package com.hurong.credit.action.mobile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.hurong.credit.util.ValidateCloud;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileAction extends BaseAction{

	private BpCustMember bpCustMember;
	@Resource
	private HtmlService htmlService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private OaNewsMessageService oaNewsMessageService;
	@Resource
	private WebBankcardService webBankcardService;
	private String custType;
	private String successHtml;
	
	private String email;
	private String loginname;
	private String password;
	private String repeat_password;
	private String trueName;
	private String cardnumber;
	
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getloginname() {
		return loginname;
	}
	public void setloginname(String loginname) {
		this.loginname = loginname;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getrepeat_password() {
		return repeat_password;
	}
	public void setrepeat_password(String repeat_password) {
		this.repeat_password = repeat_password;
	}
	public String gettrueName() {
		return trueName;
	}
	public void settrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getcardnumber() {
		return cardnumber;
	}
	public void setcardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	/**
	 * 爱财富注册
	 * @return
	 */
	public String mob()
	{
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.REGISTER).getTemplateFilePath());
		return "freemarker";
	}
	public String mobIndex()
	{
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEINDEX).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 注册成功
	 * @return
	 */
	public String mobReg()
	{
		System.out.println("注册成功了.......");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MREGSUCCESS).getTemplateFilePath());
		return "freemarker";
	}
	public String mobact()
	{
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MACTION).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 注册
	 * @return
	 */
	public String addReg()
	{
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		
		String projStr = AppUtil.getProjStr();
		//将数据转成JSON格式
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
//		String val =  (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
//		if(projStr.equals("proj_duorongyi")){
//			val = "checkCode";
//			checkCode = "checkCode";
//		}else{
//			val = val.toLowerCase();StringUtils.swapCase(val);
//			checkCode  = checkCode.toLowerCase();StringUtils.swapCase(StringUtils.trim(checkCode));
//		}
		
		
		//if (val!=null && val.equals(checkCode)) {
			//String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			//if(verify_sms!=null&&verify_sms.equals(phonecode)){//判断手机验证码
				if(id ==null){
					
					List<BpCustMember> list1 = bpCustMemberService.getAll();
					Boolean flag = false;//判断是否有重名的
					Boolean emailflag = false;//判断是否有重名的
					Boolean phoneflag = false;//
					
					BpCustMember bplogin = bpCustMemberService.isExist(loginname.toLowerCase());
					if(bplogin!=null){
						flag = true;
					}
					if(!"".equals(email)){
						BpCustMember bpemail = bpCustMemberService.isExistEmail(email);
						if(bpemail!=null){//判断邮箱是否被注册
							emailflag = true;
						}
					}
					
//					BpCustMember bpphone = bpCustMemberService.isExistPhone(telphone);
//					if(bpphone!=null){//判断电话是否被注册
//						phoneflag = true;
//					}
					/*for(int i =0;i<list1.size();i++){
						BpCustMember obj = list1.get(i);
						if(obj.getloginname()!=null&&obj.getloginname().equals(loginname)){
							flag = true;
						}
						if(obj.getEmail()!=null&&obj.getEmail().equals(email)){
							emailflag = true;
						}
					}*/
					if(!flag){
						if(!phoneflag){
							if(!emailflag){
								String phonecode = "";
								if("proj_wenandai".equals(AppUtil.getProjStr())){
									phonecode = null;
								}else{
									phonecode = (String) this.getSession().getAttribute(
											MyUserSession.TELPHONE_REG_RANDOM_SESSION);
								}
								if (phonecode!=null){
//									sb.append("{\"success\":true,\"errMsg\":");
//									 sb.append(gson.toJson("短信验证码错误!"));
//										sb.append("}");
								}else{
									bpCustMember = new BpCustMember();
									if(loginname!=null){
										bpCustMember.setLoginname(loginname.toLowerCase());
									}if(password!=null){
										MD5 md5 = new MD5();
										bpCustMember.setPassword(md5.md5(password, "utf-8"));
									}if(email!=null){
										bpCustMember.setEmail(email);
									}
									bpCustMember.setRegistrationDate(new Date());
									//验证云平台
									boolean bool = false;
									if(projStr!=null&&projStr.equals(Constants.PROJ_SYS)){
										
										
										if(null!=custType&&"1".equals(custType)){
											
											String type = ValidateCloud.ValidateUser(bpCustMember,Constants.PUB_REG);
											if(type==null){
												sb.append("{\"success\":true,\"errMsg\":");
												sb.append(gson.toJson("云平台验证出错"));
												sb.append("}");
											}else if(type.equals(Constants.PUB_REG_EMAIL)){
												sb.append("{\"success\":true,\"errMsg\":");
												sb.append(gson.toJson("邮箱已经存在"));
												sb.append("}");
											}else if(type.equals(Constants.PUB_REG_EXITS)){
												sb.append("{\"success\":true,\"errMsg\":");
												sb.append(gson.toJson("用户名已经存在"));
												sb.append("}");
											}else{
												bool = true;
												
											}
										}else if(null!=custType&&"0".equals(custType)){
											bool = true;
										}
										if(bool){
											//bpCustMember.setOPType("1");
										//	bpCustMember.setpassword(aValue);
											bpCustMemberService.save(bpCustMember);
											//bpCustMember.setIsSync(Short.valueOf(type));
											MD5 md5 = new MD5();
											bpCustMember = bpCustMemberService.login(loginname.toLowerCase(), md5.md5(password, "utf-8"));
											//isSendMail = validateMail(bpCustMember);
											sb.append("{\"success\":true,\"data\":");
											sb.append(gson.toJson(bpCustMember));
											sb.append(",\"result\":1");
											sb.append("}");
											this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
										}
									}else{
										bpCustMember.setTruename(trueName);
										bpCustMember.setCardcode(cardnumber);
										bpCustMemberService.save(bpCustMember);
										if("proj_wenandai".equals(AppUtil.getProjStr())){
											//wenanInsert(bpCustMember.getId());
											sb.append("{\"success\":true,\"data\":");
											sb.append(gson.toJson(bpCustMember));
											sb.append(",\"result\":1");
											sb.append("}");
											this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
										}else{
											MD5 md5 = new MD5();
											bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),md5.md5(password, "utf-8"));
											//isSendMail = validateMail(bpCustMember);
											sb.append("{\"success\":true,\"data\":");
											sb.append(gson.toJson(bpCustMember));
											sb.append(",\"result\":1");
											sb.append("}");
											
											//将注册的信息保存到session中
											QueryFilter filter =new QueryFilter(this.getRequest());
								            filter.addFilter("Q_recipient_L_EQ", bpCustMember.getId().toString());
								    		List<OaNewsMessage> list=oaNewsMessageService.getAll(filter);
								    		//我的消息
								    		int messageNum =0;
								    		if(list!=null&&list.size()>0){
								    			messageNum=list.size();
								    		}
											//登录 成功以后 统一 显示 不进行修改
								    		successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息("+messageNum+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
								    		this.getRequest().getSession().setAttribute("successHtml", successHtml);
								    		this.getRequest().getSession().setAttribute("messageNum", messageNum);
											this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
										}

									}
								}
								//this.getSession().setAttribute("isSendMail", isSendMail);//发送验证邮件是否成功
							}else{
								sb.append("{\"success\":true,\"errMsg\":");
								 sb.append(gson.toJson("邮箱已经存在"));
									sb.append("}");
							}
						}
					}else{
						sb.append("{\"success\":true,\"errMsg\":");
						 sb.append(gson.toJson("用户名已经存在"));
							sb.append("}");
					}
				}
