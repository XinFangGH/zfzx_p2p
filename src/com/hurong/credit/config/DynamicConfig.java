package com.hurong.credit.config;

import com.hurong.core.util.AppUtil;
import com.hurong.core.util.UUIDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;



public class DynamicConfig {
	public static final String REPLACE_UUID = "{uuid}";// 随机UUID字符串替换
	public static final String REPLACE_DATE_YY = "{date_yyyy}";// 当前日期字符串替换(年)
	public static final String REPLACE_DATE_MM = "{date_MM}";// 当前日期字符串替换(月)
	public static final String REPLACE_DATE_DD = "{date_dd}";// 当前日期字符串替换(日)
	public static final String REPLACE_DATE_HH = "{date_HH}";// 当前日期字符串替换(时)
	
	/************招标详情*****************/
	public static final String BDIRBID="bdirbid";//版本一的页面
	public static final String BDIRBIDV2="bdirbidv2";//版本2的页面
	public static final String PDIRBID="pdirbid";
	public static final String BORBID="borbid";
	public static final String PORBID="porbid";
	
	/*************end********************/
	/************理财计划**********/
	public static final String MONEYPLAN = "moneyPlan";
	/*********end********/
	
	
	public static final String BID_LIST="bidList";// 招标列表
	public static final String BID_LIST_VIP="bidListVip";// 招标列表
	public static final String ARTICLE_LIST = "articleList";// 文章列表
	public static final String ARTICLE_CONTENT = "articleContent";// 文章neirong
	public static final String TRAIN_LIST = "trainList";// 练习列表
	public static final String TRAIN_CONTENT = "trainContent";// 练习列表
	public static final String MYHOME = "myHome";// 个人主页
	public static final String MYHOMEFINANCIAL = "myHomeFinancial";// 个人理财顾问主页
	public static final String MYSALEHOME = "mysalehome";//新版UI个人中心我的债权
	public static final String MYHOMEENTERPRISE = "myHomeEnterprise";// 企业个人主页
	public static final String MYHOMEGUARANTEE = "myHomeGuarantee";// 企业担保户主页
	public static final String EDITUSERINFO = "editUserInfo";// 编辑个人信息
	public static final String SHOWEDITUSERINFONOTICE = "showEditUserInfoNotice";//进入修改密码页面
	public static final String ACCOUNTSAFETY = "accountSafety";// 账户安全
	public static final String CHANGELOGINPASSWORD = "changeLoginPassword";//修改密码页面
	public static final String CHANGEENTERPRISENAME = "changeEnterpriseName";//修改企业名称页面
	public static final String PERSONAUTHENTICATE = "personAuthenticate";//实名认证页面
	public static final String PERSONCODESUCCESS = "personCodeSuccess";//实名认证结果页面
	public static final String BINDPHONE = "bindPhone";//绑定手机页面
	public static final String BINDPHONESSUCCE = "bindPhoneSuccess";//绑定手机成功页面
	public static final String CHANGETRADEPASSWORD = "changeTradePassword";//修改支付密码第一步
	public static final String CHANGETRADEPASSWORDNEXT = "changeTradePasswordNext";//修改支付密码第二步
	public static final String REG = "reg";// 注册
	public static final String EMAILAUTHENTICATION = "emailAuthentication";// 邮箱认证
	public static final String EMAILSENDSUCCESS = "emailSendSuccess";// 邮件发送结果页面
	public static final String EMAILSUCCESS = "emailSuccess";// 邮箱认证结果
	public static final String JUMPPASSWORD = "jumpPassword";
	public static final String THIRDPAY = "thirdPay";// 绑定个人客户第三方支付页面
	public static final String THIRDPAY_ENTERPRISE = "thirdPay_enterprise";// 绑定企业客户第三方支付页面
	public static final String THIRDPAYSUCCESS = "thirdPaySuccess";// 绑定第三方支付成功页面
	public static final String LOGIN = "login";// 登录
	
