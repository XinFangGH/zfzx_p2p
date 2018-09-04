package com.thirdPayInterface.YeePay;

import java.util.Map;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.CommonRequst;

public class YeepayConfigImpl implements ThirdPayTypeInterface {
	private static Log logger=LogFactory.getLog(YeePayInterfaceUtil.class);
	/**
	 * 易宝请求接口
	 */
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
	    try{
	    	if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)){//个人开通第三方
	    		commonResponse=YeePayInterfaceUtil.register(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//企业开通第三方
	    		commonResponse=YeePayInterfaceUtil.enterRegister(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值  
	    		commonResponse=YeePayInterfaceUtil.recharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_APPQRECHARGE)){//app充值  
	    		commonResponse=YeePayInterfaceUtil.recharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//取现
	    		commonResponse=YeePayInterfaceUtil.toWithdraw(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
	    		commonResponse=YeePayInterfaceUtil.toBindBankCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARD)){//取消绑定银行卡
	    		commonResponse=YeePayInterfaceUtil.cancelBindBankCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
	    		commonResponse=YeePayInterfaceUtil.toResetMobile(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//散标投标
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=YeePayInterfaceUtil.fiancialTransfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_LOAN)){//散标放款
	    		commonResponse=YeePayInterfaceUtil.loan(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//散标自助还款
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=YeePayInterfaceUtil.toRepaymentByLoaner2(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HELPPAY)){//平台帮助借款人还款
	    		commonResponse=YeePayInterfaceUtil.toRepaymentDirectlyByLoaner(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//散标提前还款
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
//	    		commonResponse=YeePayInterfaceUtil.toRepaymentByLoaner(commonRequst);
	    		commonResponse=YeePayInterfaceUtil.toRepaymentByLoaner2(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_AUTOBID)){//散标自动投标
	    		commonResponse=YeePayInterfaceUtil.autoTransfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//自动投标授权
	    		commonResponse=YeePayInterfaceUtil.autoTransferAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BUSSINESSTYPE_CLOSEAUTOBID)){//取消自动投标授权
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//自动还款授权
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=YeePayInterfaceUtil.autoRepaymentAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭自动投标授权
	    		commonResponse=YeePayInterfaceUtil.cancelTransferAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//关闭自动还款授权
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		commonResponse=YeePayInterfaceUtil.cancelRepaymentAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)){//取消投标
	    		commonResponse=YeePayInterfaceUtil.revocationTransfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACEMONEY)){//代偿还款 申请 
	    		commonResponse=YeePayInterfaceUtil.ommontransferIntrface(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKREPLACE)){//代偿还款审核 
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//理财计划购买(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=YeePayInterfaceUtil.fiancialTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMPLATFORM)){//理财计划购买(收款账户为平台)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);	
	    		commonResponse=YeePayInterfaceUtil.ommontransferIntrface(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)){//理财计划起息(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
//	    		commonResponse=YeePayInterfaceUtil.loan(commonRequst);  //1.0接口
	    		commonResponse=YeePayInterfaceUtil.useAllaudit(commonRequst);//2.0接口
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANPLATF)){//理财计划起息(收款账户为平台)
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEUSER)){//理财计划派息(收款账户为用户)
	    		commonResponse=YeePayInterfaceUtil.toRepaymentDirectlyByLoaner(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMGIVEPLATF)){//理财计划派息(收款账户为平台)
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELUSER)){//理财计划取消购买(收款账户为用户)
	    		commonResponse=YeePayInterfaceUtil.revocationTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELPLATF)){//理财计划取消购买(收款账户为平台)
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMAUTH)){//理财计划授权平台派息
	    		commonResponse=YeePayInterfaceUtil.autoRepaymentAuthorization(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//挂牌交易
//	    		commonResponse=YeePayInterfaceUtil.ommontransferIntrface(commonRequst);//1.0接口
	    		commonResponse=YeePayInterfaceUtil.ommontransferIntrface2(commonRequst);//2.0接口
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		//commonResponse=YeePayInterfaceUtil.obligationDeal(commonRequst);//1.0接口   
	    		commonResponse=YeePayInterfaceUtil.obligationDeal2(commonRequst);//2.0接口   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_TRUESERVICE)){//挂牌交易服务费将预收转为实收
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_FALSESERVICE)){//挂牌交易服务费将预收转实收后退费
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请
	    		commonResponse=YeePayInterfaceUtil.ommontransferIntrface(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKHRBIN)){//互融宝转入审核
	    		commonResponse=YeePayInterfaceUtil.checkCommonTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//派发红包
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//派发优惠券奖励
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREPAREPAY)){//平台准备金代偿还款
	    		commonResponse=YeePayInterfaceUtil.platformTransfer(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	    		commonResponse=YeePayInterfaceUtil.singleQuery(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//注册用户查询
	    		commonResponse=YeePayInterfaceUtil.registerQuery(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYACCOUNT)){//注册用户查询
	    		commonResponse=YeePayInterfaceUtil.reconciliation(commonRequst);   
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE)){//平台收费
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("处理成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEBID)){//更改标的状态
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("更改标的状态成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COMPENSATORY)){//担保公司代偿还款
	    		commonRequst.setBidType(CommonRequst.HRY_BID);
	    		//commonResponse=YeePayInterfaceUtil.guanteeRepayMent(commonRequst); //1.0接口  
	    		commonResponse=YeePayInterfaceUtil.guanteeRepayMent2(commonRequst);  //2.0接口 
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMBACKMONEY)){//UD计划理财专户为投资人还款
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=YeePayInterfaceUtil.toRepaymentByLoaner(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMADVANCEUSER)){//UD计划理财专户为投资人还款
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonResponse=YeePayInterfaceUtil.toRepaymentByLoaner(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_USEALLAUDIT)){//通用转账授权审核
	    		commonResponse=YeePayInterfaceUtil.useAllaudit(commonRequst);
	    	}else{
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
	 * 页面回调地址
	 */
	@Override
	public CommonResponse businessReturn(Map maps,HttpServletRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		
		String req=maps.get("resp").toString();
		logger.info("前台页面回调数据的xml报文："+req);
		String sign=maps.get("sign").toString();
		logger.info("前台页面回调数据的签名："+sign);
		YeePayReponse response=null;
		try {
			response = YeePaySecretKeyUtil.JAXBunmarshal(req,YeePayReponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Boolean isSign=YeePaySecretKeyUtil.verifySign(req, sign);
		logger.info("前台页面回调数据的签名验证结果："+isSign);
		if(isSign){
			if(response.getCode().equals("1")){
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			}
			commonResponse.setResponseMsg(response.getDescription());//返回说明
		}else{
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
			commonResponse.setResponseMsg("签名验证失败");//返回说明
		}
		commonResponse.setRequestNo(response.getRequestNo());//流水号
		commonResponse.setBussinessType(response.getService());//业务类型
		commonResponse.setRequestInfo(req+""+sign);//返回数据包
		return commonResponse;
	}
	
	/**
	 * 新增方法用来检测接口调用前的页面是自有页面还是第三方提供页面
	 */
	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] ret=new Object[2];
		String type="noPage";
		if(businessType.equals(ThirdPayConstants.BT_RECHAGE)){//充值
			type="noPage";
    	}else if(businessType.equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
    		type="noPage";
    	}
		ret[0]=type;
		ret[1]=null;
		return ret;
	}

}
