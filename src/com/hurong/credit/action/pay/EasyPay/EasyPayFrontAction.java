package com.hurong.credit.action.pay.EasyPay;
import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.service.thirdInterface.EasyPayService;
import com.hurong.credit.util.TemplateConfigUtil;



public class EasyPayFrontAction extends BaseAction {
	@Resource
	private EasyPayService easyPayService;
	
	/**
	 * 返回充值参数
	 * 
	 * @return
	 */
	public String recharge() {
		//验证充值返回值
		String ret[]=easyPayService.getEasypayReturnParameter(this.getRequest());
		//设置 返回提示消息
		webMsgInstance("0", ret[0], ret[1],  "", "", "", "", "");
		// 后台去掉
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.MESSAGE).getTemplateFilePath());
		return "freemarker";
	}
}