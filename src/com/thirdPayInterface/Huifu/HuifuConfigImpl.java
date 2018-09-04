package com.thirdPayInterface.Huifu;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.thirdPayInterface.CommonRequestInvestRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.Huifu.HuifuUtil.MD5;
import com.thirdPayInterface.ThirdPayLog.model.ThirdPayRecord;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;
public class HuifuConfigImpl implements ThirdPayTypeInterface {
	static  BpFundIntentService bpFundIntentService = (BpFundIntentService) AppUtil.getBean("bpFundIntentService");
	static  PlBidInfoService plBidInfoService = (PlBidInfoService)AppUtil.getBean("plBidInfoService");
	static  BpCustMemberService bpCustMemberService = (BpCustMemberService)AppUtil.getBean("bpCustMemberService");
	static  PlMmOrderAssignInterestService plMmOrderAssignInterestService = (PlMmOrderAssignInterestService)AppUtil.getBean("plMmOrderAssignInterestService");
	static PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService  = (PlManageMoneyPlanBuyinfoService)AppUtil.getBean("plManageMoneyPlanBuyinfoService");
	/**
	 * 汇付天下
	 */ 
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
	    try{
	    	if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)){//个人开通第三方
	    		commonResponse=HuiFuInterfaceUtil.register(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKREIGSTER)){//登录汇付平台
	    		commonResponse=HuiFuInterfaceUtil.userLogin(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//投标接口
	    		commonRequst.setReqExt("");//请求扩展域
	    		commonRequst.setPageType("");//页面类型
	    		commonRequst.setIsFreeze("Y");//是否冻结
	    		commonRequst.setMaxTenderRate("0.50");//默认20%
	    		commonRequst.setBorrowerDetails("[{\"BorrowerCustId\":\""+commonRequst.getLoaner_thirdPayflagId().toString()+"\",\"BorrowerAmt\":\""+commonRequst.getAmount().setScale(2).toString()+"\",\"BorrowerRate\":\""+0.99+"\",\"ProId\":\""+CommonRequst.HRY_BID+""+commonRequst.getBidId()+"\"}]");
	    		commonResponse=HuiFuInterfaceUtil.biding(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)){//取消投标
	    		commonRequst.setMerPriv("");//商户私有域
	    		commonResponse=HuiFuInterfaceUtil.unFreeze(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//企业开通第三方
	    		commonResponse=HuiFuInterfaceUtil.registerenterprise(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHECKREIGSTER)){//企业户开户查询
	    		commonResponse=HuiFuInterfaceUtil.checkRegister(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		commonResponse=HuiFuInterfaceUtil.rechargeMoney(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//取现
	    		commonResponse=HuiFuInterfaceUtil.withdrawsMoney(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//散标自助还款
	    		List<CommonRequestInvestRecord> list =  commonRequst.getRepaymemntList();
	    		commonRequst.setFeeObjFlag(list.get(0).getFee().compareTo(new BigDecimal(0))>0?"O":"");//向出款账户收取
	    		commonRequst.setDzObject("");//垫资/代偿对象
	    		commonRequst.setReqExt("");//入参扩展域
	    		List<CommonResponse> list1 = new ArrayList();
	    		String periodId = commonRequst.getRemark1();//还款期数
	    		String bidId = commonRequst.getBidId();//标的号
	    		List<BpFundIntent> fund_list = bpFundIntentService.getListByBidIdAndPeriod(bidId, periodId);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		//按投资人将款项重新设置orderNo
	    		String requestNo = "";//
	    		BigDecimal sumMoney = BigDecimal.ZERO;
	    		for(CommonRequestInvestRecord record : list){
	    			//更新款项表
	    			QueryFilter filter = new QueryFilter();
	    			BpFundIntent fund = bpFundIntentService.get(record.getFundIntentId());
	    			filter.addFilter("Q_orderNo_S_EQ", fund.getOrderNo());
	    			filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(periodId));
	    			filter.getPagingBean().setPageSize(10000000);
	    			List<BpFundIntent> list2 = bpFundIntentService.getAll(filter);
	    			if(list2.size()>0){
	    				requestNo = ContextUtil.createRuestNumber();
	    				for(BpFundIntent intent: list2){
	    					if(intent.getFactDate()==null){
	    						intent.setRequestNo(requestNo);
	    						bpFundIntentService.merge(intent);
	    					}
	    				}
	    			}
	    			Properties props = new Properties();
					Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
					String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
					commonRequst.setRequsetNo(requestNo);

					//费用为0的时候，分账账户串填空
	    			commonRequst.setDivDetails(record.getFee().compareTo(new BigDecimal(0))>0?"[{\"DivCustId\":\""+plateFormNo.toString()+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+record.getFee().setScale(2).toString()+"\"}]":"");//,"borrowerCustId":"6000060002900106","borrowerRate":"1.00"}]//分账账户串
	    			commonRequst.setInterestAmt(record.getInterest().setScale(2).toString());//应还利息(利息+罚息)
	    			commonRequst.setPrincipalAmt(record.getPrincipal().setScale(2).toString());//应还本金(本金+罚息)
	    			commonRequst.setOutCustId(commonRequst.getThirdPayConfigId());//出账商户号
	    			commonRequst.setOutAcctId("");//出账客户号(默认空)
	    			commonRequst.setFee(record.getFee().setScale(2));//费用
	    			PlBidInfo info = plBidInfoService.getByOrdId(record.getBidRequestNo())!=null?plBidInfoService.getByOrdId(record.getBidRequestNo()):plBidInfoService.getByNewOrdId(record.getBidRequestNo());
	    			commonRequst.setInCustId(info.getNewInvestPersonId()!=null?bpCustMemberService.get(info.getNewInvestPersonId()).getThirdPayFlagId():record.getInvest_thirdPayConfigId());//入账商户号
	    			commonRequst.setInAcctId("");//(默认空)
	    			commonRequst.setSubOrdDate(sdf.format(info.getBidtime()));//订单日期
	    			commonRequst.setSubOrdId(info.getNewOrderNo()!=null&&!"".equals(info.getNewOrderNo())?info.getNewOrderNo():info.getOrderNo());//订单号
	    			CommonResponse response = commonResponse=HuiFuInterfaceUtil.repayment(commonRequst);
	    			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//成功则添加到列表中
	    				response.setRequestNo(requestNo);
	    				list1.add(response);
	    			}
	    			sumMoney=sumMoney.add(record.getAmount());
	    		}
	    		commonRequst.setRequsetNo(oldRequestNo);
	    		commonResponse.setRemark(sumMoney.toString());
	    		commonResponse.setResponseList(list1);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEBID)){//更新标的状态
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE)){//平台收费默认成功
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//提前还款
	    		List<CommonRequestInvestRecord> list =  commonRequst.getRepaymemntList();
	    		List<CommonResponse> list1 = new ArrayList();
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		//按投资人将款项重新设置orderNo
	    		String requestNo = "";//
	    		for(CommonRequestInvestRecord record : list){
	    			//更新款项表
	    			QueryFilter filter = new QueryFilter();
	    			BpFundIntent fund = bpFundIntentService.get(record.getFundIntentId());
	    			filter.addFilter("Q_orderNo_S_EQ", fund.getOrderNo());
	    			filter.getPagingBean().setPageSize(10000000);
	    			List<BpFundIntent> list2 = bpFundIntentService.getAll(filter);
	    			if(list2.size()>0){
	    				requestNo = ContextUtil.createRuestNumber();
	    				for(BpFundIntent intent: list2){
	    					if(intent.getFactDate()==null&&intent.getSlEarlyRepaymentId()!=null){
	    						intent.setRequestNo(requestNo);
	    						bpFundIntentService.save(intent);
	    					}
	    				}
	    			}
	    			Properties props = new Properties();
					Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
					String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
	    			commonRequst.setRequsetNo(requestNo);
	    			commonRequst.setInterestAmt(record.getInterest().setScale(2).toString());//应还利息(利息+罚息)
	    			commonRequst.setOutCustId(commonRequst.getThirdPayConfigId());//出账商户号
	    			commonRequst.setOutAcctId("");//出账客户号(默认空)
	    			commonRequst.setFee(record.getFee().setScale(2));//费用
	    			commonRequst.setDivDetails(commonRequst.getFee().compareTo(new BigDecimal(0))>0?"[{\"DivCustId\":\""+plateFormNo.toString()+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+record.getFee()+"\"}]":"");//,"borrowerCustId":"6000060002900106","borrowerRate":"1.00"}]//分账账户串
	    			commonRequst.setInCustId(record.getInvest_thirdPayConfigId());//入账商户号
	    			commonRequst.setInAcctId("");//(默认空)
	    			PlBidInfo info = plBidInfoService.getByOrdId(record.getBidRequestNo());
	    			if(info==null){
	    				info = plBidInfoService.getByNewOrdId(record.getBidRequestNo());
	    			}
	    			commonRequst.setPrincipalAmt(record.getPrincipal().setScale(2).toString());//应还本金
	    			commonRequst.setSubOrdDate(sdf.format(info.getBidtime()));//订单日期
	    			commonRequst.setSubOrdId(info.getNewOrderNo()!=null?info.getNewOrderNo():info.getOrderNo());//订单号
	    			CommonResponse response = commonResponse=HuiFuInterfaceUtil.repayment(commonRequst);
	    			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//成功则添加到列表中
	    				response.setRequestNo(requestNo);
	    				response.setRemark(record.getAmount().toString());
	    				list1.add(response);
	    			}
	    		}
	    		commonRequst.setRequsetNo(oldRequestNo);
	    		commonResponse.setResponseList(list1);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACEMONEY)){//代偿还款申请
	    		commonRequst.setBidType(CommonRequst.HRY_HRB);
	    		commonResponse=HuiFuInterfaceUtil.usrAcctPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//发红包
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券派发奖励
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请 
	    		commonResponse=HuiFuInterfaceUtil.usrAcctPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//挂牌交易
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=HuiFuInterfaceUtil.usrAcctPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=HuiFuInterfaceUtil.CreditAssign(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
	    		commonRequst.setBidType(CommonRequst.HRY_SALE);
	    		commonResponse=HuiFuInterfaceUtil.cancelDeal(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
	    		commonRequst.setAction(2);
	    		commonResponse=HuiFuInterfaceUtil.bindCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARD)){//取消绑定银行卡  
	    		commonResponse=HuiFuInterfaceUtil.delCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BALANCEQUERY)){//余额查询 
	    		commonResponse=HuiFuInterfaceUtil.queryBalanceBg(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//银行卡信息查询 
	    		commonResponse=HuiFuInterfaceUtil.queryCardInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	    		if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){//充值
	    			commonResponse=HuiFuInterfaceUtil.queryTransStat(commonRequst);
	    		}else if (commonRequst.getQueryType().equals("RECHARGE_RECORD")){//取现
	    			commonResponse=HuiFuInterfaceUtil.queryTransDetail(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//发红包
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券派发奖励
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请 
	    		commonResponse=HuiFuInterfaceUtil.usrAcctPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    		commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
	    		commonRequst.setAction(2);
	    		commonResponse=HuiFuInterfaceUtil.bindCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BALANCEQUERY)){//余额查询 
	    		commonResponse=HuiFuInterfaceUtil.queryBalanceBg(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//银行卡信息查询 
	    		commonResponse=HuiFuInterfaceUtil.queryCardInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//平台会员交易查询(充值、取现、还款、放款、转账)
	    		if(commonRequst.getQueryType().equals("WITHDRAW_RECORD")){//充值
	    			commonResponse=HuiFuInterfaceUtil.queryTransStat(commonRequst);
	    		}else if (commonRequst.getQueryType().equals("RECHARGE_RECORD")){//取现
	    			commonResponse=HuiFuInterfaceUtil.queryTransDetail(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//发红包
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券派发奖励
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)){//互融宝转入申请 
	    		commonResponse=HuiFuInterfaceUtil.usrAcctPay(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转出
	    		commonResponse=HuiFuInterfaceUtil.transfer(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//理财计划购买(收款账户为用户)
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonRequst.setIsFreeze("Y");//是否冻结
	    		commonRequst.setReqExt("");//请求扩展域
	    		commonRequst.setPageType("");//页面类型
	    		//借款人信息
	    		commonRequst.setBorrowerDetails("[{\"BorrowerCustId\":\"" +
	    										""+commonRequst.getLoaner_thirdPayflagId().toString()
	    										+"\",\"BorrowerAmt\":\""+commonRequst.getAmount().setScale(2).toString()
	    										+"\",\"BorrowerRate\":\""+0.99+"\",\"ProId\":\""
	    										+commonRequst.getBidType()+commonRequst.getBidId()+"\"}]");
	    		commonResponse=HuiFuInterfaceUtil.bidingPlmanage(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)){//理财计划起息(收款账户为用户)
	    		//理财计划起息(收款账户为用户)
	    		String unFreezeNo = ContextUtil.createRuestNumber();
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		commonRequst.setOrderDate(sdf.format(new Date()));
	    		Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
				String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
				if(commonRequst.getFee()!=null&&commonRequst.getFee().compareTo(new BigDecimal(0))>0){
					commonRequst.setDivDetails("[{\"DivCustId\":\""+plateFormNo+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+commonRequst.getFee()+"\"}]");
					commonRequst.setFeeObjFlag("I");//根据费用是否为0进行判断  (I:向入款账户收费 O:向出款账户收费)默认向借款人收取
				}else{
					commonRequst.setDivDetails("");
					commonRequst.setFeeObjFlag("");//根据费用是否为0进行判断  (I:向入款账户收费 O:向出款账户收费)默认向借款人收取
				}	 
				commonRequst.setReqExt("{\"LoansVocherAmt\":\""+""+"\",\"ProId\":\""+commonRequst.getBidType()+commonRequst.getBidId()+"\"}");//用户扩展域
				commonRequst.setIsDefault("N");//是否默认Y--默认添加资金池:这部分资金需要商户调用商户代取现接口，帮助用户做后台取现动作N--不默认不添加资金池:这部分资金用户可以自己取现
				commonRequst.setIsUnFreeze("Y");//是否解冻
				commonRequst.setUnFreezeOrdId(unFreezeNo);//解冻订单号
	    		commonResponse=HuiFuInterfaceUtil.bidLoan(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMBACKMONEY)){//UD计划理财专户为投资人还款
	    		commonRequst.setBidType(CommonRequst.HRY_PLANBUY);
	    		commonRequst.setFeeObjFlag(commonRequst.getFee().compareTo(new BigDecimal(0))>0?"O":"");//向出款账户收取
	    		commonRequst.setDzObject("");//垫资/代偿对象
	    		commonRequst.setReqExt("");//入参扩展域
	    		List<CommonRequestInvestRecord> list =  commonRequst.getRepaymemntList();
	    		List<CommonResponse> list1 = new ArrayList();
	    		String periodId = commonRequst.getRemark1();//还款期数
	    		String bidId = commonRequst.getBidId();//标的号
	    		Date intentDate = commonRequst.getIntentDate();
	    		List<PlMmOrderAssignInterest> fund_list = plMmOrderAssignInterestService.getListByBidIdAndPeriod(bidId, periodId,intentDate); //plMmOrderAssignInterestService.getListByBidIdAndPeriod(bidId, periodId);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		//按投资人将款项重新设置orderNo
	    		String requestNo = "";//
	    		BigDecimal accMoney = BigDecimal.ZERO;
	    		BigDecimal sumMoney = BigDecimal.ZERO;
	    		for(CommonRequestInvestRecord record : list){
	    			//更新款项表
	    			accMoney = BigDecimal.ZERO;
	    			QueryFilter filter = new QueryFilter();
	    			PlMmOrderAssignInterest fund = plMmOrderAssignInterestService.get(record.getAssignInterestId());
	    			filter.addFilter("Q_requestNo_S_EQ", fund.getRequestNo());
	    			filter.addFilter("Q_intentDate_D_EQ", intentDate);
	    			filter.getPagingBean().setPageSize(10000000);
	    			List<PlMmOrderAssignInterest> list2 = plMmOrderAssignInterestService.getAll(filter);
	    			if(list2.size()>0){
	    				requestNo = ContextUtil.createRuestNumber();
	    				for(PlMmOrderAssignInterest intent: list2){
	    					if(intent.getFactDate()==null){
	    						intent.setInvestRequestNo(requestNo);
								plMmOrderAssignInterestService.merge(intent);
	    					}
	    				}
	    			}
	    			Properties props = new Properties();
					Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
					String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
					commonRequst.setRequsetNo(requestNo);
	    			commonRequst.setDivDetails(record.getFee().compareTo(new BigDecimal(0))>0?"[{\"DivCustId\":\""+plateFormNo.toString()+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+record.getFee()+"\"}]":"");//,"borrowerCustId":"6000060002900106","borrowerRate":"1.00"}]//分账账户串
	    			commonRequst.setInterestAmt(record.getInterest().compareTo(new BigDecimal(0))>0?record.getInterest().add(accMoney).setScale(2).toString():new BigDecimal(0).setScale(2).toString());//应还利息(利息+罚息)
	    			commonRequst.setPrincipalAmt(record.getPrincipal().setScale(2).toString());//应还本金
	    			commonRequst.setOutCustId(commonRequst.getThirdPayConfigId());//出账商户号
	    			commonRequst.setOutAcctId("");//出账客户号(默认空)
	    			commonRequst.setFee(record.getFee().setScale(2));//费用
	    			PlManageMoneyPlanBuyinfo info1 = plManageMoneyPlanBuyinfoService.getOrderNumber(record.getBidRequestNo());
	    			commonRequst.setInCustId(String.valueOf(bpCustMemberService.get(info1.getInvestPersonId()).getThirdPayFlagId()));//入账商户号
	    			commonRequst.setInAcctId("");//(默认空)
	    			commonRequst.setSubOrdDate(sdf.format(info1.getBuyDatetime()));//订单日期
	    			commonRequst.setSubOrdId(info1.getDealInfoNumber());//订单号
	    			CommonResponse response = commonResponse=HuiFuInterfaceUtil.repayment(commonRequst);
	    			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//成功则添加到列表中
	    				response.setRequestNo(requestNo);
	    				list1.add(response);
	    			}
	    			sumMoney=sumMoney.add(record.getAmount());
	    		}
	    		commonResponse.setRemark(sumMoney.toString());
	    		commonResponse.setResponseList(list1);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COMPENSATORY)){  //担保代偿接口
	    		commonRequst.setFeeObjFlag("");//向出款账户收取
	    	//	commonRequst.setDzObject("");//垫资/代偿对象
	    		commonRequst.setReqExt("");//入参扩展域
	    		List<CommonRequestInvestRecord> list =  commonRequst.getRepaymemntList();
	    		List<CommonResponse> list1 = new ArrayList();
	    		Date periodId = commonRequst.getIntentDate();//还款期数
	    		String bidId = commonRequst.getBidId();//标的号
	    //		List<BpFundIntent> fund_list = bpFundIntentService.getListByBidIdAndIntent(bidId, periodId);
	    		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	    	//	List<BpFundIntent> fund_list = bpFundIntentService.getListByBidIdAndIntent(bidId, periodId);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		//按投资人将款项重新设置orderNo
	    		String requestNo = "";//
	    		BigDecimal accMoney = BigDecimal.ZERO;
	    		BigDecimal sumMoney = BigDecimal.ZERO;
	    		for(CommonRequestInvestRecord record : list){
	    			//更新款项表
	    			accMoney = BigDecimal.ZERO;
	    			QueryFilter filter = new QueryFilter();
	    			BpFundIntent fund = bpFundIntentService.get(record.getFundIntentId());
	    			filter.addFilter("Q_orderNo_S_EQ", fund.getOrderNo());
	    			filter.addFilter("Q_intentDate_D_EQ", periodId);
	    			filter.getPagingBean().setPageSize(10000000);
	    			List<BpFundIntent> list2 = bpFundIntentService.getAll(filter);
	    			if(list2.size()>0){
	    				requestNo = ContextUtil.createRuestNumber();
	    				for(BpFundIntent intent: list2){
	    					if(intent.getFactDate()==null){
	    						if(intent.getAccrualMoney()!=null&&intent.getAccrualMoney().compareTo(new BigDecimal(0))>0){
	    							accMoney = accMoney.add(intent.getAccrualMoney());	
	    						}
	    						intent.setRequestNo(requestNo);
	    						bpFundIntentService.merge(intent);
	    					}
	    				}
	    			}
	    			Properties props = new Properties();
					Map thirdMap=HuiFuInterfaceUtil.HuifuProperty();
					String plateFormNo = thirdMap.get("thirdPay_Huifu_platNumber").toString(); 
					commonRequst.setRequsetNo(requestNo);
	    			commonRequst.setDivDetails("[{\"DivCustId\":\""+plateFormNo.toString()+"\",\"DivAcctId\":\""+"MDT000001"+"\",\"DivAmt\":\""+record.getFee()+"\"}]");//,"borrowerCustId":"6000060002900106","borrowerRate":"1.00"}]//分账账户串
	    			commonRequst.setInterestAmt(record.getInterest().compareTo(new BigDecimal(0))>0?record.getInterest().add(accMoney).setScale(2).toString():new BigDecimal(0).setScale(2).toString());//应还利息(利息+罚息)
	    			commonRequst.setPrincipalAmt(record.getPrincipal().setScale(2).toString());//应还本金
	    			//commonRequst.setOutCustId(commonRequst.getOutCustId());//出账商户号
	    			
	    			commonRequst.setOutAcctId("MDT000001");//出账客户号(默认空)
	    			commonRequst.setFee(record.getFee().setScale(2));//费用
	    			PlBidInfo info = plBidInfoService.getByOrdId(record.getBidRequestNo())!=null?plBidInfoService.getByOrdId(record.getBidRequestNo()):plBidInfoService.getByNewOrdId(record.getBidRequestNo());
	    			commonRequst.setInCustId(info.getNewInvestPersonId()!=null?bpCustMemberService.get(info.getNewInvestPersonId()).getThirdPayFlagId():record.getInvest_thirdPayConfigId());//入账商户号
	    			commonRequst.setInAcctId("");//(默认空)
	    			//commonRequst.setDzObject(commonRequst.getOutCustId());
	    			commonRequst.setSubOrdDate(sdf.format(info.getBidtime()));//订单日期
	    			commonRequst.setSubOrdId(info.getNewOrderNo()!=null?info.getNewOrderNo():info.getOrderNo());//订单号
	    			CommonResponse response = commonResponse=HuiFuInterfaceUtil.repayment(commonRequst);
	    			if(response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){//成功则添加到列表中
	    				response.setRequestNo(requestNo);
	    				list1.add(response);
	    			}
	    			sumMoney=sumMoney.add(record.getAmount());
	    		}
	    		commonResponse.setRemark(sumMoney.toString());
	    		commonResponse.setResponseList(list1);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMADVANCEUSER)){ //我的债权提前赎回审核
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    		commonRequst.setProId(CommonRequst.HRY_PLANBUY+commonRequst.getBidId());
	    		commonRequst.setOrderDate(sdf.format(new Date()));
	    		commonResponse=HuiFuInterfaceUtil.repayment(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){
	    		commonResponse=HuiFuInterfaceUtil.openBidAuto(commonRequst);
	    		//commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		//commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){
	    		commonResponse=HuiFuInterfaceUtil.closeBidAuto(commonRequst);
	    		//commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    		//commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		//commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE_B)){
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else {
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTBUSINESS);
		    	commonResponse.setResponseMsg("没有该业务类型");
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	commonResponse.setResponseMsg("系统报错");
	    }
		return commonResponse;
	}
	
	/**
	 * 解析回调
	 */
	@Override
	public CommonResponse businessReturn(Map map,HttpServletRequest request) {
		request.getSession().setAttribute("retUrl", "user/getBpCustMember.do");
		CommonResponse commonResponse = new CommonResponse();
		//注： 如果是单一业务可以用 cmdId消息类型判断业务类型、
		//    如果是一个消息类型处理多个业务 和 请求的流水号一起判断业务类型
		
		//得到消息类型判断系统业务
		String cmdId = "";
		if(map.containsKey("CmdId")){
			cmdId = map.get("CmdId").toString();
		}
		//得到请求的流水号
		String requestNo = "";
		if(map.containsKey("MerPriv")){
			requestNo = map.get("MerPriv").toString();
		}else{
			requestNo="0000";
		}
		//如果流水号不为空就可判断业务类型
		ThirdPayRecord thirdPayRecord=null;
		if(!requestNo.equals("")){
			ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
			thirdPayRecord = thirdPayRecordService.getByOrderNo(requestNo);
		}
		if(!cmdId.equals("")){ 
			if(cmdId.equals("UserRegister")||cmdId.equals("CorpRegister")){//注册
				String merData = 	map.get("CmdId").toString().trim()+
									map.get("RespCode").toString().trim()+
									map.get("MerCustId").toString().trim()+
									map.get("UsrId").toString().trim()+
									map.get("UsrCustId").toString().trim()+
									map.get("BgRetUrl").toString().trim()+
									map.get("TrxId").toString().trim()+
									map.get("RetUrl").toString().trim()+
									map.get("MerPriv").toString().trim();
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString().trim());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						commonResponse.setThirdPayConfigId(map.get("UsrCustId").toString());
	    				commonResponse.setThirdPayConfigId0(map.get("UsrCustId").toString());
	    				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
				commonResponse.setRequestNo(requestNo);
				commonResponse.setRequestInfo(map.toString());
				commonResponse.setResponseMsg(map.get("RespDesc").toString());
			}else if(cmdId.equals("NetSave")){//充值
				String merData = 	map.get("CmdId").toString().trim()+
									map.get("RespCode").toString().trim()+
									map.get("MerCustId").toString().trim()+
									map.get("UsrCustId").toString().trim()+
									map.get("OrdId").toString().trim()+
									map.get("OrdDate").toString().trim()+
									map.get("TransAmt").toString().trim()+
									map.get("TrxId").toString().trim()+
									map.get("RetUrl").toString().trim()+
									map.get("BgRetUrl").toString().trim()+
									map.get("MerPriv").toString().trim();
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						if(map.get("GateBusiId").toString().equals("QP")){//快捷支付
							commonResponse.setBankCode(map.get("CardId").toString());
							commonResponse.setBankCardNumber(map.get("GateBankId").toString());
						}
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
				commonResponse.setRequestNo(requestNo);
				commonResponse.setRequestInfo(map.toString());
				commonResponse.setResponseMsg(map.get("RespDesc").toString());
			}else if(cmdId.equals("Cash")){//取现
				String merData="";
				try {
					merData = map.get("CmdId").toString().trim()+
										map.get("RespCode").toString().trim()+
										map.get("MerCustId").toString().trim()+
										map.get("OrdId").toString().trim()+
										map.get("UsrCustId").toString().trim()+
										map.get("TransAmt").toString().trim()+
										map.get("OpenAcctId").toString().trim()+
										map.get("OpenBankId").toString().trim()+
										map.get("FeeAmt")+
										map.get("FeeCustId")+
										map.get("FeeAcctId")+
										map.get("ServFee")+
										map.get("ServFeeAcctId")+
										URLDecoder.decode(map.get("RetUrl").toString().trim(),"UTF-8")+
										URLDecoder.decode(map.get("BgRetUrl").toString().trim(),"UTF-8")+
										map.get("MerPriv").toString().trim()+
										map.get("RespExt").toString();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						commonResponse.setThirdPayConfigId(map.get("UsrCustId").toString());
						commonResponse.setThirdPayConfigId0(map.get("UsrCustId").toString());
						commonResponse.setFeeUser(new BigDecimal(map.get("FeeAmt").toString()));
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
					commonResponse.setRequestNo(requestNo);
					commonResponse.setRequestInfo(map.toString());
					try {
						commonResponse.setResponseMsg(URLDecoder.decode(map.get("RespDesc").toString().trim(),"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}else if(cmdId.equals("InitiativeTender")){//投标、理财计划购买
					String merData;
					try {
						merData = map.get("CmdId").toString().trim()
										+ map.get("RespCode").toString().trim()
										+ map.get("MerCustId").toString().trim()
										+ map.get("OrdId").toString().trim()
										+ map.get("OrdDate").toString().trim()
										+ map.get("TransAmt").toString().trim()
										+ map.get("UsrCustId").toString().trim()
										+ map.get("TrxId").toString().trim()
										+ map.get("IsFreeze").toString().trim()
										+ map.get("FreezeOrdId").toString().trim()
										+ map.get("FreezeTrxId").toString().trim()
										+ URLDecoder.decode(map.get("RetUrl").toString().trim(),"UTF-8")
										+ URLDecoder.decode(map.get("BgRetUrl").toString().trim(),"UTF-8")
										+ map.get("MerPriv").toString().trim()
										+ map.get("RespExt").toString().trim();
						boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString().trim());
						if(flag){
							if(map.get("RespCode").toString().equals("000")){
								String RespDesc = map.get("RespDesc").toString();
						        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
						        Matcher m=p.matcher(RespDesc); 
						        if(!m.find()){ 
						        	commonResponse.setThirdPayConfigId(map.get("UsrCustId").toString());
						        	commonResponse.setThirdPayConfigId0(map.get("UsrCustId").toString());
						        	commonResponse.setRequestNo(map.get("OrdId").toString());
						        	commonResponse.setRemark2(map.get("TrxId").toString());
						        	commonResponse.setRemark1(map.get("FreezeTrxId").toString());
						        	commonResponse.setLoanNo(map.get("FreezeTrxId").toString());
						        	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						        }else{
						        	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						        }
							}else{
								commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
							}
						}else{
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						commonResponse.setRequestNo(requestNo);
						commonResponse.setRequestInfo(map.toString());
						try {
							commonResponse.setResponseMsg(URLDecoder.decode(map.get("RespDesc").toString().trim(),"UTF-8"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}else if(cmdId.equals("UserBindCard")){//绑定银行卡
				String merData = 	map.get("CmdId").toString().trim()+
									map.get("RespCode").toString().trim()+
									map.get("MerCustId").toString().trim()+
									map.get("OpenAcctId").toString().trim()+
									map.get("OpenBankId").toString().trim()+
									map.get("UsrCustId").toString().trim()+
									map.get("TrxId").toString().trim()+
									map.get("BgRetUrl").toString().trim()+
									map.get("MerPriv").toString().trim();
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						commonResponse.setBankCode(map.get("OpenAcctId").toString());
						commonResponse.setBankCardNumber(map.get("OpenBankId").toString());
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
				commonResponse.setRequestNo(requestNo);
				commonResponse.setRequestInfo(map.toString());
				commonResponse.setResponseMsg(map.get("RespDesc").toString());
			}else if(cmdId.equals("PnrUsrUnBindExpressCard")){//解绑快捷卡
				String merData = 	map.get("CmdId").toString().trim()+
									map.get("RespCode").toString().trim()+
									map.get("MerCustId").toString().trim()+
									map.get("CustId").toString().trim()+
									map.get("TrxId").toString().trim()+
									map.get("BankId").toString().trim()+
									map.get("CardId").toString().trim()+
									map.get("ExpressFlag").toString().trim()+
									map.get("BgRetUrl").toString().trim();
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						commonResponse.setBankCode(map.get("CardId").toString());
						commonResponse.setBankCardNumber(map.get("BankId").toString());
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
					commonResponse.setRequestNo(requestNo);
					commonResponse.setRequestInfo(map.toString());
					commonResponse.setResponseMsg(map.get("RespDesc").toString());
			}else if(cmdId.equals("UsrAcctPay")){//用户账户支付
				String merData = 	map.get("CmdId").toString().trim()+
									map.get("RespCode").toString().trim()+
									map.get("OrdId").toString().trim()+
									map.get("UsrCustId").toString().trim()+
									map.get("MerCustId").toString().trim()+
									map.get("TransAmt").toString().trim()+
									map.get("InAcctId").toString().trim()+
									map.get("InAcctType").toString().trim()+
									map.get("RetUrl").toString().trim()+
									map.get("BgRetUrl").toString().trim()+
									map.get("MerPriv").toString().trim();
				boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
				if(flag){
					if(map.get("RespCode").toString().equals("000")){
						commonResponse.setDealState(CommonResponse.DEALSTATE2);
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
					}else{
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
					}
				}else{
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
				}
					commonResponse.setRequestNo(requestNo);
					commonResponse.setRequestInfo(map.toString());
					commonResponse.setResponseMsg(map.get("RespDesc").toString());
			}else if(cmdId.equals("CreditAssign")){//购买债权
/*				CmdId + RespCode + MerCustId +
				SellCustId + CreditAmt + CreditDealAmt +
				Fee + BuyCustId + OrdId + OrdDate +
				RetUrl + BgRetUrl + MerPriv+ RespExt +
				LcId + TotalLcAmt
*/				String merData;
				try {
					merData = map.get("CmdId").toString().trim()
							+ map.get("RespCode").toString().trim()
							+ map.get("MerCustId").toString().trim()
							+ map.get("SellCustId").toString().trim()
							+ map.get("CreditAmt").toString().trim()
							+ map.get("CreditDealAmt").toString().trim()
							+ map.get("Fee").toString().trim()
							+ map.get("BuyCustId").toString().trim()
							+ map.get("OrdId").toString().trim()
							+ map.get("OrdDate").toString().trim()
							+ URLDecoder.decode(map.get("RetUrl").toString()
									.trim(), "UTF-8")
							+ URLDecoder.decode(map.get("BgRetUrl").toString()
									.trim(), "UTF-8")
							+ map.get("MerPriv").toString().trim()
							+ map.get("RespExt").toString().trim()
							+ map.get("LcId").toString().trim()
							+ map.get("TotalLcAmt").toString().trim();

					MD5 md5 = new MD5();
					boolean flag = HuiFuInterfaceUtil.verifyChkValue(md5.md5(
							merData, "utf-8"), map.get("ChkValue").toString());
					if (flag) {
						if (map.get("RespCode").toString().equals("000")) {
							commonResponse.setDealState(CommonResponse.DEALSTATE2);
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						} else {
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						}
					} else {
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
					}
					commonResponse.setRequestNo(map.get("OrdId").toString().trim());
					commonResponse.setRequestInfo(map.toString());
					commonResponse.setResponseMsg(URLDecoder.decode(map.get("RespDesc").toString(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(cmdId.equals("AutoTenderPlan")){
//////
				String merData;
				try {
					merData = map.get("CmdId").toString().trim()
							+ map.get("RespCode").toString().trim()
							+ map.get("MerCustId").toString().trim()
							+ map.get("UsrCustId").toString().trim()
							+ map.get("TenderPlanType").toString().trim()
							+ map.get("TransAmt").toString().trim()
							+ map.get("RetUrl").toString().trim()
							+ map.get("MerPriv").toString().trim();

					MD5 md5 = new MD5();
					boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
					/*boolean flag = HuiFuInterfaceUtil.verifyChkValue(md5.md5(
							merData, "utf-8"), map.get("ChkValue").toString());
*/					if (flag) {
						if (map.get("RespCode").toString().equals("000")) {
							
							//commonResponse.setDealState(CommonResponse.DEALSTATE2);
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						} else {
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						}
					} else {
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
					}
					commonResponse.setRequestNo(map.get("MerPriv").toString().trim());
					/*commonResponse.setRequestInfo(map.toString());*/
					commonResponse.setResponseMsg(URLDecoder.decode(map.get("RespDesc").toString(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
/////
				
			}else if(cmdId.equals("AutoTenderPlanClose")){
				String merData;
				try {
					/*CmdId + RespCode + MerCustId + UsrCustId +
					RetUrl + MerPriv*/
					merData = map.get("CmdId").toString().trim()
							+ map.get("RespCode").toString().trim()
							+ map.get("MerCustId").toString().trim()
							+ map.get("UsrCustId").toString().trim()
							+ map.get("RetUrl").toString().trim()
							+ map.get("MerPriv").toString().trim();
					MD5 md5 = new MD5();
					boolean flag=HuiFuInterfaceUtil.verifyChkValue(merData,map.get("ChkValue").toString());
					/*boolean flag = HuiFuInterfaceUtil.verifyChkValue(md5.md5(
							merData, "utf-8"), map.get("ChkValue").toString());
*/					if (flag) {
						if (map.get("RespCode").toString().equals("000")) {
							
							//commonResponse.setDealState(CommonResponse.DEALSTATE2);
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
						} else {
							commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
						}
					} else {
						commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
					}
					commonResponse.setRequestNo(map.get("MerPriv").toString().trim());
					/*commonResponse.setRequestInfo(map.toString());*/
					commonResponse.setResponseMsg(URLDecoder.decode(map.get("RespDesc").toString(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			return commonResponse;
	}
	public void gettest(){}

	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] ret=new Object[2];
		String type="NoPage";
		if(businessType.equals(ThirdPayConstants.BT_RECHAGE)){//充值
			type="NoPage";
    	}else if(businessType.equals(ThirdPayConstants.BT_BINDCARD)){//绑定银行卡
    		type="NoPage";
    		ret[1]="all";
    	}
		ret[0]=type;
		
		return ret;
	}

}
