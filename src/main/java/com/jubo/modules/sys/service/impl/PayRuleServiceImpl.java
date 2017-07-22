package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.PayRuleDao;
import com.jubo.modules.sys.entity.PayRuleEntity;
import com.jubo.modules.sys.service.PayRuleService;



@Service("payRuleService")
public class PayRuleServiceImpl implements PayRuleService {
	@Autowired
	private PayRuleDao payRuleDao;
	
	@Override
	public PayRuleEntity queryObject(String id){
		return payRuleDao.queryObject(id);
	}
	
	@Override
	public List<PayRuleEntity> queryList(Map<String, Object> map){
		return payRuleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return payRuleDao.queryTotal(map);
	}
	
	@Override
	public void save(PayRuleEntity payRule){
		payRuleDao.save(payRule);
	}
	
	@Override
	public void update(PayRuleEntity payRule){
		payRuleDao.update(payRule);
	}
	
	@Override
	public void delete(String id){
		payRuleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		payRuleDao.deleteBatch(ids);
	}
	
}
