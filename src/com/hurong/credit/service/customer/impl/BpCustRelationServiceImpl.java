package com.hurong.credit.service.customer.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.customer.BpCustRelationDao;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.service.customer.BpCustRelationService;

/**
 * 
 * @author 
 *
 */
public class BpCustRelationServiceImpl extends BaseServiceImpl<BpCustRelation> implements BpCustRelationService{
	@SuppressWarnings("unused")
	private BpCustRelationDao dao;
	
	public BpCustRelationServiceImpl(BpCustRelationDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BpCustRelation getByLoanTypeAndId(String loanUserType,
			Long loanUserId) {
		
		return dao.getByLoanTypeAndId(loanUserType,loanUserId);
	}

	@Override
	public BpCustRelation getP2pCustById(Long custId) {
		// TODO Auto-generated method stub
		return dao.getP2pCustById(custId);
	}

	@Override
	public BpCustRelation getP2pCustLoanById(Long custId) {
		// TODO Auto-generated method stub
		return dao.getP2pCustLoanById(custId);
	}

	@Override
	public BpCustRelation getCustRelation(Long offlineCusId) {
		// TODO Auto-generated method stub
		return dao.getCustRelation(offlineCusId);
	}

	@Override
	public BpCustRelation getRelation(Long offlineCusId) {
		// TODO Auto-generated method stub
		return dao.getRelation(offlineCusId);
	}

	@Override
	public BpCustRelation getCustOffine(Long custId, String custType) {
		// TODO Auto-generated method stub
		return dao.getCustOffine(custId, custType);
	}
	

}