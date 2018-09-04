package com.hurong.credit.service.financePurchase;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;


public interface BpFinanceApplyUserService extends BaseService<BpFinanceApplyUser>{
	public List<BpFinanceApplyUser> getApplyUser(BpFinanceApplyUser applyUser);
	public List<BpFinanceApplyUser> getFinanceApply(BpFinanceApplyUser appUser);
	public List<BpFinanceApplyUser> getFinanceApplyState(BpFinanceApplyUser appUser);
	public String getPassThrough(Long userId);
}
