package com.hurong.credit.dao.creditFlow.finance;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.finance.FundIntent;

public interface FundIntentDao extends BaseDao<FundIntent> {
	List<FundIntent> getList(String pname,String[] names,Object[] values);
}
