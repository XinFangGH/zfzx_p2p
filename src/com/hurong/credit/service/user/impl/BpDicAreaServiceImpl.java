package com.hurong.credit.service.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.user.BpDicAreaDao;
import com.hurong.credit.model.user.BpDicArea;
import com.hurong.credit.service.user.BpDicAreaService;

/**
 * 
 * @author 
 *
 */
public class BpDicAreaServiceImpl extends BaseServiceImpl<BpDicArea> implements BpDicAreaService{
	@SuppressWarnings("unused")
	private BpDicAreaDao dao;
	
	public BpDicAreaServiceImpl(BpDicAreaDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<BpDicArea> getAreaList(String ParentCode) {
		
		List<BpDicArea> list = dao.getAreaList(ParentCode);
		
		return list;
	}
}