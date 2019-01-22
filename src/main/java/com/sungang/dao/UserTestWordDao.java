package com.sungang.dao;

import com.sungang.model.UserTestResult;
import org.apache.ibatis.annotations.Param;

/**
 * Created by SGang on 2019/1/14.
 */
public interface UserTestWordDao {
    /*
    查询是否存在测试结果
     */
    UserTestResult getResuleByUserId(@Param("openid") String openid);
    /*
    插入数据
     */
    void saveResult(UserTestResult result);
    /*
    更新数据
     */
    void updateResult(UserTestResult result);
}
