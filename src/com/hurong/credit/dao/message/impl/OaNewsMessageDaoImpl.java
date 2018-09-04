package com.hurong.credit.dao.message.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.message.OaNewsMessageDao;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class OaNewsMessageDaoImpl extends BaseDaoImpl<OaNewsMessage> implements OaNewsMessageDao{

	public OaNewsMessageDaoImpl() {
		super(OaNewsMessage.class);
	}

	/**
	 * 获取单个用户的所有站内信
	 * userId 用户ID
	 * startpage 首行
	 * pagesize 每页数量
	 */
	@Override
	public List<OaNewsMessage> getUserAll(Long userId,int startpage,int pagesize,int status1,int status2) {
		List<OaNewsMessage> list=null;
		StringBuffer hql = new StringBuffer(" from OaNewsMessage ");
		hql.append(" where (isDelete!=1 or isDelete is null) and recipient= ").append(userId);
		hql.append(" and status=").append(status1).append(" or status=").append(status2);
		 Query query = getSession().createQuery(hql.toString()).setFirstResult(startpage).setMaxResults(pagesize);		 
		return query.list();
	}

	@Override
	public int getStatusNum(int userId, int status) {
		String hql ="select * from oa_news_message o where o.recipient="+Long.valueOf(String.valueOf(userId))+" and o.status=0 and o.isDelete=0";
		List<OaNewsMessage> list = this.getSession().createSQLQuery(hql).addEntity(OaNewsMessage.class).list();
		if(list!=null&&list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}

	@Override
	public BpPersonCenterData queryOaNews(Long userId) {
		// TODO Auto-generated method stub
		String sql = "SELECT a.readNums AS readNums, b.notReadNums AS notReadNums FROM ( SELECT COUNT(*) as readNums FROM `oa_news_messagerinfo` AS p LEFT JOIN oa_news_message AS o ON (o.id = p.messageId) WHERE p.`status` != 1 AND p.userId = '"+userId+"' and p.readStatus = 1"+
					") AS a,("+
		             " SELECT COUNT(*) as notReadNums  FROM `oa_news_messagerinfo` AS p LEFT JOIN oa_news_message AS o ON (o.id = p.messageId) WHERE p.`status` != 1 AND p.userId = '"+userId+"' and p.readStatus = 0"+
		             ") as b ";
		
		 BpPersonCenterData data = new BpPersonCenterData();
		 
		 data = (BpPersonCenterData) this.getSession().createSQLQuery(sql).
		 								addScalar("readNums",Hibernate.INTEGER).
		 								addScalar("notReadNums",Hibernate.INTEGER).
		 								setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).
		 								uniqueResult();
		return data;
	}

	@Override
	public List<OaNewsMessagerinfo> getAllInfo(HttpServletRequest request,
			BpCustMember member) {
		List<OaNewsMessagerinfo> list = new ArrayList<OaNewsMessagerinfo>();
				String hql="SELECT "+
				"p.id as id, "+
				"p.userId as userId, "+
				"p.userType as userType, "+
				"p.userName as userName, "+
				"p.`status` as `status`, "+
				"p.readStatus as readStatus, "+
				"p.readTime as readTime, "+
				"p.istop as istop, "+
				"p.isTopTime as isTopTime, "+
				"o.title as title, "+
				"o.content as content, "+
				"o.operator as operator, "+
				"o.addresser as addresser, "+
				"o.sendTime as sendTime, "+
				"o.typename as typename "+
			"FROM "+
				"`oa_news_messagerinfo` AS p "+
				"LEFT JOIN oa_news_message AS o ON (o.id = p.messageId) where p.`status`!=1";
		if(member.getId()!=null&&!"".equals(member.getId())){
			hql=hql+" and p.userId="+member.getId();
		}
		if(member.getLoginname()!=null&&!"".equals(member.getTruename())){
			hql=hql+" and p.userName like'%"+member.getTruename()+"%'";
		}
		
		hql=hql+" order by p.istop desc,p.isTopTime desc,o.sendTime desc  ";
		System.out.println("hql=="+hql);
		if(request!=null){
			if(request.getParameter("start")!=null&&!"".equals(request.getParameter("start"))&&request.getParameter("limit")!=null&&!"".equals(request.getParameter("limit"))){
				Integer start = Integer.valueOf(request.getParameter("start").toString());
				Integer limit = Integer.valueOf(request.getParameter("limit").toString());
				 list=this.getSession().createSQLQuery(hql).
					addScalar("id", Hibernate.LONG).
					addScalar("userType", Hibernate.STRING).
					addScalar("userName", Hibernate.STRING).
					addScalar("status", Hibernate.INTEGER).
					addScalar("readStatus", Hibernate.INTEGER).
					addScalar("readTime", Hibernate.TIMESTAMP).
					addScalar("istop", Hibernate.INTEGER).
					addScalar("isTopTime", Hibernate.TIMESTAMP).
					addScalar("title", Hibernate.STRING).
					addScalar("content", Hibernate.STRING).
					addScalar("operator", Hibernate.STRING).
					addScalar("addresser", Hibernate.STRING).
					addScalar("sendTime", Hibernate.TIMESTAMP).
					addScalar("typename", Hibernate.STRING).
					setResultTransformer(Transformers.aliasToBean(OaNewsMessagerinfo.class)).
					setFirstResult(start).setMaxResults(limit).
					list();
			}else{
				 list=this.getSession().createSQLQuery(hql).
					addScalar("id", Hibernate.LONG).
					addScalar("userType", Hibernate.STRING).
					addScalar("userName", Hibernate.STRING).
					addScalar("status", Hibernate.INTEGER).
					addScalar("readStatus", Hibernate.INTEGER).
					addScalar("readTime", Hibernate.TIMESTAMP).
					addScalar("istop", Hibernate.INTEGER).
					addScalar("isTopTime", Hibernate.TIMESTAMP).
					addScalar("title", Hibernate.STRING).
					addScalar("content", Hibernate.STRING).
					addScalar("operator", Hibernate.STRING).
					addScalar("addresser", Hibernate.STRING).
					addScalar("sendTime", Hibernate.TIMESTAMP).
					addScalar("typename", Hibernate.STRING).
					setResultTransformer(Transformers.aliasToBean(OaNewsMessagerinfo.class)).
					list();
			}
		}else{
			 list=this.getSession().createSQLQuery(hql).
				addScalar("id", Hibernate.LONG).
				addScalar("userType", Hibernate.STRING).
				addScalar("userName", Hibernate.STRING).
				addScalar("status", Hibernate.INTEGER).
				addScalar("readStatus", Hibernate.INTEGER).
				addScalar("readTime", Hibernate.TIMESTAMP).
				addScalar("istop", Hibernate.INTEGER).
				addScalar("isTopTime", Hibernate.TIMESTAMP).
				addScalar("title", Hibernate.STRING).
				addScalar("content", Hibernate.STRING).
				addScalar("operator", Hibernate.STRING).
				addScalar("addresser", Hibernate.STRING).
				addScalar("sendTime", Hibernate.TIMESTAMP).
				addScalar("typename", Hibernate.STRING).
				setResultTransformer(Transformers.aliasToBean(OaNewsMessagerinfo.class)).
				list();
		}
		return list;
	}

}