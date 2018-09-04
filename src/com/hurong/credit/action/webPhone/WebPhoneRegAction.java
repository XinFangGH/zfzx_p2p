package com.hurong.credit.action.webPhone;
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

public class WebPhoneRegAction  extends BaseAction{

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

     * app用户新的注册页面保存方法
     * @return
     */
    public String newsign(){
        MobileDataResultModel model = new MobileDataResultModel();
        //获取参数信息
        String telphone = this.getRequest().getParameter("telPhone");
        String code = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        String checkCode = this.getRequest().getParameter("checkCode");
        String password = this.getRequest().getParameter("passWord");
        String recommand = this.getRequest().getParameter("recommand");
        //        获取是否是APP
       String isApp= this.getRequest().getParameter("isApp");
//        if(!"1".equals(isApp)){
//            return "register";
//        }

//        String customerType=this.getRequest().getParameter("custType");//用户类型（企业户和个人户）
        /**
         * session中存放注册手机验证码
         */
//        String sessioncode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);

        String newphone = telphone;
        String[] validation= AppSmsUtil.checkCode(newphone,checkCode,code);

        if(validation[0].equals(Constants.CODE_SUCCESS)){ //先判断验证码是否正确
                BpCustMember bpCustMember=new BpCustMember();
                bpCustMember.setLoginname(telphone);//登陆用户名（没有处理的用户名）
                MD5 md5 = new MD5();
                bpCustMember.setPassword(md5.md5(password, "utf-8"));//密码加密
                bpCustMember.setTelphone(telphone);//没有处理过的手机号码
                bpCustMember.setRecommandPerson(recommandPerson);//没有处理过的推荐人邀请码
                if(org.apache.commons.lang3.StringUtils.isNotEmpty(recommand)){
                    bpCustMember.setRecommandPerson(recommand);
                }
                    //人用户注册
                    Object[] ret=bpCustMemberService.savePersonCustomer(bpCustMember);
                    if(ret[0].equals(Constants.CODE_SUCCESS)){ //注册成功后操作
                        //注册成功后登陆存放session值
                        this.registerLogin(telphone,password);
                        //注册成功后开通虚拟账户
                        String[] a =obSystemAccountService.saveAccount( bpCustMember.getId().toString(), "0");
                        logger.info("调用生成虚拟账户方法结果：a[0]="+a[0]+"; a[1]："+a[1]);
                        //TOOD  注册成功后发送短信
                            model.setMsg("注册成功");
                            model.setCode(MobileErrorCode.SUCCESS);
                            model.addDataContent("bpCustMember",bpCustMember);
                    }else{
                            model.setCode(MobileErrorCode.FAILED);
                            model.setMsg("注册失败:" + ret[1].toString());
                    }
        }else{
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("注册失败：手机验证码不正确");
        }
        setJsonString(model.toJSON());
        return SUCCESS;
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
            //注册成功发送短信、邮件、站内信。
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", "sms_SignSuccess");
            map.put("userId",bpCustMember.getId().toString());
            map.put("${name}", bpCustMember.getLoginname());
            String result =  sendMesService.sendSmsEmailMessage(map);

        }catch(Exception e){
            e.printStackTrace();
            logger.error("注册后登陆失败");
        }

    }

    public String appThird() {
        String mobile=null;
        MobileDataResultModel model = new MobileDataResultModel();
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
                        model.setMsg("该用户为企业用户");
                        setJsonString(model.toJSON());
                        return SUCCESS;
                    }else{
                        if(mobile!=null&&"mobile".equals(mobile)){
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEYB).getTemplateFilePath());
                        }else{
                            //判断是在平台认证还是在开通第三方认证
                            Object[] thirdType=new Object[2];
                            thirdType = ThirdPayInterfaceUtil.checkThirdType(ThirdPayConstants.BT_RECHAGE);
                            if(thirdType!=null&&thirdType[0].equals("1")){
                                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONAUTHENTICATE).getTemplateFilePath());
                            }else{
                                model.setMsg("认证成功");
                                setJsonString(model.toJSON());
                                return SUCCESS;
                                //this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.THIRDPAY).getTemplateFilePath());
                            }
                        }
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
            model.setMsg("报错了");
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        model.setMsg("报错了");
        model.setCode(MobileErrorCode.SERVICE_ERROR);
        setJsonString(model.toJSON());
        return SUCCESS;
    }

    public String appThirdRem(){
        BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        MobileDataResultModel model = new MobileDataResultModel();
        if(mem!=null){
            mem=bpCustMemberService.get(mem.getId());
            if(mem.getTelphone()!=null || !mem.getTelphone().equals("")){
                bpCustMemberService.updateTC(mem.getTelphone());
                model.setCode(MobileErrorCode.SUCCESS);
                setJsonString(model.toString());
                return SUCCESS;
            }else {
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("手机号验证失败，请重试！");
                setJsonString(model.toString());
                return SUCCESS;
            }
        }
        model.setMsg("登录已过期，请重新登录！");
        model.setCode(MobileErrorCode.SERVICE_ERROR);
        setJsonString(model.toString());
        return SUCCESS;
    }

    public String thirdPayBind(){
        return "open_bankDepository";
    }

    //升升投服务协议
    public String service_agreement(){
        return "service_agreement";
    }

}
