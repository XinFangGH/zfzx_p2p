package com.hurong.credit.dao.creditFlow.finance.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.finance.SlFundIntentDao;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;
import com.hurong.credit.model.customer.BpCustRelation;

@SuppressWarnings("unchecked")
public class SlFundIntentDaoImpl extends BaseDaoImpl<SlFundIntent> implements
		SlFundIntentDao {

	public SlFundIntentDaoImpl() {
		super(SlFundIntent.class);
	}

	@Override
	public List<SlFundIntent> getListByBidPlanId(Long bidPlanId, PagingBean pb) {
		String hql = "from SlFundIntent as sfi where sfi.bidPlanId = ? and sfi.fundType in ('loanInterest','principalRepayment')";
		if(null==pb){
			return super.findByHql(hql, new Object[]{bidPlanId});
		}else{
			return super.findByHql(hql, new Object[]{bidPlanId}, pb);
		} 
	}

	@Override
	public List<SlFundIntent> getListByCustId(Long custId, String custType, HttpServletRequest request, PagingBean pb) {
		String sql = "select sfi.* from sl_fund_intent as sfi LEFT JOIN sl_smallloan_project as sp on sp.projectId =  sfi.projectId  where sp.oppositeType = '"+custType+"' and sp.oppositeID = "+custId+" and sfi.fundType in ('loanInterest','principalRepayment','serviceMoney','consultationMoney') ";
		
		String notMoney = request.getParameter("notMoney");
		if(null!=notMoney&&!"".equals(notMoney)){
			if("1".equals(notMoney)){
				sql += " and notMoney !=0 ";
			}else if("2".equals(notMoney)){
				sql += " and notMoney =0 ";
			}
		}
		String projectName = request.getParameter("projectName");
		if(null!=projectName&&!"".equals(projectName)){
			sql += " and sp.projectName like '%"+projectName+"%' ";
		}
		String selectTime = request.getParameter("selectTime");
		if(null!=selectTime&&!"".equals(selectTime)){
			sql += " and sfi.intentDate >= '"+selectTime+" 00:00:00' ";
		}
		String selectTime2 = request.getParameter("selectTime2");
		if(null!=selectTime2&&!"".equals(selectTime2)){
			sql += " and sfi.intentDate <= '"+selectTime2+" 23:59:59'";
		}
		String incomeMoney = request.getParameter("incomeMoney");
		if(null!=incomeMoney&&!"".equals(incomeMoney)){
			sql += " and sp.incomeMoney = "+incomeMoney+" ";
		}
		sql += " order by sfi.intentDate desc";
		
		return (List<SlFundIntent>) this.getSession().createSQLQuery(sql).addEntity(SlFundIntent.class).list();
	}

	@Override
	public Integer getListByBidPlanId(String investpersonName) {
		// TODO Auto-generated method stub
		Integer num=0;
		try {
			/*List<SlFundIntent>  list=this.getSession()
					.createQuery(
							"select COUNT(*) from SlFundIntent b where b.investpersonName =?")
					.setParameter(0, investpersonName).list();*/
			List<SlFundIntent>  list=this.getSession()
			.createSQLQuery(
					"select COUNT(*) from bp_fund_intent b where b.investpersonName =? " +
					" and b.isCheck=0 and isValid=0 and b.fundType !='principalLending'"+
					"  and b.notMoney !=0 group by b.intentDate")
			.setParameter(0, investpersonName).list();
			num = list==null?0:list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}


	@Override
	public List<FundIncome> getPay(HttpServletRequest request, PagingBean pb,
			Long investPersonId,String type) {

		String sql = "select sfi.fundIntentId,bcm.id as memberId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,pbp.bidProName,pbp.authorizationStatus,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo"
				+ "	from bp_fund_intent sfi "
				+ "LEFT JOIN pl_bid_plan pbp on sfi.bidPlanId = pbp.bidId 	 "
				+ "left join bp_persion_dir_pro bpdp on bpdp.pdirProId = pbp.pDirProId  "
				+ "	left join cs_person cp on cp.id=bpdp.persionId"
				+ "	left join bp_cust_relation bcr on bcr.offlineCusId=cp.id and bcr.offlineCustType='p_loan' "
				+ "  left join bp_cust_member bcm on bcm.id=bcr.p2pCustId  "
				+ "	where sfi.investPersonId="+investPersonId
				+ " and sfi.fundType !='principalLending' and sfi.isCheck=0 and sfi.isValid=0 and sfi.loanerRepayMentStatus is null ";
				
		
		//项目名称
		String projectName = request.getParameter("Q_projectName_S_LK");
		if(null!=projectName&&!"".equals(projectName.trim())){	
			sql += " and pbp.bidProName like '%"+projectName.trim()+"%'";
		}
		//计划到帐日
		String selectTime = request.getParameter("selectTime");
		if(null!=selectTime&&!"".equals(selectTime.trim())){	
			sql += " and sfi.intentDate >= '"+selectTime.trim()+" 00:00:00'";
		//	sql += " and  fund_income.intentDate <= '"+selectTime+" 23:59:59'";
		}
		String selectTime2 = request.getParameter("selectTime2");
		if(null!=selectTime2&&!"".equals(selectTime2.trim())){	
			//sql += " and fund_income.intentDate >= '"+selectTime+" 00:00:00'";
			sql += " and  sfi.intentDate <= '"+selectTime2.trim()+" 23:59:59'";
		}
		if("Repaymented".equals(type)){
			sql+=" and sfi.notMoney =0";//未还款
		}
		/*String collegeDegree = request.getParameter("bpCustMember.collegeDegree");
		if(null!=collegeDegree&&!"".equals(collegeDegree.trim())){	
			if(collegeDegree.equals("1")){
				sql+=" and sfi.notMoney !=0";
			}
			if(collegeDegree.equals("2")){
				sql+=" and sfi.notMoney =0";
			}
		}*/
		
		sql +=  " order by sfi.bidPlanId asc,sfi.payintentPeriod asc ";
		System.out.println("sql="+sql);
	    List<FundIncome> list = new ArrayList<FundIncome>();
		List li = this.jdbcTemplate.query(sql,new rowMapper());
		
		return li;
	
	}
	
	class  rowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			FundIncome income = new FundIncome();
			income.setPlanId(rs.getLong("planId"));
			income.setAfterMoney(rs.getBigDecimal("afterMoney"));
			income.setFundType(rs.getString("fundType"));
			income.setIncomeMoney(rs.getBigDecimal("incomeMoney"));
			income.setIntentDate(rs.getDate("intentDate"));
			income.setPayintentPeriod(rs.getInt("payintentPeriod"));
			income.setProjectName(rs.getString("bidProName"));
			income.setNotMoney(rs.getBigDecimal("notMoney"));
			income.setFundIntentId(rs.getLong("fundIntentId"));
			income.setInvestId(rs.getLong("investPersonId"));
			income.setRepaySource(rs.getShort("repaySource"));
			income.setAccrualMoney(rs.getBigDecimal("accrualMoney"));
			income.setFactDate(rs.getDate("factDate"));
			income.setOrderNo(rs.getString("orderNo"));
			income.setAuthorizationStatus(rs.getShort("authorizationStatus"));
			return income;
		}
}

	@Override
	public BigDecimal getLoanTotal(Long projectId) {
		//String hql = "select SUM(fund.afterMoney) from BpFundIntent as fund where fund.bidPlanId=?  and fund.fundType !='principalLending' ";
		String hql ="SELECT * FROM `bp_cust_relation` as p where p.p2pCustId =? and p.offlineCustType='p_loan'";
		String sql = "";
		BigDecimal money= new BigDecimal(0);
		BpCustRelation bp=(BpCustRelation) this.getSession().createSQLQuery(hql).addEntity(BpCustRelation.class).setParameter(0, projectId).uniqueResult();
		if(bp!=null){
			sql ="select sum(bp.payMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and fundType='principalLending' and bp.isValid=0  and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?)";
			money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
		}
		return money;
	} 
	
	@Override
	public BigDecimal getMoney(Long projectId, String type) {
		BigDecimal money= new BigDecimal(0);
		String sql = "";
		String hql ="SELECT * FROM `bp_cust_relation` as p where p.p2pCustId =? and p.offlineCustType='p_loan'";
		BpCustRelation bp=(BpCustRelation) this.getSession().createSQLQuery(hql).addEntity(BpCustRelation.class).setParameter(0, projectId).uniqueResult();
		if(bp!=null){
			if("逾期金额".equals(type)){
				sql ="select sum(bp.notMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType<> 'principalLending' and (UNIX_TIMESTAMP(now())*1000)>(UNIX_TIMESTAMP(bp.intentDate)*1000) and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?)";
				money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
			}else if("待还金额".equals(type)){
				sql ="select sum(bp.notMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType<> 'principalLending' and ISNULL(bp.factDate) and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?)";
				money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
			}else if("近10天借款总额".equals(type)){
				sql ="select sum(bp.notMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType<> 'principalLending' and (DATE_SUB(CURDATE(),INTERVAL 10 day) <= date(bp.intentDate)) and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?)";
				money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
			}else if("total".equals(type)){
				sql ="select sum(bp.notMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType<> 'principalLending' and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?)";
				money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
			}else{
				/*近10天应还金额*/
				String begeginDate=DateUtil.getCurrentMonthFistDay();
				String ednDate=DateUtil.getCurrentMonthLastDay();
				sql ="select sum(bp.notMoney)  as bp from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType<> 'principalLending' and (DATE_SUB(CURDATE(),INTERVAL 10 day) <= date(bp.intentDate)) and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?) and bp.intentDate between '" +begeginDate+"' and '"+ednDate+"'";
				money=(BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").uniqueResult();
			}
		}
		return money;
		
		
	}

	@Override
	public SlFundIntent getSlFoundIntent(Long projectId) {
		String sql ="from FundIntent as sl where sl.projectId=? GROUP BY sl.bidPlanId";
		List<SlFundIntent> list = this.getSession().createQuery(sql).setParameter(0, projectId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Date getIntentDate(Long projectId, int payintentPeriod, String type) {
		String sql = "";
		if("next".equals(type)){
			sql = "SELECT fund.intentDate from bp_fund_intent fund where fund.bidPlanId=? and ISNULL(fund.factDate) GROUP BY fund.payintentPeriod HAVING fund.payintentPeriod=?";
			return (Date)this.getSession().createSQLQuery(sql).setParameter(0, projectId).setParameter(1, payintentPeriod).uniqueResult();
		}else{
			sql = "SELECT fund.intentDate from bp_fund_intent fund where fund.bidPlanId=? and fund.factDate!='' GROUP BY fund.payintentPeriod";
			List<java.util.Date> list = this.getSession().createSQLQuery(sql).setParameter(0, projectId).list();
			if(list!=null&&list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}
	}

	@Override
	public Integer getNextCount(Long projectId) {
		String sql ="SELECT fund.payintentPeriod from bp_fund_intent fund where fund.projectId=? and fund.factDate!='' GROUP BY fund.payintentPeriod "; 
		List<Integer> list = this.getSession().createSQLQuery(sql).setParameter(0, projectId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return 0;
		}
	}

	@Override
	public Integer getPayintentPeriodMax(Long projectId) {
		String sql ="select MAX(fund.payintentPeriod) from bp_fund_intent fund where fund.projectId=?";
		return (Integer)this.getSession().createSQLQuery(sql).setParameter(0, projectId).uniqueResult();
	}

	@Override
	public BigDecimal getnotMoneySum(Long projectId, int payintentPeriod) {
		String sql ="SELECT SUM(fund.notMoney) from bp_fund_intent fund where fund.bidPlanId=? and ISNULL(fund.factDate) and ISNULL(fund.loanerRepayMentStatus) and  ISNULL(fund.slEarlyRepaymentId)  and  fund.payintentPeriod=? and fundType not in('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest')";
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, projectId).setParameter(1, payintentPeriod).uniqueResult();
	}

	@Override
	public Integer getCountDetailLoan(Long bidPlanId, String type ,String investpersonName) {
		String sql ="";
		if("nowOverdue".equals(type)){
			sql = "select COUNT(*) from sl_fund_intent as fund where fund.bidPlanId=? and ISNULL(fund.factDate) and  fund.investpersonName=? ";
			java.math.BigInteger bugNum = (java.math.BigInteger)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).setParameter(1, investpersonName).uniqueResult();
			return bugNum.intValue();
		}else if("historyLoan".equals(type)){
			sql ="select COUNT(*) from sl_fund_intent as fund where fund.bidPlanId=? and fund.intentDate<NOW() ";
			java.math.BigInteger bugNum = (java.math.BigInteger)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
			return bugNum.intValue();
		}else if("repayment".equals(type)){
			sql ="select COUNT(*) from sl_fund_intent as fund where fund.bidPlanId=? and fund.intentDate<NOW() and fund.factDate is not null";
			java.math.BigInteger bugNum = (java.math.BigInteger)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
			return bugNum.intValue();
		}else{
			return 0;
		}
		
	}

	@Override
	public BigDecimal getLoanTotle(Long bidPlanId, String type,String investpersonName) {
		if("thisTime".equals(type)){
			String sql ="select SUM(fund.incomeMoney) from sl_fund_intent as fund where fund.bidPlanId=? ";
			return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
		}else{
			String sql = "select SUM(fund.incomeMoney) from sl_fund_intent as fund where fund.investpersonName=? ";
			return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, investpersonName).uniqueResult();
		}
	}

	@Override
	public List<FundIncome> getPay(String bidId,String type,String userName,String orderNo) {
		String sql="";
		if(type.equals("lookUpLoan")){
			 sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
				+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId+" and sfi.investpersonName='"+userName+"' and sfi.fundType in ('loanInterest','principalRepayment')";
		}else if(type.equals("PlanbackingOne") ){
			 sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
				+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +" and sfi.investpersonName='"+userName+"'  and sfi.fundType in ('loanInterest','principalRepayment')";
		}else if(type.equals("Financial")){
			 sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
					+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +" and sfi.fundType <> 'principalLending'";
		}else if(type.equals("Repaymented")){
			 sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
					+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +" and sfi.fundType <> 'principalLending'  and sfi.isCheck=0 and sfi.isValid=0 and sfi.factDate is not null";
		}else if(type.equals("Loan")){
			sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
				+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +"  and sfi.fundType  in ('loanInterest','consultationMoney','serviceMoney','principalRepayment')";
	
		}else if (type.equals("Financing")){
			sql = "select sfi.fundIntentId,sfi.bidPlanId as planId,sfi.fundType,sfi.incomeMoney,sfi.notMoney,sfi.afterMoney,sfi.payintentPeriod,sfi.projectName,sfi.intentDate,sfi.factDate,sfi.investPersonId,sfi.repaySource,sfi.accrualMoney,sfi.orderNo "
			//	+ "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +" and sfi.investpersonName='"+userName+"' and sfi.orderNo= '"+orderNo+"' ";
			    + "	from bp_fund_intent sfi where sfi.isCheck=0 and sfi.isValid=0 and sfi.bidPlanId="+bidId +" and sfi.orderNo= '"+orderNo+"' ";
		}
		System.out.println("sql=="+sql);
		List<FundIncome> list = new ArrayList<FundIncome>();
		List li = this.jdbcTemplate.query(sql,new rowMapperIntent());
		return li;
	}
	
	class  rowMapperIntent implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			FundIncome income = new FundIncome();
			income.setPlanId(rs.getLong("planId"));
			income.setAfterMoney(rs.getBigDecimal("afterMoney"));
			income.setFundType(rs.getString("fundType"));
			income.setIncomeMoney(rs.getBigDecimal("incomeMoney"));
			income.setIntentDate(rs.getDate("intentDate"));
			income.setPayintentPeriod(rs.getInt("payintentPeriod"));
			income.setProjectName(rs.getString("projectName"));
			income.setNotMoney(rs.getBigDecimal("notMoney"));
			income.setFundIntentId(rs.getLong("fundIntentId"));
			income.setInvestId(rs.getLong("investPersonId"));
			income.setRepaySource(rs.getShort("repaySource"));
			income.setAccrualMoney(rs.getBigDecimal("accrualMoney"));
			income.setFactDate(rs.getDate("factDate"));
			income.setOrderNo(rs.getString("orderNo"));
			return income;
		}
}
} 
