package com.sungang.dao;

import com.sungang.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by SGang on 2019/1/22.
 */
public interface UserDao {
    void saveUser(User user);
    User queryUserByOpenid(@Param("openid") String openid);
}
