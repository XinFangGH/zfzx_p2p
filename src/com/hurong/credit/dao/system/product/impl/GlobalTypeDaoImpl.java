package com.hurong.credit.dao.system.product.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.system.GlobalTypeDao;
import com.hurong.credit.model.system.GlobalType;

@SuppressWarnings("unchecked")
public class GlobalTypeDaoImpl  extends BaseDaoImpl<GlobalType>  implements GlobalTypeDao {

	public GlobalTypeDaoImpl(){
		super(GlobalType.class);
	}

	@Override
	public List<GlobalType> getByNodeKey(String nodeKey) {
		/*String hql=" from GlobalType gt where gt.nodeKey = ? and gt.status=0 order by gt.sn asc";
		
		return findByHql(hql, new Object[]{nodeKey});*/
		String sql = "select *  from global_type as gt where gt.nodeKey = ? and gt.status=0 order by gt.sn asc";
		return this.getSession().createSQLQuery(sql).addEntity(GlobalType.class).setParameter(0, nodeKey).list();
	}
}
