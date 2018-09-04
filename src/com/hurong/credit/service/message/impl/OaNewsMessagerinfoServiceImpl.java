package com.hurong.credit.service.message.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.message.OaNewsMessagerinfoDao;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.service.message.OaNewsMessagerinfoService;



/**
 * 
 * @author 
 *
 */
public class OaNewsMessagerinfoServiceImpl extends BaseServiceImpl<OaNewsMessagerinfo> implements OaNewsMessagerinfoService{
	@SuppressWarnings("unused")
	private OaNewsMessagerinfoDao dao;
	
	public OaNewsMessagerinfoServiceImpl(OaNewsMessagerinfoDao dao) {
		super(dao);
		this.dao=dao;
	}


	@Override
	public List<OaNewsMessagerinfo> getAllInfo(PagingBean pb,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.getAllInfo(pb,request);
	}


	@Override
	public int getByStateCount(int userId, int status) {
		return dao.getByStateCount(userId,status);
	}

	@Override
	public List<OaNewsMessagerinfo> getInfo(HttpServletRequest request) {
		return dao.getInfo(request);
	}
}