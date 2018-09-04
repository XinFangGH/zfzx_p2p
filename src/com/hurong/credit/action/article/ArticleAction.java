package com.hurong.credit.action.article;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.DateUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.article.Operate;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleCategoryService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;



public class ArticleAction extends BaseAction {

	private static final long serialVersionUID = -25541236985328967L;
	
	private ArticleCategory articleCategory;
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articlecategoryService;
	private List<ArticleCategory> listArticleCat;//新闻分类列表
	private List<Article> listArticle;//新闻分类列表
	private String helpTitle;//帮助中心页面的标题
	private String newType;//判断是否是单页面
	private Article article;
	private Long id;
	private String categoryId;
    private  String type;

  

	public String getNewType() {
		return newType;
	}

	public void setNewType(String newType) {
		this.newType = newType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<ArticleCategory> getListArticleCat() {
		return listArticleCat;
	}

	public void setListArticleCat(List<ArticleCategory> listArticleCat) {
		this.listArticleCat = listArticleCat;
	}

	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	public String getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String list() {
		
		if (pager == null) {
			pager = new Pager();
		}
		//获取分类 列表
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_type_N_EQ", "1");//type 为1 是新闻类 类别  为 0 为 单页面 类别
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		QueryFilter filter=new QueryFilter(getRequest());
		Long catId;
		if(this.getRequest().getParameter("catId")!=null){
		 catId=Long.valueOf(this.getRequest().getParameter("catId"));
		 articleCategory =articlecategoryService.get(catId);
			filter.addFilter("Q_articleCategory.id_L_EQ", catId.toString());
		}else{
			 articleCategory =listArticleCat.get(0);
		}
		if(articleCategory!=null){
		filter.addFilter("Q_articleCategory.id_L_EQ", articleCategory.getId().toString());
		}
		filter.getPagingBean().setStart((pager.getPageNumber()-1)*pager.getPageSize());
		filter.getPagingBean().setPageSize(pager.getPageSize());
		filter.addFilter("Q_isPublication_S_EQ", "1");
		List<Article> list = articleService.getAll(filter);
		
		pager.setProperty(null);
		pager.setKeyword(null);
		pager.setTotalCount(filter.getPagingBean().getTotalItems());
		pager.setList(list);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.ARTICLE_LIST).getTemplateFilePath());
		return "freemarker";
		}
	

	/**
	 * 显示列表
	 */
	public String listByCat(){
		try{
		QueryFilter filter=new QueryFilter(getRequest());
		List<Article> list= articleService.getAll(filter);
		Type type=new TypeToken<List<Article>>(){}.getType();
		StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"result\":");
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
        buff.append("}");
		setJsonString(buff.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String show() {
		
		QueryFilter filter=new QueryFilter(getRequest());
		
		articleCategory = articlecategoryService.get(new Long(19));
		
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(Article.DEFAULT_ARTICLE_LIST_PAGE_SIZE);
		}
		this.start = pager.getPageSize()*(pager.getPageNumber()-1);
		this.limit = pager.getPageSize();
		PagingBean pag = getInitPagingBean();
		listArticle = articleService.getAll(pag);
		
		
		pager.setProperty(null);
		pager.setKeyword(null);
		pager.setTotalCount(pag.getTotalItems());
		pager.setList(listArticle);
		
		//bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ARTICLE_LIST).getTemplateFilePath());
		return SUCCESS;
		
	}
	
	public String detail(){
		 if(type==null||type.equals("")){
			 type="0"; 
		 }
		 article=articleService.get(id);
		 QueryFilter filterArtCat=new QueryFilter(getRequest());
		 filterArtCat.addFilter("Q_articleCategory.id_L_EQ", categoryId);//type 为1 是新闻类 类别  为 0 为 单页面 类别
		 filterArtCat.addFilter("Q_single_N_EQ", type);//type 为1 是新闻类 类别  为 0 为 单页面 类别
		 listArticle=articleService.getAll(filterArtCat);
		 
		 //获取分类 列表
		 QueryFilter filterArt=new QueryFilter(getRequest());
		 filterArt.addFilter("Q_type_N_EQ", "1");//type 为1 是新闻类 类别  为 0 为 单页面 类别
		listArticleCat=articlecategoryService.getAll(filterArt);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(DynamicConfig.ARTICLE_CONTENT).getTemplateFilePath());
		return "freemarker";
	}
  
	public String save(){
		flushCache();
		return SUCCESS;
	}
	
	public String cloud(){
		this.getSession().setAttribute("cloud", 1);
		setJsonString("{\"success\":true}");
		return "success";
	}
	
	/**
	 * 帮助中心的主页面
	 * 访问路径：/article/help_list_allArticle.do?lid=0
	 * @return
	 */
	public String helplistall(){
		
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "27");//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		//查看每个列表下的单页面
		QueryFilter filter=null;
		List<Article> listArt=articleService.getArticleAll();
		this.getRequest().setAttribute("listArt", listArt);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.HELPLISTALL).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 帮助中心其他页面
	 * 访问路径：/article/help_listArticle.do?lid=1
	 * @return
	 */
	public String helplist(){
		bpCustMember= (BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		String lid = this.getRequest().getParameter("lid");
		QueryFilter qf=new QueryFilter(getRequest());
		qf.addFilter("Q_id_L_EQ", lid);//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(qf);
		int newsType=0;
		if(listArticleCat.size()>0)
		{
			 newsType =listArticleCat.get(0).getType();
		}
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		this.getRequest().setAttribute("articleCategory.id", lid);
		this.getRequest().setAttribute("articleCategorysign", lid);
		listArticle = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		List<Article> listcount = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		BigInteger count = articleService.getCount(this.getRequest());
		pager.setTotalCount(Integer.valueOf(count.toString())/*listcount != null ? listcount.size() : 0*/);
		
		pager.setList(listArticle);
		
		QueryFilter filterArtCat=new QueryFilter(this.getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "27");//查询栏目中心
		filterArtCat.addFilter("Q_id_L_EQ", lid);//
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		if("35".equals(lid)&&lid!=null){
			//平台介绍
			this.getRequest().setAttribute("helpTitle", "新手指引");
			
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}else if("36".equals(lid)&&lid!=null){
			//注册与登录
			this.getRequest().setAttribute("helpTitle", "会员须知");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}else if("37".equals(lid)&&lid!=null){
			//认证与安全
			this.getRequest().setAttribute("helpTitle", "我要理财");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}else if("38".equals(lid)&&lid!=null){
			//充值与提现
			this.getRequest().setAttribute("helpTitle", "我要借款");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}else if("39".equals(lid)&&lid!=null){
			//利息和费用
			this.getRequest().setAttribute("helpTitle", "常见问题");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}else if("40".equals(lid)&&lid!=null){
			//如何借款
			this.getRequest().setAttribute("helpTitle", "如何借款");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.HELPCONTENT).getTemplateFilePath());
			}
		}
		return "freemarker";
	}
	/**
	 * 帮助中心的详情页
	 * 访问路径：/article/help_contentArticle.do?aid=aa
	 * @return
	 */
	public String helpcontent(){
		String catId = this.getRequest().getParameter("catId");
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_id_L_EQ", catId);//查询新闻中心栏目
		listArticle=articleService.getAll(filterArtCat);
		QueryFilter filterArt=new QueryFilter(this.getRequest());
		filterArt.addFilter("Q_parentId_L_EQ", "27");//查询新闻栏目中心
		if(null!=listArticle && listArticle.size()>0){
			filterArt.addFilter("Q_id_L_EQ", listArticle.get(0).getArticleCategoryId().toString());//查询栏目中心
		}
		listArticleCat=articlecategoryService.getAll(filterArt);
		if(listArticleCat!=null&&listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		this.getRequest().setAttribute("articleCategory", articleCategory);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(				DynamicConfig.HELPCONTENT).getTemplateFilePath());

		return "freemarker";
	}
	/**
	 * 法律法规
	 * @return
	 */
	public String lawList(){
		String lid = this.getRequest().getParameter("lid");
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		ArticleCategory category = articlecategoryService.get(Long.valueOf(lid));
		if(category!=null){
			this.getRequest().setAttribute("helpTitle", category.getName());
		}
		this.getRequest().setAttribute("articleCategory.id", lid);
		listArticle = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		//List<Article> listcount = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		BigInteger count = articleService.getCount(this.getRequest());
		pager.setTotalCount(Integer.valueOf(count.toString()));
		pager.setList(listArticle);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.LAWLIST).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 法律法规详细页面
	 * @return
	 */
	public String lawListDetail(){

		String artId = this.getRequest().getParameter("catId");
		if(artId!=null&&!artId.equals("")){
			Article article = articleService.get(Long.valueOf(artId));
			ArticleCategory articleCategory = articlecategoryService.get(article.getArticleCategoryId());
			this.getRequest().setAttribute("article", article);
			this.getRequest().setAttribute("articleCategory", articleCategory);
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.LAWLISTDETAIL).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 新闻中心的主页面
	 * 访问路径：/article/news_list_allArticle.do?lid=0
	 * @return
	 */
	public String newslistall(){
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "25");//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		//查看每个列表下的单页面
		QueryFilter filter=null;
		List<Article> listArt=articleService.getArticleAll();
		this.getRequest().setAttribute("listArt", listArt);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.NEWSLISTALL).getTemplateFilePath());
		return "freemarker";
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public List<Article> getListArticle() {
		return listArticle;
	}

	public void setListArticle(List<Article> listArticle) {
		this.listArticle = listArticle;
	}

	//关于我们
	public String aboutlist(){
		String lid = this.getRequest().getParameter("lid");
		QueryFilter qf=new QueryFilter(getRequest());
		qf.addFilter("Q_id_L_EQ", lid);//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(qf);
		int newsType=0;
		if(listArticleCat.size()>0)
		{
			 newsType =listArticleCat.get(0).getType();
		}
		
		QueryFilter filterArtCat=new QueryFilter(this.getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "26");//查询新闻栏目中心
		filterArtCat.addFilter("Q_id_L_EQ", lid);//查询栏目中心
	
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		if(listArticleCat!=null||listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		this.getRequest().setAttribute("articleCategory.id", lid);
		listArticle = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		List<Article> listcount = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		pager.setTotalCount(listcount != null ? listcount.size() : 0);
		
		pager.setList(listArticle);
		
		
		this.getRequest().setAttribute("lid", lid);//保存请求的id
		
		if("45".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "公司简介");
			
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTCONTENT).getTemplateFilePath());
			}
		}else if("46".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "公司资质");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTCONTENT).getTemplateFilePath());
			}
		}else if("47".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "加入我们");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTCONTENT).getTemplateFilePath());
			}
		}else if("48".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "联系我们");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.ABOUTCONTENT).getTemplateFilePath());
			}
		}
		return "freemarker";
	}
	
	public String aboutcontent(){
		String catId = this.getRequest().getParameter("catId");
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_id_L_EQ", catId);//查询新闻中心栏目
		listArticle=articleService.getAll(filterArtCat);
		
		QueryFilter filterArt=new QueryFilter(this.getRequest());
		filterArt.addFilter("Q_parentId_L_EQ", "26");//查询新闻栏目中心
		filterArt.addFilter("Q_id_L_EQ", listArticle.get(0).getArticleCategoryId().toString());//查询栏目中心
		listArticleCat=articlecategoryService.getAll(filterArt);
		if(listArticleCat!=null||listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.ABOUTCONTENT).getTemplateFilePath());
		return "freemarker";
	}
	
	//安全保证
	public String safelist(){
		String lid = this.getRequest().getParameter("lid");
		QueryFilter qf=new QueryFilter(getRequest());
		qf.addFilter("Q_id_L_EQ", lid);//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(qf);
		int newsType=0;
		if(listArticleCat.size()>0)
		{
			 newsType =listArticleCat.get(0).getType();
		}
		
		
		QueryFilter filterArtCat=new QueryFilter(this.getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "28");//查询新闻栏目中心
		filterArtCat.addFilter("Q_id_L_EQ", lid);//查询栏目中心
		
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		if(listArticleCat!=null||listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		this.getRequest().setAttribute("articleCategory.id", lid);
		listArticle = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		List<Article> listcount = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		pager.setTotalCount(listcount != null ? listcount.size() : 0);
		
		pager.setList(listArticle);
		
		
		this.getRequest().setAttribute("lid", lid);//保存请求的id
		
		if("41".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("" +
					"Title", "风险基金");
			
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFELIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFECONTENT).getTemplateFilePath());
			}
		}else if("42".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "交易安全");

			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFELIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFECONTENT).getTemplateFilePath());
			}
		}else if("43".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "信用审核");

			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFELIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFECONTENT).getTemplateFilePath());
			}
		}else if("44".equals(lid)&&lid!=null){
			this.getRequest().setAttribute("helpTitle", "风险控制");

			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFELIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.SAFECONTENT).getTemplateFilePath());
			}
		}
		return "freemarker";
	}
	
	public String safecontent(){
		String catId = this.getRequest().getParameter("catId");
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_id_L_EQ", catId);//查询新闻中心栏目
		listArticle=articleService.getAll(filterArtCat);
		
		QueryFilter filterArt=new QueryFilter(this.getRequest());
		filterArt.addFilter("Q_parentId_L_EQ", "28");//查询新闻栏目中心
		filterArt.addFilter("Q_id_L_EQ", listArticle.get(0).getArticleCategoryId().toString());//查询栏目中心
		listArticleCat=articlecategoryService.getAll(filterArt);
		if(listArticleCat!=null||listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SAFECONTENT).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 新闻中心其他页面
	 * 访问路径：/article/news_listArticle.do?lid=7
	 * @return
	 */
	public String newslist(){
		String lid = this.getRequest().getParameter("lid");
		String isMobile=this.getRequest().getParameter("isMobile");
		QueryFilter qf=new QueryFilter(getRequest());
		qf.addFilter("Q_id_L_EQ", lid);//查询新闻中心栏目
		listArticleCat=articlecategoryService.getAll(qf);
		int newsType =listArticleCat.get(0).getType();
		
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		if(null!=isMobile&&isMobile.endsWith("1")){
			String page=this.getRequest().getParameter("page");
			String limit=this.getRequest().getParameter("limit");
			pager = new Pager();
			pager.setPageSize(Integer.valueOf(limit));
			pager.setPageNumber(Integer.valueOf(page));
		}
		this.getRequest().setAttribute("articleCategory.id", lid);
		listArticle = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		List<Article> listcount = articleService.getByIdCat(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		BigInteger count = articleService.getCount(this.getRequest());
		pager.setTotalCount(Integer.valueOf(count.toString()));
		
		pager.setList(listArticle);
		
		QueryFilter filterArtCat=new QueryFilter(this.getRequest());
		filterArtCat.addFilter("Q_parentId_L_EQ", "25");//查询新闻栏目中心
		filterArtCat.addFilter("Q_id_L_EQ", lid);//查询栏目中心
		filterArtCat.addSorted("createDate", "desc");
		listArticleCat=articlecategoryService.getAll(filterArtCat);
		if(listArticleCat!=null&&listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		this.getRequest().setAttribute("lid", lid);//保存请求的id
		if("30".equals(lid)&&lid!=null){
			//官方公告
			this.getRequest().setAttribute("helpTitle", articleCategory);
			if(newsType==1&&null==isMobile)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				
				if(null!=isMobile&&isMobile.endsWith("1")){
					StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
					.append(pager.getTotalCount()).append(",\"result\":");
						JSONSerializer json = JsonUtil.getJSONSerializer("createDate");
						json.transform(new DateTransformer("yyyy-MM-dd"), new String[] {
								
								"createDate" });
						buff.append(json.serialize(pager.getList()));
						buff.append("}");
						jsonString = buff.toString();
						return SUCCESS;
					
				}else{
					this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
							DynamicConfig.NEWSCONTENT).getTemplateFilePath());
					
				}
			}
		}else if("31".equals(lid)&&lid!=null){
			//还款公告
			this.getRequest().setAttribute("helpTitle", "还款公告");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("32".equals(lid)&&lid!=null){
			//法律法规
			this.getRequest().setAttribute("helpTitle", "法律法规");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("33".equals(lid)&&lid!=null){
			//行业动态
			this.getRequest().setAttribute("helpTitle", "最新动态");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("34".equals(lid)&&lid!=null){
			//媒体报道
			this.getRequest().setAttribute("helpTitle", articleCategory);
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else{
				if(listArticle.size()>0){
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("35".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "平台介绍");
			if(newsType==1){
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else{
				if(listArticle.size()>0){
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("36".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "注册与登录");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("37".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "认证与安全");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("38".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "充值与提现");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("39".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "利息和费用");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}else if("40".equals(lid)&&lid!=null){
			//测试单页面
			this.getRequest().setAttribute("helpTitle", "如何借款");
			if(newsType==1)
			{
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSLIST).getTemplateFilePath());
			}else
			{
				if(listArticle.size()>0)
				{
					String pId=String.valueOf(listArticle.get(0).getId());
					 QueryFilter qfNews=new QueryFilter(getRequest());
					 qfNews.addFilter("Q_id_L_EQ", pId);//查询新闻中心栏目
					 listArticle=articleService.getAll(qfNews);
				}
				this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
						DynamicConfig.NEWSCONTENT).getTemplateFilePath());
			}
		}
		
		return "freemarker";
	}
	
	/**
	 * 新闻中心的详情页
	 * 访问路径：/article/news_contentArticle.do?aid=aa
	 * @return
	 */
	public String newscontent(){
		String catId = this.getRequest().getParameter("catId");
		String hujin = this.getRequest().getParameter("type");
		QueryFilter filterArtCat=new QueryFilter(getRequest());
		filterArtCat.addFilter("Q_id_L_EQ", catId);//查询新闻中心栏目
		listArticle=articleService.getAll(filterArtCat);
		QueryFilter filterArt=new QueryFilter(this.getRequest());
//		filterArt.addFilter("Q_parentId_L_EQ", "25");//查询新闻栏目中心
		filterArt.addFilter("Q_id_L_EQ", listArticle.get(0).getArticleCategoryId().toString());//查询栏目中心
		listArticleCat=articlecategoryService.getAll(filterArt);
		if(listArticleCat!=null&&listArticleCat.size()>0){
			articleCategory = listArticleCat.get(0);
		}
		Article article = listArticle.get(0);
		if (null != article) {
			article.setHits(article.getHits()+1);
			articleService.merge(article);
		}
		if (StringUtils.isNotBlank(hujin) && hujin.equals("hujin")){
			List<ArticleCategory> listParent = articlecategoryService.findByCateKey("hujin");
			ArticleCategory parent = articlecategoryService.get(articleCategory.getParentId());
			this.getRequest().setAttribute("parent",parent);
			this.getRequest().setAttribute("listParent",listParent);
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWSCONTENTHUJIN).getTemplateFilePath());
		}else {
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWSCONTENT).getTemplateFilePath());
		}
		return "freemarker";
	}
	/**
	 * 收费标准
	 * 访问路径：/article/chargestandardArticle.do
	 * @return
	 */
	public String chargestandard(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CHARGESSTANDARD).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 注册演示
	 * 访问路径：/article/regShowArticle.do
	 * @return
	 */
	public String regShow(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.REGSHOW).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 投资演示
	 * 访问路径：/article/inventShowArticle.do
	 * @return
	 */
	public String inventShow(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.INVENTSHOW).getTemplateFilePath());
		return "freemarker";
	}
	
	/**
	 * 投资技巧
	 * 访问路径：/article/inventShillArticle.do
	 * @return
	 */
	public String inventShill(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.INVENTSHILL).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 宏达信贷的焦点文章和最新消息
	 * @return
	 */
	public String hongdaArticle(){
		String type = this.getRequest().getParameter("type");
		String lid = this.getRequest().getParameter("id");
		if (pager == null) {
			pager = new Pager();
			pager.setPageSize(10);
		}
		//设置新闻中心
		this.getRequest().setAttribute("articleCategory.id", lid);
		listArticle = articleService.getHongDaArticle(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		List<Article> listcount = articleService.getHongDaArticle(this.getRequest(), (pager.getPageNumber() - 1)* pager.getPageSize(), pager.getPageSize());
		pager.setTotalCount(listcount != null ? listcount.size() : 0);
		
		pager.setList(listArticle);
		
		
		if("30".equals(lid)&&lid!=null){
			//官方公告
			this.getRequest().setAttribute("helpTitle", "官方公告");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWSLIST).getTemplateFilePath());
		}else if("34".equals(lid)&&lid!=null){
			//媒体报道
			this.getRequest().setAttribute("helpTitle", "媒体报道");
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWSLIST).getTemplateFilePath());
		}
		return "freemarker";
	}
	/**
	 *运营数据 数据整理
	 *
	 * @auther: XinFang
	 * @date: 2018/6/27 14:48
	 */
	public String operation(){
		String end = this.getRequest().getParameter("end");

		Operate operate =	articleService.getOperationMessage(end);

		this.getRequest().setAttribute("operate",operate);
		this.getRequest().setAttribute("date",end.replaceAll("-",""));

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.OPERATEDETAILS).getTemplateFilePath());
		return "freemarker";
	}


}