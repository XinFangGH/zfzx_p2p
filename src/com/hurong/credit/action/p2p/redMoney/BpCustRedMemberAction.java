package com.hurong.credit.action.p2p.redMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import javax.annotation.Resource;

import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.p2p.redMoney.BpCustRedMember;
import com.hurong.credit.service.p2p.redMoney.BpCustRedEnvelopeService;
import com.hurong.credit.service.p2p.redMoney.BpCustRedMemberService;
import com.hurong.credit.service.user.BpCustMemberService;
/**
 * 
 * @author 
 *
 */
public class BpCustRedMemberAction extends BaseAction{
	@Resource
	private BpCustRedMemberService bpCustRedMemberService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private BpCustRedEnvelopeService bpCustRedEnvelopeService;
	
	
	private BpCustRedMember bpCustRedMember;
	
	private Long redTopersonId;

	public Long getRedTopersonId() {
		return redTopersonId;
	}

	public void setRedTopersonId(Long redTopersonId) {
		this.redTopersonId = redTopersonId;
	}

	public BpCustRedMember getBpCustRedMember() {
		return bpCustRedMember;
	}

	public void setBpCustRedMember(BpCustRedMember bpCustRedMember) {
		this.bpCustRedMember = bpCustRedMember;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		return SUCCESS;
	}
}
