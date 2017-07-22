package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.PayRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface PayRuleService {
	
	PayRuleEntity queryObject(String id);
	
	List<PayRuleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PayRuleEntity payRule);
	
	void update(PayRuleEntity payRule);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
