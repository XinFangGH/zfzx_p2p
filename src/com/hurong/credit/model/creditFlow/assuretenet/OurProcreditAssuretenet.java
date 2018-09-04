package com.hurong.credit.model.creditFlow.assuretenet;
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
 * OurProcreditAssuretenet Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class OurProcreditAssuretenet extends com.hurong.core.model.BaseModel {

	private static final long serialVersionUID = 1L;
	protected Long assuretenetId;
	protected String assuretenet;
    protected String businessTypeKey; //业务类别key
    protected String businessTypeName;
    protected String operationTypeKey;  //业务种类key
    protected String operationTypeName;
    protected Long businessTypeGlobalId;
    protected Long operationTypeGlobalId;
    protected String customerType;//客户类型
    
    protected Long productId;//产品Id
    protected String outletopinion;//门店负责人意见
    protected Long projectId;//项目Id
    
    protected Boolean isfile;//是否归档
    protected String xxnums;//线下份数
    protected String remark;//归档备注
    

	public String getOperationTypeName() {
		return operationTypeName;
	}

	public void setOperationTypeName(String operationTypeName) {
		this.operationTypeName = operationTypeName;
	}

	public String getOperationTypeKey() {
		return operationTypeKey;
	}

	public void setOperationTypeKey(String operationTypeKey) {
		this.operationTypeKey = operationTypeKey;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getBusinessTypeKey() {
		return businessTypeKey;
	}

	public void setBusinessTypeKey(String businessTypeKey) {
		this.businessTypeKey = businessTypeKey;
	}

	/**
	 * Default Empty Constructor for class OurProcreditAssuretenet
	 */
	public OurProcreditAssuretenet () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class OurProcreditAssuretenet
	 */
	public OurProcreditAssuretenet (
		 Long in_assuretenetId
        ) {
		this.setAssuretenetId(in_assuretenetId);
    }

    

	public Long getAssuretenetId() {
		return assuretenetId;
	}

	public void setAssuretenetId(Long assuretenetId) {
		this.assuretenetId = assuretenetId;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="assuretenet" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getAssuretenet() {
		return this.assuretenet;
	}
	
	/**
	 * Set the assuretenet
	 */	
	public void setAssuretenet(String aValue) {
		this.assuretenet = aValue;
	}	

	public Long getBusinessTypeGlobalId() {
		return businessTypeGlobalId;
	}

	public void setBusinessTypeGlobalId(Long businessTypeGlobalId) {
		this.businessTypeGlobalId = businessTypeGlobalId;
	}

	public Long getOperationTypeGlobalId() {
		return operationTypeGlobalId;
	}

	public void setOperationTypeGlobalId(Long operationTypeGlobalId) {
		this.operationTypeGlobalId = operationTypeGlobalId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OurProcreditAssuretenet)) {
			return false;
		}
		OurProcreditAssuretenet rhs = (OurProcreditAssuretenet) object;
		return new EqualsBuilder()
				.append(this.assuretenetId, rhs.assuretenetId)
				.append(this.assuretenet, rhs.assuretenet)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.assuretenetId) 
				.append(this.assuretenet) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("assuretenetId", this.assuretenetId) 
				.append("assuretenet", this.assuretenet) 
				.toString();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getOutletopinion() {
		return outletopinion;
	}

	public void setOutletopinion(String outletopinion) {
		this.outletopinion = outletopinion;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Boolean getIsfile() {
		return isfile;
	}

	public void setIsfile(Boolean isfile) {
		this.isfile = isfile;
	}

	public String getXxnums() {
		return xxnums;
	}

	public void setXxnums(String xxnums) {
		this.xxnums = xxnums;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
