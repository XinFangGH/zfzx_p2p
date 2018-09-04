package com.hurong.credit.dao.financingAgency.manageMoney.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;

@SuppressWarnings("unchecked")
public class PlManageMoneyPlanDaoImpl extends BaseDaoImpl<PlManageMoneyPlan> implements
		PlManageMoneyPlanDao {

	public PlManageMoneyPlanDaoImpl() {
		super(PlManageMoneyPlan.class);
	}

	@Override
	public List<PlManageMoneyPlan> listpmmp(Pager p) {
		String hql = "from PlManageMoneyPlan p "+p.getProperty()+" and p.keystr='mmplan' order by p.state ASC,p.createtime desc";
		System.out.println("查询的slq是"+hql);
		return this.getSession().createQuery(hql).setMaxResults(p.getPageSize()).setFirstResult((p.getPageNumber()-1)*p.getPageSize()).list();
	}

	@Override
	public BigInteger getCount(Pager p) {
		
		return  (BigInteger) this.getSession().createSQLQuery("select count(*) from pl_manageMoney_plan p "+p.getProperty()+" and p.keystr='mmplan'").uniqueResult();
	}
	@Override
	public List<PlManageMoneyPlan> indexProduct(String sql){
		return this.getSession().createSQLQuery(sql)
		.addScalar("mmplanId", Hibernate.LONG)
		.addScalar("mmName", Hibernate.STRING)
		.addScalar("mmNumber", Hibernate.STRING)
		.addScalar("manageMoneyTypeId", Hibernate.LONG)
		.addScalar("keystr", Hibernate.STRING)
		.addScalar("investScope", Hibernate.STRING)
		.addScalar("benefitWay", Hibernate.STRING)
		.addScalar("buyStartTime", Hibernate.TIMESTAMP)
		.addScalar("buyEndTime", Hibernate.TIMESTAMP)
		.addScalar("startMoney", Hibernate.BIG_DECIMAL)
		.addScalar("riseMoney", Hibernate.BIG_DECIMAL)
		.addScalar("limitMoney", Hibernate.BIG_DECIMAL)
		.addScalar("startinInterestCondition", Hibernate.STRING)
		.addScalar("expireRedemptionWay", Hibernate.STRING)
		.addScalar("chargeGetway", Hibernate.STRING)
		.addScalar("guaranteeWay", Hibernate.STRING)
		.addScalar("yeaRate", Hibernate.BIG_DECIMAL)
		.addScalar("investlimit", Hibernate.INTEGER)
		.addScalar("sumMoney", Hibernate.BIG_DECIMAL)
		.addScalar("state", Hibernate.INTEGER)
		.addScalar("startinInterestTime", Hibernate.DATE)
		.addScalar("endinInterestTime", Hibernate.DATE)
		.addScalar("investedMoney", Hibernate.BIG_DECIMAL)
		.addScalar("htmlPath", Hibernate.STRING)
		.addScalar("createtime", Hibernate.DATE)
		.addScalar("updatetime", Hibernate.DATE)
		.addScalar("isCyclingLend", Hibernate.INTEGER)
		.addScalar("isOne", Hibernate.INTEGER)
		//.addScalar("riskProtectionRate", Hibernate.BIG_DECIMAL)
		.addScalar("earlierOutRate", Hibernate.BIG_DECIMAL)
		.addScalar("maxYearRate", Hibernate.BIG_DECIMAL)
		.addScalar("lockingEndDate", Hibernate.DATE)
		.addScalar("lockingLimit", Hibernate.INTEGER)
		.addScalar("moneyReceiver", Hibernate.STRING)
		//.addScalar("requestNo", Hibernate.STRING)
		//.addScalar("authorityStatus", Hibernate.INTEGER)
		.addScalar("bidRemark", Hibernate.STRING).setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlan.class)).list();
	}

	@Override
	public List<PlManageMoneyPlan> getCurrentList() {
		// TODO Auto-generated method stub
		String hql="SELECT "+
						"p.mmplanId as mmplanId, " +
						"p.mmName as mmName, " +
						"p.sumMoney as sumMoney, " +
						"p.investlimit as investlimit, " +
						"p.yeaRate as yeaRate, " +
						"p.buyStartTime as buyStartTime, " +
						"p.buyEndTime as buyEndTime, " +
						"p.state as state, " +
						"(case when p.sumMoney is not null and p.investedMoney is not null then p.investedMoney*100/p.sumMoney else 0.00 end) as progress, "+
						"p.manageMoneyTypeId as manageMoneyTypeId, " +
						"ppl.`name` as manageMoneyTypeName "+
						" FROM "+
						"pl_managemoney_plan AS p," +
						"( " +
							"SELECT " +
								"pp.manageMoneyTypeId," +
								"max(pp.buyStartTime) AS buyStartTime " +
							"FROM pl_managemoney_plan AS pp " +
							"WHERE " +
								"pp.keystr  in ('mmplan','UPlan') " +
								"AND pp.state IN (1, 2, 6, 7) " +
							"GROUP BY pp.manageMoneyTypeId)" +
							" AS pl "+
						"LEFT JOIN pl_managemoney_type AS ppl ON ( "+
							"ppl.manageMoneyTypeId = pl.manageMoneyTypeId "+
						") "+
					"WHERE 	" +
						"p.manageMoneyTypeId = pl.manageMoneyTypeId " +
						//"AND pl.buyStartTime = p.buyStartTime"+
					" order by p.buyStartTime desc "+
					" LIMIT 4";
	//	System.out.println("产出的sql"+hql);
		return this.getSession().createSQLQuery(hql).
			            addScalar("mmplanId",Hibernate.LONG).
			            addScalar("mmName",Hibernate.STRING).
			            addScalar("sumMoney",Hibernate.BIG_DECIMAL).
			            addScalar("investlimit",Hibernate.INTEGER).
			            addScalar("yeaRate",Hibernate.BIG_DECIMAL).
			            addScalar("buyStartTime",Hibernate.TIMESTAMP).
			            addScalar("buyEndTime",Hibernate.TIMESTAMP).
			            addScalar("state",Hibernate.INTEGER).
			            addScalar("progress",Hibernate.DOUBLE).
			            addScalar("manageMoneyTypeId",Hibernate.LONG).
			            addScalar("manageMoneyTypeName",Hibernate.STRING).
			            setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlan.class)).
			            list();
	}

	@Override
	public List<PlManageMoneyPlan> getUPlanList(String typeId) {
		StringBuffer sql=new StringBuffer("SELECT "+
						" p.mmplanId as mmplanId, " +
						" p.mmName as mmName, " +
						" p.sumMoney as sumMoney, " +
						" p.investlimit as investlimit, " +
						" p.yeaRate as yeaRate, " +
						" p.keystr, " +
						" p.buyStartTime as buyStartTime, " +
						" p.buyEndTime as buyEndTime, " +
						" p.lockingLimit as lockingLimit, " +
						" p.investedMoney as investedMoney, " +
						" p.state as state, " +
						" (case when p.sumMoney is not null and p.investedMoney is not null then p.investedMoney*100/p.sumMoney else 0.00 end) as progress, "+
						" p.manageMoneyTypeId as manageMoneyTypeId, " +
						" ppl.name as manageMoneyTypeName "+
						" FROM "+
						" pl_managemoney_plan AS p" +
						" left join pl_managemoney_type as ppl on ppl.manageMoneyTypeId=p.manageMoneyTypeId " +
						" where p.state>0 and p.keystr='UPlan' ");
		if(null!=typeId && !"".equals(typeId)){
			sql.append(" and p.manageMoneyTypeId ="+typeId);
		}
		sql.append(" order by p.mmplanId desc limit 4");
		return this.getSession().createSQLQuery(sql.toString()).
	        addScalar("mmplanId",Hibernate.LONG).
	        addScalar("mmName",Hibernate.STRING).
	        addScalar("keystr",Hibernate.STRING).
	        addScalar("sumMoney",Hibernate.BIG_DECIMAL).
	        addScalar("investedMoney",Hibernate.BIG_DECIMAL).
	        addScalar("investlimit",Hibernate.INTEGER).
	        addScalar("lockingLimit",Hibernate.INTEGER).
	        addScalar("yeaRate",Hibernate.BIG_DECIMAL).
	        addScalar("buyStartTime",Hibernate.TIMESTAMP).
	        addScalar("buyEndTime",Hibernate.TIMESTAMP).
	        addScalar("state",Hibernate.INTEGER).
	        addScalar("progress",Hibernate.DOUBLE).
	        addScalar("manageMoneyTypeId",Hibernate.LONG).
	        addScalar("manageMoneyTypeName",Hibernate.STRING).
	        setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlan.class)).
	        list();
	}

	@Override
	public String getLogoUrl(String mark) {
		/**
		 * 
		 * String :mark值  pl_managemoney_plan表表名.表主键（查询条件）例：mark = 'pl_managemoney_plan.236'
		 */
			List list=null;
			String ret="";
			Map<String, String> params=new HashMap<String, String>();
			params.put("mark", mark);
			list=this.executeSqlFind("cs_file","webPath",params);
			if(list!=null&&list.size()>0){
				ret= list.get(0).toString();
			}else{
				ret= "0";
			}
			return ret;
	}
	/**
	 *根据收款用户名称查询理财计划列表
	 */
	@Override
	public List<PlManageMoneyPlan> listByMoneyReceiver(String moneyReceiver) {
		// TODO Auto-generated method stub
		String hql = "from PlManageMoneyPlan where moneyReceiver =? ";
		Object objs[] = {moneyReceiver};
		return this.findByHql(hql,objs);
	}
}