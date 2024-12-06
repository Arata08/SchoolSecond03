package com.ssm.dao;

import com.ssm.domain.Admin;
import com.ssm.domain.Business;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminDao {                   //管理员接口，实现数据库操作

    //管理员查看所有商家
    @Select("select * from business")
    List<Business> selectAll();

    //login
    @Select("select * from admin where username=#{username}")                 //查
    Admin selectAdminByUsername(@Param("username") String username);

    //delete
    @Delete("delete from business where id=#{id}")                 //删
    boolean delete(@Param("id") Integer id);

    //update
    @Update("update business set state=#{state} where id=#{id}")         //改
    boolean update(@Param("id") Integer id, @Param("state") String state);
}
