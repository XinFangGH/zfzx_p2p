package com.hurong.credit.service.financingAgency.manageMoney;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.user.BpCustMember;

public interface PlManageMoneyPlanService extends BaseService<PlManageMoneyPlan> {
	
	List<PlManageMoneyPlan> getMoneyPlanByType(Long typeId);
	
	PlManageMoneyPlan bidDynamic(PlManageMoneyPlan moneyPlan);
	BpCustMember getMemmber(BpCustMember bpcm);
	List<PlManageMoneyPlan> getMoneyPlan(HttpServletRequest request,boolean isByb);

	public Boolean getPlanLimitStatus(PlManageMoneyPlan plManageMoneyPlan);
	List<PlManageMoneyPlan> getMoneyPlanBYsategroup(Integer state);
	Pager getmyplmanage(Pager p);
	List<PlManageMoneyPlan> getPlmanage();
	public List<PlManageMoneyPlan> getFinishProduct(Integer state);
	public List<PlManageMoneyPlan> getPresellProduct(Integer state);
	public List<PlManageMoneyPlan> getOpenProduct(Integer state);

	public List<PlManageMoneyPlan> getCurrentList();
	
	/**
	 * 处理体验标
	 */
	public PlManageMoneyPlan setDetail(PlManageMoneyPlan plManageMoneyPlan);

	/**
	 * 查询U计划列表
	 * @param typeId  计划类别id
	 * @return
	 */
	public List<PlManageMoneyPlan> getUPlanList(String typeId);
	/**
	 * 注册账户起息方法
	 * @param orgPlManageMoneyPlan
	 * @param plManageMoneyPlanBuyinfo
	 * @return
	 */
	public void zczhLoan(PlManageMoneyPlan orgPlManageMoneyPlan,PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo);
	/**
	 * 平台账户起息方法
	 * @param orgPlManageMoneyPlan
	 * @param plManageMoneyPlanBuyinfo
	 * @return
	 */
	public void ptzhLoan(PlManageMoneyPlan orgPlManageMoneyPlan,PlManageMoneyPlanBuyinfo plManageMoneyPlanBuyinfo);
	/**
	 *查询计划LogoUrl
	 * @param moneyPlan
	 * @return
	 */
	PlManageMoneyPlan setLogoUrl(PlManageMoneyPlan moneyPlan);
	/**
	 *根据收款用户名称查询理财计划列表
	 */
	public List<PlManageMoneyPlan> listByMoneyReceiver(String moneyReceiver);
	
	
}