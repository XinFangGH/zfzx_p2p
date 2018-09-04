package com.thirdPayInterface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thirdPayInterface.FuDianPay.InvestBean;
import com.thirdPayInterface.Huifu.BorrowerDetail;

/**
 * 第三方支付公共请求参数列表
 * @author Administrator
 *
 */
public class CommonRequst {
	
	
	private String bussinessType;//业务类型 
	private String bizType;//传给第三方的业务类型
	private String bankType;//银行类型
	private String requsetNo;//请求第三方接口流水号
	private String thirdPayConfig;//第三方支付类型
	private String thirdPayConfigId;//第三方支付账号 accountNo
	private String thirdPayConfigId0;//第三方支付别名（或者默认第三方账号） userName
	private String custMemberId;//客户标Id
	private String loanAccType;//企业商户号必填
	private Long slEarlyRepaymentId;//散标提前还款id
	private String city_id;//开户地区码
	private String capAcntNo;//账号
	private String login_id;//用户登录id
	private String user_id_from;//用户在系统中的标志
	private String maxTenderRate;//最大投资费率(汇付)
	private String reqExt;//请求扩展域
	private String pageType;//页面类型
	private String proId;//标的号
	private String sellCustId;//转让人客户号
	private String creditAmt;//转让金额
	private String creditDealAmt;//承接金额
	private String busiCode;//营业执照号
	private String isDefault;//是否默认
	private String isUnFreeze;//是否解冻
	private String unFreezeOrdId;//解冻订单号
	private String freezeTrxId;//冻结标识
	private String tenderPlanType;//投标计划类型
    private String usrCustId;//用户id
    private String bidProNumber;//项目编号
    
	public String getBidProNumber() {
		return bidProNumber;
	}
	public void setBidProNumber(String bidProNumber) {
		this.bidProNumber = bidProNumber;
	}
	public String getUsrCustId() {
		return usrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		this.usrCustId = usrCustId;
	}
	public String getTenderPlanType() {
		return tenderPlanType;
	}
	public void setTenderPlanType(String tenderPlanType) {
		this.tenderPlanType = tenderPlanType;
	}

