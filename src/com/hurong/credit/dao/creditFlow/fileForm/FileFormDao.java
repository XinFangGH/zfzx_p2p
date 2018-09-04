package com.hurong.credit.dao.creditFlow.fileForm;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;

import java.util.List;

public interface FileFormDao extends BaseDao<FileForm>{

    /**
     * 新智能根据企业id获取上传的图片
     * @param id
     * @return
     */
    public List<FileForm> getAllEnterpriseImg(Integer id);
}
