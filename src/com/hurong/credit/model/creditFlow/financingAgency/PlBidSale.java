package com.hurong.credit.model.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * PlBidSale Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 债权挂牌转让表
 */
public class PlBidSale extends com.hurong.core.model.BaseModel {

    protected Long id;
	protected Long oldCustID;
	protected Long outCustID;
	protected Long inCustID;
	protected Long bidPlanID;
	protected Long bidInfoID;
	protected java.util.Date saleStartingTime;
	protected java.util.Date saleStartTime;
	protected java.util.Date saleCloseTime;
	protected java.util.Date saleDealTime;
	protected java.util.Date saleSuccessTime;
	protected java.math.BigDecimal saleMoney;
	protected java.math.BigDecimal beginMoney;
	protected java.math.BigDecimal changeMoney;
	protected Integer changeMoneyRate;
	protected Short changeMoneyType;
	protected Short saleStatus; //0，挂牌中；1挂牌成功 3，受让人转帐中；4，交易成功；7生成到债权库了  9，交易手动关闭；10，交易过 期；
	protected java.math.BigDecimal sumMoney;
	protected java.math.BigDecimal transferFee;
	protected java.math.BigDecimal preTransferFee;
	protected String preTransferFeeRequestNo;//预收费流水号
	protected Short preTransferFeeStatus;
	protected String preAuthorizationNum; //富友，预授权合同号，双乾转账审核流水号
	/**
     * 是否为vip  0 否 1是
     * @return
     */
    protected Short isVip;
	
	public Short getIsVip() {
		return isVip;
	}

	public void setIsVip(Short isVip) {
		this.isVip = isVip;
	}

	public String getPreAuthorizationNum() {
		return preAuthorizationNum;
	}

	public void setPreAuthorizationNum(String preAuthorizationNum) {
		this.preAuthorizationNum = preAuthorizationNum;
	}

	public Short getPreTransferFeeStatus() {
		return preTransferFeeStatus;
	}

	public void setPreTransferFeeStatus(Short preTransferFeeStatus) {
		this.preTransferFeeStatus = preTransferFeeStatus;
	}

