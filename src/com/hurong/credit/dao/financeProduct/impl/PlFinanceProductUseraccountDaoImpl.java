package com.hurong.credit.dao.financeProduct.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUseraccountDao;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.p2p.BpPersonCenterData;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlFinanceProductUseraccountDaoImpl extends BaseDaoImpl<PlFinanceProductUseraccount> implements PlFinanceProductUseraccountDao{

	public PlFinanceProductUseraccountDaoImpl() {
		super(PlFinanceProductUseraccount.class);
	}

	@Override
	public List<PlFinanceProductUseraccount> getUserAccountList(
			HttpServletRequest request, PagingBean pb) {
	   String fistDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"first");
	   String endDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"end");
		// TODO Auto-generated method stub
		String sql="SELECT "+
					"p.id AS id, "+
					"p.userId AS userId, "+
					"p.userName AS userName, "+
					"p.userloginName AS userloginName, "+
					"p.openDate AS openDate, "+
					"p.modifyDate AS modifyDate, "+
					"p.productId AS productId, "+
					"p.productName AS productName, "+
					"p.accountStatus AS accountStatus, "+
					"p.companyId AS companyId, "+
					"sum(case when pinfo.dealStatus>0 then pinfo.amont else 0.0 END) AS currentMoney, "+
					"sum(case when pinfo.dealtype=3 then pinfo.amont else 0.0 END ) AS totalIntestMoney, "+
					"sum(case when pinfo.dealtype=1 then pinfo.amont else 0.0 END ) AS incomeMoney, "+
					"-sum(case when pinfo.dealtype=2 then pinfo.amont else 0.0 END) AS payMoney, "+
					"sum(case when (pinfo.dealtype=3 and pinfo.dealDate BETWEEN '"+fistDay+"' and '"+endDay+"' ) then pinfo.amont else 0.0 END) AS yesterdayEarn, "+
					"sum(case when pinfo.dealStatus=2 and pinfo.dealStatus=0 then pinfo.amont else 0.0 END) AS intestMoney, "+
					"sum(case when pinfo.dealStatus=1 then pinfo.amont else 0.0 END) AS onwayMoney "+
					"FROM  "+
					"`pl_finance_product_useraccount` AS p "+
					"LEFT JOIN pl_finance_product_useraccountinfo AS pinfo ON (p.userId = pinfo.userId and pinfo.dealStatus>=0)  where 1=1 ";
		
		    if(request!=null){
		    	String openDateS=request.getParameter("openDateS");
		    	if(openDateS!=null&&!"".equals(openDateS)){
		    		sql=sql+" and p.openDate>='"+openDateS+" 00:00:00'";
		    	}
		    	String openDateE=request.getParameter("openDateE");
		    	if(openDateE!=null&&!"".equals(openDateE)){
		    		sql=sql+" and p.openDate<='"+openDateE+" 23:59:59'";
		    	}
		    	String userName=request.getParameter("userName");
		    	if(userName!=null&&!"".equals(userName)){
		    		sql=sql+" and p.userName like \"%"+userName+"%\"";
		    	}
		    	String loginName=request.getParameter("loginName");
		    	if(loginName!=null&&!"".equals(loginName)){
		    		sql=sql+" and p.userloginName like \"%"+loginName+"%\"";
		    	}
		    }
		
		     sql=sql +"  GROUP BY p.userId, p.productId";
		     System.out.println(sql);
		     List<PlFinanceProductUseraccount> list=this.getSession().createSQLQuery(sql).
		     										addScalar("id",Hibernate.LONG).
		     										addScalar("userId", Hibernate.LONG).
		     										addScalar("userName", Hibernate.STRING).
		     										addScalar("userloginName", Hibernate.STRING).
		     										addScalar("openDate", Hibernate.TIMESTAMP).
		     										addScalar("modifyDate", Hibernate.TIMESTAMP).
		     										addScalar("productId", Hibernate.LONG).
		     										addScalar("productName", Hibernate.STRING).
		     										addScalar("accountStatus", Hibernate.SHORT).
		     										addScalar("companyId", Hibernate.LONG).
		     										addScalar("currentMoney", Hibernate.BIG_DECIMAL).
		     										addScalar("totalIntestMoney", Hibernate.BIG_DECIMAL).
		     										addScalar("incomeMoney", Hibernate.BIG_DECIMAL).
		     										addScalar("payMoney", Hibernate.BIG_DECIMAL).
		     										addScalar("yesterdayEarn", Hibernate.BIG_DECIMAL).
		     										addScalar("intestMoney", Hibernate.BIG_DECIMAL).
		     										addScalar("onwayMoney", Hibernate.BIG_DECIMAL).
		     										setResultTransformer(Transformers.aliasToBean(PlFinanceProductUseraccount.class)).
		     									list();
		     if(pb!=null&&pb.getStart()!=null&&pb.getPageSize()!=null){
		    	 pb.setTotalItems(list!=null?list.size():0);
		    	 List<PlFinanceProductUseraccount> listP=this.getSession().createSQLQuery(sql).
							addScalar("id",Hibernate.LONG).
							addScalar("userId", Hibernate.LONG).
							addScalar("userName", Hibernate.STRING).
							addScalar("userloginName", Hibernate.STRING).
							addScalar("openDate", Hibernate.TIMESTAMP).
							addScalar("modifyDate", Hibernate.TIMESTAMP).
							addScalar("productId", Hibernate.LONG).
							addScalar("productName", Hibernate.STRING).
							addScalar("accountStatus", Hibernate.SHORT).
							addScalar("companyId", Hibernate.LONG).
							addScalar("currentMoney", Hibernate.BIG_DECIMAL).
							addScalar("totalIntestMoney", Hibernate.BIG_DECIMAL).
							addScalar("incomeMoney", Hibernate.BIG_DECIMAL).
							addScalar("payMoney", Hibernate.BIG_DECIMAL).
							addScalar("yesterdayEarn", Hibernate.BIG_DECIMAL).
							addScalar("intestMoney", Hibernate.BIG_DECIMAL).
							addScalar("onwayMoney", Hibernate.BIG_DECIMAL).
							setResultTransformer(Transformers.aliasToBean(PlFinanceProductUseraccount.class)).
							setFirstResult(pb.getStart()).
							setMaxResults(pb.getPageSize()).
							list();
		    	 return listP; 
		     }else{
		    	 return list; 
		     }
		
	}
	public BigDecimal findBidPlanMoney(Long guarantorsId, String states) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("select IFNULL(SUM(p.bidMoney),0) from pl_bid_plan p where 1=1");
		if(null!=guarantorsId && !"".equals(guarantorsId)){
			  sql.append(" and p.guarantorsId="+guarantorsId);
		}
		if(null!=states && !"".equals(states)){
			  sql.append(" and p.state in "+states);
		}
		return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult(); 
	}

	/*@Override
	public BigDecimal getPersonAccount1(Long id) {
		String something ="收益";
		String sql="SELECT 	sum(pinfo.currentMoney)"
			+" FROM "
			+"  	pl_finance_product_useraccountinfo pinfo "
			+"  WHERE "
			+" 		pinfo.userId = "+id
			+"  AND pinfo.dealtypeName = '"+something+"' "
			+"  AND pinfo.dealStatus = 2 "
			+"  AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < DATE(pinfo.dealDate)";
		System.out.println("<<<<<<"+sql);
		return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult();
	}*/

	@Override
	public BpPersonCenterData getPersonAccount1(Long id) {
		// TODO Auto-generated method stub
		   String something ="收益";
		   String fistDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"first");
		   String endDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"end");
			// TODO Auto-generated method stub earningsYesterday
		   String sql="SELECT table1.currentMoney as  accountBalance,"
			+"table1.yesterdayEarn as earningsYesterday, "
			+"table1.totalIntestMoney as accumulatedEarnings, "
			+"table1.onwayMoney as leviticusInterestMoney, "
			+"table2.currentMoney1  as nearlyMoney "
			+"FROM (" +
					"SELECT "+
						"p.id AS id, "+
						"p.userId AS userId, "+
						"p.userName AS userName, "+
						"p.userloginName AS userloginName, "+
						"p.openDate AS openDate, "+
						"p.modifyDate AS modifyDate, "+
						"p.productId AS productId, "+
						"p.productName AS productName, "+
						"p.accountStatus AS accountStatus, "+
						"p.companyId AS companyId, "+
						"sum(case when pinfo.dealStatus>0 then pinfo.amont else 0.0 END) AS currentMoney, "+
						"sum(case when pinfo.dealtype=3 then pinfo.amont else 0.0 END ) AS totalIntestMoney, "+
						"sum(case when pinfo.dealtype=1 then pinfo.amont else 0.0 END ) AS incomeMoney, "+
						"-sum(case when pinfo.dealtype=2 then pinfo.amont else 0.0 END) AS payMoney, "+
						"sum(case when (pinfo.dealtype=3 and pinfo.dealDate BETWEEN '"+fistDay+"' and '"+endDay+"' ) then pinfo.amont else 0.0 END) AS yesterdayEarn, "+
						"sum(case when pinfo.dealStatus=2 and pinfo.dealStatus=0 then pinfo.amont else 0.0 END) AS intestMoney, "+
						"sum(case when pinfo.dealStatus=1 then pinfo.amont else 0.0 END) AS onwayMoney "+
						"FROM  "+
						"`pl_finance_product_useraccount` AS p "+
						"LEFT JOIN pl_finance_product_useraccountinfo AS pinfo ON (p.userId = pinfo.userId and pinfo.dealStatus>=0)  where p.userId= "+id +
			     		" GROUP BY p.userId"
						+") AS table1,  "
						+" (  "
						+"SELECT 	sum(pinfo.amont) as  currentMoney1"
						+" FROM "
						+"  	pl_finance_product_useraccountinfo pinfo "
						+"  WHERE "
						+" 		pinfo.userId = "+id
						+"  AND pinfo.dealtype = '3' "
						+"  AND pinfo.dealStatus = 2 "
						+"  AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) < DATE(pinfo.dealDate)) as table2";
		   
			/*String sql="SELECT "+
						"p.id AS id, "+
						"p.userId AS userId, "+
						"p.userName AS userName, "+
						"p.userloginName AS userloginName, "+
						"p.openDate AS openDate, "+
						"p.modifyDate AS modifyDate, "+
						"p.productId AS productId, "+
						"p.productName AS productName, "+
						"p.accountStatus AS accountStatus, "+
						"p.companyId AS companyId, "+
						"sum(case when pinfo.dealStatus>0 then pinfo.amont else 0.0 END) AS currentMoney, "+
						"sum(case when pinfo.dealtype=3 then pinfo.amont else 0.0 END ) AS totalIntestMoney, "+
						"sum(case when pinfo.dealtype=1 then pinfo.amont else 0.0 END ) AS incomeMoney, "+
						"-sum(case when pinfo.dealtype=2 then pinfo.amont else 0.0 END) AS payMoney, "+
						"sum(case when (pinfo.dealtype=3 and pinfo.dealDate BETWEEN '"+fistDay+"' and '"+endDay+"' ) then pinfo.amont else 0.0 END) AS yesterdayEarn, "+
						"sum(case when pinfo.dealStatus=2 and pinfo.dealStatus=0 then pinfo.amont else 0.0 END) AS intestMoney, "+
						"sum(case when pinfo.dealStatus=1 then pinfo.amont else 0.0 END) AS onwayMoney "+
						"FROM  "+
						"`pl_finance_product_useraccount` AS p "+
						"LEFT JOIN pl_finance_product_useraccountinfo AS pinfo ON (p.userId = pinfo.userId and pinfo.dealStatus>=0)  where p.userId= "+id;
			     sql=sql +" GROUP BY p.userId";*/
		   
		    /*nearlyMoney;//近一个月赚取
			earningsYesterday;//昨日收益
			accountBalance;//账户余额
			accumulatedEarnings;//累计收益
			leviticusInterestMoney;//尚未记息金额
		   */
			     System.out.println(">>>>"+sql);
			     BpPersonCenterData list=(BpPersonCenterData) this.getSession().createSQLQuery(sql).
					addScalar("nearlyMoney", Hibernate.BIG_DECIMAL).
					addScalar("earningsYesterday", Hibernate.BIG_DECIMAL).
					addScalar("accountBalance", Hibernate.BIG_DECIMAL).
					addScalar("accumulatedEarnings", Hibernate.BIG_DECIMAL).
					addScalar("leviticusInterestMoney", Hibernate.BIG_DECIMAL).
					setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
			   return list; 
			     
	}
	
	@Override
	public PlFinanceProductUseraccount getPersonAccount(Long id) {
		// TODO Auto-generated method stub
		   String fistDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"first");
		   String endDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"end");
			// TODO Auto-generated method stub
			String sql="SELECT "+
						"p.id AS id, "+
						"p.userId AS userId, "+
						"p.userName AS userName, "+
						"p.userloginName AS userloginName, "+
						"p.openDate AS openDate, "+
						"p.modifyDate AS modifyDate, "+
						"p.productId AS productId, "+
						"p.productName AS productName, "+
						"p.accountStatus AS accountStatus, "+
						"p.companyId AS companyId, "+
						"sum(case when pinfo.dealStatus>0 then pinfo.amont else 0.0 END) AS currentMoney, "+
						"sum(case when pinfo.dealtype=3 then pinfo.amont else 0.0 END ) AS totalIntestMoney, "+
						"sum(case when pinfo.dealtype=1 then pinfo.amont else 0.0 END ) AS incomeMoney, "+
						"-sum(case when pinfo.dealtype=2 then pinfo.amont else 0.0 END) AS payMoney, "+
						"sum(case when (pinfo.dealtype=3 and pinfo.dealDate BETWEEN '"+fistDay+"' and '"+endDay+"' ) then pinfo.amont else 0.0 END) AS yesterdayEarn, "+
						"sum(case when pinfo.dealStatus=2 and pinfo.dealStatus=0 then pinfo.amont else 0.0 END) AS intestMoney, "+
						"sum(case when pinfo.dealStatus=1 then pinfo.amont else 0.0 END) AS onwayMoney "+
						"FROM  "+
						"`pl_finance_product_useraccount` AS p "+
						"LEFT JOIN pl_finance_product_useraccountinfo AS pinfo ON (p.userId = pinfo.userId and pinfo.dealStatus>=0)  where p.userId= "+id;
			     sql=sql +" GROUP BY p.userId";
			     System.out.println(sql);
			     PlFinanceProductUseraccount list=(PlFinanceProductUseraccount) this.getSession().createSQLQuery(sql).
			     										addScalar("id",Hibernate.LONG).
			     										addScalar("userId", Hibernate.LONG).
			     										addScalar("userName", Hibernate.STRING).
			     										addScalar("userloginName", Hibernate.STRING).
			     										addScalar("openDate", Hibernate.TIMESTAMP).
			     										addScalar("modifyDate", Hibernate.TIMESTAMP).
			     										addScalar("productId", Hibernate.LONG).
			     										addScalar("productName", Hibernate.STRING).
			     										addScalar("accountStatus", Hibernate.SHORT).
			     										addScalar("companyId", Hibernate.LONG).
			     										addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("totalIntestMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("incomeMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("payMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("yesterdayEarn", Hibernate.BIG_DECIMAL).
			     										addScalar("intestMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("onwayMoney", Hibernate.BIG_DECIMAL).
			     										setResultTransformer(Transformers.aliasToBean(PlFinanceProductUseraccount.class)).uniqueResult();
			   
			   return list; 
			     
	}


	//查询用户是否具备这些账户
	@Override
	public PlFinanceProductUseraccount getProductUserAccount(Long id,Long productId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		   String fistDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"first");
		   String endDay=DateUtil.getFirstDate(DateUtil.addDaysToDate(new Date(), -1),"end");
			// TODO Auto-generated method stub
			String sql="SELECT "+
						"p.id AS id, "+
						"p.userId AS userId, "+
						"p.userName AS userName, "+
						"p.userloginName AS userloginName, "+
						"p.openDate AS openDate, "+
						"p.modifyDate AS modifyDate, "+
						"p.productId AS productId, "+
						"p.productName AS productName, "+
						"p.accountStatus AS accountStatus, "+
						"p.companyId AS companyId, "+
						"sum(case when pinfo.dealStatus>0 then pinfo.amont else 0.0 END) AS currentMoney, "+
						"sum(case when pinfo.dealtype=3 then pinfo.amont else 0.0 END ) AS totalIntestMoney, "+
						"sum(case when pinfo.dealtype=1 then pinfo.amont else 0.0 END ) AS incomeMoney, "+
						"-sum(case when pinfo.dealtype=2 then pinfo.amont else 0.0 END) AS payMoney, "+
						"sum(case when (pinfo.dealtype=3 and pinfo.dealDate BETWEEN '"+fistDay+"' and '"+endDay+"' ) then pinfo.amont else 0.0 END) AS yesterdayEarn, "+
						"sum(case when pinfo.dealStatus=2 and pinfo.dealStatus=0 then pinfo.amont else 0.0 END) AS intestMoney, "+
						"sum(case when pinfo.dealStatus=1 then pinfo.amont else 0.0 END) AS onwayMoney "+
						"FROM  "+
						"`pl_finance_product_useraccount` AS p "+
						"LEFT JOIN pl_finance_product_useraccountinfo AS pinfo ON (p.userId = pinfo.userId and pinfo.dealStatus>=0 and pinfo.productId="+productId+")  where p.userId= "+id +" and p.productId="+productId ;
			     sql=sql +" GROUP BY p.userId";
			     System.out.println(sql);
			     PlFinanceProductUseraccount list=(PlFinanceProductUseraccount) this.getSession().createSQLQuery(sql).
			     										addScalar("id",Hibernate.LONG).
			     										addScalar("userId", Hibernate.LONG).
			     										addScalar("userName", Hibernate.STRING).
			     										addScalar("userloginName", Hibernate.STRING).
			     										addScalar("openDate", Hibernate.TIMESTAMP).
			     										addScalar("modifyDate", Hibernate.TIMESTAMP).
			     										addScalar("productId", Hibernate.LONG).
			     										addScalar("productName", Hibernate.STRING).
			     										addScalar("accountStatus", Hibernate.SHORT).
			     										addScalar("companyId", Hibernate.LONG).
			     										addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("totalIntestMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("incomeMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("payMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("yesterdayEarn", Hibernate.BIG_DECIMAL).
			     										addScalar("intestMoney", Hibernate.BIG_DECIMAL).
			     										addScalar("onwayMoney", Hibernate.BIG_DECIMAL).
			     										setResultTransformer(Transformers.aliasToBean(PlFinanceProductUseraccount.class)).uniqueResult();
			   
			   return list; 
	}

}