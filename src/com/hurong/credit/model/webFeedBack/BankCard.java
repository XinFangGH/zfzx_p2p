package com.hurong.credit.model.webFeedBack;

public class BankCard {
    protected String cardId;
    protected String bankName;
    protected String cardNum;
    protected String signDealQuota;
    protected String dayDealQuota;
    protected String bindCardStatus;
    protected String spell;
    protected String bankMaxLogo;
    protected String bankMinLogo;
    protected String cardCode;

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getBankMaxLogo() {
        return bankMaxLogo;
    }

    public void setBankMaxLogo(String bankMaxLogo) {
        this.bankMaxLogo = bankMaxLogo;
    }

    public String getBankMinLogo() {
        return bankMinLogo;
    }

    public void setBankMinLogo(String bankMinLogo) {
        this.bankMinLogo = bankMinLogo;
    }

    public String getSpell() {
        return spell;
    }


    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getBindCardStatus() {
        return bindCardStatus;
    }

    public void setBindCardStatus(String bindCardStatus) {
        this.bindCardStatus = bindCardStatus;
    }

    protected String bankLogo;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getSignDealQuota() {
        return signDealQuota;
    }

    public void setSignDealQuota(String signDealQuota) {
        this.signDealQuota = signDealQuota;
    }

    public String getDayDealQuota() {
        return dayDealQuota;
    }

    public void setDayDealQuota(String dayDealQuota) {
        this.dayDealQuota = dayDealQuota;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
}
