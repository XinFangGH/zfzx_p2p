package com.thirdPayInterface.Fuiou;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.thirdPayInterface.CommonRequestInvestRecord;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayTypeInterface;
import com.thirdPayInterface.ThirdPayLog.service.ThirdPayRecordService;

public class FuiouConfigImpl implements ThirdPayTypeInterface {
	static  BpFundIntentService bpFundIntentService = (BpFundIntentService) AppUtil.getBean("bpFundIntentService");
	static  PlBidInfoService plBidInfoService = (PlBidInfoService)AppUtil.getBean("plBidInfoService");
	/**
	 * 富友
	 */
	@Override
	
	public CommonResponse businessHandle(CommonRequst commonRequst) {
		CommonResponse commonResponse=new CommonResponse();
		try{
			if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_PREGISTER)||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_EREGISTER)){//个人开通第三方 
	    		if(commonRequst.getAccountType()!=null&&commonRequst.getAccountType()==1){//企业用户注册
	    			commonResponse=FuiouInterfaceUtil.accountRefRegister(commonRequst);
	    		}else{
	    			commonResponse=FuiouInterfaceUtil.accountRegister(commonRequst);
	    		}
	    	}/*else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}*/else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_WITHDRAW)){//提现
	    		commonResponse = FuiouInterfaceUtil.p2pFreeWithdrawDeposit(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENPAYAUTH)){//开启还款授权
	    		commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEPAYAUTH)){//
	    		commonResponse.setReturnType(CommonResponse.RETURNTYPE_JOSN);
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_APPWITHDRAW)){//用户app免登陆提现
	    		commonResponse = FuiouInterfaceUtil.appWithdraw(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_RECHAGE)){//充值
	    		commonResponse = FuiouInterfaceUtil.recharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BID)){//投标
	    		commonResponse = FuiouInterfaceUtil.preAuthorization(commonRequst);
	    		commonResponse.setDealState("7");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPLACEMONEY)){//代偿还款
	    		commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_APPQRECHARGE)){//APP免登陆快捷充值 
	    		commonResponse = FuiouInterfaceUtil.appQRecharge(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_SEDRED)){//派发红包
				commonRequst.setLoaner_thirdPayflagId(commonRequst.getThirdPayConfigId());
				commonRequst.setThirdPayConfigId("");//付款人置空默认平台付款
				commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBIN)
	    			||commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HRBOUT)){//互融宝转入 BT_HRBOUT
	    		if(commonRequst.getBussinessType().equals(ThirdPayConstants. BT_HRBOUT)){
	    			commonRequst.setThirdPayConfigId(""); 
	    		}
	    		commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_REPAYMENT)){//还款
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
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_OPENBIDAUTH)){//开启自动投标
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CLOSEBIDAUTH)){//关闭自动投标
	    		commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
	    		commonResponse.setResponseMsg("操作成功");
	    	}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_BALANCEQUERY)){//用户余额查询接口
				commonResponse = FuiouInterfaceUtil.balanceQuery(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CHANGEPASSAPP)){//用户密码修改重置免登陆接口(app版)
				commonResponse = FuiouInterfaceUtil.balanceQuery(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_UPDATEPHONE)){//修改手机号
				commonResponse = FuiouInterfaceUtil.changePhone(commonRequst);  
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_HANGDEAL)){//债权转让接口  
				commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
			}else if(commonRequst.getBussinessType().equals(ThirdPayConstants.BT_CANCELDEAL)){//取消挂牌
				commonRequst.setLoaner_thirdPayflagId(commonRequst.getThirdPayConfigId());
				commonRequst.setThirdPayConfigId("");
	    		commonResponse = FuiouInterfaceUtil.transfers(commonRequst);
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
		ThirdPayRecordService thirdPayRecordService = (ThirdPayRecordService) AppUtil.getBean("thirdPayRecordService");
		CommonResponse commonResponse = new CommonResponse();
		String req=maps.get("resp_code").toString();
		String sign=maps.get("signature").toString();
		String newMobile = "";
		if(maps.get("new_mobile")!=null){
			 newMobile = maps.get("new_mobile").toString();
			 commonResponse.setNewMobile(newMobile);
		}
        String plateFormNo = maps.get("mchnt_cd").toString();//商户账号
        String requestNo = maps.get("mchnt_txn_ssn").toString();//流水号
        String capAcntNo = "";
        if(maps.get("capAcntNo")!=null){
        	capAcntNo = maps.get("capAcntNo").toString();//银行账户
        }
        String thirdPayConfigId = "";
        if(maps.get("mobile_no")!=null){
        	thirdPayConfigId = maps.get("mobile_no").toString();//手机号作为第三方账号
        }
        if(maps.get("contract_no")!=null){
        	commonResponse.setContract_no(maps.get("contract_no").toString());
        }
        
        commonResponse.setRequestNo(requestNo);
        if(req!=null&&(req.equals("0000")||req.equals("5343"))){
        	if(capAcntNo!=null&&!"".equals(capAcntNo)){
        		commonResponse.setBankCardNumber(capAcntNo);//银行卡号码
        	}
        	commonResponse.setNewMobile(newMobile);
        	if(!"".equals(thirdPayConfigId)){
        		commonResponse.setThirdPayConfigId(thirdPayConfigId);
        	}
            commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);//操作成功
        }else{
        	if(maps.containsKey("resp_desc")){
        		commonResponse.setResponseMsg(maps.get("resp_desc").toString());
        	}else{
        		commonResponse.setResponseMsg("数据校验失败");
        	}
        	 commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);//操作失败
        }
        commonResponse.setRequestNo(requestNo);
		/*FuiouReponse response=null;
		try {
			response = YeePaySecretKeyUtil.JAXBunmarshal(req,FuiouReponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Boolean isSign=YeePaySecretKeyUtil.verifySign(req, sign);*/
		/*if(isSign){
			if(response.getCode().equals("1")){
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
			}else{
				commonResponse.setResponsecode(CommonResponse.RESPONSECODE_FAILD);
			}
			commonResponse.setResponseMsg(response.getDescription());//返回说明
		}else{
			commonResponse.setResponsecode(CommonResponse.RESPONSECODE_ISNOTPASSSIGN);
			commonResponse.setResponseMsg("签名验证失败");//返回说明
		}
*///		commonResponse.setRequestNo(response.getRequestNo());//流水号
	//	commonResponse.setBussinessType(response.getService());//业务类型
		commonResponse.setRequestInfo(req+""+sign);//返回数据包
//		return commonResponse;
		return commonResponse;
	}

	@Override
	public Object[] checkThirdType(String businessType) {
		// TODO Auto-generated method stub
		return null;
	}


}
