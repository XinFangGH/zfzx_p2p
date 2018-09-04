package com.hurong.credit.service.thirdInterface.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.thirdInterface.BaoFoo;
import com.hurong.credit.model.thirdInterface.YeePay;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.thirdInterface.BaoFooService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;
import com.hurong.credit.util.YeePayConfig.SignUtil;




public class BaoFooServiceImpl implements BaoFooService{
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private BpCustMemberService bpCustMemberService;

	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	@Override
	public String[] recharge(HttpServletResponse response, BpCustMember mem,
			String amount, String basePath, HttpServletRequest request,String bankId,
			String orderNum) {
		String [] ret=new String[2];
		// TODO Auto-generated method stub
		    BaoFoo baofoo=new BaoFoo();
		    baofoo=generatePublicData(baofoo,basePath);
		    baofoo=rechargeDate(baofoo,orderNum,amount,basePath,bankId);
		    Map<String, String> params=getReqSign(baofoo);
			System.out.println(params);
			String outStr="";
			if(configMap.get("thirdPay_baoFoo_URL")!=null){
				//宝付支付调用地址
				String baofoourl=configMap.get("thirdPay_baoFoo_URL").toString().trim();
				try{
					 String url = UrlUtils.generateUrl(params, baofoourl, BaoFoo.CHARSETUTF8);
					 System.out.println("URL==="+url);
					 WebClient.SendByUrl(response, url,YeePay.CHARSETUTF8);
					 ret[0]=Constants.CODE_SUCCESS;
					 ret[1]="接口对接成功";
					
				}catch(Exception e){
					e.printStackTrace();
					ret[0]=Constants.CODE_FAILED;
					ret[1]="对接失败"+e.getMessage();
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="宝付支付配置信息不齐全，请联系系统管理员进行配置";
			}
		return null;
	}
	
	
	/**2014-07-15
	 * 充值需要的属性方法
	 * @param yeepay
	 * @param bp
	 * @param request
	 * @param webBankcard
	 * @return
	 */
	public BaoFoo rechargeDate(BaoFoo baoFoo,String orderNum,String amount,String basePath,String bankId){
	  String nowDate =getNowDateTime("yyyyMMddHHmmss");
	  String orderMoney =(new BigDecimal(amount)).multiply(new BigDecimal(100)).setScale(0).toString();
		if(bankId!=null&&!"".equals(bankId)){
			  baoFoo.setPayID(bankId);
		}else{
			  baoFoo.setPayID("");
		}
		baoFoo.setTradeDate(nowDate);
		baoFoo.setTransID(orderNum);
		baoFoo.setOrderMoney(orderMoney);
		return baoFoo;
	}
	/**2014-07-15
	 * 得到reg与sign
	 * @param yeepay
	 * @return
	 */
	public Map<String, String> getReqSign(BaoFoo baoFoo){
		Map<String, String> params = new HashMap<String, String>();
		params.put("MemberID", baoFoo.getMemberID());
		params.put("TerminalID", baoFoo.getTerminalID());
		params.put("InterfaceVersion", baoFoo.getInterfaceVersion());
		params.put("KeyType", baoFoo.getKeyType());
		params.put("TradeDate", baoFoo.getTradeDate());
		params.put("TransID", baoFoo.getTransID());
		params.put("OrderMoney", baoFoo.getOrderMoney());
		params.put("PageUrl", baoFoo.getPageUrl());
		params.put("ReturnUrl", baoFoo.getReturnUrl());
		params.put("NoticeType", baoFoo.getNoticeType());
		if(baoFoo.getPayID()!=null&&!"".equals(baoFoo.getPayID())){
			params.put("PayID", baoFoo.getPayID());
		}else{
			params.put("PayID", "");
		}
		params.put("ProductName", "");
		params.put("Amount", "1");
		params.put("Username", "");
		params.put("AdditionalInfo", "");
		String signature =baoFoo.getMemberID()+"|"+baoFoo.getPayID()+"|"+baoFoo.getTradeDate()+"|"+
		                  baoFoo.getTransID()+"|"+baoFoo.getOrderMoney()+"|"+baoFoo.getPageUrl()+"|"+
		                  baoFoo.getReturnUrl()+"|"+baoFoo.getNoticeType()+"|"+configMap.get("thirdPay_baoFoo_password").toString().trim();
		System.out.println("signature==="+signature);
		String sign=md5Sign(signature, "utf-8");
		System.out.println("Sign==="+sign);
		params.put("Signature", sign);
		return params;
	}
	
	/**2014-07-14
	 * 易宝公共数据获取方法 商户编号
	 * @param yeepay
	 * @return
	 */
	public BaoFoo generatePublicData(BaoFoo baoFoo,String basePath){
		baoFoo.setMemberID(configMap.get("thirdPay_baoFoo_MemberID").toString().trim());
		baoFoo.setTerminalID(configMap.get("thirdPay_baoFoo_TerminalID").toString().trim());
		baoFoo.setInterfaceVersion("4.0");
		baoFoo.setKeyType("1");
		baoFoo.setNoticeType("1");
		baoFoo.setReturnUrl(basePath+"baoFoo/rechargeBaoFooBack.do");
		baoFoo.setPageUrl(basePath+"baoFoo/rechargeBaoFooFront.do");
		return baoFoo;
	}
	
	private String md5Sign(String singnature,String encode){
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(singnature.getBytes(encode));
			byte[] digest = md5.digest();
	
			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				// byteVar &
				// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
				// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
				// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
				strTemp = Integer.toHexString((digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}

	}
	
	
	/**
	 * @function 自定义 日期格式
	 * @param dateFormat
	 * @return
	 */
	public final static String getNowDateTime(String dateFormat){
		Date nowDate = new Date(); 
		
		SimpleDateFormat sf = null;
		try {
			sf = new SimpleDateFormat(dateFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		return sf.format(nowDate);
	}


	@Override
	public String[] getBaoFooReturnParameter(HttpServletRequest request) {
		String Result =request.getParameter("Result");
		System.out.println("result==="+Result);
		String MemberID =request.getParameter("MemberID");
		System.out.println("MemberID==="+MemberID);
		String TerminalID =request.getParameter("TerminalID");
		System.out.println("TerminalID==="+TerminalID);
		String TransID =request.getParameter("TransID");
		System.out.println("TransID==="+TransID);
		String ResultDesc =request.getParameter("ResultDesc");
		System.out.println("ResultDesc==="+ResultDesc);
		String FactMoney =request.getParameter("FactMoney");
		System.out.println("FactMoney==="+FactMoney);
		String AdditionalInfo =request.getParameter("AdditionalInfo");
		System.out.println("AdditionalInfo==="+AdditionalInfo);
		String SuccTime =request.getParameter("SuccTime");
		System.out.println("SuccTime==="+SuccTime);
		String Md5Sign =request.getParameter("Md5Sign");
		System.out.println("Md5Sign==="+Md5Sign);
		String signature ="MemberID="+ MemberID +"~|~TerminalID="+ TerminalID  +"~|~TransID="+ TransID +"~|~Result="+ Result+"~|~ResultDesc="+ ResultDesc +"~|~FactMoney="+ FactMoney  +"~|~AdditionalInfo="+ AdditionalInfo +"~|~SuccTime="+ SuccTime +"~|~Md5Sign="+configMap.get("thirdPay_baoFoo_password").toString().trim();
		String sign =md5Sign(signature,"utf-8");
		String [] ret =new String[2];
		if(Md5Sign.equals(sign)){
			ret[0]=Constants.CODE_SUCCESS;
			ret[1]="支付成功";
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="签名验证失败";
		}
		return ret;
	}


	@Override
	public String[] postEasyPayReturnParmater(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String Result =request.getParameter("Result");
		System.out.println("result==="+Result);
		String MemberID =request.getParameter("MemberID");
		System.out.println("MemberID==="+MemberID);
		String TerminalID =request.getParameter("TerminalID");
		System.out.println("TerminalID==="+TerminalID);
		String TransID =request.getParameter("TransID");
		System.out.println("TransID==="+TransID);
		String ResultDesc =request.getParameter("ResultDesc");
		System.out.println("ResultDesc==="+ResultDesc);
		String FactMoney =request.getParameter("FactMoney");
		System.out.println("FactMoney==="+FactMoney);
		String AdditionalInfo =request.getParameter("AdditionalInfo");
		System.out.println("AdditionalInfo==="+AdditionalInfo);
		String SuccTime =request.getParameter("SuccTime");
		System.out.println("SuccTime==="+SuccTime);
		String Md5Sign =request.getParameter("Md5Sign");
		System.out.println("Md5Sign==="+Md5Sign);
		String signature ="MemberID="+ MemberID +"~|~TerminalID="+ TerminalID  +"~|~TransID="+ TransID +"~|~Result="+ Result+"~|~ResultDesc="+ ResultDesc +"~|~FactMoney="+ FactMoney  +"~|~AdditionalInfo="+ AdditionalInfo +"~|~SuccTime="+ SuccTime +"~|~Md5Sign="+configMap.get("thirdPay_baoFoo_password").toString().trim();
		String sign =md5Sign(signature,"utf-8");
		String [] ret =new String[2];
		if(Md5Sign.equals(sign)){
			ret[0]=Constants.CODE_SUCCESS;
			ret[1]="支付成功";
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="签名验证失败";
		}
		if(ret[0].equals(Constants.CODE_SUCCESS)){
			String totalFee=(new BigDecimal(FactMoney).divide(new BigDecimal(100))).toString();
			ObAccountDealInfo ob =obAccountDealInfoService.getByOrderNumber(TransID,totalFee,ObAccountDealInfo.T_RECHARGE.toString(),ObSystemAccount.type0.toString());
			if(ob!=null){
				BpCustMember bp =bpCustMemberService.get(ob.getInvestPersonId());
				/**
				 * 修改 交易记录到erp
				 * @param mem 投资人
				 * @param amount 金额
				 * @param orderNum 订单号
				 * type  交易类型 1表示充值，2表示取现,3收益，4投资，5还本
			       @state 2成功 3 失败
				 */
				Map<String,Object> map=new HashMap<String,Object>();
				 map.put("investPersonId",bp.getId());//投资人Id
				 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 map.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				 map.put("money",new BigDecimal(totalFee));//交易金额
				 map.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 map.put("DealInfoId",null);//交易记录id，没有默认为null
				 map.put("recordNumber",TransID);//交易流水号
				 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 String rett[]=obAccountDealInfoService.updateAcountInfo(map);
				//String rett[] =obAccountDealInfoService.updateAcountInfo(bp.getId(), ObAccountDealInfo.T_RECHARGE.toString(), totalFee, ObSystemAccount.type0.toString(), TransID, null, ObAccountDealInfo.DEAL_STATUS_2.toString());
				//String rett[] = payCommonService.updateDealToERP(bp, totalFee,TransID,ObAccountDealInfo.T_RECHARGE.toString(),"2");
				System.out.println(rett[0]+"======"+rett[1]+"type==="+ObAccountDealInfo.T_RECHARGE.toString()+"state=="+"2");
			}
		}
		return ret;
	}	
		
}