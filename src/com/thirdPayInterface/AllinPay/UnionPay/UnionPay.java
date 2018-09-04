package com.thirdPayInterface.AllinPay.UnionPay;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UnionPay {
	
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 字符集编码 默认"UTF-8"
	 */
	private String encoding;
	/**
	 * 签名方法 01 RSA
	 */
	private String signMethod;
	/**
	 * 交易类型 01-消费
	 */
	private String txnType;
	/**
	 * 交易子类型 01:自助消费 02:订购 03:分期付款
	 */
	private String txnSubType;
	/**
	 * bizType
	 */
	private String bizType;
	/**
	 * 渠道类型，07-PC，08-手机
	 */
	private String channelType;
	/**
	 * 前台通知地址 ，控件接入方式无作用
	 */
	private String frontUrl;
	/**
	 * 后台通知地址
	 */
	private String backUrl;
	/**
	 * 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
	 */
	private String accessType;
	/**
	 * 商户号码
	 */
	private String merId;
	/**
	 * 商户订单号，8-40位数字字母
	 */
	private String orderId;
	/**
	 * 订单发送时间，取系统时间
	 */
	private String txnTime;
	/**
	 * 交易金额，单位分
	 */
	private String txnAmt;
	/**
	 * 交易币种 默认为 156
	 */
	private String currencyCode;
	/**
	 * 商户自定义保留域，交易应答时会原样返回
	 */
	private String reqReserved;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnSubType() {
		return txnSubType;
	}
	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getFrontUrl() {
		return frontUrl;
	}
	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getReqReserved() {
		return reqReserved;
	}
	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}
	
}
