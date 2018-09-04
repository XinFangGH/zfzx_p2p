package com.hurong.credit.model.financePurchase;

@SuppressWarnings("serial")
public class BpFinanceApplyUser extends com.hurong.core.model.BaseModel{
	protected Long loanId;
	/**
	 * 产品ID 
	 */
	protected Long productId;
	/**
	 * 产品名称
	 */
	protected String productName;
	/**
	 * 用户ID
	 */
	protected Long userID;
	/**
	 * 借款标题
	 */
	protected String loanTitle;
	/**
	 * 借款金额
	 */
	protected String loanMoney;
	/**
	 * 借款期限
	 */
	protected String loanTimeLen;
	/**
	 * 借款说明
	 */
	protected String remark;
	/**
	 * 收益
	 */
	protected String expectAccrual;
	/**
	 * 还款方式，sameprincipalandInterest=等额本息，singleInterest=等额本金，sameprincipalsameInterest=等本等息 singleInterest=按期收息，到期还本 otherMothod=其他还款方式
	 */
	protected String payIntersetWay;
	/**
	 * 借款用途 708=流动资金周转，709=固定资产投资贷款，710=项目融资贷款,724=其他用途
	 */
	protected String loanUse;
	/**
	 * 每月收益
	 */
	protected String monthlyInterest;
	/**
	 * 服务费用
	 */
	protected String monthlyCharge;
	/**
	 * 
	 */
	protected String startCharge;
	/**
	 * 0未提交，1已提交，2初步受理，3,打回补充，4,通过审核，5立项中，6已发标，7未通过终止
	 */
	protected String state;
	/**
	 * 创建时间
	 */
	protected String createTime;
	/**
	 * 最后提交时间
	 */
	protected String submitTime;
	/**
	 * 流程配置
	 */
	protected String flownodes; 
	/**
	 * 当前所处流程节点
	 */
	protected String currentnode;
	/**
	 * 节点英文名称
	 */
	protected String nodeEng;
	/**
	 * 节点中文名称
	 */
	protected String nodeChina;
	/**
	 * 显示的节点页面信息
	 */
	protected String showMenu;
	/**
	 * 每个节点完成的状态
	 */
	protected String finishStatus;
	/**
	 * 节点是否可用
	 */
	protected String nodeEnable;
	protected String state1;
	protected String state2;

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getNodeEnable() {
		return nodeEnable;
	}

	public void setNodeEnable(String nodeEnable) {
		this.nodeEnable = nodeEnable;
	}

	public String getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}

	public String getShowMenu() {
		return showMenu;
	}

	public void setShowMenu(String showMenu) {
		this.showMenu = showMenu;
	}

	public String getNodeEng() {
		return nodeEng;
	}

	public void setNodeEng(String nodeEng) {
		this.nodeEng = nodeEng;
	}

	public String getNodeChina() {
		return nodeChina;
	}

	public void setNodeChina(String nodeChina) {
		this.nodeChina = nodeChina;
	}

	public String getFlownodes() {
		return flownodes;
	}

	public void setFlownodes(String flownodes) {
		this.flownodes = flownodes;
	}

	public String getCurrentnode() {
		return currentnode;
	}

	public void setCurrentnode(String currentnode) {
		this.currentnode = currentnode;
	}

	public BpFinanceApplyUser () {
		super();
	}
	
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getLoanTitle() {
		return loanTitle;
	}
	public void setLoanTitle(String loanTitle) {
		this.loanTitle = loanTitle;
	}
	public String getLoanMoney() {
		return loanMoney;
	}
	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}
	public String getLoanTimeLen() {
		return loanTimeLen;
	}
	public void setLoanTimeLen(String loanTimeLen) {
		this.loanTimeLen = loanTimeLen;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExpectAccrual() {
		return expectAccrual;
	}
	public void setExpectAccrual(String expectAccrual) {
		this.expectAccrual = expectAccrual;
	}
	public String getPayIntersetWay() {
		return payIntersetWay;
	}
	public void setPayIntersetWay(String payIntersetWay) {
		this.payIntersetWay = payIntersetWay;
	}
	public String getLoanUse() {
		return loanUse;
	}
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}
	public String getMonthlyInterest() {
		return monthlyInterest;
	}
	public void setMonthlyInterest(String monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}
	public String getMonthlyCharge() {
		return monthlyCharge;
	}
	public void setMonthlyCharge(String monthlyCharge) {
		this.monthlyCharge = monthlyCharge;
	}
	public String getStartCharge() {
		return startCharge;
	}
	public void setStartCharge(String startCharge) {
		this.startCharge = startCharge;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	} 
	
	
}
