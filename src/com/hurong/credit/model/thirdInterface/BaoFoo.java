package com.hurong.credit.model.thirdInterface;

public class BaoFoo{
	
	public static final String CHARSETUTF8="utf-8";
	/**
	 * 商户号
	 */
	public String MemberID;
	/**
	 * 终端号
	 */
	public String TerminalID;
	/**
	 * 接口版本号
	 */
	public String InterfaceVersion;
	/**
	 * 接口加密类型
	 */
	public String KeyType;
	/**
	 * 支付类型
	 */
	public String PayID;
	/**
	 * 交易时间
	 */
	public String TradeDate;
	/**
	 * 交易流水号：8-20位
	 */
	public String TransID;
	/**
	 * 交易金额（单位wei分）
	 */
	public String OrderMoney;
	/**
	 * 通知商户地址(页面重定向地址)前台地址(URL编码)
	 * String类型限制长度为不超过255
	 */
	public String PageUrl;
	
	/**
	 * 底层通知地址(后台地址)(URL编码)
	 * String类型限制长度为不超过255
	 */
	public String ReturnUrl;
	/**
	 * 通知方式
	 * (URL编码)
	 * String类型限制长度为不超过255
	 */
	public String NoticeType;
	/**
	 * 签名
	 * (URL编码)
	 * String类型限制长度为不超过255
	 */
	public String sign;
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getTerminalID() {
		return TerminalID;
	}
	public void setTerminalID(String terminalID) {
		TerminalID = terminalID;
	}
	public String getInterfaceVersion() {
		return InterfaceVersion;
	}
	public void setInterfaceVersion(String interfaceVersion) {
		InterfaceVersion = interfaceVersion;
	}
	public String getKeyType() {
		return KeyType;
	}
	public void setKeyType(String keyType) {
		KeyType = keyType;
	}
	public String getPayID() {
		return PayID;
	}
	public void setPayID(String payID) {
		PayID = payID;
	}
	public String getTradeDate() {
		return TradeDate;
	}
	public void setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
	}
	public String getTransID() {
		return TransID;
	}
	public void setTransID(String transID) {
		TransID = transID;
	}
	public String getOrderMoney() {
		return OrderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		OrderMoney = orderMoney;
	}
	public String getPageUrl() {
		return PageUrl;
	}
	public void setPageUrl(String pageUrl) {
		PageUrl = pageUrl;
	}
	public String getReturnUrl() {
		return ReturnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		ReturnUrl = returnUrl;
	}
	public String getNoticeType() {
		return NoticeType;
	}
	public void setNoticeType(String noticeType) {
		NoticeType = noticeType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
} 