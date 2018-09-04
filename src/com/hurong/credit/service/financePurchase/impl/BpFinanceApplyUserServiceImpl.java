package com.hurong.credit.service.financePurchase.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.financePurchase.BpFinanceApplyUserDao;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;


public class BpFinanceApplyUserServiceImpl extends BaseServiceImpl<BpFinanceApplyUser> implements BpFinanceApplyUserService{
	@SuppressWarnings("unused")
	private BpFinanceApplyUserDao dao;
	
	public BpFinanceApplyUserServiceImpl(BpFinanceApplyUserDao dao){
		super(dao);
		this.dao=dao;
	}
/**
 * userid
 */
	@Override
	public List<BpFinanceApplyUser> getApplyUser(BpFinanceApplyUser applyUser) {
		
		return dao.getFinanceApplyUser(applyUser);
	}
/**
 * userid,state,
 */
	@Override
	public List<BpFinanceApplyUser> getFinanceApply(BpFinanceApplyUser appUser) {
		// TODO Auto-generated method stub
		return dao.getFinanceApply(appUser);
	}
/**
 * userid,state,state1,state2
 */
	@Override
	public List<BpFinanceApplyUser> getFinanceApplyState(
			BpFinanceApplyUser appUser) {
		// TODO Auto-generated method stub
		return dao.getFinanceApplyState(appUser);
	}
@Override
public String getPassThrough(Long userId) {
	// TODO Auto-generated method stub
	return dao.getPassThrough(userId);
}
}
