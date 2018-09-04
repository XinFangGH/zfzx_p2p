package com.hurong.credit.dao.creditFlow.auto;

import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;

public interface PlBidAutoDao extends BaseDao<PlBidAuto>{

	public Integer getOrderNum();
	public PlBidAuto getPlBidAuto(Long userId);
	public boolean isUpdate(PlBidAuto auto);
	public List<String> getCreditlevel();
	public PlBidAuto getByRequestNo(String requestNo);
	public PlBidAuto getByThirdPayConfigId(String thirdPayConfigId);
}
