package com.hurong.credit.service.article;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.BaseService;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.article.Operate;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;


/**
 * 
 * @author 
 *
 */
public interface ArticleService extends BaseService<Article>{
	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager);
	
	public List<Article> getLimit(Integer firstNum ,Integer limit);

	public List<Article> getByCat(ArticleCategory articleCategory);
	
	public List<Article> getByIdCat(HttpServletRequest request,Integer start ,Integer limit);

	public List<Article> getArticleAll();
	
	public List<Article> getHongDaArticle(HttpServletRequest request,Integer start ,Integer limit);
	
	public BigInteger getCount(HttpServletRequest request);

	//查找并返回最新的媒体报道
	List<Article> getArticleLimit(Integer articleCategory_id);


	List<Article> getArticleLimit1(Integer articleCategory_id, Integer start, Integer limit);

    Operate getOperationMessage(String end);

	List<Article> getArticleById(Long articleId);
	//查询个人消息未读条数
	Integer getPersonMessage(Long userId,String status);

    Object getArticCount();

    List<Article> getByCat5(ArticleCategory articleCategory);
}


