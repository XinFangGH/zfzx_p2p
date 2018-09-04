package com.hurong.credit.dao.creditFlow.financingAgency.persion;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.creditFlow.financingAgency.persion.BpPersionDirPro;

import java.util.List;

/**
 * 
 * @author 
 *
 */
public interface BpPersionDirProDao extends BaseDao<BpPersionDirPro>{

    List<BpPersionDirPro> getInfo(Long pdirProId);
}