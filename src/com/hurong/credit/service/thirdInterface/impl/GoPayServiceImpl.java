package com.hurong.credit.service.thirdInterface.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.model.thirdInterface.GoPayVO;
import com.hurong.credit.model.thirdInterface.GoZhiFuVO;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.service.thirdInterface.GoPayService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;

public class GoPayServiceImpl implements GoPayService {
	@Resource
	private SysConfigService sysConfigService;
	private String URL = "";
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();

	@Override
	public String[] recharge(HttpServletResponse respose, String bankCode,
			String buyerContact, String buyerName, String goodsName,
			String goodsDetail, String remark1, String remark2, String tranAmt,
			String ip, String userType,String orderNum, String charSet, String timeOut,String basePath) {
		String[] outStr =new  String[2];
		if (timeOut == null || timeOut.equals("")) {
			timeOut = "1200000";
		}
		// 国付宝VO
		GoPayVO goPayVO = new GoPayVO();
		// 生成公共参数
		goPayVO = generateRechargePublicData(goPayVO,basePath,charSet);
		// 生成参数
		goPayVO = generateRechargeParamatsData(goPayVO, bankCode, buyerContact,
				buyerName, goodsName, goodsDetail, remark1, remark2, tranAmt,
				ip, userType,orderNum);

		// 组织加密明文
		String signValue = generateRechargePlain(goPayVO,charSet);
		// 生成充值map
		Map<String, String> params = generateRechargeMap(goPayVO, signValue);
		try {
			String url = UrlUtils.generateUrl(params, URL, charSet);
			System.out.println("传递"+url);
			WebClient.SendByUrl(respose, url,Constants.CHAR_UTF);
			outStr[0]=Constants.CODE_SUCCESS;
			outStr[1]="对接成功";
			
			//  String param=UrlUtils.generateParams(params,charSet); 
			// String  outStr0 =WebClient.getWebContentByPost(URL, param, charSet,Integer.valueOf(timeOut));
			  //System.out.println(outStr0);
			 
		} catch (Exception e) {
			e.printStackTrace();
			outStr[0]=Constants.CODE_FAILED;
			outStr[1]="对接失败"+e.getMessage();
		}

		return outStr;
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
	 * 生成充值Map
	 * @param goPayVO
	 * @param signValue
	 * @return
	 */
	private Map<String, String> generateRechargeMap(GoPayVO goPayVO,
			String signValue) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("version", goPayVO.getVersion());
		params.put("charset", GoPayVO.charset2);
		params.put("language", GoPayVO.language);
		params.put("signType", GoPayVO.signType);
		params.put("tranCode", GoPayVO.tranCode);
		params.put("merchantID", goPayVO.getMerchantID());
		params.put("merOrderNum", goPayVO.getMerOrderNum());
		params.put("tranAmt", goPayVO.getTranAmt());
		params.put("feeAmt", goPayVO.getFeeAmt());
		params.put("currencyType", GoPayVO.currencyType);
		params.put("frontMerUrl", goPayVO.getFrontMerUrl());
		params.put("backgroundMerUrl", goPayVO.getBackgroundMerUrl());

		params.put("tranDateTime", goPayVO.getTranDateTime());
		params.put("virCardNoIn", goPayVO.getVirCardNoIn());

		params.put("tranIP", goPayVO.getTranIP());
		params.put("isRepeatSubmit", goPayVO.getIsRepeatSubmit());
		params.put("goodsName", goPayVO.getGoodsName());

		params.put("goodsDetail", goPayVO.getGoodsDetail());
		params.put("buyerName", goPayVO.getBuyerName());
		params.put("buyerContact", goPayVO.getBuyerContact());
		params.put("merRemark1", goPayVO.getMerRemark1());
		params.put("merRemark2", goPayVO.getMerRemark2());

		params.put("bankCode", goPayVO.getBankCode());
		params.put("userType", goPayVO.getUserType());
		params.put("signValue", signValue);
		params.put("gopayServerTime", goPayVO.getGopayServerTime());
		return params;
	}

