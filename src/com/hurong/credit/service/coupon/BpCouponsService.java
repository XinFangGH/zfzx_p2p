package com.hurong.credit.service.coupon;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.coupon.BpCouponSetting;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface BpCouponsService extends BaseService<BpCoupons>{

	public Boolean createBpCoupons(BpCouponSetting bps, String couponresourceNormal);

	public List<BpCoupons> bouponBelongList(HttpServletRequest request,Integer start, Integer limit);

	public Boolean couponDistribute(Long id, String truename,Long opraterId,String oprateName,BpCoupons bps);

	public List<BpCoupons> listForNotDistributeCoupon(HttpServletRequest request, Integer start, Integer limit);
	
	public String createCouponNumber(String couponType);
	
	public BpCoupons saveBpCoupons(String bpCustMemberId,BpActivityManage bpActivityManage);
	
	public List<BpCoupons>  getActivityType(String Type,Long activityId,String bpCustMemberId);
	
	public  BpPersonCenterData getCoupon(Long id);
	
	public String listCouponsNew(BpCustMember member,HttpServletRequest request);
}


