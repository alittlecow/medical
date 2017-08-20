package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.*;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.*;
import com.jubo.modules.sys.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private SysDeptService sysDeptService;

    @Autowired
    private CardHistoryService cardHistoryService;


    /**
     * ID激活
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/active", method = RequestMethod.POST)
    public R active(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        String code = MapUtils.getString(params, "code");

        String cardHoldName = MapUtils.getString(params, "cardHoldName");

        String cardHoldPhone = MapUtils.getString(params, "cardHoldPhone");

        if (!ParamVerifyUtils.checkAllValuesNotEmpty(code, cardHoldName, cardHoldPhone)) {
            return R.error("卡号,联系人姓名，联系人手机号码不能为空");
        }

        if (!ParamVerifyUtils.isPhoneNo(cardHoldPhone)) {
            return R.error("联系人手机号码格式错误");
        }

        CardEntity card = cardService.queryObjectByCode(code);
        if (card == null) {
            return R.error("ID卡不存在");
        }

        if (Constant.CommonStatus.TRUE.getValue().compareTo(card.getIsActive()) == 0) {
            return R.error("ID卡已经被激活");
        }

        Map map = new HashMap();
        map.put("cardHoldName", cardHoldName);
        map.put("cardHoldPhone", cardHoldPhone);
        map.put("code", code);

        cardService.active(map);

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

        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("卡号不能为空");
        }

        CardEntity card = cardService.queryObjectByCode(code);
        if (card == null) {
            return R.error("ID卡不存在");
        }

        return R.ok().putData(card);
    }


    /**
     * ID卡使用记录
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public R history(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("卡号不能为空");
        }

        CardEntity card = cardService.queryObjectByCode(code);
        if (card == null) {
            return R.error("ID卡不存在");
        }

        //查询列表数据
        Query query = new Query(params);

        List<CardHistoryEntity> cardHistoryList = cardHistoryService.queryList(query);
        int total = cardHistoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(cardHistoryList, total, query.getLimit(), query.getPage());

        return R.ok().putData(pageUtil);
    }


    /**
     * ID绑定
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public R bind(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        String code = MapUtils.getString(params, "code");

        CardEntity card = cardService.queryObjectByCode(code);
        if (card == null) {
            return R.error("ID卡不存在");
        }

        if (Constant.CommonStatus.TRUE.getValue().compareTo(card.getIsActive()) != 0) {
            return R.error("ID卡还未被激活");
        }

        if (Constant.CommonStatus.TRUE.getValue().compareTo(card.getIsBind()) == 0) {
            return R.error("ID卡已经绑定");
        }

        card = cardService.queryObjectByUserId(user.getUserId());
        if (card != null) {
            return R.error("用户已经绑定过ID卡");
        }

        Map map = new HashMap();
        map.put("code", code);
        map.put("userId", user.getUserId());

        cardService.bind(map);

        return R.ok();
    }


    /**
     * 我的id卡
     */
    @RequestMapping(value = "/mycard", method = RequestMethod.POST)
    public R myCard(@LoginUser SysUserEntity user) {

        CardEntity card = cardService.queryObjectByUserId(user.getUserId());
        if (card == null) {
            return R.error("ID卡不存在");
        }

        return R.ok().putData(card);
    }

    /**
     * 分销商所有id卡
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public R list(@LoginUser SysUserEntity user) {

        List<Long> merchantIdList = sysDeptService.getAllMerchantByUserId(user.getUserId());
        List<CardEntity> list = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(merchantIdList)){
            Map map = new HashMap();
            map.put("merchantList",merchantIdList);
            list = cardService.queryList(map);
        }

        return R.ok().putData(list);
    }

}
