package com.hurong.credit.service.financingAgency.manageMoney;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlan;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderAssignInterest;


public class AssignInerestGenerate {
	
	/**
	 * 保证金
	 */
	public static final String ProjectRisk = "riskRate"; // 保证金

	/**
	 * 收息
	 */
	public static final String ProjectLoadAccrual = "loanInterest"; 
	/**
	 * 收本
	 */
	public static final String ProjectLoadRoot = "principalRepayment"; 
	/**
	 * 优惠券利息   ---优惠券计算的
	 */
	public static final String CouponInterest = "couponInterest";
	/**
	 * 优惠券 返息现，本金，立还
	 */
	public static final String PrincipalCoupons = "principalCoupons";
	
	/**
	 * 加息券---加息利率计算的
	 */
	public static final String SubjoinInterest = "subjoinInterest";
	/**
	 * 普通加息
	 */
	public static final String CommonInterest = "commoninterest";
	/**
	 * 募集期
	 */
	public static final String RaiseInterest = "raiseinterest";
	public static  Integer couNum=0;//利息次数
	public static BigDecimal couMoney=new BigDecimal("0");//合计利息
	public static BigDecimal couPrincipalMoney=new BigDecimal("0");//合计本金
	public static String rebateWay="";//返利方式
	public static String rebateType="";//返利类型
	public static BigDecimal addRate = new BigDecimal(0);//加息日化利率
	public static BigDecimal couponValue = new BigDecimal(0);//优惠券有效面值
	public static String createType = "";//设置是否是优惠券生成台账
	public static final BigDecimal thirty = new BigDecimal(30); 
	private  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String isActualDay;
	private String isccalculateFirstAndEnd;
	private    Long orderId;
	private    String keystr;
	
	
	private    Integer isOne;
	private    Integer orderlimit;
    private    BigDecimal buyMoney;
	
	private    BigDecimal promisDayRate;
	private    BigDecimal promisMonthRate;
	
	
	private    String accrualType="singleInterest";
	private    String date1;
	private    String date2;
	private    Date startDate;
	private    Date intentDate;
	
	private    Date  payintentPerioDateDate;
	private    Date startDatecla;  //为了算投资人月末的日期
	
	private    String isStartDatePay;
	private    String payintentPerioDate;
	
	private    String mmName;
	private    Long mmplanId;
	private    Long investPersonId;
	private    String investPersonName;
	private    Integer investType;//1：收益再投资，2：提取主账户

	private static BigDecimal lin=new BigDecimal(0);
	
