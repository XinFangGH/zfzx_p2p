package com.hurong.credit.dao.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.user.CsDicAreaDynamDao;
import com.hurong.credit.model.user.CsDicAreaDynam;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class CsDicAreaDynamDaoImpl extends BaseDaoImpl<CsDicAreaDynam> implements CsDicAreaDynamDao{

	public CsDicAreaDynamDaoImpl() {
		super(CsDicAreaDynam.class);
	}
/**
 * @param parentId 当为0时是省，不为0时为市
 */
	@Override
	public List<CsDicAreaDynam> getAreaList(Integer parentId) {
		// TODO Auto-generated method stub 
		List<CsDicAreaDynam> list =null;
		StringBuffer hql = new StringBuffer("from CsDicAreaDynam where parentId = ").append(parentId);
		list = (List<CsDicAreaDynam>) getSession().createQuery(hql.toString());
		
		return list;
	}
@Override
public List<CsDicAreaDynam> listByParentId(Integer parentId) {
	String hql="from CsDicAreaDynam AS a where a.parentId =? and a.isOld=0 order by a.orderid ";
	return this.findByHql(hql,new Object[]{parentId});
}
@Override
public String queryDicArea(Long id) {
	String sql = "select dic.title from cs_dic_area_dynam as dic where dic.id=? ";
	Object obj = this.getSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
	return String.valueOf(obj);

}

}