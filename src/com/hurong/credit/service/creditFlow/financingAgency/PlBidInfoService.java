package com.hurong.credit.service.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hurong.core.service.BaseService;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;

/**
 * 
 * @author 
 *
 */
public interface PlBidInfoService extends BaseService<PlBidInfo>{
	/**
	 * 通过钱多多接口投标
	 * @param cpCut 投资人
	 * @param plBidInfo 标信息 
	 * @param moneyMoreMore钱多多实体
	 * @param basePath 路径
	 * orderNum 订单号
	 * @return
	 */
	public String[] bidByMoneyMore(BpCustMember cpCut,PlBidInfo plBidInfo,MoneyMoreMore moneyMoreMore,String basePath,String orderNum,HttpServletResponse response);
	/**
	 * 通过标获取借款人 在p2p 注册的账号
	 * @param plBidInfo
	 * @return
	 */
	public BpCustMember getLoanMember(PlBidInfo plBidInfo);

	public  BpCustMember getLoanMember(PlBidPlan bidplan);
	
	/**
	 * 通过标获取借款人借款金额
	 * @param plBidInfo
	 * @return
	 */
	public String getLoanMoney(PlBidInfo plBidInfo);
	
	/**
	 * 将投资人信息存入投资人列表
	 * @param orderNum 订单编号
	 * @param bid
	 */
	public void saveToERP(String bidInfoId,String orderNum);
	/**
	 * 通过订单号获取
	 * @param ordId
	 * @return
	 */
	public PlBidInfo getByOrdId(String ordId);
	/**
	 * 通过订单号获取
	 * @param ordId
	 * @return
	 */
	public PlBidInfo getByNewOrdId(String ordId);
	/**
	 * 获取放款后的项目
	 * @return
	 */
	public List<PlBidInfo> getBidLoanAfter();
	
	public List<PlBidInfo> getPlBidList(int state);
	/**
	 * 根据请求第三方的流水号来查询
	 * @param requestNo
	 * @return
	 */
	public PlBidInfo getOrderNumber(String requestNo);
	
	public Long getPlanInfoCount(Long userId,String type);
	public Long getPlanInfoFailCount(Long userId,String type);
	
	public List<PlBidInfo> getBidList(Long userId, String type);
	
	public List<PlBidInfo> getBidOrderNoList(Long userId,String type);
	
	public java.math.BigDecimal getLoanTotal(Long userId,String type);
	
	public List<PlBidInfo> getIntentInfo(Long bidId ,String group);
	
	public java.math.BigDecimal getUserMoneyGroup(Long bidId,Long userId);
	List<PlBidInfo> getIntentInfo(Long bidId,Long userId);
	
	public java.math.BigDecimal queryUserMoney(Long bidId,Long userId,String orderNo);
	public List<PlBidInfo> getbyPersonAndPlan(Long id, String state, Pager pager);
	public Map<String, Object> getbyPersonAndPlan2(Long id, String planstate,
			String start, String limit); 
	
	public BigDecimal queryAllProfit(Long userId);
	//查询个人中心我的理财的数据
	public BpPersonCenterData queryAllManage(Long userId);
	//个人中心获取所有关于标的的数据
	public BpPersonCenterData queryAllBid(Long userId);   

	public List<PlBidInfo> monthsort();
	public List<PlBidInfo> weeksort();
	public List<PlBidInfo> allsort();

    List<PlBidInfo> getListInfo(HttpServletRequest request,Integer start,Integer limit);
}


