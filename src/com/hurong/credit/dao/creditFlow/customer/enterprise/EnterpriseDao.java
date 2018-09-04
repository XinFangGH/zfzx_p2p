package com.hurong.credit.dao.creditFlow.customer.enterprise;

		import com.hurong.core.dao.BaseDao;
		import com.hurong.core.web.paging.PagingBean;
		import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;

		import java.util.List;


public interface EnterpriseDao extends BaseDao<Enterprise>{
	public void ajaxQueryEnterprise(String searchCompanyId,String []userIds,String enterprisename ,String ownership,String shortname,String organizecode,String cciaa,int start ,int limit,String sort,String dir,String customerLevel);
	public Enterprise queryEnterpriseName(String name)throws Exception;
	public Enterprise isEmpty(String organizecode)throws Exception;
	public Enterprise getById(int id);
	public List<Enterprise> getListByLegalPersonId(int legalpersonid);
	public void getList(String customerType,String customerName, String cardNum,String companyId,int start,int limit);
	public List<Enterprise> ajaxQueryEnterpriseForCombo(String query,int start ,int limit);
	void getList(String enterIds,PagingBean pb,String type);
	public List<Enterprise> getDetail(Long id);
}