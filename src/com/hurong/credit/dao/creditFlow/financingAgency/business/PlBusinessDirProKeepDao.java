package com.hurong.credit.dao.creditFlow.financingAgency.business;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;

import java.util.List;

/**
 * 
 * @author 
 *
 */
public interface PlBusinessDirProKeepDao extends BaseDao<PlBusinessDirProKeep>{

	PlBusinessDirProKeep getDirProKeepByDirIdType(Long dirId, String type);
	
	public String getImgUrl(String string);

    PlBusinessDirProKeep getDirProKeepByDirIdType1(Long bdirProId, String proType);

    public List<PlBusinessDirProKeep> getDetail(Long keepId);

}