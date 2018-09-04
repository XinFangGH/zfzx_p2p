package com.hurong.credit.service.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.mobile.InviteDetail;
import com.hurong.credit.model.mobile.InvitePersonDetail;
import com.hurong.credit.model.webFeedBack.FeedBack;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObSystemAccountDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MD5_T;
import com.hurong.credit.util.validation;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * @author 
 *
 */
public class BpCustMemberServiceImpl extends BaseServiceImpl<BpCustMember> implements BpCustMemberService{
	@SuppressWarnings("unused")
	private BpCustMemberDao dao;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private WebBankcardService webBankcardService;
	@Resource
	private ObSystemAccountDao obSystemAccountDao;
	@Resource
	private PlBidInfoDao plBidInfoDao;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	public BpCustMemberServiceImpl(BpCustMemberDao dao) {
		super(dao);
		this.dao=dao;
	}
	public BpCustMember login(String loginname, String password){
		return dao.login(loginname, password);
	}
	@Override
	public BpCustMember isExist(String loginname) {
		return dao.isExist(loginname);
	}
	@Override
	/**
	 * 判断邮箱是否存在
	 */
	public BpCustMember isExistEmail(String email) {
		return dao.isExistEmail(email);
	}
	@Override
	/**
	 * 判断邀请的推荐码是否存在
	 */
	public BpCustMember isRecommandPerson(String recommandPerson) {
		// TODO Auto-generated method stub
		return dao.isRecommandPerson(recommandPerson);
	}
	/**
	 * 获取所有没被禁用和删除的用户
	 */
	@Override
	public List<BpCustMember> getAllUserful() {
		return dao.getAllUserful();
	}
	
	/**
	 * 将用户邮箱设置为已验证
	 * @return
	 */
	@Override
	public void setCheckEmail(Long id){
		BpCustMember bpCustMember = dao.get(id);
		bpCustMember.setIsCheckEmail("1");
		dao.merge(bpCustMember);
	}
	
	/**
	 * 判断该身份证是否已被其他用户认证
	 */
	@Override
	public BpCustMember isExistCode(String code,Long id) {
		// TODO Auto-generated method stub
		return dao.isExistCode(code, id);
	}
	
	/**
	 * 判断该邮箱是否已被其他用户认证
	 */
	public BpCustMember isExistEmailOther(String email,Long id){
		return dao.isExistEmailOther(email, id);
	}
	
