package com.hurong.credit.service.financingAgency.manageMoney.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyTypeDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyTypeService;

public class PlManageMoneyTypeServiceImpl extends BaseServiceImpl<PlManageMoneyType> implements
		PlManageMoneyTypeService {
	private PlManageMoneyTypeDao dao;
	public PlManageMoneyTypeServiceImpl(PlManageMoneyTypeDao dao) {
		super(dao);
		this.dao = dao;
	}
	@Override
	public List<PlManageMoneyType> getMoneyType(String keyStr, Short type) {
		if(type!=null){
			String hql = "from PlManageMoneyType as mt where mt.keystr = ? and mt.state=?";
			return dao.findByHql(hql, new Object[]{keyStr,type.intValue()});
		}else{
			String hql = "from PlManageMoneyType as mt where mt.keystr = ? ";
			return dao.findByHql(hql, new Object[]{keyStr});
		}
		
	}
	@Override
	public List<PlManageMoneyType> getcurrentType() {
		// TODO Auto-generated method stub
		return dao.getcurrentType();
	}
	
}
