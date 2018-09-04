package com.thirdPayInterface.MoneyMorePay;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.thirdPayInterface.CommonRecord;
import com.thirdPayInterface.CommonRequestInvestRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayWebClient;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.Common;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.DateUtil;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.HibernateProxyTypeAdapter;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.MD5;
import com.thirdPayInterface.MoneyMorePay.MoneyMoreUtil.SignUtil;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.LoanTransferReturnBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.MadaiLoanInfoBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.QueryExtractionBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.QueryRechargeBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.QueryTransferBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.ResultBean;
import com.thirdPayInterface.MoneyMorePay.MoneyResponse.TransferauditBean;


public class MoneyMorePayInterfaceUtil {
	private static Log logger=LogFactory.getLog(MoneyMorePayInterfaceUtil.class);
	 /**
     * 获取系统配置参数
     */
	private static Map configMap = AppUtil.getConfigMap();
	/**
	 * 第三方支付环境（正式环境和测试环境）
	 */
	private static String  thirdPayEnvironmentType=configMap.containsKey("thirdPayEnvironmentType")?configMap.get("thirdPayEnvironmentType").toString().trim():ThirdPayConstants.THIRDENVIRONMENT1;
	/**
	 * 获取
	 * @return
	 */
	private static String moneyMorePayUrl(){
		Map moneyMoreConfigMap=moneyMoreProperty();
		//双乾支付调用地址
		String moneyMorePayUrl=moneyMoreConfigMap.get("MM_PostUrl").toString();
		return moneyMorePayUrl;
	}
	/**
	 * 获取双乾支付的第三方支付环境参数
	 * @return
	 */
	public static Map moneyMoreProperty(){
		Map moneyMoreConfigMap=new HashMap();
		try{
			InputStream in=null;
			//获取当前支付环境为正式环境还是测试环境
			if(thirdPayEnvironmentType.equals(ThirdPayConstants.THIRDENVIRONMENT0)){//正式环境
		       in = MoneyMorePayInterfaceUtil.class.getResourceAsStream("MoneyMoreNormalEnvironment.properties"); 
			}else{
		        in = MoneyMorePayInterfaceUtil.class.getResourceAsStream("MoneyMoreTestEnvironment.properties"); 
			}
			Properties props =  new  Properties(); 
			if(in!=null){
				props.load(in);
		    	Iterator it= props.keySet().iterator();
		    	while(it.hasNext()){
		    		String key=(String)it.next();
		    		moneyMoreConfigMap.put(key, props.getProperty(key));
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
    		logger.error(ex.getMessage());
    	}
		return moneyMoreConfigMap;
	}
	/**
	 * 获取通用参数
	 * @param returnURL
	 * @param notifyURL
	 * @param randomStamp
	 * @return
	 */
	public static MoneyMore generatePublicData(boolean returnURL, boolean notifyURL,boolean randomStamp,boolean isMobile) {
		// TODO Auto-generated method stub
		Map thirdPayConfig=moneyMoreProperty();
		if(thirdPayConfig!=null){
			MoneyMore moneyMore=new MoneyMore();
			if(randomStamp){
				//是否启用防抵赖 
				moneyMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); 
			}else{
				moneyMore.setRandomTimeStamp("");
			}
			//平台乾多多标识
			moneyMore.setPlatformMoneymoremore(thirdPayConfig.get("MM_PlatformMoneymoremore").toString().trim());
			String BasePath=ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath() + "/";
			if(returnURL){
				if(isMobile){
					moneyMore.setReturnURL(BasePath+thirdPayConfig.get("thirdPay_callbackUrl").toString().trim()+"?isMobile=1");//页面返回网址
				}else{
					moneyMore.setReturnURL(BasePath+thirdPayConfig.get("thirdPay_callbackUrl").toString().trim());//页面返回网址
				}
			}
			if(notifyURL){
			/*	if(isMobile){
					if(thirdPayConfig.get("MM_PostUrl").toString().equals("normal")){//正式环境
						moneyMore.setNotifyURL(BasePath+thirdPayConfig.get("thirdPay_notifyUrl").toString().trim()+"?isMobile=1");//后台通知网址 
					}else{//测试环境
						moneyMore.setNotifyURL(thirdPayConfig.get("MM_TestnotifyUrl").toString().trim()+"?isMobile=1");//后台通知网址 
					}
				}else{*/
					if(thirdPayConfig.get("MM_PostUrl").toString().equals("normal")){//正式环境
						moneyMore.setNotifyURL(BasePath+thirdPayConfig.get("thirdPay_notifyUrl").toString().trim());//后台通知网址 
					}else{//测试环境
						moneyMore.setNotifyURL(thirdPayConfig.get("MM_TestnotifyUrl").toString());//后台通知网址 
					}
				//}
			}
			return moneyMore;
		}else{
			return null;
		}
	}
	 /**
     * 实体对象  转map
     * @param request
     * @return
     */
    public static Map getParameterMap(MoneyMore moneyMore,String dataStr,Map moneyMoreConfigMap) {
    	Map map = new HashMap();;
		try{
			if(moneyMore!=null&&moneyMoreConfigMap!=null){
		        String privatekey =moneyMoreConfigMap.get("MM_PrivateKey").toString();
				SignUtil rsa = SignUtil.getInstance();
				MD5 md5=new MD5();
//				String sign =  (rsa.signData(md5.getMD5Info(dataStr), privatekey.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "")));
				String sign =  (rsa.signData(dataStr, privatekey.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "")));
				moneyMore.setSignInfo(sign);
				//封装Map
				map = MoneyMorePaySecretKeyUtil.createMap(moneyMore.getClass(), moneyMore);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  map;
    }
	/**
	 * 签名
	 * @param moneyMore
	 * @param dataStr
	 * @return
	 */
	public static MoneyMore getReqSign(MoneyMore moneyMore,String dataStr){
		Map moneyMoreConfigMap=moneyMoreProperty();
		String privatekey = moneyMoreConfigMap.get("MM_PrivateKey").toString();
		// 签名
		SignUtil rsa = SignUtil.getInstance();
		MD5 md5=new MD5();
		moneyMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
		return moneyMore;
	}
	
	
	/**
	 * 获取双乾支付的绑定银行卡列表
	 * @return
	 */
	public static Map moneyMoreBankList(){
		Map moneyMoreBankList=new HashMap();
		try{
			InputStream in=null;
			in = MoneyMorePayInterfaceUtil.class.getResourceAsStream("BindBankCode.properties"); 
			Properties props =  new  Properties(); 
			if(in!=null){
				props.load(in);
		    	Iterator it= props.keySet().iterator();
		    	while(it.hasNext()){
		    		String key=(String)it.next();
		    		moneyMoreBankList.put(key, props.getProperty(key));
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
    		logger.error(ex.getMessage());
    	}
		return moneyMoreBankList;
	}
	//==================================接口开始=========================================
	/**
	 * 网关接口01--个人用户注册
	 */
	public static CommonResponse register(CommonRequst commonRequst){
		logger.info("调用双乾支付网关用户注册接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();
		CommonResponse commonResponse=new  CommonResponse();
		ResultBean resultBean=new ResultBean();
		try{
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);//设置手机端为true
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				moneyMore.setLoanPlatformAccount(commonRequst.getLoginname());//网贷平台登录名
				moneyMore.setRegisterType(commonRequst.getRegisterType());//1表示全自动，2表示半自动
				moneyMore.setMobile(commonRequst.getTelephone());//手机号
				moneyMore.setEmail(commonRequst.getEmail());//邮箱
				moneyMore.setAccountType(commonRequst.getAccountType());//"" 为个人客户，1 为企业客户
				moneyMore.setRealName(commonRequst.getTrueName().trim());//会员真实姓名
				moneyMore.setIdentificationNo(commonRequst.getCardNumber());//身份证好/营业执照号
				moneyMore.setImage1("");//身份证/营业执照正面
				moneyMore.setImage2("");//身份证/营业执照反面
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//拼接字符串生成签名
				String acconutType= "";
				if(moneyMore.getAccountType()!=null){
					acconutType=moneyMore.getAccountType().toString();
				}
				String dataStr = moneyMore.getRegisterType()+acconutType+ moneyMore.getMobile() 
								+ moneyMore.getEmail() +moneyMore.getRealName()+ moneyMore.getIdentificationNo() 
								+ moneyMore.getImage1() + moneyMore.getImage2() +moneyMore.getLoanPlatformAccount() 
								+ moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() /*+ moneyMore.getRandomTimeStamp() */
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() + moneyMore.getReturnURL() 
								+ moneyMore.getNotifyURL();
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TOREG;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TOREG;
				}
				//实体对象整合成map对象，并生成签名
				Map<String, String> map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("params="+map);
				//map对象经过编码转变成post提交的string对象
					String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
					logger.info("调用双乾支付网转账接口参数："+rett[1]);
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("注册申请成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("注册申请失败");
					}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("注册对接失败");
		}
		
		return commonResponse;
	}
	/**
	 * 网关接口01--企业用户注册
	 */
	public static CommonResponse registerenterprise(CommonRequst commonRequst){
		logger.info("调用双乾支付网关用户注册接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();
		CommonResponse commonResponse=new  CommonResponse();
		ResultBean resultBean=new ResultBean();
		try{
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=generatePublicData(true,true,false,false);
			if(moneyMore!=null){
				moneyMore.setLoanPlatformAccount(commonRequst.getLoginname());//网贷平台登录名
				moneyMore.setRegisterType(commonRequst.getRegisterType());//1表示全自动，2表示半自动
				moneyMore.setMobile(commonRequst.getContactPhone());//手机号
				moneyMore.setEmail(commonRequst.getEmail());//邮箱
				moneyMore.setAccountType(1);
				moneyMore.setRealName(commonRequst.getEnterpriseName());//会员真实姓名
				//判断是否是三证合一
				if(commonRequst.getThreeCardCode()!=null&&!commonRequst.getThreeCardCode().equals("")){
					moneyMore.setIdentificationNo(commonRequst.getThreeCardCode());//社会信息代码
				}else{
					moneyMore.setIdentificationNo(commonRequst.getBusinessLicense());//身份证好/营业执照号
				}
				moneyMore.setImage1("");//身份证/营业执照正面
				moneyMore.setImage2("");//身份证/营业执照反面
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//拼接字符串生成签名
				String dataStr="";
					 dataStr = moneyMore.getRegisterType()+moneyMore.getAccountType().toString()+ moneyMore.getMobile() 
					+ moneyMore.getEmail() +moneyMore.getRealName()+ moneyMore.getIdentificationNo() 
					+ moneyMore.getImage1() + moneyMore.getImage2() +moneyMore.getLoanPlatformAccount() 
					+ moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() /*+ moneyMore.getRandomTimeStamp() */
					+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() + moneyMore.getReturnURL() 
					+ moneyMore.getNotifyURL();
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TOREG;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TOREG;
				}
				//实体对象整合成map对象，并生成签名
				Map<String, String> map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("params="+map);
				//map对象经过编码转变成post提交的string对象
					String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
					logger.info("调用双乾支付网转账接口参数："+rett[1]);
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("注册申请成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("注册申请失败");
					}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("注册对接失败");
		}
		
		return commonResponse;
	}
	/**
	 * 网关接口02--转账接口(资金冻结)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse transfer(CommonRequst commonRequst){
		logger.info("调用双乾支付转账接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				moneyMore=generatePublicData(true,true,false,true);
			}else{
				moneyMore=generatePublicData(true,true,false,false);
			}
			//if(commonRequst.getBussinessType().equals(ThirdPayConstants.BUSSINESSTYPE_BID)){
				// moneyMore.setNeedAudit(null);//需要审核
			//}
			if(moneyMore!=null){
				//生成转账列表
				moneyMore = loanJsonList(commonRequst,moneyMore);
				moneyMore.setTransferAction(moneyMore.TACTION_1);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_1);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TTYPE_2);//转账方式，1.桥连	2.直连
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr = moneyMore.getLoanJsonList() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()
								+ moneyMore.getTransferAction() + moneyMore.getAction() + moneyMore.getTransferType() /*+ moneyMore.getNeedAudit()*/
								/*+ moneyMore.getRandomTimeStamp()*/ + moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付网转账接口参数："+rett[1]);
				commonResponse.setReturnType(CommonResponse.RETURNTYPE_HTML);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("通用转账成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("通用转账失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				commonResponse.setResponseMsg("通用转账基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
			commonResponse.setResponseMsg("通用转账接口对接失败");
		}
		return commonResponse;
	}
	/**
	 * 转账接口-----------》购买债权
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse buySaleTransfer(CommonRequst commonRequst){
		logger.info("调用双乾支付转账接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			moneyMore=generatePublicData(true,true,false,false);
			moneyMore.setNeedAudit(1);//需要审核，1自动通过
			if(moneyMore!=null){
				//生成转账列表
				moneyMore = loanJsonList(commonRequst,moneyMore);
				moneyMore.setTransferAction(3);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_1);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TTYPE_2);//转账方式，1.桥连	2.直连
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr = moneyMore.getLoanJsonList() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()
								+ moneyMore.getTransferAction() + moneyMore.getAction() + moneyMore.getTransferType() + moneyMore.getNeedAudit()
								/*+ moneyMore.getRandomTimeStamp()*/ + moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付网转账接口参数："+rett[1]);
				commonResponse.setReturnType(CommonResponse.RETURNTYPE_HTML);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("通用转账成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("通用转账失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				commonResponse.setResponseMsg("通用转账基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
			commonResponse.setResponseMsg("通用转账接口对接失败");
		}
		return commonResponse;
	}
	
	/**
	 * 网关接口03--转账接口(派发红包)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse giveRed(CommonRequst commonRequst){
		logger.info("发红包调用双乾支付转账接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			moneyMore=generatePublicData(true,true,false,false);
			if(moneyMore!=null){
				//生成转账列表
				moneyMore = loanJsonListRed(commonRequst,moneyMore);
				moneyMore.setTransferAction(moneyMore.TACTION_3);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_2);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TTYPE_2);//转账方式，1.桥连	2.直连
				moneyMore.setNeedAudit(moneyMore.NEEDAUDIT_1);//通过是否需要审核   1自动通过 ，空需要审核
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr = moneyMore.getLoanJsonList() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()
								+ moneyMore.getTransferAction() + moneyMore.getAction() + moneyMore.getTransferType() + moneyMore.getNeedAudit()
								/*+ moneyMore.getRandomTimeStamp()*/ + moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String param=ThirdPayWebClient.generateParams(map,moneyMore.CHARSETUTF8);
				logger.info("发红包调用双乾支付网转账接口参数："+param);
				String outStr = ThirdPayWebClient.getUndecodeByPost(moneyMorePayurl, param, moneyMore.CHARSETUTF8,120000);
				List<Object> listr = Common.JSONDecodeList(outStr,LoanTransferReturnBean.class);
				LoanTransferReturnBean returnBean = new LoanTransferReturnBean();
				if(listr.size()>0){
					returnBean = (LoanTransferReturnBean) listr.get(0);
				}
				if(returnBean.getResultCode().toString().equals("88")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("平台账户划款转账成功");
				}else{
    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
    				commonResponse.setResponseMsg("平台账户划款转账失败");
    			}
				commonResponse.setRequestInfo(outStr);
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
				commonResponse.setResponseMsg("平台账户划款转账基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
			commonResponse.setResponseMsg("平台账户划款转账接口对接失败");
		}
		return commonResponse;
	}
	
	/**
	 * 
	 * 转账接口--------------->>(p2p还款)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse repayment(CommonRequst commonRequst){
		logger.info("调用双乾支付还款接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				//生成转账列表
				List<CommonRequestInvestRecord> repaymemntList = commonRequst.getRepaymemntList();
				List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
				//设置LoanJsonList还款列表
				BigDecimal feeMoney = new BigDecimal(0);
				for(CommonRequestInvestRecord temp:repaymemntList){
					String requestNo = ContextUtil.createRuestNumber();
					if(temp.getAmount()!=null&&!temp.getAmount().toString().equals("0.00")){
						BigDecimal money = new BigDecimal(0);
						if(temp.getFee()!=null&&!temp.getFee().toString().equals("0.00")){
							money=temp.getAmount().subtract(temp.getFee());//减掉平台服务费
							feeMoney=feeMoney.add(temp.getFee());//计算平台服务费
						}else{
							money = temp.getAmount();
						}
						MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
						mlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//借款人第三方标识
						mlib.setLoanInMoneymoremore(temp.getInvest_thirdPayConfigId());//投资人第三方标识
						mlib.setOrderNo(requestNo);//交易流水号
						mlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId()+"_"+commonRequst.getRemark1());//标的编号
						mlib.setAmount(money.setScale(2).toString());//交易金额
						mlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
						mlib.setTransferName(commonRequst.getTransferName());//用途
						mlib.setRemark("还款");//定义备注
						if(temp.getFee().compareTo(temp.getAmount())==0){
							
						}else{
							listmlib.add(mlib);
						}
					}
				}
				//增加平台服务费
				if(feeMoney.compareTo(new BigDecimal(0))>0){
					String requestNum = ContextUtil.createRuestNumber();
					MadaiLoanInfoBean feemlib = new MadaiLoanInfoBean();
					feemlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//借款人第三方标识
					feemlib.setLoanInMoneymoremore(moneyMore.getPlatformMoneymoremore());//投资人第三方标识
					feemlib.setOrderNo(requestNum);//交易流水号
					feemlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId()+"_"+commonRequst.getRemark1());//标的编号
					feemlib.setAmount(feeMoney.setScale(2).toString());//交易金额
					feemlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
					feemlib.setTransferName(commonRequst.getTransferName());//用途
					feemlib.setRemark("平台服务费收取");//定义备注
					listmlib.add(feemlib);
				}
				moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
				moneyMore.setTransferAction(moneyMore.TACTION_1);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_1);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TTYPE_2);//转账方式，1.桥连	2.直连
				moneyMore.setNeedAudit(moneyMore.ACTION_1);//通过是否需要审核，空需要审核，1自动通过
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr=moneyMore.getLoanJsonList() +  moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() + moneyMore.getTransferAction() 
								+ moneyMore.getAction() +  moneyMore.getTransferType() + moneyMore.getNeedAudit() /*+ moneyMore.getRandomTimeStamp()*/
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付网转账接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("还款请求申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("还款请求申请失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("还款请求对接失败");
		}
		return commonResponse;
	}
	

	
	
	/**
	 * 
	 * 转账接口--------------->>(p2p还款)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse compotory(CommonRequst commonRequst){
		logger.info("调用双乾支付还款接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				//生成转账列表
				List<CommonRequestInvestRecord> repaymemntList = commonRequst.getRepaymemntList();
				List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
				//设置LoanJsonList还款列表
				BigDecimal feeMoney = new BigDecimal(0);
				for(CommonRequestInvestRecord temp:repaymemntList){
					String requestNo = ContextUtil.createRuestNumber();
					if(temp.getAmount()!=null&&!temp.getAmount().toString().equals("0.00")){
						BigDecimal money = new BigDecimal(0);
						if(temp.getFee()!=null&&!temp.getFee().toString().equals("0.00")){
							money=temp.getAmount().subtract(temp.getFee());//减掉平台服务费
							feeMoney=feeMoney.add(temp.getFee());//计算平台服务费
						}else{
							money = temp.getAmount();
						}
						MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
						mlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//借款人第三方标识
						mlib.setLoanInMoneymoremore(temp.getInvest_thirdPayConfigId());//投资人第三方标识
						mlib.setOrderNo(requestNo);//交易流水号
						mlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId());//标的编号
						mlib.setAmount(money.setScale(2).toString());//交易金额
						mlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
						mlib.setTransferName(commonRequst.getTransferName());//用途
						mlib.setRemark("还款");//定义备注
						listmlib.add(mlib);
					}
				}
				//增加平台服务费
				if(feeMoney.compareTo(new BigDecimal(0))>0){
					String requestNum = ContextUtil.createRuestNumber();
					MadaiLoanInfoBean feemlib = new MadaiLoanInfoBean();
					feemlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//借款人第三方标识
					feemlib.setLoanInMoneymoremore(moneyMore.getPlatformMoneymoremore());//投资人第三方标识
					feemlib.setOrderNo(requestNum);//交易流水号
					feemlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId());//标的编号
					feemlib.setAmount(feeMoney.setScale(2).toString());//交易金额
					feemlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
					feemlib.setTransferName(commonRequst.getTransferName());//用途
					feemlib.setRemark("平台服务费收取");//定义备注
					listmlib.add(feemlib);
				}
				moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
				moneyMore.setTransferAction(moneyMore.TACTION_2);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_1);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TACTION_1);//转账方式，1.桥连	2.直连
				moneyMore.setNeedAudit(moneyMore.ACTION_1);//通过是否需要审核，空需要审核，1自动通过
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr=moneyMore.getLoanJsonList() +  moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() + moneyMore.getTransferAction() 
								+ moneyMore.getAction() +  moneyMore.getTransferType() + moneyMore.getNeedAudit() /*+ moneyMore.getRandomTimeStamp()*/
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付网转账接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("还款请求申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("还款请求申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("还款请求对接失败");
		}
		return commonResponse;
	}

	
	
	
	
	/**
	 * 
	 * 转账接口--------------->>(理财计划购买)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse transferPlan(CommonRequst commonRequst){
		logger.info("调用双乾支付购买理财计划开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse = new CommonResponse();
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);//跳转至手机端的页面
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				//生成转账列表
				List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
				MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
				mlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//投资人第三方标识
				if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMPLATFORM)){//根据业务用途判断转账方向（转账给平台）
					mlib.setLoanInMoneymoremore(moneyMore.getPlatformMoneymoremore());
				}else{//转账给个人
					mlib.setLoanInMoneymoremore(commonRequst.getLoaner_thirdPayflagId());//借款人第三方标识
				}
				mlib.setOrderNo(commonRequst.getRequsetNo());//交易流水号
				mlib.setBatchNo(commonRequst.getBidType()+"_"+commonRequst.getBidId());//标的编号
				mlib.setAmount(commonRequst.getAmount().subtract(commonRequst.getFee()).toString());//交易金额
				mlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
				mlib.setTransferName(commonRequst.getTransferName());//用途
				mlib.setRemark(commonRequst.getRemark1());//定义备注
				listmlib.add(mlib);
				//设置 转账列表
				moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
				
				//增加平台服务费
				if(commonRequst.getFee().compareTo(new BigDecimal(0))>0){
					String requestNum = ContextUtil.createRuestNumber();
					MadaiLoanInfoBean feemlib = new MadaiLoanInfoBean();
					feemlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//借款人第三方标识
					feemlib.setLoanInMoneymoremore(moneyMore.getPlatformMoneymoremore());//投资人第三方标识
					feemlib.setOrderNo(requestNum);//交易流水号
					feemlib.setBatchNo(commonRequst.getBidType()+"_"+commonRequst.getBidId());//标的编号
					feemlib.setAmount(commonRequst.getFee().setScale(2).toString());//交易金额
					feemlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
					feemlib.setTransferName(commonRequst.getTransferName());//用途
					feemlib.setRemark("理财计划加入费用");//定义备注
					listmlib.add(feemlib);
				}
				
				moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
				moneyMore.setTransferAction(moneyMore.TACTION_1);//转账类型1.投标，2.还款，	3.其他
				moneyMore.setAction(moneyMore.ACTION_1);//操作类型1.手动转账，2.自动转账
				moneyMore.setTransferType(moneyMore.TTYPE_2);//转账方式，1.桥连	2.直连
			//	moneyMore.setNeedAudit(moneyMore.ACTION_1);//通过是否需要审核，空需要审核，1自动通过
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1，订单号
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());////自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());////自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TRANSFER;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TRANSFER;
				}
				//加密数据字符串
				String dataStr=moneyMore.getLoanJsonList() +  moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() + moneyMore.getTransferAction() 
								+ moneyMore.getAction() +  moneyMore.getTransferType() /*+ moneyMore.getNeedAudit() + moneyMore.getRandomTimeStamp()*/
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				moneyMore.setLoanJsonList(URLEncoder.encode(moneyMore.getLoanJsonList(),"utf-8"));
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				System.out.println("map="+map);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付购买理财计划接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("通用转账成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("通用转账成功");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("通用转账基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("通用转账还款请求对接失败");
		}
		return commonResponse;
	}
	
