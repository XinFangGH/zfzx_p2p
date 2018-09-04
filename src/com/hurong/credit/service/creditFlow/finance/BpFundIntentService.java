package com.hurong.credit.service.creditFlow.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.SlFundIntentPeriod;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;

public interface BpFundIntentService extends BaseService<BpFundIntent> {
	BigDecimal getInterest(Long preceptId,Long investPersonId);
	BigDecimal getPrincipal(Long preceptId,Long investPersonId);
	
	
	List<FundIncome> getIncome(HttpServletRequest request,PagingBean pb,Long investPersonId);
	
	List<BpFundIntent> getList(Long bidPlanId,PagingBean pb);
	/**
	 * 根据投资人的id获得投资人当期应该受到的钱是多少
	 * @param temp
	 * @param planId
	 * @param peridId
	 * @param object
	 * @return
	 */
	public BigDecimal getByPlanIdAndOtherRequest(String temp, String planId,String peridId, String type); 
	
	public List<BpFundIntent> getBpFund(String fundType,String investPersonName,String time);
	
	public java.math.BigDecimal getTotle(String fundType, String investPeronName);
	
	public java.math.BigDecimal getincome(String type, String investpersonName);
	
	public Long getProjectInfo(String fundType, String investPersonName,String type);
	public List<BpFundIntent> getProjectInfoInvest(Long bidPlanId,String fundType,String investpersonName);
	
	public java.math.BigDecimal getTotal(Long bidPlanId, String type);
	
	public Integer getPayintentPeriodMax(Long bidPlanId);
	public java.math.BigDecimal getnotMoneySum(Long bidPlanId,int payintentPeriod);
	public java.util.Date getIntentDate(Long bidPlanId,int payintentPeriod,String type);
	public java.lang.Integer getNextCount(Long bidPlanId);
	/**
	 * 借款管理中的金额查询
	 * @param bidPlanId
	 * @param type
	 * @return
	 */
	public java.math.BigDecimal getMoney(Long bidPlanId,String fundType, String type,String orderNo);
	
	public java.math.BigDecimal getFinancialMoney(Long bidPlanId, String type);
	//获取借款人款项台帐
	List<BpFundIntent> getListByBidPlanId(long parseLong, Object object);
	String[] getFundInfoByPlanId(Long valueOf, HttpServletRequest request);
	
	public List<BpFundIntent> getBpFundIntentAll(String bidId, String type,String userName);
	
	//招标进度
	public Integer getProgressTotal(Long bidPlanId,String userName,String type);
	public Integer getFactDateNull(String userName);
	
	/**
	 * 依据交易流水号查询款项记录
	 * @param requestNo
	 * @return
	 */
	public List<BpFundIntent> getByRequestNo(String requestNo);
	/**
	 * 查询平台奖励的台账
	 * @param planId
	 * @param peridId
	 * @return
	 */
	public List<BpFundIntent> getCouponsIntent(String planId,String peridId,String requestNo,String fundType);
	
	public BpFundIntent getBpFundIntent(Long ProjectId);
	
	public List<BpFundIntent> getFundIntent(String investpersonName);
	/**
	 * 通过标查询借款的标信息
	 * @param userId
	 * @return
	 */
	public List<BpFundIntent> getLoanPlanId(Long userId);
	
	public List<BpFundIntent> getIntentList(Long userId,String requestType,Pager pager);
	
	public java.math.BigDecimal getEveryTimeMoney(Long ProjectId);
	
	public List<BpFundIntent> getFund(Long ProjectId);
	/**
	 * 通过标和提前还款id获取到当前投资应收的金额和平台需要收到的金额
	 * @param orderNo
	 * @param planId
	 * @param slEarlyPayId
	 * @param object
	 * @return
	 */
	public BigDecimal getSumEarlymoney(String orderNo, String planId,String slEarlyPayId, Object object); 
	
	public List<SlFundIntentPeriod> listByBidPlanIdAndpayintentPeriod( PagingBean pb,Map<String, String> map);
	/**
	 * 根据标ID 订单号 和 资金类型查询
	 * @param bidId
	 * @param orderNo
	 * @param fundType
	 * @return
	 */
	List<BpFundIntent> findByBidOrderNoFundType(String orderNo, String fundType);
	BigDecimal getrepaymentTotal(Long bidId);
	
