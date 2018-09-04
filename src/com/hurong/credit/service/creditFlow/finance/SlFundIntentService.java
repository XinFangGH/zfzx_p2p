package com.hurong.credit.service.creditFlow.finance;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;

public interface SlFundIntentService extends BaseService<SlFundIntent> {

	List<SlFundIntent> getListByBidPlanId(Long bidPlanId,PagingBean pb);
	Integer getListByBidPlanId(String investpersonName);
	
	
	List<SlFundIntent> getListByCustId(Long custId, String custType,HttpServletRequest request, PagingBean pb);
	List<FundPay> getPay(HttpServletRequest request,PagingBean pb,Long investPersonId,String type);
	List<FundPay> getPay(String bidId,String type,String userName,String orderNo);
	public java.math.BigDecimal getLoanTotal(Long projectId);
	public BigDecimal getMoney(Long projectId, String type) ;
	public String[] getFundInfoByPlanId(Long bidId,HttpServletRequest req);
	
	public SlFundIntent getSlFoundIntent(Long projectId);
	
	public Integer getPayintentPeriodMax(Long projectId);
	public java.math.BigDecimal getnotMoneySum(Long projectId,int payintentPeriod);
	public java.util.Date getIntentDate(Long projectId,int payintentPeriod,String type);
	public java.lang.Integer getNextCount(Long projectId);
	
	public java.lang.Integer getCountDetailLoan(Long bidPlanId, String type,String investpersonName);
	
	public java.math.BigDecimal getLoanTotle(Long bidPlanId ,String type,String investpersonName);
}
