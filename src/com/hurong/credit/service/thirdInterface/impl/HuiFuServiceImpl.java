package com.hurong.credit.service.thirdInterface.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import chinapnr.SecureLink;

import com.google.gson.Gson;
import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.credit.action.pay.FontHuiFuAction;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.service.thirdInterface.HuiFuService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;

public class HuiFuServiceImpl implements HuiFuService {
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private PlThirdInterfaceLogService plThirdInterfaceLogService;
	private String URL = "";
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
    // 注册
	@Override
	public String[] register(HttpServletResponse respose,BpCustMember mem, String regType,String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成注册数据
		huiFuVO=generateRegisterData(huiFuVO,mem,basePath); 
		//签名验证
		String  MerData = huiFuVO.getVersion().trim() + huiFuVO.getCmdId().trim() + huiFuVO.getMerCustId().trim() + huiFuVO.getBgRetUrl().trim() + huiFuVO.getRetUrl().trim() + huiFuVO.getUsrId().trim() + huiFuVO.getUsrName().trim() + huiFuVO.getIdType().trim() + huiFuVO.getIdNo().trim() + huiFuVO.getUsrMp().trim() + huiFuVO.getUsrEmail().trim()+ huiFuVO.getMerPriv().trim();
		huiFuVO.setChkValue(Sign(MerData));
		// 生成注册map
		Map<String, String> params = generateRegisterMap(huiFuVO);
		String url="";
		try {
			 url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		  plThirdInterfaceLogService.saveLog(ret[0], ret[1], url,
					"（请求）汇付注册接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					mem.getTruename(), "", "", "");
		return ret;
	}
	//登录
	@Override
	public String[] loginToHuiFu(HttpServletResponse respose, BpCustMember mem,
			String regType, String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成登录数据
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		//签名验证
		String  MerData = huiFuVO.getVersion().trim() + huiFuVO.getCmdId().trim() + huiFuVO.getMerCustId().trim()+huiFuVO.getUsrCustId();
		huiFuVO.setChkValue(Sign(MerData.replaceAll(" ", "")));
		// 生成登录map
		Map<String, String> params = generateLoginMap(huiFuVO);
		try {
			String url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			plThirdInterfaceLogService.saveLog("", "", url,
					"登陆汇付接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					mem.getTruename(), "", "", "");
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
			 
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	//绑定银行卡
	@Override
	public String[] bindBankCard(HttpServletResponse respose, BpCustMember mem,String regType,
			String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成邦卡数据
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		huiFuVO.setBgRetUrl(basePath+"pay/bindCardBkHuiFu.do");// (this.getBasePath()+"pay/backPay.do");
		//签名验证
		String  MerData = huiFuVO.getVersion().trim() + huiFuVO.getCmdId().trim() + huiFuVO.getMerCustId().trim()+huiFuVO.getUsrCustId()+huiFuVO.getBgRetUrl()+huiFuVO.getMerPriv();
		huiFuVO.setChkValue(Sign(MerData));
		// 生成登录map
		Map<String, String> params = generateBindCardMap(huiFuVO);
		String url="";
		try {
			 url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
			 
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		  plThirdInterfaceLogService.saveLog(ret[0], ret[1], url,
					"（请求）汇付绑卡接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					mem.getTruename(), "", "", "");
		return ret;
	}
	
    //充值
	@Override
	public String[] recharge(HttpServletResponse respose,BpCustMember mem,String regType, String ordId,
			String ordDate, String dcFlag, String amt, String bankCode,String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成充值数据
		huiFuVO=generateRechargeParamatsData(huiFuVO,mem,ordId,ordDate,dcFlag,amt,bankCode,basePath);
		//签名验证
		String  MerData = huiFuVO.getVersion().trim() + huiFuVO.getCmdId().trim() + huiFuVO.getMerCustId().trim()+huiFuVO.getUsrCustId()+huiFuVO.getOrdId().trim()+huiFuVO.getOrdDate().trim()+huiFuVO.getGateBusiId().trim()+huiFuVO.getOpenBankId().trim()+huiFuVO.getDcFlag().trim()+huiFuVO.getTransAmt().trim()+huiFuVO.getRetUrl().trim()+huiFuVO.getBgRetUrl().trim()+huiFuVO.getMerPriv().trim(); 
		huiFuVO.setChkValue(Sign(MerData));
		// 生成充值
		Map<String, String> params = generateRechargeMap(huiFuVO);
		String url="";
		try {
			 url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		 plThirdInterfaceLogService.saveLog(ret[0], ret[1], url,
					"（请求）汇付充值接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					mem.getTruename(), "订单号"+ordId, "充值金额"+amt, "");
		return ret;
	
	}
	//余额 查询
	@Override
	public String[] balanceQuery(HttpServletResponse respose,BpCustMember mem, String regType) {
		String[] ret=new String[3];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成查询数据
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		//签名验证
		String  MerData = huiFuVO.getVersion().trim() + huiFuVO.getCmdId().trim() + huiFuVO.getMerCustId().trim()+huiFuVO.getUsrCustId();
		huiFuVO.setChkValue(Sign(MerData));
		// 生成余额查询map
		Map<String, String> params = generateLoginMap(huiFuVO);
		params.put("ChkValue", huiFuVO.getChkValue());
		String outStr="";
		try {
			  String param=UrlUtils.generateParams(params,FontHuiFuAction.UTF8CHARSET);
			  outStr =WebClient.getWebContentByPost(URL, param, FontHuiFuAction.UTF8CHARSET,12000); 
			  plThirdInterfaceLogService.saveLog("", "", outStr,
						"汇付余额查询接口", null, PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
						"", "", "", "");
			  
			  Gson gson=new Gson();
			  FontHuiFuAction huifu=new FontHuiFuAction();
			  huifu=gson.fromJson(outStr, FontHuiFuAction.class);
			  String msgData =  huifu.getCmdId() + huifu.getRespCode() + huifu.getMerCustId() + huifu.getUsrCustId()+ huifu.getAvlBal()+ huifu.getAcctBal()+ huifu.getFrzBal();
		      boolean isSuccess = this.DecodSign(msgData, huifu.getChkValue());
		      if(isSuccess){
		    	  ret[0]=huifu.getAvlBal(); //可用余额
		    	  ret[1]=huifu.getAcctBal(); //账户余额 账户资金余额
		    	  ret[2]=huifu.getFrzBal(); //冻结金额
		      }
		} catch (Exception e) {
			e.printStackTrace();
			  ret[0]="00"; //可用余额
	    	  ret[1]="00"; //账户余额 账户资金余额
	    	  ret[2]="00"; //冻结金额
			
		}
		return ret;
	}
	// 自动/手动投标
	@Override
	public String[] tenderHuiFu(HttpServletResponse respose, BpCustMember mem,
			String regType, String ordId, String ordDate, String transAmt,
			String borrowerDetails, String isFreeze, String freezeOrdId,
			String reqExt, String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成投标数据
		huiFuVO=generateTenderData(huiFuVO,mem, regType,  ordId,  ordDate,  transAmt,
				 borrowerDetails,  isFreeze,  freezeOrdId,
				 reqExt,basePath);
		//签名验证
		String  MerData =  huiFuVO.getVersion()  +huiFuVO.getCmdId()  + huiFuVO.getMerCustId () + huiFuVO.getOrdId()  + huiFuVO.getOrdDate()  +  huiFuVO.getTransAmt()  +  huiFuVO.getUsrCustId()  + 
		huiFuVO.getMaxTenderRate()  +  huiFuVO.getBorrowerDetails () +  huiFuVO.getIsFreeze()+	huiFuVO.getFreezeOrdId()+  huiFuVO.getRetUrl()  +huiFuVO.getBgRetUrl()  + huiFuVO.getMerPriv()+
		huiFuVO.getReqExt() ;
		huiFuVO.setChkValue(Sign(MerData.replaceAll(" ", "")));
		// 生成投标map
		Map<String, String> params = generateTenderMap(huiFuVO);
		String url="";
		try {
			 url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		 plThirdInterfaceLogService.saveLog(ret[0], ret[1], url,
					"（请求）汇付投标接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					mem.getTruename(), "订单号"+ordId, "投标金额"+transAmt, "");
		return ret;
	}
	
	@Override
	public String[] Loans(HttpServletResponse respose, String regType,
			String ordId, String ordDate, String OutCustId, String TransAmt,
			String Fee, String SubOrdId, String SubOrdDate, String InCustId,
			String DivDetails, String FeeObjFlag, String IsDefault,
			String IsUnFreeze, String UnFreezeOrdId, String FreezeTrxId,String reqExt,
			String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成放款数据
		huiFuVO=generateLoansData(huiFuVO, regType,  ordId,  ordDate,OutCustId,  TransAmt,
				Fee,  SubOrdId,  SubOrdDate,
				InCustId,DivDetails,FeeObjFlag,IsDefault,IsUnFreeze,UnFreezeOrdId,FreezeTrxId, reqExt,basePath);
		//签名验证
		String  MerData =  huiFuVO.getVersion ()+huiFuVO.getCmdId ()+ huiFuVO.getMerCustId ()+ huiFuVO.getOrdId ()+ huiFuVO.getOrdDate ()+ huiFuVO.getOutCustId()+ huiFuVO.getTransAmt()+ huiFuVO.getFee()+ 
		huiFuVO.getSubOrdId()+ huiFuVO.getSubOrdDate()+ huiFuVO.getInCustId()+ huiFuVO.getDivDetails()+ huiFuVO.getFeeObjFlag()+ huiFuVO.getIsDefault ()+ huiFuVO.getIsUnFreeze()+ 
		huiFuVO.getUnFreezeOrdId()+huiFuVO.getFreezeTrxId() + huiFuVO.getBgRetUrl()+ huiFuVO.getMerPriv()+ huiFuVO.getReqExt();
		huiFuVO.setChkValue(Sign(MerData.replaceAll(" ", "")));
		// 生成投标map
		Map<String, String> params = generateLoansMap(huiFuVO);
		String param="";
		try {
			/*String url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);*/
		   param=UrlUtils.generateParams(params,FontHuiFuAction.GBKCHARSET); 
			 String  outStr0 =WebClient.getWebContentByPost(URL, param, FontHuiFuAction.GBKCHARSET,Integer.valueOf(12000));
			  System.out.println(outStr0);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		 plThirdInterfaceLogService.saveLog(ret[0], ret[1], param,
					"（请求）汇付放款接口", null, PlThirdInterfaceLog.MEMTYPE1,
					PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
					"系统", "订单号"+ordId, "放款金额"+TransAmt, "");
		return ret;
	}
	//还款接口
	@Override
	public String[] Repayment(HttpServletResponse respose, String regType,
			String ordId, String ordDate, String OutCustId, String TransAmt,
			String Fee, String SubOrdId, String SubOrdDate, String InCustId,
			String DivDetails, String FeeObjFlag, String OutAcctId,
			String InAcctId, String reqExt, String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成放款数据
		huiFuVO=generateRepaymentData(huiFuVO, regType,  ordId,  ordDate,OutCustId,  TransAmt,
				Fee,  SubOrdId,  SubOrdDate,
				InCustId,DivDetails,FeeObjFlag,OutAcctId,InAcctId, reqExt,basePath);
		//签名验证
		String  MerData =  huiFuVO.getVersion ()+huiFuVO.getCmdId()+huiFuVO.getMerCustId()+huiFuVO.getOrdId()+huiFuVO.getOrdDate()+huiFuVO.getOutCustId()+huiFuVO.getSubOrdId()+huiFuVO.getSubOrdDate()+huiFuVO.getOutAcctId()+huiFuVO.getTransAmt()+huiFuVO.getFee()+huiFuVO.getInCustId()+huiFuVO.getInAcctId()+huiFuVO.getDivDetails()+huiFuVO.getFeeObjFlag()+huiFuVO.getBgRetUrl()+huiFuVO.getMerPriv ()+huiFuVO.getReqExt();
		huiFuVO.setChkValue(Sign(MerData.replaceAll(" ", "")));
		// 生成投标map
		Map<String, String> params = generateRepaymentMap(huiFuVO);
		 String param="";
		try {
			/*String url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);*/
		   param=UrlUtils.generateParams(params,FontHuiFuAction.GBKCHARSET); 
			 String  outStr0 =WebClient.getWebContentByPost(URL, param, FontHuiFuAction.GBKCHARSET,Integer.valueOf(12000));
			  System.out.println(outStr0);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		plThirdInterfaceLogService.saveLog(ret[0], ret[1], param,
				"（请求）汇付还款接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				"系统", "订单号"+ordId, "放款金额"+TransAmt, "");
		return ret;
	}
	//取现接口
	@Override
	public String[] webWithdraw(HttpServletResponse respose,String regType, String OrdId,
			String UsrCustId, String TransAmt, String ServFee,
			String ServFeeAcctId, String OpenAcctId, String Remark,
			String ReqExt, String charSet, String timeOut,String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成取现数据
		huiFuVO=generateWebWithdrawData(huiFuVO, regType,   OrdId,
				 UsrCustId,  TransAmt,  ServFee,
				 ServFeeAcctId,  OpenAcctId,  Remark,
				 ReqExt,basePath,charSet);
		//签名验证
		String  MerData = huiFuVO.getVersion()+huiFuVO.getCmdId() +huiFuVO.getMerCustId()+ huiFuVO.getOrdId()+huiFuVO.getUsrCustId()+huiFuVO.getTransAmt()+huiFuVO.getServFee()+ 
		huiFuVO.getServFeeAcctId() +huiFuVO.getOpenAcctId() + huiFuVO.getRetUrl() +huiFuVO.getBgRetUrl()+ huiFuVO.getRemark()+ huiFuVO.getMerPriv() + huiFuVO.getReqExt(); 
		
		huiFuVO.setChkValue(Sign(MerData.replaceAll(" ", "")));
		// 生成投标map
		Map<String, String> params = generateWebWithdrawMap(huiFuVO);
		String url="";
		try {
			 url = UrlUtils.generateUrl(params, URL,charSet);
			System.out.println("url================================"+url);
			WebClient.SendByUrl(respose, url,charSet);
		 
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		plThirdInterfaceLogService.saveLog(ret[0], ret[1], url,
				"（请求）汇付取现接口", null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE1, PlThirdInterfaceLog.TYPENAME1,
				"UsrCustId"+UsrCustId, "订单号"+OrdId, "取现金额："+TransAmt+"手续费："+ServFee, "");
		return ret;
	}
	//生成 取现map
	private Map<String, String> generateWebWithdrawMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("Version", FontHuiFuAction.Version20);
		params.put("OrdId", huiFuVO.getOrdId());
		params.put("UsrCustId", huiFuVO.getUsrCustId());
		params.put("TransAmt", huiFuVO.getTransAmt());
		
		params.put("ServFee", huiFuVO.getServFee());
		params.put("ServFeeAcctId", huiFuVO.getServFeeAcctId());
		params.put("OpenAcctId", huiFuVO.getOpenAcctId());
		
		
		params.put("RetUrl", huiFuVO.getRetUrl());
		params.put("BgRetUrl", huiFuVO.getBgRetUrl());
		
		
		params.put("Remark", huiFuVO.getRemark());
		
		params.put("CharSet", huiFuVO.getCharSet());
		params.put("ReqExt", huiFuVO.getReqExt());
	    
		params.put("MerPriv", huiFuVO.getMerPriv());
		params.put("ChkValue", huiFuVO.getChkValue());
		return params;
	}
	//生成取现参数
	private FontHuiFuAction generateWebWithdrawData(FontHuiFuAction huiFuVO,
			String regType, String ordId, String usrCustId, String transAmt,
			String servFee, String servFeeAcctId, String openAcctId,
			String remark, String reqExt, String basePath ,String charSet) {

		huiFuVO.setVersion(FontHuiFuAction.Version20);
		huiFuVO.setCmdId(regType);
		huiFuVO.setOrdId(ordId);
		huiFuVO.setUsrCustId(usrCustId);
		huiFuVO.setTransAmt(transAmt);
		huiFuVO.setServFee(servFee);
		huiFuVO.setServFeeAcctId(servFeeAcctId);
		huiFuVO.setOpenAcctId(openAcctId);
		huiFuVO.setRemark(remark);
		huiFuVO.setReqExt(reqExt);
		huiFuVO.setCharSet(charSet);
		huiFuVO.setRetUrl(basePath+"pay/WebWithdrawFontHuiFu.do");
		huiFuVO.setBgRetUrl(basePath+"pay/WebWithdrawBkHuiFu.do");
		return huiFuVO;
	}
	private Map<String, String> generateRepaymentMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("OrdId", huiFuVO.getOrdId());
		params.put("OrdDate", huiFuVO.getOrdDate());
		params.put("OutCustId", huiFuVO.getOutCustId());
		params.put("Fee", huiFuVO.getFee());
		params.put("SubOrdId", huiFuVO.getSubOrdId());
		params.put("SubOrdDate", huiFuVO.getSubOrdDate());
		params.put("InCustId", huiFuVO.getInCustId());
		params.put("FeeObjFlag", huiFuVO.getFeeObjFlag());
		params.put("OutAcctId", huiFuVO.getOutAcctId());
		params.put("InAcctId", huiFuVO.getInAcctId());
		params.put("ReqExt", huiFuVO.getReqExt());
	    params.put("DivDetails", huiFuVO.getDivDetails());
	    params.put("TransAmt", huiFuVO.getTransAmt());
		params.put("BgRetUrl", huiFuVO.getBgRetUrl());
		params.put("MerPriv", huiFuVO.getMerPriv());
		params.put("ChkValue", huiFuVO.getChkValue());
		return params;
	}
	/**
	 * 生成还款数据
	 * @param huiFuVO
	 * @param regType
	 * @param ordId
	 * @param ordDate
	 * @param outCustId
	 * @param transAmt
	 * @param fee
	 * @param subOrdId
	 * @param subOrdDate
	 * @param inCustId
	 * @param divDetails
	 * @param feeObjFlag
	 * @param outAcctId
	 * @param inAcctId
	 * @param reqExt
	 * @param basePath
	 * @return
	 */
	private FontHuiFuAction generateRepaymentData(FontHuiFuAction huiFuVO,
			String regType, String ordId, String ordDate, String outCustId,
			String transAmt, String fee, String subOrdId, String subOrdDate,
			String inCustId, String divDetails, String feeObjFlag,
			String outAcctId, String inAcctId, String reqExt, String basePath) {

		huiFuVO.setVersion(FontHuiFuAction.Version20);
		huiFuVO.setCmdId(regType);
		huiFuVO.setOrdId(ordId);
		huiFuVO.setOrdDate(ordDate);
		huiFuVO.setOutCustId(outCustId);
		huiFuVO.setTransAmt(transAmt);
		huiFuVO.setFee(fee);
		huiFuVO.setSubOrdId(subOrdId);
		huiFuVO.setSubOrdDate(subOrdDate);
		huiFuVO.setInCustId(inCustId);
		huiFuVO.setDivDetails(divDetails);
		huiFuVO.setFeeObjFlag(feeObjFlag);
		huiFuVO.setOutAcctId(outAcctId);
		huiFuVO.setInAcctId(inAcctId);
		huiFuVO.setReqExt(reqExt);
		huiFuVO.setBgRetUrl(basePath+"pay/repaymentBkHuiFu.do");
		return huiFuVO;
	}
	//生成放款 参数
	private Map<String, String> generateLoansMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("OrdId", huiFuVO.getOrdId());
		params.put("OrdDate", huiFuVO.getOrdDate());
		params.put("OutCustId", huiFuVO.getOutCustId());
		params.put("Fee", huiFuVO.getFee());
		params.put("SubOrdId", huiFuVO.getSubOrdId());
		params.put("SubOrdDate", huiFuVO.getSubOrdDate());
		params.put("InCustId", huiFuVO.getInCustId());
		params.put("FeeObjFlag", huiFuVO.getFeeObjFlag());
		params.put("IsDefault", huiFuVO.getIsDefault());
		params.put("IsUnFreeze", huiFuVO.getIsUnFreeze());
		params.put("UnFreezeOrdId", huiFuVO.getUnFreezeOrdId());
		params.put("FreezeTrxId", huiFuVO.getFreezeTrxId());
		params.put("ReqExt", huiFuVO.getReqExt());
	    params.put("DivDetails", huiFuVO.getDivDetails());
	    params.put("TransAmt", huiFuVO.getTransAmt());
		params.put("BgRetUrl", huiFuVO.getBgRetUrl());
		params.put("MerPriv", huiFuVO.getMerPriv());
		params.put("ChkValue", huiFuVO.getChkValue());
		return params;
	}
	//生成放款数据
	private FontHuiFuAction generateLoansData(FontHuiFuAction huiFuVO,
			String regType, String ordId, String ordDate, String outCustId,
			String transAmt, String fee, String subOrdId, String subOrdDate,
			String inCustId, String divDetails, String feeObjFlag,
			String isDefault, String isUnFreeze, String unFreezeOrdId,
			String freezeTrxId,String reqExt, String basePath) {
		
		huiFuVO.setVersion(FontHuiFuAction.Version20);
		huiFuVO.setCmdId(regType);
		huiFuVO.setOrdId(ordId);
		huiFuVO.setOrdDate(ordDate);
		huiFuVO.setOutCustId(outCustId);
		huiFuVO.setTransAmt(transAmt);
		huiFuVO.setFee(fee);
		huiFuVO.setSubOrdId(subOrdId);
		huiFuVO.setSubOrdDate(subOrdDate);
		huiFuVO.setInCustId(inCustId);
		huiFuVO.setDivDetails(divDetails);
		huiFuVO.setFeeObjFlag(feeObjFlag);
		huiFuVO.setIsDefault(isDefault);
		huiFuVO.setIsUnFreeze(isUnFreeze);
		huiFuVO.setUnFreezeOrdId(unFreezeOrdId);
		huiFuVO.setFreezeTrxId(freezeTrxId);
		huiFuVO.setReqExt(reqExt);
		huiFuVO.setBgRetUrl(basePath+"pay/loansBkHuiFu.do");
		return huiFuVO;
	}
	@Override
	public String[] unFreeze(HttpServletResponse respose, BpCustMember mem,
			String regType, String ordId, String ordDate, String trxid,String basePath) {
		String[] ret=new String[2];
		FontHuiFuAction huiFuVO = new FontHuiFuAction();
		// 生成公共参数
		huiFuVO = generatePublicData(huiFuVO,regType);
		//生成解冻数据
		huiFuVO=generateUnFreezeData(huiFuVO,mem, regType,  ordId,  ordDate,  trxid,basePath);
		//签名验证
		String  MerData =   huiFuVO.getCmdId() +huiFuVO.getRespCode() + huiFuVO.getMerCustId() + huiFuVO.getOrdId ()+ huiFuVO.getOrdDate() + huiFuVO.getTrxId() + huiFuVO.getRetUrl()+ huiFuVO.getBgRetUrl()+ huiFuVO.getMerPriv();
		huiFuVO.setChkValue(Sign(MerData));
		// 生成投标map
		Map<String, String> params = generateUnFreezeMap(huiFuVO);
		try {
			String url = UrlUtils.generateUrl(params, URL, FontHuiFuAction.GBKCHARSET);
			WebClient.SendByUrl(respose, url,FontHuiFuAction.GBKCHARSET);
			  ret[0]=Constants.CODE_SUCCESS;
			  ret[1]="对接成功";
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="对接失败"+e.getMessage();
		}
		return ret;
	}
	@Override
	public String withdraw(HttpServletResponse respose,
			String recvBankAcctName, String recvBankAcctNum,
			String recvBankBranchName, String recvBankCity,
			String recvBankName, String recvBankProvince, String tranAmt,
			String description, String charSet, String timeOut) {
		return "";
		
	}

	/**
	 * 生成充值提交参数Map
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateRechargeMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("UsrCustId", huiFuVO.getUsrCustId());
		params.put("OrdId", huiFuVO.getOrdId());
		params.put("OrdDate", huiFuVO.getOrdDate());
		params.put("GateBusiId", huiFuVO.getGateBusiId());
		params.put("OpenBankId", huiFuVO.getOpenBankId());
		params.put("DcFlag", huiFuVO.getDcFlag());
		params.put("TransAmt", huiFuVO.getTransAmt());
		params.put("RetUrl", huiFuVO.getRetUrl());
		params.put("BgRetUrl", huiFuVO.getBgRetUrl());
		params.put("MerPriv", huiFuVO.getMerPriv());
		params.put("ChkValue", huiFuVO.getChkValue());
		return params;
	}

	
    /**
     * 生成充值数据
     * @param huiFuVO
     * @param ordId
     * @param ordDate
     * @param dcFlag
     * @param amt
     * @return
     */
	private FontHuiFuAction generateRechargeParamatsData(FontHuiFuAction huiFuVO,BpCustMember mem,String ordId ,String ordDate,String dcFlag, String amt,String bankCode,String basePath) {
		huiFuVO.setOrdId(ordId);
		huiFuVO.setGateBusiId("B2C");
		huiFuVO.setOpenBankId(bankCode);
		huiFuVO.setOrdDate(ordDate);
		huiFuVO.setDcFlag(dcFlag);
		huiFuVO.setTransAmt(amt);
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		huiFuVO.setRetUrl(basePath+"pay/rechargeFontHuiFu.do");// (AppUtil.get+"pay/backPay.do");
		huiFuVO.setBgRetUrl(basePath+"pay/rechargeBkHuiFu.do");// (this.getBasePath()+"pay/backPay.do");
		return huiFuVO;
	}
	
	/**
	 * 生成注册提交参数
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateRegisterMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("UsrId",huiFuVO.getUsrId());
		params.put("UsrName",huiFuVO.getUsrName());
		params.put("IdType",huiFuVO.getIdType());
		params.put("IdNo",huiFuVO.getIdNo());
		params.put("RetUrl",huiFuVO.getRetUrl());
		params.put("BgRetUrl",huiFuVO.getBgRetUrl());
		params.put("MerPriv",huiFuVO.getMerPriv());
		params.put("UsrMp",huiFuVO.getUsrMp());
		params.put("UsrEmail",huiFuVO.getUsrEmail());
		params.put("CharSet",huiFuVO.getCharSet());
		params.put("ChkValue",huiFuVO.getChkValue());
		return params;
	}
	
	/**
	 * 生成登录提交参数 /和 余额查询公用
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateLoginMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("UsrCustId",huiFuVO.getUsrCustId());
		return params;
	}
	/**
	 * 生成邦卡提交参数
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateBindCardMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params=generatePublicMap(huiFuVO);
		params.put("UsrCustId",huiFuVO.getUsrCustId());
		params.put("BgRetUrl",huiFuVO.getBgRetUrl());
		params.put("MerPriv",huiFuVO.getMerPriv());
		params.put("ChkValue",huiFuVO.getChkValue());
		return params;
	}
	/**
	 * 生成公共提交参数
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generatePublicMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version",huiFuVO.getVersion());
		params.put("CmdId",huiFuVO.getCmdId());
		params.put("MerCustId",huiFuVO.getMerCustId());
		return params;
	}
	
	/**
	 * 数据加密签名验证
	 * @param huiFuVO
	 * @param basePath
	 * @return
	 */
	private String Sign(String MerData){
		 //签名
		String MerKeyFile=AppUtil.getAppAbsolutePath()+"WEB-INF/template/"+AppUtil.getProjStr()+"/"+configMap.get("keyPath").toString()+"MerPrK.key";
        SecureLink sl=new SecureLink();
        int ret=sl.SignMsg(configMap.get("signpass").toString(),MerKeyFile,MerData);
       // AppUtil.getSignpass()
        String  ChkValue="";
        if (ret != 0) 
        {
        	System.out.println("ret=" + ret );
        	ChkValue= "";
        }
        ChkValue= sl.getChkValue();
        return ChkValue;
	}
	
	/**
	 * 数据解密签名验证
	 * @param msgData 被签名的数据
	 * @param chkValue 要验证的签名
	 * @return true 表示验证成 false 失败
	 */
	@Override
	public boolean DecodSign(String msgData,String chkValue){
		 //签名
		 String keyFile=AppUtil.getAppAbsolutePath()+"WEB-INF/template/"+AppUtil.getProjStr()+"/"+configMap.get("keyPath").toString()+"PgPubk.key";
        SecureLink sl=new SecureLink();
        boolean isSuccess=false;
        int ret=sl.VeriSignMsg(keyFile,msgData,chkValue);
        if (ret != 0) 
        {
        	System.out.println("ret=" + ret );
        	isSuccess=false;
        }else{
        	isSuccess=true;
        }
        return isSuccess;
	}

	/**
	 * 生成公共 数据
	 * @param FontHuiFuAction
	 * @return
	 */
	private FontHuiFuAction generatePublicData(FontHuiFuAction huiFuVO,String regType) {

		// 获取系统配置信息
		// URL
		//URL = sysConfigService.findByKey("huiFuURL").getDataValue();
		// 版本号
		//String huiFuVer = sysConfigService.findByKey("huiFuVer").getDataValue();
		// 商户账号
		//String huiFuNumber = sysConfigService.findByKey("huiFuNumber").getDataValue();
		// URL
		URL = configMap.get("URL").toString();
		// 版本号
		String huiFuVer = configMap.get("Ver").toString();
		// 商户账号
		String huiFuNumber = configMap.get("number").toString();
		
		huiFuVO.setVersion(huiFuVer);
		huiFuVO.setCharSet(FontHuiFuAction.GBKCHARSET);
		huiFuVO.setCmdId(regType);
		huiFuVO.setMerPriv("");
		huiFuVO.setMerCustId(huiFuNumber);
		return huiFuVO;
	}

	/**
	 * 生成注册 数据
	 * @param FontHuiFuAction
	 * @return
	 */
	private FontHuiFuAction generateRegisterData(FontHuiFuAction huiFuVO,BpCustMember mem,String basePath) {
		huiFuVO.setUsrEmail(mem.getEmail().trim());
		huiFuVO.setUsrId(mem.getLoginname().trim());
		huiFuVO.setUsrMp(mem.getTelphone().trim());
		huiFuVO.setUsrName(mem.getTruename().trim());
		huiFuVO.setIdNo(mem.getCardcode());
		huiFuVO.setIdType("00");//00 必须为身份证  编码 为 00
		huiFuVO.setRetUrl(basePath+"pay/registerFontHuiFu.do");// (AppUtil.get+"pay/backPay.do");
		huiFuVO.setBgRetUrl(basePath+"pay/registerBkHuiFu.do");// (this.getBasePath()+"pay/backPay.do");
		return huiFuVO;
	}
	
	/**
	 * 生成投标参数
	 * @param FontHuiFuAction
	 * @return
	 */
	private FontHuiFuAction generateTenderData(FontHuiFuAction huiFuVO,BpCustMember mem,String cmdId,String ordId,String ordDate,String transAmt,String borrowerDetails,String isFreeze,String freezeOrdId,String reqExt,String basePath) {
		huiFuVO.setVersion(FontHuiFuAction.Version20); //投标参数 这里 单独设置
		huiFuVO.setCmdId(cmdId);
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		huiFuVO.setOrdId(ordId);
		huiFuVO.setOrdDate(ordDate);
		huiFuVO.setTransAmt(transAmt);
		huiFuVO.setBorrowerDetails(borrowerDetails);
		huiFuVO.setIsFreeze(isFreeze);
		huiFuVO.setFreezeOrdId(freezeOrdId);
		huiFuVO.setReqExt(reqExt);
		huiFuVO.setMaxTenderRate(FontHuiFuAction.MaxTenderRate_0);
		huiFuVO.setRetUrl(basePath+"pay/tenderFontHuiFu.do");// (AppUtil.get+"pay/backPay.do");
		huiFuVO.setBgRetUrl(basePath+"pay/tenderBkHuiFu.do");// (this.getBasePath()+"pay/backPay.do");
		return huiFuVO;
	}
	
	
	/**
	 * 生成解冻参数
	 * @param FontHuiFuAction
	 * @return
	 */
	private FontHuiFuAction generateUnFreezeData(FontHuiFuAction huiFuVO,BpCustMember mem,String cmdId,String ordId,String ordDate,String trxId,String basePath) {
		huiFuVO.setCmdId(cmdId);
		huiFuVO.setUsrCustId(mem.getThirdPayFlagId());
		huiFuVO.setOrdId(ordId);
		huiFuVO.setOrdDate(ordDate);
		huiFuVO.setTrxId(trxId);
		huiFuVO.setRetUrl(basePath+"pay/unFreezeFontHuiFu.do");// (AppUtil.get+"pay/backPay.do");
		huiFuVO.setBgRetUrl(basePath+"pay/unFreezeBkHuiFu.do");// (this.getBasePath()+"pay/backPay.do");
		return huiFuVO;
	}
	
	
	/**
	 * 生成投标提交参数
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateTenderMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Version",huiFuVO.getVersion());

		params.put("OrdId",huiFuVO.getOrdId());

		params.put("UsrCustId",huiFuVO.getUsrCustId()); 

		params.put("IsFreeze",huiFuVO.getIsFreeze());

		params.put("BgRetUrl",huiFuVO.getBgRetUrl()); 

		params.put("ChkValue",huiFuVO.getChkValue()); 

		params.put("CmdId",huiFuVO.getCmdId()); 

		params.put("OrdDate",huiFuVO.getOrdDate()); 

		params.put("MaxTenderRate",huiFuVO.getMaxTenderRate()); 

		params.put("FreezeOrdId",huiFuVO.getFreezeOrdId());

		params.put("MerPriv",huiFuVO.getMerPriv()); 
		
		params.put("MerCustId",huiFuVO.getMerCustId());   

		params.put("TransAmt",huiFuVO.getTransAmt()); 

		params.put("BorrowerDetails",huiFuVO.getBorrowerDetails()); 
		params.put("RetUrl",huiFuVO.getRetUrl());
		params.put("ReqExt",huiFuVO.getReqExt()); 
		
		return params;
	}
	
	/**
	 * 生成解冻提交参数
	 * @param huiFuVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateUnFreezeMap(FontHuiFuAction huiFuVO) {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Version",huiFuVO.getVersion());

		params.put("OrdId",huiFuVO.getOrdId());

		params.put("BgRetUrl",huiFuVO.getBgRetUrl()); 

		params.put("ChkValue",huiFuVO.getChkValue()); 

		params.put("CmdId",huiFuVO.getCmdId()); 

		params.put("OrdDate",huiFuVO.getOrdDate()); 

		params.put("MerPriv",huiFuVO.getMerPriv()); 
		
		params.put("MerCustId",huiFuVO.getMerCustId());   

		params.put("TrxId",huiFuVO.getTrxId()); 

		params.put("RetUrl",huiFuVO.getRetUrl());
		
		return params;
	}
	
}
