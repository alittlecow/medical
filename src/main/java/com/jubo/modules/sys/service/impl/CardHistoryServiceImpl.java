package com.jubo.modules.sys.service.impl;

import com.jubo.modules.sys.dao.CardHistoryDao;
import com.jubo.modules.sys.entity.CardHistoryEntity;
import com.jubo.modules.sys.service.CardHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;





@Service("cardHistoryService")
public class CardHistoryServiceImpl implements CardHistoryService {
	@Autowired
	private CardHistoryDao cardHistoryDao;
	
	@Override
	public CardHistoryEntity queryObject(String id){
		return cardHistoryDao.queryObject(id);
	}
	
	@Override
	public List<CardHistoryEntity> queryList(Map<String, Object> map){
		return cardHistoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cardHistoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CardHistoryEntity cardHistory){
		cardHistoryDao.save(cardHistory);
	}
	
	@Override
	public void update(CardHistoryEntity cardHistory){
		cardHistoryDao.update(cardHistory);
	}
	
	@Override
	public void delete(String id){
		cardHistoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		cardHistoryDao.deleteBatch(ids);
	}
	
}
