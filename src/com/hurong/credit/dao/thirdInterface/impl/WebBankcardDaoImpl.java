package com.hurong.credit.dao.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.thirdInterface.WebBankcardDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.BankCard;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

/**
 *
 * @author
 *
 */
@SuppressWarnings("unchecked")
public class WebBankcardDaoImpl extends BaseDaoImpl<WebBankcard> implements WebBankcardDao{

	public WebBankcardDaoImpl() {
		super(WebBankcard.class);
	}

	@Override
	public WebBankcard getByCardNum(String openAcctId) {
		String hql="from WebBankcard bc where bc.cardNum=?";
		Object[] params={openAcctId};
		return (WebBankcard)findUnique(hql, params);
	}

	@Override
	public WebBankcard checkCardExit(WebBankcard webBankcard, BpCustMember mem) {
		// TODO Auto-generated method stub
		WebBankcard card=null;
		String hql =" from WebBankcard as bc where bc.cardNum=? and bc.customerId=? and bc.bankId=?";
		if(webBankcard.getCardNum()!=null&&mem.getId()!=null&&webBankcard.getCardId()!=null){
			List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, webBankcard.getCardNum()).setParameter(0, mem.getId()).setParameter(2, webBankcard.getBankId()).list();
			if(list!=null && list.size()>0){
				card=list.get(0);
			}
		}
		return card;
	}

	@Override
	public List<WebBankcard> getBycusterId(Long id) {
		// TODO Auto-generated method stub
		String hql =" from WebBankcard as bc where  bc.customerId=? and (bc.bindCardStatus is not null and  bc.bindCardStatus in ('bindCard_status_success','bindCard_status_accept','bindCard_status_faild','bindcard_status_cancelapply')) ORDER BY bc.bankId desc";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, id).list();
		return list;
	}

	@Override
	public WebBankcard getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		WebBankcard card=null;
		String hql =" from WebBankcard as bc where  bc.requestNo=?";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, requestNo).list();
		if(list!=null && list.size()>0){
			card=list.get(0);
		}
		return card;
	}

	@Override
	public List<WebBankcard> getbanklist(Long userId, String cardStatus,
										 String remak) {
		// TODO Auto-generated method stub
		String hql =" from WebBankcard as bc where  bc.customerId=?  and bc.bindCardStatus in ('bindCard_status_success','bindCard_status_accept','bindcard_status_cancelapply'))";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, userId).list();
		return list;
	}

	@Override
	public List<WebBankcard> getByuserId(Long id) {
		// TODO Auto-generated method stub
		String hql = " from WebBankcard as bc where  bc.customerId=? ";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, id).list();
		return list;
	}

	@Override
	public List<WebBankcard> getBycustAndState(Long id,
											   String bindcardStatusSuccess) {
		String hql = " from WebBankcard as bc where  bc.customerId=? and bc.bindCardStatus=?";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, id).setParameter(1, bindcardStatusSuccess).list();
		return list;
	}

	@Override
	public WebBankcard getByCardNumAndState(String openAcctId, String state) {
		WebBankcard card=null;
		String hql =" from WebBankcard as bc where  bc.cardNum=? and bindCardStatus=?";
		List<WebBankcard> list= this.getSession().createQuery(hql).setParameter(0, openAcctId).setParameter(1, state).list();
		if(list!=null && list.size()>0){
			card=list.get(0);
		}
		return card;
	}

	@Override
	public List<BankCard> getBankCardList(Long id) {


		String sql="select bc.cardId,bc.bankname,bc.cardNum,wb.signDealQuota,wb.dayDealQuota,bc.bindCardStatus,wc.spell,wc.bankMaxLogo,wc.bankMinLogo,wc.bankLogo " +
				"from web_bankcard as bc  LEFT JOIN web_bank_code_fudian wb " +
				"on bc.bankname =wb.bankName " +
				"LEFT JOIN web_bank_code wc on bc.bankname=wc.bankName " +
				"where  bc.customerId=? and bc.bindCardStatus = 'bindCard_status_success'";
		List list = this.getSession().createSQLQuery(sql)
				.addScalar("cardId", Hibernate.STRING)
				.addScalar("bankName", Hibernate.STRING)
				.addScalar("cardNum", Hibernate.STRING)
				.addScalar("bindCardStatus", Hibernate.STRING)
				.addScalar("signDealQuota", Hibernate.STRING)
				.addScalar("dayDealQuota", Hibernate.STRING)
				.addScalar("spell",Hibernate.STRING)
				.addScalar("bankMaxLogo",Hibernate.STRING)
				.addScalar("bankMinLogo",Hibernate.STRING)
				.addScalar("bankLogo", Hibernate.STRING).setResultTransformer(Transformers.aliasToBean(BankCard.class)).setParameter(0,id).list();
		return list;
	}

	@Override
	public List<BankCard> getBankCardQuota() {
		String sql = "SELECT wb.cardCode,wb.bankName,wb.signDealQuota,wb.dayDealQuota,wc.bankLogo FROM web_bank_code_fudian wb LEFT JOIN web_bank_code wc on wb.bankname=wc.bankName where wc.bankLogo is not null and wb.signDealQuota<>'0'";
		List list = this.getSession().createSQLQuery(sql)
				.addScalar("cardCode", Hibernate.STRING)
				.addScalar("bankName", Hibernate.STRING)
				.addScalar("signDealQuota", Hibernate.STRING)
				.addScalar("dayDealQuota", Hibernate.STRING)
				.addScalar("bankLogo", Hibernate.STRING).setResultTransformer(Transformers.aliasToBean(BankCard.class)).list();
		return list;


	}


}