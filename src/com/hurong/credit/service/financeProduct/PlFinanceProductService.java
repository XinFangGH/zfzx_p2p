package com.hurong.credit.service.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface PlFinanceProductService extends BaseService<PlFinanceProduct>{

	public List<PlFinanceProduct> getAllProduct();

	public Object[] checkCondition(BigDecimal amount, Long productId,
			BpCustMember mem);

	public Object[] checkFromCondition(BigDecimal amount, Long productId,
			BpCustMember mem);
	
}


