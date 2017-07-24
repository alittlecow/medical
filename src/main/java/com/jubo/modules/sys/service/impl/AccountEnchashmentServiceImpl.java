package com.jubo.modules.sys.service.impl;

import com.jubo.modules.sys.dao.AccountEnchashmentDao;
import com.jubo.modules.sys.entity.AccountEnchashmentEntity;
import com.jubo.modules.sys.service.AccountEnchashmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("accountEnchashmentService")
public class AccountEnchashmentServiceImpl implements AccountEnchashmentService {
	@Autowired
	private AccountEnchashmentDao accountEnchashmentDao;
	
	@Override
	public AccountEnchashmentEntity queryObject(String id){
		return accountEnchashmentDao.queryObject(id);
	}
	
	@Override
	public List<AccountEnchashmentEntity> queryList(Map<String, Object> map){
		return accountEnchashmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accountEnchashmentDao.queryTotal(map);
	}
	
	@Override
	public void save(AccountEnchashmentEntity accountEnchashment){
		accountEnchashmentDao.save(accountEnchashment);
	}
	
	@Override
	public void update(AccountEnchashmentEntity accountEnchashment){
		accountEnchashmentDao.update(accountEnchashment);
	}
	
	@Override
	public void delete(String id){
		accountEnchashmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		accountEnchashmentDao.deleteBatch(ids);
	}
	
}
