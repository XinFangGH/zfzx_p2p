package com.hurong.credit.action.thirdInterface;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.util.ContextUtil;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.web.action.BaseAction;


import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.model.thirdInterface.WebBankcard;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.sms.util.SmsSendUtil;
import com.hurong.credit.service.thirdInterface.WebBankcardService;
import com.hurong.credit.service.user.BpCustMemberService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.thirdPayInteface.CommonRequst;
import com.thirdPayInteface.ThirdPayInterfaceUtil;
/**
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
public class WebBankcardAction extends BaseAction{
	//得到config.properties读取的所有资源
	@SuppressWarnings("unchecked")
	private static Map configMap = AppUtil.getConfigMap();
	@Resource
	private WebBankcardService webBankcardService;
	private WebBankcard webBankcard;
	@Resource
	private BpCustMemberService bpCustMemberService;
	
	//初始化短信发送类
	SmsSendUtil smsSendUtil = new SmsSendUtil();
	
	private Long cardId;
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public WebBankcard getWebBankcard() {
		return webBankcard;
	}

	public void setWebBankcard(WebBankcard webBankcard) {
		this.webBankcard = webBankcard;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<WebBankcard> list= webBankcardService.getAll(filter);
		
		Type type=new TypeToken<List<WebBankcard>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				webBankcardService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		WebBankcard webBankcard=webBankcardService.get(cardId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(webBankcard));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(webBankcard.getCardId()==null){
			webBankcardService.save(webBankcard);
		}else{
			WebBankcard orgWebBankcard=webBankcardService.get(webBankcard.getCardId());
			try{
				BeanUtil.copyNotNullProperties(orgWebBankcard, webBankcard);
				webBankcardService.save(orgWebBankcard);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 绑定银行卡
	 */
	public String bindCard(){
		BpCustMember mem = (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		if(mem!=null){
			synchronized (webBankcard.getCardNum()){
				mem=bpCustMemberService.get(mem.getId());
				webBankcard.setCustomerId(mem.getId());
				webBankcard.setUserFlg(mem.getThirdPayFlagId());
				webBankcard.setThirdConfig(configMap.get("thirdPayConfig").toString());
				WebBankcard card=webBankcardService.checkCardExit(webBankcard,mem);
				if(card==null){
					webBankcard.setAccountname(mem.getTruename());
					webBankcard.setAccounttype("1");
					webBankcard.setUsername(mem.getTruename());
					webBankcard.setRequestNo(ContextUtil.createRuestNumber());
					webBankcard.setBindCardStatus(WebBankcard.BINDCARD_STATUS_FAILD);
					webBankcardService.save(webBankcard);
					
					/**~~~~~~~~~~~~~~~~~~~~~~~~绑定银行卡调第三方开始~~~~~~~~~~~~~~~~~~~~~~~~~**/
					CommonRequst  commonRequst = new CommonRequst();
					//商户订单号
					commonRequst.setRequsetNo(webBankcard.getRequestNo());
					//商户订单日期
					commonRequst.setTransactionTime(new Date());
					//平台用户号
					commonRequst.setThirdPayConfigId(webBankcard.getUserFlg());
					//银行卡号
					commonRequst.setBankCardNumber(webBankcard.getCardNum());
					//开户名称
					commonRequst.setTrueName(webBankcard.getUsername());
					//身份证号
					commonRequst.setCardNumber(mem.getCardcode());
					Object[] result = ThirdPayInterfaceUtil.bindBank(commonRequst);
					/**~~~~~~~~~~~~~~~~~~~~~~~~绑定银行卡调第三方结束~~~~~~~~~~~~~~~~~~~~~~~~~**/
					
					//银行卡绑定后对客户发送短信
					//svn：songwj
					if( mem.getTelphone()!=null &&!"".equals( mem.getTelphone())){
//						smsSendUtil.bankCardBindingSend(webBankcard.getCardNum(), mem.getTelphone());
					}else{
						System.out.println("登录人没有手机号！！！！");
					}
				}else{
					webMsgInstance("0", Constants.CODE_FAILED, "绑定了银行卡重复",  "", "", "", "", "");
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.MESSAGE).getTemplateFilePath());
				}
			}
		}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.LOGIN).getTemplateFilePath());
			
		}
		return "freemarker";
	}
}
