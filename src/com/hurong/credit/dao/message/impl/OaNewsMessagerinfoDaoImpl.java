package com.hurong.credit.dao.message.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.message.OaNewsMessagerinfoDao;
import com.hurong.credit.model.message.OaNewsMessage;
import com.hurong.credit.model.message.OaNewsMessagerinfo;



/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class OaNewsMessagerinfoDaoImpl extends BaseDaoImpl<OaNewsMessagerinfo> implements OaNewsMessagerinfoDao{

	public OaNewsMessagerinfoDaoImpl() {
		super(OaNewsMessagerinfo.class);
	}


	@Override
	public List<OaNewsMessagerinfo> getAllInfo(PagingBean pb,HttpServletRequest request) {

		// TODO Auto-generated method stub
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
		 String messageId=request.getParameter("messageId");
		 if(messageId!=null&&!"".equals(messageId)){
			 hql=hql+" and p.messageId="+Long.valueOf(messageId);
		 }
		 Object userId=request.getAttribute("userId");
		 if(userId!=null&&!"".equals(userId)){
			 hql=hql+" and p.userId="+Long.valueOf(userId.toString());
		 }
		 String userName=request.getParameter("userName");
		 if(userName!=null&&!"".equals(userName)){
			 hql=hql+" and p.userName like'%"+userName+"%'";
		 }
		 Object id =request.getAttribute("id");
		 if(id!=null&&!"".equals(id)){
			 hql=hql+" and p.id="+Long.valueOf(id.toString());
		 }
		 hql=hql+" order by p.istop desc,p.isTopTime desc,o.sendTime desc  ";
		 System.out.println("hql=="+hql);
		 List<OaNewsMessagerinfo> listcount=this.getSession().createSQLQuery(hql).
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
		 pb.setTotalItems(listcount.size());
		 if(pb.getStart()!=null&&pb.getPageSize()!=null){
			 List<OaNewsMessagerinfo> list=this.getSession().createSQLQuery(hql).
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
										 setFirstResult(pb.getStart()).
										 setMaxResults(pb.getPageSize()).list();
			 return list;
		 }else{
			 return listcount;
		 }
	
	}


	@Override
	public List<OaNewsMessagerinfo> getInfo(HttpServletRequest request) {
		String sql = "SELECT "+
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
		Object userId=request.getAttribute("userId");
		if(userId!=null&&!"".equals(userId)){
			sql=sql+" and p.userId="+Long.valueOf(userId.toString());
		}
		sql=sql+" ORDER BY p.readStatus ASC  ";
		System.out.println("sql=="+sql);
		List<OaNewsMessagerinfo> listcount=this.getSession().createSQLQuery(sql).
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
		return listcount;
	}

	@Override
	public int getByStateCount(int userId, int status) {
		String hql="SELECT COUNT(om.id) AS messageNumber FROM oa_news_messagerinfo AS om where om.readStatus=? and om.status=2 and om.userId=?";
		Integer messageNumber=((BigInteger)this.getSession().createSQLQuery(hql).setParameter(0, status).setParameter(1, userId).uniqueResult()).intValue();
		return messageNumber;
	}

}