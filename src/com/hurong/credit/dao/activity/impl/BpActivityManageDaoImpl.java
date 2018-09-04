package com.hurong.credit.dao.activity.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PageBean;
import com.hurong.credit.dao.activity.BpActivityManageDao;
import com.hurong.credit.model.activity.BpActivityManage;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpActivityManageDaoImpl extends BaseDaoImpl<BpActivityManage> implements BpActivityManageDao{

	public BpActivityManageDaoImpl() {
		super(BpActivityManage.class);
	}

	@Override
	public List<BpActivityManage> findActivityNumber(String flag) {
		String hql="";
		if("3".equals(flag)){
			hql ="from BpActivityManage sl where sl.flag=? ";
		}else{
			hql ="from BpActivityManage sl where sl.flag=? and sl.createDate=now()";
		}
		return  this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0,Integer.valueOf(flag)).list();
	}

	@Override
	public List<BpActivityManage> findExistCrossDate(BpActivityManage bpActivityManage) {
		String hql =" from BpActivityManage sl where sl.flag=? and sl.sendType=?  and ? BETWEEN sl.activityStartDate and sl.activityEndDate "
				   +" or ? BETWEEN sl.activityStartDate and sl.activityEndDate ";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
		.setParameter(0,bpActivityManage.getFlag())
		.setParameter(1,bpActivityManage.getSendType())
		.setParameter(2,bpActivityManage.getActivityStartDate())
		.setParameter(3,bpActivityManage.getActivityEndDate())
		.list();
	}

	@Override
	public void findList(PageBean<BpActivityManage> pageBean) {
		/*--------查询总条数---------*/
		StringBuffer totalCounts = new StringBuffer ("select count(*) from ( ");
		StringBuffer sql =new StringBuffer(" select activityId,status,activityStartDate,activityEndDate,flag,needIntegral, "
				   +" activityExplain,parValue,money,couponStartDate,validNumber,createrId,createDate,activityNumber, "
				   +" (select dic.itemValue from dictionary as dic  where dic.dicId=manage.sendType) as sendTypeValue, "
				   +" (select dic.itemValue from dictionary as dic  where dic.dicId=manage.referenceUnit) as referenceUnitValue,"
				   +" (select dic.itemValue from dictionary as dic  where dic.dicId=manage.couponType) as couponTypeValue, "
				   +" (select custLevel.levelName from web_bonus_custLevel_setting as custLevel  where custLevel.levelId=manage.levelId) as levelValue, "
				   +" (select u.fullname from app_user as u where u.userId=manage.createrId) as createrValue "
				   +" from bp_activity_manage as manage where 1=1 ");
		HttpServletRequest request = pageBean.getRequest();
		String flag=request.getParameter("flag");
		String couponType=request.getParameter("couponType");
		String status=request.getParameter("status");
		String sendType=request.getParameter("sendType");
		String activityNumber=request.getParameter("activityNumber");
		String activityExplain=request.getParameter("activityExplain");
		
		if(null!=flag && !"".equals(flag)){
			sql.append(" and manage.flag=").append(flag);
		}
		if(null!=couponType && !"".equals(couponType)){
			sql.append(" and manage.couponType=").append(couponType);
		}
		if(null!=status && !"".equals(status)){
			sql.append(" and manage.status=").append(status);
		}
		if(null!=sendType && !"".equals(sendType)){
			sql.append(" and manage.sendType=").append(sendType);
		}
		if(null!=activityNumber && !"".equals(activityNumber)){
			sql.append(" and manage.activityNumber like '").append(activityNumber).append("'");
		}
		if(null!=activityExplain && !"".equals(activityExplain)){
			sql.append(" and manage.activityExplain like '").append(activityExplain).append("'");
		}
		
		totalCounts.append(sql).append(") as temp ");
		
		List list = this.getSession().createSQLQuery(sql.toString())
			.addScalar("activityId",Hibernate.LONG)
			.addScalar("status",Hibernate.INTEGER)
			.addScalar("activityStartDate",Hibernate.DATE)
			.addScalar("activityEndDate",Hibernate.DATE)
			.addScalar("activityExplain",Hibernate.STRING)
			.addScalar("parValue",Hibernate.BIG_DECIMAL)
			.addScalar("needIntegral",Hibernate.INTEGER)
			.addScalar("money",Hibernate.LONG)
			.addScalar("couponStartDate",Hibernate.DATE)
			.addScalar("validNumber",Hibernate.INTEGER)
			.addScalar("flag",Hibernate.INTEGER)
			.addScalar("activityNumber",Hibernate.STRING)
			.addScalar("createDate",Hibernate.DATE)
			.addScalar("levelValue",Hibernate.STRING)
			.addScalar("createrValue",Hibernate.STRING)
			.addScalar("sendTypeValue",Hibernate.STRING)
			.addScalar("couponTypeValue",Hibernate.STRING)
			.addScalar("referenceUnitValue",Hibernate.STRING)
			.setResultTransformer(Transformers.aliasToBean(BpActivityManage.class)).setFirstResult(pageBean.getStart()).setMaxResults(pageBean.getLimit()).list();
			
		pageBean.setResult(list);
		
		BigInteger total =   (BigInteger) this.getSession().createSQLQuery(totalCounts.toString()).uniqueResult();
		pageBean.setTotalCounts(total.intValue());
		this.releaseSession(getSession());
	}
	
	
	@Override
	public List<BpActivityManage> listActivity(Long activityType) {
		
		String hql = " from BpActivityManage where status = 0 and  sendType = "+activityType+"  and activityEndDate >= '"+DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd")+"' ";
		return findByHql(hql);
		
	}

	@Override
	public List<BpActivityManage> showMyCoupons(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BpActivityManage> list = new ArrayList<BpActivityManage>();
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String sql = "select * from bp_activity_manage manage where manage.status = 0 and manage.flag = 3 ";//and manage.activityEndDate > '"+sdf.format(d)+"'    
		System.out.println(">>>><<<<"+sql);
		//默认分页十条每页
		if(request.getParameter("tab")!=null){
			list =  this.getSession().createSQLQuery(sql).addEntity(BpActivityManage.class).setFirstResult(0).setMaxResults(4).list();
		}else{
			list =  this.getSession().createSQLQuery(sql).addEntity(BpActivityManage.class).setFirstResult(0).setMaxResults(10).list();
		}
		return list;
	}
}