package com.hurong.credit.dao.system.product.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.system.product.DictionaryDao;
import com.hurong.credit.model.system.product.Dictionary;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements DictionaryDao{

	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	@Override
	public List<Dictionary> getByProTypeId(long proTypeId) {
		/*String hql="from Dictionary where proTypeId=? and status=0";
		return getSession().createQuery(hql).setParameter(0, proTypeId).list();*/
		String sql = "select * from dictionary where proTypeId=? and status=0";
		return this.getSession().createSQLQuery(sql).addEntity(Dictionary.class).setParameter(0, proTypeId).list();
	}
	@Override
	public List<Dictionary> getByProTypeId2(long proTypeId,String status) {
		/*String hql="from Dictionary where proTypeId=? and status=0";
		return getSession().createQuery(hql).setParameter(0, proTypeId).list();*/
		String sql = "select * from dictionary where proTypeId=? and status=?";
		return this.getSession().createSQLQuery(sql).addEntity(Dictionary.class).setParameter(0, proTypeId).setParameter(1, status).list();
	}
	
	
	@Override
	public String getQueryDicId(long dicId) {
		/*String sql = "select dic.itemValue from dictionary as dic where dic.dicId=? AND dic.status=0";
		return (String)this.getSession().createSQLQuery(sql).setParameter(0, dicId).uniqueResult();*/
		String sql = "select dic.itemValue from Dictionary as dic where dic.dicId=? AND dic.status=0";
		return (String)this.getSession().createQuery(sql).setParameter(0, dicId).uniqueResult();
	}

}