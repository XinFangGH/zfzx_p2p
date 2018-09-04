package com.hurong.credit.dao.materials;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;

public interface WebFinanceApplyUploadsDao extends BaseDao<WebFinanceApplyUploads> {

	public boolean update(WebFinanceApplyUploads webFinanceApplyUploads);
	public WebFinanceApplyUploads getUserIdBystate(Long userId,String martType);
	public List<WebFinanceApplyUploads> getUploadState(Long userId);
}
