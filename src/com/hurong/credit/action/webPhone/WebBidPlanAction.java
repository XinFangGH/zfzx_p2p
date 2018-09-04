package com.hurong.credit.action.webPhone;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.dao.creditFlow.customer.enterprise.EnterpriseDao;
import com.hurong.credit.dao.creditFlow.multiLevelDic.AreaDicDao;
import com.hurong.credit.dao.system.product.DictionaryDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.model.creditFlow.customer.person.CsPersonCar;
import com.hurong.credit.model.creditFlow.customer.person.CsPersonHouse;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlanFilter;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepProtype;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.BankCard;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.creditFlow.FileForm.FileFormService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.customer.enterprise.EnterpriseService;
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
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.CsDicAreaDynamService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WebBidPlanAction extends BaseAction{

    protected Log logger= LogFactory.getLog(WebPhoneAction.class);



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
    private EnterpriseService enterpriseService;
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
    private PlBidInfo plBidInfo;

    //得到config.properties读取的所有资源
    private static Map configMap = AppUtil.getConfigMap();

    private String bidListType;//招标类型标示：dir:散列标，or：转让标
    //借款人款项台帐
    private List<BpFundIntent> slFundList = new ArrayList<BpFundIntent>();

    @Resource
    private WebBankcardService webBankcardService;

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

    public PlBidInfo getPlBidInfo() {
        return plBidInfo;
    }

    public void setPlBidInfo(PlBidInfo plBidInfo) {
        this.plBidInfo = plBidInfo;
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


    @Resource
    private FileFormService fileFormService;

    //安卓标的详情接口
    public String mobilePlanDetail(){
        MobileDataResultModel model = new MobileDataResultModel();
        String page = this.getRequest().getParameter("page");
        String pDir = this.getRequest().getParameter("isPDir");
        if (page != null) {
            start = Integer.valueOf(page);
            start = (start - 1) * limit;
        }
        //设置方重复 token
        Object tokenSession=this.getSession().getAttribute(MyUserSession.FORM_TOKEN);
        if(tokenSession!=null&&!tokenSession.equals("")){
            this.getSession().removeAttribute(MyUserSession.FORM_TOKEN);
        }
        this.formtoken= Common.getRandomNum(5)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
        this.getSession().setAttribute(MyUserSession.FORM_TOKEN, formtoken);
        model.addDataContent(MyUserSession.FORM_TOKEN,formtoken);
        //标的信息
        String bidId = getRequest().getParameter("bidId");
        String pid = "";
        String host = this.getBasePath();
        if(null != bidId && !"".equals(bidId)) {
            //投资记录
            listPlBid = plBidInfoService.getListInfo(this.getRequest(),null,null);
//            plBidPlan = plBidPlanService.get(Long.parseLong(bidId));
            List<PlBidPlan> list = plBidPlanService.getPlanDetail(Long.parseLong(bidId));
            PlBidPlan plan1 = plBidPlanService.get(Long.parseLong(bidId));
            if (list.size() > 0) {
                this.plBidPlan = list.get(0);
                plBidPlanService.setMobileProperty(this.plBidPlan);
                plBidPlanService.mobileReturnPlBidPlan(this.plBidPlan);
                //项目信息
                List<PlBusinessDirProKeep> plList = plBusinessDirProKeepService.getDetail(this.plBidPlan.getBdirProId());
                if(plList.size() > 0){
                    PlBusinessDirProKeep pl = plList.get(0);
                    pl.setSecurityMeasures("烟台山腾建筑工程机械有限公司承诺逾期债券回购；设备残余价值保障");
                    model.addDataContent("pl",pl);
                    this.getRequest().setAttribute("pl",pl);
                }
                //个人基本信息
                Long perId = this.plBidPlan.getPdirProId();
                queryPersonInfo(plan1);
                String proType = this.plBidPlan.getProType();
                if("P_Dir".equals(proType)){
                    BpPersionDirPro bpPersionDirPro1 = plan1.getBpPersionDirPro();
                    BpPersionDirPro personDir = plBidPlan.getBpPersionDirPro();
                    BpPersionDirPro bpPersionDirPro = bpPersionDirProService.get(personDir.getPdirProId());
                    String loginName = bpPersionDirPro1.getLoginName();
                    String educationSchool = bpPersionDirPro1.getEducationSchool();
                    String companyIndustry = bpPersionDirPro1.getCompanyIndustry();
                    String companyScale = bpPersionDirPro1.getCompanyScale();
                    String position = bpPersionDirPro1.getPosition();
                    String workCity = bpPersionDirPro1.getWorkCity();
                    String workTime = bpPersionDirPro1.getWorkTime();

                    if(personDir!=null){
                        Person person = personService.getJobIncome(bpPersionDirPro.getPersionId().toString());
                        plBidPlan.setMonthIncome(person==null?0:person.getJobincome()==null?0:person.getJobincome());//月收入
                        List<CsPersonHouse> personHouse = csPersonHouseService.getByPersonId(bpPersionDirPro.getPersionId().toString());
                        if(personHouse!=null&&personHouse.size()>0){
                            plBidPlan.setHouseLoan("有");
                            plBidPlan.setHouseAssets("有");
                        }else{
                            plBidPlan.setHouseLoan("无");
                            plBidPlan.setHouseAssets("无");
                        }
                        List<CsPersonCar>  personCar = csPersonCarService. getByPersonId(bpPersionDirPro.getPersionId().toString());
                        if(personCar!=null&&personCar.size()>0){
                            plBidPlan.setCarLoan("有");
                            plBidPlan.setCarAssets("有");
                        }else{
                            plBidPlan.setCarLoan("无");
                            plBidPlan.setCarAssets("无");
                        }
                    }
                    List<BpPersionDirPro> bPerDirInfo = bpPersionDirProService.getInfo(personDir.getPdirProId());
                    BpPersionDirPro dirPro = bPerDirInfo.get(0);
                    dirPro.setLoginName(loginName);
                    dirPro.setEducationSchool(educationSchool);
                    dirPro.setCompanyIndustry(companyIndustry);
                    dirPro.setCompanyScale(companyScale);
                    dirPro.setPosition(position);
                    dirPro.setWorkCity(workCity);
                    dirPro.setWorkTime(workTime);
                    List<PlPersionDirProKeep> pl = plPersionDirProKeepService.getInfo(personDir.getPdirProId());
                    PlPersionDirProKeep dirProKeep = pl.get(0);
                    //BpPersionDirPro persionDirPro = bpPersionDirProService.get(plan1.getPdirProId());
                    //PlPersionDirProKeep proKeep = plan1.getBpPersionDirPro().getPlPersionDirProKeep();
                    //getPersonalInfo(plBidPlan);
                    model.addDataContent("persionDirPro",dirPro);
                    model.addDataContent("proKeep",dirProKeep);
                    this.getRequest().setAttribute("persionDirPro",dirPro);
                    this.getRequest().setAttribute("proKeep",dirProKeep);
                }
                //企业基本信息
                List<BpBusinessDirPro> bpList = bpBusinessDirProService.getBusinessId(this.plBidPlan.getBdirProId());
                if(bpList.size() > 0){
                    List<Enterprise> enterPriseList = enterpriseService.getDetail(bpList.get(0).getBusinessId());
                    if(enterPriseList.size() > 0){
                        enterPrise = enterPriseList.get(0);
                        if(null != enterPrise.getHangyeType()) {
                            if(null!=areaDicDao.getById(enterPrise.getHangyeType())){
                                enterPrise.setHangyeName(areaDicDao.getById(enterPrise.getHangyeType()).getRemarks1());
                            }
                        }
                        if(enterPrise.getOwnership() != null && !"".equals(enterPrise.getOwnership().trim())){
                            Dictionary ss = dictionaryDao.get(Long.valueOf(enterPrise.getOwnership()));
                            enterPrise.setOwnership((ss!=null)?(ss.getItemValue()):"");
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
                        model.addDataContent("enterprise",enterPrise);
                        this.getRequest().setAttribute("enterprise",enterPrise);
                        //经营公示
                        List<FileForm> fileLIst = fileFormService.getAllEnterpriseImg(enterPrise.getId());
                        for (FileForm form : fileLIst) {
                            fileFormService.evict(form);
                            form.setWebPath(host + form.getWebPath());
                        }
                        model.addDataContent("fileLIst", fileLIst);
                        this.getRequest().setAttribute("fileLIst", fileLIst);
                    }

                }
            }

            //设备公示
            try {
                if (this.plBidPlan.getProType().equals("B_Dir")) {
                    BpBusinessDirPro bp = bpBusinessDirProService.get(this.plBidPlan.getBpBusinessDirPro().getBdirProId());
                    pid=bp.getProId().toString();
                }else if(this.plBidPlan.getProType().equals("P_Dir")){
                    BpPersionDirPro pp = bpPersionDirProService.get(this.plBidPlan.getBpPersionDirPro().getPdirProId());
                    pid=pp.getProId().toString();
                }
                if(!"".equals(pid)) {
                    List<PlWebShowMaterials> materials = findMaterials(pid, "SmallLoanBusiness");
                    for (PlWebShowMaterials pl : materials) {

                        pl.setImgUrl(host + pl.getImgUrl());
                    }
                    model.addDataContent("materials", materials);
                    this.getRequest().setAttribute("materials", materials);
                }
            }catch(Exception e){
                e.printStackTrace();
            }


            model.addDataContent("plBidPlan", this.plBidPlan);
            this.getRequest().setAttribute("plBidPlan",this.plBidPlan);

            model.addDataContent("listPlBid",listPlBid);
            this.getRequest().setAttribute("listPlBid",this.listPlBid);

        }

        BpCustMember custMember  = (BpCustMember)this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(custMember != null){
            BpCustMember cust = bpCustMemberService.get(custMember.getId());
            List<BankCard> listAll = webBankcardService.getBankCardList(cust.getId());
            if(listAll.size()== 0){
                cust.setIsBankCard(0);
            }
            BigDecimal[]ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());
            if(ret != null){
                BigDecimal available = ret[3];
                cust.setAvailableInvestMoney(available);
            }
            model.addDataContent("cust",cust);
            this.getRequest().setAttribute("cust",cust);
        }

        setJsonString(model.toJSON());
        PlBidPlan plan = plBidPlanService.get(Long.parseLong(bidId));
        String proType = plan.getProType();
        boolean isApp = isApp();
        if(isApp){
            return SUCCESS;
        }else if("P_Dir".equals(proType)){
            return "combination_person";
        }else {
            return "combination";
        }
       /* String isApp = this.getRequest().getParameter("isApp");
        if("1".equals(isApp)){
            return SUCCESS;
        }else{
            return "combination";
        }*/

    }

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
                System.out.println(planPro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 标详情页：个人资产信息
     */
    private void getPersonalInfo(PlBidPlan pbp){
        BpPersionDirPro personDir = pbp.getBpPersionDirPro();
        if(personDir!=null){
            BpPersionDirPro bpPersionDirPro = bpPersionDirProService.get(personDir.getPdirProId());
            Person person = personService.getJobIncome(bpPersionDirPro.getPersionId().toString());
            plBidPlan.setMonthIncome(person==null?0:person.getJobincome()==null?0:person.getJobincome());//月收入
            List<CsPersonHouse> personHouse = csPersonHouseService.getByPersonId(bpPersionDirPro.getPersionId().toString());
            if(personHouse!=null&&personHouse.size()>0){
                plBidPlan.setHouseLoan("有");
                plBidPlan.setHouseAssets("有");
            }else{
                plBidPlan.setHouseLoan("无");
                plBidPlan.setHouseAssets("无");
            }
            List<CsPersonCar>  personCar = csPersonCarService. getByPersonId(bpPersionDirPro.getPersionId().toString());
            if(personCar!=null&&personCar.size()>0){
                plBidPlan.setCarLoan("有");
                plBidPlan.setCarAssets("有");
            }else{
                plBidPlan.setCarLoan("无");
                plBidPlan.setCarAssets("无");
            }
        }
    }

    //跳转风险协议
    public String termSheet(){
        return "agreement";
    }

    //升升投服务协议
    public  String service(){
        return "service";
    }


    private List<PlWebShowMaterials> findMaterials(String pid,String businessType){
        try{
            listMaterials=plWebShowMaterialsService.getByPidAndType(pid,businessType);
            for(PlWebShowMaterials m:listMaterials){
//                plWebShowMaterialsService.evict(m);
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
//                    plWebShowMaterialsService.evict(m);
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

}
