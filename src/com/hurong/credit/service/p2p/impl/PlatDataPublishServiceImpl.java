package com.hurong.credit.service.p2p.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.PlatDataPublishDao;
import com.hurong.credit.model.p2p.PlatDataPublish;
import com.hurong.credit.service.p2p.PlatDataPublishService;

/**
 * 
 * @author 
 *
 */
public class PlatDataPublishServiceImpl extends BaseServiceImpl<PlatDataPublish> implements PlatDataPublishService{
	@SuppressWarnings("unused")
	private PlatDataPublishDao dao;
	
	public PlatDataPublishServiceImpl(PlatDataPublishDao dao) {
		super(dao);
		this.dao=dao;
	}

}