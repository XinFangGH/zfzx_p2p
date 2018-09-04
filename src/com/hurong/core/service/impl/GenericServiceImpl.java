package com.hurong.core.service.impl;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/
import java.io.Serializable;
import java.util.List;

//import javax.jws.WebMethod;
//import javax.jws.WebService;

import com.hurong.credit.model.user.BpCustMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.dao.GenericDao;
import com.hurong.core.service.GenericService;
import com.hurong.core.web.paging.PagingBean;
//@WebService
public class GenericServiceImpl<T,PK extends Serializable> implements GenericService<T, PK> {
	
	protected Log logger=LogFactory.getLog(GenericServiceImpl.class);
	
	protected GenericDao<T, Serializable> dao=null;

	public void setDao(GenericDao dao) {
		this.dao = dao;
	}
	
	public GenericServiceImpl(GenericDao dao) {
		this.dao=dao;
	}

	public T get(PK id) {
		return (T)dao.get(id);
	}

	public T save(T entity) {
		return (T)dao.save(entity);
	}
	
	public T merge(T entity){
		return (T)dao.merge(entity);
	}
	public T update1(T entity){
		return (T)dao.update1(entity);
	}
	
	public void evict(T entity){
		dao.evict(entity);
	}
	//@WebMethod(operationName="getAll")
	public List<T> getAll(){
		return dao.getAll();
	}
	//@WebMethod(operationName="getAllByPb")
	public List<T> getAll(PagingBean pb){
		return dao.getAll(pb);
	}
	//@WebMethod(operationName="getAllByFilter")
	public List<T> getAll(QueryFilter filter){
		return dao.getAll(filter);
	}
	//@WebMethod(operationName="remove")
	public void remove(PK id){
		dao.remove(id);
	}
	//@WebMethod(operationName="removeByEntity")
	public void remove(T entity){
		dao.remove(entity);
	}
	
	@Override
	public void flush() {
		dao.flush();
	}


}
