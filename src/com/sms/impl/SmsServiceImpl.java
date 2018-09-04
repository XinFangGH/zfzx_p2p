package com.sms.impl;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.dmkj.webservice.WebServiceUtil;
import com.hurong.core.Constants;
import com.hurong.core.util.StringUtil;
import com.hurong.core.util.XmlUtil;
import com.hurong.credit.service.thirdPay.fuiou.util.HttpClientHelper;
import com.hurong.credit.util.MD5;
import com.sms.PropertiesUtil;
import com.sms.SmsService;

public class SmsServiceImpl implements SmsService {

	public String[] webServicesMeth(String content, String phoneList) {
		String[] ret = new String[2];
		String ECCode = com.dmkj.webservice.PropertiesUtil
				.getProperty("companyCode");
		String AdminNum = com.dmkj.webservice.PropertiesUtil
				.getProperty("userName");
		String AdminPass = com.dmkj.webservice.PropertiesUtil
				.getProperty("passWord");
		String SmsType = "0";
		WebServiceUtil aa = new WebServiceUtil();
		aa.sendSms(ECCode, AdminNum, AdminPass, phoneList, content, SmsType);
		return ret;
	}

	public String[] urlMeth(String content, String phoneList) {

		String[] ret = new String[2];
		String url ="";
		String ECCode ="" ;
		String AdminNum = "";
		String AdminPass ="" ;
		
		 url = PropertiesUtil.getProperty("url");
		 ECCode = PropertiesUtil.getProperty("userid");
		 AdminNum = PropertiesUtil.getProperty("account");
		 AdminPass = PropertiesUtil.getProperty("password");
		/*if (getMobileType(phoneList).equals("1")) {
			 url = PropertiesUtil.getProperty("url");
			 ECCode = PropertiesUtil.getProperty("userid");
			 AdminNum = PropertiesUtil.getProperty("account");
			 AdminPass = PropertiesUtil.getProperty("password");

		}else if(getMobileType(phoneList).equals("2")){
			 url = PropertiesUtil.getProperty("y_url");
			 ECCode = PropertiesUtil.getProperty("y_userid");
			 AdminNum = PropertiesUtil.getProperty("y_account");
			 AdminPass = PropertiesUtil.getProperty("y_password");
		}else if(getMobileType(phoneList).equals("3")){
			 url = PropertiesUtil.getProperty("d_url");
			 ECCode = PropertiesUtil.getProperty("d_userid");
			 AdminNum = PropertiesUtil.getProperty("d_account");
			 AdminPass = PropertiesUtil.getProperty("d_password");
		}
		*/
		String sp_code = "";
		String task_id = "";
		MD5 md5 = new MD5();
		try {
			// content="您的验证码为："+content+"，若不是您的操作，请忽略本短信。";
			System.out.println("=============" + content);
			String urlencContent = URLEncoder.encode(content, "utf-8");
			String sign = md5.getMD5Info((urlencContent + AdminPass));

			String params = "content=" + urlencContent + "&destMobiles="
					+ phoneList + "&sign=" + sign + "&cust_code=" + AdminNum
					+ "&sp_code=" + sp_code + "&task_id=" + task_id;

			/*
			 * URL myurl = new URL(url); URLConnection urlc =
			 * myurl.openConnection(); urlc.setReadTimeout(1000 * 30);
			 * urlc.setDoOutput(true); urlc.setDoInput(true);
			 * urlc.setAllowUserInteraction(false);
			 * 
			 * DataOutputStream server = new
			 * DataOutputStream(urlc.getOutputStream());
			 * System.out.println("发送数据=" + postData);
			 * server.write(postData.getBytes("utf-8")); server.close();
			 * 
			 * BufferedReader in = new BufferedReader(new InputStreamReader(
			 * urlc.getInputStream(), "utf-8")); String resXml = "", s = "";
			 * while ((s = in.readLine()) != null) resXml = resXml + s + "\r\n";
			 * in.close(); System.out.println("接收数据=" + resXml);
			 */

			String xml = HttpClientHelper.doHttp(url, HttpClientHelper.POST,
					"utf-8", params, "60000");
			ret = str2Arr(StringUtil.stringURLDecoderByUTF8(xml));
			System.out.println(ret);
		} catch (Exception e) {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "发送失败";
		}
		return ret;
	}

	@Override
	public String[] sendSMS(String content, String phoneList, String type) {
		String[] ret = new String[2];
		try {

			if (type.equals("0")) {
				ret = webServicesMeth(content, phoneList);
			} else if (type.equals("1")) {
				ret = urlMeth(content, phoneList);
			}
		} catch (Exception e) {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "发送失败";
		}
		System.out.println("发送短信返回==" + ret[1]);
		return ret;
	}

	/***
	 * 判断号码是联通，移动，电信中的哪个, 在使用本方法前，请先验证号码的合法性 规则：前三位为130-133 联通
	 * ；前三位为135-139或前四位为1340-1348 移动； 其它的应该为电信
	 * 
	 * @param mobile要判断的号码
	 * @return 返回相应类型：1代表联通；2代表移动；3代表电信
	 */
	public  String getMobileType(String mobile) {
		if (mobile.startsWith("0") || mobile.startsWith("+860")) {
			mobile = mobile.substring(mobile.indexOf("0") + 1, mobile.length());
		}
		List chinaUnicom = Arrays.asList(new String[] { "130", "131", "132",
				"133" });
		List chinaMobile1 = Arrays.asList(new String[] { "135", "136", "137",
				"138", "139", "158", "159" });
		List chinaMobile2 = Arrays.asList(new String[] { "1340", "1341",
				"1342", "1343", "1344", "1345", "1346", "1347", "1348" });
		boolean bolChinaUnicom = (chinaUnicom.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile1 = (chinaMobile1
				.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile2 = (chinaMobile2
				.contains(mobile.substring(0, 4)));
		if (bolChinaUnicom)
			return "1";// 联通
		if (bolChinaMobile1 || bolChinaMobile2)
			return "2"; // 移动
		return "3"; // 其他为电信
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @return
	 */
	private String[] xml2Arr(String xml) {
		String[] ret = new String[2];
		// 解析xml
		if (xml != null) {
			Document rootDoc = XmlUtil.stringToDocument(xml);
			Element element = rootDoc.getRootElement();
			Iterator<Element> it = element.elements().iterator();
			while (it.hasNext()) {
				Element el = it.next();
				if (el.getName().equals("returnstatus")) {
					ret[0] = el.getText();
				}
				if (el.getName().equals("message")) {
					ret[1] = el.getText();
				}
			}
		} else {
			ret[0] = "00";
			ret[1] = "连接出错";
		}
		return ret;
	}

	/**
	 * 解析字符串
	 * 
	 * @param String
	 * @return
	 */
	private String[] str2Arr(String str) {
		String[] ret = new String[2];
		// 解析xml
		if (str != null) {
			System.out.println(str);

			if (str.substring(0, 5).equals("ERROR")) {
				ret[0] = Constants.CODE_FAILED;
				ret[1] = str;
			} else if (str.substring(0, 7).equals("SUCCESS")) {
				ret[0] = Constants.CODE_SUCCESS;
				ret[1] = "发送成功";
			}

		} else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "连接出错";
		}
		return ret;
	}
}
