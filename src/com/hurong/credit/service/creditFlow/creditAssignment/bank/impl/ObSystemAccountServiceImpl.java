package com.hurong.credit.service.creditFlow.creditAssignment.bank.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObAccountDealInfoDao;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObSystemAccountDao;
import com.hurong.credit.dao.user.BpCustMemberDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.UUIDUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author 
 *
 */

public class ObSystemAccountServiceImpl extends BaseServiceImpl<ObSystemAccount> implements ObSystemAccountService{
	@SuppressWarnings("unused")
	private ObSystemAccountDao dao;
	@Resource
	private ObAccountDealInfoDao obAccountDealInfoDao;
	@Resource
	private BpCustRelationService bpCustRelationService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	
	@Resource
	private ObSystemAccountDao obSystemAccountDao;
	
	@Resource
	private BpCustMemberDao bpCustMemberDao;
	public ObSystemAccountServiceImpl(ObSystemAccountDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ObSystemAccount> findAccountList(String investName,String accountType,
			HttpServletRequest request, Integer start, Integer limit) {
		return dao.findAccountList(investName,accountType,request,start,limit);
	}

	@Override
	public BigDecimal getBalance(String investPersonId) {
		
		return dao.getBalance(investPersonId);
	}

	@Override
	public ObSystemAccount getByInvrstPersonId(Long investMentPersonId) {
		
		return dao.getByInvrstPersonId(investMentPersonId);
	}
    


	@Override
	public ObSystemAccount getByInvrstPersonIdAndType(Long investPersionId,
			Short investPsersionType) {
		return dao.getByInvrstPersonIdAndType(investPersionId,investPsersionType);
	}

	@Override
	public BigDecimal changeAccountMoney(Long accountId, BigDecimal money,Short direction) {
		// TODO Auto-generated method stub
		try{
			ObSystemAccount account =this.dao.get(accountId);
			if(account!=null){
				if(null!=direction&& direction.equals(ObAccountDealInfo.DIRECTION_INCOME)){//收入
					account.setTotalMoney(account.getTotalMoney().add(money));
				}else if(null!=direction&& direction.equals(ObAccountDealInfo.DIRECTION_PAY)){//支出
					account.setTotalMoney(account.getTotalMoney().subtract(money));
				}
				this.dao.merge(account);
				System.out.println("处理账户信息，账户余额为："+account.getTotalMoney());
				return account.getTotalMoney()==null?new BigDecimal(0):account.getTotalMoney();
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public BigDecimal prefreezMoney(Long accountId, String direction) {
		
		return obAccountDealInfoDao.prefreezMoney(accountId,direction);
	}

	@Override
	public BigDecimal typeTotalMoney(Long userId,Long accountId, String transferType) {
		
		return obAccountDealInfoDao.typeTotalMoney(userId,accountId,transferType);
	}

	@Override
	public BigDecimal[] sumTypeTotalMoney(Long investPeronId, String personType) {
		BpCustMember mem = bpCustMemberDao.get(Long.valueOf(investPeronId));
		
		/*Map<String, String> map = bpCustMemberService.queryThirdPayCustomerInfo(mem);
		String availableInvestMoney = map.get("availableInvestMoney").toString();*/
		
		BigDecimal availableInvestMoney = obSystemAccountDao.getBalance(mem.getId().toString());
		
		
		Long userId=null;
		BpCustRelation bpCustRelation = bpCustRelationService.getP2pCustLoanById(investPeronId);
  	    if(bpCustRelation!=null){
  	    	userId= bpCustRelation.getOfflineCusId();
  	    }
		BigDecimal[] ret=new BigDecimal[12] ;
		BigDecimal flag=new BigDecimal(0);//是否存在系统账户
		BigDecimal totalMoney=new BigDecimal(0);//账户总额
		BigDecimal prefreezMoney =new BigDecimal(0);//账户冻结金额
		BigDecimal availbleMoney =new BigDecimal(0);//账户可用金额
		BigDecimal totalInvestMoney=new BigDecimal(0);//账户累计投资金额
		BigDecimal totalprofitMoney=new BigDecimal(0);//账户累计进账收益
		BigDecimal totalbackPrinMoney=new BigDecimal(0);//账户累计收回本金
		BigDecimal totalRecharge=new BigDecimal(0);//账户累计充值金额
		BigDecimal totalEnchashment=new BigDecimal(0);//账户累计取现金额

		BigDecimal totalLoanMoney=new BigDecimal(0);//累计融资金额
		BigDecimal totalPrincipalRepaymentMoney=new BigDecimal(0);//累计还本息
		BigDecimal totalNotPrincipalRepaymentMoney=new BigDecimal(0); //累计未还本息
		ObSystemAccount account=dao.getByInvrstPersonIdAndType(investPeronId, Short.valueOf(personType));
		if(account!=null){
			flag=new BigDecimal(1);
			totalMoney=account.getTotalMoney();
			prefreezMoney=obAccountDealInfoDao.prefreezMoney(account.getId(),null);
			if(null !=prefreezMoney){
				availbleMoney=account.getTotalMoney().subtract(prefreezMoney);
			}else{
				availbleMoney=totalMoney;
			}
			totalInvestMoney =obAccountDealInfoDao.typeTotalMoney( userId,account.getId(), ObAccountDealInfo.T_INVEST);
			totalprofitMoney =obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_PROFIT);
			totalbackPrinMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_PRINCIALBACK);
			totalRecharge=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_RECHARGE);
			totalEnchashment=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_ENCHASHMENT);
			
			totalLoanMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_INMONEY);
			totalPrincipalRepaymentMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_LOANPAY);
			totalNotPrincipalRepaymentMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_N_REPAYMENT);
		}
		ret[0]=flag;
		ret[1]=totalMoney==null?new BigDecimal(0):totalMoney;
		ret[2]=prefreezMoney==null?new BigDecimal(0):prefreezMoney;
