package com.hurong.credit.action.system;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.RandomValidateCode;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.model.system.SystemConfig;
import com.hurong.credit.model.thirdInterface.Fuiou;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.activity.BpActivityManageService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.sms.util.AppSmsUtil;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.service.system.IndexShowService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.CreateRandomRemate;
import com.hurong.credit.util.HttpRequestDeviceUtils;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MD5_T;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.Random;
import com.hurong.credit.util.TemplateConfigUtil;
import com.hurong.credit.util.ValidateCloud;
import com.hurong.credit.util.validation;
import com.kong.util.ValidateUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

public class RegAction extends BaseAction {
	
	private static final long serialVersionUID = -25541236985328967L;
	
	private Fuiou fuiou;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@SuppressWarnings("unused")
	@Resource
	private IndexShowService indexShowService;
	@Resource
	private OaNewsMessageService oaNewsMessageService;
	@Resource
	private HtmlService htmlService;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private WebBankcardService webBankCardService;
	@Resource
	private BpActivityManageService bpActivityManageService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private SendMesService sendMesService;
	@Resource
	private  BpCustRelationService bpCustRelationService;
	//得到config.properties读取的所有资源
	@SuppressWarnings("rawtypes")
	private static Map configMap = AppUtil.getConfigMap();
	private static Properties props = new Properties(); 
	private static MultiThreadedHttpConnectionManager connectionManager;
	private String telphone_qy;
	
	public String getTelphone_qy() {
		return telphone_qy;
	}

	public void setTelphone_qy(String telphoneQy) {
		telphone_qy = telphoneQy;
	}

	SmsSendUtil  smsSendUtil = new SmsSendUtil();
	
	private IndexShow indexShow;//存放页面头部信息
	
	private String token;
	
	private String paymentCode;//支付密码
	protected String loginname;//登录名
	protected String truename;//真实姓名
	protected String password;//密码
	protected String plainpassword;//用户推荐码
	protected String recommandPerson;//邀请的推荐码
	protected String departmentRecommend;//部门的推荐码
	protected String telphone;//手机号码
	protected String verify_sms;//短信验证码
	protected String email="";//邮箱
	protected Integer type;//类型：企业，个人
	protected Integer sex;//性别
	protected Integer cardtype;//证件类型
	protected String cardcode;//证件号码
	protected java.util.Date birthday;//出生日期
	protected String headImage;//头像
	protected String nativePlaceProvice;//籍贯省
	protected String nativePlaceCity;//籍贯市
	protected String nation;//民族
	protected String homePhone;//家庭电话
	protected String relationAddress;//联系地址
	protected String postCode;//邮编
	protected String QQ;
	protected String sinawb;
	protected String MSN;
	protected String securityQuestion;//密码保护问题
	protected String securityAnswer;//密码保护答案
	protected Integer roleId;//角色ID
	protected java.util.Date registrationDate;//注册时间
	protected Long liveProvice;//居住城市省
	protected Long liveCity;//居住城市-市
	protected Integer marry;//婚姻状况
	protected String fax;//传真
	protected Long memberOrderId;//会员等级
	private String QQNickname=null;//QQ的ID号
	private String recommand;
	
	public String getRecommand() {
		return recommand;
	}

	public void setRecommand(String recommand) {
		this.recommand = recommand;
	}

	public String getQQNickname() {
		return QQNickname;
	}

	public void setQQNickname(String qQNickname) {
		QQNickname = qQNickname;
	}

	public String getSinawb() {
		return sinawb;
	}

	public void setSinawb(String sinawb) {
		this.sinawb = sinawb;
	}

	public Fuiou getFuiou() {
		return fuiou;
	}

