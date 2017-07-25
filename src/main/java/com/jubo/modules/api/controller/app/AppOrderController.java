package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.CardEntity;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/23
 */
@RestController
@Api("app订单接口")
@RequestMapping("/api/app/order")
public class AppOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CardService cardService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private AccountInfoService accountInfoService;


    /**
     * 门店消费生成订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/consumeorder", method = RequestMethod.POST)
    public R create(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String goodsId = MapUtils.getString(params, "goodsId");
        Assert.isBlank(goodsId, "商品ID不能为空");

        GoodsEntity goods = goodsService.queryObject(goodsId);
        Assert.isNull(goodsId, "商品不存在");

        String orderId = orderService.buildOrder(user.getUserId(), goodsId, goods.getMoney());
        Map map = new HashMap();
        map.put("orderId", orderId);
        return R.ok().putData(map);
    }


    /**
     * ID卡充值生成订单
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/rechargeorder", method = RequestMethod.POST)
    public R recharge(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        Byte orderType = MapUtils.getByte(params, "orderType");

        String objectId = null;
        //ID卡充值订单
        if (Constant.RechargeOrderType.ID_RECHARGE.getValue().compareTo(orderType) == 0) {
            String code = MapUtils.getString(params, "code");
            Assert.isBlank(code, "充值卡号不能为空");
            CardEntity card = cardService.queryObjectByCode(code);
            Assert.isNull(card, "ID卡号错误");
            objectId = code;
        }

        //账户充值
        if (Constant.RechargeOrderType.ACCOUNT_RECHARGE.getValue().compareTo(orderType) == 0) {
            AccountInfoEntity account = accountInfoService.queryObjectByUserId(user.getUserId());
            objectId = account.getId();
        }

        if (StringUtils.isBlank(objectId)) {
            return R.error("充值类型错误");
        }

        String goodsId = MapUtils.getString(params, "goodsId");
        GoodsEntity goods = goodsService.queryObject(goodsId);
        Assert.isNull(goodsId, "商品不存在");

        String orderId = rechargeOrderService.buildIdRechargeOrder(orderType, objectId, user.getUserId(),
                goodsId, goods.getMoney());

        Map map = new HashMap();
        map.put("orderId", orderId);

        return R.ok().putData(map);
    }
}
