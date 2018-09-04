package com.hurong.credit.action.system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.creditFlow.log.Userloginlogs;
import com.hurong.credit.model.financePurchase.BpFinanceApplyUser;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;
import com.hurong.credit.model.system.product.BpProductParameter;
import com.hurong.credit.model.user.BpCustLoginlog;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.assuretenet.OurProcreditAssuretenetService;
import com.hurong.credit.service.creditFlow.log.UserloginlogsService;
import com.hurong.credit.service.creditFlow.materials.OurProcreditMaterialsEnterpriseService;
import com.hurong.credit.service.financePurchase.BpFinanceApplyUserService;
import com.hurong.credit.service.materials.WebFinanceApplyUploadsService;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.service.system.product.BpProductParameterService;
import com.hurong.credit.service.user.BpCustLoginlogService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.GetMACUtil;
import com.hurong.credit.util.MD5;
import com.hurong.credit.util.MD5_T;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.Random;
import com.kong.util.ValidateUtil;

@SuppressWarnings("serial")
public class BorrowerGatherAction extends BaseAction {
	@Resource
	private HtmlService htmlService;
	@Resource
	private BpCustMemberService bpCustMemberService;
	@Resource
	private BpCustLoginlogService bpCustLoginlogService;
	@Resource
	private UserloginlogsService userloginlogsService;// 日志接口
	@Resource
	private BpProductParameterService bpProductParameterService;
	@Resource
	private OurProcreditAssuretenetService ourProcreditAssuretenetService;
	@Resource
	private OurProcreditMaterialsEnterpriseService ourProcreditMaterialsEnterpriseService;
	@Resource	
	private BpFinanceApplyUserService financeApplyUserService;
	private String loginname;
	private String password;
	private String successHtml;// 返回登录信息
	private String checkName;
	private String custType;
	private String email;
	private int type;
	private String token;
	private BpFinanceApplyUser financeApplyUser;
	private List<BpFinanceApplyUser> listApplyUser;
	
	
	
	public List<BpFinanceApplyUser> getListApplyUser() {
		return listApplyUser;
	}

	public void setListApplyUser(List<BpFinanceApplyUser> listApplyUser) {
		this.listApplyUser = listApplyUser;
	}

	public BpFinanceApplyUser getFinanceApplyUser() {
		return financeApplyUser;
	}

