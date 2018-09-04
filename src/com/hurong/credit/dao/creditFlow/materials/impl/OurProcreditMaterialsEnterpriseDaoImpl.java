package com.hurong.credit.dao.creditFlow.materials.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.materials.OurProcreditMaterialsEnterpriseDao;
import com.hurong.credit.model.creditFlow.materials.OurProcreditMaterialsEnterprise;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class OurProcreditMaterialsEnterpriseDaoImpl extends BaseDaoImpl<OurProcreditMaterialsEnterprise> implements OurProcreditMaterialsEnterpriseDao{

	public OurProcreditMaterialsEnterpriseDaoImpl() {
		super(OurProcreditMaterialsEnterprise.class);
	}
  
	@Override
	public List<OurProcreditMaterialsEnterprise> getListByParentId(
			Integer parentId) {
		String hql = "from OurProcreditMaterialsEnterprise AS a where a.parentId =?";
		Object[] objs={parentId};
		return findByHql(hql, objs);
	}
	

	@Override
	public void initMaterials(String projectId, Integer businessType) {}


	@Override
	public void initMaterials(String projectId,String businessTypeKey,String operationTypeKey) {}


	@Override
	public List<OurProcreditMaterialsEnterprise> getListByParentIdAndType(
			Integer parentId, String operationTypeKey) {
		String hql = "from OurProcreditMaterialsEnterprise AS a where a.parentId =? and a.operationTypeKey=?";
		Object[] objs={parentId,operationTypeKey};
		return findByHql(hql, objs);
	}


	@Override
	public void deleteByProductId(String id) {
		String hql="delete from OurProcreditMaterialsEnterprise as o where o.productId=?";
		Query query = getSession().createQuery(hql).setLong(0, Long.valueOf(id));
		query.executeUpdate();
	}


	@Override
	public List<OurProcreditMaterialsEnterprise> getByProductId(Long productId) {
		String hql = "from OurProcreditMaterialsEnterprise AS a where a.productId =? ";
		return getSession().createQuery(hql).setLong(0, Long.valueOf(productId)).list();
	}


	@Override
	public List<OurProcreditMaterialsEnterprise> getByProjectId(Long projectId) {
		String hql = "from OurProcreditMaterialsEnterprise AS a where a.projectId =? ";
		return getSession().createQuery(hql).setLong(0, Long.valueOf(projectId)).list();
	}
}