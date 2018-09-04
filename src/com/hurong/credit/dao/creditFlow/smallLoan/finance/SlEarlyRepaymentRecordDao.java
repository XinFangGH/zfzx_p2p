package com.hurong.credit.dao.creditFlow.smallLoan.finance;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;



/**
 * 
 * @author 
 *
 */
public interface SlEarlyRepaymentRecordDao extends BaseDao<SlEarlyRepaymentRecord>{
	public SlEarlyRepaymentRecord queryId(Long bidPlanId);
}