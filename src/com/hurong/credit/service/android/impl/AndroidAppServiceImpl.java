package com.hurong.credit.service.android.impl;

import com.hurong.core.service.impl.BaseServiceImpl;
import com.hurong.credit.dao.android.AndroidAppDao;
import com.hurong.credit.model.mobile.AppInfo;
import com.hurong.credit.service.android.AndroidAppService;

public class AndroidAppServiceImpl extends BaseServiceImpl<AppInfo> implements AndroidAppService{

    private AndroidAppDao dao;



    public AndroidAppServiceImpl(AndroidAppDao dao) {
        super(dao);
        this.dao=dao;
    }

    @Override
    public void add(AppInfo appInfo) {
        dao.add(appInfo);
    }

    @Override
    public Integer select() {
        return dao.select();
    }

    @Override
    public AppInfo selectInfo() {
        return dao.selectInfo();
    }
}
