package com.hurong.credit.service.creditFlow.auto.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.dao.creditFlow.auto.PlBidAutoDao;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.thirdInterface.YeePayReponse;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.financingAgency.typeManger.PlKeepCreditlevelService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.Common;

public class PlBidAutoServiceImpl extends BaseServiceImpl<PlBidAuto> implements PlBidAutoService {

	private PlBidAutoDao dao;
	@Resource
	private YeePayService yeePayService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private PlKeepCreditlevelService plKeepCreditlevelService;
	public PlBidAutoServiceImpl(PlBidAutoDao dao){
		super(dao);
		this.dao=dao;
	}
	@Override
	public Integer getOrderNum() {
		return dao.getOrderNum();
	}
	@Override
	public PlBidAuto getPlBidAuto(Long userId) {
		return dao.getPlBidAuto(userId);
	}
	@Override
	public boolean isUpdate(PlBidAuto auto) {
		return dao.isUpdate(auto);
	}
	@Override
	public List<String> getCreditlevel() {
		return dao.getCreditlevel();
	}
	/**
	 * 检查是否能够满足开通自动投标的条件
	 *  update by linyan
	 *  updateDate 2015-4-22
	 */
	@Override
	public String[] chk(Long userid) {
		BpCustMember mem=bpCustMemberService.get(userid);
		String[] str=new String[2];
		PlBidAuto auto=dao.getPlBidAuto(userid);
		int rateStartOrder=0;
		int rateEndOrder=0;
		if(auto.getRateStart()!=null){//自动投标投标利率上限
			rateStartOrder=Integer.valueOf(plKeepCreditlevelService.get(Long.valueOf(auto.getRateStart())).getKeyStr());
		}
		if(auto.getRateEnd()!=null){//自动投标投标利率下线
			rateEndOrder=Integer.valueOf(plKeepCreditlevelService.get(Long.valueOf(auto.getRateEnd())).getKeyStr());
		}
		if(auto.getBanned().equals(PlBidAuto.ISBANNED)){//是否允许设置了使用自动投标开通功能
			str[0]=Constants.FAILDFLAG;
			str[1]="开通自动投标功能被禁用，请联系管理员。";
		}else if(auto.getBidMoney().compareTo(new BigDecimal(PlBidAuto.MAXBIDMONEY))<0){
			str[0]=Constants.FAILDFLAG;
			str[1]="每次投标金额必须大于"+PlBidAuto.MAXBIDMONEY+"元。";
		}else if(!isDivide(auto.getBidMoney(),PlBidAuto.DIVIDEMONEY)){
			str[0]=Constants.FAILDFLAG;
			str[1]="每次投标金额必须能整除"+PlBidAuto.DIVIDEMONEY+"。";
		}else if(auto.getInterestStart()<PlBidAuto.ISTART||auto.getInterestEnd()>PlBidAuto.IEND){
			str[0]=Constants.FAILDFLAG;
			str[1]="利率范围应该在"+PlBidAuto.ISTART+"%至"+PlBidAuto.IEND+"%之间，填写的数值必须是正整数。";
		}else if(auto.getPeriodStart()<PlBidAuto.PSTART||auto.getInterestEnd()>PlBidAuto.PEND){
			str[0]=Constants.FAILDFLAG;
			str[1]="借款期限应该在"+PlBidAuto.PSTART+"到"+PlBidAuto.PEND+"个月以内。";
		}else if(auto.getKeepMoney().compareTo(new BigDecimal(0))<0){
			str[0]=Constants.FAILDFLAG;
			str[1]="账户保留金额不能为负值，必须是0或者正整数。";
		}else if(rateEndOrder>rateStartOrder){
			str[0]=Constants.FAILDFLAG;
			str[1]="信用等级范围不能低于下限";
		}else{
			BigDecimal totmoney=null;
			if(AppUtil.getSysConfig().get("thirdPayType").equals("0")){
				totmoney=new BigDecimal(10000);//临时测试
			//	totmoney=userMoney(mem);
			}else {
				totmoney=obSystemAccountService.getAccountSumMoney(mem).getAvailableInvestMoney();
			}
			if(totmoney.compareTo(new BigDecimal(PlBidAuto.TOTALMONEY))<0){
				str[0]=Constants.FAILDFLAG;
				str[1]="账户余额必须大于"+PlBidAuto.TOTALMONEY+"元。";
			}else{
				str[0]=Constants.SUCCESSFLAG;
				str[1]="您正在开通自动投标功能。";
			}
		}
		return str;
	}
	
