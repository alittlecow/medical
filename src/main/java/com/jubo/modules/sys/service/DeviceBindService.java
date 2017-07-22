package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.DeviceBindEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface DeviceBindService {
	
	DeviceBindEntity queryObject(String id);
	
	List<DeviceBindEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeviceBindEntity deviceBind);
	
	void update(DeviceBindEntity deviceBind);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
