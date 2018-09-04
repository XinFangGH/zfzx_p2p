package com.hurong.credit.dao.creditFlow.financingAgency.impl;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * @author
 * 
 */
@SuppressWarnings("unchecked")
public class PlBidInfoDaoImpl extends BaseDaoImpl<PlBidInfo> implements
		PlBidInfoDao {

	public PlBidInfoDaoImpl() {
		super(PlBidInfo.class);
	}

	@Override
	public PlBidInfo getByOrdId(String ordId) {
		String hql = "from PlBidInfo p where p.orderNo=?";
		Object[] params = { ordId };
		return (PlBidInfo) findUnique(hql, params);
	}

	@Override
	public List<PlBidInfo> getBidLoanAfter() {
		String hql = "from PlBidInfo p where p.state>=? and p.state<=?";
		Object[] params = { Short.valueOf("2"), Short.valueOf("3") };
		return findByHql(hql, params);
	}

	@Override
	public List<PlBidInfo> getPlBidList(int bidId) {
		String hql = "select * from pl_bid_info p where p.state>0 and p.bidId=? GROUP BY p.bidtime ORDER BY p.bidtime desc";
		return this.getSession().createSQLQuery(hql).addEntity(PlBidInfo.class)
				.setParameter(0, bidId).list();
	}

	@Override
	public Long getPlanInfoCount(Long userId, String type) {
		Calendar calen = Calendar.getInstance();
		int years = calen.get(Calendar.YEAR);
		int month = calen.get(Calendar.MONTH) + 1;
		if ("totle".equals(type)) {
			/*
			 * String hql=
			 * "select info.bidId from pl_bid_info as info inner join bp_fund_intent as fund on info.bidId= fund.bidPlanId and info.userId=? and info.state>=1 and info.state<4 and fund.fundType!='principalLending' GROUP BY fund.bidPlanId"
			 * ; List<Long> list =
			 * this.getSession().createSQLQuery(hql).setParameter(0,
			 * userId).list(); if(list!=null&list.size()>0){ return new
			 * Long(list.size()); }else{ return new Long(0); }
			 */
			String hql = "select COUNT(*) from PlBidInfo p where p.userId=? and p.state>=1 and p.state<4";
			return (Long) this.getSession().createQuery(hql).setParameter(0,
					userId).uniqueResult();
		} else {
			/*
			 * String hql=
			 * "select COUNT(*) from PlBidInfo p where p.userId=? and p.state>=1 and p.state<4 and YEAR(p.bidtime)=? and MONTH(p.bidtime)=? GROUP BY MONTH(p.bidtime)"
			 * ; return (Long)this.getSession().createQuery(hql)
			 * .setParameter(0, userId).setParameter(1, years).setParameter(2,
			 * month).uniqueResult();
			 */
			String hql = "select info.bidId from pl_bid_info as info inner join bp_fund_intent as fund on info.bidId= fund.bidPlanId and info.userId=? and YEAR(info.bidtime)=? and MONTH(info.bidtime)=? and info.state>=1 and info.state<4 and fund.fundType!='principalLending' GROUP BY fund.bidPlanId";
			List<Long> list = this.getSession().createSQLQuery(hql)
					.setParameter(0, userId).setParameter(1, years)
					.setParameter(2, month).list();
			if (list != null & list.size() > 0) {
				return new Long(list.size());
			} else {
				return new Long(0);
			}
		}
	}

	@Override
	public Long getPlanInfoFailCount(Long userId, String type) {
		Calendar calen = Calendar.getInstance();
		int years = calen.get(Calendar.YEAR);
		int month = calen.get(Calendar.MONTH) + 1;
		if ("totle".equals(type)) {
			String hql = "select COUNT(*) from PlBidInfo p where p.userId=? and p.state=4";
			return (Long) this.getSession().createQuery(hql).setParameter(0,
					userId).uniqueResult();
		} else {
			String hql = "select COUNT(*) from PlBidInfo p where p.userId=? and p.state=4 and YEAR(p.bidtime)=? and MONTH(p.bidtime)=? GROUP BY MONTH(p.bidtime)";
			return (Long) this.getSession().createQuery(hql).setParameter(0,
					userId).setParameter(1, years).setParameter(2, month)
					.uniqueResult();
		}
	}

	@Override
	public List<PlBidInfo> getBidList(Long userId, String type) {
		if ("success".equals(type)) {
			// select * from PlBidInfo p where p.userId=86 and p.state>=1 and
			// p.state<4 GROUP BY p.bidId
			String hql = "select * from pl_bid_info p where p.userId=?  and (p.state>=1 and p.state<4) and p.bidId in(select plan.bidId from pl_bid_plan as plan where plan.state=1) ";
			return this.getSession().createSQLQuery(hql).addEntity(
					PlBidInfo.class).setParameter(0, userId).list();
		} else {
			String hql = "select * from pl_bid_info p where p.userId=?  and p.state=4  ";
			return this.getSession().createSQLQuery(hql).addEntity(
					PlBidInfo.class).setParameter(0, userId).list();
		}
	}

	@Override
	public List<PlBidInfo> getBidOrderNoList(Long userId, String type) {
		String sql = "";
		if ("Planback".equals(type)) {
			sql = "select plan.bidId from pl_bid_info as info inner join pl_bid_plan as plan on info.bidId=plan.bidId where info.userId=? and plan.state=10  "
					+ "GROUP BY info.bidId ORDER BY info.bidtime desc";
			List<java.math.BigInteger> list = this.getSession().createSQLQuery(
					sql).setParameter(0, userId).list();
			List<PlBidInfo> plBidList = new ArrayList<PlBidInfo>();
			if (list != null && list.size() > 0) {
				for (java.math.BigInteger obj : list) {
					PlBidInfo info = new PlBidInfo();
					info.setBidId(obj.longValue());
					plBidList.add(info);
				}
				return plBidList;
			} else {
				return null;
			}
		} else if ("Planbacking".equals(type)) {
			sql = "select plan.bidId from pl_bid_info as info inner join pl_bid_plan as plan on info.bidId=plan.bidId where info.userId=? and plan.state=7  "
					+ "GROUP BY info.bidId ORDER BY info.bidtime desc";
			List<java.math.BigInteger> list = this.getSession().createSQLQuery(
					sql).setParameter(0, userId).list();
			List<PlBidInfo> plBidList = new ArrayList<PlBidInfo>();
			if (list != null && list.size() > 0) {
				for (java.math.BigInteger obj : list) {
					PlBidInfo info = new PlBidInfo();
					info.setBidId(obj.longValue());
					plBidList.add(info);
				}
				return plBidList;
			} else {
				return null;
			}
		} else if ("url".equals(type)) {
			sql = "select * from pl_bid_info as info where info.userId=? ";
			return this.getSession().createSQLQuery(sql).addEntity(
					PlBidInfo.class).setParameter(0, userId).list();
		} else {
			sql = "select * from pl_bid_info info where info.userId=?  GROUP BY info.bidId ORDER BY info.bidtime desc";
			return this.getSession().createSQLQuery(sql).addEntity(
					PlBidInfo.class).setParameter(0, userId).list();
		}

		// return
		// this.getSession().createSQLQuery(sql).addEntity(PlBidInfo.class).setParameter(0,
		// userId).list();
	}

	@Override
	public BigDecimal getLoanTotal(Long userId, String type) {
		if ("10".equals(type)) {
			// 近10天借款总额
			String sql = "select SUM(info.userMoney) from pl_bid_info info where DATE_SUB(CURDATE(),INTERVAL 10 day) <= date(info.bidtime) and info.userId=?";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, userId).uniqueResult();
		} else if ("intent".equals(type)) {
			// String sql=" select SUM(bp.notMoney) from bp_fund_intent "
			String sql = "select SUM(bp.notMoney) from bp_fund_intent as bp where bp.fundType='principalRepayment' and bp.orderNo in(SELECT p.orderNo FROM `pl_bid_info`  as p  left  join pl_bid_plan  as plan on ( p.bidId=plan.bidId) where p.state=1 and p.userId=? and plan.isLoan=1)";
			// String sql =
			// "select SUM(info.userMoney) from pl_bid_info info where info.userId=?";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, userId).uniqueResult();
		} else if ("userMoney".equals(type)) {
			// String sql =
			// "select SUM(bp.notMoney) from bp_fund_intent as bp where bp.fundType='principalRepayment' and bp.bidPlanId in(SELECT p.bidId FROM `pl_bid_info`  as p  left  join pl_bid_plan  as plan on ( p.bidId=plan.bidId) where p.state=1 and p.userId=?  and plan.isLoan=1) ";
			String sql = "select SUM(bp.notMoney) from bp_fund_intent as bp where bp.fundType='principalRepayment' and bp.bidPlanId in(SELECT p.bidId FROM `pl_bid_info`  as p  left  join pl_bid_plan  as plan on ( p.bidId=plan.bidId) where p.state=1  and plan.isLoan=1)  and bp.projectId=? ";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, userId).uniqueResult();
		} else {
			// 借款总额
			// String sql
			// ="select SUM(info.userMoney) from pl_bid_info info where info.userId=? and info.state>0";
			String sql = "select SUM(bp.notMoney) from bp_fund_intent as bp inner join pl_bid_info as info on bp.bidPlanId=info.bidId where info.userId=? and info.state>0 and (bp.fundType='loanInterest' or bp.fundType='principalRepayment')";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, userId).uniqueResult();
		}

	}

	@Override
	public List<PlBidInfo> getIntentInfo(Long bidId, String group) {
		String sql = "";
		if ("group".equals(group)) {
			sql = "select * from pl_bid_info as info where info.bidId=? and (info.state=1 or info.state=2) GROUP BY info.userId";
		} else {
			sql = "select * from pl_bid_info as info where info.bidId=? and (info.state=1 or info.state=2) ";
		}
		System.out.println("投标记录"+sql);
		return this.getSession().createSQLQuery(sql).addEntity(PlBidInfo.class)
				.setParameter(0, bidId).list();
	}

	//投资列表查询
	@Override
	public List<PlBidInfo>
	getListInfo(HttpServletRequest request,Integer start,Integer limit) {
		//String sql = "";

		StringBuffer buff = new StringBuffer("SELECT bp.telphone,info.userMoney,info.bidtime from pl_bid_info as info left join bp_cust_member bp on info.userId=bp.id ");
		String group1 = request.getParameter("group");
		String bidId = request.getParameter("bidId");

		if ("group".equals(group1)) {
			buff.append("WHERE info.bidId= "+bidId+" and (info.state=1 or info.state=2) GROUP BY info.userId");
			//sql = "select info.userName,info.userMoney,info.bidtime from pl_bid_info
			// as info where info.bidId=? and (info.state=1 or info.state=2) GROUP BY info.userId";
		} else {
			buff.append("WHERE info.bidId= "+bidId+" and (info.state=1 or info.state=2)");
			//sql = "select info.userName,info.userMoney,info.bidtime from pl_bid_info as info where info.bidId=? and (info.state=1 or info.state=2) ";
		}
		buff.append(" order by info.bidtime desc");
		System.out.println("投标记录"+buff);
		if(start==null || limit ==null){
			return this.getSession().createSQLQuery(buff.toString()).
					addScalar("telphone",Hibernate.STRING).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					setResultTransformer(Transformers.aliasToBean(PlBidInfo.class)).list();
		}
			return this.getSession().createSQLQuery(buff.toString()).
					addScalar("telphone",Hibernate.STRING).
					addScalar("userMoney",Hibernate.BIG_DECIMAL).
					addScalar("bidtime",Hibernate.DATE).
					//.setParameter(0, bidId).list();
							setResultTransformer(Transformers.aliasToBean(PlBidInfo.class)).
							setFirstResult(start).setMaxResults(limit).list();

	}

	@Override
	public BigDecimal getUserMoneyGroup(Long bidId, Long userId) {
		String sql = "select SUM(info.userMoney) from pl_bid_info as info where info.userId=? and (info.state=1 or info.state=2) and info.bidId=?  GROUP BY info.userId ";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(
				0, userId).setParameter(1, bidId).uniqueResult();
	}

	@Override
	public List<PlBidInfo> getIntentInfo(Long bidId, Long userId) {
		String sql = "select * from pl_bid_info as p where p.state=1 and  p.userId=? and   p.bidId in(select info.bidPlanId from invest_person_info as info where  info.contractUrls!='' and info.bidPlanId=?  )";
		return this.getSession().createSQLQuery(sql).addEntity(PlBidInfo.class)
				.setParameter(0, userId).setParameter(1, bidId).list();
	}

	@Override
	public BigDecimal queryUserMoney(Long bidId, Long userId, String orderNo) {
		if (orderNo == null) {
			String sql = "select SUM(bid.userMoney) from pl_bid_info as bid where bid.bidId=? and bid.userId=?  and state=1";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, bidId).setParameter(1, userId)
					.uniqueResult();
		} else {
			String sql = "select SUM(bid.userMoney) from pl_bid_info as bid where bid.bidId=? and bid.userId=?  and bid.orderNo=? and state=1";
			return (BigDecimal) this.getSession().createSQLQuery(sql)
					.setParameter(0, bidId).setParameter(1, userId)
					.setParameter(2, orderNo).uniqueResult();
		}

	}

	@Override
	public List<PlBidInfo> getbyPersonAndPlan(Long id, String state, Pager pager) {
		String sql = "SELECT * from pl_bid_info info"
				+ " inner JOIN pl_bid_plan plan on info.bidId=plan.bidId and info.userId=?  ";
		if (state != null && !"".equals(state)) {
			if (state.equals("7")) {
				sql = sql + "and plan.state=7 and info.state=2";
			} else if (state.equals("-1")) {
				sql = sql + "and (plan.state=-1 or plan.state=3) and info.state=1";
			} else if (state.equals("10")) {
				sql = sql + "and plan.state=10 and info.state=2" ;
			} else if (state.equals("1")) {
				sql = sql
						+ "and (plan.state=1 or plan.state=2 or plan.state=4 or plan.state=6) and info.state=1";
			}
		}
		// if(pager!=null){
		// sql = sql+" limit " +
		// (pager.getPageNumber()-1)*pager.getPageSize()+1+","+pager.getPageNumber()*pager.getPageSize();
		// }
		sql = sql + " ORDER BY info.bidtime DESC";
		List<PlBidInfo> list = this.getSession().createSQLQuery(sql).addEntity(
				PlBidInfo.class).setParameter(0, id).list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao#getbyPersonAndPlan(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getbyPersonAndPlan(Long id, String planstate,
			String start, String limit) {
		String startsql="SELECT *";
		String sql =" from pl_bid_info info"+
		" INNER   JOIN pl_bid_plan plan on info.bidId=plan.bidId and info.userId=? ";
		if(planstate!=null&&!"".equals(planstate)){
			if(planstate.equals("7")){
				sql = sql+" and info.state=2  and plan.state=7";
			}else if(planstate.equals("-1")){
				//2015-6-12  查询失败包含流标PLBidInfo状态3 
				sql = sql+" and (info.state=1 or info.state=3) and (plan.state=-1 or plan.state=3)";
			}else if(planstate.equals("10")){
				sql = sql+" and info.state=1 and plan.state=10";
			}else if(planstate.equals("1")){
				sql = sql+" and info.state=1 and (plan.state=1 or plan.state=2 or plan.state=4 or plan.state=6)";
			}
		}
		sql = sql+" ORDER BY info.bidtime DESC";
		//查总数
		String countsql="SELECT count(*)";
		Integer count = Integer.valueOf(this.getSession().createSQLQuery(countsql+sql).setParameter(0, id).uniqueResult().toString());
		Map map = new HashMap<String, Object> ();
		map.put("count", count);
		sql = sql+" limit "+start +","+(Integer.valueOf(limit));
		System.out.println("========="+sql);
		//查数据
		List<PlBidInfo> list = this.getSession().createSQLQuery(startsql+sql).addEntity(PlBidInfo.class).setParameter(0, id).list();
		map.put("list", list);
		return map;
	}

	@Override
	public PlBidInfo getByNewOrdId(String ordId) {
		String hql = "from PlBidInfo p where p.newOrderNo=?";
		Object[] params = { ordId };
		return (PlBidInfo) findUnique(hql, params);
	}

	@Override
	public BigDecimal queryAllProfit(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT SUM(intent.afterMoney) + IF(intent.accrualMoney is null,0,intent.accrualMoney) from bp_fund_intent intent " +
				" WHERE intent.fundType  NOT in ('principalLending','consultationMoney','serviceMoney','principalRepayment') " +
				"and intent.investPersonId = ?  AND intent.isCheck = '0' and intent.isValid='0'";
		BigDecimal money = (BigDecimal)this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
		return money;
	}

	@Override
	public Integer queryBidCount(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM pl_bid_info info WHERE info.userId = ? AND info.state in ('1','2')";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public BigDecimal queryAddBidMoney(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(investMoney) FROM invest_person_info info WHERE info.investPersonId = ? and info.bidtype = '0'";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public BigDecimal queryAllLoanMoney(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(plan.bidMoney) FROM pl_bid_plan plan WHERE plan.reciverId = ? AND plan.state in ('7','10')";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryAllLoanAmount(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM pl_bid_plan plan WHERE plan.reciverId = ? AND plan.state in ('7','10')";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryBidingAmount(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = ? and plan.state =  '1'";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public BigDecimal queryBidingMoney(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(plan.bidMoney) FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = ? and plan.state =  '1'";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryUnback(String loginName) {
		String sql = "SELECT count(*) FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = ? and plan.state =  '7'";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public BigDecimal queryUnBackMoney(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(intent.notMoney) FROM bp_fund_intent intent LEFT JOIN pl_bid_plan plan ON intent.bidPlanId = plan.bidId" +
				" WHERE plan.receiverP2PAccountNumber = ? and plan.state = '7' and intent.isCheck = '0' and intent.isValid='0'";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryUnpublic(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM  pl_managemoney_plan plan  WHERE  plan.state = '1' and plan.moneyReceiver = ? and plan.startinInterestTime < NOW()";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryBiding(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM  pl_managemoney_plan plan  WHERE  plan.state = '1' and plan.moneyReceiver = ?";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryFull(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM  pl_managemoney_plan plan  WHERE  plan.state = '2' and plan.moneyReceiver = ?";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryBacking(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM  pl_managemoney_plan plan  WHERE  plan.state = '7' and plan.moneyReceiver = ?";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public Integer queryOk(Long  userId) {
		String sql = "SELECT count(*) FROM pl_managemoneyplan_buyinfo info  LEFT JOIN pl_managemoney_plan plan ON info.mmplanId = plan.mmplanId WHERE  plan.state = '10' and info.investPersonId = ?";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public BigDecimal queryAllBenifit(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(ass.afterMoney) FROM pl_mm_order_assigninterest ass LEFT JOIN pl_managemoney_plan plan on ass.mmplanId = plan.mmplanId WHERE  ass.isCheck = '0' AND ass.isValid = '0' AND ass.investPersonId = ? and ass.fundType NOT IN ('principalRepayment','serviceMoney','consultationMoney','principalLending') AND ass.keystr NOT IN ('mmproduce', 'experience');";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public Integer queryInvestCount(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM  pl_managemoneyplan_buyinfo info WHERE info.investPersonId = ? and info.state not in ('-2','0') and info.keystr not in ('experience','mmproduce');";
		return (Integer) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public BigDecimal queryAllInvest(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT sum(info.buyMoney) FROM  pl_managemoneyplan_buyinfo info WHERE info.investPersonId = ?" +
				" and info.state not in ('-2','0') and info.keystr not in ('experience','mmproduce')";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
	}

	@Override
	public BigDecimal UnbackRepayment(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT SUM(incomeMoney) FROM pl_mm_order_assigninterest ass LEFT JOIN pl_managemoney_plan plan on ass.mmplanId = ass.mmplanId WHERE plan.moneyReceiver = ? and ass.fundType = 'principalRepayment' AND ass.isCheck = '0' and ass.isValid = '0' and plan.state not in ('-2','0') and ass.keystr not in('mmproduce','experience') and ass.factDate is NULL";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public BigDecimal UnbackInterest(String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT SUM(incomeMoney) FROM pl_mm_order_assigninterest ass LEFT JOIN pl_managemoney_plan plan on ass.mmplanId = ass.mmplanId WHERE plan.moneyReceiver = ? and ass.fundType not in('principalRepayment','liquidatedDamages') AND ass.isCheck = '0' and ass.isValid = '0' and plan.state not in ('-2','0') and ass.keystr not in('mmproduce','experience') and ass.factDate is NULL";
		return (BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public BpPersonCenterData queryAllBid(Long userId) {
		String sql = "SELECT "
							+"table1.allBenefit,"
							+"table2.allBidTimes,"
							+"IFNULL(table3.allBidMoney,0) as allBidMoney"
						+" FROM"
							+"( select IFNULL(SUM(incomMoney),0) as allBenefit " +
									"from ob_account_deal_info " +
										"where accountId in(" +
											"select id from ob_system_account" +
												" where investmentPersonId="+userId+") " +
													"and transferType=3 and dealRecordStatus=2 ) AS table1,"
							+" ("
							+"SELECT "
							+"( "
								+"tableA.manageMoneyTime + tableB.BidTimes"
							+" ) AS allBidTimes"
						+" FROM"
							+"("
								+" SELECT"
									+" count(*) AS manageMoneyTime"
								+" FROM"
								+"	pl_managemoneyplan_buyinfo"
								+" WHERE"
								+"	investPersonId = '"+userId+"'"
								+" AND state = 2"
								+" AND keystr != 'experience'"
							+" ) AS tableA,"
							+" ("
								+"SELECT"
								+"	COUNT(*) AS BidTimes"
								+" FROM"
								+"	pl_bid_info info "
								+ " WHERE "
								+"	info.userId = '"+userId+"'"
								+" AND info.state IN ('2','7','10')"
							+" ) AS tableB"
							+") AS table2,"
							+"(" +
							" SELECT (b.buyMoney+IFNULL( a.buyMoney1,0)) as allBidMoney" +
							" from"+
							" ("+
							" SELECT SUM(investMoney) as buyMoney from invest_person_info as ifo INNER JOIN pl_bid_info as bif on  bif.state=2 and ifo.orderNo =bif.orderNo  and   ifo.investPersonId= '"+userId+"' "+
						" ) AS b,"+
						" ("+
							" SELECT "+
								"SUM(buyMoney) AS buyMoney1 "+
							" FROM"+
								" pl_managemoneyplan_buyinfo "+
							"WHERE "+
								" investPersonId = '"+userId+"' "+
							" AND state = 2 "+
							" AND keystr != 'experience'"+
						" ) AS a "+
									") AS table3";
        System.out.println("查询所用的标的信息"+sql);
		BpPersonCenterData data = new BpPersonCenterData();
		data =(BpPersonCenterData) this.getSession().createSQLQuery(sql).
										addScalar("allBenefit", Hibernate.BIG_DECIMAL).
										addScalar("allBidTimes", Hibernate.INTEGER).
										addScalar("allBidMoney",Hibernate.BIG_DECIMAL).
										setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
		return data;


	}

	@Override
	public BpPersonCenterData queryAllManage(Long userId, String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT"+
						" tableA.allBenefit,"+
						" tableB.allBidTimes,"+
						" tableC.allBidMoney,"+
						" tableD.unbackRepayment,"+
						" tableE.unbackInterest"+
					" FROM"+
						" ("+
							"SELECT"+
								" sum(ass.afterMoney) AS allBenefit"+
							" FROM"+
								" pl_mm_order_assigninterest ass"+
							" LEFT JOIN pl_managemoney_plan plan ON ass.mmplanId = plan.mmplanId"+
							" WHERE"+
								" ass.isCheck = '0'"+
							" AND ass.isValid = '0'"+
							" AND ass.investPersonId = "+userId+""+
							" AND ass.fundType NOT IN ("+
								"'principalRepayment',"+
								"'serviceMoney',"+
								"'consultationMoney',"+
								"'principalLending'"+
							")"+
							" AND ass.keystr NOT IN ('mmproduce', 'experience')"+
						") AS tableA,"+
						"("+
							"SELECT"+
								" COUNT(*) AS allBidTimes"+
							" FROM"+
								" pl_managemoneyplan_buyinfo info"+
							" WHERE"+
								" info.investPersonId = "+userId+""+
							" AND info.state NOT IN ('-2', '0')"+
							" AND info.keystr NOT IN ('experience', 'mmproduce')"+
						") AS tableB,"+
						"("+
							" SELECT"+
								" sum(info.buyMoney) AS allBidMoney"+
							" FROM "+
								" pl_managemoneyplan_buyinfo info"+
							" WHERE"+
								" info.investPersonId = "+userId+""+
							" AND info.state NOT IN ('-2', '0')"+
							" AND info.keystr NOT IN ('experience', 'mmproduce')"+
						" ) AS tableC,"+
						" ("+
							" SELECT"+
								" SUM(incomeMoney) AS unbackRepayment"+
							" FROM"+
								" pl_mm_order_assigninterest ass"+
							" LEFT JOIN pl_managemoney_plan plan ON ass.mmplanId = ass.mmplanId"+
							" WHERE"+
								" plan.moneyReceiver = '"+loginName+"'"+
							" AND ass.fundType = 'principalRepayment'"+
							" AND ass.isCheck = '0'"+
							" AND ass.isValid = '0'"+
							" AND plan.state NOT IN ('-2', '0')"+
							" AND ass.keystr NOT IN ('mmproduce', 'experience')"+
							" AND ass.factDate IS NULL"+
						") AS tableD,"+
						"("+
							" SELECT"+
								" SUM(incomeMoney) AS unbackInterest"+
							" FROM"+
								" pl_mm_order_assigninterest ass"+
							" LEFT JOIN pl_managemoney_plan plan ON ass.mmplanId = ass.mmplanId"+
							" WHERE"+
								" plan.moneyReceiver = '"+loginName+"'"+
							" AND ass.fundType NOT IN ("+
								"'principalRepayment',"+
								"'liquidatedDamages'"+
							")"+
							" AND ass.isCheck = '0'"+
							" AND ass.isValid = '0'"+
							" AND plan.state NOT IN ('-2', '0')"+
							" AND ass.keystr NOT IN ('mmproduce', 'experience')"+
							" AND ass.factDate IS NULL"+
						") AS tableE";
		            System.out.println("我的理财的数据"+sql);
					BpPersonCenterData data = new BpPersonCenterData();
					data =(BpPersonCenterData) this.getSession().createSQLQuery(sql).
													addScalar("allBenefit", Hibernate.BIG_DECIMAL).
													addScalar("allBidTimes", Hibernate.INTEGER).
													addScalar("allBidMoney",Hibernate.BIG_DECIMAL).
													addScalar("unbackRepayment",Hibernate.BIG_DECIMAL).
													addScalar("unbackInterest",Hibernate.BIG_DECIMAL).
													setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
					return data;
			}

	@Override
	public BpPersonCenterData queryAllLc(Long userId, String loginName) {
		// TODO Auto-generated method stub
		String sql = "SELECT tableA.unPublic as unPublic," +
				" tableB.bidingCount as bidingCount," +
				" tableC.bidFullCount as bidFullCount," +
				" tableD.backingCount as backingCount," +
				" tableE.complateCount as complateCount," +
				" tableF.unbackRepayment as unbackRepayment," +
				" tableG.unbackInterest as unbackInterest"+
				" FROM " +
				" (" +
				" SELECT " +
				" count(*) AS unPublic " +
				" FROM pl_managemoney_plan plan" +
				"  WHERE plan.state = '1' AND plan.moneyReceiver = '"+loginName+"' 	AND plan.buyStartTime > NOW() ) AS tableA," +
				" ("+
		        " SELECT count(*) AS bidingCount " +
		        " FROM pl_managemoney_plan plan " +
		        " WHERE plan.state = '1' and  plan.buyStartTime < NOW()  AND plan.moneyReceiver = '"+loginName+"' ) AS tableB," +
		        " (" +
		        " SELECT"+
                " count(*) AS bidFullCount " +
                " FROM pl_managemoney_plan plan " +
                " WHERE plan.state = '2' AND plan.moneyReceiver = '"+loginName+"' ) AS tableC," +
                " ("+
                " SELECT count(*) AS backingCount " +
                " FROM pl_managemoney_plan plan WHERE plan.state = '7' AND plan.moneyReceiver = '"+loginName+"' ) AS tableD,"+
                " (" +
                "  SELECT count(*) AS complateCount " +
                "  from pl_managemoney_plan plan " +
                "  WHERE plan.state = '10'"+
                " AND plan.moneyReceiver = '"+loginName+"' ) AS tableE," +
                " ( " +
                " SELECT SUM(incomeMoney) AS unbackRepayment " +
                "  FROM pl_mm_order_assigninterest ass " +
                " LEFT JOIN pl_managemoney_plan plan ON plan.mmplanId = ass.mmplanId"+
                " WHERE plan.moneyReceiver = '"+loginName+"' AND ass.fundType = 'principalRepayment' " +
                " AND ass.isCheck = '0' AND ass.isValid = '0' AND plan.state NOT IN ('-2', '0') " +
                " AND ass.keystr NOT IN ('mmproduce', 'experience')"+
                " AND ass.factDate IS NULL ) AS tableF," +
                "(" +
                "  SELECT SUM(incomeMoney) as unbackInterest " +
                " FROM pl_mm_order_assigninterest ass " +
                " LEFT JOIN pl_managemoney_plan plan ON plan.mmplanId = ass.mmplanId"+
                " WHERE plan.moneyReceiver = '"+loginName+"' AND ass.fundType NOT IN ( 'principalRepayment', 'liquidatedDamages' ) AND ass.isCheck = '0' AND ass.isValid = '0' AND plan.state NOT IN ('-2', '0')"+
                " AND ass.keystr NOT IN ('mmproduce', 'experience') AND ass.factDate IS NULL ) as tableG";
		System.out.println("查询的债权是"+sql);
		BpPersonCenterData data = new BpPersonCenterData();
		data =(BpPersonCenterData) this.getSession().createSQLQuery(sql).
										addScalar("unPublic", Hibernate.INTEGER).
										addScalar("bidingCount", Hibernate.INTEGER).
										addScalar("bidFullCount", Hibernate.INTEGER).
										addScalar("backingCount", Hibernate.INTEGER).
										addScalar("complateCount",Hibernate.INTEGER).
										addScalar("unbackRepayment",Hibernate.BIG_DECIMAL).
										addScalar("unbackInterest",Hibernate.BIG_DECIMAL).
										setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
		return data;
	}

	@Override
	public BpPersonCenterData queryAllSale(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT tableB.canTransferSale," +
				" tableA.transferedMoney," +
				" (tableA.transferedAmount+tableC.buyedCount) AS successDeals " +
				" from (SELECT SUM(a.sumMoney) as transferedMoney, count(*) as transferedAmount " +
				" FROM " +
				" ( SELECT * FROM pl_bid_sale AS pbs WHERE pbs.saleStatus IN (4) AND pbs.outCustID = "+userId+" ) AS a" +
				" LEFT JOIN pl_bid_info pbi ON pbi.id = a.bidInfoID " +
				" LEFT JOIN pl_bid_plan plan ON a.bidPlanID = plan.bidId " +
				" LEFT JOIN bp_business_dir_pro AS bdir ON bdir.bdirProId = plan.bdirProId " +
				" AND plan.proType = 'B_Dir'" +
				" LEFT JOIN bp_business_or_pro AS bor ON bor.borProId = plan.borProId" +
				" AND plan.proType = 'B_Or' " +
				" LEFT JOIN bp_persion_dir_pro AS pdir ON pdir.pDirProId = plan.pDirProId " +
				" AND plan.proType = 'P_Dir' " +
				" LEFT JOIN bp_persion_or_pro AS por ON por.pOrProId = plan.pOrProId" +
				" AND plan.proType = 'P_Or' " +
				" LEFT JOIN bp_fund_project AS sl ON ( ( (sl.id = bdir.moneyPlanId)" +
				" OR (sl.id = bor.moneyPlanId) OR (sl.id = pdir.moneyPlanId)" +
				" OR (sl.id = por.moneyPlanId) ) )" +
				" ORDER BY a.saleDealTime, a.saleCloseTime DESC ) as tableA," +
				" ( SELECT sum(f.userMoney) as canTransferSale," +
				" ( f.userMoney - IFNULL( (SELECT sum(sfi1.afterMoney)"+
				" FROM bp_fund_intent sfi1 " +
				" WHERE sfi1.orderNo = f.orderNo" +
				" AND sfi1.fundType = 'principalRepayment'" +
				" AND sfi1.isCheck = 0 AND sfi1.isValid = 0 ),0 ) ) AS saleMoney " +
				" FROM ( SELECT * FROM ( SELECT pbi.id AS bidInfoID, pbi.bidId AS bidPlanID, pbi.orderNo, pbi.userMoney, " +
				" ( SELECT count(*) FROM pl_bid_sale pbs " +
				" WHERE pbs.bidInfoID = pbi.id AND pbs.outCustID = "+userId+" " +
				" AND pbs.saleStatus IN (1, 3) ) AS tansferingcount," +
				" IFNULL( ( SELECT sum(sfi.notMoney) FROM bp_fund_intent sfi " +
				" WHERE sfi.orderNo = pbi.orderNo AND sfi.fundType = 'principalRepayment' " +
				" AND sfi.isCheck = 0 AND sfi.isValid = 0 AND sfi.notMoney != 0 ), 0 ) AS saleMoney," +
				" pbi.id FROM pl_bid_info AS pbi " +
				" WHERE pbi.isToObligatoryRightChildren IS NULL AND " +
				" pbi.bidId IN ( SELECT plan.bidId FROM pl_bid_plan AS plan WHERE plan.state IN (7, 10) )" +
				" AND ( ( pbi.userId = "+userId+" AND pbi.newInvestPersonId IS NULL )" +
				" OR pbi.newInvestPersonId = "+userId+" )) AS a" +
				" WHERE a.tansferingcount = 0 AND a.saleMoney > 0 ORDER BY a.id ASC ) AS f " +
				" LEFT JOIN pl_bid_plan plan ON plan.bidId = f.bidPlanID" +
				" LEFT JOIN pl_bid_sale pbs1 ON f.bidInfoID = pbs1.bidInfoID AND pbs1.outCustID = "+userId+" " +
				" AND pbs1.saleStatus = 0 LEFT JOIN bp_business_dir_pro AS bdir ON bdir.bdirProId = plan.bdirProId" +
				" AND plan.proType = 'B_Dir' LEFT JOIN bp_business_or_pro AS bor ON bor.borProId = plan.borProId" +
				" AND plan.proType = 'B_Or' LEFT JOIN bp_persion_dir_pro AS pdir ON pdir.pDirProId = plan.pDirProId " +
				" AND plan.proType = 'P_Dir' LEFT JOIN bp_persion_or_pro AS por ON por.pOrProId = plan.pOrProId " +
				" AND plan.proType = 'P_Or' LEFT JOIN bp_fund_project AS sl ON ( ( (sl.id = bdir.moneyPlanId) OR (sl.id = bor.moneyPlanId) " +
				" OR (sl.id = pdir.moneyPlanId) OR (sl.id = por.moneyPlanId) )) " +
				" ORDER BY f.bidInfoID ASC ) AS tableB, " +
				" ( SELECT COUNT(*) as buyedCount FROM ( SELECT * FROM pl_bid_sale AS pbs WHERE pbs.saleStatus = 4 " +
				" AND pbs.inCustID ="+userId+" ) AS a LEFT JOIN pl_bid_info pbi" +
				" ON pbi.id = a.bidInfoID LEFT JOIN pl_bid_plan plan ON a.bidPlanID = plan.bidId " +
				" LEFT JOIN bp_business_dir_pro AS bdir ON bdir.bdirProId = plan.bdirProId AND plan.proType = 'B_Dir' " +
				" LEFT JOIN bp_business_or_pro AS bor ON bor.borProId = plan.borProId AND plan.proType = 'B_Or' " +
				" LEFT JOIN bp_persion_dir_pro AS pdir ON pdir.pDirProId = plan.pDirProId AND plan.proType = 'P_Dir' " +
				" LEFT JOIN bp_persion_or_pro AS por ON por.pOrProId = plan.pOrProId AND plan.proType = 'P_Or' " +
				" LEFT JOIN bp_fund_project AS sl ON (( (sl.id = bdir.moneyPlanId) OR (sl.id = bor.moneyPlanId) " +
				" OR (sl.id = pdir.moneyPlanId) OR (sl.id = por.moneyPlanId))) " +
				" ORDER BY a.saleDealTime, a.saleCloseTime DESC) as tableC";
		BpPersonCenterData data = new BpPersonCenterData();
		data =(BpPersonCenterData) this.getSession().createSQLQuery(sql).
										addScalar("canTransferSale", Hibernate.BIG_DECIMAL).
										addScalar("transferedMoney", Hibernate.BIG_DECIMAL).
										addScalar("successDeals",Hibernate.INTEGER).
										setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
		return data;

	}

	@Override
	public BpPersonCenterData queryAllJk(String loginName,Long userId) {
		String sql = "SELECT tableA.allLoanMoney, " +
				" tableB.allLoanNums," +
				" tableC.numsOnBiding," +
				" tableD.moneyOnBiding," +
				" tableE.unBackNums," +
				" tableF.unBackMoney " +
				"FROM " +
				"( SELECT sum(plan.bidMoney) as allLoanMoney FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state IN ('7', '10') ) AS tableA," +
				" ( SELECT count(*) as allLoanNums FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state IN ('7', '10') ) AS tableB, " +
				"( SELECT count(*) as numsOnBiding FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state in ('1','2','6') ) AS tableC, " +
				"( SELECT IF(sum(plan.bidMoney) is null,0,sum(plan.bidMoney)) AS moneyOnBiding FROM pl_bid_plan plan WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state in ('1','2','6') ) AS tableD, " +
				"( SELECT count(*) as unBackNums FROM pl_bid_plan plan  WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state = '7' ) AS tableE, " +
				"(SELECT  (c.money1 + d.composary) as unBackMoney FROM  (SELECT sum(intent.notMoney) as money1  FROM bp_fund_intent intent LEFT JOIN pl_bid_plan plan ON intent.bidPlanId = plan.bidId WHERE plan.receiverP2PAccountNumber = '"+loginName+"' AND plan.state = '7' AND intent.isCheck = '0' AND intent.isValid = '0') as c,(SELECT  (b.allMoney -  a.backedMoney) as composary from (SELECT IF(SUM(flow.backCompensatoryMoney)>0,SUM(flow.backCompensatoryMoney),0) AS backedMoney from pl_bid_compensatory_flow flow LEFT JOIN pl_bid_compensatory com  on flow.compensatoryId = com.id WHERE com.loanerp2pId = '"+userId+"') as a, ( SELECT IF(sum(com.compensatoryMoney)>0,sum(com.compensatoryMoney),0) AS allMoney from pl_bid_compensatory com WHERE com.loanerp2pId = '"+userId+"') as b ) as d    ) AS tableF";
		System.out.println("查询的sql"+sql);
		
		BpPersonCenterData data = new BpPersonCenterData();
		data =(BpPersonCenterData) this.getSession().createSQLQuery(sql).
										addScalar("allLoanMoney", Hibernate.BIG_DECIMAL).
										addScalar("allLoanNums", Hibernate.INTEGER).
										addScalar("moneyOnBiding",Hibernate.BIG_DECIMAL).
										addScalar("numsOnBiding",Hibernate.INTEGER).
										addScalar("unBackNums",Hibernate.INTEGER).
										addScalar("unBackMoney",Hibernate.BIG_DECIMAL).
										setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).uniqueResult();
		return data;

	}

	@Override
	public BpPersonCenterData myRecommend(BpCustMember mem)  {
		// TODO Auto-generated method stub
		BpPersonCenterData data = new BpPersonCenterData();
		if(mem.getPlainpassword()!=null&&mem.getRegistrationDate()!=null){
			String sql = "SELECT table1.allRecommend AS allRecommend," +
			"table2.monthRecommend as monthRecommend " +
			"FROM " +
			"( " +
			"SELECT COUNT(*) AS allRecommend" +
			" FROM bp_cust_member member " +
			" WHERE member.recommandPerson = '"+mem.getPlainpassword()+"' and member.id != '"+mem.getId()+"' ) AS table1," +
			" (SELECT COUNT(*) AS monthRecommend " +
			" FROM bp_cust_member mem " +
			" WHERE mem.recommandPerson = '"+mem.getPlainpassword()+"' " +
			" AND DATE_SUB(CURDATE(), INTERVAL 1 MONTH) <=  mem.registrationDate "+
	        " ) AS table2";
		data = (BpPersonCenterData) this.getSession().createSQLQuery(sql).
				addScalar("allRecommend", Hibernate.INTEGER).
				addScalar("monthRecommend", Hibernate.INTEGER).
				setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).
				uniqueResult();
		}		
		return data;
	}
	
	public List<PlBidInfo> weeksort(){
		String sql = "select sum(userMoney) as userMoney,userName as userName from pl_bid_info where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= bidtime GROUP BY userName ORDER BY userMoney desc limit 0,9";
		System.out.println(sql);
		List<PlBidInfo> list = this.getSession().createSQLQuery(sql).
								addScalar("userMoney", Hibernate.BIG_DECIMAL).
								addScalar("userName", Hibernate.STRING).
								setResultTransformer(Transformers.aliasToBean(PlBidInfo.class)).list();
		return list;
	}
	
	public List<PlBidInfo> monthsort(){
		String sql = "select sum(userMoney) as userMoney,userName as userName from pl_bid_info where DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= bidtime GROUP BY userName ORDER BY userMoney desc limit 0,9";
		List<PlBidInfo> list = this.getSession().createSQLQuery(sql).
								addScalar("userMoney", Hibernate.BIG_DECIMAL).
								addScalar("userName", Hibernate.STRING).
								setResultTransformer(Transformers.aliasToBean(PlBidInfo.class)).list();
		return list;
	}
	
	public List<PlBidInfo> allsort(){
		String sql = "select sum(userMoney) as userMoney,userName as userName from pl_bid_info  GROUP BY userName ORDER BY userMoney desc limit 0,9";
		List<PlBidInfo> list = this.getSession().createSQLQuery(sql).
		addScalar("userMoney", Hibernate.BIG_DECIMAL).
		addScalar("userName", Hibernate.STRING).
		setResultTransformer(Transformers.aliasToBean(PlBidInfo.class)).list();
		return list;
	}
}