	public static final String MOBILELOGIN = "mobileLogin";// 登录
	public static final String MOBILEREG = "mobileReg";// 登录
	public static final String MOBILECENTER = "mobileCenter";// 手机端的个人中心
	public static final String MOBILEBIDPLAN = "mobileBidPlan";// 手机端的投资页
	public static final String MOBILEBIDPLANDETAIL = "mobileBidPlanDetail";// 手机端的投资详情
	public static final String MOBILEUSERINFO = "mobileUserInfo";// 手机端查看用户信息
	public static final String MOBILEABOUT = "mobileAbout";//手机端的关于页面
	public static final String MOBILEYB = "mobileyb";//手机端的易保
	
	public static final String EXCHANGEINTEGRAL = "exchangeIntegral";//积分兑换
	public static final String CHECKMATERIAL = "checkMaterial";//认证材料
	public static final String MYGUARANTEE = "myGuarantee";//我的担保
	public static final String MYINTEGRAL = "myIntegral";//我的积分
	public static final String MYINVITE = "myInvite";//我的邀请
	public static final String MYINVITEINFO = "myInviteInfo";//我的邀请详细
	public static final String MYCOUPONS = "myCoupons";//我的优惠券
	public static final String MYTHIRDPAY = "myThirdPay";//第三方支付
	
	
	public static final String SOLIDACTIVITY = "solidactivity";//优惠活动
	public static final String ADVANCELOAN = "advanceLoan";//提前还款
	
	public static final String TEL = "tel";// 手机认证(wenandai)
	public static final String REGSUCCESS = "regsuccess";// 手机认证(wenandai)
	public static final String AUTOREG = "autoReg";//使用QQ或新浪微博快捷登录
	
	public static final String MYFINACEPURCHASE = "myFinancePurchase";//我的投资
	public static final String MYFINANCEPURCHASEENTERPRISE = "myFinancePurchaseEnterprise";//企业理财管理
	public static final String MYFINACE = "myFinance";//我的融资
	public static final String MYFUND = "myFund";//我的融资
	public static final String REPAYMENTRECORDS = "repaymentrecords";//还款记录
	public static final String DETAILSFUNDS = "detailsFunds";//资金明细
	public static final String WITHDRAW = "withdraw";//账户提现1
	public static final String WITHDRAWSUCCESS = "withdrawSuccess";//账户提现结果页面
	public static final String WITHDRAWNEXT = "withdrawNext";//账户提现2
	public static final String WITHDRAWTHIRD = "withdrawThird";//账户提现3
	public static final String RECHARGE = "recharge";//账户充值1
	public static final String RECHARGSUCCESS = "rechargeSuccess";//账户充值结果
	public static final String RECHARGENEXT = "rechargeNext";//账户充值2
	public static final String RECHARGETHIRD = "rechargeThird";//账户充值3
	public static final String LOANAUTHORIZE = "loanAuthorize";//授权
	public static final String FINANCING ="financing";//我要融资
	public static final String REGISTER ="register";//爱财富注册
	public static final String MOBILEINDEX ="mobileIndex";
	public static final String MREGSUCCESS ="mRegsuccess";//爱财富注册成功
	public static final String MACTION ="maction";//爱财富
	
	public static final String MEEBERINTOR ="meeberintor";//特权介绍
	public static final String MEMBERPURCHASE ="memberpurchase";//开通续费
	public static final String THIRDINFO = "thirdinfo";//第三方详情页

	public static final String MESSAGE = "message";//通用信息提示
	public static final String MOBILE_MESSAGE = "mobilemessage";//手机通用信息提示
	
	public static final String PRODUCTMATERIAL = "productmaterial";//身份认证
	
