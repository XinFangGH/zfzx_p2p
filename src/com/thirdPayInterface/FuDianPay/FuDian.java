package com.thirdPayInterface.FuDianPay;

import java.util.List;

/**
 * 富滇银行接口POJO类
 * @author tzw
 *
 */
public class FuDian{

	/**
	 * 字符编码
	 */
	public static final String CHARSETUTF8 = "UTF-8";
	
	
	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";
	
	/**
	 * 证件类型，只有这一种身份证
	 */
	public static final String IDENTITYTYPE = "1";
	
	/**
	 * 1原有手机号可用，进行自助修改
	 * 修改手机号码方式
	 */
	public static final String UPDATEPHONETYPE1 = "1";
	/**
	 * 2原有手机号不可用，进行人工修改
	 * 修改手机号码方式
	 */
	public static final String UPDATEPHONETYPE2 = "2";
	
	/**
	 * 0取现不需要审核
	 */
	public static final String VERIFYTYPE0 = "0";
	/**
	 * 1取现需要审核
	 */
	public static final String VERIFYTYPE1 = "1";
	
	/**
	 * 业务类型1 授权
	 */
	public static final String BUSINESSTYPE1 = "1";
	
	/**
	 * 业务类型2 取消授权
	 */
	public static final String BUSINESSTYPE2 = "2";
	
	/**
	 * 转让手续费方式    转让人出
	 */
	public static final String CREDITFEETYPE1 = "1";
	
	/**
	 * 转让手续费方式   承接人出
	 */
	public static final String CREDITFEETYPE2 = "2";
	
	/**
	 * 充值
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE01 = "01";
	
	/**
	 * 提现
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE02 = "02";
	
	/**
	 * 投标
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE03 = "03";
	
	/**
	 * 借款人还款
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE04 = "04";
	
	/**
	 * 投资人回款
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE05 = "05";
	
	/**
	 * 债权认购
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE06 = "06";
	
	/**
	 * 满标放款
	 * 订单交易查询接口交易类型   
	 */
	public static final String QUERYTYPE07 = "07";


	/**
	 * 角色类型：1借款人
	 */
	public static final String ROLE_TYPE1 = "1";

	/**
	 * 角色类型：3出借人
	 */
	public static final String ROLE_TYPE3 = "3";

	/**
	 * 01富滇个人注册url
	 */
	public static final String FUDIANGOLDREG="/user/register";

	/**
	 * 02富滇企业注册url
	 */
	public static final String FUDIANCORPREG="/corp/register";
	/**
	 * 03富滇绑定银行卡url
	 */
	public static final String FUDIANBINDCARD="/user/card/bind";
	/**
	 * 04富滇更换银行卡url
	 * 目前只支持个人用户
	 */
	public static final String FUDIANCHANGECARD="/user/card/applyChange";

	/**
	 * 05富滇修改手机号码
	 * 目前只支持个人用户
	 */
	public static final String FUDIANCHANGEPHONE="/phone/update";
	
	
	/**
	 * 06富滇充值接口--pc
	 */
	public static final String FUDIANRECHARGE="/account/recharge";
	
	/**
	 * 07富滇充值接口--手机端
	 */
	public static final String FUDIANRECHARGEPHONE="/app/realPayRecharge";
	

	/**
	 * 08富滇取现接口
	 */
	public static final String FUDIANWITHDRAW="/account/withdraw";
	
	
	/**
	 * 09富滇查询个人信息
	 */
	public static final String FUDIANQUERYUSER="/query/user";
	
	
	/**
	 * 10富滇手动投标
	 */
	public static final String FUDIANINVEST="/loan/invest";
	
	
	/**
	 * 11富滇授权接口
	 */
	public static final String FUDIANAUTHORIZATION="/business/authorization";
	
	/**
	 * 12富滇手动还款
	 */
	public static final String FUDIANREPAY="/loan/repay";
	
	/**
	 * 13富滇投资人回款
	 */
	public static final String FUDIANCALLBANK="/loan/callback";
	
	
	/**
	 * 14富滇债权认购
	 */
	public static final String FUDIANCREDITINVEST="/loanCredit/invest";
	
	
	
	/**
	 * 15解除绑卡
	 */
	public static final String CANCELBIND="/user/card/cancelBind";

    /**
     * 15.1 企业解除绑卡
     */
    public static final String CANCELCARDBIND="/user/card/cancelCardBind";
	/**
	 * 16标的账户查询接口
	 */
	public static final String QUERYLOAN="/query/loan";

	/**
	 * 17标的流水查询接口
	 */
	public static final String QUERYLOANBALANCE="/query/logLoanAccount";
	
	/**
	 * 18账户流水查询接口
	 */
	public static final String QUERYLOGACCOUNT="/query/logAccount";
	
