package com.sungang.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
public class UserAllMsgResult implements Serializable {
    private String openid;
    private String sex;
    private String headUrl;
    private String nickName;
    private List<String> tagList;

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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "UserAllMsgResult{" +
                "openid='" + openid + '\'' +
                ", sex='" + sex + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", tagList=" + tagList +
                '}';
    }
}
