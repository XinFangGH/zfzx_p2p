package com.hurong.credit.dao.creditFlow.auto.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.auto.PlBidAutoDao;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;

public class PlBidAutoDaoImpl extends BaseDaoImpl<PlBidAuto>  implements PlBidAutoDao {

	public PlBidAutoDaoImpl(){
		super(PlBidAuto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getOrderNum() {
		String sql ="select MAX(auto.orderNum) from pl_bid_auto as auto";
		List<Integer> list = this.getSession().createSQLQuery(sql).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PlBidAuto getPlBidAuto(Long userId) {
		String sql ="select * from pl_bid_auto as auto where auto.userID=?";
		List<PlBidAuto> list =this.getSession().createSQLQuery(sql).addEntity(PlBidAuto.class).setParameter(0, userId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean isUpdate(PlBidAuto auto) {
		boolean fag= false;
		try {
			String sql = "update  pl_bid_auto as auto set auto.bidMoney=?,auto.interestStart=?,auto.interestEnd=?," 
						+"auto.periodStart=?,auto.periodEnd=?,auto.rateStart=?,auto.rateEnd=?,auto.keepMoney=? where auto.id=?";
			int row = this.getSession().createSQLQuery(sql)
						.setParameter(0, auto.getBidMoney())
						.setParameter(1, auto.getInterestStart())
						.setParameter(2, auto.getInterestEnd())
						.setParameter(3, auto.getPeriodStart())
						.setParameter(4, auto.getPeriodEnd())
						.setParameter(5, auto.getRateStart())
						.setParameter(6, auto.getRateEnd())
						.setParameter(7, auto.getKeepMoney())
						.setParameter(8, auto.getId()).executeUpdate();
			if(row>0){
				fag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCreditlevel() {
		String sql ="select keep.name,keep.keyStr from pl_keep_creditlevel as keep order by keyStr asc";
		return this.getSession().createSQLQuery(sql).list();
	}

	@Override
	public PlBidAuto getByRequestNo(String requestNo) {
		String sql ="select * from pl_bid_auto as auto where auto.requestNo=?";
		List<PlBidAuto> list =this.getSession().createSQLQuery(sql).addEntity(PlBidAuto.class).setParameter(0, requestNo).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public PlBidAuto getByThirdPayConfigId(String thirdPayConfigId) {
		String sql="SELECT * from pl_bid_auto as pla  LEFT JOIN bp_cust_member as bpm on bpm.id=pla.userID " +
				"where bpm.thirdPayFlagId=?";
		List<PlBidAuto> list=this.getSession().createSQLQuery(sql).addEntity(PlBidAuto.class).setParameter(0, thirdPayConfigId).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
