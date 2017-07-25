package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.R;
import com.jubo.common.utils.SMSUtils;
import com.jubo.common.validator.Assert;
import com.jubo.modules.api.annotation.AuthIgnore;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.entity.SysUserTokenEntity;
import com.jubo.modules.sys.service.SysUserService;
import com.jubo.modules.sys.service.SysUserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/23
 */
@RestController
@RequestMapping("/api/app/user")
@Api("用户接口")
public class AppUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 注册
     */
    @AuthIgnore
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public R register(@RequestBody Map<String, Object> params) {
        String mobile = MapUtils.getString(params, "mobile");
        String verifyCode = MapUtils.getString(params, "verifyCode");
        String password = MapUtils.getString(params, "password");

        Assert.isNotPhone(mobile, "手机号码格式错误");
        Assert.isNotValidPassword(password, "请输入6-20位字母数字组合密码");
        if (!SMSUtils.isValid(mobile, verifyCode)) {
            return R.error("验证码错误");
        }
        //用户信息
        SysUserEntity user = sysUserService.queryByMobile(mobile);
        if (user != null) {
            return R.error("手机号码已注册");
        }
        sysUserService.register(mobile, password);
        return R.ok();
    }

    /**
     * 登录
     */
    @AuthIgnore
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(@RequestBody Map<String, Object> params) {
        String mobile = MapUtils.getString(params, "mobile");
        String password = MapUtils.getString(params, "password");
        //用户信息
        SysUserEntity user = sysUserService.queryByMobile(mobile);

        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getUserId());
        return r;
    }

    /**
     * 修改登录用户密码
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public R resetPassword(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String password = MapUtils.getString(params, "password");
        String newPassword = MapUtils.getString(params, "newPassword");

        Assert.isNotValidPassword(newPassword, "新密码格式错误");

        //sha256加密
        password = new Sha256Hash(password, user.getSalt()).toHex();
        //sha256加密
        newPassword = new Sha256Hash(newPassword, user.getSalt()).toHex();

        //更新密码
        int count = sysUserService.updatePassword(user.getUserId(), password, newPassword);
        if (count == 0) {
            return R.error("原密码不正确");
        }

        return R.ok();
    }


    /**
     * 退出登陆
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public R logout(@LoginUser SysUserEntity user) {

        SysUserTokenEntity userTokenEntity = sysUserTokenService.queryByUserId(user.getUserId());

        //更新token失效时间
        userTokenEntity.setExpireTime(new Date());
        sysUserTokenService.update(userTokenEntity);

        return R.ok();
    }


    /**
     * 修改用户信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "password", required = false, dataType = "String")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R update(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        params.put("userId",user.getUserId());
        sysUserService.updateAppUser(params);

        return R.ok();
    }

    /**
     * 信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping("/info")
    public R info(@LoginUser SysUserEntity user) {

        Assert.isNull(user, "token失效，请重新登录");

        return R.ok().putData(user);
    }

}