	/**
	 * 判断该手机是否已被其他用户认证
	 */
	public BpCustMember isExistPhoneOther(String telphone,Long id){
		return dao.isExistPhoneOther(telphone, id);
	}
	@Override
	public BpCustMember getMemberByPIdAndFlag(String flag, String pid) {
		return dao.getMemberByPIdAndFlag(flag,pid);
	}
	@Override
	public BpCustMember getMemberUserName(String userName, String cardNum) {
		return dao.getMemberUserName(userName,cardNum);
	}
	@Override
	public BpCustMember getMemberUserName(String thirdPayFlagId) {
		return dao.getMemberUserName(thirdPayFlagId);
	}
	@Override
	public String[] checkUser(BpCustMember member) {
		String[] ret=new String[2];
		StringBuffer buff=new StringBuffer();
		if(member==null){
			buff.append("登录系统");
			buff.append(",");
			ret[0]=Constants.CODE_FAILED;
		}else{
			member =get(member.getId());
		 /*if(member.getCardcode()==null||member.getCardcode().equals("")){
			buff.append("身份证号未维护");
			buff.append(",");
			
		} if(member.getTruename()==null||member.getTruename().equals("")){
			buff.append("请先维护用户真实姓名");
			buff.append(",");
			
		} if(member.getTelphone()==null||member.getTelphone().equals("")){
			buff.append("请先维护用户手机号");
			buff.append(",");
			
		} if(member.getEmail()==null||member.getEmail().equals("")){
			buff.append("请先维护用户邮箱并进行验证");
			buff.append(",");
		}*/		
		String thirdPayType = configMap.get("thirdPayType").toString();
		if(null!=thirdPayType&&"1".equals(thirdPayType)){
			
		}else{
			if(member.getThirdPayFlagId()==null){
				buff.append("请先开通第三方支付,再进行投资。");
				buff.append(",");
			}
		}
		if(buff.length()>0){
			ret[0]=Constants.CODE_FAILED;
		}else {
			buff.append("验证成功！");
			buff.append(",");
			ret[0]=Constants.CODE_SUCCESS;
		}
		}
		buff=buff.deleteCharAt(buff.length()-1);
		ret[1]=buff.toString();
		return ret;
	}
	@Override
	public BpCustMember isExistPhone(String phone) {
		// TODO Auto-generated method stub
		return dao.isExistPhone(phone);
	}
	@Override
	public BpCustMember isExistTelphone(String phone) {
		// TODO Auto-generated method stub
		return dao.isExistPhone(phone);
	}
	@Override
	public BpCustMember getMemberByRet(String ret, String cardNum) {
		String userName = ret.split("_")[1];
		return getMemberUserName(userName, cardNum);
	}
	@Override
	public BpCustMember getMemberByRet(String ret) {
		return getMemberUserName(ret);
	}
	@Override
	public String validateInfo(String userName, String passWord,
			String loginName,String loginPassWord, String telphone, String cardNumber, String email) {
		 StringBuffer result=new StringBuffer("{\"code\":");
	 	  if(userName == null || "".equals(userName)){
		    result.append("\"00\",\"msg\":\"账号为空\"");
	 	  }else if(passWord == null || "".equals(passWord)){
		    result.append("\"01\",\"msg\":\"密码为空\"");
	   	  }else if(!userName.equals(configMap.get("userName").toString()) || !passWord.equals(configMap.get("passWord").toString())){
		    result.append("\"02\",\"msg\":\"账号密码错误\"");
		  }else if (loginName==null||loginName.equals("")){
			  result.append("\"03\",\"msg\":\"登录名为空\"");
		  }else if (loginPassWord==null||loginPassWord.equals("")){
			  result.append("\"04\",\"msg\":\"登录密码为空\"");
		  }else if(telphone==null||telphone.equals("")){
			  result.append("\"05\",\"msg\":\"电话为空\"");
		  }/*else if(cardNumber==null||cardNumber.equals("")){
			  result.append("\"05\",\"msg\":\"身份证为空\"");
		  }*/else if(email==null||email.equals("")){
			  result.append("\"06\",\"msg\":\"电子邮箱为空\"");
		  }else{
			  loginName = StringUtil.html2Text(loginName);
			  telphone = StringUtil.html2Text(telphone);
			  cardNumber = StringUtil.html2Text(cardNumber);
			  email = StringUtil.html2Text(email);
			  if(dao.valadateInfo("loginName", loginName, telphone, cardNumber, email) != null){
				  result.append("\"10\",\"msg\":\"登录名已存在\""); 
			  }else if(dao.valadateInfo("telphone", loginName, telphone, cardNumber, email) != null){
				  result.append("\"11\",\"msg\":\"电话号码已存在\""); 
			  }/*else if(dao.valadateInfo("cardNumber", loginName, telphone, cardNumber, email) != null){
				  result.append("\"12\",\"msg\":\"身份证号码已存在\""); 
			  }*/else if(dao.valadateInfo("email", loginName, telphone, cardNumber, email) != null){
				  result.append("\"13\",\"msg\":\"电子邮箱已存在\""); 
			  }else{
				  BpCustMember menber = new BpCustMember();
				  MD5 md5 = new MD5();
				  //menber.setPassword(md5.md5(loginPassWord, "utf-8"));
				  menber.setPassword(loginPassWord);
				  menber.setLoginname(loginName);
				  menber.setTelphone(telphone);
				  menber.setCardcode(cardNumber);
				  menber.setEmail(email);
				  try {
					  dao.save(menber);
					  result.append("\"88\",\"msg\":\"保存成功！\"");
				  } catch (Exception e) {
					  result.append("\"22\",\"msg\":\"保存失败！\"");
					e.printStackTrace();
				  }
				 
				  
			  }
			 
			  
		  }
	 	 result.append("}");
		// TODO Auto-generated method stub
		return result.toString();
	}
	@Override
	public BpCustMember getMemberByEmail(String email) {
		return dao.getMemberByEmail(email);
	}
	@Override
	public BpCustMember getMemberByPhone(String telphone) {
		return dao.getMemberByPhone(telphone);
	}
	@Override
	public BpCustMember getByThirdPayId(String platformUserNo) {
		// TODO Auto-generated method stub
		return dao.getByThirdPayId(platformUserNo);
	}
	@Override
	public BpCustMember getByQQ(String qq) {
		// TODO Auto-generated method stub
		return dao.getByQQ(qq);
	}
	@Override
	public BpCustMember getBySina(String sina) {
		// TODO Auto-generated method stub
		return dao.getBySina(sina);
	}
	public List<com.hurong.credit.model.user.BpCustMember> getReferrer(String begintime, String endtime){
		return dao.referrer2Excel(begintime, endtime);
	}
	public BpCustMember getInviter(String id){
		return dao.getInviter(id);
	}
	
	

