package com.hurong.credit.model.webFeedBack;

import java.util.Date;

public class FeedBack {
    protected Long id;//主键id
//    protected Long userid;//用户id
    protected String content;//反馈内容
    protected java.util.Date createTime;//创建时间
    protected String contact;//反馈内容

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
