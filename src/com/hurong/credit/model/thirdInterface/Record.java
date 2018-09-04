package com.hurong.credit.model.thirdInterface;

public class Record {
	/**
	 * 投资金额
	 */
	private String paymentAmount ;
	/**
	 * 投资人 
	 */
	private String sourceUserNo;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 放款时间
	 */
	private String loanTime;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 还款金额
	 */
	private String repaymentAmount;
	/**
	 * 原投资人
	 */
	private String targetUserNo;
	/**
	 * 充值、取现金额
	 */
	private String amount ;
	/**
	 * 充值取现用户
	 */
	private String userNo ;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(String loanTime) {
		this.loanTime = loanTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRepaymentAmount() {
		return repaymentAmount;
	}
	public void setRepaymentAmount(String repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}
	public String getTargetUserNo() {
		return targetUserNo;
	}
	public void setTargetUserNo(String targetUserNo) {
		this.targetUserNo = targetUserNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setSourceUserNo(String sourceUserNo) {
		this.sourceUserNo = sourceUserNo;
	}
	public String getSourceUserNo() {
		return sourceUserNo;
	}
	
	

}
