package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.DeviceDataHistoryDao;
import com.jubo.modules.sys.entity.DeviceDataHistoryEntity;
import com.jubo.modules.sys.service.DeviceDataHistoryService;



@Service("deviceDataHistoryService")
public class DeviceDataHistoryServiceImpl implements DeviceDataHistoryService {
	@Autowired
	private DeviceDataHistoryDao deviceDataHistoryDao;
	
	@Override
	public DeviceDataHistoryEntity queryObject(String id){
		return deviceDataHistoryDao.queryObject(id);
	}
	
	@Override
	public List<DeviceDataHistoryEntity> queryList(Map<String, Object> map){
		return deviceDataHistoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deviceDataHistoryDao.queryTotal(map);
	}
	
	@Override
	public void save(DeviceDataHistoryEntity deviceDataHistory){
		deviceDataHistoryDao.save(deviceDataHistory);
	}
	
	@Override
	public void update(DeviceDataHistoryEntity deviceDataHistory){
		deviceDataHistoryDao.update(deviceDataHistory);
	}
	
	@Override
	public void delete(String id){
		deviceDataHistoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		deviceDataHistoryDao.deleteBatch(ids);
	}
	
}
