package com.hurong.credit.service.creditFlow.assuretenet;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.assuretenet.OurProcreditAssuretenet;

/**
 * 
 * @author 
 *
 */
public interface OurProcreditAssuretenetService extends BaseService<OurProcreditAssuretenet>{
	   
	    /**
	     * 根据业务种类获取获取担保材料列表
	     * @param businessTypeKey
	     * @return
	     */
	   public List<OurProcreditAssuretenet> getListByBussinessType(String businessTypeKey);

	   public void deleteByProductId(String id);

	   public List<OurProcreditAssuretenet> getByProjectId(Long projectId);

	   public List<OurProcreditAssuretenet> getByProductId(Long productId);

}


