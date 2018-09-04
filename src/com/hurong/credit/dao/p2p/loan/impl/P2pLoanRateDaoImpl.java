package com.hurong.credit.dao.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanRateDao;
import com.hurong.credit.model.p2p.loan.P2pLoanRate;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class P2pLoanRateDaoImpl extends BaseDaoImpl<P2pLoanRate> implements P2pLoanRateDao{

	public P2pLoanRateDaoImpl() {
		super(P2pLoanRate.class);
	}

}