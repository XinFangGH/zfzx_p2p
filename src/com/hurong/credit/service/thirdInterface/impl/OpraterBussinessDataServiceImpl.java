package com.hurong.credit.service.thirdInterface.impl;

import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObAccountDealInfoDao;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.finance.compensatory.PlBidCompensatoryFlowDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUserAccountInfoDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlEarlyRedemptionDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderAssignInterestDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatoryFlow;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.activity.BpActivityManageService;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidSaleService;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;
import com.hurong.credit.service.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordService;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlEarlyRedemptionService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.sms.SendMesService;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OpraterBussinessDataServiceImpl implements OpraterBussinessDataService {
	//log4j日志文件记录
	protected Log logger=LogFactory.getLog(OpraterBussinessDataServiceImpl.class);
	
	//初始化静态的BidMap
	public final static  HashMap<Integer,Lock> bidmap=new HashMap<Integer,Lock>();
	static{
		for(int i=0;i<100;i++){
			bidmap.put(i, new ReentrantLock());
		}
	}
	
	@Resource
	public BpCustMemberService bpCustMemberService;
	@Resource
	public ObSystemAccountService obSystemAccountService;
	@Resource
	public WebBankcardService webBankcardService;
	@Resource
	public ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	public PlBidInfoService plBidInfoService;
	@Resource
	public InvestPersonInfoService investPersonInfoService;
	@Resource
	public PlBidPlanService plBidPlanService;
	@Resource
	public BpFundIntentService bpFundIntentService;
	@Resource
	public PlBidAutoService plBidAutoService;
	@Resource
	public BpCustMemberDao bpCustMemberDao;
	@Resource
	public YeePayService yeePayService;
	@Resource
	public  PlBidPlanDao plBidPlanDao;
	@Resource
	public SlEarlyRepaymentRecordService slEarlyRepaymentRecordService;
	@Resource
	public PlBidSaleService plBidSaleService;
	@Resource
	public PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	@Resource
	public PlManageMoneyPlanService plManageMoneyPlanService;
	@Resource
	public BpActivityManageService bpActivityManageService;
	@Resource 
	private BpCouponsService bpCouponsService;
	@Resource 
	private PlFinanceProductUserAccountInfoDao plFinanceProductUserAccountInfoDao;
	@Resource 
	private ObAccountDealInfoDao obAccountDealInfoDao;
	@Resource 
	private PlBidCompensatoryFlowDao plBidCompensatoryFlowDao;
	@Resource 
	private PlBidCompensatoryService plBidCompensatoryService;
	@Resource
	private PlMmOrderAssignInterestDao plMmOrderAssignInterestDao;
	@Resource
	private PlManageMoneyPlanBuyinfoDao plManageMoneyPlanBuyinfoDao;
	@Resource
	private PlEarlyRedemptionDao plEarlyRedemptionDao;
	@Resource
	private PlEarlyRedemptionService plEarlyRedemptionService;
	@Resource
	private PlMmOrderAssignInterestService plMmOrderAssignInterestService;
	@Resource
	private ThirdPayRecordService thirdPayRecordService;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private SendMesService sendMesService;
	@Resource
	private PlBidCompensatoryService  plBidCompenstoryService;
	private String requestNo;
	
	//短信发送
	SmsSendUtil  smsSendUtil = new SmsSendUtil();

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getRequestNo() {
		return requestNo;
	}
	
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	/**
	 * 开通第三方支付接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 *  map.get("custermemberId")   开通第三方支付的用户id  有的第三方无法获取注册用户在我们平台的id  则会传空值
	 *  map.get("custermemberType") 开通第三方支付的用户类型
	 *  map.get("platformUserNo");  第三方支付的账号
	 *  map.get("platFormUserName")  第三方支付的昵称
	 * @return
	 */
	@Override
	public String[] regedit(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String custmemberId=null;
		String dealRecordStatus=null;
		if(!"".equals(map.get("custermemberId").toString())&&map.get("custermemberId")!=null){
			custmemberId=map.get("custermemberId");
			if(map.get("capAcntNo")!=null&&!"".equals(map.get("capAcntNo").toString())){//银行卡号不为空
				List<WebBankcard> card = webBankcardService.getBycusterId(Long.valueOf(custmemberId));
				WebBankcard card1 = null;
				if(card.size()>0){//如果有则更新
					card1 = card.get(0);
					if(card1!=null){
						card1.setCardNum(map.get("capAcntNo"));
					}
					webBankcardService.merge(card1);
				}else{//没有则新建
					card1 = new WebBankcard(); 
					card1.setCardNum(map.get("capAcntNo").toString());
					card1.setCustomerId(Long.valueOf(custmemberId.toString()));
					webBankcardService.save(card1);
				}
			}
		}else{
			
		}
		if(custmemberId!=null){
			synchronized(custmemberId){
				String platformUserNo=map.get("platformUserNo").toString();
				String platFormUserName=map.get("platFormUserName").toString();
//				if(platFormUserName.equals("")){
//					platFormUserName=platformUserNo;
//				}
				BpCustMember bpCustMember=bpCustMemberService.get(Long.valueOf(custmemberId));
				//用来检查bpCustMember有没有进行实名认证过
//				if(bpCustMember!=null&&(bpCustMember.getThirdPayConfig()!=null||!"".equals(bpCustMember.getThirdPayConfig()))&&(bpCustMember.getThirdPayFlagId()!=null||!"".equals(bpCustMember.getThirdPayFlagId()))){
				if(bpCustMember!=null){//企业请求是不会有ThirdPayConfig 
					if (map.containsKey("dealRecordStatus") && StringUtils.isNotEmpty(map.get("dealRecordStatus"))) {
						dealRecordStatus = map.get("dealRecordStatus");
						//如果是回调审核不通过
						if (ObAccountDealInfo.DEAL_STATUS_3.toString().equals(dealRecordStatus)) {
							bpCustMember.setIsCheckCard("0");
							ret[0]=Constants.CODE_SUCCESS;
							ret[1]="三方审核不通过";
							return ret;
						}
					}
					
					bpCustMember.setThirdPayFlag0(platFormUserName);
					if(configMap.get("thirdPayConfig").toString()!=null&&!"".equals(configMap.get("thirdPayConfig").toString())){
						if(configMap.get("thirdPayConfig").toString().trim().toLowerCase().equals("fuiouconfig".toLowerCase().trim())){
							if(map.get("mobile_no").toString()!=null&&!"".equals(map.get("mobile_no").toString())){
								bpCustMember.setThirdPayFlagId(bpCustMember.getTelphone());
							}
						}else{
							bpCustMember.setThirdPayFlagId(platformUserNo);
						}
					}
					bpCustMember.setThirdPayConfig(configMap.get("thirdPayConfig").toString());
					if (StringUtils.isNotEmpty(bpCustMember.getThirdPayConfig())){
						map.put("thirdPayConfig",bpCustMember.getThirdPayConfig());
					}
					//判断是否 注册时生成了虚拟账户 否则就更新虚拟账户
					ObSystemAccount obSystemAccount=obSystemAccountService.getByInvrstPersonId(bpCustMember.getId());
					if(obSystemAccount==null){
						obSystemAccountService.saveAccount("1", bpCustMember.getTruename(), bpCustMember.getId().toString(), bpCustMember.getCardcode(), "0", "0");
					}else{
						obSystemAccount.setAccountName(bpCustMember.getTruename());
						obSystemAccount.setInvestPersonName(bpCustMember.getTruename());
						obSystemAccount.setAccountNumber(bpCustMember.getCardcode()+"-"+0);
						obSystemAccountService.save(obSystemAccount);
					}
					
				 	if(bpCustMember.getCustomerType()!=null&&bpCustMember.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//企业客户
//						bpCustMember.setThirdPayStatus(BpCustMember.THIRDPAY_DEACCTIVED);//未激活
						bpCustMember.setThirdPayStatus(BpCustMember.THIRDPAY_ACCTIVED);//已激活
					}else{
						if(bpCustMember.getCardcode()!=null&&bpCustMember.getCardcode().length()==18){
							String year=bpCustMember.getCardcode().substring(6, 10);
							String month=bpCustMember.getCardcode().substring(10,12);
							String date=bpCustMember.getCardcode().substring(12,14);
							String strDate=year+"-"+month+"-"+date;
							Date birthday=java.sql.Date.valueOf(strDate);
							bpCustMember.setBirthday(birthday);
						}
						bpCustMember.setThirdPayStatus(BpCustMember.THIRDPAY_ACCTIVED);//已激活
					}

				 	bpCustMember.setIsCheckCard("1");
					bpCustMember.setIsCheckCardTime(new Date());//实名认证时间
					if (map.containsKey("mobilePhone") && StringUtils.isNotEmpty(map.get("mobilePhone")))//用户在银行填写的预留手机号码
						bpCustMember.setHomePhone(map.get("mobilePhone"));
					bpCustMember.setBambooJoint((short) 1);//节点标识
					bpCustMemberService.merge(bpCustMember);//TODO 持久化之前没有对实体进行异常处理  有可能会导致易宝开通成功 p2p没有保存成功 --李云飞


					//开通第三方成功发送短信、邮件、站内信。
					Map<String, String> mapMessage = new HashMap<String, String>();
					mapMessage.put("key", "sms_SignThirdPay");
					mapMessage.put("userId",bpCustMember.getId().toString());
					mapMessage.put("${name}", bpCustMember.getLoginname());
					//String result =  sendMesService.sendSmsEmailMessage(mapMessage);
					
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="开通第三方支付账户业务数据处理成功";
					logger.info("bpCustMember.id="+bpCustMember.getId()+";bpCustMember.Cardcode="+bpCustMember.getCardcode()+";bpCustMember.thirdPayConfig()="+bpCustMember.getThirdPayConfig()+";bpCustMember.getthirdPayFlagId="+bpCustMember.getThirdPayFlagId());
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="开通第三方支付账户业务数据处理失败:数据不存在或者已经处理过了";
					logger.info("失败原因：bpCustMember="+bpCustMember+"; 描述："+ret[1]);	
				}
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="业务数据处理失败";
			logger.info("custmemberId 为空");
		}
		return ret;
	}


	/**
	 * 银行修改企业实名认证接口，所以把企业实名认证提取出来
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	@Override
	public String[] regeditBus(Map<String, String> map) {
		String[] ret=new String[2];
		Long custmemberId=null;
		String dealRecordStatus=null;
		if (map.containsKey("custermemberId") && StringUtils.isNotEmpty(map.get("custermemberId"))) {
			custmemberId = Long.valueOf(map.get("custermemberId"));
			BpCustMember bpCustMember = bpCustMemberService.get(custmemberId);
			synchronized(custmemberId){
				if (bpCustMember != null) {
					if (map.containsKey("dealRecordStatus") && StringUtils.isNotEmpty(map.get("dealRecordStatus"))) {
						dealRecordStatus = map.get("dealRecordStatus");
						if (ObAccountDealInfo.DEAL_STATUS_1.toString().equals(dealRecordStatus)) {//处理中
							bpCustMember.setIsCheckCard("2");
							bpCustMemberService.merge(bpCustMember);
						}else if(ObAccountDealInfo.DEAL_STATUS_3.toString().equals(dealRecordStatus)) {
							bpCustMember.setIsCheckCard("0");
							bpCustMemberService.merge(bpCustMember);
						}else {
							String platformUserNo = map.get("platformUserNo");
							String platFormUserName = map.get("platFormUserName");
							String artificialIdentityCode = map.get("artificialIdentityCode");//法人证件号码
							String artificialRealName = map.get("artificialRealName");//法人真实姓名
							String mobilePhone = map.get("mobilePhone");//法人手机号
							String trueName = map.get("trueName");//企业名称
							String cardCode = map.get("cardCode");//企业三合一证件号码
							String licenceCode = map.get("licenceCode");//营业执照编号
							String taxRegCode = map.get("taxRegCode");//税务登记号
							String orgCode = map.get("orgCode");//组织机构代码

							bpCustMember.setThirdPayConfig(configMap.get("thirdPayConfig").toString());
							if (StringUtils.isNotEmpty(bpCustMember.getThirdPayConfig())){
								map.put("thirdPayConfig",bpCustMember.getThirdPayConfig());
							}

							bpCustMember.setThirdPayFlag0(platFormUserName);
							bpCustMember.setThirdPayFlagId(platformUserNo);
							bpCustMember.setTruename(trueName);
							bpCustMember.setCardcode(cardCode);
							bpCustMember.setLegalPerson(artificialRealName);
							bpCustMember.setLegalNo(artificialIdentityCode);
							if (StringUtils.isNotEmpty(licenceCode))
								bpCustMember.setBusinessLicense(licenceCode);
							if (StringUtils.isNotEmpty(taxRegCode))
								bpCustMember.setTaxNo(taxRegCode);
							if (StringUtils.isNotEmpty(orgCode) && StringUtils.isEmpty(cardCode))
								bpCustMember.setCardcode(orgCode);

							bpCustMember.setIsCheckCard("1");
							//判断是否 注册时生成了虚拟账户 否则就更新虚拟账户
							ObSystemAccount obSystemAccount=obSystemAccountService.getByInvrstPersonId(bpCustMember.getId());
							if(obSystemAccount==null){
								obSystemAccountService.saveAccount("1", bpCustMember.getTruename(), bpCustMember.getId().toString(), bpCustMember.getCardcode(), "0", "0");
							}else{
								obSystemAccount.setAccountName(bpCustMember.getTruename());
								obSystemAccount.setInvestPersonName(bpCustMember.getTruename());
								obSystemAccount.setAccountNumber(bpCustMember.getCardcode()+"-"+0);
								obSystemAccountService.save(obSystemAccount);
							}

							bpCustMember.setThirdPayStatus(BpCustMember.THIRDPAY_ACCTIVED);//已激活
							bpCustMemberService.merge(bpCustMember);
							if (StringUtils.isNotEmpty(bpCustMember.getThirdPayConfig())){
								map.put("thirdPayConfig",bpCustMember.getThirdPayConfig());
							}
							ret[0]=Constants.CODE_SUCCESS;
							ret[1]="开通第三方支付账户业务数据处理成功";
							logger.info("bpCustMember.id="+bpCustMember.getId()+";bpCustMember.Cardcode="+bpCustMember.getCardcode()+";bpCustMember.thirdPayConfig()="+bpCustMember.getThirdPayConfig()+";bpCustMember.getthirdPayFlagId="+bpCustMember.getThirdPayFlagId());
						}
					}else {
						ret[0]=Constants.CODE_FAILED;
						ret[1]="状态为空";
						logger.info("custmemberId 为空");
					}
				}
			}
		}else {
			ret[0]=Constants.CODE_FAILED;
			ret[1]="业务数据处理失败";
			logger.info("custmemberId 为空");
		}

		return ret;
	}
	
	/**
	 * 第三方支付绑卡接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 *  map.get("custermemberId")   绑卡的第三方支付的用户id
	 *  map.get("requestNo");  交易请求流水号
	 *  map.get("bankCardNo") 卡号
	 *  map.get("bankCode")    开户行编码
	 *  map.get("bankstatus");  绑卡状态
	 *  map.get("bankName");绑定银行名
	 * @return
	 */
	@Override
	public String[] bandCard(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if(!"".equals(requestNo)){
			card=webBankcardService.getByRequestNo(requestNo);
		}
		if(card!=null){
			synchronized(requestNo){
				if (map.containsKey("custermemberId")&&StringUtils.isNotEmpty(map.get("custermemberId"))) {
					String custmemberId=map.get("custermemberId");
					card.setCustomerId(Long.valueOf(custmemberId));
				}
				if(map.containsKey("thirdPayConfig")&&StringUtils.isNotEmpty(map.get("thirdPayConfig"))){
					card.setThirdConfig(configMap.get("thirdPayConfig").toString());
				}
				
				if(map.containsKey("bankCardNo")&&!"".equals(map.get("bankCardNo")) && map.get("bankCardNo") !=null){
					card.setCardNum(map.get("bankCardNo"));
				}
				if(map.containsKey("bankCode")&&!"".equals(map.get("bankCode")) && map.get("bankCode") !=null){
					card.setBankId(map.get("bankCode"));
				}
				if(map.containsKey("bankName")&&!"".equals(map.get("bankName"))&& map.get("bankName") !=null){
					card.setBankname(map.get("bankName"));
				}
				
				if(map.containsKey("bankType")&&!"".equals(map.get("bankType")) && map.get("bankType") !=null){
					card.setAccounttype(map.get("bankType"));
				}
				if(map.containsKey("province")&&!"".equals(map.get("province")) && map.get("province") !=null){
					card.setProvinceId(Long.valueOf(map.get("province")));
				}
				if(map.containsKey("city")&&!"".equals(map.get("city")) && map.get("city") !=null){
					card.setCityId(Long.valueOf(map.get("city")));
				}
				
				if("".equals(map.get("bankstatus"))){
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
				}else{
					card.setBindCardStatus(map.get("bankstatus"));
				}
				webBankcardService.merge(card);
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="绑定银行卡业务数据处理成功";
				logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
			}
		}else if(map.containsKey("bankCode")){
			if (map.containsKey("bandType") && "1".equals(map.get("bandType"))) {//从实名认证进来的绑卡信息
				System.out.println("实名认证绑卡开始---------->");
				card = new WebBankcard();
				String custmemberId = map.get("custermemberId");
				if (StringUtils.isNotEmpty(custmemberId)){
					BpCustMember member = bpCustMemberService.get(Long.valueOf(custmemberId));
					if (member != null) {
						List<WebBankcard> bankcardList = webBankcardService.getBycustAndState(member.getId(),WebBankcard.BINDCARD_STATUS_SUCCESS);

						if (bankcardList == null || bankcardList.size()<=0) {//如果没有卡才新增卡，因为实名认证的时候一定是用户的第一张卡
                            card.setCustomerId(member.getId());
                            if(map.containsKey("thirdPayConfig")&&StringUtils.isNotEmpty(map.get("thirdPayConfig"))){
                                card.setThirdConfig(map.get("thirdPayConfig").toString());
                            }else if (StringUtils.isNotEmpty(member.getThirdPayConfig())){
                                card.setThirdConfig(member.getThirdPayConfig());
                            }
                            if(map.containsKey("bankCardNo")&&!"".equals(map.get("bankCardNo")) && map.get("bankCardNo") !=null){
                                card.setCardNum(map.get("bankCardNo"));
                            }
                            if(map.containsKey("bankCode")&&!"".equals(map.get("bankCode")) && map.get("bankCode") !=null){
                                card.setBankId(map.get("bankCode"));
                            }
                            if(map.containsKey("bankName")&&!"".equals(map.get("bankName"))&& map.get("bankName") !=null){
                                card.setBankname(map.get("bankName"));
                            }

							if(map.containsKey("bankType")&&!"".equals(map.get("bankType")) && map.get("bankType") !=null){
								card.setAccounttype(map.get("bankType"));
							}
							card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
							card.setUsername(member.getTruename());
							card.setAccountname(member.getTruename());
							if (map.containsKey("platformUserNo") && StringUtils.isNotEmpty(map.get("platformUserNo"))){
								card.setUserFlg(map.get("platformUserNo"));
							}
							System.out.println("绑卡结束，卡信息："+card.toString());
							webBankcardService.save(card);
						}
					}else {
						ret[0]=Constants.CODE_FAILED;
						ret[1]="用户不存在";
					}
				}else {
					ret[0]=Constants.CODE_FAILED;
					ret[1]="用户id不存在";
				}
			}else{
				card=webBankcardService.getByCardNumAndState(map.get("bankCode"),"bindCard_status_success");
				if(card!=null){
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
					webBankcardService.merge(card);
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="绑定银行卡业务数据处理成功";
					logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
					logger.info("失败原因：card="+card+"; 描述："+ret[1]);
				}
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：card="+card+"; 描述："+ret[1]);
		}
		return ret;
	
	}


	/**
	 * 取消绑定银行卡
	 * @param map
	 * @return
	 */
	public String[] cancelBandCard(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String custmemberId=map.get("custermemberId");
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if(!"".equals(requestNo)){
			card=webBankcardService.getByRequestNo(requestNo);
		}
		if(card!=null){
			synchronized(requestNo){
				if("".equals(map.get("bankstatus"))){
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
				}else{
					card.setBindCardStatus(map.get("bankstatus"));
				}
				webBankcardService.merge(card);
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="绑定银行卡业务数据处理成功";
				logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：card="+card+"; 描述："+ret[1]);
		}
		return ret;
	
	}
	/**
	 * 第三方支付充值接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 *  map.get("custermemberId")   充值的第三方支付的用户id
	 *  map.get("requestNo");  交易请求流水号
	 *  map.get("custmerType");  用户类型
	 *  map.get("dealRecordStatus"); 交易状态
	 * @return
	 */
	@Override
	public String[] recharge(Map<String, String> map) {
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		System.out.println(requestNo+"   requestNorequestNorequestNorequestNorequestNo");
		BpCustMember mem = null;
		ObAccountDealInfo dealinfo=null;
		if(!"".equals(requestNo)){
			dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_RECHARGE.toString(),  "0");
		}
		if(dealinfo!=null&&dealinfo.getDealRecordStatus().compareTo(ObAccountDealInfo.DEAL_STATUS_1)==0||
				dealinfo.getDealRecordStatus().compareTo(ObAccountDealInfo.DEAL_STATUS_3)==0||
				dealinfo.getDealRecordStatus().compareTo(ObAccountDealInfo.DEAL_STATUS_9)==0
				){
			synchronized(requestNo){
				mem=bpCustMemberService.get(dealinfo.getInvestPersonId());
				 Map<String,Object> mapo=new HashMap<String,Object>();
				 mapo.put("investPersonId",dealinfo.getInvestPersonId());//投资人Id
				 mapo.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 mapo.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				//交易金额
				 if (map.containsKey("receivedAmount")&& StringUtils.isNotEmpty(map.get("receivedAmount"))) {
					 //实际到账金额
					 mapo.put("money",new BigDecimal(map.get("receivedAmount")));
				}else {
					 mapo.put("money",dealinfo.getIncomMoney());//交易金额
				}
				 mapo.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 mapo.put("DealInfoId",dealinfo.getId());//交易记录id，没有默认为null
				 mapo.put("recordNumber",requestNo);//交易流水号
				 mapo.put("dealStatus",Short.valueOf(map.get("dealRecordStatus").toString()));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 ret=obAccountDealInfoService.updateAcountInfo(mapo);
				 if(map.get("dealRecordStatus").toString().equals("2")){
					 //派发活动
					 bpActivityManageService.autoDistributeEngine("4", dealinfo.getInvestPersonId().toString(),dealinfo.getIncomMoney());
					 //派发累计活动
					 bpActivityManageService.autoDistributeEngine("7", dealinfo.getInvestPersonId().toString(),null);
					 
					//提现成功发送短信、邮件、站内信。
					 BpCustMember bpCustMember=bpCustMemberService.get(dealinfo.getInvestPersonId());
					Map<String, String> mapMessage = new HashMap<String, String>();
					mapMessage.put("key", "sms_recharge");
					mapMessage.put("userId",bpCustMember.getId().toString());
					mapMessage.put("${name}", bpCustMember.getLoginname());
					mapMessage.put("${code}", dealinfo.getIncomMoney().setScale(2).toString());
					//String result =  sendMesService.sendSmsEmailMessage(mapMessage);
					// smsSendUtil.sms_recharge(bpCustMember.getTelphone(), bpCustMember.getTruename(), dealinfo.getIncomMoney().setScale(2).toString());
				 }
				 //如果有银行卡绑定银行卡
				 if(map.containsKey("bankCode")){
					WebBankcard bankcard = webBankcardService.getByCardNum(map.get("bankCode").toString());
					if(bankcard==null){
						WebBankcard card = new WebBankcard();
						card.setCustomerId(mem.getId());
						card.setCustomerType(Short.valueOf("0"));
						card.setUsername(mem.getTruename());
						card.setAccountname(mem.getTruename());
						card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
						card.setRequestNo(requestNo);
						card.setUserFlg(mem.getThirdPayFlagId());
						card.setCardNum(map.get("bankCode").toString());
						card.setBankId(map.get("bankCardNumber").toString());
//						card.setBankname("");
						card.setCustomerId(mem.getId());
						card.setThirdConfig(mem.getThirdPayConfig());
						card.setUserFlg(mem.getThirdPayFlagId());
						webBankcardService.save(card);
					}
				 }
				 logger.info("ret[0]"+ret[0]+";ret[1]="+ret[1]+";ObAccountDealInfo.id="+dealinfo.getId()+";ObAccountDealInfo.dealRecordStatus="+dealinfo.getDealRecordStatus());
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="充值业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：ObAccountDealInfo="+dealinfo+"; 描述："+ret[1]);
		}
		return ret;
	}
	
	/**
	 * 第三方支付取现接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 *  map.get("custermemberId")   充值的第三方支付的用户id
	 *  map.get("requestNo");  交易请求流水号
	 *  map.get("custmerType");  用户类型
	 *  map.get("dealRecordStatus"); 交易状态
	 * @return
	 */
	@Override
	public String[] withDraw(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String custmemberId=map.get("custermemberId");
		String requestNo=map.get("requestNo");
		String dealRecordStatus = map.get("dealRecordStatus");
		String feeUser="0";
		if(map.containsKey("feeUser")){
			 feeUser = map.get("feeUser");
		}
		String feePlatform = map.get("feePlatform");
		ObAccountDealInfo dealinfo=null;
		if(!"".equals(requestNo)){
			dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_ENCHASHMENT.toString(),"0");//map.get("custmerType")
		}
		if(dealinfo!=null&&dealinfo.getDealRecordStatus().compareTo(ObAccountDealInfo.DEAL_STATUS_1)==0||dealinfo.getDealRecordStatus().compareTo(ObAccountDealInfo.DEAL_STATUS_7)==0){
			synchronized(requestNo){
				Map<String,Object> mapo=new HashMap<String,Object>();
				 mapo.put("investPersonId",dealinfo.getInvestPersonId());//投资人Id
				 mapo.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				 mapo.put("transferType",ObAccountDealInfo.T_ENCHASHMENT);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				 if (map.containsKey("receivedAmount") && StringUtils.isNotEmpty(map.get("receivedAmount"))) {
					 mapo.put("money",new BigDecimal(map.get("receivedAmount")));//交易金额
				}else {
					mapo.put("money",dealinfo.getPayMoney());//交易金额
				}
				 
				 mapo.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
				 mapo.put("DealInfoId",dealinfo.getId());//交易记录id，没有默认为null
				 mapo.put("recordNumber",requestNo);//交易流水号
				 mapo.put("dealStatus",Short.valueOf(dealRecordStatus));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
				 ret=obAccountDealInfoService.updateAcountInfo(mapo);
				
				 /**
				  * 增加用户手续费用流水
				  */
				 String orderNum =ContextUtil.createRuestNumber();
				 if(new BigDecimal(feeUser).compareTo(new BigDecimal(0))>0){
					 Map<String,Object> mapOF=new HashMap<String,Object>();
					 mapOF.put("investPersonId",dealinfo.getInvestPersonId());//投资人Id（必填）
					 mapOF.put("investPersonType",dealinfo.getInvestPersonType());//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
					 mapOF.put("transferType",ObAccountDealInfo.T_LOANINCOME);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
					 mapOF.put("money",new BigDecimal(feeUser));//交易金额	（必填）
					 mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					 mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					 mapOF.put("recordNumber",orderNum);//交易流水号	（必填）
					 mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
					 String[] poundageRet =obAccountDealInfoService.operateAcountInfo(mapOF);

					 Map<String,Object> mapOI=new HashMap<String,Object>();
					 mapOI.put("investPersonId",Long.valueOf(7164));//投资人Id（必填）
					 mapOI.put("investPersonType",dealinfo.getInvestPersonType());//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
					 mapOI.put("transferType",ObAccountDealInfo.T_LOANINCOMEFEE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
					 mapOI.put("money",new BigDecimal(feeUser));//交易金额	（必填）
					 mapOI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					 mapOI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					 mapOI.put("recordNumber",orderNum);//交易流水号	（必填）
					 mapOI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
					 obAccountDealInfoService.operateAcountInfo(mapOI);

					 logger.info("poundageRet[0]"+poundageRet[0]+";poundageRet[1]="+poundageRet[1]+";feeUser="+feeUser+";关联取现记录：ObAccountDealInfo.id="+dealinfo.getId());
				 }
				
				//提现成功发送短信、邮件、站内信。
				 BpCustMember bpCustMember=bpCustMemberService.get(dealinfo.getInvestPersonId());
				Map<String, String> mapMessage = new HashMap<String, String>();
				mapMessage.put("key", "sms_withdrawals");
				mapMessage.put("userId",bpCustMember.getId().toString());
				mapMessage.put("${name}", bpCustMember.getLoginname());
				mapMessage.put("${code}", dealinfo.getPayMoney().setScale(2).toString());
				//String result =  sendMesService.sendSmsEmailMessage(mapMessage);
				
				//smsSendUtil.sms_withdrawals(bpCustMember.getTelphone(), bpCustMember.getTruename(), dealinfo.getPayMoney().setScale(2).toString());
				 
				logger.info("ret[0]"+ret[0]+";ret[1]="+ret[1]+";ObAccountDealInfo.id="+dealinfo.getId()+";ObAccountDealInfo.dealRecordStatus="+dealinfo.getDealRecordStatus());
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="取现业务业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：ObAccountDealInfo="+dealinfo+"; 描述："+ret[1]);
		}
		return ret;
	}
	/**
	 * 取现退回
	 */
	@Override
	public String[] withDrawReturn(Map<String, String> map) {
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		String dealRecordStatus = map.get("dealRecordStatus");
		String feeUser="0";
		if(map.containsKey("feeUser")){
			 feeUser = map.get("feeUser");
		}
		ObAccountDealInfo dealinfo=null;
		if(!"".equals(requestNo)){
			dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_ENCHASHMENT.toString(),"0");
		}
		Map<String,Object> mapo=new HashMap<String,Object>();
		mapo.put("investPersonId",dealinfo.getInvestPersonId());//投资人Id
		mapo.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
		mapo.put("transferType",ObAccountDealInfo.T_RECHARGE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
		mapo.put("money",dealinfo.getPayMoney().add(new BigDecimal(feeUser)));//交易金额
		mapo.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
		mapo.put("DealInfoId",dealinfo.getId());//交易记录id，没有默认为null
		mapo.put("recordNumber",requestNo+"_T");//交易流水号
		mapo.put("dealStatus",Short.valueOf(dealRecordStatus));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
		ret=obAccountDealInfoService.operateAcountInfo(mapo);
		return ret;
	}
	

	/**
	 * 第三方支付投标（冻结账户金额）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 *  map.get("custermemberId")   第三方支付的用户id
	 *  map.get("requestNo");  交易请求流水号
	 *  map.get("custmerType");  用户类型
	 *  map.get("dealRecordStatus");交易状态
	 * @return
	 */
	public String[] biding(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		PlBidInfo info=null;
		if(!"".equals(requestNo)){
			info=plBidInfoService.getOrderNumber(requestNo);
		}
		
		if(info!=null&&info.getState().compareTo(Short.valueOf("0"))==0){
				if(map.get("dealRecordStatus").equals("2")||map.get("dealRecordStatus").equals("7")){
					ObAccountDealInfo dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_INVEST.toString(), "0");
					if(dealinfo!=null){
						ret[0]=Constants.CODE_FAILED;
						ret[1]="投标业务数据处理失败:已经处理过了";
						logger.info("失败原因：ObAccountDealInfo="+dealinfo+"; 描述："+ret[1]);
					}else{
						BpCustMember bpCustMember=null;
						if(info.getUserId()!=null){
							 bpCustMember=bpCustMemberService.get(info.getUserId());
						}else{
							 bpCustMember=bpCustMemberService.getByLoginName(info.getUserName());
						}
						Map<String,Object> mapOF=new HashMap<String,Object>();
						mapOF.put("investPersonId",info.getUserId());//投资人Id（必填）
						mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapOF.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapOF.put("money",info.getUserMoney());//交易金额	（必填）			
						mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("recordNumber",requestNo);//交易流水号	（必填）
						mapOF.put("dealStatus",map.get("dealRecordStatus"));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
						logger.info("投标保存系统账户交易记录：rett[0]="+rett[0]+"; rett[1]："+rett[1]+";requestNo="+requestNo);
						int index=(int) (info.getBidId()%bidmap.size());
						synchronized(bidmap.get(index)){
							System.out.println("进入是否流标判断操作-----------------");
							//判断是否超投标
							int flag=plBidPlanService.getBidSumMoney(info.getBidId(),info.getUserMoney());
							if(flag==1){
								System.out.println("已满标,准备流标-----------------");
								info.setState(PlBidInfo.TYPE5);
								plBidInfoService.merge(info);
									/**(17)取消投标
									 * Map<String,Object> map  第三方支付取消投标需要的map参数
									 * map.get("platformUserNo").toString() 第三方支付账号
									 * map.get("requestNo").toString()交易流水号
									 * map.get("bidrequestNo").toString()之前投标流水号
									 * @return
									 */
								logger.info("准备流标数据--------------");
									CommonRequst cq=new CommonRequst();
									Date date = new Date();
									PlBidPlan plan = plBidPlanService.get(info.getBidId());
									BpCustMember loanerMember = bpCustMemberService.getByLoginName(plan.getReceiverP2PAccountNumber());
									SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
									String orderNum=ContextUtil.createRuestNumber();//流水号d
									cq.setLoanNoList(info.getPreAuthorizationNum());//投标时第三方返回的流水号
					    			cq.setContractNo(info.getPreAuthorizationNum()!=null?info.getPreAuthorizationNum():"");
					    			cq.setTransactionTime(date);
					    			cq.setOrderDate(sdf.format(date));
					    			cq.setOrdId(info.getOrderNo());//汇付的投标撤销接口(投标的流水号)
					    			cq.setLoaner_thirdPayflagId(loanerMember.getThirdPayFlagId());//入账账户  富友
					    			cq.setTrxId(info.getTrxId());
					    			cq.setFreezeOrdId(info.getFreezeTrxId());
					    			if(bpCustMember.getCustomerType()!=null&&!"".equals(bpCustMember.getCustomerType())&&bpCustMember.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){
					    				cq.setAccountType(1);//企业	
					    			}else{
					    				cq.setAccountType(0);//个人
					    			}
									cq.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());//用户第三方标识
									cq.setQueryRequsetNo(info.getOrderNo());//之前投标的请求流水号
									cq.setRequsetNo(orderNum);//请求流水号
									cq.setRepaymemntList(null);
									cq.setTransactionTime(date);
									cq.setBidType(cq.HRY_BID);
									cq.setBidId(info.getBidId().toString());
									cq.setAmount(info.getUserMoney());
					    			cq.setBussinessType(ThirdPayConstants.BT_CANCELBID);//业务类型 取消投标
					    			cq.setTransferName(ThirdPayConstants.TN_CANCELBID);//交易名称 取消投标
					    			CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
					    			logger.info("流标返回状态---------------"+cr.getResponsecode());
									if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
										info.setState(PlBidInfo.TYPE3);
										plBidInfoService.merge(info);
										Map<String,Object> mapup=new HashMap<String,Object>();
										mapup.put("investPersonId",info.getUserId());//投资人Id
										mapup.put("investPersonType",Short.valueOf(map.get("custmerType").toString()));//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
										mapup.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
										mapup.put("money",info.getUserMoney());//交易金额
										mapup.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
										mapup.put("DealInfoId",null);//交易记录id，没有默认为null
										mapup.put("recordNumber",requestNo);//交易流水号
										mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。
										obAccountDealInfoService.updateAcountInfo(mapup);
										logger.info("成功流标：ObAccountDealInfo="+info+"; 描述："+ret[1]);
										ret[0]="successOther";
										ret[1]="投标失败：已满标,第三方冻结资金已经归还";
									}else{
										info.setState(PlBidInfo.TYPE7);
										plBidInfoService.save(info);
										logger.info("失败原因：ObAccountDealInfo="+info+"; 描述："+ret[1]);
										ret[0]="successOther";
										ret[1]="投标失败：第三方资金仍冻结,请联系管理员";
									}
								
							}else{
								List<InvestPersonInfo> investPersonInfo =investPersonInfoService.getByRequestNumber(requestNo);
								if(investPersonInfo==null||investPersonInfo.size()==0){
									//投资人列表到erp 直接保存
									InvestSaveMethod invest=new InvestSaveMethod(info.getId().toString(),requestNo);
									//线程不知道用来干什么的
									Thread t=new Thread(invest);
									t.setPriority(10);
									t.start();
									try {
										t.join();
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									plBidPlanService.updateStatByOrderNomber(info.getBidId(), info.getUserMoney(),requestNo);
									info.setState(PlBidInfo.TYPE1);
									info.setBidtime(new Date());
									/*if(map.containsKey("loanNo")&&map.get("loanNo")!=null&&!"".equals(map.get("loanNo").toString())){
										info.setPreAuthorizationNum(map.get("loanNo").toString());
									}else if(map.containsKey("requestNo")){
										info.setPreAuthorizationNum(map.get("requestNo").toString());
									}
									if(map.containsKey("contract_no")){//富友的预授权合同号
										info.setPreAuthorizationNum(map.get("contract_no").toString());
									}
									if(map.containsKey("freezeTrx")){
										info.setFreezeTrxId(map.get("freezeTrx").toString());
									}
									if(map.containsKey("trxId")){
										info.setTrxId(map.get("trxId").toString());
									}*/
									plBidInfoService.save(info);
									//判断是否使用了优惠券  设置优惠券状态
									if(info.getCouponId()!=null&&!info.getCouponId().equals("")){
										PlBidPlan plan = plBidPlanService.get(info.getBidId());
										BpCoupons coupon = bpCouponsService.get(info.getCouponId());
										if(plan!=null&&coupon!=null){
											coupon.setCouponStatus(BpCoupons.COUPONSTATUS1);
											bpCouponsService.save(coupon);
										}
									}
									
									//投标成功，发送短信提醒
									try {
										PlBidPlan plan = plBidPlanService.get(info.getBidId());
										//投标成功发送短信、邮件、站内信。
										Map<String, String> mapMessage = new HashMap<String, String>();
										if(plan.getRaiseRate() != null && plan.getRaiseRate().compareTo(BigDecimal.ZERO) > 0){
										mapMessage.put("key", "sms_BidByType");
										mapMessage.put("${money}", plan.getRaiseRate().toString());
										}else{
										mapMessage.put("key", "sms_Bid");
										}
										mapMessage.put("userId",bpCustMember.getId().toString());
										mapMessage.put("${projNumber}", plan.getBidProNumber());
										mapMessage.put("${projName}", plan.getBidProName());
										mapMessage.put("${code}", info.getUserMoney().setScale(2).toString());
										String result =  sendMesService.sendSmsEmailMessage(mapMessage);
										//smsSendUtil.sms_Bid(bpCustMember.getTelphone(), info.getUserMoney().toString(), plan.getBidProName());
									} catch (Exception e) {
										e.printStackTrace();
									}
									logger.info("保存投标记录情况：PlBidInfo="+info+"; PlBidInfo.id："+info.getId()+";PlBidInfo.status="+info.getState());
								}
							}
						}
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]=info.getUserMoney().toString();
						logger.info("PlBidInfo.id="+info.getId()+";PlBidInfo.status="+info.getState()+";PlBidInfo.orderNo="+info.getOrderNo());
					}
				}else{
					info.setState(PlBidInfo.TYPE4);
					info.setBidtime(new Date());
					System.out.println("=======修改状态");
					plBidInfoService.save(info);
					ret[0]=Constants.CODE_FAILED;
					ret[1]="投标失败";
					logger.info("PlBidInfo.id="+info.getId()+";PlBidInfo.status="+info.getState()+";PlBidInfo.orderNo="+info.getOrderNo());
				}
			
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="取现业务业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：PlBidInfo="+info+"; 描述："+ret[1]);
		}
		return ret;
	}
	/**
	 * 第三方支付取消投标（解冻冻结金额）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	@Override
	public String[] cancelbiding(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 第三方支付自动投标授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * Map<String ,String> map=new HashMap<String,String>();
	 * map.gett("custermemberId");
	 * map.get("custmerType");
	 * map.get("requestNo");
	 * @return
	 */
	@Override
	public String[] bidingAuthorization(Map<String, String> map) {
		// TODO Auto-generated method stub
		PlBidAuto  bidAuto=null;
		BpCustMember bpCustMember=null;
		String custermemberId = map.containsKey("custermemberId")?map.get("custermemberId"):"";
		String requestNo=map.containsKey("requestNo")?map.get("requestNo"):"";
		String ThirdPayConfigId=map.containsKey("ThirdPayConfigId")?map.get("requestNo"):"";
		/*System.out.println("a======"+a);
		if(null !=map.get("custermemberId")&&(map.get("custermemberId").equals("")||""==map.get("custermemberId").toString())){
			if(!map.get("ThirdPayConfigId").equals("") && null != map.get("ThirdPayConfigId")){
				bidAuto= plBidAutoService.getByThirdPayConfigId(map.get("ThirdPayConfigId").toString());
				bpCustMember=bpCustMemberService.getMemberByPIdAndFlag(map.get("ThirdPayConfigId").toString(),null);
			}
		}else{
			bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId").toString()));
		}*/
		if(!"".equals(custermemberId)){//判断是否有授权人Id
			bpCustMember=bpCustMemberService.get(Long.valueOf(custermemberId));
		}
		if(!"".equals(ThirdPayConfigId)){//判断是否有第三方账号
			bpCustMember=bpCustMemberService.getMemberByPIdAndFlag(map.get("ThirdPayConfigId").toString(),null);
		}
		if(!"".equals(requestNo)){//检查一下是否有授权账号
			bidAuto= plBidAutoService.getByRequestNo(requestNo);
		}
		if(bidAuto!=null){
			synchronized(bidAuto.getId()){
				bidAuto.setIsOpen(1);
				bidAuto.setOrderTime(new Date());
				plBidAutoService.save(bidAuto);
			}
			if(bpCustMember ==null){
				bpCustMember=bpCustMemberService.get(bidAuto.getUserID());
			}
		}else{
			if(bpCustMember !=null){
				bidAuto= plBidAutoService.getPlBidAuto(bpCustMember.getId());
				bidAuto.setIsOpen(1);
				bidAuto.setOrderTime(new Date());
				plBidAutoService.save(bidAuto);
			}
		}
		if(bpCustMember !=null){//判断用户是否开通自动投标
			synchronized(bpCustMember.getId()){
				bpCustMember.setTender("0");
				bpCustMemberService.save(bpCustMember);
			}
		}
		return null;
	}
	
	/**
	 * 双乾授权接口回调处理
	 */
	@Override
	public String[] mmmoreLoanAuthorize(Map<String, String> map) {
		BpCustMember mem=bpCustMemberService.get(Long.valueOf(map.get("custermemberId")));
		String authorizeTypeOpen = map.get("AuthorizeTypeOpen").toString();
		String authorizeTypeClose = map.get("AuthorizeTypeClose").toString();
		String bidId = map.containsKey("bidId")?map.get("bidId").toString().trim():"";
		PlBidAuto  bidAuto=null;
		if (mem != null) {
			if (authorizeTypeOpen != null&&!authorizeTypeOpen.equals("")) {// 开启
				String[] str = authorizeTypeOpen.split(",");

				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("1")) {
						mem.setTender("1");
						bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId")));
						bidAuto.setIsOpen(1);
						bidAuto.setOrderTime(new Date());
						plBidAutoService.save(bidAuto);
					}
					if (str[i].equals("2")) {
						mem.setRefund("1");
						if(null != bidId && !"".equals(bidId)){
							PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
							plBidPlan.setAuthorizationStatus(Short.valueOf("1"));
							plBidPlanService.save(plBidPlan);
						}
					}
					if (str[i].equals("3")) {
						mem.setSecondAudit("1");
					}
					bpCustMemberService.merge(mem);
				}
			}
			if (authorizeTypeClose != null&&!authorizeTypeClose.equals("")) {// 关闭
				String[] str = authorizeTypeClose.split(",");
				for (int i = 0; i < str.length; i++) {
					if (str[i].equals("1")) {
						mem.setTender("0");
						bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId")));
						bidAuto.setIsOpen(0);
						plBidAutoService.save(bidAuto);
					}
					if (str[i].equals("2")) {
						mem.setRefund("0");
						if(null != bidId && !"".equals(bidId)){
							PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
							plBidPlan.setAuthorizationStatus(Short.valueOf("0"));
							plBidPlanService.save(plBidPlan);
						}
					}
					if (str[i].equals("3")) {
						mem.setSecondAudit("0");
					}
					bpCustMemberService.merge(mem);
				}
			}
		}
	
		return null;
	}
	
	/**
	 *联动优势授权接口回调处理
	 */
	@Override
	public String[] umpayLoanAuthorize(Map<String, String> map) {
		BpCustMember mem=bpCustMemberService.get(Long.valueOf(map.get("custermemberId")));
		String open = map.get("open").toString();
		String close = map.get("close").toString();
		PlBidAuto  bidAuto=null;
		if (mem != null) {
			if (open != null && !open.equals("")) {//开启
				if(open.equals(ThirdPayConstants.BT_OPENBIDAUTH)){// 自动投标授权
					mem.setTender("1");
					bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId")));
					bidAuto.setIsOpen(1);
					bidAuto.setOrderTime(new Date());
					plBidAutoService.save(bidAuto);
				}else if(open.equals(ThirdPayConstants.BT_OPENPAYAUTH)){//自动还款授权
					mem.setRefund("1");
					String bidId = map.get("bidId");
					if(null != bidId && !"".equals(bidId)){
						PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
						plBidPlan.setAuthorizationStatus(Short.valueOf("1"));
						plBidPlanService.save(plBidPlan);
					}
					
				}
				bpCustMemberService.merge(mem);
			}
			if (close != null && !close.equals("")) {// 关闭
				if(close.equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){// 自动投标授权
					mem.setTender("0");
					bpCustMemberService.merge(mem);
					bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId")));
					bidAuto.setIsOpen(0);
					plBidAutoService.save(bidAuto);
				}else if(close.equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//自动还款授权
					mem.setRefund("0");
					String bidId = map.get("bidId");
					if(null != bidId && !"".equals(bidId)){
						PlBidPlan plBidPlan = plBidPlanService.get(Long.valueOf(bidId));
						plBidPlan.setAuthorizationStatus(Short.valueOf("0"));
						plBidPlanService.save(plBidPlan);
					}
				}
				bpCustMemberService.merge(mem);
			}
		}
	
		return null;
	}

	/**
	 * 第三方支付关闭自动投标授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * Map<String ,String> map=new HashMap<String,String>();
	 * map.gett("custermemberId");
	 * map.get("custmerType");
	 * map.get("requestNo");
	 * @return
	 */
	@Override
	public String[] closeBidingAuthorization(Map<String, String> map) {
		// TODO Auto-generated method stub
		PlBidAuto  bidAuto=null;
		BpCustMember bpCustMember=null;
		if(null !=map.get("custermemberId")&&(map.get("custermemberId").equals("")||""==map.get("custermemberId").toString())){
			if(!map.get("ThirdPayConfigId").equals("") && null != map.get("ThirdPayConfigId")){
				bidAuto= plBidAutoService.getByThirdPayConfigId(map.get("ThirdPayConfigId").toString());
				bpCustMember=bpCustMemberService.getMemberByPIdAndFlag(map.get("ThirdPayConfigId").toString(),null);
			}
		}else{
			bidAuto= plBidAutoService.getPlBidAuto(Long.valueOf(map.get("custermemberId").toString()));
		}
		if(bidAuto!=null){
			synchronized(bidAuto.getId()){
				bidAuto.setIsOpen(0);
				bidAuto.setOrderTime(new Date());
				plBidAutoService.save(bidAuto);
			}
		}
		if(bpCustMember !=null){
			synchronized(bpCustMember.getId()){
				bpCustMember.setTender("1");
				bpCustMemberService.save(bpCustMember);
			}
		}
		return null;
	}
	
	/**
	 * 第三方支付投标放款接口（转账）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	@Override
	public String[] loan(Map<String, String> map) {
		return null;
	}

	/**
	 * 第三方支付自动还款授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");自动还款授权的流水号
	 * @return
	 */
	@Override
	public String[] repaymentAuthorization(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String requestNo =map.get("requestNo").toString(); 
		PlBidPlan plan=null;
		if("".endsWith(requestNo)){
			String planId=map.get("orderNo").toString(); 
			plan=plBidPlanDao.get(Long.valueOf(planId));
			requestNo=plan.getRequestNo();
		}else{
			plan=plBidPlanDao.getByRequestNo(requestNo);
		}
		synchronized(requestNo){
			if(plan!=null){
				plan.setAuthorizationStatus(Short.valueOf("1"));
				plBidPlanDao.save(plan);
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="已经完成了自动还款授权";
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="自动还款授权失败：没有找到需要自动授权的标";
			}
		}
		return ret;
	}
	
	/**
	 * 第三方支付自动还款授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("ThirdPayFlagId");商户号
	 * 
	 * @return
	 */
	@Override
	public String[] rAuthorization(Map<String, String> map) {
		String[] ret=new String[2];
		System.out.println("map.get(thirdPayConfigId)======"+map.get("ThirdPayConfigId"));
		BpCustMember bp=bpCustMemberService.getMemberByPIdAndFlag(map.get("ThirdPayConfigId").toString(),null);
		String msg="";
		if(bp!=null){
			if(map.get("oprateType").equals("cancel")){//表示取消自动还款授权
				bp.setRefund("1");
				bpCustMemberService.save(bp);
				msg="取消授权自动还款,用户的第三方用户号";
			}else{
				bp.setRefund("0");
				bpCustMemberService.save(bp);
				msg="授权自动还款,用户的第三方用户号";
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="没有找到授权自动还款人的信息";
			logger.info("处理数据记录：bp="+bp+"; 描述："+ret[1]+";"+msg+"："+map.get("thirdPayConfigId").toString());
		}
		return ret;
	}
	
	@Resource
	private BpFundProjectService bpFundProjectService;
	
	/**
	 * 第三方支付客户自助还款接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	@Override
	public String[] repayment(Map<String, String> map) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		int i=0;//成功返款的人数
		int j=0;//返款失败的人数
		String payintentPeriod = "";
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] ret=new String[2];
			System.out.println(map.get("requestTime").toString());
			Date date2 =  sdf.parse(map.get("requestTime").toString());
			String bidId = map.get("bidId");
			String requestNo=map.get("requestNo");
			Date intentDate = sdf.parse(map.get("intentDate").toString());
			//查询还款的记录
			List<BpFundIntent> list= bpFundIntentService.getThirdBpFundIntentList(map.get("bidId").toString(),intentDate);
			if(map.containsKey("peridId")){
				payintentPeriod = map.get("peridId").toString();
			}else{
				payintentPeriod = list.get(0).getPayintentPeriod().toString();
			}
			String slEarlyRepaymentId = map.containsKey("slEarlyRepaymentId")?map.get("slEarlyRepaymentId"):"";
			BigDecimal manageMoney = new BigDecimal("0");
			if(bidId!=null&&!"".equals(bidId)&& payintentPeriod!=null&&!"".equals(payintentPeriod)){//根据还款期数和标的的Id计算平台收费金额
				manageMoney = bpFundIntentDao.getManageFee(bidId, payintentPeriod,slEarlyRepaymentId);
				System.out.println("管理费 bidId："+bidId+" slEarlyRepaymentId : " + slEarlyRepaymentId);
			}
			
//			synchronized(requestNo){
				if(list!=null && list.size()>0){
					String orderNoStr="";
					BigDecimal sumMoney = new BigDecimal(0);//本金利息（给投资人的）
					BigDecimal sumMoney2 = new BigDecimal(0);//其他费用（给平台的）
					BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidPlanService.get(list.get(0).getBidPlanId()));
					for(BpFundIntent temp:list){
						
						//bidId=temp.getBidPlanId().toString();
					    //统计借款人给投资人还款总金额
					    if(temp.getFundType()!=null&&(temp.getFundType().equals("loanInterest")
							  ||temp.getFundType().equals("principalRepayment")
							  ||temp.getFundType().equals("interestPenalty"))){
						    //计算借款人还款本息(未对账金额+罚息)
						    sumMoney=sumMoney.add(temp.getIncomeMoney()).add(temp.getAccrualMoney());
					    }
						//只判断本金利息管理咨询费平台服务费（投资人奖励后面处理）
						if(temp.getRequestNo()!=null&&temp.getRequestNo().equals(requestNo)&&(temp.getFundType().equals("principalRepayment")
							||temp.getFundType().equals("loanInterest")||temp.getFundType().equals("consultationMoney")
								||temp.getFundType().equals("serviceMoney")||temp.getFundType().equals("interestPenalty")||temp.getFundType().equals("principalCoupons"))){
							  synchronized(temp.getNotMoney()){
								  if(temp.getNotMoney().compareTo(new BigDecimal(0))>0&&temp.getFactDate()==null){
									  List<InvestPersonInfo> iofos=investPersonInfoService.getByRequestNumber(temp.getOrderNo());
									  //返给投资人的费用
									  if(iofos!=null&&iofos.size()>0){
										  PlBidInfo plbidinfo=plBidInfoService.getByOrdId(temp.getOrderNo());
										  Long custmemId=iofos.get(0).getInvestPersonId();
										  String bidOrderNo=temp.getOrderNo();
										  if(plbidinfo!=null&&plbidinfo.getNewInvestPersonId()!=null){
											  custmemId=plbidinfo.getNewInvestPersonId();
											  bidOrderNo=plbidinfo.getNewOrderNo();
										  }
										  BpCustMember bps=bpCustMemberService.get(custmemId);
										  if(temp.getFundType()!=null&&(temp.getFundType().equals("loanInterest")||temp.getFundType().equals("principalRepayment")||temp.getFundType().equals("interestPenalty"))){//回调只生成本金利息
											  Map<String,Object> mapI=new HashMap<String,Object>();
											  mapI.put("investPersonId",bps.getId());//投资人Id（必填）
											  mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）		 T_PROFIT			 
											  mapI.put("transferType",temp.getFundType().equals("principalRepayment")?ObAccountDealInfo.T_PRINCIALBACK:ObAccountDealInfo.T_PROFIT);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
											  mapI.put("money",temp.getAccrualMoney().compareTo(new BigDecimal(0))>0?temp.getNotMoney().add(temp.getAccrualMoney()):temp.getNotMoney());//交易金额	（必填）			 
											  mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
											  mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
											  mapI.put("recordNumber",requestNo+temp.getFundIntentId()+"I");//交易流水号	（必填）
											  mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
											  obAccountDealInfoService.operateAcountInfo(mapI);
											  	//给借款人统计罚息
											  sumMoney2=temp.getAccrualMoney().compareTo(new BigDecimal(0))>0?sumMoney2.add(temp.getAccrualMoney()):sumMoney2;
										  }else{
											  //其他费用
											  sumMoney2=temp.getAccrualMoney().compareTo(new BigDecimal(0))>0?sumMoney2.add(temp.getNotMoney()).add(temp.getAccrualMoney()):sumMoney2.add(temp.getNotMoney());
										  }
										  temp.setRequestNo(requestNo);
										  temp.setRequestDate(date2);
										  temp.setRequestCount(temp.getRequestCount()!=null?temp.getRequestCount().intValue()+1:1);
										  temp.setNotMoney(new BigDecimal(0));
										  temp.setAfterMoney(temp.getIncomeMoney());
										  temp.setFactDate(new Date());
										  temp.setIsCheck((short)0);
										  temp.setRepaySource(BpFundIntent.REPAYSOURCE1);
										  temp.setLoanerRepayMentStatus(1);//预留字段   标注还款成功  返款失败的情况
//										  bpFundIntentService.update1(temp);
										  bpFundIntentService.merge(temp);
										  bpFundIntentService.flush();
										  bpFundIntentService.save(temp);
										  System.out.println("保存款项表");
									  }
									  logger.info("款项业务已成功处理：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
									  orderNoStr=temp.getFundIntentId()+":"+temp.getFundType()+":"+temp.getOrderNo()+";";
								  }else{
									  logger.info("款项业务已经被处理过：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
								  }
							  }	
						}
					}
					
					//生成借款人的资金明细
					String thirdConfigType = map.containsKey("thirdConfigType") ? map.get("thirdConfigType") : "";;
					BigDecimal alreadyPay = new BigDecimal(0);
					if(bidId!=null && !"".equals(bidId) && payintentPeriod!=null && !"".equals(payintentPeriod)){
						QueryFilter filter = new QueryFilter();
						filter.addFilter("Q_planId_L_EQ",Long.valueOf(bidId));
						filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(payintentPeriod));
						List<PlBidCompensatory> list_comp = plBidCompenstoryService.getAll(filter);
						if(list_comp.size()>0){
							alreadyPay = list_comp.get(0).getBackCompensatoryMoney().add(list_comp.get(0).getBackPunishMoney());
						}
					}
					if(thirdConfigType!=null && !thirdConfigType.equals("")){
						if(sumMoney.compareTo(new BigDecimal(0))>0 && thirdConfigType.equals("loanerFore") || thirdConfigType.equals("loaner")){
							  Map<String,Object> mapL=new HashMap<String,Object>();
							  mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
							  mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							  mapL.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							  mapL.put("money",sumMoney.subtract(alreadyPay));//交易金额	（必填）			 
							  mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							  mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							  mapL.put("recordNumber",requestNo+list.get(0).getFundIntentId()+"L");//交易流水号	（必填）
							  mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							  obAccountDealInfoService.operateAcountInfo(mapL);
						}
						if(manageMoney.compareTo(new BigDecimal(0))>0 && thirdConfigType.equals("loanerFee") || thirdConfigType.equals("loaner")){
							//平台收取的费用
							System.out.println("平台收取的费用："+manageMoney);
							  Map<String,Object> mapL=new HashMap<String,Object>();
							  mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
							  mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							  mapL.put("transferType",ObAccountDealInfo.T_PLATEFORM_ONETIMEFEE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							  mapL.put("money",manageMoney);//交易金额	（必填）			 
							  mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							  mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							  mapL.put("recordNumber",requestNo+list.get(0).getFundIntentId()+"S");//交易流水号
							  mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							  obAccountDealInfoService.operateAcountInfo(mapL);

							Map<String,Object> mapR=new HashMap<String,Object>();
							mapR.put("investPersonId","7164");//投资人Id（必填）
							mapR.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
							mapR.put("transferType",ObAccountDealInfo.T_PLATEFORM_ONETIMEFEE);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapR.put("money",manageMoney);//交易金额	（必填）
							mapR.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapR.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapR.put("recordNumber",requestNo+list.get(0).getFundIntentId()+"S");//交易流水号
							mapR.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							obAccountDealInfoService.operateAcountInfo(mapR);


						}
					}
					//提前还款发送短信
					if(null !=list.get(0).getSlEarlyRepaymentId() && !"".equals(list.get(0).getSlEarlyRepaymentId())){//判断是否是提前还款
						QueryFilter filter=new QueryFilter();
						filter.addFilter("Q_requestNo_S_EQ", requestNo);
						filter.addFilter("Q_slEarlyRepaymentId_L_EQ", list.get(0).getSlEarlyRepaymentId());
						List<BpFundIntent> listfund=bpFundIntentService.getAll(filter);
						//List<BpFundIntent> listfund=bpFundIntentService.getByRequestNo(requestNo);
						//List<InvestPersonInfo> infoList=investPersonInfoService.getOrderNum(listfund.get(0).getOrderNo());
						List<InvestPersonInfo> infoList=investPersonInfoService.getByPlanId(listfund.get(0).getBidPlanId());
						PlBidPlan plBidPlan =plBidPlanService.get(Long.valueOf(Long.valueOf(list.get(0).getBidPlanId())));
						int count=0;
						BpFundProject bpFundProject = null;
						if(null !=infoList && infoList.size()>0){
							for(InvestPersonInfo temp:infoList){
								bpFundProject=bpFundProjectService.getBpFundProject(temp.getProjectId(), Short.valueOf("0"));
								BpCustMember bp = bpCustMemberService.get(temp.getInvestPersonId());
								//统计利息和补偿息
								BigDecimal  lxAndBcx=bpFundIntentService.getEarlyAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('loanInterest','interestPenalty')");
								if(null ==lxAndBcx){
									lxAndBcx=new BigDecimal(0.00);
								}
								//统计本金
								BigDecimal  bj=bpFundIntentService.getEarlyAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('principalRepayment')");
								if(null ==bj){
									bj=new BigDecimal(0.00);
								}
								count++;
								
//								SmsSendUtil  sss = new SmsSendUtil();
								Map<String, String> mapMessage = new HashMap<String, String>();
								mapMessage.put("key", "sms_paymentRemind");
								mapMessage.put("userId",bp.getId().toString());
								mapMessage.put("${name}", bp.getLoginname());
								mapMessage.put("${projName}", plBidPlan.getBidProName());
								mapMessage.put("${payintentPeriod}", payintentPeriod);
								mapMessage.put("${investPrincipal}", bj.toString());
								mapMessage.put("${investInterest}", lxAndBcx.toString());
								String result =  sendMesService.sendSmsEmailMessage(mapMessage);
							}
							System.out.println("进入提前还款次数："+count);
						}
					}else{
						//还款成功发送短信
						List<InvestPersonInfo> infoList=investPersonInfoService.getByPlanId(Long.valueOf(list.get(0).getBidPlanId()));
						PlBidPlan plBidPlan =plBidPlanService.get(Long.valueOf(Long.valueOf(list.get(0).getBidPlanId())));
						if(null !=infoList && infoList.size()>0){
							for(InvestPersonInfo temp:infoList){
								BpCustMember bp = bpCustMemberService.get(temp.getInvestPersonId());
								//统计利息和补偿息
								BigDecimal  lxAndBcx=bpFundIntentService.getAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('loanInterest','interestPenalty')");
								if(null ==lxAndBcx){
									lxAndBcx=new BigDecimal(0.00);
								}
								//统计本金
								BigDecimal  bj=bpFundIntentService.getAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('principalRepayment')");
								if(null ==bj){
									bj=new BigDecimal(0.00);
								}
								Map<String, String> mapMessage = new HashMap<String, String>();
								mapMessage.put("key", "sms_paymentRemind");
								mapMessage.put("userId",bp.getId().toString());
								mapMessage.put("${name}", bp.getLoginname());
								mapMessage.put("${projName}", plBidPlan.getBidProName());
								mapMessage.put("${payintentPeriod}", payintentPeriod);
								mapMessage.put("${investPrincipal}", bj.toString());
								mapMessage.put("${investInterest}", lxAndBcx.toString());
								String result =  sendMesService.sendSmsEmailMessage(mapMessage);
							}
						}
					}
					
					List<BpFundIntent> couponslist= bpFundIntentService.getByRequestNo(requestNo);
					//平台发送奖励金额
					if(couponslist.size()>0){
						checkCouponsIntent(couponslist.get(0).getBidPlanId().toString(),couponslist.get(0).getPayintentPeriod().toString(),requestNo,null);
					}
					if(list.get(0).getSlEarlyRepaymentId()!=null&&!"".equals(list.get(0).getSlEarlyRepaymentId())){
						if(list.get(0).getBidPlanId()!=null&&!"".equals(list.get(0).getBidPlanId())){
							List<BpFundIntent> updateBp=bpFundIntentService.getList(list.get(0).getBidPlanId(), null);
							if(updateBp!=null&&updateBp.size()>0){
								for(BpFundIntent bp:updateBp){
									if((bp.getSlEarlyRepaymentId()==null||"".equals(bp.getSlEarlyRepaymentId()))&&bp.getAfterMoney().compareTo(new BigDecimal(0))==0){
										bp.setIsValid(Short.valueOf("1"));
										bp.setIsCheck(Short.valueOf("1"));
										bpFundIntentService.save(bp);
									}else if(bp.getSlEarlyRepaymentId()!=null&&list.get(0).getSlEarlyRepaymentId().equals(bp.getSlEarlyRepaymentId())){//等于当前的提前还款Id
										System.out.println("bp.fundId="+bp.getFundIntentId()+"  "+"notmoney="+bp.getNotMoney());
										bp.setIsValid(Short.valueOf("0"));
										bp.setIsCheck(Short.valueOf("0"));
										bp.setAfterMoney(bp.getIncomeMoney());
										bp.setNotMoney(new BigDecimal(0));
										bp.setFactDate(new Date());
										bpFundIntentService.save(bp);
									}else if(bp.getSlEarlyRepaymentId()!=null&&!list.get(0).getSlEarlyRepaymentId().equals(bp.getSlEarlyRepaymentId())){//不等于当前提前还款Id
										bp.setIsValid(Short.valueOf("1"));
										bp.setIsCheck(Short.valueOf("1"));
										bpFundIntentService.merge(bp);
										if(bp.getAfterMoney().compareTo(new BigDecimal(0))==0){//表示没有还款对账成功
											bpFundIntentService.remove(bp);
										}
									}else if(bp.getSlEarlyRepaymentId()==null&&bp.getFactDate()!=null){
										bp.setIsValid(Short.valueOf("0"));
										bp.setIsCheck(Short.valueOf("0"));
										bp.setAfterMoney(bp.getIncomeMoney());
										bp.setNotMoney(new BigDecimal(0));
										bp.setFactDate(new Date());
										bpFundIntentService.merge(bp);
									}
								}
							}

						}
					}	
					if(null !=list.get(0).getSlEarlyRepaymentId() && !"".equals(list.get(0).getSlEarlyRepaymentId())){//判断是否是提前还款
						SlEarlyRepaymentRecord slEarlyRepaymentRecord=slEarlyRepaymentRecordService.get(list.get(0).getSlEarlyRepaymentId());
						if(null !=slEarlyRepaymentRecord && !"".equals(slEarlyRepaymentRecord)){
							PlBidPlan plan = plBidPlanService.get(Long.valueOf(bidId));
							plan.setState(10);
							plBidPlanService.merge(plan);
							slEarlyRepaymentRecord.setCheckStatus(5);
							slEarlyRepaymentRecordService.save(slEarlyRepaymentRecord);
						}
					}	
					
					//判断是否是最后一期还款。把标改成已完成状态
					List<BpFundIntent> bpList = bpFundIntentService.getOverList(bidId);
					if(bpList.size()==0){
						PlBidPlan plan = plBidPlanService.get(Long.valueOf(bidId));
						plan.setState(10);
						plBidPlanService.save(plan);
					}
					ret[0]=Constants.CODE_SUCCESS;	
					if(map.containsKey("thirdPayConfig")){
						if(map.get("thirdPayConfig").toString().equals("UMPayConfig")){
							ret[1]="还款业务数据处理完成,返款成功个数i="+i;
						}else{
							ret[1]="还款业务数据处理完成";
						}
					}
					logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";本次处理数据集合(FundIntentId:fundtype:orderNo)："+orderNoStr);
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="还款业务数据处理失败:数据不存在或已处理";
					logger.info("失败原因：requestNo="+requestNo+"; 描述："+ret[1]);
				}
//			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 第三方支付解除银行卡接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	@Override
	public String[] cancelBindBank(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String custmemberId=map.get("custermemberId");
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if(StringUtils.isNotEmpty(requestNo)){
			card=webBankcardService.getByRequestNo(requestNo);
		}else {
			if(StringUtils.isNotEmpty(custmemberId)){
//				List<WebBankcard> list=webBankcardService.getBycusterId(Long.valueOf(custmemberId));
				//修改为查询成功的卡然后取消掉
				List<WebBankcard> list= webBankcardService.getBycustAndState(Long.valueOf(custmemberId),WebBankcard.BINDCARD_STATUS_SUCCESS);
				if(list!=null&&list.size()>0){
					card=list.get(0);
				}
			}
		}
		synchronized(requestNo){
			if(card!=null){
				card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
				webBankcardService.merge(card);
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="取消绑定银行卡业务数据处理成功";
				logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="取消绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
				logger.info("失败原因：card="+card+"; 描述："+ret[1]);
			}
		}
		return ret;
	
	}

	/**
	 * 第三方支付企业解除银行卡接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	@Override
	public String[] cancelBindBankCard(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String custmemberId=map.get("custermemberId");
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if ("pageUrl".equals(map.get("type"))){
			if(StringUtils.isNotEmpty(requestNo)){
				card=webBankcardService.getByRequestNo(requestNo);
			}else {
				if(StringUtils.isNotEmpty(custmemberId)){
//				List<WebBankcard> list=webBankcardService.getBycusterId(Long.valueOf(custmemberId));
					//修改为查询成功的卡然后取消掉
					List<WebBankcard> list= webBankcardService.getBycustAndState(Long.valueOf(custmemberId),WebBankcard.BINDCARD_STATUS_SUCCESS);
					if(list!=null&&list.size()>0){
						card=list.get(0);
					}
				}
			}
			synchronized(requestNo){
				if(card!=null){
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
					webBankcardService.merge(card);
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="取消绑定银行卡业务数据处理成功";
					logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="取消绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
					logger.info("失败原因：card="+card+"; 描述："+ret[1]);
				}
			}

		}else {
			if(StringUtils.isNotEmpty(requestNo)){
				card=webBankcardService.getByRequestNo(requestNo);
			}else {
				if(StringUtils.isNotEmpty(custmemberId)){
//				List<WebBankcard> list=webBankcardService.getBycusterId(Long.valueOf(custmemberId));
					//修改为查询成功的卡然后取消掉
					List<WebBankcard> list= webBankcardService.getBycustAndState(Long.valueOf(custmemberId),WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
					if(list!=null&&list.size()>0){
						card=list.get(0);
					}
				}
			}
			synchronized(requestNo){
				if(card!=null){
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
					webBankcardService.merge(card);
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="取消绑定银行卡业务数据处理成功";
					logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="取消绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
					logger.info("失败原因：card="+card+"; 描述："+ret[1]);
				}
			}
		}
		return ret;

	}
	/**
	 * 易宝第三方支付债权交易接口处理业务数据方法
	 * @param map
	 * @return
	 */
	@Override
	public String[] doObligationDeal(Map<String, String> map) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		PlBidSale plbidSale =plBidSaleService.getByNewOrderNo(requestNo);
		if(plbidSale!=null){
			PlBidInfo plbidinfo=plBidInfoService.get(plbidSale.getBidInfoID());
			if(plbidSale.getSaleStatus().equals(Short.valueOf("2"))){
				ret[0]=Constants.CODE_FAILED;
				ret[1]="债权交易业务数据处理失败:数据不存在或者已经处理过了";
				logger.info("失败原因：交易流水号requestNo="+requestNo+";挂牌交易标号saleId="+plbidSale.getId()+"; 描述："+ret[1]);
			}else{
				if(map.get("dealRecordStatus").toString().equals("2")){						
					plbidinfo.setNewInvestPersonId(plbidSale.getInCustID());
					plbidinfo.setNewInvestPersonName(plbidSale.getInCustName());
					plbidinfo.setNewOrderNo(plbidSale.getNewOrderNo());
					if (StringUtils.isNotEmpty(map.get("orderDate"))) {
						plbidinfo.setNewOrderDate(map.get("orderDate"));
					}else {
						plbidinfo.setNewOrderDate(sdf.format(new Date()));
					}
					plBidInfoService.save(plbidinfo);
					plbidSale.setSaleStatus(Short.valueOf("4"));
					plbidSale.setPreTransferFeeStatus(Short.valueOf("1"));
					plbidSale.setSaleSuccessTime(new Date());
					plBidSaleService.save(plbidSale);
					ObAccountDealInfo info=obAccountDealInfoService.getByOrderNumber(requestNo, null, ObAccountDealInfo.T_TRANSFER, "0");
					if(info!=null){
						ret[0]=Constants.CODE_FAILED;
						ret[1]="已经生成了系统账户交易明细";
						logger.info("失败原因：交易流水号requestNo="+requestNo+";挂牌交易标号saleId="+plbidSale.getId()+"; 描述："+ret[1]);
					}else{
						//购买债权人的系统账户明细
						BpCustMember bps=bpCustMemberService.get(plbidSale.getInCustID());
						Map<String,Object> mapI=new HashMap<String,Object>();
						mapI.put("investPersonId",bps.getId());//投资人Id（必填）
						mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapI.put("transferType",ObAccountDealInfo.T_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapI.put("money",plbidSale.getSumMoney());//交易金额	（必填）			 
						mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapI.put("recordNumber",plbidSale.getNewOrderNo());//交易流水号	（必填）
						mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						System.out.println("购买债权人系统账户交易记录dealStatus=="+mapI.get("dealStatus"));
						obAccountDealInfoService.operateAcountInfo(mapI);
						//出让债权人的系统账户明细
						BpCustMember bpso=bpCustMemberService.get(plbidSale.getOutCustID());
						Map<String,Object> mapIo=new HashMap<String,Object>();
						if(map.get("thirdPayConfig")==null||!map.get("thirdPayConfig").toString().equals("umpayConfig")){
							mapIo.put("investPersonId",bpso.getId());//投资人Id（必填）
							mapIo.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapIo.put("transferType",ObAccountDealInfo.T_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapIo.put("money",plbidSale.getSumMoney());//交易金额	（必填）			 
							mapIo.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapIo.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapIo.put("recordNumber",plbidSale.getNewOrderNo()+"-O");//交易流水号	（必填）
							mapIo.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							System.out.println("出让债权人系统账户交易记录dealStatus=="+mapI.get("dealStatus"));
							obAccountDealInfoService.operateAcountInfo(mapIo);
						}

						if (map.containsKey("feeType") && StringUtils.isNotEmpty(map.get("feeType"))) {
							BigDecimal fee = BigDecimal.ZERO;
							String feeType = map.get("feeType");
							if (map.containsKey("fee") && StringUtils.isNotEmpty(map.get("fee"))) {
								fee = new BigDecimal(map.get("fee"));
								if (fee.compareTo(BigDecimal.ZERO) != 0) {
									String orderNos = ContextUtil.createRuestNumber();
									Map<String,Object> mapF=new HashMap<String,Object>();
									if ("1".equals(feeType)) {//转让人出
										mapF.put("investPersonId",bpso.getId());//投资人Id（必填）
									}else {//承接人出
										mapF.put("investPersonId",bps.getId());//投资人Id（必填）
									}
									mapF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
									mapF.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
									mapF.put("money",fee);//交易金额	（必填）
									mapF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapF.put("recordNumber",orderNos);//交易流水号	（必填）
									mapF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
									obAccountDealInfoService.operateAcountInfo(mapF);

									Map<String,Object> mapS=new HashMap<String,Object>();
									mapS.put("investPersonId","7164");//投资人Id（必填）
									mapS.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）
									mapS.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
									mapS.put("money",fee);//交易金额	（必填）
									mapS.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapS.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapS.put("recordNumber",orderNos+"_1");//交易流水号	（必填）
									mapS.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
									obAccountDealInfoService.operateAcountInfo(mapS);
								}
							}

						}
						//判断是否使用了优惠券，把优惠券生成的台账设置为无效
						if(plbidinfo.getCouponId()!=null&&!plbidinfo.getCouponId().equals("")){
							//普通加息系统配置是否无效 0=有效1=无效
							String isAddRate = configMap.get("isAddRate").toString();
							List<BpFundIntent> bp = bpFundIntentService.getCouponsIntent(plbidinfo.getBidId().toString(), null, plbidinfo.getOrderNo(), isAddRate);
							for(BpFundIntent fund:bp){
								fund.setIsValid(Short.valueOf("1"));
								fund.setIsCheck(Short.valueOf("1"));
								bpFundIntentService.save(fund);
							}
						}
						
						//债权出让成功，通知短信
						PlBidPlan plBidPlan=plBidPlanService.get(plbidinfo.getBidId());
						Map<String, String> mapMessage = new HashMap<String, String>();
						mapMessage.put("key", "sms_sale");
						mapMessage.put("userId",bpso.getId().toString());
						mapMessage.put("${name}", bpso.getLoginname());
						mapMessage.put("${projNumber}", plBidPlan.getBidProNumber());
						mapMessage.put("${projName}", plBidPlan.getBidProName());
						mapMessage.put("${code}", plbidSale.getSumMoney().setScale(2).toString());
						//String result =  sendMesService.sendSmsEmailMessage(mapMessage);
						//债权购买成功，通知信息
						Map<String, String> mapMessage1 = new HashMap<String, String>();
						mapMessage1.put("key", "sms_buyPlbidSale");
						mapMessage1.put("userId",bps.getId().toString());
						mapMessage1.put("${name}", bps.getLoginname());
						mapMessage1.put("${projNumber}", plBidPlan.getBidProNumber());
						mapMessage1.put("${projName}", plBidPlan.getBidProName());
						mapMessage1.put("${code}", plbidSale.getSumMoney().setScale(2).toString());
						//String result1 =  sendMesService.sendSmsEmailMessage(mapMessage1);
						
						//smsSendUtil.sms_sale(bpso.getTelphone(), bpso.getTruename(), plBidPlan.getBidProName());
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="处理成功";
					}
				}else if(map.get("dealRecordStatus").toString().equals("0")){
					plbidSale.setSaleStatus(Short.valueOf("1"));
					plbidSale.setSaleSuccessTime(null);
					plbidSale.setInCustID(null);
					plbidSale.setSaleMoney(null);
					plbidSale.setSumMoney(null);
					plbidSale.setTransferFee(null);
					plbidSale.setNewOrderNo(null);
					plBidSaleService.save(plbidSale);
					if(plbidinfo!=null){
						plbidinfo.setNewInvestPersonId(null);
						plbidinfo.setNewInvestPersonName(null);
						plbidinfo.setNewOrderNo(null);
						plBidInfoService.save(plbidinfo);
					}
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="未知状态，暂无处理方式";
					logger.info("失败原因：交易流水号requestNo="+requestNo+";挂牌交易标号saleId="+plbidSale.getId()+"; 描述："+ret[1]);
				}
		   }
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="取消绑定银行卡业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：交易流水号requestNo="+requestNo+"; 描述："+ret[1]);
		}
		return ret;
	}
	/**
	 * 易宝第三方支付通用转账挂牌业务处理方法
	 * @param map
	 * @return
	 */
	@Override
	public String[] doObligationPublish(Map<String, String> map) {
		try{
			// TODO Auto-generated method stub
			String[] ret=new String[2];
			String requestNo=map.get("requestNo");
			PlBidSale plbidSale =plBidSaleService.getByPreTransferRequestNo(requestNo);
			if(plbidSale!=null){
				if(plbidSale.getSaleStatus().equals(Short.valueOf("1"))){
					ret[0]=Constants.CODE_FAILED;
					ret[1]="债权交易业务数据处理失败:数据不存在或者已经挂牌了";
					logger.info("失败原因：交易流水号requestNo="+requestNo+";挂牌交易标号saleId="+plbidSale.getId()+"; 描述："+ret[1]);
				}else{
					if(map.get("dealRecordStatus").toString().equals("2")||map.get("dealRecordStatus").toString().equals("7")){//表示挂牌成功，服务费已经收取了
						if(map.get("dealRecordStatus").toString().equals("2")){
							plbidSale.setPreTransferFeeStatus(Short.valueOf("1"));
						}
						plbidSale.setSaleStatus(Short.valueOf("1"));
						plbidSale.setSaleStartTime(new Date());
						if(map.containsKey("loanNo")){
							plbidSale.setPreAuthorizationNum(map.get("loanNo").toString());
						}
						plBidSaleService.save(plbidSale);
						//出让债权人的挂牌手续费的系统账户明细
						BpCustMember bps=bpCustMemberService.get(plbidSale.getOutCustID());
						Map<String,Object> mapI=new HashMap<String,Object>();
						mapI.put("investPersonId",bps.getId());//投资人Id（必填）
						mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapI.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapI.put("money",plbidSale.getPreTransferFee());//交易金额	（必填）			 
						mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapI.put("recordNumber",requestNo);//交易流水号	（必填）
						mapI.put("dealStatus",Short.valueOf(map.get("dealRecordStatus").toString()));//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(mapI);
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="处理成功";
					}else if(map.get("dealRecordStatus").toString().equals("0")){
						if(plbidSale.getSaleStatus().equals(Short.valueOf("0"))){
							ObAccountDealInfo info=obAccountDealInfoService.getByOrderNumber(requestNo, null, ObAccountDealInfo.T_TRANSFER, "0");
							if(info!=null){
								obAccountDealInfoService.remove(info);
							}
							plBidSaleService.remove(plbidSale);
						}
						
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="处理成功";
					}else{
						ret[0]=Constants.CODE_FAILED;
						ret[1]="未知状态，暂无处理方式";
						logger.info("失败原因：交易流水号requestNo="+requestNo+";挂牌交易标号saleId="+plbidSale.getId()+"; 描述："+ret[1]);
					}
			   }
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="业务数据处理失败:数据不存在或者已经处理过了";
				logger.info("失败原因：交易流水号requestNo="+requestNo+"; 描述："+ret[1]);
			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 取消挂牌交易
	 */
	@Override
	public String[] cancelPlbidSale(Map<String, String> maps) {
		
		PlBidSale orgPlBidSale=plBidSaleService.get(Long.valueOf(maps.get("bidId").toString()));
		orgPlBidSale.setSaleStatus(Short.valueOf("9"));
		orgPlBidSale.setSaleCloseTime(new Date());
		plBidSaleService.save(orgPlBidSale);
		//getTransferingList(mem);
		Map<String,Object> mapI=new HashMap<String,Object>();
		mapI.put("investPersonId",orgPlBidSale.getOldCustID());//投资人Id（必填）
		mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
		mapI.put("transferType",ObAccountDealInfo.T_PLATEFORM_OBLIGATIONDEAL);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
		mapI.put("money",orgPlBidSale.getPreTransferFee());//交易金额	（必填）			 
		mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
		mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
		mapI.put("recordNumber",orgPlBidSale.getPreTransferFeeRequestNo());//交易流水号	（必填）
		mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
		System.out.println("购买债权人系统账户交易记录dealStatus=="+mapI.get("dealStatus"));
		obAccountDealInfoService.updateAcountInfo(mapI);
		return null;
	}

	/**
	 * 理财计划购买业务处理方法
	 */
	@Override
	public String[] bidMoneyPlan(Map<String, String> map) {
		// TODO Auto-generated method stub
		try{
			// TODO Auto-generated method stub
			String[] ret=new String[3];
			String requestNo=map.get("requestNo");
			PlManageMoneyPlanBuyinfo info=null;
			if(!"".equals(requestNo)){//找到对应的理财计划（进行业务数据处理）
				info=plManageMoneyPlanBuyinfoService.getOrderNumber(requestNo);
			}
			if(info!=null&&info.getState().compareTo(Short.valueOf("0"))==0){
				if(map.get("dealRecordStatus").equals("2")){
					ObAccountDealInfo dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_INVEST.toString(),  map.get("custmerType"));
					if(dealinfo!=null){
						ret[0]=Constants.CODE_FAILED;
						ret[1]="投标业务数据处理失败:已经处理过了";
						logger.info("失败原因：ObAccountDealInfo="+dealinfo+"; 描述："+ret[1]);
					}else{
						BpCustMember bpCustMember=bpCustMemberService.get(info.getInvestPersonId());
						PlManageMoneyPlan plan=info.getPlManageMoneyPlan();
						BigDecimal investMoney=plan.getInvestedMoney().add(info.getBuyMoney());
						if(investMoney.compareTo(plan.getSumMoney())>0){//已经超投
							String	orderNum=ContextUtil.createRuestNumber();//第三方账号及系统账号的生成方法
							CommonResponse cr=new CommonResponse();
							//收款账户为注册用户流标
							if(null !=info.getPlManageMoneyPlan().getReceiverType() && 
									info.getPlManageMoneyPlan().getReceiverType().equals("zc")){
								CommonRequst cq=new CommonRequst();
								cq.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());//用户第三方标识
								cq.setRequsetNo(orderNum);//请求流水号
								cq.setQueryRequsetNo(info.getDealInfoNumber());//之前投标的请求流水号 
				    			cq.setBussinessType(ThirdPayConstants.BT_MMCANCELUSER);//业务类型
				    			cq.setTransferName(ThirdPayConstants.TN_MMCANCELUSER);//业务名称
				    			cq.setLoanNoList(info.getPreAuthorizationNum());//第三方流水号
				    			cq.setBidId(info.getPlManageMoneyPlan().getMmplanId().toString());
				    			cq.setAmount(info.getBuyMoney().add(info.getJoinMoney()));
				    			if(bpCustMember.getCustomerType()!=null&&bpCustMember.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
				    				cq.setAccountType(1);
				    			}else{//借款人是个人
				    				cq.setAccountType(0);
				    			}
				    			cr=ThirdPayInterfaceUtil.thirdCommon(cq);
							//收款账户为平台账户流标	
							}else{
								CommonRequst cq=new CommonRequst();
								cq.setConfimStatus(false);//false表示退回 true表示通过
								cq.setBussinessType(ThirdPayConstants.BT_MMCANCELPLATF);//业务类型
								cq.setTransferName(ThirdPayConstants.TN_MMCANCELPLATF);//业务名称
								cq.setRequsetNo(orderNum);
								cq.setQueryRequsetNo(info.getDealInfoNumber());//请求之前的流水号
								cq.setLoanNoList(info.getPreAuthorizationNum());//还款流水号
								cr=ThirdPayInterfaceUtil.thirdCommon(cq);
							}
							if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
								info.setState(Short.valueOf("-2"));
								plManageMoneyPlanBuyinfoService.save(info);
								Map<String,Object> mapOF=new HashMap<String,Object>();
								mapOF.put("investPersonId",info.getInvestPersonId());//投资人Id（必填）
								mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
								mapOF.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
								mapOF.put("money",info.getBuyMoney());//交易金额	（必填）			
								mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapOF.put("recordNumber",requestNo);//交易流水号	（必填）
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
								
								Map<String,Object> mapUPlan=new HashMap<String,Object>();
								mapUPlan.put("investPersonId",info.getInvestPersonId());//投资人Id（必填）
								mapUPlan.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
								mapUPlan.put("transferType",ObAccountDealInfo.T_JOIN);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本,32加入费用(参见ObAccountDealInfo中的常量) （必填）
								mapUPlan.put("money",info.getJoinMoney());//加入费用（必填）			
								mapUPlan.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapUPlan.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapUPlan.put("recordNumber",requestNo+"TJ");//交易流水号	（必填）
								mapUPlan.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								obAccountDealInfoService.operateAcountInfo(mapUPlan);
								logger.info("投标保存系统账户交易记录：rett[0]="+rett[0]+"; rett[1]："+rett[1]+";requestNo="+requestNo);
								logger.info("成功流标：ObAccountDealInfo="+info+"; 描述："+ret[1]);
								ret[0]="successOther";
								ret[1]="投标失败：已满标,第三方冻结资金已经归还";
							}else{
								Map<String,Object> mapOF=new HashMap<String,Object>();
								mapOF.put("investPersonId",info.getInvestPersonId());//投资人Id（必填）
								mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
								mapOF.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
								mapOF.put("money",info.getBuyMoney());//交易金额	（必填）			
								mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapOF.put("recordNumber",requestNo);//交易流水号	（必填）
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
								info.setState(PlBidInfo.TYPE7);//异常标
								plManageMoneyPlanBuyinfoService.save(info);
								logger.info("失败原因：ObAccountDealInfo="+info+"; 描述："+ret[1]);
								ret[0]="successOther";
								ret[1]="投标失败：第三方资金仍冻结,请联系管理员";
							}
						}else{
							plan.setInvestedMoney(investMoney);
							if(investMoney.compareTo(plan.getSumMoney())==0){
								plan.setState(2);//标已满标
							}
							plManageMoneyPlanService.save(plan);//保存理财计划的状态
							info.setState(Short.valueOf("1"));
							if(map.containsKey("loanNo")){
								info.setPreAuthorizationNum(map.get("loanNo").toString());
							}
							plManageMoneyPlanBuyinfoService.save(info);
							Map<String,Object> mapOF=new HashMap<String,Object>();
							mapOF.put("investPersonId",info.getInvestPersonId());//投资人Id（必填）
							mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapOF.put("transferType",ObAccountDealInfo.T_INVEST);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapOF.put("money",info.getBuyMoney());//交易金额	（必填）			
							mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("recordNumber",requestNo);//交易流水号	（必填）
							mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
							
							//针对理财计划，新增一条加入费用
							Map<String,Object> mapUPlan=new HashMap<String,Object>();
							mapUPlan.put("investPersonId",info.getInvestPersonId());//投资人Id（必填）
							mapUPlan.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapUPlan.put("transferType",ObAccountDealInfo.T_JOIN);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本,32加入费用(参见ObAccountDealInfo中的常量) （必填）
							mapUPlan.put("money",info.getJoinMoney());//加入费用	（必填）			
							mapUPlan.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapUPlan.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapUPlan.put("recordNumber",requestNo+"TJ");//交易流水号	（必填）
							mapUPlan.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							//如果加入费用小于0，不插入资金明细
							if(info.getJoinMoney().compareTo(new BigDecimal(0))>0){
								obAccountDealInfoService.operateAcountInfo(mapUPlan);
							}
							
							//判断是否使用了优惠券  设置优惠券状态
							if(info.getCouponId()!=null&&!info.getCouponId().equals("")){
								BpCoupons coupon = bpCouponsService.get(info.getCouponId());
								if(plan!=null&&coupon!=null){
									coupon.setCouponStatus(BpCoupons.COUPONSTATUS1);
									bpCouponsService.save(coupon);
								}
							}
							
							
							try {
								System.out.println("plan.getStartinInterestCondition()==="+plan.getStartinInterestCondition());
								//判断起息类型———>投资日起息（即投即转账即起息）
								if(null !=plan.getStartinInterestCondition() && plan.getStartinInterestCondition().equals("1")){
									info.setStartinInterestTime(info.getBuyDatetime());
									info.setEndinInterestTime(DateUtil.addDaysToDate(DateUtil.addMonthsToDate(info.getStartinInterestTime(),plan.getInvestlimit().intValue()),-1));
									if(null !=plan.getReceiverType() && plan.getReceiverType().equals("pt")){//平台账户收款
										plManageMoneyPlanService.ptzhLoan(plan, info);
									}else{//注册账户收款
										plManageMoneyPlanService.zczhLoan(plan, info);
									}
										
								//投标日+1起息(既投即转账次日起息)	
								}else if(null !=plan.getStartinInterestCondition() && plan.getStartinInterestCondition().equals("2")){
									info.setStartinInterestTime(DateUtil.addDaysToDate(info.getBuyDatetime(),1));
									info.setEndinInterestTime(DateUtil.addDaysToDate(DateUtil.addMonthsToDate(info.getStartinInterestTime(),plan.getInvestlimit().intValue()),-1));
									if(null !=plan.getReceiverType() && plan.getReceiverType().equals("pt")){//平台账户收款
										plManageMoneyPlanService.ptzhLoan(plan, info);
									}else{//注册账户收款
										plManageMoneyPlanService.zczhLoan(plan, info);
									}
								}
							} catch (Exception e) {
								System.out.println("即时起息出错开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
								e.printStackTrace();
								System.out.println("即时起息出错结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
							}
							
							
							//投标成功，发送短信提醒
							try {
								//投标成功发送短信、邮件、站内信。
								Map<String, String> mapMessage = new HashMap<String, String>();
								mapMessage.put("key", "sms_Bid");
								mapMessage.put("userId",bpCustMember.getId().toString());
								mapMessage.put("${projNumber}", plan.getMmNumber());
								mapMessage.put("${projName}", plan.getMmName());
								mapMessage.put("${code}", info.getBuyMoney().setScale(2).toString());
								//String result =  sendMesService.sendSmsEmailMessage(mapMessage);
								//smsSendUtil.sms_Bid(bpCustMember.getTelphone(), info.getBuyMoney().toString(), plan.getMmName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							
							//String[] rett =obAccountDealInfoService.operateAcountInfo(info.getUserId().toString(), ObAccountDealInfo.T_INVEST.toString(), info.getUserMoney().toString(), "3", map.get("custmerType"), ObAccountDealInfo.UNFREEZY.toString(), ObAccountDealInfo.DEAL_STATUS_1.toString(), requestNo);
							logger.info("投标保存系统账户交易记录：rett[0]="+rett[0]+"; rett[1]："+rett[1]+";requestNo="+requestNo);
							ret[0]=Constants.CODE_SUCCESS;
							ret[1]="投标成功，资金账户成功冻结";
							logger.info("PlManageMoneyPlanBuyinfo.id="+info.getOrderId()+";PlManageMoneyPlanBuyinfo.status="+info.getState()+";PlManageMoneyPlanBuyinfo.orderNo="+info.getDealInfoNumber());
						}
					}
				}else{
					info.setState(PlBidInfo.TYPE3);
					plManageMoneyPlanBuyinfoService.save(info);
					ret[0]=Constants.CODE_FAILED;
					ret[1]="投标失败";
					logger.info("PlManageMoneyPlanBuyinfo.id="+info.getOrderId()+";PlManageMoneyPlanBuyinfo.status="+info.getState()+";PlManageMoneyPlanBuyinfo.orderNo="+info.getDealInfoNumber());
				}
				ret[2]=info.getKeystr();//U计划用到
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="取现业务业务数据处理失败:数据不存在或者已经处理过了";
				logger.info("失败原因：PlManageMoneyPlanBuyinfo="+info+"; 描述："+ret[1]);
			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
    /**
     * 处理站岗资金的购买业务
     */
	@Override
	public Object[] doFianceProductBuy(Map<String, String> map) {
		// TODO Auto-generated method stub
		String cardNo=map.get("requestNo").toString();
		Short status=Short.valueOf(map.get("dealRecordStatus").toString());
		Object[] ret=new Object[2];
		try{
			PlFinanceProductUserAccountInfo pinfo=plFinanceProductUserAccountInfoDao.get("requestNo", cardNo);
			if(pinfo!=null){
				ObAccountDealInfo ob=obAccountDealInfoDao.get("recordNumber", cardNo);
					if(status.equals(Short.valueOf("7"))){//如果是冻结
						if(map.containsKey("loanNo")){
							pinfo.setPreAuthorizationNum(map.get("loanNo").toString());
							plFinanceProductUserAccountInfoDao.save(pinfo);
						}
						ret=this.checkFianceProductBuy(pinfo);
						
					}else{//其他交易情况
						pinfo.setDealDate(new Date());
						pinfo.setDealStatus(Short.valueOf("1"));
						pinfo.setDealStatusName("未起息金额");
						pinfo.setRemark("成功转入金额");
						plFinanceProductUserAccountInfoDao.save(pinfo);
						
						if(ob==null){
							Map<String,Object> mapOF=new HashMap<String,Object>();
							mapOF.put("investPersonId",pinfo.getUserId());//投资人Id（必填）
							mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapOF.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapOF.put("money",pinfo.getAmont().add(pinfo.getPlateFee()!=null?pinfo.getPlateFee():new BigDecimal(0)));//交易金额	（必填）			
							mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("recordNumber",cardNo);//交易流水号	（必填）
							if(status.equals(Short.valueOf("2"))){
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}else{
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}
							
							String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
							
							if(status.equals(Short.valueOf("2"))){
								ret[0]=Constants.CODE_SUCCESS;
								ret[1]="操作类型-购买成功";
							}else{
								ret[0]=Constants.CODE_FAILED;
								ret[1]="操作类型-购买失败";
							}
						}else{
							if(!ob.getDealRecordStatus().equals(ObAccountDealInfo.DEAL_STATUS_2)){
								ret[0]=Constants.CODE_SUCCESS;
								ret[1]="操作类型-已经处理过业务数据";
							}else{
								Map<String,Object> mapup=new HashMap<String,Object>();
								mapup.put("investPersonId",ob.getInvestPersonId());//投资人Id
								mapup.put("investPersonType",Short.valueOf("0"));//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
								mapup.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
								mapup.put("money",ob.getPayMoney().add(pinfo.getPlateFee()!=null?pinfo.getPlateFee():new BigDecimal(0)));//交易金额
								mapup.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
								mapup.put("DealInfoId",null);//交易记录id，没有默认为null
								mapup.put("recordNumber",cardNo);//交易流水号
								if(status.equals(Short.valueOf("2"))){
									mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);
								}else{
									mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);
								}
								//资金交易状态：1等待支付，2支付成功，3 支付失败。。。
								obAccountDealInfoService.updateAcountInfo(mapup);
								if(status.equals(Short.valueOf("2"))){
									ret[0]=Constants.CODE_SUCCESS;
									ret[1]="操作类型-购买成功";
								}else{
									ret[0]=Constants.CODE_FAILED;
									ret[1]="操作类型-购买失败";
								}
							}
						}
					}
				
				
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="操作类型-没有购买站岗资金记录";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="操作类型-系统错误";
		}
		return ret;
	}

	private Object[] checkFianceProductBuy(PlFinanceProductUserAccountInfo pinfo) {
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			ObAccountDealInfo ob=obAccountDealInfoDao.get("recordNumber", pinfo.getRequestNo());
				BpCustMember mem = bpCustMemberService.get(pinfo.getUserId());
				String orderNo = ContextUtil.createRuestNumber();
				CommonResponse cr=new CommonResponse();
				CommonRequst cq=new CommonRequst();
				cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
				cq.setConfimStatus(true);//false表示退回 true表示通过
				cq.setLoanNoList(pinfo.getPreAuthorizationNum());//转入时双乾返回的流水号
				cq.setTransferName(ThirdPayConstants.TN_CHECKHRBIN);
				cq.setBussinessType(ThirdPayConstants.BT_CHECKHRBIN);
				cq.setQueryRequsetNo(pinfo.getRequestNo());//转入时的流水号
				cq.setRequsetNo(orderNo);//请求流水号
				cq.setBidId(pinfo.getId().toString());
				cr=ThirdPayInterfaceUtil.thirdCommon(cq);
				if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
					Map<String,String> map = new HashMap<String, String>();
					map.put("requestNo", pinfo.getRequestNo());
					map.put("dealRecordStatus",  ObAccountDealInfo.DEAL_STATUS_2.toString());
					ret=doFianceProductBuy(map);
				}else{
					if(ob!=null){
					}else{
						Map<String,Object> mapOF=new HashMap<String,Object>();
						mapOF.put("investPersonId",pinfo.getUserId());//投资人Id（必填）
						mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapOF.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapOF.put("money",pinfo.getAmont());//交易金额	（必填）			
						mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("recordNumber", pinfo.getRequestNo());//交易流水号	（必填）
						mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
					}
					Map<String,String> map = new HashMap<String, String>();
					map.put("requestNo", pinfo.getRequestNo());
					map.put("dealRecordStatus",  ObAccountDealInfo.DEAL_STATUS_7.toString());
					ret=doFianceProductBuy(map);
				}
				
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="操作类型-系统错误";
		}
		return ret;
	}

	/**
	 * 平台发送奖励金额
	 * @param planId
	 * @param peridId
	 */
	public void checkCouponsIntent(String planId,String peridId,String requestNo,String basePath){
		PlBidPlan bidplan = plBidPlanService.get(Long.valueOf(planId));
		//判断此标是否可用优惠券
		if(bidplan.getCoupon()!=null&&bidplan.getCoupon().compareTo(1)==0){
			//判断返利方式是否是 随期或者到期
			if(bidplan.getRebateWay().compareTo(2)==0||bidplan.getRebateWay().compareTo(3)==0){
				List<BpFundIntent> bpfundInterestList=null;//利息
				List<BpFundIntent> bpfundPrincipalList=null;//本金
				String transferType="";//资金类型
				boolean checkWay=false;
				if(bidplan.getRebateWay().compareTo(2)==0){
					checkWay=true;
				}else if(bidplan.getRebateWay().compareTo(3)==0){
					int per = Integer.valueOf(peridId)+1;
					List<BpFundIntent> checkFund = bpFundIntentService.getCouponsIntent(planId, String.valueOf(per),requestNo,null);
					if(checkFund.size()==0){//表示当前期数是最后一期还款
						checkWay=true;
					}
				}
				if(checkWay){
					//判断 返利类型
					if(bidplan.getRebateType().compareTo(1)==0){//返现 principalCoupons
						transferType=ObAccountDealInfo.T_BIDRETURN_RETURNRATIO;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"principalCoupons");
					}else if(bidplan.getRebateType().compareTo(2)==0){//返息 couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE27;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"couponInterest");
					}else if(bidplan.getRebateType().compareTo(3)==0){//返息现  principalCoupons couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE29;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"couponInterest");
						bpfundPrincipalList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"principalCoupons");
					}else if(bidplan.getRebateType().compareTo(4)==0){//加息 couponInterest
						transferType=ObAccountDealInfo.T_BIDRETURN_RATE30;
						bpfundInterestList = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"subjoinInterest");
					}
					if(bpfundInterestList!=null){//返利息
						couponIntent(bpfundInterestList,bidplan,transferType,basePath);
					}
					if(bpfundPrincipalList!=null){//返本金
						couponIntent(bpfundPrincipalList,bidplan,ObAccountDealInfo.T_BIDRETURN_RATE28,basePath);
					}
				}
			}
		}else{
			//判断是否此标设置了普通加息
			if(bidplan.getAddRate()!=null&&!bidplan.getAddRate().equals("")){
				//判断是否设置的是随期或到期
				if(bidplan.getRebateWay().compareTo(2)==0||bidplan.getRebateWay().compareTo(3)==0){
					boolean checkWay=false;
					if(bidplan.getRebateWay().compareTo(2)==0){
						checkWay=true;
					}else if(bidplan.getRebateWay().compareTo(3)==0){
						int per = Integer.valueOf(peridId)+1;
						List<BpFundIntent> checkFund = bpFundIntentService.getCouponsIntent(planId, String.valueOf(per),requestNo,null);
						if(checkFund.size()==0){//表示当前期数是最后一期还款
							checkWay=true;
						}
					}
					if(checkWay){
						List<BpFundIntent> subjoinInterest = bpFundIntentService.getCouponsIntent(planId, peridId,requestNo,"commoninterest");
						couponIntent(subjoinInterest,bidplan,ObAccountDealInfo.T_BIDRETURN_ADDRATE,basePath);
					}
				}
			}
		}
	}
	/**
	 * 派发优惠券奖励
	 * @param bp
	 */
	public void couponIntent(List<BpFundIntent> bp,PlBidPlan bidplan,String transferType,String basePath){
		for(BpFundIntent bpfund:bp){
			if(bpfund.getFactDate()==null||bpfund.getFactDate().equals("")){
				BpCustMember mem=null;
				PlBidInfo bidInfo1 = plBidInfoService.getOrderNumber(bpfund.getOrderNo());
				//判断是否发生了债权交易,奖励发给新的债权人(针对于普通加息) 
				if(bidInfo1.getNewInvestPersonId()!=null&&!bidInfo1.getNewInvestPersonId().equals("")){
					mem=bpCustMemberService.get(bidInfo1.getNewInvestPersonId());
				}else{
					mem=bpCustMemberService.get(bpfund.getInvestPersonId());
				} 
				if(mem!=null){
					String requestNo=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
					CommonRequst commonRequest = new CommonRequst();
					commonRequest.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方标识
					commonRequest.setRequsetNo(requestNo);//请求流水号
					commonRequest.setAmount(bpfund.getNotMoney());//交易金额
					if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
						commonRequest.setAccountType(1);
					}else{//借款人是个人
						commonRequest.setAccountType(0);
					}
					commonRequest.setCustMemberType("0");
					commonRequest.setBidId(bpfund.getFundIntentId().toString());
					commonRequest.setBussinessType(ThirdPayConstants.BT_COUPONAWARD);//业务类型
					commonRequest.setTransferName(ThirdPayConstants.TN_COUPONAWARD);//业务名称
					CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequest);
					if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
						//添加资金明细
						Map<String,Object> map3=new HashMap<String,Object>();
						map3.put("investPersonId",mem.getId());//投资人Id（必填）
						map3.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						map3.put("transferType",transferType);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						map3.put("money",bpfund.getNotMoney());//交易金额	（必填）			 
						map3.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						map3.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						map3.put("recordNumber",requestNo);//交易流水号	（必填）
						map3.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(map3);
						//更新款项
						bpfund.setNotMoney(new BigDecimal(0));
						bpfund.setAfterMoney(bpfund.getIncomeMoney());
						bpfund.setFactDate(new Date());
						bpfund.setRequestDate(new Date());
						bpfund.setRequestCount(bpfund.getRequestCount()!=null?bpfund.getRequestCount().intValue()+1:1);
						bpfund.setNotMoney(new BigDecimal(0));
						bpfund.setIsCheck(Short.valueOf("0"));
						bpfund.setRepaySource(BpFundIntent.REPAYSOURCE1);
						bpfund.setLoanerRepayMentStatus(1);
						bpFundIntentService.save(bpfund);
					}
				}
				
			}
			
		}
	}
	/**
	 * 代偿还款
	 */
	@Override
	public Object[] doCompensatory(Map<String, String> map) {
		// TODO Auto-generated method stub
		String cardNo=map.get("requestNo").toString();
		Short status=Short.valueOf(map.get("dealRecordStatus").toString());	
		Object[] ret=new Object[2];
		try{
			PlBidCompensatoryFlow flow=plBidCompensatoryFlowDao.get("requestNo", cardNo);
			if(flow!=null){
				ObAccountDealInfo ob=obAccountDealInfoDao.get("recordNumber", cardNo);
				PlBidCompensatory atory = plBidCompensatoryService.get(flow.getCompensatoryId());
					if(status.equals(Short.valueOf("7"))){//如果是冻结
						flow.setBackStatus(1);
						flow.setPreAuthorizationNum(map.get("loanNo").toString());
						plBidCompensatoryFlowDao.save(flow);
						ret=this.checkPlBidCompensatory(flow,atory);
					}else{//其他交易情况
						flow.setBackStatus(2);
						flow.setBackDate(new Date());
						plBidCompensatoryFlowDao.save(flow);
						
						//已偿还的罚息
						atory.setBackPunishMoney(atory.getBackPunishMoney().add(flow.getBackPunishMoney()));
						//已偿还的金额
						atory.setBackCompensatoryMoney(atory.getBackCompensatoryMoney().add(flow.getBackCompensatoryMoney()));
						//判断是否偿还完成
						BigDecimal sumMoney = atory.getPunishMoney().add(atory.getCompensatoryMoney());
						BigDecimal backMoney = atory.getBackPunishMoney().add(atory.getBackCompensatoryMoney()).add(atory.getPlateMoney());
						if(backMoney.compareTo(sumMoney)!=-1){
							atory.setBackStatus(2);
						}
						atory.setBackDate(new Date());
						plBidCompensatoryService.save(atory);
						
						if(ob==null&&atory!=null){
							Map<String,Object> mapOF=new HashMap<String,Object>();
							mapOF.put("investPersonId",atory.getLoanerp2pId());//投资人Id（必填）
							mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapOF.put("transferType",ObAccountDealInfo.T_PLBID_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapOF.put("money",flow.getBackPunishMoney().add(flow.getBackCompensatoryMoney()));//交易金额	（必填）			
							mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapOF.put("recordNumber",cardNo);//交易流水号	（必填）
							if(status.equals(Short.valueOf("2"))){
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}else{
								mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}
							
							String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
							//如果是担保户代偿的，收款的账户为担保户，要处理担保户账户的业务数据
							if(null!=atory.getCompensatoryType() && "GURANEE".equals(atory.getCompensatoryType())){
							
							BpCustMember bps=bpCustMemberService.get(atory.getCompensatoryP2PId());
							Map<String,Object> mapI=new HashMap<String,Object>();
							mapI.put("investPersonId",bps.getId());//投资人Id（必填）
							mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
							mapI.put("transferType",ObAccountDealInfo.T_REPAYMENT_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
							mapI.put("money",flow.getBackPunishMoney().add(flow.getBackCompensatoryMoney()));//交易金额	（必填）			 
							mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
							mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
							mapI.put("recordNumber",cardNo+"G");//交易流水号	（必填）
						//	mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						//	System.out.println("dealStatus=="+mapI.get("dealStatus"));
							if(status.equals(Short.valueOf("2"))){
								mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}else{
								mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							}
							obAccountDealInfoService.operateAcountInfo(mapI);
							}
							
							if(status.equals(Short.valueOf("2"))){
								ret[0]=Constants.CODE_SUCCESS;
								ret[1]="操作类型-还款成功";
							}else{
								ret[0]=Constants.CODE_FAILED;
								ret[1]="操作类型-还款失败";
							}
						}else{
							if(!ob.getDealRecordStatus().equals(ObAccountDealInfo.DEAL_STATUS_2)){
								ret[0]=Constants.CODE_SUCCESS;
								ret[1]="操作类型-已经处理过业务数据";
							}else{
								Map<String,Object> mapup=new HashMap<String,Object>();
								mapup.put("investPersonId",ob.getInvestPersonId());//投资人Id
								mapup.put("investPersonType",Short.valueOf("0"));//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
								mapup.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
								mapup.put("money",flow.getBackPunishMoney().add(flow.getBackCompensatoryMoney()));//交易金额
								mapup.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
								mapup.put("DealInfoId",null);//交易记录id，没有默认为null
								mapup.put("recordNumber",cardNo);//交易流水号
								if(status.equals(Short.valueOf("2"))){
									mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);
								}else{
									mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);
								}
								//资金交易状态：1等待支付，2支付成功，3 支付失败。。。
								obAccountDealInfoService.updateAcountInfo(mapup);
								//如果是担保户代偿的，收款的账户为担保户，要处理担保户账户的业务数据
								if(null!=atory.getCompensatoryType() && "GURANEE".equals(atory.getCompensatoryType())){
								
								BpCustMember bps=bpCustMemberService.get(atory.getCompensatoryP2PId());
								Map<String,Object> mapIp=new HashMap<String,Object>();
								mapIp.put("investPersonId",bps.getId());//投资人Id（必填）
								mapIp.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
								mapIp.put("transferType",ObAccountDealInfo.T_REPAYMENT_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
								mapIp.put("money",flow.getBackPunishMoney().add(flow.getBackCompensatoryMoney()));//交易金额	（必填）			 
								mapIp.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
								mapup.put("DealInfoId",null);//交易记录id，没有默认为null
								//mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
								mapIp.put("recordNumber",cardNo+"G");//交易流水号	（必填）
							//	mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
							//	System.out.println("dealStatus=="+mapI.get("dealStatus"));
								if(status.equals(Short.valueOf("2"))){
									mapIp.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								}else{
									mapIp.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_3);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
								}
								obAccountDealInfoService.updateAcountInfo(mapIp);
								}
								
								
								if(status.equals(Short.valueOf("2"))){
									ret[0]=Constants.CODE_SUCCESS;
									ret[1]="操作类型-还款成功";
								}else{
									ret[0]=Constants.CODE_FAILED;
									ret[1]="操作类型-还款失败";
								}
							}
						}
					}
				
				
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="操作类型-没有购买站岗资金记录";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="操作类型-系统错误";
		}
		return ret;
	}
	/**
	 * 代偿还款预冻结
	 * @param flow
	 * @param story
	 * @return 
	 */
    public Object[] checkPlBidCompensatory(PlBidCompensatoryFlow flow,PlBidCompensatory story){
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			ObAccountDealInfo ob=obAccountDealInfoDao.get("recordNumber", flow.getRequestNo());
				//Map<String,Object> mapFaildBid=new HashMap<String,Object>();
				//mapFaildBid.put("basePath","");
				//mapFaildBid.put("requestNo",flow.getRequestNo());
				//String[] retFaild=yeePayService.checkCommentTransfer(mapFaildBid);
				
				BpCustMember mem = bpCustMemberService.get(story.getLoanerp2pId());
				String orderNo = ContextUtil.createRuestNumber();
				CommonResponse cr=new CommonResponse();
				CommonRequst cq=new CommonRequst();
				cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
				cq.setConfimStatus(true);//false表示退回 true表示通过
				cq.setLoanNoList(flow.getPreAuthorizationNum());//转入时双乾返回的流水号
				cq.setTransferName(ThirdPayConstants.TN_CHECKHRBIN);
				cq.setBussinessType(ThirdPayConstants.BT_CHECKHRBIN);
				cq.setQueryRequsetNo(flow.getRequestNo());//转入时的流水号
				cq.setRequsetNo(orderNo);//请求流水号
				cq.setBidId(flow.getId().toString());
				cr=ThirdPayInterfaceUtil.thirdCommon(cq);
				if(cr.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
					Map<String,String> map = new HashMap<String, String>();
					map.put("requestNo", flow.getRequestNo());
					map.put("dealRecordStatus",  ObAccountDealInfo.DEAL_STATUS_2.toString());
					doCompensatory(map);
				}else{
					if(ob!=null){
					}else{
						Map<String,Object> mapOF=new HashMap<String,Object>();
						mapOF.put("investPersonId",story.getLoanerp2pId());//投资人Id（必填）
						mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapOF.put("transferType",ObAccountDealInfo.T_PLBID_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapOF.put("money",flow.getBackPunishMoney().add(flow.getBackCompensatoryMoney()));//交易金额	（必填）			
						mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("recordNumber", flow.getRequestNo());//交易流水号	（必填）
						mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_7);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
					}
					Map<String,String> map = new HashMap<String, String>();
					map.put("requestNo", flow.getRequestNo());
					map.put("dealRecordStatus",  ObAccountDealInfo.DEAL_STATUS_7.toString());
				}
				
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="操作类型-系统错误";
		}
		return ret;
    }
	/**
	 * 修改手机号码业务处理方法
	 */
	@Override
	public void chageMobile(Map<String, String> map) {
		//拿出bp_cust_member的主键
		//String id=map.get("requestNo").substring(11,map.get("requestNo").length());
		String newTel = map.get("newTelphone").toString();
		String id = map.get("userId").toString();
		BpCustMember bpCustMember=bpCustMemberService.get(Long.valueOf(id));
		if(null !=bpCustMember){
			//bpCustMember.setTelphone(telephone);
			bpCustMember.setTelphone(newTel);
			bpCustMemberService.merge(bpCustMember);
		}
	}


	/**
	 * 企业信息变更接口
	 */
	@Override
	public void modifyEnterprise(Map<String, String> map) {
		String id = map.get("userId").toString();
		BpCustMember bpCustMember=bpCustMemberService.get(Long.valueOf(id));
		if(null !=bpCustMember){
			if (map.containsKey("modifyType") && StringUtils.isNotEmpty(map.get("modifyType").toString())){
				String modifyType = map.get("modifyType").toString();
				if ("1".equals(modifyType)){//1 -企业名称
					if (map.containsKey("corpName") && StringUtils.isNotEmpty(map.get("corpName").toString())) {
						bpCustMember.setTruename(map.get("corpName").toString());
						List<WebBankcard> bankList = webBankcardService.getByUserId(bpCustMember.getId());
						if (bankList!=null && bankList.size()>0){
							for (WebBankcard webBankcard : bankList) {
								webBankcard.setAccountname(bpCustMember.getTruename());
								webBankcard.setUsername(bpCustMember.getTruename());
								webBankcardService.save(webBankcard);
							}
						}
					}
				}else if("2".equals(modifyType)) {
					if (map.containsKey("artificialRealName") && StringUtils.isNotEmpty(map.get("artificialRealName").toString())){
						bpCustMember.setLegalPerson(map.get("artificialRealName").toString());
					}
					if (map.containsKey("artificialIdentityCode") && StringUtils.isNotEmpty(map.get("artificialRealName").toString())){
						bpCustMember.setLegalNo(map.get("artificialIdentityCode").toString());
					}
				}else if("3".equals(modifyType)) {
					if (map.containsKey("newTelphone") && StringUtils.isNotEmpty(map.get("newTelphone").toString())) {
						String newTel = map.get("newTelphone").toString();
						bpCustMember.setTelphone(newTel);
					}
				}
			}else {
				System.out.println("修改企业信息类型为空！！！");
			}
			bpCustMemberService.merge(bpCustMember);
		}
	}

	@Override
	public Object[] repaymentAuthorizationMoneyPlan(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object[] replaceCard(Map<String, String> map) {
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if(!"".equals(requestNo)){
			card=webBankcardService.getByRequestNo(requestNo);
		}
		if(card!=null){
			synchronized(requestNo){
				card.setThirdConfig(configMap.get("thirdPayConfig").toString());
				if(map.containsKey("bankCardNo")&&!"".equals(map.get("bankCardNo")) && map.get("bankCardNo") !=null){
					card.setCardNum(map.get("bankCardNo"));
				}
				if(map.containsKey("bankCode")&&!"".equals(map.get("bankCode")) && map.get("bankCode") !=null){
					card.setBankId(map.get("bankCode"));
				}
				if(map.containsKey("bankName")&&!"".equals(map.get("bankName"))&& map.get("bankName") !=null){
					card.setBankname(map.get("bankName"));
				}
				
				if(map.containsKey("bankType")&&!"".equals(map.get("bankType")) && map.get("bankType") !=null){
					card.setAccounttype(map.get("bankType"));
				}
				if(map.containsKey("province")&&!"".equals(map.get("province")) && map.get("province") !=null){
					card.setProvinceId(Long.valueOf(map.get("province")));
				}
				if(map.containsKey("city")&&!"".equals(map.get("city")) && map.get("city") !=null){
					card.setCityId(Long.valueOf(map.get("city")));
				}
				if(WebBankcard.BINDCARD_STATUS_SUCCESS.equals(map.get("bankstatus"))){
					//只有当前卡更换成功时，才把原来的卡修改 为失败
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
					List<WebBankcard> bankList = webBankcardService.getBycusterId(card.getCustomerId());
					for(WebBankcard wk : bankList){
						if(!requestNo.equals(wk.getRequestNo())){
							wk.setBindCardStatus(WebBankcard.BINDCARD_STATUS_CANCEL);
							webBankcardService.merge(wk);
						}
					}
				}else if(WebBankcard.BINDCARD_STATUS_FAILD.equals(map.get("bankstatus"))) {
					//绑卡失败
					card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_FAILD);
					//将原来的卡修改回成功的状态
					List<WebBankcard> bankList = webBankcardService.getBycustAndState(card.getCustomerId(),WebBankcard.BINDCARD_STATUS_CANCELAPPLY);
					for(WebBankcard wk : bankList){
						if(!requestNo.equals(wk.getRequestNo())){
							wk.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
							webBankcardService.merge(wk);
						}
					}
				}else{
					card.setBindCardStatus(map.get("bankstatus"));
				}
				webBankcardService.save(card);
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="更换银行卡业务数据处理成功";
				logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="更换银行卡业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：card="+card+";");
		}
		
		return ret;
	}
	
	
