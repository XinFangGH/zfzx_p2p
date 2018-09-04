package com.hurong.credit.dao.creditFlow.creditAssignment.bank;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;

/**
 * 
 * @author 
 *
 */
public interface ObAccountDealInfoDao extends BaseDao<ObAccountDealInfo>{
	//查询取现交易信息
	public List<ObAccountDealInfo> getDealList(String investPersonName,String transferDate,String flag);
//查询充值确认列表信息
	public List<ObAccountDealInfo> getRechargeDealList(String investPersonName,
			String seniorValidationRechargeStatus,
			String rechargeConfirmStatus, String flag,String rechargeLevel);
	/*查询投资人账户记录，总收入，总支出*/
	public String getCreateNameByCreateId(Long createId);
	//根据投资人id  投资人债权id  以及账户交易类型
	public ObAccountDealInfo getDealInfo(Long investMentPersonId, Long id,String flag);
	public List<ObAccountDealInfo> queyAccountInfoRecord(Long accountId,
			String transferType, Short dealRecordStatus,
			Short rechargeConfirmStatus, HttpServletRequest request,Integer start, Integer limit);
	/**
	 * 查询系统账户预冻结金额
	 * @param accountId
	 * @param direction
	 * @return
	 */
	public BigDecimal prefreezMoney(Long accountId, String direction);
	/**
	 * 查询系统账户中各种生效交易的总额
	 * @param accountId
	 * @param direction 
	 * @return
	 */
	public BigDecimal typeTotalMoney(Long userId,Long accountId, String direction);
	/**
	 * 查询还在办理中的交易记录
	 * @param accountId
	 * @param transferType
	 * @param dealRecordStatus
	 * @return
	 */
	public List<ObAccountDealInfo> queryThreeDealInfo(String accountId,String transferType,String dealRecordStatus);

	/**
	 * 根据系统账户信息以及查询条件来查询系统账户成功和没有冻结的交易信息
	 * @param accountId
	 * @param request
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ObAccountDealInfo> getaccountListQuery(String accountId,
			HttpServletRequest request, Integer start, Integer limit);
	/**
	 * 查询交易记录
	 * @param accountId
	 * @param investPersonId
	 * @param recordNumber
	 * @param accountInfoId
	 * @return
	 */
	public ObAccountDealInfo getDealinfo(Long accountId, Long investPersonId,Short investPersonType,
			String recordNumber, Long accountInfoId);
	
	public ObAccountDealInfo getByOrderNumber(String orderNo, String totalFee,String transferType,
			String type0);
	/**
	 * 根据iversionPersionid，稳安贷手机端
	 * @param iversionPersionId
	 * @return
	 */
	public List<ObAccountDealInfo> getInfoByInverPersionId(Long accountId);
	public BigDecimal sumBytype(String transferType,String dealRecordStatus,Long investPersonId);
	public BigDecimal sumPersonMoney(String transferType,Long investPersonId,String startTime,String endTime);
	
	/**
	 * 根据交易类型，状态，查询总交易金额,
	 * @param transferType交易类型
	 * @param dealRecordStatus  状态
	 * @param direction   交易方向   1 收入，2支出
	 * @return
	 */
	public BigDecimal sumMoneyByTypeAndState(String transferType,
			Short dealRecordStatus, Integer direction);

    List<ObAccountDealInfo> getaccountListQuery1(String accountId, HttpServletRequest request, Integer start, Integer limit);
}