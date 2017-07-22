package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public DeviceEntity queryObject(String id){
		return deviceDao.queryObject(id);
	}
	
	@Override
	public List<DeviceEntity> queryList(Map<String, Object> map){
		return deviceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deviceDao.queryTotal(map);
	}
	
	@Override
	public void save(DeviceEntity device){
		deviceDao.save(device);
	}
	
	@Override
	public void update(DeviceEntity device){
		deviceDao.update(device);
	}
	
	@Override
	public void delete(String id){
		deviceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		deviceDao.deleteBatch(ids);
	}
	
}
