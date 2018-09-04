package com.hurong.credit.action.financingAgency.manageMoney;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlEarlyRedemptionService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderChildrenOrService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author
 * 
 */
public class PlManageMoneyPlanBuyinfoAction extends BaseAction {
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	@Resource
	private PlManageMoneyPlanService plManageMoneyPlanService;
	@Resource
	private PlManageMoneyTypeService plManageMoneyTypeService;
	@Resource
	private PlMmOrderChildrenOrService plMmOrderChildrenOrService;
	@Resource
	private PlEarlyRedemptionService plEarlyRedemptionService;
	@Resource
	private PlMmOrderAssignInterestService plMmOrderAssignInterestService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private SendMesService sendMesService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;
	// 得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	private PlBidAuto bidAuto;
	private List<PlManageMoneyType> listMoneyType;// U计划类别列表
	private List<PlManageMoneyType> plManageMoneyType;// 理财计划类别列表
	private PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo;
	private List<PlMmOrderChildrenOr> newClaimsList;// 债权列表

	protected Pager upager; // U计划pager
	
	public static Calendar calendar = Calendar.getInstance();

	private Long id;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 加载模板文件(我的理财)
	 * @return
	 */
	public String managelist1() {
		this.getSession().setAttribute("highlight", 4);
		String tab = this.getRequest().getParameter("tab");
		if(tab!=null&&!"".equals(tab)){
			this.getRequest().setAttribute("tab", tab);
		}
		String loginType =(String) this.getSession().getAttribute("loginType");
    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
    	if (mem != null) {
			List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
			this.getRequest().setAttribute("fileList", fileList);
    		if(loginType.equals("enterprise")){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MMPLAN_MANAGE_LIST_ENTERPRISE).getTemplateFilePath());
			}else if (loginType.equals("person")){
				if(type!=null&&!"".equals(type)){
					this.getRequest().setAttribute("type", "1");
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.mmplan_manage_list).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
    	}else{
    		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
    	}
    	return "freemarker";
	}
	
	/**
	 * 获取我的理财的相关数据
	 * 
	 */
	public String myFinance(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			BpPersonCenterData data =  plBidInfoService.queryAllManage(mem.getId());
            StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
     		JSONSerializer json = JsonUtil.getJSONSerializer();
     		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
     		buff1.append(json.serialize(data));
     		buff1.append("}");
     		System.out.println(buff1.toString());
     		jsonString=buff1.toString();
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		return 	SUCCESS;
	}
	
	
	
	
	/**
	 * 查询我的理财的列表
	 * @return
	 */
	public String manageListShow(){
		this.getSession().setAttribute("highlight", 4);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(isLogin()){
			//理财计划列表
            String buffer1 = plManageMoneyPlanBuyinfoService.getList(mem.getId(),getRequest());
     		jsonString=buffer1;
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		return SUCCESS;
	}
	
	/**
	 * 获取理财计划的类型
	 * @return
	 */
	public String getMyFinanceType(){
		List<PlManageMoneyType> plManageMoneyType = plManageMoneyTypeService.getAll();
		StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(plManageMoneyType.size()).append(",\"result\":");
  		JSONSerializer json = JsonUtil.getJSONSerializer();
  		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
  		buff1.append(json.serialize(plManageMoneyType));
  		buff1.append("}");
  		System.out.println(buff1.toString());
  		jsonString=buff1.toString();
		return SUCCESS;
	}
	/**
	 * 显示列表
	 */
	public String managelist() {
		try{
			this.getSession().setAttribute("highlight", 4);
	    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if (mem != null) {
				commoon(mem);
				BpCustMember temp= bpCustMemberService.get(mem.getId());
				bpCustMember.setScore(temp.getScore());
				String show = this.getRequest().getParameter("show");
				if(show==null||show.equals("")){
					show="buyingList";
					this.getRequest().setAttribute("show", "buyingList");
				}else{
					this.getRequest().setAttribute("show", show);
				}
				String selectValue = this.getRequest().getParameter("selectValue");
				this.getRequest().setAttribute("selectValue", selectValue);
				String startTime = this.getRequest().getParameter("startTime");
				String endTime = this.getRequest().getParameter("endTime");
				//查询理财计划类型
				List<PlManageMoneyType> plManageMoneyType = plManageMoneyTypeService.getAll();
				this.getRequest().setAttribute("plManageMoneyType", plManageMoneyType);
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_investPersonId_L_EQ", mem.getId());
				filter.addFilterIn("Q_keystr_S_IN",Arrays.asList("mmplan","UPlan"));
				filter.addSorted("keystr","asc");
				filter.addSorted("buyDatetime","DESC");
				if(startTime!=null&&!startTime.equals("")){
					filter.addFilter("Q_buyDatetime_D_GE", startTime);
					this.getRequest().setAttribute("startTime_"+show, startTime);
				}
				if(endTime!=null&&!endTime.equals("")){
					filter.addFilter("Q_buyDatetime_D_LE", endTime);
					this.getRequest().setAttribute("endTime_"+show, endTime);
				}
				if(pager == null) {
				    pager = new Pager();
				    pager.setPageNumber(pager==null?1:pager.getPageNumber());
				    pager.setPageSize(10);
			 	}
		 		filter.getPagingBean().setStart((pager.getPageNumber() - 1) * pager.getPageSize());
		 		filter.getPagingBean().setPageSize(pager.getPageSize());
				 		 
		 		String state1=this.getRequest().getParameter("state1");//购买中
		 		String state2=this.getRequest().getParameter("state2");//持有中
		 		String state7=this.getRequest().getParameter("state7");//持有中
		 		String state10=this.getRequest().getParameter("state10");//已完成
		 		String state8=this.getRequest().getParameter("state8");//已退出
		 		String state_2=this.getRequest().getParameter("state_2");//已失败
		 		String state3=this.getRequest().getParameter("state3");//已失败
				 		
		 		if(null !=state1 && !"".equals(state1)){//购买中
		 			filter.addFilterIn("Q_state_SN_IN",Arrays.asList(Short.valueOf("1")));
		 		}
		 		if(null !=state2 && !"".equals(state2) && null !=state7 && !"".equals(state7)){//持有中
		 			filter.addFilterIn("Q_state_SN_IN",Arrays.asList(Short.valueOf("2"),Short.valueOf("7")));
		 		}
		 		if(null !=state10 && !"".equals(state10)){//已完成
		 			filter.addFilterIn("Q_state_SN_IN",Arrays.asList(Short.valueOf("10")));
		 		}
		 		if(null !=state8 && !"".equals(state8)){//已退出
		 			filter.addFilterIn("Q_state_SN_IN",Arrays.asList(Short.valueOf("8")));
		 		}
		 		if(null !=state_2 && !"".equals(state_2) && null !=state3 && !"".equals(state3)){//已失败
		 			filter.addFilterIn("Q_state_SN_IN",Arrays.asList(Short.valueOf("-2"),Short.valueOf("3")));
		 		}
				List<PlManageMoneyPlanBuyinfo> list = plManageMoneyPlanBuyinfoService.getAll(filter);
				List<PlManageMoneyPlanBuyinfo> buyInfolist = new ArrayList<PlManageMoneyPlanBuyinfo>();
				for(PlManageMoneyPlanBuyinfo l: list){
					plManageMoneyPlanBuyinfoService.evict(l);
			    	BigDecimal getedMoney=	plMmOrderAssignInterestService.getGetMoney(l.getOrderId());
					l.setCurrentGetedMoney(getedMoney);
					if(selectValue!=null&&!selectValue.equals("")){
						if(l.getPlManageMoneyPlan().getManageMoneyTypeId().toString().equals(selectValue)){
							buyInfolist.add(l);
						}
						this.getRequest().setAttribute("selectValue_"+show, selectValue);
					}
					if(null !=state2 && !"".equals(state2) && null !=state7 && !"".equals(state7)){//持有中
						//计算锁定期到期日期
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						calendar.setTime(l.getStartinInterestTime());
						calendar.add(Calendar.MONTH,l.getPlManageMoneyPlan().getLockingLimit());
						l.setLockingEndDate(sdf.format(calendar.getTime()));
					}
				}
				if(selectValue!=null&&!selectValue.equals("")){
					pager.setList(buyInfolist);
				}else{
					pager.setList(list);
				}
		 		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		 		String isMobile =this.getRequest().getParameter("isMobile");
		 		if(null!=isMobile&&isMobile.endsWith("1")){
		 			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":");
		 			JSONSerializer json = JsonUtil.getJSONSerializer();
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"startinInterestTime","buyEndTime"});
					 buff.append(pager.getTotalCount()).append(",\"result\":");
				     buff.append(json.serialize(pager.getList()));
					 buff.append("}");
				     jsonString = buff.toString();
				     return SUCCESS;
		 		}
		 		//取出登录类型，个人还是企业
				String loginType =(String) this.getSession().getAttribute("loginType");
				if(loginType.equals("enterprise")){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MMPLAN_MANAGE_LIST_ENTERPRISE).getTemplateFilePath());
				}else if (loginType.equals("person")){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.mmplan_manage_list).getTemplateFilePath());
				}else{
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				}
			}else {
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "freemarker";
	}

	
	
	
	
	
	
	
	/**
	 * 查询理财计划对应的债权清单列表
	 * 
	 * @return
	 */
	public String getClaimsList() {
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if (null != mem) {
				commoon(mem);
				String orderId = this.getRequest().getParameter("orderId");
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_orderId_L_EQ", orderId);
				List<PlManageMoneyPlanBuyinfo> list = plManageMoneyPlanBuyinfoService.getAll(filter);
				if (null != list && list.size() > 0) {
					plManageMoneyPlanBuyinfo = list.get(0);
					if (plManageMoneyPlanBuyinfo.getInvestType()!=null&&plManageMoneyPlanBuyinfo.getInvestType().equals(Integer.valueOf(1))) {
						plManageMoneyPlanBuyinfo.setInvestTypeName("收益再投资");
					} else {
						plManageMoneyPlanBuyinfo.setInvestTypeName("提取主账户");
					}
				}
				newClaimsList = plManageMoneyPlanBuyinfoService.getClaimsList(orderId);
				this.getRequest().setAttribute("orderId", orderId);
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CLAIMS).getTemplateFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "freemarker";
	}

	private void commoon(BpCustMember mem) {
		bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
		try {
			bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
			bpCustMember = bpCustMemberService.get(mem.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int percent = 0;
		if ("1".equals(bpCustMember.getIsCheckEmail())&& bpCustMember.getIsCheckEmail() != null) {
			// 判断邮箱是否验证
			percent += 10;
		}
		if ("1".equals(bpCustMember.getIsCheckPhone())&& bpCustMember.getIsCheckPhone() != null) {
			// 判断手机是否验证
			percent += 20;
		}
		if ("1".equals(bpCustMember.getIsCheckPhone())&& bpCustMember.getIsCheckPhone() != null) {
			// 判断是否实名认证
			percent += 40;
		}
		if (!"".equals(bpCustMember.getThirdPayFlagId())&& bpCustMember.getThirdPayFlagId() != null) {
			// 判断手机是否验证
			percent += 30;
		}
		// 保存信誉等级
		this.getRequest().setAttribute("percent", percent);
		// 保存客户的信誉信息
		this.getRequest().setAttribute("bpCustMember", bpCustMember);
	}

	public String orMacthingDetaillist() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			String orderId = this.getRequest().getParameter("orderId");
			Map<String, String> map = new HashMap<String, String>();
			map.put("orderId", orderId);
			List<PlMmOrderChildrenOr> list = plMmOrderChildrenOrService.listbysearch(null, map);
			this.getRequest().setAttribute("orMacthingDetaillist", list);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.orMacthing_Detail_list).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	public String assigninterestlist() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			Long orderId = Long.valueOf(this.getRequest().getParameter("orderId"));
			QueryFilter filter = new QueryFilter(getRequest());
			filter.getPagingBean().setPageSize(1000000);
			filter.addFilter("Q_orderId_L_EQ", orderId);
			filter.addFilter("Q_isValid_SN_EQ", 0);
			filter.addFilter("Q_isCheck_SN_EQ", 0);
			List<PlMmOrderAssignInterest> list = plMmOrderAssignInterestService.getAll(filter);
			
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
//				for(int i=0;i<list.size();i++){
//					BigDecimal  money1=fundIntentpay.get(i).getPayIntestPrinMoney();
//					BigDecimal  money2=fundIntentpay.get(i).getCouponMoney();
//					BigDecimal  money3=fundIntentpay.get(i).getCompensationMoney();
//					fundIntentpay.get(i).setCompensationMoney(money1.add(money2).add(money3));
//				}
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(list.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
				buff.append(json.serialize(list));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
			this.getRequest().setAttribute("assigninterestlist", list);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.assigninterest_list).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	public String earlyOutDetail() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			String orderId = this.getRequest().getParameter("orderId");
//			Map<String, String> map = new HashMap<String, String>();
			this.getRequest().setAttribute("orderId", orderId);
			PlManageMoneyPlanBuyinfo buyinfo = plManageMoneyPlanBuyinfoService.get(Long.valueOf(orderId));
			PlManageMoneyPlan plan=buyinfo.getPlManageMoneyPlan();
			if(null!=plan){
				buyinfo.setUearlierOutRate(plan.getEarlierOutRate());
			}
			
			//应收利息=贷款本金*（提前还款日期-起息日）*日化利率
			long day = (new Date().getTime()-buyinfo.getStartinInterestTime().getTime())/(24*60*60*1000);
			BigDecimal sumLoanInterestMoney=buyinfo.getBuyMoney().multiply(buyinfo.getPromisDayRate()).multiply(new BigDecimal(day)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
			//已收利息
			BigDecimal InterestMoney=new BigDecimal("0");
			QueryFilter filter = new QueryFilter();
			filter.getPagingBean().setPageSize(10000000);
			filter.addFilter("Q_orderId_L_EQ", orderId);
			filter.addFilter("Q_fundType_S_EQ", "loanInterest");
			filter.addFilter("Q_isValid_SN_EQ", 0);
			filter.addFilter("Q_isCheck_SN_EQ", 0);
			List<PlMmOrderAssignInterest> listInterest=plMmOrderAssignInterestService.getAll(filter);
			if(null !=listInterest && listInterest.size()>0){
				for(PlMmOrderAssignInterest plAssignInterest : listInterest){
					if(plAssignInterest.getFactDate() !=null){
						InterestMoney=InterestMoney.add(plAssignInterest.getAfterMoney());
					}
				}
			}
			//欠派利息=应收利息-已收利息
			BigDecimal loanInterestMoney=sumLoanInterestMoney.subtract(InterestMoney);
			
			//提前支取违约金
			BigDecimal liquidatedDamagesMoney=buyinfo.getBuyMoney().multiply(plan.getEarlierOutRate()).divide(new BigDecimal(100), 2,BigDecimal.ROUND_HALF_UP);
			
			//结算金额
			BigDecimal sumMoney=buyinfo.getBuyMoney().add(loanInterestMoney).subtract(liquidatedDamagesMoney);
			
			this.getRequest().setAttribute("loanInterestMoney",loanInterestMoney);
			this.getRequest().setAttribute("liquidatedDamagesMoney", liquidatedDamagesMoney);
			this.getRequest().setAttribute("sumMoney", sumMoney);
			this.getRequest().setAttribute("now",sd.format(new Date()));
			this.getRequest().setAttribute("buyinfo",buyinfo);
			this.getRequest().setAttribute("telphone",mem.getTelphone());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.early_out_detail).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	public String submitEarlyOutApply() {
		//System.out.println("----->>>>>");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (null != mem) {
			String orderId = this.getRequest().getParameter("orderId");
//			String earlierOutDate = this.getRequest().getParameter("earlierOutDate");
			PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo = plManageMoneyPlanBuyinfoService.get(Long.valueOf(orderId));
			if (plManageMoneyPlanBuyinfo.getState().equals(Short.valueOf("7"))) {
				managelist();
			} else {
				PlEarlyRedemption plEarlyRedemption = new PlEarlyRedemption();
				plEarlyRedemption.setOrderId(Long.valueOf(orderId));
				plEarlyRedemption.setCheckStatus(Short.valueOf("0"));
				plEarlyRedemption.setCreateDate(new Date());
				plEarlyRedemption.setCreator(mem.getLoginname());
				plEarlyRedemption.setKeystr(plManageMoneyPlanBuyinfo.getKeystr());
				plEarlyRedemption.setLiquidatedDamagesRate(plManageMoneyPlanBuyinfo.getPlManageMoneyPlan().getEarlierOutRate());
				plEarlyRedemption.setEarlyDate(new Date());
				plEarlyRedemption.setLoanerRepayMentStatus(0);//默认值，没有特殊含义
				/*
				 * SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
				 * try {
				 * plEarlyRedemption.setEarlyDate(from.parse(earlierOutDate)); }
				 * catch (ParseException e) { e.printStackTrace(); }
				 */
				plEarlyRedemption.setResidualPrincipal(plManageMoneyPlanBuyinfo.getBuyMoney());
				plEarlyRedemption.setEarlyMoney(plManageMoneyPlanBuyinfo.getBuyMoney());
				plEarlyRedemptionService.save(plEarlyRedemption);

				plManageMoneyPlanBuyinfo.setState(Short.valueOf("7"));
				plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
				managelist();
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.mmplan_manage_list).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
	/**
	 * 显示列表(个人理财顾问)
	 */
	public String manageFinanciallist() {
		try{
			this.getSession().setAttribute("highlight", 4);
	    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if (mem != null) {
				commoon(mem);
				String show = this.getRequest().getParameter("show");
				if(show==null||show.equals("")){
					show="returnList";
					this.getRequest().setAttribute("show", "returnList");
				}else{
					this.getRequest().setAttribute("show", show);
				}
				String mmName = this.getRequest().getParameter("mmName");
				if(mmName!=null&&!mmName.equals("")){
					this.getRequest().setAttribute("mmName_"+show, mmName);
				}
				if(pager == null) {
				    pager = new Pager();
				    pager.setPageNumber(pager==null?1:pager.getPageNumber());
				    pager.setPageSize(10);
			 	}
				if(null!=show && "earlyList".equals(show)){
					List<PlManageMoneyPlanBuyinfo> list=plManageMoneyPlanBuyinfoService.findEarlyList(mem.getLoginname(), this.getRequest(), pager);
					Long count=plManageMoneyPlanBuyinfoService.findEarlyCount(mem.getLoginname(), this.getRequest());
					pager.setList(list);
			 		pager.setTotalCount(Integer.valueOf(count.toString()));
			 		String checkStatus = getRequest().getParameter("checkStatus");
			 		if(checkStatus!=null&&!checkStatus.equals("")){
						this.getRequest().setAttribute("checkStatus1", checkStatus);
					}
				}else{
					List<ShowManageMoney> list = plMmOrderAssignInterestService.findFinancialList(mem.getLoginname(), this.getRequest(), pager);
		            Long count=plMmOrderAssignInterestService.findFinancialCount(mem.getLoginname(), this.getRequest());
					pager.setList(list);
			 		pager.setTotalCount(Integer.valueOf(count.toString()));
				}
		 		//取出登录类型，个人还是企业
				String loginType =(String) this.getSession().getAttribute("loginType");
			     if (loginType.equals("person")){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MMPLAN_FINANCIALMANAGE_LIST).getTemplateFilePath());
				}else{
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				}
			}else {
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "freemarker";
	}
	/**
	 * 个人理财顾问派息信息
	 * 
	 */
	public String financialMoney(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		try {
			String mmplanId = this.getRequest().getParameter("mmplanId");
			String intentDate = this.getRequest().getParameter("intentDate");
		//	System.out.println("mmplanId--->"+mmplanId);
		//	System.out.println("intentDate--->"+intentDate);
			ShowManageMoney  showManageMoney=null;
			if(null!=mmplanId && ""!=mmplanId && null!=intentDate && ""!=intentDate){
				showManageMoney=plMmOrderAssignInterestService.findByIdDate(Long.valueOf(mmplanId), intentDate);
				this.getSession().setAttribute("showManageMoney", showManageMoney);
			}
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.FINANCIALMONEY).getTemplateFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "freemarker";
	}
	/**
	 * 提前赎回申请
	 * @return
	 */
	public String gcalculateEarlyOut() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
		String earlyRedemptionId = this.getRequest().getParameter("earlyRedemptionId");
        if(null!=earlyRedemptionId && !"".equals(earlyRedemptionId)){
			PlEarlyRedemption	plEarlyRedemption=plEarlyRedemptionService.get(Long.valueOf(earlyRedemptionId));
			List<PlMmOrderAssignInterest> pailist=plMmOrderAssignInterestService.mmplancreateList(plEarlyRedemption);
		    //转账金额
			//提前支取违约金
			BigDecimal liquidatedDamagesMoney=new BigDecimal("0");
			//欠派利息
			BigDecimal loanInterestMoney=new BigDecimal("0");
			//本金
			BigDecimal principalRepaymenMoney=new BigDecimal("0");
			for(PlMmOrderAssignInterest pi:pailist){
				if(pi.getFundType().equals("liquidatedDamages")){
					liquidatedDamagesMoney=liquidatedDamagesMoney.subtract(pi.getPayMoney());
				}else if(pi.getFundType().equals("loanInterest")){ 
					loanInterestMoney=loanInterestMoney.add(pi.getIncomeMoney());
				}else if(pi.getFundType().equals("principalRepayment")){ 
					principalRepaymenMoney=principalRepaymenMoney.add(pi.getIncomeMoney());
				}
			}
			//结算金额
			BigDecimal sumMoney=loanInterestMoney.add(liquidatedDamagesMoney).add(principalRepaymenMoney);
			this.getRequest().setAttribute("principalRepaymenMoney", principalRepaymenMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("loanInterestMoney", loanInterestMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("liquidatedDamagesMoney", liquidatedDamagesMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("sumMoney", sumMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("investPersonName",plEarlyRedemption.getCreator() );
			this.getRequest().setAttribute("earlyRedemptionId",plEarlyRedemption.getEarlyRedemptionId() );
			
			//发送短信。邮件。站内信
			PlManageMoneyPlanBuyinfo order=plManageMoneyPlanBuyinfoService.get(plEarlyRedemption.getOrderId());
			PlManageMoneyPlan plan = new PlManageMoneyPlan();
			Map<String, String> mapMessage1 = new HashMap<String, String>();
			mapMessage1.put("key", "sms_redeem");
			mapMessage1.put("userId",mem.getId().toString());
			mapMessage1.put("${name}", mem.getLoginname());
			if(order.getMmplanId()!=null){
				plan = plManageMoneyPlanService.get(order.getMmplanId());
				mapMessage1.put("${projNumber}", plan.getMmName());
				mapMessage1.put("${projName}", plan.getMmNumber());
			}
			String result1 =  sendMesService.sendSmsEmailMessage(mapMessage1);
			
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EARLYOUT).getTemplateFilePath());
        }
        return "freemarker";
	}
	
	/**
	 * 驳回提前赎回
	 * @return
	 */
	public String rejectedByFinancial(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		String earlyRedemptionId = this.getRequest().getParameter("earlyRedemptionId");
		PlEarlyRedemption  plEarlyRedemption=plEarlyRedemptionService.get(Long.valueOf(earlyRedemptionId));
		if(null!=plEarlyRedemption && null!=plEarlyRedemption.getOrderId()){
			PlManageMoneyPlanBuyinfo order=plManageMoneyPlanBuyinfoService.get(plEarlyRedemption.getOrderId());
			if(!order.getState().equals(Short.valueOf("8"))){
				plEarlyRedemption.setCheckStatus(Short.valueOf("3"));
				plEarlyRedemptionService.save(plEarlyRedemption);
				order.setState(Short.valueOf("2"));
				plManageMoneyPlanBuyinfoService.save(order);
				//发送短信。邮件。站内信
				PlManageMoneyPlan plan = plManageMoneyPlanService.get(order.getMmplanId());
				Map<String, String> mapMessage1 = new HashMap<String, String>();
				mapMessage1.put("key", "sms_redeemFail");
				mapMessage1.put("userId",mem.getId().toString());
				mapMessage1.put("${name}", mem.getLoginname());
				mapMessage1.put("${projNumber}", plan.getMmName());
				mapMessage1.put("${projName}", plan.getMmNumber());
				String result1 =  sendMesService.sendSmsEmailMessage(mapMessage1);
				
				webMsgInstance("0", Constants.CODE_SUCCESS,"提前支取驳回成功", "", "", "", "", "");
			
			}else{
				webMsgInstance("0", Constants.CODE_FAILED,"提前支取失败，已经支取过", "", "", "", "", "");
			}
			
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
	
	public boolean isLogin(){
		Boolean boo = false;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			boo = true;
		}
		return boo;
	}

	public List<PlManageMoneyType> getListMoneyType() {
		return listMoneyType;
	}

	public void setListMoneyType(List<PlManageMoneyType> listMoneyType) {
		this.listMoneyType = listMoneyType;
	}

	public Pager getUpager() {
		return upager;
	}

	public void setUpager(Pager upager) {
		this.upager = upager;
	}

	public List<PlManageMoneyType> getPlManageMoneyType() {
		return plManageMoneyType;
	}

	public void setPlManageMoneyType(List<PlManageMoneyType> plManageMoneyType) {
		this.plManageMoneyType = plManageMoneyType;
	}

	public PlManageMoneyPlanBuyinfo getPlManageMoneyPlanBuyinfo() {
		return plManageMoneyPlanBuyinfo;
	}

	public void setPlManageMoneyPlanBuyinfo(
			PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo) {
		this.plManageMoneyPlanBuyinfo = plManageMoneyPlanBuyinfo;
	}

	public List<PlMmOrderChildrenOr> getNewClaimsList() {
		return newClaimsList;
	}

	public void setNewClaimsList(List<PlMmOrderChildrenOr> newClaimsList) {
		this.newClaimsList = newClaimsList;
	}
}