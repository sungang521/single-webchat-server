package com.sungang.service.impl;

import com.sungang.dao.MetaDataDao;
import com.sungang.dao.UserTestWordDao;
import com.sungang.model.MetaData;
import com.sungang.model.ReturnMsg;
import com.sungang.model.UserTestResult;
import com.sungang.service.UserTestWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SGang on 2019/1/14.
 */
@Service
public class UserTestWordServiceImpl implements UserTestWordService {
    @Autowired
    private MetaDataDao metaDataDao;
    @Autowired
    private UserTestWordDao userTestWordDao;
    @Override
    public ReturnMsg getResult(String openId, int wordStep, String sex) {
        ReturnMsg msg = new ReturnMsg();
        MetaData metaData = metaDataDao.getDataByNameAndWordStep(wordStep,sex);
        String[] tag = metaData.getTag().split("；");
        List<String> tagList = Arrays.asList(tag);
        msg.setTagList(tagList);
        msg.setUserId(openId);
        //
        UserTestResult result = userTestWordDao.getResuleByUserId(openId);
        UserTestResult result1 = new UserTestResult();
        result1.setUserId(openId);
        result1.setId(metaData.getId());
        if(result==null){
            userTestWordDao.saveResult(result1);
        }else{
            userTestWordDao.updateResult(result1);
        }
        return msg;
    }
}
