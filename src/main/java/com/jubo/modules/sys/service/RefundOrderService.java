package com.jubo.modules.sys.service;

import com.jubo.modules.sys.entity.RefundOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-21 22:46:51
 */
public interface RefundOrderService {
	
	RefundOrderEntity queryObject(String id);
	
	List<RefundOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RefundOrderEntity refundOrder);
	
	void update(RefundOrderEntity refundOrder);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
