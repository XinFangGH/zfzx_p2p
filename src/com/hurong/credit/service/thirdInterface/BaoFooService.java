package com.hurong.credit.service.thirdInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hurong.credit.model.user.BpCustMember;

public interface BaoFooService{
	
	/**
	 * 2014-07-15
	 * 充值
	 * @return
	 */
	public String[] recharge(HttpServletResponse respose,BpCustMember mem, String amount,String basePath,HttpServletRequest request,String bankId,String orderNum);

	public String[] getBaoFooReturnParameter(HttpServletRequest request);

	public String[] postEasyPayReturnParmater(HttpServletRequest request);
	
}