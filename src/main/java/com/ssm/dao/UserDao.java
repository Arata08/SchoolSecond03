package com.ssm.dao;

import com.ssm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {

    //根据用户名字查找用户
    @Select("select * from user where username=#{username}")
    User selectUserByUsername(@Param("username") String username);

    //用户注册
    @Insert("insert into user(username,password,name) values(#{username},#{password},#{name})")
    void insertUser(User user);

    //根据用户id查找用户
    @Select("select * from user where id=#{id}")
    User selectUserById(@Param("id") Integer id);

    //更新用户信息
    @Update("update user set username=#{username},password=#{password},introduction=#{introduction},qq=#{qq},address=#{address},phone=#{phone},name=#{name} where id=#{id}")
    void updateUser(User user);
}
