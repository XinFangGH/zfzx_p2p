package com.hurong.credit.service.creditFlow.customer.person;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.customer.person.Person;
import com.hurong.credit.model.creditFlow.customer.person.PersonRelation;

public interface PersonService extends BaseService<Person>{
	public Person getById(int id);
	public void ajaxQueryPerson(String searchCompanyId,int start, int limit ,String [] userIds, String hql ,String[] str,
			Object[] obj,String sort,String dir,String customerLevel) throws Exception;
	public void addPerson(Person person)throws Exception;
	public Person queryPersonCardnumber(String cardNumber)throws Exception;
//	public VPersonDic queryPerson(int id);
	public void updatePerson(Person person);
	public void deletePerson(String[] strTable ,String[] listId,String turl) throws Exception;
	public List<Person> getAllList();
//	public List<VPersonDic> ajaxQueryPersonForCombo(String query,int start ,int limit,String []userIds);
	public void addPersonFamily(Person person);
//	public VPersonDic queryPerson(Integer id);
	public  void addPersonImport(Person person);
	public void getListBypersonId(Integer start, Integer limit, int intValue,String flag);
	public void addRelationPerson(PersonRelation personRelation);
	public void deletePersonRelationById(int id);
	public void seePersonRelation(int parseInt);
	public void updateRelationPerson(PersonRelation personRelation);
	
	public Person getJobIncome(String personId);
	
	public Person queryPersonId(Integer personId);
}