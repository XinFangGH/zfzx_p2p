package com.hurong.credit.dao.p2p.redMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.p2p.redMoney.BpCustRedMemberDao;
import com.hurong.credit.model.p2p.redMoney.BpCustRedMember;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCustRedMemberDaoImpl extends BaseDaoImpl<BpCustRedMember> implements BpCustRedMemberDao{

	public BpCustRedMemberDaoImpl() {
		super(BpCustRedMember.class);
	}

	@Override
	public List<BpCustRedMember> getActivityNumber(String activityNumber,
			String bpCustMemberId, String remark) {
		String sql = " from BpCustRedMember where bpCustMemberId=? and activityNumber=?";
		return this.getSession().createQuery(sql).setParameter(0, Long.valueOf(bpCustMemberId)).setParameter(1, activityNumber).list();
	}


}