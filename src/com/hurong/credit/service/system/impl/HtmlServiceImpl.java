package com.hurong.credit.service.system.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.codehaus.groovy.reflection.handlegen;
import org.springframework.transaction.annotation.Transactional;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.HtmlConfig;
import com.hurong.credit.config.SystemConfig;
import com.hurong.credit.model.system.HtmlDataMapVO;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.service.system.IndexShowService;
import com.hurong.credit.util.MetaUtil;
import com.hurong.credit.util.SystemConfigUtil;
import com.hurong.credit.util.TemplateConfigUtil;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.ResourceBundleModel;
import freemarker.template.Configuration;
import freemarker.template.Template;

@WebService(endpointInterface = "com.hurong.credit.service.system.HtmlService")

public class HtmlServiceImpl  implements HtmlService  {

	//@Resource
	private FreemarkerManager freemarkerManager;
	@Resource
	private IndexShowService indexShowService;
	
	
	public void buildHtml(String templateFilePath, String htmlFilePath, Map<String, Object> data) {
		try {
			freemarkerManager=(FreemarkerManager)AppUtil.getBean("freemarkerManager");
			Configuration configuration = freemarkerManager.getConfiguration(AppUtil.servletContext);
			Template template = configuration.getTemplate(templateFilePath);
			File htmlFile = new File(AppUtil.servletContext.getRealPath(htmlFilePath));
			File htmlDirectory = htmlFile.getParentFile();
			if (!htmlDirectory.exists()) {
				htmlDirectory.mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取公共数据
	public Map<String, Object> getCommonData(Map<String, Object> commonData) {
		
		commonData.put("base", AppUtil.servletContext.getContextPath());
		commonData.put("systemConfig", SystemConfigUtil.getSystemConfig());
		
	    return commonData;
	}

	
	public void baseJavascriptBuildHtml() {
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.BASE_JAVASCRIPT);
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	  @Override
	public void indexBuildHtml2(Map<String, Object> data) {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.INDEX);
		data=getCommonData(data);
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void loginBuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.LOGIN);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	@Override
	public void regBuildHtml(HtmlDataMapVO dataVo) {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.REG);
		Map<String, Object> data;
		if(dataVo==null){
			data=new HashMap<String, Object>();
		}else{
		 data = dataVo.getData();
		}
		data=getCommonData(data);
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	 @Transactional
	public void articleContentBuildHtml(HtmlDataMapVO dataVo) {
		
		 Map<String, Object> data;
			if(dataVo==null){
				data=new HashMap<String, Object>();
			}else{
			 data = dataVo.getData();
			}
		data=getCommonData(data);
	    buildHtml(data.get("templateFilePath").toString(), data.get("currentHtmlFilePath").toString(), data);
	}
	 
	 public void signSchemeContentBuildHtml(HtmlDataMapVO dataVo) {
		 Map<String, Object> data;
			if(dataVo==null){
				data=new HashMap<String, Object>();
			}else{
			 data = dataVo.getData();
			}
		data=getCommonData(data);
	    buildHtml(data.get("templateFilePath").toString(), data.get("htmlFilePath").toString(), data);
		
	}
	
	public void errorPageBuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPageAccessDeniedBuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		data.put("errorContent", "您无此访问权限！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage500BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_500);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage404BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_404);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		data.put("errorContent", "您访问的页面不存在！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
	
	public void errorPage403BuildHtml() {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.ERROR_PAGE_403);
		Map<String, Object> data = new HashMap<String, Object>();
		data=getCommonData(data);
		data.put("errorContent", "系统出现异常，请与管理员联系！");
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}

	@Override
	public void indexBuildHtml(HtmlDataMapVO dataVo) {
		HtmlConfig htmlConfig = TemplateConfigUtil.getHtmlConfig(HtmlConfig.INDEX);
		
		Map<String, Object> data=dataVo.getData();
		data=getCommonData(data);
		String htmlFilePath = htmlConfig.getHtmlFilePath();
		String templateFilePath = htmlConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}

	/**
	 * 充值页面
	 */
	@Override
	public void rechargeBuildHtml(Map<String, Object> data) {
		// TODO Auto-generated method stub
		DynamicConfig dynamicConfig = TemplateConfigUtil.getDynamicConfig(DynamicConfig.RECHARGE); 
		data=getCommonData(data);
		String htmlFilePath = dynamicConfig.getHtmlFilePath();
		String templateFilePath = dynamicConfig.getTemplateFilePath();
		buildHtml(templateFilePath, htmlFilePath, data);
	}
}