	public static final String MYLOAN = "myloan";// 登录
	public static final String VERIIDCARD = "veriidcard";//身份认证
	public static final String VERICREDITRECORD = "vericreditRecord";//信用认证
	public static final String VERIINCOME = "veriincome";//收入认证
	public static final String VERWEBSHOP = "veriwebShop";//网店认证
	public static final String VERIHOUSE = "verihouse";//房产认证
	public static final String VERIVEHICLE = "verivehicle";//购车认证
	public static final String VERIMARRIAGE = "verimarriage";//结婚认证
	public static final String VERIEDUCATION = "verieducation";//学历认证
	public static final String VERICAREER = "vericareer";//工作认证
	public static final String VERIJOBTITLE = "verijobtitle";//职称认证
	public static final String VERIMOBILEPHONE = "verimobilephone";//手机认证
	public static final String VERIMICROBLOG = "verimicroblog";//微博认证
	public static final String VERIRESIDENCE = "veriresidence";//居住认证
	public static final String VERICOMPANYPLACE = "vericompanyplace";//经营场所认证
	public static final String VERICOMPANYREVENUE = "vericompanyrevenue";//经营收入认证
	public static final String VERITEACHER = "veriteacher";//教师资格认证
	public static final String SUCCESSINFO = "successinfo";//我要借款成功提示页面
	
	public static final String FORGOTPASSWORD = "forgotpassword";//忘记密码
	public static final String RESTPASSWROD = "updatePassword";
	public static final String CHECKUSER = "checkUser";
	public static final String COMPANY = "company"; //公司简介
	public static final String COMPLIANCE = "compliance"; //合规进程
	public static final String STRUCTURE = "structure"; //组织架构
	public static final String SEFITY = "sefity"; //风控管理
	public static final String RISK = "risk"; //重大风险信息
	public static final String COMMITMENT = "commitment"; //承诺函
	public static final String OPERATE = "operate"; //运营数据
	public static final String OPERATEDETAILS = "operateDetails"; //运营详情

	public static final String FINANCIAL = "financial"; //财务报告

	public static final String REGULATIONS = "regulations"; //法律法规
	public static final String BULLETIN = "bulletin"; //网站公告

	/*2018/05/10 帮助  常见问题 刘艳南  start*/

	public static final String LOGINREG = "loginreg"; //登录注册
	public static final String BINDCARK = "bindcark"; //绑定银行卡
	public static final String RECHARGES = "recharges"; //充值提现
	public static final String STANDARD = "standard"; //标的类
	public static final String DEPOSIT = "deposit"; //资金存管
	public static final String RECOMMEND = "recommend"; //推荐浏览器
	public static final String DESCRIPTION = "description"; //平台操作说明

	/*2018/05/10 帮助  常见问题 刘艳南  end*/


	public static final String ABILITY = "ability"; //公司资质
	public static final String MEMORABILIA = "memorabilia"; //大事记
	public static final String ENTERPRISE = "enterprise"; //企业资质
	public static final String PLATFORM = "platform"; //平台信息
	public static final String TEAM = "team"; //团队介绍
	public static final String NEWUSERlOAN = "newuserLoan"; //投资流程
	public static final String NEWUSERINVENT = "newuserInvent"; //借款流程
	public static final String SECURE = "secure"; //安全保障
	public static final String FUNDS = "funds"; //风险基金
	public static final String BILLSAFE = "billsafe"; //交易安全
	public static final String CREDITCHECK = "creditcheck"; //信用审核
	public static final String RISKCONTROL = "riskcontrol"; //风险控制
	public static final String VIP = "vip"; //VIP服务
	public static final String SITEMAP = "sitemap"; //网站地图
	
