package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.ErrorMessage;
import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.AccountTransactionHistoryEntity;
import com.jubo.modules.sys.entity.OrderEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import com.jubo.modules.sys.service.AccountTransactionHistoryService;
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

import java.util.List;
import java.util.Map;


/**
 * @author pengxiao
 * @date 2017/7/23
 */
@RestController
@Api("app账户接口")
@RequestMapping(value = "/api/app/account",method = RequestMethod.GET)
public class AppAccountController {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AccountTransactionHistoryService accountHistoryService;

    @Autowired
    private OrderService orderService;


    /**
     * 余额支付
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public R pay(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String orderId = MapUtils.getString(params, "orderId");
        OrderEntity order = orderService.queryObject(orderId);
        Assert.isNull(order, ErrorMessage.ORDER_NOT_EXIST);

        if (user.getUserId() != order.getUserId()) {
            return R.error(ErrorMessage.ORDER_NOT_YOURS);
        }

        //支付订单
        R r = accountInfoService.pay(orderId);

        return r;
    }


    /**
     * 信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public R info(@LoginUser SysUserEntity user) {

        AccountInfoEntity accountInfo = accountInfoService.queryObjectByUserId(user.getUserId());

        return R.ok().putData(accountInfo);
    }


    /**
     * 账户历史
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public R history(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        AccountInfoEntity account = accountInfoService.queryObjectByUserId(user.getUserId());
        params.put("accountId", account.getId());
        //查询列表数据
        Query query = new Query(params);

        List<AccountTransactionHistoryEntity> accountTransactionHistoryList = accountHistoryService.queryList(query);
        int total = accountHistoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(accountTransactionHistoryList, total, query.getLimit(), query.getPage());

        return R.ok().putData(pageUtil);
    }


}
