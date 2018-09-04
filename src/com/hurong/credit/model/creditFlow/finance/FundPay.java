package com.hurong.credit.model.creditFlow.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 投资人的款项台账
 * @author ll
 *
 */
public class FundPay implements Serializable {
    private Long planId;
	private String projectName;
	private Date intentDate;
	private Date factDate;
	private BigDecimal principalRepaymentMoney;
	private BigDecimal loanInterestMoney;
	private BigDecimal punishInterestMoney;
	private BigDecimal comprehensiveMoney;
	private BigDecimal notMoney;
	private BigDecimal afterMoney;
	private BigDecimal incomeMoney;
	private Integer payintentPeriod;
	private String ids;
	private BigDecimal compensateMoney;//代偿金额
	
	private String url;//合同下载路径
	private BigDecimal factPaymentsMoney;//已回款
	private BigDecimal planPaymentsMoney;//剩余未支付的金额

	private String fundType ;
	
	/**
	 * 计划的本息(本金+投资利息)
	 */
	private BigDecimal payIntestPrinMoney;
	
	/**
	 * 未 偿还的本金和利息（本金+利息）
	 */
	private BigDecimal unPayIntestPrinMoney;
	/**
	 * 计划的奖励金额
	 */
	private BigDecimal couponMoney;
	
	/**
	 * 未支付的奖励金额
	 */
	private BigDecimal unCouponMoney;
	/**
	 * 计划的补偿息和罚息
	 */
	private BigDecimal compensationMoney;
	/**
	 * 未支付的补偿息和罚息
	 */
	private BigDecimal unCompensationMoney;
	/**
	 * 投资交易流水号
	 */
	private String orderNo;
	
    /**
     * 年化利率 plBidPlan
     */
    private BigDecimal interestRate;
    /**
     * 招标期限
     */
    private String loanLife;
    /**
     * 投标金额
     */
    private BigDecimal userMoney;
    /**
     * 投标时间
     */
    private Date bidtime;
    //加息利率
    private BigDecimal showRate;

	//标的结束时间
	private Date bidEndTime;

	//标的总额
	private BigDecimal bidMoney;

	public BigDecimal getBidMoney() {
		return bidMoney;
	}

	public BigDecimal getbAftermoney() {
		return bAftermoney;
	}

	public void setbAftermoney(BigDecimal bAftermoney) {
		this.bAftermoney = bAftermoney;
	}

	public Long getRemainingTime() {

		return remainingTime;
	}

