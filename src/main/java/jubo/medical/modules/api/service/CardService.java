package jubo.medical.modules.api.service;


import jubo.medical.modules.api.entity.CardEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 21:42:07
 */
public interface CardService {
	
	CardEntity queryObject(String id);
	
	List<CardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CardEntity card);
	
	void update(CardEntity card);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
