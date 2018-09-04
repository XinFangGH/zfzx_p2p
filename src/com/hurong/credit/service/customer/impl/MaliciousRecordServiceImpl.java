package com.hurong.credit.service.customer.impl;

import com.hurong.core.dao.GenericDao;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.model.customer.MaliciousRecord;
import com.hurong.credit.service.customer.MaliciousRecordService;

public class MaliciousRecordServiceImpl extends BaseServiceImpl<MaliciousRecord> implements MaliciousRecordService{

	@SuppressWarnings("unchecked")
	public MaliciousRecordServiceImpl(@SuppressWarnings("rawtypes") GenericDao dao) {
		super(dao);
		this.dao=dao;
	}

}
