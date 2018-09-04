package com.hurong.credit.service.idcard;

import javax.servlet.http.HttpServletResponse;

import com.hurong.credit.model.pay.MoneyMoreMore;
import com.hurong.credit.model.pay.ResultBean;

public interface IdCardService {

	/**
	 * 身份查询接口
	 * cardId 查询身份证号
	 */
	public String idCardQuery(String cardId);
	/**
	 * 身份证查询接口
	 * returnType：查询返回类型，single：单条查询，batch：多条查询
	 * queryType: 查询类型，身份证、学历、
	 * data: 查询数据
	 * */
	public String idCardQuery(String returnType,String queryType,String data);
	
}
