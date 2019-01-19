package com.sungang.controller;

import com.sungang.model.ReturnMsg;
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
    public ReturnMsg getUserTestResult(String openId, String sex, int wordStep) {
        logger.info("openId:{},sex:{},wordStep:{}",openId,sex,wordStep);
        return userTestWordService.getResult(openId, wordStep, sex);
    }

    @RequestMapping(value = "/initPage", method = RequestMethod.GET)
    public List<String> getInitMsg() {
        logger.info("access server......");
        return initTagService.getInitTag();
    }

}
