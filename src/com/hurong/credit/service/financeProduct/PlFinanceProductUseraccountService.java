package com.hurong.credit.service.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;

/**
 * 
 * @author 
 *
 */
public interface PlFinanceProductUseraccountService extends BaseService<PlFinanceProductUseraccount>{

	public List<PlFinanceProductUseraccount> getUserAccountList(
			HttpServletRequest request, PagingBean pb);

	public PlFinanceProductUseraccount getPersonAccount(Long id);
	//个人中心活期理财页面
	public String queryAllList(Long userId);
	
}


