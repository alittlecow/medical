package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.DeviceEntity;

import java.util.List;
import java.util.Map;

/**
 * @author pengxiao
 * @date 2017-07-21 22:46:51
 */
public interface DeviceService {

    DeviceEntity queryObject(String id);

    List<DeviceEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DeviceEntity device);

    void update(DeviceEntity device);

    void delete(String id);

    void deleteBatch(String[] ids);

    //根据设备编码使用设备
    void useDevice(String code);


}
