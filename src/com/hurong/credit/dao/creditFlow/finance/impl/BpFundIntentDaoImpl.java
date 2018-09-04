package com.hurong.credit.dao.creditFlow.finance.impl;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.SlFundIntentPeriod;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.customer.BpCustRelation;
import sun.nio.cs.HistoricallyNamedCharset;

@SuppressWarnings("unchecked")
public class BpFundIntentDaoImpl extends BaseDaoImpl<BpFundIntent> implements
		BpFundIntentDao {

	public BpFundIntentDaoImpl() {
		super(BpFundIntent.class);
	}

	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private BpFundProjectDao bpFundProjectDao; 
	@Resource
	private PlBidPlanDao plBidPlanDao;
	@Override
	public List<FundIncome> getIncome(HttpServletRequest request,PagingBean pb,Long investPersonId) {
		String sql = "select * from ( "
				+ "	SELECT"
				+ "	plan.bidId as planId, "
				+ "	plan.bidProName as projectName, "
				+ "	intent.fundType as fundType,"
				+ "	intent.intentDate as intentDate, "
				+ "	intent.incomeMoney as incomeMoney, "
				+ "	intent.afterMoney as afterMoney, "
				+ "	intent.payintentPeriod as payintentPeriod, "
				+ "	intent.notMoney as notMoney,"
				+ "	intent.payMoney as payMoney,"
				
				//+ "	pInfo.investPersonId as investPersonId, "
				+ "	intent.investPersonId as investPersonId "
				+ "FROM "
				+ "	bp_fund_intent AS intent "
				+ "LEFT JOIN pl_bid_plan AS plan ON intent.bidPlanId = plan.bidId "
				//+ "LEFT JOIN invest_person_info as pInfo ON intent.investPersonId = pInfo.investPersonId  "
				+ "WHERE " + "	intent.bidPlanId IS NOT NULL " + "union ALL "
				+ "select " 
				+ "	interest.mmplanId as planId, "
				+ "	interest.mmName as projectName, "
				
				+ "	interest.fundType as fundType, "
				+ "	interest.intentDate as intentDate, "
				+ "	interest.incomeMoney as incomeMoney, "
				+ "	interest.afterMoney as afterMoney, "
				+ "	interest.periods as payintentPeriod, " 
				+ "	(interest.incomeMoney-interest.afterMoney) as notMoney, "
				+ "	interest.payMoney as payMoney,"
				+ "	interest.investPersonId as investPersonId "
				+ "from pl_mm_order_assigninterest as interest "
				+ "where interest.keystr = 'mmplan' " + ") as fund_income where 1=1 ";
		if(request!=null){
			/**
			 * 1、投资人
			 * 2、notMoney  状态
			 * 3、projectName 项目名称
			 * 4、selectTime  计划到帐日
			 * 5、incomeMoney 金额
			 * **/
			
			//投资人
			if(null!=investPersonId){
				sql += " and fund_income.investPersonId = "+investPersonId;
			}
			//状态
			String notMoney = request.getParameter("notMoney");
			if(null!=notMoney&&!"".equals(notMoney)){	
				if("1".equals(notMoney)){
					sql += " and fund_income.notMoney >0 ";
				}else if("2".equals(notMoney)){
					sql += " and fund_income.notMoney =0 ";
				}
			}
			//项目名称
			String projectName = request.getParameter("Q_projectName_S_LK");
			if(null!=projectName&&!"".equals(projectName.trim())){	
				sql += " and fund_income.projectName like '%"+projectName.trim()+"%'";
			}
			//计划到帐日
			String selectTime = request.getParameter("selectTime");
			if(null!=selectTime&&!"".equals(selectTime.trim())){	
				sql += " and fund_income.intentDate >= '"+selectTime.trim()+" 00:00:00'";
			//	sql += " and  fund_income.intentDate <= '"+selectTime+" 23:59:59'";
			}
			String selectTime2 = request.getParameter("selectTime2");
			if(null!=selectTime2&&!"".equals(selectTime2.trim())){	
				//sql += " and fund_income.intentDate >= '"+selectTime+" 00:00:00'";
				sql += " and  fund_income.intentDate <= '"+selectTime2.trim()+" 23:59:59'";
			}
			//金额
			String incomeMoney = request.getParameter("Q_incomeMoney_BD_EQ");
			if(null!=incomeMoney&&!"".equals(incomeMoney.trim())){	
				sql += " and  fund_income.incomeMoney = "+incomeMoney.trim()+" ";
			}	
		}
		sql += " and fund_income.fundType <> 'principalLending'  ";
		sql += " order by fund_income.intentDate,fund_income.fundType desc";
		List<FundIncome> list = new ArrayList<FundIncome>();
		if(pb==null){
			list = this.jdbcTemplate.query(sql,new rowMapper());
			if(list!=null){
				pb.setTotalItems(list.size());
				pb.setTotalItems(list.size());
			}
			
		}else{
			List li = this.jdbcTemplate.query(sql,new rowMapper());
			if(li!=null){
				pb.setTotalItems(li.size());
				pb.setTotalItems(li.size());
			}
			sql += " limit "+pb.getStart()+","+pb.getPageSize();
			list = this.jdbcTemplate.query(sql,new rowMapper());
		}
		return list;
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
			income.setNotMoney(rs.getBigDecimal("notMoney"));
			income.setPayMoney(rs.getBigDecimal("payMoney"));
			income.setPayintentPeriod(rs.getInt("payintentPeriod"));
			income.setProjectName(rs.getString("projectName"));
			return income;
		}
		
	}

	@Override
	public List<BpFundIntent> getList(Long bidPlanId, PagingBean pb) {
		String hql = "from BpFundIntent as bpf where bpf.bidPlanId = ?";
		if(null==pb){
			return super.findByHql(hql, new Object[]{bidPlanId});
		}else{
			return super.findByHql(hql,  new Object[]{bidPlanId}, pb);
		}
	}

	public java.math.BigDecimal getTotle(String fundType, String investPeronName){
		if("loanInterest".equals(fundType)){
			//待收利息
			return (java.math.BigDecimal)this.getSession().createSQLQuery("select sum(b.notMoney) from bp_fund_intent as b where b.fundType= 'loanInterest' and b.investpersonName=? and b.isCheck=0 and b.isValid=0").setParameter(0, investPeronName).uniqueResult();
		}else if("principalRepayment".equals(fundType)){
			//待收本金（还款）
			return (java.math.BigDecimal)this.getSession().createSQLQuery("select sum(b.notMoney) from bp_fund_intent as b where b.fundType= 'principalRepayment' and b.investpersonName=? and b.isCheck=0 and b.isValid=0").setParameter(0, investPeronName).uniqueResult();
		}else if("principalLending".equals(fundType)){
			//回款
			return (java.math.BigDecimal)this.getSession().createSQLQuery("select sum(b.afterMoney) from bp_fund_intent as b where b.fundType= 'principalLending' and b.investpersonName=? and b.isCheck=0 and b.isValid=0").setParameter(0, investPeronName).uniqueResult();
		}else{
			return (java.math.BigDecimal)this.getSession().createSQLQuery("select sum(b.incomeMoney) from bp_fund_intent as b where  b.investpersonName=? ").setParameter(0, investPeronName).uniqueResult();
		}
	}
	@Override
	public List<BpFundIntent> getBpFund(String fundType, String investPersonName,String time) {
			List<Long> inList = new ArrayList<Long>();
			inList.add(Long.valueOf(9));
			inList.add(Long.valueOf(11));
			return this.getSession().createQuery("select sum(b.payMoney) from BpFundIntent as b where b.fundType= :fundType ").setParameter("fundType", fundType).list();
		}
	
	@Override
	public BigDecimal getincome(String type, String investpersonName) {
		 String hql ="";
		 Calendar calen=Calendar.getInstance();
		 int years = calen.get(Calendar.YEAR);
		 int month =  calen.get(Calendar.MONTH);
		if("newYears".equals(type)){
			hql = "select SUM(b.afterMoney) from bp_fund_intent b where YEAR(b.factDate)=? and b.investpersonName=? and b.fundType='loanInterest' GROUP BY YEAR(b.factDate)";
			//当年收益
			return (BigDecimal)this.getSession().createSQLQuery(hql).setParameter(0, years).setParameter(1, investpersonName).uniqueResult();
		}else{
			hql = "select SUM(b.afterMoney) from bp_fund_intent b where YEAR(b.factDate)=? and month(b.factDate)=? and b.investpersonName=? and b.fundType='loanInterest' GROUP BY YEAR(b.factDate)";
			//上月收益
			return (BigDecimal)this.getSession().createSQLQuery(hql).setParameter(0, years).setParameter(1, month).setParameter(2, investpersonName).uniqueResult();
		}
	}

	@Override
	public Long getProjectInfo(String fundType, String investPersonName,
			String type) {
		Calendar calen=Calendar.getInstance();
		 int years = calen.get(Calendar.YEAR);
		 int month =  calen.get(Calendar.MONTH)+1;
		if("totle".equals(type)){
			String hql ="select COUNT(*) from BpFundIntent  b where b.investPersonName=? and b.fundType=? GROUP BY b.bidPlanId";
			List<Long> list = this.getSession().createQuery(hql).setParameter(0, investPersonName).setParameter(1, fundType).list();
			if(list!=null&&list.size()>0){
				return new Long(list.size());
			}else{
				return new Long(0);
			}
			//return (Long)this.getSession().createQuery(hql).setParameter(0, investPersonName).setParameter(1, fundType).uniqueResult();
		}if("totle".equals(type)){
			String hql ="select COUNT(*) from BpFundIntent  b where b.investPersonName=? and b.fundType=? GROUP BY b.bidPlanId";
			List<Long> list = this.getSession().createQuery(hql).setParameter(0, investPersonName).setParameter(1, fundType).list();
			if(list!=null&&list.size()>0){
				return new Long(list.size());
			}else{
				return new Long(0);
			}
			//return (Long)this.getSession().createQuery(hql).setParameter(0, investPersonName).setParameter(1, fundType).uniqueResult();
		}else{
			String hql ="select COUNT(*) from  BpFundIntent b where YEAR(b.factDate)=? and month(b.factDate)=? and b.investPersonName=? and b.fundType=? GROUP BY month(b.factDate)";
			return (Long)this.getSession().createQuery(hql).setParameter(0, years).setParameter(1, month).setParameter(2, investPersonName).setParameter(3, fundType).uniqueResult();
		}
	}

	@Override
	public List<BpFundIntent> getProjectInfoInvest(Long bidPlanId,String fundType,String investpersonName) {
		String sql = "select * from bp_fund_intent b where b.fundType=? and b.bidPlanId =? ";
		if(!"null".equals(investpersonName)){
			sql += " and b.investpersonName='"+investpersonName+"'";
		}else{
			sql += "  GROUP BY b.orderNo  ORDER BY b.intentDate DESC ";
		}
		return this.getSession().createSQLQuery(sql)
				.addEntity(BpFundIntent.class)
				.setParameter(0, fundType)
				.setParameter(1, bidPlanId).list();
	}
	

	@Override
	public BigDecimal getByPlanIdAndOtherRequest(String temp, String planId,
			String peridId, String type) {
		String sql= "select SUM(bp.notMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
				    "and bp.orderNo=? and bp.payintentPeriod=? and bp.isCheck=0 and bp.isValid=0 ";
		if(type!=null){
			sql=sql+" and bp.fundType in "+type;
		}
		return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId))
					.setParameter(1, temp).setParameter(2, Integer.valueOf(peridId)).uniqueResult();
	}

	@Override
	public BigDecimal getTotal(Long bidPlanId, String type) {
		String sql = "";
		if("afterMoney".equals(type)){
			sql = "select SUM(fund.afterMoney) from bp_fund_intent fund where  (fund.fundType='loanInterest' or fund.fundType='principalRepayment') and fund.bidPlanId=?";
		}else{
			sql = "select SUM(fund.notMoney) from bp_fund_intent fund where  (fund.fundType='loanInterest' or fund.fundType='principalRepayment') and fund.bidPlanId=?";
		}
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
	}

	@Override
	public Integer getPayintentPeriodMax(Long bidPlanId) {
		String sql ="select MAX(fund.payintentPeriod) from bp_fund_intent fund where fund.projectId=?";
		return (Integer)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
	}

	@Override
	public BigDecimal getnotMoneySum(Long bidPlanId, int payintentPeriod) {
		String sql ="SELECT SUM(fund.notMoney) from bp_fund_intent fund where fund.bidPlanId=? and ISNULL(fund.factDate) and  fund.payintentPeriod=?";
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).setParameter(1, payintentPeriod).uniqueResult();
	}

	@Override
	public Date getIntentDate(Long bidPlanId, int payintentPeriod, String type) {
		String sql = "";
		if("next".equals(type)){
			sql = "SELECT fund.intentDate from bp_fund_intent fund where fund.bidPlanId=? and ISNULL(fund.factDate) GROUP BY fund.payintentPeriod HAVING fund.payintentPeriod=?";
			return (Date)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).setParameter(1, payintentPeriod).uniqueResult();
		}else{
			sql = "SELECT fund.intentDate from bp_fund_intent fund where fund.bidPlanId=? and fund.factDate!='' GROUP BY fund.payintentPeriod";
			List<java.util.Date> list = this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).list();
			if(list!=null&&list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}
	}

	@Override
	public Integer getNextCount(Long bidPlanId) {
		String sql ="SELECT fund.payintentPeriod from bp_fund_intent fund where fund.bidPlanId=? and fund.factDate!='' GROUP BY fund.payintentPeriod "; 
		List<Integer> list = this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return 0;
		}
	}

	@Override
	public BigDecimal getMoney(Long bidPlanId, String fundType, String type,String orderNo) {
		String sql = "";
		if("afterMoney".equals(type)){
			sql = "select SUM(b.afterMoney) from  bp_fund_intent as b where  b.bidPlanId=? and b.fundType!='principalLending'  ";
		}else if("notMoney".equals(type)&&orderNo!=null){
			sql = "select SUM(b.notMoney) from  bp_fund_intent as b where  b.bidPlanId=?  and b.investpersonName='"+fundType+"' and  b.orderNo='"+orderNo+"'  and b.fundType in('loanInterest','principalRepayment')";
		}else if(orderNo==null){
			sql = "select SUM(b.notMoney) from  bp_fund_intent as b where  b.bidPlanId=?  and b.investpersonName='"+fundType+"'   and b.fundType in('loanInterest','principalRepayment')";
		}
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
	}

	@Override
	public BigDecimal getFinancialMoney(Long bidPlanId, String type) {
		String sql = "";
		if("afterMoney".equals(type)){
			sql = "select SUM(intent.afterMoney) from bp_fund_intent as intent where intent.bidPlanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("payMoney".equals(type)){
			sql = "select SUM(intent.payMoney) from bp_fund_intent as intent where intent.bidPlanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("notMoney".equals(type)){
			sql = "select SUM(intent.notMoney) from bp_fund_intent as intent where intent.bidPlanId=? and intent.fundType='loanInterest' or intent.fundType='principalRepayment'";
		}else if("all".equals(type)){
			sql = "select SUM(intent.incomeMoney) from bp_fund_intent as intent where intent.bidPlanId=? and  intent.fundType in ('loanInterest','consultationMoney','serviceMoney','principalRepayment') ";
		}
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).uniqueResult();
	}

	@Override
	public List<BpFundIntent> getListByBidPlanId(long parseLong, Object object) {
		
		String sql="select SUM(b.notMoney) as notMoney,SUM(b.incomeMoney) as incomeMoney,b.factDate,b.intentDate,b.payintentPeriod,b.fundType,SUM(b.accrualMoney) AS accrualMoney from BpFundIntent as b where b.isValid=0 and b.isCheck=0 and b.bidPlanId= :bidPlanId and b.fundType in('loanInterest','principalRepayment','interestPenalty')  GROUP BY payintentPeriod";//b.loanerRepayMentStatus is NULL
		List list=(ArrayList<BpFundIntent>)this.getSession().createQuery(sql)
		.setParameter("bidPlanId", parseLong).list();
		
		List<BpFundIntent> frblist = new ArrayList<BpFundIntent>();
		    for(int i=0;i<list.size();i++){
		        Object[] obj = (Object[]) list.get(i);
		        BpFundIntent frb = new BpFundIntent();
		        frb.setNotMoney(new BigDecimal(obj[0].toString()));
		        frb.setIncomeMoney(new BigDecimal(obj[1].toString()));
		        frb.setFactDate((Date)obj[2]);
		        frb.setIntentDate((Date)obj[3]);
		        frb.setPayintentPeriod((Integer)obj[4]);
		        frb.setFundType(obj[5].toString());
		        frb.setAccrualMoney(new BigDecimal(obj[6].toString()));
		        /*frb.setPunishAccrual((java.math.BigDecimal)obj[6]);//罚息利率
		        java.util.Date fiveDate = getDateAfter(frb.getIntentDate(),6);//得到预期还款后的地6天时间
		        int day = getDateDay(fiveDate);//罚款的天数
		        if("".equals(frb.getPunishAccrual())||frb.getPunishAccrual()==null){
		        	frb.setPunishAccrual(new BigDecimal(0));
		        }
		        java.math.BigDecimal accrualMoney = frb.getNotMoney().multiply(frb.getPunishAccrual()).multiply(new BigDecimal(day<=0?0:day));
		        frb.setAccrualMoney(accrualMoney);*/
                frblist.add(frb);
		}
		return frblist;
		}
	/**
	 * 得到5天后的时间(即第6天的时间)
	 * @param d
	 * @param day
	 * @return
	 */
	 public  Date getDateAfter(Date d,int day){  
		   Calendar now =Calendar.getInstance();  
		   now.setTime(d);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		   return now.getTime();  
	}  
	 /**
	  * 得到相差的天数
	  * @param date
	  * @return
	  */
	public int getDateDay(Date date){
		long millisecond =  new Date().getTime()- date.getTime();
	    int day = (int) (millisecond / 24L / 60L / 60L / 1000L);
	    return day;
	} 
	 
	@Override
	public List<BpFundIntent> getBpFundIntentAll(String bidId, String type,String userName) {
		String sql = "";
		if("Financial".equals(type)){
			//稳安总览--理财--回款计划
			sql = "select * from bp_fund_intent b where (b.fundType='loanInterest' or b.fundType='principalRepayment') and b.bidPlanId =? and b.investpersonName=? ORDER BY b.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("Planbacking".equals(type)){
			//理财管理--回款中--回款计划
			sql = "select * from bp_fund_intent b where  b.bidPlanId =? and b.investpersonName=? ORDER BY b.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("Planback".equals(type)){
			//理财管理--已回款--回款计划
			sql = "select * from bp_fund_intent b where  b.bidPlanId =? and b.investpersonName=? ORDER BY b.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("PlanSuccess".equals(type)){
			//理财管理--投标成功--回款计划
			sql = "select * from bp_fund_intent as fund inner JOIN pl_bid_info as info on fund.bidPlanId= info.bidId where  info.state>=1 and info.state<4 " 
				+" AND info.userName='"+userName+"'   and fund.bidPlanId =? and fund.investpersonName=? ORDER BY fund.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("PlanFila".equals(type)){
			//理财管理--投标失败--回款计划
			sql = "select * from bp_fund_intent as fund inner JOIN pl_bid_info as info on fund.bidPlanId= info.bidId where info.state=4 " 
				+" AND info.userName='"+userName+"'  and fund.bidPlanId =? and fund.investpersonName=? ORDER BY fund.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("PlanFinancial".equals(type)){
			//理财回款--回款计划
			sql = "select * from bp_fund_intent b where  b.bidPlanId =? and b.investpersonName=? ORDER BY b.payintentPeriod DESC";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("financialreturn".equals(type)){
			sql = "select * from bp_fund_intent as fund where fund.bidPlanId=? and fund.investpersonName=?  GROUP BY fund.payintentPeriod";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).setParameter(1, userName).list();
		}else if("lookUpLoan".equals(type)){
			//借款管理--还款管理--查看
			sql = "select * from bp_fund_intent as fund where fund.bidPlanId=?  ";
			return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidId).list();
		}
		return null;
	}

	@Override
	public Integer getProgressTotal(Long bidPlanId, String userName, String type) {
		String sql = "";
		if("total".equals(type)){
			sql = "select fund.payintentPeriod from bp_fund_intent as fund where fund.projectId=? and fund.investpersonName=? and fund.payintentPeriod!=0 GROUP BY fund.payintentPeriod";
		}else{
			sql = "select fund.payintentPeriod from bp_fund_intent as fund where fund.projectId=? and fund.investpersonName=? and fund.factDate!='' and fund.payintentPeriod!=0 GROUP BY fund.payintentPeriod";
		}
		List<Integer> list = this.getSession().createSQLQuery(sql).setParameter(0, bidPlanId).setParameter(1, userName).list();
		if(list!=null&&list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}

	@Override
	public Integer getFactDateNull(String userName) {
		String sql = "SELECT COUNT(bp.fundIntentId) from bp_fund_intent bp WHERE bp.factDate is NULL and bp.isCheck=0 and isValid=0 and bidPlanId in ("
			+"SELECT p.bidId from pl_bid_plan p where p.receiverP2PAccountNumber = ?)";
		/**
		int num=0;
		String sql = "select * from bp_fund_intent as fund where ISNULL(fund.factDate) and fund.investpersonName=? ";
		List<BpFundIntent> list = this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, userName).list();
		if(list!=null&&list.size()>0){
			num = list.size();
		}
		return num;*/
		Object result = this.getSession().createSQLQuery(sql).setParameter(0, userName).uniqueResult();
		return result==null?0:Integer.valueOf(result.toString());
	}

	@Override
	public List<BpFundIntent> getByRequestNo(String requestNo) {
		String sql = " from BpFundIntent as fund where fund.requestNo=?";
		return this.getSession().createQuery(sql).setParameter(0, requestNo).list();
	}

	@Override
	public List<BpFundIntent> getCouponsIntent(String planId, String peridId,String requestNo,String fundType) {
		String sql = "";
		if(fundType!=null&&!fundType.equals("")){
			if(peridId!=null&&!peridId.equals("")){
				 sql = " from BpFundIntent as fund where fund.bidPlanId=? and fund.payintentPeriod=? and fund.requestNo=? and fund.fundType=? and fund.factDate is  null  and fund.isValid=0";
					return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, Integer.valueOf(peridId)).setParameter(2, requestNo).setParameter(3, fundType).list();
			}else{
				if(fundType.equals("0")){
					sql = " from BpFundIntent as fund where fund.bidPlanId=?  and fund.orderNo=? and fund.fundType " +
							" in('couponInterest','principalCoupons','subjoinInterest') and fund.factDate is  null ";
				}else{
					sql = " from BpFundIntent as fund where fund.bidPlanId=?  and fund.orderNo=? and fund.fundType " +
							" in ('couponInterest','principalCoupons','subjoinInterest','commoninterest') and fund.factDate is  null ";
				}
				System.out.println("====>"+sql);
				return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, requestNo).list();
			}
		}else{
			 sql = " from BpFundIntent as fund where fund.bidPlanId=? and fund.payintentPeriod=? and fund.requestNo=? and fund.factDate is  null ";
			 System.out.println("====>"+sql);
			 return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, Integer.valueOf(peridId)).setParameter(2, requestNo).list();
		}
	
	
	}

	@Override
	public BpFundIntent getBpFundIntent(Long projectId) {
		String sql ="select * from bp_fund_intent as sl where sl.projectId=? ";
		List<BpFundIntent> list = this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, projectId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<BpFundIntent> getFundIntent(String investpersonName) {
		String sql = "select * from bp_fund_intent as fund where fund.investpersonName=? GROUP BY fund.bidPlanId";
		List<BpFundIntent> list = this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, investpersonName).list();
		return list;
	}

	@Override
	public List<BpFundIntent> getLoanPlanId(Long userId) {
		List<BpFundIntent> list = null;
		String hql ="SELECT * FROM `bp_cust_relation`   as p where p.p2pCustId =?";
		BpCustRelation bp=(BpCustRelation) this.getSession().createSQLQuery(hql).addEntity(BpCustRelation.class).setParameter(0, userId).uniqueResult();
		if(bp!=null){
			String sql ="select * from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType='principalRepayment' and bp.projectid in (select s.projectId from sl_smallloan_project as s where s.oppositeID=? and s.oppositeType=?) GROUP BY bp.projectId ";
			list = this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bp.getOfflineCusId()).setParameter(1, bp.getOfflineCustType().equals("p_loan")?"person_customer":"company_customer").list();
		}
		return list;
	}

	@Override
	public List<BpFundIntent> getIntentList(Long userId,String requestType,Pager pager) {
		String sql ="";
		if("finish".equals(requestType)){//已完成
			sql = "select sfi.* "
			  +" from bp_fund_intent sfi "
			  +" LEFT JOIN pl_bid_plan pbp on sfi.bidPlanId = pbp.bidId "	 
			  +" left join bp_persion_dir_pro bpdp on bpdp.pdirProId = pbp.pDirProId  "
			  +" left join cs_person cp on cp.id=bpdp.persionId "
			  +" left join bp_cust_relation bcr on bcr.offlineCusId=cp.id and bcr.offlineCustType='p_loan'  "
			  +" left join bp_cust_member bcm on bcm.id=bcr.p2pCustId  "
			  +" where bcm.id=? "
			  +" and sfi.fundType !='principalLending' and sfi.isCheck=0 and sfi.isValid=0 and sfi.notMoney=0  GROUP BY sfi.bidPlanId";
		}else if("authorization".equals(requestType)){//还款授权
			sql = "select sfi.* "
				  +" from bp_fund_intent sfi "
				  +" LEFT JOIN pl_bid_plan pbp on sfi.bidPlanId = pbp.bidId "	 
				  +" left join bp_persion_dir_pro bpdp on bpdp.pdirProId = pbp.pDirProId  "
				  +" left join cs_person cp on cp.id=bpdp.persionId "
				  +" left join bp_cust_relation bcr on bcr.offlineCusId=cp.id and bcr.offlineCustType='p_loan'  "
				  +" left join bp_cust_member bcm on bcm.id=bcr.p2pCustId  "
				  +" where bcm.id=? "
				  +" and sfi.fundType !='principalLending' and sfi.isCheck=0 and sfi.isValid=0  GROUP BY sfi.bidPlanId";
		}else{
			sql = "select * from bp_fund_intent as bp where bp.fundType='principalRepayment' and bp.bidPlanId in(SELECT p.bidId FROM `pl_bid_info`  as p  left  join pl_bid_plan  as plan on ( p.bidId=plan.bidId) where p.state=1 and p.userId=? and plan.isLoan=1)  GROUP BY bp.projectId ";
		}
		List<BpFundIntent> list=this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, userId).setFirstResult(pager.getPageNumber()).setMaxResults(pager.getPageSize()).list();
		return list;
	}

	@Override
	public BigDecimal getEveryTimeMoney(Long ProjectId) {
		String sql ="select SUM(bp.notMoney) from bp_fund_intent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.fundType='principalRepayment'  and bp.projectid=?  GROUP BY bp.projectId ";
		return (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, ProjectId).uniqueResult();
	}

	@Override
	public List<BpFundIntent> getFund(Long ProjectId) {
		String sql = "select * from bp_fund_intent as bp where bp.fundType='principalRepayment' and  bp.projectId=? ";
		return this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, ProjectId).list();
	}
	@Override
	public BigDecimal getLoanInterest(Long bidPlanId,String orderNo) {
		String hql="select sum(f.incomeMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BigDecimal getnotLoanInterest(Long bidPlanId,String orderNo) {
		String hql="select sum(f.notMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		System.out.println(hql);
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BigDecimal getPrincipalLending(Long bidPlanId,String orderNo) {
		String hql="select sum(f.payMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='principalLending'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BigDecimal getafterLoanInterestfromEnddate(Long bidPlanId,
			String orderNo, Date enddate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		String str = simpleDateFormat.format(enddate);
		String hql="select sum(f.afterMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		hql=hql+" and  f.intentDate > '"+str+"'";
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BigDecimal getnotLoanInterestToEnddate(Long bidPlanId,String orderNo,Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		String str = simpleDateFormat.format(endDate);
		String hql="select sum(f.notMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		hql=hql+" and  f.intentDate <= '"+str+"'";
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BpFundIntent getnotLoanInterestlast(Long bidPlanId,String orderNo,Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		String str = simpleDateFormat.format(endDate);
		String hql="from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		hql=hql+" and  f.intentDate <= '"+str+"' order by f.intentDate desc";
		List <BpFundIntent> list= getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		if(null!=list&&list.size()!=0){
			return list.get(0);
		}
	
		return null;
	}@Override
	public BpFundIntent getnotLoanInterestnext(Long bidPlanId,String orderNo,Date endDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		String str = simpleDateFormat.format(endDate);
		String hql="from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='loanInterest'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		hql=hql+" and  f.intentDate > '"+str+"' order by f.intentDate asc";
		
		List <BpFundIntent> list= getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		if(null!=list&&list.size()!=0){
			return list.get(0);
		}
	
		return null;
	}
	@Override
	public BigDecimal getAllAfterPrincipalMoney(Long bidPlanId,String orderNo) {
		String hql="select sum(f.afterMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='principalRepayment'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}
	@Override
	public BigDecimal getAllPrincipalMoney(Long bidPlanId,String orderNo) {
		String hql="select sum(f.notMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType='principalRepayment'";
		if(null!=orderNo && !orderNo.equals("")){
			hql=hql+" and f.orderNo='"+orderNo+"'";
		}
		System.out.println("==========>"+hql+"orderNo="+orderNo+"bidPlanId="+bidPlanId);
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}

	@Override
	public BigDecimal getOverdueMoney(Long bidPlanId) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String date=sd.format(new Date());
		String hql="select sum(f.notMoney) from BpFundIntent as f where f.bidPlanId=? and f.fundType!='principalLending' and f.isValid=0 and f.isCheck=0 and f.intentDate<'"+date+"' and (f.factDate is null or f.factDate>f.intentDate) and f.notMoney!=0";
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}

	@Override
	public List<BpFundIntent> listByBidIdAndEarlyDate(Long bidPlanId,
			String earlyDate, Long slEarlyRepaymentId) {
		String hql="from BpFundIntent as f where f.bidPlanId=? and (f.slEarlyRepaymentId is null or f.slEarlyRepaymentId!=?) and f.isValid=0 and f.isCheck=0 and f.intentDate>'"+earlyDate+"'";
		return this.findByHql(hql, new Object[]{bidPlanId,slEarlyRepaymentId});
	}
	@Override
	public List<BpFundIntent> listByEarlyDate(Long bidPlanId, String orderNo,
			String earlyDate,String fundType) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String hql="from BpFundIntent as f where f.factDate is NULL and " +
				" f.bidPlanId=? and f.orderNo=? and f.fundType=? and f.isValid=0 and f.isCheck=0 and f.interestStarTime<='"+earlyDate+"'"
					+" and f.interestEndTime>='"+sd.format(DateUtil.addDaysToDate(DateUtil.parseDate(earlyDate,"yyyy-MM-dd"), -1))+"'";
		return this.findByHql(hql, new Object[]{bidPlanId,orderNo,fundType});
	}

	@Override
	public List<BpFundIntent> listBySlEarlyRepaymentId(Long bidPlanId,
			Long slEarlyRepaymentId) {
		String hql="from BpFundIntent as f where f.bidPlanId=? and f.slEarlyRepaymentId=?";
		return this.findByHql(hql, new Object[]{bidPlanId,slEarlyRepaymentId});
	}

	@Override
	public BigDecimal getSumEarlymoney(String orderNo, String planId,
			String slEarlyPayId, Object object) {
		String sql= "select SUM(bp.notMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
	    "and bp.orderNo=? and bp.slEarlyRepaymentId=? and bp.isCheck=1 and bp.isValid=0 ";
		if(object!=null){
		sql=sql+" and bp.fundType in "+object;
		}
		BigDecimal totalMoney=(BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId))
		.setParameter(1, orderNo).setParameter(2, Long.valueOf(slEarlyPayId)).uniqueResult();
		if(totalMoney==null){
			totalMoney=new BigDecimal(0);
		}
		return totalMoney;
	}

	@Override
	public List<BpFundIntent> getByBidPlanIdAndIntentDate(Long bidPlanId,
			Date intentDate, String orderNo) {
		if(null ==orderNo){
			String sql = " from BpFundIntent as fund where fund.bidPlanId=? and fund.intentDate=?  ";
			return this.getSession().createQuery(sql).setParameter(0, bidPlanId).setParameter(1, intentDate).list();
		}else{
			String sql = "from BpFundIntent as fund where fund.bidPlanId=? and fund.intentDate=? and fund.orderNo=?  ";
			return this.getSession().createQuery(sql).setParameter(0, bidPlanId).setParameter(1, intentDate).setParameter(2, orderNo).list();
			
		}
		
	}

	@Override
	public List<SlFundIntentPeriod> listByBidPlanIdAndpayintentPeriod(
			PagingBean pb, Map<String, String> map) {
		
		StringBuffer sql =new StringBuffer("SELECT ");
		sql.append(" intentperiod.intentDate AS intentDate, ");
		sql.append(" intentperiod.factDate AS factDate, ");
		sql.append(" intentperiod.bidPlanId AS planId, ");
		sql.append(" intentperiod.payintentPeriod AS payintentPeriod, ");
		sql.append(" intentperiod.repaySource AS repaySource, ");
		sql.append(" intentperiod.punishDays AS punishDays, ");
		sql.append(" pbp.bidProNumber AS bidPlanProjectNum, ");
		sql.append(" pbp.bidProName AS bidPlanName, ");
		sql.append(" IFNULL(cp. NAME, ce.enterprisename) AS borrowName, ");
		sql.append(" IFNULL(cp.id, ce.id) AS borrowId, ");
		sql.append(" IFNULL(bcm.id, bcm1.id) AS ptpborrowId, ");
		sql.append(" IFNULL(bcm.loginname,bcm1.loginname) AS ptpborrowName, ");
		sql.append(" pbp.authorizationStatus AS authorizationStatus, ");
		sql.append(" bfp.oppositeType AS oppositeType FROM ");
		sql.append(" (SELECT * FROM bp_fund_intent intentperiod1 ");
		sql.append(" WHERE 	 intentperiod1.isValid = 0	AND intentperiod1.fundType != 'principalLending'	AND intentperiod1.afterMoney = 0 ");
		sql.append(" GROUP BY	intentperiod1.bidPlanId) AS  intentperiod ");
		//sql.append(" GROUP BY	intentperiod1.bidPlanId,	intentperiod1.intentDate) AS  intentperiod ");
		sql.append(" LEFT JOIN invest_person_info info ON intentperiod.orderNo = info.orderNo ");
		sql.append(" LEFT JOIN pl_bid_plan pbp ON intentperiod.bidPlanId = pbp.bidId ");
		sql.append(" LEFT JOIN bp_fund_project bfp ON bfp.id = intentperiod.preceptId ");
		sql.append(" LEFT JOIN cs_person cp ON cp.id = bfp.oppositeID ");
		sql.append(" AND bfp.oppositeType = 'person_customer' ");
		sql.append(" LEFT JOIN bp_cust_relation bcr ON bcr.offlineCusId = cp.id ");
		sql.append(" AND bcr.offlineCustType = 'p_loan' ");
		sql.append(" LEFT JOIN bp_cust_member bcm ON bcm.id = bcr.p2pCustId ");
		sql.append(" LEFT JOIN cs_enterprise ce ON ce.id = bfp.oppositeID ");
		sql.append(" AND bfp.oppositeType = 'company_customer' ");
		sql.append(" LEFT JOIN bp_cust_relation bcr1 ON bcr1.offlineCusId = ce.id ");
		sql.append(" AND bcr1.offlineCustType = 'b_loan' ");
		sql.append(" LEFT JOIN bp_cust_member bcm1 ON bcm1.id = bcr1.p2pCustId where 1=1 ");
		sql.append(" and (bcm1.id="+map.get("userId")+" or bcm.id="+map.get("userId")+") ");
		
	String projectName=map.get("projectName");
	if(null !=projectName&&!projectName.equals("")){
		sql.append(" and bfp.projectName like '%/"+projectName+"%'  escape '/' ");
	}
	String bidPlanProjectNum=map.get("bidPlanProjectNum");
	if(null !=bidPlanProjectNum&&!bidPlanProjectNum.equals("")){
		sql.append(" and pbp.bidProNumber  like '%/"+bidPlanProjectNum+"%'  escape '/' ");
	}
	String bidPlanName=map.get("bidPlanName");
	if(null !=bidPlanName&&!bidPlanName.equals("")){
		sql.append(" and pbp.bidProName  like '%/"+bidPlanName+"%'  escape '/' ");
	}
	
	String intentDateg=map.get("intentDateg");
	if(null !=intentDateg&&!intentDateg.equals("")){
		sql.append(" and intentperiod.intentDate <= '").append(intentDateg).append("'");
	}
	String intentDatel=map.get("intentDatel");
	if(null !=intentDatel&&!intentDatel.equals("")){
		sql.append(" and intentperiod.intentDate >= '").append(intentDatel).append("'");
	}
	String factDateg=map.get("factDateg");
	if(null !=factDateg&&!factDateg.equals("")){
		sql.append(" and intentperiod.factDate <= '").append(factDateg).append(" 59:59:59'");
	}
	String factDatel=map.get("factDatel");
	if(null !=factDatel&&!factDatel.equals("")){
		sql.append(" and intentperiod.factDate >= '").append(factDatel).append(" 00:00:00'");
	}
	String isPay=map.get("isPay");
	if(null ==isPay|| isPay.equals("")){
		sql.append(" and intentperiod.afterMoney =0 ");
	}
	
	if(null !=isPay&&!isPay.equals("")&&!isPay.equals("all")){
		if(isPay.equals("notPay")){
			sql.append(" and intentperiod.afterMoney =0 ");
		}
        if(isPay.equals("payed")){
        	sql.append( "and intentperiod.afterMoney !=0 ");
		}
        if(isPay.equals("none")){
        	sql.append(" and 1=2 ");
		}
	}
	
	if(null==pb){
	
		
		List<SlFundIntentPeriod>	list = this.jdbcTemplate.query(sql.toString(),new rowLoanMapper());
		  return list;
	}else{
	
		sql.append(" limit "+pb.getStart()+","+(pb.getStart()+pb.getPageSize()));
		List<SlFundIntentPeriod> list = this.jdbcTemplate.query(sql.toString(),new rowLoanMapper());
		  return list;
	}
	
	}
	class  rowLoanMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SlFundIntentPeriod income = new SlFundIntentPeriod();
			income.setIntentDate(rs.getDate("intentDate"));
			income.setFactDate(rs.getDate("factDate"));
			income.setPayintentPeriod(rs.getInt("payintentPeriod"));
			income.setPlanId(rs.getLong("planId"));
			income.setBidPlanName(rs.getString("bidPlanName"));
			income.setBidPlanProjectNum(rs.getString("bidPlanProjectNum"));
			income.setBorrowId(rs.getLong("borrowId"));
			income.setBorrowName(rs.getString("borrowName"));
			income.setPtpborrowId(rs.getLong("ptpborrowId"));
			income.setPtpborrowName(rs.getString("ptpborrowName"));
			income.setRepaySource(rs.getShort("repaySource"));
			income.setPunishDays(rs.getInt("punishDays"));
			income.setAuthorizationStatus(rs.getShort("authorizationStatus"));
			income.setOppositeType(rs.getString("oppositeType"));
			return income;
		}
		
	}

	@Override
	public BigDecimal getrepaymentTotal(Long id) {
		String sql1 = "SELECT sum(b.accrualMoney) FROM bp_fund_intent AS b where b.bidPlanId=? and b.fundType!='principalLending'";
		String sql2 = "SELECT sum(b.afterMoney) FROM bp_fund_intent AS b where b.bidPlanId=? and b.fundType!='principalLending'";
		if(this.getSession().createSQLQuery(sql1).setParameter(0, id).uniqueResult()==null||this.getSession().createSQLQuery(sql2).setParameter(0, id).uniqueResult()==null){
			return BigDecimal.ZERO;
		}else{
		return ((BigDecimal)this.getSession().createSQLQuery(sql1).setParameter(0, id).uniqueResult()).add((BigDecimal)this.getSession().createSQLQuery(sql2).setParameter(0, id).uniqueResult());
		}
	}
	
	@Override
	public BigDecimal getAfterMoney(String temp, String planId,
			String payintentPeriod, String type) {
		String sql= "select SUM(bp.incomeMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
	    "and bp.orderNo=?and bp.isCheck=0 and bp.isValid=0  and payintentPeriod=?";
		if(type!=null){
		sql=sql+" and bp.fundType in "+type;
		}
		System.out.println("sql====="+sql);
		return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId))
				.setParameter(1, temp).setParameter(2, Integer.valueOf(payintentPeriod)).uniqueResult();
	}

	@Override
	public List<BpFundIntent> getThirdBpFundIntentList(String bidId,
			Date intentDate) {
			String sql = "from BpFundIntent as fund where fund.bidPlanId=? and fund.intentDate=? and fund.notMoney >0  and fund.isValid=0 and fund.requestNo is not null and fund.fundType not in('couponInterest','principalCoupons','subjoinInterest','raiseinterest','commoninterest')";
			return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).setParameter(1, intentDate).list();
	}

	@Override
	public BigDecimal getBackMoney(String bidId, String period) {
		// TODO Auto-generated method stub
		String sql="select SUM(bp.notMoney)+SUM(bp.accrualMoney) from BpFundIntent as bp where bp.bidPlanId = ? and bp.isCheck=0 and bp.isValid=0  and bp.payintentPeriod=? and bp.fundType in ('serviceMoney','principalRepayment','loanInterest','interestPenalty','consultationMoney')";
		System.out.println("借款人应还金额是"+sql);
		return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId))
		.setParameter(1, Integer.valueOf(period)).uniqueResult();
	}
	
	public List<BpFundIntent> getListByBidIdAndPeriod(String planId,String period){
		String sql = "from BpFundIntent as bp where bp.bidPlanId = ? and bp.isCheck=0 and bp.isValid=0  and payintentPeriod=?";
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, Integer.valueOf(period)).list();
	}
	/*public List<BpFundIntent>  getUnReturnFund(){
		String sql = "from BpFundIntent as bp where  bp.isCheck=0 and bp.isValid=0 and bp.loanerRepayMentStatus=1 and bp.factDate=null";
		return  this.getSession().createQuery(sql).list();
	}*/

	@Override
	public List<BpFundIntent> getUnCheckIntent(String bidId) {
		// TODO Auto-generated method stub
		String sql = "from BpFundIntent as bp where bp.bidPlanId = ? and bp.isCheck=0 and bp.isValid=0  and bp.notMoney is not null  and bp.loanerRepayMentStatus is null";//and bp.slEarlyRepaymentId is not null
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).list();
	}

	@Override
	public List<BpFundIntent> getBySlEarlyId(String slEarlyId) {
		String sql = "from BpFundIntent as bp where bp.slEarlyRepaymentId = ?";
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(slEarlyId)).list();
	}

	@Override
	public List<BpFundIntent> getOriFund(String bidId) {
		String sql = "from BpFundIntent as bp where bp.bidPlanId = ? and bp.slEarlyRepaymentId is null";
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).list();
	}
	
	@Override
	public List<BpFundIntent> getByRequestNoLoaner(String requestNo) {
		// TODO Auto-generated method stub
		String sql = " from BpFundIntent as fund where fund.requestNOLoaner=? ";
		return this.getSession().createQuery(sql).setParameter(0, requestNo).list();
	}





	@Override
	public BigDecimal getBackAccMoney(String bidId, String period) {

		// TODO Auto-generated method stub
		String sql="select SUM(bp.accrualMoney) from BpFundIntent as bp where bp.bidPlanId = ? and bp.isCheck=0 and bp.isValid=0  and bp.payintentPeriod=? and bp.fundType in ('serviceMoney','principalRepayment','loanInterest','interestPenalty','consultationMoney')";
		System.out.println("借款人应还金额是"+sql);
		return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId))
		.setParameter(1, Integer.valueOf(period)).uniqueResult();
	
	}
	/**
     * 查询借款人款项台账（还款中待偿还的项目）
     * @param id
     * @param loginname
     * @param i
     * @param loanType
     * @return
     */
	@Override
	public List<ShowManageMoney> findLoanRepayMemtList(Long id,
			String loginname, Long planId, String loanType,HttpServletRequest request) {
		String start = null;
		String limit = null;
		String type = "";
		if(null!=planId && !"".equals(planId)){
			type=plBidPlanDao.get(planId).getGuaranteeWay();
		}
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}
			// TODO Auto-generated method stub
			String sql="SELECT " +
					 "pl.bidId as bidId, " +
				     "pl.bidProName as proName, " +
				     "pl.authorizationStatus as authorizationStatus, " +
				     "cont.url as url, " +
				     "p.loanerRepayMentStatus as loanerRepayMentStatus, " +
				     "p.payintentPeriod as payintentPeriod, " +
				     "p.intentDate as intentDate, " +
				     //"IF(pl.guaranteeWay is not NULL,comp.backDate,p.factDate) as factDate,"+
				     " max(p.factDate) as factDate,"+
				     //"p.factDate as factDate, "+
				     "sum(case when p.fundType='principalRepayment' then p.notMoney else 0.00 end) as principal, " +//未还款本金
				     "sum(case when p.fundType='loanInterest' then p.notMoney else 0.00 end) as loanInterest, " +//未还借款利息
				     "sum(case when (p.fundType='serviceMoney' or p.fundType='consultationMoney') then p.notMoney else 0.00 end) as serviceMoney, " +//未还款服务费
				     "sum(CASE WHEN p.fundType='interestPenalty' THEN  p.incomeMoney   " +
					 "when (p.fundType!='interestPenalty') then p.accrualMoney  ELSE 0.00 end) as compensationMoney,   " +//计划罚息和补偿息金额
				     "sum(case when ( p.fundType!='interestPenalty') then p.incomeMoney else 0.00 end) as intenttotal, " + //计划本息收款金额
					 "sum(p.accrualMoney ) as accMoney, " +//计划罚息金额
				//	 "sum(case when pl.guaranteeFeeWay = '1' or pl.guaranteeFeeWay = '2' then  p.afterMoney  when pl.guaranteeFeeWay = '2'  then (p.afterMoney+p.accrualMoney) else p.afterMoney end ) as backmoney, " +//已还款金额
					 "sum(CASE WHEN p.afterMoney>0 THEN (p.afterMoney + p.accrualMoney) ELSE 0 END) AS backmoney, " +//已还款总金额
					 "sum(case when p.afterMoney>0 then (p.notMoney+p.accrualMoney) " +
					 "when p.afterMoney=0 then (p.notMoney+p.accrualMoney) else 0.00 end) as repaymentTotal " +//全部待还款金额
				  "FROM " +
					 "bp_fund_intent AS p " ;
		   if(loanType.equals("repayMemt")){//近期需要还款的项目，每个项目只显示一条
			   sql = sql+ "INNER JOIN ( " +
			         " SELECT ssa.plt,ssa.perid FROM ("+
			         " SELECT "+
			         " pp.bidPlanId AS plt,"+
			         " min(pp.intentDate) AS perid"+
			         " FROM"+
			         " bp_fund_intent AS pp"+
			         " WHERE"+
			         " pp.isCheck = 0"+
			         " AND pp.isValid = 0"+
			         " AND pp.notMoney > 0"+
			         " AND pp.fundType IN ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty')"+
			         " AND (pp.loanerRepayMentStatus IS NULL)"+
			         " GROUP BY pp.bidPlanId"+
			         " UNION"+
			         " SELECT"+
			         " pp.bidPlanId AS plt,"+
			         " max(pp.intentDate) AS perid"+
			         " FROM"+
			         " bp_fund_intent AS pp"+
			         " WHERE"+
			         " pp.isCheck = 0"+
			         " AND pp.isValid = 0"+
			         " AND pp.fundType IN ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty')"+
			         " AND (pp.loanerRepayMentStatus = 1 OR pp.notMoney = 0)"+
			         " GROUP BY pp.bidPlanId ) AS ssa GROUP BY ssa.plt"+
					 ") AS ppp " +
				"on (p.bidPlanId = ppp.plt AND p.intentDate = ppp.perid) "; 
		 }
		   if(type!=null && !"".equals(type)){
			sql = sql+" LEFT JOIN pl_bid_compensatory comp on comp.planId = p.bidPlanId and comp.payintentPeriod = p.payintentPeriod ";   
		   }
		sql=sql+"LEFT JOIN pl_bid_plan AS pl ON (pl.bidId = p.bidPlanId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) ";
		sql=sql+"where  p.fundType IN ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty')  AND p.isCheck = 0 AND P.isValid = 0";  //if(pl.guaranteeWay = '1',p.fundType in ('principalRepayment'),if(pl.guaranteeFeeWay = '2',p.fundType in ('principalRepayment','loanInterest'),p.fundType IN ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty') ))
		if(loanType.equals("repayMemt")){//近期需要还款的项目，每个项目只显示一条,还款状态必须标的状态是还款中
			sql=sql+" AND pl.state=7  ";
		}else if(loanType.equals("repayMemtSignle")){//查询借款人具体标的每期的款项台账
			sql=sql+" and pl.bidId="+planId ;
		}else{//查询全部生成款项项目每一期的款项计划
			sql=sql+" AND pl.state>=7  and (p.loanerRepayMentStatus is NULL ) ";
		}
		if(loginname!=null&&!"".equals(loginname)){
			sql=sql+" and pl.receiverP2PAccountNumber='"+loginname+"'  ";
		}
		
		sql=sql+" GROUP BY " +
			"p.bidPlanId,p.intentDate ";
		System.out.println("sql="+sql);
		List list=null;
		Session session=this.getSession();
		if(null == start && null == limit){
			list = session.createSQLQuery(sql). 
			  addScalar("bidId",Hibernate.LONG).
			  addScalar("proName",Hibernate.STRING).
			  addScalar("authorizationStatus",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("principal",Hibernate.BIG_DECIMAL).
			  addScalar("loanInterest",Hibernate.BIG_DECIMAL).
			  addScalar("serviceMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("intenttotal",Hibernate.BIG_DECIMAL).
			  addScalar("accMoney",Hibernate.BIG_DECIMAL).
			  addScalar("backmoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
			  list();
		} else {
			list = session.createSQLQuery(sql). 
			  addScalar("bidId",Hibernate.LONG).
			  addScalar("proName",Hibernate.STRING).
			  addScalar("authorizationStatus",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("principal",Hibernate.BIG_DECIMAL).
			  addScalar("loanInterest",Hibernate.BIG_DECIMAL).
			  addScalar("serviceMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("intenttotal",Hibernate.BIG_DECIMAL).
			  addScalar("accMoney",Hibernate.BIG_DECIMAL).
			  addScalar("backmoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
			  setFirstResult(Integer.valueOf(start)).
			  setMaxResults(Integer.valueOf(limit)).
			  list();
		}
		releaseSession(session);
		return list;
	}

	 /**
     * 借款人招标中的项目（尚未进入还款前的状态）
     * @param id
     * @param loginname
     * @param planId
     * @param loanType
     * @return
     */
	@Override
	public List<ShowManageMoney> findLoanBeforeRepayMentList(Long id,
			String loginname, Long planId, String loanType,HttpServletRequest request) {
		String start = null;
		String limit = null;
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}
		// TODO Auto-generated method stub
		String sql="SELECT " +
		 "pl.bidId as bidId, " +
	     "pl.bidProName as proName, " +
	     "pl.authorizationStatus as authorizationStatus, " +
	     //"DATEDIFF(pl.endIntentDate,pl.startIntentDate) as loanLife, " +
	     
	     "CASE "+
			"WHEN project.payaccrualType = 'dayPay' THEN "+
			"CONCAT(project.payintentPeriod, '天') "+
			"WHEN project.payaccrualType = 'monthPay' THEN "+
			"CONCAT(project.payintentPeriod, '个月') "+
			"WHEN project.payaccrualType = 'seasonPay' THEN "+
			"CONCAT(project.payintentPeriod, '个季度') "+
			"WHEN project.payaccrualType = 'yearPay' THEN "+
			"CONCAT(project.payintentPeriod, '年') "+
			"WHEN project.payaccrualType = 'owerPay' THEN "+
			"CONCAT(project.payintentPeriod, '天') "+
			"END AS loanLife, "+
			
	     "pl.publishSingeTime as bidTime, " +
	     "pl.bidMoney as payMoney, " +
		 "IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate   "+
	  "FROM " +
		 "pl_bid_plan AS pl " ;

			
	sql=sql+"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
			"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
			"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
			"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)" +
			"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)"+
			"where  pl.state in (1,2,6)  ";
	if(loginname!=null&&!"".equals(loginname)){
		sql=sql+" and pl.receiverP2PAccountNumber='"+loginname+"'  ";
	}
	
	
	System.out.println("招标中="+sql);
	List list=null;
	Session session=this.getSession();
	if(null == start && null == limit){
		list = session.createSQLQuery(sql).
		addScalar("bidId",Hibernate.LONG).
		addScalar("proName",Hibernate.STRING).
		addScalar("authorizationStatus",Hibernate.STRING).
		addScalar("loanLife",Hibernate.STRING).
		addScalar("bidTime",Hibernate.DATE).
		addScalar("payMoney",Hibernate.BIG_DECIMAL).
		addScalar("interestRate",Hibernate.BIG_DECIMAL).
		setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
		list();
	} else {
		list = session.createSQLQuery(sql).
		addScalar("bidId",Hibernate.LONG).
		addScalar("proName",Hibernate.STRING).
		addScalar("authorizationStatus",Hibernate.STRING).
		addScalar("loanLife",Hibernate.STRING).
		addScalar("bidTime",Hibernate.DATE).
		addScalar("payMoney",Hibernate.BIG_DECIMAL).
		addScalar("interestRate",Hibernate.BIG_DECIMAL).
		setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
		setFirstResult(Integer.valueOf(start)).
		setMaxResults(Integer.valueOf(limit)).
		list();
	}
	releaseSession(session);
	return list;
	}

	 /**
     * 借款人已经标记为已结项（已完成的项目）
     * @param id
     * @param loginname
     * @param planId
     * @param loanType
     * @return
     */
	@Override
	public List<ShowManageMoney> findLoanFishRepayMentList(Long id,
			String loginname, Long planId, String loanType,HttpServletRequest request) {
		String start = null;
		String limit = null;
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}
		// TODO Auto-generated method stub
		String sql="SELECT " +
			 "pl.bidId as bidId, " +
		     "pl.bidProName as proName, " +
		     "pl.authorizationStatus as authorizationStatus, " +
		     "cont.url as url, " +
		     "p.loanerRepayMentStatus as loanerRepayMentStatus, " +
		     "CASE "+
				"WHEN project.payaccrualType = 'dayPay' THEN "+
				"CONCAT(project.payintentPeriod, '天') "+
				"WHEN project.payaccrualType = 'monthPay' THEN "+
				"CONCAT(project.payintentPeriod, '个月') "+
				"WHEN project.payaccrualType = 'seasonPay' THEN "+
				"CONCAT(project.payintentPeriod, '个季度') "+
				"WHEN project.payaccrualType = 'yearPay' THEN "+
				"CONCAT(project.payintentPeriod, '年') "+
				"WHEN project.payaccrualType = 'owerPay' THEN "+
				"CONCAT(project.payintentPeriod, '天') "+
				"END AS loanLife, "+
		     "pl.publishSingeTime as bidTime, " + 
		     "pl.bidMoney as payMoney, " +
		     "ppp.perid as repaymentDate, " +
			 "sum(p.afterMoney) as backmoney, " +
			 "sum(p.afterMoney+p.accrualMoney) as repaymentTotal, " +
			 "IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate  "+
		  "FROM " +
			 "bp_fund_intent AS p " ;

		sql=sql+ "inner JOIN ( " +
					"SELECT " +
					"pp.bidPlanId AS plt, " +
					"max(pp.requestDate) AS perid " +
				"FROM " +
					"bp_fund_intent AS pp " +
				"WHERE " +
					"pp.isCheck = 0 " +
				"AND pp.isValid = 0 " +
				"AND pp.fundType in ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty')"+
				"and (pp.loanerRepayMentStatus=1  or pp.notMoney = 0) "+
				"GROUP BY " +
					"pp.bidPlanId " +
			") AS ppp " +
		"on (p.bidPlanId = ppp.plt  ) ";
				
		sql=sql+"LEFT JOIN pl_bid_plan AS pl ON (pl.bidId = p.bidPlanId)" +
				" LEFT JOIN bp_fund_project AS project on (project.projectId=p.projectId) and project.flag=1 " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)" +
				"where p.fundType in ('serviceMoney','consultationMoney','loanInterest','principalRepayment','interestPenalty') and p.isCheck = 0 AND p.isValid = 0 AND pl.state=10 AND ppp.perid is not NULL ";
		
		if(loginname!=null&&!"".equals(loginname)){
			sql=sql+" and pl.receiverP2PAccountNumber='"+loginname+"'  ";
		}
		
		sql=sql+"GROUP BY " +
		"p.bidPlanId";
		
		System.out.println("已结清 ="+sql);
		List list=null;
		Session session=this.getSession();
		if(null == start && null == limit){
			list = session.createSQLQuery(sql). 
			  addScalar("bidId",Hibernate.LONG).
			  addScalar("proName",Hibernate.STRING).
			  addScalar("authorizationStatus",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("bidTime",Hibernate.DATE).
			  addScalar("payMoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentDate",Hibernate.DATE).
			  addScalar("backmoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
			  list();
		} else {
			list = session.createSQLQuery(sql). 
			  addScalar("bidId",Hibernate.LONG).
			  addScalar("proName",Hibernate.STRING).
			  addScalar("authorizationStatus",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("loanerRepayMentStatus",Hibernate.INTEGER).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("bidTime",Hibernate.DATE).
			  addScalar("payMoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentDate",Hibernate.DATE).
			  addScalar("backmoney",Hibernate.BIG_DECIMAL).
			  addScalar("repaymentTotal",Hibernate.BIG_DECIMAL).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).
			  setFirstResult(Integer.valueOf(start)).
			  setMaxResults(Integer.valueOf(limit)).
			  list();
		}
		return list;
	}
	/**
     * 尚未起息的投资记录
     * @param memId
     * @param loginname
     * @param planId
     * @param repaymentType
     * @return
     */
	@Override
	public List<FundPay> findInvestBeforeRepayMemtList(Long memId,
			String loginname, String planId, String repaymentType,HttpServletRequest request) {
		String start = null;
		String limit = null;
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}

			String sql="SELECT  "+ 
			"pl.bidId as planId,  " +
			"pl.state as state,  " +
			"pl.bidProName as projectName, " +
			"pl.showRate as showRate, " +
			"pl.novice as novice, " +
			"pl.proKeepType as proKeepType, " +
			"pl.bidMoney as bidMoney, " +
			"pl.bidEndTime as bidEndTime, " +
			"CASE "+
			"WHEN project.payaccrualType = 'dayPay' THEN "+
			"CONCAT(project.payintentPeriod, '天') "+
			"WHEN project.payaccrualType = 'monthPay' THEN "+
			"CONCAT(project.payintentPeriod, '个月') "+
			"WHEN project.payaccrualType = 'seasonPay' THEN "+
			"CONCAT(project.payintentPeriod, '个季度') "+
			"WHEN project.payaccrualType = 'yearPay' THEN "+
			"CONCAT(project.payintentPeriod, '年') "+
			"WHEN project.payaccrualType = 'owerPay' THEN "+
			"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
			"END AS loanLife, "+
			"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
			"p.bidtime as bidtime, " +
			"p.userMoney as userMoney, "+
			"p.orderNo  as orderNo " +
			"FROM  pl_bid_info AS p  " +
			"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
			"left join pl_bid_plan as pl on (p.bidId=pl.bidId) " +
			"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
			"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
			"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
			"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
			"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)" +
			"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";
		
			if(repaymentType.equals("InvestRepayMemtBeforeFaild")){
				sql=sql+" WHERE p.state=3 ";
			}else{
				sql=sql+" WHERE p.state in (1,2) and pl.state in (1,2,6) ";
			}
	
			if(memId!=null){
			sql=sql+" and ((p.newInvestPersonId="+memId+") or ( p.newInvestPersonId is null and p.userId="+memId+"))";
			}
			sql=sql+"  order BY  p.bidtime desc";
			System.out.println("尚未起息==="+sql);
			
			List list = null;
			Session session = this.getSession();
			if(null == start && null == limit){
				list = session.createSQLQuery(sql). 
				  addScalar("planId",Hibernate.LONG).
				  addScalar("projectName",Hibernate.STRING).
				  addScalar("state",Hibernate.INTEGER).
				  addScalar("showRate",Hibernate.BIG_DECIMAL).
				  addScalar("novice",Hibernate.INTEGER).
				  addScalar("proKeepType",Hibernate.STRING).
				  addScalar("bidMoney",Hibernate.BIG_DECIMAL).
				  addScalar("bidEndTime",Hibernate.TIMESTAMP).
				  addScalar("loanLife",Hibernate.STRING).
				  addScalar("interestRate",Hibernate.BIG_DECIMAL).
				  addScalar("bidtime",Hibernate.DATE).
				  addScalar("userMoney",Hibernate.BIG_DECIMAL).
				  addScalar("orderNo",Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
				  list();
			} else {
				list = session.createSQLQuery(sql). 
				  addScalar("planId",Hibernate.LONG).
				  addScalar("projectName",Hibernate.STRING).
				  addScalar("state",Hibernate.INTEGER).
				  addScalar("showRate",Hibernate.BIG_DECIMAL).
				  addScalar("novice",Hibernate.INTEGER).
				  addScalar("proKeepType",Hibernate.STRING).
				  addScalar("bidMoney",Hibernate.BIG_DECIMAL).
				  addScalar("bidEndTime",Hibernate.TIMESTAMP).
				  addScalar("loanLife",Hibernate.STRING).
				  addScalar("interestRate",Hibernate.BIG_DECIMAL).
				  addScalar("bidtime",Hibernate.DATE).
				  addScalar("userMoney",Hibernate.BIG_DECIMAL).
				  addScalar("orderNo",Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
				  setFirstResult(Integer.valueOf(start)).
				  setMaxResults(Integer.valueOf(limit)).
				  list();
			}
			releaseSession(session);
			return list;
	}

	/**
	 * 尚未起息的投资记录
	 * @param memId
	 * @param loginname
	 * @param planId
	 * @param repaymentType
	 * @return
	 */
	@Override
	public List<FundPay> findInvestBeforeRepayMemt(Long memId,
													   String loginname, String planId, String repaymentType,HttpServletRequest request) {
		Integer start = null;
		Integer limit = null;
		if(null != request && !"".equals(request)){
			start =Integer.valueOf(request.getParameter("start")) ;
			limit =Integer.valueOf(request.getParameter("limit"));
			start=(start-1)*limit;
		}


		String sql="SELECT  "+
				"pl.bidId as planId,  " +
				"pl.state as state,  " +
				"pl.bidProName as projectName, " +
				"pl.showRate as showRate, " +
				"pl.novice as novice, " +
				"pl.proKeepType as proKeepType, " +
				"pl.bidMoney as bidMoney, " +
				"pl.bidEndTime as bidEndTime, " +
				"CASE "+
				"WHEN project.payaccrualType = 'dayPay' THEN "+
				"CONCAT(project.payintentPeriod, '天') "+
				"WHEN project.payaccrualType = 'monthPay' THEN "+
				"CONCAT(project.payintentPeriod, '个月') "+
				"WHEN project.payaccrualType = 'seasonPay' THEN "+
				"CONCAT(project.payintentPeriod, '个季度') "+
				"WHEN project.payaccrualType = 'yearPay' THEN "+
				"CONCAT(project.payintentPeriod, '年') "+
				"WHEN project.payaccrualType = 'owerPay' THEN "+
				"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
				"END AS loanLife, "+
				"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
				"p.bidtime as bidtime, " +
				"p.userMoney as userMoney, "+
				"p.orderNo  as orderNo " +
				"FROM  pl_bid_info AS p  " +
				"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
				"left join pl_bid_plan as pl on (p.bidId=pl.bidId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)" +
				"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";

		if(repaymentType.equals("InvestRepayMemtBeforeFaild")){
			sql=sql+" WHERE p.state=3 ";
		}else{
			sql=sql+" WHERE p.state in (1,2) and pl.state in (1,2,6) ";
		}

		if(memId!=null){
			sql=sql+" and ((p.newInvestPersonId="+memId+") or ( p.newInvestPersonId is null and p.userId="+memId+"))";
		}
		sql=sql+"  order BY  p.bidtime desc";
		System.out.println("尚未起息==="+sql);

		List list = null;
		Session session = this.getSession();
		if(null == start && null == limit){
			list = session.createSQLQuery(sql).
					addScalar("planId",Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("bidMoney",Hibernate.BIG_DECIMAL).
					addScalar("bidEndTime",Hibernate.TIMESTAMP).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("orderNo",Hibernate.STRING).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					list();
		} else {
			list = session.createSQLQuery(sql).
					addScalar("planId",Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("bidMoney",Hibernate.BIG_DECIMAL).
					addScalar("bidEndTime",Hibernate.TIMESTAMP).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("orderNo",Hibernate.STRING).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					setFirstResult(start).
					setMaxResults(limit).
					list();
		}
		releaseSession(session);
		return list;
	}

	/**
	 *根据ID查询募集期利润已经还过多少
	 *
	 * @auther: XinFang
	 * @date: 2018/8/7 9:39
	 */
	@Override
	public BigDecimal getByRaiseinterest(Long id) {
		String sql = "SELECT SUM(afterMoney) from bp_fund_intent where fundType = 'raiseinterest' and notMoney =0 and investPersonId = "+id;
		BigDecimal money= (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
		if (money == null) {
			money = new BigDecimal(0);
		}
		return money;
	}

	/**
     * 已经结项的投资人的投资记录
     * @param memId
     * @param loginname
     * @param planId
     * @param repaymentType
     * @return
     */
	@Override
	public List<FundPay> findInvestFinishRepayMemtList(Long memId,
			String loginname, String orderNo, String repaymentType,HttpServletRequest request) {

		String start = null;
		String limit = null;
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}
		String sql="SELECT  "+ 
						"p.bidPlanId as planId,  " +
						"pl.bidProName as projectName, " +
				        "pl.state as state, " +
						"pl.novice as novice, " +
						"pl.showRate as showRate, " +
						"pl.proKeepType as proKeepType, " +
						"CASE "+
						"WHEN project.payaccrualType = 'dayPay' THEN "+
						"CONCAT(project.payintentPeriod, '天') "+
						"WHEN project.payaccrualType = 'monthPay' THEN "+
						"CONCAT(project.payintentPeriod, '个月') "+
						"WHEN project.payaccrualType = 'seasonPay' THEN "+
						"CONCAT(project.payintentPeriod, '个季度') "+
						"WHEN project.payaccrualType = 'yearPay' THEN "+
						"CONCAT(project.payintentPeriod, '年') "+
						"WHEN project.payaccrualType = 'owerPay' THEN "+
						"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
						"END AS loanLife, "+
						"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
						"info.bidtime as bidtime, " +
						"info.userMoney as userMoney, "+
						"p.payintentPeriod as payintentPeriod,  "+ 
						"p.intentDate as intentDate,  "+ 
						"p.factDate as factDate,  " +
						"p.orderNo  as orderNo," +
						"invest.contractUrls as url, "+ 
						"sum(CASE WHEN (p.fundType='loanInterest' OR p.fundType='principalRepayment') " +
						"THEN p.incomeMoney ELSE 0.00 END) AS 'payIntestPrinMoney', "+ 
						"sum(CASE WHEN (p.fundType!='loanInterest' AND p.fundType != 'principalRepayment' AND p.fundType != 'interestPenalty') " +
						"THEN  p.incomeMoney  ELSE 0.00 END ) AS 'couponMoney',  "+ 
						"sum(CASE WHEN (p.fundType='loanInterest' or p.fundType = 'principalRepayment' ) THEN  p.accrualMoney   " +
						"when (p.fundType='interestPenalty') then p.incomeMoney  ELSE 0.00 end) AS 'compensationMoney', " +
						"sum(case when p.afterMoney>0 then p.notMoney else (p.notMoney+p.accrualMoney) end) AS planPaymentsMoney, "+ //未支付总金额
						"sum(case when p.afterMoney>0 then (p.afterMoney+p.accrualMoney) else p.afterMoney end ) AS 'factPaymentsMoney' , "; //实际回款金额
		
						
		
		if(repaymentType.equals("InvestRepayMemtFinish")){
			sql=sql+" ppp.perid as finishDate FROM  bp_fund_intent AS p  INNER JOIN (SELECT  pp.bidPlanId AS plt, max(pp.factDate) AS perid "+
					"FROM bp_fund_intent AS pp "+
					"WHERE pp.isCheck = 0 AND pp.isValid = 0 AND pp.notMoney = 0 " +
					"AND pp.fundType not in ('serviceMoney','consultationMoney','principalLending')"+
					"GROUP BY pp.bidPlanId ) AS ppp "+
				    "on (p.bidPlanId = ppp.plt )";
		}else{
			sql=sql+"null as finishDate FROM  bp_fund_intent AS p  " ;
		}
		
		sql=sql+"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
				"left join pl_bid_info as info on(info.orderNo = p.orderNo) " +
				"left join pl_bid_plan as pl on (p.bidPlanId=pl.bidId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)"+
				"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";
		
		
		sql=sql+" WHERE p.fundType NOT IN ('principalLending', 'consultationMoney','serviceMoney') AND p.isCheck = 0  AND p.isValid = 0 AND pl.state=10 ";
		
		
		if(memId!=null){
			sql=sql+" and ((info.newInvestPersonId="+memId+") or ( info.newInvestPersonId is null and info.userId="+memId+"))";
		}
		sql=sql+"  GROUP BY  p.orderNo ORDER BY ppp.perid DESC";
		System.out.println("sql:"+sql);
		List list = null;
		Session session = this.getSession();
		if(start ==null && limit == null){
			list = session.createSQLQuery(sql). 
			  addScalar("planId",Hibernate.LONG).
			  addScalar("projectName",Hibernate.STRING).
			  addScalar("state",Hibernate.INTEGER).
			  addScalar("novice",Hibernate.INTEGER).
			  addScalar("showRate",Hibernate.BIG_DECIMAL).
			  addScalar("proKeepType",Hibernate.STRING).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  addScalar("bidtime",Hibernate.DATE).
			  addScalar("userMoney",Hibernate.BIG_DECIMAL).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("orderNo",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
			  addScalar("couponMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("finishDate",Hibernate.DATE).
			  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
			  list();
		} else {
			list = session.createSQLQuery(sql). 
			  addScalar("planId",Hibernate.LONG).
			  addScalar("projectName",Hibernate.STRING).
			  addScalar("state",Hibernate.INTEGER).
			  addScalar("novice",Hibernate.INTEGER).
			  addScalar("showRate",Hibernate.BIG_DECIMAL).
			  addScalar("proKeepType",Hibernate.STRING).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  addScalar("bidtime",Hibernate.DATE).
			  addScalar("userMoney",Hibernate.BIG_DECIMAL).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("orderNo",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
			  addScalar("couponMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("finishDate",Hibernate.DATE).
			  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
			  setFirstResult(Integer.valueOf(start)).
			  setMaxResults(Integer.valueOf(limit)).
			  list();
		}
		releaseSession(session);
		return list;
	}

	/**
	 * 已经结项的投资人的投资记录
	 * @param memId
	 * @param loginname
	 * @param planId
	 * @param repaymentType
	 * @return
	 */
	@Override
	public List<FundPay> findInvestFinishRepayMemt(Long memId,
													   String loginname, String orderNo, String repaymentType,HttpServletRequest request) {

		Integer start = null;
		Integer limit = null;
		if(null != request && !"".equals(request)){
			start =Integer.valueOf(request.getParameter("start")) ;
			limit =Integer.valueOf(request.getParameter("limit"));
			start=(start-1)*limit;
		}

		String sql="SELECT  "+
				"p.bidPlanId as planId,  " +
				"pl.bidProName as projectName, " +
				"pl.state as state, " +
				"pl.novice as novice, " +
				"pl.showRate as showRate, " +
				"pl.proKeepType as proKeepType, " +
				"CASE "+
				"WHEN project.payaccrualType = 'dayPay' THEN "+
				"CONCAT(project.payintentPeriod, '天') "+
				"WHEN project.payaccrualType = 'monthPay' THEN "+
				"CONCAT(project.payintentPeriod, '个月') "+
				"WHEN project.payaccrualType = 'seasonPay' THEN "+
				"CONCAT(project.payintentPeriod, '个季度') "+
				"WHEN project.payaccrualType = 'yearPay' THEN "+
				"CONCAT(project.payintentPeriod, '年') "+
				"WHEN project.payaccrualType = 'owerPay' THEN "+
				"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
				"END AS loanLife, "+
				"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
				"info.bidtime as bidtime, " +
				"info.userMoney as userMoney, "+
				"p.payintentPeriod as payintentPeriod,  "+
				"p.intentDate as intentDate,  "+
				"p.factDate as factDate,  " +
				"p.orderNo  as orderNo," +
				"invest.contractUrls as url, "+
				"sum(CASE WHEN (p.fundType='loanInterest' OR p.fundType='principalRepayment') " +
				"THEN p.incomeMoney ELSE 0.00 END) AS 'payIntestPrinMoney', "+
				"sum(CASE WHEN (p.fundType!='loanInterest' AND p.fundType != 'principalRepayment' AND p.fundType != 'interestPenalty') " +
				"THEN  p.incomeMoney  ELSE 0.00 END ) AS 'couponMoney',  "+
				"sum(CASE WHEN (p.fundType='loanInterest' or p.fundType = 'principalRepayment' ) THEN  p.accrualMoney   " +
				"when (p.fundType='interestPenalty') then p.incomeMoney  ELSE 0.00 end) AS 'compensationMoney', " +
				"sum(case when p.afterMoney>0 then p.notMoney else (p.notMoney+p.accrualMoney) end) AS planPaymentsMoney, "+ //未支付总金额
				"sum(case when p.afterMoney>0 then (p.afterMoney+p.accrualMoney) else p.afterMoney end ) AS 'factPaymentsMoney' , "; //实际回款金额



		if(repaymentType.equals("InvestRepayMemtFinish")){
			sql=sql+" ppp.perid as finishDate FROM  bp_fund_intent AS p  INNER JOIN (SELECT  pp.bidPlanId AS plt, max(pp.factDate) AS perid "+
					"FROM bp_fund_intent AS pp "+
					"WHERE pp.isCheck = 0 AND pp.isValid = 0 AND pp.notMoney = 0 " +
					"AND pp.fundType not in ('serviceMoney','consultationMoney','principalLending')"+
					"GROUP BY pp.bidPlanId ) AS ppp "+
					"on (p.bidPlanId = ppp.plt )";
		}else{
			sql=sql+"null as finishDate FROM  bp_fund_intent AS p  " ;
		}

		sql=sql+"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
				"left join pl_bid_info as info on(info.orderNo = p.orderNo) " +
				"left join pl_bid_plan as pl on (p.bidPlanId=pl.bidId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)"+
				"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";


		sql=sql+" WHERE p.fundType NOT IN ('principalLending', 'consultationMoney','serviceMoney') AND p.isCheck = 0  AND p.isValid = 0 AND pl.state=10 ";


		if(memId!=null){
			sql=sql+" and ((info.newInvestPersonId="+memId+") or ( info.newInvestPersonId is null and info.userId="+memId+"))";
		}
		sql=sql+"  GROUP BY  p.orderNo ORDER BY ppp.perid DESC";
		System.out.println("sql:"+sql);
		List list = null;
		Session session = this.getSession();
		if(start ==null && limit == null){
			list = session.createSQLQuery(sql).
					addScalar("planId",Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("payintentPeriod",Hibernate.INTEGER).
					addScalar("intentDate",Hibernate.DATE).
					addScalar("factDate",Hibernate.DATE).
					addScalar("orderNo",Hibernate.STRING).
					addScalar("url",Hibernate.STRING).
					addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
					addScalar("couponMoney",Hibernate.BIG_DECIMAL).
					addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
					addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("finishDate",Hibernate.DATE).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					list();
		} else {
			list = session.createSQLQuery(sql).
					addScalar("planId",Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("payintentPeriod",Hibernate.INTEGER).
					addScalar("intentDate",Hibernate.DATE).
					addScalar("factDate",Hibernate.DATE).
					addScalar("orderNo",Hibernate.STRING).
					addScalar("url",Hibernate.STRING).
					addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
					addScalar("couponMoney",Hibernate.BIG_DECIMAL).
					addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
					addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("finishDate",Hibernate.DATE).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					setFirstResult(start).
					setMaxResults(limit).
					list();
		}
		releaseSession(session);
		return list;
	}

	/**
	 * 投资人款项台账
	 * @param memId
	 * @param loginname
	 * @param planId
	 * @param repaymentType
	 * @return
	 */
	@Override
	public List<FundPay> findInvestRepayMemtList(Long memId, String loginname,
			String orderNo, String repaymentType,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String start = null;
		String limit = null;
		if(null != request && !"".equals(request)){
			start = request.getParameter("start");
			limit = request.getParameter("limit");
		}
		
		String sql="SELECT  "+ 
						"p.bidPlanId as planId,  " +
						"pl.bidProName as projectName, " +
						"pl.state as state, "+
						"pl.showRate as showRate, " +
						"pl.novice as novice, " +
						"pl.proKeepType as proKeepType, " +
						"CASE "+
						"WHEN project.payaccrualType = 'dayPay' THEN "+
						"CONCAT(project.payintentPeriod, '天') "+
						"WHEN project.payaccrualType = 'monthPay' THEN "+
						"CONCAT(project.payintentPeriod, '个月') "+
						"WHEN project.payaccrualType = 'seasonPay' THEN "+
						"CONCAT(project.payintentPeriod, '个季度') "+
						"WHEN project.payaccrualType = 'yearPay' THEN "+
						"CONCAT(project.payintentPeriod, '年') "+
						"WHEN project.payaccrualType = 'owerPay' THEN "+
						"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
						"END AS loanLife, "+
						"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
						"info.bidtime as bidtime, " +
						"info.userMoney as userMoney, "+
						"p.payintentPeriod as payintentPeriod,  "+ 
						"p.intentDate as intentDate,  "+ 
						"p.factDate as factDate,  " +
						"p.orderNo  as orderNo," +
						"invest.contractUrls as url, "+ 
						"sum(CASE WHEN (p.fundType='loanInterest' OR p.fundType='principalRepayment') " +
						"THEN p.incomeMoney ELSE 0.00 END) AS 'payIntestPrinMoney', "+ //计划本息金额
						"sum(CASE WHEN (p.fundType!='loanInterest' AND p.fundType != 'principalRepayment' AND p.fundType != 'interestPenalty') " +
						"THEN  p.incomeMoney  ELSE 0.00 END ) AS 'couponMoney',  "+ //奖励金额
						"sum(CASE WHEN (p.fundType='loanInterest' or p.fundType = 'principalRepayment' ) THEN  p.accrualMoney   " +
						" when (p.fundType='interestPenalty') then p.incomeMoney  ELSE 0.00 end) AS 'compensationMoney', " +//计划补偿息和罚息
						"sum(case when p.afterMoney>0 then p.notMoney else (p.notMoney+p.accrualMoney) end) AS planPaymentsMoney, "+ //未支付总金额
						"sum(case when p.afterMoney>0 then (p.afterMoney+p.accrualMoney) else p.afterMoney end ) AS 'factPaymentsMoney'  "+ //实际回款金额
					"FROM  bp_fund_intent AS p  " ;
		
		if(repaymentType.equals("InvestRepayment")){
			sql=sql+"INNER JOIN (SELECT  pp.bidPlanId AS plt, min(pp.intentDate) AS perid "+
					"FROM bp_fund_intent AS pp "+
					"WHERE pp.isCheck = 0 AND pp.isValid = 0 AND pp.notMoney > 0 " +
					"AND pp.fundType not in ('serviceMoney','consultationMoney','principalLending','raiseinterest')"+
					"GROUP BY pp.bidPlanId ) AS ppp "+
				    "on (p.bidPlanId = ppp.plt AND p.intentDate = ppp.perid)";
		}
		
		sql=sql+"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
				"left join pl_bid_info as info on(info.orderNo = p.orderNo) " +
				"left join pl_bid_plan as pl on (p.bidPlanId=pl.bidId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)"+
				"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";
		
		sql=sql+" WHERE p.fundType NOT IN ('principalLending', 'consultationMoney','serviceMoney') AND p.isCheck = 0  AND p.isValid = 0 ";
		
		if(orderNo!=null&&!"".equals(orderNo)){
			sql=sql+" and p.orderNo='"+orderNo+"' ";
		}
		if(memId!=null){
			sql=sql+" and ((info.newInvestPersonId="+memId+") or ( info.newInvestPersonId is  null and info.userId="+memId+"))";
		}
		sql=sql+"  GROUP BY  p.orderNo, p.intentDate  order by info.bidtime desc,p.intentDate ";
		System.out.println("sql》》》》》》"+sql);
		List list=null;
		Session session=this.getSession();
		if(null == start && null == limit){
			list = session.createSQLQuery(sql).
			  addScalar("planId", Hibernate.LONG).
			  addScalar("projectName",Hibernate.STRING).
			  addScalar("state",Hibernate.INTEGER).
			  addScalar("showRate",Hibernate.BIG_DECIMAL).
			  addScalar("novice",Hibernate.INTEGER).
			  addScalar("proKeepType",Hibernate.STRING).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  addScalar("bidtime",Hibernate.DATE).
			  addScalar("userMoney",Hibernate.BIG_DECIMAL).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("orderNo",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
			  addScalar("couponMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
			  list();
		} else {
			list = session.createSQLQuery(sql). 
			  addScalar("planId",Hibernate.LONG).
			  addScalar("projectName",Hibernate.STRING).
			  addScalar("state",Hibernate.INTEGER).
			  addScalar("showRate",Hibernate.BIG_DECIMAL).
			  addScalar("novice",Hibernate.INTEGER).
			  addScalar("proKeepType",Hibernate.STRING).
			  addScalar("loanLife",Hibernate.STRING).
			  addScalar("interestRate",Hibernate.BIG_DECIMAL).
			  addScalar("bidtime",Hibernate.DATE).
			  addScalar("userMoney",Hibernate.BIG_DECIMAL).
			  addScalar("payintentPeriod",Hibernate.INTEGER).
			  addScalar("intentDate",Hibernate.DATE).
			  addScalar("factDate",Hibernate.DATE).
			  addScalar("orderNo",Hibernate.STRING).
			  addScalar("url",Hibernate.STRING).
			  addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
			  addScalar("couponMoney",Hibernate.BIG_DECIMAL).
			  addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
			  addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
			  addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
			  setResultTransformer(Transformers.aliasToBean(FundPay.class)).
			  setFirstResult(Integer.valueOf(start)).
			  setMaxResults(Integer.valueOf(limit)).
			  list();
		}
		releaseSession(session);
		return list;
	}

	/**
	 * 投资人款项台账
	 * @param memId
	 * @param loginname
	 * @param planId
	 * @param repaymentType
	 * @return
	 */
	@Override
	public List<FundPay> findInvestRepayMemt(Long memId, String loginname,
												 String orderNo, String repaymentType,HttpServletRequest request) {
		Integer start = null;
		Integer limit = null;
		if(null != request && !"".equals(request)){
			start =Integer.valueOf(request.getParameter("start")) ;
			limit =Integer.valueOf(request.getParameter("limit"));
			start=(start-1)*limit;
		}


		String sql="SELECT  "+
				"p.bidPlanId as planId,  " +
				"pl.bidProName as projectName, " +
				"pl.state as state, "+
				"pl.showRate as showRate, " +
				"pl.novice as novice, " +
				"pl.proKeepType as proKeepType, " +
				"CASE "+
				"WHEN project.payaccrualType = 'dayPay' THEN "+
				"CONCAT(project.payintentPeriod, '天') "+
				"WHEN project.payaccrualType = 'monthPay' THEN "+
				"CONCAT(project.payintentPeriod, '个月') "+
				"WHEN project.payaccrualType = 'seasonPay' THEN "+
				"CONCAT(project.payintentPeriod, '个季度') "+
				"WHEN project.payaccrualType = 'yearPay' THEN "+
				"CONCAT(project.payintentPeriod, '年') "+
				"WHEN project.payaccrualType = 'owerPay' THEN "+
				"CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天') "+
				"END AS loanLife, "+
				"IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate, " +
				"info.bidtime as bidtime, " +
				"info.userMoney as userMoney, "+
				"p.payintentPeriod as payintentPeriod,  "+
				"p.intentDate as intentDate,  "+
				"p.factDate as factDate,  " +
				"p.orderNo  as orderNo," +
				"invest.contractUrls as url, "+
				"sum(CASE WHEN (p.fundType='loanInterest' OR p.fundType='principalRepayment') " +
				"THEN p.incomeMoney ELSE 0.00 END) AS 'payIntestPrinMoney', "+ //计划本息金额
				"sum(CASE WHEN (p.fundType!='loanInterest' AND p.fundType != 'principalRepayment' AND p.fundType != 'interestPenalty') " +
				"THEN  p.incomeMoney  ELSE 0.00 END ) AS 'couponMoney',  "+ //奖励金额
				"sum(CASE WHEN (p.fundType='loanInterest' or p.fundType = 'principalRepayment' ) THEN  p.accrualMoney   " +
				" when (p.fundType='interestPenalty') then p.incomeMoney  ELSE 0.00 end) AS 'compensationMoney', " +//计划补偿息和罚息
				"sum(case when p.afterMoney>0 then p.notMoney else (p.notMoney+p.accrualMoney) end) AS planPaymentsMoney, "+ //未支付总金额
				"sum(case when p.afterMoney>0 then (p.afterMoney+p.accrualMoney) else p.afterMoney end ) AS 'factPaymentsMoney'  "+ //实际回款金额
				"FROM  bp_fund_intent AS p  " ;

		if(repaymentType.equals("InvestRepayment")){
			sql=sql+"INNER JOIN (SELECT  pp.bidPlanId AS plt, min(pp.intentDate) AS perid "+
					"FROM bp_fund_intent AS pp "+
					"WHERE pp.isCheck = 0 AND pp.isValid = 0 AND pp.notMoney > 0 " +
					"AND pp.fundType not in ('serviceMoney','consultationMoney','principalLending','raiseinterest')"+
					"GROUP BY pp.bidPlanId ) AS ppp "+
					"on (p.bidPlanId = ppp.plt AND p.intentDate = ppp.perid)";
		}

		sql=sql+"left join invest_person_info as invest on (invest.orderNo=p.orderNo) " +
				"left join pl_bid_info as info on(info.orderNo = p.orderNo) " +
				"left join pl_bid_plan as pl on (p.bidPlanId=pl.bidId) " +
				"left join cs_procredit_contract as cont on (pl.bidId=cont.bidPlanId ) " +
				"LEFT JOIN bp_business_dir_pro AS bdir ON (pl.proType='B_Dir' and pl.bDirProId=bdir.bdirProId)"+
				"left join bp_persion_dir_pro as pdir on (pl.proType='P_Dir' and pl.pDirProId=pdir.pdirProId)"+
				"left join bp_business_or_pro as bor on (pl.proType='B_Or' and pl.borProId=bor.borProId)"+
				"left join bp_persion_or_pro as por on (pl.proType='P_Or' and pl.pOrProId=por.porProId)"+
				"left join bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)";

		sql=sql+" WHERE p.fundType NOT IN ('principalLending', 'consultationMoney','serviceMoney') AND p.isCheck = 0  AND p.isValid = 0 ";

		if(orderNo!=null&&!"".equals(orderNo)){
			sql=sql+" and p.orderNo='"+orderNo+"' ";
		}
		if(memId!=null){
			sql=sql+" and ((info.newInvestPersonId="+memId+") or ( info.newInvestPersonId is  null and info.userId="+memId+"))";
		}
		sql=sql+"  GROUP BY  p.orderNo, p.intentDate  order by info.bidtime desc,p.intentDate ";
		System.out.println("sql》》》》》》"+sql);
		List list=null;
		Session session=this.getSession();
		if(null == start && null == limit){
			list = session.createSQLQuery(sql).
					addScalar("planId", Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("payintentPeriod",Hibernate.INTEGER).
					addScalar("intentDate",Hibernate.DATE).
					addScalar("factDate",Hibernate.DATE).
					addScalar("orderNo",Hibernate.STRING).
					addScalar("url",Hibernate.STRING).
					addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
					addScalar("couponMoney",Hibernate.BIG_DECIMAL).
					addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
					addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					list();
		} else {
			list = session.createSQLQuery(sql).
					addScalar("planId",Hibernate.LONG).
					addScalar("projectName",Hibernate.STRING).
					addScalar("state",Hibernate.INTEGER).
					addScalar("showRate",Hibernate.BIG_DECIMAL).
					addScalar("novice",Hibernate.INTEGER).
					addScalar("proKeepType",Hibernate.STRING).
					addScalar("loanLife",Hibernate.STRING).
					addScalar("interestRate",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("payintentPeriod",Hibernate.INTEGER).
					addScalar("intentDate",Hibernate.DATE).
					addScalar("factDate",Hibernate.DATE).
					addScalar("orderNo",Hibernate.STRING).
					addScalar("url",Hibernate.STRING).
					addScalar("payIntestPrinMoney",Hibernate.BIG_DECIMAL).
					addScalar("couponMoney",Hibernate.BIG_DECIMAL).
					addScalar("compensationMoney",Hibernate.BIG_DECIMAL).
					addScalar("planPaymentsMoney",Hibernate.BIG_DECIMAL).
					addScalar("factPaymentsMoney",Hibernate.BIG_DECIMAL).
					setResultTransformer(Transformers.aliasToBean(FundPay.class)).
					setFirstResult(start).
					setMaxResults(limit).
					list();
		}
		releaseSession(session);
		return list;
	}


	@Override
	public List<BpFundIntent> findByPeriodAndBidId(String bidId, String periodId) {
		// TODO Auto-generated method stub
		String sql = "select * from bp_fund_intent intent where intent.bidPlanId = ? and intent.payintentPeriod = ? and intent.isCheck = 0 and intent.factDate == null";
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).setParameter(1, Long.valueOf(periodId)).list();
	}

	@Override
	public BigDecimal getByPlanId(String bidId,String custId) {
		// TODO Auto-generated method stub
		String sql = "select sum(intent.afterMoney) from bp_fund_intent intent where intent.bidPlanId=? and intent.isCheck=0 and intent.factDate is not null and intent.investPersonId=? and intent.fundType in ('principalRepayment')";//  and intent.fundType in ('principalRepayment','loanInterest','','')
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, Long.valueOf(bidId))
		.setParameter(1, Long.valueOf(custId)).uniqueResult();

	}


	@Override
	public List<BpFundIntent> getByPlanId(String bidId) {
		// TODO Auto-generated method stub
		String hql = "from BpFundIntent intent where intent.bidPlanId='"+Long.valueOf(bidId)+"' and intent.isCheck=0  and intent.fundType in ('principalRepayment') and intent.factDate is null";//  and intent.fundType in ('principalRepayment','loanInterest','','')
		return this.getSession().createQuery(hql).list();

	}

	
	
	
	
	@Override
	public List<ShowManageMoney> listByGuarantorsId(Long guarantorsId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ShowManageMoney> list = new ArrayList<ShowManageMoney>();
	//	PlBidPlan plan = plBidPlanDao.getByGuarantor(guarantorsId);
		StringBuffer hql=new StringBuffer("select *" +
				" from  ( SELECT "
				+" b.notMoney,"
				+" b.bidPlanId as bidId,"
				+" p.bidProName as proName,"
				+" p.receiverName as receiverName ,"
				+" b.payintentPeriod,"
				+" sum(case when b.fundType='loanInterest' then b.notMoney else 0.00 end) as loanInterest,"
				+" sum(case when b.fundType='principalRepayment' then b.notMoney else 0.00 end) as principal,"
				+" sum(case when b.fundType='serviceMoney' or b.fundType='consultationMoney'  then b.notMoney else 0.00 end) as serviceMoney,"
				+" b.punishDays,"
				+" IFNULL(SUM(b.accrualMoney),0) AS accMoney,"
				+" b.intentDate,"
				+" b.orderNo"
				+" FROM bp_fund_intent  AS b "
				+" LEFT JOIN pl_bid_plan p ON p.bidId = b.bidPlanId"
				+" LEFT JOIN bp_cust_relation r ON p.guarantorsId = r.offlineCusId"
				+" AND r.offlineCustType = 'b_guarantee'"
				+" LEFT JOIN bp_cust_member m ON m.id = r.p2pCustId"
				+" WHERE 1=1 "
				+" AND b.payintentPeriod != 0"
				+" and if(p.guaranteeWay = '1',b.fundType = 'principalRepayment',if(p.guaranteeWay = '3',p.guaranteeWay = '3',IF(p.guaranteeWay = '2',b.fundType in ('principalRepayment','loanInterest'),''))) "
			//	+" AND b.bidPlanId =6045 "
				+" AND b.isValid = 0"
				+" AND b.isCheck = 0"
				+" AND b.fundType != 'principalLending'"
				+" AND b.intentDate < DATE_FORMAT("
				+" DATE_ADD(NOW(), INTERVAL 1 DAY),"
				+" '%Y-%m-%d'"
				+" )"
				+" and m.id="+guarantorsId
				+" AND b.notMoney > 0 "
				+" GROUP BY"
				+"  b.bidPlanId,"
				+" 	b.payintentPeriod"
				+"  ORDER BY b.payintentPeriod asc ) as s where s.notMoney>0  "
		         );
	  
		 String projectName=request.getParameter("name");
		 if(null!=projectName && !"".equals(projectName)){
				hql.append(" and s.proName like '%"+projectName+"%'");
		 }
		 String overDays=request.getParameter("overDays");
		 if(null!=overDays && !"".equals(overDays)){
			  if(Integer.valueOf(overDays)>15){
				  hql.append(" and s.punishDays > "+Integer.valueOf(overDays));
			  }else{
				  hql.append(" and s.punishDays < "+Integer.valueOf(overDays));
			  }
		 }
		 String ostartDate=request.getParameter("startDate");
		 if(null!=ostartDate && !"".equals(ostartDate)){
			 hql.append(" and s.intentDate >='"+ostartDate+"'");
		 }
		 String oendDate=request.getParameter("endDate");
		 if(null!=oendDate && !"".equals(oendDate)){
			 hql.append(" and s.intentDate <='"+oendDate+"'");
		 }
		 hql.append(" GROUP BY s.bidId");
		 System.out.println(">>>"+hql);
		 // 	System.out.println("-->"+hql.toString());
		 if(request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			 Integer start = Integer.valueOf(request.getParameter("start").toString());
			 Integer limit = Integer.valueOf(request.getParameter("limit").toString());
			 list = this.getSession().createSQLQuery(hql.toString())
				.addScalar("bidId", Hibernate.LONG)
				.addScalar("proName", Hibernate.STRING)
				.addScalar("receiverName", Hibernate.STRING)
				.addScalar("loanInterest", Hibernate.BIG_DECIMAL)
				.addScalar("principal", Hibernate.BIG_DECIMAL)
				.addScalar("serviceMoney", Hibernate.BIG_DECIMAL)
				.addScalar("accMoney", Hibernate.BIG_DECIMAL)
				.addScalar("punishDays", Hibernate.INTEGER)
				.addScalar("payintentPeriod", Hibernate.INTEGER)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
		        .setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).setFirstResult(start).setMaxResults(limit).list();
		 }else{
			 list = this.getSession().createSQLQuery(hql.toString())
				.addScalar("bidId", Hibernate.LONG)
				.addScalar("proName", Hibernate.STRING)
				.addScalar("receiverName", Hibernate.STRING)
				.addScalar("loanInterest", Hibernate.BIG_DECIMAL)
				.addScalar("principal", Hibernate.BIG_DECIMAL)
				.addScalar("serviceMoney", Hibernate.BIG_DECIMAL)
				.addScalar("accMoney", Hibernate.BIG_DECIMAL)
				.addScalar("punishDays", Hibernate.INTEGER)
				.addScalar("payintentPeriod", Hibernate.INTEGER)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
		        .setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).list();
			 
		 }
			return list;
	}
	@Override
	public List<ShowManageMoney> listByGuarantorsId1(Long guarantorsId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<ShowManageMoney> list = new ArrayList<ShowManageMoney>();
		StringBuffer hql=new StringBuffer("select * from  ( SELECT "
				+" b.bidPlanId as bidId,"
				+" p.bidProName as proName,"
				+" p.receiverName as receiverName ,"
				+" b.payintentPeriod,"
				+" sum(case when b.fundType='loanInterest' then b.notMoney else 0.00 end) as loanInterest,"
				+" sum(case when b.fundType='principalRepayment' then b.notMoney else 0.00 end) as principal,"
				+" sum(case when b.fundType='serviceMoney' or b.fundType='consultationMoney'  then b.notMoney else 0.00 end) as serviceMoney,"
				+" b.punishDays,"
				+" IFNULL(SUM(b.accrualMoney),0) AS accMoney,"
				+" b.intentDate,"
				+" b.orderNo"
				+" FROM bp_fund_intent  AS b "
				+" LEFT JOIN pl_bid_plan p ON p.bidId = b.bidPlanId"
				+" LEFT JOIN bp_cust_relation r ON p.guarantorsId = r.offlineCusId"
				+" AND r.offlineCustType = 'b_guarantee'"
				+" LEFT JOIN bp_cust_member m ON m.id = r.p2pCustId"
				+" WHERE 1=1 "
				+" AND b.payintentPeriod != 0"
			//	+" AND b.bidPlanId =6045 "
				+" AND b.isValid = 0"
				+" AND b.isCheck = 0"
				+" AND b.fundType != 'principalLending'"
				+" AND b.intentDate < DATE_FORMAT("
				+" DATE_ADD(NOW(), INTERVAL 1 DAY),"
				+" '%Y-%m-%d'"
				+" )"
				+" and m.id="+guarantorsId
				+" AND b.notMoney > 0 "
				+" GROUP BY"
				+"  b.bidPlanId,"
				+" 	b.payintentPeriod"
				+"  ORDER BY b.intentDate desc ) as s where s.principal>0 "
		         );
	  
		 String projectName=request.getParameter("name");
		 if(null!=projectName && !"".equals(projectName)){
			 try {
				hql.append(" and s.proName like '%"+new String(projectName.getBytes("ISO8859-1"), "UTF-8")+"%'");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 String overDays=request.getParameter("overDays");
		 if(null!=overDays && !"".equals(overDays)){
			 hql.append(" and s.punishDays < "+Integer.valueOf(overDays));
		 }
		 String ostartDate=request.getParameter("ostartDate");
		 if(null!=ostartDate && !"".equals(ostartDate)){
			 hql.append(" and s.intentDate >='"+ostartDate+"'");
		 }
		 String oendDate=request.getParameter("oendDate");
		 if(null!=oendDate && !"".equals(oendDate)){
			 hql.append(" and s.intentDate <='"+oendDate+"'");
		 }
		// 	System.out.println("-->"+hql.toString());
			 list = this.getSession().createSQLQuery(hql.toString())
				.addScalar("bidId", Hibernate.LONG)
				.addScalar("proName", Hibernate.STRING)
				.addScalar("receiverName", Hibernate.STRING)
				.addScalar("loanInterest", Hibernate.BIG_DECIMAL)
				.addScalar("principal", Hibernate.BIG_DECIMAL)
				.addScalar("serviceMoney", Hibernate.BIG_DECIMAL)
				.addScalar("accMoney", Hibernate.BIG_DECIMAL)
				.addScalar("punishDays", Hibernate.INTEGER)
				.addScalar("payintentPeriod", Hibernate.INTEGER)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
		        .setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).list();
			return list;
	}

	
	
	@Override
	public List<BpFundIntent> queryNotPay(Long bidPlanId, String orderNo, String fundType) {
		String hql="from BpFundIntent as f where f.factDate is NULL and" +
				" f.bidPlanId=? and f.orderNo=? and f.fundType=? and f.isValid=0 and f.isCheck=0";
		return this.findByHql(hql, new Object[]{bidPlanId,orderNo,fundType});
	}

	@Override
	public BigDecimal getMoneyByPlanDateType(String temp, String planId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		String sql= "select SUM(bp.notMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
        "and bp.orderNo=?  and bp.isCheck=0 and bp.isValid=0 ";
          if(type!=null && !"".equals(type)){
             sql=sql+" and bp.fundType in "+type;
           }
      	if(null!=intentDate && !intentDate.equals("")){
  			sql=sql+" and bp.intentDate='"+intentDate+"'";
  		}
         return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, temp).uniqueResult();
	}

	@Override
	public BigDecimal getMoneyByPlanType(Long bidPlanId, String fundType,
			String intentDate) {
		// TODO Auto-generated method stub
		String hql="select sum(f.notMoney) from BpFundIntent as f where f.bidPlanId=? and f.isValid=0 and f.isCheck=0 and f.fundType=?";
		if(null!=intentDate && !intentDate.equals("")){
			hql=hql+" and f.intentDate='"+intentDate+"'";
		}
		List list=this.getSession().createQuery(hql).setParameter(0, bidPlanId).setParameter(1, fundType).list();
		BigDecimal money=new BigDecimal(0);
		if(null!=list && list.size()>0){
			if(null!=list.get(0)){
				money=(BigDecimal) list.get(0);
			}
		}
		return money;
	}

	@Override
	public BigDecimal getPenaltyInterest(String temp, String planId,
			String intentDate) {
		// TODO Auto-generated method stub
		String sql= "select SUM(bp.accrualMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
	    "and bp.orderNo=? and bp.isCheck=0 and bp.isValid=0  and bp.intentDate='"+intentDate+"'";
        return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId)).setParameter(1, temp).uniqueResult();
	}

	@Override
	public List<ShowManageMoney> listByByPlanIdDate(Long bidPlanId,
			String intentDate) {
		// TODO Auto-generated method stub
		//判断担保代偿的方式
		//singleInterest
		String guaranteeWay ="";
		//如果是本金保障则取偿还本金的那期
		String Type = "";
		guaranteeWay = plBidPlanDao.get(bidPlanId).getGuaranteeWay();
		if(bidPlanId!=null){
			PlBidPlan plan = plBidPlanDao.get(bidPlanId);
			Type  = plan.getGuaranteeWay();
		}
		String period  = "";
		String intentDate1 = "";
		if(Type!=null && !"".equals(Type) && ("1".equals(Type))){//本金保障
			period = bpFundIntentDao.getByPlanId(String.valueOf(bidPlanId)).get(0).getPayintentPeriod().toString();
			intentDate1 = bpFundIntentDao.getByPlanId(String.valueOf(bidPlanId)).get(0).getIntentDate().toString();
		}
		
		StringBuffer hql=new StringBuffer("SELECT "
				+" b.bidPlanId as bidId,"
				+" p.bidProName as proName,"
				+" p.receiverName as receiverName ,"
				+" b.payintentPeriod,");
				hql.append(" sum(case WHEN p.guaranteeWay = '1' THEN 0.00 when b.fundType='loanInterest' then b.notMoney else 0.00 end) as loanInterest,"
					+" sum(case when b.fundType='principalRepayment' then b.notMoney else 0.00 end) as principal,"
					+" sum(case WHEN p.guaranteeWay = '1' THEN 0.00  WHEN p.guaranteeWay = '2' THEN 0.00 when b.fundType='serviceMoney' or b.fundType='consultationMoney'  then b.notMoney+b.accrualMoney else 0.00 end) as serviceMoney,");
				hql.append(" b.punishDays,"
				+" IFNULL(SUM(b.accrualMoney),0) AS accMoney,"
				+" sum(case WHEN p.guaranteeWay = '1' THEN 0.00  WHEN p.guaranteeWay = '2' THEN 0.00 when b.fundType='principalRepayment' then b.accrualMoney else 0.00 end) as principalAccMoney,"
				+" sum(case WHEN p.guaranteeWay = '1' THEN 0.00  WHEN p.guaranteeWay = '2' THEN 0.00 when b.fundType='loanInterest' or b.fundType='principalRepayment'  then b.accrualMoney else 0.00 end) as interestAccMoney,"
				+" b.intentDate,"
				+" b.orderNo"
				+" FROM bp_fund_intent  AS b "
				+" LEFT JOIN pl_bid_plan p ON p.bidId = b.bidPlanId"
				+" WHERE 1=1 ");
				if(period!=null && !"".equals(period) && intentDate1!=null && !"".equals(intentDate1)){
					hql.append(" AND b.payintentPeriod ="+period
							//	+" AND b.bidPlanId =6045 "
								+" AND b.isValid = 0"
								+" AND b.isCheck = 0"
								+" AND b.fundType != 'principalLending'"
							    +" AND b.bidPlanId="+ bidPlanId
								+" and b.intentDate='"+intentDate1+"'"
								+" GROUP BY"
								+"  b.bidPlanId,"
								+" 	b.payintentPeriod");
				}else{
					hql.append(" AND b.payintentPeriod != 0"
							//	+" AND b.bidPlanId =6045 "
								+" AND b.isValid = 0"
								+" AND b.isCheck = 0"
								+" AND b.fundType != 'principalLending'"
							    +" AND b.bidPlanId="+ bidPlanId
								+" and b.intentDate='"+intentDate+"'"
								+" GROUP BY"
								+"  b.bidPlanId,"
								+" 	b.payintentPeriod");
				}
	     	System.out.println("-->"+hql.toString());
			return this.getSession().createSQLQuery(hql.toString())
				.addScalar("bidId", Hibernate.LONG)
				.addScalar("proName", Hibernate.STRING)
				.addScalar("receiverName", Hibernate.STRING)
				.addScalar("loanInterest", Hibernate.BIG_DECIMAL)
				.addScalar("principal", Hibernate.BIG_DECIMAL)
				.addScalar("serviceMoney", Hibernate.BIG_DECIMAL)
				.addScalar("accMoney", Hibernate.BIG_DECIMAL)
				.addScalar("principalAccMoney", Hibernate.BIG_DECIMAL)
				.addScalar("interestAccMoney", Hibernate.BIG_DECIMAL)
				.addScalar("punishDays", Hibernate.INTEGER)
				.addScalar("payintentPeriod", Hibernate.INTEGER)
				.addScalar("intentDate", Hibernate.DATE)
				.addScalar("orderNo", Hibernate.STRING)
		        .setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).list();
	}
	/**
	 * 根据标的ID和款项类型查询款项记录
	 */
	@Override
	public List<BpFundIntent> listBybidPlanIdType(Long bidPlanId,
			String fundType) {
		// TODO Auto-generated method stub
		String hql="from BpFundIntent as f where f.bidPlanId=? and f.fundType=? and f.isValid=0 and f.isCheck=0 ";
		System.out.println("hql==="+hql);
	return this.findByHql(hql, new Object[]{bidPlanId,fundType});
	}

	@Override
	public BigDecimal sumAllCompensatoryMoney(String planId, String peridId,
			String fundType) {
		// TODO Auto-generated method stub
		String hql=" select sum(case WHEN plan.guaranteeWay='1' THEN  p.afterMoney WHEN plan.guaranteeWay='2' THEN  p.afterMoney WHEN plan.guaranteeWay='3' THEN  (p.afterMoney+P.accrualMoney) END) from bp_fund_intent as p left join pl_bid_plan as plan on plan.bidId = p.bidPlanId where p.bidPlanId="+Long.valueOf(planId)+" and p.intentDate='"+peridId+"'";
		if(null!=fundType && !"".equals(fundType)){
			hql=hql+" and p.fundType in  "+fundType+" ";
		}
		System.out.println("111"+hql);
		return (BigDecimal) this.getSession().createSQLQuery(hql).uniqueResult(); 
	}

	@Override
	public List<BpFundIntent> getOverList(String bidId) {
		// TODO Auto-generated method stub
		String hql="from BpFundIntent as f where f.bidPlanId=? and f.factDate is null and f.isValid=0 and f.isCheck=0 ";
		return this.findByHql(hql, new Object[]{Long.valueOf(bidId)});
	}


	@Override
	public BigDecimal getManageFee(String bidId, String periodId,String slEarlyRepaymentId) {
		//对于提前还款费用查询做特殊处理
		BigDecimal feeMoney = new BigDecimal("0");
		StringBuffer sql =new StringBuffer();
		if(null != slEarlyRepaymentId && !"".equals(slEarlyRepaymentId)){
			sql.append("select IFNULL(sum(IFNULL(incomeMoney,0)+IFNULL(accrualMoney,0)),0) from bp_fund_intent as intent where intent.fundType in ('serviceMoney','consultationMoney') and intent.bidPlanId = ?  and intent.slEarlyRepaymentId = ? and intent.isValid = 0");
//			sql.append("select sum(intent.incomeMoney+IFNULL(intent.accrualMoney,0)) from bp_fund_intent as intent where intent.fundType in ('serviceMoney','consultationMoney') and intent.bidPlanId = ? and intent.payintentPeriod = ? and intent.slEarlyRepaymentId = ? and intent.isCheck = 0 and intent.isValid = 0");
			feeMoney = (BigDecimal)this.getSession().createSQLQuery(sql.toString()).setParameter(0, bidId)/*.setParameter(1, periodId)*/.setParameter(1, slEarlyRepaymentId).uniqueResult();
		}else{
			sql.append("select IFNULL(sum(IFNULL(incomeMoney,0)+IFNULL(accrualMoney,0)),0) from bp_fund_intent as intent where intent.fundType in ('serviceMoney','consultationMoney') and intent.bidPlanId = ? and intent.payintentPeriod = ? and intent.isCheck = 0 and intent.isValid = 0");
			feeMoney = (BigDecimal)this.getSession().createSQLQuery(sql.toString()).setParameter(0, bidId).setParameter(1, periodId).uniqueResult();
		}
		if(null == feeMoney || "".equals(feeMoney)){
			feeMoney = new BigDecimal("0");
		}
		return feeMoney;
	}

	@Override
	public BigDecimal findWaitCompensatoryMoney(Long guarantorsId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT");
		             sql.append("  IFNULL(SUM(cmoney), 0) AS money");
		             sql.append("  FROM");
		             sql.append("  (SELECT ( ");
		             sql.append("  CASE p.guaranteeWay WHEN 1 THEN ( CASE WHEN b.fundType = 'principalRepayment' THEN (b.notMoney + b.accrualMoney) ELSE 0.00  END )");
		             sql.append("  WHEN 2 THEN ( CASE WHEN b.fundType = 'principalRepayment' OR b.fundType = 'loanInterest' THEN (b.notMoney + b.accrualMoney) ELSE 0.00  END )");
		             sql.append("  WHEN 3 THEN ( CASE WHEN b.fundType = 'principalRepayment' OR b.fundType = 'loanInterest'  OR b.fundType = 'consultationMoney' OR b.fundType = 'serviceMoney' THEN (b.notMoney + b.accrualMoney) ELSE 0.00  END )");
		             sql.append("  ELSE 0 END ) AS cmoney");
		             sql.append("  FROM bp_fund_intent AS b");
		             sql.append("  LEFT JOIN pl_bid_plan p ON p.bidId = b.bidPlanId");
		             sql.append("  LEFT JOIN bp_cust_relation r ON p.guarantorsId = r.offlineCusId");
		             sql.append("  AND r.offlineCustType = 'b_guarantee'");
		             sql.append("  LEFT JOIN bp_cust_member m ON m.id = r.p2pCustId");
		             sql.append("  WHERE b.payintentPeriod != 0");
		             sql.append("  AND b.isValid = 0");
		             sql.append("  AND b.isCheck = 0");
		             sql.append("  AND b.fundType != 'principalLending'");
		             sql.append("  AND b.intentDate < DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 1 DAY),'%Y-%m-%d')");
		             sql.append("  AND b.notMoney > 0");
		             if(null!=guarantorsId && !"".equals(guarantorsId)){
		              sql.append(" AND m.id ="+guarantorsId); 
		             }
		             sql.append(" ORDER BY b.intentDate DESC) AS bb");
		             sql.append(" WHERE bb.cmoney > 0;");
	    System.out.println("-->"+sql.toString());
		return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult(); 
	}

	@Override
	public List<BpFundIntent> getListByBidIdAndIntent(String bidId, Date date) {
		String sql = "from BpFundIntent as bp where bp.bidPlanId = ? and bp.isCheck=0 and bp.isValid=0  and bp.intentDate=?";
		return  this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bidId)).setParameter(1, date).list();

	}
	@Override
	public BigDecimal getEarlyAfterMoney(String temp, String planId,
			String payintentPeriod, String type) {
		String sql= "select SUM(bp.incomeMoney) from BpFundIntent  as bp where bp.bidPlanId = ? " +
	    "and bp.orderNo=?and bp.isCheck=0 and payintentPeriod=? and slEarlyRepaymentId is not null";
		if(type!=null){
		sql=sql+" and bp.fundType in "+type;
		}
		System.out.println("sql====="+sql);
		return (BigDecimal) this.getSession().createQuery(sql).setParameter(0, Long.valueOf(planId))
				.setParameter(1, temp).setParameter(2, Integer.valueOf(payintentPeriod)).uniqueResult();
	}
	
	/**
	 * sql语句修改台账
	 * @param temp
	 * @return
	 */
	public boolean update(BpFundIntent temp){
		String sql = "update bp_fund_intent b set b.isCheck = 0 where fundIntentId=?";
		int b = this.getSession().createSQLQuery(sql).setParameter(0, temp.getFundIntentId()).executeUpdate();
		if(b>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<BpFundIntent> getIntentList(Long bidPlanId,
			Integer payintentPeriod) {
		String sql ="SELECT * FROM bp_fund_intent WHERE bidPlanId=? AND payintentPeriod=? ";
		List<BpFundIntent> list=this.getSession().createSQLQuery(sql).addEntity(BpFundIntent.class).setParameter(0, bidPlanId).setParameter(1, payintentPeriod).list();
		return list;
	}
	/**
	 * 查询提前还款过的款项
	 * @return
	 */
	@Override
	public List<BpFundIntent> getBySlEarlyRepaymentId(Long bidId){
		String hql = "from BpFundIntent as bp where bp.bidPlanId = ? and bp.slEarlyRepaymentId is not null order by bp.fundIntentId ";
		return this.findByHql(hql, new Object[]{bidId});
	}
}
