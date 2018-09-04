package com.hurong.credit.dao.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderChildrenOrDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlMmOrderChildrenOrDaoImpl extends BaseDaoImpl<PlMmOrderChildrenOr> implements PlMmOrderChildrenOrDao{

	public PlMmOrderChildrenOrDaoImpl() {
		super(PlMmOrderChildrenOr.class);
	}

	@Override
	public List<PlMmOrderChildrenOr> listupdate(String searchDate) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingState=0 ");
		if(null !=searchDate&&!searchDate.equals("")){
		hql.append(" and f.matchingEndDate <= '"+searchDate+"'");
		}
		return findByHql(hql.toString());
			
	}
	@Override
	public List<PlMmOrderChildrenOr> listbysearch(PagingBean pb,
			Map<String, String> map) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where 1=1");
		String matchingStartDate=map.get("Q_matchingStartDate_D_EQ");
		String matchingEndDate=map.get("Q_matchingEndDate_D_EQ");
		String keystr=map.get("keystr");
		String orderId=map.get("orderId");
		String investPersonId=map.get("investPersonName");
		
		
		if(null !=investPersonId&&!investPersonId.equals("")){
			hql.append(" and f.investPersonName like '%"+investPersonId+"%'");
			}
		/*if(null !=keystr&&!keystr.equals("")){
			hql.append(" and f.keystr ='"+keystr+"'");
			}*/
		if(null !=orderId&&!orderId.equals("")){
		hql.append(" and f.orderId ="+orderId);
		}
		if(null !=matchingStartDate&&!matchingStartDate.equals("")){
			hql.append(" and f.matchingEndDate >= '"+matchingStartDate+"'");
		}
		if(null !=matchingEndDate&&!matchingEndDate.equals("")){
			hql.append(" and f.matchingStartDate <= '"+matchingEndDate+"'");
		}
		hql.append(" order by f.matchingStartDate asc");
		if(null==pb){
			return findByHql(hql.toString());
			
		}else{
			return findByHql(hql.toString(),null,pb);
		}
	
	}

	@Override
	public List<PlMmOrderChildrenOr> listbyorderid(Long orderId) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingState=0 and f.orderId="+orderId);
		return findByHql(hql.toString());
	}

	@Override
	public List<PlMmOrderChildrenOr> listbychildrenorId(Long childrenorId) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingState=0 and f.childrenorId="+childrenorId);
		return findByHql(hql.toString());
	}

	@Override
	public List<PlMmOrderChildrenOr> listbyIds(String ids) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchId in ( "+ids+")");
	
		return findByHql(hql.toString());
	}

	@Override
	public List<PlMmOrderChildrenOr> listbysame(String matchStartDate,
			String endStartDate, Long orderId,Long childrenorId) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingStartDate='"+matchStartDate
				+"' and f.matchingEndDate='"+endStartDate+"' and f.orderId="+orderId + " and f.childrenorId="+childrenorId);
		
		return findByHql(hql.toString());
	}
	
	@Override
	public List<PlMmOrderChildrenOr> listByParentOrBidId(Long parentOrBidId) {
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingState=0 and f.parentOrBidId="+parentOrBidId);
		return findByHql(hql.toString());
	}
	
	@Override
	public List<PlMmOrderChildrenOr> listByPlanId(PlManageMoneyPlan moneyPlan) {
		StringBuffer hql = new StringBuffer("");
		if(moneyPlan.getState()==7){//回款中
			hql = new StringBuffer("select" +
					" plan.moneyReceiver," +
					" childrenor.parentOrBidName as bidName," +
					" childrenor.matchingMoney," +
					" datediff(p.intentDate,NOW()) as days" +
					" from pl_mm_order_childrenor  as childrenor" +
					" LEFT JOIN pl_managemoney_plan as plan  on childrenor.mmplanId=plan.mmplanId" +
					" LEFT JOIN pl_mm_obligatoryright_children as p on p.childrenorId=childrenor.childrenorId" +
			" where childrenor.matchingState=0 and childrenor.mmplanId=? ");
		}else{
			hql = new StringBuffer("select" +
					" p.receiverP2PAccountNumber as moneyReceiver," +
					" p.parentOrBidName as bidName," +
					" p.availableMoney as matchingMoney," +
					" p.orlimit as days" +
					" from pl_mm_obligatoryright_children as p" +
					" LEFT JOIN pl_managemoney_plan as plan  on p.receiverP2PAccountNumber=plan.moneyReceiver" +
					" where plan.mmplanId=? and p.availableMoney>0 and NOW() BETWEEN p.startDate and p.intentDate");
		}
		if(moneyPlan.getKeystr().equals("mmplan")){//D计划
			hql.append(" and p.childType='mmplanOr' ");
		}else if(moneyPlan.getKeystr().equals("UPlan")){//U计划
			hql.append(" and p.childType='UPlanOr'  ");
		}
		return this.getSession().createSQLQuery(hql.toString())
			.addScalar("moneyReceiver", Hibernate.STRING)
			.addScalar("bidName", Hibernate.STRING)
			.addScalar("days", Hibernate.STRING)
			.addScalar("matchingMoney", Hibernate.BIG_DECIMAL)
			.setParameter(0,moneyPlan.getMmplanId())
			.setResultTransformer(Transformers.aliasToBean(PlMmOrderChildrenOr.class)).list();
	}

	@Override
	public List<PlMmOrderChildrenOr> listbyorderid(Long orderId, String enddate) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from PlMmOrderChildrenOr f where f.matchingState=0 and f.orderId="+orderId+" and f.matchingEndDate > '" +enddate+"'");
		return findByHql(hql.toString());
	}



}