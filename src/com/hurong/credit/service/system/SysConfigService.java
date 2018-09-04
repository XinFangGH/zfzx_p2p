package com.hurong.credit.service.system;
/*
 *  北京互融时代软件有限公司 OA办公管理系统   -- http://www.hurongtime.com
 *  Copyright (C) 2008-2011 hurong Software Company
*/
import java.util.Map;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.system.SysConfig;

public interface SysConfigService extends BaseService<SysConfig>{
	/**
	 * 根据KEY来取配置对象
	 * @param key
	 * @return
	 */
	public SysConfig findByKey(String key);
	
	/**
	 * 按类查找配置列表
	 * @return
	 */
	public Map findByType();

	/**
	 * 删除sys_config表中typeKey="p2p_config"的记录
	 * @param key
	 * @return
	 */
	public void delP2PConfig(String key);
}


