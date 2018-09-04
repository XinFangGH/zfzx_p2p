package com.hurong.credit.dao.p2p.redMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.p2p.redMoney.BpCustRedMember;

/**
 * 
 * @author 
 *
 */
public interface BpCustRedMemberDao extends BaseDao<BpCustRedMember>{
	public List<BpCustRedMember> getActivityNumber(String activityNumber,String bpCustMemberId, String remark);
}