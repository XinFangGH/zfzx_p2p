package com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager;

import java.math.BigDecimal;
import java.util.Date;

import com.zhiwei.core.model.BaseModel;

/**
 * 将线上线下投资客户的投资记录进行汇总
 * 这张实体表没有对应的hibernate  xml文件，也没有数据库对应数据表
 * @author LINYAN
 *
 */
public class Investproject extends BaseModel{
	public static final Short T_RECHARGE=Short.valueOf("1");//充值
	
	
    public static final Short type0=Short.valueOf("0");//0 线上客户
    public static final Short type1=Short.valueOf("1");//1 线下客户
    
    public static final String PROTYPEBD="B_Dir";//企业类型直投标
    public static final String PROTYPEPD="P_Dir";//个人类型直投标
    public static final String PROTYPEBO="B_Or";//企业类型债权标
    public static final String PROTYPEPO="P_Or";//个人类型债权标
    public static final String PROTYPEPRODUCT="mmproduce";//理财产品
    public static final String PROTYPEPLAN="mmplan";//理财计划
    
    public static final String PLANTABLEMONEY="pl_managemoney_plan";//理财类表
    public static final String PLANTABLEPROJECT="pl_bid_plan";//投标类表
    
	private Long infoId;//投资人投资记录id （读取两张表）
	private Long planId;//投资人投资计划id 分理财产品，理财计划，债权标，直投标等
	private String proType; //投资人投资产品的类型:分为直投标，债权标，理财产品，理财计划
	private String bidName;//投资项目名称
	private Short userType;// 投资客户类型：分为线上客户，线下客户
	private Long userId;//投资客户id
	private String userName; //投资客户姓名
	private BigDecimal userMoney; //投资客户投资金额
	private Date bidtime;//投资客户投资时间
	private Integer periodTime;//投资期限
	private BigDecimal yeaRate;//投资年化收益率
	private Short state;//投资状态
	private String planTable;//投资项目所在的数据表
	private String orderNo;
	private Short pstate;//标的状态
	private Integer count;//投资次数
    private String contract;
    private Long investId;




	private String stateShow;//投资状态别名
	private String cardcode;//身份证

	private String trueName;

	private String bidtimeStr;

	private String telphone;//手机号

	private Short isOtherFlow;

	private String contractNo;//合同编号
	private Date startinInterestTime;
	private Date endinInterestTime;


	private Long runId;
	private Date earlierOutDate;//提前退出时间

	//投资详细日期  XF
	private  String  payaccrualType;

	public String getPayaccrualType() {
		return payaccrualType;
	}

	public void setPayaccrualType(String payaccrualType) {
		this.payaccrualType = payaccrualType;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Short getIsOtherFlow() {
		return isOtherFlow;
	}

	public void setIsOtherFlow(Short isOtherFlow) {
		this.isOtherFlow = isOtherFlow;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getStartinInterestTime() {
		return startinInterestTime;
	}

	public void setStartinInterestTime(Date startinInterestTime) {
		this.startinInterestTime = startinInterestTime;
	}

	public Date getEndinInterestTime() {
		return endinInterestTime;
	}

	public void setEndinInterestTime(Date endinInterestTime) {
		this.endinInterestTime = endinInterestTime;
	}

	public Date getEarlierOutDate() {
		return earlierOutDate;
	}

	public void setEarlierOutDate(Date earlierOutDate) {
		this.earlierOutDate = earlierOutDate;
	}


	public String getStateShow() {
		return stateShow;
	}

	public void setStateShow(String stateShow) {
		this.stateShow = stateShow;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getBidtimeStr() {
		return bidtimeStr;
	}

	public void setBidtimeStr(String bidtimeStr) {
		this.bidtimeStr = bidtimeStr;
	}


	public Long getInvestId() {
		return investId;
	}
	public void setInvestId(Long investId) {
		this.investId = investId;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Short getPstate() {
		return pstate;
	}
	public void setPstate(Short pstate) {
		this.pstate = pstate;
	}

	private BigDecimal incomeMoney;
	

	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	private java.util.Date startIntentDate;
	private java.util.Date endIntentDate;
	
	public BigDecimal getIncomeMoney() {
		return incomeMoney;
	}
	public void setIncomeMoney(BigDecimal incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	public java.util.Date getStartIntentDate() {
		return startIntentDate;
	}
	public void setStartIntentDate(java.util.Date startIntentDate) {
		this.startIntentDate = startIntentDate;
	}
	public java.util.Date getEndIntentDate() {
		return endIntentDate;
	}
	public void setEndIntentDate(java.util.Date endIntentDate) {
		this.endIntentDate = endIntentDate;
	}

	private Long timeValue;//距离当前的时间
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getBidName() {
		return bidName;
	}
	public void setBidName(String bidName) {
		this.bidName = bidName;
	}
	public Short getUserType() {
		return userType;
	}
	public void setUserType(Short userType) {
		this.userType = userType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	public Date getBidtime() {
		return bidtime;
	}
	public void setBidtime(Date bidtime) {
		this.bidtime = bidtime;
	}
	public Integer getPeriodTime() {
		return periodTime;
	}
	public void setPeriodTime(Integer periodTime) {
		this.periodTime = periodTime;
	}
	public BigDecimal getYeaRate() {
		return yeaRate;
	}
	public void setYeaRate(BigDecimal yeaRate) {
		this.yeaRate = yeaRate;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public String getPlanTable() {
		return planTable;
	}
	public void setPlanTable(String planTable) {
		this.planTable = planTable;
	}
	public void setTimeValue(Long timeValue) {
		this.timeValue = timeValue;
	}
	public Long getTimeValue() {
		return timeValue;
	}
	
}