package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.DealerShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface DealerShopService {
	
	DealerShopEntity queryObject(String id);
	
	List<DealerShopEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DealerShopEntity dealerShop);
	
	void update(DealerShopEntity dealerShop);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
