package com.jubo.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.jubo.modules.sys.dao.AccountTransactionHistoryDao;
import com.jubo.modules.sys.entity.AccountTransactionHistoryEntity;
import com.jubo.modules.sys.service.AccountTransactionHistoryService;



@Service("accountTransactionHistoryService")
public class AccountTransactionHistoryServiceImpl implements AccountTransactionHistoryService {
	@Autowired
	private AccountTransactionHistoryDao accountTransactionHistoryDao;
	
	@Override
	public AccountTransactionHistoryEntity queryObject(String id){
		return accountTransactionHistoryDao.queryObject(id);
	}
	
	@Override
	public List<AccountTransactionHistoryEntity> queryList(Map<String, Object> map){
		return accountTransactionHistoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accountTransactionHistoryDao.queryTotal(map);
	}
	
	@Override
	public void save(AccountTransactionHistoryEntity accountTransactionHistory){
		accountTransactionHistoryDao.save(accountTransactionHistory);
	}
	
	@Override
	public void update(AccountTransactionHistoryEntity accountTransactionHistory){
		accountTransactionHistoryDao.update(accountTransactionHistory);
	}
	
	@Override
	public void delete(String id){
		accountTransactionHistoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		accountTransactionHistoryDao.deleteBatch(ids);
	}
	
}
