package com.hurong.credit.model.system;


public class SystemConfig {
public String metaSubject;//头部subject
	
	public  String  metaDescription;//头部description
	
	public  String metaKeywords;//头部keywords
	
	private String host;
	public  String metaRobots;//头部robots
	
	public  String metaTitle;//网站总名称
	
	public  String metaLanguage;//头部language
	
	public  String metaGenerator;//头部generator
	
	
	public  String metaAuthor;//网站作者
	
	public  String theme;//模版路径
	public  String regDeal;//注册协议
	
	public String site;
	public String erpURL;
    

	public String thirdPay;
    public String thirdPayType;//第三方支付类型
    
	public String phone4;//电话
	
    public String poundage; //手续费率
    
    public String fileURL;//文件服务器地址

	public String isOpenPayMentCode; //是否开启交易密码验证
    /**
     * thirdPayAuthentication:第三方验证实名认证
     * ID5Authentication:ID5接口实名认证
     * systemAuthentication:目前系统用的实名认证接口
     * add by linyan
     * 2014-8-18
     */
    public String system_authentication_type;//支持何种方式验证身份证
	/**
	 * 是否需要显示实名认证模块
	 * 目前只有第三方进行实名认证需要用
	 * yes：表示显示实名认证模块
	 * no：表示不显示实名认证模块
	 */
    public String system_authentication_show;//是否需要显示实名认证模块
    
    public String copyRight;//站点版权
    public String workTime;//工作时间
    public String companyAddress;//公司地址
    public String customerEmail;//客服邮箱
    public String consumerHotline;//客服电话
    public String consumerQQ;//客服QQ
    public String p2pLogoFile;//p2p logo
    public String p2pIconFile;//p2p icon
    public String weibo;
    public String weixin;
    public String beianInfo;//备案信息
    public String baiduMapKey;//百度地图appkey
    public String baiduMapMarkers;//百度地图标记
    public String countCode;//站长统计
    public String attest;//网站认证
    
    public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	} 
	
    public String getSystem_authentication_show() {
		return system_authentication_show;
	}

	public void setSystem_authentication_show(String systemAuthenticationShow) {
		system_authentication_show = systemAuthenticationShow;
	}

	public String getSystem_authentication_type() {
		return system_authentication_type;
	}

	public void setSystem_authentication_type(String systemAuthenticationType) {
		system_authentication_type = systemAuthenticationType;
	}

	public String getIsOpenPayMentCode() {
		return isOpenPayMentCode;
	}

	public void setIsOpenPayMentCode(String isOpenPayMentCode) {
		this.isOpenPayMentCode = isOpenPayMentCode;
	}

	public String getPhone4() {
		return phone4;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}

	public String getThirdPay() {
		return thirdPay;
	}

	public void setThirdPay(String thirdPay) {
		this.thirdPay = thirdPay;
	}

	public String getRegDeal() {
		return regDeal;
	}

	public void setRegDeal(String regDeal) {
		this.regDeal = regDeal;
	}


	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getMetaSubject() {
		return metaSubject;
	}

	public void setMetaSubject(String metaSubject) {
		this.metaSubject = metaSubject;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaRobots() {
		return metaRobots;
	}

	public void setMetaRobots(String metaRobots) {
		this.metaRobots = metaRobots;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaLanguage() {
		return metaLanguage;
	}

	public void setMetaLanguage(String metaLanguage) {
		this.metaLanguage = metaLanguage;
	}

	public String getMetaGenerator() {
		return metaGenerator;
	}

	public void setMetaGenerator(String metaGenerator) {
		this.metaGenerator = metaGenerator;
	}

	public String getMetaAuthor() {
		return metaAuthor;
	}

	public void setMetaAuthor(String metaAuthor) {
		this.metaAuthor = metaAuthor;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getThirdPayType() {
		return thirdPayType;
	}

	public void setThirdPayType(String thirdPayType) {
		this.thirdPayType = thirdPayType;
	}

	public String getErpURL() {
		return erpURL;
	}

	public void setErpURL(String erpURL) {
		this.erpURL = erpURL;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getConsumerHotline() {
		return consumerHotline;
	}

	public void setConsumerHotline(String consumerHotline) {
		this.consumerHotline = consumerHotline;
	}

	public String getConsumerQQ() {
		return consumerQQ;
	}

	public void setConsumerQQ(String consumerQQ) {
		this.consumerQQ = consumerQQ;
	}

	public String getP2pLogoFile() {
		return p2pLogoFile;
	}

	public void setP2pLogoFile(String p2pLogoFile) {
		this.p2pLogoFile = p2pLogoFile;
	}

	public String getP2pIconFile() {
		return p2pIconFile;
	}

	public void setP2pIconFile(String p2pIconFile) {
		this.p2pIconFile = p2pIconFile;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getBeianInfo() {
		return beianInfo;
	}

	public void setBeianInfo(String beianInfo) {
		this.beianInfo = beianInfo;
	}

	public String getBaiduMapKey() {
		return baiduMapKey;
	}

	public void setBaiduMapKey(String baiduMapKey) {
		this.baiduMapKey = baiduMapKey;
	}

	public String getBaiduMapMarkers() {
		return baiduMapMarkers;
	}

	public void setBaiduMapMarkers(String baiduMapMarkers) {
		this.baiduMapMarkers = baiduMapMarkers;
	}

	public String getCountCode() {
		return countCode;
	}

	public void setCountCode(String countCode) {
		this.countCode = countCode;
	}

	public String getAttest() {
		return attest;
	}

	public void setAttest(String attest) {
		this.attest = attest;
	}

}