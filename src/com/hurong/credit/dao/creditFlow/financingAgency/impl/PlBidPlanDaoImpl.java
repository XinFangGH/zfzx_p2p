package com.hurong.credit.dao.creditFlow.financingAgency.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.web.paging.PagingBean;
import com.hurong.credit.dao.creditFlow.financingAgency.PlBidPlanDao;
import com.hurong.credit.model.creditFlow.creditAssignment.investInfoManager.InvestprojectPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidPlan;
import com.hurong.credit.model.creditFlow.financingAgency.PlBidSale;
import com.hurong.credit.model.customer.InvestPersonInfo;
import com.hurong.credit.util.Common;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
@SuppressWarnings("unchecked")
public class PlBidPlanDaoImpl extends BaseDaoImpl<PlBidPlan> implements PlBidPlanDao {


    public PlBidPlanDaoImpl() {
        super(PlBidPlan.class);
    }


    @Override
    public String findLoanTotalMoneyBySQL(String pid) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("projectId", pid);
        List list = this.executeSqlFind("sl_smallloan_project", "projectMoney", params);
        if (list != null && list.size() > 0) {
            ret = list.get(0).toString();
        } else {
            ret = "0";
        }
        return ret;
    }

    @Override
    public String findAssureTypeBySQL(String pid) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("projectId", pid);
        List list = this.executeSqlFind("sl_smallloan_project", "assuretypeid", params);
        if (list != null && list.size() > 0) {
            ret = list.get(0).toString();
        } else {
            ret = "0";
        }
        return findAssureTypeNameBySQL(ret);
    }

    public String findAssureTypeNameBySQL(String did) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("dicId", did);
        List list = this.executeSqlFind("dictionary", "itemValue", params);
        if (list != null && list.size() > 0) {
            ret = list.get(0).toString();
        } else {
            ret = "无";
        }
        return ret;
    }


    @Override
    public String findOrgMoneyBySQL(String pid, String flag) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("projectId", pid);
        params.put("flag", flag);
        List list = this.executeSqlFind("bp_fund_project", "ownJointMoney", params);
        if (list != null && list.size() > 0) {
            ret = list.get(0).toString();
        } else {
            ret = "0";
        }
        return ret;
    }


    @Override
    public List<InvestprojectPlan> getInvestPlanList(HttpServletRequest request) {
        String sql = "SELECT " +
                "plan.planId, " +
                "plan.planName, " +
                "plan.planNumber, " +
                "plan.planType," +
                "plan.planmoney," +
                "plan.planState," +
                "plan.planStartDate, " +
                "plan.planEndDate, " +
                "plan.planFullTime  " +
                "from " +
                "( " +
                " SELECT " +
                "	bidPlan.bidId AS planId, " +
                "	bidPlan.bidProName AS planName," +
                "	bidPlan.bidProNumber AS planNumber," +
                "	bidPlan.proType AS planType," +
                "	bidPlan.bidMoney AS planmoney," +
                "	bidPlan.state AS planState," +
                "	bidPlan.publishSingeTime AS planStartDate," +
                "	bidPlan.bidEndTime AS planEndDate," +
                " 	bidPlan.fullTime as planFullTime   " +
                " FROM " +
                "  pl_bid_plan AS bidPlan" +
                " UNION ALL " +
                " SELECT " +
                "	plPlan.mmplanId AS planId," +
                "	plPlan.mmName AS planName," +
                "	plPlan.mmNumber AS planNumber," +
                "	plPlan.keystr AS planType," +
                "	plPlan.sumMoney AS planmoney," +
                "	plPlan.state AS planState," +
                "	NULL AS planStartDate," +
                "	plPlan.buyEndTime AS planEndDate," +
                "  NULL as planFullTime" +
                " FROM " +
                "	pl_managemoney_plan AS plPlan" +
                ") AS plan  " +
                "where " +
                "plan.planFullTime is not null " +
                "and plan.planState =5 " +
                "ORDER BY plan.planFullTime desc limit 2";

        List list = null;
        list = this.getSession().createSQLQuery(sql).
                addScalar("planId", Hibernate.LONG).
                addScalar("planName", Hibernate.STRING).
                addScalar("planNumber", Hibernate.STRING).
                addScalar("planType", Hibernate.STRING).
                addScalar("planmoney", Hibernate.BIG_DECIMAL).
                addScalar("planState", Hibernate.SHORT).
                addScalar("planStartDate", Hibernate.DATE).
                addScalar("planEndDate", Hibernate.DATE).
                addScalar("planFullTime", Hibernate.DATE).
                setResultTransformer(Transformers.aliasToBean(InvestprojectPlan.class)).
                list();
        return list;
    }


    @Override
    public BigDecimal statisticalFinance(String sql) {
        BigDecimal statis = (BigDecimal) this.jdbcTemplate.queryForObject(sql, new Object[]{}, java.math.BigDecimal.class);
        return statis;
    }


    @Override
    public String findSmallloanProjectBySQL(String pid) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("projectId", pid);
        List list = this.executeSqlFind("sl_smallloan_project", "projectStatus", params);
        if (list != null && list.size() > 0) {
            return list.get(0).toString();
        } else {
            return "0";
        }
    }


    @Override
    public String findMortgageBySQL(String pid) {
        String ret = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("projid", pid);
        List list = this.executeSqlFind("cs_procredit_mortgage", "mortgagepersontypeforvalue", params);
        if (list != null && list.size() > 0) {
            ret = list.get(0).toString();
        } else {
            ret = "未填";
        }
        //return findAssureTypeNameBySQL(ret);
        return ret;
    }


    @Override
    public PlBidPlan getById(Long bidId) {
        String hql = "from PlBidPlan p where p.bidId=?";
        Object[] params = {bidId};
        return (PlBidPlan) findUnique(hql, params);
    }


    @Override
    public PlBidPlan getByRequestNo(String requestNo) {
        String hql = "from PlBidPlan p where p.requestNo=?";
        return (PlBidPlan) this.getSession().createQuery(hql).setParameter(0, requestNo).uniqueResult();
    }


    @Override
    public List<PlBidPlan> queryBidPlan(String userName, String type) {
        String sql1 = "select * from invest_person_info as invest where invest.investPersonName=? ";
        String sql2 = "";
        if ("error".equals(type)) {
            sql2 = "select plan.* from pl_bid_plan as plan inner join pl_bid_info as info on plan.bidId=info.bidId where (info.state=0 or info.state=3) and info.userName=?  GROUP BY info.orderNo";
            return this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, userName).list();
        } else {
            if ("Planback".equals(type)) {
                sql2 = "select * from pl_bid_plan as plan where plan.bidId=? and plan.state=10";
            } else if ("Planbacking".equals(type)) {
                sql2 = "select * from pl_bid_plan as plan where plan.bidId=? and plan.state=7";
            } else if ("success".equals(type)) {
                sql2 = "select * from pl_bid_plan as plan where plan.bidId=? and plan.state in(1,2,4,6)";
            } else {
                return null;
            }
            List<InvestPersonInfo> investList = this.getSession().createSQLQuery(sql1).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
            List<PlBidPlan> list = new ArrayList<PlBidPlan>();
            if (investList != null && investList.size() > 0) {
                for (InvestPersonInfo invest : investList) {
                    PlBidPlan plan = (PlBidPlan) this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, invest.getBidPlanId()).uniqueResult();
                    if (plan != null) {
                        plan.setInvestOrderNo(invest.getOrderNo());
                        list.add(plan);
                    }
                }
            }
            return list;
        }
        /*
		 * String sql = "";if("Planback".equals(type)){
			//sql = "select plan.* from pl_bid_plan as plan where plan.state=10 and plan.bidId in(select info.bidPlanId from invest_person_info as info where info.investPersonName=? )";
			String sql1= "select * from invest_person_info as invest where invest.investPersonName=? ";
			String sql2 ="select * from pl_bid_plan as plan where plan.bidId=? and plan.state=10";
			List<InvestPersonInfo> investList = this.getSession().createSQLQuery(sql1).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			List<PlBidPlan> list = new ArrayList<PlBidPlan>();
			if(investList!=null&&investList.size()>0){
				for(InvestPersonInfo invest : investList){
					PlBidPlan plan = (PlBidPlan)this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, invest.getBidPlanId()).uniqueResult();
					if(plan!=null){
						plan.setInvestOrderNo(invest.getOrderNo());
						list.add(plan);
					}
				}
			}
			return list;
		}else if("Planbacking".equals(type)){
			//sql = "select plan.* from pl_bid_plan as plan where plan.state=7 and plan.bidId in(select info.bidPlanId from invest_person_info as info where info.investPersonName=? )";
			String sql1= "select * from invest_person_info as invest where invest.investPersonName=? ";
			String sql2 ="select * from pl_bid_plan as plan where plan.bidId=? and plan.state=7";
			List<InvestPersonInfo> investList = this.getSession().createSQLQuery(sql1).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			List<PlBidPlan> list = new ArrayList<PlBidPlan>();
			if(investList!=null&&investList.size()>0){
				for(InvestPersonInfo invest : investList){
					PlBidPlan plan = (PlBidPlan)this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, invest.getBidPlanId()).uniqueResult();
					if(plan!=null){
						plan.setInvestOrderNo(invest.getOrderNo());
						list.add(plan);
					}
				}
			}
			return list;
		}else if("success".equals(type)){
			//sql = "select plan.* from pl_bid_plan as plan inner join pl_bid_info as info on plan.bidId=info.bidId where  (info.state>=1 and info.state<4) and plan.bidId in(select invest.bidPlanId from invest_person_info as invest where invest.investPersonName=? ) and plan.state in(1,2,4,6)  GROUP BY info.orderNo ";
			//sql = "select * from invest_person_info as invest inner join pl_bid_plan as plan on invest.bidPlanId=plan.bidId where invest.investPersonName='"+userName+"'  and plan.state in(1,2,4,6)";
			String sql1= "select * from invest_person_info as invest where invest.investPersonName=? ";
			String sql2 ="select * from pl_bid_plan as plan where plan.bidId=? and plan.state in(1,2,4,6)";
			List<InvestPersonInfo> investList = this.getSession().createSQLQuery(sql1).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			List<PlBidPlan> list = new ArrayList<PlBidPlan>();
			if(investList!=null&&investList.size()>0){
				for(InvestPersonInfo invest : investList){
					PlBidPlan plan = (PlBidPlan)this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, invest.getBidPlanId()).uniqueResult();
					if(plan!=null){
						plan.setInvestOrderNo(invest.getOrderNo());
						list.add(plan);
					}
				}
			}
			return list;
		}else if("error".equals(type)){
			//sql = "select plan.* from pl_bid_plan as plan inner join pl_bid_info as info on plan.bidId=info.bidId where info.state=4 and plan.bidId in(select invest.bidPlanId from invest_person_info as invest where invest.investPersonName=? )  GROUP BY info.orderNo";
			String sql1= "select * from invest_person_info as invest where invest.investPersonName=? ";
			String sql2 ="select plan.* from pl_bid_plan as plan inner join pl_bid_info as info on plan.bidId=info.bidId where info.state=4 and plan.bidId=?  GROUP BY info.orderNo";
			List<InvestPersonInfo> investList = this.getSession().createSQLQuery(sql1).addEntity(InvestPersonInfo.class).setParameter(0, userName).list();
			List<PlBidPlan> list = new ArrayList<PlBidPlan>();
			if(investList!=null&&investList.size()>0){
				for(InvestPersonInfo invest : investList){
					PlBidPlan plan = (PlBidPlan)this.getSession().createSQLQuery(sql2).addEntity(PlBidPlan.class).setParameter(0, invest.getBidPlanId()).uniqueResult();
					if(plan!=null){
						plan.setInvestOrderNo(invest.getOrderNo());
						list.add(plan);
					}
				}
			}
			return list;
		}else{
			return null;
		}
		return this.getSession().createSQLQuery(sql).addEntity(PlBidPlan.class).setParameter(0, userName).list();*/
    }

    /**
     * 查询某担保担保下某状态下的标(增加查询条件)
     *
     * @param guarantorsId
     * @param states
     * @param request
     * @return
     */
    @Override
    public List<PlBidPlan> findBidPlan(Long guarantorsId, String states,
                                       HttpServletRequest request) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("SELECT  " +
                " p.bidId,p.bidProName," +
                " p.bidProNumber,p.bidMoney, " +
                " p.receiverName, " +
                " p.publishSingeTime, " +
                " (select (p.bidMoney - SUM(info.userMoney))  from pl_bid_info as info where info.bidId=p.bidId and info.state in ('1','2') GROUP BY info.bidId ) as afterMoney," +
                " (select COUNT(info.bidId) from pl_bid_info as info where info.bidId=p.bidId GROUP BY info.bidId ) as persionNum," +
                " CASE " +
                " WHEN project.payaccrualType = 'dayPay' THEN " +
                " CONCAT(project.payintentPeriod, '天') " +
                " WHEN project.payaccrualType = 'monthPay' THEN " +
                " CONCAT(project.payintentPeriod, '个月') " +
                " WHEN project.payaccrualType = 'seasonPay' THEN " +
                " CONCAT(project.payintentPeriod, '个季度') " +
                " WHEN project.payaccrualType = 'yearPay' THEN " +
                " CONCAT(project.payintentPeriod, '年') " +
                " WHEN project.payaccrualType = 'owerPay' THEN " +
                " CONCAT(project.payintentPeriod, '天') " +
                " END AS loanLife, " +
                " IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate" +
                " from pl_bid_plan p" +
                " LEFT JOIN bp_business_dir_pro AS bdir ON (p.proType='B_Dir' and p.bDirProId=bdir.bdirProId)" +
                " LEFT JOIN bp_persion_dir_pro as pdir on (p.proType='P_Dir' and p.pDirProId=pdir.pdirProId)" +
                " LEFT JOIN bp_business_or_pro as bor on (p.proType='P_Or' and p.borProId=bor.borProId)" +
                " LEFT JOIN bp_persion_or_pro as por on (p.proType='P_Or' and p.pOrProId=por.porProId)" +
                " LEFT JOIN bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)" +
                " where 1=1 ");
        if (null != guarantorsId && !"".equals(guarantorsId)) {
            sql.append(" and p.guarantorsId=" + guarantorsId);
        }
        if (null != states && !"".equals(states)) {
            sql.append(" and p.state in " + states);
        }
        String pprojectName = request.getParameter("name");
        if (null != pprojectName && !"".equals(pprojectName)) {
            sql.append(" and p.bidProName like '%" + pprojectName + "%'");
        }
        String uprojectName = request.getParameter("uprojectName");
        if (null != uprojectName && !"".equals(uprojectName)) {
            sql.append(" and p.bidProName like '%" + uprojectName + "%'");
        }
        System.out.println("已结清项目的sql" + sql.toString());
        List list = new ArrayList();
        if (request.getParameter("start") != null && request.getParameter("limit") != null) {
            Integer start = Integer.valueOf(request.getParameter("start").toString());
            Integer limit = Integer.valueOf(request.getParameter("limit").toString());
            list = this.getSession().createSQLQuery(sql.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("receiverName", Hibernate.STRING).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("afterMoney", Hibernate.DOUBLE).
                    addScalar("publishSingeTime", Hibernate.DATE).
                    addScalar("persionNum", Hibernate.INTEGER).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("interestRate", Hibernate.BIG_DECIMAL).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).setFirstResult(start).setMaxResults(limit).list();
        } else {
            list = this.getSession().createSQLQuery(sql.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("receiverName", Hibernate.STRING).
                    addScalar("publishSingeTime", Hibernate.DATE).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("afterMoney", Hibernate.DOUBLE).
                    addScalar("persionNum", Hibernate.INTEGER).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("interestRate", Hibernate.BIG_DECIMAL).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).list();
        }
        return list;
    }


    @Override
    public List<PlBidPlan> findBidPlan1(Long guarantorsId, String states,
                                        HttpServletRequest request) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("SELECT  " +
                " p.bidId,p.bidProName," +
                " p.bidProNumber,p.bidMoney, " +
                " p.receiverName, " +
                " CASE " +
                " WHEN project.payaccrualType = 'dayPay' THEN " +
                " CONCAT(project.payintentPeriod, '天') " +
                " WHEN project.payaccrualType = 'monthPay' THEN " +
                " CONCAT(project.payintentPeriod, '个月') " +
                " WHEN project.payaccrualType = 'seasonPay' THEN " +
                " CONCAT(project.payintentPeriod, '个季度') " +
                " WHEN project.payaccrualType = 'yearPay' THEN " +
                " CONCAT(project.payintentPeriod, '年') " +
                " WHEN project.payaccrualType = 'owerPay' THEN " +
                " CONCAT(project.payintentPeriod, '天') " +
                " END AS loanLife, " +
                " IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as interestRate" +
                " from pl_bid_plan p" +
                " LEFT JOIN bp_business_dir_pro AS bdir ON (p.proType='B_Dir' and p.bDirProId=bdir.bdirProId)" +
                " LEFT JOIN bp_persion_dir_pro as pdir on (p.proType='P_Dir' and p.pDirProId=pdir.pdirProId)" +
                " LEFT JOIN bp_business_or_pro as bor on (p.proType='P_Or' and p.borProId=bor.borProId)" +
                " LEFT JOIN bp_persion_or_pro as por on (p.proType='P_Or' and p.pOrProId=por.porProId)" +
                " LEFT JOIN bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)" +
                " where 1=1 ");
        if (null != guarantorsId && !"".equals(guarantorsId)) {
            sql.append(" and p.guarantorsId=" + guarantorsId);
        }
        if (null != states && !"".equals(states)) {
            sql.append(" and p.state in " + states);
        }
        String pprojectName = request.getParameter("name");
        if (null != pprojectName && !"".equals(pprojectName)) {
            sql.append(" and p.bidProName like '%" + pprojectName + "%'");
        }
        String uprojectName = request.getParameter("uprojectName");
        if (null != uprojectName && !"".equals(uprojectName)) {
            sql.append(" and p.bidProName like '%" + uprojectName + "%'");
        }
        List list = this.getSession().createSQLQuery(sql.toString()).
                addScalar("bidId", Hibernate.LONG).
                addScalar("bidProName", Hibernate.STRING).
                addScalar("bidProNumber", Hibernate.STRING).
                addScalar("receiverName", Hibernate.STRING).
                addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                addScalar("loanLife", Hibernate.STRING).
                addScalar("interestRate", Hibernate.BIG_DECIMAL).
                setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).list();
        return list;
    }

    /**
     * 查询某担保担保下某状态下的标的数量
     *
     * @param guarantorsId
     * @param states
     * @return
     */
    @Override
    public Long findBidPlanCount(Long guarantorsId, String states) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("SELECT  count(*) " +
                " from pl_bid_plan p " +
                " where 1=1 ");
        if (null != guarantorsId && !"".equals(guarantorsId)) {
            sql.append(" and p.guarantorsId=" + guarantorsId);
        }
        if (null != states && !"".equals(states)) {
            sql.append(" and p.state in " + states);
        }
        //	System.out.println("--->"+sql.toString());
        BigInteger count = new BigInteger("0");
        List list = this.getSession().createSQLQuery(sql.toString()).list();
        if (null != list && list.size() > 0) {
            if (null != list.get(0)) {
                count = (BigInteger) list.get(0);
            }
        }
        return count.longValue();
    }

    /**
     * 查询某担保公司担保下某状态下的标的总金额
     *
     * @param guarantorsId
     * @param states
     * @return
     */
    @Override
    public BigDecimal findBidPlanMoney(Long guarantorsId, String states) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("select IFNULL(SUM(p.bidMoney),0) from pl_bid_plan p where 1=1");
        if (null != guarantorsId && !"".equals(guarantorsId)) {
            sql.append(" and p.guarantorsId=" + guarantorsId);
        }
        if (null != states && !"".equals(states)) {
            sql.append(" and p.state in " + states);
        }
        return (BigDecimal) this.getSession().createSQLQuery(sql.toString()).uniqueResult();
    }

    /**
     * 查询某担保机构担保的逾期的标的数量的查询
     *
     * @param guarantorsId
     * @param states
     * @return
     */
    @Override
    public Long findOverdueBidPlanCount(Long guarantorsId) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("SELECT  count(*) " +
                " FROM " +
                " pl_bid_plan p" +
                " WHERE" +
                " p.bidId IN (" +
                " SELECT" +
                " f.bidPlanId" +
                " FROM" +
                " bp_fund_intent f WHERE " +
                " f.intentDate < DATE_FORMAT(" +
                " DATE_ADD(NOW(), INTERVAL 1 DAY),'%Y-%m-%d')" +
                " and IF (p.guaranteeWay = '1'," +
                " f.fundType = 'principalRepayment'," +
                " IF ( p.guaranteeWay = '3',p.guaranteeWay = '3'," +
                " IF ( p.guaranteeWay = '2', f.fundType IN ( 'principalRepayment','loanInterest'" +
                " ),''" +
                ")))" +
                " AND f.notMoney > 0" +
                " GROUP BY" +
                " f.bidPlanId" +
                " )");
        if (null != guarantorsId && !"".equals(guarantorsId)) {
            sql.append(" and p.state = 7 and p.guarantorsId=" + guarantorsId);
        }
        System.out.println("--->" + sql.toString());
        BigInteger count = new BigInteger("0");
        List list = this.getSession().createSQLQuery(sql.toString()).list();
        if (null != list && list.size() > 0) {
            if (null != list.get(0)) {
                count = (BigInteger) list.get(0);
            }
        }
        return count.longValue();
    }


    @Override
    public List<PlBidPlan> getNewPlanList(PagingBean pb,
                                          HttpServletRequest request) {
        StringBuffer buff = new StringBuffer("SELECT * from ( ");
        buff.append(" SELECT p.bidId,p.state,p.prepareSellTime,p.bidProName,p.bidProNumber,p.payIntersetWay,p.bidMoney, p.proType,project.payaccrualType as payMoneyTimeType,p.keepCreditlevelName,p.proKeepType,");
        buff.append(" CASE 	");
        buff.append(" WHEN (project.payaccrualType = 'dayPay' or project.payaccrualType='owerPay') and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod");
        buff.append(" WHEN (project.payaccrualType = 'dayPay' or project.payaccrualType='owerPay') and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'monthPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*30 ");
        buff.append(" WHEN project.payaccrualType = 'monthPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*120");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'yearPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*360");
        buff.append(" WHEN project.payaccrualType = 'yearPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now())) ");
        buff.append(" END AS loanLifeQuery,");
        buff.append(" CASE  WHEN project.payaccrualType = 'dayPay' THEN CONCAT(project.payintentPeriod, '天') ");
        buff.append(" WHEN project.payaccrualType = 'monthPay' THEN  CONCAT(project.payintentPeriod, '个月') ");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' THEN  CONCAT(project.payintentPeriod, '个季度') ");
        buff.append(" WHEN project.payaccrualType = 'yearPay' THEN  CONCAT(project.payintentPeriod, '年') ");
        buff.append(" WHEN project.payaccrualType = 'owerPay' THEN  CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天')  ");
        buff.append(" END AS loanLife, ");
        buff.append(" IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as yearInterestRate");
        buff.append(" from pl_bid_plan p");
        buff.append(" LEFT JOIN bp_business_dir_pro AS bdir ON (p.proType='B_Dir' and p.bDirProId=bdir.bdirProId)");
        buff.append(" LEFT JOIN bp_persion_dir_pro as pdir on (p.proType='P_Dir' and p.pDirProId=pdir.pdirProId)");
        buff.append(" LEFT JOIN bp_business_or_pro as bor on (p.proType='P_Or' and p.borProId=bor.borProId)");
        buff.append(" LEFT JOIN bp_persion_or_pro as por on (p.proType='P_Or' and p.pOrProId=por.porProId)");
        buff.append(" LEFT JOIN bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)");
        buff.append(" ) AS plan");
        buff.append(" where plan.state>0");

        //首页拼接


        //借款期限查询
        String payTime = request.getParameter("payTime");
        if (StringUtils.isNotEmpty(payTime) && Common.sql_inj(payTime)) {
            String[] loanLifeQuery = payTime.split("-");
            Integer startDay = Integer.valueOf(loanLifeQuery[0]) * 30;
            Integer endDay = Integer.valueOf(loanLifeQuery[1]) * 30;
            buff.append(" and plan.loanLifeQuery>=" + startDay + " and plan.loanLifeQuery<= " + endDay + "");
        }
        //借款金额查询
        String bidMoney = request.getParameter("bidMoney");
        if (StringUtils.isNotEmpty(bidMoney) && Common.sql_inj(bidMoney)) {
            String[] bidMoneyStr = bidMoney.split("-");
            buff.append(" and plan.bidMoney>=" + bidMoneyStr[0] + " and plan.bidMoney<=" + bidMoneyStr[1] + "");
        }
        //年收益率查询
        String yearInterestRate = request.getParameter("yearInterestRate");
        if (StringUtils.isNotEmpty(yearInterestRate) && Common.sql_inj(yearInterestRate)) {
            String yearInterestRateStr[] = yearInterestRate.split("-");
            buff.append(" and plan.yearInterestRate>=" + yearInterestRateStr[0] + " and plan.yearInterestRate<=" + yearInterestRateStr[1] + "");
        }
        //标的名称查询
        String bidProName = request.getParameter("bidProName");
        if (StringUtils.isNotEmpty(bidProName) && Common.sql_inj(bidProName)) {
            String bidProNameStr[] = bidProName.split("-");
            buff.append(" and LOWER(plan.bidProName) like LOWER('%" + bidProName + "%')");
        }

        // 标的类型查询
        String proKeepType = null;
        try {
            String keepProtypeName = request.getParameter("keepProtypeName");
            if (StringUtils.isNotEmpty(keepProtypeName) && Common.sql_inj(keepProtypeName)) {
                proKeepType = new String(keepProtypeName.getBytes("iso8859-1"), "utf-8");
            } else {
                proKeepType = "";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(proKeepType) && Common.sql_inj(proKeepType)) {
            buff.append(" and plan.proKeepType='" + proKeepType + "'");
        }
        String keepCreditlevelName = request.getParameter("keepCreditlevelName");
        if (StringUtils.isNotEmpty(keepCreditlevelName) && Common.sql_inj(keepCreditlevelName)) {
            buff.append(" and plan.keepCreditlevelName='" + keepCreditlevelName + "'");
        }
        buff.append(" ORDER BY FIELD(plan.state,1,2,6,7,10)");
        System.out.println(buff);
        if (pb == null) {
            return this.getSession().createSQLQuery(buff.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("state", Hibernate.INTEGER).
                    addScalar("payIntersetWay", Hibernate.STRING).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("proType", Hibernate.STRING).
                    addScalar("prepareSellTime", Hibernate.DATE).
                    addScalar("payMoneyTimeType", Hibernate.STRING).
                    addScalar("keepCreditlevelName", Hibernate.STRING).
                    addScalar("proKeepType", Hibernate.STRING).
                    addScalar("loanLifeQuery", Hibernate.STRING).
                    addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                    addScalar("bidProName", Hibernate.STRING).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).list();
        } else {
            Integer firstResult = 0;
            if (pb.getFirstResult() > 1) {
                firstResult = (pb.getFirstResult() - 1) * pb.getPageSize();
            }

            return this.getSession().createSQLQuery(buff.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("state", Hibernate.INTEGER).
                    addScalar("payIntersetWay", Hibernate.STRING).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("prepareSellTime", Hibernate.DATE).
                    addScalar("proType", Hibernate.STRING).
                    addScalar("payMoneyTimeType", Hibernate.STRING).
                    addScalar("keepCreditlevelName", Hibernate.STRING).
                    addScalar("proKeepType", Hibernate.STRING).
                    addScalar("loanLifeQuery", Hibernate.STRING).
                    addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                    addScalar("bidProName", Hibernate.STRING).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class))
                    .setFirstResult(firstResult).setMaxResults(pb.getPageSize()).list();
        }
    }


    @Override
    public List<PlBidPlan> getNewPlanList1(Integer start, Integer limit, HttpServletRequest request) {
        StringBuffer buff = new StringBuffer("SELECT * from ( ");
        buff.append(" SELECT p.bidId,p.state,p.prepareSellTime,p.bidProName,p.bidProNumber,p.payIntersetWay,p.bidMoney, p.proType,project.payaccrualType as payMoneyTimeType,p.keepCreditlevelName,p.proKeepType,p.startMoney,");
        buff.append(" CASE 	");
        buff.append(" WHEN (project.payaccrualType = 'dayPay' or project.payaccrualType='owerPay') and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod");
        buff.append(" WHEN (project.payaccrualType = 'dayPay' or project.payaccrualType='owerPay') and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'monthPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*30 ");
        buff.append(" WHEN project.payaccrualType = 'monthPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*120");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now()))");
        buff.append(" WHEN project.payaccrualType = 'yearPay' and (p.proType='B_Dir' or p.proType='P_Dir') THEN 	project.payintentPeriod*360");
        buff.append(" WHEN project.payaccrualType = 'yearPay' and (p.proType='B_Or' or p.proType='P_Or') THEN IFNULL(datediff(bor.loanEndTime, now()),datediff(por.loanEndTime, now())) ");
        buff.append(" END AS loanLifeQuery,");
        buff.append(" CASE  WHEN project.payaccrualType = 'dayPay' THEN CONCAT(project.payintentPeriod, '天') ");
        buff.append(" WHEN project.payaccrualType = 'monthPay' THEN  CONCAT(project.payintentPeriod, '个月') ");
        buff.append(" WHEN project.payaccrualType = 'seasonPay' THEN  CONCAT(project.payintentPeriod, '个季度') ");
        buff.append(" WHEN project.payaccrualType = 'yearPay' THEN  CONCAT(project.payintentPeriod, '年') ");
        buff.append(" WHEN project.payaccrualType = 'owerPay' THEN  CONCAT(project.payintentPeriod*project.dayOfEveryPeriod, '天')  ");
        buff.append(" END AS loanLife, ");
        buff.append(" IFNULL(IFNULL(bdir.yearInterestRate,pdir.yearInterestRate),IFNULL(bor.yearInterestRate,por.yearInterestRate)) as yearInterestRate");
        buff.append(" from pl_bid_plan p");
        buff.append(" LEFT JOIN bp_business_dir_pro AS bdir ON (p.proType='B_Dir' and p.bDirProId=bdir.bdirProId)");
        buff.append(" LEFT JOIN bp_persion_dir_pro as pdir on (p.proType='P_Dir' and p.pDirProId=pdir.pdirProId)");
        buff.append(" LEFT JOIN bp_business_or_pro as bor on (p.proType='P_Or' and p.borProId=bor.borProId)");
        buff.append(" LEFT JOIN bp_persion_or_pro as por on (p.proType='P_Or' and p.pOrProId=por.porProId)");
        buff.append(" LEFT JOIN bp_fund_project as project on (project.id=bdir.moneyPlanId or project.id=pdir.moneyPlanId  or project.id=bor.moneyPlanId or project.id=por.moneyPlanId)");
        buff.append(" ) AS plan");
        buff.append(" where plan.state>0");

        //首页拼接
        //借款期限查询
        String payTime = request.getParameter("payTime");
        if (StringUtils.isNotEmpty(payTime) && Common.sql_inj(payTime)) {
            String[] loanLifeQuery = payTime.split("-");
            Integer startDay = Integer.valueOf(loanLifeQuery[0]) * 30;
            Integer endDay = Integer.valueOf(loanLifeQuery[1]) * 30;
            buff.append(" and plan.loanLifeQuery>=" + startDay + " and plan.loanLifeQuery<= " + endDay + "");
        }
        //借款金额查询
        String bidMoney = request.getParameter("bidMoney");
        if (StringUtils.isNotEmpty(bidMoney) && Common.sql_inj(bidMoney)) {
            String[] bidMoneyStr = bidMoney.split("-");
            buff.append(" and plan.bidMoney>=" + bidMoneyStr[0] + " and plan.bidMoney<=" + bidMoneyStr[1] + "");
        }
        //年收益率查询
        String yearInterestRate = request.getParameter("yearInterestRate");
        if (StringUtils.isNotEmpty(yearInterestRate) && Common.sql_inj(yearInterestRate)) {
            String yearInterestRateStr[] = yearInterestRate.split("-");
            buff.append(" and plan.yearInterestRate>=" + yearInterestRateStr[0] + " and plan.yearInterestRate<=" + yearInterestRateStr[1] + "");
        }
        //标的名称查询
        String bidProName = request.getParameter("bidProName");
        if (StringUtils.isNotEmpty(bidProName) && Common.sql_inj(bidProName)) {
            String bidProNameStr[] = bidProName.split("-");
            buff.append(" and LOWER(plan.bidProName) like LOWER('%" + bidProName + "%')");
        }

        // 标的类型查询
        String proKeepType = null;
        try {
            String keepProtypeName = request.getParameter("keepProtypeName");
            if (StringUtils.isNotEmpty(keepProtypeName) && Common.sql_inj(keepProtypeName)) {
                proKeepType = new String(keepProtypeName.getBytes("iso8859-1"), "utf-8");
            } else {
                proKeepType = "";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(proKeepType) && Common.sql_inj(proKeepType)) {
            buff.append(" and plan.proKeepType='" + proKeepType + "'");
        }
        String keepCreditlevelName = request.getParameter("keepCreditlevelName");
        if (StringUtils.isNotEmpty(keepCreditlevelName) && Common.sql_inj(keepCreditlevelName)) {
            buff.append(" and plan.keepCreditlevelName='" + keepCreditlevelName + "'");
        }
        buff.append(" ORDER BY FIELD(plan.state,1,2,6,7,10)");
        System.out.println(buff);
        if (start == null && limit ==null) {
            return this.getSession().createSQLQuery(buff.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("state", Hibernate.INTEGER).
                    addScalar("payIntersetWay", Hibernate.STRING).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("proType", Hibernate.STRING).
                    addScalar("prepareSellTime", Hibernate.DATE).
                    addScalar("payMoneyTimeType", Hibernate.STRING).
                    addScalar("keepCreditlevelName", Hibernate.STRING).
                    addScalar("proKeepType", Hibernate.STRING).
                    addScalar("loanLifeQuery", Hibernate.STRING).
                    addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("startMoney", Hibernate.BIG_DECIMAL).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).list();
        } else {

            return this.getSession().createSQLQuery(buff.toString()).
                    addScalar("bidId", Hibernate.LONG).
                    addScalar("state", Hibernate.INTEGER).
                    addScalar("payIntersetWay", Hibernate.STRING).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("bidProNumber", Hibernate.STRING).
                    addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                    addScalar("loanLife", Hibernate.STRING).
                    addScalar("prepareSellTime", Hibernate.DATE).
                    addScalar("proType", Hibernate.STRING).
                    addScalar("payMoneyTimeType", Hibernate.STRING).
                    addScalar("keepCreditlevelName", Hibernate.STRING).
                    addScalar("proKeepType", Hibernate.STRING).
                    addScalar("loanLifeQuery", Hibernate.STRING).
                    addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                    addScalar("bidProName", Hibernate.STRING).
                    addScalar("startMoney", Hibernate.BIG_DECIMAL).
                    setResultTransformer(Transformers.aliasToBean(PlBidPlan.class))
                    .setFirstResult(start).setMaxResults(limit).list();
        }
    }



    @Override
    public List<PlBidSale> getSaleList(PagingBean pb, Long bidId) {
        // TODO Auto-generated method stub
        Integer firstResult = 0;

        String sql = "select * from pl_bid_sale sale where sale.bidPlanID  = ? and sale.saleStatus in('3','4','7')";
        List<PlBidSale> list = new ArrayList<PlBidSale>();
        if (pb != null) {
            if (pb.getFirstResult() > 1) {
                firstResult = (pb.getFirstResult() - 1) * pb.getPageSize();
            }
            list = this.getSession().createSQLQuery(sql).addEntity(PlBidSale.class).setParameter(0, bidId).setFirstResult(firstResult).setMaxResults(pb.getPageSize()).list();
        } else {
            list = this.getSession().createSQLQuery(sql).addEntity(PlBidSale.class).setParameter(0, bidId).list();
        }
        return list;
    }


    @Override
    public List<PlBidPlan> getByGuarantor(Long userId) {
        // TODO Auto-generated method stub
        String sql = "select * from pl_bid_plan plan where plan.guarantorsId = ?";
        return this.getSession().createSQLQuery(sql).setParameter(0, userId).list();
    }


    @Override
    public List<PlBidPlan> getListByState(String state) {
        String hql = "from PlBidPlan plan where plan.state in (" + state + ")";
        return findByHql(hql);
    }


    @Override
    public List<PlBidPlan> getBidListByDuration(Integer state) {
        StringBuffer sql = new StringBuffer("select * from pl_bid_plan plan where  ");
        sql.append(" state IN ('1','2','7','10') AND isVip=0  AND ");
        sql.append(" (novice IS NULL OR novice !=1) AND ");
        if (state == 1) {//查询短期  小于等于半年
            sql.append(" ((payMoneyTimeType = 'owerPay' AND payMoneyTime <180) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'dayPay' AND payMoneyTime <180) ")
                    .append("OR")
                    .append(" (payMoneyTimeType = 'monthPay' AND payMoneyTime <6) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'seasonPay' AND payMoneyTime <2)) ");
        } else if (state == 2) {//查询中期  小于等于一年
            sql.append(" ((payMoneyTimeType = 'owerPay' AND payMoneyTime >=180 AND payMoneyTime<360) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'dayPay' AND payMoneyTime >=180 AND payMoneyTime<360) ")
                    .append("OR")
                    .append(" (payMoneyTimeType = 'monthPay' AND payMoneyTime >=6 AND payMoneyTime<12) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'seasonPay' AND payMoneyTime >=2 AND payMoneyTime<4) ")
//			.append(" OR ")
//			.append(" (payMoneyTimeType = 'yearPay' AND payMoneyTime =1) ")
                    .append(")");
        } else {//长期 大于一年
            sql.append(" ((payMoneyTimeType = 'owerPay' AND payMoneyTime >=360) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'owerPay' AND payMoneyTime >=360) ")
                    .append("OR")
                    .append(" (payMoneyTimeType = 'monthPay' AND payMoneyTime >=12) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'seasonPay' AND payMoneyTime >=4) ")
                    .append(" OR ")
                    .append(" (payMoneyTimeType = 'yearPay' AND payMoneyTime >=1)) ");
        }
        sql.append(" ORDER BY state, bidId desc LIMIT 4 ");
        List<PlBidPlan> list = this.getSession().createSQLQuery(sql.toString()).addEntity(PlBidPlan.class).list();
        return list;
    }

    @Override
    public List<PlBidPlan> getIndexPlanList() {
        StringBuffer sql = new StringBuffer("(select plan.bidId" +
                ", plan.state" +
                ", plan.bidProName" +
                ", plan.yearInterestRate" +
                ", plan.proKeepType" +
                ", plan.raiseRate" +
                ", plan.showRate" +
                ", plan.startMoney" +
                ", plan.payMoneyTime" +
                ", plan.payMoneyTimeType" +
                ", plan.novice,plan.bidMoney" +
                " from pl_bid_plan plan where ");
        sql.append("state IN ('1') AND isVip=0  AND payMoneyTime = 3 AND");
        sql.append(" (novice IS NULL OR novice !=1)");
        sql.append(" ORDER BY state, createtime desc LIMIT 1) UNION ALL ");
        sql.append("(select plan.bidId" +
                ", plan.state" +
                ", plan.bidProName" +
                ", plan.yearInterestRate" +
                ", plan.proKeepType" +
                ", plan.raiseRate" +
                ", plan.showRate" +
                ", plan.startMoney" +
                ", plan.payMoneyTime" +
                ", plan.payMoneyTimeType" +
                ", plan.novice,plan.bidMoney" +
                " from pl_bid_plan plan where ");
        sql.append("state IN ('1') AND isVip=0  AND payMoneyTime = 6 AND");
        sql.append(" (novice IS NULL OR novice !=1)");
        sql.append(" ORDER BY state, createtime desc LIMIT 1) UNION ALL ");
        sql.append("(select plan.bidId" +
                ", plan.state" +
                ", plan.bidProName" +
                ", plan.yearInterestRate" +
                ", plan.proKeepType" +
                ", plan.raiseRate" +
                ", plan.showRate" +
                ", plan.startMoney" +
                ", plan.payMoneyTime" +
                ", plan.payMoneyTimeType" +
                ", plan.novice,plan.bidMoney" +
                " from pl_bid_plan plan where ");
        sql.append("state IN ('1') AND isVip=0  AND payMoneyTime = 12 AND");
        sql.append(" (novice IS NULL OR novice !=1)");
        sql.append(" ORDER BY state, createtime desc LIMIT 1)");
        System.out.println(sql.toString());
        return this.getSession().createSQLQuery(sql.toString()).
                addScalar("bidId", Hibernate.LONG).
                addScalar("state", Hibernate.INTEGER).
                addScalar("bidProName", Hibernate.STRING).
                addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                addScalar("proKeepType", Hibernate.STRING).
                addScalar("novice", Hibernate.INTEGER).
                addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                addScalar("raiseRate", Hibernate.BIG_DECIMAL).
                addScalar("showRate", Hibernate.BIG_DECIMAL).
                addScalar("startMoney", Hibernate.BIG_DECIMAL).
                addScalar("payMoneyTime", Hibernate.INTEGER).
                addScalar("payMoneyTimeType", Hibernate.STRING).
                //addScalar("createtime", Hibernate.DATE).
                //addScalar("bdirProId", Hibernate.LONG).
                //addScalar("afterMoney", Hibernate.BIG_DECIMAL).
                //addScalar("theWayBack", Hibernate.BIG_DECIMAL).
                //addScalar("loanLife", Hibernate.BIG_DECIMAL).
                        setResultTransformer(Transformers.aliasToBean(PlBidPlan.class))
                .list();
    }

    @Override
    public List<PlBidPlan> getPlanDetail(Long bidId){

        StringBuffer sql = new StringBuffer("select p.proKeepType" +
                ",p.bidId"+
                ",p.bidProName" +
                ",p.bdirProId"+
                ",p.pDirProId"+
                ",p.yearInterestRate" +
                ",p.proType"+
                ",p.showRate" +
                ",p.bidMoney" +
                ",p.riseMoney" +
                ",p.payIntersetWay" +
                ",p.keepCreditlevelName"+
                ",p.state"+
                ",p.startIntentDate"+
                ",p.bidEndTime"+
                ",p.payMoneyTime"+
                ",p.startMoney"+
                ",p.payMoneyTimeType"+
                ",p.novice"+
                ",p.raiseRate"+
                ",p.versionType"+
                " from pl_bid_plan p "+
                "where bidId=" +
                 bidId);
        System.out.println(sql);
        return this.getSession().createSQLQuery(sql.toString()).
                addScalar("proKeepType",Hibernate.STRING).
                addScalar("bidId",Hibernate.LONG).
                addScalar("bidProName",Hibernate.STRING).
                addScalar("bdirProId",Hibernate.LONG).
                addScalar("pdirProId",Hibernate.LONG).
                addScalar("yearInterestRate",Hibernate.BIG_DECIMAL).
                addScalar("proType",Hibernate.STRING).
                addScalar("showRate",Hibernate.BIG_DECIMAL).
                addScalar("bidMoney",Hibernate.BIG_DECIMAL).
                addScalar("riseMoney",Hibernate.BIG_DECIMAL).
                addScalar("payIntersetWay",Hibernate.STRING).
                addScalar("keepCreditlevelName",Hibernate.STRING).
                addScalar("state",Hibernate.INTEGER).
                addScalar("startIntentDate",Hibernate.DATE).
                addScalar("bidEndTime", Hibernate.TIMESTAMP).
                addScalar("payMoneyTime",Hibernate.INTEGER).
                addScalar("startMoney",Hibernate.BIG_DECIMAL).
                addScalar("payMoneyTimeType",Hibernate.STRING).
                addScalar("versionType",Hibernate.SHORT).
                addScalar("novice",Hibernate.INTEGER).
                addScalar("raiseRate",Hibernate.BIG_DECIMAL).
                setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).list();
    }


    @Override
    public List<PlBidPlan> getNewcomerList() {
        StringBuffer sql = new StringBuffer("select plan.bidId" +
                ", plan.state" +
                ", plan.bidProName" +
                ", plan.yearInterestRate" +
                ", plan.proKeepType" +
                ", plan.raiseRate" +
                ", plan.showRate" +
                ", plan.startMoney" +
                ", plan.payMoneyTime" +
                ", plan.payMoneyTimeType" +
                ", plan.novice,plan.bidMoney" +
                " from pl_bid_plan plan where ");
        sql.append("state IN ('1','2','7','10') AND isVip=0  AND");
        sql.append(" novice = 1");
        sql.append(" GROUP BY FIELD(state,'1','2','7','10'),bidId DESC LIMIT 1 ");
        System.out.println(sql.toString());
        return this.getSession().createSQLQuery(sql.toString()).
                addScalar("bidId", Hibernate.LONG).
                addScalar("state", Hibernate.INTEGER).
                addScalar("bidProName", Hibernate.STRING).
                addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                addScalar("proKeepType", Hibernate.STRING).
                addScalar("novice", Hibernate.INTEGER).
                addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                addScalar("raiseRate", Hibernate.BIG_DECIMAL).
                addScalar("showRate", Hibernate.BIG_DECIMAL).
                addScalar("startMoney", Hibernate.BIG_DECIMAL).
                addScalar("payMoneyTime", Hibernate.INTEGER).
                addScalar("payMoneyTimeType", Hibernate.STRING).
//                addScalar("bdirProId", Hibernate.LONG).
                //addScalar("afterMoney", Hibernate.BIG_DECIMAL).
                //addScalar("theWayBack", Hibernate.BIG_DECIMAL).
                //addScalar("loanLife", Hibernate.BIG_DECIMAL).
                        setResultTransformer(Transformers.aliasToBean(PlBidPlan.class))
                .list();
    }

    @Override
    public List<PlBidPlan> getIndexPlanList1(Integer start, Integer limit) {
        StringBuffer sql = new StringBuffer("select plan.bidId" +
                ", plan.state" +
                ", plan.bidProName" +
                ", plan.yearInterestRate" +
                ", plan.proKeepType" +
                ", plan.raiseRate" +
                ", plan.showRate" +
                ", plan.startMoney" +
                ", plan.payMoneyTime" +
                ", plan.payMoneyTimeType" +
                ", plan.novice,plan.bidMoney,plan.versionType" +
                " from pl_bid_plan plan where ");
        sql.append("state IN ('1', '2', '3', '4', '7', '10') AND isVip=0");
        //sql.append(" (novice IS NULL OR novice !=1)");
        sql.append(" GROUP BY FIELD(state,'1','2','7','10','4','3'),createtime DESC ");
        System.out.println(sql.toString());
        return this.getSession().createSQLQuery(sql.toString()).
                addScalar("bidId", Hibernate.LONG).
                addScalar("state", Hibernate.INTEGER).
                addScalar("bidProName", Hibernate.STRING).
                addScalar("yearInterestRate", Hibernate.BIG_DECIMAL).
                addScalar("proKeepType", Hibernate.STRING).
                addScalar("novice", Hibernate.INTEGER).
                addScalar("bidMoney", Hibernate.BIG_DECIMAL).
                addScalar("raiseRate", Hibernate.BIG_DECIMAL).
                addScalar("showRate", Hibernate.BIG_DECIMAL).
                addScalar("startMoney", Hibernate.BIG_DECIMAL).
                addScalar("payMoneyTime", Hibernate.INTEGER).
                addScalar("payMoneyTimeType", Hibernate.STRING).
                addScalar("versionType", Hibernate.SHORT).
                //addScalar("bdirProId", Hibernate.LONG).
                //addScalar("afterMoney", Hibernate.BIG_DECIMAL).
                //addScalar("theWayBack", Hibernate.BIG_DECIMAL).
                //addScalar("loanLife", Hibernate.BIG_DECIMAL).
                        setResultTransformer(Transformers.aliasToBean(PlBidPlan.class)).
                        setFirstResult(start).setMaxResults(limit)         //.setFirstResult(firstResult).setMaxResults(pb.getPageSize()).list();
                .list();
    }

    @Override
    public Integer getCount() {
        String sql = "SELECT COUNT(bidId) FROM pl_bid_plan WHERE state IN ('1','2','3','4','7','10')";
        BigInteger count = (BigInteger) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        return count.intValue();
    }
}