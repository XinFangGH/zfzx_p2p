package com.hurong.credit.action.pay;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.RequestUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.system.MailData;
import com.hurong.credit.model.system.SystemConfig;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.thirdInterface.CsBankService;
import com.hurong.credit.service.thirdInterface.HuiFuService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.service.thirdInterface.WebBankCodeService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;

public class BkHuiFuAction extends BaseAction {

	private BpCustMember bpCustMember;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private PlThirdInterfaceLogService plThirdInterfaceLogService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private WebBankcardService webBankcardService;
	@Resource
	private CsBankService csBankService;
	@Resource
	private WebBankCodeService webBankCodeService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private HuiFuService huiFuService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	public static final String GBKCHARSET = "GBK";
	public static final String UTF8CHARSET = "UTF-8";
	
	public static final String Version20="20";
	
	public int flag=0;
	/**
	 * 借款手续费率
	 * 数字，保留 2 位小数，例如：0.01，0.99，2.00，2.01，999.99．取值范围 0.01<= MaxTenderRate <=999.99 
	 */
	public static final String BorrowerRate_0="1.00";
	/**
	 * 最大投资手续费率
	 * 数字，保留 2 位小数，例如：0.01，0.99，2.00，2.01，999.99．取值范围 0.01<= MaxTenderRate <=999.99 
	 */
	public static final String MaxTenderRate_0="0.10";
	/**
	 * 签名验证密码
	 */
	public static final String SIGNPASS = "530125";
	/**
	 * 版本号定长 2位目前固定为 10 。 如版本升级，能向前兼容。
	 */
	String Version;
	/**
	 * 消息类型变长每一种消息类型代表交易，具体码见 每一种消息类型代表交易，具体码见 HH TUTUTUTUTUTUTUTU
	 */
	String CmdId;
	/**
	 * 商户客号变长 16 位商户客户号，由汇付生成，商户的唯一性标识
	 */
	String MerCustId;
	/**
	 * 用户客号变长 16 位用户客户号，由汇付生成，用户的唯一性标识
	 */
	String UsrId;
	/**
	 * 真实名称变长 50 位
	 */
	String UsrName;
	/**
	 * 卡号类型
	 */
	String IdType;
	/**
	 * 返回前台地址
	 */
	String RetUrl;
	/**
	 * 返回后台通知地址
	 */
	String BgRetUrl;
	/**
	 * 商户私有域 变长 12 0位 为商户的自定义字段，该在交易完成后由 为商户的自定义字段，该在交易完成后由 商户专属 平台 原样返回
	 * 。注意：如果该字段中包含了文符请 。注意：如果该字段中包含了文符请 。注意：如果该字段中包含了文符请 。注意：如果该字段中包含了文符请
	 * 对该字段的数据进行 base64base64base64 base64base64加密后再使用。 加
	 */
	String MerPriv;
	/**
	 * 证件号
	 */
	String IdNo;
	/**
	 * 手机号 定长 11 位 商户专属平台 系
	 */
	String UsrMp;
	/**
	 * 邮箱
	 */
	String UsrEmail;
	/**
	 * 编码集 变长 加签验的时候商户，告知汇付系统是什么编 加签验的时候商户，告知汇付系统是什么编 加签验的时候商户，告知汇付系统是什么编 码
	 */
	String CharSet;
	/**
	 * 加密验证
	 */
	String ChkValue;
	/**
	 * 返回code
	 */
	String RespCode;
	/**
	 * 返回 说明
	 */
	String RespDesc;

	/**
	 * 用户客号 变长 16 位 用户客户号，由汇付生成，用户的唯一性标识
	 */
	String UsrCustId;
	String TrxId;
	/**
	 * 银行账号
	 */
	String OpenAcctId;
	/**
	 * 开户银行代号
	 */
	String OpenBankId;
	/**
	 * 由商户的系统生成，必须保证唯一，请使用纯数字
	 */
	String OrdId;
	/**
	 * 订单日期 定长 8 位 例如：20130307
	 */
	String OrdDate;
	/**
	 * 支 付 网 关 业 网关的细分业务类型，如 B2C、B2B、WH
	 */
	String GateBusiId;
	/**
	 * 借贷记标记 D：借记 C：贷记
	 */
	String DcFlag;
	/**
	 * 交易金额
	 */
	String TransAmt;

