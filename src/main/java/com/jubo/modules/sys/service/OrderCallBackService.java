package com.jubo.modules.sys.service;

/**
 * @author pengxiao
 * @date 2017/8/20
 * 订单回调
 */
public interface OrderCallBackService {

    //分成订单处理 : id卡充值  扫码支付
    void handleSettlementOrder(String orderId, Byte payType);


    //账户充值订单

    void handleRechargeOrder(String orderId, Byte payType);


}
