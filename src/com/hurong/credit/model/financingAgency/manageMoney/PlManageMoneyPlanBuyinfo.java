package com.hurong.credit.model.financingAgency.manageMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
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
 * PlManageMoneyPlanBuyinfo Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 理财计划加入/产品购买情况库
 */
public class PlManageMoneyPlanBuyinfo extends com.zhiwei.core.model.BaseModel {
	//线上客户
    public static final short P_TYPE0=0;
    //线下客户
    public static final short P_TYPE1=1;
    
    public static final short pic_count=5;
    @Expose
    protected Long orderId;//订单id
	
    protected Short persionType;// 属于线上客户还是线下客户 1线下 0线上
	protected Long investPersonId;//投资人id
	@Expose
	protected String investPersonName;//投资人姓名 
	protected Long mmplanId;//理财计划/产品id
	protected String keystr;//理财类别
	@Expose
	protected java.util.Date buyDatetime;//购买时间
	@Expose
	protected java.math.BigDecimal buyMoney;//购买金额
	protected java.util.Date startinInterestTime;//起息开始日
	protected java.util.Date endinInterestTime;//起息结束日
	protected java.util.Date earlierOutDate;//提前退出日期
	protected Integer orderlimit;//投资期限
	protected Integer investlimit;
	public Integer getInvestlimit() {
		return investlimit;
	}

	public void setInvestlimit(Integer investlimit) {
		this.investlimit = investlimit;
	}

	protected java.math.BigDecimal promisYearRate;//承诺年化收益率
	protected java.math.BigDecimal promisMonthRate;//承诺月化收益率
	protected java.math.BigDecimal promisDayRate;//承诺日化收益率
	protected java.math.BigDecimal promisIncomeSum;//承诺收入总金额
	protected java.math.BigDecimal currentMatchingMoney;//当前可匹配金额
	protected java.math.BigDecimal currentGetedMoney;//当前已获收益
	protected java.math.BigDecimal optimalDayRate;//最优日化利率
	protected PlManageMoneyPlan plManageMoneyPlan;
	protected Integer firstProjectIdcount;
	protected String firstProjectIdstr;
	protected Integer isAtuoMatch;  //1进行托管，0不托管
	protected String dealInfoNumber;//交易记录订单号
	/**
     * 状态
     * -2 ：关闭（流标），0：尚未支付购买金额，1：购买冻结成功，2：购买成功,7：提前支取申请中，8：提前支取，10：完成
     */
	@Expose
	protected Short state;
	protected String preAuthorizationNum;//富友，预授权合同号
	
	protected String mmName;//订单名称
	protected String contractNo; //合同编号
	
	protected Long couponId;//优惠券Id
	protected java.math.BigDecimal couponsMoney;//优惠券金额
	protected java.math.BigDecimal investmentProportion;//投资比例
	
	protected Integer investType;//U计划用到____1收益再投资,2提取主账户
	protected java.math.BigDecimal joinMoney;//加入费用
	
	//不与数据库映射字段
	protected String investTypeName;//收益处理方式1收益再投资,2提取主账户
	protected java.math.BigDecimal uearlierOutRate;//提前退出费率
	protected String lockingEndDate;//锁定期截止时间
	
	protected Long earlyRedemptionId;//提前赎回记录id
	protected java.util.Date earlyDate;//提前赎回申请日期
	/**
	 * 提前赎回状态
	 * 0:办理中，3:已终止,1:已通过
	 */
	protected Short checkStatus;
	
	/**
	 * 是否已经生成过奖励
	 * 1：没有生成 ，2：已经生成
	 */
	protected Integer isCreateReward;
	
	public Long getEarlyRedemptionId() {
		return earlyRedemptionId;
	}

	public void setEarlyRedemptionId(Long earlyRedemptionId) {
		this.earlyRedemptionId = earlyRedemptionId;
	}

	public java.util.Date getEarlyDate() {
		return earlyDate;
	}

	public void setEarlyDate(java.util.Date earlyDate) {
		this.earlyDate = earlyDate;
	}

	public Short getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getDealInfoNumber() {
		return dealInfoNumber;
	}

	public void setDealInfoNumber(String dealInfoNumber) {
		this.dealInfoNumber = dealInfoNumber;
	}

	public String getPreAuthorizationNum() {
		return preAuthorizationNum;
	}

	public java.util.Date getEarlierOutDate() {
		return earlierOutDate;
	}

