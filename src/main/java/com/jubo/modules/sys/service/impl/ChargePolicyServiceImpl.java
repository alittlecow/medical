package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.ChargePolicyDao;
import com.jubo.modules.sys.entity.ChargePolicyEntity;
import com.jubo.modules.sys.service.ChargePolicyService;



@Service("chargePolicyService")
public class ChargePolicyServiceImpl implements ChargePolicyService {
	@Autowired
	private ChargePolicyDao chargePolicyDao;
	
	@Override
	public ChargePolicyEntity queryObject(String id){
		return chargePolicyDao.queryObject(id);
	}
	
	@Override
	public List<ChargePolicyEntity> queryList(Map<String, Object> map){
		return chargePolicyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return chargePolicyDao.queryTotal(map);
	}
	
	@Override
	public void save(ChargePolicyEntity chargePolicy){
		chargePolicyDao.save(chargePolicy);
	}
	
	@Override
	public void update(ChargePolicyEntity chargePolicy){
		chargePolicyDao.update(chargePolicy);
	}
	
	@Override
	public void delete(String id){
		chargePolicyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		chargePolicyDao.deleteBatch(ids);
	}
	
}
