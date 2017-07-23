package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface OrderService {

    /**
     * 构建初始化订单
     */
    String buildOrder(Long userId, String goodsId, BigDecimal orderMoney);

    OrderEntity queryObject(String id);

    List<OrderEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderEntity order);

    void update(OrderEntity order);

    void delete(String id);

    void deleteBatch(String[] ids);
}