	/**
	 * 通过投资人ID获取该客户托管账户余额
	 * @param investmentPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccountLeft(Long investmentPersonId) {
		return dao.getMoneyAccountLeft(investmentPersonId);
	}
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyBidFrozen(Long investPersonId) {
		return dao.getMoneyBidFrozen(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取客户投标或者购买理财计划过程中，被冻结的资金总额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyMmplanFrozen(Long investPersonId) {
		return dao.getMoneyMmplanFrozen(investPersonId);
	}
	
	
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第一部分：散标投资的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll(Long investPersonId) {
		return dao.getMoneyInvestAll(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人所有的待回收本金和利息(第二部分：理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyInvestAll1(Long investPersonId) {
		return dao.getMoneyInvestAll1(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人待还借款总额(款项计划表中该借款人所有待还款型之和)
	 * @param receiverP2PaccountNumber
	 * @return
	 */
	public BigDecimal getMoneyDueinAll(String receiverP2PaccountNumber) {
		return dao.getMoneyDueinAll(receiverP2PaccountNumber);
	}
	
	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第一部分是散标投资的投资额
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest(Long investPersonId) {
		return dao.getMoneyAccumulativeInvest(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人累计投资总额(客户所有投资的金额，包括投标冻结中的金额，已经起息的投标金额，投资在理财计划中的金额，包括已经完全回款的投资，但是不包括已经投标后来流标解冻的金额)第二部分是理财计划的本金和利息
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeInvest1(Long investPersonId) {
		return dao.getMoneyAccumulativeInvest1(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第一部分散标投资的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen(Long investPersonId) {
		return dao.getCountBidFrozen(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人投标冻结中笔数(第一部分理财计划的投标冻结笔数)
	 * @param investPersonId
	 * @return
	 */
	public BigInteger getCountBidFrozen1(Long investPersonId) {
		return dao.getCountBidFrozen1(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第一部分散标投资的投资笔数)
	 * @param investPersonId
	 * @return
	 * @author hurong
	 */
	public Integer getCountInvestBack(Long investPersonId) {
		return dao.getCountInvestBack(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人待回款投资笔数(第二部分理财计划的投资笔数)
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountInvestBack1(Long investPersonId) {
		return dao.getCountInvestBack1(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人招标中借款笔数
	 * @param investPersonId
	 * @return
	 */
	public Integer getCountBidLoan(String receiverP2PaccountNumber) {
		return dao.getCountBidLoan(receiverP2PaccountNumber);
	}
	
	/**
	 * 通过投资人ID获取该投资人还款中借款笔数
	 * @param investPersonId
	 * 
	 * @return
	 */
	public Integer getCountRepaymentLoan(String receiverP2PaccountNumber) {
		return dao.getCountRepaymentLoan(receiverP2PaccountNumber);
	}
	
	/**
	 * 通过投资人ID获取该投资人累计到账收益(客户账户总计收到的所有利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyAccumulativeIncome(Long investPersonId) {
		return dao.getMoneyAccumulativeIncome(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人累计回收本金
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPreweekIncome(Long investPersonId) {
		return dao.getMoneyPreweekIncome(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人上月到账收益(30天以来，包括第30天，客户账户总计收到的所有利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyPremonthIncome(Long investPersonId) {
		return dao.getMoneyPremonthIncome(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第一部分是散标投资的利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome(Long investPersonId) {
		return dao.getMoneyExpectIncome(investPersonId);
	}
	
	/**
	 * 通过投资人ID获取该投资人预期待收收益(客户账户未来所有的计划利息:第二部分理财计划的本金和利息)
	 * @param investPersonId
	 * @return
	 */
	public BigDecimal getMoneyExpectIncome1(Long investPersonId) {
		return dao.getMoneyExpectIncome1(investPersonId);
	}
	/**
	 * 通过投资人ID获取该投资人会员等级
	 * @param score
	 * @return
	 */
	public String getLevelMark(Long score) {
		return dao.getLevelMark(score);
	}
	/**
	 * P2P企业户注册保存页面
	 * @see com.hurong.credit.service.user.BpCustMemberService#saveEnterpriseCustomer(com.hurong.credit.model.user.BpCustMember)
	 */
	@Override
	public Object[] saveEnterpriseCustomer(BpCustMember bpCustMember) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			//登陆名验证是否可用
			registermsg=this.checkLoginName(bpCustMember.getLoginname());
			if(registermsg[0].equals(Constants.CODE_SUCCESS)){ //通过用户名验证
				//TOOD  密码是否需要验证
				bpCustMember.setLoginname(bpCustMember.getLoginname().toLowerCase());//全部小写
				//验证手机号码可用性
				registermsg=this.checkTelphone(bpCustMember.getTelphone());
				if(registermsg[0].equals(Constants.CODE_SUCCESS)){//通过手机验证
					registermsg=this.saveCustomer(bpCustMember);
					//验证证件号码的可用性
					/*registermsg=this.checkCardCode(bpCustMember.getCardcode());
					if(registermsg[0].equals(Constants.CODE_SUCCESS)){//通过证件号码验证
						registermsg=this.checkOtherNull(bpCustMember);
						if(registermsg[0].equals(Constants.CODE_SUCCESS)){//通过不为空验证
							registermsg=this.saveCustomer(bpCustMember);
						}
					}*/
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="P2P企业客户注册方法出错";
		}
		return registermsg;
	}
	
	/**
	 * P2P个人客户注册保存页面
	 * @see com.hurong.credit.service.user.BpCustMemberService#savePersonCustomer(com.hurong.credit.model.user.BpCustMember)
	 */
	@Override
	public Object[] savePersonCustomer(BpCustMember bpCustMember) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			//登陆名验证是否可用
			registermsg=this.checkLoginName(bpCustMember.getLoginname());
			if(registermsg[0].equals(Constants.CODE_SUCCESS)){ //通过用户名验证
				//TOOD  密码是否需要验证
//				bpCustMember.setLoginname(bpCustMember.getLoginname().toLowerCase());//全部小写
				//验证手机号码可用性
				registermsg=this.checkTelphone(bpCustMember.getTelphone());
				if(registermsg[0].equals(Constants.CODE_SUCCESS)){//通过手机验证
					registermsg=this.saveCustomer(bpCustMember);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="P2P个人客户注册方法出错";
		}
		return registermsg;
	}
	
	/**
	 * 保存P2P用户账户
	 * @param bpCustMember
	 * @return
	 */
	private Object[] saveCustomer(BpCustMember bpCustMember) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			bpCustMember.setIsCheckPhone("1");
			//TOOD   推荐码生成规则  登录名MD5加密
			bpCustMember.setRegistrationDate(new Date());
			MD5_T md1 = new MD5_T();
			bpCustMember.setPlainpassword(md1.md5_3(bpCustMember.getLoginname()));
			if(bpCustMember.getRecommandPerson()!=null&&!"".equals(bpCustMember.getRecommandPerson())){
				BpCustMember recommand=dao.isRecommandPerson(bpCustMember.getRecommandPerson());
				if(recommand!=null){
					bpCustMember.setSecondRecommandPerson(recommand.getRecommandPerson());
				}else{
					bpCustMember.setRecommandPerson(null);
				}
			}
			bpCustMember = dao.save(bpCustMember);
			registermsg[0]=Constants.CODE_SUCCESS;
			registermsg[1]=bpCustMember;
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="用户保存出错";
		}
		return registermsg;
	}
	/**
	 * 企业客户个别字段不允许为空
	 * @param bpCustMember
	 * @return
	 */
	private Object[] checkOtherNull(BpCustMember bpCustMember) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			if(bpCustMember.getTruename()!=null&&!"".equals(bpCustMember.getTruename())){
				if(bpCustMember.getContactPerson()!=null&&!"".equals(bpCustMember.getContactPerson())){
					registermsg[0]=Constants.CODE_SUCCESS;
					registermsg[1]="参数格式正确";
				}else{
					registermsg[0]=Constants.CODE_FAILED;
					registermsg[1]="企业客户注册参数错误：企业联系人不允许为空";
				}
			}else{
				registermsg[0]=Constants.CODE_FAILED;
				registermsg[1]="企业客户注册参数错误：企业名称不允许为空";
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="企业客户注册参数错误";
		}
		return registermsg;
	}
	/**
	 * 验证证件号码的可用性
	 * @param cardcode
	 * @return
	 */
	private Object[] checkCardCode(String cardcode) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			if(cardcode!=null&&!"".equals(cardcode)){
				if(!dao.isExist("cardcode", cardcode)){
					registermsg[0]=Constants.CODE_SUCCESS;
					registermsg[1]="参数格式正确";
				}else{
					registermsg[0]=Constants.CODE_FAILED;
					registermsg[1]="错误：证件号码已经使用，请更换";
				}
			}else{
				registermsg[0]=Constants.CODE_FAILED;
				registermsg[1]="证件号码不允许为空";
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="证件号码参数错误";
		}
		return registermsg;
	}
	/**
	 * 验证手机号可用性
	 * @param loginname
	 * @return
	 */
	private Object[] checkTelphone(String telphone) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			if(telphone!=null&&!"".equals(telphone)){
				if(!dao.isExist("telphone", telphone)){
					String re[]=validation.phoneValidation(telphone);
					if(re[0].equals(Constants.CODE_SUCCESS)){
						registermsg[0]=Constants.CODE_SUCCESS;
						registermsg[1]="参数格式正确";
					}else{
						registermsg[0]=Constants.CODE_FAILED;
						registermsg[1]=re[1];
					}
					
				}else{
					registermsg[0]=Constants.CODE_FAILED;
					registermsg[1]="错误：手机号码已经使用，请更换";
				}
			}else{
				registermsg[0]=Constants.CODE_FAILED;
				registermsg[1]="手机号码不允许为空";
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="手机号码参数错误";
		}
		return registermsg;
	}
	/**
	 * 验证登陆名的可用性
	 * @param loginname
	 * @return
	 */
	private Object[] checkLoginName(String loginname) {
		// TODO Auto-generated method stub
		Object [] registermsg=new Object[2];
		try{
			if(loginname!=null&&!"".equals(loginname)){
				if(!dao.isExist("loginname", loginname)){
					registermsg[0]=Constants.CODE_SUCCESS;
					registermsg[1]="参数格式正确";
				}else{
					registermsg[0]=Constants.CODE_FAILED;
					registermsg[1]="错误：用户名已存在，请更换";
				}
			}else{
				registermsg[0]=Constants.CODE_FAILED;
				registermsg[1]="用户名不允许为空";
			}
		}catch(Exception e){
			e.printStackTrace();
			registermsg[0]=Constants.CODE_FAILED;
			registermsg[1]="用户名参数错误";
		}
		return registermsg;
	}
	
	
	/**
	 * 查询注册用户的第三方账户信息方法：用于页面展示用户信息
	 * (non-Javadoc)
	 * @see com.hurong.credit.service.user.BpCustMemberService#queryThirdPayCustomerInfo(com.hurong.credit.model.user.BpCustMember)
	 */
	@Override
	public Map<String, String> queryThirdPayCustomerInfo(BpCustMember bpCustMember) {
		CommonRequst common = new CommonRequst();
		CommonResponse commonResponse = new CommonResponse();
		Map<String, String> map = new HashMap<String, String>();
		try{
			if(bpCustMember!=null){
				bpCustMember=dao.get(bpCustMember.getId());
				if(bpCustMember.getThirdPayFlagId()!=null&&!"".equals(bpCustMember.getThirdPayFlagId())){
						if(bpCustMember.getThirdPayFlagId()!=null&&!"".equals(bpCustMember.getThirdPayFlagId())){
							String orderNum =ContextUtil.createRuestNumber();
							common.setRequsetNo(orderNum);//请求流水号
							common.setTransactionTime(new Date());//用户查询日期
							common.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());
							common.setThirdPayConfigId0(bpCustMember.getThirdPayFlag0());
							common.setBussinessType(ThirdPayConstants.BT_QUERYUSER);
							common.setTransferName(ThirdPayConstants.TN_QUERYUSER);
							commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);

							String orderNum1 =ContextUtil.createRuestNumber();
							CommonResponse response = new CommonResponse();
							CommonRequst common1 = new CommonRequst();
							common1.setRequsetNo(orderNum1);//请求流水号
							common1.setTransactionTime(new Date());//用户查询日期
							common1.setThirdPayConfigId(bpCustMember.getThirdPayFlagId());//第三方账号
							common1.setBussinessType(ThirdPayConstants.BT_BALANCEQUERY);
							common1.setTransferName(ThirdPayConstants.TN_BALANCEQUERY);
//							response=ThirdPayInterfaceUtil.thirdCommon(common1);
							response.setResponsecode(CommonResponse.RESPONSECODE_SUCCESS);
							if(response.getResponsecode()!=null&&response.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
								if(response.getAvailableAmount()!=null){
									commonResponse.setAvailableAmount(response.getAvailableAmount());
								}
								if(response.getFreezeAmount()!=null){
									commonResponse.setFreezeAmount(response.getFreezeAmount());
								}
								if(response.getBalance()!=null){
									commonResponse.setBalance(response.getBalance());
								}
							}
//							map.put("支付账号：", bpCustMember.getThirdPayFlagId());
						}
						if(commonResponse!=null&&commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
							/*if(commonResponse.getCustMemberType()!=null&&!commonResponse.getCustMemberType().equals("")){
								map.put("支付会员类型：", commonResponse.getCustMemberType());
							}
							if(commonResponse.getCustmemberStatus()!=null&&!commonResponse.getCustmemberStatus().equals("")){
								map.put("支付账号状态 ：", commonResponse.getCustmemberStatus());
								dao.merge(bpCustMember);
							}
							if(commonResponse.getBalance()!=null&&!"".equals(commonResponse.getBalance())){
								map.put("账户余额：", commonResponse.getBalance().toString());
							}
							if(commonResponse.getAvailableAmount()!=null){
								map.put("账户可用余额：", commonResponse.getAvailableAmount().toString());
							}
							if(commonResponse.getFreezeAmount()!=null){
								map.put("账户冻结金额：", commonResponse.getFreezeAmount().toString());
							}
							if(commonResponse.getAutoTender()!=null&&!commonResponse.getAutoTender().equals("")){
								if(commonResponse.getAutoTender().equals("true")){
									map.put("自动投标授权 ：","已授权");
								}else{
									map.put("自动投标授权 ：","未授权");
								}
							}
							if(commonResponse.getBindBankStatus()!=null&&!commonResponse.getBindBankStatus().equals("")){
								map.put("支付银行卡状态：", commonResponse.getBindBankStatus());
								if(commonResponse.getBankName()!=null){
									map.put("支付银行：", commonResponse.getBankName());
								}
								map.put("支付银行卡号：", commonResponse.getBankCode());
							}else{
								map.put("支付银行卡状态：", "尚未绑定银行卡");
							}*/
							//企业户更新激活状态  CustmemberStatus
							if(bpCustMember.getCustomerType()!=null&&bpCustMember.getCustomerType()==1){
								if(commonResponse.getCustmemberStatus()!=null&&commonResponse.getCustmemberStatus().equals("已激活")){
									bpCustMember.setThirdPayStatus(BpCustMember.THIRDPAY_ACCTIVED);//已激活
									bpCustMember.setIsCheckCard("1");
									dao.save(bpCustMember);
								}
							}
							webBankcardService.updateBankList(bpCustMember,commonResponse.getBankCode(),commonResponse.getBankName(),commonResponse.getBankCode());
							HttpServletRequest request = ServletActionContext.getRequest(); 
							request.setAttribute("commonResponse", commonResponse);
//							map.put("availableInvestMoney",commonResponse.getAvailableAmount().toString());
							map.put("availableInvestMoney",commonResponse.getBalance().toString());
							map.put("availableInvestMoney2",commonResponse.getBalance().toString());
							map.put("withdrawBalance",commonResponse.getWithDrawbalance().toString());//可提现金额
						}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
	    }
		return map;
		
    }
	/**
	 * 第三方交易：用户交易资格查询
	 * @see com.hurong.credit.service.user.BpCustMemberService#checkUserDealCondition(com.hurong.credit.model.user.BpCustMember)
	 */
	@Override
	public Object[] checkUserDealCondition(BpCustMember mem) {
		String forwardPage=DynamicConfig.MESSAGE;
		Object[] ret=new Object[3];
		try{
			ret=checkUserCondition(mem);
			if((Boolean) ret[0]){//通过用户基础检查
				mem=dao.get(mem.getId());
				if(mem.getIsCheckPhone()!=null&&"1".equals(mem.getIsCheckPhone())){//完成了手机认证
					if(mem.getIsCheckCard()!=null&&"1".equals(mem.getIsCheckCard())){//完成实名认证
						if(mem.getThirdPayFlagId()!=null&&!"".equals(mem.getThirdPayFlagId())){//是否开通第三方支付账户
							if(mem.getThirdPayStatus()==null||BpCustMember.THIRDPAY_ACCTIVED.equals(mem.getThirdPayStatus())){//第三方账户属于激活账户
								ObSystemAccount ob=obSystemAccountDao.getByInvrstPersonIdAndType(mem.getId(), ObSystemAccount.type0);
								if(ob!=null){
									ret[0]=true;
									ret[1]="用户具备交易资格";
								}else{
									ret[0]=false;
									ret[1]="第三方账户未激活：请前往用户信息第三方支付查看账户状态。";
								}
							}else{
								ret[0]=false;
								ret[1]="第三方账户未激活：请前往用户信息第三方支付查看账户状态。";
							}
						}else{
							ret[0]=false;
							ret[1]="openThirdPay";
						}
						
					}else{
						if(configMap.get("system_authentication_type").toString().equals("thirdPayAuthentication")){//
							ret[0]=false;
							ret[1]="未完成实名认证：请先完成实名认证，如认证请联系管理员。";
						}else{
							ret[0]=false;
							ret[1]="OPENPERSONAUTHENTICATE";
						}
					}
				}else{//手机认证没有完成
					ret[0]=false;
					ret[1]="opentelePthone";
				}
				ret[2]=forwardPage;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=false;
			ret[1]="系统错误，请联系管理员";
			ret[2]=forwardPage;
			
		}
		return ret;
	}
	
	/**
	 * 第三方交易：用户交易资格查询
	 * @see com.hurong.credit.service.user.BpCustMemberService#checkUserDealCondition(com.hurong.credit.model.user.BpCustMember)
	 */
	@Override
	public Object[] checkUserCondition(BpCustMember mem) {
		String forwardPage=DynamicConfig.MESSAGE;
		Object[] ret=new Object[3];
		try{
			if(mem!=null&&mem.getId()!=null&&!"".equals(mem.getId())){//检查对象是否为空
				mem=dao.get(mem.getId());
				if(mem!=null){//数据库是否存在该对象
					if(mem.getIsDelete()==null||mem.getIsDelete().equals(1)){//是否删除
						if(mem.getIsForbidden()==null||mem.getIsForbidden().equals(0)){//是否禁用
							if (mem.getCustomerType() != null && mem.getCustomerType()==1) {
								if (StringUtils.isNotEmpty(mem.getIsCheckCard()) && "2".equals(mem.getIsCheckCard())) {
									ret[0]=false;
									ret[1]="三方账户申请中";
								}else {
									ret[0]=true;
									ret[1]="用户正常";
								}
							}else {
								ret[0]=true;
								ret[1]="用户正常";
							}
						}else{
							ret[0]=false;
							ret[1]="系统错误，用户已被禁用了";
						}
					}else{
						ret[0]=false;
						ret[1]="系统错误，用户已被删除了";
					}
				}else{
					ret[0]=false;
					ret[1]="系统错误，请先登录";
					forwardPage=DynamicConfig.LOGIN;
				}
			}else{//没有登陆或者是Session为空
				ret[0]=false;
				ret[1]="系统错误，请联系管理员";
				forwardPage=DynamicConfig.LOGIN;
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=false;
			ret[1]="系统错误，请联系管理员";
			
		}
		ret[2]=forwardPage;
		return ret;
	}
	@Override
	public BpCustMember getByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getMemberByEmail(email);
	}
	@Override
	public BpCustMember getByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return dao.getByLoginName(loginName);
	}

	@Override
	public BpCustMember getEmailCode(String emailcode) {
		// TODO Auto-generated method stub
		return dao.getEmailCode(emailcode);
	}

	@Override
	public String queryAllJk(String loginName,Long userId, HttpServletRequest request) {
		BpPersonCenterData data = plBidInfoDao.queryAllJk(loginName,userId);
		BigDecimal unBackMoney = dao.getMoneyDueinAll(loginName);
		data.setUnBackMoney(unBackMoney);
		return CreateJson(data,1);
	}

	
	
	@Override
	public String queryAllLc(Long userId,String loginName) {
		BpPersonCenterData data = plBidInfoDao.queryAllLc(userId,loginName);
        return CreateJson(data,1);
	}
	
	
	@Override
	public String queryAllDebt(String loginName,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Integer Nums = dao.queryAllDebtNum(loginName,request).size();
		List<ShowManageMoney> list = dao.queryAllDebt(loginName, request);
		List<PlManageMoneyPlanBuyinfo> list1 = dao.queryEarlyList(loginName, request);
		Integer Nums1 = dao.queryEarlyListNum(loginName, request).size();
		if(request.getParameter("state")==null||"".equals(request.getParameter("state"))){
			list1 = dao.queryEarlyList(loginName, request);
			return CreateJson(list1,Nums1);
		}else{
			return CreateJson(list,Nums);
		}
	}


	@Override
	public String myInvest(BpCustMember member,HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BpCustMember> list = dao.queryMyInvest(member, request);
		List<InvestPersonInfo> moneyList =  dao.getMoneyPeople(member);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < moneyList.size(); j++) {
				if (list.get(i).getId().equals( moneyList.get(j).getInvestId())){
					list.get(i).setSumMoney(moneyList.get(j).getInvestMoney());
				}
			}
		}

		Integer listNum = dao.queryMyInvestNum(member, request).size();
		return CreateJson(list, listNum);
	}

	@Override
	public List<BpCustMember> appMyInvest(BpCustMember member, HttpServletRequest request) {
		return dao.appQueryMyInvest(member, request);
//		List<InvestPersonInfo> moneyList =  dao.appGetMoneyPeople(member);
//		for (int i = 0; i < list.size(); i++) {
//			for (int j = 0; j < moneyList.size(); j++) {
//				if (list.get(i).getId().equals( moneyList.get(j).getInvestId())){
//					list.get(i).setSumMoney(moneyList.get(j).getInvestMoney());
//				}
//			}
//		}
//
//		Integer listNum = dao.queryMyInvestNum(member, request).size();
	}

	@Override
	public String myInvestData(BpCustMember member) {
		// TODO Auto-generated method stub
		BpPersonCenterData data = plBidInfoDao.myRecommend(member);
		return CreateJson(data, 1);
	}
	

	@Override
	public String CreateJson(Object obj,Integer Num) {
        StringBuffer buff1 = new StringBuffer("{\"success\":true,\"totalCounts\":").append(Num).append(",\"result\":");
        JSONSerializer json = JsonUtil.getJSONSerializer();
		json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
		buff1.append(json.serialize(obj));
		buff1.append("}");
		return buff1.toString();
	}
	public boolean organiz (String recommendCode){
		return dao.organiz(recommendCode);
	}
	public Object regCount(){
		return dao.regCount();
	}

	public BpCustMember getUserInfo(Long id) {

		return dao.getUserInfo(id);
	}

	@Override
	public Integer checkPhoneNum(String telPhone) {
		return dao.chenkPhoneNum(telPhone);
	}

	@Override
	public List<Investproject> getMyInviteInfo(String bidId) {
		return dao.getMyInviteInfo(bidId);
	}

	@Override
	public void saveFeedBack(FeedBack feedBack) {
		dao.saveFeedBack(feedBack);
	}

	@Override
	public Object findCode(BpCustMember bpCustMember) {
		return dao.findCode(bpCustMember);
	}

	@Override
	public Object finTel(BpCustMember bpcust) {
		return dao.finTel(bpcust);
	}

	@Override
	public void updateTC(String telphone) {
		dao.updateTC(telphone);
	}

	@Override
	public Object findPhone(String plainCode) {
		return dao.findPhone(plainCode);
	}

	@Override
	public List<InviteDetail> inviteList(String plainCode) {
		return dao.inviteList(plainCode);
	}

	@Override
	public List<InviteDetail> selectInvite(HttpServletRequest request, String plainpassword) {
		return dao.selectInvite(request,plainpassword);
	}

	@Override
	public InviteDetail inviteList1(String id) {
		return dao.inviteList1(id);
	}

	@Override
	public List<InvitePersonDetail> checkInviteDetail(String id) {
		return dao.checkInviteDetail(id);
	}

	@Override
	public Integer countNum(String plainpassword) {
		return dao.countNum(plainpassword);
	}

	@Override
	public BigDecimal sumMoney(String plainpassword) {
		return dao.sunMoney(plainpassword);
	}
}