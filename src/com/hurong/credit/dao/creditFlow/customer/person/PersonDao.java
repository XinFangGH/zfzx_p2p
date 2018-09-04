package com.hurong.credit.dao.creditFlow.customer.person;

import java.util.Date;
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.customer.person.PersonRelation;



public interface PersonDao extends BaseDao<Person>{
	public Person getById(int id);
	public void ajaxQueryPerson(String searchCompanyId,int start, int limit ,String [] userIds, String hql ,String[] str,
			Object[] obj,String sort,String dir,String customerLevel) throws Exception;
	public int getAge(Date birthDay) throws Exception;
	public Person queryPersonCardnumber(String cardNumber)throws Exception;
//	public VPersonDic queryPerson(int id);
	public List<Person> getAllList();
//	public List<VPersonDic> ajaxQueryPersonForCombo(String query,int start ,int limit,String []userIds);
//	public VPersonDic queryPerson(Integer id);
	public Person getPersonById(Integer id);
	public List<PersonRelation> getPersonByPersonId(int parseInt);
	public PersonRelation getByRelationShip(int parseInt, int ship);
	public Person getJobIncome(String personId);
	public Person queryPersonId(Integer personId);
}