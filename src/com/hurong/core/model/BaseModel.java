package com.hurong.core.model;
/*
 *  北京金智万维软件有限公司 OA办公自动管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import java.io.Serializable;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import flexjson.JSON;
/**
 * Base model
 * @author 
 *
 */
public class BaseModel implements Serializable{
	protected Log logger=LogFactory.getLog(BaseModel.class);
	protected Integer version=0;
	@JSON(include=false)
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	protected Long companyId;
	/**
	 * 所在主部门Id
	 */
	protected Long orgId;
	/**
	 * 所在主部门的路径
	 */
	protected String orgPath;
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
