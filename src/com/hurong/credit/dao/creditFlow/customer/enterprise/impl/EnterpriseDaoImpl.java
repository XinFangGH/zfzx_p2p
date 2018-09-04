package com.hurong.credit.dao.creditFlow.customer.enterprise.impl;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.customer.enterprise.EnterpriseDao;
import com.hurong.credit.model.creditFlow.customer.enterprise.Enterprise;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import org.apache.xml.security.encryption.Transforms;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import javax.xml.crypto.dsig.Transform;
import java.util.List;


@SuppressWarnings("unchecked")
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise> implements EnterpriseDao{
	public EnterpriseDaoImpl() {
		super(Enterprise.class);
	}
	@Override
	public void ajaxQueryEnterprise(String searchCompanyId,String []userIds,String enterprisename ,String ownership,String shortname,String organizecode,String cciaa,int start ,int limit,String sort,String dir,String customerLevel){}
	public Enterprise queryEnterpriseName(String name)throws Exception{/*
		String hql = "from Enterprise AS e where e.enterprisename=? " ;
		List list = creditBaseDao.queryHql(hql, name) ;
		if(list == null ){
			return null;
		}else
		    return (Enterprise)list.get(0);
	*/
		return null;};
	public Enterprise isEmpty(String organizecode)throws Exception {/*
		String hql = "from Enterprise AS e where e.organizecode=? " ;
		List list = creditBaseDao.queryHql(hql, organizecode) ;
		if(null == list){
			return null ;
		}else 
			return (Enterprise) list.get(0) ;
	*/
		return null;}
	@Override
	public Enterprise getById(int id) {
		String hql="from Enterprise as e where e.id=?";
		return (Enterprise)getSession().createQuery(hql).setParameter(0, id).uniqueResult();
	}


	@Override
	public List<Enterprise> getListByLegalPersonId(int legalpersonid) {
		String hql= "from Enterprise as e where e.legalpersonid=?";
		return getSession().createQuery(hql).setParameter(0, legalpersonid).list();
	}
	@Override
	public void getList(String customerType,
			String customerName, String cardNum,String companyId,int start,int limit) {/*
		
		String hql="from VEnterprisePerson v where v.name like ? and v.code like ?";
		if(null!=companyId && !"".equals(companyId)){
        	hql = hql+" and v.companyId in ("+companyId+") ";
        }
		String hql1="select count(*) from VEnterprisePerson v where v.name like ? and v.code like ?";
		if(null!=companyId && !"".equals(companyId)){
        	hql1 = hql1+" and v.companyId in ("+companyId+") ";
        }
		if(null!=customerType && !"".equals(customerType)){
			hql=hql+" and v.type=('"+customerType+"')";
			hql1=hql1+" and v.type=('"+customerType+"')";
		}
		try {
			//List<VEnterprisePerson> list=creditBaseDao.queryHql("from VEnterprisePerson v where v.name like '%"+customerName+"%' and v.code like '%"+customerName+"%' and v.companyId in ('"+companyId+"') " );
			List<VEnterprisePerson> list=creditBaseDao.queryHql(hql, new Object[]{'%'+customerName+'%','%'+cardNum+'%'}, start, limit);
			List list1=creditBaseDao.queryHql(hql1, new Object[]{'%'+customerName+'%','%'+cardNum+'%'});
			int  totalCount=0;
			if(null!=list1 && list1.size()>0){
				totalCount=((Long)list1.get(0)).intValue();
			}
			JsonUtil.jsonFromList(list, totalCount) ;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	*/}
	@Override
	public List<Enterprise> ajaxQueryEnterpriseForCombo(String query, int start, int limit) {
		if(query==null){
			query="" ;
		}else{
			query.replaceAll(" ", "") ;
		}
		String hql = "from Enterprise as e where e.enterprisename like '%" + query + "%'" ;
		return this.findByHql(hql, new Object[]{}, start, limit);
	}
	
	@Override
	public void getList(String enterIds, PagingBean pb, String type) {/*
		String hql ="";// "from Enterprise e where e.id in ("+enterIds+")";
		String hql1 = "";
		if(type.equals("1")){//获取关联企业
			hql += "from EnterpriseView e where e.id in ("+enterIds+")";
			hql1 += "select count(*) from EnterpriseView e where e.id in ("+enterIds+")";
		}else if(type.equals("0")){//获取不关联企业
			hql+= "from EnterpriseView e where e.id not in ("+enterIds+")";
			hql1+= "select count(*) from EnterpriseView e where e.id not in ("+enterIds+")";;
		}
		List list = new ArrayList();
		int totalProperty = 0;
		List totalList = new ArrayList();
		try {
			list = creditBaseDao.queryHql(hql,new Object[]{}, pb.getStart(), pb.getPageSize()) ;
			totalList = creditBaseDao.queryHql(hql1,new Object[]{});
			totalProperty = ((Long)totalList.get(0)).intValue() ;//记录总数
			JsonUtil.jsonFromList(list, totalProperty) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/}

	@Override
	public List<Enterprise> getDetail(Long id) {
		StringBuffer sql = new StringBuffer("select e.enterprisename" +
				",e.id" +
				",e.ownership" +
				",e.organizecode" +
				",e.linkman" +
				",e.registermoney" +
				",e.opendate" +
				",e.managecity" +
				",e.hangyetype"+
				" from cs_enterprise e where e.id =" +
				id);
		System.out.println(sql);
		return getSession().createSQLQuery(sql.toString()).
				addScalar("enterprisename", Hibernate.STRING).
				addScalar("id", Hibernate.INTEGER).
				addScalar("ownership",Hibernate.STRING).
				addScalar("organizecode",Hibernate.STRING).
				addScalar("linkman",Hibernate.STRING).
				addScalar("registermoney",Hibernate.DOUBLE).
				addScalar("opendate",Hibernate.DATE).
				addScalar("managecity",Hibernate.STRING).
				addScalar("hangyeType",Hibernate.INTEGER).
				setResultTransformer(Transformers.aliasToBean(Enterprise.class)).list();



	}
}