	public void setFuiou(Fuiou fuiou) {
		this.fuiou = fuiou;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	protected boolean isSendMail;
	
	protected String checkCode;//验证码
	private String successHtml;//返回登录信息
	
	private String isSync;
	
	private String custType;
	
	public String getSuccessHtml() {
		return successHtml;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getIsSync() {
		return isSync;
	}


	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}


	public void setSuccessHtml(String successHtml) {
		this.successHtml = successHtml;
	}

	public boolean isSendMail() {
		return isSendMail;
	}

	public void setSendMail(boolean isSendMail) {
		this.isSendMail = isSendMail;
	}

	public IndexShow getIndexShow() {
		return indexShow;
	}

	public void setIndexShow(IndexShow indexShow) {
		this.indexShow = indexShow;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainpassword() {
		return plainpassword;
	}

	public void setPlainpassword(String plainpassword) {
		this.plainpassword = plainpassword;
	}

	public String getDepartmentRecommend() {
		return departmentRecommend;
	}

	public void setDepartmentRecommend(String departmentRecommend) {
		this.departmentRecommend = departmentRecommend;
	}

	public String getRecommandPerson() {
		return recommandPerson;
	}

	public void setRecommandPerson(String recommandPerson) {
		this.recommandPerson = recommandPerson;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getNativePlaceProvice() {
		return nativePlaceProvice;
	}

	public void setNativePlaceProvice(String nativePlaceProvice) {
		this.nativePlaceProvice = nativePlaceProvice;
	}

	public String getNativePlaceCity() {
		return nativePlaceCity;
	}

	public void setNativePlaceCity(String nativePlaceCity) {
		this.nativePlaceCity = nativePlaceCity;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getRelationAddress() {
		return relationAddress;
	}

	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getMSN() {
		return MSN;
	}

	public void setMSN(String mSN) {
		MSN = mSN;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public java.util.Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(java.util.Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Long getLiveProvice() {
		return liveProvice;
	}

	public void setLiveProvice(Long liveProvice) {
		this.liveProvice = liveProvice;
	}

	public Long getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(Long liveCity) {
		this.liveCity = liveCity;
	}

	public Integer getMarry() {
		return marry;
	}

	public void setMarry(Integer marry) {
		this.marry = marry;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getMemberOrderId() {
		return memberOrderId;
	}

	public void setMemberOrderId(Long memberOrderId) {
		this.memberOrderId = memberOrderId;
	}

	public String getVerify_sms() {
		return verify_sms;
	}

	public void setVerify_sms(String verifySms) {
		verify_sms = verifySms;
	}
	
	/**
	 * 进入注册页面
	 * @return
	 */
	public String html() {
//		String phone="18312342345";
//		String[] ret=validation.phoneValidation(phone);
//		System.out.println(ret[1]);
		String mobile = this.getRequest().getParameter("mobile");
		String backpath = this.getRequest().getParameter("backpath");
		String recommand = this.getRequest().getParameter("recommand");
		this.getRequest().getSession().setAttribute("recommand", recommand);
		this.getRequest().getSession().setAttribute("backpath", backpath);
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		MD5 md5 = new MD5();
		token = md5.md5(Random.createRandom(false, 10),"UTF-8");
		System.out.println("token:"+token);
		getSession().setAttribute("applyToken", token);
		if("mobile".equals(mobile) || HttpRequestDeviceUtils.isMobileDevice(getRequest())){
			try {
				getResponse().sendRedirect(getRequest().getContextPath()+"/indexp2pmobile.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.REG).getTemplateFilePath());
		}
		System.out.println("successResultValue=="+this.getSuccessResultValue());
		return "freemarker";
		
	}
	
	public String forgot(){
		//客服电话
		String servicePhone = (String) configMap.get("phone");
		this.getSession().setAttribute("servicePhone", servicePhone);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.FORGOTPASSWORD).getTemplateFilePath());
		return "freemarker";
	}
	public String gotoUpdatePassword(){
		String id = getRequest().getParameter("id");
		if(id!=null){
			bpCustMember = bpCustMemberService.get(Long.parseLong(id));
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CHECKUSER).getTemplateFilePath());
		return "freemarker";
	}
	public String checkUer(){
		String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
		StringBuffer sb = new StringBuffer();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (!phonecode.equals(verify_sms)){
			sb.append("{\"success\":true,\"errMsg\":");
			sb.append(gson.toJson("短信验证码错误!"));
			sb.append("}");
		}else{
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_loginname_S_EQ", loginname);
			List<BpCustMember> list = bpCustMemberService.getAll(filter);
			if(list==null||list.size()==0){
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("用户不存在!"));
				sb.append("}");
			}else{
				BpCustMember mem = list.get(0);
				if(mem.getEmail()!=null&&mem.getEmail().equals(email)){
					sb.append("{\"success\":true,\"data\":");
					sb.append(gson.toJson(mem));
					sb.append(",\"result\":1");
					sb.append("}");
				}else{
					sb.append("{\"success\":true,\"errMsg\":");
					sb.append(gson.toJson("邮箱出错!"));
					sb.append("}");
				}
			}
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 进行注册
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unused", "static-access" })
	public String sign() throws Exception {
		System.out.println("注册-----"+this.QQ+"====="+this.sinawb);
		String autoReg = this.getRequest().getParameter("autoReg");
		System.out.println("autoReg=="+autoReg);
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		
		String projStr = AppUtil.getProjStr();
		//将数据转成JSON格式
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		String val =  (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
		if(projStr.equals("proj_duorongyi")){
			val = "checkCode";
			checkCode = "checkCode";
		}else{
			val = val.toLowerCase();StringUtils.swapCase(val);
			checkCode  = checkCode.toLowerCase();StringUtils.swapCase(StringUtils.trim(checkCode));
		}
		
		
		if (val!=null && val.equals(checkCode)) {
			//String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			//if(verify_sms!=null&&verify_sms.equals(phonecode)){//判断手机验证码
				if(id ==null){
					
					List<BpCustMember> list1 = bpCustMemberService.getAll();
					Boolean flag = false;//判断是否有重名的
					Boolean emailflag = false;//判断是否有重名的
					Boolean phoneflag = false;//
					Boolean recommandPersonflag = false;
					Boolean departmentRecommendflag = false;//部门推荐码
					
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
					recommandPerson=this.getRequest().getParameter("recommandPerson");
					if(recommandPerson!=null&&!"".equals(recommandPerson)&&!"'".equals(recommandPerson)){
						BpCustMember bprecommandPerson = bpCustMemberService.isRecommandPerson(recommandPerson);
						if(bprecommandPerson!=null){//判断邀请码是否存在
							recommandPersonflag = true;
						}
					}else{
						recommandPersonflag=true;
					}
					departmentRecommend = this.getRequest().getParameter("departmentRecommend");
					if(departmentRecommend!=null&&!"".equals(departmentRecommend)){
						boolean o = bpCustMemberService.organiz(departmentRecommend);
						if(o==true){
							departmentRecommendflag = true;
						}
					}else{
						departmentRecommendflag=true;
					}
					
					BpCustMember bpphone = bpCustMemberService.isExistPhone(telphone);
					if(bpphone!=null){//判断电话是否被注册
						phoneflag = true;
					}
					/*for(int i =0;i<list1.size();i++){
						BpCustMember obj = list1.get(i);
						if(obj.getLoginname()!=null&&obj.getLoginname().equals(loginname)){
							flag = true;
						}
						if(obj.getEmail()!=null&&obj.getEmail().equals(email)){
							emailflag = true;
						}
					}*/
					if(!flag){
						if(!phoneflag){
							if(!emailflag){
								if(recommandPersonflag){
									if(departmentRecommendflag){

										String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
										if("proj_wenandai".equals(AppUtil.getProjStr())){
											phonecode = null;
										}else{
											phonecode = (String) this.getSession().getAttribute(
													MyUserSession.TELPHONE_REG_RANDOM_SESSION);
										}
										if (phonecode!=null&&!phonecode.equals(verify_sms)){
											sb.append("{\"success\":true,\"errMsg\":");
											 sb.append(gson.toJson("短信验证码错误!"));
												sb.append("}");
										}else{
											bpCustMember = new BpCustMember();
											if(!"autoReg".equals(autoReg))
											{
												if(loginname!=null){
													//bpCustMember.setLoginname(loginname.toLowerCase());
													this.getSession().setAttribute("wad_loginName", loginname);
												}if(password!=null){
													MD5 md5 = new MD5();
													//bpCustMember.setPassword(md5.md5(password, "utf-8"));
													this.getSession().setAttribute("wad_password", password);
												}if(email!=null){
													bpCustMember.setEmail(email);
												}if(type!=null){
													bpCustMember.setType(type);
												}if(telphone!=null){
													bpCustMember.setTelphone(telphone);
													bpCustMember.setIsCheckPhone("1");
												}if(recommandPerson!=null){
													//bpCustMember.setRecommandPerson(recommandPerson);
													this.getSession().setAttribute("wad_recommandPerson", recommandPerson);
												}
											}else{
												System.out.println("手机和微薄 注册");
												//手机和微薄 注册
												if(loginname!=null){
													bpCustMember.setLoginname(loginname.toLowerCase());
													MD5_T md1 = new MD5_T();
													bpCustMember.setPlainpassword(md1.md5_3(loginname));
												}if(password!=null){
													MD5 md5 = new MD5();
													bpCustMember.setPassword(md5.md5(password, "utf-8"));
												}if(recommandPerson!=null){
													bpCustMember.setRecommandPerson(recommandPerson);
												}
												System.out.println("开始注册qq");
												if(QQ!=null){
													System.out.println("注册qq");
													bpCustMember.setQq(QQ);
													bpCustMember.setIsCheckQQ("1");
												}
												System.out.println("开始注册新浪");
												if(sinawb!=null){
													System.out.println("注册新浪");
													bpCustMember.setSinawb(sinawb);
													bpCustMember.setIsCheckSinaWB("1");
												}
											}
											
											if(null!=custType){
												bpCustMember.setCustType(Short.valueOf(custType));
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
														sb.append(",\"result\":3");
														sb.append("}");
													}else if(type.equals(Constants.PUB_REG_EMAIL)){
														sb.append("{\"success\":true,\"errMsg\":");
														sb.append(gson.toJson("邮箱已经存在"));
														sb.append(",\"result\":3");
														sb.append("}");
													}else if(type.equals(Constants.PUB_REG_EXITS)){
														sb.append("{\"success\":true,\"errMsg\":");
														sb.append(gson.toJson("用户名已经存在"));
														sb.append(",\"result\":3");
														sb.append("}");
													}else{
														bool = true;

													}
												}else if(null!=custType&&"0".equals(custType)){
													bool = true;
												}
												if(bool){
													//bpCustMember.setOPType("1");
												//	bpCustMember.setPassword(aValue);
													bpCustMember=bpCustMemberService.save(bpCustMember);
													//如果 为1 资金池模式 ThirdPayFlagId 设置为 客户id
													if(AppUtil.getSysConfig().get("thirdPayType").equals("1")){
														bpCustMember.setThirdPayFlagId(bpCustMember.getId().toString());
													}
													bpCustMember.setPlainpassword(bpCustMember.getId().toString());
													bpCustMember=bpCustMemberService.save(bpCustMember);
													//bpCustMember.setIsSync(Short.valueOf(type));
													MD5 md5 = new MD5();
													bpCustMember = bpCustMemberService.login(loginname.toLowerCase(), md5.md5(password, "utf-8"));
													//isSendMail = validateMail(bpCustMember);
													if("autoReg".equals(autoReg))
													{
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":2");
														sb.append("}");
													}else
													{
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":1");
														sb.append("}");
													}
													this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中

													//发送注册成功信息
													//svn：songwj
													if(bpCustMember.getTelphone() != null && !"".equals(bpCustMember.getTelphone())){
//														 vipsend.vIPRegistrationSend(bpCustMember.getTelphone());//注册后发送信息
													}else{
														System.out.println("没有注册手机号");
													}
												}
											}else{
												if("autoReg".equals(autoReg))
												{
													System.out.println("开始保存QQ或微博");
													//qq和微薄
													bpCustMemberService.save(bpCustMember);
													this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
													//wenanInsert(bpCustMember.getId());
													
												}
												if("proj_wenandai".equals(AppUtil.getProjStr())){
													if("autoReg".equals(autoReg))
													{
														
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":2");
														sb.append("}");
													}else
													{
														
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":1");
														sb.append("}");
													}
//													this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
												}else{
													MD5 md5 = new MD5();
													bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),md5.md5(password, "utf-8"));
													//isSendMail = validateMail(bpCustMember);
													if("autoReg".equals(autoReg))
													{
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":2");
														sb.append("}");
													}else
													{
														sb.append("{\"success\":true,\"data\":");
														sb.append(gson.toJson(bpCustMember));
														sb.append(",\"result\":1");
														sb.append("}");
													}
													
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
												//发送注册成功信息
												//svn：songwj
												if(bpCustMember.getTelphone() != null && !"".equals(bpCustMember.getTelphone())){
//													 vipsend.vIPRegistrationSend(bpCustMember.getTelphone());//注册后发送信息
												}else{
													System.out.println("没有注册手机号");
												}
											}
										}
										//this.getSession().setAttribute("isSendMail", isSendMail);//发送验证邮件是否成功
									
									}else{
										sb.append("{\"success\":true,\"errMsg\":");
										 sb.append(gson.toJson("部门推荐码不存在"));
											sb.append("}");
									}
								}else{
								sb.append("{\"success\":true,\"errMsg\":");
								 sb.append(gson.toJson("邀请码不存在"));
									sb.append("}");
								}	
							}else{
								sb.append("{\"success\":true,\"errMsg\":");
								 sb.append(gson.toJson("邮箱已经存在"));
									sb.append("}");
							}
						}else{
							sb.append("{\"success\":true,\"errMsg\":");
							 sb.append(gson.toJson("手机号码已经存在"));
								sb.append("}");
						}	
					}else{
						sb.append("{\"success\":true,\"errMsg\":");
						 sb.append(gson.toJson("用户名已经存在"));
							sb.append("}");
					}
				}
		}else{
			sb.append("{\"success\":true,\"errMsg\":");
			 sb.append(gson.toJson("验证码错误"));
				sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	
	@SuppressWarnings("unused")
	public String msign() throws Exception{
		 Gson gson = new Gson();
		 MD5 md5 = new MD5();
		 ValidateUtil vu = new ValidateUtil();
		 vu.addNotNull(this.getRequest().getParameter("username"),"用户名不能为空")
		 .addRegValidate(this.getRequest().getParameter("username"), "用户名长度为6~16个字符", "^[\\d\\w]{6,16}$")
		 .addRegValidate(this.getRequest().getParameter("username"), "用户名不能使用纯数字", "^(?!(?:\\d+)$)[\\da-zA-Z]{6,16}$")
		 .addNotNull(this.getRequest().getParameter("pass"),"密码不能为空")
		 .addRegValidate(this.getRequest().getParameter("pass"), "密码长度为6~16个字符", "^[\\d\\w]{6,16}$")
		 .addRegValidate(this.getRequest().getParameter("pass"), "两次密码不一致", "^"+this.getRequest().getParameter("word")+"$")
		 .addNotNull(this.getRequest().getParameter("telphone_reg_random"), "电话号码不能为空")
		 .addRegValidate(this.getRequest().getParameter("telphone_reg_random"), "电话号码非法", "^1[\\d]{10}$")
		 ;
		 
		 String result = vu.validate();
		 if(!"1".equals(result)){
			//this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileReg.ftl");
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson(result));
				sb.append("}");
					setJsonString(sb.toString());
				//setJsonString("{status:1,remark:\""+s+"\"}");
				//getRequest().setAttribute("meg", s);
				return SUCCESS;
		 }
		 
		 
		 BpCustMember bplogin = bpCustMemberService.isExist(this.getRequest().getParameter("username").toLowerCase().trim());
			if(bplogin!=null){
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("用户名已存在"));
				sb.append("}");
					setJsonString(sb.toString());
				//setJsonString("{status:1,remark:\""+s+"\"}");
				//getRequest().setAttribute("meg", s);
				return SUCCESS;
			}
			
			
			BpCustMember bpphone = bpCustMemberService.isExistPhone(this.getRequest().getParameter("telphone_reg_random"));
			if(bpphone!=null){//判断电话是否被注册
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("电话已被注册"));
				sb.append("}");
					setJsonString(sb.toString());
				//setJsonString("{status:1,remark:\""+s+"\"}");
				//getRequest().setAttribute("meg", s);
				return SUCCESS;
			}
		 
		
		if(!"1".equals(result)){
			//this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileReg.ftl");
			StringBuffer sb = new StringBuffer();
			sb.append("{\"success\":true,\"errMsg\":");
			sb.append(gson.toJson(result));
			sb.append("}");
				setJsonString(sb.toString());
			//setJsonString("{status:1,remark:\""+s+"\"}");
			//getRequest().setAttribute("meg", s);
			return SUCCESS;
		}
		System.out.println("注册-----"+this.QQ+"====="+this.sinawb);
		String autoReg=this.getRequest().getParameter("autoReg");
		System.out.println("autoReg=="+autoReg);
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		
		String projStr = AppUtil.getProjStr();
		//将数据转成JSON格式
		 gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		/*String val =  (String) getSession().getAttribute("telphone_reg_random");
		if(projStr.equals("proj_duorongyi")){
			val = "checkCode";
			checkCode = "checkCode";
		}else{
			val = val.toLowerCase();
			StringUtils.swapCase(val);
			checkCode  = checkCode.toLowerCase();
			StringUtils.swapCase(StringUtils.trim(checkCode));
		}
		
		
		if (val!=null && val.equals(checkCode)) {*/
				if(id ==null){
					
					List<BpCustMember> list1 = bpCustMemberService.getAll();
					
								String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
								/*if("proj_wenandai".equals(AppUtil.getProjStr())){
									phonecode = null;
								}else{
									phonecode = (String) this.getSession().getAttribute(
											MyUserSession.TELPHONE_REG_RANDOM_SESSION);
								}*/
								if (phonecode!=null&&!phonecode.equals(this.getRequest().getParameter("checkCode"))){
									sb.append("{\"success\":true,\"errMsg\":");
									sb.append(gson.toJson("短信验证码错误!"));
									sb.append("}");
										
								}else{
									bpCustMember = new BpCustMember();
									
									if(null!=custType){
										bpCustMember.setCustType(Short.valueOf(custType));
									}
									
									bpCustMember.setRegistrationDate(new Date());
											MD5_T md1 = new MD5_T();
											//bpCustMember.setPlainpassword(md1.md5_3(this.getRequest().getParameter("username").toLowerCase().trim()));
											bpCustMember.setOPType("1");
											bpCustMember.setIsCheckPhone("1");
											bpCustMember.setEmail(email);
											bpCustMember.setType(type);
											bpCustMember.setTelphone(this.getRequest().getParameter("telphone_reg_random"));
											bpCustMember.setLoginname(this.getRequest().getParameter("username").toLowerCase().trim());
											bpCustMember.setPassword(md5.md5(this.getRequest().getParameter("word"), "utf-8"));
											bpCustMember=bpCustMemberService.save(bpCustMember);
											//如果 为1 资金池模式 ThirdPayFlagId 设置为 客户id
											if(AppUtil.getSysConfig().get("thirdPayType").equals("1")){
												bpCustMember.setThirdPayFlagId(bpCustMember.getId().toString());
											}
											
											bpCustMember.setPlainpassword(bpCustMember.getId().toString());
											bpCustMember=bpCustMemberService.save(bpCustMember);
											//bpCustMember.setIsSync(Short.valueOf(type));
											
											bpCustMember = bpCustMemberService.login(this.getRequest().getParameter("username").toLowerCase(), md5.md5(this.getRequest().getParameter("word"), "utf-8"));
											//isSendMail = validateMail(bpCustMember);
											//wenanInsert(bpCustMember.getId());
											this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中

											//发送注册成功信息
											//svn：songwj
											/*if(bpCustMember.getTelphone() != null && !"".equals(bpCustMember.getTelphone())){
												 vipsend.vIPRegistrationSend(bpCustMember.getTelphone());//注册后发送信息
											}else{
												System.out.println("没有注册手机号");
											}*/
											//wenanInsert(bpCustMember.getId());
											sb.append("{\"success\":false,\"errMsg\":");
											sb.append(gson.toJson("注册成功"));
											sb.append("}");
											setJsonString(sb.toString());
									}
								}
								//this.getSession().setAttribute("isSendMail", isSendMail);//发送验证邮件是否成功
						
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	/**
	 * 添加认证记录
	 * @param id 注册用户的id
	 */
	private void wenanInsert(Long id){
		
		WebFinanceApplyUploadsService webFinanceApplyUploadsService=(WebFinanceApplyUploadsService)AppUtil.getBean("webFinanceApplyUploadsService");
		//16中认证
		String[] str = new String[]{"IDCard","CreditRecord","Income","WebShop","House","Vehicle","Marriage","Education","Career","JobTitle","MobilePhone","MicroBlog","Residence","CompanyPlace","CompanyRevenue","Teacher"};
		System.out.println("数组的长度="+str.length);
		/**
		添加认证记录
		 */
		for(int i=0;i<str.length;i++){
			WebFinanceApplyUploads webFinanceApplyUploads = new WebFinanceApplyUploads();
			webFinanceApplyUploads.setUserID(id);
			webFinanceApplyUploads.setFiles("");
			webFinanceApplyUploads.setStatus(0);
			webFinanceApplyUploads.setLastuploadtime(new Date());
			webFinanceApplyUploads.setMaterialstype(str[i]);
			webFinanceApplyUploadsService.save(webFinanceApplyUploads);
		}
	}
	
