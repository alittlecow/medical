package com.jubo.modules.sys.dao;

import com.jubo.modules.sys.entity.DeviceDataHistoryEntity;
;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
@Mapper
public interface DeviceDataHistoryDao extends BaseDao<DeviceDataHistoryEntity> {


    void batchSave(List<DeviceDataHistoryEntity> list);
}
