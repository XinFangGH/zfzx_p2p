package com.hurong.credit.model.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * 
 * @author 
 *
 */
/**
 * BpCustMember Base Java Bean, base class for the.credit.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 会员表
 */
public class BpCustMember extends com.hurong.core.model.BaseModel {

    protected Long id;
	protected String loginname;
	protected String truename;
	protected String password;
	protected String plainpassword;//用户推荐码
	protected String recommandPerson;//直接邀请人的推荐码   e10adc3949ba59abbe56e057f20f883e
	protected String secondRecommandPerson;//间接邀请人的推荐码
	protected String telphone;
	protected String email;
	protected Integer type;
	protected Integer sex;
	protected Integer cardtype;
	protected String cardcode;
	protected java.util.Date birthday;
	protected String headImage;
	protected String nativePlaceProvice;
	protected String nativePlaceCity;
	protected String nation;
	protected String homePhone;
	protected String relationAddress;
	protected String postCode;
	protected String qq;
	protected String MSN;
	protected String paymentCode;//支付密码
	protected String securityQuestion;
	protected String securityAnswer;
	protected Integer roleId;
	protected java.util.Date registrationDate;
	protected Long liveProvice;
	protected Long liveCity;
	protected Integer marry;
	protected String fax;
	protected Long memberOrderId;
	protected Integer isDelete;
	protected Integer isForbidden;
	protected String MoneymoremoreId;//钱多多账号
	protected String myPMoneymoremoreMoney;//查询我的钱多多账户余额（平台账户金额）
	protected String myTMoneymoremoreMoney;//查询我的钱多多账户余额（总金额）
	protected String myDMoneymoremoreMoney;//查询我的钱多多账户余额（冻结金额）
	protected String myWithMoneymoremoreMoney;//累计充值金额
	protected String myChargeMoneymoremoreMoney;//可提现金额
	protected String tender;//投标（1代表开启，0代表关闭）
	protected String refund;//还款（1代表开启，0代表关闭）
	protected String secondAudit;//二次分配审核（1代表开启，0代表关闭
	protected String isCheckEmail;//邮箱是否验证（1代表通过验证）
	protected String isCheckPhone;//手机是否通过验证
	protected String isCheckCard;//身份证是否通过验证
	protected java.util.Date isCheckCardTime;//身份证验证时间
	protected String isCheckCardMessage;//身份证验证返回信息
	
	public static final Short THIRDPAY_ACCTIVED=0;//已激活账户
	public static final Short THIRDPAY_DEACCTIVED=1;//未激活账户
	
	protected Short  thirdPayStatus=THIRDPAY_ACCTIVED;//第三方账户状态：默认未激活状态

	protected String thirdPayConfig ;// '第三方支付配置 如：gopayConfig 为国付宝 和表sys_config 中对应';
	protected String thirdPayFlagId ;//VARCHAR(50) COMMENT '第三方支付 返回唯一标识';	富滇银行对应三方返回的accountNo
	protected String thirdPayFlag0 ;//VARCHAR(50) COMMENT '第三方支付 备注标识 如汇付 返回的usrID';		富滇银行对应三方返回的userName

	protected java.lang.Short isSync;//恒安财富----是否已同步到云平台，1:已同步；0:未同步
	
	protected java.lang.Short custType;//客户类型 0投资客户 1融资客户
	
	protected String OPType;//恒安财富----用户同步信息操作
	
	

	/**
	 * 系统账户金额统计字段，不与数据库做映射
	 * @return
	 */
	protected String levelMark;//会员等级
	protected java.math.BigDecimal   totalMoney=new BigDecimal(0);//系统账户总额
	protected java.math.BigDecimal   totalInvestMoney=new BigDecimal(0);//该账户投资人累计投资金额
	protected java.math.BigDecimal   freezeMoney=new BigDecimal(0);//投资预冻结金额
	protected java.math.BigDecimal   availableInvestMoney=new BigDecimal(0);//该账户目前可用金额
	protected java.math.BigDecimal   principalRepayment=new BigDecimal(0);//表示该账户已经收回本金
	protected java.math.BigDecimal   allInterest=new BigDecimal(0);//表示该账户累计的收益
	protected java.math.BigDecimal   totalRecharge=new BigDecimal(0);//表示该账户累计充值金额
	protected java.math.BigDecimal   totalEnchashment=new BigDecimal(0);//表示该账户累计取现金额 
	protected java.math.BigDecimal   unChargeMoney=new BigDecimal(0);//表示该账户未结转金额（富有金账户使用字段）
	protected java.math.BigDecimal   notallInterest=new BigDecimal(0);//未来收益
	//svn:songwj
	protected Long score;//会员积分
	protected Long category;//会员类型
	protected String recomCodeMyself;//自己的推荐码
	protected String recomCodeUp;//上级推荐码
	protected Integer memberGrade;//会员等级
	protected java.util.Date memberDuedate;//会员到期时间
	protected String directReferralsName;//直接推荐人姓名
	protected String indirectReferenceName;//间接推荐人姓名
	protected Long directReferralsId;//直接推荐人主键
	protected Long indirectReferenceId;//间接推荐人主键
	

	protected java.math.BigDecimal   totalLoanMoney;//累计融资金额
	protected java.math.BigDecimal   totalPrincipalRepaymentMoney;//累计还本付息
	protected java.math.BigDecimal   totalNotPrincipalRepaymentMoney;//剩余未还本息

	protected String  categoryName;//会员类别

	private Integer havechildren;
	private String collegeDegree;
	private String collegeYear;
	private String collegename;
	private String relDirName;
	private String relDirType;
	private String relDirPhone;
	private String relOtherName;
	private String relOtherType;
	private String relOtherPhone;
	private String relFriendName;
	private String relFriendType;
	private String relFriendPhone;
	private Integer careerType;
	private String webshopName;
	private java.math.BigDecimal webshopMonthlyincome;
	private String webshopEmail;
	private String webshopProvince;
	private String webshopCity;
	private String webshopAddress;
	private String webshopStartyear;
	private String webshopPhone;
	private String hireCompanyname;
	private String hirePosition;
	private java.math.BigDecimal hireMonthlyincome;
	private String hireEmail;
	private String hireProvince;
	private String hireCity;
	private String hireAddress;
	private String hireCompanytype;
	private String hireCompanycategory;
	private String hireCompanysize;
	private String hireStartyear;
	private String hireCompanyphone;
	private Integer havehouse;
	private Integer havehouseloan;
	private Integer havecar;
	private Integer havecarloan;
	private String homePhonePrefix;
	private String homePhoneSuffix;
	private String webshopPhonePrefix;
	private String webshopPhoneSuffix;
	private String hireCompanyphonePrefix;
	private String hireCompanyphoneSuffix;
	
