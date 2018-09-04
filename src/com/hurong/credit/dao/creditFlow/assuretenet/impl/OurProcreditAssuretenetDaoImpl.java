package com.hurong.credit.dao.creditFlow.assuretenet.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.assuretenet.OurProcreditAssuretenetDao;
import com.hurong.credit.dao.creditFlow.assuretenet.SlProcreditAssuretenetDao;
import com.hurong.credit.model.creditFlow.assuretenet.SlProcreditAssuretenet;
import com.hurong.credit.model.creditFlow.assuretenet.OurProcreditAssuretenet;
/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class OurProcreditAssuretenetDaoImpl extends BaseDaoImpl<OurProcreditAssuretenet> implements OurProcreditAssuretenetDao{

	public OurProcreditAssuretenetDaoImpl() {
		super(OurProcreditAssuretenet.class);
	}
    @Resource
    private SlProcreditAssuretenetDao procreditAssuretenetDao;
    
	@Override
	public boolean initAssuretenet(String projId, Integer businessType) {
		
		String hql = "from OurProcreditAssuretenet sm  where sm.businessTypeId=?";
		Object[] objs={businessType.intValue()};
		int i = 1;
		List<OurProcreditAssuretenet> list=this.findByHql(hql, objs);
		for(OurProcreditAssuretenet dictionary : list){
			SlProcreditAssuretenet assuretenet = new SlProcreditAssuretenet();
			assuretenet.setAssuretenet(dictionary.getAssuretenet());
			assuretenet.setProjid(projId);
			//assuretenet.setBusinessTypeId(businessType);
			assuretenet.setSortvalue(Integer.toString(i));
			i++;
			procreditAssuretenetDao.save(assuretenet);
		}
		return true;
		
	}

	@Override
	public List<OurProcreditAssuretenet> getListByBussinessType(String businessTypeKey) {
		String hql = "from OurProcreditAssuretenet sm  where sm.businessTypeKey=?";
		Object[] objs={businessTypeKey};
		//List<OurProcreditAssuretenet> list=this.findByHql(hql, objs);
		return this.findByHql(hql, objs);
	}

	@Override
	public boolean initAssuretenet(String projectId, String businessTypeKey,String operationTypeKey,String customerType) {
		String hql = "from OurProcreditAssuretenet sm  where sm.businessTypeKey=? and sm.operationTypeKey like '%"+operationTypeKey+"%'";
		if(customerType.equals("company_customer")){
			hql+=" and sm.customerType='company'";
		}else{
			hql+=" and sm.customerType='person'";
		}
		Object[] objs={businessTypeKey};
		int i = 1;
		List<OurProcreditAssuretenet> list=this.findByHql(hql, objs);
		for(OurProcreditAssuretenet dictionary : list){
			SlProcreditAssuretenet assuretenet = new SlProcreditAssuretenet();
			assuretenet.setAssuretenet(dictionary.getAssuretenet());
			assuretenet.setProjid(projectId);
			assuretenet.setBusinessTypeKey(businessTypeKey);
			assuretenet.setSortvalue(Integer.toString(i));
			i++;
			procreditAssuretenetDao.save(assuretenet);
		}
		return false;
	}

	@Override
	public void deleteByProductId(String id) {
		String hql="delete from OurProcreditAssuretenet as o where o.productId=?";
		Query query = getSession().createQuery(hql).setLong(0, Long.valueOf(id));
		query.executeUpdate();
	}

	@Override
	public List<OurProcreditAssuretenet> getByProductId(Long productId) {
		String hql = "from OurProcreditAssuretenet sm  where sm.productId="+productId;
		return this.findByHql(hql);
	}

	@Override
	public List<OurProcreditAssuretenet> getByProjectId(Long projectId) {
		String hql = "from OurProcreditAssuretenet sm  where sm.projectId="+projectId;
		return this.findByHql(hql);
	}
	

}