	public boolean validateMail(BpCustMember bpCustMember){
		String emailcode = CreateRandomRemate.createRandom(false, 8);// 生成验证码，需要放在session中
		bpCustMember.setIsCheckEmail(emailcode);// 将验证码先存放在IsCheckEmail中
		this.getSession().setAttribute(
				MyUserSession.EMAIL_REG_RANDOM_SESSION, emailcode);
		String loginname = bpCustMember.getLoginname();
		String password = bpCustMember.getPassword();
		String returnpath = getBasePath()
				+ "user/updateEmailTypeBpCustMember.do?emailcode="
				+ emailcode + "&loginname=" + loginname + "&password="
				+ password + "&email=" + email;

		boolean isSuccess = sendMail(returnpath, "===网站邮箱验证", email,bpCustMember);// 发送
		return isSuccess;
	}
	public boolean sendMail(String msgURL, String title, String email,BpCustMember user) {
		boolean ret = false;
		// 邮件实体
		MailModel mode = new MailModel();
		// 系统配置
		SystemConfig con = getSystemConfig();
		// 用户

		// 激活URL 地址
		con.setSite(msgURL);// 加入发送的url地址
		// 邮件模版需要的数据
		Map<String, Object> mailData = new HashMap<String, Object>();
		mailData.put("user", user);
		mailData.put("systemConfig", con);
		mode.setMailData(mailData);
		// 邮件标题
		mode.setSubject(con.getMetaTitle() + "======" + title);
		mode.setTo(email);
		// 邮件发送类
		MailMessageConsumer mailMessage = new MailMessageConsumer();
		// 发送
		try {
			mailMessage.sendMail(mode);
			ret = true;
		} catch (Exception e) {

		}
		return ret;
	}
	@SuppressWarnings("unused")
	private boolean validateUser(BpCustMember bpCustMember,StringBuffer sb){
		
		return false;
	}
	
