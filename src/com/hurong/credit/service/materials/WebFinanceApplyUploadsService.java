package com.hurong.credit.service.materials;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;

public interface WebFinanceApplyUploadsService extends BaseService<WebFinanceApplyUploads> {

	public boolean update(WebFinanceApplyUploads webFinanceApplyUploads);
	public WebFinanceApplyUploads getUserIdBystate(Long userid,String martType);
	public List<WebFinanceApplyUploads> getUploadState(Long userId);
}
