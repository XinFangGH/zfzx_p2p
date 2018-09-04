package com.hurong.credit.service.creditFlow.customer.person.workcompany;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.service.BaseService;
import com.hurong.credit.model.creditFlow.customer.person.workcompany.WorkCompany;

/**
 * 
 * @author 
 *
 */
public interface WorkCompanyService extends BaseService<WorkCompany>{
	public WorkCompany getWorkCompanyByPersonId(int personId);
	
}


