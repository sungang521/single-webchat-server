package com.sungang.model.result;

import java.io.Serializable;

/**
 * Created by SGang on 2019/1/21.
 */
public class HttpResult implements Serializable {
    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }

    // 响应的状态码
    private int code;
    // 响应的响应体
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
