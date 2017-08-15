package com.jubo.common.utils;

import com.jubo.common.exception.RRException;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换为日期
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateString) {
        Date d = null;
        try {
            org.apache.commons.lang.time.DateUtils.parseDate(dateString,
                    new String[]{DATE_PATTERN, DATE_TIME_PATTERN});
        } catch (ParseException e) {
            throw new RRException("日期格式错误");
        }

        return d;
    }
}
