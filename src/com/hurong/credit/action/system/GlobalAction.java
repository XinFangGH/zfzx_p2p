package com.hurong.credit.action.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.system.HtmlDataMapVO;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.system.HtmlService;
import com.hurong.credit.util.MyUserSession;
import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

public class GlobalAction extends BaseAction {
	/**
	 * 获取url 根目录
	 * @return
	 */
	public String glBasePath(){
		setJsonString("{\"basePath\":\""+this.getBasePath()+"\",\"tempBasePath\":\"theme/"+this.getSystemConfig().getTheme()+"/\"}");
		return SUCCESS;
	}
	
	public String sysConfig(){
	  Gson gson=new Gson();
	    String con= gson.toJson(this.getSystemConfig());
		setJsonString(con);
		return SUCCESS;
	}
}
