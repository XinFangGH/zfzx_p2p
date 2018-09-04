package com.hurong.credit.service.creditFlow.FileForm;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;

import java.util.List;

public interface FileFormService extends BaseService<FileForm>{

    /**
     * 新增根据企业id获取上传的图片
     * @param id    企业id
     * @return
     */
    List<FileForm> getAllEnterpriseImg(Integer id);
}
