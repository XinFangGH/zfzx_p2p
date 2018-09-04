package com.hurong.credit.model.financingAgency.manageMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author 
 *
 */
/**
 * PlManageMoneyPlan Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 理财计划/产品库
 */
public class PlManageMoneyPlan extends com.zhiwei.core.model.BaseModel {
	/*
	 * 未发标
	 */
	public static final int STATE0=0;
	/*
	 * 招标中
	 */
	public static final int STATE1=1;
	/*
	 * 已齐标
	 */
	public static final int STATE2=2;
/*	
	 * 已流标
	 
	public static final int STATE3=3;
	
	 * 关闭状态
	 
	public static final int STATE4=4;*/
	/*
	 * 已流标
	 */
	public static final int STATE3=-2;// 由原来的 3改为 -2
	/*
	 * 关闭状态
	 */
	public static final int STATE4=-1; // 由原来的 4改为 -1
	/*
	 * 已起息
	 */
	public static final int STATE7=7;
	
	/**
	 * 完成
	 */
	public static final int STATE10=10;
	
    protected Long mmplanId;//理财计划id
	protected String mmName;//理财计划名称
	protected String mmNumber;//理财计划编号
	protected Long manageMoneyTypeId;//理财计划类别id
	protected String keystr;//标识 mmplan(D计划),mmproduce(理财产品),UPlan(U计划)
	protected String investScope;//投资范围
	protected String benefitWay;//收益方式
	protected java.util.Date buyStartTime;//购买开放时间
	protected java.util.Date buyEndTime;//购买结束时间
	protected java.math.BigDecimal startMoney;//投资起点金额
	protected java.math.BigDecimal riseMoney;//递增金额
	protected java.math.BigDecimal limitMoney;//单笔投资上限
	protected String startinInterestCondition;//起息模式
	protected String expireRedemptionWay;//到期赎回方式
	protected String chargeGetway;//费用收取方式
	protected String guaranteeWay;//保障方式
	protected java.math.BigDecimal yeaRate;//年化收益率
	protected java.math.BigDecimal maxYearRate; //最大年化收益率
	protected Integer investlimit;//投资期限
	protected java.math.BigDecimal sumMoney;//累计投资额
	protected Integer state;
	protected Integer isCyclingLend;//是否循环出借
	protected Integer isOne;
	protected java.util.Date startinInterestTime;//计息起日
	protected java.util.Date endinInterestTime;//计息止日
	protected java.math.BigDecimal investedMoney;
	protected String bidRemark;//产品说明
	protected String htmlPath;
	protected java.util.Date createtime;
	protected java.util.Date updatetime;
	protected java.util.Set plManageMoneyPlanBuyinfos = new java.util.HashSet();
	
	protected Integer lockingLimit; //锁定期
	protected java.util.Date lockingEndDate;
	protected String moneyReceiver; //收款人
	protected java.math.BigDecimal earlierOutRate; //提前退出费率
	
	protected Integer plmmStartTime;//体验标开放投标时间
	protected java.math.BigDecimal  monthRate;//体验标月化利率
	protected java.math.BigDecimal  dayRate;//体验标日化利率
	@Expose
	 protected BigDecimal addRate;//加息利率
	 @Expose
	 protected BigDecimal returnRatio;//面值折现比
	 @Expose
	 protected Integer coupon;//是否可用优惠券   1是，0否
	 @Expose
	 protected Integer novice;//是否新手专享   1是，0否
	 
	 @Expose
	 protected BigDecimal raiseRate;//募集期利率
	 @Expose
	 protected BigDecimal maxCouponMoney;//单笔最大面值
	 @Expose
	 protected Integer rebateType;//返利类型 1=返现 ，2=返息，3=返息现，4=加息
	 @Expose
	 protected Integer rebateWay;//返利方式 1=立返 ，2=随期，3=到期
	
	protected java.math.BigDecimal joinRate;//加入费率
	
	 /**
	  * 预售开放时间
	  */
	 protected java.util.Date preSaleTime;
	 /**
	  * 收款类型
	  */
	 protected String receiverType;
	
	//数据没有
	protected java.math.BigDecimal afterMoney;
	protected int persionNum;
	protected double progress;
	protected String manageMoneyTypeName;//理财类别
	protected String afterTime;//剩余投标时间（秒）
	protected BigDecimal yqincomMoney;//预期收益
	
