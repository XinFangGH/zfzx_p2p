package com.hurong.credit.action.p2p;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
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


import com.hurong.credit.model.p2p.PlatDataPublish;
import com.hurong.credit.service.p2p.PlatDataPublishService;
/**
 * 
 * @author 
 *
 */
public class PlatDataPublishAction extends BaseAction{
	@Resource
	private PlatDataPublishService platDataPublishService;
	private PlatDataPublish platDataPublish;
	
	private Long publishId;

	public Long getPublishId() {
		return publishId;
	}

	public void setPublishId(Long publishId) {
		this.publishId = publishId;
	}

	public PlatDataPublish getPlatDataPublish() {
		return platDataPublish;
	}

	public void setPlatDataPublish(PlatDataPublish platDataPublish) {
		this.platDataPublish = platDataPublish;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PlatDataPublish> list= platDataPublishService.getAll(filter);
		
		Type type=new TypeToken<List<PlatDataPublish>>(){}.getType();
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
				platDataPublishService.remove(new Long(id));
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
		PlatDataPublish platDataPublish=platDataPublishService.get(publishId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(platDataPublish));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(platDataPublish.getPublishId()==null){
			platDataPublishService.save(platDataPublish);
		}else{
			PlatDataPublish orgPlatDataPublish=platDataPublishService.get(platDataPublish.getPublishId());
			try{
				BeanUtil.copyNotNullProperties(orgPlatDataPublish, platDataPublish);
				platDataPublishService.save(orgPlatDataPublish);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}