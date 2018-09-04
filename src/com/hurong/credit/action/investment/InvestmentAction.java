package com.hurong.credit.action.investment;


import javax.annotation.Resource;

import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;




public class InvestmentAction extends BaseAction {

	private static final long serialVersionUID = -25541236985328967L;
	private BpCustMember bpCustMember;
	@Resource
	private BpCustMemberService bpCustMemberService;
	
	
	



	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}


	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}


	//我要投资
	public String tomy(){
		
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.INVESTMENT).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}
	
	//利益计算器
	public String interest(){
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.INTERESTCALCULATE).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}
	
	//我要投资详情
	public String detail(){
		BpCustMember mem=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			bpCustMember=bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.INVESTMENTDETAIL).getTemplateFilePath());
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}
	

}