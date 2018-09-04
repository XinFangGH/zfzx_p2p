package com.hurong.credit.dao.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;

/**
 * 
 * @author 
 *
 */
public interface PlBidPlanDao extends BaseDao<PlBidPlan>{

	String findLoanTotalMoneyBySQL(String pid);

	String findOrgMoneyBySQL(String pid, String flag);

	public List<InvestprojectPlan> getInvestPlanList(HttpServletRequest request);
	
	public List<PlBidPlan> getByGuarantor(Long userId);
	BigDecimal statisticalFinance(String sql);

	String findAssureTypeBySQL(String pid);
	public String findSmallloanProjectBySQL(String pid);
	public String findMortgageBySQL(String pid);

	PlBidPlan getById(Long bidId);

	public PlBidPlan getByRequestNo(String requestNo);
	
	public List<PlBidPlan> queryBidPlan(String userName, String type);
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
	 * 新 散标列表查询
	 * @param pb
	 * @param request
	 * @return
	 */
	public List<PlBidPlan> getNewPlanList(PagingBean pb,
			HttpServletRequest request);

	public List<PlBidSale> getSaleList(PagingBean pb,
			Long bidId);

	/**
	 * 根据状态查找标的
	 * @param state
	 * @return
	 */
	List<PlBidPlan> getListByState(String state);

	/**
	 * 根据传值分别获取短期(6个月以内)/中期(1年以内)/长期的标的
	 * @param state       1 短期         2中期           3长期
	 * @return
	 */
	List<PlBidPlan> getBidListByDuration(Integer state);

    List<PlBidPlan> getIndexPlanList();

    public List<PlBidPlan> getPlanDetail(Long bidId);

    List<PlBidPlan> getNewcomerList();

	List<PlBidPlan> getIndexPlanList1(Integer page, Integer limit);

	Integer getCount();


    List<PlBidPlan> getNewPlanList1(Integer start, Integer limit, HttpServletRequest request);
}