	public static final String FAQ = "faq"; //常见问题
	public static final String PARTNERPIC = "partnerPic"; //合作机构
	public static final String PARTNERTEXT= "partnerText"; //友情链接
	public static final String JOINUS= "joinus"; //加入我们
	public static final String SAFEFINANCING= "safeFinancing"; //安全理财
	public static final String MEDIAREPORTS= "mediaReports"; //媒体报道
	public static final String STRATEGY= "strategy"; //战略合作伙伴
	public static final String ACCOUNT= "account"; //账户问题
	public static final String INVENTPROBLEM= "inventProblem"; //投资问题
	public static final String NORICEASK= "noriceask"; //新手问题
	public static final String SAFETYPROBLEM= "safetyproblem"; //安全问题
	public static final String TRANSACTION= "transaction"; //交易问题
	public static final String SOCIALDUTY= "socialduty"; //社会责任
	public static final String CAREERS= "careers"; //招贤纳士
	public static final String HELPLISTALL= "helpListAll"; //帮助中心的主页面
	public static final String HELPLIST= "helpList"; //帮助中心其他页面
	public static final String HELPCONTENT= "helpContent"; //帮助中心的详情页
	public static final String NEWSLISTALL= "newsListAll"; //新闻中心的主页面
	public static final String NEWSLIST= "newsList"; //新闻中心其他页面
	public static final String ABOUTLIST= "aboutlist"; //关于我们列表页
	public static final String SAFELIST= "safelist"; //安全保障列表页
	public static final String LAWLIST="lawlist";//法律法规
	public static final String LAWLISTDETAIL="lawListDetail";//法律法规详细页面
	
	public static final String NEWSCONTENT= "newsContent"; //新闻中心的详情页
	public static final String NEWSCONTENTHUJIN= "newsContentHuJin"; //新闻中心的详情页
	public static final String ABOUTCONTENT= "aboutContent"; //关于我们的详情页
	public static final String SAFECONTENT= "safeContent"; //安全保障的详情页
	public static final String EXPERIENCEBIDCONTENT= "experienceBidContent"; //体验标详情页
	
	public static final String CONTACT= "contact"; //联系我们
	public static final String FINANCIALSTRATEGY= "financialStrategy"; //理财攻略
	public static final String MUTUALFINANCIAL= "mutualFinancial"; //互金理财
	public static final String THIRDFINANCIAL= "thirdFinancial"; //互金理财三级分类


	public static final String CHARGESSTANDARD= "chargestandard"; //收费标准	
	public static final String REGSHOW= "regShow"; //注册演示	
	public static final String INVENTSHOW= "inventShow"; //投资演示	
	public static final String INVENTSHILL= "inventShill"; //投资技巧
	public static final String BORROWMONEY= "borrowmoney"; //借钱
	public static final String INTERNETSECURITY= "internetsecurity"; //网上安全
	public static final String LOANFILE= "loanfile"; //贷款文件
	public static final String LOANNOVICE= "loannovice"; //贷款新手
	public static final String MONTHLYREPAYMENT= "monthlyrepayment"; //每月还款
	public static final String MANAGEMENTTEAM= "managementteam"; //团队管理
	public static final String EXPERTCONSULTANT= "expertconsultant"; //专家顾问
	public static final String SUPERVISIONREPORT= "supervisionreport"; //监督报告
	public static final String NEWUSERHELPS= "newuserhelps"; 
	public static final String NEWUSERLOANS= "newuserloans"; 
	public static final String CEOSPEECH= "ceospeech"; //CEO致辞
	public static final String LOANPRODUCT= "loanProduct"; //CEO致辞
	public static final String LOANAUTHORIZESUCCESS = "loanAuthorizeSuccess";//授权结果
	public static final String INVESTMENT = "investment";//我要投资
	public static final String INVESTMENTDETAIL= "investmentdetail";//我要投资详情
	public static final String INTERESTCALCULATE = "interestCalculate";//收益计算器
	public static final String FINACECENTER = "financeCenter";//我要融资
	public static final String FINACECOMPANYCENTER = "finacecompanycenter";//稳安贷个人借款
	public static final String APPLYFINANCE = "applyFinance";//申请融资
	public static final String INDEX = "index";//首页
	public static final String INCOME = "income";//首页
	public static final String CLOUDPLAT = "cloudplat";//云平台
	public static final String USERGUIDE="userGuide";//新手指引
	public static final String INTRODUCTION="introduction";
	
