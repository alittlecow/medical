package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.SettlementRuleDao;
import com.jubo.modules.sys.entity.SettlementRuleEntity;
import com.jubo.modules.sys.service.SettlementRuleService;



@Service("settlementRuleService")
public class SettlementRuleServiceImpl implements SettlementRuleService {
	@Autowired
	private SettlementRuleDao settlementRuleDao;
	
	@Override
	public SettlementRuleEntity queryObject(String id){
		return settlementRuleDao.queryObject(id);
	}
	
	@Override
	public List<SettlementRuleEntity> queryList(Map<String, Object> map){
		return settlementRuleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return settlementRuleDao.queryTotal(map);
	}
	
	@Override
	public void save(SettlementRuleEntity settlementRule){
		settlementRuleDao.save(settlementRule);
	}
	
	@Override
	public void update(SettlementRuleEntity settlementRule){
		settlementRuleDao.update(settlementRule);
	}
	
	@Override
	public void delete(String id){
		settlementRuleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		settlementRuleDao.deleteBatch(ids);
	}
	
}
