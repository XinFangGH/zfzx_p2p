package com.hurong.credit.service.creditFlow.financingAgency.business.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.business.PlBusinessDirProKeepDao;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import com.hurong.credit.service.creditFlow.financingAgency.business.PlBusinessDirProKeepService;

import java.util.List;

/**
 * 
 * @author 
 *
 */
public class PlBusinessDirProKeepServiceImpl extends BaseServiceImpl<PlBusinessDirProKeep> implements PlBusinessDirProKeepService{
	@SuppressWarnings("unused")
	private PlBusinessDirProKeepDao dao;
	
	public PlBusinessDirProKeepServiceImpl(PlBusinessDirProKeepDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public PlBusinessDirProKeep getDirProKeepByDirIdType(Long dirId, String type) {
		return dao.getDirProKeepByDirIdType(dirId,type);
	}

	@Override
	public String getImgUrl(String string) {
		return dao.getImgUrl(string);
	}

	@Override
	public PlBusinessDirProKeep getDirProKeepByDirIdType1(Long bdirProId, String proType) {
		return dao.getDirProKeepByDirIdType1(bdirProId,proType);
	}

	@Override
	public List<PlBusinessDirProKeep> getDetail(Long keepId) {
		return dao.getDetail(keepId);
	}


}