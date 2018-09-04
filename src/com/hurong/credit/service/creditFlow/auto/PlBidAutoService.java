package com.hurong.credit.service.creditFlow.auto;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.auto.PlBidAuto;

public interface PlBidAutoService extends BaseService<PlBidAuto> {

	public Integer getOrderNum();
	public PlBidAuto getPlBidAuto(Long userId);
	public boolean isUpdate(PlBidAuto auto);
	public List<String> getCreditlevel();
	/**
	 * 检查用户是否具备开通自动投标的权限
	 * @param userid
	 * @return
	 */
	public String[] chk(Long userid);
	/**
	 * 保存自动投标数据校验
	 * @param PlBidAuto auto
	 * @return
	 */
	public String savechk(PlBidAuto auto);
	public PlBidAuto getByRequestNo(String string);
	public PlBidAuto getByThirdPayConfigId(String ThirdPayConfigId);
}
