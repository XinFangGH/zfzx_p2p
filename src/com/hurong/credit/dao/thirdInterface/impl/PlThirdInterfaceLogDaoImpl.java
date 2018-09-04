package com.hurong.credit.dao.thirdInterface.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.thirdInterface.PlThirdInterfaceLogDao;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlThirdInterfaceLogDaoImpl extends BaseDaoImpl<PlThirdInterfaceLog> implements PlThirdInterfaceLogDao{

	public PlThirdInterfaceLogDaoImpl() {
		super(PlThirdInterfaceLog.class);
	}

	@Override
	public PlThirdInterfaceLog getByOrderNum(String orderNum) {
		String hql="from PlThirdInterfaceLog as l where l.remark1=?";
		Object[] o={orderNum};
		return (PlThirdInterfaceLog)findUnique(hql, o);
	}

}