package com.hurong.credit.dao.article.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hurong.credit.model.article.Operate;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.user.BpCustMember;
import com.sdicons.json.validator.impl.predicates.Str;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.article.ArticleDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticleDao{

	public ArticleDaoImpl() {
		super(Article.class);
	}
	
	@Override
	public List<Article> getRecommendArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? order by article.isTop desc, article.createDate desc";
		Object[] params={"1","1"};
		return findByHql(hql,params);
	}

	@Override
	public List<Article> getRecommendArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and article.isRecommend = ? and (articlecategory = ? or article.articlecategory.path like ?) order by article.isTop desc, article.createDate desc";
		
		Object[] params={"1","1",articleCategory,articleCategory.getPath() + "%"};
		return findByHql(hql,params);
	}

	@Override
	public List<Article> getHotArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.hits desc, article.isTop desc, article.createDate desc";
		Object[] params={"1"};
		return findByHql(hql,params);
	}

	@Override
	public List<Article> getHotArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articlecategory = ? or article.articlecategory.path like ?) order by article.hits desc, article.isTop desc, article.createDate desc";
		
		Object[] params={"1",articleCategory,articleCategory.getPath() + "%"};
		return findByHql(hql,params);
		
	}
	
	@Override
	public List<Article> getNewArticleList(int maxResults) {
		String hql = "from Article as article where article.isPublication = ? order by article.createDate desc";
		
		Object[] params={"1"};
		return findByHql(hql,params);
	}

	@Override
	public List<Article> getNewArticleList(ArticleCategory articleCategory, int maxResults) {
		String hql = "from Article as article where article.isPublication = ? and (articlecategory = ? or article.articlecategory.path like ?) order by article.createDate desc";
		
		Object[] params={"1",articleCategory,articleCategory.getPath() + "%"};
		return findByHql(hql,params);
	}
	@Override
	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		detachedCriteria.createAlias("articleCategory", "articleCategory");
		detachedCriteria.add(Restrictions.or(Restrictions.eq("articleCategory", articleCategory), Restrictions.like("articleCategory.path", articleCategory.getPath() + "%")));
		detachedCriteria.add(Restrictions.eq("isPublication", "1"));
		return super.findByPager(pager, detachedCriteria);
	}

	@Override
	public List<Article> getLimit(Integer firstNum, Integer maxResults) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Article as article  order by article.createDate desc");
		Query query = this.getSession().createQuery(hql.toString());
		
		if(firstNum >0){
			query.setFirstResult(firstNum);
		}
		if(maxResults > 0){
			query.setMaxResults(maxResults);
		}
		
		return query.list();
	}

	@Override
	public List<Article> getByCat(ArticleCategory articleCategory) {
      String hql = "from Article as article where articleCategory=? order by createDate desc ";
		
		Object[] params={articleCategory};
		return findByHql(hql,params);
	}
	@Override
	public List<Article> getArticleOrderNew(){
      String sql = "(SELECT * from p2p_article where isTop = '1' ORDER BY createDate DESC LIMIT 2) " +
      		"UNION (select * from p2p_article where articleCategory_id = "+Long.parseLong("30")+" ORDER BY createDate DESC LIMIT 2)" +
      		"union (select * from p2p_article where articleCategory_id = "+Long.parseLong("33")+" ORDER BY createDate DESC LIMIT 2)" +
      		"union (select * from p2p_article where articleCategory_id = "+Long.parseLong("34")+" ORDER BY createDate DESC LIMIT 2) " +
      		"ORDER BY isTop='1' DESC LIMIT 6";
      List<Article> list = new ArrayList<Article>();
      Query setResultTransformer = this.getSession().createSQLQuery(sql)
      .addScalar("id", Hibernate.LONG).
	  addScalar("createDate", Hibernate.DATE).
	  addScalar("title", Hibernate.STRING).
	  addScalar("content", Hibernate.STRING).
	  setResultTransformer(Transformers.aliasToBean(Article.class));
      list = setResultTransformer.list();
	  return list;
	}
	@Override
	public List<Article> getByIdCat(HttpServletRequest request,Integer start ,Integer limit) {
		//Long.parseLong((String)request.getAttribute("articleCategory.id"));
		//Long articleCategoryId = (Long)request.getAttribute("articleCategory.id");
		String sql = "select * from p2p_article p where p.articleCategory_id= "+Long.parseLong((String)request.getAttribute("articleCategory.id"))+" and p.isPublication = 1 ORDER BY createDate DESC";
		
		List<Article> list = new ArrayList<Article>();
		if(start==null||limit==null){
		List listcount =this.getSession().createSQLQuery(sql).list();
		list=listcount;
		}else{
			/*
			 * 
				  addScalar("yearManagementConsultingOfRate", Hibernate.BIG_DECIMAL).
				  addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
				  addScalar("startDate", Hibernate.DATE).*/
		  list=this.getSession().createSQLQuery(sql).
				  addScalar("id", Hibernate.LONG).
				  addScalar("createDate", Hibernate.DATE).
				  addScalar("title", Hibernate.STRING).
				  addScalar("content", Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(Article.class)).
				  setFirstResult(start).setMaxResults(limit).list();
		 
		}
		return list;
	}

	@Override
	public List<Article> getArticleAll() {
		// TODO Auto-generated method stub
		String hql = "from Article as article where article.single=1 ";
		List<Article> list = this.getSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<Article> getHongDaArticle(HttpServletRequest request,
			Integer start, Integer limit) {
		// TODO Auto-generated method stub
		String sql = "select * from p2p_article p where p.articleCategory_id= "+Long.parseLong((String)request.getAttribute("articleCategory.id"));
		String type = (String)request.getAttribute("type");
		if(type !=null&&!"".equals(type)&&!"null".equals(type)){
			sql =sql+ "  and p.isTop=1";
		}else{
			sql =sql+ "  order by p.createDate DESC";
		}
		List<Article> list = new ArrayList<Article>();
		if(start==null||limit==null){
		List listcount =this.getSession().createSQLQuery(sql).list();
		list=listcount;
		}else{
			/*
			 * 
				  addScalar("yearManagementConsultingOfRate", Hibernate.BIG_DECIMAL).
				  addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
				  addScalar("startDate", Hibernate.DATE).*/
		  list=this.getSession().createSQLQuery(sql).
				  addScalar("id", Hibernate.LONG).
				  addScalar("createDate", Hibernate.DATE).
				  addScalar("title", Hibernate.STRING).
				  addScalar("content", Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(Article.class)).
				  setFirstResult(start).setMaxResults(limit).list();
		 
		}
		return list;
	}
	public BigInteger getCount(HttpServletRequest request){
		String sql = "select count(*) from p2p_article p where p.articleCategory_id= "+Long.parseLong((String)request.getAttribute("articleCategory.id"));
		return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
	}

	@Override
	public List<Article> getArticleLimit(Integer articleCategory_id) {
		String sql = "select id,createDate,title,content from p2p_article where articleCategory_id = "+articleCategory_id+" ORDER BY createDate DESC LIMIT 2 ";
		System.out.println(sql.toString());
		List<Article> list = new ArrayList<Article>();
		list = this.getSession().createSQLQuery(sql)
				.addScalar("id", Hibernate.LONG).
						addScalar("createDate", Hibernate.DATE).
						addScalar("title", Hibernate.STRING).
						addScalar("content", Hibernate.STRING).
						setResultTransformer(Transformers.aliasToBean(Article.class)).list();
		return list;
	}

	@Override
	public List<Article> getArticleLimit1(Integer articleCategory_id, Integer start, Integer limit) {
		StringBuffer sql = new StringBuffer("select p.id"+
			",p.createDate"+
			",p.title"+
			",p.content"+
			" from p2p_article p where");
		sql.append(" articleCategory_id = '" + articleCategory_id + "'");
		sql.append(" ORDER BY createDate DESC");
		//String sql = "select id,createDate,title,content from p2p_article where articleCategory_id = "+articleCategory_id+" ORDER BY createDate DESC LIMIT 2 ";
		System.out.println(sql.toString());
		List<Article> list = new ArrayList<Article>();
		list = this.getSession().createSQLQuery(sql.toString())
				.addScalar("id", Hibernate.LONG).
						addScalar("createDate", Hibernate.DATE).
						addScalar("title", Hibernate.STRING).
						addScalar("content", Hibernate.STRING).
						setResultTransformer(Transformers.aliasToBean(Article.class)).
						setFirstResult(start).setMaxResults(limit).list();
		return list;
	}

	@Override
	public Object getArticCount() {
		StringBuffer sql = new StringBuffer("select COUNT(id)"+
				" from p2p_article p where");
		sql.append(" articleCategory_id = 30");
		sql.append(" ORDER BY createDate DESC");
		System.out.println(sql.toString());
		return this.getSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	@Override
	public List<Article> getByCat5(ArticleCategory articleCategory) {
	    String sql = "SELECT * from p2p_article a ,(SELECT id  from p2p_articlecategory where parent_id = "+articleCategory.getId()+")as b where a.articleCategory_id = b.id ORDER BY createDate desc limit 5";
	    return this.getSession().createSQLQuery(sql).addEntity(Article.class).list();
//		String hql = "from Article as article where articleCategory=? order by createDate desc limit 5 ";
//
//		Object[] params={articleCategory};
//		return findByHql(hql,params);
	}

	//	public List<Article> getArticleLimit1(Integer articleCategory_id, Integer start, Integer limit) {
//		StringBuffer sql = new StringBuffer("select COUNT(id)"+
//				" from p2p_article p where");
//		sql.append(" articleCategory_id = '" + articleCategory_id + "'");
//		sql.append(" ORDER BY createDate DESC");
//		//String sql = "select id,createDate,title,content from p2p_article where articleCategory_id = "+articleCategory_id+" ORDER BY createDate DESC LIMIT 2 ";
//		System.out.println(sql.toString());
//		List<Article> list = new ArrayList<Article>();
//		list = this.getSession().createSQLQuery(sql.toString())
//				.addScalar("id", Hibernate.LONG).
//						addScalar("createDate", Hibernate.DATE).
//						addScalar("title", Hibernate.STRING).
//						addScalar("content", Hibernate.STRING).
//						setResultTransformer(Transformers.aliasToBean(Article.class)).
//						setFirstResult(start).setMaxResults(limit).list();
//		return list;
//	}

	@Override
	public Operate getSumMoneyCount(String end) {
		String sql = "SELECT SUM(bidMoney) as sumLoanMoney ,count(*) as sumLoanCount from pl_bid_plan where state in (7,10)  and fullTime <= '"+end+" 23:59:59'";

        Operate operate = (Operate) this.getSession().createSQLQuery(sql).
                addScalar("sumLoanMoney", Hibernate.INTEGER).
                addScalar("sumLoanCount", Hibernate.INTEGER).
                setResultTransformer(Transformers.aliasToBean(Operate.class)).
                list().get(0);

        return operate;
	}

    @Override
    public Integer getBalanceMoney(String end) {
	    String sql= "SELECT sum(bp.notMoney) as balanceLoanMoney from bp_fund_intent bp , (SELECT bidId from pl_bid_plan WHERE state = 7 and fullTime <'"+end+" 23:59:59' )as pp where pp.bidId = bp.bidPlanId AND bp.fundType  = 'principalRepayment' and bp.notMoney > 0 ";
        BigDecimal i = (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
        return i.intValue();
    }

    @Override
    public Integer getBalanceCount(String end) {
	    String sql = "SELECT count(*) from (SELECT * from bp_fund_intent bp , (SELECT bidId from pl_bid_plan WHERE state = 7 and fullTime <'"+end+" 23:59:59' )as pp where pp.bidId = bp.bidPlanId AND bp.fundType  = 'principalRepayment' and bp.notMoney > 0 GROUP BY bidPlanId) as c";
        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();
    }

    @Override
    public Integer getSumPayPeople(String end) {
	    String sql = "SELECT count(*) from (SELECT count(userId) from pl_bid_info where bidtime <= '"+end+" 23:59:59' GROUP BY userId ) as pl";

        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();
    }

    @Override
    public Integer getPayPeople(String end) {
        String sql = "SELECT count(*) from (SELECT count(*) from bp_fund_intent bp,(SELECT orderNo from pl_bid_info where state in (1,2) and bidtime <=  '"+end+" 23:59:59') as pl where bp.orderNo = pl.orderNo and  bp.fundType ='principalRepayment' and bp.notMoney > 0  GROUP BY bp.investPersonId) as bpfund";

        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();

    }

    @Override
    public Integer getSumIncomePeople(String end) {
        String sql = "SELECT count(*) from (SELECT * from pl_bid_plan  WHERE createtime < '"+end+" 23:59:59' and state != -1  GROUP BY  receiverP2PAccountNumber) as pp";

        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();
    }

    @Override
    public Integer getIncomePeople(String end) {
        String sql = "SELECT count(*) from (SELECT * from pl_bid_plan  WHERE createtime < '"+end+" 23:59:59' and state not in (-1,10)   GROUP BY  receiverP2PAccountNumber) as pp";

        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();
    }

    @Override
    public BigDecimal getTenIncomeMoney(String end) {
        String sql = "SELECT SUM(a.allMoney) as tenIncomeMoney from (SELECT SUM(b.notMoney) as allMoney from bp_fund_intent b LEFT JOIN pl_bid_plan p on b.bidPlanId = p.bidId where b.notMoney>0 and p.state = 7  and p.fullTime <'"+end+" 23:59:59' GROUP BY p.receiverP2PAccountNumber  order by SUM(b.notMoney) desc LIMIT 10) as a";

        BigDecimal decimal = (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
        return decimal;
    }

    @Override
    public BigDecimal getMaxIncomeMoney(String end) {
        String sql = "SELECT SUM(b.notMoney) as maxIncomeMoney   from bp_fund_intent b LEFT JOIN pl_bid_plan p on b.bidPlanId = p.bidId where b.notMoney>0 and p.state = 7  and p.fullTime <'"+end+" 23:59:59' GROUP BY p.receiverP2PAccountNumber  order by SUM(b.notMoney) desc LIMIT 1";

        BigDecimal decimal = (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
        return decimal;
    }

	@Override
	public List<Article> getArticleById(Long articleId) {
//		String sql = "SELECT content FROM p2p_article WHERE id = '"+articleId+"'";
		StringBuffer sql = new StringBuffer("select a.content" +
				",a.title"+
				",a.modifyDate" +
				" from p2p_article a where id=" +
				articleId);
		System.out.println(sql);
		//Article article = (Article) this.getSession().createSQLQuery(sql).addEntity(Article.class).addScalar("content",Hibernate.STRING).uniqueResult();
		return this.getSession().createSQLQuery(sql.toString()).
				addScalar("content",Hibernate.STRING).
				addScalar("title",Hibernate.STRING).
				addScalar("modifyDate",Hibernate.DATE).
				setResultTransformer(Transformers.aliasToBean(Article.class)).
				list();
	}

	@Override
	public Integer getMessageAcount(Long userId, String status) {
		String sql = "select count(1)  from oa_news_messagerinfo o where o.userid=? and o.readStatus=?";
		System.out.println(sql);
		BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).setParameter(0,userId).setParameter(1,status).uniqueResult();
		return integer.intValue();
	}
}