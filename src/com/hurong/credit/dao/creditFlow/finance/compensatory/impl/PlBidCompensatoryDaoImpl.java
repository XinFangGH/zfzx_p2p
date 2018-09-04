package com.hurong.credit.dao.creditFlow.finance.compensatory.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.compensatory.PlBidCompensatoryDao;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.SlFundIntentPeriod;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryService;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlBidCompensatoryDaoImpl extends BaseDaoImpl<PlBidCompensatory> implements PlBidCompensatoryDao{

	public PlBidCompensatoryDaoImpl() {
		super(PlBidCompensatory.class);
	}

	@Override
	public List<PlBidCompensatory> getCompensatoryList(Long loanerp2pId,String custmerType,PagingBean pb) {
		String sql = "SELECT tory.planId as bidId, " +
					"  plan.bidProName as bidName,	" +
					"  tory.payintentPeriod as period," +
					"  tory.compensatoryDate as satoryDate," +
					"  tory.compensatoryMoney as satoryMoney," +
					"  tory.punishMoney as punMoney," +
					"  tory.backPunishMoney as backPunMoney," +
					"  tory.backCompensatoryMoney as backSatoryMoney," +
					"  tory.plateMoney as plaMoney," +
					"  tory.compensatoryDays as comDays," +
					"  tory.compensatoryMoney+tory.punishMoney-tory.backPunishMoney-tory.backCompensatoryMoney as surplusMoney" +
					"  FROM	pl_bid_compensatory AS tory LEFT JOIN pl_bid_plan AS plan ON tory.planId = plan.bidId " +
					"  where tory.loanerp2pId = "+loanerp2pId;
		sql += " limit "+pb.getStart()+","+pb.getPageSize();
		pb.setTotalItems(3);
		return  this.jdbcTemplate.query(sql.toString(),new rowCompensatoryMapper());
	}
	class  rowCompensatoryMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			PlBidCompensatory satory = new PlBidCompensatory();
			satory.setPlanId(rs.getLong("bidId"));
			satory.setBidName(rs.getString("bidName"));
			satory.setPayintentPeriod(rs.getInt("period"));
			satory.setCompensatoryDate(rs.getDate("satoryDate"));
			satory.setCompensatoryMoney(rs.getBigDecimal("satoryMoney"));
			satory.setPunishMoney(rs.getBigDecimal("punMoney"));
			satory.setBackPunishMoney(rs.getBigDecimal("backPunMoney"));
			satory.setBackCompensatoryMoney(rs.getBigDecimal("backSatoryMoney"));
			satory.setPlateMoney(rs.getBigDecimal("plaMoney"));
			satory.setCompensatoryDays(rs.getInt("comDays"));
			satory.setSurplusMoney(rs.getBigDecimal("surplusMoney"));
			return satory;
		}
		
	}
	@Override
	public BigDecimal findCompensatoryMoneytByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" select IFNULL(SUM(p.compensatoryMoney),0) - IFNULL(SUM(p.backCompensatoryMoney),0) from pl_bid_compensatory p where 1=1");
		if(null!=compensatoryP2PId && !"".equals(compensatoryP2PId)){
			  sql.append(" and p.compensatoryP2PId="+compensatoryP2PId);
		}
		if(null!=compensatoryType && !"".equals(compensatoryType)){
			  sql.append(" and p.compensatoryType = '"+compensatoryType+"'");
		}
		if(null!=backStatus && !"".equals(backStatus)){
			  sql.append(" and p.backStatus="+backStatus);
		}
	//	System.out.println("111"+sql.toString());
		return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult(); 
	}

	@Override
	public Long findCountByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT  count(*) "+ 
		           " from pl_bid_compensatory p "+
		           " where 1=1 ");
		if(null!=compensatoryP2PId && !"".equals(compensatoryP2PId)){
			  sql.append(" and p.compensatoryP2PId="+compensatoryP2PId);
		}
		if(null!=compensatoryType && !"".equals(compensatoryType)){
			  sql.append(" and p.compensatoryType = '"+compensatoryType+"'");
		}
		if(null!=backStatus && !"".equals(backStatus)){
			  sql.append(" and p.backStatus="+backStatus);
		}
	    BigInteger count=new BigInteger("0");
		List list=this.getSession().createSQLQuery(sql.toString()).list();
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				count=(BigInteger) list.get(0);
			}
		}
		return count.longValue();
	}

	@Override
	public List<PlBidCompensatory> findListByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus, Pager pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT  "+ 
		           " c.id,c.planId," +
		           " c.compensatoryMoney," +
		           " p.receiverName, " +
		           " p.bidProName AS bidName,"+
		           " p.receiverName,"+
		           " c.payintentPeriod,"+
		           " c.compensatoryDate,"+
		           " c.backDate,"+
		           " c.punishMoney,"+
		           " c.compensatoryDays,"+
		           " c.backCompensatoryMoney+c.backPunishMoney as backAllMoney "+
		           " FROM "+
		           " pl_bid_compensatory c"+
		           " LEFT JOIN pl_bid_plan p ON p.bidId = c.planId "+
		           " where 1=1 ");
		if(null!=compensatoryP2PId && !"".equals(compensatoryP2PId)){
			  sql.append(" and c.compensatoryP2PId="+compensatoryP2PId);
		}
		if(null!=compensatoryType && !"".equals(compensatoryType)){
			  sql.append(" and c.compensatoryType = '"+compensatoryType+"'");
		}
		if(null!=backStatus && !"".equals(backStatus)){
			  sql.append(" and c.backStatus="+backStatus);
		}
		String bidName=request.getParameter("name");
		if(null!=bidName && !"".equals(bidName)){
				 sql.append(" and p.bidProName  like '%"+bidName+"%'");
		}
		 String cstartDate=request.getParameter("cstartDate");
		 if(null!=cstartDate && !"".equals(cstartDate)){
			 sql.append(" and c.compensatoryDate >='"+cstartDate+"'");
		 }
		 String cendDate=request.getParameter("cendDate");
		 if(null!=cendDate && !"".equals(cendDate)){
			 sql.append(" and c.compensatoryDate <='"+cendDate+"'");
		 }
		System.out.println("tt-->"+sql.toString());
		List<PlBidCompensatory>  list=new ArrayList<PlBidCompensatory>();
		if(request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			Integer start = Integer.valueOf(request.getParameter("start").toString());
			Integer limit = Integer.valueOf(request.getParameter("limit").toString());
			list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("id",Hibernate.LONG).
			  addScalar("planId",Hibernate.LONG).
			  addScalar("compensatoryMoney",Hibernate.BIG_DECIMAL).
			  addScalar("receiverName",Hibernate.STRING).
			  addScalar("bidName",Hibernate.STRING).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("compensatoryDate",Hibernate.TIMESTAMP).
			  addScalar("backDate",Hibernate.TIMESTAMP).
			  addScalar("punishMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensatoryDays",Hibernate.INTEGER).
			  addScalar("backAllMoney",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(PlBidCompensatory.class)).setFirstResult(start).setMaxResults(limit)
			  .list();
		}
		else{
			list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("id",Hibernate.LONG).
			  addScalar("planId",Hibernate.LONG).
			  addScalar("compensatoryMoney",Hibernate.BIG_DECIMAL).
			  addScalar("receiverName",Hibernate.STRING).
			  addScalar("bidName",Hibernate.STRING).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("compensatoryDate",Hibernate.DATE).
			  addScalar("backDate",Hibernate.DATE).
			  addScalar("punishMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensatoryDays",Hibernate.INTEGER).
			  addScalar("backAllMoney",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(PlBidCompensatory.class))
			  .list();
		}

		return list;
	}

	
	@Override
	public List<PlBidCompensatory> findListByComp2PId1(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus, Pager pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT  "+ 
		           " c.id,c.planId," +
		           " c.compensatoryMoney," +
		           " p.receiverName, " +
		           " p.bidProName AS bidName,"+
		           " p.receiverName,"+
		           " c.payintentPeriod,"+
		           " c.compensatoryDate,"+
		           " c.backDate,"+
		           " c.punishMoney,"+
		           " c.compensatoryDays,"+
		           " c.backCompensatoryMoney+c.backPunishMoney as backAllMoney "+
		           " FROM "+
		           " pl_bid_compensatory c"+
		           " LEFT JOIN pl_bid_plan p ON p.bidId = c.planId "+
		           " where 1=1 ");
		if(null!=compensatoryP2PId && !"".equals(compensatoryP2PId)){
			  sql.append(" and c.compensatoryP2PId="+compensatoryP2PId);
		}
		if(null!=compensatoryType && !"".equals(compensatoryType)){
			  sql.append(" and c.compensatoryType = '"+compensatoryType+"'");
		}
		if(null!=backStatus && !"".equals(backStatus)){
			  sql.append(" and c.backStatus="+backStatus);
		}
		String bidName=request.getParameter("cprojectName");
		if(null!=bidName && !"".equals(bidName)){
			  sql.append(" and p.bidProName  like '%"+bidName+"%'");
		}
		 String cstartDate=request.getParameter("cstartDate");
		 if(null!=cstartDate && !"".equals(cstartDate)){
			 sql.append(" and c.compensatoryDate >='"+cstartDate+"'");
		 }
		 String cendDate=request.getParameter("cendDate");
		 if(null!=cendDate && !"".equals(cendDate)){
			 sql.append(" and c.compensatoryDate <='"+cendDate+"'");
		 }
	//	System.out.println("tt-->"+sql.toString());
		List<PlBidCompensatory>  list=new ArrayList<PlBidCompensatory>();
			list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("id",Hibernate.LONG).
			  addScalar("planId",Hibernate.LONG).
			  addScalar("compensatoryMoney",Hibernate.BIG_DECIMAL).
			  addScalar("receiverName",Hibernate.STRING).
			  addScalar("bidName",Hibernate.STRING).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("compensatoryDate",Hibernate.DATE).
			  addScalar("backDate",Hibernate.DATE).
			  addScalar("punishMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensatoryDays",Hibernate.INTEGER).
			  addScalar("backAllMoney",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(PlBidCompensatory.class))
			  .list();

		return list;
	}

	
	
	@Override
	public BigDecimal findPunishMoneyByComp2PId(Long compensatoryP2PId,
			String compensatoryType, Integer backStatus) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(" select IFNULL(SUM(p.punishMoney),0) - IFNULL(SUM(p.backPunishMoney),0) from pl_bid_compensatory p where 1=1");
		if(null!=compensatoryP2PId && !"".equals(compensatoryP2PId)){
			  sql.append(" and p.compensatoryP2PId="+compensatoryP2PId);
		}
		if(null!=compensatoryType && !"".equals(compensatoryType)){
			  sql.append(" and p.compensatoryType = '"+compensatoryType+"'");
		}
		if(null!=backStatus && !"".equals(backStatus)){
			  sql.append(" and p.backStatus="+backStatus);
		}
	//	System.out.println("111"+sql.toString());
		return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult(); 
	}
}