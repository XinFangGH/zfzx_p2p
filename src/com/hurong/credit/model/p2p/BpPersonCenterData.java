package com.hurong.credit.model.p2p;

import java.math.BigDecimal;

public class BpPersonCenterData {
	
	private BigDecimal allBenefit;//累计赚取收益
	private Integer allBidTimes;//累计投资笔数
	private BigDecimal allBidMoney;//累计投资金额
	
	/**
	 * 我的理财
	 */
	private BigDecimal unbackRepayment;//应派本金
	private BigDecimal unbackInterest;//应派利息
	

	/**
	 * 批量债权
	 * @return
	 */
	private Integer unPublic;//批量债权中的理财数据(预售中)
	private Integer bidingCount;//招标中的理财数据
	private Integer bidFullCount; //已齐标的理财数据
	private Integer backingCount;//回款中的数量
	private Integer complateCount;//已完成的数量
	
	
	
	/**
	 * 债权交易
	 * @return
	 */
	public BigDecimal canTransferSale;//可交易债权
	public BigDecimal transferedMoney;//已交易的债权金额
	public Integer successDeals;//成功交易的笔数
	
	/**
	 * 我的借款
	 * @return
	 */
	public Integer allLoanNums;//总共借款笔数
	public Integer numsOnBiding;//招标中的笔数
	public Integer unBackNums;//代还款笔数
	public BigDecimal allLoanMoney;//累计借款金额
	public BigDecimal moneyOnBiding;//招标中的金额
	public BigDecimal unBackMoney;//代还款金额
	
	/**
	 * 消息记录
	 * @return
	 */
	public Integer readNums;//已读条数
	public Integer notReadNums;//未读条数
	
	
	
	
	/**
	 * 我的积分
	 * @return
	 */
	public Integer useRecordNumber;//已使用积分
	public Integer totalRecordNumber;//累计积分
	public Integer canUseNumber;//可用积分
	public Integer allRecommend;
	

	/*
	 *活期理财 
	 * */
	public BigDecimal nearlyMoney;//近一个月赚取
	public BigDecimal earningsYesterday;//昨日收益
	public BigDecimal accountBalance;//账户余额
	public BigDecimal accumulatedEarnings;//累计收益
	public BigDecimal leviticusInterestMoney;//尚未记息金额
	/*
	 * 账户总览
	 * */
	public Integer coupons;//优惠券
	
	
	
