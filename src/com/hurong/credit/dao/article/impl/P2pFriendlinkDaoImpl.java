package com.hurong.credit.dao.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.dao.article.P2pFriendlinkDao;
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
@SuppressWarnings("unchecked")
public class P2pFriendlinkDaoImpl extends BaseDaoImpl<P2pFriendlink> implements P2pFriendlinkDao{

	public P2pFriendlinkDaoImpl() {
		super(P2pFriendlink.class);
	}

	@Override
	public List<P2pFriendlink> getListByType(int type) {
   String hql = "from P2pFriendlink as fl where fl.linkType = ? ";
		
		Object[] params={Short.valueOf(String.valueOf(type))};
		return findByHql(hql,params);
	}

	@Override
	public List<P2pFriendlink> getByRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String hql = "from P2pFriendlink as fl where 1=1";
		if(request!=null){
			String type=request.getAttribute("type").toString();
			if(type!=null&&!"".equals(type)){
				hql=hql+" and fl.linkType="+Short.valueOf(type);
			}
		}
		return this.getSession().createQuery(hql).list();
	}

	
	/**
	 * 查询友情链接图片展示路径
	 * String :mark值（查询条件）
	 * svn：songwj
	 */
	public String getImgUrl(String string){
		List list=null;
		String ret="";
		Map<String, String> params=new HashMap<String, String>();
		params.put("mark", string);
		list=this.executeSqlFind("cs_file","webPath",params);
		if(list!=null&&list.size()>0){
			ret= list.get(0).toString();
		}else{
			ret= "0";
		}
		return ret;
	}
	/**
	 * 根据网站类别的值查询友情链接
	 */
	@Override
	public List<P2pFriendlink> getListByWebKey(String webKey) {
		// TODO Auto-generated method stub
		 String hql = "from P2pFriendlink as fl where fl.webKey = ?  and fl.isShow=1";
			
			Object[] params={webKey};
			return findByHql(hql,params);
	}
	
}