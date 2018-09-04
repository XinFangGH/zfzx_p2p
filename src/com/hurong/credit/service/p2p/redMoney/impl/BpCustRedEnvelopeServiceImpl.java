package com.hurong.credit.service.p2p.redMoney.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.p2p.redMoney.BpCustRedEnvelopeDao;
import com.hurong.credit.model.p2p.redMoney.BpCustRedEnvelope;
import com.hurong.credit.service.p2p.redMoney.BpCustRedEnvelopeService;

/**
 * 
 * @author 
 *
 */
public class BpCustRedEnvelopeServiceImpl extends BaseServiceImpl<BpCustRedEnvelope> implements BpCustRedEnvelopeService{
	@SuppressWarnings("unused")
	private BpCustRedEnvelopeDao dao;

	public BpCustRedEnvelopeServiceImpl(BpCustRedEnvelopeDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	

}