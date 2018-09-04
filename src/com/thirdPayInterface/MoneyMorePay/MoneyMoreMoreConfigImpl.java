package com.thirdPayInterface.MoneyMorePay;


import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.AppUtil;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.HibernateProxyTypeAdapter;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.SignUtil;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.ResultLoanBean;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;

public class MoneyMoreMoreConfigImpl implements ThirdPayTypeInterface {
	/**
	 * 双乾
	 */
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
	    try{
	    	if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)){//个人开通第三方
	    		commonResponse=MoneyMorePayInterfaceUtil.register(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//企业开通第三方
	    		commonResponse=MoneyMorePayInterfaceUtil.registerenterprise(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		commonResponse=MoneyMorePayInterfaceUtil.rechargeMoney(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//取现
	    		commonResponse=MoneyMorePayInterfaceUtil.withdrawsMoney(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
	    		commonRequst.setAction(2);//三合一接口中绑定银行卡接口类型是2
	    		commonResponse=MoneyMorePayInterfaceUtil.toloanfastpay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARD)){//取消绑定银行卡
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("取消绑定银行卡成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//散标投标
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=MoneyMorePayInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_LOAN)){//散标放款
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//散标自助还款
	    		commonRequst.setBidType(CommonRequst.HRY_PAY);
	    		commonResponse=MoneyMorePayInterfaceUtil.repayment(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HELPPAY)){//平台帮助借款人还款
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//散标提前还款
	    		commonRequst.setBidType(CommonRequst.HRY_PAY);
	    		commonResponse=MoneyMorePayInterfaceUtil.repayment(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_AUTOBID)){//散标自动投标
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//自动投标授权
	    		commonResponse=MoneyMorePayInterfaceUtil.authorize(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//自动还款授权
	    		commonResponse=MoneyMorePayInterfaceUtil.authorize(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭自动投标授权
	    		commonResponse=MoneyMorePayInterfaceUtil.authorize(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//关闭自动还款授权
	    		commonResponse=MoneyMorePayInterfaceUtil.authorize(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)){//取消投标
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACEMONEY)){//代偿还款申请
	    		commonRequst.setBidType(CommonRequst.HRY_HRB);
	    		commonResponse=MoneyMorePayInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKREPLACE)){//代偿还款审核
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//理财计划购买(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=MoneyMorePayInterfaceUtil.transferPlan(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMPLATFORM)){//理财计划购买(收款账户为平台)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=MoneyMorePayInterfaceUtil.transferPlan(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)){//理财计划起息(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANPLATF)){//理财计划起息(收款账户为平台)
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEUSER)){//理财计划派息(收款账户为用户)
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEPLATF)){//理财计划派息(收款账户为平台)
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELUSER)){//理财计划取消购买(收款账户为用户)
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELPLATF)){//理财计划取消购买(收款账户为平台)
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMAUTH)){//理财计划授权平台派息
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//挂牌交易
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=MoneyMorePayInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=MoneyMorePayInterfaceUtil.buySaleTransfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_TRUESERVICE)){//挂牌交易服务费将预收转为实收
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_FALSESERVICE)){//挂牌交易服务费将预收转实收后退费
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请 
	    		commonRequst.setBidType(CommonRequst.HRY_HRB);
	    		commonResponse=MoneyMorePayInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKHRBIN)){//互融宝转入审核
	    		commonRequst.setBidType(CommonRequst.HRY_HRB);
	    		commonResponse=MoneyMorePayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonRequst.setBidType(CommonRequst.HRY_HRB);
	    		commonResponse=MoneyMorePayInterfaceUtil.giveRed(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//派发红包
	    		commonRequst.setBidType(CommonRequst.HRY_RED);
	    		commonResponse=MoneyMorePayInterfaceUtil.giveRed(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券派发奖励
	    		commonRequst.setBidType(CommonRequst.HRY_BIDRED);
	    		commonResponse=MoneyMorePayInterfaceUtil.giveRed(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREPAREPAY)){//平台准备金代偿还款
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COMPENSATORY)){//担保代偿还款
	    		commonRequst.setBidType(CommonRequst.HRY_PAY);
	    		commonResponse=MoneyMorePayInterfaceUtil.compotory(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	    		commonResponse=MoneyMorePayInterfaceUtil.reconciliation(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//注册用户查询
	    		commonResponse=MoneyMorePayInterfaceUtil.balanceQueryMoneys(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYACCOUNT)){//业务对账
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE)){//平台收费
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
	    		commonResponse.setResponseMsg("处理成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEBID)){//更新标的状态
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("处理成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_AUTOLOAN)){//放款授权
	    		commonResponse=MoneyMorePayInterfaceUtil.authorize(commonRequst);
	    	}else {
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTBUSINESS);
		    	commonResponse.setResponseMsg("没有该业务类型");
	    	}
	    	
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	commonResponse.setResponseMsg("系统报错");
	    }
		 
		return commonResponse;
	}
	
	/**
	 * 解析双乾回调
	 */
	@Override
	public CommonResponse businessReturn(Map map,HttpServletRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		System.out.println("remark1="+map.get("Remark1"));
		ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
		ThirdPayRecord thirdPayRecord = thirdPayRecordService.getByOrderNo(map.get("Remark1").toString());
		if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_PREGISTER)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_EREGISTER)){//注册
    		String signature=(map.containsKey("AccountType")?map.get("AccountType").toString().trim():"")+
    		                 (map.get("AccountNumber").toString().trim())+
    		                 (map.get("Mobile").toString().trim())+
    		                 (map.get("Email").toString().trim())+
    		                 (map.get("RealName").toString().trim())+
    		                 (map.get("IdentificationNo").toString().trim())+
    		                 (map.get("LoanPlatformAccount").toString().trim())+
    		                 (map.get("MoneymoremoreId").toString().trim())+
    		                 (map.get("PlatformMoneymoremore").toString().trim())+
    		                 (map.get("AuthFee").toString().trim())+
    		                 (map.get("AuthState").toString().trim())+
    		                 (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
    		                 (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
    		                 (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
    		                 (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
    		                 (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
    		boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setThirdPayConfigId(map.get("MoneymoremoreId").toString());
    				commonResponse.setThirdPayConfigId0(map.get("MoneymoremoreId").toString());
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
			String signature=(map.containsKey("RechargeMoneymoremore")?map.get("RechargeMoneymoremore").toString().trim():"")+
            (map.get("PlatformMoneymoremore").toString().trim())+
            (map.get("LoanNo").toString().trim())+
            (map.get("OrderNo").toString().trim())+
            (map.get("Amount").toString().trim())+
            (map.containsKey("Fee")?map.get("Fee").toString().trim():"")+
            (map.containsKey("FeePlatform")?map.get("FeePlatform").toString().trim():"")+
            (map.containsKey("RechargeType")?map.get("RechargeType").toString().trim():"")+
            (map.containsKey("FeeType")?map.get("FeeType").toString().trim():"")+
            (map.get("CardNoList").toString().trim())+
            (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
            (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
            (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
            (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
            (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
    		
			boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_WITHDRAW)){//提现
			String signature=(map.containsKey("WithdrawMoneymoremore")?map.get("WithdrawMoneymoremore").toString().trim():"")+
            (map.get("PlatformMoneymoremore").toString().trim())+
            (map.get("LoanNo").toString().trim())+
            (map.get("OrderNo").toString().trim())+
            (map.get("Amount").toString().trim())+
            (map.containsKey("FeeMax")?map.get("FeeMax").toString().trim():"")+
            (map.containsKey("FeeWithdraws")?map.get("FeeWithdraws").toString().trim():"")+
            (map.containsKey("FeePercent")?map.get("FeePercent").toString().trim():"")+
            (map.containsKey("Fee")?map.get("Fee").toString().trim():"")+
            (map.containsKey("FreeLimit")?map.get("FreeLimit").toString().trim():"")+
            (map.containsKey("FeeRate")?map.get("FeeRate").toString().trim():"")+
            (map.containsKey("FeeSplitting")?map.get("FeeSplitting").toString().trim():"")+
            (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
            (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
            (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
            (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
            (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
			boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    				//标识双乾取现收取手续费
    				commonResponse.setFeeUser(new BigDecimal(map.get("FeeWithdraws").toString().trim()));
    			}else if(map.get("ResultCode").toString().equals("89")){//提现资金退回
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_RETURN);
    				//标识双乾取现收取手续费
    				commonResponse.setFeeUser(new BigDecimal(map.get("FeeWithdraws").toString().trim()));
    			}else if(map.get("ResultCode").toString().equals("90")){//提现待平台审核
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
    				//标识双乾取现收取手续费
    				commonResponse.setFeeUser(new BigDecimal(map.get("FeeWithdraws").toString().trim()));
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    			
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_OPENBIDAUTH)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_OPENPAYAUTH)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_AUTOLOAN)){//授权
			//授权接口
    		String signature=(map.containsKey("MoneymoremoreId")?map.get("MoneymoremoreId").toString().trim():"")+
            (map.get("PlatformMoneymoremore").toString().trim())+
            (map.containsKey("AuthorizeTypeOpen")?map.get("AuthorizeTypeOpen").toString().trim():"")+
            (map.containsKey("AuthorizeTypeClose")?map.get("AuthorizeTypeClose").toString().trim():"")+
            (map.containsKey("AuthorizeType")?map.get("AuthorizeType").toString().trim():"")+
            (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
            (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
            (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
            (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
            (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
    		boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		System.out.println("授权验签结果："+flag);
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_BID) 
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_MMUSER)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_MMPLATFORM)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_REPAYMENT)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_HANGDEAL)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_BUYDEAL)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_HRBIN)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_BEFOREPAY)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_COMPENSATORY)
				|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_REPLACEMONEY)
				){//转账接口、投标，理财计划购买  
    		String signature="";
			try {
				signature = (map.containsKey("LoanJsonList")?URLDecoder.decode(map.get("LoanJsonList").toString().trim(),"UTF-8"):"")+
				(map.get("PlatformMoneymoremore").toString().trim())+
				(map.get("Action").toString().trim())+
				(map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
				(map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
				(map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
				(map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
				(map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
			} catch (Exception e) {
				e.printStackTrace();
			}
    		boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		System.out.println("转账验签结果："+flag);
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				try {
						String jsonStr = URLDecoder.decode(map.get("LoanJsonList").toString(), "utf-8");
						Gson gson = new GsonBuilder().registerTypeAdapterFactory(
								HibernateProxyTypeAdapter.FACTORY).create();
						Type type = new TypeToken<List<ResultLoanBean>>() {
						}.getType();
						List<ResultLoanBean> retList = gson.fromJson(jsonStr, type);
						String loanNo = "";
						for (ResultLoanBean ret : retList) {
							if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_REPAYMENT)
									|| thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_BEFOREPAY)){
								loanNo="";
							}
							if(loanNo.equals("")){
								loanNo = ret.getLoanNo();
							}else{
								loanNo = loanNo+","+ret.getLoanNo();
							}
						}
						commonResponse.setLoanNo(loanNo);
					} catch (Exception e) {
						e.printStackTrace();
					}
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_LOAN)
				||thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_CANCELBID)
				||thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_CANCELDEAL)
				||thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_CHECKHRBIN)){//转账审核接口、流标、取消挂牌交易、互融宝转入shen
			//审核转账接口
    		String signature=(map.containsKey("LoanNoList")?map.get("LoanNoList").toString().trim():"")+
    		(map.containsKey("LoanNoListFail")?map.get("LoanNoListFail").toString().trim():"")+
    		(map.get("PlatformMoneymoremore").toString().trim())+
            (map.get("AuditType").toString().trim())+
            (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
            (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
            (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
            (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
            (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");
    		boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		System.out.println("授权验签结果："+flag);
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}else if(thirdPayRecord.getInterfaceType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡信息
			Map moneyMoreConfigMap=MoneyMorePayInterfaceUtil.moneyMoreProperty();  
			//得到公钥
			String privatekey =moneyMoreConfigMap.get("MM_PrivateKey").toString();
			SignUtil rsa = SignUtil.getInstance();
			String carNo=(map.containsKey("CardNo")?rsa.decryptData(map.get("CardNo").toString().trim(),privatekey):"");
			String signature=(map.containsKey("MoneymoremoreId")?map.get("MoneymoremoreId").toString().trim():"")+
            (map.get("PlatformMoneymoremore").toString().trim())+
            (map.containsKey("Action")?map.get("Action").toString().trim():"")+
            (map.containsKey("CardType")?map.get("CardType").toString().trim():"")+
            (map.containsKey("BankCode")?map.get("BankCode").toString().trim():"")+
            carNo+
            (map.containsKey("BranchBankName")?map.get("BranchBankName").toString().trim():"")+
            (map.containsKey("Province")?map.get("Province").toString().trim():"")+
            (map.containsKey("City")?map.get("City").toString().trim():"")+
            (map.containsKey("WithholdBeginDate")?map.get("WithholdBeginDate").toString().trim():"")+
            (map.containsKey("WithholdEndDate")?map.get("WithholdEndDate").toString().trim():"")+
            (map.containsKey("SingleWithholdLimit")?map.get("SingleWithholdLimit").toString().trim():"")+
            (map.containsKey("TotalWithholdLimit")?map.get("TotalWithholdLimit").toString().trim():"")+
            (map.containsKey("RandomTimeStamp")?map.get("RandomTimeStamp").toString().trim():"")+
            (map.containsKey("Remark1")?map.get("Remark1").toString().trim():"")+
            (map.containsKey("Remark2")?map.get("Remark2").toString().trim():"")+
            (map.containsKey("Remark3")?map.get("Remark3").toString().trim():"")+
            (map.containsKey("ResultCode")?map.get("ResultCode").toString().trim():"");;
    		boolean flag=MoneyMorePaySecretKeyUtil.verifySignature(signature,map.get("SignInfo").toString().trim());
    		System.out.println("绑定银行卡验签结果："+flag);
    		if(flag){
    			if(map.get("ResultCode").toString().equals("88")){
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    				commonResponse.setBankCardType(map.containsKey("CardType")?map.get("CardType").toString().trim():"");//银行卡类型
    				
    				
    				commonResponse.setBankCode(carNo);//银行卡卡号
    				commonResponse.setBankCardNumber(map.containsKey("BankCode")?map.get("BankCode").toString().trim():"");//银行代码
    				
    				commonResponse.setProvince(map.containsKey("Province")?map.get("Province").toString().trim():"");
    				commonResponse.setCity(map.containsKey("City")?map.get("City").toString().trim():"");
    				if(map.containsKey("BankCode")&&map.get("BankCode").toString().trim()!=null){
    					Map banklist=MoneyMorePayInterfaceUtil.moneyMoreBankList();
    					if(!banklist.isEmpty()){
    						commonResponse.setBankName(banklist.get(map.get("BankCode").toString().trim()).toString().trim());
    					}
    				}
    			}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
    		}
    		commonResponse.setRequestInfo(map.toString());
    		commonResponse.setRequestNo(map.get("Remark1").toString());
    		commonResponse.setResponseMsg(map.get("Message").toString());
		}
		return commonResponse;
	}
	public void gettest(){}

	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] ret=new Object[2];
		String type="NoPage";
		if(businessType.equals(ThirdPayConstants.BT_RECHAGE)){//充值
			type="NoPage";
    	}else if(businessType.equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
    		type="NoPage";
    		ret[1]=null;
    	}
		ret[0]=type;
		
		return ret;
	}

}
