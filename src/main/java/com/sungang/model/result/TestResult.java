package com.sungang.model.result;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/22.
 */
public class TestResult implements Serializable{
    //0代表成功,1代表失败
    private int status;
    private String describe;
    public TestResult(int status,String describe){
        this.status = status;
        this.describe = describe;
    }
    public static TestResult success(){
        return new TestResult(0,"测试成功");
    }
    public static TestResult fail(){
        return new TestResult(1,"测试失败");
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
