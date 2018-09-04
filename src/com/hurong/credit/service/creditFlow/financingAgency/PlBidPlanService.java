package com.hurong.credit.service.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface PlBidPlanService extends BaseService<PlBidPlan>{
	
	/***
	 * 根据
	 * 
	 * **/
	
	
	public PlBidPlan  setProperty(PlBidPlan plan,HttpServletRequest request);
	/**
	 * 获取标的状态
	 * （0 未发布 1招标中 2已齐标 3已流标4手动关闭）
	 * @return
	 */
	public String[] bidStat(Long bidId);
	/**
	 * 更新标的状态 通过判断 金额是否已满
	 * bidId 标id 
	 * currMoney 投标金额
	 * @return
	 * 0 满标  1超标  -1 未满标
	 */
	public int updateStatByMoney(Long bidId,BigDecimal currMoney);

	/**
	 * 更新标的状态 通过判断 金额是否已满
	 * bidId 标id 
	 * currMoney 投标金额
	 * orderNo 流水号
	 * @return
	 * 0 满标  1超标  -1 未满标
	 */
	public int updateStatByOrderNomber(Long bidId,BigDecimal currMoney,String orderNo);
	/**
	 * 查询已投标金额总和
	 * @return
	 */
	public BigDecimal bidedMoney(Long bidId);
	
	/**
	 * 查询已投标金额总和
	 * @return
	 */
	public BigDecimal bidedMoneyByOrderNo(Long bidId,String orderNo);
	/**
	 * 比较金额 决定是否能投标
	 * @return
	 */
	public boolean compare(Long bidId,BigDecimal currMoney);
	/**
	 * 获取动态值
	 * @param plBidPlan
	 * @return
	 */
	public String[] bidDynamic(PlBidPlan plBidPlan);
	/**
	 * 获取担保方式
	 * @param pid
	 * @return
	 */
	public String findAssureTypeBySQL(String pid);
	public String findLoanTotalMoneyBySQL(String pid);
	/**
	 * 获取合作机构 金额
	 * @param pid 项目id
	 * @param flag 1 合作机构 0 平台自有资金
	 * @return
	 */
	public String findOrgMoneyBySQL(String pid,String flag);
	
	
	/**
	 * 获得拼接的直投标和理财计划列表
	 * @param request
	 * @return
	 */
	public List<InvestprojectPlan> getInvestPlanList(HttpServletRequest request);
	 /**
	  * 投标金额必须大于 投标起始金额 而且必须被 递增金额整除
	   * true 可以投标 false 不可以投标
	   */
	public boolean compareToStatrMoney(Long bidId,BigDecimal currMoney);
	
	//public PlBidPlan getBidDynamic(PlBidPlan plBidPlan);
	
	public PlBidPlan getAfterTime(PlBidPlan plan);
	public boolean compareToRiseMoney(Long bidId, BigDecimal userMoney);
	
	public BigDecimal[] statisticalFinance();
	public boolean compareIsLasterMoney(Long bidId,BigDecimal currMoney);
	public boolean compareCurMIsEQAft(Long bidId,BigDecimal currMoney);
	/**
	 * 项目状态
	 * @param pid
	 * @return
	 */
	public String findSmallloanProjectBySQL(String pid);
	/**
	 * 抵押担保物类别
	 * @param pid
	 * @return
	 */
	public String findMortgageBySQL(String pid);
	
	/**
	 * 主要返回网页上的显示的周期字段
	 * svn：songwj 
	 * @param pid：标的主键
	 * @return
	 */
	public PlBidPlan returnPlBidPlan(PlBidPlan plBidPlan);
	/**
	 * 比较投资金额是否超过上限金额
	 * @param bidId
	 * @param currMoney
	 * @return
	 */
	public boolean compareMaxMoney(Long bidId, BigDecimal currMoney);
	public PlBidPlan getById(Long bidId);
	
	public List<PlBidPlan> queryBidPlan(String userName, String type);
	/**
	 * 得到提前还款需要的数据(剩余本金，截止利息总额，补偿息总额，逾期金额)
	 */
	public String getPrepaymentData(Long bidPlanId,BpCustMember mem);
	
	/**
	 * 判断是否已经还清 并修改状态
	 */
	public void bidComplete(Long planId,HttpServletRequest req);

	/**
	 * 查询logo路径
	 */
	public PlBidPlan returnImgUrl(PlBidPlan plBidPlan);

	/**
	 * 查询某担保担保下某状态下的标的数量
	 * @param guarantorsId
	 * @param states
	 * @return
	 */
	public Long findBidPlanCount(Long guarantorsId, String states);
	/**
	 * 查询某担保机构担保的逾期的标的数量的查询
	 * @param guarantorsId
	 * @param states
	 * @return
	 */
	public Long findOverdueBidPlanCount(Long guarantorsId);
	/**
	 * 查询某担保公司担保下某状态下的标的总金额
	 * @param guarantorsId
	 * @param states
	 * @return
	 */
	public BigDecimal findBidPlanMoney(Long guarantorsId, String states);
	/**
	 * 查询某担保担保下某状态下的标(增加查询条件)
	 * @param guarantorsId
	 * @param states
	 * @param request
	 * @return
	 */
	public List<PlBidPlan> findBidPlan(Long guarantorsId, String states,HttpServletRequest request);
	public List<PlBidPlan> findBidPlan1(Long guarantorsId, String states,HttpServletRequest request);
	

	
	/**
	 * 计算招标项目是否超投的问题
	 * @param bidId
	 * @param userMoney
	 * @return
	 */
	int getBidSumMoney(Long bidId, BigDecimal userMoney);
	/**
	 * 新 散标列表查询
	 * @param pb
	 * @param request
	 * @return
	 */
	public List<PlBidPlan> getNewPlanList(PagingBean pb,
			HttpServletRequest request);
	
	/**
	 * 获取U计划对应的个人直投和企业直投标的转让信息
	 */
	public List<PlBidSale> getSaleList(PagingBean pb,
			Long bidId);
	
	/**
	 * 根据状态查找标的
	 * @param state
	 * @return
	 */
	public List<PlBidPlan> getListByState(String state);
	
	/**
	 * 根据传值分别获取短期(6个月以内)/中期(1年以内)/长期的标的
	 * @param state       1 短期         2中期           3长期
	 * @return
	 */
	public List<PlBidPlan> getBidListByDuration(Integer state);

	List<PlBidPlan> getIndexPlanList();

    public List<PlBidPlan> getPlanDetail(long l);



    public PlBidPlan setMobileProperty(PlBidPlan plBidPlan);

    public String[] mobileBidDynamic(PlBidPlan plBidPlan);

	public PlBidPlan mobileReturnPlBidPlan(PlBidPlan plan);

	List<PlBidPlan> getNewcomerList();

	PlBidPlan setProperty1(PlBidPlan plan, HttpServletRequest request);

	PlBidPlan returnPlBidPlan1(PlBidPlan plan);

	List<PlBidPlan> getIndexPlanList1(Integer page, Integer limit);

	Integer getCount();


	List<PlBidPlan> getNewPlanList1(Integer start, Integer limit, HttpServletRequest request);
}


