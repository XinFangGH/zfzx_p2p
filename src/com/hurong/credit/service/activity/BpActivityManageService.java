package com.hurong.credit.service.activity;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PageBean;
import com.hurong.credit.model.activity.BpActivityManage;

/**
 * 
 * @author 
 *
 */
public interface BpActivityManageService extends BaseService<BpActivityManage>{

	/**
	 * 根据活动类型及当前日期生成活动编号
	 * @param flag
	 */
	public void findActivityNumber(String flag);
	
	/**
	 * 根据活动类型及当前日期生成活动编号
	 * @param bpActivityManage 活动实体类
	 * @return true存在 false不存在
	 */
	public boolean findExistCrossDate(BpActivityManage bpActivityManage);
	
	/**
	 * 根基ids集合删除相应活动
	 * @param ids
	 * @return true成功, false失败
	 */
	public boolean closeActivity(String[] ids);

	/**
	 * 根据pageBean查询集合
	 * @param pageBean
	 */
	public void findList(PageBean<BpActivityManage> pageBean);
	
	
	
	/**
	 * 查找全部当前日期存活的活动(含积分丶红包丶优惠券)
	 * 说明--当前活动没有被关闭，并且活动结束日期包含当前日期
	 * @return
	 * @author LIUSL
	 */
	public List<BpActivityManage> listActivity(String activityType);
	
	
	/**
	 * 自动分发活动奖励引擎
	 * 活动中心，所有活动查询
	 * --自动派发积分，
	 *   自动派发红包
	 *   自动派发优惠券
     * @param bpCustMemberId   --p2p账户ID
     * @param money   --金额--用于投标，充值时进行条件判断
     * @param activityType   --活动操作类型
     * 		  		["注册", "1"],
					["邀请", "2"],
					["投标", "3"],
					["充值", "4"],
					["邀请好友第一次投标", "5"]			
     * 
     * 
     * 
     * @author LIUSL
	 */
	public void autoDistributeEngine(String activityType, String bpCustMemberId,BigDecimal money);

	
	public String showMyCoupons(HttpServletRequest request);

	
	public String CreateJson(Object obj,Integer Num); 
}


