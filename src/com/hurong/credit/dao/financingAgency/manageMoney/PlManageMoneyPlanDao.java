package com.hurong.credit.dao.financingAgency.manageMoney;

import java.math.BigInteger;
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;

public interface PlManageMoneyPlanDao extends BaseDao<PlManageMoneyPlan> {
	List<PlManageMoneyPlan> listpmmp(Pager p);
	BigInteger getCount(Pager p);
	List<PlManageMoneyPlan> indexProduct(String sql);
	public List<PlManageMoneyPlan> getCurrentList();
	
	public List<PlManageMoneyPlan> getUPlanList(String typeId);
	/**
	 * mark值：pl_managemoney_plan.+表的主键
	 * @param mark
	 * @return
	 */
	public String  getLogoUrl(String mark);
	/**
	 *根据收款用户名称查询理财计划列表
	 */
	public List<PlManageMoneyPlan> listByMoneyReceiver(String moneyReceiver);
}
