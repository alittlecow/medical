package com.jubo.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zhanglj on 2017/7/9.
 */
public class ParamVerifyUtils {
    public static final String PHONE_REG = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

    public static final String PASSWORD_REG = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

    public static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 手机号码校验
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneNo(String phone) {
        if (StringUtils.isBlank(phone))
            return false;
        Pattern p = Pattern.compile(PHONE_REG);
        Matcher matcher = p.matcher(phone);
        return matcher.matches();
    }

    /**
     * 密码校验 6位到20位 有数字和字母
     *
     * @param password
     * @return
     */
    public static boolean isValidPassWord(String password) {
        if (StringUtils.isBlank(password))
            return false;
        Pattern p = Pattern.compile(PASSWORD_REG);
        Matcher matcher = p.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email))
            return false;
        Pattern p = Pattern.compile(EMAIL_REG);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    /**
     * 所有字符串均不为空时返回true
     * @param str
     * @return
     */
    public static boolean checkAllValues(String... str){
        for(String s : str){
            if (StringUtils.isBlank(s)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isValidPassWord("12345"));
        System.out.println(isValidPassWord("p12345"));
        System.out.println(isValidPassWord("1fsdf2345"));
        System.out.println(isValidPassWord("1234567"));
    }

}
