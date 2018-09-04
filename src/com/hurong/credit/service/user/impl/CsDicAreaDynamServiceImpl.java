package com.hurong.credit.service.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.user.CsDicAreaDynamDao;
import com.hurong.credit.model.user.CsDicAreaDynam;
import com.hurong.credit.service.user.CsDicAreaDynamService;

/**
 * 
 * @author 
 *
 */
public class CsDicAreaDynamServiceImpl extends BaseServiceImpl<CsDicAreaDynam> implements CsDicAreaDynamService{
	@SuppressWarnings("unused")
	private CsDicAreaDynamDao dao;
	
	public CsDicAreaDynamServiceImpl(CsDicAreaDynamDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Map<Long, String> getAreaList(Integer parentId) {
		// TODO Auto-generated method stub
		Map<Long, String> map = new HashMap<Long, String>();
		List<CsDicAreaDynam> list = dao.getAreaList(parentId);
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CsDicAreaDynam csDicAreaDynam = list.get(i);
				map.put(csDicAreaDynam.getId(), csDicAreaDynam.getTitle());
			}
		}
		
		return map;
	}

	@Override
	public List<CsDicAreaDynam> listByParentId(Integer parentId) {
		return dao.listByParentId(parentId);
	}

	@Override
	public String queryDicArea(Long id) {
		return dao.queryDicArea(id);
	}

}