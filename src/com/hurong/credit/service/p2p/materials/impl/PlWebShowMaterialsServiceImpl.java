package com.hurong.credit.service.p2p.materials.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.materials.PlWebShowMaterialsDao;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;

/**
 * 
 * @author 
 *
 */
public class PlWebShowMaterialsServiceImpl extends BaseServiceImpl<PlWebShowMaterials> implements PlWebShowMaterialsService{
	@SuppressWarnings("unused")
	private PlWebShowMaterialsDao dao;
	
	public PlWebShowMaterialsServiceImpl(PlWebShowMaterialsDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlWebShowMaterials> getByPidAndType(String pid,
			String businessType) {
		return dao.getByPidAndType(pid,businessType);
	}

	@Override
	public List<FileForm> getImgUrl(String mark) {
		return dao.getImgUrl(mark);
	}

	@Override
	public String getMaterialImgUrl(String string) {
		// TODO Auto-generated method stub
		return dao.getMaterialImgUrl(string);
	}

}