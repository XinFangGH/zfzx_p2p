package com.hurong.credit.service.creditFlow.materials;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.materials.OurProcreditMaterialsEnterprise;

/**
 * 
 * @author 
 *
 */
public interface OurProcreditMaterialsEnterpriseService extends BaseService<OurProcreditMaterialsEnterprise>{
	 
	  List<OurProcreditMaterialsEnterprise> getListByParentId(Integer parentId);
	  public  List<OurProcreditMaterialsEnterprise> getListByParentIdAndType(Integer parentId,String operationTypeKey);
	  public void deleteByProductId(String id);
	  public List<OurProcreditMaterialsEnterprise> getByProjectId(Long projectId);
	  public List<OurProcreditMaterialsEnterprise> getByProductId(Long productId);
	
}


