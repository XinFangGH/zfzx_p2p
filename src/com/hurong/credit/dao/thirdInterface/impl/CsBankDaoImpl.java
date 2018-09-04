package com.hurong.credit.dao.thirdInterface.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.thirdInterface.CsBankDao;
import com.hurong.credit.model.thirdInterface.CsBank;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class CsBankDaoImpl extends BaseDaoImpl<CsBank> implements CsBankDao{

	public CsBankDaoImpl() {
		super(CsBank.class);
	}

	@Override
	public List<CsBank> getListByBankName(String bankName) {
		String hql="from CsBank as b where b.bankname=?";
		return getSession().createQuery(hql).setParameter(0, bankName).list();
	}

	@Override
	public CsBank getByCardName(String bankId) {
		// TODO Auto-generated method stub
		String hql="from CsBank as b where b.bankid=?";
		List<CsBank> list = getSession().createQuery(hql).setParameter(0, Long.valueOf(bankId)).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}