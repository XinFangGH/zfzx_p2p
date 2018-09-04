package com.hurong.credit.model.creditFlow.financingAgency;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */

import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */

/**
 * PlBidPlan Base Java Bean, base class for the.credit.model, mapped directly to
 * database table
 * <p>
 * Avoid changing this file if not necessary, will be overwritten.
 * <p>
 * 招标计划
 */
public class PlBidPlan extends com.hurong.core.model.BaseModel {
    // -1 关闭 0 未发布 1招标中 2已齐标 3已流标4已过期 5 还款中，不需要发布 6办理中 7还款中10已完成
    /*
     * 关闭状态
     */
    public static final int STATE_1 = -1;
    /*
     * 未发标
     */
    public static final int STATE0 = 0;
    /*
     * 招标中
     */
    public static final int STATE1 = 1;
    /*
     * 已齐标
     */
    public static final int STATE2 = 2;
    /*
     * 已流标
     */
    public static final int STATE3 = 3;
    /**
     * 已过期
     */
    public static final int STATE4 = 4;

    /*
     * 自动匹配债权，不需要发布
     */
    public static final int STATE5 = 5;
    /*
     * 已经启动过流程 不能重复启动 起息办理中
     */
    public static final int STATE6 = 6;

    /**
     * 还款中
     */
    public static final int STATE7 = 7;
    /**
     * 已完成
     */
    public static final int STATE10 = 10;

    @Expose
    protected Long bidId;
    @Expose
    protected String bidProName;
    @Expose
    protected String bidProNumber;
    @Expose
    protected String proType;
    @Expose
    protected java.math.BigDecimal bidMoney;
    @Expose
    protected Double bidMoneyScale;
    @Expose
    protected java.math.BigDecimal startMoney;
    @Expose
    protected java.math.BigDecimal riseMoney;
    @Expose
    protected java.util.Date createtime;
    @Expose
    protected java.util.Date updatetime;
    @Expose
    protected Integer state;
    @Expose
    protected Integer startInterestType;
    @Expose
    protected Integer bidStartTime;
    @Expose
    protected java.util.Date publishSingeTime;
    @Expose
    protected java.util.Date bidEndTime;
    @Expose
    protected java.util.Date fullTime;
    @Expose
    protected java.util.Date startinInterestTime;
    @Expose
    protected String bidRemark;
    @Expose
    protected String htmlPath;
    @Expose
    protected String proKeepType;// 包装借款项目类型
    @Expose
    protected String proKeeyRemark;// 借款项目类型

    @Expose
    protected String businessFrom;// 业务来源

    @Expose
    protected String creditLevel;// 包装借款项目信用等级
    @Expose
    protected double progress;// 投标进度
    @Expose
    protected int persionNum;// 投标人数
    @Expose
    protected double afterMoney;// 剩余金额
    @Expose
    protected String orgName;// 合作机构名称
    @Expose
    protected String orgKeyStr;// 合作机构 key 多容易用来判断 是典 还是直

    @Expose
    protected BigDecimal loanTotalMoney; // 借款总额 多容易用到
    @Expose
    protected BigDecimal orgMoney; // 合作机构金额 多容易用到

    @Expose
    protected String laveTime;
    protected String keepProtypeName;// 借款类型

    @Expose
    protected Short isLoan;// 是否放款，1：已放款

    @Expose
    protected java.util.Date startIntentDate;// 起息日期
    @Expose
    protected java.util.Date endIntentDate;// 止息日期

    @Expose
    protected String assureType = ""; // 担保方式

    protected java.util.Date prepareSellTime;// 标的预售时间

    @Expose
    protected String projectStatus = "";// 项目状态
    @Expose
    protected String mortgage = "";// 抵押担保物类别
    @Expose
    protected String loanLife;// svn：songwj
    @Expose
    public String Explain;// 标的说明svn：songwj
    @Expose
    public String acctulData;// 周期svn：songwj
    @Expose
    public String repaymentPeriod;// 还款期限svn：songwj

    private Long remainingTime;// 剩余时间，单位是毫秒，用于js计算

    private Date intentDate;

    private String bidFullTime;// 满标用时
    private String keepCreditlevelName;// 信用等级

    private String notRepaymentMoney;// 待还本息
    private String repayMentLength;// 还款进度
    private String nextRepaymentDate;// 下次还款日
    private String repaymentFullDate;// 还清时间
    @Expose
    protected BigDecimal maxMoney;// 投资上限
    private String url;// 合同下载路径
    private String nowTimeStr;// 当前时间格式化
    private String preSaleTimeStr;// 投标时间格式化
    private String investOrderNo;// 订单编号
    /**
     * 是否为vip 0 否 1是
     *
     * @return
     */
    protected Short isVip;

