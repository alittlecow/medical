package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.service.CardService;
import com.jubo.modules.sys.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.OrderDao;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.service.OrderService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CardService cardService;

    @Transactional
    @Override
    public String buildConsumeOrder(Long userId, String deviceId) {
        //获取id为0的商品
        GoodsEntity goods = goodsService.queryObject("0");

        return buildConsumerOrder(userId, deviceId, "0", goods.getMoney());
    }

    //扫码支付订单
    public String buildConsumerOrder(Long userId, String deviceId, String goodsId, BigDecimal orderMoney) {
        OrderEntity order = new OrderEntity();
        String id = UUIDUtil.getUUId();
        order.setId(id);
        order.setUserId(userId);
        order.setDeviceId(deviceId);
        order.setOrderType(Constant.OrderType.USER_DEVICE.getValue());
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());

        orderDao.save(order);

        return id;
    }


    @Transactional
    @Override
    public void buildCardOrder(String code, String deviceId) {

        //使用次数1
        cardService.useCard(code, 1);

        GoodsEntity goods = goodsService.queryObject("0");
        BigDecimal orderMoney = goods.getMoney();

        OrderEntity order = new OrderEntity();
        String id = UUIDUtil.getUUId();
        order.setId(id);
        order.setDeviceId(deviceId);
        order.setOrderType(Constant.OrderType.USER_DEVICE.getValue());
        order.setCardCode(code);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.SUCCESS.getValue());
        order.setPayType(Constant.PayType.CARD.getValue());
        order.setCreateTime(new Date());
        order.setPayTime(new Date());
        order.setPayType(Constant.PayType.CARD.getValue());

        //保存为支付成功订单
        save(order);
    }

    @Override
    public OrderEntity queryObject(String id) {
        return orderDao.queryObject(id);
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public void save(OrderEntity order) {
        orderDao.save(order);
    }

    @Override
    public void update(OrderEntity order) {
        orderDao.update(order);
    }

    @Override
    public void delete(String id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        orderDao.deleteBatch(ids);
    }

}
