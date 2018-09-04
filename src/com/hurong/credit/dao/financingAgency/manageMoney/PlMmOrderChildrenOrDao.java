package com.hurong.credit.dao.financingAgency.manageMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;


/**
 * 
 * @author 
 *
 */
public interface PlMmOrderChildrenOrDao extends BaseDao<PlMmOrderChildrenOr>{
	public List<PlMmOrderChildrenOr> listupdate(String searchDate);
	List<PlMmOrderChildrenOr> listbysearch(PagingBean pb,Map<String, String> map);
	List<PlMmOrderChildrenOr> listbyorderid(Long orderId);
	List<PlMmOrderChildrenOr> listbychildrenorId(Long childrenorId);
	List<PlMmOrderChildrenOr> listbyIds(String ids);
	List<PlMmOrderChildrenOr> listbysame(String matchStartDate,String endStartDate,Long orderId,Long childrenorId);
	List<PlMmOrderChildrenOr> listByParentOrBidId(Long parentOrBidId);
	public List<PlMmOrderChildrenOr> listByPlanId(PlManageMoneyPlan moneyPlan);
	List<PlMmOrderChildrenOr> listbyorderid(Long orderId,String enddate);
}