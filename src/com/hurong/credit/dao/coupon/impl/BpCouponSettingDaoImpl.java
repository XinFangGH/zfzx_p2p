package com.hurong.credit.dao.coupon.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.coupon.BpCouponSettingDao;
import com.hurong.credit.model.coupon.BpCouponSetting;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCouponSettingDaoImpl extends BaseDaoImpl<BpCouponSetting> implements BpCouponSettingDao{

	public BpCouponSettingDaoImpl() {
		super(BpCouponSetting.class);
	}

}