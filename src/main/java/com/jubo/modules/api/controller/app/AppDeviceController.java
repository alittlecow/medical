package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.Constant;
import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.entity.SysDeptEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.DeviceService;
import com.jubo.modules.sys.service.SysDeptService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author pengxiao
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("/api/app/device")
public class AppDeviceController {
    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private DeviceService deviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@LoginUser SysUserEntity user, @RequestParam Map<String, Object> params) {

        List<Long> merchantIdList = sysDeptService.getAllMerchantByUserId(user.getUserId());

        List<DeviceEntity> deviceList = new ArrayList<>();
        int total = 0;
        Query query = new Query(params);

        if (CollectionUtils.isNotEmpty(merchantIdList)) {
            params.put("merchantList", merchantIdList);
            //查询列表数据
            deviceList = deviceService.queryList(query);
            total = deviceService.queryTotal(query);
        }

        PageUtils pageUtil = new PageUtils(deviceList, total, query.getLimit(), query.getPage());

        return R.ok().putData(pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        DeviceEntity device = deviceService.queryObject(id);

        return R.ok().putData(device);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DeviceEntity device) {

        deviceService.update(device);

        return R.ok();
    }

    /**
     * 使用设备
     */
//    @RequestMapping("/useDevice")
//    public R useDevice(@RequestBody DeviceEntity device) {
//        deviceService.useDevice(device);
//        return R.ok();
//    }

    /**
     * 设备
     *
     * @param device
     * @return
     */
    @RequestMapping("/history")
    public R history(@RequestBody DeviceEntity device) {
        deviceService.update(device);

        return R.ok();
    }



}
