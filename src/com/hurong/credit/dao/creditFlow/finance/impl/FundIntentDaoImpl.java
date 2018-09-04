package com.hurong.credit.dao.creditFlow.finance.impl;

import java.util.List;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.finance.FundIntentDao;
import com.hurong.credit.model.creditFlow.finance.FundIntent;

public class FundIntentDaoImpl extends BaseDaoImpl<FundIntent> implements FundIntentDao {

	public FundIntentDaoImpl() {
		super(FundIntent.class);
	}

	@Override
	public List<FundIntent> getList(String pname, String[] names, Object[] values) {
		String hql = " from "+pname+ "  c where 1 =1 ";
		if(names.length!=0){
			for(int i=0; i<names.length;i++){
				hql += " and c."+names[i]+" = ? ";
			}
		}
		return super.findByHql(hql, values);
	}
}
