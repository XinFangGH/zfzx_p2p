package com.hurong.credit.dao.article.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.article.P2pBannerlinkDao;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.credit.model.article.P2pBannerlink;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class P2pBannerlinkDaoImpl extends BaseDaoImpl<P2pBannerlink> implements P2pBannerlinkDao{

	public P2pBannerlinkDaoImpl() {
		super(P2pBannerlink.class);
	}

	@Override
	public List<P2pBannerlink> getBanner() {
		String hql = "from P2pBannerlink where isShow = 1";
		return this.getSession().createQuery(hql).list();
	}

}