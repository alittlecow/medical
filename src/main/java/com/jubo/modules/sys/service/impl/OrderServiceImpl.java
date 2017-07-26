package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.OrderDao;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.service.OrderService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Transactional
    @Override
    public String buildOrder(Long userId, String goodsId, BigDecimal orderMoney) {
        OrderEntity order = new OrderEntity();
        String id = UUIDUtil.getUUId();
        order.setId(id);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());

        orderDao.save(order);

        return id;
    }

    @Transactional
    @Override
    public String buildIdRechargeOrder(String cardCode, Long userId, String goodsId, BigDecimal orderMoney) {
        OrderEntity order = new OrderEntity();
        String id = UUIDUtil.getUUId();
        order.setId(id);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());

        orderDao.save(order);

        return id;
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
