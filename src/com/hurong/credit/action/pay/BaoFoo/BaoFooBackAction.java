package com.hurong.credit.action.pay.BaoFoo;
import javax.annotation.Resource;

import com.hurong.core.Constants;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.service.thirdInterface.BaoFooService;
import com.hurong.credit.service.thirdInterface.EasyPayService;
import com.hurong.credit.util.TemplateConfigUtil;



public class BaoFooBackAction extends BaseAction {
	@Resource
	private BaoFooService baoFooService;
	
	/**
	 * 验证充值返回参数 
	 * @return
	 */
	public String recharge(){
		String ret[]=baoFooService.postEasyPayReturnParmater(this.getRequest());
		if(ret[0].equals(Constants.CODE_SUCCESS)){
			setJsonString("OK");
		}else{
			setJsonString("fail");
		}
		System.out.println(ret[1]);
		return SUCCESS;
	}
}
