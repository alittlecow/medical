package com.jubo.modules.sys.service.impl;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.Constant;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author pengxiao
 * @date 2017/8/20
 */
@Transactional
@Service("orderCallBackService")
public class OrderCallBackServiceImpl implements OrderCallBackService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CardService cardService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public void doHandle(String orderId, Byte payType) {

        if (StringUtils.isBlank(orderId)) {
            return;
        }

        OrderEntity order = orderService.queryObject(orderId);
        if (order == null) {
            return;
        }

        Long merchantId = null;

        //id卡充值订单
        if (Constant.OrderType.ID_RECHARGE.getValue().compareTo(order.getOrderType()) == 0) {

            handleCardRecharge(order);
            merchantId = cardService.queryObjectByCode(order.getCardCode()).getMerchantId();
        }
        //设备使用订单
        if (Constant.OrderType.USE_DEVICE_ONLINE.getValue().compareTo(order.getOrderType()) == 0) {

            handleUseDevice(order);
            merchantId = deviceService.queryObject(order.getDeviceId()).getMerchantId();
        }

        //更改订单状态
        order.setPayStatus(Constant.PayStatus.SUCCESS.getValue());
        order.setPayTime(new Date());
        order.setPayType(payType);
        orderService.update(order);


        //分成
        accountInfoService.settlement(merchantId, order.getOrderMoney().doubleValue(), orderId);

    }


    //id卡充值订单处理
    private void handleCardRecharge(OrderEntity order) {
        if (order == null) return;

        GoodsEntity goods = goodsService.queryObject(order.getGoodsId());
        if (goods == null) {
            throw new RRException("充值套餐不存在");
        }
        if (Constant.GoodsType.DEVICE_USE_COUNT.getValue().compareTo(goods.getType()) != 0) {
            throw new RRException("充值类型错误");
        }

        int rechargeTimes = goods.getValue();
        cardService.changeCardTimes(order.getCardCode(), rechargeTimes);

    }

    //设备使用订单处理
    private void handleUseDevice(OrderEntity order) {
        if (order == null) return;
        //使用设备
        deviceService.useDevice(order.getDeviceId());

        System.out.println("请求mqtt,开始使用设备······");
    }
}
