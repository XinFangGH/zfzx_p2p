package com.hurong.credit.service.financeProduct.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.hurong.core.Constants;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObAccountDealInfoDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductRateDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUserAccountInfoDao;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUseraccountDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.financeProduct.PlFinanceProductRate;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.financeProduct.PlFinanceProductUseraccount;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.financeProduct.PlFinanceProductUserAccountInfoService;
import com.hurong.credit.service.thirdInterface.OpraterBussinessDataService;
import com.hurong.credit.service.thirdInterface.YeePayService;
import com.hurong.credit.util.Common;
import com.hurong.credit.util.WebClient;
import com.thirdPayInterface.CommonDetail;
import com.thirdPayInterface.CommonRequst;
import com.thirdPayInterface.CommonResponse;
import com.thirdPayInterface.ThirdPayConstants;
import com.thirdPayInterface.ThirdPayInterfaceUtil;

/**
 * 
 * @author 
 *
 */
public class PlFinanceProductUserAccountInfoServiceImpl extends BaseServiceImpl<PlFinanceProductUserAccountInfo> implements PlFinanceProductUserAccountInfoService{
	@SuppressWarnings("unused")
	private PlFinanceProductUserAccountInfoDao dao;
	@Resource
	private PlFinanceProductUseraccountDao plFinanceProductUserAccountDao;
	@Resource
	private PlFinanceProductRateDao plFinanceProductRateDao;
	@Resource
	private PlFinanceProductDao plFinanceProductdao;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
    private OpraterBussinessDataService opraterBussinessDataService;
	@Resource
    private YeePayService yeePayService;
	@Resource
    private ObAccountDealInfoDao obAccountDealInfoDao;
	
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	public PlFinanceProductUserAccountInfoServiceImpl(PlFinanceProductUserAccountInfoDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PlFinanceProductUserAccountInfo> getListByParamet(
			HttpServletRequest request, PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getListByParamet(request,pb);
	}

	//正常生成每日派息数据(不考虑异常情况)
	@Override
	public boolean creatYestDayIntent(Date date) {
		// TODO Auto-generated method stub
		
		//更新用户的在途金额的状态
		this.updateAccountInfo();
		//添加
		plFinanceProductRateDao.addNowDayRate(date);
		if(date==null){
			date=DateUtil.addDaysToDate(new Date(), -1);
		}
		List<PlFinanceProductUseraccount> list=plFinanceProductUserAccountDao.getUserAccountList(null,null);
	    if(list!=null&&list.size()>0){
	    	for(PlFinanceProductUseraccount temp:list){
	    		if(temp.getIntestMoney().compareTo(new BigDecimal(0))>0&&temp.getYesterdayEarn().compareTo(new BigDecimal(0))<0){
	    			PlFinanceProductRate prate=plFinanceProductRateDao.getRate(temp.getProductId(),date);
	    		}
	    	}
	    }
		
		
		
		return false;
	}
	//@Override
	public void updateAccountInfo(){
		dao.updateAccountInfo();
	}

	@Override
	public List<PlFinanceProductUserAccountInfo> getPersonList(HttpServletRequest request,Long id,int i,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return dao.getPersonList(request,id,i,pageSize);
	}
	
	//购买专户理财产品方法
	@Override
	public Object[] buyRecord(BigDecimal amount, Long productId,BpCustMember mem,Boolean isMobile) {
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			PlFinanceProduct pl=plFinanceProductdao.get(productId);
			PlFinanceProductUseraccount plFinanceProductUserAccount=plFinanceProductUserAccountDao.getProductUserAccount(mem.getId(),productId);
			if(plFinanceProductUserAccount==null){//生成对应产品的购买账户
				PlFinanceProductUseraccount p=new PlFinanceProductUseraccount();
				p.setUserId(mem.getId());
				p.setUserloginName(mem.getLoginname());
				p.setUserName(mem.getTruename());
				p.setProductId(productId);
				p.setProductName(pl.getProductName());
				p.setOpenDate(new Date());
				p.setModifyDate(new Date());
				p.setAccountStatus(Short.valueOf("1"));
				p.setCompanyId(1L);
				plFinanceProductUserAccountDao.save(p);
			}
			String cardNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
			 BigDecimal fee=new BigDecimal(0);
			 if(pl.getPlateRate()!=null&&pl.getPlateRate().compareTo(new BigDecimal(0))>0){
					fee=amount.multiply(pl.getPlateRate()).divide(new BigDecimal(100),2, BigDecimal.ROUND_HALF_UP);
				}
			 Boolean  flag=this.saveInfo(pl,mem,amount,fee,cardNo);
			 if(flag){//生成转入数据成功  调用第三方处理业务数据
				 if(isMobile){
					 ret=this.thirdPayBuyRecord(mem,amount.add(fee),cardNo,true);
				 }else{
					 ret=this.thirdPayBuyRecord(mem,amount.add(fee),cardNo,false);
				 }
			 }else{
				 ret[0]=Constants.CODE_FAILED;
				 ret[1]="操作类型-系统出错,保存购买记录出错";
			 }
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			 ret[1]="操作类型-系统出错";
		}
		
		return ret;
	}

