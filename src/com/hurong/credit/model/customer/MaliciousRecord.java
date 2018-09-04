package com.hurong.credit.model.customer;

public class MaliciousRecord {
	private Long mrid;
	private String mrmac;
	private String mrip;
	private String mrhttpheader;


	public Long getMrid() {
		return mrid;
	}

	public void setMrid(Long mrid) {
		this.mrid = mrid;
	}

	public String getMrmac() {
		return mrmac;
	}

	public void setMrmac(String mrmac) {
		this.mrmac = mrmac;
	}

	public String getMrip() {
		return mrip;
	}

	public void setMrip(String mrip) {
		this.mrip = mrip;
	}

	public String getMrhttpheader() {
		return mrhttpheader;
	}

	public void setMrhttpheader(String mrhttpheader) {
		this.mrhttpheader = mrhttpheader;
	}
}
