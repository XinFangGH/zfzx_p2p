package com.hurong.credit.action.financePurchase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.system.GlobalType;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.user.CsDicAreaDynam;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.system.GlobalTypeService;
import com.hurong.credit.service.system.product.DictionaryService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.CsDicAreaDynamService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import freemarker.ext.util.IdentityHashMap;

public class BpFinanceApplyUserAction extends BaseAction{
	private BpFinanceApplyUser financeApplyUser;
	BpFinanceApplyUser user;
	@Resource
	private BpCustMemberService bpCustMemberService;
	
	public BpFinanceApplyUser getUser() {
		return user;
	}

	public void setUser(BpFinanceApplyUser user) {
		this.user = user;
	}
	List<BpFinanceApplyUser> listApplyUser;
	@Resource	
	private BpFinanceApplyUserService financeApplyUserService;
	@Resource
	private BpCustMemberService bpCustMemeberService;
	private BpCustMember bpCustMember;
	
	private List<WebFinanceApplyUploads> webFinanceApplylist;
	private WebFinanceApplyUploads webFinanceApplyUploads;
	@Resource
	private WebFinanceApplyUploadsService webFinanceApplyUploadService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private CsDicAreaDynamService csDicAreaDynamService;
	@Resource
	private DictionaryService dictionaryService;
	private List<Dictionary> listCompanysize;

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
	private static Map configMap = AppUtil.getConfigMap();
	
	public List<BpFinanceApplyUser> getListApplyUser() {
		return listApplyUser;
	}

	public void setListApplyUser(List<BpFinanceApplyUser> listApplyUser) {
		this.listApplyUser = listApplyUser;
	}

	public List<WebFinanceApplyUploads> getWebFinanceApplylist() {
		return webFinanceApplylist;
	}

