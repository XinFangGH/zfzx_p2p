package com.hurong.credit.service.thirdInterface.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.thirdInterface.CsBankDao;
import com.hurong.credit.model.thirdInterface.CsBank;
import com.hurong.credit.service.thirdInterface.CsBankService;



/**
 * 
 * @author 
 *
 */
public class CsBankServiceImpl extends BaseServiceImpl<CsBank> implements CsBankService{
	@SuppressWarnings("unused")
	private CsBankDao dao;
	
	public CsBankServiceImpl(CsBankDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<CsBank> getListByBankName(String bankName) {
		
		return dao.getListByBankName(bankName);
	}

	@Override
	public CsBank getByCardName(String bankId) {
		// TODO Auto-generated method stub
		return dao.getByCardName(bankId);
	}

}