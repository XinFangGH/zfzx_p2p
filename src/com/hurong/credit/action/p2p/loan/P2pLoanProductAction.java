package com.hurong.credit.action.p2p.loan;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.p2p.BpFinanceApply;
import com.hurong.credit.model.p2p.loan.P2pLoanApplyStep;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.p2p.loan.P2pLoanConditionOrMaterial;
import com.hurong.credit.model.p2p.loan.P2pLoanProduct;
import com.hurong.credit.model.p2p.loan.P2pLoanRate;
import com.hurong.credit.model.system.GlobalType;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.user.CsDicAreaDynam;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.p2p.loan.P2pLoanApplyStepService;
import com.hurong.credit.service.p2p.loan.P2pLoanBasisMaterialService;
import com.hurong.credit.service.p2p.loan.P2pLoanConditionOrMaterialService;
import com.hurong.credit.service.p2p.loan.P2pLoanProductService;
import com.hurong.credit.service.p2p.loan.P2pLoanRateService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.system.GlobalTypeService;
import com.hurong.credit.service.system.product.DictionaryService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.CsDicAreaDynamService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
/**
 * 
 * @author 
 *
 */
public class P2pLoanProductAction extends BaseAction{
	@Resource
	private P2pLoanProductService p2pLoanProductService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private P2pLoanConditionOrMaterialService p2pLoanConditionOrMaterialService;
	@Resource
	private BpFinanceApplyUserService financeApplyUserService;
	@Resource
	private P2pLoanRateService p2pLoanRateService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private WebFinanceApplyUploadsService webFinanceApplyUploadService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private CsDicAreaDynamService csDicAreaDynamService;
	@Resource
	private P2pLoanApplyStepService p2pLoanApplyStepService;
	@Resource
	private P2pLoanBasisMaterialService p2pLoanBasisMaterialService;
	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	private P2pLoanProduct p2pLoanProduct;
	public List<P2pLoanProduct> productList;
	public List<P2pLoanProduct> productPersonList;
	public List<P2pLoanProduct> productEnterpriseList;
	private List<Dictionary> listloanProject;
	private Long productId;
	private BpFinanceApplyUser financeApplyUser;
	List<BpFinanceApplyUser> listApplyUser;
	private List<WebFinanceApplyUploads> webFinanceApplylist;
	private WebFinanceApplyUploads webFinanceApplyUploads;
	private List<Dictionary> listCompanysize;
	private BpCustMember bpCustMember;
	List<P2pLoanConditionOrMaterial> conditionList;
	private List<WebFinanceApplyUploads> webFinanceApplyUploadsList;
	
	private File upfile;//上传文件域的属性
	private String upfileContentType;//上传文件类型的属性
	private String upfileFileName;//上传文件名的属性
	private Long teamId;
	private Long projId;
	
	
	public List<WebFinanceApplyUploads> getWebFinanceApplyUploadsList() {
		return webFinanceApplyUploadsList;
	}

	public void setWebFinanceApplyUploadsList(
			List<WebFinanceApplyUploads> webFinanceApplyUploadsList) {
		this.webFinanceApplyUploadsList = webFinanceApplyUploadsList;
	}

