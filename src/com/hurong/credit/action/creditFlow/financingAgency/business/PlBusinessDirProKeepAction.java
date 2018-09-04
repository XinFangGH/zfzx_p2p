package com.hurong.credit.action.creditFlow.financingAgency.business;

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

import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.PlBusinessDirProKeep;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessDirProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.BpBusinessOrProService;
import com.hurong.credit.service.creditFlow.financingAgency.business.PlBusinessDirProKeepService;

import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class PlBusinessDirProKeepAction extends BaseAction {
	@Resource
	private PlBusinessDirProKeepService plBusinessDirProKeepService;
	@Resource
	private BpBusinessDirProService bpBusinessDirProService;
	@Resource
	private BpBusinessOrProService bpBusinessOrProService;
	private PlBusinessDirProKeep plBusinessDirProKeep;

	private Long keepId;

	public Long getKeepId() {
		return keepId;
	}

	public void setKeepId(Long keepId) {
		this.keepId = keepId;
	}

	public PlBusinessDirProKeep getplBusinessDirProKeep() {
		return plBusinessDirProKeep;
	}

	public void setplBusinessDirProKeep(
			PlBusinessDirProKeep plBusinessDirProKeep) {
		this.plBusinessDirProKeep = plBusinessDirProKeep;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("sort", "updateDate");
		filter.addFilter("dir", QueryFilter.ORDER_DESC);
		List<PlBusinessDirProKeep> list = plBusinessDirProKeepService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("createDate",
				"updateDate");
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				plBusinessDirProKeepService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		PlBusinessDirProKeep plBusinessDirProKeep = plBusinessDirProKeepService
				.get(keepId);

		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				 "createDate",
				"updateDate");
		sb.append(serializer.exclude(new String[] { "class" }).serialize(
				plBusinessDirProKeep));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		plBusinessDirProKeep = setProPack(plBusinessDirProKeep);
		if (plBusinessDirProKeep.getKeepId() == null) {
			plBusinessDirProKeep.setCreateDate(new Date());
			plBusinessDirProKeep.setUpdateDate(new Date());

			plBusinessDirProKeepService.save(plBusinessDirProKeep);
			updateProState(plBusinessDirProKeep.getProType());
		} else {
			PlBusinessDirProKeep orgplBusinessDirProKeep = plBusinessDirProKeepService
					.get(plBusinessDirProKeep.getKeepId());
			try {
				BeanUtil.copyNotNullProperties(orgplBusinessDirProKeep,
						plBusinessDirProKeep);

				plBusinessDirProKeepService.save(orgplBusinessDirProKeep);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

		}
		setJsonString("{success:true}");
		return SUCCESS;

	}

	// 设置pack 保存和更新的值
	public PlBusinessDirProKeep setProPack(PlBusinessDirProKeep pack) {
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
		if (proType.equals("B_Dir")) {
			BpBusinessDirPro sl = bpBusinessDirProService
					.get(plBusinessDirProKeep.getBDirProId());
			sl.setKeepStat(1);
			bpBusinessDirProService.save(sl);
		} else if (proType.equals("B_Or")) {
			BpBusinessOrPro sl = bpBusinessOrProService
					.get(plBusinessDirProKeep.getBOrProId());
			sl.setKeepStat(1);
			bpBusinessOrProService.save(sl);
		}
	}
}
