package com.hurong.credit.model.credit.thirdInterface;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;

/**
 * 
 * @author 
 *
 */
/**
 * WebBankCodeFudian Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 富滇银行支持快捷充值列表  不在目录中为不支持快捷充值
 */
public class WebBankCodeFudian extends com.hurong.core.model.BaseModel {

	/**
	 * id
	 */
    protected Long id;
    /**
     * 银行编号
     */
	protected String cardCode;
	/**
	 * 银行名称
	 */
	protected String bankName;
	/**
	 * 单笔限额
	 */
	protected java.math.BigDecimal signDealQuota;
	/**
	 * 日限额
	 */
	protected java.math.BigDecimal dayDealQuota;
	/**
	 * 网关单笔限额
	 */
	protected java.math.BigDecimal gatewaySignDealQuota;
	/**
	 * 网关日限额
	 */
	protected java.math.BigDecimal gatewayDayDealQuota;
	/**
	 * 是否支持快捷支付
	 */
	protected Integer isSupportShortcut;


	/**
	 * Default Empty Constructor for class WebBankCodeFudian
	 */
	public WebBankCodeFudian () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class WebBankCodeFudian
	 */
	public WebBankCodeFudian (
		 Long in_id
        ) {
		this.setId(in_id);
    }


	public BigDecimal getGatewaySignDealQuota() {
		return gatewaySignDealQuota;
	}

	public void setGatewaySignDealQuota(BigDecimal gatewaySignDealQuota) {
		this.gatewaySignDealQuota = gatewaySignDealQuota;
	}

	public BigDecimal getGatewayDayDealQuota() {
		return gatewayDayDealQuota;
	}

	public void setGatewayDayDealQuota(BigDecimal gatewayDayDealQuota) {
		this.gatewayDayDealQuota = gatewayDayDealQuota;
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
	 * 银行编码	 * @return String
	 * @hibernate.property column="cardCode" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getCardCode() {
		return this.cardCode;
	}
	
	/**
	 * Set the cardCode
	 */	
	public void setCardCode(String aValue) {
		this.cardCode = aValue;
	}	

	/**
	 * 银行名称	 * @return String
	 * @hibernate.property column="bankName" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBankName() {
		return this.bankName;
	}
	
	/**
	 * Set the bankName
	 */	
	public void setBankName(String aValue) {
		this.bankName = aValue;
	}	

	/**
	 * 单笔限额	 * @return java.math.BigDecimal
	 * @hibernate.property column="signDealQuota" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSignDealQuota() {
		return this.signDealQuota;
	}
	
	/**
	 * Set the signDealQuota
	 */	
	public void setSignDealQuota(java.math.BigDecimal aValue) {
		this.signDealQuota = aValue;
	}	

	/**
	 * 日限额	 * @return java.math.BigDecimal
	 * @hibernate.property column="dayDealQuota" type="java.math.BigDecimal" length="20" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getDayDealQuota() {
		return this.dayDealQuota;
	}
	
	/**
	 * Set the dayDealQuota
	 */	
	public void setDayDealQuota(java.math.BigDecimal aValue) {
		this.dayDealQuota = aValue;
	}	

	/**
	 * 1支持0不支持	 * @return Integer
	 * @hibernate.property column="isSupportShortcut" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsSupportShortcut() {
		return this.isSupportShortcut;
	}
	
	/**
	 * Set the isSupportShortcut
	 */	
	public void setIsSupportShortcut(Integer aValue) {
		this.isSupportShortcut = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof WebBankCodeFudian)) {
			return false;
		}
		WebBankCodeFudian rhs = (WebBankCodeFudian) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.cardCode, rhs.cardCode)
				.append(this.bankName, rhs.bankName)
				.append(this.signDealQuota, rhs.signDealQuota)
				.append(this.dayDealQuota, rhs.dayDealQuota)
				.append(this.isSupportShortcut, rhs.isSupportShortcut)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.cardCode) 
				.append(this.bankName) 
				.append(this.signDealQuota) 
				.append(this.dayDealQuota) 
				.append(this.isSupportShortcut) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("cardCode", this.cardCode) 
				.append("bankName", this.bankName) 
				.append("signDealQuota", this.signDealQuota) 
				.append("dayDealQuota", this.dayDealQuota) 
				.append("isSupportShortcut", this.isSupportShortcut) 
				.toString();
	}



}
