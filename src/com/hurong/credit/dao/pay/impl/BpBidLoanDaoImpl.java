package com.hurong.credit.dao.pay.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.pay.BpBidLoanDao;
import com.hurong.credit.model.pay.BpBidLoan;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpBidLoanDaoImpl extends BaseDaoImpl<BpBidLoan> implements BpBidLoanDao{

	public BpBidLoanDaoImpl() {
		super(BpBidLoan.class);
	}
	
	@Override
	public BpBidLoan getByOrderNo(String orderNo) {
		String hql = "from BpBidLoan i where i.orderNo=?";
		return (BpBidLoan)findUnique(hql, new Object[]{orderNo});
	}

	@Override
	public BpBidLoan getByLoanNo(String loanNo) {
		String hql = "from BpBidLoan i where i.loanNo=?";
		return (BpBidLoan)findUnique(hql, new Object[]{loanNo});
	}

}