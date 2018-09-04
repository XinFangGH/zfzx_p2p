package com.hurong.credit.dao.creditFlow.financingAgency.persion.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.BpPersionDirProDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import java.util.List;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpPersionDirProDaoImpl extends BaseDaoImpl<BpPersionDirPro> implements BpPersionDirProDao{

	public BpPersionDirProDaoImpl() {
		super(BpPersionDirPro.class);
	}


	@Override
	public List<BpPersionDirPro> getInfo(Long pdirProId) {
		StringBuffer sql = new StringBuffer("select " +
				"p.age"+
				",p.education" +
				",p.companyIndustry"+
				",p.companyScale" +
				",p.position"+
				",p.workCity" +
				",p.workTime" +
				" from bp_persion_dir_pro p "+
				"where pdirProId=" +
				pdirProId);
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql.toString()).
				addScalar("age", Hibernate.INTEGER).
				addScalar("education",Hibernate.STRING).
				addScalar("companyIndustry",Hibernate.STRING).
				addScalar("companyScale",Hibernate.STRING).
				addScalar("position",Hibernate.STRING).
				addScalar("workCity",Hibernate.STRING).
				addScalar("workTime",Hibernate.STRING).
				setResultTransformer(Transformers.aliasToBean(BpPersionDirPro.class)).list();
	}
}