	/**
	 * 余额查询接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse balanceQueryMoneys(CommonRequst commonRequst){
		logger.info("调用双乾支付余额查询接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=generatePublicData(true,true,true,false);
			if(moneyMore!=null){
				moneyMore.setPlatformId(commonRequst.getThirdPayConfigId());//查询的第三方标识号
				moneyMore.setPlatformType(1);//平台乾多多账户类型，当查询平台账户时必填，1.托管账户，2.自有账户
				//moneyMore.setQueryType(1);
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_QUERY;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_QUERY;
				}
				//加密数据字符串
				String dataStr = moneyMore.getPlatformId() + moneyMore.getPlatformType() /*+moneyMore.getQueryType()*/
								+ moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String param=ThirdPayWebClient.generateParams(map,MoneyMore.CHARSETUTF8);
				String  retdata=ThirdPayWebClient.getWebContentByPost(moneyMorePayurl, param,MoneyMore.CHARSETUTF8,120000);
				logger.info("注册用户查询结果："+retdata);
				if(retdata!=null){
					if(retdata!=null&&!retdata.equals("")){
						String[] money=retdata.replace("\r", "").replace("\n", "").split("\\|");
						commonResponse.setBalance(new BigDecimal(money[0]));//网贷平台可提现余额
						commonResponse.setAvailableAmount(new BigDecimal(money[1]));//可投标余额(子账户可用余额+公共账户可用余额)
						commonResponse.setFreezeAmount(new BigDecimal(money[2]));//子账户冻结余额
						commonResponse.setCustmemberStatus("已激活");
					}
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("用户余额查询成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户余额查询失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("余额查询接口基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("余额查询接口接对接失败");
		}
		return commonResponse;
	}
	/**
	 * 注册信息认证
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse balanceQueryUserInfo(CommonRequst commonRequst){
		logger.info("调用双乾支付注册信息认证接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=generatePublicData(true,true,true,false);
			if(moneyMore!=null){
				moneyMore.setPlatformId(commonRequst.getThirdPayConfigId());//查询的第三方标识号
				moneyMore.setPlatformType(1);//平台乾多多账户类型，当查询平台账户时必填，1.托管账户，2.自有账户
				moneyMore.setQueryType(1);
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_QUERY;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_QUERY;
				}
				//加密数据字符串
				String dataStr = moneyMore.getPlatformId() + moneyMore.getPlatformType() +moneyMore.getQueryType()
								+ moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String param=ThirdPayWebClient.generateParams(map,MoneyMore.CHARSETUTF8);
				String  retdata=ThirdPayWebClient.getWebContentByPost(moneyMorePayurl, param,MoneyMore.CHARSETUTF8,120000);
				System.out.println("retdata="+retdata);
				logger.info("注册用户查询结果："+retdata);
				if(retdata!=null){
					if(retdata!=null&&!retdata.equals("")){
						String[] money=retdata.replace("\r", "").replace("\n", "").split("\\|");
						commonResponse.setBalance(new BigDecimal(money[0]));//网贷平台可提现余额
						commonResponse.setAvailableAmount(new BigDecimal(money[1]));//可投标余额(子账户可用余额+公共账户可用余额)
						commonResponse.setFreezeAmount(new BigDecimal(money[2]));//子账户冻结余额
					}
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("用户信息查询成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("用户信息查询失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			commonResponse.setResponseMsg("账接口接对接失败");
		}
		return commonResponse;
	}
	/**
	 * 充值接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse rechargeMoney(CommonRequst commonRequst){
		logger.info("调用双乾支付充值接口开始时间："+new Date());
		CommonResponse cr=new CommonResponse();
		cr.setCommonRequst(commonRequst);
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);//如果是手机端则在回调中添加isMobile=1字段
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				moneyMore.setRechargeMoneymoremore(commonRequst.getThirdPayConfigId());//充值的钱多多标识
			//	moneyMore.setRechargeMoneymoremore(moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString());//充值的钱多多标识
				moneyMore.setOrderNo(commonRequst.getRequsetNo());//交易流水号
				moneyMore.setAmount(commonRequst.getAmount().doubleValue());//交易金额
				if(commonRequst.getRechargeType()!= null){
					moneyMore.setRechargeType(commonRequst.getRechargeType());//充值类型
				}
				if(commonRequst.getFeeType() != null){
					moneyMore.setFeeType(commonRequst.getFeeType());//费用类型
				}
				
				moneyMore.setCardNo(commonRequst.getCardNumber() == null ? "" : commonRequst.getCardNumber());//银行卡号
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1
				moneyMore.setRemark2(commonRequst.getRemark2() == null ? "" : commonRequst.getRemark2());//自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());//自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_RECHARGE;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_RECHARGE;
				}
				String rechargeType = "";
				if(null != commonRequst.getRechargeType()){
					rechargeType = commonRequst.getRechargeType().toString();
				}
				String feeType = "";
				if(null != commonRequst.getFeeType()){
					feeType = commonRequst.getFeeType().toString();
				}
				//加密数据字符串
				String dataStr = moneyMore.getRechargeMoneymoremore() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()+ moneyMore.getOrderNo() 
								 + moneyMore.getAmount() +rechargeType + feeType + moneyMore.getCardNo() /*+ moneyMore.getRandomTimeStamp()*/ 
								 + moneyMore.getRemark1()  + moneyMore.getRemark2()  + moneyMore.getRemark3() + moneyMore.getReturnURL()  + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				cr.setRequestInfo(rett[1]);
				cr.setReturnType(CommonResponse.RETURNTYPE_HTML);
				logger.info("调用双乾支付充值接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					cr.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					cr.setResponseMsg("用户充值申请提交");
				}else{
					cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					cr.setResponseMsg(rett[1]);
				}
			
			}else{
				cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				cr.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			cr.setResponseMsg("账接口接对接失败"+e.getMessage());
		}
		return cr;
	}
	/**
	 * 提现接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse withdrawsMoney(CommonRequst commonRequst){
		logger.info("调用双乾支付提现接口开始时间："+new Date());
		CommonResponse cr=new  CommonResponse();
		cr.setCommonRequst(commonRequst);
		Map moneyMoreConfigMap=moneyMoreProperty();  
		//得到公钥
		String publickey =moneyMoreConfigMap.get("MM_PublicKey").toString();
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				SignUtil rsa = SignUtil.getInstance();
				moneyMore.setWithdrawMoneymoremore(commonRequst.getThirdPayConfigId());//提现人乾多多标识
				moneyMore.setCity(commonRequst.getCity());//开户行城市
				moneyMore.setProvince(commonRequst.getProvince());//开户行省份
				moneyMore.setBranchBankName(commonRequst.getBankBranchName() == null ? "" : commonRequst.getBankBranchName());//开户行支行名称
				moneyMore.setBankCode(commonRequst.getBankCode());//银行代码
				moneyMore.setOrderNo(commonRequst.getRequsetNo());//交易流水号
				moneyMore.setAmount(commonRequst.getAmount().doubleValue());//交易金额
				moneyMore.setFeeQuota(commonRequst.getFee().doubleValue());//用户承担手续费
				moneyMore.setCardNo(rsa.encryptData(commonRequst.getBankCardNumber(), publickey).replaceAll("\r", "").replaceAll("\n", ""));//银行卡号
				moneyMore.setCardType(Integer.valueOf(commonRequst.getBankCardType()));//银行卡类型
				moneyMore.setRemark1(commonRequst.getRequsetNo());//自定义备注1
				moneyMore.setRemark2(commonRequst.getRemark2() == null ? "" : commonRequst.getRemark2());//自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3() == null ? "" : commonRequst.getRemark3());//自定义备注3
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_WITHDRAWALS;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_WITHDRAWALS;
				}
				//加密数据字符串
				String dataStr = moneyMore.getWithdrawMoneymoremore() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() 
								+ moneyMore.getOrderNo() + moneyMore.getAmount() + moneyMore.getFeeQuota() + commonRequst.getBankCardNumber() 
								+ moneyMore.getCardType() + moneyMore.getBankCode() + moneyMore.getBranchBankName() 
								+ moneyMore.getProvince() + moneyMore.getCity() /*+ moneyMore.getRandomTimeStamp() */
							    + moneyMore.getRemark1()  + moneyMore.getRemark2()  + moneyMore.getRemark3() + moneyMore.getReturnURL()  + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				cr.setRequestInfo(rett[1]);
				cr.setReturnType(CommonResponse.RETURNTYPE_HTML);
				logger.info("调用双乾支付提现接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					cr.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					cr.setResponseMsg("用户提现申请提交");
				}else{
					cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					cr.setResponseMsg(rett[1]);
				}
			}else{
				cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				cr.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			cr.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			cr.setResponseMsg("账接口接对接失败"+e.getMessage());
		}
		return cr;
	}
	/**
	 * 授权接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse authorize(CommonRequst commonRequst){
		logger.info("调用双乾支付授权接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				moneyMore.setMoneymoremoreId(commonRequst.getThirdPayConfigId());//用户钱多多标识
				moneyMore.setAuthorizeTypeOpen(commonRequst.getAuthorizeTypeOpen()==null?"":commonRequst.getAuthorizeTypeOpen());//开启授权类型
				moneyMore.setAuthorizeTypeClose(commonRequst.getAuthorizeTypeClose()==null?"":commonRequst.getAuthorizeTypeClose());//关闭授权类型
				moneyMore.setRemark1(commonRequst.getRequsetNo()==null?"":commonRequst.getRequsetNo());//自定义备注1
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());//自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_AUTH;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_AUTH;
				}
				//加密数据字符串
				String dataStr = moneyMore.getMoneymoremoreId() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() 
								+ moneyMore.getAuthorizeTypeOpen() +  moneyMore.getAuthorizeTypeClose() /*+  moneyMore.getRandomTimeStamp()*/ 
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() + moneyMore.getReturnURL() +  moneyMore.getNotifyURL();		
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				System.out.println("map="+map);
				logger.info("调用双乾支付授权接口参数："+rett[1]);
				System.out.println("调用双乾支付授权接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("用户授权申请提交成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户授权申请提交失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("用户授权基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户授权接口对接失败");
		}
		return commonResponse;
	}
	/**
	 * 审核接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse transferaudit(CommonRequst commonRequst){
		logger.info("调用双乾支付审核接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		CommonResponse commonResponse=new  CommonResponse();
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				moneyMore=generatePublicData(true,true,true,true);
			}else{
				moneyMore=generatePublicData(true,true,true,false);
			} 
			 if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_LOAN)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKHRBIN)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANPLATF)){
				moneyMore.setAuditType(1);//审核类型，通过
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELUSER)
					||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMCANCELPLATF)){
				moneyMore.setAuditType(2);//审核类型，退回
			}
			moneyMore.setLoanNoList(commonRequst.getLoanNoList());//乾多多流水号列表
			if(moneyMore!=null){
				moneyMore.setRemark1(commonRequst.getRequsetNo()==null?"":commonRequst.getRequsetNo());//自定义备注1
				moneyMore.setRemark2(commonRequst.getRemark2()==null?"":commonRequst.getRemark2());//自定义备注2
				moneyMore.setRemark3(commonRequst.getRemark3()==null?"":commonRequst.getRemark3());
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_AUDIT;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_AUDIT;
				}
				//加密数据字符串
				String dataStr = moneyMore.getLoanNoList() + moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()  + moneyMore.getAuditType() 
								/*+ moneyMore.getRandomTimeStamp()*/ + moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String param=ThirdPayWebClient.generateParams(map,moneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(moneyMorePayurl, param, moneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				
				JSONObject  dataJson=new JSONObject(outStr); 
				String  resultCode=dataJson.getString("ResultCode");
				String  message=dataJson.getString("Message");
				if(resultCode.equals("88")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("审核接口成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("审核接口失败:"+message);
				}
				commonResponse.setRequestInfo(outStr);
				
				
				/*String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付审核接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("用户审核申请提交成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户审核申请提交失败");
				}*/
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("用户审核申请提交基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户审核申请提交接对接失败");
		}
		return commonResponse;
	}
	/**
	 * 姓名匹配接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] identityMatching(CommonRequst commonRequst){
		logger.info("调用双乾支付姓名匹配接口开始时间："+new Date());
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore =generatePublicData(true,true,true,false);
			if(moneyMore!=null){
				moneyMore.setIdentityJsonList(commonRequst.getIdentityJsonList());
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TOREG;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TOREG;
				}
				//加密数据字符串
				String dataStr =  moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString() + moneyMore.getIdentityJsonList() + moneyMore.getRandomTimeStamp() + moneyMore.getNotifyURL();
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付姓名匹配接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					ret[0]=ThirdPayConstants.RECOD_SUCCESS;
					ret[1]="用户姓名匹配申请提交";
					ret[2]=commonRequst;
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]=rett[1];
					ret[2]=commonRequst;
				}
			
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="账接口接对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	/**
	 * 对账接口，充值、提现、转账查询
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse reconciliation(CommonRequst commonRequst){
		logger.info("调用双乾对账接口接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		Map moneyMoreConfigMap=moneyMoreProperty();       
		Object[]  ret=new Object[3];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=generatePublicData(true,true,true,false);
			if(moneyMore!=null){
				if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){//充值
					moneyMore.setAction(1);
				}else if(commonRequst.getQueryType().equals("RECHARGE_RECORD")){//取现
					moneyMore.setAction(2);
				}else{
					//moneyMore.setAction();
				}
				
				moneyMore.setLoanNo("");//乾多多流水号
				moneyMore.setOrderNo(commonRequst.getQueryRequsetNo());//网贷平台订单号
				moneyMore.setBatchNo("");//网贷平台标号
				moneyMore.setBeginTime("");//开始时间
				moneyMore.setEndTime("");//结束时间s
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_LOANORDERQUERY;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_LOANORDERQUERY;
				}
				//加密数据字符串
				String dataStr = "";
				if(commonRequst.getQueryType().equals("PAYMENT_RECORD")||commonRequst.getQueryType().equals("REPAYMENT_RECORD")){//转账记录查询  
					dataStr = moneyMore.getPlatformMoneymoremore()
					/*+moneyMore.getAction()*/
					+moneyMore.getLoanNo()+moneyMore.getOrderNo()
					+moneyMore.getBatchNo()
	                 +moneyMore.getBeginTime()+moneyMore.getEndTime();
				}else{
					dataStr = moneyMore.getPlatformMoneymoremore()
					+moneyMore.getAction()
					+moneyMore.getLoanNo()+moneyMore.getOrderNo()
					+moneyMore.getBatchNo()
	                 +moneyMore.getBeginTime()+moneyMore.getEndTime();
				}
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String param=ThirdPayWebClient.generateParams(map,MoneyMore.CHARSETUTF8);
				System.out.println("paramparam="+param);
				String rett=ThirdPayWebClient.getWebContentByPost(moneyMorePayurl, param,MoneyMore.CHARSETUTF8,12000);
				System.out.println("rett="+rett);
				if(rett!=null&&!rett.equals("")){
					commonResponse.setRequestInfo(rett);
					rett=rett.replace("[", "").replace("]", "");
					List<CommonRecord> recordList= null;
					if(commonRequst.getQueryType().equals("PAYMENT_RECORD")||commonRequst.getQueryType().equals("REPAYMENT_RECORD")){//转账记录
						 CommonRecord commonRecord=new CommonRecord();
						 recordList =new ArrayList<CommonRecord>();
						JSONObject  dataJson=new JSONObject(rett); 
						String transferName = dataJson.getString("TransferName").toString();
						if(commonRequst.getQueryType().equals("PAYMENT_RECORD")){//放款记录
							if(transferName.equals(ThirdPayConstants.TN_LOAN)){
								commonRecord.setSourceUserNo(dataJson.getString("LoanInMoneymoremore"));
								commonRecord.setPaymentAmount(dataJson.getString("Amount"));
								Date d = sdf.parse(dataJson.getString("TransferTime").toString());
								String time = sdf2.format(d);
								commonRecord.setCreateTime(time);
								
								String status = dataJson.getString("ActState").toString();
								if(status.equals("0")){
									commonRecord.setStatus("未操作");
								}else if(status.equals("1")){
									commonRecord.setStatus("已通过");
								}else if(status.equals("2")){
									commonRecord.setStatus("已退回");
								}else if(status.equals("3")){
									commonRecord.setStatus("自动通过");
								}else{
									commonRecord.setStatus(dataJson.getString("WithdrawsState"));
								}
								recordList.add(commonRecord);
								commonResponse.setRecordList(recordList);
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
								commonResponse.setResponseMsg("用户对账申请查询成功");
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("用户对账申请查询类型错误");
							}
						}else if(commonRequst.getQueryType().equals("REPAYMENT_RECORD")){//还款记录
							if(transferName.equals(ThirdPayConstants.TN_REPAYMENT)||transferName.equals(ThirdPayConstants.TN_HELPPAY)){
								commonRecord.setTargetUserNo(dataJson.getString("LoanInMoneymoremore"));
								commonRecord.setRepaymentAmount(dataJson.getString("Amount"));
								Date d = sdf.parse(dataJson.getString("TransferTime").toString());
								String time = sdf2.format(d);
								commonRecord.setCreateTime(time);
								
								String status = dataJson.getString("ActState").toString();
								if(status.equals("0")){
									commonRecord.setStatus("未操作");
								}else if(status.equals("1")){
									commonRecord.setStatus("已通过");
								}else if(status.equals("2")){
									commonRecord.setStatus("已退回");
								}else if(status.equals("3")){
									commonRecord.setStatus("自动通过");
								}else{
									commonRecord.setStatus(dataJson.getString("WithdrawsState"));
								}
								recordList.add(commonRecord);
								commonResponse.setRecordList(recordList);
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
								commonResponse.setResponseMsg("用户对账申请查询成功");
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("用户对账申请查询类型错误");
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("用户对账申请查询类型错误");
						}
					}else if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){//充值对账
						 CommonRecord commonRecord=new CommonRecord();
						 recordList =new ArrayList<CommonRecord>();
						JSONObject  dataJson=new JSONObject(rett); 
						commonRecord.setAmount(dataJson.getString("Amount"));
						commonRecord.setUserNo(dataJson.getString("RechargeMoneymoremore"));
						Date d = sdf.parse(dataJson.getString("RechargeTime").toString());
						String time = sdf2.format(d);
						commonRecord.setCreateTime(time);
						String status=dataJson.getString("RechargeState").toString();
						if(status.equals("0")){
							commonRecord.setStatus("未充值");
						}else if(status.equals("1")){
							commonRecord.setStatus("成功");
						}else if(status.equals("2")){
							commonRecord.setStatus("失败");
						}else{
							commonRecord.setStatus(dataJson.getString("RechargeState"));
						}
						recordList.add(commonRecord);
						commonResponse.setRecordList(recordList);
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("用户对账申请查询成功");
					}else if(commonRequst.getQueryType().equals("RECHARGE_RECORD")){//取现对账
						 CommonRecord commonRecord=new CommonRecord();
						 recordList =new ArrayList<CommonRecord>();
						JSONObject  dataJson=new JSONObject(rett); 
						commonRecord.setAmount(dataJson.getString("Amount"));
						commonRecord.setUserNo(dataJson.getString("WithdrawMoneymoremore"));
						Date d = sdf.parse(dataJson.getString("WithdrawsTime").toString());
						String time = sdf2.format(d);
						commonRecord.setCreateTime(time);
						String status = dataJson.getString("WithdrawsState").toString();
						if(status.equals("0")){
							commonRecord.setStatus("已提交");
						}else if(status.equals("1")){
							commonRecord.setStatus("成功");
						}else if(status.equals("2")){
							commonRecord.setStatus("已退回");
						}else if(status.equals("3")){
							commonRecord.setStatus("待平台审核");
						}else{
							commonRecord.setStatus(dataJson.getString("WithdrawsState"));
						}
						recordList.add(commonRecord);
						commonResponse.setRecordList(recordList);
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("用户对账申请查询成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("用户对账申请查询类型错误");
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户对账申请查询失败,请检查流水号是否正确");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("对账接口基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("对账接口对接失败");
		}
		return commonResponse;
	}
	/**
	 * 认证、提现银行卡绑定、代扣授权三合一接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse toloanfastpay(CommonRequst commonRequst){
		logger.info("调用双乾支付三合一接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		Map moneyMoreConfigMap=moneyMoreProperty();  
		//得到公钥
		String publickey =moneyMoreConfigMap.get("MM_PublicKey").toString();
		try {
			//公共商户号，第一个参数是页面返回网址，第二个参数是后台通知地址，第三个参数表示是否启用防抵赖 
			MoneyMore moneyMore=null;
			if(commonRequst.getIsMobile()!=null&&!"".equals(commonRequst.getIsMobile())&&"1".equals(commonRequst.getIsMobile())){
				 moneyMore=generatePublicData(true,true,true,true);//手机端更改回调方法路径
			}else{
				 moneyMore=generatePublicData(true,true,true,false);
			}
			if(moneyMore!=null){
				SignUtil rsa = SignUtil.getInstance();
				moneyMore.setMoneymoremoreId(commonRequst.getThirdPayConfigId());//用户钱多多标识
				moneyMore.setAction(commonRequst.getAction());//操作类型
				moneyMore.setCardNo("");//银行卡号
				moneyMore.setWithholdBeginDate(commonRequst.getWithholdBeginDate()!=null?commonRequst.getWithholdBeginDate():"");//代扣开始日期
				moneyMore.setWithholdEndDate(commonRequst.getWithholdEndDate()!=null?commonRequst.getWithholdEndDate():"");//代扣结束日期
				moneyMore.setSingleWithholdLimit(commonRequst.getSingleWithholdLimit());//单笔代扣限额
				moneyMore.setTotalWithholdLimit(commonRequst.getTotalWithholdLimit());//代扣总限额
				moneyMore.setRemark1(commonRequst.getRequsetNo()!=null?commonRequst.getRequsetNo():"");//请求第三方的流水号
				moneyMore.setRemark2(commonRequst.getRemark2()!=null?commonRequst.getRemark2():"");//自定义备注2
				moneyMore.setRemark3("");
				moneyMore.setRandomTimeStamp("");
				//双乾支付调用地址
				String moneyMorePayurl=moneyMorePayUrl();
				if(moneyMorePayurl.equals("normal")){
					moneyMorePayurl=MoneyMore.MONEYMORE_NORMAL_TOLOANFASTPAY;
				}else{
					moneyMorePayurl=moneyMorePayurl+MoneyMore.MONEYMORE_TEST_TOLOANFASTPAY;
				}
				//加密数据字符串
				String dataStr = moneyMore.getMoneymoremoreId() +  moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString()  + moneyMore.getAction()
								+ "" + moneyMore.getWithholdBeginDate() + moneyMore.getWithholdEndDate() 
								+ (moneyMore.getSingleWithholdLimit()!=null?moneyMore.getSingleWithholdLimit():"") + (moneyMore.getTotalWithholdLimit()!=null?moneyMore.getTotalWithholdLimit():"") + moneyMore.getRandomTimeStamp()
								+ moneyMore.getRemark1() + moneyMore.getRemark2() + moneyMore.getRemark3() 
								+ moneyMore.getReturnURL() + moneyMore.getNotifyURL();
				System.out.println(dataStr);
				//实体对象整合成map对象，并生成签名
				Map map =getParameterMap(moneyMore,dataStr,moneyMoreConfigMap);
				String [] rett=ThirdPayWebClient.operateParameter(moneyMorePayurl, map,MoneyMore.CHARSETUTF8);
				logger.info("调用双乾支付三合一接口参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
		    		commonResponse.setResponseMsg("申请绑定银行卡成功");
		    		commonResponse.setCommonRequst(commonRequst);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("申请绑定银行卡失败");
				}
			
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("双乾支付三合一接口参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("双乾支付三合一接口对接失败");
		}
		return commonResponse;
	}
	
	/**
	 * 生成转账列表 个人转账给个人 或者 平台
	 * @param moneyMoreMore
	 * @return
	 */
	public static MoneyMore loanJsonList(CommonRequst commonRequst,MoneyMore moneyMore){	
		Map moneyMoreConfigMap=moneyMoreProperty();
		List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
		MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
		mlib.setLoanOutMoneymoremore(commonRequst.getThirdPayConfigId());//投资人第三方标识
		if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMPLATFORM)
			||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)
			||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//根据业务用途判断转账方向（转账给平台）  
			mlib.setLoanInMoneymoremore(moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString());
		}else{//转账给个人
			mlib.setLoanInMoneymoremore(commonRequst.getLoaner_thirdPayflagId());//借款人第三方标识
		}
		mlib.setOrderNo(commonRequst.getRequsetNo());//交易流水号
		mlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId());//标的编号
		mlib.setAmount(commonRequst.getAmount().toString());//交易金额
		mlib.setFullAmount(commonRequst.getPlanMoney().toString());//满标标额
		mlib.setTransferName(commonRequst.getTransferName());//用途
		mlib.setRemark(commonRequst.getRemark1());//定义备注
		listmlib.add(mlib);
		//设置 转账列表
		moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
		return moneyMore;
		
	}
	
	/**
	 * 返回注册绑定
	 * @param resultBean
	 * @return
	 */
	private static ResultBean setBindCodeMsg(ResultBean resultBean){
		System.out.println("注册返回==="+resultBean.getResultCode());
		if(resultBean.getResultCode().equals("88")){
			resultBean.setCodeMsg("成功！");
		}else if(resultBean.getResultCode().equals("01")){
			resultBean.setCodeMsg("注册类型错误");
		}else if(resultBean.getResultCode().equals("02")){
			resultBean.setCodeMsg("手机号错误");
		}else if(resultBean.getResultCode().equals("03")){
			resultBean.setCodeMsg("邮箱错误");
		}else if(resultBean.getResultCode().equals("04")){
			resultBean.setCodeMsg("真实姓名错误");
		}else if(resultBean.getResultCode().equals("05")){
			resultBean.setCodeMsg("身份证号错误");
		}else if(resultBean.getResultCode().equals("06")){
			resultBean.setCodeMsg("身份证图片错误");
		}else if(resultBean.getResultCode().equals("07")){
			resultBean.setCodeMsg("平台乾多多标识错误");
		}else if(resultBean.getResultCode().equals("08")){
			resultBean.setCodeMsg("加密验证失败");
		}else if(resultBean.getResultCode().equals("09")){
			resultBean.setCodeMsg("手机和邮箱已存在");
		}else if(resultBean.getResultCode().equals("10")){
			resultBean.setCodeMsg("邮箱已存在");
		}else if(resultBean.getResultCode().equals("11")){
			resultBean.setCodeMsg("手机已存在");
		}else if(resultBean.getResultCode().equals("12")){
			resultBean.setCodeMsg("支付密码错误");
		}else if(resultBean.getResultCode().equals("13")){
			resultBean.setCodeMsg("安保问题错误");
		}else if(resultBean.getResultCode().equals("14")){
			resultBean.setCodeMsg("用户网贷平台账号错误");
		}else if(resultBean.getResultCode().equals("15")){
			resultBean.setCodeMsg("用户网贷平台账号错误");
		}else if(resultBean.getResultCode().equals("16")){
			resultBean.setCodeMsg("网贷平台账号已绑定");
		}
		return resultBean;
	}
	
	/**
	 * 生成转账列表。平台转账给个人
	 * @param moneyMoreMore
	 * @return
	 */
	public static MoneyMore loanJsonListRed(CommonRequst commonRequst,MoneyMore moneyMore){
		Map moneyMoreConfigMap=moneyMoreProperty();
		List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
		MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
		mlib.setLoanOutMoneymoremore(moneyMoreConfigMap.get("MM_PlatformMoneymoremore").toString());//平台账户
		mlib.setLoanInMoneymoremore(commonRequst.getThirdPayConfigId());//收款人第三方标识
		mlib.setOrderNo(commonRequst.getRequsetNo());//交易流水号
		mlib.setBatchNo(commonRequst.getBidType()+commonRequst.getBidId());//标的编号
		mlib.setAmount(commonRequst.getAmount().toString());//交易金额
		mlib.setTransferName(commonRequst.getTransferName());//用途
		mlib.setRemark(commonRequst.getRemark1());//定义备注
		listmlib.add(mlib);
		//设置 转账列表
		moneyMore.setLoanJsonList(Common.JSONEncode(listmlib));
		return moneyMore;
		
	}

}
