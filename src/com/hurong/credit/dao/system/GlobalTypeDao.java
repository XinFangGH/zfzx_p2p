package com.hurong.credit.dao.system;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.system.GlobalType;

public interface GlobalTypeDao extends BaseDao<GlobalType>{

	/**
	 * 根据node key 获取节点列表
	 */
	public List<GlobalType> getByNodeKey(String nodeKey);
}
