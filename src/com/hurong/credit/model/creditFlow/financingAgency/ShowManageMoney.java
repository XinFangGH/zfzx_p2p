package com.hurong.credit.model.creditFlow.financingAgency;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Hibernate;

import com.google.gson.annotations.Expose;

/**
 * 借款人款项台账
 * 
 */
public class ShowManageMoney {
	/**
     * 投资的项目名称 plBidId
     */
    private Long bidId;
	/**
     * 投资的项目名称 plBidPlan
     */
    private String proName;
    /**
     * 招标开始时间 
     */
    private Date bidTime;
    /**
     * 投标开始时间
     */
	private Date buyEndTime;
    /**
     * 本期期数（新增）
     */
    private  Integer payintentPeriod;
    
    /**
     * 投资总金额
     */
    private BigDecimal payMoney;
    /**
     * 年化利率 plBidPlan
     */
    private BigDecimal interestRate;
    /**
     * 招标期限
     */
    private String loanLife;
    
    /**
     * 计划还款日
     */
    private Date intentDate;
    /**
     * 实际还款日
     */
    private Date factDate;
    /**
     * 还清日期
     */
    private Date repaymentDate;
    /**
     * 罚息总和
     */
    private BigDecimal accMoney;
    
    /**
     * 未还本金（新增）
     */
    private BigDecimal principal;
    
    /**
     * 已还本金（新增）
     */
    private BigDecimal backprincipal;
    
    /**
     * 未还利息（新增）
     */
    private  BigDecimal loanInterest;
    /**
     * 已还利息（新增）
     */
    private BigDecimal backloanInterest;
    /**
     * 未支付服务费（新增）
     */
    private BigDecimal serviceMoney;
    /**
     * 已支付服务费（新增）
     */
    private BigDecimal backserviceMoney;
    /**
     * 未还补偿息（新增）
     */
    private BigDecimal compensationMoney;
    
    /**
     * 已换补偿息（新增）
     */
    private BigDecimal backcompensationMoney;
    /**
     * 未还本息合计
     */
    private BigDecimal total;
    
    /**
     * 计划本息还款金额（包括逾期金额）  本金加利息+服务费
     */
    private BigDecimal intenttotal;
    
    
    /**
     * 本期应还款总额
     */
    private BigDecimal repaymentTotal;
    
    /**
     * 投资  结清 已回收金额
     * 
     */
    private BigDecimal backmoney;
    
    /**
     * 是否 授权 
     * 1表示已经授权，null表示没有授权或者取消了授权
     * 
     * 1 对应 cancel null 对应 authorization
     */
    private  String authorizationStatus;
    
	protected Integer loanerRepayMentStatus;//还款成功 1   有标的账户的第三方会用这个字段
    
    public Integer getLoanerRepayMentStatus() {
		return loanerRepayMentStatus;
	}
	public void setLoanerRepayMentStatus(Integer loanerRepayMentStatus) {
		this.loanerRepayMentStatus = loanerRepayMentStatus;
	}
	/**
     * 下个还款日 FundIntent中计算出  或者最后实际还款日期
     */
    private Date nextPayDate;
    /**
     * 
     */
    private BigDecimal nextPayMoney;
  
    /**
     * 还款进度
     */
    private String repayMentLength;
    /**
     * 合同的url
     */
    private String url;
   
    /**
     * 本息合计（尚未偿还的本息）
     */
    private BigDecimal plCount;

