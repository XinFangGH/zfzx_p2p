package com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager;
import java.math.BigDecimal;
import java.util.Date;

import com.zhiwei.core.model.BaseModel;

public class InvestprojectPlan extends BaseModel{
	
	private Long planId;

	private String planName;
	private String planNumber;
	private String planType;
	private BigDecimal planmoney;
	private Short planState;
	private Date planStartDate;
	
	private Date planEndDate;
	private Date planFullTime;
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public BigDecimal getPlanmoney() {
		return planmoney;
	}
	public void setPlanmoney(BigDecimal planmoney) {
		this.planmoney = planmoney;
	}
	public Short getPlanState() {
		return planState;
	}
	public void setPlanState(Short planState) {
		this.planState = planState;
	}
	public Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public Date getPlanFullTime() {
		return planFullTime;
	}
	public void setPlanFullTime(Date planFullTime) {
		this.planFullTime = planFullTime;
	}
	
	
	
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
}