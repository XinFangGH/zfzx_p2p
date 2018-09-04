package com.hurong.credit.service.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanBasisMaterialDao;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.service.p2p.loan.P2pLoanBasisMaterialService;

/**
 * 
 * @author 
 *
 */
public class P2pLoanBasisMaterialServiceImpl extends BaseServiceImpl<P2pLoanBasisMaterial> implements P2pLoanBasisMaterialService{
	@SuppressWarnings("unused")
	private P2pLoanBasisMaterialDao dao;
	
	public P2pLoanBasisMaterialServiceImpl(P2pLoanBasisMaterialDao dao) {
		super(dao);
		this.dao=dao;
	}

}