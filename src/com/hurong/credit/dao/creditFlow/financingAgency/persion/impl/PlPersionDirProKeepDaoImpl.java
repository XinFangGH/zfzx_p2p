package com.hurong.credit.dao.creditFlow.financingAgency.persion.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.PlPersionDirProKeepDao;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlPersionDirProKeepDaoImpl extends BaseDaoImpl<PlPersionDirProKeep> implements PlPersionDirProKeepDao{

	public PlPersionDirProKeepDaoImpl() {
		super(PlPersionDirProKeep.class);
	}

	@Override
	public PlPersionDirProKeep getDirProKeepByDirIdType(Long dirId, String type) {
		String hql = "";
		if(type.equals("P_Dir")){
			hql = "from PlPersionDirProKeep keep where keep.bpPersionDirPro.pdirProId=? and keep.proType=?";
		}else if(type.equals("P_Or")){
			hql = "from PlPersionDirProKeep keep where keep.bpPersionOrPro.porProId=? and keep.proType=?";
		}
		Object[] params = {dirId,type};
		List<PlPersionDirProKeep> list=findByHql(hql, params);
		if(list.size()>0){
		return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<PlPersionDirProKeep> getInfo(Long pdirProId) {
		StringBuffer sql = new StringBuffer("select p.mainFinance" +
				",p.mainDebt"+
				",p.proDes" +
				",p.proUseWay"+
				",p.proPayMoneyWay"+
				" from pl_persion_dir_pro_keep p "+
				"where pdirProId =" +
				pdirProId);
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql.toString()).
				addScalar("mainFinance", Hibernate.STRING).
				addScalar("mainDebt",Hibernate.STRING).
				addScalar("proDes",Hibernate.STRING).
				addScalar("proUseWay",Hibernate.STRING).
				addScalar("proPayMoneyWay",Hibernate.STRING).
				setResultTransformer(Transformers.aliasToBean(PlPersionDirProKeep.class)).list();
	}
}