package com.hurong.credit.model.user;

public class Member {
	
	protected Long id;
	protected String username;
	protected String password;
	protected String truename;
	
	
	
	public Member() {
		super();
	}
	public Member(Long id, String username, String password, String truename) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.truename = truename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	
	
	

}
