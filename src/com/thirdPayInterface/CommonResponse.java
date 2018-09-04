package com.thirdPayInterface;

import com.thirdPayInterface.Fuiou.FuiouResponse.Detail;
import com.thirdPayInterface.YeePay.YeePayReponse;

import java.math.BigDecimal;
import java.util.List;


/**
 * 所有第三方通用回调通知实体对象
 * @author Administrator
 *
 */
public class CommonResponse {
	
	/**
	 * 回调通知响应码类型
	 */
	/**
	 * 交易申请成功
	 */
	public static final String RESPONSECODE_APPLAY="responsecode_apply";
	/**
	 * 交易成功
	 */
	public static final String RESPONSECODE_SUCCESS="responsecode_success";
	/**
	 * 交易失败
	 */
	public static final String RESPONSECODE_FAILD="responsecode_faild";
	/**
	 * 交易成功后在退回(适用于双乾提现成功后退回)
	 */
	public static final String RESPONSECODE_RETURN="responsecode_return";
	/**
	 * 交易冻结
	 */
	public static final String RESPONSECODE_FREEZE="responsecode_freeze";
	/**
	 * 没有对接此业务类型
	 */
	public static final String RESPONSECODE_NOTBUSINESS="responsecode_notBusiness";
	/**
	 * 签名验证失败
	 */
	public static final String RESPONSECODE_ISNOTPASSSIGN="responsecode_isNotPassSign";
	/**
	 * 没有接收到回调参数
	 */
	public static final String RESPONSECODE_NOTRECIVEPARAMETER="responsecode_notReciveParmeter";
	/**
	 * 系统报错
	 */
	public static final String RESPONSECODE_SYSTEMERROR="responsecode_systemError";
	/**
	 * 资金交易状态2=成功、7=冻结
	 */
	public static final String DEALSTATE2="2";
	public static final String DEALSTATE7="7";
	
	/**
	 * 直连
	 */
	public static final String RETURNTYPE_JOSN="returntype_josn";//直连接口,返回josn
	/**
	 * 网关
	 */
	public static final String RETURNTYPE_HTML="returntype_html";//网关接口,返回html
	
	/**
	 * 调用传入的参数
	 */
	public CommonRequst CommonRequst;
	
	/**
	 * 回调通知响应编码（第三方通知的响应码解析）
	 */
	private  String responsecode;
	/**
	 * 回调通知响应编码描述（第三方通知的响应码解析描述）
	 */
	private String responseMsg;
	/**
	 * 返回给第三方支付服务器数据类型（json串还是直接页面输出）
	 */
	private String returnType;
	/**
	 * 返回给第三方支付的响应参数
	 */
	private String returnMsg;
	/**
	 * 返回系统中每次请求的流水号
	 */
	private String  requestNo;
	/**
	 * 第三方的流水号
	 */
	private String loanNo;
	/**
	 * 业务类型（指调用接口的具体业务类型）  来自第三方支付常量池
	 */
	private String bussinessType;
	/**
	 * 查询的交易记录
	 */
	private List<CommonRecord>  recordList;
	private String custMemberId;//客户标Id
	private String custMemberType;//客户类型
	private String thirdPayConfig;//第三方支付类型
	private String thirdPayConfigId;//第三方支付账号
	private String paySwift;//银行卡类型 
	private String thirdPayConfigId0;//第三方支付别名（或者默认第三方账号）
	private String custmemberStatus;//第三方用户账户状态
	private BigDecimal balance;//第三方账户金额
	private BigDecimal availableAmount;//可用金额  
	private BigDecimal freezeAmount;//冻结金额
	private BigDecimal unTransfersAmount;//未转结余额
	private BigDecimal withDrawbalance;//可取现金额
	private String  bankName;//绑定银行卡银行名称
	private String  bankCode;//绑定银行卡卡号
	private String  bindBankStatus;//绑定银行卡状态
	private List<Detail> details;//资金明细
	private String truename;//真实姓名
	private String cardCode;//身份证号
	private String autoTender;//开通自动投标的状态
	private String requestInfo;//调用第三方接口的报文详情
	private YeePayReponse yeepayreponse;
	private String activeStatus;//	中发展信--作为处理的结果     0失败，1 成功
	private BigDecimal feeUser;//用户实际承担的手续费
	private BigDecimal feePlatform;//平台实际承担的手续费
	private List<CommonResponse> responseList;//当需要批量执行的时候   富友
	private String newMobile;//更换新的手机号
	private String remark;//添加备注
	private String remark1;//备注1
	private String remark2;//备注2

