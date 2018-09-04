package com.hurong.credit.dao.creditFlow.financingAgency.business.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.business.BpBusinessDirProDao;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpBusinessDirProDaoImpl extends BaseDaoImpl<BpBusinessDirPro> implements BpBusinessDirProDao{

	public BpBusinessDirProDaoImpl() {
		super(BpBusinessDirPro.class);
	}

	@Override
	public List<BpBusinessDirPro> getBusinessId(Long bDirProId) {
		StringBuffer sql = new StringBuffer("select b.businessId from bp_business_dir_pro b where b.bdirProId="+bDirProId);
		System.out.println(sql);

		return this.getSession().createSQLQuery(sql.toString()).
				addScalar("businessId", Hibernate.LONG).
				setResultTransformer(Transformers.aliasToBean(BpBusinessDirPro.class)).list();
	}
}