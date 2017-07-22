package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface GoodsService {
	
	GoodsEntity queryObject(String id);
	
	List<GoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsEntity goods);
	
	void update(GoodsEntity goods);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
