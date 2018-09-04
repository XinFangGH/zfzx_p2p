package com.hurong.credit.dao.creditFlow.customer.person.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.credit.dao.creditFlow.customer.person.PersonDao;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.customer.person.PersonRelation;




@SuppressWarnings("unchecked")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao{

	public PersonDaoImpl() {
		super(Person.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajaxQueryPerson(String searchCompanyId, int start, int limit,
			String[] userIds, String hql, String[] str, Object[] obj,
			String sort, String dir, String customerLevel) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAge(Date birthDay) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Person> getAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonRelation getByRelationShip(int parseInt, int ship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonRelation> getPersonByPersonId(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person queryPersonCardnumber(String cardNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getJobIncome(String personId) {
		String hql = " from Person as person where person.id= "+personId;
		return (Person)this.getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Person queryPersonId(Integer personId) {
		String hql = "from Person as person where person.id=? ";
		return (Person)this.getSession().createQuery(hql).setParameter(0, personId).uniqueResult();
	}
	
}