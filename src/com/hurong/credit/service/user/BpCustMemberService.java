package com.hurong.credit.service.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.mobile.InviteDetail;
import com.hurong.credit.model.mobile.InvitePersonDetail;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.webFeedBack.FeedBack;

/**
 * 
 * @author 
 *
 */
public interface BpCustMemberService extends BaseService<BpCustMember>{
	public BpCustMember login(String loginname, String password);
	public BpCustMember isExist(String loginname);
	public BpCustMember isExistEmail(String email);
	public BpCustMember isRecommandPerson(String recommandPerson);
	public BpCustMember isExistTelphone(String phone);
	public List<BpCustMember> getAllUserful();
	public BpCustMember getByQQ(String qq);
	public BpCustMember getBySina(String sina);
	public BpCustMember getByEmail(String email);
	public BpCustMember getByLoginName(String loginName);
	/**
	 * 将用户邮箱设置为已验证
	 * @return     
	 */
	public void setCheckEmail(Long id);
	/**
	 * 判断手机号是否已验证
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
	/**
	 * 获取 用户信息 通过 标识 和类别id
	 * @param flag
	 * @param pid
	 * @return
	 */
	public BpCustMember getMemberByPIdAndFlag(String flag, String pid);
	/**
	 * 获取 用户信息 通过 用户名 和身份证号
	 * @param flag
	 * @param pid
	 * @return
	 */
	public BpCustMember getMemberUserName(String userName,String cardNum);
	/**
	 * 获取 用户信息 通过 第三方账户的 唯一标识
	 * @param username
	 * @return
	 */
	public BpCustMember getMemberUserName(String thirdPayFlagId);
	/**
	 * 检查用户的信息是否已经维护
	 * @return
	 */
	public String[] checkUser(BpCustMember member);
	
	public BpCustMember getMemberByRet(String ret, String cardNum);

	public BpCustMember getMemberByRet(String ret);
	
	public String validateInfo(String userName,String passWord,String loginName,String loginPassWord,String telphone,String cardNumber,String email);
	public BpCustMember getMemberByEmail(String email);
	public BpCustMember getMemberByPhone(String telphone);
	
	public BpCustMember getByThirdPayId(String platformUserNo);
	public List<com.hurong.credit.model.user.BpCustMember> getReferrer(String begintime, String endtime);
	public BpCustMember getInviter(String id);
	
    public String queryAllJk(String loginName,Long userId,HttpServletRequest request);//查询所用的借款数据
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
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额 （理财计划）
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
	public BigInteger getCountBidFrozen1(Long investPersonId);
	
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
	 * 通过投资人ID获取该投资人上周到账收益(7天以来，包括第7天，客户账户总计收到的所有利息)
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
	public BigDecimal getMoneyExpectIncome1(Long investPersonId);
	/**
	 * 通过投资人ID获取该投资人会员等级
	 * @param score
	 * @return
	 */
	public String getLevelMark(Long score);
	/**
	 * P2P个人客户注册方法
	 * @param bpCustMember
	 * @return
	 */
	public Object[] savePersonCustomer(BpCustMember bpCustMember);
	/**
	 * P2P企业客户注册方法
	 * @param bpCustMember
	 * @return
	 */
	public Object[] saveEnterpriseCustomer(BpCustMember bpCustMember);
	
	/**
	 * 查询注册用户的第三方账户信息
	 * @param bpCustMember
	 * @return
	 */
	public Map<String, String> queryThirdPayCustomerInfo(BpCustMember bpCustMember);
	
	/**
	 * 第三方交易：用户交易资格检查
	 * @param mem
	 * @return
	 */
	public Object[] checkUserDealCondition(BpCustMember mem);
	
	/**
	 * 登陆用户查询：用户资格检查
	 * @param mem
	 * @return
	 */
	public Object[] checkUserCondition(BpCustMember mem);
	/**
	 * 邮箱验证码返回的唯一标识
	 * @param emailcode
	 * @return
	 */
	public BpCustMember getEmailCode(String emailcode);

	
	/**
	 * 查询个人中心 我的债权的统计数据
	 */
	
	public String queryAllLc(Long userId,String loginName);
	
	/**
	 * 查询个人中心我的债权的记录
	 */
	
	public String queryAllDebt(String loginName,HttpServletRequest request); 
	
	/**
	 * 生成json串的通用方法
	 */
	public String CreateJson(Object obj,Integer Num);
	
	/**
	 * 查询我的邀请的信息
	 */
	public String myInvest(BpCustMember member,HttpServletRequest request);

	List<BpCustMember> appMyInvest(BpCustMember member, HttpServletRequest request);


	/**
	 * 统计我的邀请的信息
	 */
	public String myInvestData(BpCustMember member);

	public boolean organiz (String recommendCode);
	
	public Object regCount();

	/**
	 * APP接口查询个人信息
	 *
	 */
	public BpCustMember getUserInfo(Long id);

    Integer checkPhoneNum(String telPhone);

    List<Investproject> getMyInviteInfo(String bidId);

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

	BigDecimal sumMoney(String plainpassword);
}


