package com.jubo.modules.sys.service;

import com.jubo.common.utils.R;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017-07-21 22:46:51
 */
public interface DeviceService {

    R freeUse(String code, SysUserEntity user);

    DeviceEntity queryObject(String id);

    DeviceEntity queryObjectByCode(String code);

    List<DeviceEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DeviceEntity device);

    void update(DeviceEntity device);

    void updateByCode(Map map);

    void delete(String id);

    void deleteBatch(String[] ids);

    //根据设备编码使用设备
    void useDevice(String code);


}
