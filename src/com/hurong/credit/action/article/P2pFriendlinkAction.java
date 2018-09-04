package com.hurong.credit.action.article;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.BeanUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.model.article.P2pFriendlink;
import com.hurong.credit.service.article.P2pFriendlinkService;



/**
 * 
 * @author 
 *
 */
public class P2pFriendlinkAction extends BaseAction{
	@Resource
	private P2pFriendlinkService p2pFriendlinkService;
	private P2pFriendlink p2pFriendlink;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public P2pFriendlink getP2pFriendlink() {
		return p2pFriendlink;
	}

	public void setP2pFriendlink(P2pFriendlink p2pFriendlink) {
		this.p2pFriendlink = p2pFriendlink;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<P2pFriendlink> list= p2pFriendlinkService.getAll(filter);
		
		Type type=new TypeToken<List<P2pFriendlink>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
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
				p2pFriendlinkService.remove(new Long(id));
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
		P2pFriendlink p2pFriendlink=p2pFriendlinkService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(p2pFriendlink));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(p2pFriendlink.getId()==null){
			p2pFriendlink.setCreateDate(new Date());
			p2pFriendlink.setModifyDate(new Date());
			p2pFriendlinkService.save(p2pFriendlink);
		}else{
			P2pFriendlink orgP2pFriendlink=p2pFriendlinkService.get(p2pFriendlink.getId());
			try{
				BeanUtil.copyNotNullProperties(orgP2pFriendlink, p2pFriendlink);
				orgP2pFriendlink.setModifyDate(new Date());
				p2pFriendlinkService.save(orgP2pFriendlink);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
