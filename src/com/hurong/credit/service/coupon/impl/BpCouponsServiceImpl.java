package com.hurong.credit.service.coupon.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.credit.dao.coupon.BpCouponsDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.activity.BpActivityManage;
import com.hurong.credit.model.coupon.BpCouponSetting;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.coupon.BpCouponsService;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.sms.SendMesService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class BpCouponsServiceImpl extends BaseServiceImpl<BpCoupons> implements BpCouponsService{
	@SuppressWarnings("unused")
	private BpCouponsDao dao;
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	@Resource
	private OaNewsMessageService oaNewsMessageService;
	@Resource
	private SendMesService sendMesService;
	private static Map configMap = AppUtil.getConfigMap();
	public BpCouponsServiceImpl(BpCouponsDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Boolean createBpCoupons(BpCouponSetting bps,
			String couponresourceNormal) {
		// TODO Auto-generated method stub
		try {
			List<BpCoupons> list=new ArrayList<BpCoupons>();
			if(bps!=null){
				for(int i=0;i<bps.getCounponCount();i++){
					BpCoupons bp=new BpCoupons();
					bp.setCouponResourceType(couponresourceNormal);
					bp.setResourceId(bps.getCategoryId());
					bp.setCompanyId(bps.getCompanyId());
					bp.setCreateDate(new Date());
					bp.setCreateName(bps.getCouponCheckUserName());
					bp.setCreateUserId(bps.getCouponCheckUserId());
					bp.setCouponValue(bps.getCouponValue());
					bp.setCouponStartDate(bps.getCouponStartDate());
					bp.setCouponEndDate(bps.getCouponEndDate());
					bp.setCouponTypeValue(bps.getCouponTypeValue());
					bp.setCouponStatus(Short.valueOf("0"));
					bp.setCouponNumber(this.createCouponNumber(bps.getCouponType()));
					dao.save(bp);
				}
				//批量保存生成的代金券方法
				//dao.saveList(list);
				return true;
			}else{//没有对象时返回空
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public String createCouponNumber(String couponType) {
		// TODO Auto-generated method stub
		Long time =System.nanoTime();
		if(couponType.equals("")){
			
		}else{
			
		}
		System.out.println("time=="+time);
		
		return time.toString();
	}

	@Override
	public List<BpCoupons> getActivityType(String Type, Long activityId,
			String bpCustMemberId) {
		// TODO Auto-generated method stub
		return dao.getActivityType(Type, activityId, bpCustMemberId);
	}

	/**
	 * 优惠券派发方法
	 */
	@Override
	public Boolean couponDistribute(Long id, String truename,Long opraterId,String oprateName,BpCoupons bps) {
		// TODO Auto-generated method stub
		try {
			bps.setBelongUserId(id);
			bps.setCouponStatus(BpCoupons.COUPONSTATUS5);
			bps.setBelongUserName(truename);
			bps.setBindOpratorId(opraterId);
			bps.setBindOpratorName(oprateName);
			bps.setBindOpraterDate(new Date());
			dao.merge(bps);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 查询出来所有没有派发的优惠券
	 */
	@Override
	public List<BpCoupons> listForNotDistributeCoupon(
			HttpServletRequest request, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return dao.listForNotDistributeCoupon(request,start,limit);
	}
	/**
	 * 查询出来所有派发过的优惠券
	 */
	@Override
	public List<BpCoupons> bouponBelongList(HttpServletRequest request,
			Integer start, Integer limit) {
		 List<BpCoupons> list = dao.bouponBelongList(request,start,limit);
		 if(list!=null&&list.size()>0){
			 for(BpCoupons bp : list){
				 dao.evict(bp);
				 if(bp.getBelongUserId()!=null){
					 BpCustMember bpCustMember = bpCustMemberDao.get(bp.getBelongUserId());
					 bp.setLogginName(bpCustMember.getLoginname());
				 }
			 }
			 return list;
		 }
		 return null;
		 
	}
	@Override
	public BpCoupons saveBpCoupons(String bpCustMemberId,
			BpActivityManage bpActivityManage) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BpCustMember bpCustMember = bpCustMemberDao.get(Long.valueOf(bpCustMemberId));
		BpCoupons bp=new BpCoupons();
		bp.setCouponResourceType(BpCoupons.COUPONRESOURCE_ACTIVE);//来源
		bp.setCompanyId(Long.valueOf(1));
		bp.setResourceId(bpActivityManage.getActivityId());
		bp.setCreateName("系统");
		bp.setCreateUserId(Long.valueOf(0));
		bp.setCreateDate(new Date());
		bp.setCouponType(bpActivityManage.getCouponType());
		bp.setCouponStatus(Short.valueOf("5"));
		bp.setCouponValue(new BigDecimal(bpActivityManage.getParValue().longValue()));
		bp.setCouponStartDate(new Date());
		bp.setCouponEndDate(DateUtil.addDaysToDate(new Date(), bpActivityManage.getValidNumber()));
		bp.setCouponNumber(this.createCouponNumber(BpCoupons.COUPONRESOURCE_ACTIVE));
		bp.setBindOpratorName("系统");
		bp.setBindOpraterDate(new Date());
		
		bp.setBelongUserId(Long.valueOf(bpCustMemberId));
		bp.setBelongUserName(bpCustMember.getTruename());
		bp.setIntention(bpActivityManage.findSendType());//操作点名称
		bp.setCouponsDescribe(bpActivityManage.getActivityExplain());//描述
		BpCoupons save = dao.save(bp);
		
		//参与活动成功发送消息提醒
		Map<String, String> mapMessage = new HashMap<String, String>();
		mapMessage.put("key", "sms_activityMessage");
		mapMessage.put("userId",bpCustMember.getId().toString());
		mapMessage.put("${activity}", bp.getCouponsDescribe());
		mapMessage.put("${parValue}", bp.getCouponValue().toString());
		mapMessage.put("${endTime}",sdf.format(bp.getCouponEndDate()));
		String result =  sendMesService.sendSmsEmailMessage(mapMessage);
		
		//发送站内信
		//sms_oaMessage(bp.getCouponValue(),bp.getCouponEndDate(),bp.getCouponType().toString(),Long.valueOf(bpCustMemberId),bp.getCouponNumber(),bp.getCouponsDescribe());
		
		return save;
	}
	
	/**
	 * 发送站内信
	 * @param bpCustMemberId
	 * @param bpActivityManage
	 */
	public void sms_oaMessage(BigDecimal couponValue,Date endTime,String couponType,Long memberId,String couponNumber,String couponsDescribe){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String project=configMap.get("subject").toString();
		String telphone=configMap.get("phone").toString();
		Map configSmsMap=AppUtil.getConfigMap();
		String dw="";
		if(couponType.equals("1")||couponType.equals("2")){
			dw="元";
		}else if(couponType.equals("3")){
			dw="%";
		}
		String content="";
			String temp = "";
			if(configSmsMap.get("sms_activityMessage")!=null){
				temp  = configSmsMap.get("sms_activityMessage").toString();
				content = temp.replace("${parValue}", couponValue.setScale(2)+dw).replace("${endTime}", 
						sdf.format(endTime)).replace("${activity}", couponsDescribe).replace("${telphone}", telphone).replace("${project}", project);
				String title="优惠券派发提醒";
				oaNewsMessageService.sedBpcouponsMessage(title,content,memberId,"");		
			}
		
	}
	//查询优惠券
	@Override
	public BpPersonCenterData getCoupon(Long id){
		return dao.getCoupon(id); 
	}
	
	

	@Override
	public String listCouponsNew(BpCustMember member, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BpCoupons> list = dao.listCouponsNew(member, request);
		Integer listNum = dao.listCouponsNewNums(member, request).size();
		StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(5).append(",\"result\":");
		if(request.getParameter("detail")!=null&&!"".equals(request.getParameter("detail"))){
			buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(listNum).append(",\"result\":");
		}
	    JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buff1.append(json.serialize(list));
		buff1.append("}");
		return buff1.toString();
	}
}