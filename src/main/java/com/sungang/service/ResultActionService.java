package com.sungang.service;

import com.sungang.model.ResultActionBean;
import com.sungang.model.ResultActionResponseBean;

import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
public interface ResultActionService {
    void saveResultAction(ResultActionBean bean);
    List<ResultActionResponseBean> getResultActionByOpenId(String openid);
}