	public List<P2pLoanConditionOrMaterial> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<P2pLoanConditionOrMaterial> conditionList) {
		this.conditionList = conditionList;
	}

	public File getUpfile() {
		return upfile;
	}

	public void setUpfile(File upfile) {
		this.upfile = upfile;
	}

	public String getUpfileContentType() {
		return upfileContentType;
	}

	public void setUpfileContentType(String upfileContentType) {
		this.upfileContentType = upfileContentType;
	}

	public String getUpfileFileName() {
		return upfileFileName;
	}

	public void setUpfileFileName(String upfileFileName) {
		this.upfileFileName = upfileFileName;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}

	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}

	public List<Dictionary> getListCompanysize() {
		return listCompanysize;
	}

	public void setListCompanysize(List<Dictionary> listCompanysize) {
		this.listCompanysize = listCompanysize;
	}

	public WebFinanceApplyUploads getWebFinanceApplyUploads() {
		return webFinanceApplyUploads;
	}

	public void setWebFinanceApplyUploads(
			WebFinanceApplyUploads webFinanceApplyUploads) {
		this.webFinanceApplyUploads = webFinanceApplyUploads;
	}

	public List<WebFinanceApplyUploads> getWebFinanceApplylist() {
		return webFinanceApplylist;
	}

	public void setWebFinanceApplylist(
			List<WebFinanceApplyUploads> webFinanceApplylist) {
		this.webFinanceApplylist = webFinanceApplylist;
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

	public List<Dictionary> getListloanProject() {
		return listloanProject;
	}

	public void setListloanProject(List<Dictionary> listloanProject) {
		this.listloanProject = listloanProject;
	}

	public List<P2pLoanProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<P2pLoanProduct> productList) {
		this.productList = productList;
	}

	public List<P2pLoanProduct> getProductPersonList() {
		return productPersonList;
	}

	public void setProductPersonList(List<P2pLoanProduct> productPersonList) {
		this.productPersonList = productPersonList;
	}

	public List<P2pLoanProduct> getProductEnterpriseList() {
		return productEnterpriseList;
	}

	public void setProductEnterpriseList(List<P2pLoanProduct> productEnterpriseList) {
		this.productEnterpriseList = productEnterpriseList;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public P2pLoanProduct getP2pLoanProduct() {
		return p2pLoanProduct;
	}

	public void setP2pLoanProduct(P2pLoanProduct p2pLoanProduct) {
		this.p2pLoanProduct = p2pLoanProduct;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		String isMobile =this.getRequest().getParameter("isMobile");
		//得到微博二维码
		List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
		this.getRequest().setAttribute("fileList", fileList);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//个人贷发布中的产品
		QueryFilter filter=new QueryFilter(getRequest());
		filter.addFilter("Q_productState_L_EQ", "4");
		filter.addFilter("Q_operationType_S_EQ", "person");
		productPersonList= p2pLoanProductService.getAll(filter);
		for(P2pLoanProduct person:productPersonList){
			QueryFilter filter2=new QueryFilter(getRequest());
			filter2.addFilter("Q_conditionType_L_EQ", "1");//查询申请条件
			filter2.addFilter("Q_productId_L_EQ", person.getProductId().toString());
			List<P2pLoanConditionOrMaterial> conditionList = p2pLoanConditionOrMaterialService.getAll(filter2);
			person.setConditionList(conditionList);
		}
		//企业贷发布中的产品 (分开查询，便于页面取下标值，给div背景颜色设置)
		QueryFilter filter1=new QueryFilter(getRequest());
		filter1.addFilter("Q_productState_L_EQ", "4");
		filter1.addFilter("Q_operationType_S_EQ", "enterprise");
		productEnterpriseList= p2pLoanProductService.getAll(filter1);
		for(P2pLoanProduct enterprise:productEnterpriseList){
			QueryFilter filter3=new QueryFilter(getRequest());
			filter3.addFilter("Q_conditionType_L_EQ", "1");//查询申请条件
			filter3.addFilter("Q_productId_L_EQ", enterprise.getProductId().toString());
			List<P2pLoanConditionOrMaterial> conditionList = p2pLoanConditionOrMaterialService.getAll(filter3);
			enterprise.setConditionList(conditionList);
		}
		 if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(productPersonList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
					"intentDate", "factDate", "interestStarTime",
				"interestEndTime" });
				buff.append(json.serialize(productPersonList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.FINACECENTER).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 贷款详细信息展示
	 * @return
	 */
	public String showProduct(){
		String  isMobile =this.getRequest().getParameter("isMobile");
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//未登录则跳转到登陆页面
		 if(bpCustMember==null){
			 if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":false,\"result\":")
					.append(productPersonList.size()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer();
					buff.append(json.serialize("201"));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
			  }
			 System.out.println(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			 return "freemarker";
		 } else if(null!=bpCustMember.getCustomerType() && 0!=bpCustMember.getCustomerType()){
			 webMsgInstance("0", Constants.CODE_FAILED,
						"个人信用贷款只针对个人线上注册用户", "", "", "", "", "");
			  if(null!=isMobile&&isMobile.endsWith("1")){
				  	JSONSerializer json = JsonUtil.getJSONSerializer();
					StringBuffer buff = new StringBuffer("{\"success\":false,\"result\":")
					.append(json.serialize("个人信用贷款只针对个人线上注册用户"));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
			  }
				this.setSuccessResultValue(TemplateConfigUtil
						.getDynamicConfig(DynamicConfig.MESSAGE)
						.getTemplateFilePath());
				 return "freemarker";
		 }else{
	          String productId = this.getRequest().getParameter("productId");
	           if(productId!=null&&!productId.equals("")){
		          QueryFilter filter1=new QueryFilter(getRequest());
		          filter1.addFilter("Q_productId_L_EQ", productId);
		          productList= p2pLoanProductService.getAll(filter1);
		          for(P2pLoanProduct product:productList){
			        //查询申请条件
		        	QueryFilter filter3=new QueryFilter(getRequest());
			        filter3.addFilter("Q_conditionType_L_EQ", "1");
		          	filter3.addFilter("Q_productId_L_EQ", product.getProductId().toString());
		           	List<P2pLoanConditionOrMaterial> conditionList = p2pLoanConditionOrMaterialService.getAll(filter3);
			        product.setConditionList(conditionList);
			
			       //查询申请材料
		         	QueryFilter filter2=new QueryFilter(getRequest());
		        	filter2.addFilter("Q_conditionType_L_EQ", "2");
		         	filter2.addFilter("Q_productId_L_EQ", product.getProductId().toString());
		         	List<P2pLoanConditionOrMaterial> conditionMaterialList = p2pLoanConditionOrMaterialService.getAll(filter2);
			       //查询材料是否上传了样例图片
		         	for(P2pLoanConditionOrMaterial material:conditionMaterialList){
			    	getMaterialImgUrl(material,material.getProjectId(),material.getConditionId());
			       }
			       product.setConditionMaterialList(conditionMaterialList);
			      //查询贷款期限利率
			       QueryFilter filter4=new QueryFilter(getRequest());
			       filter4.addFilter("Q_productId_L_EQ", product.getProductId().toString());
			       List<P2pLoanRate> loanRateList = p2pLoanRateService.getAll(filter4);
			       product.setLoanRateList(loanRateList);
			       product.setListSize(loanRateList.size());
		      }
		          if(null!=isMobile&&isMobile.endsWith("1")){
						StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
						.append(productList.size()).append(",\"result\":");
						JSONSerializer json = JsonUtil.getJSONSerializer();
						json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
						buff.append(json.serialize(productList));
						buff.append("}");
						jsonString = buff.toString();
						return SUCCESS;
				}
	         }
	            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
			    DynamicConfig.P2PLOANPRODUCT).getTemplateFilePath());
	            return "freemarker";
	      
	  }
	}
	/**
	 * 查询材料示例图片
	 * @param projectId
	 * @param conditionId
	 */
	public void getMaterialImgUrl(P2pLoanConditionOrMaterial material,Long projectId,Long conditionId){
		String imgUrl1="";//示例图1
		String imgUrl2="";//示例图2
		if(projectId!=null&&!projectId.equals("")){
			//查询基础材料是否上传了图片
			imgUrl1 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_basismaterial.shilitu1."+projectId);
			imgUrl2 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_basismaterial.shilitu2."+projectId);
		}else{
			imgUrl1 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_conditionormaterial.shilitu1."+conditionId);
			imgUrl2 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_conditionormaterial.shilitu2."+conditionId);
		}
		if(!imgUrl2.equals("")&&imgUrl2!=null){
			material.setImgUrl2(imgUrl2);
		}
		if(!imgUrl1.equals("")&&imgUrl1!=null){
			material.setImgUrl1(imgUrl1);
		}
	}
	/**
	 * 填写借款信息
	 * @return
	 */
	public String creditInfo(){
		String isMobile =this.getRequest().getParameter("isMobile");
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		P2pLoanProduct loanProduct = p2pLoanProductService.get(productId);
		long productId=Long.parseLong(this.getRequest().getParameter("productId"));
		if(bpCustMember==null){
			if(isMobile!=null){
				StringBuffer sb = new StringBuffer();
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				sb.append("{\"success\":false,\"message\":");
	    		sb.append(gson.toJson("未登录"));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			this.getRequest().setAttribute("loanProduct", loanProduct);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.APPPERSON).getTemplateFilePath());
			return "freemarker";
		}else if("".equals(bpCustMember.getThirdPayFlagId())||bpCustMember.getThirdPayFlagId() == null){
			if(isMobile!=null){
				StringBuffer sb = new StringBuffer();
				Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				sb.append("{\"success\":false,\"message\":");
	    		sb.append(gson.toJson("未开通资金托管账户"));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			this.getRequest().getSession().setAttribute("retUrl", "user/safeBpCustMember.do?safe=all");
			webMsgInstance("0", Constants.CODE_FAILED, "未开通资金托管账户",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());
			return "freemarker"; 
		}else{
			BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
			applyUser.setUserID(bpCustMember.getId());
			applyUser.setState("0");
			applyUser.setState("3");
			List<BpFinanceApplyUser> list1 =financeApplyUserService.getFinanceApplyState(applyUser);
			if(list1.size()>0){
				if(isMobile!=null){
					StringBuffer sb = new StringBuffer();
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					sb.append("{\"success\":false,\"message\":");
		    		sb.append(gson.toJson("您有一个未完成或被打回的产品、请在个人中心查看"));
					sb.append("}");
					setJsonString(sb.toString());
					return SUCCESS;
				}
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个未完成或被打回的产品、请在个人中心查看。。。",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "freemarker";
			}
			applyUser.setState("1");
			applyUser.setState1("2");
//			applyUser.setState2("4");
			//判断审请的产品是否通过审核
			List<BpFinanceApplyUser> list2 =financeApplyUserService.getFinanceApplyState(applyUser);
			if(list2.size()>0){
				if(isMobile!=null){
					StringBuffer sb = new StringBuffer();
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
					sb.append("{\"success\":false,\"message\":");
		    		sb.append(gson.toJson("您有一个正在审核的产品、请在个人中心查看"));
					sb.append("}");
					setJsonString(sb.toString());
					return SUCCESS;
				}
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个正在审核的产品、请在个人中心查看。。。",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "freemarker";
			}else{
					if(loanProduct!=null){
						//保存请求借款类型（注册用户）
						BpFinanceApply financeApply=new BpFinanceApply();
						String flownnode="";
						//产品id
						financeApply.setProductId(productId);
						//产品名称
						financeApply.setProductName(loanProduct.getProductName());
						//查询产品配置流程
						QueryFilter filter0 = new QueryFilter(this.getRequest());
						filter0.addFilter("Q_productId_L_EQ", productId);
						List<P2pLoanApplyStep> step = p2pLoanApplyStepService.getAll(filter0);
						//个人信息开始默认
						flownnode="nodePerson|";
						if(step.size()>0){
							for(P2pLoanApplyStep s:step){
								if(!s.getKeyValue().equals("nodePerson")&&!s.getKeyValue().equals("nodeOver")){
									flownnode+=s.getKeyValue()+"|";
								}
							}
						}
						//最后完成默认
						flownnode+="nodeOver";
						financeApply.setFlowNode(flownnode);
						String[] str=flownnode.split("\\|");
						String finshState="";
						for(int i=0;i<str.length;i++){
							finshState="0"+"|"+finshState;
						}
						
						financeApply.setFinishState(finshState);
						//默认为第一个流程节点
						financeApply.setCurrnodeid(str[0]+"");
						QueryFilter filter=new QueryFilter(this.getRequest());
						filter.addFilter("Q_proTypeId_L_EQ", 1134+"");//借款用途列表
						filter.addFilter("Q_status_S_EQ", 0+"");
						listloanProject=dictionaryService.getAll(filter);
						if(isMobile!=null&&"0".equals(isMobile)){
							StringBuffer sb = new StringBuffer();
							Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
							sb.append("{\"success\":true,\"data\":");
				    		sb.append(gson.toJson(financeApply));
							sb.append("}");
							setJsonString(sb.toString());
							return SUCCESS;
						}
						//查询贷款期限利率
						QueryFilter filter4=new QueryFilter(getRequest());
						filter4.addFilter("Q_productId_L_EQ", productId);
						List<P2pLoanRate> loanRateList = p2pLoanRateService.getAll(filter4);
						loanProduct.setLoanRateList(loanRateList);
						if(isMobile!=null){
							StringBuffer sb = new StringBuffer();
							Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
							sb.append("{\"success\":true,\"data\":");
				    		sb.append(gson.toJson(financeApply));
							sb.append("}");
							setJsonString(sb.toString());
							return SUCCESS;
							
						}
						this.getRequest().setAttribute("loanProduct", loanProduct);
						this.getRequest().setAttribute("financeApply", financeApply);
						
					}else{
						if(isMobile!=null){
							StringBuffer sb = new StringBuffer();
							Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
							sb.append("{\"success\":false,\"message\":");
				    		sb.append(gson.toJson("产品信息有误"));
							sb.append("}");
							setJsonString(sb.toString());
							return SUCCESS;
						}
						webMsgInstance("0", Constants.CODE_FAILED, "产品信息有误。。。",  "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
								DynamicConfig.MESSAGE).getTemplateFilePath());
						return "freemarker";
					}
						
				}
			}
			bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CREDIT).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 继续添写，跳转到相应的页面
	 * @return
	 */
	public String getNode(){
		try{
			String productId = this.getRequest().getParameter("productId");
			//long id = Long.parseLong(this.getRequest().getParameter("id"));
			bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//查询申请的产品信息
			QueryFilter filter = new QueryFilter(this.getRequest());
			List<String> listLong = new ArrayList<String>();
			listLong.add("0");
			listLong.add("3");
			filter.addFilter("Q_productId_L_EQ", productId);
			filter.addFilter("Q_userID_L_EQ", bpCustMember.getId());
			filter.addFilterIn("Q_state_S_IN", listLong);
			List<BpFinanceApplyUser> applyuser = financeApplyUserService.getAll(filter);
			if(applyuser.size()>0){
				financeApplyUser=applyuser.get(0);
			}else{
				financeApplyUser = new BpFinanceApplyUser();
			}
			//得到最新的bpCustMember资料
			bpCustMember = bpCustMemberService.get(bpCustMember.getId());
			//BeanUtil.copyNotNullProperties(financeApplyUser,applyuser);
			getList(bpCustMember,financeApplyUser.getProductId());
			String nowNode = financeApplyUser.getCurrentnode();
			financeApplyUser.setShowMenu(nowNode);
			listApplyUser = new ArrayList<BpFinanceApplyUser>();
			//查询申请步骤
			listApplyUser = getApplyStep(financeApplyUser);
			//查询材料信息
			getConditionOrMaterial(productId,bpCustMember.getId());
			getDateList();//得到日期集合
			
			webFinanceApplylist = webFinanceApplyUploadService.getUploadState(financeApplyUser.getUserID());
			//上传的材料图片
			/*webFinanceApplyUploads = new WebFinanceApplyUploads();
			for(WebFinanceApplyUploads u:webFinanceApplylist){
				if(u.getStatus() == 1){
					if(u.getMaterialstype().equals("IDCard")){
						webFinanceApplyUploads.setRefIDCard(1);
					}
				}
			}*/
				if(!"".equals(bpCustMember.getHomePhone())&&bpCustMember.getHomePhone()!=null&&!bpCustMember.getHomePhone().equals("-")){
					String[] strHomePhone=bpCustMember.getHomePhone().split("-");
					if(strHomePhone.length>1){
						bpCustMember.setHomePhonePrefix(strHomePhone[0]);
						bpCustMember.setHomePhoneSuffix(strHomePhone[1]);
					}
					
				}
				if(!"".equals(bpCustMember.getWebshopPhone())&&bpCustMember.getWebshopPhone()!=null&&!bpCustMember.getWebshopPhone().equals("-")){
					String [] strWebPhone=bpCustMember.getWebshopPhone().split("-");
					if(strWebPhone.length>1){
						bpCustMember.setWebshopPhonePrefix(strWebPhone[0]);
						bpCustMember.setWebshopPhoneSuffix(strWebPhone[1]);
					}
					
				}
				if(!"".equals(bpCustMember.getHireCompanyphone())&&bpCustMember.getHireCompanyphone()!=null&&!bpCustMember.getHireCompanyphone().equals("-")){
					String[] strComPhone=bpCustMember.getHireCompanyphone().split("-");
					if(strComPhone.length>1){
						bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
						bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
					}
					
				}
				if(financeApplyUser.getProductId()!=12){
				if(!"".equals(bpCustMember.getTeacherCompanyphone())&&bpCustMember.getTeacherCompanyphone()!=null&&!bpCustMember.getTeacherCompanyphone().equals("-")){
					String[] strComPhone=bpCustMember.getTeacherCompanyphone().split("-");
					if(strComPhone.length>1){
						bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
						bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
					}
				}
				}
				if(!"".equals(bpCustMember.getBossCompanyphone())&&bpCustMember.getBossCompanyphone()!=null&&!bpCustMember.getBossCompanyphone().equals("-")){
					String[] strComPhone=bpCustMember.getBossCompanyphone().split("-");
					if(strComPhone.length>1){
						bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
						bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
					}
				}
				String isPass = financeApplyUserService.getPassThrough(bpCustMember.getId());//查询借款用户是否有已通过借款审核的信息
				this.getRequest().setAttribute("isPass", isPass);
	
		}catch(Exception e){
			e.printStackTrace();
		}
		if("7".equals(this.getRequest().getParameter("state"))){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/finance/mobileAuthentication.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.P2PPERSON).getTemplateFilePath());
		}
		 return "freemarker";	
	}
	/**
	 * 查询材料信息
	 */
	private void getConditionOrMaterial(String productId,Long userId){
		QueryFilter filter1=new QueryFilter(getRequest());
		filter1.addFilter("Q_conditionType_L_EQ", "2");
		filter1.addFilter("Q_productId_L_EQ", productId);
		conditionList = p2pLoanConditionOrMaterialService.getAll(filter1);
		//判断材料图片是否上传
		for(P2pLoanConditionOrMaterial list:conditionList){
			QueryFilter filter2=new QueryFilter(getRequest());
			filter2.addFilter("Q_conditionId_L_EQ", list.getConditionId());
			filter2.addFilter("Q_userID_L_EQ", userId);
			List<WebFinanceApplyUploads> apply = webFinanceApplyUploadService.getAll(filter2);
			if(apply.size()>0){
				list.setWebFinanceApplyUploadsList(apply);
				list.setImgUrl(apply.get(0).getFiles());
				list.setStatus(apply.get(0).getStatus());
				list.setRejectReason(apply.get(0).getRejectReason());
			}else{
				//检查是否基础材料上传了图片
				if(list.getProjectId()!=null){
					QueryFilter filter3=new QueryFilter(getRequest());
					filter3.addFilter("Q_materialId_L_EQ", list.getProjectId());
					filter3.addFilter("Q_userID_L_EQ", userId);
					List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter3);
					if(applys.size()>0){
						list.setWebFinanceApplyUploadsList(applys);
						list.setImgUrl(applys.get(0).getFiles());
						list.setStatus(applys.get(0).getStatus());
						list.setRejectReason(applys.get(0).getRejectReason());
					}
				}
			}
		}
		
	}
	/**
	 * 查询申请步骤
	 * @param financeApplyUser
	 */
	private List<BpFinanceApplyUser> getApplyStep(BpFinanceApplyUser financeApplyUser){

		List<BpFinanceApplyUser> listApplyUser=new ArrayList<BpFinanceApplyUser>();
		//String flowNodesChina="";
		String flowNodes=financeApplyUser.getFlownodes();
		if(financeApplyUser!=null){
			String[] strEng=flowNodes.split("\\|");//英文英文标识
			//String[] strChina=flowNodesChina.split("\\|");//节点中文字标识
			String[] nodeEnable=financeApplyUser.getFinishStatus().split("\\|");
			for(int i=0;i<strEng.length;i++){
				BpFinanceApplyUser appuser=new BpFinanceApplyUser();
				appuser.setNodeEng(strEng[i]);
				if(strEng[i].equals("nodePerson")){
					appuser.setNodeChina("个人信息");
				}else if(strEng[i].equals("nodeFamily")){
					appuser.setNodeChina("家庭信息");
				}else if(strEng[i].equals("nodeStore")){
					appuser.setNodeChina("网店信息");
				}else if(strEng[i].equals("nodeWork")){
					appuser.setNodeChina("工作信息");
				}else if(strEng[i].equals("nodeCompany")){
					appuser.setNodeChina("公司信息");
				}else if(strEng[i].equals("nodeOver")){
					appuser.setNodeChina("完成");
				}
				appuser.setNodeEnable(nodeEnable[i]);
				listApplyUser.add(appuser);
			}
		}
		return listApplyUser;
	
	}
	private void getList(BpCustMember mem,Long loadid){
		try{
			BpCustMember cust = bpCustMemberService.get(mem.getId());
			List<GlobalType> list=globalTypeService.getByNodeKey("dgree");
		    if(null!=list && list.size()>0){
			   List<Dictionary> listDgree=dictionaryService.getByProTypeId(list.get(0).getProTypeId());
			   this.getRequest().setAttribute("listDgree", listDgree);//最高学历
		    }
		    
		    List<GlobalType> list2=globalTypeService.getByNodeKey("gxrgx");
		    if(null!=list2 && list2.size()>0){
			   List<Dictionary> listGxrgx=dictionaryService.getByProTypeId(list2.get(0).getProTypeId());
			   this.getRequest().setAttribute("listGxrgx", listGxrgx);//亲属关系
		    }
		    
		    List<GlobalType> list3=globalTypeService.getByNodeKey("unitproperties");
		    if(null!=list3 && list3.size()>0){
		    	List<Dictionary> listUnitp;
//		    	if(loadid==16){
//		    		 listUnitp = dictionaryService.getByProTypeId2(list3.get(0).getProTypeId(),"1000");
//		    	}else{
		    		 listUnitp = dictionaryService.getByProTypeId(list3.get(0).getProTypeId());
//		    	}
			   
			   this.getRequest().setAttribute("listUnitp", listUnitp);//公司类别
		    }
		    
		    List<GlobalType> list4=globalTypeService.getByNodeKey("zhiwujob");
		    if(null!=list4 && list4.size()>0){
		    	List<Dictionary> listJob;
//		    	if(loadid==16){
//		    		listJob=dictionaryService.getByProTypeId2(list4.get(0).getProTypeId(),"1000");
//		    	}else{
		    		listJob=dictionaryService.getByProTypeId(list4.get(0).getProTypeId());
//		    	}
			   this.getRequest().setAttribute("listJob", listJob);//公司职位
		    }
		    
		    List<GlobalType> list5=globalTypeService.getByNodeKey("8");
		    if(null!=list5 && list5.size()>0){
			   List<Dictionary> listMarry=dictionaryService.getByProTypeId(list5.get(0).getProTypeId());
			   this.getRequest().setAttribute("listMarry", listMarry);//婚姻状况
		    }
		    
		    List<CsDicAreaDynam> listArea6591 = csDicAreaDynamService.listByParentId(6591);
		    this.getRequest().setAttribute("listArea6591", listArea6591);//工作城市
		    
		    CsDicAreaDynam csDicArea = csDicAreaDynamService.get(mem.getLiveCity()==null?0:mem.getLiveCity());//通过子级的信心反推回去
		    CsDicAreaDynam csDic= null;
		    if(csDicArea!=null){
		    	csDic= new CsDicAreaDynam();
		    	csDic= csDicAreaDynamService.get(new Long(csDicArea.getParentId()));//得到父级的信息
		    	csDicArea.setParentTitle(csDic.getTitle());//父级名称
		    	csDicArea.setParentTitleId(csDic.getId().toString());
		    }
		    this.getRequest().setAttribute("csDicArea", csDicArea);
		    
		    List<CsDicAreaDynam> listArea10092 = csDicAreaDynamService.listByParentId(10092);
		    this.getRequest().setAttribute("listArea10092", listArea10092);//公司行业
		    QueryFilter filter=new QueryFilter(this.getRequest());
		   
		    filter.addFilter("Q_proTypeId_L_EQ", 1228+"");//公司规模
//		    if(loadid==16){
//		    	filter.addFilter("Q_status_S_EQ", 1+"000");
//		    }else{
		    	filter.addFilter("Q_status_S_EQ", 0+"");
//		    }
			
			listCompanysize=dictionaryService.getAll(filter);
			
			/**********************************以下是查询下拉列表中的数据**************************************/
			 CsDicAreaDynam csDicAreaNativePlaceProvice = csDicAreaDynamService.get(cust.getNativePlaceProvice()==null?-10:Long.valueOf(cust.getNativePlaceProvice()));//籍贯省查询
			 CsDicAreaDynam csDicAreaNativePlaceCity = csDicAreaDynamService.get(cust.getNativePlaceCity()==null?-10:Long.valueOf(cust.getNativePlaceCity()));//籍贯市查询
			 CsDicAreaDynam csDicAreaLiveProvice = csDicAreaDynamService.get(cust.getLiveProvice()==null?-10:Long.valueOf(cust.getLiveProvice()));//户口所在地省查询
			 CsDicAreaDynam csDicAreaLiveCity = csDicAreaDynamService.get(cust.getLiveCity()==null?-10:Long.valueOf(cust.getLiveCity()));//户口所在地市查询
			 CsDicAreaDynam csDicAreaHireProvince = csDicAreaDynamService.get(cust.getHireProvince()==null?-10:Long.valueOf(cust.getHireProvince()));//公司城市省查询
			 CsDicAreaDynam csDicAreaHireCity = csDicAreaDynamService.get(cust.getHireCity()==null?-10:Long.valueOf(cust.getHireCity()));//公司城市市查询
			// CsDicAreaDynam csDicAreaBossCity = csDicAreaDynamService.get(cust.getBossCity()==null?-10:Long.valueOf(cust.getBossCity()));//工作城市市查询
			 
			 CsDicAreaDynam csDicAreaTeacherCity = csDicAreaDynamService.get(cust.getTeacherCity()==null?-10:Long.valueOf(cust.getTeacherCity()));//工作城市市查询
			 this.getRequest().setAttribute("csDicAreaNativePlaceProvice", csDicAreaNativePlaceProvice);
			 this.getRequest().setAttribute("csDicAreaNativePlaceCity", csDicAreaNativePlaceCity);
			 this.getRequest().setAttribute("csDicAreaLiveProvice", csDicAreaLiveProvice);
			 this.getRequest().setAttribute("csDicAreaLiveCity", csDicAreaLiveCity);
			 this.getRequest().setAttribute("csDicAreaHireProvince", csDicAreaHireProvince);
			 this.getRequest().setAttribute("csDicAreaHireCity", csDicAreaHireCity);
			 this.getRequest().setAttribute("csDicAreaTeacherCity", csDicAreaTeacherCity);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询上传材料信息
	 * @return
	 */
	public String geteProductMaterial(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String productId = this.getRequest().getParameter("productId");
		String conditionId = this.getRequest().getParameter("conditionId");
		if(productId!=null&&!productId.equals("")){//贷款材料
			P2pLoanConditionOrMaterial materialList=p2pLoanConditionOrMaterialService.get(Long.valueOf(conditionId));
			if(materialList!=null){
				QueryFilter filter2=new QueryFilter(getRequest());
				filter2.addFilter("Q_conditionId_L_EQ", conditionId);
				filter2.addFilter("Q_userID_L_EQ", bpCustMember.getId());
				List<WebFinanceApplyUploads> apply = webFinanceApplyUploadService.getAll(filter2);
				if(apply.size()>0){
					//materialList.setImgUrl(apply.get(0).getFiles());
					webFinanceApplyUploadsList=apply;
				}else{
					//检查是否基础材料上传了图片
					if(materialList.getProjectId()!=null){
						QueryFilter filter3=new QueryFilter(getRequest());
						filter3.addFilter("Q_materialId_L_EQ", materialList.getProjectId());
						filter3.addFilter("Q_userID_L_EQ",  bpCustMember.getId());
						List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter3);
						if(applys.size()>0){
							webFinanceApplyUploadsList=applys;
						}
					}
				}
				//查询材料示例图片
				getMaterialImgUrl(materialList,materialList.getProjectId(),materialList.getConditionId());
				this.getRequest().setAttribute("materialList", materialList);
			}
		}else{//基础材料
			P2pLoanBasisMaterial baseMaterial = p2pLoanBasisMaterialService.get(Long.valueOf(conditionId));
			if(baseMaterial!=null){
				//检查是否基础材料上传了图片
				QueryFilter filter3=new QueryFilter(getRequest());
				filter3.addFilter("Q_materialId_L_EQ", conditionId);
				filter3.addFilter("Q_userID_L_EQ",  bpCustMember.getId());
				List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter3);
				if(applys.size()>0){
					webFinanceApplyUploadsList=applys;
				}
				//查询基础材料是否上传了图片
				String imgUrl1 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_basismaterial.shilitu1."+baseMaterial.getMaterialId());
				String imgUrl2 = plWebShowMaterialsService.getMaterialImgUrl("p2p_loan_basismaterial.shilitu2."+baseMaterial.getMaterialId());
				if(!imgUrl2.equals("")&&imgUrl2!=null){
					baseMaterial.setImgUrl2(imgUrl2);
				}
				if(!imgUrl1.equals("")&&imgUrl1!=null){
					baseMaterial.setImgUrl1(imgUrl1);
				}
				this.getRequest().setAttribute("baseMaterial", baseMaterial);
			}
		}
		
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PRODUCTMATERIAL).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 上传头像
	 * @return
	 */
	public String saveHeadImage() {
		String conditionId = this.getRequest().getParameter("conditionId");
		//1=基础材料。2=贷款材料
		String uploadWay = this.getRequest().getParameter("uploadWay");
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		if (bpCustMember != null) {
			if (upfile != null && !"".equals(upfile)) {
				try {
					String uploadDir = "attachFiles" + File.separatorChar + "userMaterialImage" +  File.separatorChar + random() + File.separatorChar;
					String savePath = ServletActionContext.getServletContext().getRealPath(uploadDir);
					System.out.println("最后保存的路径：" + savePath);
					upfileFileName = updateFileName(upfileFileName);
					File savefile = new File(new File(savePath), upfileFileName);
					if (!savefile.getParentFile().exists()) {
						savefile.getParentFile().mkdirs();
					}
					FileUtils.copyFile(upfile, savefile);
					String backPath = uploadDir + upfileFileName;//返回上传的路径
					System.out.println("返回的路径backPath：" + backPath);
					sb.append("{\"result\":true,\"Msg\":");
					sb.append(gson.toJson(backPath));
					sb.append("}");
					/**
					 * 上传成功以后 进行保存
					 */
					if(uploadWay.equals("2")){
						P2pLoanConditionOrMaterial material = p2pLoanConditionOrMaterialService.get(Long.valueOf(conditionId));
						WebFinanceApplyUploads apply = new WebFinanceApplyUploads();
						apply.setUserID(bpCustMember.getId());
						apply.setFiles(backPath);
						apply.setConditionId(Long.valueOf(conditionId));
						if(material.getProjectId()!=null){
							apply.setMaterialId(material.getProjectId());
						}
						//apply.setRejectReason(material.getConditionContent());
						apply.setLastuploadtime(new Date());
						apply.setStatus(1);
						apply.setMaterialstype(material.getConditionContent());
						webFinanceApplyUploadService.save(apply);
					}else if(uploadWay.equals("1")){
						P2pLoanBasisMaterial material = p2pLoanBasisMaterialService.get(Long.valueOf(conditionId));
						WebFinanceApplyUploads apply = new WebFinanceApplyUploads();
						apply.setUserID(bpCustMember.getId());
						apply.setFiles(backPath);
						apply.setMaterialId(Long.valueOf(conditionId));
						//apply.setRejectReason(material.getMaterialName());
						apply.setLastuploadtime(new Date());
						apply.setStatus(1);
						apply.setMaterialstype(material.getMaterialName());
						webFinanceApplyUploadService.save(apply);
					}
					/*sb.append("{\"result\":true,\"errMsg\":");
					sb.append(gson.toJson("上传成功"));
					sb.append("}");*/
				} catch (Exception e) {
					e.printStackTrace();
					sb.append("{\"result\":false,\"Msg\":");
					sb.append(gson.toJson("失败"));
					sb.append("}");
				}
			} else {
				sb.append("{\"result\":false,\"Msg\":");
				sb.append(gson.toJson("失败"));
				sb.append("}");
			}
			setJsonString(sb.toString());
			return SUCCESS;
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}

	}
	
	/**
	 * 最新提交填写信息
	 * @return
	 */
	public String savefileUpload(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//得到bpFinancceApplyUser主键
		long loanid=Long.parseLong(this.getRequest().getParameter("loadid"));
		//得到该信息对象
		financeApplyUser=financeApplyUserService.get(loanid);
		String meg="";
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		sb.append("{\"success\":true,\"message\":");
		//判断前面的信息是否填写完成
		String finishStatus = financeApplyUser.getFinishStatus();
		String finisgstr[] = finishStatus.split("\\|");
		String fin = "1";
		if(finisgstr.length>2){
			for(int i=0;i<finisgstr.length-1;i++){
				if(finisgstr[i].equals("0")){
					fin = finisgstr[i];
					break;
				}else{
					fin = finisgstr[i];
				}
			}
		}
		if(fin.equals("1")){
			//查询产品所要上传的必要材料
			QueryFilter filter1=new QueryFilter(getRequest());
			filter1.addFilter("Q_conditionType_L_EQ", "2");
			filter1.addFilter("Q_conditionState_L_EQ", "1");
			filter1.addFilter("Q_productId_L_EQ", financeApplyUser.getProductId());
			List<P2pLoanConditionOrMaterial> conditionList = p2pLoanConditionOrMaterialService.getAll(filter1);
			for(P2pLoanConditionOrMaterial material:conditionList){
				QueryFilter filter3=new QueryFilter(getRequest());
				filter3.addFilter("Q_userID_L_EQ", financeApplyUser.getUserID());
				filter3.addFilter("Q_conditionId_L_EQ", material.getConditionId());
				List<WebFinanceApplyUploads> ucondititonList = webFinanceApplyUploadService.getAll(filter3);
				//查询材料以前是否上传过
				QueryFilter filter2=new QueryFilter(getRequest());
				filter2.addFilter("Q_userID_L_EQ", financeApplyUser.getUserID());
				filter2.addFilter("Q_materialId_L_EQ", material.getProjectId());
				List<WebFinanceApplyUploads> materialList = webFinanceApplyUploadService.getAll(filter2);
				if(ucondititonList.size()==0&&materialList.size()==0){
					meg+=material.getConditionContent()+",";
				}else{
					if(ucondititonList.size()==0&&material.getProjectId()==null){
						meg+=material.getConditionContent()+",";
					}
				}
			}
			if(!"".equals(meg)&&meg!=null){
				meg+="尚未上传";
				sb.append(gson.toJson(meg));
			}
			if(!"".equals(meg)&&meg!=null){//必要认证的都提交完成时
				sb.append(",\"result\":0");
			}else{	
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				financeApplyUser.setCreateTime(df.format(new Date()));//erp读取的这个字段 暂且把提交时间存放在这
				financeApplyUser.setSubmitTime(df.format(new Date()));//提交时间
				financeApplyUser.setState("1");
				financeApplyUserService.save(financeApplyUser);
				meg+="信息提交成功!";
				sb.append(gson.toJson(meg));
				sb.append(",\"result\":1");
			}
		}else{//表示前面还有未完成的信息。
			meg+="您还有借款信息未完成，请检查！";
			sb.append(gson.toJson(meg));
			sb.append(",\"result\":0");
		}
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 生成的验证码
	 * @return
	 */
	private String random() {
		Calendar nowtime = new GregorianCalendar();
		String random = String.valueOf((int) (Math.random() * (9999 - 1000)) + 1000);
		String strDateTime = String.format("%04d", nowtime.get(Calendar.YEAR)) + String.format("%02d", nowtime.get(Calendar.MONTH) + 1) + String.format("%02d", nowtime.get(Calendar.DATE)) + String.format("%02d", nowtime.get(Calendar.HOUR)) + String.format("%02d", nowtime.get(Calendar.MINUTE)) + String.format("%02d", nowtime.get(Calendar.SECOND)) + String.format("%03d", nowtime.get(Calendar.MILLISECOND)) + random;
		return strDateTime;
	}
	/**
	 * 删除材料认证
	 * @return
	 */
	public String deleteMaterialImg(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
		String id = this.getRequest().getParameter("id");
		if(id!=null&&!id.equals("")&&bpCustMember!=null){
			//判断传入进来的图片id是否是本人上传的图片
			WebFinanceApplyUploads webupload = webFinanceApplyUploadService.get(Long.valueOf(id));
			if(webupload!=null){
				if(bpCustMember.getId().equals(webupload.getUserID())){
					webFinanceApplyUploadService.remove(Long.valueOf(id));
					sb.append("{\"result\":true,\"Msg\":");
					sb.append(gson.toJson("success"));
					sb.append("}");
				}else{
					sb.append("{\"result\":false,\"Msg\":");
					sb.append(gson.toJson("恶意删除图片!"));
					sb.append("}");
				}
			}else{
				sb.append("{\"result\":false,\"Msg\":");
				sb.append(gson.toJson("false"));
				sb.append("}");
			}
		}else{
			sb.append("{\"result\":false,\"Msg\":");
			sb.append(gson.toJson("false"));
			sb.append("}");
		}
		setJsonString(sb.toString());
		return SUCCESS;
	} 
	/**
	 * 修改上传文件名
	 * @return
	 */
	private String updateFileName(String upfileFileName) {
		//String prefixName = upfileFileName.substring(0, upfileFileName.lastIndexOf("."));
		String suffixName = upfileFileName.substring(upfileFileName.lastIndexOf("."), upfileFileName.length());
		return random() + suffixName;
	}
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		P2pLoanProduct p2pLoanProduct=p2pLoanProductService.get(productId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(p2pLoanProduct));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(p2pLoanProduct.getProductId()==null){
			p2pLoanProductService.save(p2pLoanProduct);
		}else{
			P2pLoanProduct orgP2pLoanProduct=p2pLoanProductService.get(p2pLoanProduct.getProductId());
			try{
				BeanUtil.copyNotNullProperties(orgP2pLoanProduct, p2pLoanProduct);
				p2pLoanProductService.save(orgP2pLoanProduct);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 修改产品状态
	 */
	public String updateProduct(){
		String productId = this.getRequest().getParameter("productId");
		String productState = this.getRequest().getParameter("productState");
		if(productState!=null&&!productState.equals("")&&productId!=null&&!productId.equals("")){
			P2pLoanProduct product=p2pLoanProductService.get(Long.valueOf(productId));
			if(product!=null){
				product.setProductState(Long.valueOf(productState));
				p2pLoanProductService.save(product);
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
}
