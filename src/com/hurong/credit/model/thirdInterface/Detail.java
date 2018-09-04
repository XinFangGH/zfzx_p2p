package com.hurong.credit.model.thirdInterface;

public class Detail {
   private String amount;//转账金额
   private String targetUserType;//收款人类型
   private String targetPlatformUserNo;//收款人账号
   private String bizType;
public void setAmount(String amount) {
	this.amount = amount;
}
public String getAmount() {
	return amount;
}
public void setTargetUserType(String targetUserType) {
	this.targetUserType = targetUserType;
}
public String getTargetUserType() {
	return targetUserType;
}
public void setTargetPlatformUserNo(String targetPlatformUserNo) {
	this.targetPlatformUserNo = targetPlatformUserNo;
}
public String getTargetPlatformUserNo() {
	return targetPlatformUserNo;
}
public void setBizType(String bizType) {
	this.bizType = bizType;
}
public String getBizType() {
	return bizType;
}

}
