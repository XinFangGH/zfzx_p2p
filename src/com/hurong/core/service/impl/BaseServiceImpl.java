package com.hurong.core.service.impl;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import com.hurong.core.dao.GenericDao;
import com.hurong.core.service.BaseService;

public class BaseServiceImpl<T> extends GenericServiceImpl<T, Long> implements BaseService<T>{

	public BaseServiceImpl(GenericDao dao) {
		super(dao);
	}
	
}
