package com.hurong.credit.dao.financePurchase;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;

public interface BpFinanceApplyUserDao extends BaseDao<BpFinanceApplyUser> {
	public List<BpFinanceApplyUser> getFinanceApplyUser(BpFinanceApplyUser appUser);
	public List<BpFinanceApplyUser> getFinanceApply(BpFinanceApplyUser appUser);
	public List<BpFinanceApplyUser> getFinanceApplyState(BpFinanceApplyUser appUser);
	public String getPassThrough(Long userId);
}
