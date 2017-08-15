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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author pengxiao
 * @date 2017-07-21 22:46:51
 */
@RestController
@RequestMapping("/app/device")
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

        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if (user.getUserId() != Constant.SUPER_ADMIN) {
            map.put("deptFilter", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }
        List<SysDeptEntity> deptList = sysDeptService.queryList(map);

        params.put("merchantList", deptList);

        //查询列表数据
        Query query = new Query(params);

        List<DeviceEntity> deviceList = deviceService.queryList(query);
        int total = deviceService.queryTotal(query);

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
}
