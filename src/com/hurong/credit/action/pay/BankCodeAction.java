package com.hurong.credit.action.pay;
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
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.StringUtil;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.model.pay.BankCode;
import com.hurong.credit.service.pay.BankCodeService;
/**
 * 
 * @author 
 *
 */
public class BankCodeAction extends BaseAction{
	@Resource
	private BankCodeService bankCodeService;
	private BankCode bankCode;
	private String ParentCode;//上一级
	private Map<String,String> map = new HashMap<String,String>();
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	private Long code;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public BankCode getBankCode() {
		return bankCode;
	}

	public void setBankCode(BankCode bankCode) {
		this.bankCode = bankCode;
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
		List<BankCode> list= bankCodeService.getAll(filter);
		
		Type type=new TypeToken<List<BankCode>>(){}.getType();
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
				bankCodeService.remove(new Long(id));
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
		BankCode bankCode=bankCodeService.get(code);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bankCode));
		sb.append("}");
		setJsonString(sb.toString());
		
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
		StringBuffer sb = new StringBuffer("");
		QueryFilter filter=new QueryFilter(getRequest());
		filter.getPagingBean().setPageSize(100);
		//根据不同的第三方支付选择不同的城市编码Q_thirdPayConfig_S_EQ
		String thirdPayConfig =configMap.get("thirdPayConfig").toString();
		filter.addFilter("Q_thirdPayConfig_S_EQ", thirdPayConfig);
		if(ParentCode!=null&&ParentCode.length()>0){
			filter.addFilter("Q_parentCode_S_EQ", ParentCode);
		}else{
			ParentCode = "0";
			filter.addFilter("Q_parentCode_S_EQ", ParentCode);
		}
		
		List<BankCode> list= bankCodeService.getAll(filter);
		
		//System.out.println("aaaaaaaaaaaa"+list.size());
		if(list!=null&&list.size()>0){
			
			for(BankCode mapentry:list){
				
				sb.append("<option value='").append(mapentry.getCode()).append("'>").append(mapentry.getName()).append("</option>");
			}
		}
		
		setJsonString(sb.toString());
		
		return SUCCESS;
	}

}
