package com.hurong.core.integral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.util.MyUserSession;

/*
 * 积分切面类
 */
public class IntegralAop {

	// 切面方法
	public Object integralEngine(ProceedingJoinPoint pjp) throws Throwable {
		Object object = null;

		object = pjp.proceed();// 放行

		Signature signature = pjp.getSignature();
		/*
		 * System.out.println("方法名："+signature.getName()+"-----:");
		 * System.out.println(signature.getDeclaringTypeName());
		 * System.out.println(signature.getDeclaringType());
		 */

		try {
			IntegralManage integralManage = new IntegralManageImpl();
			integralManage.integralEngine(signature.getDeclaringTypeName(),signature.getName());
		} catch (Exception e) {
			System.out.println("自动化AOP积分配置功能报错");
			e.printStackTrace();
		}

		return object;
	}

}
