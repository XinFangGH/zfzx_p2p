package com.hurong.credit.service.customer;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.customer.BpCustRelation;

/**
 * 
 * @author 
 *
 */
public interface BpCustRelationService extends BaseService<BpCustRelation>{
    /**
     * 获取关系 通过类别和ID
     * @param loanUserType
     * @param loanUserId
     * @return
     */
	BpCustRelation getByLoanTypeAndId(String loanUserType, Long loanUserId);
	
	public BpCustRelation getP2pCustById(Long custId);
	
	public BpCustRelation getP2pCustLoanById(Long custId);
	
	public BpCustRelation getCustRelation(Long offlineCusId);
	public BpCustRelation getRelation(Long offlineCusId);
	
	public BpCustRelation getCustOffine(Long custId,String custType);
}


