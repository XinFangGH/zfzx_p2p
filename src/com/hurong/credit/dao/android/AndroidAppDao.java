package com.hurong.credit.dao.android;

import com.hurong.core.dao.BaseDao;
import com.hurong.credit.model.mobile.AppInfo;

public interface AndroidAppDao extends BaseDao<AppInfo>{

    void add(AppInfo appInfo);

    Integer select();

    AppInfo selectInfo();
}
