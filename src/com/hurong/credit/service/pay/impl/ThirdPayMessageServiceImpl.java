package com.hurong.credit.service.pay.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.pay.ThirdPayMessageDao;
import com.hurong.credit.model.pay.ThirdPayMessage;
import com.hurong.credit.service.pay.ThirdPayMessageService;

/**
 * 
 * @author 
 *
 */
public class ThirdPayMessageServiceImpl extends BaseServiceImpl<ThirdPayMessage> implements ThirdPayMessageService{
	@SuppressWarnings("unused")
	private ThirdPayMessageDao dao;
	
	public ThirdPayMessageServiceImpl(ThirdPayMessageDao dao) {
		super(dao);
		this.dao=dao;
	}

}