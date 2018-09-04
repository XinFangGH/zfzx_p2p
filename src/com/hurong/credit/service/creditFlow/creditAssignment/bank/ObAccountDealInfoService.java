package com.hurong.credit.service.creditFlow.creditAssignment.bank;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.Constants;
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;

/**
 * 
 * @author 
 *
 */

public interface ObAccountDealInfoService extends BaseService<ObAccountDealInfo>{

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
	 * map.put("fianceStatus",short);//资金明细状态：0资金冻结，1交易生效，null 表示为 交易记录
	 * map.put("dealStatus",short);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
	 * @return String[0]:操作代码  Constants.CODE_SUCCESS =8888账户操作成功，Constants.CODE_FAILED=0000  账户操作失败
	 *         String[1]:操作信息   操作成功 ，操作失败
	 *  add by liny 2014-5-6   
	 *  update by linyan  2014-10-10
	 */
	
	public String[] operateAcountInfo(Map<String,Object> map);


	/**
	 * 操作系统账户调用方法   只操作流水  不操作账户余额
	 * Map<String,Object> map =new HashMap<String,Object>();
	 * map.put("investPersonId",Long);//投资人Id
	 * map.put("investPersonType",Short);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
	 * map.put("transferType",Short);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
	 * map.put("money",BigDecimal);//交易金额
	 * map.put("dealDirection",short);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
	 * map.put("dealType",Short);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）
	 * map.put("recordNumber",String);//交易流水号
	 * map.put("fianceStatus",short);//资金明细状态：0资金冻结，1交易生效，null 表示为 交易记录
	 * map.put("dealStatus",short);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)
	 * @return String[0]:操作代码  Constants.CODE_SUCCESS =8888账户操作成功，Constants.CODE_FAILED=0000  账户操作失败
	 *         String[1]:操作信息   操作成功 ，操作失败
	 *
	 */
	public String[] operateDealInfo(Map<String,Object> map);
	
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
	
	public String[] updateAcountInfo(Map<String,Object> map);
	/**
	 * 查询还在办理中的交易记录
	 * @param accountId
	 * @param transferType
	 * @param dealRecordStatus
	 * @return
	 */
	public List<ObAccountDealInfo> queryThreeDealInfo(String accountId,String transferType,String dealRecordStatus);

	/**
	 * 交易 记录  充值   提现  投标  还款
	 * @param accountId 虚拟账户id
	 * @param money  交易金额
	 * @param transferType 交易类型    1表示充值，2表示取现,3收益，4投资，
	 * @param dealType  交易类型。1现金交易。2银行卡交易
	 * @param rechargeLevel  充值金额确认级别  1表示只需要充值确认，2表示需要高级确认
	 * @param investPersonName 投资人姓名
	 * @param investPersonId 投资人id
	 * @param rechargeConfirmStatus  充值确认 ,true 表示确认  false  表示没有确认
	 * @param seniorValidationRechargeStatus  充值高级确认 ,true 表示确认  false  表示没有确认
	 * @return
	 */

	public String saveRechargeDealInfo(String accountId, String money ,String transferType,String dealType,String rechargeLevel,String investPersonName,String investPersonId,String rechargeConfirmStatus,String seniorValidationRechargeStatus);
	
/*	*/
	
	
	
	/**
	 * 查询系统账户交易信息调用方法
	 * @param accountId  系统账户id
	 * @param transferType  交易类型  1表示充值，2表示取现,3收益，4投资，5还本
	 * @param dealRecordStatus  系统账户交易明细记录状态：1审批2成功3失败4复核5办理
	 * @param rechargeConfirmStatus 系统账户交易明细记录是否生效，或者冻结
	 * @param request
	 * @return
	 */
	
	public List<ObAccountDealInfo> queyAccountInfoRecord(Long accountId,String transferType,Short dealRecordStatus, Short rechargeConfirmStatus,HttpServletRequest request,Integer start, Integer limit);
	/**
	 * 线下客户充值操作接口
	 * @param obAccountDealInfo
	 * @return
	 */
	
	public String[] saveAcountInfo(ObAccountDealInfo obAccountDealInfo);
	/**
	 * 根据系统账户信息以及查询条件来查询系统账户成功和没有冻结的交易信息
	 * @param accountId
	 * @param request
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ObAccountDealInfo> getaccountListQuery(String accountId,HttpServletRequest request, Integer start, Integer limit);
	public List<ObAccountDealInfo> getaccountListQuery1(String accountId,HttpServletRequest request, Integer start, Integer limit);
	/**
	 * 根据交易编号和交易客户类型  以及交易金额查询到交易记录
	 * @param orderNo
	 * @param type0
	 * @return
	 */
	public ObAccountDealInfo getByOrderNumber(String orderNo,String totalFee, String transferType,String type0);
	/**
	 * 根据inverstPersionId得到账户明细
	 * @param inverstPersionId
	 * @return
	 */
	public List<ObAccountDealInfo> getDealInfoByPersionId(Long inverstPersionId);
	public BigDecimal sumBytype(String transferType,String dealRecordStatus,Long investPersonId);
	public BigDecimal sumPersonMoney(String transferType,Long investPersonId,String startTime,String endTime);

	/**
	 * 根据交易类型，状态，查询总交易金额,
	 * @param transferType交易类型
	 * @param dealRecordStatus  状态
	 * @param direction   交易方向   1 收入，2支出
	 * @return
	 */
	public BigDecimal sumMoneyByTypeAndState(String transferType,Short dealRecordStatus,Integer direction);
		
	
}


