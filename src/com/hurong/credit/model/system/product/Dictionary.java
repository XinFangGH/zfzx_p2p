package com.hurong.credit.model.system.product;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/


/**
 * 
 * @author 
 *
 */
/**
 * BpProductParameter Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
@SuppressWarnings("serial")
public class Dictionary extends com.hurong.core.model.BaseModel {

    protected Long dicId;
	protected Long proTypeId;
	protected String itemValue;
	protected String status;
	public Dictionary () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BpProductParameter
	 */
	public Dictionary (
		 Long dicId
        ) {
		this.setDicId(dicId);
    }


	

	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	public Long getProTypeId() {
		return proTypeId;
	}

	public void setProTypeId(Long proTypeId) {
		this.proTypeId = proTypeId;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