	@Override
	public String savechk(PlBidAuto auto) {
		StringBuffer bf=new StringBuffer("{");
		int rateStartOrder=0;
		int rateEndOrder=0;
			if(auto.getRateStart()!=null){
				rateStartOrder=Integer.valueOf(plKeepCreditlevelService.get(Long.valueOf(auto.getRateStart())).getKeyStr());
			}
			if(auto.getRateEnd()!=null){
				rateEndOrder=Integer.valueOf(plKeepCreditlevelService.get(Long.valueOf(auto.getRateEnd())).getKeyStr());
			}
		 if(auto.getBidMoney().compareTo(new BigDecimal(PlBidAuto.MAXBIDMONEY))<0||!isDivide(auto.getBidMoney(),PlBidAuto.DIVIDEMONEY)){
			bf.append("\"bidMoneycode\":");
			bf.append("\""+Constants.FAILDFLAG+"\",\"bidMoney\":");
			bf.append("\"每次投标金额必须大于"+PlBidAuto.MAXBIDMONEY+"元。且必须能整除"+PlBidAuto.DIVIDEMONEY+"。\",");
		 }else{
			 bf.append("\"bidMoneycode\":");
			 bf.append("\""+Constants.SUCCESSFLAG+"\",\"bidMoney\":");
			 bf.append("\"\","); 
		 }
		 
		 if(auto.getInterestStart()<PlBidAuto.ISTART||auto.getInterestEnd()>PlBidAuto.IEND){
				bf.append("\"interestcode\":");
				bf.append("\""+Constants.FAILDFLAG+"\",\"interest\":");
				bf.append("\"利率范围应该在"+PlBidAuto.ISTART+"%至"+PlBidAuto.IEND+"%之间，填写的数值必须是正整数。\",");
			 }else{
				 bf.append("\"interestcode\":");
				 bf.append("\""+Constants.SUCCESSFLAG+"\",\"interest\":");
				 bf.append("\"\","); 
			 }
		 
		 if(auto.getPeriodStart()<PlBidAuto.PSTART||auto.getInterestEnd()>PlBidAuto.PEND){
				bf.append("\"periodcode\":");
				bf.append("\""+Constants.FAILDFLAG+"\",\"period\":");
				bf.append("\"借款期限应该在"+PlBidAuto.PSTART+"到"+PlBidAuto.PEND+"个月以内。\",");
			 }else{
				 bf.append("\"periodcode\":");
				 bf.append("\""+Constants.SUCCESSFLAG+"\",\"period\":");
				 bf.append("\"\","); 
			 }
		 
		 if(auto.getKeepMoney().compareTo(new BigDecimal(0))<0){
				bf.append("\"keepMoneycode\":");
				bf.append("\""+Constants.FAILDFLAG+"\",\"keepMoney\":");
				bf.append("\"账户保留金额不能为负值，必须是0或者正整数。\",");
			 }else{
				 bf.append("\"keepMoneycode\":");
				 bf.append("\""+Constants.SUCCESSFLAG+"\",\"keepMoney\":");
				 bf.append("\"\","); 
			 }
		 if(rateEndOrder>rateStartOrder){
				bf.append("\"ratecode\":");
				bf.append("\""+Constants.FAILDFLAG+"\",\"rate\":");
				bf.append("\"信用等级范围不能低于下限\",");
			 }else{
				 bf.append("\"ratecode\":");
				 bf.append("\""+Constants.SUCCESSFLAG+"\",\"rate\":");
				 bf.append("\"\","); 
			 }
		if(bf.length()>1){
			bf.deleteCharAt(bf.length() - 1);
		}
		bf.append("}");
		return bf.toString();
	}
	/**
	 * 获取第三方帐户余额
	 * @param mem
	 * @return
	 */
	private BigDecimal userMoney(com.hurong.credit.model.user.BpCustMember mem){
		BigDecimal totalMoney=new BigDecimal(0);
		//调用投标接口
		if(mem!=null&&mem.getThirdPayFlagId()!=null){
			/**(11)注册用户查询接口
		     * Map<String,Object> map  第三方支付注册用户查询需要的map参数
			 * map.get("platformUserNo").toString() 第三方支付账号
			 * map.get("requestNo").toString()交易流水号
			 * @return
			 */
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("requestNo", Common.getRandomNum(3)+ DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss"));
			map.put("platformUserNo", mem.getThirdPayFlagId());
			Object[] o=yeePayService.registerQuery(map);
			if(o!=null&&o[0].equals(Constants.SUCCESSFLAG)){
				YeePayReponse yp=(YeePayReponse)o[1];
				totalMoney=new BigDecimal(yp.getAvailableAmount());
			}
		}
		return totalMoney;
	}
	
	private boolean isDivide(BigDecimal a,Integer b){

		double ab = (Double.valueOf(a.toString()) * 100)
				% (Double.valueOf(b.toString()) * 100);
		if (ab == 0) {
			return true;
		}else{
			return false;
		}
	
	}
	@Override
	public PlBidAuto getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByRequestNo(requestNo);
	}
	@Override
	public PlBidAuto getByThirdPayConfigId(String ThirdPayConfigId) {
		// TODO Auto-generated method stub
		return dao.getByThirdPayConfigId(ThirdPayConfigId);
	}
}
