package com.hurong.credit.dao.article;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.credit.model.article.Operate;
import sun.security.util.BigInt;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;


/**
 * 
 * @author 
 *
 */
public interface ArticleDao extends BaseDao<Article>{
	/**
	 * 根据最大返回数获取所有推荐文章(只包含isPublication=true的对象，不限分类)
	 * 
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 所有推荐文章集合
	 */
	public List<Article> getRecommendArticleList(int maxResults);

	/**
	 * 根据ArticleCategory对象和最大返回数获取此分类下的所有推荐文章(只包含isPublication=true的对象，包含子分类文章)
	 * 
	 * @param articleCategory
	 *            文章分类
	 *            
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 此分类下的所有推荐文章集合
	 */
	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults);
	
	/**
	 * 根据最大返回数获取所有热点文章(只包含isPublication=true的对象，不限分类)
	 * 
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 所有热点文章集合
	 */
	public List<Article> getHotArticleList(int maxResults);

	/**
	 * 根据ArticleCategory对象和最大返回数获取此分类下的所有热点文章(只包含isPublication=true的对象，包含子分类文章)
	 * 
	 * @param articleCategory
	 *            文章分类
	 * 
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 此分类下的所有热点文章集合
	 */
	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults);
	
	/**
	 * 根据最大返回数获取最新文章(只包含isPublication=true的对象，不限分类)
	 * 
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 最新文章集合
	 */
	public List<Article> getNewArticleList(int maxResults);

	/**
	 * 根据ArticleCategory对象和最大返回数获取此分类下的最新文章(只包含isPublication=true的对象，包含子分类文章)
	 * 
	 * @param articleCategory
	 *            文章分类
	 * 
	 * @param maxResults
	 *            最大返回数
	 * 
	 * @return 此分类下的最新文章集合
	 */
	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults);

	/**
	 * 根据ArticleCategory和Pager对象，获取此分类下的文章分页对象（只包含isPublication=true的对象，包含子分类文章）
	 * 
	 * @param articleCategory
	 *            文章分类
	 *            
	 * @param pager
	 *            分页对象
	 * 
	 * @return Pager
	 */

	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager);
	/**
	 * 分页返回列表
	 * @param firstNum
	 * @param limit
	 * @return
	 */
	public List<Article> getLimit(Integer firstNum, Integer limit);

	public List<Article> getByCat(ArticleCategory articleCategory);
	
	public List<Article> getByIdCat(HttpServletRequest request,Integer start ,Integer limit);
	
	public List<Article> getArticleAll();
	
	public List<Article> getHongDaArticle(HttpServletRequest request,Integer start ,Integer limit);
	public List<Article> getArticleOrderNew();
	public BigInteger getCount(HttpServletRequest request);

    List<Article> getArticleLimit(Integer articleCategory_id);

    List<Article> getArticleLimit1(Integer articleCategory_id, Integer start, Integer limit);

    Operate getSumMoneyCount(String end);


    Integer getBalanceMoney(String end);

    Integer getBalanceCount(String end);

    Integer getSumPayPeople(String end);

    Integer getPayPeople(String end);

    Integer getSumIncomePeople(String end);

    Integer getIncomePeople(String end);

    BigDecimal getTenIncomeMoney(String end);

    BigDecimal getMaxIncomeMoney(String end);

    List<Article> getArticleById(Long articleId);

    Integer getMessageAcount(Long userId, String status);

    Object getArticCount();

    List<Article> getByCat5(ArticleCategory articleCategory);
}