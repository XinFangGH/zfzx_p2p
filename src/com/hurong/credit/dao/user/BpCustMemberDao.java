package com.hurong.credit.dao.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.mobile.InviteDetail;
import com.hurong.credit.model.mobile.InvitePersonDetail;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.FeedBack;

/**
 * 
 * @author 
 *
 */
public interface BpCustMemberDao extends BaseDao<BpCustMember>{
	
	public BpCustMember login(String loginname,String password);
	public BpCustMember isExist(String loginname);
	/**
	 * 判断邮箱是否存在
	 */
	public BpCustMember isExistEmail(String email);
	/**
	 * 判断邀请的推荐码是否存在
	 * @param recommandPerson
	 * @return
	 */
	public BpCustMember isRecommandPerson(String recommandPerson);
	/**
	 * 获取所有没被禁用和删除的用户
	 */
	public List<BpCustMember> getAllUserful();
	/**
	 * 判断电话是否存在
	 * 
	 * */
	public BpCustMember isExistPhone(String phone);
	/**
	 * 判断该身份证是否已被其他用户认证
	 */
	public BpCustMember isExistCode(String code,Long id);
	/**
	 * 判断该邮箱是否已被其他用户认证
	 */
	public BpCustMember isExistEmailOther(String email,Long id);
	
	/**
	 * 判断该手机是否已被其他用户认证
	 */
	public BpCustMember isExistPhoneOther(String telphone,Long id);
	public BpCustMember getMemberByPIdAndFlag(String flag, String pid);
	public BpCustMember getMemberUserName(String userName, String cardNum);
	/**
	 * 通过第三方标识查询
	 * @param thirdPayFlagId
	 * @return
	 */
	public BpCustMember getMemberUserName(String thirdPayFlagId);
	/**
	 * 通过email查询用户
	 * 
	 */	
	public BpCustMember valadateInfo(String flag,String loginName, String telphone, String cardNumber, String email);
	public BpCustMember getMemberByEmail(String email);
	public BpCustMember getMemberByPhone(String telphone);
	public BpCustMember getByThirdPayId(String platformUserNo);
	public BpCustMember getByQQ(String qq);
	public BpCustMember getBySina(String sina);
	public List<com.hurong.credit.model.user.BpCustMember> referrer2Excel(String begintime,String endtime);
	public BpCustMember getInviter(String sina);
	
	public void executeSql(String sql);
	
	//根据登录名称查找对应的用户
	public BpCustMember getByLoginName(String loginName);

	/**
	 * 通过投资人ID获取该客户托管账户余额
	 * @param investmentPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccountLeft(Long investmentPersonId);
	
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyBidFrozen(Long investPersonId);
	
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyMmplanFrozen(Long investPersonId);
	
	
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第一部分：散标投资的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第二部分：理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll1(Long investPersonId); 
	
	/**
	 * 通过投资人ID获取该投资人待还借款总额(款项计划表中该借款人所有待还款型之和)
	 * @param receiverP2PaccountNumber
	 * @return
	 */
	public BigDecimal getMoneyDueinAll(String receiverP2PaccountNumber);

	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第一部分是散标投资的投资额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第二部分是理财计划的本金和利息
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest1(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第一部分散标投资的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第二部分理财计划的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen1(Long investPersonId) ;
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第一部分散标投资的投资笔数)
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountInvestBack(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第二部分理财计划的投资笔数)
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountInvestBack1(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人招标中借款笔数
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountBidLoan(String receiverP2PaccountNumber);
	
	/**
	 * 通过投资人ID获取该投资人还款中借款笔数
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountRepaymentLoan(String receiverP2PaccountNumber);
	
	/**
	 * 通过投资人ID获取该投资人累计到账收益(客户账户总计收到的所有利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeIncome(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人累计回收本金
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPreweekIncome(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人上月到账收益(30天以来，包括第30天，客户账户总计收到的所有利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPremonthIncome(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第一部分是散标投资的利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome(Long investPersonId);
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第二部分理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome1(Long investPersonId) ;
	/**
	 * 通过投资人ID获取该投资人会员等级
	 * @param score
	 * @return
	 */
	public String getLevelMark(Long score);
	/**
	 * 邮箱验证码返回的唯一标识
	 * @param emailcode
	 * @return
	 */
	public BpCustMember getEmailCode(String emailcode);
	/**
	 * 查询债权记录
	 */
	public List<ShowManageMoney> queryAllDebt(String loginName,HttpServletRequest request);

	/**
	 * 查询条数(分页)
	 */
	public List<ShowManageMoney> queryAllDebtNum(String loginName,HttpServletRequest request);

	/**
	 * 我的债权提前退出查询
	 */
	public List<PlManageMoneyPlanBuyinfo> queryEarlyList(String loginName,HttpServletRequest request);

	/**
	 * 我的债权提前退出的记录查询条数(分页)
	 */
	public  List<PlManageMoneyPlanBuyinfo> queryEarlyListNum(String loginName,HttpServletRequest request);
	
	/**
	 * 我的邀请
	 */
	
	public List<BpCustMember> queryMyInvest(BpCustMember member,HttpServletRequest request);
	List<BpCustMember> appQueryMyInvest(BpCustMember member, HttpServletRequest request);


	public List<BpCustMember> queryMyInvestNum(BpCustMember member,HttpServletRequest request);
	public boolean organiz (String recommendCode);
	public Object regCount();

	BpCustMember getUserInfo(Long id);

    Integer chenkPhoneNum(String telPhone);

    List<Investproject> getMyInviteInfo(String bidId);

    List<InvestPersonInfo> getMoneyPeople(BpCustMember member);
	List<InvestPersonInfo> appGetMoneyPeople(BpCustMember member);

	void saveFeedBack(FeedBack feedBack);

    Object findCode(BpCustMember bpCustMember);

    Object finTel(BpCustMember bpcust);

    void updateTC(String telphone);

	Object findPhone(String plainCode);

    List<InviteDetail> inviteList(String plainCode);

	List<InvitePersonDetail> checkInviteDetail(String id);

	InviteDetail inviteList1(String id);

    List<InviteDetail> selectInvite(HttpServletRequest request, String plainpassword);

    Integer countNum(String plainpassword);

	BigDecimal sunMoney(String plainpassword);
}