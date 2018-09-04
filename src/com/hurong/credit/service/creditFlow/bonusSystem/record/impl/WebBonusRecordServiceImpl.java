package com.hurong.credit.service.creditFlow.bonusSystem.record.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.JsonUtil;
import com.hurong.credit.dao.creditFlow.bonusSystem.record.WebBonusRecordDao;
import com.hurong.credit.model.creditFlow.bonusSystem.WebBonusConstant;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.creditFlow.bonusSystem.setting.WebBonusSetting;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.bonusSystem.record.WebBonusRecordService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class WebBonusRecordServiceImpl extends BaseServiceImpl<WebBonusRecord> implements WebBonusRecordService{
	@SuppressWarnings("unused")
	private WebBonusRecordDao dao;
	
	public WebBonusRecordServiceImpl(WebBonusRecordDao dao) {
		super(dao);
		this.dao=dao;
	}


	@Override
	public Long findBySetting(Long userId ,WebBonusSetting webBonusSetting) {
		
		/**
		 * 一丶如果webBonusSetting 规则的周期类型为一次性--直接返回奖励分值
		 * 二丶通过规则查找对应的积分记录，返回符合条件的相同记录
		 *   返回一个list
		 * 	   如果list不为空，并且list.size()大于等于积分规则中的周期内奖励次数则,返回0
		 * 	   否则返回奖励分值
		 */
		//一丶
		if(WebBonusConstant.BOMUSPERIOD_TYPE_ONCE.equals(webBonusSetting.getBomusPeriodType())){
			return Long.valueOf(webBonusSetting.getBonus());
		}
		
		//二丶
		List<WebBonusRecord> list = dao.findBySettingEngine(userId,webBonusSetting);
		if(list!=null&&list.size()>=Integer.parseInt(webBonusSetting.getPeriodBonusLimit())){
			return Long.valueOf(0);
		}
		return Long.valueOf(webBonusSetting.getBonus());
		
	}


	@Override
	public List<WebBonusRecord> listByUserId(Long userId, String sort) {
		StringBuffer sb = new StringBuffer(" from WebBonusRecord where customerId = ? ");
		if(sort!=null&&!"".equals(sort)){
			sb.append(" order by  createTime  " + sort + " ");
		}else{
			sb.append(" order by  createTime desc ");
		}
		return dao.findByHql(sb.toString(), new Object[]{userId.toString()});
	}


	@Override
	public int findRecordNumber(Long userId, String recordDirector) {
		// TODO Auto-generated method stub
		return dao.findRecordNumber(userId,recordDirector);
	}


	@Override
	public List<WebBonusRecord> getActivityNumber(String activityNumber,
			String bpCustMemberId, String remark) {
		// TODO Auto-generated method stub
		return dao.getActivityNumber(activityNumber, bpCustMemberId, remark);
	}


	@Override
	public String getMyIntegral(HttpServletRequest request,
			BpCustMember member) {
		// TODO Auto-generated method stub
		List<WebBonusRecord> list = dao.getMyIntegral(request, member);
		Integer listNum = dao.getMyIntegralNum(request, member).size();
		return CreateJson(list,5);
	}

	@Override
	public String queryMyIntegral(BpCustMember member) {
		// TODO Auto-generated method stub
		BpPersonCenterData data =  dao.queryMyIntegral(member);
		return CreateJson(data ,1);
	}

	@Override
	public String CreateJson(Object obj,Integer Num) {
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(Num).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buff1.append(json.serialize(obj));
		buff1.append("}");
		return buff1.toString();
	}


	
	
}