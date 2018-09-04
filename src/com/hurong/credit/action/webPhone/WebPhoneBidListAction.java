package com.hurong.credit.action.webPhone;

import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.dao.creditFlow.customer.enterprise.EnterpriseDao;
import com.hurong.credit.dao.creditFlow.multiLevelDic.AreaDicDao;
import com.hurong.credit.dao.system.product.DictionaryDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlanFilter;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepProtype;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleService;
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
import com.hurong.credit.util.MyUserSession;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WebPhoneBidListAction extends BaseAction{

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
    private PlKeepProtypeService plKeepProtypeService;
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
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected String jsonString=JSON_SUCCESS;

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }

    //标的查询接口

    public String checkPlanList(){
        this.getSession().setAttribute("dh", 2);
        String isApp = this.getRequest().getParameter("isApp");
        MobileDataResultModel model = new MobileDataResultModel();
        String page = this.getRequest().getParameter("page");
        String lim = this.getRequest().getParameter("limit");
        limit = Integer.valueOf(lim);
        if (page != null) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
        }
        bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        List<PlBidPlan> planList = plBidPlanService.getNewPlanList1(start,limit,this.getRequest());
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
        if("1".equals(isApp)){
            model.addDataContent("indexPlanList",planList);
            model.addDataContent("totalCount",listSize.size());
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        this.getRequest().setAttribute("indexPlanList",planList);
        this.getRequest().setAttribute("totalCount",listSize.size());
        return "";
    }







//投资列表
    public String plbidInvestorList(){
        String isApp = this.getRequest().getParameter("isApp");
        String page = this.getRequest().getParameter("page");
        if (page != null) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
        }
        MobileDataResultModel model = new MobileDataResultModel();
        listPlBid = plBidInfoService.getListInfo(this.getRequest(),start, limit);
        List<PlBidInfo> listPlBidCount = plBidInfoService.getListInfo(this.getRequest(),null, null);
        //pager.setTotalCount(listPlBidCount != null ? listPlBidCount.size() : 0);
        if("1".equals(isApp)){
            model.addDataContent("listPlBid",listPlBid);
            model.addDataContent("totalCounts",listPlBidCount.size());
            setJsonString(model.toJSON());
            return SUCCESS;
        }
        this.getRequest().setAttribute("listPlBid",listPlBid);
        return "record";
    }
}
