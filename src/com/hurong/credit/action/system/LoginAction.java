package com.hurong.credit.action.system;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.integral.IntegralManage;
import com.hurong.core.integral.IntegralManageImpl;
import com.hurong.core.jms.MailMessageConsumer;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.config.RandomValidateCode;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.P2pBannerlink;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.p2p.BpFinanceApply;
import com.hurong.credit.model.p2p.PlatDataPublish;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.model.system.SystemConfig;
import com.hurong.credit.model.system.product.BpProductParameter;
import com.hurong.credit.model.user.BpCustLoginlog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleCategoryService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.article.P2pBannerlinkService;
import com.hurong.credit.service.creditFlow.FileForm.FileFormService;
import com.hurong.credit.service.creditFlow.assuretenet.OurProcreditAssuretenetService;
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
import com.hurong.credit.service.user.BpCustLoginlogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.*;
import com.hurong.credit.util.Random;
import com.kong.util.ValidateUtil;
import jodd.http.HttpRequest;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 
public class LoginAction extends BaseAction {
	//log4j日志文件记录
	protected Log logger=LogFactory.getLog(LoginAction.class);
	private static final long serialVersionUID = -25541236985328967L;

	@SuppressWarnings("unused")
	@Resource
	private IPayService iPayService;
	private BpCustMember bpCustMember;
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

	private PlManageMoneyPlan bybMoneyPlanone=null;
	private PlManageMoneyPlan bybMoneyPlantwo=null;
	private PlManageMoneyPlan bybMoneyPlantree=null;
	private PlManageMoneyPlan bybMoneyPlanfour=null;

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
	/**
	 * 检查用户是否登录
	 * @return
	 */
    @SuppressWarnings("unused")
	public String checkUserIsLogin(){
    	String[] moneyArr=null;
    	StringBuffer sb = new StringBuffer();
    	BpCustMember cust=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
    	if(cust==null){
    		sb.append("{\"success\":false,");
    		sb.append("\"msg\":");
    		sb.append("\"您未登录,请先登录,然后进行投标！\"");
    	}else{
    		//查询余额
    		cust = obSystemAccountService.getAccountSumMoney(cust);
    		/*if(configMap.get("thirdPayConfig").toString().equals("fuiouConfig")){
    			cust=fuiouService.getCurrentMoney(cust);
    		}*/
    		Map<String, String> map = bpCustMemberService.queryThirdPayCustomerInfo(cust);
			String availableInvestMoney = "0.00";
    		if (map.containsKey("availableInvestMoney")) {
				availableInvestMoney = map.get("availableInvestMoney").toString();
			}

			cust.setAvailableInvestMoney(new BigDecimal(availableInvestMoney));
    		sb.append("{\"success\":true,\"userName\":");
    		sb.append("\""+cust.getLoginname()+"\"");
    		sb.append(",\"userId\":");
    		sb.append("\""+cust.getId()+"\"");
    		sb.append(",\"myPmoney\":");
    		sb.append("\""+cust.getAvailableInvestMoney()+"\"");//可用金额
    		sb.append(",\"myTmoney\":");
    		sb.append("\""+cust.getTotalMoney()+"\"");//可用余额(网贷平台账户余额+公共账户余额)
    		sb.append(",\"myDmoney\":");
    		sb.append("\""+cust.getFreezeMoney()+"\"");//冻结余额
    		sb.append(",\"msg\":");
    		sb.append("\"用户存在！\"");
    		//登录 成功以后 统一 显示 不进行修改
    		//String successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+cust.getLoginname()+"</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息("+messageNum+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
    		sb.append(",\"displayhtml\":");
    		sb.append("\""+successHtml+"\"");
    	}


    	sb.append("}");
    	setJsonString(sb.toString());
    	return SUCCESS;
    }

    /**
	 * 检查用户是否安全
	 * @return
	 */
    public String checkUserIsSecurity(){
    	StringBuffer sb = new StringBuffer();
    	BpCustMember cust=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
    	cust=bpCustMemberService.get(cust.getId());
    	 if(bpCustMemberService.checkUser(cust)==null||bpCustMemberService.checkUser(cust)[0].equals(Constants.CODE_FAILED)){
    		sb.append("{\"success\":false,");
    		sb.append("\"msg\":");
    		sb.append("\""+bpCustMemberService.checkUser(cust)[1]+"\"");
    	}else{

    		//查询余额
    		cust = obSystemAccountService.getAccountSumMoney(cust);
            QueryFilter filter =new QueryFilter(this.getRequest());
            filter.addFilter("Q_recipient_L_EQ", cust.getId().toString());
    		List<OaNewsMessage> list=oaNewsMessageService.getAll(filter);
    		//我的消息
    		int messageNum =0;
    		if(list!=null&&list.size()>0){
    			messageNum=list.size();
    		}
    		sb.append("{\"success\":true,\"userName\":");
    		sb.append("\""+cust.getLoginname()+"\"");
    		sb.append(",\"userId\":");
    		sb.append("\""+cust.getId()+"\"");
    		sb.append(",\"myPmoney\":");
    		sb.append("\""+cust.getAvailableInvestMoney()+"\"");//可用金额
    		sb.append(",\"myTmoney\":");
    		sb.append("\""+cust.getTotalMoney()+"\"");//可用余额(网贷平台账户余额+公共账户余额)
    		sb.append(",\"myDmoney\":");
    		sb.append("\""+cust.getFreezeMoney()+"\"");//冻结余额
    		sb.append(",\"msg\":");
    		sb.append("\"用户存在！\"");
    		//登录 成功以后 统一 显示 不进行修改
    		String successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+cust.getLoginname()+"</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息("+messageNum+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
    		sb.append(",\"displayhtml\":");
    		sb.append("\""+successHtml+"\"");


    	}
    	sb.append("}");
    	setJsonString(sb.toString());
    	return SUCCESS;
    }

	@Resource
	private FileFormService fileFormService;

