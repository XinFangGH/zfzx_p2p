package com.hurong.credit.model.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * PlFinanceProduct Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class PlFinanceProduct extends com.hurong.core.model.BaseModel {

    protected Long id;//主键Id
	protected String productName;//理财专户产品名称
	protected String productNumber;//理财专户产品编号
	protected String productDes;//理财专户产品介绍
	protected String intestModel;//起息模式
	protected String intestModelName;//起息模式描述
	protected String exitModel;//退出模式
	protected String exitModelName;//退出模式描述
	protected java.math.BigDecimal minBidMoney;//单笔最小投资额
	protected java.math.BigDecimal maxBidMoney;//单笔最大投资额
	protected java.math.BigDecimal totalBidMoney;//累积投资最大额
	protected String productGuarantee;//保障方式
	protected java.math.BigDecimal plateRate=new BigDecimal(0);//平台收费
	protected java.util.Date productStartDate;//产品创建日期
	//计息当日年化利率
	protected java.math.BigDecimal yearRate;
	//计息昨日年化利率
	protected java.math.BigDecimal dayRate;
	//预计产生收益日期
	protected java.util.Date intentDay;
	//收益到账日
	protected java.util.Date accountDay;

	
	public java.util.Date getIntentDay() {
		return intentDay;
	}

	public void setIntentDay(java.util.Date intentDay) {
		this.intentDay = intentDay;
	}

	public java.util.Date getAccountDay() {
		return accountDay;
	}

	public void setAccountDay(java.util.Date accountDay) {
		this.accountDay = accountDay;
	}

	public String getIntestModelName() {
		return intestModelName;
	}

	public java.math.BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(java.math.BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public java.math.BigDecimal getDayRate() {
		return dayRate;
	}

	public void setDayRate(java.math.BigDecimal dayRate) {
		this.dayRate = dayRate;
	}

	public void setIntestModelName(String intestModelName) {
		this.intestModelName = intestModelName;
	}
	
	public String getExitModelName() {
		return exitModelName;
	}

	public void setExitModelName(String exitModelName) {
		this.exitModelName = exitModelName;
	}
	

	/**
	 * Default Empty Constructor for class PlFinanceProduct
	 */
	public PlFinanceProduct () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PlFinanceProduct
	 */
	public PlFinanceProduct (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * 	 * @return Long
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
	 * 	 * @return String
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
	 * 	 * @return String
	 * @hibernate.property column="productNumber" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getProductNumber() {
		return this.productNumber;
	}
	
	/**
	 * Set the productNumber
	 */	
	public void setProductNumber(String aValue) {
		this.productNumber = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="productDes" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getProductDes() {
		return this.productDes;
	}
	
	/**
	 * Set the productDes
	 */	
	public void setProductDes(String aValue) {
		this.productDes = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="intestModel" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getIntestModel() {
		return this.intestModel;
	}
	
	/**
	 * Set the intestModel
	 */	
	public void setIntestModel(String aValue) {
		this.intestModel = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="exitModel" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getExitModel() {
		return this.exitModel;
	}
	
	/**
	 * Set the exitModel
	 */	
	public void setExitModel(String aValue) {
		this.exitModel = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="minBidMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getMinBidMoney() {
		return this.minBidMoney;
	}
	
	/**
	 * Set the minBidMoney
	 */	
	public void setMinBidMoney(java.math.BigDecimal aValue) {
		this.minBidMoney = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="maxBidMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getMaxBidMoney() {
		return this.maxBidMoney;
	}
	
	/**
	 * Set the maxBidMoney
	 */	
	public void setMaxBidMoney(java.math.BigDecimal aValue) {
		this.maxBidMoney = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="totalBidMoney" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getTotalBidMoney() {
		return this.totalBidMoney;
	}
	
	/**
	 * Set the totalBidMoney
	 */	
	public void setTotalBidMoney(java.math.BigDecimal aValue) {
		this.totalBidMoney = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="productGuarantee" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getProductGuarantee() {
		return this.productGuarantee;
	}
	
	/**
	 * Set the productGuarantee
	 */	
	public void setProductGuarantee(String aValue) {
		this.productGuarantee = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="plateRate" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPlateRate() {
		return this.plateRate;
	}
	
	/**
	 * Set the plateRate
	 */	
	public void setPlateRate(java.math.BigDecimal aValue) {
		this.plateRate = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="productStartDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getProductStartDate() {
		return this.productStartDate;
	}
	
	/**
	 * Set the productStartDate
	 */	
	public void setProductStartDate(java.util.Date aValue) {
		this.productStartDate = aValue;
	}	


	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PlFinanceProduct)) {
			return false;
		}
		PlFinanceProduct rhs = (PlFinanceProduct) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.productName, rhs.productName)
				.append(this.productNumber, rhs.productNumber)
				.append(this.productDes, rhs.productDes)
				.append(this.intestModel, rhs.intestModel)
				.append(this.exitModel, rhs.exitModel)
				.append(this.minBidMoney, rhs.minBidMoney)
				.append(this.maxBidMoney, rhs.maxBidMoney)
				.append(this.totalBidMoney, rhs.totalBidMoney)
				.append(this.productGuarantee, rhs.productGuarantee)
				.append(this.plateRate, rhs.plateRate)
				.append(this.productStartDate, rhs.productStartDate)
				.append(this.companyId, rhs.companyId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.productName) 
				.append(this.productNumber) 
				.append(this.productDes) 
				.append(this.intestModel) 
				.append(this.exitModel) 
				.append(this.minBidMoney) 
				.append(this.maxBidMoney) 
				.append(this.totalBidMoney) 
				.append(this.productGuarantee) 
				.append(this.plateRate) 
				.append(this.productStartDate) 
				.append(this.companyId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("productName", this.productName) 
				.append("productNumber", this.productNumber) 
				.append("productDes", this.productDes) 
				.append("intestModel", this.intestModel) 
				.append("exitModel", this.exitModel) 
				.append("minBidMoney", this.minBidMoney) 
				.append("maxBidMoney", this.maxBidMoney) 
				.append("totalBidMoney", this.totalBidMoney) 
				.append("productGuarantee", this.productGuarantee) 
				.append("plateRate", this.plateRate) 
				.append("productStartDate", this.productStartDate) 
				.append("companyId", this.companyId) 
				.toString();
	}



}
