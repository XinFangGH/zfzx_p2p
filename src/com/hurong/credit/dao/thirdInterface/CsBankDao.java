package com.hurong.credit.dao.thirdInterface;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.thirdInterface.CsBank;

/**
 * 
 * @author 
 *
 */
public interface CsBankDao extends BaseDao<CsBank>{
	public List<CsBank> getListByBankName(String bankName);
	public CsBank getByCardName(String bankId);
}