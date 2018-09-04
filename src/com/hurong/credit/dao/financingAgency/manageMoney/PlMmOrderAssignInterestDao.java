package com.hurong.credit.dao.financingAgency.manageMoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.thirdPayInterface.CommonRequestInvestRecord;

/**
 * 
 * @author 
 *
 */
public interface PlMmOrderAssignInterestDao extends BaseDao<PlMmOrderAssignInterest>{


	public PlMmOrderAssignInterest getFisrtByOrderId(Long mmplanId);
	public BigDecimal getGetMoney(Long orderId);
	/**
	 * 查询个人理财顾问下的UD计划列表
	 * @param loginname
	 * @param request
	 * @return
	 */
	public List<ShowManageMoney> findFinancialList(String loginname, HttpServletRequest request,Pager pager);
	
	/**
	 * 查询个人理财顾问下的UD计划列表总数目
	 * @param loginname
	 * @param request
	 * @return
	 */
	public Long findFinancialCount(String loginname, HttpServletRequest request);
    /**
     * 根据计划的ID和计划派息日查询具体派息数据
     * @param mmplanId
     * @param intentDate
     * @return
     */
	public ShowManageMoney findByIdDate(Long mmplanId, String intentDate);
    /**
     * 查询还款金额
     * @param mmplanId
     * @param intentDate
     * @param type
     * @return
     */
	public BigDecimal findReturnMoney( Long mmplanId,String intentDate, String type);
	/**
	 * 根据流水查询款项记录
	 * @param requestNo
	 * @return
	 */
	public List<PlMmOrderAssignInterest> getByRequestNo(String requestNo);
	
    /**
     * 查询还款对账金额
     * @param orderId
     * @param mmplanId
     * @param intentDate
     * @param type
     * @return
     */
	public BigDecimal findAfterMoney( Long  orderId,Long mmplanId,String intentDate, String type);
	/**
	 * 查询某个投资人某次交易记录为对完账的款项记录
	 * @param orderId
	 * @param investPersonId
	 * @return
	 */
	public List<PlMmOrderAssignInterest> getByDealCondition(Long orderId,Long investPersonId);
	/**
	 * 查询款项记录
	 * @param mmPlanId
	 * @param intentDate
	 * @param requestNo
	 * @param fundType
	 * @return
	 */
	public List<PlMmOrderAssignInterest> getCouponsIntent(String mmPlanId, Date intentDate,String requestNo,String fundType);
	
	/**
	 * 查询个人理财顾问下的UD计划(按标的状态)
	 * @param loginname
	 * @param stste
	 * @return
	 */
	public Long getBidCountFinancial(String loginname, Integer state,String isPresale);
	/**
	 * 很据登录名和资金类型查询金额
	 * @param loginname
	 * @param fundType
	 * @return
	 */
	public BigDecimal findMoneyByType(String loginname, String fundType);
/*	*//**
	 * UD计划个人理财专户的回款计划查询
	 * @param mmPlanId
	 * @param loginname
	 * @return
	 *//*
	public List<FundPay> findLoanRepayMemtList(Long mmplanId, String loginname);*/
	
	/**
	 * UD计划个人理财专户的回款计划查询
	 * @param mmPlanId
	 * @param loginname
	 * @return
	 */
	public List<ShowManageMoney> findLoanRepayMemtList(Long mmplanId, String loginname);
	/**
	 * UD计划个人理财专户的回款计划查询
	 * @param mmPlanId
	 * @param loginname
	 * @return
	 */
	public List<ShowManageMoney> findLoanRepayMemtList1(Long mmplanId, String loginname);
	/**
	 *查询某个理财计划某种类型金额总和
	 * @param mmplanId
	 * @param type
	 * @return
	 */
	public BigDecimal getFinancialMoney(Long mmplanId, String type);
	/**
	 *UD计划收款账户还款时款项查询
	 * @param mmplanId
	 * @param intentDate
	 * @return
	 */
	public List<CommonRequestInvestRecord> getRepaymentList(Long mmplanId,String intentDate);
	/**
	 * 查询某个理财计划为对完账的款项记录
	 * @param orderId
	 * @param investPersonId
	 * @return
	 */
	public List<PlMmOrderAssignInterest> getByDealCondition(Long mmplanId);
	public List<PlMmOrderAssignInterest> listByEarlyDate(String earlyDate,Long orderId,String fundType,Long earlyRedemptionId);
	public List<PlMmOrderAssignInterest> listByOrderIdAndFundType(String orderId, String fundType);
	public List<PlMmOrderAssignInterest> getByPlanIdA(Long orderId, Long investPersonId, Long mmplanId, String fundType,Integer periods);
	public List<PlMmOrderAssignInterest> getListByBidIdAndPeriod(String bidId,String periodId,Date intentDate);
	public List<PlMmOrderAssignInterest> getByInvestNo(String requestNo);
}