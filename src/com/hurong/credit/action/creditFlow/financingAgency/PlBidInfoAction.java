package com.hurong.credit.action.creditFlow.financingAgency;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.action.pay.FontHuiFuAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.system.ErpMsg;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.pay.IPayService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.thirdInterface.FuiouService;
import com.hurong.credit.service.thirdInterface.HuiFuService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.QueueManger;
import com.hurong.credit.util.TemplateConfigUtil;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import com.thirdPayInterface.Huifu.BorrowerDetail;
import com.thirdPayInterface.Huifu.BorrowerDetails;

import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class PlBidInfoAction extends BaseAction {
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	ObSystemAccountService obSystemAccountService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private PlThirdInterfaceLogService plThirdInterfaceLogService;
    @Resource
	private IPayService iPayService;
    @Resource
    private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
    @Resource
    private HuiFuService huiFuService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource 
	private BpCouponsService bpCouponsService;
	@Resource
	private OpraterBussinessDataService opraterBussinessDataService;
	@Resource
	public  SendMesService    sendMesService;
	@Resource
	private FuiouService fuiouService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	//短信发送
	SmsSendUtil  vIPRegistrationSendUtil = new SmsSendUtil();
	
	private PlBidInfo plBidInfo;
	MoneyMoreMore moneyMoreMore;
	private Long id;
	
	private static final short PERSION_TYPE_0=0;//线上客户
	private static final short PERSION_TYPE_1=1;//线下客户
	private static final String SMALLLOAN="SmallLoan";//业务类别
	private static final short  FUNDRESOURCE_0=0;//个人
	private static final short  FUNDRESOURCE_1=1;//企业
	//检查是否重复提交
	public String formtoken="";
	

	public String getFormtoken() {
		return formtoken;
	}

	public void setFormtoken(String formtoken) {
		this.formtoken = formtoken;
	}

	public MoneyMoreMore getMoneyMoreMore() {
		return moneyMoreMore;
	}

	public void setMoneyMoreMore(MoneyMoreMore moneyMoreMore) {
		this.moneyMoreMore = moneyMoreMore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlBidInfo getPlBidInfo() {
		return plBidInfo;
	}

	public void setPlBidInfo(PlBidInfo plBidInfo) {
		this.plBidInfo = plBidInfo;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<PlBidInfo> list = plBidInfoService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("bidTime");
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String newBidList() {

		
		String bidtype = this.getRequest().getParameter("bidtype");
		String bidId = this.getRequest().getParameter("bidId");
		if(bidtype!=null&&"plmm".equals(bidtype)){
           QueryFilter filter0 = new QueryFilter(getRequest());
			
			filter0.addFilter("Q_plManageMoneyPlan.mmplanId_L_EQ", bidId);
			filter0.addFilter("Q_persionType_SN_EQ", "0");
			filter0.addSorted("buyDatetime", QueryFilter.ORDER_DESC);
			//优选理财计划
			List<PlManageMoneyPlanBuyinfo> list = plManageMoneyPlanBuyinfoService.getAll(filter0);
			Type type = new TypeToken<List<PlManageMoneyPlanBuyinfo>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"result\":");
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			buff.append(gson.toJson(list, type));
			buff.append("}");
			jsonString = buff.toString();
		}else{
			QueryFilter filter = new QueryFilter(getRequest());
			if(!"undefined".equals(bidId)) filter.addFilter("Q_plBidPlan.bidId_L_EQ", bidId);
			filter.addFilter("Q_state_SN_GE", "1");
			List<PlBidInfo> list = plBidInfoService.getAll(filter);
			//债券和散标
			//List<PlBidInfo> list = null;
			/*if(!"undefined".equals(bidId)){
				list = plBidInfoService.getPlBidList(Integer.valueOf(bidId));
			}*/
			
			Type type = new TypeToken<List<PlBidInfo>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"result\":");
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			buff.append(gson.toJson(list, type));
			buff.append("}");
			jsonString = buff.toString();
		}
		
		return SUCCESS;
	}


	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		PlBidInfo plBidInfo = plBidInfoService.get(id);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plBidInfo));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}
	/**
	 * 检查是否重复提交
	 * @return
	 */
    private boolean chkRepeat(){
    	boolean isRepeat=false;
    	Object tokenSession=this.getSession().getAttribute(MyUserSession.FORM_TOKEN);
    	if(tokenSession!=null&&tokenSession.toString().equals(this.formtoken)){
    		isRepeat=true;
    	}
    	this.getSession().removeAttribute(MyUserSession.FORM_TOKEN);
    	return isRepeat;
    }
	
    /**
	 * 添加及保存操作
	 */
	public String biding() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String mobile=this.getRequest().getParameter("mobile");
		String isMobile=this.getRequest().getParameter("isMobile");
		String backpath = this.getRequest().getParameter("backpath");
		this.getRequest().getSession().setAttribute("backpath",backpath);
		BpCustMember bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		BpCustMember cpCut = bpCustMemberService.get(bpCustMember.getId());
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(cpCut);
		try{
			if((Boolean) usercondition[0]){//验证是否 具备交易资格
				boolean chkIsRepeat=chkRepeat();
				if(isMobile!=null){
					chkIsRepeat=true;
				}
				if(chkIsRepeat){
					String couponId = this.getRequest().getParameter("couponId");//使用优惠券ID
					// 加入队列等待
					inputQueue();
					plBidInfo = (PlBidInfo) QueueManger.QueuePeek();
					//判断优惠券是否过期
					if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
						BpCoupons coupon = bpCouponsService.get(Long.valueOf(couponId));
						if(coupon!=null){
							Date d = new Date();
							if(coupon.getCouponEndDate().getTime()<d.getTime()){
								coupon.setCouponStatus(Short.valueOf("4"));
								bpCouponsService.save(coupon);
								webMsgInstance("0", Constants.CODE_FAILED, "所选择的优惠券已经过期,不能使用该优惠券！",  "MyFinance", "", "", "", "");
								return "freemarker";
							}
						}
					}
					//订单号
					String	orderNum=ContextUtil.createRuestNumber();
					//虚拟账户余额
					cpCut = obSystemAccountService.getAccountSumMoney(cpCut);
					//判断是否符合投标递增情况
					String[] chkBidArr=check(plBidInfo.getBidId(),plBidInfo);
					PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
					//检查是否使用了优惠券，此标是否设置了可用优惠券
					if(couponId!=null&&!couponId.equals("")){
						if(bidplan.getCoupon()!=null&&bidplan.getCoupon()==1){
						}else{
							chkBidArr=null;
						}
					}
					if(chkBidArr==null||chkBidArr[0].equals(Constants.CODE_FAILED)){
						webMsgInstance("0", Constants.CODE_FAILED, chkBidArr[2],"", "", "", "", "");
					} else {
						String freezeOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
						CommonRequst cq = new CommonRequst();
						BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);

						String loanMoney=plBidInfoService.getLoanMoney(plBidInfo);
						if(LoanMember==null || LoanMember.getThirdPayFlagId()==null ||LoanMember.getThirdPayFlagId().equals("")){
							webMsgInstance("0", Constants.CODE_FAILED, "借款人未开通第三方在支付","", "", "", "", "");
						}else if(loanMoney==null || loanMoney.equals("")){
							webMsgInstance("0", Constants.CODE_FAILED, "借款人借款金额错误","", "", "", "", "");
						}else if(LoanMember.getThirdPayFlagId().equals(cpCut.getThirdPayFlagId())){
							webMsgInstance("0", Constants.CODE_FAILED, "投资人和借款人不能是同一人","", "", "", "", "");
						}else if(cpCut!=null && cpCut.getThirdPayFlagId()!=null &&!"".equals(cpCut.getThirdPayFlagId())){
							Date date = new Date();
							BorrowerDetail detail = new BorrowerDetail();
							detail.setBorrowerCustId(LoanMember.getThirdPayFlagId().toString());
							mobile=this.getRequest().getParameter("mobile");

							//保存投标的信息
							plBidInfo.setUserId(cpCut.getId());
							plBidInfo.setUserName(cpCut.getLoginname());
							plBidInfo.setBidName(bidplan.getBidProName());
							if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
								plBidInfo.setCouponId(Long.valueOf(couponId));
							}
							if(bidplan.getBidRemark()!=null){
								plBidInfo.setPreAuthorizationNum(bidplan.getBidRemark());
							}
							plBidInfo.setState(PlBidInfo.TYPE0);
							plBidInfo.setOrderNo(orderNum);
							plBidInfo.setBidtime(new Date());
							plBidInfoService.save(plBidInfo);
							plBidInfoService.flush();

							if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
								cq.setIsMobile("1");
							}
							cq.setThirdPayConfigId(cpCut.getThirdPayFlagId());
							cq.setThirdPayConfigId0(cpCut.getThirdPayFlag0());
							cq.setRequsetNo(orderNum);//流水号
							cq.setBidId(plBidInfo.getPlBidPlan().getBidId().toString());//标的号
							cq.setFreezeOrdId(freezeOrdId);//冻结流水号
							cq.setAmount(plBidInfo.getUserMoney().setScale(2));//投标金额
							cq.setBidProNumber(bidplan.getLoanTxNo());//三方返回的唯一标识
							cq.setTransferName(ThirdPayConstants.TN_BID);//业务用途
							cq.setBussinessType(ThirdPayConstants.BT_BID);//业务类型
							
							if(cpCut.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)) {//个人用户投标的
								cq.setAccountType(0);//个人投标判断标志
							}else {
								cq.setAccountType(1);//企业投标判断标志
							}
							CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
							QueueManger.QueuePoll();

							/*if(cpCut.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户投标的
								cq.setAccountType(0);//个人投标判断标志
								//请求三方
								CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
								QueueManger.QueuePoll();
								*//*
								
								 if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
									 plBidInfo.setState(PlBidInfo.TYPE0);
									 plBidInfo.setUserId(cpCut.getId());
									 plBidInfo.setUserName(cpCut.getLoginname());
									 plBidInfo.setBidName(bidplan.getBidProName());
										plBidInfo.setOrderNo(orderNum);
										if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
											plBidInfo.setCouponId(Long.valueOf(couponId));
										}
										save(plBidInfo,1);//保存p2p投标信息
										QueueManger.QueuePoll();
										 webMsgInstance("0", Constants.CODE_SUCCESS, "投标请求成功","", "", "", "", "");
 								 }else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									 	plBidInfo.setState(PlBidInfo.TYPE0);
										plBidInfo.setUserId(cpCut.getId());
										plBidInfo.setUserName(cpCut.getLoginname());
										plBidInfo.setBidName(bidplan.getBidProName());
										if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
											plBidInfo.setCouponId(Long.valueOf(couponId));
										}
										if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
											plBidInfo.setPreAuthorizationNum(cr.getContract_no());
										}
										if(bidplan.getBidRemark()!=null){
											plBidInfo.setPreAuthorizationNum(bidplan.getBidRemark());
										}
										plBidInfo.setOrderNo(orderNum);
										//plBidInfo.setPreAuthorizationNum(freezeOrdId);//保存冻结号
										save(plBidInfo,1);//保存p2p投标信息
										Map<String,String> map1 = new HashMap<String, String>();
										map1.put("requestNo", orderNum);
										if(cr.getDealState()!=null&&!cr.getDealState().equals("")){
											map1.put("dealRecordStatus", cr.getDealState());
										}else{
											map1.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
										}
										if(cr.getContract_no()!=null&&!cr.getContract_no().equals("")){
											map1.put("contract_no", cr.getContract_no());
										}
										opraterBussinessDataService.biding(map1);
										QueueManger.QueuePoll();
										 webMsgInstance("0", Constants.CODE_SUCCESS, "投标成功","", "", "", "", "");
								 }else{
									 webMsgInstance("0", Constants.CODE_FAILED, "投标失败"+cr.getResponseMsg(),"", "", "", "", "");
								 }	*//*
							}else{//企业户投标
								cq.setAccountType(1);//企业投标判断标志
								CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
								QueueManger.QueuePoll();
								*//*
								 if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
										plBidInfo.setState(PlBidInfo.TYPE0);
										plBidInfo.setUserId(cpCut.getId());
										plBidInfo.setUserName(cpCut.getLoginname());
										plBidInfo.setBidName(bidplan.getBidProName());
										if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
											plBidInfo.setCouponId(Long.valueOf(couponId));
										}
										if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
											plBidInfo.setPreAuthorizationNum(cr.getContract_no());
										}
										plBidInfo.setOrderNo(orderNum);
										save(plBidInfo,1);//保存p2p投标信息
										Map<String,String> map1 = new HashMap<String, String>();
										map1.put("requestNo", orderNum);
										if(cr.getDealState()!=null&&!cr.getDealState().equals("")){
											map1.put("dealRecordStatus", cr.getDealState());
										}else{
											map1.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
										}
										if(cr.getContract_no()!=null&&!cr.getContract_no().equals("")){
											map1.put("contract_no", cr.getContract_no());
										}
										opraterBussinessDataService.biding(map1);
										 webMsgInstance("0", Constants.CODE_SUCCESS, "投标成功","", "", "", "", "");
								 }else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
									 plBidInfo.setState(PlBidInfo.TYPE0);
									 plBidInfo.setBidName(bidplan.getBidProName());
										plBidInfo.setOrderNo(orderNum);
										if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
											plBidInfo.setCouponId(Long.valueOf(couponId));
										}
										save(plBidInfo,1);//保存p2p投标信息
										QueueManger.QueuePoll();
										 webMsgInstance("0", Constants.CODE_SUCCESS, "投标请求成功","", "", "", "", "");
								 }else{
									 webMsgInstance("0", Constants.CODE_FAILED, "投标失败"+cr.getResponseMsg(),"", "", "", "", "");
								 }	*//*
							}*/
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "投资人未开通第三方在支付","", "", "", "", "");
						}
					}
					
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "不能重复提交","", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}

		if(mobile!=null&&"mobile".equals(mobile)){
//			webMsg.setUrl("bidPlanDetailPlBidPlan.do?bidId="+plBidInfo.getPlBidPlan().getBidId()+"&mobile=mobile");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else if(isMobile!=null&&"1".equals(isMobile)){
//			webMsg.setUrl("bidPlanDetailPlBidPlan.do?bidId="+plBidInfo.getPlBidPlan().getBidId()+"&mobile=mobile");
//			webMsgInstance("0", Constants.CODE_FAILED, "不能重复提交","", "", "", "", "");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
	}
	/**
	 * 添加及保存操作
	 * 做为方法保留
	 */
	public String bidingOld() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String mobile=this.getRequest().getParameter("mobile");
		String isMobile=this.getRequest().getParameter("isMobile");
		BpCustMember bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		BpCustMember cpCut = bpCustMemberService.get(bpCustMember.getId());
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		/**
		 * 第三方交易：用户交易资格查询(检查用户是否具备交易资格)
		 */
		Object[] usercondition=bpCustMemberService.checkUserDealCondition(cpCut);
		try{
			if((Boolean) usercondition[0]){//验证是否 具备交易资格
				boolean chkIsRepeat=chkRepeat();
				if(isMobile!=null){
					chkIsRepeat=true;
				}
				if(chkIsRepeat){
					String couponId = this.getRequest().getParameter("couponId");//使用优惠券ID
					// 加入队列等待
					inputQueue();
					plBidInfo = (PlBidInfo) QueueManger.QueuePeek();
					//判断优惠券是否过期
					if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
						BpCoupons coupon = bpCouponsService.get(Long.valueOf(couponId));
						if(coupon!=null){
							Date d = new Date();
							if(coupon.getCouponEndDate().getTime()<d.getTime()){
								coupon.setCouponStatus(Short.valueOf("4"));
								bpCouponsService.save(coupon);
								webMsgInstance("0", Constants.CODE_FAILED, "所选择的优惠券已经过期,不能使用该优惠券！",  "MyFinance", "", "", "", "");
								return "freemarker";
							}
						}
					}
					//订单号
					String	orderNum=ContextUtil.createRuestNumber();
					//虚拟账户余额
					cpCut = obSystemAccountService.getAccountSumMoney(cpCut);
					//判断是否符合投标递增情况
					String[] chkBidArr=check(plBidInfo.getBidId(),plBidInfo);
					PlBidPlan bidplan = plBidPlanService.get(plBidInfo.getBidId());
					//检查是否使用了优惠券，此标是否设置了可用优惠券
					if(couponId!=null&&!couponId.equals("")){
						if(bidplan.getCoupon()!=null&&bidplan.getCoupon()==1){
						}else{
							chkBidArr=null;
						}
					}
					if(chkBidArr==null||chkBidArr[0].equals(Constants.CODE_FAILED)){
						webMsgInstance("0", Constants.CODE_FAILED, chkBidArr[2],"", "", "", "", "");
					} else {
						String freezeOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
						CommonRequst cq = new CommonRequst();
						BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);
						String loanMoney=plBidInfoService.getLoanMoney(plBidInfo);
						if(LoanMember==null || LoanMember.getThirdPayFlagId()==null ||LoanMember.getThirdPayFlagId().equals("")){
							webMsgInstance("0", Constants.CODE_FAILED, "借款人未开通第三方在支付","", "", "", "", "");
						}else if(loanMoney==null || loanMoney.equals("")){
							webMsgInstance("0", Constants.CODE_FAILED, "借款人借款金额错误","", "", "", "", "");
						}else if(LoanMember.getThirdPayFlagId().equals(cpCut.getThirdPayFlagId())){
							webMsgInstance("0", Constants.CODE_FAILED, "投资人和借款人不能是同一人","", "", "", "", "");
						}else if(cpCut!=null && cpCut.getThirdPayFlagId()!=null &&!"".equals(cpCut.getThirdPayFlagId())){
							Date date = new Date();
							BorrowerDetail detail = new BorrowerDetail();
							detail.setBorrowerCustId(LoanMember.getThirdPayFlagId().toString());
							mobile=this.getRequest().getParameter("mobile");
							if(isMobile!=null&&!"".equals(isMobile)&&"1".equals(isMobile)){
								cq.setIsMobile("1");
							}
							cq.setThirdPayConfigId(cpCut.getThirdPayFlagId());//投资人平台会员标识  
							cq.setRequsetNo(orderNum);//流水号
							cq.setTransactionTime(date);	
							cq.setBidId(plBidInfo.getPlBidPlan().getBidId().toString());//标的号
							cq.setPlanMoney(bidplan.getBidMoney());//标的金额
							cq.setLoaner_thirdPayflagId(LoanMember.getThirdPayFlagId().toString());//借款人平台会员标识
							cq.setFreezeOrdId(freezeOrdId);//冻结流水号
							cq.setAmount(plBidInfo.getUserMoney().setScale(2));//投标金额
							cq.setBidName(bidplan.getBidProName());//项目名称
							cq.setBidProNumber(bidplan.getBidProNumber());//项目编号
							cq.setTransferName(ThirdPayConstants.TN_BID);//业务用途
							cq.setBussinessType(ThirdPayConstants.BT_BID);//业务类型
							if(cpCut.getCustomerType().equals(BpCustMember.CUSTOMER_PERSON)){//个人用户投标的
								cq.setAccountType(0);//个人投标判断标志
								CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
								if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
									plBidInfo.setState(PlBidInfo.TYPE0);
									plBidInfo.setUserId(cpCut.getId());
									plBidInfo.setUserName(cpCut.getLoginname());
									plBidInfo.setBidName(bidplan.getBidProName());
									plBidInfo.setOrderNo(orderNum);
									if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
										plBidInfo.setCouponId(Long.valueOf(couponId));
									}
									save(plBidInfo,1);//保存p2p投标信息
									QueueManger.QueuePoll();
									webMsgInstance("0", Constants.CODE_SUCCESS, "投标请求成功","", "", "", "", "");
								}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									plBidInfo.setState(PlBidInfo.TYPE0);
									plBidInfo.setUserId(cpCut.getId());
									plBidInfo.setUserName(cpCut.getLoginname());
									plBidInfo.setBidName(bidplan.getBidProName());
									if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
										plBidInfo.setCouponId(Long.valueOf(couponId));
									}
									if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
										plBidInfo.setPreAuthorizationNum(cr.getContract_no());
									}
									/*if(bidplan.getBidRemark()!=null){
											plBidInfo.setPreAuthorizationNum(bidplan.getBidRemark());
										}*/
									plBidInfo.setOrderNo(orderNum);
									//plBidInfo.setPreAuthorizationNum(freezeOrdId);//保存冻结号
									save(plBidInfo,1);//保存p2p投标信息
									Map<String,String> map1 = new HashMap<String, String>();
									map1.put("requestNo", orderNum);
									if(cr.getDealState()!=null&&!cr.getDealState().equals("")){
										map1.put("dealRecordStatus", cr.getDealState());
									}else{
										map1.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
									}
									if(cr.getContract_no()!=null&&!cr.getContract_no().equals("")){
										map1.put("contract_no", cr.getContract_no());
									}
									opraterBussinessDataService.biding(map1);
									QueueManger.QueuePoll();
									webMsgInstance("0", Constants.CODE_SUCCESS, "投标成功","", "", "", "", "");
								}else{
									webMsgInstance("0", Constants.CODE_FAILED, "投标失败"+cr.getResponseMsg(),"", "", "", "", "");
								}	
							}else{//企业户投标
								cq.setAccountType(1);//企业投标判断标志
								CommonResponse cr= ThirdPayInterfaceUtil.thirdCommon(cq);
								if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
									plBidInfo.setState(PlBidInfo.TYPE0);
									plBidInfo.setUserId(cpCut.getId());
									plBidInfo.setUserName(cpCut.getLoginname());
									plBidInfo.setBidName(bidplan.getBidProName());
									if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
										plBidInfo.setCouponId(Long.valueOf(couponId));
									}
									if(cr.getContract_no()!=null&&!"".equals(cr.getContract_no())){
										plBidInfo.setPreAuthorizationNum(cr.getContract_no());
									}
									plBidInfo.setOrderNo(orderNum);
									save(plBidInfo,1);//保存p2p投标信息
									Map<String,String> map1 = new HashMap<String, String>();
									map1.put("requestNo", orderNum);
									if(cr.getDealState()!=null&&!cr.getDealState().equals("")){
										map1.put("dealRecordStatus", cr.getDealState());
									}else{
										map1.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
									}
									if(cr.getContract_no()!=null&&!cr.getContract_no().equals("")){
										map1.put("contract_no", cr.getContract_no());
									}
									opraterBussinessDataService.biding(map1);
									webMsgInstance("0", Constants.CODE_SUCCESS, "投标成功","", "", "", "", "");
								}else if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_APPLAY)){
									plBidInfo.setState(PlBidInfo.TYPE0);
									plBidInfo.setBidName(bidplan.getBidProName());
									plBidInfo.setOrderNo(orderNum);
									if(couponId!=null&&!couponId.equals("")&&!couponId.equals("null")){
										plBidInfo.setCouponId(Long.valueOf(couponId));
									}
									save(plBidInfo,1);//保存p2p投标信息
									QueueManger.QueuePoll();
									webMsgInstance("0", Constants.CODE_SUCCESS, "投标请求成功","", "", "", "", "");
								}else{
									webMsgInstance("0", Constants.CODE_FAILED, "投标失败"+cr.getResponseMsg(),"", "", "", "", "");
								}	
							}
						}else{
							webMsgInstance("0", Constants.CODE_FAILED, "投资人未开通第三方在支付","", "", "", "", "");
						}
					}
					
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "不能重复提交","", "", "", "", "");
				}
			}else{
				forwardPage=usercondition[2].toString();
				webMsgInstance("0", Constants.CODE_FAILED, usercondition[1].toString(),"", "", "", "", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			webMsgInstance("0", Constants.CODE_FAILED, "系统错误，请联系管理员","", "", "", "", "");
		}
		if(mobile!=null&&"mobile".equals(mobile)){
			webMsg.setUrl("bidPlanDetailPlBidPlan.do?bidId="+plBidInfo.getPlBidPlan().getBidId()+"&mobile=mobile");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else if(isMobile!=null&&"1".equals(isMobile)){
			webMsg.setUrl("bidPlanDetailPlBidPlan.do?bidId="+plBidInfo.getPlBidPlan().getBidId()+"&mobile=mobile");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobilemessage.ftl");
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		}
		return "freemarker";
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
			String orderDate, PlBidInfo plBidInfo2,String isMobile) {
		String[] ret=new String[2];
		BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);
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
				/**
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
				 */
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("requestNo", orderNum);
				map.put("basePath", this.getBasePath());
				map.put("platformUserNo", cpCut.getThirdPayFlagId());
				map.put("customerId", cpCut.getId().toString());
				map.put("customerType", "0");
				map.put("bidPlanId", plBidInfo.getPlBidPlan().getBidId().toString());
				//map.put("bidPlanType", PlBidPlan.HRY_BID);
				map.put("bidPlanMoney", bidplan.getBidMoney().toString());
				map.put("bidMoney", plBidInfo.getUserMoney().toString());
				map.put("targetplatformUserNo", LoanMember.getThirdPayFlag0().toString());
				String[] returnret = {};
				if(isMobile!=null){
					returnret=yeePayService.mobilefiancialTransfer(map,"isMobile");
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
		}
		return ret;
	}

	/**
	 * 富有金账户投标准备
	 * @param cpCut
	 * @param orderNum
	 * @param orderDate
	 * @param plBidInfo2
	 * @return
	 */
	private String[] tenderToFuiouGold(BpCustMember cpCut, String orderNum,
			String orderDate, PlBidInfo plBidInfo) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);
		String loanMoney=plBidInfoService.getLoanMoney(plBidInfo);
		if(LoanMember==null || LoanMember.getThirdPayFlagId()==null ||LoanMember.getThirdPayFlagId().equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人未开通第三方在支付";
		}else if(loanMoney==null || loanMoney.equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人借款金额错误";
		}else {
			if(cpCut!=null && cpCut.getThirdPayFlagId()!=null &&!"".equals(cpCut.getThirdPayFlagId())){
				cpCut=fuiouService.getCurrentMoney(cpCut);
				System.out.println("cpCut.getAvailableInvestMoney()==="+cpCut.getAvailableInvestMoney());
				System.out.println("cplBidInfo=="+plBidInfo.getUserMoney());
				if(cpCut.getAvailableInvestMoney().compareTo(plBidInfo.getUserMoney())>=0){
					 String amount=plBidInfo.getUserMoney().multiply(new BigDecimal(100)).setScale(0).toString();
					 String[] returnret=fuiouService.preAuth(orderNum, cpCut.getThirdPayFlag0(), LoanMember.getThirdPayFlag0(), amount, "", this.getRequest());
					 if(returnret[0].equals(Constants.CODE_SUCCESS)){
						 plBidInfo.setPreAuthorizationNum(returnret[3]);
						 Map<String,Object> map=new HashMap<String,Object>();
						 map.put("investPersonId",cpCut.getId());//投资人Id
						 map.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
						 map.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
						 map.put("money",plBidInfo.getUserMoney());//交易金额
						 map.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
						 map.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
						 map.put("recordNumber",orderNum);//交易流水号
						 map.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
						 ret =obAccountDealInfoService.operateAcountInfo(map);
						 //ret = payCommonService.dealToERP(cpCut, plBidInfo.getUserMoney().toString(),orderNum,"4");
						 ret[0]=returnret[0];
						 ret[1]="投标成功";
					 }else{
						 ret[0]=returnret[0];
						 ret[1]="预授权投标失败："+returnret[1];
					 }
				}/*else if((cpCut.getAvailableInvestMoney().add(cpCut.getUnChargeMoney())).compareTo(plBidInfo.getUserMoney())>=0){
					ret[0]=Constants.CODE_SUCCESS;
					 ret[1]="5";
				}*/else{
					ret[0]=Constants.CODE_SUCCESS;
					 ret[1]="5";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="投资人未开通第三方在支付";
			}
			
		
		}
		return ret;
	}

	/**
	 * 检测 是否安全 是否可以进行投标
	 * bidid 标的id
	 * @return
	 */
	public String[] check(Long bidid,PlBidInfo plBidInfo){
		String str="";
		String[] ret=new String[3];
		BpCustMember cpCut=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		PlBidPlan plan=plBidPlanService.get(bidid);
		String[] bidStat = plBidPlanService.bidStat(bidid);
		
		//虚拟账户余额
		cpCut = obSystemAccountService.getAccountSumMoney(cpCut);
		try{
			int novice=0;
			//判断是否是新手专享
			if(plan.getNovice()!=null&&!plan.getNovice().equals("")&&plan.getNovice()==1){
					List<Short> listLong = new ArrayList<Short>();
					listLong.add(Short.valueOf("1"));
					listLong.add(Short.valueOf("2"));
					QueryFilter fit = new QueryFilter(this.getRequest());
			    	fit.addFilter("Q_userId_L_EQ", cpCut.getId().toString());
			    	fit.addFilterIn("Q_state_SN_IN", listLong);
			    	List<PlBidInfo> infoList = plBidInfoService.getAll(fit);
			    	int num = infoList.size();
					 if(num>0){
						 novice=1;
					 }else{
						 novice=0;
					 }
			}else{
				novice=0;
			}
			if(novice==0){
				 if (!"1".equals(bidStat[0])) {
						str="{\"success\":false,\"type\":\"0\",\"msg\":'\"" + bidStat[1]+ "\"'}";
						ret[0]=Constants.CODE_FAILED;
						ret[2]=bidStat[1];
						QueueManger.QueueClear();
					}else if (!plBidPlanService.compare(plBidInfo.getBidId(),
							plBidInfo.getUserMoney())) {
						str="{\"success\":false,\"type\":\"0\",\"msg\":\"投标金额大于剩余金额,不能进行投标！\"}";
						ret[0]=Constants.CODE_FAILED;
						ret[2]="投标金额大于剩余金额,不能进行投标！";
						QueueManger.QueueClear();
					}else if (!plBidPlanService.compareMaxMoney(plBidInfo.getBidId(),
							plBidInfo.getUserMoney())) {
						str="{\"success\":false,\"type\":\"0\",\"msg\":\"投标金额大于上限金额,不能进行投标！\"}";
						ret[0]=Constants.CODE_FAILED;
						ret[2]="投标金额大于上限金额,不能进行投标！";
						QueueManger.QueueClear();
					}else if(cpCut.getAvailableInvestMoney()==null||cpCut.getAvailableInvestMoney().compareTo(plBidInfo.getUserMoney())<0){
						str="{\"success\":false,\"type\":\"0\",\"msg\":\"系统账户余额不足,不能进行投标！\"}";
						ret[0]=Constants.CODE_FAILED;
						ret[2]="系统账户余额不足,不能进行投标！";
						QueueManger.QueueClear();
					} else if(plBidPlanService.compareIsLasterMoney(plBidInfo.getBidId(),
							plBidInfo.getUserMoney())){
						
						if(!plBidPlanService.compareCurMIsEQAft(plBidInfo.getBidId(),plBidInfo.getUserMoney())){
							str="{\"success\":false,\"type\":\"0\",\"msg\":\"剩余投资额小于1000元，投资金额必须大于或等于起投金额！\"}";
							ret[0]=Constants.CODE_FAILED;
							ret[2]="剩余投资额小于1000元，投资金额必须大于或等于起投金额！";
							QueueManger.QueueClear();
						}else{
							ret[0]=Constants.CODE_SUCCESS;
							ret[2]="";
							str="";
						}
					}else{
						if(!plBidPlanService.compareToStatrMoney(plBidInfo.getBidId(),
								plBidInfo.getUserMoney())){
							str="{\"success\":false,\"type\":\"0\",\"msg\":\"投标金额不符合该标的规则（"+plan.getStartMoney()+"元起投，"+plan.getRiseMoney()+"元递增）\"}";
							ret[0]=Constants.CODE_FAILED;
							ret[2]="投标金额不符合该标的规则（"+plan.getStartMoney()+"元起投，"+plan.getRiseMoney()+"元递增）";
							QueueManger.QueueClear();
						}else if(!plBidPlanService.compareToRiseMoney(plBidInfo.getBidId(),plBidInfo.getUserMoney())){
							str="{\"success\":false,\"type\":\"0\",\"msg\":\"投标金额不符合该标的规则（"+plan.getStartMoney()+"元起投，"+plan.getRiseMoney()+"元递增）\"}";
							ret[0]=Constants.CODE_FAILED;
							ret[2]="投标金额不符合该标的规则（"+plan.getStartMoney()+"元起投，"+plan.getRiseMoney()+"元递增）";
							QueueManger.QueueClear();
						} else{
							ret[0]=Constants.CODE_SUCCESS;
							str="";
						}
					}
			}else{
				str="{\"success\":false,\"type\":\"0\",\"msg\":\"该标只适用于新手专享投标!\"}";
				ret[0]=Constants.CODE_FAILED;
				ret[2]="该标只适用于新手专享投标!";
			}
		
			
		}catch (Exception e) {
			ret[0]=Constants.CODE_FAILED;
			str="{\"success\":false,\"type\":\"0\",\"msg\":\"出现错误请检查\"}";
			QueueManger.QueueClear();
		}
		ret[1]=str;
		return ret;
	}
	
	/**
	 * 重新投标
	 * @param orderNum
	 */
	public String restartBid(){
		//判断是否符合投标递增情况
		Long bidid=Long.valueOf(StringUtil.html2Text(this.getRequest().getParameter("bidid")));
		Long bidInfoId=Long.valueOf(StringUtil.html2Text(this.getRequest().getParameter("bidInfoId")));
		PlBidInfo plBidInfo=plBidInfoService.get(bidInfoId);
		String[] chkBidArr=check(bidid,plBidInfo);
		if(chkBidArr==null||chkBidArr[0].equals(Constants.CODE_FAILED)){
			setJsonString(chkBidArr[1]);
			System.out.println(getJsonString());
			Gson gson=new Gson();
			ErpMsg  msg=gson.fromJson(getJsonString(), ErpMsg.class);
			if(msg!=null){
				//第三个参数是跳转到首页
				webMsgInstance("0", Constants.CODE_SUCCESS, msg.getMsg(),  "balanceLackof", "", "", "", "");
			}else{
				//设置 返回提示消息
				webMsgInstance("0", Constants.CODE_FAILED, "操作出错",  "", "", "", "", "");
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());

		} else{
			BpCustMember mem =(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			String orderNum=StringUtil.html2Text(this.getRequest().getParameter("orderNum"));
			String orderDate=DateUtil.dateToStr(new Date(), "yyyyMMdd");
			tenderByHuifu(mem,orderNum,orderDate,plBidInfo);
		}
		
	return "freemarker";
	}
	
	/**
	 * @param mem
	 * @param orderNum
	 * @param orderDate
	 * @param oweParams 自定义参数 投标信息的id 会付返回后使用
	 * @return
	 */
	private String[] tenderByHuifu(BpCustMember mem,String orderNum,String orderDate,PlBidInfo plBidInfo){
        String[] ret=new String[2];
		BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidInfo);
		String loanMoney=plBidInfoService.getLoanMoney(plBidInfo);
		//冻结订单
		String freezeOrdId=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
		if(LoanMember==null || LoanMember.getThirdPayFlagId()==null ||LoanMember.getThirdPayFlagId().equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人未开通第三方在支付";
		}else if(loanMoney==null || loanMoney.equals("")){
			ret[0]=Constants.CODE_FAILED;
			ret[1]="借款人借款金额错误";
		}else {
            String amount=setAmount(plBidInfo.getUserMoney().toString());
			String borrowerDetails="[{\"BorrowerCustId\":\""+LoanMember.getThirdPayFlagId()+"\", \"BorrowerAmt\": \""+amount+"\", \"BorrowerRate\":\""+FontHuiFuAction.BorrowerRate_0+"\" }]";
			ret=huiFuService.tenderHuiFu(getResponse(), mem, "InitiativeTender", orderNum, orderDate, amount, borrowerDetails, "Y", freezeOrdId, "", getBasePath());
		}
			return ret;
	}
	/**
	 * 保存投标信息
	 * 
	 * @param bid
	 * isUpdateStat 是否更新标的状态 0 更新 1 不更新
	 */
	private void save(PlBidInfo bid,int isUpdateStat) {
		if (bid.getId() == null) {
			if(isUpdateStat==0){
				System.out.println("=======修改状态");
                plBidPlanService.updateStatByMoney(bid.getBidId(), bid.getUserMoney());
			}
			bid.setBidtime(new Date());
			plBidInfoService.save(bid);
			
		}
	}
