package com.hurong.credit.service.creditFlow.FileForm.impl;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.fileForm.FileFormDao;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.service.creditFlow.FileForm.FileFormService;

import java.util.List;

public class FileFormServiceImpl extends BaseServiceImpl<FileForm> implements FileFormService{
	@SuppressWarnings("unused")
	private FileFormDao dao;
	public FileFormServiceImpl(FileFormDao dao) {
		super(dao);
		this.dao=dao;
	}

	//新增根据企业id查找图片
	public List<FileForm> getAllEnterpriseImg(Integer id) {
		return dao.getAllEnterpriseImg(id);
	}

}