	/**
	 * 19账户流水查询接口
	 */
	public static final String QUERYDEALINFO="/query/trade";

	/**
	 * 20数据迁移接口
	 */
	public static final String MIGRATION="/migration/dataMigration";

	/**
	 * 21企业信息变更接口
	 */
	public static final String MODIFY="/corp/modify";

	/**
	 * 22 富滇企业注册新url
	 * 富滇于20180625更新企业注册接口，修改接口为跳转三方页面
	 */
	public static final String FUDIANCORPREGNEW="/corp/registerNew";
	
	/**                                       基础参数                                                                                       */
	 
	
	
	/**
	 * 商户号
	 */
	private String merchantNo;

	/**
	 * 用户名
	 * 用户在三方系统的唯一账户编号，由三方生成
	 * 回调
	 */
	private String userName;

	/**
	 * 账户号
	 * 用户在三方系统的唯一账户编号，由三方生成
	 * 回调
	 */
	private String accountNo;

	/**
	 * 订单流水号
	 */
	private String orderNo;

	/**
	 * 订单日期
	 */
	private String orderDate;
	/**
	 * 页面回调
	 */
	private String returnUrl;
	
	/**
	 * 服务器回调
	 */
	private String notifyUrl;
	/**
	 * 参数扩展域
	 * 该字段在交易完成后由本平台原样返回。注意：如果该字段中包含了中文字符请对该字段的数据进行Base64加密后再使用
	 * 非必填
	 * 变长256
	 */
	private String extMark;
	
	/**
	 * 请求参数
	 */
	private String reqData;
	
	/**
	 * 支付类型
	 * 非三方参数，获取请求地址的时候用到
	 */
	private String thirdType;
	
	
	
	
	
	/**                         用户开户                                                                      */
	
	
	/**
	 * 真实姓名
	 * 必填
	 * 用户真实姓名（必须中文）
	 * 变长32
	 */
	private String realName;
	/**
	 * 证件类型
	 * 必填
	 * 1:身份证
	 */
	private String identityType;
	/**
	 * 证件号
	 * 必填
	 * 证件类型为身份证时，仅支持18位长度，同一商户下必须唯一
	 * 变长256
	 */
	private String identityCode;
	/**
	 * 手机号
	 * 必填
	 * 手机号，同一商户下必须唯一
	 * 定长11
	 */
	private String mobilePhone;

	/**
	 * 角色类型
	 * 必填
	 * 角色类型：1借款人 3出借人
	 * 定长1
	 */
	private Integer roleType;

	
	/**    企业开户用到参数         */
	
	/**
	 * 法人代表真实姓名
	 * 必填
	 * 变长60
	 */
	private String artificialRealName;
	
	
	/**
	 * 法人代表证件号
	 * 必填
	 * 定长18
	 */
	private String artificialIdentityCode;
	
	/**
	 * 企业名称
	 * 必填
	 * 变长256
	 */
	private String corpName;
	
	
	/**
	 * 公司类型
	 * 必填
	 * 定长1
	 * 1、	企业
		2、担保公司
	 	3 出借企业
	 */
	private String corpType;
	
	/**
	 * 统一社会信用代码
	 * 必填
	 * 变长256
	 */
	private String creditCode;

	/**
	 * 营业执照编号
	 * 必填
	 * 变长30
	 */
	private String licenceCode;
	
	/**
	 * 组织机构代码
	 * 必填
	 * 定长30
	 */
	private String orgCode;
	
	/**
	 * 税务登记号
	 * 必填
	 * 定长30
	 */
	private String taxRegCode;

	/**
	 * 是否三证合一   0否  1是
	 * 必填
	 * 定长30
	 * 非三证合一：组织机构代码，营业执照编号，税务登记号必填
	 * 三证合一：统一社会信用代码必填
	 */
	private String threeCertUnit;
	
	
	
	/**               更换手机号                    */
	
	
	/**
	 * 新手机号
	 * 必填
	 * 定长11
	 * 服务器通知业务参数，请根据此做业务处理
	 */
	private String newPhone;
	
	
	/**
	 * 修改方式
	 * 必填
	 * 1原有手机号可用，进行自助修改
	 * 2原有手机号不可用，进行人工修改
	 * 定长1
	 */
	private String type;
	
	
	
	/**            充值参数                        */
	
	/**
	 * 充值金额
	 * 必填
	 * 充值金额，以元为单位，保留小数点后2位
	 * 变长20
	 */
	private String amount;
	
	
	/**
	 * 手续费
	 * 必填
	 * 充值手续费，默认向用户收取以元为单位，保留小数点后2位
	 * 变长20
	 */
	private String fee;

	/**
	 * 支付方式
	 * 必填
	 * 支付方式 （个人、企业都可）
	 *	1:快捷充值
	 *	3:网关充值（暂不可用）
	 *	5:银行直连（绑定富滇银行卡，只能走网关充值及银行直连）
	 * 定长1
	 */
	private String payType;
	
