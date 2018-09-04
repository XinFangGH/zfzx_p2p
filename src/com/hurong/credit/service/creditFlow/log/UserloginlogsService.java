package com.hurong.credit.service.creditFlow.log;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;

public interface UserloginlogsService extends BaseService<Userloginlogs> {

	public Userloginlogs getLoginlogs(String loginUser);
}
