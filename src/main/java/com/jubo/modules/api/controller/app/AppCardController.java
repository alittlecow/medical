package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.ErrorMessage;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.common.validator.ValidatorUtils;
import com.jubo.common.validator.group.AddGroup;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.ApplyCardEntity;
import com.jubo.modules.sys.entity.CardEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.ApplyCardService;
import com.jubo.modules.sys.service.CardService;
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
import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017/7/24
 */
@RestController
@Api("appID卡接口")
@RequestMapping("/api/app/card")
public class AppCardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private ApplyCardService applyCardService;

    /**
     * ID卡绑定
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public R bind(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String code = MapUtils.getString(params, "code");
        Assert.isBlank(code, "ID卡号不存在");

        CardEntity card = cardService.queryObjectByCode(code);
        if (card != null) {
            return R.error("改卡已被绑定");
        }

        Map map = new HashMap();
        map.put("userId", user.getUserId());
        int total = cardService.queryTotal(map);
        if (total > 0) {
            return R.error("用户已经绑定过ID卡");
        }

        cardService.bind(code, user.getUserId());

        return R.ok();
    }


    /**
     * ID卡详情
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public R info(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String cardId = MapUtils.getString(params, "cardId");
        Assert.isBlank(cardId, "ID卡不存在");

        CardEntity card = cardService.queryObject(cardId);
        Assert.isNull(card, "ID卡不存在");
        if (card.getUserId().compareTo(user.getUserId()) != 0) {
            return R.error(ErrorMessage.USER_NO_PERMISSION);
        }
        return R.ok().putData(card);
    }

    /**
     * ID卡申请
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public R apply(@LoginUser SysUserEntity user, @RequestBody ApplyCardEntity applyCardEntity) {
        ValidatorUtils.validateEntity(applyCardEntity, AddGroup.class);
        Assert.isNotPhone(applyCardEntity.getContactPhone(), "手机号码格式错误");

        applyCardEntity.setApplyUserId(user.getUserId());
        applyCardService.apply(applyCardEntity);

        return R.ok();
    }

    /**
     * ID卡申请记录信息
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/applyinfo", method = RequestMethod.POST)
    public R applyInfo(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String applyId = MapUtils.getString(params, "applyId");
        Assert.isBlank(applyId, "申请记录不存在");

        ApplyCardEntity applyCardEntity = applyCardService.queryObject(applyId);
        if (user.getUserId().compareTo(applyCardEntity.getApplyUserId()) != 0) {
            return R.error(ErrorMessage.USER_NO_PERMISSION);
        }

        return R.ok().putData(applyCardEntity);
    }

    /**
     * ID卡申请记录列表
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/applylist", method = RequestMethod.POST)
    public R applyList(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        params.put("applyUserId", user.getUserId());
        List<ApplyCardEntity> list = applyCardService.queryList(params);

        return R.ok().putData(list);
    }

    /**
     * ID卡充值记录
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/rechargerecord", method = RequestMethod.POST)
    public R RechargeRecord(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        return R.ok();
    }
}
