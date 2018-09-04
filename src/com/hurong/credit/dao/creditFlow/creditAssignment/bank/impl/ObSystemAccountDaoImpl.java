package com.hurong.credit.dao.creditFlow.creditAssignment.bank.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObSystemAccountDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class ObSystemAccountDaoImpl extends BaseDaoImpl<ObSystemAccount> implements ObSystemAccountDao{

	public ObSystemAccountDaoImpl() {
		super(ObSystemAccount.class);
	}
	

	//根据投资人id查看系统虚拟账户
	@Override
	public ObSystemAccount getByInvrstPersonId(Long investMentPersonId) {
		// TODO Auto-generated method stub
		String  hql ="from ObSystemAccount as bank where  1=1 and bank.investmentPersonId="+investMentPersonId;
		List<ObSystemAccount> list =this.getSession().createQuery(hql).list();
		ObSystemAccount obSystemAccount =null;
		if(list!=null&&list.size()>0){
			obSystemAccount=list.get(0);
		}
		return obSystemAccount;
	}

	@Override
	public BigDecimal getBalance(String investPersonId) {
		String  sql ="select totalMoney from ObSystemAccount where  investmentPersonId="+investPersonId;
		List list =this.getSession().createQuery(sql).list();
		BigDecimal  obSystemAccount =null;
		if(list!=null&&list.size()>0){
			obSystemAccount=(BigDecimal) list.get(0);
		}
		return obSystemAccount;
	}
	@Override
	public ObSystemAccount getByInvrstPersonIdAndType(Long investPersionId,
			Short investPsersionType) {
		String hql="from ObSystemAccount as bank where  1=1 and bank.investmentPersonId=? and bank.investPersionType=?";
		return (ObSystemAccount)findUnique(hql, new Object[]{investPersionId,investPsersionType});
	}
	@Override
	public List<ObSystemAccount> findAccountList(String investName,String accountType, HttpServletRequest request, Integer start,Integer limit) {
			String  hql ="select " +
						 "bank.id," +
						 "bank.accountName," +
						 "bank.accountNumber," +
						 "bank.investmentPersonId," +
						 "bank.investPersonName," +
						 "bank.investPersionType," +
						 "bank.totalMoney," +
						 "bank.accountStatus," +
						 "bank.companyId " +
						 "from ob_system_account as bank where  1=1 ";
		
		if(investName!=null&&!"".equals(investName)&&!"null".equals(investName)){
			hql=hql+"   and bank.investPersonName = '"+investName+"'";
		}
		if(accountType!=null&&!"".equals(accountType)&&!"null".equals(accountType)){
			hql=hql+"   and bank.investPersionType = "+Short.valueOf(accountType);
		}
		List list=null;
		if(start==null||limit==null){
			list=this.getSession().createSQLQuery(hql).list();;
		}else{
			list=this.getSession().createSQLQuery(hql)
		     .addScalar("id", Hibernate.LONG)
		     .addScalar("accountName", Hibernate.STRING)
		     .addScalar("accountNumber", Hibernate.STRING)
		     .addScalar("investmentPersonId", Hibernate.LONG)
		     .addScalar("investPersonName", Hibernate.STRING)
		     .addScalar("investPersionType", Hibernate.SHORT)
		     .addScalar("totalMoney", Hibernate.BIG_DECIMAL)
		     .addScalar("accountStatus", Hibernate.SHORT)
		     .addScalar("companyId", Hibernate.LONG)
		     .setResultTransformer(Transformers.aliasToBean(ObSystemAccount.class)).
			  setFirstResult(start).setMaxResults(limit).
			  list();;
		}
		return list;
	}


	@Override
	public List<ObSystemaccountSetting> findObSystemaccountSetting() {
		// TODO Auto-generated method stub
		String hql=" from ObSystemaccountSetting  as o where o.usedRemark like '%供系统账户交易明细类型%' ";
		
		return (List<ObSystemaccountSetting>) this.getSession().createQuery(hql).list();
	}

}