package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.ChargePolicyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface ChargePolicyService {
	
	ChargePolicyEntity queryObject(String id);
	
	List<ChargePolicyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ChargePolicyEntity chargePolicy);
	
	void update(ChargePolicyEntity chargePolicy);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
