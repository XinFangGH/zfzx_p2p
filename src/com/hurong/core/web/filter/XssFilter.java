package com.hurong.core.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class XssFilter implements javax.servlet.Filter{
	private String excludedPage;
	private String[] excludedPages;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);

		// 定义表示变量 并验证用户请求URL 是否包含不过滤路径
		boolean flag = true;
		for (String page:excludedPages) {
			if (xssRequest.getServletPath().equals(page)){
				flag = false;
				break;
			}
		}
		if (flag){
			filterChain.doFilter(xssRequest, response);
		}else {
		    filterChain.doFilter(request, response);
        }

		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPage = filterConfig.getInitParameter("excludedPage");
		if (excludedPage != null && excludedPage.length() > 0){
			excludedPages = excludedPage.split(",");
		}
		
	}
}
