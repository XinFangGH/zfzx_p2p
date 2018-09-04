package com.hurong.credit.service.creditFlow.financingAgency.persion.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.PlPersionDirProKeepDao;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import com.hurong.credit.service.creditFlow.financingAgency.persion.PlPersionDirProKeepService;

import java.util.List;

/**
 * 
 * @author 
 *
 */
public class PlPersionDirProKeepServiceImpl extends BaseServiceImpl<PlPersionDirProKeep> implements PlPersionDirProKeepService{
	@SuppressWarnings("unused")
	private PlPersionDirProKeepDao dao;
	
	public PlPersionDirProKeepServiceImpl(PlPersionDirProKeepDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public PlPersionDirProKeep getDirProKeepByDirIdType(Long dirId, String type) {
		return dao.getDirProKeepByDirIdType(dirId,type);
	}

	@Override
	public List<PlPersionDirProKeep> getInfo(Long pdirProId) {
		return dao.getInfo(pdirProId);
	}
}