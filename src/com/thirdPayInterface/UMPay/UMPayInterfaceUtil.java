package com.thirdPayInterface.UMPay;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.thirdPayInterface.CommonRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayWebClient;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
import com.thirdPayInterface.UMPay.UMPayUtil.SignUtil;
import com.thirdPayInterface.UMPay.UMPayUtil.SunBase64;


public class UMPayInterfaceUtil {

	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	protected static Log logger=LogFactory.getLog(UMPayInterfaceUtil.class);
	public static final Map<String,String> requestValue=new HashMap<String,String>();
	/**
	 * 第三方支付环境（正式环境和测试环境）
	 */
	private static String  thirdPayEnvironmentType=configMap.containsKey("thirdPayEnvironmentType")?configMap.get("thirdPayEnvironmentType").toString().trim():ThirdPayConstants.THIRDENVIRONMENT1;
	static  ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
	/**
	 * 准备UMP公共支付数据
	 * @param umpay
	 * @return
	 */
	private static UMPay generalPublicDate(UMPay umpay,Boolean notifyUrl,Boolean pageUrl,Boolean isMobile,String bussinessType) {
		Map thirdPayConfig=umProperty();
		umpay.setVersion("1.0");//版本号
		umpay.setCharset(UMPay.UTF8);//参数字符编码集
		umpay.setRes_format("HTML");//响应数据格式
		umpay.setSign_type("RSA");//签名方式
		umpay.setMer_id(thirdPayConfig.get("thirdPay_umpay_MemberID").toString());//商户编号
		String BasePath=ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath() + "/";
		if(notifyUrl){
			umpay.setNotify_url(BasePath+thirdPayConfig.get("thirdPay_umpay_notifyUrl").toString());
		}
		if(pageUrl){
			if(isMobile){
				umpay.setRet_url(BasePath+thirdPayConfig.get("thirdPay_umpay_callbackUrl").toString().trim()+"?isMobile=1");
				umpay.setSourceV("HTML5");
			}else{
				umpay.setRet_url(BasePath+thirdPayConfig.get("thirdPay_umpay_callbackUrl").toString().trim());
			}
		}
		return umpay;
	}
	/**
	 * 获取第三方支付环境参数
	 * @return
	 */
	private static Map umProperty(){
		Map umConfigMap=new HashMap();
		try{
			InputStream in=null;
			//获取当前支付环境为正式环境还是测试环境
			if(thirdPayEnvironmentType.equals(ThirdPayConstants.THIRDENVIRONMENT0)){//正式环境
		       in = UMPayInterfaceUtil.class.getResourceAsStream("UMPayNormalEnvironment.properties"); 
			}else{
		        in = UMPayInterfaceUtil.class.getResourceAsStream("UMPayTestEnvironment.properties"); 
			}
			Properties props =  new  Properties(); 
			if(in!=null){
				props.load(in);
		    	Iterator it= props.keySet().iterator();
		    	while(it.hasNext()){
		    		String key=(String)it.next();
		    		umConfigMap.put(key, props.getProperty(key));
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
    		logger.error(ex.getMessage());
    	}
		return umConfigMap;
	}
	/**
	 * 
	 * <br>description : 用联动公钥加密
	 * @param s
	 * @return
	 * @throws Exception
	 * @version     1.0
	 * @date    2014-11-11
	 */
	private static String Encrypt(String s,String charset) throws Exception {
		X509Certificate cert = PkCertFactory.getCert();
	    byte[] keyBytes = cert.getPublicKey().getEncoded();
	    // 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		String str = SunBase64.encode(cipher.doFinal(s.getBytes(charset))).replace("\n", "");
		return str;
	}
	/**
	 * 获取需要拼接成HTML页面需要的参数map列表
	 * @param umpay
	 * @return
	 */
	private static Map<String, String> createHtmlMap(UMPay umpay) {
		Map map = new HashMap();;
		try{
			if(umpay!=null){
				Class type = umpay.getClass();
		        BeanInfo beanInfo = Introspector.getBeanInfo(type);
		        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
		        for(int i=0;i<propertyDescriptors.length;i++){
		        	 PropertyDescriptor descriptor=propertyDescriptors[i];
		        	 String propertyName = descriptor.getName();
		             if (!propertyName.equals("class")) {
		                 Method readMethod = descriptor.getReadMethod();
		                 Object result = readMethod.invoke(umpay, new Object[0]);
		                 if (result != null&&!"".equals(result)){
		                	 map.put(propertyName, result);
		                 }
		             }
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据项目标对象获取签名对象以及页面参数列表map对象
	 * @param umpay
	 * @return
	 */
	public static Map<String,String> creatDataMap(UMPay umpay){
		Map returnMap = new HashMap();
		try{
			if(umpay!=null){
				Map map = UMPayInterfaceUtil.createHtmlMap(umpay);
		        if(!map.isEmpty()){
		        	Object[] obj = map.keySet().toArray();
					Arrays.sort(obj);
					String plain,sign,str,value,valueEncoder= null;
					StringBuffer plainString = new StringBuffer();
					StringBuffer signString = new StringBuffer();
					for(int i = 0 ; i < obj.length ; i++){
						str = obj[i].toString();
						value = map.get(str).toString();
						System.out.println("str=="+str+"    value=="+value);
						if(value==null||"".equals(value)){
							continue;
						}
						if("sign_type".equals(str)){
							value=URLEncoder.encode(value,"UTF-8");
							plainString.append(str).append("=").append(value).append("&");
						}else{
							valueEncoder =URLEncoder.encode(value,"UTF-8");
							plainString.append(str).append("=").append(valueEncoder).append("&");
							signString.append(str).append("=").append(value).append("&");
						}
					}
					plain = plainString.subSequence(0, plainString.length()-1).toString();
					sign = signString.substring(0,signString.length()-1).toString();
					System.out.println("请求数据获得的签名明文串为signString: "+sign);
					logger.info("请求数据获得的签名明文串为："+ sign);
					String merId =umpay.getMer_id().trim();
					sign = SignUtil.sign(sign,merId.trim());
					System.out.println("请求数据获得的签名串为sign: "+sign);
					logger.info("请求数据获得的签名串为：" + sign);
					plain=plain+"&sign="+URLEncoder.encode(sign,"UTF-8");
					returnMap.put("plain", plain);
					returnMap.put("sign", sign);
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return returnMap;
	}
	/**
	 * 用来解析html方法
	 * @param html
	 * @return
	 */
	public static Map<String,String> parseHTMLMethod(String html){
		try{
			String returnDate="";
			if(html.contains("&reg")){
				html=html.replaceAll("&reg", "&changeO");
			}
			Document document=Jsoup.parse(html);
			if(document!=null){
				Element head=document.head();
			    System.out.println("head=="+head);
			    
			    List<Node> list=head.childNodes();
			    if(list!=null){
			    	 for(Node temp:list){
			    		 System.out.println("temp=="+temp.attr("name"));
			    		 String metaName=temp.attr("name");
			    		 String content=temp.attr("content");
			    		 if(content.contains("&changeO")){
			    			 content=content.replaceFirst("&changeO", "&reg");
							}
			    		 if(metaName!=null&&!"".equals(metaName)&&metaName.equals("MobilePayPlatform")){
			    			 returnDate= content;
			    			 break;
			    		 }
			    	 } 
			    }
			}
			if(!"".equals(returnDate)){
				logger.info("解析成功，content"+returnDate);
				Map<String,String> map=new HashMap<String,String>();
				String[] ret=returnDate.split("&");
				if(ret!=null&&ret.length>0){
					for(String strr:ret){
						logger.info("解析成功，往mapput数据"+strr);
						map.put(strr.split("=",2)[0], strr.split("=",2)[1]);
					}
					logger.info("解析成功，生成了map："+map);
					return map;
				}else{
					logger.info("解析失败，content数据split分解出错："+ret.length);
					return null;
				}
			}else{
				logger.info("没有获取解析的content数据");
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("解析HTML字符串出错,原因"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 验证收到签名
	 * @param map
	 * @return
	 */
	public static Boolean verifySign(Map<String,String> map){
		Boolean signValue=false;
		try{
			Object[] obj = map.keySet().toArray();
			Arrays.sort(obj);
			String plain,sign,str,value,valueEncoder= null;
			StringBuffer signString = new StringBuffer();
			for(int i = 0 ; i < obj.length ; i++){
				str = obj[i].toString();
				value = map.get(str).toString();
				if(value==null||"".equals(value)){
					continue;
				}
				if("sign".contains(str)||"sign_type".contains(str)){
				}else{
					signString.append(str).append("=").append(value).append("&");
				}
			}
			sign = signString.substring(0,signString.length()-1).toString();
			signValue = SignUtil.verify(map.get("sign").toString(), sign);
			System.out.println("验证签名结果: "+signValue);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("验证签名方法报错,原因"+e.getMessage());
		}
		return signValue;
		
	}
	/**
	 * 接口输出对第三方回调通知已经正确接收的返回html
	 * @param map
	 * @return
	 */
	public static String createReaponseHTML() {
		UMPay umpay=new UMPay();
		umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,null);
		umpay.setCharset(null);
		umpay.setRet_code("0000");
		Map<String,String> map=UMPayInterfaceUtil.creatDataMap(umpay);
		if(!map.isEmpty()&&map.containsKey("plain")){
			String html="<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>"
				+"<html>"
				+"<head>"
				+"<META NAME='MobilePayPlatform' CONTENT="+map.get("plain").toString()+" />"
				+"</head>"	
				+"<body>"
				+"</body>"
				+"</html>";
			return html;
		}else{
			return null;
		}
	}
    //######################################网关接口（开始）#############################################
	/**
	 * 网关接口01
	 * 联动优势支付个人客户充值接口
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("requestNo",String);//充值交易记录流水号
	 * map.put("orderDate",String);//生成订单的日期
	 * map.put("thirdPayflagId",String);//充值客户的联动用户号
	 * map.put("thirdPayflagId0",String);//充值客户的联动账户号
	 * map.put("amount",BigDecimal);//充值用户充值金额
	 * map.put("bankCode",String);//充值选择的银行代码
	 * @param map
	 * @return
	 */
	public static CommonResponse recharge(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付个人有密码充值接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_RECHARGE);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(DateUtil.dateToStr(new Date(),"yyyyMMdd"));
			//默认是个人网银支付
			umpay.setPay_type(UMPay.BANK_PERSON);
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			//读取联动给注册用户开的资金账户
			//umpay.setAccount_id(commonRequst.getThirdPayConfigId0());
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			//读取进行充值的银行卡
			umpay.setGate_id(commonRequst.getBankCode());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付个人有密码充值接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);// configMap.get("thirdPay_umpay_URL")
			logger.info("联动优势支付个人有密码充值接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付个人有密码充值接口调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			if(ret!=null&&ret.length>1){
				if(ret[0]!=null&&ret[0].equals("SUCCESS")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("充值申请已提交");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("充值申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("接口调用异常");
			}
			commonResponse.setCommonRequst(commonRequst);
		}catch(Exception e){
			logger.info("联动优势支付充值接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付充值接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	 /* 网关接口07.1  
	 * 个人还款
	 * 联动优势支付投标类有密码转账接口
	 * 涉及（涉及投资人投资，借款人还款）
     * Map<String,object> map  第三方支付投标需要的map参数
	 * map.put("basePath",String) 只当前的绝对路径
	 * map.put("requestNo",string)交易流水号
	 * map.put("orderDate",String)生成交易订单日期
	 * map.put("amount",BigDecimal) 投标金额
	 * map.put("bidPlanId",Long) 标的id
	 * map.put("bidType",String) 标的类型
	 * map.put("thirdPayflagId",String)转账人的联动优势支付开户账号
	 * map.put("transferUserType",String)转账方账户类型
	 * map.put("businessType",String)转账的类型属于投资还是还款
	 * @param map
	 * @return
	 */
	public static CommonResponse transferInterface_repay(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			logger.info("联动优势支付投标类有密码转账接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_TRANSFER);
			
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sdf.format(commonRequst.getTransactionTime()));
			
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidType()+commonRequst.getBidId());
			//业务类型
			String bussinessType="";
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//投标
				bussinessType="01";
			}
			else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//还款
				bussinessType="03";
			}
			umpay.setServ_type(bussinessType);
			//转账方类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
			umpay.setPartic_type("02");
			//标的转入
			umpay.setTrans_action(UMPay.DIRECTION_CUSTOMER);
			//转账方账户类型
			if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);//企业用户			
				}else{
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);//个人用户
				}
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付投标类有密码转账调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付支付投标类有密码转账接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付支付投标类有密码转账接口调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			System.out.println("ret[1]===="+ret[1]);
			System.out.println("ret[0]===="+ret[0]);
			if(ret!=null&&ret.length>1){
				if(ret[0].equals("SUCCESS")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("还款申请提交成功");
					commonResponse.setThirdPayConfig("UMPayConfig");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("还款申请提交失败");
					
				}
				commonResponse.setCommonRequst(commonRequst);
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("还款申请提交失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			logger.error("联动优势支付支付投标类有密码转账接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("调用");
			commonResponse.setCommonRequst(commonRequst);
		}
		return  commonResponse;
	}

	/**
	 * 网关接口07
	 * 联动优势支付投标类有密码转账接口
	 * 涉及（涉及投资人投资，借款人还款）
     * Map<String,object> map  第三方支付投标需要的map参数
	 * map.put("basePath",String) 只当前的绝对路径
	 * map.put("requestNo",string)交易流水号
	 * map.put("orderDate",String)生成交易订单日期
	 * map.put("amount",BigDecimal) 投标金额
	 * map.put("bidPlanId",Long) 标的id
	 * map.put("bidType",String) 标的类型
	 * map.put("thirdPayflagId",String)转账人的联动优势支付开户账号
	 * map.put("transferUserType",String)转账方账户类型
	 * map.put("businessType",String)转账的类型属于投资还是还款
	 * @param map
	 * @return
	 */
	public static CommonResponse transferInterface(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			logger.info("联动优势支付投标类有密码转账接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_TRANSFER);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sdf.format(commonRequst.getTransactionTime()));
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidType()+commonRequst.getBidId());
			//业务类型
			String bussinessType="";
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)
				||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){
				bussinessType="01";
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BACKDEAL)){//还款
				bussinessType="03";
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
				bussinessType="02";
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BACKDEAL)){//债权转让返款
				bussinessType="56";
			}
			umpay.setServ_type(bussinessType);
			//转账方类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
			umpay.setPartic_type("01");
			//标的转入
			umpay.setTrans_action(UMPay.DIRECTION_CUSTOMER);
			//转账方账户类型
			if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);//企业用户
			}else{
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);//个人用户			
			}
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付投标类有密码转账调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付支付投标类有密码转账接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付支付投标类有密码转账接口调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			System.out.println("ret[0]===="+ret[0]+" "+ret[1]);
			if(ret!=null&&ret.length>1){
				if(ret[0]!=null&&ret[0].equals("SUCCESS")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("投标类有密码转账申请已提交");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("投标类有密码转账申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("接口调用异常");
			}
			commonResponse.setCommonRequst(commonRequst);
		}catch(Exception e){
			logger.error("联动优势支付支付投标类有密码转账接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势个人有密接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
	     return commonResponse;
	}
    /**
     * 直连接口13
	 * 联动优势支付投标类无密标的接口（平台发起）
	 * 主要针对接收处理P2P平台发起的标的转入（对应业务场景为投资、还款）请求，不经过验密，直接完成其他账户资金划转到标的账户和相应记录的更新接收处理，同步响应商户交易结果。
     * 使用此接口的用户必须签订了借记卡快捷、无密快捷、无密投资、无密还款等协议之一。
     * P2P平台需要上送用户账户号、标的号等字段，托管平台实现从用户账户到标的账户的资金划转。
     * 交易成功，使用“标的交易通知”告知商户结果。
     * @return
     */
	public static CommonResponse TransferWithAgreementInterfate(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		if(commonRequst.getCustMemberType()!=null){			
			commonRequst.setLoanAccType(commonRequst.getCustMemberType());
		}
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付投标类无密标的接口调用");
			UMPay umpay= new UMPay();
			umpay=generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_NOPWDTRANSFER);
			//获取流标订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			String merDate=sf.format(new Date());
			umpay.setMer_date(merDate);
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidType()+commonRequst.getBidId());//
			//标的转账方向(收入方向)
			umpay.setTrans_action(UMPay.DIRECTION_CUSTOMER);
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());//发起转账操作的投资人和借款人的第三方标识账号
			if(null != commonRequst.getAccountType() && "0".equals(commonRequst.getAccountType().toString())){
				commonRequst.setCustMemberType(UMPay.PARTIPAY_PERSON);
			}else if(null != commonRequst.getAccountType() && "1".equals(commonRequst.getAccountType().toString())){
				commonRequst.setCustMemberType(UMPay.PARTIPAY_PUBLIC);
			}
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID) || commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//企业户投标
				umpay.setServ_type(UMPay.BUSSINESS_INVEST);//无密投标
				umpay.setPartic_type(UMPay.TRABSFER_INVEST);//转账方类型-投资人
				//转账方账户类型
				if(commonRequst.getCustMemberType().equals(UMPay.PARTIPAY_PUBLIC)){//对公还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
					//umpay.setMer_id(commonRequst.getThirdPayConfigId());
					umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}else{//对私还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
			  }	
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//借款人无密码还款
				umpay.setServ_type(UMPay.BUSSINESS_REPAYMENT);//标的转账接口业务类型 还款
				umpay.setPartic_type(UMPay.TRABSFER_LOANER);//转账方类型-借款人
				umpay.setPartic_acc_type("01");//还款方是借款人
				if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//对公还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
					umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}else{//对私还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}	
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//企业户购买债权
				umpay.setServ_type(UMPay.BUSSINESS_DEALINVEST);
				umpay.setPartic_type(UMPay.TRABSFER_INVEST);//转账方类型-投资人
				//转账方账户类型
				if(commonRequst.getCustMemberType().equals(UMPay.PARTIPAY_PUBLIC)){//对公还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
					umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}else{//对私还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
			  }	
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//提前还款
				umpay.setServ_type(UMPay.BUSSINESS_REPAYMENT);//标的转账接口业务类型 还款
				umpay.setPartic_type(UMPay.TRABSFER_LOANER);//转账方类型-借款人
				umpay.setPartic_acc_type("01");//还款方是借款人
				if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//对公还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
					umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}else{//对私还款
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
					umpay.setLoan_acc_type(commonRequst.getLoanAccType());
				}	
			}
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付投标类无密码转账接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付投标类无密标的接口生成的签名sign=="+sign);
			String ret=ThirdPayWebClient.getWebContentByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付投标类无密标的接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=parseHTMLMethod(ret);
			Boolean isSign=true;//verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			commonResponse.setRequestInfo(htmlReturnMap.toString());
			logger.info("联动优势支付投标类无密标的接口调用后收到的通知后验证签名结果isSign="+isSign);
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setCommonRequst(commonRequst);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setCommonRequst(commonRequst);
				}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付投标类无密码转账接口调用出错,原因:"+e.getMessage());
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付投标类无密码转账接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}

	/**
	 * 直连接口03
	 * 联动优势支付非投标类无密码转账接口
	 * 主要针对平台转账给投资人（无密码操作）
	 * @param map
	 * @return
	 */
	public static CommonResponse normalNOPassWordTransferInterface(CommonRequst commonRequst) {
		CommonResponse response = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付非投标类无密码转账调用");
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_PLATFORMTRANSFER);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sf.format(new Date()));
			if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业
				//对公账户转账（企业用户）
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
			}else{
				//对私账户转账（个人用户）
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
			}
			//转账方向
			umpay.setTrans_action(UMPay.DIRECTION_PLATFORM);
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取需要转账的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付非投标类无密码转账调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付非投标类无密码转账生成的签名sign=="+sign);
			String ret=ThirdPayWebClient.getUndecodeByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付非投标类无密码转账调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付非投标类无密码转账调用后收到的通知后验证签名结果isSign="+isSign);
			response.setRequestInfo(htmlReturnMap.toString());
			if(true){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
				}
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("平台转账交易签名验证失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			response.setResponseMsg("联动优势支付非投标类无密码转账调用出错");
		}
		return response;
	}
	
	/**
	 * 网关接口08
	 * 联动优势支付企业客户充值接口
	 * @param map
	 * @return
	 */
	public  static CommonResponse enterPriseRecharge(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付企业客户有密码充值接口调用");
			UMPay umpay= new UMPay();
			//umpay.setWithdraw_mer_id(commonRequst.getThirdPayConfigId());
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.qiyekehuData(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.qiyekehuData(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_ENPRISE_RECHARGE);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			umpay.setGate_id(commonRequst.getBankCode());
			if(commonRequst.getBankType().equals(UMPay.BANK_ENTERPRISE)){
				umpay.setPay_type(UMPay.BANK_ENTERPRISE);
			} else {
		        umpay.setPay_type(UMPay.BANK_PERSON);
		    }
			//生成订单日期
			umpay.setMer_date(sf.format(new Date()));
			if(commonRequst.getThirdPayConfigId()!=null){
				umpay.setRecharge_mer_id(commonRequst.getThirdPayConfigId());
			}else{
				umpay.setRecharge_mer_id(umpay.getMer_id());
			}
			
			umpay.setAccount_type("01");
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			//读取进行充值的银行卡
			umpay.setGate_id(commonRequst.getBankCode());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付个人有密码充值接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付个人有密码充值接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付个人有密码充值接口调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			if(ret!=null&&ret.length>1){
				if(ret[0]!=null&&ret[0].equals("SUCCESS")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("企业充值申请提交成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("企业充值申请提交失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("接口调用异常");
			}
			commonResponse.setCommonRequst(commonRequst);
		}catch(Exception e){
			logger.info("联动优势支付充值接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付充值接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	
	/**
	 * 网关接口02
	 * 联动优势支付取现接口
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("bathpath",String);//当前系统访问地址
	 * map.put("requestNo",String);//取现交易记录流水号
	 * map.put("orderDate",String);//生成取现订单的日期
	 * map.put("thirdPayflagId",String);//取现客户的联动用户号
	 * map.put("thirdPayflagId0",String);//取现客户的联动账户号
	 * map.put("amount",BigDecimal);//取现用户取现金额
	 * @param map
	 * @return
	 */
	public static CommonResponse toWithdraw(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付个人有密码取现接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_WITHDRAWRECHARGE);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			String merDate=sf.format(commonRequst.getTransactionTime());
			umpay.setMer_date(merDate);
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			//读取联动给注册用户开的资金账户
			umpay.setAccount_id(commonRequst.getThirdPayConfigId0());
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付个人有密码取现接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付个人有密码取现接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付个人有密码取现接口调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			if(ret!=null&&ret.length>1){
				if(ret[0]!=null&&ret[0].equals("SUCCESS")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("申请提现成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("申请提现失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("接口调用异常");
			}
			commonResponse.setCommonRequst(commonRequst);
		}catch(Exception e){
			logger.info("联动优势支付充值接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付充值接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	
	/**
	 * 直连接口09
	 * 联动优势支付企业户取现接口
	 * （主要是针对企业户和平台账户）
	 * @param map
	 * @return
	 */
	public static CommonResponse EnterpriseWithdraw(CommonRequst commonRequst) {
		Object[] backData=new Object[3];
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			logger.info("联动优势支付企业户取现接口调用");
			UMPay umpay= new UMPay();
			umpay.setWithdraw_mer_id(commonRequst.getThirdPayConfigId());
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,false,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,false,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_ENPRISE_WITHDARW);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
			umpay.setMer_date(sf.format(new Date()));
				//读取联动给注册企业用户开的用户账号
				umpay.setWithdraw_mer_id(commonRequst.getThirdPayConfigId());
			
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付企业户取现接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付企业户取现接口生成的签名sign=="+sign);
			logger.info("联动优势支付企业户取现接口调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付企业户取现接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=parseHTMLMethod(ret);
			Boolean isSign=verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付企业户取现接口调用后收到的通知后验证签名结果isSign="+isSign);
			if(true){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("提现申请成功,等待第三方确认");
					commonResponse.setCommonRequst(commonRequst);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			logger.info("联动优势支付取现接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付企业取现接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	/**
	 * 网关接口03
	 * 联动优势支付绑定银行卡接口
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("bathpath",String);//当前系统访问地址
	 * map.put("requestNo",String);//绑卡记录流水号
	 * map.put("orderDate",String);//生成绑卡记录的日期
	 * map.put("thirdPayflagId",String);//绑卡客户的联动用户号
	 * map.put("cardCode",String);//绑卡客户的身份证号码
	 * map.put("custmerName",BigDecimal);//绑卡用户姓名
	 * map.put("bankCardNumber",BigDecimal);//绑卡用户银行卡号
	 * @param map
	 * @return
	 */
	public static CommonResponse toBindBankCard(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付个人客户银行卡绑定接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_BINDCARD);
			//umpay.setNotify_url(map.get("bathpath").toString()+configMap.get("thirdPay_umpay_notifyUrl").toString());
			//umpay.setRet_url(map.get("bathpath").toString()+configMap.get("thirdPay_umpay_pageUrl").toString());
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(format.format(commonRequst.getTransactionTime()));
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			//进行绑卡人选择证件类型，默认身份证
			umpay.setIdentity_type(UMPay.IDENTITY_CARD);
			//进行绑卡人的身份证号码
			String cardCode=UMPayInterfaceUtil.Encrypt(commonRequst.getCardNumber(),UMPay.GBK);
			umpay.setIdentity_code(cardCode);
			//进行绑卡人的银行卡开户名
			String userName=UMPayInterfaceUtil.Encrypt(commonRequst.getTrueName(),UMPay.GBK);
			umpay.setAccount_name(userName);
			//进行绑卡的银行卡卡号
			String bankCardNumber=UMPayInterfaceUtil.Encrypt(commonRequst.getBankCardNumber(),UMPay.GBK);
			umpay.setCard_id(bankCardNumber);
			//是否开启快捷支付（默认不开启页面也不显示）
			umpay.setIs_open_fastPayment(UMPay.FASTPAYMENT_NO);
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付个人客户银行卡绑定接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			logger.info("联动优势支付个人客户银行卡绑定接口调用生成的签名sign=="+sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			commonResponse.setRequestInfo(ret[1]);
			if(ret!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(ret[0])){
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				commonResponse.setResponseMsg("个人绑定银行卡申请成功");
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("个人绑定银行卡申请失败");
			}
			
			return commonResponse;
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("个人绑定银行卡申请对接失败");
			logger.info("联动优势支付个人客户银行卡绑定接口调用出错,原因:"+e.getMessage());
			return commonResponse;
		}
	}
	
	/**
	 * 更换银行卡接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse toChangeBankCard(CommonRequst commonRequst) {
		CommonResponse cr = new CommonResponse();
		cr.setCommonRequst(commonRequst);
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付个人客户更换银行卡接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_REPLACECARD);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(format.format(commonRequst.getTransactionTime()));
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			//进行绑卡人选择证件类型，默认身份证
			umpay.setIdentity_type(UMPay.IDENTITY_CARD);
			//进行绑卡人的身份证号码
			String cardCode=UMPayInterfaceUtil.Encrypt(commonRequst.getCardNumber(),UMPay.GBK);
			umpay.setIdentity_code(cardCode);
			//进行绑卡人的银行卡开户名
			String userName=UMPayInterfaceUtil.Encrypt(commonRequst.getTrueName(),UMPay.GBK);
			umpay.setAccount_name(userName);
			//进行绑卡的银行卡卡号
			String bankCardNumber=UMPayInterfaceUtil.Encrypt(commonRequst.getNewBankCardNumber(),UMPay.GBK);
			umpay.setCard_id(bankCardNumber);
			
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付个人客户更换银行卡接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付个人客户更换银行卡接口调用生成的签名sign=="+sign);
			cr.setRequestInfo(ret[1]);
			if(ret!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(ret[0])){
				cr.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				cr.setResponseMsg("个人更换银行卡申请成功");
			}else{
				cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				cr.setResponseMsg("个人更换银行卡申请失败");
			}
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			cr.setResponseMsg("个人更换银行卡对接失败");
			logger.info("联动优势支付个人客户更换银行卡接口调用出错,原因:"+e.getMessage());
			return cr;
		}
	}
	
	/**
	 * 网关接口04
	 * 联动优势支付无密交易授权接口
	 * 涉及（投标授权，还款授权，还有无密码快捷交易即指）
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("bathPath",String);//当前系统访问地址
	 * map.put("thirdPayflagId",String);//做授权的第三方支付用户号
	 * map.put("thirdPayflagId0",String);//做授权的第三方支付账号
	 * map.put("autoAuthorizationType",String);//做授权的交易类型  invest  自动投标授权     repayment 还款授权    nopassword 无密充值授权
	 * @param map
	 * @return
	 */
	public static CommonResponse autoAuthorization(CommonRequst commonRequst) {
		CommonResponse cr = new CommonResponse();
		cr.setCommonRequst(commonRequst);
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付无密交易授权接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_NOPASSWORD);
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			//授权类型
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//无密投标授权
				umpay.setUser_bind_agreement_list(UMPay.NO_PASSWORD_INVST);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//无密还款授权
				umpay.setUser_bind_agreement_list(UMPay.NO_PASSWORD_REPAYMENT);
			}
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付无密交易授权接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			System.out.println("ret[1]=="+ret[1]);
			logger.info("联动优势支付无密交易授权接口调用生成的签名sign=="+sign);
			cr.setRequestInfo(ret[1]);
			if(null != ret && ThirdPayConstants.RECOD_SUCCESS.equals(ret[0])){
				cr.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				cr.setResponseMsg("无密交易授权开通成功");
			}else{
				cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				cr.setResponseMsg("无密交易授权开通失败");
			}
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			cr.setResponseMsg("无密交易授权接口对接失败");
			logger.info("联动优势支付无密交易授权接口调用出错,原因:"+e.getMessage());
			return cr;
		}
	}
	
	/**
	 * 网关接口05
	 * 联动优势支付取消无密交易授权接口
	 * 涉及（投标授权，还款授权，还有无密码快捷交易即指）
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("bathPath",String);//当前系统访问地址
	 * map.put("thirdPayflagId",String);//做授权的第三方支付用户号
	 * map.put("thirdPayflagId0",String);//做授权的第三方支付账号
	 * map.put("autoAuthorizationType",String);//做授权的交易类型  invest  自动投标授权     repayment 还款授权    nopassword 无密充值授权
	 * @param map
	 * @return
	 */
	public static CommonResponse cancelAuthorization(CommonRequst commonRequst) {
		CommonResponse cr = new CommonResponse();
		cr.setCommonRequst(commonRequst);
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付取消无密交易授权接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_CANCELNOPASSWORD);
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭无密投标授权
				umpay.setUser_unbind_agreement_list(UMPay.NO_PASSWORD_INVST);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//关闭无密还款授权
				umpay.setUser_unbind_agreement_list(UMPay.NO_PASSWORD_REPAYMENT);
			}
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付取消无密交易授权接口调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付取消无密交易授权接口调用生成的签名sign=="+sign);
			cr.setRequestInfo(ret[1]);
			if(null != ret && ThirdPayConstants.RECOD_SUCCESS.equals(ret[0])){
				cr.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				cr.setResponseMsg("取消无密交易授权成功");
			}else{
				cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				cr.setResponseMsg("取消无密交易授权失败");
			}
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付取消无密交易授权接口调用出错,原因:"+e.getMessage());
			cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			cr.setResponseMsg("取消无密交易授权对接失败");
			return cr;
		}
	}
	
	/**
	 * 网关接口06
	 * 联动优势支付非投标类有密码转账接口
	 * 涉及（个人向平台转账）
	 * @param map
	 * @return
	 */
	public static CommonResponse normalTransferInterface(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付非投标类有密码转账接口调用");
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			}
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sdf.format(new Date()));
			umpay.setService(UMPay.SER_NOMALTRANSFER);
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取转账客户类型
			umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
			//读取需要转账的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付非投标类有密码转账接口生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付非投标类有密码转账接口调用生成的签名sign=="+sign);
			if(null != ret && ThirdPayConstants.RECOD_SUCCESS.equals(ret[0])){
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				commonResponse.setResponseMsg("取消无密交易授权成功");
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("取消无密交易授权失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付非投标类有密码转账接口调用出错");
			logger.info("联动优势支付非投标类有密码转账接口调用出错,原因:"+e.getMessage());
		}
		return commonResponse;
	}
	
	/**
	 * 网关接口07
	 * 联动优势支付投标类有密码转账接口
	 * 涉及（涉及投资人投资，借款人还款）
     * Map<String,object> map  第三方支付投标需要的map参数
	 * map.put("basePath",String) 只当前的绝对路径
	 * map.put("requestNo",string)交易流水号
	 * map.put("orderDate",String)生成交易订单日期
	 * map.put("amount",BigDecimal) 投标金额
	 * map.put("bidPlanId",Long) 标的id
	 * map.put("bidType",String) 标的类型
	 * map.put("thirdPayflagId",String)转账人的联动优势支付开户账号
	 * map.put("transferUserType",String)转账方账户类型
	 * map.put("businessType",String)转账的类型属于投资还是还款
	 * @param map
	 * @return
	 */
	public static Object[] transferInterface1(CommonRequst commonRequst) {

		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			logger.info("联动优势支付投标类有密码转账接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_TRANSFER);
			
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sdf.format(commonRequst.getTransactionTime()));
			
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(/*map.get("bidType").toString()+"-"+*/commonRequst.getBidId());
			//业务类型
			String bussinessType="";
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BUSSINESSTYPE_BID)){
				bussinessType="01";
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BUSSINESSTYPE_LOANERREPAYMENT)){
				bussinessType="03";
			}
			umpay.setServ_type(bussinessType);
			//转账方类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
			umpay.setPartic_type(commonRequst.getCustMemberType());
			//标的转入
			umpay.setTrans_action(UMPay.DIRECTION_CUSTOMER);
			//转账方账户类型
			umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
			
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付投标类有密码转账调用生成的传输参数params=="+params);
			Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			htmlmap.put("sign", sign);
			String[] ret= ThirdPayWebClient.operateParameter(configMap.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
			logger.info("联动优势支付支付投标类有密码转账接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付支付投标类有密码转账接口调用调用的url=="+configMap.get("thirdPay_umpay_URL").toString());
			System.out.println("ret[1]===="+ret[1]);
			backData[0]=ret[0];
			backData[1]=ret[1];
			backData[2]=commonRequst;
			return backData;
		}catch(Exception e){
			logger.info("联动优势支付支付投标类有密码转账接口调用出错,原因:"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	
	}

	//######################################网关接口（结束）#############################################
	
	//######################################直连接口（开始）#############################################
	
	
	/**
	 * 直连01
	 * 联动优势支付注册接口
	 * @param map
	 * @return
	 */
	public static CommonResponse regiest(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			logger.info("联动优势支付注册接口调用");
			Map thirdPayConfig=umProperty();
			UMPay umpay= new UMPay();
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,true,commonRequst.getBussinessType());
			}else{
				umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			}
			umpay.setService(UMPay.SER_REGIST);//接口名称
			umpay.setIdentity_type(UMPay.IDENTITY_CARD);//证件类型
			umpay.setEmail(commonRequst.getEmail());//邮箱
			umpay.setMobile_id(commonRequst.getTelephone());//手机号
			String cardCode=UMPayInterfaceUtil.Encrypt(commonRequst.getCardNumber(),UMPay.GBK);
			umpay.setIdentity_code(cardCode);//证件号
			String userName=UMPayInterfaceUtil.Encrypt(commonRequst.getTrueName(),UMPay.GBK);
			umpay.setMer_cust_name(userName);//用户姓名
			umpay.setMer_cust_id("TG"+commonRequst.getCardNumber()+commonRequst.getCustMemberId());//商户用户唯一标识
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			String ret=ThirdPayWebClient.getUndecodeByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付注册接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付注册接口调用后收到的通知后验证签名结果isSign="+isSign);
			commonResponse.setRequestInfo(ret);
			if(isSign){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setThirdPayConfig(ThirdPayConstants.UMPAY);
					commonResponse.setThirdPayConfigId(htmlReturnMap.get("user_id").toString());
					commonResponse.setThirdPayConfigId0(htmlReturnMap.get("account_id").toString());
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("个人用户注册签名验证失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付注册接口调用出错");
		}
		return commonResponse;
	}
	
	
	/**
	 * 直连接口02
	 * 联动优势支付账户查询接口
	 * @param map
	 * @return
	 */
	public static CommonResponse queryCustmerInfo(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			logger.info("联动优势支付账户查询接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_QUERYCUSTOMER);
			//读取联动给注册用户开的用户账号
			umpay.setUser_id(commonRequst.getThirdPayConfigId());
			umpay.setIs_find_account(UMPay.IS_HAVESELECT);
			umpay.setIs_select_agreement(UMPay.IS_HAVEAUTHORIZATION);
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付账户查询接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付支付账户查询接口生成的签名sign=="+sign);
			String ret=ThirdPayWebClient.getUndecodeByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付注册接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付支付账户查询接口调用后收到的通知后验证签名结果isSign="+isSign);
			if(isSign){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("查询成功");
					commonResponse.setCustmemberStatus("已激活");
					if(htmlReturnMap.containsKey("balance")){
						String moneyChange=new BigDecimal(htmlReturnMap.get("balance")).divide(new BigDecimal(100)).toString();
						commonResponse.setBalance(new BigDecimal(moneyChange));
					}
					if(htmlReturnMap.containsKey("card_id")){
						commonResponse.setBankCode(htmlReturnMap.get("card_id"));
						commonResponse.setBindBankStatus("已认证"); 
						commonResponse.setBankName(htmlReturnMap.get("gate_id"));
					}
					if(htmlReturnMap.containsKey("user_bind_agreement_list")){
						String agreementlist=htmlReturnMap.get("user_bind_agreement_list");
						String agreementName="开启无密交易：";
						String[] splitAgreementlist=agreementlist.split("|");
						if(splitAgreementlist.length>0){
							for(String temp:splitAgreementlist){
								if(temp.equals(UMPay.NO_PASSWORD_INVST)){
									agreementName=agreementName+"自动投标，";
									commonResponse.setAutoTender("true");
								}else if(temp.equals(UMPay.NO_PASSWORD_REPAYMENT)){
									agreementName=agreementName+"自动还款，";
								}
							}
						}
						htmlReturnMap.put("user_bind_agreement_list", agreementName);
					}
					if(htmlReturnMap.containsKey("mail_addr")){
						htmlReturnMap.put("mail", htmlReturnMap.get("mail_addr"));
					}
					htmlReturnMap.put("mer_cust_name", htmlReturnMap.get("cust_name"));
					htmlReturnMap.put("user_id", htmlReturnMap.get("plat_user_id"));
					if(htmlReturnMap.get("account_state").equals("1")){
						htmlReturnMap.put("account_state", "账户正常");
					}else if(htmlReturnMap.get("account_state").equals("2")){
						htmlReturnMap.put("account_state", "账户已挂失");
					}else if(htmlReturnMap.get("account_state").equals("3")){
						htmlReturnMap.put("account_state", "账户已冻结");
					}else if(htmlReturnMap.get("account_state").equals("4")){
						htmlReturnMap.put("account_state",  "账户已锁定");
					}else if(htmlReturnMap.get("account_state").equals("9")){
						htmlReturnMap.put("account_state", "已销户");
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("查询失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("签名验证失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付账户查询接口调用出错,原因:"+e.getMessage());
		}
		return commonResponse;
	}
	
	/**
	 * 直连接口03
	 * 联动优势支付非投标类无密码转账接口
	 * 主要针对平台转账给投资人（无密码操作）
	 * @param map
	 * @return
	 */
	public static CommonResponse normalNOPassWordTransferInterface1(CommonRequst commonRequst) {
		CommonResponse response = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付非投标类无密码转账调用");
			Object[] backData=new Object[2];
			UMPay umpay= new UMPay();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_PLATFORMTRANSFER);
			//获取充值订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			umpay.setMer_date(sdf.format(new Date()));
			//对私账户转账（个人用户）
			if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
			}else{
				umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
			}
			//转账方向
			umpay.setTrans_action(UMPay.DIRECTION_CUSTOMER);
			//读取联动给注册用户开的用户账号
			umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			//读取需要转账的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付支付非投标类无密码转账调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付非投标类无密码转账生成的签名sign=="+sign);
			logger.info("联动优势支付非投标类无密码转账调用调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			//企业
			if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){
				String ret=ThirdPayWebClient.getWebContentByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
				logger.info("联动优势支付非投标类无密码转账调用后收到的通知"+ret);
				Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
				Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
				System.out.println("验证签名isSign=="+isSign);
				logger.info("联动优势支付非投标类无密码转账调用后收到的通知后验证签名结果isSign="+isSign);
					if(htmlReturnMap.get("ret_code").equals("0000")){
						response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
						response.setCommonRequst(commonRequst);
					}else{
						response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
						response.setCommonRequst(commonRequst);
					}
			}else{//个人
				umpay.setService("transfer_asyn");
				System.out.println("生成的签名sign=="+sign);
				Map<String,String> htmlmap=UMPayInterfaceUtil.createHtmlMap(umpay);
				System.out.println("生成的签名sign=="+sign);
				htmlmap.put("sign", sign);
				String[] ret= ThirdPayWebClient.operateParameter(thirdPayConfig.get("thirdPay_umpay_URL").toString(),htmlmap,UMPay.UTF8);
				logger.info("联动优势支付非投标类有密码转账接口调用生成的签名sign=="+sign);
				logger.info("联动优势支付非投标类有密码转账接口调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			    if(ret[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
				   response.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					response.setResponseMsg("代偿还款申请提交成功");
					response.setCommonRequst(commonRequst);
			   }else{
				   response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("代偿还款申请提交失败");
					response.setCommonRequst(commonRequst);
			   }
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付非投标类无密码转账调用出错,原因:"+e.getMessage());
			response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			response.setResponseMsg("联动优势支付非投标类无密码转账调用出错");
			response.setCommonRequst(commonRequst);
		}
	return response;
	}
	
	/**
	 * 直连接口04
	 * 联动优势支付发标接口
	 * 主要针对erp发标接口（联动优势建立标的账户）
	 * @param map
	 * @return
	 */
	public static Object[] createBidAccount(CommonRequst commonRequst) {
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付发标接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_CREATBIDACCOUNT);
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidId());
			umpay.setProject_name(commonRequst.getBidName());
			//读取需要标的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setProject_amount(amount.toString());
			//读取借款人联动优势给借款人开通的第三方账号
			umpay.setLoan_user_id(commonRequst.getLoaner_thirdPayflagId());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付发标接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付发标接口生成的签名sign=="+sign);
			logger.info("联动优势支付发标接口调用的url=="+configMap.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(configMap.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付发标接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付发标接口调用后收到的通知后验证签名结果isSign="+isSign);
			if(true){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					backData[0]=ThirdPayConstants.RECOD_SUCCESS;
					backData[1]=htmlReturnMap;
					backData[2]=commonRequst;
					
				}else{
					backData[0]=ThirdPayConstants.RECOD_FAILD;
					backData[1]=htmlReturnMap.get("ret_msg").toString();
					backData[2]=null;
				}
			}else{
				backData[0]=ThirdPayConstants.RECOD_FAILD;
				backData[1]="签名验证失败";
				backData[2]=null;
			}
			return backData;
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付发标接口调用出错,原因:"+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 直连接口05
	 * 联动优势支付更新标接口
	 * 主要针对erp修改标接口（联动优势更新标的账户）
	 * @param map
	 * @return
	 */
	public static CommonResponse UpdateBidAccount(CommonRequst commonRequst) {
		CommonResponse commonResponse = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付更新标接口调用");
			Object[] backData=new Object[3];
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_UPDATEBIDACCOUNT);
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidType()+commonRequst.getBidId());
			umpay.setChange_type(UMPay.UPDATE_TYPE1);
			umpay.setProject_state(commonRequst.getBidIdStatus());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付更新标接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付更新标接口生成的签名sign=="+sign);
			logger.info("联动优势支付发标接口调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付更新标接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付更新标接口调用后收到的通知后验证签名结果isSign="+isSign);
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("标的状态更改成功");
					commonResponse.setCommonRequst(commonRequst);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setCommonRequst(commonRequst);
				}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付更新标接口调用出错,原因:"+e.getMessage());
		}
		return commonResponse;
	}
	
	
	/**
	 * 直接接口06
	 * 联动优势支付投标类无密码转账接口
	 * 主要针对投标类（账户转出）
	 * P2P平台发起的标的转出（对应业务场景为流标后还款、满标后缴费或放款、还款后返款、偿付后返款、债权转让的返款、撤资后的返款）请求，并完成标的账户资金划转到其他客户账户和相应记录的更新
	 * @param map
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("requestNo",String); //流水号
	 * map.put("orderDate",String);//交易日期
	 * map.put("bidType",String);//标的类型
	 * map.put("bidPlanId",String);//标的主键id
	 * map.put("businessType",String);//业务类型
	 * map.put("transferType",String);//转账方类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
	 * map.put("transferUserType",String);//转账方账户类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
	 * map.put("thirdPayflagId"，String);////联动给注册用户开的用户账号
	 * map.put("transferDirection"，String);//标的转账方向
	 * map.put("amount"，BigDecimal);//转账金额  单位元
	 * @return
	 */
	public static CommonResponse NoPasswordTransferInterface(CommonRequst commonRequst) {
		Object[] backData=new Object[3];
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
			logger.info("联动优势支付投标类无密码转账接口调用");
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,true,true,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_TRANSFER);
			//获取流标订单号
			umpay.setOrder_id(commonRequst.getRequsetNo());
			//生成订单日期
			String merDate=sdf.format(new Date());
			umpay.setMer_date(merDate);
			//发标时间标的主键Id和标的标识
			umpay.setProject_id(commonRequst.getBidType()+commonRequst.getBidId());
			//转账方类型（取值范围：01投资者02融资人03 P2P平台04担保方05资金使用方）
			//流标返款（从标的账户将钱返回给投资人）
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELUSER)){
				//读取联动给注册用户开的用户账号
				umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
				umpay.setServ_type(UMPay.BUSSINESS_BIDFAILE);//标的转账接口业务类型（流标返款）
				umpay.setPartic_type(UMPay.TRABSFER_INVEST);//收款方类型
				//标的转账方向(支出方向)
				umpay.setTrans_action(UMPay.DIRECTION_PLATFORM);
				if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){
					umpay.setPartic_acc_type("02");//流标收款人是企业用户
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PUBLIC);
				}else{
					umpay.setPartic_acc_type("01");//流标收款人是个人用户
					umpay.setPartic_acc_type(UMPay.PARTIPAY_PERSON);
				}
			}
			//读取需要充值的金额（联动交易金额是以分为单位）
			BigDecimal  amount=commonRequst.getAmount();
			amount=amount.multiply(new BigDecimal(100)).setScale(0);
			umpay.setAmount(amount.toString());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付投标类无密码转账接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付投标类无密码转账接口生成的签名sign=="+sign);
			String ret=ThirdPayWebClient.getUndecodeByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付投标类无密码转账接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付投标类无密码转账接口调用后收到的通知后验证签名结果isSign="+isSign);
				if(htmlReturnMap.get("ret_code").equals("0000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("交易成功");
					commonResponse.setCommonRequst(commonRequst);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					commonResponse.setCommonRequst(commonRequst);
				}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付投标类无密码转账接口调用出错,原因:"+e.getMessage());
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("联动优势支付投标类无密码转账接口调用出错");
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	/**
	 * 直接接口07  下发注册验证码
	 * @param commonRequst
	 * @return
	 */
	public static Object[]  sendcheckCode(CommonRequst commonRequst){
		Object[] backData=new Object[3];
		try{
			logger.info("联动优势下发验证码接口调用");
			UMPay umpay= new UMPay();
			umpay.setService(UMPay.SER_SENDCODE);
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setMobile_id(commonRequst.getTelephone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
			//生成订单日期
			String merDate=sdf.format(new Date());
			umpay.setMer_date(merDate);
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势下发验证码接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付注册接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付注册接口调用调用的url=="+configMap.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(configMap.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付注册接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付注册接口调用后收到的通知后验证签名结果isSign="+isSign);
			if(true){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					backData[0]=ThirdPayConstants.RECOD_SUCCESS;
					commonRequst.setRequsetNo(htmlReturnMap.get("sms_trace"));
					backData[1]="下发验证码成功";
					backData[2]=commonRequst;
				}else{
					backData[0]=ThirdPayConstants.RECOD_FAILD;
					backData[1]=htmlReturnMap.get("ret_msg").toString();
					backData[2]=null;
				}
			}else{
				backData[0]=ThirdPayConstants.RECOD_FAILD;
				backData[1]="签名验证失败";
				backData[2]=null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付注册接口调用出错,原因:"+e.getMessage());
			backData[0]=ThirdPayConstants.RECOD_FAILD;
			backData[1]="联动优势下发验证码调用出错";
			backData[2]=null;
		}
		return backData;
	}
	
	/**
	 * 直连01
	 * 联动优势支付注册接口
	 * @param map
	 * @return
	 */
	public static Object[] checkCodeRegiest(CommonRequst commonRequst) {
		Object[] backData=new Object[3];
		try{
			logger.info("联动优势验证码注册接口调用");
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			umpay.setService(UMPay.SER_CHECKCODEREGISTER);
			umpay.setIdentity_type(UMPay.IDENTITY_CARD);
			umpay.setEmail(commonRequst.getEmail());
			umpay.setMobile_id(commonRequst.getTelephone());
			//产生验证码成功的流水号
			umpay.setSms_trace(commonRequst.getRequsetNo());
			//下发验证码
			umpay.setVerification_code(commonRequst.getCheckCode());
			String cardCode=UMPayInterfaceUtil.Encrypt(commonRequst.getCardNumber(),UMPay.GBK);
			umpay.setIdentity_code(cardCode);
			String userName=UMPayInterfaceUtil.Encrypt(commonRequst.getTrueName(),UMPay.GBK);
			umpay.setMer_cust_name(userName);
			umpay.setMer_cust_id("TG"+commonRequst.getCardNumber()+commonRequst.getCustMemberId());
			System.out.println(umpay.getMer_cust_id());
			Map<String,String> returnMap=UMPayInterfaceUtil.creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付注册接口调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付注册接口调用生成的签名sign=="+sign);
			logger.info("联动优势支付注册接口调用调用的url=="+configMap.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(configMap.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付注册接口调用后收到的通知"+ret);
			Map<String,String> htmlReturnMap=UMPayInterfaceUtil.parseHTMLMethod(ret);
			Boolean isSign=UMPayInterfaceUtil.verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付注册接口调用后收到的通知后验证签名结果isSign="+isSign);
			if(true){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					backData[0]=ThirdPayConstants.RECOD_SUCCESS;
					commonRequst.setThirdPayConfig(ThirdPayConstants.UMPAY);
					commonRequst.setThirdPayConfigId(htmlReturnMap.get("user_id").toString());
					commonRequst.setThirdPayConfigId0(htmlReturnMap.get("account_id").toString());
					backData[1]="注册成功";
					backData[2]=commonRequst;
				}else{
					backData[0]=ThirdPayConstants.RECOD_FAILD;
					backData[1]=htmlReturnMap.get("ret_msg").toString();
					backData[2]=null;
				}
			}else{
				backData[0]=ThirdPayConstants.RECOD_FAILD;
				backData[1]="签名验证失败";
				backData[2]=null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势支付注册接口调用出错,原因:"+e.getMessage());
			backData[0]=ThirdPayConstants.RECOD_FAILD;
			backData[1]="联动优势支付注册接口调用出错";
			backData[2]=null;
		}
		return backData;
	}
	//######################################直连接口（结束）#############################################
	/**
	 * 公共方法
	 * 将回调通知（页面回调和服务器端回调）收到的request转换成map对象 
	 */
	private static Map createResponseMap(HttpServletRequest request){
		/**
		 * 准备将回调通知参数整合成map
		 */
		Map<String,String> map =new HashMap<String,String>();
		Enumeration paramEnu=request.getParameterNames();
		String parameter="";
    	while(paramEnu.hasMoreElements()){
    		String paramName=(String)paramEnu.nextElement();
    		String paramValue=(String)request.getParameter(paramName);
    		map.put(paramName, paramValue);
    		logger.info("联动优势回调通知方法收到的参数记录："+paramName+"="+paramValue);
    		parameter=parameter+(paramName+"="+paramValue+"&");
    		logger.info("联动优势回调函数通知方法收到的参数记录连接："+paramName+"="+paramValue);
    	}
    	logger.info("联动优势回调函数通知方法收到的参数记录parameter:"+parameter);
		return map;
	};
	
	/**
	 * 页面回调
	 * 通知业务数据处理
	 */
	public static CommonResponse pageCallBackOprate(HttpServletRequest request) {
		//业务类型(每个接口多种使用方式系统添加参数)
		//默认回调通知参数实体
		CommonResponse commonResponse=new CommonResponse();
		//系统调用第三方回调的url中默认携带参数
		String bussinessType=request.getParameter("HRBT");
		request.removeAttribute("HRBT");//将我们默认传递的参数从request中移除
		//将业务参数返回给业务层
		commonResponse.setBussinessType(bussinessType);
		try{
			logger.info("联动优势页面回调函数通知方法");
			//用来标识是否完成
			
			/**
			 * 准备将回调通知参数整合成map
			 */
			Map<String,String> map =createResponseMap(request);
	    	if(!map.isEmpty()){
	    		Boolean isSign=false;
	    		//联动优势验证签名方法
	    		logger.info("联动优势页面回调函数通知签名验证结果isSign="+isSign);
	    		if(true){
	    			String service=request.getParameter("service");
	    			if(service!=null&&!"".equals(service)){//其他业务处理方法
	    				if(service.equals(UMPay.NOTIFY_RECHARGE)){//充值交易回调通知
	    					logger.info("联动优势服务器端回调函数通知调用充值业务操作方法开始");
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
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
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    							commonResponse.setResponseMsg("取现成功");
	    						}else{
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    							commonResponse.setResponseMsg("取现失败");
	    						}
	    						requestValue.remove(map.get("order_id"));
	    					}
	    				}else if(service.equals(UMPay.NOTIFY_BINDCARD)){//绑卡回调通知
	    					logger.info("联动优势服务器端回调函数通知调用绑卡业务操作方法开始");
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    							if(map.containsKey("last_four_cardid")){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("绑卡成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
		    							commonResponse.setResponseMsg("绑卡受理中，等待时间大约2个小时");
	    							}
	    						}else{
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    							commonResponse.setResponseMsg("绑卡失败");
	    						}
	    						requestValue.remove(map.get("order_id"));
	    					}
	    					
	    				}/*else if(service.equals(UMPay.NOTIFY_BINDCARD)){//更换银行卡回调通知（第一次申请结果）
	    					
	    				}else if(service.equals(UMPay.NOTIFY_BINDCARD)){//更换银行卡回调通知（第二次结果通知）
	    					
	    				}*/else if(service.equals(UMPay.NOTIFY_BINDAGERRMENT)){//无密交易授权回调通知
	    					logger.info("联动优势服务器端回调函数通知页面回调函数通知调用无密交易授权业务操作方法开始");
	    					String[] ret=new String[2];
	    					// TODO Auto-generated method stub
	    					if(!requestValue.containsKey(map.get("user_bind_agreement_list"))){
	    						requestValue.put(map.get("user_bind_agreement_list")+map.get("user_id").toString(),map.get("user_bind_agreement_list")+map.get("user_id").toString());
	    					}
	    					synchronized(requestValue.get(map.get("user_bind_agreement_list")+map.get("user_id").toString())){
	    						String bind_agreement=map.get("user_bind_agreement_list").toString().trim();
	    						String[] bind=bind_agreement.split(",");
	    						//授权用户的第三方账号
	    						commonResponse.setThirdPayConfigId(map.get("user_id").toString());
	    						if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_INVST)){//投资授权
	    							//TODD 处理 投标授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动投标授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("第三方自动投标授权失败，失败原因："+bind[2]);
	    							}
	    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_REPAYMENT)){//还款授权
	    							//TODD 处理 还款授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动还款授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("第三方自动还款授权失败，失败原因："+bind[2]);
	    							}
	    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_FASTPAYMENT)){//NO_PASSWORD_FASTPAYMENT
	    							//TODD 处理 无密充值快捷协议和开通快捷支付银行卡不同的业务方法
	    							
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动授权无密码交易授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("绑卡失败");
	    					    		ret[1]="自动授权无密码交易授权失败，失败原因："+bind[2];
	    							}
	    						}else if (service.equals(UMPay.SER_TRANSFER)){//投标回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					//充值交易回调通知
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("投标成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("投标失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}else if (service.equals(UMPay.SER_TRANSFER)){//还款回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("还款成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("还款失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}else if (service.equals(UMPay.SER_TRANSFER)){//流标回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("流标成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("流标失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}else if (service.equals(UMPay.SER_TRANSFER)){//放款回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("放款成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("放款失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}
	    	    				
	    						requestValue.remove(map.get("user_bind_agreement_list")+map.get("user_id").toString());
	    					}
	    				}else if(service.equals(UMPay.NOTIFY_CANCELAGERRMENT)){//取消无密交易授权回调通知
	    					logger.info("联动优服务器端回调函数通知页面回调函数通知调用取消无密交易授权业务操作方法开始");
	    					String[] ret=new String[2];
	    					// TODO Auto-generated method stub
	    					if(!requestValue.containsKey(map.get("user_unbind_agreement_list"))){
	    						requestValue.put(map.get("user_unbind_agreement_list")+map.get("user_id").toString(),map.get("user_unbind_agreement_list")+map.get("user_id").toString());
	    					}
	    					synchronized(requestValue.get(map.get("user_unbind_agreement_list")+map.get("user_id").toString())){
	    						String bind_agreement=map.get("user_unbind_agreement_list").toString().trim();
	    						String[] bind=bind_agreement.split(",");
	    						//授权用户的第三方账号
	    						commonResponse.setThirdPayConfigId(map.get("user_id").toString());
	    						if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_INVST)){//解除投资授权
	    							//TODD 处理 投标授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    								commonResponse.setResponseMsg("解除自动投标授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    								commonResponse.setResponseMsg("解除第三方自动投标授权失败，失败原因："+bind[2]);
	    							}
	    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_REPAYMENT)){//还款授权
	    							//TODD 处理 还款授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("解除自动还款授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("解除第三方自动还款授权失败，失败原因："+bind[2]);
	    							}
	    						}
	    						requestValue.remove(map.get("user_bind_agreement_list")+map.get("user_id").toString());
	    					}
	    				}else if(service.equals(UMPay.NOTIFY_NOMALTRANSFER)){//普通转账回调通知
	    					logger.info("联动优势服务器端回调函数通知调用普通转账操作方法开始");
	    					
	    				}else if(service.equals(UMPay.NOTIFY_BIDTRANSFER)){//标类的转账回调通知
	    					logger.info("联动优势服务器端回调函数通知调用标类的转账业务操作方法开始");
	    				}
	    				
	    			}else{
	    				logger.info("联动优势页面回调函数通知无业务类型字段service，目前认为是取现服务器端通知");
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
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SYSTEMERROR);
			commonResponse.setResponseMsg("系统报错，联系管理员");
			logger.info("联动优势页面回调函数通知出错，原因："+e.getMessage());
		}
		
		
		return commonResponse;
	}
	
	/**
	 * 服务器端回调
	 * 通知业务数据处理
	 * @param request
	 * @return
	 */
	public static CommonResponse notifyCallBackOprate(HttpServletRequest request) {
		//业务类型(每个接口多种使用方式系统添加参数)
		//默认回调通知参数实体
		CommonResponse commonResponse=new CommonResponse();
		//系统调用第三方回调的url中默认携带参数
		String bussinessType=request.getParameter("HRBT");
		request.removeAttribute("HRBT");//将我们默认传递的参数从request中移除
		//将业务参数返回给业务层
		commonResponse.setBussinessType(bussinessType);
		try{
			logger.info("联动优势服务器端回调函数通知方法开始");
			//用来标识是否完成
			Boolean isSign=false;
			/**
			 * 准备将回调通知参数整合成map
			 */
			Map<String,String> map =createResponseMap(request);
	    	if(!map.isEmpty()){
	    		//联动优势验证签名方法
	    		isSign=verifySign(map);
	    		logger.info("联动优势服务器端回调函数通知签名验证结果isSign="+isSign);
	    		if(isSign){
	    			String service=request.getParameter("service");
	    			//生成需要返回给第三方的参数
	    			String responsehtml=createReaponseHTML();
	    			if(service!=null&&!"".equals(service)){//其他业务处理方法
	    				if(service.equals(UMPay.NOTIFY_RECHARGE)){//充值交易回调通知
	    					logger.info("联动优势服务器端回调函数通知调用充值业务操作方法开始");
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
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
	    					String[] ret=new String[2];
	    					// TODO Auto-generated method stub
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    							commonResponse.setResponseMsg("取现成功");
	    						}else{
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    							commonResponse.setResponseMsg("取现失败");
	    						}
	    						requestValue.remove(map.get("order_id"));
	    					}
	    				}else if(service.equals(UMPay.NOTIFY_BINDCARD)){//绑卡回调通知
	    					logger.info("联动优势服务器端回调函数通知调用绑卡业务操作方法开始");
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    							if(map.containsKey("last_four_cardid")){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("绑卡成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
		    							commonResponse.setResponseMsg("绑卡受理中，等待时间大约2个小时");
	    							}
	    						}else{
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    							commonResponse.setResponseMsg("绑卡失败");
	    						}
	    						requestValue.remove(map.get("order_id"));
	    					}
	    					
	    				}/*else if(service.equals(UMPay.NOTIFY_BINDCARD)){//更换银行卡回调通知（第一次申请结果）
	    					
	    				}else if(service.equals(UMPay.NOTIFY_BINDCARD)){//更换银行卡回调通知（第二次结果通知）
	    					
	    				}*/else if(service.equals(UMPay.NOTIFY_BINDAGERRMENT)){//无密交易授权回调通知
	    					logger.info("联动优势服务器端回调函数通知页面回调函数通知调用无密交易授权业务操作方法开始");
	    					String[] ret=new String[2];
	    					// TODO Auto-generated method stub
	    					if(!requestValue.containsKey(map.get("user_bind_agreement_list"))){
	    						requestValue.put(map.get("user_bind_agreement_list")+map.get("user_id").toString(),map.get("user_bind_agreement_list")+map.get("user_id").toString());
	    					}
	    					synchronized(requestValue.get(map.get("user_bind_agreement_list"))){
	    						String bind_agreement=map.get("user_bind_agreement_list").toString().trim();
	    						String[] bind=bind_agreement.split(",");
	    						//授权用户的第三方账号
	    						commonResponse.setThirdPayConfigId(map.get("user_id").toString());
	    						if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_INVST)){//投资授权
	    							//TODD 处理 投标授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动投标授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("第三方自动投标授权失败，失败原因："+bind[2]);
	    							}
	    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_REPAYMENT)){//还款授权
	    							//TODD 处理 还款授权的业务方法
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动投标授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("第三方自动还款授权失败，失败原因："+bind[2]);
	    							}
	    						}else if(bind!=null&&bind[0].equals(UMPay.NO_PASSWORD_FASTPAYMENT)){//NO_PASSWORD_FASTPAYMENT
	    							//TODD 处理 无密充值快捷协议和开通快捷支付银行卡不同的业务方法
	    							
	    							if(bind[1].equals(UMPay.CODE_SUCESS)){
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
		    							commonResponse.setResponseMsg("自动授权无密码交易授权成功");
	    							}else{
	    								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
		    							commonResponse.setResponseMsg("绑卡失败");
	    					    		ret[1]="自动授权无密码交易授权失败，失败原因："+bind[2];
	    							}
	    						}else if (service.equals(UMPay.SER_TRANSFER)){//还款回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("还款成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("还款失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}else if (service.equals(UMPay.SER_TRANSFER)){//流标回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("流标成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("流标失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}else if (service.equals(UMPay.SER_TRANSFER)){//放款回调通知
	    	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    	    					if(!requestValue.containsKey(map.get("order_id"))){
	    	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    	    					}
	    	    					synchronized(requestValue.get(map.get("order_id"))){
	    	    			          //TODO 添加处理业务方法
	    	    						commonResponse.setRequestNo(map.get("order_id"));
	    	    						commonResponse.setBussinessType(service);
	    	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    	    							commonResponse.setResponseMsg("放款成功");
	    	    						}else{
	    	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	    							commonResponse.setResponseMsg("放款失败");
	    	    						}
	    	    						requestValue.remove(map.get("order_id"));
	    	    					}
	    	    				
	    	    				}
	    						requestValue.remove(map.get("user_bind_agreement_list")+map.get("user_id").toString());
	    					}
	    				}else if(service.equals(UMPay.NOTIFY_CANCELAGERRMENT)){//取消无密交易授权回调通知
	    					logger.info("联动优服务器端回调函数通知页面回调函数通知调用取消无密交易授权业务操作方法开始");
	    					
	    				}else if(service.equals(UMPay.NOTIFY_NOMALTRANSFER)){//普通转账回调通知
	    					logger.info("联动优势服务器端回调函数通知调用普通转账操作方法开始");
	    					
	    				}else if(service.equals(UMPay.NOTIFY_BIDTRANSFER)){//标类的转账回调通知
	    					logger.info("联动优势服务器端回调函数通知调用标类的转账业务操作方法开始");
	    					
	    					
	    				}else if (service.equals(UMPay.SER_TRANSFER)){//投标回调通知
	    					logger.info("联动优势服务器端回调函数通知调用标类的投标业务操作方法开始");
	    					//充值交易回调通知
	    					if(!requestValue.containsKey(map.get("order_id"))){
	    						requestValue.put(map.get("order_id"),map.get("order_id"));
	    					}
	    					synchronized(requestValue.get(map.get("order_id"))){
	    			          //TODO 添加处理业务方法
	    						commonResponse.setRequestNo(map.get("order_id"));
	    						commonResponse.setBussinessType(service);
	    						if(map.get("ret_code").toString().equals(UMPay.CODE_SUCESS)){
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    							commonResponse.setResponseMsg("投标成功");
	    						}else{
	    							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    							commonResponse.setResponseMsg("投标失败");
	    						}
	    						requestValue.remove(map.get("order_id"));
	    					}
	    				
	    				}
	    				
	    			}else{//默认为第三方接收到银行的最后的通知(默认取现最后一次通知)
	    				
	    			}
	    			commonResponse.setReturnMsg(responsehtml);
	    			logger.info("联动优势服务器端回调函数通知给联动优势的相应html="+responsehtml);
	    		}else{
	    			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
					commonResponse.setResponseMsg("签名验证失败");
		    		logger.info("联动优势服务器端回调函数通知出错，原因：联动优势服务器端回调函数通知签名验证没有通过，请等待一段时间后查询个人中心资金交易明细，或者联系管理员");
	    		}
	    	}else{
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTRECIVEPARAMETER);
				commonResponse.setResponseMsg("没有收到任何回调通知");
	    		logger.info("联动优势服务器端回调函数通知出错，原因：没有收到任何交易参数，请等待一段时间后查询个人中心资金交易明细，或者联系管理员");
	    	}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("联动优势服务器端回调函数通知出错，原因："+e.getMessage());
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SYSTEMERROR);
			commonResponse.setResponseMsg("系统报错，联系管理员");
		}
		commonResponse.setReturnType("json");
		return  commonResponse;
	}
	/**
	 * 准备UMP公共支付数据
	 * @param umpay
	 * @return
	 */
	private static UMPay qiyekehuData(UMPay umpay,Boolean notifyUrl,Boolean pageUrl,Boolean isMobile,String bussinessType) {
		umpay.setVersion("1.0");
		umpay.setCharset(UMPay.UTF8);
		umpay.setRes_format("HTML");
		umpay.setSign_type("RSA");
		//umpay.setMer_id(umpay.getWithdraw_mer_id());
		umpay.setMer_id(configMap.get("thirdPay_umpay_MemberID").toString());
		String BasePath=ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath() + "/";
		if(notifyUrl){
			umpay.setNotify_url(BasePath+configMap.get("thirdPay_umpay_notifyUrl").toString()+"?HRBT="+bussinessType);
		}
		if(pageUrl){
			if(isMobile){
				umpay.setRet_url(BasePath+configMap.get("thirdPay_umpay_callbackUrl").toString()+"?HRBT="+bussinessType+"&isMobile=1");
				umpay.setSourceV("HTML5");
			}else{
				umpay.setRet_url(BasePath+configMap.get("thirdPay_umpay_callbackUrl").toString()+"?HRBT="+bussinessType);
			}
		}
		return umpay;
	}

	/**
	 * 直连接口11
	 * 联动优势支付账户的流水查询查询接口
	 * @param map
	 * @return
	 */
	public static CommonResponse accountQulideQuery1(CommonRequst commonRequst) {
		Object[] backData=new Object[3];
		CommonResponse response = new CommonResponse();
		try{
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
			logger.info("联动优势支付标账户查询调用");
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			String queryAccountType=commonRequst.getBussinessType();
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BIDFLOWQUERY)){//标的账户流水查询
				umpay.setAccount_type("03");
				umpay.setPartic_user_id(commonRequst.getBidType()+commonRequst.getBidId());
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFLOWQUERY)){//平台账户流水查询
				umpay.setAccount_type("02");
				umpay.setAccount_id(umpay.getMer_id());
			}/*else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BUSSINESSTYPE_CUSTFLOWQUERY)){//个人账户流水查询
				umpay.setAccount_type("01");
				umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
			}*/else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_ACCOUNTQUERY)){//商户账户流水查询				
				umpay.setPartic_user_id(commonRequst.getThirdPayConfigId());
				umpay.setAccount_type("01");
				umpay.setMer_id(configMap.get("thirdPay_umpay_MemberID").toString());
				umpay.setQuery_mer_id(commonRequst.getThirdPayConfigId());
				umpay.setAccount_type("02");
			}
		
			//发标时间标的主键Id和标的标
			if(commonRequst.getQueryStartDate()!=null){
				umpay.setStart_date(sf.format(commonRequst.getQueryStartDate()));
			}
			if(commonRequst.getQueryEndDate()!=null){
				umpay.setEnd_date(sf.format(commonRequst.getQueryEndDate()));
			}
			umpay.setPage_num((commonRequst.getPageNumber()!=null&&!"".equals(commonRequst.getPageNumber()))?commonRequst.getPageNumber():"1");
			Map<String,String> returnMap=creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付标账户查询调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付标账户查询生成的签名sign=="+sign);
			logger.info("联动优势支付标账户查询调用的url=="+configMap.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(configMap.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付标账户查询调用后收到的通知"+ret);
			System.out.println(ret);
			Map<String,String> htmlReturnMap=parseHTMLMethod(ret);
			if(htmlReturnMap.containsKey("trans_detail")){
				System.out.println("trans_detail=="+htmlReturnMap.get("trans_detail").toString());
			}
			Boolean isSign=true;//verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付标账户查询调用后收到的通知后验证签名结果isSign="+isSign);
			if(isSign){
				if(htmlReturnMap.get("ret_code").equals("0000")){
					if(htmlReturnMap.containsKey("trans_detail")){
						System.out.println("trans_detail=="+htmlReturnMap.get("trans_detail").toString());
						String[] rett=htmlReturnMap.get("trans_detail").toString().split("\\|");
						if(rett!=null&&rett.length>0){
							List<CommonRecord> list=new ArrayList<CommonRecord>();
							for(int i=0;i<rett.length;i++){
								String[] retinfo=rett[i].split(",");
								if(retinfo!=null&&retinfo.length>0){
									CommonRecord p =new CommonRecord();
									for(int j=0;j<retinfo.length;j++){
										String key=retinfo[j];
										System.out.println("key=="+key);
										String[] keyStr=key.split("=");
										System.out.println("keyStr.length=="+keyStr.length);
										if(keyStr!=null&&keyStr.length>0){
											if(keyStr[0].equals("order_id")){//交易订单号
												if(keyStr.length>1&&!"".equals(keyStr[1])){
													System.out.println("order_id=="+keyStr[1]);
													p.setRequestNo(keyStr[1]);
												}
											}else if(keyStr[0].equals("acc_check_date")){//交易时间
												
												if(keyStr.length>1&&!"".equals(keyStr[1])){
													System.out.println("acc_check_date=="+keyStr[1]);
													p.setTime(sf.parse(keyStr[1]).toString());
												}
												
											}else if(keyStr[0].equals("amount")){//交易金额
												
												if(keyStr.length>1&&!"".equals(keyStr[1])){
													System.out.println("amount=="+keyStr[1]);
													BigDecimal amount=new BigDecimal(keyStr[1]).divide(new BigDecimal(100),3,BigDecimal.ROUND_HALF_UP).setScale(2);
													p.setAmount(amount.toString());

												}
											}else if(keyStr[0].equals("balance")){//交易金额
												if(keyStr.length>1&&!"".equals(keyStr[1])){
													System.out.println("balance=="+keyStr[1]);
													System.out.println("amount=="+keyStr[1]);
													BigDecimal amount=new BigDecimal(keyStr[1]).divide(new BigDecimal(100),3,BigDecimal.ROUND_HALF_UP).setScale(2);
													p.setBalance(amount.toString());
												}
											}else if(keyStr[0].equals("com_amt")){//手续费
												if(keyStr.length>1&&!"".equals(keyStr[1])){
													System.out.println("amount=="+keyStr[1]);
													BigDecimal amount=new BigDecimal(keyStr[1]).divide(new BigDecimal(100),3,BigDecimal.ROUND_HALF_UP).setScale(2);
													p.setFee(amount.toString());

												}
												//p.setFee((new BigDecimal(keyStr[1])).divide(new BigDecimal(100)));
											}/*else if(keyStr[0].equals("dc_mark")){//资金借贷方向
												String  markType="";
												if(keyStr.length>1&&keyStr[1].equals("01")){
													markType="入账";
												}else if(keyStr.length>1&&keyStr[1].equals("02")){
													markType="出账";
												}else if(keyStr.length>1&&keyStr[1].equals("99")){
													markType="其它";
												}
												p.setTransferDirection(markType);
											}*/else if(keyStr[0].equals("trans_type")){//交易类型
												String type=keyStr.length>1?keyStr[1]:"";
												String msg="";
												if(type.equals("01")){
													msg="充值";
												}else if(type.equals("02")){
													msg="提现";
												}else if(type.equals("03")){
													msg="标的转账";
												}else if(type.equals("04")){
													msg="转账";
												}else if(type.equals("99")){
													msg="退费等其他交易";
												}
												p.setBizType(msg);
											}else if(keyStr[0].equals("trans_state")){//交易状态
												String type=keyStr.length>1?keyStr[1]:"";
												String msg="";
												if(type.equals("01")){
													msg="成功";
												}else if(type.equals("02")){
													msg="冲正";
												}else if(type.equals("99")){
													msg="其它";
												}
												p.setStatus(msg);
											}
											
										}
									}
									list.add(p);	
								}
							}
							if(list.size()>0){
								response.setRecordList(list);
							}
						}
					}
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg("查询成功");
					response.setCommonRequst(commonRequst);
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					response.setCommonRequst(commonRequst);
				}
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("签名验证失败");
				response.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			response.setResponseMsg("联动优势支付标账户查询调用出错");
			response.setCommonRequst(commonRequst);
			logger.info("联动优势支付标账户查询调用出错,原因:"+e.getMessage());
		}
		return response;
	}
	
	/**
	 * 直连接口11
	 * 联动优势支付账户的流水查询查询接口
	 * @param map
	 * @return
	 */
	public static CommonResponse accountQulideQuery(CommonRequst commonRequst) {
		Object[] backData=new Object[3];
		CommonResponse response = new CommonResponse();
		try{
			Map thirdPayConfig=umProperty();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
			logger.info("联动优势支付标账户查询调用");
			UMPay umpay= new UMPay();
			umpay=UMPayInterfaceUtil.generalPublicDate(umpay,false,false,false,commonRequst.getBussinessType());
			String queryAccountType=commonRequst.getBussinessType();
			umpay.setService("transfer_search");
			if(commonRequst.getQueryType().equals("RECHARGE_RECORD")){//充值流水查询				
				umpay.setMer_id(thirdPayConfig.get("thirdPay_umpay_MemberID").toString());
				umpay.setQuery_mer_id(commonRequst.getThirdPayConfigId());
				umpay.setOrder_id(commonRequst.getQueryRequsetNo().toString());
				umpay.setBusi_type(commonRequst.getRemark1()!=null?commonRequst.getRemark1():"01");
				umpay.setUser_id(commonRequst.getThirdPayConfigId());
				umpay.setIs_find_account("01");
				umpay.setMer_date(sf.format(commonRequst.getTransactionTime()));
				//umpay.setUser_id(commonRequst.get)
			}else if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){//提现流水查询
				umpay.setMer_id(thirdPayConfig.get("thirdPay_umpay_MemberID").toString());
				umpay.setQuery_mer_id(commonRequst.getThirdPayConfigId());
				umpay.setOrder_id(commonRequst.getQueryRequsetNo().toString());
				umpay.setBusi_type(commonRequst.getRemark1()!=null?commonRequst.getRemark1():"02");
				umpay.setUser_id(commonRequst.getThirdPayConfigId());
				umpay.setIs_find_account("01");
				umpay.setMer_date(sf.format(commonRequst.getTransactionTime()));
			}
		
			//发标时间标的主键Id和标的标
			if(commonRequst.getQueryStartDate()!=null){
				umpay.setStart_date(sf.format(commonRequst.getQueryStartDate()));
			}
			if(commonRequst.getQueryEndDate()!=null){
				umpay.setEnd_date(sf.format(commonRequst.getQueryEndDate()));
			}
			umpay.setPage_num((commonRequst.getPageNumber()!=null&&!"".equals(commonRequst.getPageNumber()))?commonRequst.getPageNumber():"1");
			Map<String,String> returnMap=creatDataMap(umpay);
			String params=returnMap.get("plain").toString().trim();
			System.out.println("生成的传输参数params=="+params);
			logger.info("联动优势支付标账户查询调用生成的传输参数params=="+params);
			String sign=returnMap.get("sign").toString().trim();
			System.out.println("生成的签名sign=="+sign);
			logger.info("联动优势支付标账户查询生成的签名sign=="+sign);
			logger.info("联动优势支付标账户查询调用的url=="+thirdPayConfig.get("thirdPay_umpay_URL").toString());
			String ret=ThirdPayWebClient.getWebContentByPost(thirdPayConfig.get("thirdPay_umpay_URL").toString(), params,UMPay.UTF8,12000);
			logger.info("联动优势支付标账户查询调用后收到的通知"+ret);
			System.out.println(ret);
			Map<String,String> htmlReturnMap=parseHTMLMethod(ret);
			if(htmlReturnMap.containsKey("trans_detail")){
				System.out.println("trans_detail=="+htmlReturnMap.get("trans_detail").toString());
			}
			Boolean isSign=true;//verifySign(htmlReturnMap);
			System.out.println("验证签名isSign=="+isSign);
			logger.info("联动优势支付标账户查询调用后收到的通知后验证签名结果isSign="+isSign);
			if(isSign){
				if(htmlReturnMap.get("ret_code").equals("0000")||htmlReturnMap.get("ret_code").equals("00240005")){
					List<CommonRecord> list=new ArrayList<CommonRecord>();
					CommonRecord p1 =new CommonRecord();
					//查询标的账户 解析数据单独处理
						if(commonRequst.getQueryRequsetNo()!=null&&!"".equals(commonRequst.getQueryRequsetNo())){
							ThirdPayRecord record = thirdPayRecordService.getByOrderNo(commonRequst.getQueryRequsetNo());
							if(record.getThirdPayFlagId()!=null){
								p1.setUserNo(record.getThirdPayFlagId());
								p1.setSourceUserNo(record.getThirdPayFlagId());
								p1.setTargetUserNo(record.getThirdPayFlagId());
							}
						}
						if(htmlReturnMap.containsKey("amount")){
							p1.setAmount((new BigDecimal(htmlReturnMap.get("amount").toString()).divide(new BigDecimal(100)).toString()));
							p1.setPaymentAmount((new BigDecimal(htmlReturnMap.get("amount").toString()).divide(new BigDecimal(100)).toString()));
							p1.setRepaymentAmount((new BigDecimal(htmlReturnMap.get("amount").toString()).divide(new BigDecimal(100)).toString()));
						}
						if(htmlReturnMap.containsKey("tran_state")){
							if(htmlReturnMap.get("tran_state").equals("2")){
								p1.setStatus("交易成功");
							}else{
								p1.setStatus("交易失败");
							}
						}
						if(htmlReturnMap.containsKey("mer_date")){
							p1.setCreateTime(htmlReturnMap.get("mer_date"));
						}
						list.add(p1);
					response.setRecordList(list);
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg("查询成功");
					response.setCommonRequst(commonRequst);
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg(htmlReturnMap.get("ret_msg").toString());
					response.setCommonRequst(commonRequst);
				}
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("签名验证失败");
				response.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			response.setResponseMsg("联动优势支付标账户查询调用出错");
			response.setCommonRequst(commonRequst);
			logger.info("联动优势支付标账户查询调用出错,原因:"+e.getMessage());
		}
		return response;
	}
}
