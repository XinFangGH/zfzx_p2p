package com.hurong.credit.action.pay;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */
import com.allinpay.ets.client.RequestOrder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatoryFlow;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.model.pay.AllinPay;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;
import com.hurong.credit.model.thirdInterface.CsBank;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustLoginlog;
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
import com.hurong.credit.util.*;
import com.thirdPayInterface.*;
import com.thirdPayInterface.FuDianPay.FuDian;
import com.thirdPayInterface.FuDianPay.InvestBean;
import com.thirdPayInterface.Huifu.HuiFuInterfaceUtil;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayLog;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayLogService;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 * @authoro
 * 
 */
@SuppressWarnings("serial")
public class PayAction extends BaseAction {
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
	SmsSendUtil  smsSendUtil = new SmsSendUtil();
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

	// 双乾 绑定接口 (已作废)
	public String bindCheck() {
		String retdata = "";
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			bpCustMember = bpCustMemberService.get(mem.getId());
			if (moneyMoreMore == null) {
				moneyMoreMore = new MoneyMoreMore();
			}
			moneyMoreMore.setLoanPlatformAccount(bpCustMember.getLoginname());// 用户在网贷平台的账号

			moneyMoreMore.setLoanMoneymoremore(loanMoneymoremore);// 用户乾多多账号(多多号、手机号、邮箱均可)

			retdata = iPayService.bind(moneyMoreMore, getBasePath());

			setJsonString(retdata);

		}
		return SUCCESS;
	}

	/**
	 * 无密自动还款授权接口
	 */
	public String loanAuthorize() {
		String refund=this.getRequest().getParameter("refund");
		String thirdPayconfig=configMap.get("thirdPayConfig").toString().trim();
		String thirdPayType=configMap.get("thirdPayType").toString().trim();
		StringBuffer open = new StringBuffer();
		StringBuffer close = new StringBuffer();
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			if(thirdPayType.equals("0")){
				if(thirdPayconfig.equals(Constants.UMPAY)){
					if (refund.equals("0")) {
						if (mem.getRefund() == null || mem.getRefund().equals("1")) {//检测是否已经开启
							CommonRequst commonRequst=new CommonRequst();
							commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
							Object[] ret=ThirdPayInterfaceUtil.noPasswordRepayment(commonRequst);
							
						}else{
							webMsgInstance("0", Constants.CODE_FAILED,"已经开启了自动还款授权",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							return "freemarker";
						}
					}else{
						if (mem.getRefund()!=null && mem.getRefund().equals("0")) {//检测是否已经开启
							/**
							 * 网关接口05
							 * 联动优势支付取消无密交易授权接口
							 * 涉及（投标授权，还款授权，还有无密码快捷交易即指）
							 * Map<String,Object> map =new HashMap<String,Object>();
							 * map.put("bathPath",String);//当前系统访问地址
							 * map.put("thirdPayflagId",String);//做授权的第三方支付用户号
							 * map.put("thirdPayflagId0",String);//做授权的第三方支付账号
							 * map.put("autoAuthorizationType",String);//做授权的交易类型  invest  自动投标授权     repayment 还款授权    nopassword 无密充值授权
							 * @param map
							 * @return
							  */
							CommonRequst commonRequst =new CommonRequst();
							commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
							Object[] ret=ThirdPayInterfaceUtil.relieveNoPasswordRepayment(commonRequst);
							
						}else{
							webMsgInstance("0", Constants.CODE_FAILED,"尚未开启自动还款授权，无法取消自动还款授权",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							return "freemarker";
						}
					}
				}else{
					if (bid.equals("1")) {
						if (mem.getTender() == null || mem.getTender().equals("0")) {
							open.append(1);
							open.append(",");
						}
					}
					if (payMoney.equals("1")) {
						if (mem.getRefund() == null || mem.getRefund().equals("0")) {
							open.append(2);
							open.append(",");
						}
					}
					if (secondAllow.equals("1")) {
						if (mem.getSecondAudit() == null
								|| mem.getSecondAudit().equals("0")) {
							open.append(3);
							open.append(",");
						}
					}
					if (bid.equals("0")) {
						if (mem.getTender() == null || mem.getTender().equals("1")) {
							close.append(1);
							close.append(",");
						}
					}
					if (payMoney.equals("0")) {
						if (mem.getRefund() == null || mem.getRefund().equals("1")) {
							close.append(2);
							close.append(",");
						}
					}
					if (secondAllow.equals("0")) {
						if (mem.getSecondAudit() == null
								|| mem.getSecondAudit().equals("1")) {
							close.append(3);
							close.append(",");
						}
					}
					if (open.length() > 0) {
						open = open.deleteCharAt(open.length() - 1);
					}
					if (close.length() > 0) {
						close = close.deleteCharAt(close.length() - 1);
					}
					if (moneyMoreMore == null) {
						moneyMoreMore = new MoneyMoreMore();
					}
					moneyMoreMore.setAuthorizeTypeOpen(open.toString());
					moneyMoreMore.setAuthorizeTypeClose(close.toString());
					moneyMoreMore.setMoneymoremoreId(mem.getThirdPayFlagId());
					try {
						iPayService.loanAuthorize(moneyMoreMore, getBasePath(), this
								.getResponse());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	/**
	 * 转账接口
	 */
	public void transfer() {
		// 生成转账列表
		moneyMoreMore = iPayService.loanJsonList(moneyMoreMore);
		iPayService.transfer(moneyMoreMore, getBasePath(), this.getResponse());
	}

	/**
	 * 接收授权页面返回信息
	 * 
	 * @return
	 */
	public String testLoanAuthorizeReturn() {
		try {
			System.out.println("页面返回:"
					+ this.getRequest().getParameter("ResultCode"));
			System.out.println("返回码:"
					+ this.getRequest().getParameter("ResultCode"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// ******************************新接口**************************************//
	/**
	 * 登录到汇付页面
	 */
	public String loginToHuiFu() {
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
					MyUserSession.MEMBEER_SESSION);
			if (mem != null) {
				mem = bpCustMemberService.get(mem.getId());
				String thirdPayConfig = configMap.get("thirdPayConfig")
						.toString();// 第三方支付名称
				String proj = configMap.get("thirdPayType").toString();// 第三方支付类型
				if (thirdPayConfig.equals(Constants.HUIFU)) {
					huiFuService.loginToHuiFu(this.getResponse(), mem,
							"UserLogin", this.getBasePath());
				} else if (thirdPayConfig.equals(Constants.FUIOU)) {
					String[] ret = fuiouService.loginToFuiou(
							this.getResponse(), mem, "UserLogin", this
									.getBasePath());
				} else if (thirdPayConfig.equals(Constants.MONEYMOREMORE)) {
					String url = configMap.get("MM_PostUrl").toString();
					WebClient.SendByUrl(this.getResponse(), url, "utf-8");
				}
			} else {
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "freemarker";
	}
	
	/**
	 * 绑定银行卡 
	 * 富滇银行之间请求页面，银行卡号等参数为回调的时候传回来
	 * @throws IOException
	 */
	public String bindCard() throws IOException {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String mobile =  this.getRequest().getParameter("mobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		if (mem != null) {
			List<WebBankcard> bankList = webBankcardService.getBycustAndState(mem.getId(),WebBankcard.BINDCARD_STATUS_SUCCESS);
			mem = bpCustMemberService.get(mem.getId());
			if (StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard()) && mem.getThirdPayFlagId() != null) {
				if (bankList== null || bankList.size() <= 0) {
					mem = bpCustMemberService.get(mem.getId());
					String orderNum=ContextUtil.createRuestNumber();//绑卡流水号
					CommonRequst commonRequst=new CommonRequst();
					if(mobile!=null&&!"".equals(mobile)&&("1".equals(mobile))){
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
					CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
					/*if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
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
						this.getRequest().setAttribute("str", commonResponse.getResponseMsg());
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TOTHIRD).getTemplateFilePath());
						return "freemarker";
					}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
						String cardNo = this.getRequest().getParameter("cardNo");
						String bankId = this.getRequest().getParameter("webBankcard.bankId");
						if(bankId!=null&&!bankId.equals("")&&cardNo!=null&&!cardNo.equals("")){
							CsBank csbank = csBankService.get(Long.valueOf(bankId));
							if(csbank!=null){
								WebBankcard card = new WebBankcard();
								card.setCustomerId(mem.getId());
								card.setCustomerType(Short.valueOf("0"));
								card.setUsername(mem.getTruename());
								card.setAccountname(mem.getTruename());
								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
								card.setRequestNo(orderNum);
								card.setUserFlg(mem.getThirdPayFlagId());
								card.setCardNum(cardNo);
								card.setBankId(csbank.getRemarks());
								card.setBankname(csbank.getBankname());
								card.setCustomerId(mem.getId());
								card.setProvinceId(Long.valueOf("1"));
								card.setCityId(Long.valueOf("1001"));
								card.setThirdConfig(mem.getThirdPayConfig());
								card.setUserFlg(mem.getThirdPayFlagId());
								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, "绑定银行卡成功", "","", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}else{
								webMsgInstance("0", Constants.CODE_FAILED, "开户银行选择错误", "","", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "银行卡信息填写错误", "","", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					}else {
						webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "", "",	"");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}*/
				}else {
					webMsgInstance("0", Constants.CODE_FAILED, "您已经绑定过银行卡了", "", "", "", "",	"");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else {
				webMsgInstance("0", Constants.CODE_FAILED, "请先进行实名认证，再进行绑卡操作", "","", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}else{
			if("mobile".equals(mobile)){
				this.getResponse().sendRedirect(this.getBasePath()+"mobileLoginlogin.do");
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}
		return "freemarker";
	}

	/**
	 * 绑定银行卡 
	 * 作为保留方法
	 * @throws IOException
	 */
	public String bindCardOld() throws IOException {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String mobile =  this.getRequest().getParameter("mobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		if (mem != null) {
				mem = bpCustMemberService.get(mem.getId());
				String orderNum=ContextUtil.createRuestNumber();//绑卡流水号
				CommonRequst commonRequst=new CommonRequst();
				if(mobile!=null&&!"".equals(mobile)&&("1".equals(mobile))){
					commonRequst.setIsMobile("1");//判断手机端操作 
				}
				commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
				commonRequst.setRequsetNo(orderNum);
				//商户订单日期
				commonRequst.setTransactionTime(new Date());
				//银行卡号
				commonRequst.setBankCardNumber(cardNo);
				//银行开户用户名
				commonRequst.setTrueName(mem.getTruename());
				//身份证号
				commonRequst.setCardNumber(mem.getCardcode());
				commonRequst.setBussinessType(ThirdPayConstants.BT_BINDCARD);
				commonRequst.setTransferName(ThirdPayConstants.TN_BINDCARD);
				CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
				if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
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
					this.getRequest().setAttribute("str", commonResponse.getResponseMsg());
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TOTHIRD).getTemplateFilePath());
					return "freemarker";
				}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
					//先判断用户是否进行了实名认证
					if(mem.getIsCheckCard()!=null&&mem.getTruename()!=null){
						String cardNo = this.getRequest().getParameter("cardNo");
						String bankId = this.getRequest().getParameter("webBankcard.bankId");
						if(bankId!=null&&!bankId.equals("")&&cardNo!=null&&!cardNo.equals("")){
							CsBank csbank = csBankService.get(Long.valueOf(bankId));
							if(csbank!=null){
								WebBankcard card = new WebBankcard();
								card.setCustomerId(mem.getId());
								card.setCustomerType(Short.valueOf("0"));
								card.setUsername(mem.getTruename());
								card.setAccountname(mem.getTruename());
								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
								card.setRequestNo(orderNum);
								card.setUserFlg(mem.getThirdPayFlagId());
								card.setCardNum(cardNo);
								card.setBankId(csbank.getRemarks());
								card.setBankname(csbank.getBankname());
								card.setCustomerId(mem.getId());
								card.setProvinceId(Long.valueOf("1"));
								card.setCityId(Long.valueOf("1001"));
								card.setThirdConfig(mem.getThirdPayConfig());
								card.setUserFlg(mem.getThirdPayFlagId());
								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, "绑定银行卡成功", "","", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}else{
								webMsgInstance("0", Constants.CODE_SUCCESS, "开户银行选择错误", "","", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						}else{
							webMsgInstance("0", Constants.CODE_SUCCESS, "银行卡信息填写错误", "","", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					}else{
						webMsgInstance("0", Constants.CODE_SUCCESS, "请先进行实名认证，再进行绑卡操作", "","", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}else {
					webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "", "",	"");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
		}else{
			if("mobile".equals(mobile)){
				this.getResponse().sendRedirect(this.getBasePath()+"mobileLoginlogin.do");
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}
		return "freemarker";
	}
	
	/**
	 * 取消绑定银行卡
	 * @return
	 */
	public String cancelBindCard() {
 		String isMobile = this.getRequest().getParameter("mobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember=mem;
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			String cardId = getRequest().getParameter("cardNoId");
			if (cardId != null && !"".equals(cardId)&& !"undefined".equals(cardId)) {
				WebBankcard card = webBankCardService.get(Long.valueOf(cardId));
				if (card != null) {
						if (mem.getThirdPayFlagId() == null) {
							webMsgInstance("0", Constants.CODE_FAILED,"请先开通第三方支付", "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						} else {
							CommonRequst commonRequst =new CommonRequst();
							String orderNum=ContextUtil.createRuestNumber();//流水号
							if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
								commonRequst.setIsMobile("1");//判断手机端操作
							}
							commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
							commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
							commonRequst.setBankCardNumber(card.getCardNum());
							commonRequst.setRequsetNo(orderNum);

							commonRequst.setTransferName(ThirdPayConstants.TN_CANCELCARD);//业务名称
							commonRequst.setBussinessType(ThirdPayConstants.BT_CANCELCARD);//业务类型
							card.setRequestNo(orderNum);//解绑卡收到回调的时候需要根据此流水号来查询卡
							CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
							if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
//								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
//								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
								if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
									this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
								}else{
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								}
								return "freemarker";
							}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
//								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
//								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							}  else {
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "","", "");
							}
						}
				} else {
					webMsgInstance("0", Constants.CODE_FAILED, "选择的银行卡不存在", "","", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			} else {
				webMsgInstance("0", Constants.CODE_FAILED, "请先选择要取消绑定的银行卡", "","", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}


	/**
	 *企业取消绑定银行卡
	 * /user/card/cancelCardBind
	 * @auther: XinFang
	 * @date: 2018/6/14 13:58
	 */
	public String cancelCardBind() {
		String isMobile = this.getRequest().getParameter("mobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember=mem;
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			String cardId = getRequest().getParameter("cardNoId");
			if (cardId != null && !"".equals(cardId)&& !"undefined".equals(cardId)) {
				WebBankcard card = webBankCardService.get(Long.valueOf(cardId));
				if (card != null) {
					if (mem.getThirdPayFlagId() == null) {
						webMsgInstance("0", Constants.CODE_FAILED,"请先开通第三方支付", "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					} else {
						CommonRequst commonRequst =new CommonRequst();
						String orderNum=ContextUtil.createRuestNumber();//流水号
						if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
							commonRequst.setIsMobile("1");//判断手机端操作
						}
						commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
						commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
						commonRequst.setBankCardNumber(card.getCardNum());
						commonRequst.setRequsetNo(orderNum);

						commonRequst.setTransferName(ThirdPayConstants.TN_CANCELCARDBIND);//业务名称
						commonRequst.setBussinessType(ThirdPayConstants.BT_CANCELCARDBIND);//业务类型
						card.setRequestNo(orderNum);//解绑卡收到回调的时候需要根据此流水号来查询卡
						CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
						if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
//								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
//								webBankCardService.save(card);
							webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
							if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
								this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
							}else{
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
							return "freemarker";
						}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
//								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
//								webBankCardService.save(card);
							webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							return "freemarker";
						}  else {
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "","", "");
						}
					}
				} else {
					webMsgInstance("0", Constants.CODE_FAILED, "选择的银行卡不存在", "","", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			} else {
				webMsgInstance("0", Constants.CODE_FAILED, "请先选择要取消绑定的银行卡", "","", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}




	/**
	 * 取消绑定银行卡保留方法
	 * @return
	 */
	public String cancelBindCardOld() {
		String isMobile = this.getRequest().getParameter("mobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember=mem;
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			String cardId = getRequest().getParameter("cardNoId");
			if (cardId != null && !"".equals(cardId)&& !"undefined".equals(cardId)) {
				WebBankcard card = webBankCardService.get(Long.valueOf(cardId));
				if (card != null) {
						if (mem.getThirdPayFlagId() == null) {
							webMsgInstance("0", Constants.CODE_FAILED,"请先开通第三方支付", "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						} else {
							CommonRequst commonRequst =new CommonRequst();
							if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
								commonRequst.setIsMobile("1");//判断手机端操作
							}
							commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方账号
							commonRequst.setBankCardNumber(card.getCardNum());
							commonRequst.setRequsetNo(card.getRequestNo()+ "-Cancel");//请求流水号
					
							commonRequst.setTransferName(ThirdPayConstants.TN_CANCELCARD);//业务名称
							commonRequst.setBussinessType(ThirdPayConstants.BT_CANCELCARD);//业务类型
							CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
							if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
								if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
									this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
								}else{
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								}
								return "freemarker";
							}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
								card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
								webBankCardService.save(card);
								webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							}  else {
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "","", "");
							}
						}
				} else {
					webMsgInstance("0", Constants.CODE_FAILED, "选择的银行卡不存在", "","", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			} else {
				webMsgInstance("0", Constants.CODE_FAILED, "请先选择要取消绑定的银行卡", "","", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	//测试请求地址：http://localhost:8130/interfacep2p4.0.8.2/pay/replaceBindCardPay.do
	// 更换银行卡
	public String replaceBindCard() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember=mem;
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			if (mem.getThirdPayFlagId() == null) {
				webMsgInstance("0", Constants.CODE_FAILED,"请先开通第三方支付", "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE)
						.getTemplateFilePath());
			} else {
				WebBankcard webBankcard = new WebBankcard();
				webBankcard.setThirdConfig("umpayConfig");
				webBankcard.setUserFlg(mem.getThirdPayFlagId());
				webBankcard.setCardNum("6214830107767955");//6222021221213452426   ,6214830107767955
				webBankcard.setUsername("黄国辉");
				webBankcard.setAccountname("黄国辉");
				webBankcard.setBindCardStatus(WebBankcard.BINDCARD_STATUS_REPARE);
				
				String orderNum =ContextUtil.createRuestNumber();//生成第三需要的流水号
				CommonRequst commonRequst =new CommonRequst();
				commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方账号
				commonRequst.setRequsetNo(orderNum);//请求流水号
				commonRequst.setTransactionTime(new Date());//商户订单日期
				commonRequst.setTrueName(mem.getTruename());//开户名称
				commonRequst.setCardNumber(mem.getCardcode());//身份证号
				commonRequst.setNewBankCardNumber(webBankcard.getCardNum());
				commonRequst.setBussinessType(ThirdPayConstants.BT_REPLACECARD);//业务类型
				commonRequst.setTransferName(ThirdPayConstants.TN_REPLACECARD);//业务名称
				CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
				
				if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
					webBankcard.setBindCardStatus(WebBankcard.BINDCARD_STATUS_ACCEPT);
					webBankcard.setRequestNo(orderNum);
					webBankcard.setCustomerId(mem.getId());
					webBankCardService.save(webBankcard);
					webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(), "", "", "","", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
							DynamicConfig.MESSAGE).getTemplateFilePath());
					
					return "freemarker";
				}else {
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
							DynamicConfig.MESSAGE).getTemplateFilePath());
					webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "",
							"", "");
				}
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	/**
	 * 检查p2p用户是否能进行第三方支付开通操作
	 * @param mem  传入开通用户信息
	 * @return object[2]:Object[0] true 可以进行开通  false 不可以开通   ；object[1] 中文描述
	 */
	public Object[] checkRegisterCondition(BpCustMember mem){
		Object[] ret= new Object[2];
		try {
				BpCustMember checkMemCardCode = bpCustMemberService.isExistCode(cardcode, mem.getId());
				if (checkMemCardCode != null) {
					ret[0]=false;
					ret[1]="该证件号码已经认证过了，请换一个证件号码(身份证/组织机构号码)";
				}else{
					
						if(mem.getThirdPayFlagId()!=null&&!"".equals(mem.getThirdPayFlagId())){
							ret[0]=false;
							ret[1]="该用户已经完成了第三方支付账户开通操作，不需要再次开通";
						}else{
							//判断身份证号码
							String strIdCard="";
							if(mem.getCustomerType()==0){
								CardValidate cardVal= new CardValidate();
								 strIdCard = cardVal.IDCardValidate(cardcode);
							}else{
								strIdCard="true";
							}
							//判断真实姓名
							boolean checkname = true;
							/* String regex = "[\u4E00-\u9FA5]+";
							 if(!truename.matches(regex)){
								 checkname=false;
							 } 	*/	
							if(strIdCard.equals("true")&&checkname){
								ret[0]=true;
								ret[1]="可以进行第三方开通操作";
							}else{
								ret[0]=false;
								ret[1]=strIdCard;
							}
						}
				}
		} catch (Exception e) {
			e.printStackTrace();
			ret[0]=false;
			ret[1]="系统出错了，请联系管理员";
		}
		return ret;
	}
	/**
	 * 开通第三方支付统一接口（调用）
	 * @return
	 */
	public String registerAndBind() {
		trimParams();
		String isMobile = this.getRequest().getParameter("isMobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		String[] ret = new String[2];
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember=mem;
		Object[] userCondition=bpCustMemberService.checkUserCondition(mem);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		Boolean flag=true;
		try {
            if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
                truename = new String(truename.getBytes("iso-8859-1"),"utf-8");//真实姓名
            }
			if((Boolean) userCondition[0]){//检查用户基本资格
				mem = bpCustMemberService.get(mem.getId());
				if(threeCard!=null&&!threeCard.equals("")&&threeCard.equals("1")){
					cardcode=threeCardCode;
					mem.setCardcode(cardcode);
				}else{
					mem.setCardcode(cardcode);
				}
				Object[] checkstatus=this.checkRegisterCondition(mem);
				if(checkstatus[0].equals(true)){
						/*if(email!=null&&!"".equals(email)){
							email = email.trim();
							BpCustMember mem1 = bpCustMemberService.getByEmail(email);
							if(mem1!=null*//*&&mem1.getIsCheckEmail()!=null&&mem1.getIsCheckEmail().equals("1")*//*){
								webMsgInstance("0",Constants.CODE_FAILED,"邮箱已存在","","","","","");
								flag=false;
							}
							mem.setEmail(email);
						}*/
						if(flag){
							mem.setTruename(truename);
							CommonRequst commonRequst=new CommonRequst();
							String requestNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
							System.out.println("orderLenth--"+requestNo.length());
							if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//企业用户
								mem.setBankLicense(bankLicense);
								mem.setBusinessLicense(businessLicense);
								mem.setTaxNo(taxNo);
								mem.setLegalPerson(legalPerson);
								mem.setLegalNo(legalNo);
								mem.setContactPerson(contactPerson);
								
								if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
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
								if(!threeCard.equals("")&&threeCard!=null&&threeCard.equals("0")){//非三证合一
									threeCardCode = null;
								}
								commonRequst.setThreeCardCode(threeCardCode);//社会信用代码
								commonRequst.setTaxNo(taxNo);//税务登记号 
								commonRequst.setLegal(legalPerson);//法人姓名
								commonRequst.setLegalIdNo(legalNo);//法人身份证号码
								commonRequst.setContact(contactPerson);//联系人
								commonRequst.setContactPhone(telphone);//联系人电话
								commonRequst.setTelephone(telphone);
								/*commonRequst.setEmail(email);*///邮箱
								commonRequst.setGuarType(guarType);//开户类型，1借款企业，2担保公司 3,出借企业
//								commonRequst.setThirdPayConfigId(mem.getCardcode()+"-"+mem.getId()+"-0");//第三方账号
								commonRequst.setCustMemberId(mem.getId().toString());
								commonRequst.setLoginname(mem.getLoginname());//登录名
								commonRequst.setRegisterType(2);//注册类型1表示全自动，2表示半自动
								commonRequst.setAccountType(1);//账户类型，空表示个人账户，1表示企业账户
								BpCustRelation r = bpCustRelationService.getP2pCustById(mem.getId());
								commonRequst.setTransferName(ThirdPayConstants.TN_EREGISTER);//企业用户开通第三方transerfername
								commonRequst.setBussinessType(ThirdPayConstants.BT_EREGISTER);//企业用户开通第三方
								commonRequst.setMemberClassType("ENTERPRISE");
								if(null!=mem.getEntCompanyType() && mem.getEntCompanyType()==1 ){//标识为企业担保户，针对的易宝
									commonRequst.setMemberClassType("GUARANTEE_CORP");	
									}
									commonRequst.setRequsetNo(requestNo);//流水号
									commonRequst.setEnterpriseName(truename);//企业用户名
									commonRequst.setTrueName(truename);//企业用户名
									
//									commonRequst.setTrueName(contactPerson);//企业用户名
									
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
									/*commonRequst.setEmail(email);*///邮箱
//									commonRequst.setThirdPayConfigId(cardcode+"-"+mem.getId()+"-0");//第三方账号
									commonRequst.setCustMemberId(mem.getId().toString());
									commonRequst.setLoginname(mem.getLoginname());//登录名
									commonRequst.setRegisterType(2);//注册类型1表示全自动，2表示半自动
									commonRequst.setAccountType(1);//账户类型，空表示个人账户，1表示企业账户
									commonRequst.setTransferName(ThirdPayConstants.TN_EREGISTER);//企业用户开通第三方transerfername
									commonRequst.setBussinessType(ThirdPayConstants.BT_EREGISTER);//企业用户开通第三方 
									commonRequst.setMemberClassType("ENTERPRISE");
									if(null!=mem.getEntCompanyType() && mem.getEntCompanyType()==1 ){//标识为企业担保户，针对的易宝
										commonRequst.setMemberClassType("GUARANTEE_CORP");	
									}
								}else{
									bpCustMemberService.merge(mem);

									commonRequst.setTrueName(truename);//真实姓名

									commonRequst.setAccountType(0);
									commonRequst.setRequsetNo(requestNo);//流水号
								//
									commonRequst.setCardNumber(cardcode);//身份证号
									commonRequst.setTelephone(mem.getTelphone());//手机号
//									commonRequst.setEmail(email);//邮箱
//									commonRequst.setThirdPayConfigId(cardcode+"-"+mem.getId()+"-0");//第三方账号
									commonRequst.setGuarType(FuDian.ROLE_TYPE3);
									commonRequst.setCustMemberId(mem.getId().toString());
									commonRequst.setLoginname(mem.getLoginname());//登录名
//									commonRequst.setRegisterType(2);//注册类型1表示全自动，2表示半自动
									commonRequst.setAccountType(null);//账户类型，空表示个人账户，1表示企业账户
									commonRequst.setTransferName(ThirdPayConstants.TN_PREGISTER);//业务类型 个人开通第三方transferName
									commonRequst.setBussinessType(ThirdPayConstants.BT_PREGISTER);//个人开通第三方
								}
								CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
								if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
									/*mem.setEmail(email);*/
									//

                                        mem.setTruename(truename);//真实姓名

									mem.setCardcode(cardcode);
									mem.setIsCheckCard("2");//开通三方账户中
									bpCustMemberService.merge(mem);
									webMsgInstance("0", Constants.CODE_SUCCESS,"开通第三方支付申请成功", "", "", "", "", "");
								}else if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
									bpCustMemberService.merge(mem);
									mem = bpCustMemberService.get(mem.getId());
									/*mem.setEmail(email);*/


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
									mapMessage.put("userId",bpCustMember.getId().toString());
									mapMessage.put("${name}", bpCustMember.getLoginname());
									String result =  sendMesService.sendSmsEmailMessage(mapMessage);
									//String[] a =obSystemAccountService.saveAccount("1", bpCustMember.getTruename(), bpCustMember.getId().toString(), bpCustMember.getCardcode(), "0", "0");
									webMsgInstance("0", Constants.CODE_SUCCESS,"开通第三方支付成功", "", "", "", "", "");
								}else{
									webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
								}
							}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, checkstatus[1].toString(), "", "", "", "", "");
				}
			}else{
				forwardPage=userCondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, userCondition[1].toString(),"", "", "", "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "开通第三方账户错误，请联系管理员","", "", "", "", "");
		}
		//更新缓存
		this.getRequest().getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);
		this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, mem);//将用户信息保存在session中
		if(isMobile != null && !"".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}

	/**
	 * 企业实名认证
	 * 20180627 行方修改
	 * @return
	 */
	public String thirdAndBindBud() {
		String isMobile = this.getRequest().getParameter("isMobile");
		String backpath = this.getRequest().getParameter("backpath");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			//检查用户基本资格
			Object[] userCondition=bpCustMemberService.checkUserCondition(mem);
			if((Boolean) userCondition[0]){
				CommonRequst commonRequst=new CommonRequst();
				String requestNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
				commonRequst.setRequsetNo(requestNo);//流水号
				commonRequst.setCustMemberId(mem.getId().toString());
				commonRequst.setLoginname(mem.getLoginname());//登录名
				commonRequst.setAccountType(1);//账户类型，空表示个人账户，1表示企业账户
				commonRequst.setMemberClassType("ENTERPRISE");
				commonRequst.setTransferName(ThirdPayConstants.TN_EREGISTER);//企业用户开通第三方transerfername
				commonRequst.setBussinessType(ThirdPayConstants.BT_EREGISTER);//企业用户开通第三方
				CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
				if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
					mem.setIsCheckCard("2");//开通三方账户中
					bpCustMemberService.merge(bpCustMember);
					webMsgInstance("0", Constants.CODE_SUCCESS,"开通第三方支付申请成功", "", "", "", "", "");
				}else if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					webMsgInstance("0", Constants.CODE_SUCCESS,"开通第三方支付成功", "", "", "", "", "");
				}else{
					mem.setIsCheckCard("0");
					bpCustMemberService.merge(bpCustMember);
					webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(), "", "", "", "", "");
				}
			}else {
				forwardPage=userCondition[2].toString();
				this.getSession().setAttribute("retUrl","/htmllogin.do");
				webMsgInstance("0", Constants.CODE_FAILED, userCondition[1].toString(),"", "", "", "", "");
			}
		}else {

			webMsgInstance("0", Constants.CODE_FAILED, "您还没有登陆","", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		return "freemarker";
	}

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
	 * 充值统一接口
	 */
	public String recharge() {
		String isMobile =this.getRequest().getParameter("isMobile");
		String payType =this.getRequest().getParameter("payType");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
//		String isMobile ="1";
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(null !=mem && !"".equals(mem)){
			mem=bpCustMemberService.get(mem.getId());
			bpCustMember = mem;
		}
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		//查询用户是否绑卡
		List<WebBankcard> banks = webBankcardService.getBycustAndState(mem.getId(),"bindCard_status_success");
		if (banks!= null && banks.size() > 0) {
			try{
				if((Boolean) usercondition[0]){//验证是否 具备交易资格
					if(amount!=null&&new BigDecimal(amount).compareTo(new BigDecimal(0))>0){//充值金额不能为空和大于0元
						String[] dealToErpRetArr = new String[2];
						// 交易流水号
						String orderNum = ContextUtil.createRuestNumber();//"hry"+
						// 保存充值交易记录
						Map<String, Object> mapO = new HashMap<String, Object>();
						mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
						mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
						mapO.put("transferType", ObAccountDealInfo.T_RECHARGE);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)（必填）
						mapO.put("money", new BigDecimal(amount));// 交易金额 （必填）
						mapO.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapO.put("dealType", ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
						mapO.put("dealStatus", ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3支付失败。。。(参见ObAccountDealInfo中的常量)(必填)
						dealToErpRetArr = obAccountDealInfoService.operateAcountInfo(mapO);
						if (dealToErpRetArr != null	&& dealToErpRetArr[0].equals(Constants.SUCCESSFLAG)) {
						    CommonRequst common =new CommonRequst();
						    if(isMobile!=null&&!"".equals(isMobile)&&("1").equals(isMobile)){
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
							if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//企业用户
    							common.setAccountType(1);//1代表企业账户 0代表个人账户
							}else{//个人用户充值
								common.setAccountType(0);//1代表企业账户 0代表个人账户
							}
							//支付方式 （个人、企业都可）
							/*
							 * 1:快捷充值
							 * 3:网关充值（暂不可用）    注：2017-12-26三方让修改为6
							 * 5:银行直连（绑定富滇银行卡，只能走网关充值及银行直连）
							 */
							common.setBankFastPay(payType);
							CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);
							if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
								webMsgInstance("0", Constants.CODE_SUCCESS, "充值申请成功","", "", "", "", "");
							}else{
								webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(),"", "", "", "", "");
							}
	
						} else {
							webMsgInstance("0", dealToErpRetArr[0],dealToErpRetArr[1], "", "", "", "", "");
						}
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "操作错误，充值金额不能为空或应大于0元","", "", "", "", "");
					}
				}else{
					forwardPage=usercondition[2].toString();
					webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				}
			}catch(Exception e){
				e.printStackTrace();
					webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");

			}
		}else {
			webMsgInstance("0", Constants.CODE_FAILED, "请先绑卡","", "", "", "", "");
		}
		if(isMobile != null && !"".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}

	public String delAccount() {
		iPayService.delAccount(AppUtil.getSysConfig().get(
				"MM_PlatformMoneymoremore").toString());
		return SUCCESS;
	}

	/**
	 * 余额查询 统一接口
	 */
	public String balanceQuery() {
		String retdata = "";
		String[] ret = null;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			if (configMap.get("thirdPayConfig").toString().equals(
					Constants.MONEYMOREMORE)) {
				// 传入用户钱多多标识 即可 查询
				retdata = iPayService.balanceQueryMoneys(mem
						.getThirdPayFlagId(), MoneyMoreMore.TTYPE_1);
			} else if (configMap.get("thirdPayConfig").toString().equals(
					Constants.HUIFU)) {
				ret = huiFuService.balanceQuery(this.getResponse(), mem,
						"QueryBalanceBg");
			}

			System.out.println(ret[0] + "===" + ret[1]);

		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}

		setJsonString(ret[0] + "===" + ret[1]);
		return "freemarker";
	}

	/**
	 * 钱多多 注册绑定接口
	 */
	private void moreRegister() {
		if ("".equals(email) || email == null) {
			email = "";
		}
		moneyMoreMore = new MoneyMoreMore();
		moneyMoreMore.setLoanPlatformAccount(loginname);
		moneyMoreMore.setRegisterType(MoneyMoreMore.RTYPE_2);
		moneyMoreMore.setMobile(telphone);
		moneyMoreMore.setEmail(email);
		moneyMoreMore.setRealName(truename);
		moneyMoreMore.setIdentificationNo(cardcode);
		StringBuffer sb = new StringBuffer();
		// 全自动
		if (moneyMoreMore.getRegisterType().equals(MoneyMoreMore.RTYPE_1)) {
			ResultBean retBean = iPayService.registerAndBind(moneyMoreMore,
					getBasePath());

			Gson gson = new GsonBuilder().create();
			if (retBean != null && retBean.getResultCode().equals("88")) {
				BpCustMember bcut = bpCustMemberService.get(loginId);
				bcut.setTruename(truename);
				bcut.setCardcode(cardcode);
				bcut.setThirdPayFlagId(retBean.getMoneymoremoreId());
				bpCustMemberService.save(bcut);
			}
			sb.append(gson.toJson(retBean));

			setJsonString(sb.toString());
			System.out.println(sb.toString());
		} else { // 半自动
			iPayService.registerAndBind(moneyMoreMore, getBasePath(), this
					.getResponse());
			webMsgInstance("0", Constants.CODE_SUCCESS, "", "", "", "", "", "");
			setJsonString(sb.toString());
		}
	}

	/**
	 * 钱多多 充值
	 * 
	 * @param mem
	 * @return
	 */
	private String moreRechare(BpCustMember mem, String orderNum) {
		String retdata = "";
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			System.out.println("=====" + mem.getThirdPayFlagId());
			try {
				if (moneyMoreMore == null) {
					moneyMoreMore = new MoneyMoreMore();
				}
				moneyMoreMore.setOrderNo(orderNum);// 自动生成平台订单号
				if (amount != null) {
					moneyMoreMore.setAmount(amount);// 充值金额
				}
				moneyMoreMore.setRechargeMoneymoremore(mem.getThirdPayFlagId());// 要充值的账号的乾多多标识
				retdata = iPayService.recharge(moneyMoreMore, this
						.getResponse(), getBasePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}

		return "freemarker";
	}
	/**
	 * 汇付取现方法
	 * 
	 * @return
	 */
	public void webWithdraw() {
		String isMobile = this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			try {

				mem = bpCustMemberService.get(mem.getId());
				// 虚拟账户余额
				mem = obSystemAccountService.getAccountSumMoney(mem);
				// String ServFee="";
				// 服务费=（取现金额*费率/100）
				// String ServFee= ServFee(amount,config.getPoundage());
				String mixcash = configMap.get("mixcash").toString();
				String ServFee = ServFee(amount, configMap.get("poundage")
						.toString());
				// String huiFuFeeNumber =
				// sysConfigService.findByKey("huiFuFeeNumber").getDataValue();
				String huiFuFeeNumber = configMap.get("feeNumber").toString();
				String ServFeeAcctId = huiFuFeeNumber;
				WebBankcard bankCard = webBankCardService.get(Long
						.valueOf(cardNo));
				String OpenAcctId = bankCard.getCardNum();
				String Remark = "";
				String ReqExt = "";

				/*
				 * if(new BigDecimal(ServFee).compareTo(new
				 * BigDecimal(mixcash))<0){ ServFee=mixcash; }
				 */
				// 取现金额 =取现金额 -（服务费）
				amount = new BigDecimal(amount).subtract(
						new BigDecimal(ServFee)).toString();
				// 订单号
				String orderNum = Common.getRandomNum(3)
						+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
				if (mem.getAvailableInvestMoney() == null
						|| mem.getAvailableInvestMoney().compareTo(
								new BigDecimal(amount).add(new BigDecimal(
										ServFee))) < 0) {
					webMsgInstance("0", Constants.CODE_FAILED, "可提现金额不足", "",
							"", "", "", "");
				} else {
					huiFuService.webWithdraw(this.getResponse(), "Cash",
							orderNum, mem.getThirdPayFlagId(), amount, ServFee,
							ServFeeAcctId,
							OpenAcctId == null ? "" : OpenAcctId, Remark,
							ReqExt, Constants.CHAR_UTF, "12000", this
									.getBasePath());
					webMsgInstance("0", Constants.CODE_SUCCESS, "", "", "", "",
							"", "");
				}
			} catch (Exception e) {
				e.printStackTrace();
				webMsgInstance("0", Constants.CODE_FAILED, "提现失败", "", "", "",
						"", "");

			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		// return "freemarker";
	}

	/**
	 * 提现申请接口
	 */
	public String withdraws() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String isMobile = this.getRequest().getParameter("isMobile");//判断是否是手机端的操作
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		mem=bpCustMemberService.get(mem.getId());
		bpCustMember=mem;
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		CommonResponse commonResponse=new CommonResponse();
		String orderNum =ContextUtil.createRuestNumber();
		CommonRequst commonRequst= new CommonRequst();
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try {
			if((Boolean) usercondition[0]){//验证是否 具备交易资格
				if(cardNo!=null&&!cardNo.equals("")){
					cardNo = StringUtil.html2Text(cardNo);
					if (mem.getThirdPayFlagId() != null&& !mem.getThirdPayFlagId().equals("")) {
						//实时计算可提现金额
						BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(mem.getId(),ObSystemAccount.type0.toString());
						mem.setAvailableInvestMoney(ret[3]);
						//查询三方获取可取现余额
						Map<String, String> memberMoney = bpCustMemberService.queryThirdPayCustomerInfo(mem);
						if (mem.getAvailableInvestMoney() == null|| mem.getAvailableInvestMoney().compareTo(new BigDecimal(amount)) < 0) {
							webMsgInstance("0",CommonResponse.RESPONSECODE_FAILD,"可提现金额不足", "", "", "", "", "");
						
						//如果三方返回的可提现余额大于取现金额
						} else if(StringUtils.isNotEmpty(memberMoney.get("withdrawBalance")) && new BigDecimal(memberMoney.get("withdrawBalance")).compareTo(new BigDecimal(amount)) < 0){
							webMsgInstance("0",CommonResponse.RESPONSECODE_FAILD,"可提现金额不足", "", "", "", "", "");
						}else {
							mem = obSystemAccountService.getAccountSumMoney(mem);
							WebBankcard card = webBankCardService.get(Long.valueOf(cardNo));
							Date date = new Date();
							if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
								commonRequst.setIsMobile("1");//手机端操作
							}
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
							if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户提现
								if(cardNo!=null){//判断是否绑定银行卡   针对于个人用户
										commonRequst.setAccountType(0);//1代表企业账户 0代表个人账户
										commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
										if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
											Map<String, Object> mapO = new HashMap<String, Object>();
											mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
											mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
											mapO.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
											mapO.put("money", new BigDecimal(amount));// 交易金额
											mapO.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
											mapO.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
											mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
											if(commonResponse.getDealState()!=null&&!commonResponse.getDealState().equals("")){
												mapO.put("bankId",cardNo);
												mapO.put("dealStatus",commonResponse.getDealState());// 资金交易状态：1等待支付，2支付成功，3
											}else{
												mapO.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
											}
											String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
											this.getRequest().setAttribute("str",commonResponse.getResponseMsg());
												webMsgInstance("0", Constants.SUCCESSFLAG,"提现申请成功", "", "", "", "", "");
										} else {
											webMsgInstance("0", Constants.CODE_FAILED,commonResponse.getResponseMsg(), "", "", "", "", "");
										}
								}else{
									webMsgInstance("0", Constants.CODE_FAILED,"请绑定银行卡", "", "", "", "", "");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						} else {//企业用户
					            commonRequst.setAccountType(1);//判断是企业用户还是个人用户
								commonResponse = ThirdPayInterfaceUtil.thirdCommon(commonRequst);
								if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//直连的接口
									Map<String, Object> mapO = new HashMap<String, Object>();
									mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
									mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
									mapO.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
									mapO.put("money", new BigDecimal(amount));// 交易金额
									mapO.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapO.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
									if(commonResponse.getDealState()!=null&&!commonResponse.getDealState().equals("")){
										mapO.put("dealStatus",commonResponse.getDealState());// 资金交易状态：1等待支付，2支付成功，3
									}else{
										mapO.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
									}
									String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
									webMsgInstance("1", CommonResponse.RESPONSECODE_SUCCESS,commonResponse.getResponseMsg(), "", "", "", "", "");
								}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
									Map<String, Object> mapO = new HashMap<String, Object>();
									mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
									mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
									mapO.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
									mapO.put("money", new BigDecimal(amount));// 交易金额
									mapO.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapO.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapO.put("recordNumber", orderNum);// 交易流水号 （必填）
									mapO.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_1);// 资金交易状态：1等待支付，2支付成功，3
									String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
									webMsgInstance("0", Constants.CODE_SUCCESS,commonResponse.getResponseMsg(), "", "", "", "", "");
								}else{
										webMsgInstance("0", Constants.CODE_FAILED,commonResponse.getResponseMsg(), "", "", "", "", "");
								}
									webMsgInstance("0", Constants.CODE_FAILED,commonResponse.getResponseMsg(), "", "", "", "", "");
								}
							}
						}
						
				    }else{
					    webMsgInstance("0", Constants.CODE_FAILED,"请先绑定提现银行卡", "", "", "", "", "");
				}
			}else{
				 webMsgInstance("0", Constants.CODE_FAILED,usercondition[1].toString(), "", "", "", "", "");
			}
		}catch(Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误:提现申请失败,请联系管理员", "", "","", "", "");
		}
		if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	
	}
	/**
	 * 通联提现
	 * 
	 * @param orderNo
	 * @param card
	 */
	public String[] allinWithdraw(String orderNo,WebBankcard card) {
		//设置提交参数
		AllinPay allin = new AllinPay();
		allin.setACCOUNT_NO(card.getCardNum());
		allin.setACCOUNT_NAME(card.getAccountname());
		allin.setBANK_CODE(card.getUserFlg());
		//提现金额
		String withdrawMoney = amount.replace(".","");
		allin.setTOTAL_SUM(withdrawMoney);
		String[] result = new String[3];
		result = allinPayService.withdraws(allin, this.getResponse(), this.getBasePath());
		return result;
	}
	/**
	 * 通联取现查询
	 * @return
	 */
	public String queryAllinWithdraw()
	{
		String[] result = new String[3];
		String orderNum = this.getRequest().getParameter("orderNum");
		String regSn = this.getRequest().getParameter("regSn");
		System.out.println("orderNum="+orderNum);
		//获取第三方环境路径
		String URL11https= AppUtil.getSysConfig().get("DF_payUrl").toString();
		boolean isfront=false;//是否发送至前置机（由前置机进行签名）
		try {
			result = allinPayService.queryResult(regSn,isfront,URL11https);
			webMsgInstance("0", Constants.CODE_SUCCESS, "查询第三方交易：返回代码："+result[0]+",第三方交易批次号："+result[2]+",状态："+result[1]+"",  "", "", "", "", "");  
		} catch (Exception e) {

			webMsgInstance("0", Constants.CODE_FAILED, "第三方平台出错了,请联系第三方",  "", "", "", "", "");  
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			return "freemarker";
		}
		
		/*if(result[0].equals("0000")){
			//交易最终处理成功
			ObAccountDealInfo deal = obAccountDealInfoService.getByOrderNumber(orderNum, "", "", "");
			if(deal!=null){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("investPersonId", deal.getInvestPersonId());// 投资人Id（必填）
				map.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				map.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				map.put("money", new BigDecimal(deal.getPayMoney().toString()));// 交易金额
				map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
				map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
				map.put("recordNumber", orderNum);// 交易流水号 （必填）
				map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);// 资金交易状态：1等待支付，2支付成功，3
				obAccountDealInfoService.updateAcountInfo(map);
			}
		}*/
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 通联充值
	 * @param mem
	 * @param orderNo
	 * @param amount
	 * @return
	 */
	public String applinRechare(BpCustMember mem,String orderNo,String amount){
		String ret="";
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			try {
				//构造订单请求对象，生成signMsg。
				RequestOrder requestOrder = new RequestOrder();
				//充值金额
				String rechareMoney = amount.replace(".","");
				//订单提交时间
				String orderDatetime = DateUtil.dateToStr(new Date(),"yyyyMMddHHmmss");
				requestOrder.setPayerName(mem.getTruename());
				requestOrder.setOrderNo(orderNo);
				requestOrder.setOrderAmount(Long.parseLong(rechareMoney));
				requestOrder.setOrderDatetime(orderDatetime);//商户订单提交时间
				requestOrder.setExt1(mem.getId().toString());
			    ret = allinPayService.recharge(requestOrder, this.getResponse(), this.getBasePath());
//				this.getRequest().setAttribute("allin", requestOrder);
//				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
//						DynamicConfig.APPLINPAYRECHARE).getTemplateFilePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}

		return ret;
	}
	/**
	 * 易宝web取现方法
	 * 
	 * @param orderNum
	 * @param mem
	 */
	private String[] yeePpaywebWithdraw(String orderNum, BpCustMember mem) {
		String isMobile = this.getRequest().getParameter("isMobile");
		String[] returnvalue = new String[2];
		// 虚拟账户余额
		mem = obSystemAccountService.getAccountSumMoney(mem);
		// WebBankcard bankCard=webBankcardService.get(Long.valueOf(cardNo));
		// amount=new BigDecimal(amount).subtract( new
		// BigDecimal(ServFee)).toString();
		if (mem.getAvailableInvestMoney() == null
				|| mem.getAvailableInvestMoney().compareTo(
						new BigDecimal(amount)) < 0) {
			webMsgInstance("0", Constants.CODE_FAILED, "可提现金额不足", "", "", "",
					"", "");
		} else {
			/**
			 * 易宝提现 Map<String,object> map 第三方支付取现需要的map参数
			 * map.get("basePath").toString() 只当前的绝对路径
			 * map.get("platformUserNo").toString() 第三方支付账号
			 * map.get("customerId").toString();
			 * map.get("customerType").toString();
			 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
			 * map.get("paymoney").toString() 取现金额
			 */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("basePath", this.getBasePath());
			map.put("platformUserNo", mem.getThirdPayFlagId());
			map.put("customerId", mem.getId().toString());
			map.put("customerType", "0");
			map.put("requestNo", orderNum);
			map.put("paymoney", amount);
			String[] ret ;
			if (isMobile!=null &&isMobile.endsWith("1")){
				ret = yeePayService.MobiletoWithdraw(map);
			}else{
		       ret = yeePayService.toWithdraw(map);
		       }
			returnvalue = ret;
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.MESSAGE).getTemplateFilePath());
		return returnvalue;
	}
	private String[] YEEPpaywebWithdraw(String orderNum, BpCustMember mem) {
		String[] returnvalue = new String[2];
		// 虚拟账户余额
		mem = obSystemAccountService.getAccountSumMoney(mem);
		// WebBankcard bankCard=webBankcardService.get(Long.valueOf(cardNo));
		// amount=new BigDecimal(amount).subtract( new
		// BigDecimal(ServFee)).toString();
		if (mem.getAvailableInvestMoney() == null
				|| mem.getAvailableInvestMoney().compareTo(
						new BigDecimal(amount)) < 0) {
			
			webMsgInstance("0", Constants.CODE_FAILED, "可提现金额不足", "", "", "",
					"", "");

				this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		} else {
			/**
			 * 易宝提现 Map<String,object> map 第三方支付取现需要的map参数
			 * map.get("basePath").toString() 只当前的绝对路径
			 * map.get("platformUserNo").toString() 第三方支付账号
			 * map.get("customerId").toString();
			 * map.get("customerType").toString();
			 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
			 * map.get("paymoney").toString() 取现金额
			 */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("basePath", this.getBasePath());
			map.put("platformUserNo", mem.getThirdPayFlagId());
			map.put("customerId", mem.getId().toString());
			map.put("customerType", "0");
			map.put("requestNo", orderNum);
			map.put("paymoney", amount);
			String[] ret = yeePayService.MobiletoWithdraw(map);
			
			returnvalue = ret;
		}
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");

		return returnvalue;
	}

	/**
	 * 钱多多 提现
	 * 
	 * @param mem
	 * @return
	 */
	private String moreWithdraws(BpCustMember mem, WebBankcard card) {
		String retdata = "";
		String cardNo = Common.getRandomNum(3)
				+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		if (mem != null) {
			mem = bpCustMemberService.get(mem.getId());
			try {
				if (moneyMoreMore == null) {
					moneyMoreMore = new MoneyMoreMore();
				}
				moneyMoreMore.setOrderNo(cardNo);// 随机生成平台订单号
				if (amount != null) {
					moneyMoreMore.setAmount(amount);
				}
				moneyMoreMore.setFeePercent(MoneyMoreMore.FeePercentPublic);// 在配置文件里设置获取
				moneyMoreMore.setCardNo(card.getCardNum());// 银行卡号
				moneyMoreMore.setCardType("0");// 0.借记卡
				moneyMoreMore.setBankCode(card.getBankId());// 银行代码
				moneyMoreMore.setBranchBankName(card.getBranchbank());// 开户行网名称
				moneyMoreMore.setProvince(card.getProvinceId().toString());// 开户行省份
				moneyMoreMore.setCity(card.getCityId().toString());// 开户行城市
				moneyMoreMore.setWithdrawMoneymoremore(mem.getThirdPayFlagId());// 要提现的账号的乾多多标识
				moneyMoreMore.setFeeMax(MoneyMoreMore.Fee_MAX);
				retdata = iPayService.withdraws(moneyMoreMore, this
						.getResponse(), getBasePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	/**
	 * 金额 费率
	 * 
	 * @param money
	 * @param poundage
	 * @return
	 */
	private String ServFee(String money, String poundage) {
		String ServFee = "";
		BigDecimal money0 = new BigDecimal(money);
		BigDecimal poundage0 = new BigDecimal(poundage);
		ServFee = money0.multiply(poundage0).divide(new BigDecimal(100), 2,
				BigDecimal.ROUND_HALF_EVEN).toString();
		return ServFee;

	}

	/**
	 * 校验数据安全
	 * 
	 * @return
	 */
	private boolean checkSecurity() {
		boolean ret = false;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem == null) {
			ret = false;
		} else {
			mem = bpCustMemberService.get(mem.getId());
		}
		if (mem.getThirdPayFlagId() == null&& configMap.get("thirdPayType").toString().equals("0")) {
			ret=false;
		} else if (mem.getIsCheckCard() == null|| !mem.getIsCheckCard().equals("1")) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * 富有金账户浏览器端用户注册
	 * 
	 * @return
	 */
	public String getFuioureturnDate() {
		String code = this.getRequest().getParameter("resp_code");
		String user_id_from = this.getRequest().getParameter("user_id_from");
		String email = this.getRequest().getParameter("email");
		if (code.equals("0000")) {
			bpCustMember = bpCustMemberService.get(Long.valueOf(user_id_from));
			bpCustMember.setThirdPayConfig("fuiouConfig");
			bpCustMember.setThirdPayFlagId(bpCustMember.getTelphone());
			bpCustMember.setThirdPayFlag0(bpCustMember.getTelphone());
			bpCustMember.setEmail(email);
			bpCustMemberService.merge(bpCustMember);
			List<WebBankcard> Card = webBankCardService
					.getBycusterId(bpCustMember.getId());
			if (Card == null) {
				WebBankcard webcard = new WebBankcard();
				webcard.setCustomerId(bpCustMember.getId());
				webcard.setCustomerType(Short.valueOf("0"));
				String cityId = this.getRequest().getParameter("user_id_from");
				if (cityId != null) {
					webcard.setCityId(Long.valueOf(cityId));
				}
				webcard.setBankId(this.getRequest().getParameter(
						"parent_bank_id"));
				webcard.setSubbranchbank(this.getRequest().getParameter(
						"bank_nm"));
				webcard.setCardNum(this.getRequest().getParameter("capAcntNo"));
				webBankCardService.save(webcard);
			}
			webMsgInstance("0", Constants.CODE_SUCCESS, "第三方开通成功", "", "", "",
					"", "");
		} else {
			webMsgInstance("0", Constants.CODE_FAILED, "开通第三方失败,失败编码:" + code,
					"", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.MESSAGE).getTemplateFilePath());

		return "freemarker";
	}

	public void setListCsbank(List<CsBank> listCsbank) {
		this.listCsbank = listCsbank;
	}

	public List<CsBank> getListCsbank() {
		return listCsbank;
	}

	/**
	 * 借款人自助还款方法
	 * 
	 * @return
	 */
	public String repayMentByLoaner() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		String isMobile  = this.getRequest().getParameter("mobile");
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){
				mem = bpCustMemberService.get(mem.getId());
				System.out.println("还款人的手机号为======="+mem.getTelphone());
				//start 判断是否有代偿借款
				QueryFilter filter = new QueryFilter(this.getRequest());
				filter.addFilter("Q_loanerp2pId_L_EQ", mem.getId());
				filter.addFilter("Q_backStatus_N_EQ", "0");
				filter.getPagingBean().setPageSize(100000000);
				List<PlBidCompensatory> compensatoryList = plBidCompensatoryService.getAll(filter);
				//end
				if(null != compensatoryList && compensatoryList.size()>0){
					webMsgInstance("0", Constants.CODE_FAILED, "请先偿还代偿借款","", "", "", "", "");
				}else{
					mem = obSystemAccountService.getAccountSumMoney(mem);
					String repayMoney = this.getRequest().getParameter("notMoney");
					if (repayMoney != null && !"".equals(repayMoney)) {
						if (new BigDecimal(repayMoney).compareTo(new BigDecimal(0)) <= 0) {
							webMsgInstance("0", Constants.CODE_FAILED,"请先确认还款金额不能为零或负值", "", "", "", "", "");
						} else if (mem.getAvailableInvestMoney().compareTo(new BigDecimal(repayMoney)) >= 0) {
							
							//还款
							String[] ret = this.thirdPayRepayMentByLoaner(this.getRequest(), mem);
							if (ret[0].equals(Constants.CODE_SUCCESS)) {
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							} else {
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							}
						} else {
							webMsgInstance("0", Constants.CODE_FAILED,"账户可用金额不足，请先充值", "", "", "", "", "");
						}
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "还款金额不能为空","", "", "", "", "");
					}
				}
				
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			System.out.println("系统还款报错---");
			//在方法执行最后加入出队列操作
			QueueManger.QueuePoll();
//			e.printStackTrace();
			e.getMessage();
			 webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}
		if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	
	
	/**
	 * 借款人自助提前还款方法
	 * 
	 * @return
	 */

	@SuppressWarnings("unused")
	public String toEarlyRepaymentByLoaner() {
		System.out.println("借款人开始提前还款");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){
				mem = bpCustMemberService.get(mem.getId());
				mem = obSystemAccountService.getAccountSumMoney(mem);
				String repayMoney = this.getRequest().getParameter("notMoney");
				String bidPlanId = this.getRequest().getParameter("planId");
				
				//在提前还款之前先查询之前提前还款的款项是否已经偿还
				List<BpFundIntent> bplist = bpFundIntentService.getBySlEarlyRepaymentId(Long.valueOf(bidPlanId));
				String requestNo0 = "";
				if(bplist!=null&&bplist.size()>0){
					for(BpFundIntent b:bplist){
						if(b.getRequestNOLoaner()!=null&&!"".equals(b.getRequestNOLoaner())){
							requestNo0 = b.getRequestNOLoaner();
							break;
						}
					}
				}
				if(requestNo0==null||"".equals(requestNo0)){
					requestNo0 = ContextUtil.createRuestNumber();//生成第三需要的流水号
				}
				//添加队列防止并发问题
				if (requestNo0 != null) {
					QueueManger.QueueOffer(requestNo0);
				}
				String requestNo = (String) QueueManger.QueuePoll();
				//查询平台还款日志，第三方还款记录
//				boolean thirdpay = getThirdPayRecord(mem,planId,intentDate,ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);
				String intentDate = DateUtil.dateToStr(new Date(), "yyyyMMdd");
//				int thirdpay = getThirdPayLog(mem, requestNo, ThirdPayConstants.BT_BEFOREPAY, ThirdPayConstants.TN_BEFOREPAY, ThirdPayConstants.BT_BEFOREPAY+bidPlanId+intentDate);
				//根据查询状态进行相应操作
//				if(thirdpay==3){
				SlEarlyRepaymentRecord repay = slEarlyRepaymentRecordService.saveEarlyProjectInfo(Long.valueOf(bidPlanId));
				if(repay!=null){
					if (repayMoney != null && !"".equals(repayMoney)) {
						if (new BigDecimal(repayMoney).compareTo(new BigDecimal(0)) <= 0) {
							webMsgInstance("0", Constants.CODE_FAILED,"请先确认提前还款金额不能为零和负值", "", "", "", "", "");
						} else if (mem.getAvailableInvestMoney().compareTo(new BigDecimal(repayMoney)) >= 0) {
							
							//还款
							String[] ret = this.thirdPayEarlyRepayMentByLoaner(bidPlanId,(null!=repay&&!"".equals(repay))?repay.getSlEarlyRepaymentId().toString():null, mem,requestNo);
							if (ret[0].equals(Constants.CODE_SUCCESS)) {
								if(configMap.get("thirdPayType").toString().equals("0")){
									webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									return "freemarker";
								}else{
									webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									return "freemarker";
								}
							} else {
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							}
						} else {
							webMsgInstance("0", Constants.CODE_FAILED,"账户可用金额不足，请先充值", "", "", "", "", "");
						}
					} else {
						webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "保存提前还款的款项出错", "","", "", "", "");
				}
			 /* }else{
				  webMsgInstance("0", Constants.CODE_FAILED, "您存在提前还款记录,如有问题请联系客服！","", "", "", "", "");
			  }*/
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "提前还款出错，请联系管理员","", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				forwardPage).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 借款客户自助提前还款数据操作
	 * 
	 * @param planId
	 * @param mem
	 * @return
	 */
	@SuppressWarnings("unused")
	private String[] thirdPayEarlyRepayMentByLoaner(String planId,String slEarlyPayId,BpCustMember mem,String requestNo) {
		String [] ret=new String[2];
//		String cardNo=ContextUtil.createRuestNumber();//流水号
		String isMobile = this.getRequest().getParameter("mobile");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		//获得未对账的款项
		List<BpFundIntent> list = bpFundIntentService.getUnCheckIntent(planId);
		//提前还款的台账
		List<BpFundIntent> list2 = bpFundIntentService.getBySlEarlyId(slEarlyPayId);
 		if(planId!=null&&!"".equals(planId)&&slEarlyPayId!=null&&!"".equals(slEarlyPayId)){
 			PlBidPlan bidPlan = plBidPlanService.get(Long.valueOf(planId));
			List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepayEarlymentList(planId,slEarlyPayId);
			BigDecimal repayMoney =   bpFundIntentService.getFinancialMoney(Long.valueOf(planId), "all");//标的还款总金额 this.getRequest().getParameter("notMoney");
			BigDecimal bidTotalMoney = BigDecimal.ZERO;
			String money = this.getRequest().getParameter("notMoney");
			if(money!=null&&!"".equals(money)){
				bidTotalMoney = new BigDecimal(money);
			}
			Date intentDate2=new Date();
			String intentDate3 = "";
			String peridId = "";
			String slEarlyRepaymentId = "";
			if(list2.size()>0){
				intentDate2 = list2.get(0).getIntentDate();
				intentDate3 = sdf.format(intentDate2);
				peridId = list2.get(0).getPayintentPeriod().toString();
				slEarlyRepaymentId = list2.get(0).getSlEarlyRepaymentId().toString();
			}
			BigDecimal capital = BigDecimal.ZERO;//还款本金
			BigDecimal interest = BigDecimal.ZERO;//应还利息
			BigDecimal accrual = BigDecimal.ZERO;//罚息
			//平台应收费用
			BigDecimal Fee = new BigDecimal(0);
			for(BpFundIntent intent:list2){
				if(intent.getFundType()!=null&&("serviceMoney".equals(intent.getFundType())||"consultationMoney".equals(intent.getFundType()))){
					Fee = Fee.add(intent.getNotMoney());
				}else if(intent.getFundType()!=null&&intent.getFundType().equals("principalRepayment")) {
					capital = capital.add(intent.getNotMoney());
				}else if(intent.getFundType()!=null&&intent.getFundType().equals("loanInterest")) {
					interest = interest.add(intent.getNotMoney());
				}else if(intent.getFundType()!=null&&intent.getFundType().equals("couponInterest")) {
					accrual = accrual.add(intent.getNotMoney());
				}
				intent.setRequestNo(requestNo);
				intent.setRequestNOLoaner(requestNo);
				bpFundIntentService.save(intent);
			}
			
			String intentDate =sdf1.format(new Date());
			//查询平台还款日志，第三方还款记录
			//查询还款的记录
//			boolean thirdpay = getThirdPayRecord(mem,planId,intentDate,ThirdPayConstants.BT_BEFOREPAY+planId.toString()+intentDate);
		    	if(repayList.size()>0){
					try{
						StringBuffer buff = new StringBuffer();
						//还款之前先更改标的状态为还款中
						CommonRequst common = new CommonRequst();
    					common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
    					common.setBidId(planId.toString());
    					common.setBidIdStatus("2");//更新标的状态为还款中
    					CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
    					//更改标的状态成功
    					if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
    						if(list.size()>0){
    							if(list.get(0).getLoanerRepayMentStatus()!=null){
    								ret[0] =Constants.CODE_FAILED;
	    							ret[1] ="该业务已经处理过，请勿重复请求";
    							}else{
    								//开始调用提前还款的方法
    	    						CommonRequst cq=new CommonRequst();
    	    						if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
    	    							cq.setIsMobile("1");//手机端操作
    	    						}
    	    						
    	    						cq.setThirdPayConfigId(mem.getThirdPayFlagId());
    	    						cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
    	    						cq.setRequsetNo(requestNo);//请求流水号
    	    						cq.setRepaymemntList(repayList);//还款list
    	    						cq.setPrincipalAmt(capital.toString());//本金
    	    						cq.setInterestAmt((interest.add(accrual)).setScale(2,BigDecimal.ROUND_HALF_UP).toString());//利息=利息+罚息
    	    						cq.setLoanFee(Fee.setScale(2,BigDecimal.ROUND_HALF_UP).toString());//管理费
    	    						cq.setLoanNoList(bidPlan.getLoanTxNo());//还款标的标识
	    			    			cq.setBidId(planId.toString());//标id
	    			    			
	    			    			
	    			    			cq.setPlanMoney(repayMoney);//标的还款金额
	    			    			cq.setIntentDate(repayList.get(0).getIntentDate());//计划还款日期
	    			    			cq.setUniqueId(ThirdPayConstants.BT_BEFOREPAY+planId.toString()+intentDate);//还款唯一标识
	    			    			cq.setBussinessType(ThirdPayConstants.BT_BEFOREPAY);//业务类型
	    			    			cq.setTransferName(ThirdPayConstants.TN_BEFOREPAY);//业务名称
	    			    			cq.setCustMemberId(mem.getId().toString());//用户id
	    			    			cq.setLoginname(mem.getLoginname());//登录名
	    			    			cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
	    			    			cq.setAmount(bidTotalMoney);//提前还款的金额
	    			    			cq.setProId(CommonRequst.HRY_BID+planId.toString());
	    			    			cq.setFeeObjFlag("O");//向出账人收取费用
	    			    			cq.setDzObject("");//担保代偿机构
	    			    			cq.setReqExt("");//f
	    			    			cq.setOrderDate(sdf1.format(new Date()));//请求日期
	    			    			cq.setSlEarlyRepaymentId(Long.valueOf(slEarlyPayId));
	    			    			cq.setTransactionTime(new Date());
	    			    			cq.setContractNo("");//预授权合同号
	    			    			cq.setRemark1(slEarlyPayId);
	    			    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
	    							if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
	    								if (cr!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						    				ret[0] =Constants.CODE_SUCCESS;
											ret[1] =cr.getResponseMsg();
										}else {
											ret[0] = Constants.CODE_FAILED;
											ret[1] = "还款失败";
										}
									}else {
										ret[0] = Constants.CODE_FAILED;
										ret[1] = "还款失败";
									}
    							}
    						}else{
    							ret[0] =Constants.CODE_FAILED;
    							ret[1] ="该业务已经处理过，请勿重复请求";
    						}
						}
	               }catch(Exception e){
						e.printStackTrace();
						ret[0]=Constants.CODE_FAILED;
						ret[1]="系统错误-报错了 ";
					}
		    	}else{
		    		ret[0]=Constants.CODE_FAILED;
					ret[1]="系统错误-提前还款，没有找到需要提前还款的投资人列表。或者已经还款";
		    	}
		}
		return ret;
	}
	
	
	
	
	/**
	 * 查询平台还款日志，第三方还款记录
	 * @param mem
	 * @param planId
	 * @param intentDate
	 * @param uniqueId
	 * @return
	 */
	public boolean getThirdPayRecord(BpCustMember mem,String planId,String intentDate,String uniqueId){
		boolean thirdpay = true;
		ThirdPayRecord thirdpayrecord = thirdPayRecordService.getByUniqueId(uniqueId);
		if(thirdpayrecord==null){
		}else{
			if(thirdpayrecord.getCode().equals(CommonResponse.RESPONSECODE_APPLAY)){//已提交申请
				String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
				//查询第三方操作 
				CommonRequst commonRequst =new CommonRequst();
				commonRequst.setRequsetNo(requestNo);
				commonRequst.setQueryRequsetNo(thirdpayrecord.getRecordNumber());//查询的交易流水号
				commonRequst.setQueryType("REPAYMENT_RECORD");//REPAYMENT_RECORD表示查询还款的交易记录
				commonRequst.setBussinessType(ThirdPayConstants.BT_QUERYPLATF);
				commonRequst.setTransferName(ThirdPayConstants.TN_QUERYPLATF);
				CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
				if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//第三方已经请求成功
					CommonRecord commonRecord = commonResponse.getRecordList().get(0);
					if(commonRecord.getStatus().equals("SUCCESS")||commonRecord.getStatus().equals("CONFIRM")){//第三方还款成功
						thirdpayrecord.setCode(CommonResponse.RESPONSECODE_SUCCESS);
						thirdpayrecord.setStatus(2);
						thirdPayRecordService.save(thirdpayrecord);
						thirdpay=false;
						//检查平台是否更新了还款记录
						//查询还款的记录
						Map<String,String> map = new HashMap<String, String>();
						map.put("bidId",planId);//标id
						map.put("intentDate",thirdpayrecord.getIntentDate().toString());//计划还款日期
						map.put("requestTime", thirdpayrecord.getRequestTime().toString());//请求接口时间
						map.put("requestNum", thirdpayrecord.getRequestNum().toString());//请求接口次数
						map.put("requestNo", commonResponse.getRequestNo());
						map.put("thirdConfigType", "loaner");
						opraterBussinessDataService.repayment(map);	
					}else if(commonRecord.getStatus().equals("PREAUTH")){//还款申请已提交，继续调用审核接口
						//调用审核接口
						String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
						CommonRequst request = new CommonRequst();
						request.setRequsetNo(requestNum);
						request.setLoanNoList(thirdpayrecord.getRecordNumber());//审核流水号
						request.setConfimStatus(true);
						request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
						request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
						CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
						if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
							Map<String,String> map = new HashMap<String, String>();
							map.put("bidId",thirdpayrecord.getBidId().toString());//标id
							map.put("intentDate",thirdpayrecord.getIntentDate().toString());//计划还款日期
							map.put("requestTime", thirdpayrecord.getRequestTime().toString());//请求接口时间
							map.put("requestNum", thirdpayrecord.getRequestNum().toString());//请求接口次数
							map.put("requestNo", thirdpayrecord.getRecordNumber());
							map.put("thirdPayConfig", thirdpayrecord.getThirdPayConfig());
							map.put("thirdConfigType", "loaner");
							opraterBussinessDataService.repayment(map);
							thirdpayrecord.setCode(CommonResponse.RESPONSECODE_SUCCESS);
							thirdpayrecord.setStatus(2);
							thirdPayRecordService.save(thirdpayrecord);
							thirdpay=false;
						}
					}else{//还款失败
					}
				}else if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_NOTRECIVEPARAMETER)){//第三方未查找到记录
				}else{//交易失败
				}
			}else if(thirdpayrecord.getCode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//交易成功
				thirdpay=false;
				//调用审核接口
				String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
				CommonRequst request = new CommonRequst();
				request.setRequsetNo(requestNum);
				request.setLoanNoList(thirdpayrecord.getRecordNumber());//审核流水号
				request.setConfimStatus(true);
				request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
				request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
				CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
				if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
					Map<String,String> map = new HashMap<String, String>();
					map.put("bidId",thirdpayrecord.getBidId().toString());//标id
					map.put("intentDate",thirdpayrecord.getIntentDate().toString());//计划还款日期
					map.put("requestTime", thirdpayrecord.getRequestTime().toString());//请求接口时间
					map.put("requestNum", thirdpayrecord.getRequestNum().toString());//请求接口次数
					map.put("requestNo", thirdpayrecord.getRecordNumber());
					map.put("thirdPayConfig", thirdpayrecord.getThirdPayConfig());
					map.put("thirdConfigType", "loaner");
					opraterBussinessDataService.repayment(map);
				}
			}else{//交易失败
			}
		}
		return thirdpay;
	}
	/**
	 * 查询平台还款日志，第三方还款记录
	 * @param mem 1 审核中:前台页面提示  2.成功不继续请求三方  3.失败 正常交易
	 * @param recordNo
	 * @param interfaceType
	 * @param uniqueId
	 * @return
	 */
	public int getThirdPayLog(BpCustMember mem,String recordNo,String interfaceType,String interfaceName,String uniqueId){
		ThirdPayLog log = thirdPayLogService.getByOrderNo(recordNo);   //查询三方日志表   这俩个表以ThirdPayLog状态为主  ThirdPayRecord只记录订单号等 
		ThirdPayRecord thirdpayrecord = thirdPayRecordService.getByUniqueId(uniqueId);  //查询三方记录表
		int thirdpay = 1;   
		if(log!=null){
			if(log.getStatus()==1){
				thirdpay = 1; 
				/*//已提交申请
				String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
				//查询第三方操作 
				CommonRequst commonRequst =new CommonRequst();
				commonRequst.setRequsetNo(requestNo);
				commonRequst.setQueryRequsetNo(log.getRecordNumber());//查询的交易流水号
				commonRequst.setQueryType("REPAYMENT_RECORD");//REPAYMENT_RECORD表示查询还款的交易记录
				commonRequst.setBussinessType(ThirdPayConstants.BT_QUERYPLATF);
				commonRequst.setTransferName(ThirdPayConstants.TN_QUERYPLATF);
				CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
				if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//第三方已经请求成功
					CommonRecord commonRecord = commonResponse.getRecordList().get(0);
					if(commonRecord.getStatus().equals("SUCCESS")||commonRecord.getStatus().equals("CONFIRM")){//第三方还款成功
						log.setStatus(2);
						thirdPayLogService.save(log);
						thirdpay=2;
						//检查平台是否更新了还款记录
						//查询还款的记录
						Map<String,String> map = new HashMap<String, String>();
						map.put("bidId",thirdpayrecord.getBidId().toString());//标id
						map.put("intentDate",thirdpayrecord.getIntentDate().toString());//计划还款日期
						map.put("requestTime", log.getRequestTime().toString());//请求接口时间
						map.put("requestNum", thirdpayrecord.getRequestNum().toString());//请求接口次数
						map.put("requestNo", commonResponse.getRequestNo());
						map.put("thirdConfigType", "loaner");
						opraterBussinessDataService.repayment(map);	
					}else if(commonRecord.getStatus().equals("PREAUTH")){//还款申请已提交，继续调用审核接口
						thirdpay=1;
					}else if(commonRecord.getStatus().equals(CommonResponse.RESPONSECODE_FAILD)){
						thirdpay=3;
					}else{//还款失败
						thirdpay=1;
					}
				}else if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_FAILD)){//第三方未查找到记录
					thirdpay=3;
				}else if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_NOTRECIVEPARAMETER)){//第三方未查找到记录
					thirdpay=1;
				}else{//交易失败
					thirdpay=1;
				}
			*/}else if(log.getStatus()==2){//交易成功
				//调用审核接口
				String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
				CommonRequst request = new CommonRequst();
				request.setRequsetNo(requestNum);
				request.setLoanNoList(log.getRecordNumber());//审核流水号
				request.setConfimStatus(true);
				request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
				request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
				CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
				if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
					thirdpay=2;
					Map<String,String> map = new HashMap<String, String>();
					map.put("bidId",thirdpayrecord.getBidId().toString());//标id
					map.put("intentDate",thirdpayrecord.getIntentDate().toString());//计划还款日期
					map.put("requestTime", thirdpayrecord.getRequestTime().toString());//请求接口时间
					map.put("requestNum", thirdpayrecord.getRequestNum().toString());//请求接口次数
					map.put("requestNo", thirdpayrecord.getRecordNumber());
					map.put("thirdPayConfig", thirdpayrecord.getThirdPayConfig());
					map.put("thirdConfigType", "loaner");
					opraterBussinessDataService.repayment(map);
				}else{
					thirdpay=1;
				}
			}else{//交易失败
				thirdpay=3;
			}
		}else{
			//得到第三方标识
			String thirdPayConfig = configMap.get("thirdPayConfig").toString();
			ThirdPayLog log1 = new ThirdPayLog(mem.getId(), uniqueId, thirdPayConfig, interfaceType, interfaceName, new Date(), 1, 1, recordNo, null);
			thirdPayLogService.save(log1);
			thirdpay = 3;
		}
		logger.info("还款时查询三方接口日志状态为---------------------"+thirdpay);
		return thirdpay;
	}
	/**
	 * 准备还款数据
	 * @param request
	 * @param mem
	 * @return
	 */
	private String[] thirdPayRepayMentByLoaner(HttpServletRequest request,BpCustMember mem) {
		BigDecimal capital = BigDecimal.ZERO;//当期还款本金
		BigDecimal interest = BigDecimal.ZERO;//当期应还利息
		BigDecimal loanFee = BigDecimal.ZERO;//借款管理费
		BigDecimal accrual = BigDecimal.ZERO;//罚息
		
		String[] ret = new String[2];
		String isMobile = this.getRequest().getParameter("mobile");
		String planId = request.getParameter("planId");
		String peridId = request.getParameter("peridId");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (planId != null && !"".equals(planId) && peridId != null && !"".equals(peridId)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			BigDecimal bidTotalMoney = bpFundIntentService.getFinancialMoney(Long.valueOf(planId), "all");//标的还款总金额
			//查询还款记录
			List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepaymentList(planId,peridId);
			PlBidPlan bidPlan = plBidPlanService.get(Long.valueOf(planId));
			List<BpFundIntent> list = bpFundIntentService.getListByBidIdAndPeriod(planId, peridId);
			String intentDate  = new String();
			String intentDate3 = "";
			Date intentDate1 = new Date();
			Date intentDate2=new Date();
			String requestNo0 = ContextUtil.createRuestNumber();//生成第三需要的流水号
			System.out.println("往队列中添加流水号-----------");
			boolean flag = QueueManger.QueueOffer1(requestNo0);   //添加流水号进队列
			System.out.println("是否成功添加队列========"+flag);
			if(!flag){   //如果队列满了就清空
				QueueManger.QueueClear();
				QueueManger.QueueOffer1(requestNo0);
			}
			String requestNo = (String) QueueManger.QueuePeek();
			if(list.size()>0){
				intentDate2 = list.get(0).getIntentDate();
				intentDate3 =  sdf2.format(intentDate2);
				if(list.get(0).getRequestNOLoaner()!=null&&!"".equals(list.get(0).getRequestNOLoaner())){
//					requestNo = list.get(0).getRequestNOLoaner();
				}
			}
//			if(requestNo0==null||"".equals(requestNo0)){
//				
//			}
			//添加队列防止并发问题
//			if (requestNo0 != null) {
////				QueueManger.QueueClear();
//				QueueManger.QueueOffer(requestNo0);
//			}
		System.out.println("获取的流水号为requestNo："+requestNo);
//			synchronized (requestNo) {
			if(repayList.size()>0){//同一期的不是一个intentDate吗？为什么要循环？？？
				for(CommonRequestInvestRecord record:repayList){
					if(record.getIntentDate()!=null){
						intentDate = sdf.format(record.getIntentDate());
						intentDate1 = record.getIntentDate();
					}
					//还款本金和
					capital = capital.add(record.getPrincipal());
					//还款利息和
					interest = interest.add(record.getInterest());
					//服务费和
					loanFee = loanFee.add(record.getFee());
					//罚息
					accrual = accrual.add(record.getAccrual());
				}
			}
			//查询平台还款日志，第三方还款记录
//			boolean thirdpay = getThirdPayRecord(mem,planId,intentDate,ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);
			int thirdpay = getThirdPayLog(mem, requestNo, ThirdPayConstants.BT_REPAYMENT, ThirdPayConstants.TN_REPAYMENT, ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);
			//本息金额
			BigDecimal backMoney = bpFundIntentService.getBackMoney(planId, peridId);
			//取罚息金额
			BigDecimal backAccMoney = bpFundIntentService.getBackAccMoney(planId, peridId);
			//获取借款人的第三方账号
			String loanerName = plBidPlanService.get(Long.valueOf(planId)).getReceiverP2PAccountNumber();
			String loanerConfigId = "";
			if(loanerName!=null&&!"".equals(loanerName)){
				loanerConfigId = bpCustMemberService.getByLoginName(loanerName).getThirdPayFlagId();//(loanerName);
			}
			if(null!=repayList && repayList.size()>0){
				if(thirdpay==3){
					Date currentRequestDate=new Date();
					Date lastRequestDate  =repayList.get(0).getRequestDate();
		    		if(lastRequestDate!=null&&currentRequestDate.getTime()-lastRequestDate.getTime()<=10*60*1000){//同一笔还款项目两次还款请求需要间隔十分钟
		    			long time=10-((currentRequestDate.getTime()-lastRequestDate.getTime())/60000);
		    			ret[0] = Constants.CODE_FAILED;
			    		ret[1] = "同一笔还款项目两次还款请求需要间隔十分钟，请"+time+"分钟后再请求还款";
		    		}else{
		    			if(!Constants.CODE_FAILED.equals(ret[0])){
		    				logger.info("自助还款投资人还款列表:"+repayList);
		    				try{	    					
	  		    				CommonRequst common = new CommonRequst();
		    					common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
		    					common.setTransferName(ThirdPayConstants.TN_UPDATEBID);
		    					common.setBidId(planId.toString());
		    					common.setBidType(common.HRY_BID);
		    					common.setBidIdStatus("2");//更新标的状态为还款中
		    					CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
		    					if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									   if(list.size()>0){
										   for(BpFundIntent intent:list){
											   intent.setRequestNo(requestNo);
											   intent.setRequestNOLoaner(requestNo);
											   bpFundIntentService.merge(intent);
										   }
									   }
									//借款人给投资人转账  
		    						CommonRequst cq=new CommonRequst();
									Date date = new Date();
									if(isMobile!=null&&"1".equals(isMobile)){
										cq.setIsMobile("1");
									}
									cq.setThirdPayConfigId(mem.getThirdPayFlagId());
									cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
									cq.setRequsetNo(requestNo);//请求流水号
									cq.setRepaymemntList(repayList);//还款list
									cq.setPrincipalAmt(capital.toString());//本金
									cq.setInterestAmt((interest.add(accrual)).toString());//利息=利息+罚息
									cq.setLoanFee(loanFee.toString());//管理费
									cq.setLoanNoList(bidPlan.getLoanTxNo());//还款标的标识
									
					    			cq.setCustMemberId(mem.getId().toString());//用户id
					    			cq.setLoginname(mem.getLoginname());//登录名
					    			cq.setBidId(planId.toString());//标Id
					    			cq.setPlanMoney(bidTotalMoney);//标的还款总金额
					    			cq.setIntentDate(intentDate1);//计划还款日期
					    			cq.setProId(CommonRequst.HRY_BID+planId.toString());//(汇付，标的id	)
					    			cq.setOrderDate(sdf1.format(new Date()));//请求交易时间
					    			cq.setOutCustId(mem.getThirdPayFlagId());//出账客户号
					    			cq.setUniqueId(ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);//还款唯一标识
					    			cq.setBussinessType(ThirdPayConstants.BT_REPAYMENT);//业务类型
					    			cq.setTransferName(ThirdPayConstants.TN_REPAYMENT);//名称
					    			cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
					    			cq.setTransactionTime(date);
					    			cq.setRemark1(peridId);
					    			cq.setAmount(backMoney.add(backAccMoney));
					    			cq.setLoaner_thirdPayflagId(loanerConfigId);//借款人的第三方账号
					    			cq.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
					    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
					    			if (cr!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					    				ret[0] =Constants.CODE_SUCCESS;
										ret[1] =cr.getResponseMsg();
									}else {
										ret[0] = Constants.CODE_FAILED;
										ret[1] = "还款失败";
									}
		    					}else{
		    						ret[0] = Constants.CODE_FAILED;
									ret[1] = "标的状态更改失败 ";
		    					}
	    					}catch(Exception e){
	    						System.out.println("系统错误-还款报错了 ");
								ret[0] = Constants.CODE_FAILED;
								ret[1] = "系统错误-报错了 ";
								e.printStackTrace();
							}
		    			}
		    		}
				}else if(thirdpay==1||thirdpay==2){
			    	ret[0] = Constants.CODE_FAILED;
			    	ret[1] = "已经操作过了还款，请联系管理员核对。";
				}
			}else{
		    	ret[0] = Constants.CODE_FAILED;
		    	ret[1] = "不能进行还款，投资人款项计划错误";
			}
//		  }
			//在方法执行最后加入出队列操作
			QueueManger.QueuePoll();
		} else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "系统错误-还款投资人列表，标号或者期数没有值";
		}
		return ret;
	}
	/**
	 * 准备还款数据
	 * 做为保留方法
	 * @param request
	 * @param mem
	 * @return
	 */
	private String[] thirdPayRepayMentByLoanerOld(HttpServletRequest request,BpCustMember mem) {
		int i = 0;//还款给投资人成功个数
		int j = 0;//平台还款费用成功个数
		int k = 0;//还款给投资人失败个数
		int o = 0;//还款给投资人失败个数
		int t = 0;//统计本息还款的次数
		int u = 0;//统计平台费用还款的次数
		BigDecimal loanMoney = BigDecimal.ZERO;//本息实际收取费用
		BigDecimal feeMoney = BigDecimal.ZERO;//实际费用收取的金额
		String[] ret = new String[2];
		String isMobile = this.getRequest().getParameter("mobile");
		String planId = request.getParameter("planId");
		String peridId = request.getParameter("peridId");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (planId != null && !"".equals(planId) && peridId != null && !"".equals(peridId)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			BigDecimal bidTotalMoney = bpFundIntentService.getFinancialMoney(Long.valueOf(planId), "all");//标的还款总金额
			//查询还款记录
			List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepaymentList(planId,peridId);
			PlBidPlan bidPlan = plBidPlanService.get(Long.valueOf(planId));
			List<BpFundIntent> list = bpFundIntentService.getListByBidIdAndPeriod(planId, peridId);
			String intentDate  = new String();
			String intentDate3 = "";
			Date intentDate1 = new Date();
			Date intentDate2=new Date();
			String requestNo0 = ContextUtil.createRuestNumber();//生成第三需要的流水号
			System.out.println("往队列中添加流水号-----------");
			boolean flag = QueueManger.QueueOffer1(requestNo0);   //添加流水号进队列
			System.out.println("是否成功添加队列========"+flag);
			if(!flag){   //如果队列满了就清空
				QueueManger.QueueClear();
				QueueManger.QueueOffer1(requestNo0);
			}
			String requestNo = (String) QueueManger.QueuePeek();
			if(list.size()>0){
				intentDate2 = list.get(0).getIntentDate();
				intentDate3 =  sdf2.format(intentDate2);
				if(list.get(0).getRequestNOLoaner()!=null&&!"".equals(list.get(0).getRequestNOLoaner())){
					requestNo = list.get(0).getRequestNOLoaner();
				}
			}
//			if(requestNo0==null||"".equals(requestNo0)){
//				
//			}
			//添加队列防止并发问题
//			if (requestNo0 != null) {
////				QueueManger.QueueClear();
//				QueueManger.QueueOffer(requestNo0);
//			}
			System.out.println("获取的流水号为requestNo："+requestNo);
//			synchronized (requestNo) {
			if(repayList.size()>0){//同一期的不是一个intentDate吗？为什么要循环？？？
				for(CommonRequestInvestRecord record:repayList){
					if(record.getIntentDate()!=null){
						intentDate = sdf.format(record.getIntentDate());
						intentDate1 = record.getIntentDate();
					}
				}
			}
			//查询平台还款日志，第三方还款记录
//			boolean thirdpay = getThirdPayRecord(mem,planId,intentDate,ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);
			int thirdpay = getThirdPayLog(mem, requestNo, ThirdPayConstants.BT_REPAYMENT, ThirdPayConstants.TN_REPAYMENT, ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);
			//本息金额
			BigDecimal backMoney = bpFundIntentService.getBackMoney(planId, peridId);
			//取罚息金额
			BigDecimal backAccMoney = bpFundIntentService.getBackAccMoney(planId, peridId);
			//获取借款人的第三方账号
			String loanerName = plBidPlanService.get(Long.valueOf(planId)).getReceiverP2PAccountNumber();
			String loanerConfigId = "";
			if(loanerName!=null&&!"".equals(loanerName)){
				loanerConfigId = bpCustMemberService.getByLoginName(loanerName).getThirdPayFlagId();//(loanerName);
			}
			if(null!=repayList && repayList.size()>0){
				if(thirdpay==3){
					Date currentRequestDate=new Date();
					Date lastRequestDate  =repayList.get(0).getRequestDate();
					if(lastRequestDate!=null&&currentRequestDate.getTime()-lastRequestDate.getTime()<=10*60*1000){//同一笔还款项目两次还款请求需要间隔十分钟
						long time=10-((currentRequestDate.getTime()-lastRequestDate.getTime())/60000);
						ret[0] = Constants.CODE_FAILED;
						ret[1] = "同一笔还款项目两次还款请求需要间隔十分钟，请"+time+"分钟后再请求还款";
					}else{
						if(!Constants.CODE_FAILED.equals(ret[0])){
							logger.info("自助还款投资人还款列表:"+repayList);
							try{	    					
								CommonRequst common = new CommonRequst();
								common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
								common.setTransferName(ThirdPayConstants.TN_UPDATEBID);
								common.setBidId(planId.toString());
								common.setBidType(common.HRY_BID);
								common.setBidIdStatus("2");//更新标的状态为还款中
								CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
								if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									if(list.size()>0){
										for(BpFundIntent intent:list){
											intent.setRequestNo(requestNo);
											intent.setRequestNOLoaner(requestNo);
											bpFundIntentService.merge(intent);
										}
									}
									//借款人给投资人转账  
									CommonRequst cq=new CommonRequst();
									Date date = new Date();
									if(isMobile!=null&&"1".equals(isMobile)){
										cq.setIsMobile("1");
									}
									cq.setRepaymemntList(repayList);//还款list
									cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
									cq.setCustMemberId(mem.getId().toString());//用户id
									cq.setLoginname(mem.getLoginname());//登录名
									cq.setBidId(planId.toString());//标Id
									cq.setRequsetNo(requestNo);//请求流水号
									cq.setPlanMoney(bidTotalMoney);//标的还款总金额
									cq.setIntentDate(intentDate1);//计划还款日期
									cq.setProId(CommonRequst.HRY_BID+planId.toString());//(汇付，标的id	)
									cq.setOrderDate(sdf1.format(new Date()));//请求交易时间
									cq.setOutCustId(mem.getThirdPayFlagId());//出账客户号
									cq.setUniqueId(ThirdPayConstants.BT_REPAYMENT+planId.toString()+intentDate);//还款唯一标识
									cq.setBussinessType(ThirdPayConstants.BT_REPAYMENT);//业务类型
									cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
									cq.setTransferName(ThirdPayConstants.TN_REPAYMENT);//名称
									cq.setTransactionTime(date);
									cq.setRemark1(peridId);
									cq.setAmount(backMoney.add(backAccMoney));
									cq.setLoaner_thirdPayflagId(loanerConfigId);//借款人的第三方账号
									cq.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
									CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
//					    			CommonResponse cr = null;
									ret[0] =cr.getResponsecode();
									ret[1] =cr.getResponseMsg();
									ThirdPayRecord record = thirdPayRecordService.getByOrderNo(requestNo);
									if(cr!=null&&cr.getResponseList()!=null){
										if(cr.getResponseList().size()>0){
											for(CommonResponse response:cr.getResponseList()){
												if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													i++;
													t++;
													if(response.getRemark()!=null){
														loanMoney = loanMoney.add(new BigDecimal(response.getRemark()));
													}else{
														loanMoney=new BigDecimal(0);
													}
													Map map = new HashMap();
													if(t==cr.getResponseList().size()){
														map.put("thirdConfigType","loanerFore");
														map.put("money", loanMoney.toString());
													}
													map.put("requestNo",response.getRequestNo());
													map.put("bidId",planId.toString());
													map.put("intentDate", intentDate3);
													map.put("peridId", peridId);//将还款的期数传入
													if(record!=null){
														map.put("requestTime", sdf2.format(record.getRequestTime()));
													}else{
														map.put("requestTime", sdf2.format(new Date()));
													}
													opraterBussinessDataService.repayment(map);
												}else{
													t++;
													k++;
												}
											}
										}
									}else if(record !=null && !"umpayConfig".equals(record.getThirdPayConfig())){
										if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
											Map<String,String> map = new HashMap<String, String>();
											map.put("bidId",planId.toString());//标id
											map.put("intentDate",intentDate2.toString());//计划还款日期
											map.put("requestNo", requestNo);
											map.put("peridId", peridId);
											map.put("thirdConfigType","loanerFore");
											map.put("requestTime", sdf2.format(record.getRequestTime()));
											opraterBussinessDataService.repayment(map);
										}
									}
									//借款人给平台转账
									CommonRequst cq1=new CommonRequst();
									Date date1 = new Date();
									String requestNo1 = ContextUtil.createRuestNumber();//生成第三需要的流水号
									if(isMobile!=null&&"1".equals(isMobile)){
										cq1.setIsMobile("1");
									}
									cq1.setRepaymemntList(repayList);//还款list
									cq1.setBidId(planId.toString());//标Id
									cq1.setRequsetNo(requestNo1);//请求流水号
									cq1.setPlanMoney(bidTotalMoney);//标的还款总金额
									cq1.setIntentDate(intentDate1);//计划还款日期
									cq1.setUniqueId(ThirdPayConstants.BT_PLATEFORMRECHAGE+planId.toString()+intentDate);//还款唯一标识
									cq1.setBussinessType(ThirdPayConstants.BT_PLATEFORMRECHAGE);//业务类型
									cq1.setTransferName(ThirdPayConstants.TN_PLATEFORMRECHAGE);//名称
									cq1.setRemark1(peridId);
									cq1.setThirdPayConfigId(loanerConfigId);//借款人的第三方账号
									cq1.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
									CommonResponse cr2=ThirdPayInterfaceUtil.thirdCommon(cq1);
									ThirdPayRecord record2 = thirdPayRecordService.getByOrderNo(requestNo1);
									if(cr2!=null&&cr2.getResponseList()!=null){
										if(cr2.getResponseList().size()>0){
											for(CommonResponse response1:cr2.getResponseList()){
												if(response1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													j++;
													u++;
													feeMoney = feeMoney.add(new BigDecimal(response1.getRemark()));
													Map map1 = new HashMap();
													map1.put("requestNo",response1.getRequestNo());
													map1.put("bidId",planId.toString());
													map1.put("intentDate", intentDate2.toString());
													map1.put("peridId", peridId);//传入还款期数peridId
													if(u == cr2.getResponseList().size()){
														map1.put("money", feeMoney.toString());
														map1.put("thirdConfigType", "loanerFee");
													}
													if(record2!=null){
														map1.put("requestTime", sdf2.format(record2.getRequestTime()));
													}else{
														map1.put("requestTime", sdf2.format(new Date()));
													}
													opraterBussinessDataService.repayment(map1);
												}else{
													u++;
													o++;
												}
											}
										}
									}else if(record2 !=null && !"umpayConfig".equals(record2.getThirdPayConfig())){
										if(cr2.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
											Map<String,String> map = new HashMap<String, String>();
											map.put("bidId",planId.toString());//标id
											map.put("intentDate",intentDate2.toString());//计划还款日期
											map.put("requestNo", requestNo);
											map.put("peridId", peridId);
											map.put("thirdConfigType","loanerFee");
											map.put("requestTime", sdf2.format(record.getRequestTime()));
											opraterBussinessDataService.repayment(map);
										}
									}
									
									//联动优势企业户还款为直连接口
									if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE) 
											&& record !=null && "umpayConfig".equals(record.getThirdPayConfig())){
										if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
											//企业用户 直接手动调用回调方法
											Map<String,String> map = new HashMap<String, String>();
											map.put("requestNo",requestNo);
											opraterBussinessDataService.umpayRepayment(map);	
											ret[0] = Constants.CODE_SUCCESS;
											ret[1] = "还款成功";
										}else{
											ret[0] = Constants.CODE_FAILED;
											ret[1] = "还款失败";
										}
									}else{
										if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
											ret[0] = Constants.CODE_SUCCESS;
											ret[1] = "还款成功";
										}else if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
											ret[0] = Constants.CODE_FAILED;
											ret[1] = "还款申请已提交";
										}else{
											ret[0] = Constants.CODE_FAILED;
											ret[1] = "还款失败";
										}
									}
								}else{
									ret[0] = Constants.CODE_FAILED;
									ret[1] = "标的状态更改失败 ";
								}
							}catch(Exception e){
								System.out.println("系统错误-还款报错了 ");
								ret[0] = Constants.CODE_FAILED;
								ret[1] = "系统错误-报错了 ";
								e.printStackTrace();
							}
						}
					}
				}else if(thirdpay==1||thirdpay==2){
					ret[0] = Constants.CODE_FAILED;
					ret[1] = "已经操作过了还款，请联系管理员核对。";
				}
			}else{
				ret[0] = Constants.CODE_FAILED;
				ret[1] = "不能进行还款，投资人款项计划错误";
			}
