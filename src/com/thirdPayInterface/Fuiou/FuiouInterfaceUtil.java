package com.thirdPayInterface.Fuiou;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;
import com.thirdPayInterface.CommonRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayWebClient;
import com.thirdPayInterface.Fuiou.FuiouResponse.FuiouReponse;
import com.thirdPayInterface.Fuiou.FuiouResponse.OpResult;
import com.thirdPayInterface.Fuiou.FuiouResponse.Plain;
import com.thirdPayInterface.Fuiou.FuiouResponse.Result;
import com.thirdPayInterface.Fuiou.FuiouUtil.SecurityUtils;
import com.thirdPayInterface.Fuiou.FuiouUtil.StringUtil;

@SuppressWarnings("unchecked")
public class FuiouInterfaceUtil { 
	
	private static Log logger=LogFactory.getLog(FuiouInterfaceUtil.class);
    /**
     * 获取系统配置参数
     */
	private static Map configMap = AppUtil.getConfigMap();
	/**
	 * 第三方支付环境（正式环境和测试环境）
	 */
	private static String  thirdPayEnvironmentType=configMap.containsKey("thirdPayEnvironmentType")?configMap.get("thirdPayEnvironmentType").toString().trim():ThirdPayConstants.THIRDENVIRONMENT1;
	
	/**
	 * 富有公共数据获取方法
	 * @return
	 */
	public static Fuiou generatePublicData(boolean pageCallBack, boolean notifyCallBack){
		Fuiou fuiou=new Fuiou();
		Map thirdPayConfig=fuiouProperty();
		if(thirdPayConfig!=null&&!thirdPayConfig.isEmpty()){//判断map中是否为空和有数值
			fuiou.setMchnt_cd(thirdPayConfig.get("thirdPay_fuiou_platNumber").toString().trim());
			String BasePath=ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath() + "/";
			if(notifyCallBack){
				fuiou.setBack_notify_url(BasePath+thirdPayConfig.get("thirdPay_fuiou_notifyUrl").toString().trim());
			}
			if(pageCallBack){
				fuiou.setPage_notify_url(BasePath+thirdPayConfig.get("thirdPay_fuiou_pageCallUrl").toString().trim());
			}
			return fuiou;
		}else{
			return null;
		}
	}
	
