package com.hurong.credit.dao.creditFlow.bonusSystem.record;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.creditFlow.bonusSystem.setting.WebBonusSetting;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface WebBonusRecordDao extends BaseDao<WebBonusRecord>{
	
	/**
	 * 通过积分规则配置的时间找出积分记录
	 * @param webBonusSetting
	 * @return
	 */
	public List<WebBonusRecord> findBySettingEngine(Long userId,WebBonusSetting webBonusSetting);
	/**
	 * 查询所对应的积分
	 * @param userId
	 * @param recordDirector
	 * @return
	 */
	public int findRecordNumber(Long userId,String recordDirector);
	public List<WebBonusRecord> getActivityNumber(String activityNumber,String bpCustMemberId,String remark);

	/**
	 * 查询我的积分的方法
	 */
	public List<WebBonusRecord> getMyIntegral(HttpServletRequest request, BpCustMember member);
	
	
	public List<WebBonusRecord> getMyIntegralNum(HttpServletRequest request, BpCustMember member);
	
	/**
	 * 查询我的积分的统计数据
	 */
	public BpPersonCenterData queryMyIntegral(BpCustMember member);
	
}