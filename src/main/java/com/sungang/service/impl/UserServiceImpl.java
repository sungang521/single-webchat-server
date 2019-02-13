package com.sungang.service.impl;


import com.sungang.dao.UserDao;
import com.sungang.model.User;
import com.sungang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SGang on 2019/1/22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUser(User user) {
        User u = userDao.queryUserByOpenid(user.getOpenid());
        if (u != null) {
            userDao.deleteUser(user.getOpenid());
        }
        userDao.saveUser(user);
    }

    @Override
    public User queryUserByOpenid(String openid) {
        return userDao.queryUserByOpenid(openid);
    }
}
