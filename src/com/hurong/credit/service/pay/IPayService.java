package com.hurong.credit.service.pay;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;

public interface IPayService {
	/**
	 * 转账接口
	 * @param moneyMoreMore 钱多多vo
	 */
	public String transfer(MoneyMoreMore moneyMoreMore,String basePath,HttpServletResponse response);
	/**
	 * 查询接口
	 * moneymoremoreId 查询账户的乾多多标识
	 * type 1.托管账户 2.自有账户
	 * @param moneyMoreMore 钱多多vo
	 */
	public String[] balanceQuery(String moneymoremoreId,String type);
	
	public String balanceQueryMoneys(String moneymoremoreId,String type);
	
	/**
	 * 测试接口删除 注册用户
	 * @param pid
	 * @return
	 */
	public String delAccount (String pid);
	
	public ResultBean loanAuthorize(MoneyMoreMore moneyMoreMore,String basePath,HttpServletResponse response);
	
	/**
	 * 绑定 (已作废)
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	public String bind(MoneyMoreMore moneyMoreMore, String basePath);
	/** 
	 * 注册并绑定 全自动
	 * @return
	 */
	public ResultBean registerAndBind(MoneyMoreMore moneyMoreMore,String basePath);
	/**
	 * 注册并绑定 半自动 返回页面 
	 */
	public void registerAndBind(MoneyMoreMore moneyMoreMore, String basePath,HttpServletResponse response);
	/**
	 * 提现接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	public String withdraws(MoneyMoreMore moneyMoreMore,HttpServletResponse respose, String basePath);
	/**
	 * 充值接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	public String recharge(MoneyMoreMore moneyMoreMore,HttpServletResponse respose, String basePath);
	/**
	 * 转账list
	 * @param moneyMoreMore
	 * @return
	 */
	public MoneyMoreMore loanJsonList(MoneyMoreMore moneyMoreMore);
	/**
	 * 二次转账list
	 * money  收费基数  =投资金额 
	 * reePercent  收费比例
	 * @return
	 */
	public String secondaryJsonList(BigDecimal money,Double reePercent);
	/**
	 * 转账返回信息解析
	 * @param retStr
	 * @return
	 */
	public ResultBean transferNotify(String retStr);
	/**
	 * 资金释放接口
	 * @return
	 */
	public void moneyReaease(MoneyMoreMore moneyMoreMore,String basePath,HttpServletResponse response);

}
