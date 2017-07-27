package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.modules.sys.dao.RechargeOrderDao;
import com.jubo.modules.sys.entity.RechargeOrderEntity;
import com.jubo.modules.sys.service.RechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("rechargeOrderService")
public class RechargeOrderServiceImpl implements RechargeOrderService {
    @Autowired
    private RechargeOrderDao rechargeOrderDao;


    @Transactional
    @Override
    public String buildIdRechargeOrder(Byte rechargeOrderType, String objectId,
                                       Long userId, String goodsId, BigDecimal orderMoney) {
        RechargeOrderEntity order = new RechargeOrderEntity();
        String id = UUIDUtil.getUUId();
        order.setId(id);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());
        order.setObjectId(objectId);
        order.setOrderType(rechargeOrderType);
        rechargeOrderDao.save(order);

        return id;
    }

    @Override
    public RechargeOrderEntity queryObject(String id) {
        return rechargeOrderDao.queryObject(id);
    }

    @Override
    public List<RechargeOrderEntity> queryList(Map<String, Object> map) {
        return rechargeOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return rechargeOrderDao.queryTotal(map);
    }

    @Override
    public void save(RechargeOrderEntity order) {
        rechargeOrderDao.save(order);
    }

    @Override
    public void update(RechargeOrderEntity order) {
        rechargeOrderDao.update(order);
    }

    @Override
    public void delete(String id) {
        rechargeOrderDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        rechargeOrderDao.deleteBatch(ids);
    }

}
