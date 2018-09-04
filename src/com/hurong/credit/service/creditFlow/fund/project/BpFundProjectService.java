package com.hurong.credit.service.creditFlow.fund.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;

public interface BpFundProjectService extends BaseService<BpFundProject>{
	
	List<BpFundProject> getByProjectIdAndFlag(Long projectId,Short flag);
	
	BpFundProject getBpFundProject(Long projectId,Short flag);
	
	public List<BpFundProject> getPersonBpFundProject(HttpServletRequest request,Integer start ,Integer limit);
    /**
     * 查询放款后的项目
     * @return
     */
	List<BpFundProject> getBidLoanAfter();
}
