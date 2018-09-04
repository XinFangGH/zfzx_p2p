package com.hurong.credit.dao.creditFlow.smallLoan.finance.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordDao;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;



/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class SlEarlyRepaymentRecordDaoImpl extends BaseDaoImpl<SlEarlyRepaymentRecord> implements SlEarlyRepaymentRecordDao{

	public SlEarlyRepaymentRecordDaoImpl() {
		super(SlEarlyRepaymentRecord.class);
	}

	@Override
	public SlEarlyRepaymentRecord queryId(Long bidPlanId) {
		String hql ="from SlEarlyRepaymentRecord as sl where sl.bidPlanId=? ";
		return (SlEarlyRepaymentRecord)this.getSession().createQuery(hql).setParameter(0, bidPlanId).uniqueResult();
	}
	

}