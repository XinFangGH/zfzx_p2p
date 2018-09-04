package com.hurong.credit.service.creditFlow.smallLoan.finance;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;

/**
 * 
 * @author 
 *
 */
public interface SlEarlyRepaymentRecordService extends BaseService<SlEarlyRepaymentRecord>{
	

	/**
	 * 前端提前还款保存数据
	 */
	public SlEarlyRepaymentRecord saveEarlyProjectInfo(Long bidPlanId);
	
	/**
	 * 更改款项的状态为有效
	 */
	public Boolean updateFundIntentInfo(Long bidPlanId,Long slEarlyRepaymentId);
	
	public SlEarlyRepaymentRecord queryId(Long bidPlanId);
}


