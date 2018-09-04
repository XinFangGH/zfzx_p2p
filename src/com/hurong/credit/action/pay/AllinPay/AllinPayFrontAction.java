package com.hurong.credit.action.pay.AllinPay;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import com.allinpay.ets.client.PaymentResult;
import com.hurong.core.Constants;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.thirdInterface.AllinPayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.TemplateConfigUtil;



public class AllinPayFrontAction extends BaseAction {
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private AllinPayService allinPayService;
	//接收通联返回的支付结果,参数解释参照 AlliPay类
	public String merchantId;
	public String version;
	public String language;
	public String signType;
	public String payType;
	public String issuerId;
	public String paymentOrderId;//通联订单号
	public String payerName;
	public String orderNo;//平台订单号
	public String orderDatetime;
	public String orderAmount;//金额
	public String payDatetime;//支付完成时间
	public String payAmount;//订单实际支付金额
	public String ext1;//平台保存的用户id
	public String ext2;
	public String payResult;//处理结果
	public String errorCode;
	public String returnDatetime;//结果返回时间
	public String signMsg;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getPaymentOrderId() {
		return paymentOrderId;
	}

	public void setPaymentOrderId(String paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayDatetime() {
		return payDatetime;
	}

	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getReturnDatetime() {
		return returnDatetime;
	}

	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String recharge(){
		try{
			//验证签名。
			boolean verifyResult = this.allinVerify();
			//验签成功，还需要判断订单状态，为"1"表示支付成功。
			boolean paySuccess = verifyResult && payResult.equals("1");
			if(paySuccess){
				webMsgInstance("0", Constants.CODE_SUCCESS, "充值成功",  "", "", "", "", "");  
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, "充值失败",  "", "", "", "", ""); 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
	    return "freemarker";
	 }
	public boolean allinVerify()
	{
		//构造订单结果对象，验证签名。
		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setMerchantId(merchantId);
		paymentResult.setVersion(version);
		paymentResult.setLanguage(language);
		paymentResult.setSignType(signType);
		paymentResult.setPayType(payType);
		paymentResult.setIssuerId(issuerId);
		paymentResult.setPaymentOrderId(paymentOrderId);
		paymentResult.setOrderNo(orderNo);
		paymentResult.setOrderDatetime(orderDatetime);
		paymentResult.setOrderAmount(orderAmount);
		paymentResult.setPayDatetime(payDatetime);
		paymentResult.setPayAmount(payAmount);
		paymentResult.setExt1(ext1);
		paymentResult.setPayResult(payResult);
		paymentResult.setErrorCode(errorCode);
		paymentResult.setReturnDatetime(returnDatetime);
		// signMsg为服务器端返回的签名值。
		paymentResult.setSignMsg(signMsg);
		//signType为"1"时，必须设置证书路径。
		String configPath="";
		try {
			 configPath = java.net.URLDecoder.decode(this.getClass().getResource("/").getPath(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println("configPath="+configPath);
		paymentResult.setCertPath(configPath+"/com/hurong/credit/action/pay/AllinPay/TLCert-test.cer"); 
		//验证签名：返回true代表验签成功；否则验签失败。
		boolean verifyResult = paymentResult.verify();
		return verifyResult;
	}
}