    /**
     * 投资订单号
     */
    private String orderNo;
	/**
	 * 表示收款人姓名，债券标表示原始债权人姓名，直投标表示借款人姓名
	 */
	protected String receiverName;
	/**
	 * 罚息天数
	 */
	private Integer punishDays; 
	/**
	 * 
	 * 以下字段为个人理财顾问新增字段
	 * 计划ID
	 */
	protected Long mmplanId;
	/**
	 * 
	 * 计划名称
	 */
	protected String mmName;
    /**
     * 招标总金额
     * 
     */
    private BigDecimal sumMoney;
    /**
     * 锁定截止日期
     */
    private Date lockingEndDate;
    /**
	 * 投资期限
	 */
	protected Integer investlimit;
    /**
     * 投标开始时间
     */
    private Date buyStartTime;
    /**
     * 预售开始日期
     */
    private Date preSaleTime;
	/**
	 * 
	 * 计划的key值
	 */
	protected String keystr;
    /**
     * 剩余金额
     * 
     */
    private BigDecimal remainingMoney;
    /**
	 * 加入人数
	 */
	protected Integer joinCount;
    /**
     * 派息总金额
     * 
     */
    private BigDecimal allMoney;
    /**
     *本金罚息金额
     * 
     */
    private BigDecimal principalAccMoney;
    /**
     * 本金+利息的罚息总金额
     * 
     */
    private BigDecimal interestAccMoney;
    
    @Expose
	 protected Integer novice;//是否新手专享   1是，0否
    @Expose
    protected double progress;//投标进度
    @Expose
    protected double afterMoney;//剩余金额
    protected java.math.BigDecimal yeaRate;//年化收益率
    protected String planName;//计划名称
    protected Integer state;
    private String nowTimeStr;//当前时间格式化
    private String preSaleTimeStr;//投标时间格式化
    
    
	public Integer getNovice() {
		return novice;
	}
	public void setNovice(Integer novice) {
		this.novice = novice;
	}
   
