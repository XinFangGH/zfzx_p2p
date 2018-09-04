package com.hurong.credit.action.webPhone;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.StringUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObSystemAccount;
import com.hurong.credit.model.creditFlow.finance.FundPay;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidInfo;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.customer.MaliciousRecord;
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
import com.hurong.credit.model.webFeedBack.BankCard;
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
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebFinancePurchaseAction extends BaseAction{
    protected Log logger= LogFactory.getLog(WebFinancePurchaseAction.class);
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



    //安卓散标理财管理接口
    public String mobileMyManageMoney(){
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);

        if(mem != null) {
            String planstate = this.getRequest().getParameter("planstate");

            List<FundPay> showManageMoneylist = new ArrayList<FundPay>();
            List<FundPay> showManageMoneylist2 = new ArrayList<FundPay>();

//            还款中：planstate=7    投标中：planstate=1    已结清：planstate=10
//            投标失败：planstate=-1

            try {
                if ("7".equals(planstate)) {
                    showManageMoneylist = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayment", this.getRequest());
                    showManageMoneylist2 = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayment", null);
                } else if ("10".equals(planstate)) {
                    showManageMoneylist = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtFinish", this.getRequest());
                    showManageMoneylist2 = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtFinish", null);

                } else if ("-1".equals(planstate)) {
                    showManageMoneylist = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBeforeFaild", this.getRequest());
                    showManageMoneylist2 = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBeforeFaild", null);
                } else {
                    showManageMoneylist = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBefore", this.getRequest());
                    showManageMoneylist2 = bpFundIntentService.findInvestRepayment(mem.getLoginname(), mem.getId(), null, "InvestRepayMemtBefore", null);
                    if (showManageMoneylist.size() > 0) {
                        for (FundPay fp : showManageMoneylist) {
                            bpFundIntentService.setMoneyAndRemain(fp);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (FundPay fp : showManageMoneylist){
                if(fp.getShowRate() ==null){
                    fp.setShowRate(new BigDecimal("0"));
                }
            }
		    model.addDataContent("totalCounts",showManageMoneylist2 !=null ? showManageMoneylist2.size() : 0);
            model.addDataContent("fundPayList", showManageMoneylist);
            this.getRequest().setAttribute("totalCounts",showManageMoneylist2 !=null? showManageMoneylist2.size() : 0);
            this.getRequest().setAttribute("fundPayList",showManageMoneylist);
        }else{
            model.setCode(MobileErrorCode.FAILED);
            model.setMsg("请先登录");
        }

        setJsonString( model.toJSON());
      /*  String isApp = this.getRequest().getParameter("isApp");
        if("1".equals(isApp)){
            return SUCCESS;
        }else if(mem != null){
            return "scatterdLend";
        }else{
            return "reg_log";
        }*/
        boolean isApp = isApp();
        if(isApp){
            return SUCCESS;
        }else if(mem != null){
            return "scatterdLend";
        }else{
            return "reg_log";
        }
    }

    //合同下载
    public void downLoad() throws UnsupportedEncodingException {
        String contractUrl = this.getRequest().getParameter("contractUrl");
        System.out.println("合同" + contractUrl);
//		String contractUrl =  new String(this.getRequest().getParameter("contractUrl").getBytes("iso-8859-1"), "utf-8");
//		DownloadURLFile.getInstance().downLoad(AppUtil.getAppAbsolutePath()+contractUrl, this.getResponse());

        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if (contractUrl != null && !"".equals(contractUrl)) {
            if (contractUrl.indexOf("\\") != -1) {
                contractUrl = contractUrl.replace("\\", "/");
            }

            boolean b = false;
            //根据合同下载地址传送的客户类型来选择是否查询投资记录
            String customerType = this.getRequest().getParameter("customerType");
            if (customerType != null && !"".equals(customerType) && customerType.equals("loan")) {
                b = true;
            } else {
                String curl = "";
                //取出当前用户所有合同
                List<InvestPersonInfo> invest = investPersonInfoService.queryName(mem.getLoginname(), "All", null);
                if (invest != null && invest.size() > 0) {
                    for (InvestPersonInfo bid : invest) {//遍历
                        if (bid.getContractUrls() == null || "".equals(bid.getContractUrls())) {//为空跳出此次循环
                            continue;
                        }
                        if (contractUrl.equals(bid.getContractUrls())) {//判断用户确实有此份合同跳出循环
                            b = true;
                            break;
                        }
                    }
                }
            }

            //插入下载合同Ip信息
            if (!b) {//当前用户没有合同的下载权
                MaliciousRecord mr = new MaliciousRecord();
                try {
                    String ip = this.getRequest().getHeader("x-forwarded-for");
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = this.getRequest().getHeader("Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
                    }
                    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                        ip = this.getRequest().getRemoteAddr();
                    }
                    String sMAC = getMACAddress(ip);
                    mr.setMrip(ip);
                    mr.setMrmac(sMAC);
                    mr.setMrhttpheader("header");
                    maliciousRecordService.save(mr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//				this.setSuccessResultValue("/html/error_page_404.html");
//				return "freemarker";
            }

            String filePath = AppUtil.getConfigMap().get("fileURL") + contractUrl;//拼接文件在远程服务器上的路径
            HttpServletResponse response = this.getResponse();
            try {
//				System.out.println("*******下载合同路径*****filePath="+filePath);
                String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);//获取文件名称
                filePath = filePath.replace(fileName, new String(fileName.getBytes("UTF-8"), "GBK"));
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");//把文件名按UTF-8取出并按ISO8859-1编码，
                //最多支持17个中文，因为header有150个字节限制。
                response.setContentType("application/octet-stream");//告诉浏览器输出内容为流
                response.reset();//清除缓冲中的数据
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);//Content-Disposition中指定的类型是文件的扩展名，
                //并且弹出的下载对话框中的文件类型图片是按照文件的扩展名显示的，点保存后，
                //HTTP远程下载songwj
                HttpURLConnection httpConn = null;
                remoteFile = new URL(filePath);//建立远程连接
                httpConn = (HttpURLConnection) remoteFile.openConnection();  //打开连接

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            webMsgInstance("0", Constants.CODE_FAILED, "该用户没有生成合同", "", "", "", "", "");
            this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MYFINACE).getTemplateFilePath());
        }
    }

    //主要用于流的转换
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }


    //安卓散标管理之体验标接口
    public String myExperience(){

        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        String planstate = this.getRequest().getParameter("planstate");
        //分页代码
       /* String start = this.getRequest().getParameter("start");
        String limit = this.getRequest().getParameter("limit");*/
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

        model.addDataContent("showManageMoneylist",showManageMoneylist);
        setJsonString(model.toJSON());
        return SUCCESS;
    }
    public String rechargeMoney(){
        String url = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort();
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        mem = bpCustMemberService.get(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());
        List<BankCard> listAll = webBankcardService.getBankCardList(bpCustMember.getId());
                String logoUrl="";
                if(listAll!=null&&!("").equals(listAll)){
                    BankCard temp = listAll.get(0);
                    String cardNum = temp.getCardNum();
                    int length = cardNum.length();
                    cardNum=cardNum.substring(length-4);
                    temp.setCardNum(cardNum);
                    temp.setBindCardStatus(null);
                    logoUrl=url+"/"+temp.getBankLogo();
                    temp.setBankLogo(logoUrl);
                    this.getRequest().setAttribute("bankCard",temp);
                    return  "rechargeMoney";
                }else{
                    System.out.println("请先绑定银行卡");
                    return "null";
                }

    }

    public String putForWard(){
        BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        mem = bpCustMemberService.get(mem.getId());
        bpCustMember = bpCustMemberService.get(mem.getId());
        List<BankCard> listAll = webBankcardService.getBankCardList(bpCustMember.getId());
        BankCard temp = listAll.get(0);
        String cardNum = temp.getCardNum();
        int length = cardNum.length();
        cardNum=cardNum.substring(length-4);
        temp.setCardNum(cardNum);
        temp.setBindCardStatus(null);
        this.getRequest().setAttribute("bankCard",temp);
        //可以提现金额
        String trueMoney = this.getRequest().getParameter("trueMoney");
        this.getRequest().setAttribute("trueMoney",trueMoney);
        return "putForWard";
    }
    public String recharge(){
        String trueMoney= this.getRequest().getParameter("trueMoney");
        String availableInvestMoney = this.getRequest().getParameter("availableInvestMoney");
        this.getRequest().setAttribute("availableInvestMoney",availableInvestMoney);
        this.getRequest().setAttribute("trueMoney",trueMoney);
        return "recharge";
    }
//点击解绑银行卡返回是否可以解绑
    public String relieveCard(){
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        BigDecimal[] ret = obSystemAccountService.sumTypeTotalMoney(cust.getId(), ObSystemAccount.type0.toString());
        String trueMoney;
        //通过获取用户第三方账户信息来更新银行列表数据
        Map<String, String> queryThirdPayCustomerInfo = bpCustMemberService.queryThirdPayCustomerInfo(cust);
        if (queryThirdPayCustomerInfo.containsKey("withdrawBalance")) {
            trueMoney = queryThirdPayCustomerInfo.get("withdrawBalance");
        } else {
            trueMoney = "0.00";
        }
        //投标冻结金额
        BigDecimal freeMoney = bpCustMemberService.getMoneyBidFrozen(cust.getId());//冻结资金总额
        if (freeMoney == null) {
            freeMoney = new BigDecimal(0);
            if (ret != null) {
                BigDecimal money = ret[3];//账户可用金额
                //账户余额为0 直接解绑卡
                if(money.compareTo(new BigDecimal(0))==0){
                    model.addDataContent("code","0");//账户余额为0 直接解绑卡
                }
                //可用余额大于0，可提现金额等于0
                if(money.compareTo(new BigDecimal(0))==1&&"0.00".equals(trueMoney)){
                    model.addDataContent("code","1"); //可用余额大于0，可提现金额等于0
                }

                //可提现金额大于0元小于1元
                if(0<Double.valueOf(trueMoney)&&1>=Double.valueOf(trueMoney)){
                    model.addDataContent("code","2");//可提现金额大于0元小于1元
                }
                ;
                //可提现金额大于1，可用余额大于可提现金额
                if(Double.valueOf(trueMoney)>1&&money.compareTo(new BigDecimal(trueMoney))==1){
                    model.addDataContent("code","3");//可提现金额大于1，可用余额大于可提现金额
                }
                //可提现金额大于1，可用余额等于可提现金额
                if(Double.valueOf(trueMoney)>1&&money.compareTo(new BigDecimal(trueMoney))==0){
                    model.addDataContent("code","4"); //可提现金额大于1，可用余额等于可提现金额
                }
            }
        }else{//冻结金额大于0
            model.addDataContent("code","5");

        }
        model.setCode(MobileErrorCode.SUCCESS);
        setJsonString(model.toJSON());
        return SUCCESS;
    }
    public String  inspect(){
        MobileDataResultModel model = new MobileDataResultModel();
        BpCustMember cust = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
        if(cust==null){
            model.setCode(9999);
            model.setMsg("用户未登录，请先登录");
        }else{
            BpCustMember bpCustMember = bpCustMemberService.get(cust.getId());
            List<BankCard> listAll = webBankcardService.getBankCardList(bpCustMember.getId());
            String isCheckCard = bpCustMember.getIsCheckCard();
            String grade = bpCustMember.getGrade();
            int card = listAll.size();
//            if("".equals(grade)||grade==null){
//                model.setCode(MobileErrorCode.FAILED);
//                model.setMsg("请先进行风险测评");
//            }else
            if(!"1".equals(isCheckCard)){
                model.setCode(6666);
                model.setMsg("请先进行实名");
            }else if(card==0){
                model.setCode(8888);
                model.setMsg("请先进行绑卡");
            }
        }


        setJsonString(model.toJSON());
        return SUCCESS;
    }
    public String bankCard1(){
        return "bankCard1";
    }

}
