package com.hurong.credit.dao.system;
/*
 *  北京互融时代软件有限公司 OA办公管理系统   --  http://www.hurongtime.com
 *  Copyright (C) 2008-2011 hurong Software Company
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.system.SysConfig;

/**
 * 
 * @author 
 *
 */
public interface SysConfigDao extends BaseDao<SysConfig>{
	
	public SysConfig findByKey(String key);
	
	public List<SysConfig> findConfigByTypeKey(String typeKey);
	
	public List findTypeKeys();

	public void delP2PConfig(String key);
	
}