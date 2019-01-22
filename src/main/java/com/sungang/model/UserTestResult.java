package com.sungang.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by SGang on 2019/1/14.
 */
public class UserTestResult implements Serializable {
    private int id;
    private int metaId;
    private String openid;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Override
    public String toString() {
        return "UserTestResult{" +
                "id=" + id +
                ", metaId='" + metaId + '\'' +
                ", openid='" + openid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetaId() {
        return metaId;
    }

    public void setMetaId(int metaId) {
        this.metaId = metaId;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
