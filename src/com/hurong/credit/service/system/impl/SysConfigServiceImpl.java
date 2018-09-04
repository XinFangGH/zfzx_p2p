package com.hurong.credit.service.system.impl;
/*
 *  北京互融时代软件有限公司 OA办公管理系统   -- http://www.hurongtime.com
 *  Copyright (C) 2008-2011 hurong Software Company
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.system.SysConfigDao;
import com.hurong.credit.model.system.SysConfig;
import com.hurong.credit.service.system.SysConfigService;

public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService{
	private SysConfigDao dao;
	
	public SysConfigServiceImpl(SysConfigDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public SysConfig findByKey(String key) {
		return dao.findByKey(key);
	}

	@Override
	public Map findByType() {
		List<String> list=dao.findTypeKeys();
		Map cList=new HashMap();
		for(String typeKey:list){
			List<SysConfig> confList=dao.findConfigByTypeKey(typeKey);
			cList.put(typeKey, confList);
		}
		return cList;
	}

	@Override
	public void delP2PConfig(String key) {
		dao.delP2PConfig(key);
	}

}