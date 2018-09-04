package com.hurong.credit.service.system.product.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.BeanUtil;
import com.hurong.credit.dao.system.product.BpProductParameterDao;
import com.hurong.credit.model.system.product.BpProductParameter;
import com.hurong.credit.service.system.product.BpProductParameterService;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpProductParameterServiceImpl extends BaseServiceImpl<BpProductParameter> implements BpProductParameterService{
	private BpProductParameterDao dao;
	
	
	public BpProductParameterServiceImpl(BpProductParameterDao dao) {
		super(dao);
		this.dao=dao;
	}

}