package com.hurong.credit.service.article;
import java.util.List;

import com.hurong.core.service.BaseService;
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
public interface P2pBannerlinkService extends BaseService<P2pBannerlink>{

	public List<P2pBannerlink> getBanner();
    /**
     * 获取数据库中banner的列表   带条件
     * @param string
     * @return
     */
	public List<P2pBannerlink> getBannerList(String WebType);
	
}


