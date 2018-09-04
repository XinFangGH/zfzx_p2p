package com.hurong.credit.dao.creditFlow.customer.person.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.customer.person.BpCustPersonEducationDao;
import com.hurong.credit.model.creditFlow.customer.person.BpCustPersonEducation;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCustPersonEducationDaoImpl extends BaseDaoImpl<BpCustPersonEducation> implements BpCustPersonEducationDao{

	public BpCustPersonEducationDaoImpl() {
		super(BpCustPersonEducation.class);
	}

	@Override
	public String getSchool(Long personId) {
		String ret="";
		Map<String, String> params=new HashMap<String, String>();
		params.put("personId", String.valueOf(personId));
		List list=this.executeSqlFind("bp_cust_person_education","educationSchool",params);
		if(list!=null&&list.size()>0){
			ret= list.get(0).toString();
		}else{
			ret= "未填";
		}
		//return findAssureTypeNameBySQL(ret);
		return ret;
	}

}