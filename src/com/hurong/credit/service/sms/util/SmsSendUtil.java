package com.hurong.credit.service.sms.util;

import java.util.Map;

import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.service.sms.MessageStrategyService;
import com.hurong.extend.module.CommonSendUtil;
import com.hurong.extend.module.commonRequest.SendMsgCommonRequest;
import com.hurong.extend.module.commonResponse.SendMsgCommonResponse;
 

/**
 * 发送短信
 * 
 * @author songwenjie
 * 
 */
@SuppressWarnings("serial")
public class SmsSendUtil extends BaseAction {
	
	public Map<?,?> configMap = AppUtil.getConfigMap();;
	/**
	 * 您的验证码为${code}，请在页面中输入以完成验证，有问题请致电${phone4}【${subject}】
	 * 
	 * @param telephone  接收短信的手机号
	 * @param codeStr 验证码
	 * @return
	 */
	public String sms_Sign(String telephone, String codeStr) {
		String content = "";
		if (telephone != null && !"".equals(telephone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_Sign").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${code}", codeStr)
						.replace("${phone4}",configMap.get("phone") == null ? "" : configMap.get( "phone").toString())
						.replace( "${subject}", configMap.get("subject") == null ? "" : configMap.get( "subject").toString());
//				System.out.println("*****短信验证码发送内容=" + content);
			}
		}
		sendMsg(telephone, content);
		return "";
	}

	/**
	 * 投标成功--发送短信恭喜您，成功收到一张优惠券,激活码为： ${couponNumber}，优惠券有效期到：${endTime} ，
	 * 请登录平台激活使用，如有疑问请致电：${telphone}【${project}】
	 * @param telephone 接收短信的手机号
	 * @param code    投标金额
	 * @param projName    标的名称
	 * @return
	 */
	public String sms_bpCoupons(String telephone, String couponNumber,String endTime) {
		String content = "";
		if (telephone != null && !"".equals(telephone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_bpCoupons").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${couponNumber}", couponNumber)
						.replace("${endTime}", endTime).
						replace( "${telphone}",configMap.get("phone") == null ? "" : configMap .get("phone").toString())
						.replace("${subject}", configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//				System.out.println("*****优惠券提醒内容=" + content);
			}
		}
//		sendMsg(telephone, content);
		return "";
	}

	/**
	 * 投标成功--发送短信 您已成功投标项目:${projName}${code}元，有问题请致电${phone4}【${subject}】
	 * @param telephone   接收短信的手机号
	 * @param code    投标金额
	 * @param projName     标的名称
	 * @return
	 */
	public String sms_Bid(String telephone, String code, String projName) {
		String content = "";
		if (telephone != null && !"".equals(telephone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_Bid").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${code}", code)
					.replace("${projName}", projName)
					.replace( "${phone4}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
					.replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//				System.out.println("*****投标成功发送内容=" + content);
			}
		}
//		sendMsg(telephone, content);
		return "";
	}

	/**
	 * sms_activityMessage
	 * 恭喜您，成功收到一张 ${parValue}优惠券，优惠券有效期到：${endTime} ，请登 录平台查收使用，
	 * 感谢您参与“ ${activity}”活动，如有疑问请致电：${telphone}【${project}】
	 * @return
	 */
	public  String sms_activityMessage(String  telephone,String  parValue,String endTime,String  activity){
		String content = "";
		if (telephone != null && !"".equals(telephone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_activityMessage").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${parValue}", parValue)
					.replace("${endTime}", endTime)
					.replace("${activity}", activity)
					.replace( "${telphone}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
					.replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//				System.out.println("*****投标成功发送内容=" + content);
			}
		}
//		sendMsg(telephone, content);
		return "";
	}
	
	/**
    * 回款提醒
    * @param telephone
    * @param codeStr
    * @return
    */
	public String sms_paymentRemind(String telphone,String truename,String RepaymentMoney,String loanInterestMoney,String projName){
	   String content  = "";
	   if (telphone != null && !"".equals(telphone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_paymentRemind").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${name}", truename)
		              .replace("${investInterest}", RepaymentMoney)
		              .replace("${investPrincipal}", loanInterestMoney)
		              .replace("${projName}",projName)
		              .replace("${phone4}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
					  .replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//				System.out.println("*****回款成功发送内容=" + content);
			}
	   }
//	   sendMsg(telphone,content);
	   return "";
	}
	
	/**
    * 充值提醒
    * 尊敬的客户${name}您好，您已成功充值${code}元。有问题请致电${phone4}【${subject}】 
    * @param telphone
    * @param truename
    * @param money
    * @return
    */
	public String sms_recharge(String telphone,String truename,String money){
	   String content  = "";
	   if (telphone != null && !"".equals(telphone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_recharge").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${name}", truename)
			              .replace("${code}", money)
			              .replace("${phone4}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
						  .replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//					System.out.println("*****充值成功发送内容=" + content);
			}
	   }
//	   sendMsg(telphone,content);
	   return "";
	}
	
	/**
    * 提现提醒
    * @param telphone
    * @param truename
    * @param money
    * @return
    */
	public String sms_withdrawals(String telphone,String truename,String money){
	   String content  = "";
	   if (telphone != null && !"".equals(telphone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_withdrawals").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${name}", truename)
			              .replace("${code}", money)
			              .replace("${phone4}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
						  .replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//						System.out.println("*****充值成功发送内容=" + content);
			}
	   }
//	   sendMsg(telphone,content);
	   return "";
	}

	/**
    * 债权交易成功转让人短信提醒
    * @param telphone
    * @param truename
    * @param bidName
    * @return
    */
	public String sms_sale(String telphone,String truename,String bidName){
	   String content  = "";
	   if (telphone != null && !"".equals(telphone)) {
			// 获得短信发送模板
			String temp = configMap.get("sms_sale").toString();
			if (temp != null && !"".equals(temp)) {
				content = temp.replace("${name}", truename)
			              .replace("${bidName}", bidName)
			              .replace("${phone4}",configMap.get("phone") == null ? "" : configMap.get("phone").toString())
						  .replace("${subject}",configMap.get("subject") == null ? "" : configMap.get("subject").toString());
//						System.out.println("*****债权交易成功转让人短信提醒=" + content);
			}
	   }
//	   sendMsg(telphone,content);
	   return "";
	}
	
	/**
	 * 发送短信方法
	 * 
	 * @param phone
	 *            接收短信的手机号
	 * @param content
	 *            短信内容
	 * @return
	 */
	public String sendMsg(String phone, String content) {
		String relt="";
		if (configMap.get("sms_benname").toString().equals("MDSmsManagerService")) {
			SendMsgCommonRequest sn = new SendMsgCommonRequest();
			sn.setThirdPayInterfaceType("ManDaoServiceImpl");
			sn.setThirdPayMethodType("sendMsg");
			sn.setPhone(phone);
			sn.setMsg(content);
			SendMsgCommonResponse response=(SendMsgCommonResponse) CommonSendUtil.sendMsg(sn);
			System.out.println(response.getCode());
			System.out.println(response.getResponseMessage());
			System.out.println(response.getResponseMessage());
			relt = response.getCode();
		}else {
			MessageStrategyService messageStrategy = (MessageStrategyService) AppUtil.getBean(configMap.get("sms_benname").toString());
			relt = messageStrategy.sendMsg(phone, content);
		}
		return relt;
	}
}