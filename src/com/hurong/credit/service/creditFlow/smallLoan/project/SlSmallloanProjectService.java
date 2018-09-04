package com.hurong.credit.service.creditFlow.smallLoan.project;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.smallLoan.project.SlSmallloanProject;

public interface SlSmallloanProjectService extends BaseService<SlSmallloanProject> {

	public List<SlSmallloanProject> getSmallLoan(Long oppositeID);
}
