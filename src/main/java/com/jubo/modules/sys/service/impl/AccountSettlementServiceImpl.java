package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.AccountSettlementDao;
import com.jubo.modules.sys.entity.AccountSettlementEntity;
import com.jubo.modules.sys.service.AccountSettlementService;



@Service("accountSettlementService")
public class AccountSettlementServiceImpl implements AccountSettlementService {
	@Autowired
	private AccountSettlementDao accountSettlementDao;
	
	@Override
	public AccountSettlementEntity queryObject(String id){
		return accountSettlementDao.queryObject(id);
	}
	
	@Override
	public List<AccountSettlementEntity> queryList(Map<String, Object> map){
		return accountSettlementDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accountSettlementDao.queryTotal(map);
	}
	
	@Override
	public void save(AccountSettlementEntity accountSettlement){
		accountSettlementDao.save(accountSettlement);
	}
	
	@Override
	public void update(AccountSettlementEntity accountSettlement){
		accountSettlementDao.update(accountSettlement);
	}
	
	@Override
	public void delete(String id){
		accountSettlementDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		accountSettlementDao.deleteBatch(ids);
	}
	
}
