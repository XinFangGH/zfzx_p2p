package com.hurong.credit.service.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmObligatoryRightChildrenDao;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderChildrenOrDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmObligatoryRightChildren;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;
import com.hurong.credit.service.financingAgency.manageMoney.PlMmOrderChildrenOrService;


/**
 * 
 * @author 
 *
 */
public class PlMmOrderChildrenOrServiceImpl extends BaseServiceImpl<PlMmOrderChildrenOr> implements PlMmOrderChildrenOrService{
	@SuppressWarnings("unused")
	private PlMmOrderChildrenOrDao dao;
	@Resource
	private PlMmObligatoryRightChildrenDao plMmObligatoryRightChildrenDao;
	@Resource
	private PlManageMoneyPlanBuyinfoDao plManageMoneyPlanBuyinfoDao;
	@Resource
	private PlManageMoneyPlanDao plManageMoneyPlanDao;
	public PlMmOrderChildrenOrServiceImpl(PlMmOrderChildrenOrDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<PlMmOrderChildrenOr> listbysearch(PagingBean pb,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.listbysearch(pb, map);
	}
	@Override
	public List<PlMmOrderChildrenOr> listByParentOrBidId(Long parentOrBidId) {
		return dao.listByParentOrBidId(parentOrBidId);
	}
	
	@Override
	public List<PlMmOrderChildrenOr> listByPlanId(PlManageMoneyPlan moneyPlan) {
		return dao.listByPlanId(moneyPlan);
	}
	@Override
	public String matchingrelease(Date date, PlMmOrderChildrenOr o,
			PlManageMoneyPlanBuyinfo order,
			PlMmObligatoryRightChildren orchildren) {
		// TODO Auto-generated method stub
		if(o.getMatchingEndDateType()==1){
    		if(orchildren.getIntentDate().compareTo(date)==1){
    			orchildren.setAvailableMoney(orchildren.getAvailableMoney().add(o.getMatchingMoney()));
    			
    		}
    		
    	 
    	}else if(o.getMatchingEndDateType()==2){
    		
    		
    /*		if(plManageMoneyPlan.getIsCyclingLend()==1){
    		  order.setCurrentMatchingMoney(order.getCurrentMatchingMoney().add(o.getMatchingMoney()).add(o.getMatchingGetMoney()));
    		}else{*/
    			if(order.getEndinInterestTime().compareTo(date)==1){
    				order.setCurrentMatchingMoney(order.getCurrentMatchingMoney().add(o.getMatchingMoney()));
    				
    			}
    			 
    	//	}
    	}else if(o.getMatchingEndDateType()==3){  
    		if(orchildren.getIntentDate().compareTo(date)==1){
    			orchildren.setAvailableMoney(orchildren.getAvailableMoney().add(o.getMatchingMoney()));
    			
    		}
    		
/*    		if(plManageMoneyPlan.getIsCyclingLend()==1){
    		  order.setCurrentMatchingMoney(order.getCurrentMatchingMoney().add(o.getMatchingMoney()).add(o.getMatchingGetMoney()));
    		}else{*/
    			
    		if(order.getEndinInterestTime().compareTo(date)==1){
				order.setCurrentMatchingMoney(order.getCurrentMatchingMoney().add(o.getMatchingMoney()));
				
			}
    //		}
    	}else if(o.getMatchingEndDateType()==4){ //提前赎回
    		
    		
    		if(orchildren.getIntentDate().compareTo(date)==1){
    			orchildren.setAvailableMoney(orchildren.getAvailableMoney().add(o.getMatchingMoney()));
    			
    		}
    	}
    /*	if(order.getOptimalDayRate().compareTo(new BigDecimal(0))==0){
    		
    		order.setCurrentMatchingMoney((new BigDecimal(0)));
    	}*/
    	o.setMatchingState(1); //
    	dao.save(o);
    
		return "";
	}

	
}