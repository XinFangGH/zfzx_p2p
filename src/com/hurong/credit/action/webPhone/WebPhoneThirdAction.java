package com.hurong.credit.action.webPhone;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.action.pay.PayAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.mobile.ErrorMessageModel;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.thirdInterface.CsBank;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryFlowService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlEarlyRedemptionService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.thirdInterface.*;
import com.hurong.credit.service.user.BpCustLoginlogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.HttpRequestDeviceUtils;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.FuDianPay.FuDian;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayLogService;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

public class WebPhoneThirdAction extends BaseAction {

    @Resource
    private IPayService iPayService;
    @Resource
    private HuiFuService huiFuService;
    @Resource
    private AllinPayService allinPayService;
    @Resource
    private BpCustMemberService bpCustMemberService;
    @Resource
    private ObSystemAccountService obSystemAccountService;
    @Resource
    private FuiouService fuiouService;
    @Resource
    private WebBankcardService webBankCardService;
    @Resource
    private YeePayService yeePayService;
    @Resource
    private ObAccountDealInfoService obAccountDealInfoService;
    @Resource
    private BpFundIntentService bpFundIntentService;
    @Resource
    private InvestPersonInfoService investPersonInfoService;
    @Resource
    private PlBidPlanService plBidPlanService;
    @Resource
    private SlEarlyRepaymentRecordService slEarlyRepaymentRecordService;
    @Resource
    private OpraterBussinessDataService opraterBussinessDataService;
    @Resource
    private PlBidCompensatoryService plBidCompensatoryService;
    @Resource
    private PlBidCompensatoryFlowService plBidCompensatoryFlowService;
    @Resource
    private ThirdPayRecordService thirdPayRecordService;
    @Resource
    private BpCustRelationService bpCustRelationService;
    @Resource
    private CsBankService csBankService;
    @Resource
    private PlMmOrderAssignInterestService plMmOrderAssignInterestService;
    @Resource
    private PlManageMoneyPlanService plManageMoneyPlanService;
    @Resource
    private PlEarlyRedemptionService plEarlyRedemptionService;
    @Resource
    private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
    @Resource
    private SendMesService sendMesService;
    @Resource
    private ThirdPayLogService thirdPayLogService;
    @Resource
    private BpCustLoginlogService bpCustLoginlogService;
    @Resource
    private WebBankcardService webBankcardService;
    @Resource
    private PlBidInfoService plBidInfoService;

    private BigDecimal principal;//本金
    private BigDecimal liquidatedDamagesMoney;//罚息

    public BigDecimal getLiquidatedDamagesMoney() {
        return liquidatedDamagesMoney;
    }

    public void setLiquidatedDamagesMoney(BigDecimal liquidatedDamagesMoney) {
        this.liquidatedDamagesMoney = liquidatedDamagesMoney;
    }

    private BigDecimal interest;//利息

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    private static Log logger = LogFactory.getLog(PayAction.class);
    MoneyMoreMore moneyMoreMore;
    private BpCustMember bpCustMember;
    private List<CsBank> listCsbank;
    // 得到config.properties读取的所有资源
    private static Map configMap = AppUtil.getConfigMap();
    //短信发送
    SmsSendUtil smsSendUtil = new SmsSendUtil();
    private String loginname;
    private Long loginId;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    private String telphone;
    private String email;
    private String guarType; //企业开户类型     1，企业，2担保公司, 3出借企业
    private String truename;
    private String cardcode;
    private String checkbank;// 银行编号
    private String amountShow;// 金额
    private String user_account_money;// 提现金额
    private String amount;// 充值金额
    private String rechargeMoneymoremore;// 钱多多唯一标识符
    public String bid; // 开启投标授权
    public String payMoney;// 开启还款授权
    public String secondAllow;// 开启二次分配授权
    private String ResultCode;
    private String CodeMsg;
    private String MoneymoremoreId;

    private String bType;// 绑定银行卡

    protected String bankCode;// 银行id
    protected String cardNo;// 提现卡号
    protected String bankname;// 银行名称
    protected String branchBankName;// 开户姓名
    protected String province;// 开户省份
    protected String city;// 开户城市
    protected String provinceId;// 开户省份id
    protected String cityId;// 开户城市id
    protected String outBankName;// 网点名称
    /**
     * 企业客户-银行开户许可证
     */
    protected String bankLicense;

    /**
     * 企业客户-营业执照
     */
    protected String businessLicense;
    /**
     * 企业客户-税务登记号
     */
    protected String taxNo;
    /**
     * 企业客户-法人姓名
     */
    protected String legalPerson;
    /**
     * 企业客户-法人身份证号码
     */
    protected String legalNo;
    /**
     * 企业客户-企业联系人
     */
    protected String contactPerson;
    /**
     * 是否三证合一
     */
    protected String threeCard;
    /**
     * 社会信用代码(三证合一)
     */
    protected String threeCardCode;


    public String getThreeCard() {
        return threeCard;
    }

    public void setThreeCard(String threeCard) {
        this.threeCard = threeCard;
    }

