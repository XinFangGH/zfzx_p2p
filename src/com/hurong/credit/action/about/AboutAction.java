package com.hurong.credit.action.about;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.HtmlConfig;
import com.hurong.credit.dao.thirdInterface.PlThirdInterfaceLogDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.P2pFriendlink;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.P2pFriendlinkService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.thirdInterface.impl.PlThirdInterfaceLogServiceImpl;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

@SuppressWarnings("serial")
public class AboutAction  extends BaseAction{
	
	
	private String csstype;//导航栏样式0000000000000
	
	
	
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private P2pFriendlinkService p2pFriendlinkService;
	//友情链接
	private List<P2pFriendlink> articleLinks;
//	@Resource
//	private  SXTMessageSer sXTMessageService;
//	//短线发送接口
//	public MessageStrategy  messageStrategyImpl = new MessageStrategyImpl();
	
 
	
	public void setArticleLinks(List<P2pFriendlink> articleLinks) {
		this.articleLinks = articleLinks;
	}
	public List<P2pFriendlink> getArticleLinks() {
		return articleLinks;
	}
	
	public String getCsstype() {
		return csstype;
	}
	public void setCsstype(String csstype) {
		this.csstype = csstype;
	}
	

//	//测试短信接口信息
//	public String listTest(){
//		
//	 
// 
////		messageStrategyImpl.sendMsg("15701332275", "2366555", "您的验证码为123456，请在页面中输入以完成验证，有问题请致电15701332275【技术部】");
//		return SUCCESS;
//	}
	
	private PlThirdInterfaceLogDao dao ;
	
	public  PlWebShowMaterialsService plWebShowMaterialsService ;
	
	
//	public  SXTMessageSer sXTMessageSer ;
 
//	@Resource
//	private MessageStrategy  messageStrategy;
//	//添加测试数据
//	public String addData(){
//		messageStrategy.sendMsg("15701332275", "", "");
//		return SUCCESS;
//	}

	//法律政策
	public String legalPolicies(){
		csstype = "about";
		
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LEGALPOLICIES).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		
		return SUCCESS;
	}
	

	//关于我们，可直接查看
	public String to(){
		csstype = "about";
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ABOUT).getTemplateFilePath());
		return SUCCESS;
	}
	
	//招贤纳士
	public String recruitment(){
		csstype = "about";
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.RECRUITMENT).getTemplateFilePath());
		return SUCCESS;
	}
	
	//联系我们，可直接查看
	public String contact(){

		//csstype = "about";
		

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LINKOWER).getTemplateFilePath());

		return SUCCESS;
	}
	
	//商务合作，可直接查看
	public String cooperation(){
		csstype = "about";
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.COOPERATION).getTemplateFilePath());
		return SUCCESS;
	}
	
	//操作指南，可直接查看
	public String operationsGuide(){
		csstype = "operation";
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
			QueryFilter filter =new QueryFilter(this.getRequest());
			
			bpCustMemberService.getAll(filter);
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.OPERATIONGUIDE).getTemplateFilePath());
		return SUCCESS;
	}
	/**
	 * 新手指引 vip
	 * @return
	 */
	public String newuser(){
	 String type=this.getRequest().getParameter("type");
	 if(type.equals("vip")){
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NEWUSERVIP).getTemplateFilePath());
		 
	 }else if(type.equals("touzi")){
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NEWUSERTOUZI).getTemplateFilePath());
		 
	 }else if(type.equals("jiekuan")){
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NEWUSERJIEKUAN).getTemplateFilePath());
	 }else if(type.equals("anquan")){
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.NEWUSERANQUAN).getTemplateFilePath());
	 }
		
		return SUCCESS;
	}
	
	/**
	 * 生成错误页
	 */
	public void createErrorPage(){
		errorPageBuildHtml();
		 errorPage500BuildHtml();
		 errorPage404BuildHtml() ;
		 errorPage403BuildHtml();
		 
	}

	public void errorPageBuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("systemConfig", this.getSystemConfig());
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPageAccessDeniedBuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("systemConfig", this.getSystemConfig());
		data.put("errorContent", "您无此访问权限！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage500BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_500);
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("systemConfig", this.getSystemConfig());
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage404BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_404);
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("errorContent", "您访问的页面不存在！");
		data.put("systemConfig", this.getSystemConfig());
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage403BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_403);
		Map<String, Object> data =new HashMap<String, Object>();
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		data.put("systemConfig", this.getSystemConfig());
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}

	/**
	 * 友情链接
	 * @return
	 */
	public String links(){
		HttpServletRequest request=this.getRequest();
		request.setAttribute("type", 2);
		articleLinks= p2pFriendlinkService.getByRequest(getRequest());
		return "toLinks";
	}
	/**
	 * 合作伙伴
	 */
	public String partners(){
		System.out.println("-------合作伙伴-------");
		return "toPartners";
	}

}
