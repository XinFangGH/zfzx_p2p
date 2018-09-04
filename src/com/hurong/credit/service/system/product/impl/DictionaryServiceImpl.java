package com.hurong.credit.service.system.product.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/


import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.system.product.DictionaryDao;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.service.system.product.DictionaryService;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary> implements DictionaryService{
	private DictionaryDao dao;
	
	
	public DictionaryServiceImpl(DictionaryDao dao) {
		super(dao);
		this.dao=dao;
	}


	@Override
	public List<Dictionary> getByProTypeId(long proTypeId) {
		return dao.getByProTypeId(proTypeId);
	}


	@Override
	public String getQueryDicId(long dicId) {
		return dao.getQueryDicId(dicId);
	}


	@Override
	public List<Dictionary> getByProTypeId2(long proTypeId,String status) {
		return dao.getByProTypeId2(proTypeId,status);
	}

}