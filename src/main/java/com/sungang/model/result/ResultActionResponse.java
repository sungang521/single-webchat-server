package com.sungang.model.result;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/22.
 */
public class ResultActionResponse implements Serializable{
    //0代表成功,1代表失败
    private int status;
    private String describe;
    public ResultActionResponse(int status, String describe){
        this.status = status;
        this.describe = describe;
    }
    public static ResultActionResponse success(){
        return new ResultActionResponse(0,"保存成功");
    }
    public static ResultActionResponse fail(){
        return new ResultActionResponse(1,"保存失败");
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
