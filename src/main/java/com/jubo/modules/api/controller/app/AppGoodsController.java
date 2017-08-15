package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.common.validator.Assert;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.service.GoodsService;
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
@Api("app商品接口")
@RequestMapping("/api/app/goods")
public class AppGoodsController {

    @Autowired
    private GoodsService goodsService;


    /**
     * 查询商品列表
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "token", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public R list(@RequestBody Map<String, Object> params) {
        Byte type = MapUtils.getByte(params, "type");
        Assert.isNull(type, "商品类型不能为空");

        //查询列表数据
        Query query = new Query(params);
        List<GoodsEntity> goodsList = goodsService.queryList(query);
        int total = goodsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsList, total, query.getLimit(), query.getPage());
        return R.ok().putData(pageUtil);
    }

}
