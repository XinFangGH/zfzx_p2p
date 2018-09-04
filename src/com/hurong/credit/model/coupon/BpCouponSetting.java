package com.hurong.credit.model.coupon;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * BpCouponSetting Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BpCouponSetting extends com.zhiwei.core.model.BaseModel {

    protected Long categoryId;
	/**
	 *  1  返现券
	 *  2   体验券
	 *  3  加息券
	 */
	protected String couponType;
	protected String couponTypeValue;
	protected String couponDescribe;
	protected java.math.BigDecimal couponValue;
	protected Integer counponCount;
	protected java.math.BigDecimal totalCouponValue;
	protected java.util.Date couponStartDate;
	protected java.util.Date couponEndDate;
	protected java.util.Date couponCheckDate;
	protected String couponCheckUserName;
	protected Long couponCheckUserId;
	protected Short couponState=0;
	protected java.util.Date createDate;
	protected String createName;
	protected Long createUserId;


	
	public String getCouponTypeValue() {
		return couponTypeValue;
	}

	public void setCouponTypeValue(String couponTypeValue) {
		this.couponTypeValue = couponTypeValue;
	}
	
	/**
	 * Default Empty Constructor for class BpCouponSetting
	 */
	public BpCouponSetting () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BpCouponSetting
	 */
	public BpCouponSetting (
		 Long in_categoryId
        ) {
		this.setCategoryId(in_categoryId);
    }

    

	/**
	 * 优惠券包id	 * @return Long
     * @hibernate.id column="categoryId" type="java.lang.Long" generator-class="native"
	 */
	public Long getCategoryId() {
		return this.categoryId;
	}
	
	/**
	 * Set the categoryId
	 */	
	public void setCategoryId(Long aValue) {
		this.categoryId = aValue;
	}	

	/**
	 * 优惠券类型类型	 * @return String
	 * @hibernate.property column="couponType" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getCouponType() {
		return this.couponType;
	}
	
	/**
	 * Set the couponType
	 * @spring.validator type="required"
	 */	
	public void setCouponType(String aValue) {
		this.couponType = aValue;
	}	

	/**
	 * 优惠券说明	 * @return String
	 * @hibernate.property column="couponDescribe" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getCouponDescribe() {
		return this.couponDescribe;
	}
	
	/**
	 * Set the couponDescribe
	 * @spring.validator type="required"
	 */	
	public void setCouponDescribe(String aValue) {
		this.couponDescribe = aValue;
	}	

	/**
	 * 单张面值	 * @return java.math.BigDecimal
	 * @hibernate.property column="couponValue" type="java.math.BigDecimal" length="20" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getCouponValue() {
		return this.couponValue;
	}
	
	/**
	 * Set the couponValue
	 * @spring.validator type="required"
	 */	
	public void setCouponValue(java.math.BigDecimal aValue) {
		this.couponValue = aValue;
	}	

	/**
	 * 优惠券张数	 * @return Integer
	 * @hibernate.property column="counponCount" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getCounponCount() {
		return this.counponCount;
	}
	
	/**
	 * Set the counponCount
	 * @spring.validator type="required"
	 */	
	public void setCounponCount(Integer aValue) {
		this.counponCount = aValue;
	}	

	/**
	 * 本次优惠券总额	 * @return java.math.BigDecimal
	 * @hibernate.property column="totalCouponValue" type="java.math.BigDecimal" length="20" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getTotalCouponValue() {
		return this.totalCouponValue;
	}
	
	/**
	 * Set the totalCouponValue
	 * @spring.validator type="required"
	 */	
	public void setTotalCouponValue(java.math.BigDecimal aValue) {
		this.totalCouponValue = aValue;
	}	

	/**
	 * 优惠券使用开始时间	 * @return java.util.Date
	 * @hibernate.property column="couponStartDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getCouponStartDate() {
		return this.couponStartDate;
	}
	
	/**
	 * Set the couponStartDate
	 * @spring.validator type="required"
	 */	
	public void setCouponStartDate(java.util.Date aValue) {
		this.couponStartDate = aValue;
	}	

	/**
	 * 优惠券使用结束时间	 * @return java.util.Date
	 * @hibernate.property column="couponEndDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getCouponEndDate() {
		return this.couponEndDate;
	}
	
	/**
	 * Set the couponEndDate
	 * @spring.validator type="required"
	 */	
	public void setCouponEndDate(java.util.Date aValue) {
		this.couponEndDate = aValue;
	}	

	/**
	 * 审核日期	 * @return java.util.Date
	 * @hibernate.property column="couponCheckDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCouponCheckDate() {
		return this.couponCheckDate;
	}
	
	/**
	 * Set the couponCheckDate
	 */	
	public void setCouponCheckDate(java.util.Date aValue) {
		this.couponCheckDate = aValue;
	}	

	/**
	 * 审核人姓名	 * @return String
	 * @hibernate.property column="couponCheckUserName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getCouponCheckUserName() {
		return this.couponCheckUserName;
	}
	
	/**
	 * Set the couponCheckUserName
	 */	
	public void setCouponCheckUserName(String aValue) {
		this.couponCheckUserName = aValue;
	}	

	/**
	 * 审核人ID	 * @return Long
	 * @hibernate.property column="couponCheckUserId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getCouponCheckUserId() {
		return this.couponCheckUserId;
	}
	
	/**
	 * Set the couponCheckUserId
	 */	
	public void setCouponCheckUserId(Long aValue) {
		this.couponCheckUserId = aValue;
	}	

	/**
	 * 优惠券状态  默认状态0  表示未审核	 * @return Short
	 * @hibernate.property column="couponState" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getCouponState() {
		return this.couponState;
	}
	
	/**
	 * Set the couponState
	 * @spring.validator type="required"
	 */	
	public void setCouponState(Short aValue) {
		this.couponState = aValue;
	}	

	/**
	 * 创建时间	 * @return java.util.Date
	 * @hibernate.property column="createDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * Set the createDate
	 * @spring.validator type="required"
	 */	
	public void setCreateDate(java.util.Date aValue) {
		this.createDate = aValue;
	}	

	/**
	 * 创建人姓名	 * @return String
	 * @hibernate.property column="createName" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getCreateName() {
		return this.createName;
	}
	
	/**
	 * Set the createName
	 * @spring.validator type="required"
	 */	
	public void setCreateName(String aValue) {
		this.createName = aValue;
	}	

	/**
	 * 创建人Id	 * @return Long
	 * @hibernate.property column="createUserId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getCreateUserId() {
		return this.createUserId;
	}
	
	/**
	 * Set the createUserId
	 * @spring.validator type="required"
	 */	
	public void setCreateUserId(Long aValue) {
		this.createUserId = aValue;
	}	


	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpCouponSetting)) {
			return false;
		}
		BpCouponSetting rhs = (BpCouponSetting) object;
		return new EqualsBuilder()
				.append(this.categoryId, rhs.categoryId)
				.append(this.couponType, rhs.couponType)
				.append(this.couponDescribe, rhs.couponDescribe)
				.append(this.couponValue, rhs.couponValue)
				.append(this.counponCount, rhs.counponCount)
				.append(this.totalCouponValue, rhs.totalCouponValue)
				.append(this.couponStartDate, rhs.couponStartDate)
				.append(this.couponEndDate, rhs.couponEndDate)
				.append(this.couponCheckDate, rhs.couponCheckDate)
				.append(this.couponCheckUserName, rhs.couponCheckUserName)
				.append(this.couponCheckUserId, rhs.couponCheckUserId)
				.append(this.couponState, rhs.couponState)
				.append(this.createDate, rhs.createDate)
				.append(this.createName, rhs.createName)
				.append(this.createUserId, rhs.createUserId)
				.append(this.companyId, rhs.companyId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.categoryId) 
				.append(this.couponType) 
				.append(this.couponDescribe) 
				.append(this.couponValue) 
				.append(this.counponCount) 
				.append(this.totalCouponValue) 
				.append(this.couponStartDate) 
				.append(this.couponEndDate) 
				.append(this.couponCheckDate) 
				.append(this.couponCheckUserName) 
				.append(this.couponCheckUserId) 
				.append(this.couponState) 
				.append(this.createDate) 
				.append(this.createName) 
				.append(this.createUserId) 
				.append(this.companyId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("categoryId", this.categoryId) 
				.append("couponType", this.couponType) 
				.append("couponDescribe", this.couponDescribe) 
				.append("couponValue", this.couponValue) 
				.append("counponCount", this.counponCount) 
				.append("totalCouponValue", this.totalCouponValue) 
				.append("couponStartDate", this.couponStartDate) 
				.append("couponEndDate", this.couponEndDate) 
				.append("couponCheckDate", this.couponCheckDate) 
				.append("couponCheckUserName", this.couponCheckUserName) 
				.append("couponCheckUserId", this.couponCheckUserId) 
				.append("couponState", this.couponState) 
				.append("createDate", this.createDate) 
				.append("createName", this.createName) 
				.append("createUserId", this.createUserId) 
				.append("companyId", this.companyId) 
				.toString();
	}



}
