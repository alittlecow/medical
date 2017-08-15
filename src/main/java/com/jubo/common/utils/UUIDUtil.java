package com.jubo.common.utils;

import java.util.UUID;

public class UUIDUtil {

    public static void main(String[] args) {
        int a = 3242;
        String s = String.valueOf(a) + "000000";
        System.out.println(s.substring(0, 6));
    }


    public static String getUUId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