	public BigDecimal getPrincipalAccMoney() {
		return principalAccMoney;
	}
	public void setPrincipalAccMoney(BigDecimal principalAccMoney) {
		this.principalAccMoney = principalAccMoney;
	}
	public BigDecimal getInterestAccMoney() {
		return interestAccMoney;
	}
	public void setInterestAccMoney(BigDecimal interestAccMoney) {
		this.interestAccMoney = interestAccMoney;
	}
	public Integer getPunishDays() {
		return punishDays;
	}
	public void setPunishDays(Integer punishDays) {
		this.punishDays = punishDays;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Long getMmplanId() {
		return mmplanId;
	}
	public void setMmplanId(Long mmplanId) {
		this.mmplanId = mmplanId;
	}
	public String getMmName() {
		return mmName;
	}
	public void setMmName(String mmName) {
		this.mmName = mmName;
	}
	public BigDecimal getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}
	public Date getLockingEndDate() {
		return lockingEndDate;
	}
	public void setLockingEndDate(Date lockingEndDate) {
		this.lockingEndDate = lockingEndDate;
	}
	public Integer getInvestlimit() {
		return investlimit;
	}
	public void setInvestlimit(Integer investlimit) {
		this.investlimit = investlimit;
	}
	public Date getBuyStartTime() {
		return buyStartTime;
	}
	public void setBuyStartTime(Date buyStartTime) {
		this.buyStartTime = buyStartTime;
	}
	public Date getPreSaleTime() {
		return preSaleTime;
	}
	public void setPreSaleTime(Date preSaleTime) {
		this.preSaleTime = preSaleTime;
	}
	public String getKeystr() {
		return keystr;
	}
	public void setKeystr(String keystr) {
		this.keystr = keystr;
	}
	public BigDecimal getRemainingMoney() {
		return remainingMoney;
	}
	public void setRemainingMoney(BigDecimal remainingMoney) {
		this.remainingMoney = remainingMoney;
	}
	public Integer getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}
	public BigDecimal getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(BigDecimal allMoney) {
		this.allMoney = allMoney;
	}
	public String getAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(String authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public Date getBidTime() {
		return bidTime;
	}
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public String getLoanLife() {
		return loanLife;
	}
	public void setLoanLife(String loanLife) {
		this.loanLife = loanLife;
	}
	public Date getNextPayDate() {
		return nextPayDate;
	}
	public void setNextPayDate(Date nextPayDate) {
		this.nextPayDate = nextPayDate;
	}
	public BigDecimal getNextPayMoney() {
		return nextPayMoney;
	}
	public void setNextPayMoney(BigDecimal nextPayMoney) {
		this.nextPayMoney = nextPayMoney;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getBidId() {
		return bidId;
	}
	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}
	public BigDecimal getPlCount() {
		return plCount;
	}
	public void setPlCount(BigDecimal plCount) {
		this.plCount = plCount;
	}
	public BigDecimal getAccMoney() {
		return accMoney;
	}
	public void setAccMoney(BigDecimal accMoney) {
		this.accMoney = accMoney;
	}
	public String getRepayMentLength() {
		return repayMentLength;
	}
	public void setRepayMentLength(String repayMentLength) {
		this.repayMentLength = repayMentLength;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getRepaymentTotal() {
		return repaymentTotal;
	}
	public void setRepaymentTotal(BigDecimal repaymentTotal) {
		this.repaymentTotal = repaymentTotal;
	}
	public Date getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getBackmoney() {
		return backmoney;
	}
	public void setBackmoney(BigDecimal backmoney) {
		this.backmoney = backmoney;
	}
	public void setPayintentPeriod(Integer payintentPeriod) {
		this.payintentPeriod = payintentPeriod;
	}
	public Integer getPayintentPeriod() {
		return payintentPeriod;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setBackprincipal(BigDecimal backprincipal) {
		this.backprincipal = backprincipal;
	}
	public BigDecimal getBackprincipal() {
		return backprincipal;
	}
	public void setLoanInterest(BigDecimal loanInterest) {
		this.loanInterest = loanInterest;
	}
	public BigDecimal getLoanInterest() {
		return loanInterest;
	}
	public void setBackloanInterest(BigDecimal backloanInterest) {
		this.backloanInterest = backloanInterest;
	}
	public BigDecimal getBackloanInterest() {
		return backloanInterest;
	}
	public void setServiceMoney(BigDecimal serviceMoney) {
		this.serviceMoney = serviceMoney;
	}
	public BigDecimal getServiceMoney() {
		return serviceMoney;
	}
	public void setBackserviceMoney(BigDecimal backserviceMoney) {
		this.backserviceMoney = backserviceMoney;
	}
	public BigDecimal getBackserviceMoney() {
		return backserviceMoney;
	}
	public void setCompensationMoney(BigDecimal compensationMoney) {
		this.compensationMoney = compensationMoney;
	}
	public BigDecimal getCompensationMoney() {
		return compensationMoney;
	}
	public void setBackcompensationMoney(BigDecimal backcompensationMoney) {
		this.backcompensationMoney = backcompensationMoney;
	}
	public BigDecimal getBackcompensationMoney() {
		return backcompensationMoney;
	}
	public void setIntentDate(Date intentDate) {
		this.intentDate = intentDate;
	}
	public Date getIntentDate() {
		return intentDate;
	}
	public void setIntenttotal(BigDecimal intenttotal) {
		this.intenttotal = intenttotal;
	}
	public BigDecimal getIntenttotal() {
		return intenttotal;
	}
	public void setFactDate(Date factDate) {
		this.factDate = factDate;
	}
	public Date getFactDate() {
		return factDate;
	}
	public double getProgress() {
		return progress;
	}
	public void setProgress(double progress) {
		this.progress = progress;
	}
	public double getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(double afterMoney) {
		this.afterMoney = afterMoney;
	}
	public java.math.BigDecimal getYeaRate() {
		return yeaRate;
	}
	public void setYeaRate(java.math.BigDecimal yeaRate) {
		this.yeaRate = yeaRate;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getNowTimeStr() {
		return nowTimeStr;
	}
	public void setNowTimeStr(String nowTimeStr) {
		this.nowTimeStr = nowTimeStr;
	}
	public String getPreSaleTimeStr() {
		return preSaleTimeStr;
	}
	public void setPreSaleTimeStr(String preSaleTimeStr) {
		this.preSaleTimeStr = preSaleTimeStr;
	}
	public Date getBuyEndTime() {
		return buyEndTime;
	}
	public void setBuyEndTime(Date buyEndTime) {
		this.buyEndTime = buyEndTime;
	}
	
	
}
