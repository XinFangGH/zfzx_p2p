package com.hurong.credit.service.creditFlow.finance.compensatory;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.creditFlow.finance.compensatory.PlBidCompensatory;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
public interface PlBidCompensatoryService extends BaseService<PlBidCompensatory>{
	public List<PlBidCompensatory> getCompensatoryList(Long loanerp2pId,String custmerType,PagingBean pb);
	/**
	 * 查询某担保公司下代偿的数量
	 * @param compensatoryP2PId
	 * @param compensatoryType
	 * @param backStatus
	 * @return
	 */
	public Long findCountByComp2PId(Long compensatoryP2PId,String compensatoryType,Integer backStatus);
	/**
	 * 查询某担保公司应收代偿金额
	 * @param compensatoryP2PId
	 * @param compensatoryType
	 * @param backStatus
	 * @return
	 */
	public BigDecimal findCompensatoryMoneytByComp2PId(Long compensatoryP2PId,String compensatoryType,Integer backStatus);
	/**
	 * 查询某担保公司下代偿的数量
	 * @param compensatoryP2PId
	 * @param compensatoryType
	 * @param backStatus
	 * @return
	 */
	public BigDecimal findPunishMoneyByComp2PId(Long compensatoryP2PId,String compensatoryType,Integer backStatus);
	/**
	 * 查询某担保公司下代偿的项目列表(多查询条件)
	 * @param compensatoryP2PId
	 * @param compensatoryType
	 * @param request
	 * @param backStatus
	 * @param pb
	 * @return
	 */
	public List<PlBidCompensatory> findListByComp2PId(Long compensatoryP2PId,String compensatoryType,Integer backStatus,Pager pb,HttpServletRequest request);
	public List<PlBidCompensatory> findListByComp2PId1(Long compensatoryP2PId,String compensatoryType,Integer backStatus,Pager pb,HttpServletRequest request);
	/**
	 * 平台or担保户代偿
	 * @param planId
	 * @param peridId
	 * @param cardNo
	 * @param type
	 */
	public  void saveComPensatoryService(String planId, String peridId, String cardNo,String type,String  period);
	
	public BpCustMember getLoanMember(PlBidPlan bidplan);
	
	public BpFundProject getBpFundProject(PlBidPlan bidplan);
	public BpCustMember getGuraneeMember(PlBidPlan bidplan);
}


