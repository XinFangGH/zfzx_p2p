package com.hurong.credit.service.creditFlow.finance.compensatory.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.finance.compensatory.PlBidCompensatoryFlowDao;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatoryFlow;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryFlowService;

/**
 * 
 * @author 
 *
 */
public class PlBidCompensatoryFlowServiceImpl extends BaseServiceImpl<PlBidCompensatoryFlow> implements PlBidCompensatoryFlowService{
	@SuppressWarnings("unused")
	private PlBidCompensatoryFlowDao dao;
	
	public PlBidCompensatoryFlowServiceImpl(PlBidCompensatoryFlowDao dao) {
		super(dao);
		this.dao=dao;
	}

}