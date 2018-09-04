package com.hurong.credit.service.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.user.BpCustLoginlogDao;
import com.hurong.credit.model.user.BpCustLoginlog;
import com.hurong.credit.service.user.BpCustLoginlogService;

/**
 * 
 * @author 
 *
 */
public class BpCustLoginlogServiceImpl extends BaseServiceImpl<BpCustLoginlog> implements BpCustLoginlogService{
	@SuppressWarnings("unused")
	private BpCustLoginlogDao dao;
	
	public BpCustLoginlogServiceImpl(BpCustLoginlogDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<BpCustLoginlog> listByMemberId(Long id){
		return dao.listByMemberId(id);
	}
}