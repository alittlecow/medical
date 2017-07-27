package com.jubo.modules.sys.service.impl;

import com.jubo.modules.sys.dao.UserBankCardDao;
import com.jubo.modules.sys.entity.SysUserEntity;
import com.jubo.modules.sys.entity.UserBankCardEntity;
import com.jubo.modules.sys.service.UserBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("userBankCardService")
public class UserBankCardServiceImpl implements UserBankCardService {
	@Autowired
	private UserBankCardDao userBankCardDao;
	
	@Override
	public UserBankCardEntity queryObject(String id){
		return userBankCardDao.queryObject(id);
	}
	
	@Override
	public List<UserBankCardEntity> queryList(Map<String, Object> map){
		return userBankCardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userBankCardDao.queryTotal(map);
	}
	
	@Override
	public void save(UserBankCardEntity userBankCard, SysUserEntity userEntity){
		userBankCard.setUserId(userEntity.getUserId().toString());
		userBankCardDao.save(userBankCard);
	}
	
	@Override
	public void update(UserBankCardEntity userBankCard){
		userBankCardDao.update(userBankCard);
	}
	
	@Override
	public void delete(String id){
		userBankCardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		userBankCardDao.deleteBatch(ids);
	}
	
}
