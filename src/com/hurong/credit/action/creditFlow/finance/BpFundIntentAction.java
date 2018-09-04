package com.hurong.credit.action.creditFlow.finance;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.parser.JSONParser;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.FundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.customer.InvestPersonInfoService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class BpFundIntentAction extends BaseAction {

	
	@Resource
	private FundIntentService fundIntentService;

	@Resource
	private BpFundIntentService bpFundIntentService;

	@Resource
	private InvestPersonInfoService investPersonInfoService;
	@Resource
	private PlBidPlanService plBidPlanService;
	

	private String preceptId;
	private Long bidPlanId;
	
	public String list(){

		QueryFilter filter = new QueryFilter(getRequest());
		List<BpFundIntent> list = bpFundIntentService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer("bidTime");
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}


	public String getPreceptId() {
		return preceptId;
	}


	public void setPreceptId(String preceptId) {
		this.preceptId = preceptId;
	}


	public Long getBidPlanId() {
		return bidPlanId;
	}


	public void setBidPlanId(Long bidPlanId) {
		this.bidPlanId = bidPlanId;
	}
	
}
