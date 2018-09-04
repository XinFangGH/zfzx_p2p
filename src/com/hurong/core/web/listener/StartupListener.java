package com.hurong.core.web.listener;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.service.sms.util.AppSmsUtil;

public class StartupListener extends ContextLoaderListener {
	
	private static Log logger=LogFactory.getLog(StartupListener.class);
	
	public void contextInitialized(ServletContextEvent event) {

		super.contextInitialized(event);
		//初始化应用程序工具类
		AppUtil.init(event.getServletContext());
		
		//初始化发送短信工具类，主要用于读取配置文件中的配置参数信息
		AppSmsUtil.init();
		
	}
}