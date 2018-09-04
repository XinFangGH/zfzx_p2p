package com.hurong.credit.dao.pay.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.pay.ThirdPayMessageDao;
import com.hurong.credit.model.pay.ThirdPayMessage;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class ThirdPayMessageDaoImpl extends BaseDaoImpl<ThirdPayMessage> implements ThirdPayMessageDao{

	public ThirdPayMessageDaoImpl() {
		super(ThirdPayMessage.class);
	}

}