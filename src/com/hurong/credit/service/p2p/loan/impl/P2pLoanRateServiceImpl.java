package com.hurong.credit.service.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanRateDao;
import com.hurong.credit.model.p2p.loan.P2pLoanRate;
import com.hurong.credit.service.p2p.loan.P2pLoanRateService;

/**
 * 
 * @author 
 *
 */
public class P2pLoanRateServiceImpl extends BaseServiceImpl<P2pLoanRate> implements P2pLoanRateService{
	@SuppressWarnings("unused")
	private P2pLoanRateDao dao;
	
	public P2pLoanRateServiceImpl(P2pLoanRateDao dao) {
		super(dao);
		this.dao=dao;
	}

}