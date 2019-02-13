package com.sungang.service.impl;

import com.sungang.dao.ResultActionDao;
import com.sungang.dao.UserDao;
import com.sungang.model.ResultActionBean;
import com.sungang.model.ResultActionResponseBean;
import com.sungang.model.User;
import com.sungang.service.ResultActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
@Service
public class ResultActionServiceImpl implements ResultActionService {
    @Autowired
    private ResultActionDao resultActionDao;
    @Autowired
    private UserDao userDao;
    @Override
    public void saveResultAction(ResultActionBean bean) {
        resultActionDao.saveResultAction(bean);
    }

    @Override
    public List<ResultActionResponseBean> getResultActionByOpenId(String openid) {
        List<ResultActionResponseBean> list = new ArrayList<>();
        List<ResultActionBean> re = resultActionDao.getResultActionByOpenId(openid);
        for(ResultActionBean bean : re){
            User user = userDao.queryUserByOpenid(bean.getOpenid());
            ResultActionResponseBean rar = new ResultActionResponseBean();
            rar.setId(bean.getId());
            rar.setCreateTime(bean.getCreateTime());
            rar.setShareOpenid(bean.getShareOpenid());
            rar.setOpenid(bean.getOpenid());
            rar.setHeadurl(user.getHeadUrl());
            rar.setNickName(user.getNickName());
            list.add(rar);
        }
        return list;
    }
}