    public String getThreeCardCode() {
        return threeCardCode;
    }

    public void setThreeCardCode(String threeCardCode) {
        this.threeCardCode = threeCardCode;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public String getBankLicense() {
        return bankLicense;
    }

    public void setBankLicense(String bankLicense) {
        this.bankLicense = bankLicense;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalNo() {
        return legalNo;
    }

    public void setLegalNo(String legalNo) {
        this.legalNo = legalNo;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }


    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {

        this.bankname = StringUtil.stringURLEncoderByUTF8(bankname);
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = StringUtil.stringURLEncoderByUTF8(branchBankName);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = StringUtil.stringURLEncoderByUTF8(province);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = StringUtil.stringURLEncoderByUTF8(city);
    }

    public String getOutBankName() {
        return outBankName;
    }

    public void setOutBankName(String outBankName) {
        this.outBankName = StringUtil.stringURLEncoderByUTF8(outBankName);
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    private String loanMoneymoremore;// 用户乾多多账号(多多号、手机号、邮箱均可)

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getSecondAllow() {
        return secondAllow;
    }

    public void setSecondAllow(String secondAllow) {
        this.secondAllow = secondAllow;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        this.amount = df.format(Double.valueOf(amount));
    }

    public String getUser_account_money() {
        return user_account_money;
    }

    public void setUser_account_money(String userAccountMoney) {
        user_account_money = userAccountMoney;
    }

    public String getCheckbank() {
        return checkbank;
    }

    public void setCheckbank(String checkbank) {
        this.checkbank = checkbank;
    }

    public String getAmountShow() {
        return amountShow;
    }

    public void setAmountShow(String amountShow) {
        this.amountShow = amountShow;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
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


    public String getGuarType() {
        return guarType;
    }

    public void setGuarType(String guarType) {
        this.guarType = guarType;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getRechargeMoneymoremore() {
        return rechargeMoneymoremore;
    }

    public void setRechargeMoneymoremore(String rechargeMoneymoremore) {
        this.rechargeMoneymoremore = rechargeMoneymoremore;
    }

    public String getLoanMoneymoremore() {
        return loanMoneymoremore;
    }

    public void setLoanMoneymoremore(String loanMoneymoremore) {
        this.loanMoneymoremore = loanMoneymoremore;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public String getCodeMsg() {
        return CodeMsg;
    }

    public void setCodeMsg(String codeMsg) {
        CodeMsg = codeMsg;
    }

    public String getMoneymoremoreId() {
        return MoneymoremoreId;
    }

    public void setMoneymoremoreId(String moneymoremoreId) {
        MoneymoremoreId = moneymoremoreId;
    }

    public MoneyMoreMore getMoneyMoreMore() {
        return moneyMoreMore;
    }

    public void setMoneyMoreMore(MoneyMoreMore moneyMoreMore) {
        this.moneyMoreMore = moneyMoreMore;
    }

    public BpCustMember getBpCustMember() {
        return bpCustMember;
    }

    public void setBpCustMember(BpCustMember bpCustMember) {
        this.bpCustMember = bpCustMember;
    }

    /**
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * Convenience method to get the response
     *
     * @return current response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one
     * doesn't exist.
     *
     * @return the session from the request (request.getSession()).
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected String getBasePath() {

        String path = getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + getRequest().getContextPath() + "/";
        return path;//path.replace("https", "http").replace(":443", "");
    }


    // ---------------------------Methods------------------------------


    private void trimParams() {
        if (StringUtils.isNotEmpty(cardcode)) {
            cardcode = cardcode.trim();
            cardcode = cardcode.toUpperCase();
        }
        if (StringUtils.isNotEmpty(truename)) {
            truename = truename.trim();
        }
        if (StringUtils.isNotEmpty(legalPerson)) {
            legalPerson = legalPerson.trim();
        }
        if (StringUtils.isNotEmpty(legalNo)) {
            legalNo = legalNo.trim();
        }
        if (StringUtils.isNotEmpty(telphone)) {
            telphone = telphone.trim();
        }
        if (StringUtils.isNotEmpty(contactPerson)) {
            contactPerson = contactPerson.trim();
        }
        if (StringUtils.isNotEmpty(bankLicense)) {
            bankLicense = bankLicense.trim();
        }
        if (StringUtils.isNotEmpty(businessLicense)) {
            businessLicense = businessLicense.trim();
        }
    }


    /**
     * 检查p2p用户是否能进行第三方支付开通操作
     *
     * @param mem 传入开通用户信息
     * @return object[2]:Object[0] true 可以进行开通  false 不可以开通   ；object[1] 中文描述
     */
    public Object[] checkRegisterCondition(BpCustMember mem) {
        Object[] ret = new Object[2];
        try {
            BpCustMember checkMemCardCode = bpCustMemberService.isExistCode(cardcode, mem.getId());
            if (checkMemCardCode != null) {
                ret[0] = false;
                ret[1] = "该证件号码已经认证过了，请换一个证件号码(身份证/组织机构号码)";
            } else {


                if (mem.getThirdPayFlagId() != null && !"".equals(mem.getThirdPayFlagId())) {
                    ret[0] = false;
                    ret[1] = "该用户已经完成了第三方支付账户开通操作，不需要再次开通";
                } else {
                    //判断身份证号码
                    String strIdCard = "";
                    if (mem.getCustomerType() == 0) {
//                        CardValidate cardVal= new CardValidate();
//                        strIdCard = cardVal.IDCardValidate(cardcode);
                        strIdCard = "true";
                    } else {
                        strIdCard = "true";
                    }
                    //判断真实姓名
                    boolean checkname = true;
                            /* String regex = "[\u4E00-\u9FA5]+";
                             if(!truename.matches(regex)){
								 checkname=false;
							 } 	*/
                    if (strIdCard.equals("true") && checkname) {
                        ret[0] = true;
                        ret[1] = "可以进行第三方开通操作";
                    } else {
                        ret[0] = false;
                        ret[1] = strIdCard;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret[0] = false;
            ret[1] = "系统出错了，请联系管理员";
        }
        return ret;
    }

    //个人开通三方账户
    public String appRegAndBind() {
        trimParams();
        MobileDataResultModel model = new MobileDataResultModel();
        ErrorMessageModel messageModel = new ErrorMessageModel();
        //String isMobile = this.getRequest().getParameter("isApp");
        String isMobile = getRequest().getHeader("isApp");

        String truename = getRequest().getParameter("truename");
        System.out.println("truename111" + truename);
        String backpath = this.getRequest().getParameter("backpath");
        this.getRequest().getSession().setAttribute("backpath", backpath);
        String[] ret = new String[2];
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        bpCustMember = mem;
        Object[] userCondition = bpCustMemberService.checkUserCondition(mem);
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        Boolean flag = true;
        try {
//            if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
//                truename = new String(truename.getBytes("iso-8859-1"),"utf-8");//真实姓名
//            }
            if ((Boolean) userCondition[0]) {//检查用户基本资格
                mem = bpCustMemberService.get(mem.getId());

                Object[] checkstatus = this.checkRegisterCondition(mem);
                if (checkstatus[0].equals(true)) {
                    if (flag) {
                        if (threeCard != null && !threeCard.equals("") && threeCard.equals("1")) {
                            cardcode = threeCardCode;
                            mem.setCardcode(cardcode);
                        } else {
                            mem.setCardcode(cardcode);
                        }

//                        if(isMobile!=null){
//                            truename = new String(truename.getBytes("iso-8859-1"),"utf-8");//真实姓名 = new String(truename.getBytes("iso-8859-1"),"utf-8");//真实姓名
//                            System.out.println("truename222"+truename);
//                            mem.setTruename(truename);
//                        }
                        mem.setTruename(truename);

                        CommonRequst commonRequst = new CommonRequst();
                        String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
                        System.out.println("orderLenth--" + requestNo.length());
                        if (mem.getCustomerType() != null && mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)) {//企业用户
                            mem.setBankLicense(bankLicense);
                            mem.setBusinessLicense(businessLicense);
                            mem.setTaxNo(taxNo);
                            mem.setLegalPerson(legalPerson);
                            mem.setLegalNo(legalNo);
                            mem.setContactPerson(contactPerson);

                            if (isMobile != null && !"".equals(isMobile) && ("1".equals(isMobile))) {
                                commonRequst.setIsMobile("1");//手机端操作
                            }

                            commonRequst.setRequsetNo(requestNo);//流水号
                            commonRequst.setEnterpriseName(truename);//企业用户名
                            commonRequst.setTrueName(truename);//企业用户名
                            commonRequst.setCardNumber(legalNo);
                            commonRequst.setAccountType(1);
                            commonRequst.setBankLicense(bankLicense);//开户银行许可证
                            commonRequst.setOrgNo(cardcode);//组织机构代码
                            commonRequst.setBusinessLicense(businessLicense);//营业执照编号
                            if (!threeCard.equals("") && threeCard != null && threeCard.equals("0")) {//非三证合一
                                threeCardCode = null;
                            }
                            commonRequst.setThreeCardCode(threeCardCode);//社会信用代码
                            commonRequst.setTaxNo(taxNo);//税务登记号
                            commonRequst.setLegal(legalPerson);//法人姓名
                            commonRequst.setLegalIdNo(legalNo);//法人身份证号码
                            commonRequst.setContact(contactPerson);//联系人
                            commonRequst.setContactPhone(telphone);//联系人电话
                            commonRequst.setTelephone(telphone);
                            commonRequst.setGuarType(guarType);//开户类型，1借款企业，2担保公司 3,出借企业
                            commonRequst.setCustMemberId(mem.getId().toString());
                            commonRequst.setLoginname(mem.getLoginname());//登录名
                            commonRequst.setRegisterType(2);//注册类型1表示全自动，2表示半自动
                            commonRequst.setAccountType(1);//账户类型，空表示个人账户，1表示企业账户
                            BpCustRelation r = bpCustRelationService.getP2pCustById(mem.getId());
                            commonRequst.setTransferName(ThirdPayConstants.TN_EREGISTER);//企业用户开通第三方transerfername
                            commonRequst.setBussinessType(ThirdPayConstants.BT_EREGISTER);//企业用户开通第三方
                            commonRequst.setMemberClassType("ENTERPRISE");
                            if (null != mem.getEntCompanyType() && mem.getEntCompanyType() == 1) {//标识为企业担保户，针对的易宝
                                commonRequst.setMemberClassType("GUARANTEE_CORP");
                            }

                            commonRequst.setRequsetNo(requestNo);//流水号
                            commonRequst.setEnterpriseName(truename);//企业用户名
                            commonRequst.setTrueName(truename);//企业用户名
                            commonRequst.setCardNumber(legalNo);
                            commonRequst.setAccountType(1);
                            commonRequst.setBankLicense(bankLicense);//开户银行许可证
                            commonRequst.setOrgNo(cardcode);//组织机构代码
                            commonRequst.setBusinessLicense(businessLicense);//营业执照编号
                            commonRequst.setTaxNo(taxNo);//税务登记号
                            commonRequst.setLegal(legalPerson);//法人姓名
                            commonRequst.setLegalIdNo(legalNo);//法人身份证号码
                            commonRequst.setContact(contactPerson);//联系人
                            commonRequst.setContactPhone(telphone);//联系人电话
                            commonRequst.setTelephone(telphone);
                            commonRequst.setCustMemberId(mem.getId().toString());
                            commonRequst.setLoginname(mem.getLoginname());//登录名
                            commonRequst.setRegisterType(2);//注册类型1表示全自动，2表示半自动
                            commonRequst.setAccountType(1);//账户类型，空表示个人账户，1表示企业账户
                            commonRequst.setTransferName(ThirdPayConstants.TN_EREGISTER);//企业用户开通第三方transerfername
                            commonRequst.setBussinessType(ThirdPayConstants.BT_EREGISTER);//企业用户开通第三方
                            commonRequst.setMemberClassType("ENTERPRISE");
                            if (null != mem.getEntCompanyType() && mem.getEntCompanyType() == 1) {//标识为企业担保户，针对的易宝
                                commonRequst.setMemberClassType("GUARANTEE_CORP");
                            }
                        } else {
                            bpCustMemberService.merge(mem);

                            commonRequst.setTrueName(truename);//真实姓名
                            commonRequst.setAccountType(0);
                            commonRequst.setRequsetNo(requestNo);//流水号
                            commonRequst.setCardNumber(cardcode);//身份证号
                            commonRequst.setTelephone(mem.getTelphone());//手机号
                            commonRequst.setGuarType(FuDian.ROLE_TYPE3);
                            commonRequst.setCustMemberId(mem.getId().toString());
                            commonRequst.setLoginname(mem.getLoginname());//登录名
                            commonRequst.setAccountType(null);//账户类型，空表示个人账户，1表示企业账户
                            commonRequst.setTransferName(ThirdPayConstants.TN_PREGISTER);//业务类型 个人开通第三方transferName
                            commonRequst.setBussinessType(ThirdPayConstants.BT_PREGISTER);//个人开通第三方
                        }
                        CommonResponse commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);
                        if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {

                            mem.setTruename(truename);//真实姓名
                            mem.setCardcode(cardcode);
                            mem.setIsCheckCard("2");//开通三方账户中
                            bpCustMemberService.merge(mem);
                            model.setCode(MobileErrorCode.CHANGEPHONE_ERROR);
                            model.setMsg("开通第三方支付申请成功");
                            setJsonString(model.toJSON());
                            return SUCCESS;
                        } else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                            bpCustMemberService.merge(mem);
                            mem = bpCustMemberService.get(mem.getId());

                            mem.setTruename(truename);//真实姓名
                            mem.setCardcode(cardcode);
                            mem.setThirdPayFlag0(commonResponse.getThirdPayConfigId0());
                            mem.setThirdPayFlagId(commonResponse.getThirdPayConfigId());
                            mem.setThirdPayConfig(commonResponse.getThirdPayConfig());
                            mem.setThirdPayStatus(BpCustMember.THIRDPAY_ACCTIVED);//已激活
                            mem.setIsCheckCard("1");
                            bpCustMemberService.merge(mem);
                            //开通第三方成功发送短信、邮件、站内信。
                            Map<String, String> mapMessage = new HashMap<String, String>();
                            mapMessage.put("key", "sms_SignThirdPay");
                            mapMessage.put("userId", bpCustMember.getId().toString());
                            mapMessage.put("${name}", bpCustMember.getLoginname());
                            String result = sendMesService.sendSmsEmailMessage(mapMessage);
                            model.setMsg("开通第三方支付成功");
                            setJsonString(model.toJSON());
                            return SUCCESS;
                            //webMsgInstance("0", Constants.CODE_SUCCESS,"开通第三方支付成功", "", "", "", "", "");
                        } else {
                            model.setCode(MobileErrorCode.SERVICE_ERROR);
                            model.setMsg("开通第三方账户失败");
                            setJsonString(model.toJSON());
                            return SUCCESS;
                            //webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
                        }
                    }
                } else {
                    messageModel.setCode(MobileErrorCode.SERVICE_ERROR);
                    messageModel.setMess((String) checkstatus[1]);
                    this.getRequest().setAttribute("messageModel", messageModel);
                    this.getRequest().setAttribute("code", MobileErrorCode.SERVICE_ERROR);
                    this.getRequest().setAttribute("mes", checkstatus[1]);
//                    model.setCode(MobileErrorCode.SERVICE_ERROR);
//                    model.setMsg((String) checkstatus[1]);
//                    model.setMsg("开通第三方账户失败");
//                    setJsonString(model.toJSON());
//                    return SUCCESS;
                    if (isMobile != null && !"".equals(isMobile)) {
                        this.getRequest().setAttribute("code", MobileErrorCode.SERVICE_ERROR);
                        this.getRequest().setAttribute("mes", checkstatus[1]);
                        return "error_mes";
                    }
                    return "error_message";
                }
            } else {
                if(isMobile != null && !"".equals(isMobile)){
                    this.getRequest().setAttribute("mes", userCondition[1]);
                    return "error_mes";
                }
                this.getRequest().setAttribute("code", MobileErrorCode.SERVICE_ERROR);
                this.getRequest().setAttribute("mes", userCondition[1]);
                return "error_message";
                //forwardPage=userCondition[2].toString();
                //model.setCode(MobileErrorCode.SERVICE_ERROR);
                //model.setMsg("开通第三方账户失败");
                //setJsonString(model.toJSON());
                //return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().setAttribute("code", MobileErrorCode.SERVICE_ERROR);
            this.getRequest().setAttribute("mes", "开通第三方账户错误，请联系管理员");
            if ("1".equals(isMobile)) {
                this.getRequest().setAttribute("mes", "开通第三方账户错误，请联系管理员");
                return "error_mes";
            }
            return "error_message";
//            model.setCode(MobileErrorCode.SERVICE_ERROR);
//            model.setMsg("开通第三方账户错误，请联系管理员");
//            setJsonString(model.toJSON());
//            return SUCCESS;
            //webMsgInstance("0", Constants.CODE_FAILED, "开通第三方账户错误，请联系管理员","", "", "", "", "");
        }
        //更新缓存
        this.getRequest().getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);
        this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, mem);//将用户信息保存在session中
        if (isMobile != null && !"".equals(isMobile)) {
            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
        } else {
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
        }
        return "";
    }

    /**
     * 绑定银行卡
     * 富滇银行之间请求页面，银行卡号等参数为回调的时候传回来
     *
     * @throws IOException
     */
    public String appBindCard() throws IOException {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String mobile = this.getRequest().getParameter("isApp");
        String backpath = this.getRequest().getParameter("backpath");
        this.getRequest().getSession().setAttribute("backpath", backpath);
        boolean isAPP = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if (mem != null) {
            List<WebBankcard> bankList = webBankcardService.getBycustAndState(mem.getId(), WebBankcard.BINDCARD_STATUS_SUCCESS);
            mem = bpCustMemberService.get(mem.getId());
            if (StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard()) && mem.getThirdPayFlagId() != null) {
                if (bankList == null || bankList.size() <= 0) {
                    mem = bpCustMemberService.get(mem.getId());
                    String orderNum = ContextUtil.createRuestNumber();//绑卡流水号
                    CommonRequst commonRequst = new CommonRequst();
                    if (mobile != null && !"".equals(mobile) && ("1".equals(mobile))) {
                        commonRequst.setIsMobile("1");//判断手机端操作
                    }
                    commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
                    commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
                    commonRequst.setRequsetNo(orderNum);
                    commonRequst.setBussinessType(ThirdPayConstants.BT_BINDCARD);
                    commonRequst.setTransferName(ThirdPayConstants.TN_BINDCARD);

                    //绑卡在三方页面进行，本地需要保存一条绑卡中的数据
                    WebBankcard card = new WebBankcard();
                    card.setCustomerId(mem.getId());
                    card.setCustomerType(Short.valueOf("0"));
                    card.setUsername(mem.getTruename());
                    card.setCardNum(cardNo);
                    card.setAccountname(mem.getTruename());
                    card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_REPARE);
                    card.setRequestNo(orderNum);
                    card.setThirdConfig(configMap.get("thirdPayConfig").toString());
                    card.setUserFlg(mem.getThirdPayFlagId());
                    webBankCardService.save(card);
                    CommonResponse commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);

                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, "您已经绑定过银行卡了", "", "", "", "", "");

                    if(isAPP){
                        return "back_msgApp";
                    }else{
                        return "back_msg";
                    }
//                    this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                }
            } else {
                webMsgInstance("0", Constants.CODE_FAILED, "请先进行实名认证，再进行绑卡操作", "", "", "", "", "");

                if(isAPP){
                    return "back_msgApp";
                }else{
                    return "back_msg";
                }
//                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
            }
        } else {
            if ("1".equals(mobile)) {
                this.getResponse().sendRedirect(this.getBasePath() + "/webPhone/regLoghomePage.do");
            } else {
                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
            }
        }
        return "freemarker";
    }

    /**
     * 取消绑定银行卡
     *
     * @return
     */
    public String appCancelBindCard() throws IOException {
        String isMobile = this.getRequest().getParameter("isApp");
        String backpath = this.getRequest().getParameter("backpath");
        this.getRequest().getSession().setAttribute("backpath", backpath);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        bpCustMember = mem;
        if (mem != null) {
            mem = bpCustMemberService.get(mem.getId());
            String cardId = getRequest().getParameter("cardNoId");
            if (cardId != null && !"".equals(cardId) && !"undefined".equals(cardId)) {
                WebBankcard card = webBankCardService.get(Long.valueOf(cardId));
                if (card != null) {
                    if (mem.getThirdPayFlagId() == null) {
                        webMsgInstance("0", Constants.CODE_FAILED, "请先开通第三方支付", "", "", "", "", "");
                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                    } else {
                        CommonRequst commonRequst = new CommonRequst();
                        String orderNum = ContextUtil.createRuestNumber();//流水号
                        if (isMobile != null && !"".equals(isMobile) && ("1".equals(isMobile))) {
                            commonRequst.setIsMobile("1");//判断手机端操作
                        }
                        commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
                        commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
                        commonRequst.setBankCardNumber(card.getCardNum());
                        commonRequst.setRequsetNo(orderNum);

                        commonRequst.setTransferName(ThirdPayConstants.TN_CANCELCARD);//业务名称
                        commonRequst.setBussinessType(ThirdPayConstants.BT_CANCELCARD);//业务类型
                        card.setRequestNo(orderNum);//解绑卡收到回调的时候需要根据此流水号来查询卡
                        CommonResponse commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);
                        if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
                            webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "", "", "");
                            if (isMobile != null && !"".equals(isMobile) && "1".equals(isMobile)) {
                                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                            } else {
                                this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                            }
                            return "freemarker";
                        } else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
                            webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "", "", "");
                            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                            return "freemarker";
                        } else {
                            this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                            webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "", "", "");
                        }
                    }
                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, "选择的银行卡不存在", "", "", "", "", "");
                    this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
                }
            } else {
                webMsgInstance("0", Constants.CODE_FAILED, "请先选择要取消绑定的银行卡", "", "", "", "", "");
                this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/back_msg.ftl");
            }
        } else {
            this.getResponse().sendRedirect(this.getBasePath() + "/webPhone/regLoghomePage.do");
        }
        return "freemarker";
    }


    /**
     * 充值统一接口Mozilla/5.0 (Linux; Android 7.0; EVA-DL00 Build/HUAWEIEVA-DL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36 zxzbAndroid
     */
    public String appRecharge() {
        String isMobile = this.getRequest().getParameter("isApp");
        String isHapp = this.getRequest().getHeader("isApp");
        String payType = this.getRequest().getParameter("payType");
        String backpath = this.getRequest().getParameter("backPath");
        this.getRequest().getSession().setAttribute("backPath", backpath);
        boolean isAPP = HttpRequestDeviceUtils.isAppBrower(getRequest());
        //交易金额
        String money = this.getRequest().getParameter("amount");
        DecimalFormat df = new DecimalFormat("#####0.00");
        String amount = df.format(Double.valueOf(money));

//		String isMobile ="1";
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (null != mem && !"".equals(mem)) {
            mem = bpCustMemberService.get(mem.getId());
            bpCustMember = mem;
        }
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        /**
         * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
         */
        Object[] usercondition = bpCustMemberService.checkUserDealCondition(mem);
        //查询用户是否绑卡
        List<WebBankcard> banks = webBankcardService.getBycustAndState(mem.getId(), "bindCard_status_success");
        if (banks != null && banks.size() > 0) {
            try {
                if ((Boolean) usercondition[0]) {//验证是否 具备交易资格
                    if (amount != null && new BigDecimal(amount).compareTo(new BigDecimal(0)) > 0) {//充值金额不能为空和大于0元
                        String[] dealToErpRetArr = new String[2];
                        // 交易流水号
                        String orderNum = ContextUtil.createRuestNumber();//"hry"+
                        // 保存充值交易记录
                        Map<String, Object> mapO = new HashMap<String, Object>();
                        mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
                        mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
                        mapO.put("transferType", ObAccountDealInfo.T_RECHARGE);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)（必填）
                        mapO.put("money", new BigDecimal(amount));// 交易金额 （必填）
                        mapO.put("dealDirection", ObAccountDealInfo.DIRECTION_INCOME);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
                        mapO.put("dealType", ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
                        mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
                        mapO.put("dealStatus", ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3支付失败。。。(参见ObAccountDealInfo中的常量)(必填)
                        dealToErpRetArr = obAccountDealInfoService.operateAcountInfo(mapO);
                        if (dealToErpRetArr != null && dealToErpRetArr[0].equals(Constants.SUCCESSFLAG)) {
                            CommonRequst common = new CommonRequst();
                            if (isMobile != null && !"".equals(isMobile) && ("1").equals(isMobile) || "1".equals(isHapp)) {
                                common.setIsMobile("1");//判断是否是手机端
//						    	common.setTransferName(ThirdPayConstants.TN_APPQRECHARGE);
//						    	common.setBussinessType(ThirdPayConstants.BT_APPQRECHARGE);
                            }
                            common.setTransferName(ThirdPayConstants.TN_RECHAGE);//充值
                            common.setBussinessType(ThirdPayConstants.BT_RECHAGE);//业务类型
                            //2个三方账号
                            common.setThirdPayConfigId(mem.getThirdPayFlagId());
                            common.setThirdPayConfigId0(mem.getThirdPayFlag0());
                            common.setFee(new BigDecimal(0));
                            common.setAmount(new BigDecimal(amount));//交易金额
                            common.setRequsetNo(orderNum);//流水号
                            if (mem.getCustomerType() != null && mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)) {//企业用户
                                common.setAccountType(1);//1代表企业账户 0代表个人账户
                            } else {//个人用户充值
                                common.setAccountType(0);//1代表企业账户 0代表个人账户
                            }
                            //支付方式 （个人、企业都可）
                            /*
                             * 1:快捷充值
							 * 3:网关充值（暂不可用）    注：2017-12-26三方让修改为6
							 * 5:银行直连（绑定富滇银行卡，只能走网关充值及银行直连）
							 */
                            common.setBankFastPay(payType);
                            CommonResponse commonResponse = ThirdPayInterfaceUtil.thirdCommon(common);
                            if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
                                webMsgInstance("0", Constants.CODE_SUCCESS, "充值申请成功", "", "", "", "", "");
                            } else {
                                webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
                            }

                        } else {
                            webMsgInstance("0", dealToErpRetArr[0], dealToErpRetArr[1], "", "", "", "", "");
                        }
                    } else {
                        webMsgInstance("0", Constants.CODE_FAILED, "操作错误，充值金额不能为空或应大于0元", "", "", "", "", "");
                    }
                } else {
                    forwardPage = usercondition[2].toString();
                    webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(), "", "", "", "", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员", "", "", "", "", "");
            }
        } else {
            webMsgInstance("0", Constants.CODE_FAILED, "请先绑卡", "", "", "", "", "");
        }
        if (isAPP) {
            //            this.setSuccessResultValue("/WEB-INF/mobileJsp/app/back_msg.jsp");
            return "back_msgApp";
        } else {
            //H5
            return "back_msg";
//            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
        }

//        else {
//            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
//        }
//        return "freemarker";
    }

    /**
     * 提现申请接口
     */
    public String appWithdraws() {
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        Map maps = new HashMap();
//        String isMobile = this.getRequest().getParameter("isApp");//判断是否是手机端的操作
//        String isHapp = this.getRequest().getHeader("isApp");
        boolean isAPP = HttpRequestDeviceUtils.isAppBrower(getRequest());
        String backpath = this.getRequest().getParameter("backPath");
        this.getRequest().getSession().setAttribute("backPath", backpath);
        mem = bpCustMemberService.get(mem.getId());
        String money = this.getRequest().getParameter("amount");
//        money.replace(",","");
        DecimalFormat df = new DecimalFormat("0.##");
        String cardNo = this.getRequest().getParameter("cardId");//银行卡ID
        bpCustMember = mem;
        //业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
        String forwardPage = DynamicConfig.MESSAGE;
        CommonResponse commonResponse = new CommonResponse();
        String orderNum = ContextUtil.createRuestNumber();
        CommonRequst commonRequst = new CommonRequst();
        try {
            Number parseMoney = NumberFormat.getInstance().parse(money);
            String amount = df.format(parseMoney);
            System.out.println("提现金额---" + "==" + amount + "-----" + parseMoney);
//        String amount = df.format(Double.valueOf(money));

            /**
             * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
             */
            Object[] usercondition = bpCustMemberService.checkUserDealCondition(mem);
            try {
                if ((Boolean) usercondition[0]) {//验证是否 具备交易资格
                    if (cardNo != null && !cardNo.equals("")) {
                        cardNo = StringUtil.html2Text(cardNo);
                        if (mem.getThirdPayFlagId() != null && !mem.getThirdPayFlagId().equals("")) {
                            //实时计算可提现金额
                            BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(mem.getId(), ObSystemAccount.type0.toString());
                            mem.setAvailableInvestMoney(ret[3]);
                            //查询三方获取可取现余额
                            Map<String, String> memberMoney = bpCustMemberService.queryThirdPayCustomerInfo(mem);
                            if (mem.getAvailableInvestMoney() == null || mem.getAvailableInvestMoney().compareTo(new BigDecimal(amount)) < 0) {
                                webMsgInstance("0", CommonResponse.RESPONSECODE_FAILD, "可提现金额不足", "", "", "", "", "");
                                //如果三方返回的可提现余额大于取现金额
                            } else if (StringUtils.isNotEmpty(memberMoney.get("withdrawBalance")) && new BigDecimal(memberMoney.get("withdrawBalance")).compareTo(new BigDecimal(amount)) < 0) {
                                webMsgInstance("0", CommonResponse.RESPONSECODE_FAILD, "可提现金额不足", "", "", "", "", "");
                            } else {
                                mem = obSystemAccountService.getAccountSumMoney(mem);
                                WebBankcard card = webBankCardService.get(Long.valueOf(cardNo));
                                Date date = new Date();
                                commonRequst.setIsMobile("1");//手机端操作
                                commonRequst.setTransferName(ThirdPayConstants.TN_WITHDRAW);//业务用途
                                commonRequst.setBussinessType(ThirdPayConstants.BT_WITHDRAW);//业务类型
                                commonRequst.setTransactionTime(date);
                                commonRequst.setRequsetNo(orderNum);//请求流水号
                                commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
                                commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
                                commonRequst.setAmount(new BigDecimal(amount));// 交易金额
                                commonRequst.setFee(new BigDecimal("1.00"));//用户承担手续费
                            /*if(card.getProvinceId()!=null){
                                commonRequst.setProvince(card.getProvinceId().toString());//开户省份代码
							}
							if(card.getCityId()!=null){
								commonRequst.setCity(card.getCityId().toString());//开户城市代码
							}*/
                                if (mem.getCustomerType() != null && mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)) {//个人用户提现
                                    if (cardNo != null) {//判断是否绑定银行卡   针对于个人用户
                                        commonRequst.setAccountType(0);//1代表企业账户 0代表个人账户
                                        commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);
                                        if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
                                            Map<String, Object> mapO = new HashMap<String, Object>();
                                            mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
                                            mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
                                            mapO.put("transferType", ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
                                            mapO.put("money", new BigDecimal(amount));// 交易金额
                                            mapO.put("dealDirection", ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
                                            mapO.put("dealType", ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
                                            mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
                                            if (commonResponse.getDealState() != null && !commonResponse.getDealState().equals("")) {
                                                mapO.put("bankId", cardNo);
                                                mapO.put("dealStatus", commonResponse.getDealState());// 资金交易状态：1等待支付，2支付成功，3
                                            } else {
                                                mapO.put("dealStatus", ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
                                            }
                                            String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
                                            this.getRequest().setAttribute("str", commonResponse.getResponseMsg());
                                            webMsgInstance("0", Constants.SUCCESSFLAG, "提现申请成功", "", "", "", "", "");
                                        } else {
                                            webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
                                        }
                                    } else {
                                        webMsgInstance("0", Constants.CODE_FAILED, "请绑定银行卡", "", "", "", "", "");
                                        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
                                    }
                                } else {//企业用户
                                    commonRequst.setAccountType(1);//判断是企业用户还是个人用户
                                    commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);
                                    if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {//直连的接口
                                        Map<String, Object> mapO = new HashMap<String, Object>();
                                        mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
                                        mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
                                        mapO.put("transferType", ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
                                        mapO.put("money", new BigDecimal(amount));// 交易金额
                                        mapO.put("dealDirection", ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
                                        mapO.put("dealType", ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
                                        mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
                                        if (commonResponse.getDealState() != null && !commonResponse.getDealState().equals("")) {
                                            mapO.put("dealStatus", commonResponse.getDealState());// 资金交易状态：1等待支付，2支付成功，3
                                        } else {
                                            mapO.put("dealStatus", ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
                                        }
                                        String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
                                        webMsgInstance("1", CommonResponse.RESPONSECODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "", "", "");
                                    } else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
                                        Map<String, Object> mapO = new HashMap<String, Object>();
                                        mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
                                        mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
                                        mapO.put("transferType", ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
                                        mapO.put("money", new BigDecimal(amount));// 交易金额
                                        mapO.put("dealDirection", ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
                                        mapO.put("dealType", ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
                                        mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
                                        mapO.put("dealStatus", ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
                                        String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
                                        webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "", "", "");
                                    } else {
                                        webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
                                    }
                                    webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
                                }
                            }
                        }

                    } else {
                        webMsgInstance("0", Constants.CODE_FAILED, "请先绑定提现银行卡", "", "", "", "", "");
                    }
                } else {
                    webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(), "", "", "", "", "");
                }
            } catch (Exception e) {
                e.printStackTrace();
                webMsgInstance("0", Constants.CODE_FAILED, "系统错误:提现申请失败,请联系管理员", "", "", "", "", "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            webMsgInstance("0", Constants.CODE_FAILED, "系统错误:输入金额有误", "", "", "", "", "");
        }
        if (isAPP) {
            //            this.setSuccessResultValue("/WEB-INF/mobileJsp/app/back_msg.jsp");
            return "back_msgApp";
        } else {
            //H5
            return "back_msg";
//            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
        }

    }


    public String allBank() {
        return "allBank";
    }
}
