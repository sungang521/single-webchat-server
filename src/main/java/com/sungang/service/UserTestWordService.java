package com.sungang.service;

import com.sungang.model.ReturnMsg;

/**
 * Created by SGang on 2019/1/14.
 */
public interface UserTestWordService {
    ReturnMsg getResult(String openId, int wordStep, String sex);
}
