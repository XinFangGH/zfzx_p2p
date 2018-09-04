package com.hurong.credit.action.user;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.*;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.config.RandomValidateCode;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepCreditlevel;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.p2p.PlatDataPublish;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.system.GlobalType;
import com.hurong.credit.model.system.MailData;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.user.CsDicAreaDynam;
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
import com.hurong.credit.sms.HXSmsManagerServerImp;
import com.hurong.credit.sms.MessageStrategy;
import com.hurong.credit.util.*;
import com.sms.SmsService;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author
 */
@SuppressWarnings("serial")
public class BpCustMemberAction extends BaseAction {
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


    /**
     * 批量删除
     *
     * @return
     */
    public String multiDel() {

        String[] ids = getRequest().getParameterValues("ids");
        if (ids != null) {
            for (String id : ids) {
                bpCustMemberService.remove(new Long(id));
            }
        }

        jsonString = "{success:true}";

        return SUCCESS;
    }

    /**
     * 判断是否有重名
     */
    public String isExist() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        boolean flag = validateQeqDate();


        BpCustMember member = bpCustMemberService.isExist(loginname);
        if (flag) {
            if (member != null) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("用户名已存在"));
                sb.append(",\"result\":1");
                sb.append("}");
            } else {
                sb.append("{\"success\":false,\"errMsg\":");
                sb.append(gson.toJson("用户名可以使用"));
                sb.append(",\"result\":0");
                sb.append("}");
            }
        }else {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("输入太频繁，请稍后再试"));
            sb.append(",\"result\":1");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;

    }


    /**
     *  校验同意Ip在某一时间的请求次数
     * @return
     */
    private boolean validateQeqDate() {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String now = sdf.format(new Date());
        String remoteAddr =  this.getRequest().getRemoteAddr();//获取请求Ip
        String condition = (String) this.getSession().getAttribute(remoteAddr);//获取结果
        if (StringUtils.isNotEmpty(condition)){
            String[] split = condition.split("_");
            if (split[1].equals(now)){
                if (StringUtils.isNotEmpty(split[2])){
                    String num = split[2];
                    if (num.length() > 15) {
                        flag = false;
                    }else {
                        this.getSession().setAttribute(remoteAddr,condition+"1");
                    }
                }
            }else {
                this.getSession().setAttribute(remoteAddr,remoteAddr+"_"+now + "_1");
            }
        }else {
            this.getSession().setAttribute(remoteAddr,remoteAddr+"_"+now + "_1");
        }
        return  flag;
    }

    public String validateInfo() {
        String userName = this.getRequest().getParameter("userName");
        String passWord = this.getRequest().getParameter("passWord");
        String loginName = this.getRequest().getParameter("loginName");
        String loginPassWord = this.getRequest().getParameter("loginPassWord");
        String telphone = this.getRequest().getParameter("telphone");
        String cardNumber = this.getRequest().getParameter("cardNumber");
        String email = this.getRequest().getParameter("email");
        setJsonString(bpCustMemberService.validateInfo(userName, passWord, loginName, loginPassWord, telphone, cardNumber, email));
        return SUCCESS;

    }


    /**
     * 判断邮箱是否已使用
     */
    public String isExistEmail() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();

        BpCustMember member = bpCustMemberService.isExistEmail(email);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (member != null) {
            if (mem != null && mem.getId().equals(member.getId())) {
                sb.append("{\"success\":false,\"errMsg\":");
                sb.append(gson.toJson("邮箱可以使用"));
                sb.append(",\"result\":0");
                sb.append("}");
            } else {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("邮箱已存在"));
                sb.append(",\"result\":1");
                sb.append("}");
            }

        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson("邮箱可以使用"));
            sb.append(",\"result\":0");
            sb.append("}");
        }

        setJsonString(sb.toString());
        return SUCCESS;

    }

    /**
     * 跳转到消息页
     *
     * @return
     */
    public String toMessage() {
        System.out.println("message=" + this.getRequest().getParameter("message"));
        this.getRequest().setAttribute("message", this.getRequest().getParameter("message"));
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.MESSAGE).getTemplateFilePath());
        return SUCCESS;
    }

    public String isTelphone() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        String telphone = this.getRequest().getParameter("telphone");
        String[] codemeg = new String[2];
        codemeg = validation.phoneValidation(telphone);
        if (codemeg[0].equals(Constants.CODE_SUCCESS)) {
            BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
            if (member != null) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("手机号码已存在"));
                sb.append(",\"result\":1");
                sb.append("}");
            } else {
                sb.append("{\"success\":false,\"errMsg\":");
                sb.append(gson.toJson("号码可以使用"));
                sb.append(",\"result\":0");
                sb.append("}");
            }
        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson(codemeg[1]));
            sb.append(",\"result\":1");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;

    }

    /**
     * 判断手机是否存在
     */
    public void isExistTelphone() {
        String phone = this.getRequest().getParameter("phone");
        BpCustMember member = bpCustMemberService.isExistTelphone(phone);
        if (member != null) {
            try {
                this.getResponse().getWriter().print("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.getResponse().getWriter().print("false");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断验证码是否正确
     */
    public String isRightCheckCode() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (val != null && !"".equals(val)) {
            val = val.toLowerCase();
        }
        checkCode = checkCode.toLowerCase();
        if (val != null && val.equals(checkCode)) {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("正确"));
            sb.append(",\"result\":0");
            sb.append("}");
        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson("验证码错误"));
            sb.append(",\"result\":1");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 判断手机短信验证码是否正确
     */
    public String isRightTelCheckCode() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        String val = (String) getSession().getAttribute("codeValue");
        if (val != null && !"".equals(val)) {
            val = val.toLowerCase();
        }
        checkCode = checkCode.toLowerCase();
        if (val != null && val.equals(checkCode)) {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("正确"));
            sb.append(",\"result\":0");
            sb.append("}");
        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson("验证码错误"));
            sb.append(",\"result\":1");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 判断支付密码是否正确
     */
    public String isRightPayMent() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            bpCustMember = bpCustMemberService.get(mem.getId());
            if (bpCustMember.getPaymentCode() == null) {

                sb.append("{\"success\":false,\"errMsg\":");
                sb.append(gson.toJson("支付密码没有设置！"));
                sb.append(",\"result\":0");
                sb.append("}");
            } else if (paymentCode.equals(bpCustMember.getPaymentCode())) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("支付密码正确"));
                sb.append(",\"result\":1");
                sb.append("}");
            } else {
                sb.append("{\"success\":false,\"errMsg\":");
                sb.append(gson.toJson("支付密码错误"));
                sb.append(",\"result\":2");
                sb.append("}");
            }
        }
        setJsonString(sb.toString());
        return SUCCESS;

    }

    /**
     * 个人中心总览---近期待还借款
     */
    public void newLoanSet(BpCustMember mem) {
        //数据库中查询 是否有返款失败的 并进行返款
        List<ShowManageMoney> list = new ArrayList<ShowManageMoney>();
        try {
            //查询出来待还款记录（每个项目一条）
            list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemt", null);
			/*QueryFilter filter = new QueryFilter();
			filter.addFilter("Q_receiverP2PAccountNumber_S_EQ", mem.getLoginname());
			filter.addFilter("Q_state_N_EQ",7);
			List<PlBidPlan> all = plBidPlanService.getAll(filter);//当前用户的借款项目
			for (PlBidPlan plBidPlan : all) {
				ShowManageMoney show = new ShowManageMoney();
				BigDecimal count = new BigDecimal(0);
				String[] ret=bpFundIntentService.getFundInfoByPlanId(plBidPlan.getBidId(), this.getRequest());
				show.setRepayMentLength((Integer.valueOf(ret[1]))+"/"+ret[2]);//还款进度
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
				java.util.Date next=null;
				if(ret[3]!=null&&!ret[3].equals("")){
					next = sdf.parse(ret[3]);
				}
		        show.setNextPayDate(next);  //下次还款日期
		        //前台显示的名称
				show.setProName(plBidPlan.getBidProName());
				show.setBidId(plBidPlan.getBidId());
				BigDecimal plconut = slFundIntentService.getnotMoneySum(plBidPlan.getBidId(), Integer.valueOf(ret[1]));
				if(plconut!=null){
					show.setPlCount(plconut);//本息合计
				}else{
					show.setPlCount(new BigDecimal("0"));
				}
		        QueryFilter filter1 = new QueryFilter();
				filter1.addFilter("Q_bidPlanId_L_EQ", plBidPlan.getBidId());
				filter1.addFilter("Q_isCheck_SN_EQ", Short.valueOf("0"));
				filter1.addFilter("Q_isValid_SN_EQ", Short.valueOf("0"));
				filter1.addFilter("Q_loanerRepayMentStatus_N_EQ", 2);
				filter1.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(ret[1])+1);
				List<BpFundIntent> fundList = bpFundIntentService.getAll(filter1);
				for (BpFundIntent bpFundIntent : fundList) {
					if(bpFundIntent.getIntentDate().compareTo(next)==0){
						count = count.add(bpFundIntent.getAccrualMoney()==null?BigDecimal.ZERO:bpFundIntent.getAccrualMoney());
					}else{
						count = new BigDecimal("0");
					}
				}
				show.setAccMoney(count);//罚息
				show.setTotal(show.getAccMoney().add(show.getPlCount()));
				if(show.getAccMoney().add(show.getPlCount())!=null&&show.getAccMoney().add(show.getPlCount()).compareTo(new BigDecimal(0))<0){

				}else{
					list.add(show);
				}
			}*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getRequest().setAttribute("LoanList", list);

    }

    /**
     * 显示我要融资的界面
     *
     * @return
     */
    public String toFinancing() {
        //从session中取出当前用户
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        if (mem != null) {

            if (pager == null) {
                pager = new Pager();
                pager.setPageSize(10);
            }
            String bidtime = getRequest().getParameter("bidtime");
            if (bidtime != null && !"".equals(bidtime) && !"null".equals(bidtime)) {
                searchMap.put("bidtime", bidtime);
            }
            //结束时间
            String bidtime2 = getRequest().getParameter("bidtime2");
            if (bidtime2 != null && !"".equals(bidtime2) && !"null".equals(bidtime2)) {
                searchMap.put("bidtime2", bidtime2);
            }
            //投资名称
            String bidName = getRequest().getParameter("bidName");
            if (null != bidName && !"".equals(bidName) && !"null".equals(bidName)) {
                searchMap.put("bidName", bidName);
            }
            //投资金额
            String userMoney = getRequest().getParameter("userMoney");
            if (null != userMoney && !"".equals(userMoney) && !"null".equals(userMoney)) {
                searchMap.put("userMoney", userMoney);
            }
            List<BpFundProject> list = getLoanList(mem, (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
            List<BpFundProject> listcount = bpFundProjectService.getPersonBpFundProject(this.getRequest(), (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
            pager.setTotalCount(listcount != null ? listcount.size() : 0);

            pager.setList(list);
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.FINANCING).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 获取融资列表
     *
     * @param start
     * @param limit
     * @return
     */
    private List<BpFundProject> getLoanList(BpCustMember mem, int start, int limit) {
        //得到线下的编号
        BpCustRelation bpCustRelation = bpCustRelationService.getP2pCustById(mem.getId());
        if (bpCustRelation != null) {
            //向request中保存属性
            this.getRequest().setAttribute("offlineCusId", bpCustRelation.getOfflineCusId());
        }
        return bpFundProjectService.getPersonBpFundProject(this.getRequest(), start, limit);
    }

    /**
     * 获取email 进行拆分
     *
     * @param bpCustMember
     */
    private void getEmail(BpCustMember bpCustMember) {
        if (bpCustMember.getEmail() != null && !bpCustMember.getEmail().equals("") && !bpCustMember.getEmail().equals("null")) {
            String[] str = bpCustMember.getEmail().split("@");
            if (str != null && str.length > 1) {
                emailName = bpCustMember.getEmail().split("@")[0];
                emailNameAfter = bpCustMember.getEmail().split("@")[1];
            } else {
                emailName = null;
                emailNameAfter = null;
            }
        }
    }

    /**
     * 特权介绍
     *
     * @return
     */
    public String mbIntro() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        commoon(mem);
        bpCustMember = bpCustMemberService.get(mem.getId());
        String member_grade = "";
        Integer memberGrade = bpCustMember.getMemberGrade();
        if (memberGrade == null || "0".equals(memberGrade)) {
            member_grade = configMap.get("member_grade_0").toString();
        } else {
            if (memberGrade == 1) {
                member_grade = configMap.get("member_grade_1").toString();
            }
            if (memberGrade == 2) {
                member_grade = configMap.get("member_grade_2").toString();
            }
        }
        this.getRequest().setAttribute("member_grade", member_grade);

        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MEEBERINTOR).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 开通续费
     *
     * @return
     * @throws ParseException
     */
    public String mbPurchase() throws ParseException {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        commoon(mem);
        bpCustMember = bpCustMemberService.get(mem.getId());
        String member_grade = "";
        Integer memberGrade = bpCustMember.getMemberGrade();
        if (memberGrade == null || "0".equals(memberGrade)) {
            member_grade = configMap.get("member_grade_0").toString();
        } else {
            if (memberGrade == 1) {
                member_grade = configMap.get("member_grade_1").toString();
            }
            if (memberGrade == 2) {
                member_grade = configMap.get("member_grade_2").toString();
            }
            //得到系统时间
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String da = sdf.format(d);
            Date date1 = sdf.parse(da);
            //计算会员到期天数
            long quot = bpCustMember.getMemberDuedate().getTime() - date1.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            //int day =(int) quot;
            if (quot > 1) {
                this.getRequest().setAttribute("quot", quot);
            } else {
                this.getRequest().setAttribute("quot", "已到期,请续费");
            }
        }

        this.getRequest().setAttribute("member_grade", member_grade);
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MEMBERPURCHASE).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 显示详细信息
     *
     * @return
     * @throws IOException
     */
    public String get() {
        String isMobile = this.getRequest().getParameter("isMobile");
        this.getSession().setAttribute("highlight", 1);
        String imgurl = (String) this.getRequest().getSession().getAttribute("imgurl");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(imgurl)){
            this.getRequest().getSession().removeAttribute("imgurl");
        }
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        List<ObSystemaccountSetting> obSystemaccountSetting = obSystemAccountService.findObSystemaccountSetting();
        this.getRequest().setAttribute("obSystemaccountSetting", obSystemaccountSetting);
        String mobile = this.getRequest().getParameter("mobile");
        if ("mobile".equals(mobile) && mem == null) {
            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
            return "freemarker";
        }
        if (mem != null) {
            bpCustMember = bpCustMemberService.get(mem.getId());
            bpCustMemberService.queryThirdPayCustomerInfo(bpCustMember);
            BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
            applyUser.setUserID(mem.getId());
            listApplyUser = financeApplyUserService.getApplyUser(applyUser);
            if (null == bpCustMember) {
                this.getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.LOGIN).getTemplateFilePath());
                return "freemarker";
            }
            userloginlogs = userloginlogsService.getLoginlogs(mem.getLoginname());
            QueryFilter filter = new QueryFilter();
            filter.addFilter("Q_customerId_L_EQ", bpCustMember.getId().toString());
            List<WebBankcard> listw = webBankcardservice.getAll(filter);
            if (listw.size() > 0) {
                this.getRequest().setAttribute("isbind", 1);
            } else {
                this.getRequest().setAttribute("isbind", 2);
            }

            this.getRequest().setAttribute("memberId", mem.getId());
            getEmail(bpCustMember);
            List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
            this.getRequest().setAttribute("fileList", fileList);
            Pager pager = new Pager();
            pager.setPageSize(5);
            List<FundPay> newManageMoney = newManageMoney(bpCustMember, "1", pager);//投资成功
            this.getRequest().setAttribute("newManageMoney", newManageMoney);
            List<FundPay> newManageMoney2 = newManageMoney(bpCustMember, "7", pager);
            this.getRequest().setAttribute("ShowManageMoney", newManageMoney2);
            newLoanSet(bpCustMember);
            //理财计划 购买中
            managelist("1");
            //理财计划 持有中
            managelist("2");
            commoon(bpCustMember);
            total(bpCustMember);
            //查询资金明细
            obSystemDeal();
            //消息
            message();
            //优惠券
            coupon();
            //自动投标
            bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
            this.getSession().setAttribute("bidAuto", bidAuto);
            QueryFilter filter2 = new QueryFilter();
            filter2.addFilter("Q_remark_S_EQ", bpCustMember.getId());
            List<FileForm> fileList3 = fileFormService.getAll(filter2);
            if (fileList3.size() > 0) {
                String url = fileList3.get(0).getMark();
                this.getRequest().getSession().setAttribute("imgurl", url);
            }

            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                }
            }


            //会员等级
            String levelMark = "普通会员";
            if (null != bpCustMember.getScore() && !"".equals(bpCustMember.getScore())) {
                levelMark = bpCustMemberService.getLevelMark(bpCustMember.getScore());
            }
            if (isMobile != null && isMobile.endsWith("1")) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                StringBuffer buff = new StringBuffer("{\"success\":true,\"data\":")
                        .append(gson.toJson(bpCustMember));
                buff.append("}");
                setJsonString(buff.toString());
                return SUCCESS;
            }
            this.getSession().setAttribute("levelMark", levelMark);
            if (typ == 0) {
                // 个人账户登录，主页
                this.getSession().setAttribute("loginType", "person");//保存登录类型
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYHOME).getTemplateFilePath());
                //判断是否为企业担保户
                BpCustRelation relation = bpCustRelationService.getCustOffine(mem.getId(), "b_guarantee");
                if (relation != null) {
                    //企业账户登录，主页
                    List<ShowManageMoney> guaranteeList = bpFundIntentService.listByGuarantorsId(bpCustMember.getId(), this.getRequest());//需要代偿的项目
                    this.getRequest().setAttribute("guaranteeList", guaranteeList);
                    this.getSession().setAttribute("loginType", "enterprise");//保存登录类型
                    List<PlBidCompensatory> compensatoryList = plBidCompensatoryService.findListByComp2PId(mem.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf("0"), null, this.getRequest());
                    this.getRequest().setAttribute("compensatoryList", compensatoryList);
                    //在保项目查询，按标
                    BpCustRelation r = bpCustRelationService.getCustOffine(mem.getId(), "b_guarantee");

                    List<PlBidPlan> zlist = plBidPlanService.findBidPlan(r.getOfflineCusId(), "(7)", this.getRequest());
                    //结清项目查询，按标
                    List<PlBidPlan> jlist = plBidPlanService.findBidPlan(r.getOfflineCusId(), "(10)", this.getRequest());
                    this.getRequest().setAttribute("zlist", zlist);
                    this.getRequest().setAttribute("jlist", jlist);

                    String oprojectName = getRequest().getParameter("oprojectName");
                    if (oprojectName != null && !oprojectName.equals("")) {
                        this.getRequest().setAttribute("oprojectName", oprojectName);
                    }
                    String overDays = getRequest().getParameter("overDays");
                    if (overDays != null && !overDays.equals("")) {
                        this.getRequest().setAttribute("overDays1", overDays);
                    }
                    String ostartDate = getRequest().getParameter("ostartDate");
                    if (ostartDate != null && !ostartDate.equals("")) {
                        this.getRequest().setAttribute("ostartDate", ostartDate);
                    }
                    String oendDate = getRequest().getParameter("oendDate");
                    if (oendDate != null && !oendDate.equals("")) {
                        this.getRequest().setAttribute("oendDate", oendDate);
                    }

                    String cprojectName = getRequest().getParameter("cprojectName");
                    if (cprojectName != null && !cprojectName.equals("")) {
                        this.getRequest().setAttribute("cprojectName", cprojectName);
                    }
                    String cstartDate = getRequest().getParameter("cstartDate");
                    if (cstartDate != null && !cstartDate.equals("")) {
                        this.getRequest().setAttribute("cstartDate", cstartDate);
                    }
                    String cendDate = getRequest().getParameter("cendDate");
                    if (cendDate != null && !cendDate.equals("")) {
                        this.getRequest().setAttribute("cendDate", cendDate);
                    }
                    String pprojectName = getRequest().getParameter("pprojectName");
                    if (pprojectName != null && !pprojectName.equals("")) {
                        this.getRequest().setAttribute("pprojectName", pprojectName);
                    }
                    String uprojectName = getRequest().getParameter("uprojectName");
                    if (uprojectName != null && !uprojectName.equals("")) {
                        this.getRequest().setAttribute("uprojectName", uprojectName);
                    }
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYGUARANTEE).getTemplateFilePath());
                    //MYHOMEGUARANTEE
                }
                this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION,bpCustMember);
                if (null != isMobile && !"".equals(isMobile) && "1".equals(isMobile)) {
                    StringBuffer sb = new StringBuffer();
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                    sb.append("{\"success\":true,\"bpcust\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"levelMark\":");
                    sb.append(gson.toJson(levelMark));
                    setJsonString(sb.toString());
                    return SUCCESS;

                }
			/*	BpCustRelation relation1 = bpCustRelationService.getCustOffine(mem.getId(), "p_financial");
				if(null!=relation1){
						manageFinancialList("1");
						// 个人理财顾问账户登录，主页
						mem.setPerCompanyType(Short.valueOf("1"));
						this.getSession().setAttribute("loginType", "person");//保存登录类型
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYHOMEFINANCIAL).getTemplateFilePath());
					}*/
            } else if (typ == 1) {// 个人信息修改页面
                cardList(bpCustMember);
                /*if(bpCustMember.getIsCheckCard().equals("1")){

                }*/
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.EDITUSERINFO).getTemplateFilePath());
            } else if (typ == 2) {// 修改密码
                this.getRequest().setAttribute("toAction", this.getRequest().getParameter("action"));
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.CHANGELOGINPASSWORD)
                        .getTemplateFilePath());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        if (mobile != null) {
            //资金流水记录
            ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), ObSystemAccount.type0);
            if (account != null) {
                Long accountId = account.getId();
                listcount = obAccountDealInfoService.getDealInfoByPersionId(accountId);
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILECENTER).getTemplateFilePath());
        }
        return "freemarker";
    }


    /**
     * 理财计划列表
     * state 1 购买中 2持有中
     */
    public void managelist(String state) {
        try {
            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            if (mem != null) {
                QueryFilter filter = new QueryFilter(getRequest());
                filter.addFilter("Q_investPersonId_L_EQ", mem.getId());
                filter.getPagingBean().setStart(0);
                filter.getPagingBean().setPageSize(5);
                filter.addFilterIn("Q_state_SN_IN", Arrays.asList(Short.valueOf(state)));
                filter.addFilterIn("Q_keystr_S_IN", Arrays.asList("mmplan", "UPlan"));
                List<PlManageMoneyPlanBuyinfo> list = plManageMoneyPlanBuyinfoService.getAll(filter);
                this.getRequest().setAttribute("mmlist" + state, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人中心借款管理列表查询方法（招标中，待还款，已结清）
     *
     * @return
     */
    public String newLoanmanagement() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        try {
            List<ShowManageMoney> list = new ArrayList<ShowManageMoney>();
            List<ShowManageMoney> list2 = new ArrayList<ShowManageMoney>();
            String planstate = this.getRequest().getParameter("planstate");
            if ("7".equals(planstate)) {
                list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemt", this.getRequest());
                list2 = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemt", null);
                jsonString = createJson(list, list2);
            } else if ("10".equals(planstate)) {
                list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemtFinish", this.getRequest());
                list2 = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemtFinish", null);
                jsonString = createJson(list, list2);
            } else if ("-1".equals(planstate)) {
                List<BpFinanceApplyUser> list1 = new ArrayList<BpFinanceApplyUser>();
                BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
                applyUser.setUserID(mem.getId());
                list1 = financeApplyUserService.getApplyUser(applyUser);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                ;
                Type type = new TypeToken<List<BpFinanceApplyUser>>() {
                }.getType();
                StringBuffer buff = new StringBuffer("{\"success\":\"true\",\"totalCounts\":").append(list1 != null ? list1.size() : 0).append(",\"result\":");
                buff.append(gson.toJson(list1, type));
                buff.append("}");
                jsonString = buff.toString();
            } else {
                list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemtBefore", this.getRequest());
                list2 = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), null, "repayMemtBefore", null);
                jsonString = createJson(list, list2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String createJson(List<ShowManageMoney> list, List<ShowManageMoney> list2) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        ;
        Type type = new TypeToken<List<T>>() {
        }.getType();
        StringBuffer buff = new StringBuffer("{\"success\":\"true\",\"totalCounts\":").append(list2 != null ? list2.size() : 0).append(",\"result\":");
        buff.append(gson.toJson(list, type));
        buff.append("}");
        System.out.println("jsonString=" + jsonString);
        return buff.toString();
    }

    /**
     * 个人中心总览---近期待收投资
     */
    public List<FundPay> newManageMoney(BpCustMember mem, String state, Pager pager) {
        List<FundPay> showManageMoneylist = new ArrayList<FundPay>();
        try {
            if ("7".equals(state)) {//回款中
                showManageMoneylist = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), null, "InvestRepayment", null);
            } else if ("10".equals(state)) {//已结清
                showManageMoneylist = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtFinish", null);
            } else if ("-1".equals(state)) {//投资失败
                showManageMoneylist = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBeforeFaild", null);
            } else {//投资成功（尚未放款）
                showManageMoneylist = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBefore", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.getRequest().setAttribute("ShowManageMoney", showManageMoneylist);
        return showManageMoneylist;
    }

    /**
     * 稳安总览---统计
     *
     * @param mem
     */
    private void total(BpCustMember mem) {
        try {
            loanTotal = slFundIntentService.getMoney(mem.getId(), "total");//借款金额

            //投资总额
            investMoney = plBidInfoService.getLoanTotal(mem.getId(), "intent");
            investMoney = investMoney != null ? investMoney : new BigDecimal(0);
            investLoanMoneytotal = investLoanMoneytotal.add((investMoney.add(bpCustMember.getTotalMoney())));
            if (loanTotal != null && investLoanMoneytotal != null) {
                investLoanMoneytotal = investLoanMoneytotal.subtract(loanTotal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void commoon(BpCustMember mem) {
        bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());
        try {
            //////
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
            if ("1".equals(bpCustMember.getIsCheckCard())) {/*
				ObSystemAccount ob = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), (short)0);
				if(ob!=null){
					ob.setTotalMoney(bpCustMember.getAvailableInvestMoney());
					obSystemAccountService.save(ob);
				}
			*/
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
		/*if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
			//判断手机是否验证
			percent += 30;
		}*/
        //保存信誉等级
        this.getSession().setAttribute("safetyLevel", percent);
        //查询基础材料
        QueryFilter filter = new QueryFilter(this.getRequest());
        filter.addFilter("Q_operationType_S_EQ", "person");//默认查询出个人的
        filter.addFilter("Q_materialState_L_GE", 1l);
        filter.getPagingBean().setPageSize(1000000);
        basisMaterialList = p2pLoanBasisMaterialService.getAll(filter);
        //判断材料是否上传
        for (P2pLoanBasisMaterial material : basisMaterialList) {
            QueryFilter filter2 = new QueryFilter(getRequest());
            filter2.addFilter("Q_materialId_L_EQ", material.getMaterialId());
            filter2.addFilter("Q_userID_L_EQ", bpCustMember.getId());
            List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter2);
            if (applys.size() > 0) {
                material.setWebFinanceApplyUploadsList(applys);
                material.setImgUrl(applys.get(0).getFiles());
                material.setStatus(applys.get(0).getStatus());
                material.setRejectReason(applys.get(0).getRejectReason());
            }
        }
    }


    public String checkThirdPartyMoney() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();

        sb.append("{\"success\":true,\"msg\":");
        try {
            //////
            BigDecimal[] ret = obSystemAccountService.checkThirdPartyMoney(bpCustMember.getId(), ObSystemAccount.type0.toString());
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
                sb.append(gson.toJson("余额更新成功!"));
                sb.append(",\"money\":");
                sb.append("\"" + ret[3] + "\"");

            }
            if ("1".equals(bpCustMember.getIsCheckCard())) {
                ObSystemAccount ob = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), (short) 0);
                if (ob != null) {
                    ob.setTotalMoney(bpCustMember.getAvailableInvestMoney());
                    obSystemAccountService.save(ob);
                }
            }
        } catch (Exception e) {
            sb.append(gson.toJson("余额更新失败!"));
            sb.append(",\"money\":");
            sb.append("\"0.00\"");
            e.printStackTrace();
        }
        //bpCustMember = obSystemAccountService.getAccountSumMoney(mem);

        sb.append("}");
        setJsonString(sb.toString());
        System.out.println("JSON+++++++" + sb.toString());

        return SUCCESS;
    }

    /**
     * 个人中心总览---借款
     *
     * @param mem
     */
    private void loanSet(BpCustMember mem) {
        try {
            bidPlanLoan = new ArrayList<PlBidPlan>();
            List<BpFundIntent> list = bpFundIntentService.getLoanPlanId(mem.getId());
            if (list != null && list.size() > 0) {
                for (BpFundIntent fund : list) {
                    PlBidPlan plBidPlan = plBidPlanService.get(fund.getBidPlanId());
                    String[] ret = bpFundIntentService.getFundInfoByPlanId(new Long(fund.getBidPlanId()), this.getRequest());
                    plBidPlan.setNotRepaymentMoney(ret[0]);
                    plBidPlan.setRepayMentLength((Integer.valueOf(ret[1])) + "/" + ret[2]);
                    //还款日减少5天
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                    java.util.Date next = sdf.parse(ret[3]);
                    cal.setTime(next);
                    cal.add(Calendar.DAY_OF_YEAR, -5);
                    Date newdt1 = cal.getTime();
                    String str = sdf.format(newdt1);

                    plBidPlan.setNextRepaymentDate(str);
                    plBidPlan.setRepaymentFullDate(ret[4]);
                    java.util.Date nextTime = slFundIntentService.getIntentDate(fund.getBidPlanId(), Integer.valueOf(ret[1]), "next");
                    plBidPlan.setNextTime(nextTime == null ? null : nextTime);
                    plBidPlan.setNextMoney(slFundIntentService.getnotMoneySum(fund.getBidPlanId(), Integer.valueOf(ret[1]) + 1));

                    //java.util.Date now = slFundIntentService.getIntentDate(fund.getBidPlanId(),Integer.valueOf(ret[1]), null);
                    java.util.Date now = DateUtil.parseDate(ret[3]);
                    if (now != null) {
                        if ((new Date().getTime()) > now.getTime()) {
                            plBidPlan.setIsDate(1);
                        } else {
                            plBidPlan.setIsDate(0);
                        }
                    } else {
                        plBidPlan.setIsDate(0);
                    }
                    plBidPlan.setUrl(plManageMoneyPlanBuyinfoService.getUrl(fund.getOrderNo(), ""));//下载合同路径
                    java.math.BigDecimal loanMoney = bpFundIntentService.getEveryTimeMoney(fund.getProjectId());
                    plBidPlan.setLoanMoney(loanMoney);
                    bidPlanLoan.add(plBidPlan);


                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人中心总览---理财
     *
     * @param mem
     */
    private void manageMoney(BpCustMember mem) {

        try {
			/*List<PlBidInfo> listBidInfo = plBidInfoService.getBidOrderNoList(mem.getId(),null);
			bidPlanFinancial = new ArrayList<PlBidPlan>();
			if(listBidInfo!=null&&listBidInfo.size()>0){
				for (PlBidInfo info : listBidInfo) {
					PlBidPlan plan = plBidPlanService.get(info.getBidId());
					plan.setUrl(plManageMoneyPlanBuyinfoService.getUrl(info.getBidId(), ""));//下载合同路径
					plan.setInvestmentTime(info.getBidtime());
					PlBidPlan PlBid = plBidPlanService.returnPlBidPlan(plan);
					plan.setUserMoney(plBidInfoService.getLoanTotal(mem.getId(),"intent"));
					plan.setInterestRate(PlBid.getInterestRate());
					plan.setLoanLife(PlBid.getLoanLife());
					plan.setBackMoneyEd(bpFundIntentService.getTotal(info.getBidId(), "afterMoney"));
					plan.setBackMoneyIng(bpFundIntentService.getTotal(info.getBidId(), "notMoney"));
					bidPlanFinancial.add(plan);
				}
			}*/
            Pager p = new Pager();//myhome初始化加载5条
            p.setPageNumber(0);
            p.setPageSize(5);
            bidPlanFinancial = new ArrayList<PlBidPlan>();
            List<BpFundIntent> list = bpFundIntentService.getIntentList(mem.getId(), null, p);
            if (list != null && list.size() > 0) {
                for (BpFundIntent fund : list) {
                    PlBidPlan plan = plBidPlanService.get(fund.getBidPlanId());

                    String url = "";
                    List<PlBidInfo> listBidInfoUrl2 = plBidInfoService.getIntentInfo(plan.getBidId(), mem.getId());
                    if (listBidInfoUrl2 != null && listBidInfoUrl2.size() > 0) {
                        for (PlBidInfo bp : listBidInfoUrl2) {
                            url = plManageMoneyPlanBuyinfoService.getUrl(bp.getOrderNo(), "");//模板下载路径
                        }
                    }

                    plan.setUrl(url);//下载合同路径
                    plan.setInvestmentTime(fund.getIntentDate());
                    PlBidPlan PlBid = plBidPlanService.returnPlBidPlan(plan);
                    plan.setUserMoney(plBidInfoService.getLoanTotal(fund.getProjectId(), "userMoney"));
                    plan.setInterestRate(PlBid.getInterestRate());
                    plan.setLoanLife(PlBid.getLoanLife());
                    //zh
                    plan.setAfterMoneyTotal(plBidInfoService.queryUserMoney(PlBid.getBidId(), mem.getId(), null));
                    java.math.BigDecimal notMoneyTotal = bpFundIntentService.getMoney(PlBid.getBidId(), mem.getLoginname(), "notMoney", fund.getOrderNo());
                    plan.setNotMoneyTotal(notMoneyTotal == null ? new BigDecimal(0) : notMoneyTotal);
                    //h
                    plan.setBackMoneyEd(bpFundIntentService.getTotal(fund.getBidPlanId(), "afterMoney"));
                    plan.setBackMoneyIng(bpFundIntentService.getTotal(fund.getBidPlanId(), "notMoney"));
                    bidPlanFinancial.add(plan);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inient(BpCustMember mem) {
        try {
            bpCustMember.setPrincipal(bpFundIntentService.getTotle("principalRepayment", mem.getLoginname()) == null ? new java.math.BigDecimal(0) : bpFundIntentService.getTotle("principalRepayment", mem.getLoginname()));//代收本金
            bpCustMember.setAccrual(bpFundIntentService.getTotle("loanInterest", mem.getLoginname()) == null ? new java.math.BigDecimal(0) : bpFundIntentService.getTotle("loanInterest", mem.getLoginname()));//代收利息
            bpCustMember.setPaymentPlanNum(slFundIntentService.getListByBidPlanId(mem.getLoginname()));//回款计划的笔数
            bpCustMember.setMonthIncome(bpFundIntentService.getincome(null, mem.getLoginname()) == null ? new java.math.BigDecimal(0) : bpFundIntentService.getincome(null, mem.getLoginname()));
            bpCustMember.setYearIncome(bpFundIntentService.getincome("newYears", mem.getLoginname()) == null ? new java.math.BigDecimal(0) : bpFundIntentService.getincome("newYears", mem.getLoginname()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unused")
    private void getFundIncome(BpCustMember mem) {
        try {
            bpCustMember = bpCustMemberService.get(mem.getId());
            QueryFilter filter = new QueryFilter(getRequest());
            filter.addFilter("Q_investPersonName_S_EQ", bpCustMember.getLoginname());
            //filter.addFilter("Q_payintentPeriod_N_GT", "0");
            filter.addFilter("Q_fundType_S_NEQ", "principalLending");
            if (pager == null) {
                pager = new Pager();
                pager.setPageSize(10);
            }
            filter.getPagingBean().setStart((pager.getPageNumber() - 1) * pager.getPageSize());
            filter.getPagingBean().setPageSize(pager.getPageSize());
            List<FundIncome> list = bpFundIntentService.getIncome(getRequest(), filter.getPagingBean(), bpCustMember.getId());
            pager.setTotalCount(filter.getPagingBean().getTotalItems());
            pager.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取个人中心 我的投资列表
     *
     * @param mem
     */
    private void financeInint(BpCustMember mem) {

        try {
            bpCustMember = bpCustMemberService.get(mem.getId());
            this.getRequest().setAttribute("userType", "0");
            this.getRequest().setAttribute("userId", bpCustMember.getId().toString());
            //	List<Investproject> list = plManageMoneyPlanBuyinfoService.getPersonInvestProject(this.getRequest(), 0, 3);
            //List<Investproject> listcount = plManageMoneyPlanBuyinfoService.getPersonInvestProject(this.getRequest(), null, null);
            //	List<Investproject> listcount1 = plManageMoneyPlanBuyinfoService.getPersonInvestProject("0", bpCustMember.getId().toString(), null);
            //	List<Investproject> listcount2 = plManageMoneyPlanBuyinfoService.getPersonInvestProject("0", bpCustMember.getId().toString(), "1");

            BigDecimal investmentNumber = plManageMoneyPlanBuyinfoService.investmentBidNum("0", bpCustMember.getId().toString(), "investmentNumber");//累计投资笔数
            BigDecimal investmentBidNum = plManageMoneyPlanBuyinfoService.investmentBidNum("0", bpCustMember.getId().toString(), "investmentBidNum");//招标中的笔数
            BigDecimal investmentBackNum = plManageMoneyPlanBuyinfoService.investmentBidNum("0", bpCustMember.getId().toString(), "investmentBackNum");
            ;//回款中总数
            BigDecimal totalInvestMoney = plManageMoneyPlanBuyinfoService.investmentBidNum("0", bpCustMember.getId().toString(), "totalInvestMoney");
            ;//回款中总数
            BigDecimal notallInterest = plManageMoneyPlanBuyinfoService.investmentBidNum("0", bpCustMember.getId().toString(), "notallInterest");
            ;//回款中总数
            bpCustMember.setTotalInvestMoney(totalInvestMoney);//累积投资额
            bpCustMember.setInvestmentNumber(investmentNumber.intValue());//累计投资笔数
            bpCustMember.setInvestmentBackNum(investmentBackNum.intValue());//回款中的笔数
            bpCustMember.setInvestmentBidNum(investmentBidNum.intValue());//招标中的笔数
            bpCustMember.setNotallInterest(notallInterest);//招标中的笔数
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加及保存操作
     */
/*	public String save() {
		if (bpCustMember.getId() == null) {
			BpCustMember member = bpCustMemberService.isExist(bpCustMember.getLoginname());
			if(member!=null){
				setJsonString("{success:false,msg:'用户已经存在'}");
			}else{
				BpCustMember email = bpCustMemberService.isExistEmail(bpCustMember.getEmail());
				if(email!=null){
					setJsonString("{success:false,msg:'邮箱已经存在'}");
				}else{
					bpCustMember = bpCustMemberService.save(bpCustMember);
				}
			}
		} else {
			System.out.println(bpCustMember.getId());
			BpCustMember orgBpCustMember = bpCustMemberService.get(bpCustMember.getId());
			try {
				BeanUtil.copyNotNullProperties(orgBpCustMember, bpCustMember);
				bpCustMemberService.save(orgBpCustMember);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}*/

    /**
     * 修改会员信息
     */
    public String update() {

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            // 将数据转成JSON格式
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            StringBuffer sb = new StringBuffer();
            if (mem.getId() == null) {
                bpCustMember = new BpCustMember();
                if (loginname != null) {
                    bpCustMember.setLoginname(loginname);
                }
                if (truename != null) {
                    bpCustMember.setTruename(truename);
                }
                if (telphone != null) {
                    bpCustMember.setTelphone(telphone);
                }
                if (email != null) {
                    bpCustMember.setEmail(email);
                }
                if (sex != null) {
                    bpCustMember.setSex(sex);
                }
                if (cardtype != null) {
                    bpCustMember.setCardtype(cardtype);
                }
                if (cardcode != null) {
                    bpCustMember.setCardcode(cardcode);
                }
                if (birthday != null) {
                    bpCustMember.setBirthday(birthday);
                }
                if (headImage != null) {
                    bpCustMember.setHeadImage(headImage);
                }
                if (nativePlaceProvice != null) {
                    bpCustMember.setNativePlaceProvice(nativePlaceProvice);
                }
                if (nativePlaceCity != null) {
                    bpCustMember.setNativePlaceCity(nativePlaceCity);
                }
                if (nation != null) {
                    bpCustMember.setNation(nation);
                }
                if (homePhone != null) {
                    bpCustMember.setHomePhone(homePhone);
                }
                if (relationAddress != null) {
                    bpCustMember.setRelationAddress(relationAddress);
                }
                if (postCode != null) {
                    bpCustMember.setPostCode(postCode);
                }
                if (QQ != null) {
                    bpCustMember.setQq(QQ);
                }
                if (MSN != null) {
                    bpCustMember.setMSN(MSN);
                }
                if (securityQuestion != null) {
                    bpCustMember.setSecurityQuestion(securityQuestion);
                }
                if (securityAnswer != null) {
                    bpCustMember.setSecurityAnswer(securityAnswer);
                }
                if (liveProvice != null) {
                    bpCustMember.setLiveProvice(liveProvice);
                }
                if (liveCity != null) {
                    bpCustMember.setLiveCity(liveCity);
                }
                if (marry != null) {
                    bpCustMember.setMarry(marry);
                }
                if (teacherPosition != null) {
                    bpCustMember.setTeacherPosition(teacherPosition);
                }
                if (fax != null) {
                    bpCustMember.setFax(fax);
                }
                if (memberOrderId != null) {
                    bpCustMember.setMemberOrderId(memberOrderId);
                }

                bpCustMemberService.save(bpCustMember);
                sb.append("{\"success\":true,\"data\":");
                sb.append(gson.toJson(bpCustMember));
                sb.append(",\"result\":1");
                sb.append("}");
            } else {
                BpCustMember orgBpCustMember = bpCustMemberService.get(mem
                        .getId());
                try {
                    // BeanUtil.copyNotNullProperties(orgBpCustMember,
                    // bpCustMember);

                    if (loginname != null) {
                        orgBpCustMember.setLoginname(loginname);
                    }
                    if (truename != null) {
                        orgBpCustMember.setTruename(truename);
                    }
                    if (telphone != null) {
                        orgBpCustMember.setTelphone(telphone);
                    }
                    if (email != null) {
                        orgBpCustMember.setEmail(email);
                    }
                    if (sex != null) {
                        orgBpCustMember.setSex(sex);
                    }
                    if (cardtype != null) {
                        orgBpCustMember.setCardtype(cardtype);
                    }
                    if (cardcode != null) {
                        orgBpCustMember.setCardcode(cardcode);
                    }
                    if (birthday != null) {
                        orgBpCustMember.setBirthday(birthday);
                    }
                    if (headImage != null) {
                        orgBpCustMember.setHeadImage(headImage);
                    }
                    if (nativePlaceProvice != null) {
                        orgBpCustMember
                                .setNativePlaceProvice(nativePlaceProvice);
                    }
                    if (nativePlaceCity != null) {
                        orgBpCustMember.setNativePlaceCity(nativePlaceCity);
                    }
                    if (nation != null) {
                        orgBpCustMember.setNation(nation);
                    }
                    if (homePhone != null) {
                        orgBpCustMember.setHomePhone(homePhone);
                    }
                    if (relationAddress != null) {
                        orgBpCustMember.setRelationAddress(relationAddress);
                    }
                    if (postCode != null) {
                        orgBpCustMember.setPostCode(postCode);
                    }
                    if (QQ != null) {
                        orgBpCustMember.setQq(QQ);
                    }
                    if (MSN != null) {
                        orgBpCustMember.setMSN(MSN);
                    }
                    if (securityQuestion != null) {
                        orgBpCustMember.setSecurityQuestion(securityQuestion);
                    }
                    if (securityAnswer != null) {
                        orgBpCustMember.setSecurityAnswer(securityAnswer);
                    }
                    if (liveProvice != null) {
                        orgBpCustMember.setLiveProvice(liveProvice);
                    }
                    if (liveCity != null) {
                        orgBpCustMember.setLiveCity(liveCity);
                    }
                    if (marry != null) {
                        orgBpCustMember.setMarry(marry);
                    }
                    if (fax != null) {
                        orgBpCustMember.setFax(fax);
                    }
                    if (memberOrderId != null) {
                        orgBpCustMember.setMemberOrderId(memberOrderId);
                    }
                    bpCustMemberService.merge(orgBpCustMember);
                    bpCustMember = orgBpCustMember;
                    sb.append("{\"success\":true,\"data\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"result\":1");
                    sb.append("");
                    sb.append("}");
                    // this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.SHOWEDITUSERINFONOTICE).getTemplateFilePath());
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    sb.append("{\"success\":false,\"errMsg\":");
                    sb.append(gson.toJson("详细资料修改失败"));
                    sb.append("}");
                }
            }
            setJsonString(sb.toString());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;

    }

    /**
     * 判断补录的推荐码是否存在
     *
     * @return
     */
    public String isExistRecommand() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();

        BpCustMember member = bpCustMemberService.isRecommandPerson(recommandPerson);

        if (member != null) {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("该推荐码存在!"));
            sb.append(",\"result\":1");
            sb.append("}");
        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson("该推荐码不存在!"));
            sb.append(",\"result\":0");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    protected String departmentRecommend;//部门的推荐码

    public String getDepartmentRecommend() {
        return departmentRecommend;
    }

    public void setDepartmentRecommend(String departmentRecommend) {
        this.departmentRecommend = departmentRecommend;
    }

    /**
     * 判断部门的推荐码是否存在
     *
     * @return
     */
    public String isExistdepartRecommand() {
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();

        boolean member = bpCustMemberService.organiz(departmentRecommend);

        if (member == true) {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("该推荐码存在!"));
            sb.append(",\"result\":1");
            sb.append("}");
        } else {
            sb.append("{\"success\":false,\"errMsg\":");
            sb.append(gson.toJson("该推荐码不存在!"));
            sb.append(",\"result\":0");
            sb.append("}");
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 保存补录的推荐码
     *
     * @return
     */
    public String updateRecommand() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (null != mem) {
            // 将数据转成JSON格式
            StringBuffer sb = new StringBuffer();
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            orgBpCustMember.setRecommandPerson(recommandPerson);//直接推荐码
            this.getSession().setAttribute("recommandPerson", recommandPerson);
            //二级推荐码
            if (null != bpCustMemberService.getInviter(recommandPerson) && !"".equals(bpCustMemberService.getInviter(recommandPerson))) {
                orgBpCustMember.setSecondRecommandPerson(bpCustMemberService.getInviter(recommandPerson).getPlainpassword());
            }
            bpCustMemberService.merge(orgBpCustMember);
            sb.append("{\"success\":true,\"msg\":\"保存成功!\"}");
            setJsonString(sb.toString());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;

    }

    public String wenAnUpdate() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        } else {
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            //bpCustMemberService.merge(orgBpCustMember);
            bpCustMember = orgBpCustMember;

            WebFinanceApplyUploadsService webFinanceApplyUploadsService = (WebFinanceApplyUploadsService) AppUtil.getBean("webFinanceApplyUploadsService");
            //查询材料表
            webFinanceApplylist = webFinanceApplyUploadsService.getUploadState(bpCustMember.getId());
            //	String material = this.getRequest().getParameter("material");
			/*if(material!=null){
				//this.getRequest().getSession().setAttribute("no", "6");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.WORK).getTemplateFilePath());
				return "freemarker";
			}*/
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.PERSON).getTemplateFilePath());
            return "freemarker";
        }
    }


    @SuppressWarnings("unused")
    private BpCustMember assets(BpCustMember cust) {
        if (havehouse != null) {
            cust.setHavehouse(havehouse);
        }
        if (havehouseloan != null) {
            cust.setHavehouseloan(havehouseloan);
        }
        if (havecar != null) {
            cust.setHavecar(havecar);
        }
        if (havecarloan != null) {
            cust.setHavecarloan(havecarloan);
        }
        return cust;
    }

    @SuppressWarnings("unused")
    private BpCustMember work(BpCustMember cust) {
        if (hireCompanyname != null) {
            cust.setHireCompanyname(hireCompanyname);
        }
        if (hirePosition != null) {
            cust.setHirePosition(hirePosition);
        }
        if (hireMonthlyincome != null) {
            cust.setHireMonthlyincome(hireMonthlyincome);
        }
        if (hireEmail != null) {
            cust.setHireEmail(hireEmail);
        }
        if (hireProvince != null) {
            cust.setHireProvince(hireProvince);
        }
        if (hireCity != null) {
            cust.setHireCity(hireCity);
        }
        if (hireAddress != null) {
            cust.setHireAddress(hireAddress);
        }
        if (hireCompanytype != null) {
            cust.setHireCompanytype(hireCompanytype);
        }
        if (hireCompanycategory != null) {
            cust.setHireCompanycategory(hireCompanycategory);
        }
        if (hireCompanysize != null) {
            cust.setHireCompanysize(hireCompanysize);
        }
        if (hireStartyear != null) {
            cust.setHireStartyear(hireStartyear);
        }
        if (this.getRequest().getParameter("hireCompanyphone_Prefix") != null) {
            String phone = this.getRequest().getParameter("hireCompanyphone_Prefix");
            hireCompanyphone += (phone + "-");
        }
        if (this.getRequest().getParameter("hireCompanyphone_Suffix") != null) {
            hireCompanyphone += this.getRequest().getParameter("hireCompanyphone_Suffix");
        }
        if (hireCompanyphone != null) {
            cust.setHomePhone(hireCompanyphone);
        }
        return cust;
    }

    @SuppressWarnings("unused")
    private BpCustMember theNetwork(BpCustMember cust) {
        if (webshopName != null) {
            cust.setWebshopName(webshopName);
        }
        if (webshopMonthlyincome != null) {
            cust.setWebshopMonthlyincome(webshopMonthlyincome);
        }
        if (webshopEmail != null) {
            cust.setWebshopEmail(webshopEmail);
        }
        if (webshopProvince != null) {
            cust.setWebshopProvince(webshopProvince);
        }
        if (webshopCity != null) {
            cust.setWebshopCity(webshopCity);
        }
        if (webshopAddress != null) {
            cust.setWebshopAddress(webshopAddress);
        }

        if (this.getRequest().getParameter("webshopStartyears") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String str = this.getRequest().getParameter("webshopStartyears");

            cust.setWebshopStartyear(str);


        }
        if (this.getRequest().getParameter("webshopPhone_Prefix") != null) {
            String phone = this.getRequest().getParameter("webshopPhone_Prefix");
            webshopPhone += (phone + "-");
        }
        if (this.getRequest().getParameter("webshopPhone_Suffix") != null) {
            webshopPhone += this.getRequest().getParameter("webshopPhone_Suffix");
        }
        if (webshopPhone != null) {
            cust.setHomePhone(webshopPhone);
        }
        return cust;
    }

    @SuppressWarnings("unused")
    private BpCustMember family(BpCustMember cust) {

        if (marry != null) {
            cust.setMarry(marry);
        }
        if (havechildren != null) {
            cust.setHavechildren(havechildren);
        }
        if (relDirName != null) {
            cust.setRelDirName(relDirName);
        }
        if (relDirType != null) {
            cust.setRelDirType(relDirType);
        }
        if (relDirPhone != null) {
            cust.setRelDirPhone(relDirPhone);
        }
        if (relOtherName != null) {
            cust.setRelOtherName(relOtherName);
        }
        if (relOtherType != null) {
            cust.setRelOtherType(relOtherType);
        }
        if (relOtherPhone != null) {
            cust.setRelOtherPhone(relOtherPhone);
        }
        if (relFriendName != null) {
            cust.setRelFriendName(relFriendName);
        }
        if (relFriendType != null) {
            cust.setRelFriendType(relFriendType);
        }
        if (relFriendPhone != null) {
            cust.setRelFriendPhone(relFriendPhone);
        }
        return cust;
    }

    @SuppressWarnings("unused")
    private BpCustMember personalInformation(BpCustMember cust) {
        if (collegeDegree != null) {
            cust.setCollegeDegree(collegeDegree);
        }
        if (collegeYear != null) {
            cust.setCollegeYear(collegeYear);
        }
        if (collegename != null) {
            cust.setCollegename(collegename);
        }
        if (nativePlaceProvice != null) {
            cust.setNativePlaceProvice(nativePlaceProvice);
        }
        if (nativePlaceCity != null) {
            cust.setNativePlaceCity(nativePlaceCity);
        }
        if (this.getRequest().getParameter("liveProvicenew") != null) {
            cust.setLiveProvice(Long.valueOf(this.getRequest().getParameter("liveProvicenew")));
        }
        if (this.getRequest().getParameter("liveCitynew") != null) {
            cust.setLiveCity(Long.valueOf(this.getRequest().getParameter("liveCitynew")));
        }
        if (relationAddress != null) {
            cust.setRelationAddress(relationAddress);
        }
        if (postCode != null) {
            cust.setPostCode(postCode);
        }
        if (this.getRequest().getParameter("homePhone_Prefix") != null) {
            String phone = this.getRequest().getParameter("homePhone_Prefix");
            homePhone += (phone + "-");
        }
        if (this.getRequest().getParameter("homePhone_Suffix") != null) {
            homePhone += this.getRequest().getParameter("homePhone_Suffix");
        }
        if (homePhone != null) {
            cust.setHomePhone(homePhone);
        }
        return cust;
    }

    /**
     * 修改会员信息成功跳转页面
     */
    public String updateSuccess() {

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        if (mem != null) {
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            bpCustMember = orgBpCustMember;
            sb.append("{\"success\":true,\"data\":");
            sb.append(gson.toJson(bpCustMember));
            sb.append(",\"result\":1");
            sb.append("}");
            this
                    .setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                            DynamicConfig.SHOWEDITUSERINFONOTICE)
                            .getTemplateFilePath());
            setJsonString(sb.toString());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }

        return "freemarker";

    }

    public String resetPassword() {
        MD5 md5 = new MD5();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        String id = getRequest().getParameter("id");

        String random = getRequest().getParameter("random");
        String isMobile = getRequest().getParameter("isMobile");
        String session_userId = (String) this.getSession().getAttribute(random);

        if (isMobile != null) {
            if (random != null && !"".equals(random)) {
                //	bpCustMember = bpCustMemberService.get(Long.parseLong(id));
                bpCustMember = bpCustMemberService.get(Long.parseLong(random));
                if (bpCustMember != null) {
                    this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
                    bpCustMember.setPassword(md5.md5(password, "utf-8"));
                    bpCustMemberService.merge(bpCustMember);
                    sb.append("{\"success\":true,\"data\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"result\":1");
                    sb.append(",\"errMsg\":");
                    sb.append(gson.toJson("修改成功"));
                    sb.append("}");
                } else {
                    sb.append("{\"success\":true,\"errMsg\":");
                    sb.append(gson.toJson("修改失败"));
                    sb.append(",\"result\":0");
                    sb.append("}");
                }
            } else {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("修改失败"));
                sb.append(",\"result\":0");
                sb.append("}");
            }
        } else {
            if (session_userId != null && !"".equals(session_userId)) {
                //	bpCustMember = bpCustMemberService.get(Long.parseLong(id));
                bpCustMember = bpCustMemberService.get(Long.parseLong(session_userId));
                if (bpCustMember != null) {
                    this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
                    bpCustMember.setPassword(md5.md5(password, "utf-8"));
                    bpCustMemberService.merge(bpCustMember);
                    sb.append("{\"success\":true,\"data\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"result\":1");
                    sb.append(",\"errMsg\":");
                    sb.append(gson.toJson("修改成功"));
                    sb.append("}");
                } else {
                    sb.append("{\"success\":true,\"errMsg\":");
                    sb.append(gson.toJson("修改失败"));
                    sb.append(",\"result\":0");
                    sb.append("}");
                }
            } else {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("修改失败"));
                sb.append(",\"result\":0");
                sb.append("}");
            }
        }

		/*bpCustMember=null;
		this.getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);//将用户信息从session中注销
*/
        setJsonString(sb.toString());
        return SUCCESS;
    }


    public  String appResetPassword(){
        MobileDataResultModel model = new MobileDataResultModel();
        MD5 md5 = new MD5();
        //获取随机数
        String uuid = (String) this.getSession().getAttribute(MyUserSession.FINDPWD_RANDOM_SESSION);
        //用户id
        String userid = (String) this.getSession().getAttribute(uuid);
        if(userid!=null&&!"".equals(userid)){
            bpCustMember = bpCustMemberService.get(Long.parseLong(userid));
            if(bpCustMember!=null&&!"".equals(bpCustMember)){
                this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
                bpCustMember.setPassword(md5.md5(password, "utf-8"));
                bpCustMemberService.merge(bpCustMember);
                model.addDataContent("bpCustMember",bpCustMember);
                model.setMsg("修改成功");
            }else{
                model.setMsg("修改失败");
            }
        }else{
            model.setMsg("修改失败");
        }
        setJsonString(model.toJSON());
        return SUCCESS;
    }






    /**
     * 修改会员登录密码
     */
    public String updatePassword() {
        // 判断是否是移动端
        String isMobile = this.getRequest().getParameter("isMobile");

        // 创建json字符串
        StringBuffer sb = new StringBuffer();

        // 将数据转成JSON格式
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        // 声明布尔值判断
        Boolean flag = false;

        // 声明String获取字符串
        String str = "";

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            //判断密码输入错误的次数
            Integer lockerrorNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
            if (lockerrorNum != null && lockerrorNum >= 5) {
                Long lastTime = (Long) this.getSession().getAttribute(MyUserSession.LOCK_TIME);
                Long now = new Date().getTime();
                Long disparity = now - lastTime;
                Long min = disparity / (60 * 1000);
                if (min.intValue() > 5) {//大于五分钟清空缓存
                    this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
                    this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
                    this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, "旧密码输入次数过多，请稍后在试！", "", "", "", "", "");

                    // 判断是否是移动端
                    if (null != isMobile && "1".equals(isMobile)) {
                        // 判断当前状态是false还是true
                        flag = false;

                        // 需返回状态文本
                        str = "旧密码输入次数过多，请稍后在试！";

                    } else {
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.MESSAGE).getTemplateFilePath());
                        return "freemarker";
                    }
                }
            }
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            try {
                if (oldpassword != null) {
                    MD5 md5 = new MD5();
                    String pass = md5.md5(oldpassword, "utf-8");
                    if (pass.equals(orgBpCustMember.getPassword())) {
                        orgBpCustMember.setPassword(md5.md5(password, "utf-8"));
                        String projSys = AppUtil.getProjStr();
                        if (projSys != null && projSys.equals(Constants.PROJ_SYS)) {
                            String type = updateCloud(orgBpCustMember, "2");
                            if (type != null && type.equals("0")) {
                                System.out.println("同步工业云成功");
                            } else {
                                System.out.println("同步工业云失败");
                            }
                        }
                        orgBpCustMember.setIsSync((short) 1);
                        bpCustMemberService.merge(orgBpCustMember);
                        bpCustMember = orgBpCustMember;
                        //设置 返回提示消息
                        webMsgInstance("0", Constants.CODE_SUCCESS, "登录密码修改成功", "", "", "", "", "");

                        // 判断是否是移动端
                        if (null != isMobile && "1".equals(isMobile)) {
                            // 判断当前状态是false还是true
                            flag = true;

                            // 需返回状态文本
                            str = "登录密码修改成功";

                        } else {
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                            return "freemarker";
                        }


                    } else {
                        //判断旧密码输入错误限制
                        Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
                        if (lockNum == null) {
                            lockNum = 0;
                        }
                        this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
                        this.getSession().setAttribute(MyUserSession.lOCK_NUM, (lockNum + 1));
                        this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
                        webMsgInstance("0", Constants.CODE_FAILED, "旧密码输入错误", "", "", "", "", "");


                        // 判断是否是移动端
                        if (null != isMobile && "1".equals(isMobile)) {
                            // 判断当前状态是false还是true
                            flag = false;

                            // 需返回状态文本
                            str = "旧密码输入错误";

                        } else {
                            //设置 返回提示消息
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                            return "freemarker";
                        }

                    }
                } else {
                    //设置 返回提示消息
                    webMsgInstance("0", Constants.CODE_FAILED, "登录密码修改失败", "", "", "", "", "");

                    // 判断是否是移动端
                    if (null != isMobile && "1".equals(isMobile)) {
                        // 判断当前状态是false还是true
                        flag = false;

                        // 需返回状态文本
                        str = "登录密码修改失败";

                    } else {
                        //设置 返回提示消息
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.MESSAGE).getTemplateFilePath());
                        return "freemarker";
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error(ex.getMessage());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        // 判断是否是移动端
        if (null != isMobile && "1".equals(isMobile)) {
            sb.append("{\"success\":");
            sb.append(gson.toJson(flag));
            sb.append(",\"msg\":");
            sb.append(gson.toJson(str));
            sb.append("}");
            setJsonString(sb.toString());
            return SUCCESS;
        }

        return SUCCESS;
    }

    /**
     * 修改会员支付密码
     */
    public String updatePayPasswordPhoneCheck() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        } else {
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());

            String phonecode = (String) this.getSession().getAttribute(
                    MyUserSession.TELPHONE_REG_RANDOM_SESSION);
            try {
                bpCustMember = orgBpCustMember;
                if (verify_sms.equals(phonecode)) {

                    sb.append("{\"success\":true,\"data\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"result\":1");
                    sb.append("}");
                } else {
                    sb.append("{\"success\":true,\"errMsg\":");
                    sb.append(gson.toJson("手机验证码输入错误"));
                    sb.append("}");
                }
            } catch (Exception ex) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("手机验证码输入错误"));
                sb.append("}");
                logger.error(ex.getMessage());
            }


        }
        setJsonString(sb.toString());
        return SUCCESS;

    }

    /**
     * 修改会员支付密码
     */
    public String updatePayPassword() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        } else {
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            try {
                // BeanUtil.copyNotNullProperties(orgBpCustMember,
                // bpCustMember);
                orgBpCustMember.setPaymentCode(paymentCode);
                bpCustMemberService.merge(orgBpCustMember);
                bpCustMember = orgBpCustMember;
                sb.append("{\"success\":true,\"data\":");
                sb.append(gson.toJson(bpCustMember));
                sb.append(",\"result\":1");
                sb.append("}");

            } catch (Exception ex) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("支付密码修改失败"));
                sb.append("}");
                logger.error(ex.getMessage());
            }
        }
        setJsonString(sb.toString());
        return SUCCESS;

    }

    /**
     * 绑定手机号
     */
    @SuppressWarnings({"unused", "static-access"})
    public String bindPhone() {
        System.out.println("手机验证-------------=======" + telphone);
        String verify_sms = this.getRequest().getParameter("verify_sms");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        String phonecode = (String) this.getSession().getAttribute(
                MyUserSession.TELPHONE_REG_RANDOM_SESSION);

        if (null == phonecode || "".equals(phonecode)) {
            sb.append("{\"success\":true,\"errMsg\":");
            sb.append(gson.toJson("0"));
            sb.append("}");
        } else {
            String wad_loginName = (String) this.getSession().getAttribute("wad_loginName");
            String wad_password = (String) this.getSession().getAttribute("wad_password");
            MD5 md5 = new MD5();
            BpCustMember cust = bpCustMemberService.getMemberUserName(wad_loginName, null);//判断用户是否已经存入到表中
            if (cust != null) {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("3"));
                sb.append("}");
            } else {
                BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
                if (member == null) {
                    //往session中放入验证码时拼接了手机号码，所以判断的时候也要拼接手机号，
                    //防止接收验证码的手机号和进数据库的验证码不一致
                    if (phonecode.equals(telphone + verify_sms)) {
                        try {

                            //设置 返回提示消息
                            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                                    MyUserSession.MEMBEER_SESSION);
                            mem.setTelphone(telphone);
                            mem.setIsCheckPhone("1");
                            bpCustMember = bpCustMemberService.save(mem);
                            webMsgInstance("0", Constants.CODE_SUCCESS, "恭喜您手机绑定成功", "", "", "", "", "");
                            this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, mem);
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                            return "freemarker";
                        } catch (Exception ex) {
                            webMsgInstance("0", Constants.CODE_FAILED, "手机绑定失败", "", "", "", "", "");
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                            return "freemarker";
                        }
                    } else {
                        //设置 返回提示消息
                        webMsgInstance("0", Constants.CODE_FAILED, "手机验证码不正确", "", "", "", "", "");
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.MESSAGE).getTemplateFilePath());
                        return "freemarker";
                    }
                } else {
                    //设置 返回提示消息
                    webMsgInstance("0", Constants.CODE_FAILED, "该手机号已经被其他用户注册", "", "", "", "", "");

                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                            DynamicConfig.MESSAGE).getTemplateFilePath());
                    return "freemarker";
                }
            }
        }

        setJsonString(sb.toString());
        return SUCCESS;

    }

    /*
	原来的方法
	public String bindPhone() {
		System.out.println("手机验证-------------======="+telphone);
		String verify_sms = this.getRequest().getParameter("verify_sms");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer();
		String phonecode = (String) this.getSession().getAttribute(
				MyUserSession.TELPHONE_REG_RANDOM_SESSION);

		if(null==phonecode||"".equals(phonecode)){
			sb.append("{\"success\":true,\"errMsg\":");
			sb.append(gson.toJson("0"));
			sb.append("}");
		}else{
			String wad_loginName=(String) this.getSession().getAttribute("wad_loginName");
			String wad_password=(String) this.getSession().getAttribute("wad_password");
			MD5 md5 = new MD5();
			BpCustMember cust = bpCustMemberService.getMemberUserName(wad_loginName,null);//判断用户是否已经存入到表中
			if(cust!=null){
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("3"));
				sb.append("}");
			}else{
				BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
				if (member==null) {
					//往session中放入验证码时拼接了手机号码，所以判断的时候也要拼接手机号，
					//防止接收验证码的手机号和进数据库的验证码不一致
					if (phonecode.equals(telphone+verify_sms)) {
						try {
							bpCustMember = new BpCustMember();
							String wad_recommandPerson=(String) this.getSession().getAttribute("wad_recommandPerson");
							bpCustMember.setTelphone(telphone);
							bpCustMember.setIsCheckPhone("1");
							bpCustMember.setLoginname(wad_loginName.toLowerCase());
							bpCustMember.setPassword(md5.md5(wad_password, "utf-8"));

							if(wad_recommandPerson!=null&&!"".equals(wad_recommandPerson)){
								bpCustMember.setRecommandPerson(wad_recommandPerson);//直接推荐人邀请码
								if(null !=bpCustMemberService.getInviter(wad_recommandPerson)
										&& !"".equals(bpCustMemberService.getInviter(wad_recommandPerson))){
									bpCustMember.setSecondRecommandPerson(bpCustMemberService.getInviter(wad_recommandPerson).getPlainpassword());
								}
								;//间接推荐人邀请码
								//推荐人
								BpCustMember bprecommandPerson = bpCustMemberService.isRecommandPerson(wad_recommandPerson);
								//活动中心管理--------start   add by liusl
								bpActivityManageService.autoDistributeEngine("2", bprecommandPerson.getId().toString(),null);
								//活动中心管理 --------end
							}


							bpCustMember.setRegistrationDate(new Date());
							MD5_T md1 = new MD5_T();
							bpCustMember.setPlainpassword(md1.md5_3(wad_loginName));
							bpCustMember = bpCustMemberService.save(bpCustMember);
							//bpCustMember.setPlainpassword(bpCustMember.getId().toString());

							try {
								bpCustMember = bpCustMemberService.save(bpCustMember);
							} catch (Exception e) {
								sb.append("{\"success\":true,\"errMsg\":");
								sb.append(gson.toJson("3"));
								sb.append("}");
							}

							//活动中心管理--------start   add by liusl
							bpActivityManageService.autoDistributeEngine("1", bpCustMember.getId().toString(),null);
							//活动中心管理 --------end

							bpCustMember = bpCustMemberService.login(wad_loginName.toLowerCase(), md5.md5(wad_password, "utf-8"));
							//wenanInsert(bpCustMember.getId());
							initPlBidAuto(bpCustMember);

							sb.append("{\"success\":true,\"errMsg\":");
							sb.append(gson.toJson("1"));
							sb.append("}");

							//设置 返回提示消息
//							webMsgInstance("0", Constants.CODE_SUCCESS, "恭喜您手机绑定成功",  "", "", "", "", "");
//							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
//									DynamicConfig.MESSAGE).getTemplateFilePath());
//							return "freemarker";
						} catch (Exception ex) {
							sb.append("{\"success\":true,\"errMsg\":");
							sb.append(gson.toJson("2"));
							sb.append("}");

//							logger.error(ex.getMessage());
//							//设置 返回提示消息
//							webMsgInstance("0", Constants.CODE_FAILED, "手机绑定失败",  "", "", "", "", "");
//							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
//									DynamicConfig.MESSAGE).getTemplateFilePath());
//							return "freemarker";
						}
					} else {
						sb.append("{\"success\":true,\"errMsg\":");
						sb.append(gson.toJson("0"));
						sb.append("}");
						//设置 返回提示消息
//						webMsgInstance("0", Constants.CODE_FAILED, "手机验证码不正确",  "", "", "", "", "");
//						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
//								DynamicConfig.MESSAGE).getTemplateFilePath());
//						return "freemarker";
					}
				} else {
					//设置 返回提示消息
					webMsgInstance("0", Constants.CODE_FAILED, "该手机号已经被其他用户注册",  "", "", "", "", "");

					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
							DynamicConfig.MESSAGE).getTemplateFilePath());
					return "freemarker";
				}
			}
		}

		setJsonString(sb.toString());
		return SUCCESS;

	}*/
    public String successPhone() {
        webMsgInstance("0", Constants.CODE_SUCCESS, "恭喜您手机绑定成功", "", "", "", "", "");
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.REGSUCCESS).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 初始化pl_bid_auto表
     *
     * @param mem
     */
    private PlBidAuto initPlBidAuto(BpCustMember mem) {
        //自动投标添加一行记录
        PlBidAuto auto = new PlBidAuto();
        auto.setUserID(mem.getId());
        auto.setBidMoney(new java.math.BigDecimal(200));
        auto.setInterestStart(8);
        auto.setInterestEnd(24);
        auto.setPeriodStart(0);
        auto.setPeriodEnd(36);
        auto.setRateStart(null);
        auto.setRateEnd(null);
        auto.setKeepMoney(new java.math.BigDecimal(PlBidAuto.TOTALMONEY));
        auto.setIsOpen(0);
        auto.setOrderTime(null);
        auto.setBanned(0);
        auto = plBidAutoService.save(auto);
        return auto;
    }

    /**
     * 添加认证记录
     *
     * @param id 注册用户的id
     */
    private void wenanInsert(Long id) {

        WebFinanceApplyUploadsService webFinanceApplyUploadsService = (WebFinanceApplyUploadsService) AppUtil.getBean("webFinanceApplyUploadsService");
        //15中认证
        String[] str = new String[]{"IDCard", "CreditRecord", "Income", "WebShop", "House", "Vehicle", "Marriage", "Education", "Career", "JobTitle", "MobilePhone", "MicroBlog", "Residence", "CompanyPlace", "CompanyRevenue", "Teacher"};
        /**
         添加认证记录
         */
        for (int i = 0; i < str.length; i++) {
            WebFinanceApplyUploads webFinanceApplyUploads = new WebFinanceApplyUploads();
            webFinanceApplyUploads.setUserID(id);
            webFinanceApplyUploads.setFiles("");
            webFinanceApplyUploads.setStatus(0);
            webFinanceApplyUploads.setLastuploadtime(new Date());
            webFinanceApplyUploads.setMaterialstype(str[i]);
            webFinanceApplyUploadsService.save(webFinanceApplyUploads);
        }
    }

    /**
     * 修改手机号码之前的验证
     *
     * @return
     */
    public String verifPhone() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        String phonecode = (String) this.getSession().getAttribute(
                MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        //往修改页面跳转
        this.getRequest().setAttribute("toAction", "updatePhone");

        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        } else {
            bpCustMember = bpCustMemberService.get(mem.getId());

            if (phonecode != null && !"".equals(phonecode)) {
                    if (phonecode.equals(bpCustMember.getTelphone() + verify_sms)) {
                        this.getRequest().setAttribute("decide", "success");
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.CHANGELOGINPASSWORD).getTemplateFilePath());
                    } else {
                        this.getRequest().setAttribute("toAction", "updateTelphone");
                        this.getRequest().setAttribute("Sms", "error");
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.CHANGELOGINPASSWORD).getTemplateFilePath());
                    }
            } else {
                this.getRequest().setAttribute("toAction", "updateTelphone");
                this.getRequest().setAttribute("Sms", "error");
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.CHANGELOGINPASSWORD).getTemplateFilePath());

            }
        }
			return "freemarker";
    }

    /**
     * 修改手机号码之前的验证
     *手机端
     * @return
     */
    public String verifPhoneMo() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        String phonecode = (String) this.getSession().getAttribute(
                MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        //往修改页面跳转
        this.getRequest().setAttribute("toAction", "updatePhone");
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            sb.append("{\"success\":false,\"remark\":");
            sb.append(gson.toJson("请先登陆"));
        } else {
            bpCustMember = bpCustMemberService.get(mem.getId());
            if (phonecode != null && !"".equals(phonecode)) {
                    if (phonecode.equals(bpCustMember.getTelphone() + verify_sms)) {
                        sb.append("{\"success\":true,\"remark\":");
                        sb.append(gson.toJson("验证成功"));
                    } else {
                        sb.append("{\"success\":false,\"remark\":");
                        sb.append(gson.toJson("手机验证码输入错误"));
                    }
                }else {
                sb.append("{\"success\":false,\"remark\":");
                sb.append(gson.toJson("验证码已失效，请重新获取"));
            }
        }
        sb.append("}");
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 修改邮箱之前的手机号码之前的验证
     *
     * @return
     */
    public String verifEmail() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        //StringBuffer sb = new StringBuffer();
        String phonecode = (String) this.getSession().getAttribute(
                MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        //往修改页面跳转
        this.getRequest().setAttribute("toAction", "updateEmail");

        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        } else {
            bpCustMember = bpCustMemberService.get(mem.getId());

            BpCustMember bp = bpCustMemberService.isExistPhoneOther(telphone,
                    bpCustMember.getId());
            if (bp == null) {
                if (phonecode.equals(verify_sms)) {
                    //this.getRequest().setAttribute("decide", "success");
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                            DynamicConfig.CHANGELOGINPASSWORD).getTemplateFilePath());
                    return "freemarker";
                } else {
                    //this.getRequest().setAttribute("decide", "error");
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                            DynamicConfig.CHANGELOGINPASSWORD).getTemplateFilePath());
                    return "freemarker";
                }
            } else {

                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.LOGIN).getTemplateFilePath());
                return "freemarker";
            }
        }

        return SUCCESS;

    }

    /**
     * 绑定手机号成功
     */
    public String bindPhoneSuccess() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        } else {
            bpCustMember = bpCustMemberService.get(mem.getId());
            //设置 返回提示消息
            webMsgInstance("0", Constants.CODE_SUCCESS, "手机绑定成功", "", "", "", "", "");
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BINDPHONESSUCCE).getTemplateFilePath());
        }
        return "freemarker";

    }

    /*
	 * 判断第三方
	 * */
    public void getThirdPayConfig() {
        String payType = AppUtil.getSysConfig().get("thirdPayConfig").toString();
        if (payType != null && payType.toLowerCase().trim().equals("HuifuConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "汇付");
        } else if (payType != null && payType.toLowerCase().trim().equals("AllinPayConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "通联");
        } else if (payType != null && payType.toLowerCase().trim().equals("UMPayConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "联动优势");
        } else if (payType != null && payType.toLowerCase().trim().equals("FuiouConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "富友");
        } else if (payType != null && payType.toLowerCase().trim().equals("MoneyMorePayConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "双乾");
        } else if (payType != null && payType.toLowerCase().trim().equals("SinaPayConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "新浪");
        } else if (payType != null && payType.toLowerCase().trim().equals("YeePayConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "易宝");
        } else if (payType != null && payType.toLowerCase().trim().equals("FuDianConfig".toLowerCase().trim())) {
            this.getRequest().setAttribute("payType", "富滇银行");
        }
    }

    /**
     * 进入账户安全页面
     */
    @SuppressWarnings("unused")
    public String safe() {
        String isMobile = this.getRequest().getParameter("isMobile");
        this.getSession().setAttribute("highlight", 9);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String show = this.getRequest().getParameter("show");
        this.getRequest().setAttribute("show", show);
        this.getRequest().setAttribute("mypath", this.getBasePath());
        isSendMail = getSession().getAttribute("isSendMail");
        String tab = this.getRequest().getParameter("tab");
        if (tab != null && !"".equals(tab)) {
            this.getRequest().setAttribute("tab", "tab1");
        }
        if (mem != null) {
            commoon(mem);
            userloginlogs = userloginlogsService.getLoginlogs(mem.getLoginname());//登录日志
            cardList(bpCustMember);
            getEmail(bpCustMember);
            webFinanceApplylist = webFinanceApplyUploadService.getUploadState(bpCustMember.getId());//查询认证信息
            bpCustMember = bpCustMemberService.get(mem.getId());
            //头像信息
            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_remark_S_EQ", bpCustMember.getId());
            List<FileForm> fileList2 = fileFormService.getAll(filter1);
            if (fileList2.size() > 0) {
                String url = fileList2.get(0).getMark();
                this.getRequest().setAttribute("imgurl", url);
            }
            this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);
            QueryFilter filter = new QueryFilter(this.getRequest());
            filter.addFilter("Q_recommandPerson_S_EQ", mem.getPlainpassword());
            List<BpCustMember> invite = bpCustMemberService.getAll(filter);
            this.getSession().setAttribute("invite", invite);
            Map<String, String> map = new HashMap<String, String>();
            if (bpCustMember.getThirdPayFlagId() != null && !"".equals(bpCustMember.getThirdPayFlagId())) {//托管第三方查询出来第三方账户信息
                map = bpCustMemberService.queryThirdPayCustomerInfo(bpCustMember);

            }
/*
			if(isMobile!=null&&"1".equals(isMobile)){
				StringBuffer sb = new StringBuffer();
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				sb.append("{\"success\":true,\"data\":");
				sb.append(gson.toJson(invite));
				sb.append("}");
				setJsonString(sb.toString());
			    return SUCCESS;
			}

*/
            //判断是否是担保户
            QueryFilter filter11 = new QueryFilter();
            filter11.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter11);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                }
            }
            //判断是否录入过推荐码
            String code = (String) this.getSession().getAttribute("recommandPerson");
            if (code != null && !"".equals(code)) {
                this.getRequest().setAttribute("hide", "true");
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EDITUSERINFO).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 会员实名认证
     *
     * @throws ParseException
     */
    @SuppressWarnings("unused")
    public String updateAuthenticate() throws ParseException {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        bpCustMember = mem;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        BpCustMember check = bpCustMemberService.isExistCode(cardcode, mem.getId());
        if (check == null) {
            CardValidate cardVal = new CardValidate();
            String strIdCard = cardVal.IDCardValidate(this.cardcode);
            if (strIdCard.equals("true")) {
                if (mem != null) {
                    BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
                    try {
                        if (cardtype != null && cardtype >= 0) {
                            orgBpCustMember.setCardtype(cardtype);
                        }
                        if (truename != null) {
                            orgBpCustMember.setTruename(truename);
                        }
                        if (cardcode != null) {
                            orgBpCustMember.setCardcode(cardcode);
                        }
                        orgBpCustMember.setIsCheckCard("1");// 身份证已验证
                        orgBpCustMember.setIsCheckCardTime(new Date());// 验证时间
                        orgBpCustMember.setIsCheckCardMessage("8888");// 验证返回信息
                        orgBpCustMember.setThirdPayFlagId(cardcode);
                        orgBpCustMember.setThirdPayConfig(configMap.get("thirdPayConfig").toString());
                        bpCustMemberService.save(orgBpCustMember);
                        //判断是否 注册时生成了虚拟账户 否则就更新虚拟账户
                        ObSystemAccount obSystemAccount = obSystemAccountService.getByInvrstPersonId(bpCustMember.getId());
                        if (obSystemAccount == null) {
                            obSystemAccountService.saveAccount("1", bpCustMember.getTruename(), bpCustMember.getId().toString(), bpCustMember.getCardcode(), "0", "0");
                        } else {
                            obSystemAccount.setAccountName(bpCustMember.getTruename());
                            obSystemAccount.setInvestPersonName(bpCustMember.getTruename());
                            obSystemAccount.setAccountNumber(bpCustMember.getCardcode() + "-" + 0);
                            obSystemAccountService.save(obSystemAccount);
                        }
                        obSystemAccount = obSystemAccountService.getByInvrstPersonId(bpCustMember.getId());
                        if (obSystemAccount != null) {
                            //设置 返回提示消息
                            webMsgInstance("0", Constants.CODE_SUCCESS, "恭喜您实名认证成功！</span>", "", "", "", "", "");
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                        } else {
                            //设置 返回提示消息
                            webMsgInstance("0", Constants.CODE_FAILED, "实名认证成功,生成虚拟账号失败！", "", "", "", "", "");
                            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                    DynamicConfig.MESSAGE).getTemplateFilePath());
                        }
                    } catch (Exception ex) {

                        //设置 返回提示消息
                        webMsgInstance("0", Constants.CODE_FAILED, "实名认证失败！" + strIdCard, "", "", "", "", "");
                        logger.error(ex.getMessage());

                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.MESSAGE).getTemplateFilePath());
                    }
                } else {
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
                }

            } else {
                //设置 返回提示消息
                webMsgInstance("0", Constants.CODE_FAILED, "实名认证失败！" + strIdCard, "", "", "", "", "");
                sb.append("{\"success\":true,\"errMsg\":");
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.MESSAGE).getTemplateFilePath());
            }
        } else {
            //设置 返回提示消息
            webMsgInstance("0", Constants.CODE_FAILED, "该身份证已被认证！", "", "", "", "", "");

            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.MESSAGE).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 身份证第三方验证接口
     * ret 字符串数组用来表示是否身份证验证成功
     * ret[0]:表示验证成功与否(均为固定值)，成功：Constants.CODE_SUCCESS  ；失败：Constants.CODE_FAILED
     * ret[1]:用来表示验证成功和失败描述
     * add by linyan  2014-6-13
     *
     * @return
     */
    private String[] thirdAuthenticateInterface(String trueName, String CardNumber) {
        String thirdAuthenticateType = configMap.get("system_authentication_type").toString();
        String[] ret = new String[2];
        System.out.println("身份证：00000000000000000000000");
        try {
            if ("ID5Authentication".equals(thirdAuthenticateType)) {//彩云金融对接ID5接口方法
                String data = truename + "," + cardcode;
                String message = idCardService.idCardQuery("singleQuery",
                        "1A020201", data);
                System.out.println("身份证：" + message);
                if (message == null) {
                    ret[0] = Constants.CODE_FAILED;
                    ret[1] = "ID5身份验证没有返回验证数据!";
                } else if (message.indexOf("resultcode:\"3\"") == -1) {
                    ret[0] = Constants.CODE_FAILED;
                    ret[1] = message.substring(message.lastIndexOf(":") + 2,
                            message.length() - 1);
                } else {
                    ret[0] = Constants.CODE_SUCCESS;
                    ret[1] = message.substring(message.lastIndexOf(":") + 2,
                            message.length() - 1);
                }
            } else if ("thirdPayAuthentication".equals(thirdAuthenticateType)) {
                ret[0] = Constants.CODE_FAILED;
                ret[1] = "系统配置出错了，开通第三方支付就会完成实名认证";

            } else if ("systemAuthentication".equals(thirdAuthenticateType)) {//默认实名认证方法，如果需要对接其他身份认证接口，请自行在eles 前添加else if 方法
                String messageq = idCardService.idCardQuery(cardcode);// 进行身份证验证
                if (messageq.indexOf("\"resultcode\":\"200\"") != -1) {
                    ret[0] = Constants.CODE_SUCCESS;
                    ret[1] = messageq;
                } else {
                    ret[0] = Constants.CODE_FAILED;
                    ret[1] = messageq;
                }

            } else {
                ret[0] = Constants.CODE_FAILED;
                ret[1] = "没有该类型的实名认证接口";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    //同步用户信息到工业云
    private String updateCloud(BpCustMember bpCustMember, String oPType) {
        if (bpCustMember.getIsSync() == null || bpCustMember.getIsSync() == 0) {//未同步
            bpCustMember.setOPType("0");
        } else {//已同步
            bpCustMember.setOPType(oPType);
        }
        String type = ValidateCloud.ValidateUser(bpCustMember, Constants.PUB_ADD_USER);
        return type;
    }

    /**
     * 会员实名认证成功跳转页面
     */
    public String personCodeSuccess() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        if (mem != null) {
            BpCustMember orgBpCustMember = bpCustMemberService.get(mem.getId());
            bpCustMember = orgBpCustMember;
            try {

                //设置 返回提示消息
                webMsgInstance("0", Constants.CODE_SUCCESS, "实名认证成功", "", "", "", "", "");

				/*this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.PERSONCODESUCCESS).getTemplateFilePath());*/
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.MESSAGE).getTemplateFilePath());
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }

        setJsonString(sb.toString());
        return "freemarker";

    }

    /**
     * 会员邮箱认证 需要进行发送邮件
     */
    public String bindEmail() {
        // 将数据转成JSON格式
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            bpCustMember = bpCustMemberService.get(mem.getId());
            BpCustMember bpemail = bpCustMemberService.isExistEmailOther(email, bpCustMember.getId());// 其他用户是否已注册该邮箱
            if (bpemail == null) {
                String emailcode = CreateRandomRemate.createRandom(false, 8);// 生成验证码，需要放在session中
                emailcode = emailcode + "-" + System.currentTimeMillis();
                bpCustMember.setIsCheckEmail(emailcode);// 将验证码先存放在IsCheckEmail中
                //存放发送邮件的时间 用于过期验证
                this.getSession().setAttribute(MyUserSession.TIMEOUT_SESSION, System.currentTimeMillis());
                String uid = bpCustMember.getId().toString();
                String returnpath = getBasePath() + "user/updateEmailTypeBpCustMember.do?emailcode=" + emailcode;
                boolean isSuccess = sendMail(returnpath, null);// 发送
                try {
                    if (isSuccess) {
                        bpCustMember.setEmail(email);
                        bpCustMember = bpCustMemberService.save(bpCustMember);
                        webMsgInstance("0", Constants.CODE_SUCCESS, "邮件发送成功，请及时登录邮箱完成验证！", "", "", "", "", "");
                    } else {
                        //设置 返回提示消息
                        webMsgInstance("0", Constants.CODE_FAILED, "邮件发送失败！", "", "", "", "", "");

                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());

                    //设置 返回提示消息
                    webMsgInstance("0", Constants.CODE_FAILED, "邮件发送失败！", "", "", "", "", "");
                }
            } else {
                //设置 返回提示消息
                webMsgInstance("0", Constants.CODE_FAILED, "该邮箱已经被注册！", "", "", "", "", "");
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.MESSAGE).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 邮件发送成功跳转页面
     */
    public String sendEmailSuccess() {

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);

        if (mem != null) {
            //设置 返回提示消息
            webMsgInstance("0", Constants.CODE_SUCCESS, "邮件已发送，请登录邮箱进行激活！", "", "", "", "", "");


            bpCustMember = bpCustMemberService.get(mem.getId());

            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.EMAILSENDSUCCESS).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";

    }

    /**
     * 会员邮箱验证
     */
    public String updateEmailType() {
        try {
            //得到验证返回的唯一标识查询是否存在邮箱绑定用户
            if (emailcode != null && !emailcode.equals("")) {
                bpCustMember = bpCustMemberService.getEmailCode(emailcode);
                if (bpCustMember != null) {
                    String[] ret = emailcode.split("-");
                    if (ret.length > 1) {
                        this.getSession().getAttribute(MyUserSession.EMAIL_REG_RANDOM_SESSION);
                        Long nowtime = System.currentTimeMillis();
                        Long sessionTime = Long.valueOf(ret[1]);
                        //有效时间不能超过30分钟
                        Long funntime = (nowtime - sessionTime) / 1000 / 60;
                        if (funntime <= 30) {
                            try {
                                bpCustMember.setIsCheckEmail("1");// 将该字段设置为已进行邮箱验证
                                if (email != null) {
                                    bpCustMember.setEmail(email);// 邮箱进行修改ssss
                                }
                                bpCustMemberService.merge(bpCustMember);
                                this.getSession().removeAttribute(MyUserSession.EMAIL_REG_RANDOM_SESSION);// 将邮件验证码从session中注销
                                this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将用户信息保存在session中
                                //设置 返回提示消息
                                webMsgInstance("0", Constants.CODE_SUCCESS, "恭喜您邮箱验证成功！", "", "", "", "", "");
                            } catch (Exception ex) {
                                //设置 返回提示消息
                                webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败！", "", "", "", "", "");
                                ex.printStackTrace();
                                logger.error(ex.getMessage());
                            }
                        } else {
                            //设置 返回提示消息
                            webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败！", "已经超过有效时间", "", "", "", "");
                            this.getSession().removeAttribute(MyUserSession.EMAIL_REG_RANDOM_SESSION);
                        }
                    } else {
                        webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败！", "已经超过有效时间", "", "", "", "");
                    }
                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败，验证非法请求！", "", "", "", "", "");
                }
            } else {
                webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败，请求非法请求！", "", "", "", "", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            webMsgInstance("0", Constants.CODE_FAILED, "邮箱验证失败!", "", "", "", "", "");
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.MESSAGE).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 修改会员图像
     */
    public String updateImages() {

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        StringBuffer sb = new StringBuffer();
        HttpServletRequest request = getRequest();
        dataUrl = new ArrayList<String>();
        String imgpath = "attachFiles/uploads";
        try {
            request.setCharacterEncoding("UTF-8");

            // 文件的上传部分
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                if (mem != null) {
                    BpCustMember orgBpCustMember = bpCustMemberService.get(mem
                            .getId());
                    for (int i = 0; i < file.size(); ++i) {
                        InputStream is = new FileInputStream(file.get(i));
                        String path = ServletActionContext.getServletContext()
                                .getRealPath("/");
                        dataUrl.add(imgpath + this.getFileFileName().get(i));
                        File destFile = new File(path + imgpath, this
                                .getFileFileName().get(i));
                        File makeUrl = new File(path + imgpath);
                        if (!makeUrl.exists()) {
                            makeUrl.mkdirs();
                        }
						/*
						 * System.out.println("上传地址："+destFile.toString());
						 * System
						 * .out.println("图片名称："+this.getFileFileName().get(i));
						 */
                        OutputStream os = new FileOutputStream(destFile);
                        byte[] buffer = new byte[400];
                        int length = 0;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }

                        is.close();

                        os.close();
                        orgBpCustMember.setHeadImage(imgpath + "/"
                                + this.getFileFileName().get(i));
                    }

                    bpCustMemberService.merge(orgBpCustMember);
                    bpCustMember = orgBpCustMember;
                    sb.append("{\"success\":true,\"data\":");
                    sb.append(gson.toJson(bpCustMember));
                    sb.append(",\"result\":1");
                    sb.append("}");

                } else {
                    this.setSuccessResultValue(TemplateConfigUtil
                            .getDynamicConfig(DynamicConfig.LOGIN)
                            .getTemplateFilePath());
                }
            } else {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append("图片上传失败");
                sb.append("}");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setJsonString(sb.toString());

        return SUCCESS;

    }


    public String test1() {
        String localAddr = ServletActionContext.getRequest().getLocalAddr();
        Integer port = ServletActionContext.getRequest().getLocalPort();

        String a = ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getLocalAddr() + ":" + ServletActionContext.getRequest().getLocalPort() + ServletActionContext.getRequest().getContextPath() + "/";
        System.out.println("路径：" + a);
        return SUCCESS;
    }

    /**
     * 修改手机号
     * PC
     */
    public String updatePhone() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem==null){
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        String verify_sms = this.getRequest().getParameter("verify_sms");
        String isMobile = this.getRequest().getParameter("mobile");
        String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        if (null == phonecode || "".equals(phonecode)) {
            //设置 返回提示消息
            webMsgInstance("0", Constants.CODE_FAILED, "验证码已经超时！", "", "", "", "", "");
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
            return "freemarker";
        } else {
            BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
            if (member == null) {
                mem = bpCustMemberService.get(mem.getId());
                if(StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard())){
                //往session中放入验证码时拼接了手机号码，所以判断的时候也要拼接手机号，
                //防止接收验证码的手机号和进数据库的验证码不一致
                if (phonecode.equals(telphone + verify_sms)) {
                    CommonRequst cq = new CommonRequst();
                    if (isMobile != null && !"".equals(isMobile) && ("1".equals(isMobile))) {
                        cq.setIsMobile("1");//手机端操作
                    }
                    String orderNum = ContextUtil.createRuestNumber();//生成第三需要的流水号
                     if (mem.getCustomerType()!= null && mem.getCustomerType()==0) {//个人修改手机号码
                         cq.setBussinessType(ThirdPayConstants.BT_UPDATEPHONE);
                         cq.setTransferName(ThirdPayConstants.TN_UPDATEPHONE);
                     }else {//企业修改手机号码
                         cq.setBussinessType(ThirdPayConstants.BT_MODIFY);
                         cq.setTransferName(ThirdPayConstants.TN_MODIFY);
                         cq.setLegal(mem.getLegalPerson());//法人姓名
                         cq.setLegalIdNo(mem.getLegalNo());//法人身份证号码
                         cq.setTelephone(telphone);//联系人电话
                         cq.setTrueName(mem.getTruename());
                         cq.setGuarType("3");
                     }

                    cq.setRequsetNo(orderNum);//流水号
                    cq.setThirdPayConfigId(mem.getThirdPayFlagId());
                    cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
                    cq.setTelephone(telphone);//手机号
                    CommonResponse cr = ThirdPayInterfaceUtil.thirdCommon(cq);

                    if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
//						BpCustMember cust = bpCustMemberService.get(mem.getId());
//						cust.setTelphone(telphone);
//						bpCustMemberService.save(cust);
                        webMsgInstance("0", Constants.CODE_SUCCESS, "修改手机号成功", "", "", "", "", "");
                    } else {
                        webMsgInstance("0", cr.getResponsecode(), cr.getResponseMsg(), "", "", "", "", "");
                    }
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                    return "freemarker";
                } else {
                    //设置 返回提示消息

                    webMsgInstance("0", Constants.CODE_FAILED, "验证码错误！", "", "", "", "", "");

                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                            DynamicConfig.MESSAGE).getTemplateFilePath());
                    return "freemarker";
                }
            }else{
                    if (phonecode.equals(telphone + verify_sms)) {

                        mem.setTelphone(telphone);//手机号
                        bpCustMemberService.merge(mem);
                        this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION,mem);
                        webMsgInstance("0", Constants.CODE_SUCCESS, "修改手机号成功", "", "", "", "", "");
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                        return "freemarker";
                    } else {
                        //设置 返回提示消息

                        webMsgInstance("0", Constants.CODE_FAILED, "验证码错误！", "", "", "", "", "");

                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                                DynamicConfig.MESSAGE).getTemplateFilePath());
                        return "freemarker";
                    }
                }
        }else {
                //设置 返回提示消息
                webMsgInstance("0", Constants.CODE_FAILED, "该手机号已经被其他用户注册", "", "", "", "", "");

                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.MESSAGE).getTemplateFilePath());
                return "freemarker";
            }
        }
    }
    
    
    
    /**
     * 修改手机号
     * 手机端
     */
    public String updatePhoneMO() {
        String backpath = this.getRequest().getParameter("backpath");
    	 String verify_sms = this.getRequest().getParameter("verify_sms");
         String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
    	 BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
         Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
         // 将数据转成JSON格式
         StringBuffer sb = new StringBuffer("{\"success\":");
         if (mem==null){
        	 	sb.append("false,\"remark\":");
	         	sb.append(gson.toJson("登陆信息失效，请先登陆"));
         }else {
        	 if (StringUtils.isNotEmpty(phonecode)) {//验证码是否存在
        		 BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
                 if (member == null) {
                     mem = bpCustMemberService.get(mem.getId());
                     if(StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard())){
                     //往session中放入验证码时拼接了手机号码，所以判断的时候也要拼接手机号，
                     //防止接收验证码的手机号和进数据库的验证码不一致
                     if (phonecode.equals(telphone + verify_sms)) {
                         this.getSession().setAttribute("backpath",backpath);
                         CommonRequst cq = new CommonRequst();
                         cq.setIsMobile("1");//手机端操作
                         String orderNum = ContextUtil.createRuestNumber();//生成第三需要的流水号
                         cq.setBussinessType(ThirdPayConstants.BT_UPDATEPHONE);
                         cq.setTransferName(ThirdPayConstants.TN_UPDATEPHONE);
                         cq.setRequsetNo(orderNum);//流水号
                         cq.setThirdPayConfigId(mem.getThirdPayFlagId());
                         cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
                         cq.setTelephone(telphone);//手机号
                         CommonResponse cr = ThirdPayInterfaceUtil.thirdCommon(cq);

                         if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
             	         	 webMsgInstance("0", Constants.CODE_SUCCESS,"修改手机号申请成功", "", "", "", "", "");
             	         	 this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
             	         	 return "freemarker";
                         } else {
                             webMsgInstance("0", Constants.CODE_SUCCESS,cr.getResponseMsg(), "", "", "", "", "");
                             this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                             return "freemarker";
                         }
                     } else {
                         //设置 返回提示消息
                         webMsgInstance("0", Constants.CODE_FAILED, "验证码错误！", "", "", "", "", "");
                         this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                         return "freemarker";
                     }
                 }else{
                         if (phonecode.equals(telphone + verify_sms)) {
                             mem.setTelphone(telphone);//手机号
                             bpCustMemberService.merge(mem);
                             this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION,mem);
                             sb.append("true,\"remark\":");
             	         	 sb.append(gson.toJson("修改手机号成功"));
                         } else {
                             //设置 返回提示消息
                        	 sb.append("false,\"remark\":");
             	         	 sb.append(gson.toJson("验证码错误！"));
                         }
                     }
             }else {
                     if (StringUtils.isNotEmpty(backpath)){
                         webMsgInstance("0", Constants.CODE_FAILED, "手机号码已存在！", "", "", "", "", "");
                         this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                         return "freemarker";
                     }else {
                         sb.append("false,\"remark\":");
                         sb.append(gson.toJson("手机号码已存在！"));
                     }
                 }
             }else {
            	//设置 返回提示消息
                 if (StringUtils.isNotEmpty(backpath)){
                     webMsgInstance("0", Constants.CODE_FAILED, "验证码已经超时！", "", "", "", "", "");
                     this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                     return "freemarker";
                 }else {
                     sb.append("false,\"remark\":");
                     sb.append(gson.toJson("验证码已经超时！"));
                 }
             }
         }
         sb.append("}");
    	setJsonString(sb.toString());
    	return SUCCESS;
    }



    
    

    private List<File> file;

    private List<String> fileFileName;

    private List<String> fileContentType;

    private List<String> dataUrl;

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    public List<String> getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(List<String> dataUrl) {
        this.dataUrl = dataUrl;
    }

    /**
     * 邮件发送方法 msgURL 激活 或者 找回密码的 的URL链接 title 如 注册 修改密码
     *
     * @return boolean true 成功 false 失败
     */
    public boolean sendMail(String msgURL, String code) {
        boolean ret = false;
        // 邮件实体
        MailModel mode = new MailModel();
        // 邮件模版需要的数据
        Map<String, Object> mailData = new HashMap<String, Object>();
        String title = "";
        if (code != null) {
            mode.setMailTemplate("mail/findPass.vm");
            title = "找回密码";
        } else {
            mode.setMailTemplate("mail/sendMsg.vm");
            title = "邮箱验证";
        }
        MailData data = new MailData(title, email);
        data.setCode(code);
        data.setSite(msgURL);
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
            System.out.println("sendMailMessage=" + sendMailMessage);
            if (sendMailMessage == null || "".equals(sendMailMessage)) {
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public String checkEmailAndPhone() {
        StringBuffer sb = new StringBuffer();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (val != null || !"".equals(val)) {
            val = val.toLowerCase();
        }
        String checkCode = this.getRequest().getParameter("checkCode");
        //System.out.println("--------------val="+val);
        //System.out.println("--------------checkCode="+checkCode);
        if (checkCode.equals(val)) {
            BpCustMember bcm = null;
            String email = this.getRequest().getParameter("email");
            String phone = this.getRequest().getParameter("phone");
            if (email != null && !"".equals(email)) {
                bcm = bpCustMemberService.isExistEmail(email);
                if (bcm != null) {
                    sb.append("{\"success\":true,\"regType\":");
                    sb.append(gson.toJson("email"));
                    sb.append(",\"id\":");
                    sb.append(gson.toJson(bcm.getId().toString()));
                    sb.append(",\"email\":");
                    sb.append(gson.toJson(bcm.getEmail()));
                    sb.append("}");
                } else {
                    sb.append("{\"success\":false}");
                }
            } else {
                bcm = bpCustMemberService.isExistTelphone(phone);
                if (bcm != null) {
                    sb.append("{\"success\":true,\"regType\":");
                    sb.append(gson.toJson("telphone"));
                    sb.append(",\"id\":");
                    sb.append(gson.toJson(bcm.getId().toString()));
                    sb.append(",\"telphone\":");
                    sb.append(gson.toJson(bcm.getTelphone()));
                    sb.append("}");
                } else {
                    sb.append("{\"success\":false}");
                }
            }

        } else {
            sb.append("{\"success\":false}");
        }

        setJsonString(sb.toString());
        return SUCCESS;
    }

    public String checkUer() {
        ///验证用户是否存在及发送邮件短信
		/*
		 * 1、检查用户是否存在
		 * 2、如果存在则发送验证短信或邮件，否则返回错误信息
		 * 3、
		 * **/
        StringBuffer sb = new StringBuffer();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String regType = getRequest().getParameter("regType");//验证类型
        String random = createRandom(true, 6);//生成的验证码
        System.out.println("random:" + random);
        this.getSession().setAttribute(MyUserSession.RANDOM_SESSION, random);
        if (null == regType || "".equals(regType)) {
            sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
            sb.append(gson.toJson("页面超时!请刷新....."));
            sb.append("}");
        } else {
            //验证验证码
            String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
            val = val.toLowerCase();
            StringUtils.swapCase(val);
            checkCode = checkCode.toLowerCase();
            StringUtils.swapCase(StringUtils.trim(checkCode));
            if (val != null && val.equals(checkCode)) {
                if ("email".equals(regType)) {//邮箱找回密码
                    //验证邮箱是否被注册
                    String email = getRequest().getParameter("email");
                    if (null == email || "".equals(email)) {
                        sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                        sb.append(gson.toJson("请输入邮箱!"));
                        sb.append("}");
                    } else {
                        BpCustMember bpemail = bpCustMemberService.isExistEmail(email);
                        if (null == bpemail) {
                            sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                            sb.append(gson.toJson("该用户不存在!"));
                            sb.append("}");
                        } else {
							/*
							 * 发送邮件
							 * **/
                            this.getSession().setAttribute("typeValue", email);
                            this.getSession().setAttribute(MyUserSession.TEMP_MEMBER, bpemail);
                            this.getSession().setAttribute(MyUserSession.TIMEOUT_SESSION, System.currentTimeMillis());
                            boolean isSuccess = sendMail("", random);// 发送
                            if (true) {
                                sb.append("{\"success\":true,\"data\":");
                                sb.append(gson.toJson(regType));
                                sb.append(",\"result\":1");
                                sb.append("}");
                            } else {
                                sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                                sb.append(gson.toJson("邮件发送失败,请联系系统管理员!"));
                                sb.append("}");
                            }

                        }
                    }
                } else if ("telphone".equals(regType)) {//短信找回密码
                    //验证手机是否备注册
                    String telphone = getRequest().getParameter("telphone");
                    if (null == telphone || "".equals(telphone)) {
                        sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                        sb.append(gson.toJson("请输入手机号码!"));
                        sb.append("}");
                    } else {
                        BpCustMember bpphone = bpCustMemberService.isExistPhone(telphone);
                        if (null == bpphone) {
                            sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                            sb.append(gson.toJson("该用户不存在!"));
                            sb.append("}");
                        } else {
                            /**
                             * 发送短信
                             * ***/

                            //开通第三方成功发送短信、邮件、站内信。
                            Map<String, String> mapMessage = new HashMap<String, String>();
                            mapMessage.put("key", "sms_BackPassword");
                            mapMessage.put("telphone", telphone);
                            mapMessage.put("${code}", random);
                            String result = sendMesService.sendSmsEmailMessage(mapMessage);


                            this.getSession().setAttribute("typeValue", telphone);
                            this.getSession().setAttribute(MyUserSession.TEMP_MEMBER, bpphone);
							/*SmsSendUtil  sss = new SmsSendUtil();
							sss.sms_Sign(telphone, random);*/
                            sb.append("{\"success\":true,\"data\":");
                            sb.append(gson.toJson(regType));
                            sb.append(",\"result\":1");
                            sb.append("}");

                        }
                    }

                } else {
                    sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                    sb.append(gson.toJson("网络异常!"));
                    sb.append("}");
                }
            } else {
                sb.append("{\"success\":true,\"errMsg\":");
                sb.append(gson.toJson("验证码错误!"));
                sb.append("}");
            }

        }
        setJsonString(sb.toString());
        return SUCCESS;

    }




    /**
     * 提前退出获得验证码
     *
     * @return
     */
    public String getTelphoneCode() {
        StringBuffer sb = new StringBuffer();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String random = createRandom(true, 6);//生成的验证码
        //验证手机是否备注册
        String telphone = getRequest().getParameter("telphone");
        if (null == telphone || "".equals(telphone)) {
            sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
            sb.append(gson.toJson("请输入手机号码!"));
            sb.append("}");
        } else {
            BpCustMember bpphone = bpCustMemberService.isExistPhone(telphone);
            if (null == bpphone) {
                sb.append("{\"success\":true,\"result\":0,\"errMsg\":");
                sb.append(gson.toJson("该用户不存在!"));
                sb.append("}");
            } else {
                /**
                 * 发送短信
                 * ***/
                this.getSession().setAttribute("typeValue", telphone);
                this.getSession().setAttribute(MyUserSession.TEMP_MEMBER, bpphone);
                this.getSession().setAttribute("codeValue", random);

                Map<String, String> mapMessage1 = new HashMap<String, String>();
                mapMessage1.put("key", "sms_operationFunction");
                mapMessage1.put("userId", bpphone.getId().toString());
                mapMessage1.put("${code}", random);
                mapMessage1.put("${operationFunction}", "理财计划提前退出");
                String result1 = sendMesService.sendSmsEmailMessage(mapMessage1);

                sb.append("{\"success\":true,\"data\":");
                sb.append(gson.toJson(regType));
                sb.append(",\"result\":1");
                sb.append("}");
            }
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 验证身份证号码是否合法
     *
     * @return
     */
    public String checkCard() {
        String cardcode = this.getRequest().getParameter("cardcode");
        StringBuffer sb = new StringBuffer();
        CardValidate cardVal = new CardValidate();
        try {
            String strIdCard = cardVal.IDCardValidate(cardcode);
            if (strIdCard.equals("true")) {
                sb.append("{\"success\":true");
                sb.append(",\"result\":1");
                sb.append("}");
            } else {
                sb.append("{\"success\":false");
                sb.append(",\"result\":0");
                sb.append("}");
            }
        } catch (Exception e) {
            sb.append("{\"success\":false");
            sb.append(",\"result\":0");
            sb.append("}");
            e.printStackTrace();
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length     长度
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
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

    public String[] strategySms(String phone, String code, String content) {
		/*
		 * 1、获取模板
		 * 2、获取系统类别---决策短信接口
		 * 3、调用短信接口
		 *
		 *
		 * */
        String[] strRelt = new String[3];
        String projStr = AppUtil.getProjStr();
        if ("proj_duorongyi".equals(projStr)) {
            strRelt = smsService.sendSMS(content, telphone, "1");
        } else if (AppUtil.getProjStr().equals("proj_yirandai") || AppUtil.getProjStr().equals("proj_caiyunjinrong")) {
            String str = sxtMessageStrategy.sendMsg(this.telphone, code, content);
            if (str != null && str.equals("Sucess")) {
                strRelt[0] = Constants.CODE_SUCCESS;
                strRelt[1] = "发送成功";
            } else {
                strRelt[0] = Constants.CODE_FAILED;
                strRelt[1] = "发送失败";
            }
        } else if ("proj_diandianjubao".equals(projStr)) {
            String str = yzyxMessageStrategyImpl.sendMsg(telphone, telphone, content);
            if (str != null && str.equals("Sucess")) {
                strRelt[0] = Constants.CODE_SUCCESS;
                strRelt[1] = "发送成功";
            } else {
                strRelt[0] = Constants.CODE_FAILED;
                strRelt[1] = "发送失败";
            }
        } else {
            String str = "";
            try {
                str = HXSmsManagerServerImp.sendMsm(telphone, content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (str != null && str.split("&")[0].split("=")[1].equals("0")) {
                strRelt[0] = Constants.CODE_SUCCESS;
                strRelt[1] = "发送成功";
            } else {
                strRelt[0] = Constants.CODE_FAILED;
                strRelt[1] = "发送失败";
            }
        }

        return strRelt;
    }

    public String jump() {
        typeValue = (String) this.getSession().getAttribute("typeValue");

        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.JUMPPASSWORD).getTemplateFilePath());
        return "freemarker";
    }

    public String checkCode() {
//		String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        String checkCode = getRequest().getParameter("checkCode");
        String code = (String) this.getSession().getAttribute(MyUserSession.RANDOM_SESSION);
        StringBuffer sb = new StringBuffer();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Long nowtime = System.currentTimeMillis();
//		Long sessionTime=Long.valueOf(String.valueOf(this.getSession().getAttribute(MyUserSession.TIMEOUT_SESSION)));
//         有效时间不能超过30分钟
//		Long fulltime=(nowtime-sessionTime)/1000/60;
        if (null == checkCode || "".equals(checkCode)) {
            sb.append("{\"success\":false,\"result\":0,\"errMsg\":");
            sb.append(gson.toJson("验证码错误!"));
            sb.append("}");

        }/*else if(fulltime>=30){
				sb.append("{\"success\":false,\"result\":0,\"errMsg\":");
				sb.append(gson.toJson("验证码过期!"));
				sb.append("}");
				this.getSession().removeAttribute(MyUserSession.TIMEOUT_SESSION);

		}*/ else if (null == code || "".equals(code)) {
            sb.append("{\"success\":false,\"result\":0,\"errMsg\":");
            sb.append(gson.toJson("验证码已失效!"));
            sb.append("}");

        } else if (code != null && !code.equals(checkCode)) {
            sb.append("{\"success\":false,\"result\":0,\"errMsg\":");
            sb.append(gson.toJson("验证码错误!"));
            sb.append("}");
        } else {

            String data = "";
            if (email != null) {
                BpCustMember user = bpCustMemberService.getMemberByEmail(email);
                data = user.getId().toString();
            } else {
                BpCustMember user = bpCustMemberService.getMemberByPhone(telphone);
                data = user.getId().toString();
            }


            sb.append("{\"success\":true,\"result\":1,\"data\":");
            sb.append(gson.toJson(data));
            sb.append("}");

            //去除6位验证码的session
            this.getSession().removeAttribute(MyUserSession.RANDOM_SESSION);

            //添加uuid  session  和 一个随机数
            //uuid值为和要修改的userId
            //将uuid   key值也放入session    此处key值为自定义 MyUserSession.FINDPWD_RANDOM_SESSION
            String uuid = UUIDUtils.getUUID();
            this.getSession().setAttribute(uuid, data);  //随机数和id进行一一对应
            this.getSession().setAttribute(MyUserSession.FINDPWD_RANDOM_SESSION, uuid);

        }
        setJsonString(sb.toString());
        return SUCCESS;
    }



    /**
     * 加载用户资产页面
     * @return
     *//*
	public String showAssets(){
		bpCustMember= (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

		//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				//DynamicConfig.ASSET).getTemplateFilePath());
		return "freemarker";
	}*/

    /**
     * 获取银行卡列表
     *
     * @param bpCustMember
     */
    private void cardList(BpCustMember bpCustMember) {
        QueryFilter filter0 = new QueryFilter(this.getRequest());
        if (configMap.get("thirdPayConfig").toString().equals(Constants.HUIFU)) {
            filter0.addFilter("Q_thirdConfig_S_EQ", configMap.get("thirdPayConfig").toString());
            filter0.addFilter("Q_userFlg_S_EQ", (bpCustMember.getThirdPayFlagId() == null || bpCustMember.getThirdPayFlagId().equals("")) ? "000" : bpCustMember.getThirdPayFlagId());//如果还没有开通第三方先默认一个不存在的值
        } else {
            filter0.addFilter("Q_thirdConfig_S_EQ", configMap.get("thirdPayConfig").toString());
            //filter0.addFilter("Q_userFlg_S_EQ", (bpCustMember.getThirdPayFlagId()==null||bpCustMember.getThirdPayFlagId().equals(""))?"000":bpCustMember.getThirdPayFlagId());//如果还没有开通第三方先默认一个不存在的值
            filter0.addFilter("Q_userFlg_S_EQ", bpCustMember.getThirdPayFlagId());
            filter0.addFilter("Q_customerId_L_EQ", bpCustMember.getId().toString());
        }
        listBankCard = webBankcardService.getAll(filter0);
    }

    private void getList(BpCustMember mem, Long loadid) {
        try {
            BpCustMember cust = bpCustMemberService.get(mem.getId());
            List<GlobalType> list = globalTypeService.getByNodeKey("dgree");
            if (null != list && list.size() > 0) {
                List<Dictionary> listDgree = dictionaryService.getByProTypeId(list.get(0).getProTypeId());
                this.getRequest().setAttribute("listDgree", listDgree);//最高学历
            }

            List<GlobalType> list2 = globalTypeService.getByNodeKey("gxrgx");
            if (null != list2 && list2.size() > 0) {
                List<Dictionary> listGxrgx = dictionaryService.getByProTypeId(list2.get(0).getProTypeId());
                this.getRequest().setAttribute("listGxrgx", listGxrgx);//亲属关系
            }

            List<GlobalType> list3 = globalTypeService.getByNodeKey("unitproperties");
            if (null != list3 && list3.size() > 0) {
                List<Dictionary> listUnitp;
                if (loadid == 16) {
                    listUnitp = dictionaryService.getByProTypeId2(list3.get(0).getProTypeId(), "1000");
                } else {
                    listUnitp = dictionaryService.getByProTypeId(list3.get(0).getProTypeId());
                }

                this.getRequest().setAttribute("listUnitp", listUnitp);//公司类别
            }

            List<GlobalType> list4 = globalTypeService.getByNodeKey("zhiwujob");
            if (null != list4 && list4.size() > 0) {
                List<Dictionary> listJob;
                if (loadid == 16) {
                    listJob = dictionaryService.getByProTypeId2(list4.get(0).getProTypeId(), "1000");
                } else {
                    listJob = dictionaryService.getByProTypeId(list4.get(0).getProTypeId());
                }
                this.getRequest().setAttribute("listJob", listJob);//公司职位
            }

            List<GlobalType> list5 = globalTypeService.getByNodeKey("8");
            if (null != list5 && list5.size() > 0) {
                List<Dictionary> listMarry = dictionaryService.getByProTypeId(list5.get(0).getProTypeId());
                this.getRequest().setAttribute("listMarry", listMarry);//婚姻状况
            }

            List<CsDicAreaDynam> listArea6591 = csDicAreaDynamService.listByParentId(6591);
            this.getRequest().setAttribute("listArea6591", listArea6591);//工作城市

            CsDicAreaDynam csDicArea = csDicAreaDynamService.get(cust.getLiveCity() == null ? 0 : cust.getLiveCity());
            CsDicAreaDynam csDic = null;
            if (csDicArea != null) {
                csDic = new CsDicAreaDynam();
                csDic = csDicAreaDynamService.get(new Long(csDicArea.getParentId()));//得到父级的信息
                csDicArea.setParentTitle(csDic.getTitle());//父级名称
                csDicArea.setParentTitleId(csDic.getId().toString());
            }
            this.getRequest().setAttribute("csDicArea", csDicArea);

            List<CsDicAreaDynam> listArea10092 = csDicAreaDynamService.listByParentId(10092);
            this.getRequest().setAttribute("listArea10092", listArea10092);//公司行业
            QueryFilter filter = new QueryFilter(this.getRequest());

            filter.addFilter("Q_proTypeId_L_EQ", 1228 + "");//公司规模
            if (loadid == 16) {
                filter.addFilter("Q_status_S_EQ", 1 + "000");
            } else {
                filter.addFilter("Q_status_S_EQ", 0 + "");
            }
            listCompanysize = dictionaryService.getAll(filter);

            /**********************************以下是查询下拉列表中的数据**************************************/
            CsDicAreaDynam csDicAreaNativePlaceProvice = csDicAreaDynamService.get(cust.getNativePlaceProvice() == null ? -10 : Long.valueOf(cust.getNativePlaceProvice()));//籍贯省查询
            CsDicAreaDynam csDicAreaNativePlaceCity = csDicAreaDynamService.get(cust.getNativePlaceCity() == null ? -10 : Long.valueOf(cust.getNativePlaceCity()));//籍贯市查询
            CsDicAreaDynam csDicAreaLiveProvice = csDicAreaDynamService.get(cust.getLiveProvice() == null ? -10 : Long.valueOf(cust.getLiveProvice()));//户口所在地省查询
            CsDicAreaDynam csDicAreaLiveCity = csDicAreaDynamService.get(cust.getLiveCity() == null ? -10 : Long.valueOf(cust.getLiveCity()));//户口所在地市查询
            CsDicAreaDynam csDicAreaHireProvince = csDicAreaDynamService.get(cust.getHireProvince() == null ? -10 : Long.valueOf(cust.getHireProvince()));//公司城市省查询
            CsDicAreaDynam csDicAreaHireCity = csDicAreaDynamService.get(cust.getHireCity() == null ? -10 : Long.valueOf(cust.getHireCity()));//公司城市市查询
            // CsDicAreaDynam csDicAreaBossCity = csDicAreaDynamService.get(cust.getBossCity()==null?-10:Long.valueOf(cust.getBossCity()));//工作城市市查询

            CsDicAreaDynam csDicAreaTeacherCity = csDicAreaDynamService.get(cust.getTeacherCity() == null ? -10 : Long.valueOf(cust.getTeacherCity()));//工作城市市查询
            this.getRequest().setAttribute("csDicAreaNativePlaceProvice", csDicAreaNativePlaceProvice);
            this.getRequest().setAttribute("csDicAreaNativePlaceCity", csDicAreaNativePlaceCity);
            this.getRequest().setAttribute("csDicAreaLiveProvice", csDicAreaLiveProvice);
            this.getRequest().setAttribute("csDicAreaLiveCity", csDicAreaLiveCity);
            this.getRequest().setAttribute("csDicAreaHireProvince", csDicAreaHireProvince);
            this.getRequest().setAttribute("csDicAreaHireCity", csDicAreaHireCity);
            this.getRequest().setAttribute("csDicAreaTeacherCity", csDicAreaTeacherCity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示不同的节点页面
     *
     * @return
     */
    public String getNodeMem() {

        try {
            //得到session对象
            BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            Long loadid = 0l;
            try {
                //得到bp_finance_apply_user中的主键
                loadid = Long.parseLong(this.getRequest().getParameter("loadid"));
            } catch (Exception e) {
                this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
                webMsgInstance("0", Constants.CODE_FAILED, "操作非法，重新填写数据！", "", "", "", "", "");
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.MESSAGE).getTemplateFilePath());
                return "message";
            }
            ///点击左侧菜单时，得到相应的菜单值
            String menuNode = this.getRequest().getParameter("menuNode");

            //得到产品对象信息
            financeApplyUser = bpFinanceApplyUserService.get(loadid);
            getList(cust, financeApplyUser.getProductId());
            //表单提交时!
            if (menuNode == null) {
                String nowNode = this.getRequest().getParameter("nowNode");
                if (bpCustMember.getHomePhonePrefix() != null && bpCustMember.getHomePhoneSuffix() != null) {
                    bpCustMember.setHomePhone(bpCustMember.getHomePhonePrefix() + "-" + bpCustMember.getHomePhoneSuffix());
                }
                if (bpCustMember.getWebshopPhonePrefix() != null && bpCustMember.getWebshopPhoneSuffix() != null) {
                    bpCustMember.setWebshopPhone(bpCustMember.getWebshopPhonePrefix() + "-" + bpCustMember.getWebshopPhoneSuffix());
                }
                if (bpCustMember.getHireCompanyphonePrefix() != null && bpCustMember.getHireCompanyphoneSuffix() != null) {
					/*	if(financeApplyUser.getProductId()==15){
							bpCustMember.setTeacherCompanyphone(bpCustMember.getHireCompanyphonePrefix()+"-"+bpCustMember.getHireCompanyphoneSuffix());
						}else if(financeApplyUser.getProductId()==13){
							bpCustMember.setBossCompanyphone(bpCustMember.getHireCompanyphonePrefix()+"-"+bpCustMember.getHireCompanyphoneSuffix());
						}else{*/
                    bpCustMember.setHireCompanyphone(bpCustMember.getHireCompanyphonePrefix() + "-" + bpCustMember.getHireCompanyphoneSuffix());
                    //	}
                }
                if (null != bpCustMember.getTeacherPosition() && !"".equals(bpCustMember.getTeacherPosition())) {
                    // if(financeApplyUser.getProductId()==16){
                    bpCustMember.setTeacherPosition(bpCustMember.getTeacherPosition());
                }
                //得到用户对象
                BpCustMember member = bpCustMemberService.get(financeApplyUser.getUserID());
                //更新用户信息操作
                try {
                    BeanUtil.copyNotNullProperties(member, bpCustMember);
                    bpCustMemberService.save(member);
                } catch (Exception ex) {
                    logger.error("<<" + bpCustMember.getId() + ">>" + ex.getMessage());
                    System.out.println(ex);
                    this.getRequest().getSession().setAttribute("retUrl", "/loan/getNodeP2pLoanProduct.do?productId=" + financeApplyUser.getProductId());
                    webMsgInstance("0", Constants.CODE_FAILED, "您的操作出现错误，请重新填写！", "", "", "", "", "");
                    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                    return "message";
                }
                String newCurrenNodes = "";//得到下一步的节点
                //拆分该流程节点
                String[] str = financeApplyUser.getFlownodes().split("\\|");
                //进行对比，判断，得到下一流程节点
                for (int i = 0; i < str.length; i++) {
                    String oldNode = str[i].toString();
                    if (oldNode.equals(financeApplyUser.getCurrentnode()) && i < str.length - 1) {
                        String[] finishState = financeApplyUser.getFinishStatus().split("\\|");
                        newCurrenNodes = str[i + 1];
                        String fs = "";
                        for (int a = 0; a < finishState.length; a++) {
                            if (i == a) {
                                finishState[a] = 1 + "";
                            }
                            fs = fs + finishState[a] + "|";
                        }
                        financeApplyUser.setFinishStatus(fs);
                        bpFinanceApplyUserService.save(financeApplyUser);
                        break;
                    } else if (i == str.length - 1) {
                        newCurrenNodes = str[str.length - 1];
                    }
                }

                financeApplyUser.setCurrentnode(newCurrenNodes);
                bpFinanceApplyUserService.save(financeApplyUser);
            } else {//点击左边连接时

                if (menuNode.equals("nodePerson")) {//个人信息

                } else if (menuNode.equals("nodeFamily")) {//家庭信息

                } else if (menuNode.equals("nodeWebshop")) {//网店信息

                } else if (menuNode.equals("nodeCareer")) {//工作信息

                } else if (menuNode.equals("nodeCompany")) {//企业信息

                } else if (menuNode.equals("nodeAssets")) {//资产信息

                } else if (menuNode.equals("nodeCredit")) {//信用信息

                } else if (menuNode.equals("nodeUpload")) {//上传资料

                }
                //更新产品点节信息
                financeApplyUser.setCurrentnode(menuNode);
                //更新节点信息
                bpFinanceApplyUserService.save(financeApplyUser);
            }
            webFinanceApplylist = webFinanceApplyUploadService.getUploadState(financeApplyUser.getUserID());
            //得到最新的bpCustMember资料
            bpCustMember = bpCustMemberService.get(financeApplyUser.getUserID());
            if (!"".equals(bpCustMember.getHomePhone()) && bpCustMember.getHomePhone() != null && !bpCustMember.getHomePhone().equals("-")) {
                String[] strHomePhone = bpCustMember.getHomePhone().split("-");
                if (strHomePhone.length > 1) {
                    bpCustMember.setHomePhonePrefix(strHomePhone[0]);
                    bpCustMember.setHomePhoneSuffix(strHomePhone[1]);
                }
            }
            if (!"".equals(bpCustMember.getWebshopPhone()) && bpCustMember.getWebshopPhone() != null && !bpCustMember.getWebshopPhone().equals("-")) {
                String[] strWebPhone = bpCustMember.getWebshopPhone().split("-");
                if (strWebPhone.length > 1) {
                    bpCustMember.setWebshopPhonePrefix(strWebPhone[0]);
                    bpCustMember.setWebshopPhoneSuffix(strWebPhone[1]);
                }
            }
            if (!"".equals(bpCustMember.getHireCompanyphone()) && bpCustMember.getHireCompanyphone() != null && !bpCustMember.getHireCompanyphone().equals("-")) {
                String[] strComPhone = bpCustMember.getHireCompanyphone().split("-");
                if (strComPhone.length > 1) {
                    bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
                    bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
                }
            }
            //TODO 先申请园丁贷然后申请薪资贷这里覆盖    薪资贷中的”公司(单位)电话“
		/*	if(financeApplyUser.getProductId()==12){
					String[] strComPhone=bpCustMember.getHireCompanyphone().split("-");
					if(strComPhone.length>1){
						bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
						bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
					}

			}else{*/
            if (!"".equals(bpCustMember.getTeacherCompanyphone()) && bpCustMember.getTeacherCompanyphone() != null && !bpCustMember.getTeacherCompanyphone().equals("-")) {
                String[] strComPhone = bpCustMember.getTeacherCompanyphone().split("-");
                if (strComPhone.length > 1) {
                    bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
                    bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
                }
            }
            //	}
            if (!"".equals(bpCustMember.getBossCompanyphone()) && bpCustMember.getBossCompanyphone() != null && !bpCustMember.getBossCompanyphone().equals("-")) {
                String[] strComPhone = bpCustMember.getBossCompanyphone().split("-");
                if (strComPhone.length > 1) {
                    bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
                    bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
                }
            }
            //通过产品id，得到左侧菜单节点列表
            financeApplyUser.setProductId(financeApplyUser.getProductId());
            listApplyUser = getMenu(financeApplyUser);
            getDateList();//得到日期集合
            String isPass = financeApplyUserService.getPassThrough(cust.getId());//查询借款用户是否有已通过借款审核的信息
            this.getRequest().setAttribute("isPass", isPass);
            if ("7".equals(this.getRequest().getParameter("supplement"))) {
                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/finance/mobileAuthentication.ftl");
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.P2PPERSON).getTemplateFilePath());
            }
            return "freemarker2";
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "freemarker";
    }


    /**
     * 提交填写信息
     *
     * @return
     */
    public String fileUpload() {

        //得到bpFinancceApplyUser主键
        long loanid = Long.parseLong(this.getRequest().getParameter("loadid"));
        //得到该信息对象
        financeApplyUser = bpFinanceApplyUserService.get(loanid);
        webFinanceApplylist = webFinanceApplyUploadService.getUploadState(financeApplyUser.getUserID());
        String meg = "";
        if (financeApplyUser.getProductId() == 12) {//薪资贷
            for (WebFinanceApplyUploads web : webFinanceApplylist) {
                if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                    meg = "请上传身份认证";
                    break;
                } else if (web.getMaterialstype().equals("CreditRecord") && web.getFiles().equals("")) {
                    meg = "请上传信用认证";
                    break;
                } else if (web.getMaterialstype().equals("Income") && web.getFiles().equals("")) {
                    meg = "请上传收入认证";
                    break;
                }
            }
        } else if (financeApplyUser.getProductId() == 13) {//经营贷
            for (WebFinanceApplyUploads web : webFinanceApplylist) {
                if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                    meg = "请上传身份认证";
                    break;
                } else if (web.getMaterialstype().equals("CreditRecord") && web.getFiles().equals("")) {
                    meg = "请上传信用认证";
                    break;
                } else if (web.getMaterialstype().equals("Income") && web.getFiles().equals("")) {
                    meg = "请上传收入认证";
                    break;
                } else if (web.getMaterialstype().equals("CompanyPlace") && web.getFiles().equals("")) {
                    meg = "请上传经营场所认证";
                    break;
                }
            }
        } else if (financeApplyUser.getProductId() == 14) {//网商贷
            for (WebFinanceApplyUploads web : webFinanceApplylist) {
                if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                    meg = "请上传身份认证";
                    break;
                } else if (web.getMaterialstype().equals("WebShop") && web.getFiles().equals("")) {
                    meg = "请上传网店认证";
                    break;
                }
            }
        } else if (financeApplyUser.getProductId() == 15) {//园丁贷
            if ("园丁贷".equals(financeApplyUser.getProductName())) {
                for (WebFinanceApplyUploads web : webFinanceApplylist) {
                    if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                        meg = "请上传身份认证";
                        break;
                    } else if (web.getMaterialstype().equals("Income") && web.getFiles().equals("")) {
                        meg = "请上传收入认证";
                        break;
                    } else if (web.getMaterialstype().equals("Teacher") && web.getFiles().equals("")) {
                        meg = "请上传教师资格认证";
                        break;
                    }
                }
            } else {
                for (WebFinanceApplyUploads web : webFinanceApplylist) {
                    if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                        meg = "请上传身份认证";
                        break;
                    } else if (web.getMaterialstype().equals("Income") && web.getFiles().equals("")) {
                        meg = "请上传学生认证";
                        break;
                    }
                }
            }

        } else if (financeApplyUser.getProductId() == 16) {
            for (WebFinanceApplyUploads web : webFinanceApplylist) {
                if (web.getMaterialstype().equals("IDCard") && web.getFiles().equals("")) {
                    meg = "请上传身份认证";
                    break;
                } else if (web.getMaterialstype().equals("Income") && web.getFiles().equals("")) {
                    meg = "请上传学生认证";
                    break;
                }
            }
        }
        financeApplyUser.setProductId(financeApplyUser.getProductId());
        listApplyUser = getMenu(financeApplyUser);
        getDateList();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"success\":true,\"message\":");
        sb.append(gson.toJson(meg));
        if (!"".equals(meg) && meg != null) {//必要认证的都提交完成时
            sb.append(",\"result\":0");
            //this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSON).getTemplateFilePath());
        } else {
            sb.append(",\"result\":1");
        }
        sb.append("}");
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * 最后成功提交
     *
     * @return
     */
    public String successUpload() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //得到bpFinancceApplyUser主键
	/*	long loanid=Long.parseLong(this.getRequest().getParameter("loadid"));
		//得到该信息对象
		financeApplyUser=bpFinanceApplyUserService.get(loanid);
		if(financeApplyUser.getState().equals("0")){
			financeApplyUser.setState("1");
		}
		if(financeApplyUser.getState().equals("7")){
			financeApplyUser.setState("8");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		financeApplyUser.setCreateTime(df.format(new Date()));//erp读取的这个字段 暂且把提交时间存放在这
		financeApplyUser.setSubmitTime(df.format(new Date()));//提交时间
		bpFinanceApplyUserService.save(financeApplyUser);*/

        QueryFilter filter = new QueryFilter(this.getRequest());
        filter.addFilter("Q_recipient_L_EQ", mem.getId().toString());
        filter.addFilter("Q_status_S_EQ", "1");
        List<OaNewsMessage> list = oaNewsMessageService.getAll(filter);
        //我的消息
        int messageNum = 0;
        if (list != null && list.size() > 0) {
            messageNum = list.size();
        }
        String successHtml = "<a href='" + this.getBasePath() + "user/getBpCustMember.do' target='_self'><span class='loginname'>" + mem.getLoginname() + "</span></a><span class='sep'></span><a href='" + this.getBasePath() + "message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息(" + messageNum + ")</span></a><span class='sep'></span><a href='" + this.getBasePath() + "exitlogin.do' onClick='exit()'><span>退出</span></a>";
        this.getRequest().getSession().setAttribute("successHtml", successHtml);
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.UPMATERIAL).getTemplateFilePath());
        return "freemarker";
    }


    /**
     * 借款管理
     *
     * @return
     */
    public String loanmanagement() {
        this.getSession().setAttribute("highlight", 5);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            try {
                commoon(mem);
                java.math.BigDecimal loantotal = slFundIntentService.getLoanTotal(mem.getId());
                if (loantotal != null) {
                    loanTotal = loanTotal.add(loantotal);//借款总金额
                }
                java.math.BigDecimal money = slFundIntentService.getMoney(mem.getId(), "近10天借款总额");
                if (money != null) {
                    loanTotalTen = loanTotalTen.add(money);//近10天借款总额
                }
                java.math.BigDecimal money6 = slFundIntentService.getMoney(mem.getId(), null);//近10天应还金额
                if (money6 != null) {
                    money3 = money3.add(money6);
                }
                java.math.BigDecimal money4 = slFundIntentService.getMoney(mem.getId(), "逾期金额");
                java.math.BigDecimal money5 = slFundIntentService.getMoney(mem.getId(), "待还金额");

                if (money4 != null) {
                    money1 = money1.add(money4);
                }
                if (money5 != null) {
                    money2 = money2.add(money5);
                }

                BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
                applyUser.setUserID(mem.getId());
                listApplyUser = financeApplyUserService.getApplyUser(applyUser);
                back(mem);
                finishLoan(mem);
                //authorizationLoan(mem);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.getRequest().setAttribute("show", this.getRequest().getParameter("toAction"));
            //取出登录类型，个人还是企业
            String loginType = (String) this.getSession().getAttribute("loginType");
            if (loginType.equals("enterprise")) {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.LOANMANAGEMENTENTERPRISE).getTemplateFilePath());
            } else if (loginType.equals("person")) {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOANMANAGEMENT).getTemplateFilePath());
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.LOGIN).getTemplateFilePath());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }


    /**
     * 加载模板(我的借款)
     *
     * @return
     */
    public String loadTemplate() {
        this.getSession().setAttribute("highlight", 5);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            getBalance();
            String loginType = (String) this.getSession().getAttribute("loginType");
            if (loginType.equals("enterprise")) {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOANMANAGEMENTENTERPRISE).getTemplateFilePath());
            } else if (loginType.equals("person")) {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOANMANAGEMENT).getTemplateFilePath());
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }


    /**
     * 加载我的借款的统计数据
     *
     * @return
     */
    public String loadAllLoanData() {
        this.getSession().setAttribute("highlight", 4);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (isLogin()) {
            //理财计划列表
            String buffer = bpCustMemberService.queryAllJk(mem.getLoginname(), mem.getId(), getRequest());
            jsonString = buffer;
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }


    private void finishLoan(BpCustMember mem) {
        //已完成
        List<PlBidPlan> PlanLoan = new ArrayList<PlBidPlan>();
        try {
            List<BpFundIntent> list = bpFundIntentService.getIntentList(mem.getId(), "finish", pager);
            if (list != null && list.size() > 0) {
                for (BpFundIntent fund : list) {
                    PlBidPlan plBidPlan = plBidPlanService.setProperty(plBidPlanService.get(fund.getBidPlanId()), this.getRequest());
                    PlBidPlan plan = plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
                    plBidPlan.setNextTime(fund.getFactDate());
                    //plBidPlan.setUrl(plManageMoneyPlanBuyinfoService.getUrl(fund.getOrderNo(), ""));//下载合同路径
                    plBidPlan.setUrl(plManageMoneyPlanBuyinfoService.queryLoanUrl(fund.getBidPlanId()));
                    plBidPlan.setLoanMoney(fund.getAfterMoney());
                    PlanLoan.add(plan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getRequest().setAttribute("PlanLoan", PlanLoan);
    }

    private void authorizationLoan(BpCustMember mem) {
        //还款授权
        try {
            List<PlBidPlan> authorizationPlan = new ArrayList<PlBidPlan>();
            List<BpFundIntent> list = bpFundIntentService.getIntentList(mem.getId(), "authorization", pager);
            if (list != null && list.size() > 0) {
                for (BpFundIntent fund : list) {
                    PlBidPlan plBidPlan = plBidPlanService.setProperty(plBidPlanService.get(fund.getBidPlanId()), this.getRequest());
                    PlBidPlan plan = plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
                    //plBidPlan.setUrl(plManageMoneyPlanBuyinfoService.getUrl(fund.getOrderNo(), ""));//下载合同路径
                    plBidPlan.setUrl(plManageMoneyPlanBuyinfoService.queryLoanUrl(fund.getBidPlanId()));
                    plBidPlan.setLoanMoney(fund.getAfterMoney());
                    authorizationPlan.add(plan);
                }
            }
            this.getRequest().setAttribute("authorizationPlan", authorizationPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void back(BpCustMember mem) {
        QueryFilter filter = new QueryFilter(getRequest());
        if (notMoney != null && "1".equals(notMoney)) {
            searchMap.put("notMoney", "1");
        } else if (notMoney != null && "2".equals(notMoney)) {
            searchMap.put("notMoney", "2");
        }
        String Q_projectName_S_LK = getRequest().getParameter("Q_projectName_S_LK");
        if (null != Q_projectName_S_LK && !"".equals(Q_projectName_S_LK)) {
            searchMap.put("Q_projectName_S_LK", Q_projectName_S_LK);
        }
        String Q_incomeMoney_BD_EQ = getRequest().getParameter("Q_incomeMoney_BD_EQ");
        if (null != Q_incomeMoney_BD_EQ && !"".equals(Q_incomeMoney_BD_EQ)) {
            searchMap.put("Q_incomeMoney_BD_EQ", Q_incomeMoney_BD_EQ);
        }
        if (selectTime != null && !selectTime.equals("")) {
            searchMap.put("selectTime", selectTime);
        }
        if (null != selectTime2 && !"".equals(selectTime2)) {
            searchMap.put("selectTime2", selectTime2);
        }

        if (pager == null) {
            pager = new Pager();
        }

        filter.getPagingBean().setStart((pager.getPageNumber() - 1) * pager.getPageSize());
        filter.getPagingBean().setPageSize((pager.getPageNumber() - 1) * pager.getPageSize() + pager.getPageSize());
        List<FundPay> list1 = slFundIntentService.getPay(getRequest(), filter.getPagingBean(), Long.valueOf(mem.getId()), "");
        for (int i = 0; i < list1.size(); i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(list1.get(i).getIntentDate());
            cal.add(Calendar.DAY_OF_YEAR, -5);
            Date newdt1 = cal.getTime();
            list1.get(i).setIntentDate(newdt1);
        }
        pager.setTotalCount(filter.getPagingBean().getTotalItems());
        pager.setList(list1);


    }

    /**
     * 理财回款
     *
     * @return
     */
    public String financialreturn() throws Exception {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        bidPlanFinancial = new ArrayList<PlBidPlan>();
        if (mem != null) {
            commoon(mem);
            List<BpFundIntent> list = bpFundIntentService.getIntentList(mem.getId(), null, pager);
            if (list != null && list.size() > 0) {
                for (BpFundIntent fund : list) {
                    PlBidPlan plan = plBidPlanService.get(fund.getBidPlanId());
                    plBidPlanService.setProperty(plan, this.getRequest());
                    PlBidPlan PlBid = plBidPlanService.returnPlBidPlan(plan);
                    PlBid.setAllCount(bpFundIntentService.getProgressTotal(fund.getProjectId(), mem.getLoginname(), "total"));
                    PlBid.setNowCount(bpFundIntentService.getProgressTotal(fund.getProjectId(), mem.getLoginname(), null));

                    BigDecimal PunishAccrual = new BigDecimal(0);
                    java.math.BigDecimal accrualMoney = new BigDecimal(0);
                    List<BpFundIntent> listFund = bpFundIntentService.getFund(fund.getProjectId());
                    for (BpFundIntent intent : listFund) {
                        java.util.Date fiveDate = getDateAfter(intent.getIntentDate(), 6);//得到预期还款后的地6天时间
                        int day = getDateDay(fiveDate);//罚款的天数
                        if ("".equals(intent.getPunishAccrual()) || intent.getPunishAccrual() == null) {
                            PunishAccrual = new BigDecimal(0);
                        }
                        accrualMoney = intent.getNotMoney().multiply(intent.getPunishAccrual()).multiply(new BigDecimal(day <= 0 ? 0 : day));
                    }
                    fund.setPunishAccrual(PunishAccrual);
                    fund.setAccrualMoney(accrualMoney);

                    PlBid.setFinancialAfterMoney(fund.getIncomeMoney());
                    PlBid.setFinancialNotMoney(fund.getPunishAccrual());
                    PlBid.setFinancialPayMoney(fund.getAccrualMoney());
                    PlBid.setFullTime(fund.getFactDate());
                    bidPlanFinancial.add(PlBid);
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.FINANCIALRETURN).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }

        return "freemarker";
    }

    /**
     * 还款列表
     *
     * @return
     */
    public String returnmoney() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {

            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.RETURNMONEY).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }

        return "freemarker";
    }

    /**
     * 投资人的回款计划
     * user/paymentplanBpCustMember.do
     */
    public String paymentplan() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        try {
            if (mem != null) {
                String str = this.getRequest().getParameter("str");
                String[] strArray = str.split(",");
                typeTopay = strArray[1];
                PlBidSale plBidSale = null;
                if (Long.valueOf(strArray[0]) != null) {
                    QueryFilter filter = new QueryFilter();
                    filter.addFilter("Q_bidPlanID_L_EQ", Long.valueOf(strArray[0]));
                    List<PlBidSale> plBidSales = plBidSaleService.getAll(filter);
                    if (plBidSales != null && plBidSales.size() > 0) {
                        plBidSale = plBidSales.get(0);
                    }
                }

                if (plBidSale != null) {
                    if (plBidSale.getSaleStatus() != null && (plBidSale.getSaleStatus() == 1 || plBidSale.getSaleStatus() == 4)) {
                        fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), null, strArray[2], "InvestRepayMemtSignle", null);
                    } else {
                        fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), strArray[2], "InvestRepayMemtSignle", null);
                    }
                } else {
                    fundIntentpay = bpFundIntentService.findInvestRepaymentList(mem.getLoginname(), mem.getId(), strArray[2], "InvestRepayMemtSignle", null);
                }


				/*if(strArray[1].equals("PlanbackingOne")||strArray[1].equals("lookUpLoan")||strArray[1].equals("Financial")||strArray[1].equals("Loan")){
					fundIntentpay = slFundIntentService.getPay(strArray[0],strArray[1],mem.getLoginname(),null);
					for(int i=0;i<fundIntentpay.size();i++){
						Calendar cal = Calendar.getInstance();
				        cal.setTime(fundIntentpay.get(i).getIntentDate());
				        cal.add(Calendar.DAY_OF_YEAR,-5);
				        Date newdt1 = cal.getTime();
				        fundIntentpay.get(i).setIntentDate(newdt1);
					}
				}else if(strArray[1].equals("Repaymented")){
					fundIntentpay = slFundIntentService.getPay(strArray[0],strArray[1],mem.getLoginname(),null);
				}else if(strArray[1].equals("Financing")){
					fundIntentpay = slFundIntentService.getPay(strArray[0],strArray[1],mem.getLoginname(),strArray[2]);
				}else{
					fundIntent = bpFundIntentService.getBpFundIntentAll(strArray[0],strArray[1],mem.getLoginname());//第一个参数是标id，第二个参数是请求类型
				}

				System.out.println("===="+fundIntentpay.size());
				if(fundIntentpay!=null){
					for(FundPay fund : fundIntentpay){
						if("lookUpLoan".equals(typeTopay) || "PlanbackingOne".equals(typeTopay)){
							fund.setPlanPaymentsMoney((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()));
							fund.setFactPaymentsMoney(fund.getAfterMoney()==null?BigDecimal.ZERO:fund.getAfterMoney());
						}else if("Repaymented".equals(typeTopay)){
							fund.setPlanPaymentsMoney((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()));
							fund.setFactPaymentsMoney((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()));
						}else if("Financing".equals(typeTopay)){
							fund.setPlanPaymentsMoney((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()));
							if(fund.getAfterMoney().compareTo(BigDecimal.ZERO)>0){
								fund.setFactPaymentsMoney((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()));
							}else{
								fund.setFactPaymentsMoney(fund.getAfterMoney()==null?BigDecimal.ZERO:fund.getAfterMoney());
							}
						}else if("Financial".equals(typeTopay) || "Loan".equals(typeTopay)){
							fund.setPlanPaymentsMoney(((fund.getPrincipalRepaymentMoney()==null?BigDecimal.ZERO:fund.getPrincipalRepaymentMoney()).add(fund.getLoanInterestMoney()==null?BigDecimal.ZERO:fund.getLoanInterestMoney()).add(fund.getComprehensiveMoney()==null?BigDecimal.ZERO:fund.getComprehensiveMoney())));
							fund.setFactPaymentsMoney(fund.getAfterMoney()==null?BigDecimal.ZERO:fund.getAfterMoney());
						}
					}
				}*/
                //
                //fundIntent = bpFundIntentService.getBpFundIntentAll(bidId, "Financial");
                //System.out.println("===="+fundIntentpay.size());
                String isMobile = this.getRequest().getParameter("isMobile");
                if (null != isMobile && isMobile.endsWith("1")) {
                    for (int i = 0; i < fundIntentpay.size(); i++) {
                        BigDecimal money1 = fundIntentpay.get(i).getPayIntestPrinMoney();
                        BigDecimal money2 = fundIntentpay.get(i).getCouponMoney();
                        BigDecimal money3 = fundIntentpay.get(i).getCompensationMoney();
                        fundIntentpay.get(i).setCompensationMoney(money1.add(money2).add(money3));
                    }
                    StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
                            .append(fundIntentpay.size()).append(",\"result\":");
                    JSONSerializer json = JsonUtil.getJSONSerializer();
                    json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{"intentDate"});
                    buff.append(json.serialize(fundIntentpay));
                    buff.append("}");
                    jsonString = buff.toString();
                    return SUCCESS;
                }

                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PAYMENTPLAN).getTemplateFilePath());
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "freemarker";
    }

    /**
     * 自动投标
     *
     * @return
     */
    public String automaticbid() throws Exception {
        String isMobile = this.getRequest().getParameter("isMobile");
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            commoon(mem);
            QueryFilter filter = new QueryFilter();
            filter.addSorted("keyStr", QueryFilter.ORDER_ASC);
            List<PlKeepCreditlevel> listName = plKeepCreditlevelService.getAll(filter);
            this.getRequest().setAttribute("plKeepCreditlevel", listName);//信用等级
            List<String> listPeriod = new ArrayList<String>();
            for (int i = 0; i <= 36; i++) {
                listPeriod.add(String.valueOf(i));
            }
            this.getRequest().setAttribute("listPeriod", listPeriod);//期限
            bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
            if (bidAuto == null) {
                bidAuto = initPlBidAuto(mem);
            }
            if (bidAuto.getRateStart() != null) {
                bidAuto.setRateStartShow(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateStart())).getName());
            }
            if (bidAuto.getRateEnd() != null) {
                bidAuto.setRateEndShow(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateEnd())).getName());
            }
            if (isMobile != null) {
                StringBuffer buff = new StringBuffer("{\"success\":true,\"data\":");
                JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime",
                        "bidEndTime");
                buff.append(json.serialize(bidAuto));
                buff.append("}");
                jsonString = buff.toString();
                System.out.println("jsonString====" + jsonString);
                return SUCCESS;
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AUTOMATICBID).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 保存自动投标信息
     *
     * @return
     */
    public String saveAutoBidInfo() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            PlBidAuto auto = new PlBidAuto();
            bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
            auto.setId(bidAuto.getId());
            auto.setUserID(mem.getId());
            auto.setBidMoney(bidMoney);
            auto.setInterestStart(interestStart);
            auto.setInterestEnd(interestEnd);
            auto.setPeriodStart(periodStart);
            auto.setPeriodEnd(periodEnd);
            auto.setRateStart(rateStart);
            auto.setRateEnd(rateEnd);
            auto.setKeepMoney(keepMoney);
            auto.setIsOpen(bidAuto.getIsOpen());
            auto.setOrderTime(bidAuto.getOrderTime());
            auto.setBanned(bidAuto.getBanned());
            String ret = plBidAutoService.savechk(auto);
            if (ret.indexOf(Constants.FAILDFLAG) == -1) {
                bidAuto = plBidAutoService.merge(auto);
                if (bidAuto.getRateStart() != null) {
                    bidAuto.setRateStart(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateStart())).getName());
                }
                if (bidAuto.getRateEnd() != null) {
                    bidAuto.setRateEnd(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateEnd())).getName());
                }
                bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
                Gson gson = new Gson();
                jsonString = gson.toJson(bidAuto);
                jsonString = "{\"success\":true," + jsonString.substring(1) + "";
            } else {
                jsonString = "{\"success\":false," + ret.substring(1) + "";
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }

        return SUCCESS;
    }

    /**
     * 开启自动投标功能
     *
     * @return
     */
    public String openBidAuto() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String isMobile = this.getRequest().getParameter("isMobile");
        String[] str = null;
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        /**
         * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
         */
        Object[] usercondition = bpCustMemberService.checkUserDealCondition(mem);
        if ((Boolean) usercondition[0]) {
            CommonResponse cr = new CommonResponse();
            str = plBidAutoService.chk(mem.getId());

            if (str[0].equals(Constants.SUCCESSFLAG)) {
                String orderNo = ContextUtil.createRuestNumber();
                CommonRequst cq = new CommonRequst();
                if (isMobile != null && !"".equals(isMobile) && ("1".equals(isMobile))) {
                    cq.setIsMobile("1");//判断手机端操作
                }
                cq.setThirdPayConfigId(mem.getThirdPayFlagId());
                cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
//				if(mem.getSecondAudit() != null && mem.getSecondAudit().equals("1")){
//					cq.setAuthorizeTypeOpen("1");//开启授权类型
//				}else{
//					cq.setAuthorizeTypeOpen("1,3");//开启授权类型
//				}
                if (mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)) {//个人用户
                    cq.setAccountType(0);
                } else {//企业用户
                    cq.setAccountType(1);
                }
                cq.setBussinessType(ThirdPayConstants.BT_OPENBIDAUTH);//业务类型
                cq.setTransferName(ThirdPayConstants.TN_OPENBIDAUTH);//业务名称
                cq.setRequsetNo(orderNo);//流水号
                cr = ThirdPayInterfaceUtil.thirdCommon(cq);
                if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
                    bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
                    bidAuto.setRequestNo(orderNo);
                    plBidAutoService.save(bidAuto);
                } else if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                    bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
                    bidAuto.setRequestNo(orderNo);
                    plBidAutoService.save(bidAuto);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("ThirdPayConfigId", mem.getThirdPayFlagId());//第三方方标识
                    map.put("custermemberId", mem.getId().toString());//用户Id
                    map.put("open", ThirdPayConstants.BT_OPENBIDAUTH);//开启类型
                    map.put("close", "");//关闭类型
                    opraterBussinessDataService.umpayLoanAuthorize(map);
                    webMsgInstance("0", Constants.CODE_SUCCESS, cr.getResponseMsg(), "", "", "", "", "");
                    if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
                        this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                    }else{
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
                    }
                } else {
                    webMsgInstance("0", cr.getResponsecode(), cr.getRequestInfo(), "", "", "", "", "");
                    if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
                        this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                    }
                }
            } else {
                webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(), "", "", "", "", "");
                if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
                    this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                }
            }
        } else {
            if (mem.getIsCheckCard() == null || !mem.getIsCheckCard().equals("1")  ){
                webMsgInstance("0",Constants.CODE_SUCCESS,"请先实名认证","","","","","");
            }
            if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
            }else{
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
            }
        }
        return "freemarker";
    }

    /**
     * 关闭自动投标功能
     *
     * @return
     */
    public String closeBidAuto() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String isMobile = this.getRequest().getParameter("isMobile");
        if (mem != null) {
            CommonResponse cr = new CommonResponse();
            String orderNo = ContextUtil.createRuestNumber();
            CommonRequst cq = new CommonRequst();
            cq.setThirdPayConfigId(mem.getThirdPayFlagId());
            cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
            cq.setBussinessType(ThirdPayConstants.BT_CLOSEBIDAUTH);//业务类型
            cq.setTransferName(ThirdPayConstants.TN_CLOSEBIDAUTH);//业务名称
            cq.setRequsetNo(orderNo);
            cq.setAuthorizeTypeClose("1");//关闭授权类型
            if (mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)) {//个人用户
                cq.setAccountType(0);
            } else {//企业用户
                cq.setAccountType(1);
            }
            cr = ThirdPayInterfaceUtil.thirdCommon(cq);
            if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
