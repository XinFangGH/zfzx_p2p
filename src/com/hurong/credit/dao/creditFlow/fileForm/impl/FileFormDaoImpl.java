package com.hurong.credit.dao.creditFlow.fileForm.impl;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.creditFlow.fileForm.FileFormDao;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;

import java.util.List;

public class FileFormDaoImpl extends BaseDaoImpl<FileForm> implements FileFormDao{

	public FileFormDaoImpl() {
		super(FileForm.class);
	}

	//新增图片上传读取
	@Override
	public List<FileForm> getAllEnterpriseImg(Integer id) {
		StringBuffer sql = new StringBuffer("select * from cs_file where mark in (");
		//上传文件的关键字
		sql.append("'cs_enterprise_dsdjz.").append(id).append("',").
				append("'cs_enterprise_gsdjz.").append(id).append("',").
				append("'cs_enterprise_yyzzfb.").append(id).append("',").
				append("'cs_enterprise_zzjgdmz.").append(id).append("',").
				append("'cs_enterprise_xxtpy.").append(id).append("',").
				append("'cs_enterprise_xxtpe.").append(id).append("',").
				append("'cs_enterprise_xxtps.").append(id).append("',").
				append("'cs_enterprise_xxtpss.").append(id).append("'");
				//append(",");
		sql.append(")");
		System.out.println("查询企业"+sql.toString());
		List<FileForm> list = this.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(FileForm.class).list();
		return list;
	}
}
