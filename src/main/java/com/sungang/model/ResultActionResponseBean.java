package com.sungang.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by SGang on 2019/1/22.
 */
public class ResultActionResponseBean extends ResultActionBean implements Serializable{
    private String headurl;
    private String nickName;
    private String formatTime;

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
