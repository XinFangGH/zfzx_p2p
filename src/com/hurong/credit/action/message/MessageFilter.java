package com.hurong.credit.action.message;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.message.OaNewsMessagerinfoService;
import com.hurong.credit.util.MyUserSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MessageFilter  implements Filter{

	private static String path;
	private static String basePath;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		//得到request
		HttpServletRequest request = (HttpServletRequest)arg0;
		//得到当前用户
		BpCustMember mem = (BpCustMember) request.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		path= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		basePath = path;//path.replace("https", "http").replace(":443", "");
		if(mem!=null){
			//得到消息的接口
			/*OaNewsMessageService oaNewsMessageService = (OaNewsMessageService) AppUtil.getBean("oaNewsMessageService");
			int message=oaNewsMessageService.getStatusNum(Integer.parseInt(mem.getId().toString()), 0);*/
			// add hgh 2005-07-13 12:01:30
			OaNewsMessagerinfoService oaNewsMessagerinfoService = (OaNewsMessagerinfoService) AppUtil.getBean("oaNewsMessagerinfoService");
			int message=oaNewsMessagerinfoService.getByStateCount(Integer.parseInt(mem.getId().toString()), 0);
			String successHtml="<a href='"+basePath+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+mem.getLoginname()+"</span></a><span class='sep'></span><a href='javascript:void(0)' target='_self' onClick='showMeg()'><span>消息("+message+")</span></a><span class='sep'></span><a href='"+basePath+"html/loginregSingle.do' onClick='exit()'><span>帮助</span></a><a href='"+basePath+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
			request.getSession().setAttribute("successHtml", successHtml);
			filterChain.doFilter(arg0, arg1);
		}else{
			//继续执行
			filterChain.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
