package com.hurong.credit.service.sms;

import java.util.Map;

import com.hurong.credit.model.user.BpCustMember;

 

/**
 * 发送短信  站内信 邮件 service
 * @author XiRuiJie
 *
 */
public interface SendMesService {
	
	/**
	 * 发送验证码
	 * @param bp      会员实体类
	 * @param code    验证码
	 * @param ifsms   是否发送短信 true 是 false 否
	 * @param ifmail  是否发送邮件  true 是 false 否
	 * @param ifnew   是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_Sign(BpCustMember bp,String code,Boolean ifsms,Boolean ifmail,Boolean ifnew);
	/**
	 * 成功投标
	 * @param bp      会员实体类
	 * @param money   投标金额
	 * @param projName标的名称
	 * @param ifsms   是否发送短信 true 是 false 否
	 * @param ifmail  是否发送邮件  true 是 false 否
	 * @param ifnew   是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_Bid(BpCustMember bp,String money,String projName,Boolean ifsms,Boolean ifmail,Boolean ifnew);
	/**
	 * 成功投标
	 * 恭喜您，成功收到一张优惠券,激活码为： ${couponNumber}，优惠券有效期到：${endTime} ，请登录平台激活使用，如有疑问请致电：${phone}【${project}】
	 * @param bp            会员实体类
	 * @param money         投标金额
	 * @param couponNumber  优惠券邀请码
	 * @param endTime       优惠券有效期
	 * @param ifsms         是否发送短信 true 是 false 否
	 * @param ifmail        是否发送邮件  true 是 false 否
	 * @param ifnew         是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_bpCoupons(BpCustMember bp,String couponNumber,String  endTime,Boolean ifsms,Boolean ifmail,Boolean ifnew);
	/**
	 * 通用发送 短信、邮件、站内信方法
	 * @param map
	 * @return
	 */
	public String sendSmsEmailMessage(Map<String, String> map);

}
