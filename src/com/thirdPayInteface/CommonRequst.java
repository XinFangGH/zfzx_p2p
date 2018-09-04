package com.thirdPayInteface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 第三方支付公共请求参数列表
 * @author Administrator
 *
 */
public class CommonRequst {
	
	private String bussinessType;//业务类型 
	private String requsetNo;//请求第三方接口流水号
	private String thirdPayConfig;//第三方支付类型
	private String thirdPayConfigId;//第三方支付账号
	private String thirdPayConfigId0;//第三方支付别名（或者默认第三方账号）
	private String custMemberId;//客户标Id
	private String custMemberType;//客户类型
	
	private String trueName;//真实姓名
	private String cardType;//证件类型
	private String cardNumber;//证件号码
	
	private String telephone;//手机号码
	private String email;//邮箱
	
	private BigDecimal  amount=new BigDecimal("0");//表示单笔交易金额（充值，取现，投标，还款）
	
	private BigDecimal fee=new BigDecimal("0");//表示单笔交易额外费用（充值手续费,取现手续费,单笔交易平台分润）
	/**
	 * 债权交易记录的投资流水号
	 */
	private String oldBidRequestNo;
	
	/**
	 * 银行key值(充值银行编码，绑卡银行卡编码)
	 */
	private String bankCode;
	/**
	 * 绑卡的银行卡卡号
	 */
	private String bankCardNo;
	/**
	 * 绑定的银行卡属于对公银行卡还是对私银行卡
	 */
	private String bankCardType;
	
	private String province;//银行卡所在省份
	
	private String city;//银行卡所在的城市  
	
	/**
	 * 交易发生时间
	 */
	private Date transactionTime;
	/**
	 * 银行卡号
	 */
	private String bankCardNumber;
	/**
	 * 自动授权类型
	 */
	private String autoAuthorizationType;

	/**
	 * 标的主键Id
	 */
	private String bidId;
	/**
	 * 标的标识
	 */
	private String bidType;
	/**
	 * 标的name
	 */
	private String bidName;
	/**
	 * 招标项目总金额
	 */
	private BigDecimal planMoney;
	/**
	 * 借款人第三方账号
	 */
	private String loaner_thirdPayflagId;
	
	
	/**
	 * 标的状态
	 */
	private String bidIdStatus;
	/**
	 * 接口业务处理状态返回状态（用来标识是否立即处理业务数据）
	 * 主要作用是用来标识该业务是否在接到通知后进行业务处理还是等待服务器端的第三方通知
	 * 数据类型来源于ThirdPayConstants  常量类
	 */
	private String returnType;
	/**
	 * 验证码
	 */
	private String checkCode;
	//企业用户名
	private String enterpriseName ;
	//开户银行许可证
	private String bankLicense;
	//组织机构代码
	private String orgNo;
	//营业执照编号
	private String businessLicense;
	//税务登记号 
	private String taxNo;
	//法人姓名
	private String legal;
	//法人身份证号码
	private String legalIdNo;
	//联系人
	private String contact;
	//联系人电话
	private String contactPhone; 
	//放款list
	private  List<CommonRequestInvestRecord> loanList;
	/**
	 * 还款list
	 */
	private List<CommonRequestInvestRecord> repaymemntList;
	
	/**
	 * 单笔查询类型（充值，取现，投标，放款，转账）
	 */
	private String queryType;
	/**
	 * 审核是否通过字段
	 */
	private Boolean confimStatus=false;
	
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getBankLicense() {
		return bankLicense;
	}
	public void setBankLicense(String bankLicense) {
		this.bankLicense = bankLicense;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getLegalIdNo() {
		return legalIdNo;
	}
	public void setLegalIdNo(String legalIdNo) {
		this.legalIdNo = legalIdNo;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	 
	public String getRequsetNo() {
		return requsetNo;
	}
	public void setRequsetNo(String requsetNo) {
		this.requsetNo = requsetNo;
	}
	public String getThirdPayConfigId() {
		return thirdPayConfigId;
	}
	public void setThirdPayConfigId(String thirdPayConfigId) {
		this.thirdPayConfigId = thirdPayConfigId;
	}
	public String getThirdPayConfigId0() {
		return thirdPayConfigId0;
	}
	public void setThirdPayConfigId0(String thirdPayConfigId0) {
		this.thirdPayConfigId0 = thirdPayConfigId0;
	}
	public String getCustMemberId() {
		return custMemberId;
	}
	public void setCustMemberId(String custMemberId) {
		this.custMemberId = custMemberId;
	}
	public String getCustMemberType() {
		return custMemberType;
	}
	public void setCustMemberType(String custMemberType) {
		this.custMemberType = custMemberType;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setThirdPayConfig(String thirdPayConfig) {
		this.thirdPayConfig = thirdPayConfig;
	}
	public String getThirdPayConfig() {
		return thirdPayConfig;
	}
	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}
	public String getBussinessType() {
		return bussinessType;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getAutoAuthorizationType() {
		return autoAuthorizationType;
	}
	public void setAutoAuthorizationType(String autoAuthorizationType) {
		this.autoAuthorizationType = autoAuthorizationType;
	}

	public String getBidId() {
		return bidId;
	}
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	public String getBidType() {
		return bidType;
	}
	public void setBidType(String bidType) {
		this.bidType = bidType;
	}
	public String getBidName() {
		return bidName;
	}
	public void setBidName(String bidName) {
		this.bidName = bidName;
	}
	public String getLoaner_thirdPayflagId() {
		return loaner_thirdPayflagId;
	}
	public void setLoaner_thirdPayflagId(String loanerThirdPayflagId) {
		loaner_thirdPayflagId = loanerThirdPayflagId;
	}
	public String getBidIdStatus() {
		return bidIdStatus;
	}
	public void setBidIdStatus(String bidIdStatus) {
		this.bidIdStatus = bidIdStatus;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	public String getBankCardType() {
		return bankCardType;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}
	public BigDecimal getPlanMoney() {
		return planMoney;
	}
	public void setRepaymemntList(List<CommonRequestInvestRecord> repaymemntList) {
		this.repaymemntList = repaymemntList;
	}
	public List<CommonRequestInvestRecord> getRepaymemntList() {
		return repaymemntList;
	}
	public void setLoanList(List<CommonRequestInvestRecord> loanList) {
		this.loanList = loanList;
	}
	public List<CommonRequestInvestRecord> getLoanList() {
		return loanList;
	}
	public void setOldBidRequestNo(String oldBidRequestNo) {
		this.oldBidRequestNo = oldBidRequestNo;
	}
	public String getOldBidRequestNo() {
		return oldBidRequestNo;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setConfimStatus(Boolean confimStatus) {
		this.confimStatus = confimStatus;
	}
	public Boolean getConfimStatus() {
		return confimStatus;
	}

	
	
	

	
	
}
