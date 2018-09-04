package com.hurong.credit.dao.financingAgency.manageMoney;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;

public interface PlManageMoneyTypeDao extends BaseDao<PlManageMoneyType> {

	public List<PlManageMoneyType> getcurrentType();
	
}
