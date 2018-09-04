package com.hurong.credit.dao.creditFlow.creditAssignment.bank.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.RowMapper;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.creditAssignment.bank.ObAccountDealInfoDao;
import com.hurong.credit.model.creditFlow.creditAssignment.bank.ObAccountDealInfo;
import com.hurong.credit.model.creditFlow.finance.SlFundIntent;
import com.hurong.credit.model.creditFlow.fund.project.BpFundProject;



/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class ObAccountDealInfoDaoImpl extends BaseDaoImpl<ObAccountDealInfo> implements ObAccountDealInfoDao{

	public ObAccountDealInfoDaoImpl() {
		super(ObAccountDealInfo.class);
	}
	@Override
	public List<ObAccountDealInfo> getDealList(String investPersonName,
			String transferDate,String flag) {
		// TODO Auto-generated method stub
		try{
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
			String  hql="from ObAccountDealInfo as dealInfo where 1=1 ";
			if(investPersonName!=null&&!"".equals(investPersonName)&&!"null".equals(investPersonName)){
				hql=hql+"  and dealInfo.investPersonName like '%"+investPersonName+"%'";
			} 
			if(transferDate!=null&&!"".equals(transferDate)&&!"null".equals(transferDate)){
				hql=hql+"  and dealInfo.transferDate >=" +format1.parse(transferDate);
			}
			if("2".equals(flag)){
				hql=hql+" and dealInfo.transferType=2";
			}else if("1".equals(flag)){
				hql=hql+" and dealInfo.transferType=1";
			}
			hql =hql+" order by createDate desc";
			return this.getSession().createQuery(hql).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	//查询充值审批确认页面
	@Override
	public List<ObAccountDealInfo> getRechargeDealList(String investPersonName,
			String seniorValidationRechargeStatus,
			String rechargeConfirmStatus, String flag,String rechargeLevel) {
		// TODO Auto-generated method stub
		try{
			String  hql="from ObAccountDealInfo as dealInfo where 1=1 ";
			if(investPersonName!=null&&!"".equals(investPersonName)&&!"null".equals(investPersonName)){
				hql=hql+"  and dealInfo.investPersonName like '%"+investPersonName+"%'";
			}
			/*if("3".equals(rechargeLevel)){
				hql =hql+"  and dealInfo.rechargeConfirmStatus =1 ";
			}else{
				hql =hql+"  and dealInfo.rechargeConfirmStatus =0 ";
			}*/
			if("2".equals(flag)){//用来确认是充值还是取现
				hql=hql+" and dealInfo.transferType=2 ";
			}else if("1".equals(flag)){
				hql=hql+" and dealInfo.transferType=1";
			}
			hql =hql+" order by dealInfo.createDate desc";
			return this.getSession().createQuery(hql).list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getCreateNameByCreateId(Long createId) {
		String hql="select a.fullname as username from AppUser as a where a.userId="+createId;
		List list=this.getSession().createQuery(hql).list();
		return list.isEmpty()?"":list.get(0).toString();
	}
	
	
	//根据投资人id  投资人债权id  以及账户交易类型
	@Override
	public ObAccountDealInfo getDealInfo(Long investMentPersonId, Long id,
			String flag) {
		// TODO Auto-generated method stub
		ObAccountDealInfo info =null;
		String  hql =" from ObAccountDealInfo as dealInfo where dealInfo.investPersonId=? and dealInfo.investObligationInfoId =? and dealInfo.transferType =? order by dealInfo.createDate desc ";
		List<ObAccountDealInfo> list=this.getSession().createQuery(hql).setParameter(0, Long.valueOf(investMentPersonId)).setParameter(1,Long.valueOf(id)).setParameter(2, flag).list();
		if(list!=null&&list.size()>0){
			info=list.get(0);
		}
		return info;
	}
	@Override
	public List<ObAccountDealInfo> queyAccountInfoRecord(Long accountId,
			String transferType, Short dealRecordStatus,
			Short rechargeConfirmStatus, HttpServletRequest request,Integer start, Integer limit) {
		try{
			String sql ="select " +
						"ob.id," +
						"ob.accountId," +
						"ob.incomMoney," +
					    "ob.payMoney," +
					    "ob.currentMoney," +
					    "ob.transferType," +
					    "ob.shopId,"+
					    "ob.shopName," +
					    "ob.dealType," +
					    "ob.transferDate," +
					    "ob.investPersonName," +
					    "ob.rechargeLevel," +
					    "ob.dealRecordStatus,"+
					    "ob.runId,"+
					    "p.fullname as createName"+
					    " from ob_account_deal_info as ob left join app_user as p on (ob.createId = p.userId) where 1=1 ";
			if(dealRecordStatus!=null){
				sql=sql+" and ob.dealRecordStatus="+dealRecordStatus;
			}
			if(transferType!=null){
				sql=sql+" and ob.transferType='"+transferType+"'";
			}
			if(accountId!=null){
				sql=sql+" and ob.accountId ="+accountId;
			}
			String investPersonName =request.getParameter("investPersonName");
			if(investPersonName!=null && !"".endsWith(investPersonName)){
				sql=sql+" and ob.investPersonName like'%"+investPersonName+"%'";
			}
			System.out.println(sql);
			List list=null;
			if(start==null || limit ==null){
				list =this.getSession().createSQLQuery(sql).
				 addScalar("id",Hibernate.LONG).
				  addScalar("accountId",Hibernate.LONG).
				  addScalar("incomMoney",Hibernate.BIG_DECIMAL).
				  addScalar("payMoney",Hibernate.BIG_DECIMAL).
				  addScalar("currentMoney",Hibernate.BIG_DECIMAL).
				  addScalar("transferType",Hibernate.STRING).
				  addScalar("shopId",Hibernate.LONG).
				  addScalar("shopName",Hibernate.STRING).
				  addScalar("dealType",Hibernate.SHORT).
				  addScalar("transferDate",Hibernate.DATE).
				  addScalar("investPersonName",Hibernate.STRING).
				  addScalar("rechargeLevel",Hibernate.SHORT).
				  addScalar("dealRecordStatus",Hibernate.SHORT).
				  addScalar("runId",Hibernate.LONG).
				  addScalar("createName",Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).
				list();
			}else{
				list =this.getSession().createSQLQuery(sql).
				  addScalar("id",Hibernate.LONG).
				  addScalar("accountId",Hibernate.LONG).
				  addScalar("incomMoney",Hibernate.BIG_DECIMAL).
				  addScalar("payMoney",Hibernate.BIG_DECIMAL).
				  addScalar("currentMoney",Hibernate.BIG_DECIMAL).
				  addScalar("transferType",Hibernate.STRING).
				  addScalar("shopId",Hibernate.LONG).
				  addScalar("shopName",Hibernate.STRING).
				  addScalar("dealType",Hibernate.SHORT).
				  addScalar("transferDate",Hibernate.DATE).
				  addScalar("investPersonName",Hibernate.STRING).
				  addScalar("rechargeLevel",Hibernate.SHORT).
				  addScalar("dealRecordStatus",Hibernate.SHORT).
				  addScalar("runId",Hibernate.LONG).
				  addScalar("createName",Hibernate.STRING).
				  setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).
				  setFirstResult(start).setMaxResults(limit).
				  list();
			}
			
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public BigDecimal prefreezMoney(Long accountId, String direction) {
		// TODO Auto-generated method stub
		if(direction!=null&&direction.equals(ObAccountDealInfo.T_ENCHASHMENT)){
			String hql="select SUM(payMoney) from ObAccountDealInfo where accountId=? and transferType='4' and dealRecordStatus='7' or (transferType='2' and dealRecordStatus='5')";
			return (BigDecimal) this.getSession().createQuery(hql).setParameter(0, accountId).uniqueResult();

		}else{
			String  hql =" select sum(ob.payMoney) from ObAccountDealInfo as ob where ob.accountId="+accountId+"and ob.dealRecordStatus="+ObAccountDealInfo.DEAL_STATUS_7;
			if(direction!=null&&direction.equals(ObAccountDealInfo.T_ENCHASHMENT)){
				hql=hql+"and ob.transferType='"+ObAccountDealInfo.T_ENCHASHMENT+"'";
			}else if(direction!=null&&direction.equals(ObAccountDealInfo.T_INVEST)){
				hql=hql+" and ob.transferType='"+ObAccountDealInfo.T_INVEST+"'";
			}
			return (BigDecimal) this.getSession().createQuery(hql).uniqueResult();
		}
	}
	@Override
	public BigDecimal typeTotalMoney(Long userId,Long accountId, String transferType) {
		String hql="";
		if(transferType.equals(ObAccountDealInfo.T_RECHARGE) ||transferType.equals(ObAccountDealInfo.T_PRINCIALBACK)||transferType.equals(ObAccountDealInfo.T_PROFIT)){
			 hql =" select sum(ob.incomMoney) from ObAccountDealInfo as ob where ob.accountId="+accountId+" and ob.dealRecordStatus=2 and ob.transferType='"+transferType+"'";
		}else if(transferType.equals(ObAccountDealInfo.T_INMONEY)){
			hql =" select sum(ob.incomMoney) from ObAccountDealInfo as ob where ob.accountId="+accountId+" and ob.dealRecordStatus=2 and ob.transferType='"+transferType+"'";
		}else if(transferType.equals(ObAccountDealInfo.T_LOANPAY)){
			hql =" select sum(ob.payMoney) from ObAccountDealInfo as ob where ob.accountId="+accountId+" and ob.dealRecordStatus=2 and ob.transferType='"+transferType+"'";
		}else if(transferType.equals(ObAccountDealInfo.T_N_REPAYMENT)){
			if(userId==null){
				return new BigDecimal(0); 
				
			}else{
				hql = " select sum(slf.notMoney) from SlFundIntent  slf where slf.projectId in (select bf.projectId from BpFundProject bf where   bf.oppositeID ="+userId+" group by bf.projectId) and (slf.fundType='serviceMoney' or slf.fundType='consultationMoney' or slf.fundType='loanInterest' or slf.fundType='principalRepayment')";
			}
			

		}else{
			 hql =" select sum(ob.payMoney) from ObAccountDealInfo as ob where ob.accountId="+accountId+" and ob.dealRecordStatus=2 and ob.transferType='"+transferType+"'";
		}
		
		return (BigDecimal) this.getSession().createQuery(hql).uniqueResult();
	}
	
	/**查询投资人账户总记录*/
	@Override
	public List<ObAccountDealInfo> getaccountListQuery(String accountId,
			HttpServletRequest request, Integer start, Integer limit){
			String sql ="select adc.* from (" +
			"select " +
			"ob.id ," +
			"ob.accountId," +
			"ob.incomMoney," +
		    "ob.payMoney," +
		    "ob.currentMoney," +
		    "ob.transferType," +
		    "ob.shopName," +
		    "ob.dealType," +
		    "ob.createDate," +
		    "ob.transferDate," +
		    "ob.investPersonName," +
		    "ob.rechargeLevel," +
		    "ob.dealRecordStatus,"+
		    "ob.thirdPayRecordNumber,"+
		    "ob.msg,"+
		    "ob.recordNumber,"+
		    "accountSetting.typeName as transferTypeName,"+
		    "IF(ob.transferDate is null,ob.createDate,ob.transferDate) as orderDate" +
		    " from ob_account_deal_info as ob  " +
		    " left join ob_systemaccount_setting as accountSetting on (ob.transferType=accountSetting.typeKey ) " +
		    " where  " +
		    " ob.accountId="+Long.valueOf(accountId)+
			")as adc  where 1=1 ";
			if(null!=request){
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate) ) {
					if(startDate.equals(endDate)){
						sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
						sql+=" and createDate<="+"'"+endDate+" 23:59:59"+"'";
					}else{
						if(null!=startDate&&!"".equals(startDate)){
							sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
						}
						if(null!=endDate&&!"".equals(endDate)){
							sql+=" and createDate<="+"'"+endDate+" 23:59:59"+"'";
						}
					}
				}else{
					if(null!=startDate&&!"".equals(startDate)){
						sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
					}
					if(null!=endDate&&!"".equals(endDate)){
						sql+=" and createDate<="+"'"+endDate+" 00:00:00"+"'";
					}
				}
				String transferType = request.getParameter("transferType");
				if(null!=transferType&&!"".equals(transferType)){
					sql+=" and transferType="+"'"+transferType+"'";
				}
				
				String isAccountSuccess = request.getParameter("isAccountSuccess");
				if(null!=isAccountSuccess && "1".equals(isAccountSuccess)){
					sql+=" and  dealRecordStatus in (2,7)";
				}
				if(null!=isAccountSuccess && "2".equals(isAccountSuccess)){
					sql+=" and  dealRecordStatus in (1,3,4,5,6,8)";
				}
				
			}
			sql+=" ORDER BY adc.orderDate DESC";
			System.out.println(sql);
			List list=null;
			if(start==null || limit ==null){
				list =this.getSession().createSQLQuery(sql).addScalar("id",Hibernate.LONG).
				  addScalar("accountId",Hibernate.LONG).
				  addScalar("incomMoney",Hibernate.BIG_DECIMAL).
				  addScalar("payMoney",Hibernate.BIG_DECIMAL).
				  addScalar("currentMoney",Hibernate.BIG_DECIMAL).
				  addScalar("transferType",Hibernate.STRING).
				  addScalar("shopName",Hibernate.STRING).
				  addScalar("dealType",Hibernate.SHORT).
				  addScalar("createDate",Hibernate.TIMESTAMP).
				  addScalar("transferDate",Hibernate.TIMESTAMP).
				  addScalar("investPersonName",Hibernate.STRING).
				  addScalar("thirdPayRecordNumber",Hibernate.STRING).
				  addScalar("rechargeLevel",Hibernate.SHORT).
				  addScalar("dealRecordStatus",Hibernate.SHORT).
				  addScalar("msg",Hibernate.STRING).
				  addScalar("recordNumber",Hibernate.STRING).
				  addScalar("transferTypeName",Hibernate.STRING).
				  addScalar("orderDate",Hibernate.TIMESTAMP).
				  setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).list();
			}else{
				list =this.getSession().createSQLQuery(sql).
				  addScalar("id",Hibernate.LONG).
				  addScalar("accountId",Hibernate.LONG).
				  addScalar("incomMoney",Hibernate.BIG_DECIMAL).
				  addScalar("payMoney",Hibernate.BIG_DECIMAL).
				  addScalar("currentMoney",Hibernate.BIG_DECIMAL).
				  addScalar("transferType",Hibernate.STRING).
				  addScalar("shopName",Hibernate.STRING).
				  addScalar("dealType",Hibernate.SHORT).
				  addScalar("createDate",Hibernate.TIMESTAMP).
				  addScalar("transferDate",Hibernate.TIMESTAMP).
				  addScalar("investPersonName",Hibernate.STRING).
				  addScalar("thirdPayRecordNumber",Hibernate.STRING).
				  addScalar("rechargeLevel",Hibernate.SHORT).
				  addScalar("dealRecordStatus",Hibernate.SHORT).
				  addScalar("msg",Hibernate.STRING).
				  addScalar("recordNumber",Hibernate.STRING).
				  addScalar("transferTypeName",Hibernate.STRING).
				  addScalar("orderDate",Hibernate.TIMESTAMP).
				  setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).
				  setFirstResult(start).setMaxResults(limit).
				  list();
			}
		return list;
	}

	@Override
	public List<ObAccountDealInfo> getaccountListQuery1(String accountId, HttpServletRequest request, Integer start, Integer limit) {
		String sql ="select adc.* from (" +
				"select " +
				"ob.id ," +
				"ob.accountId," +
				"ob.incomMoney," +
				"ob.payMoney," +
				"ob.currentMoney," +
				"ob.transferType," +
				"ob.shopName," +
				"ob.dealType," +
				"ob.createDate," +
				"ob.transferDate," +
				"ob.investPersonName," +
				"ob.rechargeLevel," +
				"ob.dealRecordStatus,"+
				"ob.thirdPayRecordNumber,"+
				"ob.msg,"+
				"ob.recordNumber,"+
				"accountSetting.typeName as transferTypeName,"+
				"IF(ob.transferDate is null,ob.createDate,ob.transferDate) as orderDate" +
				" from ob_account_deal_info as ob  " +
				" left join ob_systemaccount_setting as accountSetting on (ob.transferType=accountSetting.typeKey ) " +
				" where  " +
				" ob.accountId="+Long.valueOf(accountId)+
				" and ob.dealRecordStatus != 1"+
				")as adc  where 1=1 ";
		if(null!=request){
//			String startDate = request.getParameter("startDate");
//			String endDate = request.getParameter("endDate");
//			if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate) ) {
//				if(startDate.equals(endDate)){
//					sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
//					sql+=" and createDate<="+"'"+endDate+" 23:59:59"+"'";
//				}else{
//					if(null!=startDate&&!"".equals(startDate)){
//						sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
//					}
//					if(null!=endDate&&!"".equals(endDate)){
//						sql+=" and createDate<="+"'"+endDate+" 23:59:59"+"'";
//					}
//				}
//			}else{
//				if(null!=startDate&&!"".equals(startDate)){
//					sql+=" and createDate>="+"'"+startDate+" 00:00:00"+"'";
//				}
//				if(null!=endDate&&!"".equals(endDate)){
//					sql+=" and createDate<="+"'"+endDate+" 00:00:00"+"'";
//				}
//			}             {"["2","3","4"]"}----> "2","3","4"         {"2","3","4"}
		//String[] transferTypes = request.getParameterValues("transferType");
			String transferTypes = request.getParameter("transferType");
			if(transferTypes!=null){
				String transferType1 = new String();
				transferType1 = transferTypes.replace("[","");
				String transferType = new String();
				transferType = transferType1.replace("]","");
				System.out.println(transferType+"");
				if(null!=transferType && !"".equals(transferType) && !"0".equals(transferType)){
					sql+=" and transferType in("+transferType+")";
				}
				if(null!=transferType && !"".equals(transferType) && "0".equals(transferType)){
					sql+="";
				}
			}
//			if(transferTypes!=null){
//				for(int i=0;i<transferTypes.length;i++){
//					if(i>0){
//						transferType.append(",");
//					}
//					transferType.append("").append(transferTypes[i]).append("");
//					System.out.println(transferType+ "");
//				}
//			}

//			String transferType = "";


//			String transferType = request.getParameter("transferType");
//			if(null!=transferType && !"".equals(transferType) && !"0".equals(transferType)){
//				sql+=" and transferType="+"'"+transferType+"'";
//			}
//			if(null!=transferType && !"".equals(transferType) && "0".equals(transferType)){
//				sql+="";
//			}
//			String isAccountSuccess = request.getParameter("isAccountSuccess");
//			if(null!=isAccountSuccess && "1".equals(isAccountSuccess)){
//				sql+=" and  dealRecordStatus in (2,7)";
//			}
//			if(null!=isAccountSuccess && "2".equals(isAccountSuccess)){
//				sql+=" and  dealRecordStatus in (1,3,4,5,6,8)";
//			}

			String createDate = request.getParameter("createDate");
			if(null!=createDate && "14".equals(createDate)){
				sql+="";
			}
			if(null!=createDate && "15".equals(createDate)){
				sql+=" and createDate BETWEEN DATE_SUB(NOW(),INTERVAL 1 MONTH) AND NOW()";
			}
			if(null!=createDate && "16".equals(createDate)){
				sql+=" and createDate BETWEEN DATE_SUB(NOW(),INTERVAL 3 MONTH) AND NOW()";
			}
		}
		sql+=" ORDER BY adc.orderDate DESC";
		System.out.println(sql);
		List list=null;
		if(start==null || limit ==null){
			list =this.getSession().createSQLQuery(sql).addScalar("id",Hibernate.LONG).
					addScalar("accountId",Hibernate.LONG).
					addScalar("incomMoney",Hibernate.BIG_DECIMAL).
					addScalar("payMoney",Hibernate.BIG_DECIMAL).
					addScalar("currentMoney",Hibernate.BIG_DECIMAL).
					addScalar("transferType",Hibernate.STRING).
					addScalar("shopName",Hibernate.STRING).
					addScalar("dealType",Hibernate.SHORT).
					addScalar("createDate",Hibernate.TIMESTAMP).
					addScalar("transferDate",Hibernate.TIMESTAMP).
					addScalar("investPersonName",Hibernate.STRING).
					addScalar("thirdPayRecordNumber",Hibernate.STRING).
					addScalar("rechargeLevel",Hibernate.SHORT).
					addScalar("dealRecordStatus",Hibernate.SHORT).
					addScalar("msg",Hibernate.STRING).
					addScalar("recordNumber",Hibernate.STRING).
					addScalar("transferTypeName",Hibernate.STRING).
					addScalar("orderDate",Hibernate.TIMESTAMP).
					setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).list();
		}else{
			list =this.getSession().createSQLQuery(sql).
					addScalar("id",Hibernate.LONG).
					addScalar("accountId",Hibernate.LONG).
					addScalar("incomMoney",Hibernate.BIG_DECIMAL).
					addScalar("payMoney",Hibernate.BIG_DECIMAL).
					addScalar("currentMoney",Hibernate.BIG_DECIMAL).
					addScalar("transferType",Hibernate.STRING).
					addScalar("shopName",Hibernate.STRING).
					addScalar("dealType",Hibernate.SHORT).
					addScalar("createDate",Hibernate.TIMESTAMP).
					addScalar("transferDate",Hibernate.TIMESTAMP).
					addScalar("investPersonName",Hibernate.STRING).
					addScalar("thirdPayRecordNumber",Hibernate.STRING).
					addScalar("rechargeLevel",Hibernate.SHORT).
					addScalar("dealRecordStatus",Hibernate.SHORT).
					addScalar("msg",Hibernate.STRING).
					addScalar("recordNumber",Hibernate.STRING).
					addScalar("transferTypeName",Hibernate.STRING).
					addScalar("orderDate",Hibernate.TIMESTAMP).
					setResultTransformer(Transformers.aliasToBean(ObAccountDealInfo.class)).
					setFirstResult(start).setMaxResults(limit).
					list();
		}
		return list;
	}

	/*	class  rowMapper implements RowMapper {

            @Override
            public Object mapRow(ResultSet rs, int arg1) throws SQLException {
                ObAccountDealInfo income = new ObAccountDealInfo();
                income.setFundId(rs.getLong("fundId"));
                income.setFundType(rs.getString("fundType"));
                income.setFundTypeName(rs.getString("fundTypeName"));
                income.setBidPlanId(rs.getLong("bidPlanId"));
                income.setBidPlanName(rs.getString("bidPlanName"));
                income.setBidPlanNumber(rs.getString("bidPlanNumber"));
                income.setBorrowerId(rs.getLong("borrowerId"));
                income.setBorrowerName(rs.getString("borrowerName"));
                income.setP2pborrowerId(rs.getLong("p2pborrowerId"));
                income.setP2pborrowerName(rs.getString("p2pborrowerName"));
                income.setPlanIncomeMoney(rs.getBigDecimal("planIncomeMoney"));
                income.setPlanReciveDate(rs.getDate("planReciveDate"));
                income.setFactIncomeMoney(rs.getBigDecimal("factIncomeMoney"));
                income.setFactReciveDate(rs.getDate("factReciveDate"));
                income.setNotMoney(rs.getBigDecimal("notMoney"));
                return income;
            }

        }*/
	@Override
	public ObAccountDealInfo getDealinfo(Long accountId, Long investPersonId,Short investPersonType,
			String recordNumber, Long accountInfoId) {
		// TODO Auto-generated method stub
		String  hql="from ObAccountDealInfo as info where info.accountId="+accountId;
		if(null!=investPersonId&&!"".equals(investPersonId)){
			hql+=" and info.investPersonId="+Long.valueOf(investPersonId)+" and info.investPersonType="+investPersonType;
		}
		if(null!=recordNumber&&!"".equals(recordNumber)){
			hql+=" and info.recordNumber='"+recordNumber+"'";
		}
		if(null!=accountInfoId){
			hql+=" and info.id="+accountInfoId;
		}
		ObAccountDealInfo info =null;
		List<ObAccountDealInfo>  list =this.getSession().createQuery(hql).list();
		if(list!=null&&list.size()>0){
			info=list.get(0);
		}
		return info;
	}
	@Override
	public ObAccountDealInfo getByOrderNumber(String orderNo, String totalFee,
			String transferType, String type0) {
		String hql =" from ObAccountDealInfo as ob where ob.recordNumber=? and ob.investPersonType=? and ob.transferType=? ";
		return (ObAccountDealInfo) this.getSession().createQuery(hql).setParameter(0, orderNo).setParameter(1, Short.valueOf(type0)).setParameter(2, transferType).uniqueResult();
	}
	@Override
	public List<ObAccountDealInfo> getInfoByInverPersionId(Long accountId) {
		// TODO Auto-generated method stub
		String hql="from ObAccountDealInfo as i where i.accountId='"+accountId+"' order by transferDate desc";
		System.out.println("hql:"+hql);
		return findByHql(hql);
	}
	
	@Override
	public BigDecimal sumBytype(String transferType, String dealRecordStatus,Long investPersonId) {
		String hql="";
		if(transferType.equals("1")){
			 hql =" select sum (ob.incomMoney) as sumMoney  from ObAccountDealInfo as ob where ob.transferType=? and ob.dealRecordStatus=? and ob.investPersonId=?";
		}else{
			 hql =" select sum (ob.payMoney) as sumMoney  from ObAccountDealInfo as ob where ob.transferType=? and  ob.dealRecordStatus=?  and ob.investPersonId=?";
		}
		System.out.println(hql);
		BigDecimal a=(BigDecimal)this.getSession().createQuery(hql).setParameter(0, transferType).setParameter(1, Short.valueOf(dealRecordStatus)).setParameter(2, investPersonId).uniqueResult();
		if(null!=a){

			return  a;
		}else{

			return new BigDecimal("0");
		}
		
	}

		@Override
		public BigDecimal sumPersonMoney(String transferType,
				Long investPersonId, String startTime, String endTime) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String hql="";
			if(transferType.equals("1")){
				 hql =" select sum (ob.incomMoney) as sumMoney  from ObAccountDealInfo as ob " +
				 		" where ob.transferType=? and ob.dealRecordStatus=2 and ob.investPersonId=? " +
				 		" and transferDate >= ? and transferDate <= ?";
			}else{
				 hql =" select sum (ob.payMoney) as sumMoney  from ObAccountDealInfo as ob " +
				 		" where ob.transferType=? and  ob.dealRecordStatus=2  and ob.investPersonId=? " +
				 		" and transferDate >= ? and transferDate <= ?";
			}
			try {
				BigDecimal sumMoney = (BigDecimal)this.getSession().createQuery(hql).setParameter(0, transferType)
									.setParameter(1, investPersonId).setParameter(2, format.parse(startTime))
									.setParameter(3, format.parse(endTime)).uniqueResult();
				if(null!=sumMoney){
					return sumMoney;
				}else{
					return new BigDecimal("0");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		@Override
		public List<ObAccountDealInfo> queryThreeDealInfo(String accountId,
				String transferType, String dealRecordStatus) {
			String hql="";
			if(accountId==null){
				 hql="select * from ob_account_deal_info as i where   transferType=? and dealRecordStatus=? and now()-createDate>900 and msg is not null";
				 List<ObAccountDealInfo>  list =this.getSession().createSQLQuery(hql).addEntity(ObAccountDealInfo.class).setParameter(0, transferType).setParameter(1, Short.valueOf(dealRecordStatus)).list();
				return  list;
			}else{
				 hql="select * from ob_account_deal_info as i where i.accountId=? and  transferType=? and dealRecordStatus=? and now()-createDate>900 and msg is not null";
				 List<ObAccountDealInfo>  list =this.getSession().createSQLQuery(hql).addEntity(ObAccountDealInfo.class).setParameter(0, accountId).setParameter(1, transferType).setParameter(2, Short.valueOf(dealRecordStatus)).list();
				return  list;
			}
		}
		@Override
		public BigDecimal sumMoneyByTypeAndState(String transferType,
				Short dealRecordStatus, Integer direction) {
			BigDecimal result = BigDecimal.ZERO;
			StringBuffer hql=new StringBuffer("select sum ");
			if (direction==1) {
				hql.append(" (ob.incomMoney) as sumMoney  from ObAccountDealInfo as ob where 1=1 ");
			}else {
				hql.append(" (ob.payMoney) as sumMoney  from ObAccountDealInfo as ob where 1=1 ");
			}
			if (StringUtils.isNotEmpty(transferType)) {
				hql.append(" and ob.transferType=").append(transferType);
			}
			if (dealRecordStatus != null) {
				hql.append(" and ob.dealRecordStatus=").append(dealRecordStatus);
			}else {
				hql.append(" and ob.dealRecordStatus=2");
			}
			result = (BigDecimal) this.getSession().createQuery(hql.toString()).uniqueResult();
			if (result != null) {
				return result;
			}else {
				return BigDecimal.ZERO;
			}
		}



}