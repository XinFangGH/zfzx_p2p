package com.hurong.credit.service.p2p.materials;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;

/**
 * 
 * @author 
 *
 */
public interface PlWebShowMaterialsService extends BaseService<PlWebShowMaterials>{

	List<PlWebShowMaterials> getByPidAndType(String pid, String businessType);

	List<FileForm> getImgUrl(String mark);
	
	String getMaterialImgUrl(String string);
	
}


