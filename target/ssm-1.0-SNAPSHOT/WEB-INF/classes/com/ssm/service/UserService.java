package com.ssm.service;

import com.ssm.domain.User;

public interface UserService {

    User login(User user);

    void register(User user);

    User selectUserById(Integer id);

    void updateUser(User user);

    User selectUserByUsername(String username);
}
