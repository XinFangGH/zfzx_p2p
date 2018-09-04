package com.hurong.credit.service.credit.thirdInterface;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.credit.thirdInterface.WebBankCodeFudian;

/**
 * 
 * @author 
 *
 */
public interface WebBankCodeFudianService extends BaseService<WebBankCodeFudian>{

	/**
	 * 根据银行编号获取
	 * @param bankId
	 * @return
	 * 2017-12-12
	 * @tzw
	 */
	WebBankCodeFudian getFudianRoleByBankCode(String bankId);
	
}


