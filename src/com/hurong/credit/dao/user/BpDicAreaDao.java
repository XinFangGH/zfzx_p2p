package com.hurong.credit.dao.user;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.user.BpDicArea;

/**
 * 
 * @author 
 *
 */
public interface BpDicAreaDao extends BaseDao<BpDicArea>{
	public List<BpDicArea> getAreaList(String ParentCode);
}