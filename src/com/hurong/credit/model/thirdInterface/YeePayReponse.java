package com.hurong.credit.model.thirdInterface;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;



@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class YeePayReponse {
	/**
	 * 商户编号
	 */
	@XmlAttribute
	private String platformNo;
	/**
	 * 商户平台会员标识 
	 */
	private String platformUserNo;
	/**
	 * 请求流水号
	 */
	private String requestNo;
	/**
	 * 返回编码
	 */
	private String code;
	/**
	 * 返回编码描述
	 */
	private String description;
	/**
	 * 记录列表list
	 */
	@XmlElementWrapper(name = "records")
	@XmlElement(name = "record")
	private List<Record> records;
	/**
	 * 返回接口类型
	 */
	private String service;
	/**
	 * 会员类型
	 */
	private String memberType;
	/**
	 * 会员激活状态
	 */
	private String activeStatus;
	/**
	 * 会员账户余额
	 */
	private String balance;
	/**
	 * 会员账户可用金额
	 */
	private String availableAmount;
	/**
	 * 会员账户冻结金额
	 */
	private String freezeAmount;
	/**
	 * 会员绑定银行卡号
	 */
	private String cardNo;
	
	/**
	 * 会员绑定银行卡的状态
	 */
	private String cardStatus;
	/**
	 * 绑定的银行卡编号
	 */
	private String bank;
	/**
	 * 绑定的银行卡名称
	 */
	private String bankName;
	/**
	 * 是否已授权自动投标,true 或则 false
	 */
	public  String autoTender;
	/**
	 * 表示用户是否已开通快捷支付。NORMAL 表示未升级，UPGRADE 表示已升级
	 */
	private String paySwift;
	
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}
	public String getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	
	public String getPlatformNo() {
		return platformNo;
	}
	public void setPlatformNo(String platformNo) {
		this.platformNo = platformNo;
	}
	public String getPlatformUserNo() {
		return platformUserNo;
	}
	public void setPlatformUserNo(String platformUserNo) {
		this.platformUserNo = platformUserNo;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Record> getRecords() {
		return records;
	}
	public void setRecords(List<Record> records) {
		this.records = records;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return service;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBank() {
		return bank;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setAutoTender(String autoTender) {
		this.autoTender = autoTender;
	}
	public String getAutoTender() {
		return autoTender;
	}
	public void setPaySwift(String paySwift) {
		this.paySwift = paySwift;
	}
	public String getPaySwift() {
		return paySwift;
	}
	
	
}
