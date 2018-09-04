package com.hurong.credit.service.article.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.article.P2pFriendlinkDao;
import com.hurong.credit.model.article.P2pFriendlink;
import com.hurong.credit.service.article.P2pFriendlinkService;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/


/**
 * 
 * @author 
 *
 */
public class P2pFriendlinkServiceImpl extends BaseServiceImpl<P2pFriendlink> implements P2pFriendlinkService{
	@SuppressWarnings("unused")
	private P2pFriendlinkDao dao;
	
	public P2pFriendlinkServiceImpl(P2pFriendlinkDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<P2pFriendlink> getListByType(int type) {
		return dao.getListByType(type);
	}

	@Override
	public List<P2pFriendlink> getByRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.getByRequest(request);
	}

	/**
	 * 查询友情链接图片展示路径
	 * String :mark值（查询条件）
	 * svn：songwj
	 */
	public String getImgUrl(String string){
		return dao.getImgUrl(string);
	}

	@Override
	public List<P2pFriendlink> getListByWebKey(String webKey) {
		// TODO Auto-generated method stub
		return dao.getListByWebKey(webKey);
	}

}