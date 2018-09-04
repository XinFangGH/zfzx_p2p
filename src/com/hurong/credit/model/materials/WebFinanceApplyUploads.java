package com.hurong.credit.model.materials;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class WebFinanceApplyUploads {
	/*
	 * materialstype类别所对应的值
	 * 
		1.身份认证：  IDCard   
		
		2.信用认证： CreditRecord      
		
		3.收入认证： Income       
		
		4.网店认证： WebShop         
		
		5.房产认证： House        
		
		6.购车认证： Vehicle           
		
		7.结婚认证： Marriage         
		
		8.学历认证：Education        
		
		9.工作认证：Career          
		
		10.职称认证：JobTitle     
		
		11.手机认证：MobilePhone  
		
		12.微博认证：MicroBlog  
		
		13.居住认证：Residence 
		
		14.经营场所认证：CompanyPlace  

		15.经营收入认证：CompanyRevenue 
	 * */
	public static String IDCARD="IDCard";
	public static String CREDITRECORD="CreditRecord";
	public static String INCOME="Income";
	public static String WEBSHOP="WebShop";
	public static String HOUSE="House";
	public static String VEHICLE="Vehicle";
	public static String MARRIAGE="Marriage";
	public static String EDUCATION="Education";
	public static String CAREER="Career";
	public static String JOBTITLE="JobTitle";
	public static String MOBILEPHONE="MobilePhone";
	public static String MICROBLOG="MicroBlog";
	public static String RESIDENCE="Residence";
	public static String COMPANYPLACE="CompanyPlace";
	public static String COMPANYREVENUE="CompanyRevenue";
	private Integer refIDCard;
	
	
	public Integer getRefIDCard() {
		return refIDCard;
	}
	public void setRefIDCard(Integer refIDCard) {
		this.refIDCard = refIDCard;
	}
	@Expose
	private Long id;
	/**
	 * //注册用户id
	 */
	@Expose
	private Long userID;
	@Expose
	/**
	 * //材料类别
	 */
	private String materialstype;
	@Expose
	/**
	 * //上传的图片
	 */
	private String files;
	@Expose
	/**
	 * //审核状态（0未上传，1待审查，2已驳回，3已认证）
	 */
	private Integer status;
	@Expose
	/**
	 * //最后操作的时间
	 */
	private java.util.Date lastuploadtime;
	@Expose
	private String rejectReason;
	/**
	 * 基础材料id
	 */
	@Expose
	private Long materialId;
	/**
	 * 贷款材料id
	 */
	@Expose
	private Long conditionId;
	/**
	 * 上传的材料份数
	 */
	@Expose
	private Long materialCount;
	
	public Long getMaterialCount() {
		return materialCount;
	}
	public void setMaterialCount(Long materialCount) {
		this.materialCount = materialCount;
	}
	public Long getConditionId() {
		return conditionId;
	}
	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getMaterialstype() {
		return materialstype;
	}
	public void setMaterialstype(String materialstype) {
		this.materialstype = materialstype;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public java.util.Date getLastuploadtime() {
		return lastuploadtime;
	}
	public void setLastuploadtime(java.util.Date lastuploadtime) {
		this.lastuploadtime = lastuploadtime;
	}
	public WebFinanceApplyUploads() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WebFinanceApplyUploads(Long id, Long userID, String materialstype,
			String files, Integer status, Date lastuploadtime) {
		super();
		this.id = id;
		this.userID = userID;
		this.materialstype = materialstype;
		this.files = files;
		this.status = status;
		this.lastuploadtime = lastuploadtime;
	}
	
	
}
