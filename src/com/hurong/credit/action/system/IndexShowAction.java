package com.hurong.credit.action.system;
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


import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.service.system.IndexShowService;
/**
 * 
 * @author 
 *
 */
public class IndexShowAction extends BaseAction{
	@Resource
	private IndexShowService indexShowService;
	private IndexShow indexShow;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IndexShow getIndexShow() {
		return indexShow;
	}

	public void setIndexShow(IndexShow indexShow) {
		this.indexShow = indexShow;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<IndexShow> list= indexShowService.getAll(filter);
		
		Type type=new TypeToken<List<IndexShow>>(){}.getType();
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
				indexShowService.remove(new Long(id));
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
		IndexShow indexShow=indexShowService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(indexShow));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(indexShow.getId()==null){
			indexShowService.save(indexShow);
		}else{
			IndexShow orgIndexShow=indexShowService.get(indexShow.getId());
			try{
				BeanUtil.copyNotNullProperties(orgIndexShow, indexShow);
				indexShowService.save(orgIndexShow);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
