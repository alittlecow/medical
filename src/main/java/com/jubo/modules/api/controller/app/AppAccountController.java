package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.R;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.AccountInfoEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.AccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengxiao
 * @date 2017/7/23
 */
@RestController
@Api("app账户接口")
@RequestMapping("/api/app/account")
public class AppAccountController {

    @Autowired
    private AccountInfoService accountInfoService;


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

}
