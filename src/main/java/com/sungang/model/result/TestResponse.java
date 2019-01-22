package com.sungang.model.result;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/22.
 */
public class TestResponse implements Serializable{
    //0代表成功,1代表失败
    private int status;
    private String describe;
    public TestResponse(int status,String describe){
        this.status = status;
        this.describe = describe;
    }
    public static TestResponse success(){
        return new TestResponse(0,"测试成功");
    }
    public static TestResponse fail(){
        return new TestResponse(1,"测试失败");
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
