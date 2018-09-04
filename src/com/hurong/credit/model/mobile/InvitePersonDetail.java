package com.hurong.credit.model.mobile;

import com.hurong.core.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class InvitePersonDetail extends BaseModel{
    private String truename;
    private String telphone;
    private Date registrationDate;
    private String isCheckCard;
    private Long bidId;
    private String bidName;
    private BigDecimal userMoney;
    private Date bidtime;
    private Integer payMoneyTime;
    private Date startIntentDate;
    private Date endIntentDate;
    private Integer state;

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getIsCheckCard() {
        return isCheckCard;
    }

    public void setIsCheckCard(String isCheckCard) {
        this.isCheckCard = isCheckCard;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
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

    public Integer getPayMoneyTime() {
        return payMoneyTime;
    }

    public void setPayMoneyTime(Integer payMoneyTime) {
        this.payMoneyTime = payMoneyTime;
    }

    public Date getStartIntentDate() {
        return startIntentDate;
    }

    public void setStartIntentDate(Date startIntentDate) {
        this.startIntentDate = startIntentDate;
    }

    public Date getEndIntentDate() {
        return endIntentDate;
    }

    public void setEndIntentDate(Date endIntentDate) {
        this.endIntentDate = endIntentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
