package com.hurong.credit.dao.creditFlow.financingAgency;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author 
 *
 */
public interface PlBidInfoDao extends BaseDao<PlBidInfo>{

	PlBidInfo getByOrdId(String ordId);
    PlBidInfo getByNewOrdId(String ordId);
	List<PlBidInfo> getBidLoanAfter();
	
	public List<PlBidInfo> getPlBidList(int state);
	public Long getPlanInfoCount(Long userId, String type);
	public Long getPlanInfoFailCount(Long userId,String type);
	public List<PlBidInfo> getBidList(Long userId, String type);
	public List<PlBidInfo> getBidOrderNoList(Long userId,String type) ;
	
	public java.math.BigDecimal getLoanTotal(Long userId,String type);
	
	public List<PlBidInfo> getIntentInfo(Long bidId,String group);

	//新增
    List<PlBidInfo> getListInfo(HttpServletRequest request,Integer start,Integer limit);

	public java.math.BigDecimal getUserMoneyGroup(Long bidId,Long userId);
	public List<PlBidInfo> getIntentInfo(Long bidId,Long userId);
	
	public java.math.BigDecimal queryUserMoney(Long bidId,Long userId,String orderNo);

	List<PlBidInfo> getbyPersonAndPlan(Long id, String state, Pager pager); 
	
	Map<String, Object> getbyPersonAndPlan(Long id, String planstate,
			String start, String limit); 
	
	/**
	 * 散标理财
	 * @param userId
	 * @return
	 */
	//查询散标投资收益
	public BigDecimal queryAllProfit(Long userId);
	//查询累计投资笔数
	public Integer queryBidCount(Long userId);
	//查询累计投资金额(主动投标)
	public BigDecimal queryAddBidMoney(Long userId);
	
	public BpPersonCenterData queryAllBid(Long userId);
	
	/**
	 * 我的借款
	 * @param loginName
	 * @return
	 */
	
	//查询借款总金额
	public BigDecimal queryAllLoanMoney(String loginName);
	//查询借款笔数
    public Integer queryAllLoanAmount(String loginName);
    //招标中借款的总笔数
    public Integer queryBidingAmount(String loginName);
    //招标中借款的金额
    public BigDecimal queryBidingMoney(String loginName);
    //待还款的笔数
    public Integer queryUnback(String loginName);
    //代还款的金额
    public BigDecimal queryUnBackMoney(String loginName); 
    //借款总查询
    public BpPersonCenterData queryAllJk(String loginName,Long userId);
    
    
    /**
     * 批量债权
     * @param loginName
     * @return
     */
    
    //批量债权中的理财数据(预售中)
    public Integer queryUnpublic(String loginName);
    //招标中的理财数据
    public Integer queryBiding(String loginName);
    //已齐标的理财数据
    public Integer queryFull(String loginName);
    //回款中的理财计划个数
    public Integer queryBacking(String loginName);
    //已完成的理财计划个数
    public Integer queryOk(Long userId);
    //理财计划总查询
    public BpPersonCenterData queryAllLc(Long userId,String loginName);
    
    
    /**
     * 我的理财
     */
    //理财的累计收益
    public BigDecimal queryAllBenifit(Long userId);
    //累计投资
    public Integer queryInvestCount(Long userId);
    //累计投资金额
    public BigDecimal queryAllInvest(Long userId);
    //应派本金
    public BigDecimal UnbackRepayment(String loginName);
    //应派利息
    public BigDecimal UnbackInterest(String loginName);
    
    public BpPersonCenterData queryAllManage(Long userId,String loginName);

    
    /**
     * 债权交易
     */
    public BpPersonCenterData queryAllSale(Long userId);
    
    /**
     * 我的推荐人    
     */
    
    public BpPersonCenterData myRecommend(BpCustMember mem);
    public List<PlBidInfo> monthsort();
    public List<PlBidInfo> allsort();
	List<PlBidInfo> weeksort();

}