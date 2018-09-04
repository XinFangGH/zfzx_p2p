package com.hurong.credit.model.thirdInterface;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
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
 * CsBank Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class CsBank extends com.zhiwei.core.model.BaseModel {

    protected Long bankid;
	protected String bankname;
	protected String remarks;
	protected String typeKey;


	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	/**
	 * Default Empty Constructor for class CsBank
	 */
	public CsBank () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class CsBank
	 */
	public CsBank (
		 Long in_bankid
        ) {
		this.setBankid(in_bankid);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="bankid" type="java.lang.Long" generator-class="native"
	 */
	public Long getBankid() {
		return this.bankid;
	}
	
	/**
	 * Set the bankid
	 */	
	public void setBankid(Long aValue) {
		this.bankid = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="bankname" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getBankname() {
		return this.bankname;
	}
	
	/**
	 * Set the bankname
	 */	
	public void setBankname(String aValue) {
		this.bankname = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="remarks" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	/**
	 * Set the remarks
	 */	
	public void setRemarks(String aValue) {
		this.remarks = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CsBank)) {
			return false;
		}
		CsBank rhs = (CsBank) object;
		return new EqualsBuilder()
				.append(this.bankid, rhs.bankid)
				.append(this.bankname, rhs.bankname)
				.append(this.remarks, rhs.remarks)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.bankid) 
				.append(this.bankname) 
				.append(this.remarks) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("bankid", this.bankid) 
				.append("bankname", this.bankname) 
				.append("remarks", this.remarks) 
				.toString();
	}



}