//				if(cr.getReturnType().equals(CommonResponse.RETURNTYPE_JOSN)){
                PlBidAuto auto = plBidAutoService.getPlBidAuto(mem.getId());
                auto.setOrderTime(null);
                auto.setIsOpen(0);
                plBidAutoService.save(auto);
                bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
                mem.setTender("1");
                webMsgInstance("0", Constants.CODE_SUCCESS, "已关闭自动投标功能", "", "", "", "", "");
//				}
            } else {
                webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(), "", "", "", "", "");
            }
            if (isMobile != null && !"".equals(isMobile) && "1".equals(isMobile)) {
                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 判断是否为整数
     *
     * @param num
     * @return
     */
    @SuppressWarnings("unused")
    private boolean isInt(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            e.getMessage();
            return false;
        }
    }

    private String notMoney;
    private String selectTime;
    private String selectTime2;

    public String getNotMoney() {
        return notMoney;
    }

    public void setNotMoney(String notMoney) {
        this.notMoney = notMoney;
    }

    public String getSelectTime() {
        return selectTime;
    }

    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }

    public String getSelectTime2() {
        return selectTime2;
    }

    public void setSelectTime2(String selectTime2) {
        this.selectTime2 = selectTime2;
    }

    /**
     * 得到5天后的时间(即第6天的时间)
     *
     * @param d
     * @param day
     * @return
     */
    public Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到相差的天数
     *
     * @param date
     * @return
     */
    public int getDateDay(Date date) {
        long millisecond = new Date().getTime() - date.getTime();
        int day = (int) (millisecond / 24L / 60L / 60L / 1000L);
        return day;
    }

    //手机端的关于页面
    public String about() {
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEABOUT).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 优惠活动页面
     *
     * @return
     */
    public String solidactivity() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

        //得到微博二维码
        List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
        this.getRequest().setAttribute("fileList", fileList);
        QueryFilter filter = new QueryFilter(getRequest());
        filter.addFilter("Q_novice_N_EQ", "1");
        filter.addSorted("publishSingeTime", "desc");
        filter.getPagingBean().setPageSize(3);
        List<PlBidPlan> activityList = plBidPlanService.getAll(filter);
        for (PlBidPlan plan : activityList) {
            plBidPlanService.setProperty(plan, this.getRequest());
            plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
        }
        this.getRequest().setAttribute("activityList", activityList);

		 /*this.getSession().setAttribute("highlight", 7);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			int percent = 0;
			if("1".equals(bpCustMember.getIsCheckEmail())&&bpCustMember.getIsCheckEmail()!=null){
				//判断邮箱是否验证
				percent += 10;
			}
			if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
				//判断手机是否验证
				percent += 20;
			}
			if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
				//判断是否实名认证
				percent += 40;
			}
			if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
				//判断手机是否验证
				percent += 30;
			}
			commoon(bpCustMember);
			//保存信誉等级
			this.getRequest().setAttribute("percent", percent);*/
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.SOLIDACTIVITY).getTemplateFilePath());
		/*}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}*/
        return "freemarker";
    }

    /**
     * 提前还款
     *
     * @return
     */
    public String advanceLoan() {
        try {
            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            if (mem != null) {
                this.getRequest().setAttribute("nowTime", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
                String planId = this.getRequest().getParameter("bidPlanId");//标的id
                String prepaymentStr = plBidPlanService.getPrepaymentData(Long.valueOf(planId), bpCustMemberService.get(mem.getId()));
                this.getRequest().setAttribute("prepaymentStr", prepaymentStr);
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
                return "freemarker";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ADVANCELOAN).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 保存提前还款
     *
     * @return
     */
    public String saveRepaymentRecord() {
        return null;/*
		String fagStr = "error";
		try {
			String bidPlanId = this.getRequest().getParameter("bidPlanId");
			boolean fag = slEarlyRepaymentRecordService.saveEarlyProjectInfo(Long.valueOf(bidPlanId));
			SlEarlyRepaymentRecord repay = slEarlyRepaymentRecordService.queryId(Long.valueOf(bidPlanId));
			if (fag) {
				if(falg){

				}
				fagStr = "success";
			}
			boolean falg = slEarlyRepaymentRecordService.updateFundIntentInfo(Long.valueOf(bidPlanId), repay.getSlEarlyRepaymentId());//改变提前还款的状态
			this.getRequest().setAttribute("fagStr", fagStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.advanceLoan();
	*/
    }


    /**
     * 通过投资人ID获取该客户托管账户余额
     *
     * @return
     */
    public String getMoneyAccountLeft() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyAccountLeft(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
     *
     * @return
     */
    public String getMoneyBidFrozen() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //散标冻结金额
        BigDecimal money = bpCustMemberService.getMoneyBidFrozen(mem.getId());
        //理财计划冻结金额
        BigDecimal money1 = bpCustMemberService.getMoneyMmplanFrozen(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);

        }
        if (money1 == null) {
            money1 = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.add(money1).setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人所有的待回收本金和利息(第一部分：散标投资的本金和利息   + 第二部分：理财计划的本金和利息)
     *
     * @return
     */
    public String getMoneyInvestAll() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyInvestAll(mem.getId());
        BigDecimal money1 = bpCustMemberService.getMoneyInvestAll1(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }
        if (money1 == null) {
            money1 = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.add(money1).setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人待还借款总额(款项计划表中该借款人所有待还款型之和)
     *
     * @return
     */
    public String getMoneyDueinAll() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyDueinAll(mem.getLoginname());
        if (money == null) {
            money = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 账户净资产：表示客户账户净值，做净值标的时候可以复用。("账户可用金额、投标冻结金额、待收投资总额三个"金额相加减去"待还借款总额")
     *
     * @return
     */
    public String getMoneyAccountNetasset() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(mem.getId(), ObSystemAccount.type0.toString());
        BigDecimal money = ret[3];//账户可用金额
        BigDecimal money1 = bpCustMemberService.getMoneyBidFrozen(mem.getId());//冻结资金总额散标
        BigDecimal money2 = bpCustMemberService.getMoneyMmplanFrozen(mem.getId());//冻结资金总额理财计划
        BigDecimal money3 = bpCustMemberService.getMoneyInvestAll(mem.getId());//待收投资总额第一部分：散标投资的本金和利息
        BigDecimal money4 = bpCustMemberService.getMoneyInvestAll1(mem.getId());//第二部分：理财计划的本金和利息
        BigDecimal money5 = bpCustMemberService.getMoneyDueinAll(mem.getLoginname());//待还借款总额
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
        BigDecimal totalMoney = money.add(money1).add(money2).add(money3).add(money4).subtract(money5);
        jsonString = "{\"result\":\"" + totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";

        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第一部分散标投资的投资额 + 第二部分理财计划的本金和利息
     *
     * @return
     */
    public String getMoneyAccumulativeInvest() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyAccumulativeInvest(mem.getId());
        System.out.println("money=" + money);
        BigDecimal money1 = bpCustMemberService.getMoneyAccumulativeInvest1(mem.getId());
        System.out.println("money1=" + money1);
        if (money == null) {
            money = new BigDecimal(0);
        }
        if (money1 == null) {
            money1 = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.add(money1).setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }


    /**
     * 通过投资人ID获取该投资人投标冻结中笔数 (第一部分散标投资的投标冻结笔数  + 第二部分理财计划的投标冻结笔数)
     *
     * @return
     */
    public String getCountBidFrozen() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Integer count = Integer.valueOf(bpCustMemberService.getCountBidFrozen(mem.getId()).toString());
        //Integer count1 = Integer.valueOf(bpCustMemberService.getCountBidFrozen1(mem.getId()).toString());
        if (count == null) {
            count = 0;
        }
	/*	if(count1 == null) {
			count1 = 0;
		}*/
        jsonString = "{\"result\":\"" + (count) + "\"}";
        return SUCCESS;
    }


    /**
     * 通过投资人ID获取该投资人待回款投资笔数(第一部分散标投资的投资笔数  + 第二部分理财计划的投资笔数)
     *
     * @return
     */
    public String getCountInvestBack() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Integer count = bpCustMemberService.getCountInvestBack(mem.getId());
        Integer count1 = bpCustMemberService.getCountInvestBack1(mem.getId());
        if (count == null) {
            count = 0;
        }
        if (count1 == null) {
            count1 = 0;
        }
        jsonString = "{\"result\":\"" + (count + count1) + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人招标中借款笔数
     *
     * @return
     */
    public String getCountBidLoan() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Integer count = bpCustMemberService.getCountBidLoan(mem.getLoginname());
        if (count == null) {
            count = 0;
        }
        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人还款中借款笔数
     *
     * @return
     */
    public String getCountRepaymentLoan() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Integer count = bpCustMemberService.getCountRepaymentLoan(mem.getLoginname());
        if (count == null) {
            count = 0;
        }
        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过投资人ID获取该投资人累计到账收益(客户账户总计收到的所有利息)
     *
     * @return
     */
    public String getMoneyAccumulativeIncome() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = new BigDecimal(0);
        if (null != mem) {
            BigDecimal   money12 = bpCustMemberService.getMoneyAccumulativeIncome(mem.getId());
            BigDecimal raiseinterest =  bpFundIntentService.getByRaiseinterest(mem.getId());
            money = money12.add(raiseinterest);
        }
        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;

    }

    /**
     * 过投资人ID获取该投资人 累计回收本金
     *
     * @return
     */
    public String getMoneyPreweekIncome() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyPreweekIncome(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;

    }

    /**
     * 过投资人ID获取该投资人上月到账收益(30天以来，包括第30天，客户账户总计收到的所有利息)
     *
     * @return
     */
    public String getMoneyPremonthIncome() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyPremonthIncome(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;

    }

    /**
     * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第一部分是散标投资的利息)
     *
     * @return
     */
    public String getMoneyExpectIncome() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyExpectIncome(mem.getId());
        BigDecimal money1 = bpCustMemberService.getMoneyExpectIncome1(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }
        if (money1 == null) {
            money1 = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.add(money1).setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 累计收益率：累计到账收益/累计投资额
     *
     * @return
     */
    public String getMoneyAccumulativeIncomeRate() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpCustMemberService.getMoneyAccumulativeIncome(mem.getId());
        BigDecimal money1 = bpCustMemberService.getMoneyPreweekIncome(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }
        if (money1 == null) {
            money1 = new BigDecimal(0);
        }
        BigDecimal rate = BigDecimal.ZERO;
        if (money1.compareTo(BigDecimal.ZERO) == 0 || money.compareTo(BigDecimal.ZERO) == 0) {
        } else {
            rate = money.multiply(new BigDecimal(100)).divide(money1, 2, BigDecimal.ROUND_HALF_UP);
        }
        jsonString = "{\"result\":\"" + rate + "%\"}";
        return SUCCESS;
    }


    /**
     * 验证客户端是否需要输入验证码  add by yanfeng 2015-07-14
     *
     * @param request
     * @return true:频繁操作; false:正常操作
     */
    private static Map<String, Long> validataIpMap = new HashMap<String, Long>();
	/*private boolean isValidataRequest(HttpServletRequest request){
		String ip = IPv4Tool.getIpAddr(request);
		log.info("clientIp : "+ip);  //如果ip为0:0:0:0:0:0:0:1，需要把浏览器的localhost改为127.0.0.1
		String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		if(!ip.matches(regex)){
			return true;
		}
		String ipL = String.valueOf(IPv4Tool.ipToLong(ip));
		if(validataIpMap.get(ipL)==null){
			validataIpMap.put(ipL, System.currentTimeMillis());
		}else{
			Long lastTimeL = validataIpMap.get(ipL);
			Date lastTime = new Date(lastTimeL);
			lastTime = DateUtils.addSeconds(lastTime, 120);  //设置120秒后校验
			Date currentTime = new Date();
			boolean b = currentTime.after(lastTime);
			if(b){  //如果超过了120秒则表示正常请求
				validataIpMap.remove(ipL);
				return false;
			}else{  //否则提示频繁操作
				return true;
			}
		}
		return false;
	}*/

    /**
     * 发送验证码接口
     */
    public String validataTel2CheckCode2() {

        String tel = this.getRequest().getParameter("telphone");
        BpCustMember mem = null;
        mem = bpCustMemberService.getMemberByPhone(tel);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"success\":true,\"errMsg\":");
        //通过电话查找用户,没有注册用户后可以发送验证码
        if (mem == null) {
        } else {
            sb.append(gson.toJson("该号码已经注册"));
        }
        sb.append("}");
        jsonString = sb.toString();
        return SUCCESS;
    }

    public String getRecommandPerson() {
        return recommandPerson;
    }

    public void setRecommandPerson(String recommandPerson) {
        this.recommandPerson = recommandPerson;
    }

    /**
     * 企业客户第三方账户激活说明。
     *
     * @return
     */
    public String offerMaterial() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Object[] userCondition = bpCustMemberService.checkUserCondition(mem);
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        if ((Boolean) userCondition[0]) {//检查用户基本资格
            bpCustMemberService.queryThirdPayCustomerInfo(mem);
            forwardPage = DynamicConfig.OFFERMATERIAL_THIRDPAY;

        } else {
            forwardPage = userCondition[2].toString();
            webMsgInstance("0", Constants.CODE_FAILED, userCondition[1].toString(), "", "", "", "", "");
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
        return "freemarker";
    }

    public String isSessinonValid() {
        //if(null!=isMobile&&isMobile.endsWith("1"))
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String backpath = (String) this.getSession().getAttribute("backpath");
        String recommand = (String) this.getSession().getAttribute("recommand");
        StringBuffer sb = new StringBuffer();
        Gson gsons = new GsonBuilder().create();
        if (null == cust) {
            sb.append("{\"success\":false,\"data\":");
            sb.append("{\"backpath\":"+gsons.toJson(backpath)+",\"recommand\":"+gsons.toJson(recommand));
            sb.append("}");
        } else {
            cust = bpCustMemberService.get(cust.getId());
            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());
            if (ret != null) {
                cust.setTotalMoney(ret[1]);
                cust.setAvailableInvestMoney(ret[3]);
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            sb.append("{\"success\":true,\"data\":");
            sb.append(gson.toJson(cust));
            sb.append(",\"backpath\":" + gson.toJson(backpath));
        }
        sb.append("}");
        setJsonString(sb.toString());
        this.getSession().removeAttribute("backpath");
        this.getSession().removeAttribute("recommand");
        return SUCCESS;

    }




    public String userValid() {
        //if(null!=isMobile&&isMobile.endsWith("1"))
        MobileDataResultModel mobile = new MobileDataResultModel();
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (null == cust) {
            mobile.setMsg("1");
            mobile.setVersion(1);
        }else{

            BpCustMember bpCustMembers = bpCustMemberService.getUserInfo(cust.getId());
            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());
            if (ret != null) {
                BigDecimal money = ret[3];//账户可用金额
                BigDecimal money1 = bpCustMemberService.getMoneyBidFrozen(cust.getId());//冻结资金总额散标
                BigDecimal money2 = bpCustMemberService.getMoneyMmplanFrozen(cust.getId());//冻结资金总额理财计划
                BigDecimal money3 = bpCustMemberService.getMoneyInvestAll(cust.getId());//待收投资总额第一部分：散标投资的本金和利息
                BigDecimal money4 = bpCustMemberService.getMoneyInvestAll1(cust.getId());//第二部分：理财计划的本金和利息
                BigDecimal money5 = bpCustMemberService.getMoneyDueinAll(cust.getLoginname());//待还借款总额
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
                BigDecimal AllInterest = bpCustMemberService.getMoneyPreweekIncome(cust.getId());//累计收益金额
                if (money == null) {
                    money = new BigDecimal(0);
                }

                    bpCustMemberService.evict(bpCustMembers);
                    bpCustMembers.setTotalMoney(totalMoney);//总金额
                    bpCustMembers.setAvailableInvestMoney(money);//可用金额
                    bpCustMembers.setAllInterest(AllInterest);//累计收益金额
                    bpCustMembers.setVersion(null);
                    bpCustMembers.setFreezeMoney(null);
                    bpCustMembers.setNotallInterest(null);
                    bpCustMembers.setPrincipalRepayment(null);
                    bpCustMembers.setThirdPayStatus(null);
                    bpCustMembers.setTotalEnchashment(null);
                    bpCustMembers.setTotalRecharge(null);
                    bpCustMembers.setUnChargeMoney(null);
                    bpCustMembers.setCustomerType(null);
            }
            String imgurl = (String) this.getRequest().getSession().getAttribute("imgurl");
            if (org.apache.commons.lang3.StringUtils.isNotBlank(imgurl)){
                this.getRequest().getSession().removeAttribute("imgurl");
                imgurl="/theme/proj_wenandai/images/uc/urer-name-pic.png";
            }
            Map<String,String> imgMap = new HashMap<String,String>();
            imgMap.put("imgurl",imgurl);
            mobile.addDataContent("bpCustMembers",bpCustMembers);
            mobile.addDataContent("imgMap",imgMap);
            mobile.setMsg("0");
        }
        setJsonString( mobile.toJSON());
        return SUCCESS;
    }

    /**
     * 查询台账中是否有还款成功返款未成功的
     * 并进行返款
     */
    public void checkReturnMoney(BpCustMember mem) {
        List<BpFundIntent> list = bpFundIntentService.getUnReturnFund();
        if (list.size() > 0) {
            Long proId = list.get(0).getProjectId();
            if (mem.getCustomerType() != null && mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)) {

            } else {

            }
        }
    }

    /**
     * 跳转到评估页面
     *
     * @return
     */
    public String toAccess() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String isAccess = this.getRequest().getParameter("isAccess");
        //评估之后跳转到之前的购买页面
        //begin (散标、U、D计划)
        String bidId = this.getRequest().getParameter("bidId");//标id
        String bidType = this.getRequest().getParameter("bidType");//标类型
        this.getSession().setAttribute("bidId", bidId);
        this.getSession().setAttribute("bidType", bidType);
        //end
        String from = this.getRequest().getParameter("from");
        if (from != null && !"".equals(from)) {
            this.getSession().setAttribute("from", "1");
        }
        if (null != isAccess && !"".equals(isAccess)) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TOACCESS).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ACCESS).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 保存评估结果
     *
     * @return
     */
    public String saveAccess() {
        try {
            //评估之后跳转到之前的购买页面
            //begin (散标、U、D计划)
            if (null == this.getSession().getAttribute("bidId")) {
                String bidId = this.getRequest().getParameter("bidId");//标id
                String bidType = this.getRequest().getParameter("bidType");//标类型
                this.getSession().setAttribute("bidId", bidId);
                this.getSession().setAttribute("bidType", bidType);
            }
            bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            BpCustMember oldMember = bpCustMemberService.get(bpCustMember.getId());
            String accessScore = this.getRequest().getParameter("accessScore");//分数
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
            if (from != null && !"".equals(from)) {
                this.getRequest().setAttribute("from", "1");
            }
            bpCustMemberService.merge(oldMember);
            BeanUtil.copyNotNullProperties(bpCustMember, oldMember);
        } catch (Exception e) {
            System.out.println("报错了!!!");
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ACCESSRESULT).getTemplateFilePath());
        return "freemarker";
    }


    /**
     * 借款人还款计划
     *
     * @return
     */
    public String loanRepayMentPlan() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        try {
            if (mem != null) {
                List<ShowManageMoney> list = new ArrayList<ShowManageMoney>();
                String str = this.getRequest().getParameter("str");
                String[] strArray = str.split(",");
                typeTopay = strArray[1];
                if (strArray[1].equals("PlanbackingOne") || strArray[1].equals("lookUpLoan") || strArray[1].equals("Financial") || strArray[1].equals("Loan")) {
                    list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), Long.valueOf(strArray[0]), "repayMemtSignle", null);
                } else if (strArray[1].equals("Repaymented")) {
                    list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), Long.valueOf(strArray[0]), "repayMemtSignle", null);
                } else if (strArray[1].equals("Financing")) {
                    list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), Long.valueOf(strArray[0]), "repayMemtSignle", null);
                } else if (strArray[1].equals("Finperson")) {
                    if (strArray.length > 2) {
                        if (strArray[2] != null) {
                            list = plMmOrderAssignInterestService.findLoanRepayMemtList1(Long.valueOf(strArray[0]), mem.getLoginname());
                        } else {
                            list = plMmOrderAssignInterestService.findLoanRepayMemtList(Long.valueOf(strArray[0]), mem.getLoginname());
                        }
                    } else {
                        list = plMmOrderAssignInterestService.findLoanRepayMemtList(Long.valueOf(strArray[0]), mem.getLoginname());
                    }
                } else if (strArray[1].equals("guaranteeFin")) {//担保公司查看回款计划
                    list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), null, Long.valueOf(strArray[0]), "repayMemtSignle", null);
                } else {
                    list = bpFundIntentService.findLoanRepayMemtList(mem.getId(), mem.getLoginname(), Long.valueOf(strArray[0]), "repayMemtSignle", null);
                }

                String isMobile = this.getRequest().getParameter("isMobile");
                if (null != isMobile && isMobile.endsWith("1")) {
                    for (int i = 0; i < list.size(); i++) {
                        BigDecimal superman = list.get(i).getIntenttotal();
                        BigDecimal bighear = list.get(i).getCompensationMoney();
                        list.get(i).setIntenttotal(superman.add(bighear));
                    }

                    StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
                            .append(list.size()).append(",\"result\":");
                    JSONSerializer json = JsonUtil.getJSONSerializer();
                    json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{"factDate"});
                    buff.append(json.serialize(list));
                    buff.append("}");
                    jsonString = buff.toString();
                    return SUCCESS;
                }
                this.getRequest().setAttribute("LoanList", list);
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOANPAYMENTPLAN).getTemplateFilePath());
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "freemarker";

    }

    /**
     * 双乾放款授权
     */
    public String autoLoan() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        //用户交易资格查询(检查用户是否具备交易资格)
        Object[] usercondition = bpCustMemberService.checkUserDealCondition(mem);
        try {
            if ((Boolean) usercondition[0]) {//验证是否具备交易资格
                mem = bpCustMemberService.get(mem.getId());
                mem = obSystemAccountService.getAccountSumMoney(mem);
                String actionStatus = this.getRequest().getParameter("actionStatus");
                CommonResponse cr = new CommonResponse();
                //授权与取消授权
                String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
                CommonRequst cq = new CommonRequst();
                cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
                cq.setRequsetNo(requestNo);//请求流水号
                cq.setBussinessType(ThirdPayConstants.BT_AUTOLOAN);//业务类型
                cq.setTransferName(ThirdPayConstants.TN_AUTOLOAN);//业务名称
                if (mem.getSecondAudit() != null && mem.getSecondAudit().equals("1")) {
                    cq.setAuthorizeTypeOpen("2");//开启授权类型
                } else {
                    cq.setAuthorizeTypeOpen("2,3");//开启授权类型
                }
                //cq.setAuthorizeTypeClose("2,3");
                if (mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)) {//个人用户
                    cq.setAccountType(0);
                } else {//企业用户
                    cq.setAccountType(1);
                }
                cr = ThirdPayInterfaceUtil.thirdCommon(cq);
                if (CommonResponse.RESPONSECODE_SUCCESS.equals(cr.getResponsecode())) {
                    webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(), "", "", "", "", "");
                }
            } else {
                forwardPage = usercondition[2].toString();
                webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(), "", "", "", "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            webMsgInstance("0", Constants.CODE_FAILED, "授权错误，请联系管理员", "", "", "", "", "");
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 平台数据披露
     *
     * @return
     */
    public String toPublish() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //平台披露数据读取
        PlatDataPublish platDataPublish = null;
        List<PlatDataPublish> list = platDataPublishService.getAll();
        if (null != list && list.size() > 0) {
            platDataPublish = list.get(0);
            this.getSession().setAttribute("platDataPublish", platDataPublish);
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PLATFORMPUBLISH).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 通过担保机构ID查询在保项目or结清项目
     *
     * @return
     */
    public String getBidCountGuarantee() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BpCustRelation r = bpCustRelationService.getCustOffine(mem.getId(), "b_guarantee");
        String state = this.getRequest().getParameter("state");
        String states = "";
        if (null != state && "7".equals(state)) {
            states = "(7)";
        }
        if (null != state && "10".equals(state)) {
            states = "(10)";
        }
        Long count;
        if (r != null) {
            count = plBidPlanService.findBidPlanCount(r.getOfflineCusId(), states);
        } else {
            count = Long.valueOf("0");
        }

        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询代偿项目 or 追回项目
     *
     * @return
     */
    public String getBidCountCompensatory() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String backStatus = this.getRequest().getParameter("backStatus");
        Long count = plBidCompensatoryService.findCountByComp2PId(mem.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf(backStatus));
        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询期担保的逾期标的数量
     *
     * @return
     */
    public String getOverdueBidCountGuarantee() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BpCustRelation r = bpCustRelationService.getCustOffine(mem.getId(), "b_guarantee");
        Long count;
        if (r != null) {

            count = plBidPlanService.findOverdueBidPlanCount(r.getOfflineCusId());
        } else {
            count = Long.valueOf("0");
        }
        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询担保公司应收代偿金额总和
     *
     * @return
     */
    public String getCompensatoryMoney() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = plBidCompensatoryService.findCompensatoryMoneytByComp2PId(mem.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf("0"));
        if (money == null) {
            money = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";

        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询担保公司应收代偿金额总和
     *
     * @return
     */
    public String getPunishMoney() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = plBidCompensatoryService.findPunishMoneyByComp2PId(mem.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf("0"));
        if (money == null) {
            money = new BigDecimal(0);
        }

        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询在保项目的总金额
     *
     * @return
     */
    public String getBidMoneyGuarantee() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BpCustRelation r = bpCustRelationService.getCustOffine(mem.getId(), "b_guarantee");
        String state = this.getRequest().getParameter("state");
        String states = "";
        if (null != state && "7".equals(state)) {
            states = "(7)";
        }
        if (null != state && "10".equals(state)) {
            states = "(10)";
        }
        BigDecimal money = new BigDecimal(0);
        if (r != null) {
            money = plBidPlanService.findBidPlanMoney(r.getOfflineCusId(), states);
        }
        if (money == null) {
            money = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 理财计划列表(根据个人理财顾问)
     * state 1 招标中
     */
    public void manageFinancialList(String state) {
        try {

            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                    MyUserSession.MEMBEER_SESSION);
            if (mem != null) {
                QueryFilter filter = new QueryFilter(getRequest());
                filter.addFilter("Q_moneyReceiver_S_EQ", mem.getLoginname());

                filter.getPagingBean().setStart(0);
                filter.getPagingBean().setPageSize(5);
                filter.addFilterIn("Q_state_SN_IN", Arrays.asList(Integer.valueOf(state)));

                List<PlManageMoneyPlan> list = plManageMoneyPlanService.getAll(filter);
                this.getRequest().setAttribute("mflist" + state, list);
            } else {
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * 通过个人理财顾问的登录名查询标的统计数据
     *
     * @return
     */
    public String getBidCountFinancial() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Long count = null;
        String state = this.getRequest().getParameter("state");
        String isPresale = this.getRequest().getParameter("isPresale");
        if (null != state && !"".equals(state)) {
            count = plMmOrderAssignInterestService.getBidCountFinancial(mem.getLoginname(), Integer.valueOf(state), isPresale);
        }
        if (count == null) {
            count = new Long(0);
        }
        jsonString = "{\"result\":\"" + count + "\"}";
        return SUCCESS;
    }

    /**
     * 通过担保机构ID查询在保项目的总金额
     *
     * @return
     */
    public String getBidMoneyFinancial() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String fundType = this.getRequest().getParameter("fundType");
        BigDecimal money = plMmOrderAssignInterestService.findMoneyByType(mem.getLoginname(), fundType);
        if (money == null) {
            money = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 个人中心  我的债权查询
     */
    public String loadMySaleTemplate() {
        if (isLogin()) {
            getBalance();
            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            bpCustMember = mem;
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYSALEHOME).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 新版UI个人中心我的债权的统计数据
     *
     * @return
     */
    public String loadMySaleData() {
        if (isLogin()) {
            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            bpCustMember = mem;
            jsonString = bpCustMemberService.queryAllLc(mem.getId(), mem.getLoginname());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }


    /**
     * 新版UI 个人中心我的债权 记录查询
     */
    public String showMyDebt() {
        if (isLogin()) {
            BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            bpCustMember = mem;
            jsonString = bpCustMemberService.queryAllDebt(mem.getLoginname(), getRequest());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }


    /**
     * 进入理财计划债权人个人中心
     *
     * @return
     * @throws IOException
     */
    public String getFinancial() {
        this.getSession().setAttribute("highlight", 1);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

        String mobile = this.getRequest().getParameter("mobile");
        if ("mobile".equals(mobile) && mem == null) {
            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
            return "freemarker";
        }

        if (mem != null) {
            bpCustMember = bpCustMemberService.get(mem.getId());
            bpCustMemberService.queryThirdPayCustomerInfo(bpCustMember);
            BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
            applyUser.setUserID(mem.getId());
            listApplyUser = financeApplyUserService.getApplyUser(applyUser);
            if (null == bpCustMember) {
                this.getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                        DynamicConfig.LOGIN).getTemplateFilePath());
                return "freemarker";
            }
            userloginlogs = userloginlogsService.getLoginlogs(mem.getLoginname());
            QueryFilter filter = new QueryFilter();
            filter.addFilter("Q_customerId_L_EQ", bpCustMember.getId().toString());
            List<WebBankcard> listw = webBankcardservice.getAll(filter);
            if (listw.size() > 0) {
                this.getRequest().setAttribute("isbind", 1);
            } else {
                this.getRequest().setAttribute("isbind", 2);
            }
            getEmail(bpCustMember);

            Pager pager = new Pager();
            pager.setPageSize(5);
            commoon(bpCustMember);
            total(bpCustMember);

            //自动投标
            bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
            this.getSession().setAttribute("bidAuto", bidAuto);
            //会员等级
            String levelMark = "普通会员";
            if (null != bpCustMember.getScore() && !"".equals(bpCustMember.getScore())) {
                levelMark = bpCustMemberService.getLevelMark(bpCustMember.getScore());
            }
            this.getSession().setAttribute("levelMark", levelMark);

            if (typ == 0) {
                manageFinancialList("1");
                // 个人理财顾问账户登录，主页
                mem.setPerCompanyType(Short.valueOf("1"));
                this.getSession().setAttribute("loginType", "person");//保存登录类型
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYHOMEFINANCIAL).getTemplateFilePath());
            }
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        if (mobile != null) {
            //资金流水记录
            ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), ObSystemAccount.type0);
            if (account != null) {
                Long accountId = account.getId();
                listcount = obAccountDealInfoService.getDealInfoByPersionId(accountId);
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILECENTER).getTemplateFilePath());
        }
        return "freemarker";
    }


    public String mobileaccount() {
        //投标冻结金额
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
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

        StringBuffer sb = new StringBuffer();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        sb.append("{\"success\":true,\"money\":");
        sb.append(gson.toJson(money));
        sb.append(",\"money1\":");
        sb.append(gson.toJson(money101));

        sb.append(",\"money3\":");
        sb.append(gson.toJson(money3));

        sb.append(",\"money4\":");
        sb.append(gson.toJson(money4));

        sb.append(",\"money5\":");
        sb.append(gson.toJson(money5));

        sb.append(",\"money6\":");
        sb.append(gson.toJson(money6));

        sb.append(",\"money7\":");
        sb.append(gson.toJson(money7));

        sb.append(",\"money8\":");
        sb.append(gson.toJson(money8));

        sb.append(",\"money9\":");
        sb.append(gson.toJson(money9));

        sb.append(",\"money10\":");
        sb.append(gson.toJson(money10));

        sb.append(",\"money11\":");
        sb.append(gson.toJson(money11));

        sb.append(",\"money12\":");
        sb.append(gson.toJson(money12));

        sb.append(",\"count\":");
        sb.append(gson.toJson(count));

        sb.append(",\"count1\":");
        sb.append(gson.toJson(count1));

        sb.append(",\"count2\":");
        sb.append(gson.toJson(count2));

        sb.append(",\"count3\":");
        sb.append(gson.toJson(count3));

        sb.append(",\"count4\":");
        sb.append(gson.toJson(count4));

        sb.append(",\"count5\":");
        sb.append(gson.toJson(count5));

        sb.append(",\"moneyMB\":");
        sb.append(gson.toJson(moneyMB));

        sb.append(",\"rate\":\"" + rate + "%\"");
//			sb.append(gson.toJson(rate));
        sb.append("}");
        setJsonString(sb.toString());
        return SUCCESS;
    }










    public String mobilegetlistApplyUser() {
        String userid = this.getRequest().getParameter("userid");
        long useridid = Long.parseLong(userid);
        BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
        applyUser.setUserID(useridid);
        listApplyUser = financeApplyUserService.getApplyUser(applyUser);
        StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
                .append(listApplyUser.size()).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
        json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{});
        buff.append(json.serialize(listApplyUser));
        buff.append("}");
        jsonString = buff.toString();
        return SUCCESS;

    }


    /**
     * 查询标的的相关数据
     *
     * @return
     */
    public String queryBidInfo() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            BpPersonCenterData data = plBidInfoService.queryAllBid(Long.valueOf(mem.getId()));

            //增加募集期利息
            BigDecimal raiseinterest =  bpFundIntentService.getByRaiseinterest(mem.getId());
            BigDecimal allBenefit1 = data.getAllBenefit();
            BigDecimal allBenefit = allBenefit1.add(raiseinterest);
            data.setAllBenefit(allBenefit);

            StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
            JSONSerializer json = JsonUtil.getJSONSerializer();
            json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{});
            buff1.append(json.serialize(data));
            buff1.append("}");
            System.out.println(buff1.toString());
            jsonString = buff1.toString();
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }


    //积分兑换，认证材料，我的担保，我的积分，我的邀请，第三方支付

    /**
     * 积分兑换
     */
    public String exchangeIntegral() {
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EXCHANGEINTEGRAL).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 认证材料
     */
    public String checkMaterial() {
        if (isLogin()) {
            getBalance();
            bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CHECKMATERIAL).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 查询认证材料的详情
     */
    public String getMaterial() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //查询基础材料
        QueryFilter filter = new QueryFilter(this.getRequest());
        filter.addFilter("Q_operationType_S_EQ", "person");//默认查询出个人的
        basisMaterialList = p2pLoanBasisMaterialService.getAll(filter);
        //判断材料是否上传
        for (P2pLoanBasisMaterial material : basisMaterialList) {
            QueryFilter filter2 = new QueryFilter(getRequest());
            filter2.addFilter("Q_materialId_L_EQ", material.getMaterialId());
            filter2.addFilter("Q_userID_L_EQ", 604);//bpCustMember.getId()
            List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter2);
            if (applys.size() > 0) {
                material.setWebFinanceApplyUploadsList(applys);
                material.setImgUrl(applys.get(0).getFiles());
                material.setStatus(applys.get(0).getStatus());
                material.setRejectReason(applys.get(0).getRejectReason());
            }
        }
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
        json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{});
        buff1.append(json.deepSerialize(basisMaterialList));
        buff1.append("}");
        System.out.println("输出的字符串是" + buff1.toString());
        jsonString = buff1.toString();
        return SUCCESS;
    }

    /**
     * 我的担保
     */
    public String myGuarantee() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (bpCustMember != null) {
            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                    this.getRequest().setAttribute("memberId", bpCustMember.getId());
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYGUARANTEE).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }


    /**
     * 加载我的担保的数据
     */
    public String loadMyGuauatee() {
        Integer type = Integer.valueOf(this.getRequest().getParameter("type").toString());
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (type != null) {
            switch (type) {
                case 1:   //逾期项目
                    List<ShowManageMoney> guaranteeList = bpFundIntentService.listByGuarantorsId(bpCustMember.getId(), this.getRequest());//需要代偿的项目
                    Integer guaranteeListNums = bpFundIntentService.listByGuarantorsId1(bpCustMember.getId(), this.getRequest()).size();
                    jsonString = CreateJson(guaranteeList, guaranteeListNums);
                    break;
                case 2:   //代偿项目
                    List<PlBidCompensatory> compensatoryList = plBidCompensatoryService.findListByComp2PId(bpCustMember.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf("0"), null, this.getRequest());
                    Integer compensatoryListNums = plBidCompensatoryService.findListByComp2PId1(bpCustMember.getId(), PlBidCompensatory.TYPE_GURANEE, Integer.valueOf("0"), null, this.getRequest()).size();

                    jsonString = CreateJson(compensatoryList, compensatoryListNums);
                    break;
                case 3:   //在保项目
                    List<PlBidPlan> zlist = new ArrayList();
                    Integer Nums = 0;
                    BpCustRelation r = bpCustRelationService.getCustOffine(bpCustMember.getId(), "b_guarantee");
                    if (r != null) {
                        zlist = plBidPlanService.findBidPlan(r.getOfflineCusId(), "(7)", this.getRequest());
                        Nums = plBidPlanService.findBidPlan1(r.getOfflineCusId(), "(7)", this.getRequest()).size();
                    }
                    jsonString = CreateJson(zlist, Nums);
                    break;
                case 4:  //结清项目
                    BpCustRelation r1 = bpCustRelationService.getCustOffine(bpCustMember.getId(), "b_guarantee");
                    List<PlBidPlan> jlist = new ArrayList();
                    Integer Num = 0;
                    if (r1 != null) {
                        jlist = plBidPlanService.findBidPlan(r1.getOfflineCusId(), "(10)", this.getRequest());
                        Num = plBidPlanService.findBidPlan1(r1.getOfflineCusId(), "(10)", this.getRequest()).size();
                    }
                    jsonString = CreateJson(jlist, Num);
                    break;
                default:   //默认逾期项目
                    List<ShowManageMoney> guaranteeList1 = bpFundIntentService.listByGuarantorsId(bpCustMember.getId(), this.getRequest());//需要代偿的项目
                    Integer guaranteeListNum1 = bpFundIntentService.listByGuarantorsId1(bpCustMember.getId(), this.getRequest()).size();
                    jsonString = CreateJson(guaranteeList1, guaranteeListNum1);
            }
        }
        return SUCCESS;
    }


    /**
     * 我的积分
     */
    public String myIntegral() {
        if (isLogin()) {
            getBalance();
            bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYINTEGRAL).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    /**
     * 我的优惠券
     */
    public String myCoupons() {
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        QueryFilter filter1 = new QueryFilter();
        filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
        List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
        if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
            if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                this.getRequest().setAttribute("isGarantee", "1");
            }
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYCOUPONS).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 加载我的邀请模板
     */
    public String myInvite() {
        if (isLogin()) {
            getBalance();
            bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
            QueryFilter filter1 = new QueryFilter();
            filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
            List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
            if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                    this.getRequest().setAttribute("isGarantee", "1");
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYINVITE).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }


    /**
     * 获取我的邀请的数据
     *
     * @return
     */
    public String loadMyInvest() {
        if (isLogin()) {
            jsonString = bpCustMemberService.myInvest(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)), getRequest());

        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;

    }

    /**
     * 我的邀请的统计数据
     */

    public String loadInvestData() {
        if (isLogin()) {
            jsonString = bpCustMemberService.myInvestData(((BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION)));
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }

    /**
     * 第三方支付
     */
    public String myThirdPay() {
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYTHIRDPAY).getTemplateFilePath());
        return "freemarker";
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

    //获取当前用户的余额
    public void getBalance() {
        BpCustMember member = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        commoon(member);
        bpCustMember = member;
    }


    public String obSystemDeal() {
        String isMobile = this.getRequest().getParameter("isMobile");
        if (null != isMobile && isMobile.endsWith("1")) {
            String page = this.getRequest().getParameter("page");
            String limit = this.getRequest().getParameter("limit");
            pager = new Pager();
            pager.setPageSize(Integer.valueOf(limit));
            pager.setPageNumber(Integer.valueOf(page));
        }
        this.getSession().setAttribute("highlight", 6);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String from = this.getRequest().getParameter("from");
        String to = this.getRequest().getParameter("to");
        if (pager == null) {
            pager = new Pager();
            pager.setPageSize(5);
        }
        String startDate = getRequest().getParameter("startDate");
        if (null != startDate && !"".equals(startDate)) {
            searchMap.put("startDate", startDate);
        }
        String endDate = getRequest().getParameter("endDate");
        if (null != endDate && !"".equals(endDate)) {
            searchMap.put("endDate", endDate);
        }
        String isAccountSuccess = getRequest().getParameter("isAccountSuccess");
        if (null != isAccountSuccess && !"".equals(isAccountSuccess)) {
            searchMap.put("isAccountSuccess", isAccountSuccess);
        }
        commoon(mem);
        BpCustMember oldMember = bpCustMemberService.get(mem.getId());
        bpCustMember.setScore(oldMember.getScore());
        ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), ObSystemAccount.type0);
        if (account != null) {
            List<ObAccountDealInfo> list = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
            List<ObAccountDealInfo> listcount = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), null, null);
            pager.setTotalCount(listcount != null ? listcount.size() : 0);
            if (list != null && list.size() > 0) {
                for (ObAccountDealInfo temp : list) {
                    if (temp.getDealRecordStatus().compareTo(Short.valueOf("1")) == 0) {
                        temp.setMsg("等待支付");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("2")) == 0) {
                        temp.setMsg("交易成功");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("3")) == 0) {
                        temp.setMsg("交易失败");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("4")) == 0) {
                        temp.setMsg("取现审核");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("5")) == 0) {
                        temp.setMsg("取现办理");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("6")) == 0) {
                        temp.setMsg("异常数据");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() == null) {
                        temp.setMsg("资金冻结");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() != null) {
                        temp.setMsg("取现审核");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("8")) == 0) {
                        temp.setMsg("资金解冻");
                    } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("9")) == 0) {
                        temp.setMsg("银行处理中");
                    } else {
                        temp.setMsg("");
                    }
                }
            }
            pager.setList(list);
            //查询交易类型
            List<ObSystemaccountSetting> obSystemaccountSetting = obSystemAccountService.findObSystemaccountSetting();
            this.getRequest().setAttribute("obSystemaccountSetting", obSystemaccountSetting);
            if (startDate != null && !startDate.equals("")) {
                this.getRequest().setAttribute("startDate", startDate);
            }
            if (endDate != null && !endDate.equals("")) {
                this.getRequest().setAttribute("endDate", endDate);
            }
            String transferType = getRequest().getParameter("transferType");
            if (transferType != null && !transferType.equals("")) {
                this.getRequest().setAttribute("transferType1", transferType);
            }
            if (isAccountSuccess != null && !isAccountSuccess.equals("")) {
                this.getRequest().setAttribute("isAccountSuccess1", isAccountSuccess);
            }

        }
        if (null != isMobile && isMobile.endsWith("1")) {
            StringBuffer buff = new StringBuffer("{\"success\":false,\"result\":1,\"msg\":\"数据校验失败,请检查是否认证通过或开通第三方支付\"}");
            jsonString = buff.toString();
            return SUCCESS;
        }
        return SUCCESS;
    }


    //未读消息
    public String message() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            bpPersonCenterData = oaNewsMessageService.getMessage(Long.valueOf(mem.getId()));
            this.getRequest().setAttribute("notRead", bpPersonCenterData.notReadNums);
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }

    //优惠券
    public String coupon() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            bpPersonCenterData = bpCouponsService.getCoupon(Long.valueOf(mem.getId()));
            this.getRequest().setAttribute("coupons", bpPersonCenterData.coupons);
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        return SUCCESS;
    }


    public BpPersonCenterData getBpPersonCenterData() {
        return bpPersonCenterData;
    }

    public void setBpPersonCenterData(BpPersonCenterData bpPersonCenterData) {
        this.bpPersonCenterData = bpPersonCenterData;
    }


    /**
     * 通过担保机构ID查询其待代偿金额总和
     *
     * @return
     */
    public String getWaitCompensatoryMoney() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal money = bpFundIntentService.findWaitCompensatoryMoney(mem.getId());
        if (money == null) {
            money = new BigDecimal(0);
        }
        jsonString = "{\"result\":\"" + money.setScale(2, BigDecimal.ROUND_HALF_UP) + "\"}";
        return SUCCESS;
    }

    /**
     * 生成json的方法
     */

    public String CreateJson(Object obj, Integer Nums) {
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(Nums).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
        json.transform(new DateTransformer("yyyy-MM-dd"), new String[]{});
        buff1.append(json.serialize(obj));
        buff1.append("}");
        jsonString = buff1.toString();
        return buff1.toString();
    }


    /**
     * 上传客户头像的方法
     */
    public String uploadAvatar() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String filename = "";
        String extendname = "";
        String filepath = "";
        StringBuffer rootPath = new StringBuffer("");
        rootPath.append(ROOT);
        rootPath.append(mark);
        rootPath.append("\\");
        rootPath.append(DateUtil.getYearAndMonth());
        rootPath.append("\\");
        String tempSrc = getSession().getServletContext().getRealPath("/") + rootPath.toString();
        tempSrc = tempSrc.replaceAll("\\\\", "/");
        FileUtil.mkDirectory(tempSrc);//在服务器上创建路径
        filename = getPath();
        extendname = FileUtil.getExtention(filename);
        //得到当前时间，精确到毫秒

        boolean flag = checkFile(extendname);
        if (flag) {
            String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            filename = nowTime + extendname;
            filepath = rootPath.toString() + filename;
            filepath = filepath.replaceAll("\\\\", "/");
            String webPath = uploadPath + "/" + mark + "/" + DateUtil.getYearAndMonth() + "/" + filename;
            setSuccess(FileUtil.upload(filename, tempSrc, getAtvatar_image()));
			/*String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		filename = mark + extendname;
		filepath = rootPath.toString() + filename;
		filepath = filepath.replaceAll("\\\\","/");
		String webPath = uploadPath+"/"+mark+"/"+nowTime+"/";
		setSuccess(FileUtil.upload(filename,webPath,getAtvatar_image()));*/
            QueryFilter filter = new QueryFilter();
            filter.addFilter("Q_remark_S_EQ", mem.getId());
            List<FileForm> list = fileFormService.getAll(filter);
            if (list.size() > 0) {
                list.get(0).setMark(webPath);
                fileFormService.merge(list.get(0));
            } else {
                FileForm file = new FileForm();
                file.setMark(webPath);
                file.setRemark(String.valueOf(mem.getId()));
                file.setFilename(filename);
                file.setExtendname(extendname);
                //file.setFilesize(filesize);
                file.setCreatetime(new Date());
                fileFormService.merge(file);
            }
            webMsgInstance("0", Constants.CODE_SUCCESS, "头像更换成功", "", "", "", "", "");
        } else {
            webMsgInstance("0", Constants.CODE_FAILED, "文件格式不正确！！", "", "", "", "", "");
        }

		/*sb.append("{\"message\":");
		sb.append(gson.toJson(countent));
		sb.append(",\"state\":0");
		sb.append("}");
		message = sb.toString();*/
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.MESSAGE).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 根据后缀名判断文件是否为图片
     *
     * @return
     */
    private boolean checkFile(String extendname) {
        boolean flag = true;
        if (StringUtils.isNotEmpty(extendname)) {
            if (extendname.contains(".")) {
                extendname = extendname.replace(".", "");
            }
            extendname = extendname.toUpperCase();
            if (!extendname.matches("^[(JPG)|(JPEG)|(PNG)|(GIF)]+$")) {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public String queryThirdPay() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem != null) {
            if (mem.getThirdPayFlagId() != null && !"".equals(mem.getThirdPayFlagId())) {//托管第三方查询出来第三方账户信息
                this.getRequest().setAttribute("bpCustMember", mem);
                Map<String, String> map = new HashMap<String, String>();
                map = bpCustMemberService.queryThirdPayCustomerInfo(mem);
                QueryFilter filter1 = new QueryFilter();
                filter1.addFilter("Q_p2pCustId_L_EQ", mem.getId());
                List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
                if (relationlist.size() > 0 && !"".equals(relationlist.get(0).getOfflineCustType())) {
                    if (relationlist.get(0).getOfflineCustType().equals("b_guarantee")) {
                        this.getRequest().setAttribute("isGarantee", "1");
                    }
                }
            }
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.THIRDINFO).getTemplateFilePath());
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
        }
        return "freemarker";
    }

    public void test() {
        //判断是否已经全部还款完毕   将状态设置为10
        QueryFilter filter = new QueryFilter();
        filter.addFilter("Q_isCheck_SN_EQ", (short) 0);
        filter.addFilter("Q_isValid_SN_EQ", (short) 0);
        filter.addFilter("Q_mmplanId_L_EQ", 3091);
        PlManageMoneyPlan plan = plManageMoneyPlanService.get(Long.valueOf(3091));
        List<PlMmOrderAssignInterest> plList1 = plMmOrderAssignInterestService.getAll(filter);
        if (plList1.size() > 0) {
            Boolean boo = true;
            for (PlMmOrderAssignInterest interest : plList1) {
                if (interest.getFactDate() == null) {
                    boo = false;
                }
            }
            if (boo) {
                plan.setState(Integer.valueOf("10"));
                plManageMoneyPlanService.merge(plan);
            }
        }
    }

    //判断用户是否登录
    public String checkSession() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        StringBuffer sb = new StringBuffer();
        if (mem != null) {
            sb.append("{\"success\":true,\"flag\":\"yes\"}");
        } else {
            sb.append("{\"success\":true,\"flag\":\"no\"}");
        }
        setJsonString(sb.toString());
        return SUCCESS;
    }

    /**
     * H5查询可提现余额单独方法
     *
     * XF
     */
    public String getTrueMoney(){
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //通过获取用户第三方账户信息来更新银行列表数据
        Map<String, String> queryThirdPayCustomerInfo = bpCustMemberService.queryThirdPayCustomerInfo(cust);
        StringBuffer sb = new StringBuffer();
        if (queryThirdPayCustomerInfo.containsKey("withdrawBalance")) {
            sb.append("{\"success\":true,\"trueMoney\":\""+queryThirdPayCustomerInfo.get("withdrawBalance")+"\"}");
        }else {
            sb.append("{\"success\":true,\"trueMoney\":\"0.00\"}");
        }
        setJsonString(sb.toString());
        return  SUCCESS;
    }

    /**
     *PC邀请人详情页面
     *
     * @auther: XinFang
     * @date: 2018/6/4 14:48
     */
    public String myInviteInfo(){

        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String bidId = this.getRequest().getParameter("bidId");
        List<Investproject> list = bpCustMemberService.getMyInviteInfo(bidId);

        this.getRequest().setAttribute("InvestprojectList",list);
        long totalMoney = 0 ;
        for (Investproject investproject : list) {
            totalMoney = totalMoney + investproject.getUserMoney().longValue();
        }
        this.getRequest().setAttribute("totalMoney",totalMoney);
        this.getRequest().setAttribute("bpCustMember",cust);


        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.MYINVITEINFO).getTemplateFilePath());
        return "freemarker";
    }

    /**
     * 修改手机号码之前的验证
     *安卓
     * @return
     */
    /*public String verifPhoneMobile() {
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
                MyUserSession.MEMBEER_SESSION);
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        //StringBuffer sb = new StringBuffer();
        String phonecode = (String) this.getSession().getAttribute(
                MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        //往修改页面跳转
        this.getRequest().setAttribute("toAction", "updatePhone");
        if (mem == null) {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            model.setMsg("请先登录");
            setJsonString(model.toJSON());
            return SUCCESS;
            //sb.append("{\"success\":false,\"remark\":");
            //sb.append(gson.toJson("请先登陆"));
        } else {
            bpCustMember = bpCustMemberService.get(mem.getId());
            if (phonecode != null && !"".equals(phonecode)) {
                if (phonecode.equals(bpCustMember.getTelphone() + verify_sms)) {
                    model.setCode(MobileErrorCode.SUCCESS);
                    model.setMsg("验证成功");
                    setJsonString(model.toJSON());
                    return SUCCESS;
                    //sb.append("{\"success\":true,\"remark\":");
                    //sb.append(gson.toJson("验证成功"));
                } else {
                    model.setCode(MobileErrorCode.SERVICE_ERROR);
                    model.setMsg("手机验证码输入错误");
                    setJsonString(model.toJSON());
                    return SUCCESS;
                    //sb.append("{\"success\":false,\"remark\":");
                    //sb.append(gson.toJson("手机验证码输入错误"));
                }
            }else {
                model.setCode(MobileErrorCode.SERVICE_ERROR);
                model.setMsg("验证码已失效，请重新获取");
                setJsonString(model.toJSON());
                return SUCCESS;
                //sb.append("{\"success\":false,\"remark\":");
                //sb.append(gson.toJson("验证码已失效，请重新获取"));
            }
        }
    }*/

    /*public String isMobilePhone() {
        // 将数据转成JSON格式
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        //StringBuffer sb = new StringBuffer();
        MobileDataResultModel model = new MobileDataResultModel();
        String telphone = this.getRequest().getParameter("telphone");
        String[] codemeg = new String[2];
        codemeg = validation.phoneValidation(telphone);
        if (codemeg[0].equals(Constants.CODE_SUCCESS)) {
            BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
            if (member != null) {
                model.setMsg("手机号码已存在");
                model.setCode(MobileErrorCode.SERVICE_ERROR);
                setJsonString(model.toJSON());
                return SUCCESS;
//                sb.append("{\"success\":true,\"errMsg\":");
//                sb.append(gson.toJson("手机号码已存在"));
//                sb.append(",\"result\":1");
//                sb.append("}");
            } else {
                model.setMsg("号码可以使用");
                model.setCode(MobileErrorCode.SUCCESS);
                setJsonString(model.toJSON());
                return SUCCESS;
//                sb.append("{\"success\":false,\"errMsg\":");
//                sb.append(gson.toJson("号码可以使用"));
//                sb.append(",\"result\":0");
//                sb.append("}");
            }
        } else {
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            model.setMsg(codemeg[1]);
            setJsonString(model.toJSON());
            return SUCCESS;
//            sb.append("{\"success\":false,\"errMsg\":");
//            sb.append(gson.toJson(codemeg[1]));
//            sb.append(",\"result\":1");
//            sb.append("}");
        }
        //setJsonString(sb.toString());
        //return SUCCESS;

    }*/

    /**
     * 修改手机号
     * 手机端
     */
    /*public String updatePhoneMobile() {
        MobileDataResultModel model = new MobileDataResultModel();
        String backpath = this.getRequest().getParameter("backpath");
        String verify_sms = this.getRequest().getParameter("verify_sms");
        String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        // 将数据转成JSON格式
        //StringBuffer sb = new StringBuffer("{\"success\":");
        if (mem==null){
            model.setCode(MobileErrorCode.FAILED);
            model.setMsg("登陆信息失效，请先登陆");
//            sb.append("false,\"remark\":");
//            sb.append(gson.toJson("登陆信息失效，请先登陆"));
        }else {
            if (StringUtils.isNotEmpty(phonecode)) {//验证码是否存在
                BpCustMember member = bpCustMemberService.isExistTelphone(telphone);
                if (member == null) {
                    mem = bpCustMemberService.get(mem.getId());
                    if(StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard())){
                        //往session中放入验证码时拼接了手机号码，所以判断的时候也要拼接手机号，
                        //防止接收验证码的手机号和进数据库的验证码不一致
                        if (phonecode.equals(telphone + verify_sms)) {
                            this.getSession().setAttribute("backpath",backpath);
                            CommonRequst cq = new CommonRequst();
                            cq.setIsMobile("1");//手机端操作
                            String orderNum = ContextUtil.createRuestNumber();//生成第三需要的流水号
                            cq.setBussinessType(ThirdPayConstants.BT_UPDATEPHONE);
                            cq.setTransferName(ThirdPayConstants.TN_UPDATEPHONE);
                            cq.setRequsetNo(orderNum);//流水号
                            cq.setThirdPayConfigId(mem.getThirdPayFlagId());
                            cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
                            cq.setTelephone(telphone);//手机号
                            CommonResponse cr = ThirdPayInterfaceUtil.thirdCommon(cq);

                            if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                                webMsgInstance("0", Constants.CODE_SUCCESS,"修改手机号申请成功", "", "", "", "", "");
                                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                                return "freemarker";
                            } else {
                                webMsgInstance("0", Constants.CODE_SUCCESS,cr.getResponseMsg(), "", "", "", "", "");
                                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                                return "freemarker";
                            }
                        } else {
                            //设置 返回提示消息
                            webMsgInstance("0", Constants.CODE_FAILED, "验证码错误！", "", "", "", "", "");
                            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                            return "freemarker";
                        }
                    }else{
                        if (phonecode.equals(telphone + verify_sms)) {
                            mem.setTelphone(telphone);//手机号
                            bpCustMemberService.merge(mem);
                            this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION,mem);
                            sb.append("true,\"remark\":");
                            sb.append(gson.toJson("修改手机号成功"));
                        } else {
                            //设置 返回提示消息
                            sb.append("false,\"remark\":");
                            sb.append(gson.toJson("验证码错误！"));
                        }
                    }
                }else {
                    if (StringUtils.isNotEmpty(backpath)){
                        webMsgInstance("0", Constants.CODE_FAILED, "手机号码已存在！", "", "", "", "", "");
                        this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                        return "freemarker";
                    }else {
                        sb.append("false,\"remark\":");
                        sb.append(gson.toJson("手机号码已存在！"));
                    }
                }
            }else {
                //设置 返回提示消息
                if (StringUtils.isNotEmpty(backpath)){
                    webMsgInstance("0", Constants.CODE_FAILED, "验证码已经超时！", "", "", "", "", "");
                    this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
                    return "freemarker";
                }else {
                    sb.append("false,\"remark\":");
                    sb.append(gson.toJson("验证码已经超时！"));
                }
            }
        }
//        sb.append("}");
        setJsonString(model.toString());
        return SUCCESS;
    }*/

    //app校验session中是否有用户信息
    public String isSessinoValue() {
        MobileDataResultModel model = new MobileDataResultModel();
        String isApp = this.getRequest().getParameter("isApp");
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String backpath = (String) this.getSession().getAttribute("backpath");
        String recommand = (String) this.getSession().getAttribute("recommand");
//        StringBuffer sb = new StringBuffer();
//        Gson gsons = new GsonBuilder().create();
        if("1".equals(isApp)){
            if (null == cust) {
                model.addDataContent("backpath", backpath);
                model.addDataContent("recommand", recommand);
                //setJsonString(model.toJSON());
            } else {
                cust = bpCustMemberService.get(cust.getId());
                BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());
                if (ret != null) {
                    cust.setTotalMoney(ret[1]);
                    cust.setAvailableInvestMoney(ret[3]);
                }
                model.addDataContent("cust", cust);
                model.addDataContent("backpath", backpath);
            }
            setJsonString(model.toJSON());
            this.getSession().removeAttribute("backpath");
            this.getSession().removeAttribute("recommand");
            this.getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);
            return SUCCESS;
        }
        return "";
    }

    /**
     *添加修改企业信息方法
     *
     * @auther: XinFang
     * @date: 2018/6/8 15:41
     */
    public  String toChangeEnterprise(){
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

        String type = this.getRequest().getParameter("type");

        this.getRequest().setAttribute("enterprise",cust);
        this.getRequest().setAttribute("type",type);


        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.CHANGEENTERPRISENAME).getTemplateFilePath());
        return  "freemarker";
    }

    /**
     *修改企业信息方法接口
     *
     * @auther: XinFang
     * @date: 2018/6/8 16:19
     */

    public String changeEnterprise() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (mem==null){
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                    DynamicConfig.LOGIN).getTemplateFilePath());
            return "freemarker";
        }
        String enterpriseName = this.getRequest().getParameter("enterpriseName");
        String orderNum = ContextUtil.createRuestNumber();//生成第三需要的流水号

        CommonRequst cq = new CommonRequst();

        cq.setBussinessType(ThirdPayConstants.BT_MODIFY);
        cq.setTransferName(ThirdPayConstants.TN_MODIFY);
        cq.setLegal(mem.getLegalPerson());//法人姓名
        cq.setLegalIdNo(mem.getLegalNo());//法人身份证号码
        cq.setTelephone(mem.getTelphone());//联系人电话
        cq.setTrueName(enterpriseName);//企业名称
        cq.setBankBranchName(mem.getTruename());//企业原来名称
        cq.setGuarType(this.getRequest().getParameter("type"));//变更类型
        cq.setRequsetNo(orderNum);//流水号
        cq.setThirdPayConfigId(mem.getThirdPayFlagId());
        cq.setThirdPayConfigId0(mem.getThirdPayFlag0());

        CommonResponse cr = ThirdPayInterfaceUtil.thirdCommon(cq);

        if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
            webMsgInstance("0", Constants.CODE_SUCCESS, "修改企业信息成功", "", "", "", "", "");
        } else {
            webMsgInstance("0", cr.getResponsecode(), cr.getResponseMsg(), "", "", "", "", "");
        }
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
        return "freemarker";
    }
}
