package com.hurong.credit.service.p2p.loan.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.loan.P2pLoanConditionOrMaterialDao;
import com.hurong.credit.model.p2p.loan.P2pLoanConditionOrMaterial;
import com.hurong.credit.service.p2p.loan.P2pLoanConditionOrMaterialService;

/**
 * 
 * @author 
 *
 */
public class P2pLoanConditionOrMaterialServiceImpl extends BaseServiceImpl<P2pLoanConditionOrMaterial> implements P2pLoanConditionOrMaterialService{
	@SuppressWarnings("unused")
	private P2pLoanConditionOrMaterialDao dao;
	
	public P2pLoanConditionOrMaterialServiceImpl(P2pLoanConditionOrMaterialDao dao) {
		super(dao);
		this.dao=dao;
	}

}