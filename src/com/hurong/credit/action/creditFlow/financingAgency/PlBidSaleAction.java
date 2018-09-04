package com.hurong.credit.action.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.customer.enterprise.EnterpriseService;
import com.hurong.credit.service.creditFlow.customer.person.PersonService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidSaleService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessOrProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionOrProService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.hurong.credit.util.freemarkerToWord.DocumentHandler;
import com.hurong.credit.util.freemarkerToWord.FileHelper;
import com.hurong.credit.util.freemarkerToWord.WordtoPdfUtil;
import com.thirdPayInterface.CommonDetail;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import com.thirdPayInterface.FuDianPay.FuDian;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author 
 *
 */
public class PlBidSaleAction extends BaseAction{
	@Resource
	private PlBidSaleService plBidSaleService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private PlManageMoneyTypeService plManageMoneyTypeService;
	@Resource
	private PlManageMoneyPlanService plManageMoneyPlanService;
	@Resource
	public ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	public BpCustMemberService bpCustMemberService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private BpPersionDirProService bpPersionDirProService;
	@Resource
	private BpBusinessDirProService bpBusinessDirProService;
	@Resource
	private BpFundProjectService bpFundProjectService;
	@Resource
	private PersonService personService;
	@Resource
	private EnterpriseService enterpriseService;
	@Resource
	private BpPersionOrProService bpPersionOrProService;
	@Resource
	private BpBusinessOrProService bpBusinessOrProService;
	@Resource
	private OpraterBussinessDataService opraterBussinessDataService;
	@Resource
	private  BpFundIntentService  bpFundIntentService; 
	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	private PlBidAuto bidAuto;
	
	private Pager canTransferingListpager;
	private Pager transferingListpager;
	private Pager transferedListpager;
	private Pager closeedListpager;
	private Pager buyedListpager;
	private PlBidSale plBidSale;
	private String verify_sms;
	private Long id;
	
	
	
	public String obligatoryrightTransfer() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {
			String show = this.getRequest().getParameter("show");
			if (show == null || show.equals("")) {
				this.getRequest().setAttribute("show", "canTransferingList");
			} else {
				this.getRequest().setAttribute("show", show);
			}
			getTransferingList(mem);
			getTransferedList(mem);
			getCloseedList(mem);
			getBuyedList(mem);
			getCanTransferingList(mem);

			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.Obligatoryright_Transfer)
					.getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";

	}

	
	
	public void getCanTransferingList(BpCustMember mem) {
		// 把超过30分钟没有付服务费成功的设置删除，即为没挂牌
		List<PlBidSale> list = plBidSaleService.getBySaleState(Short
				.valueOf("0"));
		for (PlBidSale l : list) {
			// System.out.println("(new Date()).getTime()="+(new
			// Date()).getTime()+"l.getSaleStartingTime().getTime()="+l.getSaleStartingTime().getTime());
			if (null == l.getSaleStartingTime()) {
				l.setSaleStartingTime(new Date());
			}
			if ((new Date()).getTime() - l.getSaleStartingTime().getTime() > 30 * 60 * 1000) {
				plBidSaleService.remove(l);
			}
		}
		if (canTransferingListpager == null) {
			canTransferingListpager = new Pager();
			canTransferingListpager.setPageNumber(pager == null ? 1 : pager
					.getPageNumber());
			canTransferingListpager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart(
				(canTransferingListpager.getPageNumber() - 1)
						* canTransferingListpager.getPageSize());
		filter.getPagingBean().setPageSize(
				canTransferingListpager.getPageSize());

		List<PlBidSale> canTransferingList = new ArrayList<PlBidSale>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("saleStatus", "canTransferingList");
		canTransferingList = plBidSaleService.getCanTransferingList(
				mem.getId(), filter.getPagingBean(), map);
		canTransferingListpager.setList(canTransferingList);
		canTransferingListpager.setTotalCount(filter.getPagingBean()
				.getTotalItems());
		this.getRequest()
				.setAttribute("canTransferingList", canTransferingList);
	}


	
	public void getTransferingList(BpCustMember mem) {
		// 把超过30分钟没有交易的设置为挂牌状态
		List<PlBidSale> list = plBidSaleService.getBySaleState(Short
				.valueOf("3"));
		for (PlBidSale l : list) {
			if (null == l.getSaleDealTime()) {
				l.setSaleDealTime(new Date());
			}
			if ((new Date()).getTime() - l.getSaleDealTime().getTime() > 30 * 60 * 1000) {
				l.setSaleStatus(Short.valueOf("1"));
			}
		}
		//
		if (transferingListpager == null) {
			transferingListpager = new Pager();
			transferingListpager.setPageNumber(pager == null ? 1 : pager
					.getPageNumber());
			transferingListpager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart(
				(transferingListpager.getPageNumber() - 1)
						* transferingListpager.getPageSize());
		filter.getPagingBean().setPageSize(transferingListpager.getPageSize());

		List<PlBidSale> transferingList = new ArrayList<PlBidSale>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "one");

		/*
		 * map.put("saleStatus", "transferingList");//挂牌中 transferingList=
		 * plBidSaleService.getCanTransferingList(mem.getId(), null, map);
		 */
		transferingList = plBidSaleService.transferingList(mem.getId(), filter
				.getPagingBean(), map, "");
		transferingListpager.setList(transferingList);
		transferingListpager.setTotalCount(filter.getPagingBean()
				.getTotalItems());
	}    
    
    
    
	public void getTransferedList(BpCustMember mem) {
		if (transferedListpager == null) {
			transferedListpager = new Pager();
			transferedListpager.setPageNumber(pager == null ? 1 : pager
					.getPageNumber());
			transferedListpager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart(
				(transferedListpager.getPageNumber() - 1)
						* transferedListpager.getPageSize());
		filter.getPagingBean().setPageSize(transferedListpager.getPageSize());

		List<PlBidSale> transferedList = new ArrayList<PlBidSale>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("saleStatus", "4");// 交易成功
		transferedList = plBidSaleService.outTransferingList(mem.getId(),
				filter.getPagingBean(), map);
		transferedListpager.setList(transferedList);
		transferedListpager.setTotalCount(filter.getPagingBean()
				.getTotalItems());
	}   
   
	public void getCloseedList(BpCustMember mem) {
		if (closeedListpager == null) {
			closeedListpager = new Pager();
			closeedListpager.setPageNumber(pager == null ? 1 : pager
					.getPageNumber());
			closeedListpager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart(
				(closeedListpager.getPageNumber() - 1)
						* closeedListpager.getPageSize());
		filter.getPagingBean().setPageSize(closeedListpager.getPageSize());

		List<PlBidSale> closeedList = new ArrayList<PlBidSale>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("saleStatus", "9,10");// 手动关闭
		closeedList = plBidSaleService.outTransferingList(mem.getId(), filter
				.getPagingBean(), map);
		closeedListpager.setList(closeedList);
		closeedListpager.setTotalCount(filter.getPagingBean().getTotalItems());
	}   
   
   
   
	public void getBuyedList(BpCustMember mem) {
		if (buyedListpager == null) {
			buyedListpager = new Pager();
			buyedListpager.setPageNumber(pager == null ? 1 : pager
					.getPageNumber());
			buyedListpager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart(
				(buyedListpager.getPageNumber() - 1)
						* buyedListpager.getPageSize());
		filter.getPagingBean().setPageSize(buyedListpager.getPageSize());

		List<PlBidSale> buyedList = new ArrayList<PlBidSale>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("saleStatus", "4");// 交易成功
		buyedList = plBidSaleService.inTransferingList(mem.getId(), filter
				.getPagingBean(), map);
		buyedListpager.setList(buyedList);
		buyedListpager.setTotalCount(filter.getPagingBean().getTotalItems());

	}   
   
   /**
    * 新版UI个人中心债权交易
    * 模板加载
    * @return
    */
   public String loadTemplate(){
	   this.getSession().setAttribute("highlight", 10);
	   if(isLogin()){
		    List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
			this.getRequest().setAttribute("fileList", fileList);
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AllSaleTemplate).getTemplateFilePath());
	   }else{
		   this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
	   }
	   return "freemarker";
   }
   
   /**
    * 新版UI个人中心债权交易----VIP
    * 模板加载
    * @return
    */
   public String loadTemplateVip(){
	   this.getSession().setAttribute("highlight", 11);
	   BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
	   if(mem!=null){
		   mem = bpCustMemberService.get(mem.getId());
		   if(mem.getIsVip()!=null&&mem.getIsVip()==1){
			    List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
				this.getRequest().setAttribute("fileList", fileList);
			    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AllSaleTemplateVIP).getTemplateFilePath());
		   }else{
			   webMsgInstance("0", Constants.CODE_FAILED, "您不是会员，无法查看","", "", "", "", "");
			   this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			   return "freemarker";
		   }
	   }else{
		   this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
	   }
	   return "freemarker";
   }
   
   /**
    * 债权交易笔数统计
    */
   public String loadSaleData(){
	   if(isLogin()){
		   BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		   jsonString = plBidSaleService.queryAllData(mem.getId());
	   }else{
		   this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		   return "freemarker";
	   }
	   return SUCCESS;
   }
   
   
   
   /**
    * 债权交易展示记录
    * 
    */
   public String showSaleRecord(){
	  BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	  if(isLogin()){
		  jsonString = plBidSaleService.queryAllSaleList(mem.getId(),getRequest());
	  }else{
		  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		  return "freemarker";
	  }  
	 return SUCCESS;
   }
   
   /**
    * 债权交易展示记录---VIP
    * 
    */
   public String showSaleRecordVip(){
	  BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
	  if(isLogin()){
		  jsonString = plBidSaleService.queryAllSaleListVip(mem.getId(),getRequest());
	  }else{
		  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		  return "freemarker";
	  }  
	 return SUCCESS;
   }
   
   
   /**
    * 判断是否登录的公用方法
    * @return
    */
	public boolean isLogin(){
		Boolean boo = false;
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			boo = true;
		}
		return boo;
	}

