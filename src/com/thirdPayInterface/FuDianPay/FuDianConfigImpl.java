package com.thirdPayInterface.FuDianPay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.thirdPayInterface.*;
import com.thirdPayInterface.Fuiou.FuiouInterfaceUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FuDianConfigImpl implements ThirdPayTypeInterface {
	static  BpFundIntentService bpFundIntentService = (BpFundIntentService) AppUtil.getBean("bpFundIntentService");
	static  PlBidInfoService plBidInfoService = (PlBidInfoService)AppUtil.getBean("plBidInfoService");
	/**
	 * 富滇银行
	 */
	@Override
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
		try{//3333333
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//个人开通第三方 
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户注册
//	    			commonResponse=FuDianInterfaceUtil.accountRefRegister(commonRequst);
	    			commonResponse=FuDianInterfaceUtil.accountRefRegisterNew(commonRequst);
	    		}else{//个人
	    			commonResponse=FuDianInterfaceUtil.accountRegister(commonRequst);
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BINDCARD)){//绑卡
	    		commonResponse = FuDianInterfaceUtil.bandCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACECARD)){//换卡 --更新接口文档后此接口已作废
	    		commonResponse = FuDianInterfaceUtil.bandCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARD)){//取消绑卡
	    		commonResponse = FuDianInterfaceUtil.cancelBandCard(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELCARDBIND)){//企业取消绑卡
                commonResponse = FuDianInterfaceUtil.cancelCardBind(commonRequst);
            }else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
				commonResponse = FuDianInterfaceUtil.changePhone(commonRequst);  
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		commonResponse = FuDianInterfaceUtil.recharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//提现
	    		commonResponse = FuDianInterfaceUtil.withdraw(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){//查询个人信息接口
	    		commonResponse = FuDianInterfaceUtil.queryUserInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYLOAN)){//查询标的账户信息接口
	    		commonResponse = FuDianInterfaceUtil.queryLoan(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYDEAL)){//查询订单状态
	    		commonResponse = FuDianInterfaceUtil.queryDealInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//投标
	    		commonResponse = FuDianInterfaceUtil.preAuthorization(commonRequst);
	    		commonResponse.setDealState("7");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//开启自动投标
	    		commonResponse = FuDianInterfaceUtil.authorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭自动投标
	    		commonResponse = FuDianInterfaceUtil.closeAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//手动还款
	    		commonResponse = FuDianInterfaceUtil.repayAmount(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//提前还款
	    		commonResponse = FuDianInterfaceUtil.repayAmount(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
				commonResponse = FuDianInterfaceUtil.allot(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CALLBACK)){//投资人回款
	    		commonResponse = FuDianInterfaceUtil.callbank(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_ACCOUNTQUERY)){//查询用户明细
	    		commonResponse = FuDianInterfaceUtil.queryUserDealInfo(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYLOANBALANCE)){//查询标的明细
	    		commonResponse = FuDianInterfaceUtil.queryLoanBalance(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEFILE)){//数据迁移接口
	    		commonResponse = FuDianInterfaceUtil.migration(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MODIFY)){//企业信息变更接口
				commonResponse = FuDianInterfaceUtil.modifyEnterpriseMessage(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//债权转让接口
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//开启还款授权
	    		commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//关闭授权
	    		commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//还款   作为原方法保留，新方法在上边
	    		List<CommonRequestInvestRecord> list = commonRequst.getRepaymemntList();
	    		CommonResponse response = new CommonResponse();
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		List<CommonResponse> list3 = new ArrayList();
	    		//计数器  判断是否已经还过款  如果已经还过  则不能再还
	    		Integer count =0;
	    		String requestNo1 = "";
	    		if(list.size()>0){
	    			for(CommonRequestInvestRecord record:list){
	    				requestNo1 = ContextUtil.createRuestNumber();
	    				//判断是否是转让后的债权
	    				QueryFilter filter1 = new QueryFilter();
	    				//逾期还款的罚息
	    				BigDecimal acturalMoney = BigDecimal.ZERO;
	    				filter1.addFilter("Q_newOrderNo_S_EQ", record.getBidRequestNo());
	    				filter1.addFilter("Q_plBidPlan.bidId_L_EQ", Long.valueOf(commonRequst.getBidId()));
	    				List<PlBidInfo> list2 = plBidInfoService.getAll(filter1);
 	    				if(list2.size()>0){//如果是转让后的债权则按照标的号查找
//		    				QueryFilter filter = new QueryFilter();
//		    	    		filter.addFilter("Q_bidPlanId_L_EQ", Long.valueOf(commonRequst.getBidId()));
//		    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
		    	    		//filter.addFilter("Q_isValid_SN_EQ", (short)0); 
//		    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
		    	    		
		    	    		List<BpFundIntent> list1 =bpFundIntentService.getIntentList(Long.valueOf(commonRequst.getBidId()), Integer.valueOf(commonRequst.getRemark1()));
		    	    		if(list1.size()>0){
			    	    		for(BpFundIntent intent : list1){
			    	    			//平台费用不还给投资人
			    	    			if(intent.getFundType()!=null&&!intent.getFundType().equals("serviceMoney")&&!intent.getFundType().equals("consultationMoney")&&intent.getFactDate()==null){
			    	    				intent.setRequestNo(requestNo1);
			    	    				bpFundIntentService.merge(intent);
			    	    				count++;
			    	    			}
			    	    		}
			    	    		
		    	    		}
	    				}else{//如果不是则按照流水号进行查找操作
		    				//list3 = new ArrayList();
		    				QueryFilter filter = new QueryFilter();
		    	    		filter.addFilter("Q_orderNo_S_EQ", record.getBidRequestNo());
		    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
		    	    		//filter.addFilter("Q_isValie_SN_EQ", (short)1);
		    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
		    	    		
		    	    		if(list1.size()>0){
			    	    		for(BpFundIntent intent : list1){
			    	    			//平台费用不还给投资人
			    	    			if(intent.getFundType()!=null&&!intent.getFundType().equals("serviceMoney")&&!intent.getFundType().equals("consultationMoney")&&intent.getFactDate()==null){
			    	    			//	acturalMoney = acturalMoney.add(intent.getAccrualMoney());
			    	    				intent.setRequestNo(requestNo1);
			    	    				bpFundIntentService.merge(intent);
			    	    				count++;
			    	    			}
			    	    		}
		    	    		}
	    				}
	    	    		CommonResponse commonResponse1 = new CommonResponse();
	    	    		CommonRequst requst = new CommonRequst();
	    	    		if(count>0){
	    	    			requst.setThirdPayConfigId(commonRequst.getThirdPayConfigId());//出账用户支付账号
	    	    			requst.setLoaner_thirdPayflagId(record.getInvest_thirdPayConfigId());//入账用户支付账号
	    	    			requst.setContractNo("");//预授权合同号
	    	    			requst.setRequsetNo(requestNo1);
    	    				requst.setAmount(record.getAmount().subtract(record.getFee()));
	    	    			commonResponse1 = FuiouInterfaceUtil.allot(requst);
	    	    			commonResponse1.setRequestNo(requestNo1);
	    	    			commonResponse1.setRemark(record.getAmount().subtract(record.getFee()).toString());
	    	    			list3.add(commonResponse1);
	    	    		}
	    			}
	    			commonRequst.setRequsetNo(oldRequestNo);
	    			response.setResponsecode(list3.get(0).getResponsecode());
	    			response.setResponseList(list3);
	    			commonResponse = response;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEBID)){
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
				commonResponse.setResponseMsg("操作成功");
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELBID)){
	    		commonResponse = FuiouInterfaceUtil.preAuthorizationCancel(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYUSER)){
	    		commonResponse = FuiouInterfaceUtil.queryUserInfo(commonRequst);
	    		commonResponse.setCustmemberStatus("已激活");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMUSER)){//理财计划购买(注册用户)
	    		commonResponse = FuiouInterfaceUtil.preAuthorization(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_COUPONAWARD)){//优惠券奖励
	    		commonRequst.setLoaner_thirdPayflagId(commonRequst.getThirdPayConfigId());
	    		commonRequst.setThirdPayConfigId("");//出账方第三方账号置空  默认平台付款
	    		commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BALANCEQUERY)){//用户余额查询接口
				commonResponse = FuiouInterfaceUtil.balanceQuery(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHANGEPASSAPP)){//用户密码修改重置免登陆接口(app版)
				commonResponse = FuiouInterfaceUtil.balanceQuery(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BUYDEAL)){//购买债权
				commonResponse = FuiouInterfaceUtil.allot(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_QUERYPLATF)){//充值取现查询
	    		if(commonRequst.getQueryType()!=null&&!"".equals(commonRequst.getQueryType())){
	    			if(commonRequst.getQueryType().equals("RECHARGE_RECORD")){
	    				commonRequst.setQueryType("PW11");//充值类型
	    			}else{
	    				commonRequst.setQueryType("PWTX");//提现类型
	    			}
	    		}
	    		commonResponse = FuiouInterfaceUtil.rechargeAndWithdraw(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHANGEPASS)){//用户更改密码
	    		commonResponse = FuiouInterfaceUtil.changeUserPass(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELUSER)){//用户注销接口(网页版)
	    		//commonResponse = FuiouInterfaceUtil.(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE_B)){//提前还款情况下的平台收费
	    		List<CommonRequestInvestRecord> list = commonRequst.getRepaymemntList();
	    		CommonResponse response = new CommonResponse();
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		List<CommonResponse> list3 = new ArrayList();
	    		if(list.size()>0){
	    			for(CommonRequestInvestRecord record:list){
//	    				list3 = new ArrayList();
	    				String requestNo1 = ContextUtil.createRuestNumber();
	    				QueryFilter filter = new QueryFilter();
	    				//查找当期的款项台账查找无效的即是生成的提前还款的款项
	    	    		filter.addFilter("Q_bidPlanId_L_EQ", Long.valueOf(commonRequst.getBidId()));
	    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
	    	    		filter.addFilter("Q_isCheck_SN_EQ",(short)1);
	    	    		filter.getPagingBean().setPageSize(1000000);
	    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
	    	    		for(BpFundIntent intent : list1){
	    	    			//平台费用不还给投资人
	    	    			if(intent.getFundType()!=null&&(intent.getFundType().equals("consultationMoney")||intent.getFundType().equals("serviceMoney"))&&intent.getFactDate()==null){
	    	    				intent.setRequestNo(requestNo1);
	    	    				bpFundIntentService.merge(intent);
	    	    			}
	    	    		}
	    			    CommonResponse commonResponse1 = new CommonResponse();
	    				CommonRequst requst = new CommonRequst();
    	    			requst.setThirdPayConfigId(commonRequst.getThirdPayConfigId());//出账用户支付账号
    	    			requst.setLoaner_thirdPayflagId("");//入账用户支付账号
    	    			requst.setContractNo("");//预授权合同号
    	    			requst.setRequsetNo(requestNo1);
    	    			requst.setAmount(record.getFee());
    	    			System.out.println("借款人还给平台的费用是"+record.getFee());
    	    			//如果费用是0则不做处理
    	    			if(record.getFee()==null||record.getFee().compareTo(new BigDecimal(0))==0){
    	    				commonResponse1.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);
    	    				commonResponse1.setRequestNo(requestNo1);
    	    			}else{
    	    				commonResponse1 = FuiouInterfaceUtil.transfers(requst);
    	    			}
    	    			commonResponse1.setRemark(record.getFee().toString());
    	    			commonResponse1.setRequestNo(requestNo1);
    	    			list3.add(commonResponse1);
	    			}
	    			commonRequst.setRequsetNo(oldRequestNo);
	    			response.setResponseList(list3);
	    			commonResponse = response;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PLATEFORMRECHAGE)){//平台收费
	    		List<CommonRequestInvestRecord> list = commonRequst.getRepaymemntList();
	    		CommonResponse response = new CommonResponse();
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		List<CommonResponse> list3 = new ArrayList();
	    		//计数器  判断是否已经还过款  如果已经还过  则不能再还
	    		Integer count =0;
	    		String requestNo1 = "";
	    		if(list.size()>0){
	    			for(CommonRequestInvestRecord record:list){
	    				requestNo1 = ContextUtil.createRuestNumber();
	    				//判断是否是转让后的债权
	    				QueryFilter filter1 = new QueryFilter();
	    				filter1.addFilter("Q_newOrderNo_S_EQ", record.getBidRequestNo());
	    				filter1.addFilter("Q_plBidPlan.bidId_L_EQ", Long.valueOf(commonRequst.getBidId()));
	    				filter1.getPagingBean().setPageSize(1000000);
	    				List<PlBidInfo> list2 = plBidInfoService.getAll(filter1);
	    	    		if(list2.size()>0){//如果是转让后的债权则按照标的号查找
		    				QueryFilter filter = new QueryFilter();
		    	    		filter.addFilter("Q_bidPlanId_L_EQ", Long.valueOf(commonRequst.getBidId()));
		    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
		    	    		//filter.addFilter("Q_isValid_SN_EQ", (short)0);
		    	    		filter.getPagingBean().setPageSize(1000000);
		    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
		    	    		if(list1.size()>0){
			    	    		for(BpFundIntent intent : list1){
			    	    			//平台费用不还给投资人
			    	    			if(intent.getFundType()!=null&&intent.getFundType().equals("serviceMoney")||intent.getFundType().equals("consultationMoney")&&intent.getFactDate()==null){
			    	    				intent.setRequestNo(requestNo1);
			    	    				bpFundIntentService.merge(intent);
			    	    				count++;
			    	    			}
			    	    		}
		    	    		}
	    				}else{//如果不是则按照流水号进行查找操作
		    				//list3 = new ArrayList();
		    				QueryFilter filter = new QueryFilter();
		    	    		filter.addFilter("Q_orderNo_S_EQ", record.getBidRequestNo());
		    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
		    	    		//filter.addFilter("Q_isValid_SN_EQ", (short)0);
		    	    		filter.getPagingBean().setPageSize(1000000);
		    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
		    	    		if(list1.size()>0){
			    	    		for(BpFundIntent intent : list1){
			    	    			//平台费用不还给投资人
			    	    			if(intent.getFundType()!=null&&(intent.getFundType().equals("serviceMoney")||intent.getFundType().equals("consultationMoney"))&&intent.getFactDate()==null){
			    	    			//	acctural = acctural.add(intent.getAccrualMoney());
			    	    				intent.setRequestNo(requestNo1);
			    	    				bpFundIntentService.merge(intent);
			    	    				count++;
			    	    			}
			    	    		}
		    	    		}
	    				}
	    	    		CommonResponse commonResponse1 = new CommonResponse();
	    	    		CommonRequst requst = new CommonRequst();
	    	    		if(count>0){
	    	    			requst.setThirdPayConfigId(commonRequst.getThirdPayConfigId());//出账用户支付账号
	    	    			requst.setLoaner_thirdPayflagId("");//入账用户支付账号
	    	    			requst.setContractNo("");//预授权合同号
	    	    			requst.setRequsetNo(requestNo1);
    	    				requst.setAmount(record.getFee());
	    	    			commonResponse1 = FuiouInterfaceUtil.transfers(requst);
	    	    			commonResponse1.setRequestNo(requestNo1);
	    	    			commonResponse1.setRemark(requst.getAmount().toString());
	    	    			list3.add(commonResponse1);
	    	    		}
	    			}
	    			commonRequst.setRequsetNo(oldRequestNo);
	    			response.setResponseList(list3);
	    			commonResponse = response;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BEFOREPAY)){//提前还款（本息+奖励）
	    		List<CommonRequestInvestRecord> list = commonRequst.getRepaymemntList();
	    		CommonResponse response = new CommonResponse();
	    		//业务方法中传过来的流水号，用关联请求第三方记录
	    		String oldRequestNo = commonRequst.getRequsetNo();
	    		List<CommonResponse> list3 = new ArrayList();
	    		if(list.size()>0){
	    			for(CommonRequestInvestRecord record:list){
	    				String requestNo1 = ContextUtil.createRuestNumber();
	    				QueryFilter filter = new QueryFilter();
	    	    		filter.addFilter("Q_bidPlanId_L_EQ", Long.valueOf(commonRequst.getBidId()));
	    	    		filter.addFilter("Q_payintentPeriod_N_EQ", Integer.valueOf(commonRequst.getRemark1()));
	    	    		filter.addFilter("Q_isCheck_SN_EQ",(short)1);
	    	    		filter.getPagingBean().setPageSize(1000000);
	    	    		List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
	    	    		for(BpFundIntent intent : list1){
	    	    			//平台费用不还给投资人
	    	    			if(intent.getFundType()!=null&&!intent.getFundType().equals("consultationMoney")&&!intent.getFundType().equals("serviceMoney")&&intent.getFactDate()==null){
	    	    				intent.setRequestNo(requestNo1);
	    	    				bpFundIntentService.merge(intent);
	    	    			}
	    	    		}
	    			    CommonResponse commonResponse1 = new CommonResponse();
	    				CommonRequst requst = new CommonRequst();
	    				requst.setThirdPayConfigId(commonRequst.getThirdPayConfigId());//出账用户支付账号
	    				requst.setLoaner_thirdPayflagId(record.getInvest_thirdPayConfigId());//入账用户支付账号
	    				requst.setContractNo("");//预授权合同号
	    				requst.setRequsetNo(requestNo1);
	    				requst.setAmount(record.getAmount().subtract(record.getFee()));
	    				commonResponse1 = FuiouInterfaceUtil.allot(requst);
	    				commonResponse1.setRequestNo(requestNo1);
	    				commonResponse1.setRemark(record.getAmount().subtract(record.getFee()).toString());
	    				list3.add(commonResponse1);
	    			}
	    			commonRequst.setRequsetNo(oldRequestNo);
	    			response.setResponseList(list3);
	    			commonResponse = response;
	    		}
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_MMLOANUSER)){//理财计划起息(注册用户)  
	    			String loanThirdPayConfigId=commonRequst.getThirdPayConfigId();
	    			commonRequst.setContractNo(commonRequst.getLoanNoList());
	    			commonRequst.setThirdPayConfigId(commonRequst.getInvest_thirdPayConfigId());
	    			commonRequst.setLoaner_thirdPayflagId(loanThirdPayConfigId);
					commonResponse = FuiouInterfaceUtil.allot(commonRequst);
			}else{
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_NOTBUSINESS);
		    	commonResponse.setResponseMsg("没有该业务类型");
	    	}
		}catch(Exception e){
			e.printStackTrace();
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
	    	commonResponse.setResponseMsg("系统报错");
		}
		return commonResponse;
	}

	@Override
	public CommonResponse businessReturn(Map maps,HttpServletRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			String reqData=maps.get("reqData").toString();
			System.out.println("返回的密文参数：" + reqData);
//			reqData = DecryptUtil.decryptByPrivateKey(reqData, CommonPro.getDemoCustomPrivateKey());
//			System.out.println("解密结果：" + reqData);
			JSONObject resObj = JSON.parseObject(reqData);
			String data = resObj.getString("data");
			String content = null;
			if (data != null) {
				content = JSON.parseObject(data).getString("content");
			} else {
				content = resObj.getString("content");
			}
			String requestNo = JSON.parseObject(content).getString("orderNo");//流水号
			commonResponse.setRequestNo(requestNo);
			
			
			String req = null; 
			if (data != null) {
				req = JSON.parseObject(data).getString("retCode");
			} else {
				req = resObj.getString("retCode");
			}
			String retMsg = null; 
			if (data != null) {
				retMsg = JSON.parseObject(data).getString("retMsg");
			} else {
				retMsg = resObj.getString("retMsg");
			}
			 if(req!=null&&(req.equals("0000"))){//成功标志   说明处理成功，并非结果  结果需要根据各自的状态分别判断
				//处理返回参数并封装
				commonResponse = deal(commonResponse, content);
				if (commonResponse.getActiveStatus().equals("1")) {//成功
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);//操作成功
				}else if(commonResponse.getActiveStatus().equals("2")) {//处理中
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_APPLAY);//操作成功
					commonResponse.setResponseMsg("申请成功，正在处理中。。。");
				}else {
					commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);//操作失败
					commonResponse.setResponseMsg("操作失败");
				}
	        }else{
	        	commonResponse.setResponseMsg(retMsg);
	        	commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);//操作失败
	        }
				commonResponse.setRequestInfo(req+""+reqData);//返回数据包
		} catch (Exception e) {
			e.printStackTrace();
			 commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);//操作失败
		}
		return commonResponse;
	}

	@Override
	public Object[] checkThirdType(String businessType) {
		Object[] typekey=new Object[2];
		typekey[0]="0";
		typekey[1]=null;
		return typekey;
	}
	
	/**
	 * 处理返回的参数
	 * @param commonResponse
	 * @param content
	 * @return
	 * 2017-8-29
	 * @tzw
	 */
	private CommonResponse deal(CommonResponse commonResponse,String content) {
		//业务处理结果  默认成功
		String state = "1";
		commonResponse.setActiveStatus(state);
		//如果成功一定会有这2个参数
		String accountNo = JSON.parseObject(content).getString("accountNo");//三方账号1
		String userName = JSON.parseObject(content).getString("userName");//三方账号2
		commonResponse.setThirdPayConfigId(accountNo);
		commonResponse.setThirdPayConfigId0(userName);
		
		//备注---传的是业务类型
		if (content.contains("extMark")) {
			String extMark = JSON.parseObject(content).getString("extMark");
			System.out.println("业务类型：" + extMark);

			//处理个人开户数据，20180518银行方再个人开户的同时绑卡，修改回调,20180820由于银行放开手机号码可编辑，所以需要另外接受一个手机号
			if (extMark.equals(ThirdPayConstants.BT_PREGISTER)) {
				System.out.println("处理实名认证返回信息 ");
				putregister(commonResponse, content, state);
			}

			//根据业务类型修改   企业开户没有特殊值返回，所以不单独做处理，---20180626行方修改接口，企业需要接返回值
			if (extMark.equals(ThirdPayConstants.BT_EREGISTER)) {
				System.out.println("企业认证返回信息 ");
				putregisterBus(commonResponse, content, state);
			}

			//处理绑卡.换卡数据  
			if (extMark.equals(ThirdPayConstants.BT_BINDCARD)||extMark.equals(ThirdPayConstants.BT_REPLACECARD)) {
				putBindCard(commonResponse, content, state);
			}
			
			//取消绑卡
			if (extMark.equals(ThirdPayConstants.BT_CANCELCARD)) {
					System.out.println("处理三方返回解绑信息");
					state = putCancelCard(commonResponse, content, state);
			}
			//更换手机号
			if (extMark.equals(ThirdPayConstants.BT_UPDATEPHONE)) {
				state = putChangePhone(commonResponse, content, state);
			}
			//充值
			if (extMark.equals(ThirdPayConstants.BT_RECHAGE)) {
				state = putRechage(commonResponse, content, state);
			}
			//取现
			if (extMark.equals(ThirdPayConstants.BT_WITHDRAW)) {
				state = putWithdraw(commonResponse, content, state);
			}
			
			//投标
			if (extMark.equals(ThirdPayConstants.BT_BID)) {
				bidPlan(commonResponse, content);
			}
			
			//授权
			if (extMark.equals(ThirdPayConstants.BT_OPENBIDAUTH)) {
				//授权状态  1：成功授权；2：取消授权
				if (content.contains("status")) {
					String status = JSON.parseObject(content).getString("status");
					if (status.equals("1") ) {
						state = "1";
					}else if(status.equals("2")) {
						state = "0";
					}
					//统一为系统状态，0不成功，1成功，2办理中
					commonResponse.setActiveStatus(state);
				}
			}

			//债权交易
			if (extMark.equals(ThirdPayConstants.BT_BUYDEAL)) {
				state = "1";
				//统一为系统状态，0不成功，1成功，2办理中
				commonResponse.setActiveStatus(state);
				creditors(commonResponse, content);
			}


			//变更企业信息
			if (extMark.equals(ThirdPayConstants.BT_MODIFY)) {
				modifyEnterprise(commonResponse, content);
			}
			
			//取消授权  与授权相反，返回取消授权则为成功
			if (extMark.equals(ThirdPayConstants.BT_CLOSEBIDAUTH)) {
				//授权状态  1：成功授权；2：取消授权
				if (content.contains("status")) {
					String status = JSON.parseObject(content).getString("status");
					if (status.equals("2") ) {
						state = "1";
					}else if(status.equals("1")) {
						state = "0";
					}
					//统一为系统状态，0不成功，1成功，2办理中
					commonResponse.setActiveStatus(state);
				}
			}
			
			
			
			
		}
		//订单日期
		if (content.contains("orderDate")) {
			String orderDate = JSON.parseObject(content).getString("orderDate");
			commonResponse.setOrderDate(orderDate);
		}
		//手机号
		if (content.contains("mobilePhone")) {
			String mobilePhone = JSON.parseObject(content).getString("mobilePhone");
			commonResponse.setNewMobile(mobilePhone);
		}
		//企业开户时审核状态  2 通过, 3 不通过
		if (content.contains("verifyStatus")) {
			String verifyStatus = JSON.parseObject(content).getString("verifyStatus");
			if (verifyStatus.equals("3")) {
				state = "0";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
		}
		return commonResponse;
	}

	private void creditors(CommonResponse commonResponse, String content) {
		//承接金额
		if (content.contains("amount")) {
            String amount = JSON.parseObject(content).getString("amount");
            commonResponse.setAmount(amount);
        }

		//认购本金
		if (content.contains("creditAmount")) {
            String creditAmount = JSON.parseObject(content).getString("creditAmount");
            commonResponse.setReceivedAmount(creditAmount);
        }

		//转让手续费
		if (content.contains("creditFee")) {
            String creditFee = JSON.parseObject(content).getString("creditFee");
            commonResponse.setFee(creditFee);
        }

		//转让手续费方式  1:转让人出2：承接人出
		if (content.contains("creditFeeType")) {
            String creditFeeType = JSON.parseObject(content).getString("creditFeeType");
            commonResponse.setModifyType(creditFeeType);
        }
	}

	/**
	 * 变更企业信息
	 * @param commonResponse
	 * @param content
	 */
	private void modifyEnterprise(CommonResponse commonResponse, String content) {
		//法人代表真实姓名
		if (content.contains("artificialRealName")) {
			String artificialRealName = JSON.parseObject(content).getString("artificialRealName");
			commonResponse.setTruename(artificialRealName);
		}
		//法人代表证件号
		if (content.contains("artificialIdentityCode")) {
			String artificialIdentityCode = JSON.parseObject(content).getString("artificialIdentityCode");
			commonResponse.setCardCode(artificialIdentityCode);
		}
		//企业名称
		if (content.contains("corpName")) {
			String corpName = JSON.parseObject(content).getString("corpName");
			commonResponse.setCorpName(corpName);
		}
		//联系人手机号
		if (content.contains("mobilePhone")) {
			String mobilePhone = JSON.parseObject(content).getString("mobilePhone");
			commonResponse.setNewMobile(mobilePhone);
		}
		//变更类型
		if (content.contains("modifyType")) {
			String modifyType = JSON.parseObject(content).getString("modifyType");
			commonResponse.setModifyType(modifyType);
		}
	}


	/**
	 * 处理投标成功接口，（三方只是将请求参数原样返回，有异步回调则说明成功）
	 * @param commonResponse
	 * @param content
	 * 2017-9-11
	 * @tzw
	 */
	private void bidPlan(CommonResponse commonResponse, String content) {
		//取现金额
		if (content.contains("amount")) {
			String amount = JSON.parseObject(content).getString("amount");
			commonResponse.setAmount(amount);
		}
		//标的号
		if (content.contains("loanTxNo")) {
			String loanTxNo = JSON.parseObject(content).getString("loanTxNo");
			commonResponse.setLoanNo(loanTxNo);
		}
		//奖励金额  单位：元 用于平台的红包奖励，由p2p平台支出,无则传0   目前没有用处
		if (content.contains("award")) {
			String award = JSON.parseObject(content).getString("award");
			commonResponse.setFee(award);
		}
	}

	/**
	 * 处理取现接口返回参数 
	 * @param commonResponse
	 * @param content
	 * @param state
	 * @return
	 * 2017-8-31
	 * @tzw
	 */
	private String putWithdraw(CommonResponse commonResponse, String content,
			String state) {
		//取现状态  0未处理 ;1提现成功（已扣款）；2提现失败；3银行退单成功；4提现申请成功（冻结资金）
		if (content.contains("status")) {
			String status = JSON.parseObject(content).getString("status");
			if (status.equals("2") || status.equals("3")) {
				state = "0";
			}else if(status.equals("0") || status.equals("4")) {
				state = "2";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
		}
		//取现金额
		if (content.contains("amount")) {
			String amount = JSON.parseObject(content).getString("amount");
			commonResponse.setAmount(amount);
		}
		//实际到账金额
		if (content.contains("receivedAmount")) {
			String receivedAmount = JSON.parseObject(content).getString("receivedAmount");
			commonResponse.setReceivedAmount(receivedAmount);
		}
		//手续费
		if (content.contains("fee")) {
			String fee = JSON.parseObject(content).getString("fee");
			commonResponse.setFee(fee);
		}

		//提现银行卡号
		if (content.contains("bankCardNo")) {
			String bankCardNo = JSON.parseObject(content).getString("bankCardNo");
			commonResponse.setBankCardNumber(bankCardNo);
		}
		
		//银行编码
		if (content.contains("bankCode")) {
			String bankCode = JSON.parseObject(content).getString("bankCode");
			commonResponse.setBankCode(bankCode);
		}
		
		//提现银行名称
		if (content.contains("bankName")) {
			String bankName = JSON.parseObject(content).getString("bankName");
			commonResponse.setBankName(bankName);
		}
		return state;
	}
	
	
	
	/**
	 * 处理充值返回数据
	 * @param commonResponse
	 * @param content
	 * @param state
	 * @return
	 * 2017-8-31
	 * @tzw
	 */
	private String putRechage(CommonResponse commonResponse, String content,
			String state) {
		//充值状态   1：充值成功  2：充值失败  4：银行处理中
		if (content.contains("status")) {
			String status = JSON.parseObject(content).getString("status");
			if (status.equals("2")) {
				state = "0";
			}else if(status.equals("0") || status.equals("3")) {
				state = "2";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
		}
		//充值金额
		if (content.contains("amount")) {
			String amount = JSON.parseObject(content).getString("amount");
			commonResponse.setAmount(amount);
		}
		//实际到账金额
		if (content.contains("receivedAmount")) {
			String receivedAmount = JSON.parseObject(content).getString("receivedAmount");
			commonResponse.setReceivedAmount(receivedAmount);
		}
		//手续费
		if (content.contains("fee")) {
			String fee = JSON.parseObject(content).getString("fee");
			commonResponse.setFee(fee);
		}
		return state;
	}

	/**
	 * 处理更换手机号业务数据
	 * @param commonResponse
	 * @param content
	 * @param state
	 * @return
	 * 2017-8-31
	 * @tzw
	 */
	private String putChangePhone(CommonResponse commonResponse,
			String content, String state) {
		//更换手机号状态   1代表更新手机号成功   2代表更新手机号失败\人工申请失败   3代表用户人工申请成功
		if (content.contains("status")) {
			String status = JSON.parseObject(content).getString("status");
			if (status.equals("2")) {
				state = "0";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
		}
		
		//手机号
		if (content.contains("newPhone")) {
			String newPhone = JSON.parseObject(content).getString("newPhone");
			commonResponse.setNewMobile(newPhone);
		}
		//申请协议号   预留字段，暂时不用
		/*if (content.contains("protocolNo")) {
			String protocolNo = JSON.parseObject(content).getString("protocolNo");
			//commonResponse.setNewMobile(newPhone);
		}*/
		return state;
	}


	/**
	 * 处理注册成功绑卡信息
	 * @param commonResponse
	 * @param content
	 * @param state
	 * 2017-8-31
	 * @tzw
	 */
	private void putregisterBus(CommonResponse commonResponse, String content,
							 String state) {

		//审批状态      1：待审核；2：通过；3：不通过
		if (content.contains("verifyStatus")) {
			String status = JSON.parseObject(content).getString("verifyStatus");
			if (status.equals("2") ) {
				state = "1";
			}else if(status.equals("1")) {
				state = "2";
			}else {
				state = "0";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
			System.out.println("绑卡状态："+commonResponse.getActiveStatus());
		}else {
			state = "2";
			commonResponse.setActiveStatus(state);
		}


		//法人证件号码
		if (content.contains("artificialIdentityCode")) {
			String artificialIdentityCode = JSON.parseObject(content).getString("artificialIdentityCode");
			commonResponse.setArtificialIdentityCode(artificialIdentityCode);
		}
		//法人代表真实姓名
		if (content.contains("artificialRealName")) {
			String artificialRealName = JSON.parseObject(content).getString("artificialRealName");
			commonResponse.setArtificialRealName(artificialRealName);
		}

		//企业名称
		if (content.contains("corpName")) {
			String corpName = JSON.parseObject(content).getString("corpName");
			commonResponse.setTruename(corpName);
		}

		//公司类型  1：借款企业；2：担保公司 3 出借企业
		if (content.contains("corpType")) {
			String corpType = JSON.parseObject(content).getString("corpType");
			commonResponse.setRoleType(corpType);
		}

		//统一社会信用代码
		if (content.contains("creditCode")) {
			String creditCode = JSON.parseObject(content).getString("creditCode");
			commonResponse.setCardCode(creditCode);
		}

		//营业执照编号
		if (content.contains("licenceCode")) {
			String licenceCode = JSON.parseObject(content).getString("licenceCode");
			commonResponse.setLicenceCode(licenceCode);
		}

		//法人手机号
		if (content.contains("mobilePhone")) {
			String mobilePhone = JSON.parseObject(content).getString("mobilePhone");
			commonResponse.setMobilePhone(mobilePhone);
		}

		//组织机构代码
		if (content.contains("orgCode")) {
			String orgCode = JSON.parseObject(content).getString("orgCode");
			commonResponse.setOrgCode(orgCode);
		}

		//税务登记号
		if (content.contains("taxRegCode")) {
			String taxRegCode = JSON.parseObject(content).getString("taxRegCode");
			commonResponse.setTaxRegCode(taxRegCode);
		}

		//是否三证合一         0否;1是
		if (content.contains("threeCertUnit")) {
			String threeCertUnit = JSON.parseObject(content).getString("threeCertUnit");
			commonResponse.setThreeCertUnit(threeCertUnit);
		}


		//绑卡银行
		if (content.contains("bank")) {
			String bank = JSON.parseObject(content).getString("bank");
			commonResponse.setBankName(bank);
			System.out.println("银行："+bank);
		}

		//绑定银行卡号    绑定的银行卡号，可用于提现
		if (content.contains("bankCardNo")) {
			String bankAccountNo = JSON.parseObject(content).getString("bankCardNo");
			commonResponse.setBankCode(bankAccountNo);
			System.out.println("银行卡号："+bankAccountNo);
		}

		//银行对应编码
		if (content.contains("bankCode")) {
			String bankCode = JSON.parseObject(content).getString("bankCode");
			commonResponse.setBankCardNumber(bankCode);
			System.out.println("银行编码："+bankCode);
		}
	}


	/**
	 * 处理注册成功绑卡信息
	 * @param commonResponse
	 * @param content
	 * @param state
	 * 2017-8-31
	 * @tzw
	 */
	private void putregister(CommonResponse commonResponse, String content,
							 String state) {
		//用户类型
		if (content.contains("roleType")) {
			String roleType = JSON.parseObject(content).getString("roleType");
			commonResponse.setRoleType(roleType);
		}

		//绑卡银行
		if (content.contains("bank")) {
			String bank = JSON.parseObject(content).getString("bank");
			commonResponse.setBankName(bank);
			System.out.println("银行："+bank);
		}

		//绑定银行卡号    绑定的银行卡号，可用于提现
		if (content.contains("bankCardNo")) {
			String bankAccountNo = JSON.parseObject(content).getString("bankCardNo");
			commonResponse.setBankCode(bankAccountNo);
			System.out.println("银行卡号："+bankAccountNo);
		}

		//银行对应编码
		if (content.contains("bankCode")) {
			String bankCode = JSON.parseObject(content).getString("bankCode");
			commonResponse.setBankCardNumber(bankCode);
			System.out.println("银行编码："+bankCode);
		}

		//手机号码
		if (content.contains("mobilePhone")) {
			String mobilePhone = JSON.parseObject(content).getString("mobilePhone");
			commonResponse.setMobilePhone(mobilePhone);
			System.out.println("手机号码："+mobilePhone);
		}
	}
	
	/**
	 * 处理绑卡.换卡返回数据
	 * @param commonResponse
	 * @param content
	 * @param state
	 * 2017-8-31
	 * @tzw
	 */
	private void putBindCard(CommonResponse commonResponse, String content,
			String state) {
		//绑卡状态      0-绑卡中，1-绑卡成功，2-绑卡失败 3-鉴权不通过
		if (content.contains("status")) {
			String status = JSON.parseObject(content).getString("status");
			if (status.equals("2") || status.equals("3")) {
				state = "0";
			}else if(status.equals("0")) {
				state = "2";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
			System.out.println("绑卡状态："+commonResponse.getActiveStatus());
		}
		
		
		//绑卡银行
		if (content.contains("bank")) {
			String bank = JSON.parseObject(content).getString("bank");
			commonResponse.setBankName(bank);
		}
		
		//绑定银行卡号    绑定的银行卡号，可用于提现
		if (content.contains("bankAccountNo")) {
			String bankAccountNo = JSON.parseObject(content).getString("bankAccountNo");
			commonResponse.setBankCode(bankAccountNo);
			System.out.println("卡号："+commonResponse.getBankCode());
		}
		
		//银行对应编码
		if (content.contains("bankCode")) {
			String bankCode = JSON.parseObject(content).getString("bankCode");
			commonResponse.setBankCardNumber(bankCode);
		}
	}
	
	/**
	 * 处理取消绑卡的业务
	 * @param commonResponse
	 * @param content
	 * @param state
	 * 2017-11-6
	 * @tzw
	 */
	private String putCancelCard(CommonResponse commonResponse, String content,
			String state) {
		//取消银行卡状态 1-解绑失败， 4-解绑成功
		if (content.contains("status")) {
			String status = JSON.parseObject(content).getString("status");
			if (status.equals("1")) {
				state = "0";
			}else if(status.equals("4")) {
				state = "1";
			}
			//统一为系统状态，0不成功，1成功，2办理中
			commonResponse.setActiveStatus(state);
		}
		
		//绑卡银行
		if (content.contains("bank")) {
			String bank = JSON.parseObject(content).getString("bank");
			commonResponse.setBankName(bank);
		}
		
		//绑定银行卡号    绑定的银行卡号，可用于提现
		if (content.contains("bankAccountNo")) {
			String bankAccountNo = JSON.parseObject(content).getString("bankAccountNo");
			commonResponse.setBankCode(bankAccountNo);
		}
		
		//银行对应编码
		if (content.contains("bankCode")) {
			String bankCode = JSON.parseObject(content).getString("bankCode");
			commonResponse.setBankCardNumber(bankCode);
		}
		System.out.println("解绑卡状态"+state);
		return state;
		
	}
	
	
	
	

}
