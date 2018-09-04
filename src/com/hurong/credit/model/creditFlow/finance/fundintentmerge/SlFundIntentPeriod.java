package com.hurong.credit.model.creditFlow.finance.fundintentmerge;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import com.google.gson.annotations.Expose;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.finance.FundIntent;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;
import com.hurong.credit.model.creditFlow.finance.fundintentmerge.annotation.FundType;

public  class SlFundIntentPeriod extends FundIntentPeriod {
//	public final static Comparator comparator= new FundIntentPeriodComparator();//用来给期数排序
	
	public SlFundIntentPeriod(){
		
	}
	
	protected String ids;
	
	
	protected Long fundProjectId;
	protected Long planId;
	protected String bidPlanName;
	protected String bidPlanProjectNum;
	protected Long borrowId;
	protected String borrowName;
	protected Long ptpborrowId;
	protected String ptpborrowName;
	protected Short repaySource;
	private Short authorizationStatus;//是否授权，1表示已经授权，null表示没有授权或者取消了授权
	
	private String url ;//合同下载路径
	private BigDecimal comprehensiveMoney;//综合服务费
	private BigDecimal allLoanMoney;//应还总额
	private BigDecimal punishInterestMoney;//当前罚息

	
	
	//设置初始值的原因，如果principalRepayment为null那么gson中 principalRepayment的属性不会显示，所以出示一个都为null的飞空对象，通过isNull属性来判断是否为新加对象
	//也可以通过principalRepayment==SlFundIntent.slFundIntentNull；来判断
	@FundType(name="principalRepayment",prior=FundType.priorLevel.One)@Expose
	public SlFundIntent principalRepayment = new SlFundIntent();//本金偿还
	
	@FundType(name="loanInterest",prior=FundType.priorLevel.Two)@Expose
	public SlFundIntent loanInterest = new SlFundIntent();//利息
	
	@FundType(name="consultationMoney",prior=FundType.priorLevel.Three)@Expose
	public SlFundIntent consultationMoney = new SlFundIntent();//咨询服务费
	
	@FundType(name="principalLending",prior=FundType.priorLevel.None)@Expose
	public SlFundIntent principalLending = new SlFundIntent();//本金放款 1条记录
	
	@FundType(name="serviceMoney",prior=FundType.priorLevel.Four)@Expose
	public SlFundIntent serviceMoney = new SlFundIntent();
	
	@FundType(name="interestPenalty",prior=FundType.priorLevel.Five)@Expose
	public BpFundIntent interestPenalty = new BpFundIntent();
	
	
	
	
	
	public void initSlFundIntentPeriod(List<BpFundIntent> bpfundlist){
		this.ids="";
		principalRepayment.setIncomeMoney(new BigDecimal("0"));
		principalRepayment.setAfterMoney(new BigDecimal("0"));
		principalRepayment.setAccrualMoney(new BigDecimal("0"));
		
		loanInterest.setIncomeMoney(new BigDecimal("0"));
		loanInterest.setAfterMoney(new BigDecimal("0"));
		loanInterest.setAccrualMoney(new BigDecimal("0"));
		
		consultationMoney.setIncomeMoney(new BigDecimal("0"));
		consultationMoney.setAfterMoney(new BigDecimal("0"));
		consultationMoney.setAccrualMoney(new BigDecimal("0"));
		
		serviceMoney.setIncomeMoney(new BigDecimal("0"));
		serviceMoney.setAfterMoney(new BigDecimal("0"));
		serviceMoney.setAccrualMoney(new BigDecimal("0"));
		
		interestPenalty.setIncomeMoney(new BigDecimal("0"));
		interestPenalty.setAfterMoney(new BigDecimal("0"));
		interestPenalty.setAccrualMoney(new BigDecimal("0"));
		int i=0;
		while(i<bpfundlist.size()){
			BpFundIntent fp=bpfundlist.get(i);
			if(fp.getFundType().equals("principalRepayment")){
				principalRepayment.setIncomeMoney(principalRepayment.getIncomeMoney().add(fp.getIncomeMoney()));
				principalRepayment.setAfterMoney(principalRepayment.getAfterMoney().add(fp.getAfterMoney()));
				principalRepayment.setAccrualMoney(principalRepayment.getAccrualMoney().add(fp.getAccrualMoney()));
			}else if(fp.getFundType().equals("loanInterest")){
				loanInterest.setIncomeMoney(loanInterest.getIncomeMoney().add(fp.getIncomeMoney()));
				loanInterest.setAfterMoney(loanInterest.getAfterMoney().add(fp.getAfterMoney()));
				loanInterest.setAccrualMoney(loanInterest.getAccrualMoney().add(fp.getAccrualMoney()));
			}else if(fp.getFundType().equals("consultationMoney")){
				consultationMoney.setIncomeMoney(consultationMoney.getIncomeMoney().add(fp.getIncomeMoney()));
				consultationMoney.setAfterMoney(consultationMoney.getAfterMoney().add(fp.getAfterMoney()));
				consultationMoney.setAccrualMoney(consultationMoney.getAccrualMoney().add(fp.getAccrualMoney()));
			}else if(fp.getFundType().equals("serviceMoney")){
				serviceMoney.setIncomeMoney(serviceMoney.getIncomeMoney().add(fp.getIncomeMoney()));
				serviceMoney.setAfterMoney(serviceMoney.getAfterMoney().add(fp.getAfterMoney()));
				serviceMoney.setAccrualMoney(serviceMoney.getAccrualMoney().add(fp.getAccrualMoney()));
			}else if(fp.getFundType().equals("interestPenalty")){
				interestPenalty.setIncomeMoney(interestPenalty.getIncomeMoney().add(fp.getIncomeMoney()));
				interestPenalty.setAfterMoney(interestPenalty.getAfterMoney().add(fp.getAfterMoney()));
				interestPenalty.setAccrualMoney(interestPenalty.getAccrualMoney().add(fp.getAccrualMoney()));
			}
			i++;
			//初始化ids
			 if(!this.ids.contains(fp.getOrderNo())){
			    	
				 this.ids=this.ids+","+fp.getOrderNo();
		    }
		}
		
		
		this.ids=this.ids.substring(1,this.ids.length());
		
		
	}
	
	
	
	
	public Short getRepaySource() {
		return repaySource;
	}




