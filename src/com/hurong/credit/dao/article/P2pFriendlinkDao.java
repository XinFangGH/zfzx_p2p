package com.hurong.credit.dao.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.article.P2pFriendlink;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/


/**
 * 
 * @author 
 *
 */
public interface P2pFriendlinkDao extends BaseDao<P2pFriendlink>{

	List<P2pFriendlink> getListByType(int type);

	public List<P2pFriendlink> getByRequest(HttpServletRequest request);
	
	/**
	 * 查询友情链接图片展示路径
	 * String :mark值（查询条件）
	 * svn：songwj
	 */
	public String getImgUrl(String string);
	/**
	 * 根据网站类别的值查询友情链接,1为P2P网站，2为云购，3为云众筹
	 */
	List<P2pFriendlink> getListByWebKey(String  webKey);
	
}