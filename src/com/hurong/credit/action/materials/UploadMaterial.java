package com.hurong.credit.action.materials;

import java.util.List;

import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

public class UploadMaterial  extends BaseAction {

	
	private WebFinanceApplyUploads webFinanceApply;
	private List<WebFinanceApplyUploads> webFinanceApplylist;
	private String diagId;
	private Long loadid;
	private String currentnode;
	private Long userId;
	
	public List<WebFinanceApplyUploads> getWebFinanceApplylist() {
		return webFinanceApplylist;
	}
	public void setWebFinanceApplylist(
			List<WebFinanceApplyUploads> webFinanceApplylist) {
		this.webFinanceApplylist = webFinanceApplylist;
	}
	public WebFinanceApplyUploads getWebFinanceApply() {
		return webFinanceApply;
	}
	public void setWebFinanceApply(WebFinanceApplyUploads webFinanceApply) {
		this.webFinanceApply = webFinanceApply;
	}
	public String getDiagId() {
		return diagId;
	}
	public void setDiagId(String diagId) {
		this.diagId = diagId;
	}
	public Long getLoadid() {
		return loadid;
	}
	public void setLoadid(Long loadid) {
		this.loadid = loadid;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getCurrentnode() {
		return currentnode;
	}
	public void setCurrentnode(String currentnode) {
		this.currentnode = currentnode;
	}
	/**
	 * 身份认证
	 * 路径：/material/veriidcardMaterial.do
	 * @return
	 */
	public String veriidcard(){
		
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIIDCARD).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 信用认证
	 * 路径：/material/vericreditRecordMaterial.do
	 * @return
	 */
	public String vericreditRecord(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERICREDITRECORD).getTemplateFilePath());
		return SUCCESS;
	}
	/**
	 * 微信认证
	 * 路径：/material/vericreditRecordMaterial.do
	 * @return
	 */
	public String veriwechat(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/uploadMaterial/veri_Wechat.ftl");
		return SUCCESS;
	}
	
	/**
	 * 收入认证
	 * 路径：/material/veriincomeMaterial.do
	 * @return
	 */
	public String  veriincome(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIINCOME).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 学生认证
	 * 路径：/material/veriincomeMaterial.do
	 * @return
	 */
	public String  veristudent(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/uploadMaterial/veri_Student.ftl");
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 网店认证
	 * 路径：/material/veriwebShopMaterial.do
	 * @return
	 */
	public String  veriwebShop(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERWEBSHOP).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 房产认证
	 * 路径：/material/verihouseMaterial.do
	 * @return
	 */
	public String  verihouse(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIHOUSE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 购车认证
	 * 路径：/material/verivehicleMaterial.do
	 * @return
	 */
	public String  verivehicle(){
		diagId = this.getRequest().getParameter("diagId");
		loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIVEHICLE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 结婚认证
	 * 路径：/material/verimarriageMaterial.do
	 * @return
	 */
	public String  verimarriage(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIMARRIAGE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 学历认证
	 * 路径：/material/verieducationMaterial.do
	 * @return
	 */
	public String  verieducation(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIEDUCATION).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 工作认证
	 * 路径：/material/vericareerMaterial.do
	 * @return
	 */
	public String  vericareer(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERICAREER).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 职称认证
	 * 路径：/material/verijobtitleMaterial.do
	 * @return
	 */
	public String  verijobtitle(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIJOBTITLE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 手机认证
	 * 路径：/material/verimobilephoneMaterial.do
	 * @return
	 */
	public String  verimobilephone(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIMOBILEPHONE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 微博认证
	 * 路径：/material/verimicroblogMaterial.do
	 * @return
	 */
	public String  verimicroblog(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIMICROBLOG).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 居住认证
	 * 路径：/material/veriresidenceMaterial.do
	 * @return
	 */
	public String  veriresidence(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERIRESIDENCE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 经营场所认证
	 * 路径：/material/vericompanyplaceMaterial.do
	 * @return
	 */
	public String  vericompanyplace(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERICOMPANYPLACE).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 经营收入认证
	 * 路径：/material/vericompanyrevenueMaterial.do
	 * @return
	 */
	public String  vericompanyrevenue(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERICOMPANYREVENUE).getTemplateFilePath());
		return SUCCESS;
	}
	/**
	 * 教师资格认证
	 * @return
	 */
	public String  veriTeacher(){
		diagId = this.getRequest().getParameter("diagId");
		 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
		 currentnode=this.getRequest().getParameter("current");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.VERITEACHER).getTemplateFilePath());
		return SUCCESS;
	}
	/**
	 * 成功页面
	 * 路径：/material/successinfoMaterial.do
	 * @return
	 */
	public String successinfo(){
		diagId = this.getRequest().getParameter("diagId");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.UPLOADINFO).getTemplateFilePath());
		return SUCCESS;
	}
	
	/**
	 * 修改材料状态
	 * 路径：/material/updateStateMaterial.do
	 * @return
	 */
	public String updateState(){
		System.out.println("已进入updateState");
		//diagId为材料认证的唯一id
		diagId = this.getRequest().getParameter("diagId");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember = mem;
		//得到认证类型
		String type = this.getRequest().getParameter("type");
		WebFinanceApplyUploadsService webFinanceApplyUploadsService = (WebFinanceApplyUploadsService) AppUtil.getBean("webFinanceApplyUploadsService");
		//修改当前类型材料的状态
		webFinanceApply = new WebFinanceApplyUploads();
		if(!"".equals(type)&&type!=null){
			webFinanceApply = (WebFinanceApplyUploads) webFinanceApplyUploadsService.getUserIdBystate(Long.valueOf(mem.getId()),type);//修改状态
			if(webFinanceApply!=null){
				webFinanceApply.setStatus(1);
				boolean fag = webFinanceApplyUploadsService.update(webFinanceApply);
			}
		}
		//查询材料表
		webFinanceApplylist = webFinanceApplyUploadsService.getUploadState(bpCustMember.getId());
		if("7".equals(this.getRequest().getParameter("state"))){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/finance/mobileAuthentication.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EDITUSERINFO).getTemplateFilePath());
		}
		return SUCCESS;
	}
}
