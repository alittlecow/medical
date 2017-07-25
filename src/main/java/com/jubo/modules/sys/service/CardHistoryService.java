package com.jubo.modules.sys.service;


import com.jubo.modules.sys.entity.CardHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-25 12:54:27
 */
public interface CardHistoryService {
	
	CardHistoryEntity queryObject(String id);
	CardHistoryEntity queryObjectByCode(String code);

	List<CardHistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CardHistoryEntity cardHistory);
	
	void update(CardHistoryEntity cardHistory);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