	/**  取现参数    **/
	
	
	/**
	 * 审核类型
	 * 必填
	 * 提现是否需要审核，0-不需要，1-需要（暂只支持不需要审核类型）
	 * 定长1
	 */
	private String verifyType;
	
	
	/**      投标参数             */
	
	/**
	 * 奖励金额
	 * 必填
	 * 单位：元 用于平台的红包奖励，由p2p平台支出,无则传0
	 * 变长20
	 */
	private String award;
	
	/**
	 * 标的号
	 * 必填
	 * 标的号，由存管系统生成并确保唯一性.
	 * 变长32
	 */
	private String loanTxNo;
	
	
	/**     授权需要参数      */
	
	
	/**
	 * 业务类型
	 * 必填
	 * 1授权 2取消授权   
	 * 当传值1的时候，用户会跳转到授权页面选择授权的业务进行授权。
	 * 当传值2的时候，会取消全部已经授权的业务
	 * 定长1
	 */
	private String businessType;
	
	
	
	/**        还款参数                  */
	
	
	/**
	 * 当期还款本金
	 * 必填
	 * 还款没有本金时请传0，单位：元，其他请传两位小数金额
	 * 变长20
	 */
	private String capital;

	/**
	 * 当期应还利息综合
	 * 必填
	 * 还款没有利息时，请传0，单位元注意本金和利息不可都为0
	 * 变长20
	 */
	private String interest;
	
	/**
	 * 借款管理费
	 * 必填
	 * 标的借款管理费，单位:元,保留2位有效数字
	 * 变长20
	 */
	private String loanFee;
	
	
	/**       投资人回款               */
	
	/**
	 * 投资人回款列表
	 * 必填
	 * json格式的投资人回款信息
	 * 变长256??
	 */
	private List<InvestBean> investList;
	
	
	
	
	/**       债权认购接口参数                               */
	
	/**
	 * 认购本金
	 * 必填
	 * 承接人购买的转让本金
	 * 注意，累计购买的转让本金不能大于发布的债权本金
	 * 变长20
	 */
	private String creditAmount;
	
	/**
	 * 转让手续费
	 * 必填
	 * 债权转让过程中产生的手续费，支付给平台商户
	 * 变长20
	 */
	private String creditFee;
	
	/**
	 * 转让手续费方式
	 * 必填
	 * 手续费到底由谁支出?
	 * 1:转让人出2：承接人出
	 * 变长20
	 */
	private String creditFeeType;
	
	/**
	 * 债权挂牌ID
	 * 必填
	 * 平台发布债权标的唯一标识符
	 * 变长50
	 */
	private String creditNo;
	
	/**
	 * 债权挂牌金额
	 * 必填
	 * 平台发布债权转让的金额
	 * 变长20
	 */
	private String creditNoAmount;
	
	/**
	 * 原投资记录的订单日期
	 * 必填
	 * 原投资的订单日期yyyyMMdd
	 * 定长8
	 */
	private String investOrderDate;
	
	/**
	 * 原投资记录的订单号
	 * 必填
	 * 原投资的订单号，必须保证唯一性
	 * 定长18
	 */
	private String investOrderNo;
	
	/**
	 * 最原始投资记录订单日期
	 * 必填
	 * 最原始投资的订单日期yyyyMMdd
	 * 定长8
	 */
	private String oriOrderDate;
	
	/**
	 * 最原始投资记录订单号
	 * 必填
	 * 最原始投资的订单号，必须保证唯一性
	 * 定长18
	 */
	private String oriOrderNo;
	
	/**
	 * 已还款金额
	 * 必填
	 * 原投资已经还款的本金,默认：0.00
	 * 变长20
	 */
	private String repayedAmount;
	
	
	/**       标的流水查询接口                               */
	/**
	 * 标的账户号
	 * 必填
	 * 商户标的账户号，由存管系统生成并确保唯一性
	 */
	private String loanAccNo;
	
	
	/**       账户流水查询接口                              */
	/**
	 * 查询订单日期
	 * 必填
	 * 查询日期
	 */                       
	private String queryOrderDate;
	
	/**       订单交易查询接口                              */
	
	/**
	 * 查询订单流水号
	 * 必填
	 * 查询日期
	 */                       
	private String queryOrderNo;
	
	/**
	 * 交易类型
	 * 必填
	 * 查询日期
	 * 定长2
	 * 01充值   02提现  03投标  04借款人还款  05投资人回款   06债权认购   07满标放款
	 */                       
	private String queryType;
	
	
	/**       数据迁移接口                              */
	
	/**
	 * 迁移类型
	 * 必填
	 * 1-用户，2-资金，3-投资，4-标的，5还款
	 */                       
	private String migrationType;
	
