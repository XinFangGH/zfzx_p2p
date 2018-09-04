package com.hurong.credit.model.creditFlow.bonusSystem.record;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 积分记录流水表
 * @author LIUSL
 *
 */
public class WebBonusRecord extends com.hurong.core.model.BaseModel {
	
	public WebBonusRecord(){
		this.createTime = new Date();
	}
	
    protected Long recordId;      //积分ID
	protected Long recordNumber;  //奖励数目
	/**
	 * recordDirector 表示积分累积方式
	 * 1：增加
	 * 2：减少
	 */
	protected String recordDirector;   //积分收支方向
	protected String customerType;	  //用户类型
	protected String customerId;   	  //用户ID
	protected String customerName;    //用户名称
	protected String bonusId;//		  //使用的积分规则
	protected String recordMsg;		  //积分备注信息
	protected java.util.Date createTime;  //创建日期
	
	/**
	 *  1普通积分
	 *  2活动积分
	 *  3论坛积分
	 */
	protected String operationType;//积分类型   
	protected String bonusKey;//      //积分规则的Key值 --操作类型
	protected String bonusIntention;  //积分用意
	protected String bonusDescription; //积分规则说明
	protected Long totalNumber;//用户当前积分
	protected String activityNumber; //活动编号
	
	protected Long totalRecordNumber;//累计积分
	protected Long usableRecordNumber;//可用积分
	protected Long useRecordNumber;//已用积分
	
	
	public Long getTotalRecordNumber() {
		return totalRecordNumber;
	}
	public void setTotalRecordNumber(Long totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}
	public Long getUsableRecordNumber() {
		return usableRecordNumber;
	}
	public void setUsableRecordNumber(Long usableRecordNumber) {
		this.usableRecordNumber = usableRecordNumber;
	}
	public Long getUseRecordNumber() {
		return useRecordNumber;
	}
	public void setUseRecordNumber(Long useRecordNumber) {
		this.useRecordNumber = useRecordNumber;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	public Long getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(Long recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getRecordDirector() {
		return recordDirector;
	}
	public void setRecordDirector(String recordDirector) {
		this.recordDirector = recordDirector;
	}
	public String getRecordMsg() {
		return recordMsg;
	}
	public void setRecordMsg(String recordMsg) {
		this.recordMsg = recordMsg;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBonusId() {
		return bonusId;
	}
	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getBonusKey() {
		return bonusKey;
	}
	public void setBonusKey(String bonusKey) {
		this.bonusKey = bonusKey;
	}
	public String getBonusIntention() {
		return bonusIntention;
	}
	public void setBonusIntention(String bonusIntention) {
		this.bonusIntention = bonusIntention;
	}
	public String getBonusDescription() {
		return bonusDescription;
	}
	public void setBonusDescription(String bonusDescription) {
		this.bonusDescription = bonusDescription;
	}
	public Long getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}
	public String getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}
	
	
	

}
