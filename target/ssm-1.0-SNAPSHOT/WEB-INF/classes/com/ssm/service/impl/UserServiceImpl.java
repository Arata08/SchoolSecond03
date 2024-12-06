package com.ssm.service.impl;

import com.ssm.dao.UserDao;
import com.ssm.domain.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    //查找是否存在这样一个用户，并返回他
    @Override
    public User login(User user) {
        User user1 = userDao.selectUserByUsername(user.getUsername());
        return user1;
    }

    //用户注册
    @Override
    public void register(User user) {
        userDao.insertUser(user);
    }

    //根据用户id查找用户
    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    //更新用户信息
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    //根据用户名字查找用户
    @Override
    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }
}
