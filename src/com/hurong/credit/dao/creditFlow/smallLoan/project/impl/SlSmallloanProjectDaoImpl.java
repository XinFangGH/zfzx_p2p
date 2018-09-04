package com.hurong.credit.dao.creditFlow.smallLoan.project.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.smallLoan.project.SlSmallloanProjectDao;
import com.hurong.credit.model.creditFlow.smallLoan.project.SlSmallloanProject;

@SuppressWarnings("unchecked")
public class SlSmallloanProjectDaoImpl extends BaseDaoImpl<SlSmallloanProject> implements SlSmallloanProjectDao {

	public SlSmallloanProjectDaoImpl() {
		super(SlSmallloanProject.class);
	}

	@Override
	public List<SlSmallloanProject> getSmallLoan(Long oppositeID) {
		String hql = "from SlSmallloanProject as loan where loan.oppositeID=? and loan.oppositeType='person_customer'";
		return this.getSession().createQuery(hql).setParameter(0, oppositeID).list();
	}
}
