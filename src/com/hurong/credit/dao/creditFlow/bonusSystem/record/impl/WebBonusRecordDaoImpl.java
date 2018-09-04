package com.hurong.credit.dao.creditFlow.bonusSystem.record.impl;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.bonusSystem.record.WebBonusRecordDao;
import com.hurong.credit.model.creditFlow.bonusSystem.WebBonusConstant;
import com.hurong.credit.model.creditFlow.bonusSystem.record.WebBonusRecord;
import com.hurong.credit.model.creditFlow.bonusSystem.setting.WebBonusSetting;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyPlanBuyinfo;
import com.hurong.credit.model.p2p.BpPersonCenterData;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class WebBonusRecordDaoImpl extends BaseDaoImpl<WebBonusRecord> implements WebBonusRecordDao{

	public WebBonusRecordDaoImpl() {
		super(WebBonusRecord.class);
	}


	@Override
	public List<WebBonusRecord> findBySettingEngine(Long userId,WebBonusSetting webBonusSetting) {
		
		/**
		 * 查询积分规则内的有积分记录
		 */
		//webBonusSetting.getIsBonus();//积分增加还是减少
		//webBonusSetting.getBonusId();//积分ID
		//通过奖励周期，和奖励周期类型计算出createTime
		//奖励周期类型分为四类
			//分钟，查当前时间减去这个分钟之间的所有记录数
			//小时，查当前时间减去这个小时之间的所有记录数
			//天,  查当前时间减去这个天数之间的所有记录数
		//webBonusSetting.getBomusPeriod();//奖励周期
		//webBonusSetting.getBomusPeriodType();//奖励周期类型
		
		StringBuffer sb = new StringBuffer("from WebBonusRecord as w  where 1=1 ");
		sb.append(" and w.customerId = ").append("'").append(userId.toString()).append("'");
		sb.append(" and w.recordDirector =  ").append("'").append(webBonusSetting.getIsBonus()).append("'");
		sb.append(" and w.bonusId =  ").append("'").append(webBonusSetting.getBonusId().toString()).append("'");
		
		//String queryDate = DateUtil.integralNextDate(webBonusSetting.getBomusPeriod(), webBonusSetting.getBomusPeriodType());
		String queryDate = integralNextDate(webBonusSetting.getBomusPeriod(), webBonusSetting.getBomusPeriodType());
		sb.append(" and w.createTime >  ").append("'").append(queryDate).append("'");
		
		System.out.println(queryDate);
		
		return findByHql(sb.toString());
		
	}
	
	/**
	 * 下一次的奖励时间
	 * @param bomusPeriod
	 * @param bomusPeriodType
	 * @return
	 */
    public static String integralNextDate(String bomusPeriod ,String bomusPeriodType){
    	SimpleDateFormat formatMin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat formatHour = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    	
    	Date toDate = new Date();
    	Calendar c1 = Calendar.getInstance();
		c1.setTime(toDate);
 //   	System.out.println(formatMin.format(toDate));
    	
    	
		if(WebBonusConstant.BOMUSPERIOD_TYPE_MIN.equals(bomusPeriodType)){
			
    		//如果是类型为分钟，则当前时间减去bomusPeriod分钟
    		c1.add(Calendar.MINUTE,-Integer.parseInt(bomusPeriod));
    		return formatMin.format(c1.getTime());
    		
    	}else if(WebBonusConstant.BOMUSPERIOD_TYPE_HOUR.equals(bomusPeriodType)){
    		
    		//如果是类型为分钟，则当前时间减去bomusPeriod小时
    		c1.add(Calendar.HOUR_OF_DAY,-Integer.parseInt(bomusPeriod));
    		return formatHour.format(c1.getTime());
    		
    	}else if(WebBonusConstant.BOMUSPERIOD_TYPE_DATE.equals(bomusPeriodType)){
    		
    		//如果是类型为天数，则当前时间减去bomusPeriod天数
    		c1.add(Calendar.DAY_OF_MONTH, (-Integer.parseInt(bomusPeriod))  +1);
    		return formatDate.format(c1.getTime());
    		
    	}
		
		return null;
    	
    }


	@Override
	public int findRecordNumber(Long userId, String recordDirector) {
		String sql="select sum(recordNumber) from web_bonus_record where recordDirector=? and customerId=?";
		BigDecimal bigdec =(BigDecimal) this.getSession().createSQLQuery(sql).setParameter(0, recordDirector).setParameter(1, userId).uniqueResult();
		if(bigdec!=null){
			int recordNumber =bigdec.intValue();
			return recordNumber;
		}
		return 0;
	}


	@Override
	public List<WebBonusRecord> getActivityNumber(String activityNumber,
			String bpCustMemberId, String remark) {
		String sql = "select * from web_bonus_record where customerId=? and activityNumber=?";
		return this.getSession().createSQLQuery(sql).setParameter(0, bpCustMemberId).setParameter(1, activityNumber).list();
	}


	@Override
	public List<WebBonusRecord> getMyIntegral(HttpServletRequest request,
			BpCustMember member) {
		List<WebBonusRecord> list = new ArrayList<WebBonusRecord>();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(d);
		Date date=null;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		StringBuffer sql = new StringBuffer("SELECT * from web_bonus_record record WHERE record.customerId = ?");
		//添加查询条件
		//分页
		if(request.getParameter("sel")!=null){
			Integer val =  Integer.valueOf(request.getParameter("sel").toString());
			switch(val){
				case 1:
					Calendar cl = Calendar.getInstance();
				    cl.setTime(date);
				    cl.add(Calendar.DATE,-30);
				    String temp = "";
				    temp = sdf.format(cl.getTime());
					sql.append(" and record.createTime <= '"+sdf.format(date)+"' and record.createTime >= '"+temp+"' " );
					break;
				case 2:
					Calendar cl1 = Calendar.getInstance();
				    cl1.setTime(date);
				    cl1.add(Calendar.DATE,-90);
				    String temp1 = "";
				    temp1 = sdf.format(cl1.getTime());
				    System.out.println(temp1);
				    sql.append(" and record.createTime <= '"+sdf.format(date)+"' and record.createTime >= '"+temp1+"' " );
				    break;
				default:
					
			}
		}
		sql.append(" order by createTime desc");

		if(request.getParameter("start")!=null&&request.getParameter("limit")!=null){
			Integer start  = Integer.valueOf(request.getParameter("start").toString());
			Integer limit  = Integer.valueOf(request.getParameter("limit").toString());
			list = this.getSession().createSQLQuery(sql.toString()).addEntity(WebBonusRecord.class).setParameter(0,member.getId()).setFirstResult(Integer.valueOf(start)).setMaxResults(limit).list();
		}else{//不分页
		   list = this.getSession().createSQLQuery(sql.toString()).addEntity(WebBonusRecord.class).setParameter(0,member.getId()).list();
		}
		return list;
	}


	@Override
	public List<WebBonusRecord> getMyIntegralNum(HttpServletRequest request,
			BpCustMember member) {
		List<WebBonusRecord> list = new ArrayList<WebBonusRecord>();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(d);
		Date date=null;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		StringBuffer sql = new StringBuffer("SELECT * from web_bonus_record record WHERE record.customerId = ?");
		//添加查询条件
		//分页
		if(request.getParameter("sel")!=null){
			Integer val =  Integer.valueOf(request.getParameter("sel").toString());
			switch(val){
				case 1:
					Calendar cl = Calendar.getInstance();
				    cl.setTime(date);
				    cl.add(Calendar.DATE,-30);
				    String temp = "";
				    temp = sdf.format(cl.getTime());
					sql.append(" and record.createTime <= '"+sdf.format(date)+"' and record.createTime >= '"+temp+"' " );
					break;
				case 2:
					Calendar cl1 = Calendar.getInstance();
				    cl1.setTime(date);
				    cl1.add(Calendar.DATE,-90);
				    String temp1 = "";
				    temp1 = sdf.format(cl1.getTime());
				    System.out.println(temp1);
				    sql.append(" and record.createTime <= '"+sdf.format(date)+"' and record.createTime >= '"+temp1+"' " );
				    break;
				default:
			}
		}
		sql.append(" order by createTime desc");
		list = this.getSession().createSQLQuery(sql.toString()).setParameter(0,member.getId()).list();
		return list;
	}


	@Override
	public BpPersonCenterData queryMyIntegral(BpCustMember member) {
		BpPersonCenterData data = new BpPersonCenterData(); 
		String sql="select IF(table1.totalRecordNumber is null,0,table1.totalRecordNumber) AS totalRecordNumber," +
				" IF(table2.useRecordNumber is null,0,table2.useRecordNumber) AS useRecordNumber," +
				" (totalRecordNumber - useRecordNumber) as canUseNumber " +
				"FROM " +
				"(SELECT sum(recordNumber) AS totalRecordNumber " +
				"FROM web_bonus_record WHERE recordDirector = 1 AND customerId ='"+member.getId()+"') as table1, " +
				"( SELECT sum(recordNumber) AS useRecordNumber " +
				"FROM web_bonus_record " +
				"WHERE recordDirector = 2  AND customerId ='"+member.getId()+"' ) as table2";
		System.out.println(">>>>"+sql);
		data = (BpPersonCenterData) this.getSession().createSQLQuery(sql).
													  addScalar("totalRecordNumber", Hibernate.INTEGER).
													  addScalar("useRecordNumber", Hibernate.INTEGER).
													  addScalar("canUseNumber",Hibernate.INTEGER).
													  setResultTransformer(Transformers.aliasToBean(BpPersonCenterData.class)).
													  uniqueResult();
		return data;
	}
}