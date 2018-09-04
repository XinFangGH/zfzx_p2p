package com.hurong.credit.service.message.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.JsonUtil;
import com.hurong.credit.dao.message.OaNewsMessageDao;
import com.hurong.credit.dao.message.OaNewsMessagerinfoDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.message.OaNewsMessageService;
import com.hurong.credit.service.user.BpCustMemberService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class OaNewsMessageServiceImpl extends BaseServiceImpl<OaNewsMessage> implements OaNewsMessageService{
	@SuppressWarnings("unused")
	private OaNewsMessageDao dao;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	@Resource
	private OaNewsMessagerinfoDao oaNewsMessagerinfoDao;
	public OaNewsMessageServiceImpl(OaNewsMessageDao dao) {
		super(dao);
		this.dao=dao;
	}

	/**
	 * 向所有用户发送站内信
	 */
	@Override
	public void sendAllUser(OaNewsMessage oaNewsMessage) {
		// TODO Auto-generated method stub
		
		List<BpCustMember> list = bpCustMemberService.getAllUserful();
		try{
			for (int i = 0; i < list.size(); i++) {
				BpCustMember bpCustMember = list.get(i);
				Long memberID = bpCustMember.getId();
				oaNewsMessage.setRecipient(memberID);
				oaNewsMessage.setSendTime(new Date());
				dao.save(oaNewsMessage);
			
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		
	}

	/**
	 * 获取单个用户的所有站内信
	 * userId 用户ID
	 * startpage 首行
	 * pagesize 每页数量
	 */
	@Override
	public List<OaNewsMessage> getUserAll(Long userId,int startpage,int pagesize,int status1,int status2) {
		return dao.getUserAll(userId, startpage, pagesize,status1,status2)
		;
	}

	@Override
	public int getStatusNum(int userId, int status) {
		return dao.getStatusNum(userId,status);
	}

	@Override
	public void sedBpcouponsMessage(String title, String content,
			Long memberId, String remaker) {
		OaNewsMessage oa=new OaNewsMessage();
		oa.setTitle(title);//发送标题
		oa.setAddresser("超级管理员");//发送人
		oa.setContent(content);//发送内容
		oa.setType("coupons");//类型
		oa.setTypename("优惠券通知");//类型名称
		oa.setRecipient(memberId);//接收人
		oa.setSendTime(new Date());//发送时间
		oa.setStatus("1");
		oa.setIsAllSend("0");
		BpCustMember bp=bpCustMemberDao.get(memberId);
		oa.setComment1(bp.getLoginname());
		oa.setComment2(memberId.toString());
		dao.save(oa);

		OaNewsMessagerinfo info= new OaNewsMessagerinfo();
		info.setUserId(memberId);//收件人Id
		if(bp!=null){
			info.setUserName(bp.getTruename());			
		}
		info.setUserType("P2P");//P2P登陆用户
		info.setMessageId(oa.getId());
		info.setReadStatus(0);//默认阅读状态为未读
		info.setStatus(2);//已发送
		info.setIstop(0);//默认不置顶
		oaNewsMessagerinfoDao.save(info);
	}

	@Override
	public String queryAllData(Long userId) {
		// TODO Auto-generated method stub
		BpPersonCenterData data = dao.queryOaNews(userId);
		StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
		JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buffer.append(json.serialize(data));
		buffer.append("}");
		return buffer.toString();
	}

	@Override
	public String queryAllInfo(HttpServletRequest request, BpCustMember member) {
        List<OaNewsMessagerinfo> list = dao.getAllInfo(request, member);
        Integer listNums = dao.getAllInfo(null, member).size();
		StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(listNums).append(",\"result\":");
		JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buffer.append(json.serialize(list));
		buffer.append("}");
		return buffer.toString();
	}
	@Override
	public BpPersonCenterData getMessage(Long userId){
		return dao.queryOaNews(userId);
	}
}