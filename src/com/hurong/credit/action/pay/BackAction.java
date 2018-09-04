package com.hurong.credit.action.pay;

import groovy.transform.Synchronized;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.emay.sms.framework.MOMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.RequestUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.financePurchase.BpMoneyManager;
import com.hurong.credit.model.pay.BpBidLoan;
import com.hurong.credit.model.pay.MadaiLoanInfoSecondaryBean;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;
import com.hurong.credit.model.pay.ResultLoanBean;
import com.hurong.credit.model.pay.ThirdPayMessage;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.financePurchase.BpMoneyManagerService;
import com.hurong.credit.service.pay.BpBidLoanService;
import com.hurong.credit.service.pay.ThirdPayMessageService;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.HibernateProxyTypeAdapter;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.RsaHelper;
import com.hurong.credit.util.TemplateConfigUtil;
import com.zhiwei.credit.model.p2p.article.Article;

public class BackAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BpCustMember bpCustMember;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	PlThirdInterfaceLogService plThirdInterfaceLogService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private BpBidLoanService bpBidLoanService;

	@Resource
	private BpMoneyManagerService bpMoneyManagerService;
	@Resource
	private ThirdPayMessageService thirdPayMessageService;

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
		    		 webMsgInstance("0", Constants.CODE_SUCCESS, "开通第三方账户:"+Message,  "", "", "", "", "");  
		    			updateCustFlag(this.getRequest());
		    	 }else{
		    		 webMsgInstance("0", Constants.CODE_FAILED, "开通第三方账户:"+Message,  "", "", "", "", "");  
		    	 }
		    	 
		     }else{
		    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		     }
		     // 记录操作日志
				plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
						"双钱注册并绑定接口", null, PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
						RealName, "", "", "");
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		    setJsonString("SUCCESS");
		    return SUCCESS;
		 }
		

		/**
		 * 充值返回前台
		 * @return
		 */
		public String recharge(){
			String data=RechargeMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + RechargeType + CardNoList + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			System.out.println("充值回调方法开始");
			System.out.println("ResultCode==="+ResultCode);
		     if(verifySignature(SignInfo, data)){
		    	 if(ResultCode.equals("88")){
		    		 BpCustMember cpCut = bpCustMemberService.getMemberUserName(RechargeMoneymoremore);
		    		 System.out.println("成功充值回调方法开始");
		    		 Map<String,Object> map=new HashMap<String,Object>();
					 map.put("investPersonId",cpCut.getId());//投资人Id
					 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					 map.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					 map.put("money",new BigDecimal(Amount));//交易金额
					 map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
					 map.put("DealInfoId",null);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
					 map.put("recordNumber",OrderNo);//交易流水号
					 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
					 obAccountDealInfoService.updateAcountInfo(map);
		    		// updateAccount(Amount,"1","2",RechargeMoneymoremore,OrderNo);
		    		 webMsgInstance("0", Constants.CODE_SUCCESS, "充值:"+Message,  "", "", "", "", "");  
		    		 System.out.println("成功充值回调方法开始");
		    	 }else{
		    		 webMsgInstance("0", Constants.CODE_FAILED, "充值:"+Message,  "", "", "", "", "");  
		    	 }
		    	 
		     }else{
		    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		     }
		     System.out.println("成功充值回调方法开始22222");
				// 记录操作日志
				plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
						"双钱充值接口", null, PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
						RealName, OrderNo, "", "");
				setJsonString("SUCCESS");
				System.out.println("ResultCode==="+ResultCode);
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		    System.out.println("成功充值回调方法开始33333");
		    return SUCCESS;
		 }

		
		/**
		 * 提现
		 * @return
		 */
		public String withdraw(){
			System.out.println("进入取现后台===");
			String data=WithdrawMoneymoremore + PlatformMoneymoremore + LoanNo + OrderNo + Amount + FeeMax + FeeWithdraws + FeePercent + Fee + FreeLimit + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			   if(verifySignature(SignInfo, data)){
		    	 if(ResultCode.equals("88")){
		    		 BpCustMember cashcpCut = bpCustMemberService.getMemberByRet(WithdrawMoneymoremore);
						//String[]  ret = payCommonService.dealToERP(cashcpCut, TransAmt,OrdId,"2");
		    		 Map<String,Object> map=new HashMap<String,Object>();
					 map.put("investPersonId",cashcpCut.getId());//投资人Id
					 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					 map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					 map.put("money",new BigDecimal(Amount));//交易金额
					 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
					 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
					 map.put("recordNumber",OrderNo);//交易流水号
					 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
					 obAccountDealInfoService.operateAcountInfo(map);
					 //obAccountDealInfoService.operateAcountInfo(cashcpCut.getId().toString(), "2", Amount, "3", "0", "0", "2", OrderNo);
		    		 webMsgInstance("0", Constants.CODE_SUCCESS, "提现:"+Message,  "", "", "", "", "");  
		    	 }else{
		    		 webMsgInstance("0", Constants.CODE_FAILED, "提现:"+Message,  "", "", "", "", "");  
		    	 }
		    	 
		     }else{
		    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		     }
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		    plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
					"双乾取现接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					bpCustMember.getTruename(), OrderNo, "", ""); 
		    setJsonString("SUCCESS");
		    return SUCCESS;
		 }
		/**
		 * 转账返回
		 * @return
		 */
		public String transferReturn(){
			String data=LoanJsonList + PlatformMoneymoremore + Action + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;
			data=StringUtil.stringURLDecoderByUTF8(data);
			System.out.println("回调函数返回json串没解码前"+LoanJsonList);
			if(verifySignature(SignInfo, data)){
		    	 if(ResultCode.equals("88")){
		    		 String jsonStr;
						try {
							jsonStr = URLDecoder.decode(LoanJsonList, "utf-8");
							System.out.println("回调函数返回json串"+jsonStr);
							saveLoan(jsonStr);
						} catch (Exception e) {
							e.printStackTrace();
						}
		    		 webMsgInstance("0", Constants.CODE_SUCCESS, "投标:"+Message,  "", "", "", "", "");  
		    	 }else{
		    		 webMsgInstance("0", Constants.CODE_FAILED, "投标:"+Message,  "", "", "", "", "");  
		    	 }
		    	 
		     }else{
		    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		     }
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		    setJsonString("SUCCESS");
		    return SUCCESS;
		}

		/**
		 * 资金释放
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
		    plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
					"资金释放接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					bpCustMember.getTruename(), OrderNo, "", ""); 
		    setJsonString("SUCCESS");
		    return SUCCESS;
			
		}
		
		/**
		 * 授权返回前台
		 * @return
		 */
		public String loanAuthorize(){
			String data=MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + Remark1 + Remark2 + Remark3 + ResultCode;

		     if(verifySignature(SignInfo, data)){
		    	 if(ResultCode.equals("88")){
		    		 updateloanAuthorize(this.getRequest());
		    		 webMsgInstance("0", Constants.CODE_SUCCESS, "授权:"+Message,  "", "", "", "", "");  
		    	 }else{
		    		 webMsgInstance("0", Constants.CODE_FAILED, "授权:"+Message,  "", "", "", "", "");  
		    	 }
		    	 
		     }else{
		    	 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		     }
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		    plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
					"授权接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					bpCustMember.getTruename(), RandomTimeStamp, "", ""); 
		    setJsonString("SUCCESS");
		    return SUCCESS;
		}

	/**
	 * 投标 关联 获取标的唯一 转账订单
	 * 
	 * @param jsonStr
	 * @return
	 */
	private String saveLoan(String jsonStr) {
		String MerPrivArr="";
		ResultLoanBean result = null;
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(
				HibernateProxyTypeAdapter.FACTORY).create();
		Type type = new TypeToken<List<ResultLoanBean>>() {
		}.getType();
		List<ResultLoanBean> retList = gson.fromJson(jsonStr, type);
		for (ResultLoanBean ret : retList) {
			// 保存交易记录到 erp
			System.out.println("回调函数调用地址开始");
			BpCustMember cpCut = bpCustMemberService.getMemberUserName(ret.getLoanOutMoneymoremore());
				ObAccountDealInfo dealinfo=obAccountDealInfoService.getByOrderNumber(ret.getOrderNo(), null, "4", "0");
				if(dealinfo!=null){
					
				}else{
					Map<String,Object> map=new HashMap<String,Object>();
					 map.put("investPersonId",cpCut.getId());//投资人Id
					 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					 map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					 map.put("money",new BigDecimal(ret.getAmount()));//交易金额
					 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
					 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
					 map.put("recordNumber",ret.getOrderNo());//交易流水号
					 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
					 obAccountDealInfoService.operateAcountInfo(map);
					//obAccountDealInfoService.operateAcountInfo(cpCut.getId().toString(), ObAccountDealInfo.T_INVEST.toString(), ret.getAmount().toString(), "3", ObSystemAccount.type0.toString(), ObAccountDealInfo.UNFREEZY.toString(), ObAccountDealInfo.DEAL_STATUS_1.toString(), ret.getOrderNo());
					// payCommonService.dealToERP(cpCut, ret.getAmount().toString(),ret.getOrderNo(),"4");
					    PlBidInfo plBidInfo=plBidInfoService.getByOrdId(ret.getOrderNo());
					  //更新标的状态
						plBidPlanService.updateStatByMoney(plBidInfo.getBidId(), new BigDecimal(ret.getAmount()));
					    System.out.println("");
					    plBidInfo.setState(PlBidInfo.TYPE1);
						plBidInfo.setOrderNo(ret.getOrderNo());
						plBidInfoService.save(plBidInfo);//修改p2p投标信息
						plBidInfoService.saveToERP(plBidInfo.getId().toString(),ret.getOrderNo());//投资人列表到erp 投标成功后保存
						MerPrivArr=ret.getAmount()+","+ret.getLoanOutMoneymoremore()+","+ret.getLoanInMoneymoremore()+","+plBidInfo.getBidId();
						
						
			  }
				plThirdInterfaceLogService.saveLog(ResultCode, Message, RequestUtil.queryString(this.getRequest()),
						"双钱投标接口", null, PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
						cpCut.getTruename(), ret.getOrderNo(), ret.getLoanNo(), MerPrivArr); //投标是记录 投标订单号 和解冻订单号
				MerPrivArr="";
			}
		
		return SUCCESS;
	}

	/**
	 * 资金流转记录
	 * 
	 * @param req
	 * @param type
	 */
	private void rechargeSave(HttpServletRequest req, String type) {

		try {
			if (req.getParameter("WithdrawMoneymoremore") != null) {
				WithdrawMoneymoremore = req
						.getParameter("WithdrawMoneymoremore");
			}
			if (req.getParameter("FeePercent") != null) {
				FeePercent = req.getParameter("FeePercent");
			}
			if (req.getParameter("Fee") != null) {
				Fee = req.getParameter("Fee");
			}
			if (req.getParameter("FreeLimit") != null) {
				FreeLimit = req.getParameter("FreeLimit");
			}
			if (req.getParameter("RechargeMoneymoremore") != null) {
				RechargeMoneymoremore = req
						.getParameter("RechargeMoneymoremore");
			}
			if (req.getParameter("PlatformMoneymoremore") != null) {
				PlatformMoneymoremore = req
						.getParameter("PlatformMoneymoremore");
			}
			if (req.getParameter("LoanNo") != null) {
				LoanNo = req.getParameter("LoanNo");
			}
			if (req.getParameter("OrderNo") != null) {
				OrderNo = req.getParameter("OrderNo");
			}
			if (req.getParameter("Amount") != null) {
				Amount = req.getParameter("Amount");
			}
			QueryFilter filter = new QueryFilter(req);
			filter.addFilter("Q_LoanNo_S_EQ", LoanNo);
			filter.addFilter("Q_OrderNo_S_EQ", OrderNo);
			List<BpMoneyManager> bpMoneyManagerList = bpMoneyManagerService
					.getAll(filter);
			if (bpMoneyManagerList == null || bpMoneyManagerList.size() == 0) {
				ResultCode = req.getParameter("ResultCode");
				SignInfo = req.getParameter("SignInfo");

				BpCustMember mem = findMamber(req, String
						.valueOf(MoneyMoreMore.PID));

				BpMoneyManager bpMoneyManager = new BpMoneyManager();
				bpMoneyManager.setStatus(ResultCode);
				bpMoneyManager.setRechargeMoneymoremore(RechargeMoneymoremore);
				bpMoneyManager.setPlatformMoneymoremore(PlatformMoneymoremore);
				bpMoneyManager.setLoanNo(LoanNo);
				bpMoneyManager.setSignInfo(SignInfo);
				bpMoneyManager.setMemberId(mem.getId());
				bpMoneyManager.setOrderNo(OrderNo);
				bpMoneyManager.setAmount(Amount);
				bpMoneyManager.setFee(Fee);
				bpMoneyManager.setFeePercent(FeePercent);
				bpMoneyManager.setFreeLimit(FreeLimit);
				bpMoneyManager.setDotime(new Date());
				bpMoneyManager.setType(type);
				bpMoneyManagerService.save(bpMoneyManager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存 与第三方对接返回的数据
	 * 
	 * @param req
	 *            请求
	 * @param type
	 *            数据类型
	 * @param msg
	 *            消息
	 * @param IName
	 *            接口名称
	 */
	private void saveThirdPayInfo(HttpServletRequest req, String type,
			String msg, String IName) {
		try {
			BpCustMember mem = findMamber(req, String
					.valueOf(MoneyMoreMore.PID));
			SignInfo = req.getParameter("SignInfo");
			// 签名
			RsaHelper rsa = RsaHelper.getInstance();
			// 存储与第三方的交互信息
			ThirdPayMessage payMessage = new ThirdPayMessage();
			payMessage.setCreateTime(new Date());// 记录时间
			if (mem != null) {
				payMessage.setMemberId(mem.getId());// 操作用户
			} else {
				payMessage.setMemberId(Long.valueOf(0));// 操作用户
			}
			payMessage.setType(type);// 操作事件
			payMessage.setJsonMessage(rsa.decryptData(SignInfo,
					AppUtil.getSysConfig().get("MM_PublicKey").toString()));// 解密后的 返回信息
			payMessage.setResultcode(req.getParameter("ResultCode"));
			payMessage.setComment1(msg);// 返回消息
			payMessage.setComment2(req.getParameter("RandomTimeStamp")); // 时间戳
			payMessage.setComment3(IName); // 接口名称
			thirdPayMessageService.save(payMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二次转账实体
	 * 
	 * @param jsonStr
	 *            LoanNo 第三方 订单号 OrderNo 平台订单号
	 * @return
	 */
	private String saveSecondLoan(String jsonStr, String LoanNo, String OrderNo) {
		try {
			jsonStr = URLDecoder.decode(jsonStr, "utf-8");
			Gson gson = new GsonBuilder().registerTypeAdapterFactory(
					HibernateProxyTypeAdapter.FACTORY).create();
			Type type = new TypeToken<List<MadaiLoanInfoSecondaryBean>>() {
			}.getType();

			List<MadaiLoanInfoSecondaryBean> retList = gson.fromJson(jsonStr,
					type);
			MadaiLoanInfoSecondaryBean madaiLoanInfoSecondaryBean = null;
			for (MadaiLoanInfoSecondaryBean ret : retList) {
				madaiLoanInfoSecondaryBean = new MadaiLoanInfoSecondaryBean();
				BpMoneyManager bpMoneyManager = new BpMoneyManager();
				bpMoneyManager.setAmount(ret.getAmount());// 二次分配金额
				bpMoneyManager.setType(BpMoneyManager.TYPE6);
				bpMoneyManager.setStatus("00");// 转账是 为 失败 等审核通过后修改为 88成功
				bpMoneyManager.setPlatformMoneymoremore(ret
						.getLoanInMoneymoremore());
				bpMoneyManager.setDescription("用途：" + ret.getTransferName()
						+ "备注：" + ret.getRemark());
				bpMoneyManager.setLoanNo(LoanNo);
				bpMoneyManager.setOrderNo(OrderNo);
				bpMoneyManager.setDotime(new Date());
				bpMoneyManagerService.save(bpMoneyManager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取 用户
	 * 
	 * @param flag
	 * @param pid
	 * @return
	 */
	private BpCustMember findMamber(HttpServletRequest req, String pid) {
		String flag = "";
		if (req.getParameter("WithdrawMoneymoremore") != null
				&& !req.getParameter("WithdrawMoneymoremore").equals("")) {
			WithdrawMoneymoremore = req.getParameter("WithdrawMoneymoremore");
			flag = WithdrawMoneymoremore;
		}
		if (req.getParameter("RechargeMoneymoremore") != null
				&& !req.getParameter("RechargeMoneymoremore").equals("")) {
			RechargeMoneymoremore = req.getParameter("RechargeMoneymoremore");
			flag = RechargeMoneymoremore;
		}
		if (req.getParameter("MoneymoremoreId") != null
				&& !req.getParameter("MoneymoremoreId").equals("")) {
			MoneymoremoreId = req.getParameter("MoneymoremoreId");
			flag = MoneymoremoreId;
		}
		BpCustMember mem = bpCustMemberService.getMemberByPIdAndFlag(flag, pid);
		return mem;
	}

	/**
	 *注册 后 更新 第三方支付 关联标识
	 * 
	 * @param req
	 */
	private void updateCustFlag(HttpServletRequest req) {

		Mobile = req.getParameter("Mobile");
		Email = req.getParameter("Email");
		RealName = req.getParameter("RealName");
		IdentificationNo = req.getParameter("IdentificationNo");
		LoanPlatformAccount = req.getParameter("LoanPlatformAccount");
		MoneymoremoreId = req.getParameter("MoneymoremoreId");
		QueryFilter filter = new QueryFilter(req);
		filter.addFilter("Q_telphone_S_EQ", Mobile);
		filter.addFilter("Q_email_S_EQ", Email);
		filter.addFilter("Q_cardcode_S_EQ", IdentificationNo);
		filter.addFilter("Q_loginname_S_EQ", LoanPlatformAccount);
		filter.addFilter("Q_truename_S_EQ", RealName);
		List<BpCustMember> custList = bpCustMemberService.getAll(filter);
		if (custList != null && custList.size() == 1) {
			BpCustMember cust = custList.get(0);
			cust.setThirdPayFlagId(MoneymoremoreId);
			
		}
	}

	private void updateloanAuthorize(HttpServletRequest req) {
		BpCustMember mem = findMamber(req, String.valueOf(MoneyMoreMore.PID));
		AuthorizeTypeOpen = req.getParameter("AuthorizeTypeOpen");
		AuthorizeTypeClose = req.getParameter("AuthorizeTypeClose");
		if (mem != null) {
			if (AuthorizeTypeOpen != null) {// 开启
				String[] str = AuthorizeTypeOpen.split(",");

				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("1")) {
						mem.setTender("1");
					}
					if (str[i].equals("2")) {
						mem.setRefund("1");
					}
					if (str[i].equals("3")) {
						mem.setSecondAudit("1");
					}
					bpCustMemberService.merge(mem);
				}
			}
			if (AuthorizeTypeClose != null) {// 关闭
				String[] str = AuthorizeTypeClose.split(",");
				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("1")) {
						mem.setTender("0");
					}
					if (str[i].equals("2")) {
						mem.setRefund("0");
					}
					if (str[i].equals("3")) {
						mem.setSecondAudit("0");
					}

				}
				bpCustMemberService.merge(mem);
			}
		}
	}
	/**
	 * 更新账户信息
	 * tranAmt 交易金额
	 * type 1表示充值，2表示取现,3收益，4投资，5还本
	 * @param state
	 *            2 成功 3 失败
	 */
	/*private void updateAccount(String tranAmt,String type ,String state,String usrCustId,String ordId) {
		 bpCustMember = bpCustMemberService.getMemberUserName(usrCustId);
		 System.out.println(bpCustMember.getCardcode()+"=="+bpCustMember.getTruename());
		 System.out.println("ordId=="+ordId);
		String[] erpArr = new String[2];
		System.out.println("state=="+state);
		 Map<String,Object> map=new HashMap<String,Object>();
		 map.put("investPersonId",bpCustMember.getId());//投资人Id
		 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
		 map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
		 map.put("money",new BigDecimal(Amount));//交易金额
		 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
		 map.put("DealInfoId",null);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
		 map.put("recordNumber",OrderNo);//交易流水号
		 map.put("fianceStatus",ObAccountDealInfo.ISAVAILABLE);//资金明细状态：0资金冻结，1交易生效，2 记录交易记录
		 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
		erpArr =obAccountDealInfoService.updateAcountInfo(bpCustMember.getId(), type, tranAmt, ObSystemAccount.type0.toString(), ordId, null, state);
		erpArr = payCommonService.updateDealToERP(bpCustMember, tranAmt,
				ordId, type, state);
		
		System.out.println(erpArr[0]+"======"+erpArr[1]+"type==="+type+"state=="+state);
	}*/

	/**
	 * 国付宝 返回参数
	 * 
	 * @return
	 */
	public String returnRechargeGoPay() {
		System.out.println("进入后台1");
		signRet();
		return SUCCESS;
	}

	/**
	 * 国付宝 返回参数 签名验证
	 */
	private void signRet() {
		System.out.println("进入后台2");
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
		String backgroundMerUrl = this.getRequest().getParameter(
				"backgroundMerUrl");
		String tranDateTime = this.getRequest().getParameter("tranDateTime");
		String tranIP = this.getRequest().getParameter("tranIP");
		String respCode = this.getRequest().getParameter("respCode");
		String msgExt = this.getRequest().getParameter("msgExt");
		String orderId = this.getRequest().getParameter("orderId");
		String gopayOutOrderId = this.getRequest().getParameter(
				"gopayOutOrderId");
		String bankCode = this.getRequest().getParameter("bankCode");
		String tranFinishTime = this.getRequest()
				.getParameter("tranFinishTime");
		String merRemark1 = this.getRequest().getParameter("merRemark1");
		String merRemark2 = this.getRequest().getParameter("merRemark2");
		// 商户识别码
		String password = sysConfigService.findByKey("goPayRecognition")
				.getDataValue();
		String VerficationCode = password;
		String signValueFromGopay = this.getRequest().getParameter("signValue");
		String gopayServerTime=this.getRequest().getParameter("gopayServerTime");

		// 组织加密明文
		String plain = "version=[" + version + "]tranCode=[" + tranCode
				+ "]merchantID=[" + merchantID + "]merOrderNum=[" + merOrderNum
				+ "]tranAmt=[" + tranAmt + "]feeAmt=[" + feeAmt
				+ "]tranDateTime=[" + tranDateTime + "]frontMerUrl=["
				+ frontMerUrl + "]backgroundMerUrl=[" + backgroundMerUrl
				+ "]orderId=[" + orderId + "]gopayOutOrderId=["
				+ gopayOutOrderId + "]tranIP=[" + tranIP + "]respCode=["
				+ respCode + "]gopayServerTime=[]VerficationCode=["
		
				+ VerficationCode + "]";
		
		  System.out.println("验证签名明文："+plain);
		   System.out.println("返回的signValue："+signValueFromGopay);
		   MD5 md5=new MD5();
		   String signValue = md5.md5(plain,Constants.CHAR_UTF);
		   System.out.println("验证签名signValue："+signValue);
		   if(signValue.equals(signValueFromGopay)){
			   System.out.println("=========");
		   }
		try {
			msgExt = URLDecoder.decode(msgExt, Constants.CHAR_UTF);
			buyerName= URLDecoder.decode(buyerName, Constants.CHAR_UTF);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] erpArr=new String[2];
		 BpCustMember mem=bpCustMemberService.get(Long.valueOf(merRemark1));
		if (signValue.equals(signValueFromGopay)) {
			// 验证成功 返回成功页面
			System.out.println("验证成功"+msgExt);
			Message = msgExt;
			// erpArr= payCommonService.updateDealToERP(mem, tranAmt, merOrderNum, "1", "2");
			Map<String,Object> map=new HashMap<String,Object>();
			 map.put("investPersonId",mem.getId());//投资人Id
			 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
			 map.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
			 map.put("money",new BigDecimal(tranAmt));//交易金额
			 map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
			 map.put("DealInfoId",null);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
			 map.put("recordNumber",merOrderNum);//交易流水号
			 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
			 erpArr=obAccountDealInfoService.updateAcountInfo(map);
			// erpArr=obAccountDealInfoService.updateAcountInfo(mem.getId(), ObAccountDealInfo.T_RECHARGE.toString(), tranAmt, ObSystemAccount.type0.toString(), merOrderNum, null, ObAccountDealInfo.DEAL_STATUS_2.toString());
		} else {
			// 返回失败页面
			Message = "交易失败:" + msgExt;
			System.out.println("验证失败"+msgExt);
			Map<String,Object> map=new HashMap<String,Object>();
			 map.put("investPersonId",mem.getId());//投资人Id
			 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
			 map.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
			 map.put("money",new BigDecimal(tranAmt));//交易金额
			 map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
			 map.put("DealInfoId",null);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
			 map.put("recordNumber",merOrderNum);//交易流水号
			 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
			 erpArr=obAccountDealInfoService.updateAcountInfo(map);
			//erpArr=obAccountDealInfoService.updateAcountInfo(mem.getId(), ObAccountDealInfo.T_RECHARGE.toString(), tranAmt, ObSystemAccount.type0.toString(), merOrderNum, null, ObAccountDealInfo.DEAL_STATUS_3.toString());
			//erpArr= payCommonService.updateDealToERP(mem, tranAmt, merOrderNum, "1", "3");
		}
		System.out.println("进入后台3"+msgExt);
		System.out.println("进入后台4"+buyerName);
		//记录操作日志
		plThirdInterfaceLogService.saveLog(respCode, msgExt, plain, "国付宝充值接口",
				null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,buyerName,"","","");

	}
}
