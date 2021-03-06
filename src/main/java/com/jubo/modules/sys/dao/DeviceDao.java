package com.jubo.modules.sys.dao;

import com.jubo.modules.sys.entity.DeviceEntity;
;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@Mapper
public interface DeviceDao extends BaseDao<DeviceEntity> {

    DeviceEntity queryObjectByCode(String code);


    void updateByCode(Map map);
}
