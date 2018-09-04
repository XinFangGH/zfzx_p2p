package com.hurong.credit.dao.creditFlow.finance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.SlFundIntentPeriod;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;

public interface BpFundIntentDao extends BaseDao<BpFundIntent> {
	
	List<FundIncome> getIncome(HttpServletRequest request,PagingBean pb,Long investPersonId);
	
	List<BpFundIntent> getList(Long bidPlanId,PagingBean pb);
	
	public List<BpFundIntent> getBpFund(String fundType, String investPersonName,String time);
	
	public BigDecimal getTotle(String fundType,String investPeronName);
	
	public java.math.BigDecimal getincome(String type, String investpersonName);
	
	public Long getProjectInfo(String fundType, String investPersonName,String type);
	
	public List<BpFundIntent> getProjectInfoInvest(Long bidPlanId,String fundType,String investpersonName) ;
	//计算已还款的金额
	public BigDecimal getByPlanId(String bidId,String CustId);
	
	//按照标的号 查找最后还款本金的款项集合
	public List<BpFundIntent> getByPlanId(String bidId);
	/**
	 * 根据投资人的id获得投资人当期应该受到的钱是多少
	 * @param temp
	 * @param planId
	 * @param peridId
	 * @param object
	 * @return
	 */
	public BigDecimal getByPlanIdAndOtherRequest(String temp, String planId,String peridId, String type);
	public BigDecimal getTotal(Long bidPlanId, String type);
	public Integer getPayintentPeriodMax(Long bidPlanId) ;
	public BigDecimal getnotMoneySum(Long bidPlanId, int payintentPeriod);
	public java.util.Date getIntentDate(Long bidPlanId,int payintentPeriod,String type);
	public java.lang.Integer getNextCount(Long bidPlanId);
	
	public java.math.BigDecimal getMoney(Long bidPlanId,String fundType, String type,String orderNo);
	public BigDecimal getFinancialMoney(Long bidPlanId, String type);

	List<BpFundIntent> getListByBidPlanId(long parseLong, Object object);
	public List<BpFundIntent> getBpFundIntentAll(String bidId, String type,String userName);
	
	public Integer getProgressTotal(Long bidPlanId,String userName,String type);
	public Integer getFactDateNull(String userName);
	
	/**
	 * 依据款项交易的流水号来进行款项的查询
	 * @param requestNo
	 * @return
	 */
	public List<BpFundIntent> getByRequestNo(String requestNo);
	public List<BpFundIntent> getCouponsIntent(String planId,String peridId,String requestNo,String fundType);
	public BpFundIntent getBpFundIntent(Long ProjectId);
	
	public List<BpFundIntent> getFundIntent(String investpersonName);
	
	public List<BpFundIntent> getLoanPlanId(Long userId) ;
	
	public List<BpFundIntent> getIntentList(Long userId,String requestType,Pager pager) ;
	
	public BigDecimal getEveryTimeMoney(Long ProjectId);
	
	public List<BpFundIntent> getFund(Long ProjectId);
	
	public BigDecimal getAllAfterPrincipalMoney(Long bidPlanId,String orderNo);
	
	/**
	 * 截至到今天剩余逾期的收益金额 
	 */
	public BigDecimal getnotLoanInterestToEnddate(Long bidPlanId,String orderNo,Date enddate);
	/**
	 * 截至到今天剩余逾期的收益金额 
	 */
	public BigDecimal getafterLoanInterestfromEnddate(Long bidPlanId,String orderNo,Date enddate);
	/**
	 * 截至到今天上个款项
	 */
	public BpFundIntent getnotLoanInterestlast(Long bidPlanId,String orderNo,Date enddate);
	/**
	 * 截至到今天下个款项
	 */
	public BpFundIntent getnotLoanInterestnext(Long bidPlanId,String orderNo,Date enddate);
	/**
	 * 剩余未还的收益金额 
	 */
	public BigDecimal getnotLoanInterest(Long bidPlanId,String orderNo);
	/**
	 * 总收益金额 
	 */
	public BigDecimal getLoanInterest(Long bidPlanId,String orderNo);
	/**
	 * 借款金额 
	 */
	public BigDecimal getPrincipalLending(Long bidPlanId,String orderNo);
	/**
	 * 截止当前时间的剩余本金
	 */
	public BigDecimal getAllPrincipalMoney(Long bidPlanId,String orderNo);
	/**
	 * 截止当前时间的逾期金额，除了本金放贷
	 */
	public BigDecimal getOverdueMoney(Long bidPlanId);
	/**
	 * 某个时间之后所有的款项（提前还款）
	 */
	public List<BpFundIntent> listByBidIdAndEarlyDate(Long bidPlanId,String earlyDate,Long slEarlyRepaymentId);
	/**
	 * 获得提前还款日所在的期数
	 */
	public List<BpFundIntent> listByEarlyDate(Long bidPlanId,String orderNo,String earlyDate,String fundType);
	/**
	 * 得带某提前还款的款项
	 */
	public List<BpFundIntent> listBySlEarlyRepaymentId(Long bidPlanId,Long slEarlyRepaymentId);
   
