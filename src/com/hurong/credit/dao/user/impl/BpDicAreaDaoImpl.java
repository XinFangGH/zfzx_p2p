package com.hurong.credit.dao.user.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.user.BpDicAreaDao;
import com.hurong.credit.model.user.BpDicArea;
import com.hurong.credit.model.user.CsDicAreaDynam;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class BpDicAreaDaoImpl extends BaseDaoImpl<BpDicArea> implements BpDicAreaDao{

	public BpDicAreaDaoImpl() {
		super(BpDicArea.class);
	}
	
	/**
	 * @param parentId 当为0时是省，不为0时为市
	 */
		@Override
		public List<BpDicArea> getAreaList(String ParentCode) {
			// TODO Auto-generated method stub 
			List<BpDicArea> list =null;
			StringBuffer hql = new StringBuffer("from BpDicArea where ParentCode = ").append(ParentCode);
			list =  findByHql(hql.toString());
			
			return list;
		}

}