package com.hurong.credit.dao.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.user.BpCustLoginlogDao;
import com.hurong.credit.model.user.BpCustLoginlog;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCustLoginlogDaoImpl extends BaseDaoImpl<BpCustLoginlog> implements BpCustLoginlogDao{

	public BpCustLoginlogDaoImpl() {
		super(BpCustLoginlog.class);
	}
	@Override
	public List<BpCustLoginlog> listByMemberId(Long id){
		String hql = " from BpCustLoginlog where memberId=?";
		return this.findByHql(hql, new Object[]{id});
	}
}