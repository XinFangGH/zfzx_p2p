package com.hurong.credit.model.p2p.loan;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author 
 *
 */
/**
 * P2pLoanProduct Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * p2p贷款产品
 */
public class P2pLoanProduct extends com.zhiwei.core.model.BaseModel {

    protected Long productId;
    /**
     * 产品名称
     */
	protected String productName;
	/**
	 * 业务品种 person=个人贷，enterprise=企业贷
	 */
	protected String operationType;
	/**
	 * 使用范围
	 */
	protected String userScope;
	/**
	 * 产品状态,1=填写中，0=已删除，4=发布中,5=已关闭
	 */
	protected Long productState;
	/**
	 * 还款方式 , sameprincipalandInterest=等额本息，sameprincipal=等额本金，sameprincipalsameInterest=等本等息，singleInterest=按期收息，到期还本
	 * otherMothod=其他还款方式
	 */
	protected String accrualtype; 
	/**
	 * 还款周期,monthPay=月，yearPay=年，dayPay=日，seasonPay=季
	 */
	protected String payaccrualType; 
	/**
	 * 前置付息，0为否，1为是
	 */
	protected Integer isPreposePayAccrual; 
	/**
	 * 一次性支付全部利息，0为否，1为是
	 */
	protected Integer isInterestByOneTime; 
	/**
	 * 贷款额度起
	 */
	protected BigDecimal loanStartMoney;
	/**
	 * 贷款额度至
	 */
	protected BigDecimal loanEndMoney;
	/**
	 * 审批时间起
	 */
	protected Long approveStartTime;
	/**
	 * 审批时间至
	 */
	protected Long approveEndTime;
	//不与数据库映射
	/**
	 * 贷款条件和材料
	 */
	List<P2pLoanConditionOrMaterial> conditionList;
	List<P2pLoanConditionOrMaterial> conditionMaterialList;
	/**
	 * 贷款期限利率
	 */
	List<P2pLoanRate> loanRateList;
	
	public Integer listSize;
	public String productStateValue;
	public String operationTypeValue;
	
	/**
	 * Default Empty Constructor for class P2pLoanProduct
	 */
	public P2pLoanProduct () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class P2pLoanProduct
	 */
	public P2pLoanProduct (
		 Long in_productId
        ) {
		this.setProductId(in_productId);
    }

    


	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public List<P2pLoanRate> getLoanRateList() {
		return loanRateList;
	}

	public void setLoanRateList(List<P2pLoanRate> loanRateList) {
		this.loanRateList = loanRateList;
	}

	public List<P2pLoanConditionOrMaterial> getConditionMaterialList() {
		return conditionMaterialList;
	}

	public void setConditionMaterialList(
			List<P2pLoanConditionOrMaterial> conditionMaterialList) {
		this.conditionMaterialList = conditionMaterialList;
	}

	public BigDecimal getLoanStartMoney() {
		return loanStartMoney;
	}

	public void setLoanStartMoney(BigDecimal loanStartMoney) {
		this.loanStartMoney = loanStartMoney;
	}

	public BigDecimal getLoanEndMoney() {
		return loanEndMoney;
	}

	public void setLoanEndMoney(BigDecimal loanEndMoney) {
		this.loanEndMoney = loanEndMoney;
	}

	public Long getApproveStartTime() {
		return approveStartTime;
	}

	public void setApproveStartTime(Long approveStartTime) {
		this.approveStartTime = approveStartTime;
	}

	public Long getApproveEndTime() {
		return approveEndTime;
	}

	public void setApproveEndTime(Long approveEndTime) {
		this.approveEndTime = approveEndTime;
	}

	public List<P2pLoanConditionOrMaterial> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<P2pLoanConditionOrMaterial> conditionList) {
		this.conditionList = conditionList;
	}

	/**
	 * 	 * @return Long
     * @hibernate.id column="productId" type="java.lang.Long" generator-class="native"
	 */
	public Long getProductId() {
		return this.productId;
	}
	
	/**
	 * Set the productId
	 */	
	public void setProductId(Long aValue) {
		this.productId = aValue;
	}	

	public String getProductStateValue() {
		return productStateValue;
	}

	public void setProductStateValue(String productStateValue) {
		this.productStateValue = productStateValue;
	}

	public String getOperationTypeValue() {
		return operationTypeValue;
	}

	public void setOperationTypeValue(String operationTypeValue) {
		this.operationTypeValue = operationTypeValue;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="productName" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProductName() {
		return this.productName;
	}
	
	/**
	 * Set the productName
	 * @spring.validator type="required"
	 */	
	public void setProductName(String aValue) {
		this.productName = aValue;
	}	

	public Long getProductState() {
		return productState;
	}

	public void setProductState(Long productState) {
		this.productState = productState;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="operationType" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getOperationType() {
		return this.operationType;
	}
	
	/**
	 * Set the operationType
	 * @spring.validator type="required"
	 */	
	public void setOperationType(String aValue) {
		this.operationType = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="userScope" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getUserScope() {
		return this.userScope;
	}
	
	/**
	 * Set the userScope
	 * @spring.validator type="required"
	 */	
	public void setUserScope(String aValue) {
		this.userScope = aValue;
	}	

	public String getAccrualtype() {
		return accrualtype;
	}

	public void setAccrualtype(String accrualtype) {
		this.accrualtype = accrualtype;
	}

	public String getPayaccrualType() {
		return payaccrualType;
	}

	public void setPayaccrualType(String payaccrualType) {
		this.payaccrualType = payaccrualType;
	}

	public Integer getIsPreposePayAccrual() {
		return isPreposePayAccrual;
	}

	public void setIsPreposePayAccrual(Integer isPreposePayAccrual) {
		this.isPreposePayAccrual = isPreposePayAccrual;
	}

	public Integer getIsInterestByOneTime() {
		return isInterestByOneTime;
	}

	public void setIsInterestByOneTime(Integer isInterestByOneTime) {
		this.isInterestByOneTime = isInterestByOneTime;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof P2pLoanProduct)) {
			return false;
		}
		P2pLoanProduct rhs = (P2pLoanProduct) object;
		return new EqualsBuilder()
				.append(this.productId, rhs.productId)
				.append(this.productName, rhs.productName)
				.append(this.operationType, rhs.operationType)
				.append(this.userScope, rhs.userScope)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.productId) 
				.append(this.productName) 
				.append(this.operationType) 
				.append(this.userScope) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("productId", this.productId) 
				.append("productName", this.productName) 
				.append("operationType", this.operationType) 
				.append("userScope", this.userScope) 
				.toString();
	}



}
