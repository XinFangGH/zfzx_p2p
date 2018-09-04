package com.hurong.credit.service.creditFlow.smallLoan.finance.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.dao.coupon.BpCouponsDao;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidInfoDao;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.dao.creditFlow.financingAgency.business.BpBusinessDirProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.business.BpBusinessOrProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.BpPersionDirProDao;
import com.hurong.credit.dao.creditFlow.financingAgency.persion.BpPersionOrProDao;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.dao.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordDao;
import com.hurong.credit.dao.customer.InvestPersonInfoDao;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;
import com.hurong.credit.model.creditFlow.smallLoan.finance.SlEarlyRepaymentRecord;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.smallLoan.finance.SlEarlyRepaymentRecordService;

/**
 * 
 * @author 
 *
 */
public class SlEarlyRepaymentRecordServiceImpl extends BaseServiceImpl<SlEarlyRepaymentRecord> implements SlEarlyRepaymentRecordService{
	@SuppressWarnings("unused")
	private SlEarlyRepaymentRecordDao dao;

	@Resource
	private PlBidPlanDao plBidPlanDao;
	@Resource
	private PlBidInfoDao plBidInfoDao;
	@Resource
	private BpCouponsDao bpCouponsDao;
	@Resource
	private BpFundProjectDao bpFundProjectDao;
	@Resource
	private BpFundIntentDao bpFundIntentDao;
	@Resource
	private BpBusinessDirProDao bpBusinessDirProDao;
	@Resource
	private BpBusinessOrProDao bpBusinessOrProDao;
	@Resource
	private BpPersionDirProDao bpPersionDirProDao;
	@Resource
	private BpPersionOrProDao bpPersionOrProDao;
	@Resource
	private InvestPersonInfoDao investPersonInfoDao;
	@Resource
	private BpFundIntentService bpFundIntentService;
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	public SlEarlyRepaymentRecordServiceImpl(SlEarlyRepaymentRecordDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	@Override
	public SlEarlyRepaymentRecord saveEarlyProjectInfo(Long bidPlanId) {
		SlEarlyRepaymentRecord record=null;
		try{
			//检查之前是否有生成过提前还款的记录  如果有 则置为无效  重新生成
			QueryFilter filter = new QueryFilter();
			filter.addFilter("Q_bidPlanId_L_EQ", bidPlanId);
			filter.getPagingBean().setPageSize(1000000000);
			List<BpFundIntent> list1 = bpFundIntentService.getAll(filter);
			String requestNOLoaner = "";
			if(list1.size()>0){
				for(BpFundIntent intent:list1){
					if(intent.getSlEarlyRepaymentId()!=null && intent.getIsCheck()==1 && intent.getFactDate()==null){
						bpFundIntentDao.remove(intent);
					}
				}
			}
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			PlBidPlan plBidPlan = plBidPlanDao.get(bidPlanId);
			Long projectId=null;
			if(plBidPlan.getProType().equals("B_Dir")){
				BpBusinessDirPro bdirpro = bpBusinessDirProDao.get(plBidPlan.getBdirProId());
				projectId = bdirpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("B_Or")){
				BpBusinessOrPro borpro = bpBusinessOrProDao.get(plBidPlan.getBorProId());
				projectId = borpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("P_Dir")){
				BpPersionDirPro pdirpro = bpPersionDirProDao.get(plBidPlan.getPdirProId());
				projectId = pdirpro.getMoneyPlanId();
			}else if(plBidPlan.getProType().equals("P_Or")){
				BpPersionOrPro porpro = bpPersionOrProDao.get(plBidPlan.getPOrProId());
				projectId = porpro.getMoneyPlanId();
			}
			BpFundProject project=bpFundProjectDao.get(projectId);
			//剩余本金（提前还款金额=剩余本金）
			BigDecimal principalMoney=bpFundIntentDao.getAllPrincipalMoney(bidPlanId,null);
			Date date=DateUtil.parseDate(sd.format(new Date()));
			record=new SlEarlyRepaymentRecord();
			record.setBidPlanId(bidPlanId);
			record.setEarlyDate(date);
			record.setCheckStatus(0);
			record.setEarlyProjectMoney(principalMoney);
			record.setProjectId(project.getProjectId());
			record.setBusinessType(project.getBusinessType());
			record.setOpTime(new Date());
			this.dao.save(record);
			List<InvestPersonInfo> list=investPersonInfoDao.getByPlanId(bidPlanId);
			List<BpFundIntent> flist=new ArrayList<BpFundIntent>();
			for(InvestPersonInfo p:list){
				//按提前还款日期时间查询当前所在期（不能查询已对账的款项）
				List<BpFundIntent> filist=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "loanInterest");
				//查询出离提前还款日期最近的未还款款项台账（不能查询已对账的款项）
				List<BpFundIntent> filist2=bpFundIntentDao.queryNotPay(bidPlanId, p.getOrderNo(), "loanInterest");
				Integer payintentPeriod=0;
				Date startDate=null;
				int days=0;
				int payDays=0;//当前总天数
				//防止客户非正常还款，例如：在4月份的时候已经正常还了6月份应还的款项，再进行提前还款时，如果按提前还款日期锁定目前所在期数，会出错
				if(null!=filist && filist.size()>0){
					BpFundIntent f=filist.get(0);
					payintentPeriod=f.getPayintentPeriod();
					startDate=f.getInterestStarTime();
					days=DateUtil.getDaysBetweenDate(sd.format(f.getInterestStarTime()), sd.format(new Date()));
					payDays=DateUtil.getDaysBetweenDate(sd.format(f.getInterestStarTime()), sd.format(f.getInterestEndTime()));
					/*if(AppUtil.getInterest().equals("1")){
						days=days+1;
					}*/
				}else if(null!=filist2 && filist2.size()>0){
					BpFundIntent f2=filist2.get(0);
					payintentPeriod=f2.getPayintentPeriod();
					startDate=f2.getInterestStarTime();
				}
				BigDecimal principal=bpFundIntentDao.getAllPrincipalMoney(bidPlanId,p.getOrderNo());
				//补偿息
				
				BpFundIntent f=this.calculBpFundIntent("interestPenalty", date, project.getStartDate(), date,new BigDecimal(0) , principal.multiply(project.getDayAccrualRate().divide(new BigDecimal(100))).multiply(new BigDecimal(plBidPlan.getPenaltyDays()==null?0:plBidPlan.getPenaltyDays())).setScale(2,BigDecimal.ROUND_HALF_UP), payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
				if(f.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
					f.setBidPlanId(bidPlanId);
					f.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
					bpFundIntentDao.save(f);
				}
				//利息
				BpFundIntent f1=this.calculBpFundIntent("loanInterest", date, startDate, date,new BigDecimal(0) , principal.multiply(project.getDayAccrualRate().divide(new BigDecimal(100))).multiply(new BigDecimal(days)).setScale(2,BigDecimal.ROUND_HALF_UP), payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
				if(f1.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
					f1.setBidPlanId(bidPlanId);
					f1.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
					bpFundIntentDao.save(f1);
				}
				//咨询费
				BpFundIntent f2=this.calculBpFundIntent("consultationMoney", date, startDate, date,new BigDecimal(0) , principal.multiply(project.getDayManagementConsultingOfRate().divide(new BigDecimal(100))).multiply(new BigDecimal(days)).setScale(2,BigDecimal.ROUND_UP), payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
				if(f2.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
					f2.setBidPlanId(bidPlanId);
					f2.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
					bpFundIntentDao.save(f2);
				}
				//服务费
				BpFundIntent f3=this.calculBpFundIntent("serviceMoney", date, startDate, date,new BigDecimal(0) , principal.multiply(project.getDayFinanceServiceOfRate().divide(new BigDecimal(100))).multiply(new BigDecimal(days)), payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
				if(f3.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
					f3.setBidPlanId(bidPlanId);
					f3.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
					bpFundIntentDao.save(f3);
				}
				//本金偿还
				BpFundIntent f4=this.calculBpFundIntent("principalRepayment", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , principal, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
				if(f4.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
					f4.setBidPlanId(bidPlanId);
					f4.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
					bpFundIntentDao.save(f4);
				}
				
				//重新生成优惠券奖励，普通加息台账
				newCouponsFundIntent(plBidPlan,p,days,payDays,payintentPeriod,project,record);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return record;
	}
	/**
	 * 重新生成优惠券奖励台账
	 * @param plBidPlanint
	 */
	public void newCouponsFundIntent(PlBidPlan plBidPlan,InvestPersonInfo p,int days,int payDays,
			int payintentPeriod,BpFundProject project,SlEarlyRepaymentRecord record){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date date=DateUtil.parseDate(sd.format(new Date()));
		Long bidPlanId = plBidPlan.getBidId();
		if(plBidPlan.getCoupon()!=null&&plBidPlan.getCoupon().compareTo(1)==0){
			//查询出对应的投标记录
			PlBidInfo plBidInfo = plBidInfoDao.getByOrdId(p.getOrderNo());
			//如果优惠券不为空并且没有发生债权交易
			if(plBidInfo.getCouponId()!=null&&plBidPlan.getRebateType()!=null&&plBidPlan.getRebateWay()!=null&&plBidInfo.getNewInvestPersonId()==null){
				//通过投标记录查找优惠券
				BpCoupons bpCoupons =bpCouponsDao.get(plBidInfo.getCouponId());
				//判断是返利方式是否是  随期 ，到期
				if(plBidPlan.getRebateWay()==2||plBidPlan.getRebateWay()==3){
					BpFundIntent newFundIntent=null;//利息台账
					BpFundIntent newPrinciIntent=null;//本金台账
					//贷款总天数
					int sumDays=DateUtil.getDaysBetweenDate(sd.format(plBidPlan.getStartIntentDate()).toString(), sd.format(plBidPlan.getEndIntentDate()).toString());
					BigDecimal imcomeMoney = new BigDecimal("0");//重新计算利息奖励金额
					BigDecimal imcomePriMoney = new BigDecimal("0");//重新计算本金奖励金额
					if(plBidPlan.getRebateType()==1){//返现
						 imcomeMoney = bpCoupons.getCouponValue().multiply(plBidPlan.getReturnRatio()).
						 divide(new BigDecimal(100)).multiply(new BigDecimal(days)).divide(new BigDecimal(sumDays))
						 .setScale(2,BigDecimal.ROUND_HALF_UP);
						 newFundIntent=this.calculBpFundIntent("principalCoupons", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomeMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
					}else if(plBidPlan.getRebateType()==2){//返息
						BigDecimal couponMoney=new BigDecimal("0");//得到当期奖励金额
						List<BpFundIntent> couponInterest=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "couponInterest");
						for(BpFundIntent fund:couponInterest){
								couponMoney=fund.getIncomeMoney();
						}
						 imcomeMoney = couponMoney.multiply(new BigDecimal(days)).divide(new BigDecimal(payDays),2,BigDecimal.ROUND_HALF_UP);
						 newFundIntent=this.calculBpFundIntent("couponInterest", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomeMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
					}else if(plBidPlan.getRebateType()==3){//返息现
						BigDecimal couponMoney=new BigDecimal("0");//得到当期利息奖励金额
						BigDecimal princiMoney=new BigDecimal("0");//得到当期本金奖励金额
						List<BpFundIntent> couponInterest=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "couponInterest");
						List<BpFundIntent> principalCoupons=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "principalCoupons");
						for(BpFundIntent fund:couponInterest){
								couponMoney=fund.getIncomeMoney();
						}
						for(BpFundIntent fund:principalCoupons){
								princiMoney=fund.getIncomeMoney();
						}
						 imcomeMoney = couponMoney.multiply(new BigDecimal(days)).divide(new BigDecimal(payDays),2,BigDecimal.ROUND_HALF_UP);
						 imcomePriMoney = princiMoney.multiply(new BigDecimal(days)).divide(new BigDecimal(payDays),2,BigDecimal.ROUND_HALF_UP);
						 newFundIntent=this.calculBpFundIntent("couponInterest", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomeMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
						 newPrinciIntent=this.calculBpFundIntent("principalCoupons", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomePriMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
					}else if(plBidPlan.getRebateType()==4){//加息
						BigDecimal couponMoney=new BigDecimal("0");//得到当期奖励金额
						List<BpFundIntent> subjoinInterest=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "subjoinInterest");
						for(BpFundIntent fund:subjoinInterest){
								couponMoney=fund.getIncomeMoney();
								break;
						}
						 imcomeMoney = couponMoney.multiply(new BigDecimal(days)).divide(new BigDecimal(payDays),2,BigDecimal.ROUND_HALF_UP);
						 newFundIntent=this.calculBpFundIntent("subjoinInterest", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomeMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
					}
					if(newFundIntent!=null){
						if(newFundIntent.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
							newFundIntent.setBidPlanId(bidPlanId);
							newFundIntent.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
							bpFundIntentDao.save(newFundIntent);
						}
					}
					if(newPrinciIntent!=null){
						if(newPrinciIntent.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
							newPrinciIntent.setBidPlanId(bidPlanId);
							newPrinciIntent.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
							bpFundIntentDao.save(newPrinciIntent);
						}
					}
				}
			}
		}else{//判断是否有普通加息
			if(plBidPlan.getAddRate()!=null&&!plBidPlan.getAddRate().equals("")){
				//普通加息系统配置是否无效 0=有效1=无效
				String isAddRate = configMap.get("isAddRate").toString();
				if(isAddRate.equals("0")){
					//加息
					BigDecimal couponMoney=new BigDecimal("0");//得到当期奖励金额
					List<BpFundIntent> commoninterest=bpFundIntentDao.listByEarlyDate(bidPlanId, p.getOrderNo(), sd.format(new Date()), "commoninterest");
					for(BpFundIntent fund:commoninterest){
						couponMoney=fund.getIncomeMoney();
					}
					BigDecimal imcomeMoney = couponMoney.multiply(new BigDecimal(days)).divide(new BigDecimal(payDays),2,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
					BpFundIntent fundIntent=this.calculBpFundIntent("commoninterest", date, plBidPlan.getStartIntentDate(), date,new BigDecimal(0) , imcomeMoney, payintentPeriod, p, project, sd.format(plBidPlan.getStartIntentDate()));
					if(fundIntent.getIncomeMoney().compareTo(new BigDecimal(0))!=0){
						fundIntent.setBidPlanId(bidPlanId);
						fundIntent.setSlEarlyRepaymentId(record.getSlEarlyRepaymentId());
						bpFundIntentDao.save(fundIntent);
					}
				}
			}
		}
	}
	public  BpFundIntent calculBpFundIntent(String fundType,Date intentdate,Date lastintentDate,Date nextintentDate,BigDecimal paymoney,BigDecimal incomemoeny,int payintentPeriod,InvestPersonInfo p,BpFundProject project,String startDate){
		BpFundIntent sf1=new BpFundIntent();
		sf1.setFundType(fundType);// 资金类型
		sf1.setIntentDate(intentdate);
		sf1.setPayMoney(paymoney); // 支出金额
		sf1.setIncomeMoney(incomemoeny); // 收入金额
		sf1.setAfterMoney(new BigDecimal("0"));
		sf1.setFlatMoney(new BigDecimal("0"));
		sf1.setAccrualMoney(new BigDecimal("0"));
		sf1.setNotMoney(paymoney.compareTo(new BigDecimal("0"))==0?incomemoeny:paymoney);
		sf1.setRemark("");
		sf1.setBusinessType("SmallLoan");
		sf1.setInvestPersonId(p.getInvestPersonId());
		sf1.setInvestPersonName(p.getInvestPersonName());
		sf1.setPayintentPeriod(payintentPeriod);
		sf1.setFundResource(p.getFundResource().toString());
		sf1.setProjectId(project.getProjectId());
		sf1.setProjectName(project.getProjectName());
		sf1.setProjectNumber(project.getProjectNumber());
		sf1.setCompanyId(Long.valueOf("1"));
		sf1.setBusinessType(project.getBusinessType());
		sf1.setPreceptId(project.getId());
		sf1.setOrderNo(p.getOrderNo());
		sf1.setIsValid(Short.valueOf("0"));
		sf1.setIsCheck(Short.valueOf("1"));
	    Date interestStarTime=null;
	    Date interestEndTime=null;
	    String isHaveLin="no";
	    if(!fundType.equals("principalLending")){
	    	if(payintentPeriod==0){
		    	isHaveLin="yes";
		    }
		   // if(AppUtil.getInterest().equals("0")){
		    	if(project.getIsPreposePayAccrual()==0){
		    		interestStarTime=lastintentDate;
		    	    interestEndTime=DateUtil.addDaysToDate(nextintentDate,-1);
		    	}else{
		    		interestStarTime=intentdate;
		    		interestEndTime=DateUtil.addDaysToDate(nextintentDate,-1);
		    	}
		    	if(fundType.equals("principalRepayment")&& project.getAccrualtype().equals("singleInterest")){
			    	interestStarTime=lastintentDate;
			    	interestEndTime=DateUtil.addDaysToDate(nextintentDate,-1);;
			    }
		  /*  }else{
		    	if(project.getIsPreposePayAccrual()==0){
		    		if(intentdate.equals(startDate) || (isHaveLin.equals("no")&&payintentPeriod==1)|| (isHaveLin.equals("yes")&&payintentPeriod==0)){
		    			interestStarTime=lastintentDate;  //第一期的时候不减加一天
		    		}else{
		    		   interestStarTime=DateUtil.addDaysToDate(lastintentDate,1);
		    		}
		    		  interestEndTime=intentdate;
			    }else{
		    		if(intentdate.equals(startDate)|| (isHaveLin.equals("no")&&payintentPeriod==1)|| (isHaveLin.equals("yes")&&payintentPeriod==0)){
		    			interestStarTime=intentdate;  //第一期的时候不加一天
		    		}else{
		    		   interestStarTime=DateUtil.addDaysToDate(intentdate,1);
		    		}
		    		interestEndTime=nextintentDate;
			    	}
		    	
		    	if(fundType.equals("principalRepayment")&& project.getAccrualtype().equals("singleInterest")){
			    	interestStarTime=lastintentDate;
			    	interestEndTime=nextintentDate;
			    }
		    	
		    }*/
		    
		    sf1.setInterestStarTime(interestStarTime);
			sf1.setInterestEndTime(interestEndTime);
	    }
		return  sf1;
	}

	@Override
	public Boolean updateFundIntentInfo(Long bidPlanId, Long slEarlyRepaymentId) {
		Boolean flag=false;
		try{
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			List<BpFundIntent> list=bpFundIntentDao.listBySlEarlyRepaymentId(bidPlanId, slEarlyRepaymentId);
			for(BpFundIntent f:list){
				f.setIsCheck(Short.valueOf("0"));
				f.setIsValid(Short.valueOf("0"));
				bpFundIntentDao.merge(f);
				
			}
			List<BpFundIntent> blist=bpFundIntentDao.listByBidIdAndEarlyDate(bidPlanId, sd.format(new Date()), slEarlyRepaymentId);
			for(BpFundIntent bf:blist){
				bf.setIsCheck(Short.valueOf("1"));
				bf.setIsValid(Short.valueOf("1"));
				bpFundIntentDao.merge(bf);
					
			}
			SlEarlyRepaymentRecord record=this.dao.get(slEarlyRepaymentId);
			record.setCheckStatus(1);
			this.dao.merge(record);
			flag=true;
		}catch(Exception e){
			flag=false;
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SlEarlyRepaymentRecord queryId(Long bidPlanId) {
		return dao.queryId(bidPlanId);
	}
}