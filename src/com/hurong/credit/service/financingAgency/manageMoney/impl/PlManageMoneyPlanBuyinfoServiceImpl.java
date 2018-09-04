package com.hurong.credit.service.financingAgency.manageMoney.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmObligatoryRightChildrenDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderChildrenOrDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmObligatoryRightChildren;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderChildrenOrService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;


public class PlManageMoneyPlanBuyinfoServiceImpl extends BaseServiceImpl<PlManageMoneyPlanBuyinfo>
		implements PlManageMoneyPlanBuyinfoService {
	private PlManageMoneyPlanBuyinfoDao dao;
	@Resource
	private PlMmOrderChildrenOrService plMmOrderChildrenOrService;
	@Resource
	private PlMmOrderChildrenOrDao plMmOrderChildrenOrDao;
	@Resource
	private PlMmObligatoryRightChildrenDao plMmObligatoryRightChildrenDao;
	@Resource
	private PlManageMoneyPlanDao plManageMoneyPlanDao; 
	public PlManageMoneyPlanBuyinfoServiceImpl(PlManageMoneyPlanBuyinfoDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long planId,Short persionType) {
		return dao.getBuyInfoListByPlanId(planId, persionType);
	}
	
	/**
	 * 投资人投资管理查询方法
	 * add by linyan
	 * 2014-5-16
	 * @param request
	 * @return
	 */
	@Override
	public List<Investproject> getPersonInvestProject(
			HttpServletRequest request, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return dao.getPersonInvestProject(request,start,limit);
	}

	@Override
	public List<Investproject> getTopFiveList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<Investproject> list = dao.getTopFiveList(request);
		for(Investproject in : list){
			in.setCount(dao.queryNum(in.getUserName()));
		}
		return list;
	}

	@Override
	public int queryNum(String userName) {
		// TODO Auto-generated method stub
		return dao.queryNum(userName);
	}

	@Override
	public List<Investproject> getPersonInvestProject(String userType,
			String userId, String state) {
		// TODO Auto-generated method stub
		return dao.getPersonInvestProject(userType, userId, state);
	}

	@Override
	public String getUrl(String orderNo, String type) {
		// TODO Auto-generated method stub
		return dao.getUrl( orderNo, type);
	}

	@Override
	public BigDecimal investmentBidNum(String userType, String userId, String state) {
		// TODO Auto-generated method stub
		return dao.investmentBidNum(userType, userId, state);
	}

	@Override
	public String queryLoanUrl(Long bidId) {
		return dao.queryLoanUrl( bidId);
	}

	@Override
	public PlManageMoneyPlanBuyinfo getOrderNumber(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getOrderNumber(requestNo);
	}
	
	public BigInteger countpl(Long l){
		return dao.getCountpl(l);
	}
	public List<PlManageMoneyPlanBuyinfo> getManagePlanBuyInfo(String sql){
		return dao.getManagePlanBuyInfo(sql);
	}
	public PlManageMoneyPlanBuyinfo getCount(String sql){
		return dao.getInfoCount(sql);
	}
	
	public BigInteger countExperience(Long mmplanId,Long investPersonId){
		return dao.countExperience(mmplanId,investPersonId);
	}

	@Override
	public List<PlMmOrderChildrenOr> getClaimsList(String orderId) {
		return dao.getClaimsList(orderId);
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> findEarlyList(String loginname,
			HttpServletRequest request, Pager pager) {
		// TODO Auto-generated method stub
		return dao.findEarlyList(loginname, request, pager);
	}

	@Override
	public Long findEarlyCount(String loginname, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.findEarlyCount(loginname, request);
	}

	@Override
	public String gcalculateEarlyOutOrmatching(
			PlEarlyRedemption plEarlyRedemption) {
		// TODO Auto-generated method stub
		PlManageMoneyPlanBuyinfo order=dao.get(plEarlyRedemption.getOrderId()); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		List<PlMmOrderChildrenOr> clist= plMmOrderChildrenOrDao.listbyorderid(plEarlyRedemption.getOrderId(),sdf.format(plEarlyRedemption.getEarlyDate()));
		BigDecimal money=order.getCurrentGetedMoney();
		for(PlMmOrderChildrenOr c:clist){
			c.setMatchingEndDate(plEarlyRedemption.getEarlyDate());
			int matchingLimit=DateUtil.getDaysBetweenDate(sdf.format(c.getMatchingStartDate()),sdf.format(c.getMatchingEndDate()));
			if(matchingLimit<=0){
				c.setMatchingEndDateType(1);
				PlMmObligatoryRightChildren orchildren=	plMmObligatoryRightChildrenDao.get(c.getChildrenorId());
		    	plMmOrderChildrenOrService.matchingrelease(new Date(),c,order,orchildren);
			
		    	money=money.subtract(c.getMatchingGetMoney());
				plMmOrderChildrenOrDao.remove(c);
			}else{
				c.setMatchingEndDateType(4);
				c.setMatchingLimit(matchingLimit);
				BigDecimal thismatchgetMoney=c.getMatchingMoney().multiply(c.getChildrenOrDayRate().multiply(new BigDecimal(matchingLimit))).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
				money=money.subtract(c.getMatchingGetMoney()).add(thismatchgetMoney);
				c.setMatchingGetMoney(thismatchgetMoney);
				plMmOrderChildrenOrDao.save(c);
			}
		}
		order.setCurrentGetedMoney(money);
		order.setEarlierOutDate(plEarlyRedemption.getEarlyDate());
		dao.save(order);
		
		return "";
	}

	@Override
	public String getList(Long uerId,HttpServletRequest request) {
		List<PlManageMoneyPlanBuyinfo> list = new ArrayList();
		List<PlManageMoneyPlanBuyinfo> list1 = new ArrayList();
		List<PlManageMoneyPlanBuyinfo> listCount = new ArrayList();
        list = dao.getList(uerId,request);
        List list1s = new ArrayList<PlManageMoneyPlanBuyinfo>();
        Calendar cal = Calendar.getInstance();
        
        if(list.size()>0){
        	for(PlManageMoneyPlanBuyinfo info : list){
        		//if(info.getInvestlimit()!=null && info.getBuyDatetime()!=null){
        		if(info.getMmplanId()!=null){
        			PlManageMoneyPlan plan = plManageMoneyPlanDao.get(info.getMmplanId());
        			if(plan!=null && plan.getInvestlimit()!=null){
        				Date date = new Date(info.getBuyDatetime().getTime()+plan.getInvestlimit()*30*24*60*60*1000);
        				info.setEndinInterestTime(date);
        			}
        		}
        	//	}
        	}
        }
        listCount =dao.getListNum(uerId,request);
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(listCount.size()>0?listCount.size():0).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
 		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
 		buff1.append(json.serialize(list));
 		buff1.append("}");
		return buff1.toString();
	}
}