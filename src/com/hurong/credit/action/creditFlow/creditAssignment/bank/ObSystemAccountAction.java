package com.hurong.credit.action.creditFlow.creditAssignment.bank;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;

import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;

import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;

import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;

import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;

/**
 * 
 * @author 
 *
 */
public class ObSystemAccountAction extends BaseAction{
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private ObAccountDealInfoService  obAccountDealInfoService;
	private ObSystemAccount obSystemAccount;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ObSystemAccount getObSystemAccount() {
		return obSystemAccount;
	}

	public void setObSystemAccount(ObSystemAccount obSystemAccount) {
		this.obSystemAccount = obSystemAccount;
	}

	
}
