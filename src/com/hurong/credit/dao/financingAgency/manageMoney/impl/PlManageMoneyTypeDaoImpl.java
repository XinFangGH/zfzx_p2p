package com.hurong.credit.dao.financingAgency.manageMoney.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyTypeDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;

@SuppressWarnings("unchecked")
public class PlManageMoneyTypeDaoImpl extends BaseDaoImpl<PlManageMoneyType> implements
		PlManageMoneyTypeDao {

	public PlManageMoneyTypeDaoImpl() {
		super(PlManageMoneyType.class);
	}

	@Override
	public List<PlManageMoneyType> getcurrentType() {
		// TODO Auto-generated method stub
		String hql="SELECT * "+
					"FROM "+
						"pl_managemoney_type AS p "+
					"WHERE "+
						"p.keystr = 'mmplan' "+
						"AND p.manageMoneyTypeId NOT IN ( "+
							"SELECT "+
								"pp.manageMoneyTypeId "+
							"FROM "+
								"pl_managemoney_plan AS pp "+
							"WHERE "+
								"pp.state IN (1, 2, 6, 7) "+
								"AND pp.keystr = 'mmplan' "+
							") " +
					"ORDER BY p.manageMoneyTypeId DESC";
		return this.getSession().createSQLQuery(hql).addEntity(PlManageMoneyType.class).list();
	}
}
