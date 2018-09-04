package com.hurong.credit.service.thirdInterface.impl;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.XmlUtil;
import com.hurong.credit.model.thirdInterface.Detail;
import com.hurong.credit.model.thirdInterface.Repayment;
import com.hurong.credit.model.thirdInterface.Transfer;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.thirdInterface.YeePay;
import com.hurong.credit.model.thirdInterface.YeePayReponse;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;
import com.hurong.credit.util.YeePayConfig.SignUtil;

public class YeePayServiceImpl implements YeePayService{
	
	protected Log logger=LogFactory.getLog(YeePayServiceImpl.class);
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	
	/**2014-07-15
	 * 得到reg与sign
	 * @param yeepay
	 * @return
	 */
	public Map<String, String> getReqSign(YeePay yeepay,String service){
		Map<String, String> params = new HashMap<String, String>();
		StringWriter w = new StringWriter();
		JAXB.marshal(yeepay, w);
		String s = w.toString().trim();
		s = s.replaceAll(Pattern.quote("\n"), "");
		params.put("req", s);
		if(service!=null&&!"".equals(service)){
			params.put("service", service);
		}
		String password=configMap.get("thirdPay_yeepay_phapassword").toString();
		String filePath=configMap.get("thirdPay_yeepay_phaKey").toString();
		filePath=AppUtil.getAppAbsolutePath()+filePath;
		String url = filePath.replace("\\", "/");
		String sign=SignUtil.sign(s, url, password);
		params.put("sign", sign);
		return params;
	}
	/**2014-07-14
	 * 易宝公共数据获取方法 商户编号
	 * @param yeepayBasePath
	 * @return
	 */
	public YeePay generatePublicData(YeePay yeepay,String BasePath){
		yeepay.setPlatformNo(configMap.get("thirdPay_yeepay_platformNo").toString().trim());
		if(BasePath!=null&&!"".equals(BasePath)){
			yeepay.setNotifyUrl(BasePath+configMap.get("thirdPay_yeepay_notifyUrl").toString());//服务器通知 URL
		}
		return yeepay;
	}
	
	
	/**
	 * 将String 类型的xml  转换为实体对象T
	 * @param <T>
	 * @param outStr
	 * @param type
	 * @return
	 */
	public <T> T JAXBunmarshal(String outStr,Class<T> type) throws Exception{
		InputStream is = new ByteArrayInputStream(outStr.getBytes("UTF-8"));
		return (T) JAXB.unmarshal(is, YeePayReponse.class);
	}
	
