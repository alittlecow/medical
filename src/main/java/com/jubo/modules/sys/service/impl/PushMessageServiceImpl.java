package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.PushMessageDao;
import com.jubo.modules.sys.entity.PushMessageEntity;
import com.jubo.modules.sys.service.PushMessageService;



@Service("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {
	@Autowired
	private PushMessageDao pushMessageDao;
	
	@Override
	public PushMessageEntity queryObject(String id){
		return pushMessageDao.queryObject(id);
	}
	
	@Override
	public List<PushMessageEntity> queryList(Map<String, Object> map){
		return pushMessageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return pushMessageDao.queryTotal(map);
	}
	
	@Override
	public void save(PushMessageEntity pushMessage){
		pushMessageDao.save(pushMessage);
	}
	
	@Override
	public void update(PushMessageEntity pushMessage){
		pushMessageDao.update(pushMessage);
	}
	
	@Override
	public void delete(String id){
		pushMessageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		pushMessageDao.deleteBatch(ids);
	}
	
}