	protected String newOrderNo;//债权交易新债权人的流水号
	protected String oldCustName;
	protected String outCustName;
	protected String inCustName;
	
	
  //数据库没有的
	protected String orderNo;
	protected BigDecimal yearAccrualRate;
	protected java.util.Date startDate;
	protected java.util.Date intentDate;
	protected String bidProName;
	protected String accrualtype;  //计息方式
	protected java.util.Date nextPayDate;
	protected String proKeepType;
	
	
	/**
	 * 债权最原始金额
	 */
	protected BigDecimal userMoney;
	/**
	 * 债权剩余天数
	 */
	protected Integer days;
	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}

	public Integer getDays() {
		return days;
	}

	/**
	 * 债权到期时间
	 */
	protected Date endIntentDate;
	
	/**
	 * 债权编号（出让债权人的购买编号）
	 */
	protected String saleNumber;
	/**
	 * 债权已收回本金
	 */
	protected BigDecimal factbackMoney;
	
	/**
	 * 债权未收回本金
	 */
	protected BigDecimal factMoney;
	
	/**
	 * 债权总收益
	 */
	protected BigDecimal totalInterest;
	/**
	 * 债权已收回利息
	 */
	protected BigDecimal interestBackMoney;
	/**
	 * 债权未付利息
	 */
	protected BigDecimal interestMoney;
	
	/**
	 * 债权欠支付利息罚息
	 */
	protected BigDecimal accrualMoney;
	
	public void setDays(Integer days) {
		this.days = days;
	}

	public Date getEndIntentDate() {
		return endIntentDate;
	}

	public void setEndIntentDate(Date endIntentDate) {
		this.endIntentDate = endIntentDate;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public BigDecimal getFactbackMoney() {
		return factbackMoney;
	}

	public void setFactbackMoney(BigDecimal factbackMoney) {
		this.factbackMoney = factbackMoney;
	}

	public BigDecimal getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(BigDecimal factMoney) {
		this.factMoney = factMoney;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}

	public BigDecimal getInterestBackMoney() {
		return interestBackMoney;
	}

	public void setInterestBackMoney(BigDecimal interestBackMoney) {
		this.interestBackMoney = interestBackMoney;
	}

	public BigDecimal getInterestMoney() {
		return interestMoney;
	}

	public void setInterestMoney(BigDecimal interestMoney) {
		this.interestMoney = interestMoney;
	}

	public BigDecimal getAccrualMoney() {
		return accrualMoney;
	}

	public void setAccrualMoney(BigDecimal accrualMoney) {
		this.accrualMoney = accrualMoney;
	}

	
	
	
	
	
	public String getPreTransferFeeRequestNo() {
		return preTransferFeeRequestNo;
	}

	public void setPreTransferFeeRequestNo(String preTransferFeeRequestNo) {
		this.preTransferFeeRequestNo = preTransferFeeRequestNo;
	}
	public BigDecimal getYearAccrualRate() {
		return yearAccrualRate;
	}

	public void setYearAccrualRate(BigDecimal yearAccrualRate) {
		this.yearAccrualRate = yearAccrualRate;
	}

	public java.math.BigDecimal getPreTransferFee() {
		return preTransferFee;
	}

	public void setPreTransferFee(java.math.BigDecimal preTransferFee) {
		this.preTransferFee = preTransferFee;
	}

	public java.util.Date getSaleStartingTime() {
		return saleStartingTime;
	}

	public void setSaleStartingTime(java.util.Date saleStartingTime) {
		this.saleStartingTime = saleStartingTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getNewOrderNo() {
		return newOrderNo;
	}

	public void setNewOrderNo(String newOrderNo) {
		this.newOrderNo = newOrderNo;
	}

	public java.util.Date getNextPayDate() {
		return nextPayDate;
	}

	public String getOutCustName() {
		return outCustName;
	}

	public void setOutCustName(String outCustName) {
		this.outCustName = outCustName;
	}

	public String getOldCustName() {
		return oldCustName;
	}

	public void setOldCustName(String oldCustName) {
		this.oldCustName = oldCustName;
	}

	public String getInCustName() {
		return inCustName;
	}

	public void setInCustName(String inCustName) {
		this.inCustName = inCustName;
	}

	public void setNextPayDate(java.util.Date nextPayDate) {
		this.nextPayDate = nextPayDate;
	}

	public String getAccrualtype() {
		return accrualtype;
	}

	public void setAccrualtype(String accrualtype) {
		this.accrualtype = accrualtype;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getIntentDate() {
		return intentDate;
	}

	public void setIntentDate(java.util.Date intentDate) {
		this.intentDate = intentDate;
	}

	public String getBidProName() {
		return bidProName;
	}

	public void setBidProName(String bidProName) {
		this.bidProName = bidProName;
	}

	/**
	 * Default Empty Constructor for class PlBidSale
	 */
	public PlBidSale () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PlBidSale
	 */
	public PlBidSale (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * id	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 原始债权人在注册用户表中的ID	 * @return Long
	 * @hibernate.property column="oldCustID" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getOldCustID() {
		return this.oldCustID;
	}
	
	/**
	 * Set the oldCustID
	 * @spring.validator type="required"
	 */	
	public void setOldCustID(Long aValue) {
		this.oldCustID = aValue;
	}	

	/**
	 * 本次交易出让人在注册用户表中的ID	 * @return Long
	 * @hibernate.property column="outCustID" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getOutCustID() {
		return this.outCustID;
	}
	
	/**
	 * Set the outCustID
	 * @spring.validator type="required"
	 */	
	public void setOutCustID(Long aValue) {
		this.outCustID = aValue;
	}	

	/**
	 * 受让人注册用户表ID	 * @return Long
	 * @hibernate.property column="inCustID" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getInCustID() {
		return this.inCustID;
	}
	
	/**
	 * Set the inCustID
	 * @spring.validator type="required"
	 */	
	public void setInCustID(Long aValue) {
		this.inCustID = aValue;
	}	

	/**
	 * 转让的债权在pl_bid_plan表中的ID	 * @return Long
	 * @hibernate.property column="bidPlanID" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getBidPlanID() {
		return this.bidPlanID;
	}
	
	/**
	 * Set the bidPlanID
	 */	
	public void setBidPlanID(Long aValue) {
		this.bidPlanID = aValue;
	}	

	/**
	 * 转让的债权在pl_bid_plan表中的ID	 * @return Long
	 * @hibernate.property column="bidInfoID" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getBidInfoID() {
		return this.bidInfoID;
	}
	
	/**
	 * Set the bidInfoID
	 * @spring.validator type="required"
	 */	
	public void setBidInfoID(Long aValue) {
		this.bidInfoID = aValue;
	}	

	/**
	 * 挂牌交易正式开始时间	 * @return java.util.Date
	 * @hibernate.property column="saleStartTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSaleStartTime() {
		return this.saleStartTime;
	}
	
	/**
	 * Set the saleStartTime
	 */	
	public void setSaleStartTime(java.util.Date aValue) {
		this.saleStartTime = aValue;
	}	

	/**
	 * 手动关闭挂牌的操作时间	 * @return java.util.Date
	 * @hibernate.property column="saleCloseTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSaleCloseTime() {
		return this.saleCloseTime;
	}
	
	/**
	 * Set the saleCloseTime
	 */	
	public void setSaleCloseTime(java.util.Date aValue) {
		this.saleCloseTime = aValue;
	}	

	/**
	 * 受让人开始摘牌时间	 * @return java.util.Date
	 * @hibernate.property column="saleDealTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSaleDealTime() {
		return this.saleDealTime;
	}
	
	/**
	 * Set the saleDealTime
	 */	
	public void setSaleDealTime(java.util.Date aValue) {
		this.saleDealTime = aValue;
	}	

	/**
	 * 受让人成功摘牌时间	 * @return java.util.Date
	 * @hibernate.property column="saleSuccessTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSaleSuccessTime() {
		return this.saleSuccessTime;
	}
	
	/**
	 * Set the saleSuccessTime
	 */	
	public void setSaleSuccessTime(java.util.Date aValue) {
		this.saleSuccessTime = aValue;
	}	

	/**
	 * 剩余可转让本金	 * @return java.math.BigDecimal
	 * @hibernate.property column="saleMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSaleMoney() {
		return this.saleMoney;
	}
	
	/**
	 * Set the saleMoney
	 */	
	public void setSaleMoney(java.math.BigDecimal aValue) {
		this.saleMoney = aValue;
		if(this.changeMoneyRate!=null&&this.changeMoneyType!=null&&this.saleMoney!=null){
			this.changeMoney=this.saleMoney.multiply(new BigDecimal(this.changeMoneyRate)).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP);
			
		}
	}	

	/**
	 * 挂牌时剩余可转让本金	 * @return java.math.BigDecimal
	 * @hibernate.property column="beginMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBeginMoney() {
		return this.beginMoney;
	}
	
	/**
	 * Set the beginMoney
	 */	
	public void setBeginMoney(java.math.BigDecimal aValue) {
		this.beginMoney = aValue;
	}	

	/**
	 * 折价/加价金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="changeMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getChangeMoney() {
		return this.changeMoney;
	}
	
	/**
	 * Set the changeMoney
	 */	
	public void setChangeMoney(java.math.BigDecimal aValue) {
		this.changeMoney = aValue;
	}	

	/**
	 * 折价率	 * @return Integer
	 * @hibernate.property column="changeMoneyRate" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getChangeMoneyRate() {
		
		return this.changeMoneyRate;
	}
	
	/**
	 * Set the changeMoneyRate
	 */	
	public void setChangeMoneyRate(Integer aValue) {
		this.changeMoneyRate = aValue;
		
	}	

	/**
	 * 加价/折价类型	 * @return Short
	 * @hibernate.property column="changeMoneyType" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getChangeMoneyType() {
		return this.changeMoneyType;
	}
	
	/**
	 * Set the changeMoneyType
	 */	
	public void setChangeMoneyType(Short aValue) {
		this.changeMoneyType = aValue;
	}	

	/**
	 * 挂牌状态	 * @return Short
	 * @hibernate.property column="saleStatus" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getSaleStatus() {
		return this.saleStatus;
	}
	
	/**
	 * Set the saleStatus
	 */	
	public void setSaleStatus(Short aValue) {
		this.saleStatus = aValue;
	}	

	/**
	 * 交易完成时的总结款金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="sumMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSumMoney() {
		return this.sumMoney;
	}
	
	/**
	 * Set the sumMoney
	 */	
	public void setSumMoney(java.math.BigDecimal aValue) {
		this.sumMoney = aValue;
	}	

	/**
	 * 转让服务费	 * @return java.math.BigDecimal
	 * @hibernate.property column="transferFee" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getTransferFee() {
		return this.transferFee;
	}
	
	/**
	 * Set the transferFee
	 */	
	public void setTransferFee(java.math.BigDecimal aValue) {
		this.transferFee = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PlBidSale)) {
			return false;
		}
		PlBidSale rhs = (PlBidSale) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.oldCustID, rhs.oldCustID)
				.append(this.outCustID, rhs.outCustID)
				.append(this.inCustID, rhs.inCustID)
				.append(this.bidPlanID, rhs.bidPlanID)
				.append(this.bidInfoID, rhs.bidInfoID)
				.append(this.saleStartTime, rhs.saleStartTime)
				.append(this.saleCloseTime, rhs.saleCloseTime)
				.append(this.saleDealTime, rhs.saleDealTime)
				.append(this.saleSuccessTime, rhs.saleSuccessTime)
				.append(this.saleMoney, rhs.saleMoney)
				.append(this.beginMoney, rhs.beginMoney)
				.append(this.changeMoney, rhs.changeMoney)
				.append(this.changeMoneyRate, rhs.changeMoneyRate)
				.append(this.changeMoneyType, rhs.changeMoneyType)
				.append(this.saleStatus, rhs.saleStatus)
				.append(this.sumMoney, rhs.sumMoney)
				.append(this.transferFee, rhs.transferFee)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.oldCustID) 
				.append(this.outCustID) 
				.append(this.inCustID) 
				.append(this.bidPlanID) 
				.append(this.bidInfoID) 
				.append(this.saleStartTime) 
				.append(this.saleCloseTime) 
				.append(this.saleDealTime) 
				.append(this.saleSuccessTime) 
				.append(this.saleMoney) 
				.append(this.beginMoney) 
				.append(this.changeMoney) 
				.append(this.changeMoneyRate) 
				.append(this.changeMoneyType) 
				.append(this.saleStatus) 
				.append(this.sumMoney) 
				.append(this.transferFee) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("oldCustID", this.oldCustID) 
				.append("outCustID", this.outCustID) 
				.append("inCustID", this.inCustID) 
				.append("bidPlanID", this.bidPlanID) 
				.append("bidInfoID", this.bidInfoID) 
				.append("saleStartTime", this.saleStartTime) 
				.append("saleCloseTime", this.saleCloseTime) 
				.append("saleDealTime", this.saleDealTime) 
				.append("saleSuccessTime", this.saleSuccessTime) 
				.append("saleMoney", this.saleMoney) 
				.append("beginMoney", this.beginMoney) 
				.append("changeMoney", this.changeMoney) 
				.append("changeMoneyRate", this.changeMoneyRate) 
				.append("changeMoneyType", this.changeMoneyType) 
				.append("saleStatus", this.saleStatus) 
				.append("sumMoney", this.sumMoney) 
				.append("transferFee", this.transferFee) 
				.toString();
	}

	public String getProKeepType() {
		return proKeepType;
	}

	public void setProKeepType(String proKeepType) {
		this.proKeepType = proKeepType;
	}




}
