package com.hurong.credit.action.creditFlow.creditAssignment.bank;

/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class ObAccountDealInfoAction extends BaseAction {
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	
	@Resource
	private ObSystemAccountService obSystemAccountService;
	
	private ObAccountDealInfo obAccountDealInfo;
	private List<ObAccountDealInfo> ptList;

	private Long id;


	
	private ObSystemAccount obSystemAccount;
	/**
	 * 标志系统账户交易明细记录类型
	 * 1表示充值，2表示取现,3收益，4投资，5还本
	 */
	private Short transferType;
	/**
	 * 标志系统账户交易明细记录是否生效，或者冻结
	 */
	private Short rechargeConfirmStatus;
	/**
	 * 表示当前系统账户交易明细记录状态：1审批2成功3失败4复核5办理
	 */
	private Short dealRecordStatus;
	
	public Short getRechargeConfirmStatus() {
		return rechargeConfirmStatus;
	}

	public void setRechargeConfirmStatus(Short rechargeConfirmStatus) {
		this.rechargeConfirmStatus = rechargeConfirmStatus;
	}

	public List<ObAccountDealInfo> getPtList() {
		return ptList;
	}

	public void setPtList(List<ObAccountDealInfo> ptList) {
		this.ptList = ptList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ObAccountDealInfo getObAccountDealInfo() {
		return obAccountDealInfo;
	}

	public void setObAccountDealInfo(ObAccountDealInfo obAccountDealInfo) {
		this.obAccountDealInfo = obAccountDealInfo;
	}

	

	public void setObSystemAccount(ObSystemAccount obSystemAccount) {
		this.obSystemAccount = obSystemAccount;
	}

	public ObSystemAccount getObSystemAccount() {
		return obSystemAccount;
	}

	public void setTransferType(Short transferType) {
		this.transferType = transferType;
	}

	public Short getTransferType() {
		return transferType;
	}

	public void setDealRecordStatus(Short dealRecordStatus) {
		this.dealRecordStatus = dealRecordStatus;
	}

	public Short getDealRecordStatus() {
		return dealRecordStatus;
	}
}
