package com.thirdPayInterface.AllinPay;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.allinpay.ets.client.PaymentResult;
import com.hurong.core.util.AppUtil;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;

public class AllinPayConfigImpl implements ThirdPayTypeInterface {
	/**
	 * 通联  
	 */
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
	    try{
	    	if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		commonResponse=AllinPayInterfaceUtil.recharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_APPQRECHARGE)){//手机端充值(银联接口)
	    		commonResponse=AllinPayInterfaceUtil.rechargeUnionPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//提现  
	    		//commonResponse=AllinPayInterfaceUtil.withdraws(commonRequst);//不用后台审核直接提现
	    		//后台审核直接返回申请成功
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
	    		commonResponse.setDealState("7");
		    	commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//充值 提现 查询 
	    		if(commonRequst.getQueryType().equals("RECHARGE_RECORD")){
	    			//commonResponse=AllinPayInterfaceUtil.queryRechargeUnionPay(commonRequst);
	    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			    	commonResponse.setResponseMsg("操作成功");
	    		}else{
	    			//commonResponse=AllinPayInterfaceUtil.queryResult(commonRequst);
		    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			    	commonResponse.setResponseMsg("操作成功");
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}/*else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//提现 查询 
	    		//commonResponse=AllinPayInterfaceUtil.queryResult(commonRequst);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    	commonResponse.setResponseMsg("操作成功");

	    	}*/else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){
	    		commonResponse.setDealState("7");
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    	commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){
	    		commonResponse.setThirdPayConfigId(commonRequst.getCardNumber());
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    	commonResponse.setResponseMsg("操作成功");
	    	}else{
	    		commonResponse.setCommonRequst(commonRequst);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    	commonResponse.setResponseMsg("操作成功");
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	commonResponse.setResponseMsg("系统报错");
	    }
		 
		return commonResponse;
	}
	
	/**
	 * 通联回调
	 */
	@Override
	public CommonResponse businessReturn(Map map,HttpServletRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		//得到操作的是那种业务类型
		ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
		//判断是 pc端充值，还是手机端银联充值
		if(map.containsKey("reqReserved")){//手机端银联充值
			ThirdPayRecord thirdPayRecord = thirdPayRecordService.getByOrderNo(map.get("orderId").toString());
			if(thirdPayRecord!=null){
				//验证签名。
				boolean verifyResult = AllinPayInterfaceUtil.unionpayVerify(map,request);
				System.out.println("验签结果="+verifyResult);
				if(verifyResult){
					if(map.get("respMsg").toString().equals("success")){//交易成功
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
				    	commonResponse.setResponseMsg("充值成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				    	commonResponse.setResponseMsg("充值失败");
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
			    	commonResponse.setResponseMsg("签名验证失败");
				}
				commonResponse.setLoanNo(map.get("queryId").toString());
				commonResponse.setRequestNo(map.get("orderId").toString());
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTBUSINESS);
		    	commonResponse.setResponseMsg("没有此业务类型");
			}
		}else{
			ThirdPayRecord thirdPayRecord = thirdPayRecordService.getByOrderNo(map.get("orderNo").toString());
			if(thirdPayRecord!=null){
				if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
					//构造订单结果对象，验证签名。
					PaymentResult paymentResult = new PaymentResult();
					paymentResult.setMerchantId(map.get("merchantId").toString());
					paymentResult.setVersion(map.get("version").toString());
					paymentResult.setLanguage(map.get("language").toString());
					paymentResult.setSignType(map.get("signType").toString());
					paymentResult.setPayType(map.get("payType").toString());
					paymentResult.setIssuerId(map.get("issuerId").toString());
					paymentResult.setPaymentOrderId(map.get("paymentOrderId").toString());
					paymentResult.setOrderNo(map.get("orderNo").toString());
					paymentResult.setOrderDatetime(map.get("orderDatetime").toString());
					paymentResult.setOrderAmount(map.get("orderAmount").toString());
					paymentResult.setPayDatetime(map.get("payDatetime").toString());
					paymentResult.setPayAmount(map.get("payAmount").toString());
					paymentResult.setExt1(map.get("ext1").toString());
					paymentResult.setPayResult(map.get("payResult").toString());
					paymentResult.setErrorCode(map.get("errorCode").toString());
					paymentResult.setReturnDatetime(map.get("returnDatetime").toString());
					paymentResult.setSignMsg(map.get("signMsg").toString());
					//验证签名。
					boolean verifyResult = AllinPayInterfaceUtil.allinVerify(paymentResult);
					//验签成功，还需要判断订单状态，为"1"表示支付成功。
					if(verifyResult){
						if(map.get("payResult").equals("1")){
							commonResponse.setRequestNo(map.get("orderNo").toString());
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					    	commonResponse.setResponseMsg("充值成功");
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					    	commonResponse.setResponseMsg("充值失败");
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				    	commonResponse.setResponseMsg("签名验证失败");
					}
					commonResponse.setRequestInfo(map.toString());
				}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_WITHDRAW)){//取现
					
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTBUSINESS);
		    	commonResponse.setResponseMsg("没有此业务类型");
			}
		}
		return commonResponse;
	}
	public void gettest(){}

	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] ret=new Object[2];
		String type="NoPage";
		if(businessType.equals(ThirdPayConstants.BT_RECHAGE)){//充值
			type="1";
    	}else if(businessType.equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
    		type="Page";
    		ret[1]="all";
    	}
		ret[0]=type;
		
		return ret;
	}

}
