package com.hurong.credit.service.system.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.system.IndexShowDao;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.service.system.IndexShowService;

/**
 * 
 * @author 
 *
 */
public class IndexShowServiceImpl extends BaseServiceImpl<IndexShow> implements IndexShowService{
	@SuppressWarnings("unused")
	private IndexShowDao dao;
	
	public IndexShowServiceImpl(IndexShowDao dao) {
		super(dao);
		this.dao=dao;
	}

}