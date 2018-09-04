package com.hurong.credit.service.coupon.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;

import javax.annotation.Resource;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.ContextUtil;
import com.hurong.credit.dao.coupon.BpCouponSettingDao;
import com.hurong.credit.model.coupon.BpCouponSetting;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.service.coupon.BpCouponSettingService;
import com.hurong.credit.service.coupon.BpCouponsService;

/**
 * 
 * @author 
 *
 */
public class BpCouponSettingServiceImpl extends BaseServiceImpl<BpCouponSetting> implements BpCouponSettingService{
	@SuppressWarnings("unused")
	private BpCouponSettingDao dao;
	@Resource
	private BpCouponsService bpCouponsService;
	
	public BpCouponSettingServiceImpl(BpCouponSettingDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String check(String checkType,Long categoryId) {
		try {
			String msg="";
			if(categoryId!=null){
				//审核类型
				if(checkType!=null&&!"".equals(checkType)){
					BpCouponSetting bps=dao.get(categoryId);
					if(bps!=null){
						//bps.setCouponCheckUserId(ContextUtil.getCurrentUserId());
						bps.setCouponCheckDate(new Date());
						//bps.setCouponCheckUserName(ContextUtil.getCurrentUser().getFullname());
						if(checkType.equals("pass")){
							Boolean flag=bpCouponsService.createBpCoupons(bps,BpCoupons.COUPONRESOURCE_NORMAL);
							if(flag){
								bps.setCouponState(Short.valueOf("10"));
								msg="操作成功,审核通过并成功生成了所有的优惠券";
							}else{
								bps.setCouponCheckUserId(null);
								bps.setCouponCheckDate(null);
								bps.setCouponCheckUserName(null);
								msg="操作失败,没有生成所有的优惠券";
							}
						}else if(checkType.equals("refuse")){
							bps.setCouponState(Short.valueOf("5"));
							msg="操作成功";
						}
						dao.save(bps);
					}else{
						msg="操作失败,数据库没有找到对应审核记录";
					}
				}else{
					msg="操作失败,没有审核类型";
				}
			}else{
				msg="操作失败,没有审核记录";
			}
			return msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "系统出错了，请联系管理员";
		}
	}

}