package com.hurong.credit.service.credit.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.credit.thirdInterface.WebBankCodeFudianDao;
import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;
import com.hurong.credit.service.credit.thirdInterface.WebBankCodeFudianService;

/**
 * 
 * @author 
 *
 */
public class WebBankCodeFudianServiceImpl extends BaseServiceImpl<WebBankCodeFudian> implements WebBankCodeFudianService{
	@SuppressWarnings("unused")
	private WebBankCodeFudianDao dao;
	
	public WebBankCodeFudianServiceImpl(WebBankCodeFudianDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public WebBankCodeFudian getFudianRoleByBankCode(String bankId) {
		return dao.getFudianRoleByBankCode(bankId);
	}
	

}