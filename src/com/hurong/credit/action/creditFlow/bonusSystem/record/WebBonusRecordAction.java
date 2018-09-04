package com.hurong.credit.action.creditFlow.bonusSystem.record;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.bonusSystem.record.WebBonusRecordService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.p2p.loan.P2pLoanBasisMaterialService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class WebBonusRecordAction extends BaseAction{
	@Resource
	private WebBonusRecordService webBonusRecordService;
	private WebBonusRecord webBonusRecord;
	private List<P2pLoanBasisMaterial> basisMaterialList;
	@Resource
	private P2pLoanBasisMaterialService p2pLoanBasisMaterialService;
	@Resource
	private  WebFinanceApplyUploadsService webFinanceApplyUploadService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	public List<P2pLoanBasisMaterial> getBasisMaterialList() {
		return basisMaterialList;
	}

	public void setBasisMaterialList(List<P2pLoanBasisMaterial> basisMaterialList) {
		this.basisMaterialList = basisMaterialList;
	}

	private Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public WebBonusRecord getWebBonusRecord() {
		return webBonusRecord;
	}

	public void setWebBonusRecord(WebBonusRecord webBonusRecord) {
		this.webBonusRecord = webBonusRecord;
	}

	
/*	public String loadTempate(){
		if(isLogin()){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}*/
	
	/**
	 * 查询我的积分列表
	 */
	public String loadMyIntegral(){
		if(isLogin()){
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			 String buffer = webBonusRecordService.getMyIntegral(getRequest(), mem);
     		 jsonString=buffer;
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		return SUCCESS;
	}
	
	/**
	 * 查询我的积分的查询数据
	 * @return
	 */
	public String loadMyIntegralData(){
		if(isLogin()){
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			 String buffer = webBonusRecordService.queryMyIntegral(mem);
     		 jsonString=buffer;
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		return SUCCESS;
	}
	
	/**
	 * 显示列表
	 */
	public String list(){
		BpCustMember bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(pager==null){
			pager = new Pager();
		}
		//累计总积分
		int totalRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "1");
		//已使用积分
		int useRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "2");
		String sel = this.getRequest().getParameter("sel");
		QueryFilter filter=queryFilter(bpCustMember,sel);
		filter.getPagingBean().setPageSize(5);
		List<WebBonusRecord> list= webBonusRecordService.getAll(filter);
		for(WebBonusRecord record:list){
			record.setTotalRecordNumber(Long.valueOf(totalRecordNumber));
			record.setUseRecordNumber(Long.valueOf(useRecordNumber));
			//可用积分
			record.setUsableRecordNumber(Long.valueOf(totalRecordNumber-useRecordNumber));
			break;
		}
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		// 将数据转成JSON格式
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		StringBuffer sb = new StringBuffer();
		String liststr = gson.toJson(list);
		sb.append("{\"success\":true,\"result\":");
		 sb.append(gson.toJson(liststr));
			sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 更多列表显示
	 * @return
	 */
	public String pageList(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (pager == null) {
			pager = new Pager();
		}
		//累计总积分
		int totalRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "1");
		//已使用积分
		int useRecordNumber=webBonusRecordService.findRecordNumber(bpCustMember.getId(), "2");
		String sel = this.getRequest().getParameter("sel");
		QueryFilter filter=queryFilter(bpCustMember,sel);
		filter.addFilter("Q_customerId_S_EQ", bpCustMember.getId());
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		List<WebBonusRecord> list= webBonusRecordService.getAll(filter);
		for(WebBonusRecord record:list){
			record.setTotalRecordNumber(Long.valueOf(totalRecordNumber));
			record.setUseRecordNumber(Long.valueOf(useRecordNumber));
			//可用积分
			record.setUsableRecordNumber(Long.valueOf(totalRecordNumber-useRecordNumber));
			break;
		}
		
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		String isMobile =this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
 			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":");
 			JSONSerializer json = JsonUtil.getJSONSerializer();
			 buff.append(pager.getTotalCount()).append(",\"result\":");
		     buff.append(json.serialize(pager.getList()));
			 buff.append("}");
		     jsonString = buff.toString();
		     return SUCCESS;
 		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BONUSRECORDLIST).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 条件查询
	 */
	public QueryFilter queryFilter(BpCustMember bpCustMember,String sel){
		
		QueryFilter filter=new QueryFilter(getRequest());
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(d);
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(sel!=null&&!sel.equals("")){
			if(sel.equals("1")){
				//一个月之内的记录
				Calendar cl = Calendar.getInstance();
			    cl.setTime(date);
			    cl.add(Calendar.DATE,-30);
			    String temp = "";
			    temp = sdf.format(cl.getTime());
			    System.out.println(temp);
			    filter.addFilter("Q_createTime_D_GE", temp);
				filter.addFilter("Q_createTime_D_LT", sdf.format(date));
				
			}else if(sel.equals("3")){
				//三个月之内的记录
				Calendar cl = Calendar.getInstance();
			    cl.setTime(date);
			    cl.add(Calendar.DATE,-90);
			    String temp = "";
			    temp = sdf.format(cl.getTime());
			    System.out.println(temp);
			    filter.addFilter("Q_createTime_D_GE", temp);
				filter.addFilter("Q_createTime_D_LT", sdf.format(date));
			}else if(sel.equals("33")){
				
			}
		}else{
			//一个月之内的记录
			Calendar cl = Calendar.getInstance();
		    cl.setTime(date);
		    cl.add(Calendar.DATE,-30);
		    String temp = "";
		    temp = sdf.format(cl.getTime());
		    System.out.println(temp);
		    filter.addFilter("Q_createTime_D_GE", temp);
			filter.addFilter("Q_createTime_D_LT", sdf.format(date));
		}
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		filter.addFilter("Q_customerId_S_EQ", bpCustMember.getId().toString());
		filter.addSorted("createTime", "desc");
		return filter;
		
	}

	//判断用户是否登录
	public boolean isLogin(){
		Boolean boo = false;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			boo = true;
		}
		return boo;
	}
	
	//获取当前用户的余额
	public void getBalance(){
		BpCustMember member = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		commoon(member);
		bpCustMember = member;
	}

	private void commoon(BpCustMember mem){
		bpCustMember = bpCustMemberService.get(mem.getId());
		try {
			BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(),ObSystemAccount.type0.toString());
			if(ret!=null){
				bpCustMember.setTotalMoney(ret[1]);
				bpCustMember.setFreezeMoney(ret[2]);
				bpCustMember.setAvailableInvestMoney(ret[3]);
				bpCustMember.setTotalInvestMoney(ret[4]);
				bpCustMember.setAllInterest(ret[5]);
				bpCustMember.setPrincipalRepayment(ret[6]);
				bpCustMember.setTotalRecharge(ret[7]);
				bpCustMember.setTotalEnchashment(ret[8]);
				
				bpCustMember.setTotalLoanMoney(ret[9]);
				bpCustMember.setTotalPrincipalRepaymentMoney(ret[10]);
				bpCustMember.setTotalNotPrincipalRepaymentMoney(ret[11]);
				
			}
			//bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		int percent = 0;
		if(bpCustMember.getIsCheckEmail()!=null&&"1".equals(bpCustMember.getIsCheckEmail())){
			//判断邮箱是否验证
			percent += 30;
		}
		if(bpCustMember.getIsCheckPhone()!=null&&"1".equals(bpCustMember.getIsCheckPhone())){
			//判断手机是否验证
			percent += 30;
		}
		if(bpCustMember.getIsCheckCard()!=null&&"1".equals(bpCustMember.getIsCheckCard())){
			//判断是否实名认证
			percent += 40;
		}
		/*if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
			//判断手机是否验证
			percent += 30;
		}*/
		//保存信誉等级
		this.getSession().setAttribute("safetyLevel", percent);
		//查询基础材料
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_operationType_S_EQ", "person");//默认查询出个人的
		basisMaterialList = p2pLoanBasisMaterialService.getAll(filter);
		//判断材料是否上传
		for(P2pLoanBasisMaterial material: basisMaterialList){
			QueryFilter filter2=new QueryFilter(getRequest());
			filter2.addFilter("Q_materialId_L_EQ", material.getMaterialId());
			filter2.addFilter("Q_userID_L_EQ", bpCustMember.getId());
			List<WebFinanceApplyUploads> applys = webFinanceApplyUploadService.getAll(filter2);
			if(applys.size()>0){
				material.setWebFinanceApplyUploadsList(applys);
				material.setImgUrl(applys.get(0).getFiles());
				material.setStatus(applys.get(0).getStatus());
				material.setRejectReason(applys.get(0).getRejectReason());
			}		
		}
	}

}
