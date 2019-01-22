package com.sungang.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SGang on 2019/1/14.
 */
public class ReturnMsg1 implements Serializable{
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