    private String requestNo;// 授权标的自动还款流水号
    @Expose
    private Short authorizationStatus;// 是否授权，1表示已经授权，null表示没有授权或者取消了授权

    protected Integer penaltyDays;// 补偿息天数

    @Expose
    protected BigDecimal addRate;// 加息利率

    /**
     * 展示加息利率
     */
    @Expose
    protected BigDecimal showRate;//展示加息利率
    @Expose
    protected BigDecimal returnRatio;// 返现比例
    @Expose
    protected Integer coupon;// 是否可用优惠券 1是，0否
    @Expose
    protected Integer novice;// 是否新手专享 1是，0否

    // 2015-06-02新增字段
    @Expose
    protected String receiverName;// 表示收款人姓名，债券标表示原始债权人姓名，直投标表示借款人姓名
    @Expose
    protected String receiverP2PAccountNumber;// 表示收款人P2P账号，债券标表示原始债权人P2P账号，直投标表示借款人P2P账号

    @Expose
    protected BigDecimal maxCouponMoney;// 单笔最大面值
    @Expose
    protected Integer rebateType;// 返利类型 1=返现 ，2=返息，3=饭息现，4=加息
    @Expose
    protected Integer rebateWay;// 返利方式 1=立现 ，2=随期，3=到期
    @Expose
    protected BigDecimal raiseRate;// 募集期利率

    @Expose
    protected java.math.BigDecimal yearInterestRate;// 年化利率
    @Expose
    protected String loanLifeQuery;// 期限查询/债权标期限获取

    protected String logoURL;// logoUrl
    /**
     * 担保机构Id
     */
    protected Long guarantorsId;// 担保机构Id
    /**
     * 保障方式['本金保障',1],['本息保障',2],['全部保障',3]
     */
    protected String guaranteeWay;
    /**
     * 担保费率
     */
    protected BigDecimal guaranteeRate;
    /**
     * 保费收取方式：1为一次性前置收取，2为随期收取
     */
    protected String guaranteeFeeWay;

    /**   富滇银行对接保存数据     */

    /**
     * 发标的订单日期
     */
    protected String loanOrderDate;
    /**
     * 发标的订单流水号
     */
    protected String loanOrderNo;
    /**
     * 三方返回的标的号
     */
    protected String loanTxNo;

    /**
     * 三方返回的标的号
     */
    protected String loanAccNo;

    /**
     * 版本类型，用来控制前台显示样式
     */
    protected Short versionType;

    /*标的天数*/
    protected Integer cycle;



    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Short getVersionType() {
        return versionType;
    }

    public void setVersionType(Short versionType) {
        this.versionType = versionType;
    }

    public BigDecimal getShowRate() {
        return showRate;
    }

    public void setShowRate(BigDecimal showRate) {
        this.showRate = showRate;
    }


    public String getLoanAccNo() {
        return loanAccNo;
    }

    public void setLoanAccNo(String loanAccNo) {
        this.loanAccNo = loanAccNo;
    }

    public String getLoanOrderDate() {
        return loanOrderDate;
    }

    public void setLoanOrderDate(String loanOrderDate) {
        this.loanOrderDate = loanOrderDate;
    }

    public String getLoanOrderNo() {
        return loanOrderNo;
    }

    public void setLoanOrderNo(String loanOrderNo) {
        this.loanOrderNo = loanOrderNo;
    }

    public String getLoanTxNo() {
        return loanTxNo;
    }

    public void setLoanTxNo(String loanTxNo) {
        this.loanTxNo = loanTxNo;
    }

    public Short getIsVip() {
        return isVip;
    }

    public void setIsVip(Short isVip) {
        this.isVip = isVip;
    }

    public Long getGuarantorsId() {
        return guarantorsId;
    }

    public void setGuarantorsId(Long guarantorsId) {
        this.guarantorsId = guarantorsId;
    }

    public String getGuaranteeWay() {
        return guaranteeWay;
    }

