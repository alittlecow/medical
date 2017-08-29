package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.AuthIgnore;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.*;
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

    @Autowired
    private OrderCallBackService orderCallBackService;

    @Autowired
    private DeviceService deviceService;


    /**
     * 门店消费订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/consumeorder", method = RequestMethod.POST)
    public R consumeOrder(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        if (new Byte("0").equals(user.getIsAuth())) {
            return R.error("请先进行实名认证");
        }
        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("设备编码不能为空");
        }

        DeviceEntity device = deviceService.queryObjectByCode(code);
        if (device == null) {
            return R.error("设备不存在");
        }
        device.checkValid();

        String orderId = orderService.buildConsumeOrder(user.getUserId(), device.getId());

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
        Assert.isNull(goodsId, "商品号不能为空");

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
        Assert.isNull(goodsId, "商品号不能为空");

        String orderId = rechargeOrderService.buildIdRechargeOrder(user.getUserId(), goodsId);

        Map map = new HashMap();
        map.put("orderId", orderId);

        return R.ok().putData(map);
    }

    /**
     * 缴纳押金订单接口
     */
    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public R deposit(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        //设备数量
        int num = MapUtils.getIntValue(params, "num");

        if (num <= 0) {
            return R.error("至少缴纳一台设备的押金");
        }

        String orderId = rechargeOrderService.buildDepositOrder(user.getUserId(), num);

        Map map = new HashMap();
        map.put("orderId", orderId);

        return R.ok().putData(map);
    }


    //用户免费体验订单
    @RequestMapping("/freeuse")
    public R freeUse(@LoginUser SysUserEntity user, @RequestBody Map<String, String> params) {
        //是否需要先进行实名认证
        if (new Byte("0").equals(user.getIsAuth())) {
            return R.error("请先进行实名认证");
        }
        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("设备编码不能为空");
        }

        DeviceEntity device = deviceService.queryObjectByCode(code);
        if (device == null) {
            return R.error("设备不存在");
        }
        device.checkValid();

        deviceService.freeUse(code, user);

        return R.ok();
    }

    @AuthIgnore
    @RequestMapping(value = "h1")
    public R handleRechargeOrder(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String orderId = MapUtils.getString(params, "orderId");
        Byte payType = MapUtils.getByte(params, "payType");

        orderCallBackService.handleRechargeOrder(orderId, payType);
        return R.ok();
    }

    @AuthIgnore
    @RequestMapping(value = "h2")
    public R handleSettlementOrder(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String orderId = MapUtils.getString(params, "orderId");
        Byte payType = MapUtils.getByte(params, "payType");

        orderCallBackService.handleSettlementOrder(orderId, payType);
        return R.ok();
    }
}
