package com.hurong.credit.dao.customer.impl;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.customer.MaliciousRecordDAO;
import com.hurong.credit.model.customer.MaliciousRecord;

@SuppressWarnings("unchecked")
public class MaliciousRecordDAOImpl extends BaseDaoImpl<MaliciousRecord> implements MaliciousRecordDAO{

	public MaliciousRecordDAOImpl() {
		super(MaliciousRecord.class);
	}

}
