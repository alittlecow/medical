package com.jubo.modules.sys.controller;

import java.util.List;
import java.util.Map;

import com.jubo.common.validator.ValidatorUtils;
import com.jubo.common.validator.group.AddGroup;
import com.jubo.common.validator.group.UpdateGroup;
import com.jubo.modules.sys.entity.GoodsEntity;
import com.jubo.modules.sys.service.GoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<GoodsEntity> goodsList = goodsService.queryList(query);
        int total = goodsService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:info")
    public R info(@PathVariable("id") Long id) {
        GoodsEntity goods = goodsService.queryObject(id);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:save")
    public R save(@RequestBody GoodsEntity goods) {
        ValidatorUtils.validateEntity(goods, AddGroup.class);
        goodsService.save(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:update")
    public R update(@RequestBody GoodsEntity goods) {

        ValidatorUtils.validateEntity(goods, UpdateGroup.class);
        goodsService.update(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goods:delete")
    public R delete(@RequestBody String[] ids) {
        goodsService.deleteBatch(ids);

        return R.ok();
    }

}
