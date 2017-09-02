package com.jubo.modules.api.controller.app;

import com.jubo.common.utils.PageUtils;
import com.jubo.common.utils.Query;
import com.jubo.common.utils.R;
import com.jubo.modules.api.annotation.LoginUser;
import com.jubo.modules.sys.entity.DeviceDataHistoryEntity;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.service.DeviceDataHistoryService;
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

    @Autowired
    private DeviceDataHistoryService deviceDataHistoryService;

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
    @RequestMapping("/info")
    public R info(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {
        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("设备编码不能为空");
        }

        List<Long> merchantIdList = sysDeptService.getAllMerchantByUserId(user.getUserId());

        List<DeviceEntity> deviceList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(merchantIdList)) {
            Map<String, Object> map = new HashMap<>();

            map.put("merchantList", merchantIdList);
            map.put("code", code);
            //查询列表数据
            deviceList = deviceService.queryList(map);
        }

        if (CollectionUtils.isEmpty(deviceList)) {
            return R.error("设备不存在");
        }

        return R.ok().putData(deviceList.get(0));
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        String code = MapUtils.getString(params, "code");
        if (StringUtils.isBlank(code)) {
            return R.error("设备编码不能为空");
        }

        List<Long> merchantIdList = sysDeptService.getAllMerchantByUserId(user.getUserId());

        List<DeviceEntity> deviceList = new ArrayList<>();


        if (CollectionUtils.isNotEmpty(merchantIdList)) {
            Map<String, Object> map = new HashMap<>();

            map.put("merchantList", merchantIdList);
            //查询列表数据
            deviceList = deviceService.queryList(map);
        }

        if (CollectionUtils.isEmpty(deviceList)) {
            return R.error("设备不存在");
        }

        deviceService.updateByCode(params);

        return R.ok();
    }

    /**
     * 设备
     * code.startTime,endTime
     */
    @RequestMapping("/historydata")
    public R history(@LoginUser SysUserEntity user, @RequestBody Map<String, Object> params) {

        List<Long> merchantIdList = sysDeptService.getAllMerchantByUserId(user.getUserId());

        List<DeviceEntity> deviceList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(merchantIdList)) {
            Map<String, Object> map = new HashMap<>();

            map.put("merchantList", merchantIdList);
            //查询列表数据
            deviceList = deviceService.queryList(map);
        }

        if (CollectionUtils.isEmpty(deviceList)) {
            return R.error("设备不存在");
        }

        Query query = new Query(params);

        List<DeviceDataHistoryEntity> deviceDataHistoryList = deviceDataHistoryService.queryList(query);

        int total = deviceDataHistoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(deviceDataHistoryList, total, query.getLimit(), query.getPage());

        return R.ok().putData(pageUtil);
    }


}
