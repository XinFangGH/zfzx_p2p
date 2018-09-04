package com.hurong.core.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.credit.model.user.BpCustMember;
public class MySessionFilter implements Filter {

	@SuppressWarnings("rawtypes")
	public static ThreadLocal session = new ThreadLocal();
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Override
	public void destroy() {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		    session.set(((HttpServletRequest)req).getSession());
		    HttpServletRequest hsr = (HttpServletRequest) req;
		    HttpServletResponse res = (HttpServletResponse)resp;
		   BpCustMember bcm = (BpCustMember) ((HttpServletRequest)req).getSession().getAttribute("hurong_website");
		   Long userid = null;
		   if(bcm != null){
			   userid = bcm.getId();
		   }
		   
		  
		   if(hsr.getRequestURL().indexOf("secureSingle.do")==-1//心跳链接
				   &&hsr.getRequestURL().indexOf("/js/")==-1//js连接
				   &&hsr.getRequestURL().indexOf("/images/")==-1//图片链接
				   &&hsr.getRequestURL().indexOf("/css/")==-1//css链接
				   ){
		    logger.info("FILTER:<|"+userid+"|>"+ hsr.getRequestURL());
		   }
		    StringBuffer url = hsr.getRequestURL();
		    
		   
		    
		    if(url.length()>0){
		    	int si = url.toString().indexOf(".jsp");
		    	if(si!=-1){//过滤jsp乱入
		    		res.sendRedirect(hsr.getContextPath()+"/html/error_page_404.html");
		    	}else{
		    		chain.doFilter(req, resp);
		    	}
		    }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
