package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.DateUtils;
import com.jubo.common.utils.ParamVerifyUtils;
import com.jubo.common.utils.R;
import com.jubo.common.utils.SMSUtils;
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
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.jubo.common.utils.ErrorMessage.EMAIL_FORMAT_ERROR;

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

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private CardService cardService;

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

        if (!ParamVerifyUtils.checkAllValuesNotEmpty(mobile, password)) {
            return R.error("手机号,密码不能为空");
        }
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

        List<Long> roleList = sysUserRoleService.queryRoleIdList(user.getUserId());

        Collections.sort(roleList);

        //账户余额，ID卡数量
        AccountInfoEntity account = accountInfoService.queryObjectByUserId(user.getUserId());
        r.put("balance", account.getBalance());

        CardEntity card = cardService.queryObjectByUserId(user.getUserId());
        r.put("cardNum", card == null ? 0 : 1);

        return r.put("role", roleList.get(0));
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
     * 找回密码
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @AuthIgnore
    @RequestMapping(value = "/getBackPassword", method = RequestMethod.POST)
    public R getBackPassword(@RequestBody Map<String, Object> params) {
        String password = MapUtils.getString(params, "password");
        String mobile = MapUtils.getString(params, "mobile");

        Assert.isNotValidPassword(password, "密码格式错误");

        SysUserEntity user = sysUserService.queryByMobile(mobile);

        if (user == null) {
            return R.error("用户不存在");
        }

        //sha256加密
        password = new Sha256Hash(password, user.getSalt()).toHex();

        //更新密码
        int count = sysUserService.getBackPassword(user.getUserId(), password);

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
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R update(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String username = MapUtils.getString(params, "username");
        String sex = MapUtils.getString(params, "sex");
        String email = MapUtils.getString(params, "email");
        String birthday = MapUtils.getString(params, "birthday");

        if (ParamVerifyUtils.checkAllValuesEmpty(username, sex, email, birthday)) {
            return R.ok();
        }

        //日期格式校验
        if (StringUtils.isNotBlank(birthday) && !ParamVerifyUtils.isDate(birthday)) {
            return R.error("日期格式错误");
        }

        if (StringUtils.isNotBlank(email) && !ParamVerifyUtils.isEmail(email)) {
            return R.error(EMAIL_FORMAT_ERROR);
        }

        params.put("userId", user.getUserId());
        sysUserService.updateAppUser(params);

        return R.ok();
    }

    /**
     * 信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public R info(@LoginUser SysUserEntity user) {

        Assert.isNull(user, "token失效，请重新登录");

        return R.ok().putData(user);
    }

    /**
     * 实名认证
     */
    @RequestMapping("/auth")
    public R auth(@RequestBody Map<String, String> params, @LoginUser SysUserEntity userEntity) {

        String name = params.get("realName");
        if (StringUtils.isBlank(name)) {
            return R.error("真实姓名不能为空");
        }
        String idCard = params.get("idCard");
        if (!ParamVerifyUtils.isIdCard(idCard)) {
            return R.error("身份证号码格式错误");
        }
//        String cardNo = params.get("cardNo");
//        if (StringUtils.isBlank(cardNo)) {
//            return R.error("银行卡不能为空");
//        }

        sysUserService.auth(params, userEntity);
        return R.ok();
    }

}
