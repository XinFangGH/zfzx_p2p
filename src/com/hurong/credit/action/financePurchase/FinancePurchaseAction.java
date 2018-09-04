package com.hurong.credit.action.financePurchase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.config.RandomValidateCode;
import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemaccountSetting;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.creditFlow.finance.FundIncome;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.customer.BpCustRelation;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.customer.MaliciousRecord;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.mobile.MobileDataResultModel;
import com.hurong.credit.model.mobile.MobileErrorCode;
import com.hurong.credit.model.p2p.BpFinanceApply;
import com.hurong.credit.model.system.product.BpProductParameter;
import com.hurong.credit.model.system.product.Dictionary;
import com.hurong.credit.model.thirdInterface.CsBank;
import com.hurong.credit.model.thirdInterface.WebBankCode;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.model.user.BpDicArea;
import com.hurong.credit.service.credit.thirdInterface.WebBankCodeFudianService;
import com.hurong.credit.service.creditFlow.assuretenet.OurProcreditAssuretenetService;
import com.hurong.credit.service.creditFlow.auto.PlBidAutoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObAccountDealInfoService;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.creditFlow.finance.BpFundIntentService;
import com.hurong.credit.service.creditFlow.finance.SlFundIntentService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidInfoService;
import com.hurong.credit.service.creditFlow.financingAgency.PlBidPlanService;
import com.hurong.credit.service.creditFlow.materials.OurProcreditMaterialsEnterpriseService;
import com.hurong.credit.service.customer.BpCustRelationService;
import com.hurong.credit.service.customer.InvestPersonInfoService;
import com.hurong.credit.service.customer.MaliciousRecordService;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoService;
import com.hurong.credit.service.p2p.BpFinanceApplyService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.service.system.product.BpProductParameterService;
import com.hurong.credit.service.system.product.DictionaryService;
import com.hurong.credit.service.thirdInterface.WebBankCodeService;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.service.user.BpDicAreaService;
import com.hurong.credit.util.*;
import com.hurong.credit.util.Random;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FinancePurchaseAction extends BaseAction {

	private static final long serialVersionUID = -25541236985328967L;
	private BpFinanceApply financeApply;
	@Resource
	private BpFinanceApplyService financeApplyService;
	@Resource
	private BpFinanceApplyUserService financeApplyUserService;
	private BpCustMember bpCustMember;
	@Resource
	private BpDicAreaService bpDicAreaService;
	@Resource
	private PlBidInfoService plBidInfoService;
	@Resource
	private OurProcreditAssuretenetService ourProcreditAssuretenetService;
	@Resource
	private OurProcreditMaterialsEnterpriseService ourProcreditMaterialsEnterpriseService;
	@Resource
	private WebBankcardService webBankcardService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private BpFundIntentService bpFundIntentService;
	@Resource
	private BpProductParameterService bpProductParameterService;
	@Resource
	private ObSystemAccountService obSystemAccountService;
	@Resource
	private ObAccountDealInfoService obAccountDealInfoService;
	@Resource
	private PlManageMoneyPlanBuyinfoService plManageMoneyPlanBuyinfoService;
	@Resource
	private InvestPersonInfoService investPersonInfoService;
	@Resource
	private WebBankCodeService webBankCodeService;
	@Resource
	private PlBidPlanService plBidPlanService;
	@Resource
	private PlBidAutoService plBidAutoService;
	@Resource
	private SlFundIntentService slFundIntentService;
	@Resource
	private BpCustRelationService bpCustRelationService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;
	@Resource
	private WebBankCodeFudianService webBankCodeFudianService;
	@Resource
	private MaliciousRecordService maliciousRecordService;
	
	private List<Dictionary> listloanProject;
	private PlBidInfo plBidInfo ;
	private PlBidAuto bidAuto;
	private String successHtml;//返回登录信息
	//得到config.properties读取的所有资源
	private static Map<?,?> configMap = AppUtil.getConfigMap();
	
	private String selectTime;
	private String selectTime2;
	private String checkCode;// 个人验证码
	private String checkCodeB;//企业验证码
	private List<WebBankCode> listBankCode;
	private List<CsBank> listCsbank;
	private List<WebBankcard> listBankCard;
	private List<BpDicArea> listBpDicArea;
	private String state;
	private String updateState;
	private String type;
	private String notMoney;
	private String productName;
	private long id;
	private String contractUrl;

	public PlBidAuto getBidAuto() {
		return bidAuto;
	}

	public void setBidAuto(PlBidAuto bidAuto) {
		this.bidAuto = bidAuto;
	}

	public String getContractUrl() {
		return contractUrl;
	}

	public void setContractUrl(String contractUrl) {
		this.contractUrl = contractUrl;
	}

	public PlBidInfo getPlBidInfo() {
		return plBidInfo;
	}

	public void setPlBidInfo(PlBidInfo plBidInfo) {
		this.plBidInfo = plBidInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//查询条件
	private  Map<String,String> searchMap = new HashMap<String,String>();

	private String token;//我要融资令牌
	
	public String getSuccessHtml() {
		return successHtml;
	}

	public void setSuccessHtml(String successHtml) {
		this.successHtml = successHtml;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNotMoney() {
		return notMoney;
	}

	public void setNotMoney(String notMoney) {
		this.notMoney = notMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUpdateState() {
		return updateState;
	}

	public void setUpdateState(String updateState) {
		this.updateState = updateState;
	}

	public String getSelectTime2() {
		return selectTime2;
	}

	public void setSelectTime2(String selectTime2) {
		this.selectTime2 = selectTime2;
	}

	public List<BpDicArea> getListBpDicArea() {
		return listBpDicArea;
	}

	public void setListBpDicArea(List<BpDicArea> listBpDicArea) {
		this.listBpDicArea = listBpDicArea;
	}

	public List<WebBankcard> getListBankCard() {
		return listBankCard;
	}

	public void setListBankCard(List<WebBankcard> listBankCard) {
		this.listBankCard = listBankCard;
	}

	public List<CsBank> getListCsbank() {
		return listCsbank;
	}

	public void setListCsbank(List<CsBank> listCsbank) {
		this.listCsbank = listCsbank;
	}

	public List<WebBankCode> getListBankCode() {
		return listBankCode;
	}

	public void setListBankCode(List<WebBankCode> listBankCode) {
		this.listBankCode = listBankCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}
	
	public List<Dictionary> getListloanProject() {
		return listloanProject;
	}

	public void setListloanProject(List<Dictionary> listloanProject) {
		this.listloanProject = listloanProject;
	}

	private List<BpProductParameter> listProduct;
	private BpProductParameter bpProductParameter;
	protected String productId;
	protected String linkPersion;
	protected String phone;
	protected String loanMoney;
	protected String isOnline;
	protected String loanTimeLen;
	protected String area;
	protected String remark;
	protected String loanType; // 1 企业 0 个人
	protected String businessName;// 企业名称
	
	protected String companyNo;//工商注册号
	protected String cardnumber;//身份证号
	protected String legalEmail;//法人邮箱
	protected String registeredcapital;//注册资本
	protected String createdate;//公司成立时间
	protected String startTime;//期望放款时间
	protected String expectAccrual;//期望利率
	protected String payIntersetWay;//返款方式
	protected String loanUse;//借款用途
	protected String rebatesUse;//还款来源

	public BpProductParameter getBpProductParameter() {
		return bpProductParameter;
	}

	public void setBpProductParameter(BpProductParameter bpProductParameter) {
		this.bpProductParameter = bpProductParameter;
	}

	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo =  StringUtil.html2Text(companyNo);
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = StringUtil.html2Text(cardnumber);
	}

	public String getLegalEmail() {
		return legalEmail;
	}

	public void setLegalEmail(String legalEmail) {
		this.legalEmail = StringUtil.html2Text(legalEmail);
	}

	public String getRegisteredcapital() {
		return registeredcapital;
	}

	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = StringUtil.html2Text(registeredcapital);
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = StringUtil.html2Text(createdate);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = StringUtil.html2Text(startTime);
	}

	public String getExpectAccrual() {
		return expectAccrual;
	}

	public void setExpectAccrual(String expectAccrual) {
		this.expectAccrual = StringUtil.html2Text(expectAccrual);
	}

	public String getPayIntersetWay() {
		return payIntersetWay;
	}

	public void setPayIntersetWay(String payIntersetWay) {
		this.payIntersetWay = StringUtil.html2Text(payIntersetWay);
	}

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = StringUtil.html2Text(loanUse);
	}

	public String getRebatesUse() {
		return rebatesUse;
	}

	public void setRebatesUse(String rebatesUse) {
		this.rebatesUse = StringUtil.html2Text(rebatesUse);
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		
		//System.out.println(this.getRequest().getParameter("businessName"));
		this.businessName =businessName;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = StringUtil.html2Text(loanType);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = StringUtil.html2Text(productId);
	}

	public String getLinkPersion() {
		return linkPersion;
	}

	public void setLinkPersion(String linkPersion) {
		this.linkPersion = linkPersion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = StringUtil.html2Text(phone);
	}

	public String getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney = StringUtil.html2Text(loanMoney);
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public String getLoanTimeLen() {
		return loanTimeLen;
	}

	public void setLoanTimeLen(String loanTimeLen) {
		this.loanTimeLen = StringUtil.html2Text(loanTimeLen);
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area =StringUtil.html2Text(area);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = StringUtil.html2Text(remark);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = StringUtil.html2Text(productName);
	}

	public List<BpProductParameter> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<BpProductParameter> listProduct) {
		this.listProduct = listProduct;
	}

	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime =StringUtil.html2Text( selectTime);
	}

	private String Q_notMoney;

	public String getQ_notMoney() {
		return Q_notMoney;
	}

	public void setQ_notMoney(String qNotMoney) {
		Q_notMoney = StringUtil.html2Text(qNotMoney);
	}

	private String financ_type;// 融资类型

	private String from;// 开始时间
	private String to;// 结束时间

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = StringUtil.html2Text(from);
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = StringUtil.html2Text(to);
	}

	public String getFinanc_type() {
		return financ_type;
	}

	public void setFinanc_type(String financType) {
		financ_type = StringUtil.html2Text(financType);
	}

	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}

	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}
	
	private URL remoteFile = null;
	
	
	/** 
	 * 通过IP地址获取MAC地址 
	 * @param ip String,127.0.0.1格式 
	 * @return mac String 
	 * @throws Exception 
	 */  
	public String getMACAddress(String ip) throws Exception {  
	    String line = "";  
	    String macAddress = "";  
	    final String MAC_ADDRESS_PREFIX = "MAC Address = ";  
	    final String LOOPBACK_ADDRESS = "127.0.0.1";  
	    //如果为127.0.0.1,则获取本地MAC地址。  
	    if (LOOPBACK_ADDRESS.equals(ip)) {  
	        InetAddress inetAddress = InetAddress.getLocalHost();  
	        //貌似此方法需要JDK1.6。  
	        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();  
	        //下面代码是把mac地址拼装成String  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < mac.length; i++) {  
	            if (i != 0) {  
	                sb.append("-");  
	            }  
	            //mac[i] & 0xFF 是为了把byte转化为正整数  
	            String s = Integer.toHexString(mac[i] & 0xFF);  
	            sb.append(s.length() == 1 ? 0 + s : s);  
	        }  
	        //把字符串所有小写字母改为大写成为正规的mac地址并返回  
	        macAddress = sb.toString().trim().toUpperCase();  
	        return macAddress;  
	    }  
	    //获取非本地IP的MAC地址  
	    try {  
	        Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
	        InputStreamReader isr = new InputStreamReader(p.getInputStream());  
	        BufferedReader br = new BufferedReader(isr);  
	        while ((line = br.readLine()) != null) {  
	            if (line != null) {  
	                int index = line.indexOf(MAC_ADDRESS_PREFIX);  
	                if (index != -1) {  
	                    macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();  
	                }  
	            }  
	        }  
	        br.close();  
	    } catch (Exception e) {  
	        e.printStackTrace(System.out);  
	    }  
	    return macAddress;  
	}  
	/**
	 * 补充材料样例下载
	 * @return
	 */
	public String downimg(){
		String filename = this.getRequest().getParameter("imgname");
		String filePath = this.getBasePath()+"/theme/proj_wenandai/images/samples/"+filename;
		System.out.println("文件下载："+filePath);
		HttpServletResponse response=this.getResponse();
		try {
	            File file = new File(filePath);
	            String  fileName = file.getName();
	            															
	            response.setContentType("application/octet-stream");
	            response.reset();//清除缓冲中的数据
	            response.addHeader("Content-Disposition", "attachment;filename="+fileName);
	            																			
	           
	            HttpURLConnection httpConn = null;
	           
	        	remoteFile = new URL(filePath);//建立远程连接
	            httpConn = (HttpURLConnection)remoteFile.openConnection();  //打开连接
	            
	            httpConn.setRequestMethod("GET");
	            httpConn.setConnectTimeout(1000 * 1000);//设置下载连接时间
	            InputStream inStream = httpConn.getInputStream();//通过输入流获取图片数据流
	            
	            
	            byte data[];//声明字节
				try {
					data = readInputStream(inStream);
					inStream.read(data);  //读数据
					inStream.close();
					OutputStream os = response.getOutputStream();
					os.write(data);
					os.flush();
				    os.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
		       } catch (Exception e) {
					e.printStackTrace();
		       }
		       return null;
		
	}
	
	// 合同下载
	public void downLoad() throws UnsupportedEncodingException {
		String contractUrl = this.getRequest().getParameter("contractUrl");
		System.out.println("合同"+contractUrl);
//		String contractUrl =  new String(this.getRequest().getParameter("contractUrl").getBytes("iso-8859-1"), "utf-8");
//		DownloadURLFile.getInstance().downLoad(AppUtil.getAppAbsolutePath()+contractUrl, this.getResponse());
		
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(contractUrl!=null&&!"".equals(contractUrl)){
			if(contractUrl.indexOf("\\" )!= -1){
				contractUrl=contractUrl.replace("\\", "/");
			} 
			
			boolean b = false;
			//根据合同下载地址传送的客户类型来选择是否查询投资记录
			String customerType=this.getRequest().getParameter("customerType");
			if(customerType!=null&&!"".equals(customerType)&&customerType.equals("loan")){
				b=true;
			}else{
				String curl = "";
				//取出当前用户所有合同
				List<InvestPersonInfo> invest = investPersonInfoService.queryName(mem.getLoginname(),"All",null);
				if(invest!=null&&invest.size()>0){
					for(InvestPersonInfo bid:invest){//遍历
						if(bid.getContractUrls() == null||"".equals(bid.getContractUrls())){//为空跳出此次循环
							 continue;
						}
						if(contractUrl.equals(bid.getContractUrls())){//判断用户确实有此份合同跳出循环
							b = true;
							break;
						}
					}
				}
			}
			
			//插入下载合同Ip信息
			if(!b){//当前用户没有合同的下载权
				MaliciousRecord mr = new MaliciousRecord();
		        try{  
		        	String ip = this.getRequest().getHeader("x-forwarded-for"); 
		            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		                ip = this.getRequest().getHeader("Proxy-Client-IP"); 
		            } 
		            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		                ip = this.getRequest().getHeader("WL-Proxy-Client-IP"); 
		            } 
		            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		                ip = this.getRequest().getRemoteAddr(); 
		            } 
		            String sMAC = getMACAddress(ip);
		        	mr.setMrip(ip);
					mr.setMrmac(sMAC);
					mr.setMrhttpheader("header");
					maliciousRecordService.save(mr);
		        }catch(Exception e){
		            e.printStackTrace();  
		        }  
//				this.setSuccessResultValue("/html/error_page_404.html");
//				return "freemarker";
			}
			
			String  filePath = AppUtil.getConfigMap().get("fileURL") + contractUrl;//拼接文件在远程服务器上的路径
			HttpServletResponse response=this.getResponse();
			try {
//				System.out.println("*******下载合同路径*****filePath="+filePath);
				String fileName=filePath.substring(filePath.lastIndexOf("/")+1);//获取文件名称
				filePath=filePath.replace(fileName,new String(fileName.getBytes("UTF-8"),"GBK"));
	            fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");//把文件名按UTF-8取出并按ISO8859-1编码，
	            															//最多支持17个中文，因为header有150个字节限制。
	            response.setContentType("application/octet-stream");//告诉浏览器输出内容为流
	            response.reset();//清除缓冲中的数据
	            response.addHeader("Content-Disposition", "attachment;filename="+fileName);//Content-Disposition中指定的类型是文件的扩展名，
	            																			//并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，
	            //HTTP远程下载songwj
	            HttpURLConnection httpConn = null;  
	        	remoteFile =new URL(filePath);//建立远程连接
	            httpConn = (HttpURLConnection)remoteFile.openConnection();  //打开连接
	            
	            httpConn.setRequestMethod("GET");    
	            httpConn.setConnectTimeout(1000 * 1000);//设置下载连接时间
	            InputStream inStream = httpConn.getInputStream();//通过输入流获取图片数据流
	            
	            
	            byte data[];//声明字节
				try {
					data = readInputStream(inStream);
					inStream.read(data);  //读数据     
					inStream.close();     
					OutputStream os = response.getOutputStream();    
					os.write(data);    
					os.flush();    
				    os.close();  
				}catch (Exception e) {
					e.printStackTrace();
				} 
		       } catch (Exception e) {
					e.printStackTrace();
		       } 
		}else{
			webMsgInstance("0", Constants.CODE_FAILED, "该用户没有生成合同",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYFINACE).getTemplateFilePath());
		}
	}
	
	//主要用于流的转换
	public static byte[] readInputStream(InputStream inStream) throws Exception{    
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();    
        byte[] buffer = new byte[2048];    
        int len = 0;    
        while( (len=inStream.read(buffer)) != -1 ){    
            outStream.write(buffer, 0, len);    
        }    
        inStream.close();    
        return outStream.toByteArray();    
	}  
	
	private void planInfo(BpCustMember mem,Pager pager){
		//投标成功
		List<PlBidPlan> listPlanSuccess = new ArrayList<PlBidPlan>();
		List<InvestPersonInfo> invest = investPersonInfoService.queryName(mem.getLoginname(),"success",pager);
		BigInteger successcount = investPersonInfoService.getCount(mem.getLoginname(),"success",pager);
		if(invest!=null&&invest.size()>0){
			for(InvestPersonInfo inve :invest){
				try{
					PlBidPlan plan = new PlBidPlan();
					PlBidPlan oldPlan =plBidPlanService.setProperty(plBidPlanService.get(inve.getBidPlanId()), this.getRequest());
					BeanUtil.copyNotNullProperties(plan, oldPlan);
					plBidPlanService.returnPlBidPlan(plan);
					String url = plManageMoneyPlanBuyinfoService.getUrl(inve.getOrderNo(), "");//模板下载路径
					BigDecimal userMoney = new BigDecimal(0);
					userMoney = plBidInfoService.queryUserMoney(inve.getBidPlanId(), mem.getId(),inve.getOrderNo());
					plan.setAfterMoneyTotal(inve.getInvestMoney());
					plan.setUrl(url);
					BigDecimal notMoneyTotal = new BigDecimal(0);
					notMoneyTotal = bpFundIntentService.getMoney(inve.getBidPlanId(), mem.getLoginname(), "notMoney",inve.getOrderNo());
					plan.setNotMoneyTotal(notMoneyTotal!=null?notMoneyTotal:new BigDecimal(0));
					plan.setInvestOrderNo(inve.getOrderNo());
					listPlanSuccess.add(plan);//添加到集合中
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		this.getRequest().setAttribute("listPlanSuccess", listPlanSuccess);
		this.getRequest().setAttribute("successcount", successcount);
		
		
		//投标失败的
		//List<PlBidInfo> plFila = plBidInfoService.getBidList(mem.getId(), null);
		List<PlBidPlan> listPlanFila = new ArrayList<PlBidPlan>();
		List<PlBidPlan> listFila =  plBidPlanService.queryBidPlan(mem.getLoginname(), "error");
		if(listFila!=null&&listFila.size()>0){
			for(PlBidPlan bid :listFila){
				PlBidPlan plan =plBidPlanService.setProperty(plBidPlanService.get(bid.getBidId()), this.getRequest());
				plBidPlanService.returnPlBidPlan(plan);
				String url = "";
				List<PlBidInfo> listBidInfoUrl2 = plBidInfoService.getIntentInfo(bid.getBidId(),mem.getId());
				if(listBidInfoUrl2!=null&&listBidInfoUrl2.size()>0){
					for(PlBidInfo bp : listBidInfoUrl2){
						url = plManageMoneyPlanBuyinfoService.getUrl(bp.getOrderNo(), "");//模板下载路径
					}
				}
				plan.setAfterMoneyTotal(plBidInfoService.queryUserMoney(bid.getBidId(), mem.getId(),bid.getInvestOrderNo()));
				plan.setUrl(url);
				BigDecimal notMoneyTotal = bpFundIntentService.getMoney(bid.getBidId(),  mem.getLoginname(), "notMoney",null);
				plan.setNotMoneyTotal(notMoneyTotal!=null?notMoneyTotal:new BigDecimal(0));
				listPlanFila.add(plan);//添加到集合中
			}
		}
		this.getRequest().setAttribute("listPlanFila", listPlanFila);
		
		
	}
	private void bpFund(BpCustMember mem,Pager pager){
		//已结束
		List<PlBidPlan> listPlan2 = new ArrayList<PlBidPlan>();
		List<InvestPersonInfo> investPerson = investPersonInfoService.queryName(mem.getLoginname(),"Planback",pager);
		BigInteger Planbackcount = investPersonInfoService.getCount(mem.getLoginname(),"Planback",pager);
		if(investPerson!=null&&investPerson.size()>0){
			for(InvestPersonInfo info:investPerson){
				try{
					PlBidPlan plan = new PlBidPlan();
					PlBidPlan oldPlan =plBidPlanService.setProperty(plBidPlanService.get(info.getBidPlanId()), this.getRequest());
					BeanUtil.copyNotNullProperties(plan, oldPlan);
					plBidPlanService.returnPlBidPlan(plan);
					plan.setAfterMoneyTotal(plBidInfoService.queryUserMoney(info.getBidPlanId(), mem.getId(),info.getOrderNo()));
					String url = plManageMoneyPlanBuyinfoService.getUrl(info.getOrderNo(), "");//模板下载路径
					plan.setUrl(url);
					BigDecimal notMoneyTotal = bpFundIntentService.getMoney(info.getBidPlanId(),  mem.getLoginname(), "notMoney",null);
					 plan.setNotMoneyTotal(notMoneyTotal==null?new BigDecimal(0):notMoneyTotal);
					 plan.setInvestOrderNo(info.getOrderNo());
					listPlan2.add(plan);//添加到集合中
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}this.getRequest().setAttribute("listPlanback", listPlan2);
		this.getRequest().setAttribute("Planbackcount", Planbackcount);
		
		//回款中
		List<PlBidPlan> listPlan = new ArrayList<PlBidPlan>();
		List<InvestPersonInfo> invest = investPersonInfoService.queryName(mem.getLoginname(),"Planbacking",pager);
		BigInteger Planbackingcount = investPersonInfoService.getCount(mem.getLoginname(),"Planbacking",pager);
		if(invest!=null&&invest.size()>0){
			for(InvestPersonInfo bid:invest){
				try{
					PlBidPlan plan = new PlBidPlan();
					PlBidPlan oldPlan =plBidPlanService.setProperty(plBidPlanService.get(bid.getBidPlanId()), this.getRequest());
					BeanUtil.copyNotNullProperties(plan, oldPlan);
					plBidPlanService.returnPlBidPlan(plan);
					String url = plManageMoneyPlanBuyinfoService.getUrl(bid.getOrderNo(), "");//模板下载路径
					plan.setUrl(url);
					plan.setAfterMoneyTotal(bid.getInvestMoney());
					BigDecimal notMoneyTotal = bpFundIntentService.getMoney(bid.getBidPlanId(), mem.getLoginname(), "notMoney",bid.getOrderNo());
					plan.setNotMoneyTotal(notMoneyTotal==null?new BigDecimal(0):notMoneyTotal);
					plan.setInvestOrderNo(bid.getOrderNo());
					//System.out.println("bidProName=="+plan.getBidProName()+"*******orderNo==="+bid.getOrderNo());
					listPlan.add(plan);//添加到集合中
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
		}
		this.getRequest().setAttribute("listPlanbacking", listPlan);
		this.getRequest().setAttribute("Planbackingcount", Planbackingcount);
	}
	
	public String ajaxplan(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		Integer t = Integer.valueOf(this.getRequest().getParameter("pageNumber"));
		
		//投标成功
		String state = this.getRequest().getParameter("state");
		List<PlBidPlan> listPlanSuccess = new ArrayList<PlBidPlan>();
		List<InvestPersonInfo> invest = investPersonInfoService.queryNamepager(mem.getLoginname(),state,t,10);
		BigInteger count = investPersonInfoService.getCount(mem.getLoginname(),state,pager);
		if(invest!=null&&invest.size()>0){
			for(InvestPersonInfo inve :invest){
				try{
					PlBidPlan plan = new PlBidPlan();
					PlBidPlan oldPlan =plBidPlanService.setProperty(plBidPlanService.get(inve.getBidPlanId()), this.getRequest());
					BeanUtil.copyNotNullProperties(plan, oldPlan);
					plBidPlanService.returnPlBidPlan(plan);
					String url = plManageMoneyPlanBuyinfoService.getUrl(inve.getOrderNo(), "");//模板下载路径
					BigDecimal userMoney = new BigDecimal(0);
					userMoney = plBidInfoService.queryUserMoney(inve.getBidPlanId(), mem.getId(),inve.getOrderNo());
					plan.setAfterMoneyTotal(inve.getInvestMoney());
					plan.setUrl(url);
					BigDecimal notMoneyTotal = new BigDecimal(0);
					notMoneyTotal = bpFundIntentService.getMoney(inve.getBidPlanId(), mem.getLoginname(), "notMoney",inve.getOrderNo());
					plan.setNotMoneyTotal(notMoneyTotal!=null?notMoneyTotal:new BigDecimal(0));
					plan.setInvestOrderNo(inve.getOrderNo());
					listPlanSuccess.add(plan);//添加到集合中
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		List<PlBidPlan> listPlanS = new ArrayList<PlBidPlan>();;
		for(int i=0;i<listPlanSuccess.size();i++){
			PlBidPlan pbplan = new PlBidPlan();
			pbplan.setBidId(listPlanSuccess.get(i).getBidId());
			pbplan.setInterestRate(listPlanSuccess.get(i).getInterestRate());
			pbplan.setLoanLife(listPlanSuccess.get(i).getLoanLife());
			pbplan.setBidProName(listPlanSuccess.get(i).getBidProName());
			pbplan.setPublishSingeTime(listPlanSuccess.get(i).getPublishSingeTime());
			pbplan.setAfterMoneyTotal(listPlanSuccess.get(i).getAfterMoneyTotal());
			pbplan.setLoanLife(listPlanSuccess.get(i).getLoanLife());
			pbplan.setInterestRate(listPlanSuccess.get(i).getInterestRate());
			pbplan.setNotMoneyTotal(listPlanSuccess.get(i).getNotMoneyTotal());
			pbplan.setInvestOrderNo(listPlanSuccess.get(i).getInvestOrderNo());
			pbplan.setUrl(listPlanSuccess.get(i).getUrl());
			listPlanS.add(pbplan);
		}
		
		StringBuffer sb = new StringBuffer();
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(MyGsonUtil.FACTORY).create();
		String liststr = gson.toJson(listPlanS);
		Integer num = 0;
		num = Integer.valueOf(this.getRequest().getParameter("pageNumber"))+1;
		sb.append("{\"success\":true,\"pageNumber\":"+num+",\"count\":"+count+",\"result\":");
		sb.append(gson.toJson(liststr));
		sb.append("}");
		jsonString = sb.toString();
		
		return SUCCESS;
	}
	
	/**
	 * 投资人投资列表
	 * 
	 * @return
	 */

	public String my(){
		this.getSession().setAttribute("highlight", 2);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			//
			BpCustMember temp= bpCustMemberService.get(mem.getId());
			commoon(mem);
			List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
			this.getRequest().setAttribute("fileList", fileList);
			bpCustMember.setScore(temp.getScore());
			if (pager == null) {
				pager = new Pager();
				pager.setPageSize(10);
			}
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
				String page=this.getRequest().getParameter("page");
				String limit=this.getRequest().getParameter("limit");
				pager = new Pager();
				pager.setPageSize(Integer.valueOf(limit));
				pager.setPageNumber(Integer.valueOf(page));
			}
			//查询条件
			String state  = getRequest().getParameter("state");
			this.getRequest().setAttribute("state",state);
			if(null!=state&&!"".equals(state)){
				searchMap.put("state", state);
			}
			String bidtime = getRequest().getParameter("bidtime");
			if(null!=bidtime&&!"".equals(bidtime)){
				searchMap.put("bidtime", bidtime);
			}
			String bidtime2 = getRequest().getParameter("bidtime2");
			if(null!=bidtime2&&!"".equals(bidtime2)){
				searchMap.put("bidtime2", bidtime2);
			}
			String bidName = getRequest().getParameter("bidName");
			if(null!=bidName&&!"".equals(bidName)){
				searchMap.put("bidName", bidName);
			}
			String userMoney = getRequest().getParameter("userMoney");
			if(null!=userMoney&&!"".equals(userMoney)){
				searchMap.put("userMoney", userMoney);
			}
			
			this.getRequest().setAttribute("userType", "0");
			this.getRequest().setAttribute("userId",bpCustMember.getId().toString());
			planInfo(mem,pager);
			bpFund(mem,pager);
			//移动端代码
			String buystate = getRequest().getParameter("buystate");
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":").append("2").append(",\"result\":");
				JSONSerializer json = JsonUtil.getJSONSerializer("publishSingeTime","endIntentDate");
				json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"publishSingeTime","endIntentDate"});
				if(null==buystate||buystate.equals("1")){
					buff.append(json.serialize(this.getRequest().getAttribute("listPlanbacking")));
				}else if(buystate.equals("2")){
					buff.append(json.serialize(this.getRequest().getAttribute("listPlanSuccess")));
				}else if(buystate.equals("3")){
					buff.append(json.serialize(this.getRequest().getAttribute("listPlanback")));
				}else if(buystate.equals("4")){
					buff.append(json.serialize(this.getRequest().getAttribute("listPlanFila")));
				}
				buff.append("}");
				jsonString = buff.toString();
				return SUCCESS;
			}
			//取出登录类型，个人还是企业
			String loginType =(String) this.getSession().getAttribute("loginType");
			if(loginType.equals("enterprise")){
				if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":").append(pager.getTotalCount()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("bidtime","endIntentDate");
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"bidtime","endIntentDate"});
					buff.append(json.serialize(pager.getList()));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYFINANCEPURCHASEENTERPRISE).getTemplateFilePath());
			}else if (loginType.equals("person")){
				if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":").append(pager.getTotalCount()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("bidtime","endIntentDate");
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {"bidtime","endIntentDate"});
					buff.append(json.serialize(pager.getList()));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				}
				if(type!=null&&!"".equals(type)){
					this.getRequest().setAttribute("type", "1");
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYFINACEPURCHASE).getTemplateFilePath());
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			}
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}

	// 我的融资
	public String show() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MYFINACE).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	public String  showStatistics(){
		
		
		return SUCCESS;
	}
	
	
	// 我的收益
	public String myIncome() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_investPersonName_S_EQ", bpCustMember
					.getLoginname());
			//filter.addFilter("Q_payintentPeriod_N_GT", "0");
			filter.addFilter("Q_fundType_S_NEQ", "principalLending");
			if (notMoney != null && "1".equals(notMoney)) {
				searchMap.put("notMoney", "1");
			} else if (notMoney != null && "2".equals(notMoney)) {
				searchMap.put("notMoney", "2");
			}
			String Q_projectName_S_LK = getRequest().getParameter("Q_projectName_S_LK");
			if(null!=Q_projectName_S_LK&&!"".equals(Q_projectName_S_LK)){
				searchMap.put("Q_projectName_S_LK", Q_projectName_S_LK);
			}
			String Q_incomeMoney_BD_EQ = getRequest().getParameter("Q_incomeMoney_BD_EQ");
			if(null!=Q_incomeMoney_BD_EQ&&!"".equals(Q_incomeMoney_BD_EQ)){
				searchMap.put("Q_incomeMoney_BD_EQ", Q_incomeMoney_BD_EQ);
			}
			if (selectTime != null && !selectTime.equals("")) {
				searchMap.put("selectTime", selectTime);
			}
			if(null!=selectTime2&&!"".equals(selectTime2)){
				searchMap.put("selectTime2", selectTime2);
			}
			if (pager == null) {
				pager = new Pager();
				pager.setPageSize(10);
			}
			String isMobile=this.getRequest().getParameter("isMobile");
			if(null!=isMobile&&isMobile.endsWith("1")){
				String page=this.getRequest().getParameter("page");
				String limit=this.getRequest().getParameter("limit");
				pager = new Pager();
				pager.setPageSize(Integer.valueOf(limit));
				pager.setPageNumber(Integer.valueOf(page));
				
			}
			filter.getPagingBean().setStart(
					(pager.getPageNumber() - 1) * pager.getPageSize());
			filter.getPagingBean().setPageSize(pager.getPageSize());
			List<FundIncome> list = bpFundIntentService.getIncome(getRequest(),filter.getPagingBean(), bpCustMember.getId());
			
			pager.setTotalCount(filter.getPagingBean().getTotalItems());
			pager.setList(list);
			
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(pager.getTotalCount()).append(",\"result\":");
					JSONSerializer json = JsonUtil.getJSONSerializer("intentDate",
							"factDate", "interestStarTime", "interestEndTime");
					json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
							"intentDate", "factDate", "interestStarTime",
							"interestEndTime" });
					buff.append(json.serialize(pager.getList()));
					buff.append("}");
					jsonString = buff.toString();
					return SUCCESS;
				
			}
			
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MYFINACE).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}
	// 我的收益
	public String myPayMoney() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<FundPay> list1 = slFundIntentService.getPay(getRequest(),filter.getPagingBean(), Long.valueOf("323"),"");
		pager.setTotalCount(list1.size());
		pager.setList(list1);
		return "myPayMoney";
	}
	//融资费息
	public String myFundIntent(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			
			QueryFilter filter = new QueryFilter(getRequest());
			
			String notMoney = getRequest().getParameter("notMoney");
			if(null!=notMoney&&!"".equals(notMoney)){
				searchMap.put("notMoney", notMoney);
			}
			String projectName = getRequest().getParameter("projectName");
			if(null!=projectName&&!"".equals(projectName)){
				searchMap.put("projectName", projectName);
			}
			String selectTime = getRequest().getParameter("selectTime");
			if(null!=selectTime&&!"".equals(selectTime)){
				searchMap.put("selectTime", selectTime);
			}
			String selectTime2 = getRequest().getParameter("selectTime2");
			if(null!=selectTime2&&!"".equals(selectTime2)){
				searchMap.put("selectTime2", selectTime2);
			}
			String incomeMoney = getRequest().getParameter("incomeMoney");
			if(null!=incomeMoney&&!"".equals(incomeMoney)){
				searchMap.put("incomeMoney", incomeMoney);
			}
			if (pager == null) {
				pager = new Pager();
				pager.setPageSize(10);
			}
			BpCustRelation relation  = bpCustRelationService.getP2pCustById(mem.getId());
			if(null==relation){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MYFUND).getTemplateFilePath());
				return "finance";
			}
			filter.getPagingBean().setStart(
					(pager.getPageNumber() - 1) * pager.getPageSize());
			filter.getPagingBean().setPageSize(pager.getPageSize());
			List<SlFundIntent> list = new ArrayList<SlFundIntent>();
			//List<FundIncome> list = bpFundIntentService.getIncome(getRequest(),filter.getPagingBean(), bpCustMember.getId());
			if("p_loan".equals(relation.getOfflineCustType())){
				list = slFundIntentService.getListByCustId(relation.getOfflineCusId(), "person_customer", getRequest(), null);
			}else if("b_loan".equals(relation.getOfflineCustType())){
				
			}else{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MYFUND).getTemplateFilePath());
				return "finance";
			}
			//List<SlFundIntent> list = slFundIntentService.getAll(filter);
			pager.setTotalCount(10);
			pager.setList(list);
			
			
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MYFUND).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}
	
	// 还款页面
	public String repayment() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.REPAYMENTRECORDS).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	// 资金明细页面
	public String detail() {
		String isMobile=this.getRequest().getParameter("isMobile");
		if(null!=isMobile&&isMobile.endsWith("1")){
			String page=this.getRequest().getParameter("page");
			String limit=this.getRequest().getParameter("limit");
			pager = new Pager();
			pager.setPageSize(Integer.valueOf(limit));
			pager.setPageNumber(Integer.valueOf(page));
		}
		this.getSession().setAttribute("highlight", 6);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		BpCustMember bpCustMember1 = bpCustMemberService.get(mem.getId());
		String thirdFlagId = bpCustMember1.getThirdPayFlagId();
		mem.setThirdPayFlagId(thirdFlagId);
		
		from = this.getRequest().getParameter("from");
		to = this.getRequest().getParameter("to");
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		String startDate = getRequest().getParameter("startDate");
		if(null!=startDate&&!"".equals(startDate)){
			searchMap.put("startDate", startDate);
		}
		String endDate = getRequest().getParameter("endDate");
		if(null!=endDate&&!"".equals(endDate)){
			searchMap.put("endDate", endDate);
		}
		String isAccountSuccess = getRequest().getParameter("isAccountSuccess");
		if(null!=isAccountSuccess&&!"".equals(isAccountSuccess)){
			searchMap.put("isAccountSuccess", isAccountSuccess);
		}
		if (mem != null) {
			//判断是否是担保户
			QueryFilter filter11 = new QueryFilter();
			filter11.addFilter("Q_p2pCustId_L_EQ", mem.getId());
			List<BpCustRelation> relationlist = bpCustRelationService.getAll(filter11);
			if(relationlist.size()>0 && !"".equals(relationlist.get(0).getOfflineCustType())){
				if(relationlist.get(0).getOfflineCustType().equals("b_guarantee")){
					this.getRequest().setAttribute("isGarantee", "1");
				}
			}
			commoon(mem);
			BpCustMember oldMember= bpCustMemberService.get(mem.getId());
			bpCustMember.setScore(oldMember.getScore());
			ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(),ObSystemAccount.type0);
			if (account != null) {
				List<ObAccountDealInfo> list = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
				List<ObAccountDealInfo> listcount = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), null, null);
				pager.setTotalCount(listcount != null ? listcount.size() : 0);
				if(list!=null&&list.size()>0){
					for(ObAccountDealInfo temp :list){
						if(temp.getDealRecordStatus().compareTo(Short.valueOf("1"))==0){
							temp.setMsg("等待支付");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("2"))==0){
							temp.setMsg("交易成功");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("3"))==0){
							temp.setMsg("交易失败");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("4"))==0){
							temp.setMsg("取现审核");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("5"))==0){
							temp.setMsg("取现办理");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("6"))==0){
							temp.setMsg("异常数据");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("7"))==0&&temp.getThirdPayRecordNumber()==null){
							temp.setMsg("资金冻结");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("7"))==0&&temp.getThirdPayRecordNumber()!=null){
							temp.setMsg("取现审核");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("8"))==0){
							temp.setMsg("资金解冻");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("9"))==0){
							temp.setMsg("银行处理中");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("36"))==0){
							temp.setMsg("提现手续费收入");
						}else if(temp.getDealRecordStatus().compareTo(Short.valueOf("12"))==0){
							temp.setMsg("系统账户转账支出");
						}else {
							temp.setMsg("");
						}
					}
				}
				pager.setList(list);
				//查询交易类型
				List<ObSystemaccountSetting> obSystemaccountSetting = obSystemAccountService.findObSystemaccountSetting();
				this.getRequest().setAttribute("obSystemaccountSetting", obSystemaccountSetting);
				if(startDate!=null&&!startDate.equals("")){
					this.getRequest().setAttribute("startDate", startDate);
				}
				if(endDate!=null&&!endDate.equals("")){
					this.getRequest().setAttribute("endDate", endDate);
				}
				String transferType = getRequest().getParameter("transferType");
				if(transferType!=null&&!transferType.equals("")){
					this.getRequest().setAttribute("transferType1", transferType);
				}
				if(isAccountSuccess!=null&&!isAccountSuccess.equals("")){
					this.getRequest().setAttribute("isAccountSuccess1", isAccountSuccess);
				}
				
				//p2p手机端
				if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
					.append(pager.getTotalCount()).append(",\"result\":");
					    JSONSerializer json = JsonUtil.getJSONSerializer("createDate","transferDate");
						json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {});
						buff.append(json.serialize(pager.getList()));
						buff.append("}");
						setJsonString(buff.toString());
						return SUCCESS;
					
				}
			}
			if(null!=isMobile&&isMobile.endsWith("1")){
				StringBuffer buff = new StringBuffer("{\"success\":false,\"result\":1,\"msg\":\"数据校验失败,请检查是否认证通过或开通第三方支付\"}");
				jsonString = buff.toString();
				return SUCCESS;
			}	
			webMsgInstance("0", Constants.CODE_FAILED, "数据校验失败,请检查是否认证通过或开通第三方支付",  "", "", "", "", "");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.DETAILSFUNDS).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}
	// 用户绑定银行卡列表
	public String getBindBankList() {
		String isMobile=this.getRequest().getParameter("isMobile");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		
		state = "0";//状态为0和1,1表示投标之类的消息，0是其他的消息
		if (mem == null) {
			if(null!=isMobile&&isMobile.endsWith("1")){
				return SUCCESS;
			}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
			return "finance";
			}
		}else{
			mem = bpCustMemberService.get(mem.getId());
		}
		List<WebBankcard> list = webBankcardService.getByUserId(mem.getId());
		String bankCardNo = "";
		if(list.size()>0){
			bankCardNo = list.get(0).getCardNum();
		}
		 if(mem.getIsCheckCard() == null|| mem.getCardcode().equals("")) {
			webMsgInstance("0", Constants.CODE_FAILED, "请先进行实名认证",  "", "", "", "", "");
			webMsg.setUrl(this.getBasePath()+"/thirdreg.do");
			updateState = "notsuccess";
			if(isMobile!=null){
	                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	                StringBuffer sb = new StringBuffer();
	                sb.append("{\"success\":false,\"message\":");
	 				sb.append(gson.toJson("请先进行实名认证"));
	 				sb.append(",\"result\":2");
	 				sb.append("}");
					setJsonString(sb.toString());
					return SUCCESS;
			}else{this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());}
		} else  if(mem.getIsCheckPhone()== null|| mem.getIsCheckPhone().equals("")) {
			webMsgInstance("0", Constants.CODE_FAILED, "请先进行手机认证",  "", "", "", "", "");
			if(isMobile!=null){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"success\":false,\"message\":");
 				sb.append(gson.toJson("请先进行手机认证"));
 				sb.append(",\"result\":2");
 				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());
			}
		}else{
			//查询第三方绑卡信息
			/*CommonRequst common = new CommonRequst();
			CommonResponse commonResponse = new CommonResponse();
			String orderNum =ContextUtil.createRuestNumber();
			common.setRequsetNo(orderNum);//请求流水号
			common.setThirdPayConfigId(mem.getThirdPayFlagId());//第三方账号
			common.setThirdPayConfigId(mem.getThirdPayFlagId());//用户的第三方账号
			common.setBussinessType(ThirdPayConstants.BT_QUERYUSER);
			common.setTransferName(ThirdPayConstants.TN_QUERYUSER);
			common.setTransactionTime(new Date());
			commonResponse=ThirdPayInterfaceUtil.thirdCommon(common);
			if(commonResponse!=null&&commonResponse.getResponsecode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
				if(commonResponse.getBindBankStatus()!=null&&!commonResponse.getBindBankStatus().equals("")){
					List<WebBankcard> listAll=webBankcardService.getbanklist(mem.getId(),"","");
					if(listAll.size()==0){
						if(commonResponse.getBindBankStatus().equals("已认证")){
							WebBankcard webn=new WebBankcard();
							webn.setBankname(commonResponse.getBankName());
							webn.setCardNum(commonResponse.getBankCode());
							webn.setBindCardStatus(WebBankcard.BINDCARD_STATUS_SUCCESS);
							webn.setUserFlg(mem.getThirdPayFlagId());
							webn.setThirdConfig(mem.getThirdPayConfig());
							webn.setCustomerId(mem.getId());
							webn.setUsername(mem.getTruename());
							webn.setCustomerType(Short.valueOf("0"));//线上用户
							webn.setAccountname(mem.getTruename());
							webBankcardService.save(webn);
						}
					}else{
						if(commonResponse.getBindBankStatus().equals("未绑卡")){
							for(WebBankcard temp:listAll){
								temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_CANCEL);
								webBankcardService.save(temp);
							}
						}else if(commonResponse.getBindBankStatus().equals("已认证")){
							for(WebBankcard temp:listAll){
								if(temp.getRequestNo()==null && !temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
									webBankcardService.remove(temp);
								}else{
									//删除多余的一条绑卡记录
									ThirdPayRecord third = null;
									if(null != temp.getRequestNo() && !"".equals(temp.getRequestNo())){
										third = thirdPayRecordService.getByOrderNo(temp.getRequestNo());
									}
									if(third==null && !temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
										webBankcardService.remove(temp);
									}else if(third != null){
										if(third.getCode().equals(CommonResponse.RESPONSECODE_SUCCESS)){
											temp.setBankname(commonResponse.getBankName());
											temp.setCardNum(commonResponse.getBankCode());
											if(!temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_CANCELAPPLY)){
												
											}else{
												temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS);
											}
											webBankcardService.save(temp);
										}else{
											webBankcardService.remove(temp);
										}
									}
								}
							}
						}
					}
				}
			}*/
			bpCustMember = bpCustMemberService.get(mem.getId());
			commoon(bpCustMember);
			List<WebBankcard> listAll=webBankcardService.getbanklist(bpCustMember.getId(),"","");
			if(listAll!=null&&listAll.size()>0){
				for(WebBankcard temp:listAll){
					if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_FAILD)){
						temp.setBindCardStatusmsg("绑卡失败");
					}else if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
					/*	if(commonResponse.getBankName()!=null&&!commonResponse.getBankName().equals("")){
							temp.setBankname(commonResponse.getBankName());
						}*/
						temp.setBindCardStatusmsg("绑卡成功");
						if(isMobile!=null &&"2".equals(isMobile)){
							if(null!=listAll && listAll.size()>0){
								StringBuffer buff = new StringBuffer("[");
								for (WebBankcard a:listAll) {
									if(a.getBankname()==null){
										buff.append("{\"text\":\"").append(a.getCardNum()).append("\",\"value\":\"")
										.append(a.getCardId()).append("\"},");
									}else{
									
									buff.append("{\"text\":\"").append(a.getBankname()+"-"+a.getCardNum()).append("\",\"value\":\"")
									.append(a.getCardId()).append("\"},");
									}

								}
								if (listAll.size() > 0) {
									buff.deleteCharAt(buff.length() - 1);
								}
								buff.append("]");
								
								setJsonString(buff.toString());
							}
							return SUCCESS;
							}
					}else  if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_ACCEPT)){
						temp.setBindCardStatusmsg("绑卡受理");
					}else  if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_CANCEL)){
						temp.setBindCardStatusmsg("取消绑卡");
					}else  if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_CANCELAPPLY)){
						temp.setBindCardStatusmsg("取消绑卡申请中");
					}
				}
		  }
			listBankCard=listAll;
			if(isMobile!=null&&isMobile.endsWith("1")){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"success\":true,\"data\":");
 				sb.append(gson.toJson(listBankCard));
 				sb.append(",\"result\":1");
 				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
				}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.BIND_BANK_LIST).getTemplateFilePath());
		}
		return "finance";
	}
	// 账户提现页面1
	public String withdraw() {
		String projectType=AppUtil.getProjStr();
		this.getRequest().setAttribute("projectType",projectType);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem == null) {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
			return "freemarker";
		}
		state = "0";//状态为0和1,1表示投标之类的消息，0是其他的消息
		Object[] userCondition=bpCustMemberService.checkUserCondition(mem);
		//业务方法处理完毕跳转页面：默认是跳转到MessAge页面。
		String forwardPage=DynamicConfig.MESSAGE;
		if((Boolean) userCondition[0]){//检查用户基本资格
			ObSystemAccount  ob =obSystemAccountService.getByInvrstPersonIdAndType(mem.getId(), Short.valueOf("0"));
			if(ob!=null){
//				List<ObAccountDealInfo> list=obAccountDealInfoService.queyAccountInfoRecord(ob.getId(),ObAccountDealInfo.T_RECHARGE,ObAccountDealInfo.DEAL_STATUS_1,null, this.getRequest(), null, null);
				/*if(list!=null&&list.size()>0){//有未处理的充值记录
					 webMsgInstance("0", Constants.CODE_FAILED, "有等待支付的取现记录，请先前往资金明细页面处理完后在进行取现",  "", "", "", "", "");
				}else{*/
					bpCustMember = bpCustMemberService.get(mem.getId());
					bpCustMember = obSystemAccountService.getAccountSumMoney(bpCustMember);
					//通过获取用户第三方账户信息来更新银行列表数据
					Map<String, String> queryThirdPayCustomerInfo = bpCustMemberService.queryThirdPayCustomerInfo(bpCustMember);
					if (queryThirdPayCustomerInfo.containsKey("withdrawBalance")) {
						this.getRequest().setAttribute("withdrawBalance", queryThirdPayCustomerInfo.get("withdrawBalance"));
					}else {
						this.getRequest().setAttribute("withdrawBalance", "0.00");
					}
					if (bpCustMember.getIsCheckCard() != null && "1".equals(bpCustMember.getIsCheckCard())) {
						List<WebBankcard> listAll=webBankcardService.getBycustAndState(bpCustMember.getId(),WebBankcard.BINDCARD_STATUS_SUCCESS);
						if(listAll!=null&&listAll.size()>0){
							for(WebBankcard temp:listAll){
								if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_FAILD)){
									temp.setBindCardStatusmsg("绑卡失败");
								}else if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_SUCCESS)){
									temp.setBindCardStatusmsg("绑卡成功");
								}else  if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_ACCEPT)){
									temp.setBindCardStatusmsg("绑卡受理");
								}else  if(temp.getBindCardStatus().equals(WebBankcard.BINDCARD_STATUS_CANCEL)){
									temp.setBindCardStatusmsg("取消绑卡");
								}
							}
						}
						listBankCard=listAll;
						forwardPage=DynamicConfig.WITHDRAW;
					}else {
						webMsgInstance("0", Constants.CODE_FAILED, "请先进行实名认证",  "", "", "", "", "");
					}

				//}
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, "没有开通系统账户不能取现",  "", "", "", "", "");
			}
		}else{
			forwardPage=userCondition[2].toString();
			webMsgInstance("0", Constants.CODE_FAILED, userCondition[1].toString(),"", "", "", "", "");
		}
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(forwardPage).getTemplateFilePath());
		return "finance";
	}

	// 账户提现页面2
	public String withdrawNext() {

		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.WITHDRAWNEXT).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}

	// 账户提现页面2
	public String withdrawThird() {

		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.WITHDRAWTHIRD).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	// 账户充值页面1
	@SuppressWarnings("null")
	public String recharge() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String thirdPay = configMap.get("thirdPayConfig").toString();
		QueryFilter filter = new QueryFilter(this.getRequest());
		filter.addFilter("Q_thirdPayConfig_S_EQ", thirdPay); //0  标识 要显示 1标识不显示
		filter.getPagingBean().setPageSize(100);
		listBankCode = webBankCodeService.getAll(filter);
		Integer isSupportShortcut = 0;
		if (mem == null) {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
		} else{
			mem=bpCustMemberService.get(mem.getId());
			ObSystemAccount  ob =obSystemAccountService.getByInvrstPersonIdAndType(mem.getId(), Short.valueOf("0"));
			if(ob!=null){
						/* List<ObAccountDealInfo> list=obAccountDealInfoService.queyAccountInfoRecord(ob.getId(),ObAccountDealInfo.T_RECHARGE,ObAccountDealInfo.DEAL_STATUS_1,null, this.getRequest(), null, null);
						 if(list!=null&&list.size()>0){//有未处理的充值记录
							 webMsgInstance("0", Constants.CODE_FAILED, "有等待支付的充值记录，请先前往资金明细页面处理完后在进行充值",  "", "", "", "", "");
								this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
						 }else{*/
							 //判断是资金池还是托管
							/* Object[] thirdType=new Object[2];
							 thirdType = ThirdPayInterfaceUtil.checkThirdType(ThirdPayConstants.BT_RECHAGE);
							 if(thirdType!=null&&thirdType[0].equals("1")){
								 
							 }else{
								 if(mem.getThirdPayFlagId()==null){
										webMsgInstance("0", Constants.CODE_FAILED, "openThirdPay",  "", "", "", "", "");
										this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
								 }
							 }*/
							bpCustMember = bpCustMemberService.get(mem.getId());
							try {
								bpCustMember = obSystemAccountService.getAccountSumMoney(bpCustMember);
								if (bpCustMember.getCustomerType()!= null && bpCustMember.getCustomerType()==1) {
									isSupportShortcut = 0;
								}else {
									//获取客户绑定的银行卡并且判断用户快捷充值状态
									List<WebBankcard> bindBank = webBankcardService.getBycustAndState(bpCustMember.getId(), WebBankcard.BINDCARD_STATUS_SUCCESS);
									if (bindBank != null && bindBank.size() > 0) {
										WebBankcard webBankcard = bindBank.get(0);
										WebBankCodeFudian fudian = webBankCodeFudianService.getFudianRoleByBankCode(webBankcard.getBankId());
										if (fudian != null) {
											isSupportShortcut = fudian.getIsSupportShortcut();
											this.getRequest().setAttribute("fudian", fudian);
										}
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.RECHARGE).getTemplateFilePath());
//						 }	
			}else{
				webMsgInstance("0", Constants.CODE_FAILED, "没有开通系统账户不能充值",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
			}
			   
		}
		this.getRequest().setAttribute("isSupportShortcut", isSupportShortcut);
		return "finance";
	}

	
	// 账户充值页面2
	public String rechargeNext() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.RECHARGENEXT).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	// 账户充值页面1
	public String rechargeThird() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.RECHARGETHIRD).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	// 授权
	public String loanAuthorize() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.RECHARGETHIRD).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "loanAuthorize";
	}

	// 我的积分页面
	public String myIntegral() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MYINTEGRAL).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return SUCCESS;
	}

	// 我要融资页面
	public String to() {
		this.getSession().setAttribute("dh", "3");
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
		}
		myFinance();
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.FINACECENTER).getTemplateFilePath());

		return "finance";
	}
	
	// 个人借款
	public String finacecompanycenter() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.FINACECOMPANYCENTER).getTemplateFilePath());

		return "finance";
	}

	private void myFinance() {
		try{
		listProduct=new ArrayList<BpProductParameter>();
		List<BpProductParameter> curr;
		QueryFilter filter = new QueryFilter(this.getRequest());
		curr = bpProductParameterService.getAll(filter);
		for(BpProductParameter product:curr){
			QueryFilter filter1 = new QueryFilter(this.getRequest());
			filter1.addFilter("Q_productId_L_EQ", product.getId().toString());
			product.setAssure(ourProcreditAssuretenetService.getAll(filter1));
			product.setLoanMaterial (ourProcreditMaterialsEnterpriseService.getAll(filter1));
			listProduct.add(product);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 *  申请融资页面
	 * @return
	 */
	public String apply() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(AppUtil.getSysConfig().get("loanIsLogin")!=null&&mem == null){
			this.getRequest().getSession().setAttribute("retUrl", "/financePurchase/toFinancePurchase.do");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}else{
			listBpDicArea = bpDicAreaService.getAreaList("-1");
			productId = StringUtil.html2Text(this.getRequest().getParameter("productId"));
			productName = StringUtil.html2Text(this.getRequest().getParameter("productName")) ;
			loanType = this.getRequest().getParameter("type");
			MD5 md5 = new MD5();
			token = md5.md5(Random.createRandom(false, 10),"UTF-8");
			getSession().setAttribute("applyToken", token);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.APPLYFINANCE).getTemplateFilePath());
		}

		return "finance";
	}

	public String addApplyInfo() {
		try {
			this.getRequest().setCharacterEncoding("utf-8");
			String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
			val = val.toLowerCase();
			String projSys = AppUtil.getProjStr();
			checkCode = checkCode.toLowerCase();
			String verify_sms = getRequest().getParameter("verify_sms");
			String 	telCode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
				//存在手机验证执行
				if(null!=projSys&&"proj_duorongyi".equals(projSys)){
					if(null==verify_sms||null==telCode||!verify_sms.toLowerCase().equals(telCode.toLowerCase())){
						webMsgInstance("0", Constants.CODE_FAILED, "申请失败!",  "", "", "", "", "");
						return "message";
					}
				}
			if (val != null && val.equals(checkCode)) {
				BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
						MyUserSession.MEMBEER_SESSION);
				if (mem != null) {
					// 线上 客户
					isOnline = "1";
				} else {
					isOnline = "0";
				}
				if (businessName != null && !businessName.equals("")) {
					businessName = StringUtil.stringURLEncoderByUTF8(businessName);
				}
				String ret = "";
				//根据productId获取贷款产品名称
				BpProductParameter product = bpProductParameterService.get(Long.parseLong(productId));
				if(product!=null&&"".equals(product)){
					productName = product.getProductName();
				}
				if(ret!=null)
				{
					ret = ret.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
					if ( ret.equals(Constants.SUCCESSFLAG)) 
					{
						webMsgInstance("0", Constants.CODE_SUCCESS, "申请成功，请等待工作人员审核!",  "", "", "", "", "");
						
					} else
					{
						webMsgInstance("0", Constants.CODE_FAILED, "申请失败!",  "", "", "", "", "");
					}
				}else
				{
					webMsgInstance("0", Constants.CODE_FAILED, "申请失败!",  "", "", "", "", "");
				}
			} else {
				webMsgInstance("0", Constants.CODE_FAILED, "验证码错误!",  "", "", "", "", "");
				
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "message";
		}
	/**
	 * 产品审请提交方法
	 * @return
	 * @throws ParseException 
	 */
	public String saveApply() throws ParseException{
			String val = (String) getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY);
			val = val.toLowerCase();
			checkCode = checkCode==null?"":checkCode.toLowerCase();
			checkCodeB = checkCodeB==null?"":checkCodeB.toLowerCase();
			if (val != null && val.equals(checkCode)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Date dt=new Date();
				Date date=sdf.parse(sdf.format(dt));
				if(financeApply!=null){
					if(financeApply.getLoanId()==null){
						financeApply.setType("0");
						financeApply.setState((short)0);
						financeApply.setCreateTime(date);
						financeApplyService.save(financeApply);
						webMsgInstance("0", Constants.CODE_SUCCESS, "申请成功，请等待工作人员审核!",  "", "", "", "", "");
					}
				}
				
			}else if(val != null && val.equals(checkCodeB)){
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
					Date dt=new Date();
					Date date=sdf.parse(sdf.format(dt));
					if(financeApply!=null){
						if(financeApply.getLoanId()==null){
							financeApply.setCreateTime(date);
							financeApply.setType("1");
							financeApply.setState((short)0);
							financeApplyService.save(financeApply);
							webMsgInstance("0", Constants.CODE_SUCCESS, "申请成功，请等待工作人员审核!",  "", "", "", "", "");
						}
					}
					
				
			}else {
				if(financeApply!=null){
					if(financeApply.getType().equals("1")){//企业
						this.getRequest().getSession().setAttribute("retUrl", "/financePurchase/applyComFinancePurchase.do");
					}else if(financeApply.getType().equals("0")){//个人（稳安贷）
						this.getRequest().getSession().setAttribute("retUrl", "/financePurchase/applyPerFinancePurchase.do?personal_loan="+financeApply.getProductId());
					}
				}
				webMsgInstance("0", Constants.CODE_FAILED, "验证码错误!",  "", "", "", "", "");
				
			}
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());	
			return "message";
	}
	
	private void commoon(BpCustMember mem){
		/*bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
		try {
			bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
			//
		} catch (Exception e) {
			e.printStackTrace();
		}
		int percent = 0;
		if("1".equals(bpCustMember.getIsCheckEmail())&&bpCustMember.getIsCheckEmail()!=null){
			//判断邮箱是否验证
			percent += 10;
		}
		if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
			//判断手机是否验证
			percent += 20;
		}
		if("1".equals(bpCustMember.getIsCheckPhone())&&bpCustMember.getIsCheckPhone()!=null){
			//判断是否实名认证
			percent += 40;
		}
		if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
			//判断手机是否验证
			percent += 30;
		}
		//保存信誉等级
		this.getRequest().setAttribute("percent", percent);
		//保存客户的信誉信息
		this.getRequest().setAttribute("bpCustMember", bpCustMember);*/
		
			bidAuto = plBidAutoService.getPlBidAuto(mem.getId());
			bpCustMember = bpCustMemberService.get(mem.getId());
			try {
				BigDecimal[] ret =obSystemAccountService.sumTypeTotalMoney(bpCustMember.getId(),ObSystemAccount.type0.toString());
				if(ret!=null){
					bpCustMember.setTotalMoney(ret[1]);
					bpCustMember.setFreezeMoney(ret[2]);
					bpCustMember.setAvailableInvestMoney(ret[3]);
					bpCustMember.setTotalInvestMoney(ret[4]);
					bpCustMember.setAllInterest(ret[5]);
					bpCustMember.setPrincipalRepayment(ret[6]);
					bpCustMember.setTotalRecharge(ret[7]);
					bpCustMember.setTotalEnchashment(ret[8]);
					bpCustMember.setTotalLoanMoney(ret[9]);
					bpCustMember.setTotalPrincipalRepaymentMoney(ret[10]);
					bpCustMember.setTotalNotPrincipalRepaymentMoney(ret[11]);
				}
				//bpCustMember = obSystemAccountService.getAccountSumMoney(mem);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			int percent = 0;
			if(bpCustMember.getIsCheckEmail()!=null&&"1".equals(bpCustMember.getIsCheckEmail())){
				//判断邮箱是否验证
				percent += 30;
			}
			if(bpCustMember.getIsCheckPhone()!=null&&"1".equals(bpCustMember.getIsCheckPhone())){
				//判断手机是否验证
				percent += 30;
			}
			if(bpCustMember.getIsCheckCard()!=null&&"1".equals(bpCustMember.getIsCheckCard())){
				//判断是否实名认证
				percent += 40;
			}
			/*if(!"".equals(bpCustMember.getThirdPayFlagId())&&bpCustMember.getThirdPayFlagId()!=null){
				//判断手机是否验证
				percent += 30;
			}*/
	}
	// 理财专区页面
	public String money() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MONEYMANAGER).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
		return "finance";
	}
	/**	
	 * 2014-07-17
	 * 3种产品展示
	 * @return
	 */
	public String showProduct(){
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		//得到产品id
		id=Integer.parseInt(getRequest().getParameter("id")+"");
		//BpProductParameter p=bpProductParameterService.get(id);
		if(id==12){//薪资贷
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PRODUCT1).getTemplateFilePath());
		}else if(id==13){//经营贷
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PRODUCT2).getTemplateFilePath());
		}else if(id==14){//经营贷
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PRODUCT3).getTemplateFilePath());
		}else if(id==15){//园丁贷  
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PRODUCT4).getTemplateFilePath());
		}else if(id==16){//青春贷
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PRODUCT5).getTemplateFilePath());
		}
		return "finance";
	}
	
	
	/**2014-07-17
	 * 已注册个人审请
	 * @return
	 */
	public String creditInfo(){
		//保存请求借款类型（注册用户）
		//this.getSession().setAttribute("toLoanType", this.getRequest().getParameter("toLoanType"));
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		long pNo=Long.parseLong(this.getRequest().getParameter("id"));
		if(bpCustMember==null){
			if(pNo==12){
				//产品名称
				 productName=configMap.get("loanProduct1").toString();
				 //产品流程
			}else if(pNo==13){
				 productName=configMap.get("loanProduct2").toString();
			}else if(pNo==14){
				 productName=configMap.get("loanProduct3").toString();
			}else if(pNo==15){
				 productName=configMap.get("loanProduct7").toString();
			}else if(pNo==16){
				 productName=configMap.get("loanProduct8").toString();
			}
			this.getRequest().setAttribute("productName", productName);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.APPPERSON).getTemplateFilePath());
			  return "finance";
		}else if("".equals(bpCustMember.getThirdPayFlagId())||bpCustMember.getThirdPayFlagId() == null){
			this.getRequest().getSession().setAttribute("retUrl", "user/safeBpCustMember.do?safe=all");
			webMsgInstance("0", Constants.CODE_FAILED, "未开通资金托管账户",  "", "", "", "", "");
			//getSession().setAttribute("retUrl","/financePurchase/getLoanUserapplyUser.do");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MESSAGE).getTemplateFilePath());
			return "message"; 
		}else{
			BpFinanceApplyUser applyUser=new BpFinanceApplyUser();
			applyUser.setUserID(bpCustMember.getId());
			applyUser.setState("0");
			List<BpFinanceApplyUser> list1 =financeApplyUserService.getFinanceApply(applyUser);
			if(list1.size()>0){
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个未完成的产品、请在个人中心查看。。。",  "", "", "", "", "");
				//getSession().setAttribute("retUrl","/financePurchase/getLoanUserapplyUser.do");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "message";
			}
			applyUser.setState("1");
			applyUser.setState1("2");
			applyUser.setState2("4");
			//判断审请的产品是否通过审核
			List<BpFinanceApplyUser> list2 =financeApplyUserService.getFinanceApplyState(applyUser);
			if(list2.size()>0){
				this.getRequest().getSession().setAttribute("retUrl", "/user/loanmanagementBpCustMember.do?toAction=loan");
				webMsgInstance("0", Constants.CODE_FAILED, "您有一个正在审核的产品、请在个人中心查看。。。",  "", "", "", "", "");
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.MESSAGE).getTemplateFilePath());
				return "message";
			}else{
				//保存请求借款类型（注册用户）
				financeApply=new BpFinanceApply();
					String flownnode="";
					if(pNo==12){
						//产品名称
						 productName=configMap.get("loanProduct1").toString();
						 //产品流程
						 flownnode=configMap.get("loanProduct1flow").toString();
					}else if(pNo==13){
						 productName=configMap.get("loanProduct2").toString();
						 flownnode=configMap.get("loanProduct2flow").toString();
					}else if(pNo==14){
						 productName=configMap.get("loanProduct3").toString();
						 flownnode=configMap.get("loanProduct3flow").toString();
					}else if(pNo==15){
						 productName=configMap.get("loanProduct7").toString();
						 flownnode=configMap.get("loanProduct7flow").toString();
					}else if(pNo==16){
						 productName=configMap.get("loanProduct8").toString();
						 flownnode=configMap.get("loanProduct8flow").toString();
					}
					//产品id
					financeApply.setProductId(pNo);
					//产品名称
					financeApply.setProductName(productName);
					//产品流程
					financeApply.setFlowNode(flownnode);
					String[] str=flownnode.split("\\|");
					String finshState="";
					for(int i=0;i<str.length;i++){
						finshState="0"+"|"+finshState;
					}
					
					financeApply.setFinishState(finshState);
					//默认为第一个流程节点
					financeApply.setCurrnodeid(str[0]+"");
					QueryFilter filter=new QueryFilter(this.getRequest());
					filter.addFilter("Q_proTypeId_L_EQ", 1134+"");//借款用途列表
					filter.addFilter("Q_status_S_EQ", 0+"");
					listloanProject=dictionaryService.getAll(filter);	
				}
			}
			bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CREDIT).getTemplateFilePath());
		
		return "finance";
	}



	/**
	 * 非注册企业
	 * @return
	 */
	public String applyCom(){
		
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.APPCOMPANY).getTemplateFilePath());
		return "finance";
	}
	/**
	 * 非注册个人
	 * @return
	 */
	public String applyPer(){
		long pNo=Long.parseLong(this.getRequest().getParameter("id"));
		financeApply=new BpFinanceApply();
		String loanName="";
		if(pNo==12){
			//产品名称
			 productName=configMap.get("loanProduct1").toString();
			 //产品流程
			 loanName=configMap.get("loanProduct1flow").toString();
		}else if(pNo==13){
			 productName=configMap.get("loanProduct2").toString();
			 loanName=configMap.get("loanProduct2flow").toString();
		}else if(pNo==14){
			 productName=configMap.get("loanProduct3").toString();
			 loanName=configMap.get("loanProduct3flow").toString();
		}else if(pNo==15){
			 productName=configMap.get("loanProduct7").toString();
			 loanName=configMap.get("loanProduct7flow").toString();
		}else if(pNo==16){
			 productName=configMap.get("loanProduct8").toString();
			 loanName=configMap.get("loanProduct8flow").toString();
		}
		financeApply.setProductId(pNo);
		financeApply.setProductName(loanName);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.APPPERSON).getTemplateFilePath());
		return "finance";
	}
	public BpFinanceApply getFinanceApply() {
		return financeApply;
	}

	public void setFinanceApply(BpFinanceApply financeApply) {
		this.financeApply = financeApply;
	}
	
	

	/**
	 * 散标理财管理
	 */
	public String myManageMoney(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String planstate = this.getRequest().getParameter("planstate");
	
		List<FundPay> showManageMoneylist = new ArrayList<FundPay>();
		List<FundPay> showManageMoneylist2 = new ArrayList<FundPay>();
		
		try{
			if("7".equals(planstate)){
				showManageMoneylist=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayment", this.getRequest());
				showManageMoneylist2=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayment", null);
			}else if("10".equals(planstate)){
				showManageMoneylist=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtFinish", this.getRequest());
				showManageMoneylist2=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtFinish", null);
			}else if("-1".equals(planstate)){
				showManageMoneylist=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtBeforeFaild", this.getRequest());
				showManageMoneylist2=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtBeforeFaild", null);
			}else{
				showManageMoneylist=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtBefore", this.getRequest());
				showManageMoneylist2=bpFundIntentService.findInvestRepaymentList(mem.getLoginname(),mem.getId(),null,"InvestRepayMemtBefore", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();;
		Type type=new TypeToken<List<FundPay>>(){}.getType();
		StringBuffer buff = new StringBuffer("{\"success\":\"true\",\"totalCounts\":").append(showManageMoneylist2 !=null ? showManageMoneylist2.size() : 0)
		.append(",\"result\":");
		buff.append(gson.toJson(showManageMoneylist, type));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 还款列表
	 * @return
	 */
	public String returnmoney(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			QueryFilter filter = new QueryFilter(getRequest());
	/*		filter.addFilter("Q_investPersonName_S_EQ", bpCustMember
					.getLoginname());
			//filter.addFilter("Q_payintentPeriod_N_GT", "0");
			filter.addFilter("Q_fundType_S_NEQ", "principalLending");*/
			if (notMoney != null && "1".equals(notMoney)) {
				searchMap.put("notMoney", "1");
			} else if (notMoney != null && "2".equals(notMoney)) {
				searchMap.put("notMoney", "2");
			}
			String Q_projectName_S_LK = getRequest().getParameter("Q_projectName_S_LK");
			if(null!=Q_projectName_S_LK&&!"".equals(Q_projectName_S_LK)){
				searchMap.put("Q_projectName_S_LK", Q_projectName_S_LK);
			}
			String Q_incomeMoney_BD_EQ = getRequest().getParameter("Q_incomeMoney_BD_EQ");
			if(null!=Q_incomeMoney_BD_EQ&&!"".equals(Q_incomeMoney_BD_EQ)){
				searchMap.put("Q_incomeMoney_BD_EQ", Q_incomeMoney_BD_EQ);
			}
			if (selectTime != null && !selectTime.equals("")) {
				searchMap.put("selectTime", selectTime);
			}
			if(null!=selectTime2&&!"".equals(selectTime2)){
				searchMap.put("selectTime2", selectTime2);
			}
			if (pager == null) {
					pager = new Pager();
			}
			filter.getPagingBean().setStart((pager.getPageNumber() - 1) * pager.getPageSize());
			filter.getPagingBean().setPageSize((pager.getPageNumber() - 1) * pager.getPageSize()+pager.getPageSize());
			List<FundPay> list1 = slFundIntentService.getPay(getRequest(),filter.getPagingBean(),Long.valueOf(mem.getId()),"");
			
			
			pager.setTotalCount(filter.getPagingBean().getTotalItems());
			pager.setList(list1);
		
			
			
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.RETURNMONEY).getTemplateFilePath());
		} else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}
	
		return "freemarker";
	}
	
	
	/**
	 * 体验标管理
	 */
	public String myExperience(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String planstate = this.getRequest().getParameter("planstate");
		String start = this.getRequest().getParameter("start");
		String limit = this.getRequest().getParameter("limit");
		Integer counttotal = 0;
	
		List<ShowManageMoney> showManageMoneylist = new ArrayList<ShowManageMoney>();
		try{
			//通过投资人 拿到所有的投资记录\
			String sql="SELECT * FROM pl_managemoneyplan_buyinfo AS info WHERE info.state IN (1, 2, 10)"+
               " AND info.keystr = 'experience' AND info.persionType=0 AND info.investPersonId="+mem.getId()+
               " ORDER BY info.buyDatetime DESC LIMIT "+start+","+limit;
			String sql2="SELECT * FROM pl_managemoneyplan_buyinfo AS info WHERE info.state IN (1, 2, 10)"+
            " AND info.keystr = 'experience' AND info.persionType=0 AND info.investPersonId="+mem.getId()+
            " ORDER BY info.buyDatetime DESC";
			System.out.println(sql);
			List<PlManageMoneyPlanBuyinfo> InfoListMap = plManageMoneyPlanBuyinfoService.getManagePlanBuyInfo(sql);
			List<PlManageMoneyPlanBuyinfo> count = plManageMoneyPlanBuyinfoService.getManagePlanBuyInfo(sql2);;
			counttotal = count !=null ?(Integer) count.size():0;
			for (PlManageMoneyPlanBuyinfo info : InfoListMap) {
				Date date = null;
				ShowManageMoney show =new ShowManageMoney();
				//体验标Id
				show.setBidId(info.getPlManageMoneyPlan().getMmplanId());
				//前台显示的名称
				show.setProName(info.getMmName());
				//前台显示的投标时间
				show.setBidTime(info.getBuyDatetime());
				//前台显示的投资金额
				show.setPayMoney(info.getBuyMoney());
				//前台投资期限
				show.setLoanLife(info.getOrderlimit().toString());
				//前台年化利率
				show.setInterestRate(info.getPromisYearRate());
				//预期收益
				show.setPlCount(info.getPromisIncomeSum());
				//计划到账日
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(info.getBuyDatetime().getTime()+(info.getOrderlimit()*24*60*60*1000));
				sdf.format(calendar.getTime());
				show.setRepaymentDate(calendar.getTime());//info.getEndinInterestTime()
				if(info.getState()==1){
					show.setOrderNo("未起息");
				}else if(info.getState()==2){
					show.setOrderNo("已起息");
				}else if(info.getState()==10){
					show.setOrderNo("已派息");
				}
				showManageMoneylist.add(show);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();;
		Type type=new TypeToken<List<ShowManageMoney>>(){}.getType();
		StringBuffer buff = new StringBuffer("{\"success\":\"true\",\"totalCounts\":").append(counttotal.toString())
		.append(",\"result\":");
		buff.append(gson.toJson(showManageMoneylist, type));
		buff.append("}");
		jsonString=buff.toString();
		return SUCCESS;
	}

	public String getCheckCodeB() {
		return checkCodeB;
	}

	public void setCheckCodeB(String checkCodeB) {
		this.checkCodeB = checkCodeB;
	}

	//h5资金明细


	// 安卓资金明细页面
	public String moneyDetail() {
		MobileDataResultModel model = new MobileDataResultModel();
		String isApp = this.getRequest().getParameter("isApp");
		String isHapp = this.getRequest().getHeader("isApp");
		String page = this.getRequest().getParameter("page");
		String limit = this.getRequest().getParameter("limit");
		pager = new Pager();
		pager.setPageSize(Integer.valueOf(limit));
		pager.setPageNumber(Integer.valueOf(page));

		this.getSession().setAttribute("highlight", 6);
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
		BpCustMember bpCustMember1 = bpCustMemberService.get(mem.getId());
		String thirdFlagId = bpCustMember1.getThirdPayFlagId();
		mem.setThirdPayFlagId(thirdFlagId);

		from = this.getRequest().getParameter("from");
		to = this.getRequest().getParameter("to");
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		String startDate = getRequest().getParameter("startDate");
		if (null != startDate && !"".equals(startDate)) {
			searchMap.put("startDate", startDate);
		}
		String endDate = getRequest().getParameter("endDate");
		if (null != endDate && !"".equals(endDate)) {
			searchMap.put("endDate", endDate);
		}
		String isAccountSuccess = getRequest().getParameter("isAccountSuccess");
		if (null != isAccountSuccess && !"".equals(isAccountSuccess)) {
			searchMap.put("isAccountSuccess", isAccountSuccess);
		}

			commoon(mem);
			BpCustMember oldMember = bpCustMemberService.get(mem.getId());
			bpCustMember.setScore(oldMember.getScore());
			ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), ObSystemAccount.type0);
			if (account != null) {
				List<ObAccountDealInfo> list = obAccountDealInfoService.getaccountListQuery1(account.getId().toString(), this.getRequest(), (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
				List<ObAccountDealInfo> listcount = obAccountDealInfoService.getaccountListQuery1(account.getId().toString(), this.getRequest(), null, null);

				pager.setTotalCount(listcount != null ? listcount.size() : 0);
				if (list != null && list.size() > 0) {
					for (ObAccountDealInfo temp : list) {
						if (temp.getDealRecordStatus().compareTo(Short.valueOf("1")) == 0) {
							temp.setMsg("等待支付");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("2")) == 0) {
							temp.setMsg("交易成功");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("3")) == 0) {
							temp.setMsg("交易失败");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("4")) == 0) {
							temp.setMsg("取现审核");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("5")) == 0) {
							temp.setMsg("取现办理");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("6")) == 0) {
							temp.setMsg("异常数据");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() == null) {
							temp.setMsg("资金冻结");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() != null) {
							temp.setMsg("取现审核");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("8")) == 0) {
							temp.setMsg("资金解冻");
						} else if (temp.getDealRecordStatus().compareTo(Short.valueOf("9")) == 0) {
							temp.setMsg("银行处理中");
						} else {
							temp.setMsg("");
						}
					}
				}
				pager.setList(list);
				//查询交易类型
				List<ObSystemaccountSetting> obSystemaccountSetting = obSystemAccountService.findObSystemaccountSetting();
				this.getRequest().setAttribute("obSystemaccountSetting", obSystemaccountSetting);
				if (startDate != null && !startDate.equals("")) {
					this.getRequest().setAttribute("startDate", startDate);
				}
				if (endDate != null && !endDate.equals("")) {
					this.getRequest().setAttribute("endDate", endDate);
				}
				String transferType = getRequest().getParameter("transferType");
				//getRequest().getParameterValues("transferType")
				if (transferType != null && !transferType.equals("")) {
					this.getRequest().setAttribute("transferType1", transferType);
				}
				if (isAccountSuccess != null && !isAccountSuccess.equals("")) {
					this.getRequest().setAttribute("isAccountSuccess1", isAccountSuccess);
				}
				this.getRequest().setAttribute("List",pager.getList());
				this.getRequest().setAttribute("count",pager.getTotalCount());
				if("1".equals(isApp) || "1".equals(isHapp)){
					model.addDataContent("totalCounts",pager.getTotalCount());
					model.addDataContent("List",pager.getList());
					setJsonString(model.toJSON());
					return SUCCESS;
				}
				return "capital_details";
			}
			if("1".equals(isApp) || "1".equals(isHapp)){
				model.setCode(MobileErrorCode.SERVICE_ERROR);
				model.setMsg("数据校验失败,请检查是否认证通过或开通第三方支付");
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			this.getRequest().setAttribute("mes","数据校验失败,请检查是否认证通过或开通第三方支付");
			return "error_message";
		} else {
			if("1".equals(isApp) || "1".equals(isHapp)){
				model.setCode(MobileErrorCode.REG_LOG);
				model.setMsg("未查到用户信息，请先登录");
				setJsonString(model.toJSON());
				return SUCCESS;
			}
			//this.getRequest().setAttribute("mes","未查到用户信息，请先登录");
			return "login";
		}
	}

	//安卓资金详情条件筛选接口
    public String moneyDetailCondition(){

	    String isApp = this.getRequest().getParameter("isApp");
	    String type = this.getRequest().getParameter("type");
        MobileDataResultModel model = new MobileDataResultModel();
        String page = this.getRequest().getParameter("page");
        String limit = this.getRequest().getParameter("limit");
        pager = new Pager();
        pager.setPageSize(Integer.valueOf(limit));
        pager.setPageNumber(Integer.valueOf(page));

        this.getSession().setAttribute("highlight", 6);
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BpCustMember bpCustMember1 = bpCustMemberService.get(mem.getId());
        String thirdFlagId = bpCustMember1.getThirdPayFlagId();
        mem.setThirdPayFlagId(thirdFlagId);

        from = this.getRequest().getParameter("from");
        to = this.getRequest().getParameter("to");
        if (pager == null) {
            pager = new Pager();
            pager.setPageSize(10);
        }
        String startDate = getRequest().getParameter("startDate");
        if (null != startDate && !"".equals(startDate)) {
            searchMap.put("startDate", startDate);
        }
        String endDate = getRequest().getParameter("endDate");
        if (null != endDate && !"".equals(endDate)) {
            searchMap.put("endDate", endDate);
        }
        String isAccountSuccess = getRequest().getParameter("isAccountSuccess");
        if (null != isAccountSuccess && !"".equals(isAccountSuccess)) {
            searchMap.put("isAccountSuccess", isAccountSuccess);
        }
        if (mem != null) {
            commoon(mem);
            BpCustMember oldMember = bpCustMemberService.get(mem.getId());
            bpCustMember.setScore(oldMember.getScore());
            ObSystemAccount account = obSystemAccountService.getByInvrstPersonIdAndType(bpCustMember.getId(), ObSystemAccount.type0);
            if (account != null) {
                List<ObAccountDealInfo> list = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), (pager.getPageNumber() - 1) * pager.getPageSize(), pager.getPageSize());
                List<ObAccountDealInfo> listcount = obAccountDealInfoService.getaccountListQuery(account.getId().toString(), this.getRequest(), null, null);
                pager.setTotalCount(listcount != null ? listcount.size() : 0);
                if (list != null && list.size() > 0) {
                    for (ObAccountDealInfo temp : list) {
                        if (temp.getDealRecordStatus().compareTo(Short.valueOf("1")) == 0) {
                            temp.setMsg("等待支付");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("2")) == 0) {
                            temp.setMsg("交易成功");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("3")) == 0) {
                            temp.setMsg("交易失败");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("4")) == 0) {
                            temp.setMsg("取现审核");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("5")) == 0) {
                            temp.setMsg("取现办理");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("6")) == 0) {
                            temp.setMsg("异常数据");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() == null) {
                            temp.setMsg("资金冻结");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("7")) == 0 && temp.getThirdPayRecordNumber() != null) {
                            temp.setMsg("取现审核");
                        } else if (temp.getDealRecordStatus().compareTo(Short.valueOf("8")) == 0) {
                            temp.setMsg("资金解冻");
                        } else {
                            temp.setMsg("");
                        }
                    }
                }
                pager.setList(list);
				this.getRequest().setAttribute("count",pager.getTotalCount());
				this.getRequest().setAttribute("List",pager.getList());
                model.addDataContent("count",pager.getTotalCount());
                model.addDataContent("List",pager.getList());
                setJsonString(model.toJSON());
                return SUCCESS;
            }
            model.setCode(MobileErrorCode.SERVICE_ERROR);
            model.setMsg("数据校验失败,请检查是否认证通过或开通第三方支付");
            setJsonString(model.toJSON());
            return SUCCESS;
        } else {
            model.setCode(MobileErrorCode.REG_LOG);
            model.setMsg("未查到用户信息，请先登录");
            setJsonString(model.toJSON());
            return SUCCESS;
        }

    }

}