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

public class BuildHtmlAction extends BaseAction {
	private String buildContent;// 更新内容
	@Resource
	private HtmlService htmlService;
	
	@Resource
	private ArticleService articleService;
	private String username;
	private Article article;//新闻信息
	private BpCustMember bpCustMember;
	private List<Article> listArticle;//新闻列表
	public String getBuildContent() {
		return buildContent;
	}

	public List<Article> getListArticle() {
		return listArticle;
	}

	public void setListArticle(List<Article> listArticle) {
		this.listArticle = listArticle;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public BpCustMember getBpCustMember() {
		return bpCustMember;
	}

	public void setBpCustMember(BpCustMember bpCustMember) {
		this.bpCustMember = bpCustMember;
	}

	public void setBuildContent(String buildContent) {
		this.buildContent = buildContent;
	}

	@SuppressWarnings("deprecation")
	public String buildIndex() throws Exception {
		if (StringUtils.isEmpty(buildContent)) {
			buildContent = "index";
		}
		flushCache();
		if (buildContent.equalsIgnoreCase("index")) {
			Map<String, Object> data=new HashMap<String, Object>();
			listArticle= articleService.getAll();
			bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			data.put("listArticle", listArticle);
			data.put("bpCustMember", bpCustMember);
			htmlService.indexBuildHtml2(data);
			htmlService.loginBuildHtml();
			StringBuffer buff = new StringBuffer("{success:true,");
			buff.append(STATUS+":");
			buff.append("indexFinish,buildTotal:1");
			buff.append("}");
			jsonString=buff.toString();
			
		}
		return SUCCESS;
	}
	/**
	 * 账户充值
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String buildRecharge() throws Exception {
		if (StringUtils.isEmpty(buildContent)) {
			buildContent = "recharge";
		}
		flushCache();
		if (buildContent.equalsIgnoreCase("recharge")) {
			Map<String, Object> data=new HashMap<String, Object>();
			bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
			data.put("bpCustMember", bpCustMember);
			htmlService.rechargeBuildHtml(data);
			StringBuffer buff = new StringBuffer("{success:true,");
			buff.append(STATUS+":");
			buff.append("indexFinish,buildTotal:1");
			buff.append("}");
			jsonString=buff.toString();
			
		}
		return SUCCESS;
	}
	
	public String buildRegHtml(){
		HtmlDataMapVO dataVo=null;
		htmlService.regBuildHtml(dataVo);
		return SUCCESS;
	}
	
	public String buildLoginHtml(){
		
		htmlService.loginBuildHtml();
		return SUCCESS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
