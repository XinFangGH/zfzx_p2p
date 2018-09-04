package com.hurong.credit.service.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface PlFinanceProductUserAccountInfoService extends BaseService<PlFinanceProductUserAccountInfo>{
	
	public List<PlFinanceProductUserAccountInfo> getListByParamet(
			HttpServletRequest request, PagingBean pb);
	
	public boolean  creatYestDayIntent(Date date);
    
	public List<PlFinanceProductUserAccountInfo> getPersonList(HttpServletRequest request,Long id, int i,
			Integer pageSize);

	public Object[] buyRecord(BigDecimal amount, Long productId, BpCustMember mem,Boolean isMobile);

	public Object[] transferFromPlate(BigDecimal amount, Long productId,
			BpCustMember mem);

	//个人中心活期理财页面
	public  List<PlFinanceProductUserAccountInfo> queryAllCurrentFinancialList(Long userId,HttpServletRequest request);
}


