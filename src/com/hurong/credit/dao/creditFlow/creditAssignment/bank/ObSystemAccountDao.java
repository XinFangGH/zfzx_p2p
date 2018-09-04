package com.hurong.credit.dao.creditFlow.creditAssignment.bank;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;

/**
 * 
 * @author 
 *
 */
public interface ObSystemAccountDao extends BaseDao<ObSystemAccount>{

	//根据投资人id查看系统虚拟账户
	public ObSystemAccount getByInvrstPersonId(Long investMentPersonId);

	/**查询投资人账户剩余余额*/
	public BigDecimal getBalance(String investPersonId);

	public ObSystemAccount getByInvrstPersonIdAndType(Long investPersionId,
			Short investPsersionType);

	public List<ObSystemAccount> findAccountList(String investName,
			String accountType, HttpServletRequest request, Integer start,
			Integer limit);
	//查询交易类型 
	public List<ObSystemaccountSetting> findObSystemaccountSetting();
}