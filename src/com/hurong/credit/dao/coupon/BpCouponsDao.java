package com.hurong.credit.dao.coupon;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface BpCouponsDao extends BaseDao<BpCoupons>{

	public void saveList(List<BpCoupons> list);

	public List<BpCoupons> listForNotDistributeCoupon(
			HttpServletRequest request, Integer start, Integer limit);

	public List<BpCoupons> bouponBelongList(HttpServletRequest request,
			Integer start, Integer limit);
	public List<BpCoupons>  getActivityType(String Type,Long activityId,String bpCustMemberId);
	
	public BpPersonCenterData getCoupon(Long id);
	public List<BpCoupons>  listCouponsNew(BpCustMember member,HttpServletRequest request);
	public List<BpCoupons>  listCouponsNewNums(BpCustMember member,HttpServletRequest request);

}