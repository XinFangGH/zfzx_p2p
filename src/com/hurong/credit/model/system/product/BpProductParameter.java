package com.hurong.credit.model.system.product;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.hurong.credit.model.creditFlow.assuretenet.OurProcreditAssuretenet;
import com.hurong.credit.model.creditFlow.materials.OurProcreditMaterialsEnterprise;

/**
 * 
 * @author 
 *
 */
/**
 * BpProductParameter Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
@SuppressWarnings("serial")
public class BpProductParameter extends com.hurong.core.model.BaseModel {

    protected Long id;
	protected String productName; //产品名称
	protected String borrowerType; //借款人类型
	protected String accrualtype; //还款方式
	protected String payaccrualType; //还款周期
	protected Integer payintentPeriod; //贷款期数
	protected Integer isPreposePayAccrual; //前置付息
	protected Integer isInterestByOneTime; //一次性支付全部利息
	protected String isStartDatePay; //是否按还款日还款
	protected Integer payintentPerioDate; //每期还款日
	protected java.math.BigDecimal yearAccrualRate; //年化利率
	protected java.math.BigDecimal accrual; //月化利率
	protected java.math.BigDecimal dayAccrualRate; //日化利率
	protected java.math.BigDecimal sumAccrualRate; //合计利率
	protected String productDescribe;//产品描述
	protected Date createTime;//创建时间
	protected Short productStatus;//产品状态
	
	protected List<OurProcreditAssuretenet> assure;//准入原则
	protected List<OurProcreditMaterialsEnterprise> loanMaterial;//贷款材料
	
	protected Long useAreaId;// BIGINT(5) COMMENT '适用地区id';
	protected String useAreaName ;//VARCHAR(50) COMMENT '适用地区名称';

	protected Long incomSpeedId;// BIGINT(5) COMMENT '收款速度id';
	protected String incomSpeedName;// VARCHAR(50) COMMENT '收款速度名称';

	protected Long loanCostId;// BIGINT(5) COMMENT '借款成本id';
	protected String loanCostName;// VARCHAR(50) COMMENT '借款成本名称';
	

	public List<OurProcreditAssuretenet> getAssure() {
		return assure;
	}

	public void setAssure(List<OurProcreditAssuretenet> assure) {
		this.assure = assure;
	}

	

	public List<OurProcreditMaterialsEnterprise> getLoanMaterial() {
		return loanMaterial;
	}

	public void setLoanMaterial(List<OurProcreditMaterialsEnterprise> loanMaterial) {
		this.loanMaterial = loanMaterial;
	}

	public Long getUseAreaId() {
		return useAreaId;
	}

	public void setUseAreaId(Long useAreaId) {
		this.useAreaId = useAreaId;
	}

	public String getUseAreaName() {
		return useAreaName;
	}

	public void setUseAreaName(String useAreaName) {
		this.useAreaName = useAreaName;
	}

	public Long getIncomSpeedId() {
		return incomSpeedId;
	}

	public void setIncomSpeedId(Long incomSpeedId) {
		this.incomSpeedId = incomSpeedId;
	}

	public String getIncomSpeedName() {
		return incomSpeedName;
	}

	public void setIncomSpeedName(String incomSpeedName) {
		this.incomSpeedName = incomSpeedName;
	}

	public Long getLoanCostId() {
		return loanCostId;
	}

	public void setLoanCostId(Long loanCostId) {
		this.loanCostId = loanCostId;
	}

	public String getLoanCostName() {
		return loanCostName;
	}

	public void setLoanCostName(String loanCostName) {
		this.loanCostName = loanCostName;
	}

	/**
	 * Default Empty Constructor for class BpProductParameter
	 */
	public BpProductParameter () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BpProductParameter
	 */
	public BpProductParameter (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * 产品表主键	 * @return Long
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
	 * 产品名称	 * @return String
	 * @hibernate.property column="productName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getProductName() {
		return this.productName;
	}
	
	/**
	 * Set the productName
	 */	
	public void setProductName(String aValue) {
		this.productName = aValue;
	}	

	/**
	 * 借款人类型	 * @return String
	 * @hibernate.property column="borrowerType" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getBorrowerType() {
		return this.borrowerType;
	}
	
	/**
	 * Set the borrowerType
	 */	
	public void setBorrowerType(String aValue) {
		this.borrowerType = aValue;
	}	

	/**
	 * 还款方式	 * @return String
	 * @hibernate.property column="accrualtype" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getAccrualtype() {
		return this.accrualtype;
	}
	
	/**
	 * Set the accrualtype
	 */	
	public void setAccrualtype(String aValue) {
		this.accrualtype = aValue;
	}	

	/**
	 * 还款周期	 * @return String
	 * @hibernate.property column="payaccrualType" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getPayaccrualType() {
		return this.payaccrualType;
	}
	
	/**
	 * Set the payaccrualType
	 */	
	public void setPayaccrualType(String aValue) {
		this.payaccrualType = aValue;
	}	

	/**
	 * 贷款期数	 * @return Integer
	 * @hibernate.property column="payintentPeriod" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getPayintentPeriod() {
		return this.payintentPeriod;
	}
	
	/**
	 * Set the payintentPeriod
	 */	
	public void setPayintentPeriod(Integer aValue) {
		this.payintentPeriod = aValue;
	}	

	/**
	 * 前置付息	 * @return Integer
	 * @hibernate.property column="isPreposePayAccrual" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsPreposePayAccrual() {
		return this.isPreposePayAccrual;
	}
	
	/**
	 * Set the isPreposePayAccrual
	 */	
	public void setIsPreposePayAccrual(Integer aValue) {
		this.isPreposePayAccrual = aValue;
	}	

	/**
	 * 一次性支付全部利息	 * @return Integer
	 * @hibernate.property column="isInterestByOneTime" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsInterestByOneTime() {
		return this.isInterestByOneTime;
	}
	
	/**
	 * Set the isInterestByOneTime
	 */	
	public void setIsInterestByOneTime(Integer aValue) {
		this.isInterestByOneTime = aValue;
	}	

	/**
	 * 是否按还款日还款	 * @return String
	 * @hibernate.property column="isStartDatePay" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getIsStartDatePay() {
		return this.isStartDatePay;
	}
	
	/**
	 * Set the isStartDatePay
	 */	
	public void setIsStartDatePay(String aValue) {
		this.isStartDatePay = aValue;
	}	

	/**
	 * 每期还款日	 * @return Integer
	 * @hibernate.property column="payintentPerioDate" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getPayintentPerioDate() {
		return this.payintentPerioDate;
	}
	
	/**
	 * Set the payintentPerioDate
	 */	
	public void setPayintentPerioDate(Integer aValue) {
		this.payintentPerioDate = aValue;
	}	

	/**
	 * 年化利率	 * @return java.math.BigDecimal
	 * @hibernate.property column="yearAccrualRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getYearAccrualRate() {
		return this.yearAccrualRate;
	}
	
	/**
	 * Set the yearAccrualRate
	 */	
	public void setYearAccrualRate(java.math.BigDecimal aValue) {
		this.yearAccrualRate = aValue;
	}	

	/**
	 * 月化利率	 * @return java.math.BigDecimal
	 * @hibernate.property column="accrual" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getAccrual() {
		return this.accrual;
	}
	
	/**
	 * Set the accrual
	 */	
	public void setAccrual(java.math.BigDecimal aValue) {
		this.accrual = aValue;
	}	

	/**
	 * 日化利率	 * @return java.math.BigDecimal
	 * @hibernate.property column="dayAccrualRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getDayAccrualRate() {
		return this.dayAccrualRate;
	}
	
	/**
	 * Set the dayAccrualRate
	 */	
	public void setDayAccrualRate(java.math.BigDecimal aValue) {
		this.dayAccrualRate = aValue;
	}	

	/**
	 * 合计利率	 * @return java.math.BigDecimal
	 * @hibernate.property column="sumAccrualRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSumAccrualRate() {
		return this.sumAccrualRate;
	}
	
	/**
	 * Set the sumAccrualRate
	 */	
	public void setSumAccrualRate(java.math.BigDecimal aValue) {
		this.sumAccrualRate = aValue;
	}	

	/**
	 * 产品描述	 * @return String
	 * @hibernate.property column="productDescribe" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getProductDescribe() {
		return this.productDescribe;
	}
	
	/**
	 * Set the productDescribe
	 */	
	public void setProductDescribe(String aValue) {
		this.productDescribe = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpProductParameter)) {
			return false;
		}
		BpProductParameter rhs = (BpProductParameter) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.productName, rhs.productName)
				.append(this.borrowerType, rhs.borrowerType)
				.append(this.accrualtype, rhs.accrualtype)
				.append(this.payaccrualType, rhs.payaccrualType)
				.append(this.payintentPeriod, rhs.payintentPeriod)
				.append(this.isPreposePayAccrual, rhs.isPreposePayAccrual)
				.append(this.isInterestByOneTime, rhs.isInterestByOneTime)
				.append(this.isStartDatePay, rhs.isStartDatePay)
				.append(this.payintentPerioDate, rhs.payintentPerioDate)
				.append(this.yearAccrualRate, rhs.yearAccrualRate)
				.append(this.accrual, rhs.accrual)
				.append(this.dayAccrualRate, rhs.dayAccrualRate)
				.append(this.sumAccrualRate, rhs.sumAccrualRate)
				.append(this.productDescribe, rhs.productDescribe)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.productName) 
				.append(this.borrowerType) 
				.append(this.accrualtype) 
				.append(this.payaccrualType) 
				.append(this.payintentPeriod) 
				.append(this.isPreposePayAccrual) 
				.append(this.isInterestByOneTime) 
				.append(this.isStartDatePay) 
				.append(this.payintentPerioDate) 
				.append(this.yearAccrualRate) 
				.append(this.accrual) 
				.append(this.dayAccrualRate) 
				.append(this.sumAccrualRate) 
				.append(this.productDescribe) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("productName", this.productName) 
				.append("borrowerType", this.borrowerType) 
				.append("accrualtype", this.accrualtype) 
				.append("payaccrualType", this.payaccrualType) 
				.append("payintentPeriod", this.payintentPeriod) 
				.append("isPreposePayAccrual", this.isPreposePayAccrual) 
				.append("isInterestByOneTime", this.isInterestByOneTime) 
				.append("isStartDatePay", this.isStartDatePay) 
				.append("payintentPerioDate", this.payintentPerioDate) 
				.append("yearAccrualRate", this.yearAccrualRate) 
				.append("accrual", this.accrual) 
				.append("dayAccrualRate", this.dayAccrualRate) 
				.append("sumAccrualRate", this.sumAccrualRate) 
				.append("productDescribe", this.productDescribe) 
				.toString();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Short productStatus) {
		this.productStatus = productStatus;
	}



}
