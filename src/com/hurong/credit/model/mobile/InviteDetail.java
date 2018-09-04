package com.hurong.credit.model.mobile;

import com.hurong.core.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class InviteDetail extends BaseModel{

    private Integer id;
    private String truename;
    private String telphone;
    private Date registrationDate;
    private String isCheckCard;
    private Long bidId;
    private String bidName;
    private BigDecimal userMoney;
    private Date bidtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "InviteDetail{" +
                "id=" + id +
                ", truename='" + truename + '\'' +
                ", telphone='" + telphone + '\'' +
                ", registrationDate=" + registrationDate +
                ", isCheckCard='" + isCheckCard + '\'' +
                ", bidId=" + bidId +
                ", bidName='" + bidName + '\'' +
                ", userMoney=" + userMoney +
                ", bidtime=" + bidtime +
                '}';
    }
}
