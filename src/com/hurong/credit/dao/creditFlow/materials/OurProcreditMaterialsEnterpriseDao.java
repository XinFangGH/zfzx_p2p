package com.hurong.credit.dao.creditFlow.materials;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.materials.OurProcreditMaterialsEnterprise;

/**
 * 
 * @author 
 *
 */
public interface OurProcreditMaterialsEnterpriseDao extends BaseDao<OurProcreditMaterialsEnterprise>{
	
	  public  List<OurProcreditMaterialsEnterprise> getListByParentId(Integer parentId);
	  public  void  initMaterials(String projectId,Integer businessType);
	  public  void  initMaterials(String projectId,String businessTypeKey,String operationTypeKey);
	  public  List<OurProcreditMaterialsEnterprise> getListByParentIdAndType(Integer parentId,String operationTypeKey);
	  public void deleteByProductId(String id);
	  public List<OurProcreditMaterialsEnterprise> getByProductId(Long productId);
	  public List<OurProcreditMaterialsEnterprise> getByProjectId(Long projectId);
} 