	private String retInterest; 
	private String fundType;
	private String guarType;//担保类型
	public String getGuarType() {
		return guarType;
	}
	public void setGuarType(String guarType) {
		this.guarType = guarType;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public String getRetInterest() {
		return retInterest;
	}
	public void setRetInterest(String retInterest) {
		this.retInterest = retInterest;
	}

	/**
	 * 企业客户类型，ENTERPRISE：企业用户，GUARANTEE_CORP：担保公司
	 */
	private String memberClassType; 
    /**
     * 保障方式['本金保障',1],['本息保障',2],['全部保障',3]
     */
    private String guaranteeWay;
    
	public String getGuaranteeWay() {
		return guaranteeWay;
	}
	public void setGuaranteeWay(String guaranteeWay) {
		this.guaranteeWay = guaranteeWay;
	}
	public String getMemberClassType() {
		return memberClassType;
	}
	public void setMemberClassType(String memberClassType) {
		this.memberClassType = memberClassType;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsUnFreeze() {
		return isUnFreeze;
	}
	public void setIsUnFreeze(String isUnFreeze) {
		this.isUnFreeze = isUnFreeze;
	}
	public String getUnFreezeOrdId() {
		return unFreezeOrdId;
	}
	public void setUnFreezeOrdId(String unFreezeOrdId) {
		this.unFreezeOrdId = unFreezeOrdId;
	}
	public String getFreezeTrxId() {
		return freezeTrxId;
	}
	public void setFreezeTrxId(String freezeTrxId) {
		this.freezeTrxId = freezeTrxId;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getSellCustId() {
		return sellCustId;
	}
	public void setSellCustId(String sellCustId) {
		this.sellCustId = sellCustId;
	}

	public String getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}
	public String getCreditDealAmt() {
		return creditDealAmt;
	}
	public void setCreditDealAmt(String creditDealAmt) {
		this.creditDealAmt = creditDealAmt;
	}
	public String getBidDetails() {
		return BidDetails;
	}
	public void setBidDetails(String bidDetails) {
		BidDetails = bidDetails;
	}
	public String getBuyCustId() {
		return BuyCustId;
	}
	public void setBuyCustId(String buyCustId) {
		BuyCustId = buyCustId;
	}
	public String getLcId() {
		return LcId;
	}
	public void setLcId(String lcId) {
		LcId = lcId;
	}
	public String getTotalLcAmt() {
		return TotalLcAmt;
	}
	public void setTotalLcAmt(String totalLcAmt) {
		TotalLcAmt = totalLcAmt;
	}
	private String BidDetails;//债权转让明细
	private String DivDetails;//分账账户串
	private String BuyCustId;//承接人客户号
	private String LcId;//挂牌债权ID
	private String TotalLcAmt;//挂牌债权总金额
	private String merPriv;//商户私有域
	
    public String getMerPriv() {
		return merPriv;
	}
	public void setMerPriv(String merPriv) {
		this.merPriv = merPriv;
	}
	public String getOutCustId() {
		return outCustId;
	}
	public void setOutCustId(String outCustId) {
		this.outCustId = outCustId;
	}
	public String getSubOrdId() {
		return subOrdId;
	}
	public void setSubOrdId(String subOrdId) {
		this.subOrdId = subOrdId;
	}
	public String getSubOrdDate() {
		return subOrdDate;
	}
	public void setSubOrdDate(String subOrdDate) {
		this.subOrdDate = subOrdDate;
	}
	public String getPrincipalAmt() {
		return principalAmt;
	}
	public void setPrincipalAmt(String principalAmt) {
		this.principalAmt = principalAmt;
	}
	public String getInterestAmt() {
		return interestAmt;
	}
	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}
	public String getInCustId() {
		return inCustId;
	}
	public void setInCustId(String inCustId) {
		this.inCustId = inCustId;
	}
	public String getOutAcctId() {
		return outAcctId;
	}
	public void setOutAcctId(String outAcctId) {
		this.outAcctId = outAcctId;
	}
	public String getInAcctId() {
		return inAcctId;
	}
	public void setInAcctId(String inAcctId) {
		this.inAcctId = inAcctId;
	}
	public String getDivDetails() {
		return divDetails;
	}
	public void setDivDetails(String divDetails) {
		this.divDetails = divDetails;
	}
	public String getFeeObjFlag() {
		return feeObjFlag;
	}
	public void setFeeObjFlag(String feeObjFlag) {
		this.feeObjFlag = feeObjFlag;
	}
	public String getDzObject() {
		return dzObject;
	}
	public void setDzObject(String dzObject) {
		this.dzObject = dzObject;
	}
	/**
     * 出账账户号
     * @return
     */
    private String outCustId;
    /**
     * 订单号
     * @return
     */
    private String subOrdId;
    /**
     * 订单日期
     * @return
     */
    private String subOrdDate;
    /**
     * 还款本金
     * @return
     */
    private String principalAmt;
    /**
     * 还款利息
     * @return
     */
    private String interestAmt;
    /**
     * 管理费
     * @return
     */
    private String loanFee;
    
    /**
     * 入账客户号
     * @return
     */
    private String inCustId;
    /**
     * 出账子账户
     * @return
     */
    private String outAcctId;
    /**
     * 入账子账户
     * @return
     */
    private String inAcctId;
    /**
     * 分账账户串
     * @return
     */
    private String divDetails;
    /**
     * 手续费收取对象标志
     * @return
     */
    private String feeObjFlag;
    /**
     * 垫资代偿对象
     * @return
     */
    private String dzObject;

    
	public String getLoanFee() {
		return loanFee;
	}
	public void setLoanFee(String loanFee) {
		this.loanFee = loanFee;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getReqExt() {
		return reqExt;
	}
	public void setReqExt(String reqExt) {
		this.reqExt = reqExt;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getIsFreeze() {
		return IsFreeze;
	}
	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}
	public String getFreezeOrdId() {
		return freezeOrdId;
	}
	public void setFreezeOrdId(String freezeOrdId) {
		this.freezeOrdId = freezeOrdId;
	}
	private String orderDate;//请求日期
	private String IsFreeze;//是否冻结
	private String freezeOrdId;//冻结的流水号
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getMaxTenderRate() {
		return maxTenderRate;
	}
	public void setMaxTenderRate(String maxTenderRate) {
		this.maxTenderRate = maxTenderRate;
	}
	public String getUser_id_from() {
		return user_id_from;
	}
	public void setUser_id_from(String userIdFrom) {
		user_id_from = userIdFrom;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String loginId) {
		login_id = loginId;
	}
	private String parent_bank_id;//开户行行别
	/**
	 * 表示是否支持银行卡快捷支付
	 * 1表示支持快捷支付
	 * 其余均不表示支持快捷支付
	 */
	private String bankFastPay;
	
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String cityId) {
		city_id = cityId;
	}
	public String getCapAcntNo() {
		return capAcntNo;
	}
	public void setCapAcntNo(String capAcntNo) {
		this.capAcntNo = capAcntNo;
	}
	public String getParent_bank_id() {
		return parent_bank_id;
	}
	public void setParent_bank_id(String parentBankId) {
		parent_bank_id = parentBankId;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getCustMemberId() {
		return custMemberId;
	}
	private String custMemberType;//客户类型   //
	
	private String trueName;//真实姓名
	private String cardType;//证件类型
	private String cardNumber;//证件号码
	
	private String telephone;//手机号码
	private String email;//邮箱
	private String isMobile;//判断是否是手机端操作
	
	
	
	//新添加
	private Integer requestNum;//请求接口次数
	private Integer returnNum;//回调次数
	private Date intentDate;//计划还款日
	private String otherThirdpayFlagId;//被交易人的第三方账号
	private Long otherUserId;//被交易人的平台账户Id
	private String otherLoginName;//被交易人的平台登录名
	private String UniqueId;//唯一ID  做标识的
	private Date queryStartDate;//开始查询日期
	private Date queryEndDate;//结束查询日期
	private String dcFlag;//借贷标记  --汇付天下 D:借记类型  C:贷记类型
	private String borrowerDetails;
	public String getBorrowerDetails() {
		return borrowerDetails;
	}
	public void setBorrowerDetails(String borrowerDetails) {
		this.borrowerDetails = borrowerDetails;
	}
	public String getDcFlag() {
		return dcFlag;
	}
	public void setDcFlag(String dcFlag) {
		this.dcFlag = dcFlag;
	}
	/**
	 * 标的类型 规则。key=hry_开头。value=key值去掉下划线全大写，value不要超过8个字母
	 */
	/**
	 * 标的状态
	 */
	public static final String HRY_BID ="HRYBID";
	/**
	 * 红包状态
	 */
	public static final String HRY_RED ="HRYRED";
	/**
	 * 理财计划奖励状态
	 */
	public static final String HRY_PLANRED ="HRYPLANRED";
	/**
	 * 债权交易标的状态
	 */
	public static final String HRY_SALE ="HRYSALE";
	/**
	 * 退回服务费状态
	 */
	public static final String HRY_QUIT ="HRYQUIT";
	/**
	 * 互融宝标的状态
	 */
	public static final String HRY_HRB="HRYHRB";
	/**
	 * 理财计划派息状态
	 */
	public static final String HRY_DIVIDEND ="HRYDIVIDEND";
	/**
	 * 理财计划购买状态
	 */
	public static final String HRY_PLANBUY ="HRYPLANBUY";
	/**
	 * 散标奖励状态
	 */
	public static final String HRY_BIDRED ="HRYBIDRED";
	/**
	 * 理财计划起息
	 */
	public static final String HRY_PLANSTART ="HRYPLANSTART";
	/**
	 * 还款标类型
	 */
	public static final String HRY_PAY="HRYPAY";
	
	
	private Integer registerType;//注册类型,1表示全自动，2表示半自动
	private String loginname;//用户在网贷平台的账号
	private String remark1;//自定义备注1
	private String remark2;//自定义备注2
	private String remark3;//自定义备注3
	private String transferName;//用途
	private Integer platformType;//平台乾多多账户类型，1.托管账户，2.自有账户
	private Integer rechargeType;//充值类型空.网银充值，1.代扣充值(暂不可用)，	2.快捷支付，	3.汇款充值，	4.企业网银
	private String authorizeTypeOpen;//开启授权类型,1.投标,	2.还款,	3.二次分配审核,	将所有数字用英文逗号(,)连成一个字符串
	private String authorizeTypeClose;//关闭授权类型,1.投标,	2.还款,	3.二次分配审核,	将所有数字用英文逗号(,)连成一个字符串
	private Integer auditType;//审核类型，1.通过，2.退回，3.二次分配同意，	4.二次分配不同意，5.提现通过，	6.提现退回
	private String identityJsonList;//姓名匹配列表
	private String transferAction;//转账类型，1.投标，2.还款，3.其他，10发红包(10我们自己定义的)
	private Integer accountType;//账户类型,空表示个人账户	1表示企业账户
	/**
	 * 手续费类型，快捷支付、汇款充值、企业网银必填
	 * 1.充值成功时从充值人账户全额扣除
	 * 2.充值成功时从平台自有账户全额扣除
	 * 3.充值成功时从充值人账户扣除与提现手续费的差值
	 * 4.充值成功时从平台自有账户扣除与提现手续费的差值
	 * 快捷支付、汇款充值、企业网银必填，其他类型留空
	 * 快捷支付可以填1、2、3、4
	 * 汇款充值可以填1、2
	 * 企业网银可以填1、2
	 */
	private Integer feeType;

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
	 * 银行卡开户行支行名称
	 */
	private String bankBranchName ;
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
	 * 银行卡号（原有）
	 */
	private String bankCardNumber;
	/**
	 * 银行卡号（新的）
	 */
	private String newBankCardNumber;
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
	 * 投标金额
	 */
	private BigDecimal bidMoney;
	/**
	 * 借款人第三方账号
	 */
	private String loaner_thirdPayflagId;
	
	/**
	 * 收款方第三方账号
	 */
	private String invest_thirdPayConfigId;
	
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
	//社会信用代码(三证合一)
	private String threeCardCode;
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
	//乾多多流水号列表
	private String LoanNoList;
	
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
	/**
	 * 起始时间
	 */
	private Date startDay;
	/**
	 * 截止时间
	 */
	private Date endDay;
	/**
	 * 预授权合同号
	 */
	private String contractNo;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 交易状态
	 */
	private String businessStatus;
	/**
	 * 页码
	 */
	private String pageNo;
	/**
	 * 每页条数
	 * 
	 */
	private String pageSize;
	/**
	 * 查询交易流水号，投资时的原流水号
	 * 
	 */
	private String queryRequsetNo;
	/**
	 * 查询类型    			
	 * null: 转账
	 * 1：充值
	 * 2：提现
	 * 操作类型	1.用户认证 2.提现银行卡绑定3.代扣授权
	 */
	private Integer action;
	
	/**
	 * 代扣开始日期 yyyyMMdd
	 */
	private String withholdBeginDate;
	/** 
	 * 代扣结束日期 yyyyMMdd
	 */
	private String withholdEndDate;
	/**
	 * 单笔代扣限额
	 */
	private Double singleWithholdLimit;
	/**
	 * 代扣总限额
	 */
	private Double totalWithholdLimit;
	
	/**
	 * 转账记录
	 */
	private List<CommonDetail> detailList;
	/**
	 * 是网关还是快捷支付   参数值为NET，网关支付默认为网银支付；参数值为空，默认勾选为快捷支付
	 */
	private String payProduct;
	
	/**
	 * 转让手续费方式
	 * 1:转让人出2：承接人出
	 */
	private String creditFeeType;
	/**
	 * 最原始投资记录订单日期
	 */
	private String oriOrderDate;
	/**
	 * 最原始投资记录订单号
	 */
	private String oriOrderNo;
	
	/**
	 * 
	 */
	private String pageNumber;
	
	
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
	public String getCreditFeeType() {
		return creditFeeType;
	}
	public void setCreditFeeType(String creditFeeType) {
		this.creditFeeType = creditFeeType;
	}
	public String getThreeCardCode() {
		return threeCardCode;
	}
	public void setThreeCardCode(String threeCardCode) {
		this.threeCardCode = threeCardCode;
	}
	public List<BorrowerDetail> getBorrowerList() {
		return borrowerList;
	}
	public void setBorrowerList(List<BorrowerDetail> borrowerList) {
		this.borrowerList = borrowerList;
	}
	/**
	 *借款人list 
	 */
	private List<BorrowerDetail> borrowerList;
	
	/**
	 * 投资人还款列表
	 */
	private List<InvestBean> investList;
	
	
	public List<InvestBean> getInvestList() {
		return investList;
	}
	public void setInvestList(List<InvestBean> investList) {
		this.investList = investList;
	}

	private String ordId;
	private String trxId;
	
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getInvest_thirdPayConfigId() {
		return invest_thirdPayConfigId;
	}
	public void setInvest_thirdPayConfigId(String investThirdPayConfigId) {
		invest_thirdPayConfigId = investThirdPayConfigId;
	}
	public String getLoanAccType() {
		return loanAccType;
	}
	public void setLoanAccType(String loanAccType) {
		this.loanAccType = loanAccType;
	}
	public String getPayProduct() {
		return payProduct;
	}
	public void setPayProduct(String payProduct) {
		this.payProduct = payProduct;
	}
	public String getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	public String getWithholdBeginDate() {
		return withholdBeginDate;
	}
	public void setWithholdBeginDate(String withholdBeginDate) {
		this.withholdBeginDate = withholdBeginDate;
	}
	public String getWithholdEndDate() {
		return withholdEndDate;
	}
	public void setWithholdEndDate(String withholdEndDate) {
		this.withholdEndDate = withholdEndDate;
	}
	public Double getSingleWithholdLimit() {
		return singleWithholdLimit;
	}
	public void setSingleWithholdLimit(Double singleWithholdLimit) {
		this.singleWithholdLimit = singleWithholdLimit;
	}
	public Double getTotalWithholdLimit() {
		return totalWithholdLimit;
	}
	public void setTotalWithholdLimit(Double totalWithholdLimit) {
		this.totalWithholdLimit = totalWithholdLimit;
	}
	public String getQueryRequsetNo() {
		return queryRequsetNo;
	}
	public void setQueryRequsetNo(String queryRequsetNo) {
		this.queryRequsetNo = queryRequsetNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getTransferAction() {
		return transferAction;
	}
	public void setTransferAction(String transferAction) {
		this.transferAction = transferAction;
	}
	public String getIdentityJsonList() {
		return identityJsonList;
	}
	public void setIdentityJsonList(String identityJsonList) {
		this.identityJsonList = identityJsonList;
	}
	public Integer getAuditType() {
		return auditType;
	}
	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}
	public String getLoanNoList() {
		return LoanNoList;
	}
	public void setLoanNoList(String loanNoList) {
		LoanNoList = loanNoList;
	}
	public String getAuthorizeTypeOpen() {
		return authorizeTypeOpen;
	}
	public void setAuthorizeTypeOpen(String authorizeTypeOpen) {
		this.authorizeTypeOpen = authorizeTypeOpen;
	}
	public String getAuthorizeTypeClose() {
		return authorizeTypeClose;
	}
	public void setAuthorizeTypeClose(String authorizeTypeClose) {
		this.authorizeTypeClose = authorizeTypeClose;
	}
	public Integer getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public Integer getPlatformType() {
		return platformType;
	}
	public void setPlatformType(Integer platformType) {
		this.platformType = platformType;
	}
	public String getTransferName() {
		return transferName;
	}
	public void setTransferName(String transferName) {
		this.transferName = transferName;
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
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
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
	public String getCustMemberType() {
		return custMemberType;
	}
	public void setCustMemberId(String custMemberId) {
		this.custMemberId = custMemberId;
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
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public BigDecimal getBidMoney() {
		return bidMoney;
	}
	public void setBidMoney(BigDecimal bidMoney) {
		this.bidMoney = bidMoney;
	}
	public List<CommonDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<CommonDetail> detailList) {
		this.detailList = detailList;
	}
	public Integer getRequestNum() {
		return requestNum;
	}
	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public Date getIntentDate() {
		return intentDate;
	}
	public void setIntentDate(Date intentDate) {
		this.intentDate = intentDate;
	}
	public String getOtherThirdpayFlagId() {
		return otherThirdpayFlagId;
	}
	public void setOtherThirdpayFlagId(String otherThirdpayFlagId) {
		this.otherThirdpayFlagId = otherThirdpayFlagId;
	}
	public Long getOtherUserId() {
		return otherUserId;
	}
	public void setOtherUserId(Long otherUserId) {
		this.otherUserId = otherUserId;
	}
	public String getOtherLoginName() {
		return otherLoginName;
	}
	public void setOtherLoginName(String otherLoginName) {
		this.otherLoginName = otherLoginName;
	}
	public String getUniqueId() {
		return UniqueId;
	}
	public void setUniqueId(String uniqueId) {
		UniqueId = uniqueId;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankFastPay() {
		return bankFastPay;
	}
	public void setBankFastPay(String bankFastPay) {
		this.bankFastPay = bankFastPay;
	}
	public String getNewBankCardNumber() {
		return newBankCardNumber;
	}
	public void setNewBankCardNumber(String newBankCardNumber) {
		this.newBankCardNumber = newBankCardNumber;
	}
	public Long getSlEarlyRepaymentId() {
		return slEarlyRepaymentId;
	}
	public void setSlEarlyRepaymentId(Long slEarlyRepaymentId) {
		this.slEarlyRepaymentId = slEarlyRepaymentId;
	}
	public Date getQueryStartDate() {
		return queryStartDate;
	}
	public void setQueryStartDate(Date queryStartDate) {
		this.queryStartDate = queryStartDate;
	}
	public Date getQueryEndDate() {
		return queryEndDate;
	}
	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
}
