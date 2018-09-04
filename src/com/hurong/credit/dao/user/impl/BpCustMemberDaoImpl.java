package com.hurong.credit.dao.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.mobile.InviteDetail;
import com.hurong.credit.model.mobile.InvitePersonDetail;
import com.hurong.credit.model.webFeedBack.FeedBack;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;


import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCustMemberDaoImpl extends BaseDaoImpl<BpCustMember> implements BpCustMemberDao{

	public BpCustMemberDaoImpl() {
		super(BpCustMember.class);
	}

	@Override
	public BpCustMember login(String loginname, String password) {
		// TODO Auto-generated method stub
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where (isDelete!=1 or isDelete is null) and (loginname= ? or email = ? or telphone = ? ) and password = ?");
		Object[] params = {loginname,loginname,loginname,password};
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}

	@Override
	public BpCustMember isExist(String loginname) {
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where (isDelete!=1 or isDelete is null) and loginname= ? ");
		Object[] params = {loginname};
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	@Override
	public BpCustMember isExistPhone(String phone){
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where (isDelete!=1 or isDelete is null) and telphone= ? ");
		Object[] params = {phone};
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	/**
	 * 判断邮箱是否存在
	 */
	@Override
	public BpCustMember isExistEmail(String email) {
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where (isDelete!=1 or isDelete is null) and email= ? ");
		Object[] params = {email};
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	/**
	 * 判断邀请的验证码是否存在
	 */
	@Override
	public BpCustMember isRecommandPerson(String recommandPerson) {
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where  plainpassword= ?");
		//hql.append(" where  id= '"+recommandPerson+"'");
		Object[] params = {recommandPerson};
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	/**
	 * 获取所有没被禁用和删除的用户
	 */
	@Override
	public List<BpCustMember> getAllUserful() {
		List<BpCustMember> list=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where (isDelete!=1 or isDelete is null) and (isForbidden!=1 or isForbidden is null)");
		 list = findByHql(hql.toString());
		
		return list;
	}

	/**
	 * 判断该身份证是否已被其他用户认证
	 */
	@Override
	public BpCustMember isExistCode(String cardcode,Long id) {
		BpCustMember bpCustMember=null;
		Object[] params = new Object[] {cardcode};
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where  cardcode= ?  " );
		if (id != null) {
			hql.append("and id != ?");
			params = new Object[] { cardcode,id };
		}

		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	
	/**
	 * 判断该邮箱是否已被其他用户认证
	 */
	public BpCustMember isExistEmailOther(String email,Long id){
		BpCustMember bpCustMember=null;
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where  email= ? ");
		Object[] params = new Object[]{email};
		if (null != id){
			hql.append(" and id!= ? ");
			params = new Object[]{email,id};

		}
		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}
	
	/**
	 * 判断该手机是否已被其他用户认证
	 */
	public BpCustMember isExistPhoneOther(String telphone,Long id){
		BpCustMember bpCustMember=null;
		Object[] params = {telphone};
		StringBuffer hql = new StringBuffer(" from BpCustMember ");
		hql.append(" where  telphone= ? ");
		if (null != id){
			hql.append(" and id!= ? ");
			params = new Object[]{telphone,id};
		}

		List<BpCustMember> list = findByHql(hql.toString(),params);
		if(list.size()>0){
			bpCustMember=list.get(0);
		}
		return bpCustMember;
	}

	@Override
	public BpCustMember getMemberByPIdAndFlag(String flag, String pid) {

		String hql = "from BpCustMember cust where cust.thirdPayFlagId=? ";
		Object[] params = {flag};
		return (BpCustMember)findUnique(hql, params);
	
	}

	@Override
	public BpCustMember getMemberUserName(String userName, String cardNum) {
		String hql = "from BpCustMember cust where cust.loginname=? ";
		Object[] params = {userName};
		return (BpCustMember)findUnique(hql, params);
	}

	@Override
	public BpCustMember getMemberUserName(String thirdPayFlagId) {
		String hql = "from BpCustMember cust where cust.thirdPayFlagId=? ";
		Object[] params = {thirdPayFlagId};
		return (BpCustMember)findUnique(hql, params);
	}

	@Override
	public BpCustMember valadateInfo(String flag,String loginName, String telphone,
			String cardNumber, String email) {
		String hql = "from BpCustMember cust where ";
		BpCustMember bpCustMember = null;
		if("loginName".equals(flag)){
			hql += " cust.loginname = ? ";
			bpCustMember =  (BpCustMember) getSession().createQuery(hql).setString(0, loginName).uniqueResult();
			//bpCustMember =  (BpCustMember) findUnique(hql, new Object[]{loginName});
			
		}else if("telphone".equals(flag)){
			hql += " cust.telphone = ? ";
			bpCustMember =  (BpCustMember) getSession().createQuery(hql).setString(0, telphone).uniqueResult();
			//bpCustMember = (BpCustMember) findUnique(hql, new Object[]{telphone});
		}else if("cardNumber".equals(flag)){
			hql += " cust.cardcode = ? ";
			bpCustMember =  (BpCustMember) getSession().createQuery(hql).setString(0, cardNumber).uniqueResult();
			//bpCustMember = (BpCustMember) findUnique(hql, new Object[]{cardNumber});
		}else{
			hql += " cust.email = ? ";
			bpCustMember =  (BpCustMember) getSession().createQuery(hql).setString(0, email).uniqueResult();
			//bpCustMember =(BpCustMember) findUnique(hql, new Object[]{email});
		}
		return bpCustMember;
	}


	@Override
	public BpCustMember getMemberByEmail(String email) {
		String hql = "from BpCustMember cust where cust.email=? ";
		Object[] params = {email};
		List<BpCustMember> list = findByHql(hql, params);
		if (null != list && list.size() >0) {
            return list.get(0);
        }else {
            return null;
        }
	}

	@Override
	public BpCustMember getMemberByPhone(String telphone) {
		String hql = "from BpCustMember cust where cust.telphone=? ";
		Object[] params = {telphone};
		return (BpCustMember)findUnique(hql, params);
	}


	@Override
	public BpCustMember getByThirdPayId(String platformUserNo) {
		String hql = "from BpCustMember cust where cust.thirdPayFlagId=?";
		return  (BpCustMember) getSession().createQuery(hql).setString(0, platformUserNo).uniqueResult();
	}

	@Override
	public BpCustMember getByQQ(String qq) {
		String hql = "from BpCustMember mem where mem.qq=? ";
		Object[] params = {qq};
		return (BpCustMember)findUnique(hql, params);
	}
	@Override
	public BpCustMember getBySina(String sina) {
		String hql = "from BpCustMember mem where mem.sinawb=? ";
		Object[] params = {sina};
		return (BpCustMember)findUnique(hql, params);
	}
	@Override
	public BpCustMember getInviter(String sina) {
		
		String sql="SELECT * FROM bp_cust_member p WHERE p.plainpassword =("
				+ "SELECT p1.recommandPerson FROM bp_cust_member p1 WHERE p1.plainpassword='"+sina+"');";
		
		return (BpCustMember) this.getSession().createSQLQuery(sql).addEntity(BpCustMember.class).uniqueResult();
	}
	@Override
	public List<com.hurong.credit.model.user.BpCustMember> referrer2Excel(String begintime,String endtime) {
		String hql = "SELECT * FROM	bp_cust_member b " +
				"INNER JOIN (" +
				"SELECT	 recommandPerson FROM " +
				"bp_cust_member	WHERE " +
				" registrationDate BETWEEN '"+begintime+"'	AND '"+endtime+"'" +
				") b2 ON b.id = b2.recommandPerson ORDER BY	b.loginname;";
		return (List<com.hurong.credit.model.user.BpCustMember>)this.getSession().createSQLQuery(hql).addEntity(BpCustMember.class).list();
	}

	@Override
	public void executeSql(String sql) {
		this.jdbcTemplate.update(sql);
	}
	
	
	
	/**
	 * 通过投资人ID获取该客户托管账户余额
	 * @param investmentPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccountLeft(Long investmentPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(totalMoney) from ob_system_account where investmentPersonId=?").setLong(0, investmentPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyBidFrozen(Long investPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(iinfo.investMoney) from invest_person_info as iinfo  INNER JOIN pl_bid_plan as pplan on(pplan.bidId=iinfo.bidPlanId) where iinfo.investPersonId=? and pplan.state in (1,2,6,4) ").setLong(0, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyMmplanFrozen(Long investPersonId) {
		//return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(iif.buyMoney)+SUM(iif.joinMoney) FROM pl_managemoneyplan_buyinfo as iif INNER JOIN pl_managemoney_plan as plan on   iif.investPersonId=? and iif.mmplanId=plan.mmplanId and plan.state in(1,2,5,6) and iif.keystr !='experience'").setLong(0, investPersonId).uniqueResult();
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(iif.buyMoney) + SUM(iif.joinMoney) FROM pl_managemoneyplan_buyinfo AS iif WHERE iif.investPersonId =? AND iif.state IN (1) AND iif.keystr not in('mmproduce','experience')").setLong(0, investPersonId).uniqueResult();
	}
	
	
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第一部分：散标投资的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll(Long investPersonId) {
		//未发生债权交易的待回收本金和利息
		BigDecimal notMoney1 = (BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(notMoney),0) from pl_bid_info as info LEFT JOIN bp_fund_intent as bp on info.orderNo=bp.orderNo " +
				" where  bp.isCheck=0 and info.userId=? and (bp.fundType = 'principalRepayment' OR bp.fundType = 'loanInterest' ) and info.newInvestPersonId is null").setLong(0, investPersonId).uniqueResult();
		//购买债权交易的待回收本金和利息
		BigDecimal notMoney2 = (BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(notMoney),0) from pl_bid_info as info LEFT JOIN bp_fund_intent as bp on info.orderNo=bp.orderNo " +
				" where  bp.isCheck=0 and info.newInvestPersonId=? and (bp.fundType = 'principalRepayment' OR bp.fundType = 'loanInterest' ) and info.newInvestPersonId is not null").setLong(0, investPersonId).uniqueResult();
		return notMoney1.add(notMoney2);
	}
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第二部分：理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll1(Long investPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(incomeMoney) from  pl_mm_order_assigninterest where investPersonId=? and (fundType='principalRepayment' or fundType='loanInterest') and isCheck=0 and factDate is  null").setLong(0, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人待还借款总额(款项计划表中该借款人所有待还款型之和)
	 * @param receiverP2PaccountNumber
	 * @return
	 */
	public BigDecimal getMoneyDueinAll(String receiverP2PaccountNumber) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(intent.notMoney) from pl_bid_plan as plan INNER  JOIN bp_fund_intent as intent on plan.receiverP2PaccountNumber =? and plan.bidId=intent.bidPlanId and intent.fundType<>'principalLending' and intent.isCheck=0").setString(0, receiverP2PaccountNumber).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第一部分是散标投资的投资额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest(Long investPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(investMoney) from invest_person_info as ifo INNER JOIN pl_bid_info as bif on  bif.state=2 and ifo.orderNo =bif.orderNo  and   ifo.investPersonId=?").setLong(0, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第二部分是理财计划的本金和利息
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest1(Long investPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(buyMoney) from pl_managemoneyplan_buyinfo where investPersonId=? and (state = 2 or state=10) and keystr !='experience'").setLong(0, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第一部分散标投资的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen(Long investPersonId) {
		return (BigInteger)this.getSession().createSQLQuery("SELECT (tableA.count+tableB.count1) as totalCount FROM ( SELECT count(*) AS count FROM invest_person_info AS iinfo INNER JOIN pl_bid_plan AS pplan ON iinfo.investPersonId = ? AND pplan.state IN (1, 2, 6, 4) AND iinfo.bidPlanId = pplan.bidId ) AS tableA,(SELECT count(*) as count1 FROM pl_managemoneyplan_buyinfo AS iif WHERE iif.investPersonId =? AND iif.state IN (1) AND iif.keystr NOT IN ('mmproduce', 'experience') ) as tableB").setLong(0, investPersonId).setLong(1, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第二部分理财计划的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen1(Long investPersonId) {
		return (BigInteger)this.getSession().createSQLQuery("SELECT count(*)  FROM pl_managemoneyplan_buyinfo AS iif WHERE iif.investPersonId =? AND iif.state IN (1) AND iif.keystr not in('mmproduce','experience')").setLong(0, investPersonId).uniqueResult();
	}
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第一部分散标投资的投资笔数)
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountInvestBack(Long investPersonId) {
		 //得到未发生债权交易的回款笔数
		 Integer num1 = Integer.valueOf(this.getSession().createSQLQuery("SELECT	COUNT(*) FROM pl_bid_info AS iinfo INNER JOIN pl_bid_plan AS plan ON iinfo.userId =? AND plan.bidId = iinfo.bidId AND plan.state = 7 and iinfo.state=2 and iinfo.newInvestPersonId is null").setLong(0, investPersonId).uniqueResult().toString());
		 //得到购买债权的回款笔数
		 Integer num2 = Integer.valueOf(this.getSession().createSQLQuery("select COUNT(*) from pl_bid_info as info LEFT JOIN pl_bid_plan as plan on info.bidId=plan.bidId where plan.state=7 and info.newInvestPersonId is not null and info.newInvestPersonId=?").setLong(0, investPersonId).uniqueResult().toString());
		 return num1+num2;
	}
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第二部分理财计划的投资笔数)
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountInvestBack1(Long investPersonId) {
		return Integer.valueOf(this.getSession().createSQLQuery("SELECT COUNT(*) from pl_managemoneyplan_buyinfo  as minfo  INNER JOIN pl_managemoney_plan as plan on minfo.investPersonId=? and minfo.mmplanId=plan.mmplanId and (plan.state = 7 OR plan.state = 2) and minfo.state=2").setLong(0, investPersonId).uniqueResult().toString());
	}
	
	/**
	 * 通过投资人ID获取该投资人招标中借款笔数
	 * @return
	 */
	public Integer getCountBidLoan(String receiverP2PaccountNumber) {
		return Integer.valueOf(this.getSession().createSQLQuery("SELECT COUNT(*) from pl_bid_plan where receiverP2PaccountNumber=? and (state=1 or state=2  or  state=6)").setString(0, receiverP2PaccountNumber).uniqueResult().toString());
	}
	
	/**
	 * 通过投资人ID获取该投资人还款中借款笔数
	 * @return
	 */
	public Integer getCountRepaymentLoan(String receiverP2PaccountNumber) {
		return Integer.valueOf(this.getSession().createSQLQuery("SELECT COUNT(*) from pl_bid_plan where receiverP2PaccountNumber=? and state=7").setString(0, receiverP2PaccountNumber).uniqueResult().toString());
	}
	
	/**
	 * 通过投资人ID获取该投资人累计到账收益(客户账户总计收到的所有利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeIncome(Long investPersonId) {
		/*// 直投标收益（不计入本金贷出、本金还款、服务费、管理费）
		BigDecimal dirbid=(BigDecimal)this.getSession().createSQLQuery("SELECT SUM(afterMoney) from bp_fund_intent where investPersonId=? and  fundType not in('principalLending','principalRepayment','serviceMoney','consultationMoney') and isCheck=0 and isValid=0").setLong(0, investPersonId).uniqueResult();
	    if(dirbid==null){//不处理，当dirbid为null的时候会抛异常
	    	dirbid=new BigDecimal(0);
	    }
		
		// 理财计划收益（不计入本金偿还）
		BigDecimal mmbid=(BigDecimal)this.getSession().createSQLQuery("SELECT SUM(afterMoney) from pl_mm_order_assigninterest where investPersonId=? and  fundType!='principalRepayment'").setLong(0, investPersonId).uniqueResult();
		if(mmbid==null){//不处理，当mmbid为null的时候会抛异常
			mmbid=new BigDecimal(0);
	    }*/
		
		//直接查询资金明细表 的到账收益
		BigDecimal incomMoney=(BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(incomMoney),0) from ob_account_deal_info where accountId in(select id from ob_system_account where investmentPersonId=?) and transferType=3 and dealRecordStatus=2").setLong(0, investPersonId).uniqueResult();
		return incomMoney;
	}
	
	/**
	 * 通过投资人ID获取该投资人累计回收本金
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPreweekIncome(Long investPersonId) {
		//直投标回收本金未发生债权交易的
		BigDecimal afterMoney1=(BigDecimal)this.getSession().createSQLQuery("SELECT IFNULL(SUM(afterMoney),0) from bp_fund_intent as bp " +
				" left join pl_bid_info as pl on bp.orderNo=pl.orderNo where pl.userId=? and  fundType='principalRepayment' and pl.newInvestPersonId is null").setLong(0, investPersonId).uniqueResult();
		//直投标回收本金已购买债权交易的
		BigDecimal afterMoney2=(BigDecimal)this.getSession().createSQLQuery("SELECT IFNULL(SUM(afterMoney),0) from bp_fund_intent as bp " +
				" left join pl_bid_info as pl on bp.orderNo=pl.orderNo where pl.newInvestPersonId=? and  fundType='principalRepayment' and pl.newInvestPersonId is not null").setLong(0, investPersonId).uniqueResult();
		//理财计划回收本金
		BigDecimal mmbid=(BigDecimal)this.getSession().createSQLQuery("SELECT IFNULL(SUM(afterMoney),0) from pl_mm_order_assigninterest where investPersonId=? and  fundType='principalRepayment'").setLong(0, investPersonId).uniqueResult();
		return afterMoney1.add(afterMoney2).add(mmbid);
	}
	
	/**
	 * 通过投资人ID获取该投资人30天内收益
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPremonthIncome(Long investPersonId) {
		
		/*//直投
		BigDecimal dirbid= (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(afterMoney) from bp_fund_intent where investPersonId=? and  fundType='loanInterest' and   DATE_SUB(CURDATE(), INTERVAL 1 MONTH)  <= date(factDate)").setLong(0, investPersonId).uniqueResult();
		if(dirbid==null){
			dirbid=new BigDecimal(0);
		}
		//理财计划
		BigDecimal mmbid= (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(afterMoney) from pl_mm_order_assigninterest where investPersonId=? and  fundType='loanInterest' and   DATE_SUB(CURDATE(), INTERVAL 1 MONTH)  <= date(factDate)").setLong(0, investPersonId).uniqueResult();
		if(mmbid==null){
			mmbid=new BigDecimal(0);
		}
		return dirbid.add(mmbid);*/
		
		//直接查询资金明细表 的到账收益
		BigDecimal incomMoney=(BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(incomMoney),0) from ob_account_deal_info where accountId in(select id from ob_system_account where investmentPersonId=?) and transferType=3 and dealRecordStatus=2 and DATE_SUB(CURDATE(), INTERVAL 1 MONTH)  <= date(createDate)").setLong(0, investPersonId).uniqueResult();
		return incomMoney;
	}
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第一部分散标投资的利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome(Long investPersonId) {
		//得到未发生债权交易的预期待收收益
		BigDecimal notMoney1 = (BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(notMoney),0)  from pl_bid_info as info LEFT JOIN bp_fund_intent as bp on info.orderNo=bp.orderNo " +
				" where  bp.isCheck=0 and info.userId=? and  bp.fundType = 'loanInterest'  and info.newInvestPersonId is null").setLong(0, investPersonId).uniqueResult();
		//得到购买债权交易的预期待收收益
		BigDecimal notMoney2 = (BigDecimal)this.getSession().createSQLQuery("select IFNULL(SUM(notMoney),0) from pl_bid_info as info LEFT JOIN bp_fund_intent as bp on info.orderNo=bp.orderNo " +
				" where  bp.isCheck=0 and info.newInvestPersonId=? and  bp.fundType = 'loanInterest'  and info.newInvestPersonId is not null").setLong(0, investPersonId).uniqueResult();

		return notMoney1.add(notMoney2);
	}
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第二部分理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome1(Long investPersonId) {
		return (BigDecimal)this.getSession().createSQLQuery("SELECT SUM(incomeMoney) from pl_mm_order_assigninterest where investPersonId=? and (keystr='mmplan' or keystr='UPlan') and factDate is null and fundType='loanInterest' and isCheck=0 and isValid=0").setLong(0, investPersonId).uniqueResult();
		
	}

	@Override
	public String getLevelMark(Long score) {
		return (String) this.getSession().createSQLQuery("select levelMark from web_bonus_custlevel_setting   where levelMinBonus BETWEEN 0 and ?  ORDER BY levelMinBonus DESC LIMIT 0 , 1;").setLong(0, score).uniqueResult();
	}

	@Override
	public BpCustMember getByLoginName(String loginName) {
		// TODO Auto-generated method stu
		String sql = "select * from bp_cust_member cust where cust.loginname = ?";
		return (BpCustMember)this.getSession().createSQLQuery(sql).addEntity(BpCustMember.class).setParameter(0, loginName).uniqueResult();
	}

	@Override
	public BpCustMember getEmailCode(String emailcode) {
		// TODO Auto-generated method stub
		String sql = "select * from bp_cust_member cust where cust.isCheckEmail = ?";
		return (BpCustMember)this.getSession().createSQLQuery(sql).addEntity(BpCustMember.class).setParameter(0, emailcode).uniqueResult();
	}


	@Override
	public List<ShowManageMoney> queryAllDebt(String loginName,HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		String state=request.getParameter("state");
	    String word = "";
	    if(state!=null && !"".equals(state) && state.equals("7")){
	    	word = " AND pm.incomeMoney > pm.afterMoney";
	    }
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
				" AND pm.isValid = 0"+ word+
				//" AND pm.incomeMoney > pm.afterMoney"+
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
				" AND p.moneyReceiver ='"+loginName+"'"
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
						" WHERE p.moneyReceiver ='"+loginName+"'"
		                );
			 
		 }
		if(null!=state && !"".equals(state)){
			sql.append(" AND p.state = "+Integer.valueOf(state));
		}
		String mmName=request.getParameter("mmName");
		if(null!=mmName && !"".equals(mmName)){
			sql.append(" AND p.mmName like '%"+mmName+"%'");
		}
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
		System.out.println("aaaaa"+sql);
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		if(!"".equals(start)&&start!=null&&!"".equals(limit)&&limit!=null){
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
            setResultTransformer(Transformers.aliasToBean(ShowManageMoney.class)).setFirstResult(Integer.valueOf(start)).setMaxResults(Integer.valueOf(limit)).list();
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

	@Override
	public List<ShowManageMoney> queryAllDebtNum(String loginName,
			HttpServletRequest request) {
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
				" AND p.moneyReceiver ='"+loginName+"'"
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
						" WHERE p.moneyReceiver ='"+loginName+"'"
		                );
			 
		 }
		if(null!=state && !"".equals(state)){
			sql.append(" AND p.state = "+Integer.valueOf(state));
		}
		String mmName=request.getParameter("mmName");
		if(null!=mmName && !"".equals(mmName)){
			sql.append(" AND p.mmName like '%"+mmName+"%'");
		}
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

	@Override
	public List<PlManageMoneyPlanBuyinfo> queryEarlyList(String loginName,
			HttpServletRequest request) {
		StringBuffer sql=new StringBuffer();
	     sql=new StringBuffer(" SELECT "+
			" e.earlyRedemptionId,"+
			" m.investPersonName,"+
			" m.mmName,"+
			" m.buyMoney,"+
			" m.startinInterestTime,"+
			" m.endinInterestTime,"+
			" e.checkStatus,"+
			" e.earlyDate,"+
			" m.keystr,"+
			" m.orderId,"+
			" m.mmplanId"+
			" FROM"+
			" pl_early_redemption e"+
			" LEFT JOIN pl_managemoneyplan_buyinfo m ON e.orderId = m.orderId"+
			" LEFT JOIN pl_managemoney_plan p ON m.mmplanId = p.mmplanId"+
			" WHERE"+
			"  p.moneyReceiver ='"+loginName+"'"
           );
	String mmName=request.getParameter("mmName");
	if(null!=mmName && !"".equals(mmName)){
		sql.append(" AND m.mmName like '%"+mmName+"%'");
	}
	String checkStatus=request.getParameter("checkStatus");
	if(null!=checkStatus && !"".equals(checkStatus)){
		sql.append(" AND e.checkStatus ="+Short.valueOf(checkStatus));
	}
	else{
		sql.append(" AND e.checkStatus ="+Short.valueOf("0"));
	}

	System.out.println("--->>>"+sql.toString());
	if(null!=request.getParameter("start")&&null!=request.getParameter("limit")){
		String start = request.getParameter("start").toString();
		String limit = request.getParameter("limit").toString();
		List list=this.getSession().createSQLQuery(sql.toString()).
		  addScalar("earlyRedemptionId", Hibernate.LONG).
		  addScalar("investPersonName", Hibernate.STRING).
		  addScalar("mmName", Hibernate.STRING).
		  addScalar("buyMoney", Hibernate.BIG_DECIMAL).
		  addScalar("startinInterestTime",Hibernate.DATE).
		  addScalar("endinInterestTime",Hibernate.DATE).
		  addScalar("checkStatus", Hibernate.SHORT).
		  addScalar("earlyDate",Hibernate.DATE).
		  addScalar("keystr", Hibernate.STRING).
		  addScalar("earlyRedemptionId", Hibernate.LONG).
		  addScalar("orderId", Hibernate.LONG).
         setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlanBuyinfo.class)).setFirstResult(Integer.valueOf(start)).setMaxResults(Integer.valueOf(limit)).list();
		return list;
	}else{
		List list=this.getSession().createSQLQuery(sql.toString()).
		  addScalar("earlyRedemptionId", Hibernate.LONG).
		  addScalar("investPersonName", Hibernate.STRING).
		  addScalar("mmName", Hibernate.STRING).
		  addScalar("buyMoney", Hibernate.BIG_DECIMAL).
		  addScalar("startinInterestTime",Hibernate.DATE).
		  addScalar("endinInterestTime",Hibernate.DATE).
		  addScalar("checkStatus", Hibernate.SHORT).
		  addScalar("earlyDate",Hibernate.DATE).
		  addScalar("keystr", Hibernate.STRING).
		  addScalar("mmplanId", Hibernate.LONG).
		  addScalar("orderId", Hibernate.LONG).
		  setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlanBuyinfo.class)).list();
		return list;
	}
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> queryEarlyListNum(String loginName,HttpServletRequest request) {
		StringBuffer sql=new StringBuffer();
	     sql=new StringBuffer(" SELECT "+
			" e.earlyRedemptionId,"+
			" m.investPersonName,"+
			" m.mmName,"+
			" m.buyMoney,"+
			" m.startinInterestTime,"+
			" m.endinInterestTime,"+
			" e.checkStatus,"+
			" e.earlyDate,"+
			" m.keystr,"+
			" m.orderId,"+
			" m.mmplanId"+
			" FROM"+
			" pl_early_redemption e"+
			" LEFT JOIN pl_managemoneyplan_buyinfo m ON e.orderId = m.orderId"+
			" LEFT JOIN pl_managemoney_plan p ON m.mmplanId = p.mmplanId"+
			" WHERE"+
			"  p.moneyReceiver ='"+loginName+"'"
          );
	String mmName=request.getParameter("mmName");
	try {
		if(mmName!=null){
			mmName = new String(mmName.getBytes("ISO8859-1"),"utf-8");
		}
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(null!=mmName && !"".equals(mmName)){
		sql.append(" AND m.mmName like '%"+mmName+"%'");
	}
	String checkStatus=request.getParameter("checkStatus");
	if(null!=checkStatus && !"".equals(checkStatus)){
		sql.append(" AND e.checkStatus ="+Short.valueOf(checkStatus));
	}
	else{
		sql.append(" AND e.checkStatus ="+Short.valueOf("0"));
	}

	//System.out.println("--->>>"+sql.toString());
		List list=this.getSession().createSQLQuery(sql.toString()).
		  addScalar("earlyRedemptionId", Hibernate.LONG).
		  addScalar("investPersonName", Hibernate.STRING).
		  addScalar("mmName", Hibernate.STRING).
		  addScalar("buyMoney", Hibernate.BIG_DECIMAL).
		  addScalar("startinInterestTime",Hibernate.DATE).
		  addScalar("endinInterestTime",Hibernate.DATE).
		  addScalar("checkStatus", Hibernate.SHORT).
		  addScalar("earlyDate",Hibernate.DATE).
		  addScalar("keystr", Hibernate.STRING).
		  addScalar("mmplanId", Hibernate.LONG).
		  addScalar("orderId", Hibernate.LONG).
		  setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlanBuyinfo.class)).list();
		return list;
	}

	@Override
	public List<BpCustMember> queryMyInvest(BpCustMember member,HttpServletRequest request) {
		List<BpCustMember> list = new ArrayList<BpCustMember>();
		String sql = "select * from bp_cust_member mem where mem.recommandPerson = ? and mem.id != "+member.getId()+" order by mem.registrationDate desc";
		if(request.getParameter("start")!=null&&!"".equals(request.getParameter("limit"))){
			String start = request.getParameter("start").toString();
			String limit = request.getParameter("limit").toString();
			list = this.getSession().createSQLQuery(sql).
			addEntity(BpCustMember.class).
			setParameter(0, member.getPlainpassword())
			.setFirstResult(Integer.valueOf(start))
			.setMaxResults(Integer.valueOf(limit))
			.list();
		}else{
			list =  this.getSession().createSQLQuery(sql).setParameter(0, member.getPlainpassword()).list();
		}
		return list;
	}

	@Override
	public Object findCode(BpCustMember bpCustMember) {
		String sql = "SELECT bpcust.plainpassword FROM bp_cust_member bpcust WHERE bpcust.id = " +bpCustMember.getId();
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public Object finTel(BpCustMember bpcust) {
		String sql = "SELECT bpcust.telphone FROM bp_cust_member bpcust WHERE bpcust.id = " +bpcust.getId();
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public List<BpCustMember> appQueryMyInvest(BpCustMember member, HttpServletRequest request) {
		List<BpCustMember> list;
		String sql = "select mem.telphone ,mem.registrationDate,mem.plainpassword from bp_cust_member mem where mem.recommandPerson = ? and mem.id != "+member.getId()+" order by mem.registrationDate desc";
		System.out.println(sql);
		list = this.getSession().createSQLQuery(sql).
				addScalar("telphone",Hibernate.STRING).
				addScalar("registrationDate",Hibernate.DATE).
				addScalar("plainpassword",Hibernate.STRING).
				setParameter(0, member.getPlainpassword())
				//.setFirstResult(Integer.valueOf(start))
				//.setMaxResults(Integer.valueOf(limit))
				.setResultTransformer(Transformers.aliasToBean(BpCustMember.class))
				.list();
//		if(request.getParameter("page")!=null&&!"".equals(request.getParameter("limit"))){
//			String start = request.getParameter("page").toString();
//			String limit = request.getParameter("limit").toString();
//		}else{
//			list =  this.getSession().createSQLQuery(sql).setParameter(0, member.getPlainpassword()).list();
//		}
		return list;
	}

	@Override
	public List<InvestPersonInfo> appGetMoneyPeople(BpCustMember member) {
		return null;
	}

	@Override
	public List<BpCustMember> queryMyInvestNum(BpCustMember member,
			HttpServletRequest request) {
		List<BpCustMember> list = new ArrayList<BpCustMember>();
		String sql = "select * from bp_cust_member mem where mem.recommandPerson = ? and mem.id != "+member.getId();
		list =  this.getSession().createSQLQuery(sql).setParameter(0, member.getPlainpassword()).list();
		return list;
	}
	
	public boolean organiz (String recommendCode){
		boolean flag = false;
		String sql = "select count(*) from organization where recommendCode = ? ";
		BigInteger a = (BigInteger) this.getSession().createSQLQuery(sql).setParameter(0, recommendCode).uniqueResult();
		if(a.compareTo(new BigInteger("0"))>0){
			flag = true;
		}
		return flag;
	}
	
	public Object regCount(){
		String sql = "select count(1) from bp_cust_member";
		return this.getSession().createSQLQuery(sql).uniqueResult();
	}

	public BpCustMember getUserInfo(Long id) {
		BpCustMember bpCustMember =null;

		String sql ="select id," +
				"loginname," +
				"truename," +
				"isCheckEmail," +
				"isCheckCard," +
				"telphone," +
				"email from bp_cust_member where id ="+id;
		List<BpCustMember> bpCustMemberList =
				this.getSession().createSQLQuery(sql)
				.addScalar("id",Hibernate.LONG)
				.addScalar("loginname",Hibernate.STRING)
				.addScalar("truename",Hibernate.STRING)
				.addScalar("isCheckEmail",Hibernate.STRING)
				.addScalar("isCheckCard",Hibernate.STRING)
				.addScalar("telphone",Hibernate.STRING)
				.addScalar("email",Hibernate.STRING)
				.setResultTransformer(Transformers.aliasToBean(BpCustMember.class)).list();
				if(bpCustMemberList.size()>0){
					bpCustMember=bpCustMemberList.get(0);
				}

		return bpCustMember;
	}

	@Override
	public Integer chenkPhoneNum(String telPhone) {
		String sql = "select count(1) from bp_cust_member WHERE telphone = ?";
		BigInteger count = (BigInteger) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setParameter(0,telPhone).uniqueResult();
		return count.intValue();
	}

	@Override
	public List<Investproject> getMyInviteInfo(String bidId) {
		String sql = " select * from " +
				"(SELECT  " +
				"info.id as infoId," +
				"info.bidId as planId," +
				"plan.proType as proType," +
				"plan.bidProName as bidName," +
				"0 as userType," +
				"info.userId as userId," +
				"info.userName as userName," +
				"FLOOR(info.userMoney) AS userMoney,"+ //去掉查询结果的小数点
				// "info.userMoney as userMoney," +
				" info.bidtime as bidtime," +
				" DATE_FORMAT(info.bidtime,'%Y-%m-%d') as bidtimeStr," +
				"sl.payintentPeriod as periodTime," +
				"case " +
				"sl.payaccrualType " +
				"WHEN 'monthPay' then " +
				"'个月' " +
				"WHEN 'dayPay' THEN " +
				"'天' " +
				"WHEN 'yearPay' THEN  " +
				"'年' " +
				"END as payaccrualType,"+
				"sl.yearAccrualRate as yeaRate," +
				"bm.cardcode as cardcode," +
				"bm.truename AS trueName,"+
				"bm.telphone as telphone," +
				"info.state as state," +
				"CASE info.state " +
				"WHEN '0' THEN '投资失败'" +
				"WHEN  '1' THEN '投标成功'" +
				"WHEN '2'  THEN '已放款'" +
				"WHEN '3' THEN '已流标'" +
				"END " +
				"\n" +
				"as stateShow," +
				"null as isOtherFlow," +
				"'pl_bid_plan' as planTable, " +
				"NULL AS earlierOutDate, " +
				"null as runId ," +
				"null as contractNo, " +
                        /*"null  as startinInterestTime, "+
                        "null  as endinInterestTime, " +*/
				" plan.startIntentDate As startinInterestTime,"
				+ "plan.endIntentDate AS endinInterestTime, " +
				"null  as customerManagerNameId, " +
				"NULL AS departId " +
				"FROM " +
				"`pl_bid_info` AS info " +
				"LEFT JOIN pl_bid_plan AS plan ON info.bidId = plan.bidId " +
				"left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' " +
				"left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' " +
				"left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' " +
				"left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' " +
				"LEFT JOIN bp_cust_member as bm  on info.userId = bm.id " +
				"left join bp_fund_project as sl " +
				"on ( sl.flag in ('1','0') AND((sl.projectId=bdir.proId ) " +
				"or (sl.projectId=bor.proId ) " +
				"or(sl.projectId=pdir.proId ) " +
				"or (sl.projectId=por.proId ) " +
				"))" + " where info.state=1 or info.state = 2  "+
				"UNION ALL  " +
				"SELECT  " +
				"buyInfo.orderId as infoId, " +
				"buyInfo.mmplanId as planId, " +
				"buyPlan.keystr as proType, " +
				"buyInfo.mmName as bidName, " +
				"buyInfo.persionType as userType, " +
				"buyInfo.investPersonId as userId, " +
				"buyInfo.investPersonName as userName, " +
				"FLOOR(buyInfo.buyMoney) as userMoney, " +
				"buyInfo.buyDatetime as bidtime, " +
				"buyInfo.buyDatetime as bidtimeStr, " +
				"buyPlan.investlimit as periodTime, " +
				"buyPlan.payaccrualType as payaccrualType,"+
				"buyPlan.yeaRate as yeaRate, " +
				"NULL as cardcode," +
				"NULL as trueName, "+
				"NULL as telphone, " +
				"buyInfo.state as state, " +
				"NULL AS stateShow, " +
				"buyInfo.isOtherFlow as isOtherFlow," +
				"'pl_managemoney_plan' as planTable, " +
				"buyInfo.earlierOutDate as earlierOutDate, " +
				"buyInfo.runId as runId  , " +
				"buyInfo.contractNo as contractNo, " +
				"buyInfo.startinInterestTime as startinInterestTime1, " +
				"buyInfo.endinInterestTime as endinInterestTime1, " +
				"pmoi.customerManagerNameId, " +
				"pmoi.departId as departId " +

				"FROM " +
				"pl_managemoneyplan_buyinfo AS buyInfo " +
				"LEFT JOIN pl_managemoney_plan AS buyPlan ON buyInfo.mmplanId = buyPlan.mmplanId " +
				"LEFT JOIN pl_mm_order_info as pmoi on pmoi.orderId = buyInfo.orderId" +
				") as investBuyInfo where 1=1 ";
				sql = sql+" AND investBuyInfo.userType = 0 AND investBuyInfo.userID = ?  order by investBuyInfo.bidtime desc";

		System.out.println("查询方法的sql是" + sql);
		List<Investproject> list = new ArrayList<Investproject>();
			list = this.getSession().createSQLQuery(sql).
					addScalar("infoId", Hibernate.LONG).
					addScalar("planId", Hibernate.LONG).
					addScalar("proType", Hibernate.STRING).
					addScalar("bidName", Hibernate.STRING).
					addScalar("userType", Hibernate.SHORT).
					addScalar("userId", Hibernate.LONG).
					addScalar("userName", Hibernate.STRING).
					addScalar("userMoney", Hibernate.BIG_DECIMAL).
					addScalar("bidtime", Hibernate.TIMESTAMP).
					addScalar("bidtimeStr", Hibernate.STRING).
					addScalar("periodTime", Hibernate.INTEGER).
					addScalar("payaccrualType", Hibernate.STRING).
					addScalar("yeaRate", Hibernate.BIG_DECIMAL).
					addScalar("cardcode", Hibernate.STRING).
					addScalar("trueName",Hibernate.STRING).
					addScalar("telphone", Hibernate.STRING).
					addScalar("state", Hibernate.SHORT).
					addScalar("stateShow", Hibernate.STRING).
					addScalar("planTable", Hibernate.STRING).
					addScalar("earlierOutDate", Hibernate.DATE).
					addScalar("isOtherFlow", Hibernate.SHORT).
					addScalar("runId", Hibernate.LONG).
					addScalar("contractNo", Hibernate.STRING).
					addScalar("startinInterestTime", Hibernate.TIMESTAMP).
					addScalar("endinInterestTime", Hibernate.TIMESTAMP).
					setResultTransformer(Transformers.aliasToBean(Investproject.class)).setParameter(0,bidId).
					list();

		return list;
	}

	@Override
	public List<InvestPersonInfo> getMoneyPeople(BpCustMember member) {


		String sql = "SELECT\n" +
				"\t* \n" +
				"FROM\n" +
				"\t( SELECT bm.loginname AS investPersonName, bm.id AS investId FROM bp_cust_member AS bm WHERE bm.recommandPerson =  ? ) AS bmp,\n" +
				"\t( SELECT SUM(info.investMoney) as investMoney, info.investPersonId FROM invest_person_info AS info  GROUP BY investPersonId) AS infom \n" +
				"WHERE\n" +
				"\tbmp.investId = infom.investPersonId ;";
		System.out.println("查询的sql:" + sql);

		List<InvestPersonInfo> list =  new ArrayList<>();
		list = this.getSession().createSQLQuery(sql).
				addScalar("investId", Hibernate.LONG).
				addScalar("investMoney",Hibernate.BIG_DECIMAL).
				setResultTransformer(Transformers.aliasToBean(InvestPersonInfo.class)).setParameter(0,member.getPlainpassword()).list();
		return list;
	}

	@Override
	public void saveFeedBack(FeedBack feedBack) {
		this.getHibernateTemplate().save(feedBack);
	}

	@Override
	public void updateTC(String telphone) {
		String sql = "update bp_cust_member SET truename = NULL,cardcode = NULL WHERE telphone= "+telphone;
		System.out.println(sql+"账户更改");
		this.getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public Object findPhone(String plainCode) {

		String sql = "SELECT bpcust.telphone FROM bp_cust_member bpcust WHERE bpcust.plainpassword = '"+plainCode+"'";
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public List<InviteDetail> inviteList(String plainCode) {
		String sql = " select bp.id, bp.truename, bp.telphone, bp.registrationDate, bp.isCheckCard, pl.bidId, pl.bidName, pl.userMoney, pl.bidtime from " +
					 "bp_cust_member bp LEFT JOIN pl_bid_info pl ON "+
					 "bp.id = pl.userId "+
				     "WHERE bp.recommandPerson = '"+plainCode+"'";
		System.out.println(sql+"");
		List<InviteDetail> list = this.getSession().createSQLQuery(sql).
				addScalar("id",Hibernate.INTEGER).
				addScalar("truename",Hibernate.STRING).
				addScalar("telphone",Hibernate.STRING).
				addScalar("registrationDate",Hibernate.DATE).
				addScalar("isCheckCard",Hibernate.STRING).
				addScalar("bidId",Hibernate.LONG).
				addScalar("bidName",Hibernate.STRING).
				addScalar("userMoney",Hibernate.BIG_DECIMAL).
				addScalar("bidtime",Hibernate.DATE).
				setResultTransformer(Transformers.aliasToBean(InviteDetail.class)).list();
		return list;
	}

	@Override
	public List<InviteDetail> selectInvite(HttpServletRequest request, String plainpassword) {
		StringBuffer sql = new StringBuffer(" select bp.id, bp.truename, bp.telphone, bp.registrationDate, bp.isCheckCard, pl.bidId, pl.bidName, pl.userMoney, pl.bidtime from " +
				"bp_cust_member bp LEFT JOIN pl_bid_info pl ON "+
				"bp.id = pl.userId "+
				"WHERE bp.recommandPerson = '"+plainpassword+"'");
		String truename = request.getParameter("searchKey");
//		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		if (null != truename && !"".equals(truename)) {
			sql.append(" and (bp.telphone like '%"+truename+"%' or (bp.truename like '%"+truename+"%' AND bp.isCheckCard=1))");
		}
//		if (null != truename && !"".equals(truename)) {
//			if(pattern.matcher(truename).matches()){
//				sql.append(" and bp.telphone = '"+truename+"' )");
//			}else {
//				sql.append(" and bp.truename like '%"+truename+"%'");
//			}
//		}
		String starDate = request.getParameter("starDate");
		String endDate = request.getParameter("endDate");
		if (null != starDate && !"".equals(starDate) && null != endDate && !"".equals(endDate)) {
			sql.append(" and pl.bidtime BETWEEN '"+starDate+"' AND '"+endDate+"'");
		}
		System.out.println(sql+"");
		List<InviteDetail> list = this.getSession().createSQLQuery(sql.toString()).
				addScalar("id",Hibernate.INTEGER).
				addScalar("truename",Hibernate.STRING).
				addScalar("telphone",Hibernate.STRING).
				addScalar("registrationDate",Hibernate.DATE).
				addScalar("isCheckCard",Hibernate.STRING).
				addScalar("bidId",Hibernate.LONG).
				addScalar("bidName",Hibernate.STRING).
				addScalar("userMoney",Hibernate.BIG_DECIMAL).
				addScalar("bidtime",Hibernate.DATE).
				setResultTransformer(Transformers.aliasToBean(InviteDetail.class)).list();
		return list;
	}

	@Override
	public Integer countNum(String plainpassword) {
		String sql = "select COUNT(*) from " +
				"bp_cust_member bp LEFT JOIN pl_bid_info pl ON "+
				"bp.id = pl.userId "+
				"WHERE bp.recommandPerson = '"+plainpassword+"'"+
				"AND bidName is NOT NULL";
		System.out.println(sql+"");
		BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		return integer.intValue();
	}

	@Override
	public BigDecimal sunMoney(String plainpassword) {
		String sql = "select SUM(userMoney) from " +
				"bp_cust_member bp LEFT JOIN pl_bid_info pl ON "+
				"bp.id = pl.userId "+
				"WHERE bp.recommandPerson = '"+plainpassword+"'"+
				"AND bidName is NOT NULL";
		BigDecimal decimal = (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
		return decimal;
	}

	@Override
	public List<InvitePersonDetail> checkInviteDetail(String id) {
		String sql = "select bp.truename, bp.telphone, bp.registrationDate, bp.isCheckCard, "+
				"fo.bidId, fo.bidName, fo.userMoney, fo.bidtime, " +
				"pl.payMoneyTime, pl.startIntentDate, pl.endIntentDate, pl.state from "+
				"pl_bid_info fo LEFT JOIN bp_cust_member bp "+
				"ON bp.id = fo.userId LEFT JOIN pl_bid_plan pl "+
				"ON fo.bidId = pl.bidId "+
				"WHERE bp.id = '"+id+"'";
		System.out.println(sql+"  ");
		List<InvitePersonDetail> list = this.getSession().createSQLQuery(sql).
				addScalar("truename",Hibernate.STRING).
				addScalar("telphone",Hibernate.STRING).
				addScalar("registrationDate",Hibernate.DATE).
				addScalar("isCheckCard",Hibernate.STRING).
				addScalar("bidId",Hibernate.LONG).
				addScalar("bidName",Hibernate.STRING).
				addScalar("userMoney",Hibernate.BIG_DECIMAL).
				addScalar("bidtime",Hibernate.DATE).
				addScalar("payMoneyTime",Hibernate.INTEGER).
				addScalar("startIntentDate",Hibernate.DATE).
				addScalar("endIntentDate",Hibernate.DATE).
				addScalar("state",Hibernate.INTEGER).
				setResultTransformer(Transformers.aliasToBean(InvitePersonDetail.class)).list();
		return list;
	}

	@Override
	public InviteDetail inviteList1(String id) {
		String sql = " select bp.id, bp.truename, bp.telphone, bp.registrationDate, pl.bidId, pl.bidName, pl.userMoney, pl.bidtime from " +
				"bp_cust_member bp LEFT JOIN pl_bid_info pl ON "+
				"bp.id = pl.userId "+
				"WHERE bp.id = '"+id+"'";

		System.out.println(sql+"");
		List<InviteDetail> list = this.getSession().createSQLQuery(sql).
				addScalar("id",Hibernate.INTEGER).
				addScalar("truename",Hibernate.STRING).
				addScalar("telphone",Hibernate.STRING).
				addScalar("registrationDate",Hibernate.DATE).
				addScalar("bidId",Hibernate.LONG).
				addScalar("bidName",Hibernate.STRING).
				addScalar("userMoney",Hibernate.BIG_DECIMAL).
				addScalar("bidtime",Hibernate.DATE).
				setResultTransformer(Transformers.aliasToBean(InviteDetail.class)).list();
		return list.get(0);
	}
}