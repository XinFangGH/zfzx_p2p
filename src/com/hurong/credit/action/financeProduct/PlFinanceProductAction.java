package com.hurong.credit.action.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.action.user.BpCustMemberAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.financeProduct.PlFinanceProductRate;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.financeProduct.PlFinanceProductRateService;
import com.hurong.credit.service.financeProduct.PlFinanceProductService;
import com.hurong.credit.service.financeProduct.PlFinanceProductUserAccountInfoService;
import com.hurong.credit.service.financeProduct.PlFinanceProductUseraccountService;
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
public class PlFinanceProductAction extends BaseAction{
	@Resource
	private PlFinanceProductService plFinanceProductService;
	
	@Resource
	private PlFinanceProductUseraccountService plFinanceProductUseraccountService;
	@Resource
	private PlFinanceProductUserAccountInfoService plFinanceProductUserAccountInfoService;
	@Resource
	private PlFinanceProductRateService plFinanceProductRateService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	private PlFinanceProduct plFinanceProduct;
	
	private Long id;
	private Integer typeId;

	//购买金额
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	private Long productId;
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlFinanceProduct getPlFinanceProduct() {
		return plFinanceProduct;
	}

	public void setPlFinanceProduct(PlFinanceProduct plFinanceProduct) {
		this.plFinanceProduct = plFinanceProduct;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PlFinanceProduct> list= plFinanceProductService.getAll(filter);
		
		Type type=new TypeToken<List<PlFinanceProduct>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				plFinanceProductService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 获取理财专户产品信息
	 * @return
	 */
	public String get(){
		
		List<PlFinanceProduct> list= plFinanceProductService.getAll();
		PlFinanceProduct plFinanceProduct=null;
		if(list!=null){
			if(typeId!=null){
				if(list.size()>=typeId&&typeId>0){
					plFinanceProduct=list.get(typeId-1);
				}
			}else{
				plFinanceProduct=list.get(0);
			}
		}
		//PlFinanceProduct plFinanceProduct=plFinanceProductService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plFinanceProduct));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(plFinanceProduct.getId()==null){
			plFinanceProductService.save(plFinanceProduct);
		}else{
			PlFinanceProduct orgPlFinanceProduct=plFinanceProductService.get(plFinanceProduct.getId());
			try{
				BeanUtil.copyNotNullProperties(orgPlFinanceProduct, plFinanceProduct);
				plFinanceProductService.save(orgPlFinanceProduct);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	public String getList(){
		List<PlFinanceProduct> list= plFinanceProductService.getAll();
		if(null!=list && list.size()>0){
			StringBuffer buff = new StringBuffer("[");
			for (PlFinanceProduct glType : list) {
				buff.append("[").append(glType.getId()).append(",'")
						.append(glType.getProductName()).append("'],");
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append("]");
			setJsonString(buff.toString());
		}
		return SUCCESS;
	}
	//互融宝单页面展示
	public String getProduct(){
		try {
				List<PlFinanceProduct> list= plFinanceProductService.getAll();
				this.getRequest().setAttribute("plFinanceProductlist", list);
				if(list!=null&&list.size()>0){
					PlFinanceProduct pl=list.get(0);
					pl.setIntentDay(DateUtil.addDaysToDate(new Date(), Integer.valueOf(pl.getIntestModel())));
					pl.setAccountDay((DateUtil.addDaysToDate(new Date(), Integer.valueOf(pl.getIntestModel())+1)));
					this.getRequest().setAttribute("plFinanceProduct", pl);
					HttpServletRequest request=getRequest();
					SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
					request.setAttribute("intentDateEnd", formatDate.format(DateUtil.addDaysToDate(new Date(), -1)));
					List<PlFinanceProductRate> list1= plFinanceProductRateService.getAllRateAndOrder(request, null);
					if(list1!=null&&list.size()>0){
						this.getRequest().setAttribute("plFinanceProductRate", list1.get(0));
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.FIANCPRODUCT).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 加载模板文件(活期理财)
	 * @return
	 */
	public String personFianceAccount1() {
		this.getSession().setAttribute("highlight", 4);
		String loginType =(String) this.getSession().getAttribute("loginType");
    	BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
    	if (mem != null) {
    		if(loginType.equals("enterprise")){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.COMPANYCURRENTFINANCIAL).getTemplateFilePath());
			}else if (loginType.equals("person")){
				/*if(type!=null&&!"".equals(type)){
					this.getRequest().setAttribute("type", "1");
				}*/
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONCURRENTFINANCIAL).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
    	}else{
    		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
    	}
    	return "freemarker";
	}
	//活期理财页面的方法
	public String showCurrentFinancial(){
		  BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		  if(isLogin()){
			  jsonString = plFinanceProductUseraccountService.queryAllList(mem.getId());
		  }else{
			  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			  return "freemarker";
		  }  
		 return SUCCESS;
	   }
	//活期理财页面的方法
	public String showSaleRecord(){
		  BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		  if(isLogin()){
			  List<PlFinanceProductUserAccountInfo> plf=plFinanceProductUserAccountInfoService.queryAllCurrentFinancialList(mem.getId(),getRequest());
			  Integer plfNum=plFinanceProductUserAccountInfoService.queryAllCurrentFinancialList(mem.getId(),getRequest()).size();
			  System.out.println("plfNum==="+plfNum);
			  StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(plfNum).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer();
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
				buffer.append(json.serialize(plf));
				buffer.append("}");
				jsonString = buffer.toString();
		  }else{
			  this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			  return "freemarker";
		  }  
		 return SUCCESS;
	   }
	//个人中心互融宝页面展示
	public String personFianceAccount(){
		this.getSession().setAttribute("highlight", 5);
		try {
			if (pager == null) {
				pager = new Pager();
				pager.setPageSize(5);
			}
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			if(mem!=null){
				String startTime=this.getRequest().getParameter("startTime");
				if(null != startTime && !"".equals(startTime)){
					this.getRequest().setAttribute("startTime_dealDate", startTime);
				}
				String endTime=this.getRequest().getParameter("endTime");
				if(null != endTime && !"".equals(endTime)){
					this.getRequest().setAttribute("endTime_dealDate", endTime);
				}
				PlFinanceProductUseraccount plFinanceProductUseraccount= plFinanceProductUseraccountService.getPersonAccount(mem.getId());
				this.getRequest().setAttribute("plFinanceProductUseraccount", plFinanceProductUseraccount);
				List<PlFinanceProductUserAccountInfo> accountInfo=plFinanceProductUserAccountInfoService.getPersonList(this.getRequest(),mem.getId(),(pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
				List<PlFinanceProductUserAccountInfo> list=plFinanceProductUserAccountInfoService.getPersonList(this.getRequest(),mem.getId(),0, null);
				pager.setTotalCount(list != null ? list.size() : 0);
				pager.setList(accountInfo);
				
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
				return "freemarker";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONFIANCEACCOUNT).getTemplateFilePath());
		return "freemarker";
	}
	
	//个人中心互融宝转入页面
	public String buyProduct(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			/**
			 * 第三方交易：用户资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserCondition(mem);
			if((Boolean) usercondition[0]){
				List<PlFinanceProduct> list= plFinanceProductService.getAll();
				this.getRequest().setAttribute("plFinanceProductlist", list);
				if(list!=null&&list.size()>0){
					PlFinanceProduct pl=list.get(0);
					pl.setIntentDay(DateUtil.addDaysToDate(new Date(), Integer.valueOf(pl.getIntestModel())));
					pl.setAccountDay((DateUtil.addDaysToDate(new Date(), Integer.valueOf(pl.getIntestModel())+1)));
					System.out.println(pl.getIntentDay());
					this.getRequest().setAttribute("plFinanceProduct", pl);
					this.getRequest().setAttribute("bpCustMember", mem);
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONBUYPRODUCT).getTemplateFilePath());
				}else{
					//设置 返回提示消息
					webMsgInstance("0", Constants.CODE_SUCCESS, "互融宝尚未上线，敬请期待",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "互融宝购买页面报错","", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		
		return "freemarker";
	}
	
	//个人中心互融宝转出页面
	public String persontransferFromProduct(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			/**
			 * 第三方交易：用户资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserCondition(mem);
			if((Boolean) usercondition[0]){
				PlFinanceProductUseraccount plFinanceProductUseraccount= plFinanceProductUseraccountService.getPersonAccount(mem.getId());
				if(plFinanceProductUseraccount!=null){
					//this.getRequest().setAttribute("plFinanceProductlist", list);
					if(plFinanceProductUseraccount.getCurrentMoney().compareTo(new BigDecimal(0))>0){
						List<PlFinanceProduct> list= plFinanceProductService.getAll();
						this.getRequest().setAttribute("plFinanceProduct", list.get(0));
						this.getRequest().setAttribute("plFinanceProductUseraccount",plFinanceProductUseraccount);
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.PERSONTRANSFERPRODUCT).getTemplateFilePath());
					}else{
						webMsgInstance("0", Constants.CODE_FAILED, "账户没有足够的金额，不能转出",  "", "", "", "", "");
						this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
					}
				}else{
					//设置 返回提示消息
					webMsgInstance("0", Constants.CODE_FAILED, "尚未开通互融宝服务，不能进行资金转出",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统出错，联系管理员",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 用户购买互融宝产品列表
	 * @return
	 */
	public String  transferTo(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			String isMobile = "1";//this.getRequest().getParameter("mobile");
			
			/**
			 * 第三方交易：用户资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				//检查是否具备购买资格
				Object[]  flag=plFinanceProductService.checkCondition(amount,productId,mem);
				if(flag[0].equals(Constants.CODE_SUCCESS)){//可以购买站岗资金产品
					Object[]  flagi= null;
					if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
						flagi =	plFinanceProductUserAccountInfoService.buyRecord(amount,productId,mem,true);
					}else{
						flagi =	plFinanceProductUserAccountInfoService.buyRecord(amount,productId,mem,false);
					}
					webMsgInstance("0", flagi[0].toString(), flagi[1].toString(),  "", "", "", "", "");
			        	this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}else{
					//设置 返回提示消息
					webMsgInstance("0",Constants.CODE_FAILED, flag[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "资金转入出错,请联系管理员",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 用户转出互融宝产品列表
	 * @return
	 */
	public String  transferFrom(){
		try {
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			String isMobile = this.getRequest().getParameter("mobile");
			/**
			 * 第三方交易：用户资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				Object[]  flag=plFinanceProductService.checkFromCondition(amount,productId,mem);
				if(flag[0].equals(Constants.CODE_SUCCESS)){//可以转出站岗资金产品金额
					Object[]  flagi=plFinanceProductUserAccountInfoService.transferFromPlate(amount,productId,mem);
					 if(isMobile!=null&&"1".equals(isMobile)){
				        	this.setSuccessResultValue("/WEBINF/template/proj_wenandai/mobile/mobilemessage.ftl");
				        }else{
				        	this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				        }webMsgInstance("0", flagi[0].toString(), flagi[1].toString(),  "", "", "", "", "");
				}else{
					//设置 返回提示消息
					webMsgInstance("0", flag[0].toString(), flag[1].toString(),  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}

			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "资金转出出错,请联系管理员",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
		}
		return "freemarker";
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
}