/*<<<<<<< .working
	public void setBuyedListpager(Pager buyedListpager) {
		this.buyedListpager = buyedListpager;
	}

	public PlBidSale getPlBidSale() {
		return plBidSale;
	}

	public void setPlBidSale(PlBidSale plBidSale) {
		this.plBidSale = plBidSale;
	}
	 public String obligatoryrightTransfer(){
			
	    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
					MyUserSession.MEMBEER_SESSION);
			
			if (mem != null) {
				 String show = this.getRequest().getParameter("show");
				if(show==null||show.equals("")){
					this.getRequest().setAttribute("show", "canTransferingList");
				}else{
					this.getRequest().setAttribute("show", show);
				}
				getTransferingList(mem);
				getTransferedList(mem);
				getCloseedList(mem);
				getBuyedList(mem);
				getCanTransferingList(mem);
				
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.Obligatoryright_Transfer).getTemplateFilePath());
			}else {
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.LOGIN).getTemplateFilePath());
			}
			return "freemarker";
			
		}
	 public void getCanTransferingList(BpCustMember mem){ 
		 
			//把超过30分钟没有付服务费成功的设置删除，即为没挂牌
		 List<PlBidSale> list= plBidSaleService.getBySaleState(Short.valueOf("0"));
		     for(PlBidSale l:list){
		    	// System.out.println("(new Date()).getTime()="+(new Date()).getTime()+"l.getSaleStartingTime().getTime()="+l.getSaleStartingTime().getTime());
		    	 if(null==l.getSaleStartingTime()){
		    		 l.setSaleStartingTime(new Date());
		    	 } 
		    	 if((new Date()).getTime()-l.getSaleStartingTime().getTime()>30*60*1000){
		    		  plBidSaleService.remove(l);
		    	  }
		     }		 
			 if (canTransferingListpager == null) {
				 canTransferingListpager = new Pager();
				 canTransferingListpager.setPageNumber(pager==null?1:pager.getPageNumber());
				 canTransferingListpager.setPageSize(10);
			 	}
			 QueryFilter filter = new QueryFilter(getRequest());
			 filter.getPagingBean().setStart((canTransferingListpager.getPageNumber() - 1) * canTransferingListpager.getPageSize());
			 filter.getPagingBean().setPageSize(canTransferingListpager.getPageSize());
			 
			 List<PlBidSale> canTransferingList = new ArrayList<PlBidSale>();
			 Map<String, String> map=new HashMap<String, String>();
			 map.put("saleStatus", "canTransferingList");
			 canTransferingList= plBidSaleService.getCanTransferingList(mem.getId(),filter.getPagingBean(), map);
			 canTransferingListpager.setList(canTransferingList);
			 canTransferingListpager.setTotalCount(filter.getPagingBean().getTotalItems());
			 this.getRequest().setAttribute("canTransferingList", canTransferingList);
	 }
    public void getTransferingList(BpCustMember mem){
    	//把超过30分钟没有交易的设置为挂牌状态
    	 List<PlBidSale> list= plBidSaleService.getBySaleState(Short.valueOf("3"));
	     for(PlBidSale l:list){
	    	 if(null==l.getSaleDealTime()){
	    		 l.setSaleDealTime(new Date());
	    	 }
	    	  if((new Date()).getTime()-l.getSaleDealTime().getTime()>30*60*1000){
	    		  l.setSaleStatus(Short.valueOf("1"));
	    	  }
	     }
	     //
    	 if (transferingListpager == null) {
   	    	transferingListpager = new Pager();
   	    	transferingListpager.setPageNumber(pager==null?1:pager.getPageNumber());
   	    	transferingListpager.setPageSize(10);
 			}
	 		 QueryFilter filter = new QueryFilter(getRequest());
	 		 filter.getPagingBean().setStart((transferingListpager.getPageNumber() - 1) * transferingListpager.getPageSize());
	 		 filter.getPagingBean().setPageSize(transferingListpager.getPageSize());
	 		 
	 		 List<PlBidSale> transferingList = new ArrayList<PlBidSale>();
	 		 Map<String, String> map=new HashMap<String, String>();
	 		 map.put("type", "one");
 		 
 		 map.put("saleStatus", "transferingList");//挂牌中
 		 transferingList= plBidSaleService.getCanTransferingList(mem.getId(), null, map);
 		 transferingList= plBidSaleService.transferingList(mem.getId(), filter.getPagingBean(), map,"");
 		 transferingListpager.setList(transferingList);
 		 transferingListpager.setTotalCount(filter.getPagingBean().getTotalItems());
	    }
   public void  getTransferedList(BpCustMember mem){
	   if (transferedListpager == null) {
		   transferedListpager = new Pager();
		   transferedListpager.setPageNumber(pager==null?1:pager.getPageNumber());
		   transferedListpager.setPageSize(10);
		}
	 QueryFilter filter = new QueryFilter(getRequest());
	 filter.getPagingBean().setStart((transferedListpager.getPageNumber() - 1) * transferedListpager.getPageSize());
	 filter.getPagingBean().setPageSize(transferedListpager.getPageSize());
	 
	 List<PlBidSale> transferedList = new ArrayList<PlBidSale>();
	 Map<String, String> map=new HashMap<String, String>();
	 map.put("saleStatus", "4");//交易成功
	 transferedList= plBidSaleService.outTransferingList(mem.getId(), filter.getPagingBean(), map);
	 transferedListpager.setList(transferedList);
	 transferedListpager.setTotalCount(filter.getPagingBean().getTotalItems());
	    }
   public void getCloseedList(BpCustMember mem){
		
	   if (closeedListpager == null) {
		   closeedListpager = new Pager();
		   closeedListpager.setPageNumber(pager==null?1:pager.getPageNumber());
		   closeedListpager.setPageSize(10);
		}
	 QueryFilter filter = new QueryFilter(getRequest());
	 filter.getPagingBean().setStart((closeedListpager.getPageNumber() - 1) * closeedListpager.getPageSize());
	 filter.getPagingBean().setPageSize(closeedListpager.getPageSize());
	 
=======
>>>>>>> .merge-right.r128735*/
   
   
   
   
   public String canTransferingList(){
	    this.getSession().setAttribute("highlight", 3);
	    BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			getCanTransferingList(mem);
			bpCustMember = mem;
			BpCustMember temp= bpCustMemberService.get(mem.getId());	
			commoon(bpCustMember);
			bpCustMember.setScore(temp.getScore());
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
			   List<PlBidSale> canTransferingList= canTransferingListpager.getList();
			   for(int i=0;i<canTransferingList.size();i++){
				   canTransferingList.get(i).setId((long)i);
			   }
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(canTransferingList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate","startDate"});
				buff.append(json.serialize(canTransferingList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
		    this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CanTransfering_List).getTemplateFilePath());
		}else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
   }
   
   private void commoon(BpCustMember mem){
/*		bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
		String[] moneyArr = null;
		String thirdPayConfig=configMap.get("thirdPayConfig").toString();
		String thirdPayType=configMap.get("thirdPayType").toString();
		
		try {
			if(thirdPayConfig.equals(Constants.FUIOU)&&thirdPayType.equals("0")){
				bpCustMember=fuiouService.getCurrentMoney(mem);
				bpCustMember = bpCustMemberService.get(mem.getId());
				BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(),ObSystemAccount.type0.toString());
				bpCustMember.setAllInterest(ret[5]);
				bpCustMember.setPrincipalRepayment(ret[6]);
				bpCustMember.setTotalInvestMoney(ret[4]);
			}else{
				bpCustMember = bpCustMemberService.get(mem.getId());
				bpCustMember = obSystemAccountService.getAccountSumMoney(mem);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		int percent = 0;
		if("1".equals(bpCustMember.getIsCheckEmail())&&bpCustMember.getIsCheckEmail()!=null){
			//判断邮箱是否验证
			percent += 10;
		}
		if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
			//判断手机是否验证
			percent += 20;
		}
		if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
			//判断是否实名认证
			percent += 40;
		}
		if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
			//判断手机是否验证
			percent += 30;
		}
		//保存信誉等级
		this.getRequest().setAttribute("percent", percent);
		//保存客户的信誉信息
		this.getRequest().setAttribute("bpCustMember", bpCustMember);
*/
			bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
			if(bpCustMember!=null){
				
			}else{
				bpCustMember = mem;
			}
			
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
				
				bpCustMember.setIsCheckCard("1");
				System.out.println("重要的事情要说三遍"+bpCustMember.getIsCheckCard());
				//你只说了一遍哦
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
			//判断材料是否上传
			this.getRequest().setAttribute("bpCustMember", bpCustMember);
   }
   
   
   
   
	public String transferingList() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {
			getTransferingList(mem);
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
			   List<PlBidSale> transferingList= transferingListpager.getList();
			   for(int i=0;i<transferingList.size();i++){
				   transferingList.get(i).setId((long)i);
			   }
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(transferingList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
				buff.append(json.serialize(transferingList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.Transfering_List).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}  
  
	public String transferedList() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {
			getTransferedList(mem);
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
			   List<PlBidSale> transferedList= transferedListpager.getList();
			   for(int i=0;i<transferedList.size();i++){
				   transferedList.get(i).setId((long)i);
			   }
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(transferedList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
				buff.append(json.serialize(transferedList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.Transfered_List).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	} 
 
	public String closeedList() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {
			getCloseedList(mem);
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
			   List<PlBidSale> closeedList= closeedListpager.getList();
			   for(int i=0;i<closeedList.size();i++){
				   closeedList.get(i).setId((long)i);
			   }
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(closeedList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
				buff.append(json.serialize(closeedList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.Closeed_List).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}

	public String buyedList() {

		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {

			getBuyedList(mem);
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
			   List<PlBidSale> buyedList= buyedListpager.getList();
			   for(int i=0;i<buyedList.size();i++){
				   buyedList.get(i).setId((long)i);
			   }
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(buyedList.size()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"intentDate"});
				buff.append(json.serialize(buyedList));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
		 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.Buyed_List).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
   
	public BigDecimal[] gettransferMoney(PlBidInfo plBidInfo, String startDate) {
		BigDecimal[] ret = new BigDecimal[5];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PlBidPlan plBidPlan = plBidInfo.getPlBidPlan();
		BigDecimal baseMoney = new BigDecimal(30);
		if (plBidPlan.getPayMoneyTimeType().equals("monthPay")) {
			baseMoney = new BigDecimal(30);
		} else if (plBidPlan.getPayMoneyTimeType().equals("seasonPay")) {
			baseMoney = new BigDecimal(90);
		} else if (plBidPlan.getPayMoneyTimeType().equals("yearPay")) {
			baseMoney = new BigDecimal(360);
		}
		// 借款金额
		BigDecimal loadMoney = bpFundIntentDao.getPrincipalLending(plBidPlan
				.getBidId(), plBidInfo.getOrderNo());
		// 已经还的本金
		BigDecimal getMoney = bpFundIntentDao.getAllAfterPrincipalMoney(
				plBidPlan.getBidId(), plBidInfo.getOrderNo());
		//
		BigDecimal saleMoney = loadMoney.subtract(getMoney);
		// 结算的利息
		BigDecimal notLoanInterestToEnddate = bpFundIntentDao
				.getnotLoanInterestToEnddate(plBidPlan.getBidId(), plBidInfo
						.getOrderNo(), new Date());
		BpFundIntent bpFundIntentnext = bpFundIntentDao.getnotLoanInterestnext(
				plBidPlan.getBidId(), plBidInfo.getOrderNo(), new Date());
		BpFundIntent bpFundIntentlast = bpFundIntentDao.getnotLoanInterestlast(
				plBidPlan.getBidId(), plBidInfo.getOrderNo(), new Date());
		BigDecimal sumInteresteMoney = notLoanInterestToEnddate;

		if (null != bpFundIntentlast && null != bpFundIntentnext) {
			Integer Interesteddays = DateUtil.getDaysBetweenDate(sdf
					.format(bpFundIntentlast.getIntentDate()), sdf
					.format(new Date()));
			// 计算出这期利息计算时的实际天数
			baseMoney = new BigDecimal(DateUtil.getDaysBetweenDate(sdf
					.format(bpFundIntentlast.getIntentDate()), sdf
					.format(bpFundIntentnext.getIntentDate())));
			BigDecimal InteresteMoney = bpFundIntentnext.getNotMoney()
					.multiply(new BigDecimal(Interesteddays.toString()))
					.divide(baseMoney, BigDecimal.ROUND_HALF_UP, 3);
			sumInteresteMoney = sumInteresteMoney.add(InteresteMoney);
		}
		// 介于开始到第一期还款日之间
		if (null == bpFundIntentlast && null != bpFundIntentnext) {
			Integer Interesteddays = DateUtil.getDaysBetweenDate(startDate, sdf
					.format(new Date()));
			// 计算出这期利息计算时的实际天数
			baseMoney = new BigDecimal(DateUtil.getDaysBetweenDate(startDate,
					sdf.format(bpFundIntentnext.getIntentDate())));
			BigDecimal InteresteMoney = bpFundIntentnext.getNotMoney()
					.multiply(new BigDecimal(Interesteddays.toString()))
					.divide(baseMoney, BigDecimal.ROUND_HALF_UP, 3);
			sumInteresteMoney = sumInteresteMoney.add(InteresteMoney);
		}
		// 已经过了债权到期日
		if (null != bpFundIntentlast && null == bpFundIntentnext) {
			sumInteresteMoney = sumInteresteMoney;
		}

		/*
		 * BigDecimalafterLoanInterestfromEnddate=bpFundIntentDao.
		 * getafterLoanInterestfromEnddate
		 * (plBidPlan.getBidId(),plBidInfo.getOrderNo(),new Date());
		 * sumInteresteMoney
		 * =sumInteresteMoney.subtract(afterLoanInterestfromEnddate);
		 * this.getRequest().setAttribute("sumInteresteMoney",
		 * sumInteresteMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
		 */
		// 罚息
		BigDecimal accrualMoney = new BigDecimal("0");
		ret[0] = saleMoney.setScale(2, BigDecimal.ROUND_HALF_UP);//未还本金
		ret[1] = sumInteresteMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		ret[2] = accrualMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
		ret[3] = loadMoney;//接款金额
		ret[4] = getMoney;//已还本金
		return ret;
	}
/*<<<<<<< .working
	//借款金额
	BigDecimal loadMoney= bpFundIntentDao.getPrincipalLending(plBidPlan.getBidId(),plBidInfo.getOrderNo());
	//已经还的本金
	BigDecimal getMoney= bpFundIntentDao.getAllAfterPrincipalMoney(plBidPlan.getBidId(),plBidInfo.getOrderNo());
	//
	BigDecimal saleMoney=loadMoney.subtract(getMoney);
	//结算的利息
	BigDecimal notLoanInterestToEnddate= bpFundIntentDao.getnotLoanInterestToEnddate(plBidPlan.getBidId(),plBidInfo.getOrderNo(),new Date());
	BpFundIntent bpFundIntentnext= bpFundIntentDao.getnotLoanInterestnext(plBidPlan.getBidId(),plBidInfo.getOrderNo(),new Date());
	BpFundIntent bpFundIntentlast= bpFundIntentDao.getnotLoanInterestlast(plBidPlan.getBidId(),plBidInfo.getOrderNo(),new Date());
	BigDecimal sumInteresteMoney=notLoanInterestToEnddate;
	
	if(null!=bpFundIntentlast&&null!=bpFundIntentnext){
		Integer Interesteddays=DateUtil.getDaysBetweenDate(sdf.format(bpFundIntentlast.getIntentDate()),sdf.format(new Date()));//此处应该是nextDate吧
		//计算出这期利息计算时的实际天数
		baseMoney = new BigDecimal(DateUtil.getDaysBetweenDate(sdf.format(bpFundIntentlast.getIntentDate()),sdf.format(bpFundIntentnext.getIntentDate())));
		BigDecimal InteresteMoney=bpFundIntentnext.getNotMoney().multiply(new BigDecimal(Interesteddays.toString())).divide(baseMoney,BigDecimal.ROUND_HALF_UP,3);
		 sumInteresteMoney=sumInteresteMoney.add(InteresteMoney);
	}
	//介于开始到第一期还款日之间
	if(null==bpFundIntentlast&&null!=bpFundIntentnext){
		Integer Interesteddays=DateUtil.getDaysBetweenDate(startDate,sdf.format(new Date()));
		//计算出这期利息计算时的实际天数
		baseMoney = new BigDecimal(DateUtil.getDaysBetweenDate(startDate,sdf.format(bpFundIntentnext.getIntentDate())));
		BigDecimal InteresteMoney=bpFundIntentnext.getNotMoney().multiply(new BigDecimal(Interesteddays.toString())).divide(baseMoney,BigDecimal.ROUND_HALF_UP,3);
		 sumInteresteMoney=sumInteresteMoney.add(InteresteMoney);
	}
	//已经过了债权到期日
	if(null!=bpFundIntentlast&&null==bpFundIntentnext){
		 sumInteresteMoney=sumInteresteMoney;
	}
	
	BigDecimal afterLoanInterestfromEnddate=bpFundIntentDao.getafterLoanInterestfromEnddate(plBidPlan.getBidId(),plBidInfo.getOrderNo(),new Date());
	sumInteresteMoney=sumInteresteMoney.subtract(afterLoanInterestfromEnddate);
	this.getRequest().setAttribute("sumInteresteMoney", sumInteresteMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
	//罚息
	BigDecimal accrualMoney=new BigDecimal("0");
	ret[0]=saleMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
	ret[1]=sumInteresteMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
	ret[2]=accrualMoney.setScale(2,BigDecimal.ROUND_HALF_UP);
	ret[3]=loadMoney;
	   return ret;
   }
	public String orStartTransfer(){
		
		String isMobile =this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
=======*/

	public String orStartTransfer() {
		String isMobile =this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);

		if (mem != null) {
			String type = this.getRequest().getParameter("type");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");

			String yearAccrualRate = this.getRequest().getParameter(
					"yearAccrualRate");
			String intentDate = this.getRequest().getParameter("intentDate");
			if (StringUtils.isNotEmpty(intentDate)) {
				intentDate = intentDate.substring(0,10);
			}
			String startDate = this.getRequest().getParameter("startDate");
			if (StringUtils.isNotEmpty(startDate)) {
				startDate = startDate.substring(0,10);
			}
			int days = DateUtil.getDaysBetweenDate(sdf.format(new Date()),
					intentDate);

			this.getRequest().setAttribute("days", days);
			this.getRequest().setAttribute("yearAccrualRate", yearAccrualRate);
			this.getRequest().setAttribute("intentDate", intentDate);
			this.getRequest().setAttribute("startDate", startDate);
			this.getRequest().setAttribute("newDate", sdf1.format(new Date()));

			String bidInfoId = this.getRequest().getParameter("bidInfoId");
			PlBidInfo plBidInfo = plBidInfoService.get(Long.valueOf(bidInfoId));
			PlBidPlan plBidPlan = plBidInfo.getPlBidPlan();
			this.getRequest().setAttribute("bidProName",
					plBidPlan.getBidProName());

			BigDecimal[] retmoney = gettransferMoney(plBidInfo, startDate);// 计算saleMoney,accrualMoney,sumInteresteMoney
			BigDecimal saleMoney = retmoney[0];
			BigDecimal sumInteresteMoney = retmoney[1];
			BigDecimal accrualMoney = retmoney[2];
			BigDecimal loadMoney = retmoney[3];

			this.getRequest().setAttribute("sumInteresteMoney",
					sumInteresteMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			// 本金

			BigDecimal getMoney = loadMoney.subtract(saleMoney);
			this.getRequest().setAttribute("saleMoney",
					saleMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("loadMoney",
					loadMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("getMoney",
					getMoney.setScale(2, BigDecimal.ROUND_HALF_UP));

			// 利息
			BigDecimal notLoanInterest = bpFundIntentDao.getnotLoanInterest(
					plBidPlan.getBidId(), plBidInfo.getOrderNo());
			BigDecimal loanInterest = bpFundIntentDao.getLoanInterest(plBidPlan
					.getBidId(), plBidInfo.getOrderNo());
			BigDecimal afterInterest = loanInterest.subtract(notLoanInterest);
			this.getRequest().setAttribute("notLoanInterest",
					notLoanInterest.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("loanInterest",
					loanInterest.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.getRequest().setAttribute("afterInterest",
					afterInterest.setScale(2, BigDecimal.ROUND_HALF_UP));

			this.getRequest().setAttribute("accrualMoney",
					accrualMoney.setScale(2, BigDecimal.ROUND_HALF_UP));

			this.getRequest().setAttribute("bidInfoId", bidInfoId);
			this.getRequest().setAttribute("orderNo", plBidInfo.getOrderNo());
			this.getRequest().setAttribute("bidPlanID", plBidPlan.getBidId());
			this.getRequest().setAttribute("telephone", mem.getTelphone());
			this.getRequest().setAttribute("newdate", sdf1.format(new Date()));
			BigDecimal sumMoney = saleMoney.add(sumInteresteMoney).add(
					accrualMoney);
			if (type.equals("buy")) {
				String saleId = this.getRequest().getParameter("saleId");
				plBidSale = plBidSaleService.get(Long.valueOf(saleId));
				BigDecimal changeMoney = saleMoney.multiply(
						new BigDecimal(plBidSale.getChangeMoneyRate())).divide(
						new BigDecimal("1000"));
				if (plBidSale.getChangeMoneyType().equals(Short.valueOf("1"))) {
					changeMoney = new BigDecimal("0").subtract(changeMoney);
				}
				this.getRequest().setAttribute("changeMoney",
						changeMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
				this.getRequest().setAttribute("changeMoneyRate",
						plBidSale.getChangeMoneyRate());
				this.getRequest().setAttribute("changeMoneyType",
						plBidSale.getChangeMoneyType());
				this.getRequest().setAttribute("saleId", saleId);
				this.getRequest().setAttribute("type", "buy");
				this.getRequest().setAttribute("transferFee", "0");
				sumMoney = sumMoney.add(changeMoney);
			} else if (type.equals("see")) {
				String saleId = this.getRequest().getParameter("saleId");
				plBidSale = plBidSaleService.get(Long.valueOf(saleId));
				BigDecimal changeMoney = saleMoney.multiply(
						new BigDecimal(plBidSale.getChangeMoneyRate())).divide(
						new BigDecimal("1000"));
				if (plBidSale.getChangeMoneyType().equals(Short.valueOf("1"))) {
					changeMoney = new BigDecimal("0").subtract(changeMoney);
				}
				this.getRequest().setAttribute("changeMoney",
						changeMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
				this.getRequest().setAttribute("changeMoneyRate",
						plBidSale.getChangeMoneyRate());
				this.getRequest().setAttribute("changeMoneyType",
						plBidSale.getChangeMoneyType());

				this.getRequest().setAttribute("type", "see");
				sumMoney = sumMoney.add(changeMoney);
				this.getRequest().setAttribute(
						"transferFee",
						sumMoney.multiply(new BigDecimal(0.01)).setScale(2,
								BigDecimal.ROUND_HALF_UP));
				// 已卖出债权与已购买债权结算清单日期为交易成功日期
				this.getRequest()
						.setAttribute(
								"newDate",
								sdf1.format(null != plBidSale
										.getSaleSuccessTime() ? plBidSale
										.getSaleSuccessTime() : new Date()));
			} else if (type.equals("transferpay")) {
				String saleId = this.getRequest().getParameter("saleId");
				plBidSale = plBidSaleService.get(Long.valueOf(saleId));
				BigDecimal changeMoney = saleMoney.multiply(
						new BigDecimal(plBidSale.getChangeMoneyRate())).divide(
						new BigDecimal("1000"));
				if (plBidSale.getChangeMoneyType().equals(Short.valueOf("1"))) {
					changeMoney = new BigDecimal("0").subtract(changeMoney);
				}
				this.getRequest().setAttribute("changeMoney",
						changeMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
				this.getRequest().setAttribute("changeMoneyRate",
						plBidSale.getChangeMoneyRate());
				this.getRequest().setAttribute("changeMoneyType",
						plBidSale.getChangeMoneyType());

				this.getRequest().setAttribute("type", "transferpay");
				sumMoney = sumMoney.add(changeMoney);
				this.getRequest().setAttribute(
						"transferFee",
						sumMoney.multiply(new BigDecimal(0.01)).setScale(2,
								BigDecimal.ROUND_HALF_UP));
			} else {

				this.getRequest().setAttribute("transferFee", "0");
				this.getRequest().setAttribute("changeMoney",
						new BigDecimal("0"));
				this.getRequest().setAttribute("changeMoneyRate", "0");
				this.getRequest().setAttribute("changeMoneyType", "1");
				this.getRequest().setAttribute("type", "transfer");

			}
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":true,\"data\":");
				JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","opendate",
						"bidEndTime","startDate");
				buff.append(json.serialize(plBidSale));
				buff.append(",\"days\":");//天数
				buff.append(json.serialize(days));
				buff.append(",\"yearAccrualRate\":");//年华
				buff.append(json.serialize(yearAccrualRate));
				buff.append(",\"startDate\":");//起始日
				buff.append(json.serialize(startDate));
				buff.append(",\"intentDate\":");//到期日
				buff.append(json.serialize(intentDate));
				buff.append(",\"newDate\":");//当前
				buff.append(json.serialize(sdf1.format(new Date())));
				buff.append(",\"sumInteresteMoney\":");//当前
				buff.append(json.serialize(sumInteresteMoney));
				buff.append(",\"accrualMoney\":");//当前
				buff.append(json.serialize(accrualMoney));
				buff.append(",\"loadMoney\":");//歉收利息
				buff.append(json.serialize(loadMoney));
				buff.append(",\"notLoanInterest\":");//歉收利息
				buff.append(json.serialize(notLoanInterest));
				buff.append(",\"loanInterest\":");//歉收利息
				buff.append(json.serialize(loanInterest));
				buff.append(",\"afterInterest\":");//歉收利息
				buff.append(json.serialize(afterInterest));
				
				buff.append(",\"saleMoney\":"); 
				buff.append(json.serialize(saleMoney));
				
				// 已支付收益
				buff.append(",\"getMoney\":"); 
				buff.append(json.serialize(getMoney));
				
				// 总金额
				buff.append(",\"sumMoney\":"); 
				buff.append(json.serialize(sumMoney));
				
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
			this.getRequest().setAttribute("sumMoney",sumMoney.setScale(2, BigDecimal.ROUND_HALF_UP));
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.OrStartTransfer_Form).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";

	}

	/**
	 * 债权挂牌方法（调用第三方   预收手续费）
	 * @return
	 */
	public String saveorStartTransfer(){
		BpCustMember mem1 = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem1);
		if((Boolean) usercondition[0]){
			 String bidInfoId = this.getRequest().getParameter("bidInfoId");
			 List<PlBidSale> lpbs=plBidSaleService.getBySaleState(Long.valueOf(bidInfoId),"0,1,3");//一旦变成有0的记录就说明有人在挂牌了，1和3没有是转让的前提，（其实能点进来就说明它肯定不错在1，3,就是怕人家直接用地址所以才加的）
			 if(null!=lpbs&&lpbs.size()!=0){
				 webMsgInstance("0", Constants.CODE_FAILED, "抱歉，此债权可能正在挂牌", "", "", "", "","");
				 this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				 return "freemarker";
			 }
			 
			 String startDate = this.getRequest().getParameter("startDate");
			 String changeMoneyRate = this.getRequest().getParameter("changeMoneyRate");
			 String changeMoneyType = this.getRequest().getParameter("changeMoneyType");
			
			 plBidSale=new PlBidSale();
			 PlBidInfo plBidInfo=plBidInfoService.get(Long.valueOf(bidInfoId));
		     PlBidPlan  plBidPlan = plBidInfo.getPlBidPlan();
			 BigDecimal[] retmoney =gettransferMoney(plBidInfo,startDate);//计算saleMoney,accrualMoney,sumInteresteMoney
			 BigDecimal saleMoney= retmoney[0];
			 BigDecimal sumInteresteMoney= retmoney[1];
			 BigDecimal accrualMoney= retmoney[2];
			 BigDecimal changeMoney=saleMoney.multiply(new BigDecimal(changeMoneyRate)).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
			 if(changeMoneyType.equals("1")){
				changeMoney=new BigDecimal(0).subtract(changeMoney);
			 }
			 BigDecimal sumMoney=saleMoney.add(sumInteresteMoney).add(accrualMoney).add(changeMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
//			 BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.005)).setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
			 BpCustMember mem=bpCustMemberService.get(plBidInfo.getNewInvestPersonId()!=null?plBidInfo.getNewInvestPersonId():plBidInfo.getUserId());
			
			 
			 plBidSale.setBidInfoID(plBidInfo.getId());
		     plBidSale.setBidPlanID(plBidPlan.getBidId());
		     plBidSale.setBeginMoney(saleMoney);
		     plBidSale.setSaleMoney(saleMoney);
		     plBidSale.setOldCustID(plBidInfo.getNewInvestPersonId()!=null?plBidInfo.getNewInvestPersonId():plBidInfo.getUserId());
		     plBidSale.setOldCustName(plBidInfo.getUserName());
		     plBidSale.setOutCustID(plBidInfo.getNewInvestPersonId()!=null?plBidInfo.getNewInvestPersonId():plBidInfo.getUserId());
		     plBidSale.setOutCustName(plBidInfo.getNewInvestPersonId()!=null?plBidInfo.getNewInvestPersonName():plBidInfo.getUserName());
		     plBidSale.setChangeMoneyRate(Integer.valueOf(changeMoneyRate));
		     plBidSale.setChangeMoneyType(Short.valueOf(changeMoneyType));
		     plBidSale.setIsVip(plBidPlan.getIsVip());
			 plBidSaleService.save(plBidSale);
			 CommonResponse cr=new CommonResponse();
			 String cardNo=ContextUtil.createRuestNumber();//生成第三需要的流水号
			 CommonRequst cq=new CommonRequst();
			 cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
			 cq.setMerPriv(cardNo+plBidSale.getId());//商户私有域
			 cq.setRequsetNo(cardNo+plBidSale.getId());//请求流水号 "-bid-"+
			 cq.setTransferName(ThirdPayConstants.TN_HANGDEAL);//实际业务用途
			 cq.setBussinessType(ThirdPayConstants.BT_HANGDEAL);//接口类型
			 List<CommonDetail> list=new ArrayList<CommonDetail>();
			 CommonDetail dt=new CommonDetail();
			 dt.setAmount(transferFee.toString());//转账金额
			 dt.setTargetUserType("plateForm");//收款人用户类型
			 list.add(dt);
			 cq.setContractNo("");//预授权合同号   富友
			 cq.setDetailList(list);//转账记录
			 cq.setBidId(plBidSale.getId().toString());//标的号
			 cq.setPlanMoney(plBidPlan.getBidMoney());//标的金额
			 cq.setAmount(transferFee);//转账金额
			 //判断个人企业
			 if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户
				 cq.setAccountType(0);
			 }else{//企业用户
				 cq.setAccountType(1);
			 }
			 if(transferFee.compareTo(new BigDecimal(0))>0){
				 cr=ThirdPayInterfaceUtil.thirdCommon(cq);
			 }else{
				 cr.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			 }
			
			 if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
					 plBidSale.setPreTransferFee(transferFee);
					 plBidSale.setPreTransferFeeRequestNo(cardNo+plBidSale.getId());//+"-bid-"+plBidSale.getId()
					 plBidSale.setSaleStartingTime(new Date());
					 plBidSale.setSaleStatus(Short.valueOf("0"));
					 plBidSaleService.save(plBidSale);
					 return "freemarker";
			 }else  if(CommonResponse.RESPONSECODE_SUCCESS.equals(cr.getResponsecode())){
				 plBidSale.setPreTransferFee(transferFee);
				 plBidSale.setPreTransferFeeRequestNo(cardNo+plBidSale.getId());//+"-bid-"+plBidSale.getId()
				 plBidSale.setSaleStatus(Short.valueOf("1"));
				 plBidSale.setSaleStartTime(new Date());
				 plBidSale.setPreAuthorizationNum(cardNo+plBidSale.getId());
				 plBidSaleService.save(plBidSale);
				 //调用业务处理方法
//					Map<String,String> map = new HashMap<String, String>();
//					map.put("requestNo", cardNo+plBidSale.getId());
//					map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
//					 if(transferFee.compareTo(new BigDecimal(0))>0){
//						 opraterBussinessDataService.doObligationPublish(map);
//					 }
					webMsgInstance("0", Constants.CODE_SUCCESS, "挂牌成功","", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				 return "freemarker";
			 } else{
				 webMsgInstance("0",cr.getResponsecode(),cr.getResponseMsg(), "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			 }
		}else {
			forwardPage=usercondition[2].toString();
			webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
		
	}
	
	
	/**
	 * 购买债权
	 * 富滇银行对接
	 * @return
	 */
	public String buyorStartTransfer(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String isMobile = this.getRequest().getParameter("isMobile");
		try{
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
				forwardPage=DynamicConfig.MOBILE_MESSAGE;
			}
			/**
			 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				String startDate = this.getRequest().getParameter("startDate");
				String bidInfoId = this.getRequest().getParameter("bidInfoId");
				PlBidInfo plBidInfo=plBidInfoService.get(Long.valueOf(bidInfoId));
				PlBidPlan  plBidPlan = plBidInfo.getPlBidPlan();
				String saleId = this.getRequest().getParameter("saleId");
				plBidSale=plBidSaleService.get(Long.valueOf(saleId));
				//得到借款人信息
				BpCustMember member = bpCustMemberService.getByLoginName(plBidPlan.getReceiverP2PAccountNumber());
				//得到原始债权人信息
				BpCustMember oldCust = bpCustMemberService.get(plBidSale.getOldCustID());
				if(!plBidSale.getSaleStatus().equals(Short.valueOf("1"))){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，此债权可能已经被取消或已被购买", "", "", "", "","");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					return "freemarker";
				}
				if(plBidSale.getOutCustID().equals(mem.getId())){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，自己不能买自己的债权", "", "", "", "","");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					return "freemarker";
			    }
				if(member.getId().equals(mem.getId())){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，借款人不能买自己的借款项目的债权", "", "", "", "","");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					return "freemarker";
				}
				
				BigDecimal[] retmoney =gettransferMoney(plBidInfo,startDate);//计算saleMoney,accrualMoney,sumInteresteMoney
				BigDecimal saleMoney= retmoney[0];//未还金额
				BigDecimal sumInteresteMoney= retmoney[1];
				BigDecimal accrualMoney= retmoney[2];
				BigDecimal repaydMoney= retmoney[2];//已还本金
				
				BigDecimal changeMoney=saleMoney.multiply(new BigDecimal(plBidSale.getChangeMoneyRate())).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
				if(plBidSale.getChangeMoneyType().equals(Short.valueOf("1"))){
					changeMoney=new BigDecimal(0).subtract(changeMoney);
				}
				BigDecimal sumMoney=saleMoney.add(sumInteresteMoney).add(accrualMoney).add(changeMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
//				BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.005)).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(sumMoney!=null&&!sumMoney.equals(new BigDecimal(0))){
					String orderNo = ContextUtil.createRuestNumber();
					CommonRequst cq=new CommonRequst();
					if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
						cq.setIsMobile("1");
					}
					cq.setTransferName(ThirdPayConstants.TN_BUYDEAL);
					cq.setBussinessType(ThirdPayConstants.BT_BUYDEAL);//业务类型
					cq.setThirdPayConfigId(mem.getThirdPayFlagId());
					cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
					cq.setRequsetNo(orderNo);//请求流水号
					cq.setBidId(plBidInfo.getBidId().toString());//标id
					cq.setLoanNoList(plBidPlan.getLoanTxNo());//标在三方的标识
					//原始债权交易流水号
					cq.setQueryRequsetNo(plBidInfo.getNewOrderNo()!=null?plBidInfo.getNewOrderNo():plBidInfo.getOrderNo());//债权交易记录的投资流水号
					//原始债权交易日期
					if (plBidInfo.getNewOrderDate() == null || "".equals(plBidInfo.getNewOrderDate())) {//如果没有新的交易订单
						cq.setOrderDate(sdf.format(plBidInfo.getBidtime()));
					}else {
						cq.setOrderDate(plBidInfo.getNewOrderDate());
					}
					cq.setSellCustId(oldCust.getThirdPayFlagId());//原始债权人三方账号
					cq.setLoaner_thirdPayflagId(oldCust.getThirdPayFlag0());//原始债权人三方用户名
					cq.setCreditFeeType(FuDian.CREDITFEETYPE1);//收取转让手续费方式
					cq.setAmount(sumMoney);//交易金额
					if (plBidSale.getPreTransferFee() != null) {
						cq.setFee(plBidSale.getPreTransferFee());//手续费
					}else {
						cq.setFee(new BigDecimal(0));
					}
					cq.setCreditAmt(saleMoney.setScale(2).toString());//承接本金
					cq.setCreditDealAmt(repaydMoney.setScale(2).toString());//已还本金
					//最原始投资记录订单日期
					cq.setOriOrderDate(sdf.format(plBidInfo.getBidtime()));
					//最原始投资记录订单号
					cq.setOriOrderNo(plBidInfo.getOrderNo());
					cq.setBidProNumber(saleId);//挂牌id
					cq.setPlanMoney(plBidPlan.getBidMoney());
					cq.setContractNo("");
					cq.setTransactionTime(new Date());
					if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){
						cq.setAccountType(0);//个人购买
					}else{
						cq.setAccountType(1);//企业购买
					}
					CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
					if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
						plBidInfoService.save(plBidInfo);
						plBidSale.setSaleMoney(saleMoney);
						plBidSale.setSumMoney(sumMoney);
						plBidSale.setSaleDealTime(new Date());
						plBidSale.setTransferFee(transferFee);
						plBidSale.setSaleStatus(Short.valueOf("3"));
						plBidSale.setNewOrderNo(orderNo);
						plBidSale.setInCustID(mem.getId());
						plBidSale.setInCustName(mem.getLoginname());
						plBidSale.setIsVip(plBidPlan.getIsVip());
						plBidSaleService.save(plBidSale);
					}else if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						plBidInfoService.save(plBidInfo);
						plBidSale.setSaleMoney(saleMoney);
						plBidSale.setSumMoney(sumMoney);
						plBidSale.setSaleDealTime(new Date());
						plBidSale.setTransferFee(transferFee);
						plBidSale.setSaleStatus(Short.valueOf("3"));
						plBidSale.setNewOrderNo(orderNo);
						plBidSale.setInCustID(mem.getId());
						plBidSale.setInCustName(mem.getLoginname());
						plBidSale.setIsVip(plBidPlan.getIsVip());
						plBidSaleService.save(plBidSale);
						Map<String,String> map = new HashMap<String, String>();
						map.put("requestNo", orderNo);
						map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
						opraterBussinessDataService.doObligationDeal(map);
						webMsgInstance("0", Constants.CODE_SUCCESS, "债权购买成功", "", "", "", "","");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					} else {
						webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(), "", "", "", "","");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "转让金额不能为0元", "", "", "", "","");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
				}
			}else {
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统报错", "", "", "", "","");
			if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILE_MESSAGE).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}
		return "freemarker";
		
	}
	
	
	/**
	 * 购买债权
	 * 富滇银行对接
	 * @return
	 */
	public String buyorStartTransferMobile(){
		// 判断是否是移动端
		String isMobile = this.getRequest().getParameter("isMobile");
		
		// 创建json字符串
		StringBuffer sb = new StringBuffer();
		
		// 将数据转成JSON格式
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		// 声明布尔值判断
		Boolean flag = false;
		
		// 声明String获取字符串
		String str = "";
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
				forwardPage=DynamicConfig.MOBILE_MESSAGE;
			}
			/**
			 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				String startDate = this.getRequest().getParameter("startDate");
				String bidInfoId = this.getRequest().getParameter("bidInfoId");
				PlBidInfo plBidInfo=plBidInfoService.get(Long.valueOf(bidInfoId));
				PlBidPlan  plBidPlan = plBidInfo.getPlBidPlan();
				String saleId = this.getRequest().getParameter("saleId");
				plBidSale=plBidSaleService.get(Long.valueOf(saleId));
				//得到借款人信息
				BpCustMember member = bpCustMemberService.getByLoginName(plBidPlan.getReceiverP2PAccountNumber());
				//得到原始债权人信息
				BpCustMember oldCust = bpCustMemberService.get(plBidSale.getOldCustID());
				if(!plBidSale.getSaleStatus().equals(Short.valueOf("1"))){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，此债权可能已经被取消或已被购买", "", "", "", "","");
					
					// 判断是否是移动端
					if (null != isMobile && "1".equals(isMobile)) {
						// 判断当前状态是false还是true
							flag = false;
							
							// 需返回状态文本
							str = "抱歉，此债权可能已经被取消或已被购买";
					} else {
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						return "freemarker";
					}
					
				}
				if(plBidSale.getOutCustID().equals(mem.getId())){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，自己不能买自己的债权", "", "", "", "","");
					
					
					// 判断是否是移动端
					if (null != isMobile && "1".equals(isMobile)) {
						// 判断当前状态是false还是true
							flag = false;
							
							// 需返回状态文本
							str = "抱歉，自己不能买自己的债权";
					} else {
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						return "freemarker";
					}
			    }
				if(member.getId().equals(mem.getId())){
					webMsgInstance("0", Constants.CODE_FAILED, "抱歉，借款人不能买自己的借款项目的债权", "", "", "", "","");
					
					// 判断是否是移动端
					if (null != isMobile && "1".equals(isMobile)) {
						// 判断当前状态是false还是true
							flag = false;
							
							// 需返回状态文本
							str = "抱歉，借款人不能买自己的借款项目的债权";
					} else {
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						return "freemarker";
					}
				}
				
				BigDecimal[] retmoney =gettransferMoney(plBidInfo,startDate);//计算saleMoney,accrualMoney,sumInteresteMoney
				BigDecimal saleMoney= retmoney[0];//未还金额
				BigDecimal sumInteresteMoney= retmoney[1];
				BigDecimal accrualMoney= retmoney[2];
				BigDecimal repaydMoney= retmoney[2];//已还本金
				
				BigDecimal changeMoney=saleMoney.multiply(new BigDecimal(plBidSale.getChangeMoneyRate())).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
				if(plBidSale.getChangeMoneyType().equals(Short.valueOf("1"))){
					changeMoney=new BigDecimal(0).subtract(changeMoney);
				}
				BigDecimal sumMoney=saleMoney.add(sumInteresteMoney).add(accrualMoney).add(changeMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
//				BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.005)).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal transferFee=sumMoney.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(sumMoney!=null&&!sumMoney.equals(new BigDecimal(0))){
					String orderNo = ContextUtil.createRuestNumber();
					CommonRequst cq=new CommonRequst();
					if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
						cq.setIsMobile("1");
					}
					cq.setTransferName(ThirdPayConstants.TN_BUYDEAL);
					cq.setBussinessType(ThirdPayConstants.BT_BUYDEAL);//业务类型
					cq.setThirdPayConfigId(mem.getThirdPayFlagId());
					cq.setThirdPayConfigId0(mem.getThirdPayFlag0());
					cq.setRequsetNo(orderNo);//请求流水号
					cq.setBidId(plBidInfo.getBidId().toString());//标id
					cq.setLoanNoList(plBidPlan.getLoanTxNo());//标在三方的标识
					//原始债权交易流水号
					cq.setQueryRequsetNo(plBidInfo.getNewOrderNo()!=null?plBidInfo.getNewOrderNo():plBidInfo.getOrderNo());//债权交易记录的投资流水号
					//原始债权交易日期
					if (plBidInfo.getNewOrderDate() == null || "".equals(plBidInfo.getNewOrderDate())) {//如果没有新的交易订单
						cq.setOrderDate(sdf.format(plBidInfo.getBidtime()));
					}else {
						cq.setOrderDate(plBidInfo.getNewOrderDate());
					}
					cq.setSellCustId(oldCust.getThirdPayFlagId());//原始债权人三方账号
					cq.setLoaner_thirdPayflagId(oldCust.getThirdPayFlag0());//原始债权人三方用户名
					cq.setCreditFeeType(FuDian.CREDITFEETYPE1);//收取转让手续费方式
					cq.setAmount(sumMoney);//交易金额
					if (plBidSale.getPreTransferFee() != null) {
						cq.setFee(plBidSale.getPreTransferFee());//手续费
					}else {
						cq.setFee(new BigDecimal(0));
					}
					cq.setCreditAmt(saleMoney.setScale(2).toString());//承接本金
					cq.setCreditDealAmt(repaydMoney.setScale(2).toString());//已还本金
					//最原始投资记录订单日期
					cq.setOriOrderDate(sdf.format(plBidInfo.getBidtime()));
					//最原始投资记录订单号
					cq.setOriOrderNo(plBidInfo.getOrderNo());
					cq.setBidProNumber(saleId);//挂牌id
					cq.setPlanMoney(plBidPlan.getBidMoney());
					cq.setContractNo("");
					cq.setTransactionTime(new Date());
					if(mem.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){
						cq.setAccountType(0);//个人购买
					}else{
						cq.setAccountType(1);//企业购买
					}
					CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
					if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)) {
						plBidInfoService.save(plBidInfo);
						plBidSale.setSaleMoney(saleMoney);
						plBidSale.setSumMoney(sumMoney);
						plBidSale.setSaleDealTime(new Date());
						plBidSale.setTransferFee(transferFee);
						plBidSale.setSaleStatus(Short.valueOf("3"));
						plBidSale.setNewOrderNo(orderNo);
						plBidSale.setInCustID(mem.getId());
						plBidSale.setInCustName(mem.getLoginname());
						plBidSale.setIsVip(plBidPlan.getIsVip());
						plBidSaleService.save(plBidSale);
					}else if (cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)) {
						plBidInfoService.save(plBidInfo);
						plBidSale.setSaleMoney(saleMoney);
						plBidSale.setSumMoney(sumMoney);
						plBidSale.setSaleDealTime(new Date());
						plBidSale.setTransferFee(transferFee);
						plBidSale.setSaleStatus(Short.valueOf("3"));
						plBidSale.setNewOrderNo(orderNo);
						plBidSale.setInCustID(mem.getId());
						plBidSale.setInCustName(mem.getLoginname());
						plBidSale.setIsVip(plBidPlan.getIsVip());
						plBidSaleService.save(plBidSale);
						Map<String,String> map = new HashMap<String, String>();
						map.put("requestNo", orderNo);
						map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
						opraterBussinessDataService.doObligationDeal(map);
						webMsgInstance("0", Constants.CODE_SUCCESS, "债权购买成功", "", "", "", "","");
						
						// 判断是否是移动端
						if (null != isMobile && "1".equals(isMobile)) {
							// 判断当前状态是false还是true
								flag = true;
								
								// 需返回状态文本
								str = "债权购买成功";
						} else {
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						}
					} else {
						webMsgInstance("0", Constants.CODE_FAILED, cr.getResponseMsg(), "", "", "", "","");
						
						// 判断是否是移动端
						if (null != isMobile && "1".equals(isMobile)) {
							// 判断当前状态是false还是true
								flag = false;
								
								// 需返回状态文本
								str = cr.getResponseMsg();
						} else {
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
						}
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "转让金额不能为0元", "", "", "", "","");
					
					// 判断是否是移动端
					if (null != isMobile && "1".equals(isMobile)) {
						// 判断当前状态是false还是true
							flag = false;
							
							// 需返回状态文本
							str = "转让金额不能为0元";
					} else {
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
					}
				}
			}else {
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				
				// 判断是否是移动端
				if (null != isMobile && "1".equals(isMobile)) {
					// 判断当前状态是false还是true
						flag = false;
						
						// 需返回状态文本
						str = usercondition[1].toString();
				} else {
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统报错", "", "", "", "","");
			
			// 判断是否是移动端
			if (null != isMobile && "1".equals(isMobile)) {
				// 判断当前状态是false还是true
					flag = false;
					
					// 需返回状态文本
					str = "系统报错";
			} else {
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
			
			// 判断是否是移动端
			if (null != isMobile && "1".equals(isMobile)) {
				sb.append("{\"success\":");
				sb.append(gson.toJson(flag));
				sb.append(",\"result\":");
				sb.append(gson.toJson(str));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			} else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
			
			/*if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MOBILE_MESSAGE).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}*/
		}
		
		
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
				plBidSaleService.remove(new Long(id));
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
		PlBidSale plBidSale=plBidSaleService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plBidSale));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 取消债权交易挂牌
	 */
	public String update(){
		try{
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			/**
			 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				PlBidSale orgPlBidSale=plBidSaleService.get(id);
				if(orgPlBidSale.getSaleStatus().equals(Short.valueOf("1"))){
					String orderNo = ContextUtil.createRuestNumber();
					CommonResponse cr=new CommonResponse();
					CommonRequst cq=new CommonRequst();
					cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
					cq.setConfimStatus(false);//false表示退回 true表示通过
					cq.setLoanNoList(orgPlBidSale.getPreAuthorizationNum());//挂牌时双乾返回的流水号
					cq.setTransferName(ThirdPayConstants.TN_CANCELDEAL);
					cq.setBussinessType(ThirdPayConstants.BT_CANCELDEAL);
					cq.setQueryRequsetNo(orgPlBidSale.getPreTransferFeeRequestNo());//挂牌时的流水号
					cq.setRequsetNo(orderNo);//请求流水号
					cq.setAmount(orgPlBidSale.getPreTransferFee());
					cq.setBidId(orgPlBidSale.getId().toString());
					cr=ThirdPayInterfaceUtil.thirdCommon(cq);
					if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
							orgPlBidSale.setSaleStatus(Short.valueOf("9"));
							orgPlBidSale.setSaleCloseTime(new Date());
							plBidSaleService.save(orgPlBidSale);
							getTransferingList(mem);
							//手续费放到购买的时候收取了，这里不收
							/*ObAccountDealInfo deal = obAccountDealInfoService.getByOrderNumber(orgPlBidSale.getPreTransferFeeRequestNo(),
									"", ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL, "0");
							if(deal!=null&&deal.getDealRecordStatus().toString().equals("7")){
								Map<String,Object> mapI=new HashMap<String,Object>();
								mapI.put("investPersonId",mem.getId());//投资人Id（必填）
								mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
								mapI.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
								mapI.put("money",orgPlBidSale.getPreTransferFee());//交易金额	（必填）			 
								mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapI.put("recordNumber",orgPlBidSale.getPreTransferFeeRequestNo());//交易流水号	（必填）
								mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								obAccountDealInfoService.updateAcountInfo(mapI);
							}else{
								String orderNos = ContextUtil.createRuestNumber();
								Map<String,Object> mapI=new HashMap<String,Object>();
								mapI.put("investPersonId",mem.getId());//投资人Id（必填）
								mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
								mapI.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
								mapI.put("money",orgPlBidSale.getPreTransferFee());//交易金额	（必填）			 
								mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapI.put("recordNumber",orderNos+"-"+"bid"+"-"+orgPlBidSale.getId());//交易流水号	（必填）
								mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								obAccountDealInfoService.operateAcountInfo(mapI);
							}
							*/
							webMsgInstance("0", Constants.CODE_SUCCESS, "债权已取消挂牌申请", "", "", "", "","");
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "债权取消挂牌申请"+cr.getResponseMsg(), "", "", "", "","");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "债权不能取消挂牌，因为债权可能正在交易", "", "", "", "","");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else {
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统报错", "", "", "", "","");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		
		return "freemarker";
		
	}
	/**
	 * D债权转让
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String allPlmanagemoney(){
		String isMobile =this.getRequest().getParameter("isMobile");
		if (pager == null) {
		   pager = new Pager();
		   pager.setPageNumber(pager == null?1:pager.getPageNumber());
		   pager.setPageSize(10);
		}
		String keepProtypeName = StringUtil.html2Text(this.getRequest().getParameter("plBidPlanFilter.keepProtypeName"));
		this.getRequest().setAttribute("my_plan", keepProtypeName);
		
		String investlimit = StringUtil.html2Text(this.getRequest().getParameter("investlimit"));
		this.getRequest().setAttribute("investlimit", investlimit);
		
		String yearRate = StringUtil.html2Text(this.getRequest().getParameter("yearRate"));
		this.getRequest().setAttribute("yearRate", yearRate);
		
		String sumMoney = StringUtil.html2Text(this.getRequest().getParameter("sumMoney"));
		this.getRequest().setAttribute("sumMoney", sumMoney);
		
		StringBuffer sb = new StringBuffer("WHERE p.state in (1,2,7,10)");
		if(null !=keepProtypeName && !"".equals(keepProtypeName)){
			sb.append(" and p.manageMoneyTypeId='"+keepProtypeName+"'");
		}
		if(null !=investlimit && !"".equals(investlimit)){
			String[] limit = investlimit.split("-");
			sb.append(" and p.investlimit >='"+limit[0]+"' and p.investlimit <= '"+limit[1]+"'");
		}
		if(null !=yearRate && !"".equals(yearRate)){
			String[] rate = yearRate.split("-");
			sb.append(" and p.yeaRate >'"+rate[0]+"' and p.yeaRate <= '"+rate[1]+"'");
		}
		if(null !=sumMoney && !"".equals(sumMoney)){
			String[] money = sumMoney.split("-");
			sb.append(" and p.sumMoney >'"+money[0]+"' and p.sumMoney <= '"+money[1]+"'");
		}
		pager.setProperty(sb.toString());
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pager =  plManageMoneyPlanService.getmyplmanage(pager);
		List<PlManageMoneyPlan> pList = pager.getList();
		List<PlManageMoneyType> plManageMoneyTypeList1 = plManageMoneyTypeService.getMoneyType("mmplan", Short.valueOf("0"));//一次性查询所有产品类型
		this.getRequest().setAttribute("plTypeList",plManageMoneyTypeList1);
		List<PlManageMoneyType> plManageMoneyTypeList = plManageMoneyTypeService.getMoneyType("mmplan", null);//一次性查询所有产品类型
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
		
		pager.setList(list);
		
		
		//得到微博二维码
		List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
		this.getRequest().setAttribute("fileList", fileList);
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
		
		this.getRequest().setAttribute("planType", "plmanage");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.All_transfering_list).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 债权交易列表查询
	 * @return
	 */
	public String alltransferinglist(){
    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		List<PlBidSale> list= plBidSaleService.getBySaleState(Short.valueOf("3"));
	    for(PlBidSale l:list){
	    	if(null==l.getSaleDealTime()){
	    		l.setSaleDealTime(new Date());
	    	}
	    	if((new Date()).getTime()-l.getSaleDealTime().getTime()>30*60*1000){
	    		l.setSaleStatus(Short.valueOf("1"));
	    	}
	    }
		if(pager == null) {
		   pager = new Pager();
		   pager.setPageNumber(pager==null?1:pager.getPageNumber());
		   pager.setPageSize(10);
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		StringBuffer sb = new StringBuffer();
		String payTime = StringUtil.html2Text(this.getRequest().getParameter("payTime"));
		String plKeepCreditlevel_name = this.getRequest().getParameter("plKeepCreditlevel_name");
		this.getSession().setAttribute("payTime",payTime);
		this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
		if(payTime!=null&&!"".equals(payTime)&&plKeepCreditlevel_name!=null&&!"".equals(plKeepCreditlevel_name)){
			this.getSession().setAttribute("payTime",payTime);
			this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
			String[] x = plKeepCreditlevel_name.split("-");
			String[] s = payTime.split("-");
			sb.append(" where ");
			sb.append(" saleMoney<="+x[1]+" "); 
			sb.append(" and ");
			sb.append(" saleMoney>="+x[0]+" ");
			sb.append(" and ");
			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())<="+s[1]+" ");
			sb.append(" and ");
			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())>="+s[0]+" ");
			sb.append(" and ");
			sb.append(" a.isVip=0 "); 
		}else if(payTime!=null&&!"".equals(payTime)){
			this.getSession().setAttribute("payTime",payTime);
			String[] s = payTime.split("-");
			sb.append(" where ");
			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())<="+s[1]+" ");
			sb.append(" and ");
			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())>="+s[0]+" ");
			sb.append(" and ");
			sb.append(" a.isVip=0 "); 
		}else if(plKeepCreditlevel_name!=null&&!"".equals(plKeepCreditlevel_name)){
			this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
			String[] x = plKeepCreditlevel_name.split("-");
			sb.append(" where ");
			sb.append(" saleMoney<="+x[1]+" "); 
			sb.append(" and ");
			sb.append(" saleMoney>="+x[0]+" ");
			sb.append(" and ");
			sb.append(" a.isVip=0 "); 
		}else{
			sb.append(" where ");
			sb.append(" a.isVip=0 "); 
		}
			
		List<PlBidSale> transferingList = new ArrayList<PlBidSale>();
		Map<String, String> map=new HashMap<String, String>();
		
		map.put("type", "all");
		 
		Long l = mem==null?0l:mem.getId();
		transferingList= plBidSaleService.transferingList(l, filter.getPagingBean(), map,sb.toString());
		pager.setList(transferingList);
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		String isMobile =this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
			.append(pager.getTotalCount()).append(",\"result\":");
			JSONSerializer json = JsonUtil.getJSONSerializer("saleStartTime","startDate","intentDate");
			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"saleStartTime","startDate","intentDate" });
			buff.append(json.serialize(transferingList));
			buff.append("}");
			jsonString = buff.toString();
			return SUCCESS;
			
		}
		
		this.getRequest().setAttribute("planType", "ortransfer");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.All_transfering_list).getTemplateFilePath());
		return "freemarker";
		
	}
	
	/**
	 * 债权交易列表查询 --- vip
	 * @return
	 */
	public String alltransferinglistVip(){
    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
    	if(mem!=null){
    		mem = bpCustMemberService.get(mem.getId());
        	if(mem.getIsVip()!=null&&mem.getIsVip()==1){
        		List<PlBidSale> list= plBidSaleService.getBySaleState(Short.valueOf("3"));
        	    for(PlBidSale l:list){
        	    	if(null==l.getSaleDealTime()){
        	    		l.setSaleDealTime(new Date());
        	    	}
        	    	if((new Date()).getTime()-l.getSaleDealTime().getTime()>30*60*1000){
        	    		l.setSaleStatus(Short.valueOf("1"));
        	    	}
        	    }
        		if(pager == null) {
        		   pager = new Pager();
        		   pager.setPageNumber(pager==null?1:pager.getPageNumber());
        		   pager.setPageSize(10);
        		}
        		QueryFilter filter = new QueryFilter(getRequest());
        		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
        		filter.getPagingBean().setPageSize(pager.getPageSize());
        		StringBuffer sb = new StringBuffer();
        		String payTime = StringUtil.html2Text(this.getRequest().getParameter("payTime"));
        		String plKeepCreditlevel_name = this.getRequest().getParameter("plKeepCreditlevel_name");
        		this.getSession().setAttribute("payTime",payTime);
        		this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
        		if(payTime!=null&&!"".equals(payTime)&&plKeepCreditlevel_name!=null&&!"".equals(plKeepCreditlevel_name)){
        			this.getSession().setAttribute("payTime",payTime);
        			this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
        			String[] x = plKeepCreditlevel_name.split("-");
        			String[] s = payTime.split("-");
        			sb.append(" where ");
        			sb.append(" saleMoney<="+x[1]+" "); 
        			sb.append(" and ");
        			sb.append(" saleMoney>="+x[0]+" ");
        			sb.append(" and ");
        			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())<="+s[1]+" ");
        			sb.append(" and ");
        			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())>="+s[0]+" ");
        			sb.append(" and ");
        			sb.append(" a.isVip=1 "); 
        		}else if(payTime!=null&&!"".equals(payTime)){
        			this.getSession().setAttribute("payTime",payTime);
        			String[] s = payTime.split("-");
        			sb.append(" where ");
        			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())<="+s[1]+" ");
        			sb.append(" and ");
        			sb.append(" TIMESTAMPDIFF(MONTH,plan.startIntentDate,NOW())>="+s[0]+" ");
        			sb.append(" and ");
        			sb.append(" a.isVip=1 "); 
        		}else if(plKeepCreditlevel_name!=null&&!"".equals(plKeepCreditlevel_name)){
        			this.getSession().setAttribute("plKeepCreditlevel_name",plKeepCreditlevel_name);
        			String[] x = plKeepCreditlevel_name.split("-");
        			sb.append(" where ");
        			sb.append(" saleMoney<="+x[1]+" "); 
        			sb.append(" and ");
        			sb.append(" saleMoney>="+x[0]+" ");
        			sb.append(" and ");
        			sb.append(" a.isVip=1 "); 
        		}else{
        			sb.append(" where ");
        			sb.append(" a.isVip=1 "); 
        		}
        			
        		List<PlBidSale> transferingList = new ArrayList<PlBidSale>();
        		Map<String, String> map=new HashMap<String, String>();
        		
        		map.put("type", "all");
        		 
        		Long l = mem==null?0l:mem.getId();
        		transferingList= plBidSaleService.transferingList(l, filter.getPagingBean(), map,sb.toString());
        		pager.setList(transferingList);
        		pager.setTotalCount(filter.getPagingBean().getTotalItems());
        		String isMobile =this.getRequest().getParameter("isMobile");
        		if(null!=isMobile&&isMobile.endsWith("1")){
        			StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
        			.append(pager.getTotalCount()).append(",\"result\":");
        			JSONSerializer json = JsonUtil.getJSONSerializer("saleStartTime","startDate","intentDate");
        			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"saleStartTime","startDate","intentDate" });
        			buff.append(json.serialize(transferingList));
        			buff.append("}");
        			jsonString = buff.toString();
        			return SUCCESS;
        			
        		}
        		
        		this.getRequest().setAttribute("planType", "ortransfer");
        		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.All_transfering_list_VIP).getTemplateFilePath());
        	}else{
        		webMsgInstance("0", Constants.CODE_FAILED,"您还不是VIP用户，无法查看",  "", "", "", "", "");
    			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
        	}
    	}else{
    		webMsgInstance("0", Constants.CODE_FAILED,"请先登录",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
    		//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
    	}
		
		return "freemarker";
		
	}
	
	public void contract(){
		String saleId = this.getRequest().getParameter("saleId");
		
		String customerName = "" ;//[#借款人#]
		BigDecimal borrowMoney = new BigDecimal("0");//[#本次放款金额#]
		Date satrtDate = new Date();//原借款开始日期
		Date intentDate = new Date();//原借款结束日期
		String accrualtypeValue = "";//还款方式
		String userName = "";//老债权人
		String userCardNumber = "";//老债权人证件号码
		String userLoginName = "";//老债权人账号
		String newInvestPersonNmae = "";//新的债权人
		String newInvestPersonCardNumber = "";//新债权人证件号码
		String unewInvestPersonLoginName = "";//新债权人账号
		BigDecimal bidMoney = new BigDecimal("0");//债权交易金额
		BigDecimal changeMoney = new BigDecimal("0");//债权交易金额
		BigDecimal yearAccrualRate = new BigDecimal("0");//年化利率
		Date bidtime = new Date();
		Date startDealTime = new Date();//招标计划开始时间
		Date endDealTime = new Date();//招标计划结束时间
		Date saleSuccessTime = new Date();//交易成功时间
		if(saleId!=null&&!"".equals(saleId)){
			PlBidSale sale = plBidSaleService.get(Long.valueOf(saleId));//购买债权表
			bidMoney = sale.getSaleMoney();
			BigDecimal changeMoney1 = sale.getChangeMoney();
			changeMoney = bidMoney.subtract(changeMoney1);
			saleSuccessTime = sale.getSaleSuccessTime();
			if(sale!=null){
				Long outCustID =sale.getOutCustID();
				if(outCustID!=null&&!"".equals(outCustID)){
					BpCustMember member = bpCustMemberService.get(outCustID);
					if(member!=null){
						userName = member.getTruename();
						userCardNumber = member.getCardcode();
						userLoginName = member.getLoginname();
					}
				}
				/*Long outCustId =sale.getOutCustID();
				if(outCustId!=null&&!"".equals(outCustId)){
					BpCustMember member = bpCustMemberService.get(outCustId);
					if(member!=null){
						newInvestPersonNmae = member.getTruename();
						newInvestPersonCardNumber = member.getCardcode();
						unewInvestPersonLoginName = member.getLoginname();
					}
				}*/
				Long inCustId =sale.getInCustID();
				if(inCustId!=null&&!"".equals(inCustId)){
					BpCustMember member = bpCustMemberService.get(inCustId);
					if(member!=null){
						newInvestPersonNmae = member.getTruename();
						newInvestPersonCardNumber = member.getCardcode();
						unewInvestPersonLoginName = member.getLoginname();
					}
				}
				/*Long bidInfoId = sale.getBidInfoID();
				if(bidInfoId!=null&&!"".equals(bidInfoId)){
					PlBidInfo info = plBidInfoService.get(bidInfoId);//线上投资表
					if(info!=null){
						Long userId =info.getUserId();
						if(userId!=null&&!"".equals(userId)){
							BpCustMember member = bpCustMemberService.get(userId);
							if(member!=null){
								newInvestPersonNmae = member.getTruename();
								newInvestPersonCardNumber = member.getCardcode();
								unewInvestPersonLoginName = member.getLoginname();
							}
						}
						//bidtime = info.getBidtime();
						
					}
				}*/
				
				bidtime= sale.getSaleSuccessTime();
				Long bidPlanId = sale.getBidPlanID();
				if(bidPlanId!=null&&!"".equals(bidPlanId)){
					PlBidPlan plan = plBidPlanService.get(Long.valueOf(bidPlanId));//招标计划表
					if(plan!=null){
						startDealTime = plan.getStartIntentDate();
						endDealTime = plan.getEndIntentDate();
						String protype = plan.getProType();
						if(protype!=null&&!"".equals(protype)){
							Long proId = new Long("0");
							if(protype.equals("P_Dir")){//个人直投标
								Long pDirProId = plan.getPdirProId();
								if(pDirProId!=null&&!"".equals(pDirProId)){
									BpPersionDirPro pDir = bpPersionDirProService.get(pDirProId);
									proId = pDir.getMoneyPlanId();
									
								}
								
							}else if(protype.equals("B_Dir")){//企业直投标
								Long bDirProId = plan.getBdirProId();
								if(bDirProId!=null&&!"".equals(bDirProId)){
									BpBusinessDirPro pDir = bpBusinessDirProService.get(bDirProId);
									proId = pDir.getMoneyPlanId();
								}
							}else if(protype.equals("P_Or")){//个人债转标
								Long pOrProId = plan.getPOrProId();
								if(null != pOrProId && !"".equals(pOrProId)){
									BpPersionOrPro pOr = bpPersionOrProService.get(pOrProId);
									proId =pOr.getMoneyPlanId();
								}
								
							}else if(protype.equals("B_Or")){//企业债转标
								Long bOrProId = plan.getBorProId();
								if(null != bOrProId && !"".equals(bOrProId)){
									BpBusinessOrPro bOr = bpBusinessOrProService.get(bOrProId);
									proId = bOr.getMoneyPlanId();
								}
							}
							if(proId!=null&&!"".equals(proId)&&proId>0){
								BpFundProject project = bpFundProjectService.get(proId);//借款项目表
								if(project!=null){
									borrowMoney = project.getProjectMoney();
									intentDate = project.getIntentDate();
									satrtDate = project.getStartDate();
									yearAccrualRate = project.getYearAccrualRate();
									if(project.getAccrualtype() != null) {
										
										if("sameprincipal".equals(project.getAccrualtype())) {
											accrualtypeValue = "等额本金";
										}else if ("sameprincipalandInterest".equals(project.getAccrualtype())){
											accrualtypeValue = "等额本息";
										}else if ("singleInterest".equals(project.getAccrualtype())){
											accrualtypeValue = "按期收息,到期还本";
										}else if ("ontTimeAccrual".equals(project.getAccrualtype())){
											accrualtypeValue = "一次性还本付息";
										}
									}

									String oppositeType = project.getOppositeType();
									Long oppositeID = project.getOppositeID();
									if(oppositeType!=null&&!"".equals(oppositeType)){
										if(oppositeType.equals("person_customer")){
											if(oppositeID!=null&&!"".equals(oppositeID)){
												Person person = personService.queryPersonId(oppositeID.intValue());
												if(person!=null){
													customerName = person.getName();
												}
											}
										}else if(oppositeType.equals("person_customer")){
											if(oppositeID!=null&&!"".equals(oppositeID)){
												Enterprise enterprise = enterpriseService.getById(oppositeID.intValue());
												if(enterprise!=null){
													customerName = enterprise.getEnterprisename();
												}
											}
										
										}
									}
								}
								
							}
						}
					}
				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer contractNumber = new StringBuffer("ZJS-");
		String nowDatef = sdf.format(saleSuccessTime);
		contractNumber.append(nowDatef).append("-"+saleId);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
		String nowDate = sd.format(saleSuccessTime);
		Integer duringDate = DateUtil.getDaysBetweenDate(bidtime, endDealTime)+1;//[#交易成功时债权剩余天数#]
		this.getRequest().setAttribute("nowDate", nowDate);
		this.getRequest().setAttribute("customerName", customerName);
		this.getRequest().setAttribute("borrowMoney", borrowMoney);
		this.getRequest().setAttribute("satrtDate", sd.format(satrtDate));
		this.getRequest().setAttribute("intentDate", sd.format(intentDate));
		this.getRequest().setAttribute("accrualtypeValue", accrualtypeValue);
		this.getRequest().setAttribute("userName", userName);
		this.getRequest().setAttribute("userCardNumber", userCardNumber);
		this.getRequest().setAttribute("userLoginName", userLoginName);
		this.getRequest().setAttribute("newInvestPersonNmae", newInvestPersonNmae);
		this.getRequest().setAttribute("newInvestPersonCardNumber", newInvestPersonCardNumber);
		this.getRequest().setAttribute("unewInvestPersonLoginName", unewInvestPersonLoginName);
		this.getRequest().setAttribute("bidMoney", bidMoney);
		this.getRequest().setAttribute("changeMoney", changeMoney);
		this.getRequest().setAttribute("yearAccrualRate", yearAccrualRate);
		this.getRequest().setAttribute("bidtime", sd.format(bidtime));
		this.getRequest().setAttribute("startDealTime", sd.format(startDealTime));
		this.getRequest().setAttribute("endDealTime", sd.format(endDealTime));
		this.getRequest().setAttribute("duringDate", duringDate);
		this.getRequest().setAttribute("contractNumber", contractNumber.toString());
		
		
		//获得webRoot路径
		String serverPath = getRequest().getRealPath("/");
		//模版所在文件夹的路径
		String ftlRoot = serverPath+"attachFiles\\contractFile";
		//模版名称
		String ftlName = "contract.ftl";
		
		//word临时文件名称
		//String wordName = UUIDUtils.getUUID();
		
		//存放word路径
		String wordSrc = serverPath+"attachFiles\\contractFile\\word\\"+contractNumber.toString()+".doc";
		//存放pdf路径
		String pdfSrc = serverPath+"attachFiles\\contractFile\\pdf\\"+contractNumber.toString()+".pdf";
		
		//freemarker引擎
		DocumentHandler dh=new DocumentHandler(ftlRoot);
		//数据map------
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("nowDate", nowDate);
		dataMap.put("customerName", customerName);
		dataMap.put("borrowMoney", borrowMoney);
		dataMap.put("satrtDate", satrtDate);
		dataMap.put("intentDate", intentDate);
		dataMap.put("accrualtypeValue", accrualtypeValue);
		dataMap.put("userName", userName);
		dataMap.put("userCardNumber", userCardNumber);
		dataMap.put("userLoginName", userLoginName);
		dataMap.put("newInvestPersonNmae", newInvestPersonNmae);
		dataMap.put("newInvestPersonCardNumber", newInvestPersonCardNumber);
		dataMap.put("unewInvestPersonLoginName", unewInvestPersonLoginName);
		dataMap.put("bidMoney", bidMoney);
		dataMap.put("changeMoney", changeMoney);
		dataMap.put("yearAccrualRate", yearAccrualRate);
		dataMap.put("bidtime", sd.format(bidtime));
		dataMap.put("startDealTime", sd.format(startDealTime));
		dataMap.put("endDealTime", sd.format(endDealTime));
		dataMap.put("duringDate", duringDate);
		dataMap.put("customerName", customerName);
		dataMap.put("contractNumber", contractNumber.toString());
		//生成word
		dh.createOrderAndDown(dataMap, wordSrc,ftlName);
		
		long start1 = System.currentTimeMillis();
		//word转pdf
		WordtoPdfUtil.wordToPdf(wordSrc,pdfSrc);
	    long end1 = System.currentTimeMillis();
	   // System.out.println("    ------pdf耗时:"+(end1-start1));
	    
	    //下载word
	 //   FileHelper.downLoadFile(wordSrc,"doc",contractNumber.toString(), response);
	    //下载pdf
		FileHelper.downLoadFile(pdfSrc,"pdf",contractNumber.toString(), getResponse());
		//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.CONTRACT).getTemplateFilePath());
		//this.setSuccessResultValue("/WEB-INF/template/proj_ziweicaifu/single/contract.ftl");
		//return "freemarker";
		/*try {//${base}/html/contract.html
			this.getResponse().sendRedirect(this.getBasePath()+"html/contract.html");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 根据标的号计算已还款的金额
	 * @param bidId
	 * @return
	 */
	/*public String calLeftMoney(String bidId){
		List<BpFundIntent> list = bpFundIntentService.getOriFund(bidId);
		
		
		return "";
	}*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getVerify_sms() {
		return verify_sms;
	}

	public void setVerify_sms(String verifySms) {
		verify_sms = verifySms;
	}

	public Pager getCanTransferingListpager() {
		return canTransferingListpager;
	}

	public void setCanTransferingListpager(Pager canTransferingListpager) {
		this.canTransferingListpager = canTransferingListpager;
	}

	public Pager getTransferingListpager() {
		return transferingListpager;
	}

	public void setTransferingListpager(Pager transferingListpager) {
		this.transferingListpager = transferingListpager;
	}

	public Pager getTransferedListpager() {
		return transferedListpager;
	}

	public void setTransferedListpager(Pager transferedListpager) {
		this.transferedListpager = transferedListpager;
	}

	public Pager getCloseedListpager() {
		return closeedListpager;
	}

	public void setCloseedListpager(Pager closeedListpager) {
		this.closeedListpager = closeedListpager;
	}

	public Pager getBuyedListpager() {
		return buyedListpager;
	}

	public void setBuyedListpager(Pager buyedListpager) {
		this.buyedListpager = buyedListpager;
	}

	public PlBidSale getPlBidSale() {
		return plBidSale;
	}

	public void setPlBidSale(PlBidSale plBidSale) {
		this.plBidSale = plBidSale;
	}

}
