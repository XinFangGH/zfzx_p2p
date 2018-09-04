package com.hurong.credit.dao.financingAgency.manageMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmObligatoryRightChildren;

/**
 * 
 * @author 
 *
 */
public interface PlMmObligatoryRightChildrenDao extends BaseDao<PlMmObligatoryRightChildren>{
	List<PlMmObligatoryRightChildren> listbysearch(PagingBean pb,Map<String, String> map);
	List<PlMmObligatoryRightChildren> listbydifferentBigOr(Map<String, String> map);
	
}