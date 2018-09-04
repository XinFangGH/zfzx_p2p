package com.hurong.credit.action.creditFlow.financingAgency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.*;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.config.Pager.OrderType;
import com.hurong.credit.dao.creditFlow.customer.enterprise.EnterpriseDao;
import com.hurong.credit.dao.creditFlow.multiLevelDic.AreaDicDao;
import com.hurong.credit.dao.system.product.DictionaryDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.model.creditFlow.customer.person.CsPersonCar;
import com.hurong.credit.model.creditFlow.customer.person.CsPersonHouse;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.*;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepProtype;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.creditFlow.FileForm.FileFormService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.customer.person.CsPersonCarService;
import com.hurong.credit.service.creditFlow.customer.person.CsPersonHouseService;
import com.hurong.credit.service.creditFlow.customer.person.PersonService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.SlFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidSaleService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessOrProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.PlBusinessDirProKeepService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionOrProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.PlPersionDirProKeepService;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlBiddingTypeService;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlKeepProtypeService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderChildrenOrService;
import com.hurong.credit.service.idcard.IdCardService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.system.product.DictionaryService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.CsDicAreaDynamService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author
 *
 */
public class PlBidPlanAction extends BaseAction {
	@Resource
	private IdCardService idCardService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private PlBusinessDirProKeepService plBusinessDirProKeepService;
	@Resource
	private PlPersionDirProKeepService plPersionDirProKeepService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	@Resource
	private CsPersonHouseService csPersonHouseService;
	@Resource
	private CsPersonCarService csPersonCarService;
	@Resource
	private PersonService personService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ArticleService articleService;
	private List<Article> listArticle;//新闻分类列表
	private BpCustMember custMem;
	@Resource
	private BpBusinessDirProService bpBusinessDirProService;
	@Resource
	private PlBiddingTypeService plBiddingTypeService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private PlManageMoneyPlanService plManageMoneyPlanService;
	@Resource
	private SlFundIntentService slFundIntentService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
    private EnterpriseDao enterpriseDao;
	@Resource
    private AreaDicDao areaDicDao;

	@Override
	public List<Article> getArticleNew() {
		return super.getArticleNew();
	}

	@Resource
    private DictionaryDao dictionaryDao;
	@Resource
	private PlBidSaleService plBidSaleService;
	@Resource
	private PlMmOrderChildrenOrService plMmOrderChildrenOrService;
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	private static final int PUBLISHSTAT = 1; // 已经发布
	private PlBidPlan plBidPlan;
	private PlBidPlanFilter plBidPlanFilter;
	private List<PlBiddingType> bidtypeList;
	private List<WebFinanceApplyUploads> applyUploadList;
	private Enterprise enterPrise;

	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();

	private String bidListType;//招标类型标示：dir:散列标，or：转让标
	//借款人款项台帐
	private List<BpFundIntent> slFundList = new ArrayList<BpFundIntent>();

	@Resource
	private PlKeepProtypeService  plKeepProtypeService;
	@Resource
	private BpBusinessOrProService bpBusinessOrProService;
	@Resource
	private BpPersionDirProService bpPersionDirProService;
	@Resource
	private BpPersionOrProService bpPersionOrProService;
	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;
	@Resource
	private BpFundProjectService bpFundProjectService;
	@Resource
	private BpFundIntentService bpFundIntentService;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private WebFinanceApplyUploadsService webFinanceApplyUploadsService;
	@Resource
	private CsDicAreaDynamService csDicAreaDynamService;

	private String proDes;
    private String investEnterpriseEnterprisename;

	public String getProDes() {
		return proDes;
	}

	public void setProDes(String proDes) {
		this.proDes = proDes;
	}

	public String getInvestEnterpriseEnterprisename() {
		return investEnterpriseEnterprisename;
	}

	public void setInvestEnterpriseEnterprisename(
			String investEnterpriseEnterprisename) {
		this.investEnterpriseEnterprisename = investEnterpriseEnterprisename;
	}

	//检查是否重复提交
	private String formtoken="";




	public BpCustMember getCustMem() {
		return custMem;
	}

	public void setCustMem(BpCustMember custMem) {
		this.custMem = custMem;
	}

	public List<WebFinanceApplyUploads> getApplyUploadList() {
		return applyUploadList;
	}

	public void setApplyUploadList(List<WebFinanceApplyUploads> applyUploadList) {
		this.applyUploadList = applyUploadList;
	}

	public String getFormtoken() {
		return formtoken;
	}

	public void setFormtoken(String formtoken) {
		this.formtoken = formtoken;
	}

	private PlManageMoneyPlan bybMoneyPlan;//白翼宝
	private String planType;
	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	private Object planPro;

	private Object planKeep;

	private PlBidPlan plan;

	private List<PlManageMoneyPlan> listMoneyPlan;//固定列投资列表

	private List<PlKeepProtype> businessTypeList;

	private List<PlWebShowMaterials> listMaterials;

	private List<PlBidInfo> listPlBid;

	private PlBidSale plBidSale;

	public List<PlBidInfo> getListPlBid() {
		return listPlBid;
	}

	public List<PlWebShowMaterials> getListMaterials() {
		return listMaterials;
	}

	public void setListMaterials(List<PlWebShowMaterials> listMaterials) {
		this.listMaterials = listMaterials;
	}

	public List<BpFundIntent> getSlFundList() {
		return slFundList;
	}

	public void setSlFundList(List<BpFundIntent> slFundList) {
		this.slFundList = slFundList;
	}

	public List<PlKeepProtype> getBusinessTypeList() {
		return businessTypeList;
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

	public void setBusinessTypeList(List<PlKeepProtype> businessTypeList) {
		this.businessTypeList = businessTypeList;
	}

	public List<PlBiddingType> getBidtypeList() {
		return bidtypeList;
	}

	public String getBidListType() {
		return bidListType;
	}

	public void setBidListType(String bidListType) {
		this.bidListType = bidListType;
	}

	public void setBidtypeList(List<PlBiddingType> bidtypeList) {
		this.bidtypeList = bidtypeList;
	}

	public PlBidPlanFilter getPlBidPlanFilter() {
		return plBidPlanFilter;
	}

	public void setPlBidPlanFilter(PlBidPlanFilter plBidPlanFilter) {
		this.plBidPlanFilter = plBidPlanFilter;
	}

	private Long bidId;

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public PlBidPlan getPlBidPlan() {
		return plBidPlan;
	}

	public void setPlBidPlan(PlBidPlan plBidPlan) {
		this.plBidPlan = plBidPlan;
	}

	public Object getPlanPro() {
		return planPro;
	}

	public void setPlanPro(Object planPro) {
		this.planPro = planPro;
	}

	public Object getPlanKeep() {
		return planKeep;
	}

	public void setPlanKeep(Object planKeep) {
		this.planKeep = planKeep;
	}

	public PlBidPlan getPlan() {
		return plan;
	}

	public void setPlan(PlBidPlan plan) {
		this.plan = plan;
	}
	/**
	 * 新 散标列表查询
	 * @return
	 */
	public String newPlanList(){
		//request.setCharacterEncoding("utf-8");

		this.getSession().setAttribute("dh", 2);
		this.getSession().setAttribute("planType", "Dir");
		String mobile = this.getRequest().getParameter("mobile");
		String isMobile=this.getRequest().getParameter("isMobile");

		//限时加息
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Long time = null;
		try {
			time = s.parse("2018-10-01").getTime()- new Date().getTime();
			this.getRequest().setAttribute("time", time);
		} catch (Exception e) {
			e.printStackTrace();
		}

		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        planType="Dir";
        //生成页面上的查询条件
        pageParams();
        //生成查询条件
		//查询最新的一个体验标
		getExperience();
		//生成查询条件
		if(plBidPlanFilter==null){
			plBidPlanFilter=new PlBidPlanFilter();
		}
		//
        plBidPlanFilter=createFilter(plBidPlanFilter);

        QueryFilter filter = new QueryFilter(getRequest());
        if (pager == null) {
            pager = new Pager();
        }
		System.out.println(plBidPlanFilter.getProKeepType()+"    标的类型");
		PagingBean pb=new PagingBean(pager.getPageNumber(),pager.getPageSize());
		List<PlBidPlan> planList = plBidPlanService.getNewPlanList(pb,this.getRequest());
		List<PlBidPlan> listSize = plBidPlanService.getNewPlanList(null,this.getRequest());
		String bidProName = this.getRequest().getParameter("bidProName");
		this.getRequest().setAttribute("bidProName",bidProName);
		for (PlBidPlan plan : planList) {
			plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			plBidPlanService.setProperty(plan,this.getRequest());
			if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
				plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
			}else{
				plan.setPreSaleTimeStr("1994-10-16 00:00:00");
			}
		}
		pager.setTotalCount(listSize.size());
		pager.setList(planList);
		return "freemarker";
	}
	/**
	 * 显示列表
	 */
	public String list() {
		this.getSession().setAttribute("dh", 2);
		String mobile = this.getRequest().getParameter("mobile");
		String isMobile=this.getRequest().getParameter("isMobile");
		//限时加息
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Long time = null;
		try {
			time = s.parse("2018-10-01").getTime()- new Date().getTime();
			System.out.println(time+"毫秒值ttttttttttttttttttt");
			this.getRequest().setAttribute("time", time);
		} catch (Exception e) {
			e.printStackTrace();
		}

		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		planType="Dir";
		//生成页面上的查询条件
		pageParams();
		//生成查询条件
		if(plBidPlanFilter==null){
			plBidPlanFilter=new PlBidPlanFilter();
		}
		plBidPlanFilter=createFilter(plBidPlanFilter);
		//pager 过滤条件
		QueryFilter filter = new QueryFilter(getRequest());
		if (pager == null) {
			pager = new Pager();
		}
		if(null!=isMobile&&isMobile.endsWith("1")){
			String page=this.getRequest().getParameter("page");
			String limit=this.getRequest().getParameter("limit");
			pager = new Pager();
			pager.setPageNumber(Integer.valueOf(page));
			pager.setPageSize(Integer.valueOf(limit));
		}
		if(this.getRequest().getParameter("pageSize")!=null&&!this.getRequest().getParameter("pageSize").equals("")){
			pager.setPageSize(Integer.valueOf(StringUtil.html2Text(this.getRequest().getParameter("pageSize"))));
		}
		pager.setOrderBy("createtime");

		//手机端逻辑
		//生成查询的filter
		filter=createFilter(filter,mobile);
		filter.addFilter("Q_isVip_SN_EQ",(short)0);
		String pkType = getRequest().getParameter("proKeepType");
		if(pkType!=null){
			PlKeepProtype type = plKeepProtypeService.getByKeyStr(pkType);
			if(type!=null)
				filter.addFilter("Q_proKeepType_S_EQ", type.getName());
		}
		List<PlBidPlan> list = plBidPlanService.getAll(filter);
		List<PlBidPlan> currList = new ArrayList<PlBidPlan>();
		for (PlBidPlan plan : list) {
			plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
				plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
			}else{
				plan.setPreSaleTimeStr("1994-10-16 00:00:00");
			}
			plBidPlanService.setProperty(plan, this.getRequest());
			plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
			plBidPlanService.returnImgUrl(plan);//查询标的LogoURL
			currList.add(plan);
		}

		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(currList);
		//查询最新的一个体验标
		List<PlManageMoneyPlan> listPlanArray = getExperience();

