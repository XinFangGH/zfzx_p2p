package com.hurong.core.command;
/*
 *  北京金智万维软件有限公司 OA办公自动管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import org.hibernate.Criteria;


/**
 * Creates a command to wrap the Hibernate criteria API.
 * @author csx
 * 
 */
public interface CriteriaCommand {
	public final static String SORT_DESC="desc";
	public final static String SORT_ASC="asc";
	
	public Criteria execute(Criteria criteria);
}
