package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.AuthIgnore;
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
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private AccountInfoService accountInfoService;


    /**
     * 门店消费订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/consumeorder", method = RequestMethod.POST)
    public R consumeOrder(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String deviceId = MapUtils.getString(params, "deviceId");
        Assert.isBlank(deviceId, "设备ID不能为空");

        // TODO: 2017/8/16 设备可使用校验

        String orderId = orderService.buildConsumeOrder(user.getUserId(), deviceId);
        Map map = new HashMap();
        map.put("orderId", orderId);

        return R.ok().putData(map);
    }


    /**
     * ID卡充值订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/cardrecharge", method = RequestMethod.POST)
    public R cardRecharge(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        //ID卡充值订单
        String code = MapUtils.getString(params, "code");
        Assert.isBlank(code, "充值卡号不能为空");

        Long goodsId = MapUtils.getLong(params, "goodsId");
        Assert.isNull(goodsId,"商品号不能为空");

        String orderId = orderService.buildCardRechargeOrder(user.getUserId(), code, goodsId);

        Map map = new HashMap();
        map.put("orderId", orderId);
        return R.ok().putData(map);
    }

    /**
     * 账户充值订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/accountrecharge", method = RequestMethod.POST)
    public R accountRecharge(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        //账户充值
        Long goodsId = MapUtils.getLong(params, "goodsId");
        Assert.isNull(goodsId,"商品号不能为空");

        String orderId = rechargeOrderService.buildIdRechargeOrder(user.getUserId(), goodsId);

        Map map = new HashMap();
        map.put("orderId", orderId);

        return R.ok().putData(map);
    }
}
