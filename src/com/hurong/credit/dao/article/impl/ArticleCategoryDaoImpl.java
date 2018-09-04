package com.hurong.credit.dao.article.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.article.ArticleCategoryDao;
import com.hurong.credit.model.article.ArticleCategory;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory> implements ArticleCategoryDao{

	public ArticleCategoryDaoImpl() {
		super(ArticleCategory.class);
	}

	@Override
	public List<ArticleCategory> getByParentId(Long long1) {
		String hql=" from ArticleCategory ac where ac.parentId = ?";
		return findByHql(hql, new Object[]{long1});
	}

	@Override
	public List<ArticleCategory> getRootArticleCategoryList() {
		String hql = "from ArticleCategory articleCategory where articleCategory.parentId>=0 order by articleCategory.orderList asc";
		return findByHql(hql);
	}
	
	@Override
	public List<ArticleCategory> getParentArticleCategoryList(ArticleCategory articleCategory) {
		//String hql = "from ArticleCategory articleCategory where articleCategory != ? and articleCategory.id in(?) order by articleCategory.orderList asc";
		StringBuffer buff=new StringBuffer("from ArticleCategory articleCategory where articleCategory != ? and articleCategory.id in(");
		
		String[] ids = articleCategory.getPath().split(articleCategory.PATH_SEPARATOR);
		for(int i=0;i<ids.length;i++){
			buff.append(ids[i]);
			buff.append(",");
		}
		buff.deleteCharAt(buff.length()-1);
		buff.append(")");
		buff.append(" order by articleCategory.orderList asc");
		String hql=buff.toString();
		Object[] params ={articleCategory};
		return findByHql(hql, params);
	}
	


	@Override
	public List<ArticleCategory> getChildrenArticleCategoryList(
			ArticleCategory articlecategory) {
		String hql = "from ArticleCategory articleCategory where articleCategory != ? and articleCategory.path like ? order by articleCategory.orderList asc";
		Object[] params ={articlecategory,articlecategory.getPath() + "%"};
		return findByHql(hql, params);

	}

	@Override
	public List<ArticleCategory> findByCateKey(String str) {
		String hql = "SELECT * from p2p_articlecategory where cateKey like '%"+str+"%' and parent_id = 0 ";
		List list = this.getSession().createSQLQuery(hql).addEntity(ArticleCategory.class).list();

		return  list;
	}

	@Override
	public List<ArticleCategory> findByParentId(Long id) {
		String hql = "SELECT * from p2p_articlecategory where  parent_id = "+id+" ";
		List list = this.getSession().createSQLQuery(hql).addEntity(ArticleCategory.class).list();

		return  list;
	}
}