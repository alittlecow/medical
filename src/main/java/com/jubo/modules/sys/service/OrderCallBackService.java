package com.jubo.modules.sys.service;

/**
 * @author pengxiao
 * @date 2017/8/20
 * 订单回调
 */
public interface OrderCallBackService {

    //处理订单
    void doHandle(String order, Byte payType);

}
