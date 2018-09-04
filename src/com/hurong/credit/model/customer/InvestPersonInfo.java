package com.hurong.credit.model.customer;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * InvestPersonInfo Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class InvestPersonInfo extends com.zhiwei.core.model.BaseModel {

    protected Long investId;
	protected Long investPersonId;
	protected String investPersonName;
	protected java.math.BigDecimal investMoney;
	protected java.math.BigDecimal investPercent;
	protected String remark;
	protected String businessType;
	protected Long projectId; //项目id 
	
	protected Long moneyPlanId; //资金方案表的 id
	
	protected String orderNo;// 订单号
	protected Long bidPlanId;//标的id
	
	protected Integer bidtype=0;//投标类型 0 主动投标 1 自动投标

	protected String contractUrls; //合同路径
	
	private Short status;//投资记录状态 

public String getContractUrls() {
		return contractUrls;
	}

	public void setContractUrls(String contractUrls) {
		this.contractUrls = contractUrls;
	}

public Integer getBidtype() {
	return bidtype;
}

public void setBidtype(Integer bidtype) {
	this.bidtype = bidtype;
}
	
	
	public Long getBidPlanId() {
	return bidPlanId;
}

public void setBidPlanId(Long bidPlanId) {
	this.bidPlanId = bidPlanId;
}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getMoneyPlanId() {
		return moneyPlanId;
	}

	public void setMoneyPlanId(Long moneyPlanId) {
		this.moneyPlanId = moneyPlanId;
	}

	protected Short fundResource; //资金来源  add by zcb 2014--2-12 1 企业 0个人 // 是企业还是个人 针对线下投资用户  Yuanzc
	
	protected Short persionType; //属于线上客户还是线下客户 1线下 0线上 Yuanzc
	
	
	public Short getPersionType() {
		return persionType;
	}

	public void setPersionType(Short persionType) {
		this.persionType = persionType;
	}

	//不与数据库映射字段
	protected String loanUserName;//借款人名称
	protected Date startDate;//投资起息日
	protected BigDecimal projectMoney;//借款金额
	
	/**
	 * Default Empty Constructor for class InvestPersonInfo
	 */
	public InvestPersonInfo () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class InvestPersonInfo
	 */
	public InvestPersonInfo (
		 Long in_investId
        ) {
		this.setInvestId(in_investId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="investId" type="java.lang.Long" generator-class="native"
	 */
	public Long getInvestId() {
		return this.investId;
	}
	
	/**
	 * Set the investId
	 */	
	public void setInvestId(Long aValue) {
		this.investId = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="investPersonId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getInvestPersonId() {
		return this.investPersonId;
	}
	
	/**
	 * Set the investPersonId
	 * @spring.validator type="required"
	 */	
	public void setInvestPersonId(Long aValue) {
		this.investPersonId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="investPersonName" type="java.lang.String" length="30" not-null="true" unique="false"
	 */
	public String getInvestPersonName() {
		return this.investPersonName;
	}
	
	/**
	 * Set the investPersonName
	 * @spring.validator type="required"
	 */	
	public void setInvestPersonName(String aValue) {
		this.investPersonName = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="investMoney" type="java.math.BigDecimal" length="20" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getInvestMoney() {
		return this.investMoney;
	}
	
	/**
	 * Set the investMoney
	 * @spring.validator type="required"
	 */	
	public void setInvestMoney(java.math.BigDecimal aValue) {
		this.investMoney = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="investPercent" type="java.math.BigDecimal" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getInvestPercent() {
		return this.investPercent;
	}
	
	/**
	 * Set the investPercent
	 * @spring.validator type="required"
	 */	
	public void setInvestPercent(java.math.BigDecimal aValue) {
		this.investPercent = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof InvestPersonInfo)) {
			return false;
		}
		InvestPersonInfo rhs = (InvestPersonInfo) object;
		return new EqualsBuilder()
				.append(this.investId, rhs.investId)
				.append(this.investPersonId, rhs.investPersonId)
				.append(this.investPersonName, rhs.investPersonName)
				.append(this.investMoney, rhs.investMoney)
				.append(this.investPercent, rhs.investPercent)
				.append(this.remark, rhs.remark)
				.append(this.contractUrls, rhs.contractUrls)
				
				
				
				
				.isEquals();
	}
	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.investId) 
				.append(this.investPersonId) 
				.append(this.investPersonName) 
				.append(this.investMoney) 
				.append(this.investPercent) 
				.append(this.remark) 
				.append(this.projectId)
				
				.append(this.contractUrls)
				
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("investId", this.investId) 
				.append("investPersonId", this.investPersonId) 
				.append("investPersonName", this.investPersonName) 
				.append("investMoney", this.investMoney) 
				.append("investPercent", this.investPercent) 
				.append("remark", this.remark) 
				.append("projectId",this.projectId)
				.append("contractUrls",this.contractUrls)
				
				.toString();
	}

	public Short getFundResource() {
		return fundResource;
	}

	public void setFundResource(Short fundResource) {
		this.fundResource = fundResource;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getStatus() {
		return status;
	}



}