	public String html(){
		Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
		this.getRequest().setAttribute("lockNum", lockNum);
		//保存要跳转的路径
		this.getSession().setAttribute("retUrl", this.getRequest().getParameter("retUrl"));
		AttributePrincipal principal = (AttributePrincipal)this.getRequest().getUserPrincipal();
		if(principal != null && principal.getName() !=null){
			System.out.println(principal.getName() +"=========="+principal.getProxyTicketFor(loginname));
			loginname =principal.getName();
			password =principal.getProxyTicketFor(loginname);
		}
		 bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			QueryFilter filter2 = new QueryFilter();
			filter2.addFilter("Q_remark_S_EQ", bpCustMember.getId());
			List<FileForm> fileList3 = fileFormService.getAll(filter2);
			if (fileList3.size() > 0) {
				String url = fileList3.get(0).getMark();
				if(StringUtils.isNotBlank(url)){
					this.getRequest().setAttribute("imgurl", url);
				}
			}
		}
		 if(bpCustMember==null){
			 System.out.println(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		 }else{
			 bpCustMember= obSystemAccountService.getAccountSumMoney(bpCustMember);
//			 QueryFilter filter =new QueryFilter(this.getRequest());
//	            filter.addFilter("Q_recipient_L_EQ", bpCustMember.getId().toString());
//			 List<OaNewsMessage> list=oaNewsMessageService.getAll(filter);
			 QueryFilter filter =new QueryFilter(this.getRequest());
			 filter.addFilter("Q_readStatus_I_EQ", 0);
			 filter.addFilter("Q_userId_I_EQ",  bpCustMember.getId());
			 List<OaNewsMessagerinfo> list = oaNewsMessagerinfoService.getAll(filter);


			 //我的消息
	    		int messageNum =0;
	    		if(list!=null&&list.size()>0){
							 messageNum = list.size();
					}



	    		//判断是否是担保户
	    		if(bpCustMember!=null){
	    			QueryFilter filter1 = new QueryFilter();
	    			filter1.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
	    			List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter1);
	    			if(relationlist.size()>0 && !"".equals(relationlist.get(0).getOfflineCustType())){
	    				if(relationlist.get(0).getOfflineCustType().equals("b_guarantee")){
	    					this.getRequest().setAttribute("isGarantee", "1");
	    				}
	    			}
	    		}

				//登录 成功以后 统一 显示 不进行修改
	    		successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息("+messageNum+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"html/loginregSingle.do' onClick='exit()'><span>帮助</span></a><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
	    		this.getRequest().getSession().setAttribute("successHtml", successHtml);
	    		this.getRequest().getSession().setAttribute("messageNum", messageNum);
	    		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYHOME).getTemplateFilePath());

		 }

		return "freemarker";
	}
	public String userGuide(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.USERGUIDE).getTemplateFilePath());
		return "freemarker";
	}
	
	public String qwe(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.USERGUIDE).getTemplateFilePath());
		return "freemarker";
	}
	/** 
     * 检测是否是移动设备访问
     * @return true:移动设备接入，false:pc端接入 
     */  
    @SuppressWarnings("unused")
	public static boolean check(String userAgent){ 
    	userAgent = userAgent.toLowerCase();
    	if("".equals(userAgent)||userAgent == null){
    		return false;
    	}
    	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),    
        // 字符串在编译时会被转码一次,所以是 "\\b"    
        // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)    
        String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
                +"|windows (phone|ce)|blackberry"    
                +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
                +"|laystation portable)|nokia|fennec|htc[-_]"    
                +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b"; 
        
        String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" 
        	+"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    	
        
        
      //移动设备正则匹配：手机端、平板  
        Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
        Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);  
        
        if(null == userAgent){    
            userAgent = "";    
        }    
        // 匹配    
        Matcher matcherPhone = phonePat.matcher(userAgent);    
        Matcher matcherTable = tablePat.matcher(userAgent);    
        if(matcherPhone.find() || matcherTable.find()){    
            return true;    
        } else {    
            return false;    
        }    
    } 
	
	/**
	 * 首页
	 * @return
	 */
	public String index() {
	BpCustMember bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	String isMobile = this.getRequest().getParameter("isMobile");
		if(bpCustMember!=null){
			this.getRequest().setAttribute("bpCustMember", bpCustMember);
			QueryFilter filter = new QueryFilter();
			filter.addFilter("Q_p2pCustId_L_EQ", bpCustMember.getId());
			List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter);
			if(relationlist.size()>0 && !"".equals(relationlist.get(0).getOfflineCustType())){
				if(relationlist.get(0).getOfflineCustType().equals("b_guarantee")){
					this.getRequest().setAttribute("isGarantee", "1");
				}
			}
		}
	  if(HttpRequestDeviceUtils.isMobileDevice(getRequest())&&null==isMobile){
		 
		try {
			getResponse().sendRedirect(getRequest().getContextPath()+"/indexp2pmobile.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return "freemarker";
	    
	  }
		this.getSession().setAttribute("dh", "1");
		//客服电话
		this.getSession().setAttribute("servicePhone", "0000000");
		try{
			//获取index页面的banner列表
		    bannerList=p2pBannerlinkService.getBannerList("P2P");
//			HttpServletRequest request=getRequest();
//			request.setAttribute("FindType", "TopFive");
			//获取首页网站公告、媒体报道、新手指引
			getArticleList();
			//查询体验标列表  新手标
			experiencePlan();
			//获取标的列表
			indexBidInfo();
			//理财计划列表
//	        getBYBMoneyPlan();
	        platShow();
	        //平台披露数据读取
			PlatDataPublish platDataPublish=null;
			List<PlatDataPublish> list=platDataPublishService.getAll();
			if(null!=list && list.size()>0){
				platDataPublish =list.get(0);
				this.getSession().setAttribute("platDataPublish", platDataPublish);
			}
			//周排行榜
//			List<PlBidInfo> listweek = plBidInfoService.weeksort();
//			this.getRequest().setAttribute("listweek", listweek);

			//月排行榜
//			List<PlBidInfo> listmonth = plBidInfoService.monthsort();
//			this.getRequest().setAttribute("listmonth", listmonth);

			//总排行榜
//			List<PlBidInfo> listall = plBidInfoService.allsort();
//			this.getRequest().setAttribute("listall", listall);

			//得到微博二维码
//			List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
//			this.getRequest().setAttribute("fileList", fileList);

			//判断当前用户是否为担保户
//			bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(null!=type&&!"".equals(type)&&"all".equals(type)){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.TOFINANCE).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.INDEX).getTemplateFilePath());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "freemarker";
	}


	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	
	/**
	 * 平台数据披露
	 * @throws ParseException 
	 */
	public void platShow() throws ParseException{
		//注册用户
		BigInteger regCount = (BigInteger)bpCustMemberService.regCount();
//		this.getRequest().setAttribute("listcount", regCount.intValue()+4000);
		this.getRequest().setAttribute("listcount", regCount.intValue());

//		//已撮合融资
//		QueryFilter qf8 = new QueryFilter();
//		qf8.getPagingBean().setStart(1);
//		qf8.getPagingBean().setPageSize(100000);
//		qf8.addFilter("Q_transferType_S_EQ", "8");
//		List<ObAccountDealInfo> list8 = obAccountDealInfoService.getAll(qf8);
//		BigDecimal b8 = new BigDecimal("0");
//		for(int i=0;i<list8.size();i++){
//			b8 = b8.add(list8.get(i).getIncomMoney());
//		}
		BigDecimal b8 = new BigDecimal("0");
		b8 = obAccountDealInfoService.sumMoneyByTypeAndState("8",null,1);
//		this.getRequest().setAttribute("b8", b8.add(new BigDecimal("50000000")));
		this.getRequest().setAttribute("b8", b8);

		//为客户赚取收益
//		QueryFilter qf3 = new QueryFilter();
//		qf3.addFilter("Q_transferType_S_EQ", "3");
//		qf3.getPagingBean().setStart(1);
//		qf3.getPagingBean().setPageSize(100000);
//		List<ObAccountDealInfo> list3 = obAccountDealInfoService.getAll(qf3);
//		for(int i=0;i<list3.size();i++){
//			b3 = b3.add(list3.get(i).getIncomMoney());
//		}
		BigDecimal b3 = new BigDecimal("0");
		b3 = obAccountDealInfoService.sumMoneyByTypeAndState("3",null,1);
//		this.getRequest().setAttribute("b3", b3.add(new BigDecimal("2000000")));
		this.getRequest().setAttribute("b3", b3);

		//平台运营天数
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		int day = DateUtil.getDaysBetweenDate(s.parse("2016-07-29"), new Date());
		this.getRequest().setAttribute("day", day);
		//限时加息
		Long time =  s.parse("2018-10-01").getTime()- new Date().getTime();
		this.getRequest().setAttribute("time", time);
	}
	
	
	/**
	 * 平台数据披露
	 * @throws ParseException 
	 */
	public String platShowMobile() throws ParseException{
		// 判断是否是移动端
		String isMobile = this.getRequest().getParameter("isMobile");
		
		// 创建json字符串
		StringBuffer sb = new StringBuffer();
		  

		// 将数据转成JSON格式
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
		//注册用户
//		List<BpCustMember> list = bpCustMemberService.getAll();
		BigInteger regCount = (BigInteger)bpCustMemberService.regCount();
//		this.getRequest().setAttribute("listcount", regCount.intValue()+4000);
		//已撮合融资
//		QueryFilter qf8 = new QueryFilter();
//		qf8.getPagingBean().setStart(1);
//		qf8.getPagingBean().setPageSize(100000);
//		qf8.addFilter("Q_transferType_S_EQ", "8");
//		List<ObAccountDealInfo> list8 = obAccountDealInfoService.getAll(qf8);
		BigDecimal b8 = new BigDecimal("0");
		b8 = obAccountDealInfoService.sumMoneyByTypeAndState("8",null,1);
		/*for(int i=0;i<list8.size();i++){
			b8 = b8.add(list8.get(i).getIncomMoney());
		}*/
//		this.getRequest().setAttribute("b8", b8.add(new BigDecimal("50000000")));
		//为客户赚取收益
//		QueryFilter qf3 = new QueryFilter();
//		qf3.addFilter("Q_transferType_S_EQ", "3");
//		qf3.getPagingBean().setStart(1);
//		qf3.getPagingBean().setPageSize(100000);
//		List<ObAccountDealInfo> list3 = obAccountDealInfoService.getAll(qf3);
		BigDecimal b3 = new BigDecimal("0");
//		for(int i=0;i<list3.size();i++){
//			b3 = b3.add(list3.get(i).getIncomMoney());
//		}
//		this.getRequest().setAttribute("b3", b3.add(new BigDecimal("4500000")));
		//平台运营天数
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//		int day = DateUtil.getDaysBetweenDate(s.parse("2017-08-10"), new Date());
//		this.getRequest().setAttribute("day", day);
		
		// 判断是否是移动端
		if (null != isMobile && "1".equals(isMobile)) {
			// 拼接接口请求成功后状态
			sb.append("{\"success\":true");
			
			// 累计出借
			sb.append(",\"b8\":");
//			sb.append(gson.toJson(b8.add(new BigDecimal("50000000"))));
			sb.append(gson.toJson(b8));

			// 赚取利息
			sb.append(",\"b3\":");
//			sb.append(gson.toJson(b3.add(new BigDecimal("4500000"))));
			sb.append(gson.toJson(b3));

			// 注册用户
			sb.append(",\"count\":");
//			sb.append(gson.toJson(regCount.intValue() + 4000));
			sb.append(gson.toJson(regCount.intValue()));

			sb.append("}");
			setJsonString(sb.toString());
		}
		return SUCCESS;
	}
	/**
	 * 获取网站公告、媒体报道
	 */
	public List<Article> getArticleList1(){
		//媒体报道
		List<Article> newList = articleService.getArticleLimit(34);
		for (Article article1 : newList) {
			articleService.evict(article1);
			article1.setContent("");
		}
		return newList;
	}
	public List<Article> getArticleList2(){
		//网站公告
		List<Article> webList = articleService.getArticleLimit(30);
		for (Article article1 : webList) {
			articleService.evict(article1);
			article1.setContent("");
		}
		return webList;
	}

	public void getArticleList(){
		//listArticle25 = articleDao.getArticleOrderNew();
		//媒体报道
		QueryFilter filter=new QueryFilter(getRequest());
		filter.getPagingBean().setPageSize(7);
		filter.addFilter("Q_articleCategory.id_L_EQ", String.valueOf(34));
		filter.addFilter("Q_isPublication_S_EQ", "1");
		filter.addSorted("createDate",QueryFilter.ORDER_DESC);
		listArticle= articleService.getAll(filter);

		
		//新手指引
//		QueryFilter filter35=new QueryFilter(getRequest());
//		filter35.getPagingBean().setPageSize(7);
//		filter35.addFilter("Q_articleCategory.id_L_EQ", String.valueOf(35));
//		filter35.addSorted("createDate", "desc");
//		listArticle35= articleService.getAll(filter35);
		
		//最新动态
//		QueryFilter filter33=new QueryFilter(getRequest());
//		filter33.getPagingBean().setPageSize(7);
//		filter33.addFilter("Q_articleCategory.id_L_EQ", String.valueOf(33));
//		filter33.addSorted("createDate", "desc");
//		listArticle33= articleService.getAll(filter33);
		
		//网站公告
		QueryFilter filter30=new QueryFilter(getRequest());
		filter30.addFilter("Q_articleCategory.id_L_EQ", String.valueOf(30));
        filter30.addFilter("Q_isPublication_S_EQ", "1");
		filter30.addSorted("createDate", "desc");
		filter30.getPagingBean().setPageSize(7);
		listArticle30= articleService.getAll(filter30);
		
		//网站公告
//		QueryFilter filter34=new QueryFilter(getRequest());
//		filter34.addFilter("Q_articleCategory.id_L_EQ", String.valueOf(34));
//		filter34.addSorted("createDate", "desc");
//		filter34.getPagingBean().setPageSize(7);
//		listArticle34= articleService.getAll(filter34);
	}
	/**
	 * 体验标查询
	 */
	public void experiencePlan(){
		//体验标 start
		/*QueryFilter planfilter = new QueryFilter();
		planfilter.addFilter("Q_keystr_S_EQ", "experience");
		planfilter.addFilterIn("Q_state_N_IN",Arrays.asList(1));
		planfilter.addSorted("state", "ASC");
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		planfilter.addFilter("Q_buyEndTime_D_GT",sd.format(new Date()));
		List<PlManageMoneyPlan> listPlan=plManageMoneyPlanService.getAll(planfilter);
		List<PlManageMoneyPlan> listPlanArray=new ArrayList<PlManageMoneyPlan>();
		if(null !=listPlan && !"".equals(listPlan) && listPlan.size()>0){
			//只查询出一个最新的
			listPlan.get(0).setNowDate(sd.format(new Date()));
			listPlan.get(0).setStartTime(sd.format(listPlan.get(0).getBuyStartTime()));
			listPlanArray.add(plManageMoneyPlanService.setDetail(listPlan.get(0)));
		}
		this.getRequest().setAttribute("listPlan",listPlanArray);*/
		
		QueryFilter planfilter = new QueryFilter();
		planfilter.addFilter("Q_novice_N_EQ", 1);
		planfilter.addFilterIn("Q_state_N_IN",Arrays.asList(1,2,7));
		planfilter.addSorted("bidId", QueryFilter.ORDER_DESC);
		planfilter.getPagingBean().setPageSize(2);
		List<PlBidPlan> newcomerList = plBidPlanService.getAll(planfilter);
		putShowPlan(newcomerList);
		this.getRequest().setAttribute("newcomerList", newcomerList);
		
		
	}
	@SuppressWarnings("unused")
	private void myFinance() {
		try{
		listProduct=new ArrayList<BpProductParameter>();
		List<BpProductParameter> curr;
		QueryFilter filter = new QueryFilter(this.getRequest());
		curr = bpProductParameterService.getAll(filter);
		
		
		for(BpProductParameter product:curr){
			QueryFilter filter1 = new QueryFilter(this.getRequest());
			filter1.addFilter("Q_productId_L_EQ", product.getId().toString());
			product.setAssure(ourProcreditAssuretenetService.getAll(filter1));
			product.setLoanMaterial (ourProcreditMaterialsEnterpriseService.getAll(filter1));
			listProduct.add(product);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String cloudplat() {
		
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CLOUDPLAT).getTemplateFilePath());
		return "freemarker";
	}
	
	@SuppressWarnings("unused")
	public void getMoneyPlanList(){//获取的理财计划（请求时间在理财计划期限内，且是已经发标的）
		//获取到不同类别下的最新理财计划
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_keystr_S_EQ", "mmplan");
		filter.addFilter("Q_name_S_NEQ", "百翼宝");
		filter.addFilter("Q_state_N_NEQ", "1");
		listMoneyPlan = new ArrayList<PlManageMoneyPlan>();
		List<PlManageMoneyType> list = plManageMoneyTypeService.getAll(filter); //获取理财计划类型
		if(list!=null&&list.size()!=0){
			for(PlManageMoneyType moneyType : list){
				List<PlManageMoneyPlan> planList = plManageMoneyPlanService.getMoneyPlanByType(moneyType.getManageMoneyTypeId());
				if(planList!=null&&planList.size()!=0){
					PlManageMoneyPlan moneyPlan = planList.get(0);
					if(moneyPlan!=null&&null!=moneyPlan.getState()){
						moneyPlan.setAfterMoney(moneyPlan.getSumMoney());
						plManageMoneyPlanService.bidDynamic(moneyPlan);
						moneyPlan.setManageMoneyTypeName(moneyType.getName());
						listMoneyPlan.add(moneyPlan);
					}
				}else{
					PlManageMoneyPlan plan = new PlManageMoneyPlan();
					plan.setManageMoneyTypeName(moneyType.getName());
					plan.setAfterMoney(new BigDecimal(0));
					plan.setProgress(new Double(0));
					plan.setYeaRate(new BigDecimal(0));
					plan.setInvestlimit(0);
					plan.setState(-1);
					listMoneyPlan.add(plan);
				}
			}
		}
	}
	/**
	 * 获取收益理财计划前四条数据
	 */
	public void getBYBMoneyPlan(){
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_keystr_S_EQ", "mmplan");
		filter.addFilter("Q_state_N_EQ", 1);
		List<PlManageMoneyPlan> updtepList = plManageMoneyPlanService.getAll(filter);
		for(PlManageMoneyPlan plan:updtepList ){
			if(plan.getBuyEndTime().compareTo(new Date())==-1){
				plan.setState(4);
				plManageMoneyPlanService.save(plan);
				
			}
		}
		List<PlManageMoneyPlan> listall=plManageMoneyPlanService.getCurrentList();//当前最新发布不同类型的理财计划
		List<PlManageMoneyType> plManageMoneyTypeList = plManageMoneyTypeService.getcurrentType();//全部的有效理财计划类型
		if(listall!=null&&listall.size()>0){//给四个理财计划赋值
			if(listall.size()>3){
				bybMoneyPlanone=listall.get(0);
				bybMoneyPlantwo=listall.get(1);
				bybMoneyPlantree=listall.get(2);
				bybMoneyPlanfour=listall.get(3);
			}else if(listall.size()>2){
				bybMoneyPlanone=listall.get(0);
				bybMoneyPlantwo=listall.get(1);
				bybMoneyPlantree=listall.get(2);
			}else if(listall.size()>1){
				bybMoneyPlanone=listall.get(0);
				bybMoneyPlantwo=listall.get(1);
			}else{
				bybMoneyPlanone=listall.get(0);
			}
		}
		int i=0;//取值的范围
		if(bybMoneyPlanone==null){
			if(plManageMoneyTypeList!=null&&plManageMoneyTypeList.size()>i){
				bybMoneyPlanone=new PlManageMoneyPlan();
				bybMoneyPlanone.setManageMoneyTypeName(plManageMoneyTypeList.get(i).getName());
				i++;
			}
		}else{
			if(bybMoneyPlanone.getState().equals(Integer.valueOf("1"))){
				bybMoneyPlanone.setState((bybMoneyPlanone.getBuyStartTime().compareTo(new Date())>0)?-2:1);
			}
		}
		if(bybMoneyPlantwo==null){
			if(plManageMoneyTypeList!=null&&plManageMoneyTypeList.size()>i){
				bybMoneyPlantwo=new PlManageMoneyPlan();
				bybMoneyPlantwo.setManageMoneyTypeName(plManageMoneyTypeList.get(i).getName());
				i++;
			}	
		}else{
			if(bybMoneyPlantwo.getState().equals(Integer.valueOf("1"))){
				bybMoneyPlantwo.setState((bybMoneyPlantwo.getBuyStartTime().compareTo(new Date())>0)?-2:1);
			}
		}
		if(bybMoneyPlantree==null){
			if(plManageMoneyTypeList!=null&&plManageMoneyTypeList.size()>i){
				bybMoneyPlantree=new PlManageMoneyPlan();
				bybMoneyPlantree.setManageMoneyTypeName(plManageMoneyTypeList.get(i).getName());
				i++;
			}
		}else{
			if(bybMoneyPlantree.getState().equals(Integer.valueOf("1"))){
				bybMoneyPlantree.setState((bybMoneyPlantree.getBuyStartTime().compareTo(new Date())>0)?-2:1);
			}
		}
		if(bybMoneyPlanfour==null){
			if(plManageMoneyTypeList!=null&&plManageMoneyTypeList.size()>i){
				bybMoneyPlanfour=new PlManageMoneyPlan();
				bybMoneyPlanfour.setManageMoneyTypeName(plManageMoneyTypeList.get(i).getName());
				i++;
			}
		}else{
			if(bybMoneyPlanfour.getState().equals(Integer.valueOf("1"))){
				bybMoneyPlanfour.setState((bybMoneyPlanfour.getBuyStartTime().compareTo(new Date())>0)?-2:1);
			}
		}
	}
	@SuppressWarnings("unused")
	private void indexLoanInfo(){
		QueryFilter filter=new QueryFilter(getRequest());
		// 增加排序值
		filter.addSorted("loanId", "desc");
		bpFinanceApplyList=bpFinanceApplyService.getAll(filter);
	}
	
	public void allListPager(){
		QueryFilter filter=new QueryFilter(getRequest());
		if (allListPager == null) {
			allListPager = new Pager();
			allListPager.setPageSize(Integer.valueOf((String) configMap.get("pageSize")));
		}
		filter.getPagingBean().setStart(allListPager.getPageNumber()-1);
		filter.getPagingBean().setPageSize(allListPager.getPageSize());
		filter.addFilter("Q_state_N_GT", "0");
		filter.addFilter("Q_publishSingeTime_D_NOTNULL", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
		// 增加排序值
		filter.addSorted("publishSingeTime", "desc");
		List<PlBidPlan> list= plBidPlanService.getAll(filter);
		List<PlBidPlan> currList= new ArrayList<PlBidPlan>();
		if(list!=null){
			for(PlBidPlan plan:list){
				plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
				currList.add(plan);
			}
		}
		allListPager.setTotalCount(filter.getPagingBean().getTotalItems());
		allListPager.setList(currList);
	}
	public void dirListPager(){
		QueryFilter filter=new QueryFilter(getRequest());
		if (dirListPager == null) {
			dirListPager = new Pager();
			dirListPager.setPageSize(Integer.valueOf((String) configMap.get("pageSize")));
		}
		filter.getPagingBean().setStart(dirListPager.getPageNumber()-1);
		filter.getPagingBean().setPageSize(dirListPager.getPageSize());
		filter.addFilter("Q_state_N_GT", "0");
		filter.addFilter("Q_proType_S_LK", "Dir");
		filter.addFilter("Q_publishSingeTime_D_NOTNULL", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
		// 增加排序值
		filter.addSorted("publishSingeTime", "desc");
		List<PlBidPlan> list= plBidPlanService.getAll(filter);
		List<PlBidPlan> currList= new ArrayList<PlBidPlan>();
		if(list!=null){
			for(PlBidPlan plan:list){
				plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
				currList.add(plan);
			}
		}
		this.getRequest().setAttribute("count", currList.size()==0?0:currList.size());//项目条数
		dirListPager.setTotalCount(filter.getPagingBean().getTotalItems());
		dirListPager.setList(currList);
	}
	
	public void orListPager(){
		QueryFilter filter=new QueryFilter(getRequest());
		if (orListPager == null) {
			orListPager = new Pager();
			orListPager.setPageSize(Integer.valueOf((String) configMap.get("pageSize")));
		}
		filter.getPagingBean().setStart(orListPager.getPageNumber()-1);
		filter.getPagingBean().setPageSize(orListPager.getPageSize());
		filter.addFilter("Q_state_N_GT", "0");
		filter.addFilter("Q_proType_S_LK", "Or");
		filter.addFilter("Q_publishSingeTime_D_NOTNULL", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
		// 增加排序值
		filter.addSorted("publishSingeTime", "desc");
		List<PlBidPlan> list= plBidPlanService.getAll(filter);
		List<PlBidPlan> currList= new ArrayList<PlBidPlan>();
		if(list!=null){
			for(PlBidPlan plan:list){
				plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
				currList.add(plan);
			}
		}
		orListPager.setTotalCount(filter.getPagingBean().getTotalItems());
		orListPager.setList(currList);
	}
	/**
	 * 获取首页招标信息
	 */
	public void indexBidInfo(){
		List<PlBidPlan> shortList= new ArrayList<PlBidPlan>();//短期
		List<PlBidPlan> currList= new ArrayList<PlBidPlan>();//中期 
		List<PlBidPlan> longList= new ArrayList<PlBidPlan>();//长期
		
		shortList = plBidPlanService.getBidListByDuration(1);//获取短期标的  
		currList = plBidPlanService.getBidListByDuration(2);//获取中期标的
		longList = plBidPlanService.getBidListByDuration(3);//获取长期标的
		
		putShowPlan(shortList);
		putShowPlan(currList);
		putShowPlan(longList);
		this.getRequest().setAttribute("shortList", shortList);
		this.getRequest().setAttribute("currList", currList);
		this.getRequest().setAttribute("longList", longList);
		
		
		/*	原方法
		 	QueryFilter filter=new QueryFilter(getRequest());
			filter.getPagingBean().setPageSize(500);
			filter.addFilterIn("Q_state_N_IN",Arrays.asList(1,2,5,6,7,10));
			filter.addFilter("Q_publishSingeTime_D_NOTNULL", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
			filter.addFilter("Q_prepareSellTime_D_LE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//添加标的预售时间查询
			filter.addFilter("Q_isVip_SN_EQ", (short)0);
			// 增加排序值
			filter.addSorted("state", "asc");
			filter.addSorted("createtime", "DESC");
			List<PlBidPlan> list= plBidPlanService.getAll(filter);
		 	
		 	if(list!=null){
			for(PlBidPlan plan:list){
				plBidPlanService.setProperty(plan,this.getRequest());
				plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
					plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
				}else{
					plan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}
				plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
					if("天".equals(plan.getRepaymentPeriod())){
						if (Integer.valueOf(plan.getAcctulData())<180) {
							shortList.add(plan);
						}else if(Integer.valueOf(plan.getAcctulData())<360) {
							currList.add(plan);
						}else {
							longList.add(plan);
						}
					}else if ("月".equals(plan.getRepaymentPeriod())) {
						if (Integer.valueOf(plan.getAcctulData())<6) {
							shortList.add(plan);
						}else if(Integer.valueOf(plan.getAcctulData())<12) {
							currList.add(plan);
						}else {
							longList.add(plan);
						}
					}else if("季度".equals(plan.getRepaymentPeriod())) {
						if (Integer.valueOf(plan.getAcctulData())<2) {
							shortList.add(plan);
						}else if(Integer.valueOf(plan.getAcctulData())<4) {
							currList.add(plan);
						}else {
							longList.add(plan);
						}
					}else if("年".equals(plan.getRepaymentPeriod())) {
						longList.add(plan);
					}
				}
			}*/
		
	}
	
	/**
	 * 处理标的要显示的信息
	 * @param list
	 */
	public void putShowPlan(List<PlBidPlan> list) {
		if (list != null && list.size() > 0) {
			for (PlBidPlan plan : list) {
				plBidPlanService.setProperty(plan,this.getRequest());
				plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
					plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
				}else{
					plan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}
				plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
			}
		}
	}
	
	
	/**
	 * 获取首页债权标列表
	 */
	public void indexBidOr(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_state_N_GT", "0");
		filter.addFilter("Q_proType_S_LK", "Or");
		
		// 增加排序值
		filter.addSorted("publishSingeTime", "desc");
		List<PlBidPlan> list= plBidPlanService.getAll(filter);
 	     this.getRequest().setAttribute("PlBidPlanOr", list);
	}
	public void indexBidDir(){
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_state_N_GT", "0");
		if("proj_wenandai".equals(AppUtil.getProjStr())){
			filter.addFilter("Q_proType_S_LK", "P_Dir");
		}else{
			filter.addFilter("Q_proType_S_LK", "Dir");
		}
		
		//filter.addFilter("Q_state_N_LT", "3");
		// 增加排序值
		filter.addSorted("publishSingeTime", "desc");
		List<PlBidPlan> list= plBidPlanService.getAll(filter);
		List<PlBidPlan> PlBidPlanDir= new ArrayList<PlBidPlan>();
		for(PlBidPlan plan:list){
			plBidPlanService.setProperty(plan,this.getRequest());
			
			//增加的
			plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
			
			PlBidPlanDir.add(plan);
			
		}
	this.getRequest().setAttribute("PlBidPlanDir", PlBidPlanDir);
	}
	
	public String getLoanMonth(Date startDate,Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<Integer,Integer> map = DateUtil.getMonthAndDaysBetweenDate(sdf.format(startDate), sdf.format(endDate));
		if(map!=null){
			Integer month = map.get(1);
			return month+"";
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public String yunpingtai(){

		//首先验证登陆失败次数
		StringBuffer sb = new StringBuffer();
		try{
		AttributePrincipal principal = (AttributePrincipal)this.getRequest().getUserPrincipal();   
		if(principal != null ){
			loginname =principal.getName();
			//password =principal.getProxyTicketFor(loginname);
		}
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式1403253231605
		
		boolean bool  = relieveUser(loginname.toLowerCase(),false);
		if(!bool){
			sb.append("{\"success\":true,\"errMsg\":");
			sb.append(gson.toJson("登录失败连续超过5次，请稍后再试"));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		//工业云平台
		
		//String username = principal.getName();
		//String password = principal.getProxyTicketFor(username);
			/*try {
				MD5 md5 = new MD5();
				
				System.out.println(md5.md5(password, "utf-8"));
				successHtml="";
				if(!"".equals(successHtml)||successHtml!=null){
					this.getRequest().getSession().removeAttribute("successHtml");
				}*/
				bpCustMember = bpCustMemberService.isExist(loginname);
				if(bpCustMember!=null){
					relieveUser(loginname.toLowerCase(),true);
					Integer isFor = bpCustMember.getIsForbidden();
					//Integer isFor =0;
					if(isFor!=null&&isFor==1){
						sb.append("{\"success\":true,\"errMsg\":");
						 sb.append(gson.toJson("该用户已被禁用,无法登录！"));
							sb.append("}");
					}else{
						sb.append("{\"success\":true,\"data\":");
						 sb.append(gson.toJson(bpCustMember));
						 sb.append(",\"result\":1");
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
						bpCustLoginlog.setHTTPHeader(gson.toJson(map));			
						bpCustLoginlog.setLoginIp(GetMACUtil.getIpAddr(this.getRequest()));
						bpCustLoginlogService.save(bpCustLoginlog);
						this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将用户信息保存在session中
						this.getSession().removeAttribute(RandomValidateCode.RANDOMCODEKEY);//将验证码从session中注销
						this.getSession().setAttribute("retUrl", retUrl);//将用户信息保存在session中
						
						//QueryFilter filter =new QueryFilter(this.getRequest());
			            //filter.addFilter("Q_recipient_L_EQ", bpCustMember.getId().toString());
						//List<OaNewsMessage> list=oaNewsMessageService.getAll();
			            
			            
			    		//我的消息
			    		//int messageNum =0;
			    		//if(list!=null&&list.size()>0){
			    			///messageNum=list.size();
			    		//}
			    		//<a href='login.do' target='_self'><span class='blue normal' style='font-size:14px;'>&nbsp;&nbsp;同步到工业云</span></a>
						//登录 成功以后 统一 显示 不进行修改
			    		//successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"message/getUserAllOaNewsMessage.do' target='_self'  ><span>消息("+megNum()+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
			    		//this.getRequest().getSession().setAttribute("successHtml", successHtml);
						megNum();
			    		String retUrl=(String) getSession().getAttribute("retUrl");
			    		if(retUrl!=null&&!retUrl.equals("")){
			    			sb.append(",\"retUrl\":\""+this.getBasePath()+retUrl+"\"");
			    		}else{
			    			sb.append(",\"retUrl\":\""+this.getBasePath()+"user/getBpCustMember.do\"");
			    		}
			    		this.getSession().removeAttribute("retUrl");//清空缓存
			    		sb.append("}");
					}
				}else{
					//lockUser(sb,loginname.toLowerCase());
					
					sb.append("{\"success\":true,\"errMsg\":");
					 sb.append(gson.toJson("用户名或密码错误"));
						sb.append("}");
				}
			/*} catch (Exception e) {
				e.printStackTrace();
			}*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		setJsonString(sb.toString());
		return SUCCESS;
		
	
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	public String mto() throws Exception{
		//首先验证登陆失败次数
	//	String reurl = this.getRequest().getParameter("refererurl");
		StringBuffer sb = new StringBuffer();
		ValidateUtil vu = new ValidateUtil();
		vu.addNotNull(loginname,"用户名不能为空")
		  .addNotNull(password, "密码不能为空");
		String result = vu.validate();
		if("1".equals(result)){
			
		}else{
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", result);
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式1403253231605
		
		boolean bool  = relieveUser(loginname.toLowerCase(),false);
		if(!bool){
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", "登录失败连续超过5次，请稍后再试");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		try {
			MD5 md5 = new MD5();
			//bpCustMember = bpCustMemberService.login(loginname.toLowerCase(), md5.md5(password, "utf-8"));
			bpCustMember = bpCustMemberService.login(loginname.toLowerCase(), password);
			successHtml="";
			if(!"".equals(successHtml)||successHtml!=null){
				this.getRequest().getSession().removeAttribute("successHtml");
			}
			if(bpCustMember!=null){
				relieveUser(loginname.toLowerCase(),true);
				Integer isFor = bpCustMember.getIsForbidden();
				if(isFor!=null&&isFor==1){
					
						this.getRequest().setAttribute("loginname", loginname);
						this.getRequest().setAttribute("err", "该用户已被禁用,无法登录！");
						this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
						return "freemarker";
				}else{
					 sb.append("{\"success\":true,\"data\":");
					 sb.append(gson.toJson(bpCustMember));
					 sb.append(",\"result\":1");
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
					bpCustLoginlog.setHTTPHeader(gson.toJson(map));
					bpCustLoginlog.setLoginIp(GetMACUtil.getIpAddr(this.getRequest()));
					bpCustLoginlogService.save(bpCustLoginlog);
					this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将用户信息保存在session中
					BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
					//this.getSession().removeAttribute(RandomValidateCode.RANDOMCODEKEY);//将验证码从session中注销
					//this.getSession().setAttribute("retUrl", retUrl);//将用户信息保存在session中
					//megNum();
					saveLog(bpCustMember);
		    		String retUrl=(String) getSession().getAttribute("retUrl");
		    		if(retUrl!=null&&!retUrl.equals("")){
		    			sb.append(",\"retUrl\":\""+this.getBasePath()+retUrl+"\"");
		    		}else{
		    			sb.append(",\"retUrl\":\""+this.getBasePath()+"user/getBpCustMember.do?mobile=mobile\"");
		    		}
		    		this.getSession().removeAttribute("retUrl");//清空缓存
		    		sb.append("}");
		    		if("1".equals(this.checkName)){
		    			this.getSession().setAttribute("exitCheck","1");
		    		}else{
		    			this.getSession().setAttribute("exitCheck","0");
		    		}
		    		
		    		
		    		
				}
			}else{
				
				lockUser(sb,loginname.toLowerCase());
				this.getRequest().setAttribute("loginname", loginname);
				this.getRequest().setAttribute("err", "登陆失败！");
				this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
				return "freemarker";
				/*
				sb.append("{\"success\":true,\"errMsg\":");
				 sb.append(gson.toJson("用户名或密码错误"));
					sb.append("}");*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", "登陆异常");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		setJsonString(sb.toString());
		
		
		
	/*	Short node = bpCustMember.getBambooJoint();
	if(node!=null){
		switch (node) {
				case 0 : this.getResponse().sendRedirect(this.getBasePath()+"thirdreg.do?mobile=mobile"); break;
				case 1 : this.getResponse().sendRedirect(""); break;
				case 2 : this.getResponse().sendRedirect(""); break;
				case 3 : this.getResponse().sendRedirect(""); break;
				default: break;
		}
	}else{*/
		String url = this.getBasePath()+"user/getBpCustMember.do?mobile=mobile";
		this.getResponse().sendRedirect(url);
		
	//}
	this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileCenter.ftl");
		return"freemarker";
	}
	
	/**
	 * 登录
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("rawtypes")
	public String to() throws UnsupportedEncodingException {
		logger.info("用户登录");

		//首先验证登陆失败次数
		StringBuffer sb = new StringBuffer();
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式1403253231605
		
//		boolean bool  = relieveUser(loginname.toLowerCase(),false);
		boolean bool  = relieveUser(loginname,false);
		if(!bool){
			sb.append("{\"success\":false,\"errMsg\":");
			sb.append(gson.toJson("登录失败连续超过5次，请5分钟后再试"));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		
		
		Boolean flag=false;
		String isMobile=this.getRequest().getParameter("isMobile");
		String isWeixin=this.getRequest().getParameter("isWeixin");
		if(null!=isMobile&&isMobile.endsWith("1")){
			flag=true;
		}else if(null!=isWeixin&&isWeixin.endsWith("1")){
			flag=true;
		}else{
			String val =  (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
			val = val.toLowerCase();
			valicode  = valicode.toLowerCase();
			flag=val!=null && val.equals(valicode);

		}
		//判断是否是第一次登录
		Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
		if(lockNum==null||lockNum<=2){
			flag = true;
		}
		if (flag) {//判断验证码
			try {
				/*password = Base64Encode.decode(password);
				String str[] = password.split("_");//去掉加密的key值
				password = str[0];//获取密码*/
//				bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),md5.md5(password, "utf-8"));
//				bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),password);
				loginname = loginname.trim();
                MD5 md5 = new MD5();
				password=md5.md5(password, "utf-8");
				bpCustMember = bpCustMemberService.login(loginname,password);
				successHtml="";
				if(!"".equals(successHtml)||successHtml!=null){
					this.getRequest().getSession().removeAttribute("successHtml");
				}
				if(bpCustMember!=null){
//					relieveUser(loginname.toLowerCase(),true);
					relieveUser(loginname,true);
					Integer isFor = bpCustMember.getIsForbidden();
					if(isFor!=null&&isFor==1){
						sb.append("{\"success\":false,\"errMsg\":");
						 sb.append(gson.toJson("该用户已被禁用,无法登录！"));
							sb.append("}");
					}else{
						
						try {
							IntegralManage integralManage = new IntegralManageImpl();
							integralManage.addScordByFlagKey(bpCustMember.getId(), "Login");
						} catch (Exception e) {
							logger.error("---登录积分报错-------------------------------");
							e.printStackTrace();
						}
						
						if(null!=isMobile&&isMobile.endsWith("1")){
							BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(),ObSystemAccount.type0.toString());
							BigDecimal money = ret[3];//账户可用金额
							bpCustMember.setAvailableInvestMoney(money);
						}
						
						sb.append("{\"success\":true");
//						 sb.append(gson.toJson(bpCustMember));
						 sb.append(",\"result\":1");
						 sb.append(",\"data\":");
						 sb.append(gson.toJson(bpCustMember));
						 
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
						bpCustLoginlog.setHTTPHeader(gson.toJson(map));
						bpCustLoginlog.setLoginIp(GetMACUtil.getIpAddr(this.getRequest()));
						bpCustLoginlogService.save(bpCustLoginlog);
						this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将用户信息保存在session中
						this.getSession().removeAttribute(RandomValidateCode.RANDOMCODEKEY);//将验证码从session中注销
						BigInteger sign = plManageMoneyPlanBuyinfoSevice.countpl(bpCustMember.getId());
						this.getSession().setAttribute("A_plan",sign);
						//TODO 增加查找标的申请
						BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
						applyUser.setUserID(bpCustMember.getId());
						listApplyUser = financeApplyUserService.getApplyUser(applyUser);
						this.getSession().setAttribute("applyuser",listApplyUser);
						saveLog(bpCustMember);
			    		String retUrl=(String) getSession().getAttribute("retUrl");
			    		if(retUrl!=null&&!retUrl.equals("")){
			    			sb.append(",\"retUrl\":\""+this.getBasePath()+retUrl+"\"");
			    		}else{
			    			sb.append(",\"retUrl\":\""+this.getBasePath()+"user/getBpCustMember.do\"");
			    		}
			    		this.getSession().removeAttribute("retUrl");//清空缓存
			    		sb.append("}");
			    		if("1".equals(this.checkName))
			    		{
			    			this.getSession().setAttribute("exitCheck","1");
			    		}else
			    		{
			    			this.getSession().setAttribute("exitCheck","0");
			    		}
			    		
					}
				}else{
//					lockUser(sb,loginname.toLowerCase());
					lockUser(sb,loginname);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.getSession().setAttribute(RandomValidateCode.RANDOMCODEKEY,"");
			sb.append("{\"success\":true,\"lockNum\":"+lockNum+",\"errMsg\":");
			 sb.append(gson.toJson("验证码输入错误"));
				sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	/**
	 * 登录日志
	 * @param
	 */
	private void saveLog(BpCustMember mem){
		try {
			String[]  str =getConfig();
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
    public  String[] getConfig(){  
    	String[] str = new String[2];
        try{  
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
            str[0]=sIP;
            str[1]=sMAC;
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return str;
    }  
	/****
	 * 解除用户锁定
	 * 1、登陆成功
	 * 2、超过一定时间，如5分钟
	 * @param  loginname  登陆名称
	 * @param  flag 登陆成功---清楚缓存
	 * *****/
	public boolean relieveUser(String loginname,boolean flag){
		if(flag){
			this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
			this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
			this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
		}else{
			Object lockname = this.getSession().getAttribute(MyUserSession.LOCK_NAME);
			if(null!=lockname){
				if(lockname.equals(loginname)){
					Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
					if(lockNum==5){
						Long lastTime = (Long) this.getSession().getAttribute(MyUserSession.LOCK_TIME);
						Long now = new Date().getTime();
						Long  disparity = now-lastTime;
					//	Long min = disparity/(5*60*1000);
						Long min = disparity/(60*1000);
						if(min.intValue()>5){//大于五分钟清空缓存 20:54   1449752023793
							this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
							this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
							this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
						}else{
							return false;
						}
					}
					
					
				}
			}
		}
		return true;
	}
	/****
	 * 用户登陆锁定
	 * ******/
	public boolean lockUser(StringBuffer sb,String loginname){
		Object lockname = this.getSession().getAttribute(MyUserSession.LOCK_NAME);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if(null==lockname){//lockname为空，首次绑定
			this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
			this.getSession().setAttribute(MyUserSession.lOCK_NUM, 1);
			this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
			sb.append("{\"success\":true,\"lockNum\":1,\"errMsg\":");
			sb.append(gson.toJson("用户名或密码错误"));
			sb.append("}");
			
			return false;
		}else{
			if(loginname.equals(lockname)){//不是首次绑定
				Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
				if(lockNum<5){//小于五次
					this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
					this.getSession().setAttribute(MyUserSession.lOCK_NUM, (lockNum+1));
					this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
					Integer lockN = lockNum+1;
					sb.append("{\"success\":true,\"lockNum\":"+lockN+",\"errMsg\":");
					sb.append(gson.toJson("用户名或密码错误"));
					sb.append("}");
					return false;
				}else{//第五次绑定
					this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
					this.getSession().setAttribute(MyUserSession.lOCK_NUM, 5);
					this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
					sb.append("{\"success\":true,\"lockNum\":5,\"errMsg\":");
					sb.append(gson.toJson("登录失败连续超过5次，请稍后再试"));
					sb.append("}");
					return false;
				}
			}else{//首次绑定
				this.getSession().setAttribute(MyUserSession.LOCK_NAME, loginname);
				this.getSession().setAttribute(MyUserSession.lOCK_NUM, 1);
				this.getSession().setAttribute(MyUserSession.LOCK_TIME, new Date().getTime());
				sb.append("{\"success\":true,\"lockNum\":1,\"errMsg\":");
				sb.append(gson.toJson("用户名或密码错误"));
				sb.append("}");
				return false;
			}
		}
	}
	
	
	public String exit(){ 
		BpCustMember cust=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String mobile=null;
		 mobile=this.getRequest().getParameter("mobile");
		String exitName="";
		if(cust!=null)
		{
			exitName=cust.getLoginname();
		}
		this.getSession().removeAttribute(MyUserSession.MEMBEER_SESSION);//将用户信息从session中注销
		if(!"".equals(successHtml)||successHtml!=null){
			this.getRequest().getSession().removeAttribute("successHtml");
		}
		if(mobile==null){
			WebClient.SendByUrl(this.getResponse(), this.getBasePath()+"htmllogin.do", Constants.CHAR_UTF);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		String exitCheck=(String) this.getSession().getAttribute("exitCheck");
		if("1".equals(exitCheck))
		{
			this.getSession().setAttribute("exitName", exitName);
		}else
		{
			this.getSession().setAttribute("exitName", "");
		}
//		//工业云平台
//		if(this.getSystemConfig().getTheme().equals("proj_hengancaifu")){
//		
//			try {
//				this.getResponse().sendRedirect("http://casserver.sdcloud.net:8090/cas/logout?service="+this.getBasePath());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		
//		}
		if(mobile!=null&&"mobile".equals(mobile)){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILELOGIN).getTemplateFilePath());
		}
		return "freemarker";
		
	}
	public String notice() {
			
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
			
			
			listArticle= articleService.getAll();
			
			//bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NOTICELIST).getTemplateFilePath());
			return SUCCESS;
			
	}
	
	public String noticeShow() {
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		

		listArticle= articleService.getAll();
		//bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NOTICECONTENT).getTemplateFilePath());
		return SUCCESS;
		
}
	/**
	 * 邮件发送方法
	 * msgURL 激活 或者 找回密码的 的URL链接
	 * title 如 注册  修改密码
	 * @return boolean true 成功 false 失败
	 */
	public  boolean sendMail(String msgURL, String title){
		boolean ret=false;
		//邮件实体
		MailModel mode=new MailModel();
		//系统配置
		SystemConfig con=getSystemConfig();
		//用户
		BpCustMember user=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//激活URL 地址
		con.setSite(msgURL);//加入发送的url地址
		//邮件模版需要的数据 
		Map<String, Object> mailData=new HashMap<String, Object>();
		mailData.put("user",user);
		mailData.put("systemConfig", con);
		mode.setMailData(mailData);
		//邮件标题
		mode.setSubject(con.getMetaTitle()+"======"+title);
		mode.setTo(user.getEmail());
		//邮件发送类
		MailMessageConsumer mailMessage=new MailMessageConsumer();
		//发送
		try{
		mailMessage.sendMail(mode);
		ret=true;
		}catch(Exception e){
			
		}
		return ret;
	}
	
	/**2014-07-27
	 * 得到个人的站内消息
	 * @return
	 */
	public void megNum(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		QueryFilter filter =new QueryFilter(this.getRequest());
        filter.addFilter("Q_userId_L_EQ", bpCustMember.getId().toString());
        filter.addFilter("Q_readStatus_N_NULL",null);
		List<OaNewsMessagerinfo> list=oaNewsMessagerinfoService.getAll(filter);
		successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'>|</span><a href='javascript:void(0)' target='_self' onClick='showMeg()'><span>消息("+list.size()+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
		this.getRequest().getSession().setAttribute("successHtml", successHtml);
		//return list.size();
	}

	public void setInvestprojectList(List<Investproject> investprojectList) {
		this.investprojectList = investprojectList;
	}

	public List<Investproject> getInvestprojectList() {
		return investprojectList;
	}

	public void setInvestTwoList(List<Investproject> investTwoList) {
		this.investTwoList = investTwoList;
	}

	public List<Investproject> getInvestTwoList() {
		return investTwoList;
	}

	public void setListArticle25(List<Article> listArticle25) {
		this.listArticle25 = listArticle25;
	}

	public List<Article> getListArticle25() {
		return listArticle25;
	}

	public void setListArticle31(List<Article> listArticle31) {
		this.listArticle31 = listArticle31;
	}

	public List<Article> getListArticle31() {
		return listArticle31;
	}

	public void setBpFinanceApplyList(List<BpFinanceApply> bpFinanceApplyList) {
		this.bpFinanceApplyList = bpFinanceApplyList;
	}

	public List<BpFinanceApply> getBpFinanceApplyList() {
		return bpFinanceApplyList;
	}
	
	public String mobileLogin(){
		//String mobile = this.getRequest().getParameter("mobile");
		String ress = getRequest().getHeader("referer");  
			this.getRequest().setAttribute("refererurl", ress);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILELOGIN).getTemplateFilePath());
		
		return "freemarker";
	}
	public String mobileReg(){
		MD5 md5 = new MD5();
		String token = md5.md5(Random.createRandom(false, 10),"UTF-8");
		getSession().setAttribute("applyToken", token);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEREG).getTemplateFilePath());
		return "freemarker";
	}
	
	public String inviter2Excel() throws Exception{

		List<com.hurong.credit.model.user.BpCustMember> memberlist = bpCustMemberService.getReferrer("2014-10-17", "2014-10-18");
		String mypath = "/Users/liyunfei/Desktop/";
		 WritableWorkbook book = Workbook.createWorkbook(new File(mypath+File.separator+""+new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".xls"));
		 WritableSheet sheet = book.createSheet("邀请人-返现", 0);
		for(int i=0;i<memberlist.size();i++){
			//jxl.write.Number number = new jxl.write.Number(0, i, 1);
	         Label label = null;
		         jxl.write.Number number = new jxl.write.Number(0, i, memberlist.get(i).getId());
		         sheet.addCell(number);
		         label = new Label(1,i, memberlist.get(i).getTruename());
		         sheet.addCell(label);
		         label = new Label(2,i, memberlist.get(i).getLoginname());
		         sheet.addCell(label);
		         label = new Label(3,i, memberlist.get(i).getTelphone());
		         sheet.addCell(label);
		         label = new Label(3,i, memberlist.get(i).getId().toString());
		         sheet.addCell(label);
		        /* 
		         label = new Label(5,i, memberlist.get(i).getTruename());
		         sheet.addCell(label);
		         label = new Label(6,i, memberlist.get(i).getTruename());
		         sheet.addCell(label);
		         label = new Label(7,i, memberlist.get(i).getTruename());
		         sheet.addCell(label);
		         label = new Label(8,i, memberlist.get(i).getTruename());
		         sheet.addCell(label);
		         */
		}
		book.write();
        book.close();
		return"success";
	}
	public String bidder2Excel() throws Exception{
		String sql = "SELECT * FROM pl_managemoneyplan_buyinfo info WHERE info.buyDatetime BETWEEN '2015-02-05' AND '2015-02-06';";
		List<PlManageMoneyPlanBuyinfo> infoList = plManageMoneyPlanBuyinfoService.getManagePlanBuyInfo(sql);//某一时间段的投资用户
		List<PlManageMoneyPlanBuyinfo> newlist = new ArrayList<PlManageMoneyPlanBuyinfo>();
		for(int i=0;i<infoList.size();i++){
//			String countsql = "SELECT COUNT(*) FROM pl_managemoneyplan_buyinfo info WHERE " +
//					"info.buyDatetime < '"+new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(infoList.get(i).getBuyDatetime())+"'  " +
//					"AND info.investPersonId='"+infoList.get(i).getInvestPersonId()+"';";
			String countsql = "SELECT * FROM pl_managemoneyplan_buyinfo info WHERE info.buyDatetime =("
					+ "SELECT MIN(buyDatetime) FROM pl_managemoneyplan_buyinfo i WHERE i.investPersonId='"+infoList.get(i).getInvestPersonId()+"');";
			PlManageMoneyPlanBuyinfo b = plManageMoneyPlanBuyinfoService.getCount(countsql);
			
			if(b.getBuyDatetime()==infoList.get(i).getBuyDatetime()){
				System.err.println(infoList.get(i).getInvestPersonName()+"|"+b.getBuyDatetime()+"|"+infoList.get(i).getOrderId());
				newlist.add(infoList.get(i));//去除非首投用户
			}else{
				System.out.println(infoList.get(i).getInvestPersonName()+"|"+b.getBuyDatetime()+"|"+infoList.get(i).getOrderId());
			}
		}
		 WritableWorkbook book = Workbook.createWorkbook(new File("/Users/liyunfei/Desktop/"+File.separator+"bidder"+new java.text.SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".xls"));
		 WritableSheet sheet = book.createSheet("首投-返现", 0);
         Label label = new Label(0,0, "序号");
         sheet.addCell(label);
         label = new Label(1,0, "用户名");
         sheet.addCell(label);
         label = new Label(2,0, "姓名");
         sheet.addCell(label);
         label = new Label(3,0, "投资明细");
         sheet.addCell(label);
         label = new Label(4,0, "返现金额");
         sheet.addCell(label);
         
         
		 HashSet<BpCustMember> set = new HashSet<BpCustMember>();
		 for(PlManageMoneyPlanBuyinfo info:newlist){
			 BpCustMember b = bpCustMemberService.getInviter(info.getInvestPersonId().toString());
			 set.add(b);//去重复
		 }
		 int i=1;
		 for(BpCustMember p:set){//去除重复后的邀请人集合
			 if(p!=null){
			
			 StringBuffer people = new StringBuffer();
			 Integer money = 0;
			 for(PlManageMoneyPlanBuyinfo f:newlist){
				 BpCustMember b = bpCustMemberService.getInviter(f.getInvestPersonId().toString());
				 if(b!=null){
					 if(p.getPlainpassword().equals(b.getPlainpassword())){
						 people.append(f.getInvestPersonName()+"首投金额："+String.valueOf(f.getBuyMoney()));
						 people.append("||");
						 if(f.getBuyMoney().subtract(new BigDecimal(500)).doubleValue()>0) money += 20; else money +=10;
						 
					 }
				 }
			 }
			 jxl.write.Number number = new jxl.write.Number(0, i, i);
	         sheet.addCell(number);
	         Label  label1 = new Label(1,i, p.getLoginname());
	         sheet.addCell(label1);
	         label1 = new Label(2,i, p.getTruename());
	         sheet.addCell(label1);
	         label1 = new Label(3,i, people.toString());
	         sheet.addCell(label1);
	         number = new jxl.write.Number(4, i, money);
	         sheet.addCell(number);
	         i++;
			 }
			
		 }
		book.write();
        book.close();
		return "success";
	}



	public void setBannerList(List<P2pBannerlink> bannerList) {
		this.bannerList = bannerList;
	}



	public List<P2pBannerlink> getBannerList() {
		return bannerList;
	}
	
	
	public String income(){
		String borrowamount=this.getRequest().getParameter("borrowamount");
		String indexapr=this.getRequest().getParameter("indexapr");
		String repaytime=this.getRequest().getParameter("repaytime");
		String borrowpay=this.getRequest().getParameter("borrowpay");
		this.getRequest().setAttribute("borrowamount", borrowamount);
		this.getRequest().setAttribute("indexapr", indexapr);
		this.getRequest().setAttribute("repaytime", repaytime);
		this.getRequest().setAttribute("borrowpay", borrowpay);
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.INCOME).getTemplateFilePath());
		return "freemarker";
	}

	public List<PlBidPlan> indexBidInfo1(){
		List<PlBidPlan> indexPlanList = new ArrayList<>();
		indexPlanList = plBidPlanService.getIndexPlanList();
		System.out.println(indexPlanList.size()+"标的个数查询");
		putShowPlan1(indexPlanList);
		return indexPlanList;
	}

	public List<PlBidPlan> experiencePlan1(){
//		QueryFilter planfilter = new QueryFilter();
//		planfilter.addFilter("Q_novice_N_EQ", 1);
//		planfilter.addFilterIn("Q_state_N_IN", Arrays.asList(1));
//		planfilter.addSorted("bidId", QueryFilter.ORDER_DESC);
//		planfilter.getPagingBean().setPageSize(1);
//		List<PlBidPlan> newcomerList = plBidPlanService.getAll(planfilter);
		//putShowPlan(newcomerList);
		List<PlBidPlan> newcomerList = new ArrayList<>();
		newcomerList = plBidPlanService.getNewcomerList();
		putShowPlan1(newcomerList);
		for (PlBidPlan plan : newcomerList) {
			System.out.println(plan.toString()+"新手标");
		}
		return newcomerList;
	}

	public List<BigDecimal> platShow1(){
		//注册用户展示
		BigInteger regCount = (BigInteger)bpCustMemberService.regCount();
		int regCount1 = regCount.intValue() + 4000;
		BigDecimal b8 = new BigDecimal("0");
		b8 = obAccountDealInfoService.sumMoneyByTypeAndState("8",null,1);
		BigDecimal regAdd = b8.add(new BigDecimal("50000000"));
		List<BigDecimal> list = new ArrayList<>();
		list.add(new BigDecimal(Integer.toString(regCount1)));
		list.add(regAdd);
		return list;
	}

	public void putShowPlan1(List<PlBidPlan> list) {
		if (list != null && list.size() > 0) {
			for (PlBidPlan plan : list) {
				plBidPlanService.setProperty1(plan,this.getRequest());
				System.out.println(plan.toString());
				plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
					plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
				}else{
					plan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}
				//plBidPlanService.setProperty(plan,this.getRequest());
				plBidPlanService.returnPlBidPlan1(plan);//获取标的实体类
			}
		}
	}

	public List<PlBidPlan> indexBidInfo2(Integer page, Integer limit){
		List<PlBidPlan> indexPlanList = new ArrayList<>();
		indexPlanList = plBidPlanService.getIndexPlanList1(page, limit);
		System.out.println(indexPlanList.size()+"标的个数查询222");
		putShowPlan1(indexPlanList);
		return indexPlanList;
	}

	//首页信息
	public String mobileIndex() {
		//获取首页加载的banner图列表
		List<P2pBannerlink> bannerList = p2pBannerlinkService.getBannerList("P2P");
		//获取首页网站公告、媒体报道
		List<Article> articleList = getArticleList1();

		List<Article> newsReport = getArticleList2();
		//查询体验标列表  新手标
		List<PlBidPlan> newcomerList = experiencePlan1();
		//获取标的列表
		List<PlBidPlan> indexPlanList = indexBidInfo1();
		//获取首页加载的天数及注册用户
		List<BigDecimal> showList = platShow1();
		MobileDataResultModel model = new MobileDataResultModel();
		model.addDataContent("bannerList",bannerList);
		model.addDataContent("articleList",articleList);
		model.addDataContent("newsReport",newsReport);
		model.addDataContent("newcomerList",newcomerList);
		model.addDataContent("indexPlanList",indexPlanList);
		System.out.println(indexPlanList.size()+"-----");
		model.addDataContent("showList",showList);
		setJsonString(model.toJSON());
		return SUCCESS;
	}

	//分页列表展示
	public String planList(){
		//System.out.println("进入分页方法");
		//String page = this.getRequest().getParameter("page");
		String page = this.getRequest().getParameter("page");
		if(page != null){
			start = Integer.valueOf(page);
			start = (start-1)*limit;
		}
		//System.out.println(start);
		//System.out.println(limit);
		List<PlBidPlan> indexPlanList = indexBidInfo2(start, limit);
		Integer count = plBidPlanService.getCount();
		MobileDataResultModel model = new MobileDataResultModel();
		model.addDataContent("indexPlanList",indexPlanList);
		model.addDataContent("count",count);
		setJsonString(model.toJSON());
		return SUCCESS;
	}

	//查询改手机号是否已注册
	public String checkPhone(){
		MobileDataResultModel model = new MobileDataResultModel();
		String telPhone = this.getRequest().getParameter("telPhone");
		Integer count = bpCustMemberService.checkPhoneNum(telPhone);
		if(count == 0){
			model.setMsg("该用户尚未进行注册，将跳转到注册页面");
		}
		model.addDataContent("isRegister",count);
		setJsonString(model.toJSON());
		return SUCCESS;
	}

	//登录接口
	public String mobileEntry(){
		logger.info("用户登录");
		//首先验证登陆失败次数
		System.out.println(this.getRequest().getParameter("loginname"));
		System.out.println(this.getRequest().getParameter("password"));
		StringBuffer sb = new StringBuffer();
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		MobileDataResultModel model = new MobileDataResultModel();
		boolean bool = relieveUser(loginname,false);
		if(!bool){
			model.setMsg("登录失败已超过5次，请5分钟后再试");
			setJsonString(model.toJSON());
			return SUCCESS;
		}
		Boolean flag = true;
//		String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
//		val = val.toLowerCase();
//		valicode  = valicode.toLowerCase();
//		flag=val!=null && val.equals(valicode);
		Integer lockNum = (Integer) this.getSession().getAttribute(MyUserSession.lOCK_NUM);
		if(lockNum==null||lockNum<=2){
			flag = true;
		}
		if (flag) {//判断验证码
			try {
				loginname = loginname.trim();
				MD5 md5 = new MD5();
				password=md5.md5(password, "utf-8");
				bpCustMember = bpCustMemberService.login(loginname,password);
				successHtml="";
				if(!"".equals(successHtml)||successHtml!=null){
					this.getRequest().getSession().removeAttribute("successHtml");
				}
				if(bpCustMember!=null){
					relieveUser(loginname,true);
					Integer isFor = bpCustMember.getIsForbidden();
					if(isFor!=null&&isFor==1){
						model.setMsg("该用户已被禁用,无法登录！");
					}else{
						try {
							IntegralManage integralManage = new IntegralManageImpl();
							integralManage.addScordByFlagKey(bpCustMember.getId(), "Login");
						} catch (Exception e) {
							logger.error("---登录积分报错-------------------------------");
							e.printStackTrace();
						}
						model.setCode(200);
						model.addDataContent("bpCustMember",bpCustMember);
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
//						sb.append("}");
						if("1".equals(this.checkName)){
							this.getSession().setAttribute("exitCheck","1");
						}else{
							this.getSession().setAttribute("exitCheck","0");
						}
						return SUCCESS;
					}
				}else{
					lockUser(sb,loginname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.getSession().setAttribute(RandomValidateCode.RANDOMCODEKEY,"");
//			sb.append("{\"success\":true,\"lockNum\":"+lockNum+",\"errMsg\":");
//			sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	}

	public String test() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();

		List<PlBidPlan> indexPlanList = indexBidInfo1();
		System.out.println("indexPlanList:"+indexPlanList.size());
		System.out.println(indexPlanList.get(0));
		sb.append("{\"success\":false,\"data\":");
		sb.append(gson.toJson(indexPlanList));
		sb.append(",\"result\":0");
		sb.append("}");

		setJsonString(sb.toString());
		return  SUCCESS;

	}

}