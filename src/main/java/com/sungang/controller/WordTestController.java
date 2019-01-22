package com.sungang.controller;


import com.sungang.model.result.TestResponse;
import com.sungang.model.result.UserAllMsgResult;
import com.sungang.service.UserTestWordService;
import com.sungang.service.impl.InitTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by SGang on 2019/1/14.
 */
@RestController
public class WordTestController extends BaseController {
    @Autowired
    private UserTestWordService userTestWordService;
    @Autowired
    private InitTagService initTagService;

    @RequestMapping(value = "/testWord", method = RequestMethod.GET)
    public TestResponse getUserTestResult(String openId, int wordStep) {
        logger.info("openId:{},wordStep:{}",openId,wordStep);
        try {
            userTestWordService.saveResult(openId, wordStep);
            return TestResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TestResponse.fail();
    }

    @RequestMapping(value = "/initPage", method = RequestMethod.GET)
    public List<String> getInitMsg() {
        logger.info("access server......");
        return initTagService.getInitTag();
    }
    @RequestMapping(value = "/getTestResultByOpenid", method = RequestMethod.GET)
    public UserAllMsgResult getUserAllMsgResult(String openid){
        return userTestWordService.getResultByOpenid(openid);
    }

}
