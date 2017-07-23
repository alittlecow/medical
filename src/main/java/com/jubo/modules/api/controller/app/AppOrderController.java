package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.GoodsService;
import com.jubo.modules.sys.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.collections.MapUtils;
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
    private GoodsService goodsService;


    /**
     * 生成订单接口
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
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
}
