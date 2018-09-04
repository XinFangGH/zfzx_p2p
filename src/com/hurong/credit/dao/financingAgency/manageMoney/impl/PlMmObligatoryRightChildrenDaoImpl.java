package com.hurong.credit.dao.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financingAgency.manageMoney.PlMmObligatoryRightChildrenDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmObligatoryRightChildren;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlMmObligatoryRightChildrenDaoImpl extends BaseDaoImpl<PlMmObligatoryRightChildren> implements PlMmObligatoryRightChildrenDao{

	public PlMmObligatoryRightChildrenDaoImpl() {
		super(PlMmObligatoryRightChildren.class);
	}
	@Override
	public List<PlMmObligatoryRightChildren> listbysearch(PagingBean pb,
			Map<String, String> map) {
		StringBuffer hql = new StringBuffer("from PlMmObligatoryRightChildren fmpb where fmpb.availableMoney>0 ");
		String seachDate=map.get("seachDate");
		String parentOrBidName=map.get("parentOrBidName");
		if(null !=seachDate&&!seachDate.equals("")){
			hql.append(" and fmpb.startDate <= '"+seachDate+"'");
			hql.append(" and fmpb.intentDate > '"+seachDate+"'");
			
		}
		if(parentOrBidName!=null&&!"".equals(parentOrBidName)){
			hql.append(" and fmpb.parentOrBidName like '%"+parentOrBidName+"%'");
		}
		hql.append(" order by fmpb.dayRate desc,fmpb.intentDate asc,fmpb.principalMoney desc");
		if(null==pb){
			return findByHql(hql.toString());
			
		}else{
			return findByHql(hql.toString(),null,pb);
		}
	
	}

	@Override
	public List<PlMmObligatoryRightChildren> listbydifferentBigOr(
			Map<String, String> map) {
		StringBuffer hql = new StringBuffer("from PlMmObligatoryRightChildren fmpb where fmpb.availableMoney>0 ");
		String seachDate=map.get("seachDate");
		if(null !=seachDate&&!seachDate.equals("")){
		hql.append(" and fmpb.startDate <= '"+seachDate+"'");
		hql.append(" and fmpb.intentDate > '"+seachDate+"'");
		}
		hql.append(" order by fmpb.principalMoney desc,fmpb.dayRate desc,fmpb.intentDate asc ");
			return findByHql(hql.toString());
			
	
	}



}