package com.hurong.credit.service.creditFlow.customer.person;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.customer.person.PersonRelation;

public interface PersonRelationService extends BaseService<PersonRelation>{
	public List<PersonRelation> getListByPersonId(Integer personId,PagingBean pb);
	public PersonRelation getById(Integer id);
	public List<PersonRelation> getByIdAndType(int personId, String personType);
}