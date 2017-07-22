package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.RefundOrderDao;
import com.jubo.modules.sys.entity.RefundOrderEntity;
import com.jubo.modules.sys.service.RefundOrderService;



@Service("refundOrderService")
public class RefundOrderServiceImpl implements RefundOrderService {
	@Autowired
	private RefundOrderDao refundOrderDao;
	
	@Override
	public RefundOrderEntity queryObject(String id){
		return refundOrderDao.queryObject(id);
	}
	
	@Override
	public List<RefundOrderEntity> queryList(Map<String, Object> map){
		return refundOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return refundOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(RefundOrderEntity refundOrder){
		refundOrderDao.save(refundOrder);
	}
	
	@Override
	public void update(RefundOrderEntity refundOrder){
		refundOrderDao.update(refundOrder);
	}
	
	@Override
	public void delete(String id){
		refundOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		refundOrderDao.deleteBatch(ids);
	}
	
}