	public static final String TOFINANCE="toFinance";//我要出借
	public static final String BIDDEBTLIST="biddebtlist";//债权转让列表
	
	public static final String MONEYMANAGER = "moneyManager";//理财专区
	public static final String LEGALPOLICIES = "legalPolicies";//法律政策
	public static final String ABOUT = "about";//关于我们
	public static final String RECRUITMENT = "recruitment";//招贤纳士
	public static final String LINKOWER = "linkower";//联系我们
	public static final String COOPERATION = "cooperation";//商务合作
	public static final String OPERATIONGUIDE = "operationsGuide";//操作指南
	
	//配置 贷款产品信息
	public static final String P2PLOANPRODUCT = "p2ploanproduct";
		
	/*2014-07-17 稳安贷*/
	public static final String PRODUCT1 = "lownProduct1";//网商贷
	public static final String PRODUCT2 = "lownProduct2";//消费货
	public static final String PRODUCT3 = "lownProduct3";//经营货
	public static final String PRODUCT4 = "lownProduct4";//园丁贷
	public static final String PRODUCT5 = "lownProduct5";//青春贷
	
	public static final String PERSON = "personInfo";//个人信息
	public static final String P2PPERSON = "p2ppersonInfo";//个人借款信息
	public static final String FMILY = "fmilyInfo";//家庭信息
	public static final String UPLOADINFO = "uploadInfo";//上传资料
	public static final String ASSET = "assetInfo";//资产信息
	public static final String LOANMANAGEMENT = "loanmanagement";//借款管理
	public static final String LOANMANAGEMENTENTERPRISE = "loanmanagementEnterprise";//企业借款管理
	public static final String LOANPAYMENTPLAN = "loanpaymentplan";//借款人还款计划
	
	public static final String FINANCIALRETURN = "financialreturn";//理财回款
	public static final String RETURNMONEY = "returnmoney";//还款列表
	public static final String PAYMENTPLAN = "paymentplan";//回款计划
	public static final String AUTOMATICBID = "automaticbid";//自动投标
	
	
	
	public static final String UPMATERIAL = "upMaterial";//上传资料信息
	public static final String PERSONFINANCE = "personFinance";//上传资料信息
	
	public static final String APPCOMPANY = "appCompany";//非注册企业
	public static final String APPPERSON = "appPerson";//非注册个人
	public static final String CREDIT = "creditInfo";//已注册个人
	
	
	public static final String BAIYIBAO = "baiyibao";//百易宝
	public static final String FIXATION = "fixation";//固定理财计划
	
	
	public static final String NEWUSERVIP = "newuservip";//新手vip
	public static final String NEWUSERTOUZI = "newusertouzi";//新手我要投资
	public static final String NEWUSERJIEKUAN = "newuserjiekuan";//新手我要借款
	public static final String NEWUSERANQUAN = "newuseranquan";//新手安全
	
	public static final String  NOTICELIST = "noticeList";//公告动态列表
	public static final String  NOTICECONTENT = "noticeContent";//公告动态内容
	public static final String  MESSAGELIST = "messageList";//站内信列表
	public static final String  MESSAGECONCENT = "messageConcent";//站内信内容
	
	public static final String  BONUSRECORDLIST = "bonusrecordlist";//积分列表
	public static final String  BPACTIVITYLIST = "bpactivitylist";//积分兑换列表
	public static final String  COUPONSALLLIST = "couponsalllist";//代金券列表
	
	public static final String ERROR_PAGE_500 = "errorPage500";// 错误页500
	public static final String ERROR_PAGE_404 = "errorPage404";// 错误页404
	public static final String ERROR_PAGE_403 = "errorPage403";// 错误页403
	
	public static final String BANK_BIND="userGetCash";//银行卡绑定页面
	
	
	public static final String BIND_BANK_LIST = "bindBankList";//银行卡绑定列表
	public static final String UPDATEBANK = "udateBank";//更换银行卡页面
	
	public static final String TOTHIRD = "tothird";//向第三方跳转

