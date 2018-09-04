package com.hurong.credit.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hurong.core.util.AppUtil;
import com.hurong.core.util.UUIDGenerator;


public class HtmlConfig {
	
	public static final String REPLACE_UUID = "{uuid}";// 随机UUID字符串替换
	public static final String REPLACE_DATE_YY = "{date_yyyy}";// 当前日期字符串替换(年)
	public static final String REPLACE_DATE_MM = "{date_MM}";// 当前日期字符串替换(月)
	public static final String REPLACE_DATE_DD = "{date_dd}";// 当前日期字符串替换(日)
	public static final String REPLACE_DATE_HH = "{date_HH}";// 当前日期字符串替换(时)
	public static final String REPLACE_SINGLE = "{single}";// 单页面
	
	public static final String BASE_JAVASCRIPT = "baseJavascript";// baseJavascript
	public static final String INDEX = "index";// 首页
	public static final String LOGIN = "login";// 登录
	public static final String REG = "reg";// 注册
	public static final String ARTICLE_CONTENT = "articleContent";// 文章内容
	public static final String ARTICLE_SINGLE="articleSingle";//单页面
	public static final String BUSINESSDIRBID_CONTENT = "businessDirBidContent";// 企业直投标方案内容页
	public static final String BUSINESSORBID_CONTENT = "businessOrBidContent";// 企业债权标方案内容页
	public static final String PERSIONDIRBID_CONTENT = "persionDirBidContent";// 个人直投标方案内容页
	public static final String PERSIONORBID_CONTENT = "persionOrBidContent";// 个人直投标方案内容页
	public static final String MMPLAN_CONTENT = "mmplanContent";// 理财计划方案内容页
	public static final String ERROR_PAGE = "errorPage";// 错误页
	public static final String ERROR_PAGE_ACCESS_DENIED = "errorPageAccessDenied";// 权限错误页
	public static final String ERROR_PAGE_500 = "errorPage500";// 错误页500
	public static final String ERROR_PAGE_404 = "errorPage404";// 错误页404
	public static final String ERROR_PAGE_403 = "errorPage403";// 错误页403
	
	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径
	private String htmlFilePath;// 生成HTML静态文件存放路径
	private String single;//单页面名称

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {
		templateFilePath=templateFilePath.replace("${projstr}", AppUtil.getProjStr());
		this.templateFilePath = templateFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	
	// 获取生成HTML静态文件存放路径
	public String getHtmlFilePath() {
		htmlFilePath = htmlFilePath.replace(REPLACE_UUID, UUIDGenerator.getUUID());
		SimpleDateFormat yyDateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat ddDateFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hhDateFormat = new SimpleDateFormat("HH");
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_YY, yyDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_MM, mmDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_DD, ddDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_HH, hhDateFormat.format(new Date()));
		//单页面名称
		if(this.single!=null){
		htmlFilePath = htmlFilePath.replace(REPLACE_SINGLE, this.single);
		}
		return htmlFilePath;
	}

}