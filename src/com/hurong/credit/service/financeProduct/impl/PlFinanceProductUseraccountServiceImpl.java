package com.hurong.credit.service.financeProduct.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUseraccountDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.service.financeProduct.PlFinanceProductUseraccountService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class PlFinanceProductUseraccountServiceImpl extends BaseServiceImpl<PlFinanceProductUseraccount> implements PlFinanceProductUseraccountService{
	@SuppressWarnings("unused")
	private PlFinanceProductUseraccountDao dao;
	
	public PlFinanceProductUseraccountServiceImpl(PlFinanceProductUseraccountDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlFinanceProductUseraccount> getUserAccountList(
			HttpServletRequest request, PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getUserAccountList(request,pb);
	}

	@Override
	public PlFinanceProductUseraccount getPersonAccount(Long id) {
		// TODO Auto-generated method stub
		return dao.getPersonAccount(id);
	}
	@Override
	public String queryAllList(Long userId){
		BpPersonCenterData list = dao.getPersonAccount1(userId);
		//BigDecimal money=dao.getPersonAccount1(userId);
		/*BpPersonCenterData bpPersonCenterData=new BpPersonCenterData();
		bpPersonCenterData.setNearlyMoney(money);//近一个月赚取
		bpPersonCenterData.setEarningsYesterday(list.getYesterdayEarn());//昨日收益
		bpPersonCenterData.setAccountBalance(list.getCurrentMoney());//账户余额
		bpPersonCenterData.setAccumulatedEarnings(list.getTotalIntestMoney());//累计收益
		bpPersonCenterData.setLeviticusInterestMoney(list.getOnwayMoney());//尚未记息金额
*/		
        StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
 		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
 		buffer.append(json.serialize(list));
 		buffer.append("}");
		return buffer.toString();
	}
	
}