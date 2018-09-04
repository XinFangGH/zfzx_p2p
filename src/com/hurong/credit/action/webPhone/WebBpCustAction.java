package com.hurong.credit.action.webPhone;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.activity.BpActivityManageService;
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
import com.hurong.credit.util.MyUserSession;
import com.sms.SmsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebBpCustAction extends BaseAction {
    protected Log logger= LogFactory.getLog(WebBpCustAction.class);
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

    /**
     * 显示列表
     */
    /*public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BpCustMember> list = bpCustMemberService.getAll(filter);

		Type type = new TypeToken<List<BpCustMember>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}*/
    public Map<String, String> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(Map<String, String> searchMap) {
        this.searchMap = searchMap;
    }

    //安卓投资人的回款计划接口
    public String mobilePaymentplan() {
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            typeTopay = "Financing";
            String planId = this.getRequest().getParameter("planId");
            String orderNo = this.getRequest().getParameter("orderNo");
            PlBidSale plBidSale = null;
            if (Long.valueOf(planId) != null) {
                QueryFilter filter = new QueryFilter();
                filter.addFilter("Q_bidPlanID_L_EQ", Long.valueOf(planId));
                List<PlBidSale> plBidSales = plBidSaleService.getAll(filter);
                if (plBidSales != null && plBidSales.size() > 0) {
                    plBidSale = plBidSales.get(0);
                }
            }
            if (plBidSale != null) {
                if (plBidSale.getSaleStatus() != null && (plBidSale.getSaleStatus() == 1 || plBidSale.getSaleStatus() == 4)) {
                    fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), null, orderNo, "InvestRepayMemtSignle", null);
                } else {
                    fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), orderNo, "InvestRepayMemtSignle", null);
                }
            } else {
                fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), orderNo, "InvestRepayMemtSignle", null);
            }
            model.addDataContent("fundIntentpay", this.fundIntentpay);
            model.addDataContent("totalCounts", fundIntentpay.size());
            this.getRequest().setAttribute("fundIntentpay", this.fundIntentpay);
            this.getRequest().setAttribute("totalCounts", fundIntentpay.size());
            setJsonString(model.toJSON());
        }
        /*String isApp = this.getRequest().getParameter("isApp");
        if ("1".equals(isApp)) {
            return SUCCESS;
        }else{
            return "collection";
        }*/

        boolean isApp = isApp();
        if (isApp) {
            return SUCCESS;
        }else{
            return "collection";
        }



    }



}
