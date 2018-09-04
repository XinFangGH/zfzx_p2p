package com.hurong.credit.service.creditFlow.finance.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import com.sdicons.json.mapper.MapperException;
import com.hurong.core.dao.GenericDao;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;

import com.hurong.credit.dao.creditFlow.finance.FundIntentDao;
import com.hurong.credit.dao.customer.InvestPersonInfoDao;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIntent;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.service.creditFlow.finance.FundIntentService;

public class FundIntentServiceImpl extends BaseServiceImpl<FundIntent> implements
		FundIntentService{

	@Resource
	private FundIntentDao fundIntentDao;

	@Resource
	private InvestPersonInfoDao investPersonInfoDao;
	
	public FundIntentServiceImpl(FundIntentDao dao) {
		super(dao);
	}

}
