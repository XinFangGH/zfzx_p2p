package com.hurong.credit.model.mobile;

import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.util.MyUserSession;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MobileInterceptor  extends MethodFilterInterceptor   {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        MobileDataResultModel model = new MobileDataResultModel();
        HttpSession session = ServletActionContext.getRequest().getSession();
        String isApp = ServletActionContext.getRequest().getParameter("isApp");
        BpCustMember cust = (BpCustMember) session.getAttribute(MyUserSession.MEMBEER_SESSION);
        if(cust!=null){
         return actionInvocation.invoke();
        }else {
            if("1".equals(isApp)){
                PrintWriter pw  = ServletActionContext.getResponse().getWriter();
                model.setCode(MobileErrorCode.NOT_LOGIN);
                pw.println(model.toJSON());
                pw.flush();
                pw.close();
                return null;
            }else {
                return "reg_log";
            }
        }
    }
}
