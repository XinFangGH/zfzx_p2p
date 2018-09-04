package com.hurong.credit.service.financingAgency.manageMoney;

import java.util.List;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.financingAgency.manageMoney.PlManageMoneyType;

public interface PlManageMoneyTypeService extends BaseService<PlManageMoneyType> {

	public List<PlManageMoneyType> getMoneyType(String keyStr,java.lang.Short type);

	public List<PlManageMoneyType> getcurrentType();
}
