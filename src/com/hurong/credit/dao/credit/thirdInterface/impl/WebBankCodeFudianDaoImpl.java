package com.hurong.credit.dao.credit.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.credit.thirdInterface.WebBankCodeFudianDao;
import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class WebBankCodeFudianDaoImpl extends BaseDaoImpl<WebBankCodeFudian> implements WebBankCodeFudianDao{

	public WebBankCodeFudianDaoImpl() {
		super(WebBankCodeFudian.class);
	}

	@Override
	public WebBankCodeFudian getFudianRoleByBankCode(String bankId) {
		String sql = "from WebBankCodeFudian where cardCode = ?";
		Object[] params = {bankId};
		List<WebBankCodeFudian> list = findByHql(sql,params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	

}