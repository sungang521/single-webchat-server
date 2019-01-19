package com.sungang.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SGang on 2019/1/14.
 */
public class ReturnMsg implements Serializable{
    private String userId;
    private List<String> tagList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
