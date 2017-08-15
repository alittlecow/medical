package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.SettlementRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author pengxiao
 * @date 2017-08-15 19:20:47
 */
public interface SettlementRuleService {
	
	SettlementRuleEntity queryObject(String id);
	
	List<SettlementRuleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SettlementRuleEntity settlementRule);
	
	void update(SettlementRuleEntity settlementRule);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
