package com.thirdPayInterface.MoneyMorePay.MoneyResponse;
/**
 * 查询提现返回具体参数
 * @author hgh
 *
 */
public class TransferauditBean {
	/**
	 * 乾多多流水号列表
	 */
	protected String LoanNoList;
	/**
	 * 有问题的的乾多多流水号列表
	 */
	protected String LoanNoListFail;
	/**
	 * 平台乾多多标识
	 */
	protected String PlatformMoneymoremore;
	/**
	 *  审核类型
	 *   1.通过
	 	2.退回
		3.二次分配同意
		4.二次分配不同意
		5.提现通过
		6.提现退回
	 * 
	 */
	protected String AuditType;
	/**
	 * 随机时间戳
	 */
	protected String RandomTimeStamp;
	protected String Remark1;
	protected String Remark2;
	protected String Remark3;
	/**
	 * 返回码
	 */
	protected String ResultCode;
	/**
	 * 返回信息
	 */
	protected String Message;
	protected String SignInfo;
	public String getLoanNoList() {
		return LoanNoList;
	}
	public void setLoanNoList(String loanNoList) {
		LoanNoList = loanNoList;
	}
	public String getLoanNoListFail() {
		return LoanNoListFail;
	}
	public void setLoanNoListFail(String loanNoListFail) {
		LoanNoListFail = loanNoListFail;
	}
	public String getPlatformMoneymoremore() {
		return PlatformMoneymoremore;
	}
	public void setPlatformMoneymoremore(String platformMoneymoremore) {
		PlatformMoneymoremore = platformMoneymoremore;
	}
	public String getAuditType() {
		return AuditType;
	}
	public void setAuditType(String auditType) {
		AuditType = auditType;
	}
	public String getRandomTimeStamp() {
		return RandomTimeStamp;
	}
	public void setRandomTimeStamp(String randomTimeStamp) {
		RandomTimeStamp = randomTimeStamp;
	}
	public String getRemark1() {
		return Remark1;
	}
	public void setRemark1(String remark1) {
		Remark1 = remark1;
	}
	public String getRemark2() {
		return Remark2;
	}
	public void setRemark2(String remark2) {
		Remark2 = remark2;
	}
	public String getRemark3() {
		return Remark3;
	}
	public void setRemark3(String remark3) {
		Remark3 = remark3;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getSignInfo() {
		return SignInfo;
	}
	public void setSignInfo(String signInfo) {
		SignInfo = signInfo;
	}
	
}
