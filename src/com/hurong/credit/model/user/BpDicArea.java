package com.hurong.credit.model.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
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
 * BpDicArea Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 区域表
 */
public class BpDicArea extends com.hurong.core.model.BaseModel {

    protected String AreaCode;
	protected String AreaName;
	protected String ParentCode;


	/**
	 * Default Empty Constructor for class BpDicArea
	 */
	public BpDicArea () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BpDicArea
	 */
	public BpDicArea (
		 String in_AreaCode
        ) {
		this.setAreaCode(in_AreaCode);
    }

    

	/**
	 * 	 * @return String
     * @hibernate.id column="AreaCode" type="java.lang.String" generator-class="native"
	 */
	public String getAreaCode() {
		return this.AreaCode;
	}
	
	/**
	 * Set the AreaCode
	 */	
	public void setAreaCode(String aValue) {
		this.AreaCode = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="AreaName" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getAreaName() {
		return this.AreaName;
	}
	
	/**
	 * Set the AreaName
	 * @spring.validator type="required"
	 */	
	public void setAreaName(String aValue) {
		this.AreaName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="ParentCode" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getParentCode() {
		return this.ParentCode;
	}
	
	/**
	 * Set the ParentCode
	 * @spring.validator type="required"
	 */	
	public void setParentCode(String aValue) {
		this.ParentCode = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpDicArea)) {
			return false;
		}
		BpDicArea rhs = (BpDicArea) object;
		return new EqualsBuilder()
				.append(this.AreaCode, rhs.AreaCode)
				.append(this.AreaName, rhs.AreaName)
				.append(this.ParentCode, rhs.ParentCode)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.AreaCode) 
				.append(this.AreaName) 
				.append(this.ParentCode) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("AreaCode", this.AreaCode) 
				.append("AreaName", this.AreaName) 
				.append("ParentCode", this.ParentCode) 
				.toString();
	}



}
