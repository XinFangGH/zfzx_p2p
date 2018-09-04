package com.hurong.credit.service.materials.impl;

import java.util.List;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.materials.WebFinanceApplyUploadsDao;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;

public class WebFinanceApplyUploadsServiceImpl  extends BaseServiceImpl<WebFinanceApplyUploads> implements WebFinanceApplyUploadsService {

	
	@SuppressWarnings("unused")
	private WebFinanceApplyUploadsDao dao ;
	
	public WebFinanceApplyUploadsServiceImpl(WebFinanceApplyUploadsDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public boolean update(WebFinanceApplyUploads webFinanceApplyUploads) {
		// TODO Auto-generated method stub
		return dao.update(webFinanceApplyUploads);
	}

	@Override
	public WebFinanceApplyUploads getUserIdBystate(Long userid,String martType) {
		return dao.getUserIdBystate(userid,martType);
	}

	@Override
	public List<WebFinanceApplyUploads> getUploadState(Long userId) {
		// TODO Auto-generated method stub
		return dao.getUploadState(userId);
	}

	
}
