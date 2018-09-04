package com.hurong.credit.model.mobile;

import com.hurong.core.model.BaseModel;

public class AppInfo extends BaseModel{

    private Integer id;
    private String versionCode;
    private String versionName;
    private String versionContent;
    private Boolean mustInstall;
    private String downUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionContent() {
        return versionContent;
    }

    public void setVersionContent(String versionContent) {
        this.versionContent = versionContent;
    }

    public Boolean getMustInstall() {
        return mustInstall;
    }

    public void setMustInstall(Boolean mustInstall) {
        this.mustInstall = mustInstall;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

}
