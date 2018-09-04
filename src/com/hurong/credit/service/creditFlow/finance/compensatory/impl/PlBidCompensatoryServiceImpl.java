package com.hurong.credit.service.creditFlow.finance.compensatory.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.finance.compensatory.PlBidCompensatoryDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.dao.customer.BpCustRelationDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;
import com.hurong.credit.service.sms.SendMesService;

/**
 * 
 * @author 
 *
 */
public class PlBidCompensatoryServiceImpl extends BaseServiceImpl<PlBidCompensatory> implements PlBidCompensatoryService{
	@SuppressWarnings("unused")
	private PlBidCompensatoryDao dao;
	@Resource
	private BpCustRelationDao bpCustRelationDao;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	@Resource 
	private BpFundProjectDao bpFundProjectDao;
	@Resource
	private PlBidPlanDao plBidPlanDao;
	@Resource
	private SendMesService sendMesService;
	
	public PlBidCompensatoryServiceImpl(PlBidCompensatoryDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlBidCompensatory> getCompensatoryList(Long loanerp2pId,String custmerType,PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getCompensatoryList(loanerp2pId,custmerType,pb);
	}

	@Override
	public BigDecimal findCompensatoryMoneytByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		return dao.findCompensatoryMoneytByComp2PId(compensatoryP2PId, compensatoryType, backStatus);
	}

