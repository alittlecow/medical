package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.AccountSettlementEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface AccountSettlementService {
	
	AccountSettlementEntity queryObject(String id);
	
	List<AccountSettlementEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AccountSettlementEntity accountSettlement);
	
	void update(AccountSettlementEntity accountSettlement);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
