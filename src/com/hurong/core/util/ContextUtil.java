/**
 * 此方法作用为取得当前用户
 */
/*
 *  北京金智万维软件有限公司 OA办公自动管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
package com.hurong.core.util;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.core.web.filter.MySessionFilter;
import com.hurong.credit.util.Common;



public class ContextUtil {
	private static final Log logger=LogFactory.getLog(ContextUtil.class);
	
	/**
	 * 用户userId add by lisl 2012-09-29  用于启动流程时，设置流程启动者
	 */
	private static Long userId;
	
	public static Long getUserId() {
		return ContextUtil.userId;
	}

	public static  void setUserId(Long userId) {
		ContextUtil.userId = userId;
	}

	
	
	public  static String getRoleTypeSession(){
		
	    String RoleType="";
		HttpSession session=(HttpSession)MySessionFilter.session.get();
		if(null!=session && null!=session.getAttribute("RoleType")){
		     RoleType=(String)session.getAttribute("RoleType");
		     return RoleType;
		}
		return RoleType;
	}
	
	public static Long getLoginCompanyId(){ //获得当前登录的所在企业id
		
		Long companyId=null;
		HttpSession session=(HttpSession)MySessionFilter.session.get();
		if(null!=session.getAttribute("CompanyId")){
			companyId=Long.valueOf(session.getAttribute("CompanyId").toString());
		}
		return companyId;
		
	}


	private static int addPart = 1;
	private static String result = "";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static String lastDate = "";
	private static Integer length = 20;
	/**
	 * 取值：6位随机数+通过System.nanoTime()返回最准确的可用系统计时器的当前值，以毫微秒为单位（14位）。
	 * 第三方及系统交易流水号共20位
	 * @return 第三方交易需要的流水号（系统账户需要的流水号）
	 * add by linyan   2015-04-21
	 * update by tzw 返回20位流水号，同时间流水号自增  解决流水号重复的问题
	 */
	public static String createRuestNumber(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		return Common.getRandomNum(3)+sdf.format(new Date());
//		return Common.getRandomNum(6)+System.nanoTime();
		//获取时间部分字符串

		Date now = new Date();
		String nowStr = sdf.format(now);

		//获取数字后缀值部分
		if (lastDate.equals(nowStr)) {
			addPart += 1;
		} else {
			addPart = 1;
			lastDate = nowStr;
		}

		if (length > 17) {
			Integer sub = length - 17;
			for (int i = 0; i < sub - ((addPart + "").length()); i++) {
				nowStr += "0";
			}
			nowStr += addPart;
			result = nowStr;
		} else {
			result = nowStr;
		}
		return result;
	}
}