	@Override
	public Object[] webfrontBackValue(HttpServletRequest request) {
		Object[] ret=new Object[2];
		System.out.println("开始执行页面回调");
		try{
			// TODO Auto-generated method stub
			String req=request.getParameter("resp");
			logger.info("前台页面回调数据的xml报文："+req);
			System.out.println("reqsign==="+req);
			String sign=request.getParameter("sign");
			logger.info("前台页面回调数据的签名："+sign);
			System.out.println("req==="+req);
			YeePayReponse response=JAXBunmarshal(req,YeePayReponse.class);
			System.out.println("code==="+response.getCode());
			Boolean isSign=this.verifySign(req, sign);
			logger.info("前台页面回调数据的签名验证结果："+isSign);
			if(isSign){
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]=response;
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]=response;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="出错了，请联系管理员";
		}		
		return ret ;
	}
	
	@Override
	public Boolean verifySign(String notify, String sign) {
		Boolean issign=false;
		String signverify=configMap.get("thirdPay_yeepay_signverify").toString().trim();
		if(signverify.equals("no")){
			issign=true;
		}else{
			issign=SignUtil.verifySign(notify, sign,signverify);
		}
		return issign;
	}
	/**
	 * 读取配置文件的信息
	 * @return
	 */
	@Override
	public Properties getyeePayProperties() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/hurong/credit/util/YeePayConfig/YeePay.properties");   
		  Properties p = new Properties();   
		  try {   
		   p.load(inputStream);  
		  } catch (IOException e1) {   
		   e1.printStackTrace();   
		  } 
		return p;
	}
	//===============网关接口方法开始(共10个接口,主要在P2P网站调用接口)===========================================================================================================	
	
	/**(1) 用户开户注册的方法 *2014-07-14 	
	 * Map<String,object> map  开通第三方支付需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号（需要自己生成的）
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("loginName").toString()开户昵称（用登陆名）
	 * map.get("trueName").toString()真实姓名
	 * map.get("cardcode").toString()身份证号码
	 * map.get("telephone").toString()手机号
	 * map.get("email").toString() 邮箱号
	 * 
	 */
	@Override
	public String[] register(Map<String,Object> map){
		// TODO Auto-generated method stub
		logger.info("调用易宝支付网关注册接口开始时间："+new Date());
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//注册的属性
		yeepay=regDate(yeepay,map);
		Map<String, String> params=getReqSign(yeepay,null);
		System.out.println(params);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOREG, params,YeePay.CHARSETUTF8);
				logger.info("用户开户注册调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}

	public String[] mobileregister(Map<String,Object> map,String mobile){
		// TODO Auto-generated method stub
		logger.info("调用易宝支付网关注册接口开始时间："+new Date());
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//注册的属性
		yeepay=regDate(yeepay,map);
		yeepay.setCallbackUrl(yeepay.getCallbackUrl()+"?mobile=mobile");
		Map<String, String> params=getReqSign(yeepay,null);
		System.out.println(params);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOREG, params,YeePay.CHARSETUTF8);
				logger.info("用户开户注册调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}
	public String[] Mobileregister(Map<String,Object> map){
		// TODO Auto-generated method stub
		logger.info("调用易宝支付网关注册接口开始时间："+new Date());
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//注册的属性
		yeepay=regDate(yeepay,map);
		yeepay.setCallbackUrl(yeepay.getCallbackUrl()+"?isMobile=1");
		Map<String, String> params=getReqSign(yeepay,null);
		System.out.println(params);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_YEEPAYTOREG, params,YeePay.CHARSETUTF8);
				logger.info("用户开户注册调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}
	
	/** (1-1)注册需要的属性方法 *2014-07-14
	 * @param yeepay
	 * @param bp
	 * @param request
	 * @param webBankcard
	 * @return
	 */
	public YeePay regDate(YeePay yeepay,Map<String,Object> map){
		yeepay.setPlatformUserNo(map.get("cardcode").toString()+"-"+map.get("customerId").toString()+"-"+map.get("customerType").toString());//
		yeepay.setRequestNo(map.get("cardcode").toString()+"-"+map.get("customerId").toString()+"-"+map.get("customerType").toString());//请求流水号(做过处理)
		yeepay.setNickName(map.get("loginName").toString());//昵称(非必填)
		yeepay.setRealName(map.get("trueName").toString());//会员真实姓名
		yeepay.setIdCardType("G2_IDCARD");//身份证类型 
		yeepay.setIdCardNo(map.get("cardcode").toString());//身份证号 
		yeepay.setMobile(map.get("telephone").toString());//手机号
		if(map.containsKey("email")){
			yeepay.setEmail(map.get("email").toString());//邮箱
		}else{
			yeepay.setEmail("无");//邮箱
		}
		
		System.out.println(map.get("basePath").toString());
		System.out.println(configMap.get("thirdPay_yeepay_callbackUrl").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		return yeepay;
	}
	
	/** (2)充值* 20140-07-15
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号（需要自己生成的）
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("incomMoney").toString()充值金额
	 */
	@Override
	public String[] recharge(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//充值的属性值
		yeepay=rechargeDate(yeepay, map);
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTORECH, params,YeePay.CHARSETUTF8);
				logger.info("用户充值调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	@Override
	public String[] Mobilerecharge(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//充值的属性值
		yeepay=rechargeDate(yeepay, map);
		yeepay.setCallbackUrl(yeepay.getCallbackUrl()+"?isMobile=1");
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_YEEPAYTORECH, params,YeePay.CHARSETUTF8);
				logger.info("用户充值调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	/**(2-1)充值需要的属性方法
	 * @return
	 */
	public YeePay rechargeDate(YeePay yeepay,Map<String,Object> map){
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setAmount(map.get("incomMoney").toString());//充值金额（非必填）
		yeepay.setFeeMode("PLATFORM");//费率模式
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		return yeepay;
	}

	/**(3)提现
	 * Map<String,object> map  第三方支付取现需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("paymoney").toString() 取现金额
	 */              
	public  String[] toWithdraw(Map<String,Object> map){
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay=toWithdrawDate(yeePay, map);
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,null);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.TOWITHDRAW, params,YeePay.CHARSETUTF8);
				logger.info("用户提现调用易宝参数："+ret[1]);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	/**(3)提现
	 * Map<String,object> map  第三方支付取现需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("paymoney").toString() 取现金额
	 */              
	public  String[] MobiletoWithdraw(Map<String,Object> map){
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay=toWithdrawDate(yeePay, map);
		//属性值存储在map<key,value>中
		yeePay.setCallbackUrl(yeePay.getCallbackUrl()+"?isMobile=1");
		Map<String, String> params=getReqSign(yeePay,null);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_TOWITHDRAW, params,YeePay.CHARSETUTF8);
				logger.info("用户提现调用易宝参数："+ret[1]);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	/**(3-1)提现的属性方法
	 */
	public YeePay toWithdrawDate(YeePay yeepay,Map<String,Object> map){
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		/**
		 * 手续费收取类型
		 */
		String poundage=configMap.get("poundage").toString().trim();
		if(Integer.parseInt(poundage)>0){//客户自行支付手续费
			yeepay.setFeeMode("USER");
		}else{//平台支付取现手续费
			yeepay.setFeeMode("PLATFORM");
		}
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		yeepay.setAmount(map.get("paymoney").toString());//数量
		return yeepay;
	}
	
	/**(4)绑定卡
     * Map<String,object> map  第三方支付绑定银行卡需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 */
	@Override
	public String[] toBindBankCard(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay=toBindBankCardDate(yeePay,map);
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.TOBINDBANKCARD, params,YeePay.CHARSETUTF8);
				logger.info("用户绑定卡调用易宝参数："+ret[1]);
			}catch (Exception e) {
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	
	@Override
	public String[] mobiletoBindBankCard(Map<String,Object> map,String mobile) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay=toBindBankCardDate(yeePay,map);
		yeePay.setCallbackUrl(yeePay.getCallbackUrl()+"?mobile=mobile");
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.TOBINDBANKCARD, params,YeePay.CHARSETUTF8);
				logger.info("用户绑定卡调用易宝参数："+ret[1]);
			}catch (Exception e) {
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	@Override
	public String[] MobiletoBindBankCard(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay=toBindBankCardDate(yeePay,map);
		yeePay.setCallbackUrl(yeePay.getCallbackUrl()+"?isMobile=1");
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_TOBINDBANKCARD, params,YeePay.CHARSETUTF8);
				logger.info("用户绑定卡调用易宝参数："+ret[1]);
			}catch (Exception e) {
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	
	
	
	/**(4-1)绑卡的属性方法
	 */
	public YeePay toBindBankCardDate(YeePay yeepay,Map<String,Object> map){
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		return yeepay;
	}
	
	/** (5)自动投标授权	 * 2014-07-15
     * Map<String,object> map  第三方支付自动投标授权需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 * @return
	 */
	@Override
	public String[] autoTransferAuthorization(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		Map<String, String> params=getReqSign(yeepay,null);
		System.out.println(params);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOAUTHORIZEAUTOTRANSFER, params,YeePay.CHARSETUTF8);
				logger.info("自动投标授权调用易宝页面通知："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	/**
	 * (6)自动还款授权	 * 2014-07-15
     * Map<String,Object> map  第三方支付自动投标授权需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * @return
	 */
	@Override
	public String[] autoRepaymentAuthorization(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		Map<String, String> params=getReqSign(yeepay,null);
		System.out.println(params);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOAUTHORIZEAUTOREPAYMENT, params,YeePay.CHARSETUTF8);
				logger.info("自动还款授权调用易宝页面通知："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}
	
	/**(7)资金冻结（投标）
     * Map<String,object> map  第三方支付投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidMoney").toString() 投标金额
	 * map.get("bidPlanMoney").toString() 标总金额
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * map.get("targetplatformUserNo").toString()
	 */
	@Override
	public String[] fiancialTransfer(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动投标的属性
		yeepay=fiancialTransferDate(yeepay,map);
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPTOTRANSFER, params,YeePay.CHARSETUTF8);
				logger.info("自动还款授权调用易宝页面通知："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	
	}
	
	
	/**(7)资金冻结（投标）mobile
     * Map<String,object> map  第三方支付投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidMoney").toString() 投标金额
	 * map.get("bidPlanMoney").toString() 标总金额
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * map.get("targetplatformUserNo").toString()
	 */
	@Override
	public String[] mobilefiancialTransfer(Map<String,Object> map,String isMobile) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动投标的属性
		yeepay=fiancialTransferDate(yeepay,map);
		String callbackurl = yeepay.getCallbackUrl()+"?isMobile=1";
		yeepay.setCallbackUrl(callbackurl);
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_YEEPTOTRANSFER, params,YeePay.CHARSETUTF8);
				logger.info("自动还款授权调用易宝页面通知："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	
	}
	
	
	
	/**(7-1)投标冻结接口参数准备（）
	 * @param yeepay
	 * @param map
	 * @return
	 */
	private YeePay fiancialTransferDate(YeePay yeepay,Map<String,Object> map) {
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());//标的号
		yeepay.setTransferAmount(map.get("bidPlanMoney").toString());//标的金额
		yeepay.setTargetPlatformUserNo(map.get("targetplatformUserNo").toString());//目标收款人
		yeepay.setPaymentAmount(map.get("bidMoney").toString());//冻结金额
		if(map.containsKey("JZWWmethod")){
			yeepay.setNotifyUrl(yeepay.getNotifyUrl()+"?JZWWmethod="+map.get("JZWWmethod").toString());
			yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString()+"?JZWWmethod="+map.get("JZWWmethod").toString());//页面回跳 URL
		}else{
			yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		}
		return yeepay;
	}
	
	/**(8)借款人还款接口
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidMoney").toString() 投标金额
	 * map.get("bidPlanMoney").toString() 标总金额
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * (List<Repayment>)map.get("repayments")
	 * @return
	 */
	@Override
	public String[] toRepaymentByLoaner(Map<String,Object> map) {
		// TODO Auto-generated method stub
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动还款的属性
		yeepay=repayMentPublicData(yeepay,map);
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOREPAYMENT, params,YeePay.CHARSETUTF8);
			logger.info("借款人还款调用易宝参数："+ret[1]);
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	
	/**(8-1) 还款接口准备数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private YeePay repayMentPublicData(YeePay yeepay,Map<String,Object> map) {
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setRepayments((List<Repayment>)map.get("repayments"));//？？
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		return yeepay;
	}
	
	/**
	 * (9)债权交易接口
	 * @param map
	 * @return
	 */
	@Override
	public String[] obligationDeal(Map<String, Object> map) {
		// TODO 债权交易接口
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//债权交易的参数
		yeepay.setPlatformUserNo(map.get("thirdPayConfigId").toString());
		yeepay.setAmount(map.get("salesMoney").toString());//交易金额
		yeepay.setRequestNo(map.get("requestNo").toString());//新的交易流水号
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		yeepay.setPaymentRequestNo(map.get("oldRequestNo").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTRANSFERDEALINTEFACE, params,YeePay.CHARSETUTF8);
			logger.info("债权交易调用易宝参数："+ret[1]);
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	
	}
	
	/**(10)取消绑定卡
     * Map<String,object> map  第三方支付取消绑定银行卡需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 */
	@Override
	public String[] cancelBindBankCard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,null);
		//自动投标的属性
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());
		yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());
		Map<String, String> params=getReqSign(yeepay,null);
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYTOUNBINDBANKCARD, params,YeePay.CHARSETUTF8);
				logger.info("用户取消绑定卡调用易宝参数："+ret[1]);
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	
	
	}
	/**
	 * (11)通用转账接口
	 * @param map
	 * @return
	 */
	@Override
	public String[] ommontransferIntrface(Map<String, Object> map) {
		// TODO 通用转账接口业务处理参数
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
			YeePay yeepay=new YeePay();
			//公共的商户编号
			yeepay=generatePublicData(yeepay,map.get("basePath").toString());
			//通用转账的参数
			yeepay.setRequestNo(map.get("requestNo").toString());
			yeepay.setPlatformUserNo(map.get("thirdPayConfigId").toString());
			yeepay.setBizType("TRANSFER");
			yeepay.setUserType("MEMBER");
			if(map.containsKey("details")){
				yeepay.setDetails((List<Detail>)map.get("details"));
			}else{
				List<Detail> details=new ArrayList<Detail>();
				String  totalMoney=(String) map.get("amount");
				String amount=new BigDecimal(totalMoney).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString();
				Detail detail =new Detail();
				detail.setAmount(amount);
				detail.setTargetPlatformUserNo(configMap.get("thirdPay_yeepay_platformNo").toString().trim());
				detail.setTargetUserType("MERCHANT");
				detail.setBizType("TRANSFER");
				details.add(detail);
				yeepay.setDetails(details);
			}
			
			yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
			Map<String, String> params=getReqSign(yeepay,null);
			if(configMap.get("thirdPay_yeepay_URL")!=null){
				//易宝支付调用地址
				String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYCOMMONTTRANSFER, params,YeePay.CHARSETUTF8);
				logger.info("通用转账授权调用易宝参数："+ret[1]);
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}/**
	 * (11)手机转账接口
	 * @param map
	 * @return
	 */
	@Override
	public String[] mobileommontransferIntrface(Map<String, Object> map,String isMobile) {
		// TODO 通用转账接口业务处理参数
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
			YeePay yeepay=new YeePay();
			//公共的商户编号
			yeepay=generatePublicData(yeepay,map.get("basePath").toString());
			//通用转账的参数
			yeepay.setRequestNo(map.get("requestNo").toString());
			yeepay.setPlatformUserNo(map.get("thirdPayConfigId").toString());
			yeepay.setBizType("TRANSFER");
			yeepay.setUserType("MEMBER");
			if(map.containsKey("details")){
				yeepay.setDetails((List<Detail>)map.get("details"));
			}else{
				List<Detail> details=new ArrayList<Detail>();
				String  totalMoney=(String) map.get("amount");
				String amount=new BigDecimal(totalMoney).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).toString();
				Detail detail =new Detail();
				detail.setAmount(amount);
				detail.setTargetPlatformUserNo(configMap.get("thirdPay_yeepay_platformNo").toString().trim());
				detail.setTargetUserType("MERCHANT");
				detail.setBizType("TRANSFER");
				details.add(detail);
				yeepay.setDetails(details);
			}
			
			yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
			Map<String, String> params=getReqSign(yeepay,null);
			if(configMap.get("thirdPay_yeepay_URL")!=null){
				//易宝支付调用地址
				String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
				ret=WebClient.operateParameter(yeepayurl+YeePay.MOBILE_YEEPAYCOMMONTTRANSFER, params,YeePay.CHARSETUTF8);
				logger.info("通用转账授权调用易宝参数："+ret[1]);
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	
	/**
	 * (12)修改注册手机号码接口
	 * @see com.hurong.credit.service.thirdInterface.YeePayService#changeMobile(java.util.Map)
	 */
	@Override
	public String[] changeMobile(Map<String, Object> map) {
		System.out.println("修改注册手机号码进入实现类");
		String[] ret=new String[2];
		try{
			YeePay yeepay=new YeePay();
			//公共的商户编号
			yeepay=generatePublicData(yeepay,map.get("basePath").toString());
			//修改注册手机号码的参数
			yeepay.setRequestNo(map.get("requestNo").toString());
			yeepay.setPlatformUserNo(map.get("thirdPayConfigId").toString());
			yeepay.setMobile(map.get("telephone").toString());
			yeepay.setCallbackUrl(map.get("basePath").toString()+configMap.get("thirdPay_yeepay_callbackUrl").toString());//页面回跳 URL
			Map<String, String> params=getReqSign(yeepay,null);
			if(configMap.get("thirdPay_yeepay_URL")!=null){
				//易宝支付调用地址
				String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
				ret=WebClient.operateParameter(yeepayurl+YeePay.YEEPAYCHANGEMOBILE, params,YeePay.CHARSETUTF8);
				logger.info("修改注册手机号码调用易宝参数："+ret[1]);
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	
//===============网关接口方法结束===========================================================================================================
	
//===============直连接口方法开始(共13个接口,主要在ERP端调用接口)===========================================================================================================	
	
	/**(11)注册用户查询接口
     * Map<String,Object> map  第三方支付注册用户查询需要的map参数
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("requestNo").toString()交易流水号
	 */
	@Override
	public Object[] registerQuery(Map<String, Object> map) {
		System.out.println("进入实现类");
		Object[] ret=new Object[2];
		try{
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,null);
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,"ACCOUNT_INFO");
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
			logger.info("注册用户查询请求参数："+param);
			System.out.println("YeePay.YEEPAYDIRECT===="+YeePay.YEEPAYDIRECT);
			outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,120000); 
			logger.info("注册用户查询结果："+outStr);
			YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
			Properties p=this.getyeePayProperties();
			if(response.getCode().equals("1")){
				if(response.getBank()!=null){
					response.setBankName(p.getProperty(response.getBank()));
				}
				 ret[0]=Constants.CODE_SUCCESS;
				 ret[1]=response;
			  }else{
				  ret[0]=Constants.CODE_FAILED;
				  ret[1]="注册用户信息查询失败";
			  }
			
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	
	
	
	/**(12)冻结 （这个接口只能和解冻接口配合使用） 2014-07-15
     * Map<String,Object> map  第三方支付冻结需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 * map.get("payMoney").toString() 冻结金额
	 * map.get("unFrezeeTime").toString()  自动解冻时间
	 * @return
	 */
	@Override
	public String[] freeze(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//冻结的属性
		yeepay=freezePublicData(yeepay,map);
		Map<String, String> params=getReqSign(yeepay,"FREEZE");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("冻结返回状态："+outStr);//1:代表成功 
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	/**(12-1)冻结需要的属性方法  2014-07-15
	 */
	public YeePay freezePublicData(YeePay yeepay,Map<String, Object> map){
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setAmount(map.get("payMoney").toString());//冻结金额
		yeepay.setExpired(map.get("unFrezeeTime").toString());//自动解冻时间点
		return yeepay;
	}
	
	
	/**(13) 解冻 	 * 2014-07-15
     * Map<String,Object> map  第三方支付解冻需要的map参数
	 * map.get("freezeRequestNo").toString()冻结交易流水号
	 * @return
	 */
	@Override
	public String[] unfreeze(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,null);
		//解冻的属性
		yeepay=unfreezePublicData(yeepay,map);
		Map<String, String> params=getReqSign(yeepay,"UNFREEZE");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("解冻返回状态："+outStr);//1:代表成功 
				  System.out.println(outStr);
				  Document doc=XmlUtil.stringToDocument(outStr);
				  String  code=doc.selectSingleNode("/response/code").getText();
				  String  description=doc.selectSingleNode("/response/description").getText();
				  if(code.equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=description; 
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=description;
				  }
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	
	/**(13-1)解冻需要的属性方法
	 * @param yeepay
	 * @param bp
	 * @param request
	 * @param webBankcard
	 * @return
	 */
	public YeePay unfreezePublicData(YeePay yeepay,Map<String, Object> map){
		yeepay.setFreezeRequestNo(map.get("freezeRequestNo").toString());//请求流水号
		return yeepay;
	}
	
	/**(14)自动投标
     * Map<String,Object> map  第三方支付自动投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidMoney").toString() 投标金额
	 * map.get("bidPlanMoney").toString() 标总金额
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * map.get("targetplatformUserNo").toString()借款人的第三方支付账号
	 * @return
	 */
	
	@Override
	public String[] autoTransfer(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动投标的属性
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());//标的号
		yeepay.setTransferAmount(map.get("bidPlanMoney").toString());//标的金额
		yeepay.setTargetPlatformUserNo(map.get("targetplatformUserNo").toString());//目标收款人
		yeepay.setPaymentAmount(map.get("bidMoney").toString());//冻结金额
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,"AUTO_TRANSFER");
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				 outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				 System.out.println("自动投标返回状态："+outStr);//1:代表成功 
				 YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
				 if(response.getCode().equals("1")){
					 ret[0]=Constants.CODE_SUCCESS;
					 ret[1]=response.getDescription();
				 }else{
					 ret[0]=Constants.CODE_FAILED;
					 ret[1]=response.getDescription();
				 }
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	
	}
	
	/**(15)自动还款（平台帮借款操作还款）
     * Map<String,Object> map  第三方支付自动还款需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidMoney").toString() 投标金额
	 * map.get("bidPlanMoney").toString() 标总金额
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * (List<Repayment>)map.get("repayments")
	 * @return
	 */
	@Override
	public String[] autoRepaymentA(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动还款的属性
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setRepayments((List<Repayment>)map.get("repayments"));//？？
		Map<String, String> params=getReqSign(yeepay,"AUTO_REPAYMENT");
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			String outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
			System.out.println("自动还款返回状态："+outStr);//1:代表成功 
			YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
			 if(response.getCode().equals("1")){
				 ret[0]=Constants.CODE_SUCCESS;
				 ret[1]=response.getDescription();
			 }else{
				 ret[0]=Constants.CODE_FAILED;
				 ret[1]=response.getDescription();
			 }
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	
	}

	
	/**(16)放款(2014-07-15)
     * Map<String,Object> map  第三方支付放款需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("PlatformFee").toString() 平台收取费用
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * (List<Transfer>)map.get("transfer")
	 * @return
	 */
	public String[] loan(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动还款的属性
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());//标的号
		yeepay.setFee(map.get("PlatformFee").toString());//平台方收取费用
		yeepay.setTransfers((List<Transfer>)map.get("transfer"));
		String outStr="";
		Map<String, String> params=getReqSign(yeepay,"LOAN");
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("放款返回状态："+outStr);//1:代表成功 
				  YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response.getDescription();
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=response.getDescription();
				  }
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	/**(17)取消投标
	 * Map<String,Object> map  第三方支付取消投标需要的map参数
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("requestNo").toString()交易流水号
	 * map.get("bidrequestNo").toString()之前投标流水号
	 * @return
	 */
	@Override
	public String[] REVOCATION_TRANSFER(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,null);
		//得到相应的属性
		yeePay=cancelbidDate(yeePay,map);
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,"REVOCATION_TRANSFER");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				  String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr =WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("取消投标：outStr=="+outStr);
				  YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response.getDescription();
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=response.getDescription();
				  }
			}catch (Exception e) {
				e.printStackTrace();
				 ret[0]=Constants.CODE_FAILED;
				  ret[1]="操作失败,请联系管理员";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	}
	
	/**
	 *(17-1)取消投标的属性方法
	 */
	public YeePay cancelbidDate(YeePay yeepay,Map<String,Object> map){
		yeepay.setRequestNo(map.get("bidrequestNo").toString());//商户平台会员标识
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());//商户平台会员标识
		return yeepay;
	}
	/**(18)平台划款
	 * Map<String,Object> map  第三方支付取消投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 * map.get("money");划款金额
	 * @return
	 */
	@Override
	public String[] PLATFORM_TRANSFER(Map<String, Object> map) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,null);
		//得到相应的属性
		yeePay = PLATFORM_TRANSFERDate(yeePay,map);
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,"PLATFORM_TRANSFER");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				  String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr =WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("平台划款=="+outStr);
				  YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response.getDescription();
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=response.getDescription();
				  }
			}catch (Exception e) {
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="请联系系统管理员";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
		
	}
	
	/**(18-1)平台划款的属性方法
	 */
	public YeePay PLATFORM_TRANSFERDate(YeePay yeepay,Map<String, Object> map){
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setSourceUserType("MERCHANT");//出款人类型
		yeepay.setSourcePlatformUserNo(yeepay.getPlatformNo());//出款人编号
		yeepay.setAmount(map.get("money").toString());//划款金额
		yeepay.setTargetUserType("MEMBER");//收款人类型
		yeepay.setTargetPlatformUserNo(map.get("platformUserNo").toString());//收款编号
		return yeepay;
		
	}
	
	/**(19)准备金还款
	 * Map<String,Object> map  第三方支付准备金还款需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * (List<Repayment>)map.get("repayments")
	 * @return
	 */
	@Override
	public String[] toRepaymentByReserve(Map<String, Object> map) {

		// TODO Auto-generated method stub
		System.out.println("进入实现类");
		String[] ret=new String[2];
		try{
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,map.get("basePath").toString());
		//自动还款的属性
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());//请求流水号
		yeepay.setRepayments((List<Repayment>)map.get("repayments"));//？？
		Map<String, String> params=getReqSign(yeepay,"RESERVE_REPAYMENT");
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			String outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
			 System.out.println("准备金还款返回状态："+outStr);//1:代表成功 
			 YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
			 if(response.getCode().equals("1")){
				 ret[0]=Constants.CODE_SUCCESS;
				 ret[1]=response.getDescription();
			 }else{
				 ret[0]=Constants.CODE_FAILED;
				 ret[1]=response.getDescription();
			 }
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	
	
	}
	
	/**(20)单笔业务查询
	 * Map<String,Object> map  第三方支付取消投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("queryType").toString();
	 * map.get("queryRequestNo").toString()以前的交易流水号
	 */
	@Override
	public Object[] QUERY(Map<String, Object> map) {
		Object[] ret=new Object[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,null);
		//得到相应的属性
		yeePay = QUERYDate(yeePay, map);
		Map<String, String> params=getReqSign(yeePay,"QUERY");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				  String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr =WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("单笔业务查询");
				  System.out.println(outStr);
				  YeePayReponse response=JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response;
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]="查询失败";
				  }
			}catch (Exception e) {
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				  ret[1]="接口对接失败";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
		
	}
	
	/**(20-1)单笔业务的属性方法
	 * @param requestNo
	 * @param mode
	 */
	public YeePay QUERYDate(YeePay yeepay,Map<String, Object> map){
		yeepay.setRequestNo(map.get("queryRequestNo").toString());//请求流水号
		yeepay.setMode(map.get("queryType").toString());//单笔业务查询查询模式
		return yeepay;
	}
	
	
	
	
	/**(21) 对账
	 * Map<String,Object> map  第三方支付对账需要的map参数
	 * map.get("date").toString();
	 */
	@Override
	public Object[] RECONCILIATION(Map<String, Object> map) {
		Object[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,null);
		//得到相应的属性
		yeePay = RECONCILIATIONDate(yeePay,map);
		Map<String, String> params=getReqSign(yeePay,"RECONCILIATION");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				  String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr =WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("取消投标");
				  System.out.println(outStr);
				  YeePayReponse response=JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response;
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]="查询失败";
				  }
				  
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		return ret;
	}
	
	/**
	 * (21-1)对账的属性方法
	 * @param yeepay
	 * @param date
	 * @return
	 */
	public YeePay RECONCILIATIONDate(YeePay yeepay,Map<String, Object> map){
		yeepay.setDate(map.get("date").toString());//对账时间测试赋值
		return yeepay;
	}
	




	/**(22)取消自动投标授权    *2014-07-15
	 * Map<String,Object> map  第三方支付取消投标需要的map参数
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("requestNo").toString()交易流水号
	 * @return
	 */
	@Override
	public String[] cancelTransferAuthorization(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,null);
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());
		Map<String, String> params=getReqSign(yeepay,"CANCEL_AUTHORIZE_AUTO_TRANSFER");
		System.out.println(params);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				 System.out.println("param===="+param);
				 System.out.println("YeePay.YEEPAYDIRECT===="+YeePay.YEEPAYDIRECT);
				 System.out.println("URL===="+yeepayurl+YeePay.YEEPAYDIRECT);
				  outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("取消自动投标授权状态："+outStr);//1:代表成功 
				  Document doc=XmlUtil.stringToDocument(outStr);
				  String  code=doc.selectSingleNode("/response/code").getText();
				  String  description=doc.selectSingleNode("/response/description").getText();
				  if(code.equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=description; 
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=description;
				  }
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}
	
	/**(23)取消自动还款授权  2014-07-15
	 * Map<String,Object> map  第三方支付取消投标需要的map参数
	 * map.get("basePath").toString() 只当前的绝对路径
	 * map.get("platformUserNo").toString() 第三方支付账号
	 * map.get("customerId").toString();
	 * map.get("customerType").toString();
	 * map.get("requestNo").toString()交易流水号
	 * map.get("bidPlanId").toString() 标的id
	 * map.get("bidPlanType").toString() 标的类型
	 * @return
	 */
	@Override
	public String[] cancelRepaymentAuthorization(Map<String,Object> map) {
		String[] ret=new String[2];
		YeePay yeepay=new YeePay();
		//公共的商户编号
		yeepay=generatePublicData(yeepay,null);
		yeepay.setPlatformUserNo(map.get("platformUserNo").toString());
		yeepay.setRequestNo(map.get("requestNo").toString());
		yeepay.setOrderNo(map.get("bidPlanType").toString()+"-"+map.get("bidPlanId").toString());
		Map<String, String> params=getReqSign(yeepay,"CANCEL_AUTHORIZE_AUTO_REPAYMENT");
		System.out.println(params);
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				 String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				 System.out.println("param===="+param);
				 System.out.println("YeePay.YEEPAYDIRECT===="+YeePay.YEEPAYDIRECT);
				 System.out.println("URL===="+yeepayurl+YeePay.YEEPAYDIRECT);
				  outStr=WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("取消自动还款授权状态："+outStr);//1:代表成功 
				  Document doc=XmlUtil.stringToDocument(outStr);
				  String  code=doc.selectSingleNode("/response/code").getText();
				  String  description=doc.selectSingleNode("/response/description").getText();
				  if(code.equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=description; 
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=description;
				  }
			}catch(Exception e){
				e.printStackTrace();
				ret[0]=Constants.CODE_FAILED;
				ret[1]="对接失败"+e.getMessage();
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		
		
		return ret;
	}
	 /**
     * (24)通用转账授权审核接口  2015-01-27
     * @param map
     * @return
     */
	@Override
	public String[] checkCommentTransfer(Map<String, Object> map) {
		String[] ret=new String[2];
		YeePay yeePay=new YeePay();
		//得到公共的商务代码
		yeePay=generatePublicData(yeePay,map.get("basePath").toString());
		//得到相应的属性
		yeePay.setRequestNo(map.get("requestNo").toString());
		yeePay.setMode(map.containsKey("checkStatus")?"CANCEL":"CONFIRM");
		//属性值存储在map<key,value>中
		Map<String, String> params=getReqSign(yeePay,"COMPLETE_TRANSACTION");
		String outStr="";
		if(configMap.get("thirdPay_yeepay_URL")!=null){
			//易宝支付调用地址
			String yeepayurl=configMap.get("thirdPay_yeepay_URL").toString();
			try{
				  String param=UrlUtils.generateParams(params,YeePay.CHARSETUTF8);
				  outStr =WebClient.getWebContentByPost(yeepayurl+YeePay.YEEPAYDIRECT, param, YeePay.CHARSETUTF8,12000); 
				  System.out.println("通用转账审核页面：outStr=="+outStr);
				  YeePayReponse response =JAXBunmarshal(outStr,YeePayReponse.class);
				  if(response.getCode().equals("1")){
					  ret[0]=Constants.CODE_SUCCESS;
					  ret[1]=response.getDescription();
				  }else{
					  ret[0]=Constants.CODE_FAILED;
					  ret[1]=response.getDescription();
				  }
			}catch (Exception e) {
				e.printStackTrace();
				 ret[0]=Constants.CODE_FAILED;
				  ret[1]="操作失败,请联系管理员";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易宝支付配置信息不齐全，请联系系统管理员进行配置";
		}
		return ret;
	
	}



//===============直连接口方法开始===========================================================================================================



}
