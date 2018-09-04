package com.hurong.credit.dao.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.thirdInterface.WebBankCodeDao;
import com.hurong.credit.model.thirdInterface.WebBankCode;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class WebBankCodeDaoImpl extends BaseDaoImpl<WebBankCode> implements WebBankCodeDao{

	public WebBankCodeDaoImpl() {
		super(WebBankCode.class);
	}

	@Override
	public WebBankCode getByCardName(String openBankId) {
		String hql="from WebBankCode as b where b.bankCode=?";
		Object[] o={openBankId};
		return (WebBankCode)findUnique(hql, o);
	}

}