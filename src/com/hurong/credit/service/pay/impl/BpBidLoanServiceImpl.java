package com.hurong.credit.service.pay.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.pay.BpBidLoanDao;
import com.hurong.credit.model.pay.BpBidLoan;
import com.hurong.credit.service.pay.BpBidLoanService;

/**
 * 
 * @author 
 *
 */
public class BpBidLoanServiceImpl extends BaseServiceImpl<BpBidLoan> implements BpBidLoanService{
	@SuppressWarnings("unused")
	private BpBidLoanDao dao;
	
	public BpBidLoanServiceImpl(BpBidLoanDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	@Override
	public BpBidLoan getByOrderNo(String orderNo) {
		return dao.getByOrderNo(orderNo);
	}

	@Override
	public BpBidLoan getByLoanNo(String loanNo) {
		return dao.getByLoanNo(loanNo);
	}

}