package com.hurong.credit.service.message;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.message.OaNewsMessagerinfo;


/**
 * 
 * @author 
 *
 */
public interface OaNewsMessagerinfoService extends BaseService<OaNewsMessagerinfo>{

	public List<OaNewsMessagerinfo> getAllInfo(PagingBean pb,
			HttpServletRequest request);
	/**
	 * 查询未读的站内信条数
	 * @param userId
	 * @param status
	 * @return
	 */
	public int getByStateCount(int userId,int status);

    List<OaNewsMessagerinfo> getInfo(HttpServletRequest request);
}


