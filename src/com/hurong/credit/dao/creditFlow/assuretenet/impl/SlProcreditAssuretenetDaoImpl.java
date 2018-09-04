package com.hurong.credit.dao.creditFlow.assuretenet.impl;
/*
 *  北京互融时代软件有限公司 hurong协同办公管理系统   -- http://www.hurongtime.com/
 *  Copyright (C) 2008-2010 BEIJING JINZHIWANWEI SOFTWARES LIMITED COMPANY
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.assuretenet.SlProcreditAssuretenetDao;
import com.hurong.credit.model.creditFlow.assuretenet.SlProcreditAssuretenet;

@SuppressWarnings("unchecked")
public class SlProcreditAssuretenetDaoImpl extends BaseDaoImpl<SlProcreditAssuretenet> implements SlProcreditAssuretenetDao{

	public SlProcreditAssuretenetDaoImpl() {
		super(SlProcreditAssuretenet.class);
	}
	
	//根据项目ID查询准入原则
	@Override
	public List<SlProcreditAssuretenet> getByProjId(String projId,String businessType) {
		String hql = "from SlProcreditAssuretenet sa where sa.projid=? and sa.businessTypeKey=?";
		Object[] objs={projId,businessType};
		return findByHql(hql, objs);
	}
}