package com.hurong.credit.action.p2p.loan;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.p2p.loan.P2pLoanApplyStep;
import com.hurong.credit.service.p2p.loan.P2pLoanApplyStepService;
/**
 * 
 * @author 
 *
 */
public class P2pLoanApplyStepAction extends BaseAction{
	@Resource
	private P2pLoanApplyStepService p2pLoanApplyStepService;
	private P2pLoanApplyStep p2pLoanApplyStep;
	
	private Long stepId;

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public P2pLoanApplyStep getP2pLoanApplyStep() {
		return p2pLoanApplyStep;
	}

	public void setP2pLoanApplyStep(P2pLoanApplyStep p2pLoanApplyStep) {
		this.p2pLoanApplyStep = p2pLoanApplyStep;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<P2pLoanApplyStep> list= p2pLoanApplyStepService.getAll(filter);
		
		Type type=new TypeToken<List<P2pLoanApplyStep>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				p2pLoanApplyStepService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		P2pLoanApplyStep p2pLoanApplyStep=p2pLoanApplyStepService.get(stepId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(p2pLoanApplyStep));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(p2pLoanApplyStep.getStepId()==null){
			p2pLoanApplyStepService.save(p2pLoanApplyStep);
		}else{
			P2pLoanApplyStep orgP2pLoanApplyStep=p2pLoanApplyStepService.get(p2pLoanApplyStep.getStepId());
			try{
				BeanUtil.copyNotNullProperties(orgP2pLoanApplyStep, p2pLoanApplyStep);
				p2pLoanApplyStepService.save(orgP2pLoanApplyStep);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