	/**
	 * 通过标和提前还款id获取到当前投资应收的金额和平台需要收到的金额
	 * @param orderNo
	 * @param planId
	 * @param slEarlyPayId
	 * @param object
	 * @return
	 */
	public BigDecimal getSumEarlymoney(String orderNo, String planId,
			String slEarlyPayId, Object object);
	
	public List<SlFundIntentPeriod> listByBidPlanIdAndpayintentPeriod( PagingBean pb,Map<String, String> map);
	
	public List<BpFundIntent> getByBidPlanIdAndIntentDate(Long bidPlanId,Date intentDate,String orderNo);

	BigDecimal getrepaymentTotal(Long bidId);
	
	/**
	 * 统计已还款金额
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

	public BigDecimal getBackMoney(String bidId,String period);
	
	
	/**
	 *按照期数和标的Id获取当期的款项list 
	 */
	public List<BpFundIntent> getListByBidIdAndPeriod(String planId,String period);
	
	//public List<BpFundIntent> getUnReturnFund();
	/**
	 *获取散标提前还款未对账的款项 
	 */
	public List<BpFundIntent> getUnCheckIntent(String bidId);
	/**
	 *根据提前还款的id查询款项List
	 */
    public List<BpFundIntent>  getBySlEarlyId(String slEarlyId);
    /**
     *提前还款查找原来的款项台账 
     */
    public List<BpFundIntent>  getOriFund(String bidId);
    
    /**
     *根据标的请求流水号查询款项台账 
     */
    public List<BpFundIntent> getByRequestNoLoaner(String requestNo);

    


    /**
     *查询罚息金额
     * 
     */
    public BigDecimal getBackAccMoney(String bidId, String period);
    
    /**
     * 查询借款人款项台账（还款中待偿还的项目）
     * @param id
     * @param loginname
     * @param i
     * @param loanType
     * @return
     */
	List<ShowManageMoney> findLoanRepayMemtList(Long id, String loginname,Long planId, String loanType,HttpServletRequest request);
    /**
     * 借款人已经标记为已结项（已完成的项目）
     * @param id
     * @param loginname
     * @param planId
     * @param loanType
     * @return
     */
	public List<ShowManageMoney> findLoanFishRepayMentList(Long id, String loginname,Long planId, String loanType,HttpServletRequest request);
    /**
     * 借款人招标中的项目（尚未进入还款前的状态）
     * @param id
     * @param loginname
     * @param planId
     * @param loanType
     * @return
     */
	public List<ShowManageMoney> findLoanBeforeRepayMentList(Long id,String loginname, Long planId, String loanType,HttpServletRequest request);
	/**
	 * 投资人款项台账
	 * @param memId
	 * @param loginname
	 * @param planId
	 * @param repaymentType
	 * @return
	 */
	public List<FundPay> findInvestRepayMemtList(Long memId, String loginname,String planId, String repaymentType,HttpServletRequest request);
    /**
     * 已经结项的投资人的投资记录
     * @param memId
     * @param loginname
     * @param planId
     * @param repaymentType
     * @return
     */
	List<FundPay> findInvestFinishRepayMemtList(Long memId, String loginname,String planId, String repaymentType,HttpServletRequest request);
    /**
     * 尚未起息的投资记录
     * @param memId
     * @param loginname
     * @param planId
     * @param repaymentType
     * @return
     */
	List<FundPay> findInvestBeforeRepayMemtList(Long memId, String loginname,String planId, String repaymentType,HttpServletRequest request);
	
	List<BpFundIntent> findByPeriodAndBidId(String bidId,String periodId);
    /**
	 * 根据标的ID和款项类型查询款项记录,带条件查询
	 */
	public List<ShowManageMoney> listByGuarantorsId(Long guarantorsId,HttpServletRequest request);
	public List<ShowManageMoney> listByGuarantorsId1(Long guarantorsId,HttpServletRequest request);
	/**
	 * 获得提前还款日所在的期数
	 */
	public List<BpFundIntent> queryNotPay(Long bidPlanId,String orderNo,String fundType);
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
	/**
	 * 根据标的id和还款期数计算平台应收取费用
	 * 
	 */
	public BigDecimal getManageFee(String bidId,String periodId,String slEarlyRepaymentId);

	public List<BpFundIntent> getListByBidIdAndIntent(String bidId, Date date);
	public BigDecimal getEarlyAfterMoney(String temp, String planId,
			String payintentPeriod, String type);
	public boolean update(BpFundIntent temp);
	
	
	public List<BpFundIntent> getIntentList(Long bidPlanId,Integer payintentPeriod);

	List<BpFundIntent> getBySlEarlyRepaymentId(Long bidId);


	List<FundPay> findInvestRepayMemt(Long memId, String loginname, String planId, String repaymentType, HttpServletRequest request);

	List<FundPay> findInvestFinishRepayMemt(Long memId, String loginname, String planId, String repaymentType, HttpServletRequest request);

	List<FundPay> findInvestBeforeRepayMemt(Long memId, String loginname, String planId, String repaymentType, HttpServletRequest request);

    BigDecimal getByRaiseinterest(Long id);
}
