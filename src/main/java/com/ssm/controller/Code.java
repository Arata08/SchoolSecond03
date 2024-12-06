package com.ssm.controller;

public class Code {

    public static final Integer SAVE_OK = 20011;   //保存
    public static final Integer DELETE_OK = 20021;   //可以删除
    public static final Integer UPDATE_OK = 20031;    //可以修改
    public static final Integer GET_OK = 20041;      //

    public static final Integer SAVE_ERR = 20010;        //保存失败
    public static final Integer DELETE_ERR = 20020;     //删除失败
    public static final Integer UPDATE_ERR = 20030;       //修改错误
    public static final Integer GET_ERR = 20040;        //
    public static final Integer RENAME = 114514;

    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUTERR = 50002;           //超市
    public static final Integer SYSTEM_UNKONW_ERR = 50002;

    public static final Integer BUSINESS_ERR = 60002;

    public static final Integer LOGIN_ERR = 60001;
    public static final Integer LOGIN_OK = 60002;
    public static final Integer PASSWORD_ERR = 60003;
    public static final Integer LOGININFO_CHANGE = 60004;

    public static final Integer UNLOGIN = 70001;


    public static final Integer REGISTER_ERR = 80001;
    public static final Integer REGISTER_OK = 80002;

    public static final Integer BUY_OK = 80003;
    public static final Integer BUY_ERR = 80004;

    public static final Integer DELIVERY_OK = 80005;
    public static final Integer DELIVERY_ERR = 80006;

    public static final Integer CAR_OK = 80007;
    public static final Integer CAR_ERR = 80008;

    public static final Integer ORDERBACK_OK = 90005;
    public static final Integer ORDERBACK_ERR = 90006;

    public static final Integer SELECT_OK = 10000;
}
