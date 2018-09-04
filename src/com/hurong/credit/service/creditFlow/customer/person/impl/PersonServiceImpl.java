package com.hurong.credit.service.creditFlow.customer.person.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.filter.MySessionFilter;
import com.hurong.credit.dao.creditFlow.customer.person.PersonDao;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.customer.person.PersonRelation;
import com.hurong.credit.service.creditFlow.customer.person.PersonService;

@SuppressWarnings({"unchecked","unused"})
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService{
	private PersonDao dao;
	/*@Resource
	private CreditBaseDao creditBaseDao;
	@Resource
	private OrganizationDao organizationDao;*/
	private final Log log = LogFactory.getLog(getClass());
	public PersonServiceImpl(PersonDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Person getById(int id) {
		
		return dao.getById(id);
	}

	@Override
	public void ajaxQueryPerson(String searchCompanyId, int start, int limit,
			String[] userIds, String hql, String[] str, Object[] obj,
			String sort, String dir, String customerLevel) throws Exception {
		dao.ajaxQueryPerson(searchCompanyId, start, limit, userIds, hql, str, obj, sort, dir, customerLevel);
	}

	@Override
	public void addPerson(Person person) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPersonFamily(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPersonImport(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRelationPerson(PersonRelation personRelation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(String[] strTable, String[] listId, String turl)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonRelationById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Person> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getListBypersonId(Integer start, Integer limit, int intValue,
			String flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person queryPersonCardnumber(String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void seePersonRelation(int parseInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRelationPerson(PersonRelation personRelation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person getJobIncome(String personId) {
		return dao.getJobIncome( personId);
	}

	@Override
	public Person queryPersonId(Integer personId) {
		// TODO Auto-generated method stub
		return dao.queryPersonId(personId);
	}
	
}