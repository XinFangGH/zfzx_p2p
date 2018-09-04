package com.thirdPayInterface.Huifu;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
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

import chinapnr.SecureLink;

import com.hurong.core.util.AppUtil;
import com.thirdPayInterface.CommonRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayWebClient;
import com.thirdPayInterface.Huifu.HuifuUtil.MD5;
import com.thirdPayInterface.Huifu.HuifuUtil.ObjectFormatUtil;
import com.thirdPayInterface.MoneyMorePay.MoneyMore;

public class HuiFuInterfaceUtil{
	private static Log logger=LogFactory.getLog(HuiFuInterfaceUtil.class);
    /**
     * 获取系统配置参数
     */
	private static Map configMap = AppUtil.getConfigMap();
	/**
	 * 第三方支付环境（正式环境和测试环境）
	 */
	private static String  thirdPayEnvironmentType=configMap.containsKey("thirdPayEnvironmentType")?configMap.get("thirdPayEnvironmentType").toString().trim():ThirdPayConstants.THIRDENVIRONMENT1;
	
	/**读取属性文件
	 * 获取配置文件信息（正式环境和测试环境的配置文件）
	 * @return
	 */
	public static Map HuifuProperty() {
		Map huifuConfigMap=new HashMap();
		try{
			Properties props =  new  Properties(); 
			InputStream in=null; 
			if(thirdPayEnvironmentType.equals(ThirdPayConstants.THIRDENVIRONMENT0)){//正式环境
				in = HuiFuInterfaceUtil.class.getResourceAsStream("HuifuNormalEnvironment.properties"); 
			}else{
				in = HuiFuInterfaceUtil.class.getResourceAsStream("HuifuTestEnvironment.properties"); 
			}
			if(in!=null){
				props.load(in);
		    	Iterator it= props.keySet().iterator();
		    	while(it.hasNext()){
		    		String key=(String)it.next();
		    		huifuConfigMap.put(key, props.getProperty(key));
		    	}
			}
		}catch(Exception ex){
			ex.printStackTrace();
    		logger.error(ex.getMessage());
    	}
		return huifuConfigMap;
	}
	
	/**获得地址
	 * 提交的URL地址
	 * @return
	 */
	private static String HuifuPayUrl() {
		Map thirdPayConfig=HuifuProperty();
		//汇付天下支付调用地址
		String huifuUrl=thirdPayConfig.get("thirdPay_Huifu_URL").toString();
		return huifuUrl;
	}
	
	/**
	 * 生成公共 数据
	 * @param FontHuiFuAction
	 * @return
	 */
	private static Huifu generatePublicData(Huifu huiFu,String regType,boolean pageCallBack, boolean notifyCallBack) {
		Map thirdPayConfig=HuifuProperty();
		if(thirdPayConfig!=null&&!thirdPayConfig.isEmpty()){//判断map中是否为空和有数值
			// 版本号
			String huiFuVer = thirdPayConfig.get("thirdPay_Huifu_Ver").toString();
			// 商户账号
			String huiFuNumber = thirdPayConfig.get("thirdPay_Huifu_platNumber").toString();
			huiFu.setVersion(huiFuVer);
			huiFu.setCharSet(Huifu.CHARSETUTF8);
			huiFu.setCmdId(regType);
			huiFu.setMerPriv("");
			huiFu.setMerCustId(huiFuNumber);
			String BasePath=ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath() + "/";
			if(notifyCallBack){
				huiFu.setBgRetUrl(BasePath+thirdPayConfig.get("thirdPay_Huifu_pageCallUrl").toString().trim());//thirdPay_Huifu_notifyUrl
			}
			if(pageCallBack){
				huiFu.setRetUrl(BasePath+thirdPayConfig.get("thirdPay_Huifu_pageCallUrl").toString().trim());
			}
			return huiFu;
		}else{
			return null;
		}
	}
	/**
	 * 返回签名验证
	 * @param msgData
	 * @param chkValue
	 * @return
	 */
	public static boolean verifyChkValue(String merData,String chkValue){
		Map thirdPayConfig=HuifuProperty();
		String keyFile=AppUtil.getAppAbsolutePath()+thirdPayConfig.get("thirdPay_Huifu_publicKeyPath").toString().replace("\\", "/");;
		SecureLink sl=new SecureLink();
		boolean isSuccess=false;
		int ret=sl.VeriSignMsg(keyFile,merData,chkValue);
		if (ret != 0){
	       	System.out.println("ret=" + ret );
	       	isSuccess=false;
		}else{
       		isSuccess=true;
		}
		return isSuccess;
	}
	
