package com.hurong.credit.dao.creditFlow.finance;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;

public interface SlFundIntentDao extends BaseDao<SlFundIntent> {

	List<SlFundIntent> getListByBidPlanId(Long bidPlanId,PagingBean pb);
	
	public Integer getListByBidPlanId(String investpersonName);
	
	List<SlFundIntent> getListByCustId(Long custId, String custType, HttpServletRequest request, PagingBean pb);
	List<FundIncome> getPay(HttpServletRequest request,PagingBean pb,Long investPersonId,String type);
	List<FundIncome> getPay(String bidId,String type,String userName,String orderNo);
	
	public java.math.BigDecimal getLoanTotal(Long projectId);
	public BigDecimal getMoney(Long projectId, String type) ;
	public SlFundIntent getSlFoundIntent(Long projectId);
	
	public Integer getPayintentPeriodMax(Long projectId);
	public java.math.BigDecimal getnotMoneySum(Long projectId,int payintentPeriod);
	public java.util.Date getIntentDate(Long projectId,int payintentPeriod,String type);
	public java.lang.Integer getNextCount(Long projectId);
	
	public java.lang.Integer getCountDetailLoan(Long bidPlanId, String type, String investpersonName);
	
	public java.math.BigDecimal getLoanTotle(Long bidPlanId ,String type,String investpersonName);
}
