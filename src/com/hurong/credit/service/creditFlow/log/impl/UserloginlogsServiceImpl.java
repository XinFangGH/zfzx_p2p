package com.hurong.credit.service.creditFlow.log.impl;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.log.UserloginlogsDao;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.service.creditFlow.log.UserloginlogsService;

public class UserloginlogsServiceImpl  extends BaseServiceImpl<Userloginlogs> implements UserloginlogsService {

	@SuppressWarnings("unused")
	private UserloginlogsDao dao;
	public UserloginlogsServiceImpl(UserloginlogsDao dao) {
		super(dao);
		this.dao = dao;
	}
	@Override
	public Userloginlogs getLoginlogs(String loginUser) {
		return dao.getLoginlogs(loginUser);
	}
	
}