	private String corpName;//企业名称
	private String modifyType;//变更类型   1 -企业名称, 2 -法人代表， 3- 联系人手机     企业信息变更接口
	private String roleType;//用户角色类型：1借款人 3出借人
	
	/**
	 * 交易金额
	 */
	private String amount;
	/**
	 * 实际到账金额
	 */
	private String receivedAmount;
	/**
	 * 手续费
	 */
	private String fee;
	/**
	 * loanInvest，投资授权0000代表成功，其他代表失败或者没有授权
	 */
	private String loanInvest;
	/**
	 * loanRepay，还款授权0000代表成功，其他代表失败或者没有授权
	 */
	private String loanRepay;
	/**
	 * 账户状态
	 * 1代表账户状态正常，2代表账户冻结，3代表账户挂失，4账户销户
	 */
	private String state;
	
	private String city;//绑卡的城市
	
	private String province;//绑卡的省份
	
	private String bankCardType;//银行卡类型
	
	private String bankCardNumber;//银行代码
	
	private String contract_no;//预授权合同号
	
	private String orderDate;//订单日期

	/**
	 * 法人证件号码
	 */
	private String artificialIdentityCode;

	/**
	 * 法人真实姓名
	 */
	private String artificialRealName;

	/**
	 * 是否三证合一
	 * 0否;1是
	 */
	private String threeCertUnit;

	/**
	 * 税务登记号
	 */
	private String taxRegCode;

	/**
	 * 营业执照编号
	 */
	private String licenceCode;

	/**
	 * 营业执照编号
	 */
	private String orgCode;

	/**
	 * 法人手机号码
	 */
	private String mobilePhone;


	public String getArtificialIdentityCode() {
		return artificialIdentityCode;
	}

	public void setArtificialIdentityCode(String artificialIdentityCode) {
		this.artificialIdentityCode = artificialIdentityCode;
	}

	public String getArtificialRealName() {
		return artificialRealName;
	}

	public void setArtificialRealName(String artificialRealName) {
		this.artificialRealName = artificialRealName;
	}

	public String getThreeCertUnit() {
		return threeCertUnit;
	}

	public void setThreeCertUnit(String threeCertUnit) {
		this.threeCertUnit = threeCertUnit;
	}

	public String getTaxRegCode() {
		return taxRegCode;
	}

	public void setTaxRegCode(String taxRegCode) {
		this.taxRegCode = taxRegCode;
	}

	public String getLicenceCode() {
		return licenceCode;
	}

