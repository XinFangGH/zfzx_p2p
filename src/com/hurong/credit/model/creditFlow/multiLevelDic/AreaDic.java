package com.hurong.credit.model.creditFlow.multiLevelDic;
/**
 * @author tianhuiguo
 * @Description 地区数据字典
 * 
 * */

import java.io.Serializable;

import com.zhiwei.core.model.BaseModel;

public class AreaDic extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer parentId;
	private Integer number; 
	private String text;
	private String imgUrl;
	private Boolean leaf;
	private String lable;
	private Integer isOld;
	private String remarks;
	private Integer orderid;
	private String remarks1;
	
	public Integer getIsOld() {
		return isOld;
	}
	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}
	/*public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}*/
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
	
}
