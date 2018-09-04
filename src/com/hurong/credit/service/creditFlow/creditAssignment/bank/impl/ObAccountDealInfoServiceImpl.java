package com.hurong.credit.service.creditFlow.creditAssignment.bank.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.ContextUtil;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObAccountDealInfoDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;

/**
 * 
 * @author 
 *
 */

public class ObAccountDealInfoServiceImpl extends BaseServiceImpl<ObAccountDealInfo> implements ObAccountDealInfoService{
	@SuppressWarnings("unused")
	private ObAccountDealInfoDao dao;
	@Resource
	private ObSystemAccountService obSystemAccountService;

	public ObAccountDealInfoServiceImpl(ObAccountDealInfoDao dao) {
		super(dao);
		this.dao = dao;
	}


	/**
	 * 操作系统账户调用方法
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("investPersonId",Long);//投资人Id
	 * map.put("investPersonType",Short);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
	 * map.put("transferType",Short);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
	 * map.put("money",BigDecimal);//交易金额
	 * map.put("dealDirection",short);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
	 * map.put("dealType",Short);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
	 * map.put("recordNumber",String);//交易流水号
	 * map.put("dealStatus",short);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
	 * @return String[0]:操作代码  Constants.CODE_SUCCESS =8888账户操作成功，Constants.CODE_FAILED=0000  账户操作失败
	 *         String[1]:操作信息   操作成功 ，操作失败
	 *  add by liny 2014-5-6   
	 *  update by linyan  2014-10-10
	 */
	@Override
	public String[] operateAcountInfo(Map<String,Object> map) {
		
		String[] str =new String[2];
		String ret="";
		String msg="";
		try{
			ObSystemAccount account  =obSystemAccountService.getByInvrstPersonIdAndType(Long.valueOf(map.get("investPersonId").toString()), (Short)map.get("investPersonType"));
			if(account!=null){
				//初始化交易参数开始
				ObAccountDealInfo obAccountDealInfo =new ObAccountDealInfo();
				obAccountDealInfo.setAccountId(account.getId());
				obAccountDealInfo.setInvestPersonName(account.getAccountName());
				obAccountDealInfo.setInvestPersonId(account.getInvestmentPersonId());
				obAccountDealInfo.setInvestPersonType((Short)map.get("investPersonType"));
				obAccountDealInfo.setDealType((Short)map.get("dealType"));
				obAccountDealInfo.setTransferType(map.get("transferType").toString());
				obAccountDealInfo.setCreateDate(new Date());
				obAccountDealInfo.setRecordNumber(map.get("recordNumber").toString());
				obAccountDealInfo.setDealRecordStatus(Short.valueOf(map.get("dealStatus").toString()));
				Short dealDirection=(Short)map.get("dealDirection");
				if(dealDirection.compareTo(ObAccountDealInfo.DIRECTION_INCOME)==0){
					obAccountDealInfo.setIncomMoney(new BigDecimal(map.get("money").toString()).setScale(2));
				}else {
					obAccountDealInfo.setPayMoney(new BigDecimal(map.get("money").toString()).setScale(2));
				}
				if(map.containsKey("bankId")){
					obAccountDealInfo.setBankId(Long.valueOf(map.get("bankId").toString()));
				}
				this.dao.save(obAccountDealInfo);
				ret=Constants.CODE_SUCCESS;
				msg="操作记录：操作成功!";
				if(map.get("dealStatus").toString().equals(ObAccountDealInfo.DEAL_STATUS_2.toString())){
					map.put("DealInfoId",obAccountDealInfo.getId());
					String[] returnMsg=this.updateAcountInfo(map);
					if(null!=returnMsg){
						ret=returnMsg[0];
						msg=returnMsg[1];
					}else{
						ret=Constants.CODE_FAILED;
						msg="操作记录：系统异常，请联系管理员!";
					}
				}
			}else{
				ret=Constants.CODE_FAILED;
				msg="操作记录：系统账户不存在!";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret=Constants.CODE_FAILED;
			msg="操作记录：系统异常，请联系管理员!";
		}
		
		
		str[0]=ret;
		str[1]=msg;
		return str;
	}


	/**
	 * 操作系统账户调用方法
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("investPersonId",Long);//投资人Id
	 * map.put("investPersonType",Short);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
	 * map.put("transferType",Short);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
	 * map.put("money",BigDecimal);//交易金额
	 * map.put("dealDirection",short);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
	 * map.put("dealType",Short);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
	 * map.put("recordNumber",String);//交易流水号
	 * map.put("dealStatus",short);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
	 * @return String[0]:操作代码  Constants.CODE_SUCCESS =8888账户操作成功，Constants.CODE_FAILED=0000  账户操作失败
	 *         String[1]:操作信息   操作成功 ，操作失败
	 */
	@Override
	public String[] operateDealInfo(Map<String,Object> map) {

		String[] str =new String[2];
		String ret="";
		String msg="";
		try{
			ObSystemAccount account  =obSystemAccountService.getByInvrstPersonIdAndType(Long.valueOf(map.get("investPersonId").toString()), (Short)map.get("investPersonType"));
			if(account!=null){
				//初始化交易参数开始
				ObAccountDealInfo obAccountDealInfo =new ObAccountDealInfo();
				obAccountDealInfo.setAccountId(account.getId());
				obAccountDealInfo.setInvestPersonName(account.getAccountName());
				obAccountDealInfo.setInvestPersonId(account.getInvestmentPersonId());
				obAccountDealInfo.setInvestPersonType((Short)map.get("investPersonType"));
				obAccountDealInfo.setDealType((Short)map.get("dealType"));
				obAccountDealInfo.setTransferType(map.get("transferType").toString());
				obAccountDealInfo.setCreateDate(new Date());
				obAccountDealInfo.setRecordNumber(map.get("recordNumber").toString());
				obAccountDealInfo.setDealRecordStatus(Short.valueOf(map.get("dealStatus").toString()));
				Short dealDirection=(Short)map.get("dealDirection");
				if(dealDirection.compareTo(ObAccountDealInfo.DIRECTION_INCOME)==0){
					obAccountDealInfo.setIncomMoney(new BigDecimal(map.get("money").toString()).setScale(2));
				}else {
					obAccountDealInfo.setPayMoney(new BigDecimal(map.get("money").toString()).setScale(2));
				}
				if(map.containsKey("bankId")){
					obAccountDealInfo.setBankId(Long.valueOf(map.get("bankId").toString()));
				}
				this.dao.save(obAccountDealInfo);
				ret=Constants.CODE_SUCCESS;
				msg="操作记录：操作成功!";
			}else{
				ret=Constants.CODE_FAILED;
				msg="操作记录：系统账户不存在!";
			}
		}catch(Exception e){
			e.printStackTrace();
			ret=Constants.CODE_FAILED;
			msg="操作记录：系统异常，请联系管理员!";
		}


		str[0]=ret;
		str[1]=msg;
		return str;
	}
	
	/**查询投资客户账户收支明细*/
	public List<ObAccountDealInfo> getaccountListQuery(String accountId,HttpServletRequest request, Integer start, Integer limit) {
		return	this.dao.getaccountListQuery(accountId,request,start,limit);
	}

	@Override
	public List<ObAccountDealInfo> getaccountListQuery1(String accountId, HttpServletRequest request, Integer start, Integer limit) {
		return this.dao.getaccountListQuery1(accountId,request,start,limit);
	}

	/**
	 * 查询还在办理中的交易记录
	 * @param accountId
	 * @param transferType
	 * @param dealRecordStatus
	 * @return
	 */
	public List<ObAccountDealInfo> queryThreeDealInfo(String accountId,String transferType,String dealRecordStatus){
		return	this.dao.queryThreeDealInfo(accountId,transferType,dealRecordStatus);
	}

	@Override
	public String saveRechargeDealInfo(String accountId, String money,
			String transferType, String dealType, String rechargeLevel,
			String investPersonName, String investPersonId,
			String rechargeConfirmStatus, String seniorValidationRechargeStatus) {
		String ret="";
		try{
		ObAccountDealInfo obAccountDealInfo=new ObAccountDealInfo();
		obAccountDealInfo.setAccountId(Long.valueOf(accountId));
		
		ObSystemAccount account=obSystemAccountService.get(obAccountDealInfo.getAccountId());
		obAccountDealInfo.setCompanyId(account.getCompanyId());
		
		
		if(transferType.equals(ObAccountDealInfo.T_RECHARGE)||transferType.equals(ObAccountDealInfo.T_PROFIT)){
			obAccountDealInfo.setIncomMoney(new BigDecimal(money));
			account.setTotalMoney(account.getTotalMoney().add(new BigDecimal(money)));
		}else if(transferType.equals(ObAccountDealInfo.T_ENCHASHMENT)||transferType.equals(ObAccountDealInfo.T_INVEST)){
			obAccountDealInfo.setPayMoney(new BigDecimal(money));
			account.setTotalMoney(account.getTotalMoney().subtract(new BigDecimal(money)));
		}
		//更新 账户记录
		account=obSystemAccountService.merge(account);
		
		obAccountDealInfo.setCurrentMoney(account.getTotalMoney()==null?new BigDecimal(0):account.getTotalMoney());//跟新当前取现记录生效后账户的余额是多少
		obAccountDealInfo.setTransferType(transferType);
		obAccountDealInfo.setDealType(Short.valueOf(dealType));
	    obAccountDealInfo.setRechargeLevel(Short.valueOf(rechargeLevel));
		obAccountDealInfo.setCreateId(Long.valueOf(1));
		obAccountDealInfo.setCreateDate(new Date());
		obAccountDealInfo.setTransferDate(new Date());
		
		obAccountDealInfo.setInvestPersonName(investPersonName);
		obAccountDealInfo.setInvestPersonId(Long.valueOf(investPersonId));
		
		dao.save(obAccountDealInfo);
		ret="success";
		}catch(Exception e){
			ret="faild";
		}
		return ret;
	}
	
	/**
	 * 查询系统账户交易信息调用方法
	 * @param accountId  系统账户id
	 * @param transferType  交易类型  1表示充值，2表示取现,3收益，4投资，5还本
	 * @param dealRecordStatus  系统账户交易明细记录状态：1审批2成功3失败4复核5办理
	 * @param rechargeConfirmStatus 系统账户交易明细记录是否生效，或者冻结
	 * @param request
	 * @return
	 */
	@Override
	public List<ObAccountDealInfo> queyAccountInfoRecord(Long accountId,String transferType, Short dealRecordStatus,Short rechargeConfirmStatus, HttpServletRequest request,Integer start, Integer limit) {
		return dao.queyAccountInfoRecord(accountId,transferType,dealRecordStatus,rechargeConfirmStatus,request,start,limit);
	}

	@Override
	public String[] saveAcountInfo(ObAccountDealInfo obAccountDealInfo) {
		// TODO Auto-generated method stub
		try{
			
		}catch(Exception e){
			
		}
		return null;
	}


	@Override
	public ObAccountDealInfo getByOrderNumber(String orderNo, String totalFee,String transferType,
			String type0) {
		// TODO Auto-generated method stub
		return dao.getByOrderNumber(orderNo,totalFee,transferType,type0);
	}


	/**
	 * 将账户交易记录更新，并且跟新系统账户金额
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("investPersonId",Long);//投资人Id
	 * map.put("investPersonType",Short);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
	 * map.put("transferType",Short);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
	 * map.put("money",BigDecimal);//交易金额
	 * map.put("dealDirection",short);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
	 * map.put("recordNumber",String);//交易流水号
	 * map.put("DealInfoId",Long);//交易记录Id  没有值默认传null
	 * map.put("dealStatus",short);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
	 * @return String[0]:操作代码  Constants.CODE_SUCCESS =8888账户操作成功，Constants.CODE_FAILED=0000  账户操作失败
	 *         String[1]:操作信息   操作成功 ，操作失败
	 *  add by liny 2014-5-6   
	 *  update by linyan  2014-10-10
	 * @return
	 */
	
	@Override
	public String[] updateAcountInfo(Map<String,Object> map) {
		// TODO Auto-generated method stub
		String[] str =new String[2];
		String ret="";
		String msg="";
		Long DealInfoId=null;
		if(map.containsKey("DealInfoId")){
			DealInfoId=(Long)map.get("DealInfoId");
		}
		ObSystemAccount account  =obSystemAccountService.getByInvrstPersonIdAndType(Long.valueOf(map.get("investPersonId").toString()), (Short)map.get("investPersonType"));
		if(account!=null){
			ObAccountDealInfo info =this.dao.getDealinfo(account.getId(),Long.valueOf(map.get("investPersonId").toString()),(Short)map.get("investPersonType"),map.get("recordNumber").toString(),DealInfoId);
			if(info!=null){
				if(map.get("dealStatus").toString().equals(ObAccountDealInfo.DEAL_STATUS_2.toString())){
					if(info.getTransferDate()!=null){
						ret=Constants.CODE_SUCCESS;
						msg="操作信息：业务数据已经处理过!";
					}else{
						
						BigDecimal  currentMoney=obSystemAccountService.changeAccountMoney(account.getId(),(BigDecimal)map.get("money"), (Short)map.get("dealDirection"));
						if(currentMoney!=null){
							info.setCurrentMoney(currentMoney);
							info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_2);
							info.setTransferDate(new Date());
							this.dao.merge(info);
							ret=Constants.CODE_SUCCESS;
							msg="操作信息：操作成功!";
						}else{
							ret=Constants.CODE_FAILED;
							info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_6);
							info.setTransferDate(new Date());
							info.setMsg("系统异常，请联系管理员!,交易标示异常");
							this.dao.merge(info);
							msg="操作信息：系统异常，请联系管理员!交易标示异常";
						}
					}
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_3)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_3);
					info.setTransferDate(new Date());
					info.setMsg("第三方支付出错!,交易标示作废");
					this.dao.merge(info);
					msg="操作信息：第三方支付出错!交易标示作废";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_1)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_1);
					info.setTransferDate(new Date());
					info.setMsg("无用操作");
					this.dao.merge(info);
					msg="操作信息：无用操作";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_4)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_4);
					info.setTransferDate(new Date());
					info.setMsg("取现审核");
					this.dao.merge(info);
					msg="操作信息：取现审核状态";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_5)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_5);
					info.setTransferDate(new Date());
					info.setMsg("取现办理");
					this.dao.merge(info);
					msg="操作信息：取现办理状态";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_7)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_7);
					info.setTransferDate(new Date());
					info.setMsg("第三方支付冻结金额!");
					this.dao.merge(info);
					msg="操作信息：成功冻结资金";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_8)){
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_8);
					info.setTransferDate(new Date());
					info.setMsg("第三方支付冻结金额!");
					this.dao.merge(info);
					msg="操作信息：成功冻结资金";
				}else if((Short)map.get("dealStatus")!=null&&map.get("dealStatus").equals(ObAccountDealInfo.DEAL_STATUS_9)){
					ret=Constants.CODE_SUCCESS;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_9);
					info.setMsg("银行处理中");
					this.dao.merge(info);
					msg="操作信息：成功冻结资金";
				}else{
					ret=Constants.CODE_FAILED;
					info.setDealRecordStatus(ObAccountDealInfo.DEAL_STATUS_6);
					info.setMsg("交易状态不正常，标识为异常数据，交易状态："+map.get("dealStatus"));
					info.setTransferDate(new Date());
					this.dao.merge(info);
					msg="操作信息：交易状态不正常，标识为异常数据，交易状态："+map.get("dealStatus");
				}
			}else{
				ret=Constants.CODE_FAILED;
				msg="操作失败：交易记录不存在!";
			}
		}else{
			ret=Constants.CODE_FAILED;
			msg="操作失败：系统账户不存在!";
		}
		str[0]=ret;
		str[1]=msg;
		return str;
	}


	@Override
	public List<ObAccountDealInfo> getDealInfoByPersionId(Long accountId) {
		// TODO Auto-generated method stub
		return dao.getInfoByInverPersionId(accountId);
	}
	@Override
	public BigDecimal sumBytype(String transferType, String dealRecordStatus,Long investPersonId) {
		return dao.sumBytype(transferType, dealRecordStatus,investPersonId);
	}


	@Override
	public BigDecimal sumPersonMoney(String transferType, Long investPersonId,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return dao.sumPersonMoney(transferType, investPersonId, startTime, endTime);
	}


	@Override
	public BigDecimal sumMoneyByTypeAndState(String transferType,
			Short dealRecordStatus, Integer direction) {
		return dao.sumMoneyByTypeAndState(transferType,dealRecordStatus,direction);
	}
	
	

}