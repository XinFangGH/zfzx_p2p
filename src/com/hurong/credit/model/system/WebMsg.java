package com.hurong.credit.model.system;
/*
 *  北京互融时代软件有限公司 OA办公管理系统   --  http://www.hurongtime.com
 *  Copyright (C) 2008-2011 zhiwei Software Company
*/

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 该类用来做前端提示信息使用
 * @author Yuan
 *
 */
public class WebMsg extends com.zhiwei.core.model.BaseModel {
	/**
	 * 用来区分 是 简单消息类 还是 复杂消息
	 * 简单销售：如修改密码 0
	 * 复杂消息：如 投标 1
	 * 
	 */
	private String type;
	/**
	 * 状态码 0000 失败 8888成功
	 */
	private  String code;
	/**
	 * 状态提示
	 */
	private String desc;
	/**
	 * 跳转的URL
	 */
	private String url;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRemark4() {
		return remark4;
	}
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	public String getRemark5() {
		return remark5;
	}
	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}
	
	
}