	private Object[] thirdPayBuyRecord(BpCustMember mem,BigDecimal amount,String cardNo,Boolean isMobile) {
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)){//判断是否为托管模式
					 String bidId = Common.getRandomNum(3);
					 CommonRequst cq=new CommonRequst();
					  if(isMobile){
						 cq.setIsMobile("1");
					  }
					 cq.setThirdPayConfigId(mem.getThirdPayFlagId());//用户支付账号
					 cq.setRequsetNo(cardNo);//请求流水号
					 cq.setContractNo("");
					 cq.setTransferName(ThirdPayConstants.TN_HRBIN);//实际业务用途
					 cq.setBussinessType(ThirdPayConstants.BT_HRBIN);//接口类型
					 List<CommonDetail> list=new ArrayList<CommonDetail>();
					 CommonDetail dt=new CommonDetail();
					 dt.setAmount(amount.toString());//转账金额
					 dt.setTargetUserType("plateForm");//收款人用户类型
					 list.add(dt);
					 cq.setDetailList(list);//转账记录
					 cq.setBidId(bidId);//标的号
					 cq.setPlanMoney(amount.multiply(new BigDecimal(1000000)));//标的金额
					 cq.setAmount(amount);//投标金额
					 if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
						cq.setAccountType(1);
					 }else{//借款人是个人
						cq.setAccountType(0);
					 }
					 CommonResponse cr=ThirdPayInterfaceUtil.thirdCommon(cq);
					 if(CommonResponse.RESPONSECODE_APPLAY.equals(cr.getResponsecode())){
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="已经购买调用第三方处理";
					}else if(CommonResponse.RESPONSECODE_SUCCESS.equals(cr.getResponsecode())){
						Map<String,String> map = new HashMap<String, String>();
						map.put("requestNo", cardNo);
						map.put("dealRecordStatus", ObAccountDealInfo.DEAL_STATUS_2.toString());
						opraterBussinessDataService.doFianceProductBuy(map);
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="互融宝转入申请成功";
					}else{
						ret[0]=Constants.CODE_FAILED;
						ret[1]="申请失败";
						PlFinanceProductUserAccountInfo pinfo=dao.get("requestNo", cardNo);
						dao.remove(pinfo);
					}
			}else{//其他是资金池模式
				//ret=opraterBussinessDataService.doFianceProductBuy(cardNo,Short.valueOf("2"));	
				
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			 ret[1]="操作类型-系统出错";
		}
		return ret;
		
		
	}
  
	//保存转入转出交易记录
	private Boolean saveInfo(PlFinanceProduct pl, BpCustMember mem,BigDecimal amount,BigDecimal fee,String cardNo) {
		// TODO Auto-generated method stub
		try{
			PlFinanceProductUseraccount plFinanceProductUserAccount=plFinanceProductUserAccountDao.getPersonAccount(mem.getId());
			//账户余额
			BigDecimal money=amount.add(plFinanceProductUserAccount.getCurrentMoney());
		   //初始化数据没有状态，查询也不查询
			PlFinanceProductUserAccountInfo pinfo=new PlFinanceProductUserAccountInfo();
			pinfo.setUserId(mem.getId());
			pinfo.setAmont(amount);
			pinfo.setCurrentMoney(money);
			pinfo.setProductId(pl.getId());
			pinfo.setPlateFee(fee);
			if(amount.compareTo(new BigDecimal(0))<0){
				pinfo.setDealtype("2");//交易类型
				pinfo.setDealtypeName("转出");
				pinfo.setRemark("初始化数据");
				pinfo.setRequestNo(cardNo);
			}else{
				pinfo.setDealtype("1");//交易类型
				pinfo.setDealtypeName("转入");
				pinfo.setPlateFeeRate(pl.getPlateRate()!=null?pl.getPlateRate():new BigDecimal(0));
				pinfo.setRemark("初始化数据");
				
				pinfo.setRequestNo(cardNo);
			}
			
			pinfo.setCompanyId(1L);
			dao.save(pinfo);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 转出站岗资金账户金额业务方法及第三方调用
	 */
	@Override
	public Object[] transferFromPlate(BigDecimal amount, Long productId,
			BpCustMember mem) {
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			PlFinanceProduct pl=plFinanceProductdao.get(productId);
			String cardNo =ContextUtil.createRuestNumber();//生成第三需要的流水号
			BigDecimal fee =new BigDecimal(0);
			 Boolean  flag=this.saveInfo(pl,mem,amount.multiply(new BigDecimal(-1)),fee,cardNo);
			 if(flag){//生成转入数据成功  调用第三方处理业务数据
				 ret=this.thirdPayTransfromPlate(mem,amount,cardNo);
			 }else{
				 ret[0]=Constants.CODE_FAILED;
				 ret[1]="操作类型-系统出错,保存购买记录出错";
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}

	private Object[] thirdPayTransfromPlate(BpCustMember mem,BigDecimal amount, String cardNo) {
		// TODO Auto-generated method stub
		Object[] ret=new Object[2];
		try{
			if(configMap.get("thirdPayType").toString().equals(Constants.THIRDPAY_FLG0)){//判断是否为托管模式
					String bidId = Common.getRandomNum(3);
					CommonRequst commonRequest = new CommonRequst();
					commonRequest.setThirdPayConfigId(mem.getThirdPayFlagId());//用户第三方标识
					commonRequest.setRequsetNo(cardNo);//请求流水号
					commonRequest.setAmount(amount);//交易金额
					commonRequest.setBidId(bidId);
					commonRequest.setLoaner_thirdPayflagId(mem.getThirdPayFlagId());
					commonRequest.setTransferName(ThirdPayConstants.TN_HRBOUT);//业务用途
					commonRequest.setBussinessType(ThirdPayConstants.BT_HRBOUT);//业务类型
					if(mem.getCustomerType()!=null&&mem.getCustomerType().equals(BpCustMember.CUSTOMER_ENTERPRISE)){//判断是企业
						commonRequest.setAccountType(1);
					}else{//借款人是个人
						commonRequest.setAccountType(0);
					}
					CommonResponse commonResponse=ThirdPayInterfaceUtil.thirdCommon(commonRequest);
					if(commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
						ret=this.doOprateTransferFromPlate(cardNo,Short.valueOf("2"));	
					}else{
						ret=this.doOprateTransferFromPlate(cardNo,Short.valueOf("3"));	
					}
			}else{//其他是资金池模式
				ret=this.doOprateTransferFromPlate(cardNo,Short.valueOf("2"));	
				
			}
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			 ret[1]="操作类型-系统出错";
		}
		return ret;
		
		
	
	}
	//资金转出操作方法
	private Object[] doOprateTransferFromPlate(String cardNo, Short valueOf) {
		Object[] ret=new Object[2];
		try{
			PlFinanceProductUserAccountInfo pinfo=dao.get("requestNo", cardNo);
			if(pinfo!=null){
				ObAccountDealInfo ob=obAccountDealInfoDao.get("recordNumber", cardNo);
				if(valueOf.equals(Short.valueOf("2"))){//目前只支持及时到账
					pinfo.setDealDate(new Date());
					pinfo.setDealStatus(Short.valueOf("2"));
					pinfo.setDealStatusName("转出成功");
					pinfo.setRemark("成功转出金额");
					dao.save(pinfo);
					if(ob==null){
						Map<String,Object> mapOF=new HashMap<String,Object>();
						mapOF.put("investPersonId",pinfo.getUserId());//投资人Id（必填）
						mapOF.put("investPersonType",ObSystemAccount.type0);//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量) （必填）					 
						mapOF.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量) （必填）
						mapOF.put("money",pinfo.getAmont().abs());//交易金额	（必填）			
						mapOF.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("dealType",ObAccountDealInfo.THIRDPAYDEAL);//交易方式：1表示现金交易，2表示银行卡交易，3表示第三方支付交易（参见ObAccountDealInfo中的常量）（必填）
						mapOF.put("recordNumber",cardNo);//交易流水号	（必填）
						mapOF.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);//资金交易状态：1等待支付，2支付成功，3 支付失败。。。(参见ObAccountDealInfo中的常量)	（必填）
						String[] rett =obAccountDealInfoService.operateAcountInfo(mapOF);
						ret[0]=Constants.CODE_SUCCESS;
						ret[1]="操作类型-转出成功";
					}else{
						if(!ob.getDealRecordStatus().equals(ObAccountDealInfo.DEAL_STATUS_2)){
							ret[0]=Constants.CODE_SUCCESS;
							ret[1]="操作类型-已经转出成功";
						}else{
							Map<String,Object> mapup=new HashMap<String,Object>();
							mapup.put("investPersonId",ob.getInvestPersonId());//投资人Id
							mapup.put("investPersonType",Short.valueOf("0"));//投资人类型：0线上投资人，1线下投资人(参见ObSystemAccount中的常量)
							mapup.put("transferType",ObAccountDealInfo.T_TRANSFER);//交易类型:1表示充值，2表示取现,3收益，4投资，5还本(参见ObAccountDealInfo中的常量)
							mapup.put("money",ob.getPayMoney().abs());//交易金额
							mapup.put("dealDirection",ObAccountDealInfo.DIRECTION_INCOME);//交易方向:1收入，2支出（参见ObAccountDealInfo中的常量）
							mapup.put("DealInfoId",null);//交易记录id，没有默认为null
							mapup.put("recordNumber",cardNo);//交易流水号
							mapup.put("dealStatus",ObAccountDealInfo.DEAL_STATUS_2);
							//资金交易状态：1等待支付，2支付成功，3 支付失败。。。
							obAccountDealInfoService.updateAcountInfo(mapup);
							ret[0]=Constants.CODE_SUCCESS;
							ret[1]="操作类型-已经转出成功";
						}
					}
				}else{
					pinfo.setDealDate(new Date());
					pinfo.setDealStatus(Short.valueOf("-1"));
					pinfo.setDealStatusName("交易异常");
					pinfo.setRemark("平台账户资金不足");
					dao.save(pinfo);
					ret[0]=Constants.CODE_FAILED;
					ret[1]="操作类型-转出失败";
				}
			}else{
				ret[0]=Constants.CODE_FAILED;
				ret[1]="操作类型-没有购买站岗资金记录";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ret[0]=Constants.CODE_FAILED;
			ret[1]="操作类型-系统错误";
		}
		return ret;
	}

	@Override
	public List<PlFinanceProductUserAccountInfo> queryAllCurrentFinancialList(
			Long userId, HttpServletRequest request) {
		
		return dao.getPersonList1(request, userId);
	}
	
}