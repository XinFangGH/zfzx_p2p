package com.hurong.credit.service.pay;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.pay.BpBidLoan;

/**
 * 
 * @author 
 *
 */
public interface BpBidLoanService extends BaseService<BpBidLoan>{
	 /**
     * 获取第三方流水号 通过 系统订单号
     * @param orderNo
     * @return
     */
	BpBidLoan getByOrderNo(String orderNo);
	/**
    * 获取第三方流水号 通过 第三方流水号
    * @param loanNo
    * @return
    */
	BpBidLoan getByLoanNo(String loanNo);
	
}


