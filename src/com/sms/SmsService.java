package com.sms;
/**
 * 短信 发送 服务
 * @author yuanzc
 * 2014-05-09
 *
 */
public interface SmsService {
    /**
     * 调用webServices 接口 发送短信
     * @param content 内容
     * @param phoneList 号码列表  逗号分割 eg: 13466761447,18810472461
     * @param type 类型 0 webservice 接口方式发送  1 url 地址发送
     * 
     * @return 返回String 数组 0 code 1 msg
     */
	public String[] sendSMS(String content,String phoneList,String type);
	
}
