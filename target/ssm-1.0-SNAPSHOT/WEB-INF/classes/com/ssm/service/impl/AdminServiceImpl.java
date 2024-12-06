package com.ssm.service.impl;

import com.ssm.dao.AdminDao;
import com.ssm.domain.Admin;
import com.ssm.domain.Business;
import com.ssm.service.AdminService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    //根据管理员账号查找管理员
    @Override
    public Admin selectAdminByUsername(String username) {
        return adminDao.selectAdminByUsername(username);
    }

    //管理员查看所有商家
    @Override
    public List<Business> selectAll() {
        return adminDao.selectAll();
    }

    //管理员根据id删除商家
    @Override
    public boolean delete(Integer id) {
        return adminDao.delete(id);
    }

    //管理员更新商家信息
    @Override
    public boolean update(Integer id, String state) {
        return adminDao.update(id, state);
    }
}