	private Integer investmentNumber;//累计投资笔数
	private Integer investmentBackNum;//回款中的笔数
	private Integer investmentBidNum;//投标中的笔数
	private java.math.BigDecimal principal;//代收本金
	private java.math.BigDecimal accrual;//代收利息
	private Integer paymentPlanNum;//回款计划笔数
	private java.math.BigDecimal monthIncome;//上月收益
	private java.math.BigDecimal yearIncome;//当年收益

	private Integer isBankCard;   //是否绑卡   1为绑卡   0为未绑卡

	public Integer getIsBankCard() {
		return isBankCard;
	}

	public void setIsBankCard(Integer isBankCard) {
		this.isBankCard = isBankCard;
	}

	private String openID;
	private String sinawb;
	private String isCheckSinaWB;
	private String isCheckQQ;
	
	private String bossCompanyname;//公司名称
	private String bossPosition;//职位
	private java.math.BigDecimal bossMonthlyincome;//月收入
	private String bossEmail;//工作邮箱
	private String bossCity;//工作城市
	private String bossAddress;//公司地址
	private String bossCompanytype;//公司类别
	private String bossCompanycategory;//公司行业
	private String bossCompanysize;//公司规模
	private String bossStartyear;//在现单位工作年限
	private String bossCompanyphone;//公司电话

	//不与数据库映射  XF

	private BigDecimal sumMoney;//投资总额

