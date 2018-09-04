package com.hurong.credit.action.credit.thirdInterface;
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


import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;
import com.hurong.credit.service.credit.thirdInterface.WebBankCodeFudianService;
/**
 * 
 * @author 
 *
 */
public class WebBankCodeFudianAction extends BaseAction{
	@Resource
	private WebBankCodeFudianService webBankCodeFudianService;
	private WebBankCodeFudian webBankCodeFudian;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WebBankCodeFudian getWebBankCodeFudian() {
		return webBankCodeFudian;
	}

	public void setWebBankCodeFudian(WebBankCodeFudian webBankCodeFudian) {
		this.webBankCodeFudian = webBankCodeFudian;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<WebBankCodeFudian> list= webBankCodeFudianService.getAll(filter);
		
		Type type=new TypeToken<List<WebBankCodeFudian>>(){}.getType();
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
				webBankCodeFudianService.remove(new Long(id));
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
		WebBankCodeFudian webBankCodeFudian=webBankCodeFudianService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(webBankCodeFudian));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(webBankCodeFudian.getId()==null){
			webBankCodeFudianService.save(webBankCodeFudian);
		}else{
			WebBankCodeFudian orgWebBankCodeFudian=webBankCodeFudianService.get(webBankCodeFudian.getId());
			try{
				BeanUtil.copyNotNullProperties(orgWebBankCodeFudian, webBankCodeFudian);
				webBankCodeFudianService.save(orgWebBankCodeFudian);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