    public void setGuaranteeWay(String guaranteeWay) {
        this.guaranteeWay = guaranteeWay;
    }

    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(BigDecimal guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public String getGuaranteeFeeWay() {
        return guaranteeFeeWay;
    }

    public void setGuaranteeFeeWay(String guaranteeFeeWay) {
        this.guaranteeFeeWay = guaranteeFeeWay;
    }

    public BigDecimal getRaiseRate() {
        return raiseRate;
    }

    public void setRaiseRate(BigDecimal raiseRate) {
        this.raiseRate = raiseRate;
    }

    public BigDecimal getMaxCouponMoney() {
        return maxCouponMoney;
    }

    public void setMaxCouponMoney(BigDecimal maxCouponMoney) {
        this.maxCouponMoney = maxCouponMoney;
    }

    public Integer getRebateType() {
        return rebateType;
    }

    public void setRebateType(Integer rebateType) {
        this.rebateType = rebateType;
    }

    public Integer getRebateWay() {
        return rebateWay;
    }

    public void setRebateWay(Integer rebateWay) {
        this.rebateWay = rebateWay;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverP2PAccountNumber() {
        return receiverP2PAccountNumber;
    }

    public void setReceiverP2PAccountNumber(String receiverP2PAccountNumber) {
        this.receiverP2PAccountNumber = receiverP2PAccountNumber;
    }

    public Integer getNovice() {
        return novice;
    }

    public void setNovice(Integer novice) {
        this.novice = novice;
    }

    public BigDecimal getAddRate() {
        return addRate;
    }

    public void setAddRate(BigDecimal addRate) {
        this.addRate = addRate;
    }

    public BigDecimal getReturnRatio() {
        return returnRatio;
    }

    public void setReturnRatio(BigDecimal returnRatio) {
        this.returnRatio = returnRatio;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public String getInvestOrderNo() {
        return investOrderNo;
    }

    public void setInvestOrderNo(String investOrderNo) {
        this.investOrderNo = investOrderNo;
    }

    public Integer getPenaltyDays() {
        return penaltyDays;
    }

    public void setPenaltyDays(Integer penaltyDays) {
        this.penaltyDays = penaltyDays;
    }

    public String getKeepCreditlevelName() {
        return keepCreditlevelName;
    }

    public void setKeepCreditlevelName(String keepCreditlevelName) {
        this.keepCreditlevelName = keepCreditlevelName;
    }

    public String getNowTimeStr() {
        return nowTimeStr;
    }

    public void setNowTimeStr(String nowTimeStr) {
        this.nowTimeStr = nowTimeStr;
    }

    public String getPreSaleTimeStr() {
        return preSaleTimeStr;
    }

    public void setPreSaleTimeStr(String preSaleTimeStr) {
        this.preSaleTimeStr = preSaleTimeStr;
    }

    public java.util.Date getPrepareSellTime() {
        return prepareSellTime;
    }

    public void setPrepareSellTime(java.util.Date prepareSellTime) {
        this.prepareSellTime = prepareSellTime;
    }

    public String getKeepProtypeName() {
        return keepProtypeName;
    }

    public void setKeepProtypeName(String keepProtypeName) {
        this.keepProtypeName = keepProtypeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getNotRepaymentMoney() {
        return notRepaymentMoney;
    }

    public void setNotRepaymentMoney(String notRepaymentMoney) {
        this.notRepaymentMoney = notRepaymentMoney;
    }

    public String getRepayMentLength() {
        return repayMentLength;
    }

    public void setRepayMentLength(String repayMentLength) {
        this.repayMentLength = repayMentLength;
    }

    public String getNextRepaymentDate() {
        return nextRepaymentDate;
    }

    public void setNextRepaymentDate(String nextRepaymentDate) {
        this.nextRepaymentDate = nextRepaymentDate;
    }

    public String getRepaymentFullDate() {
        return repaymentFullDate;
    }

    public void setRepaymentFullDate(String repaymentFullDate) {
        this.repaymentFullDate = repaymentFullDate;
    }

    public String getBidFullTime() {
        return bidFullTime;
    }

    public void setBidFullTime(String bidFullTime) {
        this.bidFullTime = bidFullTime;
    }

    public Date getIntentDate() {
        return intentDate;
    }

    public void setIntentDate(Date intentDate) {
        this.intentDate = intentDate;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getAcctulData() {
        return acctulData;
    }

    public void setAcctulData(String acctulData) {
        this.acctulData = acctulData;
    }

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getLoanLife() {
        return loanLife;
    }

    public void setLoanLife(String loanLife) {
        this.loanLife = loanLife;
    }

    public String getMortgage() {
        return mortgage;
    }

    public void setMortgage(String mortgage) {
        this.mortgage = mortgage;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getAssureType() {
        return assureType;
    }

    public void setAssureType(String assureType) {
        this.assureType = assureType;
    }

    public String getProKeeyRemark() {
        return proKeeyRemark;
    }

    public Short getIsLoan() {
        return isLoan;
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

    public void setIsLoan(Short isLoan) {
        this.isLoan = isLoan;
    }

    public void setProKeeyRemark(String proKeeyRemark) {
        this.proKeeyRemark = proKeeyRemark;
    }

    public String getLaveTime() {
        return laveTime;
    }

    public void setLaveTime(String laveTime) {
        this.laveTime = laveTime;
    }

    public BigDecimal getLoanTotalMoney() {
        return loanTotalMoney;
    }

    public void setLoanTotalMoney(BigDecimal loanTotalMoney) {
        this.loanTotalMoney = loanTotalMoney;
    }

    public BigDecimal getOrgMoney() {
        return orgMoney;
    }

    public void setOrgMoney(BigDecimal orgMoney) {
        this.orgMoney = orgMoney;
    }

    public String getBusinessFrom() {
        return businessFrom;
    }

    public void setBusinessFrom(String businessFrom) {
        this.businessFrom = businessFrom;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgKeyStr() {
        return orgKeyStr;
    }

    public void setOrgKeyStr(String orgKeyStr) {
        this.orgKeyStr = orgKeyStr;
    }

    @Expose
    protected String payIntersetWay; //
    @Expose
    protected Integer payMoneyTime; // 还款时间
    @Expose
    protected String payMoneyTimeType; // 还款时间方式 D 天/M月/Q季/Y年
    private String educationSchool;// 毕业学校

    private String theWayBack;// 返款方式
    private java.math.BigDecimal interestRate;// 年华利率
    private java.util.Date investmentTime;// 个人中心--总览---投资时间
    private java.math.BigDecimal backMoneyEd;// 个人中心--总览---已回款
    private java.math.BigDecimal backMoneyIng;// 个人中心--总览---待回款
    private java.math.BigDecimal userMoney;// 个人中心--总览---金额

    private Integer allCount;// 个人中心--总览---还款进度总数
    private Integer nowCount;// 个人中心--总览---还款总数
    private java.util.Date nextTime;// 个人中心--总览---下期还款时间
    private java.math.BigDecimal nextMoney;// 个人中心--总览---下期还款总额
    private Integer isDate;// 个人中心--总览---是否逾期，0否1是
    private java.math.BigDecimal LoanMoney;// 个人中心--总览---总额

    private java.math.BigDecimal afterMoneyTotal;// 个人中心--理财管理--已回款
    private java.math.BigDecimal notMoneyTotal;// 个人中心--理财管理--待回款

    private java.math.BigDecimal financialAfterMoney;// 理财回款-
    private java.math.BigDecimal financialPayMoney;// 理财回款-
    private java.math.BigDecimal financialNotMoney;// 理财回款-

    public String getEducationSchool() {
        return educationSchool;
    }

    public void setEducationSchool(String educationSchool) {
        this.educationSchool = educationSchool;
    }

    public java.math.BigDecimal getLoanMoney() {
        return LoanMoney;
    }

    public void setLoanMoney(java.math.BigDecimal loanMoney) {
        LoanMoney = loanMoney;
    }

    public java.math.BigDecimal getFinancialAfterMoney() {
        return financialAfterMoney;
    }

    public void setFinancialAfterMoney(java.math.BigDecimal financialAfterMoney) {
        this.financialAfterMoney = financialAfterMoney;
    }

    public java.math.BigDecimal getFinancialPayMoney() {
        return financialPayMoney;
    }

    public void setFinancialPayMoney(java.math.BigDecimal financialPayMoney) {
        this.financialPayMoney = financialPayMoney;
    }

    public java.math.BigDecimal getFinancialNotMoney() {
        return financialNotMoney;
    }

    public void setFinancialNotMoney(java.math.BigDecimal financialNotMoney) {
        this.financialNotMoney = financialNotMoney;
    }

    public java.math.BigDecimal getAfterMoneyTotal() {
        return afterMoneyTotal;
    }

    public void setAfterMoneyTotal(java.math.BigDecimal afterMoneyTotal) {
        this.afterMoneyTotal = afterMoneyTotal;
    }

    public java.math.BigDecimal getNotMoneyTotal() {
        return notMoneyTotal;
    }

    public void setNotMoneyTotal(java.math.BigDecimal notMoneyTotal) {
        this.notMoneyTotal = notMoneyTotal;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getNowCount() {
        return nowCount;
    }

    public void setNowCount(Integer nowCount) {
        this.nowCount = nowCount;
    }

    public java.util.Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(java.util.Date nextTime) {
        this.nextTime = nextTime;
    }

    public java.math.BigDecimal getNextMoney() {
        return nextMoney;
    }

    public void setNextMoney(java.math.BigDecimal nextMoney) {
        this.nextMoney = nextMoney;
    }

    public Integer getIsDate() {
        return isDate;
    }

    public void setIsDate(Integer isDate) {
        this.isDate = isDate;
    }

    public java.math.BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(java.math.BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public java.math.BigDecimal getBackMoneyEd() {
        return backMoneyEd;
    }

    public void setBackMoneyEd(java.math.BigDecimal backMoneyEd) {
        this.backMoneyEd = backMoneyEd;
    }

    public java.math.BigDecimal getBackMoneyIng() {
        return backMoneyIng;
    }

    public void setBackMoneyIng(java.math.BigDecimal backMoneyIng) {
        this.backMoneyIng = backMoneyIng;
    }

    public java.util.Date getInvestmentTime() {
        return investmentTime;
    }

    public void setInvestmentTime(java.util.Date investmentTime) {
        this.investmentTime = investmentTime;
    }

    public java.math.BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(java.math.BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getTheWayBack() {
        return theWayBack;
    }

    public void setTheWayBack(String theWayBack) {
        this.theWayBack = theWayBack;
    }

    public String getPayIntersetWay() {
        return payIntersetWay;
    }

    public void setPayIntersetWay(String payIntersetWay) {
        this.payIntersetWay = payIntersetWay;
    }

    public Integer getPayMoneyTime() {
        return payMoneyTime;
    }

    public void setPayMoneyTime(Integer payMoneyTime) {
        this.payMoneyTime = payMoneyTime;
    }

    public String getPayMoneyTimeType() {
        return payMoneyTimeType;
    }

    public java.util.Date getFullTime() {
        return fullTime;
    }

    public void setFullTime(java.util.Date fullTime) {
        this.fullTime = fullTime;
    }

    public java.util.Date getStartinInterestTime() {
        return startinInterestTime;
    }

    public void setStartinInterestTime(java.util.Date startinInterestTime) {
        this.startinInterestTime = startinInterestTime;
    }

    public void setPayMoneyTimeType(String payMoneyTimeType) {
        this.payMoneyTimeType = payMoneyTimeType;
    }

    public int getPersionNum() {
        return persionNum;
    }

    public void setPersionNum(int persionNum) {
        this.persionNum = persionNum;
    }

    public double getAfterMoney() {
        return afterMoney;
    }

    public void setAfterMoney(double afterMoney) {
        this.afterMoney = afterMoney;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getProKeepType() {
        return proKeepType;
    }

    public void setProKeepType(String proKeepType) {
        this.proKeepType = proKeepType;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    @Expose
    protected com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro bpPersionOrPro;
    @Expose
    protected com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType plBiddingType;
    @Expose
    protected com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro bpBusinessDirPro;
    @Expose
    protected com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro bpPersionDirPro;
    @Expose
    protected com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro bpBusinessOrPro;

    protected java.util.Set plBidInfos = new java.util.HashSet();

    protected Integer detailHistoryNum;// 历史借款笔数
    protected Integer detailRepaymentNum;// 还清笔数
    protected Integer detailNowNum;// 当前逾期笔数
    protected java.math.BigDecimal detailThisMoney;// 本次借款总数
    protected java.math.BigDecimal detailNameMoney;// 借款总额

    private Double monthIncome;// 标详情页，月收入
    private String houseAssets;// 标详情页，房产
    private String houseLoan;// 标详情页，房贷
    private String carAssets;// 标详情页，车产
    private String carLoan;// 标详情页，车贷

    public Double getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(Double monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getHouseAssets() {
        return houseAssets;
    }

    public void setHouseAssets(String houseAssets) {
        this.houseAssets = houseAssets;
    }

    public String getHouseLoan() {
        return houseLoan;
    }

    public void setHouseLoan(String houseLoan) {
        this.houseLoan = houseLoan;
    }

    public String getCarAssets() {
        return carAssets;
    }

    public void setCarAssets(String carAssets) {
        this.carAssets = carAssets;
    }

    public String getCarLoan() {
        return carLoan;
    }

    public void setCarLoan(String carLoan) {
        this.carLoan = carLoan;
    }

    public Integer getDetailHistoryNum() {
        return detailHistoryNum;
    }

    public void setDetailHistoryNum(Integer detailHistoryNum) {
        this.detailHistoryNum = detailHistoryNum;
    }

    public Integer getDetailRepaymentNum() {
        return detailRepaymentNum;
    }

    public void setDetailRepaymentNum(Integer detailRepaymentNum) {
        this.detailRepaymentNum = detailRepaymentNum;
    }

    public Integer getDetailNowNum() {
        return detailNowNum;
    }

    public void setDetailNowNum(Integer detailNowNum) {
        this.detailNowNum = detailNowNum;
    }

    public java.math.BigDecimal getDetailThisMoney() {
        return detailThisMoney;
    }

    public void setDetailThisMoney(java.math.BigDecimal detailThisMoney) {
        this.detailThisMoney = detailThisMoney;
    }

    public java.math.BigDecimal getDetailNameMoney() {
        return detailNameMoney;
    }

    public void setDetailNameMoney(java.math.BigDecimal detailNameMoney) {
        this.detailNameMoney = detailNameMoney;
    }

    /**
     * Default Empty Constructor for class PlBidPlan
     */
    public PlBidPlan() {
        super();
    }

    /**
     * Default Key Fields Constructor for class PlBidPlan
     */
    public PlBidPlan(Long in_bidId) {
        this.setBidId(in_bidId);
    }

    public com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro getBpPersionOrPro() {
        return bpPersionOrPro;
    }

    public void setBpPersionOrPro(
            com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro in_bpPersionOrPro) {
        this.bpPersionOrPro = in_bpPersionOrPro;
    }

    public com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType getPlBiddingType() {
        return plBiddingType;
    }

    public void setPlBiddingType(
            com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType in_plBiddingType) {
        this.plBiddingType = in_plBiddingType;
    }

    public com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro getBpBusinessDirPro() {
        return bpBusinessDirPro;
    }

    public void setBpBusinessDirPro(
            com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro in_bpBusinessDirPro) {
        this.bpBusinessDirPro = in_bpBusinessDirPro;
    }

    public com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro getBpPersionDirPro() {
        return bpPersionDirPro;
    }

    public void setBpPersionDirPro(
            com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro in_bpPersionDirPro) {
        this.bpPersionDirPro = in_bpPersionDirPro;
    }

    public com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro getBpBusinessOrPro() {
        return bpBusinessOrPro;
    }

    public void setBpBusinessOrPro(
            com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro in_bpBusinessOrPro) {
        this.bpBusinessOrPro = in_bpBusinessOrPro;
    }

    public java.util.Set getPlBidInfos() {
        return plBidInfos;
    }

    public void setPlBidInfos(java.util.Set in_plBidInfos) {
        this.plBidInfos = in_plBidInfos;
    }

    /**
     * bidId * @return Long
     *
     * @hibernate.id column="bidId" type="java.lang.Long"
     * generator-class="native"
     */
    public Long getBidId() {
        return this.bidId;
    }

    /**
     * Set the bidId
     */
    public void setBidId(Long aValue) {
        this.bidId = aValue;
    }

    /**
     * 招标项目名称 * @return String
     *
     * @hibernate.property column="bidProName" type="java.lang.String"
     * length="255" not-null="false" unique="false"
     */
    public String getBidProName() {
        return this.bidProName;
    }

    /**
     * Set the bidProName
     */
    public void setBidProName(String aValue) {
        this.bidProName = aValue;
    }

    /**
     * 招标项目编号 * @return String
     *
     * @hibernate.property column="bidProNumber" type="java.lang.String"
     * length="100" not-null="false" unique="false"
     */
    public String getBidProNumber() {
        return this.bidProNumber;
    }

    /**
     * Set the bidProNumber
     */
    public void setBidProNumber(String aValue) {
        this.bidProNumber = aValue;
    }

    /**
     * 招标类型 * @return Long
     */
    public Long getBiddingTypeId() {
        return this.getPlBiddingType() == null ? null : this.getPlBiddingType()
                .getBiddingTypeId();
    }

    /**
     * Set the biddingTypeId
     */
    public void setBiddingTypeId(Long aValue) {
        if (aValue == null) {
            plBiddingType = null;
        } else if (plBiddingType == null) {
            plBiddingType = new com.hurong.credit.model.creditFlow.financingAgency.typeManger.PlBiddingType(
                    aValue);
            plBiddingType.setVersion(new Integer(0));// set a version to cheat
            // hibernate only
        } else {
            //
            plBiddingType.setBiddingTypeId(aValue);
        }
    }

    /**
     * 个人债权项目id * @return Long
     */
    public Long getPOrProId() {
        return this.getBpPersionOrPro() == null ? null : this
                .getBpPersionOrPro().getPorProId();
    }

    /**
     * Set the pOrProId
     */
    public void setPOrProId(Long aValue) {
        if (aValue == null) {
            bpPersionOrPro = null;
        } else if (bpPersionOrPro == null) {
            bpPersionOrPro = new com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionOrPro(
                    aValue);
            bpPersionOrPro.setVersion(new Integer(0));// set a version to cheat
            // hibernate only
        } else {
            //
            bpPersionOrPro.setPorProId(aValue);
        }
    }

    /**
     * 个人直投项目id * @return Long
     */
    public Long getPdirProId() {
        return this.getBpPersionDirPro() == null ? null : this
                .getBpPersionDirPro().getPdirProId();
    }

    /**
     * Set the pDirProId
     */
    public void setPdirProId(Long aValue) {
        if (aValue == null) {
            bpPersionDirPro = null;
        } else if (bpPersionDirPro == null) {
            bpPersionDirPro = new com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro(
                    aValue);
            bpPersionDirPro.setVersion(new Integer(0));// set a version to cheat
            // hibernate only
        } else {
            //
            bpPersionDirPro.setPdirProId(aValue);
        }
    }

    /**
     * 项目类型 企业直投 B_Dir 企业 债权 B_Or 个人直投 P_Dir 个人债权 P_Or * @return String
     *
     * @hibernate.property column="proType" type="java.lang.String" length="50"
     * not-null="false" unique="false"
     */
    public String getProType() {
        return this.proType;
    }

    /**
     * Set the proType
     */
    public void setProType(String aValue) {
        this.proType = aValue;
    }

    /**
     * 企业直投项目id * @return Long
     */
    public Long getBdirProId() {

        return this.getBpBusinessDirPro() == null ? null : this
                .getBpBusinessDirPro().getBdirProId();
    }

    /**
     * Set the bdirProId
     */
    public void setBdirProId(Long aValue) {
        if (aValue == null) {
            bpBusinessDirPro = null;
        } else if (bpBusinessDirPro == null) {
            bpBusinessDirPro = new com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessDirPro(
                    aValue);
            bpBusinessDirPro.setVersion(new Integer(0));// set a version to
            // cheat hibernate only
        } else {
            //
            bpBusinessDirPro.setBdirProId(aValue);
        }
    }

    /**
     * 本招标金额 * @return java.math.BigDecimal
     *
     * @hibernate.property column="bidMoney" type="java.math.BigDecimal"
     * length="10" not-null="false" unique="false"
     */
    public java.math.BigDecimal getBidMoney() {
        return this.bidMoney;
    }

    /**
     * Set the bidMoney
     */
    public void setBidMoney(java.math.BigDecimal aValue) {
        this.bidMoney = aValue;
    }

    /**
     * 本招标金额所在项目比率 * @return Double
     *
     * @hibernate.property column="bidMoneyScale" type="java.lang.Double"
     * length="22" not-null="false" unique="false"
     */
    public Double getBidMoneyScale() {
        return this.bidMoneyScale;
    }

    /**
     * Set the bidMoneyScale
     */
    public void setBidMoneyScale(Double aValue) {
        this.bidMoneyScale = aValue;
    }

    /**
     * 投资起点金额 * @return java.math.BigDecimal
     *
     * @hibernate.property column="startMoney" type="java.math.BigDecimal"
     * length="10" not-null="false" unique="false"
     */
    public java.math.BigDecimal getStartMoney() {
        return this.startMoney;
    }

    /**
     * Set the startMoney
     */
    public void setStartMoney(java.math.BigDecimal aValue) {
        this.startMoney = aValue;
    }

    /**
     * 递增金额 * @return java.math.BigDecimal
     *
     * @hibernate.property column="riseMoney" type="java.math.BigDecimal"
     * length="10" not-null="false" unique="false"
     */
    public java.math.BigDecimal getRiseMoney() {
        return this.riseMoney;
    }

    /**
     * Set the riseMoney
     */
    public void setRiseMoney(java.math.BigDecimal aValue) {
        this.riseMoney = aValue;
    }

    /**
     * * @return java.util.Date
     *
     * @hibernate.property column="createtime" type="java.util.Date" length="19"
     * not-null="false" unique="false"
     */
    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    /**
     * Set the createtime
     */
    public void setCreatetime(java.util.Date aValue) {
        this.createtime = aValue;
    }

    /**
     * * @return java.util.Date
     *
     * @hibernate.property column="updatetime" type="java.util.Date" length="19"
     * not-null="false" unique="false"
     */
    public java.util.Date getUpdatetime() {
        return this.updatetime;
    }

    /**
     * Set the updatetime
     */
    public void setUpdatetime(java.util.Date aValue) {
        this.updatetime = aValue;
    }

    /**
     * 状态 （0 未发布 1招标中 2已齐标 3已流标4手动关闭） * @return Integer
     *
     * @hibernate.property column="state" type="java.lang.Integer" length="10"
     * not-null="false" unique="false"
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * Set the state
     */
    public void setState(Integer aValue) {
        this.state = aValue;
    }

    /**
     * 起息日类型（0投标日—+1 ；1投标截至日+1 ；2标满日+1） * @return Integer
     *
     * @hibernate.property column="startInterestType" type="java.lang.Integer"
     * length="10" not-null="false" unique="false"
     */
    public Integer getStartInterestType() {
        return this.startInterestType;
    }

    /**
     * Set the startInterestType
     */
    public void setStartInterestType(Integer aValue) {
        this.startInterestType = aValue;
    }

    /**
     * 招标开发期限（小时为单位） * @return Integer
     *
     * @hibernate.property column="bidStartTime" type="java.lang.Integer"
     * length="10" not-null="false" unique="false"
     */
    public Integer getBidStartTime() {
        return this.bidStartTime;
    }

    /**
     * Set the bidStartTime
     */
    public void setBidStartTime(Integer aValue) {
        this.bidStartTime = aValue;
    }

    /**
     * 发标时间 * @return java.util.Date
     *
     * @hibernate.property column="publishSingeTime" type="java.util.Date"
     * length="19" not-null="false" unique="false"
     */
    public java.util.Date getPublishSingeTime() {
        return this.publishSingeTime;
    }

    /**
     * Set the publishSingeTime
     */
    public void setPublishSingeTime(java.util.Date aValue) {
        this.publishSingeTime = aValue;
    }

    /**
     * 招标截至日期 * @return java.util.Date
     *
     * @hibernate.property column="bidEndTime" type="java.util.Date" length="19"
     * not-null="false" unique="false"
     */
    public java.util.Date getBidEndTime() {
        return this.bidEndTime;
    }

    /**
     * Set the bidEndTime
     */
    public void setBidEndTime(java.util.Date aValue) {
        this.bidEndTime = aValue;
    }

    /**
     * 招标说明 * @return String
     *
     * @hibernate.property column="bidRemark" type="java.lang.String"
     * length="65535" not-null="false" unique="false"
     */
    public String getBidRemark() {
        return this.bidRemark;
    }

    /**
     * Set the bidRemark
     */
    public void setBidRemark(String aValue) {
        this.bidRemark = aValue;
    }

    /**
     * 生成路径 * @return String
     *
     * @hibernate.property column="htmlPath" type="java.lang.String"
     * length="255" not-null="false" unique="false"
     */
    public String getHtmlPath() {
        return this.htmlPath;
    }

    /**
     * Set the htmlPath
     */
    public void setHtmlPath(String aValue) {
        this.htmlPath = aValue;
    }

    /**
     * 企业债权项目id * @return Long
     */
    public Long getBorProId() {
        return this.getBpBusinessOrPro() == null ? null : this
                .getBpBusinessOrPro().getBorProId();
    }

    /**
     * Set the borProId
     */
    public void setBorProId(Long aValue) {
        if (aValue == null) {
            bpBusinessOrPro = null;
        } else if (bpBusinessOrPro == null) {
            bpBusinessOrPro = new com.hurong.credit.model.creditFlow.financingAgency.business.BpBusinessOrPro(
                    aValue);
            bpBusinessOrPro.setVersion(new Integer(0));// set a version to cheat
            // hibernate only
        } else {
            //
            bpBusinessOrPro.setBorProId(aValue);
        }
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof PlBidPlan)) {
            return false;
        }
        PlBidPlan rhs = (PlBidPlan) object;
        return new EqualsBuilder().append(this.bidId, rhs.bidId)
                .append(this.bidProName, rhs.bidProName)
                .append(this.bidProNumber, rhs.bidProNumber)
                .append(this.proType, rhs.proType)
                .append(this.bidMoney, rhs.bidMoney)
                .append(this.bidMoneyScale, rhs.bidMoneyScale)
                .append(this.startMoney, rhs.startMoney)
                .append(this.riseMoney, rhs.riseMoney)
                .append(this.createtime, rhs.createtime)
                .append(this.updatetime, rhs.updatetime)
                .append(this.state, rhs.state)
                .append(this.startInterestType, rhs.startInterestType)
                .append(this.bidStartTime, rhs.bidStartTime)
                .append(this.publishSingeTime, rhs.publishSingeTime)
                .append(this.bidEndTime, rhs.bidEndTime)
                .append(this.bidRemark, rhs.bidRemark)
                .append(this.htmlPath, rhs.htmlPath).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-82280557, -700257973).append(this.bidId)
                .append(this.bidProName).append(this.bidProNumber)
                .append(this.proType).append(this.bidMoney)
                .append(this.bidMoneyScale).append(this.startMoney)
                .append(this.riseMoney).append(this.createtime)
                .append(this.updatetime).append(this.state)
                .append(this.startInterestType).append(this.bidStartTime)
                .append(this.publishSingeTime).append(this.bidEndTime)
                .append(this.bidRemark).append(this.htmlPath).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("bidId", this.bidId)
                .append("bidProName", this.bidProName)
                .append("bidProNumber", this.bidProNumber)
                .append("proType", this.proType)
                .append("bidMoney", this.bidMoney)
                .append("bidMoneyScale", this.bidMoneyScale)
                .append("startMoney", this.startMoney)
                .append("riseMoney", this.riseMoney)
                .append("createtime", this.createtime)
                .append("updatetime", this.updatetime)
                .append("state", this.state)
                .append("startInterestType", this.startInterestType)
                .append("bidStartTime", this.bidStartTime)
                .append("publishSingeTime", this.publishSingeTime)
                .append("bidEndTime", this.bidEndTime)
                .append("bidRemark", this.bidRemark)
                .append("htmlPath", this.htmlPath).toString();
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setAuthorizationStatus(Short authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public Short getAuthorizationStatus() {
        return authorizationStatus;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public java.math.BigDecimal getYearInterestRate() {
        return yearInterestRate;
    }

    public void setYearInterestRate(java.math.BigDecimal yearInterestRate) {
        this.yearInterestRate = yearInterestRate;
    }

    public String getLoanLifeQuery() {
        return loanLifeQuery;
    }

    public void setLoanLifeQuery(String loanLifeQuery) {
        this.loanLifeQuery = loanLifeQuery;
    }

}
