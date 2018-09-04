package com.hurong.credit.action.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
/**
 * 
 * @author Li Yunfei
 * @TEL 18210204261
 * 
 */
//@WebServlet(name="DataCollection",urlPatterns="/DataCollection")//正式服为tomcat6
public class DataCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BpFinanceApplyUserService financeApplyUserService;
    public DataCollection() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();  
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
        financeApplyUserService = (BpFinanceApplyUserService) context.getBean("bpFinanceApplyUserService"); 
		BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
		Long l = Long.valueOf(request.getParameter("bpcustmemberid"));
		applyUser.setUserID(l);
		List<BpFinanceApplyUser> listApplyUser = financeApplyUserService.getApplyUser(applyUser);
		List<BpFinanceApplyUser> newapply = new ArrayList<BpFinanceApplyUser>();
		HttpSession s = request.getSession();
		for(BpFinanceApplyUser u:listApplyUser){
			if("0".equals(u.getState())){
				u.setState("3");
			}
			newapply.add(u);
		}
		s.setAttribute("applyuser",newapply);
	}
}
