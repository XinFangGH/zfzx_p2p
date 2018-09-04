package com.hurong.credit.action.webPhone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.integral.IntegralManage;
import com.hurong.core.integral.IntegralManageImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.RandomValidateCode;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.article.Operate;
import com.hurong.credit.model.article.P2pBannerlink;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.mobile.AppInfo;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.p2p.BpFinanceApply;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.model.system.product.BpProductParameter;
import com.hurong.credit.model.user.BpCustLoginlog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.android.AndroidAppService;
import com.hurong.credit.service.article.ArticleCategoryService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.article.P2pBannerlinkService;
import com.hurong.credit.service.creditFlow.assuretenet.OurProcreditAssuretenetService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.creditFlow.log.UserloginlogsService;
import com.hurong.credit.service.creditFlow.materials.OurProcreditMaterialsEnterpriseService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.message.OaNewsMessagerinfoService;
import com.hurong.credit.service.p2p.BpFinanceApplyService;
import com.hurong.credit.service.p2p.PlatDataPublishService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.service.system.IndexShowService;
import com.hurong.credit.service.system.product.BpProductParameterService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.user.BpCustLoginlogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.GetMACUtil;
import com.hurong.credit.util.HttpRequestDeviceUtils;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MyUserSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebPhoneAction extends BaseAction {
    //log4j日志文件记录
    protected Log logger = LogFactory.getLog(WebPhoneAction.class);
    private static final long serialVersionUID = -25541236985328967L;

    @SuppressWarnings("unused")
    @Resource
    private IPayService iPayService;
    @Resource
    private PlBidInfoService plBidInfoService;
    @SuppressWarnings("unused")
    @Resource
    private BpFundProjectService bpFundProjectService;
    @Resource
    private BpCustMemberService bpCustMemberService;
    @Resource
    private BpCustLoginlogService bpCustLoginlogService;
    @Resource
    private ObSystemAccountService obSystemAccountService;
    @SuppressWarnings("unused")
    @Resource
    private IndexShowService indexShowService;
    @Resource
    private BpFinanceApplyService bpFinanceApplyService;
    @Resource
    private OaNewsMessageService oaNewsMessageService;
    @Resource
    private PlBidPlanService plBidPlanService;
    @Resource
    private PlManageMoneyPlanService plManageMoneyPlanService;
    @Resource
    private HtmlService htmlService;
    @Resource
    private PlManageMoneyTypeService plManageMoneyTypeService;
    @Resource
    private BpProductParameterService bpProductParameterService;
    @Resource
    private OurProcreditAssuretenetService ourProcreditAssuretenetService;
    @Resource
    private OurProcreditMaterialsEnterpriseService ourProcreditMaterialsEnterpriseService;
    @Resource
    private UserloginlogsService userloginlogsService;//日志接口
    @Resource
    private ArticleService articleService;
    @Resource
    private BpFinanceApplyUserService financeApplyUserService;
    @Resource
    private ObAccountDealInfoService obAccountDealInfoService;
    @Resource
    private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
    @SuppressWarnings("unused")
    @Resource
    private ArticleCategoryService articleCategoryService;
    @Resource
    private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoSevice;
    @Resource
    private OaNewsMessagerinfoService oaNewsMessagerinfoService;
    @Resource
    private PlatDataPublishService platDataPublishService;
    @Resource
    private P2pBannerlinkService p2pBannerlinkService;
    @Resource
    private PlWebShowMaterialsService plWebShowMaterialsService;

    @Resource
    private BpCustRelationService bpCustRelationService;

    @Resource
    private PlBidAutoService plBidAutoService;
    @Resource
    private FuiouService fuiouService;

    @Resource
    private ArticleCategoryService articlecategoryService;

    @Resource
    private AndroidAppService androidAppService;

    private AppInfo appInfo;

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    private List<BpProductParameter> listProduct;
    private List<BpFinanceApplyUser> listApplyUser;
    private String type;//区分请求路径
    private IndexShow indexShow;//存放页面头部信息
    private Article article;//新闻信息
    private String loginname;
    private String password;
    private String valicode;//验证码
    private String checkName;
    @SuppressWarnings("unused")
    private String userLoginAddr;//登录IP
    private List<Article> listArticle;
    private List<Article> listArticle25;
    private List<Article> listArticle31;
    private List<Article> listArticle35;
    private List<Article> listArticle33;//网站公告
    private List<Article> listArticle30;//网站公告
    private List<Article> listArticle34;//媒体报道

    private OaNewsMessage oaNewsMessage;
    private String title;//标题
    private String content;//内容
    private java.util.Date sendTime;//发送时间
    private Long recipient;//接收人(用户id)
    private String operator;//操作人
    private String addresser;//发件人（全名）
    private String status;//状态：0未读，1已读
    private String ids;//多选记录
    private java.util.Date readTime;//阅读时间
    private BpCustMember bpCustMember;//全局session中用户信息
    private PlBidAuto bidAuto;

    public PlBidAuto getBidAuto() {
        return bidAuto;
    }

    public void setBidAuto(PlBidAuto bidAuto) {
        this.bidAuto = bidAuto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public String getOperator() {
        return operator;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAddresser() {
        return addresser;
    }

    public void setAddresser(String addresser) {
        this.addresser = addresser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getReadTime() {
        return readTime;
    }

    public void setReadTime(java.util.Date readTime) {
        this.readTime = readTime;
    }

    public OaNewsMessage getOaNewsMessage() {
        return oaNewsMessage;
    }

    public void setOaNewsMessage(OaNewsMessage oaNewsMessage) {
        this.oaNewsMessage = oaNewsMessage;
    }

    public List<Article> getListArticle33() {
        return listArticle33;
    }

    public void setListArticle33(List<Article> listArticle33) {
        this.listArticle33 = listArticle33;
    }

    public List<Article> getListArticle30() {
        return listArticle30;
    }

    public void setListArticle30(List<Article> listArticle30) {
        this.listArticle30 = listArticle30;
    }

    public List<Article> getListArticle34() {
        return listArticle34;
    }

    public void setListArticle34(List<Article> listArticle34) {
        this.listArticle34 = listArticle34;
    }

    private List<P2pBannerlink> bannerList;//index页面的Banner图

    public List<Article> getListArticle35() {
        return listArticle35;
    }

    public void setListArticle35(List<Article> listArticle35) {
        this.listArticle35 = listArticle35;
    }

    private List<PlManageMoneyPlan> listMoneyPlan;//理财计划

    private PlManageMoneyPlan bybMoneyPlanone = null;
    private PlManageMoneyPlan bybMoneyPlantwo = null;
    private PlManageMoneyPlan bybMoneyPlantree = null;
    private PlManageMoneyPlan bybMoneyPlanfour = null;

    public PlManageMoneyPlan getBybMoneyPlanfour() {
        return bybMoneyPlanfour;
    }

    public void setBybMoneyPlanfour(PlManageMoneyPlan bybMoneyPlanfour) {
        this.bybMoneyPlanfour = bybMoneyPlanfour;
    }

    private List<Investproject> investprojectList;//单笔投资金额排在前五位的投资人


    private List<Investproject> investTwoList;//他在投标最新投标的前两位投资人

    private List<InvestprojectPlan> investPlanList;//他在借款已经还款的最新的两条记录

    //得到config.properties读取的所有资源
    @SuppressWarnings("rawtypes")
    private static Map configMap = AppUtil.getConfigMap();

    private String successHtml;//返回登录信息

    private List<BpFinanceApply> bpFinanceApplyList;

    public String getSuccessHtml() {
        return successHtml;
    }

    public void setSuccessHtml(String successHtml) {
        this.successHtml = successHtml;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InvestprojectPlan> getInvestPlanList() {
        return investPlanList;
    }

    public void setInvestPlanList(List<InvestprojectPlan> investPlanList) {
        this.investPlanList = investPlanList;
    }

    public PlManageMoneyPlan getBybMoneyPlanone() {
        return bybMoneyPlanone;
    }

    public void setBybMoneyPlanone(PlManageMoneyPlan bybMoneyPlanone) {
        this.bybMoneyPlanone = bybMoneyPlanone;
    }

    public PlManageMoneyPlan getBybMoneyPlantwo() {
        return bybMoneyPlantwo;
    }

    public void setBybMoneyPlantwo(PlManageMoneyPlan bybMoneyPlantwo) {
        this.bybMoneyPlantwo = bybMoneyPlantwo;
    }

    public PlManageMoneyPlan getBybMoneyPlantree() {
        return bybMoneyPlantree;
    }

    public void setBybMoneyPlantree(PlManageMoneyPlan bybMoneyPlantree) {
        this.bybMoneyPlantree = bybMoneyPlantree;
    }

    public List<PlManageMoneyPlan> getListMoneyPlan() {
        return listMoneyPlan;
    }

    public void setListMoneyPlan(List<PlManageMoneyPlan> listMoneyPlan) {
        this.listMoneyPlan = listMoneyPlan;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(List<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public IndexShow getIndexShow() {
        return indexShow;
    }

    public void setIndexShow(IndexShow indexShow) {
        this.indexShow = indexShow;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValicode() {
        return valicode;
    }

    public void setValicode(String valicode) {
        this.valicode = valicode;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public BpCustMember getBpCustMember() {
        return bpCustMember;
    }

    public void setBpCustMember(BpCustMember bpCustMember) {
        this.bpCustMember = bpCustMember;
    }

    public List<BpProductParameter> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<BpProductParameter> listProduct) {
        this.listProduct = listProduct;
    }


    //首页信息
    public String mobileIndex() {
        String isApp = this.getRequest().getParameter("isApp");
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        //获取首页加载的banner图列表
        List<P2pBannerlink> bannerList = p2pBannerlinkService.getBannerList(null);
//        P2pBannerlink p2pBannerlink1 = bannerList.get(0);
//        p2pBannerlinkService.evict(p2pBannerlink1);
//        p2pBannerlink1.setUrl(getBasePath() + p2pBannerlink1.getUrl());
        for (P2pBannerlink p2pBannerlink : bannerList) {
            p2pBannerlinkService.evict(p2pBannerlink);
            p2pBannerlink.setUrl(getBasePath() + p2pBannerlink.getUrl());
            System.out.println(p2pBannerlink.getUrl());
        }
        //获取首页网站公告、媒体报道
        List<Article> articleList = getArticleList1();

        List<Article> newsReport = getArticleList2();
        //查询体验标列表  新手标
        List<PlBidPlan> newcomerList = experiencePlan1();
        //获取标的列表
        List<PlBidPlan> indexPlanList = indexBidInfo1();
        //获取首页加载的天数及注册用户
        List<BigDecimal> showList = platShow1();
        if(bpCustMember != null){
            Integer personUnreadMsg = articleService.getPersonMessage(bpCustMember.getId(), "0");
            this.getRequest().setAttribute("personUnreadMsg", personUnreadMsg);
            this.getRequest().setAttribute("bannerList", bannerList);
            this.getRequest().setAttribute("articleList", articleList);
            this.getRequest().setAttribute("newsReport", newsReport);
            this.getRequest().setAttribute("newcomerList", newcomerList);
            this.getRequest().setAttribute("showList", showList);
            this.getRequest().setAttribute("indexPlanList", indexPlanList);
            if ("1".equals(isApp)) {
                model.addDataContent("bannerList", bannerList);
                model.addDataContent("articleList", articleList);
                model.addDataContent("newsReport", newsReport);
                model.addDataContent("newcomerList", newcomerList);
                model.addDataContent("indexPlanList", indexPlanList);
                model.addDataContent("personUnreadMsg", personUnreadMsg);
                model.addDataContent("showList", showList);
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            return "homePage";
        }
        if (null != isApp && !"".equals(isApp) && "1".equals(isApp)) {
            model.addDataContent("bannerList", bannerList);
            model.addDataContent("articleList", articleList);
            model.addDataContent("newsReport", newsReport);
            model.addDataContent("newcomerList", newcomerList);
            model.addDataContent("indexPlanList", indexPlanList);
            model.addDataContent("showList", showList);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        this.getRequest().setAttribute("bannerList", bannerList);
        this.getRequest().setAttribute("articleList", articleList);
        this.getRequest().setAttribute("newsReport", newsReport);
        this.getRequest().setAttribute("newcomerList", newcomerList);
        this.getRequest().setAttribute("showList", showList);
        this.getRequest().setAttribute("indexPlanList", indexPlanList);
        return "homePage";
    }

    /**
     * 获取网站公告、媒体报道
     */
    public List<Article> getArticleList1() {
        //媒体报道
        List<Article> newList = articleService.getArticleLimit(34);
        for (Article article1 : newList) {
            articleService.evict(article1);
            article1.setContent("");
        }
        return newList;
    }

    public List<Article> getArticleList2() {
        //网站公告
        List<Article> webList = articleService.getArticleLimit(30);
        for (Article article1 : webList) {
            articleService.evict(article1);
            article1.setContent("");
        }
        return webList;
    }

    public List<PlBidPlan> experiencePlan1() {
        List<PlBidPlan> newcomerList = new ArrayList<>();
        newcomerList = plBidPlanService.getNewcomerList();
        putShowPlan1(newcomerList);
        for (PlBidPlan plan : newcomerList) {
            System.out.println(plan.toString() + "新手标");
        }
        return newcomerList;
    }

    public List<BigDecimal> platShow1() {
        //注册用户展示
        BigInteger regCount = (BigInteger) bpCustMemberService.regCount();
        //int regCount1 = regCount.intValue() + 4000;
        BigDecimal b8 = new BigDecimal("0");
        b8 = obAccountDealInfoService.sumMoneyByTypeAndState("8", null, 1);
        //BigDecimal regAdd = b8.add(new BigDecimal("50000000"));
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal(Integer.toString(regCount.intValue())));
        list.add(b8);
        return list;
    }

    public void putShowPlan1(List<PlBidPlan> list) {
        if (list != null && list.size() > 0) {
            for (PlBidPlan plan : list) {
                plBidPlanService.setProperty1(plan, this.getRequest());
                System.out.println(plan.toString());
                plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (plan.getPublishSingeTime() != null && !"".equals(plan.getPublishSingeTime())) {
                    plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
                } else {
                    plan.setPreSaleTimeStr("1994-10-16 00:00:00");
                }
                plBidPlanService.returnPlBidPlan1(plan);//获取标的实体类
            }
        }
    }

    public List<PlBidPlan> indexBidInfo1() {
        List<PlBidPlan> indexPlanList = new ArrayList<>();
        indexPlanList = plBidPlanService.getIndexPlanList();
        System.out.println(indexPlanList.size() + "标的个数查询");
        putShowPlan1(indexPlanList);
        return indexPlanList;
    }


    //分页列表展示
    public String planList() {
        //System.out.println("进入分页方法");
        //String page = this.getRequest().getParameter("page");
        String isApp = this.getRequest().getParameter("isApp");
        String page = this.getRequest().getParameter("page");
        if (page != null) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
        }
        //System.out.println(start);
        //System.out.println(limit);
        List<PlBidPlan> indexPlanList = indexBidInfo2(start, limit);
        Integer count = plBidPlanService.getCount();
        MobileDataResultModel model = new MobileDataResultModel();
        if ("1".equals(isApp)) {
            model.addDataContent("indexPlanList", indexPlanList);
            model.addDataContent("totalCounts", count);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        this.getRequest().setAttribute("indexPlanList", indexPlanList);
        this.getRequest().setAttribute("count", count);
        return "Lend";
    }

    public List<PlBidPlan> indexBidInfo2(Integer page, Integer limit) {
        List<PlBidPlan> indexPlanList = new ArrayList<>();
        indexPlanList = plBidPlanService.getIndexPlanList1(page, limit);
        System.out.println(indexPlanList.size() + "标的个数查询222");
        putShowPlan1(indexPlanList);
        return indexPlanList;
    }

    //查询改手机号是否已注册  isRegister:1—注册；0—未注册
    public String checkPhone() {
        String isApp = this.getRequest().getParameter("isApp");
        MobileDataResultModel model = new MobileDataResultModel();
        String telPhone = this.getRequest().getParameter("telPhone");
        Integer count = bpCustMemberService.checkPhoneNum(telPhone);
        this.getRequest().setAttribute("telPhone", telPhone);
        if ("1".equals(isApp)) {
            if (count != null && count == 0) {
                model.setMsg("该用户尚未进行注册，将跳转到注册页面");
                model.addDataContent("isRegister", count);
            }
            model.addDataContent("isRegister", count);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        if (count != null && count == 0 && telPhone != null) {
            return "reg";
        }
        if(telPhone == null){
            return "reg_log";
        }
        return "login";
    }

    //登录接口
    public String mobileEntry() {
        logger.info("用户登录");
        //首先验证登陆失败次数
        StringBuffer sb = new StringBuffer();
        Map<String, Object> commonData = new HashMap<String, Object>();
        htmlService.getCommonData(commonData);
        MobileDataResultModel model = new MobileDataResultModel();
        boolean bool = relieveUser(loginname, false);
        if (!bool) {
            model.setMsg("登录失败已超过5次，请5分钟后再试");
            model.setCode(MobileErrorCode.FAILED);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        Boolean flag = true;
//		String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
//		val = val.toLowerCase();
//		valicode  = valicode.toLowerCase();
//		flag=val!=null && val.equals(valicode);
        Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
        if (lockNum == null || lockNum <= 2) {
            flag = true;
        }
        if (flag) {//判断验证码
            try {
                loginname = loginname.trim();
                MD5 md5 = new MD5();
                password = md5.md5(password, "utf-8");
                bpCustMember = bpCustMemberService.login(loginname, password);
                successHtml = "";
                if (!"".equals(successHtml) || successHtml != null) {
                    this.getRequest().getSession().removeAttribute("successHtml");
                }
                if (bpCustMember != null) {
                    relieveUser(loginname, true);
                    Integer isFor = bpCustMember.getIsForbidden();
                    if (isFor != null && isFor == 1) {
                        model.setCode(MobileErrorCode.FAILED);
                        model.setMsg("该用户已被禁用,无法登录！");
                    } else {
                        try {
                            IntegralManage integralManage = new IntegralManageImpl();
                            integralManage.addScordByFlagKey(bpCustMember.getId(), "Login");
                        } catch (Exception e) {
                            logger.error("---登录积分报错-------------------------------");
                            e.printStackTrace();
                        }
                        model.setCode(MobileErrorCode.SUCCESS);
                        //model.addDataContent("bpCustMember",bpCustMember);
                        setJsonString(model.toJSON());
//						sb.append("{\"success\":true");
//						sb.append(",\"result\":1");
//						sb.append(",\"data\":");
//						sb.append(gson.toJson(bpCustMember));
                        Map<String, String> map = new HashMap<String, String>();
                        Enumeration headerNames = this.getRequest().getHeaderNames();
                        while (headerNames.hasMoreElements()) {
                            String key = (String) headerNames.nextElement();
                            String value = this.getRequest().getHeader(key);
                            map.put(key, value);
                        }
                        BpCustLoginlog bpCustLoginlog = new BpCustLoginlog();
                        bpCustLoginlog.setMemberId(bpCustMember.getId());
                        bpCustLoginlog.setLoginTime(new Date());
                        bpCustLoginlog.setClientMAC(this.getRequest().getParameter("fingerprint"));
                        bpCustLoginlog.setLoginIp(GetMACUtil.getIpAddr(this.getRequest()));
                        bpCustLoginlogService.save(bpCustLoginlog);
                        this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将用户信息保存在session中
                        this.getSession().removeAttribute(RandomValidateCode.RANDOMCODEKEY);//将验证码从session中注销
                        //webPhoneCustMember.userValid();
                        //BigInteger sign = plManageMoneyPlanBuyinfoSevice.countpl(bpCustMember.getId());
                        //this.getSession().setAttribute("A_plan",sign);
                        saveLog(bpCustMember);
//						String retUrl=(String) getSession().getAttribute("retUrl");
//						if(retUrl!=null&&!retUrl.equals("")){
//							sb.append(",\"retUrl\":\""+this.getBasePath()+retUrl+"\"");
//						}else{
//							sb.append(",\"retUrl\":\""+this.getBasePath()+"user/getBpCustMember.do\"");
//						}
                        this.getSession().removeAttribute("retUrl");//清空缓存
                        String isApp = this.getRequest().getParameter("isApp");
                        String isAjax = this.getRequest().getParameter("isAjax");
//						sb.append("}");
                        if ("1".equals(this.checkName)) {
                            this.getSession().setAttribute("exitCheck", "1");
                        } else {
                            this.getSession().setAttribute("exitCheck", "0");
                        }
                        if (null != isAjax && !isAjax.equals("") && "1".equals(isAjax)) {
                            return SUCCESS;
                        }
                        return "getBpcustmem";
                    }
                } else {
                    lockUser(sb, loginname);
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.setCode(MobileErrorCode.FAILED);
                model.setMsg("服务器繁忙");
            }
        } else {
            this.getSession().setAttribute(RandomValidateCode.RANDOMCODEKEY, "");
//			sb.append("{\"success\":true,\"lockNum\":"+lockNum+",\"errMsg\":");
//			sb.append("}");
            model.setCode(MobileErrorCode.FAILED);
            model.addDataContent("lockNum", lockNum);
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        //setJsonString(model.toString());
        return SUCCESS;
    }

    /****
     * 解除用户锁定
     * 1、登陆成功
     * 2、超过一定时间，如5分钟
     * @param  loginname  登陆名称
     * @param  flag 登陆成功---清楚缓存
     * *****/
    public boolean relieveUser(String loginname, boolean flag) {
        if (flag) {
            this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
            this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
            this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
        } else {
            Object lockname = this.getSession().getAttribute(MyUserSession.LOCK_NAME);
            if (null != lockname) {
                if (lockname.equals(loginname)) {
                    Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
                    if (lockNum == 5) {
                        Long lastTime = (Long) this.getSession().getAttribute(MyUserSession.LOCK_TIME);
                        Long now = new Date().getTime();
                        Long disparity = now - lastTime;
                        //	Long min = disparity/(5*60*1000);
                        Long min = disparity / (60 * 1000);
                        if (min.intValue() > 5) {//大于五分钟清空缓存 20:54   1449752023793
                            this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
                            this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
                            this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 登录日志
     *
     * @param
     */
    private void saveLog(BpCustMember mem) {
        try {
            String[] str = getConfig();
            Userloginlogs userLog = new Userloginlogs();
            userLog.setUserLoginName(mem.getLoginname());
            userLog.setLoginTime(new Date());
            userLog.setLoginIp(str[0]);
            userLog.setLoginMac(str[1]);
            userLog.setIsSuccess(true);
            userLog.setUserName(mem.getTruename());
            userLog.setType("p2p");
            userLog.setCompanyId(new Long(1));
            userloginlogsService.save(userLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //得到计算机的ip地址和mac地址
    public String[] getConfig() {
        String[] str = new String[2];
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            for (int i = 0; i < mac.length; i++) {
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : "").toString();

            }
            System.out.println("IP：" + sIP);
            System.out.println("MAC：" + sMAC);
            str[0] = sIP;
            str[1] = sMAC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /****
     * 用户登陆锁定
     * ******/
    public String lockUser(StringBuffer sb, String loginname) {
        MobileDataResultModel model = new MobileDataResultModel();
        model.setCode(MobileErrorCode.FAILED);
        Object lockname = this.getSession().getAttribute(MyUserSession.LOCK_NAME);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        if (null == lockname) {//lockname为空，首次绑定
            this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
            this.getSession().setAttribute(MyUserSession.lOCK_NUM, 1);
            this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
            //model.addDataContent("lockNum", 1);
            model.setMsg("用户名或密码错误");
            setJsonString(model.toJSON());
            //sb.append("{\"success\":true,\"lockNum\":1,\"errMsg\":");
            //sb.append(gson.toJson("用户名或密码错误"));
            //sb.append("}");
            return SUCCESS;
        } else {
            if (loginname.equals(lockname)) {//不是首次绑定
                Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
                if (lockNum < 5) {//小于五次
                    this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
                    this.getSession().setAttribute(MyUserSession.lOCK_NUM, (lockNum + 1));
                    this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
                    Integer lockN = lockNum + 1;
                    //sb.append("{\"success\":true,\"lockNum\":"+lockN+",\"errMsg\":");
                    //sb.append(gson.toJson("用户名或密码错误"));
                    //sb.append("}");
                    //model.addDataContent("lockNum", lockN);
                    model.setMsg("用户名或密码错误");
                    setJsonString(model.toJSON());
                    return SUCCESS;
                } else {//第五次绑定
                    this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
                    this.getSession().setAttribute(MyUserSession.lOCK_NUM, 5);
                    this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
                    //sb.append("{\"success\":true,\"lockNum\":5,\"errMsg\":");
                    //sb.append(gson.toJson("登录失败连续超过5次，请稍后再试"));
                    //sb.append("}");
                    //model.addDataContent("lockNum", 5);
                    model.setCode(MobileErrorCode.FAILED);
                    model.setMsg("登录失败连续超过5次，请稍后再试");
                    setJsonString(model.toJSON());
                    return SUCCESS;
                }
            } else {//首次绑定
                this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
                this.getSession().setAttribute(MyUserSession.lOCK_NUM, 1);
                this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
                //sb.append("{\"success\":true,\"lockNum\":1,\"errMsg\":");
                //sb.append(gson.toJson("用户名或密码错误"));
                //sb.append("}");
                //model.addDataContent("lockNum", 1);
                model.setMsg("用户名或密码错误");
                setJsonString(model.toJSON());
                return SUCCESS;
            }
        }
    }

    //获取网站公告列表
    public String getWebArticleList() {
        MobileDataResultModel model = new MobileDataResultModel();
        String page = this.getRequest().getParameter("page");

        if (null == page&&isApp()) {
            return "platform_bulletin_app";
        } else if (isApp()&& null != page) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
            //网站公告
            List<Article> webList = articleService.getArticleLimit1(30, start, limit);
            if(webList != null){
                int size = webList.size();
                model.addDataContent("totalCounts",size);
            }
            model.addDataContent("webList", webList);
            setJsonString(model.toJSON());
            return SUCCESS;
        } else {
            return "platform_bulletin";
        }


    }

    //获取网站公告详情
    public String getWebArticle() {
        MobileDataResultModel model = new MobileDataResultModel();
        String article_Id = this.getRequest().getParameter("articleId");
        Long articleId = Long.valueOf(article_Id);
        List<Article> article = articleService.getArticleById(articleId);
        this.getRequest().setAttribute("article", article.get(0));
        if (isApp()) {
            return "msgInfo";
        }
        return "message_details";
    }

    //获取媒体报道详情
    public String getWebMedia() {
        MobileDataResultModel model = new MobileDataResultModel();
        String mediaId = this.getRequest().getParameter("mediaId");
        Long articleId = Long.valueOf(mediaId);
        List<Article> article = articleService.getArticleById(articleId);
        this.getRequest().setAttribute("article", article.get(0));
        if (isApp()) {
            return "mediaInfo";
        }
        return "media_detail";
    }

    //获取媒体列表
    public String getWebMediaList() {
        MobileDataResultModel model = new MobileDataResultModel();
        String page = this.getRequest().getParameter("page");
        if (null == page && isApp()) {
            return "mediaInfoList";
        } else if (isApp()&& null != page) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
            //媒体报道
            List<Article> mediaList = articleService.getArticleLimit1(34, start, limit);

            model.addDataContent("mediaList", mediaList);
            setJsonString(model.toJSON());
            return SUCCESS;
        } else {
            return "media_list";
        }

    }


    public String agreement() {
        String versionType = this.getRequest().getParameter("versionType");
        if("2".equals(versionType)){
            return "newFinacingLease_agreement";
        }
        return "finacingLease_agreement";
    }

    public String safety_guarantee() {
        if (isApp()) {
            return "securityInfo";
        }
        return "safety_guarantee";
    }

    public String information() {
        if (isApp()) {
            return "infoView";
        }
        return "information";
    }

    public String regLog() {
        return "reg_log";
    }

    public String changeTel() {
        String telNummber = this.getRequest().getParameter("telNummber");
        this.getRequest().setAttribute("telNummber", telNummber);
        return "changeTel";
    }

    public String test() {
        return "888";
    }

    public String risk_agreement() {
        return "risk_agreement";
    }

    //查询个人未读消息
    public String personMsgCount() {
        BpCustMember bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        MobileDataResultModel model = new MobileDataResultModel();
        if (bpCustMember != null) {
            Integer personUnreadMsg = articleService.getPersonMessage(bpCustMember.getId(), "0");
            BigInteger articCount = (BigInteger) articleService.getArticCount();
            model.setCode(MobileErrorCode.SUCCESS);
            model.addDataContent("personUnreadMsg", personUnreadMsg);
            model.addDataContent("articCount",articCount);
            setJsonString(model.toJSON());
            return SUCCESS;
        } else {
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            model.setMsg("请先登录");
            setJsonString(model.toJSON());
            return SUCCESS;
        }
    }

    public String updateApp() {
        String appType = this.getRequest().getParameter("appType");
        MobileDataResultModel model = new MobileDataResultModel();
        AppInfo appInfo = androidAppService.selectInfo();
        //版本号
        model.addDataContent("versionCode", appInfo.getVersionCode());
        //版本名称
        model.addDataContent("versionName", appInfo.getVersionName());
        //新版本描述信息
        model.addDataContent("desc", appInfo.getVersionContent());
        //按是否强制更新
        model.addDataContent("mustInstall", appInfo.getMustInstall());
        //下载地址
        model.addDataContent("downUrl", appInfo.getDownUrl());
        setJsonString(model.toJSON());
        return SUCCESS;
    }


    //帮助中心
    public String helpCenter() {
        if (isApp()) {
            return "helpInfo";
        }
        return "help_center";
    }

    public String bankCard() {
        if (isApp()) {
            return "supportCard";
        }
        return "bankCard1";
    }

    public String service_agreement() {
        if (isApp()) {
            return "agreementInfo";
        }
        return "service_agreement";
    }

    public String htmlMediaList() {
        return "mediaist";
    }

//    private void commoon(BpCustMember mem){
//        bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
//        String[] moneyArr = null;
//        String thirdPayConfig=configMap.get("thirdPayConfig").toString();
//        String thirdPayType=configMap.get("thirdPayType").toString();
//
//        try {
//            if(thirdPayConfig.equals(Constants.FUIOU)&&thirdPayType.equals("0")){
//                bpCustMember=fuiouService.getCurrentMoney(mem);
//                bpCustMember = bpCustMemberService.get(mem.getId());
//                BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(), ObSystemAccount.type0.toString());
//                bpCustMember.setAllInterest(ret[5]);
//                bpCustMember.setPrincipalRepayment(ret[6]);
//                bpCustMember.setTotalInvestMoney(ret[4]);
//            }else{
//                bpCustMember = bpCustMemberService.get(mem.getId());
//                bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //financeInint(mem);
//
//        //bpCustMember = bpCustMemberService.get(mem.getId());
//        QueryFilter filter = new QueryFilter(getRequest());
//        filter.addFilter("Q_payintentPeriod_N_GT", mem.getId().toString());
//
//        //inient(bpCustMember);
//        //getFundIncome(bpCustMember);
//        //manageMoney(bpCustMember);
//        //loanSet(bpCustMember);
//        //fundList = getLoanList(mem,0,4);
//        int percent = 0;
//        if("1".equals(bpCustMember.getIsCheckEmail())&&bpCustMember.getIsCheckEmail()!=null){
//            //判断邮箱是否验证
//            percent += 10;
//        }
//        if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
//            //判断手机是否验证
//            percent += 20;
//        }
//        if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
//            //判断是否实名认证
//            percent += 40;
//        }
//        if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
//            //判断手机是否验证
//            percent += 30;
//        }
//        //保存信誉等级
//        this.getRequest().setAttribute("percent", percent);
//        //保存客户的信誉信息
//        this.getRequest().setAttribute("bpCustMember", bpCustMember);
//    }

    public String message_center() {
        String count = this.getRequest().getParameter("count");
        //String isApp = this.getRequest().getParameter("isApp");
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        MobileDataResultModel model = new MobileDataResultModel();
        BigInteger articCount = (BigInteger) articleService.getArticCount();

        if(bpCustMember!=null){
            ///commoon(bpCustMember);
            BpCustMember temp= bpCustMemberService.get(bpCustMember.getId());
            bpCustMember.setScore(temp.getScore());
            recipient = bpCustMember.getId();


            HttpServletRequest request=this.getRequest();
            request.setAttribute("userId", recipient);
            List<OaNewsMessagerinfo> list= oaNewsMessagerinfoService.getInfo(request);//获取单个用户的所有站内信
            if(list!=null&&list.size()>0){
                OaNewsMessagerinfo info=oaNewsMessagerinfoService.get(list.get(0).getId());

                this.getRequest().setAttribute("OaNewsMessagerinfo",list.get(0));
                if(isApp()){
                    model.addDataContent("OaNewsMessagerinfo",list.get(0));
                    setJsonString(model.toJSON());
                    return SUCCESS;
                }
            }
            //this.getRequest().setAttribute("OaNewsMessager",list.get(0));
        }
        this.getRequest().setAttribute("articCount",articCount);
        return "message_center";
    }
    //法律法规列表
    public String law(){
        MobileDataResultModel model = new MobileDataResultModel();
        String st = this.getRequest().getParameter("start");
        String li = this.getRequest().getParameter("limit");
        if(st==null || li==null){
            start=1;
            limit=8;
        }else{
            start = Integer.valueOf(st);
            limit = Integer.valueOf(li);
        }
        String lid = this.getRequest().getParameter("lid");
        ArticleCategory category = articlecategoryService.get(Long.valueOf(lid));
        if(category!=null){
            this.getRequest().setAttribute("helpTitle", category.getName());
        }
        this.getRequest().setAttribute("articleCategory.id", lid);
        listArticle = articleService.getByIdCat(this.getRequest(), this.start, this.limit);

        BigInteger count = articleService.getCount(this.getRequest());
        model.addDataContent("totalCounts",count);
        model.addDataContent("listArticle",listArticle);
        setJsonString(model.toJSON());
        return SUCCESS;

    }

    //法律法规详情页
    public String law1(){
        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if(appBrower){
            return "lawArticle_1app";
        }else{
            return "lawArticle_1";
        }

    }
    public String law2(){
        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if(appBrower){
            return "lawArticle_2app";
        }else{
            return "lawArticle_2";
        }
    }
    public String law3(){
        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if(appBrower){
            return "lawArticle_3app";
        }else{
            return "lawArticle_3";
        }
    }
    public String law4(){
        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if(appBrower){
            return "lawArticle_4app";
        }else{
            return "lawArticle_4";
        }
    }
    public String law5(){
        boolean appBrower = HttpRequestDeviceUtils.isAppBrower(getRequest());
        if(appBrower){
            return "lawArticle_5app";
        }else{
            return "lawArticle_5";
        }
    }


//    运营报告
public String operation(){
    String end = this.getRequest().getParameter("end");
    Operate operate =	articleService.getOperationMessage(end);
    this.getRequest().setAttribute("operate",operate);
    boolean appBrower = HttpRequestDeviceUtils.isAppBrower(this.getRequest());
    if(appBrower){
        return "appOperation";
    }else{
        return "operation";
    }
}



    public String account_detail() {
        return "account_detail";
    }

    //登录注册
    public  String  help_center_1(){
        if(isApp()){
        return "lists_1";
        }
        return "help_center_lists_1";
    }
    //绑定银行卡
    public  String  help_center_2(){
        if(isApp()){
            return "lists_2";
        }
        return "help_center_lists_2";
    }
    //充值提现
    public  String  help_center_3(){
        if(isApp()){
            return "lists_3";
        }
        return "help_center_lists_3";
    }
    //标的类
    public  String  help_center_4(){
        if(isApp()){
            return "lists_4";
        }
        return "help_center_lists_4";
    }
    //资金存管
    public  String  help_center_5(){
        if(isApp()){
            return "lists_5";
        }
        return "help_center_lists_5";
    }

    public String coupon(){
        bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(bpCustMember!=null){
            return "coupon";
        }else {
            return "reg_log";
        }
    }

    public String invite_reg(){
        return "invite_reg";
    }

    public String invite(){
        return "invite";
    }

    public String android(){
        return "android";
    }

    public String addAndroidInfo(){
        MobileDataResultModel model = new MobileDataResultModel();
        Integer count = androidAppService.select();
        if(appInfo != null){
            androidAppService.add(appInfo);
        }else {
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            model.setMsg("请填写相关信息");
            this.getRequest().setAttribute("mes","请填写相关信息");
            return "error_message";
            //return SUCCESS;
        }
        Integer counts = androidAppService.select();
        if (counts>count){
            model.setMsg("信息保存成功");
            this.getRequest().setAttribute("mes","信息保存成功");
            return "success_message";
            //return SUCCESS;
        }else {
            model.setCode(MobileErrorCode.FAILED);
            model.setMsg("信息保存失败，请联系110");
            this.getRequest().setAttribute("mes","信息保存失败，请联系110");
            return "error_message";
            //return SUCCESS;
        }
    }
}
