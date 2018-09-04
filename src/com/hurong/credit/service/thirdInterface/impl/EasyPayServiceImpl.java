package com.hurong.credit.service.thirdInterface.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import com.hurong.core.Constants;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.thirdInterface.EasyPay;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.service.thirdInterface.EasyPayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Md5Encrypt;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;




public class EasyPayServiceImpl implements EasyPayService {
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Override
	public String[] recharge(HttpServletResponse response, BpCustMember mem,
			String orderNum, String ordDate, String amount,
			HttpServletRequest request,String basePath) {
		// TODO Auto-generated method stub
		String[] outStr =new  String[2];
		try{
			EasyPay easypay =new EasyPay();
			//生成公共参数
			easypay=generatePublicData(easypay,"create_direct_pay_by_user",request.getParameter("tradechannel"));
			//生成充值参数
			easypay=generateRechargeData(easypay,orderNum,ordDate,amount,mem,request,basePath);
			// 生成充值map
			String bankCode =request.getParameter("bankCode");
			System.out.println("bankCode=="+bankCode);
			Map<String, String> params = generateRechargeMap(easypay);
			System.out.println(easypay.getKey());
			String sign=BuildMysign(params,easypay.getKey());
			System.out.println("userSign==="+sign);
			params.put("sign",sign);
			params.put("sign_type", EasyPay.SIGNTYPE);
			String url = UrlUtils.generateUrl(params, easypay.getEasypay_url(), EasyPay.CHARSETGBK);
			System.out.println("url=="+url);
			WebClient.SendByUrl(response, url,EasyPay.CHARSETGBK);
			outStr[0]="success";
			outStr[1]="对接成功";
		}catch(Exception e){
			e.printStackTrace();
			Log.error(e.getMessage());
		}
		
		return outStr;
	}
	/**
	 * 生成充值参数map
	 * @param easypay
	 * @return
	 */
	private Map<String, String> generateRechargeMap(EasyPay easypay) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", easypay.getService());
		params.put("partner", easypay.getPartner());
		params.put("notify_url", easypay.getNotify_url());
		params.put("return_url", easypay.getReturn_url());
		params.put("_input_charset", EasyPay.CHARSETGBK);
		params.put("subject",easypay.getSubject());
		params.put("body", easypay.getBody());
		params.put("out_trade_no", easypay.getOut_trade_no());
		params.put("total_fee", easypay.getTotal_fee());
		params.put("payment_type",EasyPay.PAYMENTTYPE);
		params.put("seller_email", easypay.getSeller_email());
		if(easypay.getPaymethod()!=null&&!"".equals(easypay.getPaymethod())){
			params.put("paymethod", easypay.getPaymethod());
		}
		if(easypay.getDefaultbank()!=null&&!"".equals(easypay.getDefaultbank())){
			params.put("defaultbank", easypay.getDefaultbank());
		}
		if(easypay.getVersion()!=null&&!"".equals(easypay.getVersion())){
			params.put("version", easypay.getVersion());
		}
		return params;
	}
	/**
	 * EasyPay：生成充值数据
	 * @param easypay
	 * @param orderNum
	 * @param ordDate
	 * @param amount
	 * @param mem
	 * @param request
	 * @param basePath
	 * @return
	 */
	private EasyPay generateRechargeData(EasyPay easypay, String orderNum,
			String ordDate, String amount, BpCustMember mem,
			HttpServletRequest request,String basePath) {
		// TODO Auto-generated method stub
		//签约易生支付账号或卖家收款易生支付帐户
		String seller_email=sysConfigService.findByKey("easyPayNumber").getDataValue();
		// 版本号，固定值：PRECARD_1.0（预付费卡支付）
		String version ="";
		//支付方式，如果为银行直连，那么值为bankDirect
        String paymethod="";
        //网银代码
        String defaultbank="";
        //页面选择支付连接方式
        String bankCode=request.getParameter("bankCode");
		if(bankCode!=null&&!"".equals(bankCode)&&"easyPayNumber".equals(bankCode)){
			version =sysConfigService.findByKey("easyPayNumber").getDataValue();
		}else if(bankCode!=null&&!"".equals(bankCode)){
			paymethod="bankDirect";
        	defaultbank=bankCode;
		}else{
			paymethod="bankPay";
		}
		String PayUrl=sysConfigService.findByKey("easyPayPayURL").getDataValue();
		String verifyUrl=sysConfigService.findByKey("easyPayVerfiyURL").getDataValue();
		easypay.setVersion(version);
		easypay.setPaymethod(paymethod);
		easypay.setDefaultbank(defaultbank);
		easypay.setSeller_email(seller_email);
		easypay.setOut_trade_no(orderNum);
		easypay.setTotal_fee(amount);
		easypay.setSubject("怡然贷充值");
		easypay.setBody("怡然贷投资客户【"+mem.getTruename()+mem.getId()+"】充值交易");
		easypay.setEasypay_url(PayUrl);
		easypay.setVerify_url(verifyUrl);
		easypay.setNotify_url(basePath+"easyPay/rechargeEasyPayBack.do");
		easypay.setReturn_url(basePath+"easyPay/rechargeEasyPayFront.do");
		easypay.setBuyer_email("");
		return easypay;
	}
	/**
	 * 生成易生支付的公共参数
	 * @param easypay
	 * @param service
	 * @param tradechannel
	 * @return
	 */
	private EasyPay generatePublicData(EasyPay easypay, String service,String tradechannel) {
		// TODO Auto-generated method stub
		//获取合作者身份Id
		String  partner =sysConfigService.findByKey("partnerEasyPay").getDataValue();
		//交易安全检验码，由数字和字母组成的32位字符串
		String key=sysConfigService.findByKey("easyPayKey").getDataValue();
		//访问模式
		String transport=sysConfigService.findByKey("easyPayTransport").getDataValue();
		easypay.setPartner(partner);
		easypay.setKey(key);
		easypay.setTransport(transport);
		easypay.set_input_charset(EasyPay.CHARSETGBK);
		easypay.setSign_type(EasyPay.SIGNTYPE);
		easypay.setService(service);
		return easypay;
	}
	
	/** 
	 * 功能：生成签名结果
	 * @param sArray 要签名的数组
	 * @param key 安全校验码
	 * @return 签名结果字符串
	 */
	public  String BuildMysign(Map sArray, String key) {
		if(sArray!=null && sArray.size()>0){
			StringBuilder prestr = CreateLinkString(sArray);  //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
			//System.out.println("sign=="+Md5Encrypt.md5(prestr.append(key).toString()));
			String signData=prestr.append(key).toString();
			System.out.println("sign=="+signData);
			String sign=Md5Encrypt.md5(signData);
			System.out.println("sign=="+sign);
			return sign;
			//return Md5Encrypt.md5(prestr.append(key).toString());//把拼接后的字符串再与安全校验码直接连接起来,并且生成加密串
		}
		return null;
	}
	
	/** 
	 * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public  StringBuilder CreateLinkString(Map params){
			List keys = new ArrayList(params.keySet());
			Collections.sort(keys);
			StringBuilder prestr = new StringBuilder();
			String key="";
			String value="";
			for (int i = 0; i < keys.size(); i++) {
				key=(String) keys.get(i);
				value = (String) params.get(key);
				System.out.println(key+"=="+value);
				if("".equals(value) || value == null || 
						key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
					continue;
				}
				prestr.append(key).append("=").append(value).append("&");
			}
			return prestr.deleteCharAt(prestr.length()-1);
	}
	/**
	 * 用来验证
	 */
	@Override
	public String[] getEasypayReturnParameter(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String notify_id=request.getParameter("notify_id");
		String responseTxt =this.Verify(notify_id);
		String key=sysConfigService.findByKey("easyPayKey").getDataValue();
		if(responseTxt!=null&&!"".equals(responseTxt)&&"true".equals(responseTxt)){
			//获取易生支付GET过来反馈信息
			Map params = this.transformRequestMap(request.getParameterMap());
			String mysign = this.BuildMysign(params,key);
			String sign = request.getParameter("sign");
			if(mysign.equals(sign)){
				String is_success=request.getParameter("is_success");
				if(is_success!=null&&!"".equals(is_success)&&"T".equals(is_success)){
					
					//交易状态
					String trade_status = request.getParameter("trade_status");	
					ret[0]=Constants.CODE_SUCCESS;
					if(trade_status.equals("WAIT_BUYER_PAY")){
						ret[1]="等待支付!";
					}else if(trade_status.equals("TRADE_FAILURE")){
						ret[0]=Constants.CODE_FAILED;
						ret[1]="支付失败!";
					}else if(trade_status.equals("TRADE_FINISHED")){
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="支付成功!";
					}else{
						ret[0]=Constants.CODE_FAILED;
						ret[1]="易生支付签名正确！交易类型不清楚";
					}
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="易生支付本次交易失败";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易生支付签名验证出错！";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="易生支付参数返回验证出错！";
		}
		
		return ret;
	}
	
	/**
	 * 用来验证返回后端Erp，操作数据库数据方法
	 */
	@Override
	public String[] postEasyPayReturnParmater(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("8-5后台通知数据开始");
		String[] ret=new String[2];
		try{
			String notify_id=request.getParameter("notify_id");
			String sign = request.getParameter("sign");
			//获取易生支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
			String trade_no = request.getParameter("trade_no");				//易生支付交易号
			System.out.println("trade_no=="+trade_no);
			String order_no = request.getParameter("out_trade_no");	 
			System.out.println("order_no=="+order_no);//获取订单号
			String total_fee = request.getParameter("total_fee");	        	//获取总金额
			String subject=request.getParameter("subject");					//商品名称、订单名称
			String gmt_create=request.getParameter("gmt_create");			//交易订单创建时间
			String gmt_payment=request.getParameter("gmt_payment");			//银行支付时间
			if(subject!=null && !"".equals(subject)){
				System.out.println("subject-收到=="+subject);
				subject = new String(subject.getBytes("ISO-8859-1"),"GBK");
				System.out.println("subject=="+subject);
			}
			String body = request.getParameter("body");//商品描述、订单备注、描述
			if(body != null && !"".equals(body)){
				body = new String(body.getBytes("ISO-8859-1"), "GBK");
				System.out.println("body收到=="+body);
			}
			String trade_status = request.getParameter("trade_status");		//交易状态
			String responseTxt =this.Verify(notify_id);
			String key=sysConfigService.findByKey("easyPayKey").getDataValue();
			if(responseTxt!=null&&!"".equals(responseTxt)&&"true".equals(responseTxt)){
				request.setCharacterEncoding("GBK");
				Map params = this.transformRequestMap(request.getParameterMap());
				String mysign = this.BuildMysign(params,key);
				System.out.println("mysign=="+mysign);
				System.out.println("sign=="+sign);
				if(true){
					String dealType="3";//默认交易失败
					if(trade_status.equals("WAIT_BUYER_PAY")){
						dealType="1";
					}else if(trade_status.equals("TRADE_FINISHED")){
						dealType="2";
					}
					ret=this.readlyToOperateAccount(order_no,total_fee,dealType,request);
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="易生支付签名验证出错！";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="易生支付参数返回notify_id验证出错！";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="系统错误请联系管理员！";
		}
		
		return ret;
	}
	/**
	 * 用来准备操作数据库
	 * @param orderNo
	 * @param totalFee
	 * @param request
	 * @return
	 */
	private String[] readlyToOperateAccount(String orderNo, String totalFee,String trade_status,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String[] ret =new String[2];
		System.out.println("开始进行款项操作");
		if(trade_status.equals("1")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="暂时不知道如何操作";
		}else{
			ObAccountDealInfo ob =obAccountDealInfoService.getByOrderNumber(orderNo,totalFee,ObAccountDealInfo.T_RECHARGE.toString(),ObSystemAccount.type0.toString());
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
				 map.put("recordNumber",orderNo);//交易流水号
				 map.put("dealStatus",Short.valueOf(trade_status));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 ret=obAccountDealInfoService.updateAcountInfo(map);
				//ret =obAccountDealInfoService.updateAcountInfo(bp.getId(), ObAccountDealInfo.T_RECHARGE.toString(), totalFee, ObSystemAccount.type0.toString(), orderNo, null, trade_status);
				//ret = payCommonService.updateDealToERP(bp, totalFee,orderNo,ObAccountDealInfo.T_RECHARGE.toString() , trade_status);
				System.out.println(ret[0]+"======"+ret[1]+"type==="+ObAccountDealInfo.T_RECHARGE.toString()+"state=="+trade_status);
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="没有这条交易记录";
			}
		}
		
		return ret;
	}
	/**
	* *功能：获取远程服务器ATN结果,验证返回URL
	* @param notify_id 通知校验ID
	* @return 服务器ATN结果
	* 验证结果集：
	* invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
	* true 返回正确信息
	* false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	*/
	public  String Verify(String notify_id){
		System.out.println("开始验证url====获取远程服务器ATN结果,验证返回URL");
		//获取远程服务器ATN结果，验证是否是易生支付服务器发来的请求
		String transport=sysConfigService.findByKey("easyPayTransport").getDataValue();
		String partner = sysConfigService.findByKey("partnerEasyPay").getDataValue();
		StringBuilder veryfy_url = new StringBuilder();
		if(transport.equalsIgnoreCase("https")){
			veryfy_url.append("https://mapi.bhecard.com/verify/notify?");
		} else{
			veryfy_url.append("http://mapi.bhecard.com/verify/notify?");	//"http://mapi.bhecard.com/verify/notify?";
		}
		veryfy_url.append("partner=").append(partner).append("&notify_id=").append(notify_id);
		System.out.println("验证url"+veryfy_url);
		
		String responseTxt = CheckUrl(veryfy_url.toString());
		
		return responseTxt;

	}
	
	/**
	* *功能：获取远程服务器ATN结果
	* @param urlvalue 指定URL路径地址
	* @return 服务器ATN结果
	* 验证结果集：
	* invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
	* true 返回正确信息
	* false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	*/
	private  String CheckUrl(String urlvalue){
		String inputLine = "";
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			if(in!=null){
				inputLine = in.readLine().toString();
			}
			in.close();
			urlConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputLine;
	}
	
	
	/**
	 * 将易生支付POST过来反馈信息转换一下
	 * @param requestParams 返回参数信息
	 * @return Map 返回一个只有字符串值的MAP
	 * */
	public  Map transformRequestMap(Map requestParams){
		Map params = null;
		if(requestParams!=null && requestParams.size()>0){
			params = new HashMap();
			String name ="";
			String[] values =null;
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				name= (String) iter.next();
				values= (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					System.out.println("name="+name+"|valueStr="+valueStr);
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				params.put(name, valueStr);
			}
		}
		return params;
	}
	
}