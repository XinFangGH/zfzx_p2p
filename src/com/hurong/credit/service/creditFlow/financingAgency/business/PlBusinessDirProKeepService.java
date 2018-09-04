package com.hurong.credit.service.creditFlow.financingAgency.business;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;

import java.util.List;

/**
 * 
 * @author 
 *
 */
public interface PlBusinessDirProKeepService extends BaseService<PlBusinessDirProKeep>{
	public PlBusinessDirProKeep getDirProKeepByDirIdType(Long dirId,String type);
	
	/**
	 * 查询标的logo图片展示路径
	 * String :mark值（查询条件）
	 */
	public String getImgUrl(String string);

    PlBusinessDirProKeep getDirProKeepByDirIdType1(Long bdirProId, String proType);

	public List<PlBusinessDirProKeep> getDetail(Long keepId);

}


