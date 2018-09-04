package com.hurong.credit.service.creditFlow.materials.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.materials.OurProcreditMaterialsEnterpriseDao;
import com.hurong.credit.model.creditFlow.materials.OurProcreditMaterialsEnterprise;
import com.hurong.credit.service.creditFlow.materials.OurProcreditMaterialsEnterpriseService;

/**
 * 
 * @author 
 *
 */
public class OurProcreditMaterialsEnterpriseServiceImpl extends BaseServiceImpl<OurProcreditMaterialsEnterprise> implements OurProcreditMaterialsEnterpriseService{
	private OurProcreditMaterialsEnterpriseDao dao;
	
	public OurProcreditMaterialsEnterpriseServiceImpl(OurProcreditMaterialsEnterpriseDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<OurProcreditMaterialsEnterprise> getListByParentId(
			Integer parentId) {
		// TODO Auto-generated method stub
		return this.dao.getListByParentId(parentId);
	}

	@Override
	public List<OurProcreditMaterialsEnterprise> getListByParentIdAndType(
			Integer parentId, String operationTypeKey) {
		return dao.getListByParentIdAndType(parentId, operationTypeKey);
	}

	@Override
	public void deleteByProductId(String id) {
		dao.deleteByProductId(id);
	}

	@Override
	public List<OurProcreditMaterialsEnterprise> getByProjectId(Long projectId) {
		return dao.getByProjectId(projectId);
	}

	@Override
	public List<OurProcreditMaterialsEnterprise> getByProductId(Long productId) {
		return dao.getByProductId(productId);
	}

}