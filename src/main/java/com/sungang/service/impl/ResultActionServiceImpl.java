package com.sungang.service.impl;

import com.sungang.dao.ResultActionDao;
import com.sungang.model.ResultActionBean;
import com.sungang.service.ResultActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
@Service
public class ResultActionServiceImpl implements ResultActionService {
    @Autowired
    private ResultActionDao resultActionDao;
    @Override
    public void saveResultAction(ResultActionBean bean) {
        resultActionDao.saveResultAction(bean);
    }

    @Override
    public List<ResultActionBean> getResultActionByOpenId(String openid) {
        return resultActionDao.getResultActionByOpenId(openid);
    }
}
