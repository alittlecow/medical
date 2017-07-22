package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.AccountTransactionHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface AccountTransactionHistoryService {
	
	AccountTransactionHistoryEntity queryObject(String id);
	
	List<AccountTransactionHistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AccountTransactionHistoryEntity accountTransactionHistory);
	
	void update(AccountTransactionHistoryEntity accountTransactionHistory);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
