package com.hurong.credit.action.pay.BaoFoo;
import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.service.thirdInterface.BaoFooService;
import com.hurong.credit.service.thirdInterface.EasyPayService;
import com.hurong.credit.util.TemplateConfigUtil;



public class BaoFooFrontAction extends BaseAction {
	@Resource
	private BaoFooService baoFooService;
	
	/**
	 * 返回充值参数
	 * 
	 * @return
	 */
	public String recharge() {
		//验证充值返回值
		String result =this.getRequest().getParameter("Result");
		System.out.println("result==="+result);
		String MemberID =this.getRequest().getParameter("MemberID");
		System.out.println("MemberID==="+MemberID);
		String TerminalID =this.getRequest().getParameter("TerminalID");
		System.out.println("TerminalID==="+TerminalID);
		String TransID =this.getRequest().getParameter("TransID");
		System.out.println("TransID==="+TransID);
		String ResultDesc =this.getRequest().getParameter("ResultDesc");
		System.out.println("ResultDesc==="+ResultDesc);
		String FactMoney =this.getRequest().getParameter("FactMoney");
		System.out.println("FactMoney==="+FactMoney);
		String AdditionalInfo =this.getRequest().getParameter("AdditionalInfo");
		System.out.println("AdditionalInfo==="+AdditionalInfo);
		String SuccTime =this.getRequest().getParameter("SuccTime");
		System.out.println("SuccTime==="+SuccTime);
		String Md5Sign =this.getRequest().getParameter("Md5Sign");
		System.out.println("Md5Sign==="+Md5Sign);
		String ret[]=new String[2];
		if(result.equals("1")){
			ret=baoFooService.getBaoFooReturnParameter(this.getRequest());
		}else{
			ret[0]=Constants.CODE_FAILED;
			ret[1]="支付失败";
		}
		//设置 返回提示消息
		webMsgInstance("0", ret[0], ret[1],  "", "", "", "", "");
		// 后台去掉
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
}