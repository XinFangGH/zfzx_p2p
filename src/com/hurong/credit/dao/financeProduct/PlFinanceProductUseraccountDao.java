package com.hurong.credit.dao.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.p2p.BpPersonCenterData;



/**
 * 
 * @author 
 *
 */
public interface PlFinanceProductUseraccountDao extends BaseDao<PlFinanceProductUseraccount>{

	public List<PlFinanceProductUseraccount> getUserAccountList(HttpServletRequest request, PagingBean pb);

	public BpPersonCenterData getPersonAccount1(Long id);

	public PlFinanceProductUseraccount getProductUserAccount(Long productId,
			Long id);
	public PlFinanceProductUseraccount getPersonAccount(Long id);
}