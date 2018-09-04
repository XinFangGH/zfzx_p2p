package com.hurong.credit.service.creditFlow.smallLoan.project.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.smallLoan.project.SlSmallloanProjectDao;
import com.hurong.credit.model.creditFlow.smallLoan.project.SlSmallloanProject;
import com.hurong.credit.service.creditFlow.smallLoan.project.SlSmallloanProjectService;

public class SlSmallloanProjectServiceImpl extends BaseServiceImpl<SlSmallloanProject> implements SlSmallloanProjectService {

	@SuppressWarnings("unused")
	private SlSmallloanProjectDao dao;
	public SlSmallloanProjectServiceImpl(SlSmallloanProjectDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<SlSmallloanProject> getSmallLoan(Long oppositeID) {
		// TODO Auto-generated method stub
		return dao. getSmallLoan(oppositeID);
	}
}
