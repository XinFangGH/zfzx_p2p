package com.hurong.credit.dao.creditFlow.log.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.log.UserloginlogsDao;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;

@SuppressWarnings("unchecked")
public class UserloginlogsDaoImpl extends BaseDaoImpl<Userloginlogs> implements
		UserloginlogsDao {

	public UserloginlogsDaoImpl() {
		super(Userloginlogs.class);
	}

	@Override
	public Userloginlogs getLoginlogs(String loginUser) {
		String sql ="select * from sys_userloginlogs log where log.userLoginName=? ORDER BY log.loginTime DESC LIMIT 1,2 ";
		List<Userloginlogs> list = this.getSession().createSQLQuery(sql).addEntity(Userloginlogs.class).setParameter(0, loginUser).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}