	public AssignInerestGenerate(PlManageMoneyPlanBuyinfo orderinfo,PlManageMoneyPlan plan){
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.isActualDay="no";
        this.buyMoney=orderinfo.getBuyMoney();
        this.intentDate=orderinfo.getEndinInterestTime();   
        this.date2=sd.format(orderinfo.getEndinInterestTime()); 
        if(null !=orderinfo.getStartinInterestTime()){
	       this.date1=sd.format(orderinfo.getStartinInterestTime());  
	       this.startDate=orderinfo.getStartinInterestTime();   
        }
        this.isOne = plan.getIsOne();
        this.investType=orderinfo.getInvestType();
       
        this.promisMonthRate=orderinfo.getPromisMonthRate().divide(new BigDecimal(100));
        this.promisDayRate=orderinfo.getPromisDayRate().divide(new BigDecimal(100),100,BigDecimal.ROUND_UP);//日化
      
        this.orderlimit=plan.getInvestlimit();
        this.mmplanId=plan.getMmplanId();
        this.investPersonId= orderinfo.getInvestPersonId();
        this.investPersonName=orderinfo.getInvestPersonName();
	    this.orderId=orderinfo.getOrderId();
	    this.mmName=orderinfo.getMmName();
	    this.keystr=orderinfo.getKeystr();
		   		
	}  
	
	
	public  List<PlMmOrderAssignInterest> createUPlanPlMmOrderAssignInterest(){
    	List<PlMmOrderAssignInterest> list = new ArrayList<PlMmOrderAssignInterest>();
    	createUPlan(list);
    	if(accrualType.equals("singleInterest")&&null!=isStartDatePay && isStartDatePay.equals("1")){
			try {
				//如果借款人的借款日期是1号的话，也就是说不管结束日期是30或31号，并说明是30或31号，而是在月末，别的也有这个问题
				if(sdf.format(this.startDatecla).split("-")[2].equals("01")){
					for(PlMmOrderAssignInterest l:list){
						if(l.getFundType().equals(ProjectLoadAccrual)){
							l.setIntentDate(sdf.parse(DateUtil.getMaxMonthDate(sdf.format(l.getIntentDate()))));
						}
					}
				}
				//如果借款人的借款日期是月末的话，那每期的还款日期都是月末的前一天
				if(this.startDatecla.equals(sdf.parse(DateUtil.getMaxMonthDate(sdf.format(this.startDatecla))))){
					for(PlMmOrderAssignInterest l:list){
						if(l.getFundType().equals(ProjectLoadAccrual)){
							try {
								l.setIntentDate(DateUtil.addDaysToDate(sdf.parse(DateUtil.getMaxMonthDate(sdf.format(l.getIntentDate()))),-1));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
						}
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    	return list;
		
	}
	
	public void createUPlan(List list){
	   	//按期收息，到期还本，用得是每期的实际日期（开始日期-结束日期）*日化利率即按实际天数算利息
		createOfUPlanInterestAndMonthPay(list);
	   
    }
	public  void createOfUPlanInterestAndMonthPay(List list){
 	    Date lastintentDate = startDate; 
 	    Date nextintentDate = null;
 	    //不一次性支付利息->收益再投资
 	    if (isOne == 0 && investType == 1) { 
 	    	BigDecimal previousIncomeMoney=new BigDecimal("0");
 	    	for (int i = 1; i <= orderlimit; i++) {
 	    		Date startactualdate=DateUtil.addMonthsToDate(startDate, i-1);
			    if(i>1){
				    startactualdate=DateUtil.addDaysToDate(startactualdate, -1);
			    }
			    Date endctualdate=DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    BigDecimal actualdays=getActualdays(startactualdate,endctualdate,1,i);
			    Date intentDate=null;
			    intentDate=DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    BigDecimal everybaseMoney = buyMoney.add(previousIncomeMoney);//下一期本金
			    nextintentDate =DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    lastintentDate=intentDate;
			    
			    previousIncomeMoney=previousIncomeMoney.add(everybaseMoney.multiply(actualdays).multiply(promisDayRate).setScale(2, BigDecimal.ROUND_HALF_UP));//利息
			}
 	    	BigDecimal principalRepaymentMoney = new BigDecimal("0");
 	    	//这个比较特殊
 	    	PlMmOrderAssignInterest sf = calculslfundintent(ProjectLoadAccrual,intentDate,lastintentDate,nextintentDate,BigDecimal.valueOf(0),previousIncomeMoney,1) ;
 	    	if(sf.getIncomeMoney().compareTo(new BigDecimal("0")) !=0){
 	    		list.add(sf);
 	    	}
		    everyPeriodFundintent(list, intentDate,lastintentDate,nextintentDate,buyMoney, new BigDecimal("0"), new BigDecimal("0"),1,new BigDecimal("0"));
		    lastintentDate=intentDate;
	    }
 	    // 不一次性支付利息->提取主账户
	    if (isOne == 0 && investType == 2) {
		    for (int i = 1; i <= orderlimit; i++) {
			    Date startactualdate=DateUtil.addMonthsToDate(startDate, i-1);
			    if(i>1){
				    startactualdate=DateUtil.addDaysToDate(startactualdate, -1);
			    }
			    Date endctualdate=DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    BigDecimal actualdays=getActualdays(startactualdate,endctualdate,1,i);
			    Date intentDate=null;
			    intentDate=DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    BigDecimal principalRepaymentMoney = new BigDecimal("0");
			    BigDecimal everybaseMoney = buyMoney;
			    nextintentDate =DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, i), -1);
			    everyPeriodFundintent(list, intentDate,lastintentDate,nextintentDate,principalRepaymentMoney, everybaseMoney, actualdays,i,buyMoney);
			    lastintentDate=intentDate;
			}
		    everyPeriodFundintent(list, intentDate,lastintentDate,nextintentDate,buyMoney, new BigDecimal("0"), new BigDecimal("0"),orderlimit,new BigDecimal("0"));
		    lastintentDate=intentDate;
	    }
	    // 一次性收取全部利息
		if (isOne == 1) {
			Date startactualdate=startDate;
			Date endctualdate=DateUtil.addDaysToDate(DateUtil.addMonthsToDate(startDate, orderlimit), -1);
			BigDecimal actualdays=getActualdays(startactualdate,endctualdate,orderlimit,1);
			Date intentDate = null;
			intentDate = this.intentDate;
			BigDecimal principalRepaymentMoney = new BigDecimal("0");
			BigDecimal everybaseMoney= buyMoney;
			nextintentDate =this.intentDate;
			everyPeriodFundintent(list,intentDate,lastintentDate,nextintentDate,principalRepaymentMoney,everybaseMoney,actualdays, 1,buyMoney);
			lastintentDate=this.intentDate;
			
			everyPeriodFundintent(list, intentDate,lastintentDate,nextintentDate,buyMoney, new BigDecimal("0"), new BigDecimal("0"),1,new BigDecimal("0"));
			lastintentDate=intentDate;
		}
	 	
	}
	 
	public  PlMmOrderAssignInterest calculslfundintent(String fundType,Date intentdate1,Date lastintentDate,Date nextintentDate,BigDecimal paymoney,BigDecimal incomemoeny,int payintentPeriod){
		PlMmOrderAssignInterest ai1=new PlMmOrderAssignInterest();
		ai1.setOrderId(orderId);
		ai1.setFundType(fundType);
		ai1.setKeystr(keystr);
		ai1.setPeriods(payintentPeriod);
		ai1.setIncomeMoney(incomemoeny);
		ai1.setPayMoney(paymoney);
		ai1.setIntentDate(intentdate1);
		ai1.setInvestPersonId(investPersonId);
		ai1.setInvestPersonName(investPersonName);
		ai1.setMmName(mmName);
		ai1.setAfterMoney(lin);
		ai1.setMmplanId(mmplanId);
		ai1.setIsValid(Short.valueOf("0"));
		ai1.setIsCheck(Short.valueOf("0"));
		
		return  ai1;
	}
	
	//装配FundIntent对象
	public  void everyPeriodFundintent(List list,Date intentdate,Date lastintentDate,Date nextintentDate,BigDecimal principalRepaymentMoney,BigDecimal everybaseMoney,BigDecimal thirty ,int payintentPeriod,BigDecimal surplusManagMoney){
		if(intentdate.compareTo(this.intentDate)>0){
			return ;
		}
		PlMmOrderAssignInterest sf = calculslfundintent(ProjectLoadRoot, intentdate,lastintentDate,nextintentDate, BigDecimal.valueOf(0),principalRepaymentMoney,payintentPeriod);
		PlMmOrderAssignInterest sf1 = calculslfundintent(ProjectLoadAccrual,intentdate,lastintentDate,nextintentDate,BigDecimal.valueOf(0),everybaseMoney.multiply(promisDayRate).multiply(thirty),payintentPeriod) ;
		if(sf.getIncomeMoney().compareTo(new BigDecimal("0")) !=0){
			list.add(sf);
		}
		if(sf1.getIncomeMoney().compareTo(new BigDecimal("0")) !=0){
			list.add(sf1);
		}
	}
	
	
	//单利，循环体里面计算天数（其中对日有算头算尾)
	public BigDecimal getActualdays(Date startactualdate,Date endctualdate,int n,int payintentPeriod){
		BigDecimal actualdays=new BigDecimal(Integer.valueOf(DateUtil.getDaysBetweenDate( sdf.format(startactualdate), sdf.format(endctualdate))).toString());
		if("no".equals(isActualDay)){
			actualdays=thirty.multiply(new BigDecimal(n));
		}
		return actualdays;
	}
	
}
