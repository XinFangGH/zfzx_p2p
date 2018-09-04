package com.hurong.credit.service.financingAgency.manageMoney.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderAssignInterestDao;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.hurong.credit.service.financingAgency.manageMoney.AssignInerestGenerate;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderAssignInterestService;
import com.thirdPayInterface.CommonRequestInvestRecord;

/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

/**
 * 
 * @author 
 *
 */
public class PlMmOrderAssignInterestServiceImpl extends BaseServiceImpl<PlMmOrderAssignInterest> implements PlMmOrderAssignInterestService{
	@SuppressWarnings("unused")
	private PlMmOrderAssignInterestDao dao;
	@Resource
	private PlManageMoneyPlanBuyinfoDao plManageMoneyPlanBuyinfoDao;
	
	public PlMmOrderAssignInterestServiceImpl(PlMmOrderAssignInterestDao dao) {
		super(dao);
		this.dao=dao;
	}
	 private static BigDecimal lin=new BigDecimal(0);
	private static String loanInterest ="loanInterest";
	private static String principalRepayment ="principalRepayment";
	@Override
	public BigDecimal getGetMoney(Long orderId) {
		// TODO Auto-generated method stub
		return dao.getGetMoney(orderId);
	}
	@Override
	public String createUPlanAssignInerestlist(PlManageMoneyPlanBuyinfo orderinfo,PlManageMoneyPlan plan) {
		AssignInerestGenerate assignInerestGenerate=new AssignInerestGenerate(orderinfo,plan);
		List<PlMmOrderAssignInterest> 	list=assignInerestGenerate.createUPlanPlMmOrderAssignInterest();
		for(PlMmOrderAssignInterest a:list){
		 dao.save(a);
		}
		return "";	
	}

	@Override
	public BigDecimal findAfterMoney(Long orderId, Long mmplanId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		return dao.findAfterMoney(orderId, mmplanId, intentDate, type);
	}

	@Override
	public ShowManageMoney findByIdDate(Long mmplanId, String intentDate) {
		// TODO Auto-generated method stub
		return dao.findByIdDate(mmplanId, intentDate);
	}

	@Override
	public Long findFinancialCount(String loginname, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findFinancialCount(loginname, request);
	}

	@Override
	public List<ShowManageMoney> findFinancialList(String loginname,
			HttpServletRequest request, Pager pager) {
		// TODO Auto-generated method stub
		return dao.findFinancialList(loginname, request, pager);
	}

	@Override
	public List<ShowManageMoney> findLoanRepayMemtList(Long mmplanId, String loginname) {
		// TODO Auto-generated method stub
		return dao.findLoanRepayMemtList(mmplanId, loginname);
	}

	@Override
	public List<ShowManageMoney> findLoanRepayMemtList1(Long mmplanId, String loginname) {
		// TODO Auto-generated method stub
		return dao.findLoanRepayMemtList(mmplanId, loginname);
	}
	@Override
	public BigDecimal findMoneyByType(String loginname, String fundType) {
		// TODO Auto-generated method stub
		return dao.findMoneyByType(loginname, fundType);
	}

	@Override
	public BigDecimal findReturnMoney( Long mmplanId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		return dao.findReturnMoney( mmplanId, intentDate, type);
	}

	@Override
	public Long getBidCountFinancial(String loginname, Integer state,
			String isPresale) {
		// TODO Auto-generated method stub
		return dao.getBidCountFinancial(loginname, state, isPresale);
	}

	@Override
	public List<PlMmOrderAssignInterest> getByDealCondition(Long orderId,
			Long investPersonId) {
		// TODO Auto-generated method stub
		return dao.getByDealCondition(orderId, investPersonId);
	}

