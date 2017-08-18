package com.jubo.common.utils;

import java.util.UUID;

public class UUIDUtil {

    public static void main(String[] args) {
        int a = 3242;
        String s = String.valueOf(a) + "000000";
        System.out.println(s.substring(0, 6));
    }


    public static String getUUId() {

        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }  
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);

    }

    public static String getUUID2(){
        return UUID.randomUUID().toString().replaceAll("-", "");

    }

}