//		ret[3]=availbleMoney==null?new BigDecimal(0):new BigDecimal(availableInvestMoney);
		ret[3]=availableInvestMoney==null?new BigDecimal(0):availableInvestMoney.subtract(prefreezMoney==null?new BigDecimal(0):prefreezMoney);
//		ret[3]=availbleMoney==null?new BigDecimal(0):availableInvestMoney;
		ret[4]=totalInvestMoney==null?new BigDecimal(0):totalInvestMoney;
		ret[5]=totalprofitMoney==null?new BigDecimal(0):totalprofitMoney;
		ret[6]=totalbackPrinMoney==null?new BigDecimal(0):totalbackPrinMoney;
		ret[7]=totalRecharge==null?new BigDecimal(0):totalRecharge;
		ret[8]=totalEnchashment==null?new BigDecimal(0):totalEnchashment;
		
		ret[9]=totalLoanMoney==null?new BigDecimal(0):totalLoanMoney;
		ret[10]=totalPrincipalRepaymentMoney==null?new BigDecimal(0):totalPrincipalRepaymentMoney;
		ret[11]=totalNotPrincipalRepaymentMoney==null?new BigDecimal(0):totalNotPrincipalRepaymentMoney;
		return ret;
	}
	@Override
	public BigDecimal[] checkThirdPartyMoney(Long investPeronId, String personType) {
		BpCustMember mem = bpCustMemberDao.get(Long.valueOf(investPeronId));
		
		Map<String, String> map = bpCustMemberService.queryThirdPayCustomerInfo(mem);
//		String availableInvestMoney = map.get("availableInvestMoney").toString();
		String availableInvestMoney = map.get("availableInvestMoney2").toString();
//		BigDecimal availableInvestMoney = obSystemAccountDao.getBalance(mem.getId().toString());
		
		Long userId=null;
		BpCustRelation bpCustRelation = bpCustRelationService.getP2pCustLoanById(investPeronId);
  	    if(bpCustRelation!=null){
  	    	userId= bpCustRelation.getOfflineCusId();
  	    }
		BigDecimal[] ret=new BigDecimal[12] ;
		BigDecimal flag=new BigDecimal(0);//是否存在系统账户
		BigDecimal totalMoney=new BigDecimal(0);//账户总额
		BigDecimal prefreezMoney =new BigDecimal(0);//账户冻结金额
		BigDecimal availbleMoney =new BigDecimal(0);//账户可用金额
		BigDecimal totalInvestMoney=new BigDecimal(0);//账户累计投资金额
		BigDecimal totalprofitMoney=new BigDecimal(0);//账户累计进账收益
		BigDecimal totalbackPrinMoney=new BigDecimal(0);//账户累计收回本金
		BigDecimal totalRecharge=new BigDecimal(0);//账户累计充值金额
		BigDecimal totalEnchashment=new BigDecimal(0);//账户累计取现金额

		BigDecimal totalLoanMoney=new BigDecimal(0);//累计融资金额
		BigDecimal totalPrincipalRepaymentMoney=new BigDecimal(0);//累计还本息
		BigDecimal totalNotPrincipalRepaymentMoney=new BigDecimal(0); //累计未还本息
		ObSystemAccount account=dao.getByInvrstPersonIdAndType(investPeronId, Short.valueOf(personType));
		if(account!=null){
			flag=new BigDecimal(1);
			totalMoney=account.getTotalMoney();
			prefreezMoney=obAccountDealInfoDao.prefreezMoney(account.getId(),null);
			if(null !=prefreezMoney){
				availbleMoney=account.getTotalMoney().subtract(prefreezMoney);
			}else{
				availbleMoney=totalMoney;
			}
			totalInvestMoney =obAccountDealInfoDao.typeTotalMoney( userId,account.getId(), ObAccountDealInfo.T_INVEST);
			totalprofitMoney =obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_PROFIT);
			totalbackPrinMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_PRINCIALBACK);
			totalRecharge=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_RECHARGE);
			totalEnchashment=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_ENCHASHMENT);
			
			totalLoanMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_INMONEY);
			totalPrincipalRepaymentMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_LOANPAY);
			totalNotPrincipalRepaymentMoney=obAccountDealInfoDao.typeTotalMoney(userId,account.getId(), ObAccountDealInfo.T_N_REPAYMENT);
		}
		ret[0]=flag;
		ret[1]=totalMoney==null?new BigDecimal(0):totalMoney;
		ret[2]=prefreezMoney==null?new BigDecimal(0):prefreezMoney;
		ret[3]=availbleMoney==null?new BigDecimal(0):new BigDecimal(availableInvestMoney);
