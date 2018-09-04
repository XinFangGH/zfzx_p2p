package com.hurong.credit.action.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;

import com.google.gson.annotations.Expose;
import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.RequestUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.financePurchase.BpMoneyManagerService;
import com.hurong.credit.service.pay.BpBidLoanService;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.RsaHelper;
import com.hurong.credit.util.TemplateConfigUtil;

public class FontAction extends BaseAction {
	
	private BpCustMember bpCustMember;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private BpMoneyManagerService bpMoneyManagerService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	PlThirdInterfaceLogService plThirdInterfaceLogService;
	@Resource
	private BpBidLoanService bpBidLoanService;

	private String LoanPlatformAccount;
	private String MoneymoremoreId;
    
	private String AccountNumber;
	private String Mobile;
	private String Email;
	private String RealName;
	private String IdentificationNo;
	
	private String RechargeMoneymoremore;//要充值的账号的乾多多标识
	private String WithdrawMoneymoremore;//要提现的账号的乾多多标识
	private String PlatformMoneymoremore;//平台乾多多标识
	private String LoanNo;//乾多多流水号
	private String OrderNo;//平台的充值订单号
	private String Amount;//金额
	
	private String FeePercent;//平台承担的手续费比例
	private String Fee;//平台承担的手续费金额
	private String FreeLimit;//平台扣除的免费提现额
	private String SignInfo;//加密验证信息
	private String ResultCode;//返回码
	
	private String AuthorizeTypeOpen;//开启授权类型
	private String AuthorizeTypeClose;//关闭授权类型
	private String AuthorizeType;//当前授权类型
	
	private String Message;
	private String bindMessage;
	
	private String AccountType;
	private String RandomTimeStamp;
	private String Remark1;
	private String Remark2;
	private String Remark3;
	
	private String RechargeType; 
	private String CardNoList;
	  
	private String LoanJsonList;
	
	private String Action;
	
	private String FeeMax;
	private String FeeWithdraws;
	
	public String getFeeMax() {
		return FeeMax;
	}


	public void setFeeMax(String feeMax) {
		FeeMax = feeMax;
	}


	public String getFeeWithdraws() {
		return FeeWithdraws;
	}


	public void setFeeWithdraws(String feeWithdraws) {
		FeeWithdraws = feeWithdraws;
	}


	public String getLoanJsonList() {
		return LoanJsonList;
	}


	public void setLoanJsonList(String loanJsonList) {
		LoanJsonList = loanJsonList;
	}


	public String getAction() {
		return Action;
	}


	public void setAction(String action) {
		Action = action;
	}


	public String getRechargeType() {
		return RechargeType;
	}


	public void setRechargeType(String rechargeType) {
		RechargeType = rechargeType;
	}


	public String getCardNoList() {
		return CardNoList;
	}


	public void setCardNoList(String cardNoList) {
		CardNoList = cardNoList;
	}


	public String getRandomTimeStamp() {
		return RandomTimeStamp;
	}


	public void setRandomTimeStamp(String randomTimeStamp) {
		RandomTimeStamp = randomTimeStamp;
	}