	public Integer monthRecommend;
	public Integer getMonthRecommend() {
		return monthRecommend;
	}
	public void setMonthRecommend(Integer monthRecommend) {
		this.monthRecommend = monthRecommend;
	}
	public Integer getAllRecommend() {
		return allRecommend;
	}
	public void setAllRecommend(Integer allRecommend) {
		this.allRecommend = allRecommend;
	}
	public Integer getCanUseNumber() {
		return canUseNumber;
	}
	public void setCanUseNumber(Integer canUseNumber) {
		this.canUseNumber = canUseNumber;
	}
	public Integer getUseRecordNumber(){
		return useRecordNumber;
	}
	public void setUseRecordNumber(Integer useRecordNumber) {
		this.useRecordNumber = useRecordNumber;
	}
	public Integer getTotalRecordNumber() {
		return totalRecordNumber;
	}
	public void setTotalRecordNumber(Integer totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}
	public Integer getReadNums() {
		return readNums;
	}
	public void setReadNums(Integer readNums) {
		this.readNums = readNums;
	}
	public Integer getNotReadNums() {
		return notReadNums;
	}
	public void setNotReadNums(Integer notReadNums) {
		this.notReadNums = notReadNums;
	}
	public Integer getAllLoanNums() {
		return allLoanNums;
	}
	public void setAllLoanNums(Integer allLoanNums) {
		this.allLoanNums = allLoanNums;
	}
	public Integer getNumsOnBiding() {
		return numsOnBiding;
	}
	public void setNumsOnBiding(Integer numsOnBiding) {
		this.numsOnBiding = numsOnBiding;
	}
	public Integer getUnBackNums() {
		return unBackNums;
	}
	public void setUnBackNums(Integer unBackNums) {
		this.unBackNums = unBackNums;
	}
	public BigDecimal getAllLoanMoney() {
		return allLoanMoney;
	}
	public void setAllLoanMoney(BigDecimal allLoanMoney) {
		this.allLoanMoney = allLoanMoney;
	}
	public BigDecimal getMoneyOnBiding() {
		return moneyOnBiding;
	}
	public void setMoneyOnBiding(BigDecimal moneyOnBiding) {
		this.moneyOnBiding = moneyOnBiding;
	}
	public BigDecimal getUnBackMoney() {
		return unBackMoney;
	}
	public void setUnBackMoney(BigDecimal unBackMoney) {
		this.unBackMoney = unBackMoney;
	}
	public BigDecimal getCanTransferSale() {
		return canTransferSale;
	}
	public void setCanTransferSale(BigDecimal canTransferSale) {
		this.canTransferSale = canTransferSale;
	}
	public BigDecimal getTransferedMoney() {
		return transferedMoney;
	}
	public void setTransferedMoney(BigDecimal transferedMoney) {
		this.transferedMoney = transferedMoney;
	}
	public Integer getSuccessDeals() {
		return successDeals;
	}
	public void setSuccessDeals(Integer successDeals) {
		this.successDeals = successDeals;
	}
	public Integer getUnPublic() {
		return unPublic;
	}
	public void setUnPublic(Integer unPublic) {
		this.unPublic = unPublic;
	}
	public Integer getBidingCount() {
		return bidingCount;
	}
	public void setBidingCount(Integer bidingCount) {
		this.bidingCount = bidingCount;
	}
	public Integer getBidFullCount() {
		return bidFullCount;
	}
	public void setBidFullCount(Integer bidFullCount) {
		this.bidFullCount = bidFullCount;
	}
	public Integer getBackingCount() {
		return backingCount;
	}
	public void setBackingCount(Integer backingCount) {
		this.backingCount = backingCount;
	}
	public Integer getComplateCount() {
		return complateCount;
	}
	public void setComplateCount(Integer complateCount) {
		this.complateCount = complateCount;
	}
	public BigDecimal getUnbackRepayment() {
		return unbackRepayment;
	}
	public void setUnbackRepayment(BigDecimal unbackRepayment) {
		this.unbackRepayment = unbackRepayment;
	}
	public BigDecimal getUnbackInterest() {
		return unbackInterest;
	}
	public void setUnbackInterest(BigDecimal unbackInterest) {
		this.unbackInterest = unbackInterest;
	}
	public BigDecimal getAllBenefit() {
		return allBenefit;
	}
	public void setAllBenefit(BigDecimal allBenefit) {
		this.allBenefit = allBenefit;
	}
	public Integer getAllBidTimes() {
		return allBidTimes;
	}
	public void setAllBidTimes(Integer allBidTimes) {
		this.allBidTimes = allBidTimes;
	}
	public BigDecimal getAllBidMoney() {
		return allBidMoney;
	}
	public void setAllBidMoney(BigDecimal allBidMoney) {
		this.allBidMoney = allBidMoney;
	}
	public BigDecimal getNearlyMoney() {
		return nearlyMoney;
	}
	public void setNearlyMoney(BigDecimal nearlyMoney) {
		this.nearlyMoney = nearlyMoney;
	}
	public BigDecimal getEarningsYesterday() {
		return earningsYesterday;
	}
	public void setEarningsYesterday(BigDecimal earningsYesterday) {
		this.earningsYesterday = earningsYesterday;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public BigDecimal getAccumulatedEarnings() {
		return accumulatedEarnings;
	}
	public void setAccumulatedEarnings(BigDecimal accumulatedEarnings) {
		this.accumulatedEarnings = accumulatedEarnings;
	}
	public BigDecimal getLeviticusInterestMoney() {
		return leviticusInterestMoney;
	}
	public void setLeviticusInterestMoney(BigDecimal leviticusInterestMoney) {
		this.leviticusInterestMoney = leviticusInterestMoney;
	}
	public Integer getCoupons() {
		return coupons;
	}
	public void setCoupons(Integer coupons) {
		this.coupons = coupons;
	}
	
}
