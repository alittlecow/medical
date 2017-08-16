package com.jubo.modules.sys.service.impl;

import com.jubo.common.exception.RRException;
import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.sys.entity.CardEntity;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.service.CardService;
import com.jubo.modules.sys.service.GoodsService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.jubo.modules.sys.dao.OrderDao;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import static com.jubo.common.utils.Constant.PRE_RECHARGE_ORDER;

@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CardService cardService;

    private AtomicInteger orderNum = new AtomicInteger(0);

    @Override
    public String buildConsumeOrder(Long userId, String deviceId) {
        //获取id为0的商品
        GoodsEntity goods = goodsService.queryObject(new Long("0"));

        return buildConsumerOrder(userId, deviceId, new Long("0"), goods.getMoney());
    }

    //消费订单号
    public String buildConsumerOrder(Long userId, String deviceId, Long goodsId, BigDecimal orderMoney) {
        OrderEntity order = new OrderEntity();
        //消费订单号
        String id = createOrderId();
        order.setId(id);
        order.setUserId(userId);
        order.setDeviceId(deviceId);
        order.setOrderType(Constant.OrderType.USER_DEVICE_ONLINE.getValue());
        order.setCreateTime(new Date());
        order.setGoodsId(goodsId);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());

        orderDao.save(order);

        return id;
    }


    @Override
    public R buildCardOrder(Map<String, String> params) {

        String deviceId = MapUtils.getString(params, "deviceId");
        String code = MapUtils.getString(params, "code");

        if (StringUtils.isBlank(deviceId)) {
            return R.error("设备ID不能为空");
        }
        if (StringUtils.isBlank(code)) {
            return R.error("ID卡号不能为空");
        }
        //使用次数1
        cardService.useCard(code, 1);

        GoodsEntity goods = goodsService.queryObject(new Long("0"));
        BigDecimal orderMoney = goods.getMoney();

        OrderEntity order = new OrderEntity();

        //消费订单号
        String id = createOrderId();
        order.setId(id);
        order.setDeviceId(deviceId);
        order.setOrderType(Constant.OrderType.USER_DEVICE_BY_CARD.getValue());
        order.setCardCode(code);
        order.setOrderMoney(orderMoney);
        order.setPayStatus(Constant.PayStatus.SUCCESS.getValue());
        order.setPayType(Constant.PayType.CARD.getValue());
        order.setCreateTime(new Date());
        order.setPayTime(new Date());

        //保存为支付成功订单
        save(order);

        // TODO: 2017/8/15 分成
        return R.ok();
    }


    //id卡充值订单
    @Override
    public String buildCardRechargeOrder(Long userId, String cardCode, Long goodsId) {
        CardEntity card = cardService.queryObjectByCode(cardCode);
        Assert.isNull(card, "卡号不存在");

        GoodsEntity goods = goodsService.queryObject(goodsId);
        Assert.isNull(goods, "商品不存在");

        if (goods.getType().compareTo(Constant.GoodsType.DEVICE_USE_COUNT.getValue()) != 0) {
            throw new RRException("商品类型错误");
        }

        OrderEntity order = new OrderEntity();
        //消费订单号
        String id = createOrderId();
        order.setId(id);
        order.setOrderType(Constant.OrderType.ID_RECHARGE.getValue());
        order.setCardCode(cardCode);
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setOrderMoney(goods.getMoney());
        order.setPayStatus(Constant.PayStatus.NEED_PAY.getValue());
        order.setCreateTime(new Date());
        save(order);

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
