package com.hurong.credit.dao.pay.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.pay.BankCodeDao;
import com.hurong.credit.model.pay.BankCode;
import com.hurong.credit.model.user.BpDicArea;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BankCodeDaoImpl extends BaseDaoImpl<BankCode> implements BankCodeDao{

	public BankCodeDaoImpl() {
		super(BankCode.class);
	}
	/**
	 * 获取省市列表
	 * @param ParentCode
	 * @return
	 */
	@Override
	public List<BankCode> getAreaList(String ParentCode) {
		List<BankCode> list =null;
		StringBuffer hql = new StringBuffer("from BankCode where parentCode = ").append(ParentCode);
		list =  findByHql(hql.toString());
		
		return list;
	}

}