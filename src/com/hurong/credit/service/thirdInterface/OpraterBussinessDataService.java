package com.hurong.credit.service.thirdInterface;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;

public interface OpraterBussinessDataService {
	
	/**
	 * 开通第三方支付接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] regedit(Map<String,String> map);

	/**
	 * 开通第三方支付接收到回调函数，企业实名认证
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] regeditBus(Map<String,String> map);
	
	/**
	 * 第三方支付绑卡接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] bandCard(Map<String,String> map);
	
	/**
	 * 第三方支付充值接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] recharge(Map<String,String> map);
	/**
	 * 第三方支付取现接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] withDraw(Map<String,String> map);
	/**
	 * 第三方支付取现退回接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] withDrawReturn(Map<String,String> map);
	/**
	 * 第三方支付投标（冻结账户金额）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] biding(Map<String,String> map);
	/**
	 * 第三方支付取消投标（解冻冻结金额）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] cancelbiding(Map<String,String> map); 
	/**
	 * 第三方支付自动投标授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * Map<String ,String> map=new HashMap<String,String>();
	 * map.gett("custermemberId");
	 * map.get("custmerType");
	 * map.get("requestNo");
	 * @return
	 */
	public String[] bidingAuthorization(Map<String,String> map);
	/**
	 * 双乾授权回调
	 * @param map
	 * @return
	 */
	public String[] mmmoreLoanAuthorize(Map<String,String> map);
	
	/**
	 * 第三方支付投标放款接口（转账）接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] loan(Map<String,String> map);
	/**
	 * 第三方支付自动还款授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] repaymentAuthorization(Map<String,String> map);
	/**
	 * 第三方支付客户自助还款接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] repayment(Map<String,String> map);
	/**
	 * 第三方支付取消绑卡
	 * @param map
	 * @return
	 */
	public String[] cancelBindBank(Map<String, String> map);

	/**
	 * 第三方支付企业取消绑卡
	 * @param map
	 * @return
	 */
	public String[] cancelBindBankCard(Map<String, String> map);
	/**
	 * 易宝第三方支付债权交易接口处理业务数据方法
	 * @param map
	 * @return
	 */
	public String[] doObligationDeal(Map<String, String> map);
	/**
	 *购买债权成功，继续调用审核接口
	 * @param map
	 * @return
	 */
	public String[] yeepayObligationDeal(Map<String, String> map);
	/**
	 * 取消挂牌交易
	 * @param map
	 * @return
	 */
	public String[] cancelPlbidSale(Map<String, String> map);
	/**
	 * 易宝第三方支付通用转账挂牌业务处理方法
	 * @param map
	 * @return
	 */
	public String[] doObligationPublish(Map<String, String> map);
	/**
	 * 易宝第三方支付处理
	 * @param map
	 * @return
	 */
	public String[] bidMoneyPlan(Map<String, String> map);
	/**
	 * 第三方支付关闭自动投标授权接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * Map<String ,String> map=new HashMap<String,String>();
	 * map.gett("custermemberId");
	 * map.get("custmerType");
	 * map.get("requestNo");
	 * @return
	 */
	public String[] closeBidingAuthorization(Map<String, String> map);
	/**
	 * 第三方支付自动还款授权接收到回调函数，处理业务数据方法
	 *  @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("ThirdPayFlagId");商户号
	 */
	public String[] rAuthorization(Map<String, String> map);

	public void checkCouponsIntent(String bidPlanId, String payintentPeriod,String requestNo,String basePath);
	
	/**
	 * 站岗资金购买理财产品业务处理方法
	 * @param cardNo
	 * @return
	 */
	public Object[] doFianceProductBuy(Map<String, String> map);

	/**
	 *  修改手机号码
	 * @param map
	 */
	public void chageMobile(Map<String, String> map);
	/**
	 * 代偿还款
	 * @param cardNo
	 * @param short1
	 * @return
	 */
	public Object[] doCompensatory(Map<String, String> map);
	
	public Object[] repaymentAuthorizationMoneyPlan(Map<String, String> map);
	
	/**
	 *  更换银行卡
	 * @param map
	 */
	public Object[] replaceCard(Map<String, String> map);
	/**
	 * 联动优势授权回调
	 * @param map
	 * @return
	 */
	public String[] umpayLoanAuthorize(Map<String, String> map);
	/**
	 * 联动优势还款回调
	 * @param map
	 * @return
	 */
	public String[] umpayRepayment(Map<String, String> map);
	/**
	 * 易宝2.0还款回调
	 * @param map
	 * @return
	 */
	public String[] yeepayRepayment(Map<String, String> map);
	/**
	 * 联动优势提前还款回调
	 * @param map
	 * @return
	 */
	public String[] umpayPreRepayment(Map<String, String> map);
	/**
	 * 担保公司担保代偿收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * @return
	 */
	public String[] compensatory(Map<String,String> map);
	/**
	 * 担保公司代偿申请成功，继续调用审核接口
	 * @param map
	 * @return
	 */
	public String[] yeepayCompensatory(Map<String,String> map);
	/**
	 * 第三方支付个人理财顾问自助还款接收到回调函数，处理业务数据方法
	 * @param map  依据不同的第三方传回的数据标识进行归纳map中的数值
	 * map.get("orderNo");标的编号
	 * map.get("orderType");标的类型
	 * map.fet("requestNo");还款交易的流水号
	 * @return
	 */
	public String[] financialRepayment(Map<String, String> map);
	
	public void checkCouponsFinancialIntent(String mmPlanId,Date intentDate, String requestNo, String basePath);
	
	public void couponFinancialIntent(List<PlMmOrderAssignInterest> bp, PlManageMoneyPlan bidplan,String transferType,String basePath);
	/**
	 * 投资人提前赎回审核通过后业务处理
	 * @param map
	 * @return
	 */
	public String[] advanceFinancialRepayment(Map<String, String> map);
	
	public String[] earlyEarlyRepayment(Map<String, String> map) ;


	/**
	 *企业信息变更接口
	 * @param map
	 */
    public void modifyEnterprise(Map<String, String> map);
}
