package com.hurong.credit.dao.article;

import java.util.List;

import com.hurong.core.dao.BaseDao;
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
public interface P2pBannerlinkDao extends BaseDao<P2pBannerlink>{

	public List<P2pBannerlink> getBanner();
	
}