	public static final String Obligatoryright_Transfer = "obligatoryrightTransfer";//债权市场列表
	public static final String OrStartTransfer_Form = "orStartTransferForm";
	public static final String AllSaleTemplate = "allSaleTemplate";//债券交易展示数据的模板
	public static final String AllSaleTemplateVIP = "allSaleTemplateVip";//债券交易展示数据的模板
	public static final String CanTransfering_List = "canTransferingList";
	public static final String Transfering_List = "transferingList";
	public static final String Transfered_List = "transferedList";
	public static final String Buyed_List = "buyedList";
	public static final String Closeed_List = "closeedList";
	public static final String All_transfering_list = "alltransferinglist";
	public static final String All_transfering_list_VIP = "alltransferinglistVip";
	
	public static final String MMPLAN_MANAGE_LIST_ENTERPRISE = "mmplanManageListEnterprise";//企业理财计划
	public static final String mmplan_manage_list = "mmplanManageList";//个人理财计划
	public static final String assigninterest_list = "assigninterestlist";
	public static final String orMacthing_Detail_list = "orMacthingDetaillist";
	public static final String early_out_detail = "earlyOutDetail";
	
	public static final String EXPERIENCE_CONTENT = "experienceContent";//体验标
	
	public static final String FIANCPRODUCT="fianceProduct";//互融宝个人中心页面
	public static final String PERSONFIANCEACCOUNT="personFianceAccount";//互融宝个人中心页面
	public static final String PERSONCURRENTFINANCIAL="personCurrentFinancial";//个人活期理财
	public static final String COMPANYCURRENTFINANCIAL="companyCurrentFinancial";//企业活期理财
	public static final String PERSONBUYPRODUCT="personBuyProduct";//互融宝购买页面
	public static final String PERSONTRANSFERPRODUCT="personTransferProduct";//互融宝赎回页面
	
	
	public static final String UPLAN="UPlan";//U计划页面
	public static final String BID_SALEINFO="bid_saleInfo";//U计划页面
	public static final String CLAIMS="claims";//债权清单页面
	public static final String PERSION_LINECHILDREN="persion_lineChildren";//D计划债权详情页_个人债权
	public static final String BUSINESS_LINECHILDREN="business_lineChildren";//D计划债权详情页_企业债权
	
	public static final String OFFERMATERIAL_THIRDPAY="offerMaterial_thirdPay";//第三方支付账户激活提供材料
	
	
	public static final String ACCESS = "access";//提示用户评估
	public static final String TOACCESS = "toaccess";//用户评估开始
	public static final String ACCESSRESULT = "result";//用户评估结果
	
	public static final String PLATFORMPUBLISH = "platformPublish";//平台数据披露
	public static final String COMPENSATOTYMONEY="compensatoryMoney";//担保代偿页面
	public static final String OFFLINEREGIST="offlineRegist";//线下追偿登记页面
	public static final String EARLYOUT="earlyOut";//提前赎回审核界面
	
	public static final String  MMPLAN_FINANCIALMANAGE_LIST= "mmplanFinancialList";//个人理财顾问理财计划
	public static final String  FINANCIALMONEY= "financialMoney";//个人理财顾问派息信息

	public static String getBankBind() {
		return BANK_BIND;
	}

	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径
	private String htmlFilePath;// 生成HTML静态文件存放路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {
		 templateFilePath=templateFilePath.replace("${projstr}", AppUtil.getProjStr());
		this.templateFilePath = templateFilePath;
	}

	// 获取生成HTML静态文件存放路径
	public String getHtmlFilePath() {
		htmlFilePath = htmlFilePath.replace(REPLACE_UUID, UUIDGenerator.getUUID());
		SimpleDateFormat yyDateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat ddDateFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hhDateFormat = new SimpleDateFormat("HH");
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_YY, yyDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_MM, mmDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_DD, ddDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_HH, hhDateFormat.format(new Date()));
		return htmlFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}

}