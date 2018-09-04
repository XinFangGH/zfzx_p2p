package com.hurong.credit.dao.customer;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.thirdPayInterface.CommonRequestInvestRecord;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public interface InvestPersonInfoDao extends BaseDao<InvestPersonInfo>{
	/**
	 * 获取投资人Id列表
	 * @return
	 */
	public List<InvestPersonInfo> getByPersonId(Long personId);

	public List<InvestPersonInfo> getByRequestNumber(String requestNo);

	public List<InvestPersonInfo> getByPlanId(Long bidId);
	public List<InvestPersonInfo> queryName(String userName,String type,Pager pager);
	public List<InvestPersonInfo> queryNamepager(String userName,String type,Integer begin,Integer max);
	public BigInteger getCount(String userName,String type,Pager pager);

	public List<CommonRequestInvestRecord> getRepaymentList(String planId,String peridId);
	public List<CommonRequestInvestRecord> getRepayEarlymentList(String planId,String slEarlyPayId);
	/**
	 *得到提前还款值后结算的优惠券的金额 加息金额等 
	 */
	public List<CommonRequestInvestRecord> getFeeRepayEarlymentList(String planId,String slEarlyPayId);
	/**
	 * 担保公司代偿款项查询
	 * @param planId
	 * @param intentDate
	 * @return
	 */
	public List<CommonRequestInvestRecord> getCompensatoryRepaymentList(String planId,String intentDate);

	/**
	 * 通过标的和流水号查询（非此流水号的）
	 * @param bidId
	 * @param orderNo
	 * @return
	 * 2017-12-20
	 * @tzw
	 */
	public List<InvestPersonInfo> bidedMoneyByOrderNo(Long bidId, String orderNo);

	public Object getSumInvestMoney(Long bidId);
}