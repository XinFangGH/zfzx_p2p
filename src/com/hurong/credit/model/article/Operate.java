package com.hurong.credit.model.article;

import java.math.BigDecimal;

public class Operate {

    private  Integer  sumLoanMoney;//累计借贷金额
    private  Integer  sumLoanCount;//累计借贷笔数
    private  Integer  balanceLoanMoney;//借贷余额
    private  Integer  balanceLoanCount;//借贷余额总笔数
    private  Integer  sumPayPeople;//累计出借人数量
    private  Integer  payPeople;//当前出借人数量
    private  Integer  sumIncomePeople;//累计借款人数量
    private  Integer  incomePeople;//当前借款人数量
    private BigDecimal tenIncomeMoneyProporion;//前十大借款人待还金额占比
    private  BigDecimal  maxIncomeMoneyProporion;//最大单一借款人待还金额占比

    public Operate() {

    }

    public Operate(Integer sumLoanMoney, Integer sumLoanCount, Integer balanceLoanMoney, Integer balanceLoanCount, Integer sumPayPeople, Integer payPeople, Integer sumIncomePeople, Integer incomePeople, BigDecimal tenIncomeMoneyProporion, BigDecimal maxIncomeMoneyProporion) {
        this.sumLoanMoney = sumLoanMoney;
        this.sumLoanCount = sumLoanCount;
        this.balanceLoanMoney = balanceLoanMoney;
        this.balanceLoanCount = balanceLoanCount;
        this.sumPayPeople = sumPayPeople;
        this.payPeople = payPeople;
        this.sumIncomePeople = sumIncomePeople;
        this.incomePeople = incomePeople;
        this.tenIncomeMoneyProporion = tenIncomeMoneyProporion;
        this.maxIncomeMoneyProporion = maxIncomeMoneyProporion;
    }

    public Integer getSumLoanMoney() {
        return sumLoanMoney;
    }

    public void setSumLoanMoney(Integer sumLoanMoney) {
        this.sumLoanMoney = sumLoanMoney;
    }

    public Integer getSumLoanCount() {
        return sumLoanCount;
    }

    public void setSumLoanCount(Integer sumLoanCount) {
        this.sumLoanCount = sumLoanCount;
    }

    public Integer getBalanceLoanMoney() {
        return balanceLoanMoney;
    }

    public void setBalanceLoanMoney(Integer balanceLoanMoney) {
        this.balanceLoanMoney = balanceLoanMoney;
    }

    public Integer getBalanceLoanCount() {
        return balanceLoanCount;
    }

    public void setBalanceLoanCount(Integer balanceLoanCount) {
        this.balanceLoanCount = balanceLoanCount;
    }

    public Integer getSumPayPeople() {
        return sumPayPeople;
    }

    public void setSumPayPeople(Integer sumPayPeople) {
        this.sumPayPeople = sumPayPeople;
    }

    public Integer getPayPeople() {
        return payPeople;
    }

    public void setPayPeople(Integer payPeople) {
        this.payPeople = payPeople;
    }

    public Integer getSumIncomePeople() {
        return sumIncomePeople;
    }

    public void setSumIncomePeople(Integer sumIncomePeople) {
        this.sumIncomePeople = sumIncomePeople;
    }

    public Integer getIncomePeople() {
        return incomePeople;
    }

    public void setIncomePeople(Integer incomePeople) {
        this.incomePeople = incomePeople;
    }

    public BigDecimal getTenIncomeMoneyProporion() {
        return tenIncomeMoneyProporion;
    }

    public void setTenIncomeMoneyProporion(BigDecimal tenIncomeMoneyProporion) {
        this.tenIncomeMoneyProporion = tenIncomeMoneyProporion;
    }

    public BigDecimal getMaxIncomeMoneyProporion() {
        return maxIncomeMoneyProporion;
    }

    public void setMaxIncomeMoneyProporion(BigDecimal maxIncomeMoneyProporion) {
        this.maxIncomeMoneyProporion = maxIncomeMoneyProporion;
    }
}
