package com.hurong.credit.action.financeProduct;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.util.DateUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.service.financeProduct.PlFinanceProductUserAccountInfoService;
/**
 * 
 * @author 
 *
 */
public class PlFinanceProductUserAccountInfoAction extends BaseAction{
	@Resource
	private PlFinanceProductUserAccountInfoService plFinanceProductUserAccountInfoService;
	private PlFinanceProductUserAccountInfo plFinanceProductUserAccountInfo;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlFinanceProductUserAccountInfo getPlFinanceProductUserAccountInfo() {
		return plFinanceProductUserAccountInfo;
	}

	public void setPlFinanceProductUserAccountInfo(PlFinanceProductUserAccountInfo plFinanceProductUserAccountInfo) {
		this.plFinanceProductUserAccountInfo = plFinanceProductUserAccountInfo;
	}

	/**
	 * 查询交易记录列表
	 */
	public String list(){
		PagingBean  pb=new PagingBean(start,limit);
		List<PlFinanceProductUserAccountInfo> list= plFinanceProductUserAccountInfoService.getListByParamet(this.getRequest(),pb);
		Type type=new TypeToken<List<PlFinanceProductUserAccountInfo>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:SS").create();
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
				plFinanceProductUserAccountInfoService.remove(new Long(id));
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
		PlFinanceProductUserAccountInfo plFinanceProductUserAccountInfo=plFinanceProductUserAccountInfoService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(plFinanceProductUserAccountInfo));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(plFinanceProductUserAccountInfo.getId()==null){
			plFinanceProductUserAccountInfoService.save(plFinanceProductUserAccountInfo);
		}else{
			PlFinanceProductUserAccountInfo orgPlFinanceProductUserAccountInfo=plFinanceProductUserAccountInfoService.get(plFinanceProductUserAccountInfo.getId());
			try{
				BeanUtil.copyNotNullProperties(orgPlFinanceProductUserAccountInfo, plFinanceProductUserAccountInfo);
				plFinanceProductUserAccountInfoService.save(orgPlFinanceProductUserAccountInfo);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	

	
}
