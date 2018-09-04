package com.hurong.credit.model.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author 
 *
 */
/**
 * PlBidPlan Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 招标计划
 */
public class PlBidPlanFilter extends com.hurong.core.model.BaseModel {

	protected String bidType; //招标类别
	protected String payIntersetWay; //还款方式
	protected String payTime;//还款时间
	
	protected String bidMoney; //招标金额
	
	protected String businessFrom; //业务 来源
	protected String proKeepType; //业务类别
	protected String bidState; //招标状态
	
	protected String proType;//招标类型
	
	protected String yearInterestRate;//利率
	protected String keepCreditlevelName;//信用等级
	
	

	public String getKeepCreditlevelName() {
		return keepCreditlevelName;
	}

	public void setKeepCreditlevelName(String keepCreditlevelName) {
		this.keepCreditlevelName = keepCreditlevelName;
	}

	public String getBusinessFrom() {
		return businessFrom;
	}

	public void setBusinessFrom(String businessFrom) {
		this.businessFrom = businessFrom;
	}

	public String getProKeepType() {
		return proKeepType;
	}

	public void setProKeepType(String proKeepType) {
		this.proKeepType = proKeepType;
	}

	public String getBidState() {
		return bidState;
	}

	public void setBidState(String bidState) {
		this.bidState = bidState;
	}

	public String getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(String bidMoney) {
		this.bidMoney = bidMoney;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getPayIntersetWay() {
		return payIntersetWay;
	}

	public void setPayIntersetWay(String payIntersetWay) {
		this.payIntersetWay = payIntersetWay;
	}

	public String getBidType() {
		return bidType;
	}

	public void setBidType(String bidType) {
		this.bidType = bidType;
	}

	public String getYearInterestRate() {
		return yearInterestRate;
	}

	public void setYearInterestRate(String yearInterestRate) {
		this.yearInterestRate = yearInterestRate;
	}

}
