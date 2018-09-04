package com.hurong.credit.sms;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.hurong.core.util.AppUtil;

public class HXSmsManagerServerImp {
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	public static final double Balance=0.1;// 每条短信金额
    //以下参数为服务器URL,以及发到服务器的参数，不用修改
   private static String strRegUrl = "http://www.stongnet.com/sdkhttp/reg.aspx";
   private static  String strBalanceUrl = "http://www.stongnet.com/sdkhttp/getbalance.aspx";
   private static String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
   private static String strSchSmsUrl = "http://www.stongnet.com/sdkhttp/sendschsms.aspx";
   private static String strStatusUrl = "http://www.stongnet.com/sdkhttp/getmtreport.aspx";
   private static String strUpPwdUrl = "http://www.stongnet.com/sdkhttp/uptpwd.aspx";
    //短信url参数
   private static String strSmsParam ;
   private static String strBalanceParam=""; //余额 参数
   
   private static String strSourceAdd = "";    //子通道号，可为空（预留参数一般为空）
  
   
    
	public String HXSmsManagerServerImp() throws UnsupportedEncodingException{
	//以下为所需的参数，测试时请修改,中文请先转为16进制再发送
	String strReg = configMap.get("smsAccountID").toString();   //注册号（由华兴软通提供）
    String strPwd =configMap.get("smsPassword").toString();                 //密码（由华兴软通提供）
   
    String strTim = HXSmsManagerServer.paraTo16("2012-2-16 12:00:00"); //定时发送时间
    String strPhone = "13391750223,18701657767";//手机号码，多个手机号用半角逗号分开，最多1000个
    String strContent = HXSmsManagerServer.paraTo16("httpD试emo测ja va"+ (new Date()).toString());       //短信内容
    
    String strUname = HXSmsManagerServer.paraTo16("华兴"); //用户名，不可为空
    String strMobile = "13391750223";            //手机号，不可为空
    String strRegPhone = "01065688262";             //座机，不可为空
    String strFax = "01065685318";               //传真，不可为空
    String strEmail = "hxrt@stongnet.com";       //电子邮件，不可为空
    String strPostcode = "100080";               //邮编，不可为空
    String strCompany = HXSmsManagerServer.paraTo16("华兴软通");    //公司名称，不可为空
    String strAddress = HXSmsManagerServer.paraTo16("天阳ja");//公司地址，不可为空
    
    String strNewPwd = "BBBBBBBB";

   
    String strRegParam = "reg=" + strReg + "&pwd=" + strPwd + "&uname=" + strUname + "&mobile=" + strMobile + "&phone=" + strRegPhone + "&fax=" + strFax + 
                         "&email=" + strEmail + "&postcode=" + strPostcode + 
                         "&company=" + strCompany + "&address=" + strAddress;
 
   
    String strSchSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&tim=" + strTim + "&phone=" + strPhone + "&content=" + strContent;;
    String strStatusParam = "reg=" + strReg + "&pwd=" + strPwd;
    String strUpPwdParam = "reg=" + strReg + "&pwd=" + strPwd + "&newpwd=" + strNewPwd;
    //返回值
    String strRes ;
    
    //以下为HTTP接口主要方法，测试时请打开对应注释进行测试
    //注册
    //strRes = HXSmsManagerServer.postSend(strRegUrl, strRegParam);
    
    //查询余额
    strRes = HXSmsManagerServer.postSend(strBalanceUrl, strBalanceParam);
    
    //发送短信
    strRes = HXSmsManagerServer.postSend(strSmsUrl, strSmsParam);
    
    //定时短信
    //strRes = HXSmsManagerServer.postSend(strSchSmsUrl, strSchSmsParam);
    
    //状态报告
    //strRes = HXSmsManagerServer.postSend(strStatusUrl, strStatusParam);
    
    //修改密码
    //strRes = HXSmsManagerServer.postSend(strUpPwdUrl, strUpPwdParam);
    
    
   return strRes;
    
}
	
	/**
	 * 查询余额方法
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getBalance() throws UnsupportedEncodingException{
		String relt="";
		setBalanceParam();
		relt= HXSmsManagerServer.postSend(strBalanceUrl, strBalanceParam);
		return relt;
	}
	/**
	 * 发送短信
	 * @param phone 手机号
	 * @param content 内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendMsm(String phone,String content) throws UnsupportedEncodingException{
		String relt="";
		setSmsParam(phone,content);
		relt= HXSmsManagerServer.postSend(strSmsUrl, strSmsParam);
		return relt;
	}
	/**
	 * 设置发送短信参数url
	 * @param strPhone
	 * @param content
	 * @throws UnsupportedEncodingException 
	 */
	private static void setSmsParam(String strPhone,String content) throws UnsupportedEncodingException{
	    String strContent = HXSmsManagerServer.paraTo16(content);       //短信内容
	    String strReg=setRegAndPwd()[0];
	    String strPwd=setRegAndPwd()[1];
	 strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + strPhone + "&content=" + strContent;;
	}
	/**
	 * 设置查询余额参数url
	 * @throws UnsupportedEncodingException 
	 */
	private static void setBalanceParam() throws UnsupportedEncodingException{
	    String strReg=setRegAndPwd()[0];
	    String strPwd=setRegAndPwd()[1];
	    strBalanceParam = "reg=" + strReg + "&pwd=" + strPwd;
	}
	/**
	 * 获取账号密码 公用
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String[] setRegAndPwd(){
		String[] str=new String[2];
		str[0]=AppUtil.getSysConfig().get("smsAccountID").toString();
		str[1]=AppUtil.getSysConfig().get("smsPassword").toString();
		return str;
	}
	
}

