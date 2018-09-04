package com.hurong.credit.service.financePurchase.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.financePurchase.BpMoneyManagerDao;
import com.hurong.credit.model.financePurchase.BpMoneyManager;
import com.hurong.credit.service.financePurchase.BpMoneyManagerService;

/**
 * 
 * @author 
 *
 */
public class BpMoneyManagerServiceImpl extends BaseServiceImpl<BpMoneyManager> implements BpMoneyManagerService{
	@SuppressWarnings("unused")
	private BpMoneyManagerDao dao;
	
	public BpMoneyManagerServiceImpl(BpMoneyManagerDao dao) {
		super(dao);
		this.dao=dao;
	}

}