	/**
	 * 账户余额 账户资金余额，该余额能真正反映账户的资金量
	 */
	String AcctBal;
	/**
	 * 冻结金额 冻结余额
	 */
	String FrzBal;
	/**
	 * 可用余额 账户可以支取的余额
	 */
	String AvlBal;
	/**
	 * 最 大 投 资 手续费率数字，保留 2 位小数，例如：0.01，0.99，2.00，2.01，999.99
	 */
	String MaxTenderRate;
	/**
	 * 借款人信息
	 */
	String BorrowerDetails;
	/**
	 * 是否冻结定长 1 位是否冻结，Y：冻结；N：不冻结
	 */
	String IsFreeze;
	/**
	 * 冻结订单号
	 */
	String FreezeOrdId;
	/**
	 * 入参扩展域 变长 512 位 用于扩展请求参数
	 */
	String ReqExt;
	/**
	 * 冻结标识定长 18 位 组成规则为：8 位商户专属平台日期+10 位系统流水号
	 */
	String FreezeTrxId;
	/**
	 * 返参扩展域 变长 512 位 用于扩展返回参数
	 */
	String RespExt;
	/**
	 * 出账客户号	变长 16 位	出账客户号，由汇付生成，用户的唯一性标识
	 */
	String OutCustId;
	/**
	 * 手续费
	 */
    String Fee;
    /**
     * 入账客户
     */
    String InCustId;
    /**
     * 由商户的系统生成，必须保证唯一。如果本次交易从属
于另一个交易流水，则需要通过填写该流水号来关联。
例如：本次放款：商户流水号是 OrdId，日期是 OrdDate，
关联投标订单流水是 SubOrdId，日期是 SubOrdDate
     */
    String SubOrdId;
    String SubOrdDate;
    /**
     * 是否默认 Y:是默认，N：不是默认
     */
    String IsDefault;
    /**
     * 分账账户串
     * 放款1.0、还款1.0、债权转让1.0接口格式：
     *  [{"DivAcctId":"MDT000023","DivAmt":"1.00"}, 
     *  {"DivAcctId":"MDT000024","DivAmt":"2.00"}, 
		*{"DivAcctId":"MDT000025","DivAmt":"3.00"}] 
		
		*放款2.0、还款2.0接口格式：
	*	[{"DivCustId":"6000060000009547","DivAcctId":"MDT
	*	000001","DivAmt":"1.00"}, 
	*	{"DivCustId":"6000060000002526","DivAcctId":"MDT0
	*	00001","DivAmt":"2.00"}, 
	*	{"DivCustId":"6000060000002528","DivAcctId":"MDT0
	*	00001","DivAmt":"3.00"}]

     */
    String DivDetails;
    
    String OutAcctId;
    String InAcctId;
    
    String DivAcctId;
    /**
     * 续 费 收 取 对象标志  I/O 
     * I：向入款客户号InCustId收取
       O：向出款客户号OutCustId收取
     */
    String FeeObjFlag;
    String ServFee; //服务费
    String ServFeeAcctId; //收取服务费子账号
 String remark;
    
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
   

    
    public String getServFee() {
		return ServFee;
	}

	public void setServFee(String servFee) {
		ServFee = servFee;
	}

	public String getServFeeAcctId() {
		return ServFeeAcctId;
	}

	public void setServFeeAcctId(String servFeeAcctId) {
		ServFeeAcctId = servFeeAcctId;
	}

	public String getOutAcctId() {
		return OutAcctId;
	}

	public void setOutAcctId(String outAcctId) {
		OutAcctId = outAcctId;
	}

	public String getInAcctId() {
		return InAcctId;
	}

	public void setInAcctId(String inAcctId) {
		InAcctId = inAcctId;
	}

	String IsUnFreeze;
    String UnFreezeOrdId;
    
    
    
    public String getIsUnFreeze() {
		return IsUnFreeze;
	}

	public void setIsUnFreeze(String isUnFreeze) {
		IsUnFreeze = isUnFreeze;
	}

	public String getUnFreezeOrdId() {
		return UnFreezeOrdId;
	}

	public void setUnFreezeOrdId(String unFreezeOrdId) {
		UnFreezeOrdId = unFreezeOrdId;
	}

	public String getFeeObjFlag() {
		return FeeObjFlag;
	}

	public void setFeeObjFlag(String feeObjFlag) {
		FeeObjFlag = feeObjFlag;
	}

	public String getOutCustId() {
		return OutCustId;
	}

