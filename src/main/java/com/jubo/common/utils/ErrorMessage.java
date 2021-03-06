package com.jubo.common.utils;

/**
 * @author pengxiao
 * @date 2017/7/23
 */
public class ErrorMessage {


    //user
    public static final String PHONE_FORMAT_ERROR = "电话号码格式错误";
    public static final String EMAIL_FORMAT_ERROR = "邮箱格式错误";

    public static final String PHONE_IS_EXIST = "电话号码已注册";
    public static final String USERNAME_IS_EXIST = "用户名已注册";
    public static final String PASSWORD_FORMAT_ERROR = "请输入6-20位字母数字组合密码";

    //order
    public static final String ORDER_NOT_BLANK = "订单能为空";
    public static final String ORDER_NOT_EXIST = "订单不存在";
    public static final String ORDER_NOT_YOURS = "不能支付非本人订单";
    public static final String ORDER_ALREADY_PAY = "订单已支付";


    //acount
    public static final String MONEY_NOT_ENOUGH = "余额不足";


    //权限
    public static final String USER_NO_PERMISSION = "用户没有权限查看该记录";

    //提现

    public static final String BC_ENCHASHMENT_FAILED = "企业打款失败";

}
