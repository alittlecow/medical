package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.PushMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface PushMessageService {
	
	PushMessageEntity queryObject(String id);
	
	List<PushMessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PushMessageEntity pushMessage);
	
	void update(PushMessageEntity pushMessage);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
