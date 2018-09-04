package com.hurong.credit.model.creditFlow.financingAgency;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class UserBidInfoVO {
	    @Expose
		protected Long userId;
	    @Expose
		protected String bidName;
	    @Expose
		protected java.math.BigDecimal userMoney;
	    @Expose
		protected java.util.Date bidtime;
	    @Expose
		protected String loanUserName;//借款人
	    @Expose
		protected BigDecimal yearInterestRate;//年利率
	    @Expose
		protected Integer loanLife;//借款期限
	    @Expose
		protected BigDecimal incomeMoney;//应收本息
	    @Expose
		protected Integer state;//状态
		
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getBidName() {
			return bidName;
		}
		public void setBidName(String bidName) {
			this.bidName = bidName;
		}
		public java.math.BigDecimal getUserMoney() {
			return userMoney;
		}
		public void setUserMoney(java.math.BigDecimal userMoney) {
			this.userMoney = userMoney;
		}
		public java.util.Date getBidtime() {
			return bidtime;
		}
		public void setBidtime(java.util.Date bidtime) {
			this.bidtime = bidtime;
		}
		public String getLoanUserName() {
			return loanUserName;
		}
		public void setLoanUserName(String loanUserName) {
			this.loanUserName = loanUserName;
		}
		public BigDecimal getYearInterestRate() {
			return yearInterestRate;
		}
		public void setYearInterestRate(BigDecimal yearInterestRate) {
			this.yearInterestRate = yearInterestRate;
		}
		public Integer getLoanLife() {
			return loanLife;
		}
		public void setLoanLife(Integer loanLife) {
			this.loanLife = loanLife;
		}
		public BigDecimal getIncomeMoney() {
			return incomeMoney;
		}
		public void setIncomeMoney(BigDecimal incomeMoney) {
			this.incomeMoney = incomeMoney;
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer state) {
			this.state = state;
		}

}
