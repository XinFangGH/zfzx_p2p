package com.hurong.credit.dao.creditFlow.assuretenet;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.assuretenet.OurProcreditAssuretenet;

/**
 * 
 * @author 
 *
 */
public interface OurProcreditAssuretenetDao extends BaseDao<OurProcreditAssuretenet>
{
	public boolean initAssuretenet(String projId,Integer businessType);
	
	  /**
     * 根据业务种类获取获取担保材料列表
     * @param businessTypeKey
     * @return
     */
   public List<OurProcreditAssuretenet> getListByBussinessType(String businessTypeKey);
   
   /**
    * 根据节点key 初始化准入原则 新方法
    * @param projectId
    * @param businessTypeKey
    * @param operationTypeKey
    * @param customerType
    * @return
    */
   public boolean  initAssuretenet(String projectId,String businessTypeKey,String operationTypeKey,String customerType);

   public void deleteByProductId(String id);

   public List<OurProcreditAssuretenet> getByProductId(Long productId);

   public List<OurProcreditAssuretenet> getByProjectId(Long projectId);
   
}