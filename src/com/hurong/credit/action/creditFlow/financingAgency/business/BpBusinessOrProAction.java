package com.hurong.credit.action.creditFlow.financingAgency.business;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessOrProService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class BpBusinessOrProAction extends BaseAction{
	@Resource
	private BpBusinessOrProService bpBusinessOrProService;
	private BpBusinessOrPro bpBusinessOrPro;
	
	private Long borProId;

	public Long getBorProId() {
		return borProId;
	}

	public void setBorProId(Long borProId) {
		this.borProId = borProId;
	}

	public BpBusinessOrPro getBpBusinessOrPro() {
		return bpBusinessOrPro;
	}

	public void setBpBusinessOrPro(BpBusinessOrPro bpBusinessOrPro) {
		this.bpBusinessOrPro = bpBusinessOrPro;
	}
	/**
	 * 统计发标情况
	 */
	@SuppressWarnings("null")
	public String listPublish() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BpBusinessOrPro> list = bpBusinessOrProService.getAll(filter);
		List<BpBusinessOrPro> listCurr = new ArrayList<BpBusinessOrPro>();
		for (BpBusinessOrPro pack : list) {
			// 计算打包项目的剩余金额
			pack = bpBusinessOrProService.residueMoneyMeth(pack);
			listCurr.add(pack);
		}
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				"createTime", "updateTime","loanStarTime","loanEndTime");
		buff.append(serializer.exclude(new String[] { "class" }).serialize(
				listCurr));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}
	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BpBusinessOrPro> list= bpBusinessOrProService.getAll(filter);
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(
				",result:");
JSONSerializer serializer = JsonUtil.getJSONSerializer("bidTime",
		"createTime", "updateTime");
buff.append(serializer.exclude(new String[] { "class" })
		.serialize(list));
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
				bpBusinessOrProService.remove(new Long(id));
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
		BpBusinessOrPro bpBusinessOrPro=bpBusinessOrProService.get(borProId);
		
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				 "bidTime", "createTime",
				"updateTime","loanStarTime","loanEndTime");
		sb.append(serializer.exclude(
				new String[] { "class"}).serialize(
						bpBusinessOrPro));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bpBusinessOrPro.getBorProId()==null){
			bpBusinessOrProService.save(bpBusinessOrPro);
		}else{
			BpBusinessOrPro orgBpBusinessOrPro=bpBusinessOrProService.get(bpBusinessOrPro.getBorProId());
			try{
				BeanUtil.copyNotNullProperties(orgBpBusinessOrPro, bpBusinessOrPro);
				bpBusinessOrProService.save(orgBpBusinessOrPro);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
