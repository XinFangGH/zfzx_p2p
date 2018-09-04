package com.hurong.credit.service.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.user.CsDicAreaDynam;

/**
 * 
 * @author 
 *
 */
public interface CsDicAreaDynamService extends BaseService<CsDicAreaDynam>{
	public Map<Long,String> getAreaList(Integer parentId);
	public List<CsDicAreaDynam> listByParentId(Integer parentId);
	public String queryDicArea(Long id);
}


