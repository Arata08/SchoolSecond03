package com.ssm.service;


import com.ssm.domain.Business;

public interface BusinessService {
    Business login(Business business);

    void register(Business business);

    Business selectBusinessById(Integer id);

    void updateBusiness(Business business);

    Business selectBusinessByIdcard(Long idcard);

    Business selectBusinessByUserName(String username);
}
