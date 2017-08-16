package com.jubo.modules.sys.service.impl;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.DateUtils;
import com.jubo.common.utils.UUIDUtil;
import com.jubo.common.validator.Assert;
import com.jubo.modules.sys.dao.RechargeOrderDao;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.entity.RechargeOrderEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import com.jubo.modules.sys.service.GoodsService;
import com.jubo.modules.sys.service.RechargeOrderService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.jubo.common.utils.Constant.PRE_RECHARGE_ORDER;

@Transactional
@Service("rechargeOrderService")
public class RechargeOrderServiceImpl implements RechargeOrderService {

    private AtomicInteger orderNum = new AtomicInteger(0);

    @Autowired
    private RechargeOrderDao rechargeOrderDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public String buildIdRechargeOrder(
            Long userId, Long goodsId) {

        AccountInfoEntity account = accountInfoService.queryObjectByUserId(userId);
        Assert.isNull(account, "账户不存在");

        GoodsEntity goods = goodsService.queryObject(goodsId);
        Assert.isNull(goods, "商品不存在");

        RechargeOrderEntity order = new RechargeOrderEntity();

        //生成充值订单id
        String id = createOrderId();

        order.setId(id);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(goods.getMoney());
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());
        order.setAccountId(account.getId());
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


    /**
     * 生成充值订单id
     *
     * @return
     */
    private String createOrderId() {
        if (orderNum.addAndGet(1) == 100000) {
            orderNum.set(0);
        }
        String dateString = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String idString = ("000000" + orderNum).substring(("000000" + orderNum).length() - 6);

        return PRE_RECHARGE_ORDER + dateString + idString;
    }
}
