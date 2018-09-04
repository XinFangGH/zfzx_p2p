package com.hurong.credit.action.p2p.loan;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
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


import com.hurong.credit.model.p2p.loan.P2pLoanConditionOrMaterial;
import com.hurong.credit.service.p2p.loan.P2pLoanConditionOrMaterialService;
/**
 * 
 * @author 
 *
 */
public class P2pLoanConditionOrMaterialAction extends BaseAction{
	@Resource
	private P2pLoanConditionOrMaterialService p2pLoanConditionOrMaterialService;
	private P2pLoanConditionOrMaterial p2pLoanConditionOrMaterial;
	
	private Long conditionId;

	public Long getConditionId() {
		return conditionId;
	}

	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}

	public P2pLoanConditionOrMaterial getP2pLoanConditionOrMaterial() {
		return p2pLoanConditionOrMaterial;
	}

	public void setP2pLoanConditionOrMaterial(P2pLoanConditionOrMaterial p2pLoanConditionOrMaterial) {
		this.p2pLoanConditionOrMaterial = p2pLoanConditionOrMaterial;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<P2pLoanConditionOrMaterial> list= p2pLoanConditionOrMaterialService.getAll(filter);
		
		Type type=new TypeToken<List<P2pLoanConditionOrMaterial>>(){}.getType();
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
				p2pLoanConditionOrMaterialService.remove(new Long(id));
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
		P2pLoanConditionOrMaterial p2pLoanConditionOrMaterial=p2pLoanConditionOrMaterialService.get(conditionId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(p2pLoanConditionOrMaterial));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(p2pLoanConditionOrMaterial.getConditionId()==null){
			p2pLoanConditionOrMaterialService.save(p2pLoanConditionOrMaterial);
		}else{
			P2pLoanConditionOrMaterial orgP2pLoanConditionOrMaterial=p2pLoanConditionOrMaterialService.get(p2pLoanConditionOrMaterial.getConditionId());
			try{
				BeanUtil.copyNotNullProperties(orgP2pLoanConditionOrMaterial, p2pLoanConditionOrMaterial);
				p2pLoanConditionOrMaterialService.save(orgP2pLoanConditionOrMaterial);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
