package com.hurong.credit.dao.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;

/**
 * 
 * @author 
 *
 */
public interface PlBidSaleDao extends BaseDao<PlBidSale>{
	public List<PlBidSale> getCanTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	public List<PlBidSale> outTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	public List<PlBidSale> inTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	public List<PlBidSale> transferingList(Long userId, PagingBean pb,Map<String, String> map, String str);
	public List<PlBidSale> getBySaleState(Short saleState);
	public PlBidSale getByNewOrderNo(String requestNo);
	public List<PlBidSale> getBySaleState(Long bidInfoID,String saleState) ;
	public PlBidSale getByPreTransferRequestNo(String requestNo);
	public List<PlBidSale> getByPbidPlanID(Long bidPlanID,String saleState);
	public PlBidSale querySaleInfo(Long saleId); 
	public List<PlBidSale> getCanTransferingList(Long userId,HttpServletRequest request);
	public List<PlBidSale> transferingList(Long userId,HttpServletRequest request);
	public List<PlBidSale> querySoldSale(Long userId,HttpServletRequest request);
	public List<PlBidSale> queryBuyedSale(Long userId,HttpServletRequest request);
	public List<PlBidSale> queryClosedSale(Long userId,HttpServletRequest request);
	public List<PlBidSale> getCanTransferingListVip(Long userId,
			HttpServletRequest request);
	public List<PlBidSale> transferingListVip(Long userId,
			HttpServletRequest request);
	public List<PlBidSale> querySoldSaleVip(Long userId, HttpServletRequest request);
	public List<PlBidSale> queryBuyedSaleVip(Long userId,
			HttpServletRequest request);
	public List<PlBidSale> queryClosedSaleVip(Long userId,
			HttpServletRequest request);
}