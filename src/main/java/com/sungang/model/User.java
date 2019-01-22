package com.sungang.model;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/22.
 */
public class User implements Serializable {
    private String openid;
    private String sex;
    private String headUrl;
    private String nickName;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", sex='" + sex + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
