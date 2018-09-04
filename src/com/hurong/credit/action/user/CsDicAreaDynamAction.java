package com.hurong.credit.action.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.user.CsDicAreaDynam;
import com.hurong.credit.service.user.CsDicAreaDynamService;
/**
 * 
 * @author 
 *
 */
public class CsDicAreaDynamAction extends BaseAction{
	@Resource
	private CsDicAreaDynamService csDicAreaDynamService;
	private CsDicAreaDynam csDicAreaDynam;
	private Map<Long,String> map = new HashMap<Long,String>();
	private Integer parentId;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CsDicAreaDynam getCsDicAreaDynam() {
		return csDicAreaDynam;
	}

	public void setCsDicAreaDynam(CsDicAreaDynam csDicAreaDynam) {
		this.csDicAreaDynam = csDicAreaDynam;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<CsDicAreaDynam> list= csDicAreaDynamService.getAll(filter);
		
		Type type=new TypeToken<List<CsDicAreaDynam>>(){}.getType();
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
				csDicAreaDynamService.remove(new Long(id));
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
		CsDicAreaDynam csDicAreaDynam=csDicAreaDynamService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(csDicAreaDynam));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(csDicAreaDynam.getId()==null){
			csDicAreaDynamService.save(csDicAreaDynam);
		}else{
			CsDicAreaDynam orgCsDicAreaDynam=csDicAreaDynamService.get(csDicAreaDynam.getId());
			try{
				BeanUtil.copyNotNullProperties(orgCsDicAreaDynam, csDicAreaDynam);
				csDicAreaDynamService.save(orgCsDicAreaDynam);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	/**
	 * 获取省市列表
	 * @return
	 */
	public String getAreaList(){
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		if(parentId>=0){
			map = csDicAreaDynamService.getAreaList(parentId);
		}
		
		
		sb.append(gson.toJson(map));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
}
