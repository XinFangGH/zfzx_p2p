package com.hurong.credit.dao.coupon.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.coupon.BpCouponsDao;
import com.hurong.credit.model.coupon.BpCoupons;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.user.BpCustMember;


/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpCouponsDaoImpl extends BaseDaoImpl<BpCoupons> implements BpCouponsDao{

	public BpCouponsDaoImpl() {
		super(BpCoupons.class);
	}

	@Override
	public void saveList(List<BpCoupons> list) {
		 Session session =this.getSessionFactory().openSession();
		    //开始事务
	    Transaction tx = session.beginTransaction();
	    int count=0;
	    for(BpCoupons f:list){
	    	session.save(f);
	    	  if ( ++count % 500 == 0 ){
	              session.flush();
	              session.clear();
	          }
	      }
	    tx.commit();
	    session.close();
		
	}

	/**
	 * 查询出来未派发的优惠券
	 */
	@Override
	public List<BpCoupons> listForNotDistributeCoupon(
			HttpServletRequest request, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		String hql="SELECT "+
						"bcou.couponId as couponId, "+
						"bcou.couponTypeValue as couponTypeValue, "+
						"bcou.couponNumber as couponNumber, "+
						"bcou.couponValue as couponValue, "+
						"bcou.couponStartDate as couponStartDate, "+
						"bcou.couponEndDate as couponEndDate, "+
						"bcou.couponStatus as couponStatus, "+
						"bcou.createDate as createDate, "+
						"bcou.couponResourceType as couponResourceType, "+
						"bcou.resourceId as resourceId, "+
						"IFNULL(setting.couponDescribe,activity.activityExplain) as resourceName "+
				   "FROM "+
				   		"`bp_coupons` AS bcou "+
				   "LEFT JOIN bp_coupon_setting AS setting ON ( "+
				   		"bcou.resourceId = setting.categoryId "+
				   		"AND bcou.couponResourceType = 'couponResourceType_normal' "+
				   		") "+
				   "left join bp_activity_manage as activity on( "+
				   		"bcou.resourceId = activity.activityId "+
				   		"AND bcou.couponResourceType = 'couponResourceType_active' "+
				   		") " +
				   "where " +
				   		"bcou.belongUserId is NULL and " +
				   		"bcou.couponStatus<=4 "; 
		if(request!=null){//根据request中的查询条件来设置查询限制
			String couponType=request.getParameter("couponType");//优惠券类型
			if(couponType!=null&&!"".equals(couponType)){
				hql=hql+" and (setting.couponType='"+couponType+"' )";
			}
			String couponstatus=request.getParameter("couponstatus");//优惠券状态
			if(couponstatus!=null&&!"".equals(couponstatus)){
				hql=hql+" and bcou.couponstatus="+Short.valueOf(couponstatus);
			}
			
			String resourceName=request.getParameter("resourceName");//优惠券来源描述
			if(resourceName!=null&&!"".equals(resourceName)){
				hql=hql+" and (setting.couponDescribe like '%"+resourceName+"%' or activity.activityExplain like '%"+resourceName+"%')";
			}
			
			String couponNumber=request.getParameter("couponNumber");//优惠券编号
			if(couponNumber!=null&&!"".equals(couponNumber)){
				hql=hql+" and bcou.couponstatus like '%"+couponNumber+"%'";
			}
			String couponEndDate_S=request.getParameter("couponEndDate_S");//优惠券过期日查询开始日期
			if(couponEndDate_S!=null&&!"".equals(couponEndDate_S)){
				hql=hql+" and bcou.couponEndDate >= '"+couponEndDate_S+"'";
			}
			String couponEndDate_E=request.getParameter("couponEndDate_E");//优惠券过期日查询结束日期
			if(couponEndDate_E!=null&&!"".equals(couponEndDate_E)){
				hql=hql+" and bcou.couponEndDate <= '"+couponEndDate_E+"'";
			}
		}
		
		if(start!=null&&limit!=null){
			hql=hql+"limit "+start+","+(start+limit);
			List<BpCoupons> list = this.jdbcTemplate.query(hql,new rowMapperb());
			return list;
		}else{
			List<BpCoupons> list = this.jdbcTemplate.query(hql,new rowMapperb());
			  return list;
		}
		
	}
	/**
	 * 未派发的展示实体
	 * @author Administrator
	 *
	 */
	class  rowMapperb implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			BpCoupons income = new BpCoupons();
			income.setCouponId(rs.getLong("couponId"));
			income.setCouponTypeValue(rs.getString("couponTypeValue"));
			income.setCouponNumber(rs.getString("couponNumber"));
			income.setCouponValue(rs.getBigDecimal("couponValue"));
			income.setCouponStartDate(rs.getDate("couponStartDate"));
			income.setCouponEndDate(rs.getDate("couponEndDate"));
			income.setCouponStatus(rs.getShort("couponStatus"));
			income.setCreateDate(rs.getDate("createDate"));
			income.setResourceName(rs.getString("resourceName"));
			income.setResourceId(rs.getLong("resourceId"));
			income.setCouponResourceType(rs.getString("couponResourceType"));
			return income;
		}
		
	}
	/**
	 * 查询出来已经派发的优惠券
	 */
	@Override
	public List<BpCoupons> bouponBelongList(HttpServletRequest request,
			Integer start, Integer limit) {
			String hql="SELECT "+
				"bcou.couponId as couponId, "+
				"bcou.couponTypeValue as couponTypeValue, "+
				"bcou.couponNumber as couponNumber, "+
				"bcou.couponValue as couponValue, "+
				"bcou.couponStartDate as couponStartDate, "+
				"bcou.couponEndDate as couponEndDate, "+
				"bcou.couponStatus as couponStatus, "+
				"bcou.belongUserName as belongUserName, "+
				"bcou.bindOpraterDate as bindOpraterDate, "+
				"bcou.useProjectName as useProjectName, "+
				"bcou.useProjectNumber as useProjectNumber, "+
				"bcou.useProjectId as useProjectId, "+
				"bcou.useProjectType as useProjectType, "+
				"bcou.useTime as useTime, "+
				"bcou.couponResourceType as couponResourceType, "+
				"bcou.resourceId as resourceId, "+
				"bcou.createDate as createDate, "+
				"activity.activityNumber as activityNumber, "+
				"IFNULL(setting.couponDescribe,activity.activityExplain) as resourceName "+
		   "FROM "+
		   		"`bp_coupons` AS bcou "+
		   "LEFT JOIN bp_coupon_setting AS setting ON ( "+
		   		"bcou.resourceId = setting.categoryId "+
		   		"AND bcou.couponResourceType = 'couponResourceType_normal' "+
		   		") "+
		   "left join bp_activity_manage as activity on( "+
		   		"bcou.resourceId = activity.activityId "+
		   		"AND bcou.couponResourceType = 'couponResourceType_active' "+
		   		") " +
		   "where " +
		   		"bcou.belongUserId is NOT NULL and " +
		   		"bcou.couponStatus>=4 "; 
		if(request!=null){//根据request中的查询条件来设置查询限制
			String couponType=request.getParameter("couponType");//优惠券类型
			if(couponType!=null&&!"".equals(couponType)){
				hql=hql+" and (setting.couponType='"+couponType+"' )";
			}
			String couponstatus=request.getParameter("couponstatus");//优惠券状态
			if(couponstatus!=null&&!"".equals(couponstatus)){
				hql=hql+" and bcou.couponstatus="+Short.valueOf(couponstatus);
			}
			
			String resourceName=request.getParameter("resourceName");//优惠券来源描述
			if(resourceName!=null&&!"".equals(resourceName)){
				hql=hql+" and (setting.couponDescribe like '%"+resourceName+"%' or activity.activityExplain like '%"+resourceName+"%')";
			}
			
			String couponNumber=request.getParameter("couponNumber");//优惠券编号
			if(couponNumber!=null&&!"".equals(couponNumber)){
				hql=hql+" and bcou.couponstatus like '%"+couponNumber+"%'";
			}
			String belongUserName=request.getParameter("belongUserName");//投资人姓名
			if(belongUserName!=null&&!"".equals(belongUserName)){
				hql=hql+" and bcou.belongUserName like '%"+belongUserName+"%'";
			}
			String activityNumber=request.getParameter("activityNumber");//活动编号
			if(activityNumber!=null&&!"".equals(activityNumber)){
				hql=hql+" and activity.activityNumber like '%"+activityNumber+"%'";
			}
			String useProjectNumber=request.getParameter("useProjectNumber");//投资项目编号
			if(useProjectNumber!=null&&!"".equals(useProjectNumber)){
				hql=hql+" and bcou.useProjectNumber like '%"+useProjectNumber+"%'";
			}
		}
		System.out.println(hql);
		if(start!=null&&limit!=null){
			hql=hql+"limit "+start+","+(start+limit);
			List<BpCoupons> list = this.jdbcTemplate.query(hql,new rowMapperbDistribute());
			return list;
		}else{
			List<BpCoupons> list = this.jdbcTemplate.query(hql,new rowMapperbDistribute());
			return list;
		}
	}
	/**
	 * 已派发的展示实体
	 * @author Administrator
	 *
	 */
	class  rowMapperbDistribute implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			BpCoupons income = new BpCoupons();
			income.setCouponId(rs.getLong("couponId"));
			income.setCouponTypeValue(rs.getString("couponTypeValue"));
			income.setCouponNumber(rs.getString("couponNumber"));
			income.setCouponValue(rs.getBigDecimal("couponValue"));
			income.setCouponStartDate(rs.getDate("couponStartDate"));
			income.setCouponEndDate(rs.getDate("couponEndDate"));
			income.setCouponStatus(rs.getShort("couponStatus"));
			income.setCreateDate(rs.getDate("createDate"));
			income.setResourceName(rs.getString("resourceName"));
			income.setResourceId(rs.getLong("resourceId"));
			income.setCouponResourceType(rs.getString("couponResourceType"));
			income.setBelongUserName(rs.getString("belongUserName"));
			income.setBindOpraterDate(rs.getDate("bindOpraterDate"));
			income.setUseProjectType(rs.getString("useProjectType"));
			income.setUseProjectId(rs.getLong("useProjectId"));
			income.setUseProjectName(rs.getString("useProjectName"));
			income.setUseProjectNumber(rs.getString("useProjectNumber"));
			income.setUseTime(rs.getDate("useTime"));
			income.setActivityNumber(rs.getString("activityNumber"));
			return income;
		}
		
	}
	@Override
	public List<BpCoupons> getActivityType(String Type, Long activityId,
			String bpCustMemberId) {
		String sql = "from BpCoupons where couponResourceType=? and resourceId=? and belongUserId=?";
		return this.getSession().createQuery(sql).setParameter(0, Type).setParameter(1, activityId).setParameter(2, Long.valueOf(bpCustMemberId)).list();
	}
	//优惠券查询
	@Override
	public BpPersonCenterData getCoupon(Long id){
		String sql="select COUNT(*)as coupons from bp_coupons bc where bc.couponStatus in(5) and bc.belongUserId="+id;// and bc.couponEndDate>Date(NOW())
		System.out.println("查询优惠券的sql："+sql);
		BpPersonCenterData data = new BpPersonCenterData();
		data = (BpPersonCenterData) this.getSession().createSQLQuery(sql).
		 								addScalar("coupons",Hibernate.INTEGER).
		 								setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).
		 								uniqueResult();
		return data;
	}

	@Override
	public List<BpCoupons> listCouponsNew(BpCustMember member,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BpCoupons> list = new ArrayList<BpCoupons>();
		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date d= new Date();
		String time =sdf1.format(d).toString();	
		StringBuffer buffer = new StringBuffer("select * from bp_coupons coupons where coupons.belongUserId = "+member.getId());
		String couponStatus = request.getParameter("couponStatus");
		String couponType = request.getParameter("couponType");
		String mycouponsType = request.getParameter("mycouponsType");

		if(couponStatus!=null&&!couponStatus.equals("")){
			if(mycouponsType!=null&&!mycouponsType.equals("")&&mycouponsType.equals("4")){//查询加息券
				buffer.append(" and coupons.couponType = 3");
				buffer.append(" and coupons.couponStatus = "+couponStatus);
			}else if(couponStatus!=null&&!couponStatus.equals("")&&couponStatus.equals("5")){
				buffer.append(" and coupons.couponStatus = "+couponStatus);
			}else{
				buffer.append(" and coupons.couponType = 1");
			}
			if(couponStatus!=null&&!couponStatus.equals("")&&couponStatus.equals("10")){
				buffer.append(" and coupons.couponStatus IN ('10','1') ");
			}
			
			if(Integer.valueOf(couponStatus)==4){
				buffer.append(" AND coupons.couponEndDate < 'Date(NOW())' ");
				buffer.append(" and coupons.couponStatus = "+couponStatus);
			}else if(Integer.valueOf(couponStatus)==5){
				buffer.append(" and coupons.couponStatus = "+couponStatus);
			}else if(Integer.valueOf(couponStatus)==10){
				
			}else{
				buffer.append(" and coupons.couponStartDate = '"+time+"'");
			}
		}

		if(null !=couponType && !"".equals(couponType)){
			if(Long.valueOf(couponType)==3){
				buffer.append(" and coupons.couponType in ('1','2')");
			}
		}
		buffer.append(" ORDER BY createDate DESC");
		System.out.println(">>"+buffer.toString());
		if(request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			Integer start = Integer.valueOf(request.getParameter("start").toString());
			Integer limit = Integer.valueOf(request.getParameter("limit").toString());
			list = this.getSession().createSQLQuery(buffer.toString()).addEntity(BpCoupons.class).
									setFirstResult(start).setMaxResults(limit).list();
		}else{
			list = this.getSession().createSQLQuery(buffer.toString()).addEntity(BpCoupons.class)
			.list();
		}
		return list;
	}

	@Override
	public List<BpCoupons> listCouponsNewNums(BpCustMember member,
			HttpServletRequest request) {
		List<BpCoupons> list = new ArrayList<BpCoupons>();
		SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date d= new Date();
		String time =sdf1.format(d).toString();	
		StringBuffer buffer = new StringBuffer("select * from bp_coupons coupons where coupons.belongUserId = "+member.getId());
		String couponStatus = request.getParameter("couponStatus");
		String couponType = request.getParameter("couponType");
		String mycouponsType = request.getParameter("mycouponsType");

		if(couponStatus!=null&&!couponStatus.equals("")){
			if(mycouponsType!=null&&!mycouponsType.equals("")&&mycouponsType.equals("4")){//查询加息券
				buffer.append(" and coupons.couponType = 3");
			}else{
				buffer.append(" and coupons.couponType = 1");
			}
			buffer.append(" and coupons.couponStatus = "+couponStatus);
			if(Integer.valueOf(couponStatus)==4){
				buffer.append(" AND coupons.couponEndDate < 'Date(NOW())' ");
			}else{
				buffer.append(" and coupons.couponStartDate = '"+time+"'");
			}
		}

		
		
		if(null !=couponType && !"".equals(couponType)){
			if(Long.valueOf(couponType)==3){
				buffer.append(" and coupons.couponType in ('1','2')");
			}
		}
		buffer.append(" ORDER BY createDate DESC");
		System.out.println(">>"+buffer.toString());
		list = this.getSession().createSQLQuery(buffer.toString()).addEntity(BpCoupons.class).list();
		return list;
	}
}