package com.hurong.credit.service.system.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.system.GlobalTypeDao;
import com.hurong.credit.model.system.GlobalType;
import com.hurong.credit.service.system.GlobalTypeService;

public class GlobalTypeServiceImpl extends BaseServiceImpl<GlobalType> implements GlobalTypeService {
	
	private GlobalTypeDao dao ;
	public GlobalTypeServiceImpl(GlobalTypeDao dao){
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<GlobalType> getByNodeKey(String nodeKey) {
		return dao.getByNodeKey(nodeKey);
	}
}
