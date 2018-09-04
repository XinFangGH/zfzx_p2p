package com.hurong.credit.action.pay.AllinPay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.aipg.common.AipgRsp;
import com.aipg.common.XSUtil;
import com.allinpay.ets.client.PaymentResult;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.thirdInterface.AllinPayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.TemplateConfigUtil;


public class AllinPayBackAction extends BaseAction {
	
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

	/**
	 * 充值返回
	 * @return
	 */
	public String recharge(){
		//验证签名。
		boolean verifyResult = this.allinVerify();
		//验签成功，还需要判断订单状态，为"1"表示支付成功。
		boolean paySuccess = verifyResult && payResult.equals("1");
		if(paySuccess){
			System.out.println("充值成功------"+orderAmount);
			
			//计算充值金额。
			BigDecimal payMoney = new BigDecimal(payAmount);
			BigDecimal money = new BigDecimal("100");
			BigDecimal rechargeMoney =payMoney.divide(money);
			
	   		BpCustMember cpCut = bpCustMemberService.get(Long.valueOf(ext1));
	   		System.out.println("成功充值回调方法开始");
	   		Map<String,Object> map=new HashMap<String,Object>();
			map.put("investPersonId",cpCut.getId());//投资人Id
			map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
			map.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
		    map.put("money",rechargeMoney);//交易金额
			map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
			map.put("DealInfoId",null);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
			map.put("recordNumber",orderNo);//交易流水号
			map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
			obAccountDealInfoService.updateAcountInfo(map);
			
			webMsgInstance("0", Constants.CODE_SUCCESS, "充值成功",  "", "", "", "", "");  
   	 
		}else{
			System.out.println("验证失败");
			 webMsgInstance("0", Constants.CODE_FAILED, "验证失败",  "", "", "", "", ""); 
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 提现交易结果通知http://blc.zhengbang.com/allinPay/withdrawsAllinPayBack.do
	 * @return
	 */  
	public String withdraws(){
		System.out.println("通联交易结果查询---------");
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer msgSb = new StringBuffer();
		BufferedReader reader=null;
		try {
			reader = request.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int perchar=0;
		try {
			while ((perchar = reader.read()) != -1) {
				msgSb.append((char) perchar);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("请求报文:"+msgSb);
		String notify_no = dealRet(msgSb.toString());
		if(!notify_no.equals("")){
			QueryFilter filter =  new QueryFilter();
			filter.addFilter("Q_msg_S_EQ", notify_no);
			List<ObAccountDealInfo> threeDeal = obAccountDealInfoService.getAll(filter);
			System.out.println("threeDeal="+threeDeal);
			for(ObAccountDealInfo deal :threeDeal){
				//获取第三方环境路径
				String URL11https= AppUtil.getSysConfig().get("DF_payUrl").toString();
				boolean isfront=false;//是否发送至前置机（由前置机进行签名）
				String[] result = new String[3];
				result = allinPayService.queryResult(notify_no,isfront,URL11https);
				if(result[0].equals("0000")){
					
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("investPersonId", deal.getInvestPersonId());// 投资人Id（必填）
					map.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					map.put("money", new BigDecimal(deal.getPayMoney().toString()));// 交易金额
					map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					map.put("recordNumber", deal.getRecordNumber());// 交易流水号 （必填）
					map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);// 资金交易状态：1等待支付，2支付成功，3
					obAccountDealInfoService.updateAcountInfo(map);
				}else if (result[0].equals("2000")||result[0].equals("2001")||result[0].equals("2003")
						||result[0].equals("2005")||result[0].equals("2007")||result[0].equals("2008")){
					System.out.println("第三方正在处理中---------");
				}else{
					System.out.println("最终交易失败-----------"+result[2]);
					System.out.println("最终交易失败-----------"+result[1]);
					Map<String,Object> map1=new HashMap<String,Object>();
					map1.put("investPersonId", deal.getInvestPersonId());// 投资人Id（必填）
					map1.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
					map1.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
					map1.put("money", new BigDecimal(deal.getPayMoney().toString()));// 交易金额
					map1.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					map1.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					map1.put("recordNumber", deal.getRecordNumber());// 交易流水号 （必填）
					map1.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);// 资金交易状态：1等待支付，2支付成功，3
					map1.put("msg","第三方批次号="+result[2]+"原因="+result[1]);
					obAccountDealInfoService.updateAcountInfo(map1);
				}
			}
			if(threeDeal.size()==0){
				//查询还在第三方处理中的交易
				List<ObAccountDealInfo> threeDeals = obAccountDealInfoService.queryThreeDealInfo(null, "2", "5");
				for(ObAccountDealInfo deal :threeDeals){
					if(deal.getMsg()!=null&&!deal.getMsg().equals("")){
						//获取第三方环境路径
						String URL11https= AppUtil.getSysConfig().get("DF_payUrl").toString();
						boolean isfront=false;//是否发送至前置机（由前置机进行签名）
						String[] result = new String[3];
						result = allinPayService.queryResult(deal.getMsg(),isfront,URL11https);
						if(result[0].equals("0000")){
							Map<String,Object> map=new HashMap<String,Object>();
							map.put("investPersonId", deal.getInvestPersonId());// 投资人Id（必填）
							map.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
							map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
							map.put("money", new BigDecimal(deal.getPayMoney().toString()));// 交易金额
							map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							map.put("recordNumber", deal.getRecordNumber());// 交易流水号 （必填）
							map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);// 资金交易状态：1等待支付，2支付成功，3
							obAccountDealInfoService.updateAcountInfo(map);
						}
						else if (result[0].equals("2000")||result[0].equals("2001")||result[0].equals("2003")
								||result[0].equals("2005")||result[0].equals("2007")||result[0].equals("2008")){
							System.out.println("第三方正在处理中---------");
						}else{
							System.out.println("最终交易失败-----------"+result[2]);
							System.out.println("最终交易失败-----------"+result[1]);
							Map<String,Object> map1=new HashMap<String,Object>();
							map1.put("investPersonId", deal.getInvestPersonId());// 投资人Id（必填）
							map1.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
							map1.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
							map1.put("money", new BigDecimal(deal.getPayMoney().toString()));// 交易金额
							map1.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							map1.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							map1.put("recordNumber", deal.getRecordNumber());// 交易流水号 （必填）
							map1.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);// 资金交易状态：1等待支付，2支付成功，3
							map1.put("msg","第三方批次号="+result[2]+"原因="+result[1]);
							obAccountDealInfoService.updateAcountInfo(map1);
						}
					}
				}
			}
		}
		return SUCCESS;
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
	
	public String dealRet(String retXml){
		String notify_sn="";
		String trxcode = null;
		//交易码
		if (retXml.indexOf("<TRX_CODE>") != -1)
		{
			int end = retXml.indexOf("</TRX_CODE>");
			int begin = end - 6;
			if (begin >= 0) trxcode = retXml.substring(begin, end);
		}
		if(trxcode.equals("200003")){
			if (retXml.indexOf("<NOTIFY_SN>") != -1)
			{
				int start = retXml.indexOf("<NOTIFY_SN>");
				int end = retXml.indexOf("</NOTIFY_SN>");
				int begin = start + 11;
				if (begin >= 0) notify_sn = retXml.substring(begin, end);
			}
		}
		System.out.println("notify_sn="+notify_sn);
		System.out.println("trxcode="+trxcode);
		return notify_sn;
	}
}