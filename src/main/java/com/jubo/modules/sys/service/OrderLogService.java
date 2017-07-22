package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.OrderLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface OrderLogService {
	
	OrderLogEntity queryObject(String id);
	
	List<OrderLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderLogEntity orderLog);
	
	void update(OrderLogEntity orderLog);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
