package com.hurong.credit.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.action.webPhone.WebPhoneCustMemberAction;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.mobile.ParameterTypeCode;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.sms.HXSmsManagerServerImp;
import com.hurong.credit.sms.MessageStrategy;
import com.sms.SmsService;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class CreateRandomRemate extends BaseAction{
	
	private String sms_code_type;//验证码类型
	
	private String telphone;//手机号码
	
	private String randomCode;//随机数
	
	private String token;//
	
	private String telphone_qy;
	
	public String getTelphone_qy() {
		return telphone_qy;
	}

	public void setTelphone_qy(String telphoneQy) {
		telphone_qy = telphoneQy;
	}
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private PlThirdInterfaceLogService plThirdInterfaceLogService;
	
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private SmsService smsService;
	@Resource
	private MessageStrategy cYJRMsgStrategy;
	@Resource
	private MessageStrategy dJMsgStrategy;
	@Resource
	private MessageStrategy sxtMessageStrategy;//调用商讯通短信接口
	@Resource
	private MessageStrategy yzyxMessageStrategyImpl;//宇展盈讯短信接口
	@Resource
	private SendMesService sendMesService;
	//得到config.properties读取的所有资源
	@SuppressWarnings("unchecked")
	private static Map configMap = AppUtil.getConfigMap();

	  public String getSms_code_type() {
		return sms_code_type;
	}

	public void setSms_code_type(String smsCodeType) {
		sms_code_type = smsCodeType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = StringUtil.html2Text(telphone);
	}

	public String getRandomCode() {
		return randomCode;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}
/*
 * 关于对短信发送的设置
 * 
 * 1、调用信息加入到session中----------ip、当前用户
 * 2、发送信息加入到session中----------手机号码-----session会不会非常大
 * 3、同一个手机号码或者同一ip地址1分钟之内调用该方法5、10次则将该用户放入表中
 * 同时该ip或者该手机在系统中禁用30分钟--放入禁用表中
 * **/
	public boolean check(){
	
		String remoteAddr = getRequest().getRemoteAddr();//远程ip
		BpCustMember member = (BpCustMember) getSession().getAttribute(MyUserSession.MEMBEER_SESSION);//当前用户
		
		String ip = (String) getSession().getAttribute(MyUserSession.SMS_IP);
		String user = (String) getSession().getAttribute(MyUserSession.SMS_USER);
		String phone = (String) getSession().getAttribute(MyUserSession.SMS_PHONE);
		String number = (String) getSession().getAttribute(MyUserSession.SMS_NUMBER);
		String time = (String)getSession().getAttribute(MyUserSession.SMS_TIME);
		if(null==phone||"".equals(phone)){
			//首次调用短信接口
			if(null==member){
				setSession(remoteAddr,"",this.telphone,"1",new Date().getTime()+"");
			}else{
				setSession(remoteAddr,member.getLoginname(),this.telphone,"1",new Date().getTime()+"");
			}
			return true;
		}else{
			//无论是手机或者是ip只要不是第一次调用 就要更新number
			//session中手机的存放13521*****111,1234***333,12.....
			//session中ip的存放  192.168.1.1,192.156.1.33，.......
			boolean bphone = phone.contains(this.telphone);
			boolean bip = ip.contains(remoteAddr);
			if(!bphone&&!bip){//同时不包含ip和手机时
				if(null==member){
					setSession(remoteAddr,"",this.telphone,"1",new Date().getTime()+"");
				}else{
					setSession(remoteAddr,member.getLoginname(),this.telphone,"1",new Date().getTime()+"");
				}
				return true;
			}else{
				//首先判断第几次调
				if(number.length()>5){ //大于五次
					Long interval = (new Date().getTime()-Long.parseLong(time))/(5*60*1000);
					//时间差小于1分钟
					if(interval<1L){
						return false;
					}else{//大于30分钟 
						clareSession1();
						return true;
					}
				}else{
					phone +=","+this.telphone;
					ip += ","+remoteAddr;
					number +="1";
					if(null==member){
						setSession(remoteAddr,"",this.telphone,number,new Date().getTime()+"");
					}else{
						setSession(remoteAddr,member.getLoginname(),this.telphone,number,new Date().getTime()+"");
					}
					return true;
				}
			}
		}
	}
	
	public void clareSession1(){
		getSession().removeAttribute(MyUserSession.SMS_IP);
		getSession().removeAttribute(MyUserSession.SMS_NUMBER);
		getSession().removeAttribute(MyUserSession.SMS_PHONE);
		getSession().removeAttribute(MyUserSession.SMS_USER);
	}
	
	public void setSession(String ip,String user,String phone,String number,String time){
		getSession().setAttribute(MyUserSession.SMS_IP, ip);
		getSession().setAttribute(MyUserSession.SMS_NUMBER, number);
		getSession().setAttribute(MyUserSession.SMS_PHONE, phone);
		getSession().setAttribute(MyUserSession.SMS_USER, user);
		getSession().setAttribute(MyUserSession.SMS_TIME, time);
	}
	public void setSession(String ip,String user,String phone,String number){
		getSession().setAttribute(MyUserSession.SMS_IP, ip);
		getSession().setAttribute(MyUserSession.SMS_NUMBER, number);
		getSession().setAttribute(MyUserSession.SMS_PHONE, phone);
		getSession().setAttribute(MyUserSession.SMS_USER, user);
	}
	
	@SuppressWarnings("unchecked")
	public String code(){
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//刷新当前用户
		if(mem!=null){
			mem = bpCustMemberService.get(mem.getId());
		}
		String isMobile=this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
		} else{
		
		String code = (String) this.getSession().getAttribute("applyToken");
		if(null==mem&&(null==code||"".equals(code))){
			sb.append("{\"success\":true");
			sb.append(",\"result\":1");
			sb.append(",\"remark\":");
			sb.append(gson.toJson("您的操作过于频繁，请稍后再试"));
			sb.append(",\"status\":201");
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		if(null!=token&&!"".equals(token)&&!"null".equals(token)&&!token.equals("updatePhone")){
			if(null==code||"".equals(code)||!token.toLowerCase().equals(code.toLowerCase())){
				sb.append("{\"success\":true");
				 sb.append(",\"result\":1");
				 sb.append(",\"remark\":");
				 sb.append(gson.toJson("验证码错误"));
				 sb.append(",\"status\":201");
					sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
		}}
		//将数据转成JSON格式
		
		
		if(StringUtil.isNumeric(this.telphone)){
			String randomif = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			if(randomif!=null){
				this.getSession().removeAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			}
			BpCustMember bpphone = bpCustMemberService.isExistPhone(this.telphone);
			//当输入的新号码和旧号码相同
			String notRegCode=this.getRequest().getParameter("notRegCode");
			if(bpphone!=null &&notRegCode==null&&!token.equals("updatePhone")){
				 sb.append("{\"success\":true");
				 sb.append(",\"result\":1");
				 sb.append(",\"remark\":");
				 sb.append(gson.toJson("此号码已绑定，请重新输入"));
				 sb.append(",\"status\":201");
				 sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			
			//如果配置文件中的if_test是111111，说明是测试环境，不需要随机生成验证码；否则是正式环境需要生成验证码
			String random ="";
			String codetest=AppUtil.getConfigMap().containsKey("if_test")?AppUtil.getConfigMap().get("if_test").toString():"111111";//AppSmsUtil.getConfigMap().get("if_test").toString();//是否发送短信；null或者""短信网关发送；其他值发送规定验证码
			if(codetest==null  || "".equals(codetest)){
				 random = createRandom(true, 6);//生成的验证码
			}else   if("111111".equals(codetest)){
				random =codetest;
			}
			//记录发送时间
			this.getSession().setAttribute(MyUserSession.RANDOM_SESSION, System.currentTimeMillis());
			
			String[]  strRelt=new String[3];
			//发送短信 华兴短信网关
			String str="";
			//if-else 需要重构
			boolean bool = check();
			if(!bool){
				sb.append("{\"success\":true");
				 sb.append(",\"result\":1");
				 sb.append(",\"remark\":");
				 sb.append(gson.toJson("您获取验证码的次数过于频繁，请稍后再试"));
				 sb.append(",\"status\":201");
					sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
	
	
			//如果配置文件中的if_test项为111111说明是测试环境，不需要向手机发送手机验证码；否则为系统为正式环境，需要发送手机验证码
			if(codetest==null || "".equals(codetest)){
				System.out.println("短信验证码="+random);
				//判断是注册时候的还是修改手机号发送的短信
				String token = this.getRequest().getParameter("token");
				Map<String, String> map = new HashMap<String, String>();
				map.put("${code}", random);
				map.put("telphone", this.telphone);
				if(token!=null&&!token.equals("")&&token.equals("updatePhone")){
					//注册发送短信验证码。
					map.put("key", "sms_UpdateTelphone");
				}else{
					//注册发送短信验证码。
					map.put("key", "sms_SignUser");
				}
				String result =  sendMesService.sendSmsEmailMessage(map);
				sb.append("{\"success\":true");
				 sb.append(",\"result\":1");
				 sb.append(",\"remark\":1");
				 sb.append(",\"status\":200");
				sb.append("}");
			}else if("111111".equals(codetest)){
				sb.append("{\"success\":true");
				 sb.append(",\"result\":1");
				 sb.append(",\"remark\":1");
				 sb.append(",\"status\":200");
				sb.append("}");
			}

			
			Map<String ,String> map = new HashMap<String,String>();
			map.put(sms_code_type, random);
			if(telphone!=null && !"".equals(telphone)){
				this.getSession().setAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION, telphone.concat(random));
			}else{
				this.getSession().setAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION, telphone_qy.concat(random));
			}
			
		}else{
			sb.append("{\"success\":false");
			 sb.append(",\"result\":1");
			 sb.append(",\"remark\":");
			 sb.append(gson.toJson("手机号为非数字"));
			 sb.append(",\"status\":000");
				sb.append("}");
				
				plThirdInterfaceLogService.saveLog("", "失败","手机号为非数字 属于恶意攻击",
						"短信接口",mem==null?null: mem.getId(), PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE2, PlThirdInterfaceLog.TYPENAME2,
						mem==null?"":mem.getLoginname(), telphone, "", "");
		}
		setJsonString(sb.toString());
		System.out.println("============"+sb.toString());
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String beforeCode(){
		
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		//将数据转成JSON格式
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		
		String telphone = this.getRequest().getParameter("telphone");
		
		if(StringUtil.isNumeric(this.telphone)){
		String randomif = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
		this.getSession().setAttribute("random", MyUserSession.TELPHONE_REG_RANDOM_SESSION);
		if(randomif!=null){
			this.getSession().removeAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
		}
		
		String random = createRandom(true, 6);//生成的验证码
		System.out.println(random);
		//发送后返回的字符串   如：result=0&message=短信发送成功&smsid=20130807102546421
		String[]  strRelt=new String[3];
		//发送短信 华兴短信网关
		String str="";
		//Map configMap=AppUtil.getSysConfig();
		//System.out.println("beforeCode===="+configMap.containsKey("smsSign"));
		String temp=configMap.get("sms_Sign").toString();
		String content=temp.replace("${code}", random).replace("${phone4}", configMap.get("phone").toString()==null?"":configMap.get("phone").toString()).replace("${subject}", configMap.get("subject").toString()==null?"":configMap.get("subject").toString());
	
		if(AppUtil.getProjStr().equals("proj_caiyunjinrong")||AppUtil.getProjStr().equals("proj_yirandai")){
			//str=HXSmsManagerServerImp.sendMsm(this.telphone, content);
			str = sxtMessageStrategy.sendMsg(this.telphone,random,content);
			if(str!=null&&str.equals("Sucess")){
				strRelt[0]=Constants.CODE_SUCCESS;
				strRelt[1]="发送成功";
			}else{
				strRelt[0]=Constants.CODE_FAILED;
				strRelt[1]="发送失败";
			}
		}else if(AppUtil.getProjStr().equals("proj_diandianjubao")){
			str = yzyxMessageStrategyImpl.sendMsg(telphone, telphone, content);
			if(str!=null&&str.equals("Sucess")){
				strRelt[0]=Constants.CODE_SUCCESS;
				strRelt[1]="发送成功";
			}else{
				strRelt[0]=Constants.CODE_FAILED;
				strRelt[1]="发送失败";
			}
		}else{

			/*str = sxtMessageStrategy.sendMsg(this.telphone,random,content);
			if(str!=null&&str.equals("Sucess")){
				strRelt[0]=Constants.CODE_SUCCESS;
				strRelt[1]="发送成功";
			}else{
				strRelt[0]=Constants.CODE_FAILED;
				strRelt[1]="发送失败";
			}*/
		
			
			try {
				str = HXSmsManagerServerImp.sendMsm(telphone, content);
				System.out.println(str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(str!=null&&str.split("&")[0].split("=")[1].equals("0")){
				strRelt[0]=Constants.CODE_SUCCESS;
				strRelt[1]="发送成功";
			}else{
				strRelt[0]=Constants.CODE_FAILED;
				strRelt[1]="发送失败";
			}
		}
		
		if((Constants.CODE_SUCCESS).equals(strRelt[0])){
			System.out.println("发送成功！");
			sb.append("{\"success\":true,\"phone\":");
			 sb.append(random);
			 sb.append(",\"result\":1");
			 sb.append(",\"remark\":1");
			 sb.append(",\"status\":200");
				sb.append("}");
		}else{
			sb.append("{\"success\":true,\"phone\":");
			 sb.append(random);
			 sb.append(",\"result\":1");
			 sb.append(",\"remark\":");
			 sb.append(gson.toJson("手机验证码发送失败"+strRelt[1]));
			 sb.append(",\"status\":201");
				sb.append("}");
		}
		
		Map<String ,String> map = new HashMap<String,String>();
		
		map.put(sms_code_type, random);
		
		this.getSession().setAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION, telphone.concat(random));
			
		}else{
			sb.append("{\"success\":false,\"phone\":");
			 sb.append("");
			 sb.append(",\"result\":1");
			 sb.append(",\"remark\":");
			 sb.append(gson.toJson("手机号为非数字"));
			 sb.append(",\"status\":000");
				sb.append("}");
				
				plThirdInterfaceLogService.saveLog("", "失败","手机号为非数字 属于恶意攻击",
						"短信接口",mem==null?null: mem.getId(), PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE2, PlThirdInterfaceLog.TYPENAME2,
						mem==null?"":mem.getLoginname(), telphone, "", "");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
     * 创建指定数量的随机字符串
     * @param numberFlag 是否是数字
     * @param length 长度
     * @return
     */
    public static String createRandom(boolean numberFlag, int length){
	     String retStr = "";
	     String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
	     int len = strTable.length();
	     boolean bDone = true;
	     do {
		      retStr = "";
		      int count = 0;
		      for (int i = 0; i < length; i++) {
			       double dblR = Math.random() * len;
			       int intR = (int) Math.floor(dblR);
			       char c = strTable.charAt(intR);
			       if (('0' <= c) && (c <= '9')) {
			    	   count++;
			       }
			       retStr += strTable.charAt(intR);
		      }
		      if (count >= 2) {
		    	  bDone = false;
		      }
	     } while (bDone);
	    
	     return retStr;
    }
	public String[]  strategySms(String phone,String code,String content){
		/*
		 * 1、获取模板
		 * 2、获取系统类别---决策短信接口
		 * 3、调用短信接口
		 * 
		 * switch消灭不掉
		 * */
		String[] strRelt = new String[3];
		String projStr = AppUtil.getProjStr();
		if("proj_duorongyi".equals(projStr)){
			strRelt = smsService.sendSMS(content, telphone, "1");
		}else if("proj_caiyunjinrong".equals(projStr)){
			String str = cYJRMsgStrategy.sendMsg(this.telphone,"",content);
			System.out.println("caiyunjinrong:"+str);
			
			if(str!=null&&str.equals("Sucess")){
					strRelt[0]=Constants.CODE_SUCCESS;
					strRelt[1]="发送成功";
				}else{
					strRelt[0]=Constants.CODE_FAILED;
					strRelt[1]="发送失败";
				}
		}else if("proj_diandianjubao".equals(projStr)){
			String str = dJMsgStrategy.sendMsg(telphone,"", content);
			if(str!=null&&str.equals("Sucess")){
				strRelt[0]=Constants.CODE_SUCCESS;
				strRelt[1]="发送成功";
			}else{
				strRelt[0]=Constants.CODE_FAILED;
				strRelt[1]="发送失败";
			}
		}else{
			
		}
		
		return null;
	}


	/**
	 * 获取验证码
	 *
	 * @return
	 */

	public String appcode() {
		MobileDataResultModel model = new MobileDataResultModel();
		//获取发送验证码的手机号
		String phone = this.getRequest().getParameter("telPhone");
		//获取发送验证码类型参数
		String codeType = this.getRequest().getParameter("codeType");
		String isApp = this.getRequest().getParameter("isApp");
		if (!phone.isEmpty() &&!codeType.isEmpty()) {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//刷新当前用户
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
		}
//		String isMobile=this.getRequest().getParameter("isMobile");
//		if(null!=isMobile&&isMobile.endsWith("1")){
//		}
		//将数据转成JSON格式
		if (StringUtil.isNumeric(phone)) {
			String randomif = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			if (randomif != null) {
				this.getSession().removeAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			}
			BpCustMember bpphone = bpCustMemberService.isExistPhone(phone);
			//当输入的新号码和旧号码相同
			Integer count = bpCustMemberService.checkPhoneNum(phone);
			if (ParameterTypeCode.SMS_REGCODE.equals(codeType)) {
				if (bpphone != null && count != 0) {
					model.setMsg("此号码已绑定，请重新输入");
					model.setCode(MobileErrorCode.FAILED);
					setJsonString(model.toJSON());
					return SUCCESS;
				}

			}
			if (ParameterTypeCode.SMS_BACKPASSWORD.equals(codeType)) {
				if (bpphone != null && count == 0) {
					model.setMsg("此号码未注册,请注册");
					model.setCode(MobileErrorCode.FAILED);
					setJsonString(model.toJSON());
					return SUCCESS;
				}

			}


			//如果配置文件中的if_test是111111，说明是测试环境，不需要随机生成验证码；否则是正式环境需要生成验证码
			String random ="";
			String codetest=AppUtil.getConfigMap().containsKey("if_test")?AppUtil.getConfigMap().get("if_test").toString():"111111";//AppSmsUtil.getConfigMap().get("if_test").toString();//是否发送短信；null或者""短信网关发送；其他值发送规定验证码
			if(codetest==null  || "".equals(codetest)){
				random = createRandom(true, 6);//生成的验证码
			}else if("111111".equals(codetest)){
				random =codetest;
			}
			//记录发送时间
			this.getSession().setAttribute(MyUserSession.RANDOM_SESSION, System.currentTimeMillis());

			String[] strRelt = new String[3];
			//发送短信 华兴短信网关
			String str = "";
			//if-else 需要重构
			boolean bool = check();
			if (!bool) {
				model.setMsg("您获取验证码的次数过于频繁，请稍后再试");
				model.setCode(MobileErrorCode.FAILED);
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			//如果配置文件中的if_test项为111111说明是测试环境，不需要向手机发送手机验证码；否则为系统为正式环境，需要发送手机验证码
			if (codetest == null || "".equals(codetest)) {
				System.out.println("短信验证码=" + random);
				//判断是注册时候的还是修改手机号发送的短信
				String token = this.getRequest().getParameter("token");
				Map<String, String> map = new HashMap<String, String>();
				map.put("${code}", random);
				map.put("telphone", phone);
				if (ParameterTypeCode.SMS_REGCODE.equals(codeType)) {
					//注册发送短信验证码。
					map.put("key", "sms_SignUser");
				}
				if (ParameterTypeCode.SMS_BACKPASSWORD.equals(codeType)) {
					//短信忘记密码
					map.put("key", "sms_BackPassword");
				}
				if (ParameterTypeCode.SMS_UpdateTelphone.equals(codeType)) {
					//更换手机号
					map.put("key", "sms_UpdateTelphone");
				}

				String result = sendMesService.sendSmsEmailMessage(map);
					model.setMsg("发送成功");
					model.setCode(MobileErrorCode.SUCCESS);

					model.addDataContent("code", random);
					String sess = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
				    System.out.println(sess+"        session");
					System.out.println("============" + model.toString());
			}else if("111111".equals(codetest)){
				model.setMsg("发送成功");
				model.setCode(MobileErrorCode.SUCCESS);
				model.addDataContent("code", random);
			}
			this.getSession().setAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION, phone.concat(random));
			this.getSession().setAttribute(MyUserSession.RANDOM_SESSION, random);

		} else {
				model.setMsg("手机号不为数字");
				model.setCode(MobileErrorCode.FAILED);
				plThirdInterfaceLogService.saveLog("", "失败", "手机号为非数字 属于恶意攻击",
						"短信接口", mem == null ? null : mem.getId(), PlThirdInterfaceLog.MEMTYPE1,
						PlThirdInterfaceLog.TYPE2, PlThirdInterfaceLog.TYPENAME2,
						mem == null ? "" : mem.getLoginname(), phone, "", "");
		}
		setJsonString(model.toJSON());
		return SUCCESS;
		}
		else{
				model.setCode(MobileErrorCode.FAILED);
				model.setMsg("手机号，或参数类型为空");
				setJsonString(model.toJSON());
				return SUCCESS;
		}

	}
}