	public void setEarlierOutDate(java.util.Date earlierOutDate) {
		this.earlierOutDate = earlierOutDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public void setPreAuthorizationNum(String preAuthorizationNum) {
		this.preAuthorizationNum = preAuthorizationNum;
	}

	public Integer getIsAtuoMatch() {
		return isAtuoMatch;
	}

	public void setIsAtuoMatch(Integer isAtuoMatch) {
		this.isAtuoMatch = isAtuoMatch;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	/**
	 * Default Empty Constructor for class PlManageMoneyPlanBuyinfo
	 */
	public PlManageMoneyPlanBuyinfo () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PlManageMoneyPlanBuyinfo
	 */
	public PlManageMoneyPlanBuyinfo (
		 Long in_orderId
        ) {
		this.setOrderId(in_orderId);
    }

    

	/**
	 * id	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */


	public PlManageMoneyPlan getPlManageMoneyPlan() {
		return plManageMoneyPlan;
	}




	public Integer getFirstProjectIdcount() {
		return firstProjectIdcount;
	}

	public void setFirstProjectIdcount(Integer firstProjectIdcount) {
		this.firstProjectIdcount = firstProjectIdcount;
	}

	public String getFirstProjectIdstr() {
		return firstProjectIdstr;
	}

	public void setFirstProjectIdstr(String firstProjectIdstr) {
		this.firstProjectIdstr = firstProjectIdstr;
	}

	public void setPlManageMoneyPlan(PlManageMoneyPlan plManageMoneyPlan) {
		this.plManageMoneyPlan = plManageMoneyPlan;
	}

	/**
	 * bidId	 * @return Long
	 * @hibernate.property column="mmplanId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	
	public String getKeystr() {
		return keystr;
	}

	public Long getMmplanId() {
		return mmplanId;
	}

	public void setMmplanId(Long mmplanId) {
		this.mmplanId = mmplanId;
	}

	public void setKeystr(String keystr) {
		this.keystr = keystr;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Set the mmplanId
	 */	


	/**
	 * 	 * @return Long
	 * @hibernate.property column="investerType" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */


	/**
	 * 投资人账号	 * @return String
	 * @hibernate.property column="userName" type="java.lang.String" length="50" not-null="false" unique="false"
	 */

	/**
	 * 投资人id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	/**
	 * 	 * @return String
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
	 * 	 * @return java.util.Date
	 * @hibernate.property column="buyDatetime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBuyDatetime() {
		return this.buyDatetime;
	}
	
	/**
	 * Set the buyDatetime
	 */	
	public void setBuyDatetime(java.util.Date aValue) {
		this.buyDatetime = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="buyMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBuyMoney() {
		return this.buyMoney;
	}
	
	/**
	 * Set the buyMoney
	 */	
	public void setBuyMoney(java.math.BigDecimal aValue) {
		this.buyMoney = aValue;
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
	 * 	 * @return Integer
	 * @hibernate.property column="investlimit" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */


	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="promisYearRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPromisYearRate() {
		return this.promisYearRate;
	}
	
	/**
	 * Set the promisYearRate
	 */	
	public void setPromisYearRate(java.math.BigDecimal aValue) {
		this.promisYearRate = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="promisMonthRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPromisMonthRate() {
		return this.promisMonthRate;
	}
	
	/**
	 * Set the promisMonthRate
	 */	
	public void setPromisMonthRate(java.math.BigDecimal aValue) {
		this.promisMonthRate = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="promisDayRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPromisDayRate() {
		return this.promisDayRate;
	}
	
	/**
	 * Set the promisDayRate
	 */	
	public void setPromisDayRate(java.math.BigDecimal aValue) {
		this.promisDayRate = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="promisIncomeSum" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPromisIncomeSum() {
		return this.promisIncomeSum;
	}
	
	/**
	 * Set the promisIncomeSum
	 */	
	public void setPromisIncomeSum(java.math.BigDecimal aValue) {
		this.promisIncomeSum = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="currentMatchingMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getCurrentMatchingMoney() {
		return this.currentMatchingMoney;
	}
	
	/**
	 * Set the currentMatchingMoney
	 */	
	public void setCurrentMatchingMoney(java.math.BigDecimal aValue) {
		this.currentMatchingMoney = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="currentGetedMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getCurrentGetedMoney() {
		return this.currentGetedMoney;
	}
	
	/**
	 * Set the currentGetedMoney
	 */	
	public void setCurrentGetedMoney(java.math.BigDecimal aValue) {
		this.currentGetedMoney = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="optimalDayRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getOptimalDayRate() {
		return this.optimalDayRate;
	}
	
	/**
	 * Set the optimalDayRate
	 */	
	public void setOptimalDayRate(java.math.BigDecimal aValue) {
		this.optimalDayRate = aValue;
	}	



	public Short getPersionType() {
		return persionType;
	}

	public void setPersionType(Short persionType) {
		this.persionType = persionType;
	}

	public Long getInvestPersonId() {
		return investPersonId;
	}

	public void setInvestPersonId(Long investPersonId) {
		this.investPersonId = investPersonId;
	}

	public String getInvestPersonName() {
		return investPersonName;
	}

	public void setInvestPersonName(String investPersonName) {
		this.investPersonName = investPersonName;
	}

	public Integer getOrderlimit() {
		return orderlimit;
	}

	public void setOrderlimit(Integer orderlimit) {
		this.orderlimit = orderlimit;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PlManageMoneyPlanBuyinfo)) {
			return false;
		}
		PlManageMoneyPlanBuyinfo rhs = (PlManageMoneyPlanBuyinfo) object;
		return new EqualsBuilder()
				.append(this.orderId, rhs.orderId)
				.append(this.mmName, rhs.mmName)
				.append(this.buyDatetime, rhs.buyDatetime)
				.append(this.buyMoney, rhs.buyMoney)
				.append(this.startinInterestTime, rhs.startinInterestTime)
				.append(this.endinInterestTime, rhs.endinInterestTime)
				.append(this.promisYearRate, rhs.promisYearRate)
				.append(this.promisMonthRate, rhs.promisMonthRate)
				.append(this.promisDayRate, rhs.promisDayRate)
				.append(this.promisIncomeSum, rhs.promisIncomeSum)
				.append(this.currentMatchingMoney, rhs.currentMatchingMoney)
				.append(this.currentGetedMoney, rhs.currentGetedMoney)
				.append(this.optimalDayRate, rhs.optimalDayRate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.orderId) 
				.append(this.mmName) 
				.append(this.buyDatetime) 
				.append(this.buyMoney) 
				.append(this.startinInterestTime) 
				.append(this.endinInterestTime) 
				.append(this.promisYearRate) 
				.append(this.promisMonthRate) 
				.append(this.promisDayRate) 
				.append(this.promisIncomeSum) 
				.append(this.currentMatchingMoney) 
				.append(this.currentGetedMoney) 
				.append(this.optimalDayRate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("orderId", this.orderId) 
				.append("mmName", this.mmName) 
				.append("buyDatetime", this.buyDatetime) 
				.append("buyMoney", this.buyMoney) 
				.append("startinInterestTime", this.startinInterestTime) 
				.append("endinInterestTime", this.endinInterestTime) 
				.append("promisYearRate", this.promisYearRate) 
				.append("promisMonthRate", this.promisMonthRate) 
				.append("promisDayRate", this.promisDayRate) 
				.append("promisIncomeSum", this.promisIncomeSum) 
				.append("currentMatchingMoney", this.currentMatchingMoney) 
				.append("currentGetedMoney", this.currentGetedMoney) 
				.append("optimalDayRate", this.optimalDayRate) 
				.toString();
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public java.math.BigDecimal getCouponsMoney() {
		return couponsMoney;
	}

	public void setCouponsMoney(java.math.BigDecimal couponsMoney) {
		this.couponsMoney = couponsMoney;
	}

	public java.math.BigDecimal getInvestmentProportion() {
		return investmentProportion;
	}

	public void setInvestmentProportion(java.math.BigDecimal investmentProportion) {
		this.investmentProportion = investmentProportion;
	}

	public Integer getInvestType() {
		return investType;
	}

	public void setInvestType(Integer investType) {
		this.investType = investType;
	}

	public String getInvestTypeName() {
		return investTypeName;
	}

	public void setInvestTypeName(String investTypeName) {
		this.investTypeName = investTypeName;
	}

	public java.math.BigDecimal getUearlierOutRate() {
		return uearlierOutRate;
	}

	public void setUearlierOutRate(java.math.BigDecimal uearlierOutRate) {
		this.uearlierOutRate = uearlierOutRate;
	}

	public java.math.BigDecimal getJoinMoney() {
		return joinMoney;
	}

	public void setJoinMoney(java.math.BigDecimal joinMoney) {
		this.joinMoney = joinMoney;
	}

	public String getLockingEndDate() {
		return lockingEndDate;
	}

	public void setLockingEndDate(String lockingEndDate) {
		this.lockingEndDate = lockingEndDate;
	}

	public Integer getIsCreateReward() {
		return isCreateReward;
	}

	public void setIsCreateReward(Integer isCreateReward) {
		this.isCreateReward = isCreateReward;
	}




}
