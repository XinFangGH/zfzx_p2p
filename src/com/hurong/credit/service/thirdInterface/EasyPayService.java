package com.hurong.credit.service.thirdInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hurong.credit.model.user.BpCustMember;

public interface EasyPayService{

	public String[] recharge(HttpServletResponse response, BpCustMember mem,
			String orderNum, String ordDate, String amount,
			HttpServletRequest request,String basePath);
	/**
	 * 用来验证返回网站参数的正确性
	 * @param request
	 * @return
	 */
	public String[] getEasypayReturnParameter(HttpServletRequest request);
	/**
	 * 用来验证返回后端Erp，操作数据库数据方法
	 * @param request
	 * @return
	 */
	public String[] postEasyPayReturnParmater(HttpServletRequest request);
	
}