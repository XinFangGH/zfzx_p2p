package com.hurong.credit.sms;

/**
 * 发送短信接口
 * */
public interface MessageStrategy {

	/**
	 * 发送短信方法
	 * @param message  短信内容 
	 * */
	public String sendMsg(String phone,String code,String content);
}
