package com.hurong.credit.service.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanApplyStepDao;
import com.hurong.credit.model.p2p.loan.P2pLoanApplyStep;
import com.hurong.credit.service.p2p.loan.P2pLoanApplyStepService;

/**
 * 
 * @author 
 *
 */
public class P2pLoanApplyStepServiceImpl extends BaseServiceImpl<P2pLoanApplyStep> implements P2pLoanApplyStepService{
	@SuppressWarnings("unused")
	private P2pLoanApplyStepDao dao;
	
	public P2pLoanApplyStepServiceImpl(P2pLoanApplyStepDao dao) {
		super(dao);
		this.dao=dao;
	}

}