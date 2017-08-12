package com.jubo.common.validator;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.ParamVerifyUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    public static void isNotPhone(String phone, String message) {
        if (!ParamVerifyUtils.isPhoneNo(phone)) {
            throw new RRException(message);
        }
    }

    public static void isNotEmail(String email, String message){
        if (!ParamVerifyUtils.isEmail(email)) {
            throw new RRException(message);
        }
    }

    public static void isNotValidPassword(String password, String message) {
        if (!ParamVerifyUtils.isValidPassWord(password)) {
            throw new RRException(message);
        }
    }
}