	public void setLicenceCode(String licenceCode) {
		this.licenceCode = licenceCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getWithDrawbalance() {
		return withDrawbalance;
	}
	public void setWithDrawbalance(BigDecimal withDrawbalance) {
		this.withDrawbalance = withDrawbalance;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLoanInvest() {
		return loanInvest;
	}
	public void setLoanInvest(String loanInvest) {
		this.loanInvest = loanInvest;
	}
	public String getLoanRepay() {
		return loanRepay;
	}
	public void setLoanRepay(String loanRepay) {
		this.loanRepay = loanRepay;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	private String dealState;//资金交易状态，2=成功、7=冻结
	
	public String getDealState() {
		return dealState;
	}
	public void setDealState(String dealState) {
		this.dealState = dealState;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNewMobile() {
		return newMobile;
	}
	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}
	public List<CommonResponse> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<CommonResponse> responseList) {
		this.responseList = responseList;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contractNo) {
		contract_no = contractNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	public YeePayReponse getYeepayreponse() {
		return yeepayreponse;
	}
	public void setYeepayreponse(YeePayReponse yeepayreponse) {
		this.yeepayreponse = yeepayreponse;
	}
	public List<CommonRecord> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<CommonRecord> recordList) {
		this.recordList = recordList;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
	public String getResponsecode() {
		return responsecode;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}
	public String getBussinessType() {
		return bussinessType;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setCustMemberId(String custMemberId) {
		this.custMemberId = custMemberId;
	}
	public String getCustMemberId() {
		return custMemberId;
	}
	public void setCustMemberType(String custMemberType) {
		this.custMemberType = custMemberType;
	}
	public String getCustMemberType() {
		return custMemberType;
	}
	public void setThirdPayConfigId(String thirdPayConfigId) {
		this.thirdPayConfigId = thirdPayConfigId;
	}
	public String getThirdPayConfigId() {
		return thirdPayConfigId;
	}
	public void setThirdPayConfig(String thirdPayConfig) {
		this.thirdPayConfig = thirdPayConfig;
	}
	public String getThirdPayConfig() {
		return thirdPayConfig;
	}
	public void setThirdPayConfigId0(String thirdPayConfigId0) {
		this.thirdPayConfigId0 = thirdPayConfigId0;
	}
	public String getThirdPayConfigId0() {
		return thirdPayConfigId0;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBindBankStatus(String bindBankStatus) {
		this.bindBankStatus = bindBankStatus;
	}
	public String getBindBankStatus() {
		return bindBankStatus;
	}
	public void setCustmemberStatus(String custmemberStatus) {
		this.custmemberStatus = custmemberStatus;
	}
	public String getCustmemberStatus() {
		return custmemberStatus;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}
	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	public BigDecimal getUnTransfersAmount() {
		return unTransfersAmount;
	}
	public void setUnTransfersAmount(BigDecimal unTransfersAmount) {
		this.unTransfersAmount = unTransfersAmount;
	}
	public void setCommonRequst(CommonRequst commonRequst) {
		CommonRequst = commonRequst;
	}
	public CommonRequst getCommonRequst() {
		return CommonRequst;
	}
	public String getAutoTender() {
		return autoTender;
	}
	public void setAutoTender(String autoTender) {
		this.autoTender = autoTender;
	}
	public String getRequestInfo() {
		return requestInfo;
	}
	public void setRequestInfo(String requestInfo) {
		this.requestInfo = requestInfo;
	}
	public BigDecimal getFeeUser() {
		return feeUser;
	}
	public void setFeeUser(BigDecimal feeUser) {
		this.feeUser = feeUser;
	}
	public BigDecimal getFeePlatform() {
		return feePlatform;
	}
	public void setFeePlatform(BigDecimal feePlatform) {
		this.feePlatform = feePlatform;
	}
	public String getPaySwift() {
		return paySwift;
	}
	public void setPaySwift(String paySwift) {
		this.paySwift = paySwift;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	public String getBankCardType() {
		return bankCardType;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "CommonResponse [CommonRequst=" + CommonRequst
				+ ", responsecode=" + responsecode + ", responseMsg="
				+ responseMsg + ", returnType=" + returnType + ", returnMsg="
				+ returnMsg + ", requestNo=" + requestNo + ", loanNo=" + loanNo
				+ ", bussinessType=" + bussinessType + ", recordList="
				+ recordList + ", custMemberId=" + custMemberId
				+ ", custMemberType=" + custMemberType + ", thirdPayConfig="
				+ thirdPayConfig + ", thirdPayConfigId=" + thirdPayConfigId
				+ ", paySwift=" + paySwift + ", thirdPayConfigId0="
				+ thirdPayConfigId0 + ", custmemberStatus=" + custmemberStatus
				+ ", balance=" + balance + ", availableAmount="
				+ availableAmount + ", freezeAmount=" + freezeAmount
				+ ", unTransfersAmount=" + unTransfersAmount
				+ ", withDrawbalance=" + withDrawbalance + ", bankName="
				+ bankName + ", bankCode=" + bankCode + ", bindBankStatus="
				+ bindBankStatus + ", details=" + details + ", truename="
				+ truename + ", cardCode=" + cardCode + ", autoTender="
				+ autoTender + ", requestInfo=" + requestInfo
				+ ", yeepayreponse=" + yeepayreponse + ", activeStatus="
				+ activeStatus + ", feeUser=" + feeUser + ", feePlatform="
				+ feePlatform + ", responseList=" + responseList
				+ ", newMobile=" + newMobile + ", remark=" + remark
				+ ", remark1=" + remark1 + ", remark2=" + remark2 + ", amount="
				+ amount + ", receivedAmount=" + receivedAmount + ", fee="
				+ fee + ", loanInvest=" + loanInvest + ", loanRepay="
				+ loanRepay + ", state=" + state + ", city=" + city
				+ ", province=" + province + ", bankCardType=" + bankCardType
				+ ", bankCardNumber=" + bankCardNumber + ", contract_no="
				+ contract_no + ", orderDate=" + orderDate + ", dealState="
				+ dealState + "]";
	}
	
	
	
	
}
