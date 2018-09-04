package com.hurong.credit.dao.financeProduct.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.zhiweitime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.DateUtil;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.financeProduct.PlFinanceProductUserAccountInfoDao;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.financeProduct.PlFinanceProduct;
import com.hurong.credit.model.financeProduct.PlFinanceProductUserAccountInfo;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlFinanceProductUserAccountInfoDaoImpl extends BaseDaoImpl<PlFinanceProductUserAccountInfo> implements PlFinanceProductUserAccountInfoDao{

	public PlFinanceProductUserAccountInfoDaoImpl() {
		super(PlFinanceProductUserAccountInfo.class);
	}

	@Override
	public List<PlFinanceProductUserAccountInfo> getListByParamet(
			HttpServletRequest request, PagingBean pb) {
		// TODO Auto-generated method stub
		String sql=" SELECT "+
						"pinfo.id AS id, "+
						"p.id as accountId, "+
						"pinfo.userId AS userId, "+
						"p.userName AS userName, "+
						"p.userloginName AS userloginName, "+
						"pinfo.productId AS productId, "+
						"pfp.productName AS productName, "+
						"pinfo.dealDate AS dealDate, "+
						"pinfo.amont as amont, "+
						"pinfo.currentMoney as currentMoney, "+
						"pinfo.dealtype as dealtype, "+
						"pinfo.dealtypeName as dealtypeName, "+
						"pinfo.dealStatus as dealStatus, "+
						"pinfo.dealStatusName as dealStatusName, "+
						"pinfo.remark as remark, "+
						"pinfo.requestNo as requestNo, "+
						"pinfo.companyId as companyId "+
					"FROM "+
						"pl_finance_product_useraccountinfo AS pinfo "+
					"left join pl_finance_product as pfp on(pinfo.productId=pfp.id) "+
					"LEFT JOIN `pl_finance_product_useraccount` AS p ON (p.userId = pinfo.userId and p.productId=pinfo.productId )  where 1=1 ";
		if(request!=null){
			//用来查询交易类型
			String dealtype=(String) (request.getParameter("dealtype")!=null?request.getParameter("dealtype"):request.getAttribute("dealtype"));
			if(dealtype!=null&&!"".equals(dealtype)){
				sql=sql+" and pinfo.dealtype='"+dealtype+"'";
			}
			String dealStatus=(String) (request.getParameter("dealStatus")!=null?request.getParameter("dealStatus"):request.getAttribute("dealStatus"));
			//用来查询交易状态
			if(dealStatus!=null&&!"".equals(dealStatus)){
				sql=sql+" and pinfo.dealStatus="+Short.valueOf(dealStatus);
			}
			//查询交易开始日期
			String dealDateS=request.getParameter("dealDateS");
	    	if(dealDateS!=null&&!"".equals(dealDateS)){
	    		sql=sql+" and pinfo.dealDate>='"+dealDateS+" 00:00:00' ";
	    	}
	    	//查询交易开结束日期
	    	String dealDateE=request.getParameter("dealDateE");
	    	if(dealDateE!=null&&!"".equals(dealDateE)){
	    		sql=sql+" and pinfo.dealDate<='"+dealDateE+" 23:59:59' ";
	    	}
	    	//查询交易用户姓名
	    	String userName=request.getParameter("userName");
	    	if(userName!=null&&!"".equals(userName)){
	    		sql=sql+" and p.userName like \"%"+userName+"%\" ";
	    	}
	    	//查询交易用户登陆名
	    	String loginName=request.getParameter("loginName");
	    	if(loginName!=null&&!"".equals(loginName)){
	    		sql=sql+" and p.userloginName like \"%"+loginName+"%\" ";
	    	}
		}
		sql=sql+" ORDER BY pinfo.dealDate DESC";
		
		System.out.println(sql);
	     List<PlFinanceProductUserAccountInfo> list=this.getSession().createSQLQuery(sql).
	     										addScalar("id",Hibernate.LONG).
	     										addScalar("accountId",Hibernate.LONG).
	     										addScalar("userId", Hibernate.LONG).
	     										addScalar("userName", Hibernate.STRING).
	     										addScalar("userloginName", Hibernate.STRING).
	     										addScalar("productId", Hibernate.LONG).
	     										addScalar("productName", Hibernate.STRING).
	     										addScalar("dealDate", Hibernate.TIMESTAMP).
	     										addScalar("amont", Hibernate.BIG_DECIMAL).
	     										addScalar("currentMoney", Hibernate.BIG_DECIMAL).
	     										addScalar("dealtype", Hibernate.STRING).
	     										addScalar("dealtypeName", Hibernate.STRING).
	     										addScalar("dealStatus", Hibernate.SHORT).
	     										addScalar("dealStatusName", Hibernate.STRING).
	     										addScalar("remark", Hibernate.STRING).
	     										addScalar("requestNo", Hibernate.STRING).
	     										addScalar("companyId", Hibernate.LONG).
	     										setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
	     									    list();
	     if(pb!=null&&pb.getStart()!=null&&pb.getPageSize()!=null){
	    	 pb.setTotalItems(list!=null?list.size():0);
	    	 List<PlFinanceProductUserAccountInfo> listP=this.getSession().createSQLQuery(sql).
			    	 	addScalar("id",Hibernate.LONG).
						addScalar("accountId",Hibernate.LONG).
						addScalar("userId", Hibernate.LONG).
						addScalar("userName", Hibernate.STRING).
						addScalar("userloginName", Hibernate.STRING).
						addScalar("productId", Hibernate.LONG).
						addScalar("productName", Hibernate.STRING).
						addScalar("dealDate", Hibernate.TIMESTAMP).
						addScalar("amont", Hibernate.BIG_DECIMAL).
						addScalar("currentMoney", Hibernate.BIG_DECIMAL).
						addScalar("dealtype", Hibernate.STRING).
						addScalar("dealtypeName", Hibernate.STRING).
						addScalar("dealStatus", Hibernate.SHORT).
						addScalar("dealStatusName", Hibernate.STRING).
						addScalar("remark", Hibernate.STRING).
						addScalar("requestNo", Hibernate.STRING).
						addScalar("companyId", Hibernate.LONG).
						setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
						setFirstResult(pb.getStart()).
						setMaxResults(pb.getPageSize()).
						list();
	    	 return listP; 
	     }else{
	    	 return list; 
	     }
	}

	@Override
	public void updateAccountInfo() {
		try{
			String firstDay=DateUtil.getFirstDate(new Date(), "first");
			String endDay=DateUtil.getFirstDate(new Date(), "end");
			String sql="SELECT  p.*  FROM  pl_finance_product_useraccountinfo AS p LEFT JOIN pl_finance_product AS pfp ON (p.productId = pfp.id) WHERE 	p.dealtype = 1"+
						" AND p.dealStatus = 1 AND p.dealDate BETWEEN DATE_ADD( '"+firstDay+"', INTERVAL - (pfp.intestModel) DAY ) and DATE_ADD( '"+endDay+"', INTERVAL - (pfp.intestModel) DAY )";
			 List<PlFinanceProductUserAccountInfo> listP=this.getSession().createSQLQuery(sql).
				setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
				list();
			 
			 if(listP!=null){
				 for(PlFinanceProductUserAccountInfo temp :listP){
					 temp.setDealStatus(Short.valueOf("2"));
					 temp.setDealStatusName("交易成功");
					 temp.setRemark("由在途金额转为计息金额");
					 this.save(temp);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<PlFinanceProductUserAccountInfo> getPersonList(HttpServletRequest request,Long id,int start,
			Integer limit) {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer(" SELECT "+
			"pinfo.id AS id, "+
			"p.id as accountId, "+
			"pinfo.userId AS userId, "+
			"p.userName AS userName, "+
			"p.userloginName AS userloginName, "+
			"pinfo.productId AS productId, "+
			"pfp.productName AS productName, "+
			"pinfo.dealDate AS dealDate, "+
			"pinfo.amont as amont, "+
			"pinfo.currentMoney as currentMoney, "+
			"pinfo.dealtype as dealtype, "+
			"pinfo.dealtypeName as dealtypeName, "+
			"pinfo.dealStatus as dealStatus, "+
			"pinfo.dealStatusName as dealStatusName, "+
			"pinfo.remark as remark, "+
			"pinfo.requestNo as requestNo, "+
			"pinfo.companyId as companyId "+
		"FROM "+
			"pl_finance_product_useraccountinfo AS pinfo "+
		"left join pl_finance_product as pfp on(pinfo.productId=pfp.id) "+
		"LEFT JOIN `pl_finance_product_useraccount` AS p ON (p.userId = pinfo.userId and p.productId=pinfo.productId )  where pinfo.dealStatus is not NULL and pinfo.userId="+id);
		
		 if(request.getParameter("type")!=null&&!"".equals(request.getParameter("type"))){
			 Integer type = Integer.valueOf(request.getParameter("type").toString());
			 switch(type){
			    case 0:
			    	sql.append(" and pinfo.dealtypeName = '收益'");
			    	break;
			 	case 1:
			 		sql.append(" and pinfo.dealtypeName = '转入'");
			 		break;
			 	case 2:
			 		sql.append(" and pinfo.dealtypeName = '转出'");
			 		break;
			 	default:
			 		sql.append(" and pinfo.dealtypeName = '收益'");
			 }
			 
		}

		
		String startTime=request.getParameter("startTime");
		if(null != startTime && !"".equals(startTime)){
			sql.append(" and pinfo.dealDate >='"+startTime+" 00:00:00'");
		}
		String endTime=request.getParameter("endTime");
		if(null != endTime && !"".equals(endTime)){
			sql.append(" and pinfo.dealDate <='"+endTime+" 23:59:59'");
		}
		sql.append(" ORDER BY pinfo.dealDate DESC");
		
	//	System.out.println(sql);
		if(limit!=null){
			List<PlFinanceProductUserAccountInfo> list=this.getSession().createSQLQuery(sql.toString()).
			addScalar("id",Hibernate.LONG).
			addScalar("accountId",Hibernate.LONG).
			addScalar("userId", Hibernate.LONG).
			addScalar("userName", Hibernate.STRING).
			addScalar("userloginName", Hibernate.STRING).
			addScalar("productId", Hibernate.LONG).
			addScalar("productName", Hibernate.STRING).
			addScalar("dealDate", Hibernate.TIMESTAMP).
			addScalar("amont", Hibernate.BIG_DECIMAL).
			addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			addScalar("dealtype", Hibernate.STRING).
			addScalar("dealtypeName", Hibernate.STRING).
			addScalar("dealStatus", Hibernate.SHORT).
			addScalar("dealStatusName", Hibernate.STRING).
			addScalar("remark", Hibernate.STRING).
			addScalar("requestNo", Hibernate.STRING).
			addScalar("companyId", Hibernate.LONG).
			setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
			setFirstResult(start).setMaxResults(limit).
			list();

			return list; 
		}else{
			List<PlFinanceProductUserAccountInfo> list=this.getSession().createSQLQuery(sql.toString()).
			addScalar("id",Hibernate.LONG).
			addScalar("accountId",Hibernate.LONG).
			addScalar("userId", Hibernate.LONG).
			addScalar("userName", Hibernate.STRING).
			addScalar("userloginName", Hibernate.STRING).
			addScalar("productId", Hibernate.LONG).
			addScalar("productName", Hibernate.STRING).
			addScalar("dealDate", Hibernate.TIMESTAMP).
			addScalar("amont", Hibernate.BIG_DECIMAL).
			addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			addScalar("dealtype", Hibernate.STRING).
			addScalar("dealtypeName", Hibernate.STRING).
			addScalar("dealStatus", Hibernate.SHORT).
			addScalar("dealStatusName", Hibernate.STRING).
			addScalar("remark", Hibernate.STRING).
			addScalar("requestNo", Hibernate.STRING).
			addScalar("companyId", Hibernate.LONG).
			setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
			list();

			return list; 
		}
		
	}
	
	@Override
	public List<PlFinanceProductUserAccountInfo> getPersonList1(HttpServletRequest request,Long id) {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer(" SELECT "+
			"pinfo.id AS id, "+
			"p.id as accountId, "+
			"pinfo.userId AS userId, "+
			"p.userName AS userName, "+
			"p.userloginName AS userloginName, "+
			"pinfo.productId AS productId, "+
			"pfp.productName AS productName, "+
			"pinfo.dealDate AS dealDate, "+
			"pinfo.amont as amont, "+
			"pinfo.currentMoney as currentMoney, "+
			"pinfo.dealtype as dealtype, "+
			"pinfo.dealtypeName as dealtypeName, "+
			"pinfo.dealStatus as dealStatus, "+
			"pinfo.dealStatusName as dealStatusName, "+
			"pinfo.remark as remark, "+
			"pinfo.requestNo as requestNo, "+
			"pinfo.companyId as companyId "+
		"FROM "+
			"pl_finance_product_useraccountinfo AS pinfo "+
		"left join pl_finance_product as pfp on(pinfo.productId=pfp.id) "+
		"LEFT JOIN `pl_finance_product_useraccount` AS p ON (p.userId = pinfo.userId and p.productId=pinfo.productId )  where pinfo.dealStatus is not NULL and pinfo.userId="+id);
		 if(request.getParameter("type")!=null&&!"".equals(request.getParameter("type"))){
			 Integer type = Integer.valueOf(request.getParameter("type").toString());
			 switch(type){
			    case 0:
			    	sql.append(" and pinfo.dealtypeName = '收益'");
			    	break;
			 	case 1:
			 		sql.append(" and pinfo.dealtypeName = '转入'");
			 		break;
			 	case 2:
			 		sql.append(" and pinfo.dealtypeName = '转出'");
			 		break;
			 	default:
			 		sql.append(" and pinfo.dealtypeName = '收益'");
			 }
			 
		}
		String startTime=request.getParameter("startTime");
		if(null != startTime && !"".equals(startTime)){
			sql.append(" and pinfo.dealDate >='"+startTime+" 00:00:00'");
		}
		String endTime=request.getParameter("endTime");
		if(null != endTime && !"".equals(endTime)){
			sql.append(" and pinfo.dealDate <='"+endTime+" 23:59:59'");
		}
		sql.append(" ORDER BY pinfo.dealDate DESC");
		System.out.println(sql);
		List<PlFinanceProductUserAccountInfo> list = new ArrayList<PlFinanceProductUserAccountInfo>();
		if(request!=null&&request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			String start = request.getParameter("start")!=null?request.getParameter("start").toString():"0";
			String limit = request.getParameter("limit")!=null?request.getParameter("limit"):"10";
		list = this.getSession().createSQLQuery(sql.toString()).
			addScalar("id",Hibernate.LONG).
			addScalar("accountId",Hibernate.LONG).
			addScalar("userId", Hibernate.LONG).
			addScalar("userName", Hibernate.STRING).
			addScalar("userloginName", Hibernate.STRING).
			addScalar("productId", Hibernate.LONG).
			addScalar("productName", Hibernate.STRING).
			addScalar("dealDate", Hibernate.TIMESTAMP).
			addScalar("amont", Hibernate.BIG_DECIMAL).
			addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			addScalar("dealtype", Hibernate.STRING).
			addScalar("dealtypeName", Hibernate.STRING).
			addScalar("dealStatus", Hibernate.SHORT).
			addScalar("dealStatusName", Hibernate.STRING).
			addScalar("remark", Hibernate.STRING).
			addScalar("requestNo", Hibernate.STRING).
			addScalar("companyId", Hibernate.LONG).
			setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
			setFirstResult(Integer.valueOf(start)).setMaxResults(Integer.valueOf(limit)).list();
			return list; 
		}else{
			list=this.getSession().createSQLQuery(sql.toString()).
			addScalar("id",Hibernate.LONG).
			addScalar("accountId",Hibernate.LONG).
			addScalar("userId", Hibernate.LONG).
			addScalar("userName", Hibernate.STRING).
			addScalar("userloginName", Hibernate.STRING).
			addScalar("productId", Hibernate.LONG).
			addScalar("productName", Hibernate.STRING).
			addScalar("dealDate", Hibernate.TIMESTAMP).
			addScalar("amont", Hibernate.BIG_DECIMAL).
			addScalar("currentMoney", Hibernate.BIG_DECIMAL).
			addScalar("dealtype", Hibernate.STRING).
			addScalar("dealtypeName", Hibernate.STRING).
			addScalar("dealStatus", Hibernate.SHORT).
			addScalar("dealStatusName", Hibernate.STRING).
			addScalar("remark", Hibernate.STRING).
			addScalar("requestNo", Hibernate.STRING).
			addScalar("companyId", Hibernate.LONG).
			setResultTransformer(Transformers.aliasToBean(PlFinanceProductUserAccountInfo.class)).
			list();
			return list; 
		}
		
	}

	@Override
	public Boolean saveInfo(PlFinanceProduct pl, BpCustMember mem,
			BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

}