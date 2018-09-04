package com.hurong.credit.model.user;

public class AuCardReturn {

	protected String resultcode;//返回状态码200表示成功
	protected String reason;//结果
	protected String error_code;//
	protected String result;//返回信息实体类
	protected String area;//地区
	protected String sex;//性别
	protected String birthday;//生日
	protected String verify;//
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String errorCode) {
		error_code = errorCode;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	
	
	
}
