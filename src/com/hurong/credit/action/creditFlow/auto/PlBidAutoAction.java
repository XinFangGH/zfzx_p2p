package com.hurong.credit.action.creditFlow.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepCreditlevel;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlKeepCreditlevelService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

@SuppressWarnings("serial")
public class PlBidAutoAction  extends BaseAction {

	@Resource
	private PlKeepCreditlevelService plKeepCreditlevelService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	private PlBidAuto bidAuto;

	public PlBidAuto getBidAuto() {
		return bidAuto;
	}
	public void setBidAuto(PlBidAuto bidAuto) {
		this.bidAuto = bidAuto;
	}
	/**
	 * 自动投标
	 * @return
	 */
	public String automaticbid()throws Exception{
		String isMobile =this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			QueryFilter filter =new QueryFilter();
			filter.addSorted("keyStr", QueryFilter.ORDER_ASC);
			List<PlKeepCreditlevel> listName =  plKeepCreditlevelService.getAll(filter);
			this.getRequest().setAttribute("plKeepCreditlevel", listName);//信用等级
			List<String> listPeriod = new ArrayList<String>();
			for(int i=0;i<=36;i++){
				listPeriod.add(String.valueOf(i));
			}
			this.getRequest().setAttribute("listPeriod", listPeriod);//期限
			bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
			System.out.println("自动投标bidAuto=="+bidAuto);
			if(bidAuto==null){
				bidAuto=initPlBidAuto(mem);//当当前用户自动投标标中没有数据时，初始化数据
			}
			if(bidAuto.getRateStart()!=null){
				bidAuto.setRateStartShow(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateStart())).getName());
			}
			if(bidAuto.getRateEnd()!=null){
				PlKeepCreditlevel keepCreditlevel = plKeepCreditlevelService.get(Long.valueOf("".equals(bidAuto.getRateEnd())?"-1":bidAuto.getRateEnd()));
				if(keepCreditlevel!=null){
					bidAuto.setRateEndShow(keepCreditlevel.getName());
				}
			}
			
			if(isMobile!=null &&"1".equals(isMobile)){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"success\":true,\"data\":");
 				sb.append(gson.toJson(bidAuto));
 				sb.append(",\"result\":1");
 				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.AUTOMATICBID).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "freemarker";
	}
	
	/**
	 * 保存自动投标信息
	 * @return
	 */
	public String saveAutoBidInfo(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
		PlBidAuto	auto=new PlBidAuto();
			bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
			auto.setId(bidAuto.getId());
			auto.setUserID(mem.getId());
			auto.setBidMoney(bidMoney);
			auto.setInterestStart(interestStart);
			auto.setInterestEnd(interestEnd);
			auto.setPeriodStart(periodStart);
			auto.setPeriodEnd(periodEnd);
			auto.setRateStart(rateStart);
			auto.setRateEnd(rateEnd);
			auto.setKeepMoney(keepMoney);
			auto.setIsOpen(bidAuto.getIsOpen());
			auto.setOrderTime(bidAuto.getOrderTime());
			auto.setBanned(bidAuto.getBanned());
			String ret=plBidAutoService.savechk(auto);
			if(ret.indexOf(Constants.FAILDFLAG)==-1){
				bidAuto=plBidAutoService.merge(auto);
				if(bidAuto.getRateStart()!=null){
					bidAuto.setRateStart(plKeepCreditlevelService.get(Long.valueOf(bidAuto.getRateStart())).getName());
				}
				if(bidAuto.getRateEnd()!=null){
					PlKeepCreditlevel keepCreditlevel = plKeepCreditlevelService.get(Long.valueOf("".equals(bidAuto.getRateEnd())?"-1":bidAuto.getRateEnd()));
					if(keepCreditlevel!=null){
						bidAuto.setRateEnd(keepCreditlevel.getName());
					}
				}
				Gson gson=new Gson();
				jsonString=gson.toJson(bidAuto);
				jsonString="{\"success\":true,"+jsonString.substring(1)+"";
			}else{
				jsonString="{\"success\":false,"+ret.substring(1)+"";
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
	    
		return SUCCESS;
	}
	
	/**
	 * 开启自动投标功能
	 * @return
	 */
	public String openBidAuto(){
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			String[] str=null;
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
			String forwardPage=DynamicConfig.MESSAGE;
			/**
			 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			if((Boolean) usercondition[0]){
				str=plBidAutoService.chk(mem.getId());
				if(str!=null&&str[0].equals(Constants.SUCCESSFLAG)){
					String requestNo=ContextUtil.createRuestNumber();//开通自动投标流水号
					if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)){//资金池模式开通自动投标功能
						bidAuto= plBidAutoService.getPlBidAuto(mem.getId());
						bidAuto.setRequestNo(requestNo);
						bidAuto.setIsOpen(Integer.valueOf("1"));
						plBidAutoService.save(bidAuto);	
						webMsgInstance("0", Constants.CODE_SUCCESS, "成功开通自动投标功能",  "", "", "", "", "");
					}else{//资金托管模式开通自动投标功能
						if(configMap.get("thirdPayConfig").toString().equals(Constants.YEEPAY)){/*
							*//**
							 * (5)自动投标授权	 * 2014-07-15
						     * Map<String,object> map  第三方支付自动投标授权需要的map参数
							 * map.get("basePath").toString() 只当前的绝对路径
							 * map.get("platformUserNo").toString() 第三方支付账号
							 * map.get("requestNo").toString()交易流水号
							 * @return
							 *//*
							Map<String,Object> map =new HashMap<String,Object>();
							map.put("basePath",this.getBasePath());
							map.put("platformUserNo",mem.getThirdPayFlagId());
							map.put("requestNo",requestNo);
							String[] ret=yeePayService.autoTransferAuthorization(map);
							if(ret[0].equals(Constants.CODE_SUCCESS)){
								bidAuto= plBidAutoService.getPlBidAuto(mem.getId());
								bidAuto.setRequestNo(requestNo);
								plBidAutoService.save(bidAuto);
								this.getRequest().setAttribute("str", ret[1]);
								forwardPage=DynamicConfig.TOTHIRD;
							 }else{
								webMsgInstance("0", ret[0], ret[1],  "", "", "", "", "");
							 }
						*/}else if(configMap.get("thirdPayConfig").toString().equals(Constants.MONEYMOREMORE)){//双乾支付
							webMsgInstance("0", Constants.CODE_SUCCESS, "开发中",  "", "", "", "", "");
						}else if(configMap.get("thirdPayConfig").toString().equals(Constants.HUIFU)){//汇付天下
							webMsgInstance("0", Constants.CODE_SUCCESS, "开发中",  "", "", "", "", "");
						}else if(configMap.get("thirdPayConfig").toString().equals(Constants.FUIOU)){//富有金账户
							bidAuto= plBidAutoService.getPlBidAuto(mem.getId());
							bidAuto.setRequestNo(requestNo);
							bidAuto.setIsOpen(Integer.valueOf("1"));
							plBidAutoService.save(bidAuto);	
							webMsgInstance("0", Constants.CODE_SUCCESS, "成功开通自动投标功能",  "", "", "", "", "");
						}else if(configMap.get("thirdPayConfig").toString().equals(Constants.UMPAY)){//联动优势
							webMsgInstance("0", Constants.CODE_SUCCESS, "开发中",  "", "", "", "", "");
						}else if(configMap.get("thirdPayConfig").toString().equals(Constants.SINAPAY)){//新浪支付
							webMsgInstance("0", Constants.CODE_SUCCESS, "开发中",  "", "", "", "", "");
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "尚未对接该第三方支付类型,请与系统管理员联系",  "", "", "", "", "");
						}
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, str[1],  "", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			return "freemarker";
	}
	
	/**
	 * 关闭自动投标功能
	 * @return
	 */
	public String closeBidAuto(){
			BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			//业务方法处理完毕跳转页面：默认是跳转到MessAge页7面。
			String forwardPage=DynamicConfig.MESSAGE;
			/**
			 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
			 */
			Object[] usercondition=bpCustMemberService.checkUserDealCondition(mem);
			CommonRequst common =new CommonRequst();
			CommonResponse commonResponse = new CommonResponse();
			if((Boolean) usercondition[0]){
				String requestNo=ContextUtil.createRuestNumber();//开通自动投标流水号
				if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG1)){//资金池模式开通自动投标功能
					commonResponse.setResponseMsg("成功关闭自动投标功能");
					commonResponse.setResponsecode(Constants.CODE_SUCCESS);
				}else{
					common.setThirdPayConfigId(mem.getThirdPayFlagId());
					common.setRequsetNo(requestNo);
					common.setBussinessType(ThirdPayConstants.BT_CLOSEBIDAUTH);//类型： 关闭自动投标
					common.setTransferName(ThirdPayConstants.TN_CLOSEBIDAUTH);
					commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);
				}
				if(commonResponse.getResponsecode()!=null&&commonResponse.getResponsecode().equals(ThirdPayConstants.RECOD_SUCCESS)){
					PlBidAuto auto=plBidAutoService.getPlBidAuto(mem.getId());
					auto.setOrderTime(null);
					auto.setIsOpen(0);
					plBidAutoService.save(auto);
					webMsgInstance("0", Constants.CODE_SUCCESS, commonResponse.getResponseMsg(),  "", "", "", "", "");
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, commonResponse.getResponseMsg(),  "", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
			return "freemarker";
	}
	/**
	 * 初始化pl_bid_auto表
	 * @param id
	 */
	private PlBidAuto initPlBidAuto(BpCustMember mem){
		//自动投标添加一行记录
		PlBidAuto auto = new PlBidAuto();
		auto.setUserID(mem.getId());
		auto.setBidMoney(new java.math.BigDecimal(200));
		auto.setInterestStart(8);
		auto.setInterestEnd(24);
		auto.setPeriodStart(0);
		auto.setPeriodEnd(36);
		auto.setRateStart(null);
		auto.setRateEnd(null);
		auto.setKeepMoney(new java.math.BigDecimal(1000));
		auto.setIsOpen(0);
		auto.setOrderTime(null);
		auto.setBanned(0);
		auto=plBidAutoService.save(auto);
		return auto;
	}
	
	private java.math.BigDecimal bidMoney;
	private Integer interestStart;
	private Integer interestEnd;
	private Integer periodStart;
	private Integer periodEnd;
	private String rateStart;
	private String rateEnd;
	private java.math.BigDecimal keepMoney;
	private Integer isOpen;
	private Integer banned;//是否禁用 0 禁用 1开启
	private java.util.Date orderTime;


	public java.math.BigDecimal getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(java.math.BigDecimal bidMoney) {
		this.bidMoney = bidMoney;
	}

	public Integer getInterestStart() {
		return interestStart;
	}

	public void setInterestStart(Integer interestStart) {
		this.interestStart = interestStart;
	}

	public Integer getInterestEnd() {
		return interestEnd;
	}

	public void setInterestEnd(Integer interestEnd) {
		this.interestEnd = interestEnd;
	}

	public Integer getPeriodStart() {
		return periodStart;
	}

	public void setPeriodStart(Integer periodStart) {
		this.periodStart = periodStart;
	}

	public Integer getPeriodEnd() {
		return periodEnd;
	}

	public void setPeriodEnd(Integer periodEnd) {
		this.periodEnd = periodEnd;
	}

	public String getRateStart() {
		return rateStart;
	}

	public void setRateStart(String rateStart) {
		this.rateStart = rateStart;
	}

	public String getRateEnd() {
		return rateEnd;
	}

	public void setRateEnd(String rateEnd) {
		this.rateEnd = rateEnd;
	}

	public java.math.BigDecimal getKeepMoney() {
		return keepMoney;
	}

	public void setKeepMoney(java.math.BigDecimal keepMoney) {
		this.keepMoney = keepMoney;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getBanned() {
		return banned;
	}

	public void setBanned(Integer banned) {
		this.banned = banned;
	}

	public java.util.Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}
	
}
