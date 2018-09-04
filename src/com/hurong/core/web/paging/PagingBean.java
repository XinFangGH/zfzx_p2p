package com.hurong.core.web.paging;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/

/**
 * 分页类
 */
public class PagingBean{
	
	public static final String PAGING_BEAN="_paging_bean";
	
	public static Integer DEFAULT_PAGE_SIZE=10;
	
	/**
	 * 最多显示页码数
	 */
	public static final int SHOW_PAGES=6;
	
	/**
	 * 每页开始的索引号
	 */
	public Integer start;
	//页码大小
	private Integer pageSize;

	//总记录数
	private Integer totalItems;
	
	public PagingBean(int start,int limit){
		this.pageSize=limit;
		this.start=start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;		
				
	}
	
	public int getFirstResult(){
		return start;
	}

}