	public BigDecimal getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}


	private String teacherCompanyname;//单位名称
	private String teacherPosition;//职位
	private java.math.BigDecimal teacherMonthlyincome;//月收入
	private String teacherEmail;//工作邮箱
	private String teacherCity;//工作城市
	private String teacherAddress;//单位地址
	private String teacherStartyear;//教龄
	private String teacherCompanyphone;//单位电话
	private java.lang.Short bambooJoint;//注册绑定易宝流程标识 wenandai
	
	
	//======================================================
	
	public static final Integer CUSTOMER_PERSON=0;
	public static final Integer CUSTOMER_ENTERPRISE=1;
	/**
	 * 客户类型：默认值0，表示个人客户
	 * 0个人客户,1企业客户,2担保客户
	 */
	protected Integer customerType=0;
	
	/**
	 * 企业客户-银行开户许可证
	 */
	protected String bankLicense;

	/**
     * 企业客户-营业执照
     */
	protected String businessLicense;
	/**
	 * 企业客户-税务登记号
	 */
	protected String taxNo;
	/**
	 * 企业客户-法人姓名
	 */
	protected String legalPerson;
	/**
	 * 企业客户-法人身份证号码
	 */
	protected String legalNo;
	/**
	 * 企业客户-企业联系人
	 */
	protected String contactPerson;
	
	/**
	 * 是否评估（目前弃用）
	 */
	protected Integer isAssess;
	/**
	 * 等级
	 */
	protected String grade;
	/**
	 * 企业客户类型
	 */
	protected Short entCompanyType;//客户类型 0投资客户 1担保户
	/**
	 * 不与数据库相关联，标记为个人理财顾问，值为1
	 */
	protected Short perCompanyType;
	
	private String departmentRecommend;
	
	
	/**
     * 是否为vip  0 否 1是
     * @return
     */
    protected Short isVip;
    
	
    public Short getIsVip() {
		return isVip;
	}

	public void setIsVip(Short isVip) {
		this.isVip = isVip;
	}

	public String getDepartmentRecommend() {
		return departmentRecommend;
	}

	public void setDepartmentRecommend(String departmentRecommend) {
		this.departmentRecommend = departmentRecommend;
	}

	public Short getPerCompanyType() {
		return perCompanyType;
	}

	public void setPerCompanyType(Short perCompanyType) {
		this.perCompanyType = perCompanyType;
	}

	public Short getEntCompanyType() {
		return entCompanyType;
	}

	public void setEntCompanyType(Short entCompanyType) {
		this.entCompanyType = entCompanyType;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	
	public String getBankLicense() {
		return bankLicense;
	}

	public void setBankLicense(String bankLicense) {
		this.bankLicense = bankLicense;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	//======================================================
	
	
	
	
	public Short getBambooJoint() {
		return bambooJoint;
	}

	public void setBambooJoint(Short bambooJoint) {
		this.bambooJoint = bambooJoint;
	}

	public java.math.BigDecimal getNotallInterest() {
		return notallInterest;
	}

	public void setNotallInterest(java.math.BigDecimal notallInterest) {
		this.notallInterest = notallInterest;
	}

	public String getBossCompanyname() {
		return bossCompanyname;
	}

	public void setBossCompanyname(String bossCompanyname) {
		this.bossCompanyname = bossCompanyname;
	}

	public String getBossPosition() {
		return bossPosition;
	}

	public void setBossPosition(String bossPosition) {
		this.bossPosition = bossPosition;
	}

	public java.math.BigDecimal getBossMonthlyincome() {
		return bossMonthlyincome;
	}

	public void setBossMonthlyincome(java.math.BigDecimal bossMonthlyincome) {
		this.bossMonthlyincome = bossMonthlyincome;
	}

	public String getBossEmail() {
		return bossEmail;
	}

	public void setBossEmail(String bossEmail) {
		this.bossEmail = bossEmail;
	}

	public String getBossCity() {
		return bossCity;
	}

	public void setBossCity(String bossCity) {
		this.bossCity = bossCity;
	}

	public String getBossAddress() {
		return bossAddress;
	}

	public void setBossAddress(String bossAddress) {
		this.bossAddress = bossAddress;
	}

	public String getBossCompanytype() {
		return bossCompanytype;
	}

	public void setBossCompanytype(String bossCompanytype) {
		this.bossCompanytype = bossCompanytype;
	}

	public String getBossCompanycategory() {
		return bossCompanycategory;
	}

	public void setBossCompanycategory(String bossCompanycategory) {
		this.bossCompanycategory = bossCompanycategory;
	}

	public String getBossCompanysize() {
		return bossCompanysize;
	}

	public void setBossCompanysize(String bossCompanysize) {
		this.bossCompanysize = bossCompanysize;
	}

	public String getBossStartyear() {
		return bossStartyear;
	}

	public void setBossStartyear(String bossStartyear) {
		this.bossStartyear = bossStartyear;
	}

	public String getBossCompanyphone() {
		return bossCompanyphone;
	}

	public void setBossCompanyphone(String bossCompanyphone) {
		this.bossCompanyphone = bossCompanyphone;
	}

	public String getTeacherCompanyname() {
		return teacherCompanyname;
	}

	public void setTeacherCompanyname(String teacherCompanyname) {
		this.teacherCompanyname = teacherCompanyname;
	}

	public String getTeacherPosition() {
		return teacherPosition;
	}

	public void setTeacherPosition(String teacherPosition) {
		this.teacherPosition = teacherPosition;
	}

	public java.math.BigDecimal getTeacherMonthlyincome() {
		return teacherMonthlyincome;
	}

	public void setTeacherMonthlyincome(java.math.BigDecimal teacherMonthlyincome) {
		this.teacherMonthlyincome = teacherMonthlyincome;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherCity() {
		return teacherCity;
	}

	public void setTeacherCity(String teacherCity) {
		this.teacherCity = teacherCity;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherStartyear() {
		return teacherStartyear;
	}

	public void setTeacherStartyear(String teacherStartyear) {
		this.teacherStartyear = teacherStartyear;
	}

	public String getTeacherCompanyphone() {
		return teacherCompanyphone;
	}

	public void setTeacherCompanyphone(String teacherCompanyphone) {
		this.teacherCompanyphone = teacherCompanyphone;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public java.math.BigDecimal getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(java.math.BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	public java.math.BigDecimal getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(java.math.BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}

	public Integer getPaymentPlanNum() {
		return paymentPlanNum;
	}

	public void setPaymentPlanNum(Integer paymentPlanNum) {
		this.paymentPlanNum = paymentPlanNum;
	}

	public java.math.BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(java.math.BigDecimal principal) {
		this.principal = principal;
	}

	public java.math.BigDecimal getAccrual() {
		return accrual;
	}

	public void setAccrual(java.math.BigDecimal accrual) {
		this.accrual = accrual;
	}

	public Integer getInvestmentBackNum() {
		return investmentBackNum;
	}

	public void setInvestmentBackNum(Integer investmentBackNum) {
		this.investmentBackNum = investmentBackNum;
	}

	public Integer getInvestmentBidNum() {
		return investmentBidNum;
	}

	public void setInvestmentBidNum(Integer investmentBidNum) {
		this.investmentBidNum = investmentBidNum;
	}

	public Integer getInvestmentNumber() {
		return investmentNumber;
	}

	public void setInvestmentNumber(Integer investmentNumber) {
		this.investmentNumber = investmentNumber;
	}

	public String getHireCompanyphonePrefix() {
		return hireCompanyphonePrefix;
	}

	public void setHireCompanyphonePrefix(String hireCompanyphonePrefix) {
		this.hireCompanyphonePrefix = hireCompanyphonePrefix;
	}

	public String getHireCompanyphoneSuffix() {
		return hireCompanyphoneSuffix;
	}

	public void setHireCompanyphoneSuffix(String hireCompanyphoneSuffix) {
		this.hireCompanyphoneSuffix = hireCompanyphoneSuffix;
	}

	public String getHomePhonePrefix() {
		return homePhonePrefix;
	}

	public void setHomePhonePrefix(String homePhonePrefix) {
		this.homePhonePrefix = homePhonePrefix;
	}

	public String getHomePhoneSuffix() {
		return homePhoneSuffix;
	}

	public void setHomePhoneSuffix(String homePhoneSuffix) {
		this.homePhoneSuffix = homePhoneSuffix;
	}

	public String getWebshopPhonePrefix() {
		return webshopPhonePrefix;
	}

	public void setWebshopPhonePrefix(String webshopPhonePrefix) {
		this.webshopPhonePrefix = webshopPhonePrefix;
	}

	public String getWebshopPhoneSuffix() {
		return webshopPhoneSuffix;
	}

	public void setWebshopPhoneSuffix(String webshopPhoneSuffix) {
		this.webshopPhoneSuffix = webshopPhoneSuffix;
	}

	public java.math.BigDecimal getTotalLoanMoney() {
		return totalLoanMoney;
	}

	public void setTotalLoanMoney(java.math.BigDecimal totalLoanMoney) {
		this.totalLoanMoney = totalLoanMoney;
	}

	public java.math.BigDecimal getTotalPrincipalRepaymentMoney() {
		return totalPrincipalRepaymentMoney;
	}

	public void setTotalPrincipalRepaymentMoney(
			java.math.BigDecimal totalPrincipalRepaymentMoney) {
		this.totalPrincipalRepaymentMoney = totalPrincipalRepaymentMoney;
	}

	public java.math.BigDecimal getTotalNotPrincipalRepaymentMoney() {
		return totalNotPrincipalRepaymentMoney;
	}

	public void setTotalNotPrincipalRepaymentMoney(
			java.math.BigDecimal totalNotPrincipalRepaymentMoney) {
		this.totalNotPrincipalRepaymentMoney = totalNotPrincipalRepaymentMoney;
	}
	

	public String getSinawb() {
		return sinawb;
	}

	public void setSinawb(String sinawb) {
		this.sinawb = sinawb;
	}

	public String getIsCheckSinaWB() {
		return isCheckSinaWB;
	}

	public void setIsCheckSinaWB(String isCheckSinaWB) {
		this.isCheckSinaWB = isCheckSinaWB;
	}

	public String getIsCheckQQ() {
		return isCheckQQ;
	}

	public void setIsCheckQQ(String isCheckQQ) {
		this.isCheckQQ = isCheckQQ;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(Integer memberGrade) {
		this.memberGrade = memberGrade;
	}
	
	public java.util.Date getMemberDuedate() {
		return memberDuedate;
	}

	public void setMemberDuedate(java.util.Date memberDuedate) {
		this.memberDuedate = memberDuedate;
	}

	public String getDirectReferralsName() {
		return directReferralsName;
	}

	public void setDirectReferralsName(String directReferralsName) {
		this.directReferralsName = directReferralsName;
	}

	public String getIndirectReferenceName() {
		return indirectReferenceName;
	}

	public void setIndirectReferenceName(String indirectReferenceName) {
		this.indirectReferenceName = indirectReferenceName;
	}

	public Long getDirectReferralsId() {
		return directReferralsId;
	}

	public void setDirectReferralsId(Long directReferralsId) {
		this.directReferralsId = directReferralsId;
	}

	public Long getIndirectReferenceId() {
		return indirectReferenceId;
	}

	public void setIndirectReferenceId(Long indirectReferenceId) {
		this.indirectReferenceId = indirectReferenceId;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getRecomCodeMyself() {
		return recomCodeMyself;
	}

	public void setRecomCodeMyself(String recomCodeMyself) {
		this.recomCodeMyself = recomCodeMyself;
	}

	public String getRecomCodeUp() {
		return recomCodeUp;
	}

	public void setRecomCodeUp(String recomCodeUp) {
		this.recomCodeUp = recomCodeUp;
	}


	public java.math.BigDecimal getUnChargeMoney() {
		return unChargeMoney;
	}

	public void setUnChargeMoney(java.math.BigDecimal unChargeMoney) {
		this.unChargeMoney = unChargeMoney;
	}

	public void setThirdPayConfig(String thirdPayConfig) {
		this.thirdPayConfig = thirdPayConfig;
	}

	public String getOPType() {
		return OPType;
	}

	public void setOPType(String oPType) {
		OPType = oPType;
	}

	public java.lang.Short getIsSync() {
		return isSync;
	}

	public void setIsSync(java.lang.Short isSync) {
		this.isSync = isSync;
	}

	public String getThirdPayConfig() {
		return thirdPayConfig;
	}
	public String getThirdPayFlagId() {
		return thirdPayFlagId;
	}

	public void setThirdPayFlagId(String thirdPayFlagId) {
		this.thirdPayFlagId = thirdPayFlagId;
	}

	public String getThirdPayFlag0() {
		return thirdPayFlag0;
	}

	public void setThirdPayFlag0(String thirdPayFlag0) {
		this.thirdPayFlag0 = thirdPayFlag0;
	}


	public java.math.BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(java.math.BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public java.math.BigDecimal getTotalInvestMoney() {
		return totalInvestMoney;
	}

	public void setTotalInvestMoney(java.math.BigDecimal totalInvestMoney) {
		this.totalInvestMoney = totalInvestMoney;
	}

	public java.math.BigDecimal getFreezeMoney() {
		return freezeMoney;
	}

	public void setFreezeMoney(java.math.BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public java.math.BigDecimal getAvailableInvestMoney() {
		return availableInvestMoney;
	}

	public void setAvailableInvestMoney(java.math.BigDecimal availableInvestMoney) {
		this.availableInvestMoney = availableInvestMoney;
	}

	public java.math.BigDecimal getPrincipalRepayment() {
		return principalRepayment;
	}

	public void setPrincipalRepayment(java.math.BigDecimal principalRepayment) {
		this.principalRepayment = principalRepayment;
	}

	public java.math.BigDecimal getAllInterest() {
		return allInterest;
	}

	public void setAllInterest(java.math.BigDecimal allInterest) {
		this.allInterest = allInterest;
	}

	public java.math.BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(java.math.BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public java.math.BigDecimal getTotalEnchashment() {
		return totalEnchashment;
	}

	public void setTotalEnchashment(java.math.BigDecimal totalEnchashment) {
		this.totalEnchashment = totalEnchashment;
	}


	public String getMyPMoneymoremoreMoney() {
		return myPMoneymoremoreMoney;
	}

	public void setMyPMoneymoremoreMoney(String myPMoneymoremoreMoney) {
		this.myPMoneymoremoreMoney = myPMoneymoremoreMoney;
	}

	public String getMyTMoneymoremoreMoney() {
		return myTMoneymoremoreMoney;
	}

	public void setMyTMoneymoremoreMoney(String myTMoneymoremoreMoney) {
		this.myTMoneymoremoreMoney = myTMoneymoremoreMoney;
	}

	public String getMyDMoneymoremoreMoney() {
		return myDMoneymoremoreMoney;
	}

	public void setMyDMoneymoremoreMoney(String myDMoneymoremoreMoney) {
		this.myDMoneymoremoreMoney = myDMoneymoremoreMoney;
	}




	public String getMyWithMoneymoremoreMoney() {
		return myWithMoneymoremoreMoney;
	}

	public void setMyWithMoneymoremoreMoney(String myWithMoneymoremoreMoney) {
		this.myWithMoneymoremoreMoney = myWithMoneymoremoreMoney;
	}

	public String getMyChargeMoneymoremoreMoney() {
		return myChargeMoneymoremoreMoney;
	}

	public void setMyChargeMoneymoremoreMoney(String myChargeMoneymoremoreMoney) {
		this.myChargeMoneymoremoreMoney = myChargeMoneymoremoreMoney;
	}

	/**
	 * Default Empty Constructor for class BpCustMember
	 */
	public BpCustMember () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BpCustMember
	 */
	public BpCustMember (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * id唯一标示符	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 登录名	 * @return String
	 * @hibernate.property column="loginname" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getLoginname() {
		return this.loginname;
	}
	
	/**
	 * Set the loginname
	 */	
	public void setLoginname(String aValue) {
		this.loginname = aValue;
	}	

	/**
	 * 真实姓名	 * @return String
	 * @hibernate.property column="truename" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getTruename() {
		return this.truename;
	}
	
	/**
	 * Set the truename
	 */	
	public void setTruename(String aValue) {
		this.truename = aValue;
	}	

	/**
	 * 密码（加密）	 * @return String
	 * @hibernate.property column="password" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Set the password
	 */	
	public void setPassword(String aValue) {
		this.password = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="plainpassword" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPlainpassword() {
		return this.plainpassword;
	}
	
	public String getRecommandPerson() {
		return recommandPerson;
	}

	public void setRecommandPerson(String recommandPerson) {
		this.recommandPerson = recommandPerson;
	}

	/**
	 * Set the plainpassword
	 */	
	public void setPlainpassword(String aValue) {
		this.plainpassword = aValue;
	}	

	/**
	 * 手机号码	 * @return String
	 * @hibernate.property column="telphone" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getTelphone() {
		return this.telphone;
	}
	
	/**
	 * Set the telphone
	 */	
	public void setTelphone(String aValue) {
		this.telphone = aValue;
	}	

	/**
	 * 邮箱	 * @return String
	 * @hibernate.property column="email" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Set the email
	 */	
	public void setEmail(String aValue) {
		this.email = aValue;
	}	

	/**
	 * 会员类型（0个人，1企业）	 * @return Integer
	 * @hibernate.property column="type" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * Set the type
	 */	
	public void setType(Integer aValue) {
		this.type = aValue;
	}	

	/**
	 * 性别（0男性，1女性）	 * @return Integer
	 * @hibernate.property column="sex" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getSex() {
		return this.sex;
	}
	
	/**
	 * Set the sex
	 */	
	public void setSex(Integer aValue) {
		this.sex = aValue;
	}	

	/**
	 * 证件类型	 * @return Integer
	 * @hibernate.property column="cardtype" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getCardtype() {
		return this.cardtype;
	}
	
	/**
	 * Set the cardtype
	 */	
	public void setCardtype(Integer aValue) {
		this.cardtype = aValue;
	}	

	/**
	 * 证件号码	 * @return String
	 * @hibernate.property column="cardcode" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getCardcode() {
		return this.cardcode;
	}
	
	/**
	 * Set the cardcode
	 */	
	public void setCardcode(String aValue) {
		this.cardcode = aValue;
	}	

	/**
	 * 出生日期	 * @return java.util.Date
	 * @hibernate.property column="birthday" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	
	/**
	 * Set the birthday
	 */	
	public void setBirthday(java.util.Date aValue) {
		this.birthday = aValue;
	}	

	/**
	 * 头像	 * @return String
	 * @hibernate.property column="headImage" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getHeadImage() {
		return this.headImage;
	}
	
	/**
	 * Set the headImage
	 */	
	public void setHeadImage(String aValue) {
		this.headImage = aValue;
	}	

	/**
	 * 籍贯省	 * @return String
	 * @hibernate.property column="nativePlaceProvice" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getNativePlaceProvice() {
		return this.nativePlaceProvice;
	}
	
	/**
	 * Set the nativePlaceProvice
	 */	
	public void setNativePlaceProvice(String aValue) {
		this.nativePlaceProvice = aValue;
	}

	/**
	 * 籍贯市	 * @return String
	 * @hibernate.property column="nativePlaceCity" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getNativePlaceCity() {
		return this.nativePlaceCity;
	}
	
	/**
	 * Set the nativePlaceCity
	 */	
	public void setNativePlaceCity(String aValue) {
		this.nativePlaceCity = aValue;
	}	

	/**
	 * 民族	 * @return String
	 * @hibernate.property column="nation" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getNation() {
		return this.nation;
	}
	
	/**
	 * Set the nation
	 */	
	public void setNation(String aValue) {
		this.nation = aValue;
	}	

	/**
	 * 家庭电话	 * @return String
	 * @hibernate.property column="homePhone" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getHomePhone() {
		return this.homePhone;
	}
	
	/**
	 * Set the homePhone
	 */	
	public void setHomePhone(String aValue) {
		this.homePhone = aValue;
	}	

	/**
	 * 联系地址	 * @return String
	 * @hibernate.property column="relationAddress" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getRelationAddress() {
		return this.relationAddress;
	}
	
	/**
	 * Set the relationAddress
	 */	
	public void setRelationAddress(String aValue) {
		this.relationAddress = aValue;
	}	

	/**
	 * 邮编	 * @return String
	 * @hibernate.property column="postCode" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPostCode() {
		return this.postCode;
	}
	
	/**
	 * Set the postCode
	 */	
	public void setPostCode(String aValue) {
		this.postCode = aValue;
	}	

	/**
	 * qq	 * @return String
	 * @hibernate.property column="QQ" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	

	/**
	 * msn	 * @return String
	 * @hibernate.property column="MSN" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getMSN() {
		return this.MSN;
	}
	
	/*
	 * Set the MSN
	 */	
	public void setMSN(String aValue) {
		this.MSN = aValue;
	}	

	/**
	 * 支付密码	 * @return String
	 * @hibernate.property column="paymentCode" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPaymentCode() {
		return this.paymentCode;
	}
	
	/**
	 * Set the paymentCode
	 */	
	public void setPaymentCode(String aValue) {
		this.paymentCode = aValue;
	}	

	/**
	 * 密码保护问题	 * @return String
	 * @hibernate.property column="securityQuestion" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getSecurityQuestion() {
		return this.securityQuestion;
	}
	
	/**
	 * Set the securityQuestion
	 */	
	public void setSecurityQuestion(String aValue) {
		this.securityQuestion = aValue;
	}	

	/**
	 * 密码保护答案	 * @return String
	 * @hibernate.property column="securityAnswer" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getSecurityAnswer() {
		return this.securityAnswer;
	}
	
	/**
	 * Set the securityAnswer
	 */	
	public void setSecurityAnswer(String aValue) {
		this.securityAnswer = aValue;
	}	

	/**
	 * 角色ID	 * @return Integer
	 * @hibernate.property column="roleId" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getRoleId() {
		return this.roleId;
	}
	
	/**
	 * Set the roleId
	 */	
	public void setRoleId(Integer aValue) {
		this.roleId = aValue;
	}	

	/**
	 * 注册时间	 * @return java.util.Date
	 * @hibernate.property column="registrationDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getRegistrationDate() {
		return this.registrationDate;
	}
	
	/**
	 * Set the registrationDate
	 */	
	public void setRegistrationDate(java.util.Date aValue) {
		this.registrationDate = aValue;
	}	

	/**
	 * 居住城市省	 * @return Long
	 * @hibernate.property column="liveProvice" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getLiveProvice() {
		return this.liveProvice;
	}
	
	/**
	 * Set the liveProvice
	 */	
	public void setLiveProvice(Long aValue) {
		this.liveProvice = aValue;
	}	

	/**
	 * 居住城市-市	 * @return Long
	 * @hibernate.property column="liveCity" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getLiveCity() {
		return this.liveCity;
	}
	
	/**
	 * Set the liveCity
	 */	
	public void setLiveCity(Long aValue) {
		this.liveCity = aValue;
	}	

	/**
	 * 婚姻状况	 * @return Integer
	 * @hibernate.property column="marry" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	

	/**
	 * 传真	 * @return String
	 * @hibernate.property column="fax" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getFax() {
		return this.fax;
	}
	
	
	public Integer getMarry() {
		return marry;
	}

	public void setMarry(Integer marry) {
		this.marry = marry;
	}

	/**
	 * Set the fax
	 */	
	public void setFax(String aValue) {
		this.fax = aValue;
	}	

	/**
	 * 会员等级	 * @return Long
	 * @hibernate.property column="memberOrderId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getMemberOrderId() {
		return this.memberOrderId;
	}
	
	/**
	 * Set the memberOrderId
	 */	
	public void setMemberOrderId(Long aValue) {
		this.memberOrderId = aValue;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsForbidden() {
		return isForbidden;
	}

	public void setIsForbidden(Integer isForbidden) {
		this.isForbidden = isForbidden;
	}

	public String getMoneymoremoreId() {
		return MoneymoremoreId;
	}

	public void setMoneymoremoreId(String moneymoremoreId) {
		MoneymoremoreId = moneymoremoreId;
	}
	
	

	public String getTender() {
		return tender;
	}

	public void setTender(String tender) {
		this.tender = tender;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public String getSecondAudit() {
		return secondAudit;
	}

	public void setSecondAudit(String secondAudit) {
		this.secondAudit = secondAudit;
	}

	public String getIsCheckEmail() {
		return isCheckEmail;
	}

	public void setIsCheckEmail(String isCheckEmail) {
		this.isCheckEmail = isCheckEmail;
	}

	public String getIsCheckPhone() {
		return isCheckPhone;
	}

	public void setIsCheckPhone(String isCheckPhone) {
		this.isCheckPhone = isCheckPhone;
	}

	public String getIsCheckCard() {
		return isCheckCard;
	}

	public void setIsCheckCard(String isCheckCard) {
		this.isCheckCard = isCheckCard;
	}

	public java.util.Date getIsCheckCardTime() {
		return isCheckCardTime;
	}

	public void setIsCheckCardTime(java.util.Date isCheckCardTime) {
		this.isCheckCardTime = isCheckCardTime;
	}

	public String getIsCheckCardMessage() {
		return isCheckCardMessage;
	}

	public void setIsCheckCardMessage(String isCheckCardMessage) {
		this.isCheckCardMessage = isCheckCardMessage;
	}
	
	

	public java.lang.Short getCustType() {
		return custType;
	}

	public void setCustType(java.lang.Short custType) {
		this.custType = custType;
	}

	public Integer getHavechildren() {
		return havechildren;
	}

	public void setHavechildren(Integer havechildren) {
		this.havechildren = havechildren;
	}

	public String getCollegeDegree() {
		return collegeDegree;
	}

	public void setCollegeDegree(String collegeDegree) {
		this.collegeDegree = collegeDegree;
	}

	

	public String getCollegeYear() {
		return collegeYear;
	}

	public void setCollegeYear(String collegeYear) {
		this.collegeYear = collegeYear;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getRelDirName() {
		return relDirName;
	}

	public void setRelDirName(String relDirName) {
		this.relDirName = relDirName;
	}

	public String getRelDirType() {
		return relDirType;
	}

	public void setRelDirType(String relDirType) {
		this.relDirType = relDirType;
	}

	public String getRelDirPhone() {
		return relDirPhone;
	}

	public void setRelDirPhone(String relDirPhone) {
		this.relDirPhone = relDirPhone;
	}

	public String getRelOtherName() {
		return relOtherName;
	}

	public void setRelOtherName(String relOtherName) {
		this.relOtherName = relOtherName;
	}

	public String getRelOtherType() {
		return relOtherType;
	}

	public void setRelOtherType(String relOtherType) {
		this.relOtherType = relOtherType;
	}

	public String getRelOtherPhone() {
		return relOtherPhone;
	}

	public void setRelOtherPhone(String relOtherPhone) {
		this.relOtherPhone = relOtherPhone;
	}

	public String getRelFriendName() {
		return relFriendName;
	}

	public void setRelFriendName(String relFriendName) {
		this.relFriendName = relFriendName;
	}

	public String getRelFriendType() {
		return relFriendType;
	}

	public void setRelFriendType(String relFriendType) {
		this.relFriendType = relFriendType;
	}

	public String getRelFriendPhone() {
		return relFriendPhone;
	}

	public void setRelFriendPhone(String relFriendPhone) {
		this.relFriendPhone = relFriendPhone;
	}

	public Integer getCareerType() {
		return careerType;
	}

	public void setCareerType(Integer careerType) {
		this.careerType = careerType;
	}

	public String getWebshopName() {
		return webshopName;
	}

	public void setWebshopName(String webshopName) {
		this.webshopName = webshopName;
	}

	public java.math.BigDecimal getWebshopMonthlyincome() {
		return webshopMonthlyincome;
	}

	public void setWebshopMonthlyincome(java.math.BigDecimal webshopMonthlyincome) {
		this.webshopMonthlyincome = webshopMonthlyincome;
	}

	public String getWebshopEmail() {
		return webshopEmail;
	}

	public void setWebshopEmail(String webshopEmail) {
		this.webshopEmail = webshopEmail;
	}

	public String getWebshopProvince() {
		return webshopProvince;
	}

	public void setWebshopProvince(String webshopProvince) {
		this.webshopProvince = webshopProvince;
	}

	public String getWebshopCity() {
		return webshopCity;
	}

	public void setWebshopCity(String webshopCity) {
		this.webshopCity = webshopCity;
	}

	public String getWebshopAddress() {
		return webshopAddress;
	}

	public void setWebshopAddress(String webshopAddress) {
		this.webshopAddress = webshopAddress;
	}

	

	

	public String getWebshopStartyear() {
		return webshopStartyear;
	}

	public void setWebshopStartyear(String webshopStartyear) {
		this.webshopStartyear = webshopStartyear;
	}

	public String getWebshopPhone() {
		return webshopPhone;
	}

	public void setWebshopPhone(String webshopPhone) {
		this.webshopPhone = webshopPhone;
	}

	public String getHireCompanyname() {
		return hireCompanyname;
	}

	public void setHireCompanyname(String hireCompanyname) {
		this.hireCompanyname = hireCompanyname;
	}

	public String getHirePosition() {
		return hirePosition;
	}

	public void setHirePosition(String hirePosition) {
		this.hirePosition = hirePosition;
	}

	public java.math.BigDecimal getHireMonthlyincome() {
		return hireMonthlyincome;
	}

	public void setHireMonthlyincome(java.math.BigDecimal hireMonthlyincome) {
		this.hireMonthlyincome = hireMonthlyincome;
	}

	public String getHireEmail() {
		return hireEmail;
	}

	public void setHireEmail(String hireEmail) {
		this.hireEmail = hireEmail;
	}

	public String getHireProvince() {
		return hireProvince;
	}

	public void setHireProvince(String hireProvince) {
		this.hireProvince = hireProvince;
	}

	public String getHireCity() {
		return hireCity;
	}

	public void setHireCity(String hireCity) {
		this.hireCity = hireCity;
	}

	public String getHireAddress() {
		return hireAddress;
	}

	public void setHireAddress(String hireAddress) {
		this.hireAddress = hireAddress;
	}

	public String getHireCompanytype() {
		return hireCompanytype;
	}

	public void setHireCompanytype(String hireCompanytype) {
		this.hireCompanytype = hireCompanytype;
	}

	public String getHireCompanycategory() {
		return hireCompanycategory;
	}

	public void setHireCompanycategory(String hireCompanycategory) {
		this.hireCompanycategory = hireCompanycategory;
	}

	public String getHireCompanysize() {
		return hireCompanysize;
	}

	public void setHireCompanysize(String hireCompanysize) {
		this.hireCompanysize = hireCompanysize;
	}

	public String getHireStartyear() {
		return hireStartyear;
	}

	public void setHireStartyear(String hireStartyear) {
		this.hireStartyear = hireStartyear;
	}

	public String getHireCompanyphone() {
		return hireCompanyphone;
	}

	public void setHireCompanyphone(String hireCompanyphone) {
		this.hireCompanyphone = hireCompanyphone;
	}

	public Integer getHavehouse() {
		return havehouse;
	}

	public void setHavehouse(Integer havehouse) {
		this.havehouse = havehouse;
	}

	public Integer getHavehouseloan() {
		return havehouseloan;
	}

	public void setHavehouseloan(Integer havehouseloan) {
		this.havehouseloan = havehouseloan;
	}

	public Integer getHavecar() {
		return havecar;
	}

	public void setHavecar(Integer havecar) {
		this.havecar = havecar;
	}

	public Integer getHavecarloan() {
		return havecarloan;
	}

	public void setHavecarloan(Integer havecarloan) {
		this.havecarloan = havecarloan;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpCustMember)) {
			return false;
		}
		BpCustMember rhs = (BpCustMember) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.loginname, rhs.loginname)
				.append(this.truename, rhs.truename)
				.append(this.password, rhs.password)
				.append(this.plainpassword, rhs.plainpassword)
				.append(this.telphone, rhs.telphone)
				.append(this.email, rhs.email)
				.append(this.type, rhs.type)
				.append(this.sex, rhs.sex)
				.append(this.cardtype, rhs.cardtype)
				.append(this.cardcode, rhs.cardcode)
				.append(this.birthday, rhs.birthday)
				.append(this.headImage, rhs.headImage)
				//.append(this.nativePlaceProvice, rhs.nativePlaceProvice)
				.append(this.nativePlaceCity, rhs.nativePlaceCity)
				.append(this.nation, rhs.nation)
				.append(this.homePhone, rhs.homePhone)
				.append(this.relationAddress, rhs.relationAddress)
				.append(this.postCode, rhs.postCode)
				.append(this.qq, rhs.qq)
				//.append(this.MSN, rhs.MSN)
				.append(this.paymentCode, rhs.paymentCode)
				.append(this.securityQuestion, rhs.securityQuestion)
				.append(this.securityAnswer, rhs.securityAnswer)
				.append(this.roleId, rhs.roleId)
				.append(this.registrationDate, rhs.registrationDate)
				//.append(this.liveProvice, rhs.liveProvice)
				.append(this.liveCity, rhs.liveCity)
				.append(this.marry, rhs.marry)
				.append(this.fax, rhs.fax)
				//.append(this.memberOrderId, rhs.memberOrderId)
				.append(this.havechildren,rhs.havechildren)
				.append(this.collegeDegree,rhs.collegeDegree)
				.append(this.collegeYear,rhs.collegeYear)
				.append(this.collegename,rhs.collegename)
				.append(this.relDirName,rhs.relDirName)
				.append(this.relDirType,rhs.relDirType)
				.append(this.relDirPhone,rhs.relDirPhone)
				.append(this.relOtherName,rhs.relOtherName)
				.append(this.relOtherType,rhs.relOtherType)
				.append(this.relOtherPhone,rhs.relOtherPhone)
				.append(this.relFriendName,rhs.relFriendName)
				.append(this.relFriendType,rhs.relFriendType)
				.append(this.relFriendPhone,rhs.relFriendPhone)
				//.append(this.careerType,rhs.careerType)
				.append(this.webshopName,rhs.webshopName)
				.append(this.webshopMonthlyincome,rhs.webshopMonthlyincome)
				.append(this.webshopEmail,rhs.webshopEmail)
				.append(this.webshopProvince,rhs.webshopProvince)
				.append(this.webshopCity,rhs.webshopCity)
				.append(this.webshopAddress,rhs.webshopAddress)
				.append(this.webshopStartyear,rhs.webshopStartyear)
				.append(this.webshopPhone,rhs.webshopPhone)
				.append(this.hireCompanyname,rhs.hireCompanyname)
				.append(this.hirePosition,rhs.hirePosition)
				.append(this.hireMonthlyincome,rhs.hireMonthlyincome)
				.append(this.hireEmail,rhs.hireEmail)
				.append(this.hireProvince,rhs.hireProvince)
				.append(this.hireCity,rhs.hireCity)
				.append(this.hireAddress,rhs.hireAddress)
				.append(this.hireCompanytype,rhs.hireCompanytype)
				.append(this.hireCompanycategory,rhs.hireCompanycategory)
				.append(this.hireCompanysize,rhs.hireCompanysize)
				.append(this.hireStartyear,rhs.hireStartyear)
				.append(this.hireCompanyphone,rhs.hireCompanyphone)
				.append(this.havehouse,rhs.havehouse)
				.append(this.havehouseloan,rhs.havehouseloan)
				.append(this.havecar,rhs.havecar)
				.append(this.havecarloan,rhs.havecarloan)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.loginname) 
				.append(this.truename) 
				.append(this.password) 
				.append(this.plainpassword) 
				.append(this.telphone) 
				.append(this.email) 
				.append(this.type) 
				.append(this.sex) 
				.append(this.cardtype) 
				.append(this.cardcode) 
				.append(this.birthday) 
				.append(this.headImage) 
				//.append(this.nativePlaceProvice) 
				.append(this.nativePlaceCity) 
				.append(this.nation) 
				.append(this.homePhone) 
				.append(this.relationAddress) 
				.append(this.postCode) 
				.append(this.qq) 
				//.append(this.MSN) 
				.append(this.paymentCode) 
				.append(this.securityQuestion) 
				.append(this.securityAnswer) 
				.append(this.roleId) 
				.append(this.registrationDate) 
				//.append(this.liveProvice) 
				.append(this.liveCity) 
				.append(this.marry) 
				.append(this.fax) 
				//.append(this.memberOrderId)
				.append(this.havechildren)
				.append(this.collegeDegree)
				.append(this.collegeYear)
				.append(this.collegename)
				.append(this.relDirName)
				.append(this.relDirType)
				.append(this.relDirPhone)
				.append(this.relOtherName)
				.append(this.relOtherType)
				.append(this.relOtherPhone)
				.append(this.relFriendName)
				.append(this.relFriendType)
				.append(this.relFriendPhone)
				//.append(this.careerType)
				.append(this.webshopName)
				.append(this.webshopMonthlyincome)
				.append(this.webshopEmail)
				.append(this.webshopProvince)
				.append(this.webshopCity)
				.append(this.webshopAddress)
				.append(this.webshopStartyear)
				.append(this.webshopPhone)
				.append(this.hireCompanyname)
				.append(this.hirePosition)
				.append(this.hireMonthlyincome)
				.append(this.hireEmail)
				.append(this.hireProvince)
				.append(this.hireCity)
				.append(this.hireAddress)
				.append(this.hireCompanytype)
				.append(this.hireCompanycategory)
				.append(this.hireCompanysize)
				.append(this.hireStartyear)
				.append(this.hireCompanyphone)
				.append(this.havehouse)
				.append(this.havehouseloan)
				.append(this.havecar)
				.append(this.havecarloan)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("loginname", this.loginname) 
				.append("truename", this.truename) 
				.append("password", this.password) 
				.append("plainpassword", this.plainpassword) 
				.append("telphone", this.telphone) 
				.append("email", this.email) 
				.append("type", this.type) 
				.append("sex", this.sex) 
				.append("cardtype", this.cardtype) 
				.append("cardcode", this.cardcode) 
				.append("birthday", this.birthday) 
				.append("headImage", this.headImage) 
				//.append("nativePlaceProvice", this.nativePlaceProvice) 
				.append("nativePlaceCity", this.nativePlaceCity) 
				.append("nation", this.nation) 
				.append("homePhone", this.homePhone) 
				.append("relationAddress", this.relationAddress) 
				.append("postCode", this.postCode) 
				.append("QQ", this.qq) 
				//.append("MSN", this.MSN) 
				.append("paymentCode", this.paymentCode) 
				.append("securityQuestion", this.securityQuestion) 
				.append("securityAnswer", this.securityAnswer) 
				.append("roleId", this.roleId) 
				.append("registrationDate", this.registrationDate) 
				//.append("liveProvice", this.liveProvice) 
				.append("liveCity", this.liveCity) 
				.append("marry", this.marry) 
				.append("fax", this.fax) 
				//.append("memberOrderId", this.memberOrderId) 
				.append("havechildren",this.havechildren)
				.append("collegeDegree",this.collegeDegree)
				.append("collegeYear",this.collegeYear)
				.append("collegename",this.collegename)
				.append("relDirName",this.relDirName)
				.append("relDirType",this.relDirType)
				.append("relDirPhone",this.relDirPhone)
				.append("relOtherName",this.relOtherName)
				.append("relOtherType",this.relOtherType)
				.append("relOtherPhone",this.relOtherPhone)
				.append("relFriendName",this.relFriendName)
				.append("relFriendType",this.relFriendType)
				.append("relFriendPhone",this.relFriendPhone)
				//.append("careerType",this.careerType)
				.append("webshopName",this.webshopName)
				.append("webshopMonthlyincome",this.webshopMonthlyincome)
				.append("webshopEmail",this.webshopEmail)
				.append("webshopProvince",this.webshopProvince)
				.append("webshopCity",this.webshopCity)
				.append("webshopAddress",this.webshopAddress)
				.append("webshopStartyear",this.webshopStartyear)
				.append("webshopPhone",this.webshopPhone)
				.append("hireCompanyname",this.hireCompanyname)
				.append("hirePosition",this.hirePosition)
				.append("hireMonthlyincome",this.hireMonthlyincome)
				.append("hireEmail",this.hireEmail)
				.append("hireProvince",this.hireProvince)
				.append("hireCity",this.hireCity)
				.append("hireAddress",this.hireAddress)
				.append("hireCompanytype",this.hireCompanytype)
				.append("hireCompanycategory",this.hireCompanycategory)
				.append("hireCompanysize",this.hireCompanysize)
				.append("hireStartyear",this.hireStartyear)
				.append("hireCompanyphone",this.hireCompanyphone)
				.append("havehouse",this.havehouse)
				.append("havehouseloan",this.havehouseloan)
				.append("havecar",this.havecar)
				.append("havecarloan",this.havecarloan)
				.toString();
	}

	public String getLevelMark() {
		return levelMark;
	}

	public void setLevelMark(String levelMark) {
		this.levelMark = levelMark;
	}

	public String getSecondRecommandPerson() {
		return secondRecommandPerson;
	}

	public void setSecondRecommandPerson(String secondRecommandPerson) {
		this.secondRecommandPerson = secondRecommandPerson;
	}

	public Short getThirdPayStatus() {
		return thirdPayStatus;
	}

	public void setThirdPayStatus(Short thirdPayStatus) {
		this.thirdPayStatus = thirdPayStatus;
	}

	public Integer getIsAssess() {
		return isAssess;
	}

	public void setIsAssess(Integer isAssess) {
		this.isAssess = isAssess;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}


}