	protected String startTime;//开始购买时间
	protected String nowDate;//当前时间
	protected String saleTime;//预售剩余时间（秒）
	
	protected String logoUrl;//Logo路径
	
	/**
	 * Default Empty Constructor for class PlManageMoneyPlan
	 */
	public PlManageMoneyPlan () {
		super();
	}
	
	public Integer getIsOne() {
		return isOne;
	}

	public void setIsOne(Integer isOne) {
		this.isOne = isOne;
	}

	public String getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}

	public String getManageMoneyTypeName() {
		return manageMoneyTypeName;
	}

	public void setManageMoneyTypeName(String manageMoneyTypeName) {
		this.manageMoneyTypeName = manageMoneyTypeName;
	}

	public java.util.Date getLockingEndDate() {
		return lockingEndDate;
	}

	public void setLockingEndDate(java.util.Date lockingEndDate) {
		this.lockingEndDate = lockingEndDate;
	}

	public java.math.BigDecimal getMaxYearRate() {
		return maxYearRate;
	}

	public void setMaxYearRate(java.math.BigDecimal maxYearRate) {
		this.maxYearRate = maxYearRate;
	}

	public Integer getLockingLimit() {
		return lockingLimit;
	}

	public void setLockingLimit(Integer lockingLimit) {
		this.lockingLimit = lockingLimit;
	}

	public String getMoneyReceiver() {
		return moneyReceiver;
	}

	public void setMoneyReceiver(String moneyReceiver) {
		this.moneyReceiver = moneyReceiver;
	}

	public java.math.BigDecimal getEarlierOutRate() {
		return earlierOutRate;
	}

	public void setEarlierOutRate(java.math.BigDecimal earlierOutRate) {
		this.earlierOutRate = earlierOutRate;
	}

	public BigDecimal getAddRate() {
		return addRate;
	}

	public void setAddRate(BigDecimal addRate) {
		this.addRate = addRate;
	}

	public BigDecimal getReturnRatio() {
		return returnRatio;
	}

	public void setReturnRatio(BigDecimal returnRatio) {
		this.returnRatio = returnRatio;
	}

	public Integer getCoupon() {
		return coupon;
	}

	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}

	public Integer getNovice() {
		return novice;
	}

	public void setNovice(Integer novice) {
		this.novice = novice;
	}

	public BigDecimal getRaiseRate() {
		return raiseRate;
	}

	public void setRaiseRate(BigDecimal raiseRate) {
		this.raiseRate = raiseRate;
	}

	public BigDecimal getMaxCouponMoney() {
		return maxCouponMoney;
	}

	public void setMaxCouponMoney(BigDecimal maxCouponMoney) {
		this.maxCouponMoney = maxCouponMoney;
	}

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public Integer getRebateWay() {
		return rebateWay;
	}

	public void setRebateWay(Integer rebateWay) {
		this.rebateWay = rebateWay;
	}

	/**
	 * Default Key Fields Constructor for class PlManageMoneyPlan
	 */
	public PlManageMoneyPlan (
		 Long in_mmplanId
        ) {
		this.setMmplanId(in_mmplanId);
    }

    

	public java.util.Set getPlManageMoneyPlanBuyinfos() {
		return plManageMoneyPlanBuyinfos;
	}

	public void setPlManageMoneyPlanBuyinfos(java.util.Set plManageMoneyPlanBuyinfos) {
		this.plManageMoneyPlanBuyinfos = plManageMoneyPlanBuyinfos;
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public int getPersionNum() {
		return persionNum;
	}

	public void setPersionNum(int persionNum) {
		this.persionNum = persionNum;
	}

	public Integer getIsCyclingLend() {
		return isCyclingLend;
	}

	public void setIsCyclingLend(Integer isCyclingLend) {
		this.isCyclingLend = isCyclingLend;
	}

	public java.math.BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(java.math.BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}

	/**
	 * bidId	 * @return Long
     * @hibernate.id column="mmplanId" type="java.lang.Long" generator-class="native"
	 */
	public Long getMmplanId() {
		return this.mmplanId;
	}
	
	/**
	 * Set the mmplanId
	 */	
	public void setMmplanId(Long aValue) {
		this.mmplanId = aValue;
	}	

	/**
	 * 理财名称	 * @return String
	 * @hibernate.property column="mmName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getMmName() {
		return this.mmName;
	}
	
	/**
	 * Set the mmName
	 */	
	public void setMmName(String aValue) {
		this.mmName = aValue;
	}	

	/**
	 * 理财编号	 * @return String
	 * @hibernate.property column="mmNumber" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getMmNumber() {
		return this.mmNumber;
	}
	
	/**
	 * Set the mmNumber
	 */	
	public void setMmNumber(String aValue) {
		this.mmNumber = aValue;
	}	

	/**
	 * 理财编号	 * @return Long
	 * @hibernate.property column="manageMoneyTypeId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getManageMoneyTypeId() {
		return this.manageMoneyTypeId;
	}
	
	/**
	 * Set the manageMoneyTypeId
	 */	
	public void setManageMoneyTypeId(Long aValue) {
		this.manageMoneyTypeId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="keystr" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getKeystr() {
		return this.keystr;
	}
	
	/**
	 * Set the keystr
	 */	
	public void setKeystr(String aValue) {
		this.keystr = aValue;
	}	

	/**
	 * 理财编号	 * @return String
	 * @hibernate.property column="investScope" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getInvestScope() {
		return this.investScope;
	}
	
	/**
	 * Set the investScope
	 */	
	public void setInvestScope(String aValue) {
		this.investScope = aValue;
	}	

	/**
	 * 收益方式	 * @return String
	 * @hibernate.property column="benefitWay" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getBenefitWay() {
		return this.benefitWay;
	}
	
	/**
	 * Set the benefitWay
	 */	
	public void setBenefitWay(String aValue) {
		this.benefitWay = aValue;
	}	

	/**
	 * 收益方式	 * @return java.util.Date
	 * @hibernate.property column="buyStartTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBuyStartTime() {
		return this.buyStartTime;
	}
	
	/**
	 * Set the buyStartTime
	 */	
	public void setBuyStartTime(java.util.Date aValue) {
		this.buyStartTime = aValue;
	}	

	/**
	 * 收益方式	 * @return java.util.Date
	 * @hibernate.property column="buyEndTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBuyEndTime() {
		return this.buyEndTime;
	}
	
	/**
	 * Set the buyEndTime
	 */	
	public void setBuyEndTime(java.util.Date aValue) {
		this.buyEndTime = aValue;
	}	

	/**
	 * 投资起点金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="startMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getStartMoney() {
		return this.startMoney;
	}
	
	/**
	 * Set the startMoney
	 */	
	public void setStartMoney(java.math.BigDecimal aValue) {
		this.startMoney = aValue;
	}	

	/**
	 * 递增金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="riseMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getRiseMoney() {
		return this.riseMoney;
	}
	
	/**
	 * Set the riseMoney
	 */	
	public void setRiseMoney(java.math.BigDecimal aValue) {
		this.riseMoney = aValue;
	}	

	/**
	 * 单一投资人投资上限	 * @return java.math.BigDecimal
	 * @hibernate.property column="limitMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getLimitMoney() {
		return this.limitMoney;
	}
	
	/**
	 * Set the limitMoney
	 */	
	public void setLimitMoney(java.math.BigDecimal aValue) {
		this.limitMoney = aValue;
	}	

	/**
	 * 单一投资人投资上限	 * @return String
	 * @hibernate.property column="startinInterestCondition" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getStartinInterestCondition() {
		return this.startinInterestCondition;
	}
	
	/**
	 * Set the startinInterestCondition
	 */	
	public void setStartinInterestCondition(String aValue) {
		this.startinInterestCondition = aValue;
	}	

	/**
	 * 单一投资人投资上限	 * @return String
	 * @hibernate.property column="expireRedemptionWay" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getExpireRedemptionWay() {
		return this.expireRedemptionWay;
	}
	
	/**
	 * Set the expireRedemptionWay
	 */	
	public void setExpireRedemptionWay(String aValue) {
		this.expireRedemptionWay = aValue;
	}	

	/**
	 * 费用收取方式	 * @return String
	 * @hibernate.property column="chargeGetway" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getChargeGetway() {
		return this.chargeGetway;
	}
	
	/**
	 * Set the chargeGetway
	 */	
	public void setChargeGetway(String aValue) {
		this.chargeGetway = aValue;
	}	

	/**
	 * 保障方式	 * @return String
	 * @hibernate.property column="guaranteeWay" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getGuaranteeWay() {
		return this.guaranteeWay;
	}
	
	/**
	 * Set the guaranteeWay
	 */	
	public void setGuaranteeWay(String aValue) {
		this.guaranteeWay = aValue;
	}	

	/**
	 * 年化收益率	 * @return java.math.BigDecimal
	 * @hibernate.property column="yeaRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getYeaRate() {
		return this.yeaRate;
	}
	
	/**
	 * Set the yeaRate
	 */	
	public void setYeaRate(java.math.BigDecimal aValue) {
		this.yeaRate = aValue;
	}	

	/**
	 * 投资期限	 * @return Integer
	 * @hibernate.property column="investlimit" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getInvestlimit() {
		return this.investlimit;
	}
	
	/**
	 * Set the investlimit
	 */	
	public void setInvestlimit(Integer aValue) {
		this.investlimit = aValue;
	}	

	/**
	 * 总额度	 * @return java.math.BigDecimal
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
	 * 状态 （0 未发布 1招标中 2已齐标 3已流标4手动关闭）	 * @return Integer
	 * @hibernate.property column="state" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getState() {
		return this.state;
	}
	
	/**
	 * Set the state
	 */	
	public void setState(Integer aValue) {
		this.state = aValue;
	}	

	/**
	 * 总额度	 * @return java.util.Date
	 * @hibernate.property column="startinInterestTime" type="java.util.Date" length="10" not-null="false" unique="false"
	 */
	public java.util.Date getStartinInterestTime() {
		return this.startinInterestTime;
	}
	
	/**
	 * Set the startinInterestTime
	 */	
	public void setStartinInterestTime(java.util.Date aValue) {
		this.startinInterestTime = aValue;
	}	

	/**
	 * 计息止日	 * @return java.util.Date
	 * @hibernate.property column="endinInterestTime" type="java.util.Date" length="10" not-null="false" unique="false"
	 */
	public java.util.Date getEndinInterestTime() {
		return this.endinInterestTime;
	}
	
	/**
	 * Set the endinInterestTime
	 */	
	public void setEndinInterestTime(java.util.Date aValue) {
		this.endinInterestTime = aValue;
	}	

	/**
	 * 计息止日	 * @return java.math.BigDecimal
	 * @hibernate.property column="investedMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getInvestedMoney() {
		return this.investedMoney;
	}
	
	/**
	 * Set the investedMoney
	 */	
	public void setInvestedMoney(java.math.BigDecimal aValue) {
		this.investedMoney = aValue;
	}	

	/**
	 * 招标说明	 * @return String
	 * @hibernate.property column="bidRemark" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getBidRemark() {
		return this.bidRemark;
	}
	
	/**
	 * Set the bidRemark
	 */	
	public void setBidRemark(String aValue) {
		this.bidRemark = aValue;
	}	

	/**
	 * 生成路径	 * @return String
	 * @hibernate.property column="htmlPath" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getHtmlPath() {
		return this.htmlPath;
	}
	
	/**
	 * Set the htmlPath
	 */	
	public void setHtmlPath(String aValue) {
		this.htmlPath = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	/**
	 * Set the createtime
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="updatetime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	/**
	 * Set the updatetime
	 */	
	public void setUpdatetime(java.util.Date aValue) {
		this.updatetime = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PlManageMoneyPlan)) {
			return false;
		}
		PlManageMoneyPlan rhs = (PlManageMoneyPlan) object;
		return new EqualsBuilder()
				.append(this.mmplanId, rhs.mmplanId)
				.append(this.mmName, rhs.mmName)
				.append(this.mmNumber, rhs.mmNumber)
				.append(this.manageMoneyTypeId, rhs.manageMoneyTypeId)
				.append(this.keystr, rhs.keystr)
				.append(this.investScope, rhs.investScope)
				.append(this.benefitWay, rhs.benefitWay)
				.append(this.buyStartTime, rhs.buyStartTime)
				.append(this.buyEndTime, rhs.buyEndTime)
				.append(this.startMoney, rhs.startMoney)
				.append(this.riseMoney, rhs.riseMoney)
				.append(this.limitMoney, rhs.limitMoney)
				.append(this.startinInterestCondition, rhs.startinInterestCondition)
				.append(this.expireRedemptionWay, rhs.expireRedemptionWay)
				.append(this.chargeGetway, rhs.chargeGetway)
				.append(this.guaranteeWay, rhs.guaranteeWay)
				.append(this.yeaRate, rhs.yeaRate)
				.append(this.investlimit, rhs.investlimit)
				.append(this.sumMoney, rhs.sumMoney)
				.append(this.state, rhs.state)
				.append(this.startinInterestTime, rhs.startinInterestTime)
				.append(this.endinInterestTime, rhs.endinInterestTime)
				.append(this.investedMoney, rhs.investedMoney)
				.append(this.bidRemark, rhs.bidRemark)
				.append(this.htmlPath, rhs.htmlPath)
				.append(this.createtime, rhs.createtime)
				.append(this.updatetime, rhs.updatetime)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.mmplanId) 
				.append(this.mmName) 
				.append(this.mmNumber) 
				.append(this.manageMoneyTypeId) 
				.append(this.keystr) 
				.append(this.investScope) 
				.append(this.benefitWay) 
				.append(this.buyStartTime) 
				.append(this.buyEndTime) 
				.append(this.startMoney) 
				.append(this.riseMoney) 
				.append(this.limitMoney) 
				.append(this.startinInterestCondition) 
				.append(this.expireRedemptionWay) 
				.append(this.chargeGetway) 
				.append(this.guaranteeWay) 
				.append(this.yeaRate) 
				.append(this.investlimit) 
				.append(this.sumMoney) 
				.append(this.state) 
				.append(this.startinInterestTime) 
				.append(this.endinInterestTime) 
				.append(this.investedMoney) 
				.append(this.bidRemark) 
				.append(this.htmlPath) 
				.append(this.createtime) 
				.append(this.updatetime) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("mmplanId", this.mmplanId) 
				.append("mmName", this.mmName) 
				.append("mmNumber", this.mmNumber) 
				.append("manageMoneyTypeId", this.manageMoneyTypeId) 
				.append("keystr", this.keystr) 
				.append("investScope", this.investScope) 
				.append("benefitWay", this.benefitWay) 
				.append("buyStartTime", this.buyStartTime) 
				.append("buyEndTime", this.buyEndTime) 
				.append("startMoney", this.startMoney) 
				.append("riseMoney", this.riseMoney) 
				.append("limitMoney", this.limitMoney) 
				.append("startinInterestCondition", this.startinInterestCondition) 
				.append("expireRedemptionWay", this.expireRedemptionWay) 
				.append("chargeGetway", this.chargeGetway) 
				.append("guaranteeWay", this.guaranteeWay) 
				.append("yeaRate", this.yeaRate) 
				.append("investlimit", this.investlimit) 
				.append("sumMoney", this.sumMoney) 
				.append("state", this.state) 
				.append("startinInterestTime", this.startinInterestTime) 
				.append("endinInterestTime", this.endinInterestTime) 
				.append("investedMoney", this.investedMoney) 
				.append("bidRemark", this.bidRemark) 
				.append("htmlPath", this.htmlPath) 
				.append("createtime", this.createtime) 
				.append("updatetime", this.updatetime) 
				.toString();
	}

	public BigDecimal getYqincomMoney() {
		return yqincomMoney;
	}

	public void setYqincomMoney(BigDecimal yqincomMoney) {
		this.yqincomMoney = yqincomMoney;
	}

	public Integer getPlmmStartTime() {
		return plmmStartTime;
	}

	public void setPlmmStartTime(Integer plmmStartTime) {
		this.plmmStartTime = plmmStartTime;
	}

	public java.math.BigDecimal getMonthRate() {
		return monthRate;
	}

	public void setMonthRate(java.math.BigDecimal monthRate) {
		this.monthRate = monthRate;
	}

	public java.math.BigDecimal getDayRate() {
		return dayRate;
	}

	public void setDayRate(java.math.BigDecimal dayRate) {
		this.dayRate = dayRate;
	}

	public String getNowDate() {
		return nowDate;
	}
	public java.math.BigDecimal getJoinRate() {
		return joinRate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public void setJoinRate(java.math.BigDecimal joinRate) {
		this.joinRate = joinRate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getPreSaleTime() {
		return preSaleTime;
	}

	public void setPreSaleTime(java.util.Date preSaleTime) {
		this.preSaleTime = preSaleTime;
	}

	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}


}