//		ret[3]=totalMoney==null?new BigDecimal(0):totalMoney;
		
		ret[4]=totalInvestMoney==null?new BigDecimal(0):totalInvestMoney;
		ret[5]=totalprofitMoney==null?new BigDecimal(0):totalprofitMoney;
		ret[6]=totalbackPrinMoney==null?new BigDecimal(0):totalbackPrinMoney;
		ret[7]=totalRecharge==null?new BigDecimal(0):totalRecharge;
		ret[8]=totalEnchashment==null?new BigDecimal(0):totalEnchashment;
		
		ret[9]=totalLoanMoney==null?new BigDecimal(0):totalLoanMoney;
		ret[10]=totalPrincipalRepaymentMoney==null?new BigDecimal(0):totalPrincipalRepaymentMoney;
		ret[11]=totalNotPrincipalRepaymentMoney==null?new BigDecimal(0):totalNotPrincipalRepaymentMoney;
		return ret;
	}
	@Override
	public String[] saveAccount(String companyId, String investName,
			String investId, String cardNumber, String money, String type) {
		String[] str=new String[2];
		String ret="";
		String msg="";
		try{
			Boolean isExit =dao.isExist("accountNumber", cardNumber+"-"+type);
			if(isExit){
				ret=Constants.CODE_FAILED;
				msg="当前投资人身份证已经注册了系统账户";
			}else{
				ObSystemAccount obSystemAccount = new ObSystemAccount();
				obSystemAccount.setCompanyId(Long.valueOf(companyId));
				obSystemAccount.setAccountName(investName);
				obSystemAccount.setInvestmentPersonId(Long.valueOf(investId));
				obSystemAccount.setAccountNumber(cardNumber+"-"+type);// 当前默认系统虚拟账户是投资人的身份证号码
				obSystemAccount.setTotalMoney(new BigDecimal(money));
				obSystemAccount.setInvestPersonName(investName);
				obSystemAccount.setInvestPersionType(Short.valueOf(type));// 1 线下客户
				if(dao.isExist("accountNumber", cardNumber+"-"+type)){
					ret=Constants.CODE_SUCCESS;
					msg="系统账户已经存在！";
				}else{
					dao.save(obSystemAccount);
					ret=Constants.CODE_SUCCESS;
					msg="系统账户成功保存！";
				}
				
			}
			
		}catch(Exception e){
			ret=Constants.CODE_FAILED;
			msg="系统账户保存失败！";
		}
		str[0]=ret;
		str[1]=msg;
		return str;
	}

	@Override
	public BpCustMember getAccountSumMoney(BpCustMember bpCustMember) {
		try{
			if(bpCustMember!=null){
				BigDecimal[] ret =this.sumTypeTotalMoney(bpCustMember.getId(),ObSystemAccount.type0.toString());
				if(ret!=null){
					//if(ret[0].compareTo(new BigDecimal(1))==0){  验证 是否有虚拟账户
						bpCustMember.setTotalMoney(ret[1]);
						bpCustMember.setFreezeMoney(ret[2]);
						bpCustMember.setAvailableInvestMoney(ret[3]);
						bpCustMember.setTotalInvestMoney(ret[4]);
						bpCustMember.setAllInterest(ret[5]);
						bpCustMember.setPrincipalRepayment(ret[6]);
						bpCustMember.setTotalRecharge(ret[7]);
						bpCustMember.setTotalEnchashment(ret[8]);
						
						bpCustMember.setTotalLoanMoney(ret[9]);
						bpCustMember.setTotalPrincipalRepaymentMoney(ret[10]);
						bpCustMember.setTotalNotPrincipalRepaymentMoney(ret[11]);
						
					//}
				}
			}
			return bpCustMember;
		}catch(Exception e){
			e.printStackTrace();
			return bpCustMember;
		}
		
	}

	@Override
	public List<ObSystemaccountSetting> findObSystemaccountSetting() {
		// TODO Auto-generated method stub
		return dao.findObSystemaccountSetting();
	}

	@Override
	public String[] saveAccount(String investId, String type) {
		String[] str=new String[2];
		String ret="";
		String msg="";
		try{
			//ObSystemAccount oldAccount=dao.getByInvrstPersonId(Long.valueOf(investId));
			ObSystemAccount oldAccount=dao.getByInvrstPersonIdAndType(Long.valueOf(investId), Short.valueOf(type));
			if(null!=oldAccount){
				ret=Constants.CODE_FAILED;
				msg="当前投资人身份证已经注册了系统账户";
			}else{
				ObSystemAccount obSystemAccount = new ObSystemAccount();
				obSystemAccount.setCompanyId(Long.valueOf("1"));
				obSystemAccount.setInvestmentPersonId(Long.valueOf(investId));
			//	obSystemAccount.setAccountNumber(UUID.randomUUID().toString());// 当前默认系统虚拟账户为系统生成的随机数
				obSystemAccount.setAccountNumber(UUIDUtils.getUUID());// 当前默认系统虚拟账户为系统生成的随机数
				obSystemAccount.setTotalMoney(new BigDecimal(0));
				obSystemAccount.setInvestPersionType(Short.valueOf(type));// 1 线下客户
				if(null!=oldAccount){
					ret=Constants.CODE_SUCCESS;
					msg="系统账户已经存在！";
				}else{
					System.out.println("111");
					dao.save(obSystemAccount);
					System.out.println("222");
					ret=Constants.CODE_SUCCESS;
					msg="系统账户成功保存！";
				}
				
			}
			
		}catch(Exception e){
			ret=Constants.CODE_FAILED;
			msg="系统账户保存失败！";
		}
		str[0]=ret;
		str[1]=msg;
		return str;
	}
}