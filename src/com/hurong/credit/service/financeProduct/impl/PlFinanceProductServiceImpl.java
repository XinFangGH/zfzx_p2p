package com.hurong.credit.service.financeProduct.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.dao.financeProduct.PlFinanceProductDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUseraccountDao;
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.financeProduct.PlFinanceProductService;

/**
 * 
 * @author 
 *
 */
public class PlFinanceProductServiceImpl extends BaseServiceImpl<PlFinanceProduct> implements PlFinanceProductService{
	@SuppressWarnings("unused")
	private PlFinanceProductDao dao;
	@Resource
	private PlFinanceProductUseraccountDao plFinanceProductUseraccountDao;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	private static Map configMap = AppUtil.getConfigMap();
	public PlFinanceProductServiceImpl(PlFinanceProductDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlFinanceProduct> getAllProduct() {
		// TODO Auto-generated method stub
		return dao.getAllProduct();
	}
	/**
	 * 检测是否具备转入资格
	 */
	@Override
	public Object[] checkCondition(BigDecimal amount, Long productId,BpCustMember mem) {
		// TODO Auto-generated method stub
		String msg="";
		String code=Constants.CODE_SUCCESS;
		mem=obSystemAccountService.getAccountSumMoney(mem);
		Object[] ret=new Object[2];
		//判断是否有第三方账号(资金池没有第三方账号)
		Boolean thirdId=configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)?(mem.getThirdPayFlagId()!=null?true:false):true;
		if(thirdId){
			if(amount!=null&&amount.compareTo(new BigDecimal(0))>0){
				PlFinanceProduct pl=dao.get(productId);
				BigDecimal fee=new BigDecimal(0);
				if(pl.getPlateRate()!=null&&pl.getPlateRate().compareTo(new BigDecimal(0))>0){
					fee=amount.multiply(pl.getPlateRate()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
				}
				if(pl!=null){
					if(mem.getAvailableInvestMoney().compareTo(amount.add(fee))>=0){ 
						if(pl.getMinBidMoney()!=null){
							if(amount.compareTo(pl.getMinBidMoney())>=0){
								if(pl.getMaxBidMoney().compareTo(new BigDecimal(0))>0&&amount.compareTo(pl.getMaxBidMoney())>0){
									code=Constants.CODE_FAILED;
									msg="专户理财产品转入金额要小于单笔投资的最大金额";
								}else{
									PlFinanceProductUseraccount plFinanceProductUserAccount=plFinanceProductUseraccountDao.getProductUserAccount(mem.getId(),productId);
									if(plFinanceProductUserAccount!=null){
										if(plFinanceProductUserAccount.getAccountStatus().equals(Short.valueOf("-1"))){
											code=Constants.CODE_FAILED;
											msg="该账户已锁定,不能转入";
										}else{
											code=Constants.CODE_SUCCESS;
											msg="可以转入金额";
										}
									}else{
										code=Constants.CODE_SUCCESS;
										msg="可以转入金额";
									}
									//暂不考虑累计最高金额
									/*PlFinanceProductUseraccount plFinanceProductUseraccount= plFinanceProductUseraccountDao.getPersonAccount(mem.getId());
									if(plFinanceProductUseraccount!=null&&plFinanceProductUseraccount.getI){
										
									}*/
									
								}
							}else{
								code=Constants.CODE_FAILED;
								msg="专户理财产品转入金额要大于起投金额";
							}
						}else{
							code=Constants.CODE_FAILED;
							msg="专户理财产品起投金额";
						}
					}else{
					code=Constants.CODE_FAILED;
					msg="账户可用余额应大于实际扣款金额";
					}
				}else{
					code=Constants.CODE_FAILED;
					msg="没有找到专户理财产品";
				}
				
			}else{
				code=Constants.CODE_FAILED;
				msg="购买金额应该大于0元";
			}
		}else{
			code=Constants.CODE_FAILED;
			msg="客户没有开通第三方支付，请先开通第三方支付功能";
		}
		ret[0]=code;
		ret[1]=msg;
		return ret;
	}
    /**
     * 检测是否具备转出资格
     */
	@Override
	public Object[] checkFromCondition(BigDecimal amount, Long productId,BpCustMember mem) {
		// TODO Auto-generated method stub
		String msg="";
		String code=Constants.CODE_SUCCESS;
		Object[] ret=new Object[2];
		//判断是否有第三方账号
		Boolean thirdId=configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)?(mem.getThirdPayFlagId()!=null?true:false):true;
		PlFinanceProductUseraccount plFinanceProductUseraccount=plFinanceProductUseraccountDao.getProductUserAccount(mem.getId(),productId);
		if(thirdId){
			if(amount!=null&&amount.compareTo(new BigDecimal(0))>0){
				if(plFinanceProductUseraccount.getAccountStatus().equals(Short.valueOf("1"))){
					if(plFinanceProductUseraccount!=null&&plFinanceProductUseraccount.getCurrentMoney().compareTo(amount)>=0){ 
						code=Constants.CODE_SUCCESS;
						msg="可以转出金额";
					}else{
						code=Constants.CODE_FAILED;
						msg="该产品账户金额应大于可转出金额";
					}
				}else{
					code=Constants.CODE_FAILED;
					msg="该产品账户已被锁定，不能转出";
				}
				
			}else{
				code=Constants.CODE_FAILED;
				msg="购买金额应该大于0元";
			}
			
		}else{
			code=Constants.CODE_FAILED;
			msg="客户没有开通第三方支付，请先开通第三方支付功能";
		}
		ret[0]=code;
		ret[1]=msg;
		return ret;
	}

}