//		  }
			//在方法执行最后加入出队列操作
			QueueManger.QueuePoll();
		} else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "系统错误-还款投资人列表，标号或者期数没有值";
		}
		return ret;
	}

	/**
	 * 对每个标进行自动还款授权或者取消自动还款授权
	 */
	public String autoRepaymentAuthorization() {
		System.out.println("借款人授权平台帮助借款人自动还款");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String isMobile =  this.getRequest().getParameter("mobile");
		
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){//验证是否具备交易资格
				mem = bpCustMemberService.get(mem.getId());
				mem = obSystemAccountService.getAccountSumMoney(mem);
				String planId = this.getRequest().getParameter("planId");
				if (planId != null && !"".equals(planId)) {
					PlBidPlan plan = plBidPlanService.get(Long.valueOf(planId));
					if (plan != null) {
						String actionStatus = this.getRequest().getParameter("actionStatus");
						CommonResponse cr=new CommonResponse();
						//if (actionStatus != null && !"".equals(actionStatus)) {
							//授权与取消授权
							if (actionStatus.equals("cancel") || actionStatus.equals("authorization")||actionStatus == null||actionStatus.equals("1")||actionStatus.equals("")){
								if(actionStatus.equals("")){
									actionStatus = "authorization";
								}else if(actionStatus.equals("1")){
									actionStatus = "cancel";
								}
								String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
								if(mem.getRefund()!=null && mem.getRefund().equals("1") && actionStatus.equals("authorization")){
									plan.setAuthorizationStatus(Short.valueOf("1"));
									plBidPlanService.save(plan);
									webMsgInstance("0",Constants.CODE_SUCCESS,"开启自动还款授权成功", "", "", "", "", "");
								}else if(mem.getRefund()!=null && mem.getRefund().equals("1") && actionStatus.equals("cancel")){
									plan.setAuthorizationStatus(null);
									plBidPlanService.save(plan);
									webMsgInstance("0",Constants.CODE_SUCCESS,"关闭自动还款授权成功", "", "", "", "", "");
								}else{
									CommonRequst cq=new CommonRequst();
									if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
										cq.setIsMobile("1");
									}
									cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
									cq.setRequsetNo(requestNo);//请求流水号
									cq.setBidId(planId);//标id
									cq.setBussinessType(actionStatus.equals("authorization")?ThirdPayConstants.BT_OPENPAYAUTH:ThirdPayConstants.BT_CLOSEPAYAUTH);
									cq.setTransferName(actionStatus.equals("authorization")?ThirdPayConstants.TN_OPENPAYAUTH:ThirdPayConstants.TN_CLOSEPAYAUTH);//业务用途   开通还款授权
									if(mem.getSecondAudit() != null && mem.getSecondAudit().equals("1")){
										cq.setAuthorizeTypeOpen("2");//开启授权类型
									}else{
										cq.setAuthorizeTypeOpen("2,3");//开启授权类型
									}
									if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户
										cq.setAccountType(0);
									}else{//企业用户
										cq.setAccountType(1);
									}
									cr=ThirdPayInterfaceUtil.thirdCommon(cq);
									if(CommonResponse.RESPONSECODE_SUCCESS.equals(cr.getResponsecode())){
											if(!actionStatus.equals("authorization")){//取消
												plan.setAuthorizationStatus(null);
												plan.setRequestNo(null);
												plBidPlanService.save(plan);
												webMsgInstance("0",Constants.CODE_SUCCESS,"取消自动还款授权成功", "", "", "", "", "");
											}else{//开启
												Map<String ,String> map=new HashMap<String,String>();
												map.put("custermemberId", mem.getId().toString());//用户Id
												map.put("open", ThirdPayConstants.BT_OPENPAYAUTH);//开启类型
												map.put("close", "");//关闭类型
												map.put("bidId", plan.getBidId().toString());
												opraterBussinessDataService.umpayLoanAuthorize(map);
												webMsgInstance("0",Constants.CODE_SUCCESS,"开启自动还款授权成功", "", "", "", "", "");
											}
									}else if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
										plan.setRequestNo(requestNo);
										plBidPlanService.save(plan);
									}else{
										webMsgInstance("0",Constants.CODE_FAILED,cr.getResponseMsg(), "", "", "", "", "");
									}
								}
							}else {
								webMsgInstance("0", Constants.CODE_FAILED,"请确认是否正确传输了自动还款授权或取消自动还款授权操作指令", "","", "", "", "");
							}
						//}
						/* else {
							webMsgInstance("0", Constants.CODE_FAILED,"请确认是否传输了自动还款授权或取消自动还款授权操作指令", "", "", "","", "");
						}*/
					} else {
						webMsgInstance("0", Constants.CODE_FAILED,"请确认标的Id是否正确", "", "", "", "", "");
					}
				} else {
					webMsgInstance("0", Constants.CODE_FAILED,"请确认需要授权的标的Id是否传输", "", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "授权错误，请联系管理员","", "", "", "", "");
		}
		if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";

	}

	
	/**
	 * 平台发送奖励金额
	 * @param planId
	 * @param peridId
	 */
