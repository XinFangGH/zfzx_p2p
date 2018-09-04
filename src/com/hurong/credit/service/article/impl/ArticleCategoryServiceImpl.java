package com.hurong.credit.service.article.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.article.ArticleCategoryDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.service.article.ArticleCategoryService;


/**
 * 
 * @author 
 *
 */
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategory> implements ArticleCategoryService{
	@SuppressWarnings("unused")
	private ArticleCategoryDao dao;
	
	public ArticleCategoryServiceImpl(ArticleCategoryDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ArticleCategory> getByParentId(Long long1) {
		List<ArticleCategory> list1= this.dao.getRootArticleCategoryList();
		return dao.getByParentId(long1);
		
		
	}

	@Override
	public List<ArticleCategory> getRootArticleCategoryList() {
		List<ArticleCategory> rootArticleCategoryList = this.dao.getRootArticleCategoryList();
		/*if (rootArticleCategoryList != null) {
			for (ArticleCategory rootArticleCategory : rootArticleCategoryList) {
				Hibernate.initialize(rootArticleCategory);
			}
		}*/
		return rootArticleCategoryList;
	}
	
	
	
	
	public List<ArticleCategory> getChildrenArticleCategoryList(Article article) {
		ArticleCategory articlecategory = article.getArticleCategory();
		List<ArticleCategory> ArticleCategoryList = this.getChildrenArticleCategoryList(articlecategory);
		if (ArticleCategoryList == null) {
			ArticleCategoryList = new ArrayList<ArticleCategory>();
		}
		ArticleCategoryList.add(articlecategory);
		return ArticleCategoryList;
	}

	@Override
	public List<ArticleCategory> getArticleCategoryPathList(
			ArticleCategory articleCategory) {
		List<ArticleCategory> ArticleCategoryPathList = new ArrayList<ArticleCategory>();
		ArticleCategoryPathList.addAll(this.getParentArticleCategoryList(articleCategory));
		ArticleCategoryPathList.add(articleCategory);
		return ArticleCategoryPathList;
	}

	@Override
	public List<ArticleCategory> getArticleCategoryPathList(Article article) {
		ArticleCategory articlecategory = article.getArticleCategory();
		List<ArticleCategory> articlecategoryList = new ArrayList<ArticleCategory>();
		articlecategoryList.addAll(this.getParentArticleCategoryList(articlecategory));
		articlecategoryList.add(articlecategory);
		return articlecategoryList;
	}

	@Override
	public List<ArticleCategory> getChildrenArticleCategoryList(
			ArticleCategory articleCategory) {

		List<ArticleCategory> childrenArticleCategoryList = dao.getChildrenArticleCategoryList(articleCategory);
		if (childrenArticleCategoryList != null) {
			for (ArticleCategory childrenArticleCategory : childrenArticleCategoryList) {
				Hibernate.initialize(childrenArticleCategory);
			}
		}
		return childrenArticleCategoryList;
	
	}

	@Override
	public List<ArticleCategory> findByCateKey(String str) {
		return dao.findByCateKey(str);
	}

	@Override
	public List<ArticleCategory> findByParentId(Long id) {
		return  dao.findByParentId(id);
	}

	@Override
	public List<ArticleCategory> getParentArticleCategoryList(
			ArticleCategory articleCategory) {

		List<ArticleCategory> parentArticleCategoryList = dao.getParentArticleCategoryList(articleCategory);
		if (parentArticleCategoryList != null) {
			for (ArticleCategory parentArticleCategory : parentArticleCategoryList) {
				Hibernate.initialize(parentArticleCategory);
			}
		}
		return parentArticleCategoryList;
	
	}

	@Override
	public List<ArticleCategory> getParentArticleCategoryList(Article article) {

		ArticleCategory articlecategory = article.getArticleCategory();
		List<ArticleCategory> articlecategoryList = new ArrayList<ArticleCategory>();
		articlecategoryList.addAll(this.getParentArticleCategoryList(articlecategory));
		articlecategoryList.add(articlecategory);
		return articlecategoryList;
	
	}
	
	/*public List<ArticleCategory> getArticleCategoryTreeList() {
		List<ArticleCategory> allArticleCategoryList = this.getAll();
		return recursivArticleCategoryTreeList(allArticleCategoryList, null, null);
	}
	
	// 递归父类排序分类树
	private List<ArticleCategory> recursivArticleCategoryTreeList(List<ArticleCategory> allArticleCategoryList, ArticleCategory p, List<ArticleCategory> temp) {
		if (temp == null) {
			temp = new ArrayList<ArticleCategory>();
		}
		for (ArticleCategory ArticleCategory : allArticleCategoryList) {
			ArticleCategory parent = ArticleCategory.getParent();
			if ((p == null && parent == null) || (ArticleCategory != null && parent == p)) {
				temp.add(ArticleCategory);
				if (ArticleCategory.getChildren() != null && ArticleCategory.getChildren().size() > 0) {
					recursivArticleCategoryTreeList(allArticleCategoryList, ArticleCategory, temp);
				}
			}
		}
		return temp;
	}*/

}