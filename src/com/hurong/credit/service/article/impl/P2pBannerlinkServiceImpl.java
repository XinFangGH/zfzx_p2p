package com.hurong.credit.service.article.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.article.P2pBannerlinkDao;
import com.hurong.credit.model.article.P2pBannerlink;
import com.hurong.credit.service.article.P2pBannerlinkService;

/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/



/**
 * 
 * @author 
 *
 */
public class P2pBannerlinkServiceImpl extends BaseServiceImpl<P2pBannerlink> implements P2pBannerlinkService{
	@SuppressWarnings("unused")
	private P2pBannerlinkDao dao;
	
	public P2pBannerlinkServiceImpl(P2pBannerlinkDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<P2pBannerlink> getBanner() {
		return dao.getBanner();
	}

	@Override
	public List<P2pBannerlink> getBannerList(String WebType) {
		// TODO Auto-generated method stub
		String key="2";
		if(WebType!=null&&WebType.equals("P2P")){
			key="1";
		}
		String hql="from P2pBannerlink as p where p.isShow=1 and p.webKey='"+key+"'  ORDER BY p.orderList asc";
		return dao.findByHql(hql,null);
	}

}