package com.hurong.credit.model.system;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.annotations.Expose;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;

public class MailData extends BaseAction  implements Serializable {
	
	@Expose
	private String date;
	@Expose
	private String account;
	@Expose
	private String moneyType;
	@Expose
	private String money;
	@Expose
	private String subject;
	@Expose
	private String userName;
	@Expose
	private String projName;
	@Expose
	private String projNumber;
	@Expose
	private String code;
	@Expose
	private String site;
	@Expose
	private String website;
	@Expose
	private String time;
	@Expose
	private String proj_them;
	@Expose
	private String description;
	@Expose
	private String phone;
	@Expose
	private String toEmail;
	@Expose
	private String bidId;
	@Expose
	private String content;
	
	public String couponNumber;//优惠券激活码
	public String endTime;//优惠券结束时间
	
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public  MailData(String title,String email){
		// 系统配置
		SystemConfig con = getSystemConfig();
		Map maps=AppUtil.getSysConfig();
		// 用户
		BpCustMember user = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(user==null){
			BpCustMemberService bpCustMemberService=(BpCustMemberService)AppUtil.getBean("bpCustMemberService");
			user=bpCustMemberService.getMemberByEmail(email);
		}
		
		this.setWebsite(this.getBasePath());
		this.setTime(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setProj_them(con.getTheme());
		this.setDescription(con.getMetaDescription());
		this.setPhone(con.getPhone4());
		if(user!=null){
		this.setUserName(user.getLoginname());
		}
		if(email!=null&&!email.equals("")){
		this.setToEmail(email);
		}else{
			this.setToEmail(user.getEmail());
		}
		this.setSubject("【"+maps.get("subject") + "】"+title);
	}
	
	public  MailData(String title,String userName,String email){
		// 系统配置
		SystemConfig con = getSystemConfig();
		
		this.setWebsite(this.getBasePath());
		this.setTime(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		this.setProj_them(con.getTheme());
		this.setDescription(con.getMetaDescription());
		this.setPhone(con.getPhone4());
		this.setUserName(userName);
		this.setToEmail(email);
		this.setSubject("【"+con.getMetaTitle() + "】"+title);
	}
	
	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getProjNumber() {
		return projNumber;
	}
	public void setProjNumber(String projNumber) {
		this.projNumber = projNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getProj_them() {
		return proj_them;
	}
	public void setProj_them(String projThem) {
		proj_them = projThem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}


}
