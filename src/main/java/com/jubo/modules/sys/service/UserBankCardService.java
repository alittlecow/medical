package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.UserBankCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:50
 */
public interface UserBankCardService {
	
	UserBankCardEntity queryObject(String id);
	
	List<UserBankCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserBankCardEntity userBankCard);
	
	void update(UserBankCardEntity userBankCard);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
