package com.hurong.credit.action.creditFlow.financingAgency.persion;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.Date;
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


import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.PlPersionDirProKeep;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.BpPersionOrProService;
import com.hurong.credit.service.creditFlow.financingAgency.persion.PlPersionDirProKeepService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class PlPersionDirProKeepAction extends BaseAction{
	@Resource
	private PlPersionDirProKeepService plPersionDirProKeepService;
	@Resource
	private BpPersionDirProService bpPersionDirProService;
	@Resource
	private BpPersionOrProService bpPersionOrProService;
	private PlPersionDirProKeep plPersionDirProKeep;
	
	private Long keepId;

	public Long getKeepId() {
		return keepId;
	}

	public void setKeepId(Long keepId) {
		this.keepId = keepId;
	}

	public PlPersionDirProKeep getPlPersionDirProKeep() {
		return plPersionDirProKeep;
	}

	public void setPlPersionDirProKeep(PlPersionDirProKeep plPersionDirProKeep) {
		this.plPersionDirProKeep = plPersionDirProKeep;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<PlPersionDirProKeep> list= plPersionDirProKeepService.getAll(filter);
		

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("createDate",
				"updateDate");
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
				plPersionDirProKeepService.remove(new Long(id));
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
		PlPersionDirProKeep plPersionDirProKeep=plPersionDirProKeepService.get(keepId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer( "createDate",
				"updateDate") ;
		sb.append(serializer.exclude(new String[] { "class" }).serialize(
				plPersionDirProKeep));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		plPersionDirProKeep = setProPack(plPersionDirProKeep);
		if(plPersionDirProKeep.getKeepId()==null){
			plPersionDirProKeep.setCreateDate(new Date());
			plPersionDirProKeep.setUpdateDate(new Date());
			plPersionDirProKeepService.save(plPersionDirProKeep);
			updateProState(plPersionDirProKeep.getProType());
		}else{
			PlPersionDirProKeep orgPlPersionDirProKeep=plPersionDirProKeepService.get(plPersionDirProKeep.getKeepId());
			try{
				BeanUtil.copyNotNullProperties(orgPlPersionDirProKeep, plPersionDirProKeep);
				plPersionDirProKeepService.save(orgPlPersionDirProKeep);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	// 设置pack 保存和更新的值
	public PlPersionDirProKeep setProPack(PlPersionDirProKeep pack) {
		Long proTypeId = Long.valueOf(this.getRequest().getParameter(
				"proTypeId"));
		Long proLevelId = Long.valueOf(this.getRequest().getParameter(
				"proLevelId"));
		// 抵质押 展示字符串
		String llimitsIds = this.getRequest().getParameter("llimitsIds");
		// 保证担保 展示字符串
		String levelIds = this.getRequest().getParameter("levelIds");
		// 贷款材料清单 展示字符串
		String materialsIds = this.getRequest().getParameter("materialsIds");
		if (materialsIds.length() > 0) {
			pack.setProLoanMaterShow(materialsIds.substring(0, materialsIds
					.length() - 1));
		} else {
			pack.setProLoanMaterShow(materialsIds);
		}
		if (levelIds.length() > 0) {
			pack.setProLoanLevelShow(levelIds.substring(0,
					levelIds.length() - 1));
		} else {
			pack.setProLoanLevelShow(levelIds);
		}
		if (llimitsIds.length() > 0) {
			pack.setProLoanLlimitsShow(llimitsIds.substring(0, llimitsIds
					.length() - 1));
		} else {
			pack.setProLoanLlimitsShow(llimitsIds);
		}
		pack.setCreditLevelId(proLevelId);
		pack.setTypeId(proTypeId);

		return pack;
	}

	// 更新贷款项目状态为已经打包项目 设置为1
	public void updateProState(String proType) {
		// 更新贷款项目状态为已经打包项目 设置为1
		if (proType.equals("P_Dir")) {
			BpPersionDirPro sl = bpPersionDirProService
					.get(plPersionDirProKeep.getPDirProId());
			sl.setKeepStat(1);
			bpPersionDirProService.save(sl);
		} else if (proType.equals("P_Or")) {
			BpPersionOrPro sl = bpPersionOrProService
					.get(plPersionDirProKeep.getPOrProId());
			sl.setKeepStat(1);
			bpPersionOrProService.save(sl);
		}
	}
}
