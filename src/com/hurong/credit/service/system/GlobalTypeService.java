package com.hurong.credit.service.system;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.system.GlobalType;

public interface GlobalTypeService  extends BaseService<GlobalType> {

	/**
	 * 根据node key 获取节点
	 */
	public List<GlobalType> getByNodeKey(String nodeKey);
}