	/**
	 * 总条数
	 * 必填
	 * 文件内参数总条数
	 */                       
	private String totalCount;
	
	
	/**
	 * 文件名
	 * 必填
	 * 数据迁移申请文件名命名规范
	 * 	商户号_文件生成日期(yyyyMMdd)_迁移类型_商户订单号.txt
	 * 	举例：M0182112093_20170501_迁移类型_19700101838006384.txt
	 */                       
	private String fileName;


	/**       数据迁移接口                              */
	/**
	 * 变更类型
	 * 必填
	 * 变更类型：1 -企业名称, 2 -法人代表， 3- 联系人手机
	 */
	private String modifyType;


	/**
	 * 原企业名称
	 * 必填
	 * 要修改的企业的企业名称（当前在存管保存的企业名称）
	 */
	private String oldCorpName;


	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	public String getOldCorpName() {
		return oldCorpName;
	}

	public void setOldCorpName(String oldCorpName) {
		this.oldCorpName = oldCorpName;
	}

	public String getMigrationType() {
		return migrationType;
	}
	public void setMigrationType(String migrationType) {
		this.migrationType = migrationType;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getQueryOrderNo() {
		return queryOrderNo;
	}
	public void setQueryOrderNo(String queryOrderNo) {
		this.queryOrderNo = queryOrderNo;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getQueryOrderDate() {
		return queryOrderDate;
	}
	public void setQueryOrderDate(String queryOrderDate) {
		this.queryOrderDate = queryOrderDate;
	}
	public String getLoanAccNo() {
		return loanAccNo;
	}
	public void setLoanAccNo(String loanAccNo) {
		this.loanAccNo = loanAccNo;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getExtMark() {
		return extMark;
	}
	public void setExtMark(String extMark) {
		this.extMark = extMark;
	}
	public String getReqData() {
		return reqData;
	}
	public void setReqData(String reqData) {
		this.reqData = reqData;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public static String getCharsetutf8() {
		return CHARSETUTF8;
	}
	public String getThirdType() {
		return thirdType;
	}
	public void setThirdType(String payType) {
		this.thirdType = payType;
	}
	public String getArtificialRealName() {
		return artificialRealName;
	}
	public void setArtificialRealName(String artificialRealName) {
		this.artificialRealName = artificialRealName;
	}
	public String getArtificialIdentityCode() {
		return artificialIdentityCode;
	}
	public void setArtificialIdentityCode(String artificialIdentityCode) {
		this.artificialIdentityCode = artificialIdentityCode;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
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
	public String getTaxRegCode() {
		return taxRegCode;
	}
	public void setTaxRegCode(String taxRegCode) {
		this.taxRegCode = taxRegCode;
	}
	public String getThreeCertUnit() {
		return threeCertUnit;
	}
	public void setThreeCertUnit(String threeCertUnit) {
		this.threeCertUnit = threeCertUnit;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public String getLoanTxNo() {
		return loanTxNo;
	}
	public void setLoanTxNo(String loanTxNo) {
		this.loanTxNo = loanTxNo;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getLoanFee() {
		return loanFee;
	}
	public void setLoanFee(String loanFee) {
		this.loanFee = loanFee;
	}
	public List<InvestBean> getInvestList() {
		return investList;
	}
	public void setInvestList(List<InvestBean> investList) {
		this.investList = investList;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCreditFee() {
		return creditFee;
	}
	public void setCreditFee(String creditFee) {
		this.creditFee = creditFee;
	}
	public String getCreditFeeType() {
		return creditFeeType;
	}
	public void setCreditFeeType(String creditFeeType) {
		this.creditFeeType = creditFeeType;
	}
	public String getCreditNo() {
		return creditNo;
	}
	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	public String getCreditNoAmount() {
		return creditNoAmount;
	}
	public void setCreditNoAmount(String creditNoAmount) {
		this.creditNoAmount = creditNoAmount;
	}
	public String getInvestOrderDate() {
		return investOrderDate;
	}
	public void setInvestOrderDate(String investOrderDate) {
		this.investOrderDate = investOrderDate;
	}
	public String getInvestOrderNo() {
		return investOrderNo;
	}
	public void setInvestOrderNo(String investOrderNo) {
		this.investOrderNo = investOrderNo;
	}
	public String getOriOrderDate() {
		return oriOrderDate;
	}
	public void setOriOrderDate(String oriOrderDate) {
		this.oriOrderDate = oriOrderDate;
	}
	public String getOriOrderNo() {
		return oriOrderNo;
	}
	public void setOriOrderNo(String oriOrderNo) {
		this.oriOrderNo = oriOrderNo;
	}
	public String getRepayedAmount() {
		return repayedAmount;
	}
	public void setRepayedAmount(String repayedAmount) {
		this.repayedAmount = repayedAmount;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
}