/*	private void checkCouponsIntent(String planId,String peridId,String requestNo){
		PlBidPlan bidplan = plBidPlanService.get(Long.valueOf(planId));

		//判断此标是否可用优惠券
		if(bidplan.getCoupon()!=null&&bidplan.getCoupon().compareTo(1)==0){
			//判断返利方式是否是 随期或者到期
			if(bidplan.getRebateWay().compareTo(2)==0||bidplan.getRebateWay().compareTo(3)==0){
				List<BpFundIntent> bpfundInterestList=null;//利息
				List<BpFundIntent> bpfundPrincipalList=null;//本金
				String transferType="";//资金类型
				boolean checkWay=false;
				if(bidplan.getRebateWay().compareTo(2)==0){
					checkWay=true;
				}else if(bidplan.getRebateWay().compareTo(3)==0){
					int per = Integer.valueOf(peridId)+1;
					List<BpFundIntent> checkFund = bpFundIntentService.getCouponsIntent(planId, String.valueOf(per),requestNo,null);
					if(checkFund.size()==0){//表示当前期数是最后一期还款
						checkWay=true;
					}
				}
				if(checkWay){
					//判断 返利类型
					if(bidplan.getRebateType().compareTo(1)==0){//返现 principalCoupons
						transferType=ObAccountDealInfo.T_BIDRETURN_RETURNRATIO;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"principalCoupons");
					}else if(bidplan.getRebateType().compareTo(2)==0){//返息 couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE27;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"couponInterest");
					}else if(bidplan.getRebateType().compareTo(3)==0){//返息现  principalCoupons couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE29;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"couponInterest");
						bpfundPrincipalList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"principalCoupons");
					}else if(bidplan.getRebateType().compareTo(4)==0){//加息 couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE30;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"subjoinInterest");
					}
					if(bpfundInterestList!=null){//返利息
						couponIntent(bpfundInterestList,bidplan,transferType);
					}
					if(bpfundPrincipalList!=null){//返本金
						couponIntent(bpfundPrincipalList,bidplan,ObAccountDealInfo.T_BIDRETURN_RATE28);
					}
				}
			}
		}else{
			//判断是否此标设置了普通加息
			if(bidplan.getAddRate()!=null&&!bidplan.getAddRate().equals("")){
				List<BpFundIntent> subjoinInterest = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"subjoinInterest");
				couponIntent(subjoinInterest,bidplan,ObAccountDealInfo.T_BIDRETURN_ADDRATE);
			}
		}
		
		//判断此标是否设置了募集期利率
		if(bidplan.getRaiseRate()!=null&&!bidplan.getRaiseRate().equals("")){
			List<BpFundIntent> raiseinterestList = bpFundIntentService.getCouponsIntent(planId, peridId,"raiseinterest");
			couponIntent(raiseinterestList,bidplan,ObAccountDealInfo.T_BIDRETURN_RATE31);
		}
	}*/
	
	/**
	 * 派发优惠券奖励
	 * @param bp
	 * @param info
	 */
	/*private void couponIntent(List<BpFundIntent> bp,PlBidPlan bidplan,String transferType){
		for(BpFundIntent bpfund:bp){
			if(bpfund.getFactDate()==null||bpfund.getFactDate().equals("")){
				BpCustMember mem=bpCustMemberService.get(bpfund.getInvestPersonId());
				String requestNo=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
				Map<String,Object> mapp=new HashMap<String,Object>();
				mapp.put("basePath", this.getBasePath());
				mapp.put("platformUserNo", mem.getThirdPayFlag0());
				mapp.put("customerId", mem.getId().toString());
				mapp.put("customerType", "0");
				mapp.put("requestNo",  requestNo);
				mapp.put("money", bpfund.getNotMoney());
				String[]  result=yeePayService.PLATFORM_TRANSFER(mapp);
				System.out.println("返现result="+result[1]);
				if(result[0].equals(Constants.CODE_SUCCESS)){
					PlBidInfo bidInfo1 = plBidInfoService.getOrderNumber(bpfund.getOrderNo());
					//更新优惠券为已使用
					BpCoupons coupon = bpCouponsService.get(bidInfo1.getCouponId());
					coupon.setCouponStatus(Short.valueOf("10"));
					coupon.setUseProjectName(bidplan.getBidProName());
					coupon.setUseProjectNumber(bidplan.getBidProNumber());
					coupon.setUseProjectType(bidplan.getProType());
					coupon.setUseTime(new Date());
					bpCouponsService.save(coupon);
					//添加资金明细
					Map<String,Object> map3=new HashMap<String,Object>();
					map3.put("investPersonId",mem.getId());//投资人Id（必填）
					map3.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
					map3.put("transferType",transferType);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
					map3.put("money",bpfund.getNotMoney());//交易金额	（必填）			 
					map3.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					map3.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					map3.put("recordNumber",requestNo);//交易流水号	（必填）
					map3.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
					obAccountDealInfoService.operateAcountInfo(map3);
					
					//更新款项
					bpfund.setNotMoney(new BigDecimal(0));
					bpfund.setAfterMoney(bpfund.getIncomeMoney());
					bpfund.setFactDate(new Date());
					bpFundIntentService.save(bpfund);
				}
			}
			
		}
	}*/
	/**
	 * 取消支付
	 * @return
	 */
	public String cancelRecharge() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			ObAccountDealInfo ob = obAccountDealInfoService.get(loginId);
			if (ob != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("queryRequestNo", ob.getRecordNumber());
				map.put("queryType", ob.getTransferType().equals(
						ObAccountDealInfo.T_RECHARGE) ? "RECHARGE_RECORD"
						: "WITHDRAW_RECORD");
				String cardNo = ContextUtil.createRuestNumber();
				CommonResponse commonResponse = new CommonResponse();
				CommonRequst request = new CommonRequst();
				request.setThirdPayConfigId(mem.getThirdPayFlagId());
				request.setQueryRequsetNo(ob.getRecordNumber());
				request.setQueryType(ob.getTransferType().equals("1") ? "RECHARGE_RECORD": "WITHDRAW_RECORD");
				request.setRequsetNo(cardNo);
				request.setTransactionTime(ob.getCreateDate());
				request.setStartDay(ob.getCreateDate());//起始时间
				request.setEndDay(ob.getCreateDate());//结束时间
				request.setBussinessType(ThirdPayConstants.BT_QUERYPLATF);
				request.setTransferName(ThirdPayConstants.TN_QUERYPLATF);
				commonResponse = ThirdPayInterfaceUtil.thirdCommon(request);
				if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)
						/*||commonResponse.getResponsecode().equals("responsecode_notReciveParmeter")*/) {
					List<CommonRecord> re = commonResponse.getRecordList();
					if (re != null && re.size() > 0) {
						CommonRecord record = re.get(0);
						if (record.getStatus()!=null&&(record.getStatus().equals("SUCCESS")|| (record.getStatus().equals("已通过"))|| record.getStatus().equals("交易成功")||record.getStatus().equals(CommonResponse.RESPONSECODE_SUCCESS))) {
							if(ob.getDealRecordStatus().toString().equals("7")){
								webMsgInstance("0", Constants.CODE_SUCCESS,"交易成功", "", "", "", "", "");
							}else{
								webMsgInstance("0", Constants.CODE_FAILED,"已经支付成功，不允许取消", "", "", "", "", "");
							}
							if (ob.getTransferType().equals(ObAccountDealInfo.T_RECHARGE)) {
								Map<String, String> mapp = new HashMap<String, String>();
								mapp.put("custermemberId", "");
								mapp.put("requestNo", ob.getRecordNumber());
								mapp.put("custmerType", "0");
								mapp.put("dealRecordStatus", "2");
								opraterBussinessDataService.recharge(mapp);
							} else {
								Map<String, String> mapw = new HashMap<String, String>();
								mapw.put("custermemberId", "");
								mapw.put("requestNo", ob.getRecordNumber());
								mapw.put("custmerType", "0");
								mapw.put("dealRecordStatus", "2");
								opraterBussinessDataService.withDraw(mapw);
							}
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						} else if (record.getStatus()!=null&&(record.getStatus().equals("INIT")|| record.getStatus().equals("交易失败"))) {
							ob.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_3);
							ob.setMsg("用户主动取消交易");
							obAccountDealInfoService.merge(ob);
							webMsgInstance("0", Constants.CODE_SUCCESS,"已经成功取消支付", "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}else if (record.getStatus()!=null&&record.getStatus().equals(CommonResponse.RESPONSECODE_APPLAY)) {
							webMsgInstance("0", Constants.CODE_SUCCESS,"第三方正在处理中,不能取消支付", "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}else{ 
							ob.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_3);
							ob.setMsg("用户主动取消交易");
							obAccountDealInfoService.merge(ob);
							webMsgInstance("0", Constants.CODE_SUCCESS,"已经成功取消支付", "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					} else {
						ob.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_3);
						ob.setMsg("用户主动取消交易");
						obAccountDealInfoService.merge(ob);
						webMsgInstance("0", Constants.CODE_SUCCESS,"已经成功取消支付", "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());	
					}
				}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
					webMsgInstance("0", Constants.CODE_SUCCESS,"第三方正在处理中", "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());	
				}else {
					ob.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_3);
					ob.setMsg("用户主动取消交易");
					obAccountDealInfoService.merge(ob);
					webMsgInstance("0", Constants.CODE_SUCCESS,"已经成功取消支付", "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());	
				}
			} else {
				webMsgInstance("0", Constants.CODE_FAILED, "没有选择对应的操作记录", "",
						"", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 重新
	 * @return
	 */
	public String reRecharge(){
	    BpCustMember mem = (BpCustMember)getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	    if (mem != null) {
	      ObAccountDealInfo ob = (ObAccountDealInfo)this.obAccountDealInfoService.get(this.loginId);
	      if (ob != null) {
	        String cardNo = ContextUtil.createRuestNumber();
	        CommonResponse commonResponse = new CommonResponse();
	        CommonRequst request = new CommonRequst();
	        request.setQueryRequsetNo(ob.getRecordNumber());
	        request.setQueryType(ob.getTransferType().equals("1") ? "RECHARGE_RECORD" : "WITHDRAW_RECORD");
	        request.setRequsetNo(cardNo);
	        request.setBussinessType(ThirdPayConstants.BT_QUERYPLATF);
	        request.setTransferName(ThirdPayConstants.TN_QUERYPLATF);
	        commonResponse = ThirdPayInterfaceUtil.thirdCommon(request);
	        if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
	        	List<CommonRecord> recordList = commonResponse.getRecordList();
	          for (CommonRecord record : recordList) {
	            if ((record.getStatus().equals("SUCCESS")) || (record.getStatus().equals("已通过"))) {
	              if (ob.getTransferType().equals("1")) {
	                Map mapp = new HashMap();
	                mapp.put("custermemberId", "");
	                mapp.put("requestNo", ob.getRecordNumber());
	                mapp.put("custmerType", "0");
	                mapp.put("dealRecordStatus", "2");
	                this.opraterBussinessDataService.recharge(mapp);
	              } else {
	                Map mapw = new HashMap();
	                mapw.put("custermemberId", "");
	                mapw.put("requestNo", ob.getRecordNumber());
	                mapw.put("custmerType", "0");
	                mapw.put("dealRecordStatus", "2");
	                this.opraterBussinessDataService.withDraw(mapw);
	              }
	              webMsgInstance("0", "0000", "已经支付成功，不允许重新支付", "", "", "", "", "");
	              setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	            } else if (record.getStatus().equals("INIT")) {
	              ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	              if (third != null) {
	                this.thirdPayRecordService.remove(third);
	              }
	              CommonResponse cq = new CommonResponse();
	              CommonRequst cr = new CommonRequst();
	              if (ob.getTransferType().equals("1")) {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getIncomMoney().setScale(2));
	                cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
	                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if (!cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY))
	                {
	                  webMsgInstance("0", "0000", "操作失败、请稍后操作", "", "", "", "", "");
	                  setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	                }
	              } else {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setCustMemberId(mem.getId().toString());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getPayMoney().setScale(2));
	                cr.setCustMemberType("0");
	                cr.setFee(new BigDecimal("0"));
	                cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
	                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if(cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
	                	
	                }
	              }

	            }

	            if (recordList.size() <= 0) {
	              ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	              if (third != null) {
	                this.thirdPayRecordService.remove(third);
	              }
	              CommonResponse cq = new CommonResponse();
	              CommonRequst cr = new CommonRequst();
	              if (ob.getTransferType().equals("1")) {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getIncomMoney().setScale(2));
	                cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
	                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if (cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY))
	                  continue;
	                webMsgInstance("0", "0000", "操作失败、请稍后操作", "", "", "", "", "");
	                setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	              }
	              else {
	            	List<WebBankcard> cardList = this.webBankCardService.getBycusterId(mem.getId());
	                WebBankcard card = new WebBankcard();
	                for (WebBankcard c : cardList) {
	                  if (c.getBindCardStatus().equals("bindCard_status_success")) {
	                    card = c;
	                  }
	                }
	                Date date = new Date();
	                cr.setTransactionTime(date);
	                cr.setBankCardNumber(card.getCardNum());
	                cr.setBankCardType("0");
	                cr.setBankCode(card.getBankId());
	                cr.setAccountType(Integer.valueOf(0));
	                if (card.getProvinceId() != null) {
	                  cr.setProvince(card.getProvinceId().toString());
	                }
	                if (card.getCityId() != null) {
	                  cr.setCity(card.getCityId().toString());
	                }
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setCustMemberId(mem.getId().toString());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getPayMoney().setScale(2));
	                cr.setCustMemberType("0");
	                cr.setFee(new BigDecimal("0"));
	                cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
	                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY);
	              }
	            }
	          }
	        }
	        else
	        {
	          ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	          if (third != null) {
	            this.thirdPayRecordService.remove(third);
	          }
	          CommonResponse cq = new CommonResponse();
	          CommonRequst cr = new CommonRequst();
	          if (ob.getTransferType().equals("1")) {
	            cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	            cr.setRequsetNo(ob.getRecordNumber());
	            cr.setAmount(ob.getIncomMoney().setScale(2));
	            cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	            cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	          } else {
	        	List<WebBankcard> cardList = this.webBankCardService.getBycusterId(mem.getId());
	            WebBankcard card = new WebBankcard();
	            for (WebBankcard c : cardList) {
	              if (c.getBindCardStatus().equals("bindCard_status_success")) {
	                card = c;
	              }
	            }
	            Date date = new Date();
	            cr.setTransactionTime(date);
	            cr.setBankCardNumber(card.getCardNum());
	            cr.setBankCardType("0");
	            cr.setBankCode(card.getBankId());
	            cr.setAccountType(Integer.valueOf(0));
	            if (card.getProvinceId() != null) {
	              cr.setProvince(card.getProvinceId().toString());
	            }
	            if (card.getCityId() != null) {
	              cr.setCity(card.getCityId().toString());
	            }
	            cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	            cr.setCustMemberId(mem.getId().toString());
	            cr.setRequsetNo(ob.getRecordNumber());
	            cr.setAmount(ob.getPayMoney().setScale(2));
	            cr.setCustMemberType("0");
	            cr.setFee(new BigDecimal("5"));
	            cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	            cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	            cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY);
	          }

	        }

	      }
	      else
	      {
	        webMsgInstance("0", "0000", "没有选择对应的操作记录", "", "", "", "", "");
	        setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	      }
	    } else {
	      setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("login").getTemplateFilePath());
	    }
	    return "freemarker";
	  }
	
	/**
	 * 检查是否充值和取现成功
	 * @return
	 */
	public String check(){
	    BpCustMember mem = (BpCustMember)getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	    if (mem != null) {
	      ObAccountDealInfo ob = (ObAccountDealInfo)this.obAccountDealInfoService.get(this.loginId);
	      if (ob != null) {
	        String cardNo = ContextUtil.createRuestNumber();
	        CommonResponse commonResponse = new CommonResponse();
	        CommonRequst request = new CommonRequst();
	        request.setQueryRequsetNo(ob.getRecordNumber());
	        request.setQueryType(ob.getTransferType().equals("1") ? "RECHARGE_RECORD" : "WITHDRAW_RECORD");
	        request.setRequsetNo(cardNo);
	        request.setBussinessType(ThirdPayConstants.BT_QUERYPLATF);
	        request.setTransferName(ThirdPayConstants.TN_QUERYPLATF);
	        commonResponse = ThirdPayInterfaceUtil.thirdCommon(request);
	        if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
	        	List<CommonRecord> recordList = commonResponse.getRecordList();
	          for (CommonRecord record : recordList) {
	            if ((record.getStatus().equals("SUCCESS")) || (record.getStatus().equals("已通过"))) {
	              if (ob.getTransferType().equals("1")) {
	                Map mapp = new HashMap();
	                mapp.put("custermemberId", "");
	                mapp.put("requestNo", ob.getRecordNumber());
	                mapp.put("custmerType", "0");
	                mapp.put("dealRecordStatus", "2");
	                this.opraterBussinessDataService.recharge(mapp);
	              } else {
	                Map mapw = new HashMap();
	                mapw.put("custermemberId", "");
	                mapw.put("requestNo", ob.getRecordNumber());
	                mapw.put("custmerType", "0");
	                mapw.put("dealRecordStatus", "2");
	                this.opraterBussinessDataService.withDraw(mapw);
	              }
	              webMsgInstance("0", "0000", "已经支付成功，不允许重新支付", "", "", "", "", "");
	              setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	            } else if (record.getStatus().equals("INIT")) {
	              ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	              if (third != null) {
	                this.thirdPayRecordService.remove(third);
	              }
	              CommonResponse cq = new CommonResponse();
	              CommonRequst cr = new CommonRequst();
	              if (ob.getTransferType().equals("1")) {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getIncomMoney().setScale(2));
	                cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
	                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if (!cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY))
	                {
	                  webMsgInstance("0", "0000", "操作失败、请稍后操作", "", "", "", "", "");
	                  setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	                }
	              } else {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setCustMemberId(mem.getId().toString());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getPayMoney().setScale(2));
	                cr.setCustMemberType("0");
	                cr.setFee(new BigDecimal("0"));
	                cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
	                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if(cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
	                	
	                }
	              }

	            }

	            if (recordList.size() <= 0) {
	              ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	              if (third != null) {
	                this.thirdPayRecordService.remove(third);
	              }
	              CommonResponse cq = new CommonResponse();
	              CommonRequst cr = new CommonRequst();
	              if (ob.getTransferType().equals("1")) {
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getIncomMoney().setScale(2));
	                cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
	                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                if (cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY))
	                  continue;
	                webMsgInstance("0", "0000", "操作失败、请稍后操作", "", "", "", "", "");
	                setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	              }
	              else {
	            	List<WebBankcard> cardList = this.webBankCardService.getBycusterId(mem.getId());
	                WebBankcard card = new WebBankcard();
	                for (WebBankcard c : cardList) {
	                  if (c.getBindCardStatus().equals("bindCard_status_success")) {
	                    card = c;
	                  }
	                }
	                Date date = new Date();
	                cr.setTransactionTime(date);
	                cr.setBankCardNumber(card.getCardNum());
	                cr.setBankCardType("0");
	                cr.setBankCode(card.getBankId());
	                cr.setAccountType(Integer.valueOf(0));
	                if (card.getProvinceId() != null) {
	                  cr.setProvince(card.getProvinceId().toString());
	                }
	                if (card.getCityId() != null) {
	                  cr.setCity(card.getCityId().toString());
	                }
	                cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	                cr.setCustMemberId(mem.getId().toString());
	                cr.setRequsetNo(ob.getRecordNumber());
	                cr.setAmount(ob.getPayMoney().setScale(2));
	                cr.setCustMemberType("0");
	                cr.setFee(new BigDecimal("0"));
	                cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
	                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	                cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	                cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY);
	              }
	            }
	          }
	        }
	        else
	        {
	          ThirdPayRecord third = this.thirdPayRecordService.getByOrderNo(ob.getRecordNumber());
	          if (third != null) {
	            this.thirdPayRecordService.remove(third);
	          }
	          CommonResponse cq = new CommonResponse();
	          CommonRequst cr = new CommonRequst();
	          if (ob.getTransferType().equals("1")) {
	            cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	            cr.setRequsetNo(ob.getRecordNumber());
	            cr.setAmount(ob.getIncomMoney().setScale(2));
	            cr.setTransferName(ThirdPayConstants.BT_RECHAGE);
                cr.setBussinessType(ThirdPayConstants.TN_RECHAGE);
	            cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	          } else {
	        	List<WebBankcard> cardList = this.webBankCardService.getBycusterId(mem.getId());
	            WebBankcard card = new WebBankcard();
	            for (WebBankcard c : cardList) {
	              if (c.getBindCardStatus().equals("bindCard_status_success")) {
	                card = c;
	              }
	            }
	            Date date = new Date();
	            cr.setTransactionTime(date);
	            cr.setBankCardNumber(card.getCardNum());
	            cr.setBankCardType("0");
	            cr.setBankCode(card.getBankId());
	            cr.setAccountType(Integer.valueOf(0));
	            if (card.getProvinceId() != null) {
	              cr.setProvince(card.getProvinceId().toString());
	            }
	            if (card.getCityId() != null) {
	              cr.setCity(card.getCityId().toString());
	            }
	            cr.setThirdPayConfigId(mem.getThirdPayFlagId());
	            cr.setCustMemberId(mem.getId().toString());
	            cr.setRequsetNo(ob.getRecordNumber());
	            cr.setAmount(ob.getPayMoney().setScale(2));
	            cr.setCustMemberType("0");
	            cr.setFee(new BigDecimal("5"));
	            cr.setTransferName(ThirdPayConstants.TN_WITHDRAW);
                cr.setBussinessType(ThirdPayConstants.BT_WITHDRAW);
	            cq = ThirdPayInterfaceUtil.thirdCommon(cr);
	            cq.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY);
	          }

	        }

	      }
	      else
	      {
	        webMsgInstance("0", "0000", "没有选择对应的操作记录", "", "", "", "", "");
	        setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("message").getTemplateFilePath());
	      }
	    } else {
	      setSuccessResultValue(TemplateConfigUtil.getDynamicConfig("login").getTemplateFilePath());
	    }
	    return "freemarker";
	  }
	/**
	 * 代偿还款
	 * @return
	 */
	public String repayCompensatory(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			//生成第三需要的流水号
			String cardNo ="DCHK"+ContextUtil.createRuestNumber();
			//代偿款项Id
			String atoryId = this.getRequest().getParameter("atoryId");
			PlBidCompensatory atory = plBidCompensatoryService.get(Long.valueOf(atoryId));
			//还款金额
			String amount = this.getRequest().getParameter("checkMoney");
			BigDecimal checkMoney = new BigDecimal(amount);
			//剩余还款总金额
			if(atory.getCompensatoryMoney()==null){
				atory.setCompensatoryMoney(new BigDecimal(0));
			}
			if(atory.getPunishMoney()==null){
				atory.setPunishMoney(new BigDecimal(0));
			}
			if(atory.getBackPunishMoney()==null){
				atory.setBackPunishMoney(new BigDecimal(0));
			}
			if(atory.getBackCompensatoryMoney()==null){
				atory.setBackCompensatoryMoney(new BigDecimal(0));
			}
			//剩余金额=代偿总金额+罚息金额-已偿还的代偿金额-已偿还罚息金额-平账金额
			BigDecimal surplusMoney = atory.getCompensatoryMoney().add(atory.getPunishMoney()).subtract(atory.getBackPunishMoney()).subtract(atory.getBackCompensatoryMoney()).subtract(atory.getPlateMoney());
			//判断还款金额不能大于剩余还款总金额
			if(checkMoney.compareTo(surplusMoney)==1){
				webMsgInstance("0", Constants.CODE_SUCCESS, "还款金额不能大于剩余还款总金额",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "freemarker";
			}else{
				BigDecimal backPunishMoney = atory.getPunishMoney().subtract(atory.getBackPunishMoney());//本次应代偿的罚息
				BigDecimal backCompensatoryMoney = atory.getCompensatoryMoney().subtract(atory.getBackCompensatoryMoney());//本次应代偿的金额
				
				if(checkMoney.compareTo(backPunishMoney)==1){//还款金额大于罚息金额
					backCompensatoryMoney = checkMoney.subtract(backPunishMoney);//代偿本金
				}else{
					backPunishMoney = checkMoney;//代偿罚息
					backCompensatoryMoney = new BigDecimal(0);//本次应代偿的金额设为0
				}
				
				String[] ret=new String[3];
						/**
						 * 第三方交易：用户资格查询(检查用户是否具备交易资格)
						 */
						Object[] usercondition=bpCustMemberService.checkUserCondition(mem);
						if((Boolean) usercondition[0]){
							 String bidId = Common.getRandomNum(3);
							 CommonRequst cq=new CommonRequst();
							 cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
							 cq.setRequsetNo(cardNo);//请求流水号
							 cq.setContractNo("");//富友转账预授权合同号
							 cq.setTransferName(ThirdPayConstants.TN_REPLACEMONEY);//实际业务用途
							 cq.setBussinessType(ThirdPayConstants.BT_REPLACEMONEY);//接口类型
							 List<CommonDetail> list=new ArrayList<CommonDetail>();
							 CommonDetail dt=new CommonDetail();
							 dt.setAmount(amount);//转账金额
							 dt.setTargetUserType("plateForm");//收款人用户类型
							 //如果是担保户代偿的，要还款给担保户
							 if(null!=atory.getCompensatoryType() && "GURANEE".equals(atory.getCompensatoryType())){
								 BpCustMember  cmember=bpCustMemberService.get(atory.getCompensatoryP2PId());
								 dt.setTargetUserType("member");
								 dt.setTargetPlatformUserNo(cmember.getThirdPayFlagId());
								 cq.setLoaner_thirdPayflagId(cmember.getThirdPayFlagId());
							 }
							 list.add(dt);
							 cq.setDetailList(list);//转账记录
							 cq.setBidId(bidId);//标的号
							 cq.setPlanMoney(new BigDecimal(amount).multiply(new BigDecimal(1000000)));//标的金额
							 cq.setAmount(new BigDecimal(amount));//投标金额
							 cq.setTransactionTime(new Date());
							 if(mem.getCustomerType()!=null&&mem.getCustomerType()==1){
								 cq.setAccountType(1);//企业向平台转账
							 }else{
								 cq.setAccountType(0);//个人向平台转账
							 }
							 CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
							 if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
								PlBidCompensatoryFlow flow = new PlBidCompensatoryFlow();
								flow.setCompensatoryId(Long.valueOf(atoryId));
								flow.setRequestNo(cardNo);
								flow.setFlateMoney(new BigDecimal(0));//平账金额
								flow.setBackType("0");//偿还类型（0线上偿还，1线下登记回款） 
								flow.setBackStatus(0);
								flow.setBackPunishMoney(backPunishMoney);
								flow.setBackCompensatoryMoney(backCompensatoryMoney);
								plBidCompensatoryFlowService.save(flow);
								webMsgInstance("0", Constants.CODE_SUCCESS, "代偿还款申请提交成功",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
										DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							} else if(CommonResponse.RESPONSECODE_SUCCESS.equals(cr.getResponsecode())) {
								PlBidCompensatoryFlow flow = new PlBidCompensatoryFlow();
								flow.setCompensatoryId(Long.valueOf(atoryId));
								flow.setRequestNo(cardNo);
								flow.setFlateMoney(new BigDecimal(0));//平账金额
								flow.setBackType("0");//偿还类型（0线上偿还，1线下登记回款） 
								flow.setBackStatus(0);
								flow.setBackPunishMoney(backPunishMoney);
								flow.setBackCompensatoryMoney(backCompensatoryMoney);
								plBidCompensatoryFlowService.save(flow);
								Map<String,String> map = new HashMap<String, String>();
								map.put("requestNo", cardNo);
							    map.put("dealRecordStatus",  ObAccountDealInfo.DEAL_STATUS_2.toString());
								opraterBussinessDataService.doCompensatory(map);
								webMsgInstance("0", Constants.CODE_SUCCESS, "还款成功",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
										DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							}
						}else{
							forwardPage=usercondition[2].toString();
							webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						}
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	
	/**
	 * 绑定银行卡方法
	 * @return
	 */
	public String toThirdBankBind(){/*
		try{
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
			String mobile = this.getRequest().getParameter("mobile");
			if (mem != null) {
				//判断绑卡方式
				Object[]  type=ThirdPayInterfaceUtil.checkThirdType(ThirdPayConstants.BT_BINDCARD);
				mem = bpCustMemberService.get(mem.getId());
				//检查绑卡的条件是否满足
				String condition[] =this.checkBindBankCondition(mem,type);
				if(condition[0].equals(Constants.CODE_SUCCESS)){
					if(type!=null&&type[0]!=null&&type[0].equals("Page")){
						List<CsBank> listCsbank = csBankService.getAll();
						this.getRequest().setAttribute("listCsbank", listCsbank);
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BANK_BIND).getTemplateFilePath());
					}else{
						bindCard();
					}
				}else{
					if(mobile!=null&&"mobile".equals(mobile)){
						this.getResponse().sendRedirect(this.getBasePath()+"mobileLoginlogin.do");
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, condition[1].toString(),"", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}
			}else{
				if("mobile".equals(mobile)){
					this.getResponse().sendRedirect(this.getBasePath()+"mobileLoginlogin.do");
				}else{
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误：","", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		
		return "freemarker";
	*/
		

		try{
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if (mem != null) {
				QueryFilter qf = new QueryFilter();
				qf.addFilter("Q_bindCardStatus_S_EQ", WebBankcard.BINDCARD_STATUS_ACCEPT);
				qf.addFilter("Q_userFlg_S_EQ", mem.getThirdPayFlagId());
				List<WebBankcard> list = webBankCardService.getAll(qf);
				if(list!=null && list.size()>0){
					webMsgInstance("0", Constants.CODE_FAILED, "更换银行卡已受理","", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					bpCustMember = mem;
					List<CsBank> listCsbank = csBankService.getAll();
					this.getRequest().setAttribute("listCsbank", listCsbank);
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BANK_BIND).getTemplateFilePath());
				}
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误：","", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 检查绑卡的条件是否满足
	 * @param mem
	 * @param type
	 * @return
	 */
	private String[] checkBindBankCondition(BpCustMember mem,Object[] type) {
		// TODO Auto-generated method stub
		String [] ret=new String[2];
		try{
			List<WebBankcard> bankList = webBankCardService.getBycusterId(mem.getId());
			ret[0]=Constants.CODE_SUCCESS;
			ret[1]="可以绑定多张银行卡";
			/*if (bankList != null && bankList.size() > 0) {
				if(type!=null&&type[1]!=null&&type[1].equals("all")){
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="可以绑定多张银行卡";
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="只能绑定一张银行卡,已经绑定了银行卡，或者已经受理银行卡绑定业务";
				}
			}else{*/
				mem = bpCustMemberService.get(mem.getId());
				if(configMap.get("thirdPayType").toString().equals("0")){
					if (mem.getThirdPayFlagId() == null) {
						ret[0]=Constants.CODE_FAILED;
						ret[1]="请先开通第三方支付";
					}else{
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="可以绑定银行卡";
					} 
				}else{
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="可以绑定银行卡";
				}
			//}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="系统错误";
		}
		return ret;
	}
	/**
	 * 担保公司代偿还款方法
	 * 
	 * @return
	 */
	public String compensatory() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		String isMobile  = this.getRequest().getParameter("mobile");
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){
				mem = bpCustMemberService.get(mem.getId());
					mem = obSystemAccountService.getAccountSumMoney(mem);
					String repayMoney = this.getRequest().getParameter("notMoney");
					if (repayMoney != null && !"".equals(repayMoney)) {
						if (new BigDecimal(repayMoney).compareTo(new BigDecimal(0)) <= 0) {
							webMsgInstance("0", Constants.CODE_FAILED,"请先确认还款金额不能为零或负值", "", "", "", "", "");
						} else if (mem.getAvailableInvestMoney().compareTo(new BigDecimal(repayMoney)) >= 0) {
							String[] ret = this.compensatoryRepaymentData(this.getRequest(), mem);
							if (ret[0].equals(Constants.CODE_SUCCESS)) {
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							} else {
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							}
						} else {
							webMsgInstance("0", Constants.CODE_FAILED,"账户可用金额不足，请先充值", "", "", "", "", "");
						}
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "还款金额不能为空","", "", "", "", "");
					}
				
				
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}
		if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 准备还款数据
	 * @param request
	 * @param mem
	 * @return
	 */
	private String[] compensatoryRepaymentData(HttpServletRequest request,BpCustMember mem) {
		int i = 0;//还款给投资人成功个数
		int j = 0;//平台还款费用成功个数
		int k = 0;//还款给投资人失败个数
		int o = 0;//还款给投资人失败个数
		int t = 0;//统计本息还款的次数
		int u = 0;//统计平台费用还款的次
		String payintentPeriod = "";
		BigDecimal loanMoney = BigDecimal.ZERO;//本息实际收取费用
		BigDecimal feeMoney = BigDecimal.ZERO;//实际费用收取的金额
		String[] ret = new String[2];
		String isMobile = this.getRequest().getParameter("mobile");
		String planId = request.getParameter("planId");
		String intentDate = request.getParameter("intentDate");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (planId != null && !"".equals(planId) && intentDate != null && !"".equals(intentDate)) {
			PlBidPlan plan=plBidPlanService.get(Long.valueOf(planId));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//	BigDecimal bidTotalMoney = bpFundIntentService.getFinancialMoney(Long.valueOf(planId), "all");//标的还款总金额
			//查询还款记录
			List<CommonRequestInvestRecord> repayList=investPersonInfoService.getCompensatoryRepaymentList(planId, intentDate);
			PlBidPlan bidPlan = plBidPlanService.get(Long.valueOf(planId));
			String loanerConfigId1 = bpCustMemberService.getByLoginName(bidPlan.getReceiverP2PAccountNumber()).getThirdPayFlagId();
	    	QueryFilter filter = new QueryFilter(request);
			filter.getPagingBean().setPageSize(1000000000);
			filter.addFilter("Q_bidPlanId_L_EQ",planId);
			filter.addFilter("Q_intentDate_DL_GE",intentDate);
			filter.addFilter("Q_intentDate_DG_LE",intentDate);
			filter.addFilter("Q_isCheck_SN_EQ", "0");
			filter.addFilter("Q_isValid_SN_EQ", "0");
		    List<BpFundIntent>  list=bpFundIntentService.getAll(filter);
			String intentDate3 = "";
			Date intentDate1 = new Date();
			Date intentDate2=new Date();
			if(list.size()>0){
				payintentPeriod = list.get(0).getPayintentPeriod().toString();
				intentDate2 = list.get(0).getIntentDate();
				intentDate3 =  sdf2.format(intentDate2);
			}
			//查询平台还款日志，第三方还款记录
			boolean thirdpay = getThirdPayRecord(mem,planId,intentDate,ThirdPayConstants.BT_COMPENSATORY+planId.toString()+intentDate);
	/*		//本息金额
			BigDecimal backMoney = bpFundIntentService.getBackMoney(planId, peridId);
			//取罚息金额
			BigDecimal backAccMoney = bpFundIntentService.getBackAccMoney(planId, peridId);*/
			//获取担保公司的第三方账号
			BpCustRelation r =bpCustRelationService.getByLoanTypeAndId("b_guarantee",plan.getGuarantorsId());
			String loanerConfigId = "";
			String loanerConfigIdd = "";
			if(r!=null&&!"".equals(r.getP2pCustId())){
				loanerConfigId = r.getP2pCustId().toString();
			}
			//担保户第三方账号
			if(loanerConfigId!=null && !"".equals(loanerConfigId)){
				loanerConfigIdd = bpCustMemberService.get(Long.valueOf(loanerConfigId)).getThirdPayFlagId();
			}
			if(null!=repayList && repayList.size()>0&&thirdpay==true){
				Date currentRequestDate=new Date();
				Date lastRequestDate  =repayList.get(0).getRequestDate();
	    		if(lastRequestDate!=null&&currentRequestDate.getTime()-lastRequestDate.getTime()<=10*60*1000){//同一笔还款项目两次还款请求需要间隔十分钟
	    			long time=10-((currentRequestDate.getTime()-lastRequestDate.getTime())/60000);
	    			ret[0] = Constants.CODE_FAILED;
		    		ret[1] = "同一笔还款项目两次还款请求需要间隔十分钟，请"+time+"分钟后再请求还款";
	    		}else{
	    			if(!Constants.CODE_FAILED.equals(ret[0])){
	    				logger.info("担保公司代偿还款投资人还款列表:"+repayList);
	    				try{	    					
  		    				CommonRequst common = new CommonRequst();
	    					common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
	    					common.setTransferName(ThirdPayConstants.TN_UPDATEBID);
	    					common.setBidId(planId.toString());
	    					common.setBidType(common.HRY_BID);
	    					common.setBidIdStatus("2");//更新标的状态为还款中
	    					CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
	    					if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
	    						String requestNo = ContextUtil.createRuestNumber();//生成第三需要的流水号
								   if(list.size()>0){
									   for(BpFundIntent intent:list){
											Boolean flag=false;
											//根据保障方式选择要对账的记录
											if(null!=plan.getGuaranteeWay() && "1".equals(plan.getGuaranteeWay())){
												flag=!(intent.getFundType().equals("loanInterest") || intent.getFundType().equals("serviceMoney") || intent.getFundType().equals("consultationMoney") );
											}
			                             	if(null!=plan.getGuaranteeWay() && "2".equals(plan.getGuaranteeWay())){
												flag=!(intent.getFundType().equals("serviceMoney") || intent.getFundType().equals("consultationMoney")) ;
											}
			                            	if(null!=plan.getGuaranteeWay() && "3".equals(plan.getGuaranteeWay())){
												flag=true ;
											}
			                            	if(flag){
			                            		 intent.setRequestNo(requestNo);
												 intent.setRequestNOLoaner(requestNo);
												 bpFundIntentService.merge(intent);
			                            	}
									   }
								   }
								//借款人给投资人转账  
	    						CommonRequst cq=new CommonRequst();
								Date date = new Date();
								if(isMobile!=null&&"1".equals(isMobile)){
									cq.setIsMobile("1");
								}
								cq.setRepaymemntList(repayList);//还款list
				    			cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
				    			cq.setCustMemberId(mem.getId().toString());//用户id
				    			cq.setLoginname(mem.getLoginname());//登录名
				    			cq.setBidId(planId.toString());//标Id
				    			cq.setRequsetNo(requestNo);//请求流水号
				    			cq.setPlanMoney(plan.getBidMoney());//标的还款总金额
				    			cq.setIntentDate(intentDate1);//计划还款日期
				    			cq.setProId(CommonRequst.HRY_BID+planId.toString());//(汇付，标的id	)
				    			cq.setOrderDate(sdf1.format(new Date()));//请求交易时间
				    			cq.setOutCustId(mem.getThirdPayFlagId());//出账客户号
				    			cq.setFeeObjFlag("O");//向出款账户收取
				    			//判断当前用户是否是担保户
				    			BpCustRelation relation = bpCustRelationService.getP2pCustById(mem.getId());
				    			if(relation!=null && (relation.getOfflineCustType().equals("b_guarantee") || relation.getOfflineCustType().equals("p_guarantee"))){
				    				cq.setDzObject(loanerConfigId1);
				    			}else{
				    				cq.setDzObject("");//垫资/代偿对象
				    			}
				    			cq.setReqExt("");//入参扩展域
				    			cq.setUniqueId(ThirdPayConstants.BT_COMPENSATORY+planId.toString()+intentDate);//还款唯一标识
				    			cq.setBussinessType(ThirdPayConstants.BT_COMPENSATORY);//业务类型
				    			cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
				    			cq.setTransferName(ThirdPayConstants.TN_COMPENSATORY);//名称
				    			cq.setTransactionTime(date);
				    			cq.setGuaranteeWay(plan.getGuaranteeWay());//标是哪种代偿方式
				    			cq.setRemark1(intentDate);
				    			cq.setIntentDate(sdf.parse(intentDate));
				    		//	cq.setAmount(backMoney.add(backAccMoney));
				    			cq.setLoaner_thirdPayflagId(loanerConfigId);//借款人的第三方账号
				    			cq.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
				    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
				    			ret[0] =cr.getResponsecode();
								ret[1] =cr.getResponseMsg();
								ThirdPayRecord record = thirdPayRecordService.getByOrderNo(requestNo);
								if(cr!=null&&cr.getResponseList()!=null){
									if(cr.getResponseList().size()>0){
										for(CommonResponse response:cr.getResponseList()){
											if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
												i++;
												t++;
												if(response.getRemark()!=null){
													loanMoney = loanMoney.add(new BigDecimal(response.getRemark()));
												}else{
													loanMoney=new BigDecimal(0);
												}
												Map map = new HashMap();
												if(t==cr.getResponseList().size()){
													map.put("thirdConfigType","loanerFore");
													map.put("money", loanMoney.toString());
												}
												map.put("requestNo",response.getRequestNo());
												map.put("bidId",planId.toString());
												map.put("intentDate", intentDate3);
												if(record!=null){
													map.put("requestTime", sdf2.format(record.getRequestTime()));
												}else{
													map.put("requestTime", sdf2.format(new Date()));
												}
												//map.put("thirdConfigType", "loaner");
												opraterBussinessDataService.compensatory(map);
												//opraterBussinessDataService.repayment(map);
											}else{
												t++;
												k++;
											}
										}
										//发送短信
										Map<String, String> mapMessage = new HashMap<String, String>();
										mapMessage.put("key", "sms_compensatory");
										mapMessage.put("userId",bpCustMember.getId().toString());
										mapMessage.put("${projNumber}", plan.getBidProNumber());
										mapMessage.put("${projName}", plan.getBidProName());
										mapMessage.put("${payintentPeriod}", payintentPeriod);
										mapMessage.put("${code}", loanMoney.toString());
										String result =  sendMesService.sendSmsEmailMessage(mapMessage);
									}
								}
								if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){
									if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
										//企业用户 直接手动调用回调方法
										Map<String,String> map = new HashMap<String, String>();
		    							map.put("requestNo",requestNo);
		    							opraterBussinessDataService.umpayRepayment(map);	
		    							ret[0] = Constants.CODE_SUCCESS;
										ret[1] = "还款成功";
									}else{
										ret[0] = Constants.CODE_FAILED;
										ret[1] = "还款失败";
									}
								}else if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									ret[0] = Constants.CODE_SUCCESS;
									ret[1] = "还款成功";
								}else{
									ret[0] = Constants.CODE_SUCCESS;
									ret[1] = "还款失败";
								}
	    					}else{
	    						ret[0] = Constants.CODE_FAILED;
								ret[1] = "标的状态更改失败 ";
	    					}
    					}catch(Exception e){
							ret[0] = Constants.CODE_FAILED;
							ret[1] = "系统错误-报错了 ";
							e.printStackTrace();
						}
	    			}
	    		}
			}else{
		    	ret[0] = Constants.CODE_FAILED;
				ret[1] = "不能进行还款，因为没有为投资人生成款项计划。或者已经操作了还款";
		    }
		} else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "系统错误-还款投资人列表，标号或者期数没有值";
		}
		return ret;
	}
	/**
	 * 个人理财顾问还款方法
	 * 
	 * @return
	 */
	public String repayMentByFinancial() {
		System.out.println("个人理财顾问还款");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){
				mem = bpCustMemberService.get(mem.getId());
				mem = obSystemAccountService.getAccountSumMoney(mem);
				String repayMoney = this.getRequest().getParameter("notMoney");
				if (repayMoney != null && !"".equals(repayMoney)) {
					if (new BigDecimal(repayMoney).compareTo(new BigDecimal(0)) <= 0) {
						webMsgInstance("0", Constants.CODE_FAILED,"请先确认还款金额不能为零或负值", "", "", "", "", "");
					} else if (mem.getAvailableInvestMoney().compareTo(new BigDecimal(repayMoney)) >= 0) {
						String[] ret = this.thirdPayRepayMentByFinancial(this.getRequest(), mem);
						if (ret[0].equals(Constants.CODE_SUCCESS)) {
							if(configMap.get("thirdPayType").toString().equals("0")){
								this.getRequest().setAttribute("str", ret[1]);
								webMsgInstance("0", Constants.CODE_SUCCESS, ret[1],"", "", "", "", "");
								forwardPage=DynamicConfig.MESSAGE;
							}else{
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							}
						} else {
							webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
						}
					} else {
						webMsgInstance("0", Constants.CODE_FAILED,"账户可用金额不足，请先充值", "", "", "", "", "");
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "还款金额不能为空","", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 准备还款数据
	 * @param request
	 * @param mem
	 * @return
	 */
	private String[] thirdPayRepayMentByFinancial(HttpServletRequest request,BpCustMember mem) {
		int i = 0;//还款给投资人成功个数
		int j = 0;//平台还款费用成功个数
		int k = 0;//还款给投资人失败个数
		int o = 0;//还款给投资人失败个数
		int t = 0;//统计本息还款的次数
		int u = 0;//统计平台费用还款的次数
		BigDecimal loanMoney = BigDecimal.ZERO;//本息实际收取费用
		BigDecimal feeMoney = BigDecimal.ZERO;//实际费用收取的金额
		String[] ret = new String[2];
		String isMobile = this.getRequest().getParameter("mobile");
		String mmplanId = request.getParameter("mmplanId");
		String intentDate = request.getParameter("intentDate");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (mmplanId != null && !"".equals(mmplanId) && intentDate != null && !"".equals(intentDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			BigDecimal bidTotalMoney = plMmOrderAssignInterestService.getFinancialMoney(Long.valueOf(mmplanId), "all");//标的还款总金额
			//查询还款记录
			List<CommonRequestInvestRecord> repayList=plMmOrderAssignInterestService.getRepaymentList(Long.valueOf(mmplanId),intentDate);
			 PlManageMoneyPlan plan=plManageMoneyPlanService.get(Long.valueOf(mmplanId));
				QueryFilter filter = new QueryFilter(request);
				filter.getPagingBean().setPageSize(1000000000);
				filter.addFilter("Q_mmplanId_L_EQ",Long.valueOf(mmplanId));
				filter.addFilter("Q_intentDate_DL_GE",intentDate);
				filter.addFilter("Q_intentDate_DG_LE",intentDate);
				filter.addFilter("Q_isCheck_SN_EQ", "0");
				filter.addFilter("Q_isValid_SN_EQ", "0");
				List<PlMmOrderAssignInterest> list=plMmOrderAssignInterestService.getAll(filter);
				if(null!=list && list.size()>0){
					//将利息表的状态置1
		    		for(PlMmOrderAssignInterest temp:list){
		    			if(null!=temp.getFundType() && ("loanInterest".equals(temp.getFundType()) || "principalRepayment".equals(temp.getFundType()))){
		    			temp.setLockType("1");//锁定
						plMmOrderAssignInterestService.save(temp);
		    			}
		    		}
				}
			String intentDate3 = "";
			Date intentDate1 = new Date();
			String intentDate2="";
			if(repayList.size()>0){//同一期的不是一个intentDate吗？为什么要循环？？？
				for(CommonRequestInvestRecord record:repayList){
					if(record.getIntentDate()!=null){
						intentDate2 = sdf.format(record.getIntentDate());
						intentDate1 = record.getIntentDate();
					}
				}
			}
			//查询平台还款日志，第三方还款记录
			boolean thirdpay = getThirdPayRecord(mem,mmplanId,intentDate,ThirdPayConstants.BT_MMBACKMONEY+mmplanId.toString()+intentDate);
			//本息金额
			BigDecimal backMoney = plMmOrderAssignInterestService.findReturnMoney(Long.valueOf(mmplanId), intentDate, "('principalRepayment','loanInterest')");;
			//取罚息金额 ,UD计划没有罚息
			BigDecimal backAccMoney = new BigDecimal(0);
			//获取借款人的第三方账号
			String loanerName = plan.getMoneyReceiver();
			String loanerConfigId = "";
			if(loanerName!=null&&!"".equals(loanerName)){
				loanerConfigId = bpCustMemberService.getByLoginName(loanerName).getThirdPayFlagId();//(loanerName);
			}
			if(null!=repayList && repayList.size()>0&&thirdpay==true){
				Date currentRequestDate=new Date();
				Date lastRequestDate  =repayList.get(0).getRequestDate();
	    		if(lastRequestDate!=null&&currentRequestDate.getTime()-lastRequestDate.getTime()<=10*60*1000){//同一笔还款项目两次还款请求需要间隔十分钟
	    			long time=10-((currentRequestDate.getTime()-lastRequestDate.getTime())/60000);
	    			ret[0] = Constants.CODE_FAILED;
		    		ret[1] = "同一笔还款项目两次还款请求需要间隔十分钟，请"+time+"分钟后再请求还款";
	    		}else{
	    			if(!Constants.CODE_FAILED.equals(ret[0])){
	    				logger.info("自助还款投资人还款列表:"+repayList);
	    				try{	    					
  		    				CommonRequst common = new CommonRequst();
	    					common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
	    					common.setTransferName(ThirdPayConstants.TN_UPDATEBID);
	    					common.setBidId(mmplanId.toString());
	    					common.setBidType(common.HRY_PLANBUY);
	    					common.setBidIdStatus("2");//更新标的状态为还款中
	    					CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
	    					if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
	    						String requestNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
								   if(list.size()>0){
									   for(PlMmOrderAssignInterest pa:list){
											pa.setRequestNo(requestNo);
											pa.setInvestRequestNo(requestNo);
											pa.setRequestDate(currentRequestDate);
											pa.setRequestCount(pa.getRequestCount()!=null?pa.getRequestCount().intValue()+1:1);
										   plMmOrderAssignInterestService.save(pa);
									   }
								   }
								//借款人给投资人转账  
	    						CommonRequst cq=new CommonRequst();
								Date date = new Date();
								if(isMobile!=null&&"1".equals(isMobile)){
									cq.setIsMobile("1");
								}
								cq.setRepaymemntList(repayList);//还款list
				    			cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
				    			cq.setCustMemberId(mem.getId().toString());//用户id
				    			cq.setLoginname(mem.getLoginname());//登录名
				    			cq.setBidId(mmplanId.toString());//标Id
				    			cq.setRequsetNo(requestNo);//请求流水号
				    			cq.setPlanMoney(bidTotalMoney);//标的还款总金额
				    			cq.setIntentDate(intentDate1);//计划还款日期
				    			cq.setProId(CommonRequst.HRY_PLANBUY+mmplanId.toString());//(汇付，标的id	)
				    			cq.setOrderDate(sdf1.format(new Date()));//请求交易时间
				    			cq.setOutCustId(loanerConfigId);//出账客户号
				    			cq.setFeeObjFlag("O");//向出款账户收取
				    			cq.setDzObject("");//垫资/代偿对象
				    			cq.setReqExt("");//入参扩展域
				    			cq.setUniqueId(ThirdPayConstants.BT_MMBACKMONEY+mmplanId.toString()+intentDate2);//还款唯一标识
				    			cq.setBussinessType(ThirdPayConstants.BT_MMBACKMONEY);//业务类型
				    			cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
				    			cq.setTransferName(ThirdPayConstants.TN_MMBACKMONEY);//名称
				    			cq.setTransactionTime(date);
				    			cq.setAmount(backMoney.add(backAccMoney));
				    			cq.setLoaner_thirdPayflagId(loanerConfigId);//借款人的第三方账号
				    			cq.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
				    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
				    			ret[0] =cr.getResponsecode();
								ret[1] =cr.getResponseMsg();
								ThirdPayRecord record = thirdPayRecordService.getByOrderNo(requestNo);
								if(cr!=null&&cr.getResponseList()!=null){
									if(cr.getResponseList().size()>0){
										for(CommonResponse response:cr.getResponseList()){
											if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
												i++;
												t++;
												if(response.getRemark()!=null){
													loanMoney = loanMoney.add(new BigDecimal(response.getRemark()));
												}else{
													loanMoney=new BigDecimal(0);
												}
												Map map = new HashMap();
												if(t==cr.getResponseList().size()){
													map.put("thirdConfigType","loanerFore");
													map.put("money", loanMoney.toString());
												}
												map.put("requestNo",response.getRequestNo());
												map.put("bidId",mmplanId.toString());
												map.put("intentDate", intentDate2);
												map.put("thirdConfigType", "loaner");
												if(record!=null){
													map.put("requestTime", sdf2.format(record.getRequestTime()));
												}else{
													map.put("requestTime", sdf2.format(new Date()));
												}
												opraterBussinessDataService.financialRepayment(map);
											}else{
												t++;
												k++;
											}
										}
									}
								}
								//借款人给平台转账
	    						CommonRequst cq1=new CommonRequst();
								Date date1 = new Date();
								String requestNo1 = ContextUtil.createRuestNumber();//生成第三需要的流水号
								if(isMobile!=null&&"1".equals(isMobile)){
									cq1.setIsMobile("1");
								}
								cq1.setRepaymemntList(repayList);//还款list
								cq1.setBidId(mmplanId.toString());//标Id
				    			cq1.setRequsetNo(requestNo1);//请求流水号
				    			cq1.setPlanMoney(bidTotalMoney);//标的还款总金额
				    			cq1.setIntentDate(intentDate1);//计划还款日期
				    			cq1.setUniqueId(ThirdPayConstants.BT_MMBACKMONEY+mmplanId.toString()+intentDate2);//还款唯一标识
				    			cq1.setBussinessType(ThirdPayConstants.BT_MMBACKMONEY);//业务类型
				    			cq1.setTransferName(ThirdPayConstants.TN_MMBACKMONEY);//名称
				    		//	cq1.setRemark1(peridId);
				    			cq1.setThirdPayConfigId(loanerConfigId);//借款人的第三方账号
				    			cq1.setContractNo("");//预授权合同号 若有则为授权还款   若没有则是普通转账
				    			CommonResponse cr2=ThirdPayInterfaceUtil.thirdCommon(cq1);
				    			if(cr2!=null&&cr2.getResponseList()!=null){
				    				if(cr2.getResponseList().size()>0){
										for(CommonResponse response1:cr2.getResponseList()){
											if(response1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
												j++;
												u++;
												feeMoney = feeMoney.add(new BigDecimal(response1.getRemark()));
												Map map1 = new HashMap();
												map1.put("requestNo",response1.getRequestNo());
												map1.put("bidId",mmplanId.toString());
												map1.put("intentDate", intentDate1.toString());
												if(u == cr2.getResponseList().size()){
													map1.put("money", feeMoney.toString());
													map1.put("thirdConfigType", "loanerFee");
												}
												if(record!=null){
													map1.put("requestTime", sdf2.format(record.getRequestTime()));
												}
												//opraterBussinessDataService.repayment(map1);
												opraterBussinessDataService.financialRepayment(map1);
											}else{
												u++;
												o++;
											}
										}
				    				}
				    			}
								if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){
									if(cr.getResponsecode()!=null&&cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
										//企业用户 直接手动调用回调方法
										Map<String,String> map = new HashMap<String, String>();
		    							map.put("requestNo",requestNo);
		    							opraterBussinessDataService.umpayRepayment(map);	
		    							ret[0] = Constants.CODE_SUCCESS;
										ret[1] = "还款成功";
									}else{
										ret[0] = Constants.CODE_FAILED;
										ret[1] = "还款失败";
									}
								}else{
									ret[0] = Constants.CODE_SUCCESS;
									ret[1] = "还款成功";
								}
	    					}else{
	    						ret[0] = Constants.CODE_FAILED;
								ret[1] = "标的状态更改失败 ";
	    					}
    					}catch(Exception e){
							ret[0] = Constants.CODE_FAILED;
							ret[1] = "系统错误-报错了 ";
							e.printStackTrace();
						}
	    			}
	    		}
			}else{
		    	ret[0] = Constants.CODE_FAILED;
				ret[1] = "不能进行还款，因为没有为投资人生成款项计划。或者已经操作了还款";
		    }
		} else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "系统错误-还款投资人列表，标号或者期数没有值";
		}
		return ret;
	}
	/**
	 * 个人理财顾问审核提前赎回
	 * 
	 * @return
	 */
	public String repayMentAdvanceByFinancial() {
		System.out.println("个人理财顾问审核提前赎回");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		try{
			if((Boolean) usercondition[0]){
				mem = bpCustMemberService.get(mem.getId());
				mem = obSystemAccountService.getAccountSumMoney(mem);
				String repayMoney = this.getRequest().getParameter("notMoney");
				if (repayMoney != null && !"".equals(repayMoney)) {
					if (new BigDecimal(repayMoney).compareTo(new BigDecimal(0)) <= 0) {
						webMsgInstance("0", Constants.CODE_FAILED,"请先确认还款金额不能为零或负值", "", "", "", "", "");
					} else if (mem.getAvailableInvestMoney().compareTo(new BigDecimal(repayMoney)) >= 0) {
						String[] ret = this.thirdPayRepayMentAdvanceByFinancial(this.getRequest(), mem);
						if (ret[0].equals(Constants.CODE_SUCCESS)) {
							if(configMap.get("thirdPayType").toString().equals("0")){
								this.getRequest().setAttribute("str", ret[1]);
								//forwardPage=DynamicConfig.TOTHIRD;
								forwardPage=DynamicConfig.MESSAGE;
								webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
							}else{
							}
						} else {
							webMsgInstance("0", ret[0], ret[1], "", "", "", "","");
						}
					} else {
						webMsgInstance("0", Constants.CODE_FAILED,"账户可用金额不足，请先充值", "", "", "", "", "");
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "还款金额不能为空","", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 准备还款数据
	 * @param request
	 * @param mem
	 * @return
	 */
	private String[] thirdPayRepayMentAdvanceByFinancial(HttpServletRequest request,BpCustMember mem) {
		int i = 0;//还款给投资人成功个数
		int j = 0;//平台还款费用成功个数
		int k = 0;//还款给投资人失败个数
		int o = 0;//还款给投资人失败个数
		int t = 0;//统计本息还款的次数
		int u = 0;//统计平台费用还款的次数
		BigDecimal loanMoney = BigDecimal.ZERO;//本息实际收取费用
		BigDecimal feeMoney = BigDecimal.ZERO;//实际费用收取的金额
		String[] ret = new String[2];
		String isMobile = this.getRequest().getParameter("mobile");
		String earlyRedemptionId = request.getParameter("earlyRedemptionId");
		String liquid = this.getRequest().getParameter("liquid");
		if(liquid!=null && !"".equals(liquid)){
			liquidatedDamagesMoney = new BigDecimal(liquid);
		}
		//获取汇付的商户号的方法
		Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
		String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (earlyRedemptionId != null && !"".equals(earlyRedemptionId)) {
			PlEarlyRedemption  	plEarlyRedemption=plEarlyRedemptionService.get(Long.valueOf(earlyRedemptionId));
			PlManageMoneyPlanBuyinfo order=plManageMoneyPlanBuyinfoService.get(plEarlyRedemption.getOrderId());
			if(!order.getState().equals(Short.valueOf("8"))){
				//结算派息，
				List<PlMmOrderAssignInterest> pailist=plMmOrderAssignInterestService.mmplancreateList(plEarlyRedemption);
			    //转账金额
				BigDecimal sumMoney=new BigDecimal("0");
				//結算的利息总额
				BigDecimal loanInterestMoney=new BigDecimal("0");
				for(PlMmOrderAssignInterest pi:pailist){
					if(pi.getFundType().equals("liquidatedDamages")){
						sumMoney=sumMoney.subtract(pi.getPayMoney());
					}else if(pi.getFundType().equals("loanInterest")){ 
						sumMoney=sumMoney.add(pi.getIncomeMoney());
						loanInterestMoney=loanInterestMoney.add(pi.getIncomeMoney());
						
					}else if(pi.getFundType().equals("principalRepayment")){ 
						sumMoney=sumMoney.add(pi.getIncomeMoney());
					}
				}
			
				BpCustMember customer=bpCustMemberService.get(order.getInvestPersonId());
				List<CommonRequestInvestRecord> repayList =new ArrayList<CommonRequestInvestRecord>();
				CommonRequestInvestRecord cir = new CommonRequestInvestRecord();
				cir.setInvest_thirdPayConfigId(customer.getThirdPayFlagId());
				cir.setBidRequestNo(order.getDealInfoNumber());
				cir.setAmount(sumMoney);
				cir.setFee(new BigDecimal("0"));
				repayList.add(cir);
				
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date intentDate1=new Date();
			String  intentDate = sdf.format(intentDate1);
			PlManageMoneyPlan plan=plManageMoneyPlanService.get(order.getPlManageMoneyPlan().getMmplanId());
			String loanerName = plan.getMoneyReceiver();
			String loanerConfigId = "";
			if(loanerName!=null&&!"".equals(loanerName)){
				loanerConfigId = bpCustMemberService.getByLoginName(loanerName).getThirdPayFlagId();//(loanerName);
			}
			if(null!=repayList && repayList.size()>0){
				Date currentRequestDate=new Date();
				Date lastRequestDate  =plEarlyRedemption.getEarlyDate();
	    			if(!Constants.CODE_FAILED.equals(ret[0])){
	    				logger.info("自助还款投资人还款列表:"+repayList);
	    				try{	    					
  		    				CommonRequst common = new CommonRequst();
	    					common.setBussinessType(ThirdPayConstants.BT_UPDATEBID);
	    					common.setTransferName(ThirdPayConstants.TN_UPDATEBID);
	    					common.setBidId(plan.getMmplanId().toString());
	    					common.setBidType(common.HRY_PLANBUY);
	    					common.setBidIdStatus("2");//更新标的状态为还款中
	    					CommonResponse cr1=ThirdPayInterfaceUtil.thirdCommon(common);
	    					if(cr1!=null&&cr1.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
	    						String requestNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
	    						plEarlyRedemption.setEarlyDate(new Date());
	    						plEarlyRedemption.setLoanerRequestNo(requestNo);
	    						plEarlyRedemptionService.save(plEarlyRedemption);
								//借款人给投资人转账  
	    						CommonRequst cq=new CommonRequst();
								Date date = new Date();
								if(isMobile!=null&&"1".equals(isMobile)){
									cq.setIsMobile("1");
								}
								cq.setRepaymemntList(repayList);//还款list
				    			cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
				    			cq.setCustMemberId(mem.getId().toString());//用户id
				    			cq.setLoginname(mem.getLoginname());//登录名
				    			cq.setBidId(plan.getMmplanId().toString());//标Id
				    			cq.setRequsetNo(requestNo);//请求流水号
				    			cq.setPlanMoney(sumMoney);//标的还款总金额
				    			cq.setIntentDate(intentDate1);//计划还款日期
				    			cq.setFundType("plearly");
				    			cq.setProId(CommonRequst.HRY_PLANBUY+plan.getMmplanId().toString());//(汇付，标的id	)
				    			cq.setOrderDate(sdf1.format(new Date()));//请求交易时间
				    			cq.setOutCustId(mem.getThirdPayFlagId());//出账客户号
				    			cq.setInCustId(bpCustMemberService.getByLoginName(plEarlyRedemption.getCreator()).getThirdPayFlagId());
				    			cq.setFeeObjFlag(liquidatedDamagesMoney.compareTo(new BigDecimal(0))>0?"O":"");//向出款账户收取
				    			cq.setDzObject("");//垫资/代偿对象
				    			cq.setReqExt("");//入参扩展域
				    			cq.setUniqueId(ThirdPayConstants.BT_MMADVANCEUSER+plan.getMmplanId().toString()+intentDate);//还款唯一标识
				    			cq.setBussinessType(ThirdPayConstants.BT_MMADVANCEUSER);//业务类型
				    			cq.setAccountType(mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)?1:0);//判断企业类型
				    			cq.setTransferName(ThirdPayConstants.TN_MMADVANCEUSER);//名称
				    			cq.setTransactionTime(date);
				    			cq.setAmount(sumMoney);
				    			if(plateFormNo!=null && !"".equals(plateFormNo)){
				    				cq.setDivDetails(liquidatedDamagesMoney.compareTo(new BigDecimal(0))>0?"[{\"DivCustId\":\""+plateFormNo.toString()+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+liquidatedDamagesMoney+"\"}]":"");//,"borrowerCustId":"6000060002900106","borrowerRate":"1.00"}]//分账账户串
				    			}
				    			cq.setLoaner_thirdPayflagId(loanerConfigId);//借款人的第三方账号
				    			cq.setSubOrdId(order.getDealInfoNumber());//投标时的流水号
				    			cq.setSubOrdDate(sdf.format(order.getBuyDatetime()));//投标日期
				    			cq.setOutAcctId("");
				    			cq.setInAcctId("");
								if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
									cq.setAccountType(1);
								}else{//借款人是个人
									cq.setAccountType(0);
								}
								cq.setRetInterest(loanInterestMoney.setScale(2).toString());
								cq.setAmount(sumMoney);
				    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
				    			ret[0] =cr.getResponsecode();
								ret[1] =cr.getResponseMsg();
								if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									ThirdPayRecord record = thirdPayRecordService.getByOrderNo(requestNo);
									Map map = new HashMap();
									map.put("requestNo",requestNo);
									map.put("bidId",plan.getMmplanId().toString());
									map.put("intentDate", intentDate);
									map.put("dealMoney", sumMoney.toString());
									map.put("userId", String.valueOf(mem.getId()));
									opraterBussinessDataService.advanceFinancialRepayment(map);
									ret[0] = Constants.CODE_SUCCESS;
									ret[1] = "提前支取审核成功";
								}else{
									ret[0] = Constants.CODE_FAILED;
									ret[1] = "提前支取审核失败";
								}
	    					}else{
	    						ret[0] = Constants.CODE_FAILED;
								ret[1] = "标的状态更改失败 ";
	    					}
    					}catch(Exception e){
							ret[0] = Constants.CODE_FAILED;
							ret[1] = "系统错误-报错了 ";
							e.printStackTrace();
						}
	    			}
			}else{
		    	ret[0] = Constants.CODE_FAILED;
				ret[1] = "不能进行还款，因为没有为投资人生成款项计划。或者已经操作了还款";
		    }
			}else{
				ret[0] = Constants.CODE_FAILED;
				ret[1] = "系统错误-提前支取失败，已经支取过";
			} 
		}
	     else {
			ret[0] = Constants.CODE_FAILED;
			ret[1] = "系统错误-没有提前赎回申请";
		}
		return ret;
	}

	/**
	 * 提示信息
	 * @return
	 */
	public String mentMessage(){
		bpCustMember = (BpCustMember)getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String resultCode = this.getRequest().getParameter("resultCode");
		String resultMsg = this.getRequest().getParameter("resultMsg");
		webMsgInstance("0", resultCode,resultMsg, "","", "", "", "");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
	public static void main(String args[]){
		Map map = new HashMap();
		Map mapI = 	new HashMap();
        mapI.put("DivCustId", "232322");//DivCustId
        mapI.put("DivAcctId", "333333");//DivAcctId
        mapI.put("DivAmt", "2.00");//DivAmt
        JSONArray json1 = JSONArray.fromObject(mapI);
		map.put("OrdId", "123456");//ordId
		map.put("SubOrderId", "1111111");//subOrderId
		map.put("IncustId", "1212121211");//incustId
		map.put("Inaccid", "222222");//inaccid
		map.put("Transamt", "20.00");//transamt
        map.put("DzBorrCustId", "");//DzBorrCustId
        map.put("FeeObjFlag", "I");//FeeObjFlag
        map.put("Fee", "2.00");//Fee
        map.put("DivDetails", json1.toString());//DivDetails
        JSONArray json = JSONArray.fromObject(map); 
        Map mapII = new HashMap();
        mapII.put("InDetails",json);
        JSONObject json2 = JSONObject.fromObject(mapII); 
        System.out.println(json2);	
	}
	
	/**
	 * 更换银行卡
	 * @return
	 * @throws IOException 
	 */
	public String updateBank() throws IOException{
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String mobile =  this.getRequest().getParameter("mobile");
		if (mem != null) {
				mem = bpCustMemberService.get(mem.getId());
				//先判断用户是否进行了实名认证
				if(StringUtils.isNotEmpty(mem.getIsCheckCard()) && "1".equals(mem.getIsCheckCard())&&mem.getTruename()!=null && mem.getThirdPayFlagId() != null){
					String orderNum=ContextUtil.createRuestNumber();//绑卡流水号
					CommonRequst commonRequst=new CommonRequst();
					if(mobile!=null&&!"".equals(mobile)&&("1".equals(mobile))){
						commonRequst.setIsMobile("1");//判断手机端操作 
					}
					commonRequst.setThirdPayConfigId(mem.getThirdPayFlagId());
					commonRequst.setThirdPayConfigId0(mem.getThirdPayFlag0());
					commonRequst.setRequsetNo(orderNum);
					commonRequst.setBussinessType(ThirdPayConstants.BT_REPLACECARD);
					commonRequst.setTransferName(ThirdPayConstants.TN_REPLACECARD);
					CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequst);
					if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
						//绑卡申请成功以后，将原来的卡修改为申请取消状态
						List<WebBankcard> list = webBankCardService.getBycustAndState(mem.getId(), WebBankcard.BINDCARD_STATUS_SUCCESS);
						if (list != null && list.size() > 0) {
							for (WebBankcard webBankcard : list) {
								webBankcard.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
							}
						}
						
						WebBankcard card = new WebBankcard();
						card.setCustomerId(mem.getId());
						card.setCustomerType(Short.valueOf("0"));
						card.setUsername(mem.getTruename());
						card.setAccountname(mem.getTruename());
						card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_REPARE);
						card.setRequestNo(orderNum);
						card.setThirdConfig(configMap.get("thirdPayConfig").toString());
						card.setUserFlg(mem.getThirdPayFlagId());
						webBankCardService.save(card);
						this.getRequest().setAttribute("str", commonResponse.getResponseMsg());
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TOTHIRD).getTemplateFilePath());
						return "freemarker";
					}else if (commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
							String cardNo = this.getRequest().getParameter("cardNo");
							String bankId = this.getRequest().getParameter("webBankcard.bankId");
							
							//绑卡成功以后将别的卡都修改为取消
							List<WebBankcard> bankList = webBankcardService.getBycustAndState(mem.getId(),WebBankcard.BINDCARD_STATUS_SUCCESS);
							for(WebBankcard wk : bankList){
								wk.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
								webBankcardService.merge(wk);
							}
							if(bankId!=null&&!bankId.equals("")&&cardNo!=null&&!cardNo.equals("")){
								CsBank csbank = csBankService.get(Long.valueOf(bankId));
								if(csbank!=null){
									WebBankcard card = new WebBankcard();
									card.setCustomerId(mem.getId());
									card.setCustomerType(Short.valueOf("0"));
									card.setUsername(mem.getTruename());
									card.setAccountname(mem.getTruename());
									card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
									card.setRequestNo(orderNum);
									card.setUserFlg(mem.getThirdPayFlagId());
									card.setCardNum(cardNo);
									card.setBankId(csbank.getRemarks());
									card.setBankname(csbank.getBankname());
									card.setCustomerId(mem.getId());
									card.setProvinceId(Long.valueOf("1"));
									card.setCityId(Long.valueOf("1001"));
									card.setThirdConfig(mem.getThirdPayConfig());
									card.setUserFlg(mem.getThirdPayFlagId());
									webBankCardService.save(card);
									webMsgInstance("0", Constants.CODE_SUCCESS, "修改银行卡申请成功", "","", "", "", "");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								}else{
									webMsgInstance("0", Constants.CODE_FAILED, "开户银行选择错误", "","", "", "", "");
									this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								}
							}else{
								webMsgInstance("0", Constants.CODE_FAILED, "银行卡信息填写错误", "","", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						}else {
							webMsgInstance("0", commonResponse.getResponsecode(), commonResponse.getResponseMsg(), "", "", "", "",	"");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					}else {
						webMsgInstance("0", Constants.CODE_FAILED, "请先进行实名认证，再进行绑卡操作", "","", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
		}else{
			if("mobile".equals(mobile)){
				this.getResponse().sendRedirect(this.getBasePath()+"mobileLoginlogin.do");
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}
		return "freemarker";
	}
	/**
	 * 更换银行卡
	 * 做为保留方法
	 * @return
	 */
	public String updateBankOld(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			QueryFilter qf = new QueryFilter();
			qf.addFilter("Q_bindCardStatus_S_EQ", WebBankcard.BINDCARD_STATUS_ACCEPT);
			qf.addFilter("Q_userFlg_S_EQ", mem.getThirdPayFlagId());
			List<WebBankcard> list = webBankCardService.getAll(qf);
			if(list!=null && list.size()>0){
				webMsgInstance("0", Constants.CODE_FAILED, "更换银行卡已受理","", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}else{
				bpCustMember = mem;
				List<CsBank> listCsbank = csBankService.getAll();
				this.getRequest().setAttribute("listCsbank", listCsbank);
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.BANK_BIND).getTemplateFilePath());
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}
	
	public void test(){
		CommonRequst common = new CommonRequst();
		common.setThirdPayConfigId("6000060003107668");
		common.setTransferName(ThirdPayConstants.TN_CHECKREIGSTER);//实际业务用途
		common.setBussinessType(ThirdPayConstants.BT_CHECKREIGSTER);//接口类型
		CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(common);
	}
	
	public void test1() {
		String requestNo = this.getRequest().getParameter("requestNo");
		String interes = this.getRequest().getParameter("interes");
		ThirdPayRecord thirdPayRecord = null;
		if(StringUtils.isNotEmpty(requestNo)){
			thirdPayRecord = thirdPayRecordService.getByOrderNo(requestNo);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("bidId",thirdPayRecord.getBidId().toString());//标id
		map.put("intentDate",thirdPayRecord.getIntentDate().toString());//计划还款日期
		map.put("requestTime", thirdPayRecord.getRequestTime().toString());//请求接口时间
		map.put("requestNum", thirdPayRecord.getRequestNum().toString());//请求接口次数
		map.put("requestNo", requestNo);
		map.put("thirdPayConfig", thirdPayRecord.getThirdPayConfig());
		map.put("slEarlyRepaymentId", thirdPayRecord.getRemark1());
		map.put("thirdConfigType", "loaner");
		CommonRequst common = new CommonRequst();
		
		
		//还款完成以后，先调用投资人回款
		List<InvestBean> list = new ArrayList<InvestBean>();
		CommonRequst commonQ = new CommonRequst();
		String orderNumFirst =ContextUtil.createRuestNumber();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
		commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
		commonQ.setLoanNoList(thirdPayRecord.getRemark2());//三方标的标识
		//查询还款记录
		List<CommonRequestInvestRecord> repayList=investPersonInfoService.getRepaymentList(thirdPayRecord.getBidId().toString(),thirdPayRecord.getRemark1());
		//获取参数
		for (CommonRequestInvestRecord record : repayList) {
			String orderNum =ContextUtil.createRuestNumber();
			InvestBean invest = new InvestBean();
			if (StringUtils.isNotEmpty(interes)) {
				invest.setCapital("0");//本金
				invest.setInterest(interes);//利息
//				invest.setInterestFee(record.getFee().toString());//管理费
				invest.setInterestFee("0");//管理费，这个费用是投资人给平台的
				invest.setRateInterest("0");//罚息
			}else {
				if (record.getPrincipal().compareTo(BigDecimal.ZERO)==0) {
					invest.setCapital("0");//本金
				}else {
					invest.setCapital(record.getPrincipal().toString());//本金
				}
				if (record.getInterest().compareTo(BigDecimal.ZERO)==0) {
					invest.setInterest("0");//利息
				}else {
					if (record.getAccrual().compareTo(BigDecimal.ZERO)==0) {
						invest.setInterest(record.getInterest().toString());//利息
					}else {
						invest.setInterest(record.getInterest().add(record.getAccrual()).toString());
					}
				}
				
//				invest.setInterestFee(record.getFee().toString());//管理费
				invest.setInterestFee("0");//管理费，这个费用是投资人给平台的
				invest.setRateInterest("0");//平台给投资人的加息
			}
			invest.setInvestAccountNo(record.getInvest_thirdPayConfigId());//三方账号
			invest.setInvestUserName(record.getInvest_thirdPayConfigId0());//三方有用户名
			invest.setInvestOrderDate(sdf.format(record.getRequestDate()));//原有投资订单日期
			invest.setInvestOrderNo(record.getBidRequestNo());//原有投资订单号
			invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
			invest.setOrderNo(orderNum);//当前流水订单号
			list.add(invest);
		}
		commonQ.setInvestList(list);
		commonQ.setRequsetNo(orderNumFirst);
		commonQ.setThirdPayConfigId(null);
		commonQ.setThirdPayConfigId0(null);
		CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(commonQ);
		if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
				opraterBussinessDataService.repayment(map);
		}
	}



	/**
	 * 测试数据迁移文件上传接口
	 * @return
	 */
	public String migration() {
		String fileName = this.getRequest().getParameter("fileName");
		String type = this.getRequest().getParameter("type");
		String totalCount = this.getRequest().getParameter("count");
		String orderNum = this.getRequest().getParameter("orderNum");
		String result = "";
		if (StringUtils.isNotEmpty(fileName)) {
			CommonRequst common = new CommonRequst();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			common.setBussinessType(ThirdPayConstants.BT_UPDATEFILE);//业务类型
			common.setTransferName(ThirdPayConstants.TN_UPDATEFILE);//名称
			common.setRequsetNo(orderNum);
			common.setContact(fileName);//上传文件名称
			common.setBidType(type);//迁移类型  1-用户，2-资金，3-投资，4-标的，5还款
			common.setBidProNumber(totalCount);//文件内参数总条数
			//调用三方接口
			CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
			if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
				System.out.println(thirdCommon.getResponseMsg());
			}
			result = thirdCommon.getResponseMsg();
		}
		setJsonString(result);
		return SUCCESS;
	}
	
	/**
	 * 查询标的三方账户信息
	 * @return
	 */
	public String queryLoan() {
		String bidId = this.getRequest().getParameter("bidId");
		String result = "";
		if (StringUtils.isNotEmpty(bidId)) {
			PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
			if (plBidPlan != null) {
				CommonRequst common = new CommonRequst();
				String orderNum =ContextUtil.createRuestNumber();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				common.setBussinessType(ThirdPayConstants.BT_QUERYLOAN);//业务类型
				common.setTransferName(ThirdPayConstants.TN_QUERYLOAN);//名称
				common.setThirdPayConfigId(null);
				common.setThirdPayConfigId0(null);
				common.setBidProNumber(plBidPlan.getLoanTxNo());
				CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
				
				if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					result = "bidId="+bidId+ "的标，存管账户现有资金："+thirdCommon.getWithDrawbalance();
					result += "，募集资金："+thirdCommon.getAmount();
					System.out.println(result);
				}
			}
		}
		setJsonString(result);
		return SUCCESS;
	}
	
	
	/**
	 * 查询交易记录
	 * @return
	 */
	public String queryDealInfo() {
		String orderNo = this.getRequest().getParameter("orderNo");
		String result = "";
		if (StringUtils.isNotEmpty(orderNo)) {
			ObAccountDealInfo dealinfo=obAccountDealInfoService.getByOrderNumber(orderNo, "", ObAccountDealInfo.T_RECHARGE.toString(),  "0");
			if (dealinfo != null) {
				CommonRequst common = new CommonRequst();
				String orderNum =ContextUtil.createRuestNumber();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				common.setRequsetNo(orderNum);//请求流水号
				common.setBussinessType(ThirdPayConstants.BT_QUERYDEAL);//业务类型
				common.setTransferName(ThirdPayConstants.TN_QUERYDEAL);//名称
				common.setQueryType(FuDian.QUERYTYPE01);
				common.setThirdPayConfigId(null);
				common.setThirdPayConfigId0(null);
				common.setOldBidRequestNo(dealinfo.getRecordNumber());
				common.setSubOrdDate(sdf.format(dealinfo.getCreateDate()));//查询日期  定长8 (订单的日期)
				
				CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
				
				if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					result = "orderNo="+orderNo+ "记录返回状态："+thirdCommon.getState();
					System.out.println(result);
				}
			}
		}
		setJsonString(result);
		return SUCCESS;
	}
	
	
	
	/**
	 * 查询用户的资金明细
	 * @return
	 */
	public String queryUserInfo() {
		String memId = this.getRequest().getParameter("memId");//用户id
		String date = this.getRequest().getParameter("date");//查询日期
		String result = "";
		if (StringUtils.isNotEmpty(memId)&&StringUtils.isNotEmpty(date)) {
			bpCustMember = bpCustMemberService.get(Long.valueOf(memId));
			if (bpCustMember != null) {
				CommonRequst common = new CommonRequst();
				String orderNum =ContextUtil.createRuestNumber();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				common.setBussinessType(ThirdPayConstants.BT_ACCOUNTQUERY);//业务类型
				common.setTransferName(ThirdPayConstants.TN_ACCOUNTQUERY);//名称
				common.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());
				common.setThirdPayConfigId0(bpCustMember.getThirdPayFlag0());
				common.setSubOrdDate(date);//查询日期  定长8
				CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
				if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					result = "memId="+memId+ "的用户交易流水："+thirdCommon.getRequestInfo();
					System.out.println(result);
				}
			}
		}else {
			setJsonString("用户id-》memId或查询日期date为空");
		}
		setJsonString(result);
		return SUCCESS;
	}
	
	
	
	/**
	 * 查询标的三方账户流水
	 * @return
	 */
	public String queryLoanBalance() {
		String bidId = this.getRequest().getParameter("bidId");
		String result = "";
		if (StringUtils.isNotEmpty(bidId)) {
			PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
			if (plBidPlan != null) {
				CommonRequst common = new CommonRequst();
				String orderNum =ContextUtil.createRuestNumber();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				common.setBussinessType(ThirdPayConstants.BT_QUERYLOANBALANCE);//业务类型
				common.setTransferName(ThirdPayConstants.TN_QUERYLOANBALANCE);//名称
				common.setThirdPayConfigId(null);
				common.setThirdPayConfigId0(null);
				common.setBidProNumber(plBidPlan.getLoanTxNo());
				if (StringUtils.isNotEmpty(plBidPlan.getLoanAccNo())) {
					common.setCapAcntNo(plBidPlan.getLoanAccNo());
				}else {
					String loanAccNo = this.getRequest().getParameter("loanAccNo");
					if (StringUtils.isNotEmpty(loanAccNo)) {
						common.setCapAcntNo(loanAccNo);
					}else {
						setJsonString("标的账号为空！！！");
						return SUCCESS;
					}
				}
				
				CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
				if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
					result = "流水记录："+thirdCommon.getRemark2();
					System.out.println(result);
				}
			}else {
				result = "标的不存在";
			}
		}
		setJsonString(result);
		return SUCCESS;
	}

	/**
	 * 个人回款接口
	 * @return
	 */
	public String personalTrans() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String bidId = this.getRequest().getParameter("bidId");
		String memid = this.getRequest().getParameter("memid");
		String money = this.getRequest().getParameter("money");
		if(StringUtils.isNotEmpty(bidId) && StringUtils.isNotEmpty(memid) && StringUtils.isNotEmpty(money)) {
			PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
			if(plBidPlan != null) {
				List<InvestBean> list = new ArrayList<InvestBean>();
				CommonRequst commonQ = new CommonRequst();
				String orderNum =ContextUtil.createRuestNumber();
				commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
				commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
				commonQ.setLoanNoList(plBidPlan.getLoanTxNo());//三方标的标识

				String orderNuminvest =ContextUtil.createRuestNumber();
				InvestBean invest = new InvestBean();
				invest.setInterest(money);
				invest.setCapital("0");//本金
				invest.setInterestFee("0");//管理费，这个费用是投资人给平台的
				invest.setRateInterest("0");//平台给投资人的加息
				bpCustMember = bpCustMemberService.get(Long.valueOf(memid));

				List<PlBidInfo> infos = plBidInfoService.getIntentInfo(plBidPlan.getBidId(), bpCustMember.getId());
				if (infos != null && infos.size() > 0){
					PlBidInfo info = infos.get(0);
					invest.setInvestAccountNo(bpCustMember.getThirdPayFlagId());//三方账号
					invest.setInvestUserName(bpCustMember.getThirdPayFlag0());//三方有用户名
					invest.setInvestOrderDate(sdf.format(info.getBidtime()));//原有投资订单日期
					invest.setInvestOrderNo(info.getOrderNo());//原有投资订单号
					invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
					invest.setOrderNo(orderNuminvest);//当前流水订单号
					list.add(invest);
					commonQ.setInvestList(list);
					commonQ.setRequsetNo(orderNum);
					commonQ.setThirdPayConfigId(null);
					commonQ.setThirdPayConfigId0(null);
					CommonResponse thirdCommonQ = ThirdPayInterfaceUtil.thirdCommon(commonQ);
					if (thirdCommonQ.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						setJsonString("转账成功");
					}else{
						setJsonString("转账失败");
					}
				}else {
					setJsonString("无投资记录");
				}
			}else {
				setJsonString("标的为空");
			}
		}
		return SUCCESS;
	}


	/**
	 * 分配标的三方账户剩余金额，按照投资比例分
	 * @return
	 */
	public String distributionBidPlanMoney() {
		List<PlBidPlan> listPlan = plBidPlanService.getListByState("'7','10'");
		Iterator<PlBidPlan> iterator = listPlan.iterator();
		int num = 0;
		while (iterator.hasNext()) {
			PlBidPlan plBidPlan = (PlBidPlan) iterator.next();
			CommonRequst common = new CommonRequst();
			String orderNum =ContextUtil.createRuestNumber();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			common.setBussinessType(ThirdPayConstants.BT_QUERYLOAN);//业务类型
			common.setTransferName(ThirdPayConstants.TN_QUERYLOAN);//名称
			common.setThirdPayConfigId(null);
			common.setThirdPayConfigId0(null);
			common.setBidProNumber(plBidPlan.getLoanTxNo());
			CommonResponse thirdCommon = ThirdPayInterfaceUtil.thirdCommon(common);
			
			if (thirdCommon.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
				System.out.println("标的id为"+plBidPlan.getBidId()+"的标，账户余额为："+thirdCommon.getWithDrawbalance());
				BigDecimal surplusMoney = BigDecimal.ZERO;//剩余金额
				BigDecimal proportion = BigDecimal.ZERO;//比例
				BigDecimal proportionMoney = BigDecimal.ZERO;//根据比例算出来的金额
				//如果标的的账户里还有钱
				if (thirdCommon.getWithDrawbalance().compareTo(BigDecimal.ZERO)>0) {
					num++;
					List<InvestBean> list = new ArrayList<InvestBean>();
					CommonRequst commonQ = new CommonRequst();
					String orderNumFirst =ContextUtil.createRuestNumber();
					commonQ.setBussinessType(ThirdPayConstants.BT_CALLBACK);//业务类型
					commonQ.setTransferName(ThirdPayConstants.TN_CALLBACK);//名称
					commonQ.setLoanNoList(plBidPlan.getLoanTxNo());//三方标的标识
					
					plBidPlan.getBidMoney();
					List<PlBidInfo> bidInfos = plBidInfoService.getIntentInfo(plBidPlan.getBidId(), "");
					PlBidInfo info = null;
					for (Integer i = 0;i<bidInfos.size();i++) {
						info = bidInfos.get(i);
						String orderNuminvest =ContextUtil.createRuestNumber();
						InvestBean invest = new InvestBean();
						if (bidInfos.size() ==1) {//如果只有一个人投资，标的剩余金额都给这个人
							invest.setInterest(thirdCommon.getWithDrawbalance().toString());
						}else if(i == bidInfos.size()-1) {//如果是最后一个人
							invest.setInterest(thirdCommon.getWithDrawbalance().subtract(surplusMoney).toString());
						}else {
							//根据投资金额计算比例
							proportion = info.getUserMoney().divide(plBidPlan.getBidMoney()).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
							//根据比例计算金额
							proportionMoney = thirdCommon.getWithDrawbalance().multiply(proportion).setScale(2, BigDecimal.ROUND_HALF_UP);
							//计算剩余金额
							surplusMoney = thirdCommon.getWithDrawbalance().subtract(proportionMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
							//利息
							invest.setInterest(proportionMoney.toString());
						}
						
						invest.setCapital("0");//本金
						invest.setInterestFee("0");//管理费，这个费用是投资人给平台的
						invest.setRateInterest("0");//平台给投资人的加息
						bpCustMember = bpCustMemberService.get(info.getUserId());
						
						invest.setInvestAccountNo(bpCustMember.getThirdPayFlagId());//三方账号
						invest.setInvestUserName(bpCustMember.getThirdPayFlag0());//三方有用户名
						invest.setInvestOrderDate(sdf.format(info.getBidtime()));//原有投资订单日期
						invest.setInvestOrderNo(info.getOrderNo());//原有投资订单号
						invest.setOrderDate(sdf.format(new Date()));//当前交易订单日期，格式yyyyMMdd
						invest.setOrderNo(orderNuminvest);//当前流水订单号
						list.add(invest);
					}
					commonQ.setInvestList(list);
					commonQ.setRequsetNo(orderNumFirst);
					commonQ.setThirdPayConfigId(null);
					commonQ.setThirdPayConfigId0(null);
					CommonResponse thirdCommonQ = ThirdPayInterfaceUtil.thirdCommon(commonQ);
					if (thirdCommonQ.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						System.out.println("标的id:"+plBidPlan.getBidId()+"的标的账户余额分配ok");
					}
				}
			}
			
		}
		System.out.println("分配标的金额的标的数量："+num);
		return SUCCESS;
	}




	/**
	 * 核对个人金额，与三方不同则以三方为准
	 * @return
	 */
	public String proofreadMoney() {
		int num = 0;
		//查询所有实名认证的用户
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_isCheckCard_S_EQ", "1");
		List<BpCustMember> list = bpCustMemberService.getAll(filter);
		System.out.println("实名认证的用户有："+list.size());
		ObSystemAccount account = new ObSystemAccount();
		CommonResponse commonResponse = new CommonResponse();
		CommonRequst common = new CommonRequst();
		for (BpCustMember bpCustMember : list) {
			account = obSystemAccountService.getByInvrstPersonId(bpCustMember.getId());

			String orderNum =ContextUtil.createRuestNumber();
			common.setRequsetNo(orderNum);//请求流水号
			common.setTransactionTime(new Date());//用户查询日期
			common.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());
			common.setThirdPayConfigId0(bpCustMember.getThirdPayFlag0());
			common.setBussinessType(ThirdPayConstants.BT_QUERYUSER);
			common.setTransferName(ThirdPayConstants.TN_QUERYUSER);
			commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);
			if(commonResponse.getResponsecode()!=null&&commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				if (account.getTotalMoney().compareTo(commonResponse.getBalance())!=0) {
					System.out.println("修改用户id:"+bpCustMember.getId()+"姓名："+bpCustMember.getTruename()+"修改余额"+account.getTotalMoney()+"为--》"+commonResponse.getBalance());
					account.setTotalMoney(commonResponse.getBalance());
					obSystemAccountService.merge(account);
					num++;
				}
			}
		}
		System.out.println("修改了"+num+"位用户的余额");
		return SUCCESS;
	}

	public void testLoans(){
		BpCustMember mem = bpCustMemberService.getByLoginName("houyetz3");  //6707
		String requestNo1 = ContextUtil.createRuestNumber();
		QueueManger.QueueOffer(requestNo1);
		List<BpCustLoginlog> logs = bpCustLoginlogService.listByMemberId(mem.getId());
		if(logs!=null&&logs.size()>0){
			requestNo1 = logs.get(0).getLoginIp();
		}
//		if (requestNo1 != null) {
////			QueueManger.QueueClear();
//
//		}
		String requestNo = (String) QueueManger.QueuePeek();
		synchronized (requestNo) {
			int flag = getLogInfo(mem, requestNo);
			if(flag==3){
//			ObSystemAccount acc = obSystemAccountService.getByInvrstPersonId(mem.getId());
				BigDecimal totleMoney = BigDecimal.ZERO;
				for(int i=0;i<100;i++){
					BigDecimal money = new BigDecimal(1);
					BpCustLoginlog log = new BpCustLoginlog();
					log.setLoginIp(requestNo);
					log.setMemberId(mem.getId());
					log.setLoginTime(new Date());
					log.setType(1);
					bpCustLoginlogService.save(log);
					totleMoney = totleMoney.add(money);
//					acc.setTotalMoney(acc.getTotalMoney().add(money));
//					obSystemAccountService.merge(acc);
				}
				logger.info("totleMoney==============="+totleMoney);
			}else{
				logger.info("还款失败用重复还款数据");
			}
			QueueManger.QueuePoll();
			logger.info("测试重复还款问题提交成功");
		}
	}

	public int getLogInfo(BpCustMember mem,String requestNo){
		ThirdPayLog log = thirdPayLogService.getByOrderNo(requestNo);
		int flag = 1;
		if(log==null){
			log = new ThirdPayLog(mem.getId(), "", "fuiou", "repayment", "还款测试", new Date(), 1, 1, requestNo,null);
			thirdPayLogService.save(log);
			flag = 3;
		}else if(log.getStatus()==3){
			flag = 3;
		}else{
			flag = 1;
		}
		return flag;
	}
}
