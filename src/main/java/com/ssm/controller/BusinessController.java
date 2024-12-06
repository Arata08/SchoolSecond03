package com.ssm.controller;


import com.ssm.domain.Business;
import com.ssm.domain.User;
import com.ssm.service.BusinessService;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @PostMapping("/login")                //localhost：8080/business/login
    public Result login(HttpServletRequest request, @RequestBody Business business){
        //查询数据库
        Business business1 = businessService.login(business);
        System.out.println(business1);
        //如果没有查询到则返回登录失败结果
        if(business1 == null){
            return new Result(Code.LOGIN_ERR,null,"无该商家信息");
        }
        if(business1.getState().equals("未审核")){
            return new Result(Code.LOGIN_ERR,null,"该商家信息未审核");
        }
        //密码比对，如果不一致则返回登录失败结果
        if(!business1.getPassword().equals(business.getPassword())){
            return new Result(Code.PASSWORD_ERR,null,"密码错误");
        }
        //登录成功，将用户id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",business1.getId());
        return new Result(Code.LOGIN_OK,business1,"登录成功");
    }

    //商家注册
    @PostMapping("/register")
    public Result register(@RequestBody Business business){
        //查询数据库
        Business business1 = businessService.login(business);
        //如果查询到则返回注册失败结果
        if(business1 != null){
            return new Result(Code.REGISTER_ERR,null,"该学号不能重复注册");
        }
        Business business3 = businessService.selectBusinessByIdcard(business.getIdcard());
        if (business3 != null){
            return new Result(Code.REGISTER_ERR,null,"该身份证号已被注册");
        }
        businessService.register(business);
        return new Result(Code.REGISTER_OK,null,"已提交申请");
    }

    @PutMapping
    public Result update(@RequestBody Business business){
        boolean flag = false;
        Business business1 = businessService.selectBusinessById(business.getId());
        //是否修改了密码或者账号，修改了则需重新登录
        if((!business1.getPassword().equals(business.getPassword()))||(!business1.getUsername().equals(business.getUsername()))){
            flag = true;
        }
        //重名检测
        Business business2 = businessService.selectBusinessByUserName(business1.getUsername());
        if(business2 != null){
            if(business2.getId() != business.getId()){
                return new Result(Code.REGISTER_ERR,null,"该用户名已被占用");
            }
        }
        businessService.updateBusiness(business);
        if(flag){
            return new Result(Code.LOGININFO_CHANGE,null,"修改成功,请重新登录");
        }
        return new Result(Code.UPDATE_OK,null,"修改成功");
    }
}
