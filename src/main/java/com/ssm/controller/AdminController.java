package com.ssm.controller;

import com.ssm.domain.*;
import com.ssm.service.AdminService;
import com.ssm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public Result selectAll(){
        return new Result(Code.SELECT_OK,adminService.selectAll());
    }

    //HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，HTTP请求头中的所有信息都封装在这个对象中，通过这个对象提供的方法，可以获得客户端请求的所有信息。
    //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody Admin admin){
        Admin adminDB = adminService.selectAdminByUsername(admin.getUsername());
        //如果没有查询到则返回登录失败结果
        if(adminDB == null){
            return new Result(Code.LOGIN_ERR,null,"无该用户信息");
        }
        //密码比对，如果不一致则返回登录失败结果
        if(!adminDB.getPassword().equals(admin.getPassword())){
            return new Result(Code.PASSWORD_ERR,null,"密码错误");
        }
        //登录成功，将管理员id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",adminDB.getId());
        return new Result(Code.LOGIN_OK,adminDB,"登录成功");
    }

    //删除商家
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id){
        boolean flag = adminService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    //修改商家信息
    @PostMapping("/check")
    public Result update(@RequestBody Info info){
        Business business = businessService.selectBusinessById(info.getId());
        if(business.getState().equals("未审核")){         //如果显示商家未审核，则批准入驻
            business.setState("已入驻");
        } else {                                              //反之，则返回错误信息，并且返回msg：商家已入驻
            return new Result(Code.UPDATE_ERR,false,"商家已入驻");
        }
        //通过id来更改商家的状态
        boolean flag = adminService.update(business.getId(),business.getState());
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }
}
