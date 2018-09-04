package com.hurong.credit.dao.creditFlow.smallLoan.project;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.smallLoan.project.SlSmallloanProject;

public interface SlSmallloanProjectDao extends BaseDao<SlSmallloanProject>  {

	public List<SlSmallloanProject> getSmallLoan(Long oppositeID);
}
