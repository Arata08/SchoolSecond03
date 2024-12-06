package com.ssm.service.impl;

import com.ssm.dao.BusinessDao;
import com.ssm.domain.Business;
import com.ssm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    BusinessDao businessDao;

    //商家查找--按照本人名字
    @Override
    public Business login(Business business) {
        return businessDao.selectBusinessByUsername(business.getUsername());
    }

    //商家注册
    @Override
    public void register(Business business) {
        businessDao.insertBusiness(business);
    }

    //商家查找---按照id
    @Override
    public Business selectBusinessById(Integer id){
        return businessDao.selectBusinessById(id);
    }

    //商家账号信息更新
    @Override
    public void updateBusiness(Business business){
        businessDao.updateBusiness(business);
    }

    //商家查找--按照学号
    @Override
    public Business selectBusinessByIdcard(Long idcard){
        return businessDao.selectBusinessByIdcard(idcard);
    }

    //商家查找--按照昵称
    @Override
    public Business selectBusinessByUserName(String username){
        return businessDao.selectBusinessByUserName(username);
    }
}
