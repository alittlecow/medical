package com.jubo.modules.sys.service;


import com.jubo.modules.sys.entity.AccountEnchashmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface AccountEnchashmentService {
	
	AccountEnchashmentEntity queryObject(String id);
	
	List<AccountEnchashmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AccountEnchashmentEntity accountEnchashment);
	
	void update(AccountEnchashmentEntity accountEnchashment);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
