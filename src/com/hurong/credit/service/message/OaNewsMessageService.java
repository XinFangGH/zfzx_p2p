package com.hurong.credit.service.message;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface OaNewsMessageService extends BaseService<OaNewsMessage>{
	public void sendAllUser(OaNewsMessage oaNewsMessage);
	
	/**
	 * 获取单个用户的所有站内信
	 * userId 用户ID
	 * startpage 首行
	 * pagesize 每页数量
	 */
	public List<OaNewsMessage> getUserAll(Long userId,int startpage,int pagesize,int status1,int status2);
	public int getStatusNum(int userId,int status);
	public void sedBpcouponsMessage(String title, String content,
			Long memberId, String remaker);
	public String queryAllData(Long userId);
	public String queryAllInfo(HttpServletRequest request,BpCustMember member);
	public BpPersonCenterData getMessage(Long userId);
}


