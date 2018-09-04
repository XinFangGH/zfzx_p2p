package com.hurong.credit.dao.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.user.CsDicAreaDynam;

/**
 * 
 * @author 
 *
 */
public interface CsDicAreaDynamDao extends BaseDao<CsDicAreaDynam>{
	public List<CsDicAreaDynam> getAreaList(Integer parentId);
	public List<CsDicAreaDynam> listByParentId(Integer parentId);
	public String queryDicArea(Long id);
}