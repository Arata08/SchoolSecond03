package com.ssm.controller;

import com.ssm.domain.Goods;
import com.ssm.domain.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //用户登录
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody User user){
        //查询数据库
        User userDB = userService.login(user);
        //如果没有查询到则返回登录失败结果
        if(userDB == null){
            return new Result(Code.LOGIN_ERR,null,"无该用户信息");
        }
        //密码比对，如果不一致则返回登录失败结果
        if(!userDB.getPassword().equals(user.getPassword())){
            return new Result(Code.PASSWORD_ERR,null,"密码错误");
        }
        //登录成功，将用户id存入Session并返回登录成功结果
        request.getSession().setAttribute("user",userDB.getId());
        return new Result(Code.LOGIN_OK,userDB,"登录成功");
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        //查询数据库
        User userDB = userService.login(user);
        //如果查询到则返回注册失败结果
        if(userDB != null){
            return new Result(Code.REGISTER_ERR,null,"该用户已存在");
        }
        //注册成功，将用户信息存入数据库并返回注册成功结果
        userService.register(user);
        return new Result(Code.REGISTER_OK,null,"注册成功");
    }

    //更新用户信息
    @PutMapping
    public Result update(@RequestBody User user){
        boolean flag = false;
        User user1 = userService.selectUserById(user.getId());
        if((!user1.getPassword().equals(user.getPassword()))||(!user1.getUsername().equals(user.getUsername()))){
            flag = true;
        }
        //重名检测
        User user2 = userService.selectUserByUsername(user.getUsername());
        if(user2 != null){
            if(user2.getId() != user.getId()){
                return new Result(Code.REGISTER_ERR,null,"该学号已经注册");
            }
        }
        userService.updateUser(user);
        if(flag){
            return new Result(Code.LOGININFO_CHANGE,null,"修改成功,请重新登录");
        }
        return new Result(Code.UPDATE_OK,null,"成功");
    }

}
