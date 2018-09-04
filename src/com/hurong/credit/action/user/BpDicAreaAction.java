package com.hurong.credit.action.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.StringUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.user.BpDicArea;
import com.hurong.credit.service.user.BpDicAreaService;
/**
 * 
 * @author 
 *
 */
public class BpDicAreaAction extends BaseAction{
	@Resource
	private BpDicAreaService bpDicAreaService;
	private BpDicArea bpDicArea;
	
	private String AreaCode;
	private String ParentCode;
	private Map<String,String> map = new HashMap<String,String>();
	
	

	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String AreaCode) {
		this.AreaCode = AreaCode;
	}

	public BpDicArea getBpDicArea() {
		return bpDicArea;
	}

	public void setBpDicArea(BpDicArea bpDicArea) {
		this.bpDicArea = bpDicArea;
	}

	public String getParentCode() {
		return ParentCode;
	}

	public void setParentCode(String parentCode) {
		ParentCode = StringUtil.html2Text(parentCode);
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BpDicArea> list= bpDicAreaService.getAll(filter);
		
		Type type=new TypeToken<List<BpDicArea>>(){}.getType();
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
				bpDicAreaService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	


	/**
	 * 获取省市列表
	 * @return
	 */
	public String getList(){
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		//	StringBuffer sb = new StringBuffer("{success:true,data:");
		List<BpDicArea> list=null;
		StringBuffer sb = new StringBuffer("");
		if(ParentCode!=null&&ParentCode.length()>0){
			list = bpDicAreaService.getAreaList(ParentCode);
		}else{
			ParentCode = "-1";
			list = bpDicAreaService.getAreaList(ParentCode);
		}
		if(list!=null&&list.size()>0){
			for(BpDicArea temp:list){
				sb.append("<option value='").append(temp.getAreaCode()).append("'>").append(temp.getAreaName()).append("</option>");
			}
		}
		setJsonString(sb.toString());
		
		return SUCCESS;
	}

}
