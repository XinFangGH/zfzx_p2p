package com.hurong.credit.service.pay.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.credit.model.pay.MadaiLoanInfoBean;
import com.hurong.credit.model.pay.MadaiLoanInfoSecondaryBean;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.HibernateProxyTypeAdapter;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.RsaHelper;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;



public class IPayServiceImpl implements IPayService {
	@Resource
	private SysConfigService sysConfigService;
	
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	//public  String URL=configMap.get("MM_PostUrl").toString();
	
	@Override
	public String transfer(MoneyMoreMore moneyMoreMore,String basePath,HttpServletResponse response) {
        
		
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/loan.action");
		moneyMoreMore.setReturnURL (basePath + "pay/transferReturnFont.do");
		moneyMoreMore.setNotifyURL (basePath + "pay/transferReturnBack.do");
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		//加密数据字符串
		String dataStr = moneyMoreMore.getLoanJsonList() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() + moneyMoreMore.getTransferAction() + moneyMoreMore.getAction() + moneyMoreMore.getTransferType()/*+moneyMoreMore.getNeedAudit()*/ + moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() +moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("LoanJsonList", moneyMoreMore.getLoanJsonList());
		params.put("PlatformMoneymoremore",AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("TransferAction", moneyMoreMore.getTransferAction());
		params.put("Action", moneyMoreMore.getAction());
		params.put("TransferType", moneyMoreMore.getTransferType());
		//params.put("NeedAudit", moneyMoreMore.getNeedAudit());
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		String retdata="";
		try {
			//1 手动转账 2自动转账
			if(moneyMoreMore.getAction().equals("1")){
				String url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL(),Constants.CHAR_UTF);
				WebClient.SendByUrl(response, url,Constants.CHAR_UTF);
			}else{
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retdata=  WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,Constants.CHAR_UTF,36000000);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}
	@Override
	public String[] balanceQuery(String moneymoremoreId,String type) {
		String[] money=new String[3];
		MoneyMoreMore moneyMoreMore=new MoneyMoreMore();
		moneyMoreMore.setPlatformId(moneymoremoreId);
		moneyMoreMore.setPlatformType(type);
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/balancequery.action");
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr = moneyMoreMore.getPlatformId() + moneyMoreMore.getPlatformType() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		Map<String, String> params=new HashMap<String, String>();
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("PlatformId", moneyMoreMore.getPlatformId());
		params.put("PlatformType", moneyMoreMore.getPlatformType());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		
		String retdata = null;
		try {
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retdata=  WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,Constants.CHAR_UTF,360000);//返回数据
			if(retdata!=null&&!retdata.equals("")){
			money=retdata.replace("\r", "").replace("\n", "").split("\\|");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return money;
	}
	
	@Override
	public String delAccount(String pid) {
		String url="http://218.4.234.150:88/main/loantest/loandeletetestinfo.action";
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr =  AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		String setSignInfo=rsa.signData(dataStr, privatekey);
	
		Map<String, String> params=new HashMap<String, String>();
		params.put("p",pid);
		params.put("s", setSignInfo);
		
		String retdata = null;
		try {
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retdata=  WebClient.getWebContentByPost(url,param,Constants.CHAR_UTF,360000);//返回数据
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retdata;
	}
	
	@Override
	public String balanceQueryMoneys(String moneymoremoreId,String type) {
		String[] money=new String[3];
		MoneyMoreMore moneyMoreMore=new MoneyMoreMore();
		moneyMoreMore.setPlatformId(moneymoremoreId);
		moneyMoreMore.setPlatformType(type);
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/balancequery.action");
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr = moneyMoreMore.getPlatformId() + moneyMoreMore.getPlatformType() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		Map<String, String> params=new HashMap<String, String>();
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("PlatformId", moneyMoreMore.getPlatformId());
		params.put("PlatformType", moneyMoreMore.getPlatformType());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
	
		String retdata = null;
		try {
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retdata=  WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,Constants.CHAR_UTF,360000);//返回数据
			if(retdata!=null&&!retdata.equals("")){
			money=retdata.replace("\r", "").replace("\n", "").split("\\|");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retdata;
	}
	
	
	/**
	 * 绑定 (已作废)
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	@Override
	public String bind(MoneyMoreMore moneyMoreMore, String basePath) {
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanbind.action");
		moneyMoreMore.setReturnURL (basePath + "pay/bindMorFont.do");
		moneyMoreMore.setNotifyURL (basePath + "pay/bindMorBack.do");
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		
		String dataStr = moneyMoreMore.getLoanPlatformAccount() + moneyMoreMore.getLoanMoneymoremore() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() + moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	    Map<String, String> params=new HashMap<String, String>();
		params.put("LoanPlatformAccount", moneyMoreMore.getLoanPlatformAccount());
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("LoanMoneymoremore", moneyMoreMore.getLoanMoneymoremore());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		
		String retStr="";
		
		try {
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retStr=WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,Constants.CHAR_UTF,360000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retStr;
	}
	
	/**
	 * 注册并绑定 全自动 返回json
	 */
	@Override
	public ResultBean registerAndBind(MoneyMoreMore moneyMoreMore, String basePath) {
		ResultBean resultBean=new ResultBean();
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanregisterbind.action");
		moneyMoreMore.setReturnURL (basePath + "pay/bindFont.do");//前台方法
		moneyMoreMore.setNotifyURL (basePath + "pay/bindBack.do");//后台方法
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr = moneyMoreMore.getRegisterType() + moneyMoreMore.getMobile() + moneyMoreMore.getEmail() + moneyMoreMore.getRealName() + moneyMoreMore.getIdentificationNo() + moneyMoreMore.getImage1() + moneyMoreMore.getImage2() +moneyMoreMore.getLoanPlatformAccount() +AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() +  moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() +moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
		Map<String, String> params=new HashMap<String, String>();
		params.put("RegisterType", moneyMoreMore.getRegisterType());
		params.put("Mobile", moneyMoreMore.getMobile());
		params.put("Email", moneyMoreMore.getEmail());
		params.put("RealName", moneyMoreMore.getRealName());
		params.put("IdentificationNo", moneyMoreMore.getIdentificationNo());
		params.put("Image1", moneyMoreMore.getImage1());
		params.put("Image2", moneyMoreMore.getImage2());
		params.put("LoanPlatformAccount", moneyMoreMore.getLoanPlatformAccount());
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		String retStr="";
		try {
			String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			Gson gson=new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			retStr=WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,Constants.CHAR_UTF,360000);
			if(retStr!=null&&!retStr.equals("")){
				resultBean=setBindCodeMsg(gson.fromJson(retStr, ResultBean.class));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
	/**
	 * 注册并绑定 半自动 返回页面 
	 */
	@Override
	public void registerAndBind(MoneyMoreMore moneyMoreMore, String basePath,HttpServletResponse response) {
		
		ResultBean resultBean=new ResultBean();
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanregisterbind.action");
		moneyMoreMore.setReturnURL (basePath + "pay/bindFont.do");//前台方法
		moneyMoreMore.setNotifyURL (basePath + "pay/bindBack.do");//后台方法
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr = moneyMoreMore.getRegisterType() + moneyMoreMore.getMobile() + moneyMoreMore.getEmail() +moneyMoreMore.getRealName()+ moneyMoreMore.getIdentificationNo() + moneyMoreMore.getImage1() + moneyMoreMore.getImage2() +moneyMoreMore.getLoanPlatformAccount() +AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() + moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() + moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		//String dataStr="218501140452111@yuseen.com诚致远320583198809115945chx123p689620140611161150873http://localhost:9099/zhiwei_p2p_v3.5.1/pay/bindFont.dohttp://localhost:9099/zhiwei_p2p_v3.5.1/pay/bindBack.do";
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey).replaceAll("\r", "").replaceAll("\n", ""));
		String signature=moneyMoreMore.getSignInfo().replaceAll("\r", "").replaceAll("\n", "");
	    
	    
	    
	    
		Map<String, String> params=new HashMap<String, String>();
		params.put("RegisterType", moneyMoreMore.getRegisterType());
		params.put("Mobile", moneyMoreMore.getMobile());
		params.put("Email", moneyMoreMore.getEmail());
		params.put("RealName",moneyMoreMore.getRealName());
		params.put("IdentificationNo", moneyMoreMore.getIdentificationNo());
		params.put("Image1", moneyMoreMore.getImage1());
		params.put("Image2", moneyMoreMore.getImage2());
		params.put("LoanPlatformAccount", moneyMoreMore.getLoanPlatformAccount());
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		try {
			String url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL(),Constants.CHAR_UTF);
			WebClient.SendByUrl(response, url,Constants.CHAR_UTF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 提现接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	@Override
	public String withdraws(MoneyMoreMore moneyMoreMore,HttpServletResponse respose, String basePath) {
		String retdata ="";
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanwithdraws.action");
		moneyMoreMore.setReturnURL (basePath + "pay/withdrawFont.do");
		moneyMoreMore.setNotifyURL (basePath + "pay/withdrawBack.do");
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String publickey = AppUtil.getSysConfig().get("MM_PublicKey").toString(); 
		
		String dataStr=moneyMoreMore.getWithdrawMoneymoremore() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() + moneyMoreMore.getOrderNo() + moneyMoreMore.getAmount() + moneyMoreMore.getFeePercent() +moneyMoreMore.getFeeMax()+ moneyMoreMore.getCardNo() +moneyMoreMore.getCardType() + moneyMoreMore.getBankCode() + moneyMoreMore.getBranchBankName() + moneyMoreMore.getProvince() + moneyMoreMore.getCity() + moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() + moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo(rsa.signData(md5.getMD5Info(dataStr), privatekey).replaceAll("\r", "").replaceAll("\n", ""));
		moneyMoreMore.setCardNo(rsa.encryptData(moneyMoreMore.getCardNo(), publickey).replaceAll("\r", "").replaceAll("\n", ""));
		Map<String, String> params=new HashMap<String, String>();
		params.put("OrderNo", moneyMoreMore.getOrderNo());
		params.put("Amount", moneyMoreMore.getAmount());
		
		params.put("FeePercent", moneyMoreMore.getFeePercent());
		params.put("CardNo", moneyMoreMore.getCardNo());
		params.put("FeeMax", moneyMoreMore.getFeeMax());
		params.put("CardType", moneyMoreMore.getCardType());
		params.put("BankCode", moneyMoreMore.getBankCode());
		
		params.put("BranchBankName", moneyMoreMore.getBranchBankName());
		params.put("Province", moneyMoreMore.getProvince());
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("City", moneyMoreMore.getCity());
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("WithdrawMoneymoremore", moneyMoreMore.getWithdrawMoneymoremore());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		
		try {
			
			/*String param=UrlUtils.generateParams(params,Constants.CHAR_UTF);
			retdata=  WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,"utf-8",360000);*/
			
			String url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL(),Constants.CHAR_UTF);
			WebClient.SendByUrl(respose, url,Constants.CHAR_UTF);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retdata;
	}
	
	
	/**
	 * 充值接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	@Override
	public String recharge(MoneyMoreMore moneyMoreMore, HttpServletResponse respose, String basePath) {
		String retdata ="";
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanrecharge.action");
		moneyMoreMore.setReturnURL (basePath + "pay/rechargeFont.do");
		moneyMoreMore.setNotifyURL (basePath + "pay/rechargeBack.do");
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		
		String dataStr = moneyMoreMore.getRechargeMoneymoremore() +  AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() +  moneyMoreMore.getOrderNo() +  moneyMoreMore.getAmount() +   moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() +moneyMoreMore.getReturnURL() +  moneyMoreMore.getNotifyURL();
		
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("RechargeMoneymoremore", moneyMoreMore.getRechargeMoneymoremore());
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());//平台乾多多标识固定值
		
		params.put("OrderNo", moneyMoreMore.getOrderNo());
		params.put("Amount", moneyMoreMore.getAmount());
		
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		String url="";
		/*try{
		 url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL());
		}catch(Exception e){
		}*/
		try {
			 url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL(),Constants.CHAR_UTF);
			 WebClient.SendByUrl(respose, url,Constants.CHAR_UTF);
			/*String param=UrlUtils.generateParams(params);
			retdata=  WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,"utf-8",360000);*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retdata;
	}
	/**
	 * 授权接口
	 */
	@Override
	public ResultBean loanAuthorize(MoneyMoreMore moneyMoreMore, String basePath,HttpServletResponse response) {
		ResultBean retBean=new ResultBean();
		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanauthorize.action");
		moneyMoreMore.setReturnURL (basePath + "pay/loanAuthorizeFont.do");//前台方法
		moneyMoreMore.setNotifyURL (basePath + "pay/loanAuthorizeBack.do");//后台方法
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		String dataStr = moneyMoreMore.getMoneymoremoreId() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() +  moneyMoreMore.getAuthorizeTypeOpen() +  moneyMoreMore.getAuthorizeTypeClose() +  moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() + moneyMoreMore.getReturnURL() +  moneyMoreMore.getNotifyURL();		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		
		Map<String, String> params=new HashMap<String, String>();
		params.put("PlatformMoneymoremore", AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("MoneymoremoreId", moneyMoreMore.getMoneymoremoreId());
		params.put("AuthorizeTypeOpen", moneyMoreMore.getAuthorizeTypeOpen());
		params.put("AuthorizeTypeClose", moneyMoreMore.getAuthorizeTypeClose());
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		String retStr = null;
		try {
			Gson gson=new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			String url=UrlUtils.generateUrl(params, moneyMoreMore.getSubmitURL(),"utf-8");
			 // WebClient.getWebContentByPost(moneyMoreMore.getSubmitURL(),param,"utf-8",360000);//返回数据
			  response.sendRedirect(url);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retBean;
	}
	
	
	@Override
	//生成转账列表
	public MoneyMoreMore loanJsonList(MoneyMoreMore moneyMoreMore){
		List<MadaiLoanInfoBean> listmlib = new ArrayList<MadaiLoanInfoBean>();
		MadaiLoanInfoBean mlib = new MadaiLoanInfoBean();
		mlib.setLoanOutMoneymoremore(moneyMoreMore.getLoanOutMoneymoremore1());
		mlib.setLoanInMoneymoremore(moneyMoreMore.getLoanInMoneymoremore1());
		mlib.setOrderNo(moneyMoreMore.getOrderNo());
		mlib.setBatchNo(moneyMoreMore.getBatchNo1());
		mlib.setAmount(moneyMoreMore.getAmount1());
		mlib.setFullAmount(moneyMoreMore.getFullAmount1());
		mlib.setTransferName(moneyMoreMore.getTransferName1());
		mlib.setRemark(moneyMoreMore.getRemark1());
		//mlib.setSecondaryJsonList(secondaryJsonList(new BigDecimal(mlib.getAmount()),Double.valueOf(moneyMoreMore.getFeePercent())));
		listmlib.add(mlib);
		//设置 转账列表
		moneyMoreMore.setLoanJsonList(Common.JSONEncode(listmlib));
		return moneyMoreMore;
		
	}
	@Override
	//生成二次转账列表
	public String secondaryJsonList(BigDecimal money,Double reePercent){
		List<MadaiLoanInfoSecondaryBean> listmlisb = new ArrayList<MadaiLoanInfoSecondaryBean>();
		//二次转账收款人一
		MadaiLoanInfoSecondaryBean mlisb = new MadaiLoanInfoSecondaryBean();
		mlisb.setLoanInMoneymoremore(AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());//收款人钱多多标识
		mlisb.setAmount(money.multiply(new BigDecimal(reePercent)).toString());// 收费金额
		mlisb.setTransferName("平台手续费");//用途
		mlisb.setRemark("备注");//备注// add to your "external" style sheet that controls the "onload" table cell color, border width and color.



		listmlisb.add(mlisb);
		//二次转账收款人二
		/*mlisb = new MadaiLoanInfoSecondaryBean();
		mlisb.setLoanInMoneymoremore(SLoanInMoneymoremore2);
		mlisb.setAmount(SAmount2);
		mlisb.setTransferName(STransferName2);
		mlisb.setRemark(SRemark2);
		listmlisb.add(mlisb);*/
		
		return  Common.JSONEncode(listmlisb);
		
	}
	@Override
	public void moneyReaease(MoneyMoreMore moneyMoreMore,String basePath,HttpServletResponse response) {

		moneyMoreMore.setSubmitURL(configMap.get("MM_PostUrl").toString()+ "loan/toloanrelease.action");
		moneyMoreMore.setReturnURL (basePath + "pay/moneyReaeaseReturnFont.do");
		moneyMoreMore.setNotifyURL (basePath + "pay/moneyReaeaseReturnBack.do");
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		moneyMoreMore.setRandomTimeStamp(Common.getRandomNum(2)+com.hurong.core.util.DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS")); //启用防抵赖 
		String privatekey = AppUtil.getSysConfig().get("MM_PrivateKey").toString();
		//加密数据字符串
		String dataStr = moneyMoreMore.getMoneymoremoreId() + AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString() + moneyMoreMore.getOrderNo() + moneyMoreMore.getAmount() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark2() +  moneyMoreMore.getRandomTimeStamp() + moneyMoreMore.getRemark1() + moneyMoreMore.getRemark2() + moneyMoreMore.getRemark3() +moneyMoreMore.getReturnURL() + moneyMoreMore.getNotifyURL();
		// 签名
		RsaHelper rsa = RsaHelper.getInstance();
		MD5 md5=new MD5();
		moneyMoreMore.setSignInfo (rsa.signData(md5.getMD5Info(dataStr), privatekey));
	
		Map<String, String> params=new HashMap<String, String>();
		params.put("MoneymoremoreId", moneyMoreMore.getMoneymoremoreId());
		params.put("PlatformMoneymoremore",AppUtil.getSysConfig().get("MM_PlatformMoneymoremore").toString());
		params.put("OrderNo", moneyMoreMore.getOrderNo());
		params.put("Amount", moneyMoreMore.getAmount());
		
		params.put("RandomTimeStamp", moneyMoreMore.getRandomTimeStamp());
		params.put("Remark1", moneyMoreMore.getRemark1());
		params.put("Remark2", moneyMoreMore.getRemark2());
		params.put("Remark3", moneyMoreMore.getRemark3());
		
		params.put("ReturnURL", moneyMoreMore.getReturnURL());
		params.put("NotifyURL", moneyMoreMore.getNotifyURL());
		params.put("SignInfo", moneyMoreMore.getSignInfo());
		try {
			String url=UrlUtils.generateUrl(params,moneyMoreMore.getSubmitURL(),Constants.CHAR_UTF);
			WebClient.SendByUrl(response, url,Constants.CHAR_UTF);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public ResultBean transferNotify(String retStr) {
		ResultBean result;
		Gson gson=new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
		if(!retStr.equals("")){
			result=setCodeMsg(gson.fromJson(retStr, ResultBean.class));
		}else{
			result=null;
		}
		return result;
	}
	//转账返回code
	private ResultBean setCodeMsg(ResultBean resultBean){
		if(resultBean.getResultCode().equals("88")){
			resultBean.setCodeMsg("成功！");
		}else if(resultBean.getResultCode().equals("01")){
			resultBean.setCodeMsg("转账列表错误！");
		}else if(resultBean.getResultCode().equals("02")){
			resultBean.setCodeMsg("操作类型错误！");
		}else if(resultBean.getResultCode().equals("03")){
			resultBean.setCodeMsg("转账方式错误！");
		}else if(resultBean.getResultCode().equals("04")){
			resultBean.setCodeMsg("平台乾多多标识错误！");
		}else if(resultBean.getResultCode().equals("05")){
			resultBean.setCodeMsg("加密验证失败！");
		}else if(resultBean.getResultCode().equals("06")){
			resultBean.setCodeMsg("付款人乾多多标识错误！");
		}else if(resultBean.getResultCode().equals("07")){
			resultBean.setCodeMsg("收款人乾多多标识错误！");
		}else if(resultBean.getResultCode().equals("08")){
			resultBean.setCodeMsg("平台订单号错误！");
		}else if(resultBean.getResultCode().equals("09")){
			resultBean.setCodeMsg("平台标号错误！");
		}else if(resultBean.getResultCode().equals("10")){
			resultBean.setCodeMsg("金额错误！");
		}else if(resultBean.getResultCode().equals("11")){
			resultBean.setCodeMsg("付款人不唯一！");
		}else if(resultBean.getResultCode().equals("12")){
			resultBean.setCodeMsg("付款人乾多多账号不存在！");
		}else if(resultBean.getResultCode().equals("13")){
			resultBean.setCodeMsg("付款人未绑定乾多多！");
		}else if(resultBean.getResultCode().equals("14")){
			resultBean.setCodeMsg("付款人乾多多绑定的网贷平台错误！");
		}else if(resultBean.getResultCode().equals("15")){
			resultBean.setCodeMsg("收款人乾多多账号不存在！");
		}else if(resultBean.getResultCode().equals("16")){
			resultBean.setCodeMsg("收款人未绑定乾多多！");
		}else if(resultBean.getResultCode().equals("17")){
			resultBean.setCodeMsg("收款人乾多多绑定的网贷平台错误！");
		}else if(resultBean.getResultCode().equals("18")){
			resultBean.setCodeMsg("重复订单！");
		}else if(resultBean.getResultCode().equals("19")){
			resultBean.setCodeMsg("解冻退款操作时未找到原订单！");
		}else if(resultBean.getResultCode().equals("20")){
			resultBean.setCodeMsg("解冻退款操作时原订单状态错误！");
		}else if(resultBean.getResultCode().equals("21")){
			resultBean.setCodeMsg("乾多多账户可用余额不足！");
		}else if(resultBean.getResultCode().equals("22")){
			resultBean.setCodeMsg("支付密码输入错误！");
		}else if(resultBean.getResultCode().equals("23")){
			resultBean.setCodeMsg("短信验证码输入错误！");
		}else if(resultBean.getResultCode().equals("24")){
			resultBean.setCodeMsg("安保问题输入错误！");
		}else if(resultBean.getResultCode().equals("25")){
			resultBean.setCodeMsg("转账类型错误！");
		}else if(resultBean.getResultCode().equals("26")){
			resultBean.setCodeMsg("账户正在被别的线程操作!");
		}else if(resultBean.getResultCode().equals("27")){
			resultBean.setCodeMsg("标额错误！");
		}else if(resultBean.getResultCode().equals("28")){
			resultBean.setCodeMsg("投标总金额超过标额！");
		}else if(resultBean.getResultCode().equals("29")){
			resultBean.setCodeMsg("二次分配列表错误！");
		}else if(resultBean.getResultCode().equals("30")){
			resultBean.setCodeMsg("二次分配金额超过转账金额！");
		}else if(resultBean.getResultCode().equals("31")){
			resultBean.setCodeMsg("自动转账未授权！");
		}
		return resultBean;
	}
    /**
     * 返回绑定code
     * @param resultBean
     * @return
     */
	private ResultBean setWithdrawsMsg(ResultBean resultBean){
		 if(resultBean.getResultCode()!=null){
	         if(resultBean.getResultCode().equals("88")){
	        	 resultBean.setCodeMsg("绑定成功");
	         }else if(resultBean.getResultCode().equals("89")){
	        	 resultBean.setCodeMsg("提现资金退回");
	         }else if(resultBean.getResultCode().equals("01")){
	        	 resultBean.setCodeMsg("用户乾多多标识错误");
	         }else if(resultBean.getResultCode().equals("02")){
	        	 resultBean.setCodeMsg("平台乾多多标识错误");
	         }else if(resultBean.getResultCode().equals("03")){
	        	 resultBean.setCodeMsg("提现订单号错误");
	         }else if(resultBean.getResultCode().equals("04")){
	        	 resultBean.setCodeMsg("金额错误");
	         }else if(resultBean.getResultCode().equals("05")){
	        	 resultBean.setCodeMsg("卡信息错误");
	         }else if(resultBean.getResultCode().equals("06")){
	        	 resultBean.setCodeMsg("加密验证失败");
	         }else if(resultBean.getResultCode().equals("07")){
	        	 resultBean.setCodeMsg("余额不足");
	         }else if(resultBean.getResultCode().equals("08")){
	        	 resultBean.setCodeMsg("提现失败");
	         }else if(resultBean.getResultCode().equals("09")){
	        	 resultBean.setCodeMsg("支付密码输入错误");
	         }else if(resultBean.getResultCode().equals("10")){
	        	 resultBean.setCodeMsg("短信验证码输入错误");
	         }else if(resultBean.getResultCode().equals("11")){
	        	 resultBean.setCodeMsg("安保问题输入错误");
	         }else if(resultBean.getResultCode().equals("12")){
	        	 resultBean.setCodeMsg("账户正在被别的线程操作");
	         }else if(resultBean.getResultCode().equals("13")){
	        	 resultBean.setCodeMsg("手续费比例错误");
	         }else if(resultBean.getResultCode().equals("14")){
	        	 resultBean.setCodeMsg("平台自有账户余额不足");
	         }
         }
		return resultBean;
	}
	
	
	
	/*
	 *返回注册绑定
	 * @param resultBean
	 * @return
	 */
	private ResultBean setBindCodeMsg(ResultBean resultBean){
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
	
	
	/*
	 *返回绑定
	 * @param resultBean
	 * @return
	 */
	private ResultBean setBindMsg(ResultBean resultBean){
		if(resultBean.getResultCode().equals("88")){
			resultBean.setCodeMsg("绑定成功！");
		}else if(resultBean.getResultCode().equals("01")){
			resultBean.setCodeMsg("用户网贷平台账号错误");
		}else if(resultBean.getResultCode().equals("02")){
			resultBean.setCodeMsg("用户乾多多账号错误");
		}else if(resultBean.getResultCode().equals("03")){
			resultBean.setCodeMsg("平台乾多多标识错误");
		}else if(resultBean.getResultCode().equals("04")){
			resultBean.setCodeMsg("加密验证失败");
		}else if(resultBean.getResultCode().equals("05")){
			resultBean.setCodeMsg("网贷平台账号或乾多多账号已绑定");
		}else if(resultBean.getResultCode().equals("06")){
			resultBean.setCodeMsg("乾多多账号不存在");
		}else if(resultBean.getResultCode().equals("07")){
			resultBean.setCodeMsg("登录密码错误");
		}else if(resultBean.getResultCode().equals("08")){
			resultBean.setCodeMsg("乾多多账号被禁用");
		}else if(resultBean.getResultCode().equals("09")){
			resultBean.setCodeMsg("乾多多账号未激活");
		}else if(resultBean.getResultCode().equals("10")){
			resultBean.setCodeMsg("乾多多账号未实名认证");
		}else if(resultBean.getResultCode().equals("11")){
			resultBean.setCodeMsg("短信验证码输入错误");
		}else if(resultBean.getResultCode().equals("12")){
			resultBean.setCodeMsg("安保问题输入错误");
		}else if(resultBean.getResultCode().equals("13")){
			resultBean.setCodeMsg("手机号不唯一");
		}else if(resultBean.getResultCode().equals("14")){
			resultBean.setCodeMsg("乾多多账号已绑定别的平台");
		}
		return resultBean;
	}
	
}