	//注册第二步，邮箱认证
	public String email() {
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			QueryFilter filter1 = new QueryFilter();
			filter1.addFilter("Q_p2pCustId_L_EQ", mem.getId());
			List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
			if(relationlist.size()>0 && !"".equals(relationlist.get(0).getOfflineCustType())){
				if(relationlist.get(0).getOfflineCustType().equals("b_guarantee")){
					this.getRequest().setAttribute("isGarantee", "1");
				}
			}
			this.getRequest().setAttribute("type","2");
			this.getRequest().setAttribute("toAction", this.getRequest().getParameter("action"));
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EMAILAUTHENTICATION).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}


	//安卓邮箱认证
	public String appEmail() {
		MobileDataResultModel model = new MobileDataResultModel();
		String isApp = this.getRequest().getParameter("isApp");
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){

			//p2p用户和ERP借款人关xi
			if("1".equals(isApp)){
				this.getRequest().setAttribute("type","2");
				this.getRequest().setAttribute("toAction", this.getRequest().getParameter("action"));
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			this.getRequest().setAttribute("type","2");
			this.getRequest().setAttribute("toAction", this.getRequest().getParameter("action"));
			this.getRequest().setAttribute("Bpcustmember",mem);
			return "bindEmail";
		}else{
			if("1".equals(isApp)){
				model.setCode(MobileErrorCode.SERVICE_ERROR);
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			return "login";
		}
	}


	/**
	 * 平台用户开通第三方支付功能：
	 * 开通第三方支付逻辑：
	 * 1.开户根据用户信息customerType区分企业户和个人户；0个人客户,1企业客户,2担保客户，其中担保户和企业客户
	 * 开通第三方支付前要完成手机号认证
	 * @return
	 */
	public String third() {
		String mobile=null;
		mobile=this.getRequest().getParameter("mobile");
		try{
			BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(mem!=null){
				mem=bpCustMemberService.get(mem.getId());
				if(mem.getTelphone()==null||mem.getTelphone().equals("")){
					webMsgInstance("0", Constants.CODE_FAILED, "opentelePthone",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					bpCustMember=bpCustMemberService.get(mem.getId());
					if(bpCustMember.getCustomerType()!=null&&bpCustMember.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//企业用户
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.THIRDPAY_ENTERPRISE).getTemplateFilePath());
					}else{
						/*if(configMap.get("thirdPayConfig").toString().equals(Constants.FUIOU)&&configMap.get("thirdPayType").toString().equals("0")){
							List<WebBankcard> Card =webBankCardService.getBycusterId(mem.getId());
							if(Card!=null&&Card.size()>0){
								if(Card.size()==1){
									fuiou=fuiouService.webRegister(mem, this.getBasePath(), this.getRequest(),Card.get(0));
									if(mobile!=null&&"mobile".equals(mobile)){
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEYB).getTemplateFilePath());
									}else{
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.THIRDPAY).getTemplateFilePath());
									}
								}else{
									webMsgInstance("0", Constants.CODE_FAILED, "只能绑定一张银行卡",  "", "", "", "", "");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								}
							}else{
								fuiou=fuiouService.webRegister(mem, this.getBasePath(), this.getRequest(),null);
							}
						}else{*/
							if(mobile!=null&&"mobile".equals(mobile)){
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEYB).getTemplateFilePath());
							}else{
								//判断是在平台认证还是在开通第三方认证
								 Object[] thirdType=new Object[2];
								 thirdType = ThirdPayInterfaceUtil.checkThirdType(ThirdPayConstants.BT_RECHAGE);
								 if(thirdType!=null&&thirdType[0].equals("1")){
									 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONAUTHENTICATE).getTemplateFilePath());
								 }else{
									 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.THIRDPAY).getTemplateFilePath());
								 }
							}
						//}
						
					}
			  }
				 
			}else{
				if(mobile!=null&&"mobile".equals(mobile)){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILELOGIN).getTemplateFilePath());
				}else{
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "报错了",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		
		return "freemarker";
	}
	/**
	 * 手机验证
	 * @return
	 */
	public String tel(){
		System.out.println("手机验证页面------------");
		//this.getRequest().setAttribute("id", this.getRequest().getParameter("id"));
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TEL).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 注册成功
	 * @return
	 */
	public String succReg(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.REGSUCCESS).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 新浪微博快速登录
	 */
	public void autoSina(){
		System.out.println("已进入autoSina新浪微博");
		try {  
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			String str=getValue("authorizeURL").trim() + "?client_id="+ getValue("client_ID").trim() + "&redirect_uri="+ getValue("redirect_URI").trim()+ "&response_type=code";
			System.out.println("新浪url"+str);
			this.getResponse().sendRedirect(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}
	/**
	 * 新浪
	 * @return
	 */
	public String autoSinaPage(){
		bpCustMember=new BpCustMember();
		System.out.println("已进入autoSinaPage方法：");
		String code=this.getRequest().getParameter("code");
		System.out.println("code值："+code);
		org.apache.commons.httpclient.HttpClient client = null;
		System.out.println("client_id"+getValue("client_ID"));
		System.out.println("client_SERCRET"+getValue("client_SERCRET"));
		PostParameter[] parameter=new PostParameter[]{
				new PostParameter("client_id", getValue("client_ID")),
				new PostParameter("client_secret", getValue("client_SERCRET")),
				new PostParameter("grant_type", "authorization_code"),
				new PostParameter("code", code),
				new PostParameter("redirect_uri", getValue("redirect_URI"))};
		
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClientParams clientParams = new HttpClientParams();
		client = new org.apache.commons.httpclient.HttpClient(clientParams,connectionManager);
		
		PostMethod postMethod = new PostMethod(getValue("accessTokenURL"));
		for (int i = 0; i < parameter.length; i++) {
			postMethod.addParameter(parameter[i].getName(), parameter[i].getValue());
		}
		HttpMethodParams param = postMethod.getParams();
		param.setContentCharset("UTF-8");
		try {
			client.executeMethod(postMethod);
			System.out.println("新浪用户信息："+postMethod.getResponseBodyAsString());
			String str=postMethod.getResponseBodyAsString().replace("}", "");
			System.out.println("去掉右括号："+str);
			String[] strOld=str.split(",");
			String uid=strOld[strOld.length-1].replace("\"", "");
			System.out.println("uid"+uid);
			String[] userid=uid.split(":");
			String newUserId=userid[userid.length-1];//新浪微博的用户id
			System.out.println("用户id:"+newUserId);
			 BpCustMember mem=bpCustMemberService.getBySina(newUserId);
			 if(mem!=null){
            	 System.out.println("将要登录操作");
            	 //登录
            	bpCustMember=bpCustMemberService.get(mem.getId());
            	 this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
            	 String url=this.getBasePath();
            	 this.getResponse().sendRedirect(url);
             }else{
            	 System.out.println("将要添加操作，用户id:"+newUserId);
            	 bpCustMember.setSinawb(newUserId);
            	 //添加
            	 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AUTOREG).getTemplateFilePath());
             }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "freemarker";
	}
	
	/**
	 * 绑定QQ与新浪微博
	 * @returnre
	 */
	public String BindJudge(){
		String loginName=this.getRequest().getParameter("logName");
		System.out.println("用户名"+loginName);
		String loginPwd=this.getRequest().getParameter("pwd");
		System.out.println("密码"+loginPwd);
		//QQ
		String qq=this.getRequest().getParameter("qq");
		System.out.println("qq："+qq);
		//新浪微博
		String sinaID=this.getRequest().getParameter("sinaID");
		System.out.println("sina:"+sinaID);
		MD5 md5 = new MD5();
		String pwd=md5.md5(loginPwd, "utf-8");
		 bpCustMember=bpCustMemberService.login(loginName, pwd);
		StringBuffer sb = new StringBuffer();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		sb.append("{\"success\":true,\"Msg\":");
		if(bpCustMember==null){
			System.out.println("用户名或密码有误");
			sb.append(gson.toJson("0"));
		}else{
			if(!"".equals(qq)&&!"undefined".equals(qq)){//QQ认证
				System.out.println("开通QQ认证");
				bpCustMember.setQq(qq);
				bpCustMember.setIsCheckQQ("1");
			}
			if(!"".equals(sinaID)&&!"undefined".equals(sinaID)){//微博认证
				System.out.println("开通微博认证");
				bpCustMember.setSinawb(sinaID);
				bpCustMember.setIsCheckSinaWB("1");
			}
			sb.append(gson.toJson("1"));
			bpCustMemberService.save(bpCustMember);
			this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session
		}
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	/**
	 * 新版注册方法--注册页面验证手机验证码是否正确
	 * 验证码方法目前是手机号加验证码，存放session中的验证码也是手机号加验证码
	 * @return
	 */
	public String checkPhoneCode(){
		StringBuffer sb = new StringBuffer();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		sb.append("{\"success\":true,\"Msg\":");
		try{
			/**
			 * session中存放注册手机验证码
			 */
			String sessioncode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
			String[] validation=AppSmsUtil.checkCode(telphone,checkCode,sessioncode);
			sb.append(gson.toJson(validation[1]));
			if(validation[0].equals(Constants.CODE_SUCCESS)){
				sb.append(",\"result\":1");
			}else if(validation[0].equals(Constants.CODE_FAILED)){
				sb.append(",\"result\":2");//验证失败
			}else{
				sb.append(",\"result\":3");
			}
		}catch(Exception e){
			e.printStackTrace();
			sb.append(gson.toJson("验证出错"));
			sb.append(",\"result\":2");
			
		}
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * P2P用户新的注册页面保存方法
	 * @return
	 */
	public String newsign(){
		String customerType=this.getRequest().getParameter("custType");//用户类型（企业户和个人户）
		String isMobile=this.getRequest().getParameter("isMobile");
		String retUrl1="thirdreg.do";
		/**
		 * session中存放注册手机验证码
		 */
		if(telphone!=null&&!"".equals(telphone)){
			customerType = BpCustMember.CUSTOMER_PERSON.toString();
		}else{
			customerType = BpCustMember.CUSTOMER_ENTERPRISE.toString();
		}
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		String sessioncode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
		String newphone = !"".equals(telphone)?telphone:telphone_qy;
		
		String[] validation=AppSmsUtil.checkCode(newphone,checkCode,sessioncode);
		if(validation[0].equals(Constants.CODE_SUCCESS)){//先判断验证码是否正确
			if(customerType!=null&&""!=customerType){
				BpCustMember bpCustMember=new BpCustMember();
				bpCustMember.setLoginname(loginname);//登陆用户名（没有处理的用户名）
				MD5 md5 = new MD5();
				bpCustMember.setPassword(md5.md5(password, "utf-8"));//密码加密
				bpCustMember.setTelphone(telphone);//没有处理过的手机号码
				bpCustMember.setRecommandPerson(recommandPerson);//没有处理过的推荐人邀请码
				if(StringUtils.isNotEmpty(recommand)){
					bpCustMember.setRecommandPerson(recommand);
				}
				if(departmentRecommend!=null){
					bpCustMember.setDepartmentRecommend(departmentRecommend);
				}
				bpCustMember.setCustomerType(Integer.valueOf(customerType));//用户类型
				if(customerType.equals(BpCustMember.CUSTOMER_PERSON.toString())){//个人用户注册
					Object[] ret=bpCustMemberService.savePersonCustomer(bpCustMember);
					if(ret[0].equals(Constants.CODE_SUCCESS)){//注册成功后操作
						retUrl1="user/getBpCustMember.do";
						//注册成功后登陆存放session值
						this.registerLogin(loginname,password);
						
						sb.append("{\"success\":true,\"data\":");
						sb.append(gson.toJson(bpCustMember));
						sb.append(",\"result\":1");
						sb.append("}");
						//注册成功后开通虚拟账户
						String[] a =obSystemAccountService.saveAccount( bpCustMember.getId().toString(), "0");
						logger.info("调用生成虚拟账户方法结果：a[0]="+a[0]+"; a[1]："+a[1]);
						//TOOD  注册成功后发送短信
						webMsgInstance("0", Constants.CODE_SUCCESS, "注册成功：请前往个人中心完成后续操作",  "", "", "", "", "");
					}else{
						sb.append("{\"success\":false,\"errMsg\":");
						sb.append(gson.toJson("注册失败："+ret[1].toString()));
						sb.append("}");
						webMsgInstance("0", Constants.CODE_FAILED, "注册失败："+ret[1].toString(),  "", "", "", "", "");
					}
				}else if(customerType.equals(BpCustMember.CUSTOMER_ENTERPRISE.toString())){//企业用户类型
					bpCustMember.setTruename(this.getRequest().getParameter("truename"));//企业名称
					bpCustMember.setCardcode(this.getRequest().getParameter("cardcode"));//没有处理过的组织机构代码
					bpCustMember.setContactPerson(this.getRequest().getParameter("contactPerson"));//没有处理过的联系人姓名
					bpCustMember.setTelphone(telphone_qy);
					String entCompanyType=this.getRequest().getParameter("entCompanyType");
					if(null!=entCompanyType && !"".equals(entCompanyType)){
						bpCustMember.setEntCompanyType(Short.valueOf(entCompanyType));
					}
					Object[] ret=bpCustMemberService.saveEnterpriseCustomer(bpCustMember);
					if(ret[0].equals(Constants.CODE_SUCCESS)){ //注册成功后操作
						String[] a =obSystemAccountService.saveAccount( bpCustMember.getId().toString(), "0");
						retUrl1="user/getBpCustMember.do";
						//注册成功后登陆存放session值
						this.registerLogin(loginname,password);
						//TOOD  注册成功后发送短信
						webMsgInstance("0", Constants.CODE_SUCCESS, "注册成功：请前往个人中心完成后续操作",  "", "", "", "", "");
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "注册失败："+ret[1].toString(),  "", "", "", "", "");
					}
				}else{//位置用户类型
					webMsgInstance("0", Constants.CODE_FAILED, "注册失败：未知注册用户类型",  "", "", "", "", "");
				}
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, "注册失败：注册客户类型不能为空",  "", "", "", "", "");
			}
		}else{
			sb.append("{\"success\":false,\"errMsg\":");
			sb.append(gson.toJson("注册失败：手机验证码不正确"));
			sb.append("}");
			webMsgInstance("0", Constants.CODE_FAILED, "注册失败：手机验证码不正确",  "", "", "", "", "");
		}
		
		this.getSession().setAttribute("retUrl", retUrl1);
		if(null!=isMobile&&isMobile.endsWith("1")){
			jsonString = sb.toString();
//			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
			return SUCCESS;
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 注册成功后登陆页面
	 * @param loginname
	 * @param password
	 */
	private void registerLogin(String loginname, String password) {
		// TODO Auto-generated method stub
		try{
			MD5 md5 = new MD5();
//			bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),md5.md5(password, "utf-8"));
			bpCustMember = bpCustMemberService.login(loginname,md5.md5(password, "utf-8"));
			//TOOD  注册成功后奖励:活动中心管理
			BpCustMember bprecommandPerson = bpCustMemberService.isRecommandPerson(bpCustMember.getRecommandPerson());
			if(bprecommandPerson!=null){
				bpActivityManageService.autoDistributeEngine("2", bprecommandPerson.getId().toString(),null);
			}
			bpActivityManageService.autoDistributeEngine("1", bpCustMember.getId().toString(),null);
			
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
			if (AppUtil.getConfigMap().containsKey("if_test") && !"111111".equals(AppUtil.getConfigMap().get("if_test").toString())){
				//注册成功发送短信、邮件、站内信。
				Map<String, String> map = new HashMap<String, String>();
				map.put("key", "sms_SignSuccess");
				map.put("userId",bpCustMember.getId().toString());
				map.put("${name}", bpCustMember.getLoginname());
				String result =  sendMesService.sendSmsEmailMessage(map);
			}

			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("注册后登陆失败");
		}
		
	}
	public void test(){
		//发送短信、邮件、站内信
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "sms_SignSuccess");
		map.put("${code}", "111111");
		map.put("telphone", "15801270856");
		map.put("email","137717070@qq.com");
		map.put("userId", "603");
		String result =  sendMesService.sendSmsEmailMessage(map);
	}
	/**
	 * QQ登陆跳转到qq页面的方法
	 * @return
	 * @throws IOException
	 */
	public String qqLogin() throws IOException{
		 try {
			this.getResponse().sendRedirect(new Oauth().getAuthorizeURL(this.getRequest()));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return "freemarker";
	}
	/**
	 * QQ登陆回调访问的方法
	 * 处理思路：已经绑定的直接登录，没有绑定平台p2p账号的强制让他绑定手机号
	 * @return
	 * @throws IOException
	 */
	public String afterQQLogin() throws IOException{
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(this.getRequest());

            String accessToken   = null,openID = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
                webMsgInstance("0", Constants.CODE_FAILED, "登陆失败：不能连接到QQ互联。。",  "", "", "", "", "");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                this.getRequest().getSession().setAttribute("demo_access_token", accessToken);
                this.getRequest().getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                System.out.println("欢迎你，代号为 " + openID + " 的用户!");

                    if(!"".equals(bpCustMember.getQq())){
                        BpCustMember mem=bpCustMemberService.getByQQ(bpCustMember.getQq());
                         if(mem!=null){
                        	 System.out.println("将要登录操作");
                        	 bpCustMember=bpCustMemberService.get(mem.getId());
                        	 this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
                        	 String url=this.getBasePath();
                        	 this.getResponse().sendRedirect(url);
                         }else{
                        	 System.out.println("将要添加操作");
                             QQNickname=openID;
                        	 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AUTOREG).getTemplateFilePath());
                         }
                    }
                
            }
        } catch (QQConnectException e) {
        }

   	 return "freemarker";
	}
	
}