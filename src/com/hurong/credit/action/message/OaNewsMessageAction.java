package com.hurong.credit.action.message;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.message.OaNewsMessagerinfoService;
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
public class OaNewsMessageAction extends BaseAction{
	@Resource
	private OaNewsMessageService oaNewsMessageService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
	private FuiouService fuiouService;
	@Resource
	private OaNewsMessagerinfoService oaNewsMessagerinfoService;
	private OaNewsMessage oaNewsMessage;
	private String title;//标题
	private String content;//内容
	private java.util.Date sendTime;//发送时间
	private Long recipient;//接收人(用户id)
	private String operator;//操作人
	private String addresser;//发件人（全名）
	private String status;//状态：0未读，1已读
	private String ids;//多选记录
	private java.util.Date readTime;//阅读时间 
	private BpCustMember bpCustMember;//全局session中用户信息
	private String successHtml;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	private PlBidAuto bidAuto;

	public PlBidAuto getBidAuto() {
		return bidAuto;
	}

	public void setBidAuto(PlBidAuto bidAuto) {
		this.bidAuto = bidAuto;
	}

	public String getSuccessHtml() {
		return successHtml;
	}

	public void setSuccessHtml(String successHtml) {
		this.successHtml = successHtml;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	public Long getRecipient() {
		return recipient;
	}

	public void setRecipient(Long recipient) {
		this.recipient = recipient;
	}

	public String getOperator() {
		return operator;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAddresser() {
		return addresser;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.Date getReadTime() {
		return readTime;
	}

	public void setReadTime(java.util.Date readTime) {
		this.readTime = readTime;
	}

	public OaNewsMessage getOaNewsMessage() {
		return oaNewsMessage;
	}

	public void setOaNewsMessage(OaNewsMessage oaNewsMessage) {
		this.oaNewsMessage = oaNewsMessage;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		
		this.start = pager.getPageSize()*(pager.getPageNumber()-1);
		this.limit = pager.getPageSize();
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<OaNewsMessage> list= oaNewsMessageService.getAll(filter);
		
		Type type=new TypeToken<List<OaNewsMessage>>(){}.getType();
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
				oaNewsMessageService.remove(new Long(id));
			}
		}
		getUserAll();

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGELIST).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		//点击查看时，消息Num自动减少
		String isMobile =this.getRequest().getParameter("isMobile");
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String tab = this.getRequest().getParameter("tab");
		if(tab!=null && tab!= ""){
			this.getRequest().setAttribute("tab", "oanews");
		}
		if(bpCustMember!=null){
			PagingBean pb=new PagingBean(start,limit);
			HttpServletRequest request=this.getRequest();
			request.setAttribute("userId", bpCustMember.getId());
			request.setAttribute("id", id);

			List<OaNewsMessagerinfo> list= oaNewsMessagerinfoService.getAllInfo(pb,this.getRequest());//获取单个用户的单个站内信
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			//将数据转成JSON格式
			/*StringBuffer sb = new StringBuffer("{success:true,data:");*/
			if(list!=null&&list.size()>0){
				
				OaNewsMessagerinfo info=oaNewsMessagerinfoService.get(list.get(0).getId());
				if(info.getReadStatus().equals(Integer.valueOf("1"))){
					
				}else{
					info.setReadStatus(1);
					info.setReadTime(new Date());
					oaNewsMessagerinfoService.merge(info);
				}
				
				this.getRequest().setAttribute("OaNewsMessagerinfo", list.get(0));
			}
			/*sb.append("}");
			setJsonString(sb.toString());*/
			if(isMobile!=null && !"".equals(isMobile)&&"1".equals(isMobile)){
				return SUCCESS;
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGECONCENT).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		 /*oaNewsMessage=oaNewsMessageService.get(id);
		 oaNewsMessage.setStatus("1");//已读 （查看的时候表明该信息已读）
		 oaNewsMessage.setReadTime(new Date()); 
		 oaNewsMessageService.merge(oaNewsMessage);
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(oaNewsMessage));
		sb.append("}");
		setJsonString(sb.toString());
		//点击查看时，消息Num自动减少
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		long recipient = bpCustMember.getId();
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(5);
		}
		this.start = pager.getPageSize()*(pager.getPageNumber()-1);
		this.limit = pager.getPageSize();
		List<OaNewsMessage> list=oaNewsMessageService.getUserAll(recipient, this.start, this.limit,0,0);
		//successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'>|</span><a href='javascript:void(0)' target='_self' onClick='showMeg()'><span>消息("+list.size()+")</span></a><span class='sep'>|</span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
		//this.getRequest().getSession().setAttribute("successHtml", successHtml);
		setJsonString("{success:true}");
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGECONCENT).getTemplateFilePath());*/
		
		
		return "freemarker";
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(oaNewsMessage.getId()==null){
			oaNewsMessageService.save(oaNewsMessage);
		}else{
			OaNewsMessage orgOaNewsMessage=oaNewsMessageService.get(oaNewsMessage.getId());
			try{
				BeanUtil.copyNotNullProperties(orgOaNewsMessage, oaNewsMessage);
				oaNewsMessageService.save(orgOaNewsMessage);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		
		return SUCCESS;
		
	}
	/**
	 * 显示列表
	 */
	public String getUserAll(){
		this.getSession().setAttribute("highlight", 8);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String isMobile=this.getRequest().getParameter("isMobile");
		if(bpCustMember!=null){
			commoon(bpCustMember);
			BpCustMember temp= bpCustMemberService.get(bpCustMember.getId());
			bpCustMember.setScore(temp.getScore());
			recipient = bpCustMember.getId();
			if (pager == null) {
				pager = new Pager();
			}
			if(null!=isMobile&&isMobile.endsWith("1")){
				String page=this.getRequest().getParameter("page");
				String limit=this.getRequest().getParameter("limit");
				pager = new Pager();
				pager.setPageSize(Integer.valueOf(limit));
				pager.setPageNumber(Integer.valueOf(page));
			}	
			PagingBean pb=new PagingBean((pager.getPageNumber()-1)*pager.getPageSize(),pager.getPageSize());
			HttpServletRequest request=this.getRequest();
			request.setAttribute("userId", recipient);
			List<OaNewsMessagerinfo> list= oaNewsMessagerinfoService.getAllInfo(pb,request);//获取单个用户的所有站内信
			pager.setTotalCount(pb.getTotalItems());
			pager.setList(list);
			
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":").append(pager.getTotalCount()).append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer("sendTime");
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"sendTime"});
				buff.append(json.serialize(pager.getList()));
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGELIST).getTemplateFilePath());
		}else{//session中没有用户信息
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":false");
				jsonString = buff.toString();
				return SUCCESS;
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	
	public String queryAllNews(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		jsonString = oaNewsMessageService.queryAllInfo(getRequest(), bpCustMember);
		return SUCCESS;
	}
	
	
	public String  showMessageNums(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			jsonString = oaNewsMessageService.queryAllData(bpCustMember.getId());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		return SUCCESS;
	}
	
	
	/**
	 * 显示列表
	 */
	public String getnUserAll(){
		 this.getSession().setAttribute("highlight", 8);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		recipient = bpCustMember.getId();
		if (pager == null) {
			pager = new Pager();
			//pager.setPageSize(3);
		}
		commoon(bpCustMember);
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		filter.addFilter("Q_comment2_S_EQ", recipient.toString());
		//filter.addFilter("Q_isDelete_S_NEQ", "0");
		//加不了status=1 or status=0的条件
		List<OaNewsMessage> list= oaNewsMessageService.getAll(filter);//获取单个用户的所有站内信
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGELIST).getTemplateFilePath());
		
		return "freemarker";
	}
	
	
	private void commoon(BpCustMember mem){
		bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
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
		//financeInint(mem);
		
		//bpCustMember = bpCustMemberService.get(mem.getId());
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_payintentPeriod_N_GT", mem.getId().toString());
		
		//inient(bpCustMember);
		//getFundIncome(bpCustMember);
		//manageMoney(bpCustMember);
		//loanSet(bpCustMember);
		//fundList = getLoanList(mem,0,4);
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
	}
	
	
	/**
	 * 是否删除（假删除）单个或批量
	 * pll
	 */
	public String isDelete(){
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer();
		BpCustMember bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (bpCustMember == null) {
			sb.append("{\"success\":false,\"errMsg\":");
			sb.append(gson.toJson("请先登陆！！"));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		String tab = this.getRequest().getParameter("tab");
		if(tab!=null&&!"".equals(tab)){
			this.getRequest().setAttribute("tab", "tab");
		}
		if(id==null){
			if(ids!=null){
				String[] idss=ids.split(",");
				if(ids!=null){
					for(String id:idss){
						OaNewsMessagerinfo oaNewsMessagerinfo=oaNewsMessagerinfoService.get(new Long(id));
						try{
							oaNewsMessagerinfo.setStatus(1);//0为未删除，1为已删除
							oaNewsMessagerinfoService.merge(oaNewsMessagerinfo);
						}catch(Exception ex){
							logger.error(ex.getMessage());
						}
					}
				}
			}
		}else{
			OaNewsMessage orgOaNewsMessage=oaNewsMessageService.get(id);
			try{
				orgOaNewsMessage.setIsDelete("1");//0为未删除，1为已删除
				oaNewsMessageService.merge(orgOaNewsMessage);
			}catch(Exception ex){
				logger.error(ex.getMessage());
				sb.append("{\"success\":false,\"errMsg\":");
				 sb.append(gson.toJson("删除失败！"));
					sb.append("}");
			}
		}
		
		sb.append("{\"success\":true,\"errMsg\":");
		 sb.append(gson.toJson("删除成功！"));
			sb.append("}");
			
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	/**
	 * 标记为已读，多个删除
	 * @return
	 */
	public String isUpdate(){
		String tab = this.getRequest().getParameter("tab");
		if(tab!=null && !"".equals(tab)){
			this.getRequest().setAttribute("tab", "tab");
		}
		String state = this.getRequest().getParameter("state");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer();
		if(ids!=null){
			String[] idss=ids.split(",");
			if(ids!=null){
				for(String id:idss){
					OaNewsMessagerinfo info=oaNewsMessagerinfoService.get(Long.valueOf(id));
					if(state.equals("1")){//标记为已读
						if(info.getReadStatus().equals(Integer.valueOf("1"))){
							
						}else{
							info.setReadStatus(1);
							info.setReadTime(new Date());
							oaNewsMessagerinfoService.merge(info);
						}
					}else if(state.equals("2")){//多个删除
						try{
							info.setStatus(1);//0为未删除，1为已删除
							oaNewsMessagerinfoService.merge(info);
						}catch(Exception ex){
							logger.error(ex.getMessage());
						}
					
					}
				}
			}
		}
		
		sb.append("{\"success\":true,\"errMsg\":");
		 sb.append(gson.toJson("操作成功！"));
			sb.append("}");
			
		setJsonString(sb.toString());
		return SUCCESS;
	}
	
	/**
	 * 是否已读
	 * pll
	 */
	public String isReady(){
		if(id!=null){
				OaNewsMessage orgOaNewsMessage=oaNewsMessageService.get(id);
				
				try{
					orgOaNewsMessage.setReadTime(new Date());
					orgOaNewsMessage.setStatus("1");//已读状态 0未读  1已读
					oaNewsMessageService.merge(orgOaNewsMessage);
				}catch(Exception ex){
					logger.error(ex.getMessage());
				}
		}	
		return SUCCESS;
	}
	
	
	/**
	 * 向所有用户发送站内信
	 */
	public String sendAllUser(){
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		
		OaNewsMessage orgOaNewsMessage= new OaNewsMessage();
		try{
			
			orgOaNewsMessage.setTitle(title);//标题
			orgOaNewsMessage.setContent(content);//内容
			orgOaNewsMessage.setOperator(operator);//操作人
			orgOaNewsMessage.setAddresser(addresser);//发送人（全名）
			orgOaNewsMessage.setSendTime(new Date());//发送时间
			orgOaNewsMessage.setStatus("1");//已读状态 0未读  1已读
			oaNewsMessageService.sendAllUser(orgOaNewsMessage);
			sb.append(gson.toJson(orgOaNewsMessage));
			sb.append(",\"result\":1");
			sb.append("}");
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
	
		setJsonString(sb.toString());
		return SUCCESS;
		
	}
	/**2014-07-27 
	 * 自动刷新消息个数的方法
	 * @return
	 */
	public void refMeg(){
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(bpCustMember!=null){
			long recipient = bpCustMember.getId();
			if (pager == null) {
				pager = new Pager();
				pager.setPageSize(5);
			}
			this.start = pager.getPageSize()*(pager.getPageNumber()-1);
			this.limit = pager.getPageSize();
			List<OaNewsMessage> list=oaNewsMessageService.getUserAll(recipient, this.start, this.limit,1,1);
			successHtml="<a href='"+this.getBasePath()+"user/getBpCustMember.do' target='_self'><span class='loginname'>"+bpCustMember.getLoginname()+"</span></a><span class='sep'></span><a href='javascript:void(0)' target='_self' onClick='showMeg()'><span>消息("+list.size()+")</span></a><span class='sep'></span><a href='"+this.getBasePath()+"exitlogin.do' onClick='exit()'><span>退出</span></a>";
			this.getRequest().getSession().setAttribute("successHtml", successHtml);
		}
	}

	/**
	 * 安卓显示消息列表
	 */
	public String getUserMesAll(){
		String isApp = this.getRequest().getParameter("isApp");
		this.getSession().setAttribute("highlight", 8);
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//String isMobile=this.getRequest().getParameter("isMobile");
		MobileDataResultModel model = new MobileDataResultModel();
		if(bpCustMember!=null){
			commoon(bpCustMember);
			BpCustMember temp= bpCustMemberService.get(bpCustMember.getId());
			bpCustMember.setScore(temp.getScore());
			recipient = bpCustMember.getId();
			if (pager == null) {
				pager = new Pager();
			}

			String page=this.getRequest().getParameter("page");
			String limit=this.getRequest().getParameter("limit");
			pager = new Pager();
			pager.setPageSize(Integer.valueOf(limit));
			pager.setPageNumber(Integer.valueOf(page));

			PagingBean pb=new PagingBean((pager.getPageNumber()-1)*pager.getPageSize(),pager.getPageSize());
			HttpServletRequest request=this.getRequest();
			request.setAttribute("userId", recipient);
			List<OaNewsMessagerinfo> list= oaNewsMessagerinfoService.getAllInfo(pb,request);//获取单个用户的所有站内信
			pager.setTotalCount(pb.getTotalItems());
			pager.setList(list);

			if("1".equals(isApp)){
				model.addDataContent("totalCount",pager.getTotalCount());
				model.addDataContent("list",pager.getList());
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			this.getRequest().setAttribute("totalCount",pager.getTotalCount());
			this.getRequest().setAttribute("list",pager.getList());
			return "my_news";
		}else{//session中没有用户信息
			if("1".equals(isApp)){
				model.setCode(MobileErrorCode.SERVICE_ERROR);
				model.setMsg("查询不到该用户信息");
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			this.getRequest().setAttribute("status",1);
			this.getRequest().setAttribute("msg","查询不到该用户信息，请重新登录");
			return "reg_log";
			//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
	}

	/**
	 * 安卓显示详细信息
	 *
	 */
	public String getMes(){
		//点击查看时，消息Num自动减少
		String isApp = this.getRequest().getParameter("isApp");
		String isHapp = this.getRequest().getHeader("isApp");
		MobileDataResultModel model = new MobileDataResultModel();
		bpCustMember = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String tab = this.getRequest().getParameter("tab");
		System.out.println(tab+"");
		if(tab!=null && tab!= ""){
			this.getRequest().setAttribute("tab", "oanews");
		}
		if(bpCustMember!=null){
			PagingBean pb=new PagingBean(start,limit);
			HttpServletRequest request=this.getRequest();
			request.setAttribute("userId", bpCustMember.getId());
			request.setAttribute("id", id);
			List<OaNewsMessagerinfo> list= oaNewsMessagerinfoService.getAllInfo(pb,this.getRequest());//获取单个用户的单个站内信
			//Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			//将数据转成JSON格式
			/*StringBuffer sb = new StringBuffer("{success:true,data:")
			ReadStatus：0未读； 1：已读      ;*/
			if(list!=null&&list.size()>0){

				OaNewsMessagerinfo info=oaNewsMessagerinfoService.get(list.get(0).getId());
				if(info.getReadStatus().equals(Integer.valueOf("1"))){

				}else{
					info.setReadStatus(1);
					info.setReadTime(new Date());
					oaNewsMessagerinfoService.merge(info);
				}
				//this.getRequest().setAttribute("OaNewsMessagerinfo", list.get(0));
				model.addDataContent("OaNewsMessagerinfo",list.get(0));
				this.getRequest().setAttribute("OaNewsMessagerinfo",list.get(0));
			}
			if("1".equals(isApp) || "1".equals(isHapp)){
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			return "myMessage_details";
			//this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGECONCENT).getTemplateFilePath());
		}else{
			//跳转到登录页面
			if("1".equals(isApp) || "1".equals(isHapp)){
				model.setCode(MobileErrorCode.REG_LOG);
				model.setMsg("请先登录");
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			return "reg_log";
		}
	}

}
