package com.hurong.credit.service.creditFlow.financingAgency.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidSaleDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidSaleService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class PlBidSaleServiceImpl extends BaseServiceImpl<PlBidSale> implements PlBidSaleService{
	@SuppressWarnings("unused")
	private PlBidSaleDao dao;
	@Resource
	private PlBidInfoDao plBidInfoDao;
	public PlBidSaleServiceImpl(PlBidSaleDao dao) {
		super(dao);
		this.dao=dao;
	}

	

	@Override
	public List<PlBidSale> inTransferingList(Long userId, PagingBean pb,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.inTransferingList(userId, pb, map);
	}

	@Override
	public List<PlBidSale> outTransferingList(Long userId, PagingBean pb,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.outTransferingList(userId, pb, map);
	}



	@Override
	public List<PlBidSale> getCanTransferingList(Long userId, PagingBean pb,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.getCanTransferingList(userId, pb, map);
	}



	@Override
	public List<PlBidSale> transferingList(Long userId, PagingBean pb,
			Map<String, String> map, String str) {
		// TODO Auto-generated method stub
		return dao.transferingList(userId, pb, map,str);
	}



	@Override
	public PlBidSale getByNewOrderNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByNewOrderNo(requestNo);
	}



	@Override
	public List<PlBidSale> getBySaleState(Short saleState) {
		// TODO Auto-generated method stub
		return dao.getBySaleState(saleState);
	}



	@Override
	public List<PlBidSale> getBySaleState(Long bidInfoID, String saleState) {
		// TODO Auto-generated method stub
		return dao.getBySaleState(bidInfoID, saleState);
	}



	@Override
	public PlBidSale getByPreTransferRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByPreTransferRequestNo(requestNo);
	}



	@Override
	public List<PlBidSale> getByPbidPlanID(Long bidPlanID, String saleState) {
		// TODO Auto-generated method stub
		return dao.getByPbidPlanID(bidPlanID,saleState);
	}



	@Override
	public PlBidSale querySaleInfo(Long saleId) {
		// TODO Auto-generated method stub
		return dao.querySaleInfo(saleId);
	}



	@Override
	public String queryAllData(Long userId) {
		// TODO Auto-generated method stub
		BpPersonCenterData data = plBidInfoDao.queryAllSale(userId);
        StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(1).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
 		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
 		buffer.append(json.serialize(data));
 		buffer.append("}");
		return buffer.toString();
	}



	@Override
	public String queryAllSaleList(Long userId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		int listNums = 0;
		if(!"".equals(type)&&"CanTransfering".equals(type)){//可交易的债权
			list = dao.getCanTransferingList(userId,request);
			listNums = dao.getCanTransferingList(userId, null).size();//计算总共条数
		}else if(!"".equals(type)&&"transferingList".equals(type)){//交易中的债权
			list = dao.transferingList(userId, request);
			listNums = dao.transferingList(userId, null).size();//交易中债权的总条数
		}else if(!"".equals(type)&&"transfered".equals(type)){//已卖出的债权
			list = dao.querySoldSale(userId, request);
			listNums = dao.querySoldSale(userId, null).size();
		}else if(!"".equals(type)&&"buyed".equals(type)){//已购买的债权
			list =  dao.queryBuyedSale(userId, request);
			listNums = dao.queryBuyedSale(userId, null).size();
		}else if(!"".equals(type)&&"closed".equals(type)){//已关闭的债权
			list =  dao.queryClosedSale(userId, request);
			listNums =  dao.queryClosedSale(userId, null).size();
			StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(listNums).append(",\"result\":");
			JSONSerializer json = JsonUtil.getJSONSerializer();
			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
			buffer.append(json.serialize(list));
			buffer.append("}");
		}
		return createJson(listNums,list);
	}

	/**
	 * 债权VIP
	 */
	@Override
	public String queryAllSaleListVip(Long userId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		List<PlBidSale> list = new ArrayList<PlBidSale>();
		int listNums = 0;
		if(!"".equals(type)&&"CanTransfering".equals(type)){//可交易的债权
			list = dao.getCanTransferingListVip(userId,request);
			listNums = dao.getCanTransferingListVip(userId, null).size();//计算总共条数
		}else if(!"".equals(type)&&"transferingList".equals(type)){//交易中的债权
			list = dao.transferingListVip(userId, request);
			listNums = dao.transferingListVip(userId, null).size();//交易中债权的总条数
		}else if(!"".equals(type)&&"transfered".equals(type)){//已卖出的债权
			list = dao.querySoldSaleVip(userId, request);
			listNums = dao.querySoldSaleVip(userId, null).size();
		}else if(!"".equals(type)&&"buyed".equals(type)){//已购买的债权
			list =  dao.queryBuyedSaleVip(userId, request);
			listNums = dao.queryBuyedSaleVip(userId, null).size();
		}else if(!"".equals(type)&&"closed".equals(type)){//已关闭的债权
			list =  dao.queryClosedSaleVip(userId, request);
			listNums =  dao.queryClosedSaleVip(userId, null).size();
			StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(listNums).append(",\"result\":");
			JSONSerializer json = JsonUtil.getJSONSerializer();
			json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
			buffer.append(json.serialize(list));
			buffer.append("}");
		}
		return createJson(listNums,list);
	}


	@Override
	public String createJson(Integer nums, Object obj) {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer("{\"success\":true,\"totalCounts\":").append(nums).append(",\"result\":");
		JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buffer.append(json.serialize(obj));
		buffer.append("}");
		return buffer.toString();
	}

}