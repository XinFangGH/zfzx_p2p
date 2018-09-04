package com.thirdPayInterface.YeePay;

public class Extend {
	private String tenderOrderNo;//项目编号
	private String tenderName;//项目名称
	private String tenderAmount;//项目金额
	private String tenderDescription;//项目描述
	private String borrowerPlatformUserNo;//项目的借款人平台用户编号
	private String tenderSumLimit;//累计投标金额限制，如果此参数不为空，则项目累计已投金额+本次投标金额不能超过此参数
	public String getTenderOrderNo() {
		return tenderOrderNo;
	}
	public void setTenderOrderNo(String tenderOrderNo) {
		this.tenderOrderNo = tenderOrderNo;
	}
	public String getTenderName() {
		return tenderName;
	}
	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}
	public String getTenderAmount() {
		return tenderAmount;
	}
	public void setTenderAmount(String tenderAmount) {
		this.tenderAmount = tenderAmount;
	}
	public String getTenderDescription() {
		return tenderDescription;
	}
	public void setTenderDescription(String tenderDescription) {
		this.tenderDescription = tenderDescription;
	}
	public String getBorrowerPlatformUserNo() {
		return borrowerPlatformUserNo;
	}
	public void setBorrowerPlatformUserNo(String borrowerPlatformUserNo) {
		this.borrowerPlatformUserNo = borrowerPlatformUserNo;
	}
	public String getTenderSumLimit() {
		return tenderSumLimit;
	}
	public void setTenderSumLimit(String tenderSumLimit) {
		this.tenderSumLimit = tenderSumLimit;
	}
	
}