	/**读取富有属性文件
	 * 获取富有金账户配置文件信息（正式环境和测试环境的配置文件）
	 * @return
	 */
	public static Map fuiouProperty() {
		Map fuiouConfigMap=new HashMap();
		try{
			Properties props =  new  Properties(); 
			InputStream in=null; 
			if(thirdPayEnvironmentType.equals(ThirdPayConstants.THIRDENVIRONMENT0)){//正式环境
				in = FuiouInterfaceUtil.class.getResourceAsStream("FuiouNormalEnvironment.properties"); 
			}else{
				in= FuiouInterfaceUtil.class.getResourceAsStream("FuiouTestEnvironment.properties"); 
			}
			if(in!=null){
				props.load(in);
		    	Iterator it= props.keySet().iterator();
		    	while(it.hasNext()){
		    		String key=(String)it.next();
		    		fuiouConfigMap.put(key, props.getProperty(key));
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
    		logger.error(ex.getMessage());
    	}
		return fuiouConfigMap;
	}

	
	/**生成签名
	 * 富有金账户生成签名方法
	 * @param params
	 * @param charsetutf8
	 * @return
	 */
	private static String autoCreatSignnature(Map<String, String> params,String charsetutf8) {
		
		StringBuilder sign=CreateLinkString(params);
		System.out.println("sign=="+sign.toString());
		String signNature=SecurityUtils.sign(sign.toString());
		System.out.println("signNature=="+signNature);
		return signNature;
		/*StringBuilder sign=CreateLinkString(params);
		System.out.println("sign=="+sign.toString());
		Map fuiouConfigMap=fuiouProperty();
		if(fuiouConfigMap!=null&&!fuiouConfigMap.isEmpty()){
			String privateKeyPath=fuiouConfigMap.get("thirdPay_fuiou_publicKeyPath").toString();
			String url=AppUtil.getAppAbsolutePath();
			String signNature=SecurityUtils.sign(sign.toString(),url.concat(privateKeyPath));
			System.out.println("signNature=="+signNature);
			return signNature;
		}else{
			return null;
		}*/
	}
	
	/**按富有指定格式拼接签名数据
	 * 富有金账户参数按照字母正序规则拼接
	 * @param params
	 * @return
	 */
	private static StringBuilder CreateLinkString(Map<String, String> params) {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		StringBuilder prestr = new StringBuilder();
		String key="";
		String value="";
		StringBuilder prestr1 = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			key=(String) keys.get(i);
			value = (String) params.get(key);
			System.out.println(key+"=="+value);
			if(key.equalsIgnoreCase("signature")){
				continue;
			}else if(value==null||value.equals("null")){
				value="";
			}
			prestr1.append(key).append("|");
			prestr.append(value).append("|");
		}
		System.out.println("明文value顺序是"+prestr);
		System.out.println("明文key顺序是"+prestr1);
		StringBuilder keyvalue=prestr1.deleteCharAt(prestr1.length()-1);
		System.out.println("keyprestr1==="+keyvalue.toString());
		StringBuilder valuekey=prestr.deleteCharAt(prestr.length()-1);
		System.out.println("keyprestr==="+valuekey.toString());
		return valuekey;
	}
	
	/**获得富有地址
	 * 富有金账户获取提交的URL地址
	 * @return
	 */
	private static String fuiouPayUrl() {
		Map thirdPayConfig=fuiouProperty();
		//富有支付调用地址
		String fuiouUrl=thirdPayConfig.get("thirdPay_fuiou_URL").toString();
		return fuiouUrl;
	}
	
	/**获得富有商户号
	 * @return
	 */
	private static String fuiouplatloginName() {
		Map thirdPayConfig=fuiouProperty();
		//富有商户号
		String fuiouUrl=thirdPayConfig.get("thirdPay_fuiou_platloginname").toString();
		return fuiouUrl;
	}
	
	/**
	 * 判断验证签名是否正确
	 * @param ret	
	 * @param commonRequst  CommonRequst对象
	 * @param params  		实体转换后的map对象
	 * @param url			第三方请求action
	 * @param temp  	          提示标语
	 */
	public static void commonSign(Object[] ret,CommonRequst commonRequst,Map<String, String> params,String url,String temp){
		String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
		if(null!=sign){
			params.put("signature", sign);
			//富有金账户支付调用地址
			String fuiouUrl=fuiouPayUrl();
			String[] rett=ThirdPayWebClient.operateParameter(fuiouUrl+url,params,Fuiou.CHARSETUTF8);
			logger.info(temp+"调用富有金账户参数："+rett[1]);
			if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
				ret[0]=ThirdPayConstants.RECOD_SUCCESS;
				ret[1]=temp+"申请提交";
				ret[2]=commonRequst;
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]=rett[1];
				ret[2]=commonRequst;
			}
		}else{
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="生成签名失败";
			ret[2]=commonRequst;
		}
	}
	/**
	 * 接口01--开户注册（个人开通第三方）
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse accountRegister(CommonRequst commonRequst){
		logger.info("调用富有开户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//客户姓名
				fuiou.setCust_nm(commonRequst.getTrueName());
				//身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber());
				//手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//开户行地区代码
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户行行别
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//提现账户开户名
				//fuiou.setCapAcntNm(commonRequst.getTrueName());
				//银行卡号（要加密）
				if(commonRequst.getBankCardNumber()!=null&&!"".equals(commonRequst.getBankCardNumber())){
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				}else{
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(""));
				}
				fuiou.setUser_id_from("");
				//提现密码
				//fuiou.setPassword("");
				//登录密码
				//fuiou.setLpassword("");
				//备注
				//fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=UrlUtils.generateParams(params,Fuiou.CHARSETUTF8);
					String url = "";
					if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//开通企业账户
						url = Fuiou.FUIOUGOLDWEBENREG;
					}else{//开通个人账户
						url = Fuiou.FUIOUGOLDREG;
					}
					String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+url, params, Fuiou.CHARSETUTF8);
				    //String rest = ThirdPayWebClient.getWebContentByPost(fuiouUrl+url, param, Fuiou.CHARSETUTF8, 12000);
					if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
				    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("注册申请提交成功");
						commonResponse.setCommonRequst(commonRequst);
				    }else{
				    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("注册申请提交失败");
						commonResponse.setCommonRequst(commonRequst);
				    }
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户开户注册对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="用户开户注册对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return commonResponse;
	}
	
	/**
	 * 接口01--开户注册（企业开通第三方）
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse accountRefRegister(CommonRequst commonRequst){
		logger.info("调用富有开户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(null!=fuiou){
				//法人姓名
				fuiou.setArtif_nm(commonRequst.getLegal()!=null?commonRequst.getLegal():"");
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				if(commonRequst.getBankCardNumber()!=null&&!"".equals(commonRequst.getBankCardNumber())){
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				}else{
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(""));
				}
				//身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber()!=null?commonRequst.getCardNumber():"");
				//开户行地区代码
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//企业名称
				fuiou.setCust_nm(commonRequst.getTrueName()!=null?commonRequst.getTrueName():"");
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//手机号码
				fuiou.setMobile_no(commonRequst.getTelephone()!=null?commonRequst.getTelephone():"");
				//开户行行别
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//用户在商户系统中的标志
				fuiou.setUser_id_from(commonRequst.getUser_id_from()!=null?commonRequst.getUser_id_from():"");
				//提现账户开户名
				//fuiou.setCapAcntNm(commonRequst.getTrueName());
				//银行卡号（要加密）
				//提现密码
				//fuiou.setPassword("");
				//登录密码 
				//fuiou.setLpassword("");
				//备注
				//fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=UrlUtils.generateParams(params,Fuiou.CHARSETUTF8);
					String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUGOLDWEBENREG, params, Fuiou.CHARSETUTF8);
					if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
				    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("注册申请提交成功");
						commonResponse.setCommonRequst(commonRequst);
				    }else{
				    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("注册申请提交失败");
						commonResponse.setCommonRequst(commonRequst);
				    }
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户开户注册对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="用户开户注册对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return commonResponse;
	}

	/**
	 * 接口02--明细查询接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] detailQuery(CommonRequst commonRequst){
		logger.info("调用富有明细查询接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			DateFormat df=new SimpleDateFormat("yyyyMMdd");
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录Id
				fuiou.setUser_ids(commonRequst.getTelephone());
				//起始时间
				fuiou.setStart_day(df.format(commonRequst.getStartDay()));
				//截止时间
				fuiou.setEnd_day(df.format(commonRequst.getEndDay()));
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					
					String param=UrlUtils.generateParams(params,Fuiou.CHARSETUTF8);
					String outStr =WebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUDEALINFOQUERY,param, Fuiou.CHARSETUTF8,12000); 
					logger.info("明细查询结果："+outStr);
					
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					Plain plain=response.getPlain();
					OpResult result=plain.getOpResultSet().getOpResult();
					if(plain.getResp_code().equals("0000")){
						commonResponse.setRequestNo(plain.getMchnt_txn_ssn());//请求流水号
						commonResponse.setBalance(new BigDecimal(result.getCt_balance()));//期初账面总余额
						commonResponse.setAvailableAmount(new BigDecimal(result.getCa_balance()));//期初可用总余额
						commonResponse.setUnTransfersAmount(new BigDecimal(result.getCu_balance()));//期初未转结总余额
						commonResponse.setFreezeAmount(new BigDecimal(result.getCf_balance()));//期初冻结总余额
						commonResponse.setDetails(result.getDetails());//资金明细
						ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						ret[1]="明细查询成功";
						ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="明细查询失败";
					    ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="明细查询对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 接口03--余额查询接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse balanceQuery(CommonRequst commonRequst){
		logger.info("调用富有余额查询接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			DateFormat df=new SimpleDateFormat("yyyyMMdd");
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//交易日期
				fuiou.setMchnt_txn_dt(df.format(commonRequst.getTransactionTime()));
				//待查询的登录账户
				fuiou.setCust_no(commonRequst.getThirdPayConfigId());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					
					String param=UrlUtils.generateParams(params,Fuiou.CHARSETUTF8);
					String outStr =WebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCURRENTMONEY,param, Fuiou.CHARSETUTF8,12000); 
					logger.info("余额查询结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					Plain plain=response.getPlain();
					//OpResult result=plain.getOpResultSet().getOpResult();
					Result result = plain.getResults().get(0); 
					if(plain.getResp_code().equals("0000")){
						commonResponse.setRequestNo(plain.getMchnt_txn_ssn());//请求流水号
						BigDecimal ct_balance = new BigDecimal((result.getCt_balance()==null||"".equals(result.getCt_balance()))?"0":result.getCt_balance());
						if(ct_balance!=null&&ct_balance.compareTo(new BigDecimal(0))>0){
							commonResponse.setBalance(new BigDecimal((result.getCt_balance()==null||"".equals(result.getCt_balance()))?"0":result.getCt_balance()).divide(new BigDecimal(100)));//期初账面总余额
						}else{
							commonResponse.setBalance(new BigDecimal(0));//期初账面总余额
						}
						BigDecimal ca_balance = new BigDecimal(result.getCa_balance());
						if(ca_balance!=null&&ca_balance.compareTo(new BigDecimal(0))>0){
							commonResponse.setAvailableAmount(new BigDecimal(result.getCa_balance()).divide(new BigDecimal(100)));//期初可用总余额
						}else{
							commonResponse.setAvailableAmount(new BigDecimal(0));//期初可用总余额
						}
						BigDecimal cu_balance = new BigDecimal(result.getCu_balance());
						if(cu_balance!=null&&cu_balance.compareTo(new BigDecimal(0))>0){
							commonResponse.setUnTransfersAmount(new BigDecimal(result.getCu_balance()).divide(new BigDecimal(100)));//期初未转结总余额
						}else{
							commonResponse.setUnTransfersAmount(new BigDecimal(0));//期初未转结总余额
						}
						BigDecimal cf_balance  = new BigDecimal(result.getCf_balance());
						if(cf_balance!=null&&cf_balance.compareTo(new BigDecimal(0))>0){
							commonResponse.setFreezeAmount(new BigDecimal(result.getCf_balance()).divide(new BigDecimal(100)));//期初冻结总余额
						}else{
							commonResponse.setFreezeAmount(new BigDecimal(0));//期初冻结总余额
						}
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("余额查询成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("余额查询失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("余额查询对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 接口04--预授权接口(个人与个人)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse preAuthorization(CommonRequst commonRequst){
		logger.info("调用富有预授权接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		Map thirdPayConfig=fuiouProperty();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//商户号
				fuiou.setMchnt_cd(thirdPayConfig.get("thirdPay_fuiou_platNumber").toString());
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//出账账户
				fuiou.setOut_cust_no(commonRequst.getThirdPayConfigId());
				//入账账户
				fuiou.setIn_cust_no(commonRequst.getLoaner_thirdPayflagId());
				//预授权金额
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
				//备注
				fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("预授权请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUPREAUTO, param, Fuiou.CHARSETUTF8,120000); 
					//String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUPREAUTO, params, Fuiou.CHARSETUTF8);//(fuiouUrl+Fuiou.FUIOUPREAUTO, param, Fuiou.CHARSETUTF8,120000);
					logger.info("预授权结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						if(response.getPlain().getContract_no()!=null){
							commonResponse.setContract_no(response.getPlain().getContract_no());
						}
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("预授权成功");
						commonResponse.setCommonRequst(commonRequst);
					}else if(response.getPlain().getResp_code().equals("3018")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("第三方可用余额不足,请充值");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("预授权失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("预授权对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	/**
	 * 接口05--预授权撤销接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse preAuthorizationCancel(CommonRequst commonRequst){
		logger.info("调用富有预授权撤销接口开始时间："+new Date());
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//出账账户
				fuiou.setOut_cust_no(commonRequst.getThirdPayConfigId());
				//入账账户
				fuiou.setIn_cust_no(commonRequst.getLoaner_thirdPayflagId());
				//预授权合同号
				fuiou.setContract_no(commonRequst.getContractNo());
				//备注
				fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("预授权撤销请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUPREAUTOCANC, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("预授权撤销结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("预授权撤销成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("预授权撤销失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("预授权撤销对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 接口06--转账(商户与个人之间)接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse transfers(CommonRequst commonRequst){
		logger.info("调用富有转账(商户与个人之间)接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//付款登录账户
				if(null==commonRequst.getThirdPayConfigId()||"".equals(commonRequst.getThirdPayConfigId())){
					fuiou.setOut_cust_no(fuiouplatloginName());
				}else{
					fuiou.setOut_cust_no(commonRequst.getThirdPayConfigId());
				}
				//收款登录账户
				if(null==commonRequst.getLoaner_thirdPayflagId()||"".equals(commonRequst.getLoaner_thirdPayflagId())){
					fuiou.setIn_cust_no(fuiouplatloginName());
				}else{
					fuiou.setIn_cust_no(commonRequst.getLoaner_thirdPayflagId());
				}
				//转账金额
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
				//预授权合同号
				if(commonRequst.getContractNo()==null){
					fuiou.setContract_no("");
				}else{
					fuiou.setContract_no(commonRequst.getContractNo());
				}
				
				//备注
				fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("转账(商户与个人之间)请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUTRAACC, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("转账(商户与个人之间)结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("转账(商户与个人之间)成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("转账(商户与个人之间)失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("转账(商户与个人之间)对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 接口07--划拨 (个人与个人之间)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse allot(CommonRequst commonRequst){
		logger.info("调用富有划拨 (个人与个人之间)接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//付款登录账户
				if(null==commonRequst.getThirdPayConfigId()){
					fuiou.setOut_cust_no(fuiouplatloginName());
				}else{
					fuiou.setOut_cust_no(commonRequst.getThirdPayConfigId());
				}
				//收款登录账户
				if(null==commonRequst.getLoaner_thirdPayflagId()){
					fuiou.setIn_cust_no(fuiouplatloginName());
				}else{
					fuiou.setIn_cust_no(commonRequst.getLoaner_thirdPayflagId());
				}
				//划拨金额
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
				//预授权合同号
				fuiou.setContract_no(commonRequst.getContractNo());
				//备注
				fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("划拨 (个人与个人之间)请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUTRANSFERPERSONTOPERSON, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("划拨 (个人与个人之间)结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("划拨 (个人与个人之间)成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("划拨 (个人与个人之间)失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("划拨 (个人与个人之间)对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 接口08--用户信息查询接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse queryUserInfo(CommonRequst commonRequst){
		logger.info("调用用户信息查询接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			DateFormat df=new SimpleDateFormat("yyyyMMdd");
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//交易日期
				fuiou.setMchnt_txn_dt(df.format(commonRequst.getTransactionTime()));
				//待查询的登录帐户列表
				fuiou.setUser_ids(commonRequst.getThirdPayConfigId());
				//带查询的登录身份证列表
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("用户信息查询请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUMEMBERMESSAGE, param, Fuiou.CHARSETUTF8,120000); 
 					logger.info("用户信息查询结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					
					if(response.getPlain().getResp_code().equals("0000")){
						commonResponse.setBankName(response.getPlain().getResults().get(0).getBank_nm());//银行名称
						commonResponse.setBankCode(response.getPlain().getResults().get(0).getCapAcntNo());//银行卡号
						commonResponse.setCustmemberStatus(response.getPlain().getResults().get(0).getContract_st()!=null?(response.getPlain().getResults().get(0).getContract_st().equals("0")?"未通过":(response.getPlain().getResults().get(0).getContract_st().equals("1")?"已通过":(response.getPlain().getResults().get(0).getContract_st().equals("2")?"待验证":""))):"");//用户帐户状态
						commonResponse.setBindBankStatus("已认证");
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("用户信息查询成功");
						commonResponse.setCommonRequst(commonRequst);	
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("用户信息查询失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户信息查询对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 接口09--用户信息修改
	 * @param commonRequst
	 * @return
	 */
	public static Object[] changeUserInfo(CommonRequst commonRequst){
		logger.info("调用用户信息修改接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//客户姓名
				fuiou.setCust_nm(commonRequst.getTrueName());
				//身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber());
				//手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//银行卡开户城市
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户行行别
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//帐号（要加密）
				fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("用户信息修改请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUUPDATEMEMBERMESSAGE, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("用户信息修改结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="用户信息修改成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="用户信息修改失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="用户信息修改对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 网关接口10--个人用户注册
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse register(CommonRequst commonRequst){
		logger.info("调用富有支付网关个人用户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse response = new CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户Id
				fuiou.setUser_id_from(commonRequst.getCustMemberId().toString());
				//用户手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//用户真实姓名
				fuiou.setCust_nm(commonRequst.getTrueName());
				//用户身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber());
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//银行卡开户城市
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户银行编码
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//银行卡号（要加密）
				if(commonRequst.getBankCardNumber()!=null&&!"".equals(commonRequst.getBankCardNumber())){
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));//提现账户开户名
				}else{
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(""));//提现账户开户名
				}
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUGOLDWEBRECHARGE,"个人用户注册");
				/*String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				params.put("signature", sign);
				String outStr="";
				String param=UrlUtils.generateParams(params,Fuiou.CHARSETUTF8);
				  System.out.println("param="+param);
				  outStr =WebClient.getWebContentByPost(configMap.get("thirdPay_fuiou_URL").toString(), param, Fuiou.CHARSETUTF8,12000);//+Fuiou.FUIOUGOLDREG 
				  String[] splitStr = outStr.split("plain>");
				  System.out.println(splitStr[1]);
				  Document doc=XmlUtil.stringToDocument(outStr);
					String  resp_code = doc.selectSingleNode("/ap/plain/resp_code").getText();
					String  mchnt_cd=doc.selectSingleNode("/ap/plain/mchnt_cd").getText();
					String  mchnt_txn_ssn=doc.selectSingleNode("/ap/plain/mchnt_txn_ssn").getText();
					String  signNature=doc.selectSingleNode("/ap/signature").getText();
					System.out.println("signNature:"+signNature);
					String data = "<plain>"+splitStr[1]+"plain>";
					System.out.println(data);
					Boolean issign=SecurityUtils.verifySign(data, signNature);*/
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("基本参数获取失败");
				response.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			response.setResponseMsg("个人用户注册对接失败"+e.getMessage());
			response.setCommonRequst(commonRequst);
		}
		return response;
	}
	
	/**
	 * 接口12--冻结
	 * @param commonRequst
	 * @return
	 */
	public static Object[] freeze(CommonRequst commonRequst){
		logger.info("调用富有支付冻结接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
			    //冻结金额
			    fuiou.setAmt(commonRequst.getAmount().toString());
			    //备注
			    //fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
			    //冻结目标登录账户
			    fuiou.setCust_no(commonRequst.getThirdPayConfigId());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("冻结请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUFREEZE, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("冻结结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="冻结成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="冻结失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="冻结对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	/**
	 * 接口13--转账欲冻结
	 * @param commonRequst
	 * @return
	 */
/*	public static Object[] preTransferFreeze(CommonRequst commonRequst){
		logger.info("调用富有支付转账欲冻结接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户Id
				fuiou.setUser_id_from(commonRequst.getCustMemberId().toString());
				//用户手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//用户真实姓名
				fuiou.setCust_nm(commonRequst.getTrueName());
				//用户身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber());
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//银行卡开户城市
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户银行编码(开户行行别)
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//银行卡号（要加密）
				fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				//System.out.println(params);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				params.put("signature", sign);
				//富有金账户支付调用地址
				String fuiouUrl=fuiouPayUrl();
				String[] rett=ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUGOLDAPPREG, params,Fuiou.CHARSETUTF8);
				logger.info("转账欲冻结调用富有金账户参数："+rett[1]);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){//
					ret[0]=ThirdPayConstants.RECOD_SUCCESS;
					ret[1]="转账欲冻结申请提交";
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
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="转账欲冻结对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}*/
	
	/**
	 * 接口15--解冻接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] unfreeze(CommonRequst commonRequst){
		logger.info("调用富有支付解冻接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//解冻目标登录账户
				fuiou.setCust_no(commonRequst.getThirdPayConfigId());
				//解冻金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				//备注
				//fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("解冻请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOURELEASEFREEZE, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("解冻结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="解冻成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="解冻失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="解冻对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 接口18--手机端APP个人用户自助开户注册（APP网页版）
	 * @param commonRequst
	 * @return
	 */
	public static Object[] mobileRegister(CommonRequst commonRequst){
		logger.info("调用富有支付手机端APP个人用户自助开户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户Id
				fuiou.setUser_id_from(commonRequst.getCustMemberId().toString());
				//用户手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//用户真实姓名
				fuiou.setCust_nm(commonRequst.getTrueName());
				//用户身份证号码
				fuiou.setCertif_id(commonRequst.getCardNumber());
				//邮箱
				fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
				//银行卡开户城市
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户银行编码(开户行行别)
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//银行卡号（要加密）
				fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("手机端APP个人用户自助开户注册请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUGOLDAPPREG, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("手机端APP个人用户自助开户注册结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="手机端APP个人用户自助开户注册成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="手机端APP个人用户自助开户注册失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="手机端APP个人用户自助开户注册对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 接口20--交易查询接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] queryTransaction(CommonRequst commonRequst){
		logger.info("调用富有交易查询接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			DateFormat df=new SimpleDateFormat("yyyyMMdd");
			Fuiou fuiou=generatePublicData(false,false);
			if(null!=fuiou){
				//流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//交易类型
				fuiou.setBusi_tp(commonRequst.getQueryType());
				//起始时间
				fuiou.setStart_day(df.format(commonRequst.getStartDay()));
				//截止时间
				fuiou.setEnd_day(df.format(commonRequst.getEndDay()));
				//交易流水
	         	fuiou.setTxn_ssn(commonRequst.getQueryRequsetNo()!=null?commonRequst.getQueryRequsetNo():"");
		       //交易用户
		        fuiou.setCust_no(commonRequst.getThirdPayConfigId());
		       //交易状态
		        fuiou.setTxn_st(commonRequst.getBusinessStatus()!=null?commonRequst.getBusinessStatus():"");
		        //交易备注
		        fuiou.setRemark(commonRequst.getRemark()!=null? commonRequst.getRemark():"");
		        //页码
		        fuiou.setPage_no(commonRequst.getPageNo()!=null?commonRequst.getPageNo():new Integer("1").toString());
		        //每页条数
		        fuiou.setPage_size(commonRequst.getPageSize()!=null?commonRequst.getPageSize():new Integer("10").toString());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("交易查询请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUTRANSFERQUERY, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("交易查询结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="用户信息查询成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="注册用户信息查询失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="交易查询对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	/**
	 * 接口21--冻结到冻结接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] freezeToFreeze(CommonRequst commonRequst){
		logger.info("调用富有冻结到冻结接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//付款登录账户
				if(null==commonRequst.getThirdPayConfigId()){
					fuiou.setOut_cust_no(fuiouplatloginName());
				}else{
					fuiou.setOut_cust_no(commonRequst.getThirdPayConfigId());
				}
				//收款登录账户
				if(null==commonRequst.getLoaner_thirdPayflagId()){
					fuiou.setIn_cust_no(fuiouplatloginName());
				}else{
					fuiou.setIn_cust_no(commonRequst.getLoaner_thirdPayflagId());
				}
				//转账金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				//备注
			//	fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("冻结到冻结请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUFREEZETOFREEZE, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("冻结到冻结结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="冻结到冻结成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="冻结到冻结接口失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="冻结到冻结对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 接口22--充值提现查询接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse rechargeAndWithdraw(CommonRequst commonRequst){
		logger.info("调用富有充值提现查询接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//交易类型
				fuiou.setBusi_tp(commonRequst.getQueryType());
				//起始时间
				if(commonRequst.getStartDay()!=null){
					fuiou.setStart_time(df.format(commonRequst.getStartDay()));
				}
				//截止时间
				if(commonRequst.getEndDay()!=null){
				fuiou.setEnd_time(df.format(commonRequst.getEndDay()));
				}
				//用户
				fuiou.setCust_no(commonRequst.getThirdPayConfigId()!=null?commonRequst.getThirdPayConfigId():"");
				//交易状态
				fuiou.setTxn_st(commonRequst.getBusinessStatus()!=null?commonRequst.getBussinessType():"");
				//页码
				fuiou.setPage_no(commonRequst.getPageNo()!=null?commonRequst.getPageNo():"");
				//每页条数
				fuiou.setPage_size(commonRequst.getPageSize()!=null?commonRequst.getPageSize():"");
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("充值提现查询请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOURECHWITHDQUERY, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("充值提现查询结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					List<CommonRecord> list = new ArrayList();
					if(response.getPlain().getResults().size()>0){
						for(Result result:response.getPlain().getResults()){
							if(result.getTxn_rsp_cd()!=null&&("0000").equals(result.getTxn_rsp_cd())){
								if(commonRequst.getQueryRequsetNo().equals(result.getMchnt_ssn())){
									CommonRecord record = new CommonRecord();
									record.setStatus("SUCCESS");//状态设置为成功
									list.add(record);
								}
							}
						}
						commonResponse.setRecordList(list);
					}
					if(response.getPlain().getResp_code().equals("0000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("充值提现查询成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("充值提现查询失败");
						commonResponse.setCommonRequst(commonRequst);
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("生成签名失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
				commonResponse.setCommonRequst(commonRequst);
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("充值提现查询对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;
	}
	
	/**
	 * 充值接口
	 */
	public static CommonResponse recharge(CommonRequst commonRequst){
		logger.info("调用富有网银充值接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		Map thirdPayConfig=fuiouProperty();
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				fuiou.setMchnt_cd(thirdPayConfig.get("thirdPay_fuiou_platNumber").toString().trim());
				fuiou.setLogin_id(commonRequst.getTelephone());
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("网银充值请求参数："+param);
					String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOURECHARGE, params, Fuiou.CHARSETUTF8);
					//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOURECHARGE, param, Fuiou.CHARSETUTF8,120000);
					if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("充值申请成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("充值申请失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="网银充值对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return commonResponse;
	}
	
	
	/**
	 * 接口24--法人开户注册
	 * @param commonRequst
	 * @return
	 */
	public static Object[] legalRegister(CommonRequst commonRequst){
		logger.info("调用富有法人开户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(false,false);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//企业名称
				fuiou.setCust_nm(commonRequst.getEnterpriseName());
				//法人姓名
				fuiou.setArtif_nm(commonRequst.getLegal());
				//身份证号码
				fuiou.setCertif_id(commonRequst.getLegalIdNo());
				//手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//邮箱地址
				fuiou.setEmail(commonRequst.getEmail());
				//开户行地区代码
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户行行别
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//帐号（要加密）
				fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				//提现密码
				//fuiou.setPassword("");
				//登录密码
				//fuiou.setLpassword("");
				//备注
				//fuiou.setRem(commonRequst.getRemark()!=null?commonRequst.getRemark():"");
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("法人开户注册请求参数："+param);
					String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUGOLDENREG, param, Fuiou.CHARSETUTF8,120000); 
					logger.info("法人开户注册结果："+outStr);
					FuiouReponse response =FuiouPaySecretKeyUtil.JAXBunmarshal(outStr,FuiouReponse.class);
					if(response.getPlain().getResp_code().equals("0000")){
						 ret[0]=ThirdPayConstants.RECOD_SUCCESS;
						 ret[1]="法人开户注册成功";
						 ret[2]=commonResponse;
					}else{
						ret[0]=ThirdPayConstants.RECOD_FAILD;
					    ret[1]="法人开户注册接口失败";
						ret[2]=commonResponse;
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="法人开户注册对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return ret;
	}
	
	/**
	 * 网关接口25--法人用户自助开户注册
	 * @param commonRequst
	 * @return
	 */
	public static Object[] legalAutoRegister(CommonRequst commonRequst){
		logger.info("调用富有法人用户自助开户注册接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户在商户系统的标志
				fuiou.setUser_id_from(commonRequst.getCustMemberId().toString());
				//企业名称
				fuiou.setCust_nm(commonRequst.getEnterpriseName());
				//法人姓名
				fuiou.setArtif_nm(commonRequst.getLegal());
				//身份证号码
				fuiou.setCertif_id(commonRequst.getLegalIdNo());
				//手机号码
				fuiou.setMobile_no(commonRequst.getTelephone());
				//邮箱地址
				fuiou.setEmail(commonRequst.getEmail());
				//开户行地区代码
				fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
				//开户行行别
				fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
				//开户行支行名称
				fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
				//帐号（要加密）
				fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUGOLDWEBENREG,"法人用户自助开户注册");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="法人用户自助开户注册对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	
	/**
	 * 网关接口26--商户APP个人用户免登录快速充值
	 * @param commonRequst
	 * @return
	 */
	public static Object[] appFreeLogin(CommonRequst commonRequst){
		logger.info("调用富有商户APP个人用户免登录快速充值接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//充值金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUAPPFASTRERECHARGE,"商户APP个人用户免登录快速充值");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="商户APP个人用户免登录快速充值对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	
	/**
	 * 网关接口27--商户APP个人用户免登录快速充值
	 * @param commonRequst
	 * @return
	 */
	public static Object[] appFreeLogin2(CommonRequst commonRequst){
		logger.info("调用富有商户APP个人用户免登录快速充值接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//充值金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUAPPBANKRECHARGE,"商户APP个人用户免登录快速充值");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="商户APP个人用户免登录快速充值对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	/**
	 * 网关接口28--商户APP个人用户免登录提现
	 * @param commonRequst
	 * @return
	 */
	public static Object[] appFreeWithdrawDeposit(CommonRequst commonRequst){
		logger.info("调用富有商户APP个人用户免登录提现接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//提现金额
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUAPPWITHDRAW,"商户APP个人用户免登录提现");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="商户APP个人用户免登录提现对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	/**
	 * 网关接口29--商户P2P网站免登录快速充值接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] p2pFreeLogin(CommonRequst commonRequst){
		logger.info("调用富有商户P2P网站免登录快速充值接口接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//充值金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUPCFASTRERECHARGE,"商户P2P网站免登录快速充值接口");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="商户P2P网站免登录快速充值接口对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	/**
	 * 网关接口30--商户P2P网站免登录网银充值接口
	 * @param commonRequst
	 * @return
	 */
	public static Object[] p2pFreeLogin2(CommonRequst commonRequst){
		logger.info("调用富有商户P2P网站免登录网银充值接口接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//充值金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUPCBANKRECHARGE,"商户P2P网站免登录网银充值接口");
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonRequst;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="商户P2P网站免登录网银充值接口对接失败"+e.getMessage();
			ret[2]=commonRequst;
		}
		return ret;
	}
	/**
	 * 接口31--商户P2P网站免登录提现接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse p2pFreeWithdrawDeposit(CommonRequst commonRequst){
/*		logger.info("调用富有商户P2P网站免登录提现接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		CommonResponse commonResponse = new CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//提现金额
				fuiou.setAmt(commonRequst.getAmount().toString());
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				commonSign(ret,commonRequst,params,Fuiou.FUIOUPCWITHDRAW,"商户P2P网站免登录提现接口");
			}else{
				if(ret[0].equals(ThirdPayConstants.RECOD_FAILD)){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("基本参数获取失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("商户P2P网站免登录提现接口对接失败"+e.getMessage());
			commonResponse.setCommonRequst(commonRequst);
		}
		return commonResponse;*/
	//}
		logger.info("调用富有网银充值接口开始时间："+new Date());
		Object[]  ret=new Object[3];
		Map thirdPayConfig=fuiouProperty();
		CommonResponse commonResponse=new  CommonResponse();
		try{
			Fuiou fuiou=generatePublicData(true,true);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(fuiou!=null){
				//注册流水号
				fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
				//用户登录名
				fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
				//提现金额
				fuiou.setAmt(commonRequst.getAmount().multiply(new BigDecimal(100)).setScale(0).toString());				
				Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
				String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
				if(null!=sign){
					params.put("signature", sign);
					//富有金账户支付调用地址
					String fuiouUrl=fuiouPayUrl();
					String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
					logger.info("网银充值请求参数："+param);
					String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUPCWITHDRAW, params, Fuiou.CHARSETUTF8);
					//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUPCWITHDRAW, param, Fuiou.CHARSETUTF8,120000);
					if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("充值申请成功");
						commonResponse.setCommonRequst(commonRequst);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("充值申请失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					ret[0]=ThirdPayConstants.RECOD_FAILD;
					ret[1]="生成签名失败";
					ret[2]=commonResponse;
				}
			}else{
				ret[0]=ThirdPayConstants.RECOD_FAILD;
				ret[1]="基本参数获取失败";
				ret[2]=commonResponse;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=ThirdPayConstants.RECOD_FAILD;
			ret[1]="网银充值对接失败"+e.getMessage();
			ret[2]=commonResponse;
		}
		return commonResponse;
		}
	
	
	
	/**
	 *  接口32 用户修改密码 
	 */
		public static CommonResponse changeUserPass(CommonRequst commonRequst){
			logger.info("调用用户信息修改接口开始时间："+new Date());
			Object[]  ret=new Object[3];
			CommonResponse commonResponse=new  CommonResponse();
			try{
				Fuiou fuiou=generatePublicData(false,false);
				if(null!=fuiou){
					//流水号
					fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
					//客户姓名
					fuiou.setCust_nm(commonRequst.getTrueName());
					//身份证号码
					fuiou.setCertif_id(commonRequst.getCardNumber());
					//手机号码
					fuiou.setMobile_no(commonRequst.getTelephone());
					//邮箱
					fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
					//银行卡开户城市
					fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
					//开户行行别
					fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
					//开户行支行名称
					fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
					//帐号（要加密）
					fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
					
					Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
					String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
					if(null!=sign){
						params.put("signature", sign);
						//富有金账户支付调用地址
						String fuiouUrl=fuiouPayUrl();
						String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
						logger.info("用户信息修改请求参数："+param);
						String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPASS, params, Fuiou.CHARSETUTF8);
						//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPASS, param, Fuiou.CHARSETUTF8,120000); 
						//logger.info("用户信息修改结果："+outStr);
						if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
							commonResponse.setResponseMsg("修改密码申请成功");
							commonResponse.setCommonRequst(commonRequst);
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("修改密码申请失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("生成签名失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("基本参数获取失败");
					commonResponse.setCommonRequst(commonRequst);
				}
			}catch(Exception e){
				e.printStackTrace();
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("用户信息修改对接失败"+e.getMessage());
				commonResponse.setCommonRequst(commonRequst);
			}
			return commonResponse;
		}

		/**
		 *  接口32 用户修改密码app版 
		 */
			public static CommonResponse changeUserPassApp(CommonRequst commonRequst){
				logger.info("调用用户信息修改接口开始时间："+new Date());
				Object[]  ret=new Object[3];
				CommonResponse commonResponse=new  CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(false,false);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户姓名
						fuiou.setCust_nm(commonRequst.getTrueName());
						//身份证号码
						fuiou.setCertif_id(commonRequst.getCardNumber());
						//手机号码
						fuiou.setMobile_no(commonRequst.getTelephone());
						//邮箱
						fuiou.setEmail(commonRequst.getEmail()!=null?commonRequst.getEmail():"");
						//银行卡开户城市
						fuiou.setCity_id(commonRequst.getCity()!=null?commonRequst.getCity():"");//北京市
						//开户行行别
						fuiou.setParent_bank_id(commonRequst.getBankCode()!=null?commonRequst.getBankCode():"");//工商银行
						//开户行支行名称
						fuiou.setBank_nm(commonRequst.getBankBranchName()!=null?commonRequst.getBankBranchName():"");
						//帐号（要加密）
						fuiou.setCapAcntNo(StringUtil.stringURLEncoderByUTF8(commonRequst.getBankCardNumber()));
						
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPASS, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPASS, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("修改密码申请成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("修改密码申请失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户信息修改对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				return commonResponse;
			}

			/**
			 * 接口33 用户修改绑定手机号码
			 */
			public static CommonResponse changePhone(CommonRequst commonRequst){
				CommonResponse commonResponse = new CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(true,false);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户姓名
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("修改密码申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("修改密码申请提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户修改手机号码接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				return commonResponse;
			}
			
			public static CommonResponse rechargeQuick(CommonRequst commonRequst){
				CommonResponse commonResponse = new CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(true,false);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户姓名
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						//充值金额
						fuiou.setAmt(commonRequst.getAmount().setScale(0).multiply(new BigDecimal(100)).toString());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("修改密码申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("修改密码申请提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户修改手机号码接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				try{
					Fuiou fuiou=generatePublicData(true,false);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户姓名
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						//充值金额
						fuiou.setAmt(commonRequst.getAmount().setScale(0).multiply(new BigDecimal(100)).toString());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("修改密码申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("修改密码申请提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户修改手机号码接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				return commonResponse;
			}

			public static CommonResponse cancelUser(CommonRequst commonRequst){
				CommonResponse commonResponse = new CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(false,false);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户第三方账号
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("用户注销申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("用户注销申请提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户注销接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}

				return commonResponse;
			}

			public static CommonResponse appQRecharge(CommonRequst commonRequst){
				CommonResponse commonResponse = new CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(true,true);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户第三方账号
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						//充值金额
						fuiou.setAmt(commonRequst.getAmount().setScale(0).multiply(new BigDecimal(100)).toString().trim());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUAPPQRECHARGE, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("用户注销申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("用户注销申请提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户注销接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				return commonResponse;
			}

			/**
			 * 用户app个人免登陆取现
			 * @param commonRequst
			 * @return
			 */
			public static CommonResponse appWithdraw(CommonRequst commonRequst){
				CommonResponse commonResponse = new CommonResponse();
				try{
					Fuiou fuiou=generatePublicData(true,true);
					if(null!=fuiou){
						//流水号
						fuiou.setMchnt_txn_ssn(commonRequst.getRequsetNo());
						//客户第三方账号
						fuiou.setLogin_id(commonRequst.getThirdPayConfigId());
						//提现
						fuiou.setAmt(commonRequst.getAmount().setScale(0).multiply(new BigDecimal(100)).toString().trim());
						Map<String, String> params=FuiouPaySecretKeyUtil.createMap(fuiou.getClass(),fuiou);
						String sign=autoCreatSignnature(params,Fuiou.CHARSETUTF8);
						if(null!=sign){
							params.put("signature", sign);
							//富有金账户支付调用地址
							String fuiouUrl=fuiouPayUrl();
							String param=ThirdPayWebClient.generateParams(params,Fuiou.CHARSETUTF8);
							logger.info("用户信息修改请求参数："+param);
							String[] result = ThirdPayWebClient.operateParameter(fuiouUrl+Fuiou.FUIOUAPPWITHDRAW, params, Fuiou.CHARSETUTF8);
							//String outStr=ThirdPayWebClient.getWebContentByPost(fuiouUrl+Fuiou.FUIOUCHANGEPHONE, param, Fuiou.CHARSETUTF8,120000); 
							//logger.info("用户信息修改结果："+outStr);
							if(result!=null&&result[0].equals(ThirdPayConstants.RECOD_SUCCESS)){
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
								commonResponse.setResponseMsg("app提现申请提交成功");
								commonResponse.setCommonRequst(commonRequst);
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
								commonResponse.setResponseMsg("app提现提交失败");
								commonResponse.setCommonRequst(commonRequst);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("生成签名失败");
							commonResponse.setCommonRequst(commonRequst);
						}
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("基本参数获取失败");
						commonResponse.setCommonRequst(commonRequst);
					}
				}catch(Exception e){
					e.printStackTrace();
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("app个人免登陆提现接口对接失败"+e.getMessage());
					commonResponse.setCommonRequst(commonRequst);
				}
				return commonResponse;
			}


}
	
	