	public void setFinanceApplyUser(BpFinanceApplyUser financeApplyUser) {
		this.financeApplyUser = financeApplyUser;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	private List<BpProductParameter> listProduct;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSuccessHtml() {
		return successHtml;
	}

	public void setSuccessHtml(String successHtml) {
		this.successHtml = successHtml;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public List<BpProductParameter> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<BpProductParameter> listProduct) {
		this.listProduct = listProduct;
	}
	public String loginhtml(){
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileLogin.ftl");
		return "freemarker";
	}
	public String reghtml(){
		MD5 md5 = new MD5();
		token = md5.md5(Random.createRandom(false, 10),"UTF-8");
		getSession().setAttribute("applyToken", token);
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileReg.ftl");
		return "freemarker";
	}
	public String login() throws Exception {

		// 首先验证登陆失败次数
		StringBuffer sb = new StringBuffer();
		ValidateUtil vu = new ValidateUtil();
		vu.addNotNull(loginname, "用户名不能为空").addNotNull(password, "密码不能为空");
		String result = vu.validate();
		if (!"1".equals(result)) {
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", result);
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		
		
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式1403253231605

		boolean bool = relieveUser(loginname.toLowerCase(), false);
		if (!bool) {
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", "登录失败连续超过5次，请稍后再试");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		
		
		try {
			MD5 md5 = new MD5();
			bpCustMember = bpCustMemberService.login(loginname.toLowerCase(),md5.md5(password, "utf-8"));
			successHtml = "";
			
			if (!"".equals(successHtml) || successHtml != null) {
				this.getRequest().getSession().removeAttribute("successHtml");
			}
			
			if (bpCustMember != null) {
				relieveUser(loginname.toLowerCase(), true);
				Integer isFor = bpCustMember.getIsForbidden();
				if (isFor != null && isFor == 1) {

					this.getRequest().setAttribute("loginname", loginname);
					this.getRequest().setAttribute("err", "该用户已被禁用,无法登录！");
					this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
					return "freemarker";
				} else {
					sb.append("{\"success\":true,\"data\":");
					sb.append(gson.toJson(bpCustMember));
					sb.append(",\"result\":1");

					BpCustLoginlog bpCustLoginlog = new BpCustLoginlog();
					bpCustLoginlog.setMemberId(bpCustMember.getId());
					bpCustLoginlog.setLoginTime(new Date());

					bpCustLoginlog.setLoginIp(GetMACUtil.getIpAddr(this.getRequest()));
					bpCustLoginlogService.save(bpCustLoginlog);
					this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);// 将用户信息保存在session中
					BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
					
					saveLog(bpCustMember);
					String retUrl = (String) getSession().getAttribute("retUrl");
					if (retUrl != null && !retUrl.equals("")) {
						sb.append(",\"retUrl\":\"" + this.getBasePath() + retUrl + "\"");
					} else {
						sb.append(",\"retUrl\":\"" + this.getBasePath() + "user/getBpCustMember.do?mobile=mobile\"");
					}
					this.getSession().removeAttribute("retUrl");// 清空缓存
					sb.append("}");
					if ("1".equals(this.checkName)) {
						this.getSession().setAttribute("exitCheck", "1");
					} else {
						this.getSession().setAttribute("exitCheck", "0");
					}

				}
			} else {

				this.getRequest().setAttribute("loginname", loginname);
				this.getRequest().setAttribute("err", "登陆失败！");
				this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
				return "freemarker";
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().setAttribute("loginname", loginname);
			this.getRequest().setAttribute("err", "登陆异常");
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/mobileLogin.ftl");
			return "freemarker";
		}
		setJsonString(sb.toString());
		Short node = bpCustMember.getBambooJoint();
		
		BpFinanceApplyUser applyUser = new BpFinanceApplyUser();
		applyUser.setUserID(bpCustMember.getId());
		listApplyUser = financeApplyUserService.getApplyUser(applyUser);//用户所有申请
		StringBuffer stateString = new StringBuffer();
		for(int i=0;i<listApplyUser.size();i++){
			stateString.append(listApplyUser.get(i).getState());
		}
		if(stateString.indexOf("0")!=-1||stateString.length()==0){//申请中
			node = node==null?(short)0:node;
			switch (node) {
			case 0:
				this.getResponse().sendRedirect("../mobilegather/productlist_BGAction.do");
				break;
			case 1:
				this.getResponse().sendRedirect("../mobileProduct/first_PDAction.do");
				break;
			case 2:
				this.getResponse().sendRedirect("../mobileProduct/saveApplyUser_PDAction.do");
				break;
			case 3:
				this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileyeepay.ftl");
				break;
			default:
				this.getResponse().sendRedirect(this.getBasePath() + "thirdreg.do?mobile=mobile");
				break;
			}
		}else if(stateString.indexOf("1")!=-1||stateString.indexOf("2")!=-1||stateString.indexOf("4")!=-1){//审批中的
			this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileyeepay.ftl");
			return "freemarker";
		}else if(stateString.indexOf("3")!=-1||stateString.indexOf("5")!=-1||stateString.indexOf("6")!=-1){//重新申请
			this.getResponse().sendRedirect("../mobilegather/productlist_BGAction.do");
			return "freemarker";
		}
		return "freemarker";
	}

	@SuppressWarnings({ "static-access", "unused" })
	public String reg() throws Exception {

		 Gson gson = new Gson();
		 MD5 md5 = new MD5();
		 ValidateUtil vu = new ValidateUtil();
		 vu.addNotNull(this.getRequest().getParameter("username"),"用户名不能为空")
		 .addRegValidate(this.getRequest().getParameter("username"), "用户名长度为6~16个字符", "^[\\d\\w]{6,16}$")
		 .addRegValidate(this.getRequest().getParameter("username"), "用户名不能使用纯数字", "^(?!(?:\\d+)$)[\\da-zA-Z]{6,16}$")
		 .addNotNull(this.getRequest().getParameter("pass"),"密码不能为空")
		 .addRegValidate(this.getRequest().getParameter("pass"), "密码长度为6~16个字符", "^[\\d\\w]{6,16}$")
		 .addRegValidate(this.getRequest().getParameter("pass"), "两次密码不一致", "^"+this.getRequest().getParameter("word")+"$")
		 .addNotNull(this.getRequest().getParameter("telphone_reg_random"), "电话号码不能为空")
		 .addRegValidate(this.getRequest().getParameter("telphone_reg_random"), "电话号码非法", "^1[\\d]{10}$")
		 ;
		 
		 String result = vu.validate();
		 if(!"1".equals(result)){
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson(result));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
		 }
		 
		 
		 BpCustMember bplogin = bpCustMemberService.isExist(this.getRequest().getParameter("username").toLowerCase().trim());
			if(bplogin!=null){
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("用户名已存在"));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			
			
			BpCustMember bpphone = bpCustMemberService.isExistPhone(this.getRequest().getParameter("telphone_reg_random"));
			if(bpphone!=null){//判断电话是否被注册
				StringBuffer sb = new StringBuffer();
				sb.append("{\"success\":true,\"errMsg\":");
				sb.append(gson.toJson("电话已被注册"));
				sb.append("}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
		 
		
		if(!"1".equals(result)){
			StringBuffer sb = new StringBuffer();
			sb.append("{\"success\":true,\"errMsg\":");
			sb.append(gson.toJson(result));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		String autoReg = this.getRequest().getParameter("autoReg");
		Map<String, Object> commonData = new HashMap<String, Object>();
		htmlService.getCommonData(commonData);
		
		String projStr = AppUtil.getProjStr();
		//将数据转成JSON格式
		 gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		StringBuffer sb = new StringBuffer();
			if(id == null){
				
				List<BpCustMember> list1 = bpCustMemberService.getAll();
				
					String phonecode = (String) this.getSession().getAttribute(MyUserSession.TELPHONE_REG_RANDOM_SESSION);
					if (phonecode!=null&&!phonecode.equals(this.getRequest().getParameter("checkCode"))){
						sb.append("{\"success\":true,\"errMsg\":");
						sb.append(gson.toJson("短信验证码错误!"));
						sb.append("}");
					}else{
						bpCustMember = new BpCustMember();
						if(null!=custType){
							bpCustMember.setCustType(Short.valueOf(custType));
						}
						
						bpCustMember.setRegistrationDate(new Date());
								MD5_T md1 = new MD5_T();
								//bpCustMember.setPlainpassword(md1.md5_3(this.getRequest().getParameter("username").toLowerCase().trim()));
								bpCustMember.setOPType("1");
								bpCustMember.setIsCheckPhone("1");
								bpCustMember.setEmail(email);
								bpCustMember.setType(type);
								bpCustMember.setBambooJoint((short)0);
								bpCustMember.setTelphone(this.getRequest().getParameter("telphone_reg_random"));
								bpCustMember.setLoginname(this.getRequest().getParameter("username").toLowerCase().trim());
								bpCustMember.setPassword(md5.md5(this.getRequest().getParameter("word"), "utf-8"));
								bpCustMember = bpCustMemberService.save(bpCustMember);
								//如果 为1 资金池模式 ThirdPayFlagId 设置为 客户id
								if(AppUtil.getSysConfig().get("thirdPayType").equals("1")){
									bpCustMember.setThirdPayFlagId(bpCustMember.getId().toString());
								}
								bpCustMember.setPlainpassword(bpCustMember.getId().toString());
								bpCustMember = bpCustMemberService.save(bpCustMember);
								
								bpCustMember = bpCustMemberService.login(this.getRequest().getParameter("username").toLowerCase(), md5.md5(this.getRequest().getParameter("word"), "utf-8"));
								
								this.getSession().setAttribute(MyUserSession.MEMBEER_SESSION, bpCustMember);//将注册成功的信息保存在session中
			
								wenanInsert(bpCustMember.getId());
								sb.append("{\"success\":false,\"errMsg\":");
								sb.append(gson.toJson("注册成功"));
								sb.append("}");
								setJsonString(sb.toString());
						}
					}
						
		setJsonString(sb.toString());
		return SUCCESS;
	}
	/**
	 * 产品展示
	 * @return
	 */
	public String productlist() {
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if (mem != null) {
			bpCustMember = bpCustMemberService.get(mem.getId());
		}
		try{
			listProduct=new ArrayList<BpProductParameter>();
			List<BpProductParameter> curr;
			QueryFilter filter = new QueryFilter(this.getRequest());
			curr = bpProductParameterService.getAll(filter);
			for(BpProductParameter product:curr){
				QueryFilter filter1 = new QueryFilter(this.getRequest());
				filter1.addFilter("Q_productId_L_EQ", product.getId().toString());
				product.setAssure(ourProcreditAssuretenetService.getAll(filter1));
				product.setLoanMaterial(ourProcreditMaterialsEnterpriseService.getAll(filter1));
				listProduct.add(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setSuccessResultValue("/WEB-INF/template/proj_wenandai/mobile/gather/mobileProduct_list.ftl");
		return "freemarker";
	}
	
	
	
	
	
	
	

	/**
	 * 登录日志
	 * 
	 * @param bpCustMember
	 */
	private void saveLog(BpCustMember mem) {
		try {
			String[] str = getConfig();
			Userloginlogs userLog = new Userloginlogs();
			userLog.setUserLoginName(mem.getLoginname());
			userLog.setLoginTime(new Date());
			userLog.setLoginIp(str[0]);
			userLog.setLoginMac(str[1]);
			userLog.setIsSuccess(true);
			userLog.setUserName(mem.getTruename());
			userLog.setType("p2p");
			userLog.setCompanyId(new Long(1));
			userloginlogsService.save(userLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 得到计算机的ip地址和mac地址
	public String[] getConfig() {
		String[] str = new String[2];
		try {
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			byte[] mac = ni.getHardwareAddress();
			String sIP = address.getHostAddress();
			String sMAC = "";
			Formatter formatter = new Formatter();
			for (int i = 0; i < mac.length; i++) {
				sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : "").toString();

			}
			str[0] = sIP;
			str[1] = sMAC;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 添加认证记录
	 * @param id 注册用户的id
	 */
	private void wenanInsert(Long id){
		
		WebFinanceApplyUploadsService webFinanceApplyUploadsService=(WebFinanceApplyUploadsService)AppUtil.getBean("webFinanceApplyUploadsService");
		//16中认证
		String[] str = new String[]{"IDCard","CreditRecord","Income","WebShop","House","Vehicle","Marriage","Education","Career","JobTitle","MobilePhone","MicroBlog","Residence","CompanyPlace","CompanyRevenue","Teacher"};
		/**
		添加认证记录
		 */
		for(int i=0;i<str.length;i++){
			WebFinanceApplyUploads webFinanceApplyUploads = new WebFinanceApplyUploads();
			webFinanceApplyUploads.setUserID(id);
			webFinanceApplyUploads.setFiles("");
			webFinanceApplyUploads.setStatus(0);
			webFinanceApplyUploads.setLastuploadtime(new Date());
			webFinanceApplyUploads.setMaterialstype(str[i]);
			webFinanceApplyUploadsService.save(webFinanceApplyUploads);
		}
	}
	/****
	 * 解除用户锁定 1、登陆成功 2、超过一定时间，如5分钟
	 * 
	 * @param loginname
	 *            登陆名称
	 * @param flag
	 *            登陆成功---清楚缓存
	 * *****/
	public boolean relieveUser(String loginname, boolean flag) {
		if (flag) {
			this.getSession().removeAttribute(MyUserSession.LOCK_NAME);
			this.getSession().removeAttribute(MyUserSession.lOCK_NUM);
			this.getSession().removeAttribute(MyUserSession.LOCK_TIME);
		} else {
			Object lockname = this.getSession().getAttribute(
					MyUserSession.LOCK_NAME);
			if (null != lockname) {
				if (lockname.equals(loginname)) {
					Integer lockNum = (Integer) this.getSession().getAttribute(
							MyUserSession.lOCK_NUM);
					if (lockNum == 5) {
						Long lastTime = (Long) this.getSession().getAttribute(
								MyUserSession.LOCK_TIME);
						Long now = new Date().getTime();
						Long disparity = now - lastTime;
						Long min = disparity / (5 * 60 * 1000);
						if (min.intValue() > 5) {// 大于五分钟清空缓存
							this.getSession().removeAttribute(
									MyUserSession.LOCK_NAME);
							this.getSession().removeAttribute(
									MyUserSession.lOCK_NUM);
							this.getSession().removeAttribute(
									MyUserSession.LOCK_TIME);
						} else {
							return false;
						}
					}

				}
			}
		}
		return true;
	}
	

}
