package com.hurong.credit.dao.credit.thirdInterface;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;

/**
 * 
 * @author 
 *
 */
public interface WebBankCodeFudianDao extends BaseDao<WebBankCodeFudian>{

	WebBankCodeFudian getFudianRoleByBankCode(String bankId);
	
}