public String setAmount(String amount) {
		
		DecimalFormat   df   =   new   DecimalFormat("#####0.00");   
		return amount = df.format(Double.valueOf(amount));
	}

	/**
	 * 投标放入队列等待匹配
	 */
	public void inputQueue() {
		QueueManger.QueueClear();
		if (plBidInfo != null) {
			QueueManger.QueueOffer(plBidInfo);
		}
	}
	
	public static void main(String[] args) {
		BorrowerDetails details = new BorrowerDetails();
		List<BorrowerDetail> list = new ArrayList();
		BorrowerDetail detail1 = new BorrowerDetail();
		BorrowerDetail detail2 = new BorrowerDetail(); 
		detail1.setBorrowerAmt("100");
		detail1.setBorrowerCustId("zhagsan");
		detail1.setBorrowerRate("0.01");
		detail2.setBorrowerAmt("1000");
		detail2.setBorrowerCustId("002");
		detail2.setBorrowerRate("0.3");	
	/*	try {
			Method method=detail1.getClass().getDeclaredMethod("setBorrowerAmt",String.class);
			method.invoke(detail1,"100");
		}  catch (Exception e) {
			e.printStackTrace();
		}*/
		list.add(detail1);
		list.add(detail2);
		details.setList(list);
		JSONArray.fromObject(details.getList());
		System.out.println(JSONArray.fromObject(details.getList()));
	}
}




