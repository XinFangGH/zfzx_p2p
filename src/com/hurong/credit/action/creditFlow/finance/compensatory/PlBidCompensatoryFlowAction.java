package com.hurong.credit.action.creditFlow.finance.compensatory;
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


import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatoryFlow;
import com.hurong.credit.service.creditFlow.finance.compensatory.PlBidCompensatoryFlowService;
/**
 * 
 * @author 
 *
 */
public class PlBidCompensatoryFlowAction extends BaseAction{
	@Resource
	private PlBidCompensatoryFlowService plBidCompensatoryFlowService;
	private PlBidCompensatoryFlow plBidCompensatoryFlow;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlBidCompensatoryFlow getPlBidCompensatoryFlow() {
		return plBidCompensatoryFlow;
	}

	public void setPlBidCompensatoryFlow(PlBidCompensatoryFlow plBidCompensatoryFlow) {
		this.plBidCompensatoryFlow = plBidCompensatoryFlow;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PlBidCompensatoryFlow> list= plBidCompensatoryFlowService.getAll(filter);
		
		Type type=new TypeToken<List<PlBidCompensatoryFlow>>(){}.getType();
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
				plBidCompensatoryFlowService.remove(new Long(id));
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
		PlBidCompensatoryFlow plBidCompensatoryFlow=plBidCompensatoryFlowService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plBidCompensatoryFlow));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(plBidCompensatoryFlow.getId()==null){
			plBidCompensatoryFlowService.save(plBidCompensatoryFlow);
		}else{
			PlBidCompensatoryFlow orgPlBidCompensatoryFlow=plBidCompensatoryFlowService.get(plBidCompensatoryFlow.getId());
			try{
				BeanUtil.copyNotNullProperties(orgPlBidCompensatoryFlow, plBidCompensatoryFlow);
				plBidCompensatoryFlowService.save(orgPlBidCompensatoryFlow);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
