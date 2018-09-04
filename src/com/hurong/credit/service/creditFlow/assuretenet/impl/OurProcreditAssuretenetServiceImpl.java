package com.hurong.credit.service.creditFlow.assuretenet.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.assuretenet.OurProcreditAssuretenetDao;
import com.hurong.credit.model.creditFlow.assuretenet.OurProcreditAssuretenet;
import com.hurong.credit.service.creditFlow.assuretenet.OurProcreditAssuretenetService;

/**
 * 
 * @author 
 *
 */
public class OurProcreditAssuretenetServiceImpl extends BaseServiceImpl<OurProcreditAssuretenet> implements OurProcreditAssuretenetService{
	private OurProcreditAssuretenetDao dao;
	
	public OurProcreditAssuretenetServiceImpl(OurProcreditAssuretenetDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<OurProcreditAssuretenet> getListByBussinessType(
			String businessTypeKey) {
		
		return dao.getListByBussinessType(businessTypeKey);
	}

	@Override
	public void deleteByProductId(String id) {
		dao.deleteByProductId(id);
	}

	@Override
	public List<OurProcreditAssuretenet> getByProjectId(Long projectId) {
		return dao.getByProjectId(projectId);
	}

	@Override
	public List<OurProcreditAssuretenet> getByProductId(Long productId) {
		return dao.getByProductId(productId);
	}

}