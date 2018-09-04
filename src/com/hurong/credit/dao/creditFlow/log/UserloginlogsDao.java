package com.hurong.credit.dao.creditFlow.log;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;

public interface UserloginlogsDao extends BaseDao<Userloginlogs> {

	public Userloginlogs getLoginlogs(String loginUser);
}
