package com.ssm.dao;

import com.ssm.domain.Business;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BusinessDao {              //商家接口，实现数据库操作

    //以商家名字查
    @Select("select * from business where username=#{username}")
    Business selectBusinessByUsername(@Param("username") String username);

    //商家注册
    @Select("insert into business(username,password,name,idcard) values(#{username},#{password},#{name},#{idcard})")
    void insertBusiness(Business business);

    //按id查询
    @Select("select * from business where id=#{id}")
    Business selectBusinessById(@Param("id") Integer id);

    //更新
    @Select("update business set username=#{username},password=#{password},introduction=#{introduction},qq=#{qq},address=#{address},phone=#{phone},state=#{state} where id=#{id}")
    void updateBusiness(Business business);

    //根据idcard查询
    @Select("select * from business where idcard=#{idcard}")
    Business selectBusinessByIdcard(@Param("idcard") Long idcard);

    //根据商家账号名字查询
    @Select("select * from business where username=#{username}")
    Business selectBusinessByUserName(@Param("username") String username);

}
