package com.sungang.model;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/14.
 */
public class UserTestResult implements Serializable{
    private String userId;
    private int id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