	public String getRemark1() {
		return Remark1;
	}


	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}


	public String getRemark2() {
		return Remark2;
	}


	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}


	public String getRemark3() {
		return Remark3;
	}


	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}


	public String getAccountType() {
		return AccountType;
	}


	public void setAccountType(String accountType) {
		AccountType = accountType;
	}


	public String getAuthorizeTypeOpen() {
		return AuthorizeTypeOpen;
	}


	public void setAuthorizeTypeOpen(String authorizeTypeOpen) {
		AuthorizeTypeOpen = authorizeTypeOpen;
	}


	public String getAuthorizeTypeClose() {
		return AuthorizeTypeClose;
	}


	public void setAuthorizeTypeClose(String authorizeTypeClose) {
		AuthorizeTypeClose = authorizeTypeClose;
	}


	public String getAuthorizeType() {
		return AuthorizeType;
	}


	public void setAuthorizeType(String authorizeType) {
		AuthorizeType = authorizeType;
	}


	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}


	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}


	public String getRechargeMoneymoremore() {
		return RechargeMoneymoremore;
	}


	public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
		RechargeMoneymoremore = rechargeMoneymoremore;
	}


	public String getWithdrawMoneymoremore() {
		return WithdrawMoneymoremore;
	}


	public void setWithdrawMoneymoremore(String withdrawMoneymoremore) {
		WithdrawMoneymoremore = withdrawMoneymoremore;
	}


	public String getLoanNo() {
		return LoanNo;
	}


	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}


	public String getOrderNo() {
		return OrderNo;
	}


	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}


	public String getAmount() {
		return Amount;
	}


	public void setAmount(String amount) {
		Amount = amount;
	}


	public String getFeePercent() {
		return FeePercent;
	}


	public void setFeePercent(String feePercent) {
		FeePercent = feePercent;
	}


	public String getFee() {
		return Fee;
	}


	public void setFee(String fee) {
		Fee = fee;
	}


	public String getFreeLimit() {
		return FreeLimit;
	}


	public void setFreeLimit(String freeLimit) {
		FreeLimit = freeLimit;
	}


	public String getResultCode() {
		return ResultCode;
	}


	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}


	public String getLoanPlatformAccount() {
		return LoanPlatformAccount;
	}


	public void setLoanPlatformAccount(String loanPlatformAccount) {
		LoanPlatformAccount = loanPlatformAccount;
	}


	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}


	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}


	public String getMoneymoremoreId() {
		return MoneymoremoreId;
	}


	public void setMoneymoremoreId(String moneymoremoreId) {
		MoneymoremoreId = moneymoremoreId;
	}


	public String getSignInfo() {
		return SignInfo;
	}


	public void setSignInfo(String signInfo) {
		signInfo=signInfo.replaceAll("\r", "").replaceAll("\n", "");
		SignInfo = signInfo;
	}


	public String getAccountNumber() {
		return AccountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}


	public String getMobile() {
		return Mobile;
	}


	public void setMobile(String mobile) {
		Mobile = mobile;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getRealName() {
		return RealName;
	}


	public void setRealName(String realName) {
		RealName = realName;
	}


	public String getIdentificationNo() {
		return IdentificationNo;
	}


	public void setIdentificationNo(String identificationNo) {
		IdentificationNo = identificationNo;
	}




	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	public String getbindMessage() {
		return bindMessage;
	}


	public void setbindMessage(String bindMessage) {
		this.bindMessage = bindMessage;
	}

   /**
    * 验证签名 
    * @param sign 签名数据
    * @param data 原数据
    * @return
    */
	private boolean verifySignature(String sign,String data){
		boolean ret=false;
		MD5 md5=new MD5();
		sign=sign.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "");
		data=md5.getMD5Info(data.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", ""));
		RsaHelper rsa=RsaHelper.getInstance();
		ret=rsa.verifySignature(sign, data, AppUtil.getSysConfig().get("MM_PublicKey").toString());
		return ret;
	}
	/**
	 * 双乾注册绑定接口
	 * @return
	 */
	public String bind(){
		
		String data=AccountType + AccountNumber + Mobile + Email + RealName + IdentificationNo + LoanPlatformAccount + MoneymoremoreId + PlatformMoneymoremore + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
	     
	     if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "开通第三账户:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "开通第三账户:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    
	    return "freemarker";
	 }
	

	/**
	 * 充值返回前台
	 * @return
	 */
	public String recharge(){
		try{
		
		String data=RechargeMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + RechargeType + CardNoList + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
         System.out.println("SignInfo=="+SignInfo);
         System.out.println("data=="+data);
	     if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "充值:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "充值:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return "freemarker";
	 }

	/**
	 * 提现返回前台
	 * @return
	 */
	public String withdraw(){
		
		System.out.println("进入取现前台1==="+ResultCode);
		
		String data=WithdrawMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws + FeePercent + Fee + FreeLimit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
	     
		System.out.println("进入取现前台2==="+data);
		
		System.out.println("进入取现前台2==="+RequestUtil.queryString(this.getRequest()));
		
		if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "提现:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "提现:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    
	    return "freemarker";
	 }
	
	
	/**
	 * 转账返回
	 * @return
	 */
	public String transferReturn(){
		String data=LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
		data=StringUtil.stringURLDecoderByUTF8(data);
	     if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "投标:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "投标:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    
	    return "freemarker";
	}

	/**
	 * 资金释放前台
	 * @return
	 */
	public String moneyReaeaseReturn(){
		String data=MoneymoremoreId + PlatformMoneymoremore + LoanNo + OrderNo + Amount + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;

	     if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "资金释放:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "资金释放:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    
	    return "freemarker";
		
	}
	
	/**
	 * 授权返回前台
	 * @return
	 */
	public String loanAuthorize(){
		String data=MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;

	     if(verifySignature(SignInfo, data)){
	    	 if(ResultCode.equals("88")){
	    		 webMsgInstance("0", Constants.CODE_SUCCESS, "授权:"+Message,  "", "", "", "", "");  
	    	 }else{
	    		 webMsgInstance("0", Constants.CODE_FAILED, "授权:"+Message,  "", "", "", "", "");  
	    	 }
	    	 
	     }else{
	    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
	     }
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    
	    return "freemarker";
	}
	
	/**
	 * 国付宝 返回参数
	 * @return
	 */
	public String returnRechargeGoPay(){
		 signRet();
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		 return "freemarker";
	}
	/**
	 * 国付宝 返回参数 签名验证
	 */
	private void signRet(){
		
		String buyerName = this.getRequest().getParameter("buyerName");
		String version = this.getRequest().getParameter("version");
		   String charset = this.getRequest().getParameter("charset");
		   String language = this.getRequest().getParameter("language");
		   String signType = this.getRequest().getParameter("signType");
		   String tranCode = this.getRequest().getParameter("tranCode");
		   String merchantID = this.getRequest().getParameter("merchantID");
		   String merOrderNum = this.getRequest().getParameter("merOrderNum");
		   String tranAmt = this.getRequest().getParameter("tranAmt");
		   String feeAmt = this.getRequest().getParameter("feeAmt");
		   String frontMerUrl = this.getRequest().getParameter("frontMerUrl");
		   String backgroundMerUrl = this.getRequest().getParameter("backgroundMerUrl");
		   String tranDateTime = this.getRequest().getParameter("tranDateTime");
		   String tranIP = this.getRequest().getParameter("tranIP");
		   String respCode = this.getRequest().getParameter("respCode");
		   String msgExt = this.getRequest().getParameter("msgExt");
		   String orderId = this.getRequest().getParameter("orderId");
		   String gopayOutOrderId = this.getRequest().getParameter("gopayOutOrderId");
		   String bankCode = this.getRequest().getParameter("bankCode");
		   String tranFinishTime = this.getRequest().getParameter("tranFinishTime");
		   String merRemark1 =  this.getRequest().getParameter("merRemark1");
		   String merRemark2 =  this.getRequest().getParameter("merRemark2");
		// 商户识别码
			String password = sysConfigService.findByKey("goPayRecognition")
					.getDataValue();
			String VerficationCode = password;
		   String signValueFromGopay =  this.getRequest().getParameter("signValue");
		   String gopayServerTime=this.getRequest().getParameter("gopayServerTime");
		   
		   // 组织加密明文
		   String plain = "version=[" + version + "]tranCode=[" + tranCode + "]merchantID=[" + merchantID + "]merOrderNum=[" + merOrderNum + "]tranAmt=[" + tranAmt + "]feeAmt=[" + feeAmt+ "]tranDateTime=[" + tranDateTime + "]frontMerUrl=[" + frontMerUrl + "]backgroundMerUrl=[" + backgroundMerUrl + "]orderId=[" + orderId + "]gopayOutOrderId=[" + gopayOutOrderId + "]tranIP=[" + tranIP + "]respCode=[" + respCode + "]gopayServerTime=[]VerficationCode=[" + VerficationCode + "]";
		   System.out.println("验证签名明文："+plain);
		   System.out.println("返回的signValue："+signValueFromGopay);
		   MD5 md5=new MD5();
		   String signValue = md5.md5(plain,Constants.CHAR_UTF);
		   System.out.println("验证签名signValue："+signValue);
		  
		   try {
			msgExt=URLDecoder.decode(msgExt,Constants.CHAR_UTF);
			buyerName=URLDecoder.decode(buyerName,Constants.CHAR_UTF);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		   if(signValue.equals(signValueFromGopay)){
			   //验证成功 返回成功页面
			   webMsgInstance("0", Constants.CODE_SUCCESS, "充值成功",  "", "", "", "", ""); 
			}
			else{
				//返回失败页面
				 webMsgInstance("0", Constants.CODE_FAILED, msgExt,  "", "", "", "", ""); 
				}
		
	}
	
}