	/**
	 * 生成充值加密数据
	 * 
	 * @param goPayVO
	 * @return
	 */
	private String generateRechargePlain(GoPayVO goPayVO,String charset) {
		// 组织加密明文
		String plain = "version=[" + goPayVO.getVersion() + "]tranCode=["
				+ GoPayVO.tranCode + "]merchantID=[" + goPayVO.getMerchantID()
				+ "]merOrderNum=[" + goPayVO.getMerOrderNum() + "]tranAmt=["
				+ goPayVO.getTranAmt() + "]feeAmt=[" + goPayVO.getFeeAmt()
				+ "]tranDateTime=[" + goPayVO.getTranDateTime()
				+ "]frontMerUrl=[" + goPayVO.getFrontMerUrl()
				+ "]backgroundMerUrl=[" + goPayVO.getBackgroundMerUrl()
				+ "]orderId=[]gopayOutOrderId=[]tranIP=[" + goPayVO.getTranIP()
				+ "]respCode=[]gopayServerTime=["
				+ goPayVO.getGopayServerTime() + "]VerficationCode=["
				+ goPayVO.getVerficationCode() + "]";
		System.out.println("加密明文："+plain);
		MD5 md5=new MD5();
		String signValue = md5.md5(plain,charset);
		System.out.println(charset+"加密后："+signValue);
		return signValue;
	}

	/**
	 * 生成充值参数
	 * 
	 * @param goPayVO
	 * @param bankCode
	 * @param buyerContact
	 * @param buyerName
	 * @param feeAmt
	 * @param goodsName
	 * @param goodsDetail
	 * @param remark1
	 * @param remark2
	 * @param tranAmt
	 * @param ip
	 * @param userType
	 * orderNum  订单号
	 * @return
	 */
	private GoPayVO generateRechargeParamatsData(GoPayVO goPayVO,
			String bankCode, String buyerContact, String buyerName,
			String goodsName, String goodsDetail, String remark1,
			String remark2, String tranAmt, String ip, String userType,String orderNum) {
		goPayVO.setBankCode(bankCode);
		goPayVO.setBuyerContact(buyerContact==null?"":buyerContact);
		goPayVO.setBuyerName(StringUtil.stringURLEncoderByGBK(buyerName));

		goPayVO.setGoodsName(StringUtil.stringURLEncoderByGBK(goodsName));
		goPayVO.setGoodsDetail(StringUtil.stringURLEncoderByGBK(goodsDetail));

		goPayVO.setMerRemark1(StringUtil.stringURLEncoderByGBK(remark1));
		goPayVO.setMerRemark2(StringUtil.stringURLEncoderByGBK(remark2));
		goPayVO.setTranAmt(tranAmt);
		goPayVO.setMerOrderNum(orderNum); // 订单号
		goPayVO.setTranIP(ip);
		goPayVO.setUserType(userType); // 1 个人 2 企业支付

		return goPayVO;
	}

	/**
	 * 生成充值公共 数据
	 * 
	 * @param goPayVO
	 * @return
	 */
	private GoPayVO generateRechargePublicData(GoPayVO goPayVO,String basePath,String charset) {

		// 获取系统配置信息
		// 商户id
		String mid = configMap.get("platform").toString();
		// URL
		URL = configMap.get("URL").toString();
		// 商户识别码
		String password = configMap.get("recognition").toString();
		System.out.println("password==="+password);
		// 手续费
		String freeAmt =configMap.get("poundage").toString();
		System.out.println("freeAmt==="+freeAmt);
		// 版本号
		String goPayVer = configMap.get("Ver").toString();
		System.out.println("goPayVer==="+goPayVer);
		// 国付宝账号
		String goPayNumber = configMap.get("number").toString();
		System.out.println("goPayNumber==="+goPayNumber);

		goPayVO.setVerficationCode(password);
		goPayVO.setCharset(GoPayVO.charset2);
		goPayVO.setVersion(goPayVer);
		goPayVO.setVirCardNoIn(goPayNumber);
		goPayVO.setMerchantID(mid);
		goPayVO.setIsRepeatSubmit("1");
		goPayVO.setFeeAmt(freeAmt);
		goPayVO.setGopayServerTime(getGopayServerTime(charset));
		goPayVO.setTranDateTime(DateUtil
				.dateToStr(new Date(), "yyyyMMddHHmmss"));
		goPayVO.setFrontMerUrl(basePath+"pay/returnRechargeGoPayFont.do");// (AppUtil.get+"pay/backPay.do");
		goPayVO.setBackgroundMerUrl(basePath+"pay/returnRechargeGoPayBack.do");// (this.getBasePath()+"pay/backPay.do");
		return goPayVO;
	}


	
	/**
	 * 获取国付宝服务器时间 用于时间戳
	 * @return 格式YYYYMMDDHHMMSS
	 */
	public static String getGopayServerTime(String charset) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,10000); 
		GetMethod getMethod = new GetMethod("https://www.gopay.com.cn/PGServer/time");
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);  
		// 执行getMethod
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(getMethod);			
			if (statusCode == HttpStatus.SC_OK){
				String respString = StringUtils.trim((new String(getMethod.getResponseBody(),charset)));
				return respString;
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}
	
   
}
