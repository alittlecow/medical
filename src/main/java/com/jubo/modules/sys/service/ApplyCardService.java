package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.ApplyCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface ApplyCardService {
	
	ApplyCardEntity queryObject(String id);
	
	List<ApplyCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ApplyCardEntity applyCard);
	
	void update(ApplyCardEntity applyCard);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
