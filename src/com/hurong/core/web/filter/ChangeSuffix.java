package com.hurong.core.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeSuffix extends HttpServlet {
	public ChangeSuffix() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url= request.getRequestURI();
		String rooturl=request.getContextPath();
		url=url.replace(rooturl, "").replaceFirst(".html", ".do");
		RequestDispatcher rd=request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	public void init() throws ServletException {
	}

}
