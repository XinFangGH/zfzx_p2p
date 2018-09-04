package com.hurong.credit.dao.customer.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.command.QueryFilter;
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.finance.compensatory.PlBidCompensatoryDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.dao.customer.InvestPersonInfoDao;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.sdicons.json.validator.impl.predicates.Decimal;
import com.thirdPayInterface.CommonRequestInvestRecord;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class InvestPersonInfoDaoImpl extends BaseDaoImpl<InvestPersonInfo> implements InvestPersonInfoDao{

	public InvestPersonInfoDaoImpl() {
		super(InvestPersonInfo.class);
	}
	@Resource
	private PlBidPlanDao plBidPlanDao;
	@Resource
	private BpFundProjectDao bpFundProjectDao;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private PlBidCompensatoryDao plBidCompensatoryDao;
	/**
	 * 获取投资人Id列表
	 * @return
	 */
	public List<InvestPersonInfo> getByPersonId(Long personId){
		String hql = "from InvestPersonInfo i where i.investPersonId=?";
		return findByHql(hql, new Object[]{personId});
	}

	@Override
	public List<InvestPersonInfo> getByRequestNumber(String requestNo) {
		String hql = "from InvestPersonInfo i where i.orderNo=?";
		return this.getSession().createQuery(hql).setParameter(0, requestNo).list();
	}

	@Override
	public List<InvestPersonInfo>  getByPlanId(Long bidId) {
		String hql = "from InvestPersonInfo i where i.bidPlanId=?";
		return findByHql(hql, new Object[]{bidId});
	}

	@Override
	public List<InvestPersonInfo>  bidedMoneyByOrderNo(Long bidId,String orderNo) {
		String hql = "from InvestPersonInfo i where i.bidPlanId=? and i.orderNo!=?";
		return findByHql(hql, new Object[]{bidId,orderNo});
	}

	@Override
	public List<InvestPersonInfo> queryName(String userName,String type,Pager pager) {
		if(null !=pager && !"".equals(pager)){
			Integer first = pager.getPageNumber()*pager.getPageSize();
			if("Planbacking".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=7 )";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(0).setMaxResults(pager.getPageSize()).list();
			}else if("success".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where  plan.state in(1,2,4,6) )";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(0).setMaxResults(pager.getPageSize()).list();
			}else if("error".equals(type)){
				return null;
			}else if("Planback".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=10 )";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(0).setMaxResults(pager.getPageSize()).list();
			}else if("All".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=?";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(0).setMaxResults(pager.getPageSize()).list();
			}else{
				return null;
			}
		}else{
			if("Planbacking".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=7 )";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			}else if("success".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where  plan.state in(1,2,4,6) ) ORDER BY  info.investId DESC";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			}else if("error".equals(type)){
				return null;
			}else if("Planback".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=10 )";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			}else if("All".equals(type)){
				String sql ="select * from invest_person_info as info where info.investPersonName=?";
				return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			}else{
				return null;
			}
		}
		
	}
	
	
	@Override
	public List<InvestPersonInfo> queryNamepager(String userName,String type,Integer begin,Integer max) {
		Integer first = begin==0?0:begin*max;
		if("Planbacking".equals(type)){
			String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=7 )";
			return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(first).setMaxResults(max).list();
		}else if("success".equals(type)){
			String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where  plan.state in(1,2,4,6) )";
			return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(first).setMaxResults(max).list();
		}else if("error".equals(type)){
			return null;
		}else if("Planback".equals(type)){
			String sql ="select * from invest_person_info as info where info.investPersonName=? and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=10 )";
			return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(first).setMaxResults(max).list();
		}else if("All".equals(type)){
			String sql ="select * from invest_person_info as info where info.investPersonName=?";
			return this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).setParameter(0, userName).setFirstResult(first).setMaxResults(max).list();
		}else{
			return null;
		}
		
	}

	@Override
	public BigInteger getCount(String userName,String type,Pager pager) {
		if("Planbacking".equals(type)){
			String sql ="select COUNT(*) from invest_person_info as info where info.investPersonName='"+userName+"' and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=7 )";
			return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		}else if("success".equals(type)){
			String sql ="select COUNT(*) from invest_person_info as info where info.investPersonName='"+userName+"' and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where  plan.state in(1,2,4,6) )";
			return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		}else if("error".equals(type)){
			return null;
		}else if("Planback".equals(type)){
			String sql ="select COUNT(*) from invest_person_info as info where info.investPersonName='"+userName+"' and bidPlanId IN(select plan.bidId from pl_bid_plan as plan where plan.state=10 )";
			return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		}else if("All".equals(type)){
			String sql ="select COUNT(*) from invest_person_info as info where info.investPersonName='"+userName+"'";
			return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		}else{
			return null;
		}
	}

	@Override
	public List<CommonRequestInvestRecord> getRepaymentList(String planId,String peridId) {
		List<CommonRequestInvestRecord> list=new ArrayList<CommonRequestInvestRecord>();
		QueryFilter filter = new QueryFilter();
		filter.addFilter("Q_planId_L_EQ", Long.valueOf(planId));
		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(peridId));
		List<PlBidCompensatory> comp = plBidCompensatoryDao.getAll(filter);
		PlBidPlan plan = plBidPlanDao.get(Long.valueOf(planId));
		Boolean isComp = false;
		if(comp.size()>0 && plan.getGuaranteeWay()!=null && !"3".equals(plan.getGuaranteeWay())){
			isComp = true;
		}
		StringBuffer sql=new StringBuffer(
				/*"select bf.intentDate as intentDate, bf.fundIntentId,bm.thirdPayFlagId as invest_thirdPayConfigId,bf.requestDate," +
				" IFNULL(pb.newOrderNo,info.orderNo) as bidRequestNo," +//还款请求流水号
				" sum( IF (bf.fundType NOT IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest'),IF(bf.afterMoney>0,bf.notMoney,bf.notMoney+bf.accrualMoney),0	)	) AS amount,"+//还款金额
				" sum(IF (bf.isOverdue IS NOT NULL,bf.accrualMoney,0) +IF (bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal,sum(IF (bf.isOverdue IS NOT NULL,bf.accrualMoney,0) +IF (bf.fundType IN ('loanInterest'),bf.notMoney,0)) AS interest,"+
				" sum(IF(bf.fundType in('consultationMoney','serviceMoney'),IFNULL(bf.notMoney,0)+IFNULL(bf.accrualMoney,0),0)) as fee" +//平台服务费
				" from invest_person_info as info" +
				" LEFT JOIN  pl_bid_info as pb on info.orderNo=pb.orderNo" +
				" LEFT JOIN bp_cust_member as bm on bm.id=IFNULL(pb.newInvestPersonId,info.investPersonId)" +
				" LEFT JOIN bp_fund_intent as bf on bf.bidPlanId=info.bidPlanId and info.orderNo=bf.orderNo and bf.isCheck=0 and bf.isValid=0 "*/
				
				
				"select bf.intentDate as intentDate, bf.fundIntentId,bm.thirdPayFlagId as invest_thirdPayConfigId," +
				"bm.thirdPayFlag0 AS invest_thirdPayConfigId0,pb.bidtime AS requestDate," +
				" IFNULL(pb.newOrderNo,info.orderNo) as bidRequestNo,");//还款请求流水号
				if(isComp){
					sql.append("sum(IF (bf.fundType NOT IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest'),IF(bf.afterMoney>0,bf.notMoney+bf.accrualMoney,bf.notMoney+bf.accrualMoney),0)) AS amount,");
				}else{
					sql.append("sum(IF (bf.fundType NOT IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest'),IF(bf.afterMoney>0,bf.notMoney,bf.notMoney+bf.accrualMoney),0)) AS amount,");
				}
				
				
				sql.append("sum(IF (bf.fundType IN ('principalRepayment','loanInterest' ")
				.append("),bf.accrualMoney,0)) AS accrual,");
				
				sql.append(" sum(IF (bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal," +//本金
							" sum(IF (bf.fundType IN ('loanInterest'),bf.notMoney,0)) AS interest,"+//利息
							" sum(IF (bf.fundType in('consultationMoney','serviceMoney'),IFNULL(bf.notMoney,0)+IFNULL(bf.accrualMoney,0),0)) as fee" +//平台服务费+罚息
							" from invest_person_info as info" +
							" LEFT JOIN  pl_bid_info as pb on info.orderNo=pb.orderNo" +
							" LEFT JOIN bp_cust_member as bm on bm.id=IFNULL(pb.newInvestPersonId,info.investPersonId)" +
							" LEFT JOIN bp_fund_intent as bf on bf.bidPlanId=info.bidPlanId and info.orderNo=bf.orderNo and bf.isCheck=0 and bf.isValid=0 "
		);
		if(null!=peridId && !"".equals(peridId)){
			sql.append(" and bf.payintentPeriod="+peridId);
		}
		if(null!=planId && !"".equals(planId)){
			sql.append(" where info.bidPlanId="+planId);
		}
		sql.append(" GROUP BY info.orderNo");
		System.out.println("还款sql="+sql);
		list=this.getSession().createSQLQuery(sql.toString())
			.addScalar("invest_thirdPayConfigId",Hibernate.STRING)
			.addScalar("invest_thirdPayConfigId0",Hibernate.STRING)
			.addScalar("bidRequestNo",Hibernate.STRING)
			.addScalar("requestDate",Hibernate.DATE)
			.addScalar("fundIntentId",Hibernate.LONG)
			.addScalar("amount",Hibernate.BIG_DECIMAL)
			.addScalar("accrual",Hibernate.BIG_DECIMAL)
			.addScalar("principal", Hibernate.BIG_DECIMAL)
			.addScalar("interest",Hibernate.BIG_DECIMAL)			
			.addScalar("fee",Hibernate.BIG_DECIMAL)
			.addScalar("intentDate",Hibernate.DATE)
			.setResultTransformer(Transformers.aliasToBean(CommonRequestInvestRecord.class))
			.list();
		
		return list;
	}
	@Override
	public List<CommonRequestInvestRecord> getRepayEarlymentList(String planId,String slEarlyPayId) {
		List<CommonRequestInvestRecord> list=new ArrayList<CommonRequestInvestRecord>();
		StringBuffer sql=new StringBuffer("select bf.intentDate as intentDate,bf.fundIntentId," +
				" bm.thirdPayFlagId as invest_thirdPayConfigId," +
				" bm.thirdPayFlag0 AS invest_thirdPayConfigId0, " +
				" pb.bidtime AS requestDate," +
				" IFNULL(pb.newOrderNo,info.orderNo) as bidRequestNo," +//还款请求流水号
				" sum(IF (bf.isOverdue IS NOT NULL,	bf.accrualMoney,0) + IF (bf.fundType not IN ('couponInterest','principalCoupons','subjoinInterest','raiseinterest','commoninterest'),bf.notMoney,0)) AS amount," +//-- 还款金额
				" sum(IF(bf.fundType in('consultationMoney','serviceMoney'),bf.notMoney,0)) as fee,sum(IF(bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal,SUM(IF(bf.fundType IN ('loanInterest'),bf.notMoney,0)) AS interest " +//平台服务费
				" from invest_person_info as info" +
				" LEFT JOIN  pl_bid_info as pb on info.orderNo=pb.orderNo" +
				" LEFT JOIN bp_cust_member as bm on bm.id=IFNULL(pb.newInvestPersonId,info.investPersonId)" +
		" LEFT JOIN bp_fund_intent as bf on bf.bidPlanId=info.bidPlanId and info.orderNo=bf.orderNo and bf.isCheck=1 and bf.isValid=0 ");
		if(null!=slEarlyPayId && !"".equals(slEarlyPayId)){
			sql.append(" and bf.slEarlyRepaymentId="+slEarlyPayId);  
		}
		if(null!=planId && !"".equals(planId)){
			sql.append(" where info.bidPlanId="+planId);
		}
		sql.append(" GROUP BY info.orderNo");
		System.out.println("提前还款sql为"+sql.toString());
		list=this.getSession().createSQLQuery(sql.toString())
		.addScalar("invest_thirdPayConfigId",Hibernate.STRING)
		.addScalar("invest_thirdPayConfigId0",Hibernate.STRING)
		.addScalar("bidRequestNo",Hibernate.STRING)
		.addScalar("requestDate",Hibernate.DATE)
		.addScalar("fundIntentId",Hibernate.LONG)
		.addScalar("amount",Hibernate.BIG_DECIMAL)
		.addScalar("fee",Hibernate.BIG_DECIMAL)
		.addScalar("intentDate",Hibernate.DATE)
		.addScalar("principal", Hibernate.BIG_DECIMAL)
		.addScalar("interest", Hibernate.BIG_DECIMAL)
		.setResultTransformer(Transformers.aliasToBean(CommonRequestInvestRecord.class))
		.list();
		return list;
	}

	@Override
	public List<CommonRequestInvestRecord> getFeeRepayEarlymentList(
			String planId, String slEarlyPayId) {
		List<CommonRequestInvestRecord> list=new ArrayList<CommonRequestInvestRecord>();
		StringBuffer sql=new StringBuffer("select bf.intentDate as intentDate,bf.fundIntentId,bm.thirdPayFlagId as invest_thirdPayConfigId,bf.requestDate," +
				" IFNULL(pb.newOrderNo,info.orderNo) as bidRequestNo," +//还款请求流水号
				" sum(IF (bf.isOverdue IS NOT NULL,	bf.accrualMoney,0) + IF (bf.fundType not IN ('couponInterest','principalCoupons','subjoinInterest','raiseinterest','commoninterest'),bf.notMoney,0)) AS amount," +//-- 还款金额
				" sum(IF(bf.fundType in('consultationMoney','serviceMoney'),bf.notMoney,0)) as fee" +//平台服务费
				" from invest_person_info as info" +
				" LEFT JOIN  pl_bid_info as pb on info.orderNo=pb.orderNo" +
				" LEFT JOIN bp_cust_member as bm on bm.id=IFNULL(pb.newInvestPersonId,info.investPersonId)" +
		" LEFT JOIN bp_fund_intent as bf on bf.bidPlanId=info.bidPlanId and info.orderNo=bf.orderNo and bf.isCheck=1 and bf.isValid=0 ");
		if(null!=slEarlyPayId && !"".equals(slEarlyPayId)){
			sql.append(" and bf.slEarlyRepaymentId="+slEarlyPayId);  
		}
		if(null!=planId && !"".equals(planId)){
			sql.append(" where info.bidPlanId="+planId);
		}
		sql.append(" GROUP BY info.orderNo");
		
		list=this.getSession().createSQLQuery(sql.toString())
		.addScalar("invest_thirdPayConfigId",Hibernate.STRING)
		.addScalar("bidRequestNo",Hibernate.STRING)
		.addScalar("requestDate",Hibernate.DATE)
		.addScalar("fundIntentId",Hibernate.LONG)
		.addScalar("amount",Hibernate.BIG_DECIMAL)
		.addScalar("fee",Hibernate.BIG_DECIMAL)
		.addScalar("intentDate",Hibernate.DATE)
		.setResultTransformer(Transformers.aliasToBean(CommonRequestInvestRecord.class))
		.list();
		return list;
	}

	@Override
	public List<CommonRequestInvestRecord> getCompensatoryRepaymentList(
			String planId, String intentDate) {
		// TODO Auto-generated method stub
		
		String guaranteeWay ="";
		//如果是本金保障则取偿还本金的那期
		String Type = "";
		guaranteeWay = plBidPlanDao.get(Long.valueOf(planId)).getGuaranteeWay();
		String period  = "";
		String intentDate1 = "";
		Type = plBidPlanDao.get(Long.valueOf(planId)).getGuaranteeWay();
		if(Type!=null && !"".equals(Type) && "1".equals(Type)){//本金保障
			period = bpFundIntentDao.getByPlanId(planId).get(0).getPayintentPeriod().toString();
			intentDate1 = bpFundIntentDao.getByPlanId(planId).get(0).getIntentDate().toString();
		}
		
		List<CommonRequestInvestRecord> list=new ArrayList<CommonRequestInvestRecord>();
		StringBuffer sql=new StringBuffer("select bf.intentDate as intentDate, bf.fundIntentId,bm.thirdPayFlagId as invest_thirdPayConfigId,bf.requestDate," +
								" IFNULL(pb.newOrderNo,info.orderNo) as bidRequestNo," //还款请求流水号
								);
		
		
		
		if(guaranteeWay!=null && !"".equals(guaranteeWay) && guaranteeWay.equals("1")){//本金保障
			sql.append(" sum(IF (bf.fundType not IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest'),  IFNULL(bf.notMoney,0),0)) AS amount," +//-- 还款金额
			"	sum(IF (bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal,");
			sql.append("sum(0) AS interest,"+
					"   sum(0) as fee," +//平台服务费
					"   sum(0) AS accrual,"+
					" sum(0) as principalAccMoney,"+
			" sum(0) as interestAccMoney");
			

		}else if(guaranteeWay!=null && !"".equals(guaranteeWay) && guaranteeWay.equals("2")){//本息保障
			sql.append(" sum(IF (bf.fundType not IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest','consultationMoney','serviceMoney'), IFNULL(bf.notMoney,0),0)) AS amount," +//-- 还款金额
			"	sum(IF (bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal,");
			sql.append("sum(IF (bf.fundType IN ('loanInterest'),bf.notMoney,0)) AS interest,"+
					"   sum(0) as fee," +//平台服务费
					"   sum(0) AS accrual,"+
					" sum(0) as principalAccMoney,"+
					" sum(0) as interestAccMoney");
		}else{//全部保障
			sql.append(" sum(IF (bf.accrualMoney>0,bf.accrualMoney,0) + IF (bf.fundType not IN ('couponInterest','principalCoupons','subjoinInterest','commoninterest','raiseinterest'), IFNULL(bf.notMoney,0),0)) AS amount," +//-- 还款金额+IFNULL(bf.accrualMoney,0)
			"	sum(IF (bf.fundType IN ('principalRepayment'),bf.notMoney,0)) AS principal,");
			sql.append("sum(IF (bf.fundType IN ('loanInterest'),bf.notMoney,0)) AS interest,"+
					"   sum(IF(bf.fundType in('consultationMoney','serviceMoney'),bf.notMoney+bf.accrualMoney,0)) as fee," +//平台服务费
					"   sum(IFNULL(bf.accrualMoney, 0)	) AS accrual,"+
					" sum(case when bf.fundType='principalRepayment' then bf.accrualMoney else 0.00 end) as principalAccMoney,"+
			" sum(case when bf.fundType='loanInterest' or bf.fundType='principalRepayment'  then bf.accrualMoney else 0.00 end) as interestAccMoney");
		}
		
		sql.append(
			    " from invest_person_info as info" +
				" LEFT JOIN  pl_bid_info as pb on info.orderNo=pb.orderNo" +
				" LEFT JOIN bp_cust_member as bm on bm.id=IFNULL(pb.newInvestPersonId,info.investPersonId)" +
				" LEFT JOIN bp_fund_intent as bf on bf.bidPlanId=info.bidPlanId and info.orderNo=bf.orderNo and bf.isCheck=0 and bf.isValid=0 ");
		if(null!=intentDate && !"".equals(intentDate)){
			sql.append(" and bf.intentDate='"+intentDate+"'");
		}
		if(guaranteeWay!=null && !"".equals(guaranteeWay) && guaranteeWay.equals("1")){//本金保障
			sql.append(" and bf.fundType = 'principalRepayment'");
		}
		if(null!=planId && !"".equals(planId)){
			sql.append(" where info.bidPlanId="+planId);
		}
		sql.append(" GROUP BY info.orderNo");
		list=this.getSession().createSQLQuery(sql.toString())
			.addScalar("invest_thirdPayConfigId",Hibernate.STRING)
			.addScalar("bidRequestNo",Hibernate.STRING)
			.addScalar("requestDate",Hibernate.DATE)
			.addScalar("fundIntentId",Hibernate.LONG)
			.addScalar("amount",Hibernate.BIG_DECIMAL)
			.addScalar("principal", Hibernate.BIG_DECIMAL)
			.addScalar("interest",Hibernate.BIG_DECIMAL)			
			.addScalar("fee",Hibernate.BIG_DECIMAL)
			.addScalar("accrual",Hibernate.BIG_DECIMAL)
			.addScalar("principalAccMoney",Hibernate.BIG_DECIMAL)
			.addScalar("interestAccMoney",Hibernate.BIG_DECIMAL)
			.addScalar("intentDate",Hibernate.DATE)
			.setResultTransformer(Transformers.aliasToBean(CommonRequestInvestRecord.class))
			.list();
		
		return list;
	}

	@Override
	public BigDecimal getSumInvestMoney(Long bidId){
		StringBuffer sql = new StringBuffer("select sum(investMoney) from invest_person_info where bidPlanId="+bidId);
		System.out.println(sql);
		Object bigDecimal = this.getSession().createSQLQuery(sql.toString()).uniqueResult();
		if (bigDecimal != null ){
			return (BigDecimal) bigDecimal;

		}else {
			return BigDecimal.ZERO;
		}

	}
}