package com.hurong.credit.dao.financingAgency.manageMoney.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.config.Pager;
import com.hurong.credit.dao.financingAgency.manageMoney.PlManageMoneyPlanBuyinfoDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.Investproject;
import com.hurong.credit.model.creditFlow.financingAgency.ShowManageMoney;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.financingAgency.manageMoney.PlMmOrderChildrenOr;

@SuppressWarnings("unchecked")
public class PlManageMoneyPlanBuyinfoDaoImpl extends BaseDaoImpl<PlManageMoneyPlanBuyinfo> implements
		PlManageMoneyPlanBuyinfoDao {

	public PlManageMoneyPlanBuyinfoDaoImpl() {
		super(PlManageMoneyPlanBuyinfo.class);
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long planId,Short persionType) {
	String hql = "from PlManageMoneyPlanBuyinfo as pbi where pbi.plManageMoneyPlan.mmplanId=? and  pbi.persionType=? and pbi.state!=0  GROUP BY pbi.buyDatetime ORDER BY pbi.buyDatetime desc";
		return super.findByHql(hql, new Object[]{planId,persionType});
	}
	
	//投资人投资管理查询方法
	@Override
	public List<Investproject> getPersonInvestProject(HttpServletRequest request,Integer start ,Integer limit) {
		
		String sql =" select * from " +
				    "(SELECT  "+
	                    "info.id as infoId,"+
	                    "info.orderNo as orderNo,"+
	                    "info.bidId as planId,"+
	                    "plan.proType as proType,"+
	                    "plan.bidProName as bidName,"+
	                    "0 as userType,"+
	                    "info.userId as userId,"+
	                    "info.userName as userName,"+
	                    "info.userMoney as userMoney,"+
	                    "info.bidtime as bidtime,"+
	                    "sl.payintentPeriod as periodTime,"+
	                    "sl.yearAccrualRate as yeaRate,"+
	                    "info.state as state,"+
	                    "plan.state as pstate,"+
	                    "plan.startIntentDate as startIntentDate,"+
	                    "plan.endIntentDate as endIntentDate,"+
	                    "pinfo.investId AS investId, "+
	                    "contract.url as contract,"+
	                    "'pl_bid_plan' as planTable "+
                    "FROM "+
                    "`pl_bid_info` AS info "+
                    "LEFT JOIN pl_bid_plan AS plan ON info.bidId = plan.bidId "+
                    "LEFT JOIN invest_person_info AS pinfo ON pinfo.orderNo = info.orderNo "+
                    "LEFT JOIN cs_procredit_contract AS contract ON contract.investId = pinfo.investId "+
                    "left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' "+
                    "left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' "+
                    "left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' "+
                    "left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' "+
                    "LEFT JOIN bp_fund_project AS sl "+
                    "on (( (sl.projectId=bdir.proId ) "+ 
                    "or (sl.projectId=bor.proId ) "+ 
                    "or(sl.projectId=pdir.proId ) "+ 
                    "or (sl.projectId=por.proId ) "+
                    	") )"+
                    "UNION ALL  "+  
                    "SELECT  "+
                    	"buyInfo.orderId as infoId, "+
                    	"null as orderNo,"+
                    	"buyInfo.mmplanId as planId, "+
                    	"buyPlan.keystr as proType, "+
                    	"buyInfo.mmName as bidName, "+
                    	"buyInfo.persionType as userType, "+
                    	"buyInfo.investPersonId as userId, "+
                    	"buyInfo.investPersonName as userName, "+
                    	"buyInfo.buyMoney as userMoney, "+
                    	"buyInfo.buyDatetime as bidtime, "+
                    	"buyPlan.investlimit as periodTime, "+
                    	"buyPlan.yeaRate as yeaRate, "+
                    	"buyInfo.state as state, "+
                    	"buyPlan.state as pstate,"+
                    	"buyPlan.startinInterestTime as startIntentDate,"+
                    	"buyPlan.endinInterestTime as endIntentDate,"+
                    	"pinfo.investId AS investId, "+
                    	"contract.url as contract, "+
                    	"'pl_managemoney_plan' as planTable "+
                    "FROM "+
                    "pl_managemoneyplan_buyinfo AS buyInfo "+
                    "LEFT JOIN pl_managemoney_plan AS buyPlan ON buyInfo.mmplanId = buyPlan.mmplanId "+
                    "LEFT JOIN invest_person_info AS pinfo ON pinfo.orderNo = buyInfo.DealInfoNumber "+
                    "LEFT JOIN cs_procredit_contract AS contract ON contract.investId = pinfo.investId "+
                    ") as investBuyInfo where 1=1 and investBuyInfo.yeaRate is not NULL ";
		if(request!=null){
			
			String userType =request.getParameter("userType");
			if(null!=userType&&!"".equals(userType)&&!"null".equals(userType)){
				sql=sql+" and investBuyInfo.userType="+Short.valueOf(userType);
			}else{
				String addUserType=(String) request.getAttribute("userType");
				if(null!=addUserType&&!"".equals(addUserType)&&!"null".equals(addUserType)){
					sql=sql+" and investBuyInfo.userType="+Short.valueOf(addUserType);
				}
			}
			String userId =request.getParameter("userId");
			if(null!=userId&&!"".equals(userId)&&!"null".equals(userId)){
				sql=sql+" and investBuyInfo.userId="+Long.valueOf(userId);
			}else{
				String adduserId=(String) request.getAttribute("userId");
				if(null!=adduserId&&!"".equals(adduserId)&&!"null".equals(adduserId)){
					sql=sql+" and investBuyInfo.userId="+Long.valueOf(adduserId);
				}
			}
			String userName =request.getParameter("userName");
			if(null!=userName&&!"".equals(userName)&&!"null".equals(userName)){
				sql=sql+" and investBuyInfo.userName like '%"+userName+"%'";
			}
			String state =request.getParameter("state");
			if(null!=state&&!"".equals(state)&&!"null".equals(state)){
				sql=sql+" and investBuyInfo.state="+Short.valueOf(state);
			}
			String bidName =request.getParameter("bidName");
			if(null!=bidName&&!"".equals(bidName)&&!"null".equals(bidName)){
				sql=sql+" and investBuyInfo.bidName like '%"+bidName+"%'";
			}
			
			String yeaRate =request.getParameter("yeaRate");
			if(null!=yeaRate&&!"".equals(yeaRate)&&!"null".equals(yeaRate)){
				sql=sql+" and investBuyInfo.yeaRate = "+new BigDecimal(yeaRate);
			}
			
			String userMoney =request.getParameter("userMoney");
			if(null!=userMoney&&!"".equals(userMoney)&&!"null".equals(userMoney)){
				sql=sql+" and investBuyInfo.userMoney = "+new BigDecimal(userMoney);
			}
			
			String periodTime =request.getParameter("periodTime");
			if(null!=periodTime&&!"".equals(periodTime)&&!"null".equals(periodTime)){
				sql=sql+" and investBuyInfo.userMoney = "+Integer.valueOf(userMoney);
			}
			String proType =request.getParameter("proType");
			if(null!=proType&&!"".equals(proType)&&!"null".equals(proType)){
				sql=sql+" and investBuyInfo.proType='"+proType+"'";
			}
			String bidtime =request.getParameter("bidtime");
			if(null!=bidtime&&!"".equals(bidtime)&&!"null".equals(bidtime)){
				sql=sql+" and investBuyInfo.bidtime>='"+bidtime+"'";
			}
			String bidtime2 =request.getParameter("bidtime2");
			if(null!=bidtime2&&!"".equals(bidtime2)&&!"null".equals(bidtime2)){
				sql=sql+" and investBuyInfo.bidtime<='"+bidtime2+" 23:59:59'";
			}
		}
		sql=sql+" GROUP BY infoId  order by investBuyInfo.bidtime desc";
		System.out.println("sql="+sql);
		List<Investproject> list = new ArrayList<Investproject>();
		if(start==null||limit==null){
			List listcount =this.getSession().createSQLQuery(sql).addScalar("infoId", Hibernate.LONG).
			  addScalar("orderNo", Hibernate.STRING).
			  addScalar("planId", Hibernate.LONG).
			  addScalar("proType", Hibernate.STRING).
			  addScalar("bidName", Hibernate.STRING).
			  addScalar("userType", Hibernate.SHORT).
			  addScalar("userId", Hibernate.LONG).
			  addScalar("userName", Hibernate.STRING).
			  addScalar("userMoney", Hibernate.BIG_DECIMAL).
			  addScalar("bidtime", Hibernate.TIMESTAMP).
			  addScalar("periodTime", Hibernate.INTEGER).
			  addScalar("yeaRate", Hibernate.BIG_DECIMAL).
			  addScalar("state", Hibernate.SHORT).
			 addScalar("contract",Hibernate.STRING).
			  addScalar("planTable", Hibernate.STRING).
			  addScalar("startIntentDate",Hibernate.DATE).
			  addScalar("endIntentDate",Hibernate.DATE).
			  addScalar("pstate",Hibernate.SHORT).
			  addScalar("investId",Hibernate.LONG).
			  setResultTransformer(Transformers.aliasToBean(Investproject.class)).list();
			list=listcount;
		}else{
			  list=this.getSession().createSQLQuery(sql).
					  addScalar("infoId", Hibernate.LONG).
					  addScalar("orderNo", Hibernate.STRING).
					  addScalar("planId", Hibernate.LONG).
					  addScalar("proType", Hibernate.STRING).
					  addScalar("bidName", Hibernate.STRING).
					  addScalar("userType", Hibernate.SHORT).
					  addScalar("userId", Hibernate.LONG).
					  addScalar("userName", Hibernate.STRING).
					  addScalar("userMoney", Hibernate.BIG_DECIMAL).
					  addScalar("bidtime", Hibernate.TIMESTAMP).
					  addScalar("periodTime", Hibernate.INTEGER).
					  addScalar("yeaRate", Hibernate.BIG_DECIMAL).
					  addScalar("state", Hibernate.SHORT).
					 addScalar("contract",Hibernate.STRING).
					  addScalar("planTable", Hibernate.STRING).
					  addScalar("startIntentDate",Hibernate.DATE).
					  addScalar("endIntentDate",Hibernate.DATE).
					  addScalar("pstate",Hibernate.SHORT).
					  addScalar("investId",Hibernate.LONG).
					 
					  
					  setResultTransformer(Transformers.aliasToBean(Investproject.class)).
					  setFirstResult(start).setMaxResults(limit).
					  list();
			 
		}
		return list;
	}

	@Override
	public List<Investproject> getTopFiveList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String sql =" select distinct * from " +
	    "(SELECT  "+
            "info.id as infoId,"+
            "info.bidId as planId,"+
            "plan.proType as proType,"+
            "plan.bidProName as bidName,"+
            "0 as userType,"+
            "info.userId as userId,"+
            "info.userName as userName,"+
            "info.userMoney as userMoney,"+
            "info.bidtime as bidtime," +
            "TIMESTAMPDIFF(MINUTE,info.bidtime,now()) as timeValue,"+
            "sl.payintentPeriod as periodTime,"+
            "sl.yearAccrualRate as yeaRate,"+
            "info.state as state,"+
            "'pl_bid_plan' as planTable "+
        "FROM "+
        "`pl_bid_info` AS info "+
        "LEFT JOIN pl_bid_plan AS plan ON info.bidId = plan.bidId "+
        "left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' "+
        "left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' "+
        "left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' "+
        "left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' "+
        "left join sl_smallloan_project as sl "+
        "on ( (sl.projectId=bdir.proId and sl.operationType =bdir.businessType) "+ 
        "or (sl.projectId=bor.proId and sl.operationType =bor.businessType) "+ 
        "or(sl.projectId=pdir.proId and sl.operationType =pdir.businessType) "+ 
        "or (sl.projectId=por.proId and sl.operationType =por.businessType) "+
        	")"+
        "UNION ALL  "+  
        "SELECT  "+
        	"buyInfo.orderId as infoId, "+
        	"buyInfo.mmplanId as planId, "+
        	"buyPlan.keystr as proType, "+
        	"buyPlan.mmName as bidName, "+
        	"buyInfo.persionType as userType, "+
        	"buyInfo.investPersonId as userId, "+
        	"buyInfo.investPersonName as userName, "+
        	"buyInfo.buyMoney as userMoney, "+
        	"buyInfo.buyDatetime as bidtime, " +
        	"TIMESTAMPDIFF(MINUTE,buyInfo.buyDatetime,now()) as timeValue,"+
        	"buyPlan.investlimit as periodTime, "+
        	"buyPlan.yeaRate as yeaRate, "+
        	"null as state, "+
        	"'pl_managemoney_plan' as planTable "+
        "FROM "+
        "pl_managemoneyplan_buyinfo AS buyInfo "+
        "LEFT JOIN pl_managemoney_plan AS buyPlan ON buyInfo.mmplanId = buyPlan.mmplanId "+
        ") as investBuyInfo where 1=1 and investBuyInfo.userType=0";
		if(request!=null){
		
			String FindType =(String) request.getAttribute("FindType");
			if(null!=FindType&&!"".equals(FindType)&&!"null".equals(FindType)){
				if("TopFive".equals(FindType)){
					sql=sql+"  order by investBuyInfo.userMoney desc limit 5";
				}else if("TopTwo".equals(FindType))
					sql=sql+" order by investBuyInfo.bidtime desc limit 2";
			}
			
			/*String userName =request.getParameter("userName");
			if(null!=userName&&!"".equals(userName)&&!"null".equals(userName)){
				sql=sql+" and investBuyInfo.userName like '%"+userName+"%'";
			}
			String state =request.getParameter("state");
			if(null!=state&&!"".equals(state)&&!"null".equals(state)){
				sql=sql+" and investBuyInfo.state="+Short.valueOf(state);
			}
			String bidName =request.getParameter("bidName");
			if(null!=bidName&&!"".equals(bidName)&&!"null".equals(bidName)){
				sql=sql+" and investBuyInfo.bidName like '%"+bidName+"%'";
			}
			
			String yeaRate =request.getParameter("yeaRate");
			if(null!=yeaRate&&!"".equals(yeaRate)&&!"null".equals(yeaRate)){
				sql=sql+" and investBuyInfo.yeaRate = "+new BigDecimal(yeaRate);
			}
			
			String userMoney =request.getParameter("userMoney");
			if(null!=userMoney&&!"".equals(userMoney)&&!"null".equals(userMoney)){
				sql=sql+" and investBuyInfo.userMoney = "+new BigDecimal(userMoney);
			}
			
			String periodTime =request.getParameter("periodTime");
			if(null!=periodTime&&!"".equals(periodTime)&&!"null".equals(periodTime)){
				sql=sql+" and investBuyInfo.userMoney = "+Integer.valueOf(userMoney);
			}
			String proType =request.getParameter("proType");
			if(null!=proType&&!"".equals(proType)&&!"null".equals(proType)){
				sql=sql+" and investBuyInfo.proType='"+proType+"'";
			}*/
		}
		List<Investproject> list = null;
		list=this.getSession().createSQLQuery(sql).
				  addScalar("infoId", Hibernate.LONG).
				  addScalar("planId", Hibernate.LONG).
				  addScalar("proType", Hibernate.STRING).
				  addScalar("bidName", Hibernate.STRING).
				  addScalar("userType", Hibernate.SHORT).
				  addScalar("userId", Hibernate.LONG).
				  addScalar("userName", Hibernate.STRING).
				  addScalar("userMoney", Hibernate.BIG_DECIMAL).
				  addScalar("bidtime", Hibernate.DATE).
				  addScalar("timeValue", Hibernate.LONG).
				  addScalar("periodTime", Hibernate.INTEGER).
				  addScalar("yeaRate", Hibernate.BIG_DECIMAL).
				  addScalar("state", Hibernate.SHORT).
				  addScalar("planTable", Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(Investproject.class)).
				  list();
		 
		
		return list;
	}

	@Override
	public int queryNum(String userName) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from PlBidInfo where userName='"+userName+"'";
		Query query = this.getSession().createQuery(sql);
		String str = String.valueOf(query.list().get(0)) ;
		int count = Integer.valueOf(str);
		return count;
	}

	@Override
	public List<Investproject> getPersonInvestProject(String userType,
			String userId, String state) {
		
		String sql =" select * from " +
				    "(SELECT  "+
	                    "info.id as infoId,"+
	                    "info.orderNo as orderNo,"+
	                    "info.bidId as planId,"+
	                    "plan.proType as proType,"+
	                    "plan.bidProName as bidName,"+
	                    "0 as userType,"+
	                    "info.userId as userId,"+
	                    "info.userName as userName,"+
	                    "info.userMoney as userMoney,"+
	                    "info.bidtime as bidtime,"+
	                    "sl.payintentPeriod as periodTime,"+
	                    "sl.yearAccrualRate as yeaRate,"+
	                    "info.state as state,"+
	                    "plan.state as pstate,"+
	                    "plan.startIntentDate as startIntentDate,"+
	                    "plan.endIntentDate as endIntentDate,"+
	                    "pinfo.investId AS investId, "+
	                    "contract.url as contract,"+
	                    "'pl_bid_plan' as planTable "+
                    "FROM "+
                    "`pl_bid_info` AS info "+
                    "LEFT JOIN pl_bid_plan AS plan ON info.bidId = plan.bidId "+
                    "LEFT JOIN invest_person_info AS pinfo ON pinfo.orderNo = info.orderNo "+
                    "LEFT JOIN cs_procredit_contract AS contract ON contract.investId = pinfo.investId "+
                    "left join bp_business_dir_pro as bdir on bdir.bdirProId =plan.bdirProId and plan.proType='B_Dir' "+
                    "left join bp_business_or_pro  as bor on bor.borProId =plan.borProId and plan.proType='B_Or' "+
                    "left join bp_persion_dir_pro  as pdir on pdir.pDirProId =plan.pDirProId and plan.proType='P_Dir' "+
                    "left join bp_persion_or_pro  as por on por.pOrProId =plan.pOrProId and plan.proType='P_Or' "+
                    "LEFT JOIN bp_fund_project AS sl "+
                    "on (( (sl.projectId=bdir.proId ) "+ 
                    "or (sl.projectId=bor.proId ) "+ 
                    "or(sl.projectId=pdir.proId ) "+ 
                    "or (sl.projectId=por.proId ) "+
                    	") )"+
                    "UNION ALL  "+  
                    "SELECT  "+
                    	"buyInfo.orderId as infoId, "+
                    	"null as orderNo,"+
                    	"buyInfo.mmplanId as planId, "+
                    	"buyPlan.keystr as proType, "+
                    	"buyInfo.mmName as bidName, "+
                    	"buyInfo.persionType as userType, "+
                    	"buyInfo.investPersonId as userId, "+
                    	"buyInfo.investPersonName as userName, "+
                    	"buyInfo.buyMoney as userMoney, "+
                    	"buyInfo.buyDatetime as bidtime, "+
                    	"buyPlan.investlimit as periodTime, "+
                    	"buyPlan.yeaRate as yeaRate, "+
                    	"buyInfo.state as state, "+
                    	"buyPlan.state as pstate,"+
                    	"buyPlan.startinInterestTime as startIntentDate,"+
                    	"buyPlan.endinInterestTime as endIntentDate,"+
                    	"pinfo.investId AS investId, "+
                    	"contract.url as contract, "+
                    	"'pl_managemoney_plan' as planTable "+
                    "FROM "+
                    "pl_managemoneyplan_buyinfo AS buyInfo "+
                    "LEFT JOIN pl_managemoney_plan AS buyPlan ON buyInfo.mmplanId = buyPlan.mmplanId "+
                    "LEFT JOIN invest_person_info AS pinfo ON pinfo.orderNo = buyInfo.DealInfoNumber "+
                    "LEFT JOIN cs_procredit_contract AS contract ON contract.investId = pinfo.investId "+
                    ") as investBuyInfo where 1=1 and investBuyInfo.yeaRate is not NULL ";
			
			if(null!=userType&&!"".equals(userType)&&!"null".equals(userType)){
				sql=sql+" and investBuyInfo.userType="+Short.valueOf(userType);
			}
			if(null!=userId&&!"".equals(userId)&&!"null".equals(userId)){
				sql=sql+" and investBuyInfo.userId="+Long.valueOf(userId);
			}
			if(null!=state&&!"".equals(state)&&!"null".equals(state)){
				sql=sql+" and investBuyInfo.state="+Short.valueOf(state);
			}
		sql=sql+" GROUP BY infoId  order by investBuyInfo.bidtime desc";
		List<Investproject> list = new ArrayList<Investproject>();
		  list=this.getSession().createSQLQuery(sql).
				  addScalar("infoId", Hibernate.LONG).
				  addScalar("orderNo", Hibernate.STRING).
				  addScalar("planId", Hibernate.LONG).
				  addScalar("proType", Hibernate.STRING).
				  addScalar("bidName", Hibernate.STRING).
				  addScalar("userType", Hibernate.SHORT).
				  addScalar("userId", Hibernate.LONG).
				  addScalar("userName", Hibernate.STRING).
				  addScalar("userMoney", Hibernate.BIG_DECIMAL).
				  addScalar("bidtime", Hibernate.TIMESTAMP).
				  addScalar("periodTime", Hibernate.INTEGER).
				  addScalar("yeaRate", Hibernate.BIG_DECIMAL).
				  addScalar("state", Hibernate.SHORT).
				 addScalar("contract",Hibernate.STRING).
				  addScalar("planTable", Hibernate.STRING).
				  addScalar("startIntentDate",Hibernate.DATE).
				  addScalar("endIntentDate",Hibernate.DATE).
				  addScalar("pstate",Hibernate.SHORT).
				  addScalar("investId",Hibernate.LONG).
				 
				  
				  setResultTransformer(Transformers.aliasToBean(Investproject.class)).
				  list();
			 
		return list;
	}

	@Override
	public String getUrl(String orderNo, String type) {
		
		String sql = "select * from invest_person_info as p where p.orderNo='"+orderNo+"' ";
		/*List list = this.getSession().createSQLQuery(sql).list();
		if(list!=null&&list.size()>0){
			return (String)list.get(0);
		}else{
			return null;
		}*/
		InvestPersonInfo info=(InvestPersonInfo) this.getSession().createSQLQuery(sql).addEntity(InvestPersonInfo.class).uniqueResult();
		if(info!=null){
			return info.getContractUrls();
		}
		return null;
	}

	@Override
	public BigDecimal investmentBidNum(String userType, String userId, String type) {
		String sql="";
		if(type.equals("investmentNumber")){
			/* sql="select sum(a.count) from( select count(*) as count from invest_person_info pbi left join pl_bid_plan  pbp on pbp.bidId=pbi.bidPlanId  where pbi.investPersonId="+userId+"  and pbi.persionType=0 and pbp.state >=7" +
			" UNION ALL select count(*) as count from pl_managemoneyplan_buyinfo pmb where pmb.investPersonId="+userId+" and pmb.persionType=0 and pmb.state=0 ) as a";*/
			sql="select sum(a.count) from(select 1 as count from bp_fund_intent as b left join  pl_bid_plan pdp on pdp.bidId =b.bidPlanId where  b.investPersonId="+userId+" and b.fundType='principalLending' and b.isValid=0  and pdp.state >=7 group by b.orderNo )as a";
		}else if(type.equals("investmentBidNum")){
			
			 sql="select sum(a.count) from( select count(*) as count from invest_person_info pbi left join pl_bid_plan  pbp on pbp.bidId=pbi.bidPlanId  where pbi.investPersonId="+userId+"  and pbi.persionType=0 and pbp.state =6" +
				" UNION ALL select count(*) as count from pl_managemoneyplan_buyinfo pmb where pmb.investPersonId="+userId+" and pmb.persionType=0 and pmb.state=0 ) as a";
				
		}else if(type.equals("investmentBackNum")){
			
			/* sql="select sum(a.count) from( select count(*) as count from invest_person_info pbi left join pl_bid_plan  pbp on pbp.bidId=pbi.bidPlanId  where pbi.investPersonId="+userId+"  and pbi.persionType=0 and pbp.state =7" +
				" UNION ALL select count(*) as count from pl_managemoneyplan_buyinfo pmb where pmb.investPersonId="+userId+" and pmb.persionType=0 and pmb.state=0 ) as a";*/
			 sql="select sum(a.count) from(select 1 as count from bp_fund_intent as b where  b.investPersonId="+userId+" and b.isCheck=0 and b.isValid=0  and b.afterMoney=0 and b.fundType='principalRepayment' group by b.orderNo)as a";
		}else if(type.equals("totalInvestMoney")){
			
/*			 sql="select sum(a.count) from( select sum(pbi.investMoney) as count from invest_person_info pbi left join pl_bid_plan  pbp on pbp.bidId=pbi.bidPlanId  where pbi.investPersonId="+userId+"  and pbi.persionType=0 and pbp.state >=7" +
				" UNION ALL select sum(pmb.buymoney) as count from pl_managemoneyplan_buyinfo pmb where pmb.investPersonId="+userId+" and pmb.persionType=0 and pmb.state=0 ) as a";*/
			 sql=" SELECT sum(b.payMoney) FROM bp_fund_intent AS b left join  pl_bid_plan pdp on pdp.bidId =b.bidPlanId WHERE b.investPersonId ="+userId+"   AND b.isValid = 0 and b.fundType='principalLending' and pdp.state>=7";
		}else if(type.equals("notallInterest")){
			
/*			 sql="select sum(a.count) from( select sum(pbi.investMoney) as count from invest_person_info pbi left join pl_bid_plan  pbp on pbp.bidId=pbi.bidPlanId  where pbi.investPersonId="+userId+"  and pbi.persionType=0 and pbp.state >=7" +
				" UNION ALL select sum(pmb.buymoney) as count from pl_managemoneyplan_buyinfo pmb where pmb.investPersonId="+userId+" and pmb.persionType=0 and pmb.state=0 ) as a";*/
			 sql=" SELECT sum(b.notMoney) FROM bp_fund_intent AS b WHERE b.investPersonId ="+userId+"  AND b.isCheck = 0 AND b.isValid = 0 and b.fundType='loanInterest'";
		}
		
		BigDecimal count=new BigDecimal(0);
		List list=getSession().createSQLQuery(sql).list();
		if(null!=list && list.size()>0){
			count=(BigDecimal) list.get(0);
		}
		return null==count?new BigDecimal(0):count;
	}

	@Override
	public String queryLoanUrl(Long bidId) {
		String sql = "select cont.url from cs_procredit_contract as cont where cont.bidPlanId=? ";
		return (String)this.getSession().createSQLQuery(sql).setParameter(0, bidId).uniqueResult();
	}
   /**
    * 根据订单号找到投资人的理财计划投标记录
    */
	@Override
	public PlManageMoneyPlanBuyinfo getOrderNumber(String requestNo) {
		// TODO Auto-generated method stub
		String sql=" from PlManageMoneyPlanBuyinfo as p where p.dealInfoNumber=?";
		
		return (PlManageMoneyPlanBuyinfo) this.getSession().createQuery(sql).setParameter(0, requestNo).uniqueResult();
	}
	public BigInteger getCountpl(Long l){
		String sql = "SELECT count(*) From pl_managemoneyplan_buyinfo info " +
				"LEFT JOIN pl_managemoney_plan plan ON info.mmplanId=plan.mmplanId " +
				"LEFT JOIN pl_managemoney_type type ON plan.manageMoneyTypeId=type.manageMoneyTypeId " +
			//	"WHERE type.manageMoneyTypeId='10' " +
				"WHERE info.investPersonId='"+l+"' " +
				"and info.state<>'0'";
		return (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
	}
	public List<PlManageMoneyPlanBuyinfo> getManagePlanBuyInfo(String sql){
		return this.getSession().createSQLQuery(sql).addEntity(PlManageMoneyPlanBuyinfo.class).list();
	}
	public PlManageMoneyPlanBuyinfo getInfoCount(String sql){
		return (PlManageMoneyPlanBuyinfo) this.getSession().createSQLQuery(sql).addEntity(PlManageMoneyPlanBuyinfo.class).uniqueResult();
	}

	@Override
	public BigInteger countExperience(Long mmplanId, Long investPersonId) {
		String sql="SELECT count(*) from pl_managemoneyplan_buyinfo AS pb where pb.mmplanId=? AND pb.persionType=0 AND pb.investPersonId=?";
		return (BigInteger) this.getSession().createSQLQuery(sql).setParameter(0, mmplanId).setParameter(1, investPersonId).uniqueResult();
	}

	@Override
	public List<PlMmOrderChildrenOr> getClaimsList(String orderId) {
		String hql=" from PlMmOrderChildrenOr as p where p.orderId=? ";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0,Long.valueOf(orderId)).list();
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> getBuyInfoListByPlanId(Long mmplanId,
			Short persionType, Short state) {
		// TODO Auto-generated method stub
		String hql = "from PlManageMoneyPlanBuyinfo as pbi where pbi.plManageMoneyPlan.mmplanId=? and  pbi.persionType=? and pbi.state=?  ORDER BY pbi.buyDatetime desc";
		return super.findByHql(hql, new Object[]{mmplanId,persionType,state});
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> findEarlyList(String loginname,
			HttpServletRequest request, Pager pager) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		     sql=new StringBuffer(" SELECT "+
				" e.earlyRedemptionId,"+
				" m.investPersonName,"+
				" m.mmName,"+
				" m.buyMoney,"+
				" m.startinInterestTime,"+
				" m.endinInterestTime,"+
				" e.checkStatus,"+
				" e.earlyDate,"+
				" m.keystr,"+
				" m.orderId,"+
				" m.mmplanId"+
				" FROM"+
				" pl_early_redemption e"+
				" LEFT JOIN pl_managemoneyplan_buyinfo m ON e.orderId = m.orderId"+
				" LEFT JOIN pl_managemoney_plan p ON m.mmplanId = p.mmplanId"+
				" WHERE"+
				"  p.moneyReceiver ='"+loginname+"'"
                );
		String mmName=request.getParameter("mmName");
		if(null!=mmName && !"".equals(mmName)){
			sql.append(" AND m.mmName like '%"+mmName+"%'");
		}
		String checkStatus=request.getParameter("checkStatus");
		if(null!=checkStatus && !"".equals(checkStatus)){
			sql.append(" AND e.checkStatus ="+Short.valueOf(checkStatus));
		}
		else{
			sql.append(" AND e.checkStatus ="+Short.valueOf("0"));
		}

		//System.out.println("--->>>"+sql.toString());
		if(null!=pager){
			List list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("earlyRedemptionId", Hibernate.LONG).
			  addScalar("investPersonName", Hibernate.STRING).
			  addScalar("mmName", Hibernate.STRING).
			  addScalar("buyMoney", Hibernate.BIG_DECIMAL).
			  addScalar("startinInterestTime",Hibernate.DATE).
			  addScalar("endinInterestTime",Hibernate.DATE).
			  addScalar("checkStatus", Hibernate.SHORT).
			  addScalar("earlyDate",Hibernate.DATE).
			  addScalar("keystr", Hibernate.STRING).
			  addScalar("earlyRedemptionId", Hibernate.LONG).
			  addScalar("orderId", Hibernate.LONG).
              setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlanBuyinfo.class)).setFirstResult((pager.getPageNumber() - 1) * pager.getPageSize()).setMaxResults(pager.getPageSize()).list();
			return list;
		}else{
			List list=this.getSession().createSQLQuery(sql.toString()).
			  addScalar("earlyRedemptionId", Hibernate.LONG).
			  addScalar("investPersonName", Hibernate.STRING).
			  addScalar("mmName", Hibernate.STRING).
			  addScalar("buyMoney", Hibernate.BIG_DECIMAL).
			  addScalar("startinInterestTime",Hibernate.DATE).
			  addScalar("endinInterestTime",Hibernate.DATE).
			  addScalar("checkStatus", Hibernate.SHORT).
			  addScalar("earlyDate",Hibernate.DATE).
			  addScalar("keystr", Hibernate.STRING).
			  addScalar("mmplanId", Hibernate.LONG).
			  addScalar("orderId", Hibernate.LONG).
            setResultTransformer(Transformers.aliasToBean(PlManageMoneyPlanBuyinfo.class)).list();
			return list;
		}
	}

	@Override
	public Long findEarlyCount(String loginname, HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
	     sql=new StringBuffer(" select count(*) from ( SELECT "+
			" e.earlyRedemptionId,"+
			" m.investPersonName"+
			
			" FROM"+
			" pl_early_redemption e"+
			" LEFT JOIN pl_managemoneyplan_buyinfo m ON e.orderId = m.orderId"+
			" LEFT JOIN pl_managemoney_plan p ON m.mmplanId = p.mmplanId"+
			" WHERE"+
			"  p.moneyReceiver ='"+loginname+"'"
           );
	String mmName=request.getParameter("mmName");
	if(null!=mmName && !"".equals(mmName)){
		sql.append(" AND m.mmName like '%"+mmName+"%'");
	}
	String checkStatus=request.getParameter("checkStatus");
	if(null!=checkStatus && !"".equals(checkStatus)){
		sql.append(" AND e.checkStatus ="+Short.valueOf(checkStatus));
	}
	else{
		sql.append(" AND e.checkStatus ="+Short.valueOf("0"));
	}
	sql.append(" ) as ww");
	//	System.out.println("--->>>"+sql.toString());
	Long count=new Long("0");
	List list=this.getSession().createSQLQuery(sql.toString()).list();
	if(null!=list && list.size()>0){
		if(null!=list.get(0)){
			BigInteger c=(BigInteger) list.get(0);
			count=c.longValue();
		}
	}
	return count;
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> getList(Long userId,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<PlManageMoneyPlanBuyinfo> list = new ArrayList();
		StringBuffer sql  = new StringBuffer();
		sql.append("select * from pl_managemoneyplan_buyinfo info ");//,DATE_ADD(plan.buyStartTime,INTERVAL plan.investlimit MONTH) AS *.endinInterestTime1
//		if(request.getParameter("selectVal")!=null&&!"".equals(request.getParameter("selectVal"))){
			sql.append(" left join pl_managemoney_plan plan on plan.mmplanId = info.mmplanId ");
//		}
		sql.append("where info.investPersonId = '"+userId+"' and info.keystr in ('mmplan','UPlan')");

		if(request!=null){
			String state1 = request.getParameter("state1");//购买的状态
			if(state1!=null&&!"".equals(state1)){
				sql.append(" and info.state = '1' ");
			}
			String state2 = request.getParameter("state2");//持有中的状态
			if(state2!=null&&!"".equals(state2)){
				sql.append(" and info.state in ('2','7') ");
			}
			String state10 = request.getParameter("state10");//已完成的状态
			if(state10!=null&&!"".equals(state10)){
				sql.append(" and info.state = '10' ");
			}
			String state8 = request.getParameter("state8");//已退出的状态
			if(state8!=null&&!"".equals(state8)){
				sql.append(" and info.state = '8' ");
			}
			String state_2 = request.getParameter("state_2");//已失败的状态
			if(state_2!=null&&!"".equals(state_2)){
				sql.append(" and info.state in ('-2','3') ");
			}
			if(request.getParameter("selectVal")!=null&&!"".equals(request.getParameter("selectVal"))){
				sql.append(" and plan.manageMoneyTypeId = "+request.getParameter("selectVal"));
			}
			
			if(request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))){
				sql.append(" and info.buyDatetime >= '"+request.getParameter("startTime")+"'");
			}

			if(request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))){
				sql.append(" and info.buyDatetime <= '"+request.getParameter("endTime")+"'");
			}
			
		}
		sql.append(" order by info.buyDatetime desc");
		System.out.println(sql);
		if(request!=null){
			String start = request.getParameter("start");
			String limit = request.getParameter("limit");
			if(start!=null&&!"".equals(start)&&request.getParameter("limit")!=null&&!"".equals(request.getParameter("limit"))){
				list = this.getSession().createSQLQuery(sql.toString()).addEntity(PlManageMoneyPlanBuyinfo.class).setFirstResult(Integer.valueOf(start)).setMaxResults(Integer.valueOf(limit)).list();
			}else{
				list = this.getSession().createSQLQuery(sql.toString()).addEntity(PlManageMoneyPlanBuyinfo.class).setParameter(0, userId).list();
			}
		}else{
			list = this.getSession().createSQLQuery(sql.toString()).addEntity(PlManageMoneyPlanBuyinfo.class).list();
		}
		
		return list;
	}

	@Override
	public List<PlManageMoneyPlanBuyinfo> getListNum(Long uerId,
			HttpServletRequest request) {
		List<PlManageMoneyPlanBuyinfo> list = new ArrayList();
		StringBuffer sql  = new StringBuffer();
		sql.append("select * from pl_managemoneyplan_buyinfo info ");
		if(request.getParameter("selectVal")!=null&&!"".equals(request.getParameter("selectVal"))){
			sql.append(" left join pl_managemoney_plan plan on plan.mmplanId = info.mmplanId ");
		}
		sql.append("where info.investPersonId = '"+uerId+"' and info.keystr in ('mmplan','UPlan')");
		if(request!=null){
			String state1 = request.getParameter("state1");//购买的状态
			if(state1!=null&&!"".equals(state1)){
				sql.append(" and info.state = '1' ");
			}
			String state2 = request.getParameter("state2");//持有中的状态
			if(state2!=null&&!"".equals(state2)){
				sql.append(" and info.state in ('2','7') ");
			}
			String state10 = request.getParameter("state10");//已完成的状态
			if(state10!=null&&!"".equals(state10)){
				sql.append(" and info.state = '10' ");
			}
			String state8 = request.getParameter("state8");//已退出的状态
			if(state8!=null&&!"".equals(state8)){
				sql.append(" and info.state = '8' ");
			}
			String state_2 = request.getParameter("state_2");//已失败的状态
			if(state_2!=null&&!"".equals(state_2)){
				sql.append(" and info.state in ('-2','3') ");
			}
			
			if(request.getParameter("selectVal")!=null&&!"".equals(request.getParameter("selectVal"))){
				sql.append(" and plan.manageMoneyTypeId = "+request.getParameter("selectVal"));
			}
			
			if(request.getParameter("startTime")!=null&&!"".equals(request.getParameter("startTime"))){
				sql.append(" and info.buyDatetime >= '"+request.getParameter("startTime")+"'");
			}

			if(request.getParameter("endTime")!=null&&!"".equals(request.getParameter("endTime"))){
				sql.append(" and info.buyDatetime <= '"+request.getParameter("endTime")+"'");
			}
			
			/*if(request.getParameter("selectVal")!=null&&!"".equals(request.getParameter("selectVal"))){
				sql.append(" left join pl_managemoney_plan plan on plan.mmplanId = info.mmplanId where plan.manageMoneyTypeId = "+request.getParameter("selectVal"));
			}*/
		}
		sql.append(" order by info.buyDatetime desc");
		System.out.println(sql);
		list = this.getSession().createSQLQuery(sql.toString()).addEntity(PlManageMoneyPlanBuyinfo.class).list();
		
		return list;
	}
}
