package com.hurong.credit.service.financingAgency.manageMoney;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;

public interface PlManageMoneyPlanBuyinfoService extends BaseService<PlManageMoneyPlanBuyinfo> {
	List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long planId,Short persionType);
	/**
	 * 投资人投资管理查询方法
	 * add by linyan
	 * 2014-5-16
	 * @param request
	 * @return
	 */
	public List<Investproject> getPersonInvestProject(HttpServletRequest request,Integer start ,Integer limit);
	public List<Investproject> getTopFiveList(HttpServletRequest request);	
	public int queryNum(String userName);
	
	public  List<Investproject> getPersonInvestProject(String userType,String userId,String state);
	public String getUrl(String orderNo , String type);
	public  BigDecimal investmentBidNum(String userType,String userId,String state);
	
	public String queryLoanUrl(Long bidId);
	public PlManageMoneyPlanBuyinfo getOrderNumber(String requestNo);
	public BigInteger countpl(Long id);
	public List<PlManageMoneyPlanBuyinfo> getManagePlanBuyInfo(String sql);
	public PlManageMoneyPlanBuyinfo getCount(String sql);
	public BigInteger countExperience(Long mmplanId,Long investPersonId);
	
	/**
	 * 查询理财订单对应的债权记录
	 * @param orderId 订单号
	 * @return
	 */
	public List<PlMmOrderChildrenOr> getClaimsList(String orderId);
	/**
	 * 理财专户下的提前赎回的投资记录
	 * @param loginname
	 * @param request
	 * @param pager
	 * @return
	 */
	public List<PlManageMoneyPlanBuyinfo> findEarlyList(String loginname,HttpServletRequest request, Pager pager);
	/**
	 * 理财专户下的提前赎回的投资记录数目
	 * @param loginname
	 * @param request
	 * @return
	 */
	public Long findEarlyCount(String loginname, HttpServletRequest request);
	/**
	 * 计算提取支取的各种处理和计算
	 * @param orderId
	 * @param earlierOutDate
	 * @return
	 */
   public String gcalculateEarlyOutOrmatching(PlEarlyRedemption plEarlyRedemption);
	/**
	 * 查询理财计划的所有数据
	 * 
	 */
   public String getList(Long uerId,HttpServletRequest request);
}

