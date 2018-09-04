package com.hurong.credit.service.creditFlow.customer.enterprise;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;

import java.util.List;



public interface EnterpriseService extends BaseService<Enterprise>{
	public void ajaxQueryEnterprise(String searchCompanyId,String []userIds,String enterprisename ,String ownership,String shortname,String organizecode,String cciaa,int start ,int limit,String sort,String dir,String customerLevel);
	//public void ajaxAddEnterprise(Enterprise enterprise,Person person,List<EnterpriseShareequity> listES,String personSFZZId,String personSFZFId,Map<String,String> enterpriseMap)throws Exception;
	public Enterprise queryEnterpriseName(String name)throws Exception;
	public Enterprise isEmpty(String organizecode)throws Exception;
	public Enterprise getById(int id);
	public List<Enterprise> getListByLegalPersonId(int legalpersonid);
	public void ajaxDeleteEnterpriseWithId(String[] strTable ,String[] listId,String turl) throws Exception;
	public void getSlCompanyInfo(int id);
	public void getList(String customerType,String customerName, String cardNum,String companyId,int start,int limit);
	 public void ajaxQueryEnterpriseForCombo(String query,int start ,int limit);
	// public EnterpriseView getByViewId(Integer id);
	// public EnterpriseView setEnterpriseView(EnterpriseView enView) throws Exception;
	public void getEnterByProject(int parseLong);
	
	//List<EnterpriseView> getList(String enterIds,PagingBean pb,String type);
	void getList(String enterIds,PagingBean pb,String type);

	public List<Enterprise> getDetail(Long id);
}