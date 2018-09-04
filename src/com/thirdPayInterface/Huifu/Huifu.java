package com.thirdPayInterface.Huifu;

import java.util.List;

/**
 * 汇付天下
 * @author Administrator
 *
 */
public class Huifu{
	//
	
	
	
	public String getMaxTenderRate() {
		return MaxTenderRate;
	}
	public void setMaxTenderRate(String maxTenderRate) {
		MaxTenderRate = maxTenderRate;
	}
	public static final String GBKCHARSET = "GBK";
	public static final String CHARSETUTF8 = "UTF-8";
	/**
	 * 版本号
	 * 定长 2 位
	 * 目前固定为 10。如版本升级，能向前兼容
	 */
	private String Version="10" ;
	/**
	 * 消息类型
	 * 变长
	 * 每一种消息类型代表一种交易，具体交易类型代码见HHTUTU接口交易类型定义UUTT 
	 */
	private String CmdId;
	public String getBorrowerDetails() {
		return BorrowerDetails;
	}
	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}
	/**
	 * 前台系统返回地址
	 */
	private String RetUrl;
	/**
	 * 后台系统返回地址
	 */
	private String BgRetUrl;
	/**
	 * 商户私有域  
	 * 变长 120 位 
	 * 为商户的自定义字段， 该字段在交易完成后由商户专属平台原样返回。 注意： 如果该字段中包含了中文字符请对该字段的数据进行 base64 加密后再使用
	 */
	private String MerPriv;
	/**
	 * 签名  
	 * 定长 256 位
	 * 各接口所列有的请求参数和返回参数如无个别说明， 都需要参与签名， 参与签名的数据体为： 按照每个接口中包含的参数值（不包含参数名）的顺序（按接口表格中
     * 从左到右，从上到下的顺序）进行字符串相加。如果参数为可选项并且为空，则该参数值不参与签名
	 */
	private String ChkValue ;
	/**
	 * 应答返回码
	 * 定长 3 位
	 * 000 代表交易成功 ,其它代表失败
	 */
	private String RespCode;
	/**
	 * 应答描述
	 * 变长 
	 * 如果 RespCode 为失败，则该域为具体的错误信息描述
	 */
	private String RespDesc;
	/**
	 * 商户客户号
	 * 变长 16 位
	 * 商户客户号，由汇付生成，商户的唯一性标识
	 */
	private String  MerCustId;
	/**
	 * 用户客户号
	 * 变长 16 位
	 * 用户客户号，由汇付生成，用户的唯一性标识
	 */
	private String UsrCustId ;
	/**
	 * 用户号
	 * 变长 6-25 位
	 * 商户下的平台用户号， 在每个商户下唯一 （ 必须是 6 6- - 25位的半角字符）
	 */
	private String UsrId;
	/**
	 * 真实名称
	 * 变长 50 位
	 * 用户的真实姓名
	 */
	private String UsrName ;
	/**
	 * 证件类型
	 * 定长 2 位
	 * '00' – 身份证
	 */
	private String IdType;
	/**
	 * 证件号码
	 * 变长 30 位
	 * 用户证件号码
	 */
	private String IdNo ;
	/**
	 * 手机号
	 * 定长 11 位
	 * 商户专属平台系统提供按照手机号查询订单的功能
	 */
	private String UsrMp;
	/**
	 * 用户 Email
	 * 变长 40 位
	 * 操作员的 Email
	 */
	private String UsrEmail;
	/**
	 * 用户登录密码
	 * 定长 32 位
	 * 用户登录密码
	 */
	private String LoginPwd ;
	/**
	 * 用户交易密码
	 * 定长 32 位
	 * 用户交易密码
	 */
	private String TransPwd;
	/**
	 * 开户银行账号
	 * 变长 40 位
	 * 取现银行的账户号（银行卡号）
	 */
	private String OpenAcctId  ;
	/**
	 * 开户银行代号
	 * 变长 8 位
	 * 具体参见客服提供的相关文档
	 */
	private String OpenBankId;
	/**
	 * 开户银行账号
	 * 变长 40 位 
	 * 取现银行的账户号（银行卡号）
	 */
	private String CardId;
	/**
	 * 开户银行代号
	 * 变长 8 位 
	 * 具体参见客服提供的相关文档。
	 */
	private String GateBankId;
	/**
	 * 开户银行省份
	 * 定长 4 位 
	 * 具体参见 HHTUTU 客服提供的相关文档。 UUTT
	 */
	private String OpenProvId ;
	/**
	 * 
	 */
	private String OpenAreaId;
	/**
	 * 编码集
	 * @return
	 */
	private String charSet;
	/**
	 *订单号 
	 */
	private String ordId;
	/**
	 *订单日期 
	 */
	private String ordDate;
	/**
	 * 交易金额
	 * 
	 * @return
	 */
	private String transAmt;
	/**
	 * 用户客户号
	 * @return
	 */
	private String usrCustId;
	/**
	 * 借款人信息
	 * @return
	 */
	
	private String BorrowerDetails;
	/**
	 * 订单号
	 */
	private String OrdId;
	/**
	 * 订单日期 
	 */
	private String OrdDate;
	/**
	 * 交易金额
	 */
	private String TransAmt;
	/**
	 * 子账户类型
	 */
	private String SubAcctType;
	/**
	 * 子账户号
	 */
	private String SubAcctId;
	/**
	 * 最大投资手续费率
	 * @return
	 */
	private String MaxTenderRate;
	/**
	 * 是否冻结
	 * IsFreeze
	 * @return
	 */
    private String isFreeze;
    /**
     * 冻结流水号
     * FreezeOrdId 
     * @return
     */
    private String  freezeOrdId;
    /**
     * 请求扩展域
     * 
     * @return
     */
    private String reqExt;
    /**
     * 标的号
     * @return
     */
    private String proId;
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
     * 还款或放款的手续费
     * @return
     */
    private String fee;
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
    /**
     * 还款批次号
     * @return
     */
    private String batchId;
    /**
     * 商户还款订单日期
     * @return
     */
    private String merOrdDate;
    /**
     * 还款账户串
     * @return
     */
    private String inDetails;
    /**
     * 入账账户类型  
     */
    private String inAcctType;
    /**
     * 
     * @return
     */
    private String  inAccId;
    /**
     * 用户名
     * UserName
     * @return
     */
    private String userName;
    private String trxId;
    /**
     * 
     * GuarType
     * @return
     */
    private String guarType;
    
    private String tenderPlanType;//计划投标类型
    
    public String getTenderPlanType() {
		return tenderPlanType;
	}
	public void setTenderPlanType(String tenderPlanType) {
		this.tenderPlanType = tenderPlanType;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getGuarType() {
		return guarType;
	}
	public void setGuarType(String guarType) {
		this.guarType = guarType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInAccId() {
		return inAccId;
	}
	public void setInAccId(String inAccId) {
		this.inAccId = inAccId;
	}
	public String getInAcctType() {
		return inAcctType;
	}
	public void setInAcctType(String inAcctType) {
		this.inAcctType = inAcctType;
	}
	public String getInDetails() {
		return inDetails;
	}
	public void setInDetails(String inDetails) {
		this.inDetails = inDetails;
	}
	public String getMerOrdDate() {
		return merOrdDate;
	}
	public void setMerOrdDate(String merOrdDate) {
		this.merOrdDate = merOrdDate;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
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
	public String getOutCustId() {
		return outCustId;
	}
	public void setOutCustId(String outCustId) {
		this.outCustId = outCustId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getGateBusiId() {
		return gateBusiId;
	}
	public void setGateBusiId(String gateBusiId) {
		this.gateBusiId = gateBusiId;
	}
	public String getDcFlag() {
		return dcFlag;
	}
	public void setDcFlag(String dcFlag) {
		this.dcFlag = dcFlag;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	/**
     * 支付网关业务代号
     * GateBusiId
     * @return
     */
    private String gateBusiId;
    /**
     * 借贷记标记
     * @return
     */
    private String dcFlag;
    
    /**
     * 身份证号
     * @return
     */
    private String certId;
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
	private String pageType;
	public String getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}
	public String getFreezeOrdId() {
		return freezeOrdId;
	}
	public void setFreezeOrdId(String freezeOrdId) {
		this.freezeOrdId = freezeOrdId;
	}
	/**
	 * 复核标识
	 */
	private String AuditFlag;
	/**
	 * 组织结构代码
	 */
	private String InstuCode;
	/**
	 * 营业执照号码
	 */
	private String BusiCode;
	/**
	 * 税务登记号
	 */
	private String TaxCode;
	/**
	 * 交易类型
	 */
	private String QueryTransType;
	/**
	 * 出账账户
	 */
	private String OutCustId;
	/**
	 * 出账子账户
	 */
	private String OutAcctId;
	/**
	 * 入账账户
	 */
	private String InCustId; 
	private String sellCustId;//转让人客户号
	private String creditAmt;//转让金额
	private String creditDealAmt;//承接金额
	private String bidDetails;//债权转让明细
	private String isDefault;//是否默认
	private String isUnFreeze;//是否解冻
	private String unFreezeOrdId;//解冻订单号
	private String freezeTrxId;//解冻标识
	
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
		return bidDetails;
	}
	public void setBidDetails(String bidDetails) {
		this.bidDetails = bidDetails;
	}
	public String getBuyCustId() {
		return buyCustId;
	}
	public void setBuyCustId(String buyCustId) {
		this.buyCustId = buyCustId;
	}
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	public String getTotalLcAmt() {
		return totalLcAmt;
	}
	public void setTotalLcAmt(String totalLcAmt) {
		this.totalLcAmt = totalLcAmt;
	}
	private String buyCustId;//承接人客户号
	private String lcId;//挂牌债权ID
	private String totalLcAmt;//挂牌债权总金额
	public String getQueryTransType() {
		return QueryTransType;
	}
	public void setQueryTransType(String queryTransType) {
		QueryTransType = queryTransType;
	}
	public String getInstuCode() {
		return InstuCode;
	}
	public void setInstuCode(String instuCode) {
		InstuCode = instuCode;
	}
	public String getBusiCode() {
		return BusiCode;
	}
	public void setBusiCode(String busiCode) {
		BusiCode = busiCode;
	}
	public String getTaxCode() {
		return TaxCode;
	}
	public void setTaxCode(String taxCode) {
		TaxCode = taxCode;
	}
	public String getAuditFlag() {
		return AuditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		AuditFlag = auditFlag;
	}
	public String getSubAcctType() {
		return SubAcctType;
	}
	public void setSubAcctType(String subAcctType) {
		SubAcctType = subAcctType;
	}
	public String getSubAcctId() {
		return SubAcctId;
	}
	public void setSubAcctId(String subAcctId) {
		SubAcctId = subAcctId;
	}
	public String getTransAmt() {
		return TransAmt;
	}
	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
	}
	public String getOrdDate() {
		return OrdDate;
	}
	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}
	public String getOrdId() {
		return OrdId;
	}
	public void setOrdId(String ordId) {
		OrdId = ordId;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getCmdId() {
		return CmdId;
	}
	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}
	public String getMerPriv() {
		return MerPriv;
	}
	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}
	public String getChkValue() {
		return ChkValue;
	}
	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}
	public String getRespCode() {
		return RespCode;
	}
	public void setRespCode(String respCode) {
		RespCode = respCode;
	}
	public String getRespDesc() {
		return RespDesc;
	}
	public void setRespDesc(String respDesc) {
		RespDesc = respDesc;
	}
	public String getMerCustId() {
		return MerCustId;
	}
	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
	}
	public String getUsrCustId() {
		return UsrCustId;
	}
	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}
	public String getUsrId() {
		return UsrId;
	}
	public void setUsrId(String usrId) {
		UsrId = usrId;
	}
	public String getUsrName() {
		return UsrName;
	}
	public void setUsrName(String usrName) {
		UsrName = usrName;
	}
	public String getIdType() {
		return IdType;
	}
	public void setIdType(String idType) {
		IdType = idType;
	}
	public String getIdNo() {
		return IdNo;
	}
	public void setIdNo(String idNo) {
		IdNo = idNo;
	}
	public String getUsrMp() {
		return UsrMp;
	}
	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}
	public String getUsrEmail() {
		return UsrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		UsrEmail = usrEmail;
	}
	public String getLoginPwd() {
		return LoginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		LoginPwd = loginPwd;
	}
	public String getTransPwd() {
		return TransPwd;
	}
	public void setTransPwd(String transPwd) {
		TransPwd = transPwd;
	}
	public String getOpenAcctId() {
		return OpenAcctId;
	}
	public void setOpenAcctId(String openAcctId) {
		OpenAcctId = openAcctId;
	}
	public String getOpenBankId() {
		return OpenBankId;
	}
	public void setOpenBankId(String openBankId) {
		OpenBankId = openBankId;
	}
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getGateBankId() {
		return GateBankId;
	}
	public void setGateBankId(String gateBankId) {
		GateBankId = gateBankId;
	}
	public String getOpenProvId() {
		return OpenProvId;
	}
	public void setOpenProvId(String openProvId) {
		OpenProvId = openProvId;
	}
	public String getOpenAreaId() {
		return OpenAreaId;
	}
	public void setOpenAreaId(String openAreaId) {
		OpenAreaId = openAreaId;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getRetUrl() {
		return RetUrl;
	}
	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}
	public String getBgRetUrl() {
		return BgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}
	
}