//	else{
//			sb.append("{\"success\":true,\"errMsg\":");
//			 sb.append(gson.toJson("验证码错误"));
//				sb.append("}");
//		}

		return this.mobReg();
		//return SUCCESS;
		//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MREGSUCCESS).getTemplateFilePath());
		//return SUCCESS;
	}
	
	/**
	 * 银行卡列表显示
	 * @return
	 */
	public String getBindBankList() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem == null) {
			StringBuffer buff = new StringBuffer("[]");
		}else{
			bpCustMember = bpCustMemberService.get(mem.getId());
			QueryFilter filter0 =new QueryFilter(this.getRequest());
			if(configMap.get("thirdPayType").toString().equals("0")){
				filter0.addFilter("Q_thirdConfig_S_EQ", configMap.get("thirdPayConfig").toString());
				filter0.addFilter("Q_userFlg_S_EQ", bpCustMember.getThirdPayFlagId());
				//当前用户的编号查询
				filter0.addFilter("Q_customerId_L_EQ", bpCustMember.getId().toString());
			}else{
				
				filter0.addFilter("Q_customerId_L_EQ", bpCustMember.getId().toString());
			}
			
			List<WebBankcard>listBankCardlist = webBankcardService.getAll(filter0);


			if(null!=listBankCardlist && listBankCardlist.size()>0){
				StringBuffer buff = new StringBuffer("[");
				for (WebBankcard a:listBankCardlist) {
					if(a.getBankname()==null){
						buff.append("{\"text\":\"").append(a.getCardNum()).append("\",\"value\":\"")
						.append(a.getCardId()).append("\"},");
					}else{
					
					buff.append("{\"text\":\"").append(a.getBankname()+"-"+a.getCardNum()).append("\",\"value\":\"")
					.append(a.getCardId()).append("\"},");
					}

				}
				if (listBankCardlist.size() > 0) {
					buff.deleteCharAt(buff.length() - 1);
				}
				buff.append("]");
				
				setJsonString(buff.toString());
			}
		}
			
			return SUCCESS;
	}
}
