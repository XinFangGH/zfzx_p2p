package com.hurong.credit.action.creditFlow.financingAgency.persion;
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


import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionOrProService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class BpPersionOrProAction extends BaseAction{
	@Resource
	private BpPersionOrProService bpPersionOrProService;
	private BpPersionOrPro bpPersionOrPro;
	
	private Long porProId;

	public Long getPorProId() {
		return porProId;
	}

	public void setPorProId(Long porProId) {
		this.porProId = porProId;
	}

	public BpPersionOrPro getBpPersionOrPro() {
		return bpPersionOrPro;
	}

	public void setBpPersionOrPro(BpPersionOrPro bpPersionOrPro) {
		this.bpPersionOrPro = bpPersionOrPro;
	}
	/**
	 * 统计发标情况
	 */
	@SuppressWarnings("null")
	public String listPublish() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BpPersionOrPro> list = bpPersionOrProService.getAll(filter);
		List<BpPersionOrPro> listCurr = new ArrayList<BpPersionOrPro>();
		for (BpPersionOrPro pack : list) {
			// 计算打包项目的剩余金额
			pack = bpPersionOrProService.residueMoneyMeth(pack);
			listCurr.add(pack);
		}
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				"createTime", "updateTime","loanStarTime", "loanEndTime");
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
		List<BpPersionOrPro> list= bpPersionOrProService.getAll(filter);
		
		Type type=new TypeToken<List<BpPersionOrPro>>(){}.getType();
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
				bpPersionOrProService.remove(new Long(id));
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
		BpPersionOrPro bpPersionOrPro=bpPersionOrProService.get(porProId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bpPersionOrPro));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bpPersionOrPro.getPorProId()==null){
			bpPersionOrProService.save(bpPersionOrPro);
		}else{
			BpPersionOrPro orgBpPersionOrPro=bpPersionOrProService.get(bpPersionOrPro.getPorProId());
			try{
				BeanUtil.copyNotNullProperties(orgBpPersionOrPro, bpPersionOrPro);
				bpPersionOrProService.save(orgBpPersionOrPro);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
