package com.hurong.credit.action.user;
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


import com.hurong.credit.model.user.BpCustLoginlog;
import com.hurong.credit.service.user.BpCustLoginlogService;
/**
 * 
 * @author 
 *
 */
public class BpCustLoginlogAction extends BaseAction{
	@Resource
	private BpCustLoginlogService bpCustLoginlogService;
	private BpCustLoginlog bpCustLoginlog;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BpCustLoginlog getBpCustLoginlog() {
		return bpCustLoginlog;
	}

	public void setBpCustLoginlog(BpCustLoginlog bpCustLoginlog) {
		this.bpCustLoginlog = bpCustLoginlog;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BpCustLoginlog> list= bpCustLoginlogService.getAll(filter);
		
		Type type=new TypeToken<List<BpCustLoginlog>>(){}.getType();
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
				bpCustLoginlogService.remove(new Long(id));
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
		BpCustLoginlog bpCustLoginlog=bpCustLoginlogService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bpCustLoginlog));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(bpCustLoginlog.getId()==null){
			bpCustLoginlogService.save(bpCustLoginlog);
		}else{
			BpCustLoginlog orgBpCustLoginlog=bpCustLoginlogService.get(bpCustLoginlog.getId());
			try{
				BeanUtil.copyNotNullProperties(orgBpCustLoginlog, bpCustLoginlog);
				bpCustLoginlogService.save(orgBpCustLoginlog);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
