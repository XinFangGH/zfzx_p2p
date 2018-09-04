package com.hurong.core.web.action;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;


import com.hurong.credit.util.HttpRequestDeviceUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.freemarker.FreemarkerManager;

import org.springframework.mail.MailSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import com.opensymphony.xwork2.ActionSupport;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.engine.MailEngine;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.P2pFriendlink;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;

import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;


import com.hurong.credit.model.system.SystemConfig;
import com.hurong.credit.model.system.WebMsg;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.P2pFriendlinkService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.customer.InvestPersonInfoService;

import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;

import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.SystemConfigUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;



/**
 * Ext Base Action for all the request.
 * 
 * @author csx
 * 
 */
public class BaseAction extends ActionSupport{
	
	@Resource
	private P2pFriendlinkService p2pFriendlinkService;
	
	@Resource
	private FreemarkerManager freemarkerManager;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private BpFundIntentService bpFundIntentService;
	@Resource 
	private PlMmOrderAssignInterestService plMmOrderAssignInterestService;
	@Resource
	private BpFundProjectService bpFundProjectService;
	@Resource
	private InvestPersonInfoService investPersonInfoService;
	@Resource
	private PlManageMoneyTypeService plManageMoneyPlanBuyinfoservice;
	public static final String SUCCESS="success";
	public static final String INPUT="input";
	public static final String STATUS = "status";
	private String defUrl="user/getBpCustMember.do";
	protected WebMsg webMsg; //信息提示
	protected Pager pager;
	protected Long id;
	protected String retUrl;
	
	protected Pager dirListPager;//直投标列表（包括企业和个人）
	protected Pager orListPager;//债券标列表（包括企业和个人）
	protected Pager allListPager;//直投和债券列表（所有的）
	
	private BigDecimal financeTotal = new BigDecimal(0);//融资总金额
	private BigDecimal profitTotal = new BigDecimal(0);//预期收益总额
	private BigDecimal profitRate = new BigDecimal(0);//收益比
	private List<String> listDate;
	
	

	public List<String> getListDate() {
		return listDate;
	}

	public void setListDate(List<String> listDate) {
		this.listDate = listDate;
	}

	

	public  static Map configMap = AppUtil.getConfigMap();
	
	
	public Pager getDirListPager() {
		return dirListPager;
	}

	public void setDirListPager(Pager dirListPager) {
		this.dirListPager = dirListPager;
	}

	public Pager getOrListPager() {
		return orListPager;
	}

	public void setOrListPager(Pager orListPager) {
		this.orListPager = orListPager;
	}

	public Pager getAllListPager() {
		return allListPager;
	}

	public void setAllListPager(Pager allListPager) {
		this.allListPager = allListPager;
	}


	public BigDecimal getFinanceTotal() {
		return financeTotal;
	}

	public void setFinanceTotal(BigDecimal financeTotal) {
		this.financeTotal = financeTotal;
	}

	public BigDecimal getProfitTotal() {
		return profitTotal;
	}