	public void setRepaySource(Short repaySource) {
		this.repaySource = repaySource;
	}




	public BigDecimal getAllLoanMoney() {
		return allLoanMoney;
	}

	public void setAllLoanMoney(BigDecimal allLoanMoney) {
		this.allLoanMoney = allLoanMoney;
	}

	public BigDecimal getPunishInterestMoney() {
		return punishInterestMoney;
	}

	public void setPunishInterestMoney(BigDecimal punishInterestMoney) {
		this.punishInterestMoney = punishInterestMoney;
	}

	public Long getFundProjectId() {
		return fundProjectId;
	}

	public void setFundProjectId(Long fundProjectId) {
		this.fundProjectId = fundProjectId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getComprehensiveMoney() {
		return comprehensiveMoney;
	}

	public void setComprehensiveMoney(BigDecimal comprehensiveMoney) {
		this.comprehensiveMoney = comprehensiveMoney;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBidPlanName() {
		return bidPlanName;
	}

	public void setBidPlanName(String bidPlanName) {
		this.bidPlanName = bidPlanName;
	}

	public String getBidPlanProjectNum() {
		return bidPlanProjectNum;
	}

	public void setBidPlanProjectNum(String bidPlanProjectNum) {
		this.bidPlanProjectNum = bidPlanProjectNum;
	}

	public SlFundIntent getPrincipalRepayment() {
		return principalRepayment;
	}

	public void setPrincipalRepayment(SlFundIntent principalRepayment) {
		this.principalRepayment = principalRepayment;
	}

	public SlFundIntent getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(SlFundIntent loanInterest) {
		this.loanInterest = loanInterest;
	}

	public SlFundIntent getConsultationMoney() {
		return consultationMoney;
	}

	public void setConsultationMoney(SlFundIntent consultationMoney) {
		this.consultationMoney = consultationMoney;
	}

	public SlFundIntent getPrincipalLending() {
		return principalLending;
	}

	public void setPrincipalLending(SlFundIntent principalLending) {
		this.principalLending = principalLending;
	}

	public SlFundIntent getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(SlFundIntent serviceMoney) {
		this.serviceMoney = serviceMoney;
	}




	public Long getBorrowId() {
		return borrowId;
	}




	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}




	public String getBorrowName() {
		return borrowName;
	}




	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}




	public Long getPtpborrowId() {
		return ptpborrowId;
	}




	public void setPtpborrowId(Long ptpborrowId) {
		this.ptpborrowId = ptpborrowId;
	}




	public String getPtpborrowName() {
		return ptpborrowName;
	}




	public void setPtpborrowName(String ptpborrowName) {
		this.ptpborrowName = ptpborrowName;
	}




	public BpFundIntent getInterestPenalty() {
		return interestPenalty;
	}




	public void setInterestPenalty(BpFundIntent interestPenalty) {
		this.interestPenalty = interestPenalty;
	}




	public Short getAuthorizationStatus() {
		return authorizationStatus;
	}




	public void setAuthorizationStatus(Short authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}




	
	
	
	
}
