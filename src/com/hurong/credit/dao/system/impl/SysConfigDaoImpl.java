package com.hurong.credit.dao.system.impl;
/*
 *  北京互融时代软件有限公司 OA办公管理系统   --  http://www.hurongtime.com
 *  Copyright (C) 2008-2011 hurong Software Company
*/
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.system.SysConfigDao;
import com.hurong.credit.model.system.SysConfig;

public class SysConfigDaoImpl extends BaseDaoImpl<SysConfig> implements SysConfigDao{

	public SysConfigDaoImpl() {
		super(SysConfig.class);
	}

	@Override
	public SysConfig findByKey(String key) {
		String hql="from SysConfig vo where vo.configKey=?";
		Object[] objs={key};
		List<SysConfig> list=findByHql(hql, objs);
		if(list.size()>0)
		return (SysConfig)list.get(0);
		else return null;
	}

	@Override
	public List<SysConfig> findConfigByTypeKey(String typeKey) {
		String hql="from SysConfig vo where vo.typeKey=?";
		Object[] objs={typeKey};
		return findByHql(hql, objs);
	}

	@Override
	public List findTypeKeys() {
		String sql="select vo.typeKey from SysConfig vo group by vo.typeKey";
		Query query=getSession().createQuery(sql);
		return query.list();
	}

	@Override
	public void delP2PConfig(String key) {
		Session session=null;
		try{
			session=this.getSession();
			String sql="delete from sys_config where typeKey='"+key+"'";
			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=session){
				releaseSession(session);
			}
		}
	}
}