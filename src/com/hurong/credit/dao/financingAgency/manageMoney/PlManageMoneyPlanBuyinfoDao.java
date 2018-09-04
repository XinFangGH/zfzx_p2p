package com.hurong.credit.dao.financingAgency.manageMoney;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;

public interface PlManageMoneyPlanBuyinfoDao extends BaseDao<PlManageMoneyPlanBuyinfo> {
	List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long planId,Short persionType);

	/**
	 * 投资人投资管理查询方法
	 * add by linyan
	 * 2014-5-16
	 * @param request
	 * @return
	 */
	List<Investproject> getPersonInvestProject(HttpServletRequest request,Integer start ,Integer limit);

	public List<Investproject> getTopFiveList(HttpServletRequest request);
	
	public int queryNum(String userName);
	
	public  List<Investproject> getPersonInvestProject(String userType,String userId,String state);
	public String getUrl(String orderNo, String type);
	public  BigDecimal investmentBidNum(String userType,String userId,String state);
	public String queryLoanUrl(Long bidId);

	public PlManageMoneyPlanBuyinfo getOrderNumber(String requestNo);
	public BigInteger getCountpl(Long l);
	public List<PlManageMoneyPlanBuyinfo> getManagePlanBuyInfo(String sql);
	public PlManageMoneyPlanBuyinfo getInfoCount(String sql);
	public BigInteger countExperience(Long mmplanId,Long investPersonId);

	public List<PlMmOrderChildrenOr> getClaimsList(String orderId);
	/**
	 * 查询购买记录
	 * @param mmplanId
	 * @param persionType
	 * @param state
	 * @return
	 */
	List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long mmplanId,Short persionType,Short state);
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

	
	public List<PlManageMoneyPlanBuyinfo> getList(Long uerId,HttpServletRequest request);

	public List<PlManageMoneyPlanBuyinfo> getListNum(Long uerId,HttpServletRequest request);
}