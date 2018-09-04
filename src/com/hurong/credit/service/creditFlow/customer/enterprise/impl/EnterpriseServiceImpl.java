package com.hurong.credit.service.creditFlow.customer.enterprise.impl;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.customer.enterprise.EnterpriseDao;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.service.creditFlow.customer.enterprise.EnterpriseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise> implements EnterpriseService{
	private EnterpriseDao dao;
	private final Log log = LogFactory.getLog(getClass());
	public EnterpriseServiceImpl(EnterpriseDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void ajaxQueryEnterprise(String searchCompanyId, String[] userIds,
			String enterprisename, String ownership, String shortname,
			String organizecode, String cciaa, int start, int limit,
			String sort, String dir, String customerLevel) {
		dao.ajaxQueryEnterprise(searchCompanyId, userIds, enterprisename, ownership, shortname, organizecode, cciaa, start, limit, sort, dir, customerLevel);
		
	}

	
	public Enterprise queryEnterpriseName(String name)throws Exception{
		return dao.queryEnterpriseName(name);
	}

	@Override
	public Enterprise isEmpty(String organizecode) throws Exception {
		
		return dao.isEmpty(organizecode);
	}

	@Override
	public Enterprise getById(int id) {
		
		return dao.getById(id);
	}

	@Override
	public List<Enterprise> getListByLegalPersonId(int legalpersonid) {
		return dao.getListByLegalPersonId(legalpersonid);
	};
	
	@Override
	public void getList(String customerType, String customerName,
			String cardNum, String companyId, int start, int limit) {
		dao.getList(customerType, customerName, cardNum, companyId, start, limit);
		
	}

	

	@Override
	public void getList(String enterIds, PagingBean pb,String type) {
		String hql ="";// "from Enterprise e where e.id in ("+enterIds+")";
		if(type.equals("1")){//获取关联企业
			hql += "from EnterpriseView e where e.id in ("+enterIds+")";
		}else if(type.equals("0")){//获取不关联企业
			hql+= "from EnterpriseView e where e.id not in ("+enterIds+")";;
		}
		dao.getList(enterIds, pb, type);
		//return null;//dao.findByHql(hql, new Object[]{}, pb);
	}

	@Override
	public List<Enterprise> getDetail(Long id) {
		return dao.getDetail(id);
	}

	@Override
	public void ajaxDeleteEnterpriseWithId(String[] strTable, String[] listId,
			String turl) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajaxQueryEnterpriseForCombo(String query, int start, int limit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getEnterByProject(int parseLong) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSlCompanyInfo(int id) {
		// TODO Auto-generated method stub
		
	}
}