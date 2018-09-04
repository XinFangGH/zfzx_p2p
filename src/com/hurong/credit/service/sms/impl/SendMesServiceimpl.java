package com.hurong.credit.service.sms.impl;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.system.MailData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.message.OaNewsMessagerinfoService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.user.BpCustMemberService;

/**
 * 短信--站内信--邮件   信息的发送
 * @author XiRuiJie
 *
 */
public class SendMesServiceimpl implements SendMesService {
	@Resource
	private  OaNewsMessageService oaNewsMessageService ;
	@Resource
	private OaNewsMessagerinfoService oaNewsMessagerinfoService ;
	@Resource
	private BpCustMemberService bpCustMemberService ;
	
	Map configMap=AppUtil.getConfigMap();

	/******************************************发送验证码*********************************************/
	/**
	 * 发送验证码
	 * @param bp      会员实体类
	 * @param code    验证码
	 * @param ifsms   是否发送短信 true 是 false 否
	 * @param ifmail  是否发送邮件  true 是 false 否
	 * @param ifnew   是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_Sign(BpCustMember bp,String code,Boolean ifsms,Boolean ifmail,Boolean ifnew){
		
		if(ifsms==true){
			//1  发送短信
			SmsSendUtil  ss=  new SmsSendUtil();
			ss.sms_Sign(bp.getTelphone(), code);
		}
		if(ifnew==true){
			//2 添加站内信
			StringBuffer sb = new StringBuffer();
			sb.append("您的验证码为").append(code).append("，请在页面中输入以完成验证，有问题请致电")
			.append(configMap.get("phone")==null?"":configMap.get("phone").toString())
			.append("【")
			.append(configMap.get("subject")==null?"":configMap.get("subject").toString())
			.append("】");
			saveOaNewMessage("",  bp.getId(), "短信验证码");
		}
		if(ifmail==true){
			MailData md=new MailData("短信验证码",bp.getEmail());
			//3 邮件
			md.setUserName(bp.getLoginname());
			md.setCode(code);
			md.setPhone(configMap.get("phone")==null?"":configMap.get("phone").toString());
			md.setSubject(configMap.get("subject")==null?"":configMap.get("subject").toString());
			sendmail(md, "mail/smsCode.vm");
		}
		return "";
	}
	
	
	/******************************************成功投标*********************************************/
	/**
	 * 成功投标
	 * @param bp      会员实体类
	 * @param money   投标金额
	 * @param projName标的名称
	 * @param ifsms   是否发送短信 true 是 false 否
	 * @param ifmail  是否发送邮件  true 是 false 否
	 * @param ifnew   是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_Bid(BpCustMember bp,String money,String projName,Boolean ifsms,Boolean ifmail,Boolean ifnew){
		
		if(ifsms==true){
			//1  发送短信
			SmsSendUtil  ss=  new SmsSendUtil();
			ss.sms_Bid(bp.getTelphone(), money, projName);
		}
		if(ifnew==true){
			//2 添加站内信
			//您已成功投标项目:${projName}${code}元，有问题请致电${phone4}【${subject}】
			StringBuffer sb = new StringBuffer();
			sb.append("您已成功投标项目").append(projName).append(money).append("元，有问题请致电")
			.append(configMap.get("phone")==null?"":configMap.get("phone").toString())
			.append("【")
			.append(configMap.get("subject")==null?"":configMap.get("subject").toString())
			.append("】");
			saveOaNewMessage(sb.toString(),  bp.getId(), "投标成功");
		}
		if(ifmail==true){
			if(bp.getEmail()!=null &&!"".equals(bp.getEmail())){
				MailData md=new MailData("投标成功",bp.getEmail());
				//3 邮件
				md.setUserName(bp.getLoginname());
				md.setProjName(projName);
				md.setCode(money);
				md.setPhone(configMap.get("phone")==null?"":configMap.get("phone").toString());
				md.setSubject(configMap.get("subject")==null?"":configMap.get("subject").toString());
				sendmail(md, "mail/sms_Bid.vm");
			}else {
				System.out.println(bp.getLoginname()+"没有注册邮箱");
			}
		}
		return "";
	}
	
	/******************************************发放优惠券提醒*******************************************/
	/**
	 * 成功投标
	 * 恭喜您，成功收到一张优惠券,激活码为： ${couponNumber}，优惠券有效期到：${endTime} ，请登录平台激活使用，如有疑问请致电：${phone}【${project}】
	 * @param bp            会员实体类
	 * @param money         投标金额
	 * @param couponNumber  优惠券邀请码
	 * @param endTime       优惠券有效期
	 * @param ifsms         是否发送短信 true 是 false 否
	 * @param ifmail        是否发送邮件  true 是 false 否
	 * @param ifnew         是否添加站内信 true 是 false 否
	 * @return
	 */
	public  String  sms_bpCoupons(BpCustMember bp,String couponNumber,String  endTime,Boolean ifsms,Boolean ifmail,Boolean ifnew){
		
		if(ifsms==true){
			//1  发送短信
			SmsSendUtil  ss=  new SmsSendUtil();
			ss.sms_bpCoupons(bp.getTelphone(), couponNumber, endTime);
		}
		if(ifnew==true){
			//2 添加站内信
			//您已成功投标项目:${projName}${code}元，有问题请致电${phone4}【${subject}】
			StringBuffer sb = new StringBuffer();
			sb.append("恭喜您，成功收到一张优惠券,激活码为：").append(couponNumber).append("，优惠券有效期到：").append(endTime).append(" ，请登录平台激活使用，如有疑问请致电：")
			.append(configMap.get("phone")==null?"":configMap.get("phone").toString())
			.append("【")
			.append(configMap.get("subject")==null?"":configMap.get("subject").toString())
			.append("】");
			saveOaNewMessage(sb.toString(),  bp.getId(), "优惠券提示");
		}
		if(ifmail==true){
			if(bp.getEmail()!=null &&!"".equals(bp.getEmail())){
				MailData md=new MailData("优惠券提醒",bp.getEmail());
				//3 邮件
				md.setUserName(bp.getLoginname());
				md.setCouponNumber(couponNumber);//优惠券激活码
				md.setEndTime(endTime);//优惠券截止日期
				md.setPhone(configMap.get("phone")==null?"":configMap.get("phone").toString());
				md.setSubject(configMap.get("subject")==null?"":configMap.get("subject").toString());
				sendmail(md, "mail/sms_bpCoupons.vm");
			}else {
				System.out.println(bp.getLoginname()+"没有注册邮箱");
			}
		}
		return "";
	}
	/**
	 * 获取站内信标题
	 * @param key
	 * @return
	 */
	public String getTitle(String key,String type){
		String title = "";
		try {
			String appAbsolutePath = AppUtil.getAppAbsolutePath();
			
			String configFilePath=appAbsolutePath+"WEB-INF/classes/conf/sendmessage_config.properties";
			if(type.equals("1")){
				configFilePath=appAbsolutePath+"WEB-INF/classes/conf/senduseExplain_config.properties";
			}
			
			Properties props=new Properties();
	    	try{
	    		FileInputStream fis=new FileInputStream(configFilePath);
	    		Reader r = new InputStreamReader(fis, "UTF-8"); 
	    		props.load(r);
	    		Iterator it= props.keySet().iterator();
	    		while(it.hasNext()){
	    			String keystr=(String)it.next();
	    			if(keystr.equals(key)){
	    				title = props.get(key).toString();
	    			}
	    		}
	    	}catch(Exception ex){
	    		ex.getMessage();
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(title.equals("")){
			title = "系统默认提醒";
		}
		return title;
	}
	/**
	 * 通用站内信保存
	 * @param content   站内信内容
	 * @param recipient 
	 * @param title     标题
	 */
	public void  saveOaNewMessage(String content,Long memberId,String key){
		String title = getTitle(key,"1");
		OaNewsMessage oa=new OaNewsMessage();
		oa.setTitle(title);//发送标题
		oa.setAddresser("系统");//发送人
		oa.setOperator("系统");
		oa.setContent(content);//发送内容
		oa.setType(key);//类型
		oa.setTypename(title);//类型名称
		oa.setRecipient(memberId);//接收人
		oa.setSendTime(new Date());//发送时间
		oa.setStatus("1");
		oa.setIsAllSend("0");
		BpCustMember bp=bpCustMemberService.get(memberId);
		oa.setComment1(bp.getLoginname());
		oa.setComment2(memberId.toString());
		oaNewsMessageService.save(oa);

		OaNewsMessagerinfo info= new OaNewsMessagerinfo();
		info.setUserId(memberId);//收件人Id
		if(bp!=null){
			info.setUserName(bp.getTruename());			
		}
		info.setUserType("P2P");//P2P登陆用户
		info.setMessageId(oa.getId());
		info.setReadStatus(0);//默认阅读状态为未读
		info.setStatus(2);//已发送
		info.setIstop(0);//默认不置顶
		oaNewsMessagerinfoService.save(info);
	}
	
	/**
	 * 发送邮件 
	 * @param subject      邮件标题
	 * @param tempPath     邮件模板路径
	 * @param toEmail      接收邮件地址
	 * @param model        邮件里面传入的参数
	 */
	public  void  sendmail( MailData md,String  vm){
		/*发送邮件
	     md.setProjName(projectName);
		 md.setUserName(cpCut.getLoginname());
		 md.setPhone(configMap1.get("phone")==null ? "":configMap1.get("phone").toString());
		 md.setSubject(configMap1.get("subject")==null ? "":configMap1.get("subject").toString());
		 md.setInvestInterest(loanInterest);
		 md.setInvestPrincipal(principalRepayment);
		 sendrealMail(vm,md);*/
	}
	
	/**
	 * 通用邮箱发送
	 * @param content
	 * @param userId
	 * @param key
	 * @return
	 */
	public boolean sendrealMail(String content,Long userId,String key,String email) {
		boolean ret = false;
		// 邮件实体
		MailModel mode = new MailModel();
		// 邮件模版需要的数据
		Map<String, Object> mailData = new HashMap<String, Object>();
		mode.setMailTemplate("mail/newsSendMsg.vm");
		MailData data=new MailData("",email);
		data.setContent(content);
		mailData.put("mailData", data);
		mode.setTo(data.getToEmail());
		mode.setMailData(mailData);
		// 邮件标题
		mode.setSubject(data.getSubject());
		// 邮件发送类
		MailMessageConsumer mailMessage = new MailMessageConsumer();
		// 发送
		try {
			String sendMailMessage = mailMessage.sendMail(mode);
			if(sendMailMessage==null||"".equals(sendMailMessage)){
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}


	/**
	 * 通用发送 短信、邮件、站内信方法
	 */
	@Override
	public String sendSmsEmailMessage(Map<String, String> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			SmsSendUtil  smsUtil=  new SmsSendUtil();
			Map maps=AppUtil.getSysConfig();
			//得到模板Key得到模板内容
			//String content = maps.get(map.get("key")).toString();
			
			String content = getTitle(map.get("key").toString(),"2");
			
			System.out.println(map.get("key")+"=="+content);
			//替换内容中的变量 
			//先替换公共的变量
			content = content.replace("${subject}", configMap.get("subject").toString()).replace("${phone4}", configMap.get("phone").toString());
			if (content.contains("${date}")) {
				content = content.replace("${date}", sdf.format(new Date()));
			}
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				   Map.Entry entry = (Map.Entry) it.next();
				   String key = entry.getKey().toString();
				   String value = entry.getValue().toString();
				   int s = key.indexOf("${");  
				   if(s>=0){
					   content = content.replace(key, value);
				   }
			  }
			System.out.println("发送的内容="+content);
			BpCustMember member = null;
			if(map.containsKey("userId")){
				 member=bpCustMemberService.get(Long.valueOf(map.get("userId").toString()));
			}
			//发送站内信 
			 if(!map.get("key").toString().equals("sms_BackPassword")
					 &&!map.get("key").toString().equals("email_updatePassword")
					 &&!map.get("key").equals("sms_SignUser")
					 &&!map.get("key").equals("sms_UpdateTelphone")
					 &&!map.get("key").equals("sms_operationFunction")){//判断如果是手机、邮箱找回密码，注册不要发站内信
				 if(map.containsKey("userId")){
					 saveOaNewMessage(content,Long.valueOf(map.get("userId")),map.get("key"));
				 }
			 }
			 String codetest=AppUtil.getConfigMap().containsKey("if_test")?AppUtil.getConfigMap().get("if_test").toString():"111111";//AppSmsUtil.getConfigMap().get("if_test").toString();//是否发送短信；null或者""短信网关发送；其他值发送规定验证码
			 if ("111111".equals(codetest)) {
				//如果是测试环境 ，不发送短信
			 }else {
				//发送短信
				 if(!map.get("key").toString().equals("email_updatePassword")){//判断如果是邮箱找回密码不发短信
					 String telphone = "";
					 if(member!=null){
						 telphone = member.getTelphone();
					 }else{
						 telphone = map.get("telphone").toString();
					 }
					 System.out.println("发送的手机号="+telphone);
					 String relt = smsUtil.sendMsg(telphone,content);
				 }
				 //发送邮件
					 if(!map.get("key").toString().equals("sms_BackPassword")
							 &&!map.get("key").equals("sms_SignUser")
							 &&!map.get("key").equals("sms_UpdateTelphone")
							 &&!map.get("key").equals("sms_operationFunction")){ //判断如果是手机找回密码，注册不要发短信
						 if(member.getEmail()!=null&&!member.getEmail().equals("")){
							 sendrealMail(content,Long.valueOf(map.get("userId")),map.get("key"),member.getEmail());
						 }
				 }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
}
