package com.hurong.credit.service.creditFlow.bonusSystem.setting.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.bonusSystem.setting.WebBonusSettingDao;
import com.hurong.credit.model.creditFlow.bonusSystem.setting.WebBonusSetting;
import com.hurong.credit.service.creditFlow.bonusSystem.setting.WebBonusSettingService;

/**
 * 
 * @author 
 *
 */
public class WebBonusSettingServiceImpl extends BaseServiceImpl<WebBonusSetting> implements WebBonusSettingService{
	@SuppressWarnings("unused")
	private WebBonusSettingDao dao;
	
	public WebBonusSettingServiceImpl(WebBonusSettingDao dao) {
		super(dao);
		this.dao=dao;
	}


	@Override
	public WebBonusSetting excludeALike(Map<String, String> queryMap) {
		
		List<WebBonusSetting> list = dao.excludeALike(queryMap);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
		
	}


	@Override
	public WebBonusSetting hasWebBonusSetting(String userAction,String userActionKey, String memberLevel) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userAction", userAction);
		map.put("userActionKey", userActionKey);
		map.put("memberLevel", memberLevel);
		List<WebBonusSetting> list = dao.excludeALike(map);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}