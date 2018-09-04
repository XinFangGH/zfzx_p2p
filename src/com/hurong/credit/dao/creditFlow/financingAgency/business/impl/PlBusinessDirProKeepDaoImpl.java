package com.hurong.credit.dao.creditFlow.financingAgency.business.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.financingAgency.business.PlBusinessDirProKeepDao;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlBusinessDirProKeepDaoImpl extends BaseDaoImpl<PlBusinessDirProKeep> implements PlBusinessDirProKeepDao{

	public PlBusinessDirProKeepDaoImpl() {
		super(PlBusinessDirProKeep.class);
	}

	@Override
	public PlBusinessDirProKeep getDirProKeepByDirIdType(Long dirId, String type) {
		String hql ="";
		
		if(type.equals("B_Dir")){
			hql = "from PlBusinessDirProKeep keep where keep.bpBusinessDirPro.bdirProId=? and keep.proType=?";
		}else if(type.equals("B_Or")){
			hql = "from PlBusinessDirProKeep keep where keep.bpBusinessOrPro.borProId=? and keep.proType=?";
		}
		
		Object[] params = {dirId,type};
		return (PlBusinessDirProKeep)findUnique(hql, params);
	}
	
	/**
	 * 查询友情链接图片展示路径
	 * String :mark值   四张基础表表名.四张表主键（查询条件）例：mark = 'bp_business_dir_pro.236'
	 */
	public String getImgUrl(String string){
		List list=null;
		String ret="";
		Map<String, String> params=new HashMap<String, String>();
		params.put("mark", string);
		list=this.executeSqlFind("cs_file","webPath",params);
		if(list!=null&&list.size()>0){
			ret= list.get(0).toString();
		}else{
			ret= "0";
		}
		return ret;
	}

	@Override
	public PlBusinessDirProKeep getDirProKeepByDirIdType1(Long bdirProId, String proType) {
		String hql ="";
		hql = "from PlBusinessDirProKeep keep where keep.bpBusinessDirPro.bdirProId=? and keep.proType=?";
		Object[] params = {bdirProId,proType};
		return (PlBusinessDirProKeep)findUnique(hql, params);
	}

	@Override
	public List<PlBusinessDirProKeep> getDetail(Long id) {
		StringBuffer sql = new StringBuffer("select p.proBusinessScope"+
							",p.mainDebt"+
							",p.proUseWay"+
							",p.proPayMoneyWay"+
							",p.proDes from pl_business_dir_pro_keep p where p.bDirProId="+
							id);
		System.out.println(sql);
		return this.getSession().createSQLQuery(sql.toString()).
				addScalar("proBusinessScope", Hibernate.TEXT).
				addScalar("mainDebt",Hibernate.TEXT).
				addScalar("proUseWay",Hibernate.TEXT).
				addScalar("proPayMoneyWay",Hibernate.TEXT).
				addScalar("proDes",Hibernate.TEXT).
				setResultTransformer(Transformers.aliasToBean(PlBusinessDirProKeep.class)).list();

	}
}