package com.hurong.credit.service.system;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.hurong.credit.config.HtmlConfig;
@WebService
public interface ResultParamsService {
	@WebMethod
 public HtmlConfig resultHtmlConfig(@WebParam  String name);

	/**
	 *获取单页面 HtmlConfig 服务
	 * @param parms  模版名称 和 生成名称组成  如：single_about
	 * @return
	 */
	@WebMethod
 public HtmlConfig resultSingleHtmlConfig(@WebParam  String parms);
		
}
