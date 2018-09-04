package com.hurong.credit.service.p2p.redMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.dao.p2p.redMoney.BpCustRedEnvelopeDao;
import com.hurong.credit.dao.p2p.redMoney.BpCustRedMemberDao;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.p2p.redMoney.BpCustRedEnvelope;
import com.hurong.credit.model.p2p.redMoney.BpCustRedMember;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.p2p.redMoney.BpCustRedMemberService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

/**
 * 
 * @author 
 *
 */
public class BpCustRedMemberServiceImpl extends BaseServiceImpl<BpCustRedMember> implements BpCustRedMemberService{
	@SuppressWarnings("unused")
	private BpCustRedMemberDao dao;
	public BpCustRedMemberServiceImpl(BpCustRedMemberDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	@Resource
	private BpCustRedEnvelopeDao bpCustRedEnvelopeDao;
	
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;


	@Override
	public List<BpCustRedMember> getActivityNumber(String activityNumber,
			String bpCustMemberId, String remark) {
		// TODO Auto-generated method stub
		return dao.getActivityNumber(activityNumber, bpCustMemberId, remark);
	}


	@Override
	public BpCustRedMember saveByBpActivityManage(Long userId,BigDecimal redMoney, BpActivityManage bpActivityManage) {
		System.out.println("userId="+userId);
		String activityTypeValue = "";
		if(bpActivityManage.getSendType()==1){activityTypeValue="注册";}
		if(bpActivityManage.getSendType()==2){activityTypeValue="邀请";}
		if(bpActivityManage.getSendType()==3){activityTypeValue="投标";}
		if(bpActivityManage.getSendType()==4){activityTypeValue="充值";}
		if(bpActivityManage.getSendType()==5){activityTypeValue="邀请好友第一次投标";}
		
		//红包表
		BpCustRedEnvelope bpCustRedEnvelope = new BpCustRedEnvelope();
		bpCustRedEnvelope.setName("红包_"+activityTypeValue);
		bpCustRedEnvelope.setDistributestatus(Short.valueOf("0"));
		bpCustRedEnvelope.setDistributemoney(redMoney);
		bpCustRedEnvelope.setDistributecount(1);
		bpCustRedEnvelope.setDistributeTime(new Date());
		bpCustRedEnvelope.setRemarks("红包_"+activityTypeValue);
		bpCustRedEnvelope.setCreateTime(new Date());//创建时间
		//保存
		BpCustRedEnvelope _bpCustRedEnvelope = bpCustRedEnvelopeDao.save(bpCustRedEnvelope);
		//红包明细表
		BpCustRedMember bpCustRedMember = new BpCustRedMember();
		bpCustRedMember.setActivityNumber(bpActivityManage.getActivityNumber());//活动编号
		bpCustRedMember.setBpCustMemberId(Long.valueOf(userId));
		bpCustRedMember.setRedMoney(redMoney);
		bpCustRedMember.setEdredMoney(new BigDecimal("0"));
		bpCustRedMember.setRedType("bpActivityManage");
		bpCustRedMember.setRedId(_bpCustRedEnvelope.getRedId());
		bpCustRedMember.setDescription(bpActivityManage.getActivityExplain());
		bpCustRedMember.setSendType(bpActivityManage.getSendType().toString());
		bpCustRedMember.setIntention(bpActivityManage.findSendType());
		//保存
		BpCustRedMember save = dao.save(bpCustRedMember);
		
		//保存后调第三方---------------
		try {
			System.out.println("充值成功,,,发送红包-");
			HttpServletRequest request = ServletActionContext.getRequest();
			String path= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			BpCustMember mem = bpCustMemberService.get(userId);
			String requestNo=Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmssSSS");
			CommonRequst commonRequest = new CommonRequst();
			commonRequest.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方标识
			commonRequest.setRequsetNo(requestNo);//请求流水号
			commonRequest.setAmount(redMoney);//交易金额
			if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
				commonRequest.setAccountType(1);
			}else{//借款人是个人
				commonRequest.setAccountType(0);
			}
			commonRequest.setCustMemberType("0");
			commonRequest.setBidId(bpCustRedMember.getRedTopersonId().toString());
			commonRequest.setBussinessType(ThirdPayConstants.BT_SEDRED);//业务类型
			commonRequest.setTransferName(ThirdPayConstants.TN_SEDRED);//业务名称
			CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequest);
			System.out.println("返现result="+commonResponse.getResponseMsg());
			if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				//添加资金明细
				Map<String, Object> mapO = new HashMap<String, Object>();
				mapO.put("investPersonId", mem.getId());// 投资人Id（必填）
				mapO.put("investPersonType", ObSystemAccount.type0);// 投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
				mapO.put("transferType",ObAccountDealInfo.T_REDENVELOPE);// 交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
				mapO.put("money", redMoney);// 交易金额
				mapO.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);// 交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
				mapO.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);// 交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
				mapO.put("recordNumber", requestNo);// 交易流水号 （必填）
				mapO.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);// 资金交易状态：1等待支付，2支付成功，3
				String[] rett = obAccountDealInfoService.operateAcountInfo(mapO);
				//更新红包记录
				bpCustRedMember.setEdredMoney(redMoney);
				bpCustRedMember.setDistributeTime(new Date());
				bpCustRedMember.setOrderNo(requestNo);
				BpCustRedMember save2 = dao.save(bpCustRedMember);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//第三方成功后再修改其红包派发状态
		
		return save;
		
	}
}