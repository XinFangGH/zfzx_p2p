package com.hurong.credit.service.creditFlow.creditAssignment.bank;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */

public interface ObSystemAccountService extends BaseService<ObSystemAccount>{

//根据投资人id查看系统虚拟账户
	public ObSystemAccount getByInvrstPersonId(Long investMentPersonId);

	/**查询投资人剩余额*/
	public BigDecimal getBalance(String investPersonId);
	/**
	 * 通过 接口 增加虚拟账户
	 * @param companyId  机构ID
	 * @param investName  投资人姓名
	 * @param investId  投资人ID
	 * @param cardNumber  虚拟账号 
	 * @param money   账户金额
	 * @param type   类型  0 线上  1 线下
	 * @return String[]
	 */
	
	public String[] saveAccount(String companyId,String investName,String investId,String cardNumber,String money,String type);
   /**
    * 根据类别 和  投资人ID查询
    * @param investPersionId
    * @param investPsersionType
    * @return ObSystemAccount
    */
	public ObSystemAccount getByInvrstPersonIdAndType(Long investPersionId,Short investPsersionType);
	
	/**
	 * 改变系统账户金额
	 * @param accountId
	 * @param money
	 * @param direction
	 * @return BigDecimal 账户金额发生改变后的金额
	 */
	public BigDecimal changeAccountMoney(Long accountId,BigDecimal money,Short direction);
	
	/**
	 * 查询系统账户预冻结金额
	 * @param accountId 系统账户ID
	 * @param direction 冻结金额类型，2取现 4投资 null表示全部冻结类型 
	 * @return BigDecimal
	 */
	
	public  BigDecimal prefreezMoney(Long accountId,String direction);
	/**
	 * 
	 * @param investName
	 * @param accountType
	 * @param request
	 * @param start
	 * @param limit
	 * @return ObSystemAccount
	 */
	
	public List<ObSystemAccount> findAccountList(String investName,String accountType,HttpServletRequest request, Integer start, Integer limit);
	/**
	 * 获取各种类型金额的计算方式
	 * @param accountId
	 * @param direction 冻结金额类型，1充值2取现 3收益4投资 5还本
	 * @return
	 */
	
	public BigDecimal typeTotalMoney(Long userId,Long accountId,String transferType);
	
	
	/**
	 * ObSystemAccountService
	 * 获取一个系统账户需要计算的各种金额
	 * @param investPeronId  
	 *  Long userId 借款人id
	 * @param personType
	 * @return BigDecimal[0] 是否有系统账户： 0没有，1有
	 * 		   BigDecimal[1] 系统账户账户总金额： 0  表示没有值
	 * 		   BigDecimal[2] 系统账户预冻结金额： 0  表示没有值
	 * 		   BigDecimal[3] 系统账户可用金额： 0  表示没有值
	 * 		   BigDecimal[4] 系统账户累计投资金额： 0  表示没有值
	 * 		   BigDecimal[5] 系统账户累计进账收益： 0  表示没有值
	 *  	   BigDecimal[6] 系统账户累计收回本金： 0  表示没有值
	 * 	 	   BigDecimal[7] 系统账户累计充值： 0  表示没有值
	 *  	   BigDecimal[8] 系统账户累计取现： 0  表示没有值
	 */
	public BigDecimal[] sumTypeTotalMoney(Long investPeronId,String personType);
	
	/**
	 * 查询第三方的账户余额
	 * */
	public BigDecimal[] checkThirdPartyMoney(Long investPeronId,String personType);
	
	/**
	 * p2p独有的方法 
	 * 用来将系统账户的金额放在线上投资客户的实体内
	 * 方便页面调用
	 * add  by linyan 2014-5-17
	 * @param bpCustMember
	 * Long userId 借款人id
	 * @return
	 */
	public BpCustMember getAccountSumMoney(BpCustMember bpCustMember);
	
	//查询交易类型 
	public List<ObSystemaccountSetting> findObSystemaccountSetting();
	/**
	 * 注册后即  增加虚拟账户
	 * @param investId  投资人ID
	 * @param type   类型  0 线上  1 线下
	 * @return String[]
	 */
	
	public String[] saveAccount(String investId,String type);

	
	
    }