	/**
	 * 数据加密签名验证
	 * @param huiFuVO
	 * @param basePath
	 * @return
	 */
	private static String Sign(String MerData){
		Map thirdPayConfig=HuifuProperty();
		String MerKeyFile=AppUtil.getAppAbsolutePath()+thirdPayConfig.get("thirdPay_Huifu_privateKeyPath").toString().replace("\\", "/");;
		SecureLink sl=new SecureLink();
        int ret=sl.SignMsg(thirdPayConfig.get("thirdPay_Huifu_MerId").toString(),MerKeyFile,MerData);
        String  ChkValue="";
        if (ret != 0) {
        	System.out.println("ret=" + ret );
        	ChkValue= "";
        }
        ChkValue= sl.getChkValue();
        return ChkValue;
	}
	/**
	 * 用户登录汇付平台
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse userLogin(CommonRequst commonRequst){
		Huifu huifu = new Huifu();
		huifu = generatePublicData(huifu,"UserLogin",true,true);
		if(null != huifu){
			huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
		}
		// 生成注册map
		Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
		System.out.println("参数转map===="+params);
		String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
		return null;
	}
    /**
     *  个人注册
     * @param commonRequst
     * @return
     */
	public static CommonResponse register(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"UserRegister",true,true);
			if(null != huifu){
				huifu.setUsrId(commonRequst.getLoginname());
				//huifu.setUsrName(commonRequst.getTrueName());
				huifu.setIdType("00");//00 必须为身份证  编码 为 00
				huifu.setIdNo(commonRequst.getCardNumber());
				huifu.setUsrMp(commonRequst.getTelephone());
				huifu.setMerPriv(commonRequst.getRequsetNo());//保存请求的流水号
				huifu.setUsrEmail(commonRequst.getEmail()==null ? "" : commonRequst.getEmail());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getRetUrl().trim()
									+ huifu.getUsrId().trim() 
									//+ huifu.getUsrName().trim()
									+ huifu.getIdType().trim() 
									+ huifu.getIdNo().trim() 
									+ huifu.getUsrMp().trim()
									+ huifu.getUsrEmail().trim()
									+ huifu.getMerPriv().trim();
				System.out.println("汇付注册拼接参数===="+MerData);
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
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
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("注册对接失败");
		}
		return commonResponse;
	}
	 /**
     *  企业注册
     * @param commonRequst
     * @return
     */
	public static CommonResponse registerenterprise(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"CorpRegister",false,true);
			if(null != huifu){
				huifu.setVersion("10");
				huifu.setUsrId(commonRequst.getLoginname());
				huifu.setUserName("");
				if(commonRequst.getThreeCardCode()!=null&&!commonRequst.getThreeCardCode().equals("")){//三证合一
					huifu.setInstuCode("0");
					huifu.setBusiCode(commonRequst.getThreeCardCode());
					huifu.setTaxCode("0");
				}else{
					huifu.setInstuCode(commonRequst.getOrgNo());
					huifu.setBusiCode(commonRequst.getBusinessLicense());
					huifu.setTaxCode(commonRequst.getTaxNo());
				}
				huifu.setGuarType(commonRequst.getGuarType());
				huifu.setReqExt("");
				huifu.setMerPriv(commonRequst.getRequsetNo());//保存请求的流水号
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrId().trim() 
									+ huifu.getUserName().trim()
									+ huifu.getInstuCode().trim() 
									+ huifu.getBusiCode().trim() 
									+ huifu.getTaxCode().trim()
									+ huifu.getMerPriv().trim()
									+ huifu.getGuarType().trim()
									+ huifu.getBgRetUrl().trim()
									+ huifu.getReqExt();
				MerData= MerData.replace("null", "");
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
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
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("注册对接失败");
		}
		return commonResponse;
	}
	 /**
     *  充值
     * @param commonRequst
     * @return
     */
	public static CommonResponse rechargeMoney(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"NetSave",true,true);
			if(null != huifu){
				huifu.setVersion("10");
				huifu.setDcFlag("D");//汇付天下默认D--借记类型
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setOrdId(commonRequst.getRequsetNo());
				huifu.setOrdDate(sdf.format(new Date()));
				huifu.setTransAmt(commonRequst.getAmount().toString());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				huifu.setDcFlag("");
				huifu.setCertId("");
				huifu.setGateBusiId("");
				huifu.setPageType("");
				huifu.setOpenBankId("");
				huifu.setOpenAcctId("");
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrCustId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getOrdDate().trim() 
									+ huifu.getGateBusiId().trim()
									+ huifu.getOpenBankId().trim()
									+ huifu.getDcFlag().trim()
									+ huifu.getTransAmt().trim() 
									+ huifu.getRetUrl().trim() 
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getOpenAcctId().trim()
									+ huifu.getCertId().trim()
									+ huifu.getMerPriv().trim()
									+ huifu.getPageType();
				String  MerData1 = huifu.getVersion().trim()+"|"
				+ huifu.getCmdId().trim() +"|"
				+ huifu.getMerCustId().trim() +"|"
				+ huifu.getUsrCustId().trim() +"|"
//				+ huifu.getSubAcctType().trim() 
//				+ huifu.getSubAcctId().trim() 
				+ huifu.getOrdId().trim() +"|"
				+ huifu.getOrdDate().trim() +"|"
				+ huifu.getTransAmt().trim() +"|"
				+ huifu.getRetUrl().trim() +"|"
				+ huifu.getBgRetUrl().trim() +"|"
				+ huifu.getMerPriv().trim();
				System.out.println("汇付注册拼接参数===="+MerData);
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("充值申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("充值申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("充值对接失败");
		}
		return commonResponse;
	}
	 /**
     *  取现
     * @param commonRequst
     * @return
     */
	public static CommonResponse withdrawsMoney(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"Cash",true,true);
			if(null != huifu){
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setOrdId(commonRequst.getRequsetNo());
				huifu.setTransAmt(commonRequst.getAmount().toString());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				huifu.setOpenAcctId(commonRequst.getBankCardNumber());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getUsrCustId().trim() 
									+ huifu.getTransAmt().trim() 
									+ huifu.getOpenAcctId().trim()
									+ huifu.getRetUrl().trim() 
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getMerPriv().trim();
				huifu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("取现申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("取现申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("取现对接失败");
		}
		return commonResponse;
	}
	/**
     *  取现复审
     * @param commonRequst
     * @return
     */
	public static CommonResponse withdrawsCheckMoney(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"CashAudit",true,true);
			if(null != huifu){
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setOrdId(commonRequst.getQueryRequsetNo());
				huifu.setTransAmt(commonRequst.getAmount().toString());
				huifu.setAuditFlag(commonRequst.getAuditType().toString());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getUsrCustId().trim() 
									+ huifu.getTransAmt().trim() 
									+ huifu.getAuditFlag().trim()
									+ huifu.getRetUrl().trim() 
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getMerPriv().trim();
				huifu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("取现复审申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("取现复审申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("取现复审对接失败");
		}
		return commonResponse;
	}
	 /**
     *  绑定银行卡
     * @param commonRequst
     * @return
     */
	public static CommonResponse bindCard(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"UserBindCard",true,true);
			if(null != huifu){
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrCustId().trim() 
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getMerPriv().trim();
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("绑定银行卡申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("绑定银行卡申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("绑定银行卡对接失败");
		}
		return commonResponse;
	}
	/**
	 * 汇付的理财计划购买
	 */
	public static CommonResponse bidingPlmanage(CommonRequst commonRequst){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"InitiativeTender",true,true);
			if(null != huifu){
				huifu.setOrdDate(sdf.format(new Date()));
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId().trim());//
				huifu.setOrdId(commonRequst.getRequsetNo().trim());//订单号
				huifu.setMaxTenderRate(commonRequst.getMaxTenderRate().trim());//
				huifu.setTransAmt(commonRequst.getAmount().setScale(2).toString().trim());//
				huifu.setBorrowerDetails(commonRequst.getBorrowerDetails().trim());
				huifu.setMerPriv(commonRequst.getRequsetNo().trim());//保存请求的流水号
				huifu.setIsFreeze(commonRequst.getIsFreeze().trim());
				huifu.setFreezeOrdId(commonRequst.getFreezeOrdId().trim());
				huifu.setReqExt(commonRequst.getReqExt());
				huifu.setPageType(commonRequst.getPageType());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim()
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getUsrCustId().trim()
									+ huifu.getMaxTenderRate().trim()
									+ huifu.getBorrowerDetails().trim()
									+ huifu.getIsFreeze().trim()
									+ huifu.getFreezeOrdId().trim()
									+ huifu.getRetUrl().trim()
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getMerPriv().trim()
									+ huifu.getReqExt()
									+ huifu.getPageType();
				System.out.println("汇付注册拼接参数===="+MerData);
				huifu.setChkValue(Sign(MerData));
    			System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("投标申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("投标申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("投标接口对接失败");
		}
		return commonResponse;
	}	
	/**
	 * 汇付的投标方法
	 */
	public static CommonResponse biding(CommonRequst commonRequst){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"InitiativeTender",true,true);
			if(null != huifu){
				huifu.setOrdDate(sdf.format(new Date()));
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId().trim());//
				huifu.setOrdId(commonRequst.getRequsetNo().trim());//订单号
				huifu.setMaxTenderRate(commonRequst.getMaxTenderRate().trim());//
				huifu.setTransAmt(commonRequst.getAmount().setScale(2).toString().trim());//
				huifu.setBorrowerDetails(commonRequst.getBorrowerDetails().trim());
				huifu.setMerPriv(commonRequst.getRequsetNo().trim());//保存请求的流水号
				huifu.setIsFreeze(commonRequst.getIsFreeze().trim());
				huifu.setFreezeOrdId(commonRequst.getFreezeOrdId().trim());
				huifu.setReqExt(commonRequst.getReqExt());
				huifu.setPageType(commonRequst.getPageType());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim()
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getUsrCustId().trim()
									+ huifu.getMaxTenderRate().trim()
									+ huifu.getBorrowerDetails().trim()
									+ huifu.getIsFreeze().trim()
									+ huifu.getFreezeOrdId().trim()
									+ huifu.getRetUrl().trim()
									+ huifu.getBgRetUrl().trim() 
									+ huifu.getMerPriv().trim()
									+ huifu.getReqExt()
									+ huifu.getPageType();
				System.out.println("汇付注册拼接参数===="+MerData);
				huifu.setChkValue(Sign(MerData));
    			System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("投标申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("投标申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("投标接口对接失败");
		}
		return commonResponse;
	}	
	/**
	 * 标的资金解冻接口
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse unFreeze(CommonRequst commonRequst){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huiFu = new Huifu();
		try{
			huiFu = generatePublicData(huiFu, "UsrUnFreeze", false, true);
			if(null!=huiFu){
				huiFu.setVersion("10");
				huiFu.setOrdId(commonRequst.getOrdId());
				huiFu.setOrdDate(commonRequst.getOrderDate());
				huiFu.setTrxId(commonRequst.getFreezeOrdId());
				huiFu.setMerPriv(commonRequst.getMerPriv());
				huiFu.setReqExt(commonRequst.getReqExt());
				huiFu.setPageType(commonRequst.getPageType());
				String MerData = huiFu.getVersion()
							   + huiFu.getCmdId()
							   + huiFu.getMerCustId()
							   + huiFu.getOrdId()
							   + huiFu.getOrdDate()
							   + huiFu.getTrxId()
							   /*+ huiFu.getRetUrl()*/
							   + huiFu.getBgRetUrl()
							   + huiFu.getMerPriv();
				huiFu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huiFu);
				String param=ThirdPayWebClient.generateParams(params,Huifu.CHARSETUTF8);
				String result =  ThirdPayWebClient.getWebContentByPost(HuifuPayUrl(),param,Huifu.CHARSETUTF8,12000);
				//String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(result!=null){
					JSONObject object = new JSONObject(result);
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("标的流标成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.get("RespDesc").toString());
					}
					commonResponse.setRequestInfo(result);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("标的流标失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return  commonResponse;
	}
	/**
	 * 汇付的放款方法  理财计划起息方法
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse bidLoan(CommonRequst commonRequst){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			
			// 生成公共参数
			huifu = generatePublicData(huifu,"Loans",false,true);
			if(null != huifu){
				huifu.setVersion("20");
				huifu.setOrdId(commonRequst.getRequsetNo().trim());//订单号
				huifu.setOrdDate(commonRequst.getOrderDate().trim());
				huifu.setDivDetails(commonRequst.getDivDetails());
				
				huifu.setOutCustId(commonRequst.getInvest_thirdPayConfigId());//出账客户号
				
				huifu.setTransAmt(commonRequst.getAmount().add(commonRequst.getFee()).toString().trim());
				huifu.setFee(commonRequst.getFee().setScale(2).toString());//放款或者扣款的手续费
				huifu.setSubOrdId(commonRequst.getSubOrdId());//投标id
				huifu.setSubOrdDate(commonRequst.getSubOrdDate());//投标日期
				
				huifu.setInCustId(commonRequst.getThirdPayConfigId());
				
				huifu.setFeeObjFlag(commonRequst.getFeeObjFlag());
				huifu.setIsDefault(commonRequst.getIsDefault());
				huifu.setIsUnFreeze(commonRequst.getIsUnFreeze());//是否解冻
				huifu.setUnFreezeOrdId(commonRequst.getUnFreezeOrdId());
				huifu.setFreezeTrxId(commonRequst.getLoanNoList());
				huifu.setMerPriv(commonRequst.getRequsetNo().trim());//保存请求的流水号
				huifu.setReqExt(commonRequst.getReqExt());
				huifu.setPageType(commonRequst.getPageType());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim()
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getOutCustId().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getFee().trim()
									+ huifu.getSubOrdId().trim()
									+ huifu.getSubOrdDate().trim()
									+ huifu.getInCustId().trim()
									+ huifu.getDivDetails().trim()
									+ huifu.getFeeObjFlag().trim() 
									+ huifu.getIsDefault().trim()
									+ huifu.getIsUnFreeze()
									+ huifu.getUnFreezeOrdId()
									+ huifu.getFreezeTrxId()
									+ huifu.getBgRetUrl()
									+ huifu.getMerPriv()
									+ huifu.getReqExt();
				MerData = MerData.replace("null", "");
				huifu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,Huifu.CHARSETUTF8);
				String result =  ThirdPayWebClient.getWebContentByPost(HuifuPayUrl(),param,Huifu.CHARSETUTF8,12000);
				System.out.println("result="+result);
				String ret = result.substring(0, 33);
				result = ret+"}";
				JSONObject object = new JSONObject(result);
				if(object.get("RespCode").toString().trim().equals("000")){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					commonResponse.setResponseMsg("放款成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("放款失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("放款接口对接失败");
		}
		return commonResponse;
	}
	/**
	 * 散标自助单笔还款
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse repayment(CommonRequst commonRequst){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try{
			// 生成公共参数
			huifu = generatePublicData(huifu,"Repayment",false,true);
			if(null != huifu){
				huifu.setVersion("30");
				huifu.setProId(commonRequst.getProId());
				huifu.setOrdId(commonRequst.getRequsetNo().trim());//订单号
				huifu.setOrdDate(commonRequst.getOrderDate().trim());
				huifu.setOutCustId(commonRequst.getOutCustId().toString());
				huifu.setSubOrdId(commonRequst.getSubOrdId().toString());
				huifu.setSubOrdDate(commonRequst.getSubOrdDate().toString());
				huifu.setOutAcctId(commonRequst.getOutAcctId().toString());
				huifu.setFee(commonRequst.getFee().setScale(2).toString());
				huifu.setInCustId(commonRequst.getInCustId().toString());
				huifu.setInAcctId(commonRequst.getInAcctId().toString());
				huifu.setDivDetails("0.00".equals(commonRequst.getFee().setScale(2).toString())?"":commonRequst.getDivDetails().toString());
				huifu.setFeeObjFlag(commonRequst.getFeeObjFlag().toString());
				huifu.setDzObject(commonRequst.getDzObject().toString());
				huifu.setReqExt(commonRequst.getReqExt().toString());
				if(commonRequst.getFundType()!=null && commonRequst.getFundType().equals("plearly")){
					BigDecimal interestMoney = new BigDecimal(commonRequst.getRetInterest());
					huifu.setPrincipalAmt(commonRequst.getAmount().subtract(interestMoney).setScale(2).toString());
					huifu.setInterestAmt(commonRequst.getRetInterest());
				}else{
					huifu.setPrincipalAmt(commonRequst.getPrincipalAmt().toString());
					huifu.setInterestAmt(commonRequst.getInterestAmt().toString());
				}
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim()
									+ huifu.getMerCustId().trim() 
									+ huifu.getProId().trim()
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getOutCustId()
									+ huifu.getSubOrdId()
									+ huifu.getSubOrdDate()
									+ huifu.getOutAcctId()
									+ huifu.getPrincipalAmt()
									+ huifu.getInterestAmt()
									+ huifu.getFee()
									+ huifu.getInCustId()
									+ huifu.getInAcctId()
									+ huifu.getDivDetails()
									+ huifu.getFeeObjFlag()
									+ huifu.getDzObject()
									+ huifu.getBgRetUrl()
									+ huifu.getMerPriv().trim()
									+ huifu.getReqExt();
				String  MerData1 = huifu.getVersion().trim()+"|"
				+ huifu.getCmdId().trim()+"|"
				+ huifu.getMerCustId().trim()+"|" 
				+ huifu.getProId().trim()+"|"
				+ huifu.getOrdId().trim()+"|"
				+ huifu.getOrdDate().trim()+"|"
				+ huifu.getOutCustId()+"|"
				+ huifu.getSubOrdId()+"|"
				+ huifu.getSubOrdDate()+"|"
				+ huifu.getOutAcctId()+"|"
				+ huifu.getPrincipalAmt()+"|"
				+ huifu.getInterestAmt()+"|"
				+ huifu.getFee()+"|"
				+ huifu.getInCustId()+"|"
				+ huifu.getInAcctId()+"|"
				+ huifu.getDivDetails()+"|"
				+ huifu.getFeeObjFlag()+"|"
				+ huifu.getDzObject()+"|"
				+ huifu.getBgRetUrl()+"|"
				+ huifu.getMerPriv().trim()+"|"
				+ huifu.getReqExt();
				System.out.println(MerData1);
	
				MD5 md5 = new MD5();
				huifu.setChkValue(Sign(md5.md5(MerData, "utf-8")));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				System.out.println("param="+param);
				String outStr = ThirdPayWebClient.getUndecodeByPost(HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println(outStr);
				if(outStr!=null){
					JSONObject object = new JSONObject(outStr);
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("还款成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(URLDecoder.decode(object.getString("RespDesc").toString()
								.trim(), "UTF-8"));
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("还款失败");
				}
				commonResponse.setRequestInfo(outStr);
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("还款接口对接失败");
		}
		return commonResponse;
	}
	
	/**
	 * 散标批量还款
	 */
	public static CommonResponse batchRepayment(CommonRequst commonRequst){
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try{
			// 生成公共参数
			huifu = generatePublicData(huifu,"Repayment",false,true);
			if(null != huifu){
				huifu.setVersion("30");
				huifu.setOutCustId(commonRequst.getOutCustId().toString());
				huifu.setOutAcctId(commonRequst.getOutAcctId().toString());
				huifu.setBatchId("");
				huifu.setMerOrdDate("");
				huifu.setMerPriv("");
			    huifu.setReqExt("");	
				huifu.setProId(commonRequst.getProId());//(commonRequst.getOrderDate().trim());
				huifu.setOrdId(commonRequst.getRequsetNo().trim());//订单号
				huifu.setOrdDate(commonRequst.getOrderDate().trim());
				huifu.setSubOrdId(commonRequst.getSubOrdId().toString());
				huifu.setSubOrdDate(commonRequst.getSubOrdDate().toString());
				huifu.setPrincipalAmt(commonRequst.getPrincipalAmt().toString());
				huifu.setInterestAmt(commonRequst.getInterestAmt().toString());
				huifu.setFee(commonRequst.getFee().toString());
				huifu.setInCustId(commonRequst.getInCustId().toString());
				huifu.setInAcctId(commonRequst.getInAcctId().toString());
				huifu.setDivDetails(commonRequst.getDivDetails().toString());
				huifu.setFeeObjFlag(commonRequst.getFeeObjFlag().toString());
				huifu.setDzObject(commonRequst.getDzObject().toString());
				huifu.setReqExt(commonRequst.getReqExt().toString());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim()
									+ huifu.getMerCustId().trim() 
									+ huifu.getProId().trim()
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getOutCustId()
									+ huifu.getSubOrdId()
									+ huifu.getSubOrdDate()
									+ huifu.getOutAcctId()
									+ huifu.getPrincipalAmt()
									+ huifu.getInterestAmt()
									+ huifu.getFee()
									+ huifu.getInCustId()
									+ huifu.getInAcctId()
									+ huifu.getDivDetails()
									+ huifu.getFeeObjFlag()
									+ huifu.getDzObject()
									+ huifu.getBgRetUrl()
									+ huifu.getMerPriv().trim()
									+ huifu.getReqExt();
				System.out.println("汇付注册拼接参数===="+MerData);
				huifu.setChkValue(Sign(MerData));
    			System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
					commonResponse.setResponseMsg("投标申请成功");
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("投标申请失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("投标接口对接失败");
		}
		return commonResponse;
	}
	
	 /**
     *  删除银行卡
     * @param commonRequst
     * @return
     */
	public static CommonResponse delCard(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"DelCard",false,false);
			if(null != huifu){
				huifu.setVersion("10");
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setCardId(commonRequst.getBankCardNumber());
				//签名验证
				huifu.setVersion("10");
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrCustId().trim() 
									+ huifu.getCardId().trim(); 
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				JSONObject object = new JSONObject(outStr);
				if(outStr!=null&&object!=null){
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("删除银行卡成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("删除银行卡请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("删除银行卡对接失败");
		}
		return commonResponse;
	}
	/**
     *  余额查询
     * @param commonRequst
     * @return
     */
	public static CommonResponse queryBalanceBg(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"QueryBalanceBg",true,true);
			if(null != huifu){
				huifu.setVersion("10");
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrCustId().trim();
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("==========>输出结果是"+outStr);
				JSONObject object = new JSONObject(outStr);
				if(outStr!=null&&object!=null){
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setAvailableAmount(new BigDecimal(object.getString("AvlBal").replaceAll(",", "").toString()));
						commonResponse.setFreezeAmount(new BigDecimal(object.getString("FrzBal").replaceAll(",", "").toString()));
						commonResponse.setBalance(new BigDecimal(object.getString("AcctBal").replaceAll(",", "").toString()));
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("余额查询成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("余额查询请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("余额查询对接失败");
		}
		return commonResponse;
	}
	/**
     *  银行卡查询
     * @param commonRequst
     * @return
     */
	public static CommonResponse queryCardInfo(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"QueryCardInfo",true,true);
			if(null != huifu){
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getUsrCustId().trim();
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				JSONObject object = new JSONObject(outStr);
				if(outStr!=null&&object!=null){
					if(object.getString("RespCode").toString().equals("000")){
						String usrCardInfolist = object.getString("UsrCardInfolist");
						JSONArray jsonArray = new JSONArray(usrCardInfolist);
						int iSize = jsonArray.length();
						for (int i = 0; i < iSize; i++) {
							JSONObject jsonObj = jsonArray.getJSONObject(i);
							commonResponse.setBankCode(jsonObj.getString("CardId").toString());
							commonResponse.setBankName(jsonObj.getString("BankId").toString());
						}	
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("银行卡查询成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("银行卡查询请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("银行卡查询对接失败");
		}
		return commonResponse;
	}
	/**
     *  LOANS：放款交易查询 
     *  REPAYMENT：还款交易查询
     *  TENDER：投标交易查询 
     *  CASH：取现交易查询
     *  FREEZE：冻结解冻交易查询
    * @param commonRequst
     * @return
     */
	public static CommonResponse queryTransStat(CommonRequst commonRequst) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"QueryTransStat",true,true);
			if(null != huifu){
				huifu.setOrdId(commonRequst.getQueryRequsetNo());
				huifu.setOrdDate(sdf.format(commonRequst.getStartDay()));
				if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){
					huifu.setQueryTransType("CASH");
				}else{
					huifu.setQueryTransType(commonRequst.getQueryType());
				}
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim()
									+ huifu.getOrdDate().trim()
									+ huifu.getQueryTransType();
				huifu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				if(outStr!=null){
					JSONObject object = new JSONObject(outStr);
					if(object.getString("RespCode").toString().equals("000")){
						if(object.getString("TransStat").equals("S")){
							List<CommonRecord> recordList = new ArrayList<CommonRecord>();
							CommonRecord record = new CommonRecord();
							record.setStatus("SUCCESS");
							recordList.add(record);
							commonResponse.setRecordList(recordList);
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
							commonResponse.setResponseMsg("查询成功");
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("取现尚未成功");
						}
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("查询成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("查询请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("查询对接失败");
		}
		return commonResponse;
	}
	/**
	 * 交易明细查询(充值)
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse queryTransDetail(CommonRequst commonRequst) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"QueryTransDetail",true,true);
			if(null != huifu){
				huifu.setVersion("10");
				huifu.setOrdId(commonRequst.getQueryRequsetNo());
				huifu.setQueryTransType("SAVE");
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getMerCustId().trim() 
									+ huifu.getOrdId().trim()
									+ huifu.getQueryTransType();
				huifu.setChkValue(Sign(MerData));
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				System.out.println("param="+param);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				if(outStr!=null){
					JSONObject object = new JSONObject(outStr);
					if(object.getString("RespCode").toString().equals("000")){
						if(object.getString("TransStat").equals("S")){
							List<CommonRecord> recordList = new ArrayList<CommonRecord>();
							CommonRecord record = new CommonRecord();
							record.setStatus("SUCCESS");
							recordList.add(record);
							commonResponse.setRecordList(recordList);
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
							commonResponse.setResponseMsg("查询成功");
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							commonResponse.setResponseMsg("充值尚未成功");
						}
						
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("查询请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("查询对接失败");
		}
		return commonResponse;
	}
	/**
     *  自动扣款转账（商户用--->转出）
     * @param commonRequst
     * @return
     */
	public static CommonResponse transfer(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"Transfer",false,true);
			if(null != huifu){
				DecimalFormat   df   =new  DecimalFormat("#0.00"); 
				String transamt = df.format(commonRequst.getAmount());
				huifu.setOutCustId(huifu.getMerCustId());
				huifu.setOutAcctId("MDT000001");
				huifu.setTransAmt(transamt);
				huifu.setInCustId(commonRequst.getThirdPayConfigId());
				huifu.setOrdId(commonRequst.getRequsetNo());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getOutCustId().trim()
									+ huifu.getOutAcctId().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getInCustId().trim()
									//+ huifu.getRetUrl().trim()
									+ huifu.getBgRetUrl().trim()
									+ huifu.getMerPriv().trim()
									;
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				JSONObject object = new JSONObject(outStr);
				if(outStr!=null&&object!=null){
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("自动扣款转账（商户用）成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("自动扣款转账（商户用）请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("自动扣款转账（商户用）对接失败");
		}
		return commonResponse;
	}
	/**
     *  用户账户支付
     * @param commonRequst
     * @return
     */
	public static CommonResponse usrAcctPay(CommonRequst commonRequst) {
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"UsrAcctPay",true,true);
			if(null != huifu){
				huifu.setOrdId(commonRequst.getRequsetNo());
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());
				huifu.setMerCustId(huifu.getMerCustId());
				huifu.setTransAmt(commonRequst.getAmount().setScale(2).toString());
				huifu.setInAcctId("MDT000001");
				huifu.setInAcctType("MERDT");
				huifu.setMerPriv(/*!"".equals(commonRequst.getMerPriv())?commonRequst.getMerPriv():*/commonRequst.getRequsetNo());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getUsrCustId().trim()
									+ huifu.getMerCustId().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getInAcctId().trim()
									+ huifu.getInAcctType().trim()
									+ huifu.getRetUrl().trim()
									+ huifu.getBgRetUrl().trim()
									+ huifu.getMerPriv().trim()
									;
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&rett!=null){
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("用户账户支付成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("用户账户支付失败");
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("用户账户支付请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("用户账户支付对接失败");
		}
		return commonResponse;
	}
	/**
	 * 购买债权
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse CreditAssign(CommonRequst commonRequst){
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"CreditAssign",true,true);
			if(null != huifu){
				huifu.setVersion("30");
				huifu.setSellCustId(commonRequst.getSellCustId());//转让人客户号
				huifu.setCreditAmt(commonRequst.getCreditAmt());//转让金额
				huifu.setCreditDealAmt(commonRequst.getCreditDealAmt());//承接金额
				huifu.setBidDetails(commonRequst.getBidDetails());//债权转让明细
				huifu.setFee(commonRequst.getFee().setScale(2).toString());//扣款手续费
				huifu.setDivDetails("");//分账账户串
				huifu.setBuyCustId(commonRequst.getThirdPayConfigId());//承接人账户号
				huifu.setOrdId(commonRequst.getRequsetNo());//订单号
				huifu.setOrdDate(commonRequst.getOrderDate());//订单日期
				huifu.setLcId("");//挂牌债权id
				huifu.setTotalLcAmt("");//挂牌债权总金额
				huifu.setMerPriv("");//商户私有域
				huifu.setReqExt("");//入参扩展域
				huifu.setPageType("");//页面类型
				//签名验证
				String  MerData = huifu.getVersion().trim()
				+ huifu.getCmdId().trim()
				+ huifu.getMerCustId().trim()
				+ huifu.getSellCustId().trim()
				+ huifu.getCreditAmt().trim()
				+ huifu.getCreditDealAmt().trim()
				+ huifu.getBidDetails().trim()
				+ huifu.getFee().trim()
				+ huifu.getDivDetails().trim()
				+ huifu.getBuyCustId().trim()
				+ huifu.getOrdId().trim()
				+ huifu.getOrdDate().trim()
				+ huifu.getRetUrl().trim()
				+ huifu.getBgRetUrl().trim()
				+ huifu.getMerPriv().trim()
				+ huifu.getReqExt().trim()
				+ huifu.getPageType().trim()
				+ huifu.getLcId().trim()
				+ huifu.getTotalLcAmt().trim();

				String  MerData1 = huifu.getVersion().trim()+"|"
									+ huifu.getCmdId().trim()+"|" 
									+ huifu.getMerCustId().trim()+"|" 
									+ huifu.getSellCustId().trim()+"|"
									+ huifu.getCreditAmt().trim()+"|"
									+ huifu.getCreditDealAmt().trim()+"|"
									+ huifu.getBidDetails().trim()+"|"
									+ huifu.getFee().trim()+"|"
									+ huifu.getDivDetails().trim()+"|"
									+ huifu.getBuyCustId().trim()+"|"
									+ huifu.getOrdId().trim()+"|"
									+ huifu.getOrdDate().trim()+"|"
									+ huifu.getRetUrl().trim()+"|"
									+ huifu.getBgRetUrl().trim()+"|"
									+ huifu.getMerPriv().trim()+"|"
									+ huifu.getReqExt().trim()+"|"
									+ huifu.getPageType().trim()+"|"
									+ huifu.getLcId().trim()+"|"
									+ huifu.getTotalLcAmt().trim();
				System.out.println(MerData);
				MD5  md5 = new MD5();
				huifu.setChkValue(Sign(md5.md5(MerData, "utf8")));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String [] rett=ThirdPayWebClient.operateParameter(HuifuPayUrl(), params,Huifu.CHARSETUTF8);
				if(rett!=null&&rett!=null){
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						commonResponse.setResponseMsg("购买债权申请成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg("购买债权申请成功");
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("购买债权请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("购买债权对接失败");
		}
		return commonResponse;

	}
	
	/**
	 * 取消挂牌
	 */
	public static CommonResponse cancelDeal(CommonRequst commonRequst){
		CommonResponse commonResponse=new  CommonResponse();
		commonResponse.setCommonRequst(commonRequst);
		Huifu huifu = new Huifu();
		try {
			// 生成公共参数
			huifu = generatePublicData(huifu,"Transfer",false,true);
			if(null != huifu){
				huifu.setOutCustId(huifu.getMerCustId());
				huifu.setOutAcctId("MDT000001");
				huifu.setTransAmt(commonRequst.getAmount().toString());
				huifu.setInCustId(commonRequst.getThirdPayConfigId());
				huifu.setOrdId(commonRequst.getRequsetNo());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				//签名验证
				String  MerData = huifu.getVersion().trim()
									+ huifu.getCmdId().trim() 
									+ huifu.getOrdId().trim() 
									+ huifu.getOutCustId().trim()
									+ huifu.getOutAcctId().trim()
									+ huifu.getTransAmt().trim()
									+ huifu.getInCustId().trim()
									+ huifu.getBgRetUrl().trim()
									+ huifu.getMerPriv().trim()
									;
				huifu.setChkValue(Sign(MerData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,MoneyMore.CHARSETUTF8);
				String outStr = ThirdPayWebClient.getUndecodeByPost(	HuifuPayUrl(), param, MoneyMore.CHARSETUTF8,120000);
				System.out.println("outStr="+outStr);
				JSONObject object = new JSONObject(outStr);
				if(outStr!=null&&object!=null){
					if(object.getString("RespCode").toString().equals("000")){
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						commonResponse.setResponseMsg("自动扣款转账（商户用）成功");
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						commonResponse.setResponseMsg(object.getString("RespDesc").toString());
					}
					commonResponse.setRequestInfo(outStr);
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					commonResponse.setResponseMsg("自动扣款转账（商户用）请求失败");
				}
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				commonResponse.setResponseMsg("基本参数获取失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			commonResponse.setResponseMsg("自动扣款转账（商户用）对接失败");
		}
		return commonResponse;
	}

	public static CommonResponse checkRegister(CommonRequst commonRequst){
		CommonResponse response = new CommonResponse();
		Huifu huifu  = new Huifu();
		try{
			huifu = generatePublicData(huifu, "CorpRegisterQuery", false, false);
			if(null!=huifu){
				huifu.setVersion("10");
				huifu.setBusiCode(commonRequst.getBusiCode());//出账客户号
				huifu.setReqExt(commonRequst.getReqExt());//出账客户号
				huifu.setBusiCode("1221115");
				/*Version +CmdId + OrdId + OutCustId +OutAcctId + TransAmt+ InCustId+ InAcctId+RetUrl + BgRetUrl+ MerPriv*/
				String merData = huifu.getVersion()
							   + huifu.getCmdId() 
							   + huifu.getMerCustId()
							   + huifu.getBusiCode()
							   + huifu.getReqExt();
				System.out.println(merData);
				huifu.setChkValue(Sign(merData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,Huifu.CHARSETUTF8);
				String result =  ThirdPayWebClient.getWebContentByPost(HuifuPayUrl(),param,Huifu.CHARSETUTF8,12000);
				System.out.println(result);
				JSONObject result1 = new JSONObject(result);
				if(result1.get("RespCode").toString().trim().equals("000")){
					//储存标的账号(发标的时候应用)
					//commonResponse.setRemark(result1.get("ProId").toString().trim());
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg("批量还款成功");
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("批量还款失败失败");
				}
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return response;
	}

	public static CommonResponse openBidAuto(CommonRequst commonRequst){
		CommonResponse response = new CommonResponse();
		Huifu huifu  = new Huifu();
		try{
			huifu = generatePublicData(huifu, "AutoTenderPlan", true, false);
			if(null!=huifu){
				huifu.setVersion("10");
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());//用户客户号
				huifu.setTenderPlanType("W");//w --完全授权   q --部分授权
				huifu.setTransAmt(commonRequst.getAmount().toString());
				huifu.setMerPriv(commonRequst.getRequsetNo());
				huifu.setPageType("");
			/*	Version
				+CmdId + MerCustId + UsrCustId +
				TenderPlanType + TransAmt + RetUrl + MerPriv
				+ PageType*/
				String merData = huifu.getVersion().trim()
							   + huifu.getCmdId().trim() 
							   + huifu.getMerCustId().trim()
							   + huifu.getUsrCustId().trim()
							   + huifu.getTenderPlanType().trim()
							   + huifu.getTransAmt().trim()
							   + huifu.getRetUrl().trim()
							   + huifu.getMerPriv().trim()
							   + huifu.getPageType().trim();
				System.out.println(merData);
				huifu.setChkValue(Sign(merData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,Huifu.CHARSETUTF8);
				String[] rett =  ThirdPayWebClient.operateParameter(HuifuPayUrl(), params, Huifu.CHARSETUTF8);//(HuifuPayUrl(),param,Huifu.CHARSETUTF8,12000);
				if(rett!=null&&rett!=null){
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						response.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						response.setResponseMsg("开通自动投标申请成功");
					}else{
						response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						response.setResponseMsg("开通自动投标申请失败");
					}
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("开通自动投标请求失败");
				}
			/*	System.out.println(result);
				JSONObject result1 = new JSONObject(result);
				if(result1.get("RespCode").toString().trim().equals("000")){
					//储存标的账号(发标的时候应用)
					//commonResponse.setRemark(result1.get("ProId").toString().trim());
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg("自动投标授权成功");
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("自动投标授权失败");
				}*/
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return response;
	}
	
	
	
	/**
	 * 关闭自动投标
	 * @param commonRequst
	 * @return
	 */
	public static CommonResponse closeBidAuto(CommonRequst commonRequst){
		CommonResponse response = new CommonResponse();
		Huifu huifu  = new Huifu();
		try{
			huifu = generatePublicData(huifu, "AutoTenderPlanClose", true, false);
			if(null!=huifu){
				huifu.setVersion("10");
				huifu.setUsrCustId(commonRequst.getThirdPayConfigId());//用户客户号
				huifu.setMerPriv(commonRequst.getMerPriv());
				huifu.setPageType("");
			/*	Version +CmdId + MerCustId + UsrCustId +RetUrl + MerPriv + PageType*/
				String merData = huifu.getVersion().trim()
							   + huifu.getCmdId().trim() 
							   + huifu.getMerCustId().trim()
							   + huifu.getUsrCustId().trim()
							   + huifu.getRetUrl().trim()
							   + huifu.getMerPriv().trim()
							   + huifu.getPageType().trim();
				System.out.println(merData);
				huifu.setChkValue(Sign(merData));
				System.out.println("加密后参数===="+huifu.getChkValue());
				// 生成注册map
				Map<String, String> params = ObjectFormatUtil.createMap(Huifu.class,huifu);
				System.out.println("参数转map===="+params);
				String param=ThirdPayWebClient.generateParams(params,Huifu.CHARSETUTF8);
				String[] rett =  ThirdPayWebClient.operateParameter(HuifuPayUrl(), params, Huifu.CHARSETUTF8);//(HuifuPayUrl(),param,Huifu.CHARSETUTF8,12000);
				if(rett!=null&&rett!=null){
					if(rett!=null&&ThirdPayConstants.RECOD_SUCCESS.equals(rett[0])){
						response.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
						response.setResponseMsg("开通自动投标申请成功");
					}else{
						response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						response.setResponseMsg("开通自动投标申请失败");
					}
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("开通自动投标请求失败");
				}
			/*	System.out.println(result);
				JSONObject result1 = new JSONObject(result);
				if(result1.get("RespCode").toString().trim().equals("000")){
					//储存标的账号(发标的时候应用)
					//commonResponse.setRemark(result1.get("ProId").toString().trim());
					response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					response.setResponseMsg("自动投标授权成功");
				}else{
					response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					response.setResponseMsg("自动投标授权失败");
				}*/
			}else{
				response.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
				response.setResponseMsg("基本参数获取失败");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return response;
	}
}
