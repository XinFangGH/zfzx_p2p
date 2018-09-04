package com.hurong.credit.service.system.impl;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.hurong.credit.config.HtmlConfig;
import com.hurong.credit.service.system.ResultParamsService;
import com.hurong.credit.util.TemplateConfigUtil;

@WebService(targetNamespace="http://system.service.credit.hurong.com/", endpointInterface = "com.hurong.credit.service.system.ResultParamsService") 
public class ResultParamsServiceImpl  implements ResultParamsService {

	@Override
	public HtmlConfig resultHtmlConfig(String name) {
		return TemplateConfigUtil.getHtmlConfig(name);
	}

	@Override
	public HtmlConfig resultSingleHtmlConfig(String parms) {
		String[] ret= parms.split("_");
		return TemplateConfigUtil.getSingleHtmlConfig(ret[0],ret[1]);
	}

}
