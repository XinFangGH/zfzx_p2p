package com.hurong.credit.model.creditFlow.auto;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class PlBidAuto extends com.hurong.core.model.BaseModel{
    public static final String ISBANNED="1";
    public static final String MAXBIDMONEY="200";
    public static final int DIVIDEMONEY=200;
    public static final int TOTALMONEY=50;
    //自动投标开启状态
    public static final String OPEN="1";
  //利率下限
    public static final int ISTART=8;
  //利率上限
    public static final int IEND=24;
  //期限下限
    public static final int PSTART=0;
  //期限上限
    public static final int PEND=36;
	private Long id;
	private Long userID;
	private java.math.BigDecimal bidMoney;
	private Integer interestStart;
	private Integer interestEnd;
	private Integer periodStart;
	private Integer periodEnd;
	private String rateStart;
	private String rateEnd;
	private String rateStartShow;
	private String rateEndShow;
	private java.math.BigDecimal keepMoney;
	private Integer isOpen;
	private Integer banned;//是否禁用 0 禁用 1开启
	private java.util.Date orderTime;
	//第三方支付流水号
	private String requestNo;
	
	
	public String getRateStartShow() {
		return rateStartShow;
	}
	public void setRateStartShow(String rateStartShow) {
		this.rateStartShow = rateStartShow;
	}
	public String getRateEndShow() {
		return rateEndShow;
	}
	public void setRateEndShow(String rateEndShow) {
		this.rateEndShow = rateEndShow;
	}
	public Integer getBanned() {
		return banned;
	}
	public void setBanned(Integer banned) {
		this.banned = banned;
	}
	public java.util.Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public java.math.BigDecimal getBidMoney() {
		return bidMoney;
	}
	public void setBidMoney(java.math.BigDecimal bidMoney) {
		this.bidMoney = bidMoney;
	}
	public Integer getInterestStart() {
		return interestStart;
	}
	public void setInterestStart(Integer interestStart) {
		this.interestStart = interestStart;
	}
	public Integer getInterestEnd() {
		return interestEnd;
	}
	public void setInterestEnd(Integer interestEnd) {
		this.interestEnd = interestEnd;
	}
	public Integer getPeriodStart() {
		return periodStart;
	}
	public void setPeriodStart(Integer periodStart) {
		this.periodStart = periodStart;
	}
	public Integer getPeriodEnd() {
		return periodEnd;
	}
	public void setPeriodEnd(Integer periodEnd) {
		this.periodEnd = periodEnd;
	}
	public String getRateStart() {
		return rateStart;
	}
	public void setRateStart(String rateStart) {
		this.rateStart = rateStart;
	}
	public String getRateEnd() {
		return rateEnd;
	}
	public void setRateEnd(String rateEnd) {
		this.rateEnd = rateEnd;
	}
	public java.math.BigDecimal getKeepMoney() {
		return keepMoney;
	}
	public void setKeepMoney(java.math.BigDecimal keepMoney) {
		this.keepMoney = keepMoney;
	}
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	public PlBidAuto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlBidAuto(Long id, Long userID, BigDecimal bidMoney,
			Integer interestStart, Integer interestEnd, Integer periodStart,
			Integer periodEnd, String rateStart, String rateEnd,
			BigDecimal keepMoney, Integer isOpen, Date orderTime) {
		super();
		this.id = id;
		this.userID = userID;
		this.bidMoney = bidMoney;
		this.interestStart = interestStart;
		this.interestEnd = interestEnd;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
		this.rateStart = rateStart;
		this.rateEnd = rateEnd;
		this.keepMoney = keepMoney;
		this.isOpen = isOpen;
		this.orderTime = orderTime;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getRequestNo() {
		return requestNo;
	}
	
}
