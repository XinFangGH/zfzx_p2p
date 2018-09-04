package com.hurong.credit.dao.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.financingAgency.manageMoney.PlEarlyRedemptionDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlEarlyRedemptionDaoImpl extends BaseDaoImpl<PlEarlyRedemption> implements PlEarlyRedemptionDao{

	public PlEarlyRedemptionDaoImpl() {
		super(PlEarlyRedemption.class);
	}

	@Override
	public List<PlEarlyRedemption> listByOrderId(Long orderId) {
		String hql="from PlEarlyRedemption as e where e.orderId=?";
		return this.findByHql(hql, new Object[]{orderId});
	}

	@Override
	public PlEarlyRedemption getByRequestNo(String getByRequestNo) {
		// TODO Auto-generated method stub
		String hql="from PlEarlyRedemption as e where e.loanerRequestNo=?";
		return (PlEarlyRedemption) this.findUnique(hql, new Object[]{getByRequestNo});
		
	}

}