		if(null!=isMobile&&isMobile.endsWith("2")){
			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
			.append(listPlanArray.size()).append(",\"result\":");
			JSONSerializer json = JsonUtil.getJSONSerializer("intentDate",
					"factDate", "interestStarTime", "interestEndTime");
			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"intentDate", "factDate", "interestStarTime",
			"interestEndTime" });
			buff.append(json.serialize(listPlanArray));
			buff.append("}");
			jsonString = buff.toString();
			return SUCCESS;
		}
		if(mobile!=null&&"mobile".equals(mobile)){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEBIDPLAN).getTemplateFilePath());
			return "bidPlan";
		}else if(mobile!=null && mobile.equals("mobileIndex")){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEINDEX).getTemplateFilePath());
			return "bidPlan";
		}

		if(null!=isMobile&&isMobile.endsWith("1")){
			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
			.append(pager.getTotalCount()).append(",\"result\":");
			JSONSerializer json = JsonUtil.getJSONSerializer("intentDate",
					"factDate", "interestStarTime", "interestEndTime");
			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
				"intentDate", "factDate", "interestStarTime",
			"interestEndTime" });
			buff.append(json.serialize(pager.getList()));
			buff.append("}");
			jsonString = buff.toString();
			return SUCCESS;
		}

		return "freemarker";
	}


	/**
	 * 显示列表-----VIP
	 */
	public String listVip() {
		this.getSession().setAttribute("dh", 2);
		String mobile = this.getRequest().getParameter("mobile");
		String isMobile=this.getRequest().getParameter("isMobile");
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			bpCustMember = bpCustMemberService.get(bpCustMember.getId());
			if(bpCustMember.getIsVip()!=null&&bpCustMember.getIsVip()==1){
				planType="Dir";
				//生成页面上的查询条件
				pageParams();
				//生成查询条件
				if(plBidPlanFilter==null)
				{
					plBidPlanFilter=new PlBidPlanFilter();
				}

				plBidPlanFilter=createFilter(plBidPlanFilter);

				//pager 过滤条件
				QueryFilter filter = new QueryFilter(getRequest());
				if (pager == null) {
					pager = new Pager();
				}
				if(null!=isMobile&&isMobile.endsWith("1")){
					String page=this.getRequest().getParameter("page");
					String limit=this.getRequest().getParameter("limit");
					pager = new Pager();
					pager.setPageNumber(Integer.valueOf(page));
					pager.setPageSize(Integer.valueOf(limit));
				}
				if(this.getRequest().getParameter("pageSize")!=null&&!this.getRequest().getParameter("pageSize").equals("")){
					pager.setPageSize(Integer.valueOf(StringUtil.html2Text(this.getRequest().getParameter("pageSize"))));
				}

				//手机端逻辑
				//生成查询的filter
				filter=createFilter(filter,mobile);
				filter.addFilter("Q_isVip_SN_EQ",(short)1);
				String pkType = getRequest().getParameter("proKeepType");
				if(pkType!=null){
					PlKeepProtype type = plKeepProtypeService.getByKeyStr(pkType);
					if(type!=null)
						filter.addFilter("Q_proKeepType_S_EQ", type.getName());
				}
				List<PlBidPlan> list = plBidPlanService.getAll(filter);
				List<PlBidPlan> currList = new ArrayList<PlBidPlan>();
				for (PlBidPlan plan : list) {
					plan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					if(plan.getPublishSingeTime()!=null&&!"".equals(plan.getPublishSingeTime())){
						plan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plan.getPublishSingeTime()));
					}else{
						plan.setPreSaleTimeStr("1994-10-16 00:00:00");
					}
					plBidPlanService.setProperty(plan, this.getRequest());
					plBidPlanService.returnPlBidPlan(plan);//获取标的实体类
					plBidPlanService.returnImgUrl(plan);//查询标的LogoURL
					currList.add(plan);
				}

				pager.setTotalCount(filter.getPagingBean().getTotalItems());
				pager.setList(currList);
				//查询最新的一个体验标
				List<PlManageMoneyPlan> listPlanArray = getExperience();

				if(null!=isMobile&&isMobile.endsWith("2")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
					.append(listPlanArray.size()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("intentDate",
							"factDate", "interestStarTime", "interestEndTime");
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
						"intentDate", "factDate", "interestStarTime",
					"interestEndTime" });
					buff.append(json.serialize(listPlanArray));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				}
				if(mobile!=null&&"mobile".equals(mobile)){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEBIDPLAN).getTemplateFilePath());
					return "bidPlan";
				}else if(mobile!=null && mobile.equals("mobileIndex")){
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEINDEX).getTemplateFilePath());
					return "bidPlan";
				}

				if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
					.append(pager.getTotalCount()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("intentDate",
							"factDate", "interestStarTime", "interestEndTime");
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
						"intentDate", "factDate", "interestStarTime",
					"interestEndTime" });
					buff.append(json.serialize(pager.getList()));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BID_LIST_VIP).getTemplateFilePath());
			}else{
				webMsgInstance("0", Constants.CODE_FAILED,"您还不是VIP用户，无法查看",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}else{
			webMsgInstance("0", Constants.CODE_FAILED,"请先登录",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "bidPlan";
	}

	/**
	 * 查询最新的一个体验标
	 */
	public List<PlManageMoneyPlan> getExperience(){
		//体验标 start
		QueryFilter planfilter = new QueryFilter();
		planfilter.addFilter("Q_keystr_S_EQ", "experience");
		planfilter.addFilterIn("Q_state_N_IN",Arrays.asList(1));
		planfilter.addSorted("state", "ASC");
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		planfilter.addFilter("Q_buyEndTime_D_GT",sd.format(new Date()));
		List<PlManageMoneyPlan> listPlan=plManageMoneyPlanService.getAll(planfilter);
		List<PlManageMoneyPlan> listPlanArray=new ArrayList<PlManageMoneyPlan>();
		if(null !=listPlan && !"".equals(listPlan) && listPlan.size()>0){
			//只查询出一个最新的
			listPlan.get(0).setNowDate(sd.format(new Date()));
			listPlan.get(0).setStartTime(sd.format(listPlan.get(0).getBuyStartTime()));
			listPlanArray.add(plManageMoneyPlanService.setDetail(listPlan.get(0)));
		}
		this.getRequest().setAttribute("listPlan",listPlanArray);
		return listPlanArray;
	}
	private void getPlBidDir(){
		//生成页面上的查询条件
		pageParams();
		//生成查询条件
		if(plBidPlanFilter==null)
		{
			plBidPlanFilter=new PlBidPlanFilter();
		}

		plBidPlanFilter=createFilter(plBidPlanFilter);
		//pager 过滤条件
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_proType_S_LK", "Dir");
		if (dirListPager == null) {
			dirListPager = new Pager();

		}
		if(this.getRequest().getParameter("pageSize")!=null&&!this.getRequest().getParameter("pageSize").equals("")){
			dirListPager.setPageSize(Integer.valueOf(StringUtil.html2Text(this.getRequest().getParameter("pageSize"))));
		}
		//生成查询的filter
		filter=createFilter(filter,null);
		String pkType = getRequest().getParameter("proKeepType");
		if(pkType!=null){
			PlKeepProtype type = plKeepProtypeService.getByKeyStr(pkType);
			if(type!=null)
				filter.addFilter("Q_proKeepType_S_EQ", type.getName());
		}
		List<PlBidPlan> list = plBidPlanService.getAll(filter);
		List<PlBidPlan> currList = new ArrayList<PlBidPlan>();
		for (PlBidPlan plan : list) {
			plBidPlanService.setProperty(plan, this.getRequest());
			plBidPlanService.returnPlBidPlan(plan);//获取标的实体类

			currList.add(plan);
		}
		dirListPager.setTotalCount(filter.getPagingBean().getTotalItems());
		dirListPager.setList(currList);
	}
	private void getPlBidOr(){
		//生成页面上的查询条件
		pageParams();
		//生成查询条件
		if(plBidPlanFilter==null)
		{
			plBidPlanFilter=new PlBidPlanFilter();
		}

		plBidPlanFilter=createFilter(plBidPlanFilter);
		//pager 过滤条件
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_proType_S_LK", "Or");
		if (orListPager == null) {
			orListPager = new Pager();

		}
		if(this.getRequest().getParameter("pageSize")!=null&&!this.getRequest().getParameter("pageSize").equals("")){
			orListPager.setPageSize(Integer.valueOf(StringUtil.html2Text(this.getRequest().getParameter("pageSize"))));
		}
		//生成查询的filter
		filter=createFilter(filter,null);
		String pkType = getRequest().getParameter("proKeepType");
		if(pkType!=null){
			PlKeepProtype type = plKeepProtypeService.getByKeyStr(pkType);
			if(type!=null)
				filter.addFilter("Q_proKeepType_S_EQ", type.getName());
		}
		List<PlBidPlan> list = plBidPlanService.getAll(filter);
		List<PlBidPlan> currList = new ArrayList<PlBidPlan>();
		for (PlBidPlan plan : list) {
			plBidPlanService.setProperty(plan, this.getRequest());
			plBidPlanService.returnPlBidPlan(plan);//获取标的实体类

			currList.add(plan);
		}
		orListPager.setTotalCount(filter.getPagingBean().getTotalItems());
		orListPager.setList(currList);
	}

	public String getLoanMonth(Date startDate,Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<Integer,Integer> map = DateUtil.getMonthAndDaysBetweenDate(sdf.format(startDate), sdf.format(endDate));
		if(map!=null){
			Integer month = map.get(1);
			return month+"";
		}
		return null;
	}
	/**
	 * 显示空页面
	 * @return
	 */
	public String showList(){
		System.out.println("-----pass action------");
		return "showNull";
	}
	/**
	 * 体验标详情页
	 * @return
	 */
	public String experienceBidContent(){
		System.out.println("体验标详情页");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.EXPERIENCEBIDCONTENT).getTemplateFilePath());
	return "bidPlan";

	}

	/**
	 * 返回页面上输出的 动态 查询条件
	 */
	private void pageParams(){
		//标类型
		//QueryFilter filter0=new QueryFilter(this.getRequest());
		//filter0.addFilter("Q_biddingTypeId_L_LT", "3");
		bidtypeList=plBiddingTypeService.getAll();

		//标的业务类别
		businessTypeList=plKeepProtypeService.getAll();
	}
	/**
	 * 创建 filter
	 * @param filter
	 * @return filter
	 */
	private QueryFilter  createFilter(QueryFilter filter,String mobile){
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		if("mobileIndex".equals(mobile)||"mobile".equals(mobile)){
			filter.addFilterIn("Q_state_N_IN",Arrays.asList(1,2,3,6,7,10));
		}else{
			filter.addFilterIn("Q_state_N_IN",Arrays.asList(1,2,3,4,5,6,7,10));
		}
		filter.addFilter("Q_publishSingeTime_D_NOTNULL", DateUtil.dateToStr(new Date(), "yyyy-MM-dd"));
		//查询 借款期限
		String payTime=StringUtil.html2Text(this.getRequest().getParameter("payTime"));
		if(payTime!=null&&!payTime.equals("")){
			String[] s=payTime.split("-");
			if(Long.valueOf(s[0]) == 0){
				//当选择的借款期限为1个月以下时，查询出所有借款期限为天的
				filter.addFilter("Q_payMoneyTimeType_s_EQ", "dayPay");
				filter.addFilter("Q_payMoneyTime_N_GE", 0);
				filter.addFilter("Q_payMoneyTime_N_LE", 30);
			}else{
				//当选择的借款期限为3个月以上时，查询出所有期限为月的
				filter.addFilter("Q_payMoneyTimeType_s_EQ", "monthPay");
				filter.addFilter("Q_payMoneyTime_N_GE", s[0]);
				filter.addFilter("Q_payMoneyTime_N_LE", s[1]);
			}
		}

		//查询 年化利率
		String yearInterestRate = StringUtil.html2Text(this.getRequest().getParameter("yearInterestRate"));
		if(null != yearInterestRate && !"".equals(yearInterestRate)){
			String[] s=yearInterestRate.split("-");
			filter.addFilter("Q_yearInterestRate_BD_GE", s[0]);
			filter.addFilter("Q_yearInterestRate_BD_LE", s[1]);
		}

	  //查询 招标金额范围
		String bidMoney=StringUtil.html2Text(this.getRequest().getParameter("bidMoney"));
		if(bidMoney!=null&&!bidMoney.equals("")){
			String[] s=bidMoney.split("-");
			filter.addFilter("Q_bidMoney_BD_GE", s[0]);
			filter.addFilter("Q_bidMoney_BD_LE", s[1]);
		}
		//查询 标类型
		String biddingTypeId=StringUtil.html2Text(this.getRequest().getParameter("Q_plBiddingType.biddingTypeId_L_EQ"));
		if(biddingTypeId!=null&&!biddingTypeId.equals("")){
			String[] s=biddingTypeId.split("-");
			filter.addFilter("Q_plBiddingType.biddingTypeId_L_EQ", s[0]);
		}

		//查询标
		if(null!=bidListType&&!"".equals(bidListType)&&bidListType.trim().length()!=0){
			filter.addFilter("Q_proType_S_LK", bidListType);
		}
		// 增加排序值
		if(!"mobileIndex".equals(mobile)||!"mobile".equals(mobile)){
		//	filter.addSorted("publishSingeTime", "desc");
			filter.addSorted("state", "asc");
			//filter.addSorted("updatetime", "DESC");
		}
		//借款类型
		String keepProtypeName=StringUtil.html2Text(this.getRequest().getParameter("keepProtypeName"));
		if(keepProtypeName!=null&&!"".equals(keepProtypeName)){
			if("credit".equals(keepProtypeName)){
				filter.addFilter("Q_proKeepType_S_EQ", "信用审核标");
			}
			if("indeed".equals(keepProtypeName)){
				filter.addFilter("Q_proKeepType_S_EQ", "实地核查标");
			}
			if("assure".equals(keepProtypeName)){
				filter.addFilter("Q_proKeepType_S_EQ", "机构担保标");
			}
			if("walfare".equals(keepProtypeName)){
				filter.addFilter("Q_proKeepType_S_EQ", "福利标");
			}
		}
		String plKeepCreditlevel_name = this.getRequest().getParameter("keepCreditlevelName");
		if(plKeepCreditlevel_name!=null&&!"".equals(plKeepCreditlevel_name)){
			filter.addFilter("Q_keepCreditlevelName_S_EQ", plKeepCreditlevel_name);//标的信用等级
		}

		if("mobileIndex".equals(mobile)||"mobile".equals(mobile)){
			filter.addSorted("state","asc");
		}else{
			filter.addFilter("Q_prepareSellTime_D_LE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//添加标的预售时间查询
		}
		return filter;
	}
	/**
	 * 创建返回的filter
	 * @param plBidPlanFilter
	 * @return
	 */
	private PlBidPlanFilter createFilter(PlBidPlanFilter plBidPlanFilter){
		//付息方式
		String payIntersetWay=StringUtil.html2Text(this.getRequest().getParameter("Q_payIntersetWay_S_EQ"));
		//h还款时间
		String payTime=StringUtil.html2Text(this.getRequest().getParameter("payTime"));
		//招标金额
		String bidMoney=StringUtil.html2Text(this.getRequest().getParameter("bidMoney"));

		//查询 年化利率
		String yearInterestRate = StringUtil.html2Text(this.getRequest().getParameter("yearInterestRate"));

		//业务来源
		String Q_businessFrom_S_EQ=StringUtil.html2Text(this.getRequest().getParameter("Q_businessFrom_S_EQ"));
		//业务类型
		String Q_proKeepType_S_EQ=StringUtil.html2Text(this.getRequest().getParameter("Q_proKeepType_S_EQ"));
		//招标状态
		String Q_state_N_EQ=StringUtil.html2Text(this.getRequest().getParameter("Q_state_N_EQ"));
		//招标类型
		String Q_proType_S_LK = StringUtil.html2Text(this.getRequest().getParameter("bidListType"));

		String proKeepType = null;
		try {
			String keepProtypeName = this.getRequest().getParameter("keepProtypeName");
			if (StringUtils.isNotEmpty(keepProtypeName)) {
				proKeepType = new String(keepProtypeName.getBytes("iso8859-1"),"utf-8");
			}else {
				proKeepType = "";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(proKeepType+"  biaodileixing");
		String keepCreditlevelName= this.getRequest().getParameter("keepCreditlevelName");


		if(payIntersetWay!=null&&!"".equals(payIntersetWay)){
			plBidPlanFilter.setPayIntersetWay(payIntersetWay);
		}

        if(payTime!=null&&!"".equals(payTime)){
			plBidPlanFilter.setPayTime(payTime);
		}
        if(bidMoney!=null&&!"".equals(bidMoney)){
			plBidPlanFilter.setBidMoney(bidMoney);
		}
        if(yearInterestRate!=null&&!"".equals(yearInterestRate)){
			plBidPlanFilter.setYearInterestRate(yearInterestRate);
		}
        if(Q_businessFrom_S_EQ!=null&&!"".equals(Q_businessFrom_S_EQ)){
			plBidPlanFilter.setBusinessFrom(Q_businessFrom_S_EQ);
		}
        if(Q_proKeepType_S_EQ!=null&&"".equals(Q_proKeepType_S_EQ)){
			plBidPlanFilter.setProKeepType(Q_proKeepType_S_EQ);
		}
        if(Q_state_N_EQ!=null&&!"".equals(Q_state_N_EQ)){
			plBidPlanFilter.setBidState(Q_state_N_EQ);
		}
        if(keepCreditlevelName!=null&&!"".equals(keepCreditlevelName)){
        	plBidPlanFilter.setKeepCreditlevelName(keepCreditlevelName);
        }
        if(proKeepType!=null&&!"".equals(proKeepType)){
        	plBidPlanFilter.setProKeepType(proKeepType);
        }
		return plBidPlanFilter;
	}



	/**
	 * 显示详细信息
	 *
	 * @return
	 */
	public String get() {
		PlBidPlan plBidPlan = plBidPlanService.get(bidId);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				"publishSingeTime", "createtime", "updateTime", "bidEndTime");
		sb.append(serializer.exclude(new String[] { "class" }).serialize(
				plBidPlan));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 异步获取标的进度 投标人数 投标金额
	 *
	 * @return
	 */
	public String ajaxBidInfo() {
		Gson gson=new Gson();
		StringBuffer sb = new StringBuffer("{\"success\":true,");
		if(null!=bidId&&!"".equals(bidId)){
		PlBidPlan plBidPlan = plBidPlanService.get(bidId);
		//plBidPlan.setAfterMoney(plBidPlan.getBidMoney());
		String[] info=plBidPlanService.bidDynamic(plBidPlan);
		//从session中取出当前用户
		BpCustMember bpCustMember = (BpCustMember)this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String thirdPayConfig=configMap.get("thirdPayConfig").toString();
		String thirdPayType=configMap.get("thirdPayType").toString();
		if(bpCustMember!=null){
			try {
				if(thirdPayConfig.equals(Constants.FUIOU)&&thirdPayType.equals("0")){
					bpCustMember=fuiouService.getCurrentMoney(bpCustMember);
					bpCustMember.setTotalInvestMoney(new BigDecimal(0));
					bpCustMember.setAllInterest(new BigDecimal(0));
					bpCustMember.setPrincipalRepayment(new BigDecimal(0));
					bpCustMember.setTotalRecharge(new BigDecimal(0));
					bpCustMember.setTotalEnchashment(new BigDecimal(0));
				}else{
					bpCustMember = obSystemAccountService
					.getAccountSumMoney(bpCustMember);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.getRequest().setAttribute("info", info);
		sb.append("\"progress\":");
		sb.append(info[0]+",\"persionNum\":");
		sb.append(info[1]+",\"afterMoney\":");
		sb.append(info[2]+",\"afterTime\":");
		sb.append(info[3]);
		sb.append("}");
		setJsonString(gson.toJson(sb.toString()));
		System.out.println("返回的值="+sb.toString());
		}
		return SUCCESS;
	}


	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (plBidPlan.getBidId() == null) {
			plBidPlan.setCreatetime(new Date());
			plBidPlan.setUpdatetime(new Date());
			plBidPlanService.save(plBidPlan);
		} else {
			PlBidPlan orgPlBidPlan = plBidPlanService.get(plBidPlan.getBidId());
			try {
				BeanUtil.copyNotNullProperties(orgPlBidPlan, plBidPlan);
				orgPlBidPlan.setUpdatetime(new Date());
				plBidPlanService.save(orgPlBidPlan);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	@Resource
	private FileFormService fileFormService;


	public String bidPlanDetail(){
		String type = this.getRequest().getParameter("type");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String mobile = this.getRequest().getParameter("mobile");
		String isMobile = this.getRequest().getParameter("isMobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		if (mem !=null){
			mem = bpCustMemberService.get(mem.getId());
			this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION,mem);
		}
		if(mem==null&&"mobile".equals(mobile)){
			try {
				this.getResponse().sendRedirect("mobileLoginlogin.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "bidPlan";
		}
		try {
			//设置方重复 token
			Object tokenSession=this.getSession().getAttribute(MyUserSession.FORM_TOKEN);
			if(tokenSession!=null&&!tokenSession.equals("")){
				this.getSession().removeAttribute(MyUserSession.FORM_TOKEN);
			}
			this.formtoken=Common.getRandomNum(5)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
			this.getSession().setAttribute(MyUserSession.FORM_TOKEN, formtoken);

			//需要plBidPlan的id获取对象，
			String bidId = getRequest().getParameter("bidId");
			String pid="";
			if(null!=bidId&&!"".equals(bidId)){
				plBidPlan = plBidPlanService.get(Long.parseLong(bidId));
				plBidPlanService.setProperty(plBidPlan, this.getRequest());
				plBidPlan=plBidPlanService.getAfterTime(plBidPlan);

				plBidPlan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plBidPlan.getPublishSingeTime()!=null&&!"".equals(plBidPlan.getPublishSingeTime())){
					plBidPlan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plBidPlan.getPublishSingeTime()));
				}else{
					plBidPlan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}

				//计算满标用时
				if(plBidPlan.getFullTime()!=null){
					plBidPlan.setBidFullTime(DateUtil.dateDiff(plBidPlan.getPublishSingeTime().toString(), plBidPlan.getFullTime().toString(), "yyyy-MM-dd HH:mm:ss", ""));
				}
				if(plBidPlan.getState()==PlBidPlan.STATE7||plBidPlan.getState()==PlBidPlan.STATE5||plBidPlan.getState()==PlBidPlan.STATE6||plBidPlan.getState()==PlBidPlan.STATE10){
					String[] ret=bpFundIntentService.getFundInfoByPlanId(Long.valueOf(bidId), this.getRequest());
					plBidPlan.setNotRepaymentMoney(ret[0]);
					plBidPlan.setRepayMentLength((Integer.valueOf(ret[1]))+"/"+ret[2]);
					plBidPlan.setNextRepaymentDate(ret[3]);
					plBidPlan.setRepaymentFullDate(ret[4]);
				}

				// 获取动态信息 如 投标进度 投标人数 投标剩余金额
				String[] info=plBidPlanService.bidDynamic(plBidPlan);
				plBidPlan.setProgress(Double.valueOf(info[0]));
				plBidPlan.setPersionNum(Integer.valueOf(info[1]));
				plBidPlan.setAfterMoney(Double.valueOf(info[2]));
				if(info[3]!=null&&!"".equals(info[3])){
					plBidPlan.setRemainingTime(Long.valueOf(info[3]));
				}
				//剩余时间，单位是毫秒，用于脚本计算
				findPlanProjInfo(plBidPlan);

				PlBusinessDirProKeep pkpl =null;

				if (plBidPlan.getProType().equals("B_Dir")) {
					pid=plBidPlan.getBpBusinessDirPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString())));
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//项目状态
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessDirPro().getMoneyPlanId());
					//查询债权匹配记录
					if(type!=null&&!"".equals(type)){
						if (pager == null) {
							pager = new Pager();
						}
						PagingBean pb=new PagingBean(pager.getPageNumber(),pager.getPageSize());
						List<PlBidSale> saleList = plBidPlanService.getSaleList(pb, Long.valueOf(bidId));
						List<PlBidSale> saleList1 = plBidPlanService.getSaleList(null, Long.valueOf(bidId));
						pager.setTotalCount(saleList1.size());
						pager.setList(saleList);
						this.getRequest().setAttribute("SignZQ", "1");
					}
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){
								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							Dictionary ss = dictionaryDao.get(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership((ss!=null)?(ss.getItemValue()):"");
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
						PlBusinessDirProKeep pl=plBidPlan.getBpBusinessDirPro().getPlBusinessDirProKeep();
						if(isMobile!=null){
							pkpl=pl;
						}
						this.getRequest().setAttribute("planKeep", pl);
					}
					List<FileForm> fileLIst = fileFormService.getAllEnterpriseImg(enterPrise.getId());
					this.getRequest().setAttribute("fileLIst",fileLIst);
					if(isMobile!=null){

					}else{
						if(plBidPlan.getVersionType() == 1 || plBidPlan.getVersionType() == 0) {
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BDIRBID).getTemplateFilePath());
						}else {
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BDIRBIDV2).getTemplateFilePath());
						}

					}
				} else if (plBidPlan.getProType().equals("B_Or")) {
					pid=plBidPlan.getBpBusinessOrPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessOrPro().getMoneyPlanId());
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){
								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							String ss=dictionaryDao.getQueryDicId(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership(ss);
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
					}
					PlBusinessDirProKeep pl=plBidPlan.getBpBusinessOrPro().getPlBusinessDirProKeep();
					if(isMobile!=null){
							pkpl=pl;
					}else{
						this.getRequest().setAttribute("planKeep", pl);
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BORBID).getTemplateFilePath());
					}


				} else if (plBidPlan.getProType().equals("P_Dir")) {
					pid=plBidPlan.getBpPersionDirPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					if(type!=null&&!"".equals(type)){
						if (pager == null) {
							pager = new Pager();
						}
						PagingBean pb=new PagingBean(pager.getPageNumber(),pager.getPageSize());
						List<PlBidSale> saleList = plBidPlanService.getSaleList(pb, Long.valueOf(bidId));
						List<PlBidSale> saleList1 = plBidPlanService.getSaleList(null, Long.valueOf(bidId));
						pager.setTotalCount(saleList1.size());
						pager.setList(saleList);
						this.getRequest().setAttribute("SignZQ", "1");
					}
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString(),"1")));
					if(isMobile!=null){
					}else{
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PDIRBID).getTemplateFilePath());
						PlPersionDirProKeep pl=plBidPlan.getBpPersionDirPro().getPlPersionDirProKeep();
						this.getRequest().setAttribute("planKeep", pl);
					}

				} else if (plBidPlan.getProType().equals("P_Or")) {
					pid=plBidPlan.getBpPersionOrPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString(),"1")));

					getloanLifes(plBidPlan);

					PlPersionDirProKeep pl=plBidPlan.getBpPersionOrPro().getPlPersionDirProKeep();

					if(isMobile!=null){
					}else{
						this.getRequest().setAttribute("planKeep", pl);
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PORBID).getTemplateFilePath());
					}
				}
				plBidPlan =	plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
				getBpFundIntents(plBidPlan.getBidId().toString());
				listPlBid = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"");//投标记录
				List<PlBidInfo> bondList = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"group");//债券记录
				List<PlBidInfo> bondListBid = new ArrayList<PlBidInfo>();

				// 判断长度是否大于0
				if (bondList.size() > 0) {
					for(int i = 0; i < bondList.size(); i++) {
						// 循环遍历,将每条长度赋给参数
						bondList.get(i).setIndexId(Long.valueOf(i + 1));
						// 合并每条数据的index
						plBidInfoService.merge(bondList.get(i));

						PlBidInfo bidInfo = bondList.get(i);
						plBidInfoService.evict(bidInfo);
						BigDecimal money  = plBidInfoService.getUserMoneyGroup(bidInfo.getBidId(), bidInfo.getUserId());
						bidInfo.setBondTotelMoney(money==null?new BigDecimal(0):money);
						bondListBid.add(bidInfo);
					}
				}

				// 判断长度是否大于0
				/*if (buyInfoList.size() > 0) {
					for (int i = 0; i < buyInfoList.size(); i++) {
						// 循环遍历,将每条长度赋给参数
						bondList.get(i).setIndexId(String.valueOf(i + 1));
						// 合并每条数据的index
						plBidInfoService.merge(bondList.get(i));
					}
				}*/

				this.getRequest().setAttribute("bondListBid",bondListBid );
				plan = plBidPlan;

				//获取贷款材料
				findMaterials(pid,"SmallLoanBusiness");

				//查询债权转让记录
				List<PlBidSale> saleList=plBidSaleService.getByPbidPlanID(plBidPlan.getBidId(),"4");
				this.getRequest().setAttribute("saleList", saleList);

				if(isMobile!=null){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"proDes\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","opendate","bidEndTime","createTime");

					if (plBidPlan.getProType().equals("B_Dir")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("B_Or")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("P_Dir")){
						PlPersionDirProKeep pl=plBidPlan.getBpPersionDirPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else if (plBidPlan.getProType().equals("P_Or")) {
						PlPersionDirProKeep pl=plBidPlan.getBpPersionOrPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else{
						buff.append(json.serialize(""));
					}

					buff.append(",\"data\":");
					buff.append(json.serialize(plan));
					buff.append(",\"pltype\":");
					buff.append(json.serialize(plBidPlan.getProType()));
					buff.append(",\"enterPrise\":");
					buff.append(json.serialize(enterPrise));
					buff.append(",\"listMaterials\":");
					buff.append(json.serialize(listMaterials));
					buff.append(",\"bondListBid\":");
					buff.append(json.serialize(bondListBid));
					buff.append(",\"formtoken\":");
					buff.append(json.serialize(formtoken));
					buff.append(",\"bondListBid\":");
					buff.append(json.serialize(bondListBid));
					buff.append(",\"bondListLength\":");
					buff.append(json.serialize(bondList.size()));
					buff.append("}");
					jsonString = buff.toString();
					System.out.println("jsonString===="+jsonString);
					return SUCCESS;
				}





			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(mobile!=null && mobile.equals("mobile")){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEBIDPLANDETAIL).getTemplateFilePath());
		}else if(mobile!=null && mobile.equals("userInfo")){
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILEUSERINFO).getTemplateFilePath());
		}
		//plan=null;
		if(plan==null){
			webMsgInstance("0", Constants.CODE_FAILED, "页面数据错误",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "bidPlan";
	}
	/**
	 * 标详情页：个人资产信息
	 */
	private void getPersonalInfo(PlBidPlan pbp){
		BpPersionDirPro personDir = pbp.getBpPersionDirPro();
		if(personDir!=null){
			Person person = personService.getJobIncome(personDir.getPersionId().toString());
			plBidPlan.setMonthIncome(person==null?0:person.getJobincome()==null?0:person.getJobincome());//月收入
			List<CsPersonHouse> personHouse = csPersonHouseService.getByPersonId(personDir.getPersionId().toString());
			if(personHouse!=null&&personHouse.size()>0){
				plBidPlan.setHouseLoan("有");
				plBidPlan.setHouseAssets("有");
			}else{
				plBidPlan.setHouseLoan("无");
				plBidPlan.setHouseAssets("无");
			}
			List<CsPersonCar>  personCar = csPersonCarService. getByPersonId(personDir.getPersionId().toString());
			if(personCar!=null&&personCar.size()>0){
				plBidPlan.setCarLoan("有");
				plBidPlan.setCarAssets("有");
			}else{
				plBidPlan.setCarLoan("无");
				plBidPlan.setCarAssets("无");
			}
		}
	}
	private void getloanLifes(PlBidPlan pbp){
		//增加的
		if(pbp.getProType().equals("P_Or")){
			BpPersionOrPro pop = pbp.getBpPersionOrPro();
			if(pop!=null){
				if(pop.getLoanEndTime()!=null&&pop.getLoanStarTime()!=null){
					String month = "";
					if("P_Or".equals(pbp.getProType())){
						month = String.valueOf((Integer.parseInt(getLoanMonth(pop.getLoanStarTime(),pop.getLoanEndTime()))+1)) ;
					}else{
						month = getLoanMonth(pop.getLoanStarTime(),pop.getLoanEndTime());
					}

					pop.setLoanLife(month);
				}
			}
		}
	}

	private List<PlWebShowMaterials> findMaterials(String pid,String businessType){
		try{
			listMaterials=plWebShowMaterialsService.getByPidAndType(pid,businessType);
			for(PlWebShowMaterials m:listMaterials){
				if(null !=m.getProMaterialsId() && !"".equals(m.getProMaterialsId())){
					List<FileForm> fileList=plWebShowMaterialsService.getImgUrl("sl_smallloan_project."+m.getWebMaterialsId()+m.getProMaterialsId()+m.getProjId());
					if(null !=fileList && fileList.size()>0){
						m.setImgUrl(fileList.get(0).getFilepath().replace("\\", "/"));
						for(FileForm file:fileList){
							file.getFilepath().replace("\\", "/");
						}
						m.setFileFormList(fileList);
					}
				}else{
					List<FileForm> fileList=plWebShowMaterialsService.getImgUrl("sl_smallloan_project."+m.getWebMaterialsId()+m.getProjId());
					if(null !=fileList && fileList.size()>0){
						m.setImgUrl(fileList.get(0).getFilepath().replace("\\", "/"));
						for(FileForm file:fileList){
							file.getFilepath().replace("\\", "/");
						}
						m.setFileFormList(fileList);
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return listMaterials;
	}

	private void findPlanProjInfo(PlBidPlan plBidPlan){

		QueryFilter filter=new QueryFilter(this.getRequest());

		if(plBidPlan.getProType().equals("B_Dir")){
			planPro=bpBusinessDirProService.get(plBidPlan.getBdirProId());
			filter.addFilter("Q_bpBusinessDirPro.bdirProId_L_EQ", plBidPlan.getBdirProId().toString());
			if(plBusinessDirProKeepService.getAll(filter)!=null&&plBusinessDirProKeepService.getAll(filter).size()>0){
			planKeep =plBusinessDirProKeepService.getAll(filter).get(0);
			getCooperation((PlBusinessDirProKeep)planKeep);
			}
		}else if(plBidPlan.getProType().equals("B_Or")){
			planPro=bpBusinessOrProService.get(plBidPlan.getBorProId());
			filter.addFilter("Q_bpBusinessOrPro.borProId_L_EQ", plBidPlan.getBorProId().toString());
			if(plBusinessDirProKeepService.getAll(filter)!=null&&plBusinessDirProKeepService.getAll(filter).size()>0){
			planKeep =plBusinessDirProKeepService.getAll(filter).get(0);
			}
		}else if(plBidPlan.getProType().equals("P_Dir")){
			planPro=bpPersionDirProService.get(plBidPlan.getPdirProId());
			queryPersonInfo(plBidPlan);
			fundLoanPersion(plBidPlan);
			filter.addFilter("Q_bpPersionDirPro.pdirProId_L_EQ", plBidPlan.getPdirProId().toString());
			//信用等级加条件
			//filter.addFilter("Q_plKeepCreditlevel.creditLevelId_L_GT", "5");
			if(plPersionDirProKeepService.getAll(filter)!=null&&plPersionDirProKeepService.getAll(filter).size()>0){
				planKeep =plPersionDirProKeepService.getAll(filter).get(0);
				getCooperation((PlPersionDirProKeep)planKeep);
			}
		}else if(plBidPlan.getProType().equals("P_Or")){
			planPro=bpPersionOrProService.get(plBidPlan.getPOrProId());
			queryPersonInfo(plBidPlan);
			fundLoanPersion(plBidPlan);
			filter.addFilter("Q_bpPersionOrPro.porProId_L_EQ", plBidPlan.getPOrProId().toString());
			if(plPersionDirProKeepService.getAll(filter)!=null&&plPersionDirProKeepService.getAll(filter).size()>0){
			planKeep =plPersionDirProKeepService.getAll(filter).get(0);
			}
		}
		System.out.println("planPro==="+planPro);
	}
	/**
	 * @param plBidPlan
	 */
	private void queryPersonInfo(PlBidPlan plBidPlan){
		try {
			if(plBidPlan.getProType().equals("P_Dir")){
				BpPersionDirPro personDir = bpPersionDirProService.get(plBidPlan.getPdirProId());
				bpPersionDirProService.evict(personDir);//从缓存中清除
				BpCustRelation relaton = null;
				BpCustMember cust =  null;
				if(personDir!=null){
					relaton = bpCustRelationService.getByLoanTypeAndId("p_loan", personDir.getPersionId());
					if(relaton!=null){
						cust = bpCustMemberService.get(relaton.getP2pCustId());
					}
				}

				//查询cs_person对应的信息
				Person person = null;
				if(personDir!=null){
					person = personService.queryPersonId(personDir.getPersionId().intValue());
			//		personDir.setPersionName(person.getName());//用户名
					personDir.setLoginName((person.getP2pName()==null?(cust==null?"":cust.getLoginname()):person.getP2pName()));
					personDir.setMarriage(dictionaryService.getQueryDicId(person.getMarry()==null?-10:person.getMarry().longValue()));//婚姻
					personDir.setEducation(dictionaryService.getQueryDicId(person.getDgree()==null?-10:Long.valueOf(person.getDgree())));//学历
					personDir.setEducationSchool(person.getGraduationunversity());//学校
					String workHireCity = csDicAreaDynamService.queryDicArea(person.getHireCity()==null?-10:Long.valueOf(person.getHireCity()));
					personDir.setWorkCity("null".equals(workHireCity)?"未填":workHireCity);//工作城市
					if(null !=person.getJobstarttime() && !"".equals(person.getJobstarttime())){
						personDir.setWorkTime(new SimpleDateFormat("yyyy-MM-dd").format(person.getJobstarttime()));//工作时间
					}
					personDir.setCompanyIndustry(csDicAreaDynamService.queryDicArea(person.getHangyeType()==null?-10:Long.valueOf(person.getHangyeType())));//公司行业
					if(null !=person.getCompanyScale() && !"".equals(person.getCompanyScale())){
						personDir.setCompanyScale(dictionaryService.getQueryDicId(Long.valueOf(person.getCompanyScale())));//公司规模
					}
					personDir.setPosition(dictionaryService.getQueryDicId(person.getJob()==null?-10:Long.valueOf(person.getJob())));//公司职位
				}
				planPro = personDir;


				//Long.valueOf(person.getMarry().toString())==null?-10:Long.valueOf(person.getMarry().toString()))
				//personDir.setMarriage(dictionaryService.getQueryDicId(cust.getMarry()==null?-10:cust.getMarry()));//婚姻
				//personDir.setMarriage(dictionaryService.getQueryDicId(new Long(317)));//婚姻
				//personDir.setEducation(dictionaryService.getQueryDicId(cust.getCollegeDegree()==null?-10:Long.valueOf(cust.getCollegeDegree())));//学历
				//personDir.setWorkCity(cust.getHireAddress());//工作城市
				//personDir.setCompanyScale(cust.getHireCompanysize());//公司规模
				//System.out.println("学历cust.getCollegeDegree()=="+cust.getCollegeDegree()+"----工作城市cust.getHireAddress()=="+cust.getHireAddress()+"---公司规模cust.getHireCompanysize()=="+cust.getHireCompanysize());
				//personDir.setWorkTime(cust.getHireStartyear());//工作时间
				//personDir.setPosition(dictionaryService.getQueryDicId(cust.getHirePosition()==null?-10:Long.valueOf(cust.getHirePosition())));//公司职位
				//personDir.setEducationSchool(cust.getCollegename());//学校
				//System.out.println("工作时间cust.getHireStartyear()=="+cust.getHireStartyear()+"----公司职位cust.getHirePosition()=="+cust.getHirePosition()+"---学校cust.getCollegename()=="+cust.getCollegename());
				//personDir.setCompanyIndustry(csDicAreaDynamService.queryDicArea(cust.getBossCompanycategory()==null?-10:new Long(cust.getBossCompanycategory())));//公司行业

			}else if(plBidPlan.getProType().equals("P_Or")){
				BpPersionOrPro personOr= bpPersionOrProService.get(plBidPlan.getPOrProId());
				bpPersionOrProService.evict(personOr);//从缓存中清除
				//查询cs_person对应的信息
				Person person = null;
				if(personOr!=null){
					person = personService.queryPersonId(personOr.getPersionId().intValue());
			//		personDir.setPersionName(person.getName());//用户名
					personOr.setEducation(dictionaryService.getQueryDicId(person.getDgree()==null?-10:Long.valueOf(person.getDgree())));//学历
					personOr.setEducationSchool(person.getGraduationunversity());//学校
					String workHireCity = csDicAreaDynamService.queryDicArea(person.getHireCity()==null?-10:Long.valueOf(person.getHireCity()));
					personOr.setWorkCity("null".equals(workHireCity)?"未填":workHireCity);//工作城市
					if(null !=person.getJobstarttime() && !"".equals(person.getJobstarttime())){
						personOr.setWorkTime(new SimpleDateFormat("yyyy-MM-dd").format(person.getJobstarttime()));//工作时间
					}
					personOr.setCompanyIndustry(csDicAreaDynamService.queryDicArea(person.getHangyeType()==null?-10:Long.valueOf(person.getHangyeType())));//公司行业
					if(null !=person.getCompanyScale() && !"".equals(person.getCompanyScale())){
						personOr.setCompanyScale(dictionaryService.getQueryDicId(Long.valueOf(person.getCompanyScale())));//公司规模
					}
					personOr.setPosition(dictionaryService.getQueryDicId(person.getJob()==null?-10:Long.valueOf(person.getJob())));//公司职位
					personOr.setCurrentcompany(null==person.getCurrentcompany()?"未填":person.getCurrentcompany());//工作单位
					personOr.setJobincome(person.getJobincome());//月收入
				}
				planPro = personOr;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void fundLoanPersion(PlBidPlan plBidPlan){
		if("p_Dir".equals(plBidPlan.getProType())){
			BpPersionDirPro dirPro = plBidPlan.getBpPersionDirPro();
			BpCustRelation custRelation = bpCustRelationService.getCustRelation(dirPro.getPersionId());
			if(custRelation!=null){
				BpCustMember member = bpCustMemberService.get(custRelation.getP2pCustId());
				if(member!=null){
					Integer nowNum = slFundIntentService.getCountDetailLoan(plBidPlan.getBidId(), "nowOverdue",member.getLoginname());//当前逾期笔数：借款人所有有逾期的借款总笔数
					plBidPlan.setDetailNowNum(nowNum);
					BigDecimal nameMoney= slFundIntentService.getLoanTotle(plBidPlan.getBidId(), "", member.getLoginname());//借款总额：借款人对应所有已发的标的借款总额
					plBidPlan.setDetailNameMoney(nameMoney==null?new BigDecimal(0):nameMoney);
				}
			}

		}
		if("P_Or".equals(plBidPlan.getProType())){
			BpPersionOrPro dirPro = plBidPlan.getBpPersionOrPro();
			BpCustRelation custRelation = bpCustRelationService.getCustRelation(dirPro.getPersionId());
			if(custRelation!=null){
				BpCustMember member = bpCustMemberService.get(custRelation.getP2pCustId());
				if(member!=null){
					Integer nowNum = slFundIntentService.getCountDetailLoan(plBidPlan.getBidId(), "nowOverdue",member.getLoginname());//当前逾期笔数：借款人所有有逾期的借款总笔数
					plBidPlan.setDetailNowNum(nowNum);
					BigDecimal nameMoney= slFundIntentService.getLoanTotle(plBidPlan.getBidId(), "", member.getLoginname());//借款总额：借款人对应所有已发的标的借款总额
					plBidPlan.setDetailNameMoney(nameMoney==null?new BigDecimal(0):nameMoney);
				}
			}

		}
		Integer historyNum = slFundIntentService.getCountDetailLoan(plBidPlan.getBidId(), "historyLoan","");//历史借款：到目前为止已借款总笔数
		plBidPlan.setDetailHistoryNum(historyNum);
		Integer repaymentNum = slFundIntentService.getCountDetailLoan(plBidPlan.getBidId(), "repayment","");//还清笔数：到目前为止已还清借款的总笔数
		plBidPlan.setDetailRepaymentNum(repaymentNum);
		BigDecimal thisMoney= slFundIntentService.getLoanTotle(plBidPlan.getBidId(), "thisTime", "");//本次借款总数：此标的借款金额
		plBidPlan.setDetailThisMoney(thisMoney==null?new BigDecimal(0):thisMoney);
	}
	public PlBusinessDirProKeep getCooperation(PlBusinessDirProKeep proKeep){
		//直投标
		if(null!=proKeep.getBpBusinessDirPro()&&proKeep.getProGtOrzId()!=null){
			BpBusinessDirPro dirPro = proKeep.getBpBusinessDirPro();
			List<BpFundProject> list = bpFundProjectService.getByProjectIdAndFlag(dirPro.getProId(), (short)0);//获取合作机构投资的信息
			if(null!=list&&list.size()!=0&&null!=list.get(0)){
				BpFundProject bpProject = list.get(0);
				proKeep.setCooperationName(bpProject.getInvestName());
				if(null!=bpProject.getOwnJointMoney())
					proKeep.setCooperationMoney(bpProject.getOwnJointMoney());
				else
					proKeep.setCooperationMoney(new BigDecimal(0));
			}
		}

		//转让标

		return proKeep;
	}
	public PlPersionDirProKeep getCooperation(PlPersionDirProKeep proKeep){
		//直投标
		if(null!=proKeep.getBpPersionDirPro()&&proKeep.getProGtOrzId()!=null){
			BpPersionDirPro dirPro = proKeep.getBpPersionDirPro();
			List<BpFundProject> list = bpFundProjectService.getByProjectIdAndFlag(dirPro.getProId(), (short)0);//获取合作机构投资的信息
			if(null!=list&&list.size()>0&&null!=list.get(0)){
				BpFundProject bpProject = list.get(0);
				proKeep.setCooperationName(bpProject.getInvestName());
				if(null!=bpProject.getOwnJointMoney())
					proKeep.setCooperationMoney(bpProject.getOwnJointMoney());
				else
					proKeep.setCooperationMoney(new BigDecimal(0));
			}
		}
		//转让标
		return proKeep;
	}
	/**
	 * 债权转让列表
	 * @return
	 */
	public String biddebtlist(){
		//统计
		statisticalAll();
		if(this.getRequest().getParameter("Q_proType_S_LK")!=null&&!this.getRequest().getParameter("Q_proType_S_LK").equals("")){
			planType=this.getRequest().getParameter("Q_proType_S_LK");
		}else{
			planType="Dir";
		}

		//生成页面上的查询条件
		pageParams();
		//生成查询条件
		if(plBidPlanFilter==null)
		{
			plBidPlanFilter=new PlBidPlanFilter();
		}

		plBidPlanFilter=createFilter(plBidPlanFilter);
		//pager 过滤条件
		QueryFilter filter = new QueryFilter(getRequest());
		if (pager == null) {
			pager = new Pager();

		}
		if(this.getRequest().getParameter("pageSize")!=null&&!this.getRequest().getParameter("pageSize").equals("")){
			pager.setPageSize(Integer.valueOf(StringUtil.html2Text(this.getRequest().getParameter("pageSize"))));
		}
		//生成查询的filter
		filter=createFilter(filter,null);
		String pkType = getRequest().getParameter("proKeepType");
		if(pkType!=null){
			PlKeepProtype type = plKeepProtypeService.getByKeyStr(pkType);
			if(type!=null)
				filter.addFilter("Q_proKeepType_S_EQ", type.getName());
		}
		List<PlBidPlan> list = plBidPlanService.getAll(filter);
		List<PlBidPlan> currList = new ArrayList<PlBidPlan>();
		for (PlBidPlan plan : list) {
			plBidPlanService.setProperty(plan, this.getRequest());
			//增加的
			if(plan.getProType().equals("P_Or")){
				BpPersionOrPro pop = plan.getBpPersionOrPro();
				if(pop!=null){
					if(pop.getLoanEndTime()!=null&&pop.getLoanStarTime()!=null){
						String month = "";
						if("P_Or".equals(plan.getProType())){
							month =String.valueOf((Integer.parseInt(getLoanMonth(pop.getLoanStarTime(),pop.getLoanEndTime()))+1)) ;
						}else{
							month = getLoanMonth(pop.getLoanStarTime(),pop.getLoanEndTime());
						}

						pop.setLoanLife(month);
					}
				}
			}
			currList.add(plan);
		}
		/*if(!AppUtil.getProjStr().equals("proj_duorongyi")){
		listMoneyPlan = plManageMoneyPlanService.getMoneyPlan(getRequest(),true);
		if(plManageMoneyPlanService.getMoneyPlan(getRequest(),false).size()!=0){
			bybMoneyPlan = plManageMoneyPlanService.getMoneyPlan(getRequest(),false).get(0);
		}
		}*/
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(currList);

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BIDDEBTLIST).getTemplateFilePath());
		return "bidPlan";
	}


	/**                                        利息                                 本金                                 提前还款补偿息
	 * 还款表现，目前查询类型(fundType 等于 'loanInterest','principalRepayment','interestPenalty')
	 * @return
	 */
	public  String getBpFundIntents(String bidId){
//		String bidId = getRequest().getParameter("bidId");
		if(null==bidId||"".equals(bidId)||"null".equals(bidId)){
			return ERROR;
		}
		slFundList = bpFundIntentService.getListByBidPlanId(Long.parseLong(bidId), null);
		return "slFundIntent";
	}
	/**
	 * 获取是否可开通自动投标
	 *
	 * @return
	 */
	public String autoBid() {
		StringBuffer sb = new StringBuffer("{\"success\":");
		Gson gson=new Gson();
		try{
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if(mem!=null){
			Integer sizeNum = bpFundIntentService.getFactDateNull(mem.getLoginname());//当用户有未还的款项时，不能开通自动投标
			if(true){
				String[] str=plBidAutoService.chk(mem.getId());
				if(str[0].equals(Constants.FAILDFLAG)){
					sb.append("false,\"msg\":");
				}else{
					sb.append("true,\"msg\":");
				}
				sb.append("\""+str[1]+"\"");

			}else{
				sb.append("false,\"msg\":");
				sb.append("\"您还有借款未还清，不能开通自动投标\"");

			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("}");
		setJsonString(gson.toJson(sb.toString()));
		return SUCCESS;
	}

	/**
	 * 线下债权详情查询方法
	 * @return
	 */
	public String getlineChildren(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		try {
		//设置方重复 token
		Object tokenSession=this.getSession().getAttribute(MyUserSession.FORM_TOKEN);
		if(tokenSession!=null&&!tokenSession.equals("")){
			this.getSession().removeAttribute(MyUserSession.FORM_TOKEN);
		}
		this.formtoken=Common.getRandomNum(5)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		this.getSession().setAttribute(MyUserSession.FORM_TOKEN, formtoken);

		//需要plBidPlan的id获取对象，
		String bidId = getRequest().getParameter("bidId");
		String pid="";
		if(null!=bidId&&!"".equals(bidId)){
			plBidPlan = plBidPlanService.get(Long.parseLong(bidId));
			plBidPlanService.setProperty(plBidPlan, this.getRequest());

			//剩余时间，单位是毫秒，用于脚本计算
			findPlanProjInfo(plBidPlan);
			if (plBidPlan.getProType().equals("B_Or")) {
				pid=plBidPlan.getBpBusinessOrPro().getProId().toString();
				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//项目状态
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString())));
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString(),"1")));
				BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessOrPro().getMoneyPlanId());
				if(bp!=null){
					enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
					enterpriseDao.evict(enterPrise);
					if(null != enterPrise.getHangyeType()) {
						  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){

							  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
						  }
					}
					if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
						String[] regx=enterPrise.getManagecity().split(",");
						String rex="";
						for(int i=0;i<regx.length;i++){
							String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
							if(i==(regx.length-1)){
								rex+=temp;
							}
							else{
								rex+=temp+"_";
							}
						}
						enterPrise.setManagecityName(rex);
					}
					if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
//						if(enterPrise.getOwnership()!=null&&!enterPrise.getOwnership().equals("")){
						String ss=dictionaryDao.getQueryDicId(Long.valueOf(enterPrise.getOwnership()));
						enterPrise.setOwnership(ss);
					}
					System.out.println(enterPrise.getEnterprisename());
					this.getRequest().setAttribute("enterPrise", enterPrise);
				}
				PlBusinessDirProKeep pl=plBidPlan.getBpBusinessOrPro().getPlBusinessDirProKeep();
				this.getRequest().setAttribute("plBusinessDirProKeep", pl);
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BUSINESS_LINECHILDREN).getTemplateFilePath());
			} else if (plBidPlan.getProType().equals("P_Or")) {
				pid=plBidPlan.getBpPersionOrPro().getProId().toString();
				getPersonalInfo(plBidPlan);
				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//抵押担保物类别
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//获取担保方式
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//项目状态
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString())));
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString(),"1")));

				getloanLifes(plBidPlan);
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSION_LINECHILDREN).getTemplateFilePath());
			}

			plan = plBidPlan;

			//获取贷款材料
			findMaterials(pid,"SmallLoanBusiness");

			plBidPlan =	plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
			getBpFundIntents(plBidPlan.getBidId().toString());


			BigDecimal totalMoney=new BigDecimal(0);
			List<PlMmOrderChildrenOr> plchildOrList = plMmOrderChildrenOrService.listByParentOrBidId(plBidPlan.getBidId());//债券记录
			List<PlMmOrderChildrenOr> childOrList = new ArrayList<PlMmOrderChildrenOr>();
			for(int i=0;i<plchildOrList.size();i++){
				PlMmOrderChildrenOr plor = plchildOrList.get(i);
				PlManageMoneyPlanBuyinfo planinfo=plManageMoneyPlanBuyinfoService.get(plor.getOrderId());
				plor.setYearRate(planinfo.getPromisYearRate());
				plor.setBuyMoney(planinfo.getBuyMoney());
				plor.setBuyDate(planinfo.getBuyDatetime());
				childOrList.add(plor);
				totalMoney=totalMoney.add(planinfo.getBuyMoney());
			}
			this.getRequest().setAttribute("childOrList",childOrList);

			plBidPlan.setPersionNum(plchildOrList.size());
			plBidPlan.setAfterMoneyTotal(totalMoney);
		}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return "bidPlan";
	}


	//查询出来债权交易的债权详情
	public String  saleInfo(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		plBidSale=plBidSaleService.querySaleInfo(Long.valueOf(this.getRequest().getParameter("salseId")));
		String isMobile = this.getRequest().getParameter("isMobile");
		PlBusinessDirProKeep pkpl =null;
		String pid="";
				plBidPlan = plBidPlanService.get(plBidSale.getBidPlanID());
				plBidPlanService.setProperty(plBidPlan, this.getRequest());
				plBidPlan=plBidPlanService.getAfterTime(plBidPlan);

				plBidPlan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plBidPlan.getPublishSingeTime()!=null&&!"".equals(plBidPlan.getPublishSingeTime())){
					plBidPlan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plBidPlan.getPublishSingeTime()));
				}else{
					plBidPlan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}

				//计算满标用时
				if(plBidPlan.getFullTime()!=null){
					plBidPlan.setBidFullTime(DateUtil.dateDiff(plBidPlan.getPublishSingeTime().toString(), plBidPlan.getFullTime().toString(), "yyyy-MM-dd HH:mm:ss", ""));
				}
				if(plBidPlan.getState()==PlBidPlan.STATE7||plBidPlan.getState()==PlBidPlan.STATE5||plBidPlan.getState()==PlBidPlan.STATE6||plBidPlan.getState()==PlBidPlan.STATE10){
					String[] ret=bpFundIntentService.getFundInfoByPlanId(plBidSale.getBidPlanID(), this.getRequest());
					plBidPlan.setNotRepaymentMoney(ret[0]);
					plBidPlan.setRepayMentLength((Integer.valueOf(ret[1]))+"/"+ret[2]);
					plBidPlan.setNextRepaymentDate(ret[3]);
					plBidPlan.setRepaymentFullDate(ret[4]);
				}

				// 获取动态信息 如 投标进度 投标人数 投标剩余金额
				String[] info=plBidPlanService.bidDynamic(plBidPlan);
				plBidPlan.setProgress(Double.valueOf(info[0]));
				plBidPlan.setPersionNum(Integer.valueOf(info[1]));
				plBidPlan.setAfterMoney(Double.valueOf(info[2]));
				if(info[3]!=null&&!"".equals(info[3])){
					plBidPlan.setRemainingTime(Long.valueOf(info[3]));
				}
				//剩余时间，单位是毫秒，用于脚本计算
				findPlanProjInfo(plBidPlan);
				if (plBidPlan.getProType().equals("B_Dir")) {
					pid=plBidPlan.getBpBusinessDirPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString())));
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//项目状态
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessDirPro().getMoneyPlanId());
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){

								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							List<Dictionary> ss=dictionaryDao.getByProTypeId(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership((ss!=null&&ss.size()>0)?(ss.get(0).getItemValue()):"");
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
					}
				} else if (plBidPlan.getProType().equals("B_Or")) {
					pid=plBidPlan.getBpBusinessOrPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessOrPro().getMoneyPlanId());
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){

								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							String ss=dictionaryDao.getQueryDicId(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership(ss);
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
					}
					PlBusinessDirProKeep pl=plBidPlan.getBpBusinessOrPro().getPlBusinessDirProKeep();
					if(isMobile!=null){
						pkpl=pl;
					}else{
					this.getRequest().setAttribute("plBusinessDirProKeep", pl);}
				} else if (plBidPlan.getProType().equals("P_Dir")) {
					pid=plBidPlan.getBpPersionDirPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString(),"1")));
				} else if (plBidPlan.getProType().equals("P_Or")) {
					pid=plBidPlan.getBpPersionOrPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString(),"1")));

					getloanLifes(plBidPlan);
				}
				plBidPlan =	plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
				getBpFundIntents(plBidPlan.getBidId().toString());
				listPlBid = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"");//投标记录
				List<PlBidInfo> bondList = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"group");//债券记录
				List<PlBidInfo> bondListBid = new ArrayList<PlBidInfo>();
				for(int i=0;i<bondList.size();i++){

					PlBidInfo bidInfo = bondList.get(i);
					plBidInfoService.evict(bidInfo);
					BigDecimal money  = plBidInfoService.getUserMoneyGroup(bidInfo.getBidId(), bidInfo.getUserId());
					bidInfo.setBondTotelMoney(money==null?new BigDecimal(0):money);
					bondListBid.add(bidInfo);
				}
				this.getRequest().setAttribute("bondListBid",bondListBid );
				plan = plBidPlan;

				//获取贷款材料
				findMaterials(pid,"SmallLoanBusiness");
				//查询债权转让记录
				List<PlBidSale> saleList=plBidSaleService.getByPbidPlanID(plBidPlan.getBidId(),"4");
				this.getRequest().setAttribute("saleList", saleList);
				if(isMobile!=null){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"proDes\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","opendate","bidEndTime","createTime");

					if (plBidPlan.getProType().equals("B_Dir")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("B_Or")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("P_Dir")){
						PlPersionDirProKeep pl=plBidPlan.getBpPersionDirPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else if (plBidPlan.getProType().equals("P_Or")) {
						PlPersionDirProKeep pl=plBidPlan.getBpPersionOrPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else{
						buff.append(json.serialize(""));
					}

					buff.append(",\"data\":");
					buff.append(json.serialize(plan));
					buff.append(",\"pltype\":");
					buff.append(json.serialize(plBidPlan.getProType()));
					buff.append(",\"enterPrise\":");
					buff.append(json.serialize(enterPrise));
					buff.append(",\"listMaterials\":");
					buff.append(json.serialize(listMaterials));
					buff.append("}");
					jsonString = buff.toString();
					System.out.println("jsonString===="+jsonString);
					return SUCCESS;
				}

			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BID_SALEINFO).getTemplateFilePath());

		return "bidPlan";
	}
	public String  saleInfo1(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		plBidSale=plBidSaleService.querySaleInfo(Long.valueOf(this.getRequest().getParameter("salseId")));
		String isMobile = this.getRequest().getParameter("isMobile");
		PlBusinessDirProKeep pkpl =null;
		String pid="";
				plBidPlan = plBidPlanService.get(plBidSale.getBidPlanID());
				plBidPlanService.setProperty(plBidPlan, this.getRequest());
				plBidPlan=plBidPlanService.getAfterTime(plBidPlan);

				plBidPlan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				if(plBidPlan.getPublishSingeTime()!=null&&!"".equals(plBidPlan.getPublishSingeTime())){
					plBidPlan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plBidPlan.getPublishSingeTime()));
				}else{
					plBidPlan.setPreSaleTimeStr("1994-10-16 00:00:00");
				}

				//计算满标用时
				if(plBidPlan.getFullTime()!=null){
					plBidPlan.setBidFullTime(DateUtil.dateDiff(plBidPlan.getPublishSingeTime().toString(), plBidPlan.getFullTime().toString(), "yyyy-MM-dd HH:mm:ss", ""));
				}
				if(plBidPlan.getState()==PlBidPlan.STATE7||plBidPlan.getState()==PlBidPlan.STATE5||plBidPlan.getState()==PlBidPlan.STATE6||plBidPlan.getState()==PlBidPlan.STATE10){
					String[] ret=bpFundIntentService.getFundInfoByPlanId(plBidSale.getBidPlanID(), this.getRequest());
					plBidPlan.setNotRepaymentMoney(ret[0]);
					plBidPlan.setRepayMentLength((Integer.valueOf(ret[1]))+"/"+ret[2]);
					plBidPlan.setNextRepaymentDate(ret[3]);
					plBidPlan.setRepaymentFullDate(ret[4]);
				}

				// 获取动态信息 如 投标进度 投标人数 投标剩余金额
				String[] info=plBidPlanService.bidDynamic(plBidPlan);
				plBidPlan.setProgress(Double.valueOf(info[0]));
				plBidPlan.setPersionNum(Integer.valueOf(info[1]));
				plBidPlan.setAfterMoney(Double.valueOf(info[2]));
				if(info[3]!=null&&!"".equals(info[3])){
					plBidPlan.setRemainingTime(Long.valueOf(info[3]));
				}
				//剩余时间，单位是毫秒，用于脚本计算
				findPlanProjInfo(plBidPlan);
				if (plBidPlan.getProType().equals("B_Dir")) {
					pid=plBidPlan.getBpBusinessDirPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString())));
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//项目状态
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessDirPro().getMoneyPlanId());
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){

								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							List<Dictionary> ss=dictionaryDao.getByProTypeId(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership((ss!=null&&ss.size()>0)?(ss.get(0).getItemValue()):"");
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
					}
				} else if (plBidPlan.getProType().equals("B_Or")) {
					pid=plBidPlan.getBpBusinessOrPro().getProId().toString();
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString(),"1")));
					BpFundProject bp =bpFundProjectService.get(plBidPlan.getBpBusinessOrPro().getMoneyPlanId());
					if(bp!=null){
						enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
						enterpriseDao.evict(enterPrise);
						if(null != enterPrise.getHangyeType()) {
							  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){

								  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
							  }
						}
						if(null!=enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
							String[] regx=enterPrise.getManagecity().split(",");
							String rex="";
							for(int i=0;i<regx.length;i++){
								String temp=areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
								if(i==(regx.length-1)){
									rex+=temp;
								}
								else{
									rex+=temp+"_";
								}
							}
							enterPrise.setManagecityName(rex);
						}
						if(enterPrise.getOwnership()!=null && !"".equals(enterPrise.getOwnership().trim())){
							String ss=dictionaryDao.getQueryDicId(Long.valueOf(enterPrise.getOwnership()));
							enterPrise.setOwnership(ss);
						}
						this.getRequest().setAttribute("enterPrise", enterPrise);
					}
					PlBusinessDirProKeep pl=plBidPlan.getBpBusinessOrPro().getPlBusinessDirProKeep();
					if(isMobile!=null){
						pkpl=pl;
					}else{
					this.getRequest().setAttribute("plBusinessDirProKeep", pl);}
				} else if (plBidPlan.getProType().equals("P_Dir")) {
					pid=plBidPlan.getBpPersionDirPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString(),"1")));
				} else if (plBidPlan.getProType().equals("P_Or")) {
					pid=plBidPlan.getBpPersionOrPro().getProId().toString();
					getPersonalInfo(plBidPlan);
					plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//抵押担保物类别
					plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//获取担保方式
					plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));//项目状态
					plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString())));
					plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString(),"1")));

					getloanLifes(plBidPlan);
				}
				plBidPlan =	plBidPlanService.returnPlBidPlan(plBidPlan);//获取标的实体类
				getBpFundIntents(plBidPlan.getBidId().toString());
				listPlBid = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"");//投标记录
				List<PlBidInfo> bondList = plBidInfoService.getIntentInfo(plBidPlan.getBidId(),"group");//债券记录
				List<PlBidInfo> bondListBid = new ArrayList<PlBidInfo>();
				for(int i=0;i<bondList.size();i++){

					PlBidInfo bidInfo = bondList.get(i);
					plBidInfoService.evict(bidInfo);
					BigDecimal money  = plBidInfoService.getUserMoneyGroup(bidInfo.getBidId(), bidInfo.getUserId());
					bidInfo.setBondTotelMoney(money==null?new BigDecimal(0):money);
					bondListBid.add(bidInfo);
				}
				this.getRequest().setAttribute("bondListBid",bondListBid );
				plan = plBidPlan;

				//获取贷款材料
				findMaterials(pid,"SmallLoanBusiness");
				//查询债权转让记录
				List<PlBidSale> saleList=plBidSaleService.getByPbidPlanID(plBidPlan.getBidId(),"4");
				this.getRequest().setAttribute("saleList", saleList);
				if(isMobile!=null){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"proDes\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","opendate","bidEndTime","createTime");

					if (plBidPlan.getProType().equals("B_Dir")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("B_Or")) {
						buff.append(json.serialize(pkpl));
					}else if (plBidPlan.getProType().equals("P_Dir")){
						PlPersionDirProKeep pl=plBidPlan.getBpPersionDirPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else if (plBidPlan.getProType().equals("P_Or")) {
						PlPersionDirProKeep pl=plBidPlan.getBpPersionOrPro().getPlPersionDirProKeep();
						buff.append(json.serialize(pl));
					}else{
						buff.append(json.serialize(""));
					}

					buff.append(",\"data\":");
					buff.append(json.serialize(plan));
					buff.append(",\"pltype\":");
					buff.append(json.serialize(plBidPlan.getProType()));
					buff.append(",\"enterPrise\":");
					buff.append(json.serialize(enterPrise));
					buff.append(",\"listMaterials\":");
					buff.append(json.serialize(listMaterials));
					buff.append(",\"plBidSale\":");
					buff.append(json.serialize(plBidSale));
					buff.append(",\"listPlBid\":");
					buff.append(json.serialize(listPlBid));
					buff.append(",\"bondListBid\":");
					buff.append(json.serialize(bondListBid));
					buff.append("}");
					jsonString = buff.toString();
					System.out.println("jsonString===="+jsonString);
					return SUCCESS;
				}

			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BID_SALEINFO).getTemplateFilePath());

		return "bidPlan";
	}

	public void setPlBidSale(PlBidSale plBidSale) {
		this.plBidSale = plBidSale;
	}

	public PlBidSale getPlBidSale() {
		return plBidSale;
	}


	/**
	 * 移动端代码
	 * @return
	 */
	public String bidPlanDetailisMobile() {
		// 随机数
		int Num = new Random().nextInt(10) + 1;
		this.getSession().setAttribute("numnum", Num);


		// 设置方重复 token
		Object tokenSession=this.getSession().getAttribute(MyUserSession.FORM_TOKEN);
		if(tokenSession!=null&&!tokenSession.equals("")){
			this.getSession().removeAttribute(MyUserSession.FORM_TOKEN);
		}
		this.formtoken=Common.getRandomNum(5)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		this.getSession().setAttribute(MyUserSession.FORM_TOKEN, formtoken);

		// 需要plBidPlan的id获取对象，
		String bidId = getRequest().getParameter("bidId");

		String pid = "";

		if (null != bidId && !"".equals(bidId)) {
			plBidPlan = plBidPlanService.get(Long.parseLong(bidId));
			plBidPlanService.setProperty(plBidPlan, this.getRequest());
			plBidPlan=plBidPlanService.getAfterTime(plBidPlan);

			plBidPlan.setNowTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			if (plBidPlan.getPublishSingeTime()!=null&&!"".equals(plBidPlan.getPublishSingeTime())) {
				plBidPlan.setPreSaleTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(plBidPlan.getPublishSingeTime()));
			} else {
				plBidPlan.setPreSaleTimeStr("1994-10-16 00:00:00");
			}

			// 计算满标用时
			if (plBidPlan.getFullTime() != null) {
				plBidPlan.setBidFullTime(DateUtil.dateDiff(plBidPlan.getPublishSingeTime().toString(), plBidPlan.getFullTime().toString(), "yyyy-MM-dd HH:mm:ss", ""));
			}
			if (plBidPlan.getState() == PlBidPlan.STATE7 || plBidPlan.getState()==PlBidPlan.STATE5 || plBidPlan.getState() == PlBidPlan.STATE6 || plBidPlan.getState() == PlBidPlan.STATE10) {
				String[] ret = bpFundIntentService.getFundInfoByPlanId(Long.valueOf(bidId), this.getRequest());
				plBidPlan.setNotRepaymentMoney(ret[0]);
				plBidPlan.setRepayMentLength((Integer.valueOf(ret[1])) + "/"+ret[2]);
				plBidPlan.setNextRepaymentDate(ret[3]);
				plBidPlan.setRepaymentFullDate(ret[4]);
			}

			// 获取动态信息 如 投标进度 投标人数 投标剩余金额
			String[] info = plBidPlanService.bidDynamic(plBidPlan);
			plBidPlan.setProgress(Double.valueOf(info[0]));
			plBidPlan.setPersionNum(Integer.valueOf(info[1]));
			plBidPlan.setAfterMoney(Double.valueOf(info[2]));

			if (info[3] != null && !"".equals(info[3])) {
				plBidPlan.setRemainingTime(Long.valueOf(info[3]));
			}
			// 剩余时间，单位是毫秒，用于脚本计算
			findPlanProjInfo(plBidPlan);

			if (plBidPlan.getProType().equals("B_Dir")) {
				pid = plBidPlan.getBpBusinessDirPro().getProId().toString();

				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//抵押担保物类别
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString())));
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//获取担保方式
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString()));//项目状态
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessDirPro().getProId().toString(),"1")));

				System.out.println("==========" + plBidPlan.getBpBusinessDirPro().getMoneyPlanId().intValue());

				BpFundProject bp = bpFundProjectService.get(plBidPlan.getBpBusinessDirPro().getMoneyPlanId());
				if (bp != null) {
					enterPrise=enterpriseDao.getById(bp.getOppositeID().intValue());
					enterpriseDao.evict(enterPrise);
					if(null != enterPrise.getHangyeType()) {
						  if(null != areaDicDao.getById(enterPrise.getHangyeType())){
							  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
						  }
					}
					if(null != enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")){
						String[] regx = enterPrise.getManagecity().split(",");
						String rex = "";
						for (int i = 0; i < regx.length; i++) {
							String temp = areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
							if (i == (regx.length-1)) {
								rex += temp;
							} else{
								rex += temp + "_";
							}
						}
						enterPrise.setManagecityName(rex);
					}
					if (enterPrise.getOwnership() != null && !"".equals(enterPrise.getOwnership().trim())){
						Dictionary ss = dictionaryDao.get(Long.valueOf(enterPrise.getOwnership()));
						enterPrise.setOwnership((ss!=null)?(ss.getItemValue()):"");
					}
					System.out.println(enterPrise.getEnterprisename());
					this.getRequest().setAttribute("enterPrise", enterPrise);
				}
			} else if (plBidPlan.getProType().equals("B_Or")) {
				pid = plBidPlan.getBpBusinessOrPro().getProId().toString();
				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//抵押担保物类别
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//获取担保方式
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString()));//项目状态
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString())));
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpBusinessOrPro().getProId().toString(),"1")));
				System.out.println("=========="+plBidPlan.getBpBusinessOrPro().getMoneyPlanId().intValue());

				BpFundProject bp = bpFundProjectService.get(plBidPlan.getBpBusinessOrPro().getMoneyPlanId());
				if(bp != null){
					enterPrise = enterpriseDao.getById(bp.getOppositeID().intValue());
					enterpriseDao.evict(enterPrise);

					if(null != enterPrise.getHangyeType()) {
						  if(null!=areaDicDao.getById(enterPrise.getHangyeType())){
							  enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
						  }
					}
					if(null != enterPrise.getManagecity() && !enterPrise.getManagecity().trim().equals("")) {
						String[] regx = enterPrise.getManagecity().split(",");
						String rex = "";
						for (int i = 0; i < regx.length; i++) {
							String temp = areaDicDao.getById(Integer.valueOf(regx[i])).getRemarks();
							if (i == (regx.length-1)) {
								rex += temp;
							} else {
								rex += temp + "_";
							}
						}
						enterPrise.setManagecityName(rex);
					}
					if (enterPrise.getOwnership() != null && !"".equals(enterPrise.getOwnership().trim())) {
//							if(enterPrise.getOwnership()!=null&&!enterPrise.getOwnership().equals("")){
						Dictionary ss = dictionaryDao.get(Long.valueOf(enterPrise.getOwnership()));
						enterPrise.setOwnership((ss!=null)?(ss.getItemValue()):"");
					}
					System.out.println(enterPrise.getEnterprisename());
					this.getRequest().setAttribute("enterPrise", enterPrise);
				}
				PlBusinessDirProKeep pl=plBidPlan.getBpBusinessOrPro().getPlBusinessDirProKeep();
				this.getRequest().setAttribute("plBusinessDirProKeep", pl);
			} else if (plBidPlan.getProType().equals("P_Dir")) {
				pid = plBidPlan.getBpPersionDirPro().getProId().toString();
				getPersonalInfo(plBidPlan);

				// 抵押担保物类别
				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));

				// 获取担保方式
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));

				// 项目状态
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionDirPro().getProId().toString()));
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString())));
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionDirPro().getProId().toString(),"1")));
			} else if (plBidPlan.getProType().equals("P_Or")) {
				pid = plBidPlan.getBpPersionOrPro().getProId().toString();
				getPersonalInfo(plBidPlan);

				// 抵押担保物类别
				plBidPlan.setMortgage(plBidPlanService.findMortgageBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));
				// 获取担保方式
				plBidPlan.setAssureType(plBidPlanService.findAssureTypeBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));

				// 获取担保方式
				plBidPlan.setProjectStatus(plBidPlanService.findSmallloanProjectBySQL(plBidPlan.getBpPersionOrPro().getProId().toString()));
				plBidPlan.setLoanTotalMoney(new BigDecimal(plBidPlanService.findLoanTotalMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString())));
				plBidPlan.setOrgMoney(new BigDecimal(plBidPlanService.findOrgMoneyBySQL(plBidPlan.getBpPersionOrPro().getProId().toString(),"1")));

				getloanLifes(plBidPlan);
			}

			// 获取标的实体类
			plBidPlan =	plBidPlanService.returnPlBidPlan(plBidPlan);
			getBpFundIntents(plBidPlan.getBidId().toString());
			plan = plBidPlan;

			//获取贷款材料
			findMaterials(pid,"SmallLoanBusiness");


			if (listMaterials.size() > 0) {
				for (int i = 0; i < listMaterials.size(); i++) {
					// 循环遍历,将每条长度赋给参数
					listMaterials.get(i).setIndexId(String.valueOf(i + 1));
					// 合并每条数据的index
					plWebShowMaterialsService.merge(listMaterials.get(i));
				}
			}

			// 拼接json字符串
			StringBuffer buff = new StringBuffer("{\"success\":true,\"proDes\":\"" + proDes
					+ "\",\"investEnterpriseEnterprisename\":\"" + investEnterpriseEnterprisename
					+ "\",\"formtoken\":\""+formtoken+"\",\"data\":");
			JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","opendate","bidEndTime","createTime","startIntentDate");
			buff.append(json.serialize(plBidPlan));
			buff.append(",\"enterPrise\":");
			buff.append(json.serialize(enterPrise));
			buff.append(",\"numnum\":");
			buff.append(json.serialize(Num));
			buff.append(",\"listMaterials\":");
			buff.append(json.serialize(listMaterials));
			buff.append(",\"listPlBid\":");
			buff.append(json.serialize(listPlBid));
			buff.append("}");
			jsonString = buff.toString();
			System.out.println("jsonString====" + jsonString);
			return SUCCESS;
		}

		return SUCCESS;
	}

	/**
	 * 移动端投标记录
	 * @return
	 */
	public String listPlBidMobile(){
		listPlBid = plBidInfoService.getIntentInfo(bidId,"");//投标记录
		StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"result\":");
		JSONSerializer json = JsonUtil.getJSONSerializer("bidtime");
		buff.append(json.serialize(listPlBid));
		buff.append("}");
		jsonString = buff.toString();
		System.out.println("jsonString===="+jsonString);
		return SUCCESS;
	}


	public String availableUserInfo(){
		// 从session中取出当前用户
		BpCustMember bpCustMember = (BpCustMember) this.getSession()
				.getAttribute(MyUserSession.MEMBEER_SESSION);
		StringBuffer buff = new StringBuffer("");
		if (bpCustMember != null) {
			bpCustMember=bpCustMemberService.get(bpCustMember.getId());
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			obSystemAccountService.getAccountSumMoney(bpCustMember);
	/*		buff.append("{\"success\":true,\"availableInvestMoney\":").append(bpCustMember.getAvailableInvestMoney())
			.append(",\"totalInvestMoney\":").append(bpCustMember.getTotalInvestMoney())
			.append(",\"allInterest\":").append(bpCustMember.getAllInterest());*/
			buff.append("{\"success\":true,\"data\":");
/*			bpCustMember.setAvailableInvestMoney(availableInvestMoney);
			bpCustMember.setTotalInvestMoney(totalInvestMoney);
			bpCustMember.setAllInterest(allInterest)*/;
			buff.append(gson.toJson(bpCustMember));
		}else{

			buff.append("{\"success\":false");
		}

		buff.append("}");
		jsonString = buff.toString();
	    return SUCCESS;

	}

	public String availableInvestMoney(){
		// 从session中取出当前用户
		BpCustMember bpCustMember = (BpCustMember) this.getSession()
				.getAttribute(MyUserSession.MEMBEER_SESSION);
		StringBuffer buff = new StringBuffer("");
		if (bpCustMember != null) {
			obSystemAccountService.getAccountSumMoney(bpCustMember);
			buff.append("{\"success\":true,\"availableInvestMoney\":").append(bpCustMember.getAvailableInvestMoney())
			.append(",\"totalInvestMoney\":").append(bpCustMember.getTotalInvestMoney())
			.append(",\"allInterest\":").append(bpCustMember.getAllInterest());

		}else{

			buff.append("{\"success\":false");
		}

		buff.append("}");
		jsonString = buff.toString();
	    return SUCCESS;

	}
	/**
	 * 代偿信息
	 * creditFlow/financingAgency/compensatoryMoneyPlBidPlan.do
	 */
	public String compensatoryMoney(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		try {
			String bidId = this.getRequest().getParameter("bidId");
			String intentDate = this.getRequest().getParameter("intentDate");
		//	System.out.println("bidId--->"+bidId);
		//	System.out.println("intentDate--->"+intentDate);
			//代偿本金
			BigDecimal comRepaymentMoney=new BigDecimal("0");
			//代偿利息
			BigDecimal comInterestMoney=new BigDecimal("0");
		    //代偿罚息
			BigDecimal comPublishlMoney=new BigDecimal("0");
		    //代偿服务费
			BigDecimal comServiceMoney=new BigDecimal("0");
			//代偿总计
			BigDecimal comtotalMoney=new BigDecimal("0");
			ShowManageMoney  showManageMoney=null;

			if(null!=bidId && ""!=bidId && null!=intentDate && ""!=intentDate){
				List<ShowManageMoney> slist=bpFundIntentService.listByByPlanIdDate(Long.valueOf(bidId), intentDate);
				if(null!=slist && slist.size()>0){
					showManageMoney=slist.get(0);
					this.getSession().setAttribute("showManageMoney", showManageMoney);
					//获得该标的担保方式
				    PlBidPlan plan=plBidPlanService.get(Long.valueOf(bidId));
				    //本金保障
				    if("1".equals(plan.getGuaranteeWay())){
				    	comRepaymentMoney=comRepaymentMoney.add(showManageMoney.getPrincipal());
				    	comPublishlMoney=comPublishlMoney.add(showManageMoney.getPrincipalAccMoney());
				    }
				    //本息保障
				    else if(null!=plan.getGuaranteeWay() && "2".equals(plan.getGuaranteeWay())){
				    	comRepaymentMoney=comRepaymentMoney.add(showManageMoney.getPrincipal());
				    	comInterestMoney=comInterestMoney.add(showManageMoney.getLoanInterest());
				    //	comPublishlMoney=comPublishlMoney.add(showManageMoney.getAccMoney());
				    	comPublishlMoney=comPublishlMoney.add(showManageMoney.getInterestAccMoney());

				    }
				    //全部保障
				    else if( null!=plan.getGuaranteeWay() &&  "3".equals(plan.getGuaranteeWay())){
				     	comRepaymentMoney=comRepaymentMoney.add(showManageMoney.getPrincipal());
				    	comInterestMoney=comInterestMoney.add(showManageMoney.getLoanInterest());
				    	comPublishlMoney=comPublishlMoney.add(showManageMoney.getAccMoney());
				    	comServiceMoney=comServiceMoney.add(showManageMoney.getServiceMoney());
				    }
				}
			}
			comtotalMoney=comtotalMoney.add(comRepaymentMoney).add(comInterestMoney).add(comPublishlMoney).add(comServiceMoney);

			this.getRequest().setAttribute("comRepaymentMoney", comRepaymentMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("comInterestMoney", comInterestMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("comPublishlMoney", comPublishlMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("comServiceMoney", comServiceMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("comtotalMoney", comtotalMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.COMPENSATOTYMONEY).getTemplateFilePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bidPlan";
	}

	//新增标的投资记录接口,已废弃
//	public String plbidInvestorList(){
//		MobileDataResultModel model = new MobileDataResultModel();
//		listPlBid = plBidInfoService.getListInfo(this.getRequest());
//		model.addDataContent("listPlBid",listPlBid);
//		setJsonString(model.toJSON());
//		return SUCCESS;
//	}

}
