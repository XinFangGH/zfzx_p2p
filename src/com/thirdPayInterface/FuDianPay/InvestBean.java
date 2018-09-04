package com.thirdPayInterface.FuDianPay;

/**
 * 投资记录
 */
public class InvestBean {

	private String investUserName;//原有标的，投资人的存管账户用户名   
	private String investAccountNo;//原有标的，投资时使用的订单日期
	private String capital;//本金
	private String interest;//利息
	private String rateInterest;//加息利息
	private String interestFee;//利息管理费
	private String orderNo;//当前交易的订单号，在回款记录中唯一性
	private String orderDate;//当前交易订单日期，格式yyyyMMdd
	private String investOrderNo;// 原有投资订单号
	private String investOrderDate;// 原有投资订单日期

	public String getInvestUserName() {
		return investUserName;
	}

	public void setInvestUserName(String investUserName) {
		this.investUserName = investUserName;
	}

	public String getInvestAccountNo() {
		return investAccountNo;
	}

	public void setInvestAccountNo(String investAccountNo) {
		this.investAccountNo = investAccountNo;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getInterestFee() {
		return interestFee;
	}

	public void setInterestFee(String interestFee) {
		this.interestFee = interestFee;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getInvestOrderNo() {
		return investOrderNo;
	}

	public void setInvestOrderNo(String investOrderNo) {
		this.investOrderNo = investOrderNo;
	}

	public String getInvestOrderDate() {
		return investOrderDate;
	}

	public void setInvestOrderDate(String investOrderDate) {
		this.investOrderDate = investOrderDate;
	}

	public String getRateInterest() {
		return rateInterest;
	}

	public void setRateInterest(String rateInterest) {
		this.rateInterest = rateInterest;
	}
	
}
