package com.hurong.credit.service.creditFlow.finance.impl;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.creditFlow.finance.BpFundIntentDao;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.SlFundIntentPeriod;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.customer.InvestPersonInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BpFundIntentServiceImpl extends BaseServiceImpl<BpFundIntent> implements
		BpFundIntentService {
	@SuppressWarnings("unused")
	private BpFundIntentDao dao;

	@Resource
	private InvestPersonInfoService investPersonInfoService;

	public BpFundIntentServiceImpl(BpFundIntentDao dao) {
		super(dao);
		this.dao = dao;
	}
	@Override
	public BigDecimal getInterest(Long preceptId, Long investPersonId) {
		return null;
	}
	@Override
	public BigDecimal getPrincipal(Long preceptId, Long investPersonId) {
		return null;
	}
	@Override
	public List<FundIncome> getIncome(HttpServletRequest request,PagingBean pb,Long investPersonId) {
		return dao.getIncome(request, pb, investPersonId);
	}
	@Override
	public List<BpFundIntent> getList(Long bidPlanId, PagingBean pb) {
		return dao.getList(bidPlanId, pb);
	}
	@Override
	public List<BpFundIntent> getBpFund(String fundType, String investPersonName,String time) {
		return dao.getBpFund(fundType,investPersonName,time);
	}
	@Override
	public BigDecimal getTotle(String fundType, String investPeronName) {
		return dao.getTotle(fundType,investPeronName);
	}
	@Override
	public BigDecimal getincome(String type, String investpersonName) {
		return dao.getincome(type, investpersonName);
	}
	@Override
	public Long getProjectInfo(String fundType, String investPersonName,
			String type) {
		return dao.getProjectInfo( fundType,  investPersonName,type);
	}
	@Override
	public List<BpFundIntent> getProjectInfoInvest(Long bidPlanId,String fundType,String investpersonName) {
		return dao.getProjectInfoInvest(bidPlanId,fundType,investpersonName);
	}
	/**
	 * 根据投资人的id获得投资人当期应该受到的钱是多少
	 * @param temp
	 * @param planId
	 * @param peridId
	 * @param object
	 * @return
	 */
	@Override
	public BigDecimal getByPlanIdAndOtherRequest(String temp, String planId,
			String peridId, String type) {
		return dao.getByPlanIdAndOtherRequest(temp,planId,peridId,type);
	}
	@Override
	public BigDecimal getTotal(Long bidPlanId, String type) {
		return dao.getTotal(bidPlanId, type);
	}
	@Override
	public Integer getPayintentPeriodMax(Long bidPlanId) {
		return dao.getPayintentPeriodMax(bidPlanId);
	}
	@Override
	public BigDecimal getnotMoneySum(Long bidPlanId, int payintentPeriod) {
		return dao.getnotMoneySum(bidPlanId, payintentPeriod);
	}
	@Override
	public Date getIntentDate(Long bidPlanId, int payintentPeriod, String type) {
		return dao.getIntentDate( bidPlanId,  payintentPeriod,  type);
	}
	@Override
	public java.lang.Integer getNextCount(Long bidPlanId) {
		return dao.getNextCount( bidPlanId);
	}
	@Override
	public BigDecimal getMoney(Long bidPlanId,String fundType, String type,String orderNo) {
		return dao.getMoney( bidPlanId, fundType,  type,orderNo);
	}
	@Override
	public BigDecimal getFinancialMoney(Long bidPlanId, String type) {
		return dao.getFinancialMoney(bidPlanId, type);
	}
	@Override
	public List<BpFundIntent> getListByBidPlanId(long parseLong, Object object) {
		// TODO Auto-generated method stub
		return dao.getListByBidPlanId(parseLong,object);
	}
	@Override
	public String[] getFundInfoByPlanId(Long valueOf, HttpServletRequest request) {
	String[] ret=new String[5];
	BigDecimal notMoney=new BigDecimal(0);//待还本息
	int num = 0;//当前期数
	int totalnum=0;//总期数
	Date d = null;//下次还款日
	Date dn = null;//还清日期
	String indexStr="";//区分所有期数是否全部还完
	
	List<BpFundIntent> listSlfund=this.getListByBidPlanId(valueOf,null);
	if(valueOf.equals("5099")){
		System.out.println("");
	}
	if(listSlfund.size()>0){
	for(BpFundIntent slf:listSlfund){
		if(slf.getFundType().equals("loanInterest")||slf.getFundType().equals("principalRepayment")){
			notMoney=notMoney.add(slf.getNotMoney());
			if(slf.getFactDate()==null&&num==0&&d==null){
				d=slf.getIntentDate();
				num=slf.getPayintentPeriod()-1;
				indexStr = "pass";
			}
		}
	}
	totalnum=listSlfund.get(listSlfund.size()-1).getPayintentPeriod();
	dn=listSlfund.get(listSlfund.size()-1).getFactDate();

	}
	
	ret[0]=notMoney.toString();
	ret[1]=String.valueOf("pass".equals(indexStr)?(num+1):totalnum);
	ret[2]=String.valueOf(totalnum);
	ret[3]=(d==null?"":DateUtil.dateToStr(d, "yyyy-MM-dd"));
	ret[4]=(dn==null?"":DateUtil.dateToStr(dn, "yyyy-MM-dd"));
	return ret;
	}
	@Override
	public List<BpFundIntent> getBpFundIntentAll(String bidId, String type,String userName) {
		return dao.getBpFundIntentAll(bidId, type,userName);
	}
	@Override
	public Integer getProgressTotal(Long bidPlanId, String userName, String type) {
		return dao.getProgressTotal(bidPlanId, userName, type);
	}
	@Override
	public Integer getFactDateNull(String userName) {
		// TODO Auto-generated method stub
		return dao.getFactDateNull(userName);
	}
	
	@Override
	public List<BpFundIntent> getByRequestNo(String requestNo) {
		// TODO Auto-generated method stub
		return dao.getByRequestNo(requestNo);
	}
	
	@Override
	public List<BpFundIntent> getCouponsIntent(String planId, String peridId,String requestNo,String fundType) {
		// TODO Auto-generated method stub
		return dao.getCouponsIntent(planId, peridId,requestNo,fundType);
	}
	@Override
	public BpFundIntent getBpFundIntent(Long ProjectId) {
		// TODO Auto-generated method stub
		return dao.getBpFundIntent( ProjectId);
	}
	@Override
	public List<BpFundIntent> getFundIntent(String investpersonName) {
		return dao.getFundIntent(investpersonName);
	}
	@Override
	public List<BpFundIntent> getLoanPlanId(Long userId) {
		return dao.getLoanPlanId( userId);
	}
	@Override
	public List<BpFundIntent> getIntentList(Long userId,String requestType,Pager pager) {
		return dao.getIntentList( userId,requestType, pager);
	}
	@Override
	public BigDecimal getEveryTimeMoney(Long ProjectId) {
		return dao.getEveryTimeMoney( ProjectId);
	}
	@Override
	public List<BpFundIntent> getFund(Long ProjectId) {
		return dao.getFund(ProjectId);
	}
	@Override
	public BigDecimal getSumEarlymoney(String orderNo, String planId,
			String slEarlyPayId, Object object) {
		// TODO Auto-generated method stub
		return dao.getSumEarlymoney(orderNo,planId,slEarlyPayId,object);
	}
	@Override
	public List<SlFundIntentPeriod> listByBidPlanIdAndpayintentPeriod(
			PagingBean pb, Map<String, String> map) {
		
		List<SlFundIntentPeriod> list=	dao.listByBidPlanIdAndpayintentPeriod(pb, map);
		int j=0;
		if(null!=pb){
			for(SlFundIntentPeriod  l:list){
				List<BpFundIntent>	bpfundlist=dao.getByBidPlanIdAndIntentDate(l.getPlanId(), l.getIntentDate(), null);
				l.initSlFundIntentPeriod(bpfundlist);
				j++;
			}
		}
		return list;
	}
	@Override
	public List<BpFundIntent> findByBidOrderNoFundType(String orderNo, String fundType) {
		String hql1 = "from BpFundIntent as bf where  bf.orderNo = ? and bf.fundType  = ? ";
		List<BpFundIntent> list = dao.findByHql(hql1, new Object[]{orderNo,fundType});
		return list;
	}
	@Override
	public BigDecimal getrepaymentTotal(Long bidId) {
		
		return dao.getrepaymentTotal(bidId);
	}
	
	/**
	 *根据投资人ID统计已还款金额
	 * @param temp
	 * @param planId
	 * @param peridId
	 * @param object
	 * @return
	 */
	@Override
	public BigDecimal getAfterMoney(String temp, String planId,
			String peridId, String type) {
		// TODO Auto-generated method stub
		return dao.getAfterMoney(temp,planId,peridId,type);
	}
	@Override
	public List<BpFundIntent> getThirdBpFundIntentList(String bidId,
			Date intentDate) {
		// TODO Auto-generated method stub
		return dao.getThirdBpFundIntentList(bidId, intentDate);
	}
	@Override
	public BigDecimal getBackMoney(String bidId, String peridId) {
		// TODO Auto-generated method stub
		return dao.getBackMoney(bidId, peridId);
	}
	public List<BpFundIntent> getListByBidIdAndPeriod(String planId,String period){
		return dao.getListByBidIdAndPeriod(planId, period);
	}
	
	public List<BpFundIntent> getUnReturnFund(){
		return null;
	}
	@Override
	public List<BpFundIntent> getUnCheckIntent(String bidId) {
		// TODO Auto-generated method stub
		return dao.getUnCheckIntent(bidId);
	}
	@Override
	public List<BpFundIntent> getBySlEarlyId(String slEarlyId) {
		// TODO Auto-generated method stub
		return dao.getBySlEarlyId(slEarlyId);
	}
	@Override
	public List<BpFundIntent> getOriFund(String bidId) {
		// TODO Auto-generated method stub
		return dao.getOriFund(bidId);
	}
	@Override
	public List<BpFundIntent> getByRequestNoLoaner(String requestNoLoaner) {
		// TODO Auto-generated method stub
		return dao.getByRequestNoLoaner(requestNoLoaner);
	}



	@Override
	public BigDecimal getBackAccMoney(String bidId, String period) {
		// TODO Auto-generated method stub
		return dao.getBackAccMoney(bidId,period);
	}
	//借款人款项台账
	@Override
	public List<ShowManageMoney> findLoanRepayMemtList(Long id,
			String loginname, Long planId, String loanType,HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		if(loanType.equals("repayMemt")){//借款人借款列表
			return dao.findLoanRepayMemtList(id,loginname,planId,loanType,request);
		}else if(loanType.equals("repayMemtSignle")){//借款人一个借款项目的还款计划
			return dao.findLoanRepayMemtList(id,loginname,planId,loanType,request);
		}else if(loanType.equals("repayMemtFinish")){//借款人已经标记为已结项的项目
			return dao.findLoanFishRepayMentList(id,loginname,planId,loanType,request);
		}else if(loanType.equals("repayMemtBefore")){//借款人招标中的项目
			return dao.findLoanBeforeRepayMentList(id,loginname,planId,loanType,request);
		}else{
			return dao.findLoanRepayMemtList(id,loginname,planId,loanType,request);
		}
		
	}
	//投资人款项台账
	@Override
	public List<FundPay> findInvestRepaymentList(String loginname, Long memId,String planId, String repaymentType,HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(repaymentType.equals("InvestRepayment")){//投资人待还款项目(一个项目分很多次投资记录)
			return dao.findInvestRepayMemtList(memId,loginname,planId,repaymentType,request);
		}else if(repaymentType.equals("InvestRepayMemtSignle")){//投资人一个具体投资记录的回款记录
			return dao.findInvestRepayMemtList(memId,loginname,planId,repaymentType,null);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtFinish")){//投资人已经标记为已结项的项目(一个项目分很多次投资记录)
			return  dao.findInvestFinishRepayMemtList(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtBefore")){//投资人招标中的项目(一个项目分很多次投资记录)
			return  dao.findInvestBeforeRepayMemtList(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtBeforeFaild")){//投资人投资失败的项目(不是指投资项目失败，而是投资人本次投资失败)
			return  dao.findInvestBeforeRepayMemtList(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else{
			return  dao.findInvestRepayMemtList(memId,loginname,planId,repaymentType,null);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}
	}

	//投资人款项台账
	@Override
	public List<FundPay> findInvestRepayment(String loginname, Long memId,String planId, String repaymentType,HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(repaymentType.equals("InvestRepayment")){//投资人待还款项目(一个项目分很多次投资记录)
			return dao.findInvestRepayMemt(memId,loginname,planId,repaymentType,request);
		}else if(repaymentType.equals("InvestRepayMemtSignle")){//投资人一个具体投资记录的回款记录
			return dao.findInvestRepayMemt(memId,loginname,planId,repaymentType,null);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtFinish")){//投资人已经标记为已结项的项目(一个项目分很多次投资记录)
			return  dao.findInvestFinishRepayMemt(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtBefore")){//投资人招标中的项目(一个项目分很多次投资记录)
			return  dao.findInvestBeforeRepayMemt(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else if(repaymentType.equals("InvestRepayMemtBeforeFaild")){//投资人投资失败的项目(不是指投资项目失败，而是投资人本次投资失败)
			return  dao.findInvestBeforeRepayMemt(memId,loginname,planId,repaymentType,request);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}else{
			return  dao.findInvestRepayMemt(memId,loginname,planId,repaymentType,null);//dao.findLoanRepayMemtList(id,loginname,planId,loanType);
		}
	}

	@Override
	public BigDecimal getByRaiseinterest(Long id) {
		return dao.getByRaiseinterest(id);
	}


	@Override
	public List<BpFundIntent> findByPeriod(String bidId, String periodId) {
		// TODO Auto-generated method stub
		return dao.findByPeriodAndBidId(bidId,periodId);
	}
	@Override
	public BigDecimal getByPlanId(String bidId, String CustId) {
		// TODO Auto-generated method stub
		return dao.getByPlanId(bidId, CustId);
	}
	@Override
	public List<ShowManageMoney> listByGuarantorsId(Long guarantorsId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.listByGuarantorsId(guarantorsId, request);
	}
	@Override
	public List<ShowManageMoney> listByGuarantorsId1(Long guarantorsId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return dao.listByGuarantorsId(guarantorsId, request);
	}

	@Override
	public BigDecimal getMoneyByPlanDateType(String temp, String planId,
			String intentDate, String type) {
		// TODO Auto-generated method stub
		return dao.getMoneyByPlanDateType(temp, planId, intentDate, type);
	}
	@Override
	public BigDecimal getMoneyByPlanType(Long bidPlanId, String fundType,
			String intentDate) {
		// TODO Auto-generated method stub
		return dao.getMoneyByPlanType(bidPlanId, fundType, intentDate);
	}
	@Override
	public BigDecimal getPenaltyInterest(String temp, String planId,
			String peridId) {
		// TODO Auto-generated method stub
		return dao.getPenaltyInterest(temp, planId, peridId);
	}
	@Override
	public List<ShowManageMoney> listByByPlanIdDate(Long bidPlanId,
			String intentDate) {
		// TODO Auto-generated method stub
		return dao.listByByPlanIdDate(bidPlanId, intentDate);
	}
	@Override
	public List<BpFundIntent> listBybidPlanIdType(Long bidPlanId,
			String fundType) {
		// TODO Auto-generated method stub
		return dao.listBybidPlanIdType(bidPlanId, fundType);
	}
	@Override
	public BigDecimal sumAllCompensatoryMoney(String planId, String peridId,
			String fundType) {
		// TODO Auto-generated method stub
		return dao.sumAllCompensatoryMoney(planId, peridId, fundType);
	}
	@Override
	public List<BpFundIntent> getOverList(String bidId) {
		// TODO Auto-generated method stub
		return dao.getOverList(bidId);
	}

	@Override
	public BigDecimal findWaitCompensatoryMoney(Long guarantorsId) {
		// TODO Auto-generated method stub
		return dao.findWaitCompensatoryMoney(guarantorsId);
	}
	@Override
	public List<BpFundIntent> getListByBidIdAndIntent(String bidId, Date date) {
		// TODO Auto-generated method stub
		return dao.getListByBidIdAndIntent(bidId, date);
	}
	public BigDecimal getEarlyAfterMoney(String temp, String planId,
			String payintentPeriod, String type){
		return dao.getEarlyAfterMoney(temp, planId, payintentPeriod, type);
	}
	public boolean update(BpFundIntent temp){
		return dao.update(temp);
	}
	@Override
	public List<BpFundIntent> getIntentList(Long bidPlanId,
			Integer payintentPeriod) {
		return dao.getIntentList(bidPlanId, payintentPeriod);
	}
	@Override
	public List<BpFundIntent> getBySlEarlyRepaymentId(Long bidId){
		return dao.getBySlEarlyRepaymentId(bidId);
	}

	@Override
	public FundPay setMoneyAndRemain(FundPay fp) {

		String[] ret = mobileBidDynamic(fp);

		BigDecimal money = BigDecimal.valueOf(Double.valueOf(ret[0]));
		fp.setbAftermoney(money);
		fp.setRemainingTime(Long.valueOf(ret[1]));
		return fp;
	}




	public String[] mobileBidDynamic(FundPay fp){
		String[] ret = new String[2];
		//投标金额合计
		BigDecimal sumInvestMoney = investPersonInfoService.getSumInvestMoney(fp.getPlanId());
		//剩余可投金额
		BigDecimal afterMoney = fp.getBidMoney().subtract(sumInvestMoney);

		ret[0] = String.valueOf(afterMoney);
		if (fp.getBidEndTime() != null) {
			Date currTime = new Date();
			Date endTime = fp.getBidEndTime();
			long timeDelta = (endTime.getTime() - currTime.getTime()) / 1000;// 单位是秒
			ret[1] = String.valueOf(timeDelta);// 剩余时间
		}
		return ret;
	}
}
