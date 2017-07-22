package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.OrderLogDao;
import com.jubo.modules.sys.entity.OrderLogEntity;
import com.jubo.modules.sys.service.OrderLogService;



@Service("orderLogService")
public class OrderLogServiceImpl implements OrderLogService {
	@Autowired
	private OrderLogDao orderLogDao;
	
	@Override
	public OrderLogEntity queryObject(String id){
		return orderLogDao.queryObject(id);
	}
	
	@Override
	public List<OrderLogEntity> queryList(Map<String, Object> map){
		return orderLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderLogDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderLogEntity orderLog){
		orderLogDao.save(orderLog);
	}
	
	@Override
	public void update(OrderLogEntity orderLog){
		orderLogDao.update(orderLog);
	}
	
	@Override
	public void delete(String id){
		orderLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		orderLogDao.deleteBatch(ids);
	}
	
}
