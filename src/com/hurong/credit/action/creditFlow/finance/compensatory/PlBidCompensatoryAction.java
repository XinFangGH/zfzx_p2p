package com.hurong.credit.action.creditFlow.finance.compensatory;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.ContextUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatoryFlow;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryFlowService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
/**
 * 
 * @author 
 *
 */
public class PlBidCompensatoryAction extends BaseAction{
	@Resource
	private PlBidCompensatoryService plBidCompensatoryService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private PlBidCompensatoryFlowService plBidCompensatoryFlowService;
	private PlBidCompensatory plBidCompensatory;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlBidCompensatory getPlBidCompensatory() {
		return plBidCompensatory;
	}

	public void setPlBidCompensatory(PlBidCompensatory plBidCompensatory) {
		this.plBidCompensatory = plBidCompensatory;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PlBidCompensatory> list= plBidCompensatoryService.getAll(filter);
		
		Type type=new TypeToken<List<PlBidCompensatory>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 查询代偿列表
	 * @return
	 */
	public String getCompensatoryList(){
		String isMobile =this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_loanerp2pId_L_EQ", mem.getId());
		filter.addFilter("Q_backStatus_N_EQ", "0");
		filter.getPagingBean().setPageSize(100000000);
		//List<PlBidCompensatory> compensatory = plBidCompensatoryService.getCompensatoryList(mem.getId(),"");
		List<PlBidCompensatory> compensatoryList = plBidCompensatoryService.getAll(filter);
		for(PlBidCompensatory tory :compensatoryList){
			PlBidPlan plan = plBidPlanService.get(tory.getPlanId());
			if(plan!=null){
				tory.setBidName(plan.getBidProName());
			}
			if(tory.getCompensatoryMoney()==null){
				tory.setCompensatoryMoney(new BigDecimal(0));
			}
			if(tory.getPunishMoney()==null){
				tory.setPunishMoney(new BigDecimal(0));
			}
			if(tory.getBackPunishMoney()==null){
				tory.setBackPunishMoney(new BigDecimal(0));
			}
			if(tory.getBackCompensatoryMoney()==null){
				tory.setBackCompensatoryMoney(new BigDecimal(0));
			}
			tory.setSurplusMoney(tory.getCompensatoryMoney().add(tory.getPunishMoney())
					.subtract(tory.getBackPunishMoney())
					.subtract(tory.getBackCompensatoryMoney())
					.subtract(tory.getPlateMoney()));
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();;
		Type type=new TypeToken<List<ShowManageMoney>>(){}.getType();
		StringBuffer buff = new StringBuffer("{\"success\":\"true\",\"totalCounts\":").append(filter.getPagingBean().getTotalItems()).append(",\"result\":");
		buff.append(gson.toJson(compensatoryList, type));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	/**
	 * 查询代偿的具体信息
	 * @return
	 */
	public String getCompensatoryInfo(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		String cId = this.getRequest().getParameter("cId");
		PlBidCompensatory plBidCompensatory=plBidCompensatoryService.get(Long.valueOf(cId));
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_compensatoryId_L_EQ", Long.valueOf(cId));
		filter.addFilter("Q_backStatus_N_EQ", Integer.valueOf(2));
		//List<PlBidCompensatory> compensatory = plBidCompensatoryService.getCompensatoryList(mem.getId(),"");
		List<PlBidCompensatoryFlow> plist = plBidCompensatoryFlowService.getAll(filter);
		plBidCompensatory.setSurplusMoney(plBidCompensatory.getCompensatoryMoney().add(plBidCompensatory.getPunishMoney()).subtract(plBidCompensatory.getBackPunishMoney()).subtract(plBidCompensatory.getBackCompensatoryMoney()).subtract(plBidCompensatory.getPlateMoney()));
		plBidCompensatory.setAllMoney(plBidCompensatory.getCompensatoryMoney().add(plBidCompensatory.getPunishMoney()));
		plBidCompensatory.setBackAllMoney(plBidCompensatory.getBackCompensatoryMoney().add(plBidCompensatory.getBackPunishMoney()));
		plBidCompensatory.setUnBackCompensatoryMoney(plBidCompensatory.getCompensatoryMoney().subtract(plBidCompensatory.getBackCompensatoryMoney()));
		plBidCompensatory.setUnBackPunishMoney(plBidCompensatory.getPunishMoney().subtract(plBidCompensatory.getBackPunishMoney()));
		this.getRequest().setAttribute("plBidCompensatory", plBidCompensatory);
	    this.getRequest().setAttribute("plist", plist);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.OFFLINEREGIST).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 保存线下代偿记录
	 * @return
	 */
	public String saveCompensatoryInfo(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		String sbackPunishMoney = this.getRequest().getParameter("backPunishMoney");
		String compensatoryId = this.getRequest().getParameter("compensatoryId");
		String sflateMoney = this.getRequest().getParameter("flateMoney");
		String backDate = this.getRequest().getParameter("backDate");
		String sbackCompensatoryMoney = this.getRequest().getParameter("backCompensatoryMoney");
		PlBidCompensatory plBidCompensatory=plBidCompensatoryService.get(Long.valueOf(compensatoryId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date bdate=new Date();
		try {
			bdate = sdf.parse(backDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		BigDecimal backPunishMoney=new BigDecimal("0");
		BigDecimal flateMoney=new BigDecimal("0");
		BigDecimal backCompensatoryMoney=new BigDecimal("0");
		if(null!=sbackPunishMoney && !"".equals(sbackPunishMoney)){
			backPunishMoney=new BigDecimal(sbackPunishMoney);
		}
		if(null!=sflateMoney && !"".equals(sflateMoney)){
			flateMoney=new BigDecimal(sflateMoney);
		}
		if(null!=sbackCompensatoryMoney && !"".equals(sbackCompensatoryMoney)){
			backCompensatoryMoney=new BigDecimal(sbackCompensatoryMoney);
		}
		
		if(backPunishMoney.compareTo(new BigDecimal("0"))>0 || flateMoney.compareTo(new BigDecimal("0"))>0 || backCompensatoryMoney.compareTo(new BigDecimal("0"))>0 ){
			PlBidCompensatoryFlow flow = new PlBidCompensatoryFlow();
			flow.setCompensatoryId(Long.valueOf(compensatoryId));
			String cardNo = ContextUtil.createRuestNumber();//生成流水号
			flow.setRequestNo(cardNo);
			flow.setFlateMoney(flateMoney);//平账金额
			flow.setBackType("1");//偿还类型（0线上偿还，1线下登记回款） 
			flow.setBackStatus(2);
			flow.setBackPunishMoney(backPunishMoney);
			flow.setBackCompensatoryMoney(backCompensatoryMoney);
			flow.setBackDate(bdate);
			plBidCompensatoryFlowService.save(flow);
			
			//已偿还的罚息
			plBidCompensatory.setBackPunishMoney(plBidCompensatory.getBackPunishMoney().add(flow.getBackPunishMoney()));
			//已偿还的金额
			plBidCompensatory.setBackCompensatoryMoney(plBidCompensatory.getBackCompensatoryMoney().add(flow.getBackCompensatoryMoney()));
			//已平账金额
			plBidCompensatory.setPlateMoney(plBidCompensatory.getPlateMoney().add(flow.getFlateMoney()));
			//判断是否偿还完成
			BigDecimal sumMoney = plBidCompensatory.getPunishMoney().add(plBidCompensatory.getCompensatoryMoney());
			BigDecimal backMoney = plBidCompensatory.getBackPunishMoney().add(plBidCompensatory.getBackCompensatoryMoney()).add(plBidCompensatory.getPlateMoney());
			if(backMoney.compareTo(sumMoney)!=-1){
				plBidCompensatory.setBackStatus(2);
			}
			plBidCompensatory.setBackDate(new Date());
			plBidCompensatoryService.save(plBidCompensatory);
		}
		//当线下追偿全部登记完成更改标的状态为已完成
		QueryFilter filter = new QueryFilter();
		QueryFilter filter1 = new QueryFilter();
		filter.addFilter("Q_planId_L_EQ", plBidCompensatory.getPlanId());
		filter1.addFilter("Q_planId_L_EQ", plBidCompensatory.getPlanId());
		filter.addFilter("Q_backStatus_N_EQ", 2);
		List<PlBidCompensatory> list = plBidCompensatoryService.getAll(filter);
		List<PlBidCompensatory> list1 = plBidCompensatoryService.getAll(filter1);
		if(list!=null&&list1!=null&&list.size()==list1.size()){
			PlBidPlan plan  = plBidPlanService.get(plBidCompensatory.getPlanId());
			if(plan!=null && "3".equals(plan.getGuaranteeWay())){
				plan.setState(10);
				plBidPlanService.merge(plan);
			}
		}
		webMsgInstance("0", Constants.CODE_SUCCESS,"线下登记成功", "", "", "", "", "");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				plBidCompensatoryService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		PlBidCompensatory plBidCompensatory=plBidCompensatoryService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plBidCompensatory));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(plBidCompensatory.getId()==null){
			plBidCompensatoryService.save(plBidCompensatory);
		}else{
			PlBidCompensatory orgPlBidCompensatory=plBidCompensatoryService.get(plBidCompensatory.getId());
			try{
				BeanUtil.copyNotNullProperties(orgPlBidCompensatory, plBidCompensatory);
				plBidCompensatoryService.save(orgPlBidCompensatory);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
