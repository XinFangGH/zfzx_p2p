package com.hurong.credit.dao.materials.impl;

import java.util.List;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.materials.WebFinanceApplyUploadsDao;
import com.hurong.credit.model.materials.WebFinanceApplyUploads;

public class WebFinanceApplyUploadsDaoImpl extends BaseDaoImpl<WebFinanceApplyUploads> implements WebFinanceApplyUploadsDao {

	public WebFinanceApplyUploadsDaoImpl(){
		super(WebFinanceApplyUploads.class);
	}

	@Override
	public boolean update(WebFinanceApplyUploads webFinanceApplyUploads) {
		String sql = "update web_finance_apply_uploads wfa set wfa.files=? ,wfa.`status`=?,wfa.lastuploadtime=? where wfa.id=? and wfa.materialstype=? and wfa.userID=?";
		/*org.hibernate.Transaction tran=this.getSession().getTransaction();
		tran.begin();*/
		int row = this.getSession().createSQLQuery(sql)
						.setParameter(0, webFinanceApplyUploads.getFiles())
						.setParameter(1, webFinanceApplyUploads.getStatus())
						.setParameter(2, webFinanceApplyUploads.getLastuploadtime())
						.setParameter(3, webFinanceApplyUploads.getId())
						.setParameter(4, webFinanceApplyUploads.getMaterialstype())
						.setParameter(5, webFinanceApplyUploads.getUserID()).executeUpdate();
		//tran.commit();
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public WebFinanceApplyUploads getUserIdBystate(Long userId,String materialstype) {
		String hql = "from WebFinanceApplyUploads as w where w.userID="+userId+" and w.materialstype='"+materialstype+"'";
		List<WebFinanceApplyUploads> list = this.getSession().createQuery(hql).list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<WebFinanceApplyUploads> getUploadState(Long userId) {
		String hql = "from WebFinanceApplyUploads as w where w.userID="+userId;
		List<WebFinanceApplyUploads> list = this.getSession().createQuery(hql).list();
		return list;
	}
}
