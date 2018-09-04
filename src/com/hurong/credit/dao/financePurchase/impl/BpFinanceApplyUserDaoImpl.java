package com.hurong.credit.dao.financePurchase.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.financePurchase.BpFinanceApplyUserDao;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;

@SuppressWarnings("unchecked")
public class BpFinanceApplyUserDaoImpl extends BaseDaoImpl<BpFinanceApplyUser> implements BpFinanceApplyUserDao{
	public BpFinanceApplyUserDaoImpl(){
		super(BpFinanceApplyUser.class);
	}
	/**
	 * 参数：userid
	 */
	@Override
	public List<BpFinanceApplyUser> getFinanceApplyUser(BpFinanceApplyUser appUser) {
		String hql = "from BpFinanceApplyUser u where u.userID='"+appUser.getUserID()+"'"+" order by u.createTime desc";
		return findByHql(hql);
	}
/**
 * 参数：userid,state
 */
	@Override
	public List<BpFinanceApplyUser> getFinanceApply(BpFinanceApplyUser appUser) {
		String hql = "from BpFinanceApplyUser u where u.userID='"+appUser.getUserID()+"' and u.state='"+appUser.getState()+"'";
		return findByHql(hql);
	}
/**
 *  userid,state,state1,state2
 */
	@Override
	public List<BpFinanceApplyUser> getFinanceApplyState(
			BpFinanceApplyUser appUser) {
		String hql = "from BpFinanceApplyUser u where u.userID='"+appUser.getUserID()+"' and (u.state='"+appUser.getState()+"' or u.state='"+appUser.getState1()+"' or u.state='"+appUser.getState2()+"')";
		return findByHql(hql);
	}
	@Override
	public String getPassThrough(Long userId) {
		String hql ="from BpFinanceApplyUser as apply where apply.userID=? and apply.state=4";
		List<BpFinanceApplyUser> list = this.getSession().createQuery(hql).setParameter(0, userId).list();
		if(list!=null&&list.size()>0){
			return "passThrough";
		}else{
			return null;
		}
	}
}
