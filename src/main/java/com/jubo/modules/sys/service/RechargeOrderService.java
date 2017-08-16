package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.RechargeOrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface RechargeOrderService {

    /**
     * 构建充值订单
     */

    String buildIdRechargeOrder(Long userId, Long goodsId);

    RechargeOrderEntity queryObject(String id);

    List<RechargeOrderEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(RechargeOrderEntity order);

    void update(RechargeOrderEntity order);

    void delete(String id);

    void deleteBatch(String[] ids);
}