	@Override
	public Long findCountByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		return dao.findCountByComp2PId(compensatoryP2PId, compensatoryType, backStatus);
	}

	@Override
	public List<PlBidCompensatory> findListByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus, Pager pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findListByComp2PId(compensatoryP2PId, compensatoryType, backStatus, pb, request);
	}

	@Override
	public List<PlBidCompensatory> findListByComp2PId1(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus, Pager pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findListByComp2PId1(compensatoryP2PId, compensatoryType, backStatus, pb, request);
	}

	@Override
	public BigDecimal findPunishMoneyByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		return dao.findPunishMoneyByComp2PId(compensatoryP2PId, compensatoryType, backStatus);
	}

	@Override
	public void saveComPensatoryService(String planId, String peridId,
			String cardNo, String type, String period) {
		// TODO Auto-generated method stub
		try{
			Boolean flag=dao.isExist("requestNo", cardNo);
			if(!flag){//表示没有生成罚息台账
				PlBidPlan plBidPlan =plBidPlanDao.get(Long.valueOf(planId));
				BigDecimal money =new BigDecimal("0");
				if(null!=plBidPlan.getGuaranteeWay() && "1".equals(plBidPlan.getGuaranteeWay())){
					money=bpFundIntentDao.sumAllCompensatoryMoney(planId,peridId,"('principalRepayment')");
				}
				if(null!=plBidPlan.getGuaranteeWay() && "2".equals(plBidPlan.getGuaranteeWay())){
					money=bpFundIntentDao.sumAllCompensatoryMoney(planId,peridId,"('principalRepayment','loanInterest')");
				}
				if(null!=plBidPlan.getGuaranteeWay() && "3".equals(plBidPlan.getGuaranteeWay())){
					money=bpFundIntentDao.sumAllCompensatoryMoney(planId,peridId,"('principalRepayment','loanInterest','serviceMoney','consultationMoney')");
				}
				if(money!=null&&money.compareTo(new BigDecimal(0))>0){//代偿总金额大于0元
					BpCustMember mem=this.getLoanMember(plBidPlan);
					if(mem!=null){//有借款人P2P账号
						List<BpCustRelation> bp=bpCustRelationDao.getP2pCustListById(mem.getId());
						if(bp!=null){
							BpCustRelation cust = bp.get(0);
							BpFundProject bfp=this.getBpFundProject(plBidPlan);
							if(bfp!=null){
								PlBidCompensatory plBidCompensatory=new PlBidCompensatory();
								plBidCompensatory.setRequestNo(cardNo);
								plBidCompensatory.setPayintentPeriod(Integer.valueOf(period));
								plBidCompensatory.setPlanId(Long.valueOf(planId));
								plBidCompensatory.setLoanerp2pId(mem.getId());
								plBidCompensatory.setCustmerId(cust.getOfflineCusId());//线下客户Id
								plBidCompensatory.setCustmerType(cust.getOfflineCustType());//线下客户类型
								plBidCompensatory.setCompensatoryType(type);
								if(type.equals(PlBidCompensatory.TYPE_PLATE)){
									plBidCompensatory.setCompensatoryName("平台");
								}else if(type.equals(PlBidCompensatory.TYPE_GURANEE)){
									BpCustMember memGu=this.getGuraneeMember(plBidPlan);
									if(memGu!=null){
										plBidCompensatory.setCompensatoryName(memGu.getTruename());
										plBidCompensatory.setCompensatoryP2PId(memGu.getId());
									}
									
								}
								BigDecimal  oriValue=new BigDecimal("0");
								plBidCompensatory.setCompensatoryMoney(money);
								plBidCompensatory.setCompensatoryDate(new Date());
								plBidCompensatory.setPunishRate(bfp.getOverdueRate());//逾期后代偿
								plBidCompensatory.setCompensatoryDays(0);
								plBidCompensatory.setPunishMoney(oriValue);
								plBidCompensatory.setBackPunishMoney(oriValue);
								plBidCompensatory.setBackCompensatoryMoney(oriValue);
								dao .save(plBidCompensatory);
								
								//发送短信
								if(type.equals(PlBidCompensatory.TYPE_GURANEE)){
									BpCustMember member=bpCustMemberDao.getByLoginName(plBidPlan.getReceiverP2PAccountNumber());
									if(member!=null){
										Map<String, String> mapMessage = new HashMap<String, String>();
										mapMessage.put("key", "sms_compensatory");
										mapMessage.put("userId",member.getId().toString());
										mapMessage.put("${name}",member.getLoginname());
										mapMessage.put("${projNumber}", plBidPlan.getBidProNumber());
										mapMessage.put("${projName}", plBidPlan.getBidProName());
										mapMessage.put("${payintentPeriod}",period);
										mapMessage.put("${code}", money.toString());
										String result =  sendMesService.sendSmsEmailMessage(mapMessage);
									}
								}
							}
						}
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public BpFundProject getBpFundProject(PlBidPlan bidplan) {
		// TODO Auto-generated method stub
		Long moneyPlanId = null;
		BpFundProject bpFundProject = null;
		if (bidplan.getProType().equals("B_Dir")) {
			moneyPlanId = bidplan.getBpBusinessDirPro().getMoneyPlanId();
		} else if (bidplan.getProType().equals("B_Or")) {
			moneyPlanId = bidplan.getBpBusinessOrPro().getMoneyPlanId();
			
		} else if (bidplan.getProType().equals("P_Dir")) {
			moneyPlanId = bidplan.getBpPersionDirPro().getMoneyPlanId();
			
		} else if (bidplan.getProType().equals("P_Or")) {
			moneyPlanId = bidplan.getBpPersionOrPro().getMoneyPlanId();
		}
		if (moneyPlanId != null) {
			bpFundProject=bpFundProjectDao.get(moneyPlanId);
		}
		return bpFundProject;
	}

	@Override
	public BpCustMember getLoanMember(PlBidPlan bidplan) {
		// TODO Auto-generated method stub
		BpCustMember member=new BpCustMember();
		// 网站注册用户
		// 项目类型 企业直投 B_Dir 企业 债权 B_Or 个人直投 P_Dir 个人债权 P_Or * @return String
		String loanUserType = "";
		Long loanUserId = null;
		Long custMamberId = null;
		if (bidplan.getProType().equals("B_Dir")) {
			loanUserType = "b_loan";
			loanUserId = bidplan.getBpBusinessDirPro().getBusinessId();
		} else if (bidplan.getProType().equals("B_Or")) {
			loanUserType = "b_loan";
			member = bpCustMemberDao.getMemberUserName(bidplan.getBpBusinessOrPro().getReceiverP2PAccountNumber());
			
		} else if (bidplan.getProType().equals("P_Dir")) {
			loanUserType = "p_loan";
			loanUserId = bidplan.getBpPersionDirPro().getPersionId();
			
		} else if (bidplan.getProType().equals("P_Or")) {
			loanUserType = "p_loan";
			member = bpCustMemberDao.getMemberUserName(bidplan.getBpPersionOrPro().getReceiverP2PAccountNumber());//存放着债权人账户信息
		}
		if (loanUserId != null) {
			BpCustRelation bpCustRelation = bpCustRelationDao.getByLoanTypeAndId(loanUserType, loanUserId);
			if (bpCustRelation != null) {
				custMamberId = bpCustRelation.getP2pCustId();
			}
		}
		if (custMamberId != null && !custMamberId.equals("")) {
			member = bpCustMemberDao.get(custMamberId);
		}
		return member;
	}
	@Override
	public BpCustMember getGuraneeMember(PlBidPlan bidplan) {
		// TODO Auto-generated method stub
		//资金方案id
		String type=null;
		Long moneyPlanId = null;
		BpCustMember mem = null;
		type="b_guarantee";
		moneyPlanId = bidplan.getGuarantorsId();
		if (moneyPlanId != null) {
			BpCustRelation bp=bpCustRelationDao.getByLoanTypeAndId(type, moneyPlanId);
			if(bp!=null){
				mem=bpCustMemberDao.get(bp.getP2pCustId());
			}
		}
		return mem;
	}

}