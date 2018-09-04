package com.hurong.credit.dao.android.impl;

import com.hurong.core.dao.impl.BaseDaoImpl;
import com.hurong.credit.dao.android.AndroidAppDao;
import com.hurong.credit.model.mobile.AppInfo;
import com.hurong.credit.model.user.BpCustMember;
import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;

import java.math.BigInteger;
import java.util.List;

public class AndroidAppDaoImpl extends BaseDaoImpl<AppInfo> implements AndroidAppDao{

    public AndroidAppDaoImpl() {
        super(AppInfo.class);
    }

    @Override
    public void add(AppInfo appInfo) {
        String sql = "INSERT INTO bp_app_info(versionCode, versionName, versionContent, mustInstall, downUrl) VALUES ('"+ appInfo.getVersionCode()+"','"+ appInfo.getVersionName()+"','"+ appInfo.getVersionContent()+"',"+ appInfo.getMustInstall()+",'"+ appInfo.getDownUrl()+"')";
        System.out.println(sql+"");
        this.getSession().createSQLQuery(sql).executeUpdate();
    }

    @Override
    public Integer select() {
        String sql = "SELECT COUNT(*) from bp_app_info;";
        System.out.println(sql+"");
        BigInteger integer = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
        return integer.intValue();
    }

    @Override
    public AppInfo selectInfo() {
        String sql = "SELECT versionCode,versionName,versionContent,mustInstall, downUrl FROM bp_app_info ORDER BY id DESC";
        AppInfo appInfo = new AppInfo();
        appInfo.setVersionCode("0");
        List<AppInfo> list = this.getSession().createSQLQuery(sql).
                addScalar("versionCode", Hibernate.STRING).
                addScalar("versionName", Hibernate.STRING).
                addScalar("versionContent", Hibernate.STRING).
                addScalar("mustInstall", Hibernate.BOOLEAN).
                addScalar("downUrl",Hibernate.STRING).setResultTransformer(Transformers.aliasToBean(AppInfo.class))
                .list();
        if(list.size()>0){
            return list.get(0);
        }
        return appInfo;
    }

//    @Override
//    public AppInfo selectInfo() {
//        return null;
//    }
}