/*	@Override    保留原方法
	public Object[] replaceCard(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		WebBankcard card=null;
		if(!"".equals(requestNo)){
			card=webBankcardService.getByRequestNo(requestNo);
		}
		if(card!=null){
			synchronized(requestNo){
				
				card.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
				webBankcardService.merge(card);
				List<WebBankcard> bankList = webBankcardService.getBycusterId(card.getCustomerId());
				for(WebBankcard wk : bankList){
					if(!requestNo.equals(wk.getRequestNo())){
						wk.setBindCardStatus(WebBankcard.BINDCARD_STATUS_FAILD);
					}
				}
				ret[0]=Constants.CODE_SUCCESS;
				ret[1]="更换银行卡业务数据处理成功";
				logger.info("WebBankcard.id="+card.getCardId()+";WebBankcard.cardNum="+card.getCardNum()+";WebBankcard.BankId="+card.getBankId()+";WebBankcard.BindCardStatus="+card.getBindCardStatus());
			}
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="更换银行卡业务数据处理失败:数据不存在或者已经处理过了";
			logger.info("失败原因：card="+card+"; 描述："+ret[1]);
		}
		return ret;
	}
*/
	/**
	 * 第三方支付客户自助还款接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	@Override
	public String[] umpayRepayment(Map<String, String> map) {
		String[] ret=new String[2];
		String requestNo=map.get("requestNo");
		synchronized(requestNo){
			//联动优势List<BpFundIntent> list= bpFundIntentService.getByRequestNo(requestNo);
			List<BpFundIntent> list=bpFundIntentService.getByRequestNoLoaner(requestNo);
			BigDecimal totalMoney=new BigDecimal(0);
			if(list!=null&&list.size()>0){
				String orderNoStr="";
				BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidPlanService.get(list.get(0).getBidPlanId()));
				for(BpFundIntent temp:list){
					synchronized(temp.getNotMoney()){
						if(temp.getNotMoney().compareTo(new BigDecimal(0))>0&&(temp.getFundType().equals("principalRepayment")||temp.getFundType().equals("loanInterest")||temp.getFundType().equals("consultationMoney")||
							temp.getFundType().equals("serviceMoney"))||temp.getFundType().equals("interestPenalty")){
							totalMoney=totalMoney.add(temp.getNotMoney().add(temp.getAccrualMoney()));
							temp.setLoanerRepayMentStatus(1);
							temp.setIsCheck(Short.valueOf("0"));
							temp.setReturnDate(new Date());
							temp.setRepaySource(BpFundIntent.REPAYSOURCE1);
							bpFundIntentService.merge(temp);
							logger.info("款项业务已成功处理：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
							orderNoStr=temp.getFundIntentId()+":"+temp.getFundType()+":"+temp.getOrderNo()+";";
						}else{
							temp.setLoanerRepayMentStatus(1);
							temp.setIsCheck(Short.valueOf("0"));
							temp.setRepaySource(BpFundIntent.REPAYSOURCE1);
							bpFundIntentService.merge(temp);
							logger.info("款项业务已经被处理过：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
						}
					}
				}
				if(totalMoney.compareTo(new BigDecimal(0))>0){//借款人生成款项
					ObAccountDealInfo dealinfo=obAccountDealInfoService.getByOrderNumber(requestNo, "", ObAccountDealInfo.T_LOANPAY.toString(),  "0");
					if(dealinfo!=null){
						ret[0]=Constants.CODE_FAILED;
						ret[1]="还款业务数据处理完成-重复处理,无需生成款项";
						logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";本次处理数据集合(FundIntentId:fundtype:orderNo)："+orderNoStr);
					}else{
						Map<String,Object> mapL=new HashMap<String,Object>();
						mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
						mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapL.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapL.put("money",totalMoney);//交易金额	（必填）			 
						mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapL.put("recordNumber",requestNo);//交易流水号	（必填）
						mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(mapL);
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="还款业务数据处理完成";
						logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";本次处理数据集合(FundIntentId:fundtype:orderNo)："+orderNoStr);
					}
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="还款业务数据处理失败:数据不存在";
				logger.info("失败原因：requestNo="+requestNo+"; 描述："+ret[1]);
			}
		}
		return ret;
	}
    /**
     *联动优势提前还款 前台回调方法
     */
	@Override
	public String[] umpayPreRepayment(Map<String, String> map) {
		String[] ret=new String[2];
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String requestNo=map.get("requestNo");
			String bidId = map.get("bidId");
			String userId = map.get("userId");
			String dealMoney = map.get("dealMoney");
			String slEarlyRepayment = map.get("slEarlyRepayment");
			List<BpFundIntent> list = bpFundIntentService.getUnCheckIntent(bidId);
			if(list.size()>0){
				for(BpFundIntent info:list){
					//对应的款项置为无效
					info.setLoanerRepayMentStatus(1);
					info.setRequestNo(requestNo);
					info.setIsValid(Short.valueOf("1"));
					info.setIsCheck(Short.valueOf("1"));
					//info.setRepaySource(Short.valueOf("1"));
					bpFundIntentService.merge(info);
				}
				//对应的新生成的款项  置为有效
				List<BpFundIntent> list2 = bpFundIntentService.getBySlEarlyId(slEarlyRepayment);
                if(list2.size()>0){
                	for(BpFundIntent info:list2){
                		info.setLoanerRepayMentStatus(1);
                		info.setRequestNo(requestNo);
                		//info.setRepaySource(Short.valueOf("1"));
                		info.setIsCheck(Short.valueOf("0"));
                		info.setReturnDate(new Date());
                		bpFundIntentService.merge(info);
                	}
                }
				Map<String,Object> mapL=new HashMap<String,Object>();
				mapL.put("investPersonId",userId);//投资人Id（必填）
				mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
				mapL.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
				mapL.put("money",new BigDecimal(dealMoney));//交易金额	（必填）			 
				mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
				mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
				mapL.put("recordNumber",requestNo);//交易流水号	（必填）
				mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
				obAccountDealInfoService.operateAcountInfo(mapL);
				/*if(bidId!=null&&!"".equals(bidId)){
					PlBidPlan plan = plBidPlanService.get(Long.valueOf(bidId));	
					plan.setState(10);//更新状态为已完成
					plBidPlanService.merge(plan);
				}*/
				ret[0]=Constants.CODE_SUCCESS;	
				ret[1]="借款人提前还款成功";
				logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]);
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="还款业务数据处理失败:数据不存在";
				logger.info("失败原因：requestNo="+requestNo+"; 描述："+ret[1]);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="还款业务数据处理失败:系统错误";
		}
		return ret;
	}
	/**
	 * 担保公司担保代偿收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	@Override
	public String[] compensatory(Map<String, String> map) {
		// TODO Auto-generated method stub
		try{
			String[] ret=new String[2];
			String requestNo=map.get("requestNo");
			synchronized(requestNo){
				List<BpFundIntent> list= bpFundIntentService.getByRequestNo(requestNo);
				BpCustMember LoanMember =new BpCustMember();
				String compType = "";//担保类型
				if(list!=null && list.size()>0){
					String orderNoStr="";
					BigDecimal sumMoney = new BigDecimal(0);//借款人还息总额
					for(BpFundIntent temp:list){
						String type = plBidPlanService.get(temp.getBidPlanId()).getGuaranteeWay(); 
						compType = type;
						if(!temp.getFundType().equals("couponInterest")&&!temp.getFundType().equals("principalCoupons")
								&&!temp.getFundType().equals("subjoinInterest")&&!temp.getFundType().equals("commoninterest")
									&&!temp.getFundType().equals("raiseinterest")){//投资人奖励明细不走这里
							synchronized(temp.getNotMoney()){
								 LoanMember=plBidCompensatoryService.getGuraneeMember(plBidPlanService.get(temp.getBidPlanId()));
								//BpCustMember LoanMember=plBidInfoService.getLoanMember(plBidPlanService.get(temp.getBidPlanId()));
								if(temp.getNotMoney().compareTo(new BigDecimal(0))>0){
									sumMoney=sumMoney.add(temp.getNotMoney()).add(temp.getAccrualMoney());
								/*	Map<String,Object> mapL=new HashMap<String,Object>();
									mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
									mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
									mapL.put("transferType",ObAccountDealInfo.T_PLBID_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
									if(type!=null && !"".equals(type)){
										if(type.equals("1") || type.equals("2")){
											mapL.put("money",temp.getNotMoney());
										}else{
											mapL.put("money",temp.getNotMoney().add(temp.getAccrualMoney()));//交易金额	（必填）
										}
									}else{
										mapL.put("money",temp.getNotMoney().add(temp.getAccrualMoney()));//交易金额	（必填）			 
									}
									mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapL.put("recordNumber",requestNo+temp.getFundIntentId()+"L");//交易流水号	（必填）
									mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
									obAccountDealInfoService.operateAcountInfo(mapL);*/
									List<InvestPersonInfo> iofos=investPersonInfoService.getByRequestNumber(temp.getOrderNo());
									if(temp.getFundType().equals("loanInterest")||temp.getFundType().equals("principalRepayment")){
										if(iofos!=null&&iofos.size()>0){
											PlBidInfo plbidinfo=plBidInfoService.getByOrdId(temp.getOrderNo());
					    					Long custmemId=iofos.get(0).getInvestPersonId();
					    					String bidOrderNo=temp.getOrderNo();
					    					if(plbidinfo!=null&&plbidinfo.getNewInvestPersonId()!=null){
					    						custmemId=plbidinfo.getNewInvestPersonId();
					    						bidOrderNo=plbidinfo.getNewOrderNo();
					    					}
				    						BpCustMember bps=bpCustMemberService.get(custmemId);
											Map<String,Object> mapI=new HashMap<String,Object>();
											mapI.put("investPersonId",bps.getId());//投资人Id（必填）
											mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
											mapI.put("transferType",temp.getFundType().equals("loanInterest")?ObAccountDealInfo.T_PROFIT:ObAccountDealInfo.T_PRINCIALBACK);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
											if(type!=null && !"".equals(type)){
												if(type.equals("1") || type.equals("2")){
													mapI.put("money",temp.getNotMoney());
												}else{
													mapI.put("money",temp.getNotMoney().add(temp.getAccrualMoney()));//交易金额	（必填）
												}
											}else{
												mapI.put("money",temp.getNotMoney().add(temp.getAccrualMoney()));//交易金额	（必填）			 
											}
											mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
											mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
											mapI.put("recordNumber",requestNo+temp.getFundIntentId()+"I");//交易流水号	（必填）
											mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
											System.out.println("dealStatus=="+mapI.get("dealStatus"));
											obAccountDealInfoService.operateAcountInfo(mapI);
										}
									}
									temp.setNotMoney(new BigDecimal(0));
									temp.setAfterMoney(temp.getIncomeMoney());
									temp.setFactDate(new Date());
									temp.setIsCheck(Short.valueOf("0"));
									temp.setRepaySource(BpFundIntent.REPAYSOURCE1);
									bpFundIntentService.merge(temp);
								//	slEarlyRepaymentRecordService.updateFundIntentInfo(temp.getBidPlanId(),new Long(temp.getPayintentPeriod()));//更新款项
									logger.info("款项业务已成功处理：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
									orderNoStr=temp.getFundIntentId()+":"+temp.getFundType()+":"+temp.getOrderNo()+";";
								}else{
									logger.info("款项业务已经被处理过：requestNo="+requestNo+";款项主键Id"+temp.getFundIntentId()+"; 款型类型："+temp.getFundType()+";交易订单号"+temp.getOrderNo());
								}
							}
						}
					}
					if(sumMoney.compareTo(new BigDecimal(0))>0){
						BigDecimal  compPay = new BigDecimal(0);
						Map<String,Object> mapL=new HashMap<String,Object>();
						if(compType!=null && !"".equals(compType)){
							for(BpFundIntent intent:list){
								compPay = compPay.add(intent.getIncomeMoney());
							}
							mapL.put("money",compPay);//交易金额	（必填）
						}else{
							mapL.put("money",sumMoney);//交易金额	（必填）			 
						}
						mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
						mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapL.put("transferType",ObAccountDealInfo.T_PLBID_COMPENSATORY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapL.put("recordNumber",requestNo+list.get(0).getFundIntentId()+"L");//交易流水号	（必填）
						mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(mapL);
					}
					List<BpFundIntent> couponslist= bpFundIntentService.getByRequestNo(requestNo);
					//平台发送奖励金额
					if(couponslist.size()>0){
						checkCouponsIntent(couponslist.get(0).getBidPlanId().toString(),couponslist.get(0).getPayintentPeriod().toString(),requestNo,null);
					}
					BpFundIntent b=list.get(0);
					//登记代偿台账
					plBidCompensatoryService.saveComPensatoryService(b.getBidPlanId().toString(),b.getIntentDate().toString(),requestNo,PlBidCompensatory.TYPE_GURANEE,b.getPayintentPeriod().toString());
					PlBidPlan plBidPlan =plBidPlanService.get(Long.valueOf(Long.valueOf(list.get(0).getBidPlanId())));
					List<InvestPersonInfo> infoList=investPersonInfoService.getByPlanId(Long.valueOf(list.get(0).getBidPlanId()));
					
					if(null !=infoList && infoList.size()>0){
						for(InvestPersonInfo temp:infoList){
							BpCustMember bp = bpCustMemberService.get(temp.getInvestPersonId());
							//统计利息和补偿息
							BigDecimal  lxAndBcx=bpFundIntentService.getAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('loanInterest','interestPenalty')");
							if(null ==lxAndBcx){
								lxAndBcx=new BigDecimal(0.00);
							}
							//统计本金
							BigDecimal  bj=bpFundIntentService.getAfterMoney(temp.getOrderNo(),temp.getBidPlanId().toString(),list.get(0).getPayintentPeriod().toString(),"('principalRepayment')");
							if(null ==bj){
								bj=new BigDecimal(0.00);
							}

						}
					}
				
					if(!list.get(0).getFundType().equals("couponInterest")&&!list.get(0).getFundType().equals("principalCoupons")
							&&!list.get(0).getFundType().equals("subjoinInterest")&&!list.get(0).getFundType().equals("commoninterest")
								&&!list.get(0).getFundType().equals("raiseinterest")){
						
							if(list.get(0).getSlEarlyRepaymentId()!=null&&!"".equals(list.get(0).getSlEarlyRepaymentId())){
								if(list.get(0).getBidPlanId()!=null&&!"".equals(list.get(0).getBidPlanId())){
									List<BpFundIntent> updateBp=bpFundIntentService.getList(list.get(0).getBidPlanId(), null);
									if(updateBp!=null&&updateBp.size()>0){
										for(BpFundIntent bp:updateBp){
											if((bp.getSlEarlyRepaymentId()==null||"".equals(bp.getSlEarlyRepaymentId()))&&bp.getAfterMoney().compareTo(new BigDecimal(0))==0){
												bp.setIsValid(Short.valueOf("1"));
												bp.setIsCheck(Short.valueOf("1"));
												bpFundIntentService.save(bp);
											}else if(bp.getSlEarlyRepaymentId()!=null&&list.get(0).getSlEarlyRepaymentId().equals(bp.getSlEarlyRepaymentId())){//等于当前的提前还款Id
												bp.setIsValid(Short.valueOf("0"));
												bp.setIsCheck(Short.valueOf("0"));
												bpFundIntentService.save(bp);
											}else if(bp.getSlEarlyRepaymentId()!=null&&!list.get(0).getSlEarlyRepaymentId().equals(bp.getSlEarlyRepaymentId())){//不等于当前提前还款Id
												if(bp.getAfterMoney().compareTo(new BigDecimal(0))==0){//表示没有还款对账成功
													bpFundIntentService.remove(bp);
												}
											}
										}
									}
								}
							}
						if(null !=list.get(0).getSlEarlyRepaymentId() && !"".equals(list.get(0).getSlEarlyRepaymentId())){//判断是否是提前还款
							SlEarlyRepaymentRecord slEarlyRepaymentRecord=slEarlyRepaymentRecordService.get(list.get(0).getSlEarlyRepaymentId());
							if(null !=slEarlyRepaymentRecord && !"".equals(slEarlyRepaymentRecord)){
								slEarlyRepaymentRecord.setCheckStatus(5);
								slEarlyRepaymentRecordService.save(slEarlyRepaymentRecord);
							}
						}	
					}
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="担保代偿业务数据处理完成";
					logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";本次处理数据集合(FundIntentId:fundtype:orderNo)："+orderNoStr);
					
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="担保代偿业务数据处理失败:数据不存在";
					logger.info("失败原因：requestNo="+requestNo+"; 描述："+ret[1]);
				}
			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void checkCouponsFinancialIntent(String mmPlanId, Date intentDate,
			String requestNo, String basePath) {
		// TODO Auto-generated method stub
		PlManageMoneyPlan bidplan = plManageMoneyPlanService.get(Long.valueOf(mmPlanId));
		//	PlManageMoneyPlanBuyinfo buy = plManageMoneyPlanBuyinfoSevice.get(orderInterest.getOrderId());
			//判断此标是否可用优惠券
			if(bidplan.getCoupon()!=null&&bidplan.getCoupon().compareTo(1)==0){
				//判断返利方式是否是 随期或者到期
				if(bidplan.getRebateWay().compareTo(2)==0||bidplan.getRebateWay().compareTo(3)==0){
					List<PlMmOrderAssignInterest> bpfundInterestList=null;//利息
					List<PlMmOrderAssignInterest> bpfundPrincipalList=null;//本金
					String transferType="";//资金类型
					boolean checkWay=false;
					if(bidplan.getRebateWay().compareTo(2)==0){//随期
						checkWay=true;
					}else if(bidplan.getRebateWay().compareTo(3)==0){
						List<PlMmOrderAssignInterest> checkFund = plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, null);
						if(null==checkFund || checkFund.size()==0){//表示当前期数是最后一期还款
							checkWay=true;
						}
					}
					//判断是否使用了优惠券，派发金额
					if(checkWay){
						//判断 返利类型
						if(bidplan.getRebateType().compareTo(1)==0){//返现 principalCoupons
							transferType=ObAccountDealInfo.T_BIDRETURN_RETURNRATIO;
							bpfundInterestList=plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "principalCoupons");
						}else if(bidplan.getRebateType().compareTo(2)==0){//返息 couponInterest
							transferType=ObAccountDealInfo.T_BIDRETURN_RATE27;
							bpfundInterestList=plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "couponInterest");
						}else if(bidplan.getRebateType().compareTo(3)==0){//返息现  principalCoupons couponInterest
							transferType=ObAccountDealInfo.T_BIDRETURN_RATE29;
							bpfundInterestList=plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "couponInterest");
							bpfundPrincipalList=plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "principalCoupons");
						}else if(bidplan.getRebateType().compareTo(4)==0){//加息 couponInterest
							transferType=ObAccountDealInfo.T_BIDRETURN_RATE30;
							bpfundInterestList=plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "subjoinInterest");
						}
					}
					if(bpfundInterestList!=null){//返利息
					    couponFinancialIntent(bpfundInterestList, bidplan, transferType,basePath);
					}
					if(bpfundPrincipalList!=null){//返本金
						couponFinancialIntent(bpfundInterestList, bidplan, ObAccountDealInfo.T_BIDRETURN_RATE28,basePath);
					}
				}
			}else{
				//判断此标是否设置普通加息
				//普通加息日化利率，判断是否是立还
				if(bidplan.getAddRate()!=null&&!bidplan.getAddRate().equals("")&&bidplan.getAddRate().compareTo(new BigDecimal(0))!=0){
					if(bidplan.getRebateWay().compareTo(2)==0){//随期
						List<PlMmOrderAssignInterest> bpfundInterestList = plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "commoninterest");
						couponFinancialIntent(bpfundInterestList, bidplan, ObAccountDealInfo.T_BIDRETURN_ADDRATE,basePath);
					}else if(bidplan.getRebateWay().compareTo(3)==0){//到期
						List<PlMmOrderAssignInterest> checkFund = plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, null);
						if(null==checkFund || checkFund.size()==0){//表示当前期数是最后一期还款
							List<PlMmOrderAssignInterest> bpfundInterestList = plMmOrderAssignInterestDao.getCouponsIntent(mmPlanId, intentDate, requestNo, "commoninterest");
							couponFinancialIntent(bpfundInterestList, bidplan, ObAccountDealInfo.T_BIDRETURN_ADDRATE,basePath);
						}
					}
					
				}
			}
		
	}
	/**
	 * 第三方支付个人理财顾问自助还款接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	@Override
	public String[] financialRepayment(Map<String, String> map) {
		// TODO Auto-generated method stub
		try{
			String[] ret=new String[2];
			String requestNo=map.get("requestNo");
			String thirdConfig = map.get("thirdPayConfig");
			synchronized(requestNo){
				List<PlMmOrderAssignInterest> list = new ArrayList<PlMmOrderAssignInterest>();
				if(thirdConfig!=null){
					list= plMmOrderAssignInterestDao.getByRequestNo(requestNo);
				}else{
					list= plMmOrderAssignInterestDao.getByInvestNo(requestNo);
				}
				if(list!=null && list.size()>0){
					String orderNoStr="";
					for(PlMmOrderAssignInterest temp:list){
						if(null!=temp.getFundType() && ("loanInterest".equals(temp.getFundType()) || "principalRepayment".equals(temp.getFundType()))){//投资人奖励明细不走这里
							synchronized(temp.getIncomeMoney()){
								PlManageMoneyPlan plan=plManageMoneyPlanService.get(temp.getMmplanId());
								BpCustMember LoanMember=bpCustMemberDao.isExist(plan.getMoneyReceiver());
								PlManageMoneyPlanBuyinfo info=plManageMoneyPlanBuyinfoDao.get(temp.getOrderId());//对应的交易记录
								if(temp.getIncomeMoney().compareTo(new BigDecimal(0))>0){
									Map<String,Object> mapL=new HashMap<String,Object>();
									mapL.put("investPersonId",LoanMember.getId());//投资人Id（必填）
									mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
									mapL.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
									mapL.put("money",temp.getIncomeMoney());//交易金额	（必填）			 
									mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
									mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
									mapL.put("recordNumber",requestNo+temp.getAssignInterestId()+"L");//交易流水号	（必填）
									mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
									obAccountDealInfoService.operateAcountInfo(mapL);
									if(temp.getFundType().equals("loanInterest")||temp.getFundType().equals("principalRepayment")||temp.getFundType().equals("interestPenalty")){
					    					Long custmemId=temp.getInvestPersonId();
					    					String bidOrderNo=info.getDealInfoNumber();
				    						BpCustMember bps=bpCustMemberService.get(custmemId);
											Map<String,Object> mapI=new HashMap<String,Object>();
											mapI.put("investPersonId",bps.getId());//投资人Id（必填）
											mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
											mapI.put("transferType",temp.getFundType().equals("loanInterest")?ObAccountDealInfo.T_PROFIT:ObAccountDealInfo.T_PRINCIALBACK);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
											mapI.put("money",temp.getIncomeMoney());//交易金额	（必填）			 
											mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
											mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
											mapI.put("recordNumber",requestNo+temp.getOrderId()+"I");//交易流水号	（必填）
											mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
											System.out.println("dealStatus=="+mapI.get("dealStatus"));
											obAccountDealInfoService.operateAcountInfo(mapI);
										
									}
									temp.setLoanerRepayMentStatus(2);
									temp.setAfterMoney(temp.getIncomeMoney());
									temp.setFactDate(new Date());
									temp.setIsCheck(Short.valueOf("0"));
									temp.setLockType("");//取消锁定状态
								//	temp.setRepaySource(BpFundIntent.REPAYSOURCE1);
									plMmOrderAssignInterestDao.merge(temp);
									//如果某一投资人的某一次投资记录的款项全部对账完成，则改变该投资记录的状态为10
									List<PlMmOrderAssignInterest> plList= plMmOrderAssignInterestDao.getByDealCondition(temp.getOrderId(),temp.getInvestPersonId());
									if(plList!=null&&plList.size()>0){
									}else{
										PlManageMoneyPlanBuyinfo i=plManageMoneyPlanBuyinfoDao.get(temp.getOrderId());
										info.setState(Short.valueOf("10"));
										plManageMoneyPlanBuyinfoDao.save(i);
									}
								//	slEarlyRepaymentRecordService.updateFundIntentInfo(temp.getBidPlanId(),new Long(temp.getPayintentPeriod()));//更新款项
									logger.info("款项业务已成功处理：requestNo="+requestNo+";款项主键Id"+temp.getOrderId()+"; 款型类型："+temp.getFundType()+";交易订单号"+info.getDealInfoNumber());
									orderNoStr=temp.getOrderId()+":"+temp.getFundType()+":"+info.getDealInfoNumber()+";";
								}else{
									logger.info("款项业务已经被处理过：requestNo="+requestNo+";款项主键Id"+temp.getOrderId()+"; 款型类型："+temp.getFundType()+";交易订单号"+info);
								}
							}
						}
					}
					
					//还款成功发送短信
					List<PlManageMoneyPlanBuyinfo> infoList=plManageMoneyPlanBuyinfoDao.getBuyInfoListByPlanId(list.get(0).getMmplanId(), Short.valueOf("0"), Short.valueOf("2"));
					PlManageMoneyPlan pplan=plManageMoneyPlanService.get(list.get(0).getMmplanId());
					if(null !=infoList && infoList.size()>0){
						for(PlManageMoneyPlanBuyinfo temp:infoList){
							BpCustMember bp = bpCustMemberService.get(temp.getInvestPersonId());
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
							String date=sdf.format(list.get(0).getIntentDate());
							BigDecimal paymentMoney = plMmOrderAssignInterestDao.findAfterMoney(temp.getOrderId(), pplan.getMmplanId(), date, "('principalRepayment')");
							BigDecimal interestMoney = plMmOrderAssignInterestDao.findAfterMoney(temp.getOrderId(), pplan.getMmplanId(), date, "('loanInterest')");
							BigDecimal totalMoney = plMmOrderAssignInterestDao.findAfterMoney(temp.getOrderId(), pplan.getMmplanId(), date, "('principalRepayment','loanInterest')");
                           //本次有还款金额的才会发送短信
							if(null!=totalMoney &&totalMoney.compareTo(new BigDecimal(0)) >= 0){
                            if(null==paymentMoney){
                            	paymentMoney=new BigDecimal("0");
                            }
                            if(null==interestMoney){
                            	interestMoney=new BigDecimal("0");
                            }
                   //       smsSendUtil.sms_paymentRemind(bp.getTelphone(), bp.getTruename(),paymentMoney.toString(), interestMoney.toString(), pplan.getMmName());
                            }
						
						}
					}
					//根据是否传入config参数进行业务处理   汇付还款不传config查找investNo字段
					List<PlMmOrderAssignInterest> clist= thirdConfig!=null?plMmOrderAssignInterestDao.getByRequestNo(requestNo):plMmOrderAssignInterestDao.getByInvestNo(requestNo);
					//平台发送奖励金额
					this.checkCouponsFinancialIntent(clist.get(0).getMmplanId().toString(),clist.get(0).getIntentDate(),requestNo,null);
					//如果某一投资人的某一次投资记录的款项全部对账完成，则改变该投资记录的状态为10
					List<PlMmOrderAssignInterest> plList= plMmOrderAssignInterestDao.getByDealCondition(list.get(0).getMmplanId());
					QueryFilter filter = new QueryFilter();
					filter.addFilter("Q_isCheck_SN_EQ", (short)0);
					filter.addFilter("Q_isValid_SN_EQ", (short)0);
					filter.addFilter("Q_mmplanId_L_EQ", list.get(0).getMmplanId());
					List<PlMmOrderAssignInterest> plList1 = plMmOrderAssignInterestDao.getAll(filter);
					if(plList1.size()>0){
						Boolean boo = true;
						for(PlMmOrderAssignInterest interest:plList1){
							if(interest.getFactDate()==null){
								boo = false;
							}
						}
						if(boo){
							pplan.setState(Integer.valueOf("10"));
							plManageMoneyPlanService.merge(pplan);
						}
					}
					
					if(plList!=null&&plList.size()>0){
						
					}else{
						pplan.setState(Integer.valueOf("10"));
						plManageMoneyPlanService.merge(pplan);
					}
					ret[0]=Constants.CODE_SUCCESS;
					ret[1]="还款业务数据处理完成";
					logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";本次处理数据集合(FundIntentId:fundtype:orderNo)："+orderNoStr);
					
				}else{
					ret[0]=Constants.CODE_FAILED;
					ret[1]="还款业务数据处理失败:数据不存在";
					logger.info("失败原因：requestNo="+requestNo+"; 描述："+ret[1]);
				}
			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void couponFinancialIntent(List<PlMmOrderAssignInterest> bp,
			PlManageMoneyPlan bidplan, String transferType, String basePath) {
		// TODO Auto-generated method stub
		for(PlMmOrderAssignInterest bpfund:bp){
			if(bpfund.getFactDate()==null||bpfund.getFactDate().equals("")){
				BpCustMember mem=bpCustMemberService.get(bpfund.getInvestPersonId());
				if(null!=mem){
					String requestNo=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
					CommonRequst commonRequest = new CommonRequst();
					commonRequest.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方标识
					commonRequest.setRequsetNo(requestNo);//请求流水号
					commonRequest.setAmount(bpfund.getIncomeMoney());//交易金额
					if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
						commonRequest.setAccountType(1);
					}else{//借款人是个人
						commonRequest.setAccountType(0);
					}
					commonRequest.setCustMemberType("0");
					commonRequest.setBidId(bpfund.getAssignInterestId().toString());
					commonRequest.setBussinessType(ThirdPayConstants.BT_COUPONAWARD);//业务类型
					commonRequest.setTransferName(ThirdPayConstants.TN_COUPONAWARD);//业务名称
					CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequest);
					if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
						//添加资金明细
						Map<String,Object> map3=new HashMap<String,Object>();
						map3.put("investPersonId",mem.getId());//投资人Id（必填）
						map3.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						map3.put("transferType",transferType);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						map3.put("money",bpfund.getIncomeMoney());//交易金额	（必填）			 
						map3.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						map3.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						map3.put("recordNumber",requestNo);//交易流水号	（必填）
						map3.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(map3);
						//更新款项
						//更新款项
						bpfund.setAfterMoney(bpfund.getIncomeMoney());
						bpfund.setFactDate(new Date());
						plMmOrderAssignInterestDao.save(bpfund);
						//如果某一投资人的某一次投资记录的款项全部对账完成，则改变该投资记录的状态为10
						List<PlMmOrderAssignInterest> plList= plMmOrderAssignInterestDao.getByDealCondition(bpfund.getOrderId(),bpfund.getInvestPersonId());
						if(plList!=null&&plList.size()>0){
						}else{
							PlManageMoneyPlanBuyinfo i=plManageMoneyPlanBuyinfoDao.get(bpfund.getOrderId());
							i.setState(Short.valueOf("10"));
							plManageMoneyPlanBuyinfoDao.save(i);
						}
					}
			}
		  }
		}
		
	}
	

	//内部类，使用新线程去处理招标项目成功后数据插入问题，使数据插入位于新的线程中，避免事务对数据保存带来的影响
	public class InvestSaveMethod   implements Runnable{

		private String bidIds;
		private String requestNos;
		

		public String getBidIds() {
			return bidIds;
		}

		public void setBidIds(String bidIds) {
			this.bidIds = bidIds;
		}

		public String getRequestNos() {
			return requestNos;
		}

		public void setRequestNos(String requestNos) {
			this.requestNos = requestNos;
		}

		public InvestSaveMethod (String bid,String req){
			this.bidIds=bid;
			this.requestNos=req;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			plBidInfoService.saveToERP(bidIds,requestNos);//投资人列表到erp 直接保存
		}
		
		
	}


	@Override
	public String[] advanceFinancialRepayment(Map<String, String> map) {
		// TODO Auto-generated method stub
		try{
			String[] ret=new String[2];
			String requestNo=map.get("requestNo");
			String dealMoney=map.get("dealMoney");
			String userId=map.get("userId");
			BigDecimal sumMoney=new BigDecimal(dealMoney);
			synchronized(requestNo){
				PlEarlyRedemption	plEarlyRedemption=plEarlyRedemptionDao.getByRequestNo(requestNo);
				if(null!=plEarlyRedemption){
					PlManageMoneyPlanBuyinfo order=plManageMoneyPlanBuyinfoDao.get(plEarlyRedemption.getOrderId());
					Map<String,Object> mapL=new HashMap<String,Object>();
					mapL.put("investPersonId",userId);//投资人Id（必填）
					mapL.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
					mapL.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
					mapL.put("money",sumMoney);//交易金额	（必填）			 
					mapL.put("dealDirection",ObAccountDealInfo.DIRECTION_PAY);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
					mapL.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
					mapL.put("recordNumber",requestNo+plEarlyRedemption.getEarlyRedemptionId()+"L");//交易流水号	（必填）
					mapL.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
					obAccountDealInfoService.operateAcountInfo(mapL);
					
					
					//投资人生成资金明细
					BpCustMember mem = bpCustMemberService.getByLoginName(plEarlyRedemption.getCreator());
					if(mem!=null){
						Map<String,Object> mapL1=new HashMap<String,Object>();
						mapL1.put("investPersonId",mem.getId());//投资人Id（必填）
						mapL1.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapL1.put("transferType",ObAccountDealInfo.T_LOANPAY);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapL1.put("money",sumMoney);//交易金额	（必填）			 
						mapL1.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapL1.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapL1.put("recordNumber",requestNo+plEarlyRedemption.getEarlyRedemptionId()+"L");//交易流水号	（必填）
						mapL1.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						obAccountDealInfoService.operateAcountInfo(mapL1);
					}
					
					
					Map<String,String> map1=new HashMap<String,String>();
					map1.put("requsetNo",requestNo);
					map1.put("sumMoney",sumMoney.toString());
					map1.put("earlyRedemptionId",plEarlyRedemption.getEarlyRedemptionId().toString());
					String[] set=new String[2];
					set = this.earlyEarlyRepayment(map1);
					
					if(set[0].equals(CommonResponse.RESPONSECODE_SUCCESS)){
						//把之后的派息全部划掉
						plMmOrderAssignInterestService.mmplanupdateList(plEarlyRedemption);
						//与债权的匹配日最大不能超过提前支取日
						plManageMoneyPlanBuyinfoService.gcalculateEarlyOutOrmatching(plEarlyRedemption);
						
						plEarlyRedemption.setCheckStatus(Short.valueOf("1"));
						plEarlyRedemptionService.save(plEarlyRedemption);
						
						order.setState(Short.valueOf("8"));
						order.setIsAtuoMatch(1);//修改订单为托管,定时器才会扫到该条记录并释放债权。
						plManageMoneyPlanBuyinfoService.save(order);
						//判断理财计划是否使用了优惠券。把未派发的奖励台账设置为无效的
						if(order.getCouponId()!=null&&!order.equals("")){
							List<PlMmOrderAssignInterest>	assign = plMmOrderAssignInterestService.getByPlanIdA(order.getOrderId(), order.getInvestPersonId(), order.getPlManageMoneyPlan().getMmplanId(), null,null);
							for(PlMmOrderAssignInterest pl:assign){
								pl.setIsValid(Short.valueOf("1"));
								pl.setIsCheck(Short.valueOf("1"));
								plMmOrderAssignInterestService.save(pl);
							}
						}
						
						//判断是否已经全部还款完毕   将状态设置为10
						QueryFilter filter =  new QueryFilter();
						filter.addFilter("Q_mmplanId_L_EQ", order.getPlManageMoneyPlan().getMmplanId());
						filter.addFilter("Q_isCheck_SN_EQ", (short)0);
						filter.addFilter("Q_isValid_SN_EQ", (short)0);
						List<PlMmOrderAssignInterest>	assign = plMmOrderAssignInterestService.getAll(filter);
						if(assign.size()>0){
							Boolean boo = false;
							for(PlMmOrderAssignInterest interest:assign){
								if(interest.getFactDate()==null){
									boo = true;
								}
							}
							if(!boo){
								PlManageMoneyPlan plan = plManageMoneyPlanService.get(order.getPlManageMoneyPlan().getMmplanId());
								plan.setState(10);
								plManageMoneyPlanService.merge(plan);
							}
						}
						
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="提前支取审核通过，已经支付了支取金额";
						logger.info("处理数据记录：requestNo="+requestNo+"; 描述："+ret[1]+";提前支取审核通过，已经支付了支取金额");
					}else{
						
						ret[0]=Constants.CODE_FAILED;
						ret[1]="提前支取审核通过，请进行返款操作";
						logger.info("款项业务已经被处理过：requestNo="+requestNo+";提前支取审核通过，请进行返款操作");
					}
				}
			}
			return ret;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String[] earlyEarlyRepayment(Map<String, String> map) {
		// TODO Auto-generated method stub
		String[] set=new String[2];
		String sumMoney = map.get("sumMoney");
		String requsetNo = map.get("requsetNo");
		String earlyRedemptionId = map.get("earlyRedemptionId");
		PlEarlyRedemption plEarlyRedemption = plEarlyRedemptionService.get(Long.valueOf(earlyRedemptionId));
		PlManageMoneyPlanBuyinfo order=plManageMoneyPlanBuyinfoService.get(plEarlyRedemption.getOrderId());
		List<PlMmOrderAssignInterest> pailist=plMmOrderAssignInterestService.mmplancreateList(plEarlyRedemption);
		
		ThirdPayRecord thirdPayRecord = thirdPayRecordService.getByOrderNo(requsetNo);
		if(null != thirdPayRecord && !"".equals(thirdPayRecord) && thirdPayRecord.getThirdPayConfig().equals("umpayConfig")){
			//保存新的款项台账
			for(PlMmOrderAssignInterest pi:pailist){
				pi.setLoanerRepayMentStatus(1);
				pi.setRequestNo(requsetNo);
				pi.setEarlyRedemptionId(plEarlyRedemption.getEarlyRedemptionId());
				plMmOrderAssignInterestService.save(pi);
			}
			plEarlyRedemption.setLoanerRepayMentStatus(1);
			set[0] = CommonResponse.RESPONSECODE_APPLAY;
			set[1] = "操作记录：提前赎回操作申请成功!";
		}else{
			
			//保存新的款项台账
			for(PlMmOrderAssignInterest pi:pailist){
				pi.setLoanerRepayMentStatus(2);
				pi.setRequestNo(requsetNo);
				pi.setEarlyRedemptionId(plEarlyRedemption.getEarlyRedemptionId());
				pi.setFactDate(new Date());
				pi.setIsCheck(Short.valueOf("0"));
				pi.setIsValid(Short.valueOf("0"));
				plMmOrderAssignInterestService.save(pi);
			}
			
			Map<String,Object> mapI=new HashMap<String,Object>();
			mapI.put("investPersonId",order.getInvestPersonId());//投资人Id（必填）
			mapI.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
			mapI.put("transferType",ObAccountDealInfo.T_PRINCIALBACK);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
			mapI.put("money",new BigDecimal(sumMoney));//交易金额	（必填）			 
			mapI.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
			mapI.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
			mapI.put("recordNumber",requsetNo+"TQSH");//交易流水号	（必填）
			mapI.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
			String[] ret =obAccountDealInfoService.operateAcountInfo(mapI);
			
			plEarlyRedemption.setLoanerRepayMentStatus(2);
			set[0] = CommonResponse.RESPONSECODE_SUCCESS;
			set[1] = "操作记录：提前赎回操作成功!";
		}
		plEarlyRedemptionService.save(plEarlyRedemption);
		return set;
	}
	/**
	 * 易宝2.0还款
	 */
	@Override
	public String[] yeepayRepayment(Map<String, String> map) {
		String[] set=new String[2];
		try {
			String requestNo=map.get("requestNo");
			//调用审核接口
			String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
			CommonRequst request = new CommonRequst();
			request.setRequsetNo(requestNum);
			request.setLoanNoList(requestNo);//审核流水号
			request.setConfimStatus(true);
			request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
			request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
			CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				repayment(map);
			}else{
				set[0] = CommonResponse.RESPONSECODE_FAILD;
				set[1] = "还款申请成功，审核失败!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}
	/**
	 * 易宝债权购买成功，调用审核接口
	 */
	@Override
	public String[] yeepayObligationDeal(Map<String, String> map) {
		String[] set=new String[2];
		try {
			String requestNo=map.get("requestNo");
			//调用审核接口
			String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
			CommonRequst request = new CommonRequst();
			request.setRequsetNo(requestNum);
			request.setLoanNoList(requestNo);//审核流水号
			request.setConfimStatus(true);
			request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
			request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
			CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				doObligationDeal(map);
			}else{
				set[0] = CommonResponse.RESPONSECODE_FAILD;
				set[1] = "债权购买申请成功，审核失败!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}
	/**
	 * 担保公司代偿，继续调用审核接口
	 */
	@Override
	public String[] yeepayCompensatory(Map<String, String> map) {
		String[] set=new String[2];
		try {
			String requestNo=map.get("requestNo");
			//调用审核接口
			String requestNum=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
			CommonRequst request = new CommonRequst();
			request.setRequsetNo(requestNum);
			request.setLoanNoList(requestNo);//审核流水号
			request.setConfimStatus(true);
			request.setBussinessType(ThirdPayConstants.BT_USEALLAUDIT);//业务类型
			request.setTransferName(ThirdPayConstants.TN_USEALLAUDIT);//名称
			CommonResponse response=ThirdPayInterfaceUtil.thirdCommon(request);
			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				compensatory(map);
			}else{
				set[0] = CommonResponse.RESPONSECODE_FAILD;
				set[1] = "担保公司代偿申请成功，审核失败!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

}


