package com.hurong.credit.dao.creditFlow.financingAgency.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidSaleDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlBidSaleDaoImpl extends BaseDaoImpl<PlBidSale> implements PlBidSaleDao{

	public PlBidSaleDaoImpl() {
		super(PlBidSale.class);
	}

	
	@Override
	public List<PlBidSale> getCanTransferingList(Long userId,PagingBean pb, Map<String, String> map) {
		String saleStatus= map.get("saleStatus");
		StringBuffer sql =new StringBuffer("");
		sql.append("select pbs1.id,f.bidInfoID as bidInfoID ,f.bidPlanID  as bidPlanID ,f.orderNo,f.userMoney,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType, ");
		sql.append("(f.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =f.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append("from(select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,pbi.userMoney,");
		sql.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+" and pbs.saleStatus in(1,3)) as tansferingcount,");//能出现在转让列表的就是只有(0,继续支付)（4,9，10,可点挂牌）其中0是订单已生成继续支付要
		sql.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.orderNo =pbi.orderNo and sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0),0) as saleMoney,pbi.id ");
		sql.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null  and pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )and ( (pbi.userId="+userId+" and pbi.newInvestPersonId is null) or pbi.newInvestPersonId="+userId+" )  ) as a " );
		sql.append("where a.tansferingcount=0  and a.saleMoney>0   order by a.id asc" );//and a.saleMoney>0
		sql.append(" limit "+pb.getStart()+","+pb.getPageSize()+") as f");	
		sql.append(" left join pl_bid_plan plan on plan.bidId=f.bidPlanID ");
		sql.append(" left join pl_bid_sale pbs1 on f.bidInfoID=pbs1.bidInfoID and pbs1.outCustID="+userId+" and pbs1.saleStatus=0 ");
		sql.append(" left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append(" left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append(" left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append(" left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append(" left join bp_fund_project as sl  on (((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by f.bidInfoID asc " );
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select count(*) ");
		sqlcount.append("from (select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,");
		sqlcount.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+"  and pbs.saleStatus in(1,3)) as tansferingcount,");
		sqlcount.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0 and sfi.orderNo =pbi.orderNo ),0) as saleMoney ");
		sqlcount.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null and  pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )   and ( pbi.userId="+userId+" or pbi.newInvestPersonId="+userId+") ) as a " );
		sqlcount.append("where a.tansferingcount=0 and a.saleMoney>0 ) as f " );//and a.saleMoney>0
		System.out.println("sql=="+sql);
		
		 Query query = getSession().createSQLQuery(sqlcount.toString());
		 List countlist=query.list();
		 BigInteger count=new BigInteger("0");
			if(null!=countlist && countlist.size()>0){
				if(null!=countlist.get(0)){
					count=(BigInteger) countlist.get(0);
				}
			}
		pb.setTotalItems(Integer.parseInt(count.toString()));
		List<PlBidSale> list = this.jdbcTemplate.query(sql.toString(),new rowMapperb1());
		  return list;

	}
	class  rowMapperb1 implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			PlBidSale plBidSale = new PlBidSale();
			plBidSale.setSaleMoney(rs.getBigDecimal("saleMoney"));
			plBidSale.setId(rs.getLong("id"));
			plBidSale.setBidInfoID(rs.getLong("bidInfoID"));
			plBidSale.setBidPlanID(rs.getLong("bidPlanID"));
			plBidSale.setBidProName(rs.getString("bidProName"));
			plBidSale.setYearAccrualRate(rs.getBigDecimal("yearAccrualRate"));
			plBidSale.setAccrualtype(rs.getString("accrualtype"));
			plBidSale.setIntentDate(rs.getDate("intentDate"));
			plBidSale.setStartDate(rs.getDate("startDate"));
			plBidSale.setOrderNo(rs.getString("orderNo"));
			return plBidSale;
		}
		
	}
	@Override
	public List<PlBidSale> inTransferingList(Long userId, PagingBean pb,
			Map<String, String> map) {
		String saleStatus= map.get("saleStatus");
		
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs  where pbs.saleStatus="+saleStatus+" and pbs.inCustID="+userId+"  " );
		sql.append(" limit "+pb.getStart()+","+pb.getPageSize()+")as a " );	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
//		sql.append("left join bp_fund_project as sl  on ( sl.flag=1 AND((sl.projectId=bdir.proId ) or (sl.projectId=bor.proId ) or(sl.projectId=pdir.proId ) or (sl.projectId=por.proId ))) ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select  count(*)");
		sqlcount.append(" from (select * from pl_bid_sale as pbs where pbs.saleStatus="+saleStatus+" and pbs.inCustID="+userId+"  )as a ");
		 Query query = getSession().createSQLQuery(sqlcount.toString());
		 List countlist=query.list();
		 
		 BigInteger count=new BigInteger("0");
			if(null!=countlist && countlist.size()>0){
				if(null!=countlist.get(0)){
					count=(BigInteger) countlist.get(0);
				}
			}
		pb.setTotalItems(Integer.parseInt(count.toString()));
		List<PlBidSale> list = this.jdbcTemplate.query(sql.toString(),new rowMapperb());
		  return list;

	}


	@Override
	public List<PlBidSale> outTransferingList(Long userId, PagingBean pb,
			Map<String, String> map) {
		String saleStatus= map.get("saleStatus");
		
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		if(saleStatus.equals("0")){ //挂牌中出让金额是不断变化的
			sql.append("(select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.orderNo =pbi.orderNo) as saleMoney ");
		}else{
			sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		}
		
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in("+saleStatus+") and pbs.outCustID="+userId+"  " );
		sql.append(" limit "+pb.getStart()+","+pb.getPageSize()+")as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
//		sql.append("left join bp_fund_project as sl  on ( sl.flag=1 AND((sl.projectId=bdir.proId ) or (sl.projectId=bor.proId ) or(sl.projectId=pdir.proId ) or (sl.projectId=por.proId ))) ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println(sql);
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select  count(*) ");
		sqlcount.append(" from (select * from pl_bid_sale as pbs  where pbs.saleStatus in("+saleStatus+") and pbs.outCustID="+userId+" )as a ");
		 Query query = getSession().createSQLQuery(sqlcount.toString());
		 List countlist=query.list();
		 
		 BigInteger count=new BigInteger("0");
			if(null!=countlist && countlist.size()>0){
				if(null!=countlist.get(0)){
					count=(BigInteger) countlist.get(0);
				}
			}
		pb.setTotalItems(Integer.parseInt(count.toString()));
		List<PlBidSale> list = this.jdbcTemplate.query(sql.toString(),new rowMapperb());
		  return list;

	}
	class  rowMapperb implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			PlBidSale plBidSale = new PlBidSale();
			plBidSale.setChangeMoneyRate(rs.getInt("changeMoneyRate"));
			plBidSale.setChangeMoneyType(rs.getShort("changeMoneyType"));
			plBidSale.setSaleMoney(rs.getBigDecimal("saleMoney"));
			plBidSale.setBidInfoID(rs.getLong("bidInfoID"));
			plBidSale.setBidPlanID(rs.getLong("bidPlanID"));
			plBidSale.setBidProName(rs.getString("bidProName"));
			plBidSale.setIntentDate(rs.getDate("intentDate"));
			plBidSale.setYearAccrualRate(rs.getBigDecimal("yearAccrualRate"));
			plBidSale.setSumMoney(rs.getBigDecimal("sumMoney"));
			plBidSale.setAccrualtype(rs.getString("accrualtype"));
			Calendar calendar=Calendar.getInstance();
			plBidSale.setSaleSuccessTime(rs.getTimestamp("saleSuccessTime"));
			plBidSale.setSaleCloseTime(rs.getTimestamp("saleCloseTime"));
			plBidSale.setSaleStartTime(rs.getTimestamp("saleStartTime"));
			plBidSale.setStartDate(rs.getDate("startDate"));
			plBidSale.setId(rs.getLong("id"));
			plBidSale.setOrderNo(rs.getString("orderNo"));
			plBidSale.setSaleStatus(rs.getShort("saleStatus"));
			plBidSale.setProKeepType(rs.getString("proKeepType"));
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 System.out.println("saleSuccessTime=="+plBidSale.getSaleSuccessTime());
			if(plBidSale.getSaleSuccessTime()!=null){
				
				try {
					plBidSale.setSaleSuccessTime(sdf.parse(sdf.format(plBidSale.getSaleSuccessTime())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(null!=plBidSale.getSaleCloseTime()){
				try {
					Date a=sdf.parse(plBidSale.getSaleCloseTime().toString());
					plBidSale.setSaleCloseTime(a);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(null!=plBidSale.getSaleStartTime()){
				try {
					Date a=sdf.parse(plBidSale.getSaleStartTime().toString());
					plBidSale.setSaleStartTime(a);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}if(null!=plBidSale.getSaleSuccessTime()){
				try {
					Date a=sdf.parse(plBidSale.getSaleSuccessTime().toString());
					plBidSale.setSaleSuccessTime(a);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			/*plBidSale.setSaleSuccessTime(rs.getString("saleSuccessTime")==null?null:sdf.parse(rs.getString("saleSuccessTime")));
			plBidSale.setSaleCloseTime(rs.getDate(columnLabel, cal)("saleCloseTime")==null?null:sdf.parse(rs.getString("saleCloseTime")));
			plBidSale.setSaleStartTime(rs.getString("saleStartTime")==null?null:sdf.parse(rs.getString("saleStartTime")));*/
			return plBidSale;
		}
		
	}
	@Override
	public List<PlBidSale> transferingList(Long userId, PagingBean pb,
			Map<String, String> map, String str) {
		String type= map.get("type");
		StringBuffer sql =new StringBuffer("");
		sql.append("select  a.saleStatus,a.id as id,a.oldCustID as oldCustID,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
	//   sql.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.orderNo =pbi.orderNo),0) as saleMoney ");
	    sql.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) " );
		if(!type.equals("all")){
			sql.append(	" and pbs.outCustID="+userId+" " );
		}
		
		sql.append(" )as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
	//	sql.append("left join bp_fund_project as sl  on ( sl.flag=1 AND((sl.projectId=bdir.proId ) or (sl.projectId=bor.proId ) or(sl.projectId=pdir.proId ) or (sl.projectId=por.proId ))) ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(str);
		sql.append("order by a.saleStartTime desc limit "+pb.getStart()+","+pb.getPageSize()+"" );
		
		System.out.println(sql);
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select  a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sqlcount.append("pbi.orderNo,plan.bidProName,sl.intentDate,sl.yearAccrualRate,sl.accrualtype,sl.startDate,plan.proKeepType,");
		sqlcount.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sqlcount.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) " );
		if(!type.equals("all")){
			sqlcount.append(	" and pbs.outCustID="+userId+" " );
		}
		sqlcount.append(" )as a ");	
		sqlcount.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sqlcount.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sqlcount.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sqlcount.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sqlcount.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sqlcount.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sqlcount.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sqlcount.append(str);
		sqlcount.append("order by a.saleStartTime desc " );
		
		Query query = getSession().createSQLQuery(sqlcount.toString());
		List countlist=query.list();
		int count=Integer.valueOf(0);
		if(null!=countlist && countlist.size()>0){
			count = countlist.size();
		}
		pb.setTotalItems(count);
		List<PlBidSale> list = this.jdbcTemplate.query(sql.toString(),new rowMapperb());
		list = this.getSession().createSQLQuery(sql.toString()).
		addScalar("changeMoneyRate", Hibernate.INTEGER).
		addScalar("changeMoneyType", Hibernate.SHORT).
		addScalar("saleMoney", Hibernate.BIG_DECIMAL).
		addScalar("bidInfoID", Hibernate.LONG).
		addScalar("bidPlanID", Hibernate.LONG).
		addScalar("bidProName", Hibernate.STRING).
		addScalar("intentDate", Hibernate.DATE).
		addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
		addScalar("sumMoney", Hibernate.BIG_DECIMAL).
		addScalar("accrualtype", Hibernate.STRING).
		addScalar("saleSuccessTime", Hibernate.DATE).
		addScalar("saleCloseTime", Hibernate.DATE).
		addScalar("saleStartTime", Hibernate.TIMESTAMP).
		addScalar("startDate", Hibernate.DATE).
		addScalar("id", Hibernate.LONG).
		addScalar("orderNo", Hibernate.STRING).
		addScalar("saleStatus", Hibernate.SHORT).
		addScalar("oldCustID", Hibernate.LONG).
		addScalar("proKeepType", Hibernate.STRING).
		setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).
		list();
		return list;
	}


	@Override
	public PlBidSale getByNewOrderNo(String requestNo) {
		// TODO Auto-generated method stub
		String hql="from PlBidSale as p where p.newOrderNo=?";
		if(requestNo!=null&&!"".equals(requestNo)){
			return (PlBidSale) this.getSession().createQuery(hql).setParameter(0, requestNo).uniqueResult();
		}else{
			return null;
		}
	}


	@Override
	public List<PlBidSale> getBySaleState(Short saleState) {
		String hql=" from PlBidSale pbs where pbs.saleStatus=?";
		Object[] params={saleState};
		
		return this.findByHql(hql, params);
	}
	//(:alist)
	@Override
	public List<PlBidSale> getBySaleState(Long bidInfoID,String saleState) {
		String []  a=saleState.split(",");
		List<Short> alist=new ArrayList<Short>();
        for(int b=0;b<a.length;b++){
        	alist.add(Short.valueOf(a[b]));
        }
		String hql=" from PlBidSale  pbs where pbs.bidInfoID=:bidInfoID and  pbs.saleStatus in (:alist)";
		
		return this.getSession().createQuery(hql).setParameter("bidInfoID", bidInfoID).setParameterList("alist", alist).list();
	}


	@Override
	public PlBidSale getByPreTransferRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		String hql=" from PlBidSale  pbs where pbs.preTransferFeeRequestNo=?";
		return (PlBidSale) this.getSession().createQuery(hql).setParameter(0, requestNo).uniqueResult();
	}


	@Override
	public List<PlBidSale> getByPbidPlanID(Long bidPlanID, String saleState) {
		String []  a=saleState.split(",");
		List<Short> alist=new ArrayList<Short>();
        for(int b=0;b<a.length;b++){
        	alist.add(Short.valueOf(a[b]));
        }
		String hql=" from PlBidSale  pbs where pbs.bidPlanID=:bidPlanID and  pbs.saleStatus in (:alist) ORDER BY saleSuccessTime DESC";
		
		return this.getSession().createQuery(hql).setParameter("bidPlanID", bidPlanID).setParameterList("alist", alist).list();
	}


	@Override
	public PlBidSale querySaleInfo(Long saleId) {
		// TODO Auto-generated method stub
		
		String  sql="SELECT "+
					"   s.id as id, "+
					"   s.saleMoney as saleMoney, "+
					"   s.saleStartingTime as saleStartingTime, "+
					"   s.saleStatus as saleStatus, "+
					"   s.bidInfoID as bidInfoID, "+
					"   s.bidPlanID as bidPlanID, "+
					"   s.saleStatus as saleStatus, "+
					"   i.orderNo as orderNo, "+
					"   i.userMoney as userMoney, "+
					"   DATEDIFF(p.endIntentDate,NOW()) as days, "+
					"   p.bidProName as bidProName, "+
					"   p.startIntentDate as startDate, "+
					"   p.endIntentDate as endIntentDate, "+
					"   IFNULL(i.newOrderNo,i.orderNo) as saleNumber, "+
					"   sum( case when(f.fundType='principalRepayment'  ) then f.afterMoney else 0.0 end ) AS factbackMoney, "+
					"   sum( case when(f.fundType='principalRepayment'  ) then f.notMoney else 0.0 end ) AS factMoney, "+
					"   sum( case when(f.fundType='loanInterest'  )then (f.incomeMoney) else 0.0 end) as totalInterest, "+
					"   sum( case when(f.fundType='loanInterest'  ) then f.afterMoney else 0.0 end ) AS interestBackMoney, "+
					"   sum( case when(f.fundType='loanInterest'  ) then f.notMoney else 0.0 end ) AS interestMoney, "+
					"   sum( case when(f.fundType='loanInterest'  ) then f.accrualMoney else 0.0 end ) AS accrualMoney "+
					"FROM "+
					"   `pl_bid_sale` AS s "+
					"LEFT JOIN pl_bid_info AS i ON (s.bidInfoID = i.id) "+
					"left join pl_bid_plan as p on (s.bidPlanID = p.bidId) "+
					"LEFT JOIN bp_fund_intent AS f ON (i.orderNo = f.orderNo) "+
					"where f.fundType!='principalLending' and f.isCheck=0 and f.isValid=0  "+
					" and s.id="+saleId+" "+
					"group by s.id";
		
		return (PlBidSale) this.getSession().createSQLQuery(sql).addScalar("id",Hibernate.LONG).
						  addScalar("id",Hibernate.LONG).
						  addScalar("saleMoney",Hibernate.BIG_DECIMAL).
						  addScalar("saleStartingTime",Hibernate.DATE).
						  addScalar("saleStatus",Hibernate.SHORT).
						  addScalar("bidInfoID",Hibernate.LONG).
						  addScalar("bidPlanID",Hibernate.LONG).
						  addScalar("orderNo",Hibernate.STRING).
						  addScalar("userMoney",Hibernate.BIG_DECIMAL).
						  addScalar("days",Hibernate.INTEGER).
						  addScalar("bidProName",Hibernate.STRING).
						  addScalar("startDate",Hibernate.DATE).
						  addScalar("endIntentDate",Hibernate.DATE).
						  addScalar("saleNumber",Hibernate.STRING).
						  addScalar("factbackMoney",Hibernate.BIG_DECIMAL).
						  addScalar("factMoney",Hibernate.BIG_DECIMAL).
						  addScalar("totalInterest",Hibernate.BIG_DECIMAL).
						  addScalar("interestBackMoney",Hibernate.BIG_DECIMAL).
						  addScalar("interestMoney",Hibernate.BIG_DECIMAL).
						  addScalar("accrualMoney",Hibernate.BIG_DECIMAL).
						  setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).uniqueResult();
	}


	@Override
	public List<PlBidSale> getCanTransferingList(Long userId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		//String saleStatus=request.getParameter("saleStatus");
		StringBuffer sql =new StringBuffer("");
		sql.append("select pbs1.id,f.bidInfoID as bidInfoID ,f.bidPlanID  as bidPlanID ,f.orderNo,f.userMoney,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType, ");
		sql.append("(f.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =f.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append("from(select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,pbi.userMoney,");
		sql.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+" and pbs.saleStatus in(1,3) AND pbs.isVip = 0) as tansferingcount,");//能出现在转让列表的就是只有(0,继续支付)（4,9，10,可点挂牌）其中0是订单已生成继续支付要
		sql.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.orderNo =pbi.orderNo and sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0),0) as saleMoney,pbi.id ");
		sql.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null  and pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )and ( (pbi.userId="+userId+" and pbi.newInvestPersonId is null) or pbi.newInvestPersonId="+userId+" )  ) as a " );
		sql.append("where a.tansferingcount=0  and a.saleMoney>0   order by a.id asc" );//and a.saleMoney>0
		sql.append(" ) as f");	
		sql.append(" left join pl_bid_plan plan on plan.bidId=f.bidPlanID ");
		sql.append(" left join pl_bid_sale pbs1 on f.bidInfoID=pbs1.bidInfoID and pbs1.outCustID="+userId+" and pbs1.saleStatus=0 ");
		sql.append(" left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append(" left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append(" left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append(" left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append(" left join bp_fund_project as sl  on (((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by f.bidInfoID asc " );
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select count(*) ");
		sqlcount.append("from (select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,");
		sqlcount.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+"  and pbs.saleStatus in(1,3) and pbs.isVip=0) as tansferingcount,");
		sqlcount.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0 and sfi.orderNo =pbi.orderNo ),0) as saleMoney ");
		sqlcount.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null and  pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )   and ( pbi.userId="+userId+" or pbi.newInvestPersonId="+userId+") ) as a " );
		sqlcount.append("where a.tansferingcount=0 and a.saleMoney>0 ) as f " );//and a.saleMoney>0
		System.out.println("sql=="+sql);
		
		 Query query = getSession().createSQLQuery(sqlcount.toString());
		
		 List countlist=query.list();
		 BigInteger count=new BigInteger("0");
			if(null!=countlist && countlist.size()>0){
				if(null!=countlist.get(0)){
					count=(BigInteger) countlist.get(0);
				}
			}
			
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		 if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
				String start = request.getParameter("start")!=null?request.getParameter("start").toString():"0";
				String limit = request.getParameter("limit")!=null?request.getParameter("limit"):"10";
			list = this.getSession().createSQLQuery(sql.toString())
				//.addEntity(PlBidSale.class)
				.addScalar("saleMoney", Hibernate.BIG_DECIMAL)//this.jdbcTemplate.query(sql.toString(),new rowMapperb1());
				.addScalar("id", Hibernate.LONG)
				.addScalar("bidInfoID",Hibernate.LONG)
				.addScalar("bidPlanID", Hibernate.LONG)
				.addScalar("bidProName", Hibernate.STRING)
				.addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL)
				.addScalar("accrualtype", Hibernate.STRING)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("startDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(PlBidSale.class))
				.setFirstResult(Integer.valueOf(start))
				.setMaxResults(Integer.valueOf(limit)).list();
		 }else{
			 list =  this.getSession().createSQLQuery(sql.toString())
				//.addEntity(PlBidSale.class)
				.addScalar("saleMoney", Hibernate.BIG_DECIMAL)//this.jdbcTemplate.query(sql.toString(),new rowMapperb1());
				.addScalar("id", Hibernate.LONG)
				.addScalar("bidInfoID",Hibernate.LONG)
				.addScalar("bidPlanID", Hibernate.LONG)
				.addScalar("bidProName", Hibernate.STRING)
				.addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL)
				.addScalar("accrualtype", Hibernate.STRING)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("startDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(PlBidSale.class))
				.list();
		 }
		return list;
	}
	
	/**
	 * VIP
	 */
	@Override
	public List<PlBidSale> getCanTransferingListVip(Long userId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		//String saleStatus=request.getParameter("saleStatus");
		StringBuffer sql =new StringBuffer("");
		sql.append("select pbs1.id,f.bidInfoID as bidInfoID ,f.bidPlanID  as bidPlanID ,f.orderNo,f.userMoney,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType, ");
		sql.append("(f.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =f.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append("from(select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,pbi.userMoney,");
		sql.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+" and pbs.saleStatus in(1,3) and pbs.isVip=1 ) as tansferingcount,");//能出现在转让列表的就是只有(0,继续支付)（4,9，10,可点挂牌）其中0是订单已生成继续支付要
		sql.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.orderNo =pbi.orderNo and sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0),0) as saleMoney,pbi.id ");
		sql.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null  and pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )and ( (pbi.userId="+userId+" and pbi.newInvestPersonId is null) or pbi.newInvestPersonId="+userId+" )  ) as a " );
		sql.append("where a.tansferingcount=0  and a.saleMoney>0   order by a.id asc" );//and a.saleMoney>0
		sql.append(" ) as f");	
		sql.append(" left join pl_bid_plan plan on plan.bidId=f.bidPlanID ");
		sql.append(" left join pl_bid_sale pbs1 on f.bidInfoID=pbs1.bidInfoID and pbs1.outCustID="+userId+" and pbs1.saleStatus=0 ");
		sql.append(" left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append(" left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append(" left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append(" left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append(" left join bp_fund_project as sl  on (((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by f.bidInfoID asc " );
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select count(*) ");
		sqlcount.append("from (select * from(select  pbi.id as bidInfoID ,pbi.bidId as bidPlanID ,pbi.orderNo,");
		sqlcount.append("(select count(*) from pl_bid_sale pbs where pbs.bidInfoID=pbi.id and pbs.outCustID="+userId+"  and pbs.saleStatus in(1,3) and pbs.isVip=1) as tansferingcount,");
		sqlcount.append("IFNULL((select sum(sfi.notMoney) from bp_fund_intent sfi where sfi.fundType='principalRepayment' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney !=0 and sfi.orderNo =pbi.orderNo ),0) as saleMoney ");
		sqlcount.append("from pl_bid_info as pbi where  pbi.isToObligatoryRightChildren is null and  pbi.bidId IN(select plan.bidId from pl_bid_plan as plan where plan.state in(7,10) )   and ( pbi.userId="+userId+" or pbi.newInvestPersonId="+userId+") ) as a " );
		sqlcount.append("where a.tansferingcount=0 and a.saleMoney>0 ) as f " );//and a.saleMoney>0
		System.out.println("sql=="+sql);
		
		 Query query = getSession().createSQLQuery(sqlcount.toString());
		
		 List countlist=query.list();
		 BigInteger count=new BigInteger("0");
			if(null!=countlist && countlist.size()>0){
				if(null!=countlist.get(0)){
					count=(BigInteger) countlist.get(0);
				}
			}
			
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		 if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
				String start = request.getParameter("start")!=null?request.getParameter("start").toString():"0";
				String limit = request.getParameter("limit")!=null?request.getParameter("limit"):"10";
			list = this.getSession().createSQLQuery(sql.toString())
				//.addEntity(PlBidSale.class)
				.addScalar("saleMoney", Hibernate.BIG_DECIMAL)//this.jdbcTemplate.query(sql.toString(),new rowMapperb1());
				.addScalar("id", Hibernate.LONG)
				.addScalar("bidInfoID",Hibernate.LONG)
				.addScalar("bidPlanID", Hibernate.LONG)
				.addScalar("bidProName", Hibernate.STRING)
				.addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL)
				.addScalar("accrualtype", Hibernate.STRING)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("startDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(PlBidSale.class))
				.setFirstResult(Integer.valueOf(start))
				.setMaxResults(Integer.valueOf(limit)).list();
		 }else{
			 list =  this.getSession().createSQLQuery(sql.toString())
				//.addEntity(PlBidSale.class)
				.addScalar("saleMoney", Hibernate.BIG_DECIMAL)//this.jdbcTemplate.query(sql.toString(),new rowMapperb1());
				.addScalar("id", Hibernate.LONG)
				.addScalar("bidInfoID",Hibernate.LONG)
				.addScalar("bidPlanID", Hibernate.LONG)
				.addScalar("bidProName", Hibernate.STRING)
				.addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL)
				.addScalar("accrualtype", Hibernate.STRING)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("startDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(PlBidSale.class))
				.list();
		 }
		return list;
	}


	@Override
	public List<PlBidSale> transferingList(Long userId,
			HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select  a.saleStatus,a.oldCustID as oldCustID, a.outCustID as outCustID, a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
	    sql.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) and pbs.isVip=0 and pbs.oldCustID = "+ userId);
		sql.append(" )as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append("order by a.saleStartTime desc" );
		System.out.println(sql);
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select  a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sqlcount.append("pbi.orderNo,plan.bidProName,sl.intentDate,sl.yearAccrualRate,sl.accrualtype,sl.startDate,plan.proKeepType,");
		sqlcount.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sqlcount.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) and pbs.isVip=0 " );
		sqlcount.append(" )as a ");	
		sqlcount.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sqlcount.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sqlcount.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sqlcount.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sqlcount.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sqlcount.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sqlcount.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sqlcount.append("order by a.saleStartTime desc " );
		
		Query query = getSession().createSQLQuery(sqlcount.toString());
		List countlist=query.list();
		int count=Integer.valueOf(0);
		if(null!=countlist && countlist.size()>0){
			count = countlist.size();
		}
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&!"".equals(request.getParameter("start"))&&request.getParameter("limit")!=null&&!"".equals(request.getParameter("limit"))){
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("startDate", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("oldCustID",Hibernate.LONG).
			addScalar("proKeepType", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).
			list();
		}else{
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("startDate", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("oldCustID",Hibernate.LONG).
			addScalar("proKeepType", Hibernate.STRING).list();
		}
		return list;
	}
	
	/**
	 * VIP
	 */
	@Override
	public List<PlBidSale> transferingListVip(Long userId,
			HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select  a.saleStatus,a.oldCustID as oldCustID, a.outCustID as outCustID, a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
	    sql.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) and pbs.isVip=1 and pbs.oldCustID = "+ userId);
		sql.append(" )as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append("order by a.saleStartTime desc" );
		System.out.println(sql);
		
		
		StringBuffer sqlcount =new StringBuffer("");
		sqlcount.append("select  a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sqlcount.append("pbi.orderNo,plan.bidProName,sl.intentDate,sl.yearAccrualRate,sl.accrualtype,sl.startDate,plan.proKeepType,");
		sqlcount.append("(pbi.userMoney-IFNULL((select sum(sfi1.afterMoney) from bp_fund_intent sfi1 where  sfi1.orderNo =pbi.orderNo and sfi1.fundType='principalRepayment' and sfi1.isCheck=0 and sfi1.isValid=0   ),0)) as saleMoney  ");
		sqlcount.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in(1,3) and pbs.isVip=1 " );
		sqlcount.append(" )as a ");	
		sqlcount.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sqlcount.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sqlcount.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sqlcount.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sqlcount.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sqlcount.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sqlcount.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sqlcount.append("order by a.saleStartTime desc " );
		
		Query query = getSession().createSQLQuery(sqlcount.toString());
		List countlist=query.list();
		int count=Integer.valueOf(0);
		if(null!=countlist && countlist.size()>0){
			count = countlist.size();
		}
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&!"".equals(request.getParameter("start"))&&request.getParameter("limit")!=null&&!"".equals(request.getParameter("limit"))){
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("startDate", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("oldCustID",Hibernate.LONG).
			addScalar("proKeepType", Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).
			list();
		}else{
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("startDate", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("oldCustID",Hibernate.LONG).
			addScalar("proKeepType", Hibernate.STRING).list();
		}
		return list;
	}


	@Override
	public List<PlBidSale> querySoldSale(Long userId, HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus = 4  and pbs.isVip=0 and pbs.outCustID="+userId+"  " );
		sql.append(")as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println(sql);
		
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start = request.getParameter("start").toString();
			String limit = request.getParameter("limit").toString();
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("startDate", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("startDate", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}	
		  return list;
	}
	
	/**
	 * VIP
	 */
	@Override
	public List<PlBidSale> querySoldSaleVip(Long userId, HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus = 4 and pbs.isVip=1 and pbs.outCustID="+userId+"  " );
		sql.append(")as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println(sql);
		
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start = request.getParameter("start").toString();
			String limit = request.getParameter("limit").toString();
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("startDate", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = 	this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName", Hibernate.STRING).
			addScalar("intentDate", Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("startDate", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}	
		  return list;
	}


	@Override
	public List<PlBidSale> queryBuyedSale(Long userId,
			HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs  where pbs.saleStatus= 4 and pbs.isVip=0 and pbs.inCustID="+userId+"  " );
		sql.append(")as a " );	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println("已购买的债权查询"+sql.toString());
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start =  request.getParameter("start").toString();
			String limit =  request.getParameter("limit").toString();
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			addScalar("startDate", Hibernate.DATE).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			addScalar("startDate", Hibernate.DATE).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}
		
		  return list;
	}

	/**
	 * VIP
	 */
	@Override
	public List<PlBidSale> queryBuyedSaleVip(Long userId,
			HttpServletRequest request) {
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs  where pbs.saleStatus= 4 and pbs.isVip=1 and pbs.inCustID="+userId+"  " );
		sql.append(")as a " );	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println("已购买的债权查询"+sql.toString());
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start =  request.getParameter("start").toString();
			String limit =  request.getParameter("limit").toString();
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}
		
		  return list;
	}

	@Override
	public List<PlBidSale> queryClosedSale(Long userId,
			HttpServletRequest request) {
		
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in('9,10') and pbs.isVip=0 and pbs.outCustID="+userId+"  " );
		sql.append(")as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println("已关闭的交易查询"+sql);
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start =  request.getParameter("start").toString();
			String limit =  request.getParameter("limit").toString();
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}
		  return list;

	}
	
	/**
	 * VIP
	 */
	@Override
	public List<PlBidSale> queryClosedSaleVip(Long userId,
			HttpServletRequest request) {
		
		StringBuffer sql =new StringBuffer("");
		sql.append("select   a.saleStatus,a.id as id,a.changeMoneyRate,a.changeMoneyType,a.saleStartTime,a.saleCloseTime,a.bidInfoID,a.bidPlanID,a.sumMoney,a.saleSuccessTime,a.sumMoney,a.saleSuccessTime,");
		sql.append("pbi.orderNo,plan.bidProName,sl.yearAccrualRate,sl.accrualtype,plan.startIntentDate as startDate,plan.endIntentDate  as intentDate,plan.proKeepType,");
		sql.append("a.saleMoney ");//已买出的和关闭的出让金额不变
		sql.append(" from (select * from pl_bid_sale as pbs" +" where pbs.saleStatus in('9,10') and pbs.isVip=1 and pbs.outCustID="+userId+"  " );
		sql.append(")as a ");	
		sql.append("left join pl_bid_info pbi on pbi.id =a.bidInfoID ");
		sql.append("left join pl_bid_plan plan on a.bidPlanID=plan.bidId ");
		sql.append("left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' ");
		sql.append("left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' ");
		sql.append("left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' ");
		sql.append("left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' ");
		sql.append("left join bp_fund_project as sl  on ( ((sl.id=bdir.moneyPlanId ) or (sl.id=bor.moneyPlanId ) or(sl.id=pdir.moneyPlanId ) or (sl.id=por.moneyPlanId ))) ");
		sql.append(" order by a.saleDealTime,a.saleCloseTime desc " );
		System.out.println("已关闭的交易查询"+sql);
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start =  request.getParameter("start").toString();
			String limit =  request.getParameter("limit").toString();
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.TIMESTAMP).
			addScalar("saleCloseTime", Hibernate.TIMESTAMP).
			addScalar("saleStartTime", Hibernate.TIMESTAMP).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setFirstResult(Integer.valueOf(start)).
			setMaxResults(Integer.valueOf(limit)).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}else{
			list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("changeMoneyRate", Hibernate.INTEGER).
			addScalar("changeMoneyType", Hibernate.SHORT).
			addScalar("saleMoney", Hibernate.BIG_DECIMAL).
			addScalar("bidInfoID", Hibernate.LONG).
			addScalar("bidPlanID", Hibernate.LONG).
			addScalar("bidProName",Hibernate.STRING).
			addScalar("intentDate",Hibernate.DATE).
			addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			addScalar("sumMoney", Hibernate.BIG_DECIMAL).
			addScalar("accrualtype", Hibernate.STRING).
			addScalar("saleSuccessTime", Hibernate.DATE).
			addScalar("saleCloseTime", Hibernate.DATE).
			addScalar("saleStartTime", Hibernate.DATE).
			addScalar("id", Hibernate.LONG).
			addScalar("orderNo", Hibernate.STRING).
			addScalar("saleStatus", Hibernate.SHORT).
			addScalar("proKeepType", Hibernate.STRING).
			setResultTransformer(Transformers.aliasToBean(PlBidSale.class)).list();
		}
		  return list;

	}
}
