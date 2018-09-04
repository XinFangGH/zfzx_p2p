package com.hurong.credit.action.p2p.redMoney;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.p2p.redMoney.BpCustRedEnvelope;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.p2p.redMoney.BpCustRedEnvelopeService;
import com.hurong.credit.service.p2p.redMoney.BpCustRedMemberService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
/**
 * 
 * @author 
 *
 */
public class BpCustRedEnvelopeAction extends BaseAction{
	@Resource
	private BpCustRedEnvelopeService bpCustRedEnvelopeService;
	@Resource
	private BpCustRedMemberService bpCustRedMemberService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	private BpCustRedEnvelope bpCustRedEnvelope;
	private String reddatas;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	private Long redId;
	public Long getRedId() {
		return redId;
	}

	public String getReddatas() {
		return reddatas;
	}

	public void setReddatas(String reddatas) {
		this.reddatas = reddatas;
	}


	public void setRedId(Long redId) {
		this.redId = redId;
	}

	public BpCustRedEnvelope getBpCustRedEnvelope() {
		return bpCustRedEnvelope;
	}

	public void setBpCustRedEnvelope(BpCustRedEnvelope bpCustRedEnvelope) {
		this.bpCustRedEnvelope = bpCustRedEnvelope;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BpCustRedEnvelope> list= bpCustRedEnvelopeService.getAll(filter);
	
		
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
        JSONSerializer json = JsonUtil.getJSONSerializer("distributeTime","createTime");
        json.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new String[] {"distributeTime","createTime"});
		buff.append(json.serialize(list));
		buff.append("}");
		jsonString = buff.toString();
		
		return SUCCESS;
	}


}
