package com.hurong.credit.service.financingAgency.manageMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmObligatoryRightChildren;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;


/**
 * 
 * @author 
 *
 */
public interface PlMmOrderChildrenOrService extends BaseService<PlMmOrderChildrenOr>{
	List<PlMmOrderChildrenOr> listbysearch(PagingBean pb,Map<String, String> map);
	List<PlMmOrderChildrenOr> listByParentOrBidId(Long parentOrBidId);
	
	/**
	 * 根据理财计划id查询该计划匹配的债权记录
	 * @param moneyPlan
	 * @return
	 */
	List<PlMmOrderChildrenOr> listByPlanId(PlManageMoneyPlan moneyPlan);
	public String matchingrelease(Date date,PlMmOrderChildrenOr o,PlManageMoneyPlanBuyinfo order,PlMmObligatoryRightChildren orchildren) ;
	
}


