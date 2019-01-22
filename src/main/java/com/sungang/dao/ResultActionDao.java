package com.sungang.dao;

import com.sungang.model.ResultActionBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by SGang on 2019/1/22.
 */
public interface ResultActionDao {
    void saveResultAction(ResultActionBean bean);
    List<ResultActionBean> getResultActionByOpenId(@Param("openid") String openid);
}
