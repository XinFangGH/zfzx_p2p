package com.hurong.credit.service.customer;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.service.BaseService;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.thirdPayInterface.CommonRequestInvestRecord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public interface InvestPersonInfoService extends BaseService<InvestPersonInfo>{
	/**
	 * 获取投资人Id列表
	 * @return
	 */
	public List<InvestPersonInfo> getByPersonId(Long personId);
	/**
	 * 获取交易流水号获得的交易记录
	 * @param requestNo
	 * @return
	 */
	public List<InvestPersonInfo> getByRequestNumber(String requestNo);
	/**
	 * 通过标id 获取
	 * @param bidId
	 * @return
	 */
	public List<InvestPersonInfo> getByPlanId(Long bidId);

	/**
	 * 通过标id 获取
	 * @param bidId
	 * @return
	 */
	public List<InvestPersonInfo> bidedMoneyByOrderNo(Long bidId,String orderNo);
	
	public List<InvestPersonInfo> queryName(String userName,String type,Pager pager);
	public List<InvestPersonInfo> queryNamepager(String userName,String type,Integer begin,Integer max);
	public BigInteger getCount(String userName,String type,Pager pager);
	
	/**
	 * 根据标的id和期数获得相应款项记录信息
	 * @param planId   标id
	 * @param peridId  期数
	 * @return
	 */
	public List<CommonRequestInvestRecord> getRepaymentList(String planId,String peridId);
	/**
	 * 根据标的id和提前还款id获得相应款项记录信息
	 * @param planId   标id
	 * @param peridId  期数
	 * @return
	 */
	public List<CommonRequestInvestRecord> getRepayEarlymentList(String planId,String slEarlyPayId);
	/**
	 * 获得散标提前还款 的优惠券金额  加息金额等 
	 */
	public List<CommonRequestInvestRecord> getFeeRepayEarlymentList(String planId,String slEarlyPayId);
	/**
	 * 担保公司代偿款项查询
	 * @param planId
	 * @param intentDate
	 * @return
	 */
	public List<CommonRequestInvestRecord> getCompensatoryRepaymentList(String planId,String intentDate);


	public BigDecimal getSumInvestMoney(Long bidId);

}


