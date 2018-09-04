package com.hurong.credit.service.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.thirdInterface.WebBankCodeDao;
import com.hurong.credit.model.thirdInterface.WebBankCode;
import com.hurong.credit.service.thirdInterface.WebBankCodeService;

/**
 * 
 * @author 
 *
 */
public class WebBankCodeServiceImpl extends BaseServiceImpl<WebBankCode> implements WebBankCodeService{
	@SuppressWarnings("unused")
	private WebBankCodeDao dao;
	
	public WebBankCodeServiceImpl(WebBankCodeDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public WebBankCode getByCardName(String openBankId) {
		return dao.getByCardName(openBankId);
	}

}