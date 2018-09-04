package com.hurong.credit.dao.customer;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.customer.BpCustRelation;

/**
 * 
 * @author 
 *
 */
public interface BpCustRelationDao extends BaseDao<BpCustRelation>{

	BpCustRelation getByLoanTypeAndId(String loanUserType, Long loanUserId);
	
	public BpCustRelation getP2pCustById(Long custId);
	public BpCustRelation getP2pCustLoanById(Long custId);
	
	public BpCustRelation getCustRelation(Long offlineCusId);
	public BpCustRelation getRelation(Long offlineCusId);
	public BpCustRelation getCustOffine(Long custId,String custType);
	public List<BpCustRelation> getP2pCustListById(Long custId);
}