package com.hurong.credit.dao.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanProductDao;
import com.hurong.credit.model.p2p.loan.P2pLoanProduct;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class P2pLoanProductDaoImpl extends BaseDaoImpl<P2pLoanProduct> implements P2pLoanProductDao{

	public P2pLoanProductDaoImpl() {
		super(P2pLoanProduct.class);
	}

}