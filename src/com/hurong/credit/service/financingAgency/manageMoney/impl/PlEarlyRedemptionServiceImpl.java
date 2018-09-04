package com.hurong.credit.service.financingAgency.manageMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.BeanUtil;
import com.hurong.credit.dao.financingAgency.manageMoney.PlEarlyRedemptionDao;
import com.hurong.credit.model.financingAgency.manageMoney.PlEarlyRedemption;
import com.hurong.credit.service.financingAgency.manageMoney.PlEarlyRedemptionService;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.parser.JSONParser;

/**
 * 
 * @author 
 *
 */
public class PlEarlyRedemptionServiceImpl extends BaseServiceImpl<PlEarlyRedemption> implements PlEarlyRedemptionService{
	@SuppressWarnings("unused")
	private PlEarlyRedemptionDao dao;
	public PlEarlyRedemptionServiceImpl(PlEarlyRedemptionDao dao) {
		super(dao);
		this.dao=dao;
	}


}