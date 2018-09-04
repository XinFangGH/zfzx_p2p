package com.hurong.credit.sms.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.sms.MessageStrategy;

/*
 * 彩云金融短信发送方法---麦讯通
 * */
public class CYJRMsgStrategyImpl implements MessageStrategy {

	private static Log logger = LogFactory.getLog(CYJRMsgStrategyImpl.class);
	//code未使用
	private String phone;
	@Resource
	PlThirdInterfaceLogService plThirdInterfaceLogService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	private String projStr;
	private String url;
	private String userID;
	private String accountID;
	private String password;
	
	private String code;
	@Override
	public String sendMsg(String phone,String code,String content) {
		this.phone = phone;
		this.code = code;
		url = configMap.get("smsUrl").toString();
		userID = configMap.get("smsUserID").toString();
		accountID = configMap.get("smsAccountID").toString();
		password = configMap.get("smsPassword").toString();
		return directSend(url,parseDTO(phone,code,content));
	}
	//获取参数
	public DirectSendDTO parseDTO(String phone,String code,String content){
		DirectSendDTO directSendDTO = new DirectSendDTO();
		// 彩云金融 ，这代码 赶紧改了 哎....... 
		/*directSendDTO.setUserID("962999");
		directSendDTO.setAccountID("admin");
		directSendDTO.setPassword("EWSMF8");*/
		//国创的
		directSendDTO.setUserID(userID);
		directSendDTO.setAccountID(accountID);
		directSendDTO.setPassword(password);
		
		directSendDTO.setPhones(phone);
		directSendDTO.setContent(content);
		directSendDTO.setSendType("1");
		return directSendDTO;
	}
	
	
	// 调用接口DirectGetStockDetails
	public String directGetStockDetails(String url) {
		return excute(url);
	}

	// 调用接口DirectFetchSMS.
	public String directFetchSMS(String url) {
		return excute(url);
	}

	// 调用接口DirectSend,没有参数为中文的url时可调用如下方法.
	public String excute(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "GBK");
			if (str.contains("GBK")) {
				str = str.replaceAll("GBK", "utf-8");
			}
			int beginPoint = str.indexOf("<RetCode>");
			int endPoint = str.indexOf("</RetCode>");
			String result = "RetCode=";
			return result + str.substring(beginPoint + 9, endPoint);
		} catch (HttpException e) {
			return "1";
		} catch (IOException e) {
			return "2";
		}

		finally {
			getMethod.releaseConnection();
		}
	}

	// 调用接口DirectSend,对于参数为中文的调用采用以下方法,为防止中文参数为乱码.
	public String directSend(String url, DirectSendDTO directSendDTO) {
		// String response = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		// String responseValue;
		PostMethod getMethod = new UTF8PostMethod(url);
		NameValuePair[] data = {
				new NameValuePair("UserID", directSendDTO.getUserID()),
				new NameValuePair("Account", directSendDTO.getAccountID()),
				new NameValuePair("Password", directSendDTO.getPassword()),
				new NameValuePair("Phones", directSendDTO.getPhones()),
				new NameValuePair("SendType", directSendDTO.getSendType()),
				new NameValuePair("SendTime", directSendDTO.getSendTime()),
				new NameValuePair("PostFixNumber", directSendDTO
						.getPostFixNumber()),
				new NameValuePair("Content", directSendDTO.getContent()) };
		getMethod.setRequestBody(data);
		getMethod.addRequestHeader("Connection", "close");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			//addLog("8888","发送成功","调用短信接口,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones());
			Long sendTime = new Date().getTime();
			logger.info("[彩云金融短信验证]发送时间："+sdf.format(new Date())+" ,手机号："+phone+" ,验证码："+code+" ,短信发送.....!");
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "GBK");
			if (str.contains("GBK")) {
				str = str.replaceAll("GBK", "utf-8");
			}
			DomTest dom=new DomTest();
			String retCodeValue="";
			try {
				retCodeValue=dom.readXMLString4TagName(str, "RetCode");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Long endTime = new Date().getTime();
			logger.info("[彩云金融短信验证]返回时间："+sdf.format(new Date())+" ,手机号："+phone+" ,验证码："+code+" ,短信发送成功!"+retCodeValue);
			if(null==retCodeValue){
				addLog("0000","发送失败","短信接口调用失败,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones(),"时间："+(endTime-sendTime)+"ms");
			}else if("Sucess".equals(retCodeValue)){
				addLog("8888","发送成功","短信接口调用成功,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones(),"时间："+(endTime-sendTime)+"ms");
			}else{
				addLog("0000","发送失败","短信接口调用失败,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones(),"时间："+(endTime-sendTime)+"ms");
			}
			return retCodeValue;
		} catch (HttpException e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			logger.info("[彩云金融短信验证]时间："+sdf.format(new Date())+" ,手机号："+phone+" ,验证码："+code+" ,短信发送失败!");
			addLog("0000","发送失败","短信接口调用失败,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones(),"时间：0ms");
			return "1";
		} catch (IOException e) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			logger.info("[彩云金融短信验证]时间："+sdf.format(new Date())+" ,手机号："+phone+" ,验证码："+code+" ,短信发送失败!");
			addLog("0000","发送失败","短信接口调用失败,发送短信："+directSendDTO.getContent(),"接收手机："+directSendDTO.getPhones(),"时间：0ms");
			return "2";
		} finally {
			getMethod.releaseConnection();
		}

	}

	public static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			return "UTF-8";
		}
	}
	
	public void addLog(String respCode,String msgExt,String plain,String buyerName,String timeDifference){
		//respCode：0000失败，8888成功
		//msgExt:失败，成功
		//plain：发送信息
		//buyerName:使用者
		
		plThirdInterfaceLogService.saveLog(respCode, msgExt, plain, "商信通短信接口",
				null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE2, PlThirdInterfaceLog.TYPENAME2,buyerName,timeDifference,"","");
	}
}
