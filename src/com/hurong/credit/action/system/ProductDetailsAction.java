package com.hurong.credit.action.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.p2p.BpFinanceApply;
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

@SuppressWarnings("serial")
public class ProductDetailsAction extends BaseAction{
	@Resource	
	private BpFinanceApplyUserService financeApplyUserService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private CsDicAreaDynamService csDicAreaDynamService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private GlobalTypeService globalTypeService;
	private String type;
	private BpFinanceApply financeApply;
	private BpFinanceApplyUser financeApplyUser;
	private  BpCustMember bpCustMember;
	private List<Dictionary> listloanProject;
	List<BpFinanceApplyUser> listApplyUser;
	private String productName;
	//得到config.properties读取的所有资源
	@SuppressWarnings("rawtypes")
	private static Map configMap = AppUtil.getConfigMap();
	private List<Dictionary> listCompanysize;

	public List<Dictionary> getListCompanysize() {
		return listCompanysize;
	}

	public void setListCompanysize(List<Dictionary> listCompanysize) {
		this.listCompanysize = listCompanysize;
	}
	
	
	public List<BpFinanceApplyUser> getListApplyUser() {
		return listApplyUser;
	}

	public void setListApplyUser(List<BpFinanceApplyUser> listApplyUser) {
		this.listApplyUser = listApplyUser;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Dictionary> getListloanProject() {
		return listloanProject;
	}

	public void setListloanProject(List<Dictionary> listloanProject) {
		this.listloanProject = listloanProject;
	}

	public BpFinanceApply getFinanceApply() {
		return financeApply;
	}

	public void setFinanceApply(BpFinanceApply financeApply) {
		this.financeApply = financeApply;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String first() throws Exception{
		financeApplyUser = new BpFinanceApplyUser();
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		bpCustMember = bpCustMemberService.get(bpCustMember.getId());
		BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
		applyUser.setUserID(bpCustMember.getId());
		applyUser.setState("0");
		List<BpFinanceApplyUser> list =financeApplyUserService.getFinanceApply(applyUser);
		//Long loadid = Long.parseLong("242"/*this.getRequest().getParameter("loadid")*/);
		if(list.size()>0){
			financeApplyUser = list.get(0);//financeApplyUserService.get(loadid);
			
		}else{
			financeApplyUser.setProductName(new String(this.getRequest().getParameter("productName").getBytes("iso-8859-1"),"utf-8"));
			financeApplyUser.setProductId(Long.valueOf(this.getRequest().getParameter("productId")));
			financeApplyUser.setState("0");
			financeApplyUser.setUserID(bpCustMember.getId());
			financeApplyUserService.save(financeApplyUser);
		}
		bpCustMember.setBambooJoint((short)1);
		bpCustMemberService.save(bpCustMember);
		 List<CsDicAreaDynam> listArea6591 = csDicAreaDynamService.listByParentId(6591);
		    this.getRequest().setAttribute("listArea6591", listArea6591);
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_all.ftl");
		return"freemarker";
	}
	
	public String saveApplyUser(){
		//得到session对象
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(financeApplyUser == null){
			Long loadid = 0l;
			BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
			applyUser.setUserID(bpCustMember.getId());
			listApplyUser = financeApplyUserService.getApplyUser(applyUser);
			for(BpFinanceApplyUser bfau:listApplyUser){
				if("0".equals(bfau.getState())){
					loadid=bfau.getLoanId();
					break;
				}
			}
			financeApplyUser = financeApplyUserService.get(loadid);
		}
		
		getList(bpCustMember,financeApplyUser.getProductId());
		WebFinanceApplyUploadsService webFinanceApplyUploadsService = (WebFinanceApplyUploadsService) AppUtil.getBean("webFinanceApplyUploadsService");
		//查询材料表
		//webFinanceApplylist = webFinanceApplyUploadsService.getUploadState(bpCustMember.getId());
		//System.out.println("数据size="+webFinanceApplylist.size());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = new Date();
		String tDate = format.format(dt);
		//向BpFinanceApplyUser表中添加数据
		if(bpCustMember!=null){
			financeApplyUser.setUserID(bpCustMember.getId());
			financeApplyUser.setCreateTime(tDate);
			/*listApplyUser = financeApplyUserService.getFinanceApply(financeApplyUser);
			if(listApplyUser.size() == 0){
				financeApplyUserService.save(financeApplyUser);
			}
			listApplyUser = new ArrayList<BpFinanceApplyUser>();
			//调用baseDao方法
			listApplyUser = getMenu(financeApplyUser);*/
			getDateList();//得到日期集合
			//System.out.println("listApplyUser流程size:"+listApplyUser.size());
			//显示第一个流程
			financeApplyUser.setShowMenu("nodePerson");
			financeApplyUserService.save(financeApplyUser);
			List<CsDicAreaDynam> listArea6591 = csDicAreaDynamService.listByParentId(6591);
			this.getRequest().setAttribute("listArea6591", listArea6591);
			bpCustMember = bpCustMemberService.get(bpCustMember.getId());
			if(!"".equals(bpCustMember.getHomePhone())&&bpCustMember.getHomePhone()!=null&&!bpCustMember.getHomePhone().equals("-")){
				String[] strHomePhone=bpCustMember.getHomePhone().split("-");
				if(strHomePhone.length>1){
					bpCustMember.setHomePhonePrefix(strHomePhone[0]);
					bpCustMember.setHomePhoneSuffix(strHomePhone[1]);
				}
				
			}
			bpCustMember.setBambooJoint((short)2);
			bpCustMemberService.save(bpCustMember);
			 this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_all.ftl");
		}else{
			 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			
		}
		 return "freemarker";		
}
	
	
	/**2014-07-17
	 * 已注册个人审请
	 * @return
	 */
	public String creditInfo(){
		//保存请求借款类型（注册用户）
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		long pNo = Long.parseLong(this.getRequest().getParameter("id"));
		if(bpCustMember == null){
			 this.getRequest().getSession().setAttribute("retUrl", "/financePurchase/showProductFinancePurchase.do?id="+pNo);
			 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			 return "finance";
		}else if("".equals(bpCustMember.getThirdPayFlagId())||bpCustMember.getThirdPayFlagId() == null){
			this.getRequest().getSession().setAttribute("retUrl", "user/safeBpCustMember.do?safe=all");
			webMsgInstance("0", Constants.CODE_FAILED, "未开通资金托管账户",  "", "", "", "", "");
			//getSession().setAttribute("retUrl","/financePurchase/getLoanUserapplyUser.do");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			return "message"; 
		}else{
			BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
			applyUser.setUserID(bpCustMember.getId());
			applyUser.setState("0");
			List<BpFinanceApplyUser> list1 = financeApplyUserService.getFinanceApply(applyUser);
			if(list1.size()>0){
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个未完成的产品、请在个人中心查看。。。",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				return "message";
			}
			applyUser.setState("1");
			applyUser.setState1("2");
			applyUser.setState2("4");
			//判断审请的产品是否通过审核
			List<BpFinanceApplyUser> list2 = financeApplyUserService.getFinanceApplyState(applyUser);
			if(list2.size()>0){
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个正在审核的产品、请在个人中心查看。。。",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				return "message";
			}else{
				//保存请求借款类型（注册用户）
				financeApply = new BpFinanceApply();
					String flownnode = "";
					if(pNo == 12){
						 //产品名称
						 productName = configMap.get("loanProduct1").toString();
						 //产品流程
						 flownnode = configMap.get("loanProduct1flow").toString();
					}else if(pNo == 13){
						 productName = configMap.get("loanProduct2").toString();
						 flownnode = configMap.get("loanProduct2flow").toString();
					}else if(pNo == 14){
						 productName=configMap.get("loanProduct3").toString();
						 flownnode = configMap.get("loanProduct3flow").toString();
					}else if(pNo == 15){
						 productName = configMap.get("loanProduct7").toString();
						 flownnode = configMap.get("loanProduct7flow").toString();
					}else if(pNo == 16){
						 productName = configMap.get("loanProduct8").toString();
						 flownnode = configMap.get("loanProduct8flow").toString();
					}
					//产品id
					financeApply.setProductId(pNo);
					//产品名称
					financeApply.setProductName(productName);
					//产品流程
					financeApply.setFlowNode(flownnode);
					String[] str = flownnode.split("\\|");
					String finshState = "";
					for(int i = 0;i<str.length;i++){
						finshState = "0"+"|"+finshState;
					}
					
					financeApply.setFinishState(finshState);
					//默认为第一个流程节点
					financeApply.setCurrnodeid(str[0]+"");
					QueryFilter filter = new QueryFilter(this.getRequest());
					filter.addFilter("Q_proTypeId_L_EQ", 1134+"");//借款用途列表
					filter.addFilter("Q_status_S_EQ", 0+"");
					listloanProject = dictionaryService.getAll(filter);
				}
			}
			bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_all.ftl");
		return "finance";
	}
	
	  
	
	
	
	/**
	 * 显示不同的节点页面
	 * @return
	 */
	public String getNodeMem(){
		
		try {
			//得到session对象
			BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			Long loadid=0l;
			try{
				//得到bp_finance_apply_user中的主键
				 loadid=Long.parseLong(this.getRequest().getParameter("loadid"));
			}catch(Exception e){
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "操作非法，重新填写数据！",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "message";				
			}
			///点击左侧菜单时，得到相应的菜单值
			String menuNode=this.getRequest().getParameter("menuNode");
			
			//得到产品对象信息
			financeApplyUser = financeApplyUserService.get(loadid);
			getList(cust,financeApplyUser.getProductId());
			//表单提交时!
			if(menuNode==null){
					bpCustMember.setTeacherPosition(bpCustMember.getTeacherPosition());
					bpCustMember.setHomePhone(bpCustMember.getHomePhonePrefix()+"-"+bpCustMember.getHomePhoneSuffix());
					BpCustMember member = bpCustMemberService.get(financeApplyUser.getUserID());
					//更新用户信息操作
					try {
						BeanUtil.copyNotNullProperties(member, bpCustMember);
						member.setBambooJoint((short)3);
						bpCustMemberService.save(member);
					} catch (Exception ex) {
						logger.error(ex.getMessage());
					}
					/*String newCurrenNodes="";//得到下一步的节点
					//拆分该流程节点
					String[] str = financeApplyUser.getFlownodes().split("\\|");
					//进行对比，判断，得到下一流程节点
					for(int i=0;i<str.length;i++){
						String oldNode=str[i].toString();
						//System.out.println("oldNode:"+oldNode);
						if(oldNode.equals(financeApplyUser.getCurrentnode()) && i<str.length-1){
							String[] finishState=financeApplyUser.getFinishStatus().split("\\|");
							newCurrenNodes=str[i+1];
							String fs="";
							for(int a=0;a<finishState.length;a++){
								if(i==a){
									finishState[a]=1+"";
								}
								fs=fs+finishState[a]+"|";
							}
							financeApplyUser.setFinishStatus(fs);
							financeApplyUserService.save(financeApplyUser);
							break;
						}else if(i==str.length-1){
							newCurrenNodes=str[str.length-1];
						}
					}
					
					financeApplyUser.setCurrentnode(newCurrenNodes);*/
					financeApplyUser.setState("1");
					financeApplyUserService.save(financeApplyUser);
				}else{
					
					//更新产品点节信息
					financeApplyUser.setCurrentnode(menuNode);
					//更新节点信息
					financeApplyUser.setState("1");
					financeApplyUserService.save(financeApplyUser);
			}
			//webFinanceApplylist = webFinanceApplyUploadService.getUploadState(financeApplyUser.getUserID());
			//得到最新的bpCustMember资料
			bpCustMember = bpCustMemberService.get(financeApplyUser.getUserID());
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
			//TODO 先申请园丁贷然后申请薪资贷这里覆盖    薪资贷中的”公司(单位)电话“
			if(!"".equals(bpCustMember.getTeacherCompanyphone())&&bpCustMember.getTeacherCompanyphone()!=null&&!bpCustMember.getTeacherCompanyphone().equals("-")){
				String[] strComPhone=bpCustMember.getTeacherCompanyphone().split("-");
				if(strComPhone.length>1){
					bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
					bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
				}
			}
			if(!"".equals(bpCustMember.getBossCompanyphone())&&bpCustMember.getBossCompanyphone()!=null&&!bpCustMember.getBossCompanyphone().equals("-")){
				String[] strComPhone=bpCustMember.getBossCompanyphone().split("-");
				if(strComPhone.length>1){
					bpCustMember.setHireCompanyphonePrefix(strComPhone[0]);
					bpCustMember.setHireCompanyphoneSuffix(strComPhone[1]);
				}
			}
			//通过产品id，得到左侧菜单节点列表
			financeApplyUser.setProductId(financeApplyUser.getProductId());
//			listApplyUser = getMenu(financeApplyUser);
			getDateList();//得到日期集合
			String isPass = financeApplyUserService.getPassThrough(cust.getId());//查询借款用户是否有已通过借款审核的信息
			this.getRequest().setAttribute("isPass", isPass);
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_all.ftl");
			return "freemarker";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "freemarker";
	}
	
	
	
	
	public String details() throws Exception {
		//获取当前登录的用户
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(financeApplyUser==null){
			financeApplyUser = new BpFinanceApplyUser();
		}
		if(financeApplyUser.getLoanId()==null && bpCustMember!=null){
		
			Date dt = new Date();
			String tDate = format.format(dt);
			financeApplyUser.setUserID(bpCustMember.getId());
			financeApplyUser.setCreateTime(tDate);
			financeApplyUser.setState("0");
		}
		//存储借款申请信息
		financeApplyUserService.save(financeApplyUser);
		
		//得到bp_finance_apply_user中的主键
		if(financeApplyUser.getLoanId()!= null){
		Long loadid = financeApplyUser.getLoanId();
		
		financeApplyUser = financeApplyUserService.get(loadid);
		}
		
		bpCustMember = bpCustMemberService.get(bpCustMember.getId());
		
		//手机申请状态码
		bpCustMember.setBambooJoint((short) 2);
		bpCustMemberService.save(bpCustMember);
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_2.ftl");
		return "freemarker";
	}
	public String login2bamboo(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
		applyUser.setUserID(bpCustMember.getId());
		applyUser.setState("0");
		List<BpFinanceApplyUser> list = financeApplyUserService.getFinanceApply(applyUser);
		financeApplyUser = list.get(0);
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_2.ftl");
		return"freemarker";
	}
	public String kinsfolk(){
		BpCustMember member = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
			member.setRelDirName(bpCustMember.getRelDirName());
			member.setRelDirType(bpCustMember.getRelDirType());
			member.setRelDirPhone(bpCustMember.getRelDirPhone());
			member.setRelOtherName(bpCustMember.getRelOtherName());
			member.setRelOtherType(bpCustMember.getRelOtherType());
			member.setRelOtherPhone(bpCustMember.getRelOtherPhone());
			member.setRelFriendName(bpCustMember.getRelFriendName());
			member.setRelFriendType(bpCustMember.getRelFriendType());
			member.setRelFriendPhone(bpCustMember.getRelFriendPhone());
			member.setBambooJoint((short)3);
		bpCustMemberService.save(member);
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileyeepay.ftl");
		return"freemarker";
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
		    	if(loadid==16){
		    		 listUnitp = dictionaryService.getByProTypeId2(list3.get(0).getProTypeId(),"1000");
		    	}else{
		    		 listUnitp = dictionaryService.getByProTypeId(list3.get(0).getProTypeId());
		    	}
			   
			   this.getRequest().setAttribute("listUnitp", listUnitp);//公司类别
		    }
		    
		    List<GlobalType> list4=globalTypeService.getByNodeKey("zhiwujob");
		    if(null!=list4 && list4.size()>0){
		    	List<Dictionary> listJob;
		    	if(loadid==16){
		    		listJob=dictionaryService.getByProTypeId2(list4.get(0).getProTypeId(),"1000");
		    	}else{
		    		listJob=dictionaryService.getByProTypeId(list4.get(0).getProTypeId());
		    	}
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
		    if(loadid==16){
		    	filter.addFilter("Q_status_S_EQ", 1+"000");
		    }else{
		    	filter.addFilter("Q_status_S_EQ", 0+"");
		    }
			
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
}