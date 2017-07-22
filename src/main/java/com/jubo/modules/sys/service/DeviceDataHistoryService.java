package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.DeviceDataHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface DeviceDataHistoryService {
	
	DeviceDataHistoryEntity queryObject(String id);
	
	List<DeviceDataHistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceDataHistoryEntity deviceDataHistory);
	
	void update(DeviceDataHistoryEntity deviceDataHistory);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
