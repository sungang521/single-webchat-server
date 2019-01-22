package com.sungang.service;

import com.sungang.model.UserTestResult;
import com.sungang.model.result.UserAllMsgResult;

/**
 * Created by SGang on 2019/1/14.
 */
public interface UserTestWordService {
    void saveResult(String openId, int wordStep) throws Exception;
    UserAllMsgResult getResultByOpenid(String openid);
}