	public void setProfitTotal(BigDecimal profitTotal) {
		this.profitTotal = profitTotal;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	public String getRetUrl() {
		return retUrl;
	}

	public void setRetUrl(String retUrl) {
		this.getRequest().getSession().setAttribute("retUrl", StringUtil.html2Text(retUrl));
		this.retUrl =StringUtil.html2Text(retUrl);
	}

	public WebMsg getWebMsg() {
		return webMsg;
	}
	
	/**
	 * 理财计划、理财产品最终都需要匹配标（直投标、债权标）
	 * 所以
	 * **/
	public void statisticalAll(){
		try{
		BigDecimal payaccrual=null;
		//项目列表
		List<InvestPersonInfo> InvestPersonInfoList=investPersonInfoService.getAll();
		BigDecimal investMoney=new BigDecimal(0);
		BpFundProject bpFundProject=null;
		PlBidPlan plBidPlan=null;
		int num=0;
		Long currId=null;
		for(InvestPersonInfo investPersonInfo:InvestPersonInfoList){
			plBidPlan=plBidPlanService.get(investPersonInfo.getBidPlanId());
			if(plBidPlan.getState()>=5){
			bpFundProject=bpFundProjectService.get(investPersonInfo.getMoneyPlanId());
			if(currId==null||currId!=bpFundProject.getId()){
				num=num+1;
				currId=bpFundProject.getId();
			}
			investMoney=investPersonInfo.getInvestMoney();
			//投资总额  old
			/*financeTotal=financeTotal.add(investMoney);
			if(bpFundProject.getPayaccrualType().equals("dayPay")){
				payaccrual=bpFundProject.getDayAccrualRate().multiply(new BigDecimal(bpFundProject.getPayintentPeriod()));
			}else if(bpFundProject.getPayaccrualType().equals("owerPay")){
				payaccrual=bpFundProject.getDayAccrualRate().multiply(new BigDecimal(bpFundProject.getDayOfEveryPeriod()));
			}else{
				payaccrual=bpFundProject.getDayAccrualRate().multiply(new BigDecimal(30)).multiply(new BigDecimal(bpFundProject.getPayintentPeriod()));
			}
			profitTotal=profitTotal.add(investMoney.multiply(payaccrual).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_EVEN));*/
			profitRate=profitRate.add(bpFundProject.getYearAccrualRate());		
		}
		}
		if(num!=0){
			profitRate=profitRate.divide(new BigDecimal(num),3,BigDecimal.ROUND_HALF_EVEN);
		}else{
			profitRate=new BigDecimal(0);
		}
		//为投资人创造收益 和累计融资额 
		//直投标 和债权标
		List<BpFundIntent> bpfundintentList=bpFundIntentService.getAll();
		for(BpFundIntent fundintent:bpfundintentList){
			if(fundintent.getFundType().equals("loanInterest")){
			profitTotal=profitTotal.add(fundintent.getIncomeMoney());
			}
			if(fundintent.getFundType().equals("principalLending")){
			financeTotal=financeTotal.add(fundintent.getPayMoney());
			}
		}
		//理财计划
		
		List<PlMmOrderAssignInterest> plMmOrderAssignInterestList=plMmOrderAssignInterestService.getAll();
		for(PlMmOrderAssignInterest plMmOrderAssignInterest:plMmOrderAssignInterestList){
			if(plMmOrderAssignInterest.getFundType().equals("loanInterest")){
			profitTotal=profitTotal.add(plMmOrderAssignInterest.getIncomeMoney());
			}
			if(plMmOrderAssignInterest.getFundType().equals("principalRepayment")){
			financeTotal=financeTotal.add(plMmOrderAssignInterest.getIncomeMoney());
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * 设置 页面提示消息
	 * @param webMsg
	 * @param type
	 * @param code
	 * @param desc
	 * @param url
	 * @param remark1
	 * @param remark2
	 * @param remark3
	 * @param remark4
	 * @param remark5
	 * @return
	 */
	public WebMsg webMsgInstance(String type,String code,String desc,String remark1,String remark2,String remark3,String remark4,String remark5) {
		if(webMsg==null){
			webMsg=new WebMsg();
		}
		webMsg.setType(type);
		webMsg.setCode(code);
		String retUrl=(String) getSession().getAttribute("retUrl");
		if(retUrl!=null){
			webMsg.setUrl(this.getBasePath()+retUrl);
		}else{
			webMsg.setUrl(this.getBasePath()+defUrl);
		}
		if(desc!=null&&desc.equals("openThirdPay")){
			//跳转到个人资料
			webMsg.setDesc("请先开通第三方支付");
			webMsg.setUrl(this.getBasePath()+"/thirdreg.do");
		}else if(desc!=null&&desc.equals("opentelePthone")){
			webMsg.setDesc("请先绑定手机号");
			webMsg.setUrl(this.getBasePath()+"/user/getBpCustMember.do?typ=2&action=updateTelphone");
		}else if(desc!=null&&desc.equals("OPENPERSONAUTHENTICATE")){
			webMsg.setDesc("请先实名认证");
			webMsg.setUrl(this.getBasePath()+"/user/getBpCustMember.do?typ=2&action=updateName");
		}else if(desc!=null&&desc.equals("openEmail")){
			webMsg.setDesc("请先填写邮箱");
			webMsg.setUrl(this.getBasePath()+"/emailreg.do?type=2&action=email");
		}else{
			webMsg.setDesc(desc);
		}
		if(remark1.equals("balanceLackof")){
			//跳转到首页
			//webMsg.setDesc("系统账户余额不足,不能进行投标！");
			webMsg.setUrl(this.getBasePath());
		}else if(desc!=null&&desc.equals("MyFinance")){
			
			webMsg.setUrl(this.getBasePath()+"financePurchase/myFinancePurchase.do");
	}
		
		webMsg.setRemark1(remark1);
		webMsg.setRemark2(remark2);
		webMsg.setRemark3(remark3);
		webMsg.setRemark4(remark4);
		webMsg.setRemark5(remark5);
		this.getSession().removeAttribute("retUrl");//清空缓存
		return webMsg;
	}

	/**
	 * 成功跳转的页面(jsp)
	 */
	private String successResultValue="/jsonString.jsp";
	private String projstr=AppUtil.getProjStr();
	protected SystemConfig systemConfig;
	protected List<P2pFriendlink> flinkPic ;
	protected List<P2pFriendlink> flinkTxt ;
	protected List<Article> articleMap;
	protected List<Article> articleNew;//公告
	protected BpCustMember bpCustMember;
	
	
	//获取图片文件 svn:songwj
	public List<P2pFriendlink> getFlinkPic() {
		List<P2pFriendlink>  friendLinkList = SystemConfigUtil.getFriendLinkByWebKey("1");
		
		if(friendLinkList.size()>0){
			for(int i = 0;i<friendLinkList.size();i++){
				String mark  = "";
				mark = "p2p_friendLink."+friendLinkList.get(i).getId();//获取每条友情链接的主键值
				friendLinkList.get(i).setImgURL(p2pFriendlinkService.getImgUrl(mark));
			}
		}
		return friendLinkList;
	}

	public List<P2pFriendlink> getFlinkTxt() {
		return SystemConfigUtil.getFriendLink(2);
	}
	public List<Article> getArticleMap() {
		return SystemConfigUtil.getArticleByCat(26);
	}
	
	public List<Article> getArticleNew() {
		return SystemConfigUtil.getArticleByCat(29);
	}
	
    /**
     * 系统配置信息
     * @param systemConfig
     */
	public SystemConfig getSystemConfig() {
		return SystemConfigUtil.getSystemConfig();
		
	}
	
	
  
	public BpCustMember getBpCustMember() {
		bpCustMember=AppUtil.getBpCustMember(this.getRequest());
		return bpCustMember;
	}

	public String getSuccessResultValue() {
		return successResultValue;
	}

	public void setSuccessResultValue(String successResultValue) {
		this.successResultValue = successResultValue;
	}
	
	

	public String getProjstr() {
		return projstr;
	}

	public void setProjstr(String projstr) {
		this.projstr = AppUtil.getProjStr();
	}

	public static final String JSON_SUCCESS="{success:true}";
	
	/**
	 * 结合Ext的分页功能： dir DESC limit 25 sort id start 50
	 */
	/**
	 * 当前是升序还是降序排数据
	 */
	protected String dir;
	/**
	 * 排序的字段
	 */
	protected String sort;
	/**
	 * 每页的大小
	 */
	protected Integer limit=10;
	/**
	 * 开始取数据的索引号
	 */
	protected Integer start=0;

	protected String jsonString=JSON_SUCCESS;

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getJsonString() {
		return jsonString;
	}
	
	public BaseAction() {
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected transient final Log logger = LogFactory.getLog(getClass());

	protected MailEngine mailEngine;
	
	protected MailSender mailSender;

	
	public final String CANCEL = "cancel";

	public final String VIEW = "view";

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
	protected String  getBasePath() {
		
		String path= getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort() + getRequest().getContextPath() + "/";
		return path;//path.replace("https", "http").replace(":443", "");
	}

	// ---------------------------Methods------------------------------

	protected PagingBean getInitPagingBean() {
		PagingBean pb = new PagingBean(start,limit);
		return pb;
	}

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}
	
	public MailEngine getMailEngine(){
		return mailEngine;
	}

	public String list() {
		return SUCCESS;
	}

	public String edit() {
		return INPUT;
	}

	public String save() {
		flushCache();
		return INPUT;
	}

	public String delete() {
		return SUCCESS;
	}

	public String multiDelete() {
		return SUCCESS;
	}

	public String multiSave() {
		return SUCCESS;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 按url返回其默认对应的jsp
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		HttpServletRequest request=getRequest();   
        String uri=request.getRequestURI();   
        String url=uri.substring(request.getContextPath().length());   
        url=url.replace(".do", ".jsp");   
        url="/pages"+url;
        
        if(logger.isInfoEnabled()){
        	logger.info("forward url:" + url);
        }
        System.out.println("url==="+url);
        setSuccessResultValue(url);   
        
        return SUCCESS;   

	}
	
	/**
	 * gson list的列表
	 * @param listData
	 * @param totalItems 
	 * @param onlyIncludeExpose 仅是格式化包括@Expose标签的字段
	 * @return
	 */
	public String gsonFormat(List listData,int totalItems,boolean onlyIncludeExpose){
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(totalItems).append(",result:");
		
		Gson gson=null;
		if(onlyIncludeExpose){
			gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(Constants.DATE_FORMAT_FULL).create();
		}else{
			gson=new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).create();
		}
		buff.append(gson.toJson(listData));
		
		buff.append("}");
		
		return buff.toString();
	}
	
	public String gsonFormat(List listData,int totalItems){
		return gsonFormat(listData,totalItems,false);
	}
 
	
	// 更新页面缓存
	protected void flushCache() {
		Cache cache = ServletCacheAdministrator.getInstance(getRequest().getSession().getServletContext()).getCache(getRequest(), PageContext.APPLICATION_SCOPE); 
		cache.flushAll(new Date());
	}
	
	public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data) {
		try {
			ServletContext servletContext = ServletActionContext.getServletContext();
			Configuration configuration = freemarkerManager.getConfiguration(servletContext);
			Template template = configuration.getTemplate(templateFilePath);
			File htmlFile = new File(servletContext.getRealPath(htmlFilePath));
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 稳安贷使用
	 * @param financeApplyUser
	 * @return
	 */
	public List<BpFinanceApplyUser> getMenu(BpFinanceApplyUser financeApplyUser){
		List<BpFinanceApplyUser> listApplyUser=new ArrayList<BpFinanceApplyUser>();
		String flowNodesChina="";
		String flowNodes="";
		if(financeApplyUser.getProductId()!=null){
/*		if(financeApplyUser.getProductId()==12){//第一个产品
			flowNodesChina=configMap.get("loanProduct1flowChina").toString();
			flowNodes=configMap.get("loanProduct1flow").toString();
		}else if(financeApplyUser.getProductId()==13){//第二个产品
			flowNodesChina=configMap.get("loanProduct2flowChina").toString();
			flowNodes=configMap.get("loanProduct2flow").toString();
		}else if(financeApplyUser.getProductId()==14){//第三个产品
			flowNodesChina=configMap.get("loanProduct3flowChina").toString();
			flowNodes=configMap.get("loanProduct3flow").toString();
		}else if(financeApplyUser.getProductId()==15){//第四个产品
			if("青春贷".equals(financeApplyUser.getProductName())){
				flowNodesChina=configMap.get("loanProduct8flowChina").toString();
				flowNodes=configMap.get("loanProduct8flow").toString();
			}else{
				flowNodesChina=configMap.get("loanProduct7flowChina").toString();
				flowNodes=configMap.get("loanProduct7flow").toString();
			}
		}else if(financeApplyUser.getProductId()==16){
			//TODO 青春贷相关配置
			flowNodesChina=configMap.get("loanProduct8flowChina").toString();
			flowNodes=configMap.get("loanProduct8flow").toString();
		}*/
		String[] strEng=flowNodes.split("\\|");//英文英文标识
		String[] strChina=flowNodesChina.split("\\|");//节点中文字标识
		String[] nodeEnable=financeApplyUser.getFinishStatus().split("\\|");
		for(int i=0;i<strEng.length;i++){
			BpFinanceApplyUser appuser=new BpFinanceApplyUser();
			appuser.setNodeEng(strEng[i]);
			appuser.setNodeChina(strChina[i]);
			appuser.setNodeEnable(nodeEnable[i]);
			listApplyUser.add(appuser);
		}
		}
		return listApplyUser;
	}
	//日期集合
	public List<String> getDateList(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Date date=new Date();
		listDate=new ArrayList<String>();
		for(int i=1980;i<=Integer.parseInt(sdf.format(date));i++){
			listDate.add(i+"");
		}
		return listDate;
	}
	
	public boolean isApp(){
		return "1".equals(getRequest().getHeader("isApp"))
				||"1".equals(getRequest().getParameter("isApp"))
				||HttpRequestDeviceUtils.isAppBrower(getRequest());

	}
	
	
}
