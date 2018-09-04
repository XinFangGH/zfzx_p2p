package com.hurong.credit.action.article;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.service.article.ArticleCategoryService;

import flexjson.JSONSerializer;

/**
 * 
 * @author 
 *
 */
public class ArticleCategoryAction extends BaseAction{
	@Resource
	private ArticleCategoryService articlecategoryService;
	private ArticleCategory articlecategory;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArticleCategory getArticleCategory() {
		return articlecategory;
	}

	public void setArticleCategory(ArticleCategory articlecategory) {
		this.articlecategory = articlecategory;
	}
	/**
	 * 产生树
	 * @return
	 */
	public String tree(){
		StringBuffer buff = new StringBuffer("[{id:'"+0+"',text:'顶级分类',expanded:true,children:[");
		List<ArticleCategory> rticlecategoryList = articlecategoryService.getByParentId(new Long(0l));
		for(ArticleCategory type:rticlecategoryList){
			buff.append("{id:'"+type.getId()).append("',text:'"+type.getName()).append("',");
			 buff.append(getChildType(type.getId()));
		}
		
		if (!rticlecategoryList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
	    }
		buff.append("]}]");

		setJsonString(buff.toString());
		
		logger.info("tree json:" + buff.toString());		
		return SUCCESS;
	}
	
	public String getChildType(Long parentId){
		StringBuffer buff = new StringBuffer();
		List<ArticleCategory> typeList = articlecategoryService.getByParentId(parentId);
		if(typeList.size() == 0){
			buff.append("leaf:true,expanded:true},");
			return buff.toString(); 
		}else {
			buff.append("expanded:true,children:[");
			for(ArticleCategory type:typeList){
				buff.append("{id:'"+type.getId()).append("',text:'"+type.getName()).append("',");
				
			    buff.append(getChildType(type.getId()));
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append("]},");
			return buff.toString();
		}
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ArticleCategory> list= articlecategoryService.getAll(filter);
		Type type=new TypeToken<List<ArticleCategory>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}

	/**
	 * 显示列表 通过类别
	 */
	public String listByType(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ArticleCategory> list= articlecategoryService.getAll(filter);
		Type type=new TypeToken<List<ArticleCategory>>(){}.getType();
		StringBuffer buff = new StringBuffer("{\"success\":true").append(",\"result\":");
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				articlecategoryService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		ArticleCategory articlecategory=articlecategoryService.get(id);
		
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(
				 "bidTime", "createTime",
				"updateTime","loanStarTime","loanEndTime");
		sb.append(serializer.exclude(
				new String[] { "class"}).serialize(
						articlecategory));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}

}
