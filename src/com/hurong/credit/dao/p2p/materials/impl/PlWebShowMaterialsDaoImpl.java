package com.hurong.credit.dao.p2p.materials.impl;
/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.dao.p2p.materials.PlWebShowMaterialsDao;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.p2p.materials.PlWebShowMaterials;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("unchecked")
public class PlWebShowMaterialsDaoImpl extends BaseDaoImpl<PlWebShowMaterials> implements PlWebShowMaterialsDao{

	public PlWebShowMaterialsDaoImpl() {
		super(PlWebShowMaterials.class);
	}

	@Override
	public List<PlWebShowMaterials> getByPidAndType(String pid,
			String businessType) {
		String hql="from PlWebShowMaterials m where m.projId=? and m.operationTypeKey=?";
		Object[] params={pid,businessType};
		return findByHql(hql, params);
	}

	@Override
	public List<FileForm> getImgUrl(String mark) {
		String hql="from FileForm cf where cf.mark=?";
		Object[] params={mark};
		return (List<FileForm>) this.getSession().createQuery(hql).setParameter(0, mark).list();
	}

	@Override
	public String getMaterialImgUrl(String string) {
		List list=null;
		String ret="";
		Map<String, String> params=new HashMap<String, String>();
		params.put("mark", string);
		if(AppUtil.getProjStr().equals("proj_duorongyi")){
		 list=this.executeSqlFind("cs_file","filename",params);
		}else{
			 list=this.executeSqlFind("cs_file","filepath",params);
		}
		if(list!=null&&list.size()>0){
			ret= list.get(0).toString();
		}else{
			ret= "0";
		}
		return ret;
	
	}

}