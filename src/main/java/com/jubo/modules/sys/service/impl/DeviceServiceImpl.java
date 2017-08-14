package com.jubo.modules.sys.service.impl;

import com.jubo.common.mtqq.Mqttutils;
import com.jubo.common.utils.Constant;
import com.jubo.common.utils.SpringContextUtils;
import com.jubo.common.utils.UUIDUtil;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.DeviceDao;
import com.jubo.modules.sys.entity.DeviceEntity;
import com.jubo.modules.sys.service.DeviceService;


@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Override
    public DeviceEntity queryObject(String id) {
        return deviceDao.queryObject(id);
    }

    @Override
    public List<DeviceEntity> queryList(Map<String, Object> map) {
        return deviceDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return deviceDao.queryTotal(map);
    }

    @Override
    public void save(DeviceEntity device) {
        //初始化绑定状态
        device.setId(UUIDUtil.getUUId());
        device.setBindStatus(Constant.DeviceBindType.NEVER_USE.getValue());
        device.setOperateTime(new Date());
        device.setIsBreakdown(Constant.DeviceBreakdownStatus.NORMAL.getValue());
        device.setUseStatus(Constant.DeviceUseStatus.NEVER_USE.getValue());
        deviceDao.save(device);
    }

    @Override
    public void update(DeviceEntity device) {
        //更新操作时间
        device.setOperateTime(new Date());
        deviceDao.update(device);
    }

    @Override
    public void delete(String id) {
        deviceDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        deviceDao.deleteBatch(ids);
    }

    @Override
    public void useDevice(DeviceEntity device) {
        String code = device.getCode();

        //发布使用设备请求
        String publicTopic = "start";
        Map<String, String> message = new HashMap<String, String>();
        message.put("code", "20170001");
        Mqttutils mqttutils = (Mqttutils) SpringContextUtils.getBean("Mqttutils");
        mqttutils.publish(publicTopic, message);

        //订阅设备采集数据主题
        try {
            Mqttutils.client.subscribe(code + "/subsd", 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        //订阅设备停止使用主题
        try {
            Mqttutils.client.subscribe(code + "/stop", 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
