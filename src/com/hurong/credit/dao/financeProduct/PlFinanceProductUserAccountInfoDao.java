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
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface PlFinanceProductUserAccountInfoDao extends BaseDao<PlFinanceProductUserAccountInfo>{

	public List<PlFinanceProductUserAccountInfo> getListByParamet(
			HttpServletRequest request, PagingBean pb);

	public void updateAccountInfo();


	public List<PlFinanceProductUserAccountInfo> getPersonList(HttpServletRequest request,Long id, int i,
			Integer pageSize);

	public Boolean saveInfo(PlFinanceProduct pl, BpCustMember mem,BigDecimal amount);
	
	public List<PlFinanceProductUserAccountInfo> getPersonList1(HttpServletRequest request,Long id);
}