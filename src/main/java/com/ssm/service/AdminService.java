package com.ssm.service;

import com.ssm.domain.Admin;
import com.ssm.domain.Business;

import java.util.List;

public interface AdminService {

    List<Business> selectAll();

    //login
    Admin selectAdminByUsername(String username);

    //delete
    boolean delete(Integer id);

    //update
    boolean update(Integer id, String state);
}
