package com.hurong.credit.service.creditFlow.customer.person;
/*
 *  北京互融时代软件有限公司   -- http://www.hurongtime.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.customer.person.BpCustPersonEducation;

/**
 * 
 * @author 
 *
 */
public interface BpCustPersonEducationService extends BaseService<BpCustPersonEducation>{
	public String getSchool(Long personId);
}


