package com.hurong.credit.model.creditFlow.finance;

import java.util.Date;

import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;

public class BpFundIntent extends FundIntent {//这种设计不符合继承原则

	/**
	 * fundType:
	 *  loanInterest:贷款利息
	 *  principalRepayment : 本金--还款
	 *  principalLending ： 本金 -- 放款
	 *  consultationMoney ： 咨询管理费
	 *  serviceMoney ： 财务服务费用
	 *  backInterest ： 费用退回
	 *  riskRate ： 风险保证金
	 *  interestPenalty ： 提前还款违约金/罚息
	 *  couponInterest ： 优惠券利息
	 *  principalCoupons ： 优惠卷返息
	 *  subjoinInterest ： 加息卷利息
	 *  commoninterest ： 普通加息
	 *  raiseinterest ： 募集期利息
	 *  
	 */
	
	
	/**
	 * 用来进行投资人款项对账的资金来源
	 * 1 正常放款，还款
	 * 2 还款时由平台代偿
	 * 3还款由担保公司代偿（暂时没有用）
	 */
	public static final Short REPAYSOURCE1=1;//1 正常放款，还款
	public static final Short REPAYSOURCE2=2;//2 还款时由平台代偿
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356794928998267625L;
	
	private String orderNo;
	
	protected Long bidPlanId;

	protected String projectName;//投资项目名称---
	
	protected PlBidPlan plan;

	/**
	 * 交易流水号（针对联动优势第三方新增字段）
	 * 主要是为了记录融资方或代偿方对资金偿还的流水号
	 */
	protected String requestNOLoaner;
	/**
	 * 第三方交易流水号（针对放款，还款，派息，操作记录字段）
	 */
	protected String requestNo;
	/**
	 * 最新请求第三方交易时间
	 */
	protected Date requestDate;
	/**
	 * 请求第三方交易次数
	 */
	protected Integer requestCount=0;

	/**
	 * 用来进行对账的资金来源
	 */
	protected Short repaySource;
	
	/**
	 * 还款时间
	 */
	protected Date returnDate;
	
    /**
     * 用来标注还款成功返款失败的情况
     *   主要针对于联动优势对于还款要调两次接口 
     */
	protected Integer loanerRepayMentStatus;//还款成功 1 

	
	
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public Integer getLoanerRepayMentStatus() {
		return loanerRepayMentStatus;
	}

	public void setLoanerRepayMentStatus(Integer loanerRepayMentStatus) {
		this.loanerRepayMentStatus = loanerRepayMentStatus;
	}

	public String getRequestNOLoaner() {
		return requestNOLoaner;
	}

	public void setRequestNOLoaner(String requestNOLoaner) {
		this.requestNOLoaner = requestNOLoaner;
	}

	public Integer getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}
	
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Short getRepaySource() {
		return repaySource;
	}

	public void setRepaySource(Short repaySource) {
		this.repaySource = repaySource;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getBidPlanId() {
		return bidPlanId;
	}

	public void setBidPlanId(Long bidPlanId) {
		this.bidPlanId = bidPlanId;
	}

	public PlBidPlan getPlan() {
		return plan;
	}

	public void setPlan(PlBidPlan plan) {
		this.plan = plan;
	}

	
}
