package com.hurong.credit.action.webPhone;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.mobile.InviteDetail;
import com.hurong.credit.model.mobile.InvitePersonDetail;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.BankCard;
import com.hurong.credit.model.webFeedBack.FeedBack;
import com.hurong.credit.service.activity.BpActivityManageService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.FileForm.FileFormService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.SlFundIntentService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidSaleService;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlKeepCreditlevelService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.creditFlow.log.UserloginlogsService;
import com.hurong.credit.service.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordService;
import com.hurong.credit.service.creditFlow.smallLoan.project.SlSmallloanProjectService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.idcard.IdCardService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.p2p.PlatDataPublishService;
import com.hurong.credit.service.p2p.loan.P2pLoanBasisMaterialService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.system.GlobalTypeService;
import com.hurong.credit.service.system.product.DictionaryService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.CsDicAreaDynamService;
import com.hurong.credit.sms.MessageStrategy;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MyUserSession;
import com.sms.SmsService;
import org.apache.struts2.ServletActionContext;
import org.springframework.jdbc.UncategorizedSQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebPhoneCustMemberAction extends BaseAction {
    @Resource
    private PlKeepCreditlevelService plKeepCreditlevelService;
    @Resource
    private BpCustMemberService bpCustMemberService;
    @Resource
    private BpCustRelationService bpCustRelationService;
    @Resource
    private WebBankcardService webBankcardService;
    @Resource
    private BpFinanceApplyUserService bpFinanceApplyUserService;
    @Resource
    private BpFundProjectService bpFundProjectService;
    @Resource
    private SlFundIntentService slFundIntentService;
    @Resource
    private IdCardService idCardService;
    @Resource
    private BpFinanceApplyUserService financeApplyUserService;
    @SuppressWarnings("unused")
    @Resource
    private IPayService iPayService;
    @Resource
    private ObSystemAccountService obSystemAccountService;
    @Resource
    private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
    @Resource
    private BpFundIntentService bpFundIntentService;
    @Resource
    private SmsService smsService;
    @SuppressWarnings("unused")
    @Resource
    private SlEarlyRepaymentRecordService slEarlyRepaymentRecordService;
    @SuppressWarnings("unused")
    @Resource
    private MessageStrategy cYJRMsgStrategy;
    @SuppressWarnings("unused")
    @Resource
    private MessageStrategy dJMsgStrategy;
    @Resource
    private MessageStrategy sxtMessageStrategy;//调用商讯通短信接口
    @Resource
    private MessageStrategy yzyxMessageStrategyImpl;//宇展盈讯短信接口
    @Resource
    private UserloginlogsService userloginlogsService;//日志接口
    @Resource
    private FuiouService fuiouService;
    @SuppressWarnings("unused")
    @Resource
    private SlSmallloanProjectService slSmallloanProjectService;
    @Resource
    private PlBidAutoService plBidAutoService;
    @Resource
    private PlBidInfoService plBidInfoService;
    @Resource
    private PlBidPlanService plBidPlanService;
    @Resource
    private GlobalTypeService globalTypeService;
    @Resource
    private CsDicAreaDynamService csDicAreaDynamService;
    @Resource
    private DictionaryService dictionaryService;
    private Pager pagerLoan;//已还款管理
    @Resource
    private WebBankcardService webBankcardservice;
    @Resource
    private OaNewsMessageService oaNewsMessageService;
    @Resource
    private BpActivityManageService bpActivityManageService;
    @Resource
    private P2pLoanBasisMaterialService p2pLoanBasisMaterialService;
    @Resource
    private OpraterBussinessDataService opraterBussinessDataService;
    @Resource
    private PlatDataPublishService platDataPublishService;
    @Resource
    private PlBidCompensatoryService plBidCompensatoryService;
    @Resource
    private PlManageMoneyPlanService plManageMoneyPlanService;
    @Resource
    private PlMmOrderAssignInterestService plMmOrderAssignInterestService;
    @Resource
    private SendMesService sendMesService;
    @Resource
    private PlWebShowMaterialsService plWebShowMaterialsService;
    @Resource
    private BpCouponsService bpCouponsService;
    @Resource
    private FileFormService fileFormService;
    @Resource
    private ArticleService articleService;
    @Resource
    private PlBidSaleService plBidSaleService;

    private List<P2pLoanBasisMaterial> basisMaterialList;
    private List<BpFundIntent> fundIntent;
    private List<FundPay> fundIntentpay;
    private Userloginlogs userloginlogs;//登录日志
    private String regType;
    private String typeValue;
    private PlBidAuto bidAuto;
    private String typeTopay;
    private List<Dictionary> listCompanysize;
    private BpPersonCenterData bpPersonCenterData;
    List<ObAccountDealInfo> listcount;
    private String from;
    private String mark;//mark=模块名+"."+ID  唯一标识！
    private String path;
    private File atvatar_image;

    public File getAtvatar_image() {
        return atvatar_image;
    }

    public void setAtvatar_image(File atvatarImage) {
        atvatar_image = atvatarImage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static final String uploadPath = "attachFiles/uploads";
    private File myUpload;
    private FileForm fileinfo;

    public FileForm getFileinfo() {
        return fileinfo;
    }

    public void setFileinfo(FileForm fileinfo) {
        this.fileinfo = fileinfo;
    }

    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public File getMyUpload() {
        return myUpload;
    }

    public void setMyUpload(File myUpload) {
        this.myUpload = myUpload;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    private BpCustMember bpCustMember;

    private String recommandPerson;//推荐码

    //短地址
    private String plainURL = "https://www.baidu.com";
    private static final String shortUrlInterface = "";
    public static final String ROOT = "attachFiles\\uploads\\";

    public String getPlainURL() {
        return plainURL;
    }

    public void setPlainURL(String plainURL) {
        this.plainURL = plainURL;
    }

    public BpCustMember getBpCustMember() {
        return bpCustMember;
    }

    public void setBpCustMember(BpCustMember bpCustMember) {
        this.bpCustMember = bpCustMember;
    }

    public List<ObAccountDealInfo> getListcount() {
        return listcount;
    }

    public void setListcount(List<ObAccountDealInfo> listcount) {
        this.listcount = listcount;
    }

    public Pager getPagerLoan() {
        return pagerLoan;
    }

    public void setPagerLoan(Pager pagerLoan) {
        this.pagerLoan = pagerLoan;
    }

    public List<FundPay> getFundIntentpay() {
        return fundIntentpay;
    }

    public void setFundIntentpay(List<FundPay> fundIntentpay) {
        this.fundIntentpay = fundIntentpay;
    }

    public String getTypeTopay() {
        return typeTopay;
    }

    public void setTypeTopay(String typeTopay) {
        this.typeTopay = typeTopay;
    }

    public List<Dictionary> getListCompanysize() {
        return listCompanysize;
    }

    public void setListCompanysize(List<Dictionary> listCompanysize) {
        this.listCompanysize = listCompanysize;
    }

    public PlBidAuto getBidAuto() {
        return bidAuto;
    }

    public void setBidAuto(PlBidAuto bidAuto) {
        this.bidAuto = bidAuto;
    }

    private WebFinanceApplyUploads webFinanceApplyUploads;

    private List<WebFinanceApplyUploads> webFinanceApplylist;
    @Resource
    private WebFinanceApplyUploadsService webFinanceApplyUploadService;


    public List<P2pLoanBasisMaterial> getBasisMaterialList() {
        return basisMaterialList;
    }

    public void setBasisMaterialList(List<P2pLoanBasisMaterial> basisMaterialList) {
        this.basisMaterialList = basisMaterialList;
    }

    public List<BpFundIntent> getFundIntent() {
        return fundIntent;
    }

    public void setFundIntent(List<BpFundIntent> fundIntent) {
        this.fundIntent = fundIntent;
    }

    public Userloginlogs getUserloginlogs() {
        return userloginlogs;
    }

    public void setUserloginlogs(Userloginlogs userloginlogs) {
        this.userloginlogs = userloginlogs;
    }

    public List<WebFinanceApplyUploads> getWebFinanceApplylist() {
        return webFinanceApplylist;
    }

    public void setWebFinanceApplylist(
            List<WebFinanceApplyUploads> webFinanceApplylist) {
        this.webFinanceApplylist = webFinanceApplylist;
    }

    //得到config.properties读取的所有资源
    @SuppressWarnings("rawtypes")
    private static Map configMap = AppUtil.getConfigMap();
    private List<PlBidPlan> bidPlanFinancial;
    private List<PlBidPlan> bidPlanLoan;

    private List<BpFundProject> fundList;
    private List<Investproject> listBp;
    private BpFinanceApplyUser financeApplyUser;
    private List<BpFinanceApplyUser> listApplyUser;
    private int typ;
    private String paymentCode;// 支付密码
    protected String loginname;// 登录名
    protected String uid;// 账号id
    protected String truename;// 真实姓名
    protected String password;// 密码
    protected String plainpassword;// 密码（加密）
    protected String telphone;// 手机号码
    protected String email;// 邮箱
    protected Integer type;// 类型：企业，个人
    protected Integer sex;// 性别
    protected Integer cardtype;// 证件类型
    protected String cardcode;// 证件号码
    protected java.util.Date birthday;// 出生日期
    protected String headImage;// 头像
    protected String nativePlaceProvice;// 籍贯省
    protected String nativePlaceCity;// 籍贯市
    protected String nation;// 民族
    protected String homePhone;// 家庭电话
    protected String relationAddress;// 联系地址
    protected String postCode;// 邮编
    protected String QQ;
    protected String MSN;
    protected String securityQuestion;// 密码保护问题
    protected String securityAnswer;// 密码保护答案
    protected Integer roleId;// 角色ID
    protected java.util.Date registrationDate;// 注册时间
    protected Long liveProvice;// 居住城市省
    protected Long liveCity;// 居住城市-市
    protected Integer marry;// 婚姻状况
    protected String teacherPosition;
    protected String fax;// 传真
    protected Long memberOrderId;// 会员等级
    protected String oldpassword;// 旧密码
    protected String checkCode;// 验证码
    protected String emailcode;// 返回的邮箱验证码
    protected String verify_sms;// 手机验证码
    protected Object isSendMail;//发送邮件是否成功
    private List<WebBankcard> listBankCard;
    protected String emailName;// email @之前的内容
    protected String emailNameAfter;// email @之后的内容

    private Integer havechildren;
    private String collegeDegree;
    private String collegeYear;
    private String collegename;
    private String relDirName;
    private String relDirType;
    private String relDirPhone;
    private String relOtherName;
    private String relOtherType;
    private String relOtherPhone;
    private String relFriendName;
    private String relFriendType;
    private String relFriendPhone;
    private Integer careerType;
    private String webshopName;
    private java.math.BigDecimal webshopMonthlyincome;
    private String webshopEmail;
    private String webshopProvince;
    private String webshopCity;
    private String webshopAddress;
    private String webshopStartyear;
    private String webshopPhone;
    private String hireCompanyname;
    private String hirePosition;
    private java.math.BigDecimal hireMonthlyincome;
    private String hireEmail;
    private String hireProvince;
    private String hireCity;
    private String hireAddress;
    private String hireCompanytype;
    private String hireCompanycategory;
    private String hireCompanysize;
    private String hireStartyear;
    private String hireCompanyphone;
    private Integer havehouse;
    private Integer havehouseloan;
    private Integer havecar;
    private Integer havecarloan;
    private java.math.BigDecimal money1 = new BigDecimal(0);//借款管理--逾期金额
    private java.math.BigDecimal money2 = new BigDecimal(0);//借款管理--待还金额
    private java.math.BigDecimal money3 = new BigDecimal(0);//借款管理--近30天应还金额
    private java.math.BigDecimal loanTotal = new BigDecimal(0);//借款管理--借款总金额------稳安总览---借款金额
    private java.math.BigDecimal loanTotalTen = new BigDecimal(0);//借款管理--近10天内需还金额
    private java.math.BigDecimal investMoney = new BigDecimal(0);//稳安总览---投资金额
    private java.math.BigDecimal investLoanMoneytotal = new BigDecimal(0);//稳安总览---资产总额
    private Integer abc;

    private java.math.BigDecimal bidMoney;
    private Integer interestStart;
    private Integer interestEnd;
    private Integer periodStart;
    private Integer periodEnd;
    private String rateStart;
    private String rateEnd;
    private java.math.BigDecimal keepMoney;
    private Integer isOpen;
    private Integer banned;//是否禁用 0 禁用 1开启
    private java.util.Date orderTime;
    @Resource
    private ObAccountDealInfoService obAccountDealInfoService;

    public Integer getBanned() {
        return banned;
    }

    public void setBanned(Integer banned) {
        this.banned = banned;
    }

    public java.math.BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(java.math.BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Integer getInterestStart() {
        return interestStart;
    }

    public void setInterestStart(Integer interestStart) {
        this.interestStart = interestStart;
    }

    public Integer getInterestEnd() {
        return interestEnd;
    }

    public void setInterestEnd(Integer interestEnd) {
        this.interestEnd = interestEnd;
    }

    public Integer getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Integer periodStart) {
        this.periodStart = periodStart;
    }

    public Integer getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Integer periodEnd) {
        this.periodEnd = periodEnd;
    }

    public String getRateStart() {
        return rateStart;
    }

    public void setRateStart(String rateStart) {
        this.rateStart = rateStart;
    }

    public String getRateEnd() {
        return rateEnd;
    }

    public void setRateEnd(String rateEnd) {
        this.rateEnd = rateEnd;
    }

    public java.math.BigDecimal getKeepMoney() {
        return keepMoney;
    }

    public void setKeepMoney(java.math.BigDecimal keepMoney) {
        this.keepMoney = keepMoney;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public java.util.Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getAbc() {
        return abc;
    }

    public void setAbc(Integer abc) {
        this.abc = abc;
    }

    public java.math.BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(java.math.BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public java.math.BigDecimal getInvestLoanMoneytotal() {
        return investLoanMoneytotal;
    }

    public void setInvestLoanMoneytotal(java.math.BigDecimal investLoanMoneytotal) {
        this.investLoanMoneytotal = investLoanMoneytotal;
    }

    public java.math.BigDecimal getMoney1() {
        return money1;
    }

    public void setMoney1(java.math.BigDecimal money1) {
        this.money1 = money1;
    }

    public java.math.BigDecimal getMoney2() {
        return money2;
    }

    public void setMoney2(java.math.BigDecimal money2) {
        this.money2 = money2;
    }

    public java.math.BigDecimal getMoney3() {
        return money3;
    }

    public void setMoney3(java.math.BigDecimal money3) {
        this.money3 = money3;
    }

    public java.math.BigDecimal getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(java.math.BigDecimal loanTotal) {
        this.loanTotal = loanTotal;
    }

    public java.math.BigDecimal getLoanTotalTen() {
        return loanTotalTen;
    }

    public void setLoanTotalTen(java.math.BigDecimal loanTotalTen) {
        this.loanTotalTen = loanTotalTen;
    }

    public List<PlBidPlan> getBidPlanLoan() {
        return bidPlanLoan;
    }

    public void setBidPlanLoan(List<PlBidPlan> bidPlanLoan) {
        this.bidPlanLoan = bidPlanLoan;
    }

    public List<PlBidPlan> getBidPlanFinancial() {
        return bidPlanFinancial;
    }

    public void setBidPlanFinancial(List<PlBidPlan> bidPlanFinancial) {
        this.bidPlanFinancial = bidPlanFinancial;
    }

    public WebFinanceApplyUploads getWebFinanceApplyUploads() {
        return webFinanceApplyUploads;
    }

    public void setWebFinanceApplyUploads(
            WebFinanceApplyUploads webFinanceApplyUploads) {
        this.webFinanceApplyUploads = webFinanceApplyUploads;
    }

    public List<BpFinanceApplyUser> getListApplyUser() {
        return listApplyUser;
    }

    public void setListApplyUser(List<BpFinanceApplyUser> listApplyUser) {
        this.listApplyUser = listApplyUser;
    }

    public BpFinanceApplyUser getFinanceApplyUser() {
        return financeApplyUser;
    }

    public void setFinanceApplyUser(BpFinanceApplyUser financeApplyUser) {
        this.financeApplyUser = financeApplyUser;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<BpFundProject> getFundList() {
        return fundList;
    }

    public void setFundList(List<BpFundProject> fundList) {
        this.fundList = fundList;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getEmailNameAfter() {
        return emailNameAfter;
    }

    public void setEmailNameAfter(String emailNameAfter) {
        this.emailNameAfter = emailNameAfter;
    }

    //查询条件
    private Map<String, String> searchMap = new HashMap<String, String>();
    //自动投标返回执行结果
    private Map<String, String> backMessge = new HashMap<String, String>();

    public Map<String, String> getBackMessge() {
        return backMessge;
    }

    public void setBackMessge(Map<String, String> backMessge) {
        this.backMessge = backMessge;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public List<WebBankcard> getListBankCard() {
        return listBankCard;
    }

    public void setListBankCard(List<WebBankcard> listBankCard) {
        this.listBankCard = listBankCard;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Object isSendMail() {
        return isSendMail;
    }

    public void setSendMail(Object isSendMail) {
        this.isSendMail = isSendMail;
    }

    public String getVerify_sms() {
        return verify_sms;
    }

    public void setVerify_sms(String verifySms) {
        verify_sms = verifySms;
    }

    private Long id;


    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getPlainpassword() {
        return plainpassword;
    }

    public void setPlainpassword(String plainpassword) {
        this.plainpassword = plainpassword;
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

    public String getEmailcode() {
        return emailcode;
    }

    public void setEmailcode(String emailcode) {
        this.emailcode = emailcode;
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

    public String getTeacherPosition() {
        return teacherPosition;
    }

    public void setTeacherPosition(String teacherPosition) {
        this.teacherPosition = teacherPosition;
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

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public List<Investproject> getListBp() {
        return listBp;
    }

    public void setListBp(List<Investproject> listBp) {
        this.listBp = listBp;
    }

    public Integer getHavechildren() {
        return havechildren;
    }

    public void setHavechildren(Integer havechildren) {
        this.havechildren = havechildren;
    }

    public String getCollegeDegree() {
        return collegeDegree;
    }

    public void setCollegeDegree(String collegeDegree) {
        this.collegeDegree = collegeDegree;
    }


    public String getCollegeYear() {
        return collegeYear;
    }

    public void setCollegeYear(String collegeYear) {
        this.collegeYear = collegeYear;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getRelDirName() {
        return relDirName;
    }

    public void setRelDirName(String relDirName) {
        this.relDirName = relDirName;
    }

    public String getRelDirType() {
        return relDirType;
    }

    public void setRelDirType(String relDirType) {
        this.relDirType = relDirType;
    }

    public String getRelDirPhone() {
        return relDirPhone;
    }

    public void setRelDirPhone(String relDirPhone) {
        this.relDirPhone = relDirPhone;
    }

    public String getRelOtherName() {
        return relOtherName;
    }

    public void setRelOtherName(String relOtherName) {
        this.relOtherName = relOtherName;
    }

    public String getRelOtherType() {
        return relOtherType;
    }

    public void setRelOtherType(String relOtherType) {
        this.relOtherType = relOtherType;
    }

    public String getRelOtherPhone() {
        return relOtherPhone;
    }

    public void setRelOtherPhone(String relOtherPhone) {
        this.relOtherPhone = relOtherPhone;
    }

    public String getRelFriendName() {
        return relFriendName;
    }

    public void setRelFriendName(String relFriendName) {
        this.relFriendName = relFriendName;
    }

    public String getRelFriendType() {
        return relFriendType;
    }

    public void setRelFriendType(String relFriendType) {
        this.relFriendType = relFriendType;
    }

    public String getRelFriendPhone() {
        return relFriendPhone;
    }

    public void setRelFriendPhone(String relFriendPhone) {
        this.relFriendPhone = relFriendPhone;
    }

    public Integer getCareerType() {
        return careerType;
    }

    public void setCareerType(Integer careerType) {
        this.careerType = careerType;
    }

    public String getWebshopName() {
        return webshopName;
    }

    public void setWebshopName(String webshopName) {
        this.webshopName = webshopName;
    }

    public java.math.BigDecimal getWebshopMonthlyincome() {
        return webshopMonthlyincome;
    }

    public void setWebshopMonthlyincome(java.math.BigDecimal webshopMonthlyincome) {
        this.webshopMonthlyincome = webshopMonthlyincome;
    }

    public String getWebshopEmail() {
        return webshopEmail;
    }

    public void setWebshopEmail(String webshopEmail) {
        this.webshopEmail = webshopEmail;
    }

    public String getWebshopProvince() {
        return webshopProvince;
    }

    public void setWebshopProvince(String webshopProvince) {
        this.webshopProvince = webshopProvince;
    }

    public String getWebshopCity() {
        return webshopCity;
    }

    public void setWebshopCity(String webshopCity) {
        this.webshopCity = webshopCity;
    }

    public String getWebshopAddress() {
        return webshopAddress;
    }

    public void setWebshopAddress(String webshopAddress) {
        this.webshopAddress = webshopAddress;
    }


    public String getWebshopStartyear() {
        return webshopStartyear;
    }

    public void setWebshopStartyear(String webshopStartyear) {
        this.webshopStartyear = webshopStartyear;
    }

    public String getWebshopPhone() {
        return webshopPhone;
    }

    public void setWebshopPhone(String webshopPhone) {
        this.webshopPhone = webshopPhone;
    }

    public String getHireCompanyname() {
        return hireCompanyname;
    }

    public void setHireCompanyname(String hireCompanyname) {
        this.hireCompanyname = hireCompanyname;
    }

    public String getHirePosition() {
        return hirePosition;
    }

    public void setHirePosition(String hirePosition) {
        this.hirePosition = hirePosition;
    }

    public java.math.BigDecimal getHireMonthlyincome() {
        return hireMonthlyincome;
    }

    public void setHireMonthlyincome(java.math.BigDecimal hireMonthlyincome) {
        this.hireMonthlyincome = hireMonthlyincome;
    }

    public String getHireEmail() {
        return hireEmail;
    }

    public void setHireEmail(String hireEmail) {
        this.hireEmail = hireEmail;
    }

    public String getHireProvince() {
        return hireProvince;
    }

    public void setHireProvince(String hireProvince) {
        this.hireProvince = hireProvince;
    }

    public String getHireCity() {
        return hireCity;
    }

    public void setHireCity(String hireCity) {
        this.hireCity = hireCity;
    }

    public String getHireAddress() {
        return hireAddress;
    }

    public void setHireAddress(String hireAddress) {
        this.hireAddress = hireAddress;
    }

    public String getHireCompanytype() {
        return hireCompanytype;
    }

    public void setHireCompanytype(String hireCompanytype) {
        this.hireCompanytype = hireCompanytype;
    }

    public String getHireCompanycategory() {
        return hireCompanycategory;
    }

    public void setHireCompanycategory(String hireCompanycategory) {
        this.hireCompanycategory = hireCompanycategory;
    }

    public String getHireCompanysize() {
        return hireCompanysize;
    }

    public void setHireCompanysize(String hireCompanysize) {
        this.hireCompanysize = hireCompanysize;
    }

    public String getHireStartyear() {
        return hireStartyear;
    }

    public void setHireStartyear(String hireStartyear) {
        this.hireStartyear = hireStartyear;
    }

    public String getHireCompanyphone() {
        return hireCompanyphone;
    }

    public void setHireCompanyphone(String hireCompanyphone) {
        this.hireCompanyphone = hireCompanyphone;
    }

    public Integer getHavehouse() {
        return havehouse;
    }

    public void setHavehouse(Integer havehouse) {
        this.havehouse = havehouse;
    }

    public Integer getHavehouseloan() {
        return havehouseloan;
    }

    public void setHavehouseloan(Integer havehouseloan) {
        this.havehouseloan = havehouseloan;
    }

    public Integer getHavecar() {
        return havecar;
    }

    public void setHavecar(Integer havecar) {
        this.havecar = havecar;
    }

    public Integer getHavecarloan() {
        return havecarloan;
    }

    public void setHavecarloan(Integer havecarloan) {
        this.havecarloan = havecarloan;
    }




    //用户个人中心信息
    public String userValid() {
        String url = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort();
        MobileDataResultModel mobile = new MobileDataResultModel();
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //获取请求参数来判断是手机端还是APP
        String isApp = this.getRequest().getParameter("isApp");
        if (null == cust) {
            if ("1".equals(isApp)) {
                mobile.setMsg("请重新登录");
                mobile.setCode(MobileErrorCode.FAILED);
            } else {
                this.getRequest().setAttribute("bpCustMembers", "");
                return "account";
            }
        } else {
            BpCustMember bpCustMembers = bpCustMemberService.get(cust.getId());
            List<WebBankcard> listAll = webBankcardService.getbanklist(bpCustMembers.getId(), "", "");
            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());

            if (ret != null) {
                BigDecimal money = ret[3];//账户可用金额
                BigDecimal money1 = bpCustMemberService.getMoneyBidFrozen(cust.getId());//冻结资金总额散标
                BigDecimal money2 = bpCustMemberService.getMoneyMmplanFrozen(cust.getId());//冻结资金总额理财计划
                BigDecimal money3 = bpCustMemberService.getMoneyInvestAll(cust.getId());//待收投资总额第一部分：散标投资的本金和利息
                BigDecimal money4 = bpCustMemberService.getMoneyInvestAll1(cust.getId());//第二部分：理财计划的本金和利息
                BigDecimal money5 = bpCustMemberService.getMoneyDueinAll(cust.getLoginname());//待还借款总额
                BigDecimal money6 = bpCustMemberService.getMoneyBidFrozen(cust.getId());//冻结资金总额
                if (money6 == null) {
                    money6 = new BigDecimal(0);
                }
                if (money == null) {
                    money = new BigDecimal(0);
                }
                if (money1 == null) {
                    money1 = new BigDecimal(0);
                }
                if (money2 == null) {
                    money2 = new BigDecimal(0);
                }
                if (money3 == null) {
                    money3 = new BigDecimal(0);
                }
                if (money4 == null) {
                    money4 = new BigDecimal(0);
                }
                if (money5 == null) {
                    money5 = new BigDecimal(0);
                }
                BigDecimal totalMoney = money.add(money1).add(money2).add(money3).add(money4).subtract(money5);//可用金额
//                BigDecimal AllInterest = bpCustMemberService.getMoneyPreweekIncome(cust.getId());
                BigDecimal money121 = bpCustMemberService.getMoneyAccumulativeIncome(cust.getId());//累计收益金额
                //此处没有加上募集期利息,进行修改 XF
                BigDecimal raiseinterest =  bpFundIntentService.getByRaiseinterest(cust.getId());
                BigDecimal AllInterest= money121.add(raiseinterest);
                if (AllInterest == null) {
                    AllInterest = new BigDecimal(0);
                }

                if (money == null) {
                    money = new BigDecimal(0);
                }
                String trueMoney;
                //通过获取用户第三方账户信息来更新银行列表数据
                Map<String, String> queryThirdPayCustomerInfo = bpCustMemberService.queryThirdPayCustomerInfo(cust);
                if (queryThirdPayCustomerInfo.containsKey("withdrawBalance")) {
                    trueMoney = queryThirdPayCustomerInfo.get("withdrawBalance");
                } else {
                    trueMoney = "0.00";
                }

                bpCustMemberService.evict(bpCustMembers);
                bpCustMembers.setTotalMoney(totalMoney);//总金额
                bpCustMembers.setAvailableInvestMoney(money);//可用金额
                bpCustMembers.setAllInterest(AllInterest);//累计收益金额
//                bpCustMembers.setFreezeMoney(null);//冻结金额
//                bpCustMembers.setVersion(null);
//                bpCustMembers.setNotallInterest(null);
//                bpCustMembers.setPrincipalRepayment(null);
//                bpCustMembers.setThirdPayStatus(null);
//                bpCustMembers.setTotalEnchashment(null);
//                bpCustMembers.setTotalRecharge(null);
//                bpCustMembers.setUnChargeMoney(null);
//                bpCustMembers.setCustomerType(null);
                String imgurl = url + (String) this.getRequest().getSession().getAttribute("imgurl");
                String isBankCard="0";//没银行卡
                for (WebBankcard temp : listAll) {
                    if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
                        isBankCard="1";//有银行卡
                    }
                }
                if (org.apache.commons.lang3.StringUtils.isNotBlank(imgurl)) {
                    this.getRequest().getSession().removeAttribute("imgurl");

                    imgurl = url + "/mobileNew/img/account/user.png";
                }
                JSONObject memberJson = JSON.parseObject(JSON.toJSONStringWithDateFormat(bpCustMembers,"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));
                Integer personUnreadMsg = articleService.getPersonMessage(cust.getId(), "0");
                memberJson.put("free", "1");
                memberJson.put("personUnreadMsg",personUnreadMsg);
                memberJson.put("imgHeader", imgurl);
                memberJson.put("trueMoney", trueMoney);//可提现金额
                memberJson.put("bidFrozenMoney", money6);
                memberJson.put("isBankCard",isBankCard);
                //判断是否是APP
                if ("1".equals(isApp)) {

                    mobile.addDataContent("bpCustMembers", memberJson);
                    mobile.setCode(MobileErrorCode.SUCCESS);
                } else {
                    this.getRequest().setAttribute("bpCustMembers", memberJson);
                    return "account";
                }
            }
        }
        setJsonString(mobile.toJSON());
        return SUCCESS;
    }
    //app账户资金总览信息
    public String newmobileaccount() {
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String isApp = this.getRequest().getParameter("isApp");
        if (mem == null) {
            if("1".equals(isApp)){
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("用户未登录,请先登录！");
            }else {
                return "login";
            }
        } else {
            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(mem.getId(), ObSystemAccount.type0.toString());
            BigDecimal money = ret[3];//账户可用金额
            BigDecimal money101 = bpCustMemberService.getMoneyBidFrozen(mem.getId());//冻结资金总额
            BigDecimal money3 = bpCustMemberService.getMoneyInvestAll(mem.getId());//待收投资总额第一部分：散标投资的本金和利息
            BigDecimal money4 = bpCustMemberService.getMoneyInvestAll1(mem.getId());//第二部分：理财计划的本金和利息
            BigDecimal money5 = bpCustMemberService.getMoneyDueinAll(mem.getLoginname());//待还借款总额
            if (money == null) {
                money = new BigDecimal(0);
            }
            if (money101 == null) {
                money101 = new BigDecimal(0);
            }
            if (money3 == null) {
                money3 = new BigDecimal(0);
            }
            if (money4 == null) {
                money4 = new BigDecimal(0);
            }
            if (money5 == null) {
                money5 = new BigDecimal(0);
            }
            //还款中借款笔数
            Integer count = bpCustMemberService.getCountRepaymentLoan(mem.getLoginname());
            if (count == null) {
                count = 0;
            }
            //招标中借款笔数
            Integer count1 = bpCustMemberService.getCountBidLoan(mem.getLoginname());
            if (count1 == null) {
                count1 = 0;
            }
            //待回款投资笔数
            Integer count2 = bpCustMemberService.getCountInvestBack(mem.getId());
            Integer count3 = bpCustMemberService.getCountInvestBack1(mem.getId());
            if (count2 == null) {
                count2 = 0;
            }
            if (count3 == null) {
                count3 = 0;
            }
            //投标冻结中笔数
            Integer count4 = Integer.valueOf(bpCustMemberService.getCountBidFrozen(mem.getId()).toString());
            Integer count5 = Integer.valueOf(bpCustMemberService.getCountBidFrozen1(mem.getId()).toString());
            if (count4 == null) {
                count4 = 0;
            }
            if (count5 == null) {
                count5 = 0;
            }
            //累计投资总额
            BigDecimal money6 = bpCustMemberService.getMoneyAccumulativeInvest(mem.getId());
            BigDecimal money7 = bpCustMemberService.getMoneyAccumulativeInvest1(mem.getId());
            if (money6 == null) {
                money6 = new BigDecimal(0);
            }
            if (money7 == null) {
                money7 = new BigDecimal(0);
            }
            //预期待收收益
            BigDecimal money8 = bpCustMemberService.getMoneyExpectIncome(mem.getId());
            BigDecimal money9 = bpCustMemberService.getMoneyExpectIncome1(mem.getId());
            if (money8 == null) {
                money8 = new BigDecimal(0);
            }
            if (money9 == null) {
                money9 = new BigDecimal(0);
            }
            //上月到账收益
            BigDecimal money10 = bpCustMemberService.getMoneyPremonthIncome(mem.getId());
            if (money10 == null) {
                money10 = new BigDecimal(0);
            }
            //累计回收本金
            BigDecimal money11 = bpCustMemberService.getMoneyPreweekIncome(mem.getId());
            if (money11 == null) {
                money11 = new BigDecimal(0);
            }
            //累计到账收益
            BigDecimal money121 = bpCustMemberService.getMoneyAccumulativeIncome(mem.getId());
            //此处没有加上募集期利息,进行修改 XF
            BigDecimal raiseinterest =  bpFundIntentService.getByRaiseinterest(mem.getId());
            BigDecimal money12= money121.add(raiseinterest);
            if (money12 == null) {
                money12 = new BigDecimal(0);
            }
            BigDecimal moneyB = bpCustMemberService.getMoneyBidFrozen(mem.getId());
            //理财计划冻结金额
            BigDecimal moneyM = bpCustMemberService.getMoneyMmplanFrozen(mem.getId());
            if (moneyB == null) {
                moneyB = new BigDecimal(0);
            }
            if (moneyM == null) {
                moneyM = new BigDecimal(0);
            }
            BigDecimal moneyMB = moneyB.add(moneyM).setScale(2, BigDecimal.ROUND_HALF_UP);


//			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            BigDecimal money13 = bpCustMemberService.getMoneyAccumulativeIncome(mem.getId());
            BigDecimal money14 = bpCustMemberService.getMoneyPreweekIncome(mem.getId());
            if (money13 == null) {
                money13 = new BigDecimal(0);
            }
            if (money14 == null) {
                money14 = new BigDecimal(0);
            }
            BigDecimal rate = BigDecimal.ZERO;
            if (money14.compareTo(BigDecimal.ZERO) == 0 || money13.compareTo(BigDecimal.ZERO) == 0) {
            } else {
                rate = money13.multiply(new BigDecimal(100)).divide(money14, 2, BigDecimal.ROUND_HALF_UP);
            }
            //BigDecimal转成Double类型
            double dmoney = money.doubleValue();
            double dmoney1 = money101.doubleValue();
            double dmoney3 = money3.doubleValue();
            double dmoney4 = money4.doubleValue();
            double dmoney5 = money5.doubleValue();
            double dmoney6 = money6.doubleValue();
            double dmoney7 = money7.doubleValue();
            double dmoney8 = money8.doubleValue();
            double dmoney9 = money9.doubleValue();
            double dmoney10 = money10.doubleValue();
            double dmoney11 = money11.doubleValue();
            double dmoney12 = money12.doubleValue();
            double drate = rate.doubleValue();
            int icount = count.intValue();
            int icount1 = count1.intValue();
            int icount2 = count2.intValue();
            int icount3 = count3.intValue();
            int icount4 = count4.intValue();
            int icount5 = count5.intValue();
            // 账户可用金额(元)
            double availableInvestMoney = dmoney;
            // 投标冻结金额(元)
            double freezeMoney = dmoney1;
            // 待收投资总额(元)
            double collectionInvestmentMoney = dmoney4 + dmoney3;
            // 待还借款总额(元)
            double borrowMoney = dmoney5;
            // 累计投资总额(元)
            double InvestmentMoneyRental = dmoney7 + dmoney6;
            // 预期待收收益(元)
            double prospectiveEarnings = dmoney9 + dmoney8;
            // 30天内收益 - 上月到账收益
            double monthEarnings = dmoney10;
            // 累计收益(元) - 累计回收本金
            double capitalSum = dmoney11;
            // 累计到账收益(元)
            double accountEarnings = dmoney12;
            // 还款中借款笔数(笔)
            int repaymentBorrowCount = icount;
            // 招标中借款笔数(笔)
            int tendereeBorrowCount = icount1;
            // 待回款投资笔数(笔)
            int returnedInvestmentCount = icount3 + icount2;
            // 投标冻结中笔数(笔)
            int bidFreezeCount = icount5 + icount4;
            // 账户净资产(元)
            double moneyall = dmoney + dmoney3 + dmoney1 - dmoney5;
            // 获取累计收益率(%)
            String earningsRate = String.valueOf(drate);
            //资产信息
            Map<String, Double> assetInformation = new HashMap<String, Double>();
            assetInformation.put("availableInvestMoney", availableInvestMoney);   // 资金信息 - 可用余额
            assetInformation.put("freezeMoney", freezeMoney); // 资金信息 - 投标冻结金额
            assetInformation.put("collectionInvestmentMoney", collectionInvestmentMoney); // 资金信息 - 待收投资总额
            assetInformation.put("borrowMoney", borrowMoney);     // 资金信息 - 待还借款总额
            assetInformation.put("moneyall", moneyall);      // 资金信息 - 账户净资产
            //收益信息
            Map<String, Object> returnInformation = new HashMap<String, Object>();
            returnInformation.put("accountEarnings", accountEarnings);    // 收益信息 - 累计到账收益
            returnInformation.put("capitalSum", capitalSum); // 收益信息- 累计回收本金
            returnInformation.put("monthEarnings", monthEarnings);   // 收益信息 - 30天内收益
            returnInformation.put("prospectiveEarnings", prospectiveEarnings);  // 收益信息 - 预期待收收益
            returnInformation.put("earningsRate", earningsRate);      // 收益信息 - 累计收益率
            //交易信息
            Map<String, Object> dealInformation = new HashMap<String, Object>();
            dealInformation.put("investmentMoneyRental", InvestmentMoneyRental);      // 交易信息 - 累计投资总额
            dealInformation.put("bidFreezeCount", bidFreezeCount);   // 交易信息 - 投标冻结中笔数
            dealInformation.put("returnedInvestmentCount", returnedInvestmentCount); // 交易信息 - 待回款投资笔数
            dealInformation.put("tendereeBorrowCount", tendereeBorrowCount); // 交易信息 - 招标中借款笔数
            dealInformation.put("repaymentBorrowCount", repaymentBorrowCount);  // 交易信息 - 还款中借款笔数

            if("1".equals(isApp)){

                model.addDataContent("assetInformation", assetInformation);
                model.addDataContent("dealInformation", dealInformation);
                model.addDataContent("returnInformation", returnInformation);
                model.setCode(MobileErrorCode.SUCCESS);
            }else {
                this.getRequest().setAttribute("assetInformation",assetInformation);
                this.getRequest().setAttribute("dealInformation",dealInformation);
                this.getRequest().setAttribute("returnInformation",returnInformation);
                return "account_overview";

            }


        }
        setJsonString(model.toJSON());
        return SUCCESS;
    }


//    /**
//     *
//     * APP忘记密码
//     * 检查用户是否存在
//     */
//
//    public String appcheckUser() {
//        MobileDataResultModel model = new MobileDataResultModel();
////        String regType = getRequest().getParameter("regType");//验证类型
//        String random = createRandom(true, 6);//生成的验证码
////        if (null == regType || "".equals(regType)) {
////            model.setMsg("页面超时!请刷新.....");
////        } else {
//            //验证验证码
//            String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
//            val = val.toLowerCase();
//            StringUtils.swapCase(val);
//            checkCode = checkCode.toLowerCase();
//            StringUtils.swapCase(StringUtils.trim(checkCode));
//            if (val != null && val.equals(checkCode)) {
//                if ("telphone".equals(regType)) { //短信找回密码
//                    String telphone = getRequest().getParameter("telphone");
//                    if (null == telphone || "".equals(telphone)) {
//                        model.setMsg("请输入手机号码");
//                    } else {
//                        BpCustMember bpphone = bpCustMemberService.isExistPhone(telphone);
//                        if (null == bpphone) {
//                            model.setMsg("该用户不存在!");
//                        } else {
//                            /**
//                             * 发送短信
//                             * ***/
//                            //开通第三方成功发送短信、邮件、站内信。
//                            Map<String, String> mapMessage = new HashMap<String, String>();
//                            mapMessage.put("key", "sms_BackPassword");
//                            mapMessage.put("telphone", telphone);
//                            mapMessage.put("${code}", random);
//                            String result = sendMesService.sendSmsEmailMessage(mapMessage);
//                            this.getSession().setAttribute("typeValue", telphone);
//                            this.getSession().setAttribute(MyUserSession.TEMP_MEMBER, bpphone);
//                            model.setMsg("0");
//                            /**
//                             * 返回什么表示发送短信成功
//                             */
//
//                        }
//
//                    }
//                }else {
//                    model.setMsg("网络异常");
//                }
//            } else {
//                model.setMsg("验证码不正确");
//            }
////        }
//
//
//        setJsonString(model.toJSON());
//        return SUCCESS;
//    }

//
//    /**
//     * 创建指定数量的随机字符串
//     *
//     * @param numberFlag 是否是数字
//     * @param length     长度
//     * @return
//     */
//    public static String createRandom(boolean numberFlag, int length) {
//        String retStr = "";
//        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
//        int len = strTable.length();
//        boolean bDone = true;
//        do {
//            retStr = "";
//            int count = 0;
//            for (int i = 0; i < length; i++) {
//                double dblR = Math.random() * len;
//                int intR = (int) Math.floor(dblR);
//                char c = strTable.charAt(intR);
//                if (('0' <= c) && (c <= '9')) {
//                    count++;
//                }
//                retStr += strTable.charAt(intR);
//            }
//            if (count >= 2) {
//                bDone = false;
//            }
//        } while (bDone);
//
//        return retStr;
//    }
//

    /**
     * 忘记密码通过验证之后进行修改密码
     */
    public String appResetPassword() {

        MobileDataResultModel model = new MobileDataResultModel();
        String checkCode = this.getRequest().getParameter("checkCode");
        String telPhone = this.getRequest().getParameter("telPhone");
        String passWord = this.getRequest().getParameter("passWord");
        String isApp = this.getRequest().getParameter("isApp");
        MD5 md5 = new MD5();
        String code = (String) this.getSession().getAttribute(MyUserSession.RANDOM_SESSION);
//        System.out.println(code+"");
        if (null == checkCode || "".equals(checkCode)) {

                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("验证码错误");

        } else if (null == code || "".equals(code)) {
                model.setMsg("验证码已失效");
                model.setCode(MobileErrorCode.FAILED);

        } else if (code != null && !code.equals(checkCode)) {
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("验证码错误");

        } else {
            BpCustMember bpCustMember = bpCustMemberService.getMemberByPhone(telPhone);
//            if (bpCustMember != null && !"".equals(bpCustMember)) {
            this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
            bpCustMember.setPassword(md5.md5(passWord, "utf-8"));
            bpCustMemberService.merge(bpCustMember);
            //model.addDataContent("bpCustMember", bpCustMember);
            this.getSession().removeAttribute(MyUserSession.RANDOM_SESSION);
                model.setCode(MobileErrorCode.SUCCESS);
                model.setMsg("修改成功");
//            }
//            String userId = bp.getId().toString();
//            model.setMsg(userId);
            //添加uuid  session  和 一个随机数
            //uuid值为和要修改的userId
//            //将uuid   key值也放入session    此处key值为自定义 MyUserSession.FINDPWD_RANDOM_SESSION
//            String uuid = UUIDUtils.getUUID();
//            this.getSession().setAttribute(uuid, userId);  //随机数和id进行一一对应
//            this.getSession().setAttribute(MyUserSession.FINDPWD_RANDOM_SESSION, uuid);
        }
        setJsonString(model.toJSON());
        return SUCCESS;
    }


    /**
     * 修改密码
     */
    public String alterPassWord (){
        MobileDataResultModel model = new MobileDataResultModel();
        String passWord = this.getRequest().getParameter("passWord");//原密码
        String newPassWord =this.getRequest().getParameter("newPassWord");//新密码
        String isApp = this.getRequest().getParameter("isApp");
        BpCustMember bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        MD5 md5 = new MD5();
        passWord = md5.md5(passWord, "utf-8");
        if(bpCustMember.getPassword().equals(passWord)){
            if(!md5.md5(newPassWord, "utf-8").equals(passWord)){
                bpCustMember.setPassword(md5.md5(newPassWord, "utf-8"));
                bpCustMemberService.merge(bpCustMember);
                    model.setCode(MobileErrorCode.SUCCESS);
                    model.setMsg("修改成功");
            }else {
                    model.setCode(MobileErrorCode.FAILED);
                    model.setMsg("新旧密码不能一致！");
            }

        }else {
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("原密码不正确");
        }
        setJsonString(model.toJSON());
        return SUCCESS;
    }



    /**
     * app忘记密码通过验证之后进行修改密码
     *
     * @return
     */

//
//    public String appResetPassword() {
//        MobileDataResultModel model = new MobileDataResultModel();
//        String passWord = this.getRequest().getParameter("passWord");
//        MD5 md5 = new MD5();
//        //获取随机数
//        String uuid = (String) this.getSession().getAttribute(MyUserSession.FINDPWD_RANDOM_SESSION);
//        //用户id
//        String userid = (String) this.getSession().getAttribute(uuid);
//        if (userid != null && !"".equals(userid)) {
//            bpCustMember = bpCustMemberService.get(Long.parseLong(userid));
//            if (bpCustMember != null && !"".equals(bpCustMember)) {
//                this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
//                bpCustMember.setPassword(md5.md5(passWord, "utf-8"));
//                bpCustMemberService.merge(bpCustMember);
//                model.addDataContent("bpCustMember", bpCustMember);
//                model.setCode(MobileErrorCode.SUCCESS);
//                model.setMsg("修改成功");
//            } else {
//                model.setMsg("修改失败");
//                model.setCode(MobileErrorCode.FAILED);
//            }
//        } else {
//            model.setMsg("修改失败");
//            model.setCode(MobileErrorCode.FAILED);
//        }
//        setJsonString(model.toJSON());
//        return SUCCESS;
//    }


    // 用户绑定银行卡列表
    public String getBindBankList() {
        //获取路径
        String url = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort();
        String isApp = this.getRequest().getParameter("isApp");
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
//        state = "0";//状态为0和1,1表示投标之类的消息，0是其他的消息
        MobileDataResultModel model = new MobileDataResultModel();
        if (mem == null) {
            if (null != isApp && isApp.endsWith("1")) {
                model.setMsg("用户未登录，请登录！");
                model.setCode(MobileErrorCode.FAILED);
                setJsonString(model.toJSON());
                return SUCCESS;
            } else {
                return "login";
            }
        } else {
            mem = bpCustMemberService.get(mem.getId());
        }
        if (mem.getIsCheckCard() == null ||mem.getCardcode()==null) {

            if (isApp != null && "1".equals(isApp)) {
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("请先进行实名认证");
                setJsonString(model.toJSON());
                return SUCCESS;
            }else{
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("请先进行实名认证");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
//            } else {
////                this.setSEuccessResultValue(TemplateConfigUtil.getDynamicConfig(
////                        DynamicConfig.MESSAGE).getTemplateFilePath());
////                return "bankCard";
//            }
        } else if (mem.getIsCheckPhone() == null || mem.getIsCheckPhone().equals("")) {
//            webMsgInstance("0", Constants.CODE_FAILED, "请先进行手机认证",  "", "", "", "", "");
            if (isApp != null && "1".equals(isApp)) {
                model.setMsg("请先进行手机认证");
                model.setCode(MobileErrorCode.FAILED);
                setJsonString(model.toJSON());
                return SUCCESS;
            }
//            else {
////                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
////                        DynamicConfig.MESSAGE).getTemplateFilePath());
//            }
        }
        bpCustMember = bpCustMemberService.get(mem.getId());
        commoon(bpCustMember);
//        List<WebBankcard> listAll = webBankcardService.getbanklist(bpCustMember.getId(), "", "");
        List<BankCard> listAll = webBankcardService.getBankCardList(bpCustMember.getId());
        String logoUrl="";
        String bankMaxLogo="";
        String bankMinLogo="";
        if (listAll != null && listAll.size() > 0) {
            BankCard temp = listAll.get(0);
            temp.setBindCardStatus(null);
            logoUrl=url+"/"+temp.getBankLogo();
            bankMaxLogo=url+"/"+temp.getBankMaxLogo();
            bankMinLogo=url+"/"+temp.getBankMinLogo();
            temp.setBankLogo(logoUrl);
            temp.setBankMaxLogo(bankMaxLogo);
            temp.setBankMinLogo(bankMinLogo);
            if (isApp != null && "1".equals(isApp)) {
                model.addDataContent("bankCard",temp);
                setJsonString(model.toJSON());
                model.setCode(MobileErrorCode.SUCCESS);
                return SUCCESS;
//            for (BankCard temp : listAll) {
//                }
            }else {
                setJsonString(model.toJSON());
                model.setMsg("用户已有银行卡");//客户已经有银行卡 通过ajax跳银行卡界面
                model.setCode(MobileErrorCode.SUCCESS);
                return SUCCESS;
//                this.getRequest().setAttribute("bankCard",temp);
//                return  "my_bankCard";
            }
        }else{
            if(isApp!=null&&"1".equals(isApp)){
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("用户没有银行卡");
                setJsonString(model.toJSON());
                  return SUCCESS;
            }else{
                model.setCode(9999);
                model.setMsg("用户没有银行卡");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
        }
    }
//  绑定银行卡界面
    public String bankCard(){
        return "bankCard";
    };
//  银行卡限额
    public String bankCardQuota(){
        String url = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort();
        MobileDataResultModel model= new MobileDataResultModel();
        List<BankCard> list = webBankcardService.getBankCardQuota();
        for (BankCard bankCard : list) {
            String bankUrl = bankCard.getBankLogo();
            bankCard.setBankLogo(url + "/"+bankUrl);
        }
        model.setCode(MobileErrorCode.SUCCESS);
        model.addDataContent("list",list);
        setJsonString(model.toJSON());
        return SUCCESS;
    };

//跳银行卡界面
    public String my_banck() {
        String url = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        mem = bpCustMemberService.get(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());
        List<BankCard> listAll = webBankcardService.getBankCardList(bpCustMember.getId());
        String logoUrl = "";
            BankCard temp = listAll.get(0);
            temp.setBindCardStatus(null);
            logoUrl = url + "/" + temp.getBankLogo();
            temp.setBankLogo(logoUrl);
        BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(mem.getId(), ObSystemAccount.type0.toString());
        String trueMoney;
        //通过获取用户第三方账户信息来更新银行列表数据
        Map<String, String> queryThirdPayCustomerInfo = bpCustMemberService.queryThirdPayCustomerInfo(mem);
        if (queryThirdPayCustomerInfo.containsKey("withdrawBalance")) {
            trueMoney = queryThirdPayCustomerInfo.get("withdrawBalance");
        } else {
            trueMoney = "0.00";
        }
        BigDecimal money = ret[3];//账户可用金额
        this.getRequest().setAttribute("bankCard", temp);
        this.getRequest().setAttribute("trueMoney", trueMoney);
        this.getRequest().setAttribute("money", money);
        return "my_bankCard";

    };

    private void commoon(BpCustMember mem) {
        bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());
        try {
            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(), ObSystemAccount.type0.toString());
            if (ret != null) {
                bpCustMember.setTotalMoney(ret[1]);
                bpCustMember.setFreezeMoney(ret[2]);
                bpCustMember.setAvailableInvestMoney(ret[3]);
                bpCustMember.setTotalInvestMoney(ret[4]);
                bpCustMember.setAllInterest(ret[5]);
                bpCustMember.setPrincipalRepayment(ret[6]);
                bpCustMember.setTotalRecharge(ret[7]);
                bpCustMember.setTotalEnchashment(ret[8]);
                bpCustMember.setTotalLoanMoney(ret[9]);
                bpCustMember.setTotalPrincipalRepaymentMoney(ret[10]);
                bpCustMember.setTotalNotPrincipalRepaymentMoney(ret[11]);
            }
            //bpCustMember = obSystemAccountService.getAccountSumMoney(mem);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int percent = 0;

        if (bpCustMember.getIsCheckEmail() != null && "1".equals(bpCustMember.getIsCheckEmail())) {
            //判断邮箱是否验证
            percent += 30;
        }

        if (bpCustMember.getIsCheckPhone() != null && "1".equals(bpCustMember.getIsCheckPhone())) {
            //判断手机是否验证
            percent += 30;
        }

        if (bpCustMember.getIsCheckCard() != null && "1".equals(bpCustMember.getIsCheckCard())) {
            //判断是否实名认证
            percent += 40;
        }
    }

    //跳转立即测试
    public String evaluate(){
        String flag = this.getRequest().getParameter("flag");
        this.getRequest().setAttribute("flag",flag);
        String bidId = this.getRequest().getParameter("bidId");
        this.getRequest().setAttribute("bidId",bidId);
//        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(this.getRequest());
        boolean isApp = isApp();
        if(isApp){
            return "testRiskApp";
        }
        return "evaluate";
    }

    //跳转风险测评
    public String testRisk(){
        String flag = this.getRequest().getParameter("flag");
        this.getRequest().setAttribute("flag",flag);
        String bidId = this.getRequest().getParameter("bidId");
        this.getRequest().setAttribute("bidId",bidId);
//        String isApp = this.getRequest().getParameter("isApp");
        //        String isApp = this.getRequest().getHeader("isApp");
//        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(this.getRequest());
        boolean isApp = isApp();
        if(isApp){
            return "riskTest";
        }
        return "testRisk";
    }

    //返回风险测评结果
    public String mobileSave() {
        String flag = this.getRequest().getParameter("flag");
        this.getRequest().setAttribute("flag",flag);
        String bidId = this.getRequest().getParameter("bidId");
        this.getRequest().setAttribute("bidId",bidId);
//        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(this.getRequest());
        boolean isApp = isApp();
        MobileDataResultModel model = new MobileDataResultModel();
        try {
            //评估之后跳转到之前的购买页面
            //begin (散标、U、D计划)
            /*if (null == this.getSession().getAttribute("bidId")) {
                String bidId = this.getRequest().getParameter("bidId");//标id
                String bidType = this.getRequest().getParameter("bidType");//标类型
                this.getSession().setAttribute("bidId", bidId);
                this.getSession().setAttribute("bidType", bidType);
            }*/
            bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            BpCustMember oldMember = bpCustMemberService.get(bpCustMember.getId());
            String accessScore = this.getRequest().getParameter("total");//分数
            if (null != accessScore && !"".equals(accessScore)) {
                Long score = Long.valueOf(accessScore);
                if (score <= 10) {
                    oldMember.setGrade("谨慎型");
                } else if (score > 10 && score < 100) {
                    oldMember.setGrade("进取型");
                }
            } else {
                oldMember.setGrade("稳健型");
            }
            //判断请求是从个人中心发出的还是从开始理财发出的
           /* if (from != null && !"".equals(from)) {
                this.getRequest().setAttribute("from", "1");
                model.addDataContent("from","1");
            }*/
            bpCustMemberService.merge(oldMember);
            BeanUtil.copyNotNullProperties(bpCustMember, oldMember);
            this.getRequest().setAttribute("bp",oldMember);
        } catch (Exception e) {
           e.printStackTrace();
        }

        if(isApp){
           return "riskCofirm";
        }else{
            return "immediateConfirm";
        }
    }
    //风险测评完成跳转之前对应页面
    public void confirm() throws ServletException, IOException {
        String flag = this.getRequest().getParameter("flag");
        String bidId = this.getRequest().getParameter("bidId");

            if("1".equals(flag)){
                getResponse().sendRedirect("mobilePlanDetailWebBidPlanAction.do?bidId="+bidId);
            }else{
               /* getRequest().getRequestDispatcher("/WEB-INF/mobileJsp/account_set.jsp").forward(getRequest(),getResponse());*/
                getResponse().sendRedirect("account_setWebPhoneCustMember.do");
            }


    }
    //跳到反馈信息
    public String feedBackJsp(){
        return "feedback";
    }

    public String changePwd(){
        return "changePwd";
    }



    //用户反馈信信息
    public  String feedBack() throws UnsupportedEncodingException {
        this.getRequest().setCharacterEncoding("utf-8");
        String content = this.getRequest().getParameter("content");
        String isApp = this.getRequest().getParameter("isApp");
        String contact= this.getRequest().getParameter("contact");
        MobileDataResultModel model = new MobileDataResultModel();
        if(content.isEmpty()||contact.isEmpty()){
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("内容或联系方式不能为空");
                setJsonString(model.toJSON());
                return SUCCESS;
            }else{
                //获取当前时间
                Date date = new Date();
//                //生成UUID
//                String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                FeedBack feedBack = new FeedBack();
                feedBack.setContent(content);
                feedBack.setContact(contact);
//                feedBack.setId(uuid);
                feedBack.setCreateTime(date);
                try{
                    bpCustMemberService.saveFeedBack(feedBack);
                    model.setCode(MobileErrorCode.SUCCESS);
                    model.setMsg("提交成功");
                }catch (UncategorizedSQLException e){
                    e.printStackTrace();
                    model.setCode(MobileErrorCode.FAILED);
                    model.setMsg("请输入正确的汉字");
                    setJsonString(model.toJSON());
                    return SUCCESS;
                }


                    setJsonString(model.toJSON());
                    return SUCCESS;
            }
    }

    /**
     * 获取客服联系方式
     *
     * @return
     */
    public String userContacts() {
        String isApp = this.getRequest().getParameter("isApp");
        if ("1".equals(isApp)) {

        }
        MobileDataResultModel resultModel = new MobileDataResultModel();

        Map<String, String> phone = new HashMap<>();
        phone.put("contact", "400-9266-114");
        phone.put("desc", "工作时间:9:00-18:00");

        Map<String, String> qq = new HashMap<>();
        qq.put("contact", "123456789");
        qq.put("desc", "请联系");
        resultModel.addDataContent("phone", phone);
        resultModel.addDataContent("qq", qq);
        setJsonString(resultModel.toJSON());
        return SUCCESS;
    }

    public String forget_pwd() {
        String telPhone = this.getRequest().getParameter("telPhone");
        this.getRequest().setAttribute("telPhone",telPhone);
        return "forget_pwd";
    }

    public String forget_pwd1(){
        String telPhone = this.getRequest().getParameter("telPhone");
        String checkCode = this.getRequest().getParameter("checkCode");
        this.getRequest().setAttribute("checkCode",checkCode);
        this.getRequest().setAttribute("telPhone",telPhone);
        return "forget_pwd1";
    }

    public String account_set(){
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(bpCustMember!=null){
            BpCustMember bpCustMembe = bpCustMemberService.get(bpCustMember.getId());
            this.getRequest().setAttribute("bpCustMember",bpCustMembe);
            List<WebBankcard> listAll = webBankcardService.getbanklist(bpCustMember.getId(), "", "");
            String isBankCard="0";//没银行卡
            for (WebBankcard temp : listAll) {
                if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
                    isBankCard="1";//有银行卡
                }
            }
            String telNummber = this.getRequest().getParameter("telNummber");
            String thirdPayFlagId = this.getRequest().getParameter("thirdPayFlagId");
            if(telNummber!=""){
                this.getRequest().setAttribute("telNummber",telNummber);
            }
                this.getRequest().setAttribute("isBankCard",isBankCard);
                this.getRequest().setAttribute("thirdPayFlagId",bpCustMembe.getThirdPayFlagId());
            return "account_set";
        }else {
            return "login";
        }
    }

    /**
     * 获取我的邀请的数据
     *
     * @return
     */
    public String appLoadMyInvest() {
        MobileDataResultModel model = new MobileDataResultModel();
        String isApp = this.getRequest().getParameter("isApp");
        String plainpassword = this.getRequest().getParameter("plainCode");
        if (isLogin()) {
            List<BpCustMember> bpCustMembers = bpCustMemberService.appMyInvest(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)), getRequest());
            String plainCode = (String)bpCustMemberService.findCode(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)));
            for (BpCustMember custMember : bpCustMembers) {
                bpCustMemberService.evict(custMember);
                custMember.setThirdPayStatus(null);
                custMember.setTotalMoney(null);
                custMember.setTotalInvestMoney(null);
                custMember.setFreezeMoney(null);
                custMember.setAvailableInvestMoney(null);
                custMember.setPrincipalRepayment(null);
                custMember.setAllInterest(null);
                custMember.setTotalRecharge(null);
                custMember.setTotalEnchashment(null);
                custMember.setUnChargeMoney(null);
                custMember.setNotallInterest(null);
                custMember.setCustomerType(null);
            }

            if("1".equals(isApp)){
                model.addDataContent("bpCustMember",bpCustMembers);
                model.addDataContent("plainCode",plainCode);
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            this.getRequest().setAttribute("bpCustMember",bpCustMembers);
            this.getRequest().setAttribute("plainCode",plainCode);
            return "invite";
        } else if(plainpassword != null && !"".equals(plainpassword)){
            //invit_reg(plainpassword);
            try {
                getResponse().sendRedirect("inviteRegWebPhoneCustMember.do?invateCode="+plainpassword);
            } catch (IOException e) {
                e.printStackTrace();
                this.getRequest().setAttribute("mes","错误，此方法行不通");
                return "error_mes";
            }
        } else {
            if("1".equals(isApp)){
                model.setCode(MobileErrorCode.SERVICE_ERROR);
                model.setMsg("请先登录");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            return "login";
        }
        this.getRequest().setAttribute("mes","系统异常，请稍后再试");
        return "error_mes";
    }
    //判断用户是否登录
    public boolean isLogin() {
        Boolean boo = false;
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            boo = true;
        }
        return boo;
    }

    //邀请人邀请用户注册方法，会将邀请人的手机号和邀请码传到给邀请用户的界面上，邀请码隐藏，只会显示手机号。分享链接的时候分享的连接为此链接，用户点击此链接进行注册
    public String invit_reg(String plainpassword){
        if(isLogin()){
            String telPhone = (String)bpCustMemberService.finTel(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)));
            String plainCode = (String)bpCustMemberService.findCode(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)));
            this.getRequest().setAttribute("telPhone", telPhone);
            this.getRequest().setAttribute("plainCode", plainCode);
            return "invite_reg";
        }else {
            return "login";
        }
    }

    public String inviteReg(){
        String plainCode = this.getRequest().getParameter("invateCode");
        String telPhone = (String)bpCustMemberService.findPhone(plainCode);
        this.getRequest().setAttribute("telPhone", telPhone);
        this.getRequest().setAttribute("plainCode", plainCode);
        return "invite_reg";
    }

    public  String test(){
        return "test";
    }

    public String appInviteReg(){

        return "";
    }

    public String selectInviteList(){
        MobileDataResultModel model = new MobileDataResultModel();
        String isApp = this.getRequest().getParameter("isApp");
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(bpCustMember != null){
            String plainCode = bpCustMember.getPlainpassword();
            if(plainCode != null && !"".equals(plainCode)){
                List<InviteDetail> list = bpCustMemberService.inviteList(plainCode);
                this.getRequest().setAttribute("inviteList",list);
                this.getRequest().setAttribute("plainCode",plainCode);
                if("1".equals(isApp)){
                    model.addDataContent("inviteList",list);
                    model.addDataContent("plainCode",plainCode);
                    setJsonString(model.toJSON());
                    return SUCCESS;
                }
            }
            return "invitations_list";
        }else {
            if("1".equals(isApp)){
                model.setMsg("请先登录");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            return "login";
        }
    }

    public String checkInvite(){
        MobileDataResultModel model = new MobileDataResultModel();
        String isApp = this.getRequest().getParameter("isApp");
        String id = this.getRequest().getParameter("id");
        if(id != null && !"".equals(id)){
            InviteDetail personDetail = bpCustMemberService.inviteList1(id);
            List<InvitePersonDetail> list = bpCustMemberService.checkInviteDetail(id);
            int totalCounts = list.size();
            this.getRequest().setAttribute("invitePersonList",list);
            if(personDetail != null){
                this.getRequest().setAttribute("personDetail",personDetail);
            }
            this.getRequest().setAttribute("totalCounts",totalCounts);
            if("1".equals(isApp)){
                model.addDataContent("invitePersonList",list);
                model.addDataContent("personDetail",personDetail);
                model.addDataContent("totalCounts",totalCounts);
                setJsonString(model.toJSON());
                return SUCCESS;
            }
        }
        return "loan_details";
    }

    //查询出借情况
    public String selectInvite(){
        MobileDataResultModel model = new MobileDataResultModel();
        String isApp = this.getRequest().getParameter("isApp");
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(bpCustMember != null){
            String plainpassword = bpCustMember.getPlainpassword();
            List<InviteDetail> list = bpCustMemberService.selectInvite(getRequest(), plainpassword);
            Integer countNum = bpCustMemberService.countNum(plainpassword);
            BigDecimal sumMoney = bpCustMemberService.sumMoney(plainpassword);
            this.getRequest().setAttribute("list",list);
            this.getRequest().setAttribute("countNum",countNum);
            this.getRequest().setAttribute("sumMoney",sumMoney);
            if("1".equals(isApp)){
                model.addDataContent("list",list);
                model.addDataContent("countNum",countNum);
                model.addDataContent("sumMoney",sumMoney);
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            return "lend_search";
        }else {
            if("1".equals(isApp)){
                model.setMsg("请先登录");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            return "login";
        }
    }

    public String invite1(){
        return "invitations_list";

    }
    public String invite2(){
        return "loan_details";
    }
    public String invite3(){
        return "lend_search";
    }


}
