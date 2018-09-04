package com.hurong.credit.service.android;

import com.hurong.core.service.BaseService;
import com.hurong.credit.model.mobile.AppInfo;

public interface AndroidAppService extends BaseService<AppInfo>{


    void add(AppInfo appInfo);

    Integer select();

    AppInfo selectInfo();
}
