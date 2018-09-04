package com.hurong.credit.service.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;

/**
 * 
 * @author 
 *
 */
public interface PlBidSaleService extends BaseService<PlBidSale>{
	 public List<PlBidSale> getCanTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	 public List<PlBidSale> outTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	 public List<PlBidSale> inTransferingList(Long userId, PagingBean pb,Map<String, String> map);
	 public List<PlBidSale> transferingList(Long userId, PagingBean pb,Map<String, String> map, String str);
	 public PlBidSale getByNewOrderNo(String requestNo);
	 public List<PlBidSale> getBySaleState(Short saleState);
	 public List<PlBidSale> getBySaleState(Long bidInfoID,String saleState); 
	 public PlBidSale getByPreTransferRequestNo(String requestNo); 
	 public List<PlBidSale> getByPbidPlanID(Long bidPlanID,String saleState);
	 //依据交易记录的Id查询 出交易详情
	 public PlBidSale querySaleInfo(Long saleId);
	 //查询债权交易的统计数据
	 public String queryAllData(Long userId);
	 //查询债权交易可交易记录
	 public String queryAllSaleList(Long userId,HttpServletRequest request);
	 //针对于生成jsrender返回的json串
	 public String createJson(Integer nums,Object obj);
	 public String queryAllSaleListVip(Long userId,
				HttpServletRequest request) ;
}


