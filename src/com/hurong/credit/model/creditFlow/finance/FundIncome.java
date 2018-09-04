package com.hurong.credit.model.creditFlow.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FundIncome implements Serializable {
    private Long fundIntentId;
    private Long planId;
	private String projectName;
	private String fundType;
	private Date intentDate;
	private Date factDate;
	private BigDecimal incomeMoney;
	private BigDecimal afterMoney;
	private Integer payintentPeriod;
	private BigDecimal notMoney;
	private BigDecimal payMoney;//保证金付款
	protected Long investId;
	protected Short repaySource;
	protected BigDecimal accrualMoney;
	private String orderNo;
	
	private Short authorizationStatus;//是否授权，1表示已经授权，null表示没有授权或者取消了授权
	
	private String bidProName;

	public Short getAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(Short authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	public String getBidProName() {
		return bidProName;
	}
	public void setBidProName(String bidProName) {
		this.bidProName = bidProName;
	}
	public Date getFactDate() {
		return factDate;
	}
	public void setFactDate(Date factDate) {
		this.factDate = factDate;
	}
	public BigDecimal getAccrualMoney() {
		return accrualMoney;
	}
	public void setAccrualMoney(BigDecimal accrualMoney) {
		this.accrualMoney = accrualMoney;
	}
	public Short getRepaySource() {
		return repaySource;
	}
	public void setRepaySource(Short repaySource) {
		this.repaySource = repaySource;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getInvestId() {
		return investId;
	}
	public void setInvestId(Long investId) {
		this.investId = investId;
	}
	public Long getFundIntentId() {
		return fundIntentId;
	}
	public void setFundIntentId(Long fundIntentId) {
		this.fundIntentId = fundIntentId;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
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
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public Date getIntentDate() {
		return intentDate;
	}
	public void setIntentDate(Date intentDate) {
		this.intentDate = intentDate;
	}
	public BigDecimal getIncomeMoney() {
		return incomeMoney;
	}
	public void setIncomeMoney(BigDecimal incomeMoney) {
		this.incomeMoney = incomeMoney;
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
	public FundIncome() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundIncome(String projectName, String fundType, Date intentDate,
			BigDecimal incomeMoney, BigDecimal afterMoney,
			Integer payintentPeriod, BigDecimal notMoney) {
		super();
		this.projectName = projectName;
		this.fundType = fundType;
		this.intentDate = intentDate;
		this.incomeMoney = incomeMoney;
		this.afterMoney = afterMoney;
		this.payintentPeriod = payintentPeriod;
		this.notMoney = notMoney;
	}
}