	public void setRemainingTime(Long remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void setBidMoney(BigDecimal bidMoney) {

		this.bidMoney = bidMoney;
	}

	//标的剩余募集时间
	private Long remainingTime;

	//标的剩余可投金额
	private BigDecimal bAftermoney;

	public Date getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(Date bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	//是否为新手标   0为非新手标   1为新手标
	private Integer novice;
    //标的类型
	private String proKeepType;
	//标的状态
	private Integer state;

	public String getProKeepType() {
		return proKeepType;
	}

	public void setProKeepType(String proKeepType) {
		this.proKeepType = proKeepType;
	}

	public BigDecimal getShowRate() {
		return showRate;
	}

	public void setShowRate(BigDecimal showRate) {
		this.showRate = showRate;
	}

	public Integer getNovice() {
		return novice;
	}

	public void setNovice(Integer novice) {
		this.novice = novice;
	}

	/**

	 * 结清日期
	 */

   private Date finishDate;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public BigDecimal getFactPaymentsMoney() {
		return factPaymentsMoney;
	}
	public void setFactPaymentsMoney(BigDecimal factPaymentsMoney) {
		this.factPaymentsMoney = factPaymentsMoney;
	}
	public BigDecimal getPlanPaymentsMoney() {
		return planPaymentsMoney;
	}
	public void setPlanPaymentsMoney(BigDecimal planPaymentsMoney) {
		this.planPaymentsMoney = planPaymentsMoney;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getFactDate() {
		return factDate;
	}
	public void setFactDate(Date factDate) {
		this.factDate = factDate;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public BigDecimal getPrincipalRepaymentMoney() {
		return principalRepaymentMoney;
	}
	public void setPrincipalRepaymentMoney(BigDecimal principalRepaymentMoney) {
		this.principalRepaymentMoney = principalRepaymentMoney;
	}
	public BigDecimal getLoanInterestMoney() {
		return loanInterestMoney;
	}
	public void setLoanInterestMoney(BigDecimal loanInterestMoney) {
		this.loanInterestMoney = loanInterestMoney;
	}
	public BigDecimal getPunishInterestMoney() {
		return punishInterestMoney;
	}
	public void setPunishInterestMoney(BigDecimal punishInterestMoney) {
		this.punishInterestMoney = punishInterestMoney;
	}

	public BigDecimal getComprehensiveMoney() {
		return comprehensiveMoney;
	}
	public void setComprehensiveMoney(BigDecimal comprehensiveMoney) {
		this.comprehensiveMoney = comprehensiveMoney;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getIntentDate() {
		return intentDate;
	}
	public void setIntentDate(Date intentDate) {
		this.intentDate = intentDate;
	}

	public BigDecimal getAfterMoney() {
		return afterMoney;
	}
	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}
	public Integer getPayintentPeriod() {
		return payintentPeriod;
	}
	public void setPayintentPeriod(Integer payintentPeriod) {
		this.payintentPeriod = payintentPeriod;
	}
	public BigDecimal getNotMoney() {
		return notMoney;
	}
	public void setNotMoney(BigDecimal notMoney) {
		this.notMoney = notMoney;
	}
	public FundPay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundPay(String projectName, String fundType, Date intentDate,
			BigDecimal incomeMoney, BigDecimal afterMoney,
			Integer payintentPeriod, BigDecimal notMoney) {
		super();
		this.projectName = projectName;
		this.intentDate = intentDate;
		this.afterMoney = afterMoney;
		this.payintentPeriod = payintentPeriod;
		this.notMoney = notMoney;
	}
	public void setCompensateMoney(BigDecimal compensateMoney) {
		this.compensateMoney = compensateMoney;
	}
	public BigDecimal getCompensateMoney() {
		return compensateMoney;
	}
	public void setIncomeMoney(BigDecimal incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	public BigDecimal getIncomeMoney() {
		return incomeMoney;
	}
	public void setUnPayIntestPrinMoney(BigDecimal unPayIntestPrinMoney) {
		this.unPayIntestPrinMoney = unPayIntestPrinMoney;
	}
	public BigDecimal getUnPayIntestPrinMoney() {
		return unPayIntestPrinMoney;
	}
	public void setUnCouponMoney(BigDecimal unCouponMoney) {
		this.unCouponMoney = unCouponMoney;
	}
	public BigDecimal getUnCouponMoney() {
		return unCouponMoney;
	}
	public void setPayIntestPrinMoney(BigDecimal payIntestPrinMoney) {
		this.payIntestPrinMoney = payIntestPrinMoney;
	}
	public BigDecimal getPayIntestPrinMoney() {
		return payIntestPrinMoney;
	}
	public void setCouponMoney(BigDecimal couponMoney) {
		this.couponMoney = couponMoney;
	}
	public BigDecimal getCouponMoney() {
		return couponMoney;
	}
	public void setCompensationMoney(BigDecimal compensationMoney) {
		this.compensationMoney = compensationMoney;
	}
	public BigDecimal getCompensationMoney() {
		return compensationMoney;
	}
	public void setUnCompensationMoney(BigDecimal unCompensationMoney) {
		this.unCompensationMoney = unCompensationMoney;
	}
	public BigDecimal getUnCompensationMoney() {
		return unCompensationMoney;
	}
	public void setLoanLife(String loanLife) {
		this.loanLife = loanLife;
	}
	public String getLoanLife() {
		return loanLife;
	}
	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	public BigDecimal getInterestRate() {
		return interestRate;
	}
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}
	public Date getBidtime() {
		return bidtime;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
}