	public void setWebFinanceApplylist(
			List<WebFinanceApplyUploads> webFinanceApplylist) {
		this.webFinanceApplylist = webFinanceApplylist;
	}

	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}

	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}

	public BpFinanceApplyUser getFinanceApplyUser() {
		return financeApplyUser;
	}

	public void setFinanceApplyUser(BpFinanceApplyUser financeApplyUser) {
		this.financeApplyUser = financeApplyUser;
	}

	public String saveApplyUser(){
			//得到session对象
			String isMobile =this.getRequest().getParameter("isMobile");
			bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			getList(bpCustMember,financeApplyUser.getProductId());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt=new Date();
			String tDate=format.format(dt);
			//向BpFinanceApplyUser表中添加数据
			if(financeApplyUser.getLoanId()==null && bpCustMember!=null){
				financeApplyUser.setUserID(bpCustMember.getId());
				financeApplyUser.setCreateTime(tDate);
				listApplyUser=financeApplyUserService.getFinanceApply(financeApplyUser);
				if(listApplyUser.size()==0){
					financeApplyUserService.save(financeApplyUser);
				}
				if(listApplyUser.size()>0){
					financeApplyUser.setLoanId(listApplyUser.get(0).getLoanId());
					financeApplyUserService.merge(financeApplyUser);
				}
				listApplyUser=new ArrayList<BpFinanceApplyUser>();
				//调用baseDao方法
				//listApplyUser=getMenu(financeApplyUser);
				//查询申请步骤
				listApplyUser = getApplyStep(financeApplyUser);
				getDateList();//得到日期集合
				//显示第一个流程
				//financeApplyUser.setShowMenu("nodePerson");
				bpCustMember=bpCustMemeberService.get(bpCustMember.getId());
				if(!"".equals(bpCustMember.getHomePhone())&&bpCustMember.getHomePhone()!=null&&!bpCustMember.getHomePhone().equals("-")){
					String[] strHomePhone=bpCustMember.getHomePhone().split("-");
					if(strHomePhone.length>1){
						bpCustMember.setHomePhonePrefix(strHomePhone[0]);
						bpCustMember.setHomePhoneSuffix(strHomePhone[1]);
					}
					
				}
			
				if(isMobile!=null&&"1".equals(isMobile)){
					 Map m = new IdentityHashMap();
					 m.put("nodePerson",null);
					 m.put("nodeFamily",null);
					 m.put("nodeWork", null);
					 m.put("nodeStore",null);
					 m.put("nodeOver", null);
					for(int i=0;i<listApplyUser.size();i++){
						String leixing =listApplyUser.get(i).getNodeEng();
						 if("nodePerson".equals(leixing)){
							 m.put("nodePerson",leixing);
						 }else if("nodeFamily".equals(leixing)){
							 m.put("nodeFamily",leixing);
						 }else if("nodeWork".equals(leixing)){
							 m.put("nodeWork",leixing);
						 }else if("nodeStore".equals(leixing)){
							 m.put("nodeStore",leixing);
						 }else if("nodeOver".equals(leixing)){
							 m.put("nodeOver",leixing);
						 }
					}
					JSONObject ojj =  JSONObject.fromObject(m);
					StringBuffer buff = new StringBuffer("{\"success\":true,\"struts\":")
					.append("201").append(",\"data\":");
					JSONSerializer json = JsonUtil.getJSONSerializer();
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
					buff.append(json.serialize(listApplyUser));
					buff.append(",\"bpcustmem\":");
					buff.append(json.serialize(bpCustMember));
					buff.append(",\"next\":");
					buff.append(ojj);
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				}
				 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.P2PPERSON).getTemplateFilePath());
			}else{
				 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
			 return "finance";		
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
				//appuser.setNodeChina(strChina[i]);
				appuser.setNodeEnable(nodeEnable[i]);
				listApplyUser.add(appuser);
			}
		}
		return listApplyUser;
	
	}
	/**
	 * 得到该用户申请的产品信息
	 * @return
	 */
	public String getLoanUser(){
		
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
			applyUser.setUserID(bpCustMember.getId());
			listApplyUser=financeApplyUserService.getApplyUser(applyUser);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYLOAN).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		
		return "finance";
	}
	public String getNextLevel(){
		// 将数据转成JSON格式
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		StringBuffer sb = new StringBuffer();
		String parentId = this.getRequest().getParameter("parentId");
		 List<CsDicAreaDynam> list = csDicAreaDynamService.listByParentId(Integer.valueOf(parentId));
		if(list!=null&&list.size()>0){
			sb.append("{\"success\":true,\"result\":");
			sb.append(gson.toJson(list));
			sb.append("}");
		}else{
			sb.append("{\"success\":false,\"result\":");
			sb.append(gson.toJson("null"));
			sb.append("}");
		}
		jsonString = sb.toString();
		return SUCCESS;
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
	 * 继续添写，跳转到相应的页面
	 * @return
	 */
	public String getNode(){
		try{
			long id = Long.parseLong(this.getRequest().getParameter("id"));
			bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//getList(bpCustMember);
			bpCustMember = bpCustMemeberService.get(bpCustMember.getId());
			
			BpFinanceApplyUser applyuser = new BpFinanceApplyUser();
			applyuser = financeApplyUserService.get(id);
			financeApplyUser = new BpFinanceApplyUser();
			BeanUtil.copyNotNullProperties(financeApplyUser,applyuser);
			if("青春贷".equals(financeApplyUser.getProductName())){//特例：青春贷变换产品id
				financeApplyUser.setProductId(16l);
			}
			getList(bpCustMember,financeApplyUser.getProductId());
			String nowNode = financeApplyUser.getCurrentnode();
			financeApplyUser.setShowMenu(nowNode);
			listApplyUser = new ArrayList<BpFinanceApplyUser>();
			//调用baseDao方法
			listApplyUser = getMenu(financeApplyUser);
			getDateList();//得到日期集合
			webFinanceApplylist = webFinanceApplyUploadService.getUploadState(financeApplyUser.getUserID());
			webFinanceApplyUploads = new WebFinanceApplyUploads();
			for(WebFinanceApplyUploads u:webFinanceApplylist){
				if(u.getStatus() == 1){
					if(u.getMaterialstype().equals("IDCard")){
						webFinanceApplyUploads.setRefIDCard(1);
					}
				}
			}
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
				//System.out.println("查询借款用户是否有已通过借款审核的信息----isPass="+isPass);
	
		}catch(Exception e){
			e.printStackTrace();
		}
		if("7".equals(this.getRequest().getParameter("state"))){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/finance/mobileAuthentication.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PERSON).getTemplateFilePath());
		}
		 return "finance";	
	}
	/**
	 * 终止审请
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void endApply() throws ServletException, IOException{
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		long id=Long.parseLong(this.getRequest().getParameter("id"));
		financeApplyUser=financeApplyUserService.get(id);
		financeApplyUser.setState("7");
		financeApplyUserService.save(financeApplyUser);
		BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
		applyUser.setUserID(bpCustMember.getId());
		listApplyUser=financeApplyUserService.getApplyUser(applyUser);
		this.getResponse().sendRedirect(this.getBasePath()+"/user/loanmanagementBpCustMember.do?toAction=loan");
	}
}
