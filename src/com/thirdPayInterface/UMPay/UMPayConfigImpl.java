package com.thirdPayInterface.UMPay;


import java.util.List;
import java.util.Map;


import com.hurong.core.util.AppUtil;
import com.thirdPayInteface.UMPay.UMPay;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
public class UMPayConfigImpl implements ThirdPayTypeInterface{
	protected static Log logger=LogFactory.getLog(UMPayInterfaceUtil.class);
	public static volatile Map<String,String> requestValue=new ConcurrentHashMap<String,String>();
	/**
	 * 联动优势
	 */
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
	    try{
	    	if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)){//个人开通第三方
	    		commonResponse=UMPayInterfaceUtil.regiest(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//企业开通第三方
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    	commonResponse.setResponseMsg("企业用户不支持线上开通第三方账户，请联系平台客服！");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户充值
	    			commonRequst.setBankType(UMPay.BANK_PERSON);
	    			commonResponse=UMPayInterfaceUtil.recharge(commonRequst);
	    		}else{
	    			commonRequst.setBankType(UMPay.BANK_ENTERPRISE);
	    			commonResponse=UMPayInterfaceUtil.enterPriseRecharge(commonRequst);//企业用户充值
	    		}
	    		//commonResponse=UMPayInterfaceUtil.rechargeMoney(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//取现
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户提现
	    			commonResponse=UMPayInterfaceUtil.toWithdraw(commonRequst);
	    		}else{
	    			commonResponse=UMPayInterfaceUtil.EnterpriseWithdraw(commonRequst);//企业用户提现
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
	    		commonResponse=UMPayInterfaceUtil.toBindBankCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARD)){//取消绑定银行卡
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACECARD)){//更换银行卡
	    		commonResponse=UMPayInterfaceUtil.toChangeBankCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//散标投标
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户投标
	    			commonResponse=UMPayInterfaceUtil.transferInterface(commonRequst);
	    		}else{
	    			commonResponse=UMPayInterfaceUtil.TransferWithAgreementInterfate(commonRequst);//企业用户投标
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_LOAN)){//散标放款
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//散标自助还款
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人还款
	    			commonResponse=UMPayInterfaceUtil.transferInterface_repay(commonRequst);
	    		}else{//企业借款人还款
	    			commonResponse=UMPayInterfaceUtil.TransferWithAgreementInterfate(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RETURNMONEY)){//返款接口
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=UMPayInterfaceUtil.NoPasswordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEBID)){//更改标的状态
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=UMPayInterfaceUtil.UpdateBidAccount(commonRequst);
	    		//commonResponse=UMPayInterfaceUtil.repayment(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HELPPAY)){//平台帮助借款人还款
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//散标提前还款
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人还款
	    			commonResponse=UMPayInterfaceUtil.transferInterface_repay(commonRequst);
	    		}else{//企业借款人还款
	    			commonResponse=UMPayInterfaceUtil.TransferWithAgreementInterfate(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_AUTOBID)){//散标自动投标
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//自动投标授权
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户
	    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			    	commonResponse.setResponseMsg("开通自动投标授权成功");
	    		}else{//个人用户
	    			commonResponse=UMPayInterfaceUtil.autoAuthorization(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//自动还款授权
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户
	    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    			commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
			    	commonResponse.setResponseMsg("开启自动还款授权成功");
	    		}else{//个人用户
	    			commonResponse=UMPayInterfaceUtil.autoAuthorization(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭自动投标授权
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户
	    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    			commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
			    	commonResponse.setResponseMsg("关闭自动投标授权成功");
	    		}else{//个人用户
	    			commonResponse=UMPayInterfaceUtil.cancelAuthorization(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//关闭自动还款授权
	    		commonResponse=UMPayInterfaceUtil.cancelAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)){//取消投标
    			commonResponse=UMPayInterfaceUtil.NoPasswordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACEMONEY)){//代偿还款申请
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){
	    			commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface1(commonRequst);
	    		}else{
	    			commonResponse=UMPayInterfaceUtil.normalTransferInterface(commonRequst);;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKREPLACE)){//代偿还款审核
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//理财计划购买(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户投标
	    			commonResponse=UMPayInterfaceUtil.transferInterface(commonRequst);
	    		}else{//企业用户投标
	    			commonResponse=UMPayInterfaceUtil.TransferWithAgreementInterfate(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	   	    		commonRequst.setBidType(commonRequst.HRY_BID);
	   	    		commonResponse=UMPayInterfaceUtil.accountQulideQuery(commonRequst);
	   	    }else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMPLATFORM)){//理财计划购买(收款账户为平台)
	   	    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    	commonResponse.setResponseMsg("尚未对接此业务类型");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)){//理财计划起息(收款账户为用户)
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANPLATF)){//理财计划起息(收款账户为平台)
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEUSER)){//理财计划派息(收款账户为用户)
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEPLATF)){//理财计划派息(收款账户为平台)
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELUSER)){//理财计划取消购买(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=UMPayInterfaceUtil.NoPasswordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELPLATF)){//理财计划取消购买(收款账户为平台)
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMAUTH)){//理财计划授权平台派息
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//挂牌交易
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户
	    			commonResponse=UMPayInterfaceUtil.normalTransferInterface(commonRequst);
	    		}else{//企业
	    			commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface1(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==0){//个人用户
	    			commonResponse=UMPayInterfaceUtil.transferInterface(commonRequst);
	    		}else{
	    			commonResponse=UMPayInterfaceUtil.TransferWithAgreementInterfate(commonRequst);//企业用户
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
	    		commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_TRUESERVICE)){//挂牌交易服务费将预收转为实收
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_FALSESERVICE)){//挂牌交易服务费将预收转实收后退费
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请 
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){
	    			commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface1(commonRequst);
	    		}else{
	    			commonResponse=UMPayInterfaceUtil.normalTransferInterface(commonRequst);;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKHRBIN)){//互融宝转入审核
	    		//commonResponse=UMPayInterfaceUtil.transferaudit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//派发红包
	    		commonResponse=UMPayInterfaceUtil.normalNOPassWordTransferInterface(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券派发奖励
	    		//commonResponse=UMPayInterfaceUtil.giveRed(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREPAREPAY)){//平台准备金代偿还款
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	    		//commonResponse=UMPayInterfaceUtil.reconciliation(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//注册用户查询
	    		commonResponse=UMPayInterfaceUtil.queryCustmerInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYACCOUNT)){//业务对账
	    		
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE)){//平台收费
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("处理成功");
	    	}else {
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
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
	 * 解析回调
	 */
	@Override
	public CommonResponse businessReturn(Map map,HttpServletRequest request) {
		ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
		CommonResponse commonResponse = new CommonResponse();
    	if(!map.isEmpty()){
    		commonResponse.setRequestInfo(map.toString());//保存datapackage
    		commonResponse.setThirdPayConfig("UMPayConfig");
    		Boolean isSign=false;
    		//联动优势验证签名方法
    		logger.info("联动优势页面回调函数通知签名验证结果isSign="+isSign);
    		if(true){
    			String service=request.getParameter("service");
    			if(service!=null&&!"".equals(service)){//其他业务处理方法
    				if(service.equals(UMPay.NOTIFY_RECHARGE)){//充值交易回调通知
    					logger.info("联动优势服务器端回调函数通知调用充值业务操作方法开始");
    					if(!requestValue.containsKey(map.get("order_id"))){
    						requestValue.put(map.get("order_id").toString(),map.get("order_id").toString());
    					}
    					synchronized(requestValue.get(map.get("order_id"))){
    			          //TODO 添加处理业务方法
    						commonResponse.setRequestNo(map.get("order_id").toString());
    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    							commonResponse.setResponseMsg("充值成功");
    						}else{
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    							commonResponse.setResponseMsg("充值失败");
    						}
    						requestValue.remove(map.get("order_id"));
    					}
    				}else if(service.equals(UMPay.NOTIFY_WITHDRAW)){//取现回调通知（第一次申请结果通知）
    					logger.info("联动优势服务器端回调函数通知调用取现业务操作方法开始");
    					// TODO Auto-generated method stub
    					if(!requestValue.containsKey(map.get("order_id"))){
    						requestValue.put(map.get("order_id").toString(),map.get("order_id").toString());
    					}
    					synchronized(requestValue.get(map.get("order_id"))){
    			          //TODO 添加处理业务方法
    						commonResponse.setRequestNo(map.get("order_id").toString());
    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    							commonResponse.setResponseMsg("取现申请成功");
    						}else{
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    							commonResponse.setResponseMsg("取现失败");
    						}
    						requestValue.remove(map.get("order_id"));
    					}
    				}else if(service.equals(UMPay.NOTIFY_BINDCARD)){//绑卡回调通知||更换银行卡
    					logger.info("联动优势服务器端回调函数通知调用绑卡业务操作方法开始");
    					if(!requestValue.containsKey(map.get("order_id"))){
    						requestValue.put(map.get("order_id").toString(),map.get("order_id").toString());
    					}
    					synchronized(requestValue.get(map.get("order_id"))){
    			          //TODO 添加处理业务方法
    						commonResponse.setRequestNo(map.get("order_id").toString());
    						commonResponse.setResponseMsg(map.get("ret_msg").toString());
    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
								commonResponse.setResponseMsg("绑卡成功");
    							
    						}else{
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    							commonResponse.setResponseMsg("绑卡失败");
    						}
    						requestValue.remove(map.get("order_id"));
    					}
    				}else if(service.equals(UMPay.NOTIFY_BINDAGERRMENT)){//开通无密交易
    					//无密交易授权回调通知
    					logger.info("联动优势服务器端回调函数通知页面回调函数通知调用无密交易授权业务操作方法开始");
    					if(!requestValue.containsKey(map.get("user_bind_agreement_list"))){
    						requestValue.put(map.get("user_bind_agreement_list")+map.get("user_id").toString(),map.get("user_bind_agreement_list")+map.get("user_id").toString());
    					}
    					commonResponse.setThirdPayConfigId(map.get("user_id").toString());
    					synchronized(requestValue.get(map.get("user_bind_agreement_list")+map.get("user_id").toString())){
    						String bind_agreement=map.get("user_bind_agreement_list").toString().trim();
    						String[] bind=bind_agreement.split(",");
    						if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_INVST)){//无密投资协议
    							List<ThirdPayRecord> recordList = thirdPayRecordService.getByIdAndType(map.get("user_id").toString(), ThirdPayConstants.BT_OPENBIDAUTH);
        						if(recordList != null && recordList.size()>0){
        							ThirdPayRecord tr = recordList.get(0);
        							commonResponse.setRequestNo(tr.getRecordNumber());
        						}
        						//授权用户的第三方账号
        						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
        							commonResponse.setResponseMsg("无密投标授权成功");
        						}else{
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
        							commonResponse.setResponseMsg("无密投标授权失败，失败原因："+map.get("ret_msg"));
        						}
    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_REPAYMENT)){//无密还款协议
    							List<ThirdPayRecord> recordList = thirdPayRecordService.getByIdAndType(map.get("user_id").toString(), ThirdPayConstants.BT_OPENPAYAUTH);
        						if(recordList != null && recordList.size()>0){
        							ThirdPayRecord tr = recordList.get(0);
        							commonResponse.setRequestNo(tr.getRecordNumber());
        						}
        						//授权用户的第三方账号
        						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
        							commonResponse.setResponseMsg("无密还款授权成功");
        						}else{
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
        							commonResponse.setResponseMsg("无密还款授权失败，失败原因："+map.get("ret_msg"));
        						}
    						}
    						requestValue.remove(map.get("user_bind_agreement_list")+map.get("user_id").toString());
    					}
    				
    				}else if(service.equals(UMPay.NOTIFY_CANCELAGERRMENT)){//关闭无密交易
    					//关闭无密交易授权回调通知
    					logger.info("联动优势服务器端回调函数通知页面回调函数通知调用关闭无密交易授权业务操作方法开始");
    					if(!requestValue.containsKey(map.get("user_unbind_agreement_list"))){
    						requestValue.put(map.get("user_unbind_agreement_list")+map.get("user_id").toString(),map.get("user_unbind_agreement_list")+map.get("user_id").toString());
    					}
    					commonResponse.setThirdPayConfigId(map.get("user_id").toString());
    					synchronized(requestValue.get(map.get("user_unbind_agreement_list")+map.get("user_id").toString())){
    						String bind_agreement=map.get("user_unbind_agreement_list").toString().trim();
    						String[] bind=bind_agreement.split(",");
    						if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_INVST)){//无密投资协议
    							List<ThirdPayRecord> recordList = thirdPayRecordService.getByIdAndType(map.get("user_id").toString(), ThirdPayConstants.BT_CLOSEBIDAUTH);
        						if(recordList != null && recordList.size()>0){
        							ThirdPayRecord tr = recordList.get(0);
        							commonResponse.setRequestNo(tr.getRecordNumber());
        						}
        						//授权用户的第三方账号
        						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
        							commonResponse.setResponseMsg("关闭无密投资授权成功");
        						}else{
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
        							commonResponse.setResponseMsg("关闭无密投资授权失败，失败原因："+map.get("ret_msg"));
        						}
    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_REPAYMENT)){//无密还款协议
    							List<ThirdPayRecord> recordList = thirdPayRecordService.getByIdAndType(map.get("user_id").toString(), ThirdPayConstants.BT_CLOSEPAYAUTH);
        						if(recordList != null && recordList.size()>0){
        							ThirdPayRecord tr = recordList.get(0);
        							commonResponse.setRequestNo(tr.getRecordNumber());
        						}
        						//授权用户的第三方账号
        						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
        							commonResponse.setResponseMsg("关闭无密还款授权成功");
        						}else{
        							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
        							commonResponse.setResponseMsg("关闭无密还款授权失败，失败原因："+map.get("ret_msg"));
        						}
    						}
    						requestValue.remove(map.get("user_unbind_agreement_list")+map.get("user_id").toString());
    					}
    				
    				}else if(service.equals(UMPay.NOTIFY_BIDTRANSFER)){//标类的转账回调通知
    					logger.info("联动优势服务器端回调函数通知调用标类转账操作方法开始");
    					if(!requestValue.containsKey(map.get("order_id"))){
    						requestValue.put(map.get("order_id").toString(),map.get("order_id").toString());
    					}
    					synchronized(requestValue.get(map.get("order_id"))){
    			          //TODO 添加处理业务方法
    						commonResponse.setRequestNo(map.get("order_id").toString());
    						//commonResponse.setBussinessType(service);
    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    							commonResponse.setResponseMsg("标的账户转账成功");
    						}else{
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    							commonResponse.setResponseMsg("标的账户转账失败");
    						}
    						requestValue.remove(map.get("order_id"));
    						logger.debug("order_id="+map.get("order_id")+","+commonResponse.getResponseMsg());
    					}
    				}else if(service.equals(UMPay.NOTIFY_NOMALTRANSFER)){//普通有密码的标类转账
    					logger.info("联动优势服务器端回调函数通知调用普通转账操作方法开始");
    					if(!requestValue.containsKey(map.get("order_id"))){
    						requestValue.put(map.get("order_id").toString(),map.get("order_id").toString());
    					}
    					synchronized(requestValue.get(map.get("order_id"))){
    			          //TODO 添加处理业务方法
    						commonResponse.setRequestNo(map.get("order_id").toString());
    						//commonResponse.setBussinessType(service);
    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
    							commonResponse.setResponseMsg("普通转账成功");
    						}else{
    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    							commonResponse.setResponseMsg("普通转账失败");
    						}
    						requestValue.remove(map.get("order_id"));
    					}
    				}
    			}
    		}else{
    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				commonResponse.setResponseMsg("签名验证失败");
	    		logger.info("联动优势页面回调函数通知出错，原因：联动优势页面回调函数通知签名验证没有通过，请等待一段时间后查询个人中心资金交易明细，或者联系管理员");
    		}
    	}else{
    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTRECIVEPARAMETER);
			commonResponse.setResponseMsg("没有收到任何回调通知");
    		logger.info("联动优势页面回调函数通知出错，原因：没有收到任何交易参数，请等待一段时间后查询个人中心资金交易明细，或者联系管理员");
    	}
		return commonResponse;
	}
	public void gettest(){}

	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] ret=new Object[2];
		
		String type="Page";
		if(businessType.equals(ThirdPayConstants.BT_RECHAGE)){//充值
			type="Page";
    	}else if(businessType.equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
    		type="Page";
    		ret[1]=null;
    	}
		ret[0]=type;
		
		return ret;
	}

}
