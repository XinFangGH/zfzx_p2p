package com.hurong.credit.service.thirdInterface;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aipg.payreq.Trans_Detail;
import com.allinpay.ets.client.RequestOrder;
import com.hurong.credit.model.pay.AllinPay;
import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.thirdInterface.Repayment;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.thirdInterface.Transfer;

public interface AllinPayService {
	/**
	 * 充值接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	public String recharge(RequestOrder requestOrder,HttpServletResponse respose, String basePath);
	/**
	 * 提现接口
	 * @param moneyMoreMore
	 * @param basePath
	 * @return
	 */
	public String[] withdraws(AllinPay allin,HttpServletResponse respose, String basePath);
	/**
	 * 交易结果查询
	 * @param reqSn
	 * @param isfront
	 * @param URL11https
	 * @return
	 */
	public String[] queryResult(String reqSn,boolean isfront,String URL11https);
}
