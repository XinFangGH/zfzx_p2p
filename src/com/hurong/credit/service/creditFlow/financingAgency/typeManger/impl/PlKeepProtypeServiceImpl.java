package com.hurong.credit.service.creditFlow.financingAgency.typeManger.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.typeManger.PlKeepProtypeDao;
import com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlKeepProtype;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlKeepProtypeService;

/**
 * 
 * @author 
 *
 */
public class PlKeepProtypeServiceImpl extends BaseServiceImpl<PlKeepProtype> implements PlKeepProtypeService{
	@SuppressWarnings("unused")
	private PlKeepProtypeDao dao;
	
	public PlKeepProtypeServiceImpl(PlKeepProtypeDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlKeepProtype> getList(Short isDelete) {
		String hql = " from PlKeepProtype where isDelete is null or isDelete = ?";
		return dao.findByHql(hql, new Object[]{isDelete});
	}

	@Override
	public PlKeepProtype getByKeyStr(String keyStr) {
		String hql = " from PlKeepProtype where keyStr = ?";
		List<PlKeepProtype> list = dao.findByHql(hql, new Object[]{keyStr});
		if(null!=list&&list.size()!=0&&null!=list.get(0)){
			return list.get(0);
		}
		return null;
	}

}