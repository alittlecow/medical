package com.jubo.modules.sys.service;

import com.jubo.common.utils.R;
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
     * 用户门店消费订单
     *
     * @param userId
     * @return
     */
    String buildConsumeOrder(Long userId, String deviceId);

    /**
     * @param userId
     * @param cardCode
     * @return
     */
    String buildCardRechargeOrder(Long userId, String cardCode, Long goodsId);

    /**
     * 刷卡消费订单（分成修改为充值100%分成，现在不在订单表中生成订单）
     */
    R buildCardOrder(Map<String, String> params);

    OrderEntity queryObject(String id);

    List<OrderEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(OrderEntity order);

    void update(OrderEntity order);

    void delete(String id);

    void deleteBatch(String[] ids);
}
