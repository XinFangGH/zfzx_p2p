package com.hurong.credit.service.financingAgency.manageMoney;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;

public interface InvestprojectService extends BaseService<Investproject> {

	public List<Investproject> getListInvers();
}
