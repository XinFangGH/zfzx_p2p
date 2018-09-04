package com.hurong.credit.service.customer.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.customer.InvestPersonInfoDao;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.thirdPayInterface.CommonRequestInvestRecord;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public class InvestPersonInfoServiceImpl extends BaseServiceImpl<InvestPersonInfo> implements InvestPersonInfoService{
	@SuppressWarnings("unused")
	private InvestPersonInfoDao dao;
	
	public InvestPersonInfoServiceImpl(InvestPersonInfoDao dao) {
		super(dao);
		this.dao=dao;
	}

	/**
	 * 获取投资人Id列表
	 * @return
	 */
	public List<InvestPersonInfo> getByPersonId(Long personId){
		return dao.getByPersonId(personId);
	}

	@Override
	public List<InvestPersonInfo> getByRequestNumber(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByRequestNumber(requestNo);
	}

	@Override
	public List<InvestPersonInfo> getByPlanId(Long bidId) {
		return dao.getByPlanId(bidId);
	}
	
	@Override
	public List<InvestPersonInfo> bidedMoneyByOrderNo(Long bidId,String orderNo) {
		return dao.bidedMoneyByOrderNo(bidId,orderNo);
	}
	

	@Override
	public List<InvestPersonInfo> queryName(String userName,String type,Pager pager) {
		return dao.queryName(userName,type,pager);
	}
	@Override
	public List<InvestPersonInfo> queryNamepager(String userName,String type,Integer begin,Integer max) {
		return dao.queryNamepager(userName,type,begin,max);
	}
	@Override
	public BigInteger getCount(String userName,String type,Pager pager) {
		return dao.getCount(userName, type, pager);
	}

	@Override
	public List<CommonRequestInvestRecord> getRepaymentList(String planId,String peridId) {
		return dao.getRepaymentList(planId,peridId);
	}
	@Override
	public List<CommonRequestInvestRecord> getRepayEarlymentList(String planId,String slEarlyPayId) {
		return dao.getRepayEarlymentList(planId,slEarlyPayId);
	}

	@Override
	public List<CommonRequestInvestRecord> getFeeRepayEarlymentList(
			String planId, String slEarlyPayId) {
		// TODO Auto-generated method stub
		return dao.getFeeRepayEarlymentList(planId, slEarlyPayId);
	}

	@Override
	public List<CommonRequestInvestRecord> getCompensatoryRepaymentList(
			String planId, String intentDate) {
		// TODO Auto-generated method stub
		return dao.getCompensatoryRepaymentList(planId, intentDate);
	}


	@Override
	public BigDecimal getSumInvestMoney(Long bidId){
		return (BigDecimal)dao.getSumInvestMoney(bidId);
	}

}