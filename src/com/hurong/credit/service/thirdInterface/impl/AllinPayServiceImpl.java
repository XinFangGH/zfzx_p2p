package com.hurong.credit.service.thirdInterface.impl;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.aipg.common.AipgReq;
import com.aipg.common.AipgRsp;
import com.aipg.common.InfoReq;
import com.aipg.common.XSUtil;
import com.aipg.common.XStreamEx;
import com.aipg.payreq.Body;
import com.aipg.payreq.Trans_Detail;
import com.aipg.payreq.Trans_Sum;
import com.aipg.rtreq.Trans;
import com.aipg.rtrsp.TransRet;
import com.aipg.signquery.NSignReq;
import com.aipg.signquery.QSignDetail;
import com.aipg.singleacctvalid.ValidRet;
import com.aipg.transquery.QTDetail;
import com.aipg.transquery.QTransRsp;
import com.aipg.transquery.TransQueryReq;

import com.aipg.payreq.Trans_Detail;
import com.aipg.queryreq.Query_Trans;
import com.allinpay.XmlTools;
import com.allinpay.ets.client.RequestOrder;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.action.pay.AllinPay.query.NOTIFY;
import com.hurong.credit.action.pay.AllinPay.query.QUERY_TRANS;
import com.hurong.credit.action.pay.AllinPay.query.Q_AIPG;
import com.hurong.credit.action.pay.AllinPay.query.Q_Body;
import com.hurong.credit.model.pay.AllinPay;
import com.hurong.credit.model.thirdInterface.YeePay;
import com.hurong.credit.service.thirdInterface.AllinPayService;
import com.hurong.credit.util.SecurityUtil;
import com.hurong.credit.util.WebClient;
import com.opensymphony.module.sitemesh.Config;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class AllinPayServiceImpl implements AllinPayService{
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 充值
	 */
	@Override
	public String recharge(RequestOrder requestOrder, HttpServletResponse respose,
			String basePath) {
		//商户号
		String merchantId = AppUtil.getSysConfig().get("merchantId").toString();
		//网关接收支付请求接口版本
		String version = AppUtil.getSysConfig().get("version").toString();
		//密钥
		String key = AppUtil.getSysConfig().get("key").toString();
		//支付方式
		String payType = AppUtil.getSysConfig().get("payType").toString();
		requestOrder.setInputCharset(1);
//		requestOrder.setPickupUrl("http://192.168.1.119:8030/hurong_p2p_qdjt/allinPay/rechargeAllinPayFront.do");//前台返回地址
		requestOrder.setReceiveUrl(basePath + "/allinPay/rechargeAllinPayBack.do");//后台返回地址
		requestOrder.setVersion(version);
		requestOrder.setLanguage(1);
		requestOrder.setSignType(1);
		requestOrder.setMerchantId(merchantId);
		requestOrder.setOrderCurrency("0");//订单金额币种类型
		requestOrder.setOrderExpireDatetime("60");//订单过期时间
		requestOrder.setProductName("充值");
		requestOrder.setPayType(0);	
		requestOrder.setKey(key); //key为MD5密钥，密钥是在通联支付网关会员服务网站上设置。
		String strSrcMsg = requestOrder.getSrc(); // 此方法用于debug，测试通过后可注释。
		String strSignMsg = requestOrder.doSign(); // 签名，设为signMsg字段值。
		requestOrder.setSignMsg(strSignMsg);
		
		//生成页面标签
		Map<String, String> map=new HashMap<String, String>();
		map.put("inputCharset",String.valueOf(requestOrder.getInputCharset()));
//		map.put("pickupUrl", requestOrder.getPickupUrl());
		map.put("receiveUrl", requestOrder.getReceiveUrl());
		map.put("version", requestOrder.getVersion());
		map.put("language", String.valueOf(requestOrder.getLanguage()));
		map.put("signType", String.valueOf(requestOrder.getSignType()));
		map.put("merchantId", requestOrder.getMerchantId());
		map.put("payerName", requestOrder.getPayerName());
		map.put("orderNo", requestOrder.getOrderNo());
		map.put("orderAmount", String.valueOf(requestOrder.getOrderAmount()));
		map.put("orderCurrency", requestOrder.getOrderCurrency());
		map.put("orderDatetime", requestOrder.getOrderDatetime());
		map.put("orderExpireDatetime", requestOrder.getOrderExpireDatetime());
		map.put("productName", requestOrder.getProductName());
		map.put("ext1", requestOrder.getExt1());
		map.put("payType",String.valueOf(requestOrder.getPayType()));
		map.put("signMsg", requestOrder.getSignMsg());
		//第三方提交路径
		String thirdUrl = AppUtil.getSysConfig().get("payUrl").toString();
		String[] ret=WebClient.operateParameter(thirdUrl, map,"utf-8");
		return ret[1];
		
	}
	/**
	 * 提现
	 */
	@Override
	public String[] withdraws(AllinPay allin,	HttpServletResponse respose, String basePath) {
		//获取第三方环境路径
		String URL11https= AppUtil.getSysConfig().get("DF_payUrl").toString();
		//批量代付交易代码
		String trxCode= AppUtil.getSysConfig().get("DF_trxCode").toString();
		//业务代码
		String DF_businessCode = AppUtil.getSysConfig().get("DF_businessCode").toString();
		//获取代付商户号 
		String DF_merchantId = AppUtil.getSysConfig().get("DF_merchantId").toString();
		//交易批次号
		String reqSn=DF_merchantId+"-"+String.valueOf(System.currentTimeMillis());
		boolean isfront=false;//是否发送至前置机（由前置机进行签名）
		//设置安全提供者,注意，这一步尤为重要
//		BouncyCastleProvider provider = new BouncyCastleProvider();
//		XmlTools.initProvider(provider);
		String xml="";
		AipgReq aipg=new AipgReq();
		// 组装报文头部
		InfoReq info=makeReq(trxCode,reqSn);
		aipg.setINFO(info);
		Body body = new Body() ;
		//组装BODY/TRANS_SUM
		Trans_Sum trans_sum = new Trans_Sum() ;
		trans_sum.setBUSINESS_CODE(DF_businessCode) ;
		trans_sum.setMERCHANT_ID(DF_merchantId) ;
		//总记录数
		trans_sum.setTOTAL_ITEM("1") ;
		//总金额,整数，单位分
		trans_sum.setTOTAL_SUM(allin.getTOTAL_SUM()) ;
		body.setTRANS_SUM(trans_sum);
		//BODY/TRANS_DETAILS/TRANS_DETAIL
		List <Trans_Detail>transList = new ArrayList<Trans_Detail>() ;
		Trans_Detail trans_detail = new Trans_Detail() ;
		//记录序号
		trans_detail.setSN("0001") ;
		//账号名.银行卡或存折上的所有人姓名
    	trans_detail.setACCOUNT_NAME(allin.getACCOUNT_NAME()) ;
    	//账号属性,0私人，1公司。不填时，默认为私人0。
 		trans_detail.setACCOUNT_PROP("0") ;
 		//银行卡或存折号码
		trans_detail.setACCOUNT_NO(allin.getACCOUNT_NO()) ;
		//银行代码
		trans_detail.setBANK_CODE(allin.getBANK_CODE()) ;
		trans_detail.setAMOUNT(allin.getTOTAL_SUM()) ;
		//货币类型,人民币：CNY, 港元：HKD，美元：USD。不填时，默认为人民币。
		trans_detail.setCURRENCY("CNY");
		
		
		transList.add(trans_detail) ;
        body.setDetails(transList) ;
        aipg.addTrx(body) ;
		 
        xml=XmlTools.buildXml(aipg,true);
        String result[] = new String[3];
		result = dealRet(sendToTlt(xml,isfront,URL11https));
		//0000表示第三方已经受理。但是不代表已经成功
		if(result[0].equals("0000")){
			//如果第三方没有返回最终结果通知，20秒后轮询一次
			result = queryResult(reqSn,isfront,URL11https);
		}else{
			result[0]="1111";
		}
		String str[] = new String[3];
		str[0]=result[0];
		str[1]=result[1];
		str[2]=reqSn;
		return str;
	}
	/**
	 * 批量代付 交易结果查询
	 * @param reqSn
	 * @param isfront
	 * @param URL11https
	 * @return
	 */
	public String[] queryResult(String reqSn,boolean isfront,String URL11https){
		//获取代付商户号 
		String DF_merchantId = AppUtil.getSysConfig().get("DF_merchantId").toString();
		String DF_userName = AppUtil.getSysConfig().get("DF_userName").toString();
		String DF_password = AppUtil.getSysConfig().get("DF_password").toString();
		String xml="";
		XStream xstream = new XStreamEx(new DomDriver());
		xstream.alias("AIPG", Q_AIPG.class);
		xstream.alias("INFO", InfoReq.class);
		xstream.alias("QTRANSREQ", QUERY_TRANS.class);
		Q_AIPG g = new Q_AIPG( );
//		InfoReq info = new InfoReq( );
//		info.setTRX_CODE("200003");
//		info.setVERSION("04");
//		info.setDATA_TYPE("2");
//		info.setREQ_SN("200604000000445-1428483700975");
//		info.setUSER_NAME(DF_userName);
//		info.setUSER_PASS(DF_password);
//		g.setINFO(info);
		InfoReq info = new InfoReq( );
		info.setTRX_CODE("200004");
		info.setVERSION("04");
		info.setDATA_TYPE("2");
		info.setREQ_SN(reqSn);
		info.setUSER_NAME(DF_userName);
		info.setUSER_PASS(DF_password);
		info.setLEVEL("5");
		g.setINFO(info);
		
//		NOTIFY noti = new NOTIFY();
//		noti.setNOTIFY_SN("200604000000445-1428483700975");
		QUERY_TRANS ret_detail=new QUERY_TRANS();
		ret_detail.setMERCHANT_ID(DF_merchantId);
		ret_detail.setQUERY_SN(reqSn);
		ret_detail.setSTATUS("2");
		ret_detail.setTYPE("1");
		
		 g.setQTRANSREQ(ret_detail);
		 xml=xstream.toXML(g).replaceAll("__", "_");
		 String result[] = dealRet(sendToTlt(xml,isfront,URL11https));
		 
		String str[] = new String[3];
		str[0]=result[0];
		str[1]=result[1];
		str[2]=reqSn;
			
		 return str;
	}
	/**
	 * 测试
	 * @param url
	 * @param isTLTFront
	 * @throws Exception
	 */
	public void batchDaiShou(String url,boolean isTLTFront) throws Exception {
		String xml="";
		AipgReq aipg=new AipgReq();
		//批量代付交易代码
		String trxCode= AppUtil.getSysConfig().get("DF_trxCode").toString();
		//获取代付商户号 
		String DF_merchantId = AppUtil.getSysConfig().get("DF_merchantId").toString();
		String reqSn=DF_merchantId+"-"+String.valueOf(System.currentTimeMillis());
		InfoReq info=makeReq(trxCode,reqSn);
		aipg.setINFO(info);
		Body body = new Body() ;
		Trans_Sum trans_sum = new Trans_Sum() ;
		trans_sum.setBUSINESS_CODE("00600") ;
		trans_sum.setMERCHANT_ID(DF_merchantId) ;
		trans_sum.setTOTAL_ITEM("1") ;
		trans_sum.setTOTAL_SUM("500000") ;
		body.setTRANS_SUM(trans_sum) ;
		
		List <Trans_Detail>transList = new ArrayList<Trans_Detail>() ;
		Trans_Detail trans_detail = new Trans_Detail() ;
		Trans_Detail trans_detail2 = new Trans_Detail() ;
		Trans_Detail trans_detail3 = new Trans_Detail() ;
		Trans_Detail trans_detail4 = new Trans_Detail() ;
		trans_detail.setSN("0001") ;
    	trans_detail.setACCOUNT_NAME("曾浩") ;
 		trans_detail.setACCOUNT_PROP("0") ;
		trans_detail.setACCOUNT_NO("603023061216191772") ;
		trans_detail.setBANK_CODE("103") ;
		trans_detail.setAMOUNT("500000") ;
		trans_detail.setCURRENCY("CNY");
//		trans_detail.setSETTGROUPFLAG("xCHM");
//		trans_detail.setSUMMARY("分组清算");
//		trans_detail.setUNION_BANK("234234523523");
		transList.add(trans_detail) ;
		
		trans_detail2.setSN("0002") ;
		trans_detail2.setACCOUNT_NAME("系统对接测试02") ;
//		trans_detail.setACCOUNT_PROP("1") ;
		trans_detail2.setACCOUNT_NO("622682-0013800763464") ;
		trans_detail2.setBANK_CODE("103") ;
		trans_detail2.setAMOUNT("1") ;
		trans_detail2.setCURRENCY("CNY");
		trans_detail2.setUNION_BANK("234234523523");
//		trans_detail2.setSETTGROUPFLAG("CHM");
//		trans_detail2.setSUMMARY("分组清算");
		transList.add(trans_detail2);
		
		trans_detail3.setSN("0003") ;
		trans_detail3.setACCOUNT_NAME("系统对接测试03") ;
//		trans_detail.setACCOUNT_PROP("1") ;
		trans_detail3.setACCOUNT_NO("621034-32645-1271") ;
		trans_detail3.setBANK_CODE("103") ;
		trans_detail3.setAMOUNT("1") ;
		trans_detail3.setUNION_BANK("234234523523");
//		trans_detail3.setSETTGROUPFLAG("CHM");
//		trans_detail3.setSUMMARY("分组清算");
		transList.add(trans_detail3);
		
        body.setDetails(transList) ;
        aipg.addTrx(body) ;
		
        xml=XmlTools.buildXml(aipg,true);
		dealRet(sendToTlt(xml,isTLTFront,url));
	}
	/**
	 * 组装报文头部
	 * @param trxcod
	 * @return
	 *日期：Sep 9, 2012
	 */
	private InfoReq makeReq(String trxcod,String reqSn)
	{
		//获取代付商户号 
		String DF_merchantId = AppUtil.getSysConfig().get("DF_merchantId").toString();
		String DF_userName = AppUtil.getSysConfig().get("DF_userName").toString();
		String DF_password = AppUtil.getSysConfig().get("DF_password").toString();
		//版本
		String DF_VERSION = AppUtil.getSysConfig().get("DF_VERSION").toString();  
		InfoReq info=new InfoReq();
		info.setTRX_CODE(trxcod);
		info.setREQ_SN(reqSn);
		info.setUSER_NAME(DF_userName);
		info.setUSER_PASS(DF_password);
		info.setLEVEL("5");
		info.setDATA_TYPE("2");
		info.setVERSION(DF_VERSION);
		/*if("300000".equals(trxcod)||"300001".equals(trxcod)||"300003".equals(trxcod)){
			info.setMERCHANT_ID(tranxContants.merchantId);
		}*/
		return info;
	}
	/**
	 * 返回报文处理逻辑
	 * @param retXml
	 */
	public String[] dealRet(String retXml){
		String[] result=new String[2];
		String trxcode = null;
		AipgRsp aipgrsp=null;
		//或者交易码
		if (retXml.indexOf("<TRX_CODE>") != -1)
		{
			int end = retXml.indexOf("</TRX_CODE>");
			int begin = end - 6;
			if (begin >= 0) trxcode = retXml.substring(begin, end);
		}
		aipgrsp=XSUtil.xmlRsp(retXml);
		
		//批量代收付返回处理逻辑
		if("100001".equals(trxcode)||"100002".equals(trxcode)||"211000".equals(trxcode)){
			if("0000".equals(aipgrsp.getINFO().getRET_CODE())){
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]="受理成功";
				System.out.println("受理成功，请在20分钟后进行10/每次的轮询");
			}else{
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]=aipgrsp.getINFO().getERR_MSG();
				System.out.println("受理失败，失败原因："+aipgrsp.getINFO().getERR_MSG());
			}
		}
		//交易查询处理逻辑
		if("200004".equals(trxcode)||"200005".equals(trxcode)){
			if("0000".equals(aipgrsp.getINFO().getRET_CODE())){
				QTransRsp qrsq=(QTransRsp) aipgrsp.getTrxData().get(0);
				System.out.println("查询成功，具体结果明细如下:");
				List<QTDetail> details=qrsq.getDetails();
				for(QTDetail lobj:details){
					System.out.print("原支付交易批次号:"+lobj.getBATCHID()+"  ");
					System.out.print("记录序号:"+lobj.getSN()+"  ");
					System.out.print("账号:"+lobj.getACCOUNT_NO()+"  ");
					System.out.print("户名:"+lobj.getACCOUNT_NAME()+"  ");
					System.out.print("金额:"+lobj.getAMOUNT()+"  ");
					System.out.print("返回结果:"+lobj.getRET_CODE()+"  ");
					
					if("0000".equals(lobj.getRET_CODE())){
						result[0]=lobj.getRET_CODE();
						result[1]="交易成功";
						System.out.println("返回说明:交易成功  ");
					}else{
						result[0]=lobj.getRET_CODE();
						result[1]=lobj.getERR_MSG();
						System.out.println("返回说明:"+lobj.getERR_MSG()+"  ");
					}
					
				}
			}else if("2000".equals(aipgrsp.getINFO().getRET_CODE())
					||"2001".equals(aipgrsp.getINFO().getRET_CODE())
					||"2003".equals(aipgrsp.getINFO().getRET_CODE())
					||"2005".equals(aipgrsp.getINFO().getRET_CODE())
					||"2007".equals(aipgrsp.getINFO().getRET_CODE())
					||"2008".equals(aipgrsp.getINFO().getRET_CODE())){
				
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]=aipgrsp.getINFO().getERR_MSG();
				
				System.out.print("返回说明:"+aipgrsp.getINFO().getRET_CODE()+"  ");
				System.out.println("返回说明："+aipgrsp.getINFO().getERR_MSG());
				System.out.println("该状态时，说明整个批次的交易都在处理中");
			}else if("2004".equals(aipgrsp.getINFO().getRET_CODE())){
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]="整批交易未受理通过（最终失败）";
				System.out.println("整批交易未受理通过（最终失败）");
			}else if("1002".equals(aipgrsp.getINFO().getRET_CODE())){
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]="查询无结果集";
				System.out.println("查询无结果集");
			}else{
				result[0]=aipgrsp.getINFO().getRET_CODE();
				result[1]="查询请求失败，请重新发起查询";
				System.out.println("查询请求失败，请重新发起查询");
			}
		}
		//实时交易结果返回处理逻辑(包括单笔实时代收，单笔实时代付，单笔实时身份验证)
		if("100011".equals(trxcode)||"100014".equals(trxcode)){
			if("0000".equals(aipgrsp.getINFO().getRET_CODE())){
				System.out.println("提交成功");
				TransRet ret=(TransRet) aipgrsp.getTrxData().get(0);
				System.out.println("交易结果："+ret.getRET_CODE()+":"+ret.getERR_MSG());
				if("0000".equals(ret.getRET_CODE())){
					System.out.println("交易成功（最终结果）");
				}else{
					System.out.println("交易失败（最终结果）");
					System.out.println("交易失败原因："+ret.getERR_MSG());
				}
			}else if("2000".equals(aipgrsp.getINFO().getRET_CODE())
					||"2001".equals(aipgrsp.getINFO().getRET_CODE())
					||"2003".equals(aipgrsp.getINFO().getRET_CODE())
					||"2005".equals(aipgrsp.getINFO().getRET_CODE())
					||"2007".equals(aipgrsp.getINFO().getRET_CODE())
					||"2008".equals(aipgrsp.getINFO().getRET_CODE())){
				System.out.println("交易处理中或者不确定状态，需要在稍后5分钟后进行交易结果查询（轮询）");
			}else if(aipgrsp.getINFO().getRET_CODE().startsWith("1")){
				String errormsg=aipgrsp.getINFO().getERR_MSG()==null?"连接异常，请重试":aipgrsp.getINFO().getERR_MSG();
				System.out.println("交易请求失败，原因："+errormsg);
			}else{
				TransRet ret=(TransRet) aipgrsp.getTrxData().get(0);
				System.out.println("交易失败(最终结果)，失败原因："+ret.getERR_MSG());
			}
		}
		//(单笔实时身份验证结果返回处理逻辑)
		if("211003".equals(trxcode)){
			if("0000".equals(aipgrsp.getINFO().getRET_CODE())){
				System.out.println("提交成功");
				ValidRet ret=(ValidRet) aipgrsp.getTrxData().get(0);
				System.out.println("交易结果："+ret.getRET_CODE()+":"+ret.getERR_MSG());
			}else if("2000".equals(aipgrsp.getINFO().getRET_CODE())
					||"2001".equals(aipgrsp.getINFO().getRET_CODE())
					||"2003".equals(aipgrsp.getINFO().getRET_CODE())
					||"2005".equals(aipgrsp.getINFO().getRET_CODE())
					||"2007".equals(aipgrsp.getINFO().getRET_CODE())
					||"2008".equals(aipgrsp.getINFO().getRET_CODE())){
				System.out.println("验证处理中或者不确定状态，需要在稍后5分钟后进行验证结果查询（轮询）");
			}else if(aipgrsp.getINFO().getRET_CODE().startsWith("1")){
				String errormsg=aipgrsp.getINFO().getERR_MSG()==null?"连接异常，请重试":aipgrsp.getINFO().getERR_MSG();
				System.out.println("验证请求失败，原因："+errormsg);
			}else{
				TransRet ret=(TransRet) aipgrsp.getTrxData().get(0);
				System.out.println("验证失败(最终结果)，失败原因："+ret.getERR_MSG());
			}
		}
		return result;
	}
	public String isFront(String xml,boolean flag,String url) {
		try{
			if(!flag){
				xml=this.signMsg(xml);
			}else{
				xml=xml.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "");
			}
			return sendXml(xml,url,flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public String sendToTlt(String xml,boolean flag,String url) {
		try{
			if(!flag){
				xml=this.signMsg(xml);
			}else{
				xml=xml.replaceAll("<SIGNED_MSG></SIGNED_MSG>", "");
			}
			return sendXml(xml,url,flag);
		}catch(Exception e){
			e.printStackTrace();
			if(e.getCause() instanceof ConnectException||e instanceof ConnectException){
				System.out.println("请求链接中断，请做交易结果查询，以确认上一笔交易是否已被通联受理，避免重复交易");
			}
		}
		return "请求链接中断，请做交易结果查询，以确认上一笔交易是否已被通联受理，避免重复交易";
	}
	/**
	 * 报文签名
	 * @param msg
	 * @return
	 *日期：Sep 9, 2012
	 * @throws Exception 
	 */
	public String signMsg(String xml) throws Exception{
		String pfxPassword = AppUtil.getSysConfig().get("pfxPassword").toString();
		String configPath="";
		try {
			 configPath = java.net.URLDecoder.decode(this.getClass().getResource("/").getPath(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		String pfxPath = configPath+"/com/hurong/credit/action/pay/AllinPay/20060400000044502.p12";
		xml=XmlTools.signMsg(xml, pfxPath,pfxPassword, false);
		return xml;
	}
	public String sendXml(String xml,String url,boolean isFront) throws UnsupportedEncodingException, Exception{
		System.out.println("======================发送报文======================：\n"+xml);
		String resp=XmlTools.send(url,xml);
		System.out.println("======================响应内容======================") ;
		String configPath="";
		try {
			 configPath = java.net.URLDecoder.decode(this.getClass().getResource("/").getPath(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String pfxPath = configPath+"/com/hurong/credit/action/pay/AllinPay/allinpay-pds.cer";
		boolean flag= this.verifyMsg(resp, pfxPath,isFront);
		if(flag){
			System.out.println("响应内容验证通过") ;
		}else{
			System.out.println("响应内容验证不通过") ;
		}
		return resp;
	}
	/**
	 * 验证签名
	 * @param msg
	 * @return
	 *日期：Sep 9, 2012
	 * @throws Exception 
	 */
	public boolean verifyMsg(String msg,String cer,boolean isFront) throws Exception{
		 boolean flag=XmlTools.verifySign(msg, cer, false,isFront);
		System.out.println("验签结果["+flag+"]") ;
		return flag;
	}
}
