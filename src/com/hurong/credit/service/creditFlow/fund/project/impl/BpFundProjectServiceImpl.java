package com.hurong.credit.service.creditFlow.fund.project.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.dao.GenericDao;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.service.creditFlow.fund.project.BpFundProjectService;

public class BpFundProjectServiceImpl extends BaseServiceImpl<BpFundProject> implements
		BpFundProjectService {
	private BpFundProjectDao dao;
	
	public BpFundProjectServiceImpl(BpFundProjectDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<BpFundProject> getByProjectIdAndFlag(Long projectId, Short flag) {
		return dao.getProjectIdAndFlag(projectId, flag);
	}

	@Override
	public BpFundProject getBpFundProject(Long projectId, Short flag) {
		List<BpFundProject> list = dao.getProjectIdAndFlag(projectId, flag);
		if(null!=list&&list.size()!=0&&null!=list.get(0)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BpFundProject> getPersonBpFundProject(
			HttpServletRequest request, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return dao.getPersonBpFundProject(request,start,limit);
	}

	@Override
	public List<BpFundProject> getBidLoanAfter() {
		return dao.getBidLoanAfter();
	}
}
