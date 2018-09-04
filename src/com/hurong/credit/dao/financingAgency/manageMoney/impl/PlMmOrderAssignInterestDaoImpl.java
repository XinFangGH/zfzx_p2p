package com.hurong.credit.dao.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmOrderAssignInterestDao;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;
import com.thirdPayInterface.CommonRequestInvestRecord;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlMmOrderAssignInterestDaoImpl extends BaseDaoImpl<PlMmOrderAssignInterest> implements PlMmOrderAssignInterestDao{

	public PlMmOrderAssignInterestDaoImpl() {
		super(PlMmOrderAssignInterest.class);
	}

	@Override
	public PlMmOrderAssignInterest getFisrtByOrderId(Long orderId) {
		String hql = "from PlMmOrderAssignInterest where orderId =? order by intentDate ASC limit 1";
		Object objs[] = {orderId};
		List list =  this.findByHql(hql,objs);
		if(list==null||list.size()==0)return null;
		return (PlMmOrderAssignInterest)list.get(0);
	}
 
	@Override
	public BigDecimal getGetMoney(Long orderId) {
		String hql = "select sum(afterMoney) from PlMmOrderAssignInterest where orderId =? and fundType='loanInterest'";
		Object objs[] = {orderId};
		List list =  this.findByHql(hql,objs);
		if(list==null||list.size()==0)return new BigDecimal("0");
		return (BigDecimal)list.get(0);
	}
    /**
     * 查询还款对账金额
     * @param orderId
     * @param mmplanId
     * @param intentDate
     * @param type
     * @return
     */
	@Override
	public BigDecimal findAfterMoney(Long orderId, Long mmplanId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		String sql= "select SUM(bp.afterMoney) from PlMmOrderAssignInterest  as bp where bp.mmplanId = ? " +
	    "and bp.orderId=? and bp.intentDate=? and bp.isCheck=0 and bp.isValid=0 ";
        if(type!=null){
            sql=sql+" and bp.fundType in "+type;
            }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
			 date=sdf.parse(intentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(mmplanId))
		.setParameter(1, orderId).setParameter(2, date).uniqueResult();
	}
    /**
     * 根据计划的ID和计划派息日查询具体派息数据
     * @param mmplanId
     * @param intentDate
     * @return
     */
	@Override
	public ShowManageMoney findByIdDate(Long mmplanId, String intentDate) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" SELECT "+
				" a.mmplanId,"+
				" a.mmName,"+
				" p.sumMoney,"+
				" p.yeaRate as interestRate,"+
				" p.lockingEndDate,"+
				" p.investlimit,"+
				" sum(CASE when a.fundType = 'loanInterest' THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS loanInterest, "+
				" sum(CASE when a.fundType = 'principalRepayment' THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS principal,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS repaymentTotal,"+
				" a.intentDate,"+
				" p.buyStartTime,"+
				" p.keystr,"+
				" p.preSaleTime,"+
				" (p.sumMoney - p.investedMoney) AS remainingMoney,"+
				" (SELECT count(*) FROM pl_managemoneyplan_buyinfo pf WHERE pf.mmplanId = p.mmplanId ) AS joinCount,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.afterMoney) ELSE 0 END ) AS allMoney"+
				" FROM pl_mm_order_assigninterest a"+
				" INNER JOIN ("+
				" SELECT"+
				" pm.mmplanId AS plt, min(pm.intentDate) AS perid"+
				" FROM  pl_mm_order_assigninterest AS pm"+
				" WHERE"+
				" pm.isCheck = 0"+
				" AND pm.isValid = 0"+
			//	" AND pm.incomeMoney > pm.afterMoney"+
				" AND pm.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" GROUP BY pm.mmplanId,pm.intentDate"+
				" ) AS ppp ON ( "+
				" a.mmplanId = ppp.plt"+
				" AND a.intentDate = ppp.perid)"+
				" LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
				" WHERE"+
				" a.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" AND a.isCheck = 0"+
				" AND a.isValid = 0"
				
                );

		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" AND a.mmplanId="+mmplanId);
		}
	
		if(null!=intentDate && !"".equals(intentDate)){
			sql.append(" AND a.intentDate = '"+intentDate+"'");
		}
		sql.append(" GROUP BY a.mmplanId,a.intentDate");
		System.out.println("--->>>"+sql.toString());
			List list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("mmplanId", Hibernate.LONG).
              addScalar("mmName", Hibernate.STRING).
              addScalar("sumMoney", Hibernate.BIG_DECIMAL).
              addScalar("interestRate", Hibernate.BIG_DECIMAL).
              addScalar("lockingEndDate",Hibernate.DATE).
              addScalar("investlimit",Hibernate.INTEGER).
              addScalar("loanInterest", Hibernate.BIG_DECIMAL).
              addScalar("principal", Hibernate.BIG_DECIMAL).
              addScalar("repaymentTotal", Hibernate.BIG_DECIMAL).
              addScalar("intentDate",Hibernate.DATE).
              addScalar("buyStartTime",Hibernate.DATE).
              addScalar("preSaleTime",Hibernate.DATE).
              addScalar("keystr", Hibernate.STRING).
              addScalar("remainingMoney", Hibernate.BIG_DECIMAL).
              addScalar("joinCount",Hibernate.INTEGER).
              addScalar("allMoney", Hibernate.BIG_DECIMAL).
              setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).list();
	ShowManageMoney showManageMoney=new ShowManageMoney();
	if(null!=list && list.size()>0){
		showManageMoney=(ShowManageMoney) list.get(0);
	}
  return showManageMoney;
	}
	/**
	 * 查询个人理财顾问下的UD计划列表总数目
	 * @param loginname
	 * @param request
	 * @return
	 */
	@Override
	public Long findFinancialCount(String loginname, HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		String state=request.getParameter("state");
		 sql=new StringBuffer(" select count(*) from  (SELECT "+
				" a.mmplanId,"+
				" a.mmName"+
				" FROM pl_mm_order_assigninterest a"+
				" INNER JOIN ("+
				" SELECT"+
				" pm.mmplanId AS plt, min(pm.intentDate) AS perid"+
				" FROM  pl_mm_order_assigninterest AS pm"+
				" WHERE"+
				" pm.isCheck = 0"+
				" AND pm.isValid = 0"+
				" AND pm.incomeMoney > pm.afterMoney"+
				" AND pm.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" GROUP BY pm.mmplanId"+
				" ) AS ppp ON ( "+
				" a.mmplanId = ppp.plt"+
				" AND a.intentDate = ppp.perid)"+
				" LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
				" WHERE"+
				" a.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" AND a.isCheck = 0"+
				" AND a.isValid = 0"+
				" AND p.moneyReceiver ='"+loginname+"'"
                );
		 if(null!=state && ("1".equals(state)|| "2".equals(state))){
			  sql=new StringBuffer("   SELECT "+
						"count( p.mmplanId)"+
						" FROM pl_managemoney_plan p"+
						" WHERE p.moneyReceiver ='"+loginname+"'"
		                );
			 
		 }
	   if(null!=state && !"".equals(state)){
				sql.append(" AND p.state = "+Integer.valueOf(state));
			}
		String mmName=request.getParameter("mmName");
		if(null!=mmName && !"".equals(mmName)){
			sql.append(" AND p.mmName like '%"+mmName+"%'");
		}
/*		String intentDate=request.getParameter("intentDate");
		if(null!=intentDate && !"".equals(intentDate)){
			sql.append(" AND a.intentDate = '"+intentDate+"'");
		}
		String mmplanId=request.getParameter("mmplanId");
		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" AND a.mmplanId = "+Long.valueOf(mmplanId));
		}*/
		String isPresale=request.getParameter("isPresale");
		if(null!=isPresale && "ysz".equals(isPresale)){
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
			String s = sd.format(date); 
			sql.append(" AND p.preSaleTime<='"+s+"' AND p.buyStartTime>='"+s+"'");
		}
		if(null!=state && !("1".equals(state) || "2".equals(state))){
		sql.append(" GROUP BY a.mmplanId,a.intentDate) as ww");
		}
	//	System.out.println("--->>>"+sql.toString());
		Long count=new Long("0");
		List list=this.getSession().createSQLQuery(sql.toString()).list();
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				BigInteger c=(BigInteger) list.get(0);
				count=c.longValue();
			}
		}
		return count;
	}
	/**
	 * 查询个人理财顾问下的UD计划列表
	 * @param loginname
	 * @param request
	 * @return
	 */
	@Override
	public List<ShowManageMoney> findFinancialList(String loginname,
			HttpServletRequest request, Pager pager) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		String state=request.getParameter("state");
		     sql=new StringBuffer(" SELECT "+
				" a.mmplanId,"+
				" a.mmName,"+
				" p.sumMoney,"+
				" p.yeaRate as interestRate,"+
				" p.lockingEndDate,"+
				" p.investlimit,"+
				" sum(CASE when a.fundType = 'loanInterest' THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS loanInterest, "+
				" sum(CASE when a.fundType = 'principalRepayment' THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS principal,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney - a.afterMoney) ELSE 0 END ) AS repaymentTotal,"+
				" a.intentDate,"+
				" p.buyStartTime,"+
				" p.keystr,"+
				" p.preSaleTime,"+
				" (p.sumMoney - p.investedMoney) AS remainingMoney,"+
				" (SELECT count(*) FROM pl_managemoneyplan_buyinfo pf WHERE pf.mmplanId = p.mmplanId ) AS joinCount,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.afterMoney) ELSE 0 END ) AS allMoney"+
				" FROM pl_mm_order_assigninterest a"+
				" INNER JOIN ("+
				" SELECT"+
				" pm.mmplanId AS plt, min(pm.intentDate) AS perid"+
				" FROM  pl_mm_order_assigninterest AS pm"+
				" WHERE"+
				" pm.isCheck = 0"+
				" AND pm.isValid = 0"+
				" AND pm.incomeMoney > pm.afterMoney"+
				" AND pm.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" GROUP BY pm.mmplanId"+
				" ) AS ppp ON ( "+
				" a.mmplanId = ppp.plt"+
				" AND a.intentDate = ppp.perid)"+
				" LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
				" WHERE"+
				" a.fundType IN ("+
				" 'loanInterest','principalRepayment')"+
				" AND a.isCheck = 0"+
				" AND a.isValid = 0"+
				" AND p.moneyReceiver ='"+loginname+"'"
                );
		 if(null!=state && ("1".equals(state)|| "2".equals(state))){
			  sql=new StringBuffer(" SELECT "+
						" p.mmplanId,"+
						" p.mmName,"+
						" p.sumMoney,"+
						" p.yeaRate as interestRate,"+
						" p.lockingEndDate,"+
						" p.investlimit,"+
						" 0 AS loanInterest, "+
						" 0 principal,"+
						" 0 AS repaymentTotal,"+
						" null as intentDate,"+
						" p.buyStartTime,"+
						" p.keystr,"+
						" p.preSaleTime,"+
						" (p.sumMoney - p.investedMoney) AS remainingMoney,"+
						" (SELECT count(*) FROM pl_managemoneyplan_buyinfo pf WHERE pf.mmplanId = p.mmplanId ) AS joinCount,"+
						" 0 AS allMoney"+
						" FROM pl_managemoney_plan p"+
						" WHERE p.moneyReceiver ='"+loginname+"'"
		                );
			 
		 }
		
		 if(null!=state && !"".equals(state)){
				sql.append(" AND p.state = "+Integer.valueOf(state));
			}
		String mmName=request.getParameter("mmName");
		if(null!=mmName && !"".equals(mmName)){
			sql.append(" AND p.mmName like '%"+mmName+"%'");
		}
   /*	String intentDate=request.getParameter("intentDate");
		if(null!=intentDate && !"".equals(intentDate)){
			sql.append(" AND a.intentDate = '"+intentDate+"'");
		}
		String mmplanId=request.getParameter("mmplanId");
		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" AND a.mmplanId = "+Long.valueOf(intentDate));
		}*/
		String isPresale=request.getParameter("isPresale");
		if(null!=isPresale && "ysz".equals(isPresale)){
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
			String s = sd.format(date); 
			sql.append(" AND p.preSaleTime<='"+s+"' AND p.buyStartTime>='"+s+"'");
		}
		if(null!=state && !("1".equals(state) || "2".equals(state))){
		sql.append(" GROUP BY a.mmplanId,a.intentDate");
		}
	//	System.out.println("--->>>"+sql.toString());
		if(null!=pager){
			List list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("mmplanId", Hibernate.LONG).
              addScalar("mmName", Hibernate.STRING).
              addScalar("sumMoney", Hibernate.BIG_DECIMAL).
              addScalar("interestRate", Hibernate.BIG_DECIMAL).
              addScalar("lockingEndDate",Hibernate.DATE).
              addScalar("investlimit",Hibernate.INTEGER).
              addScalar("loanInterest", Hibernate.BIG_DECIMAL).
              addScalar("principal", Hibernate.BIG_DECIMAL).
              addScalar("repaymentTotal", Hibernate.BIG_DECIMAL).
              addScalar("intentDate",Hibernate.DATE).
              addScalar("buyStartTime",Hibernate.TIMESTAMP).
              addScalar("preSaleTime",Hibernate.TIMESTAMP).
              addScalar("keystr", Hibernate.STRING).
              addScalar("remainingMoney", Hibernate.BIG_DECIMAL).
              addScalar("joinCount",Hibernate.INTEGER).
              addScalar("allMoney", Hibernate.BIG_DECIMAL).
              setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).setFirstResult((pager.getPageNumber() - 1) * pager.getPageSize()).setMaxResults(pager.getPageSize()).list();
			return list;
		}else{
			List list=this.getSession().createSQLQuery(sql.toString()).
			addScalar("mmplanId", Hibernate.LONG).
            addScalar("mmName", Hibernate.STRING).
            addScalar("sumMoney", Hibernate.BIG_DECIMAL).
            addScalar("interestRate", Hibernate.BIG_DECIMAL).
            addScalar("lockingEndDate",Hibernate.DATE).
            addScalar("investlimit",Hibernate.INTEGER).
            addScalar("loanInterest", Hibernate.BIG_DECIMAL).
            addScalar("principal", Hibernate.BIG_DECIMAL).
            addScalar("repaymentTotal", Hibernate.BIG_DECIMAL).
            addScalar("intentDate",Hibernate.DATE).
            addScalar("buyStartTime",Hibernate.TIMESTAMP).
            addScalar("preSaleTime",Hibernate.TIMESTAMP).
            addScalar("keystr", Hibernate.STRING).
            addScalar("remainingMoney", Hibernate.BIG_DECIMAL).
            addScalar("joinCount",Hibernate.INTEGER).
            addScalar("allMoney", Hibernate.BIG_DECIMAL).
            setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).list();
			return list;
		}
	}
	/**
	 * UD计划个人理财专户的回款计划查询
	 * @param mmPlanId
	 * @param loginname
	 * @return
	 */
	@Override
	public List<ShowManageMoney> findLoanRepayMemtList(Long mmplanId, String loginname) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" SELECT "+
				" a.mmplanId,"+
				" sum(CASE WHEN a.fundType = 'loanInterest' THEN  (a.incomeMoney) ELSE 0 END 	) AS loanInterest,"+
				" sum(CASE WHEN a.fundType = 'principalRepayment' THEN  (a.incomeMoney) ELSE 0 END 	) AS principal,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney) ELSE 0 END ) AS intenttotal,"+
				" a.intentDate,"+
				" a.factDate,"+
				" a.periods as payintentPeriod,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.afterMoney) ELSE 0 END ) AS backmoney,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney-a.afterMoney) ELSE 0 END ) AS repaymentTotal,"+
				" null as loanerRepayMentStatus,"+
				" 0 as compensationMoney"+
				" FROM"+
				" pl_mm_order_assigninterest a"+
				" LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
				" WHERE"+
				" a.fundType IN ("+
				" 'loanInterest',"+
				" 'principalRepayment'"+
				"  )"+
				"  AND a.isCheck = 0"+
				"  AND a.isValid = 0"+
				"  AND p.state = 7"+
				"  AND p.moneyReceiver ='"+loginname+"'"
                );

		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" AND a.mmplanId="+mmplanId);
		}
		sql.append(" GROUP BY a.mmplanId,a.intentDate");
		System.out.println("--->>"+sql.toString());
		return this.getSession().createSQLQuery(sql.toString()). 
		  addScalar("mmplanId",Hibernate.LONG).
		  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
		  addScalar("payintentPeriod",Hibernate.INTEGER).
		  addScalar("intentDate",Hibernate.DATE).
		  addScalar("factDate",Hibernate.DATE).
		  addScalar("principal",Hibernate.BIG_DECIMAL).
		  addScalar("loanInterest",Hibernate.BIG_DECIMAL).
		  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
		  addScalar("intenttotal",Hibernate.BIG_DECIMAL).
		  addScalar("backmoney",Hibernate.BIG_DECIMAL).
		  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
		  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
		  list();
	}

	
	/**
	 * UD计划个人理财专户的回款计划查询
	 * @param mmPlanId
	 * @param loginname
	 * @return
	 */
	@Override
	public List<ShowManageMoney> findLoanRepayMemtList1(Long mmplanId, String loginname) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" SELECT "+
				" a.mmplanId,"+
				" sum(CASE WHEN a.fundType = 'loanInterest' THEN  (a.incomeMoney) ELSE 0 END 	) AS loanInterest,"+
				" sum(CASE WHEN a.fundType = 'principalRepayment' THEN  (a.incomeMoney) ELSE 0 END 	) AS principal,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney) ELSE 0 END ) AS intenttotal,"+
				" a.intentDate,"+
				" a.factDate,"+
				" a.periods as payintentPeriod,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.afterMoney) ELSE 0 END ) AS backmoney,"+
				" sum(CASE when (a.fundType = 'principalRepayment' or a.fundType = 'loanInterest') THEN (a.incomeMoney-a.afterMoney) ELSE 0 END ) AS repaymentTotal,"+
				" null as loanerRepayMentStatus,"+
				" 0 as compensationMoney"+
				" FROM"+
				" pl_mm_order_assigninterest a"+
				" LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
				" WHERE"+
				" a.fundType IN ("+
				" 'loanInterest',"+
				" 'principalRepayment'"+
				"  )"+
				"  AND a.isCheck = 0"+
				"  AND a.isValid = 0"+
				"  AND p.state = 10"+
				"  AND p.moneyReceiver ='"+loginname+"'"
                );

		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" AND a.mmplanId="+mmplanId);
		}
		sql.append(" GROUP BY a.mmplanId,a.intentDate");
		System.out.println("--->>"+sql.toString());
		return this.getSession().createSQLQuery(sql.toString()). 
		  addScalar("mmplanId",Hibernate.LONG).
		  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
		  addScalar("payintentPeriod",Hibernate.INTEGER).
		  addScalar("intentDate",Hibernate.DATE).
		  addScalar("factDate",Hibernate.DATE).
		  addScalar("principal",Hibernate.BIG_DECIMAL).
		  addScalar("loanInterest",Hibernate.BIG_DECIMAL).
		  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
		  addScalar("intenttotal",Hibernate.BIG_DECIMAL).
		  addScalar("backmoney",Hibernate.BIG_DECIMAL).
		  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
		  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
		  list();
	}

	
	
	/**
	 * 很据登录名和资金类型查询金额
	 * @param loginname
	 * @param fundType
	 * @return
	 */
	@Override
	public BigDecimal findMoneyByType(String loginname, String fundType) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" SELECT "+
				" IFNULL("+
			    " sum(a.incomeMoney - a.afterMoney),0)"+
			    " FROM"+
			    " pl_mm_order_assigninterest a"+
			    " LEFT JOIN pl_managemoney_plan p ON a.mmplanId = p.mmplanId"+
			    " WHERE"+
			    " p.moneyReceiver ='"+loginname+"'"+
			    " AND a.isCheck = 0"+
			    " AND a.isValid = 0"+
			    " AND a.incomeMoney > a.afterMoney"
                );
        if(null!=fundType && !"".equals(fundType)){
            sql.append(" AND a.fundType='"+fundType+"'");
            }
         // System.out.println(sql.toString());
         return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult();
	}
    /**
     * 查询还款金额
     * @param orderId
     * @param mmplanId
     * @param intentDate
     * @param type
     * @return
     */
	@Override
	public BigDecimal findReturnMoney( Long mmplanId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		String sql= "select SUM(bp.incomeMoney - bp.afterMoney) from PlMmOrderAssignInterest  as bp where bp.mmplanId = ? " +
	    " and bp.intentDate=? and bp.isCheck=0 and bp.isValid=0 ";
        if(type!=null){
            sql=sql+" and bp.fundType in "+type;
            }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
			 date=sdf.parse(intentDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(mmplanId))
		.setParameter(1, date).uniqueResult();
	}
	/**
	 * 查询个人理财顾问下的UD计划(按标的状态)
	 * @param loginname
	 * @param stste
	 * @return
	 */
	@Override
	public Long getBidCountFinancial(String loginname, Integer state,
			String isPresale) {
		// TODO Auto-generated method stub
	 	StringBuffer sql=new StringBuffer(" SELECT "+
				"count( p.mmplanId)"+
				" FROM pl_managemoney_plan p"+
				" WHERE p.moneyReceiver ='"+loginname+"'"
                );
        if(null!=state && !"".equals(state)){
		        sql.append(" AND p.state = "+state);
	    }
       if(null!=isPresale && "ysz".equals(isPresale)){
	       SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Date date=new Date();
	       String s = sd.format(date); 
	       sql.append(" AND p.preSaleTime<='"+s+"' AND p.buyStartTime>='"+s+"'");
         }
//	System.out.println("--->>>"+sql.toString());
        Long count=new Long("0");
        List list=this.getSession().createSQLQuery(sql.toString()).list();
       if(null!=list && list.size()>0){
	       if(null!=list.get(0)){
		      BigInteger c=(BigInteger) list.get(0);
		      count=c.longValue();
	         }
           }
          return count;
	}
	/**
	 * 查询某个投资人某次交易记录为对完账的款项记录
	 * @param orderId
	 * @param investPersonId
	 * @return
	 */
	@Override
	public List<PlMmOrderAssignInterest> getByDealCondition(Long orderId,
			Long investPersonId) {
		// TODO Auto-generated method stub
		String hql="from PlMmOrderAssignInterest as m where m.orderId=? and m.investPersonId=? and m.incomeMoney>m.afterMoney  and m.isValid=0 and m.isCheck=0 ";
		return this.getSession().createQuery(hql).setParameter(0, orderId).setParameter(1, investPersonId).list();
	}
	/**
	 * 根据流水查询款项记录
	 * @param requestNo
	 * @return
	 */
	@Override
	public List<PlMmOrderAssignInterest> getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		String sql = " from PlMmOrderAssignInterest as fund where fund.requestNo=?";
		return this.getSession().createQuery(sql).setParameter(0, requestNo).list();
	}
	/**
	 * 查询款项记录
	 * @param mmPlanId
	 * @param intentDate
	 * @param requestNo
	 * @param fundType
	 * @return
	 */
	@Override
	public List<PlMmOrderAssignInterest> getCouponsIntent(String mmPlanId,
			Date intentDate, String requestNo, String fundType) {
		// TODO Auto-generated method stub
		String sql = "";
		String s="";
		if(null!=intentDate && !"".equals(intentDate)){
			SimpleDateFormat  sd = new SimpleDateFormat("yyyy-MM-dd");
			 s=sd.format(intentDate);
		}
		if(fundType!=null&&!fundType.equals("")){
			if(intentDate!=null&&!intentDate.equals("")){
				// sql="from PlMmOrderAssignInterest AS fund where fund.mmplanId="+Long.valueOf(mmPlanId)+" and  fund.requestNo='"+requestNo+"'"+" and  fund.fundType="+fundType+" and fund.factDate is  null  and fund.isValid=0 and fund.intentDate='"+s+"'";
				 sql = " from PlMmOrderAssignInterest as fund where fund.mmplanId=?  and fund.requestNo=? and fund.fundType=? and fund.factDate is  null  and fund.isValid=0 and fund.intentDate='"+s+"'";
				 System.out.println("deedede---->>>"+sql);
			//	 return (List<PlMmOrderAssignInterest>) this.getSession().createQuery(sql).list();
				 
				 return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(mmPlanId)).setParameter(1, requestNo).setParameter(2, fundType).list();
			}else{
				if(fundType.equals("0")){
					sql = " from PlMmOrderAssignInterest as fund where fund.mmplanId=?  and fund.orderNo=? and fund.fundType " +
							" in('couponInterest','principalCoupons','subjoinInterest') and fund.factDate is  null ";
				}else{
					sql = " from PlMmOrderAssignInterest as fund where fund.mmplanId?  and fund.orderNo=? and fund.fundType " +
							" in ('couponInterest','principalCoupons','subjoinInterest','commoninterest') and fund.factDate is  null ";
				}
				return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(mmPlanId)).setParameter(1, requestNo).list();
			}
		}else{
			 sql = " from PlMmOrderAssignInterest as fund where fund.mmplanId=?  and fund.requestNo=? and fund.factDate is  null  and fund.intentDate>'"+s+"'";
			return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(mmPlanId)).setParameter(1, requestNo).list();
		}
	}
	/**
	 *查询某个理财计划某种类型金额总和
	 * @param mmplanId
	 * @param type
	 * @return
	 */
	@Override
	public BigDecimal getFinancialMoney(Long mmplanId, String type) {
		// TODO Auto-generated method stub
		String sql = "";
		if("afterMoney".equals(type)){
			sql = "select SUM(intent.afterMoney) from pl_mm_order_assigninterest as intent  where intent.mmplanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("payMoney".equals(type)){
			sql = "select SUM(intent.payMoney) from pl_mm_order_assigninterest as intent where intent.mmplanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("notMoney".equals(type)){
			sql = "select SUM(intent.incomeMoney-intent.afterMoney) from pl_mm_order_assigninterest as intentt where intent.mmplanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("all".equals(type)){
			sql = "select SUM(intent.incomeMoney) from pl_mm_order_assigninterest as intent where intent.mmplanId=? and  intent.fundType in ('loanInterest','principalRepayment') ";
		}
	//	System.out.println("--->>>"+sql);
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, mmplanId).uniqueResult();
	}
	/**
	 *UD计划收款账户还款时款项查询
	 * @param mmplanId
	 * @param intentDate
	 * @return
	 */
	@Override
	public List<CommonRequestInvestRecord> getRepaymentList(Long mmplanId,
			String intentDate) {
		// TODO Auto-generated method stub
		List<CommonRequestInvestRecord> list=new ArrayList<CommonRequestInvestRecord>();
		StringBuffer sql=new StringBuffer(" SELECT"+
				" bf.intentDate AS intentDate,"+
				" bf.assignInterestId,"+
				" bm.thirdPayFlagId AS invest_thirdPayConfigId,"+
				" null as  requestDate,"+
				" info.DealInfoNumber  AS bidRequestNo,"+
				" sum(IF (bf.fundType  IN ('principalRepayment','loanInterest'),(bf.incomeMoney-bf.afterMoney),0)) AS amount,"+
				" sum(IF (bf.fundType  IN ('principalRepayment'),(bf.incomeMoney-bf.afterMoney),0)) AS principal,"+
				" sum(IF (bf.fundType  IN ('loanInterest'),(bf.incomeMoney-bf.afterMoney),0)) AS interest,"+
				"  0 AS fee"+
				" FROM "+
				" pl_managemoneyplan_buyinfo AS info"+
				" LEFT JOIN bp_cust_member AS bm ON bm.id = info.investPersonId"+
				" LEFT JOIN pl_mm_order_assigninterest AS bf ON bf.mmplanId = info.mmplanId"+
				" AND info.orderId = bf.orderId"+
				" AND bf.isCheck = 0"+
				" AND bf.isCheck = 0 where info.state=2 "
			);
		if(null!=intentDate && !"".equals(intentDate)){
			sql.append(" and bf.intentDate='"+intentDate+"'");
		}
		if(null!=mmplanId && !"".equals(mmplanId)){
			sql.append(" and info.mmplanId="+mmplanId);
		}
		sql.append(" GROUP BY info.orderId");
	//	System.out.println("还款sql="+sql);
		list=this.getSession().createSQLQuery(sql.toString())
			.addScalar("invest_thirdPayConfigId",Hibernate.STRING)
			.addScalar("bidRequestNo",Hibernate.STRING)
			.addScalar("requestDate",Hibernate.DATE)
			.addScalar("assignInterestId",Hibernate.LONG)
			.addScalar("amount",Hibernate.BIG_DECIMAL)
			.addScalar("principal", Hibernate.BIG_DECIMAL)
			.addScalar("interest",Hibernate.BIG_DECIMAL)			
			.addScalar("fee",Hibernate.BIG_DECIMAL)
			.addScalar("intentDate",Hibernate.DATE)
			.setResultTransformer(Transformers.aliasToBean(CommonRequestInvestRecord.class))
			.list();
		
		return list;
	}

	@Override
	public List<PlMmOrderAssignInterest> getByDealCondition(Long mmplanId) {
		// TODO Auto-generated method stub
		String hql="from PlMmOrderAssignInterest as m where m.mmplanId=?  and m.incomeMoney>=m.afterMoney  and m.isValid=0 and m.isCheck=0 ";
		return this.getSession().createQuery(hql).setParameter(0, mmplanId).list();
	}

	@Override
	public List<PlMmOrderAssignInterest> listByEarlyDate(String earlyDate,
			Long orderId, String fundType, Long earlyRedemptionId) {
		// TODO Auto-generated method stub
		if(null==earlyDate){
			String hql="from PlMmOrderAssignInterest as m where m.orderId=? and m.fundType in"+fundType+" and (m.earlyRedemptionId is null or m.earlyRedemptionId!=?) order by m.intentDate asc";
			return this.findByHql(hql, new Object[]{orderId,earlyRedemptionId});
			
		}else{
			
			String hql="from PlMmOrderAssignInterest as m where m.orderId=? and m.intentDate"+earlyDate+" and m.fundType in"+fundType+" and (m.earlyRedemptionId is null or m.earlyRedemptionId!=?) order by m.intentDate asc";
			return this.findByHql(hql, new Object[]{orderId,earlyRedemptionId});
		}
	}

	@Override
	public List<PlMmOrderAssignInterest> listByOrderIdAndFundType(
			String orderId, String fundType) {
		// TODO Auto-generated method stub
		String hql = "from PlMmOrderAssignInterest where orderId =? and fundType = ? ";
		return this.findByHql(hql,new Object[]{Long.valueOf(orderId),fundType} );
	}

	@Override
	public List<PlMmOrderAssignInterest> getByPlanIdA(Long orderId,
			Long investPersonId, Long mmplanId, String fundType, Integer periods) {
		// TODO Auto-generated method stub
		if(fundType!=null&&!fundType.equals("")&&periods==null){
			String hql = "from PlMmOrderAssignInterest as bf where bf.orderId = ? and bf.investPersonId=? and bf.mmplanId=? and bf.fundType in ("+fundType+") and bf.factDate is null";
			return this.findByHql(hql, new Object[]{orderId,investPersonId,mmplanId});
		}else if(fundType!=null&&!fundType.equals("")&&periods !=null && !"".equals(periods)){
			String hql = "from PlMmOrderAssignInterest as bf where bf.orderId = ? and bf.investPersonId=? and bf.mmplanId=? and bf.fundType in ("+fundType+") and bf.factDate is null and bf.periods=?";
			return this.findByHql(hql, new Object[]{orderId,investPersonId,mmplanId,periods});
		}else{
			String hql = "from PlMmOrderAssignInterest as bf where bf.orderId = ? and bf.investPersonId=? and bf.mmplanId=? " +
					" and bf.fundType in('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest') and bf.factDate is null";
			return this.findByHql(hql, new Object[]{orderId,investPersonId,mmplanId});
		}
	}

	@Override
	public List<PlMmOrderAssignInterest> getListByBidIdAndPeriod(String bidId,
			String periodId,Date intentDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(intentDate);
		String sql  = "";
		List<PlMmOrderAssignInterest> list = new ArrayList<PlMmOrderAssignInterest>();
		if(periodId!=null && !"".equals(periodId)){
			sql	= "from PlMmOrderAssignInterest as interest where interest.mmplanId = ? and interest.isCheck=0 and interest.isValid=0  and interest.periods=?";
			list = this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).setParameter(1, Integer.valueOf(periodId)).list();
		}else{
			 sql = "from PlMmOrderAssignInterest as interest where interest.mmplanId = ? and interest.intentDate = '"+date+"' and interest.isCheck=0 and interest.isValid=0";
			 list = this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).list();
		}
		return list;
	}

	@Override
	public List<PlMmOrderAssignInterest> getByInvestNo(String requestNo) {
		String sql = "from PlMmOrderAssignInterest as interest where interest.investRequestNo = ? and interest.isCheck=0 and interest.isValid=0";
		List<PlMmOrderAssignInterest> list = new ArrayList<PlMmOrderAssignInterest>();
		list = this.getSession().createQuery(sql).setParameter(0,requestNo).list();
		return list;
	}

}