	public void setOutCustId(String outCustId) {
		OutCustId = outCustId;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getInCustId() {
		return InCustId;
	}

	public void setInCustId(String inCustId) {
		InCustId = inCustId;
	}

	public String getSubOrdId() {
		return SubOrdId;
	}

	public void setSubOrdId(String subOrdId) {
		SubOrdId = subOrdId;
	}

	public String getSubOrdDate() {
		return SubOrdDate;
	}

	public void setSubOrdDate(String subOrdDate) {
		SubOrdDate = subOrdDate;
	}

	public String getIsDefault() {
		return IsDefault;
	}

	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}

	public String getDivDetails() {
		return DivDetails;
	}

	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}

	public String getDivAcctId() {
		return DivAcctId;
	}

	public void setDivAcctId(String divAcctId) {
		DivAcctId = divAcctId;
	}

	public String getDivCustId() {
		return DivCustId;
	}

	public void setDivCustId(String divCustId) {
		DivCustId = divCustId;
	}

	public String getDivAmt() {
		return DivAmt;
	}

	public void setDivAmt(String divAmt) {
		DivAmt = divAmt;
	}

	/**
     * 手 续 费 分 账客户号
     */
    String DivCustId;
    String DivAmt;
    
	public String getAcctBal() {
		return AcctBal;
	}

	public void setAcctBal(String acctBal) {
		AcctBal = acctBal;
	}

	public String getFrzBal() {
		return FrzBal;
	}

	public void setFrzBal(String frzBal) {
		FrzBal = frzBal;
	}

	public String getAvlBal() {
		return AvlBal;
	}

	public void setAvlBal(String avlBal) {
		AvlBal = avlBal;
	}

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getOrdDate() {
		return OrdDate;
	}

	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}

	public String getGateBusiId() {
		return GateBusiId;
	}

	public void setGateBusiId(String gateBusiId) {
		GateBusiId = gateBusiId;
	}

	public String getDcFlag() {
		return DcFlag;
	}

	public void setDcFlag(String dcFlag) {
		DcFlag = dcFlag;
	}

	public String getTransAmt() {
		return TransAmt;
	}

	public void setTransAmt(String transAmt) {
		TransAmt = transAmt;
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

	public String getTrxId() {
		return TrxId;
	}

	public void setTrxId(String trxId) {
		TrxId = trxId;
	}

	public String getUsrCustId() {
		return UsrCustId;
	}

	public void setUsrCustId(String usrCustId) {
		UsrCustId = usrCustId;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
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

	public String getMerCustId() {
		return MerCustId;
	}

	public void setMerCustId(String merCustId) {
		MerCustId = merCustId;
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

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		retUrl=StringUtil.stringURLDecoderByUTF8(retUrl);
		RetUrl = retUrl;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		bgRetUrl=StringUtil.stringURLDecoderByUTF8(bgRetUrl);
		BgRetUrl = bgRetUrl;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
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

	public String getCharSet() {
		return CharSet;
	}

	public void setCharSet(String charSet) {
		CharSet = charSet;
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

	public String getMaxTenderRate() {
		return MaxTenderRate;
	}

	public void setMaxTenderRate(String maxTenderRate) {
		MaxTenderRate = maxTenderRate;
	}

	public String getBorrowerDetails() {
		return BorrowerDetails;
	}

	public void setBorrowerDetails(String borrowerDetails) {
		BorrowerDetails = borrowerDetails;
	}

	public String getIsFreeze() {
		return IsFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		IsFreeze = isFreeze;
	}

	public String getFreezeOrdId() {
		return FreezeOrdId;
	}

	public void setFreezeOrdId(String freezeOrdId) {
		FreezeOrdId = freezeOrdId;
	}

	public String getReqExt() {
		return ReqExt;
	}

	public void setReqExt(String reqExt) {
		ReqExt = reqExt;
	}

	public String getFreezeTrxId() {
		return FreezeTrxId;
	}

	public void setFreezeTrxId(String freezeTrxId) {
		FreezeTrxId = freezeTrxId;
	}

	public String getRespExt() {
		return RespExt;
	}

	public void setRespExt(String respExt) {
		RespExt = respExt;
	}
	
	 String FeeAmt ;
	    String FeeCustId ;
	    String FeeAcctId ;
	    
	    
		public String getFeeAmt() {
			return FeeAmt;
		}

		public void setFeeAmt(String feeAmt) {
			FeeAmt = feeAmt;
		}

		public String getFeeCustId() {
			return FeeCustId;
		}

		public void setFeeCustId(String feeCustId) {
			FeeCustId = feeCustId;
		}

	public String getFeeAcctId() {
			return FeeAcctId;
		}

		public void setFeeAcctId(String feeAcctId) {
			FeeAcctId = feeAcctId;
		}
	/**
	 * 汇付注册 返回参数
	 * 
	 * @return
	 */
	public String register() {
		System.out.println("注册进入后台==========");
		String msgData = CmdId + RespCode + MerCustId + UsrId + UsrCustId + BgRetUrl + TrxId + RetUrl + MerPriv;
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		// 校验
		sign(msgData);
		// 记录操作日志
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付注册接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember==null?"":bpCustMember.getLoginname(), "", "", "");
		setJsonString("RECV_ORD_ID_"+TrxId);
		return SUCCESS;
	}

	/**
	 * 返回充值参数
	 * 
	 * @return
	 */
	public String recharge() {
		System.out.println("===进入后台1"+RespDesc);
		System.out.println("===进入后台1"+CmdId);
		String msgData = CmdId + RespCode + MerCustId + UsrCustId + OrdId
				+ OrdDate + TransAmt + TrxId + RetUrl + BgRetUrl + MerPriv;
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		// 校验
		sign(msgData);
		// 记录操作日志
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付充值接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember.getTruename(), "", "", "");
		setJsonString("RECV_ORD_ID_"+TrxId);
		return SUCCESS;
	}
    //投标
	public String tender() {
		String msgData = CmdId+ RespCode + MerCustId  + OrdId  + OrdDate  +  TransAmt  +  UsrCustId  + TrxId + IsFreeze+ FreezeOrdId+FreezeTrxId +RetUrl + BgRetUrl + MerPriv+ (RespExt==null?"":RespExt) ;
		// 校验
		sign(msgData);
		// 记录操作日志
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付投标接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember.getTruename(), OrdId, FreezeTrxId, OrdDate); //投标是记录 投标订单号 和解冻订单号 和投标 日期
		setJsonString("RECV_ORD_ID_"+OrdId);
		return SUCCESS;
	}
	//放款
	public String loans(){
		String msgData =  CmdId  + RespCode + MerCustId  + OrdId  + OrdDate  + OutCustId  + OutAcctId 
		+TransAmt+ Fee+  InCustId  + InAcctId  +SubOrdId+ SubOrdDate+ FeeObjFlag+ IsDefault +
IsUnFreeze + UnFreezeOrdId + FreezeTrxId + BgRetUrl + MerPriv + (RespExt==null?"":RespExt) ;
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		// 校验
		sign(msgData);
		// 记录操作日志
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付放款接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember.getTruename(), OrdId, "", "");
		setJsonString("RECV_ORD_ID_"+OrdId);
		return SUCCESS;
	}
	/**
	 * 还款返回
	 * @return
	 */
	public String repayment(){
		String msgData = CmdId  + RespCode+  MerCustId  + OrdId  + OrdDate  + OutCustId  + SubOrdId+ 

		SubOrdDate+ OutAcctId  + TransAmt+ Fee  + InCustId+ InAcctId+ FeeObjFlag  + BgRetUrl+ 

		MerPriv + (RespExt==null?"":RespExt);
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		// 校验
		sign(msgData);
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付还款接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember.getTruename(), OrdId, "", ""); 
		setJsonString("RECV_ORD_ID_"+OrdId);
		return SUCCESS;
	}
	//绑卡接口
	public String bindCard(){
		System.out.println("绑定银行卡 进入1");
		flag=flag+1;
		if(flag==1){
		String msgData = CmdId + RespCode+ MerCustId + OpenAcctId+ OpenBankId + UsrCustId+ TrxId + BgRetUrl + MerPriv;
		// 校验
		sign(msgData);
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
				"汇付绑卡接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				bpCustMember.getTruename(), OrdId, "", "RECV_ORD_ID_"+TrxId); 
		}
		flag=0;
		setJsonString("RECV_ORD_ID_"+TrxId);
		return SUCCESS;
	}
    //提现
	public String WebWithdraw(){
		System.out.println("进入取现接口后台======="+RespCode+"返回消息"+RespDesc);
		String msgData =CmdId + RespCode + MerCustId+ OrdId + UsrCustId + TransAmt+ OpenAcctId + 
		OpenBankId + FeeAmt + FeeCustId + FeeAcctId + ServFee + ServFeeAcctId +RetUrl + BgRetUrl+ 
		MerPriv+ (RespExt==null?"":RespExt);
		System.out.println("后台 取现 参数=="+msgData);
		bpCustMember=bpCustMemberService.getMemberByRet(UsrCustId);
		// 校验
		sign(msgData);
		//取现的日志 加到验证里 为了保存 取现手续费的订单号
		setJsonString("RECV_ORD_ID_"+OrdId);
		return SUCCESS;
	}

	// 验证
	private void sign(String msgData) {
		System.out.println("===========进入后台");
		try{
		boolean isSuccess = huiFuService.DecodSign(msgData, ChkValue);
		System.out.println("===========进入后台"+isSuccess);
		RespDesc=StringUtil.stringURLDecoderByUTF8(RespDesc);
		if (isSuccess&&RespCode.equals("000")) {
			System.out.println("===========进入后台"+RespCode);
			// 注册
			if (CmdId.equals("UserRegister")) {
				updateMember();
			} else if (CmdId.equals("NetSave")) {
				updateAccount(TransAmt,ObAccountDealInfo.T_RECHARGE,ObAccountDealInfo.DEAL_STATUS_2,ObAccountDealInfo.DIRECTION_INCOME,OutCustId,OrdId);
			}else if(CmdId.equals("InitiativeTender")||CmdId.equals("AutoTender")){
				System.out.println("===========进入后台投标");
				// 保存交易记录到 erp
				BpCustMember cpCut = bpCustMemberService.getMemberByRet(UsrCustId);
				 Map<String,Object> map=new HashMap<String,Object>();
				 map.put("investPersonId",cpCut.getId());//投资人Id
				 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				 map.put("money",new BigDecimal(TransAmt));//交易金额
				 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
				 map.put("recordNumber",OrdId);//交易流水号
				 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 obAccountDealInfoService.operateAcountInfo(map);
				//obAccountDealInfoService.operateAcountInfo(cpCut.getId().toString(), ObAccountDealInfo.T_INVEST.toString(), TransAmt, "3", ObSystemAccount.type0.toString(), ObAccountDealInfo.UNFREEZY.toString(), ObAccountDealInfo.DEAL_STATUS_1.toString(), OrdId);
				// payCommonService.dealToERP(cpCut, TransAmt.toString(),OrdId,"4");
				 
				    PlBidInfo plBidInfo=plBidInfoService.getByOrdId(OrdId);
				    //更新标的状态
				    try{
					plBidPlanService.updateStatByMoney(plBidInfo.getBidId(), new BigDecimal(TransAmt));
				    }catch(Exception e){
				    	e.printStackTrace();
				    }
				    plBidInfo.setState(PlBidInfo.TYPE1);
					plBidInfo.setOrderNo(OrdId);
					plBidInfoService.save(plBidInfo);//修改p2p投标信息
					plBidInfoService.saveToERP(plBidInfo.getId().toString(),OrdId);//投资人列表到erp 投标成功后保存
					
					//发送短信
					PlBidPlan plan=plBidPlanService.get(plBidInfo.getBidId());
					String temp=AppUtil.getSysConfig().get("smsBid").toString();
					String content=temp.replace("${projName}", plan==null?"":plan.getBidProName()).replace("${code}", TransAmt).replace("${phone4}", configMap.get("phone").toString()==null?"":configMap.get("phone").toString()).replace("${subject}", configMap.get("subject").toString()==null?"":configMap.get("subject").toString());
					//clientService.sendSMS(configMap.get("erpURL").toString()+AppUtil.getSmsURL(), content, cpCut.getTelphone(), "1");
					//发送邮件
				     MailData md=new MailData("投标提醒",cpCut.getEmail());
				     md.setMoney(TransAmt);
				     md.setProjName(plan.getBidProName());
				     md.setProjNumber(plan.getBidProNumber());
				     md.setBidId(plan.getBidId().toString());
				     md.setTime(DateUtil.dateToStr(plBidInfo.getBidtime(),"yyyy-MM-dd HH:mm:ss"));
				     sendMail("mail/sendTenderMsg.vm",md);
			}else if(CmdId.equals("Loans")){
				//更新投资人账户信息
				//updateAccount(TransAmt,"4","2",OutCustId,OrdId);
				updateAccount(TransAmt,ObAccountDealInfo.T_INVEST,ObAccountDealInfo.DEAL_STATUS_2,ObAccountDealInfo.DIRECTION_PAY,OutCustId,OrdId);
				//给借款人账户加入一笔资金
				BpCustMember cpCut = bpCustMemberService.getMemberByRet(InCustId);
				Map<String,Object> map=new HashMap<String,Object>();
				 map.put("investPersonId",cpCut.getId());//投资人Id
				 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 map.put("transferType",ObAccountDealInfo.T_INMONEY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				 map.put("money",new BigDecimal(TransAmt));//交易金额
				 map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
				 map.put("recordNumber",OrdId);//交易流水号
				 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 obAccountDealInfoService.operateAcountInfo(map);
				//String[]  ret = obAccountDealInfoService.operateAcountInfo(cpCut.getId().toString(), ObAccountDealInfo.T_INMONEY.toString(), TransAmt, "3", ObSystemAccount.type0.toString(), ObAccountDealInfo.ISAVAILABLE.toString(), ObAccountDealInfo.DEAL_STATUS_2.toString(), OrdId);
				//String[]  ret = payCommonService.dealToERP(cpCut, TransAmt,OrdId,"3");
			}else if(CmdId.equals("Cash")){
				// 保存交易记录到 erp
				BpCustMember cashcpCut = bpCustMemberService.getMemberByRet(UsrCustId);
				Map<String,Object> map=new HashMap<String,Object>();
				 map.put("investPersonId",cashcpCut.getId());//投资人Id
				 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				 map.put("money",new BigDecimal(TransAmt));//交易金额
				 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
				 map.put("recordNumber",OrdId);//交易流水号
				 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 obAccountDealInfoService.operateAcountInfo(map);
				 //obAccountDealInfoService.operateAcountInfo(cashcpCut.getId().toString(), "2", TransAmt, "3", "0", "0", "2", OrdId);
				//更新取现账户信息 取现复核通过后 执行
				//updateAccount(TransAmt,"2","2",UsrCustId,OrdId);
				System.out.println("取现===0"+OrdId+"===="+TransAmt);
				String newOrdId="";
				//如果取现手续费 不为空 更新账户信息 在取现复核接口中进行 手续费的收取
				if(ServFee!=null&&!ServFee.equals("")){
					System.out.println("取现手续费==="+ServFee);
					 newOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");//生成新的订单号
					 Map<String,Object> mapF=new HashMap<String,Object>();
					 map.put("investPersonId",cashcpCut.getId());//投资人Id
					 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					 map.put("transferType",ObAccountDealInfo.T_LOANINCOME);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					 map.put("money",new BigDecimal(TransAmt));//交易金额
					 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
					 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
					 map.put("recordNumber",OrdId);//交易流水号
					 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
					 obAccountDealInfoService.operateAcountInfo(mapF);
					// obAccountDealInfoService.operateAcountInfo(cashcpCut.getId().toString(), ObAccountDealInfo.T_LOANINCOME.toString(), ServFee, "3", ObSystemAccount.type0.toString(), ObAccountDealInfo.ISAVAILABLE.toString(), ObAccountDealInfo.DEAL_STATUS_2.toString(), newOrdId);
					//更新借款人账户信息
					//updateAccount(ServFee,"6","2",UsrCustId,newOrdId);
				}
				
				plThirdInterfaceLogService.saveLog(RespCode, RespDesc, RequestUtil.queryString(this.getRequest()),
						"汇付取现接口", null, PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
						bpCustMember.getTruename(), OrdId,newOrdId, "RECV_ORD_ID_"+OrdId);  //remk2 保存提现手续费订单号 在取现复核中使用
			}else if(CmdId.equals("UserBindCard")){//绑卡接口
				try{
                WebBankcard bankCard=webBankcardService.getByCardNum(OpenAcctId);
                System.out.println("==="+OpenAcctId);
				if(bankCard==null){
				   System.out.println("bankCard====");
					bankCard=new WebBankcard();
				BpCustMember cpCut = bpCustMemberService.getMemberByRet(UsrCustId);
				
				bankCard.setThirdConfig(configMap.get("thirdPayConfig").toString());
				bankCard.setUserFlg(UsrCustId);
				bankCard.setCardNum(OpenAcctId);
				bankCard.setBankId(OpenBankId);
				if(webBankCodeService.getByCardName(OpenBankId)!=null){
				bankCard.setBankname(webBankCodeService.getByCardName(OpenBankId).getBankName());
				}else{
					bankCard.setBankname("");	
				}
				bankCard.setUsername(cpCut.getTruename());
				webBankcardService.save(bankCard);
				System.out.println("===save==");
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			if (CmdId.equals("NetSave")) {
				updateAccount(TransAmt,ObAccountDealInfo.T_RECHARGE,ObAccountDealInfo.DEAL_STATUS_3,ObAccountDealInfo.DIRECTION_INCOME,OutCustId,OrdId);
				//updateAccount(TransAmt,"1","3",UsrCustId,OrdId);
			}else if(CmdId.equals("InitiativeTender")||CmdId.equals("AutoTender")){
				//updateAccount("4","3");
			}else if(CmdId.equals("Loans")){
				//更新投资人账户信息
				//updateAccount(TransAmt,"4","3",OutCustId,OrdId);
				updateAccount(TransAmt,ObAccountDealInfo.T_INVEST,ObAccountDealInfo.DEAL_STATUS_3,ObAccountDealInfo.DIRECTION_PAY,OutCustId,OrdId);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新账户信息
	 * tranAmt 交易金额
	 * type 1表示充值，2表示取现,3收益，4投资，5还本
	 * @param state
	 *            2 成功 3 失败
	 */
	private void updateAccount(String tranAmt,String transferType ,Short state,Short dealDirection, String usrCustId,String ordId) {
		bpCustMember = bpCustMemberService.getMemberByRet(usrCustId);
		String[] erpArr = new String[2];
		/*erpArr = payCommonService.updateDealToERP(bpCustMember, tranAmt,
				ordId, type, state);*/
		Map<String,Object> map=new HashMap<String,Object>();
		 map.put("investPersonId",bpCustMember.getId());//投资人Id
		 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
		 map.put("transferType",transferType);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
		 map.put("money",new BigDecimal(tranAmt));//交易金额
		 map.put("dealDirection",dealDirection);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
		 map.put("DealInfoId",null);//交易记录id，没有默认为null
		 map.put("recordNumber",ordId);//交易流水号
		 map.put("dealStatus",state);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
		 erpArr=obAccountDealInfoService.updateAcountInfo(map);
		//erpArr =obAccountDealInfoService.updateAcountInfo(bpCustMember.getId(), type, tranAmt, ObSystemAccount.type0.toString(), ordId, null, state);
		System.out.println(erpArr[0]+"======"+erpArr[1]+"type==="+transferType+"state=="+state);
	}

	/**
	 * 更新账户信息
	 */
	private void updateMember() {
		// 后台去掉
		bpCustMember=bpCustMemberService.getMemberByRet(UsrId, IdNo);
		System.out.println("===后台注册"+bpCustMember.getLoginname());
		if (bpCustMember != null) {
			bpCustMember.setThirdPayConfig(configMap.get("thirdPayConfig").toString());
			bpCustMember.setIsCheckCard("1");
			bpCustMember.setCardcode(IdNo);
			bpCustMember.setCardtype(0);
			bpCustMember.setThirdPayFlag0(UsrId); // 提交到汇付 的登录名 由汇付修改为
			// dry_yzc666返回
			bpCustMember.setThirdPayFlagId(UsrCustId);// 汇付返回的唯一标识
			bpCustMemberService.save(bpCustMember);
			
			// 实名认证完成后 生成 虚拟账号
			String[] account = new String[2];
			account = obSystemAccountService.saveAccount("1",  bpCustMember
					.getTruename(), bpCustMember.getId().toString(), bpCustMember
					.getCardcode(), "0", ObSystemAccount.type0.toString());
			/*account = payCommonService.saveAccount("1", bpCustMember
					.getTruename(), bpCustMember.getId().toString(), bpCustMember
					.getCardcode(), "0", "0");*/
		}
	}
	public boolean sendMail(String vm, MailData md) {
		boolean ret = false;
		// 邮件实体
		MailModel mode = new MailModel();
		
		// 邮件模版需要的数据
		Map<String, Object> mailData = new HashMap<String, Object>();
		mailData.put("mailData", md);
		mode.setMailData(mailData);
		mode.setTo(md.getToEmail());
		mode.setSubject(md.getSubject());
		// 邮件发送类
		MailMessageConsumer mailMessage = new MailMessageConsumer();
		// 发送
		try {
			mailMessage.sendMail(vm,mode);
			ret = true;
		} catch (Exception e) {

		}
		return ret;
	}
	
}
