package com.hurong.credit.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.util.MyUserSession;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		// 取得请求的URL
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		
		String conString = ServletActionContext.getRequest().getHeader("REFERER");//获取父url
		//session是否失效
		/*if (!ServletActionContext.getRequest().isRequestedSessionIdValid()) {
			//TODO session过期
			logger.info("SESSION过期");
			return "toIndex";
		}else{*/
					/**
					 * saveApplyUserapplyUser.do 借款申请信息填写
					 * getNodeMemBpCustMember.do 借款申请流程
					 * getNodeapplyUser.do 稳安总览提示
					 */
					if (url.indexOf("saveApplyUserapplyUser.do") != -1
					  ||url.indexOf("getNodeMemBpCustMember.do") != -1
					  ||url.indexOf("getNodeapplyUser.do") != -1
					  ) {
						
						BpCustMember user = (BpCustMember) ServletActionContext.getRequest().getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
						if(user == null){//用户未登陆
							logger.info("借款申请流程未发现用户登录！！！");
							return "tologin";
						}else{
							return ai.invoke();
						}
					} else {
						return ai.invoke();
					}
		/*}*/
	}
}
