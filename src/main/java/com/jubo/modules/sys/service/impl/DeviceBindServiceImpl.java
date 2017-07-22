package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.DeviceBindDao;
import com.jubo.modules.sys.entity.DeviceBindEntity;
import com.jubo.modules.sys.service.DeviceBindService;



@Service("deviceBindService")
public class DeviceBindServiceImpl implements DeviceBindService {
	@Autowired
	private DeviceBindDao deviceBindDao;
	
	@Override
	public DeviceBindEntity queryObject(String id){
		return deviceBindDao.queryObject(id);
	}
	
	@Override
	public List<DeviceBindEntity> queryList(Map<String, Object> map){
		return deviceBindDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deviceBindDao.queryTotal(map);
	}
	
	@Override
	public void save(DeviceBindEntity deviceBind){
		deviceBindDao.save(deviceBind);
	}
	
	@Override
	public void update(DeviceBindEntity deviceBind){
		deviceBindDao.update(deviceBind);
	}
	
	@Override
	public void delete(String id){
		deviceBindDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		deviceBindDao.deleteBatch(ids);
	}
	
}