	@Override
	public List<PlMmOrderAssignInterest> getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByRequestNo(requestNo);
	}

	@Override
	public List<PlMmOrderAssignInterest> getCouponsIntent(String mmPlanId,
			Date intentDate, String requestNo, String fundType) {
		// TODO Auto-generated method stub
		return dao.getCouponsIntent(mmPlanId, intentDate, requestNo, fundType);
	}

	@Override
	public BigDecimal getFinancialMoney(Long mmplanId, String type) {
		// TODO Auto-generated method stub
		return dao.getFinancialMoney(mmplanId, type);
	}
	/**
	 *UD计划收款账户还款时款项查询
	 * @param mmplanId
	 * @param intentDate
	 * @return
	 */
	@Override
	public List<CommonRequestInvestRecord> getRepaymentList(Long mmplanId,
			String intentDate) {
		// TODO Auto-generated method stub
		return dao.getRepaymentList(mmplanId, intentDate);
	}

	@Override
	public List<PlMmOrderAssignInterest> getByDealCondition(Long mmplanId) {
		// TODO Auto-generated method stub
		return dao.getByDealCondition(mmplanId);
	}

	@Override
	public List<PlMmOrderAssignInterest> mmplancreateList(
			PlEarlyRedemption plEarlyRedemption) {
		// TODO Auto-generated method stub
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		PlManageMoneyPlanBuyinfo orderinfo=plManageMoneyPlanBuyinfoDao.get(plEarlyRedemption.getOrderId());
		PlManageMoneyPlan plan=orderinfo.getPlManageMoneyPlan();
		List<PlMmOrderAssignInterest> list=new ArrayList<PlMmOrderAssignInterest>();
		
	//	PlMmOrderAssignInterest a=null;
		int periods=orderinfo.getOrderlimit();
		List<PlMmOrderAssignInterest> list1=dao.listByEarlyDate(">='"+sd.format(plEarlyRedemption.getEarlyDate())+"'", orderinfo.getOrderId(),"('loanInterest')",plEarlyRedemption.getEarlyRedemptionId());
		
		if(null!=list1 && list1.size()>0){
			periods=list1.get(0).getPeriods();
		}
       
		List<PlMmOrderAssignInterest> list2=dao.listByEarlyDate(null, orderinfo.getOrderId(),"('loanInterest')",plEarlyRedemption.getEarlyRedemptionId());
		Date startDate=orderinfo.getStartinInterestTime();
	/*	if(null!=list2 && list2.size()>0){
			PlMmOrderAssignInterest a1=list2.get(list2.size()-1);
			startDate=a1.getIntentDate();
		}*/
		BigDecimal afterloanInterestMoney=new BigDecimal("0");
		for(PlMmOrderAssignInterest p:list2 ){
			afterloanInterestMoney =afterloanInterestMoney.add(p.getAfterMoney());
	    }
		
		//查询出本金台账
		PlMmOrderAssignInterest bj = null;
		List<PlMmOrderAssignInterest> plList = dao.listByOrderIdAndFundType(orderinfo.getOrderId().toString(),"principalRepayment");
		if(plList != null && plList.size()>0){
			bj = plList.get(0);
		}
		
     //提取支取的罚息
		PlMmOrderAssignInterest obj2=create(orderinfo.getOrderId(),"liquidatedDamages",orderinfo.getKeystr(),periods, lin,plEarlyRedemption.getEarlyDate(),
				orderinfo.getInvestPersonId(),orderinfo.getInvestPersonName(),orderinfo.getMmName(),orderinfo.getPlManageMoneyPlan().getMmplanId()
				
				,bj!=null?bj.getIncomeMoney().subtract(bj.getAfterMoney()).multiply(plEarlyRedemption.getLiquidatedDamagesRate()).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP):new BigDecimal(0));
		//obj2.setAfterMoney(obj2.getPayMoney());
		obj2.setEarlyRedemptionId(plEarlyRedemption.getEarlyRedemptionId());
		//obj2.setFactDate(new Date());
		if(obj2.getPayMoney().compareTo(new BigDecimal(0))!=0){
			list.add(obj2);
			dao.save(obj2);
		}
		
		
		
		//利息
		int days=DateUtil.getDaysBetweenDate(sd.format(startDate), sd.format(plEarlyRedemption.getEarlyDate()));
		BigDecimal toDayMoney=plEarlyRedemption.getEarlyMoney().multiply(new BigDecimal(days)).multiply(orderinfo.getPlManageMoneyPlan().getYeaRate()).divide(new BigDecimal(36000),2,BigDecimal.ROUND_HALF_UP);
		BigDecimal loanInterestMoney=toDayMoney.subtract(afterloanInterestMoney);
		PlMmOrderAssignInterest obj3=create(orderinfo.getOrderId(),loanInterest,orderinfo.getKeystr(),
				periods, 
				loanInterestMoney
				,plEarlyRedemption.getEarlyDate(),
				orderinfo.getInvestPersonId(),orderinfo.getInvestPersonName(),orderinfo.getMmName(),orderinfo.getPlManageMoneyPlan().getMmplanId()
				,lin);
		//obj3.setAfterMoney(obj3.getIncomeMoney());
		obj3.setEarlyRedemptionId(plEarlyRedemption.getEarlyRedemptionId());
		//obj3.setFactDate(new Date());
		if(obj3.getIncomeMoney().compareTo(new BigDecimal(0))>0){
			list.add(obj3);
			dao.save(obj3);
		}
		
		//提前赎回本金
		PlMmOrderAssignInterest obj1=create(orderinfo.getOrderId(),principalRepayment,orderinfo.getKeystr(),periods,bj!=null?bj.getIncomeMoney().subtract(bj.getAfterMoney()):new BigDecimal(0)
				,plEarlyRedemption.getEarlyDate(),orderinfo.getInvestPersonId()
				,orderinfo.getInvestPersonName(),orderinfo.getMmName(),orderinfo.getPlManageMoneyPlan().getMmplanId(),
				lin);
		//obj1.setAfterMoney(obj1.getIncomeMoney());
		obj1.setEarlyRedemptionId(plEarlyRedemption.getEarlyRedemptionId());
		//obj1.setFactDate(new Date());
		if(obj1.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
			list.add(obj1);
			dao.save(obj1);
		}
		
		return list;
	}
	public PlMmOrderAssignInterest create(Long orderId,String fundType,String keystr,
			int periods ,BigDecimal incomeMoney,Date intentDate,Long investPersonId
			,String investPersonName,String mmName,Long mmplanId,BigDecimal payMoney){
		
			PlMmOrderAssignInterest ai1=new PlMmOrderAssignInterest();
			ai1.setOrderId(orderId);
			ai1.setFundType(fundType);
			ai1.setKeystr(keystr);
			ai1.setPeriods(periods);
			ai1.setIncomeMoney(incomeMoney);
			ai1.setPayMoney(payMoney);
			ai1.setIntentDate(intentDate);
			ai1.setInvestPersonId(investPersonId);
			ai1.setInvestPersonName(investPersonName);
			ai1.setMmName(mmName);
			ai1.setAfterMoney(lin);
			ai1.setMmplanId(mmplanId);
			ai1.setIsValid(Short.valueOf("1"));
			ai1.setIsCheck(Short.valueOf("1"));
		return ai1;
	}
	@Override
	public List<PlMmOrderAssignInterest> getByPlanIdA(Long orderId,
			Long investPersonId, Long mmplanId, String fundType, Integer periods) {
		// TODO Auto-generated method stub
		return dao.getByPlanIdA(orderId, investPersonId, mmplanId, fundType, periods);
	}
	@Override
	public List<PlMmOrderAssignInterest> mmplanupdateList(
			PlEarlyRedemption plEarlyRedemption) {
		// TODO Auto-generated method stub
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		PlManageMoneyPlanBuyinfo orderinfo=plManageMoneyPlanBuyinfoDao.get(plEarlyRedemption.getOrderId());
		List<PlMmOrderAssignInterest> list=new ArrayList<PlMmOrderAssignInterest>();
		List<PlMmOrderAssignInterest> list3=dao.listByEarlyDate(null, orderinfo.getOrderId(),"('loanInterest','principalRepayment')",plEarlyRedemption.getEarlyRedemptionId());
		for(PlMmOrderAssignInterest pai:list3){
			if(pai.getAfterMoney().compareTo(new BigDecimal("0"))==0){
				pai.setIsValid(Short.valueOf("1"));
	        	pai.setIsCheck(Short.valueOf("1"));
	        	dao.save(pai);
			}
			}
     return null;
	}
	@Override
	public List<PlMmOrderAssignInterest> getListByBidIdAndPeriod(String bidId,
			String periodId,Date intentDate) {
		// TODO Auto-generated method stub
		return dao.getListByBidIdAndPeriod(bidId,periodId,intentDate);
	}
	@Override
	public List<PlMmOrderAssignInterest> getByInvestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByInvestNo(requestNo)
		;
	}

}