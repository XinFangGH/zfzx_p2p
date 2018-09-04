package com.hurong.credit.dao.creditFlow.fund.project.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.fund.project.BpFundProjectDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.finance.BpFundIntent;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;

public class BpFundProjectDaoImpl extends BaseDaoImpl<BpFundProject> implements
		BpFundProjectDao {

	public BpFundProjectDaoImpl() {
		super(BpFundProject.class);
	}

	@Override
	public List<BpFundProject> getProjectIdAndFlag(Long projectId, Short flag) {
		String hql = "from BpFundProject where projectId = ? and flag = ?";
		return super.findByHql(hql, new Object[]{projectId,flag});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BpFundProject> getPersonBpFundProject(
			HttpServletRequest request, Integer start, Integer limit) {
		String sql = "select bf.projectName,bf.projectMoney,bf.yearManagementConsultingOfRate,bf.yearAccrualRate,bf.startDate,bf.oppositeID,bf.platFormJointMoney,bf.projectStatus from bp_fund_project bf where  bf.flag=1 ";
	if(request!=null){
	
	Long offlineCusId = (Long)request.getAttribute("offlineCusId");
	if(offlineCusId !=null&&!"".equals(offlineCusId)&&!"null".equals(offlineCusId)){
		sql =sql+ " and bf.oppositeID ="+Long.valueOf(offlineCusId);
	}else{
		sql =sql+ " and bf.oppositeID =0";
	}
	//起始时间
	String bidtime = request.getParameter("bidtime");
	if(bidtime !=null && !"".equals(bidtime)&&!"null".equals(bidtime)){
		sql = sql+"  and bf.startDate>='"+bidtime+" 00:00:00'";
	}
	//结束时间
	String bidtime2 = request.getParameter("bidtime2");
	if(bidtime2 !=null && !"".equals(bidtime2)&&!"null".equals(bidtime2)){
		sql = sql+"  and bf.intentDate<='"+bidtime2+" 00:00:00'";
	}
	//投资名称
	String bidName =request.getParameter("bidName");
	if(null!=bidName&&!"".equals(bidName)&&!"null".equals(bidName)){
		sql=sql+"  and bf.projectName like '%"+bidName+"%'";
	}
	//投资金额
	String userMoney =request.getParameter("userMoney");
	if(null!=userMoney&&!"".equals(userMoney)&&!"null".equals(userMoney)){
		sql=sql+"  and bf.projectMoney = "+new BigDecimal(userMoney);
	}
	}
	List<BpFundProject> list = new ArrayList<BpFundProject>();
	if(start==null||limit==null){
	List listcount =this.getSession().createSQLQuery(sql).list();
	list=listcount;
	}else{
	  list=this.getSession().createSQLQuery(sql).
			  addScalar("oppositeID", Hibernate.LONG).
			  addScalar("projectName", Hibernate.STRING).
			  addScalar("projectMoney", Hibernate.BIG_DECIMAL).
			  addScalar("yearManagementConsultingOfRate", Hibernate.BIG_DECIMAL).
			  addScalar("yearAccrualRate", Hibernate.BIG_DECIMAL).
			  addScalar("startDate", Hibernate.DATE).
			  addScalar("platFormJointMoney", Hibernate.BIG_DECIMAL).
			  addScalar("projectStatus", Hibernate.SHORT).
			  setResultTransformer(Transformers.aliasToBean(BpFundProject.class)).
			list();
	 
	}
	return list;
		}

	@Override
	public List<BpFundProject> getBidLoanAfter() {
		String hql="from BpFundProject where projectStatus>=?";
		Object[] params={Short.valueOf("1")};
		return findByHql(hql, params);
	}
}
