package com.hurong.credit.dao.creditFlow.fund.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;

public interface BpFundProjectDao extends BaseDao<BpFundProject> {

	List<BpFundProject> getProjectIdAndFlag(Long projectId,Short flag);
	
	public List<BpFundProject> getPersonBpFundProject(HttpServletRequest request,Integer start ,Integer limit);

	List<BpFundProject> getBidLoanAfter();
}