	/**
	 * 根据投资人ID统计已还款金额
	 * @param temp
	 * @param planId
	 * @param peridId
	 * @param type
	 * @return
	 */
	public BigDecimal getAfterMoney(String temp, String planId,String peridId, String type);
	/**
	 * 根据第三方记录日志 找到还款标id，计划还款日期，得到还款要记录
	 * @param bidId
	 * @param intentDate
	 * @return
	 */
	public List<BpFundIntent> getThirdBpFundIntentList(String bidId,Date intentDate);
	/**
	 *获取当期应还总金额 
	 */
	public BigDecimal getBackMoney(String bidId, String peridId);
	public List<BpFundIntent> getListByBidIdAndPeriod(String planId,String period);
	public List<BpFundIntent> getUnReturnFund();
	/**
	 *获取提前还款为对账的款项信息 
	 */
	public List<BpFundIntent> getUnCheckIntent(String bidId);
	/**
	 *根据提现还款的id查找对应的款项台账
	 */
	public List<BpFundIntent> getBySlEarlyId(String slEarlyId);
	/**
	 *提前还款查找原来的台账 
	 */
    public List<BpFundIntent> getOriFund(String bidId);
    
    public List<BpFundIntent> getByRequestNoLoaner(String requestNoLoaner);

    /**
     * 查询借款人款项台账数据
     * @param id  借款人Id
     * @param loginname  借款人名称
     * @param i  项目状态  
     * @param string   借款人款项查询类型（只查询每个项目的一期；查询全部的期数；查询）
     * @return
     */
	public List<ShowManageMoney> findLoanRepayMemtList(Long id, String loginname,Long planId, String repaymentType,HttpServletRequest request);


    /**
     *查询罚息金额
     * 
     */
    public BigDecimal getBackAccMoney(String bidId, String period);
    
    /**
     * 查询出来投资人的款项台账及各种投资状态下项目的信息
     * @param loginname
     * @param memId
     * @param planId
     * @param repaymentType
     * @return
     */
	public List<FundPay> findInvestRepaymentList(String loginname, Long memId,String planId, String repaymentType,HttpServletRequest request);
	public List<BpFundIntent> findByPeriod(String bidId,String periodId);
	public BigDecimal getByPlanId(String bidId,String CustId);
	  /**
	 * 根据标的ID和款项类型查询款项记录,带条件查询
	 */
	public List<ShowManageMoney> listByGuarantorsId(Long guarantorsId,HttpServletRequest request);
	public List<ShowManageMoney> listByGuarantorsId1(Long guarantorsId,HttpServletRequest request);
	/**
	 * 根据标的ID和款项类型查询款项记录
	 */
	public List<BpFundIntent> listBybidPlanIdType(Long bidPlanId, String fundType);
	/**
	 * 根据标的id以及款项类型及计划到账单日查询未对账金额总和
	 */
	public BigDecimal getMoneyByPlanType(Long bidPlanId,String fundType,String intentDate);
    /**
	 * 根根据标的id以及款项类型及计划账单日查询款项记录
	 */
	public List<ShowManageMoney> listByByPlanIdDate(Long bidPlanId,String intentDate);
	  /**
	 * 根根据标的id及计划账单日查询罚息金额
	 */
	public BigDecimal getPenaltyInterest(String temp, String planId,String peridId);
	/**
	 * 查询某次代偿的总金额
	 */
	public BigDecimal sumAllCompensatoryMoney(String planId, String peridId,String fundType);
	
	/**
	 * 
	 * @param temp 订单号
	 * @param planId标的id
	 * @param intentDate计划到期日
	 * @param type款项类型
	 * @return
	 */
	public BigDecimal getMoneyByPlanDateType(String temp, String planId,String intentDate, String type);
	/**
	 * 查询未还款的款项
	 * @param bidId
	 * @return
	 */
	public List<BpFundIntent> getOverList(String bidId);
	/**
	 * 查询某担保机构待代偿金额
	 * @param guarantorsId
	 * @return
	 */
	public BigDecimal findWaitCompensatoryMoney(Long guarantorsId);

	public List<BpFundIntent> getListByBidIdAndIntent(String bidId,Date date);
	public BigDecimal getEarlyAfterMoney(String temp, String planId,
			String payintentPeriod, String type);
	public boolean update(BpFundIntent temp);
	
	
	public List<BpFundIntent> getIntentList(Long bidPlanId,Integer payintentPeriod);
	List<BpFundIntent> getBySlEarlyRepaymentId(Long bidId);

	public FundPay setMoneyAndRemain(FundPay fp);

	public List<FundPay> findInvestRepayment(String loginname, Long memId,String planId, String repaymentType,HttpServletRequest request);

    BigDecimal getByRaiseinterest(Long id);
}
