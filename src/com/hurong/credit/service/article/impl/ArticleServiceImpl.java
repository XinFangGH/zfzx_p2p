package com.hurong.credit.service.article.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.article.ArticleDao;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.article.Operate;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.article.ArticleCategoryService;


/**
 * 
 * @author 
 *
 */
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService{
	@Resource
	private ArticleCategoryService articlecategoryService;
	@SuppressWarnings("unused")
	private ArticleDao dao;
	

	public ArticleServiceImpl(ArticleDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Pager getArticlePager(ArticleCategory articleCategory, Pager pager) {
		return dao.getArticlePager(articleCategory, pager);
	}

	@Override
	public List<Article> getLimit(Integer firstNum, Integer limit) {
		// TODO Auto-generated method stub
		
		return dao.getLimit(firstNum, limit);
	}

	@Override
	public List<Article> getByCat(ArticleCategory articleCategory) {
		return dao.getByCat( articleCategory);
	}

	@Override
	public List<Article> getByIdCat(HttpServletRequest request,Integer start ,Integer limit) {
		// TODO Auto-generated method stub
		return dao.getByIdCat(request,start,limit);
	}

	@Override
	public List<Article> getArticleAll() {
		// TODO Auto-generated method stub
		return dao.getArticleAll();
	}

	@Override
	public List<Article> getHongDaArticle(HttpServletRequest request,
			Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return dao.getHongDaArticle(request,start,limit);
	}
	
	public BigInteger getCount(HttpServletRequest request){
		return dao.getCount(request);
	}

	@Override
	public List<Article> getArticleLimit(Integer articleCategory_id) {
		return dao.getArticleLimit(articleCategory_id);
	}

	@Override
	public List<Article> getArticleLimit1(Integer articleCategory_id, Integer start, Integer limit) {
		return dao.getArticleLimit1(articleCategory_id,start,limit);
	}

	@Override
	public Operate getOperationMessage(String end) {
        Operate operate = new Operate();
        //累计借贷金额
        //累计借贷笔数
		Operate sumMoney = dao.getSumMoneyCount(end);
		operate.setSumLoanCount(sumMoney.getSumLoanCount());
		operate.setSumLoanMoney(sumMoney.getSumLoanMoney());
        //累计借贷余额
        Integer balanceMoney = dao.getBalanceMoney(end);
        operate.setBalanceLoanMoney(balanceMoney);
        //借贷余额笔数
        Integer balanceCount = dao.getBalanceCount(end);
        operate.setBalanceLoanCount(balanceCount);
        //累计出借人数
        Integer sumPayPeople = dao.getSumPayPeople(end);
        operate.setSumPayPeople(sumPayPeople);
        //当前出借人数
        Integer payPeople = dao.getPayPeople(end);
        operate.setPayPeople(payPeople);
        //累计借款人数
        Integer sumIncomePeople = dao.getSumIncomePeople(end);
        operate.setSumIncomePeople(sumIncomePeople);
        //当前借款人数
        Integer incomePeople = dao.getIncomePeople(end);
        operate.setIncomePeople(incomePeople);
       //  tenIncomeMoney;//前十大借款人待还金额
		BigDecimal tenIncomeMoney = dao.getTenIncomeMoney(end);
       //   maxIncomeMoney;//最大单一借款人待还金额
		BigDecimal maxIncomeMoney = dao.getMaxIncomeMoney(end);
		//  tenIncomeMoneyProporion;//前十大借款人待还金额占比
		operate.setTenIncomeMoneyProporion(tenIncomeMoney.divide(new BigDecimal(sumMoney.getSumLoanMoney()),2));
       //   maxIncomeMoneyProporion;//最大单一借款人待还金额占比
		operate.setMaxIncomeMoneyProporion(maxIncomeMoney.divide(new BigDecimal(sumMoney.getSumLoanMoney()),2));
		return operate;
	}

	@Override
	public List<Article> getArticleById(Long articleId) {
		return dao.getArticleById(articleId);
	}

	@Override
	public Integer getPersonMessage(Long userId, String status) {
		return dao.getMessageAcount(userId,status);
	}

	@Override
	public Object getArticCount() {
		return dao.getArticCount();
	}

	@Override
	public List<Article> getByCat5(ArticleCategory articleCategory) {
		return dao.getByCat5( articleCategory);
	}
}