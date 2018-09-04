package com.hurong.credit.action.financingAgency.manageMoney;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.p2p.loan.P2pLoanBasisMaterial;
import com.hurong.credit.model.system.ErpMsg;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderChildrenOrService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.thirdPayInterface.CommonDetail;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import com.thirdPayInterface.UMPay.UMPay;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class PlManageMoneyPlanAction extends BaseAction {
	@Resource
	private PlManageMoneyPlanService plManageMoneyPlanService;
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	@Resource
	private PlManageMoneyTypeService plManageMoneyTypeService;
	
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	
	@Resource
	private BpCustMemberService bpCustMemberService;
	
	@Resource
	private PlMmOrderChildrenOrService plMmOrderChildrenOrService;
	
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	@Resource
	private FuiouService fuiouService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoSevice;
	@Resource
	private BpCouponsService bpCouponsService;
	@Resource
	private OpraterBussinessDataService opraterBussinessDataService;
	private PlManageMoneyPlan moneyPlan;//理财计划
	
	private PlManageMoneyPlan plan;//理财计划-----静态->动态
	
	private java.lang.Long mmplanId;//理财计划Id
	
	private List<PlManageMoneyPlanBuyinfo> buyInfoList;//理财计划投资人列表

	private PlManageMoneyPlan bybMoneyPlan;//白翼宝
	
	private List<PlManageMoneyPlan> listMoneyPlan;//固定列投资列表
	
	private List<PlManageMoneyType> listMoneyType;//计划类别列表
	
	private PlManageMoneyPlan plmmExperience;//体验标
	
	private List<PlMmOrderChildrenOr> orderChildrenList;//债权清单

	private URL remoteFile = null;
	private File storeFile = null;
	private BigDecimal curentInte=new BigDecimal(0);
	private String formtoken;
	
	public String getFormtoken() {
		return formtoken;
	}

	public void setFormtoken(String formtoken) {
		this.formtoken = formtoken;
	}

	public PlManageMoneyPlan getPlan() {
		return plan;
	}

	public void setPlan(PlManageMoneyPlan plan) {
		this.plan = plan;
	}

	public PlManageMoneyPlan getBybMoneyPlan() {
		return bybMoneyPlan;
	}

	public void setBybMoneyPlan(PlManageMoneyPlan bybMoneyPlan) {
		this.bybMoneyPlan = bybMoneyPlan;
	}

	public List<PlManageMoneyPlan> getListMoneyPlan() {
		return listMoneyPlan;
	}

	public void setListMoneyPlan(List<PlManageMoneyPlan> listMoneyPlan) {
		this.listMoneyPlan = listMoneyPlan;
	}

	public PlManageMoneyPlan getMoneyPlan() {
		return moneyPlan;
	}

	public void setMoneyPlan(PlManageMoneyPlan moneyPlan) {
		this.moneyPlan = moneyPlan;
	}
	
	public java.lang.Long getMmplanId() {
		return mmplanId;
	}

	public void setMmplanId(java.lang.Long mmplanId) {
		this.mmplanId = mmplanId;
	}

	public List<PlManageMoneyPlanBuyinfo> getBuyInfoList() {
		return buyInfoList;
	}

	public void setBuyInfoList(List<PlManageMoneyPlanBuyinfo> buyInfoList) {
		this.buyInfoList = buyInfoList;
	}
	
	public String list(){
		getList();
		getBYBPlan();
		return "freemarker";
	}
	/**
	 * 获得U计划
	 * @return
	 */
	public String getUPlan(){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (pager == null) {
		    pager = new Pager();
		    pager.setPageNumber(pager == null?1:pager.getPageNumber());
		    pager.setPageSize(10);
		}
		String typeId=this.getRequest().getParameter("typeId");//类型Id
		this.getRequest().setAttribute("typeId",typeId);
		
		String investlimit=this.getRequest().getParameter("investlimit");//计划期限
		this.getRequest().setAttribute("investlimit",investlimit);
		
		String yearRate=this.getRequest().getParameter("yearRate");//年化利率
		this.getRequest().setAttribute("yearRate",yearRate);
		
		String sumMoney=this.getRequest().getParameter("sumMoney");//金额范围
		this.getRequest().setAttribute("sumMoney",sumMoney);
		
		this.getRequest().setAttribute("planType", "UPlan");
		QueryFilter filter = new QueryFilter();
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		filter.addFilter("Q_keystr_S_EQ ", "UPlan");
		filter.addFilterIn("Q_state_N_IN", Arrays.asList(1,2,7,10));
		filter.addSorted("state", "ASC");
		filter.addSorted("createtime", "DESC");
		if(null !=typeId && !"".equals(typeId)){
			filter.addFilter("Q_manageMoneyTypeId_L_EQ ", typeId);
		}
		if(null !=investlimit && !"".equals(investlimit)){
			String[] inv=investlimit.split("-");
			filter.addFilter("Q_investlimit_N_GE", inv[0]);
			filter.addFilter("Q_investlimit_N_LE", inv[1]);
		}
		if(null !=yearRate && !"".equals(yearRate)){
			String[] rate=yearRate.split("-");
			filter.addFilter("Q_yeaRate_BD_GT", rate[0]);
			filter.addFilter("Q_yeaRate_BD_LE", rate[1]);
		}
		if(null !=sumMoney && !"".equals(sumMoney)){
			String[] money=sumMoney.split("-");
			filter.addFilter("Q_sumMoney_BD_GT", money[0]);
			filter.addFilter("Q_sumMoney_BD_LE", money[1]);
		}
		List<PlManageMoneyPlan> pList=plManageMoneyPlanService.getAll(filter);
		listMoneyType= plManageMoneyTypeService.getMoneyType("UPlan", Short.valueOf("0"));//一次性查询所有产品类型
		List<PlManageMoneyType> plManageMoneyTypeList = plManageMoneyTypeService.getMoneyType("UPlan", null);//一次性查询所有产品类型
		Map<Long, PlManageMoneyType> map = new HashMap<Long, PlManageMoneyType>();
		List<PlManageMoneyPlan> list=new ArrayList<PlManageMoneyPlan>();
		for(int i = 0;i < plManageMoneyTypeList.size();i++){//list转map便于取值
			map.put(plManageMoneyTypeList.get(i).getManageMoneyTypeId(), plManageMoneyTypeList.get(i));
		}
		for(PlManageMoneyPlan p:pList){
			//预售处理
			if(p.getState()==1 && p.getPreSaleTime() !=null && p.getPreSaleTime().compareTo(new Date())==-1){//属于预售中的
				list.add(p);
			}else if(p.getState()==1 && p.getBuyStartTime() !=null && p.getBuyStartTime().compareTo(new Date())==-1){//招标中
				list.add(p);
			}else if(p.getState()!=1){//其他状态
				list.add(p);
			}
			p.setManageMoneyTypeName(map.get(p.getManageMoneyTypeId()).getName());
			plManageMoneyPlanService.bidDynamic(p);
			plManageMoneyPlanService.setLogoUrl(p);
		}
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		String isMobile =this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
			.append(pager.getTotalCount()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer("createDate");
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"createDate" });
				buff.append(json.serialize(pager.getList()));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			
		}
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.UPLAN).getTemplateFilePath());
		return "freemarker";
	}
	
	public String treasureDetail(){
		System.out.println("********请求白翼宝的了解详情***********");
		getList();
		getBYBPlan();
		return "treasure";
	}
 /**
  * 获取固定类理财计划
  * @return
  */
  public String fixation(){
	  getList();
	  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.FIXATION).getTemplateFilePath());
	  return "freemarker";
  }
  /**
   * 获百易宝理财计划
   * @return
   */
   public String bestYeepay(){
	   getBYBPlan();
 	  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BAIYIBAO).getTemplateFilePath());
 	  return "freemarker";
   }
	//获取固定类理财计划
	public String getList(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_keystr_S_EQ", "mmplan");
		filter.addFilter("Q_name_S_NEQ", "百翼宝");
		filter.addFilter("Q_state_N_NEQ", "1");
		//filter.addFilter("Q_state_N_EQ","1");
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
		
		return "freemarker";
	}
	
	//获取白翼宝理财计划
	public String getBYBPlan(){
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_keystr_S_EQ", "mmplan");
		filter.addFilter("Q_name_S_EQ", "百翼宝");
		//filter.addFilter("Q_state_N_EQ","1");
		List<PlManageMoneyType> list = plManageMoneyTypeService.getAll(filter);
		if(list!=null&&list.size()!=0){
			for(PlManageMoneyType moneyType: list){
				List<PlManageMoneyPlan> pList = plManageMoneyPlanService.getMoneyPlanByType(moneyType.getManageMoneyTypeId());
				if(null!=pList&&pList.size()!=0){
					bybMoneyPlan = pList.get(0);
					if(bybMoneyPlan!=null&&null!=bybMoneyPlan.getState()&&bybMoneyPlan.getState()==1){
						if(bybMoneyPlan!=null){
							plManageMoneyPlanService.bidDynamic(bybMoneyPlan);
							bybMoneyPlan.setManageMoneyTypeName(list.get(0).getName());
						}
					}
				}
			}
		}
		return "freemarker";
	}
	
	//获取理财计划信息
	public String getDetail() throws Exception{

		BpCustMember cust=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
		if(null!=cust){
			this.getRequest().setAttribute("currM", cust.getAvailableInvestMoney());
			commoon(cust);
		}else{
			this.getRequest().setAttribute("currM", 0);
		}

		
		
		String keystr=this.getRequest().getParameter("keystr");
		if(mmplanId!=null&&!"".equals(mmplanId)){
			moneyPlan = plManageMoneyPlanService.get(mmplanId);
			PlManageMoneyType type = plManageMoneyTypeService.get(moneyPlan.getManageMoneyTypeId());
			if(null!=type){
				moneyPlan.setManageMoneyTypeName(type.getName());
			}
			PlManageMoneyPlan moneyP= plManageMoneyPlanService.bidDynamic(moneyPlan);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
			GregorianCalendar gc = new GregorianCalendar(); 
			gc.setTime(moneyP.getBuyStartTime()); 
			gc.add(2,moneyP.getInvestlimit());
			curentInte=moneyPlan.getInvestedMoney().multiply(moneyPlan.getYeaRate()).multiply(new BigDecimal(moneyPlan.getInvestlimit())).divide(new BigDecimal(1200),2,BigDecimal.ROUND_HALF_UP);
			this.getSession().setAttribute("planendtime", df.format(gc.getTime()));
			getBuyInfo();
			plan = moneyP;
//<<<<<<< .working
			if(null !=cust){
				cust = commoon(cust);
				if(null!=cust){
					this.getRequest().setAttribute("currM", cust.getAvailableInvestMoney());
				}else{
					this.getRequest().setAttribute("currM", 0);
				}
/*=======
			BpCustMember cust=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(cust!=null){
				this.getRequest().setAttribute("currM", cust.getAvailableInvestMoney());
>>>>>>> .merge-right.r128735*/
			}else{
				this.getRequest().setAttribute("currM", 0);
			}

			//债权信息
			orderChildrenList=plMmOrderChildrenOrService.listByPlanId(moneyPlan);
		}
		String isMobile=this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
			StringBuffer buff = new StringBuffer("{\"success\":true,\"proDes\":\""+null
					+"\",\"investEnterpriseEnterprisename\":\""+null
					+"\",\"formtoken\":\""+formtoken+"\",\"data\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				buff.append(json.serialize(moneyPlan));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;

		}
		//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MONEYPLAN).getTemplateFilePath());
		if(null!=moneyPlan.getKeystr() && "UPlan".equals(moneyPlan.getKeystr())){
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/uplan_content.ftl");
		}else{
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/newmmplan_content.ftl");
		}
		return "moneyPlan";
	}
	//获取理财计划投资人列表
	public String getBuyInfo(){
		if(mmplanId!=null&&!"".equals(mmplanId)){
			buyInfoList = plManageMoneyPlanBuyinfoService.getBuyInfoListByPlanId(mmplanId, (short)0);
		}
		String isMobile=this.getRequest().getParameter("isMobile");
		if(null!=isMobile && isMobile.endsWith("1")){
			StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"totalCounts\":5").append(",\"result\":");
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			buff.append(gson.toJson(buyInfoList));
			buff.append("}");
			jsonString = buff.toString();
			return SUCCESS;
		}
		return "freemarker";
	}
	
	/**
	 * 购买理财计划
	 * @return
	 */
	public String buyBidplan(){
		String isMobile = this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		if((Boolean) usercondition[0]){//验证是否 具备交易资格
			//返回值
			String[] ret=new String[2];
			//订单号
			String	orderNum=ContextUtil.createRuestNumber();
			String couponId = this.getRequest().getParameter("plBidInfo.couponId");//使用优惠券ID
			BigInteger sign = plManageMoneyPlanBuyinfoSevice.countpl(mem.getId());
			String mmplanId=this.getRequest().getParameter("plBidInfo.bidId");
			PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
			//判断优惠券是否过期
			BpCoupons coupon = null;
			if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
				coupon = bpCouponsService.get(Long.valueOf(couponId));
				if(coupon!=null){
					Date d = new Date();
					if(coupon.getCouponEndDate().getTime()<d.getTime()){
						coupon.setCouponStatus(Short.valueOf("4"));
						bpCouponsService.save(coupon);
						webMsgInstance("0", Constants.CODE_FAILED, "所选择的优惠券已经过期,不能使用该优惠券！",  "MyFinance", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						return "freemarker";
					}
					//检查此理财计划是否设置了可用优惠券
					if(plManageMoneyPlan.getCoupon()!=null&&plManageMoneyPlan.getCoupon()==1){
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "此理财计划不能使用优惠券！",  "MyFinance", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						return "freemarker";
					}
				}
			}
			try{
				String keystr=this.getRequest().getParameter("keystr");
				String buyMoney=this.getRequest().getParameter("plBidInfo.userMoney");
				String [] checkCondition=this.IsCheckBuyCondition(this.getRequest());
				if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_SUCCESS)){
	    			//查询余额
	        		mem = obSystemAccountService.getAccountSumMoney(mem);
					if(mem.getAvailableInvestMoney()!=null&&!"".equals(mem.getAvailableInvestMoney())
							&&mem.getAvailableInvestMoney().compareTo(new BigDecimal(buyMoney))>=0){
						//判断理财计划是否为新手专享
						if(null!=plManageMoneyPlan.getNovice() && 1==plManageMoneyPlan.getNovice()){
							if(sign.compareTo(new BigInteger("0"))>0){
								webMsgInstance("0", Constants.CODE_FAILED, "此产品针对于未购买过的用户",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							}
						}
						if(plManageMoneyPlan.getBuyStartTime().after(new Date())){
							webMsgInstance("0", Constants.CODE_FAILED, "此产品为预售产品，请耐心等待",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							return "freemarker";
						}
						String investType=this.getRequest().getParameter("investType");
						PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo =new PlManageMoneyPlanBuyinfo();
						plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
						plManageMoneyPlanBuyinfo.setBuyMoney(new BigDecimal(buyMoney));
						plManageMoneyPlanBuyinfo.setBuyDatetime(new Date());
						plManageMoneyPlanBuyinfo.setPersionType(PlManageMoneyPlanBuyinfo.P_TYPE0);
						BigDecimal dayRate=plManageMoneyPlan.getYeaRate().divide(new BigDecimal("360"),10,BigDecimal.ROUND_UP);
						plManageMoneyPlanBuyinfo.setCurrentGetedMoney(new BigDecimal("0"));
						plManageMoneyPlanBuyinfo.setPromisDayRate(dayRate);
						plManageMoneyPlanBuyinfo.setPromisMonthRate(plManageMoneyPlan.getYeaRate().divide(new BigDecimal("12"),10,BigDecimal.ROUND_UP));
						plManageMoneyPlanBuyinfo.setPromisYearRate(plManageMoneyPlan.getYeaRate());
						plManageMoneyPlanBuyinfo.setMmName(plManageMoneyPlan.getMmName());
						//加入费用
						if(null !=plManageMoneyPlan.getJoinRate() && !"".equals(plManageMoneyPlan.getJoinRate())){
							plManageMoneyPlanBuyinfo.setJoinMoney(plManageMoneyPlan.getJoinRate().multiply(new BigDecimal(buyMoney)).divide(new BigDecimal(100),4,BigDecimal.ROUND_UP));
						}else{
							plManageMoneyPlanBuyinfo.setJoinMoney(new BigDecimal(0));
						}
						plManageMoneyPlanBuyinfo.setOrderlimit(plManageMoneyPlan.getInvestlimit()*30);
						plManageMoneyPlanBuyinfo.setPromisIncomeSum(plManageMoneyPlanBuyinfo.getBuyMoney().multiply(dayRate.multiply(new BigDecimal(plManageMoneyPlanBuyinfo.getOrderlimit())).divide(new BigDecimal("100"))));
						plManageMoneyPlanBuyinfo.setCurrentMatchingMoney(plManageMoneyPlanBuyinfo.getBuyMoney());
						plManageMoneyPlanBuyinfo.setOptimalDayRate(dayRate);
						plManageMoneyPlanBuyinfo.setKeystr(plManageMoneyPlan.getKeystr());
						plManageMoneyPlanBuyinfo.setInvestType(Integer.valueOf(investType));
						plManageMoneyPlanBuyinfo.setFirstProjectIdcount(0);
						plManageMoneyPlanBuyinfo.setFirstProjectIdstr("");
						plManageMoneyPlanBuyinfo.setIsCreateReward(1);//默认值：1没有生成奖励
						//总支付金额=购买金额+加入费用
						BigDecimal amount=new BigDecimal(buyMoney).add(plManageMoneyPlanBuyinfo.getJoinMoney());
						//总的加入费用
						BigDecimal sumJoinMoney = new BigDecimal(0);
						if(null !=plManageMoneyPlan.getJoinRate() && !"".equals(plManageMoneyPlan.getJoinRate())){
							sumJoinMoney = plManageMoneyPlan.getJoinRate().multiply(plManageMoneyPlan.getSumMoney()).divide(new BigDecimal(100),4,BigDecimal.ROUND_UP);
						}
						if(mem.getThirdPayFlagId()!=null&&!"".equals(mem.getThirdPayFlagId())){
							String loginName=plManageMoneyPlan.getMoneyReceiver();
							if(loginName!=null){
								if(plManageMoneyPlan.getReceiverType().equals("pt")){//平台账户收款
									CommonResponse cr=new CommonResponse();
									CommonRequst cq=new CommonRequst();
									if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
										cq.setIsMobile("1");
									}
									cq.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());
									cq.setThirdPayConfigId0(bpCustMember.getThirdPayFlag0());
									cq.setRequsetNo(orderNum);//请求流水号
									cq.setBussinessType(ThirdPayConstants.BT_MMPLATFORM);//业务类型
									cq.setTransferName(ThirdPayConstants.TN_MMPLATFORM);//业务名称
									cq.setBidId(plManageMoneyPlan.getMmplanId().toString());//标的号
									cq.setPlanMoney(plManageMoneyPlan.getSumMoney().add(sumJoinMoney));//标的金额
									cq.setAmount(amount);//投标金额
									cq.setRemark1(orderNum);//流水号
									cq.setFee(plManageMoneyPlanBuyinfo.getJoinMoney());
									cr=ThirdPayInterfaceUtil.thirdCommon(cq);
									if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
										//判断是否使用优惠券
										if(coupon!=null){
											plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
											plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
										}
										plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
										plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
										plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
										plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
										plManageMoneyPlanBuyinfo.setState((short)0);
										plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
										webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}else{
										 webMsgInstance("0", Constants.CODE_FAILED, "系统错误",  "", "", "", "", "");
										 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}
								}else{//注册用户账户收款
									BpCustMember moneyReciver=bpCustMemberService.isExist(loginName);
									String freezeOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
									if(moneyReciver!=null){
										if(moneyReciver.getThirdPayFlagId()!=null&&!"".equals(moneyReciver.getThirdPayFlagId())){
											Date date = new Date();
											CommonRequst cq = new CommonRequst();     
											if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
												cq.setIsMobile("1");
											}
											cq.setBussinessType(ThirdPayConstants.BT_MMUSER);//业务类型
											cq.setTransferName(ThirdPayConstants.TN_MMUSER);//业务名称
											cq.setThirdPayConfigId(mem.getThirdPayFlagId());//投资人平台会员标识
											cq.setRequsetNo(orderNum);//流水号
											cq.setBidId(plManageMoneyPlan.getMmplanId().toString());//标的号
											cq.setBidName(plManageMoneyPlan.getMmName());//标的名称
											cq.setPlanMoney(plManageMoneyPlan.getSumMoney().add(sumJoinMoney));//标的金额(双乾会控制超投标，故总金额需加上加入费用)
											cq.setLoaner_thirdPayflagId(moneyReciver.getThirdPayFlagId().toString());//借款人平台会员标识
											cq.setAmount(amount);//投标金额
											cq.setFee(plManageMoneyPlanBuyinfo.getJoinMoney());
											cq.setTransactionTime(date);
											
											cq.setMaxTenderRate("0.10");//最大投资手续费率,默认20%
											cq.setFreezeOrdId(freezeOrdId);//冻结流水
											
											if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户购买
												cq.setAccountType(0);//个人投标判断标志
												CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
												if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													//理财计划购买(收款账户为用户)
													Map<String,String> map = new HashMap<String, String>();
													map.put("custmerType", "0");
													map.put("requestNo", orderNum);
													map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
													if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
														map.put("loanNo",cr.getContract_no());
													}
													opraterBussinessDataService.bidMoneyPlan(map);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买成功",  "", "", "", "", "");
													if(isMobile!=null&&"1".equals(isMobile)){
														this.setSuccessResultValue("/WEBINF/template/proj_wenandai/mobile/mobilemessage.ftl");
													}else{
														this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
													}
												}else{
													webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(),  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}
												cq.setAccountType(1);//企业投标判断标志
											}else{//企业用户购买
												cq.setCustMemberType(UMPay.PARTIPAY_PUBLIC);//企业用户   向第三方传递的参数
												CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
												if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													
													Map<String,String> map = new HashMap<String, String>();
													map.put("custmerType", "0");
													map.put("requestNo", orderNum);
													map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
													if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
														map.put("loanNo",cr.getContract_no());
													}
													opraterBussinessDataService.bidMoneyPlan(map);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													if(isMobile!=null&&"1".equals(isMobile)){
														this.setSuccessResultValue("/WEBINF/template/proj_wenandai/mobile/mobilemessage.ftl");
													}else{
														this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
													}
													
												}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}
											}
										}else{
											webMsgInstance("0", Constants.CODE_FAILED, "理财计划收款人没有开通第三方支付账户",  "", "", "", "", "");
											this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
										}
									}else{
										webMsgInstance("0", Constants.CODE_FAILED, "理财计划收款人没有找到",  "", "", "", "", "");
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}
								}
							}else{
								webMsgInstance("0", Constants.CODE_FAILED, "理财计划发布时没有填写收款人账号",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "投资人没有开通第三方支付账户",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "账户资金不足，不允许购买",  "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}else if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_FAILED)){
					webMsgInstance("0", Constants.CODE_FAILED, checkCondition[1],  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "系统错误不允许购买理财计划",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
				
				if(null!=keystr && !"".equals(keystr) && null!=webMsg){//U计划需要,把keystr拼接在购买的请求返回url不可以,所以只能用这种方式
					webMsg.setUrl(webMsg.getUrl()+"&keystr="+keystr);
				}
			}catch(Exception e){
				e.printStackTrace();
				webMsgInstance("0", Constants.CODE_FAILED, "投资失败:系统出现错误",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}else{
			forwardPage=usercondition[2].toString();
			webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	

	/**
	 * 购买理财计划
	 * 做为方法保留
	 * @return
	 */
	public String buyBidplanOld(){
		String isMobile = this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		if((Boolean) usercondition[0]){//验证是否 具备交易资格
			//返回值
			String[] ret=new String[2];
			//订单号
			String	orderNum=ContextUtil.createRuestNumber();
			String couponId = this.getRequest().getParameter("plBidInfo.couponId");//使用优惠券ID
			BigInteger sign = plManageMoneyPlanBuyinfoSevice.countpl(mem.getId());
			String mmplanId=this.getRequest().getParameter("plBidInfo.bidId");
			PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
			//判断优惠券是否过期
			BpCoupons coupon = null;
			if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
				coupon = bpCouponsService.get(Long.valueOf(couponId));
				if(coupon!=null){
					Date d = new Date();
					if(coupon.getCouponEndDate().getTime()<d.getTime()){
						coupon.setCouponStatus(Short.valueOf("4"));
						bpCouponsService.save(coupon);
						webMsgInstance("0", Constants.CODE_FAILED, "所选择的优惠券已经过期,不能使用该优惠券！",  "MyFinance", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						return "freemarker";
					}
					//检查此理财计划是否设置了可用优惠券
					if(plManageMoneyPlan.getCoupon()!=null&&plManageMoneyPlan.getCoupon()==1){
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "此理财计划不能使用优惠券！",  "MyFinance", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						return "freemarker";
					}
				}
			}
			try{
				String keystr=this.getRequest().getParameter("keystr");
				String buyMoney=this.getRequest().getParameter("plBidInfo.userMoney");
				String [] checkCondition=this.IsCheckBuyCondition(this.getRequest());
				if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_SUCCESS)){
	    			//查询余额
	        		mem = obSystemAccountService.getAccountSumMoney(mem);
					if(mem.getAvailableInvestMoney()!=null&&!"".equals(mem.getAvailableInvestMoney())
							&&mem.getAvailableInvestMoney().compareTo(new BigDecimal(buyMoney))>=0){
						//判断理财计划是否为新手专享
						if(null!=plManageMoneyPlan.getNovice() && 1==plManageMoneyPlan.getNovice()){
							if(sign.compareTo(new BigInteger("0"))>0){
								webMsgInstance("0", Constants.CODE_FAILED, "此产品针对于未购买过的用户",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								return "freemarker";
							}
						}
						if(plManageMoneyPlan.getBuyStartTime().after(new Date())){
							webMsgInstance("0", Constants.CODE_FAILED, "此产品为预售产品，请耐心等待",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							return "freemarker";
						}
						String investType=this.getRequest().getParameter("investType");
						PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo =new PlManageMoneyPlanBuyinfo();
						plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
						plManageMoneyPlanBuyinfo.setBuyMoney(new BigDecimal(buyMoney));
						plManageMoneyPlanBuyinfo.setBuyDatetime(new Date());
						plManageMoneyPlanBuyinfo.setPersionType(PlManageMoneyPlanBuyinfo.P_TYPE0);
						BigDecimal dayRate=plManageMoneyPlan.getYeaRate().divide(new BigDecimal("360"),10,BigDecimal.ROUND_UP);
						plManageMoneyPlanBuyinfo.setCurrentGetedMoney(new BigDecimal("0"));
						plManageMoneyPlanBuyinfo.setPromisDayRate(dayRate);
						plManageMoneyPlanBuyinfo.setPromisMonthRate(plManageMoneyPlan.getYeaRate().divide(new BigDecimal("12"),10,BigDecimal.ROUND_UP));
						plManageMoneyPlanBuyinfo.setPromisYearRate(plManageMoneyPlan.getYeaRate());
						plManageMoneyPlanBuyinfo.setMmName(plManageMoneyPlan.getMmName());
						//加入费用
						if(null !=plManageMoneyPlan.getJoinRate() && !"".equals(plManageMoneyPlan.getJoinRate())){
							plManageMoneyPlanBuyinfo.setJoinMoney(plManageMoneyPlan.getJoinRate().multiply(new BigDecimal(buyMoney)).divide(new BigDecimal(100),4,BigDecimal.ROUND_UP));
						}else{
							plManageMoneyPlanBuyinfo.setJoinMoney(new BigDecimal(0));
						}
						plManageMoneyPlanBuyinfo.setOrderlimit(plManageMoneyPlan.getInvestlimit()*30);
						plManageMoneyPlanBuyinfo.setPromisIncomeSum(plManageMoneyPlanBuyinfo.getBuyMoney().multiply(dayRate.multiply(new BigDecimal(plManageMoneyPlanBuyinfo.getOrderlimit())).divide(new BigDecimal("100"))));
						plManageMoneyPlanBuyinfo.setCurrentMatchingMoney(plManageMoneyPlanBuyinfo.getBuyMoney());
						plManageMoneyPlanBuyinfo.setOptimalDayRate(dayRate);
						plManageMoneyPlanBuyinfo.setKeystr(plManageMoneyPlan.getKeystr());
						plManageMoneyPlanBuyinfo.setInvestType(Integer.valueOf(investType));
						plManageMoneyPlanBuyinfo.setFirstProjectIdcount(0);
						plManageMoneyPlanBuyinfo.setFirstProjectIdstr("");
						plManageMoneyPlanBuyinfo.setIsCreateReward(1);//默认值：1没有生成奖励
						//总支付金额=购买金额+加入费用
						BigDecimal amount=new BigDecimal(buyMoney).add(plManageMoneyPlanBuyinfo.getJoinMoney());
						//总的加入费用
						BigDecimal sumJoinMoney = new BigDecimal(0);
						if(null !=plManageMoneyPlan.getJoinRate() && !"".equals(plManageMoneyPlan.getJoinRate())){
							sumJoinMoney = plManageMoneyPlan.getJoinRate().multiply(plManageMoneyPlan.getSumMoney()).divide(new BigDecimal(100),4,BigDecimal.ROUND_UP);
						}
						if(mem.getThirdPayFlagId()!=null&&!"".equals(mem.getThirdPayFlagId())){
							String loginName=plManageMoneyPlan.getMoneyReceiver();
							if(loginName!=null){
								if(plManageMoneyPlan.getReceiverType().equals("pt")){//平台账户收款
									CommonResponse cr=new CommonResponse();
									CommonRequst cq=new CommonRequst();
									if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
										cq.setIsMobile("1");
									}
									cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
									cq.setRequsetNo(orderNum);//请求流水号
									cq.setBussinessType(ThirdPayConstants.BT_MMPLATFORM);//业务类型
									cq.setTransferName(ThirdPayConstants.TN_MMPLATFORM);//业务名称
									List<CommonDetail> list=new ArrayList<CommonDetail>();
									CommonDetail dt=new CommonDetail();
									dt.setAmount(amount.toString());//转账金额
									dt.setTargetUserType("plateForm");//收款人用户类型
									list.add(dt);
									cq.setDetailList(list);//转账记录
									cq.setBidId(plManageMoneyPlan.getMmplanId().toString());//标的号
									cq.setPlanMoney(plManageMoneyPlan.getSumMoney().add(sumJoinMoney));//标的金额
									cq.setAmount(amount);//投标金额
									cq.setRemark1(orderNum);//流水号
									cq.setFee(plManageMoneyPlanBuyinfo.getJoinMoney());
									cr=ThirdPayInterfaceUtil.thirdCommon(cq);
									if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
										//判断是否使用优惠券
										if(coupon!=null){
											plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
											plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
										}
										plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
										plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
										plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
										plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
										plManageMoneyPlanBuyinfo.setState((short)0);
										plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
										webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}else{
										 webMsgInstance("0", Constants.CODE_FAILED, "系统错误",  "", "", "", "", "");
										 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}
								}else{//注册用户账户收款
									BpCustMember moneyReciver=bpCustMemberService.isExist(loginName);
									String freezeOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
									if(moneyReciver!=null){
										if(moneyReciver.getThirdPayFlagId()!=null&&!"".equals(moneyReciver.getThirdPayFlagId())){
											Date date = new Date();
											CommonRequst cq = new CommonRequst();     
											if(isMobile!=null&&!"".equals(isMobile)&&("1".equals(isMobile))){
												cq.setIsMobile("1");
											}
											cq.setBussinessType(ThirdPayConstants.BT_MMUSER);//业务类型
											cq.setTransferName(ThirdPayConstants.TN_MMUSER);//业务名称
											cq.setThirdPayConfigId(mem.getThirdPayFlagId());//投资人平台会员标识
											cq.setRequsetNo(orderNum);//流水号
											cq.setBidId(plManageMoneyPlan.getMmplanId().toString());//标的号
											cq.setBidName(plManageMoneyPlan.getMmName());//标的名称
											cq.setPlanMoney(plManageMoneyPlan.getSumMoney().add(sumJoinMoney));//标的金额(双乾会控制超投标，故总金额需加上加入费用)
											cq.setLoaner_thirdPayflagId(moneyReciver.getThirdPayFlagId().toString());//借款人平台会员标识
											cq.setAmount(amount);//投标金额
											cq.setFee(plManageMoneyPlanBuyinfo.getJoinMoney());
											cq.setTransactionTime(date);
											
											cq.setMaxTenderRate("0.10");//最大投资手续费率,默认20%
											cq.setFreezeOrdId(freezeOrdId);//冻结流水
											
											if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户购买
												cq.setAccountType(0);//个人投标判断标志
												CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
												if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													//理财计划购买(收款账户为用户)
													Map<String,String> map = new HashMap<String, String>();
													map.put("custmerType", "0");
													map.put("requestNo", orderNum);
													map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
													if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
														map.put("loanNo",cr.getContract_no());
													}
													opraterBussinessDataService.bidMoneyPlan(map);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买成功",  "", "", "", "", "");
													if(isMobile!=null&&"1".equals(isMobile)){
														this.setSuccessResultValue("/WEBINF/template/proj_wenandai/mobile/mobilemessage.ftl");
													}else{
														this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
													}
												}else{
													webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(),  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}
												cq.setAccountType(1);//企业投标判断标志
											}else{//企业用户购买
												cq.setCustMemberType(UMPay.PARTIPAY_PUBLIC);//企业用户   向第三方传递的参数
												CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
												if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													
													Map<String,String> map = new HashMap<String, String>();
													map.put("custmerType", "0");
													map.put("requestNo", orderNum);
													map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
													if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
														map.put("loanNo",cr.getContract_no());
													}
													opraterBussinessDataService.bidMoneyPlan(map);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													if(isMobile!=null&&"1".equals(isMobile)){
														this.setSuccessResultValue("/WEBINF/template/proj_wenandai/mobile/mobilemessage.ftl");
													}else{
														this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
													}
													
												}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
													//判断是否使用优惠券
													if(coupon!=null){
														plManageMoneyPlanBuyinfo.setCouponId(Long.valueOf(couponId));
														plManageMoneyPlanBuyinfo.setCouponsMoney(coupon.getCouponValue());
													}
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
													plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
													plManageMoneyPlanBuyinfo.setState((short)0);
													plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
													webMsgInstance("0", Constants.CODE_SUCCESS, "购买申请成功",  "", "", "", "", "");
													this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
												}
											}
										}else{
											webMsgInstance("0", Constants.CODE_FAILED, "理财计划收款人没有开通第三方支付账户",  "", "", "", "", "");
											this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
										}
									}else{
										webMsgInstance("0", Constants.CODE_FAILED, "理财计划收款人没有找到",  "", "", "", "", "");
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
									}
								}
							}else{
								webMsgInstance("0", Constants.CODE_FAILED, "理财计划发布时没有填写收款人账号",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
							}
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "投资人没有开通第三方支付账户",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						}
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "账户资金不足，不允许购买",  "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}else if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_FAILED)){
					webMsgInstance("0", Constants.CODE_FAILED, checkCondition[1],  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "系统错误不允许购买理财计划",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
				
				if(null!=keystr && !"".equals(keystr) && null!=webMsg){//U计划需要,把keystr拼接在购买的请求返回url不可以,所以只能用这种方式
					webMsg.setUrl(webMsg.getUrl()+"&keystr="+keystr);
				}
			}catch(Exception e){
				e.printStackTrace();
				webMsgInstance("0", Constants.CODE_FAILED, "投资失败:系统出现错误",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}else{
			forwardPage=usercondition[2].toString();
			webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	
	public String buyBidplan1111(){
		//返回值
		String[] ret=new String[2];
		//订单号
		String	orderNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		System.out.println("orderNum=="+orderNum);
		try{
			String InvestPersonId=this.getRequest().getParameter("plBidInfo.userId");
			String InvestPersonName=this.getRequest().getParameter("plBidInfo.userName");
			String mmplanId=this.getRequest().getParameter("plBidInfo.bidId");
			String buyMoney=this.getRequest().getParameter("plBidInfo.userMoney");
			System.out.println("******************mmplanId="+mmplanId);
			String[] ischeck=this.IsCheckBuyCondition(this.getRequest());//检查是否具备购买理财计划资格
			if(ischeck!=null&&ischeck[0].equals(Constants.CODE_SUCCESS)){
				
			}else if(ischeck!=null&&ischeck[0].equals(Constants.CODE_FAILED)){
				webMsgInstance("0", Constants.CODE_FAILED, ischeck[1],  "", "", "", "", "");
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, "系统错误",  "", "", "", "", "");
			}
			
			BpCustMember cust = bpCustMemberService.get(Long.valueOf(InvestPersonId));
			if(buyMoney!=null && !"".equals(buyMoney)){
				if(mmplanId!=null&&!"".equals(mmplanId)){
					PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
					if(plManageMoneyPlan!=null){
						if(configMap.get("thirdPayType").toString().equals("0")&& configMap.get("thirdPayConfig").toString().equals("fuiouConfig")){
			    			cust=fuiouService.getCurrentMoney(cust);
			    		}else{
			    			//查询余额
			        		cust = obSystemAccountService.getAccountSumMoney(cust);
			    		}
						if(cust.getAvailableInvestMoney().compareTo(new BigDecimal("0"))==1){
							if(cust.getAvailableInvestMoney().compareTo(new BigDecimal(buyMoney))>-1){
								if(plManageMoneyPlan.getStartMoney()!=null&&plManageMoneyPlan.getLimitMoney()!=null){
									if(new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getStartMoney())>-1&&new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getLimitMoney())<1){
										//判断投资金额与剩余金额
										plManageMoneyPlan.setAfterMoney(plManageMoneyPlan.getSumMoney());
										plManageMoneyPlan = plManageMoneyPlanService.bidDynamic(plManageMoneyPlan);
										if(new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getAfterMoney())>0){
											setJsonString("{\"success\":false,\"msg\":\"购买金额大于剩余金额\"}");
											webMsgInstance("0", Constants.CODE_FAILED, "购买金额大于剩余金额!",  "", "", "", "", "");
											this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
											return "freemarker";
										}	
										
										if(new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getAfterMoney())<1){
											BigDecimal amoney=new BigDecimal(buyMoney).subtract(plManageMoneyPlan.getStartMoney());
											if(plManageMoneyPlan.getRiseMoney()!=null){
												if(amoney.compareTo(new BigDecimal(0))>0){
													int ab=Integer.valueOf(amoney.intValue())%Integer.valueOf(plManageMoneyPlan.getRiseMoney().intValue());
											        if(ab!=0){
														setJsonString("{\"success\":false,\"msg\":\"购买金额不符合递增金额\"}");
														webMsgInstance("0", Constants.CODE_FAILED, "购买金额不符合递增金额!",  "", "", "", "", "");
														this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
														return "freemarker";
													}
												}
													PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo =new PlManageMoneyPlanBuyinfo();
													plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
													plManageMoneyPlanBuyinfo.setBuyMoney(new BigDecimal(buyMoney));
													plManageMoneyPlanBuyinfo.setBuyDatetime(new Date());
													
													plManageMoneyPlanBuyinfo.setPersionType(PlManageMoneyPlanBuyinfo.P_TYPE0);
													BigDecimal dayRate=plManageMoneyPlan.getYeaRate().divide(new BigDecimal("360"),10,BigDecimal.ROUND_UP);
													plManageMoneyPlanBuyinfo.setCurrentGetedMoney(new BigDecimal("0"));
													plManageMoneyPlanBuyinfo.setPromisDayRate(dayRate);
													plManageMoneyPlanBuyinfo.setPromisMonthRate(plManageMoneyPlan.getYeaRate().divide(new BigDecimal("12"),10,BigDecimal.ROUND_UP));
													plManageMoneyPlanBuyinfo.setPromisYearRate(plManageMoneyPlan.getYeaRate());
													plManageMoneyPlanBuyinfo.setMmName(plManageMoneyPlan.getMmName());
													
													System.out.println("plManageMoneyPlanBuyinfo.getBuyMoney()=="+plManageMoneyPlanBuyinfo.getBuyMoney()+" ==plManageMoneyPlanBuyinfo.getOrderlimit()="+plManageMoneyPlanBuyinfo.getOrderlimit()+" ==dayRate="+dayRate);
													plManageMoneyPlanBuyinfo.setOrderlimit(plManageMoneyPlan.getInvestlimit()*30);
													if(plManageMoneyPlan.getIsCyclingLend()==1){
														int investlimit =plManageMoneyPlan.getInvestlimit();
														BigDecimal baseMoney=plManageMoneyPlanBuyinfo.getBuyMoney();
														BigDecimal summoney=new BigDecimal(0);
														for(int i=1;i<=investlimit;i++){
															summoney=summoney.add(baseMoney.multiply(plManageMoneyPlanBuyinfo.getPromisMonthRate()).divide(new BigDecimal(100)));
															baseMoney=plManageMoneyPlanBuyinfo.getBuyMoney().add(summoney);
														 }
														
														plManageMoneyPlanBuyinfo.setPromisIncomeSum(summoney);
													}else{
														
														plManageMoneyPlanBuyinfo.setPromisIncomeSum(plManageMoneyPlanBuyinfo.getBuyMoney().multiply(dayRate.multiply(new BigDecimal(plManageMoneyPlanBuyinfo.getOrderlimit())).divide(new BigDecimal("100"))));
													}
													plManageMoneyPlanBuyinfo.setCurrentMatchingMoney(plManageMoneyPlanBuyinfo.getBuyMoney());
													plManageMoneyPlanBuyinfo.setOptimalDayRate(dayRate);
													plManageMoneyPlanBuyinfo.setKeystr("mmplan");
													plManageMoneyPlanBuyinfo.setFirstProjectIdcount(0);
													plManageMoneyPlanBuyinfo.setFirstProjectIdstr("");
													plManageMoneyPlanBuyinfo.setIsAtuoMatch(0);
													plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
													plManageMoneyPlanBuyinfo.setInvestPersonId(Long.valueOf(InvestPersonId));
													if(cust!=null){
														plManageMoneyPlanBuyinfo.setInvestPersonName(cust.getLoginname());
													}else{
														plManageMoneyPlanBuyinfo.setInvestPersonName("InvestPersonName");
													}
													String msg="投标成功!系统放款中...投标金额";
													//托管模式
													if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)){
														if(configMap.get("thirdPayConfig").toString().equals(Constants.FUIOU)){
															if(cust!=null && cust.getThirdPayFlagId()!=null &&!"".equals(cust.getThirdPayFlagId())){
																cust=fuiouService.getCurrentMoney(cust);
																if(cust.getAvailableInvestMoney().compareTo(plManageMoneyPlanBuyinfo.getBuyMoney())>=0){
																	//不能带小数点
																	 String amount=plManageMoneyPlanBuyinfo.getBuyMoney().multiply(new BigDecimal(100)).setScale(0).toString();
																	 String[] returnret=fuiouService.preAuth(orderNum, cust.getThirdPayFlag0(), configMap.get("thirdPay_fuiou_platloginname").toString(), amount, "", this.getRequest());
																	 if(returnret[0].equals(Constants.CODE_SUCCESS)){
																		 plManageMoneyPlanBuyinfo.setState((short)1);
																		 plManageMoneyPlanBuyinfo.setPreAuthorizationNum(returnret[3]);
																		 plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
																		 Map<String,Object> map=new HashMap<String,Object>();
																		 map.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
																		 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
																		 map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
																		 map.put("money",plManageMoneyPlanBuyinfo.getBuyMoney());//交易金额
																		 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
																		 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
																		 map.put("recordNumber",orderNum);//交易流水号
																		 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
																		 ret =obAccountDealInfoService.operateAcountInfo(map);
																		/* ret = obAccountDealInfoService.operateAcountInfo(plManageMoneyPlanBuyinfo.getInvestPersonId().toString(),ObAccountDealInfo.T_INVEST.toString(), plManageMoneyPlanBuyinfo.getBuyMoney().toString() ,"3",
																					ObSystemAccount.type0.toString(), ObAccountDealInfo.UNFREEZY.toString(),ObAccountDealInfo.ISREADTHIRDRECORD.toString(),orderNum);*/
																	 }else{
																		 ret[0]=returnret[0];
																		 ret[1]="预授权投资失败："+returnret[1];
																	 }
																}/*else if((cust.getAvailableInvestMoney().add(cust.getUnChargeMoney())).compareTo(plManageMoneyPlanBuyinfo.getBuyMoney())>=0){
																	ret[0]=Constants.CODE_SUCCESS;
																	 plManageMoneyPlanBuyinfo.setState((short)5);
																}*/else{
																	ret[0]=Constants.CODE_SUCCESS;
																	plManageMoneyPlanBuyinfo.setState((short)5);
																	plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
																	ret[1]="已经成功预约购买了当期理财计划";
																	msg="预投理财计划订单生成，请与24小时内保证资金到账，否则将取消投资资格!....投标金额";
															    }
															}else{
																ret[0]=Constants.CODE_FAILED;
																 ret[1]="投资人没有开通第三方账户：";
															}
														}else if(configMap.get("thirdPayConfig").toString().equals(Constants.YEEPAY)){
															//gaomimiinferface
															BpCustMember investcustmem=bpCustMemberService.get(plManageMoneyPlanBuyinfo.getInvestPersonId());
															String loginName=plManageMoneyPlan.getMoneyReceiver();
															if(loginName!=null){
																BpCustMember moneyReciver=bpCustMemberService.isExist(loginName);
																if(moneyReciver!=null){
																}else{
																	webMsgInstance("0", Constants.CODE_FAILED, "理财计划收款人没有找到",  "", "", "", "", "");
																	this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
																	return "freemarker";
																}
															}else{
																webMsgInstance("0", Constants.CODE_FAILED, "理财计划发布时没有填写收款人账号",  "", "", "", "", "");
																this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
																return "freemarker";
															}
															
														}
													}else if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG1)){// 直付模式
														plManageMoneyPlanBuyinfo.setState((short)1);
														// 保存交易记录到 erp
														Map<String,Object> map=new HashMap<String,Object>();
														 map.put("investPersonId",plManageMoneyPlanBuyinfo.getInvestPersonId());//投资人Id
														 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
														 map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
														 map.put("money",plManageMoneyPlanBuyinfo.getBuyMoney());//交易金额
														 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
														 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
														 map.put("recordNumber",orderNum);//交易流水号
														 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付(冻结)，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
														 ret =obAccountDealInfoService.operateAcountInfo(map);
														/*ret = obAccountDealInfoService.operateAcountInfo(plManageMoneyPlanBuyinfo.getInvestPersonId().toString(),ObAccountDealInfo.T_INVEST.toString(), plManageMoneyPlanBuyinfo.getBuyMoney().toString() ,ObAccountDealInfo.BANKDEAL.toString(),
																ObSystemAccount.type0.toString(), ObAccountDealInfo.UNFREEZY.toString(),ObAccountDealInfo.UNREADTHIRDRECORD.toString(),orderNum);*/
													}
													if(ret[0].equals(Constants.CODE_SUCCESS)){
														plManageMoneyPlan.setInvestedMoney(plManageMoneyPlan.getInvestedMoney().add(plManageMoneyPlanBuyinfo.getBuyMoney()));
														plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
												    	plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
												    	if(plManageMoneyPlan.getSumMoney().compareTo(plManageMoneyPlan.getInvestedMoney())==0){
															plManageMoneyPlan.setState(plManageMoneyPlan.STATE2);
														}
												    	BigDecimal[] afterMoney=obSystemAccountService.sumTypeTotalMoney(Long.valueOf(InvestPersonId),String.valueOf(PlManageMoneyPlanBuyinfo.P_TYPE0));
														setJsonString("{\"success\":true,\"msg\":\""+msg+plManageMoneyPlanBuyinfo.getBuyMoney()+"\",\"afterMoney\":"+plManageMoneyPlanBuyinfo.getPlManageMoneyPlan().getSumMoney().subtract(plManageMoneyPlanBuyinfo.getPlManageMoneyPlan().getInvestedMoney())+",\"myMoney\":"+afterMoney[3]+"}");
												     }else{
												    	 	setJsonString("{\"success\":false,\"msg\":\""+ret[1]+"\"}");
												 	}
											}else{
												setJsonString("{\"success\":false,\"msg\":\"理财计划的递增金额没有录入！\"}");
											}
										}else{
											setJsonString("{\"success\":false,\"msg\":\"购买金额大于剩余金额\"}");
										}
									}else{
										setJsonString("{\"success\":false,\"msg\":\"购买金额必须位于起始金额和最大投资额范围内\"}");
									}
								}else{
									setJsonString("{\"success\":false,\"msg\":\"理财计划的起始金额或最大投资额没有录入！\"}");
								}
							}else{
								setJsonString("{\"success\":false,\"msg\":\"购买金额不能超过可投资的金额！\"}");
							}
						}else{
							setJsonString("{\"success\":false,\"msg\":\"可用余额不足！\"}");
						}
					}else{
						setJsonString("{\"success\":false,\"msg\":\"没有对应的理财计划！\"}");
					}
				}else{
					setJsonString("{\"success\":false,\"msg\":\"投资理财计划项目id不能为空！\"}");
				}
			}else{
				setJsonString("{\"success\":false,\"msg\":\"投资人投资金额不能为空！\"}");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			setJsonString("{\"success\":false,\"msg\":\"投资失败:系统出现错误！\"}");
		}
		System.out.println(getJsonString());
		Gson gson=new Gson();
		ErpMsg  msg=gson.fromJson(getJsonString(), ErpMsg.class);
		if(msg!=null){
			String code= Constants.CODE_FAILED;
			if(msg.getSuccess().equals("true")){
				code=Constants.CODE_SUCCESS;
			}
			webMsgInstance("0", code, msg.getMsg(),  "", "", "", "", "");
			if("8888".equals(webMsg.getCode())&&"proj_hongdaxindai".equals(AppUtil.getProjStr())){
				String toUrl = "/creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId="+mmplanId;
				this.getRequest().setAttribute("toUrl", toUrl);
			}
		
		}else{
			webMsgInstance("0", Constants.CODE_FAILED, "操作出错",  "", "", "", "", "");
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
		
	}
	/**
	 * 检查是否符合投资情况
	 * @return
	 */
	public String[] IsCheckBuyCondition(HttpServletRequest request){
		try{
			String[] ret=new String[2];
			String mmplanId=request.getParameter("plBidInfo.bidId");
			String buyMoney=request.getParameter("plBidInfo.userMoney");
			System.out.println("******************mmplanId="+mmplanId);
			if(mmplanId!=null&&!"".equals(mmplanId)){
				PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
				if(plManageMoneyPlan!=null){
					if(buyMoney!=null && !"".equals(buyMoney)){
						if(plManageMoneyPlan.getStartMoney()!=null&&plManageMoneyPlan.getLimitMoney()!=null&&plManageMoneyPlan.getRiseMoney()!=null){//判断理财产品数据完整性
							if(new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getStartMoney())>-1&&new BigDecimal(buyMoney).compareTo(plManageMoneyPlan.getLimitMoney())<1){
								BigDecimal afterMoney=plManageMoneyPlan.getSumMoney().subtract(plManageMoneyPlan.getInvestedMoney());
								if(new BigDecimal(buyMoney).compareTo(afterMoney)<1){
									BigDecimal amoney=new BigDecimal(buyMoney).subtract(plManageMoneyPlan.getStartMoney());
									if(amoney.compareTo(new BigDecimal(0))>0){
										int ab=Integer.valueOf(amoney.intValue())%Integer.valueOf(plManageMoneyPlan.getRiseMoney().intValue());
								        if(ab!=0){
								        	ret[0]=Constants.CODE_FAILED;
											ret[1]="购买金额不符合递增金额";
										}else{
											ret[0]=Constants.CODE_SUCCESS;
											ret[1]="具备购买资格";
										}
									}else{
										ret[0]=Constants.CODE_SUCCESS;
										ret[1]="具备购买资格";
									}
								}else{
									ret[0]=Constants.CODE_FAILED;
									ret[1]="购买金额必须位于起始金额和最大投资额范围内";
									setJsonString("{\"success\":false,\"msg\":\"购买金额大于剩余金额\"}");
								}
							}else{
								ret[0]=Constants.CODE_FAILED;
								ret[1]="购买金额必须位于起始金额和最大投资额范围内";
							}
						}else{
							ret[0]=Constants.CODE_FAILED;
							ret[1]="理财计划的基础必填数据不全面";
						}
					}else{
						ret[0]=Constants.CODE_FAILED;
						ret[1]="投资金额不能为空";
					}
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="没有找到理财计划";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="没有获得理财计划标识";
			}
					
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 易宝支付投标跳转
	 * @param cpCut
	 * @param orderNum
	 * @param orderDate
	 * @param plBidInfo2
	 * @return
	 */
	private String[] tenderToYeePay(BpCustMember cpCut, String orderNum,
			String orderDate, PlManageMoneyPlanBuyinfo plBidInfo2,String mobile) {
		String[] ret=new String[2];
		/*BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);
		String loanMoney=plBidInfoService.getLoanMoney(plBidInfo);
		PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
		if(LoanMember==null || LoanMember.getThirdPayFlagId()==null ||LoanMember.getThirdPayFlagId().equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人未开通第三方在支付";
		}else if(loanMoney==null || loanMoney.equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人借款金额错误";
		}else {
			if(cpCut!=null && cpCut.getThirdPayFlagId()!=null &&!"".equals(cpCut.getThirdPayFlagId())){
				*//**
				 * (7)资金冻结（投标）
			     * Map<String,object> map  第三方支付投标需要的map参数
				 * map.get("basePath").toString() 只当前的绝对路径
				 * map.get("platformUserNo").toString() 投资者第三方支付账号
				 * map.get("customerId").toString();
				 * map.get("customerType").toString();
				 * map.get("requestNo").toString()交易流水号（易宝的和第三方支付账号保持一致）
				 * map.get("bidMoney").toString() 投标金额
				 * map.get("bidPlanMoney").toString() 标总金额
				 * map.get("bidPlanId").toString() 标的id
				 * map.get("bidPlanType").toString() 标的类型
				 * map.get("targetplatformUserNo").toString()
				 *//*
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("requestNo", orderNum);
				map.put("basePath", this.getBasePath());
				map.put("platformUserNo", cpCut.getThirdPayFlagId());
				map.put("customerId", cpCut.getId().toString());
				map.put("customerType", "0");
				map.put("bidPlanId", plBidInfo.getPlBidPlan().getBidId().toString());
				map.put("bidPlanType", PlBidPlan.BIDPLANTYPE);
				map.put("bidPlanMoney", bidplan.getBidMoney().toString());
				map.put("bidMoney", plBidInfo.getUserMoney().toString());
				map.put("targetplatformUserNo", LoanMember.getThirdPayFlag0().toString());
				String[] returnret = {};
				if("mobile".equals(mobile)){
					returnret=yeePayService.mobilefiancialTransfer(map,"mobile");
				}else{
					returnret=yeePayService.fiancialTransfer(map);
				}
				
				 if(returnret[0].equals(Constants.CODE_SUCCESS)){
					 ret[0]=returnret[0];
					 ret[1]=returnret[1];
				 }else{
					 ret[0]=returnret[0];
					 ret[1]="投标失败："+returnret[1];
				 }
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="投资人未开通第三方在支付";
			}
		}*/
		return ret;
	}
	
	/**
	 * 服务计划协议
	 * @return
	 */
	public String planContract(){
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/plan/planContract.ftl");
		return "freemarker";
	}
	
	/**
	 * 风险提示书
	 * @return
	 */
	public String riskContract(){
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/plan/riskContract.ftl");
		return "freemarker";
	}
	
	public String buyInfoContract(){
		PlManageMoneyPlanBuyinfo pbi=null;
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(mem!=null){
				String orderId=this.getRequest().getParameter("orderId");
				pbi=plManageMoneyPlanBuyinfoService.get(Long.valueOf(orderId));
				if(!pbi.getInvestPersonId().equals(mem.getId())){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
					return "freemarker";
				}
				this.getRequest().setAttribute("pbi", pbi);
				BpCustMember bpCustMember=bpCustMemberService.get(pbi.getInvestPersonId());
				this.getRequest().setAttribute("bpCustMember", bpCustMember);
				this.getRequest().setAttribute("plan", pbi.getPlManageMoneyPlan());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				return "freemarker";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("UPlan".equals(pbi.getKeystr())){//U计划
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/plan/buyInfoUContract.ftl");
		}else{
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/bid/plan/buyInfoContract.ftl");
		}
		return "freemarker";
	}
	
	public String downPlanContract(){
		String filename = this.getRequest().getParameter("filename");
	//	 filename="wadContract.pdf";
		String filePath = this.getBasePath()+"/attachFiles/"+filename;
		
	/*	String  filePath = AppUtil.getConfigMap().get("fileURL") + contractUrl;//拼接文件在远程服务器上的路径
//		String  filePath = "http://localhost:8002/hurong_p2p_proj_bj_wadzx/attachFiles/projFile/contfolder/jzww_140901_002/363/caiqing120-1.pdf";
*/		System.out.println("文件下载："+filePath);
		HttpServletResponse response=this.getResponse();
		try {
	            File file = new File(filePath);
	            String  fileName = file.getName();
	            															
	            response.setContentType("application/octet-stream");
	            response.reset();//清除缓冲中的数据
	            response.addHeader("Content-Disposition", "attachment;filename="+fileName);
	            																			
	           
	            HttpURLConnection httpConn = null;
	           
	        	remoteFile = new URL(filePath);//建立远程连接
	            httpConn = (HttpURLConnection)remoteFile.openConnection();  //打开连接
	            
	            httpConn.setRequestMethod("GET");
	            httpConn.setConnectTimeout(1000 * 1000);//设置下载连接时间
	            InputStream inStream = httpConn.getInputStream();//通过输入流获取图片数据流
	            
	            
	            byte data[];//声明字节
				try {
					data = readInputStream(inStream);
					inStream.read(data);  //读数据
					inStream.close();
					OutputStream os = response.getOutputStream();
					os.write(data);
					os.flush();
				    os.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
		       } catch (Exception e) {
					e.printStackTrace();
		       }
		       return null;
		
	}

	//主要用于流的转换
	public static byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        byte[] buffer = new byte[2048];    
        int len = 0;    
        while( (len=inStream.read(buffer)) != -1 ){    
            outStream.write(buffer, 0, len);    
        }    
        inStream.close();    
        return outStream.toByteArray();    
	}

	public void setCurentInte(BigDecimal curentInte) {
		this.curentInte = curentInte;
	}

	public BigDecimal getCurentInte() {
		return curentInte;
	}  
	
	//获取体验标信息
	public String getExperienceDetail(){
		String mmplanId = getRequest().getParameter("mmplanId");
		String isMobile = getRequest().getParameter("isMobile");
		if(null !=mmplanId && !"".equals(mmplanId)){
			plmmExperience=plManageMoneyPlanService.get(Long.valueOf(mmplanId));
			plManageMoneyPlanService.setDetail(plmmExperience);
			
			String sql="SELECT * from pl_managemoneyplan_buyinfo as info " +
					"where info.state in(1,2,10) AND info.keystr='experience' AND info.persionType=0 AND info.mmplanId="+mmplanId;
			List<PlManageMoneyPlanBuyinfo> plmmInfo=plManageMoneyPlanBuyinfoService.getManagePlanBuyInfo(sql);
			if(isMobile!=null){
				StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"data\":");
				JSONSerializer json = JsonUtil.getJSONSerializer("bidtime");
				buff.append(json.serialize(plmmExperience));
				buff.append("}");
				jsonString = buff.toString();
				System.out.println("jsonString===="+jsonString);
				return SUCCESS;
			}
			this.getRequest().setAttribute("infoList", plmmInfo);
		}
	    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.EXPERIENCE_CONTENT).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 体验标投标方法
	 * @return
	 */
	public String buyExperienceplan(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		String isMobile = this.getRequest().getParameter("isMobile");
		if(isMobile!=null&&"1".endsWith(isMobile)){
			forwardPage=DynamicConfig.MOBILE_MESSAGE;
		}
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
		if((Boolean) usercondition[0]){
			//返回值
			String[] ret=new String[2];
			//订单号
			String	orderNum=ContextUtil.createRuestNumber();
			System.out.println("orderNum=="+orderNum);
			try{
				String mmplanId=this.getRequest().getParameter("plBidInfo.bidId");
				String couponId=this.getRequest().getParameter("bpCoupons.couponId");
				//每个体验标每个投资人只能投一次
				BigInteger sign = plManageMoneyPlanBuyinfoSevice.countExperience(Long.valueOf(mmplanId),mem.getId());
				if(!"0".equals(sign.toString())){
					webMsgInstance("0", Constants.CODE_FAILED, "此产品针对于未购买过的用户",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					return "freemarker";
				}
				
				String [] checkCondition=this.IsCheckBuyExperience(this.getRequest());
				if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_SUCCESS)){
	    			//查询余额
						PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
						BpCoupons coupons=bpCouponsService.get(Long.valueOf(couponId));
						if(plManageMoneyPlan.getBuyStartTime().after(new Date())){
							webMsgInstance("0", Constants.CODE_FAILED, "此产品为预售产品，请耐心等待",  "", "", "", "", "");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
							return "freemarker";
						}
						PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo =new PlManageMoneyPlanBuyinfo();
						plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
						plManageMoneyPlanBuyinfo.setBuyMoney(coupons.getCouponValue());//购买金额
						plManageMoneyPlanBuyinfo.setCouponId(coupons.getCouponId());//体验券Id
						plManageMoneyPlanBuyinfo.setCouponsMoney(coupons.getCouponValue());//体验券金额
						//投资比例
						plManageMoneyPlanBuyinfo.setInvestmentProportion(plManageMoneyPlanBuyinfo.getBuyMoney().multiply(new BigDecimal(100)).divide(plManageMoneyPlan.getSumMoney(),2,BigDecimal.ROUND_HALF_UP));
						plManageMoneyPlanBuyinfo.setBuyDatetime(new Date());
						plManageMoneyPlanBuyinfo.setPersionType(PlManageMoneyPlanBuyinfo.P_TYPE0);
						plManageMoneyPlanBuyinfo.setCurrentGetedMoney(new BigDecimal("0"));
						plManageMoneyPlanBuyinfo.setPromisDayRate(plManageMoneyPlan.getDayRate());//日化利率
						plManageMoneyPlanBuyinfo.setPromisMonthRate(plManageMoneyPlan.getMonthRate());//月化利率
						plManageMoneyPlanBuyinfo.setPromisYearRate(plManageMoneyPlan.getYeaRate());//年化利率
						plManageMoneyPlanBuyinfo.setMmName(plManageMoneyPlan.getMmName());
						plManageMoneyPlanBuyinfo.setOrderlimit(plManageMoneyPlan.getInvestlimit());//期限
						//承诺总收益
						BigDecimal ProIncomSum=plManageMoneyPlanBuyinfo.getBuyMoney().multiply(plManageMoneyPlanBuyinfo.getPromisDayRate()).multiply(new BigDecimal(plManageMoneyPlanBuyinfo.getOrderlimit())).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP);
						plManageMoneyPlanBuyinfo.setPromisIncomeSum(ProIncomSum);
						plManageMoneyPlanBuyinfo.setKeystr("experience");
						
						plManageMoneyPlanBuyinfo.setDealInfoNumber(orderNum);
						plManageMoneyPlanBuyinfo.setInvestPersonId(mem.getId());
						//插入对应的理财计划id
						plManageMoneyPlanBuyinfo.setMmplanId(Long.valueOf(mmplanId));
						plManageMoneyPlanBuyinfo.setInvestPersonName(mem.getLoginname());
						plManageMoneyPlanBuyinfo.setState((short)1);
						plManageMoneyPlanBuyinfoSevice.save(plManageMoneyPlanBuyinfo);
						
					
						plManageMoneyPlan.setInvestedMoney(plManageMoneyPlan.getInvestedMoney().add(plManageMoneyPlanBuyinfo.getBuyMoney()));
						plManageMoneyPlanBuyinfo.setPlManageMoneyPlan(plManageMoneyPlan);
				    	plManageMoneyPlanBuyinfoService.save(plManageMoneyPlanBuyinfo);
				    	
				    	//修改优惠券状态
				    	coupons.setCouponStatus(BpCoupons.COUPONSTATUS1);
				    	bpCouponsService.save(coupons);
				    	
				    	if(plManageMoneyPlan.getSumMoney().compareTo(plManageMoneyPlan.getInvestedMoney())==0){
							plManageMoneyPlan.setState(plManageMoneyPlan.STATE2);
						}
				    	plManageMoneyPlanService.merge(plManageMoneyPlan);
				    	webMsgInstance("0", Constants.CODE_SUCCESS, "投标成功",  "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
				}else if(checkCondition!=null&&checkCondition[0].equals(Constants.CODE_FAILED)){
					webMsgInstance("0", Constants.CODE_FAILED, checkCondition[1],  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "系统错误投标失败",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
				}
				
			}catch(Exception e){
				e.printStackTrace();
				webMsgInstance("0", Constants.CODE_FAILED, "投资失败:系统出现错误",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
		}else{
			forwardPage=usercondition[2].toString();
			webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
		
	}
	
	
	
	
	
	/**
	 * 检查是否符合投资情况
	 * @return
	 */
	public String[] IsCheckBuyExperience(HttpServletRequest request){
		try{
			String[] ret=new String[2];
			String mmplanId=this.getRequest().getParameter("plBidInfo.bidId");
			String couponId=this.getRequest().getParameter("bpCoupons.couponId");
			if(mmplanId!=null&&!"".equals(mmplanId)){
				PlManageMoneyPlan plManageMoneyPlan =plManageMoneyPlanService.get(Long.valueOf(mmplanId));
				if(plManageMoneyPlan!=null){
					if(couponId!=null&&!"".equals(couponId)){
						BpCoupons coupons=bpCouponsService.get(Long.valueOf(couponId));
						if(coupons !=null){
							if(plManageMoneyPlan.getStartMoney()!=null&&plManageMoneyPlan.getLimitMoney()!=null){//判断理财产品数据完整性
								if(coupons.getCouponValue().compareTo(plManageMoneyPlan.getStartMoney())>-1 && coupons.getCouponValue().compareTo(plManageMoneyPlan.getLimitMoney())<1){
									BigDecimal afterMoney=plManageMoneyPlan.getSumMoney().subtract(plManageMoneyPlan.getInvestedMoney());
									if(coupons.getCouponValue().compareTo(afterMoney)<1){
										BigDecimal amoney=coupons.getCouponValue().subtract(plManageMoneyPlan.getStartMoney());
										if(amoney.compareTo(new BigDecimal(0))>=0){
											ret[0]=Constants.CODE_SUCCESS;
											ret[1]="具备购买资格";
										}else{
											ret[0]=Constants.CODE_FAILED;
											ret[1]="不具备购买资格";
										}
									}else{
										ret[0]=Constants.CODE_FAILED;
										ret[1]="购买金额必须位于起始金额和最大投资额范围内";
										setJsonString("{\"success\":false,\"msg\":\"购买金额大于剩余金额\"}");
									}
								}else{
									ret[0]=Constants.CODE_FAILED;
									ret[1]="购买金额必须位于起始金额和最大投资额范围内";
								}
							}else{
								ret[0]=Constants.CODE_FAILED;
								ret[1]="理财计划的基础必填数据不全面";
							}
						}else{
							ret[0]=Constants.CODE_FAILED;
							ret[1]="没有找到体验券";
						}
					}else{
						ret[0]=Constants.CODE_FAILED;
						ret[1]="没有获得体验券标识";
					}
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="没有找到体验标";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="没有获得体验标标识";
			}
					
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private BpCustMember commoon(BpCustMember mem){
		//bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
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
		return bpCustMember;
	}

	public PlManageMoneyPlan getPlmmExperience() {
		return plmmExperience;
	}

	public void setPlmmExperience(PlManageMoneyPlan plmmExperience) {
		this.plmmExperience = plmmExperience;
	}

	public List<PlManageMoneyType> getListMoneyType() {
		return listMoneyType;
	}

	public void setListMoneyType(List<PlManageMoneyType> listMoneyType) {
		this.listMoneyType = listMoneyType;
	}

	public List<PlMmOrderChildrenOr> getOrderChildrenList() {
		return orderChildrenList;
	}

	public void setOrderChildrenList(List<PlMmOrderChildrenOr> orderChildrenList) {
		this.orderChildrenList = orderChildrenList;
	}
	
}
