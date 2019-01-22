package com.sungang.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by SGang on 2019/1/22.
 */
public class ResultActionBean implements Serializable{
    private int id;
    private String shareOpenid;
    private String openid;
    private Timestamp createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShareOpenid() {
        return shareOpenid;
    }

    public void setShareOpenid(String shareOpenid) {
        this.shareOpenid = shareOpenid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
