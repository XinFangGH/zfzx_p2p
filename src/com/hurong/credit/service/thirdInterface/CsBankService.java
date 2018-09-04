package com.hurong.credit.service.thirdInterface;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.thirdInterface.CsBank;
import com.hurong.credit.model.thirdInterface.WebBankcard;


/**
 * 
 * @author 
 *
 */
public interface CsBankService extends BaseService<CsBank>{
	public List<CsBank> getListByBankName(String bankName);
	public CsBank getByCardName(String bankId);
}


