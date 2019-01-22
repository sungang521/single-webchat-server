package com.sungang.service.impl;

import com.sungang.dao.MetaDataDao;
import com.sungang.dao.UserDao;
import com.sungang.dao.UserTestWordDao;
import com.sungang.model.MetaData;
import com.sungang.model.User;
import com.sungang.model.UserTestResult;
import com.sungang.model.result.UserAllMsgResult;
import com.sungang.service.UserTestWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private UserDao userDao;

    @Override
    public void saveResult(String openId, int wordStep) throws Exception {
        UserTestResult result = userTestWordDao.getResuleByUserId(openId);
        User user = userDao.queryUserByOpenid(openId);
        MetaData metaData = metaDataDao.getDataByNameAndWordStep(wordStep,user.getSex());
        UserTestResult result1 = new UserTestResult();
        result1.setMetaId(metaData.getId());
        result1.setOpenid(openId);
        if (result == null) {
            result1.setCreateTime(new Timestamp(new Date().getTime()));
            result1.setUpdateTime(new Timestamp(new Date().getTime()));
            userTestWordDao.saveResult(result1);
        } else {
            result1.setUpdateTime(new Timestamp(new Date().getTime()));
            userTestWordDao.updateResult(result1);
        }
    }

    @Override
    public UserAllMsgResult getResultByOpenid(String openid) {
        UserAllMsgResult userResult = new UserAllMsgResult();
        UserTestResult result = userTestWordDao.getResuleByUserId(openid);
        System.out.println(result);
        //获取测字结果
        MetaData metaData = metaDataDao.getDataByopenId(result.getMetaId());
        //获取数据原信息
        User user = userDao.queryUserByOpenid(openid);
        String[] tag = metaData.getTag().split("；");
        List<String> tagList = Arrays.asList(tag);
        userResult.setTagList(tagList);
        userResult.setOpenid(openid);
        userResult.setHeadUrl(user.getHeadUrl());
        userResult.setSex(user.getSex());
        userResult.setNickName(user.getNickName());
        return userResult;
    }
//    @Override
//    public void getResult(String openId, int wordStep) throws Exception{
//        ReturnMsg msg = new ReturnMsg();
//        MetaData metaData = metaDataDao.getDataByNameAndWordStep(wordStep,sex);
//        String[] tag = metaData.getTag().split("；");
//        List<String> tagList = Arrays.asList(tag);
//        msg.setTagList(tagList);
//        msg.setUserId(openId);
//        //
//        UserTestResult result = userTestWordDao.getResuleByUserId(openId);
//        UserTestResult result1 = new UserTestResult();
//        result1.setUserId(openId);
//        result1.setId(metaData.getId());
//        if(result==null){
//            userTestWordDao.saveResult(result1);
//        }else{
//            userTestWordDao.updateResult(result1);
//        }
//        return msg;
//    }
}
